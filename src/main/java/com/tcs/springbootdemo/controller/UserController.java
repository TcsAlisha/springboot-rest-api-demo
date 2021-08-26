package com.tcs.springbootdemo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.springbootdemo.entity.User;
import com.tcs.springbootdemo.exception.UserNotFoundException;
import com.tcs.springbootdemo.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	IUserService userservice;

	@GetMapping
	public Iterable<User> getUser() {
		return userservice.getAllUsers();
	}

	@GetMapping("/{id}")
	public Optional<User> getUser(@PathVariable("id") Integer id) {
		return userservice.getUser(id);
	}

	@ExceptionHandler(value = { UserNotFoundException.class, IllegalStateException.class, EmptyResultDataAccessException.class })
	public ResponseEntity<User> exception(RuntimeException runtimeException) {
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public void saveUser(@RequestBody @Valid User user) {
		try {
			userservice.save(user);
		} catch (Exception e) {
			logger.error(e.getCause().toString());
		}
		
		logger.debug(user.getFirstName());
	}

	@PutMapping
	public void updateUser(@RequestBody User user) {
		userservice.save(user);
		logger.debug(user.getFirstName());
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userservice.deleteUser(id);
	}
	
}