package com.koylumuhendis.librarymanagement.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.koylumuhendis.librarymanagement.model.User;
import com.koylumuhendis.librarymanagement.service.UserService;

@Component
public class StartupConfig implements CommandLineRunner {

	
	private final UserService userService;
	
	
	public StartupConfig(UserService userService) {
		this.userService = userService;
	}


	@Override
	public void run(String... args) throws Exception {
		userService.createUser(new User.builder()
				.username("user")
				.password("pass")
				.build());
		
	}

}
