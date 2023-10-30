package org.tatastrive.shgdetails.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tatastrive.shgdetails.entity.ShgDetails;
import org.tatastrive.shgdetails.payload.request.ShgDetailsRequestBody;
import org.tatastrive.shgdetails.repository.ShgRepository;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@Service
public class ShgDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(ShgDetailsService.class);
	
	
	@Autowired
	private ShgRepository shgRepository;
	
	ModelMapper mapper = new ModelMapper();
	List<ShgDetails> response = new LinkedList<>();

// Saving the shg details
	public List<ShgDetails> savedShgDetails(@Valid ShgDetailsRequestBody shgDetailsrequestbody) {
		// Avoiding the mapping of shg ID
		mapper.typeMap(ShgDetailsRequestBody.class, ShgDetails.class)
	    .addMappings(mappers -> mappers.skip(ShgDetails::setShgId));
		
		ShgDetails shgdetails = mapper.map(shgDetailsrequestbody, ShgDetails.class);
		shgdetails.setShgId(shgdetails.getShgFormationDate().substring(0, 4)+""+shgdetails.getPincode());
		shgdetails = shgRepository.saveAndFlush(shgdetails);
		return Collections.singletonList(shgdetails);
	}

	
// Fetching all Shg Details 
	public List<ShgDetails> getAllShgDetails() {
		// TODO Auto-generated method stub
		response = shgRepository.findAll();
		return response;
	}
	
// Fetching Shg Details By SHG ID
	public List<ShgDetails> getShgDetailsByShgId(int shgId) {
		Optional<ShgDetails> details = shgRepository.findByShgId((long)shgId);
		response = details.map(List::of).orElse(List.of());
		return response;
	}

// Updating the existing Shg Details record by shg id..
	public List<ShgDetails> updateShgDetailsByShgId(@Valid  int shgId,
		ShgDetailsRequestBody shgDetailsrequestbody) {
		ShgDetails existingDeatils = shgRepository.findByShgId((long) shgId)
				.orElseThrow(()->new NotFoundException("Shg Details Not Found with :"+ shgId));
		 
		mapper.typeMap(ShgDetailsRequestBody.class, ShgDetails.class)
	    .addMappings(mappers -> mappers.skip(ShgDetails::setShgId));
		mapper.map(shgDetailsrequestbody, existingDeatils);
		ShgDetails updatedDetails = shgRepository.saveAndFlush(existingDeatils);
		response = List.of(updatedDetails);
		return response;
	}
	
	
}
