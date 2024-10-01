package org.boubyan.studentms.mapper;

import java.util.List;

import org.boubyan.studentms.model.dtos.CourseDto;
import org.boubyan.studentms.model.dtos.request.CreateCourseRequestDto;
import org.boubyan.studentms.model.entities.CourseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseDto fromEntityToDto(CourseEntity entity);

	List<CourseDto> fromEntityToDtoList(List<CourseEntity> entities);

	CourseEntity fromCreateRequestDtoToEntity(CreateCourseRequestDto createCourseRequestDto);

	CourseEntity fromDtoToEntity(CourseDto dto);

}