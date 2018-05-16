package com.adee.spring4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * bean后置处理器：
     	1.bean后置处理器允许在bean调用初始化方法前后对bean进行额外的处理
     	2.bean后置处理器对IOC容器里面的所有bean实例逐一处理，而非单一实例，其典型应用是：检查bean属性的正确性或根据特定的
     		标准更改bean的属性
     	3.对bean后置处理器而言，需要实现BeanPostProcessor接口，在初始化方法调用前后，spring将把每个bean实例分别传递给上述
     		接口的以下两个方法：
 * @author Administrator
 *
 */
public class MyBeanPostProcessor implements BeanPostProcessor{
	static Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor.class);
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if("hw7".equals(beanName)) {
			logger.debug("before HelloWorld7 init...");
		}
		return bean;
	}
	
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if("hw7".equals(beanName)) {
			logger.debug("after HelloWorld7 init...");
		}
		return bean;
	}
	
}
