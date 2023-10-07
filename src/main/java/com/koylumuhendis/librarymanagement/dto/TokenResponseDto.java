package com.koylumuhendis.librarymanagement.dto;

public class TokenResponseDto {
	
	private String accessToken;
	private UserDto userDto;
	
	public TokenResponseDto() {
	}
	public TokenResponseDto(String accessToken, UserDto userDto) {
		this.accessToken = accessToken;
		this.userDto = userDto;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
	

}
