package com.adee.spring4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class HelloWorld8 implements InitializingBean, DisposableBean{
	static Logger logger = LoggerFactory.getLogger(HelloWorld8.class);
	
	//bean属性设置后执行，相当于bean的初始化方法
	public void afterPropertiesSet() throws Exception {
		logger.debug("HelloWorld8 afterPropertiesSet...");
	}
	
	//bean被容器销毁前执行
	public void destroy() throws Exception {
		logger.debug("HelloWorld8 destroy...");
	}
}
