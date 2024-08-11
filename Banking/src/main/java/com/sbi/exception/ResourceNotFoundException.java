package com.sbi.exception;

import lombok.NoArgsConstructor;

public class ResourceNotFoundException extends RuntimeException{
	
	
	public ResourceNotFoundException()
	{
	     this("Resource Not availble");
	}
	public ResourceNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
