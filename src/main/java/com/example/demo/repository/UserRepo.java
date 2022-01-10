package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserRepo extends CrudRepository<User,Integer>{
	
	Optional<User> findUserById(Integer id);
	Optional<User> findUserByEmailId(String emailId);
	Optional<User> findUserByEmailIdAndPassword(String emailId,String password);
	boolean existsByEmailId(String emailId);
	
	@Override
	List<User> findAll();
	
	
	Optional<User> removeUserById(Integer userId);
	
	@Query(value="SELECT * FROM user u WHERE u.email_id LIKE %?1%"
			+ " OR u.user_name LIKE %?1%"
			+ " OR u.first_name LIKE %?1%"
			+ " OR u.last_name LIKE %?1%", nativeQuery = true)
	
	public List<User> findAllByEmailId(String emailId);
}
