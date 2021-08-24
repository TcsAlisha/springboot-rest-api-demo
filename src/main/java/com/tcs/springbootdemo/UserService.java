package com.tcs.springbootdemo;

public class UserService implements IUserService{
	@Override
	public void save(User user) {
		System.out.println("saved");
	}
}
