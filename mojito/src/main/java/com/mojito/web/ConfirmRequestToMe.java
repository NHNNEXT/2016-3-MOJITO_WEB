package com.mojito.web;

import com.mojito.domain.User;

public class ConfirmRequestToMe implements UserOperations {

	@Override
	public void process(User sessionedUser, User requestUser) {
		sessionedUser.getRequestsToMe().remove(requestUser);
    	sessionedUser.getFriendUsers().add(requestUser);
	}

}
