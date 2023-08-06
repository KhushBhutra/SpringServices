package com.sirion.Exception;

import org.springframework.stereotype.Component;

public class DriverException extends Exception {

	public DriverException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DriverException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DriverException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DriverException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
