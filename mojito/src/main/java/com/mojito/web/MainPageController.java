package com.mojito.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	@GetMapping("/")
	public String mainPage(HttpSession session){
		if (session.getAttribute(HttpSessionUtils.USER_SESSION_KEY)==null) {
			return "login_page";
		}
		
		return "main_page";
	}
}


