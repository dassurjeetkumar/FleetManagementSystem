package com.trimax.its.utility.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.fare.dao.FareChartMasterDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.GroupMasterDao;
import com.trimax.its.utility.dao.PageMasterDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.utility.model.Group_Master;

public class GroupAction extends ActionSupport{
    public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;
   
	private String requestType;
    public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public Group_Master getGroupMaster() {
		return groupMaster;
	}
	public void setGroupMaster(Group_Master groupMaster) {
		this.groupMaster = groupMaster;
	}
	private Group_Master groupMaster;
	public GroupAction() {
		// TODO Auto-generated constructor stub
	}
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	@SkipValidation
	public String deleteGroup() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewGroup.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		int MasterId=Integer.parseInt(request.getParameter("id"));
		GroupMasterDao groupdao = new GroupMasterDao();
		String s=groupdao.deleteGroup(MasterId);
		
		if(s.equals("success")){
    		addActionMessage("Group Id "+MasterId+" Deleted Successfully.");
		}else{
			addActionMessage("Group Id "+MasterId+" Not Deleted.");
		}
		
		return "success";
		}else{
			return "success";
		}
	}
	
	
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			GroupMasterDao groupdao = new GroupMasterDao();
			//int cnt = groupdao.getTotalRecords();
			////System.out.println("Count------>" + cnt);
			String[] cols = {"","group_id","group_name","status" };
			String[] dbcol = {"","group_id","group_name","status" };
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
			//total = groupdao.getTotalRecords(SEARCH_TERM);
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = groupdao.getData(total, request,dbcol[Integer.parseInt(sCol)],dir);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			//System.out.println("=====?" + ex);
		} finally {
			
		}
		return null;
		//return "success";
	}
	public String viewGroup()
	{
		
		return "success";
	}
	public String addGroup()
	{
		
		return "success";
	}
	public String editGroup()
	{

    	//System.out.println("hi i am in edit");
    	GroupMasterDao groupdao = new GroupMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		groupMaster = groupdao.getGroupDetails(Integer.parseInt(request.getParameter("group_id")));
		
		
		int groupid=Integer.parseInt(request.getParameter("group_id"));
		//System.out.println("groupid---"+groupid);
		//String rolelist=groupdao.getrolelistForGroup(groupid);
		String userlist=groupdao.getUserlist(groupid);
		if(userlist.length()>0){
			msg="Group is Mapped to User-"+userlist;
			}else{
				msg="";
			}
			
		if(msg.length()==0){
			msg="0";
		}
		
		groupMaster.setMsg(msg);
		
		return "success";
	}
	public String editGroupDetails()
	{
   HttpServletRequest request = ServletActionContext.getRequest();
   AccessRightsDao  accessrightdao=new AccessRightsDao();
   AccessRights accessrights=new AccessRights();
   int usrsessionid=(Integer)request.getSession().getAttribute("userid");
   accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewGroup.action");
   String access=accessrights.getACCESS();
   String create=accessrights.getCREATE();
   String edit=accessrights.getEDIT();
   String delete=accessrights.getDELETE();
   if(edit.equalsIgnoreCase("Y")){
 //HttpSession mySession = request.getSession();
   GroupMasterDao gmd=new GroupMasterDao();
   
   
  /* int groupiddetails=Integer.parseInt(request.getParameter("groupiddetails"));
   String groupnamedetails=request.getParameter("groupname");
   String statusdetails=request.getParameter("statusdetails");
   */
   
/*   Group_Master groupMaster=new Group_Master ();
   
   groupMaster.setGroup_id(groupiddetails);
   groupMaster.setGroup_name(groupnamedetails);
   groupMaster.setStatus(statusdetails);*/
 		String groupname=groupMaster.getGroup_name();
 		String status=groupMaster.getStatus();
 		String flag="";
		boolean flagdetails=true;
		CommonValidation common=new CommonValidation();
 		
 		if(("".equals(groupname))||(groupname==null)||(groupname.length()==0)){
			
			
            addFieldError("groupName","Group Name Should Not Blank" );
            flagdetails=false;
				flag="error";
        	
            
	}
	if(!common.isSpecialChar(groupname))
	{
		addFieldError("groupName","Special Character Not Allowed For Group Name");
      flagdetails=false;
			flag="error";
		
	}
	if(status.equals("0")){
		 addFieldError("status","Please Select Status");
		flagdetails=false;
		flag="error";
        	
            
	}
	if(flagdetails){
		int groupid=groupMaster.getGroup_id();
		GroupMasterDao groupdao = new GroupMasterDao();
    	//System.out.println("editPageDEtails"+groupid);
    	 
    	int i=groupdao.saveEDitedPageDetails(requestType,groupid,groupMaster);
    	
    		if(i>0){
				addActionMessage("Group Id "+ groupid +" Updated Successfully");
				flag="success";
			}else{
				if(i==-1){
				addActionError("Group Name Already Exist.");
				flag="error";
				}else{
					addActionError("Error in server.");
					flag="error";
				}
			}
    	}
    	
	return flag;
   }else{
	   return "error";
   }
    	
    	
		
	}
	
	public String saveGroupDetails()
	{
		String flag="";
		boolean flagdetails=true;
		CommonValidation common=new CommonValidation();
		String groupname=groupMaster.getGroup_name();
		String Status=groupMaster.getStatus();
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewGroup.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		//System.out.println("Status--------"+Status);
		if(("".equals(groupname))||(groupname==null)||(groupname.length()==0)){
			
			
	              addFieldError("groupName","Group Name Should Not Blank");
	              flagdetails=false;
					flag="error";
	          	
	              
		}
		if(!common.isSpecialChar(groupname))
		{
			addFieldError("groupName","Special Character Not Allowed For Group Name");
            flagdetails=false;
				flag="error";
			
		}
		if(Status.equals("0")){
			 addFieldError("status","Please Select Status");
			flagdetails=false;
			flag="error";
	          	
	              
		}
		if(flagdetails)
		{
		GroupMasterDao groupdao = new GroupMasterDao();
		//HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		//int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		groupMaster.setCreated_by(usrsessionid);
		groupMaster.setCreated_date(new Date());
		int i=groupdao.insertGroupMaster(groupMaster);
		if(i>0){
			addActionMessage("Group Id "+i+" Created Successfully.");
			flag="success";
		}else{
			if(i==-1){
				addActionError("Group Name Already Exist.");
				flag="error";
			}else{
				addActionError("Database Error Retry Again.");
				flag="error";
			}
		}
		}
		return flag;
		}else{
			return "error";
		}
	}
	public String userGroupMapDetails()
	{
		
		return "success";
	}

    public String findGroupID()
    {
    	GroupMasterDao groupdao = new GroupMasterDao();
		
		//serviceTypeIds=rmDao.getServiceId();
		List<String> l1=groupdao.getGroupId();
		List<String> l2=groupdao.getGroupName();
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
    	
   
    }
	
	public String getGroupDetailsForInactive(){
		try{
			GroupMasterDao groupdao = new GroupMasterDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			//HttpServletRequest request = ServletActionContext.getRequest();
			PrintWriter out = response.getWriter();
			String msg="";
			int groupid=Integer.parseInt(request.getParameter("groupid"));
			//System.out.println("groupid---"+groupid);
			//String rolelist=groupdao.getrolelistForGroup(groupid);
			String userlist=groupdao.getUserlist(groupid);
			if(userlist.length()>0){
				msg="Group is Mapped to User-"+userlist;
				}else{
					msg="";
				}
				
			if(msg.length()==0){
				msg="0";
			}
			
            out.println(msg);	
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
    

}
