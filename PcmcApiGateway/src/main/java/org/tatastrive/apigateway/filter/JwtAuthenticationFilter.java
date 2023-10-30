package org.tatastrive.apigateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import java.util.List;
import java.util.function.Predicate;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.server.ServerWebExchange;
import org.tatastrive.apigateway.exception.JwtTokenMalformedException;
import org.tatastrive.apigateway.exception.JwtTokenMissingException;
import org.tatastrive.apigateway.util.JwtUtil;
import org.springframework.http.HttpHeaders;

import io.jsonwebtoken.Claims;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

@Component
//public class JwtAuthenticationFilter implements GlobalFilter{

public class JwtAuthenticationFilter implements GatewayFilter{
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private  JwtUtil jwtUtil;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		
		
		ServerHttpRequest request =  exchange.getRequest();
		
		// User role from each Services if only one user role
//		String userRole = exchange.getRequest().getHeaders().getFirst("Role");
		
//		 If I have List Of role for Same User....
		 HttpHeaders headers = request.getHeaders();
		    
		    // Extract the "Role" headers as a list of roles
		    List<String> roleList= headers.get("Role");

		final List<String> apiEndpoints = List.of("/signin", "/signup");
		
		Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream().
				noneMatch(uri->r.getURI().getPath().contains(uri));
		
		if(isApiSecured.test(request)) {
			 
			if(!request.getHeaders().containsKey("Authorization")) {
				return handleUnauthorizedResponse(exchange, "Unauthorized access", request.getURI().getPath(), HttpStatus.UNAUTHORIZED);
				
			}
		
		final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

		try {
			jwtUtil.validateToken(token);
			
		} catch (JwtTokenMalformedException  | JwtTokenMissingException e) {
			return handleUnauthorizedResponse(exchange, "Invalid or missing JWT token", request.getURI().getPath(), HttpStatus.UNAUTHORIZED);
			 
		}
			Claims claims = jwtUtil.getClaims(token);
			    // Get the expiration time from the claims
			    Date expirationDate = claims.getExpiration();
			 
			    System.out.println(claims.toString());
			     String userRole =String.valueOf(claims.get("Role", Integer.class));
				 
			    if (expirationDate != null && expirationDate.before(new Date())) {
			        // Token has expired; handle accordingly (e.g., return a 401 Unauthorized response)
			        return handleUnauthorizedResponse(exchange, "JWT token has expired", 
			        		request.getURI().getPath(), HttpStatus.UNAUTHORIZED);
			    }
			    logger.info("role>>>>"+userRole);
			    logger.info("roleList>>> "+roleList);
			  
			    if(!roleList.stream().anyMatch(role -> role.equals(userRole))){
			    	  return handleUnauthorizedResponse(exchange, "Unauthorized Aceess.", 
				        		request.getURI().getPath(), HttpStatus.UNAUTHORIZED);
			    }
	
			 // If the token is not expired, continue processing the request
			    exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
		
	}
		return chain.filter(exchange);
	}
	
	private Mono<Void> handleUnauthorizedResponse(ServerWebExchange exchange, String errorMessage,
			String path, HttpStatus httpStatus) {
	    ServerHttpResponse response = exchange.getResponse();
	    response.setStatusCode(httpStatus);
	    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

	    // Create a JSON response with status code, message, and path
	    String jsonResponse = "{\"statusCode\": " + httpStatus.value() + ", \"message\": \"" 
	    						+ errorMessage + "\", \"path\": \"" + path + "\"}";

	    DataBuffer buffer = response.bufferFactory().wrap(jsonResponse.getBytes(StandardCharsets.UTF_8));
	    return response.writeWith(Mono.just(buffer));
	}
	
}
