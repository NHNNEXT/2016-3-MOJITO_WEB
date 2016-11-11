package com.mojito.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mojito.domain.Meeting;
import com.mojito.domain.MeetingRepository;
import com.mojito.domain.User;

@Controller
@RequestMapping("/meeting")
public class MeetingController {
	
	@Autowired
	private MeetingRepository meetingRepository;
	
	@GetMapping("/create")
	public String create(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/login_page";
		}
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
	
	@PostMapping("/{id}/create_article")
	public String createArticle(@PathVariable Long id, Meeting meeting, HttpSession session, String day, String time){
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println("date : " + day);
		System.out.println("time : " + time);
		String toConvert = day + " " + time;
		System.out.println(toConvert);
		System.out.println(LocalDateTimeConverter.converter(toConvert));
		meeting.setDate(LocalDateTimeConverter.converter(toConvert));
		meeting.setWriter(sessionUser);
		meeting.setCapacity(5);
		meeting.setCreateDate(LocalDateTime.now());
		System.out.println("meeting: "+meeting);
		meetingRepository.save(meeting);
		return "redirect:/meeting/create";
	}
}
