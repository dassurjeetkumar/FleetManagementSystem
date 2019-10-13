package com.trimax.its.utility.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.fare.dao.FareChartMasterDao;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.GroupMasterDao;
import com.trimax.its.utility.dao.PageRoleDao;
import com.trimax.its.utility.dao.RoleDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.utility.model.MenuPageRole;
import com.trimax.its.utility.model.PageRole;
import com.trimax.its.utility.model.Role;

public class RoleAction extends ActionSupport {
	Role role;
	PageRole pgrole;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	@SkipValidation
	public String execute() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			RoleDao roledao = new RoleDao();
			
			//int cnt = roledao.getTotalRecords();
			////System.out.println("Count------>" + cnt);
			String[] cols = {"","role_id", "role_name","status"};
			String[] dbcol = {"","role_id", "role_name","status"};
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
			//total = roledao.getTotalRecordsForCount(SEARCH_TERM);
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = roledao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			//System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}
	public PageRole getPgrole() {
		return pgrole;
	}
	public void setPgrole(PageRole pgrole) {
		this.pgrole = pgrole;
	}
	
	public String showRole() {
		//System.out.println("inside ");
		return "success";
	}
	
	public String createRole() {
		//System.out.println("inside create Role ");
		return "success";
	}
	public String createRoleAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowRoleAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		CommonValidation common=new CommonValidation();
		boolean isValidData=true;
		 String flagdetails="";
		//validation
		String rolename=role.getRole_name();
		String status=role.getStatus();
		//System.out.println("status-------"+status);
		
		if(("".equals(rolename))||(rolename==null)||(rolename.length()==0)){
			addFieldError("rolename","Please Enter Role Name");
			isValidData=false;
			flagdetails="input";
		}
		
		if(!common.isSpecialChar(rolename))
		{
			addFieldError("rolename","Special Character Not Allowed For Role Name");
			isValidData=false;
			flagdetails="input";
		}
			
		
		if(role.getStatus().equals("0")){
			addFieldError("status","Please Select Status");
			isValidData=false;
			flagdetails="input";
		}
		

