package org.boubyan.studentms.controllers;

import java.util.List;

import org.boubyan.studentms.model.dtos.CourseDto;
import org.boubyan.studentms.model.dtos.request.CreateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.ViewCoursesRequestDto;
import org.boubyan.studentms.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping
	public CourseDto create(@RequestBody CreateCourseRequestDto createCourseRequestDto) {
		return courseService.create(createCourseRequestDto);
	}

	@PostMapping("/viewCourses")
	public List<CourseDto> viewCourses(@RequestBody ViewCoursesRequestDto viewCoursesDto) {
		return courseService.viewCourses(viewCoursesDto);
	}

}