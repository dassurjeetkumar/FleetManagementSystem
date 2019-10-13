package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.transport.dao.FormFourDAO;
import com.trimax.its.transport.dao.ScheduleDAO;
import com.trimax.its.transport.dao.WeeklyChartDao;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.RouteNoUtil;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.SearchCriteriaJson;

public class FormFourAction extends ActionSupport implements Preparable,SessionAware {
	
	
	private Map<Integer,String> scheduleMap;
	
	private Map<Integer,String> formFourMap;
	
	private Map<Integer,String> routeMap;
	
	private FormFour formfour = new FormFour();
	
	private Schedule schedule;
	
	private String formfourid;
	
	private String requestType;
	
	private String startDate;
	private String endDate;
	
	private String schid;
	private String formfourtype;
	private String scheduleEffStart;
	private String serviceid;
	
	private String searchRouteId;
	private String searchRouteNo;
	

	public String getServiceid() {
		return serviceid;
	}



	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	Map mapSession;

	


	public Map<Integer, String> getScheduleMap() {
		return scheduleMap;
	}



	public void setScheduleMap(Map<Integer, String> scheduleMap) {
		this.scheduleMap = scheduleMap;
	}



	public Map<Integer, String> getFormFourMap() {
		return formFourMap;
	}



	public void setFormFourMap(Map<Integer, String> formFourMap) {
		this.formFourMap = formFourMap;
	}



	public Map<Integer, String> getRouteMap() {
		return routeMap;
	}



	public void setRouteMap(Map<Integer, String> routeMap) {
		this.routeMap = routeMap;
	}

	

	public FormFour getFormfour() {
		return formfour;
	}



	public void setFormfour(FormFour formfour) {
		this.formfour = formfour;
	}
	
	

	/**
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}



	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	
	


	/**
	 * @return the formfourid
	 */
	public String getFormfourid() {
		return formfourid;
	}



