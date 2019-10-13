package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.transport.dao.ScheduleTypeDao;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class ScheduleTypeAction extends ActionSupport implements Preparable {

	ScheduleType scheduletype;
	String insertmsg;
	String updatemsg;
	String deletestatus;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	
	
	public ScheduleType getScheduletype() {
		return scheduletype;
	}
	public void setScheduletype(ScheduleType scheduletype) {
		this.scheduletype = scheduletype;
	}
	
	public String getInsertmsg() {
		return insertmsg;
	}
	public void setInsertmsg(String insertmsg) {
		this.insertmsg = insertmsg;
	}
	
	public String getUpdatemsg() {
		return updatemsg;
	}
	public void setUpdatemsg(String updatemsg) {
		this.updatemsg = updatemsg;
	}
	
	public String getDeletestatus() {
		return deletestatus;
	}
	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
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
			ScheduleTypeDao schdao = new ScheduleTypeDao();

			/*int cnt = schdao.getTotalRecords();
			System.out.println("Count------>" + cnt);*/
			String[] cols = { "", "schedule_type_id","scheduleName", "status", "notes" };
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
			total = schdao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = schdao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

		
	}
	@SkipValidation
	public String showschedule()
	{
		return "success";
	}
	@SkipValidation
	public String showCreateschedule()
	{
		return "success";
	}
	@SkipValidation
	public String editscheduleType()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		ScheduleTypeDao schdao = new ScheduleTypeDao();
		scheduletype = schdao.getEditedScheduleType(Integer.parseInt(request
				.getParameter("scheduleid")));

		return "success";
	}
	public String createScheduletype()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "sheduleTypeAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		ScheduleTypeDao schdao = new ScheduleTypeDao();
		int id=0;
		if(!schdao.checkScheduletype(scheduletype.getScheduleName().trim())){
		scheduletype.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		scheduletype.setCreatedDate(new java.util.Date());
		try{
		id=schdao.insertScheduleType(scheduletype);
		}catch(Exception ex)
		{
			setInsertmsg("Couldnot Insert Data");
			
		}
		finally{
			setInsertmsg("Data Inserted SuccessFully");
			addActionMessage("Schedule Type Id "+ id +" Inserted Successfully");
		}
		}
		else{
			setInsertmsg("duplicate");
			return "input";
		}
		return "success";
		}
		else{
			return "input";
		}
	}
	
	public String EditScheduletype()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ScheduleTypeDao schdao = new ScheduleTypeDao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "sheduleTypeAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(scheduletype.getSchedule_type_id(),"schedule_type");
		if(status.equals("")||(!status.equals("") &&  scheduletype.getStatus().equals("ACTIVE"))){	
		
		if(schdao.checkScheduletypeForUpdate(scheduletype.getScheduleName().trim(),scheduletype.getSchedule_type_id())){
		scheduletype.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
		String res = schdao.updateScheduleType(scheduletype,scheduletype.getSchedule_type_id());
		}catch(Exception ex)
		{
			setUpdatemsg("Could not Update Data");
		}
		finally{
			setUpdatemsg("Data Updated successfully");
			addActionMessage("Schedule Type Id "+ scheduletype.getSchedule_type_id() +" Updated successfully");
		}
		}
		else if(!schdao.checkScheduletype(scheduletype.getScheduleName().trim()))
		{
			try{
				String res = schdao.updateScheduleType(scheduletype,scheduletype.getSchedule_type_id());
				}catch(Exception ex)
				{
					setUpdatemsg("Could not Insert Data");
				}
				finally{
					setUpdatemsg("Data Updated successfully");
					addActionMessage("Schedule Type Id "+ scheduletype.getSchedule_type_id() +" Updated successfully");
				}
		}
		else{
			setUpdatemsg("duplicate");
			return "input";
		}
		}else
		{
			
			if(scheduletype.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
		}
		return "success";
		}
		else{
			return "input";
		}
	}
	@SkipValidation
	public String deletescheduleType()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ScheduleTypeDao schdao = new ScheduleTypeDao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "sheduleTypeAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		//scheduletype.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(Integer.parseInt(request.getParameter("scheduleid")),"schedule_type");
	       if(status.equals("")){
			
			
			String res = schdao.deleteScheduleType(scheduletype,Integer.parseInt(request.getParameter("scheduleid")),request);
			addActionMessage("Schedule Type Id "+ request.getParameter("scheduleid") + " deleted Successfully");

	       }
	       else{
               addActionError(status);
               //returnResult = "success";
           }
			}catch(Exception ex)
			{
				setDeletestatus("fail");
			}
			finally{
				setDeletestatus("success");
			}
			return "success";
		}else{
			setDeletestatus("success");
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return"success";
		}
		
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
	if(scheduletype.getScheduleName().trim().equals(""))
	{
		addFieldError("schedulename", "Schedule Name Required");
		//addActionError("Schedule Name Required");
	}
	if(!common.isSpecialChar(scheduletype.getScheduleName())){
		addFieldError("schedulename", "Special  Characters For ScheduleType Name Not Allowed");
		//addActionError("");
	}
	if(scheduletype.getStatus().equals(""))
	{
		addActionError("Status Required");
	}
	/*if(scheduletype.getNotes().trim().equals(""))
	{
		addActionError("Note is Required");
	}*/
		
	}
	
}
