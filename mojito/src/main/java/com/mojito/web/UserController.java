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
	
	@Autowired // interface로 선언되어 있는 UserRepository를 spring-boot가 구현체를 생성해서 사용할 수 있게 해주는 애너테이션
	private UserRepository userRepository; 
	
    @GetMapping("/login")
    public String login() {
        return "login_page";
    }

    @PostMapping("/login")
    public String userLogin(String userEmail, String userPassword, HttpSession session) {
    	User user = userRepository.findByUserEmail(userEmail);
    	if (user==null) { // 해당 email 계정이 존재하지 않는 경우
    		System.out.println("Email Not Existing!");
    		return "/login_page";
    	}
    	
    	if (!user.matchPassword(userPassword)){ // 비밀번호가 잘못된 경우
    		System.out.println("Invalid Password!");
    		return "/login_page";
    	}
    	
    	System.out.println("Login Success!");
    	session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
    	
    	System.out.println("userEmail : " + userEmail + "\n" + "userPassword : " + userPassword);
    	return "redirect:/";
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
    		throw new IllegalStateException("You can't change other user's information.");
    	}
    	
    	return "/update_userinfo_page";
    }
    
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, User updatedUser, String userPasswordConfirm, HttpSession session) {
    	if (!HttpSessionUtils.isLoginUser(session)) {
    		return "redirect:/login";
    	}
    	
    	User dbUser = userRepository.findOne(id);
    	
    	User sessionedUser = HttpSessionUtils.getUserFromSession(session);
    	if (!sessionedUser.matchId(id)) {
    		throw new IllegalStateException("You can't change other user's information.");
    	}
    	
    	System.out.println("user : " + updatedUser);
    	dbUser.update(updatedUser, userPasswordConfirm);
    	userRepository.save(dbUser);
    	
    	return "redirect:/logout";
    	
    }
}























