package org.tatastrive.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tatastrive.security.entity.UserRole;
import org.tatastrive.security.entity.UserRole.status;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

	UserRole findByUserIdAndIsDefaultAndIsActive(Long userId, status y, status y2);
	
	
}
