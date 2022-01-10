package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductCategory;
import com.example.demo.service.ProductCategoryService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/ProductCategory")
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService categoryService;
	
	@GetMapping("GetAll")
	public List<ProductCategory> getAll(){
		return categoryService.getAll();
	}
	
	@GetMapping("GetById/{id}")
	public ProductCategory getById(@PathVariable Integer id){
		return categoryService.getById(id);
	}
	
	@PostMapping("Add")
	public ProductCategory add(@RequestBody ProductCategory category) {
		return categoryService.add(category);
	}
}
