package com.cognizant.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.user.entity.Dependent;
import com.cognizant.user.entity.User;
import com.cognizant.user.entity.UserDetail;
import com.cognizant.user.exception.UserNotFoundException;
import com.cognizant.user.repository.DependentDao;
import com.cognizant.user.repository.DependentRepository;
import com.cognizant.user.repository.UserDao;
import com.cognizant.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private DependentRepository dependentRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DependentDao dependentDao;

	public Optional<User> getUserById(String id) {
		return userRepository.findById(id);
	}
	
	public Optional<Dependent> getDependentById(String id) {
		return dependentRepository.findById(id);
	}

	public UserDetail save(UserDetail userDetail) {
				
		List<Dependent> dependents = new ArrayList<>();
		
		User user = userRepository.save(userDetail.getUser());
		
		for(Dependent dependent: userDetail.getDependents()) {
			if(dependent.getId() == null) {
				dependent.setId("D-"+Math.round(Math.random()*1000));
				dependent.setUserId(user.getId());
			}
			dependents.add(dependentRepository.save(dependent));
		}
		
		userDetail.setUser(user);
		userDetail.setDependents(dependents);
				
		return userDetail;
	}
	
	public UserDetail verifyUserDetail(String username, String password) {
		User user = userDao.validateUserDetail(username, password);
		
		if(Objects.isNull(user)) {
			throw new UserNotFoundException("Invalid Credentials");
		}
		
		UserDetail userDetail = new UserDetail();
		List<Dependent> dependents = new ArrayList<>();
		
		dependents = dependentRepository.findByUserId(user.getId());
		
		userDetail.setUser(user);
		if(dependents.size() > 0) {
			userDetail.setDependents(dependents);
		}
		
		return userDetail;
	}

	public User register(User user) {
		user.setId("R-"+Math.round(Math.random()*1000));
		return userRepository.save(user);
	}
	
	public User checkUserForClaimSubmit(String memberId, String userId) {
		return userDao.checkUserForClaimSubmit(memberId, userId);
	}
	
	public Dependent checkDependentForClaimSubmit(String memberId, String userId) {
		return dependentDao.getDependentDetail(memberId, userId);
	}
}
