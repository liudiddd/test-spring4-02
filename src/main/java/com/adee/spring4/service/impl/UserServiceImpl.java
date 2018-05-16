package com.adee.spring4.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adee.spring4.dao.UserDao;
import com.adee.spring4.exception.RollbackException;
import com.adee.spring4.User;
import com.adee.spring4.service.UserService;

//IOC都使用xml配置，DI使用注解注入
//@Service
public class UserServiceImpl implements UserService{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * javax.annotation.Resource注解进行属性注入在spring中的表现：
	 * 1.只用@Resource注解的成员变量或setter方法，默认按照成员变量名查找bean，如果找不到，则按照类型查找，
	 * 	如果该类型的bean个数多于1，则报错。
	 * 2.使用@Resource(name="xxx")注解的成员变量或setter方法，只会按照bean名字查找，找不到则报错
	 * 3.使用@Resource注解时，成员变量可以没有setter方法
	 */
	//@Resource
	
	/**
	 * 使用org.springframework.beans.factory.annotation.Autowired注解进行属性注入：
	 * 1.默认按类型查找bean，如果有多个该类型的bean，则先匹配与成员变量名相同的bean，如果没匹配上，则报错。
	 * 2.如果想按名字匹配则这样写：@Autowired @Qualifier("userDao")，这个写法等同于@Resource(name="userDao")，
	 * 	如果名字匹配不到，直接报错，不会再按类型匹配了
	 * 3.如果允许注入null则这样写：@Autowired(required=false) @Qualifier("userDao")，意思是，如果没有匹配到任何bean，
	 * 	也不会报错，注入null
	 */
	//@Autowired(required=false) @Qualifier("userDao")
	
	/**
	 * 最佳实践：
	 * 一般情况下，我们都会按照name匹配的，并且不允许为null，因此，使用@Resource，默认按名字匹配
	 */
	@Resource
	UserDao userDao;
	
	/**
	 * spring事务传播属性：

		在 spring的 TransactionDefinition接口中一共定义了六种事务传播属性：
		
		PROPAGATION_REQUIRED - 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 
															- 当前方法不会提交事务，待调用者方法执行完，由调用者提交事务，
															- 但是当前方法可以抛出回滚异常从而使事务回滚。
		PROPAGATION_SUPPORTS - 支持当前事务，如果当前没有事务，就以非事务方式执行。 
		PROPAGATION_MANDATORY - 支持当前事务，如果当前没有事务，就抛出异常。 
		PROPAGATION_REQUIRES_NEW - 新建事务，如果当前存在事务，把当前事务挂起。 
		PROPAGATION_NOT_SUPPORTED - 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
		PROPAGATION_NEVER - 以非事务方式执行，如果当前存在事务，则抛出异常。 
		PROPAGATION_NESTED - 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED
														 类似的操作。 
		
		前六个策略类似于EJB CMT，第七个（PROPAGATION_NESTED）是Spring所提供的一个特殊变量。 
		它要求事务管理器或者使用JDBC 3.0 Savepoint API提供嵌套事务行为（如Spring的DataSourceTransactionManager）
		 
	 */
	
	/**
	 * rollbackFor声明事务回滚：
	 * 1.spring默认对RuntimeException进行事务回滚
	 * 2.配置rollbackFor为非RuntimeException时，也会对RuntimeException进行回滚
	 * 3.对于配置的rollbackFor异常及子类异常，都会回滚
	 */
	
	/**
	 * isolation声明事务隔离级别：
		1. ISOLATION_DEFAULT： 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别。另外四个与
														 JDBC的隔离级别相对应；
		2. ISOLATION_READ_UNCOMMITTED： 这是事务最低的隔离级别，它充许令外一个事务可以看到这个事务未提交的数据。这种隔离
																					级别会产生脏读，不可重复读和幻像读；
		3. ISOLATION_READ_COMMITTED： 保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提
																			  交的数据；
		4. ISOLATION_REPEATABLE_READ： 这种事务隔离级别可以防止脏读，不可重复读。但是可能出现幻像读。它除了保证一个事务不能
																			读取另一个事务未提交的数据外，还保证了避免下面的情况产生(不可重复读)；
		5. ISOLATION_SERIALIZABLE： 这是花费最高代价但是最可靠的事务隔离级别。事务被处理为顺序执行。除了防止脏读，不可重复读外，
																  还避免了幻像读。
	 */
	
	/**
	 * readOnly设置只读事务：
	 * true表示当前方法所在的事务为只读事务，即只查询数据，不增删改数据。设置为只读事务可以帮数据库引擎优化事务，略去加锁等操作。
	 */
	
	/**
	 * timeout设置事务最长时间：
	 * 单位为秒
	 * 当事务从开启后持续时间超过了timeout，就强制回滚事务。好处是不让线程占用连接的时间过长。
	 * @param user
	 */
	/*@Transactional(rollbackFor=RollbackException.class, 
									propagation=Propagation.REQUIRED,
									isolation=Isolation.READ_COMMITTED,
									readOnly=false,
									timeout=5
			)*/
	public void save(User user) {
		/*logger.debug("UserServiceImpl save");
		dopriv();*/
		
		//try { Thread.sleep(6000); } catch (InterruptedException e) { }
		userDao.save(user);
		save1(user);
		//测试当前方法处于事务中时，调用事务传播属性为required方法后，当前方法回滚，会使得被调用的required方法也回滚
		//throw new IndexOutOfBoundsException("this is my IndexOutOfBoundsException...");
	}
	
	//@Transactional(rollbackFor=NullPointerException.class, propagation=Propagation.REQUIRED)
	public void save1(User user) {
		user.setName("Inner");
		userDao.save(user);
		//测试事务传播属性为required方法回滚，也会使调用者方法回滚
		//throw new NullPointerException("this is my NullPointerException...");
	}
	
	
	public User get(String name) {
		if(1 == 1)
			throw new RuntimeException("this is my RuntimeException...");
		User u = new User();
		u.setName(name);
		return u;
	}
	
	
	private void dopriv() {
		logger.debug("UserServiceImpl private dopriv");
	}
}
