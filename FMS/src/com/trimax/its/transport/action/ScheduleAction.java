package com.trimax.its.transport.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.transport.dao.FormFourDAO;
import com.trimax.its.transport.dao.ScheduleDAO;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.FormFourType;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.vehicle.model.BrandType;

public class ScheduleAction extends ActionSupport implements Preparable,SessionAware{
	
	Map mapSession;
	
	private String curtailType;
	
	private String requestType;
	
	private String schedulenumber;
	
	private int id;
	
	private Schedule schedule;
	
	private String SEARCH_TERM;	

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	private String startdate;
	
	private String effectiveStartDate;
	private String targetstartdate;
	private String scheduletype;
	private int scheduletypeId;
	
	private Map<Integer,String> scheduleTypeMap;
	private Map<Integer,String> serviceTypeMap;
	private Map<Integer,String> brandTypeMap;
	private Map<Integer,String> depotMap;
	private Map<Integer,String> routeMap;
	
	private String targetamount;
	private String edittargetamt;
	private String token;

	  public String getToken() {
	    return token;
	  }

	  public void setToken(String token) {
	    this.token = token;
	  }
	
	public String getTargetamount() {
		return targetamount;
	}

	public String getTargetstartdate() {
		return targetstartdate;
	}

	public void setTargetstartdate(String targetstartdate) {
		this.targetstartdate = targetstartdate;
	}

	public void setTargetamount(String targetamount) {
		this.targetamount = targetamount;
	}

	public String getEdittargetamt() {
		return edittargetamt;
	}

	public void setEdittargetamt(String edittargetamt) {
		this.edittargetamt = edittargetamt;
	}
	private String orgtypeid;
	private String orgchartid;
	
	
	public String getOrgtypeid() {
		return orgtypeid;
	}


	public void setOrgtypeid(String orgtypeid) {
		this.orgtypeid = orgtypeid;
	}


	public String getOrgchartid() {
		return orgchartid;
	}


	public void setOrgchartid(String orgchartid) {
		this.orgchartid = orgchartid;
	}
	public Map<Integer, String> getScheduleTypeMap() {
		return scheduleTypeMap;
	}
	
	public void setScheduleTypeMap(Map<Integer, String> scheduleTypeMap) {
		this.scheduleTypeMap = scheduleTypeMap;
	}

	
	
	public Map<Integer, String> getServiceTypeMap() {
		return serviceTypeMap;
	}

	public void setServiceTypeMap(Map<Integer, String> serviceTypeMap) {
		this.serviceTypeMap = serviceTypeMap;
	}

	public Map<Integer, String> getBrandTypeMap() {
		return brandTypeMap;
	}

	public void setBrandTypeMap(Map<Integer, String> brandTypeMap) {
		this.brandTypeMap = brandTypeMap;
	}
	
	
	
	public Map<Integer, String> getDepotMap() {
		return depotMap;
	}

	public void setDepotMap(Map<Integer, String> depotMap) {
		this.depotMap = depotMap;
	}
	
	
	public Map<Integer, String> getRouteMap() {
		return routeMap;
	}

	public void setRouteMap(Map<Integer, String> routeMap) {
		this.routeMap = routeMap;
	}


	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSchedulenumber() {
		return schedulenumber;
	}

	public void setSchedulenumber(String schedulenumber) {
		this.schedulenumber = schedulenumber;
	}

	public Map getMapSession() {
		return mapSession;
	}

	public void setMapSession(Map mapSession) {
		this.mapSession = mapSession;
	}
	
	

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	

	public String getCurtailType() {
		return curtailType;
	}

