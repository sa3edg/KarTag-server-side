package com.kartag.business.processing.processors;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.ReplyDAO;
import com.kartag.server.dao.UserUpdatesDAO;
import com.kartag.server.model.Message;
import com.kartag.server.model.Reply;
import com.kartag.server.model.UserUpdates;

public class GetMessageRepliesProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetMessageRepliesProcessor.class);
	private final static String MESSAGE_ID_PARAM = "messageId";
	private final static String USER_ID_PARAM = "userId";

	@Override
	public Response process() {

		Response response = new Response();
		try {
			ReplyDAO dao = new ReplyDAO();
			UserUpdatesDAO updateDao = new UserUpdatesDAO();
			String messageId = getRequest().getParameters().get(
					MESSAGE_ID_PARAM);
			String uid = getRequest().getParameters().get(USER_ID_PARAM);
			ArrayList<Reply> messages = dao.getMessageReplies(messageId);
			if (messages.size() > 0) {
				Date lastMessageTime = messages.get(messages.size() - 1)
						.getTime();
				if (updateDao.userUpdatesExist(uid)) {
					updateDao.updateLastReplyTime(uid, lastMessageTime);
				} else {
					UserUpdates updates = new UserUpdates();
					updates.setUid(new BigInteger(uid));
					updates.setRepliesTime(lastMessageTime);
					updateDao.store(updates);
				}
			}
			String result = new Message().getArrayAsJSON(messages);
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