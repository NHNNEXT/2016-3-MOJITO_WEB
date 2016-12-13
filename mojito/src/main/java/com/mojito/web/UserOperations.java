package com.mojito.web;

import com.mojito.domain.User;

public interface UserOperations {
	void process(User sessionedUser, User requestUser);
}
