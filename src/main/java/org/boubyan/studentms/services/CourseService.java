package org.boubyan.studentms.services;

import java.util.List;

import org.boubyan.studentms.model.dtos.CourseDto;
import org.boubyan.studentms.model.dtos.request.CreateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.UpdateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.ViewCoursesRequestDto;

public interface CourseService {
	CourseDto create(CreateCourseRequestDto createCourseRequestDto);

	CourseDto update(UpdateCourseRequestDto updateCourseRequestDto);

	void delete(Integer id);

	CourseDto getById(Integer id);

	List<CourseDto> viewCourses(ViewCoursesRequestDto viewCoursesDto);

}