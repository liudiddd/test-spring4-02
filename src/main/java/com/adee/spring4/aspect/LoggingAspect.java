package com.adee.spring4.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 使用注解@Aspect、@Component将此类声明为一个切面
 * 通知的类型有：
 * 	@Before						前置通知，目标方法前执行
 * 	@After							后置通知，目标方法后执行（无论目标方法是否抛出异常，都会执行后置通知方法）
 * 	@AfterReturning	返回通知，目标方法返回后执行
 * 	@AfterThrowing		异常通知，目标方法抛出异常时执行
 * 	@Around					环绕通知
 * @author Administrator
 *
 */
/**
 * 配置多个Aspect时，可以使用@Order(n)指定Aspect的优先级，n越小优先级越高
 */
@Order(1)
@Aspect
@Component
public class LoggingAspect {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 定义一个方法，仅用于声明切入点表达式，一般地，方法中不需要添加任何代码
	 */
	@Pointcut("execution(public * com.adee.spring4.service.*.*(..))")
	public void pointCut() {
		
	}
	
	
	
	/**
	 * 声明前置通知:
	 * 1.xml配置文件中需要声明<aop:aspectj-autoproxy/>，表示将execution表达式匹配到的类生成代理对象
	 * 2.execution匹配到指定方法的类生成代理对象，典型声明规则如下：
	 * 	-- execution(* com.adee.spring4.service.*.*(..))
	 * 		第一个*任何修饰符和返回类型，第二个*表示service包下的所有类，第三个*表示类的所有方法，..表示任意个任意类型的参数
	 * 	-- execution(public * com.adee.spring4.service.UserService.*(..))
	 * 		匹配UserService接口所有实现类的所有public方法
	 * 	-- execution(public double com.adee.spring4.service.UserService.*(..))
	 * 		匹配public double方法
	 * 	-- execution(public double com.adee.spring4.service.UserService.*(double, double))
	 * 		匹配public double，且参数为(double, double)的方法
	 * @param joinPoint
	 */
	//接口与实现类不应该在同包，而且用execution声明接口不用public，因为接口中的方法都是public的
	//@Before("execution(public * com.adee.spring4.service.*.*(..))") 
	//@Before("execution(public * com.adee.spring4.service.UserService.*(..))")
	@Before("pointCut()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		logger.error("beforeMethod " + methodName +  ", args: " + args +  "...");
	}
	
	/**
	 * 后置通知，目标方法后执行（无论目标方法是否抛出异常，都会执行后置通知方法）
	 * 但是，后置通知不能访问目标方法的返回结果
	 * @param joinPoint
	 */
	@After("pointCut()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		logger.error("afterMethod " + methodName +  ", args: " + args +  "...");
	}
	
	
	@AfterReturning(value="pointCut()", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		logger.error("afterReturing " + methodName +  ", args: " + args + ", result: " + result +  "...");
	}
	
	/**
	 * 异常通知：
	 * 目标方法抛出异常时执行，可以访问到异常对象
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(pointcut="pointCut()", throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		logger.error("afterThrowing " + methodName +  ", args: " + args + ", ex: " + ex.toString() +  "...");
	}
	
	
	/**
	 * 环绕通知：
	 * 1.环绕通知需要携带ProceedingJoinPoint类型的参数
	 * 2.环绕通知类似于动态代理的全过程，ProceedingJoinPoint类型的参数可以决定是否执行目标方法
	 * 3.环绕通知必须由返回值，返回值即为目标方法的返回值
	 * @param pjp
	 */
	@Around("pointCut()")
	public Object aroundMethod(ProceedingJoinPoint pjp) {
		Object result = null;
		String methodName = pjp.getSignature().getName();
		List<Object> args = Arrays.asList(pjp.getArgs());
		
		//执行目标方法
		try {
			//执行前置通知
			logger.debug("around执行前置通知...");
			result = pjp.proceed();
		}catch(Throwable ex) {
			//执行异常通知
			logger.error("around执行异常通知...", ex);
		}finally {
			//执行后置通知
			logger.debug("around执行后置通知...");
		}
		return result;
	}
	
	
	
	
	
	
	
	
}
