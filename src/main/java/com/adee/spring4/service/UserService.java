package com.adee.spring4.service;

import com.adee.spring4.User;


public interface UserService {
	public void save(User user);
	public void save1(User user);
	public User get(String name);
}
