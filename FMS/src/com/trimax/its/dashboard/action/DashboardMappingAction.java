package com.trimax.its.dashboard.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.dashboard.doa.DashboardMappingDoa;
import com.trimax.its.dashboard.model.ChartMapping;
import com.trimax.its.dashboard.model.DashBoardUserMapping;
import com.trimax.its.dashboard.model.DashboardModel;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.memo.dao.MemoDao;
import com.trimax.its.memo.model.Memo;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.usermanagement.model.UserDetails;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.UserGroupDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.utility.model.UserGroup;

public class DashboardMappingAction extends ActionSupport  {

    private Map<Integer, String> userlist;
    private Map<Integer, String> dashboardlist;
    private Map<String, String> roleList;
    private Map<Integer, String> userroleList;

	DashboardMappingDoa doa=new DashboardMappingDoa();
	private UserDetails userdetails;
	private ChartMapping dashMapping;
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	private String roleid;
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	DashboardModel dashboardmodel;
	
	
	
	public Map<Integer, String> getUserroleList() {
		return userroleList;
	}

	public void setUserroleList(Map<Integer, String> userroleList) {
		this.userroleList = userroleList;
	}

	

	public Map<String, String> getRoleList() {
		return roleList;
	}

	public void setRoleList(Map<String, String> roleList) {
		this.roleList = roleList;
	}

	public Map<Integer, String> getDashboardlist() {
		return dashboardlist;
	}

	public void setDashboardlist(Map<Integer, String> dashboardlist) {
		this.dashboardlist = dashboardlist;
	}

	public DashboardModel getDashboardmodel() {
		return dashboardmodel;
	}

	public void setDashboardmodel(DashboardModel dashboardmodel) {
		this.dashboardmodel = dashboardmodel;
	}

	
	 @SkipValidation
	public String viewDashboard(){
		return "success";
	}
	 @SkipValidation
	public String createDashboard()
	{
		try {

			userlist = doa.getUserlist();
			dashboardlist = doa.getDashboardlist();

		} catch (Exception e) {

		}
		return "success";

	}
	//getAllDashboard
	 @SkipValidation
	 public String mapUserDashboard()
	 {	
		 userlist=doa.getUserlist();
		 roleList=doa.roleList();
	 
		    HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String arr[]=request.getParameterValues("chkbox");
			
			int userId=Integer.parseInt(request.getSession().getAttribute("user_Id").toString());
			UserGroup usergroup=new UserGroup();
			String dashboardIds=dashboardId(arr);
			//System.out.println("dashboard"+dashboardIds);
			doa.updateDashboardId(userId);
			
			boolean isGroupUnmapped=true;
			try{
			if(arr!=null){
				if(arr.length>0)
				{
			
					for(int i=0;i<arr.length;i++)
					{
						ChartMapping dashMapping =new ChartMapping();
						dashMapping.setUser_id(userId);
						int dashboardId=Integer.parseInt(arr[i].trim());
						dashMapping.setChart_id(dashboardId);
						doa.mapUserDashboard(dashMapping);
					}
				}
			}
			}catch(Exception e){
			
				e.printStackTrace();
			}
			addActionMessage("Chart Map Successfully");
			dashboardlist = doa.getDashboardlist();
			//userdetails.setUser_id(0);
			request.getSession().removeAttribute("user_Id");
			return "success";
	 }
	 @SkipValidation
	 public String dashboardId(String arr[]){
          String dashboardIdTemp="";
		 String dashboardId="";
		try{ 
			if(arr.length==1){
				dashboardId=arr[0].trim();
			}else{
					for(int i=0;i<arr.length;i++){
						dashboardIdTemp+=arr[i].trim()+",";
				 
				 
						}
			 dashboardId=dashboardIdTemp.substring(0,dashboardIdTemp.length()-1);
		 }
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		 
		 return dashboardId;
	 }
	 @SkipValidation
	public String getAllDashboard() throws IOException
	{
		try {
			 addActionMessage("  ");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.getSession().setAttribute("user_Id",request.getParameter("userId").toString());
			
			userlist=doa.getUserlist();
			
			String[] cols =    {"","chart_name", "chart_name","dashboard_desc","status"};
			String[] dbcols = { "","chart_name", "chart_name","dashboard_desc","status"};
					
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
			total = doa.getTotalRecords(SEARCH_TERM);
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
	        int userid=Integer.parseInt(request.getSession().getAttribute("user_Id").toString());
			result = doa.getData(total, request,userid,dbcols[Integer.parseInt(sCol)],sdir);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return null;
	}
	public Map<Integer, String> getUserlist() {
		return userlist;
	}
	public void setUserlist(Map<Integer, String> userlist) {
		this.userlist = userlist;
	}

	public UserDetails getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserDetails userdetails) {
		this.userdetails = userdetails;
	}

	public ChartMapping getDashMapping() {
		return dashMapping;
	}

	public void setDashMapping(ChartMapping dashMapping) {
		this.dashMapping = dashMapping;
	}
	
	
	public String CreateDashboardName()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		dashboardmodel.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		dashboardmodel.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		dashboardmodel.setDeleted_status(0);
		dashboardmodel.setUpdated_date(new java.util.Date());
		dashboardmodel.setCreated_date(new java.util.Date());
		dashboardmodel.setDashboardname(dashboardmodel.getDashboardname());
		dashboardmodel.setStatus(dashboardmodel.getStatus());
		System.out.println("dashboardmodel.getDashboardnamedesc()"+dashboardmodel.getDashboardnamedesc());
		dashboardmodel.setDashboardnamedesc(dashboardmodel.getDashboardnamedesc());
		
		String check=doa.CheckDuplicateRecord(dashboardmodel.getDashboardname());
		if(check.equals("singlerecord"))
		{
		int a=doa.insertDashboardModel(dashboardmodel);
		}
		else 
		{
			addActionError("Dashboard Name Already Exit");
			return "input";
			
		}
		dashboardlist = doa.getDashboardlist();
		return "success";
	}
	
