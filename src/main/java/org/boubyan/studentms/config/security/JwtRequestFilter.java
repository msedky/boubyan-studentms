package org.boubyan.studentms.config.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	public JwtRequestFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String token = jwtTokenProvider.resolveToken(request);
		if (token != null && jwtTokenProvider.validateToken(token)) {
			String username = jwtTokenProvider.getUsername(token);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					jwtTokenProvider.getAuthorities(token));

			long lastActivity = jwtTokenProvider.getLastActivity(token);
			long currentTime = System.currentTimeMillis();

			// Check if the session is still valid
			if (currentTime - lastActivity > 10 * 60 * 1000) { // 10 minutes inactivity
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User session has expired.");
				return; // End the filter chain
			}

			// Update last activity timestamp
			// String newToken = jwtTokenProvider.createTokenWithUpdatedActivity(auth,
			// currentTime);
			// response.setHeader("Authorization", "Bearer " + newToken); // Optionally send
			// new token back

			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}
}
