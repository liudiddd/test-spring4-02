package com.adee.spring4;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.adee.spring4.component.MyComponent;
import com.adee.spring4.controller.UserController;
import com.adee.spring4.service.UserService;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws Exception{
    	/**
    	 * 初始化ioc容器
    	 * 一、
    	 * 有两种初始化spring ioc容器的方法：beanfactory和applicationContext
    	 * 前者是面向spring本身的，后者是面向spring用户的
    	 * 
    	 * 两种加载spring配置文件的方式：
    	 * 	ClassPathXmlApplicationContext：从类路径下加载配置文件
    	 * 	FileSystemApplicationContext：从文件系统加载配置文件
    	 * 
    	 * ConfigurableApplicationContext接口扩展于ApplicationContext新增了两个主要方法：refresh()、close()，让ApplicationContext
    	 * 具有启动、刷新和关闭上下文的能力。
    	 * ApplicationContext在初始化上下文的时候就实例化所有单例的bean。
    	 * WebApplicationContext是专门为WEB应用而准备的，它允许从相对于WEB根目录的路径中完成初始化工作。
    	 * 
    	 * 二、
    	 * 属性注入方式：属性注入、构造方法注入、工厂方法注入（不推荐）
    	 * 属性注入：getter方法注入
    	 * 构造方法注入：<constructor-arg>注入
    	 */
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       
       
       //测试普通bean
       HelloWorld hw = context.getBean("helloWorld", HelloWorld.class);
       logger.debug(hw.getName());
       
       //测试构造方法注入属性，helloWorld1和helloWorld2都是通过构造方法进行属性注入的，有循环依赖的问题，启动报错
       /*HelloWorld1 hw1 = context.getBean("helloWorld1", HelloWorld1.class);
       logger.debug(hw1.getName() +" " + hw1.getAge());*/
       
       //helloWorld3和helloWorld4都是通过属性注入的，没有循环依赖的问题
       HelloWorld3 hw3 = context.getBean("helloWorld3", HelloWorld3.class);
       logger.debug(hw3.getHw4().toString());
       HelloWorld4 hw4 = context.getBean("helloWorld4", HelloWorld4.class);
       logger.debug(hw4.getHw3().toString());
       
       //测试bean配置继承
       HelloWorld5 hw51 = context.getBean("hw51", HelloWorld5.class);
       logger.debug(hw51.getName());
       
       //测试bean的scope属性
       HelloWorld6 hw6 = context.getBean("hw6", HelloWorld6.class);
       logger.debug(hw6.toString()); //com.adee.spring4.HelloWorld6@376b4233
       HelloWorld6 hw66 = context.getBean("hw6", HelloWorld6.class);
       logger.debug(hw66.toString()); //com.adee.spring4.HelloWorld6@376b4233
       HelloWorld6 hw61 = context.getBean("hw61", HelloWorld6.class);
       logger.debug(hw61.toString()); //com.adee.spring4.HelloWorld6@2fd66ad3
       HelloWorld6 hw611 = context.getBean("hw61", HelloWorld6.class);
       logger.debug(hw611.toString()); //com.adee.spring4.HelloWorld6@5d11346a
       
       //测试spring配置文件引用properties文件内容
       ComboPooledDataSource dataSource = context.getBean("dataSource", ComboPooledDataSource.class);
       logger.debug(dataSource.getUser() + " " + dataSource.getPassword());
       
       //通过静态工厂方法创建bean实例
       Car car1 = context.getBean("car1", Car.class);
       logger.debug(car1.toString());
       
     //通过实例工厂方法创建bean实例
       Car car2 = context.getBean("car2", Car.class);
       logger.debug(car2.toString());
       
       //实现spring的FactoryBean接口来创建bean
       Car car3 = context.getBean("car3", Car.class);
       logger.debug(car3.toString());
       
       
       //测试@Resource
       UserController userController = context.getBean("userController", UserController.class);
       userController.execute();
       
       //测试<context:exclude-filter />扫描bean时排除包
       MyComponent myComponent = context.getBean("myComponent", MyComponent.class);
       logger.debug(myComponent.toString());
       
       /**
        * spring4的aop实现：
        * 1.spring4的aop有两种配置方式：注解和xml
        * 2.spring4可以使用自身的aop实现（jdk动态代理），也可以使用AspectJ，下面以AspectJ为例：
        * 		1.引入aopalliance.jar、aspectjweaver.jar、spring-aspects.jar
        * 		2.配置文件中引入xmlns:aop以及xsd
        * 		3.配置文件中声明对AspectJ的注解支持：<aop:aspectj-autoproxy />
        * 		4.当IOC容器检测到配置文件中启用了AspectJ的支持后，会自动给匹配到的bean创建代理对象
        */
       //测试sop@AfterReturning
       UserService userService = context.getBean("userService", UserService.class);
       userService.get("Banama");
       
       //测试JdbcTemplate
       JdbcTemplate jt = context.getBean("jdbcTemplate", JdbcTemplate.class);
       //String sql = "select * from t_user where id=?";
       //User u = jt.queryForObject(sql, new Integer[] {1}, User.class);
       //logger.debug(u.getName());
       String sql = "update t_user set name = ? where id = ?";
       jt.update(sql, "Jackson", 1);
       
       
    }
}
