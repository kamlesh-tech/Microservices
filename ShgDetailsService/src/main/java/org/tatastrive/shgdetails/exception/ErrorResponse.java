package org.tatastrive.shgdetails.exception;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
	
		
	    private int status;
	    private String path;	
	    private List<String>errorMessage;
	    private Date timestamp;
	
}	
