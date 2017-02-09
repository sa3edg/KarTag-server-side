package com.kartag.business.processing.processors;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.FeedbackDAO;
import com.kartag.server.model.Feedback;

public class AddFeedbackProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(AddFeedbackProcessor.class);
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   FeedbackDAO dao = new FeedbackDAO();
		   boolean added= dao.store(new Feedback(request.getParameters()));
		   
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