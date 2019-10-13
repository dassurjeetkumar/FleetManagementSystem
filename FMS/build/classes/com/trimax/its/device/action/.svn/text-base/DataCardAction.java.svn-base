package com.trimax.its.device.action;

import java.io.PrintWriter;

import java.util.Date;
import java.util.Map;

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
import com.trimax.its.device.dao.DataCardDao;
import com.trimax.its.device.model.DataCard;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.VehicleDefectsDao;
import com.trimax.its.vehicle.dao.VehicleMaintenanceDao;
import com.trimax.its.vehicle.model.VehicleMaintenance;

public class DataCardAction extends ActionSupport implements Preparable{
	DataCard datacard;
	String insertstatus;
	String deletestatus;
	String updatestatus;
	Common common = new Common();
	public DataCard getDatacard() {
		return datacard;
	}
	public void setDatacard(DataCard datacard) {
		this.datacard = datacard;
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
	private String stringprocureddate;
	
	
	public String getStringprocureddate() {
		return stringprocureddate;
	}
	public void setStringprocureddate(String stringprocureddate) {
		this.stringprocureddate = stringprocureddate;
	}
	private Map<Integer, String> Vendorlist;
	public Map<Integer, String> getVendorlist() {
		return Vendorlist;
	}
	public void setVendorlist(Map<Integer, String> vendorlist) {
		Vendorlist = vendorlist;
	}
	
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SkipValidation
	public String viewDataCard() {
		return "success";
	}
	
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			DataCardDao datacarddao = new DataCardDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] cols = { "", "data_card_id", "serial_number","status","procured_date",
					"ven.vendor_name",""};
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
			total = datacarddao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = datacarddao.getData(total, request,
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
	public String createDataCard() {
		DataCardDao datacarddao = new DataCardDao();
		Vendorlist=datacarddao.getVendorList();
		return "success";
	}
	
	public String createDataCardAction()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "datacardlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		DataCardDao datacarddao = new DataCardDao();
		Vendorlist=datacarddao.getVendorList();
		int id=0;
          if(create.equalsIgnoreCase("Y")){
		if(!datacarddao.getDuplicate(datacard.getSerial_number()))
		{
		try{
		//	System.out.println("............."+vehicledefect.getVehicle());
			datacard.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			datacard.setProcured_date(common.getDateFromDatePicker(datacard.getStringprocureddate()));
			id=datacarddao.saveDataCard(datacard);
			addActionMessage("Data Card Id "+id+ " Created Successfully");
			setInsertstatus("success");
		}catch(Exception e)
		{
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			addActionMessage("DataBase Error!!");
			setInsertstatus("fail");
		}
		finally{
			
		}
		}else{
			setInsertstatus("duplicate");
			addActionMessage("Data Card already Exist!!");
			Vendorlist=datacarddao.getVendorList();
			return "input";
		}
		return "success";
          }else{
        	  return "input";
          }
	}
	
	@SkipValidation
	public String deleteDatacard()
	{
		DataCardDao dao=new DataCardDao();
		DataCard datacard=new DataCard();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "datacardlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		datacard.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			dao.deletedatacard(datacard,Integer.parseInt(request.getParameter("deldatacardid")));
			//System.out.println("Defect Id-------"+Integer.parseInt(request.getParameter("deldefectid")));
			setDeletestatus("success");
		}catch(Exception e)
		{
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			//System.out.println(ex);
			return "input";
		}finally{
			
			addActionMessage("Data Card Id "+request.getParameter("deldatacardid")+ " Deleted Successfully");
		}
		return "success";
		}else{
			return "input";
		}
	}

	@SkipValidation
	public String getEditDetails() {
		DataCardDao dao = new DataCardDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		datacard = dao.getEditedDataCard(Integer.parseInt(request
				.getParameter("editdatacardid")));
		Vendorlist=dao.getVendorList();		
		datacard.setStringprocureddate(common.getDateToDatePicker(datacard.getProcured_date()));
		//System.out.println(vehicledefect.getDefectdate()+"          "+vehicledefect.getDate());
		return "success";
		
	}
	public String updateDataCardDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "datacardlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		DataCardDao dao = new DataCardDao();
		int useriddetails=datacard.getData_card_id();
		boolean flagdetails=true;
		boolean groupflag=false;
		datacard.setProcured_date(common.getDateFromDatePicker(datacard.getStringprocureddate()));

		Vendorlist=dao.getVendorList();	
		flagdetails=dao.getUpdateDuplicate(datacard.getSerial_number(),datacard.getData_card_id());
		System.out.println("-------"+flagdetails);
		if(flagdetails==true)
		{		
		 int i=dao.updateDataCard(useriddetails, datacard);	
		addActionMessage("Data Card Id "+useriddetails+ " Updated Successfully");		
		setUpdatestatus("success");
		flag="success";
		}
		else{
			setInsertstatus("duplicate");
			addActionError("Data Card already Exist!!");
			return "input";
		}
			
		return flag;
		}else{
			return "input";
		}
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		DataCardDao dao = new DataCardDao();
		
		if(datacard.getSerial_number().trim().equals("") || datacard.getSerial_number().equals(" "))
		{
			addFieldError("serialnumber", "Serial Number is Required");
			//addActionError("DeviceType Name is Required");
			Vendorlist=dao.getVendorList();
			datacard.setSerial_number(datacard.getSerial_number());
			
		}
		if(!common.isSpecialChar(datacard.getSerial_number()))
		{
			addFieldError("serialnumber", "Special Character For Serial Number is Not Allowed");
			//addActionError("Special Character For Device Type Name is Not Allowed");
			Vendorlist=dao.getVendorList();
			this.datacard.setSerial_number(datacard.getSerial_number());
		}
		
		if(datacard.getVendor().getId() == 0){
			addFieldError("vendorId","Please select Vendor");
			Vendorlist=dao.getVendorList();
			this.datacard.setVendor(datacard.getVendor());
		}
		if(datacard.getProcured_date()==null && datacard.getStringprocureddate().equals(""))
		{
			addFieldError("pdate", "Procured Date is Required");
			//addActionError("DeviceType Name is Required");
			Vendorlist=dao.getVendorList();
			//datacard.setProcured_date(datacard.getProcured_date());
			
		}
		
//		System.out.println("validation----"+datacard.getStringprocureddate());
//		System.out.println("validation----"+datacard.getProcured_date());
		
	}
	
}
