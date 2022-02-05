package com.fundamentos.springboot.fundamentos.services;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;

@Service
public class UserService {

	private final Log LOG = LogFactory.getLog(UserService.class);
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void saveTransactional(List<User> users) {
		users.stream()
			.peek(user -> LOG.info("Usuario Insertado: " + user))
			.forEach(userRepository::save);
			//.forEach(user -> userRepository.save(user) );
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
}