		if(isValidData)
		{
			
		RoleDao dao=new RoleDao();
		//role.setCreated_by(request.getSession().getAttribute("userid").toString());
		role.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		role.setCreated_date(new java.util.Date());
		int res = dao.insertRole(role);
		//System.out.println("res--------"+res);
		
		if(res>0){
			//System.out.println("test1");
			addActionMessage("Role Id " +  res + " Added Successfully");
			flagdetails="success";	 
		}else{
			  if(res ==-1){
				  //System.out.println("test2");
			    	addActionError("Role Name Already Exist");
			    	flagdetails="input";
			    }else{
			    	//System.out.println("test3");
			    	addActionError("Database error");
			    	flagdetails="input";
			    }
		}
		}
		return flagdetails;
		}else{
			return "input";
		}
	}

	public String editRole() {
		RoleDao dao = new RoleDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		// //System.out.println("Bus stops Size------>"+role.getCreated_by());
		role = dao.getEditedRole(Integer.parseInt(request.getParameter("chartid")));
		String grouplist=dao.getgrouplistForrole(Integer.parseInt(request.getParameter("chartid")));
		String userlist=dao.getUserlistforrole(Integer.parseInt(request.getParameter("chartid")));
		//String pagelist=roledao.getpagelistForRole(roleid);
		String msg="";
		if(grouplist.length()>0){
		msg="Role is Mapped to Group-"+grouplist;
		}else{
			msg="";
		}
		
		if(msg.length()>0)
		{
		
		if(userlist.length()>0){
			msg+="\n and  User-"+userlist+".\n";
		}else{
			msg+="";
		}
		}else{
			
			if(userlist.length()>0){
				msg+="\n Role is Mapped to User-"+userlist+".\n";
			}else{
				msg+="";
			}
			
			
		}
		
		if(msg.length()==0){
			msg="0";
		}
		
		role.setMsg(msg);
		
		return "scuuess";
	}

	public String addeditedRole() {
		RoleDao roledao = new RoleDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowRoleAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		CommonValidation common=new CommonValidation();
		boolean isValidData=true;
		 String flagdetails="";
		 //System.out.println("role---"+role);
		// int roleid=Integer.parseInt(request.getParameter("roleid"));
		 //String roleid=reque
		/*Role role1=new Role();
		 //System.out.println("roleid------"+roleid);
		 String rolename=request.getParameter("rolename");
		 //System.out.println("rolename-------"+rolename);
		 String status=request.getParameter("statusdetails");
		 //System.out.println("status---"+status);
		 role1.setRole_id(roleid);
		 role1.setRole_name(rolename);
		 role1.setStatus(status);
		 //System.out.println("role1---"+role1.getRole_id());
		 RoleDao dao = new RoleDao();
		 role = dao.getEditedRole(Integer.parseInt(request.getParameter("roleid")));
		 //System.out.println("role1---"+role.getRole_id());*/
		 
		// String msg=request.getParameter("msg").toString();
		String rolename=role.getRole_name();
		
		 
		 String msg=role.getMsg();
		 //System.out.println("msg-------"+msg);
		//role.setMsg(msg);
		//System.out.println("rolename---------"+rolename);
		if(("".equals(rolename))||(rolename==null)||(rolename.length()==0)){
			//System.out.println("in roleaname");
			addFieldError("rolename","Please Enter Role Name");
			isValidData=false;
			flagdetails="input";
		}
		
		if(!common.isSpecialChar(rolename))
		{
			//System.out.println("in roleaname special");
			addFieldError("rolename","Special Character Not Allowed For Role Name");
			isValidData=false;
			flagdetails="input";
		}
			
		
		if(role.getStatus().equals("0")){
			//System.out.println("in status");
			addFieldError("status","Please Select Status");
			isValidData=false;
			flagdetails="input";
		}
		
		if(isValidData){
		int userid=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		int res = roledao.updateRole(role,role.getRole_id(),userid);
		//System.out.println("res---1--"+res);
		if(res>0){
			//System.out.println("res--2---"+res);
			
			addActionMessage("Role Id " + role.getRole_id() + " Updated Successfully");
			
			flagdetails="success";	 
		}else{
			  if(res ==-1){
				  //System.out.println("res--3---"+res);
			    	addActionError("Role Name Already Exist");
			    	flagdetails="input";
			    }else{
			    	//System.out.println("res--4---"+res);
			    	addActionError("Database error");
			    	flagdetails="input";
			    }
		}
	}
		//System.out.println("flagdetails--------"+flagdetails);
		return flagdetails;
		}else{
			return "input";
		}
	}
	
	
	public String deleteRole(){
		 String flagdetails="";
		RoleDao roledao = new RoleDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowRoleAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		int userid=Integer.parseInt(request.getSession().getAttribute("userid").toString());
		int roleid=Integer.parseInt(request.getParameter("roleid").toString());
		//System.out.println("roleid--------"+roleid);
		int result=roledao.deleteRoleRecord(role, roleid, userid);
		//System.out.println("result---------"+result);
		if(result>0){
			addActionMessage("Role Id "+ roleid +" Deleted Sucessfully");
			//flagdetails="success";	 
		}else{
			addActionError("Error in deleted Record");
			//flagdetails="errordeleted"
		}
		return "success";
		}else{
			return "success";
		}
	}
	
	public String pageRoleDisplay() {
		//System.out.println("inside ");
		return "success";
	}
	/*public String getRoleName() {
		RoleDao dao = new RoleDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = dao.getRole_id();
		List<String> l2 = dao.getRoleName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		//System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
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
	
	public String pageRoleView() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			RoleDao roledao = new RoleDao();
			int role_id=Integer.parseInt(request.getParameter("role_id"));
			request.getSession().setAttribute("role_id", role_id);
			//System.out.println("Count------>" +role_id);
			int cnt = roledao.getTotalPageRecords(role_id);
			//int group_id=Integer.parseInt(roledao.getGroupRole(role_id));
			//System.out.println("Count------>" + cnt);
			String[] cols = { "Role Id", "Role Name","Status" };
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
			total = roledao.getTotalPageRecords(role_id);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = roledao.getpageRoleData(total, request,role_id);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			//System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}

	

	public String activeRole() {
		
		
		//System.out.println("i am i9n activaterole----->" );
		try{
		RoleDao roledao = new RoleDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//System.out.println("INSIDE ARRRRRRRRR111");
		String arr[]=request.getParameterValues("chkbox") ;
		String updtsts=roledao.updatePage_Role_M(Integer.parseInt(request.getSession().getAttribute("role_id").toString()));
		if(request.getParameterValues("chkbox")!=null){
		for(int i=0;i<arr.length;i++)
		{
			//System.out.println("INSIDE ARRRRRRRRR111"+"\t"+arr[i]);
		//System.out.println("INSIDE ARRRRRRRRR");
		pgrole.setUpdated_by(request.getSession().getAttribute("userid").toString());
		pgrole.setPage_id(Integer.parseInt(arr[i]));
		pgrole.setStatus("ACTIVE");
		pgrole.setRole_id(Integer.parseInt(request.getSession().getAttribute("role_id").toString()));
		
		
		String res = roledao.savePage_Role(pgrole);
		
		//String updt=roledao.updateroleMapping(Integer.parseInt(arr[i]));
		
		//request.getSession().invalidate();
		}
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "success";
		}

	public String getAddPagesRole(){
		String data="";
		try{
			    //System.out.println("getAddPagesRole");
				HttpServletResponse response = ServletActionContext.getResponse();
				HttpServletRequest request = ServletActionContext.getRequest();
				PrintWriter out = response.getWriter();
				//GroupMasterDao groupdao = new GroupMasterDao();
				int roleid=Integer.parseInt(request.getParameter("roleid"));
				//System.out.println("roleid---1--"+roleid);
				int pageid=Integer.parseInt(request.getParameter("pageid"));
				//System.out.println("pageid--2---"+pageid);
				int readright=Integer.parseInt(request.getParameter("readright"));
				//System.out.println("readright-----3---"+readright);
				int writeright=Integer.parseInt(request.getParameter("writeright"));
				//System.out.println("writeright---4-----"+writeright);
				int userid=(Integer)request.getSession().getAttribute("userid");
				AccessRightsDao  accessrightdao=new AccessRightsDao();
				AccessRights accessrights=new AccessRights();
				int usrsessionid=(Integer)request.getSession().getAttribute("userid");
				accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowRoleAction.action");
				String access=accessrights.getACCESS();
				String create=accessrights.getCREATE();
				if(create.equalsIgnoreCase("Y")){
				PageRoleDao pro=new PageRoleDao();
				MenuPageRole menupage =new MenuPageRole();
				menupage.setRole_id(roleid);
				menupage.setPage_id(pageid);
				menupage.setReadaccess(readright);
				menupage.setCreateaccess(writeright);
				menupage.setDeleteaccess(writeright);
				menupage.setEditaccess(writeright);
				menupage.setViewdelete(writeright);
				String  msg =pro.addPageId(menupage, userid);
				//System.out.println("msg-----"+msg);
				//PrintWriter out = response.getWriter();
				out.print(msg);
				}else{
					out.print("User Dont Have Access Rights");
				}
				////System.out.println("flag-------"+flag);
				
			   	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return null;
		
	}
	
	public String getdeletePageRole(){
		try{
			//System.out.println("getdeletePageRole");
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			PrintWriter out = response.getWriter();
			PageRoleDao pro=new PageRoleDao();
			//pageroleid
			int pageroleid=Integer.parseInt(request.getParameter("pageroleid"));
			//boolean flag=pro.getDeletRecord(pageroleid);
			int userid=(Integer)request.getSession().getAttribute("userid");
			int pageid=pro.getPageId(pageroleid, userid);
			pro.getDeletRecord(pageroleid, userid);
			String msg="Page Id "+ pageid +" Deleted Sucessfully";
			out.print(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRoleDetails(){
		try{
			RoleDao dao = new RoleDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			role = dao.getEditedRole(Integer.parseInt(request.getParameter("roleid")));
			//System.out.println("role---------"+role);
			HttpServletResponse response = ServletActionContext.getResponse();
			//HttpServletRequest request = ServletActionContext.getRequest();
			PrintWriter out = response.getWriter();
			RoleDao roledao=new RoleDao();
			String msg="";
			int roleid=Integer.parseInt(request.getParameter("roleid"));
			//String pagelist=roledao.getpagelist(roleid);
			String grouplist=roledao.getgrouplistForrole(roleid);
			String userlist=roledao.getUserlistforrole(roleid);
			//String pagelist=roledao.getpagelistForRole(roleid);
			
			if(grouplist.length()>0){
			msg="Role is Mapped to Group-"+grouplist;
			}else{
				msg="";
			}
			
			if(msg.length()>0)
			{
			
			if(userlist.length()>0){
              String[] part2MsgArr=userlist.split(",");
				
				if(part2MsgArr.length>11){
					userlist="";
				 for(int y=0;y<10;y++){
					 userlist+=part2MsgArr[y]+",";
				 }
				 userlist=userlist+"...";
				}

				msg+="\n and  User-"+userlist+".\n";
			}else{
				msg+="";
			}
			}else{
				
				if(userlist.length()>0){
					msg+="\n Role is Mapped to User-"+userlist+".\n";
				}else{
					msg+="";
				}
				
				
			}
			/*if(pagelist.length()>0){
				msg+="\n Role is mapped to Pages-"+pagelist;
			}else{
				msg+="";
			}*/
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
