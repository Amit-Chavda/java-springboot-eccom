package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping("/get/{userId}")
	public @ResponseBody Cart getCartByUserId(@PathVariable Integer userId) {
		return cartService.getByUserId(userId);
	}
	
	@PutMapping("/update/{userId}")
	public @ResponseBody Cart updateCartByUserId(@PathVariable Integer userId,@RequestBody Cart cart) {
		return cartService.updateCart(cart);
	}
}
