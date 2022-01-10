package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ProductCategory;
import com.example.demo.repository.ProductCategoryRepo;

@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepo categoryRepo;
	
	public List<ProductCategory> getAll(){
		return categoryRepo.findAll();
	}

	public ProductCategory getById(Integer id) {
		return categoryRepo.getById(id);
	}

	public ProductCategory add(ProductCategory category) {
		return categoryRepo.save(category);
	}
}
