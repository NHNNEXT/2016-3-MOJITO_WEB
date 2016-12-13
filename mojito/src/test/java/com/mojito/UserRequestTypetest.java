package com.mojito;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mojito.web.UserRequestType;

public class UserRequestTypetest {

	@Test
	public void test() {
		System.out.println(UserRequestType.CONFIRM_REQUEST_USER == UserRequestType.CONFIRM_REQUEST_USER);
	}

	@Test
	public void test2() {
		System.out.println(UserRequestType.DELETE_FRIEND);
	}
}
