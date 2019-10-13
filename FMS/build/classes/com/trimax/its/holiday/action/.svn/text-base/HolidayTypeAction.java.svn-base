package com.trimax.its.holiday.action;

import java.io.PrintWriter;

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

import com.trimax.its.holiday.dao.HolidayTypeDao;

import com.trimax.its.holiday.model.HolidayType;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;




public class HolidayTypeAction extends ActionSupport implements Preparable{
	HolidayType holidaytype;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	public HolidayType getHolidaytype() {
		return holidaytype;
	}

	public void setHolidaytype(HolidayType holidaytype) {
		this.holidaytype = holidaytype;
	}

	String insertstatus;
	String deletestatus;
	String updatestatus;
	private String defectDate;
	public String getDefectDate() {
		return defectDate;
	}

	public void setDefectDate(String defectDate) {
		this.defectDate = defectDate;
	}

	Common common = new Common();
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
	
	public String getUpdatestatus() {
		return updatestatus;
	}

	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SkipValidation
	public String viewHolidayType() {
		return "success";
	}
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			HolidayTypeDao holidaytypedao = new HolidayTypeDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

			String[] cols = { "", "holiday_id", "holiday_type","" };
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
			total = holidaytypedao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = holidaytypedao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
			//System.out.println(ex);
		} finally {

		}

	}
	
	@SkipValidation
	public String createholidaytype() {

		return "success";
	}
	
	public String createHolidayTypeAction()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "HolidayTypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		HolidayTypeDao holidaytypedao = new HolidayTypeDao();

		int id=0;
		
		if(!holidaytypedao.getDuplicate(holidaytype.getHoliday_type()))
		{
		try{
		//	System.out.println("............."+vehicledefect.getVehicle());
			holidaytype.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			
			id=holidaytypedao.saveHolidayType(holidaytype);
			addActionMessage("Holiday Type Id "+id+ " Created Successfully");
			setInsertstatus("success");
		}catch(Exception e)
		{
			addActionMessage("DataBase Error!!");
			setInsertstatus("fail");
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		finally{
			
		}
		}else{
			setInsertstatus("duplicate");
			addActionMessage("Holiday Type already Exist!!");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
	}
	
	@SkipValidation
	public String getEditDetails() {
		HolidayTypeDao dao = new HolidayTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();
//		System.out.println("Holiday id"+Integer.parseInt(request
//				.getParameter("editholidaytypeid")));
		holidaytype = dao.getEditedHolidayType(Integer.parseInt(request
				.getParameter("editholidaytypeid")));
		
		return "success";
		
	}
	
	public String updateHolidayTypeDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "HolidayTypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		HolidayTypeDao holidaytypedao = new HolidayTypeDao();
		if(edit.equalsIgnoreCase("Y")){
		int useriddetails=holidaytype.getHoliday_id();
		boolean flagdetails=true;
		boolean groupflag=false;
		flagdetails=holidaytypedao.getUpdateDuplicate(holidaytype.getHoliday_type(),holidaytype.getHoliday_id());
		if(flagdetails==true)
		{		
		 int i=holidaytypedao.updateHolidayType(useriddetails, holidaytype);	
		addActionMessage("Holiday Type Id "+useriddetails+ " Updated SuccessFully");	
		setUpdatestatus("success");
		flag="success";
		}
		else{
			setInsertstatus("duplicate");
			addActionError("Holiday Type already Exist!!");
			return "input";
		}
			
		//System.out.println("flag-----////////---"+flag);
		return flag;
		}else{
			return "input";
		}
	}
	
	@SkipValidation
	public String deleteHolidayType()
	{
		HolidayTypeDao dao=new HolidayTypeDao();
		HolidayType htype=new HolidayType();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "HolidayTypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		htype.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			dao.deleteholidayType(htype,Integer.parseInt(request.getParameter("delholidaytypeid")));
			setDeletestatus("success");
		}catch(Exception e)
		{
			setDeletestatus("fail");
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			return "input";
		}finally{
			
			addActionMessage("Holiday Type Id "+request.getParameter("delholidaytypeid")+ " Deleted SuccessFully");
		}
		return "success";
		}else{
			return "success";
		}
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		if(holidaytype.getHoliday_type().trim().equals("") || holidaytype.getHoliday_type().equals(" "))
		{
			addFieldError("htypename", "Holiday Type Name is Required");			
			holidaytype.setHoliday_type(holidaytype.getHoliday_type());
			
		}
		if(!common.isSpecialChar(holidaytype.getHoliday_type()))
		{
			addFieldError("htypename", "Special Character are not allowed for Holiday Type Name");			
			this.holidaytype.setHoliday_type(holidaytype.getHoliday_type());
		}
		
		
	}
	
}
