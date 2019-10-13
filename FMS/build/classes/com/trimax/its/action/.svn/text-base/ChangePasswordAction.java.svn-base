package com.trimax.its.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.dao.UserDao;
import com.trimax.its.model.User;

public class ChangePasswordAction extends ActionSupport{
    private String newPass;
    private String pass2;
    
    public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public String getOldpass() {
		return oldpass;
	};

	public void setOldpass(String oldpass) {
		this.oldpass = oldpass;
	}
	private String confirmPass;
    private String oldpass;
    String msg;
    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
   

	
	public ChangePasswordAction() {
		// TODO Auto-generated constructor stub
	}
	@SkipValidation
	public String execute(){
		
		//User user = (User) session.getAttribute("user");
		return "success";
	}
	public String changePassWord()
	{
        //System.out.print("hi in change"+pass2);
        UserDao userDao=null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		User user=(User)mySession.getAttribute("user");
		int userid=user.getUserId();
		//System.out.println("old password"+request.getAttribute("oldpass")+"new password is..."+request.getAttribute("newPass")+"Confirm password"+request.getAttribute("confirmPass"));
		//String pass=user.getPassword();
		int res=0;
		userDao=new UserDao();
		String pass1=userDao.getPassWord(userid);
		if(getOldpass().equals(pass1))
		{
		  		  
		   try
			{
			  userDao=new UserDao();
		      res=userDao.changePassWord(userid,oldpass,newPass);
			  if(res!=0)
			  {
				  setOldpass("");
				  setNewPass("");
				  setConfirmPass("");
				msg="Password changed Sucessfully";
				addActionMessage(msg);
			  }
			}
			catch(Exception e)
			{
				//System.out.println("Exception is ...."+e.getMessage());
				//msg="Please try again....";
				addActionError(msg);
			}
		}
		else
		{
			msg="Incorrect Old Password Please Try Again";
			addActionError(msg);
		}
		//System.out.print("User id is----"+userid+"OLd PassWord is---"+oldpass+"New Password is--"+newPass+"confirm password---"+confirmPass);
		
		
		


		return "success";
		
	}
	public void validate(){
		
		
		Map<String, Object> mySession=  ServletActionContext.getContext().getSession();
		User user=(User)mySession.get("user");
		int userid=user.getUserId();
		UserDao userDao=new UserDao();
		String pass1=userDao.getPassWord(userid);
		boolean flag=true;
		if((getOldpass()!=null)&&(!"".equals(getOldpass())))
        {
		  if(!getOldpass().equals(pass1))
		  {
			  setOldpass("");
			  addFieldError("oldpass","Please Provide Correct Old Password");
			
		  }
        }
		if(("".equals(getOldpass()))||(getOldpass()==null)||(getOldpass().length()==0)){
			 setOldpass("");
			 addFieldError("oldpass","Please Provide Correct Old Password");
		}
	
		if("".equals(getNewPass())||(getNewPass()==null)||(getNewPass().length()==0)){
			 setNewPass("");
			 flag=false;
			addFieldError("newPass","Please Provide  New Password");
		}
		if(getNewPass().contains(" ")){
			 setNewPass("");
			 setConfirmPass("");
			 flag=false;
			addFieldError("newPass","Space Not Allowed for New Password");
		}
		if(getNewPass().length()<6){
			 setNewPass("");
			 setConfirmPass("");
			 flag=false;
			 addFieldError("newPass","Password Should be Minimum 6 Character");
		}
		if(getOldpass().equalsIgnoreCase(getNewPass())){
			 setOldpass("");
			 setNewPass("");
			 setConfirmPass("");
			 flag=false;
			 addFieldError("newPass","Old and New Password Should Not Be Same");
		}
		if(flag){
		if("".equals(getConfirmPass())||(getConfirmPass()==null)||(getConfirmPass().length()==0)){
			setConfirmPass("");
			addFieldError("confirmPass","Please Provide Confirm Password");

		}
		if(!("".equals(getNewPass())||(getNewPass()==null)||(getNewPass().length()==0))&&!("".equals(getConfirmPass())||(getConfirmPass()==null)||(getConfirmPass().length()==0))){

		if(!(getNewPass().equals(getConfirmPass()))){
			setConfirmPass("");
			addFieldError("confirmPass","Confirm Password Does Not Match With New Password");
		}
		}
		}
	}

}