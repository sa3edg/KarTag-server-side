package com.kartag.common.util;

import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.Query;

import com.kartag.common.dao.jdbc.JQuery;
import com.kartag.common.model.IModel;

public class DAOUtil {
	
	public static void bindInsertQuery(Query query, String[] fields, String[] values) throws Exception
	{
		int i = 0;
		for(String fieldName : fields)
		{
			query.setParameter(fieldName, values[i]);
			i++;
		}
	}
	
	public static void bindUpdateQuery(Query query, IModel bean, LinkedHashMap<String, Object> updateCriteria) throws Exception
	{
		Map<String, Object> properties = BeanUtil.getBeanFields(bean);
		int k = 1;
		for(String fieldName : properties.keySet())
		{
			Object fieldValue = properties.get(fieldName);
			query.setParameter(k, fieldValue);
			k++;
		}
		int i = 1;
		for(Object obj : updateCriteria.values())
		{
			query.setParameter(i + k, obj);
			i++;
		}
	}
	
	public static void bindSelectQuery(Query query, LinkedHashMap<String, Object> updateCriteria) throws Exception
	{
		for(String parm : updateCriteria.keySet())
		{
			query.setParameter(parm , updateCriteria.get(parm));
		}
	}
	
	public static void bindExecuteQuery(PreparedStatement statement, JQuery query) throws Exception
	{
		int i = 1;
		for(Object parm : query.getQueryParameters())
		{
			   statement.setObject(i, parm);
			   i++;
		}
	}

}
