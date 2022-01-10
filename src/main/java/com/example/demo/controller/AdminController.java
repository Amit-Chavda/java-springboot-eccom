package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;

@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Admin> login(@RequestBody Admin admin) throws Exception {
		String email=admin.getEmailId();
		String password=admin.getPassword();
		
		
		Admin admin1 = null;
		if(email!=null && !"".equals(email) && password!=null && !"".equals(password)) {
			admin1=adminService.findByEmailIdAndPassword(email,password).get();
		}
		if(admin1==null) {
			throw new Exception("Invalid credentials!");
		}
		return new ResponseEntity<>(admin1, HttpStatus.OK);
	}
	
	
}
