package com.kartag.common.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.hibernate.Criteria;


import com.kartag.common.dao.BaseDao;
import com.kartag.common.dao.IDefaultDao;
import com.kartag.common.dao.PersistenceSessionFactory;
import com.kartag.common.model.*;
import com.kartag.common.util.BeanUtil;
import com.kartag.common.util.DAOUtil;

public abstract class BaseJDBCDao extends BaseDao{

	
	protected Connection connection = null;
    protected PreparedStatement statement = null;
    protected ResultSet rs = null;
	
	
	
	/**
	    * Method for initialize dao.
	    */
	   public void init()
	   {
		   
	   }
	   
	   public IDefaultDao getDao()
	   {
		   return dao;
	   }
	   
	   public void setDao(IDefaultDao dao)
	   {
		   this.dao = dao;
	   }
	   
	   public ArrayList<? extends IModel> executeQuery(JQuery query, IModel model) throws Exception
		{
			try
			{
				connection = JDBCSessionFactory.currentSession();
				statement = connection.prepareStatement(query.getQuery());
				DAOUtil.bindExecuteQuery(statement, query);
				rs = statement.executeQuery();
				if(query.getQueryResult().size() > 0)
				{
				   return BeanUtil.createModelFromRS(query.getQueryResult(), rs, model);
				}
				else
				{
					return BeanUtil.createModelFromRS(rs, model);
				}
			}
			finally
			{
				JDBCSessionFactory.closeSession();
				close();
			}
		}
	   
	   public int executeUpdate(JQuery query, IModel model) throws Exception
		{
			try
			{
				connection = JDBCSessionFactory.currentSession();
				statement = connection.prepareStatement(query.getQuery());
				DAOUtil.bindExecuteQuery(statement, query);
				return statement.executeUpdate();
			}
			finally
			{
				JDBCSessionFactory.closeSession();
				close();
			}
		}
	   
	   public void close() throws SQLException
	    {
	        if(statement != null)
	            statement.close();
		    if(rs != null)
	            rs.close();
	    }
	   public boolean isExistParamter(LinkedHashMap<String, String> filters, String paramterName){
			String paramter = filters.get(paramterName);
			if(paramter!=null && paramter.length()>0)
				return true;
			return false;
		}
	   
	   public Criteria getCriteria(IModel baseObj){
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			return session.createCriteria(baseObj.getClass());
		}

}
