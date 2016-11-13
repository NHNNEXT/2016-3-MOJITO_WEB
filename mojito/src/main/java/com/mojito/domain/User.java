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

	@Column(nullable = false)
    @JsonProperty
    private String userName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty
    private Set<User> friendUsers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty
    private Set<User> requestsToUser;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty
    private Set<User> requestsToMe;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Set<User> getFriendUsers() {
		return friendUsers;
	}

    public Set<User> getRequestsToUser() {
        return requestsToUser;
    }
	
	public Set<User> getRequestsToMe() {
		return requestsToMe;
	}
	
	public Set<User> getMetUsers() {
		return metUsers;
	}
	
    @Override
	public String toString() {
		return "User [id=" + id + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userName=" + userName + "]";
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
		this.userName = updatedUser.userName;
		if (updatedUser.userPassword.equals(userPasswordConfirm)) {
			this.userPassword = updatedUser.userPassword;
		}
	}
}