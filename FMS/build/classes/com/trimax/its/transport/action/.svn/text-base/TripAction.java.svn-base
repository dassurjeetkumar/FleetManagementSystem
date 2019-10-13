
package com.trimax.its.transport.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.transport.dao.FormFourDAO;
import com.trimax.its.transport.dao.TripDAO;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.vehicle.model.BrandType;

public class TripAction extends ActionSupport implements Preparable,SessionAware{
	
	private FormFour formfour;
	private String brand;
	private String route;
	private String schedule;
	private String service;
	private String division;
	private String depot;
	private String shift;
	private String shiftCode;
	private String effectstartdate;
	private String txbk;
	private String formfourname;
	private String routeNumber;
	private String routeName;
	private String signIn1;
	private String signOff1;
	private String signIn2;
	private String signOff2;
	private int id;
	private boolean flag;
	private String crewChangeId;
	private List<String> selectedTrip = new ArrayList<String>();
	private String shiftHeader1;
	private String shiftHeader2;
	private SessionMap<String,Object> sessionMap;  
	
	private int serviceid;
	
	
	public int getServiceid() {
		return serviceid;
	}

	private String traffic_order;
	private String recordDate;

	public void setServiceid(int serviceid) {
		this.serviceid = serviceid;
	}

	private Map<Integer, Boolean> checkboxes;
	
	
	public List<String> getSelectedTrip() {
		return selectedTrip;
	}



	public void setSelectedTrip(List<String> selectedTrip) {
		this.selectedTrip = selectedTrip;
	}

	private List<Integer> trips = new ArrayList<Integer>();
	
	private Map<String,String> breakMap;
	private Map<Integer,String> tripMap;
	
	private Map<String,String> CustomerMap;
	
	private Map<String,String> CustomerCharterMap;
	private Map<Integer,String> shifttypeMap;
	
	private List<ScheduleDetails> tripdetails = new ArrayList<ScheduleDetails>();
	private List<ScheduleDetails> table2details = new ArrayList<ScheduleDetails>();
	private int table2size;
	
	
	
	public Map<Integer, String> getShifttypeMap() {
		return shifttypeMap;
	}



	public void setShifttypeMap(Map<Integer, String> shifttypeMap) {
		this.shifttypeMap = shifttypeMap;
	}



	public List<ScheduleDetails> getTripdetails() {
		return tripdetails;
	}



	public void setTripdetails(List<ScheduleDetails> tripdetails) {
		this.tripdetails = tripdetails;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getRoute() {
		return route;
	}



	public void setRoute(String route) {
		this.route = route;
	}



	public String getSchedule() {
		return schedule;
	}



	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}



	public String getDivision() {
		return division;
	}



	public void setDivision(String division) {
		this.division = division;
	}



	public String getDepot() {
		return depot;
	}



	public void setDepot(String depot) {
		this.depot = depot;
	}



	public String getShift() {
		return shift;
	}



	public void setShift(String shift) {
		this.shift = shift;
	}



	public String getEffectstartdate() {
		return effectstartdate;
	}



	public void setEffectstartdate(String effectstartdate) {
		this.effectstartdate = effectstartdate;
	}



	public FormFour getFormfour() {
		return formfour;
	}



	public void setFormfour(FormFour formfour) {
		this.formfour = formfour;
	}

	

	
	public Map<String, String> getBreakMap() {
		return breakMap;
	}



	public void setBreakMap(Map<String, String> breakMap) {
		this.breakMap = breakMap;
	}



	public Map<Integer, String> getTripMap() {
		return tripMap;
	}



	public void setTripMap(Map<Integer, String> tripMap) {
		this.tripMap = tripMap;
	}

	

	public List<Integer> getTrips() {
		return trips;
	}



	public void setTrips(List<Integer> trips) {
		this.trips = trips;
	}
	
	
	
	public String getTxbk() {
		return txbk;
	}



	public void setTxbk(String txbk) {
		this.txbk = txbk;
	}

	

	public Map<Integer, Boolean> getCheckboxes() {
		return checkboxes;
	}



	public void setCheckboxes(Map<Integer, Boolean> checkboxes) {
		this.checkboxes = checkboxes;
	}

	



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	

	public String getService() {
		return service;
	}



	public String getTraffic_order() {
		return traffic_order;
	}



	public void setTraffic_order(String traffic_order) {
		this.traffic_order = traffic_order;
	}



