package com.mojito.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
	@Id
	@GeneratedValue
    @JsonProperty
	private Long id;

	@Column(nullable = false, unique=true, length=40)
    @JsonProperty
	private String userEmail;

	@Column(nullable = false)
    @JsonProperty
	private String userPassword;

    @JsonProperty
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonProperty
    private Set<User> friendUsers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonProperty
    private Set<User> requestsToUser;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonProperty
    private Set<User> requestsToMe;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonProperty
    private Set<User> metUsers;

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

	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}
		return newPassword.equals(userPassword);
	}

	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}
		return newId.equals(id);
	}

	public void update(User updatedUser, String userPasswordConfirm) {
		this.name = updatedUser.name;
		if (updatedUser.matchPassword(userPasswordConfirm)) {
			this.userPassword = updatedUser.userPassword;
		}
	}

	
}
