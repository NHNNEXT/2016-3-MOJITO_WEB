package com.mojito.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> friendUsers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> requestsToUser;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> requestsToMe;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> metUsers;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    private Set<Meeting> myMeetings;
    
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
	
//	public Set<Meeting> getMyMeetings() {
//		return myMeetings;
//	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void update(User updatedUser, String userPasswordConfirm) {
		this.userName = updatedUser.userName;
		if (updatedUser.userPassword.equals(userPasswordConfirm)) {
			this.userPassword = updatedUser.userPassword;
		}
	}

	public Long getId() {
		return id;
	}
}