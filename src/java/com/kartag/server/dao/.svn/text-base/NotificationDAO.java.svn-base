package com.kartag.server.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.kartag.common.dao.BaseDao;
import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.util.DateUtil;
import com.kartag.server.model.Notification;

public class NotificationDAO extends BaseDao{
	
	public ArrayList<Notification> getUserNotifications(String uid) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Notification> notifications =  session
					.createQuery(
							"from Notification where toUid = :uid and tripTime >= :time  and ( status = "+"'" + Notification.REQUEST_SENT_STATUS +
							"'" +" or (status in('" + Notification.ACCEPTED_STATUS + "', '" + Notification.REJECTED_STATUS +"') and type = '"+Notification.REPLY_REQUEST_TYPE+"' )) ORDER BY notificationTime DESC")
					.setParameter("uid", new BigInteger(uid))
					.setParameter("time", DateUtil.getValidTripsTime()).list();
			transaction.commit();
			ArrayList<Notification> allRows = new ArrayList<Notification>(notifications);
			return allRows;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public ArrayList<Notification> getUserNotificationsUpdates(String uid, Date time) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
		    @SuppressWarnings("unchecked")
			List<Notification> rows = session.createCriteria(Notification.class)
					.add(Restrictions.eq("toUid", new BigInteger(uid)))
					.add(Restrictions.gt("notificationTime", time))
					.addOrder(Order.desc("notificationTime")).list();
			transaction.commit();
			Set<Notification> allRows = new HashSet<Notification>(rows);
			return new  ArrayList<Notification>(allRows);
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
//	public boolean addUser(LinkedHashMap<String, String> fields) throws Exception {
//
//		try {
//			resetSession();
//			session = PersistenceSessionFactory.currentSession(mappingType);
//			transaction = session.beginTransaction();
//			User user = new User(fields);
//			User cashedUser = (User)session.get(User.class, user.getUid());
//			if(cashedUser == null)
//			{
//		       session.save(user);
//			}
//		    if(user.getLocation() != null)
//		    {
//		       session.save(user.getLocation());
//		    }
//			transaction.commit();
//			return true;
//		} finally {
//			PersistenceSessionFactory.closeSession();
//		}
//	}

}
