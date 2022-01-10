package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepo;

@Service
public class CartService {
	
	@Autowired
	private CartRepo cartRepo;
	
	public boolean existsCartByUserId(Integer userId) {
		return cartRepo.existsByUserId(userId);
	}
	
	public Cart updateCart(Cart cart) {
		return cartRepo.save(cart);
	}
	
	public Cart createCart(Cart cart) {
		return cartRepo.save(cart);
	}
	
	public Cart getByUserId(Integer userId) {
		return cartRepo.getByUserId(userId);
	}
}
