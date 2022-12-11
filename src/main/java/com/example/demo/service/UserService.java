package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;

public interface UserService {
	
	User createUser(UserModel user);
	
	User readUser(Integer id);
	
	User updateUser(UserModel user,Integer id);
	
	void deleteUser(Integer id);

}
