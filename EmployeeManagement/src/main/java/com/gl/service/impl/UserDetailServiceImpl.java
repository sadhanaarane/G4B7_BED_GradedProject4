package com.gl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.dao.UserRepository;
import com.gl.entity.User;
import com.gl.security.MyUserDetails;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		System.out.println(user);

		if (user == null)
			throw new UsernameNotFoundException("Could not find user");
		return new MyUserDetails(user);
	}

}
