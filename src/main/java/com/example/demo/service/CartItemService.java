package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepo;

@Service
@Transactional
public class CartItemService {

	@Autowired
	private CartItemRepo itemRepo;
	
	public CartItem addCartItem(CartItem item) {
		return itemRepo.save(item);
	}

	public CartItem getCartItemById(Integer id) {
		System.out.println("cart item isd :  "+id);
		return itemRepo.findById(id).get();
	}
	
	public List<CartItem> getByCartId(Integer cartId) {
		return itemRepo.findByCartId(cartId);
	}

	public Integer removeItemById(Integer itemId) {
		return itemRepo.removeCartItemById(itemId);
	}	
	
}
