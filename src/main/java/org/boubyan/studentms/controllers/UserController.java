package org.boubyan.studentms.controllers;

import javax.validation.Valid;

import org.boubyan.studentms.model.dtos.request.LoginRequestDto;
import org.boubyan.studentms.model.dtos.request.RegisterUserRequestDto;
import org.boubyan.studentms.model.dtos.response.LoginResponseDto;
import org.boubyan.studentms.model.dtos.response.RegisterUserResponseDto;
import org.boubyan.studentms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public LoginResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) {

		return LoginResponseDto.builder()
				.token(userService.authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword())).build();

	}

	@PostMapping("/register")
	public RegisterUserResponseDto register(@RequestBody @Valid RegisterUserRequestDto registerUserRequestDto) {
		return userService.register(registerUserRequestDto);
	}
}
