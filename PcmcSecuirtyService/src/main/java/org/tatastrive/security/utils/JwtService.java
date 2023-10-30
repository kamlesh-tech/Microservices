package org.tatastrive.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
  
import java.security.Key;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.*;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.web.util.WebUtils;
import org.tatastrive.security.service.UserInfoDetails;
@Component
public class JwtService {
	
	 private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
	 
	 @Value("${jwt.secret}")
		private String jwtSecret;
		
		
		@Value("${jwt.expirationMs}")
		private long jwtExpirationMs;
		
		 @Value("${jwt.cookies}")
		  private String jwtCookie;
		 
		 
		 public String parseJwt(HttpServletRequest request) {
			    String jwt = getJwtFromCookies(request);
			    return jwt;
			  }
		 
		  public String getJwtFromCookies(HttpServletRequest request) {
			    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
			    if (cookie != null) {
			      return cookie.getValue();
			    } else {
			      return null;
			    }
			  }

			  public ResponseCookie generateJwtCookie(UserInfoDetails userPrincipal , int roles) {
			    String jwt = generateTokenFromUsername(userPrincipal.getUsername(), roles);
			    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt)
			    		.build();
			    return cookie;
			  }

			  public ResponseCookie getCleanJwtCookie() {
			    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null)
			    		.path("/api").build();
			    return cookie;
			  }
			  
			  // get Claim from jwt token which include username and all claims...
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

			  public boolean validateJwtToken(String authToken) {
			    try {
			      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
			      return true;
			    } catch (MalformedJwtException e) {
			      logger.error("Invalid JWT token: {}", e.getMessage());
			    } catch (ExpiredJwtException e) {
			      logger.error("JWT token is expired: {}", e.getMessage());
			    } catch (UnsupportedJwtException e) {
			      logger.error("JWT token is unsupported: {}", e.getMessage());
			    } catch (IllegalArgumentException e) {
			      logger.error("JWT claims string is empty: {}", e.getMessage());
			    }
			    return false;
			  }

			  public String generateTokenFromUsername(String username, int role) {   
			   System.out.println("username>>>"+ username);
				  return Jwts.builder()
			               .setSubject(username)
			               .claim("Role", role)
			               .setIssuedAt(new Date())
			               .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
			               .signWith(key(), SignatureAlgorithm.HS256)
			               .compact();
			  }
}
