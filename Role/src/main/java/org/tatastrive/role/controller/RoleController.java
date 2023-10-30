package org.tatastrive.role.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tatastrive.role.entity.Role;
import org.tatastrive.role.service.RoleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
public class RoleController {
 
	@Autowired
	RoleService service;
	
	public static ResponseEntity<?>genrateRepponse(String message, HttpStatus status, Object responseObject) {
		return ResponseEntity.status(status)
				.body(Map.of("Message", message,"Status",status ,"data", responseObject));
	}

// Fetchinh role description based on role id......
	@PostMapping("/roleId")
	public ResponseEntity<?>getRoleDetails(@RequestBody List<Integer>role){
		List <Role>roleDetails = service.fetchAllRoleDetails(role);
		return genrateRepponse("Role Fecthed Successfully.",HttpStatus.OK, roleDetails);
	}
}
