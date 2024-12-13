package com.tez.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tez.main.entities.User;
import com.tez.main.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	@Autowired
	private UserService userService;
	

	@GetMapping("/registerPage")
	public String openRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitRegister(@ModelAttribute("user") User user, Model model) {
		boolean flag=userService.registerUser(user);
		if(flag) {
			model.addAttribute("successMsg","User Registered Successfully");
		}
		else {
			model.addAttribute("errorMsg","User Registration failed");
		}
		
		return "register";
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@PostMapping("/loginForm")
	public String submitLogin(@ModelAttribute("user") User user, Model model) {
		User validUser=userService.loginUser(user.getEmail(), user.getPassword());
		if(validUser!=null) {
			model.addAttribute("name", validUser.getName());
			return "profile";
		}
		else {
			model.addAttribute("errorMsg", "Email id and Password didn't match");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/loginPage";
	}
	
}