	/**
	 * @param formfourid the formfourid to set
	 */
	public void setFormfourid(String formfourid) {
		this.formfourid = formfourid;
	}

	
	

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}



	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	
	
	

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}



	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	
	
	

	/**
	 * @return the schid
	 */
	public String getSchid() {
		return schid;
	}



	/**
	 * @param schid the schid to set
	 */
	public void setSchid(String schid) {
		this.schid = schid;
	}

	
	
	

	/**
	 * @return the formfourtype
	 */
	public String getFormfourtype() {
		return formfourtype;
	}



	/**
	 * @param formfourtype the formfourtype to set
	 */
	public void setFormfourtype(String formfourtype) {
		this.formfourtype = formfourtype;
	}
	
	
	


	/**
	 * @return the scheduleEffStart
	 */
	public String getScheduleEffStart() {
		return scheduleEffStart;
	}



	/**
	 * @param scheduleEffStart the scheduleEffStart to set
	 */
	public void setScheduleEffStart(String scheduleEffStart) {
		this.scheduleEffStart = scheduleEffStart;
	}

	

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}



	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	

	public String getSearchRouteId() {
		return searchRouteId;
	}



	public void setSearchRouteId(String searchRouteId) {
		this.searchRouteId = searchRouteId;
	}



	public String getSearchRouteNo() {
		return searchRouteNo;
	}



	public void setSearchRouteNo(String searchRouteNo) {
		this.searchRouteNo = searchRouteNo;
	}

	@SkipValidation
	public String execute(){
		
		FormFourDAO formdao = new FormFourDAO();
		scheduleMap = formdao.getScheduleNumber();
		formFourMap = formdao.getFormFourName();
		//routeMap = formdao.getRoute();
		return "success";
	}
	
	public String getRouteNoAjax(){
//		waybilllist=daoObject.waybilllist();
		FormFourDAO formdao = new FormFourDAO();
			HttpServletRequest request = ServletActionContext.getRequest();
			String routeNoStartWith = request.getParameter("id");
			List<RouteNoUtil> list = formdao.getRouteNoList(routeNoStartWith);
			List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
			for (RouteNoUtil waybill : list) {

				SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
				searchCriteria.setValue(waybill.getRouteNo() + " "+ waybill.getRouteId());
				searchCriteria.setAlias1(Integer.parseInt(waybill.getRouteId()));
				searchCriteria.setSearchCriteriaName(waybill.getRouteNo());
				list1.add(searchCriteria);
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(new JSONArray(list1));

			return null;
	}
	
	public String checkValidRoute(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String routeNo = request.getParameter("routeNo");
		String routeId = request.getParameter("routeId");
		FormFourDAO daoObject = new FormFourDAO();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(daoObject.checkValidRouteNO(routeId,routeNo));

		return null;
	}
	

	
	
	public String saveFormFour(){
		

		Common common = new Common();
		FormFourDAO formdao = new FormFourDAO();
	
		Session session2 = null;
		org.hibernate.Transaction transaction= null;
	
		String scheduleNumber = formdao.getScheduleNumber(formfour.getScheduleNumber().getSchedule_id());
		String formFourTypeName = formdao.getFormFourTypeName(formfour.getFormFourType().getId());
		
		formfour.setScheduleNumberName(scheduleNumber+"-"+formFourTypeName);
		formfour.setStartTime(common.getHours(formfour.getStarttimeString()));
		formfour.setEffectiveStartDate(common.getDate(formfour.getStartDate()));
		Route_Trans routeUtil = new Route_Trans();
		routeUtil.setRoute_id(Integer.parseInt(this.getSearchRouteId()));
		routeUtil.setRouteNumber(this.getSearchRouteNo());
		formfour.setFormFourRoute(routeUtil);
		if(formfour.getEndDate().length()>0){
		formfour.setEffectiveEndDate(common.getDate(formfour.getEndDate()));
		}
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int userid = (Integer)request.getSession().getAttribute("userid");
		formfour.setCreatedBy(userid);
		formfour.setCreatedDate(new Date());
		formfour.setStarttimeString(formfour.getStartTime().toString().substring(11, 16));
		String traffic_order = formfour.getTraffic_order_no();
		System.out.println("in traffic -----"+traffic_order);
//		String order_no = request.getParameter("traffic_order_no");
//		System.out.println("save form four traffic"+order_no);
//		String record_date=request.getParameter("recordDate");
		formfour.setTraffic_order_no(traffic_order);
//		formfour.setRecordDate(record_date);
		System.out.println("aftr setr traffic order ");
//		 session2 = HibernateUtil.getSession("hibernate.cfg.xml");
//		 transaction = session2.beginTransaction();
//		 String sql="";
//		
//		          sql = "insert into form_four_traffic_order (form_four_Id,traffic_order_no,record_date,created_date,created_by) values ('"+etmno+"',"+order_no+","+record_date+",'"+etmissue+"','"+etmpickup+"',"+user+")";
//			
//			 
//		 System.out.println("Sql=="+sql);
//	 Query query = session2.createSQLQuery(sql);
//query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		
		mapSession.put("formfourID",formfour);
//		mapSession.put("traffic_Order", order_no);
//		mapSession.put("date", record_date);
		/*int id = formdao.saveFormFour(formfour);
		formfour.setId(id);
		
		setFormfour(formfour);
		mapSession.put("formfourID",id);
		request.setAttribute("formfourID",id);
		addActionMessage("Form Four Created Successfully");*/
		
		return "success";
	}

	@SkipValidation
	public String editFormFour(){
		
	
		Common common = new Common();
		FormFourDAO formdao = new FormFourDAO();
		scheduleMap = formdao.getScheduleNumber();
		formFourMap = formdao.getFormFourName();
		routeMap = formdao.getRoute();
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String formfourid = request.getParameter("formfour");
		//ScheduleDAO scheduledao = new ScheduleDAO();
		try{
		//schedule = scheduledao.getSchedule(formfour.getScheduleNumber().getSchedule_id());
		setFormfourid(formfourid);
		formfour = formdao.getFormFour(Integer.parseInt(formfourid));
		formfour.setStartDate(common.getDateFromDateTime_(formfour.getEffectiveStartDate().toString()));
		setStartDate(formfour.getStartDate());
		if(formfour.getEffectiveEndDate()!=null ){
		formfour.setEndDate(common.getDateFromDateTime_(formfour.getEffectiveEndDate().toString()));
		
		setEndDate(formfour.getEndDate());
		}
		formfour.setTraffic_order_no(formdao.getTrafficOrderNo(formfourid));
		formfour.setStarttimeString(formfour.getStartTime().toString().substring(0,5));
		setSchid(String.valueOf(formfour.getScheduleNumber().getSchedule_id()));
		setServiceid(String.valueOf(formfour.getScheduleNumber().getServicetype().getService_type_id()));
		setFormfourtype(String.valueOf(formfour.getFormFourType().getId()));
		this.setSearchRouteId(String.valueOf(formfour.getFormFourRoute().getRoute_id()));
		this.setSearchRouteNo(formfour.getFormFourRoute().getRouteNumber()+"-"+formfour.getFormFourRoute().getRouteDirection());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
		
	}
		return "success";
	}
	
	public String saveEditedFormFour() throws ParseException{
		
		System.out.println("in saveEditedFormFour");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		int userid = (Integer)request.getSession().getAttribute("userid");
		Common common = new Common();
		FormFourDAO formdao = new FormFourDAO();
		try{
		String scheduleNumber = formdao.getScheduleNumber(formfour.getScheduleNumber().getSchedule_id());
		int formFourID = Integer.parseInt(request.getAttribute("formfourid").toString()); 
		String formFourTypeName = formdao.getFormFourTypeName(formfour.getFormFourType().getId());
		formfour.setStartTime(common.getHours(formfour.getStarttimeString()));
		formfour.setEffectiveStartDate(common.getDate(formfour.getStartDate()));
		if(formfour.getEndDate().length()>0){
		formfour.setEffectiveEndDate(common.getDate(formfour.getEndDate()));
		//check that date is greater than current date
		int chkStats=common.compareOnlyDate(common.getDate(formfour.getEndDate()));
		int chk=0;
		if(chkStats == -1){
			//update Form Four
			chk =1;
			formdao.UpdatedFormFourEndDate(formFourID,common.getDateFromPicker(formfour.getEndDate()),
					common.getDateFromPicker(formfour.getStartDate()),chk);
			addActionMessage("Schedule id "+formfour.getScheduleNumber().getSchedule_id() +" Edited Successfully");
			return "scheduleList";
		}
		else{
			//update form four
			chk=2;
			formdao.UpdatedFormFourEndDate(formFourID,common.getDateFromPicker(formfour.getEndDate()),
					common.getDateFromPicker(formfour.getStartDate()),chk);
			addActionMessage("Schedule id "+formfour.getScheduleNumber().getSchedule_id() +" Edited Successfully");
			return "scheduleList";
		}
		
		}
	/*	
		String tr = formfour.getTraffic_order_no();
		String rec=formfour.getRecordDate();
		System.out.println("save trip ===== "+tr+"=== "+rec);*/
//		System.out.println("in save=="+formfour.getTraffic_order_no());
		String traffic_order = formfour.getTraffic_order_no();
//		String record_date=request.getParameter("recordDate");
		
		formfour.setId(Integer.parseInt(formfourid));
		formfour.setUpdatedBy(userid);
		formfour.setUpdatedDate(new Date());
		formfour.setScheduleNumberName(scheduleNumber+"-"+formFourTypeName);
		Route_Trans routeUtil = new Route_Trans();
		routeUtil.setRoute_id(Integer.parseInt(this.getSearchRouteId()));
		routeUtil.setRouteNumber(this.getSearchRouteNo());
		formfour.setFormFourRoute(routeUtil);
		formfour.setTraffic_order_no(traffic_order);
//		formfour.setRecordDate(record_date);
		FormFour formfour1 = formdao.updateFormFour(formfour);
		
		//new code start
		
		//if(common.compareOnlyDate(common.getDate(getStartDate())) == 0 || common.compareOnlyDate(common.getDate(getStartDate())) == 1 || common.getDate(getStartDate()).compareTo(common.getDate(formfour.getEndDate())) == 0){
		
			request.setAttribute("formfour", formfour1);
		//}
		/*else{
		formdao.UpdatedOldFormFour(formfour1);
		int newformfourid=formdao.SaveNewFormFour(formfour1,formfour);
		System.out.println("newformfourid---->"+newformfourid);
		formdao.saveFormFourInNewschedule(Integer.parseInt(formfourid),newformfourid);
		FormFour formfour2=formdao.getFormFourById(newformfourid);
		
		//new code end
		
		request.setAttribute("formfour", formfour2);
		}*/
		//int id = formdao.updateFormFour(formfour);
		//System.out.println("Id------> "+id);
		//addActionMessage("Form Four "+id+" Edited Successfully");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}

	public void validate(){
		
		FormFourDAO formdao = new FormFourDAO();
		scheduleMap = formdao.getScheduleNumber();
		formFourMap = formdao.getFormFourName();
		routeMap = formdao.getRoute();
		HttpServletRequest req=ServletActionContext.getRequest();
//		System.out.println("traffic order here is==="+req.getParameter("traffic_order_no"));
//		System.out.println("date is ==="+req.getParameter("recordDate"));		
		String traffic_order=formfour.getTraffic_order_no();
//		System.out.println("validate traffic_order------"+traffic_order);
//		String record_date=req.getParameter("recordDate");
		String inDate="";
		String enddate="";
		setFormfour(formfour);
		Common common = new Common();
		FormFourDAO dao = new FormFourDAO();
		CommonValidation commvalidate = new CommonValidation();
		String dateString ="";
		String enddateString ="";
		if(!commvalidate.isEmpty(formfour.getStartDate())){
			dateString = common.getDateFromPicker(formfour.getStartDate()); 
//			System.out.println("DateString ---> "+dateString+" Start ------> "+formfour.getStartDate());
			//set scheduleEffStart value rajesh
			setScheduleEffStart(formfour.getStartDate());
		}
		if(!commvalidate.isEmpty(formfour.getEndDate())){
			enddateString = common.getDateFromPicker(formfour.getEndDate());
		}
		formfour.setEffectiveStartDate(common.getDate(formfour.getStartDate()));
		
		
		if(formfour.getScheduleNumber().getSchedule_id()==0){
			addActionError("Schedule Number is Mandatory");
		}
		if(formfour.getFormFourType().getId()==0){
			addActionError("Form Four Name is Mandatory");
		}
		if(formfour.getNoofTrips()==0){
			addActionError("Number Of Trips is Mandatory");
		}
		if(commvalidate.isEmpty(formfour.getStarttimeString())){
			addActionError("Start Time is Mandatory");
		}
		if(commvalidate.isEmpty(formfour.getStartDate())){
			addActionError("Effective Start Date is Mandatory");
		}
		String routeId  = this.getSearchRouteId();
		if(getSearchRouteId()== null || routeId.equals("0") || routeId.equals("")){
			addActionError("Route Number is Mandatory");
		}
		
		else if(dao.checkRouteExit(Integer.parseInt(routeId),Integer.parseInt(getServiceid().trim()))<=0)
		{
			addActionError("Fare Chart does not exist for selected route");
			
		}
//		System.out.println("traffic_order###### "+traffic_order);
		if(traffic_order ==null || traffic_order.equals("")){
			addActionError("Traffic Order No. is Mandatory");
		}
//		if(commvalidate.isEmpty(record_date)){
//			addActionError("Date is Mandatory");
//		}
		if(dao.checkStartDate(formfour.getStartDate(),formfour.getScheduleNumber().getSchedule_id() ) == -1){
			addActionError("Effective Start Date is less than Schedule Effective Start Date");
		}
		if(!formfour.getEndDate().equals("") || formfour.getEndDate()!=null){
		if(dao.checkEndDate(formfour.getEndDate(),formfour.getScheduleNumber().getSchedule_id() ) == 1){
			addActionError("Effective End Date is greater than Schedule effective End Date");
		}
		}
		//check route exit or not //rajesh
		
	
		
		if(!formfour.getEndDate().equals("") || formfour.getEndDate()!=null){
		if(common.compareDates(formfour.getStartDate(), formfour.getEndDate()) == 1){
			addActionError("Effective End Date cannot be before Effective Start date");
		}
		}
		if(requestType.equals("update")){
		inDate = common.getDateFromPicker(startDate);
		if(endDate.length()!=0){
		enddate = common.getDateFromPicker(endDate);
		}
		}
		/*System.out.println("requestType.equals(\"update\") --->" +requestType.equals("update")+ "  "+(dao.checkFormFour(formfour,dateString)!=0)+"  "+(inDate.equals(dateString))+"  "+(formfour.getFormFourType().getId()!=Integer.parseInt(formfourtype)));*/
		int validate=0;
		if(requestType.equals("update") && (dao.checkFormFour(formfour,dateString)!=0 || dao.checkFormFour(formfour,enddateString)!=0)){
			if(!inDate.equals(dateString)){
			addActionError("Form Four Already Exists");
			validate=1;
			}
		}
	 
		else if(requestType.equals("update") && (dao.checkFormFour(formfour,dateString)==1 || dao.checkFormFour(formfour,enddateString)==1) && (!inDate.equals(dateString) || !enddate.equals(enddateString))){
			addActionError("Form Four Already Exists");
			validate=1;
		}
		else if(!requestType.equals("update") && (dao.checkFormFour(formfour,dateString)!=0 || dao.checkFormFour(formfour,enddateString)!=0)){
			addActionError("Form Four Already Exists");
			validate=1;
		}
		else if(requestType.equals("update") && (dao.checkFormFour(formfour,dateString)!=0 || dao.checkFormFour(formfour,enddateString)!=0) && (inDate.equals(dateString) || enddate.equals(enddateString)) && formfour.getFormFourType().getId()!=Integer.parseInt(formfourtype)){
			addActionError("Form Four Already Exists");
			validate=1;
		}
		/*if(requestType.equals("update") && common.compareOnlyDate(common.getDate(formfour.getStartDate())) == -1)
		{
			addActionError("Effective Start Date should be current or future date");
			
		}*/
//		System.out.println("in form four action validate");
		if(validate==0){
		if(requestType.equals("update") && (dao.checkFormFour(formfour,dateString)!=0 || dao.checkFormFour(formfour,enddateString)!=0) && (inDate.equals(dateString) || enddate.equals(enddateString)) && formfour.getFormFourType().getId()!=Integer.parseInt(formfourtype)){
			addActionError("Form Four Already Exists");
		}
		}
		
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		Common common = new Common();
		HttpServletRequest request=ServletActionContext.getRequest();
		String formtype = request.getParameter("formtype");
		String schid = request.getParameter("sch");
		ScheduleDAO scheduledao = new ScheduleDAO();
		if(schid != null){
			if(!schid.equals("undefined")){
		schedule = scheduledao.getSchedule(Integer.parseInt(schid));
			}
			if(schedule !=null){
			if(schedule.getEffectiveStartDate() != null){
				setScheduleEffStart(common.getDateFromDateTime_(schedule.getEffectiveStartDate().toString()));
//				System.out.println("sch effective start Date --------> "+getScheduleEffStart());
				}
			}
		}
		
		
		if(schid != null){
		if(formtype==null && !schid.equals("undefined")){
		Schedule sch = new Schedule();
		sch.setSchedule_id(schedule.getSchedule_id());
		formfour.setScheduleNumber(sch);
		setServiceid(String.valueOf(schedule.getServicetype().getService_type_id()));
		formfour.getScheduleNumber().setSchedule_id(sch.getSchedule_id());
		formfour.setNoofTrips(8);
	}
		
		}
		
	}

	

	@Override
	public void setSession(Map mapSession) {
		// TODO Auto-generated method stub
		this.mapSession=mapSession;
	}
	
	@SkipValidation
	public String activateFormFour(){
		try{
		HttpServletRequest request=ServletActionContext.getRequest();
		String id = request.getParameter("id");
		HttpSession sess = request.getSession();
		int userid = (Integer) request.getSession().getAttribute("userid");
		FormFourDAO formdao = new FormFourDAO();
		int cnt = formdao.validateTripDetails(Integer.parseInt(id));
	
		if(cnt > 0){
			mapSession.put("activatemessage", "Please check trip details for selected form four");
			
		}
		else{
		FormFour formfour = formdao.getFormFour(Integer.parseInt(id));
		if(formdao.checkFormFourCount(formfour.getScheduleNumber().getSchedule_id()) == 0 && formdao.checkWeeklyChartCount(Integer.parseInt(id))==0){
			if(formfour.getFormFourType().getFormFourTypeName().toLowerCase().replace(" ", "").contains("alldays") || formfour.getFormFourType().getFormFourTypeName().toLowerCase().replace(" ", "").contains("weekdays")){
				WeeklyChartDao weeklydao = new WeeklyChartDao();
				weeklydao.insertInitialChart(Integer.parseInt(id),userid);
			}
		}
		formdao.activateFormFour(Integer.parseInt(id));
		mapSession.put("activatemessage", "Form Four "+id+" Acivated Successfully");
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
		
		
	}
	//rajesh
	//check route exit in fare
	@SkipValidation
	public String checkRouteExit() throws IOException
	{
		CommonValidation commvalidate = new CommonValidation();
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		ScheduleDAO scheduledao = new ScheduleDAO();
		String routeid = request.getParameter("routeid");
		String serviceid = request.getParameter("serviceid");
		
		FormFourDAO formdao = new FormFourDAO();
		int cnt=formdao.checkRouteExit(Integer.parseInt(routeid),Integer.parseInt(serviceid));
		if(cnt<=0)
		{
			String s="0";
			out.println(s);
		}
		else{
			
			String s="1";
			out.println(s);
		}
		}catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	@SkipValidation
	public void getServiceidByscheduleId() throws IOException
	{
		int serviceid=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		try{
			FormFourDAO formdao = new FormFourDAO();
			int scheduleid=Integer.parseInt(request.getParameter("scheduleid"));
			serviceid=formdao.getServiceTypeIDByScheduleid(scheduleid);
 
			out.println(serviceid);
		}
		catch(Exception e)
		
		{
			
			e.printStackTrace();
		}
	}
	//end check route in fare
	
}
