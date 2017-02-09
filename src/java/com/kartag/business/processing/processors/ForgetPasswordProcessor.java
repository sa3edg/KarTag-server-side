package com.kartag.business.processing.processors;


import java.math.BigInteger;

import com.kartag.business.common.Response;
import com.kartag.business.community.CommunityHandler;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.business.security.Encryptor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.UserDAO;
import com.kartag.server.model.User;

public class ForgetPasswordProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(ForgetPasswordProcessor.class);
	private static final String UID = "uid";
	private static final String EMAIL = "communityEmail";
	@Override
	public Response process() {
		
		Response response = new Response();
		try
		{
		   UserDAO dao = new UserDAO();
		   
		   User user = new User();
		   user.setUid(new BigInteger(request.getParameters().get(UID)));
		   user.setCommunityEmail(request.getParameters().get(EMAIL));
		   User cashedUser= dao.getPassword(user);
		   boolean succeed = false;
		   if (cashedUser != null
					&& cashedUser.getUid().equals(user.getUid())
					&& cashedUser.getCommunityEmail().equals(
							user.getCommunityEmail())) {
			   cashedUser.setCommunityPassword(Encryptor.getInstance().decrypt(cashedUser.getCommunityPassword()));
			   String sent = CommunityHandler.getInstance().sendPasswordViaEmail(cashedUser);
			   if(User.YOUR_PASSWORD_SENT_SUCCESSFULLY.equals(sent)){
				   succeed = true;
			   }
		   }
		   String result = String.valueOf(succeed);
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