package org.boubyan.studentms.model.dtos.request;

import lombok.Data;

@Data
public class LoginRequestDto {

	private String username;
	private String password;
}
