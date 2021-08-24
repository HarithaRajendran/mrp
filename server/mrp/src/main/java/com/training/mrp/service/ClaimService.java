package com.training.mrp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.mrp.exception.ClaimAlreadyExistException;
import com.training.mrp.model.Claim;
import com.training.mrp.model.Dependent;
import com.training.mrp.model.Member;
import com.training.mrp.repository.ClaimRepositoryI;
import com.training.mrp.repository.DependentRepositoryI;
import com.training.mrp.repository.MemberRepositoryI;

@Service
public class ClaimService implements ClaimServiceI{
	
	@Autowired
	private ClaimRepositoryI claimRepo;
	
	@Autowired
	private MemberRepositoryI memberRepo;
	
	@Autowired
	private DependentRepositoryI dependentRepo;

	@Override
	public boolean save(Claim claim) {
		
		if(claim.getId() == null) {
			claim.setId(getLargestId()+1);
		}
		
		claimRepo.save(claim);		
		return true;
	}
	
	@Override
	public ResponseEntity<?> getMemberById(Integer id) {
		List<Integer> ids = new ArrayList<>();
		
		List<Member> members = memberRepo.findAll();
		
		for(Member member: members) {
			ids.add(member.getId());
			for(Dependent dependent: member.getDependents()) {
				ids.add(dependent.getId());
			}
		}
		
		List<Integer> filteredId = 
				ids.stream().filter(identity -> identity.equals(id)).collect(Collectors.toList());
		
		if(filteredId.size() > 0) {
			throw new ClaimAlreadyExistException("Claim with the id "+id+" already exist");
		}
		
		Optional<Member> member = memberRepo.findById(id);
				
		if(member.isEmpty()) {
			Dependent dependent = dependentRepo.findById(id).get();
			return ResponseEntity.ok(dependent) ;
		} else {
			return ResponseEntity.ok(member);
		}	
	}
	
	private Integer getLargestId() {
		List<Integer> ids = new ArrayList<Integer>();

			List<Claim> claims = claimRepo.findAll();
			for(Claim claim: claims) {
				ids.add(claim.getId());
			}
		
		return Collections.max(ids);
	}

}
