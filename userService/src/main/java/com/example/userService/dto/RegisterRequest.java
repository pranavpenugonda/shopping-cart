package com.example.userService.dto;


public class RegisterRequest {
	public String username;
	public String email;
	public String password;
	public String address;
    public String phoneNumber;
    public String role;
	public RegisterRequest() {
		super();
	}
	public RegisterRequest(String username, String email, String password, String role, String address,
			String phoneNumber) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
}

