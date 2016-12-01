package com.mojito.web;

import com.mojito.domain.User;

public class DeleteFriend implements UserOperations {

	@Override
	public void process(User sessionedUser, User requestUser) {
		sessionedUser.getFriendUsers().remove(requestUser);		
	}

}
