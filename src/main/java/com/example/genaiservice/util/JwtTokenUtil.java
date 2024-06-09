package com.example.genaiservice.util;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	private final String secretKey = "secretKeyAdi";

	/**
	 * 
	 * @param claims
	 * @param subject- pass userId in subject
	 * @return
	 */
	public String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	public String getUserId(String token) {
		Claims claims = extractAllClaims(token);
		return claims.get("userId", String.class);
	}

	public boolean checkExpiration(String token) {
		Claims claims = extractAllClaims(token);
		Date expirationTime = claims.getExpiration();
		return expirationTime.before(new Date());
	}

	// useing above we can extreact eanyting needed in claim
	// unused
	public String extractUsername(String token) {
		return extractClaim(token, claims -> claims.get("username", String.class));
	}

	public Date getExpirationTime(String token) {
		Claims claims = extractAllClaims(token);
		return claims.getExpiration();
	}

	// unused
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String extractUsernameUsingSubject(String token) {
		return extractClaim(token, Claims::getSubject);
	}

}
