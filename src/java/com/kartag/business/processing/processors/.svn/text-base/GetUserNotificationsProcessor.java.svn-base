package com.kartag.business.processing.processors;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.kartag.business.common.Response;
import com.kartag.business.processing.FileOrderProcessor;
import com.kartag.common.logging.LocalLogger;
import com.kartag.server.dao.NotificationDAO;
import com.kartag.server.dao.UserUpdatesDAO;
import com.kartag.server.model.Message;
import com.kartag.server.model.Notification;
import com.kartag.server.model.UserUpdates;

public class GetUserNotificationsProcessor extends FileOrderProcessor {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(GetUserNotificationsProcessor.class);
	private final static String USER_ID_PARAM = "userId";

	@Override
	public Response process() {

		Response response = new Response();
		try {
			NotificationDAO dao = new NotificationDAO();
			UserUpdatesDAO updateDao = new UserUpdatesDAO();
			String uid = getRequest().getParameters().get(USER_ID_PARAM);
			ArrayList<Notification> messages = dao.getUserNotifications(uid);
			if (messages.size() > 0) {
				Date lastNotificationTime = messages.get(0)
						.getNotificationTime();
				if (updateDao.userUpdatesExist(uid)) {
					updateDao.updateLastNotificationTime(uid,
							lastNotificationTime);
				} else {
					UserUpdates updates = new UserUpdates();
					updates.setUid(new BigInteger(uid));
					updates.setNotificationsTime(lastNotificationTime);
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