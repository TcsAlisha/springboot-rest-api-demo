package com.tcs.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	IUserService userservice;
	
	@GetMapping("/getUser")
	public String getuser() {
		System.out.println("Hello");
		return "Hello";
	}
	
	@PostMapping("/user")
	public void saveuser(@RequestBody User user) {
		userservice.save(user);
		System.out.println(user.getFirstName());
		
	}
	
	@PutMapping("/user")
	public void updateUser(@RequestBody User user) {
		userservice.save(user);
		System.out.println(user.getFirstName());
		
	}
}