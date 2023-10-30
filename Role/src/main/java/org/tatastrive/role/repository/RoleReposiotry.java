package org.tatastrive.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tatastrive.role.entity.Role;

@Repository
public interface RoleReposiotry extends JpaRepository<Role, Long>{
	
	List<Role> findByIdIn(List<Integer> roleIds);
	
//	List<Role> findByRoleName(String role);
	@Query
	(value  = "select * from role_description where id in :id" , nativeQuery = true)
	List<Role> findByRoleId(List<Long> id);
}
