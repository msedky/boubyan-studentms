package org.boubyan.studentms.model.dtos;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CourseDto implements Serializable {

	private static final long serialVersionUID = -3384479924446531457L;

	private Integer id;
	private String code;
	private String name;
}
