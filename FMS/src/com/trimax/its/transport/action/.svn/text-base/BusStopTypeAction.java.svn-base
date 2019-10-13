package com.trimax.its.transport.action;

import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.classic.Validatable;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.fare.dao.FareChartMasterDao;
import com.trimax.its.transport.dao.BusStopTypeDao;
import com.trimax.its.transport.model.BusStopType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class BusStopTypeAction extends ActionSupport implements Validatable{

	private BusStopType busStopType;
	
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
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		BusStopTypeDao dao=new BusStopTypeDao();
		busStopType=dao.getStopTypeById(id);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"BusStopTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		int id=Integer.parseInt(request.getParameter("id"));
		
		BusStopTypeDao dao=new BusStopTypeDao();
		
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(id, "point_type");
		
			if(status.equals("")){
		String s=dao.deleteStopType(id);
		
		if(s.equals("success")){
			addActionMessage("Bus stop type id "+id+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
			}else{
				addActionError(status);
			}
		
		return "success";
		}else
		{//setDeletestatus("success");
		addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		BusStopTypeDao dao=new BusStopTypeDao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"BusStopTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		busStopType.setCreatedDate(new java.util.Date());
		busStopType.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		busStopType.setDeletedStatus(0);
		busStopType.setUpdatedBy(0);
		busStopType.setUpdatedDate(null);
		busStopType.setNote("");
		busStopType.setStatus("ACTIVE");
		busStopType.setOrg_type_id(0);
		
		int i=dao.addStopType(busStopType);
		if(i>0){
			addActionMessage("Bus Stop Type Id "+i+" Created Successfully.");
		}else{
			if(i==-1){
				addActionError("Bus Stop Type Already Exist.");
				return INPUT;
			}else{
				addActionError("Database Error Retry Again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;}
		else{
			return INPUT;
		}
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"BusStopTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		BusStopTypeDao dao=new BusStopTypeDao();
		if(edit.equalsIgnoreCase("Y")){
		busStopType.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		busStopType.setUpdatedDate(new java.util.Date());
		
		int i=dao.updateStopType(busStopType);
		
		if(i>0){
			addActionMessage("Bus Stop Type Id "+busStopType.getId()+" Updated Successfully.");
		}else{
			if(i==-1){
				addActionError("Bus Stop Type Already Exist.");
				return INPUT;
			}else{
				addActionError("Database Error Retry Again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;}
		else{
			return INPUT;
		}
	}
	
	@SkipValidation
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			BusStopTypeDao dao = new BusStopTypeDao();
			//int cnt = dao.getTotalRecords();

			String[] cols = { "","id","pointTypeName","status"};
			String[] dbcol={"","id","pointTypeName","status"};
						
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
			total = dao.getTotalRecords(request,dbcol[col],dir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[col],sdir);

			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}
	
	public void validate(){
		
		if(busStopType.getPointTypeName()==null || busStopType.getPointTypeName().length()<=0 || busStopType.getPointTypeName().trim().equals("")){
			addFieldError("pointTypeName","Please Enter Point Type Name");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(busStopType.getPointTypeName())){
				addFieldError("pointTypeName","Special Characters Not Allowed For PointTypeName"); 
			 }
		}			
	}

	public BusStopType getBusStopType() {
		return busStopType;
	}

	public void setBusStopType(BusStopType busStopType) {
		this.busStopType = busStopType;
	}
}
