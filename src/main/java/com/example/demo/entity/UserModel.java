package com.example.demo.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserModel {
	
	
	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@NotNull(message = "Email should not be empty")
	@Email(message = "Enter a valid email")
	private String email;
	
	
	@NotNull(message = "Password should not be empty")
	@Size(min = 5,message = "Password should be atleast 5 characters")
	private String password;
	
	private Long age = 0L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
	
	
	

}
