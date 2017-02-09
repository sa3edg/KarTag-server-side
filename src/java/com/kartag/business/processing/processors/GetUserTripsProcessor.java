package com.kartag.business.processing.processors;

import java.util.ArrayList;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.TripDao;
import com.kartag.server.model.Trip;

public class GetUserTripsProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetUserTripsProcessor.class);
	private final static String TRIP_TYPE_PARAM = "type";
	private final static String USER_ID_PARAM = "userId";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
			TripDao dao = new TripDao();
		   //User user = (User)dao.selectByPrimaryKey(new User(), getRequest().getParameters().get(USER_ID_PARAM));
			ArrayList<Trip> trips = dao.getUserTrips(getRequest().getParameters().get(USER_ID_PARAM),
				   getRequest().getParameters().get(TRIP_TYPE_PARAM)
				   );
		   String result = new Trip().getArrayAsJSON(trips);
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