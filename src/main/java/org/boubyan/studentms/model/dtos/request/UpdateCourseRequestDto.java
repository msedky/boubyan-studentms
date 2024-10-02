package org.boubyan.studentms.model.dtos.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateCourseRequestDto implements Serializable {

	private static final long serialVersionUID = -1731476924981974317L;

	@NotNull(message = "Course ID is required")
	private Integer id;
	@NotBlank(message = "Course code is required")
	private String code;
	@NotBlank(message = "Course name is required")
	private String name;
}
