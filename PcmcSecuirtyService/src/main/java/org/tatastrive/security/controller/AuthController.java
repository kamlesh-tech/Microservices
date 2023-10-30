package org.tatastrive.security.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tatastrive.security.entity.UserRole;
import org.tatastrive.security.feignclient.UserRoleClient;
import org.tatastrive.security.model.UserAndRoleResponse;
import org.tatastrive.security.payload.request.LoginRequest;
import org.tatastrive.security.service.UserInfoDetails;
import org.tatastrive.security.service.UserInfoService;
import org.tatastrive.security.utils.JwtService;

import io.jsonwebtoken.Claims;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Value("${jwt.cookies}")
	  private String jwtCookie;
	 
	@Autowired
    private UserInfoService service;
  
    @Autowired
    private JwtService jwtService;
  
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
	 private UserRoleClient client;
	
    
    @Autowired
    JwtService jwtUtils;
    
    ModelMapper mapper = new ModelMapper();
    
    @PostMapping("/signin")
    public ResponseEntity<?>authenticateUser(@Valid @RequestBody LoginRequest authRequest){
    	
    	System.out.println(":data>>"+ authRequest.getUsername()+">>"+ authRequest.getPassword());
    	 Authentication authentication = 
    			 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    	 
    	 SecurityContextHolder.getContext().setAuthentication(authentication);
    	 
    	 UserInfoDetails userDetails = (UserInfoDetails) authentication.getPrincipal();
    	 // Fetching Role Details For User
    	 UserRole roleDetails = client.fetchUserRoleDetails(userDetails.getUserId());
    	 
    	 System.out.println("Role Details >>>"+ roleDetails.toString());
    	 // Model Mapper map user details and user role entity as a single entity and return reponse
    	 mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    	 UserAndRoleResponse userResponse = mapper.map(userDetails, UserAndRoleResponse.class);
    	 mapper.map(roleDetails, userResponse);
    	 
    	 // genrating jwt token
    	 ResponseCookie jwtCookie  = jwtUtils.generateJwtCookie(userDetails, userResponse.getRoleId());
    	 
    	 
    	 return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
    		        .body(userResponse);
    }
    
// genrating jwt token from previous jwt token
    @GetMapping("/generatejwttokenfromusername")
	 public ResponseEntity<?>genrateJwtTokenFromUserName(HttpServletRequest request){
    	String jwt = jwtService.parseJwt(request);
    	Claims claims = jwtService.getClaims(jwt);
    	String userName =claims.getSubject();
    	int roles =(claims.get("Role", Integer.class));
    	
    	String newJwtToken = jwtService.generateTokenFromUsername(userName, roles);
    	ResponseCookie cookie = ResponseCookie.from(jwtCookie, newJwtToken).build();
    	
    	return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
		        .body("Jwt Token Genrated Successfully.");  
	 }
}
