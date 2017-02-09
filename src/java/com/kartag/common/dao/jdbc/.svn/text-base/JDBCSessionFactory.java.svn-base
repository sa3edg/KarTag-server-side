package com.kartag.common.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.hibernate3.encryptor.HibernatePBEEncryptorRegistry;

import com.kartag.common.dao.DaoFactory;
import com.kartag.common.exception.BadRuntimeException;
import com.kartag.common.logging.LocalLogger;
import com.kartag.common.util.FileUtil;
public class JDBCSessionFactory {
	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(DaoFactory.class);
	
    /** 
     * Location of hibernate.cfg.xml file.
     * 
     */
    private static String CONFIG_FILE_LOCATION = "/config/hibernate.cfg.xml";

   
    private static JDBCSessionFactory seif = null;
    
    private static ConnectionConfig config = null;
    
    private static Connection conn = null;
    /**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *  
     *  @return Session
     *  @throws Exception
     */
    public static synchronized Connection currentSession() throws SQLException {
        
        if (conn == null) 
        {
        	logger.error("000000003");
        	throw new SQLException();
         }
        return conn;
    }

    /**
     *  Close the single  session instance.
     *
     *  @throws HibernateException
     */
    public static void closeSession() throws SQLException {
    	
        if (conn != null) {
        	conn.close();
        }
    }
   public static void createSessionInstance() 
   {
	   try
   	   {
   	      if(seif == null)
   	      {
   		      initialize();
   		      seif = new JDBCSessionFactory();
   		      //loadEncryptor();
   	      }
   	   }
   	   catch(Exception ex)
   	   {
   		  logger.error("000000003");
          throw new BadRuntimeException(ex);
   	   }
   }
    /**
     * Default constructor.
     */
    private JDBCSessionFactory(){ 	
    	
    }
    
    private static void initialize() throws Exception
    {
    	config = FileUtil.parseConnConfig(CONFIG_FILE_LOCATION);
    	Class.forName(config.getDriverName()).newInstance();
    	conn = DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassword());
    }
    
    private static void loadEncryptor()throws Exception{
    	
    	StandardPBEStringEncryptor  strongEncryptor = new StandardPBEStringEncryptor ();
        
    	SimplePBEConfig webConfig = new SimplePBEConfig();
        webConfig.setPassword("KarTag");
        webConfig.setAlgorithm("PBEWithMD5AndDES");
        strongEncryptor.setConfig(webConfig);
        
    	HibernatePBEEncryptorRegistry registry =
    	      HibernatePBEEncryptorRegistry.getInstance();
    	registry.registerPBEStringEncryptor("myHibernateStringEncryptor", strongEncryptor);
    }

}
