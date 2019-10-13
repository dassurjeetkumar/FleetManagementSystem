package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.trimax.its.fare.dao.RateMasterDAO;
import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.fare.model.RateMasterDetail;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.transport.dao.PeakHourDao;
import com.trimax.its.transport.dao.ScheduleDAO;
import com.trimax.its.transport.model.PeakHour;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
public class PeakHourAction extends ActionSupport implements Preparable{
	PeakHour peakhour;
	RateMaster rateMaster=new RateMaster();
	RateMasterDetail rmd=new RateMasterDetail();
	private String rateMasterid;
	private static Integer tempRateMasterId;
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
	private List<RateMasterDetail> rateMasterDetailList;
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
		 
	public List<RateMasterDetail> getRateMasterDetailList() {
		return rateMasterDetailList;
	}

	public void setRateMasterDetailList(List<RateMasterDetail> rateMasterDetailList) {
		this.rateMasterDetailList = rateMasterDetailList;
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

	public String getRateMasterid() {
		return rateMasterid;
	}

	public void setRateMasterid(String rateMasterid) {
		this.rateMasterid = rateMasterid;
	}	
	
	@SkipValidation
	   public String execute() throws Exception {
	// TODO Auto-generated method stub
		   return null;
	   } 
	
	@SkipValidation
    public String viewPeakHour(){
		return "success";	
	}
	   @SkipValidation
	    public String canclePeakCreation() {
		   PeakHourDao phDao=new PeakHourDao();	 
		  // System.out.println("##################################################2in sinsetdata"+newPeakhourId);
		  // if(newPeakhourId!=null){
		   
		   phDao.deletePeakHr();
		  // int j=1;
	    	//System.out.println("##################################################2in sinsetdata");
		  // return "cancle";
		  // }else{
			return "success";
		  // }
		}

	   @SkipValidation
		public void prepare() throws Exception {
		   ScheduleDAO scheduledao = new ScheduleDAO();
		   schedulelist= scheduledao.getScheduleType();
		  
			// TODO Auto-generated method stub
	    	int i=1;
			//System.out.println("*****************************************2*insert dataprepare");
			try {
	    		HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PeakHourDao devicedao=new PeakHourDao();
				//servicelist= devicedao.getServiceType();
				String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
				
				String[] dbcol = {"","Peak_Slack_hour_Id","peak_Name","start_time","end_time","percentage","lumpsum_amount","note","Effective_start_date","Effective_end_date","div.service_type_name","sche.scheduleName"};
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
				total =devicedao.getTotalRecords(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
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
		public String createPeakHour() {
	    	peakhour=new PeakHour();
//	    	peakhour.setStart_time("00:00");
//	    	peakhour.setEnd_time("00:00");
	    	return "success";
		}
	
		public String addPeakHour() throws ParseException{
			PeakHourDao dao = new PeakHourDao();
			RateMasterDAO rmDao=new RateMasterDAO();
			RateMaster rm=new RateMaster();
			List<RateMasterDetail> rmdList=new ArrayList<RateMasterDetail>();
			HttpServletRequest request = ServletActionContext.getRequest();
			Common common = new Common();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid,"PeakHours.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			int rateId=(request.getParameter("peakhour.rateMaster.rateMasterId").toString())!=null?Integer.parseInt(request.getParameter("peakhour.rateMaster.rateMasterId").toString()):0;
			float percent=Float.parseFloat(request.getParameter("peakhour.percentage"));			
			int lumpsum=Integer.parseInt(request.getParameter("peakhour.lumpsum_amount"));
			int incDec=Integer.parseInt(request.getParameter("peakhour.increase_decrease"));
						
			if(create.equalsIgnoreCase("Y")){
			//check for duplicate
			
			//int dupicate_id=dao.checkPeakHour(peakhour);
			int id = 0;
			int rate=0;
			int ratedetl=0;

			if(dao.checkPeakHourForDuplicate(peakhour)){
				//peakhour.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				peakhour.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				peakhour.setCreated_date(new java.util.Date());
								/*if(!(peakhour.getEffective_end_date().equalsIgnoreCase("")) && peakhour.getEffective_end_date()!=null && peakhour.getEffective_end_date().trim().length()>0){
				peakhour.setEffective_end_date(peakhour.getEffective_end_date());
				}

				peakhour.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
				peakhour.setCreated_date(new java.util.Date());
				
				//date validation
				if(!(peakhour.getEffective_end_date().equalsIgnoreCase("")) && peakhour.getEffective_end_date()!=null && peakhour.getEffective_end_date().trim().length()>0){

					if(common.compareDatesBrand(peakhour.getEffective_start_date(), peakhour.getEffective_end_date())==1){
						peakhour.setEffective_end_date(peakhour.getEffective_end_date());
						peakhour.setEffective_start_date(peakhour.getEffective_start_date());
			    		addActionMessage("Effective StartDate should be before Effective EndDate");
			    		return "input";
					}else{
						if(common.compareDatesBrand(peakhour.getEffective_start_date(), peakhour.getEffective_end_date())>0){
							peakhour.setEffective_end_date(peakhour.getEffective_end_date());
							peakhour.setEffective_start_date(peakhour.getEffective_start_date());
				    		
							//return "Effective StartDate should be before Effective EndDate";
							return "input";
						}
					}
				}else{
					
					peakhour.setEffective_end_date(null);

				}
				*/
				//peakhour.setStart_time(common.getHours(peakhour.getStarttimeString()));
				//peakhour.setEnd_time(common.getHours(peakhour.getEndtimeString()));
				//peakhour.setStarttimeString(common.getDateInHHMM(peakhour.getStart_time()));
				//peakhour.setEndtimeString(common.getDateInHHMM(peakhour.getEnd_time()));
				
				try{
					rmdList=dao.getRateMasterDetials(rateId);
					for (int i = 0; i < 70; i++) {
						int lumpsumvalue = Math.round(rmdList.get(i).getAdult()- lumpsum);
						if(incDec==2 && lumpsumvalue<0){
							addActionError("Please Enter Valid Lumpsum amount");
							//break;
							return "input";
						}else {
							continue;
						}
					}
							id = dao.insertDeviceType(peakhour);					
							rm=dao.getRateMaster(rateId);
							rate=dao.addRateMaster(rm,peakhour);
						    //rmdList=dao.getRateMasterDetials(rateId);				    
						    ratedetl= dao.addRateMasterDetail(rmdList,dao.getRateMasterId(rateId),percent,lumpsum,incDec);
						    rateMasterDetailList=dao.getRateMasterDetials(dao.getRateMasterId(rateId));
							rm=dao.getRateMasterDataById(rateId);
							setVersion(rm.getVersionNumber());
							setFareType(rm.getVersionNoServiceType().split("-")[1]);
						
				  	//insert into database
				} catch (Exception e) {
					Logger logger = TrimaxLogger.getTrimaxLogger();
					logger.error(this.getClass() + "$Exception in LoginAction action",e);
					SaveRequest.save(request);
					ErrorLog log = new ErrorLog();
					log.setMsg(e.getMessage());
					ErrorLogDAO.saveException(log);
					return "input";
				} finally {
					if(id>0 && rate>0 && ratedetl>0){
						
						//rmDao.add(rateMaster,0);
					addActionMessage("Peak Hours " + id + " Created Successfully");
					
				}
				}
			} else {
				setInsertstatus("duplicate");
				return "input";
			}
			return "success";
			}else{
				return "input";
			}
		}
				
		@SkipValidation
		 public String rateDetailsUpdate(){
				PeakHourDao phDao=new PeakHourDao();
				RateMaster rateMaster=new RateMaster();
				//setServiceTypeIds(rmDao.getServiceId());
				HttpServletRequest request = ServletActionContext.getRequest();
			
				int ratedetailId;
				if (!rateMasterid.isEmpty() ) {
					ratedetailId=Integer.parseInt(rateMasterid);
					tempRateMasterId =ratedetailId;
					// rateMasterDetailList=phDao.getRateMasterDetials(tempRateMasterId);
					
				}else {
					//System.out.println(""+ratedetailId);
					ratedetailId =tempRateMasterId;
					 //rateMasterDetailList=phDao.getRateMasterDetials(ratedetailId);
				}
				
				int rateId=phDao.getRateMasterId(ratedetailId);
				rateMasterDetailList=phDao.getRateMasterDetials(rateId);
				String fareType=request.getParameter("fareType");
				RateMasterDetail rmd;
			
				int adultFare=0,childFare=0,srFare=0;
				int flag=0; 
				int versionNo=0;
				// to indicate all stages are successfully saved
				//rateMasterDetailList=phDao.getRateMasterDetials(rateId);
				try{
				if (!phDao.checkRateMasterVersion(version)) {
					if(!version.contains(" ")){
				for(int c=1;c<=70;c++){
					
					flag=0;
					rmd=new RateMasterDetail();
					if(!(request.getParameter("adult"+c).isEmpty())){
					if(adultFare<=Integer.parseInt(request.getParameter("adult"+c)) && 
							childFare<=Integer.parseInt(request.getParameter("children"+c)) 
							&& srFare<=Integer.parseInt(request.getParameter("seniorCitizen"+c))
							){
					adultFare=Integer.parseInt(request.getParameter("adult"+c));
					//System.out.println("adultFare"+adultFare);
					childFare=Integer.parseInt(request.getParameter("children"+c));
					//System.out.println("childFare"+childFare);
					srFare=Integer.parseInt(request.getParameter("seniorCitizen"+c));
					//System.out.println("srFare"+srFare);
					//if(adultFare==0 || childFare==0 || srFare==0)
					rmd.setStageNo(Integer.parseInt(request.getParameter("stageNo"+c)));
					rmd.setAdult(adultFare);					
					rmd.setChildren(childFare);
					rmd.setSeniorCitizen(srFare);
					//if(adultFare>=0){
					
					flag=phDao.updateRateMasterDetailByPeakHour(rmd,rateId);					
					
					/*}else{
						rateMasterDetailList=phDao.getRateMasterDetials(rateId);
						  addActionError("Fare for stage "+c+" is required");	
						  flag=0;
						 //break;
						 return "input";
										
					}*/
					//rateMasterDetailList=phDao.getRateMasterDetials(rateId);
					if(flag<=0){
						addActionError("Fare for stage "+c+" is not saved please retry");	
						break;
					}

					}else{
					  rateMasterDetailList=phDao.getRateMasterDetials(rateId);
					  addActionError("Fare for stage "+c+" should  be greater than or equals to stage "+(c-1));	
					  flag=0;
					 //break;
					 return "input";
					}
					}else{
						 rateMasterDetailList=phDao.getRateMasterDetials(rateId);
						  addActionError("Fare for stage "+c+" is required");	
						//  rmd.setAdult(adultFare);
						  flag=0;
						 //break;
						 return "input";
					}
				}				
												
				if(version.isEmpty()){
					rateMasterDetailList=phDao.getRateMasterDetials(rateId);
					addActionError("Rate Master Version Number is required.");
					setVersion(version);
					return "input";
				}
				//peakhour.setRateMaster();
				//System.out.println("new-----------"+phDao.getNewRateMaster(ratedetailId));
				phDao.updatePeakHourMaster(phDao.getPeakHourId(),phDao.getNewRateMaster(ratedetailId));
			    rateMaster.setVersionNumber(version);
			    rateMaster.setVersionNoServiceType(version+"-"+fareType);			    
			   	phDao.updateRateMaster(rateMaster,rateId);
				versionNo=phDao.getRateMasterDataById(rateId).getRateMasterId();	
				}else{					
					 rateMasterDetailList=phDao.getRateMasterDetials(rateId);
					 addActionError("Space is not allowed in Rate Master Version Number.");					
					return "input";
				}
				if(flag>0){
				 addActionMessage("Rate Master Version "+versionNo+" Detail updated successfully.");
				 
				}
				} else {
					// ratedetailId=phDao.getRateMasterId(Integer.parseInt(rateMasterid));
					  rateMasterDetailList=phDao.getRateMasterDetials(rateId);
					 addActionError("Rate Master Version "+version+" already Exist.");						
					return "input";
				}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return "success";
				}
		
	//delete data
		@SkipValidation
		public String deletePeakHourAction()
		{
			
			String status="";
			PeakHourDao dao=new PeakHourDao();
			PeakHour peakhour=new PeakHour();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid,"PeakHours.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			peakhour.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			if(delete.equalsIgnoreCase("Y")){
			try{
				status=dao.deleteDeviceType(peakhour,Integer.parseInt(request.getParameter("peakhourid")));
				if(status.split(":")[0].equals("success")){
					setDeletestaus("success");
					addActionMessage("Peak Hours Id "+Integer.parseInt(request.getParameter("peakhourid"))+" deleted Successfully");
					status="success";
				}
				
				if(status.equals("")){
					setDeletestaus("success");
					addActionMessage("Peak Hours Id "+Integer.parseInt(request.getParameter("peakhourid"))+" deleted Successfully");
					status="success";
				
				} else {
					addActionError(status);
					status= "success";
				}
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
				
				//addActionMessage("Peak Hours  ID "+request.getParameter("peakhourid")+" Deleted Successfully");
			}
			return "success";
			}else{
				setDeletestaus("success");
				addActionMessage("Access Denied - User Does Not Have Access Privileges");
				return "input";
			}
			
		}
		
		
//		//edit pagecall to edit
		@SkipValidation
		public String editPeakHour()
		{
			PeakHourDao dao=new PeakHourDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			
			peakhour=dao.getEditedDeviceType(Integer.parseInt(request.getParameter("peakhouridd")));

			return "success";
			
		}
		
		public String addeditedPeakHour ()throws ParseException
		{
			PeakHourDao dao=new PeakHourDao();
			List<RateMasterDetail> rmdList=new ArrayList<RateMasterDetail>();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid,"PeakHours.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			int rateId=Integer.parseInt(request.getParameter("peakhour.rateMaster.rateMasterId"));
			float percent=Float.parseFloat(request.getParameter("peakhour.percentage"));
			int lumpsum=Integer.parseInt(request.getParameter("peakhour.lumpsum_amount"));
			int incDec=Integer.parseInt(request.getParameter("peakhour.increase_decrease"));
			
			if(edit.equalsIgnoreCase("Y")){
			//
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			Common common = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//peakhour.setPeak_Name(peakhour.getPeak_Name());
			peakhour.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			/*peakhour.setEffective_start_date(common.formatDate(peakhour.getEffective_start_date(),"dd/MM/yyyy","yyyy-MM-dd"));
			peakhour.setEffective_end_date(common.formatDate(peakhour.getEffective_end_date(),"dd/MM/yyyy","yyyy-MM-dd"));
			peakhour.setStart_time(common.formatDate(peakhour.getStart_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));
			peakhour.setEnd_time(common.formatDate(peakhour.getEnd_time(),"dd/MM/yyyy HH:mm","yyyy-MM-dd HH:mm:ss"));	*/
			/*if(common.compareDatesBrand(peakhour.getEffective_start_date(),peakhour.getEffective_end_date())==1){
	    		
	    		addActionMessage("Effective StartDate should be before Effective EndDate");
	    		return "input";
			}*/

			try{
				//rmdList=dao.getRateMasterDetials(dao.getRateMasterId(rateId));
				rmdList=dao.getRateMasterDetials(rateId);
				for (int i = 0; i < 70; i++) {
					int lumpsumvalue = Math.round(rmdList.get(i).getAdult()- lumpsum);
					if(incDec==2 && lumpsumvalue<0){
						addActionError("Please Enter Valid Lumpsum amount");
						//break;
						return "input";
					}else {
						continue;
					}
				}

				if(dao.checkPeakHourForUpdate(peakhour,Integer.parseInt(request.getParameter("peakhour.Peak_Slack_hour_Id")))){				

				dao.updateComplaint(peakhour,peakhour.getPeak_Slack_hour_Id());
				
				dao.updateRateMasterDetail(rmdList, rateId, percent, lumpsum, incDec);
				
				addActionMessage("Peak Hours Id "+ peakhour.getPeak_Slack_hour_Id()+" Updated Successfully" );
				}else{
					setUpdatestatus("duplicate");
					return "input";
				}
			}catch(Exception e){
				Logger logger = TrimaxLogger.getTrimaxLogger();
				logger.error(this.getClass() + "$Exception in LoginAction action",e);
				SaveRequest.save(request);
				ErrorLog log = new ErrorLog();
				log.setMsg(e.getMessage());
				ErrorLogDAO.saveException(log);
			
				return "input";
			}finally{
			}

			return "success";
			}
			else{
				return "input";
			}
		}
//		
//		//validation method override
		@SkipValidation
		public void validate() {
			HttpServletRequest request = ServletActionContext.getRequest();
			double etime=0;
			double stime=0;
			Common commons = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			CommonValidation common=new CommonValidation();
			
			if(peakhour.getServicetype().getService_type_id()==0 )
			{
				addFieldError("service_type_id", "Service Type is Required");
//				addActionError("DeviceType Name is Required");
				peakhour.setServicetype(peakhour.getServicetype());	
			}
			if(peakhour.getScheduletype().getSchedule_type_id()==0 )
			{
				addFieldError("schedule_type_id", "Schedule Type is Required");
//				addActionError("DeviceType Name is Required");
				peakhour.setScheduletype(peakhour.getScheduletype());	
			}
			if(peakhour.getRateMaster().getRateMasterId()==0){
				addFieldError("rateMasterId", "Rate Master Version is Required");
//				addActionError("DeviceType Name is Required");				
				peakhour.setRateMaster(peakhour.getRateMaster());
				rateMaster.setVersionNoServiceType(rateMaster.getVersionNoServiceType());
			}
			
			if(peakhour.getIncrease_decrease()==0){
				addFieldError("increase_decrease", "Select increase or decrease value");
//				addActionError("DeviceType Name is Required");
				peakhour.setIncrease_decrease(peakhour.getIncrease_decrease());	
			}
			
			if(peakhour.getPeak_Name().trim().equals("") || peakhour.getPeak_Name().equals(" "))
			{
				addFieldError("peak_Name", "Peak Name is Required");
				//addActionError("DeviceType Name is Required");
				peakhour.setPeak_Name(peakhour.getPeak_Name());
				
			}
			if(!common.isSpecialChar(peakhour.getPeak_Name()))
			{
				addFieldError("peak_Name", "Special Character For Peak Name  is Not Allowed");
				peakhour.setPeak_Name(peakhour.getPeak_Name());
				//addActionError("Special Character For Device Type Name is Not Allowed");
			}
			//incident date
			if(peakhour.getEffective_start_date().trim().equals("") || peakhour.getEffective_start_date().equals(" "))
			{
				peakhour.setEffective_start_date(commons.getDateToDateInpeakHour(peakhour.getEffective_start_date()));	
				addFieldError("Effective_start_date", "Effective Start Date is Required");
				//addActionError("DeviceType Name is Required");
							
			}
			
			if(!(peakhour.getEffective_end_date().trim().equals("") || peakhour.getEffective_end_date().equals(" "))){
			if(commons.compareDatesBrand(peakhour.getEffective_start_date(), peakhour.getEffective_end_date())==1){
				//System.out.println(peakhour.getStart_time()+"-----------3--------"+peakhour.getEnd_time());
				peakhour.setEffective_start_date(peakhour.getEffective_start_date());	
				peakhour.setEffective_end_date(peakhour.getEffective_end_date());
				//addFieldError("endtimeString", "End Time is Required");
				addFieldError("Effective_end_date","Effective End Date Should Be Greater Than or Equal Effective Start Date");
				//addActionError("DeviceType Name is Required");
					
				}
			if(commons.compareDatesBrand(peakhour.getStart_time().substring(0,10),peakhour.getEffective_end_date())==1){
				//System.out.println(peakhour.getStart_time()+"-----------3--------"+peakhour.getEnd_time());
				peakhour.setStart_time(peakhour.getStart_time());	
				peakhour.setEffective_end_date(peakhour.getEffective_end_date());
				//addFieldError("endtimeString", "End Time is Required");
				addFieldError("Effective_end_date","Effective End Date Should Be Greater Than or Equal Start Time Date");
				//addActionError("DeviceType Name is Required");
					
				}
			if(commons.compareDatesBrand(peakhour.getEnd_time().substring(0,10),peakhour.getEffective_end_date())==1){
				//System.out.println(peakhour.getStart_time()+"-----------3--------"+peakhour.getEnd_time());
				peakhour.setEnd_time(peakhour.getEnd_time());	
				peakhour.setEffective_end_date(peakhour.getEffective_end_date());
				//addFieldError("endtimeString", "End Time is Required");
				addFieldError("Effective_end_date","Effective End Date Should Be Greater Than or Equal End Time Date");
				//addActionError("DeviceType Name is Required");
					
				}
			}
//			if(peakhour.getEffective_end_date().trim().equals("") || peakhour.getEffective_end_date().equals(" "))
//			{
//				peakhour.setEffective_end_date(commons.getDateToPicker(peakhour.getEffective_end_date()));
//
//				addFieldError("Effective_end_date", "Effective  End Date  is Required");
//				//addActionError("DeviceType Name is Required");
//				
//			}
			if(peakhour.getLumpsum_amount()<=0 && peakhour.getPercentage()<=0)
			{
				addFieldError("percentage", "Please Enter Valid Lumpsum Amount OR Percentage");
//				addActionError("DeviceType Name is Required");
				peakhour.setLumpsum_amount(peakhour.getLumpsum_amount());
				
			}

			if(peakhour.getLumpsum_amount()==0 && peakhour.getPercentage()==0)
			{
				addFieldError("percentage", "Lumpsum Amount OR Percentage   is Required");
//				addActionError("DeviceType Name is Required");
				peakhour.setLumpsum_amount(peakhour.getLumpsum_amount());
				
			}
			if(peakhour.getLumpsum_amount()>0 && peakhour.getPercentage()>0)
			{
				addFieldError("percentage", "You Can Only Enter Lumpsum Amount OR Percentage ");
//				addActionError("DeviceType Name is Required");
				peakhour.setLumpsum_amount(peakhour.getLumpsum_amount());
				
			}
			
			if(peakhour.getPercentage()!=0 )
			{
				Float f=peakhour.getPercentage();
				String percent=f.toString().trim();
				System.out.println("position="+percent.indexOf("."));
				if((percent.indexOf("."))== 4 ||(percent.indexOf(".")) == 0)
				{
				addFieldError("percentage", "please Enter Correct Percentage Number ");
				peakhour.setPercentage(peakhour.getPercentage());
								
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
				
			else{
				//System.out.println("inside percentage else");
				peakhour.setPercentage(peakhour.getPercentage());
				}
			}
			
//		
//			if(peakhour.getNote().length()>60)
//			{
//				addFieldError("note", " Note Peak Hours Size Not More than 60 Words Allowed ");
////				addActionError("DeviceType Name is Required");
//				peakhour.setNote(peakhour.getNote());
//				
//			}
			
	
			if(peakhour.getStart_time().trim().equals("") || peakhour.getStart_time().equals(" "))
			{
				//peakhour.setStart_time(commons.getDateFromPickertopeak(peakhour.getStart_time()));	
				addFieldError("starttimeString", "Start Time is Required");
				//addActionError("DeviceType Name is Required");
				
			}

			if(peakhour.getEnd_time().trim().equals("") || peakhour.getEnd_time().equals(" "))
			{
				//peakhour.setEnd_time(commons.getDateFromPickertopeak(peakhour.getEnd_time()));

				addFieldError("endtimeString", "End Time is Required");
				//addActionError("DeviceType Name is Required");
				
			}	
			
				if(!(peakhour.getEnd_time().trim().equals("") || peakhour.getEnd_time().equals(" "))&&!(peakhour.getStart_time().trim().equals("") || peakhour.getStart_time().equals(" "))){
				if(commons.compareDatesInPeakHour(peakhour.getStart_time(), peakhour.getEnd_time())==1){				
				peakhour.setEnd_time(peakhour.getEnd_time());	
				peakhour.setStart_time(peakhour.getStart_time());
				//addFieldError("endtimeString", "End Time is Required");
				addFieldError("endtimeString","End Time Should Be Greater Than Start Time");
				//addActionError("DeviceType Name is Required");					
			}
			
				if(commons.compareDatesBrand(peakhour.getEffective_start_date(), peakhour.getStart_time().substring(0,10))==1){
				//System.out.println(peakhour.getStart_time()+"-----------3--------"+peakhour.getEnd_time());
				peakhour.setEffective_start_date(peakhour.getEffective_start_date());	
				peakhour.setStart_time(peakhour.getStart_time());
				//addFieldError("endtimeString", "End Time is Required");
				addFieldError("starttimeString","Start Time Date Should Be Greater Than or Equal Effective Start Date");
				//addActionError("DeviceType Name is Required");
					
				}
			}
			/*	
			if(peakhour.getStart_time().trim().length()>=1)
			{
			String s=peakhour.getStart_time().toString();
			String startstring[]=s.split(":");
			String start=startstring[0]+"."+startstring[1];
			stime=Double.parseDouble(start);
			}
			if(peakhour.getEnd_time().trim().length()>=1)
			{
			String s1=peakhour.getEnd_time().toString();
			String endstring[]=s1.split(":");
			String ends=endstring[0]+"."+endstring[1];
		//System.out.println(s+"^^^^^^^^^^^"+s1);
		etime=Double.parseDouble(ends);
			}
			
			if(peakhour.getEffective_end_date().trim().equals("") || peakhour.getEffective_end_date().equals(" "))
			{
				if(stime>=etime){
				peakhour.setEnd_time(peakhour.getEnd_time());	
				peakhour.setStart_time(peakhour.getStart_time());
				//addFieldError("endtimeString", "End Time is Required");
				addFieldError("endtimeString","End Time Should Be Greater Than Start Time");
				}
				}else{ 
				if(stime!=0){			
				 if(stime>=etime){
				 if(commons.compareDatesBrandEdit(peakhour.getEffective_start_date(), peakhour.getEffective_end_date())==0){
					
				peakhour.setEnd_time(peakhour.getEnd_time());	
				peakhour.setStart_time(peakhour.getStart_time());
				//addFieldError("endtimeString", "End Time is Required");
				addFieldError("endtimeString","End Time Should Be Greater Than Start Time");
				//addActionError("DeviceType Name is Required");
					
				}
				}				
			}
			}*/
			
				peakhour.setRateMaster(peakhour.getRateMaster());
				//rateMaster.setVersionNoServiceType(rateMaster.getVersionNoServiceType());
			}


		public PeakHour getPeakhour() {
			return peakhour;
		}

		public void setPeakhour(PeakHour peakhour) {
			this.peakhour = peakhour;
		}
		 
		
		 @SkipValidation
		 public String getServiceTypeForPeakHour(){
			    PeakHourDao phdao=new PeakHourDao();
				HttpServletRequest request = ServletActionContext.getRequest();			
				
				List<String> l1=phdao.getServicetypeid();
				List<String> l2=phdao.getServicetypeName();

				String regionTypeAjaxString = "";
				 regionTypeAjaxString += "<option value=0>Select</option>";
		        for (int i = 0; i < l1.size(); i++) {		        	
		            regionTypeAjaxString +=
		                    "<option id='servType"+l1.get(i)+"' value="+l1.get(i)+">" +l2.get(i)+ "</option>";
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
		 @SkipValidation
			public String getRateMasterVersion(){
			    PeakHourDao phdao=new PeakHourDao();
				HttpServletRequest request = ServletActionContext.getRequest();
				
				int rateId=Integer.parseInt(request.getParameter("serviceid"));
				List<String> l1=phdao.getRateMasterIdByServiceId(rateId);
				List<String> l2=phdao.getRateMasterVersionByServiceId(rateId);

				String regionTypeAjaxString = "";
				 regionTypeAjaxString += "<option value=0>Select</option>";
		        for (int i = 0; i < l1.size(); i++) {		        	
		            regionTypeAjaxString +=
		                    "<option id='orgType'"+l1.get(i)+" value="+l1.get(i)+">" +l2.get(i)+ "</option>";
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
		 
		 @SkipValidation
			public String getRateMasterVersionForEdit(){
			    PeakHourDao phdao=new PeakHourDao();
				HttpServletRequest request = ServletActionContext.getRequest();
				
				int rateId=Integer.parseInt(request.getParameter("serviceid"));
				List<String> l1=phdao.getRateMasterIdForEdit(rateId);
				List<String> l2=phdao.getRateMasterVersionForEdit(rateId);

				String regionTypeAjaxString = "";
				 regionTypeAjaxString += "<option value=0>Select</option>";
		        for (int i = 0; i < l1.size(); i++) {		        	
		            regionTypeAjaxString +=
		                    "<option id='orgType'"+l1.get(i)+" value="+l1.get(i)+">" +l2.get(i)+ "</option>";
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
		 
		/* 
		//ajaxvalidation
			@SkipValidation
			public void CreateAjaxPeakHour() {
				
				String ajaxString="";
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out;
				
				boolean flag=false;
				
				double etime=0;
				double stime=0;
				Common commons = new Common();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
				CommonValidation common=new CommonValidation();
				
				if(peakhour.getPeak_Name().trim().equals("") || peakhour.getPeak_Name().equals(" ")){	
					flag=true;
					ajaxString+="<ul><li>Peak Name is Required</li></ul>";
					peakhour.setPeak_Name(peakhour.getPeak_Name());	
				}
				
				if(!common.isSpecialChar(peakhour.getPeak_Name())){
					flag=true;
					ajaxString+="<ul><li>Special Character For Peak Name  is Not Allowed</li></ul>";					
				}
				
				if(peakhour.getStarttimeString().trim().equals("") || peakhour.getStarttimeString().equals(" ") || peakhour.getStarttimeString().equals("0:00")){
				    flag=true;	
					ajaxString+="Start Time is Required</li></ul>";
				}

				if(peakhour.getEndtimeString().trim().equals("") || peakhour.getEndtimeString().equals(" ") || peakhour.getEndtimeString().equals("0:00")){
					flag=true;
					ajaxString+="End Time is Required</li></ul>";		
				}
				
				if(peakhour.getStart_time().trim().length()>=1)
				{
				String s=peakhour.getStart_time().toString();
				String startstring[]=s.split(":");
				String start=startstring[0]+"."+startstring[1];
				stime=Double.parseDouble(start);
				}
				if(peakhour.getEnd_time().trim().length()>=1)
				{
				String s1=peakhour.getEnd_time().toString();
				String endstring[]=s1.split(":");
				String ends=endstring[0]+"."+endstring[1];
			//System.out.println(s+"^^^^^^^^^^^"+s1);
				etime=Double.parseDouble(ends);
				}
				if(stime!=0)
				{
				if(stime>etime){
					flag=true;
					ajaxString+="<ul><li>End Time should be Greater Than Start Time</li></ul>";
				}
				
				if(stime==etime){
					flag=true;
					ajaxString+="<ul><li>End Time should be Greater Than Start Time</li></ul>";
					}
				}

				if(peakhour.getServicetype().getService_type_id()==0 ){	
					flag=true;
					ajaxString+="<ul><li>Service Type is Required</li></ul>";
					peakhour.setServicetype(peakhour.getServicetype());	
				}
				if(peakhour.getRateMaster().getRateMasterId()==0 ){	
					flag=true;
					ajaxString+="<ul><li>Rate Master Version is Required</li></ul>";
					peakhour.setRateMaster(peakhour.getRateMaster());	
				}
				
				if(peakhour.getPercentage()!=0 )
				{
					Float f=peakhour.getPercentage();
					String percent=f.toString().trim();
					System.out.println("position="+percent.indexOf("."));
					if((percent.indexOf("."))== 4 ||(percent.indexOf(".")) == 0)
					{flag=true;
					
					peakhour.setPercentage(peakhour.getPercentage());
					ajaxString+="<ul><li>Please Enter Correct Percentage Number</li></ul>";

					}
					else if((percent.length())>=3  && ((percent.indexOf("."))==5||(percent.indexOf("."))==4)){
						flag=true;
						ajaxString+="<ul><li>Please Enter Correct Percentage Number</li></ul>";
					}
					else if((percent.length())>5  ){
						flag=true;
						ajaxString+="<ul><li>Please Enter Correct Percentage Number</li></ul>";
					}
					else if(f>100 ){
						flag=true;
						ajaxString+="<ul><li>Please Enter Correct Percentage Number</li></ul>";	
					}
					
					else if((percent.equals("100.0")&&( percent.indexOf("."))!= 3)){
						flag=true;
						ajaxString+="<ul><li>Please Enter Correct Percentage Number</li></ul>";
					}				
				else{
					peakhour.setPercentage(peakhour.getPercentage());
					}
				}
				if(peakhour.getPercentage()==0 ){
					flag=true;
					ajaxString+="<ul><li>Percentage  is Required</li></ul>";
				}
			

				if(peakhour.getLumpsum_amount()==0 ){
					flag=true;
					ajaxString+="<ul><li>Lumpsum Amount  is Required</li></ul>";
					peakhour.setLumpsum_amount(peakhour.getLumpsum_amount());
				}
				if(peakhour.getIncrease_decrease()==0){
					flag=true;
					ajaxString+="<ul><li>Please Select Option</li></ul>";
					peakhour.setIncrease_decrease(peakhour.getIncrease_decrease());
				}
				if(peakhour.getEffective_start_date().trim().equals("") || peakhour.getEffective_start_date().equals(" ")){
				    flag=true;
					peakhour.setEffective_start_date(commons.getDateToPicker(peakhour.getEffective_start_date()));	;
					ajaxString+="<ul><li>Effective Start Date is Required</li></ul>";			
				}

					try{
						if(!flag){
							String str=addPeakHourAjax();
							ajaxString+=str;
						}
					}catch(Exception e){
						
						e.printStackTrace();
					}
					
					try {
						out = response.getWriter();
						out.print(ajaxString);
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

			
			 @SkipValidation
			public String CreateAjaxPeakHourtest()
			{
				
				return "success";
			}
			
	//ajax validation end
			 @SkipValidation
			 public String addPeakHourAjax() throws ParseException{
					PeakHourDao dao = new PeakHourDao();
					HttpServletRequest request = ServletActionContext.getRequest();
					Common common = new Common();

					int id = 0;

					//check for duplicate
					//int dupicate_id=dao.checkPeakHour(peakhour);
					
					if(dao.checkPeakHourForDuplicate(peakhour)){
					peakhour.setEffective_start_date(common.getDateFromPicker(peakhour.getEffective_start_date()));
					if(!(peakhour.getEffective_end_date().equalsIgnoreCase("")) && peakhour.getEffective_end_date()!=null && peakhour.getEffective_end_date().trim().length()>0)
					{
						peakhour.setEffective_end_date(common.getDateFromPicker(peakhour.getEffective_end_date()));
					}
				

					peakhour.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
					peakhour.setCreated_date(new java.util.Date());
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String data_show=peakhour.getEffective_end_date();

						if(!(peakhour.getEffective_end_date().equalsIgnoreCase("")) && peakhour.getEffective_end_date()!=null && peakhour.getEffective_end_date().trim().length()>0)
						{						
							if(common.compareDatesBrandEdit(peakhour.getEffective_start_date(), peakhour.getEffective_end_date())==1){
								peakhour.setEffective_end_date(common.getDateToPicker(peakhour.getEffective_end_date()));
								peakhour.setEffective_start_date(common.getDateToPicker(peakhour.getEffective_start_date()));
					    		
								return "Effective StartDate should be before Effective EndDate";
							}else{
								if(common.compareDatesBrandEdit(peakhour.getEffective_start_date(), peakhour.getEffective_end_date())==0){
									peakhour.setEffective_end_date(common.getDateToPicker(peakhour.getEffective_end_date()));
									peakhour.setEffective_start_date(common.getDateToPicker(peakhour.getEffective_start_date()));
						    		
									return "Effective StartDate should be before Effective EndDate";
								}
							}
							
						}
						else{
							peakhour.setEffective_end_date(common.getDateToPicker(peakhour.getEffective_end_date()));
						}
						
						try {
							id = dao.insertDeviceType(peakhour);
							
						} catch (Exception e) {
							Logger logger = TrimaxLogger.getTrimaxLogger();
							logger.error(this.getClass() + "$Exception in LoginAction action",
									e);
							SaveRequest.save(request);
							ErrorLog log = new ErrorLog();
							log.setMsg(e.getMessage());
							ErrorLogDAO.saveException(log);
							
						} finally {
							if(id>0){
								return ("Peak Hours " + id + " Created Successfully");
						}
						}
					} else {
						
						return "Peak Hours already exits";
					}
					return "Database Error.";
				}*/

			public Map<Integer, String> getSchedulelist() {
				return schedulelist;
			}

			public void setSchedulelist(Map<Integer, String> schedulelist) {
				this.schedulelist = schedulelist;
			}
}
