package com.af.mongodb.exception;

public class UserNotFound extends RuntimeException {
	public UserNotFound(String exception) {
		super(exception);
	}
}
