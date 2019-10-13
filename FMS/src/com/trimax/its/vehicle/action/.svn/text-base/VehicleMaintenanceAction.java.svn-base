package com.trimax.its.vehicle.action;
import java.io.PrintWriter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.VehicleMaintenanceDao;
import com.trimax.its.vehicle.model.VehicleMaintenance;

public class VehicleMaintenanceAction extends ActionSupport implements Preparable{
	VehicleMaintenance vehiclemaintenance;
	String insertstatus;
	String deletestatus;
	String updatestatus;
	Common common = new Common();
	public VehicleMaintenance getVehiclemaintenance() {
		return vehiclemaintenance;
	}
	public void setVehiclemaintenance(VehicleMaintenance vehiclemaintenance) {
		this.vehiclemaintenance = vehiclemaintenance;
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
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	private Map<Integer, String> Vehiclelist;
	
	public Map<Integer, String> getVehiclelist() {
		return Vehiclelist;
	}

	public void setVehiclelist(Map<Integer, String> vehiclelist) {
		Vehiclelist = vehiclelist;
	}
	private String MaintenanceDate;
	
	public String getMaintenanceDate() {
		return MaintenanceDate;
	}
	public void setMaintenanceDate(String maintenanceDate) {
		MaintenanceDate = maintenanceDate;
	}
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SkipValidation
	public String viewVehiclemaintenance() {
		return "success";
	}
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			VehicleMaintenanceDao maintenancedao = new VehicleMaintenanceDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");


			String[] cols = { "", "vehicle_maintaince_id", "maintainance_date","maintenance_status","veh.vehicleId",
					"veh.vehicleRegistrationNumber","" };
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
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println(ex);
		} finally {

		}
	}
		@SkipValidation
		public String createVehicleMaintenance() {
			VehicleMaintenanceDao maintenancedao = new VehicleMaintenanceDao();
			Vehiclelist=maintenancedao.getVehicleList();
		//	System.out.println("Vehiclelist----"+Vehiclelist);
			//this.setVehiclelist(Vehiclelist);
			return "success";
		}

		public String createVehicleMaintenanceAction()
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "vehiclemaintenancelist.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(create.equalsIgnoreCase("Y")){
			VehicleMaintenanceDao maintenancedao = new VehicleMaintenanceDao();
			Vehiclelist=maintenancedao.getVehicleList();
			int id=0;

			if(!maintenancedao.getDuplicate(vehiclemaintenance.getVehicle().getVehicleId(),MaintenanceDate))
			{
			try{
			//	System.out.println("............."+vehicledefect.getVehicle());
				vehiclemaintenance.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				vehiclemaintenance.setMaintainance_date(common.getDateFromDatePicker(MaintenanceDate));
				id=maintenancedao.saveVehicleMaintenance(vehiclemaintenance);
				addActionMessage("Vehicle Maintenance Id "+id+ " Created Successfully");
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
				addActionMessage("Vehicle Maintenance already Exist!!");
				Vehiclelist=maintenancedao.getVehicleList();
				return "input";
			}
			return "success";
			}else{
				return "input";
			}
		}
		
		@Override
		public void validate() {
			// TODO Auto-generated method stub
			CommonValidation common=new CommonValidation();
			VehicleMaintenanceDao vehicledao = new VehicleMaintenanceDao();
			
			
			if(MaintenanceDate.equals(""))
			{
				addFieldError("mdate", "Maintenance Date is Required");
				Vehiclelist=vehicledao.getVehicleList();
				//vehicledefect.setDefect_type(vehicledefect.getDefect_type());
				
			}
			
			if(vehiclemaintenance.getVehicle().getVehicleId() == 0){
				addFieldError("vehicleId","Please select Vehicle");
				Vehiclelist=vehicledao.getVehicleList();
				this.vehiclemaintenance.setVehicle(vehiclemaintenance.getVehicle());
			}
			
		}
		
		@SkipValidation
		public String deleteVehicleMaintenance()
		{
			VehicleMaintenanceDao dao=new VehicleMaintenanceDao();
			VehicleMaintenance maintenance=new VehicleMaintenance();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "vehiclemaintenancelist.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(delete.equalsIgnoreCase("Y")){
			maintenance.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			try{
				dao.deletevehiclemaintenance(maintenance,Integer.parseInt(request.getParameter("delmaintenanceid")));
				//System.out.println("Defect Id-------"+Integer.parseInt(request.getParameter("deldefectid")));
				setDeletestatus("success");
			}catch(Exception ex)
			{
				return "input";
			}finally{
				
				addActionMessage("Vehicle Maintenance Id "+request.getParameter("delmaintenanceid")+ " Deleted SuccessFully");
			}
			return "success";
			}else{
				addActionMessage("Access Denied - User Does Not Have Access Privileges");
				return "input";
			}
		}
		
		@SkipValidation
		public String getEditDetails() {
			VehicleMaintenanceDao dao = new VehicleMaintenanceDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			vehiclemaintenance = dao.getEditedVehicleMaintenance(Integer.parseInt(request
					.getParameter("editmaintenanceid")));
					Vehiclelist=dao.getVehicleList();		
					vehiclemaintenance.setMaintenancedate(common.getDateToDatePicker(vehiclemaintenance.getMaintainance_date()));
			//System.out.println(vehicledefect.getDefectdate()+"          "+vehicledefect.getDate());
			return "success";
			
		}
		
		public String updateMaintenanceDetails(){
			String flag="";
			HttpServletRequest request = ServletActionContext.getRequest();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "vehiclemaintenancelist.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(edit.equalsIgnoreCase("Y")){
			VehicleMaintenanceDao maintenancedao = new VehicleMaintenanceDao();
			int useriddetails=vehiclemaintenance.getVehicle_maintaince_id();
			boolean flagdetails=true;
			boolean groupflag=false;
			//vehiclemaintenance.setMaintainance_date(common.getDateFromDatePicker(MaintenanceDate));
			vehiclemaintenance.setMaintenancedate(common.getDateToDatePicker(vehiclemaintenance.getMaintainance_date()));
			Vehiclelist=maintenancedao.getVehicleList();
				//System.out.println("Designation-------"+userdetails.getDesignationtypeid().getDesignation_type_id());
			flagdetails=maintenancedao.getUpdateDuplicate(vehiclemaintenance.getVehicle().getVehicleId(),MaintenanceDate,vehiclemaintenance.getVehicle_maintaince_id());
			
			if(flagdetails==false)
			{		
			 int i=maintenancedao.updateVehicleMaintenance(useriddetails, vehiclemaintenance);	
			addActionMessage("Vehicle Maintenance Id "+useriddetails+ " Updated SuccessFully");		
			setUpdatestatus("success");
			flag="success";
			}
			else{
				setInsertstatus("duplicate");
				addActionError("Vehicle Maintenance already Exist!!");
				return "input";
			}
				
			return flag;
		}
			else{
				return "input";
			}
		}
		

	}

