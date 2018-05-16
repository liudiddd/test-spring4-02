package com.adee.spring4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld7 {
	static Logger logger = LoggerFactory.getLogger(HelloWorld7.class);
	private String name;
	
	public void init() {
		logger.debug("HelloWorld7 init...");
	}
	
	public void destroy() {
		logger.debug("HelloWorld7 destroy...");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
