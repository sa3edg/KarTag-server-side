package com.kartag.business.processing.processors;


import java.math.BigInteger;

import com.kartag.business.common.Response;
import com.kartag.business.community.CommunityHandler;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.UserDAO;
import com.kartag.server.model.User;

public class ChangePasswordProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(ChangePasswordProcessor.class);
	private static final String UID = "uid";
	private final static String NEW_PASSWORD = "newPassword";
	private final static String OLD_PASSWORD = "oldPassword";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   UserDAO dao = new UserDAO();
		   
		   User user = new User();
		   user.setUid(new BigInteger(request.getParameters().get(UID)));
		   user.setCommunityPassword(request.getParameters().get(OLD_PASSWORD));
		   boolean changed = dao.changePassword(user,request.getParameters().get(OLD_PASSWORD),  request.getParameters().get(NEW_PASSWORD));
		   String result = String.valueOf(false);
		   if(changed){
			   User cashedUser= dao.getPassword(user);
			   String sent = CommunityHandler.getInstance().sendPasswordChangeViaEmail(cashedUser);
			   if(User.YOUR_PASSWORD_CHANGED_SUCCESSFULLY.equals(sent)){
				   result = String.valueOf(true);
			   }else{
				   result = String.valueOf(false);
			   }
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