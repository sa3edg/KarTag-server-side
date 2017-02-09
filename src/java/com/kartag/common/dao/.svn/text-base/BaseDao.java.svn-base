package com.kartag.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.kartag.common.model.*;
import com.kartag.common.util.DAOUtil;

public abstract class BaseDao extends HQLQueryGenerator implements IDefaultDao {

	protected Session session = null;

	protected Transaction transaction = null;

	protected IDefaultDao dao = null;

	protected String mappingType = PersistenceSessionFactory.ANNOTATION_MAPPING;

	/**
	 * Method for initialize dao.
	 */
	public void init() {

	}

	public IDefaultDao getDao() {
		return dao;
	}

	public void setDao(IDefaultDao dao) {
		this.dao = dao;
	}

	/**
	 * @sets session object
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * @sets transaction object
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * execute sql query to fetch the stored data
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public IModel[] executeQuery(IModel baseObj) throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			List<IModel> allRows = new ArrayList<IModel>();
			@SuppressWarnings("unchecked")
			List<? extends IModel> rows = session.createCriteria(
					baseObj.getClass()).list();
			transaction.commit();
			for (IModel e : rows) {
				allRows.add(e);
			}

			IModel[] result = new IModel[allRows.size()];
			return (IModel[]) allRows.toArray(result);
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * list all stored rows
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<? extends IModel> list(IModel baseObj,
			LinkedHashMap<String, String> filters) throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			List<? extends IModel> rows = session.createCriteria(
					baseObj.getClass()).list();
			transaction.commit();
			ArrayList<? extends IModel> duplicatedROws = new ArrayList<IModel>(
					rows);
			Set<? extends IModel> filteredSet = new HashSet<IModel>(
					duplicatedROws);
			ArrayList<? extends IModel> filteredList = new ArrayList<IModel>(
					filteredSet);
			return filteredList;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * list all stored rows
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public ArrayList<? extends IModel> list(IModel baseObj, String propretyId,
			ArrayList<?> values) throws SQLException {

		try {

			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();

			Criteria criteria = session.createCriteria(baseObj.getClass());
			criteria = criteria.add(Restrictions.in(propretyId, values));

			@SuppressWarnings("unchecked")
			List<? extends IModel> rows = criteria.list();
			transaction.commit();
			ArrayList<? extends IModel> allRows = new ArrayList<IModel>(rows);
			return allRows;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * select row by primary key
	 * 
	 * @param primaryKey
	 *            the primary key
	 * @return
	 */
	public IModel selectByPrimaryKey(IModel model, Object id)
			throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			// find entity by HQL query
			// Query query = session.createQuery(selectByPrimaryKeyHQL(model,
			// id));
			// query.setParameter("id", id);
			// IModel obj = (IModel)query.uniqueResult();

			IModel obj = (IModel) session.get(model.getClass(),
					createPrimaryKey(id));
			transaction.commit();
			return obj;
		}

		finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * store object into data base
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public boolean store(IModel baseObj) throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			session.save(baseObj);
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * store object into data base
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public boolean storeHashedField(IModel baseObj, String[] fields,
			String[] values, String fieldName) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(insertHashedField(baseObj,
					fields, fieldName));
			query.addEntity(baseObj.getClass());
			DAOUtil.bindInsertQuery(query, fields, values);
			int success = query.executeUpdate();
			transaction.commit();
			if (success > 0)
				return true;

		} finally {
			PersistenceSessionFactory.closeSession();
		}
		return false;
	}

	/**
	 * update object
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public boolean editHashedField(IModel baseObj, String fieldName,
			LinkedHashMap<String, Object> updateCriteria) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			Query query = session.createSQLQuery(updateHashedField(baseObj,
					fieldName, updateCriteria));
			DAOUtil.bindUpdateQuery(query, baseObj, updateCriteria);
			int success = query.executeUpdate();
			transaction.commit();
			if (success > 0)
				return true;

		} finally {
			PersistenceSessionFactory.closeSession();
		}
		return false;
	}

	/**
	 * retrieve object
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public IModel retrieveHashedField(IModel baseObj, String fieldName,
			LinkedHashMap<String, Object> selectCriteria) throws Exception {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(selectHashedField(baseObj,
					fieldName, selectCriteria));
			// query.set
			query.addEntity(baseObj.getClass());
			DAOUtil.bindSelectQuery(query, selectCriteria);

			IModel model = (IModel) query.uniqueResult();
			transaction.commit();
			return model;

		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * execute delete query
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public boolean delete(IModel baseObj) throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			session.delete(baseObj);
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * execute delete query
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public boolean deleteAllRows(IModel baseObj) throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			Query query = session.createQuery(deleteAllHQL(baseObj));
			int result = query.executeUpdate();
			if (result > 0)
				return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
		return false;
	}

	/**
	 * execute update query
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public boolean update(IModel baseObj) throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			session.update(baseObj);
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * execute update query
	 * 
	 * @param baseObj
	 *            the base object
	 * @return
	 */
	public boolean updateByPrimaryKey(String primaryKey) throws SQLException {
		try {
			resetSession();
			session = PersistenceSessionFactory.currentSession(mappingType);
			transaction = session.beginTransaction();
			IModel obj = (IModel) session.get(IModel.class, primaryKey);
			session.update(obj);
			transaction.commit();
			return true;
		} finally {
			PersistenceSessionFactory.closeSession();
		}
	}

	/**
	 * reset current session
	 * 
	 */
	public void resetSession() {
		this.session = null;
		this.transaction = null;
	}

	private Serializable createPrimaryKey(Object pk) {
		if (pk instanceof String) {
			return new String((String) pk);
		} else if (pk instanceof Integer) {
			return new Integer((Integer) pk);
		}
		return null;
	}

}
