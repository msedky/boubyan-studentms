package org.boubyan.studentms.services;

import java.util.List;

import org.boubyan.studentms.model.dtos.CourseDto;
import org.boubyan.studentms.model.dtos.request.CreateCourseRequestDto;
import org.boubyan.studentms.model.dtos.request.ViewCoursesRequestDto;

public interface CourseService {
	CourseDto create(CreateCourseRequestDto createCourseRequestDto);

	List<CourseDto> viewCourses(ViewCoursesRequestDto viewCoursesDto);

}
