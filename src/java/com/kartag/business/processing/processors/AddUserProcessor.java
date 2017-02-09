package com.kartag.business.processing.processors;

import static com.kartag.common.config.Constants.APP_COMMUNITY_VALIDATION_ENABLED;
import com.kartag.business.common.Response;
import com.kartag.business.community.CommunityHandler;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.business.validation.CommunityValidator;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.UserDAO;
import com.kartag.server.model.User;

public class AddUserProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger
			.getLogger(AddUserProcessor.class);

	@Override
	public Response process() {

		Response response = new Response();
		try {
			UserDAO dao = new UserDAO();

			User user = new User(request.getParameters(), request.getSource());
			boolean added = dao.addUser(user);
			String communityPassword = "";
			boolean communityPasswordSaved = false;
			if (added) {
				User savedUser = null;
				if ((user.getCommunityId() != 0 && !"".equals(user
						.getCommunityId()))
						&& user.getCommunityEmail() != null
						&& !"".equals(user.getCommunityEmail())){
					savedUser = dao.getUserByUid(user.getUid());
				}
				if ((user.getCommunityId() != 0 && !"".equals(user
						.getCommunityId()))
						&& user.getCommunityEmail() != null
						&& !"".equals(user.getCommunityEmail())
						&& savedUser != null
						&& (savedUser.getCommunityPassword() == null || "".equals(savedUser.getCommunityPassword()))) {
					if(APP_COMMUNITY_VALIDATION_ENABLED){
						if(new CommunityValidator().isCommunityUser(user)){
							communityPassword = CommunityHandler.getInstance().sendGeneratedPasswordViaEmail(user);
							if(!User.YOUR_PASSWORD_SENT_FAILED.equals(communityPassword)){
								communityPasswordSaved = dao.updateCommunityPassword(user, communityPassword);
							}
						}else{
							communityPasswordSaved = false;
						}
					}else{
						communityPassword = CommunityHandler.getInstance().sendGeneratedPasswordViaEmail(user);
						if(!User.YOUR_PASSWORD_SENT_FAILED.equals(communityPassword)){
							communityPasswordSaved = dao.updateCommunityPassword(user, communityPassword);
						}
					}
				}
			}
			String result="";
			if("".equals(communityPassword)){
				result = String.valueOf(added);
			}
			else if(added && !User.YOUR_PASSWORD_SENT_FAILED.equals(communityPassword) && communityPasswordSaved){
				result = String.valueOf(added);
			}else {
				result = String.valueOf(false);
			}
			if (result == null) {
				response.setStatus(Response.RESPONSE_STATUS_ERROR);
				response.createErrorNode(response);
			} else {
				response.setStatus(Response.RESPONSE_STATUS_SUCCESS);
				response.setResponse(result);
				response.mergeResponse(response);
			}
			return response;

		} catch (Exception ex) {
			response.setStatus(Response.RESPONSE_STATUS_ERROR);
			response.createErrorNode(response);
			logger.error("000000003", ex.getMessage(), ex);
		}
		return response;
	}
}