package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;
import com.example.demo.service.UserService;

@RestController
public class UserCobtroller {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> save (@Valid @RequestBody UserModel user){
		System.out.println(user.getName());
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> readUser(@PathVariable Integer id){
		return new ResponseEntity<User> (userService.readUser(id),HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserModel user,@PathVariable Integer id ){
		
		return new ResponseEntity<User>(userService.updateUser(user, id),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus>  deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<HttpStatus> (HttpStatus.NO_CONTENT);
	}

}
