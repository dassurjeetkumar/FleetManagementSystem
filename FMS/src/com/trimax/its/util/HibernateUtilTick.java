package com.trimax.its.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtilTick {
	private static  SessionFactory admin;
	//private static final SessionFactory sessionFactory = buildSessionFactory();
	//private static SessionFactory sessionFactory ;//= buildSessionFactory("");
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	static {
		try {

			admin = new AnnotationConfiguration().configure("hibernate-ticket.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {

		throw new ExceptionInInitializerError(ex);
		}
		}
	 
//    private static SessionFactory buildSessionFactory(String fileName) {
//        try {
//            // Create the SessionFactory from hibernate.cfg.xml
//            return new AnnotationConfiguration().configure(fileName)
//                    .buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
 
    /*public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }*/
    /**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session getSession(String fileName) {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (admin == null) {
				//admin = admin;
				rebuildSessionFactory();//01-08-2014
			}
			session = (admin != null) ? admin.openSession()
					: null;
			threadLocal.set(session);
		}
		return session;
	}
	
//	public static Session getSession(String fileName) {
//		Session session = (Session) threadLocal.get();
//		if (session == null || !session.isOpen()) {
//			if (sessionFactory == null) {
//				sessionFactory = buildSessionFactory(fileName);
//			}
//			session = (sessionFactory != null) ? sessionFactory.openSession()
//					: null;
//			threadLocal.set(session);
//		}
//		return session;
//	}

	/**
	 * Close the single hibernate session instance.
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}
	
	
	 public static void rebuildSessionFactory() {
	        try {
	        	admin = (SessionFactory) new AnnotationConfiguration().configure("hibernate-ticket.cfg.xml").buildSessionFactory();
	          
	        } catch (Exception e) {
	            System.err
	                    .println("%%%% Error Creating SessionFactory %%%%");
	            e.printStackTrace();
	        }
	    }
}


