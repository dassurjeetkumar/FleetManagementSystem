/*  Document   : LoginAction.java
    Created on : May 15, 2014, 11:01:01 AM
    Author     : manojv
*/
package com.trimax.its.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.dao.UserDao;
import com.trimax.its.model.User;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;

public class LoginAction extends ActionSupport implements ModelDriven<User>,Preparable,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -622083973355941900L;
	Map mapSession;
	private User user;
	private Map<Integer, String> divisionlist1;

	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String execute()
	{
		UserDao userDao=new UserDao();
		VtsDataDAO dao=VtsDataDAO.getInstance();
		
		String value="";
        try{
       
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        long l=date.getTime();
        String start=simpleDateFormat.format(date.getTime());
		
		if(userDao.checkUserLogin(user)){
			
			//HttpServletRequest request = ServletActionContext.getRequest();
			user=userDao.getUserDetails(user);
			user.setPassword("");
			mapSession.put("user",user);
			Date date1 = new Date();
			String end=simpleDateFormat.format(date1.getTime());
		    long l1=date1.getTime();
           
            long responsetime=l1-l;
            int res=(int)responsetime;
            double sec=res/1000.0;
            int id=user.getUserId();
			userDao.getViewDeletedRecords();
			userDao.getPF();
				//divisionlist1=dao.getDepot1();
				//userDao.validateAppVersion(); 
			    userDao.insertUserDetails(start,end,sec,id);
	            value=SUCCESS;
			return SUCCESS;
		}else{
			user.setUsername("");
			user.setPassword("");
			addActionError("Invalid username or password");
			return INPUT;
		} }catch(Exception e){
            e.printStackTrace();
        }
        return value;
		
	}
    
	@Override
	public void setSession(Map mapSession) {
	   this.mapSession=mapSession;
	}

    public void validate()
    {
    	if(user.getUsername()==null || user.getUsername().equals("")){
    		addFieldError("username","Username is required.");
    	}
    	
    	if(user.getPassword()==null || user.getPassword().equals("")){
    		addFieldError("password","Password is required.");
    	}
    }


	@Override
	public User getModel() {
		return user;
	}


	@Override
	public void prepare() {
		user=new User();
		
	}
	
	

	
	}
