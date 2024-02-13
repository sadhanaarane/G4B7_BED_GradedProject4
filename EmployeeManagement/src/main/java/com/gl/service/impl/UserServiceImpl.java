package com.gl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dao.UserRepository;
import com.gl.entity.User;
import com.gl.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository usersRepository;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}

	@Override
	public void saveUsers(User user) {
		// TODO Auto-generated method stub
		usersRepository.save(user);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return usersRepository.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return usersRepository.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		usersRepository.deleteById(id);
	}

}
