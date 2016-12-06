package com.mojito.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/my")
	public String my(Model model){
		model.addAttribute("meetings", meetingRepository.findAll());
		return "my_meeting_page";
	}
	
	@PostMapping("/{id}/create_article")
	public String createArticle(@PathVariable Long id, Meeting meeting, HttpSession session, String day, String time, String bomb_time){
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionUser.matchId(id)) {
			throw new IllegalStateException("session user id not match with new article request user id!");
		}
		
		meeting.setMeetingDate(day, time);
		meeting.setExpireDate(day, bomb_time);
		meeting.setCreateDate(LocalDateTime.now());
		meeting.setWriter(sessionUser);
		
		System.out.println(meeting);
		meetingRepository.save(meeting);
		return "redirect:/";
	}
}
