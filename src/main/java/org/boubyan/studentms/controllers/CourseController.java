package org.boubyan.studentms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.boubyan.studentms.model.dtos.CourseDto;
import org.boubyan.studentms.model.dtos.request.CreateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.UpdateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.ViewCoursesRequestDto;
import org.boubyan.studentms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping
	public CourseDto create(@RequestBody @Valid CreateCourseRequestDto createCourseRequestDto) {
		return courseService.create(createCourseRequestDto);
	}

	@PutMapping("/{id}")
	@CachePut(value = "courses", key = "#id")
	public CourseDto update(@PathVariable Integer id, @RequestBody @Valid UpdateCourseRequestDto updateCourseRequestDto) {
		updateCourseRequestDto.setId(id);
		return courseService.update(updateCourseRequestDto);
	}

	@DeleteMapping("/byId/{id}")
	@CacheEvict(value = "courses", key = "#id")
	public void delete(@PathVariable Integer id) {
		courseService.delete(id);
	}

	@GetMapping("/byId/{id}")
	@Cacheable(value = "courses", key = "#id")
	public CourseDto getById(@PathVariable Integer id) {
		return courseService.getById(id);
	}

	@PostMapping("/viewCourses")
	@Cacheable(value = "courses", key = "#viewCoursesDto")
	public List<CourseDto> viewCourses(@RequestBody ViewCoursesRequestDto viewCoursesDto) {
		return courseService.viewCourses(viewCoursesDto);
	}

}