package com.trimax.its.usermanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.common.EncryptionDecryption;
import com.trimax.its.dao.UserDao;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.usermanagement.dao.DesignationTypeDAO;
import com.trimax.its.usermanagement.dao.UserDetailDAO;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.usermanagement.model.MenuUserRolePage;
import com.trimax.its.usermanagement.model.UserDetails;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.PageRoleDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.utility.model.MenuPageRole;
import com.trimax.its.utility.model.Role;
import com.trimax.its.utility.model.UserGroup;
import com.trimax.its.utility.dao.UserGroupDao;
import com.trimax.its.vehicle.model.OrganisationChart;




public class UserAction extends ActionSupport  {
	//private List<DesignationType> designationlist;
	private Map<Integer, String> designationlist;
	private Map<Integer, String> rolelist;

	private Map<Integer, String> grouplist;
	private Map<Integer, String> employeelist;
	private Map<Integer, String> employeelistforedit;
	private Map<Integer,String> organizationunit;
	private Map<Integer, String> usertypelist;
	
	public Map<Integer, String> getUsertypelist() {
		return usertypelist;
	}

	public void setUsertypelist(Map<Integer, String> usertypelist) {
		this.usertypelist = usertypelist;
	}

	public Map<Integer, String> getEmployeelistforedit() {
		return employeelistforedit;
	}

	public void setEmployeelistforedit(Map<Integer, String> employeelistforedit) {
		this.employeelistforedit = employeelistforedit;
	}

	public Map<Integer, String> getRolelist() {
		return rolelist;
	}

	public void setRolelist(Map<Integer, String> rolelist) {
		this.rolelist = rolelist;
	}

	public Map<Integer, String> getGrouplist() {
		return grouplist;
	}

	public void setGrouplist(Map<Integer, String> grouplist) {
		this.grouplist = grouplist;
	}

	public Map<Integer, String> getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(Map<Integer, String> employeelist) {
		this.employeelist = employeelist;
	}

	
	public Map<Integer, String> getDesignationlist() {
		return designationlist;
	}

	public void setDesignationlist(Map<Integer, String> designationlist) {
		this.designationlist = designationlist;
	}

	public UserDetails getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserDetails userdetails) {
		this.userdetails = userdetails;
	}

	UserDetails userdetails;
	

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}


  public String getDisplayUser () throws Exception {
	 //userdetails.setAgent_id(" ");
	 // userdetails.setPassword(" ");
	  UserDetailDAO userdao = new UserDetailDAO();
	  designationlist=userdao.getDesignationList();
		rolelist=userdao.getRole();
		grouplist=userdao.getGroup();
		employeelist=userdao.getEmployee();
		organizationunit=userdao.getOrgnizationList();
		usertypelist=userdao.getUserTypeList();
				
	  return "success";
  }
	
	public Map<Integer, String> getOrganizationunit() {
	return organizationunit;
}

