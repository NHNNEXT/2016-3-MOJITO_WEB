package com.mojito.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mojito.domain.JoinMeetingResult;
import com.mojito.domain.Meeting;
import com.mojito.domain.MeetingRepository;
import com.mojito.domain.UserRepository;

@RestController
@RequestMapping("/api/meeting")
public class ApiMeetingController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MeetingRepository meetingRepository;
	
	@GetMapping("/join/{meeting_id}")
	public int joinMeeting(@PathVariable Long meeting_id, HttpSession session) {
		Meeting meeting = meetingRepository.findOne(meeting_id);
		meeting.join(HttpSessionUtils.getUserFromSession(session));
		meetingRepository.save(meeting);
		
		return meeting.getParticipantSize();
	}
	
	@GetMapping("/cancel/{meeting_id}")
	public int cancelMeeting(@PathVariable Long meeting_id, HttpSession session) {
		Meeting meeting = meetingRepository.findOne(meeting_id);
		meeting.cancel(HttpSessionUtils.getUserFromSession(session));
		meetingRepository.save(meeting);
		
		return meeting.getParticipantSize();
	}
}
