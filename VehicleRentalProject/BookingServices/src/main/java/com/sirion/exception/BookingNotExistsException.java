package com.sirion.exception;

public class BookingNotExistsException extends RuntimeException {
	private String message;
	public BookingNotExistsException() {
		
	}
	public BookingNotExistsException(String msg) {
		super(msg);
		this.message = msg;
	}
}
