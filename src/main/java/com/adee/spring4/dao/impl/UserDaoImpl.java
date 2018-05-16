package com.adee.spring4.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.adee.spring4.dao.UserDao;
import com.adee.spring4.User;

//IOC都使用xml配置，DI使用注解注入
//@Repository(value="userDao")
public class UserDaoImpl implements UserDao{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public void save(User user) {
		logger.debug("UserDaoImpl save");
		String sql = "insert into t_user (name) values (?)";
		Object[] args = new Object[] {user.getName()};
		jdbcTemplate.update(sql, args);
	}
}
