package com.mojito.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique=true, length=40)
	private String userEmail;
	
	@Column(nullable = false)
	private String userPassword;
	private String name;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", name=" + name + "]";
	}

	public boolean matchPassword(User newUser) {
		if (newUser.userPassword == null) {
			return false;
		}
		
		return newUser.userPassword.equals(userPassword);
	}

	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}
		return newId.equals(id);
	}

	public void update(User updatedUser, String userPasswordConfirm) {
		this.name = updatedUser.name;
		if (updatedUser.userPassword.equals(userPasswordConfirm)) {
			this.userPassword = updatedUser.userPassword;
		}
	}
}
