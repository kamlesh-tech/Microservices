package org.tatastrive.userrole.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tatastrive.userrole.entity.UserRoleMap;
import org.tatastrive.userrole.service.UserRoleMapService;

@RestController
@RequestMapping("/userrole")
@CrossOrigin("*")
public class UserRoleMapController {
	
	@Autowired
	UserRoleMapService service;
// Genrating Standard Response 
	public static ResponseEntity<?> genrateResponse(String message, HttpStatus status, Object responseObj){
		return ResponseEntity.status(status).body(Map.of("Message", message, "Status", status,"data",responseObj));	
	}
	
	
// Fetchinh User Role details by User Id and Default Active Status  
		@GetMapping("/details/isActive/userId/{id}")
		public ResponseEntity<?> fetchUserRoleDetailsByActiveStatus(@PathVariable("id") int id){
			List<UserRoleMap> userDetails = service.getUserDeatailByUserIdAndIsActive(id);
			return genrateResponse("Fetched List Of User Roles", HttpStatus.OK,userDetails );
		}
		
// Fetchinh User Role details by User Id and Default Active Status  
	@GetMapping("/details/userId/{id}")
	public UserRoleMap fetchUserRoleDetails(@PathVariable("id") int id){
		UserRoleMap userDetails = service.getUserDeatailByUserId(id);
		return userDetails;
	}
	
// Upadte the isdefault role by userid and role id 
	@PatchMapping("/userId/{userId}/roleId/{roleId}")
	public ResponseEntity<?>activateDefaultRoleId(@PathVariable("userId") int userId , @PathVariable("roleId") int roleId ){
		UserRoleMap userDetails = service.updateIsDefault(userId ,roleId);
		return genrateResponse("User Role is Updated.", HttpStatus.OK,userDetails);
	}
}
