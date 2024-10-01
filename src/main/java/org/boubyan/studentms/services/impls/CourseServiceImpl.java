package org.boubyan.studentms.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.boubyan.studentms.mapper.CourseMapper;
import org.boubyan.studentms.model.dtos.CourseDto;
import org.boubyan.studentms.model.dtos.request.CreateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.ViewCoursesRequestDto;
import org.boubyan.studentms.model.entities.CourseEntity;
import org.boubyan.studentms.repositories.CourseRepository;
import org.boubyan.studentms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private EntityManager entityManager;

	@Override
	public CourseDto create(CreateCourseRequestDto createCourseRequestDto) {
		CourseEntity courseEntity = courseMapper.fromCreateRequestDtoToEntity(createCourseRequestDto);
		courseEntity = courseRepository.save(courseEntity);
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

}
