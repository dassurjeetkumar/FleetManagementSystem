package com.trimax.its.utility.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.fare.dao.FareChartMasterDao;

import com.trimax.its.usermanagement.model.UserDetails;
import com.trimax.its.utility.dao.UserGroupDao;
import com.trimax.its.utility.model.UserGroup;

public class UserGroupAction extends ActionSupport{
	private UserDetails userdetails;
	
	

	public UserDetails getUserdetails() {
		return userdetails;
	}
	public void setUserdetails(UserDetails userdetails) {
		this.userdetails = userdetails;
	}

	private UserGroup usergroup;
	private Map<Integer, String> userlist;
	
	public Map<Integer, String> getUserlist() {
		return userlist;
	}
	public void setUserlist(Map<Integer, String> userlist) {
		this.userlist = userlist;
	}
	public UserGroup getUsergroup() {
		return usergroup;
	}

	/*public void setUsergroup(UserGroup usergroup) {
		this.usergroup = usergroup;
	}

	public UserMaster getUserMaster() {
		return userMaster;
	}*/

	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;


 public String execute()
 {
	 addActionMessage("  ");
	 //System.out.println("in execute method");
	 UserGroupDao usergroupdao=new UserGroupDao();
	 userlist=usergroupdao.getUserlist();
	 return "success";

 }
 public String mapUserGroup()
 {
	 
	 
	 UserGroupDao usergroupdao=new UserGroupDao();
	 userlist=usergroupdao.getUserlist();
	 HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		////System.out.println(userMaster.getUser_id());
		
	//
		
		////System.out.println("userid"+userId);
		String arr[]=request.getParameterValues("chkbox");
		////System.out.println("arr size--//////////---"+arr.length);
		/*if(request.getParameterValues("chkbox")!=null)
		{*/
		int userId=Integer.parseInt(request.getSession().getAttribute("user_Id").toString());
		UserGroup usergroup=new UserGroup();
		usergroupdao.updategroupId(userId);
		////System.out.println(arr[0]);
		
		//get user's group id
		int userGroupId=usergroupdao.getUserGroupById(userId);
		boolean isGroupUnmapped=true;
		if(arr!=null){
			//System.out.println("IN if part");
		if(arr.length>0)
		{
		
		for(int i=0;i<arr.length;i++)
		{
			usergroup.setUser_id(userId);
			int groupid=Integer.parseInt(arr[i]);
			//System.out.println("groupid-------------"+groupid);
			
			usergroup.setGroup_id(groupid);
			usergroupdao.mapUserGroupDetails(usergroup);
			if(userGroupId==groupid){
				isGroupUnmapped=false;
			}

		}
		
		//update group id of user as blank (0) if already assigned group is unmapped
		if(isGroupUnmapped){
			usergroupdao.updateUserGroup(userId,0);
		}
		
		}
		}else{
			//System.out.println("IN else part");
			usergroupdao.updateUserGroup(userId,0);
		}
		addActionMessage("Group Map Successfully");
		userdetails.setUser_id(0);
		request.getSession().removeAttribute("user_Id");
	 return "success";
 }
 public String  findUserId()
 {
	 UserGroupDao usergroupdao = new UserGroupDao();
	 userlist=usergroupdao.getUserlist();
		//serviceTypeIds=rmDao.getServiceId();
	  //System.out.println("hi i am in finduser");
		List<String> l1=usergroupdao.getUserId();
		List<String> l2=usergroupdao.getUserName();
		String regionTypeAjaxString = "";
     regionTypeAjaxString += "<option value='0'>Select</option>";
     for (int i = 0; i < l1.size(); i++) {
         regionTypeAjaxString +=
                 "<option value=" + l1.get(i).toString() + ">" + l2.get(i).toString() + "</option>";
     }
     //regionTypeAjaxString += "</select>";
     //System.out.println("regionTypeAjaxString="+regionTypeAjaxString);
     HttpServletResponse response = ServletActionContext.getResponse();
     PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
 	
		return null;
 	
	 
 }
 public String viewUserGroup()
 {
	 
	 return "success";
 }
 
public String getAllGroup() throws IOException
{
	try {
		 addActionMessage("  ");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.getSession().setAttribute("user_Id",request.getParameter("userId").toString());
		UserGroupDao groupdao = new UserGroupDao();
		userlist=groupdao.getUserlist();
		//int cnt = groupdao.getTotalRecords();
		////System.out.println("Count------>" + cnt);
		String[] cols =    {"","group_id", "group_name","status","note","created_by","created_date","updated_by","updated_date","deleted_status","page_id"};
		String[] dbcols = { "","group_id", "group_name","status","note","created_by","created_date","updated_by","updated_date","deleted_status","page_id"};
				
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");

		if (sStart != null) {
			start = Integer.parseInt(sStart);
			if (start < 0) {
				start = 0;
			}
		}
		if (sAmount != null) {
			amount = Integer.parseInt(sAmount);
			if (amount < 10 || amount > 50) {
				amount = 10;
			}
		}
		if (sCol != null) {
			col = Integer.parseInt(sCol);
			if (col < 0 || col > 5)
				col = 0;
		}
		if (sdir != null) {
			if (!sdir.equals("asc"))
				dir = "desc";
		}

		String colName = cols[col];
		int total = -1;
		
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		total = groupdao.getTotalRecords(SEARCH_TERM);
		COL_NAME = colName;
		DIR = dir;
		START = start;

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
        int userid=Integer.parseInt(request.getSession().getAttribute("user_Id").toString());
		result = groupdao.getData(total, request,userid,dbcols[Integer.parseInt(sCol)],sdir);
		//System.out.println("REsult of datatable------>" + result);
		out.print(result);
	} catch (Exception ex) {
		//System.out.println("=====?" + ex);
	} finally {
		
	}
	return null;
}
}
