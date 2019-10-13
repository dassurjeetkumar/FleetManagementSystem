package com.trimax.its.device.action;

import java.io.PrintWriter;
import java.util.List;
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
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.dao.BatteryDAO;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.model.Battery;
import com.trimax.its.device.model.Device;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class BatteryAction extends ActionSupport implements Preparable {
	
	BatteryDAO daoObject = new BatteryDAO();
	DeviceDao dao=new DeviceDao();
	private Map<Integer, String> vendorList;
	
	
	public Map<Integer, String> getVendorList() {
		return vendorList;
	}

	public void setVendorList(Map<Integer, String> vendorList) {
		this.vendorList = vendorList;
	}

	private Battery battery;
		
	private List<Device> batteryList;
	
	private int isNewBatterty;
	
	public List<Device> getBatteryList() {
	
		return batteryList;
	}

	public void setBatteryList(List<Device> batteryList) {
		
		this.batteryList = batteryList;
	}
	public Battery getBattery() {
		return battery;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	
	public int getIsNewBatterty() {
		return isNewBatterty;
	}

	public void setIsNewBatterty(int isNewBatterty) {
		this.isNewBatterty = isNewBatterty;
	}
	@SkipValidation
	public String execute()
	{
		/*System.out.println("------------------------------inside action excete method");
		this.setBatteryList(daoObject.getBatteryList());*/
		
		return "success";
	}
@SkipValidation	
	public String createBattery(){
		vendorList = daoObject.getVendorIDName();
		return "success";
	}
	
	public String saveBattery(){
		
		String returnResult = "fail";
		int id=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BatteryView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		try{
			if(!daoObject.checkBattery(battery.getSerial_number())){
				battery.setCreated_by(request.getSession().getAttribute("userid").toString());
		id=daoObject.saveNewBattery(battery);
		addActionMessage("Battery Id "+ id +" Inserted Successfully");
		returnResult="success";
			}else{
				addActionMessage("Battery Serial Number already Exists!!");
				returnResult="fail";
			}
		}catch(Exception ex)
		{
			addActionMessage("DataBase Error!!");
			returnResult="success";
		}finally{
			
		}
		return returnResult;
		}else{
			return "fail";
		}
	}
	public void validate()
	{
		CommonValidation validation =new CommonValidation();
		Common common = new Common();
		HttpServletRequest request = ServletActionContext.getRequest();
		if(battery.getVendorDetails().getId()== 0){
			addFieldError("battery.vendor", "Please enter  Vendor Name");
		}
			if(battery.getSerial_number()!=null&&battery.getSerial_number().equals("")){
				addFieldError("battery.serial_number", "Please enter  serial number");
			}
			if(!validation.isSpecialChar(battery.getSerial_number().trim()))
			{
				addFieldError("battery.serial_number", "Special character for serial number not allowed");
			}
			if(battery.getCapacity()!=null && battery.getCapacity().equals("")){
				addFieldError("battery.capacity", "Please enter Capacity");
				
			}
			String puchaseDate = battery.getProcuredDateString() != null ? battery.getProcuredDateString().toString() : "";
			if (puchaseDate.equals("") && puchaseDate != null) {
				
				addFieldError("procureddate", "Procured Date  is Required");
			}
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	@SkipValidation
	public String getBatteryListAjax()
	{	
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Number cnt = daoObject.getTotalBatteryRecords();
		JSONObject result = new JSONObject();
		
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

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
			int total = -1;
			total = (Integer) cnt;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getBatteryAjaxList(total,request,sCol,sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
	@SkipValidation
		public String EditBattery()
		{
			Common common = new Common();
			HttpServletRequest request = ServletActionContext.getRequest();
			int batteryID=Integer.parseInt(request.getParameter("value").toString());
			battery=daoObject.getEditedBattery(batteryID);
			battery.setProcuredDateString(common.getDateToDatePicker(battery.getProcured_date()));
			return "success";
		}
	public String saveEditedBattery()
	{
		int id = 0;
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BatteryView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(battery.getBattery_id(),"Battery");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  battery.getStatus().equals("ACTIVE"))){	
	//	HttpServletRequest request = ServletActionContext.getRequest();
		if (daoObject.checkBatteryForUpdate(battery.getSerial_number(),	battery.getBattery_id())) {
			battery.setUpdated_by((Integer.parseInt(request.getSession().getAttribute("userid").toString())));
			battery.setUpdated_date(new java.util.Date());
			daoObject.updateBattery(battery, battery.getBattery_id());
			addActionMessage("Battery Id " + battery.getBattery_id()	+ " Updated Successfully");
			
		} else if (!daoObject.checkBattery(battery.getSerial_number())) {
			battery.setUpdated_by((Integer.parseInt(request.getSession().getAttribute("userid").toString())));
			battery.setUpdated_date(new java.util.Date());
			daoObject.updateBattery(battery, battery.getBattery_id());
			addActionMessage("Battery Id " + battery.getBattery_id()	+ " Updated Successfully");
		} else {
			addActionMessage("Battery Serial Number Already Exists!!");
			return "input";
		}
		}else
		{
			
			if(battery.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		return "success";
		}else{
			return "input";
		}
	}
	@SkipValidation
	public String DeleteBattery()
	{	
		Battery device = new Battery();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BatteryView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		device.setUpdated_by(Integer.parseInt(request.getSession()
				.getAttribute("userid").toString()));
		try {
			
			/*int hisid=dao.updateDeviceHistory(divhistory,Integer.parseInt(request.getParameter("value")),request);
			dao.updateDeviceidHistory(divhistory, Integer.parseInt(request.getParameter("value")),request,hisid);*/
			 DependencyChecker dc=new DependencyChecker();
				String status=dc.getStatus(Integer.parseInt(request.getParameter("value")),"Battery");
				System.out.println("status---"+status);
				if(status.equals("")){
			daoObject.deleteDevice(device,Integer.parseInt(request.getParameter("value")));
			addActionMessage("Battery Id " + request.getParameter("value")
					+ " Deleted Successfully");
				}else{
					addActionError(status);
				}
			
		} catch (Exception ex) {

		} finally {
		
		}

		return "success";
		}else{
			return "success";
		}
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		vendorList = daoObject.getVendorIDName();
	}
	

}
