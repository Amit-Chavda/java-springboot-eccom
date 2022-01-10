package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserAddress;
import com.example.demo.service.UserAddressService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/UserAddress")
public class UserAddressController {
	
	@Autowired
	private UserAddressService addressService;
	
	@PostMapping("Save")
	public @ResponseBody UserAddress getAddressByUserId(@RequestBody UserAddress address){
		return addressService.saveAddress(address);
	}
	
	
	@GetMapping("GetByUserId/{userId}")
	public @ResponseBody List<UserAddress> getAddressByUserId(@PathVariable Integer userId){
		return addressService.getAddressByUserId(userId);
	}
	
	
	@GetMapping("GetById/{id}")
	public @ResponseBody UserAddress getAddressById(@PathVariable Integer id){
		return addressService.getAddressById(id);
	}
	
}
