package com.trimax.its.reportmaster.dao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.trimax.its.reportmaster.model.EmailMaster;
import com.trimax.its.reportmaster.model.ReportMaster;

import com.trimax.its.util.HibernateUtil;

public class EmailMasterDao {
	public int createEmailConfiguration(EmailMaster emailMaster) {
		// TODO Auto-generated method stub		
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			int i = 0;

			try {
				/*EmailMaster em=new EmailMaster();
				em.setEmail_id(emailMaster.getEmail_id());
				em.*/
				i = (Integer) session.save(emailMaster);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();			

			} finally {
				session.close();
			}
			return i;

		
	}
	
}
