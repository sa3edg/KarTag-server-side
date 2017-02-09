package com.kartag.business.processing.processors;


import java.math.BigInteger;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.UserDAO;
import com.kartag.server.model.User;

public class CommunityLoginProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(CommunityLoginProcessor.class);
	private static final String UID = "uid";
	private static final String EMAIL = "communityEmail";
	private static final String PASSWORD = "password";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   UserDAO dao = new UserDAO();
		   
		   User user = new User();
		   user.setUid(new BigInteger(request.getParameters().get(UID)));
		   user.setCommunityEmail(request.getParameters().get(EMAIL));
		   user.setCommunityPassword(request.getParameters().get(PASSWORD));
		   boolean added= dao.login(user);
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