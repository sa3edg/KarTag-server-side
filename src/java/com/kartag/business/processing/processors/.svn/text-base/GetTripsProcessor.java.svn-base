package com.kartag.business.processing.processors;

import java.util.ArrayList;
import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.PoolDao;
import com.kartag.server.model.Pool;

public class GetTripsProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetTripsProcessor.class);
	private final static String TRIP_TYPE_PARAM = "type";
	private final static String COUNTRY_ID_PARAM = "countryId";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   PoolDao dao = new PoolDao();
		   String countryId = getRequest().getParameters().get(COUNTRY_ID_PARAM);
		   if(countryId == null)
		   {
			   countryId = "11";
		   }
		   ArrayList<Pool> trips = dao.getValidPoolsTrips(
				   getRequest().getParameters().get(TRIP_TYPE_PARAM),
				   countryId
				   );
		   
		   String result = new Pool().getArrayAsJSON(trips);
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