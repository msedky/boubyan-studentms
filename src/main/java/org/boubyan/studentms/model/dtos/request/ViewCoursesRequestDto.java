package org.boubyan.studentms.model.dtos.request;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ViewCoursesRequestDto implements Serializable {

	private static final long serialVersionUID = 8591663295640438650L;

	private Integer id;
	@Size(max = 10, message = "Course code value cannot exceed 10 characters")
	private String code;
	@Size(max = 50, message = "Course name value cannot exceed 50 characters")
	private String name;
}
