package org.boubyan.studentms.config.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken(request);
		if (token != null && jwtTokenProvider.validateToken(token)) {
			String username = jwtTokenProvider.getUsername(token);

			// Load UserDetails from UserDetailsService
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
					jwtTokenProvider.getAuthorities(token));

			long lastActivity = jwtTokenProvider.getLastActivity(token);
			long currentTime = System.currentTimeMillis();

			// Check if the session is still valid
			if (currentTime - lastActivity > 10 * 60 * 1000) { // 10 minutes inactivity
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User session has expired.");
				return; // End the filter chain
			}

			// Update last activity timestamp
			String newToken = jwtTokenProvider.createTokenWithUpdatedActivity(auth, currentTime);
			response.setHeader("Authorization", "Bearer " + newToken);
			// Optionally send
			// new token back

			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}
}
