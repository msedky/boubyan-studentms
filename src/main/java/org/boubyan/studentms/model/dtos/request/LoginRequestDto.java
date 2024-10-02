package org.boubyan.studentms.model.dtos.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestDto {

	@NotBlank(message = "username is required")
	private String username;

	@NotBlank(message = "password is required")
	private String password;
}
