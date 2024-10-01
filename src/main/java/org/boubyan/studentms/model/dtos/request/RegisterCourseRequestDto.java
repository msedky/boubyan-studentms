package org.boubyan.studentms.model.dtos.request;

import org.boubyan.studentms.model.dtos.ScheduleDto;
import org.boubyan.studentms.model.dtos.StudentDto;

import lombok.Data;

@Data
public class RegisterCourseRequestDto {
	private ScheduleDto schedule;
	private StudentDto student;
}
