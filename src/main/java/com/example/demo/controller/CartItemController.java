package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/cart_items")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("get/{cartId}")
	public @ResponseBody List<CartItem> getItemsByCartId(@PathVariable Integer cartId) {
		return cartItemService.getByCartId(cartId);
	}
	
	@DeleteMapping("remove/{itemId}")
	public @ResponseBody CartItem removeItemById(@PathVariable Integer itemId) throws Exception {
		
		
		
		CartItem item=cartItemService.getCartItemById(itemId);
		
		Cart cart=cartService.getByUserId(item.getCart().getUser().getId());
		Product product=productService.getProductById(item.getProduct().getId()).get();
		product.setQty(item.getQty());
		
		cart=updateTotalPriceAndQuantity(cart, product);
		
		cartService.updateCart(cart);
		
		int status=cartItemService.removeItemById(itemId);
		
		if(status<=0) {
			throw new Exception("Item does not exists!");
		}
		
		return item;
	}
	
	
	@GetMapping("getItemCount/{cartId}")
	public @ResponseBody Integer getItemCountByCartId(@PathVariable Integer cartId) {
		return getItemsByCartId(cartId).size();
	}
	
	private Cart updateTotalPriceAndQuantity(Cart cart, Product product) {
		
		Double totalePrice=cart.getTotalPrice()-(product.getQty()*product.getPrice());
		
		Integer totalQty=cart.getTotalQty()-product.getQty();
		
		cart.setTotalPrice(totalePrice);
		cart.setTotalQty(totalQty);
		
		return cart;
	}
}
