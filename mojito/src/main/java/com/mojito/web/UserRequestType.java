package com.mojito.web;

public enum UserRequestType {
	REQUEST_TO_MET(1),
	REQUEST_TO_USER(2),
	CONFIRM_REQUEST_USER(3),
	DELETE_FRIEND(4);
	
	private int type;

	private UserRequestType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
}
