package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.dao.VendorDAO;
import com.trimax.its.transport.dao.ScheduleTypeDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.BreakDownTypeDetails;
import com.trimax.its.vehicle.dao.BreakDownTypeDetailsDao;

public class BreakDownTypeDetailsAction extends ActionSupport implements Preparable{
 
	BreakDownTypeDetails breakdowntypedeails;
	public BreakDownTypeDetails getBreakdowntypedeails() {
		return breakdowntypedeails;
	}

	public void setBreakdowntypedeails(BreakDownTypeDetails breakdowntypedeails) {
		this.breakdowntypedeails = breakdowntypedeails;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	@SkipValidation
	public String execute() throws IOException {
		return null;
	}
	
	@SkipValidation
	public String showBreakdownTypeList()
	{
		return "success";
	}
	@SkipValidation
	public String showCreateBreakdownType()
	{
		return "success";
	}
	
	@Override
	public void prepare() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			BreakDownTypeDetailsDao breakdowntype = new BreakDownTypeDetailsDao();

			int cnt = breakdowntype.getTotalRecords();
			//System.out.println("Count------>" + cnt);
			String[] cols = {"","breakdown_type_Id","breakdown_name","breakdown_category", "breakdown_system_type", "breakdown_system_sub_type" ,"notes"};
			String[] dbcol={"","breakdown_type_Id","breakdown_name","breakdown_category","breakdown_system_type","breakdown_system_sub_type","notes"};
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
			total = breakdowntype.getTotalRecords();
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//System.out.println("toatlll--------"+total);
			//System.out.println("sCol---------"+sCol);
			
			//System.out.println("sdir--------"+sdir);
			result = breakdowntype.getBreakdownTypeList(total,request,dbcol[Integer.parseInt(sCol)], sdir);
			//System.out.println("REsult of datatable------>" + result);
			
			//	result = breakdowntype.getBreakdownTypeList(total, request,cols[Integer.parseInt(sCol)], sdir);
			//	//System.out.println("result----------"+result);
				out.print(result);
			
			
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
	

	public String addBreakdownType(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showBreakdowntypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		try{
			
			BreakDownTypeDetailsDao breakdowntype = new BreakDownTypeDetailsDao();
			//int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			breakdowntypedeails.setCreated_by(usrsessionid);
			breakdowntypedeails.setCreated_date(new java.util.Date());
			int i=breakdowntype.addbreakdowntypedeails(breakdowntypedeails);
			if(i>0){
				addActionMessage("BreakDownType  Id "+i+" Created Successfully.");
				flag="success";
			}else{
				if(i==-1){
					breakdowntypedeails.setBreakdown_name("");
					addActionError("Breakdown Name  Already exist.");
					flag="error";
				}else{
					addActionError("Database Error Retry Again.");
					flag="error";
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
	public String deleteBreakdowntype(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showBreakdowntypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		 int breakdowntypeid=Integer.parseInt(request.getParameter("breakdowntypeid"));
		 //System.out.println("breakdowntypeid---"+breakdowntypeid);
			BreakDownTypeDetailsDao breakdowntype = new BreakDownTypeDetailsDao();
		if(breakdowntype.deletedBreakDownType(breakdowntypeid)){
			addActionMessage("BreakdownType  Id  "+breakdowntypeid+" Deleted Successfully");
		}else{
			addActionMessage("Database error retry again.");
		}
		
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	

	public String updateBreakdownTypeDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showBreakdowntypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		// int breakdowntypeid=breakdowntypedeails.getBreakdown_type_Id();
		 int breakdowntypeid=Integer.parseInt(request.getParameter("beakdowntypeid"));
		 //System.out.println("breakdowntypeid--------/////////////"+breakdowntypeid);
			BreakDownTypeDetailsDao breakdowntype = new BreakDownTypeDetailsDao();
			/*if(breakdowntype.deletedBreakDownType(breakdowntypeid)){
				addActionMessage("Break down Type  Id "+breakdowntypeid+" Updated successfully");
			}else{
				addActionMessage("Database error retry again.");
			}*/
			if(edit.equalsIgnoreCase("Y")){
			int i=breakdowntype.updateBreakDownType(breakdowntypeid,breakdowntypedeails);
			if(i>0){
				addActionMessage("BreakdownType  Id "+breakdowntypeid+" Updated successfully");
				flag="success";
			}else{
				if(i==-1){
					breakdowntypedeails.setBreakdown_name("");	
				addActionError("Breakdown Name Already Exist.");
				flag="error";
				}else{
					addActionError("Error in server.");
					flag="error";
				}
			}
			return flag;
			}else{
				return "error";
			}
	}
	
	@SkipValidation
	public String edit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		 int breakdowntypeid=Integer.parseInt(request.getParameter("breakdowntypeiddetails"));
			BreakDownTypeDetailsDao breakdowntype = new BreakDownTypeDetailsDao();
			breakdowntypedeails = breakdowntype.getmodifydetails(breakdowntypeid);
			return "success";
	}
	
	
	
	@Override
	public void validate() {
		CommonValidation common=new CommonValidation();
		if(breakdowntypedeails.getBreakdown_name().equalsIgnoreCase("")){
			addFieldError("breakdown_name","Breakdown Type Should Not Blank");
		}
		if(!common.isSpecialChar(breakdowntypedeails.getBreakdown_name()))
		{
			addFieldError("breakdown_name","Special Character Not Allowed For Breakdown Name");
			
		}
		
		if(breakdowntypedeails.getBreakdown_system_type().equalsIgnoreCase(""))
		{
			addFieldError("breakdown_system_type","System Type Should Not Blank");
		}
		if(!common.isSpecialChar(breakdowntypedeails.getBreakdown_system_type()))
		{
			addFieldError("breakdown_system_type","Special Character Not Allowed For System Type");
			
		}
		if(breakdowntypedeails.getBreakdown_system_sub_type().equalsIgnoreCase("")){
			addFieldError("breakdown_system_sub_type","Sub System Type Should Not Blank");
		}
		
		if(!common.isSpecialChar(breakdowntypedeails.getBreakdown_system_sub_type()))
		{
			addFieldError("breakdown_system_sub_type","Special Character Not Allowed For Sub System Type");
			
		}
		
	}
}