	public void setCurtailType(String curtailType) {
		this.curtailType = curtailType;
	}
	
	
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
	

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}
	
	
	
	/**
	 * @return the scheduletype
	 */
	public String getScheduletype() {
		return scheduletype;
	}

	/**
	 * @param scheduletype the scheduletype to set
	 */
	public void setScheduletype(String scheduletype) {
		this.scheduletype = scheduletype;
	}
	
	

	/**
	 * @return the scheduletypeId
	 */
	public int getScheduletypeId() {
		return scheduletypeId;
	}

	/**
	 * @param scheduletypeId the scheduletypeId to set
	 */
	public void setScheduletypeId(int scheduletypeId) {
		this.scheduletypeId = scheduletypeId;
	}

	@SkipValidation
	public String execute(){
		return "success";
	}
	
	@SkipValidation
	public String getList() throws IOException{
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ScheduleDAO scheduledao = new ScheduleDAO();
		String[] cols = { "schedule_id","schedule_id", "scheduleNumber","","", "brand.brand_type_name", "depot.org_name",
		 "servicetype.serviceTypeName", "isTrunkSchedule", "scheduletype.scheduleName","conductor","driver","status"};
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
			if (col < 0 || col > 10)	
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			SEARCH_TERM = request.getParameter("sSearch");
			total = scheduledao.getTotalRecords(SEARCH_TERM);
			AMOUNT = amount;
			
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = scheduledao.getData(total, request,colName,dir);
			out.print(result);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	
	
	@SkipValidation
	public String getRationalizedList() throws IOException{
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ScheduleDAO scheduledao = new ScheduleDAO();
		String[] cols = { "schedule_id","schedule_id", "scheduleNumber", "brand.brand_type_name", "depot.org_name",
		 "servicetype.serviceTypeName", "isTrunkSchedule", "scheduletype.scheduleName","status"};
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
			if (col < 0 || col > 10)	
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			SEARCH_TERM = request.getParameter("sSearch");
			total = scheduledao.getTotalRecords(SEARCH_TERM);
			AMOUNT = amount;
			
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = scheduledao.getData1(total, request,colName,dir);
			out.print(result);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}	
	
	@SkipValidation
	public String createSchedule(){
		ScheduleDAO scheduledao = new ScheduleDAO();
		scheduleTypeMap = scheduledao.getScheduleType();
		serviceTypeMap = scheduledao.getServiceType();
		brandTypeMap = scheduledao.getBrand();
		depotMap = scheduledao.getDepots();
		routeMap = scheduledao.getRoutes();
		return "success";
	}
	
	@SkipValidation
	public String editSchedule(){
		Common common = new Common();
		ScheduleDAO scheduledao = new ScheduleDAO();
		/*ScheduleDAO scheduledao = new ScheduleDAO();*/
		scheduleTypeMap = scheduledao.getScheduleType();
		serviceTypeMap = scheduledao.getServiceType();
		brandTypeMap = scheduledao.getBrand();
		depotMap = scheduledao.getDepots();
//		routeMap = scheduledao.getRoutes();
		HttpServletRequest request=ServletActionContext.getRequest();
		String id = request.getParameter("sch");
		targetamount=scheduledao.getScheduleTargetAmount(Integer.parseInt(id));
		targetstartdate=common.getDateToPicker(scheduledao.getScheduleStartDate(Integer.parseInt(id)));
		schedule = scheduledao.getSchedule(Integer.parseInt(id));
		setScheduletypeId(schedule.getScheduletype().getSchedule_type_id());
		setSchedulenumber(schedule.getScheduleNumber());
		if(schedule.getEffectiveStartDate()!=null){
		setStartdate(common.getDateFromDateTime_(schedule.getEffectiveStartDate().toString()));
		schedule.setStartdate(common.getDateFromDateTime_(schedule.getEffectiveStartDate().toString()));
		}
		if(schedule.getEffectiveEndDate()!=null){
			schedule.setEndDate(common.getDateFromDateTime_(schedule.getEffectiveEndDate().toString()));
		}
		return "success";
	}
	
	@SkipValidation
	public String copySchedule(){
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		 setId(Integer.parseInt(request.getParameter("sch")));
		 schedule = scheduledao.getSchedule(id);
		 setScheduletype(schedule.getScheduletype().getScheduleName());
		return "success";
	}
	
	@SkipValidation
	public String curtailSchedule(){
		Common common = new Common();
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		 setId(Integer.parseInt(request.getParameter("sch")));
		 schedule = scheduledao.getSchedule(id);
		 schedule.setStartdate((common.getDateInDDMMYY(schedule.getEffectiveStartDate())));
		return "success";
	}
	

	public String saveCurtailSchedule(){
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		if(curtailType.equals("permanent")){
			scheduledao.permanentCurtail(id);
		}
		else{
			scheduledao.temporaryCurtail(id, startdate);
		}
		addActionMessage("Schedule ID "+id+" Curtailed Successfully");
		return "success";
	}

	public String saveCopySchedule(){
		int copySchId = 0;
		try{
		ScheduleDAO scheduledao = new ScheduleDAO();
		
		HttpServletRequest request=ServletActionContext.getRequest();
		 schedule = scheduledao.getSchedule(id);
		 Schedule copyschedule = new Schedule();
		 copyschedule.setScheduleNumber(getSchedulenumber());
		 copyschedule.setStatus("NEW");
		 copyschedule.setBrand(schedule.getBrand());
		 copyschedule.setEffectiveStartDate(schedule.getEffectiveStartDate());
		 copyschedule.setEffectiveEndDate(schedule.getEffectiveEndDate());
		 copyschedule.setDepot(schedule.getDepot());
		 copyschedule.setRouteId(schedule.getRouteId());
		 copyschedule.setScheduletype(schedule.getScheduletype());
		 copyschedule.setServicetype(schedule.getServicetype());
		 copyschedule.setIsTrunkSchedule(schedule.getIsTrunkSchedule());
		 copyschedule.setCurrentStatus("CASE WORKER");
		 HttpSession sess=request.getSession();
		 int userid = (Integer)request.getSession().getAttribute("userid");
		 copyschedule.setCreatedBy(userid);
		 copyschedule.setCreatedDate(new Date());
		 
		 copySchId = scheduledao.saveSchedule(copyschedule);
		 
		 
		 List<FormFour> formfourlist = scheduledao.getFormFourList(getId());
		 for(FormFour formfour : formfourlist){
			 FormFour ff = new FormFour();
			 FormFourDAO formdao = new FormFourDAO();
			 String formFourTypeName = formdao.getFormFourTypeName(formfour.getFormFourType().getId());
			 ff.setScheduleNumberName(getSchedulenumber()+"-"+formFourTypeName);
			 ff.setFormFourType(formfour.getFormFourType());
			 
			 Schedule schedule = new Schedule();
			 schedule.setSchedule_id(copySchId);
			 ff.setScheduleNumber(schedule);
			
			 ff.setCurrentStatus(formfour.getCurrentStatus());
			 ff.setNoofTrips(formfour.getNoofTrips());
			 ff.setStartTime(formfour.getStartTime());
			 ff.setFormFourRoute(formfour.getFormFourRoute());
			 ff.setAreaLimit(formfour.getAreaLimit());
			 ff.setTollZone(formfour.getTollZone());
			 ff.setTotalKm(formfour.getTotalKm());
			 ff.setTotalDeadKm(formfour.getTotalDeadKm());
			 ff.setActualKm(formfour.getActualKm());
			 ff.setTotalRunTime(formfour.getTotalRunTime());
			 ff.setTotalSteeringTime(formfour.getTotalSteeringTime());
			 ff.setTotalBreakTime(formfour.getTotalBreakTime());
			 ff.setSpreadOverHours(formfour.getSpreadOverHours());
			 ff.setOtHours(formfour.getOtHours());
			 ff.setSignOn(formfour.getSignOn());
			 ff.setSignOff(formfour.getSignOff());
			 ff.setEffectiveStartDate(formfour.getEffectiveStartDate());
			 ff.setEffectiveEndDate(formfour.getEffectiveEndDate());
			 ff.setStotalDeadKm(formfour.getStotalDeadKm());
			 ff.setSactualKm(formfour.getSactualKm());
			 ff.setStotalSteeringTime(formfour.getStotalSteeringTime());
			 ff.setSspreadOverHours(formfour.getSspreadOverHours());
			 ff.setSotHours(formfour.getSotHours());
			 ff.setCreatedBy(userid);
			 ff.setCreatedDate(new Date());
			int formfourid = scheduledao.saveFormFour(ff);
			
			List<ScheduleDetails> scheduleDetailList = scheduledao.getScheduleDetailsList(formfour.getId());
			
			for(ScheduleDetails details : scheduleDetailList){
				 ScheduleDetails ss = new ScheduleDetails();
				 
				 FormFour forms = new FormFour();
				 forms.setId(formfourid);
				 
				 ss.setFormFour(forms);
				 
				 Schedule sch = new Schedule();
				 sch.setSchedule_id(copySchId);
				 
				 ss.setScheduleNumber(sch);
				 ss.setNoofTrips(details.getNoofTrips());
				 ss.setListItemNumber(details.getListItemNumber());
				 ss.setTripNumber(details.getTripNumber());
				 ss.setCustomer(details.getCustomer());
				 ss.setTripType(details.getTripType());
				 ss.setStartPoint(details.getStartPoint());
				 ss.setEndPoint(details.getEndPoint());
				 ss.setRouteNumber(details.getRouteNumber());
				 ss.setDistance(details.getDistance());
				 ss.setStartTime(details.getStartTime());
				 ss.setEndTime(details.getEndTime());
				 ss.setRunningTime(details.getRunningTime());
				 ss.setBreakTime(details.getBreakTime());
				 ss.setBreakType(details.getBreakType());
				 ss.setBreakLocation(details.getBreakLocation());
				 ss.setCrewChange(details.getCrewChange());
				 ss.setNightHalt(details.getNightHalt());
				 ss.setShiftType(details.getShiftType());
				 ss.setIsDreadTrip(details.getIsDreadTrip());
				 ss.setCrewChangeLocation(details.getCrewChangeLocation());
				 ss.setNightHaltLocation(details.getNightHaltLocation());
				 ss.setBreakLocation(details.getBreakLocation());
				 ss.setCreatedBy(userid);
				 ss.setCreatedDate(new Date());
				 scheduledao.saveScheduleDetails(ss);
			 }
			
		 }
		 
		 
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 addActionMessage("Schedule Id "+id+" Copied Successfully");
		return "success";
	}
	
	@SkipValidation
	public String viewRelatedFormFour(){
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		if(request.getParameter("sch") != null){
			id = Integer.parseInt(request.getParameter("sch"));
			
		}
		else{
			id = Integer.parseInt(request.getSession().getAttribute("sch").toString());
		}
		 
		schedule = scheduledao.getSchedule(id);
		mapSession.put("sch", id);
		return "success";
	}
	
	
	public String saveSchedule(){
		try{
		Common common = new Common();
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int userid = (Integer)request.getSession().getAttribute("userid");
		String targetamount= request.getParameter("targetamount");
		schedule.setCreatedBy(userid);
		schedule.setCreatedDate(new Date());
		schedule.setCurrentStatus("CASE WORKER");
		schedule.setScheduleNumber(schedule.getScheduleNumber().replace("'", "\'"));
		schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
		if(targetamount==""){
			schedule.setTargetRevenueStatus("0");
		}else{
		schedule.setTargetRevenueStatus(targetamount);
		}
		if(schedule.getEndDate().length()>0){
			schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
			}
		
		int id = scheduledao.saveSchedule(schedule);
		scheduledao.insertScheduleTargetAmount(id,targetamount,schedule.getStartdate());
		addActionMessage("Schedule Id "+id+" Created Successfully");
		}
		catch(Exception e){
			System.out.println("eee ---"+e);
		}
		return "success";
	}
	
	public String updateSchedule() throws ParseException{
		Common common = new Common();
		int id = 0;
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int userid = (Integer)request.getSession().getAttribute("userid");
		String ff = request.getParameter("ff");
		/*if(schedule.getCurrentStatus().equalsIgnoreCase("APPROVED")){*/
		System.out.println("conductor==="+schedule.getConductor());
		
		
		
		schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
		if(schedule.getEndDate().length()>0){
			schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
			}
		
		if(common.compareOnlyDate(common.getDate(getStartdate())) == 0 || common.compareOnlyDate(common.getDate(getStartdate())) == 1 || common.getDate(getStartdate()).compareTo(common.getDate(schedule.getEndDate())) == 0){
			System.out.println("updateSchedule");
			schedule.setUpdatedBy(userid);
			schedule.setUpdatedDate(new Date());
			schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
			if(schedule.getEndDate().length()>0){
				schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
				}
			id = scheduledao.updateSchedule(schedule);
			
		}
		else{
			System.out.println("saveSchedule");
			schedule.setUpdatedDate(new Date());
			schedule.setUpdatedBy(userid);
			scheduledao.RationaliseSchedule(schedule,userid);
			int oldschid = schedule.getSchedule_id();
			int oldschedultypeId = schedule.getScheduletype().getSchedule_type_id();
			schedule.setCreatedBy(userid);
			schedule.setCreatedDate(new Date());
			schedule.setCurrentStatus("CASE WORKER");
			
			 id = scheduledao.saveSchedule(schedule);
			 
			 if(ff.equals("y")){
				 scheduledao.saveFormFourInNewschedule(schedule, id,oldschid);
				 }
		}
		
		if(getEdittargetamt().equals("getEdittargetamt"))
		{
			scheduledao.UpdateScheduleTargetAmountt(getTargetamount(), schedule.getSchedule_id(),getTargetstartdate());
			scheduledao.insertScheduleTargetAmount(id,getTargetamount(),getTargetstartdate());
			
		}
		
			/* if(scheduletypeId == oldschedultypeId){
				 int cnt = scheduledao.updateFormFourRelatedSchedule(id, oldschid);
			 }*/
			 
		/*}
		else{
		
		schedule.setUpdatedBy(userid);
		schedule.setUpdatedDate(new Date());
		schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
		if(schedule.getEndDate().length()>0){
			schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
			}
		id = scheduledao.updateSchedule(schedule);
		}*/
		addActionMessage("Schedule ID "+id+" Updated Successfully");
		return "success";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		/*ScheduleDAO scheduledao = new ScheduleDAO();
		scheduleTypeMap = scheduledao.getScheduleType();
		serviceTypeMap = scheduledao.getServiceType();
		brandTypeMap = scheduledao.getBrand();
		depotMap = scheduledao.getDepots();
		routeMap = scheduledao.getRoutes();*/
		HttpServletRequest request=ServletActionContext.getRequest();
		
		int usrsessionid=(Integer)request.getSession().getAttribute("userid"); 
		
		orgtypeid=(String)request.getSession().getAttribute("orgtypeid"); 
	
		orgchartid=(String)request.getSession().getAttribute("orgchartid"); 
		
	}
	
	public void validate(){
		ScheduleDAO scheduledao = new ScheduleDAO();
		scheduleTypeMap = scheduledao.getScheduleType();
		serviceTypeMap = scheduledao.getServiceType();
		brandTypeMap = scheduledao.getBrand();
		depotMap = scheduledao.getDepots();
//		routeMap = scheduledao.getRoutes();
		CommonValidation commvalidate = new CommonValidation();
		Common common = new Common();
		
		if(requestType.equals("copy")){
			
			if(getSchedulenumber().equals(null) || getSchedulenumber().trim().equals("")){
				addActionError("Schedule Number is mandatory");
			}	
			
			int cnt = scheduledao.checkSchedule(getSchedulenumber().replace(" ", ""));
			
			if(cnt==1){
				addActionError("Schedule Number Already exists");
			}
			
			}
		else if(requestType.equals("curtail")){
			if(curtailType.equals("temporary")){
				if(startdate.equals(null) || startdate.trim().equals("")){
					addActionError("Effective End Date is Mandatory For Temporary Curtailment");
				}
			if(common.compareDates(effectiveStartDate, startdate) == 1){
				addActionError("Effective End Date cannot be before Effective Start date");
			}
			}
		}
		else{
		
		if(!requestType.equals("update")){
			schedule.setScheduleNumber(schedule.getScheduleNumber().replace("'", "\'"));
		int cnt = scheduledao.checkSchedule(schedule.getScheduleNumber());
		
		if(cnt==1){
			addActionError("Schedule Number Already exists");
		}
		}
		else{
			schedule.setScheduleNumber(schedule.getScheduleNumber().replace("'", "\'"));
			int cnt = scheduledao.checkSchedule(schedule.getScheduleNumber());
			
			if(getSchedulenumber().equals(schedule.getScheduleNumber())){
				if(cnt>1){
					addActionError("Schedule Number Already exists");
				}
			}
			else{
				if(cnt>0){
					addActionError("Schedule Number Already exists");
				}
			}
			
			
			if(cnt == 1 && getSchedulenumber().equals(schedule.getScheduleNumber())){
				
			}
		}
		if(schedule.getScheduleNumber().equals(null) || schedule.getScheduleNumber().trim().equals("")){
			addActionError("Schedule Number is mandatory");
		}	
		if(schedule.getScheduletype().getSchedule_type_id()==0){
			addActionError("Schedule Type is mandatory");
		}
		if(schedule.getServicetype().getService_type_id()==0){
			addActionError("Service Type is mandatory");
		}
		if(schedule.getBrand().getBrand_type_id()==0){
			addActionError("Brand Type is mandatory");
		}
		if(schedule.getDepot().getOrg_chart_id()==0){
			addActionError("Depot Code is mandatory");
		}
		if(common.compareDates(schedule.getStartdate(),schedule.getEndDate()) == 1){
			addActionError("Effective End Date cannot be before Effective Start date");
		}
		/*if(schedule.getRouteno().getRoute_id()==0){
			addActionError("ROute Number is mandatory");
		}*/
		if(commvalidate.isEmpty(schedule.getStartdate())){
			addActionError("Effective Start Date is mandatory");
		}
		if(getEdittargetamt().equals("getEdittargetamt"))
		{
		if(commvalidate.isEmpty(getTargetstartdate())){
			addActionError("Target Start Date is mandatory");
		}
		
		
		 if (!getTargetamount().trim().matches("^[0-9 ]*$")) {
			 addActionError("Target Amount Should be Number");
				
				}
		 
		 if(common.compareDates(getTargetstartdate(),schedule.getEndDate()) == 1){
				addActionError("Target Start Date should be lesser than Effective End Date of Schedule");
			}
		 /*if(common.compareDates(getTargetstartdate(),schedule.getStartdate()) == 1){
				addActionError("Target Start date cannot be  Before  Effective End date");
			}*/
		 /*if (getTargetamount().trim().matches("[a-zA-Z!@#$%^&?`~*,._+=]*$")) {
			 addActionError("Target Amount Should be Number");
				
				}*/
		}
		if(getEdittargetamt().equals("bcd"))
		{
			 if (!getTargetamount().trim().matches("^[0-9 ]*$")) {
				 addActionError("Target Amount Should be Number");
					
					}
			/* if (getTargetamount().trim().matches("[a-zA-Z!@#$%^&?`~*,._+=]*$")) {
				 addActionError("Target Amount Should be Number");
					
					}*/
			
			
		}
		if(targetamount!=null && targetamount.length()>0 ){
			if(Pattern.matches("[0-9]+", targetamount.trim()))
			{ 
			} 
			else{
				addFieldError("targetamount","Target Amount Should be Number ");
			}
			
		}
		}
	}
	
	
	@SkipValidation
	public String getRationalizedFormIVList() throws IOException{
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ScheduleDAO scheduledao = new ScheduleDAO();
		String[] cols = { "id","id","formFourType.formFourTypeName", "scheduleNumber.schedule_id", "scheduleNumberName", "NoofTrips","formFourRoute.route_id", "startTime", "effectiveStartDate", "effectiveEndDate","currentStatus","remarks"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;	
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		HttpSession sess=request.getSession();
		id = (Integer) sess.getAttribute("sch");
		
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
			if (col < 0 || col > 11)	
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			SEARCH_TERM = request.getParameter("sSearch");
			total = scheduledao.getFormFourTotalRecords(id,SEARCH_TERM);
			AMOUNT = amount;
			
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = scheduledao.getRationalizedFormFourData(total, request, colName, dir,id);
			out.print(result);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SkipValidation
	public String getPartialFormIVList() throws IOException{
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ScheduleDAO scheduledao = new ScheduleDAO();
		String[] cols = { "id","id","formFourType.formFourTypeName", "scheduleNumber.schedule_id", "scheduleNumberName", "NoofTrips","formFourRoute.route_id", "startTime", "effectiveStartDate", "effectiveEndDate","currentStatus","remarks"};
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
			if (col < 0 || col > 11)	
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			SEARCH_TERM = request.getParameter("sSearch");
			total = scheduledao.getFormFourTotalRecords(id,SEARCH_TERM);
			AMOUNT = amount;
			
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = scheduledao.getPartialFormFourData(total, request, colName, dir);
			out.print(result);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@SkipValidation
	public String getFormFourList() throws IOException{
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpSession sess=request.getSession();
		id = (Integer) sess.getAttribute("sch");
		String[] cols = { "id","id","formFourType.formFourTypeName", "scheduleNumber.schedule_id", "scheduleNumberName", "NoofTrips","formFourRoute.route_id", "startTime", "effectiveStartDate", "effectiveEndDate","currentStatus","remarks"};
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
			if (col < 0 || col > 11)	
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			SEARCH_TERM = request.getParameter("sSearch");
			total = scheduledao.getFormFourTotalRecords(id,SEARCH_TERM);
			AMOUNT = amount;
			
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = scheduledao.getFormFourData(total, request, colName, dir, id);
			out.print(result);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void setSession(Map mapSession) {
		// TODO Auto-generated method stub
		this.mapSession=mapSession;
	}
	@SkipValidation
	public String getBrandType() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		ScheduleDAO scheduledao = new ScheduleDAO();
		String serviceId = request.getParameter("serviceId");
		String data = scheduledao.getBrandtype(serviceId);
		out.println(data);
		return null;
	}
	//update target Amount//rajesh
	public String updateTargetAmountSchedule() throws ParseException{
		Common common = new Common();
		int id = 0;
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int userid = (Integer)request.getSession().getAttribute("userid");
		String ff = request.getParameter("ff");
		
		/*if(schedule.getCurrentStatus().equalsIgnoreCase("APPROVED")){*/
//		schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
//		if(schedule.getEndDate().length()>0){
//			schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
//			}
//		
//		if(common.compareOnlyDate(common.getDate(getStartdate())) == 0 || common.compareOnlyDate(common.getDate(getStartdate())) == 1 || common.getDate(getStartdate()).compareTo(common.getDate(schedule.getEndDate())) == 0){
//			schedule.setUpdatedBy(userid);
//			schedule.setUpdatedDate(new Date());
//			schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
//			if(schedule.getEndDate().length()>0){
//				schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
//				}
			//id = scheduledao.updateSchedule(schedule);
			
//		}
//		else{
//			schedule.setUpdatedDate(new Date());
//			schedule.setUpdatedBy(userid);
//			scheduledao.RationaliseSchedule(schedule,userid);
//			int oldschid = schedule.getSchedule_id();
//			int oldschedultypeId = schedule.getScheduletype().getSchedule_type_id();
//			schedule.setCreatedBy(userid);
//			schedule.setCreatedDate(new Date());
//			schedule.setCurrentStatus("CASE WORKER");
//			 id = scheduledao.saveSchedule(schedule);
//			 
//			 if(ff.equals("y")){
//				 scheduledao.saveFormFourInNewschedule(schedule, id,oldschid);
//				 }
//		}
		
		if(getEdittargetamt().equals("getEdittargetamt"))
		{
			scheduledao.UpdateScheduleTargetAmountt(getTargetamount(), schedule.getSchedule_id(),getTargetstartdate());
			scheduledao.insertScheduleTargetAmount(schedule.getSchedule_id(),getTargetamount(),getTargetstartdate());
			
		}
		
			/* if(scheduletypeId == oldschedultypeId){
				 int cnt = scheduledao.updateFormFourRelatedSchedule(id, oldschid);
			 }*/
			 
		/*}
		else{
		
		schedule.setUpdatedBy(userid);
		schedule.setUpdatedDate(new Date());
		schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
		if(schedule.getEndDate().length()>0){
			schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
			}
		id = scheduledao.updateSchedule(schedule);
		}*/
		addActionMessage("Schedule ID "+schedule.getSchedule_id()+" Updated Successfully");
		return "success";
	}
	//end
	
	
	
	public String updateTargetAmountScheduleChanges() throws ParseException{
		Common common = new Common();
		int id = 0;
		ScheduleDAO scheduledao = new ScheduleDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int userid = (Integer)request.getSession().getAttribute("userid");
		String targetamountedit = request.getParameter("targetamount");
		String ff = request.getParameter("ff");
		
		/*if(schedule.getCurrentStatus().equalsIgnoreCase("APPROVED")){*/
//		schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
//		if(schedule.getEndDate().length()>0){
//			schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
//			}
//		
//		if(common.compareOnlyDate(common.getDate(getStartdate())) == 0 || common.compareOnlyDate(common.getDate(getStartdate())) == 1 || common.getDate(getStartdate()).compareTo(common.getDate(schedule.getEndDate())) == 0){
//			schedule.setUpdatedBy(userid);
//			schedule.setUpdatedDate(new Date());
//			schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
//			if(schedule.getEndDate().length()>0){
//				schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
//				}
			//id = scheduledao.updateSchedule(schedule);
			
//		}
//		else{
//			schedule.setUpdatedDate(new Date());
//			schedule.setUpdatedBy(userid);
//			scheduledao.RationaliseSchedule(schedule,userid);
//			int oldschid = schedule.getSchedule_id();
//			int oldschedultypeId = schedule.getScheduletype().getSchedule_type_id();
//			schedule.setCreatedBy(userid);
//			schedule.setCreatedDate(new Date());
//			schedule.setCurrentStatus("CASE WORKER");
//			 id = scheduledao.saveSchedule(schedule);
//			 
//			 if(ff.equals("y")){
//				 scheduledao.saveFormFourInNewschedule(schedule, id,oldschid);
//				 }
//		}
//		if(!getEdittargetamt().equals("getEdittargetamt")){
//			scheduledao.UpdateScheduleTargetAmounttchanges(getTargetamount(), schedule.getSchedule_id(),getTargetstartdate());
//			scheduledao.insertScheduleTargetAmountchanges(schedule.getSchedule_id(),getTargetamount(),getTargetstartdate());
//		}
//		
		if(getEdittargetamt().equals("getEdittargetamt"))
		{
			scheduledao.UpdateScheduleTargetAmounttchanges(getTargetamount(), schedule.getSchedule_id(),getTargetstartdate());
			scheduledao.insertScheduleTargetAmountchanges(schedule.getSchedule_id(),getTargetamount(),getTargetstartdate());
			scheduledao.updateTargetAmountBasedOnSchedule(targetamountedit,schedule.getSchedule_id());
			
		}
		
			/* if(scheduletypeId == oldschedultypeId){
				 int cnt = scheduledao.updateFormFourRelatedSchedule(id, oldschid);
			 }*/
			 
		/*}
		else{
		
		schedule.setUpdatedBy(userid);
		schedule.setUpdatedDate(new Date());
		schedule.setEffectiveStartDate(common.getDate(schedule.getStartdate()));
		if(schedule.getEndDate().length()>0){
			schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
			}
		id = scheduledao.updateSchedule(schedule);
		}*/
		addActionMessage("Schedule ID "+schedule.getSchedule_id()+" Updated Successfully");
		return "success";
	}
	
	private Map<Integer, String> brandlist;
	
	public Map<Integer, String> getBrandlist() {
		return brandlist;
	}

	public void setBrandlist(Map<Integer, String> brandlist) {
		this.brandlist = brandlist;
	}

	
	
	}
