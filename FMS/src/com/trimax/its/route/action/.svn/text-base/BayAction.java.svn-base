package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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
import com.trimax.its.orgchart.dao.OrgChartDao;
import com.trimax.its.route.dao.BayDao;
import com.trimax.its.route.dao.FloorDao;
import com.trimax.its.route.model.Bay;

public class BayAction extends ActionSupport implements Preparable{

	private Bay bay;
	public Bay getBay() {
		return bay;
	}

	public void setBay(Bay bay) {
		this.bay = bay;
	}

	private Map<Integer,String> floorNameMap;
	private Map<Integer,String> orgNameMap;
	private int orgName;
	
	public int getOrgName() {
		return orgName;
	}

	public void setOrgName(int orgName) {
		this.orgName = orgName;
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
		HttpServletRequest request = ServletActionContext.getRequest();
		FloorDao daoo = new FloorDao();
		BayDao baydao=new BayDao();
		int busstation_id=request.getParameter("orgid")!= null ? Integer.parseInt(request.getParameter("orgid")):00;
		if(busstation_id!=00){
			setOrgNameMap(daoo.getOrganizationName(busstation_id));
			//
			//baydao.System.out.println(getFloorName());
			}
			else{
				setOrgNameMap(daoo.getOrganizationName());
			}
		return "add";
	}
	
	@SkipValidation
	public String addBus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		FloorDao daoo = new FloorDao();
		BayDao baydao=new BayDao();
		int busstation_id=request.getParameter("bayorgid")!= null ? Integer.parseInt(request.getParameter("bayorgid")):00;
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
		
		BayDao dao=new BayDao();
		bay=dao.getBayById(id);
		System.out.println(bay.getFloor().getFloor_id()+"::"+bay.getFloor().getFloor_name());
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
HttpServletRequest request = ServletActionContext.getRequest();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		BayDao dao=new BayDao();
		String s=dao.deleteBay(id);		

		if(s.equals("success")){
			addActionMessage("Bay id "+id+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
		
		return "success";
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		BayDao dao=new BayDao();
		
		bay.setCreated_date(new java.util.Date());
		bay.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		bay.setDeleted_status(0);
		bay.setUpdated_by(0);
		bay.setUpdated_date(null);
		bay.setNotes("");
		System.out.println(bay.getFloor().getFloor_id()+"::"+bay.getFloor().getFloor_name());
		//bay.setParent_id(bay.getFloor().getOrgChart().getOrg_chart_id());
		
		int i=dao.addBay(bay);
		
		if(i>0){
			addActionMessage("Bay id "+i+" created successfully.");
		}else{
			if(i==-1){
				addActionError("Bay already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;
	}
	/*public String AddBus(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int busstation_id=request.getParameter("orgid")!= null ? Integer.parseInt(request.getParameter("orgid")):00;
		BayDao dao=new BayDao();
		
		bay.setCreated_date(new java.util.Date());
		bay.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		bay.setDeleted_status(0);
		bay.setUpdated_by(0);
		bay.setUpdated_date(null);
		bay.setNotes("");
		System.out.println(bay.getFloor().getFloor_id()+"::"+bay.getFloor().getFloor_name());
		//bay.setParent_id(bay.getFloor().getOrgChart().getOrg_chart_id());
		
		int i=dao.addBay(bay);
		
		if(i>0){
			addActionMessage("Bay id "+i+" created successfully.");
		}else{
			if(i==-1){
				addActionError("Bay already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;
	}
	*/
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		BayDao dao=new BayDao();
		
		bay.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		bay.setUpdated_date(new java.util.Date());
		
		int i=dao.updateBay(bay);

		if(i>0){
			addActionMessage("Bay id "+bay.getBay_id()+" updated successfully.");
		}else{
			if(i==-1){
				addActionError("Bay already exist.");
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
			BayDao dao = new BayDao();
			//int cnt = dao.getTotalRecords();

			String[] cols = { "bay_id","bay_name","floor.floor_name","orgChart.org_name","status"};
			String[] dbcol={"bay_id","bay_name","floor.floor_name","orgChart.org_name","status"};
						
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
		if(bay.getBay_name().length()<=0 || bay.getBay_name().trim().equals("")){
			addFieldError("bay_name","Please Enter Bay Name");
		}else{
			CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(bay.getBay_name())){//(!Pattern.matches("^[^()<>;\\*%@#?!$]*$", bay.getBay_name())){
				addFieldError("bay_name","Special characters not allowed"); 
			 }
		}			
		
		if(bay.getFloor()==null || bay.getFloor().getFloor_id()==0){
			addFieldError("floor.floor_id","Please Select Floor Name");
		}	
		
		if(bay.getFloor().getOrgChart()==null || bay.getFloor().getOrgChart().getOrg_chart_id()==0){
			addFieldError("floor.orgChart.org_chart_id","Please Select Depot");
		}
		
		try{
		//get floor name by id
		BayDao dao=new BayDao();
		bay.getFloor().setFloor_name(dao.getFloorNameById(bay.getFloor().getFloor_id()));
		}
		catch(Exception e){}
	}
	
	public void prepare() throws Exception {
		//BayDao dao = new BayDao();
		
		//setFloorNameMap(dao.getFloorName());
		
		
		
		

	}

	@SkipValidation
	public String getFloorName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		BayDao dao = new BayDao();
		String regionTypeAjaxString=dao.getFloorNameByByPid(Integer.parseInt(request.getParameter("orgid")));
		
		
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
	

	public Map<Integer,String> getFloorNameMap() {
		return floorNameMap;
	}

	public void setFloorNameMap(Map<Integer,String> floorNameMap) {
		this.floorNameMap = floorNameMap;
	}

	public Map<Integer,String> getOrgNameMap() {
		return orgNameMap;
	}

	public void setOrgNameMap(Map<Integer,String> orgNameMap) {
		this.orgNameMap = orgNameMap;
	}

	
}
