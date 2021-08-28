package com.cognizant.claim.service;

//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.claim.entity.Claim;
import com.cognizant.claim.repository.ClaimRepository;

@Service
public class ClaimService {
	
	@Autowired
	private ClaimRepository claimRepository;

	public Claim save(Claim claimData) {
		claimData.setId((long) (Math.random() * 100000 + 9876543210l));
		return claimRepository.save(claimData);
	}

}
