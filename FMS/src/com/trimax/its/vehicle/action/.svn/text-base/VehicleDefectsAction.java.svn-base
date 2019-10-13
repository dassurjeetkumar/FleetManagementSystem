package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.cashremittancevoucher.dao.CashRemittanceVoucherDao;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.EncryptionDecryption;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.dao.DeviceTypeDao;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.toll.dao.TollfeeDao;
import com.trimax.its.toll.model.TollBusStop;
import com.trimax.its.transport.dao.WeeklyChartDao;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.usermanagement.dao.UserDetailDAO;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.UserGroupDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.utility.model.UserGroup;
import com.trimax.its.vehicle.dao.AccidentTypeDao;
import com.trimax.its.vehicle.dao.VehicleDefectsDao;
import com.trimax.its.vehicle.model.AccidentType;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleDefects;
import com.trimax.its.vehicle.model.VehicleNumber;


public class VehicleDefectsAction extends ActionSupport implements Preparable {
	VehicleDefects vehicledefect;
	String insertstatus;
	String deletestatus;
	String updatestatus;
	public String getUpdatestatus() {
		return updatestatus;
	}

	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

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

	public VehicleDefects getVehicledefect() {
		return vehicledefect;
	}

	public void setVehicledefect(VehicleDefects vehicledefect) {
		this.vehicledefect = vehicledefect;
	}

	private Map<Integer, String> Vehiclelist;
	
	public Map<Integer, String> getVehiclelist() {
		return Vehiclelist;
	}

