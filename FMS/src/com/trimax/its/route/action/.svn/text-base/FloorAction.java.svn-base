package com.trimax.its.route.action;

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
import com.trimax.its.route.dao.FloorDao;
import com.trimax.its.route.model.Floor;
import com.trimax.its.transport.dao.BusStopTypeDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class FloorAction extends ActionSupport implements Preparable{

	private Floor floor;
	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	private Map<Integer,String> orgNameMap;
	
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
		HttpServletRequest request = ServletActionContext.getRequest();
		FloorDao dao=new FloorDao();
	int busstation_id=request.getParameter("orgid")!= null ? Integer.parseInt(request.getParameter("orgid")):00;
	if(busstation_id!=00){
	setOrgNameMap(dao.getOrganizationName(busstation_id));
	}
	else{
		setOrgNameMap(dao.getOrganizationName());
	}
		return "add";
	}
	@SkipValidation
	public String addbus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		FloorDao dao=new FloorDao();
	int busstation_id=request.getParameter("florgid")!= null ? Integer.parseInt(request.getParameter("florgid")):00;
	if(busstation_id!=00){
	setOrgNameMap(dao.getOrganizationName(busstation_id));
	}
	else{
		setOrgNameMap(dao.getOrganizationName());
	}
		return "addbus";
	}
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		FloorDao dao=new FloorDao();
		floor=dao.getFloorById(id);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
HttpServletRequest request = ServletActionContext.getRequest();
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "FloorAction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
if(delete.equalsIgnoreCase("Y")){
		int id=Integer.parseInt(request.getParameter("id"));
		
		FloorDao dao=new FloorDao();
		String s=dao.deleteFloor(id);
		

		if(s.equals("success")){
			addActionMessage("Floor id "+id+" deleted successfully.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "FloorAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		FloorDao dao=new FloorDao();
		
		floor.setCreated_date(new java.util.Date());
		floor.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		floor.setDeleted_status(0);
		floor.setUpdated_by(0);
		floor.setUpdated_date(null);
		floor.setNotes("");
		
		int i=dao.addStopType(floor);
		
		if(i>0){
			addActionMessage("Floor id "+i+" created successfully.");
		}else{
			if(i==-1){
				addActionError("Floor already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		FloorDao dao=new FloorDao();
		
		floor.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		floor.setUpdated_date(new java.util.Date());
		
		int i=dao.updateStopType(floor);
		
		if(i>0){
			addActionMessage("Floor id "+floor.getFloor_id()+" updated successfully.");
		}else{
			if(i==-1){
				addActionError("Floor already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;
	}
	
	@SkipValidation
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			FloorDao dao = new FloorDao();
			//int cnt = dao.getTotalRecords();

			String[] cols = { "floor_id","floor_name","orgChart.org_name","status"};
			String[] dbcol={"floor_id","floor_name","orgChart.org_name","status"};
						
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
		CommonValidation cv=new CommonValidation();
		if(floor.getFloor_name().length()<=0 || floor.getFloor_name().trim().equals(" ")){
			addFieldError("floor_name","Please Enter Floor Name");
		}
		if (!cv.isSpecialChar(floor.getFloor_name())){
				addFieldError("floor_name","Special characters not allowed"); 
			 }
			 if (!cv.isEmpty(floor.getFloor_name())){
					addFieldError("floor_name","Space is not allowed"); 
				 }	
		
		if(floor.getOrgChart().getOrg_chart_id()<=0){
			addFieldError("orgChart.parent_id","Please Select Depot Name");
		}	
	}
	
	public void prepare() throws Exception {
		FloorDao dao = new FloorDao();
		
		

	}

	

	public Map<Integer,String> getOrgNameMap() {
		return orgNameMap;
	}

	public void setOrgNameMap(Map<Integer,String> orgNameMap) {
		this.orgNameMap = orgNameMap;
	}
}
