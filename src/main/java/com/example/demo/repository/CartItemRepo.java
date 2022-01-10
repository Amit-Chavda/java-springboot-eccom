package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
	List<CartItem> findByCartId(Integer cartId);
	Integer removeCartItemById(Integer id);
}
