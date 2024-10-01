package org.boubyan.studentms.model.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class StudentDto {
	private Long id;
	private String firstName;
	private String secondName;
	private String thirdName;
	private String fourthName;
	private Date birthDate;
	private String gender;
	private UserDto user;
}
