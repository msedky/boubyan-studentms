package org.boubyan.studentms.model.dtos.request;

import lombok.*;

@Data
public class RegisterUserRequestDto {

	private String username;
	private String password;
}