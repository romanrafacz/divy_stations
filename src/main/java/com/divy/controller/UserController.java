package com.divy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.divy.domain.validator.UserCreateFormValidator;
import com.divy.service.UserService;

public class UserController {

	private final UserService userService;
	private final UserCreateFormValidator userCreateFormValidator;
	
	@Autowired
	public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator){
		this.userService = userService;
		this.userCreateFormValidator = userCreateFormValidator;
	}
	
	@InitBinder("form")
	public void initBinder(WebDataBinder binder){
		binder.addValidators(userCreateFormValidator);
	}
	
	
}
