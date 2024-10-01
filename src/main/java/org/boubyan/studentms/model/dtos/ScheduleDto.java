package org.boubyan.studentms.model.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleDto {
	private Long id;
	private CourseDto course;
	private LocalDate startDate;
	private LocalDate endDate;
}
