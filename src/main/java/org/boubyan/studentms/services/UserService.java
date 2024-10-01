package org.boubyan.studentms.services;

import org.boubyan.studentms.model.dtos.request.RegisterUserRequestDto;
import org.boubyan.studentms.model.dtos.response.RegisterUserResponseDto;

public interface UserService {
	String authenticate(String username, String password);

	RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto);
}
