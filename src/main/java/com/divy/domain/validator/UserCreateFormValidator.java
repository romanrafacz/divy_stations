package com.divy.domain.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.divy.domain.UserCreateForm;
import com.divy.service.UserService;

public class UserCreateFormValidator implements Validator {

		private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
		private final UserService userService;
		
		@Autowired
		public UserCreateFormValidator(UserService userService){
			this.userService = userService;
		}
		
		@Override
		public boolean supports(Class<?> clazz){
			return clazz.equals(UserCreateForm.class);
		}
		
		@Override
		public void validate(Object target, Errors errors){
			LOGGER.debug("Validation {}", target);
			UserCreateForm form = (UserCreateForm) target;
			validatePasswords(errors, form);
			validateEmail(errors, form);
		}
		
		private void validatePasswords(Errors errors, UserCreateForm form){
			if (!form.getPassword().equals(form.getPassword())){
				errors.reject("password.no_match", "Passwords do not match");
			}
		}
		
		private void validateEmail(Errors errors, UserCreateForm form){
			if (UserService.getUserByEmail(form.getEmail()).isPresent()){
				errors.reject("email.exists", "User with this email already exists");
			}
		}
}
