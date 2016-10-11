package com.mojito.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@GetMapping("/login")
	public String login(){
		return "login_page";
	}
	
	@GetMapping("/user")
	public String signup(){
		return "signup_page";
	}
	
	@GetMapping("/find")
	public String findPassword(){
		return "find_password_page";
	}
	
	@GetMapping("/new_meeting")
	public String newMeeting() {
		return "create_article_page";
	}
	
	@GetMapping("/list")
	public String userList() {
		return "friend_list_page";
	}
}
