package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private final UserRepo userRepo;
	
	
	public UserService(UserRepo userRepo) {
		this.userRepo=userRepo;
	}
	
	public User addUser(User user) {
		return userRepo.save(user);
		
	}

	public Optional<User> findUserByEmailId(String emailId) {
		
		Optional<User> user=userRepo.findUserByEmailId(emailId);
		return user;
	}

	public boolean existsWithEmail(String emailId) {
		return userRepo.existsByEmailId(emailId);
	}
	
	public Optional<User> findUserByEmailIdAndPassword(String tempEmail, String tempPassword) {
		return userRepo.findUserByEmailIdAndPassword(tempEmail, tempPassword);
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public Optional<User> deleteUserById(Integer userId){
		return userRepo.removeUserById(userId);
	}
	
	public List<User> findAllByEmailId(String emailId){
		return userRepo.findAllByEmailId(emailId);
	} 
	
	
	
	public User updateUserById(User user) throws Exception {
		Optional<User> userData=userRepo.findUserById(user.getId());
		if(userData.isPresent()) {
			return userRepo.save(user);
		}else {
			throw new Exception("User does not exist!");
		}
	}
	
	public User getUserById(Integer id) throws Exception {
		Optional<User> userData=userRepo.findUserById(id);
		if(userData.isPresent()) {
			return userRepo.findUserById(id).get();
		}else {
			throw new Exception("User does not exist!");
		}
	}
}
