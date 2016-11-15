package com.mojito.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mojito.domain.MeetingRepository;

@Controller
public class MainPageController {
	@Autowired
	private MeetingRepository meetingRepository;
	
	@GetMapping("/")
	public String mainPage(HttpSession session, Model model){
		
		if (session.getAttribute(HttpSessionUtils.USER_SESSION_KEY)==null) {
			return "login_page";
		}
		model.addAttribute("meetings", meetingRepository.findAll()); 
		
		return "main_page";
	}
}


