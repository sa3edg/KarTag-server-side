package com.kartag.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.beanutils.BeanUtils;

import com.kartag.common.model.IModel;

public class BeanUtil {
	public static IModel convertBeanPropertiesToUTF(IModel bean) throws Exception
	{
		Map<String, Object> properties = new HashMap<String, Object>();
		for (Field f : bean.getClass().getDeclaredFields()) {
		    Class<?> clazz = f.getType();
		    f.setAccessible(true);
		    if (clazz.equals(String.class)) {
		    	properties.put(f.getName(), convertValueToUTF8(((String)f.get(bean))));
		    }
		    else
		    {
			    properties.put(f.getName(), f.get(bean));
		    }
		}
		IModel convertedBean = bean.getClass().newInstance();
		BeanUtils.populate(convertedBean, properties);
		return convertedBean;
	}
	private static String convertValueToUTF8(String isoString) {

	    String utf8String = null;

	      if (null != isoString && !isoString.equals("")) {
	          try {
	                byte[] stringBytesISO = isoString.getBytes("ISO-8859-1"); 
	                utf8String = new String(stringBytesISO, "UTF-8"); 
	            }

	            catch(UnsupportedEncodingException e) {

	                  throw new RuntimeException(e); 
	            } 
	      } else {
	          utf8String = isoString; 
	      } return utf8String; 

	}
	
	public static Map<String, Object> getBeanFields(IModel bean) throws Exception
	{
		Map<String, Object> properties = new HashMap<String, Object>();
		for (Field f : bean.getClass().getDeclaredFields()) {
		    f.setAccessible(true);
		    properties.put(f.getName(), f.get(bean));
		}
		return properties;
	}
	
	public static Class<?> getBeanPrimaryKey(IModel bean) throws Exception
	{
		Field f = bean.getClass().getDeclaredFields()[0];
		return f.getType();
//		for (Field f : bean.getClass().getDeclaredFields()) {
//		    f.setAccessible(true);
//		    java.lang.annotation.Annotation[] ann = f.getAnnotations();
//		    for(Annotation an : ann)
//		    {
//		    	if(an.toString().equals("@id"))
//		    	{
//		    		
//		    	}
//		    }
//		}
	}
	
	public static ArrayList<? extends IModel> createModelFromRS(ResultSet rs, IModel model) throws Exception
	{
		ArrayList<IModel> models = new ArrayList<IModel>();
		while(rs.next())
		{
			IModel bean = IModel.class.newInstance();
			fillFieldValues(model, rs);
			models.add(bean);
		}
		return models;
	}
	
	public static ArrayList<? extends IModel> createModelFromRS(ArrayList<String> queryResult, ResultSet rs, IModel model) throws Exception
	{
		ArrayList<IModel> models = new ArrayList<IModel>();
		while(rs.next())
		{
			IModel bean = IModel.class.newInstance();
			fillFieldValues(queryResult, model, rs);
			models.add(bean);
		}
		return models;
	}
	private static IModel fillFieldValues(IModel model, ResultSet rs) throws Exception
	{
		int i = 0;
		for (Field f : model.getClass().getDeclaredFields()) {
		    f.setAccessible(true);
		    f.set(model, rs.getObject(i));
		}
		return model;
	}
	
	private static IModel fillFieldValues(ArrayList<String> queryResult,IModel model, ResultSet rs) throws Exception
	{
		int i = 0;
		for (Field f : model.getClass().getDeclaredFields()) {
		    f.setAccessible(true);
		    CharSequence sequence = queryResult.get(i);
		    if(f.getName().contains(sequence))
		    {
		       f.set(model, rs.getObject(i));
		    }
		}
		return model;
	}

}
