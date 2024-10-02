package org.boubyan.studentms.model.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto implements Serializable {

	private static final long serialVersionUID = -3384479924446531457L;

	private Integer id;
	private String code;
	private String name;
}
