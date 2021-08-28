package com.cognizant.claim.service;

//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.claim.entity.Claim;
import com.cognizant.claim.entity.ClaimCheckDetail;
import com.cognizant.claim.entity.MemberDetail;
import com.cognizant.claim.repository.ClaimRepository;

@Service
public class ClaimService {
	
	@Autowired
	private ClaimRepository claimRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public Claim save(Claim claimData) {
		claimData.setId((long) (Math.random() * 100000 + 9876543210l));
		return claimRepository.save(claimData);
	}
	
	public MemberDetail checkMemberClaim(String id) {		
		MemberDetail memberDetail = restTemplate.getForObject("http://localhost:8082/api/user/check/claim/"+id, MemberDetail.class);
		return memberDetail;
	}

}
