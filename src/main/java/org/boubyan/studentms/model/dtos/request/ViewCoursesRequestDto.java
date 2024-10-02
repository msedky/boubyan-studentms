package org.boubyan.studentms.model.dtos.request;

import java.io.Serializable;

import javax.validation.constraints.Max;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ViewCoursesRequestDto implements Serializable {

	private static final long serialVersionUID = 8591663295640438650L;
	
	private Integer id;
	@Max(value = 10, message = "Course code value cannot exceed 10 characters")
	private String code;
	@Max(value = 50, message = "Course name value cannot exceed 50 characters")
	private String name;
}
