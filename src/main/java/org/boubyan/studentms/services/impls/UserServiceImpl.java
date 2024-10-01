package org.boubyan.studentms.services.impls;

import org.boubyan.studentms.config.security.JwtTokenProvider;
import org.boubyan.studentms.mapper.UserMapper;
import org.boubyan.studentms.model.dtos.request.RegisterUserRequestDto;
import org.boubyan.studentms.model.dtos.response.RegisterUserResponseDto;
import org.boubyan.studentms.model.entities.UserEntity;
import org.boubyan.studentms.repositories.UserRepository;
import org.boubyan.studentms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserMapper userMapper;

	@Override
	public String authenticate(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		authentication = authManager.authenticate(authentication);
		return jwtTokenProvider.createToken(authentication);
	}

	@Override
	public RegisterUserResponseDto register(RegisterUserRequestDto registerUserRequestDto) {
		UserEntity userEntity = UserEntity.builder().username(registerUserRequestDto.getUsername())
				.password(passwordEncoder.encode(registerUserRequestDto.getPassword())).build();
		userEntity = userRepository.save(userEntity);
		RegisterUserResponseDto registerUserResponseDto = userMapper.toRegisterUserResponseDto(userEntity);
		return registerUserResponseDto;
	}
}
