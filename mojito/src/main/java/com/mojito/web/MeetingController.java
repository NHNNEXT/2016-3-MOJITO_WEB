package com.mojito.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
	
	@GetMapping("/create")
		public String create(){
		return "create_article_page";
	}
	
	@GetMapping("/my")
		public String my(){
		return "my_meeting_page";
	}
}
