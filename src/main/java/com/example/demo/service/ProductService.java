package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.ProductRepo;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	public List<Product> getAll() {
		return productRepo.findAll();
	}

	public Optional<Product> removeProductById(Integer id) {
		return productRepo.removeProductById(id);
	}
	
	public Optional<Product> getProductById(Integer id) {
		return productRepo.getProductById(id);
	}

	public Product updateProductById(Product product) throws Exception {
		Optional<Product> productData=productRepo.findProductById(product.getId());
		
		if(productData.isPresent()) {
			return productRepo.save(product);
		}else {
			throw new Exception("Item does not exist!");
		}
	}

	public List<Product> getByCategoryId(Integer categoryId){
		return productRepo.findProductsByCategoryId(categoryId); 
		
	}
	
	
	public List<Product> getAllByNameAndDescription(String name){
		return productRepo.findAllByNameAndDescription(name);
	}
}
