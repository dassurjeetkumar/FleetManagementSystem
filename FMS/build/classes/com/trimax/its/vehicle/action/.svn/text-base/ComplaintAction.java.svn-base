package com.trimax.its.vehicle.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.dao.DeviceTypeDao;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.AccidentTypeDao;
import com.trimax.its.vehicle.dao.ComplaintDataDao;
import com.trimax.its.vehicle.model.AccidentType;
import com.trimax.its.vehicle.model.Complaint;
import com.trimax.test.model.UserTest;

public class ComplaintAction  extends ActionSupport implements Preparable{
	Complaint complaint;
	
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private  String updatestatus;;
	private String deletestaus;
	
	
	private Map<Integer, String> userlist;
	
	  public Map<Integer, String> getUserlist() {
	return userlist;
	}

	public void setUserlist(Map<Integer, String> userlist) {
		this.userlist = userlist;
	}

		public String getUpdatestatus() {
		return updatestatus;
	}

	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

		public String getDeletestaus() {
		return deletestaus;
	}

	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}

		
	 
	   @SkipValidation
	   public String execute() throws Exception {
	// TODO Auto-generated method stub
	return null;
	} 
	    
	   @SkipValidation
	    public String insertData()
	    {
	    	//System.out.println("##################################################2in sinsetdata");
			return "success";
		}

	    @SkipValidation
		public void prepare() throws Exception {
			// TODO Auto-generated method stub
			//System.out.println("*****************************************2*insert dataprepare");

			try {
	    		HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				//DeviceTypeDao devicedao = new DeviceTypeDao();
				ComplaintDataDao devicedao=new ComplaintDataDao();
				userlist = devicedao.getUserName();
				//System.out.println(userlist.size()+""+"show usrlist size");
				//ComplaintDataDao devicedao=new ComplaintDataDao();
				String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
				
				String[] dbcol = { "","complaint_id","compliant_type","incident_date","complaint_date","identification_data","complaint_description","status","complaint_media","taken_by"};
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
				total = devicedao.getTotalRecords(total,request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				
				result = devicedao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
				//System.out.println("REsult of datatable------>" + result);
				out.print(result);
			
			}
		
		catch(Exception e){
			//e.printStackTrace();
		}
			
			
		}
	    @SkipValidation
		public String createComplaint() {

			return "success";
		}
	    
	//create complaint start
		
		public String addComplaint() throws ParseException{
			//System.out.println("inside add complaint");
			ComplaintDataDao dao = new ComplaintDataDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			Common common = new Common();
			int id = 0;
			int hisid=0;
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewcomplaint.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(create.equalsIgnoreCase("Y")){
			//if (!dao.checkDevice(device.getDevice_serial_number())) {
			complaint.setIncident_date(common.getDateFromPicker(complaint.getIncident_date()));
			complaint.setComplaint_date(common.getDateFromPicker(complaint.getComplaint_date()));
					
			//System.out.println(complaint.getIncident_date()+""+"#####################################");
				complaint.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				complaint.setCreated_date(new java.util.Date());
				
				//date validation
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date1=sdf.parse(complaint.getIncident_date());
				Date date2=sdf.parse(complaint.getComplaint_date());
				
				if(common.compareDatesBrandEdit(complaint.getIncident_date(), complaint.getComplaint_date())==1){
		    		//System.out.println("Date1 is after Date2");
		    		complaint.setIncident_date(common.getDateToPicker(complaint.getIncident_date()));	
		    		complaint.setComplaint_date(common.getDateToPicker(complaint.getComplaint_date()));
		    		addActionMessage(" Incident Date should be before  Complaint Date");
		    		return "input";
		    	}
//				else if(date1.compareTo(date2)==0){
//		    		//System.out.println("Date1 is equal to Date2");
//		    		complaint.setIncident_date(common.getDateToPicker(complaint.getIncident_date()));	
//		    		complaint.setComplaint_date(common.getDateToPicker(complaint.getComplaint_date()));
//		    		addActionMessage(" Incident Date should not be equal to  Complaint Date ");
//		    		return "input";
//		    	}
		    	else if(dao.isGreaterThanCurrentDate(complaint.getIncident_date()))
				{
		    		complaint.setIncident_date(common.getDateToPicker(complaint.getIncident_date()));	
		    		complaint.setComplaint_date(common.getDateToPicker(complaint.getComplaint_date()));
					
					addFieldError("Incident_date", "Incident Date Is Not Future Date");
					//addActionError("DeviceType Name is Required");
					return "input";
								
				}
		    	else if(dao.isGreaterThanCurrentDate(complaint.getComplaint_date()))
				{
		    		complaint.setIncident_date(common.getDateToPicker(complaint.getIncident_date()));	
		    		complaint.setComplaint_date(common.getDateToPicker(complaint.getComplaint_date()));
					
					addFieldError("complaint_date", "Complaint Date Is Not Future Date");
					//addActionError("DeviceType Name is Required");
					return "input";
								
				}
				
				try {
					id = dao.insertDeviceType(complaint);
					
					
					//System.out.println("###########################inside add complaint");

					//hisid=dao.addDeviceHistory(divhistory, id,request);
					//dao.updateDeviceidHistory(divhistory, id,request,hisid);
				} catch (Exception e) {
					Logger logger = TrimaxLogger.getTrimaxLogger();
					logger.error(this.getClass() + "$Exception in LoginAction action",
							e);
					SaveRequest.save(request);
					ErrorLog log = new ErrorLog();
					log.setMsg(e.getMessage());
					ErrorLogDAO.saveException(log);
					return "input";
				} finally {
					if(id>0){
					
					addActionMessage("Complaint Id " + id + " Created Successfully");
					
				}
			
			return "success";
		}
			}else{
				return "input";
			}

}

		public Complaint getComplaint() {
			return complaint;
		}

		public void setComplaint(Complaint complaint) {
			this.complaint = complaint;
		}
		
		
		
	//delete data
		@SkipValidation
		public String deleteComplaintAction()
		{
			ComplaintDataDao dao=new ComplaintDataDao();
			Complaint complaint=new Complaint();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewcomplaint.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(delete.equalsIgnoreCase("Y")){
			complaint.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			try{
				dao.deleteDeviceType(complaint,Integer.parseInt(request.getParameter("complaintid")));
				//dao.deleteComplaint(complaint, Integer.parseInt(request.getParameter("complaintid")));
			}catch(Exception e)
			{
				Logger logger = TrimaxLogger.getTrimaxLogger();
				logger.error(this.getClass() + "$Exception in LoginAction action",
						e);
				SaveRequest.save(request);
				ErrorLog log = new ErrorLog();
				log.setMsg(e.getMessage());
				ErrorLogDAO.saveException(log);
				
				setDeletestaus("fail");
				return "input";
			}finally{
				setDeletestaus("success");
				addActionMessage("Complaint Id "+request.getParameter("complaintid")+" Deleted Successfully");
			}
			return "success";}
			else{
				setDeletestaus("success");
				addActionMessage("Access Denied - User Does Not Have Access Privileges");
				return "input";
			}
			
		}
		
		
		//edit pagecall to edit
		@SkipValidation
		public String editComplaintType()
		{
			ComplaintDataDao dao=new ComplaintDataDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			complaint=dao.getEditedDeviceType(Integer.parseInt(request.getParameter("complaintidd")));

			// System.out.println("Bus stops Size------>"+complaint.getCompliant_type());
			return "success";
			
		}
		//update edited page
		
		
		
		public String addeditedCompliant ()throws ParseException
		{
			//System.out.println("inside addeditedCompliant");
			ComplaintDataDao dao=new ComplaintDataDao();
			//ComplaintDataDao cdao=new ComplaintDataDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewcomplaint.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			
			complaint.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//date validation
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			Common common = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1=sdf.parse(complaint.getIncident_date());
			Date date2=sdf.parse(complaint.getComplaint_date());
			if(edit.equalsIgnoreCase("Y")){
			if(common.compareDatesBrandEdit(complaint.getIncident_date(), complaint.getComplaint_date())==1){
	    		//System.out.println("Date1 is after Date2");
	    		addActionMessage(" Incident Date should be before  Complaint Date");
	    		return "input";
	    	}
//	    		else if(date1.compareTo(date2)==0){
//	    		//System.out.println("Date1 is equal to Date2");
//	    		addActionMessage(" Incident Date should not be equal to Effective Complaint Date ");
//	    		return "input";
//	    	}
	    	else if(dao.isGreaterThanCurrentDatet(complaint.getIncident_date()))
			{
	    		
				//System.out.println("greter.............");
				addFieldError("Incident_date", "Incident Date Is Not Future Date");
				//addActionError("DeviceType Name is Required");
				return "input";
							
			}
	    	else if(dao.isGreaterThanCurrentDatet(complaint.getComplaint_date()))
			{
	    		
				//System.out.println("greter.............");
				addFieldError("complaint_date", "Complaint Date Is Not Future Date");
				//addActionError("DeviceType Name is Required");
				return "input";
							
			}
			try{
				//dao.updateDeviceType(complaint,complaint.getComplaint_id());
				dao.updateComplaint(complaint,complaint.getComplaint_id());
				//String a=dao.getUpdateComplaint(complaint,complaint.getComplaint_id());
			//dao.checkDeviceType(devicename)
			}catch(Exception e){
				
				Logger logger = TrimaxLogger.getTrimaxLogger();
				logger.error(this.getClass() + "$Exception in LoginAction action",
						e);
				SaveRequest.save(request);
				ErrorLog log = new ErrorLog();
				log.setMsg(e.getMessage());
				ErrorLogDAO.saveException(log);
			
				setUpdatestatus("fail");
				return "input";
			}finally{
				//setUpdatestaus("success");
				addActionMessage("Complaint Id "+ complaint.getComplaint_id()+" Updated Successfully" );
			}
//			}else if(!dao.checkDeviceType(deviceType.getDevice_type_name())){
//				try{
//					dao.updateDeviceType(deviceType,deviceType.getDevice_type_id());
//					}catch(Exception ex){
//						setUpdatestaus("fail");
//						return "input";
//					}finally{
//						setUpdatestaus("success");
//						addActionMessage("Device Type Id "+ deviceType.getDevice_type_id()+" Updated Successfully" );
//					}
//			}else
//			{
//				setUpdatestatus("duplicate");
//				return "input";
//			}
			return "success";}
			else{
				return "input";
			}
		}
		
		//validation method override
		@SkipValidation
		public void validate() {
			Common commons = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			complaint.setCreated_date(new java.util.Date());
			// TODO Auto-generated method stub
			
			//complaint.setIncident_date(commons.getDateFromPicker(complaint.getIncident_date()));
			
			//complaint.setIncident_date(commons.getDateFromPicker(complaint.getIncident_date()));

			
			CommonValidation common=new CommonValidation();
			if(complaint.getCompliant_type().trim().equals("") || complaint.getCompliant_type().equals(" "))
			{
				addFieldError("Compliant_type", "Compliant Type  is Required");
				//addActionError("DeviceType Name is Required");
				complaint.setCompliant_type(complaint.getCompliant_type());
				
			}
			if(!common.isSpecialChar(complaint.getCompliant_type()))
			{
				addFieldError("Compliant_type", "Special Character For Compliant Type  is Not Allowed");
				//addActionError("Special Character For Device Type Name is Not Allowed");
			}
			//incident date
			if(complaint.getIncident_date().trim().equals("") || complaint.getIncident_date().equals(" "))
			{
				complaint.setIncident_date(commons.getDateToPicker(complaint.getIncident_date()));	
				addFieldError("Incident_date", "Incident Date is Required");
				//addActionError("DeviceType Name is Required");
							
			}
//			if(!common.isSpecialChar(complaint.getCompliant_type()))
//	
//				addFieldError("Compliant_type", "Special Character For Compliant Type Name is Not Allowed");
//				//addActionError("Special Character For Device Type Name is Not Allowed");
//				complaint.setCompliant_type(complaint.getCompliant_type());
//			}
			
			//complaint  date
			if(complaint.getComplaint_date().trim().equals("") || complaint.getComplaint_date().equals(" "))
			{
				complaint.setComplaint_date(commons.getDateToPicker(complaint.getComplaint_date()));

				addFieldError("complaint_date", "complaint date  is Required");
				//addActionError("DeviceType Name is Required");
				
			}
//			if(!common.isSpecialChar(complaint.getCompliant_type()))
//			{
//				addFieldError("Compliant_type", "Special Character For Compliant Type Name is Not Allowed");
//				//addActionError("Special Character For Device Type Name is Not Allowed");
//				complaint.setCompliant_type(complaint.getCompliant_type());
//			}
			
			//taken by
			if(complaint.getTaken_by()==0 )
			{
				addFieldError("taken_by", "Taken By  is Required");
//				addActionError("DeviceType Name is Required");
				complaint.setTaken_by(complaint.getTaken_by());
				
			}
//			if(!common.isSpecialChar(complaint.getCompliant_type()))
//			{
//				addFieldError("Compliant_type", "Special Character For Compliant Type Name is Not Allowed");
//				//addActionError("Special Character For Device Type Name is Not Allowed");
//				complaint.setCompliant_type(complaint.getCompliant_type());
//			}
			
			if(complaint.getIdentification_data().trim().equals("") )
			{
				addFieldError("identification_data", "Identification data Required");
//				addActionError("DeviceType Name is Required");
				complaint.setIdentification_data(complaint.getIdentification_data());
				
			}
			if(!common.isSpecialChar(complaint.getIdentification_data()))
			{
				addFieldError("identification_data","Special Character For Identification Data Is Not Allowed");
//				addActionError("DeviceType Name is Required");
				complaint.setIdentification_data(complaint.getIdentification_data());
				
			}
			if(complaint.getComplaint_description().length()>50)
			{
				addFieldError("complaint_description", "Complaint Description Size Not More than 50 Words Allowed ");
//				addActionError("DeviceType Name is Required");
				complaint.setComplaint_description(complaint.getComplaint_description());
				
			}
			
			
		}

		//end
		
	
		
		
		}
		
		

