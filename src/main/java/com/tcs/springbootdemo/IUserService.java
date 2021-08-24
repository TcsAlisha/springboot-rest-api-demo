package com.tcs.springbootdemo;

import org.springframework.stereotype.Service;

@Service
public interface IUserService {
	void save(User user);
}
