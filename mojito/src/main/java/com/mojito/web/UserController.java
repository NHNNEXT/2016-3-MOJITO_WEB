package com.mojito.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mojito.domain.User;
import com.mojito.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @GetMapping("/signup")
    public String signupForm() {
    	return "signup_page";
    }

    @PostMapping("/signup")
    public String signup(User newUser) {
    	System.out.println("newUser : " + newUser);

    	if(userRepository.findByUserEmail(newUser.getUserEmail()) == null) {
    		userRepository.save(newUser);
    		return "redirect:/";
    	} else {
    		System.out.println("이미 존재하는 email입니다.");
    	}

    	return "/signup_page";
    }
    @GetMapping("/logout")
    public String userLogout(HttpSession session) {
    	session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
    	return "redirect:/";
    }

    @GetMapping("/user")
    public String signup() {
        return "signup_page";
    }

    @GetMapping("/find")
    public String findPassword() {
        return "find_password_page";
    }

    @GetMapping("/friends")
    public String friendsList(){
    	return "friend_list_page";
    }

    @GetMapping("/main_page")
    public String mainPage() {
    	return "/main_page";
    }

    @GetMapping("/{id}/updateForm")
    public String updateForm(@PathVariable Long id, HttpSession session) {
    	if (!HttpSessionUtils.isLoginUser(session)) {
    		return "redirect:/login";
    	}
    	User user = userRepository.findOne(id);

    	User sessionedUser = HttpSessionUtils.getUserFromSession(session);
    	if (!sessionedUser.matchId(id)) {
    		System.out.println("You can't change other user's information.");
    		return "redirect:/logout";
    	}

    	return "/update_userinfo_page";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, User updatedUser, String userPasswordConfirm, HttpSession session) {
		System.out.println("여기 들어오긴 하나?");
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login";
		}

		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionedUser.matchId(id)) {
			throw new IllegalStateException("You can't change other user's information.");
		}

		System.out.println("edit user : " + updatedUser);
		System.out.println("new password confirm : " + userPasswordConfirm);

		User dbUser = userRepository.findOne(id);
		System.out.println("user : " + updatedUser);
		dbUser.update(updatedUser, userPasswordConfirm);
		userRepository.save(dbUser);

		return "redirect:/logout";
	}
}























