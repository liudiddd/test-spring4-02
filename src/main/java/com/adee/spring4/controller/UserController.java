package com.adee.spring4.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.adee.spring4.User;
import com.adee.spring4.service.UserService;

@Controller
public class UserController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	UserService userService;
	public void execute() {
		logger.debug("UserController execute");
		userService.save(new User());
	}
}
