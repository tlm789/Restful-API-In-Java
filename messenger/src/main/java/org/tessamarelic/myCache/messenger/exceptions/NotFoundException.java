package org.tessamarelic.myCache.messenger.exceptions;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -62349989;
	
	public NotFoundException(String message) {
		super(message);
	}
}
