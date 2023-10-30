package org.tatastrive.security.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tatastrive.security.service.UserInfoService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	 
	@Autowired
	    private JwtService jwtService;
	  
	    @Autowired
	    private UserInfoService userDetailsService;
	    
	    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	      try {
	    	
	        String jwt = jwtService.parseJwt(request);
	        if (jwt != null && jwtService.validateJwtToken(jwt)) {
	          String username = jwtService.getClaims(jwt).getSubject();

	          logger.info("userName>>"+ username);
	          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	          
	          UsernamePasswordAuthenticationToken authentication = 
	              new UsernamePasswordAuthenticationToken(userDetails,
	                                                      null,
	                                                      userDetails.getAuthorities());
	          
	          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	          SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	      } catch (Exception e) {
	        logger.error("Cannot set user authentication: {}", e);
	      }

	      filterChain.doFilter(request, response);
	    }
		
	}
