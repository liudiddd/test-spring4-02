package com.adee.spring4.exception;

/**
 * 自定义异常，用于事务回滚
 * @author Administrator
 *
 */
public class RollbackException extends Exception{
	public RollbackException() {
		super();
	}
	
	public RollbackException(String msg) {
		super(msg);
	}
}
