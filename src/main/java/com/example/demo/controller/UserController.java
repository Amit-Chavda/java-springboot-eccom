package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception {
		String email = user.getEmailId();

		if (email != null && !"".equals(email)) {
			if(userService.existsWithEmail(email)) {
				throw new Exception("User with " + email + " already exists!");
			}
		}
		user.setActive(true);
		User user1 = userService.addUser(user);
		return new ResponseEntity<>(user1, HttpStatus.CREATED);
	}

	@PostMapping("login")
	public @ResponseBody User login(@RequestBody User user) throws Exception {

		String tempEmail = user.getEmailId();
		String tempPassword = user.getPassword();
		User userObj = null;
		if (tempEmail != null && tempPassword != null && !"".equals(tempEmail) && !"".equals(tempPassword)) {

			try {
				userObj = userService.findUserByEmailId(tempEmail).get();
			} catch (NoSuchElementException notFoundErr) {
				throw new Exception("User does not exist!");
			}
			
			
			try {
				userObj = userService.findUserByEmailIdAndPassword(tempEmail, tempPassword).get();
			} catch (NoSuchElementException notFoundErr) {
				throw new Exception("Email or password is incorrect!");
			}
		}
		if (userObj == null) {
			throw new Exception("Email or password is incorrect!");
		}
		return userObj;
	}

	@GetMapping("/find/{emailId}")
	public ResponseEntity<User> findUser(@PathVariable("emailId") String emailId) {
		Optional<User> user = userService.findUserByEmailId(emailId);
		return new ResponseEntity<>(user.get(), HttpStatus.OK);

	}
	
	@GetMapping("/getall")
	public @ResponseBody List<User> getAllUser() {
		return userService.getAllUsers();
	}
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUserById(@PathVariable("userId") Integer userId){
		userService.deleteUserById(userId).get();
	}
	
	@PutMapping("update")
	public  @ResponseBody User updateUserById(@RequestBody User user) throws Exception {
		return userService.updateUserById(user);
	}
	
	
	@GetMapping("/getall/{emailId}")
	public @ResponseBody List<User> getAllByEmailId(@PathVariable("emailId") String emailId) {
		return userService.findAllByEmailId(emailId);
	}
	
	
}

