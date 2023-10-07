package com.koylumuhendis.librarymanagement.service;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koylumuhendis.librarymanagement.model.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserService userService;
	
	public UserDetailServiceImpl(UserService userService){
		this.userService=userService;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userService.findByUsername(username);
		var roles=Stream.of(user.getRole())
				.map(t -> 
				new SimpleGrantedAuthority(t.name()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails
				.User(user.getUsername(), user.getPassword(), roles);
	}

}
