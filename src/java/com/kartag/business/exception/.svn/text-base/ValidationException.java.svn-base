package com.kartag.business.exception;

import com.kartag.common.logging.LocalLogger;

public class ValidationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(ValidationException.class);
	
	public ValidationException(Throwable t)
	{
		logger.error("000000003", t);
	}
	public ValidationException()
	{
		logger.error("000000003", new Throwable());
	}
}
