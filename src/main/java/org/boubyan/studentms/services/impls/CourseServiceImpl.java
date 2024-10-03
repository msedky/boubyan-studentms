package org.boubyan.studentms.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.boubyan.studentms.exceptions.BusinessException;
import org.boubyan.studentms.mapper.CourseMapper;
import org.boubyan.studentms.model.dtos.CourseDto;
import org.boubyan.studentms.model.dtos.request.CreateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.UpdateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.ViewCoursesRequestDto;
import org.boubyan.studentms.model.entities.CourseEntity;
import org.boubyan.studentms.model.entities.ScheduleEntity;
import org.boubyan.studentms.repositories.CourseRepository;
import org.boubyan.studentms.repositories.ScheduleRepository;
import org.boubyan.studentms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public CourseDto create(CreateCourseRequestDto createCourseRequestDto) {
		courseRepository.findByCode(createCourseRequestDto.getCode()).ifPresent(d -> {
			throw new BusinessException("Duplicated Course Code");
		});
		CourseEntity courseEntity = courseMapper.fromCreateRequestDtoToEntity(createCourseRequestDto);
		courseEntity = courseRepository.save(courseEntity);
		CourseDto courseDto = courseMapper.fromEntityToDto(courseEntity);
		addToCacheViewCoursesDto(courseDto);
		return courseDto;
	}

	@Override
	public CourseDto update(UpdateCourseRequestDto updateCourseRequestDto) {
		courseRepository.findByCode(updateCourseRequestDto.getCode()).ifPresent(d -> {
			if (!d.getId().equals(updateCourseRequestDto.getId())) {
				throw new BusinessException(
						"Another Course already has the Code [" + updateCourseRequestDto.getCode() + "]");
			}
		});
		courseRepository.findById(updateCourseRequestDto.getId()).orElseThrow(
				() -> new BusinessException("Course id [" + updateCourseRequestDto.getId() + "] is Not Found"));
		CourseEntity courseEntity = courseMapper.fromUpdateRequestDtoToEntity(updateCourseRequestDto);
		courseEntity = courseRepository.save(courseEntity);
		CourseDto courseDto = courseMapper.fromEntityToDto(courseEntity);
		addToCacheViewCoursesDto(courseDto);
		return courseDto;
	}

	@Override
	public void delete(Integer id) {
		CourseEntity courseEntity = courseRepository.findById(id)
				.orElseThrow(() -> new BusinessException("Course id [" + id + "] is Not Found"));
		List<ScheduleEntity> schedules = scheduleRepository.findByCourse(courseEntity);
		if (schedules != null && !schedules.isEmpty()) {
			throw new BusinessException("Course id[" + id + "] is already ocurred inside a Schedule");
		}
		courseRepository.delete(courseEntity);
		removeFromCacheViewCoursesDto(courseMapper.fromEntityToDto(courseEntity));
	}

	@Override
	public CourseDto getById(Integer id) {
		CourseEntity courseEntity = courseRepository.findById(id)
				.orElseThrow(() -> new BusinessException("Course id [" + id + "] is Not Found"));
		CourseDto courseDto = courseMapper.fromEntityToDto(courseEntity);
		return courseDto;
	}

	@Override
	public List<CourseDto> viewCourses(ViewCoursesRequestDto viewCoursesDto) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseEntity> criteriaQuery = criteriaBuilder.createQuery(CourseEntity.class);
		Root<CourseEntity> root = criteriaQuery.from(CourseEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		if (viewCoursesDto.getId() != null) {
			predicates.add(criteriaBuilder.equal(root.get("id"), viewCoursesDto.getId()));
		}

		if (viewCoursesDto.getCode() != null) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("code")),
					"%" + viewCoursesDto.getCode().toLowerCase() + "%")); // Case-insensitive LIKE
		}

		if (viewCoursesDto.getName() != null) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
					"%" + viewCoursesDto.getName().toLowerCase() + "%")); // Case-insensitive LIKE
		}

		criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
		List<CourseEntity> courseEntities = entityManager.createQuery(criteriaQuery).getResultList();
		List<CourseDto> courseDtos = courseMapper.fromEntityToDtoList(courseEntities);
		return courseDtos;
	}

	private void addToCacheViewCoursesDto(CourseDto courseDto) {
		Cache cache = cacheManager.getCache("courses");
		if (cache != null) {
			@SuppressWarnings("unchecked")
			Map<Object, Object> cacheMap = (Map<Object, Object>) cache.getNativeCache();
			cacheMap.keySet().stream().filter(k -> k.toString().contains("ViewCoursesRequestDto")).forEach(ks -> {
				@SuppressWarnings("unchecked")
				List<CourseDto> courseDtoList = (List<CourseDto>) cacheMap.get(ks);
				if (courseDtoList != null && !courseDtoList.isEmpty()) {
					CourseDto foundDto = courseDtoList.stream().filter(dto -> dto.getId().equals(courseDto.getId()))
							.findFirst().get();
					if (foundDto != null) {
						courseDtoList.remove(foundDto);
						foundDto.setCode(courseDto.getCode());
						foundDto.setName(courseDto.getName());
						courseDtoList.add(foundDto);
						cacheMap.put(ks, courseDtoList);
					}
				}
			});
		}
	}

	private void removeFromCacheViewCoursesDto(CourseDto courseDto) {
		Cache cache = cacheManager.getCache("courses");
		if (cache != null) {
			@SuppressWarnings("unchecked")
			Map<Object, Object> cacheMap = (Map<Object, Object>) cache.getNativeCache();
			cacheMap.keySet().stream().filter(k -> k.toString().contains("ViewCoursesRequestDto")).forEach(ks -> {
				@SuppressWarnings("unchecked")
				List<CourseDto> courseDtoList = (List<CourseDto>) cacheMap.get(ks);
				if (courseDtoList != null && !courseDtoList.isEmpty()) {
					CourseDto foundDto = courseDtoList.stream().filter(dto -> dto.getId().equals(courseDto.getId()))
							.findFirst().get();
					if (foundDto != null) {
						courseDtoList.remove(foundDto);
						cacheMap.put(ks, courseDtoList);
					}
				}
			});
		}
	}

}
