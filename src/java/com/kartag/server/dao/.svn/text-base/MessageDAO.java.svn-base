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
import com.kartag.server.model.Message;

public class MessageDAO extends BaseDao {

	public ArrayList<Message> getUserMessages(String uid) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Message> rows = session.createCriteria(Message.class)
					.add(Restrictions.eq("toUid", new BigInteger(uid)))
					.addOrder(Order.desc("time")).list();
			transaction.commit();
			ArrayList<Message> allRows = new ArrayList<Message>(rows);
			return allRows;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	public ArrayList<Message> getUserMessagesUpdates(String uid, Date time) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
		    @SuppressWarnings("unchecked")
			List<Message> rows = session.createCriteria(Message.class)
					.add(Restrictions.eq("toUid", new BigInteger(uid)))
					.add(Restrictions.gt("time", time))
					.addOrder(Order.desc("time")).list();
			transaction.commit();
			Set<Message> allRows = new HashSet<Message>(rows);
			return new  ArrayList<Message>(allRows);
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	
	public boolean deleteMessage(String id) throws Exception {
		try {
			String[] parts = id.split(",");
			if(parts.length <= 0)
			{
				return false;
			}
			boolean successed = true;
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			for(int i = 0 ; i < parts.length ; i++)
			{
			   String deleteQuery = "delete from Message  where id =:id";  
			   int messages = session.createQuery(deleteQuery).setParameter("id", Integer.valueOf(parts[i])).executeUpdate();
			   String deleteRepliesQuery = "delete from Reply  where messageId =:id";  
			   int replies = session.createQuery(deleteRepliesQuery).setParameter("id", Integer.valueOf(parts[i])).executeUpdate();
			   successed =  (messages != 0 && replies != 0) ? true : false;
			   if(!successed)
			   {
				   break;
			   }
			}
			transaction.commit();
			return successed;
			
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

}
