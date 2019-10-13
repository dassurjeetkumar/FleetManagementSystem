package com.trimax.its.util;

import java.io.PrintStream;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtilVtu {
	public static  SessionFactory admin;
	private static Configuration cfg;

	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	
	
	static {
		try {
		//Create the SessionFactory from standard (hibernate.cfg.xml)
		//config file.
			cfg= new AnnotationConfiguration().configure("hibernate-vts.cfg.xml");
			admin =cfg.buildSessionFactory();
		} catch (Throwable ex) {
		//Log the exception.
			ex.printStackTrace();
		throw new ExceptionInInitializerError(ex);
		}
		}

	public static Session getSession(String fileName) {
		
		
		
		Session session = (Session) threadLocal.get();
		try{
		if (session == null || !session.isOpen()) {
			if (admin == null) {
				rebuildSessionFactory();//01-08-2014
			}

			session = (admin != null) ? admin.openSession()
					: null;
			threadLocal.set(session);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return session;
	}
	
	 public static void rebuildSessionFactory() {
	        try {
	        	admin = (SessionFactory) new AnnotationConfiguration().configure("hibernate-vts.cfg.xml").buildSessionFactory();
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static Configuration getconfig(){
		 
		 
		 return cfg;
	 }
	 
	 
	 public static void closeSession() throws HibernateException {
			Session session = (Session) threadLocal.get();
			threadLocal.set(null);

			if (session != null) {
				session.close();
			}
		}
	 
	 public static void closeSessionFactory(){
		 
	 }

}
