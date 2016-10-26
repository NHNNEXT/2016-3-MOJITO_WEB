package com.mojito.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mojito.domain.User;
import com.mojito.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired // interface로 선언되어 있는 UserRepository를 spring-boot가 구현체를 생성해서 사용할 수 있게 해주는 애너테이션
	private UserRepository userRepository; 
	
    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @PostMapping("/login")
    public String userLogin(String userEmail, String userPassword, HttpSession session) {
    	User user = userRepository.findByUserEmail(userEmail);
    	if (user==null) {
    		System.out.println("Login Fail!");
    		return "redirect:/login";
    	}
    	
    	if (!user.matchPassword(userPassword)){
    		System.out.println("Login Fail!");
    		return "redirect:/login";
    	}
    	
    	System.out.println("Login Success!");
    	session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
    	
    	System.out.println("userEmail : " + userEmail + "\n" + "userPassword : " + userPassword);
    	return "redirect:/main_page";
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

    @GetMapping("/update") 
    public String updateUserInfo() {
        return "update_userinfo_page";
    }
    
    @GetMapping("/friends")
    public String friendsList(){
    	return "friend_list_page";
    }
    
    @GetMapping("/main_page")
    public String mainPage() {
    	return "/main_page";
    }
    
    @GetMapping("/updateForm")
    public String updateForm() {
    	return "/update_userinfo_page";
    }
}
