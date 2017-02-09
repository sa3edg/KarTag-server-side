package com.kartag.business.controller;

import com.kartag.business.common.Request;
import com.kartag.business.common.Response;
import com.kartag.business.processing.IProcessor;
import com.kartag.business.processing.ProcessorFactory;
import com.kartag.common.logging.LocalLogger;

public class OrderFacad {
	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(OrderFacad.class);

	public static Response handleOrder(Request request)
	{
		Response response = null;
		IProcessor processor = null;
		try
		{
			
			processor = ProcessorFactory.create(request);
			processor.preprocess();
			response = processor.process();
			return response;
		}
		catch(Exception ex)
		{
			logger.error("000000003", ex.getMessage(), ex);
		}
		finally
		{
			if(processor != null)
				processor.terminate();
		}
		return null;
	}

}
