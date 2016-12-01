package com.mojito.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void update_비밀번호_일치할_때() { // 한글로도 이름 지을 수 있다!!!
		User originUser = new User("1@gmail.com", "password", "name");
		User newUser = new User("1@gmail.com", "newpassword", "update name");
		originUser.update(newUser, "newpassword");
		assertEquals("update name", originUser.getUserName());
		assertEquals("newpassword", originUser.getUserPassword());
	}
	
	@Test
	public void update_비밀번호_일치하지_않을_때() {
	}

}
