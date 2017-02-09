package com.kartag.server.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import com.kartag.business.util.PushNotificationUtils;
import com.kartag.common.dao.BaseDao;
import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.server.model.Notification;
import com.kartag.server.model.Reply;
import com.kartag.server.model.User;
import com.kartag.server.model.UserUpdates;

public class UserUpdatesDAO extends BaseDao {

	public UserUpdates getLastUserUpdates(String uid) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			UserUpdates updates = (UserUpdates) session.get(UserUpdates.class,
					new BigInteger(uid));
			transaction.commit();
			return updates;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public Set<UserUpdates> getUsersUpdates() throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<UserUpdates> rows = session.createCriteria(UserUpdates.class)
					.list();
			transaction.commit();
			Set<UserUpdates> userUpdates = new HashSet<UserUpdates>(rows);
			return userUpdates;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean updateLastNotificationTime(String uid, Date time)
			throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update UserUpdates set notificationsTime =:time "
							+ "where uid =:uid");
			query.setParameter("time", time);
			query.setParameter("uid", new BigInteger(uid));
			int rows = query.executeUpdate();
			transaction.commit();
			return (rows != 0) ? true : false;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean updateLastMessagesTime(String uid, Date time)
			throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update UserUpdates set messagesTime =:time "
							+ "where uid =:uid");
			query.setParameter("time", time);
			query.setParameter("uid", new BigInteger(uid));
			int rows = query.executeUpdate();
			transaction.commit();
			return (rows != 0) ? true : false;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean updateLastReplyTime(String uid, Date time) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("update UserUpdates set repliesTime =:time "
							+ "where uid =:uid");
			query.setParameter("time", time);
			query.setParameter("uid", new BigInteger(uid));
			int rows = query.executeUpdate();
			transaction.commit();
			return (rows != 0) ? true : false;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public boolean userUpdatesExist(String uid) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			UserUpdates cashedUser = (UserUpdates) session.get(
					UserUpdates.class, new BigInteger(uid));
			transaction.commit();
			return cashedUser == null ? false : true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public Map<User, Set<com.kartag.business.notification.gcm.Message>> getPushNotifications()
			throws Exception {
		Map<User, Set<com.kartag.business.notification.gcm.Message>> notifications = new HashMap<User, Set<com.kartag.business.notification.gcm.Message>>();
		MessageDAO messageDao = new MessageDAO();
		NotificationDAO notificationDao = new NotificationDAO();
		ReplyDAO replyDao = new ReplyDAO();
		Set<User> usersSet = new UserDAO().getAllRegisteredUsers();
		Map<BigInteger, User> users = new HashMap<BigInteger, User>();
		for (final User user : usersSet) {
			users.put(user.getUid(), user);
		}
		UserUpdatesDAO updateDao = new UserUpdatesDAO();
		Set<UserUpdates> updates = updateDao.getUsersUpdates();
		Map<BigInteger, UserUpdates> updatesMap = new HashMap<BigInteger, UserUpdates>();
		for (final UserUpdates update : updates) {
			updatesMap.put(update.getUid(), update);
		}

		for (final BigInteger uid : updatesMap.keySet()) {
			UserUpdates userUpdate = updatesMap.get(uid);
			ArrayList<com.kartag.server.model.Message> newMessages = null;
			ArrayList<Notification> newNotifications = null;
			ArrayList<Reply> newReplies = null;
			if (userUpdate.getMessagesTime() != null) {
				newMessages = messageDao.getUserMessagesUpdates(
						String.valueOf(uid), userUpdate.getMessagesTime());
			}
			if (userUpdate.getNotificationsTime() != null) {
				newNotifications = notificationDao.getUserNotificationsUpdates(
						String.valueOf(uid), userUpdate.getNotificationsTime());
			}
			if (userUpdate.getRepliesTime() != null) {
				newReplies = replyDao.getUserMessagesUpdates(
						String.valueOf(uid), userUpdate.getRepliesTime());
			}
			PushNotificationUtils.createUserNotifications(notifications,
					newMessages, newNotifications, newReplies,
					users, uid);
		}

		return notifications;
	}

}
