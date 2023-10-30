package org.tatastrive.shgdetails.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tatastrive.shgdetails.entity.ShgDetails;

@Repository
public interface ShgRepository extends JpaRepository<ShgDetails, Long>{

	Optional<ShgDetails> findByShgId(long shgId);

}
