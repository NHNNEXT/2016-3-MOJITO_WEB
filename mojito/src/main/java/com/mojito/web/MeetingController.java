package com.mojito.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mojito.domain.Meeting;
import com.mojito.domain.MeetingRepository;
import com.mojito.domain.User;

import com.mojito.web.HttpSessionUtils;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
<<<<<<< HEAD
=======
	
	@Autowired
	private MeetingRepository meetingRepository;
	
>>>>>>> 6bc94b7781212487170f3b579835f4b81c3d2275
	@GetMapping("/create")
	public String create() {
		return "create_article_page";
	}
	
	@GetMapping("/")
	public String list(){
		return "my_meeting_page";
	}
	
	@GetMapping("/my")
	public String my(){
		return "my_meeting_page";
	}
	
	@PostMapping("/create_article")
	public String createArticle(Meeting meeting){
		//User sessionUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println("meeting: "+meeting);
		meetingRepository.save(meeting);
		return "redirect:/my_meeting_page";
	}
	
}
