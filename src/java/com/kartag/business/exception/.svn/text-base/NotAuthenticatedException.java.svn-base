package com.kartag.business.exception;

import com.kartag.common.logging.LocalLogger;

public class NotAuthenticatedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(NotAuthenticatedException.class);
	
	public NotAuthenticatedException(Throwable t)
	{
		logger.error("000000003", t);
	}
	public NotAuthenticatedException()
	{
		logger.error("000000003", new Throwable());
	}
}
