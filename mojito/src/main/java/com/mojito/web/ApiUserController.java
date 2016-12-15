package com.mojito.web;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mojito.domain.LoginResult;
import com.mojito.domain.RequestedListResult;
import com.mojito.domain.User;
import com.mojito.domain.UserRepository;


@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login")
	public LoginResult login(@RequestBody User loginUser, HttpSession session) {
		User user = userRepository.findByUserEmail(loginUser.getUserEmail());
		System.out.println("user : " + user);
		System.out.println("input email : " + loginUser.getUserEmail());
		
		if (loginUser.getUserEmail() == "") {
			return LoginResult.emptyInput("이메일을 입력해 주세요.");
		}
		
		if (user == null) {
			return LoginResult.emailNotFound("등록되지 않은 이메일입니다.");
		}
		
		if (!user.matchPassword(loginUser)) {
			return LoginResult.invalidPassword("잘못된 비밀번호입니다.");
		}

		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return LoginResult.ok();
	}
    
    @GetMapping("/show/{listId}")
    public RequestedListResult showList(@PathVariable Long listId, HttpSession session) {
    	if (!HttpSessionUtils.isLoginUser(session)) {
    		throw new IllegalStateException("Not Login");
    	}
    	
    	User user = HttpSessionUtils.getUserFromSession(session);
    	user = userRepository.findOne(user.getId()); // for lazy initialization
    	
    	if (listId == 1) {
    		System.out.println(user.getRequestsToUser());
    		return RequestedListResult.getRequestsToUser(user);
    	}
    	if (listId == 2) {
    		System.out.println(user.getRequestsToMe());
    		return RequestedListResult.getRequestsToMe(user);
    	}
    	if (listId == 3) {
    		System.out.println(user.getFriendUsers());
    		return RequestedListResult.getFriendUsers(user);
    	}
    	if (listId == 4) {
    		System.out.println(user.getMetUsers());
    		return RequestedListResult.getMetUsers(user);
    	}
    	
    	throw new IllegalStateException("Invalid List Request");
    }
    
    @PostMapping("/search")
    public RequestedListResult showSearchResult(@RequestParam String userName) {
    	System.out.println("넘어온 값 : " + userName);
    	Set<User> result = userRepository.findByUserName(userName);
    	return RequestedListResult.getSearchResult(result);
    }
}
