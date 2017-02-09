package com.kartag.business.processing.processors;


import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.MessageDAO;
import com.kartag.server.dao.NotificationDAO;
import com.kartag.server.dao.ReplyDAO;
import com.kartag.server.dao.UserUpdatesDAO;
import com.kartag.server.model.UserUpdates;

public class GetUserNewMessagesNotificationsContProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetUserNewMessagesNotificationsContProcessor.class);
	private final static String USER_ID_PARAM = "userId";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   String uid = getRequest().getParameters().get(USER_ID_PARAM);
		   MessageDAO dao = new MessageDAO();
		   NotificationDAO notificationDao = new NotificationDAO();
		   ReplyDAO replyDao = new ReplyDAO();
		   UserUpdatesDAO updateDao = new UserUpdatesDAO();
		   UserUpdates updates = updateDao.getLastUserUpdates(uid);
		   int newMessagesCount = 0;
		   int newNotificationCount = 0;
		   int newReplies = 0;
		   if(updates == null)
		   {
			   newMessagesCount = 0;
			   newNotificationCount = 0;
			   newReplies = 0;
		   }
		   else if(updates.getMessagesTime() != null)
		   {
			   newMessagesCount = dao.getUserMessagesUpdates(uid, updates.getMessagesTime()).size();
		   }
		   else if(updates.getNotificationsTime() != null)
		   {
			   newMessagesCount = notificationDao.getUserNotificationsUpdates(uid, updates.getNotificationsTime()).size();
		   }
		   else if(updates.getNotificationsTime() != null)
		   {
			   newReplies = replyDao.getUserMessagesUpdates(uid, updates.getNotificationsTime()).size();

		   }
		   
		   String result = "\"messages="+newMessagesCount+",notifications="+newNotificationCount
		                              +",replies="+newReplies+"\"";
		  
		   response.setStatus(Response.RESPONSE_STATUS_SUCCESS);
		   response.setResponse(result);
		   response.mergeResponse(response);
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