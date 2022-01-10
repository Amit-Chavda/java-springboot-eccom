package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserAddress;
import com.example.demo.repository.UserAddressRepo;

@Service
public class UserAddressService {
	
	@Autowired
	private UserAddressRepo addressRepo;
	
	
	public UserAddress saveAddress(UserAddress address){
		return addressRepo.save(address);
	}
	
	public List<UserAddress> getAddressByUserId(Integer userId){
		return addressRepo.findUserAddessByUserId(userId);
	}
	
	public UserAddress getAddressById(Integer id){
		return addressRepo.findUserAddessById(id);
	}
}
