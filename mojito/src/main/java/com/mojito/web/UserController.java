package com.mojito.web;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    	String emailExist = "이미 존재하는 email입니다.";

    	if(userRepository.findByUserEmail(newUser.getUserEmail()) == null) {
    		userRepository.save(newUser);
    		return "redirect:/";
    	} else {
    		System.out.println(emailExist);
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
    
    @GetMapping("cancelRequest/{id}")
	public String deleteRequestToUser(@PathVariable Long id, Model model, HttpSession session) {
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println("pathvariable id : " + id);
		User toDeleteRequest = userRepository.findOne(id);
		Set testset = sessionedUser.getRequestsToUser();
		System.out.println("before testset: " + testset);
		testset.remove(toDeleteRequest);
		System.out.println("after testset: " + testset);
//		Iterator<User> it = sessionedUser.getRequestsToUser().iterator();
//
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		userRepository.save(sessionedUser);
    	
    	return "redirect:/friends";
	}
    
    
}