package com.fundamentos.springboot.fundamentos.caseuse;

import java.util.List;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.services.UserService;

public class GetUserImpl implements GetUser {
	
	private UserService userService;
	
	public GetUserImpl(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public List<User> getAll() {
		return userService.getAllUsers();
	}

}
