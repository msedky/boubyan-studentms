package org.boubyan.studentms.model.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleStudentDto {
	private Integer id;
	private ScheduleDto schedule;
	private StudentDto student;
	private LocalDateTime registrationDateTime;
}
