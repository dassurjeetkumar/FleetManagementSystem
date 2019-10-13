package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.transport.dao.ShiftTypeDao;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.GroupMasterDao;
import com.trimax.its.utility.dao.PageMasterDao;
import com.trimax.its.utility.model.AccessRights;

public class ShiftTypeAction extends ActionSupport {
	private ShiftType shiftType;
	private String requestType;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	 private boolean isEmpty(String str) {
		 
	        return str == null ? true:(str.equals("") ? true:false);
	    }
	private String msg;

	public ShiftType getShiftType() {
		return shiftType;
	}

	public void setShiftType(ShiftType shiftType) {
		this.shiftType = shiftType;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	
	private int AMOUNT;

	public String execute() {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			ShiftTypeDao shifttypedao = new ShiftTypeDao();
			int cnt = shifttypedao.getTotalRecords(viewdeletedrecord);
			String[] cols = { "", "id", "shiftTypeName", "shift_Type_code",
					"status" };
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
			total = shifttypedao.getTotalRecords(viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = shifttypedao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}
		return null;

	}

	public String editShiftTypeDetails() {
		String shiftTypename = shiftType.getShiftTypeName();
		int id = shiftType.getId();
		String status = shiftType.getStatus();
        String shiftCode=shiftType.getShift_Type_code();
		if (("".equals(shiftTypename)) || (shiftTypename == null)
				|| (shiftTypename.length() == 0)) {

			addFieldError("shiftTYpeName", "DutyType Name is Manadatory");
			msg = "Page Details not Updated Try Again";
			return "input";

		}
		CommonValidation cm=new CommonValidation();
		if(!cm.isSpecialChar(shiftType.getShiftTypeName()))
		{
			addFieldError("shiftTYpeName", "DutyType Name is Should be a alphanumeric");
			return "input";
		}
		if ( ("".equals(shiftCode)) || (shiftCode == null)
				|| (shiftCode.length() == 0)) {

			addFieldError("id", "Duty Type Code is Manadatory");
			return "input";

		}
		
		if(!cm.isSpecialChar(shiftType.getShift_Type_code()))
		{
			addFieldError("shiftTYpeName", "DutyType Code is Should be a alphanumeric");
			return "input";
		}
		/*if((shiftType.getShift_Type_code()==-1))
		{
			addFieldError("id", "DutyType should be integer");
			return "input";
		
		}*/
		/*
		 * if(("".equals(status))||(status==null)||(status.length()==0)){
		 * System.out.println("hi if of old");
		 * System.out.println("hi if of old of shiftType status"
		 * +shiftType.getStatus());
		 * 
		 * addFieldError("status","status  is Manadatory");
		 * msg="Page Details not Updated Try Again"; return "input";
		 * 
		 * 
		 * }
		 */
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showShiftType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		int shiftid = shiftType.getId();
		String shiftTypeName = shiftType.getShiftTypeName();
		String shiftTypeCode = shiftType.getShift_Type_code();
		if(edit.equalsIgnoreCase("Y")){
		ShiftTypeDao shiftypedao = new ShiftTypeDao();
		int shifttypenamecount = 0;
		int shifttypecodecount = 0;
		if (shiftTypeName != null) {
			shifttypenamecount = shiftypedao.checkForDuplicateShiftTypeName(
					shiftTypename, shiftid);
		}
		/*if (shiftTypeCode != 0) {
			shifttypecodecount = shiftypedao.checkForDuplicateShiftTypeCode(
					shiftTypeCode, shiftid);
			System.out.println("no of records with the given value"
					+ shifttypecodecount);
		}
		
			int shifttypestatuscount=0; 
			shifttypestatuscount= shiftypedao.checkForDuplicateShiftTypeStatus(
					status, shiftid);
			System.out.println("no of records with the given value"
					+ shifttypecodecount);*/
	
		if (shifttypenamecount == 0) {
			shiftypedao.saveEDitedShiftTypeDetails(requestType, shiftid,
					shiftType);

			msg = "Duty Type Id "+id+" Updated Succesfully";
		} else {
			msg = "Duty type Id already exists";
			return "input";
		}

		return "success";
		}
		else{
			 return "input" ;
		}
	}

	public String showShift() {
		return "success";
	}

	public String saveShiftTypeDetails() {
		String shiftTypename = shiftType.getShiftTypeName();

		/*
		 * shiftType.setScheduletype(new ScheduleType());
		 * shiftType.getScheduletype().setSchedule_type_id(6);
		 */

		int id = shiftType.getId();
		String status = shiftType.getStatus();
		String shiftCode=shiftType.getShift_Type_code();
		if (("".equals(shiftTypename)) || (shiftTypename == null)
				|| (shiftTypename.length() == 0)) {

			addFieldError("shiftTYpeName", "DutyType Name is Manadatory");
			//msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (("".equals(shiftCode)) || (shiftCode == null)
				|| (shiftCode.length() == 0)) {
			
			

			addFieldError("ShiftType_code", "Duty Type Code is Manadatory");
			return "input";

		}
		CommonValidation cm=new CommonValidation();
		if(!cm.isSpecialChar(shiftType.getShiftTypeName()))
		{
			addFieldError("shiftTYpeName", "DutyType Name is Should be a alphanumeric");
			return "input";
		}
		if(!cm.isSpecialChar(shiftType.getShift_Type_code()))
		{
			addFieldError("ShiftType_code", "DutyType Code is Should be a alphanumeric");
			return "input";
		}

		if (("".equals(status)) || (status == null) || (status.length() == 0)) {

			addFieldError("status", "status  is Manadatory");
		//	msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
				
	/*	if((shiftType.getShift_Type_code()==-1))
		{
			addFieldError("ShiftType_code", "DutyType should be integer");
			return "input";
		
		}*/
		String shiftTypeName = shiftType.getShiftTypeName();
		HttpServletRequest request = ServletActionContext.getRequest();
		String shiftTypeCode = shiftType.getShift_Type_code();
/*
		shiftType.setCreatedBy((Integer) request.getSession().getAttribute(
				"userid"));*/
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showShiftType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		System.out.println(shiftTypename);
		if(create.equalsIgnoreCase("Y")){
		ShiftTypeDao shiftypedao = new ShiftTypeDao();
		int shiftnamecount = 0;
		if ((shiftTypename != null) && (shiftTypeCode != null)) {
			shiftnamecount = shiftypedao.checkForDuplicateShiftType(
					shiftTypename, shiftTypeCode);
			
		}
		int insertid=shiftypedao.getMaxShiftID()+1;
		if (shiftnamecount == 0) {
			int shiftTypeId = shiftypedao.insertShiftTypeDetails(shiftType);
			msg = "Duty Type Id "+insertid+" Inserted Succesfully";
		} else {
			msg = "Duty Type Id already Exists";
			return "input";
		}
		return "success";
		} else
		{
			return "input";
		}
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public String createShiftType() {

		return "success";
	}

	public String checkForShiftId() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		int shiftid = Integer.parseInt(request.getParameter("shiftid"));
		ShiftTypeDao shiftypedao = new ShiftTypeDao();
		int count = shiftypedao.checkForShiftDelete(shiftid);
		PrintWriter out = null;
		HttpServletResponse response = ServletActionContext.getResponse();

		out = response.getWriter();
		String result = null;
		if (count != 0) {
			result = "Duty type Id in use can not Delete";
			out.print(count);
			msg = "Duty Type Id in Use Not Deleted";
		} else {
			result = "Press ok";
			out.print(count);
		}

		return null;
	}

	public String deleteShiftType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showShiftType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		ShiftTypeDao shiftypedao = new ShiftTypeDao();
		int shiftid = Integer.parseInt(request.getParameter("shiftid"));
		int count = shiftypedao.checkForShiftDelete(shiftid);
		if (count == 0) {
			String res = shiftypedao.deleteShiftType(Integer.parseInt(request
					.getParameter("shiftid")));
			msg = "Duty Type Id "+shiftid+" Deleted successfully";
		} else {

			msg = "Duty Type Id in Use Not Deleted";
			return "success";

		}
		return "success";
		}else{
			msg="Access Denied - User Does Not Have Access Privileges";
			return "success";
		}
	}

	public String editShiftType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ShiftTypeDao shiftypedao = new ShiftTypeDao();
		shiftType = shiftypedao.gettShiftTypeDetails(Integer.parseInt(request
				.getParameter("id")));

		return "success";
	}

}
