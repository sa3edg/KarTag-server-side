package com.kartag.common.dao;

//import org.slf4j.cal10n.LocLogger;



import com.kartag.common.exception.BadRuntimeException;
import com.kartag.common.logging.LocalLogger;
import com.kartag.common.model.IModel;

public final class DaoFactory {

	/** Singleton instance. */
	private static DaoFactory self = null;

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(DaoFactory.class);
	
	private static final String DAO_PACKAGE = "com.kartag.server.dao.";

	private DaoFactory()
	{
		
	}
	/**
	 * Singleton getter.
	 * 
	 * @return the singleton instance.
	 */
	public static DaoFactory getInstance() {
		if (self == null) {
			DaoFactory temp = new DaoFactory();
			self = temp;
		}
		return self;
	}
	 public static IDefaultDao create(IModel bean)
	   {
	      return getInstance().createDao(bean);
	   }
	 
	 /**
	    * The factory method to create a processor depending on the bean
	    * 
	    * @param bean the bean
	    * @throws BadArgumentException on unknown bean
	    */
	   public IDefaultDao createDao(IModel bean)
	   {
		   IDefaultDao dao = null;
		   if(bean.getDao() != null)
		   {
			   return bean.getDao();
		   }
		   
		   Class<?> clazz = (Class<?>)bean.getClass();
		   if (clazz != null)
		      {
		         try
		         {
		        	String name = clazz.getSimpleName();
		        	Class<?> daoClass = Class.forName(DAO_PACKAGE + name + "DAO");
		            dao = (IDefaultDao)daoClass.newInstance();
		         }
		         catch (Exception e)
		         {
		        	 logger.error("000000003", e.getMessage(), e);
		             throw new BadRuntimeException(e);
		         }
		      }

	      return dao;
	   }
}
