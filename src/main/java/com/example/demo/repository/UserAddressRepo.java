package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserAddress;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress, Integer> {

	List<UserAddress> findUserAddessByUserId(Integer userId);
	UserAddress findUserAddessById(Integer id);

}
