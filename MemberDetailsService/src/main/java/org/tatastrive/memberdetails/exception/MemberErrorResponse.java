package org.tatastrive.memberdetails.exception;

import java.util.*;

import lombok.Data;

@Data
public class MemberErrorResponse {

	private int status;
	private String path;
	private List<String> errorMessage;
	private Date timestamp ;
	public int getStatus() {
		return status;
	}
	
}
