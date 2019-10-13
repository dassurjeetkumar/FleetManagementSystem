package com.trimax.its.device.action;

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
import com.trimax.its.device.dao.DeviceTypeDao;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
public class DeviceTypeAction extends ActionSupport implements Preparable {
	
	private Device_Type deviceType;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	String insertstaus;
	String updatestaus;
	String deletestaus;
	public Device_Type getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Device_Type deviceType) {
		this.deviceType = deviceType;
	}
	
	public String getInsertstaus() {
		return insertstaus;
	}
	public String getUpdatestaus() {
		return updatestaus;
	}
	public String getDeletestaus() {
		return deletestaus;
	}
	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}
	public void setUpdatestaus(String updatestaus) {
		this.updatestaus = updatestaus;
	}
	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}
	@SkipValidation
	public String execute() {
		
		return null;
	}
	@SkipValidation
	public String showDeviceType()
	{
		System.out.println("Insude dEVICE tYPE");
		return "success";
	}
	@SkipValidation
	public String createDeviceType()
	{
		return "success";
	}
	
	public String createDeviceTypeAction()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showdeviceType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		DeviceTypeDao dao=new DeviceTypeDao();
		if(!dao.checkDeviceType(deviceType.getDevice_type_name())){
		deviceType.setCreated_by(request.getSession().getAttribute("userid").toString());
		deviceType.setCreated_date(new java.util.Date());
		String res="";
		int id=0;
		try{
		 id= dao.insertDeviceType(deviceType);
		}catch(Exception ex)
		{
			setInsertstaus("fail");
			return "input";
		}finally{
			setInsertstaus("success");
			addActionMessage("Device Type Id "+ id + " Inserted Successfully");
		}
		}
		else{
			setInsertstaus("duplicate");
			return "input";
		}
		return "success";
		}else{
			return "input";
		}
		
	}
	@SkipValidation
	public String editDeviceType()
	{
		DeviceTypeDao dao=new DeviceTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();

		deviceType = dao.getEditedDeviceType(Integer.parseInt(request
				.getParameter("chartid")));

		 System.out.println("Bus stops Size------>"+deviceType.getDevice_type_name());
		return "success";
		
	}
	public String addeditedDeviceType()
	{
		DeviceTypeDao dao=new DeviceTypeDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showdeviceType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		System.out.println("================>" + "\t"+deviceType.getNotes());
		
		
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(deviceType.getDevice_type_id(), "device_type");
			System.out.println("status---"+status);
		if(status.equals("")  ||(!status.equals("") &&  deviceType.getStatus().equals("ACTIVE"))){
		if(dao.checkDeviceTypeForUpdate(deviceType.getDevice_type_name(), deviceType.getDevice_type_id())){
		deviceType.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
		dao.updateDeviceType(deviceType,deviceType.getDevice_type_id());
		}catch(Exception ex){
			setUpdatestaus("fail");
			return "input";
		}finally{
			setUpdatestaus("success");
			addActionMessage("Device Type Id "+ deviceType.getDevice_type_id()+" Updated Successfully" );
		}
		}else if(!dao.checkDeviceType(deviceType.getDevice_type_name())){
			try{
				dao.updateDeviceType(deviceType,deviceType.getDevice_type_id());
				}catch(Exception ex){
					setUpdatestaus("fail");
					return "input";
				}finally{
					setUpdatestaus("success");
					addActionMessage("Device Type Id "+ deviceType.getDevice_type_id()+" Updated Successfully" );
				}
		}else
		{
			
			setUpdatestaus("duplicate");
			
			return "input";
		}
			}
		else{
			if(deviceType.getStatus().equals("INACTIVE"))
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
	public String deleteDeviceTypeAction()
	{
		String status="";
		DeviceTypeDao dao=new DeviceTypeDao();
		Device_Type device=new Device_Type();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "showdeviceType.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		device.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			status=dao.deleteDeviceType(device,Integer.parseInt(request.getParameter("deldeviceid")));
			if(status.split(":")[0].equals("success")){
				addActionMessage("Device Type Id "+Integer.parseInt(request.getParameter("deldeviceid"))+" deleted Successfully");
				status="success";
			}
			
			if(status.equals("")){
				addActionMessage("Device Type Id "+Integer.parseInt(request.getParameter("deldeviceid"))+" deleted Successfully");
				status="success";
			
			} else {
				addActionError(status);
				status= "success";
			}
		}catch(Exception ex)
		{
			
			setDeletestaus("fail");
			return "success";
		}finally{
			//setDeletestaus("success");
			//addActionMessage("Device Type Id "+request.getParameter("deldeviceid")+" Deleted Successfully");
		}
		return status;
		}else{
			return "success";
		}
		
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		if(deviceType.getDevice_type_name().trim().equals("") || deviceType.getDevice_type_name().equals(" "))
		{
			addFieldError("devicename", "DeviceType Name is Required");
			//addActionError("DeviceType Name is Required");
			deviceType.setDevice_type_name(deviceType.getDevice_type_name());
			
		}
		if(!common.isSpecialChar(deviceType.getDevice_type_name()))
		{
			addFieldError("devicename", "Special Character For Device Type Name is Not Allowed");
			//addActionError("Special Character For Device Type Name is Not Allowed");
			this.deviceType.setDevice_type_name(this.deviceType.getDevice_type_name());
		}
		/*if(deviceType.getNotes().trim().equals(""))
		{
			if(!deviceType.getNotes().isEmpty())
			addActionError("Space Not allowed");
		}*/
		/*if(!common.isSpecialChar(deviceType.getNotes()))
		{
			addFieldError("", errorMessage)
			addActionError("Special Character For Note is Not Allowed");
			//this.deviceType.setDevice_type_name(this.deviceType.getDevice_type_name());
		}*/
		
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			DeviceTypeDao devicedao = new DeviceTypeDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			/*int cnt = devicedao.getTotalRecords();
			System.out.println("Count------>" + cnt);*/
			String[] dbcol = { "","device_type_id", "device_type_name","notes","status"};
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

			String colName = dbcol[col];
			int total = -1;
			total = devicedao.getTotalRecords(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = devicedao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			//System.out.println("=====?" + ex);
			//ex.printStackTrace();
		} finally {

		}

	}
	

	
}
