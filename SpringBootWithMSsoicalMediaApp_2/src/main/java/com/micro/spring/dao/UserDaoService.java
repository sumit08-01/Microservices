package com.micro.spring.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.micro.spring.user.User;

@Component
public class UserDaoService {

	private static int count = 0;
	
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(++count, "sumit", LocalDate.now().minusYears(28)));
		users.add(new User(++count, "sourabh", LocalDate.now().minusYears(25)));
		users.add(new User(++count, "Yogi", LocalDate.now().minusYears(26)));
	}
	
	public List<User> findAll() {
		return users;
		
	}

	public User findOneById(int id) {
//		User first = users.stream().filter(x -> x.getId().equals(id)).findFirst().get();
		User first = users.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
		return first;
	}
	
	public boolean userCreated(User user) {
		 user.setId(++count);
		return users.add(user);
	}

	public void deleteByIdUser(Integer id) {
		users.removeIf(x -> x.getId().equals(id));
		
	}
	
	
	
}
