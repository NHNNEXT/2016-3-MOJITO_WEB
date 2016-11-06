package com.mojito.mobile;

import com.mojito.domain.User;
import com.mojito.domain.UserRepository;
import com.mojito.web.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public User login(String userEmail, String password, HttpSession session) {
        if(!HttpSessionUtils.isLoginUser(session)) {
            return null;
        }
        User user = userRepository.findByUserEmail(userEmail);
        if (user == null) {
            return null;
        }
        if (!user.matchPassword(password)) {
            return null;
        }
        session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
        return user;
    }

    @GetMapping("/showRequestToUser")
    public Set<User> show(HttpSession session) {
        System.out.println("show method execute!");

        if(!HttpSessionUtils.isLoginUser(session)) {
            System.out.println("Not login!");
            return null;
        }
        User user = HttpSessionUtils.getUserFromSession(session);
        System.out.println(user);
        if(user == null) return null;
        System.out.println(user.getRequestsToUser());
        return user.getRequestsToUser();
    }
}