package org.tatastrive.memberdetails.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tatastrive.memberdetails.entity.MemberDetails;
import org.tatastrive.memberdetails.repository.MemberDetailsRepository;


@Service
public class MemberDetailsService {

	@Autowired
	MemberDetailsRepository repo;
	
	List<MemberDetails> response = new LinkedList<> ();
	
	public  List<MemberDetails> createMemberDetailsRecord( MemberDetails memberDetails) {
		MemberDetails response = (repo.saveAndFlush(memberDetails));
		return Collections.singletonList(response);
	}

	public List<MemberDetails> fetchAllMemberDetails() {
		// TODO Auto-generated method stub
		response = repo.findAll();
		return null;
	}

	public List<MemberDetails> fetchMemberDetailsByMemberId(int id) {
		// TODO Auto-generated method stub
		Optional <MemberDetails> optionalResponse = repo.findById((long) id);
		response = optionalResponse.map(List:: of).orElse(List.of());
		return response;
	}

	public MemberDetails updateExistingMemberRecord( int id,MemberDetails memberDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
