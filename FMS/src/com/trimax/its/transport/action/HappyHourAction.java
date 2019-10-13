package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.fare.model.RateMasterDetail;
import com.trimax.its.pass.dao.PassDistanceTypeDAO;
import com.trimax.its.pass.dao.PassDurationTypeDAO;
import com.trimax.its.pass.model.PassDistanceType;
import com.trimax.its.transport.dao.HappyHourDao;
import com.trimax.its.transport.dao.PeakHourDao;
import com.trimax.its.transport.dao.ScheduleDAO;
import com.trimax.its.transport.model.HappyHour;
import com.trimax.its.transport.model.PeakHour;

public class HappyHourAction extends ActionSupport{
	public HappyHour happy;
	
	public HappyHour getHappy() {
		return happy;
	}

	public void setHappy(HappyHour happy) {
		this.happy = happy;
	}
	private String version;
    private String fareType;   
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private  String updatestatus;;
	private String deletestaus;
	private String insertstatus;
	
	private Map<Integer, String> servicelist;
	private Map<Integer, String> schedulelist; 

		public Map<Integer, String> getServicelist() {
		return servicelist;
	}

		public String getInsertstatus() {
			return insertstatus;
		}

		public void setInsertstatus(String insertstatus) {
			this.insertstatus = insertstatus;
		}
	public void setServicelist(Map<Integer, String> servicelist) {
		this.servicelist = servicelist;
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
		 
	

	public String getVersion() {
		return version.trim();
	}

	public void setVersion(String version) {
		this.version = version.trim();
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	
	
	@SkipValidation
	   public String execute() throws Exception {
	// TODO Auto-generated method stub
		 ScheduleDAO scheduledao = new ScheduleDAO();
		
		   servicelist= scheduledao.getServiceType();
		   schedulelist= scheduledao.getScheduleType();
		   return null;
	   } 
	
	
	@SkipValidation
    public String viewHappyHour(){
		return "success";	
	}
	   @SkipValidation
	    public String cancleHappyCreation() {
		   HappyHourDao hhDao=new HappyHourDao();	 
		   hhDao.deleteHappyHr();
			return "success";
		  
		}

	   @SkipValidation
		public void displayHappyhour() throws Exception {
		  
//		   schedulelist= scheduledao.getScheduleType();
//		   servicelist= scheduledao.getServiceType();
		  
			// TODO Auto-generated method stub
	    	int i=1;
			//System.out.println("*****************************************2*insert dataprepare");
			try {
	    		HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				HappyHourDao devicedao = new HappyHourDao();
				
				String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
				
				String[] dbcol = {"","Id","name","start_time","end_time","percentage","lumpsum_amount","Effective_start_date","Effective_end_date","sche.scheduleName"};
				JSONObject result = new JSONObject();
				int amount = 10;
				int start = 0;
				int col = 0;
				String dir = "asc";
				String sStart = request.getParameter("iDisplayStart");
				String sAmount = request.getParameter("iDisplayLength");
				String sCol = request.getParameter("iSortCol_0");
				//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&sCol---"+sCol);
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
				total =devicedao.getTotalRecords(dbcol[Integer.parseInt(sCol)], sdir);
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				result = devicedao.getData(total, request, dbcol[Integer.parseInt(sCol)], sdir);
				//System.out.println("REsult of datatable------>" + result);
				out.print(result);
			
			}
		
		catch(Exception e){
			//e.printStackTrace();
		}
			
			
		}
	    public Map<Integer, String> getSchedulelist() {
		return schedulelist;
	}

	public void setSchedulelist(Map<Integer, String> schedulelist) {
		this.schedulelist = schedulelist;
	}

		@SkipValidation
		public String createHappyHour() {
			 ScheduleDAO scheduledao = new ScheduleDAO();
			schedulelist= scheduledao.getScheduleType();
			servicelist =scheduledao.getServiceType();
			happy=new HappyHour();
//	    	peakhour.setStart_time("00:00");
//	    	peakhour.setEnd_time("00:00");
	    	return "success";
		}
		public String addHappyHour(){
			 ScheduleDAO scheduledao = new ScheduleDAO();
	        HttpServletRequest request = ServletActionContext.getRequest();
			HappyHourDao dao = new HappyHourDao();
			try{
				//double percent=Double.parseDouble(request.getParameter("percentage"));			
				//int lumpsum=Integer.parseInt(request.getParameter("lumpsum_amount"));
				//int incresedecrese=Integer.parseInt(request.getParameter("increase_decrease"));
				happy.setName(happy.getName().trim());
				happy.setPercentage(happy.getPercentage());
				happy.setLumpsum_amount(happy.getLumpsum_amount());
				happy.setIncrease_decrease(happy.getIncrease_decrease());
				happy.setService_type_id(happy.getService_type_id());
//				happy.setSchedule_type_id(happy.getSchedule_type_id());
				happy.setEffective_start_date(happy.getEffective_start_date());
				if(happy.getEffective_end_date().trim().equalsIgnoreCase("") || happy.getEffective_end_date().equals("") || happy.getEffective_end_date()==null){
					happy.setEffective_end_date(null);
				}else{
				happy.setEffective_end_date(happy.getEffective_end_date());
				}
				happy.setCreated_date(new java.util.Date());
				happy.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				happy.setDeleted_status(0);
				happy.setUpdated_by(0);
				happy.setUpdated_date(null);
			
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
//			AccessRightsDao  accessrightdao=new AccessRightsDao();
//			AccessRights accessrights=new AccessRights();
//			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassIssuerTypeAction!view");
//			String access=accessrights.getACCESS();
//			String create=accessrights.getCREATE();
//			String edit=accessrights.getEDIT();
//			String delete=accessrights.getDELETE();
			
//			if(create.equalsIgnoreCase("Y")){
			int i=dao.addHappyHour(happy);
			
			if(i>0){
				addActionMessage("Happy Hour id "+i+" created successfully.");
			}else{
				if(i==-1){
					schedulelist= scheduledao.getScheduleType();
					addFieldError("name","Happy Hour Type already exist.");
					return INPUT;
				}else{
					schedulelist= scheduledao.getScheduleType();
					addActionError("Database error retry again.");
					return INPUT;
				}
			}
			 //ScheduleDAO scheduledao = new ScheduleDAO();
				schedulelist= scheduledao.getScheduleType();
			//execute();
			return SUCCESS;
			
//			}else{
//				return INPUT;
//			}
			 
		
			//return "success";
		}
		
		@SkipValidation
		public String editHappyHour() {
			HttpServletRequest request = ServletActionContext.getRequest();
			 ScheduleDAO scheduledao = new ScheduleDAO();
				schedulelist= scheduledao.getScheduleType();
				servicelist =scheduledao.getServiceType();
			int id=Integer.parseInt(request.getParameter("passid"));
			HappyHourDao dao = new HappyHourDao();
			happy=dao.getHappyHourId(id);
			
			
			return "success";
		}
		
		
		public String addeditedHappyHour(){
			HttpServletRequest request = ServletActionContext.getRequest();
//			AccessRightsDao  accessrightdao=new AccessRightsDao();
//			AccessRights accessrights=new AccessRights();
//			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
//			String access=accessrights.getACCESS();
//			String create=accessrights.getCREATE();
//			String edit=accessrights.getEDIT();
//			String delete=accessrights.getDELETE();
//			if(edit.equalsIgnoreCase("Y")){
			 ScheduleDAO scheduledao = new ScheduleDAO();
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(happy.getId(),"happytype");
//		if(status.equals("")||(!status.equals("") &&  happy.getStatus().equals("Active"))){
			
			HappyHourDao dao = new HappyHourDao();
			
			happy.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			happy.setUpdated_date(new java.util.Date());
			
			int i=dao.updateHappyHourType(happy);
			
			if(i>0){
				addActionMessage("Happy Hour Type id "+happy.getId()+" updated successfully.");
			}else{
				if(i==-1){
					addFieldError("name","Happy Hour Type already exist.");
					return INPUT;
				}else{
					
					addActionError("Database error retry again.");
					return INPUT;
				}
			}
//		}else
//		{
//			
//			if(passdistance.getStatus().equals("Inactive"))
//			{
//				//setUpdatestaus("success");
//			addActionError(status);
//			return "input";
//			}	
//			
//		}
			//execute();
			return SUCCESS;
//			}else{
//				return INPUT;
//			}
		}
		
		@SkipValidation
		public String deleteHappyHourAction() {
			
			String status="";
			HttpServletRequest request = ServletActionContext.getRequest();
//			AccessRightsDao  accessrightdao=new AccessRightsDao();
//			AccessRights accessrights=new AccessRights();
//			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
//			String access=accessrights.getACCESS();
//			String create=accessrights.getCREATE();
//			String edit=accessrights.getEDIT();
//			String delete=accessrights.getDELETE();
			int id=Integer.parseInt(request.getParameter("passid"));
//			if(delete.equalsIgnoreCase("Y")){
			HappyHourDao dao = new HappyHourDao();
			status=dao.deleteHappyHourType(id);
			if(status.split(":")[0].equals("success")){
				
				addActionMessage("Happy Hour id "+id+" deleted successfully.");		
				status="success";
			}
			
			if(status.equals("")){
				
				addActionMessage("Happy Hour id "+id+" deleted successfully.");		
				status="success";
				
				
			
			} else {
				addActionError(status);
				status= "success";
			}

			return "success";
//			}
//			else{
//				addActionMessage("Access Denied - User Does Not Have Access Privileges");
//				return "success";
//			}
		}
		
		@Override
		public void validate() {
			HttpServletRequest request = ServletActionContext.getRequest();
			double etime=0;
			double stime=0;
			Common commons = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			CommonValidation common=new CommonValidation();
			
			if (happy.getName() == null || happy.getName().trim().equals("")) {
				addFieldError("name","Please enter Happy Hour Name");
			}
			if(!common.isSpecialChar(happy.getName()))
			{
				addFieldError("peak_Name", "Special Character For Peak Name  is Not Allowed");
				happy.setName(happy.getName());
				//addActionError("Special Character For Device Type Name is Not Allowed");
			}
		
			if (happy.getStart_time() == null || happy.getStart_time().equals("")) {
				addFieldError("start_time","Please enter Start time");
			}
			if (happy.getEnd_time() == null || happy.getEnd_time().equals("")) {
				addFieldError("end_time","Please enter End time");
			}
//			if (happy.getService_type_id() == 0 ) {
//				addFieldError("service_type_id","Please enter Service Type");
//			}
//			if (happy.getSchedule_type_id() == 0 ) {
//				addFieldError("schedule_type_id","Please enter Schedule Type");
//			}
//			if (happy.getSchedule_type_id() == 0 ) {
//				addFieldError("schedule_type_id","Please enter Schedule Type");
//			}
			
			if(happy.getIncrease_decrease()==2){
				addFieldError("increase_decrease", "Select increase or decrease value");
				happy.setIncrease_decrease(happy.getIncrease_decrease());	
			}
			
			
			if(happy.getLumpsum_amount()<=0 && happy.getPercentage()<=0)
			{
				addFieldError("percentage", "Please Enter Valid Lumpsum Amount OR Percentage");
//				addActionError("DeviceType Name is Required");
				happy.setLumpsum_amount(happy.getLumpsum_amount());
				
			}

			if(happy.getLumpsum_amount()==0 && happy.getPercentage()==0)
			{
				addFieldError("percentage", "Lumpsum Amount OR Percentage   is Required");
//				addActionError("DeviceType Name is Required");
				happy.setLumpsum_amount(happy.getLumpsum_amount());
				
			}
			if(happy.getLumpsum_amount()>0 && happy.getPercentage()>0)
			{
				addFieldError("percentage", "You Can Only Enter Lumpsum Amount OR Percentage ");
//				addActionError("DeviceType Name is Required");
				happy.setLumpsum_amount(happy.getLumpsum_amount());
				
			}
			
			if(!(happy.getEffective_end_date().trim().equals("") || happy.getEffective_end_date().equals(" "))){
//				System.out.println("happy.getEffective_end_date()==="+happy.getEffective_end_date());
//				System.out.println("happy.getEffective_start_date()==="+happy.getEffective_start_date());

				if(commons.compareDatesBrandEdit(happy.getEffective_start_date(), happy.getEffective_end_date())==1){
//					System.out.println(peakhour.getStart_time()+"-----------3--------"+peakhour.getEnd_time());
					happy.setEffective_start_date(happy.getEffective_start_date());	
					happy.setEffective_end_date(happy.getEffective_end_date());
					//addFieldError("endtimeString", "End Time is Required");
					addFieldError("Effective_end_date","Effective End Date Should Be Greater Than or Equal Effective Start Date");
					//addActionError("DeviceType Name is Required");
						
					}
				if(commons.compareTwoDatesEdit(happy.getStart_time().substring(0,10),happy.getEffective_end_date())==1){
					//System.out.println(peakhour.getStart_time()+"-----------3--------"+peakhour.getEnd_time());
					happy.setStart_time(happy.getStart_time());	
					happy.setEffective_end_date(happy.getEffective_end_date());
					//addFieldError("endtimeString", "End Time is Required");
					addFieldError("Effective_end_date","Effective End Date Should Be Greater Than or Equal Start Time Date");
					//addActionError("DeviceType Name is Required");
						
					}
				if(commons.compareTwoDatesEdit(happy.getEnd_time().substring(0,10),happy.getEffective_end_date())==1){
					//System.out.println(peakhour.getStart_time()+"-----------3--------"+peakhour.getEnd_time());
					happy.setEnd_time(happy.getEnd_time());	
					happy.setEffective_end_date(happy.getEffective_end_date());
					//addFieldError("endtimeString", "End Time is Required");
					addFieldError("Effective_end_date","Effective End Date Should Be Greater Than or Equal End Time Date");
					//addActionError("DeviceType Name is Required");
						
					}
				}
			
			if(happy.getPercentage()!=0 )
			{
				Double f=happy.getPercentage();
				String percent=f.toString().trim();
				if((percent.indexOf("."))== 4 ||(percent.indexOf(".")) == 0)
				{
				addFieldError("percentage", "please Enter Correct Percentage Number ");
				happy.setPercentage(happy.getPercentage());
								
				}
				else if((percent.length())>=3  && ((percent.indexOf("."))==5||(percent.indexOf("."))==4)){
					addFieldError("percentage", "please Enter Correct Percentage Number ");
					
				}
				else if((percent.length())>5  ){
					
					addFieldError("percentage", "please Enter Correct Percentage Number ");
					
				}
				else if(f>100 ){
					
					addFieldError("percentage", "please Enter Correct Percentage Number ");
					
				}
				
				else if((percent.equals("100.0")&&( percent.indexOf("."))!= 3)){
					addFieldError("percentage", "please Enter Correct Percentage Number ");
					
				}
			}
			else{
				//System.out.println("inside percentage else");
				happy.setPercentage(happy.getPercentage());
			}
			if(hasFieldErrors()){
			 ScheduleDAO scheduledao = new ScheduleDAO();
			 schedulelist= scheduledao.getScheduleType();
				servicelist =scheduledao.getServiceType();
			}
		}
		
			
}



