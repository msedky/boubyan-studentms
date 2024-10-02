package org.boubyan.studentms.model.dtos.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateCourseRequestDto {

	@NotBlank(message = "Course code is required")
	private String code;
	@NotBlank(message = "Course name is required")
	private String name;
}
