package com.kartag.business.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.kartag.business.notification.NotificationService;
import com.kartag.common.model.IModel;
import com.kartag.common.text.TextProvider;
import com.kartag.server.model.Notification;
import com.kartag.server.model.Reply;
import com.kartag.server.model.User;

public class PushNotificationUtils {
	public static Map<User, Set<com.kartag.business.notification.gcm.Message>> createUserNotifications(
			Map<User, Set<com.kartag.business.notification.gcm.Message>> notifications,
			ArrayList<com.kartag.server.model.Message> newMessages,
			ArrayList<Notification> newNotifications,
			ArrayList<Reply> newReplies, Map<BigInteger, User> users,
			BigInteger uid) {
		User user = users.get(uid);
		if (newMessages != null) {
			for (final com.kartag.server.model.Message message : newMessages) {
				com.kartag.business.notification.gcm.Message notification = createMessagePushNotification(
						message);
				Set<com.kartag.business.notification.gcm.Message> set = notifications
						.get(user);
				if (set == null) {
					set = new HashSet<com.kartag.business.notification.gcm.Message>();
				}
				set.add(notification);
				notifications.put(user, set);
			}
		}
		if (newNotifications != null) {
			for (final Notification notification : newNotifications) {
				com.kartag.business.notification.gcm.Message pushNotification = createNotificationPushNotification(
						notification);
				Set<com.kartag.business.notification.gcm.Message> set = notifications
						.get(user);
				if (set == null) {
					set = new HashSet<com.kartag.business.notification.gcm.Message>();
				}
				set.add(pushNotification);
				notifications.put(user, set);
			}
		}
		if (newReplies != null) {
			for (final Reply reply : newReplies) {
				com.kartag.business.notification.gcm.Message notification = createReplyPushNotification(
						reply);
				Set<com.kartag.business.notification.gcm.Message> set = notifications
						.get(user);
				if (set == null) {
					set = new HashSet<com.kartag.business.notification.gcm.Message>();
				}
				set.add(notification);
				notifications.put(user, set);
			}
		}
		return notifications;
	}
	
	public static com.kartag.business.notification.gcm.Message createMessagePushNotification(IModel model)
	{
		com.kartag.business.notification.gcm.Message notification = null;
		if(model instanceof com.kartag.server.model.Message){
			notification = createMessagePushNotification((com.kartag.server.model.Message)model);
		}else if(model instanceof com.kartag.server.model.Reply){
			notification = createReplyPushNotification((Reply)model);
		}else if(model instanceof Notification){
			notification = createNotificationPushNotification((Notification)model);
		}
		return notification;
	}

	private static com.kartag.business.notification.gcm.Message createMessagePushNotification(
			com.kartag.server.model.Message message) {
		String body = TextProvider.getText(
				"app.message.push.notification.body",
				new String[] { message.getFromName() });
		com.kartag.business.notification.gcm.Message notification = new com.kartag.business.notification.gcm.Message.Builder()
				.collapseKey("1").timeToLive(3).delayWhileIdle(true)
				.addData(NotificationService.MESSAGE_KEY, body).build();
		return notification;
	}

	private static com.kartag.business.notification.gcm.Message createReplyPushNotification(
			Reply reply) {
		String body = TextProvider.getText("app.reply.push.notification.body",
				new String[] { reply.getFromName() });
		com.kartag.business.notification.gcm.Message notification = new com.kartag.business.notification.gcm.Message.Builder()
				.collapseKey("1").timeToLive(3).delayWhileIdle(true)
				.addData(NotificationService.MESSAGE_KEY, body).build();
		return notification;
	}

	private static com.kartag.business.notification.gcm.Message createNotificationPushNotification(
			Notification notification) {
		String body = "";
		if (Notification.JOIN_REQUEST_TYPE.equals(notification.getType())) {
			body = TextProvider.getText(
					"app.notification.join.request.push.notification.body",
					new String[] { notification.getFromName() });
		} else if (Notification.REPLY_REQUEST_TYPE.equals(notification
				.getType())) {
			if (Notification.ACCEPTED_STATUS.equals(notification.getStatus())) {
				body = TextProvider
						.getText(
								"app.notification.accept.request.push.notification.body",
								new String[] { notification.getFromName() });
			} else if (Notification.REJECTED_STATUS.equals(notification
					.getStatus())) {
				body = TextProvider
						.getText(
								"app.notification.reject.request.push.notification.body",
								new String[] { notification.getFromName() });
			}
		}
		com.kartag.business.notification.gcm.Message pushNotification = new com.kartag.business.notification.gcm.Message.Builder()
				.collapseKey("1").timeToLive(3).delayWhileIdle(true)
				.addData(NotificationService.MESSAGE_KEY, body).build();
		return pushNotification;
	}
}