	public String getRecordDate() {
		return recordDate;
	}



	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}



	public void setService(String service) {
		this.service = service;
	}

	

	public List<ScheduleDetails> getTable2details() {
		return table2details;
	}



	public void setTable2details(List<ScheduleDetails> table2details) {
		this.table2details = table2details;
	}

	

	public int getTable2size() {
		return table2size;
	}



	public void setTable2size(int table2size) {
		this.table2size = table2size;
	}


	public String getCrewChangeId() {
		return crewChangeId;
	}



	public void setCrewChangeId(String crewChangeId) {
		this.crewChangeId = crewChangeId;
	}

	

	public Map<String, String> getCustomerMap() {
		return CustomerMap;
	}



	public void setCustomerMap(Map<String, String> customerMap) {
		CustomerMap = customerMap;
	}

	

	public Map<String, String> getCustomerCharterMap() {
		return CustomerCharterMap;
	}



	public void setCustomerCharterMap(Map<String, String> customerCharterMap) {
		CustomerCharterMap = customerCharterMap;
	}

	

	public String getFormfourname() {
		return formfourname;
	}



	public void setFormfourname(String formfourname) {
		this.formfourname = formfourname;
	}
	
	


	public String getRouteNumber() {
		return routeNumber;
	}



	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
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
	 * @return the signIn1
	 */
	public String getSignIn1() {
		return signIn1;
	}



	/**
	 * @param signIn1 the signIn1 to set
	 */
	public void setSignIn1(String signIn1) {
		this.signIn1 = signIn1;
	}



	/**
	 * @return the signOff1
	 */
	public String getSignOff1() {
		return signOff1;
	}



	/**
	 * @param signOff1 the signOff1 to set
	 */
	public void setSignOff1(String signOff1) {
		this.signOff1 = signOff1;
	}



	/**
	 * @return the signIn2
	 */
	public String getSignIn2() {
		return signIn2;
	}



	/**
	 * @param signIn2 the signIn2 to set
	 */
	public void setSignIn2(String signIn2) {
		this.signIn2 = signIn2;
	}



	/**
	 * @return the signOff2
	 */
	public String getSignOff2() {
		return signOff2;
	}



	/**
	 * @param signOff2 the signOff2 to set
	 */
	public void setSignOff2(String signOff2) {
		this.signOff2 = signOff2;
	}

	

	/**
	 * @return the sessionMap
	 */
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}



	/**
	 * @param sessionMap the sessionMap to set
	 */
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}



	public String saveTripDetails(){
		int id=0;
		try{
//			System.out.println("in saveTripDetails11 ======");
		FormFourDAO formfourdao = new FormFourDAO();
		Common common = new Common();
		TripDAO tripdao = new TripDAO();
		
		String tr = formfour.getTraffic_order_no();
//		System.out.println("tr in saveTripDetails ======"+tr);
//		String rec=formfour.getRecordDate();
		if(formfourdao.checkFormFour(formfour, common.getDateInDateSecs(formfour.getEffectiveStartDate()))==0){
		 id = tripdao.saveTrips(tripdetails,formfour,crewChangeId,tr);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		HttpServletRequest request=ServletActionContext.getRequest();  
		request.setAttribute("formfourID",formfour.getId());
		sessionMap.put("id", id);
		addActionMessage("Trips For Schedule Id "+formfour.getScheduleNumber().getSchedule_id()+ " Created Successfully");
		return "success";
	}
	
	
@SkipValidation
	public String execute(){
		
		/*//setBrand(formfour1.getScheduleNumber().getBrand().getBrand_type_name());
		System.out.println("Route Number----->"+formfour1.getFormFourRoute().getRouteNumber()+" Name------->"+formfour1.getScheduleNumberName());
		
		setSchedule(formfour1.getScheduleNumber().getScheduleNumber());
		//setDepot(formfour1.getScheduleNumber().getDepot().getOrg_name());
*/		
	
	HttpServletRequest request=ServletActionContext.getRequest();
	request.setAttribute("formfourID",formfour.getId());
		return "success";
	}

	
	

	@Override
	public void prepare() throws Exception {
		
		// TODO Auto-generated method stub
		Common common = new Common();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		TripDAO tripdao = new TripDAO();
		/*int formid = 0;
		if(id!=0){
			formid = id;
		}
		else{
			//formid = (Integer)request.getAttribute("formfourID");
			formid = (Integer) session.getAttribute("formfourID");
		}*/
		
		FormFour formfour1 = (FormFour) session.getAttribute("formfourID");
		setFormfour(formfour1);
		String traffic = formfour1.getTraffic_order_no();
		String datec = formfour1.getRecordDate();
		
		Schedule schedule = tripdao.getSchedule(formfour1.getScheduleNumber().getSchedule_id());
		BrandType brandtype = tripdao.getBrand(schedule.getBrand().getBrand_type_id());
		setBrand(brandtype.getBrand_type_name());
		Route_Trans route = tripdao.getRoute(formfour1.getFormFourRoute().getRoute_id());
		setRoute(String.valueOf(route.getRoute_id()));
		setRouteNumber(route.getRouteNumber());
		setRouteName(route.getRouteName());
		setFormfourname(formfour1.getScheduleNumberName());
		setSchedule(schedule.getScheduleNumber());
		setDepot(schedule.getDepot().getOrg_name());
		setDivision(tripdao.getDivision(schedule.getDepot().getParent_id()));
		setService(schedule.getServicetype().getServiceTypeName());
		serviceid=schedule.getServicetype().getService_type_id();
		setServiceid(schedule.getServicetype().getService_type_id());
		setShift(schedule.getScheduletype().getScheduleName());
		setShiftCode(schedule.getScheduletype().getScheduleCode());
		setEffectstartdate(common.getDateInDDMMYY((formfour1.getEffectiveStartDate())));
		
		setTraffic_order(traffic);
		setRecordDate(datec);
		formfour.setStarttimeString(common.getDateInHHMM(formfour.getStartTime()));
		table2size = tripdetails.size()-table2details.size();
		breakMap = tripdao.getBreakType();
		tripMap = tripdao.getTripType();
		CustomerMap = tripdao.getCustomer();
		CustomerCharterMap = tripdao.getCustomerforCharter();
		shifttypeMap = tripdao.getShiftType(schedule.getScheduletype().getSchedule_type_id());
		String signinoff = tripdao.getSignInOff(schedule.getScheduletype().getSchedule_type_id());
		String splitresult[] = signinoff.split("@");
		String shiftsplit[];
		for(int i=0;i<splitresult.length;i++){
			shiftsplit = splitresult[i].split("-");
			if(shiftsplit[2] == "DAY_1"){
				setSignIn1(shiftsplit[0]);
				setSignOff1(shiftsplit[1]);
			}
			else if(shiftsplit[2] == "DAY_2"){
				setSignIn2(shiftsplit[0]);
				setSignOff2(shiftsplit[1]);
			}
			else if(shiftsplit[2] == "GENERAL_SHIFT" || shiftsplit[2] == "SHIFT_1" || shiftsplit[2] == "NIGHT_SHIFT"){
				setSignIn1(shiftsplit[0]);
				setSignOff1(shiftsplit[1]);
			}
			else{
				setSignIn2(shiftsplit[0]);
				setSignOff2(shiftsplit[1]);
			}
		}
		trips.clear();
		
		if(schedule.getScheduletype().getScheduleCode().equalsIgnoreCase("NO")){
		setShiftHeader1("Day 1");
		setShiftHeader2("Day 2");
		}
		else{
			setShiftHeader1("Shift 1");
			setShiftHeader2("Shift 2");
		}
		
		for(int i=0;i<formfour.getNoofTrips();i++){
			trips.add(i+1);
		}
		//tripdetails.get(0).setStarttimeString(formfour.getStarttimeString());
		
	}



	public boolean isFlag() {
		return flag;
	}



	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void validate(){
		int flag=0;
			
			
			
			/*if(tripdetails.get(i).getOrigin().equals(null) || tripdetails.get(i).getOrigin().trim().equals("")){
				addActionError("Origin Place is mandatory at trip "+(i+1));
				flag=1;
			}
			if(tripdetails.get(i).getDestination().equals(null) || tripdetails.get(i).getDestination().trim().equals("")){
				addActionError("Destination Place is mandatory at trip "+(i+1));
				flag=1;
			}
			if(tripdetails.get(i).getRouteNumber().getRoute_id().equals(0)){
				addActionError("Route Number is mandatory at trip "+(i+1));
				flag=1;
			}
			if(tripdetails.get(i).getStarttimeString().equals(null) || tripdetails.get(i).getStarttimeString().trim().equals("")|| tripdetails.get(i).getStarttimeString().equals("0:00")){
				addActionError("From Time is mandatory at trip "+(i+1));
				flag=1;
			}
			if(tripdetails.get(i).getEndtimeString().equals(null) || tripdetails.get(i).getEndtimeString().trim().equals("") || tripdetails.get(i).getEndtimeString().equals("0:00")){
				addActionError("To Time is mandatory at trip "+(i+1));
				flag=1;
			}
			if(tripdetails.get(i).getShiftType().getId() == 0){
				addActionError("Duty Type is mandatory at trip "+(i+1));
				flag=1;
			}
			if(flag==1){
				break;
			}
			*/
	
		
	}



	@Override
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}  
}
