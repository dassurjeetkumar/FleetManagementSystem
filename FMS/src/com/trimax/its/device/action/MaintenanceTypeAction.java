package com.trimax.its.device.action;

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
import com.trimax.its.device.dao.MaintenanceTypeDao;
import com.trimax.its.device.model.MaintenanceType;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.VehicleDefectsDao;



public class MaintenanceTypeAction extends ActionSupport implements Preparable {
	MaintenanceType maintenancetype;
	String insertstatus;
	String deletestatus;
	String updatestatus;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public MaintenanceType getMaintenancetype() {
		return maintenancetype;
	}

	public void setMaintenancetype(MaintenanceType maintenancetype) {
		this.maintenancetype = maintenancetype;
	}

	public String getInsertstatus() {
		return insertstatus;
	}

	public void setInsertstatus(String insertstatus) {
		this.insertstatus = insertstatus;
	}

	public String getDeletestatus() {
		return deletestatus;
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
	public String viewMaintenanceType() {
		return "success";
	}
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			MaintenanceTypeDao maintenancedao = new MaintenanceTypeDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] cols = { "", "maintenance_type_id", "maintenance_type","status","" };
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
			total = maintenancedao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = maintenancedao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println(ex);
		} finally {

		}

	}
	
	@SkipValidation
	public String createMaintenanceType() {

		return "success";
	}
	
	public String createMaintenanceTypeAction()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		MaintenanceTypeDao dao = new MaintenanceTypeDao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "maintenancetypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String read=accessrights.getREAD();
		int id=0;
		if(create.equalsIgnoreCase("Y")){
		if(!dao.getDuplicate(maintenancetype.getMaintenance_type()))
		{
		try{
		//	System.out.println("............."+vehicledefect.getVehicle());
			maintenancetype.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			
			id=dao.saveMaintenanceType(maintenancetype);
			addActionMessage("Maintenance Type Id "+id+ " Created Successfully");
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
			addActionMessage("Maintenance Type already Exist!!");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
	}
	@SkipValidation
	public String getEditDetails() {
		MaintenanceTypeDao dao = new MaintenanceTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();
//		System.out.println("Holiday id"+Integer.parseInt(request
//				.getParameter("editholidaytypeid")));
		maintenancetype = dao.getEditedMaintenanceType(Integer.parseInt(request
				.getParameter("edittypeid")));
		
		return "success";
		
	}
	
	public String updateMaintenanceTypeDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "maintenancetypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String read=accessrights.getREAD();
		if(edit.equalsIgnoreCase("Y")){
		MaintenanceTypeDao dao = new MaintenanceTypeDao();
		int useriddetails=maintenancetype.getMaintenance_type_id();
		//System.out.println("---------------------"+holidaytype.getHoliday_type());
		boolean flagdetails=true;
		boolean groupflag=false;
		flagdetails=dao.getUpdateDuplicate(maintenancetype.getMaintenance_type(),maintenancetype.getMaintenance_type_id());
		//System.out.println("flagdetails---------"+flagdetails);
		if(flagdetails==true)
		{		
		 int i=dao.updateMaintenanceType(useriddetails, maintenancetype);	
		addActionMessage("Maintenance Type Id "+useriddetails+ " Updated SuccessFully");	
		setUpdatestatus("success");
		flag="success";
		}
		else{
			setInsertstatus("duplicate");
			addActionError("Maintenance Type already Exist!!");
			return "input";
		}
			
		//System.out.println("flag-----////////---"+flag);
		return flag;
		}else{
			return "input";
		}
	}
	
	@SkipValidation
	public String deleteMaintenanceType()
	{
		MaintenanceTypeDao dao=new MaintenanceTypeDao();
		MaintenanceType htype=new MaintenanceType();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "maintenancetypelist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String read=accessrights.getREAD();
		if(delete.equalsIgnoreCase("Y")){
		htype.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			dao.deleteMaintenanceType(htype,Integer.parseInt(request.getParameter("deltypeid")));
			//System.out.println("Defect Id-------"+Integer.parseInt(request.getParameter("delholidaytypeid")));
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
			
			addActionMessage("Maintenance Type Id "+request.getParameter("deltypeid")+ " Deleted SuccessFully");
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
		if(maintenancetype.getMaintenance_type().trim().equals("") || maintenancetype.getMaintenance_type().equals(" "))
		{
			addFieldError("maintenancetype", "Maintenance Type Name is Required");
			
			maintenancetype.setMaintenance_type(maintenancetype.getMaintenance_type());
			
		}
		if(!common.isSpecialChar(maintenancetype.getMaintenance_type()))
		{
			addFieldError("maintenancetype", "Special Character are not allowed for Maintenance Type Name");
			
			this.maintenancetype.setMaintenance_type(maintenancetype.getMaintenance_type());
		}
		
		
	}
	
}
