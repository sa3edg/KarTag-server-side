package com.kartag.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.dao.jdbc.BaseJDBCDao;
import com.kartag.common.model.IModel;
import com.kartag.server.presentation.action.SystemUserActions;

public class UserChannelDAO extends BaseJDBCDao{

	
	/**
	 * list all stored rows
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public ArrayList<? extends IModel> list(IModel baseObj, LinkedHashMap<String, String> filters)throws SQLException
	   {
		   try
		   {
			  
		      resetSession();
		      session = PersistenceSessionFactory.currentSession(mappingType);
		      transaction = session.beginTransaction();
		      Criteria criteria = session.createCriteria(baseObj.getClass());
		      
		      criteria= criteria.add( Restrictions.eq("userName", filters.get(SystemUserActions.userId_req_param_name)));
		      List<? extends IModel> rows = criteria.list();
		      transaction.commit();
		      ArrayList<? extends IModel> allRows = new ArrayList<IModel>(rows);
		      return allRows;
		   }
		   finally
		   {
			   PersistenceSessionFactory.closeSession();
		   }
	   }


}
