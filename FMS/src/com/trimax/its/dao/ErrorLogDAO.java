package com.trimax.its.dao;

import org.hibernate.Session;

import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;

public class ErrorLogDAO {
	
	public static void saveException(ErrorLog log){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		session.save(log);
		session.getTransaction().commit();
		
	}
}
