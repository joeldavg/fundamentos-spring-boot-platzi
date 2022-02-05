package com.fundamentos.springboot.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fundamentos.springboot.fundamentos.caseuse.GetUser;
import com.fundamentos.springboot.fundamentos.caseuse.GetUserImpl;
import com.fundamentos.springboot.fundamentos.services.UserService;

@Configuration
public class CaseUserConfiguration {
	
	@Bean
	GetUser getUser(UserService userService) {
		return new GetUserImpl(userService);
	}
	
}
