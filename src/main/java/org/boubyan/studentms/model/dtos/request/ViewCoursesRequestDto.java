package org.boubyan.studentms.model.dtos.request;

import lombok.Data;

@Data
public class ViewCoursesRequestDto {

	private Integer id;
	private String code;
	private String name;
}
