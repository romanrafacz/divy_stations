package com.divy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.divy.service.UserService;

@Controller
public class UsersController {
	
	private final UserService userService;
	
	@Autowired
	public UsersController(UserService userService){
		this.userService = userService;
	}

	@RequestMapping("/users")
	public ModelAndView getUserPage(){
		return new ModelAndView("users", "users", userService.getAllUsers() );
	}
}
