package com.kartag.common.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.kartag.common.model.IModel;

/**
 * 
 * @author SG
 */
public abstract class SQLQueryGenerator {

	public String select(IModel entityBean, String[] criteriass) {
		String restriction = "";
		for (int i = 0; i < criteriass.length; i++) {
			restriction += criteriass[i] + "=?";
			if (criteriass.length > 1 && i < criteriass.length - 1)
				restriction += " and ";
		}
		String query = "select * from " + entityBean.getTableName() + " where "
				+ restriction;
		return query;
	}

	public String selectNotEqual(IModel entityBean, String[] criteriass) {
		String restriction = "";
		for (int i = 0; i < criteriass.length; i++) {
			restriction += criteriass[i] + "!=?";
			if (criteriass.length > 1 && i < criteriass.length - 1)
				restriction += " and ";
		}
		String query = "select * from " + entityBean.getTableName() + " where "
				+ restriction;
		return query;
	}

	public String selectAll(IModel entityBean) {
		String query = "select * from " + entityBean.getTableName();
		System.out.println(query);
		return query;
	}

	public String insert(IModel entityBean) {
		return null;
	}

	public String update(IModel entityBean, String[] columns,
			String[] criteriass) {
		String restriction = "";
		String cols = " set ";
		for (int i = 0; i < criteriass.length; i++) {
			restriction += criteriass[i] + "=?";
			if (criteriass.length > 1 && i < criteriass.length - 1)
				restriction += " and ";
		}
		for (int i = 0; i < columns.length; i++) {
			cols += columns[i] + "=?";
			if (columns.length > 1 && i < columns.length - 1)
				cols += ",";
		}
		String query = "update " + entityBean.getTableName() + cols + " where "
				+ restriction;
		System.out.println("query ===" + query);
		return query;
	}

	public String delete(IModel entityBean, String[] criteriass) {
		String restriction = "";
		for (int i = 0; i < criteriass.length; i++) {
			restriction += criteriass[i] + "=?";
			if (criteriass.length > 1 && i < criteriass.length - 1)
				restriction += " and ";
		}
		String query = "delete from " + entityBean.getTableName() + " where " + restriction;
		System.out.println("delete query == " + query);
		return query;
	}

	public String deleteAll(IModel entityBean) {
		String query = "delete from " + entityBean.getTableName();
		return query;
	}

	public String insertHashedField(IModel entityBean,String[] fields, String fieldName) {
		String columns = "";
		String args = "";
//		ArrayList<String> properties = new ArrayList<String>();
//		for (Field f : entityBean.getClass().getDeclaredFields()) {
//			Annotation[] annotations = f.getAnnotations();
//			
//			f.getAnnotations(annotations)
//			properties.add(f.getName());
//		}
		int k = 0;
		for (String name : fields) {

			columns += name;
			if (name.equals(fieldName))
				args += "MD5(:"+name+")";
			else
				args += ":"+name;
			if (fields.length > 1 && k < fields.length - 1) {
				columns += ",";
				args += ",";
			}
			k++;
		}
		String query = "insert into " + entityBean.getTableName() + " ( " + columns
				+ "  ) values(" + args + ")";
		return query;
	}
	
	public String updateHashedField(IModel entityBean, String fieldName,LinkedHashMap<String, Object> args ) {
		
		ArrayList<String> properties = new ArrayList<String>();
		for (Field f : entityBean.getClass().getDeclaredFields()) {
			properties.add(f.getName());
		}
		String restriction = "";
    	String cols = " set ";
    	int k = 0;
    	for(String name : args.keySet())
    	{
    		if(name.equals(fieldName))
    		   restriction += name + "=MD5(?)";
    		else
    		   restriction += name + "=?";
    		if(args.size() > 1 && k < args.size() - 1)
    			restriction +=" and ";
    		k++;
    	}
    	k = 0;
    	for(String name : properties)
    	{
    		if(name.equals(fieldName))
    		    cols += name + "=MD5(?)";
    		else
    			cols += name + "=?";
    		if(properties.size() > 1 && k < properties.size())
    			cols +=",";
    		
    		k++;
    	}
    	String query = "update " + entityBean.getTableName() + cols + " where "+restriction;
    	return query;
	}
	
	public String selectHashedField(IModel entityBean, String fieldName,LinkedHashMap<String, Object> args ) 
    {
    	String restriction = "";
		int k = 0;
		for(String name : args.keySet())
    	{
			if(name.equals(fieldName))
	    		   restriction += name + "=MD5(:" + name + ")";
	    		else
	    		   restriction += name + "=:"+name;
    		if(args.size() > 1 && k < args.size() - 1)
    			restriction +=" and ";
    		k++;
    	}
    	String query = "select * from " + entityBean.getTableName() + " where " + restriction;
    	return query;
    }
}
