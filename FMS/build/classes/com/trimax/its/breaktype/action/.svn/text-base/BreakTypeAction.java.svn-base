package com.trimax.its.breaktype.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.breaktype.dao.BreakTypeDao;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;

import com.trimax.its.transport.model.BreakType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class BreakTypeAction extends ActionSupport implements Preparable {

	private BreakType breaktype;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	String insertstatus;
	String updatestatus;
	String deletestatus;
	
	public String getInsertstatus() {
		return insertstatus;
	}
	public String getUpdatestatus() {
		return updatestatus;
	}
	public String getDeletestatus() {
		return deletestatus;
	}
	public void setInsertstatus(String insertstatus) {
		this.insertstatus = insertstatus;
	}
	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}
	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}
	public BreakType getBreaktype() {
		return breaktype;
	}
	public void setBreaktype(BreakType breaktype) {
		this.breaktype = breaktype;
	}
	@SkipValidation
	public String execute() throws IOException {
		return null;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			BreakTypeDao breaktypedao = new BreakTypeDao();

			
			String[] cols = { "", "id", "breakTypeName","duration","steeringHours","spreadHours","ot_hours","rest","status"};
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
			total = breaktypedao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = breaktypedao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

	}
	@SkipValidation
	public String viewBreaktype()
	{
		return "success";
	}
	@SkipValidation
	public String viewcreatePage()
	{
		breaktype=new BreakType();
		breaktype.setDuration("0:00");
		return "success";
		
	}
	@SkipValidation
	public String vieweditPage()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		BreakTypeDao breaktypedao = new BreakTypeDao();
		breaktype=breaktypedao.getEditedBreaktype(Integer.parseInt(request
				.getParameter("breakid")));
		
		return "success";
	}
	
	public String createBreakTypeAction()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "breaktypeview.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		BreakTypeDao breaktypedao = new BreakTypeDao();
		int id=0;
		if(create.equalsIgnoreCase("Y")){
		if(!breaktypedao.checkBreaktype(breaktype.getBreakTypeName())){
		breaktype.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		breaktype.setCreatedDate(new java.util.Date());
		String duration[] =breaktype.getDuration().split(":");
		String durationTime="";
		if(Integer.parseInt(duration[0])<10)
		{
			durationTime="0"+duration[0]+":"+duration[1];
		}else{
			durationTime=breaktype.getDuration();
		}
		try{
			breaktype.setDuration(durationTime);
		id=breaktypedao.saveBreaktype(breaktype);
		}catch(Exception ex){
			setInsertstatus("fail");
			return "input"; 
		}
		finally{
			setInsertstatus("success");
			addActionMessage("Breaktype Id "+ id +" Inserted SuccessFully");
			
		}
		}else{
			setInsertstatus("duplicate");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
		
	}
	public String addeditedbreaktype()
	{
		
	HttpServletRequest request = ServletActionContext.getRequest();
	AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "breaktypeview.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
	BreakTypeDao breaktypedao = new BreakTypeDao();
	if(edit.equalsIgnoreCase("Y")){
	DependencyChecker dc=new DependencyChecker();
	String status=dc.getStatus(breaktype.getId(),"break_type");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  breaktype.getStatus().equals("ACTIVE"))){	
	String durationTime="";
	String duration[] =breaktype.getDuration().split(":");
	if(Integer.parseInt(duration[0])<10)
	{
		durationTime="0"+duration[0]+":"+duration[1];
	}else{
		durationTime=breaktype.getDuration();
	}
	if(breaktypedao.checkBreaktypeForUpdate(breaktype.getBreakTypeName(), breaktype.getId())){
	breaktype.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
	
	
	try{
		breaktype.setDuration(durationTime);
	breaktypedao.updateBreaktype(breaktype, breaktype.getId());
	}catch(Exception ex){
		setUpdatestatus("fail");
		return "input";
	}
	finally{
		setUpdatestatus("success");
		addActionMessage("Break Type Id "+ breaktype.getId()+" Updated Successfully");
	}
	}else if(!breaktypedao.checkBreaktype(breaktype.getBreakTypeName())){
		try{
			breaktype.setDuration(durationTime);
			breaktypedao.updateBreaktype(breaktype, breaktype.getId());
			}catch(Exception ex){
				setUpdatestatus("fail");
				return "input";
			}
			finally{
				setUpdatestatus("success");
				addActionMessage("Break Type Id "+ breaktype.getId()+" Updated Successfully");
			}
	}else{
		setUpdatestatus("duplicate");
		return "input";
	}
	
	}else
	{
		
		if(breaktype.getStatus().equals("INACTIVE"))
		{
			//setUpdatestaus("success");
		addActionError(status);
		return "input";
		}	
		
	}
		return "success";}
	else{
		return "input";
	}
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
	if(breaktype.getBreakTypeName().trim().equals(""))
	{
		addFieldError("breaktypename", "Break Type Name is Required");
		//addActionError("");
		//breaktype.setDuration("");
	}
	if(!common.isSpecialChar(breaktype.getBreakTypeName().trim())){
		addFieldError("breaktypename", "Special Characters are Not Allowed in  BreakTypename");
		//addActionError("S");
	}
	if(breaktype.getDuration().equals("") || breaktype.getDuration().equals("0:00") )
	{
		breaktype.setDuration("0:00");
		addFieldError("duration", "Duration is Required");
		//addActionError("S");
		//addActionError("Duration is Required");
	}
	if(breaktype.getStatus().equals(""))
	{
		addActionError("Status is Required");
	}
	}
	@SkipValidation
	public String deletebreaktype()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		BreakTypeDao breaktypedao = new BreakTypeDao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "breaktypeview.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		//breaktype.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			
			DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(Integer.parseInt(request.getParameter("breakid")),"break_type");
	        System.out.println("status---"+status);
	        
	       if(status.equals("")){
			breaktypedao.deleteBreaktype(breaktype, Integer.parseInt(request.getParameter("breakid")));
			addActionMessage("Break Type Id "+ request.getParameter("breakid") + " deleted SuccessFully");
	       }else{
	            addActionError(status);
	            //returnResult = "success";
	        }
		}catch(Exception ex){
			setDeletestatus("fail");
		}finally{
			setDeletestatus("success");
			//addActionMessage("Break Type Id "+ request.getParameter("breakid") + " deleted SuccessFully");
		}
				
		return "success";
	}else{
		setDeletestatus("success");
		addActionMessage("Access Denied - User Does Not Have Access Privileges");

		return "success";
		
	}
}
}
