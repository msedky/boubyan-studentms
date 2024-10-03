package org.boubyan.studentms.config.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

	private String TOKEN_SECRECT = "uim8asd63212safg01421.5436xdaa";
	private Long TOKEN_EXPIRATION = (long) (5 * 60 * 1000);

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(TOKEN_SECRECT).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(TOKEN_SECRECT).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String createToken(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("roles", userDetails.getAuthorities());
		claims.put("lastActivity", System.currentTimeMillis());
		Date now = new Date();
		Date validity = new Date(now.getTime() + TOKEN_EXPIRATION);

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, TOKEN_SECRECT).compact();
	}

	@SuppressWarnings("unchecked")
	public List<GrantedAuthority> getAuthorities(String token) {
		Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRECT).parseClaimsJws(token).getBody();
		List<LinkedHashMap<String, String>> roles = claims.get("roles", List.class);
		List<GrantedAuthority> grantedAuthorities = roles.stream().map(r -> {
			return new SimpleGrantedAuthority(r.get("authority"));
		}).collect(Collectors.toList());
		return grantedAuthorities;
	}

	public Long getLastActivity(String token) {
		Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRECT).parseClaimsJws(token).getBody();
		return claims.get("lastActivity", Long.class);
	}

	public String createTokenWithUpdatedActivity(Authentication authentication, long lastActivity) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("roles", userDetails.getAuthorities());
		claims.put("lastActivity", lastActivity); // Update last activity timestamp

		Date now = new Date();
		Date validity = new Date(now.getTime() + 5 * 60 * 1000); // 5 minutes validity

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, TOKEN_SECRECT).compact();
	}

}
