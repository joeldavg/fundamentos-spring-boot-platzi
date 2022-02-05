package com.fundamentos.springboot.fundamentos.caseuse;

import org.springframework.stereotype.Component;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.services.UserService;

@Component
public class UpdateUser {
	
	private UserService userService;

	public UpdateUser(UserService userService) {
		this.userService = userService;
	}

	public User update(User newUser, Long id) {
		return userService.update(newUser, id);
	}
	
	
	
}
