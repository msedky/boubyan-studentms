package org.boubyan.studentms.mapper;

import org.boubyan.studentms.model.dtos.ScheduleStudentDto;
import org.boubyan.studentms.model.entities.ScheduleStudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ScheduleMapper.class, StudentMapper.class })
public interface ScheduleStudentMapper {
	ScheduleStudentDto fromEntityToDto(ScheduleStudentEntity entity);
}