	public void setVehiclelist(Map<Integer, String> vehiclelist) {
		Vehiclelist = vehiclelist;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SkipValidation
	public String viewVehicleDefect() {
		return "success";
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			VehicleDefectsDao defectdao = new VehicleDefectsDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

			String[] cols = { "", "defect_id", "defect_type","status",
					"date","" };
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
			total = defectdao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = defectdao.getData(total, request,
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
	public String createVehicleDefectsview() {
		VehicleDefectsDao vehicledefectdao = new VehicleDefectsDao();
		//Vehiclelist=vehicledefectdao.getVehicleList();
		//System.out.println("Vehiclelist----"+Vehiclelist);
		//this.setVehiclelist(Vehiclelist);
		return "success";
	}
	
	
	public String createVehicleDefectAction()
	{
		//VehicleDefectsDao vehicledefectdao = new VehicleDefectsDao();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "vehicledefectlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		VehicleDefectsDao vehicledefectdao = new VehicleDefectsDao();
//		Vehiclelist=vehicledefectdao.getVehicleList();
		Vehiclelist=vehicledefectdao.getVehicleList();
		if(create.equalsIgnoreCase("Y")){
//		System.out.println("lszdsdas..................."+vehicledefect.ge     tVehicle().getVehicleId());
		int id=0;
		Common common=new Common();
		
		String defectType=vehicledefect.getDefect_type();
		//int vehicleId=vehicledefect.getVehicle().getVehicleId();
		String vehicle_date=vehicledefectdao.getDateFromDb(vehicledefect.getDate());
		
		if(!vehicledefectdao.getDuplicate(defectType,vehicle_date)){
		try{
		//	System.out.println("............."+vehicledefect.getVehicle());
			vehicledefect.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			vehicledefect.setDate(common.getDateFromPicker(vehicledefect.getDate()));
			id=vehicledefectdao.saveVehicleDefect(vehicledefect);
			addActionMessage("Vehicle Defect Id " +id+ " Inserted SuccessFully");
			setInsertstatus("success");
		}catch(Exception e)
		{
			addActionMessage("DataBase Error!!");
			setInsertstatus("fail");
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		finally{
			
		}
		}else{
			setInsertstatus("duplicate");
			addActionMessage("Vehicle Defect already Exist!!");
			Vehiclelist=vehicledefectdao.getVehicleList();
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
	}
	
	@SkipValidation
	public String deleteVehicleDefects()
	{
		VehicleDefectsDao dao=new VehicleDefectsDao();
		VehicleDefects defect=new VehicleDefects();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "vehicledefectlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		defect.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			dao.deletevehicledefect(defect,Integer.parseInt(request.getParameter("deldefectid")));
			//System.out.println("Defect Id-------"+Integer.parseInt(request.getParameter("deldefectid")));
			setDeletestatus("success");
		}catch(Exception e)
		{
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			return "input";
		}finally{
			
			addActionMessage("Vehicle Defect Id "+request.getParameter("deldefectid")+ " Deleted SuccessFully");
		}
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");

			return "input";
		}
	}
	
	@SkipValidation
	public String getEditDetails() {
		VehicleDefectsDao dao = new VehicleDefectsDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		vehicledefect = dao.getEditedVehicleDefect(Integer.parseInt(request.getParameter("editdefectid")));
		Vehiclelist=dao.getVehicleList();		
		//vehicledefect.setDefectdate(common.getDateFromPicker(vehicledefect.getDate()));
		//System.out.println(vehicledefect.getDefectdate()+"          "+vehicledefect.getDate());
		return "success";
		
	}
	public String updateDdefectDetails() throws Exception{
		VehicleDefectsDao defectdao = new VehicleDefectsDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "vehicledefectlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		String defectType=vehicledefect.getDefect_type();
		//int vehicleId=vehicledefect.getVehicle().getVehicleId();
		String vehicle_date=vehicledefect.getDate();
		String []datesplit;
		datesplit=vehicle_date.split("-");
		String vehicleDate=datesplit[2]+"-"+datesplit[1]+"-"+datesplit[0];
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(vehicle_date);
		//System.out.println("UPDATE-------11"+date1+"Vehicle Date"+vehicleDate);

		if (defectdao.checkVehicleDefectForUpdate(defectType,vehicledefect.getDefect_id(),vehicleDate)) {
			vehicledefect.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			try {
				//System.out.println("UPDATE---*****-");
				
				defectdao.updateVehicleDefect(vehicledefect.getDefect_id(),vehicledefect);
			} catch (Exception ex) {
				setUpdatestatus("fail");
				return "input";
			} finally {
				setUpdatestatus("success");
				addActionMessage("Voucher Id " + vehicledefect.getDefect_id() + " Updated Successfully");
			}
		} else if(!defectdao.checkVehicleDefect(defectType,vehicleDate)){
			try {
				//System.out.println("-------Not update");
				defectdao.updateVehicleDefect(vehicledefect.getDefect_id(),vehicledefect);
			} catch (Exception ex) {
				setUpdatestatus("fail");
				return "input";
			} finally {
				setUpdatestatus("success");
				addActionMessage("Voucher Id " + vehicledefect.getDefect_id() + " Updated Successfully");
			}
		} else {
			setUpdatestatus("duplicate");
			addActionMessage("Vehicle Defect already Exist!!");
			Vehiclelist=defectdao.getVehicleList();
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
		/*String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		
		VehicleDefectsDao defectdao = new VehicleDefectsDao();
		int useriddetails=vehicledefect.getDefect_id();
		boolean flagdetails=true;
		boolean groupflag=false;
		vehicledefect.setDate(common.getDateFromPicker(vehicledefect.getDate()));
		Vehiclelist=defectdao.getVehicleList();
			//System.out.println("Designation-------"+userdetails.getDesignationtypeid().getDesignation_type_id());
		String defectType=vehicledefect.getDefect_type();
		int vehicleId=vehicledefect.getVehicle().getVehicleId();
		String vehicle_date=defectdao.getDateFromDb(vehicledefect.getDate());
		
		if(defectdao.getDuplicate(defectType,vehicleId,vehicle_date)){
		try{			
		int i=defectdao.updateVehicleDefect(useriddetails, vehicledefect);	
		addActionMessage("Vehicle Defect Id "+useriddetails+ " Updated SuccessFully");		
		setUpdatestatus("success");
		}catch(Exception e)
		{
			addActionMessage("DataBase Error!!");
			setUpdatestatus("fail");
		}
		}else{
			setUpdatestatus("duplicate");
			addActionMessage("Vehicle Defect already Exist!!");
			Vehiclelist=defectdao.getVehicleList();
			return "input";
		}
			
		//System.out.println("flag-----////////---"+flag);
		return "success";*/
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		VehicleDefectsDao vehicledefectdao = new VehicleDefectsDao();
		if(vehicledefect.getDefect_type().trim().equals("") || vehicledefect.getDefect_type().equals(" "))
		{
			addFieldError("defecttype", "Defect Type Name is Required");
			Vehiclelist=vehicledefectdao.getVehicleList();
			vehicledefect.setDefect_type(vehicledefect.getDefect_type());
			
		}
		if(!common.isSpecialChar(vehicledefect.getDefect_type()))
		{
			addFieldError("defecttype", "Special Character are not allowed for defect Type Name");
			Vehiclelist=vehicledefectdao.getVehicleList();
			this.vehicledefect.setDefect_type(vehicledefect.getDefect_type());
		}
//		if(vehicledefect.getVehicle().getVehicleId() == 0){
//			addFieldError("vehicleId", "Please select Vehicle Number");
//			Vehiclelist=vehicledefectdao.getVehicleList();
//			this.vehicledefect.setVehicle(vehicledefect.getVehicle());
//		}else if(vehicledefect.getVehicle().getVehicleId() == -1){
//			addFieldError("vehicleId", "Please select Valid Vehicle Number");
//			Vehiclelist=vehicledefectdao.getVehicleList();
//			this.vehicledefect.setVehicle(vehicledefect.getVehicle());
//		}
		if (common.isEmpty(vehicledefect.getDate())) {
			addFieldError("defect_date", "Date is Required");

			this.vehicledefect.setDate(vehicledefect.getDate());

		}
	}
	
	/*public String getvehiclenumberName() {
		System.out.println("sssssssssssss");
		VehicleDefectsDao dao = new VehicleDefectsDao();
		List<String> l1 = dao.getVehicleid();
		List<String> l2 = dao.getnumberName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='Number" + l1.get(i)+ "' value=" + l1.get(i).toString() + ">"+ l2.get(i).toString() + "</option>";
		}
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
	*/
	@SkipValidation
	public String getVehicledetails(){
		//System.out.println("in get bus stop11111111111111111111111");
		VehicleDefectsDao dao = new VehicleDefectsDao();
		HttpServletRequest request=ServletActionContext.getRequest();
	    String idVehicleName = request.getParameter("id");
	    //System.out.println("idVehicleName---"+idVehicleName);
	  //		 list = busstopsdao.getBusStopDropList();
//		 String busDet="";
	    List<VehicleNumber> list = dao.getVehicleDropList(idVehicleName);
		
			//System.out.println("Size --->"+list.size());
			//int i=1;
			//List<VehicleNumber> list1 = new ArrayList<VehicleNumber>();
				
		 HttpServletResponse response=ServletActionContext.getResponse();
		//System.out.println("Bus stops Size------>"+list.size()+busDet);
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list1.size());
			  out.print(new JSONArray(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
     	return null;
	}
}
