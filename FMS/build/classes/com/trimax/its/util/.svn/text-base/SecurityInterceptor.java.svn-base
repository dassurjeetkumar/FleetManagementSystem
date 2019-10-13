/* Document   : SessionDetailsDao.java
    Created on : May 15, 2014, 11:40:00 AM
    Author     : manojv
*/
package com.trimax.its.util;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.trimax.its.dao.SessionDetailsDao;
import com.trimax.its.model.User;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class  SecurityInterceptor implements Interceptor
{
	private static final String LOGGED_IN = "user";
	private static final String LOGIN_ATTEMPT = "loginAttempt";
	public void init(){}
	SessionDetailsDao sDao=null;

	public String intercept(ActionInvocation next) throws Exception{
		Map session=next.getInvocationContext().getSession();
		User user=(User)session.get(LOGGED_IN);
		HttpServletRequest request=ServletActionContext.getRequest();
		String userAttempt=request.getParameter(LOGIN_ATTEMPT);

		//check for 1st login attempt
		if(userAttempt==null){
			userAttempt="false";
			System.out.println("userAttempt="+userAttempt);
		}
		
		//check user is in http session
		if(user==null){
			if(userAttempt.equals("true")){
//				System.out.println("userAttempt="+userAttempt);
				return next.invoke();
			}else{
//				System.out.println("redirect to login");
				return "welcome";
			}
		}else{
			//System.out.println("invoke");
			int sessionId=(Integer)request.getSession().getAttribute("sessionid");
			try{
				if(sDao==null){
				sDao=new SessionDetailsDao();
				}
			
			//check update update date in sessiondetails
			sDao.update(sessionId,1);
			}
			catch(Exception e){
				return "welcome";
			}
			return next.invoke();
		}
	}

	public void destroy(){}
}
