
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

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.transport.dao.FormFourDAO;
import com.trimax.its.transport.dao.TripDAO;
import com.trimax.its.transport.model.BreakType;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.vehicle.model.BrandType;

public class TripEditAction extends ActionSupport {
	
	private FormFour formFour;
	private String brand;
	private String route;
	private String schedule;
	private String service;
	private String division;
	private String depot;
	private String shift;
	private String shiftCode;
	private String effectstartdate;
	private String routeNumber;
	private String routeName;
	private String formfourname;
	private List<ScheduleDetails> tripslist = new ArrayList<ScheduleDetails>();
	private List<ScheduleDetails> tripslist1 = new ArrayList<ScheduleDetails>();
	private List<ScheduleDetails> tripdetails = new ArrayList<ScheduleDetails>();
	private int crewChangeFlag = 0;
	private String shiftHeader1;
	private String shiftHeader2;
	private String formfourid;
	private String deletedTrips;
	private int crewChangeStatus=0;
	
	
	private Map<String,String> breakMap;
	private Map<Integer,String> tripMap;
	
	private Map<String,String> CustomerMap;
	
	private Map<String,String> CustomerCharterMap;
	private Map<Integer,String> shifttypeMap;
	
	private String crewChangeId;
	private String crewChangeStartId;
	private String serviceid;
	
	private String startDate;
	private String endDate;
	
	private String traffic_order_no;
	
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getTraffic_order_no() {
		return traffic_order_no;
	}
	public void setTraffic_order_no(String traffic_order_no) {
		this.traffic_order_no = traffic_order_no;
	}

