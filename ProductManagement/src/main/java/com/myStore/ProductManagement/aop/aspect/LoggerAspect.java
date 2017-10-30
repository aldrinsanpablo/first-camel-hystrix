package com.myStore.ProductManagement.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.myStore.ProductManagement.exception.ProductManagementCustomException;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@Aspect
@Component
public class LoggerAspect {

	private static final Logger LOGGER = Logger.getLogger(LoggerAspect.class);
	
	@Pointcut("within(com.myStore.ProductManagement.service.*)")
	public void allServiceMethods() {}
	
	@Pointcut("within(com.myStore.ProductManagement.model.tables.daos.*)")
	public void allDaoMethods() {}
	
	@Pointcut("within(com.myStore.ProductManagement.controller.*)")
	public void allControllerMethods() {}
	
	@Around("allDaoMethods() || allServiceMethods() || allControllerMethods()")
	public Object logAction(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.info("[START] Executing method call: " + pjp.getSignature() );
		Object retVal = null;
		try {
			retVal = pjp.proceed();
		} finally {
			LOGGER.info("[END] Completed method call: " + pjp.getSignature() );
		}
		return retVal;
	}
	
//	@Before("execution(* org.jooq.DSLContext.*(..))")
	public void logSqlStatement(JoinPoint jp) {
		LOGGER.info( "[JOOQ] Executing SQL: " + jp.getSignature() );
	}
	
	@AfterThrowing(pointcut="allDaoMethods() || allServiceMethods() || allControllerMethods()", throwing = "ex")
	public void logAction(JoinPoint jp, ProductManagementCustomException ex) {
		LOGGER.error("[EXCEPTION]: " + ex.getInfo());
	}
	
	@AfterThrowing(pointcut="execution(com.myStore.ProductManagement.model.ProductDetail com.myStore.ProductManagement.service.ProductManagementService.getProductDetail(int))", throwing = "ex")
	public void logHystrixError(JoinPoint jp, Exception ex) {
		LOGGER.error("[EXCEPTION - HYSTRIX]: " + ex.getMessage() );
		ex.printStackTrace();
	}
	
}
