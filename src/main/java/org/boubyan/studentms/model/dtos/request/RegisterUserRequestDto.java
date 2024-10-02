package org.boubyan.studentms.model.dtos.request;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
public class RegisterUserRequestDto {

	@NotBlank(message = "username is required")
	private String username;
	@NotBlank(message = "password is required")
	private String password;
}