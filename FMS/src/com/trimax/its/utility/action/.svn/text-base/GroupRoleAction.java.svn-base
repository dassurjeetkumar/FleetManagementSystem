
package com.trimax.its.utility.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.usermanagement.dao.UserDetailDAO;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.GroupMasterDao;
import com.trimax.its.utility.dao.RoleDao;
import com.trimax.its.utility.dao.UserGroupDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.utility.model.GroupRole;
import com.trimax.its.utility.model.Group_Master;
import com.trimax.its.utility.model.Role;
import com.trimax.its.utility.model.UserGroup;

public class GroupRoleAction {
    private Group_Master groupMaster;
    private GroupRole grouprole;
    private Role role;
    public Map<Integer, String> getGrouplistdetails() {
		return grouplistdetails;
	}
	public void setGrouplistdetails(Map<Integer, String> grouplistdetails) {
		this.grouplistdetails = grouplistdetails;
	}
	private Map<Integer, String> grouplistdetails;
	public Group_Master getGroupMaster() {
		return groupMaster;
	}
	public void setGroupMaster(Group_Master groupMaster) {
		this.groupMaster = groupMaster;
	}
	public String getGroupRole()
	{
		//use by ashwini
		 //UserDetailDAO userdao = new UserDetailDAO();
			//grouplistdetails=userdao.getGroup();
		GroupMasterDao groupdao = new GroupMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		groupMaster = groupdao.getGroupDetails(Integer.parseInt(request.getParameter("groupid")));
		return "success";
	}
	public String mapUserGroup()
	 {
		 //System.out.println("use by ashwini");
		 UserGroupDao usergroupdao=new UserGroupDao();
		 HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			int userId=Integer.parseInt(request.getSession().getAttribute("user_Id").toString());
			//System.out.println("userid"+userId);
			String arr[]=request.getParameterValues("check");
			UserGroup usergroup=new UserGroup();
			//System.out.println(arr[0]);
			if(request.getParameterValues("chkbox")!=null){
				if(arr.length>0){
			for(int i=0;i<arr.length;i++)
			{
				usergroup.setUser_id(userId);
				int groupid=Integer.parseInt(arr[i]);
				//
				//usergroupdao.updategroupId(groupid,userId);
				usergroup.setGroup_id(groupid);
				usergroupdao.mapUserGroupDetails(usergroup);
				
				//save
				
				
				//update
			}
				}else{
					  //hiidenfield check
				}
			usergroupdao.updateUser(userId);
			request.getSession().removeAttribute("user_Id");
			}
		 return "success";
	 }
	 public GroupRole getGrouprole() {
		return grouprole;
	}
	public void setGrouprole(GroupRole grouprole) {
		this.grouprole = grouprole;
	}
	
	public String deleterole(){
		String data="";
		try{
			  HttpServletResponse response = ServletActionContext.getResponse();
			  HttpServletRequest request = ServletActionContext.getRequest();
				PrintWriter out = response.getWriter();
				GroupMasterDao groupdao = new GroupMasterDao();
				int grouproleid=Integer.parseInt(request.getParameter("grouproleid"));
				//System.out.println("grouproleid--/////////-"+grouproleid);
				int roleid=groupdao.getRoleId(grouproleid);
				boolean flag=groupdao.getDeletRecord(grouproleid);
				if(flag){
					data="Role Id "+roleid+" Deleted Successfully";
					out.println(data);
				}else{
					data="Role Id "+roleid+" Not Deleted ";
					out.println(data);
				}
		}catch(Exception e){e.printStackTrace();}
	  return null;	
	}
	
	public String getAddRoleToGroup(){
		String data="";
		try{
			  HttpServletResponse response = ServletActionContext.getResponse();
			  HttpServletRequest request = ServletActionContext.getRequest();
			  AccessRightsDao  accessrightdao=new AccessRightsDao();
			  AccessRights accessrights=new AccessRights();
			  int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			  accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewGroup.action");
			  String access=accessrights.getACCESS();
			  String create=accessrights.getCREATE();
			  PrintWriter out = response.getWriter();
			  if(create.equalsIgnoreCase("Y")){
				
			  
				
				GroupMasterDao groupdao = new GroupMasterDao();
				int roleid=Integer.parseInt(request.getParameter("roleid"));
				//System.out.println("roleid-----"+roleid);
				int groupid=Integer.parseInt(request.getParameter("groupid"));
				//System.out.println("groupid-----"+groupid);
				String msg =groupdao.addRoleid(roleid,groupid);
				//System.out.println("msg-------"+msg);
				out.print(msg);}
			  else{
				  out.print("User Dont Have Acces Rights");
			  }
			   	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return null;
	}
	
	public String getRole(){
	
	String data="";
	
		try{
			  HttpServletResponse response = ServletActionContext.getResponse();
			  HttpServletRequest request = ServletActionContext.getRequest();
				int groupid=Integer.parseInt(request.getParameter("groupid"));
				//System.out.println("iddd"+groupid);
				PrintWriter out = response.getWriter();
				GroupMasterDao groupdao = new GroupMasterDao();
				String pagerolelist=groupdao.getrolegrouplist(groupid);
				//System.out.println("pagerolelist"+pagerolelist);
				String rolelist=groupdao.roleList(pagerolelist);
				//System.out.println("nameofrole"+rolelist);
				//String rolelist=groupdao.getRolelist();
		 data=rolelist;
				//data="Ashwini";
		 out.println(data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/*public String  getAllGroupNameId()
	 {
		 UserGroupDao usergroupdao = new UserGroupDao();
			
			//serviceTypeIds=rmDao.getServiceId();
		  //System.out.println("hi i am in finduser");
			List<String> l1=usergroupdao.getGroupId();
			List<String> l2=usergroupdao.getGroupName();
			String regionTypeAjaxString = "";
	     regionTypeAjaxString += "<option value=''>------select------</option>";
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
	 	
		 
	 }*/
	 public String viewUserGroup()
	 {
		 
		 return "success";
	 }
	 
	public String getAllRole() throws IOException
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			int group_id=Integer.parseInt(request.getParameter("group_id").toString());
			request.getSession().setAttribute("group_Id",request.getParameter("group_id").toString());
			GroupMasterDao groupdao = new GroupMasterDao();
			int cnt = groupdao.getTotalRoleRecords();
			//System.out.println("Count------>" + cnt+","+group_id);
			String[] dbcols = {"role_id", "role_name" };
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

			String colName = dbcols[col];
			int total = -1;
			total = groupdao.getTotalRoleRecords();
		/*	AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;*/

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = groupdao.getRoleData(total, request,group_id);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return null;
	}
	/*public String activeGroupRole() {
		
		GroupMasterDao groupdao = new GroupMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String arr[]=request.getParameterValues("chkbox");
	   int groupId=Integer.parseInt(request.getSession().getAttribute("group_Id").toString());
	    int j=groupdao.updateGroupId(groupId);
	    //System.out.println("no of records updated"+j);
	    
		if(request.getParameterValues("chkbox")!=null){
		 	
			
		for(int i=0;i<arr.length;i++)
		{
			//System.out.println("INside activate roler"+request.getParameter("roleid"));
			grouprole.setRole_id(Integer.parseInt(arr[i]));
		
			grouprole.setGroup_id(Integer.parseInt(request.getSession().getAttribute("group_Id").toString()));
			grouprole.setStatus("ACTIVE");
			//String updtsts=groupdao.updateGroupRole(request.getSession().getAttribute("group_Id").toString(),grouprole);
			String res = groupdao.saveGroup_Role(grouprole);
		
		}
		
		}
		return "success";
		}
	*/
	
	
	
	
}

