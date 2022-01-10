package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;
import com.example.demo.model.User;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{
	Optional<Product> removeProductById(Integer id);
	Optional<Product> getProductById(Integer id);
	Optional<Product> findProductById(Integer id);
	List<Product> findProductsByCategoryId(Integer categoryId);
	
	@Query(value="SELECT * FROM products p WHERE p.name LIKE %?1%"
			+ " OR p.description LIKE %?1%", nativeQuery = true)
	
	public List<Product> findAllByNameAndDescription(String name);
}