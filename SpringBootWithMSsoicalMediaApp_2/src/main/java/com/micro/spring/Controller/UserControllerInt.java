package com.micro.spring.Controller;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import com.micro.spring.user.User;

public interface UserControllerInt {

	public List<User> retrieveAllUsers();
	
//	public User findById(Integer id);
	public EntityModel<User> findById(Integer id);
	
	public ResponseEntity<User> createUser(User user);
	
	public void deleteById(Integer id);
	
	
}
