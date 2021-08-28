package com.cognizant.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.user.entity.User;
import com.cognizant.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Optional<User> getUserById(String id) {
		return userRepository.findById(id);
	}
	
}
