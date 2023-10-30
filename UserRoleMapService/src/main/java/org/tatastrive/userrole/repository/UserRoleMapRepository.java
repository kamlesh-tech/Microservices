package org.tatastrive.userrole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tatastrive.userrole.entity.UserRoleMap;
import org.tatastrive.userrole.entity.UserRoleMap.status;

@Repository
public interface UserRoleMapRepository extends JpaRepository<UserRoleMap, Long>{

	UserRoleMap findByUserIdAndIsDefaultAndIsActive(int id, status y, status y2);

	List<UserRoleMap> findByUserIdAndIsActive(int id, status y);

	UserRoleMap findByUserIdAndRoleIdAndIsActive(int userId, int roleId, status y);

}