	 @SkipValidation
	public String getAllDashboardMapping() throws IOException
	{
		try {
			 addActionMessage("  ");
			 
			 
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.getSession().setAttribute("user_Id",request.getParameter("userId").toString());
			
			userlist=doa.getUserlist();
			
			String[] cols =    {"","dashboardname", "dashboardname"};
			String[] dbcols = { "","dashboardname", "dashboardname"};
					
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
			total = doa.getTotalRecords(SEARCH_TERM);
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
	        int userid=Integer.parseInt(request.getSession().getAttribute("user_Id").toString());
			result = doa.getDashboardData(total, request,userid,dbcols[Integer.parseInt(sCol)],sdir);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	//Mapping dashboardUser start
	 @SkipValidation
	 public String mapUserDashboardMapping()
	 {	    userlist=doa.getUserlist();
	 		roleList=doa.roleList();
		    HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String arr[]=request.getParameterValues("chkbox");
			
			int userId=Integer.parseInt(request.getParameter("user_Id"));
			System.out.println("&&&userId"+userId);
			UserGroup usergroup=new UserGroup();
			String dashboardIds=dashboardId(arr);
			//System.out.println("dashboard"+dashboardIds);
			doa.updateDashboardUserId(userId);
			
			boolean isGroupUnmapped=true;
			try{
			if(arr!=null){
				if(arr.length>0)
				{
			
					for(int i=0;i<arr.length;i++)
					{
						DashBoardUserMapping dashMapping =new DashBoardUserMapping();
						dashMapping.setUser_id(userId);
						int dashboardId=Integer.parseInt(arr[i].trim());
						System.out.println("dashboardId="+dashboardId);
						dashMapping.setDashboard_id(dashboardId);
						doa.mapUserDashboardMapping(dashMapping);
					}
				}
			}
			}catch(Exception e){
			
				e.printStackTrace();
			}
			addActionMessage("Dashboard Map Successfully");
			dashboardlist = doa.getDashboardlist();
			//userdetails.setUser_id(0);
			request.getSession().removeAttribute("user_Id");
			return "success";
	 }
	 
	//dashboardUser mapping end
	 
	 //showalldashboard data show
	 @SkipValidation
	 public String getAllDashboardMster() throws IOException
		{
			try {
				 addActionMessage("  ");
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				//request.getSession().setAttribute("user_Id",request.getParameter("userId").toString());
				
				userlist=doa.getUserlist();
				
				String[] cols =    {"","id", "dashboardname","dashboard_desc","status"};
				String[] dbcols = { "","id", "dashboardname","dashboard_desc","status"};
						
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
				total = doa.getTotalRecords(SEARCH_TERM);
				COL_NAME = colName;
				DIR = dir;
				START = start;

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
		       // int userid=Integer.parseInt(request.getSession().getAttribute("user_Id").toString());
				result = doa.getData1(total,request,dbcols[Integer.parseInt(sCol)],sdir);
				out.print(result);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				
			}
			return null;
		}
	 //end list of dashboard
	//deleted Dashboard
	 @SkipValidation
	 public String deleteDashboardAction()
		{
			
			
		 DashboardMappingDoa dao=new DashboardMappingDoa();
		 DashboardModel dashboardmodel=new DashboardModel();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			
			dashboardmodel.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			try{
				dao.deleteDeviceType(dashboardmodel,Integer.parseInt(request.getParameter("memoid")));
				//dao.deleteComplaint(complaint, Integer.parseInt(request.getParameter("complaintid")));
			}catch(Exception e)
			{
				
				
				return "input";
			}finally{
				
				addActionMessage("DashBoard Id  "+request.getParameter("memoid")+" Deleted Successfully");
			}
			return "success";
			
			
		}
	 //end dashboard
	//edit dashboard
	 
	 @SkipValidation
		public String editDashBoard()
		{
		 DashboardMappingDoa dao=new DashboardMappingDoa();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			dashboardmodel=dao.getEditedDashboard(Integer.parseInt(request.getParameter("memoidd")));

			return "success";
			
		}
	 //end edit dashboard
	//add edited dashboard
	 
	 public String addeditedDashboard()throws ParseException
		{
		 DashboardMappingDoa dao=new DashboardMappingDoa();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			//AccessRightsDao  accessrightdao=new AccessRightsDao();
			//AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			dashboardlist = doa.getDashboardlist();
			/*accessrights=accessrightdao.accessRightsdetails(usrsessionid, "memoNoticeAction.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(edit.equalsIgnoreCase("Y")){*/
			dashboardmodel.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			
		if(doa.checkDashboardForUpdate(dashboardmodel.getDashboardname(),dashboardmodel.getId()))
				{
		
		try{
			
			dashboardmodel=doa.updateDashboard(dashboardmodel,dashboardmodel.getId());
				}catch(Exception e){
				
				return "input";
			}finally{
				
				
				addActionMessage("Dashboard Id "+ dashboardmodel.getId()+" Updated Successfully" );
				
			}
				}
		
		else if (!dao.checkDashboardName(dashboardmodel.getDashboardname())) {
			//System.out.println("sectorName present");
			try {
				dashboardmodel=doa.updateDashboard(dashboardmodel,dashboardmodel.getId());
				//dao.upDateLineString(revenue.getGeo_fence(),revenue.getSector_id());
				//setUpdatestatus("success");
				addActionMessage("Dashboard Id " + dashboardmodel.getId()
						+ " Updated Successfully");
			} catch (Exception ex) {
				//System.out.println("The err1"+ex);
				Logger logger = TrimaxLogger.getTrimaxLogger();
                logger.error(this.getClass() + "$Exception in LoginAction action",
                        ex);
                SaveRequest.save(request);
                ErrorLog log = new ErrorLog();
                log.setMsg(ex.getMessage());
                ErrorLogDAO.saveException(log);
				
				//addActionMessage("Dashboard Name Already Exit");
				return "input";
			} finally {
//				setUpdatestatus("success");
//				addActionMessage("Revenue Sector Id " + revenue.getSector_id()
//						+ " Updated Successfully");
			}
		}
		
		
		 else {
			 addActionError("Dashboard Name Already Exit");
				return "input";
			}
			return "success";
			/*}else{
				return "input";
			}*/
		}
	 //end edited dashboard
	 //get userlist by roleid
	 @SkipValidation
	 public String getListOfUser()
	 {
		 HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
		
		 DashboardMappingDoa dao=new DashboardMappingDoa();
		 String userlist=null;
		 userlist=dao.roleUserLista(request.getParameter("roleid"));
		 System.out.println("userlist......"+userlist);
		 			
			try {
				out = response.getWriter();
				out.print(userlist);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		 return null;
	 }
	 @SkipValidation
	 public String getRoleDashboardMapping()
	 {
		 roleList=doa.roleList();
		  userlist=doa.getUserlist();
		 return "success";
	 }
	 //end
	 @SkipValidation
		public void validate() {
			Common commons = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			
			CommonValidation common=new CommonValidation();
			
			
			

			if(dashboardmodel.getDashboardname().trim().equals("") || dashboardmodel.getDashboardname().equals(" "))
			{
				

				addFieldError("dashboardmodel.dashboardname", " Dashboard Name  is Required");
				
			}
			
			if(!common.isSpecialChar(dashboardmodel.getDashboardname()))
			{
				

				addFieldError("dashboardmodel.dashboardname", "Special Character For Dashboard Name  is  Not Allowed");
				
			}

			
		
			
			/*if(!common.isSpecialChar(dashboardmodel.getDashboardnamedesc()))
			{
				

				addFieldError("dashboardmodel.dashboardnamedesc", " Special Character For Dashboard Description  is Not Allowed");
				
			}*/
			
			}
	 
}
