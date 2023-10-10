package com.user.exmaple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.exmaple.model.User;
import com.user.exmaple.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository repository;
	@Override
	public Integer saveTheUser(User user) {
		// TODO Auto-generated method stub
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return repository.save(user).getId();
	}

}	
