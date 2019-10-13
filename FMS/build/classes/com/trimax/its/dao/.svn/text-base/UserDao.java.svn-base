 /* Document   : UserDao.java
    Created on : May 15, 2014, 11:30:01 AM
    Author     : manojv
*/
package com.trimax.its.dao;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.EncryptionDecryption;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.AppVersion;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.SessionDetails;
import com.trimax.its.model.User;
import com.trimax.its.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;


public class UserDao {
	EncryptionDecryption end=new EncryptionDecryption();
	public boolean checkUserLogin(User user) {
		
		boolean success = false;
		Session session = null;
		Query query = null;
		List<Map<String, Object>> aliasToValueMapList = null;

		try {
			//String sql="Select user_id,userloginname,password from menu_user_master u where userloginname ='" +user.getUsername()+"'and password ='"+end.encrypt(user.getPassword())+"'and status='ACTIVE'";
			/*String sql="select mum.user_id,mum.userloginname,mum.password,mut.user_org, mum.access_status  from menu_user_master mum "+ 
					   " INNER JOIN menu_user_type mut ON mum.user_type_id=mut.user_type_id " +
					   " where mum.userloginname = '"+ user.getUsername() +"' and mum.password='"+ end.encrypt(user.getPassword())+ "' and mum.status='ACTIVE'";*/		

			String sql="select mum.user_id,mum.userloginname,mum.password,mut.user_org, mum.access_status,mum.org_type_id,mum.org_chart_id ,mum.user_type_id,mum.emp_id,mum.userloginname,mum.role_id,mum.usergroup_id,mum.designation_type_id from menu_user_master mum "+
					       " INNER JOIN menu_user_type mut ON mum.user_type_id=mut.user_type_id  " +
					       " where mum.userloginname = '"+ user.getUsername() +"' and mum.password='"+ end.encrypt(user.getPassword())+ "' and mum.status='ACTIVE' and mum.deleted_status=0 "; 

			/*session = HibernateUtil.getSession("");
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();*/
			
			
			 try{
                 
                 session = HibernateUtil.getSession("");
                 query = session.createSQLQuery(sql);
                 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
                 aliasToValueMapList = query.list();
                 
                 }catch(Exception e){
                         System.out.println("Reacreate SFFF user dao");
                         
                         HibernateUtil.admin=null;
                         session = HibernateUtil.getSession("");
                         query = session.createSQLQuery(sql);
                         query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
                         aliasToValueMapList = query.list();
                 }
			 
			 
			 
			if (aliasToValueMapList.size() > 0) {

				Map<String, Object> rs = aliasToValueMapList.get(0);
//				System.out.println(rs.get("user_name").toString());
//				System.out.println(rs.get("password").toString());
//				System.out.println(user.getUsername() + ":>:"
//						+ user.getPassword());
				if (rs.get("userloginname").toString().equals(user.getUsername()) 
						&&  rs.get("password").toString().equals(end.encrypt(user.getPassword()))){
					success = true;
					HttpServletRequest request=ServletActionContext.getRequest();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date=sdf.format(new Date());
					int userId=(Integer)rs.get("user_id");
					String accessstatus=rs.get("access_status").toString();
					String orgtypeid=rs.get("org_type_id").toString();
					String orgchartid=rs.get("org_chart_id").toString();
					String empid=rs.get("emp_id").toString();
					String userloginname=rs.get("userloginname").toString();
					String roleid=rs.get("role_id").toString();
					String usergroupid=rs.get("usergroup_id").toString();
					String designationtypeid=rs.get("designation_type_id").toString();
					
					//System.out.println("userId--------"+userId);
					//System.out.println("accessstatus--------"+accessstatus);
					//String uid=(String)rs.get("user_id");
					//String userWorkingUnit=(String)rs.get("working_org_name");
					//System.out.println("userId="+userId+"  date="+date);
					
					//sessionDetails log
					SessionDetails sessionDetails=new SessionDetails();
					sessionDetails.setUserId(userId);
					sessionDetails.setIpAddress(request.getRemoteAddr());
					sessionDetails.setHtmlSessionId(request.getRequestedSessionId());
					sessionDetails.setLoginDate(date);
					sessionDetails.setUpdateDate(date);
					sessionDetails.setStatus("ACTIVE");
				    SessionDetailsDao sessionDao=new SessionDetailsDao();
				    int sessionId=sessionDao.save(session,sessionDetails);
				    
				    //set user attributes in session
				    request.getSession().setAttribute("sessionid",sessionId);
				    request.getSession().setAttribute("userorgtype",accessstatus);
				    request.getSession().setAttribute("userid",userId);
				    request.getSession().setAttribute("username",user.getUsername());
				    
				    
				    request.getSession().setAttribute("orgtypeid",orgtypeid);
				    request.getSession().setAttribute("orgchartid",orgchartid);
				    request.getSession().setAttribute("empid",empid);
				    request.getSession().setAttribute("userloginname",userloginname);
				    request.getSession().setAttribute("roleid",roleid);
				    request.getSession().setAttribute("usergroupid",usergroupid);
				    request.getSession().setAttribute("designationtypeid",designationtypeid);
				    
				   
				    //System.out.println("won*****************="+request.getSession().getAttribute("userorgtype"));
				}
			}

		} catch (Exception e) {
			//System.out.println(e);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in UserDao", e);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			HibernateUtil.admin=null;
			ErrorLogDAO.saveException(log);// save error log to db
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return success;
	}

	public User getUserDetails(User user) {

		
		Session session = null;

		try {
			String sql = "Select user_id,userloginname  FROM menu_user_master WHERE userloginname = '"
					+ user.getUsername() + "' and password ='" + end.encrypt(user.getPassword()) + "' and status='ACTIVE' and deleted_status=0";

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				Map<String, Object> rs = aliasToValueMapList.get(0);
				int userId=Integer.parseInt(rs.get("user_id").toString());
				user.setUserId(userId);
				//user.setFirstname(rs.get("first_name").toString());
				user.setFirstname(rs.get("userloginname").toString());
			}

		} catch (Exception e) {
			//System.out.println(e);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in UserDao", e);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);// save error log to db
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
		return user;
	}
	public int changePassWord(int userid,String currentPassWord,String newPassWord)
	{
		Query query=null;
		String sql = "Select password  FROM menu_user_master WHERE user_id ="+ userid + " and status='ACTIVE' and deleted_status=0";
		Session session = null;
		int updated=0;
		
		try {
			String encryptpass=end.encrypt(newPassWord);
		 	session = HibernateUtil.getSession("hibernate.cfg.xml");
		 	org.hibernate.Transaction tx=session.beginTransaction();
			query = session.createSQLQuery(sql);
			String password=query.uniqueResult().toString();
			//System.out.println("Password===>"+password);
			if( currentPassWord.equals(end.decrypt(password)))
            {

                
                    String updatesql="update menu_user_master set password='"+encryptpass+"' where user_id="+userid+" and status='ACTIVE' and deleted_status=0";
                    query = session.createSQLQuery(updatesql);
                    updated = query.executeUpdate();
                   //System.out.println(updated);

              
            }
			if(updated>0)
		    {
				tx.commit();
			}
			else
			{
				tx.rollback();
			}
		
			

		} catch (Exception e) {
			//System.out.println(e);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in UserDao", e);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);// save error log to db
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
	
		return updated;
		
	}
	public String getPassWord(int userid)
	{
		Query query=null;
		String sql = "Select password FROM menu_user_master WHERE user_id ="+ userid + " and status='ACTIVE' and deleted_status=0 ";
		Session session = null;
		int updated=0;
		String password=null;
		String decryptpass="";
		try {
			
		 	session = HibernateUtil.getSession("hibernate.cfg.xml");
		 	org.hibernate.Transaction tx=session.beginTransaction();
			query = session.createSQLQuery(sql);
			password=query.uniqueResult().toString();
			
			 decryptpass=end.decrypt(password);
			
			

		} catch (Exception e) {
			//System.out.println(e);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in UserDao", e);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);// save error log to db
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
	

		return decryptpass;
		
	}
	
	public void getViewDeletedRecords(){
		Session session = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			int userid=Integer.parseInt(request.getSession().getAttribute("userid").toString());
			System.out.println("userid--------------"+userid);
			String sql=" select sys_value  from default_system_veriable where sys_key='VIEWDELETEDRECORD_ROLE_ID' and deleted_status='0' and status= 'Y' ";
			//session = HibernateUtil.getSession("");
			System.out.println("sql--------"+sql);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			System.out.println("aliasToValueMapList------"+aliasToValueMapList.size());
			if(aliasToValueMapList.size()>0){
				Map<String, Object> rs = aliasToValueMapList.get(0);
				String roleidvalue=rs.get("sys_value").toString();
				System.out.println("roleidvalue--------"+roleidvalue.length());
				if(roleidvalue.length()==0){
					request.getSession().setAttribute("viewdeletedrecord","N");
				}
				else{
					String sql1=" select role_id from menu_user_master where user_id = "+ userid +" and role_id IN (" + roleidvalue +")";
					System.out.println("sql1---------"+sql1);
					Query query1 = session.createSQLQuery(sql1);
					query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList1 = query1.list();
					if(aliasToValueMapList1.size()>0){
					    request.getSession().setAttribute("viewdeletedrecord","Y");
					}else{
						 request.getSession().setAttribute("viewdeletedrecord","N");
					}
					}
				
			}else{
				 request.getSession().setAttribute("viewdeletedrecord","N");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void getPF(){
		Session session = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String pf="";
		try{
			int userid=Integer.parseInt(request.getSession().getAttribute("userid").toString());
			String sql=" select IFNULL(PF,' ')PF from employee " +
					   " INNER JOIN menu_user_master ON menu_user_master.emp_id = employee.EMPLOYEE_ID " +
					   " where menu_user_master.user_id = " +userid;  
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if(aliasToValueMapList.size()>0){
				Map<String, Object> rs = aliasToValueMapList.get(0);
				 pf=rs.get("PF").toString();
				 System.out.println("pf------"+pf);
				 request.getSession().setAttribute("PF",pf);
			}else{
				request.getSession().setAttribute("PF"," ");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int validateAppVersion(){
		String version=getAppVersion();
		
		AppVersion appVersion=new AppVersion();
		appVersion.setVersion_no(version);
		appVersion.setStatus("Y");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		appVersion.setCreated_date(sdf.format(new Date()));
		
		int status=checkAppVersion(appVersion);
		
		return status;
	}
	
	public int checkAppVersion(AppVersion appVersion){
		int i=0;
		Session session=null;
		Transaction tx=null;
		
		try{
		session=HibernateUtil.getSession("");
		tx=session.beginTransaction();
		
		String query="from AppVersion where version_no='"+appVersion.getVersion_no().trim()+"'";
				
		List<AppVersion> list=session.createQuery(query).list();
		
		if(list.size()<=0){	
		 i=(Integer)session.save(appVersion);
		}else{
			AppVersion av=(AppVersion)list.get(0);
			
			if(av.getStatus().equalsIgnoreCase("Y")){
				i=1;
			}else{
			  i=-1;
			}
		}
		tx.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return i;
	}
	
	public String getAppVersion(){
		String version="";
		ResourceBundle rb = ResourceBundle
				.getBundle("com.trimax.its.properties.general");
		
		version=rb.getString("current_app_version");
		
		return version;
	}
	
public void insertUserDetails(String start,String end,double response,int id){
        
        Query query=null;
        
        String sql = "insert into  response_time(start_time,end_time,time_diff,inserted_by) values('"+start+"','"+end+"',"+response+","+id+")";
        Session session = null;
        int updated=0;
        try {
            
             session = HibernateUtil.getSession("hibernate.cfg.xml");
             org.hibernate.Transaction tx=session.beginTransaction();
             System.out.println("sql-----"+sql);
            query = session.createSQLQuery(sql);
            updated=query.executeUpdate();
            System.out.println("Update value====="+updated);
            if(updated>0){
                tx.commit();
                System.out.println("One Record Inserted Successfully By=="+id);
            }else{
                System.out.println("No Record Inseted");
            }
            

        } catch (Exception e) {
            //System.out.println(e);
            e.printStackTrace();
                } 
	
}
}
