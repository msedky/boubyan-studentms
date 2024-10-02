package org.boubyan.studentms.model.dtos.request;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ViewCoursesRequestDto implements Serializable {

	private static final long serialVersionUID = 8591663295640438650L;
	
	private Integer id;
	private String code;
	private String name;
}
