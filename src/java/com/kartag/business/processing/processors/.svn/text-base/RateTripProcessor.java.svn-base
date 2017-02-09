package com.kartag.business.processing.processors;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.TripDao;

public class RateTripProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(RateTripProcessor.class);
	private final static String Trip_ID_PARAM = "tripId";
	private final static String RATING_PARAM = "rating";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   TripDao dao = new TripDao();
		   boolean rated= dao.rateTrip(request.getParameters().get(Trip_ID_PARAM), 
				   request.getParameters().get(RATING_PARAM));
		   
		   String result = String.valueOf(rated);
		   if(result == null)
		   {
			   response.setStatus(Response.RESPONSE_STATUS_ERROR);
			   response.createErrorNode(response);
		   }
		   else
		   {
			   response.setStatus(Response.RESPONSE_STATUS_SUCCESS);
			   response.setResponse(result);
			   response.mergeResponse(response);
		   }
	       return response;



		}
		catch(Exception ex)
		{
			response.setStatus(Response.RESPONSE_STATUS_ERROR);
			response.createErrorNode(response);
			logger.error("000000003", ex.getMessage(), ex);
		}
		return response;
	}
}