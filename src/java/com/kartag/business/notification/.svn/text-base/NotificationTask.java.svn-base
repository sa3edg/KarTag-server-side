package com.kartag.business.notification;

import com.kartag.common.logging.LocalLogger;

public class NotificationTask implements Runnable{

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(NotificationTask.class);
	@Override
	public void run() {
		try
		{
			NotificationService.getInstance().notifyAllUsers();
		}
		catch(Exception ex)
		{
			logger.error("000000003", ex.getMessage(), ex);
		}
	}

}
