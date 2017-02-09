package com.kartag.common.startup;

import static com.kartag.common.config.Constants.IS_PUSH_NOTIFICATION_ENABLED;
import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.kartag.business.notification.NotificationService;
import com.kartag.business.processing.ProcessorFactory;
import com.kartag.common.config.AppConfig;
import com.kartag.common.dao.jdbc.JDBCSessionFactory;
import com.kartag.common.logging.LocalLogger;
import com.kartag.common.text.TextProvider;

public class ApplicationInitializer implements PlugIn{

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(ApplicationInitializer.class);
	
	
	@Override
	public void init(ActionServlet arg0, ModuleConfig arg1)
			throws ServletException {
		try
		{
			AppConfig.init();
		    ProcessorFactory.getInstance();
		    JDBCSessionFactory.createSessionInstance();
		    TextProvider.init();
		    if(IS_PUSH_NOTIFICATION_ENABLED){
		    	NotificationService.getInstance();
		    }
		}
		catch(Exception ex)
		{
			logger.error("000000003", ex.getMessage(), ex);
		}
		
	}
	
	@Override
	public void destroy() {
		try
		{
		   JDBCSessionFactory.closeSession();
		}
		catch(Exception ex)
		{
			logger.error("000000003", ex.getMessage(), ex);
		}
	}


}
