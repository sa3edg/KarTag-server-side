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
import com.kartag.server.model.Reply;

public class ReplyDAO extends BaseDao{
	
	public ArrayList<Reply> getMessageReplies(String uid) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Reply> rows = session.createCriteria(Reply.class)
					.add(Restrictions.eq("messageId", new Integer(uid)))
					.addOrder(Order.asc("time")).list();
			transaction.commit();
			ArrayList<Reply> allRows = new ArrayList<Reply>(rows);
			return allRows;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}
	public ArrayList<Reply> getUserMessagesUpdates(String uid, Date time) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
		    @SuppressWarnings("unchecked")
			List<Reply> rows = session.createCriteria(Reply.class)
					.add(Restrictions.eq("toUid", new BigInteger(uid)))
					.add(Restrictions.gt("time", time))
					.addOrder(Order.desc("time")).list();
			transaction.commit();
			Set<Reply> allRows = new HashSet<Reply>(rows);
			return new  ArrayList<Reply>(allRows);
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

}
