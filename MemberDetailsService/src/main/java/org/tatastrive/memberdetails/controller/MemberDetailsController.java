package org.tatastrive.memberdetails.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tatastrive.memberdetails.entity.MemberDetails;
import org.tatastrive.memberdetails.service.MemberDetailsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/*This Controller Handle all Http Request regarding memeber details , 
 * like Creating member, Updating member and fetching member details. 
 * */

@RestController
@RestControllerAdvice
@CrossOrigin("*")
@RequestMapping("memberdetails")
public class MemberDetailsController {
	
	@Autowired
	MemberDetailsService service;
	
	List<MemberDetails> memberDetails = new LinkedList<>();
	
// Genrate Standard Reponse  
	public static ResponseEntity<?> genrateStandardResponse(String message, HttpStatus status, Object responseObj) {
		
		return ResponseEntity.status(null).body(Map.of(
				"Message",message,"Status",status, "data", responseObj));
	}
	
// Creating Member Details
	public ResponseEntity<?> saveMemberDeatils(@Valid @RequestBody MemberDetails memberDetails){
		memberDetails = (MemberDetails) service.createMemberDetailsRecord(memberDetails);
		return genrateStandardResponse("Member is Created.", HttpStatus.CREATED, memberDetails);
	}
	
// Fecthing Member Details
	@GetMapping("/getall")
	public ResponseEntity<?> fetchMemberDetails(){
		memberDetails = service.fetchAllMemberDetails();
		return genrateStandardResponse("Fetched Member Record Successfull. ",HttpStatus.OK, memberDetails);
	}

// Fecthing Member Details by member id
	@GetMapping("/getbymemberid/{id}")
	public ResponseEntity<?> fetchMemberDetailsById(@NotNull(message = "Member Id can't be Blank.")@PathVariable("id") int id){
		memberDetails= service.fetchMemberDetailsByMemberId( id);
		return genrateStandardResponse("Fetched Member Record Successfull. ",HttpStatus.OK, memberDetails);
	}
// Upadting Member Details 
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatedMemberDetails(@Valid @NotNull(message = "Member Id can't be Blank.")@PathVariable("id") int id,@RequestBody MemberDetails memberDetails) {
		memberDetails = service.updateExistingMemberRecord( id, memberDetails);
		return genrateStandardResponse("Member Details Update Successfully.",HttpStatus.OK ,memberDetails);
	}
}


