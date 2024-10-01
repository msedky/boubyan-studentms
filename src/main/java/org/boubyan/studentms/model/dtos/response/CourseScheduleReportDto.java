package org.boubyan.studentms.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseScheduleReportDto {
	private String code;
	private String name;
	private String startDate;
	private String endDate;
	private Long enrolledStudents;
}