	private String recordDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	/**
	 * @return the formFour
	 */
	public FormFour getFormFour() {
		return formFour;
	}
	/**
	 * @param formFour the formFour to set
	 */
	public void setFormFour(FormFour formFour) {
		this.formFour = formFour;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}
	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}
	/**
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}
	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * @return the depot
	 */
	public String getDepot() {
		return depot;
	}
	/**
	 * @param depot the depot to set
	 */
	public void setDepot(String depot) {
		this.depot = depot;
	}
	/**
	 * @return the shift
	 */
	public String getShift() {
		return shift;
	}
	/**
	 * @param shift the shift to set
	 */
	public void setShift(String shift) {
		this.shift = shift;
	}
	/**
	 * @return the effectstartdate
	 */
	public String getEffectstartdate() {
		return effectstartdate;
	}
	/**
	 * @param effectstartdate the effectstartdate to set
	 */
	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}
	/**
	 * @return the routeNumber
	 */
	public String getRouteNumber() {
		return routeNumber;
	}
	/**
	 * @param routeNumber the routeNumber to set
	 */
	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}
	/**
	 * @return the formfourname
	 */
	public String getFormfourname() {
		return formfourname;
	}
	/**
	 * @param formfourname the formfourname to set
	 */
	public void setFormfourname(String formfourname) {
		this.formfourname = formfourname;
	}
	/**
	 * @return the tripslist
	 */
	public List<ScheduleDetails> getTripslist() {
		return tripslist;
	}
	/**
	 * @param tripslist the tripslist to set
	 */
	public void setTripslist(List<ScheduleDetails> tripslist) {
		this.tripslist = tripslist;
	}
	/**
	 * @return the tripslist1
	 */
	public List<ScheduleDetails> getTripslist1() {
		return tripslist1;
	}
	/**
	 * @param tripslist1 the tripslist1 to set
	 */
	public void setTripslist1(List<ScheduleDetails> tripslist1) {
		this.tripslist1 = tripslist1;
	}
	/**
	 * @return the crewChangeFlag
	 */
	public int getCrewChangeFlag() {
		return crewChangeFlag;
	}
	/**
	 * @param crewChangeFlag the crewChangeFlag to set
	 */
	public void setCrewChangeFlag(int crewChangeFlag) {
		this.crewChangeFlag = crewChangeFlag;
	}
	/**
	 * @return the shiftHeader1
	 */
	public String getShiftHeader1() {
		return shiftHeader1;
	}
	/**
	 * @param shiftHeader1 the shiftHeader1 to set
	 */
	public void setShiftHeader1(String shiftHeader1) {
		this.shiftHeader1 = shiftHeader1;
	}
	/**
	 * @return the shiftHeader2
	 */
	public String getShiftHeader2() {
		return shiftHeader2;
	}
	/**
	 * @param shiftHeader2 the shiftHeader2 to set
	 */
	public void setShiftHeader2(String shiftHeader2) {
		this.shiftHeader2 = shiftHeader2;
	}
	
	
	
	
	/**
	 * @return the breakMap
	 */
	public Map<String, String> getBreakMap() {
		return breakMap;
	}
	/**
	 * @param breakMap the breakMap to set
	 */
	public void setBreakMap(Map<String, String> breakMap) {
		this.breakMap = breakMap;
	}
	/**
	 * @return the tripMap
	 */
	public Map<Integer, String> getTripMap() {
		return tripMap;
	}
	/**
	 * @param tripMap the tripMap to set
	 */
	public void setTripMap(Map<Integer, String> tripMap) {
		this.tripMap = tripMap;
	}
	/**
	 * @return the customerMap
	 */
	public Map<String, String> getCustomerMap() {
		return CustomerMap;
	}
	/**
	 * @param customerMap the customerMap to set
	 */
	public void setCustomerMap(Map<String, String> customerMap) {
		CustomerMap = customerMap;
	}
	/**
	 * @return the customerCharterMap
	 */
	public Map<String, String> getCustomerCharterMap() {
		return CustomerCharterMap;
	}
	/**
	 * @param customerCharterMap the customerCharterMap to set
	 */
	public void setCustomerCharterMap(Map<String, String> customerCharterMap) {
		CustomerCharterMap = customerCharterMap;
	}
	/**
	 * @return the shifttypeMap
	 */
	public Map<Integer, String> getShifttypeMap() {
		return shifttypeMap;
	}
	/**
	 * @param shifttypeMap the shifttypeMap to set
	 */
	public void setShifttypeMap(Map<Integer, String> shifttypeMap) {
		this.shifttypeMap = shifttypeMap;
	}
	/**
	 * @return the tripdetails
	 */
	public List<ScheduleDetails> getTripdetails() {
		return tripdetails;
	}
	/**
	 * @param tripdetails the tripdetails to set
	 */
	public void setTripdetails(List<ScheduleDetails> tripdetails) {
		this.tripdetails = tripdetails;
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
	 * @return the crewChangeId
	 */
	public String getCrewChangeId() {
		return crewChangeId;
	}
	/**
	 * @param crewChangeId the crewChangeId to set
	 */
	public void setCrewChangeId(String crewChangeId) {
		this.crewChangeId = crewChangeId;
	}
	/**
	 * @return the deletedTrips
	 */
	public String getDeletedTrips() {
		return deletedTrips;
	}
	/**
	 * @param deletedTrips the deletedTrips to set
	 */
	public void setDeletedTrips(String deletedTrips) {
		this.deletedTrips = deletedTrips;
	}
	/**
	 * @return the crewChangeStatus
	 */
	public int getCrewChangeStatus() {
		return crewChangeStatus;
	}
	/**
	 * @param crewChangeStatus the crewChangeStatus to set
	 */
	public void setCrewChangeStatus(int crewChangeStatus) {
		this.crewChangeStatus = crewChangeStatus;
	}
	/**
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}
	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	/**
	 * @return the crewChangeStartId
	 */
	public String getCrewChangeStartId() {
		return crewChangeStartId;
	}
	/**
	 * @param crewChangeStartId the crewChangeStartId to set
	 */
	public void setCrewChangeStartId(String crewChangeStartId) {
		this.crewChangeStartId = crewChangeStartId;
	}
	/**
	 * @return the shiftCode
	 */
	public String getShiftCode() {
		return shiftCode;
	}
	/**
	 * @param shiftCode the shiftCode to set
	 */
	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	public String execute(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		//String id = request.getParameter("formfour");
		TripDAO tripdao = new TripDAO();
		Common common = new Common();
		formFour = (FormFour) request.getAttribute("formfour");
		
		sess.setAttribute("formfour", formFour);
		Schedule schedule = tripdao.getSchedule(formFour.getScheduleNumber().getSchedule_id());
		BrandType brandtype = tripdao.getBrand(schedule.getBrand().getBrand_type_id());
		setBrand(brandtype.getBrand_type_name());
		Route_Trans route = tripdao.getRoute(formFour.getFormFourRoute().getRoute_id());
		
		setService(schedule.getServicetype().getServiceTypeName());
		serviceid=String.valueOf(schedule.getServicetype().getService_type_id());
		setRouteNumber(route.getRouteNumber());
		setRouteName(route.getRouteName());
		setSchedule(schedule.getScheduleNumber());
		setFormfourname(formFour.getScheduleNumberName());
		setDivision(tripdao.getDivision(schedule.getDepot().getParent_id()));
		setDepot(schedule.getDepot().getOrg_name());
		setShift(schedule.getScheduletype().getScheduleName());
		setShiftCode(schedule.getScheduletype().getScheduleCode());
		setEffectstartdate(common.getDateInDDMMYY((formFour.getEffectiveStartDate())));
		
		breakMap = tripdao.getBreakType();
		tripMap = tripdao.getTripType();
		CustomerMap = tripdao.getCustomerforCharter();
		CustomerCharterMap = tripdao.getCustomerforCharter();
		shifttypeMap = tripdao.getShiftType(schedule.getScheduletype().getSchedule_type_id());
		
		tripdetails = tripdao.getTripDetails(formFour.getId());
		String trafficNo =formFour.getTraffic_order_no();
//		String rec = formFour.getRecordDate();
//		System.out.println("in trip edit execute-- "+trafficNo+" rec "+rec);
		setTraffic_order_no(trafficNo);
//		setRecordDate(rec);
		request.getSession().setAttribute("traffic_order", trafficNo);
//		request.getSession().setAttribute("record_date", rec);
//		System.out.println("in trip edit execute after set "+);
		if(schedule.getScheduletype().getScheduleCode().equalsIgnoreCase("NO")){
			setShiftHeader1("Day 1");
			setShiftHeader2("Day 2");
			}
			else{
				setShiftHeader1("Shift 1");
				setShiftHeader2("Shift 2");
			}
		System.out.println("formFour.getId() -- > "+formFour.getId());
		String busids="(";
		for(int i=0;i<tripdetails.size();i++){
			
			if(i==(tripdetails.size()-1)){
				busids += "'"+tripdetails.get(i).getStartPoint()+"',";
				busids += "'"+tripdetails.get(i).getEndPoint()+"'";
			}
			else{
				busids += "'"+tripdetails.get(i).getStartPoint()+"',";
				busids += "'"+tripdetails.get(i).getEndPoint()+"',";
			}
		}
		busids += ")";
		
		Map<Integer,String> busStopMap = tripdao.getBusStopNameWithTowards(busids);
		
		
		for(int i=0;i<tripdetails.size();i++){
			tripdetails.get(i).setOrigin(busStopMap.get(tripdetails.get(i).getStartPoint()));
			tripdetails.get(i).setDestination(busStopMap.get(tripdetails.get(i).getEndPoint()));
			
			if(tripdetails.get(i).getStartTime().toString().equals("23:59:00")){
				tripdetails.get(i).setStarttimeString("24:00");
			}
			else{
				tripdetails.get(i).setStarttimeString(common.getDateInHHMM(tripdetails.get(i).getStartTime()));
			}
			
			if(tripdetails.get(i).getEndTime().toString().equals("23:59:00")){
				tripdetails.get(i).setEndtimeString("24:00");
			}
			else{
				tripdetails.get(i).setEndtimeString(common.getDateInHHMM(tripdetails.get(i).getEndTime()));
			}
			
			/*tripdetails.get(i).setStarttimeString(common.getDateInHHMM(tripdetails.get(i).getStartTime()));
			tripdetails.get(i).setEndtimeString(common.getDateInHHMM(tripdetails.get(i).getEndTime()));*/
			tripdetails.get(i).setJourneyTime(common.getDateInHHMM(tripdetails.get(i).getRunningTime()));
			tripdetails.get(i).setBreaktimeString(common.getDateInHHMM(tripdetails.get(i).getBreakTime()));
			if(tripdetails.get(i).getCrewChange() == 1 || tripdetails.get(i).getNightHalt() == 1){
				crewChangeId = String.valueOf(i);
				crewChangeStartId = String.valueOf(i+1);
				crewChangeStatus = 1;
			}
			tripdetails.get(i).setRouteDetails(tripdao.getRoute1(String.valueOf(tripdetails.get(i).getStartPoint()), String.valueOf(tripdetails.get(i).getEndPoint())));
			tripdetails.get(i).setGroupStops(tripdao.getGroupingStops(String.valueOf(tripdetails.get(i).getEndPoint())));
			if(i<(tripdetails.size()-1)){
				tripdetails.get(i).setGroupStartPoint(String.valueOf(tripdetails.get(i+1).getStartPoint()));
			}
			

		}

		return "success";
	}
	
	public String saveEdited() throws ParseException{
		
		Common common = new Common();
		TripDAO dao = new TripDAO();
		String startpoint = "";
		String endpoint="";
		int formfourId=0;
		FormFourDAO formdao = new FormFourDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession sess=request.getSession();
		FormFour editedFormfour = (FormFour) sess.getAttribute("formfour");
		sess.removeAttribute("formfour");
		int userid = (Integer)request.getSession().getAttribute("userid");
		String traffic_order=(String) request.getSession().getAttribute("traffic_order");
//		String record_dte= (String)request.getSession().getAttribute("record_date");
		
		for(int i=0;i<tripslist.size();i++){
			if(tripslist.get(i).isCrewChangeStatus()){
				//get Halt Location Name & Update 
				tripslist.get(i).setCrewChange(1);
			}
			/*if(tripslist.get(i).isDeadTrip()){
				tripslist.get(i).setIsDreadTrip(1);
			}*/
			if(tripslist.get(i).isNightTrip()){
				tripslist.get(i).setNightHalt(1);
			}
			
//			String tr = getTraffic_order_no();
//			String rec=getRecordDate();
//			System.out.println("save trip ===== "+tr+"=== "+rec);
			
			String tr =request.getParameter("traffic_order_no");
			String rec= request.getParameter("recordDate");
			
			
			if(tripslist.get(i).getStarttimeString().equals("24:00")){
				tripslist.get(i).setStarttimeString("23:59");
			}
			if(tripslist.get(i).getEndtimeString().equals("24:00")){
				tripslist.get(i).setEndtimeString("23:59");
			}
			tripslist.get(i).setStartTime(common.getHours(tripslist.get(i).getStarttimeString()));
			tripslist.get(i).setEndTime(common.getHours(tripslist.get(i).getEndtimeString()));
			tripslist.get(i).setRunningTime(common.getHours(tripslist.get(i).getJourneyTime()));
			tripslist.get(i).setBreakTime(common.getHours(tripslist.get(i).getBreaktimeString()));
			tripslist.get(i).setUpdatedBy(userid);
			tripslist.get(i).setUpdatedDate(new Date());
			BreakType breaktype = new BreakType();
			String [] splitResult = tripslist.get(i).getBreakTypeString().split("-");
			breaktype.setId(Integer.parseInt(splitResult[1]));
			tripslist.get(i).setBreakType(breaktype);
		}
//		if(!formFour.getCurrentStatus().equalsIgnoreCase("ACTIVE")){
		dao.saveEditedTrips(tripslist,formFour,editedFormfour);
//		}
		
		String splitArray[] = deletedTrips.split(",");
		if(!(splitArray.length==1 && splitArray[0].equals(""))){
		for(int i=0;i<splitArray.length;i++){
			if(splitArray[i]!=null){
			dao.setDeleteStatus(splitArray[i]);
			}
		}
		//new code start 31-07-15
		
		}
		
//		if(common.compareOnlyDate(common.getDate(getStartDate())) == 0 || common.compareOnlyDate(common.getDate(getStartDate())) == 1 || 
//				common.getDate(getStartDate()).compareTo(common.getDate(editedFormfour.getEndDate())) == 0){
//			System.out.println("INside if"+getStartDate()+"getEndDate()");					
//		}
//		else{
		FormFour formfour2=formdao.getFormFourById(tripslist.get(0).getFormFour().getId());
		if(formfour2.getCurrentStatus().equalsIgnoreCase("ACTIVE")){
		
		
		int newformfourid=formdao.SaveNewFormFour(formfour2,editedFormfour);
		FormFour formfour3=formdao.getFormFourById(newformfourid);
		formdao.UpdatedOldFormFourActive(formfour2,common.getDatefromString(editedFormfour.getEffectiveStartDate().toString()),
				common.getDateFromPicker(getStartDate()),common.getDateFromPicker(getEndDate()));
		dao.saveEditedTripsActive(tripslist,formFour,editedFormfour,newformfourid);
		//formdao.saveFormFourInNewschedule(newformfourid,newformfourid);
		
		formdao.updateweeklychart(formfour2.getId(),newformfourid);
		//For Update Schedule mapping add by Rajesh 
		formdao.updateschedulemapping(formfour2.getId(),newformfourid,editedFormfour.getScheduleNumber().getSchedule_id());
		System.out.println("old_id==="+formfour2.getId()+"new id===="+newformfourid);
		formfourId = newformfourid;
		}else{
			formdao.updateFormFour(formfour2);
			formfourId = formfour2.getId();
		}
		if(formdao.checkTraficOrderFormFour(formfourId,traffic_order)){
			
			
		}else {
		formdao.insertIntoTraffic_order(formfourId, traffic_order);
		}
//		formdao.insertIntoTraffic_order(formfourId, traffic_order, record_dte);
		//}
		//end code 
		addActionMessage("Trips For Schedule Id "+editedFormfour.getScheduleNumber().getSchedule_id()+ " Edited Successfully");
		//addActionMessage("Trips Edited Successfully");
		return "success";
	}
	
	public String setTripStatus() throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("tripid");
		TripDAO tripdao = new TripDAO();
		tripdao.setDeleteStatus(id);
		PrintWriter out = response.getWriter();
		out.println("success");
		return null;
	}
}
