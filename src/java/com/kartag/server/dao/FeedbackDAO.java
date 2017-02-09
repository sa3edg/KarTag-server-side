package com.kartag.server.dao;

import java.util.LinkedHashMap;

import com.kartag.common.dao.BaseDao;
import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.server.model.User;

public class FeedbackDAO extends BaseDao{
	
	public boolean addUser(LinkedHashMap<String, String> fields) throws Exception {

		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			User user = new User(fields);
			User cashedUser = (User)session.get(User.class, user.getUid());
			if(cashedUser == null)
			{
		       session.save(user);
			}
		    if(user.getLocation() != null)
		    {
		       session.save(user.getLocation());
		    }
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

}
