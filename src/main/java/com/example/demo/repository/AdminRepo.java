package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Admin;


public interface AdminRepo extends CrudRepository<Admin, Integer>{
	public Optional<Admin> findAdminByEmailIdAndPassword(String emailId, String password);

}
