package org.boubyan.studentms.model.dtos.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateCourseRequestDto implements Serializable {

	private static final long serialVersionUID = -1731476924981974317L;

	@NotNull(message = "Course ID is required")
	private Integer id;
	@NotBlank(message = "Course code is required")
	@Max(value = 10, message = "Course code value cannot exceed 10 characters")
	private String code;
	@NotBlank(message = "Course name is required")
	@Max(value = 50, message = "Course name value cannot exceed 50 characters")
	private String name;
}
