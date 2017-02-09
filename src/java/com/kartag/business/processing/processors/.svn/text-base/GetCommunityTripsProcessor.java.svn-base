package com.kartag.business.processing.processors;

import java.util.Set;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.TripDao;
import com.kartag.server.model.Trip;

public class GetCommunityTripsProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetTripsProcessor.class);
	private final static String TRIP_TYPE_PARAM = "type";
	private final static String COMMUNITY_ID_PARAM = "communityId";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   TripDao dao = new TripDao();
		   Set<Trip> trips = dao.getCommunityTrips(
				   getRequest().getParameters().get(TRIP_TYPE_PARAM),
				   getRequest().getParameters().get(COMMUNITY_ID_PARAM)
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