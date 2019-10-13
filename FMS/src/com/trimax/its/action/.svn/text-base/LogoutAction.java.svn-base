package com.trimax.its.action;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.dao.SessionDetailsDao;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction extends ActionSupport implements SessionAware
{
	Map session;

	public void setSession(Map session)
	{
		this.session=session;
	}

	public String execute()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		SessionDetailsDao sDao=new SessionDetailsDao();
		
		int sessionId=(Integer)request.getSession().getAttribute("sessionid");
		sDao.update(sessionId,2);
		
		session.remove("user");
		request.getSession().invalidate();
		
		return SUCCESS;
	}
}
