package com.micro.spring.Controller;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.micro.spring.Exceptions.UserNotFoundException;
import com.micro.spring.dao.UserDaoService;
import com.micro.spring.user.User;

import jakarta.validation.Valid;

@RestController
public class UserController implements UserControllerInt{

	private UserDaoService daoService;
	
	public UserController(UserDaoService daoService) {
		this.daoService=daoService;
	}

	@Override
	@GetMapping("/user")
	public List<User> retrieveAllUsers() {
		System.out.println(daoService.findAll());
		return daoService.findAll();
		
	}

//	@Override
//	@GetMapping("/user/{id}")
//	public User findById(@PathVariable Integer id) {
//		System.out.println(daoService.findOneById(id));
//		User user = daoService.findOneById(id);
//		if( user == null) {
//			throw new UserNotFoundException("id : " + id);
//		}
//		return user;
//	}
	
	
//Implementing Hateous
	@Override
	@GetMapping("/user/{id}")
	public EntityModel<User> findById(@PathVariable Integer id) {
		User user = daoService.findOneById(id);
		if( user == null) {
			throw new UserNotFoundException("id : " + id);
		}
		EntityModel<User> entity = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entity.add(link.withRel("all-users"));
		return entity;
	}

	
//	This is a way
//	@Override
//	@PostMapping("/userAdd")
//	public boolean createUser(@RequestBody User user) {
//		System.out.println(user.toString());
//		return daoService.userCreated(user);
//	}
	
	
// Another way mean sending status code with the help of response entity
	@Override
	@PostMapping("/userAdd")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		daoService.userCreated(user);
		System.out.println(user.toString());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri(); // sending the current path url
		return ResponseEntity.created(location).build();
	}

	@Override
	@DeleteMapping("/user/{id}")
	public void deleteById(@PathVariable Integer id) {
		daoService.deleteByIdUser(id);
	}
	
	
}
