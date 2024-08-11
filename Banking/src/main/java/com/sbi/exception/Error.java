package com.sbi.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
	

	private String errorMessage;
	private String uri;
	private HttpStatus status;
	
	
	@Override
	public String toString() {
		return "Error [errorMessage=" + errorMessage + ", uri=" + uri + ", status=" + status + "]";
	}


	
}
