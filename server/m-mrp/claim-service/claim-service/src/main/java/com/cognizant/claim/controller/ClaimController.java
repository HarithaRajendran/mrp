package com.cognizant.claim.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.claim.entity.Claim;
import com.cognizant.claim.entity.ClaimCheckDetail;
import com.cognizant.claim.entity.MemberDetail;
import com.cognizant.claim.service.ClaimService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/claim")
@Slf4j
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	@PostMapping("submit")
	public ResponseEntity<?> submitClaim(@RequestBody Claim claimData){
		
		Claim claim = claimService.save(claimData);
		
		if(Objects.isNull(claim)) {
			return new ResponseEntity<>("Claim not saved.", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			log.info("Claim created successfully...");
			return new ResponseEntity<>("Claim successfully created.", HttpStatus.OK);
		}
	}
	
	@GetMapping("/member/check/{id}")
	public ResponseEntity<?> verifyClaimMember(@PathVariable String id){
		MemberDetail memberDetail = claimService.checkMemberClaim(id);
		
		return new ResponseEntity<>(memberDetail, HttpStatus.OK);
	}
	
}
