package com.sbi.exception;

public class ResourceAlreadyExsistException extends RuntimeException{
	
	String message;
	public ResourceAlreadyExsistException(String message)
	{
		super(message);
	}
}
