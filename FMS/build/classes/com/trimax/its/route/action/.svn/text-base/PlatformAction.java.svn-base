package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.fare.dao.FareChartMasterDao;
import com.trimax.its.route.dao.BayDao;
import com.trimax.its.route.dao.FloorDao;
import com.trimax.its.route.dao.PlatformDao;
import com.trimax.its.route.model.Platform;
import com.trimax.its.transport.dao.BusStopTypeDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PlatformAction extends ActionSupport implements Preparable{

	private Platform platform;
	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	private Map<Integer,String> bayNameMap;
	private Map<Integer,String> floorNameMap;
	private Map<Integer,String> orgNameMap;
	
	public Map<Integer, String> getFloorNameMap() {
		return floorNameMap;
	}

	public void setFloorNameMap(Map<Integer, String> floorNameMap) {
		this.floorNameMap = floorNameMap;
	}

	public Map<Integer, String> getOrgNameMap() {
		return orgNameMap;
	}

	public void setOrgNameMap(Map<Integer, String> orgNameMap) {
		this.orgNameMap = orgNameMap;
	}

	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	
	@SkipValidation
	public String view() {
		return "view";
	}
	
	@SkipValidation
	public String add() {
		return "add";
	}
	@SkipValidation
	public String getPlatformDetails()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		FloorDao daoo = new FloorDao();
		BayDao baydao=new BayDao();
		int busstation_id=request.getParameter("plorgid")!= null ? Integer.parseInt(request.getParameter("plorgid")):00;
		if(busstation_id!=00){
			setOrgNameMap(daoo.getOrganizationName(busstation_id));
			setFloorNameMap(baydao.getFloorName(busstation_id));
			}
			else{
				setOrgNameMap(daoo.getOrganizationName());
			}
		return "addbus";
	}
	
	
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		PlatformDao dao=new PlatformDao();
		platform=dao.getPlatformById(id);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
HttpServletRequest request = ServletActionContext.getRequest();
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PlatformAction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();	
if(delete.equalsIgnoreCase("Y")){
		int id=Integer.parseInt(request.getParameter("id"));
		
		PlatformDao dao=new PlatformDao();
		String s=dao.deletePlatform(id);
		
		if(s.equals("success")){
			addActionMessage("Platform id "+id+" deleted successfully.");
			}else{
				addActionMessage("Database error retry again.");
			}
		
		return "success";
}else{
	addActionMessage("Access Denied - User Does Not Have Access Privileges");
	return "success";
}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
	
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PlatformAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();	
		if(create.equalsIgnoreCase(("Y"))){
			
		
		PlatformDao dao=new PlatformDao();
		
		platform.setCreated_date(new java.util.Date());
		platform.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		platform.setDeleted_status(0);
		platform.setUpdated_by(0);
		platform.setUpdated_date(null);
		platform.setParent_id(platform.getBay().getParent_id());
		//platform.setFloor_id(platform.getBay().getFloor().getFloor_id());
		
		int i=dao.addPlatform(platform);
		
		if(i>0){
			addActionMessage("Platform id "+i+" created successfully.");
		}else{
			if(i==-1){
				addActionError("Platform already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;
		}else{
			

			return  INPUT;
		}
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PlatformAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();	
		if(edit.equalsIgnoreCase("Y")){
		PlatformDao dao=new PlatformDao();
		
		platform.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		platform.setUpdated_date(new java.util.Date());


		
		int i=dao.updateStopType(platform);
		
		if(i>0){
			addActionMessage("Platform id "+platform.getPlatform_id()+" updated successfully.");
		}else{
			if(i==-1){
				addActionError("Platform already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	@SkipValidation
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PlatformDao dao = new PlatformDao();
		

			String[] cols ={"platform_id","platform_name","bay.bay_name","floor.floor_name","orgChart.org_name","latitude","longitude","geo_fence_data","landmark",
					"platform_description","status"};
			String[] dbcol={"platform_id","platform_name","bay.bay_name","floor.floor_name","orgChart.org_name","latitude","longitude","geo_fence_data","landmark",
					"platform_description","status"};
						
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
				if (sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			total = dao.getTotalRecords(request,dbcol[col],dir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[col],dir);

			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}
	
	public void validate(){
		if(platform.getPlatform_name().length()<=0 || platform.getPlatform_name().trim().equals("")){
			addFieldError("platform_name","Please Enter Platform Name");
		}else{
			CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(platform.getPlatform_name())){//(!Pattern.matches("^[^()<>;\\*%@#?!$]*$", platform.getPlatform_name())){
				addFieldError("platform_name","Special characters not allowed"); 
			 }
		}	
		
		if(platform.getBay()==null || platform.getBay().getBay_id()==0){
			addFieldError("bay.bay_id","Please Select Bay Name");
		}	
		
		if(platform.getBay().getFloor()==null || platform.getBay().getFloor().getFloor_id()==0){
			addFieldError("bay.floor.floor_id","Please Select Floor Name");
		}	
		
		if(platform.getBay().getFloor().getOrgChart()==null || platform.getBay().getFloor().getOrgChart().getOrg_chart_id()==0){
			addFieldError("bay.floor.orgChart.org_chart_id","Please Select Depot");
		}
		
		try{
		//get floor name by id
		BayDao dao=new BayDao();
		platform.getBay().getFloor().setFloor_name(dao.getFloorNameById(platform.getBay().getFloor().getFloor_id()));
		platform.getBay().setBay_name(dao.getBayById(platform.getBay().getBay_id()).getBay_name());
		}
		catch(Exception e){}
	}
	
	public void prepare() throws Exception {
		//PlatformDao dao = new PlatformDao();
		
		//setBayNameMap(dao.getBayName());
		
		FloorDao dao = new FloorDao();
		
		setOrgNameMap(dao.getOrganizationName());

	}

	public Map<Integer,String> getBayNameMap() {
		return bayNameMap;
	}

	public void setBayNameMap(Map<Integer,String> bayNameMap) {
		this.bayNameMap = bayNameMap;
	}

	@SkipValidation
	public String getBayName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PlatformDao dao = new PlatformDao();
		String regionTypeAjaxString=dao.getBayNameByByPid(Integer.parseInt(request.getParameter("orgid")));
		
		
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
}
