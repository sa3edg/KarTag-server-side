package com.kartag.common.logging;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LocalLogger {

  private final static ResourceBundle bundle = ResourceBundle.getBundle("/resource/log-messages/log-messages");
 
  private static Logger logger = null;
  private LocalLogger(Class<?> clazz)
  {
	  logger = LoggerFactory.getLogger(clazz);
  }

  public static LocalLogger getLogger(Class<?> clazz)
  {
	  return new LocalLogger(clazz);
  }
  
  public void error(String key)
  {
	  logger.error(getBundleMessage(key));
  }
  public void error(String key, String parm)
  {
	  logger.error(getBundleMessage(key), parm);
  }
  public void error(String key, Throwable t)
  {
	  logger.error(getBundleMessage(key), t);
  }
  public void error(String key, String param, Throwable t)
  {
	  logger.error(getBundleMessage(key), param, t);
  }
  private String getBundleMessage(String key)
  {
	  return bundle.getString(key);
  }
  public void info(String key)
  {
	  logger.info(getBundleMessage(key));
  }
  public void info(String key, String parm)
  {
	  logger.info(getBundleMessage(key), parm);
  }
  public void debug(String key, String[] parm)
  {
	  logger.debug(getBundleMessage(key), parm);
  }
  public void debug(String key, Object message)
  {
	  logger.debug(getBundleMessage(key) + message);
  }
  public void debug(String key)
  {
	  logger.debug(getBundleMessage(key));
  }
  public void debugInfo(String message)
  {
	  logger.debug(message);
  }
}