package org.tatastrive.security.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tatastrive.security.entity.UserRole;

@FeignClient(name = "User-Role-Details-Service")
public interface UserRoleClient {

	@GetMapping("/userrole/details/userId/{userId}")
	public UserRole fetchUserRoleDetails(@PathVariable Long userId);
}
