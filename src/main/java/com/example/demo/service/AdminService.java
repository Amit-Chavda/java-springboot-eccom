package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepo;

@Service
public class AdminService {
	@Autowired
	AdminRepo adminRepo;
	
	public Optional<Admin> findByEmailIdAndPassword(String email, String password) {
		
		return adminRepo.findAdminByEmailIdAndPassword(email,password);
	}
}
