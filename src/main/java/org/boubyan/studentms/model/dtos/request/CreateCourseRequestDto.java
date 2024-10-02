package org.boubyan.studentms.model.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateCourseRequestDto {

	@NotBlank(message = "Course code is required")
	@Size(max = 10, message = "Course code value cannot exceed 10 characters")
	private String code;
	@NotBlank(message = "Course name is required")
	@Size(max = 50, message = "Course name value cannot exceed 50 characters")
	private String name;
}
