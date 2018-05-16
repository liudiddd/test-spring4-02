package com.adee.spring4;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.adee.spring4.service.UserService;

public class JDBCTest {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	ApplicationContext cxt = null;
	JdbcTemplate jt = null;
	
	@Before
	public void before() {
		cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		jt = cxt.getBean("jdbcTemplate", JdbcTemplate.class);
	}
	
	
	
	
	
	@Test
	public void testJdbcTemplate_01() {
	       String sql = "update t_user set name = ? where id = ?";
	       jt.update(sql, "Jackson", 1);
	}
	
	@Test
	public void testJdbcTemplate_02() {
	       String sql = "insert into t_user (name) values (?)";
	       List<Object[]> list = new ArrayList<Object[]>();
	       list.add(new String[] {"Poppy"});
	       list.add(new String[] {"Aoppy"});
	       list.add(new String[] {"Boppy"});
	       list.add(new String[] {"Coppy"});
	       list.add(new String[] {"Doppy"});
	       list.add(new String[] {"Foppy"});
	       list.add(new String[] {"Goppy"});
	       list.add(new String[] {"Hoppy"});
	       jt.batchUpdate(sql, list);
	}
	
	@Test
	public void testJdbcTemplate_03() {
	       String sql = "select id, name from t_user where id = ?";
	       RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
	       User user = jt.queryForObject(sql, mapper, 1);
	       logger.debug(user.getName());
	}
	
	@Test
	public void testTransaction_01() {
	       User user = new User();
	       user.setName("Boppy");
	       UserService us = cxt.getBean("userService", UserService.class);
	       us.save(user);
	}
}
