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
import com.trimax.its.device.dao.RFIDCardDao;
import com.trimax.its.device.model.RFIDCard;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class RFIDCardAction extends ActionSupport implements Preparable{
	RFIDCard rfidcard;
	String insertstatus;
	String deletestatus;
	String updatestatus;
	Common common = new Common();
	
	public RFIDCard getRfidcard() {
		return rfidcard;
	}
	public void setRfidcard(RFIDCard rfidcard) {
		this.rfidcard = rfidcard;
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
	public String viewRFIDCard() {
		return "success";
	}
	
	@SkipValidation
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			RFIDCardDao rfidcarddao = new RFIDCardDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

			String[] cols = { "", "rfid_card_id", "serial_number","status","procured_date",
					"ven.vendor_name","manufacturing_id",""};
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
			total = rfidcarddao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = rfidcarddao.getData(total, request,
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
	public String createRFIDCard() {
		RFIDCardDao rfidcarddao = new RFIDCardDao();
		Vendorlist=rfidcarddao.getVendorList();
		return "success";
	}
	
	public String createRFIDCardAction()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "rfidcardlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		RFIDCardDao rfidcarddao = new RFIDCardDao();
		Vendorlist=rfidcarddao.getVendorList();
		int id=0;
         if(create.equalsIgnoreCase("Y")){
		if(!rfidcarddao.getDuplicate(rfidcard.getSerial_number()))
		{
		try{
		//	System.out.println("............."+vehicledefect.getVehicle());
			rfidcard.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			rfidcard.setProcured_date(common.getDateFromDatePicker(rfidcard.getStringprocureddate()));
			id=rfidcarddao.saveRFIDCard(rfidcard);
			addActionMessage("RFID Card Id "+id+ " Created Successfully");
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
			addActionMessage("RFID Card already Exist!!");
			Vendorlist=rfidcarddao.getVendorList();
			return "input";
		}
		return "success";
         }else{
        	 return "input";
         }
	}
	
	@SkipValidation
	public String deleteRFIDcard()
	{
		RFIDCardDao dao=new RFIDCardDao();
		RFIDCard rfidcard=new RFIDCard();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "rfidcardlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		rfidcard.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			dao.deleteRFIDcard(rfidcard,Integer.parseInt(request.getParameter("delrfidcardid")));
			//System.out.println("Defect Id-------"+Integer.parseInt(request.getParameter("deldefectid")));
			setDeletestatus("success");
		}catch(Exception e)
		{
			//System.out.println(ex);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
			return "input";
		}finally{
			
			addActionMessage("RFID Card Id "+request.getParameter("delrfidcardid")+ " Deleted SuccessFully");
		}
		return "success";
		}else{
			return "success";
		}
	}

	@SkipValidation
	public String getEditDetails() {
		RFIDCardDao dao = new RFIDCardDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		rfidcard = dao.getEditedRFIDCard(Integer.parseInt(request
				.getParameter("editrfidcardid")));
		Vendorlist=dao.getVendorList();		
		rfidcard.setStringprocureddate(common.getDateToDatePicker(rfidcard.getProcured_date()));
		//System.out.println(vehicledefect.getDefectdate()+"          "+vehicledefect.getDate());
		return "success";
		
	}
	public String updateRFIDCardDetails(){
		String flag="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "rfidcardlist.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		RFIDCardDao dao = new RFIDCardDao();
		int useriddetails=rfidcard.getRfid_card_id();
		boolean flagdetails=true;
		boolean groupflag=false;
		rfidcard.setProcured_date(common.getDateFromDatePicker(rfidcard.getStringprocureddate()));
		Vendorlist=dao.getVendorList();	
		flagdetails=dao.getUpdateDuplicate(rfidcard.getSerial_number(),rfidcard.getRfid_card_id());
	//	System.out.println("-------"+flagdetails);
		if(flagdetails==true)
		{		
		 int i=dao.updateRFIDCard(useriddetails, rfidcard);	
		addActionMessage("RFID Card Id "+useriddetails+ " Updated SuccessFully");		
		setUpdatestatus("success");
		flag="success";
		}
		else{
			setInsertstatus("duplicate");
			addActionError("RFID Card already Exist!!");
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
		RFIDCardDao dao = new RFIDCardDao();
		
		if(rfidcard.getSerial_number().trim().equals("") || rfidcard.getSerial_number().equals(" "))
		{
			addFieldError("serialnumber", "Serial Number is Required");
			//addActionError("DeviceType Name is Required");
			Vendorlist=dao.getVendorList();
			rfidcard.setSerial_number(rfidcard.getSerial_number());
			
		}
		if(!common.isSpecialChar(rfidcard.getSerial_number()))
		{
			addFieldError("serialnumber", "Special Character For Serial Number is Not Allowed");
			//addActionError("Special Character For Device Type Name is Not Allowed");
			Vendorlist=dao.getVendorList();
			this.rfidcard.setSerial_number(rfidcard.getSerial_number());
		}
		
		if(rfidcard.getVendor().getId() == 0){
			addFieldError("vendorId","Please select Vendor");
			Vendorlist=dao.getVendorList();
			this.rfidcard.setVendor(rfidcard.getVendor());
		}
		if(rfidcard.getProcured_date()==null && rfidcard.getStringprocureddate().equals(""))
		{
			addFieldError("pdate", "Procured Date is Required");
			Vendorlist=dao.getVendorList();
			//datacard.setProcured_date(datacard.getProcured_date());			
		}

		
	}
	
}
