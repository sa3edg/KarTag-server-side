package com.kartag.common.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kartag.common.model.IModel;

/**
 * common interface for all business object data access objects with db connection.
 * 
 */
public interface IDefaultDao
{
   /** CVS version id. */
   public static final String RCSID = "$Id: IJdbcDao.java,v 1.5 2010/03/16 07:45:46 mskuta Exp $";

   /**
    * Method for initialize dao.
    */
   public void init();
   
   /**
    * @sets session object
    */
   public void setSession(Session session);
   
   /**
    * @sets transaction object
    */
   public void setTransaction(Transaction transaction);
   
   /**
    * execute sql query to fetch the stored data
    * @param baseObj the base object
    * @return
    */
   public IModel[] executeQuery(IModel baseObj)throws SQLException;
   
   /**
    * list all stored rows
    * @param baseObj the base object
    * @return
    */
   public ArrayList<? extends IModel> list(IModel baseObj, LinkedHashMap<String, String> filters)throws SQLException;
   
   /**
    * list all stored rows
    * @param baseObj the base object
    * @return
    */
   public ArrayList<? extends IModel> list(IModel baseObj,String propretyId, ArrayList<?> filters)throws SQLException;
   
   /**
    * list all stored rows
    * @param baseObj the base object
    * @return
    */
   public IModel selectByPrimaryKey(IModel model, Object id)throws SQLException;
   
   /**
    * store object into data base
    * @param baseObj the base object
    * @return
    */
   public boolean store(IModel baseObj)throws SQLException;

   /**
    * execute delete query
    * @param baseObj the base object
    * @return
    */
   public boolean delete(IModel baseObj)throws SQLException;

   /**
    * execute update query
    * @param baseObj the base object
    * @return
    */
   public boolean update(IModel baseObj)throws SQLException;
     

   /**
    * execute delete all query
    * @param baseObj the base object
    * @return
    */
   public boolean deleteAllRows(IModel baseObj) throws SQLException;
   
   /**
    * execute update query by primaryKey
    * @param baseObj the base object
    * @return
    */
   public boolean updateByPrimaryKey(String primaryKey)throws SQLException;
   
   public boolean storeHashedField(IModel baseObj, String[] fields, String[] values, String fieldName) throws Exception;
   
   public boolean editHashedField(IModel baseObj, String fieldName, LinkedHashMap<String, Object> updateCriteria) throws Exception;
   
   public IModel retrieveHashedField(IModel baseObj, String fieldName, LinkedHashMap<String, Object> selectCriteria) throws Exception;
   /**
    * reset current session
    *
    */
   public void resetSession();
}