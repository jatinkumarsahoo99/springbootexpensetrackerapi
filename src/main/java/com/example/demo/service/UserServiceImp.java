package com.example.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserModel;
import com.example.demo.exceptions.ItemAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(UserModel user) {
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already register with email"+user.getEmail());
		}
		
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		
		return userRepository.save(newUser);
	}

	@Override
	public User readUser(Integer id) {
		
		
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found for the od"+id.toString()));
	}

	@Override
	public User updateUser(UserModel user, Integer id) {
		User existingUser = readUser(id);
		existingUser.setName(user.getName() != null ? user.getName(): existingUser.getName() );
		existingUser.setEmail(user.getEmail() != null ? user.getEmail(): existingUser.getEmail() );
		existingUser.setPassword(user.getPassword() != null ? user.getPassword(): existingUser.getPassword() );
		existingUser.setAge(user.getAge() != null ? user.getAge(): existingUser.getAge() );
		
		
		return userRepository.save(existingUser);
	
	}

	@Override
	public void deleteUser(Integer id) {
		User exsitingUser = readUser(id);
		userRepository.delete(exsitingUser);
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
