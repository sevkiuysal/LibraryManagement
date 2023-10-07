package com.koylumuhendis.librarymanagement.dto;


import com.koylumuhendis.librarymanagement.model.Role;

public class UserDto {

	private final String username;
	private final Role role;
	private final String email;
	
	
	public String getUsername() {
		return username;
	}
	public Role getRole() {
		return role;
	}
	public String getEmail() {
		return email;
	}


	public static class builder{
		private String username;
		private Role role;
		private String email;
		
		public builder username(String username) {
			this.username=username;
			return this;
		}
		public builder role(Role role) {
			this.role=role;
			return this;
		}
		public builder email(String email) {
			this.email=email;
			return this;
		}
		public UserDto build() {
			return new UserDto(this);
		}
	}
	public UserDto(builder builder) {
		this.username=builder.username;
		this.role=builder.role;
		this.email=builder.email;
	}
}
