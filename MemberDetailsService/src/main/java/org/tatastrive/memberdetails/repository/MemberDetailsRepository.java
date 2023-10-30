package org.tatastrive.memberdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tatastrive.memberdetails.entity.MemberDetails;

@Repository
public interface MemberDetailsRepository extends JpaRepository<MemberDetails, Long> {

}
