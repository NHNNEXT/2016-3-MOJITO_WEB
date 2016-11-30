package com.mojito.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mojito.domain.Meeting;
import com.mojito.domain.MeetingRepository;
import com.mojito.domain.User;
import com.mojito.domain.UserRepository;

@Controller
public class MainPageController {
	@Autowired
	private MeetingRepository meetingRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/")
	public String mainPage(HttpSession session, Model model){
		User sessionedUser = (User)session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
		if (sessionedUser==null) {
			return "login_page";
		}
		sessionedUser = userRepository.findOne(sessionedUser.getId()); // lazy initialization
		
//		List<Meeting> meetingList = new ArrayList<>();
//		for (User user : sessionedUser.getFriendUsers()) {
//			meetingList.add(meetingRepository.findByWriter(user));
//		}
//	
//		meetingList.add(meetingRepository.findByWriter(sessionedUser));
//		
//		model.addAttribute("meetings", meetingList); 
		
		model.addAttribute("meetings", meetingRepository.findAll());
		return "main_page";
	}
}


