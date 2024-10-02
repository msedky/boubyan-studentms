package org.boubyan.studentms.model.dtos.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateCourseRequestDto implements Serializable {

	private static final long serialVersionUID = -1731476924981974317L;

	private Integer id;
	private String code;
	private String name;
}
