package com.kartag.business.processing.processors;

import com.kartag.business.common.Response;
import com.kartag.business.notification.NotificationService;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.MessageDAO;
import com.kartag.server.dao.UserDAO;
import com.kartag.server.model.Message;
import com.kartag.server.model.User;

public class AddMessageProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(AddMessageProcessor.class);
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   MessageDAO dao = new MessageDAO();
		   Message message = new Message(request.getParameters());
		   boolean added= dao.store(message);
		   
		   String result = String.valueOf(added);
		   User notificationUser = null;
		   if(result != null){
			   notificationUser = new UserDAO().getUserByUid(message.getToUid());
		   }
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
			   //notify user
			   if(message != null && notificationUser != null){
				   NotificationService.getInstance().notifyUser(message, notificationUser);
			   }
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