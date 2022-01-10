package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService proService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemServcie;
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws Exception {
		Product p=proService.addProduct(product);
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public @ResponseBody List<Product> getAll() {
		return proService.getAll();
	}
	
	@GetMapping("/getall/{name}")
	public @ResponseBody List<Product> getAllByNameAndDescription(@PathVariable String name) {
		return proService.getAllByNameAndDescription(name);
	}
	
	@GetMapping("/GetByCategoryId/{categoryId}")
	public @ResponseBody List<Product> getByCategoryId(@PathVariable Integer categoryId) {
		return proService.getByCategoryId(categoryId);
	}
	
	@DeleteMapping("/remove/{id}")
	public Optional<Product> removeById(@PathVariable("id") Integer id){
		return proService.removeProductById(id);
	}
	
	
	@GetMapping("/get/{id}")
	public Optional<Product> getById(@PathVariable("id") Integer id){
		return proService.getProductById(id);
	}
	
	@PutMapping("update")
	public  @ResponseBody Product updateById(@RequestBody Product product) throws Exception {
		return proService.updateProductById(product);
	}
	
	@PostMapping("addtocart/{uid}/{pid}/{qty}")
	public @ResponseBody Product addToCart(@PathVariable("uid") Integer uid,@PathVariable("pid") Integer pid,@PathVariable("qty") Integer qty) {
		
		Cart cart=null;
		CartItem item=null;
		
		Product product=proService.getProductById(pid).get();
		product.setQty(qty);
		
		//create cart if does not exists
		if(!cartService.existsCartByUserId(uid)) {
			try {
				cart=new Cart();
				item=new CartItem();
				cart.setUser(userService.getUserById(uid));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			item.setProduct(product);
			item.setQty(product.getQty());
			
			cart.setTotalPrice(product.getPrice()*product.getQty());
			cart.setTotalQty(item.getQty());
			
			cart=cartService.createCart(cart);
			
			item.setCart(cart);
			
			cartItemServcie.addCartItem(item);

			return product;
		}
		
		cart=cartService.getByUserId(uid);
		cart = updateTotalPriceAndQuantity(cart, product);
		cartService.updateCart(cart);
		
		
		item=new CartItem();
		item.setProduct(product);
		item.setQty(product.getQty());
		item.setCart(cart);
		cartItemServcie.addCartItem(item);
		
		
		
		return product;
	}
 
	
	private Cart updateTotalPriceAndQuantity(Cart cart, Product product) {
		
		Double totalePrice=cart.getTotalPrice()+(product.getQty()*product.getPrice());
		
		cart.setTotalPrice(totalePrice);
		cart.setTotalQty(cart.getTotalQty()+product.getQty());
		
		return cart;
	}

}