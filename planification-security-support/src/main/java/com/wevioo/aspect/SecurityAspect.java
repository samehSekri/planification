package com.wevioo.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.wevioo.utility.LoggerUtility;

/**
 * Configuration des log pour la couche service.
 * 
 * @author Jihed KAOUECH
 *
 */
@Aspect
@Component
public class SecurityAspect {

	private final String PACKAGE_TO_LOG_IN = "com.wevioo.service";

	/**
	 * Le loggeur LOG4J.
	 */
	private final LoggerUtility logger = new LoggerUtility(this.getClass());

	/**
	 * Lanced before the execution of any method in SERVICE implementation
	 * Package.
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* " + PACKAGE_TO_LOG_IN + ".*.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String methodArgs = Arrays.toString(joinPoint.getArgs());
		logger.debug("[" + className + "] - Excecution method << " + methodName + " >> with arguments : " + methodArgs);
	}

	/**
	 * Extract data returned from method in SERVICE implementation Package.
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(pointcut = "execution(* " + PACKAGE_TO_LOG_IN + ".*.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		logger.debug("[" + className + "] - Method << " + methodName + " >> returned value is : " + result);
	}

	/**
	 * Executed if the method in the SERVICE implementation Package throw an
	 * exception.
	 * 
	 * @param joinPoint
	 * @param error
	 */
	@AfterThrowing(pointcut = "execution(* " + PACKAGE_TO_LOG_IN + ".*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();

		logger.error("[" + className + "] - Excecution method << " + methodName + " >> throw an exception : "
				+ error.getMessage());
	}

}
