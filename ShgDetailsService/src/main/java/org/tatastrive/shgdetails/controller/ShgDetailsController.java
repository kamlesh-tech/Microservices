package org.tatastrive.shgdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tatastrive.shgdetails.entity.ShgDetails;
import org.tatastrive.shgdetails.payload.request.ShgDetailsRequestBody;
import org.tatastrive.shgdetails.service.ShgDetailsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*This Controller handle all shg request like creating shg, Updating shg,
 * fetching shg details.
 * */
@RestController
@RequestMapping("/shg")
@RestControllerAdvice
public class ShgDetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShgDetailsController.class);

	List<ShgDetails> response = new LinkedList<>();
	
	@Autowired
	ShgDetailsService service;
	
	// Sending a standard Http Response
	public static ResponseEntity<Object> genrateStandardReponse(String message,HttpStatus status ,Object responseObject){
		
		return ResponseEntity.status(status).body(Map.of("Message", message,
				"Status", status.value(), "data", responseObject));
	}
	
	// Creating new SHG
	@PostMapping("/shgdetails")
	public ResponseEntity<?> savedShgDetails( @Valid @RequestBody ShgDetailsRequestBody shgDetailsrequestbody){
		response = service.savedShgDetails(shgDetailsrequestbody);
		return genrateStandardReponse("Shg Created.", HttpStatus.CREATED, response);
	}
	
	// Fetch All Shg Details
	@GetMapping("/shgdetails")
	public ResponseEntity<?> fetchShgDetails(){
		response = service.getAllShgDetails();
		return genrateStandardReponse("All Shg Details fetched.", HttpStatus.OK, response);
	}
	
//	getShgDetailsByShgId
	@GetMapping("/shgdetails/shgId/{shgid}")
	public ResponseEntity<?> fetchShgDetailsByShgId(@Valid @NotNull(message = "Shg Id Should Not be Blank.") @PathVariable("shgid") int shgId){
		response = service.getShgDetailsByShgId(shgId);
		return genrateStandardReponse("Shg Details fetched.", HttpStatus.OK, response);
	}

// Updating the existing Shg Details by Shg id
	@PutMapping("/updateshgdetails/{shgid}")
	public ResponseEntity<?> updateShgDetails(@Valid @NotNull(message = "Shg Id Should Not be Blank.") @PathVariable("shgid") int shgId, @RequestBody ShgDetailsRequestBody shgDetailsrequestbody){
		response = service.updateShgDetailsByShgId(shgId,shgDetailsrequestbody );
		return genrateStandardReponse("Shg Details fetched.", HttpStatus.OK, response);
		
	}
	
	
	@GetMapping("/details")
	public String createShgDetails(){
			
			return "shg Created";
		}
}
