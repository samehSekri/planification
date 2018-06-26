package com.wevioo.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The <code>LoggerUtility</code> is a utility class producing log method for different levels
 * 
 * @author Jihed KAOUECH
 * 
 */
public class LoggerUtility {

	private Logger logger;

	/**
	 * Create a logger named corresponding to the class passed as parameter, using the statically
	 * bound ILoggerFactory instance.
	 * 
	 * @param clazz : the logger will be named after clazz
	 */
	public LoggerUtility(Class<?> clazz) {
		logger = LogManager.getLogger(clazz.getName());
	}
	

	/**
	 * Create a logger named corresponding to the class passed as parameter, using the statically
	 * bound ILoggerFactory instance.
	 * 
	 * @param clazz : the logger will be named after clazz
	 */
	public LoggerUtility(String name) {
		logger = LogManager.getLogger(name);
	}
	/**
	 * Log a message at the TRACE level.
	 * 
	 * @param message : the message string to be logged
	 */
	public void trace(String message) {
		if (logger.isTraceEnabled()) {
			logger.trace(message);
		}
	}

	/**
	 * Log an exception (throwable) at the TRACE level with an accompanying message.
	 *
	 * @param message : the message accompanying the exception
	 * @param exception : the exception (throwable) to log
	 */
	public void trace(String message, Throwable exception) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, exception);
		}
	}
	
	/**
	 * Log a message at the DEBUG level.
	 *
	 * @param message : the message string to be logged
	 */
	public void debug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	/**
	 * Log an exception (throwable) at the DEBUG level with an accompanying message.
	 *
	 * @param message : the message accompanying the exception
	 * @param exception : the exception (throwable) to log
	 */
	public void debug(String message, Throwable exception) {
		if (logger.isDebugEnabled()) {
			logger.debug(message, exception);
		}
	}

	/**
	 * Log a message at the INFO level.
	 *
	 * @param message : the message string to be logged
	 */
	public void info(String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}

	/**
	 * Log an exception (throwable) at the INFO level with an accompanying message.
	 *
	 * @param message : the message accompanying the exception
	 * @param exception : the exception (throwable) to log
	 */
	public void info(String message, Throwable exception) {
		if (logger.isInfoEnabled()) {
			logger.info(message, exception);
		}
	}

	/**
	 * Log a message at the WARN level.
	 * 
	 * @param message : the message string to be logged
	 */
	public void warn(String message) {
		if (logger.isWarnEnabled()) {
			logger.warn(message);
		}
	}

	/**
	 * Log an exception (throwable) at the WARN level with an accompanying message.
	 *
	 * @param message : the message accompanying the exception
	 * @param exception : the exception (throwable) to log
	 */
	public void warn(String message, Throwable exception) {
		if (logger.isWarnEnabled()) {
			logger.warn(message, exception);
		}
	}
	
	/**
	 * Log a message at the ERROR level.
	 * 
	 * @param message : the message string to be logged
	 */
	public void error(String message) {
		if (logger.isErrorEnabled()) {
			logger.error(message);
		}
	}

	/**
	 * Log an exception (throwable) at the ERROR level with an accompanying message.
	 *
	 * @param message : the message accompanying the exception
	 * @param exception : the exception (throwable) to log
	 */
	public void error(String message, Throwable exception) {
		if (logger.isErrorEnabled()) {
			logger.error(message, exception);
		}
	}
	
	/**
	 * Log a message at the FATAL level.
	 * 
	 * @param message : the message string to be logged
	 */
	public void fatal(String message) {
		if (logger.isFatalEnabled()) {
			logger.fatal(message);
		}
	}

	/**
	 * Log an exception (throwable) at the FATAL level with an accompanying message.
	 *
	 * @param message : the message accompanying the exception
	 * @param exception : the exception (throwable) to log
	 */
	public void fatal(String message, Throwable exception) {
		if (logger.isFatalEnabled()) {
			logger.fatal(message, exception);
		}
	}

}
