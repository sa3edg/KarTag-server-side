package com.kartag.common.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

import com.kartag.common.exception.BadRuntimeException;
import com.kartag.common.logging.LocalLogger;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution. 
 */
public final class PersistenceSessionFactory {

	/**
	 * create class logger
	 */
	private static LocalLogger logger = LocalLogger.getLogger(PersistenceSessionFactory.class);
	
    /** 
     * Location of hibernate.cfg.xml file.
     * NOTICE: Location should be on the classpath as Hibernate uses
     * #resourceAsStream style lookup for its configuration file. That
     * is place the config file in a Java package - the default location
     * is the default Java package.<br><br>
     * Examples: <br>
     * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml". 
     * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code> 
     */
    private static String CONFIG_FILE_LOCATION = "/config/hibernate.cfg.xml";

    /** Holds a single instance of Session */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    /** The single instance of hibernate configuration */
//    private static final Configuration cfg = new Configuration();

    /** The single instance of hibernate SessionFactory */
    private static org.hibernate.SessionFactory sessionFactory;
    
    public static final String ANNOTATION_MAPPING = "ANNOTATION";
    
    public static final String XML_MAPPING = "XML";

    /**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *  @param mapping type 
     *  @return Session
     *  @throws HibernateException
     */
    public static  synchronized  Session currentSession(String mappingType) throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session != null && ! session.isConnected())
        	session = null;
        if (session == null) {
            if (sessionFactory == null) {
                try {
                	//XML mapping way
//                   cfg.configure(CONFIG_FILE_LOCATION);
//             	   sessionFactory = cfg.buildSessionFactory();
                	if(mappingType.equals(XML_MAPPING))
                	{
//                       cfg.configure(CONFIG_FILE_LOCATION);
                	   sessionFactory = new Configuration().configure(CONFIG_FILE_LOCATION).buildSessionFactory();
                	}
                	else if(mappingType.equals(ANNOTATION_MAPPING))
                	{
                	   //Annotation mapping way
                       sessionFactory = new AnnotationConfiguration().configure(CONFIG_FILE_LOCATION).buildSessionFactory();
                	}
                }
                catch (Exception e) {
                	logger.error("000000003", e.getMessage(), e);
		            throw new BadRuntimeException(e);
                }
            }
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }

        return session;
    }

    /**
     *  Close the single hibernate session instance.
     *
     *  @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
            session = null;
        }
    }

    /**
     * Default constructor.
     */
    private PersistenceSessionFactory() {
    }

}