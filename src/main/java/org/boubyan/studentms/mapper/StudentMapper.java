package org.boubyan.studentms.mapper;

import org.boubyan.studentms.model.dtos.StudentDto;
import org.boubyan.studentms.model.entities.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	StudentDto fromEntityToDto(StudentEntity entity);

	StudentEntity fromDtoToEntity(StudentDto student);
}
