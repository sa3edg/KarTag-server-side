package com.kartag.business.exception;

import com.kartag.common.exception.BadRuntimeException;
import com.kartag.common.logging.LocalLogger;

public class BadProcessingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(BadRuntimeException.class);
	
	public BadProcessingException(Throwable t)
	{
		logger.error("000000003", t);
	}
	public BadProcessingException()
	{
		logger.error("000000003", new Throwable());
	}
}
