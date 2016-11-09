package com.mojito.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mojito.domain.LoginResult;
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
}
