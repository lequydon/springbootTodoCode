package com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.Service.Interface.IUserService;
import com.demo.entity.User;
import com.demo.repository.UserRepository;

public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email);
		return user;
	}

}
