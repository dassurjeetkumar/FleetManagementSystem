 /* Document   : SessionDetailsDao.java
    Created on : May 18, 2014, 11:00:01 AM
    Author     : manojv
*/
package com.trimax.its.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;

import com.trimax.its.model.SessionDetails;
import com.trimax.its.util.HibernateUtil;

public class SessionDetailsDao {

	
	
	public int save(Session session,SessionDetails sessionDetails) {
		int sessionId=0;
		try{
		
		session.beginTransaction();
		sessionId=(Integer)session.save(sessionDetails);		
		session.getTransaction().commit();
		}
		catch(Exception e){
			HibernateUtil.admin = null;
			e.printStackTrace();
		}
		
		return sessionId;
	}
	
	public void update(int n,int para){
		
		SessionDetails sessionDetails;
		Session session=null;
		try{
			
			session = HibernateUtil.getSession("hibernate.cfg.xml");		
			session.beginTransaction();
			sessionDetails=(SessionDetails)session.get(SessionDetails.class,new Integer(n));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=sdf.format(new Date());
			
//			System.out.println("Check>>"+date);			
			//columnwise table update
			if(para==1){
//				System.out.println("INPARA 1===");
				if(session.isOpen()){
					System.out.println("date from sessiondetails======"+date);
					System.out.println("sessiondetails======"+sessionDetails);
			sessionDetails.setUpdateDate(date);
				}
//			System.out.println("INPARA 2===");
			}else{
				if(para==2){
//					System.out.println("log out update");
					
					sessionDetails.setLogoutDate(date);	
					sessionDetails.setStatus("DEACTIVE");
				}
			}
			session.getTransaction().commit();
			}
			catch(Exception e){
				HibernateUtil.admin = null;
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			finally{
				if(session.isOpen()){
					if(session!=null){
						
			               session.close();
			               //HibernateUtil.closeSession();
			            }
					}
//				System.out.println("INPARA 3===");
			//session.close();
			
		}
	}
	
//	public SessionDetails read(int n){
//		SessionDetails sessionDetails;
//		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		session.beginTransaction();
//		sessionDetails=(SessionDetails)session.load(SessionDetails.class,new Integer(n));
//		//session.getTransaction().commit();
//		return sessionDetails;
//	}
}
