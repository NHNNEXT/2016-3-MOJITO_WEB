package com.mojito.web;

import com.mojito.domain.Meeting;
import com.mojito.domain.MeetingRepository;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Jbee on 2016. 12. 21..
 */
@RestController
@RequestMapping("/api")
public class ApiMainController {
    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("")
    public List<Meeting> mainPage(HttpSession session){
        if (session.getAttribute(HttpSessionUtils.USER_SESSION_KEY)==null) {
            return null;
        }
        List<Meeting> meetingList = (List<Meeting>) meetingRepository.findAll();
        return meetingList;
    }
}
