package com.trimax.its.vehicle.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.AccidentTypeDao;
import com.trimax.its.vehicle.model.AccidentType;

public class AccidentTypeAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */

	AccidentType accidenttype;
	String insertstatus;
	String deletestatus;
	public String getInsertstatus() {
		return insertstatus;
	}

	public String getDeletestatus() {
		return deletestatus;
	}

	public void setInsertstatus(String insertstatus) {
		this.insertstatus = insertstatus;
	}

	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}

	public AccidentType getAccidenttype() {
		return accidenttype;
	}

	public void setAccidenttype(AccidentType accidenttype) {
		this.accidenttype = accidenttype;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccidentTypeDao accidentdao = new AccidentTypeDao();

			/*int cnt = accidentdao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);*/
			/*System.out.println("Count------>" + cnt);*/
			String[] cols = { "", "accident_type_id", "accident_type_name",
					"", "status" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");


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
			total = accidentdao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = accidentdao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

	}
	/*public void validate() {
		
	}*/
@SkipValidation
	public String viewAccidentType() {
		return "success";
	}
@SkipValidation
	public String createAccidenttypeview() {
		return "success";
	}
	
	public String createAccidenttypeAction()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccidentTypeDao accidentdao = new AccidentTypeDao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "accidenttypeView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String read=accessrights.getREAD();
		if(create.equalsIgnoreCase("Y")){
		int id=0;
		if(accidenttype.getAccident_type_name().trim().equals(""))
		{
			addFieldError("accname", "Accident Type Name  Required");
			return "input";
		}
		CommonValidation commvalidate = new CommonValidation();
		
		if(!commvalidate.isSpecialChar(accidenttype.getAccident_type_name().trim()))
		{
			addFieldError("accname", "Special Character For Accident Type Name is Not Allowed");
			return "input";
		}
		if(!accidentdao.getDuplicate(accidenttype.getAccident_type_name()))
		{
		try{
			accidenttype.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			
			id=accidentdao.saveAccidentType(accidenttype);
			addActionMessage("Accident Type Id "+id+ " Inserted SuccessFully");
			setInsertstatus("success");
		}catch(Exception ex)
		{
			addActionMessage("DataBase Error!!");
			setInsertstatus("fail");
		}
		finally{
			
		}
		}else{
			setInsertstatus("duplicate");
			addActionMessage("Accident Type already Exist!!");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
	}
	@SkipValidation
	public String deleteAccidentType()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccidentTypeDao accidentdao = new AccidentTypeDao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "accidenttypeView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String read=accessrights.getREAD();
		if(delete.equalsIgnoreCase("Y")){
//		accidenttype.setUpdated_by(Integer.parseInt(request.getSession()
//				.getAttribute("userid").toString()));
		try{
		String res = accidentdao.deleteAccidentType(accidenttype,
				Integer.parseInt(request.getParameter("accid")),request);
		
		}catch(Exception ex){
			setDeletestatus("fail");
		}finally{
			addActionMessage("Accident Type Id "+Integer.parseInt(request.getParameter("accid"))+ " Deleted SuccessFully");
			setDeletestatus("success");
		}
		return "success";
	}else{
		addActionMessage("Access Denied - User Does Not Have Access Privileges");

		return "success";
	}
	
}
}