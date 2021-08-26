package com.tcs.springbootdemo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.springbootdemo.entity.User;
import com.tcs.springbootdemo.exception.UserNotFoundException;
import com.tcs.springbootdemo.repository.IUserRepository;

@Service
public class UserService implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	@Autowired
	IUserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
		logger.debug("Saved");
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUser(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("user does not exist");
		}
		return user;
	}
	
	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}