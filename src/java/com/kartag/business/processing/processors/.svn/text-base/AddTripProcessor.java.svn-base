package com.kartag.business.processing.processors;


import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.business.util.TripUtils;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.TripDao;

public class AddTripProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(AddTripProcessor.class);
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   TripDao dao = new TripDao();
		   boolean added= dao.addTrip(TripUtils.generatescheduledTrips(request.getParameters()));
		   
		   String result = String.valueOf(added);
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