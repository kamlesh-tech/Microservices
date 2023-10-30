package org.tatastrive.userrole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tatastrive.userrole.entity.UserRoleMap;
import org.tatastrive.userrole.entity.UserRoleMap.status;
import org.tatastrive.userrole.repository.UserRoleMapRepository;
import java.util.*;

@Service
public class UserRoleMapService {

	@Autowired
	UserRoleMapRepository repository;
// ger single role assign to user bu is default status
	public UserRoleMap getUserDeatailByUserId(int id) {
		// TODO Auto-generated method stub
		UserRoleMap userRoleDetails = repository.findByUserIdAndIsDefaultAndIsActive(id, status.Y,status.Y);
		return userRoleDetails;
	}
// Get all is active role assign to user
	public List<UserRoleMap> getUserDeatailByUserIdAndIsActive(int id) {
		// TODO Auto-generated method stub
		List<UserRoleMap> userDetails = repository.findByUserIdAndIsActive(id,status.Y);
		return userDetails;
	}

	// Upadating the isdefault by role id and user Id
	public UserRoleMap updateIsDefault(int userId,int roleId) {
		// TODO Auto-generated method stub
		List<UserRoleMap> userRoleList = repository.findByUserIdAndIsActive(userId, status.Y);
		userRoleList.forEach(userRole->{
			userRole.setIsDefault(status.N);
			repository.save(userRole);
		});
		UserRoleMap userRole = repository.findByUserIdAndRoleIdAndIsActive(userId,roleId, status.Y);
		userRole.setIsDefault(status.Y);
		userRole = repository.save(userRole);
		return userRole;
	}

}
