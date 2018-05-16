package com.adee.spring4;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyPropertyPlaceHolderConfigurer extends PropertyPlaceholderConfigurer{
	private static Logger logger = LoggerFactory.getLogger(MyPropertyPlaceHolderConfigurer.class);
	
	@Override
	protected void loadProperties(Properties props) throws IOException {
		props.clear();
		props.setProperty("user", "myroot");
		props.setProperty("password", "mypassword");
		props.setProperty("driverClass", "mydriverClass");
		props.setProperty("jdbcUrl", "myjdbcUrl");
		logger.debug("properties has changed");
	}
	
}
