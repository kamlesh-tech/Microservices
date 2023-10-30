package org.tatastrive.role.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tatastrive.role.entity.Role;
import org.tatastrive.role.repository.RoleReposiotry;

@Service
public class RoleService {

	@Autowired
	RoleReposiotry repository;
	
// Fetching role description based on role id.....
	public List<Role> fetchAllRoleDetails(List<Integer> ids) {
		// TODO Auto-generated method stub
		List<Role> roleDetails = repository.findByIdIn(ids);
		return roleDetails;
	}


}
