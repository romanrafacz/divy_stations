package com.divy.service;

import java.util.Collection;
import java.util.Optional;

import com.divy.domain.User;
import com.divy.domain.UserCreateForm;


public interface UserService {
	
	Optional<User> getUserById(Long id);
	
	//Changed to static, UserCreateFormValidate.validateEmail - needed this change
	Optional<User> getUserByEmail(String email);
	Collection<User> getAllUsers();
	
	User create(UserCreateForm form);
	
}
