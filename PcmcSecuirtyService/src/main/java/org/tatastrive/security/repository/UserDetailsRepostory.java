package org.tatastrive.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tatastrive.security.entity.UserDetails;

@Repository
public interface UserDetailsRepostory extends JpaRepository<UserDetails, Long>{
	
	Optional<UserDetails> findByUserName(String userName);
	
}
