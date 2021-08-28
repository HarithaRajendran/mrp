package com.cognizant.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.user.entity.User;
import com.cognizant.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		
		Optional<User> user = userService.getUserById(id);
		
		if(user.isEmpty()) {
			return new ResponseEntity<>("User Not found", HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