public void setOrganizationunit(Map<Integer, String> organizationunit) {
	this.organizationunit = organizationunit;
}



	public void getUSerListDetails() throws Exception {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			UserDetailDAO userdao = new UserDetailDAO();
			//designationlist=userdao.getDesignationList();
			//rolelist=userdao.getRole();
			//grouplist=userdao.getGroup();
			//employeelist=userdao.getEmployee();
			//String[] cols = {"","Sr no", "User name","Employee Id", "Employee Name(English)", "Employee name(Kannada)","User type","Working designation","Token","PF no","Working unit","Working section","Status"};
			//String[] cols = {"","Sr no", "User name","Employee Id"};
			
			/*ja.add(rs.get("userloginname").toString());
				int empid=Integer.parseInt(rs.get("emp_id").toString());
				if(empid==0){
					ja.add("NA");	
				}else{
					ja.add(rs.get("emp_id").toString());
				}
				ja.add(rs.get("EMPLOYEE_NAME").toString());
				ja.add(rs.get("user_type").toString());
				ja.add(rs.get("designation_type_name").toString());
				ja.add(rs.get("Token").toString());
				ja.add(rs.get("pf").toString());
				ja.add(rs.get("status").toString());
				ja.add(rs.get("WORKING_DEPT").toString());*/
			String[] dbcol={"","user_id","userloginname","emp_id","EMPLOYEE_NAME","employee.EMPLOYEE_NAME_KANNADA","menu_user_type.user_type_name","designation_type_name","Token","pf","WORKING_DEPOT","WORKING_DEPT","status","menu_user_master.created_by","user_name"};
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
			int total = -1;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=userdao.getUserList(total,request,dbcol[Integer.parseInt(sCol)],sdir);
			out.print(result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SkipValidation
	public String getEditDetails(){
		try{
		EncryptionDecryption encry=new EncryptionDecryption();
		UserDetailDAO userdao = new UserDetailDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		employeelistforedit=userdao.getEmployeeListForEdit();
		 int userdetailsid=Integer.parseInt(request.getParameter("userdetailsid"));
//		 System.out.println("userdetailsid-------"+userdetailsid);
		 
		 userdetails=userdao.getEditUserDetails(userdetailsid);
//		 System.out.println("userdetails_orgchart_type===="+userdetails.getOrgnizationType().getOrgType());
//		 System.out.println("userdetails_orgchart_id-name===="+userdetails.getOrgchartdetails().getOrg_name());

		 ////System.out.println("passward-----------"+userdetails.getPassword());
		 userdetails.setOldGroupID(userdetails.getUsergroupid());
		 ////System.out.println("TESTing-----///////////////////---"+userdetails.getOldGroupID());
		 String decryptpass=encry.decrypt(userdetails.getPassword());
		 ////System.out.println("decryptpass---------"+decryptpass);
		 userdetails.setPassword(decryptpass);
		 userdetails.setDesignationtype(userdao.getDesignationTypename(userdetails.getDesignation_type_id()));
		 
		 designationlist=userdao.getDesignationList();
			rolelist=userdao.getRole();
			grouplist=userdao.getGroup();
			employeelist=userdao.getEmployee();
			organizationunit=userdao.getOrgnizationList();
			usertypelist=userdao.getUserTypeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		 return "success";
	}
	
	public String addUserDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowUserList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		//CommonValidation common=new CommonValidation();
		try{
			
			boolean flagdetails=true;
			
			HttpServletResponse response = ServletActionContext.getResponse();
			UserDetailDAO userdao = new UserDetailDAO();
			CommonValidation common=new CommonValidation();
			   designationlist=userdao.getDesignationList();
				rolelist=userdao.getRole();
				grouplist=userdao.getGroup();
				employeelist=userdao.getEmployee();
				organizationunit=userdao.getOrgnizationList();
				usertypelist=userdao.getUserTypeList();
				
			//int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			
			if(userdetails.getUsertypedetails().getUser_type_id() == 0){
				addFieldError("user_type_id","Please Select User Type");
				flagdetails=false;
				flag="error";
			}
			
			////System.out.println("usertype-------"+userdetails.getUsertypedetails().getUser_type_id());
		  String usertype=userdao.getUserTypename(userdetails.getUsertypedetails().getUser_type_id());
			if(usertype.equalsIgnoreCase("Employee")){
				if(userdetails.getEmpid() == 0){
					addFieldError("empid","Please Select Employee");
					flagdetails=false;
					flag="error";
				}
				
			}
			if(userdetails.getUserloginname().equalsIgnoreCase(""))
			{
				addFieldError("userloginname","User Login Id Should Not Be Blank");
				flagdetails=false;
				flag="error";
			}
		
			if(!common.isSpecialChar(userdetails.getUserloginname()))
			{
				addFieldError("userloginname","Special Character Not Allowed For User Login Id");
		        flagdetails=false;
					flag="error";
				
			}
			
			if(usertype.equalsIgnoreCase("Employee")||usertype.equalsIgnoreCase("Guest"))
			{
				if(!common.isSpecialChar(userdetails.getAgent_id()))
				{
				   	
					addFieldError("agent_id","Special Character Not Allowed For Call Agent Id");
			        flagdetails=false;
						flag="error";
				}
				
			}
			
			if(userdetails.getPassword().equalsIgnoreCase(""))
			{
				addFieldError("password","Password Should Not Be Blank");
				flagdetails=false;
				flag="error";
			}
			if(userdetails.getPassword().length()<6)
			{
				addFieldError("password","Password Should Minimum 6 Character");
				flagdetails=false;
				flag="error";
			}
			if(userdetails.getPassword().contains(" "))
			{
				addFieldError("password","Space Not Allowed for Password");
				flagdetails=false;
				flag="error";
			}
			////System.out.println("userdetails.getRole()---------"+userdetails.getRole());
			if(userdetails.getRole().getRole_id() == 0){
				addFieldError("role_id","Please Select Role");
				flagdetails=false;
				flag="error";
			}
		
			
			
			if(usertype.equalsIgnoreCase("Employee"))
			{
				////System.out.println("designationtypeid-------"+userdetails.getDesignation_type_id());
				int designationtypeid=Integer.parseInt(request.getParameter("designation_type_id"));
				////System.out.println("designationtypeid--------"+designationtypeid);
				if(designationtypeid == 0){
					addFieldError("designation","Designation Type Not Proper");
					flagdetails=false;
					flag="error";
				}
				
				
				int orgtypeid=Integer.parseInt(request.getParameter("org_type_id_emp"));
				////System.out.println("orgtypeid-----2-----"+orgtypeid);
				//userdetails.setOrgnizationType()
				int orgchartid=Integer.parseInt(request.getParameter("org_chart_id_emp"));
				////System.out.println("orgchartid-----2-----"+orgchartid);
				if(orgtypeid == 0){
					addFieldError("org_type_id","Organization Unit Not Proper");
					flagdetails=false;
					flag="error";
				}
				
				if(orgchartid ==0 ){
					addFieldError("org_type_id","Organization Name Not Proper");
					flagdetails=false;
					flag="error";
				}
				OrgType org=new OrgType();
				org.setOrg_type_id(orgtypeid);
				userdetails.setOrgnizationType(org);
				OrganisationChart orgchart=new OrganisationChart();
				orgchart.setOrg_chart_id(orgchartid);
				userdetails.setOrgchartdetails(orgchart);
				
			}else{
				if(userdetails.getOrgnizationType().getOrg_type_id() == 0){
					addFieldError("org_type_id","Please Select Organization Unit");
					flagdetails=false;
					flag="error";
				}
				////System.out.println("charid---------"+userdetails.getOrgchartdetails().getOrg_chart_id());
				
				if(userdetails.getOrgchartdetails().getOrg_chart_id() == 0){
					addFieldError("org_chart_id","Please Select Organization Name");
					flagdetails=false;
					flag="error";
				}
			}
			
			
			
			
			if(userdetails.getEmailid().length()>0){
				 String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				// Boolean b = this.getEmailid().matches(emailreg);
				// Boolean b= userdetails.getEmpid().matches(emailreg);
				 Boolean b=userdetails.getEmailid().matches(emailreg);
				 if (b == false) {
					    addFieldError("emailid","Email Id Is Not Valid");
						flagdetails=false;
						flag="error"; 
						
	                }
			}
			////System.out.println("contno-----"+userdetails.getContactno());
			////System.out.println("contno-----"+userdetails.getContactno().length());
			
			if(userdetails.getContactno().length()>0){
				String pattern = "\\d{6,10}";
				 if(!userdetails.getContactno().matches(pattern)){
					 addFieldError("contactno","Contact Number Should Be Numberic");
					 flagdetails=false;
						flag="error"; 
				 }
				
			}
			////System.out.println("flagdetails----///////------"+flagdetails);
			if(flagdetails){
				int designationtypeid=Integer.parseInt(request.getParameter("designation_type_id"));
				////System.out.println("designationtypeid--------"+designationtypeid);
			int i=userdao.addUser(userdetails, usrsessionid,designationtypeid);
			if(i>0){
				UserGroupDao usergroupdao=new UserGroupDao();
				UserGroup usergroup=new UserGroup();
				usergroup.setUser_id(i);
				usergroup.setGroup_id(userdetails.getUsergroupid());
				
				usergroupdao.addgroupId(usergroup);
				addActionMessage("User No "+i+" Created Successfully.");
				flag="success";
			}else{
				if(i==-1){
					addActionError("User Name Already Exist.");
					flag="error";
				}else{
					addActionError("Database Error Retry Again.");
					flag="error";
				}
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
		}else{
			return "error";
		}
	}
	
	@SkipValidation
	public String deleteUserDetails(){
		HttpServletRequest request = ServletActionContext.getRequest();
		 int userdetailsid=Integer.parseInt(request.getParameter("userdetailsiddetails"));
		 AccessRightsDao  accessrightdao=new AccessRightsDao();
		 AccessRights accessrights=new AccessRights();
		 int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		 accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowUserList.action");
		 String access=accessrights.getACCESS();
		 String create=accessrights.getCREATE();
		 String edit=accessrights.getEDIT();
		 String delete=accessrights.getDELETE();
		 if(delete.equalsIgnoreCase("Y")){
		 UserDetailDAO userdao = new UserDetailDAO();
		 DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(userdetailsid, "menu_user_master");
		System.out.println("status---"+status);
			if(status.equals("")){
				if(userdao.deletedUser(userdetailsid)){
					addActionMessage("User No "+userdetailsid+" Deleted Successfully");
				}else{
					addActionMessage("Database error retry again.");
				}
			}else{
				addActionError(status);
			}
		
		
		return "success";
		 }else{
			 addActionMessage("Access Denied - User Does Not Have Access Privileges");
			 return "success";
		 }
	}
	

	public String updateUserDetails(){
		
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		 AccessRightsDao  accessrightdao=new AccessRightsDao();
		 AccessRights accessrights=new AccessRights();
		 int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		 accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowUserList.action");
		 String access=accessrights.getACCESS();
		 String create=accessrights.getCREATE();
		 String edit=accessrights.getEDIT();
		 String delete=accessrights.getDELETE();
		 if(edit.equalsIgnoreCase("Y")){
		try{
		 UserDetailDAO userdao = new UserDetailDAO();
			int useriddetails=userdetails.getUser_id();
			 //userdetails=userdao.getEditUserDetails(useriddetails);
		boolean flagdetails=true;
		boolean groupflag=false;
		 designationlist=userdao.getDesignationList();
			rolelist=userdao.getRole();
			grouplist=userdao.getGroup();
			organizationunit=userdao.getOrgnizationList();
			employeelistforedit=userdao.getEmployeeListForEdit();
			usertypelist=userdao.getUserTypeList();
			
			CommonValidation common=new CommonValidation();
			////System.out.println("Passweord-------"+userdetails.getPassword());
			////System.out.println("Role-------"+userdetails.getRole().getRole_id());
			//////System.out.println("Designation-------"+userdetails.getDesignationtypeid().getDesignation_type_id());
		
			 String usertype=userdao.getUserTypename(userdetails.getUsertypedetails().getUser_type_id());
			
			if(!common.isSpecialChar(userdetails.getUserloginname()))
			{
				////System.out.println("IN special character");
				addFieldError("userloginname","Special Character Not Allowed For User Login Name");
		        flagdetails=false;
					flag="error";
				
			}	
			
			if(usertype.equalsIgnoreCase("Employee")||usertype.equalsIgnoreCase("Guest"))
			{
				if(!common.isSpecialChar(userdetails.getAgent_id()))
				{
				   	
					addFieldError("agent_id","Special Character Not Allowed For Call Agent Id");
			        flagdetails=false;
						flag="error";
				}
				
			}
		if(userdetails.getPassword().equalsIgnoreCase(""))
		{
			////System.out.println("IN Password");
			addFieldError("password","Password Should Not Be Blank");
			flagdetails=false;
			flag="error";
		}
		if(userdetails.getPassword().length()<6)
		{
			////System.out.println("IN Password length");
			addFieldError("password","Password should Minimum 6 Character");
			flagdetails=false;
			flag="error";
		}
		if(userdetails.getPassword().contains(" "))
		{
			addFieldError("password","Space Not Allowed for Password");
			flagdetails=false;
			flag="error";
		}
		int roleiddetails=userdetails.getRole().getRole_id();
		////System.out.println("roleiddetails------"+roleiddetails);
		
		/*if(roleiddetails == 0){
			////System.out.println("IN IF condition");
			Role role=new Role();
			role.setRole_id(roleiddetails);
			addFieldError("role_id","Please Select Role");
			flagdetails=false;
			flag="error";
			
		}*/
		if(userdetails.getRole().getRole_id() == 0){
			addFieldError("role_id","Please Select Role");
			flagdetails=false;
			flag="error";
		}
		/*if(userdetails.getDesignationtypeid().getDesignation_type_id() == 0){
			addFieldError("designation_type_id","Please select Designation Type Id");
			flagdetails=false;
			flag="error";
		}*/
		/*//System.out.println("test------123---"+userdetails.getOrgnizationType().getOrg_type_id());
		if(userdetails.getOrgnizationType().getOrg_type_id() == 0){
			addFieldError("org_type_id","Please Select Organization Unit");
			flagdetails=false;
			flag="error";
		}
		//System.out.println("charid---------"+userdetails.getOrgchartdetails().getOrg_chart_id());
		
		if(userdetails.getOrgchartdetails().getOrg_chart_id() == 0){
			addFieldError("org_chart_id","Please Select Organization Name");
			flagdetails=false;
			flag="error";
		}*/
		
		 
		  ////System.out.println("usertype----"+usertype);
		
		if(usertype.equalsIgnoreCase("Employee"))
		{
			int orgtypeid=Integer.parseInt(request.getParameter("org_type_id_emp"));
			////System.out.println("orgtypeid-----2-----"+orgtypeid);
			//userdetails.setOrgnizationType()
			int orgchartid=Integer.parseInt(request.getParameter("org_chart_id_emp"));
			////System.out.println("orgchartid-----2-----"+orgchartid);
			OrgType org=new OrgType();
			org.setOrg_type_id(orgtypeid);
			userdetails.setOrgnizationType(org);
			OrganisationChart orgchart=new OrganisationChart();
			orgchart.setOrg_chart_id(orgchartid);
			userdetails.setOrgchartdetails(orgchart);
			
		}else{
			if(userdetails.getOrgnizationType().getOrg_type_id() == 0){
				addFieldError("org_type_id","Please Select Organization Unit");
				flagdetails=false;
				flag="error";
			}
			////System.out.println("charid---------"+userdetails.getOrgchartdetails().getOrg_chart_id());
			
			if(userdetails.getOrgchartdetails().getOrg_chart_id() == 0){
				addFieldError("org_chart_id","Please Select Organization Name");
				flagdetails=false;
				flag="error";
			}
		}
		
		
		
		if(userdetails.getEmailid().length()>0){
			////System.out.println("IN emaild length");
			 String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			// Boolean b = this.getEmailid().matches(emailreg);
			// Boolean b= userdetails.getEmpid().matches(emailreg);
			 Boolean b=userdetails.getEmailid().matches(emailreg);
			 if (b == false) {
				    addFieldError("emailid","Email Id Is Not Valid");
					flagdetails=false;
					flag="error"; 
					
                }
		}
		////System.out.println("contact no---"+userdetails.getContactno());
		if(userdetails.getContactno().length()>0){
			String pattern = "\\d{6,10}";
			 if(!userdetails.getContactno().matches(pattern)){
				 addFieldError("contactno","Contact Number Should Be Numberic");
				 flagdetails=false;
					flag="error"; 
			 }
			
		}
		
		////System.out.println("flagdetails-----------"+flagdetails);
		if(flagdetails)
		{
	
		 ////System.out.println("useriddetails--------"+useriddetails);
		
			
		 int i=userdao.updateUser(useriddetails, userdetails);
		 ////System.out.println("i-------"+i);
			if(i>0){
				
				UserGroupDao usergroupdao=new UserGroupDao();
				UserGroup usergroup=new UserGroup();
				
				////System.out.println("old group id-------"+userdetails.getOldGroupID());
				
				if(userdetails.getOldGroupID()>0){
					usergroup.setUser_id(useriddetails);
					 groupflag=usergroupdao.getInactiveoldgroup(usergroup, userdetails.getOldGroupID());
					if(groupflag){
				
				
				usergroup.setGroup_id(userdetails.getUsergroupid());
				usergroupdao.addgroupId(usergroup);
				
				addActionMessage("User  No "+useriddetails+" Updated Successfully");
				flag="success";
				}
				}else{
					//old group id is zero
					usergroup.setUser_id(useriddetails);
					usergroup.setGroup_id(userdetails.getUsergroupid());
					usergroupdao.addgroupId(usergroup);
					
					addActionMessage("User  No "+useriddetails+" Updated Successfully");
					flag="success";
					
				}
			}/*else{
				if(i==-1){
				addActionError("User already exist.");
				flag="error";
				}else{
					addActionError("Error in server.");
					flag="error";
				}
			}*/
		}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		////System.out.println("flag-----////////---"+flag);
		return flag;
		 }else{
			 return "error";
		 }
	}
	
	
	/*@Override
	public void validate() {
		CommonValidation common=new CommonValidation();
		//System.out.println("test-------"+userdetails.getUser_type());
		if(userdetails.getUser_type().equals("0")){
			addFieldError("user_type","User Type should not be selected");
		}
	}*/
	
	
	public String getdesignationemloyee(){
		String data="";
		try{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserDetailDAO userdao=new UserDetailDAO();
		int empid=Integer.parseInt(request.getParameter("empid"));
		////System.out.println("empid--------"+empid);
		//String result=userdao.getDesignationfromemployee(empid);
		String designation=userdao.getDesignationDetailsForEmp(empid);
		String orgtype=userdao.getOrgtypeDetailsForEmp(empid);
		String orgchart=userdao.getOrgCharttypeDetailsForEmp(empid);
		
		
		
		data=designation+","+orgtype+","+orgchart;
		PrintWriter out = response.getWriter();
		 out.println(data);
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	
	
	public String getedituserdetails(){
		String data="";
		
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			UserDetailDAO userdao=new UserDetailDAO();
			int userid=Integer.parseInt(request.getParameter("userid"));
			String designation=userdao.getDesignationDetails(userid);
			String orgtype=userdao.getOrgtypeDetails(userid);
			String orgchart=userdao.getOrgCharttypeDetails(userid);
			data=designation+","+orgtype+","+orgchart;
			PrintWriter out = response.getWriter();
			 out.println(data);
		}catch(Exception e){
			e.printStackTrace();
			}
		return  null;
	}
	
	/*Below code use for User Page Relation Access*/
	
	public String displayusrrolepageaccess(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		UserDetailDAO userdetaildao=new UserDetailDAO();
		int iddetails=Integer.parseInt(request.getParameter("useriddetails"));
		 ////System.out.println("iddetails-------"+iddetails);
		userdetails=userdetaildao.getEditUserDetails(iddetails);
		////System.out.println("Roleid--------"+userdetails.getRole().getRole_id());
		////System.out.println("Test---//////////-----"+userdetails.getRole().getRole_name());
		//String rolename=userdetaildao.getRolename(Integer.parseInt(userdetails.getRole().toString()));
		////System.out.println("rolename----------------"+rolename);
		String Activerolename=userdetaildao.getActiveRolename(userdetails.getRole().getRole_id());
	    request.setAttribute("Activerolename", Activerolename);
		//}catch(Exception e){
			//e.printStackTrace();
		//}
		return "success";
	}
	
	public String pageRoleListForUserRole() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			//PageRoleDao roledao = new PageRoleDao();
			UserDetailDAO userdetaildao=new UserDetailDAO();
			int role_id=Integer.parseInt(request.getParameter("role_id"));
			//request.getSession().setAttribute("role_id", role_id);
			int useriddetails=Integer.parseInt(request.getParameter("useriddetails"));
			//System.out.println("useriddetails-----"+useriddetails);
			//System.out.println("Count------>" +role_id);
			//int cnt = roledao.getTotalPageRecords(role_id);
			//int group_id=Integer.parseInt(roledao.getGroupRole(role_id));
			////System.out.println("Count------>" + cnt);
			String[] cols = { "Page Id", "Page Name" };
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
			//total = roledao.getTotalPageRecords(role_id);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//int total, HttpServletRequest request,int roleid, String col,String dir
			//result = roledao.getPageRoleList(total,request,role_id,usrid);
			result=userdetaildao.getPageRoleListForUserPage(total, request, role_id, useriddetails);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			//System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}

	
	public String getAddUserRolePage(){
		try{
		//System.out.println("getAddUserRolePage---------------");	
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		MenuUserRolePage menuuserrole=new MenuUserRolePage();
		int userid=(Integer)request.getSession().getAttribute("userid");
		UserDetailDAO userdao=new UserDetailDAO();
		int useriddetails=Integer.parseInt(request.getParameter("useriddetails"));
		//System.out.println("useriddetails---"+useriddetails);
		
		int roleid=Integer.parseInt(request.getParameter("role_id"));
		//System.out.println("roleid---"+roleid);
		int pageid=Integer.parseInt(request.getParameter("pageid"));
		//System.out.println("useriddetails---"+useriddetails);
		
		int readaccess=Integer.parseInt(request.getParameter("readright"));
		//System.out.println("readaccess---"+readaccess);
		int writeaccess=Integer.parseInt(request.getParameter("writeright"));
		//System.out.println("writeaccess---"+writeaccess);
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowUserList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		if(create.equalsIgnoreCase("Y")){
		MenuUserRolePage menuuserrolepage=new MenuUserRolePage();
		menuuserrolepage.setUser_id(useriddetails);
		//menuuserrolepage.setRole_id(roleid);
		menuuserrolepage.setPage_id(pageid);
		menuuserrolepage.setReadaccess(readaccess);
		menuuserrolepage.setCreateaccess(writeaccess);
		menuuserrolepage.setDeleteaccess(writeaccess);
		menuuserrolepage.setEditaccess(writeaccess);
		menuuserrolepage.setViewdelete(writeaccess);
		/*MenuPageRole menupage =new MenuPageRole();
		menupage.setRole_id(roleid);
		menupage.setPage_id(pageid);
		menupage.setReadaccess(readright);
		menupage.setCreateaccess(writeright);
		menupage.setDeleteaccess(writeright);
		menupage.setEditaccess(writeright);*/
		String flag=userdao.addPageId(menuuserrolepage, useriddetails);
		out.print(flag);}
		else{
			out.print("User Dont Have Access Rights");
		}
		}catch(Exception e){e.printStackTrace();}
		
		return null;
	}
	
	public String getDeleteUserRolePage(){
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpServletRequest request = ServletActionContext.getRequest();
			PrintWriter out = response.getWriter();
			//MenuUserRolePage menuuserrolepage =new MenuUserRolePage();
			UserDetailDAO userdao=new UserDetailDAO();
			int userrolepageid=Integer.parseInt(request.getParameter("userrolepageid"));
			//System.out.println("userrolepageid-------"+userrolepageid);
			int userid=(Integer)request.getSession().getAttribute("userid");
			String msg=userdao.getDeletRecord(userrolepageid, userid);
			out.println(msg);
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	
	public String getOrgTypeForUser() {
		//TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		UserDetailDAO userdao=new UserDetailDAO();
		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = userdao.getOrgTypeId();
		List<String> l2 = userdao.getOrgTypeName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='orgType"+l1.get(i)+"' value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		//System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		//System.out.println("regionTypeAjaxString------"+regionTypeAjaxString);
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public String getUnitNameForUser() {
		////System.out.println("test");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int orgtypeid = Integer.parseInt(request.getParameter("orgid"));
		//System.out.println("orgtypeid----"+orgtypeid);
		//TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		UserDetailDAO userdao=new UserDetailDAO();
		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = userdao.getUnitId(orgtypeid);
		List<String> l2 = userdao.getUnitName(orgtypeid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='orgName"+l1.get(i)+"' value=" + l1.get(i).toString()
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

		//System.out.println(orgtypeid);
		return null;

	}

	
	
	

}
