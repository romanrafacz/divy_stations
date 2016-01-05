package com.divy.service;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.divy.repository.UserRepository;

import com.divy.domain.User;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Optional<User> getUserById(Long id){
		return Optional.ofNullable(userRepository.findOne(id));
	}
	
	@Override
	public Optional<User> getUserByEmail(String email){
		return userRepository.findOneByEmail(email);
	}
	
	@Override
	public Collection<User> getAllUsers(){
		return userRepository.findAll(new Sort("email"));
	}
	
	@Override
	public User create(UserCreateForm form){
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setRole(form.getRole());
		return userRepository.save(user);
	}
	
}

