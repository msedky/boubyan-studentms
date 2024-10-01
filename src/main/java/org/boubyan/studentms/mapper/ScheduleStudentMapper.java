package org.boubyan.studentms.mapper;

import org.boubyan.studentms.model.dtos.ScheduleStudentDto;
import org.boubyan.studentms.model.entities.ScheduleStudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleStudentMapper {
	ScheduleStudentDto fromEntityToDto(ScheduleStudentEntity entity);
}
