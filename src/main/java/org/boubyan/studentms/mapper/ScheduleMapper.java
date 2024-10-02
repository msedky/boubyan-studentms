package org.boubyan.studentms.mapper;

import org.boubyan.studentms.model.dtos.ScheduleDto;
import org.boubyan.studentms.model.entities.ScheduleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

	ScheduleDto fromEntityToDto(ScheduleEntity entity);
	ScheduleEntity fromDtoToEntity(ScheduleDto scheduleDto);

}
