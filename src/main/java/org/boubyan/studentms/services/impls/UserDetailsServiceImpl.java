package org.boubyan.studentms.services.impls;

import org.boubyan.studentms.model.entities.UserEntity;
import org.boubyan.studentms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return org.springframework.security.core.userdetails.User.withUsername(userEntity.getUsername())
				.password(userEntity.getPassword()).authorities("USER") // Add roles if you have them
				.accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
	}

}
