package com.skillenza.parkinglotjava.exception;

import java.util.Date;

public class BuisnessException extends RuntimeException {
	private Date timestamp;
	private String message;
	private String details;
	
	public BuisnessException(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
}
