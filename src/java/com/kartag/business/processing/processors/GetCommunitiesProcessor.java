package com.kartag.business.processing.processors;

import java.util.ArrayList;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.CommunityDAO;
import com.kartag.server.model.Community;

public class GetCommunitiesProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetCommunitiesProcessor.class);
	private final static String COUNTRY_ID_PARAM = "countryId";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   CommunityDAO dao = new CommunityDAO();
		   ArrayList<Community> communities = dao.getCountryCommunities(getRequest().getParameters().get(COUNTRY_ID_PARAM));
		   
		   String result = new Community().getArrayAsJSON(communities);
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