package org.tatastrive.apigateway.util;

import org.springframework.stereotype.Component;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.tatastrive.apigateway.exception.JwtTokenMalformedException;
import org.tatastrive.apigateway.exception.JwtTokenMissingException;

//import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	

	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parserBuilder().setSigningKey(key()).build()
					.parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}
	 private Key key() {
		    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
		  }
	
	public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			 Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
}
