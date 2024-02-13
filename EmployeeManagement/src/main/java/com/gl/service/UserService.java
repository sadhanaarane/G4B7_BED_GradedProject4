package com.gl.service;

import java.util.List;

import com.gl.entity.User;

public interface UserService {

	public List<User> getAllUsers();

	public void saveUsers(User user);

	public User getUserById(int id);

	public User updateUser(User user);

	public void deleteUserById(int id);
}