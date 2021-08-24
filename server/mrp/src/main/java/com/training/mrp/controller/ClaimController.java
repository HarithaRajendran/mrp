package com.training.mrp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClaimController {
	
	@PostMapping("/api/submitClaim")
	public String createClaim() {
		return null;
	}	
	
}
