package com.koylumuhendis.librarymanagement.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name="_user")
public class User extends BaseEntity{


    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;


	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Role getRole() {
		return role;
	}
	public String getEmail() {
		return email;
	}
	public void setPassword(String password) {
		this.password=password;
	}

	public static class builder{
		private String username;
		private Role role;
		private String email;
		private String password;
		
		public builder username(String username) {
			this.username=username;
			return this;
		}
		public builder password(String password) {
			this.password=password;
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
		public User build() {
			return new User(this);
		}
	}
	public User(builder builder) {
		this.username=builder.username;
		this.password=builder.password;
		this.role=builder.role;
		this.email=builder.email;
	}
}
