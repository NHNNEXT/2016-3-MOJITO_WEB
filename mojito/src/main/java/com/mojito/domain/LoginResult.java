package com.mojito.domain;

public class LoginResult {
	private int statusCode;
	private String errorMessage;
	
	private LoginResult(int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static LoginResult ok() {
		return new LoginResult(1, null);
	}
	
	public static LoginResult emailNotFound(String errorMessage) {
		return new LoginResult(2, errorMessage);
	}
	
	public static LoginResult invalidPassword(String errorMessage) {
		return new LoginResult(3, errorMessage);
	}

	public static LoginResult emptyInput(String errorMessage) {
		return new LoginResult(0, errorMessage);
	}
	
}
