package com.kartag.business.processing.processors;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.MessageDAO;
import com.kartag.server.dao.UserUpdatesDAO;
import com.kartag.server.model.Message;
import com.kartag.server.model.UserUpdates;

public class GetUserMessagesUpdatesProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetUserMessagesUpdatesProcessor.class);
	private final static String USER_ID_PARAM = "userId";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   MessageDAO dao = new MessageDAO();
		   UserUpdatesDAO updateDao = new UserUpdatesDAO();
		   String uid = getRequest().getParameters().get(USER_ID_PARAM);
		   UserUpdates updates = updateDao.getLastUserUpdates(uid);
		   ArrayList<Message> messages = new ArrayList<Message>();
		   if(updates != null)
		   {
			   if(updates.getMessagesTime() != null)
			   {
				   messages = dao.getUserMessagesUpdates(uid, updates.getMessagesTime());
			   }
			   Date lastMessageTime = messages.get(messages.size()-1).getTime();
			   if(updateDao.userUpdatesExist(uid))
			   {
				   updateDao.updateLastMessagesTime(uid, lastMessageTime);
			   }
			   else
			   {
				   UserUpdates userUpdates = new UserUpdates();
				   userUpdates.setUid(new BigInteger(uid));
				   userUpdates.setMessagesTime(lastMessageTime);
				   updateDao.store(userUpdates);
			   }
		   }
		   
		   String result = new Message().getArrayAsJSON(messages);
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