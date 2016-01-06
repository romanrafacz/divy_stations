package com.divy.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.servlet.ModelAndView;

import com.divy.domain.UserCreateForm;
import com.divy.domain.validator.UserCreateFormValidator;
import com.divy.service.UserService;

public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
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
	
	@RequestMapping("/user/{id}")
	public ModelAndView getUserPage(@PathVariable Long id){
		LOGGER.debug("Getting user page for user={}", id);
		return new ModelAndView("user", "user", userService.getUserById(id)
				.orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
	}
	
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public ModelAndView getUserCreatePage(){
		LOGGER.debug("Getting user create form");
		return new ModelAndView("user_create", "form", new UserCreateForm());
	}
	
	@RequestMapping(value = "/user/create", method=RequestMethod.POST)
	public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult){
		LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
		if (bindingResult.hasErrors()) {
			//failed validation
			return "user_create";
		}
		try {
			userService.create(form);
		}catch (DataIntegrityViolationException e){
			bindingResult.reject("email.exists", "Email already exists");
			return "user_create";
		}
		//redirect
		return "redirect:/users";
	}
	
}
