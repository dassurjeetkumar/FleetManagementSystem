
package com.trimax.its.transport.action;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.common.Common;
import com.trimax.its.transport.dao.FormFourDAO;
import com.trimax.its.transport.dao.TripDAO;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.vehicle.model.BrandType;

public class TripsViewAction {

	private FormFour formFour;
	private String brand;
	private String route;
	private String schedule;
	private String service;
	private String division;
	private String depot;
	private String shift;
	private String effectstartdate;
	private String routeNumber;
	private String routeName;
	private String formfourname;
	private List<ScheduleDetails> tripslist = new ArrayList<ScheduleDetails>();
	private List<ScheduleDetails> tripslist1 = new ArrayList<ScheduleDetails>();
	private int crewChangeFlag = 0;
	private String shiftHeader1;
	private String shiftHeader2;
	private String nightHaltLocation;
	private int scheduleCode;
	private int crewChangeId;
	private List<String> fareStages;
	private List<String> stages;
	List finalstageslist;
	private List<String> fareStages1;
	private List<String> fareStages2;
	private List<String> fareStages3;
	private List<String> fareStages4;
	private List<String> fareStages5;
	private List<String> fareStages6;
	private List<String> fareStages7;
	private List<String> fareStages8;
	private List<String> fareStages9;
	private List<String> fareStages10;
	private List<String> fareStages11;
	private List<String> fareStages12;
	
	
	private String routename0;
	private String routename1;
	private String routename2;
	private String routename3;
	private String routename4;
	private String routename5;
	private String routename6;
	private String routename7;
	private String routename8;
	private String routename9;
	private String routename10;
	private String routename11;
	private String routename12;
	
	private List<List<String>> listOfFareStages;
	
	private String traffic_order;
//	private String record_date;

	
	
	
	



		public List<String> getFareStages11() {
		return fareStages11;
	}

	public void setFareStages11(List<String> fareStages11) {
		this.fareStages11 = fareStages11;
	}

	public List<String> getFareStages12() {
		return fareStages12;
	}

	public void setFareStages12(List<String> fareStages12) {
		this.fareStages12 = fareStages12;
	}

	public String getRoutename11() {
		return routename11;
	}

	public void setRoutename11(String routename11) {
		this.routename11 = routename11;
	}

	public String getRoutename12() {
		return routename12;
	}

	public void setRoutename12(String routename12) {
		this.routename12 = routename12;
	}

		public List<String> getFareStages9() {
		return fareStages9;
	}

	public void setFareStages9(List<String> fareStages9) {
		this.fareStages9 = fareStages9;
	}

	public List<String> getFareStages10() {
		return fareStages10;
	}

	public void setFareStages10(List<String> fareStages10) {
		this.fareStages10 = fareStages10;
	}

	public String getRoutename9() {
		return routename9;
	}

	public void setRoutename9(String routename9) {
		this.routename9 = routename9;
	}

	public String getRoutename10() {
		return routename10;
	}

	public void setRoutename10(String routename10) {
		this.routename10 = routename10;
	}

		public List<String> getFareStages8() {
		return fareStages8;
	}

	public void setFareStages8(List<String> fareStages8) {
		this.fareStages8 = fareStages8;
	}

		public String getRoutename0() {
		return routename0;
	}

	public void setRoutename0(String routename0) {
		this.routename0 = routename0;
	}

	public String getRoutename1() {
		return routename1;
	}

	public void setRoutename1(String routename1) {
		this.routename1 = routename1;
	}

	public String getRoutename2() {
		return routename2;
	}

	public void setRoutename2(String routename2) {
		this.routename2 = routename2;
	}

	public String getRoutename3() {
		return routename3;
	}

	public void setRoutename3(String routename3) {
		this.routename3 = routename3;
	}

	public String getRoutename4() {
		return routename4;
	}

	public void setRoutename4(String routename4) {
		this.routename4 = routename4;
	}

	public String getRoutename5() {
		return routename5;
	}

	public void setRoutename5(String routename5) {
		this.routename5 = routename5;
	}

	public String getRoutename6() {
		return routename6;
	}

	public void setRoutename6(String routename6) {
		this.routename6 = routename6;
	}

	public String getRoutename7() {
		return routename7;
	}

	public void setRoutename7(String routename7) {
		this.routename7 = routename7;
	}

	public String getRoutename8() {
		return routename8;
	}

	public void setRoutename8(String routename8) {
		this.routename8 = routename8;
	}

		public List<String> getFareStages1() {
		return fareStages1;
	}

	public void setFareStages1(List<String> fareStages1) {
		this.fareStages1 = fareStages1;
	}

	public List<String> getFareStages2() {
		return fareStages2;
	}

	public void setFareStages2(List<String> fareStages2) {
		this.fareStages2 = fareStages2;
	}

	public List<String> getFareStages3() {
		return fareStages3;
	}

	public void setFareStages3(List<String> fareStages3) {
		this.fareStages3 = fareStages3;
	}

	public List<String> getFareStages4() {
		return fareStages4;
	}

	public void setFareStages4(List<String> fareStages4) {
		this.fareStages4 = fareStages4;
	}

	public List<String> getFareStages5() {
		return fareStages5;
	}

	public void setFareStages5(List<String> fareStages5) {
		this.fareStages5 = fareStages5;
	}

	public List<String> getFareStages6() {
		return fareStages6;
	}

	public void setFareStages6(List<String> fareStages6) {
		this.fareStages6 = fareStages6;
	}

	public List<String> getFareStages7() {
		return fareStages7;
	}

	public void setFareStages7(List<String> fareStages7) {
		this.fareStages7 = fareStages7;
	}

	public List getFinalstageslist() {
		return finalstageslist;
	}

	public void setFinalstageslist(List finalstageslist) {
		this.finalstageslist = finalstageslist;
	}

	public List<String> getStages() {
		return stages;
	}

	public void setStages(List<String> stages) {
		this.stages = stages;
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
	 * @return the nightHaltLocation
	 */
	public String getNightHaltLocation() {
		return nightHaltLocation;
	}

	/**
	 * @param nightHaltLocation the nightHaltLocation to set
	 */
	public void setNightHaltLocation(String nightHaltLocation) {
		this.nightHaltLocation = nightHaltLocation;
	}
	

	/**
	 * @return the scheduleCode
	 */
	public int getScheduleCode() {
		return scheduleCode;
	}

	/**
	 * @param scheduleCode the scheduleCode to set
	 */
	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	/**
	 * @return the crewChangeId
	 */
	public int getCrewChangeId() {
		return crewChangeId;
	}

	/**
	 * @param crewChangeId the crewChangeId to set
	 */
	public void setCrewChangeId(int crewChangeId) {
		this.crewChangeId = crewChangeId;
	}
	
	
	

	/**
	 * @return the fareStages
	 */
	public List<String> getFareStages() {
		return fareStages;
	}

	/**
	 * @param fareStages the fareStages to set
	 */
	public void setFareStages(List<String> fareStages) {
		this.fareStages = fareStages;
	}
	
	

	public List<List<String>> getListOfFareStages() {
		return listOfFareStages;
	}

	public void setListOfFareStages(List<List<String>> listOfFareStages) {
		this.listOfFareStages = listOfFareStages;
	}

	public String execute(){

		Set<Integer> routelist=new HashSet<Integer>();
		HashMap map=new HashMap();
		Map<Integer, String> mapp = new HashMap<Integer, String>();
		//test final modifie map
		Map<Integer, String> mapps = new HashMap<Integer, String>();
		List<String> routenames=new ArrayList<String>();
		ArrayList<String> routenamess=new ArrayList<String>();
		ArrayList<String> routenamess1=new ArrayList<String>();
		Locale fmtLocale = Locale.getDefault();
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);
		FormFourDAO fdao=new FormFourDAO();
		double sch1=0.00;
		double sch2=0.00;
		double dead1=0.00;
		double dead2=0.00;
		Common common = new Common();
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		int id = 0;
		if(request.getAttribute("id") != null){
		id = (Integer) request.getAttribute("id");
		}
		if(id==0){
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		formFour = tripdao.getFormFour(id);
		String formfourId=String.valueOf(formFour.getId());
		Schedule schedule = tripdao.getSchedule(formFour.getScheduleNumber().getSchedule_id());
		BrandType brandtype = tripdao.getBrand(schedule.getBrand().getBrand_type_id());
		setBrand(brandtype.getBrand_type_name());
		Route_Trans route = tripdao.getRoute(formFour.getFormFourRoute().getRoute_id());
		
		setService(schedule.getServicetype().getServiceTypeName());
		setRouteNumber(route.getRouteNumber());
		setRouteName(route.getRouteName());
		setTraffic_order(fdao.getTrafficOrderNo(formfourId));
		setSchedule(schedule.getScheduleNumber());
		setFormfourname(formFour.getScheduleNumberName());
		setDivision(tripdao.getDivision(schedule.getDepot().getParent_id()));
		setDepot(schedule.getDepot().getOrg_name());
		setShift(schedule.getScheduletype().getScheduleName());
		setEffectstartdate(common.getDateInDDMMYY((formFour.getEffectiveStartDate())));
		
		
		if(schedule.getScheduletype().getScheduleCode().equalsIgnoreCase("NO")){
			setShiftHeader1("Day 1");
			setShiftHeader2("Day 2");
			setScheduleCode(1);
			}
			else{
				setShiftHeader1("Shift 1");
				setShiftHeader2("Shift 2");
				setScheduleCode(0);
			}
		tripslist = tripdao.getTripDetails(formFour.getId());
		int cnt = 0;
		try{
			List finalstageslist=new ArrayList();
		for(int i=0;i<tripslist.size();i++){
			tripslist.get(i).setOrigin(tripdao.getBusStopName(tripslist.get(i).getStartPoint()));
			tripslist.get(i).setDestination(tripdao.getBusStopName(tripslist.get(i).getEndPoint()));
			routelist.add(tripslist.get(i).getRouteNumber().getRoute_id());
			
			
			
			map.put(tripslist.get(i).getRouteNumber().getRoute_id(), "tripslist.get(i).getStartPoint()"+"@"+"tripdao.getBusStopName(tripslist.get(i).getEndPoint())");
			mapp.put(tripslist.get(i).getRouteNumber().getRoute_id(), tripslist.get(i).getStartPoint()+"@"+tripslist.get(i).getEndPoint());

			routenames.add(tripslist.get(i).getRouteNumber().getRouteNumber()+""+tripslist.get(i).getRouteNumber().getRouteDirection());
			routenamess.add(tripslist.get(i).getRouteNumber().getRoute_id()+"@"+ tripslist.get(i).getStartPoint()+"@"+tripslist.get(i).getEndPoint());
			routenamess1.add(tripslist.get(i).getRouteNumber().getRoute_id()+"@"+ tripslist.get(i).getStartPoint()+"@"+tripslist.get(i).getEndPoint()+"@"+tripslist.get(i).getRouteNumber().getRouteNumber()+"@"+tripslist.get(i).getRouteNumber().getRouteDirection());
			
			///System.out.println("triplist getRoute 9-10-15..........."+i+"....."+tripslist.get(i).getRouteNumber().getRoute_id());
			//System.out.println("triplist getRoute 9-10-15..........."+i+"....."+tripslist.get(i).getRouteNumber().getRouteNumber());
			//System.out.println("tripslist.get(i).getStartTime() ----> "+tripslist.get(i).getStartTime());
			
			cnt = tripdao.checkRouteNumber(tripslist.get(i).getStartPoint(), tripslist.get(i).getEndPoint(), tripslist.get(i).getRouteNumber().getRoute_id());
			
			if(cnt == 1){
				
				tripslist.get(i).setRouteNo(tripslist.get(i).getRouteNumber().getRouteNumber());
				
			}
			else{
				tripslist.get(i).setRouteNo("");
			}
			if(tripslist.get(i).getStartTime().toString().equals("23:59:00")){
				tripslist.get(i).setStarttimeString("24:00");
			}
			else{
			tripslist.get(i).setStarttimeString(common.getDateInHHMM(tripslist.get(i).getStartTime()));
			}
			
			if(tripslist.get(i).getEndTime().toString().equals("23:59:00")){
				tripslist.get(i).setEndtimeString("24:00");
			}
			else{
				tripslist.get(i).setEndtimeString(common.getDateInHHMM(tripslist.get(i).getEndTime()));
			}
			
			
			tripslist.get(i).setJourneyTime(common.getDateInHHMM(tripslist.get(i).getRunningTime()));
			tripslist.get(i).setBreaktimeString(common.getDateInHHMM(tripslist.get(i).getBreakTime()));
			
			
			
			if(crewChangeFlag == 0){
				if(tripslist.get(i).getTripType().getTripTypeCode().equals("DT")){
					dead1 = dead1 + tripslist.get(i).getDistance();
				}
				else{
				sch1 = sch1+tripslist.get(i).getDistance();
				}
			}
			else{
				if(tripslist.get(i).getTripType().getTripTypeCode().equals("DT")){
					dead2 = dead2 + tripslist.get(i).getDistance();
				}
				else{
				sch2 = sch2+tripslist.get(i).getDistance();
				}
			}
			if(tripslist.get(i).getCrewChange()==1 || tripslist.get(i).getNightHalt()==1){
				crewChangeFlag = 1;
				setCrewChangeId(i+1);
				if(tripslist.get(i).getNightHalt()==1){
				nightHaltLocation = tripslist.get(i).getCrewChangeLocation();
				if(nightHaltLocation.equals("") || nightHaltLocation.equals(null)){
					nightHaltLocation = tripslist.get(i).getNightHaltLocation();	
				}
				}
			}
		}
		
		
		int j=0;
		//mapps is modifie HashMap with unique route
		//here ModifeHighestStagesRoutes() method return modifie route with highest stages.
		//mapps=tripdao.ModifeHighestStagesRoutes(routenamess);
		//System.out.println("setlist"+routelist.size());
		//System.out.println("mapp"+mapp.size());
		//Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		/*for(int i=0;i<routenamess.size();i++){
			setRoutename0(tripdao.getTripRouteAndDirection(a));
			   
			   fareStages=tripdao.getFareStagess(a,route_order1,route_order2);
		}*/
		
		setListOfFareStages(tripdao.getAllFareStagesOfAllRoutesofFormFour(routenamess1));
		/*for (Map.Entry<Integer, String> entry : mapps.entrySet()) {
		   // System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		    int route_id=entry.getKey();
		    int a=route_id;
		    String bus_stop_id=entry.getValue();
		    
		    String star_point[]=bus_stop_id.split("@");
		    String startpoint=star_point[0];
		    String endpoint=star_point[1];
		    
		    
		   int route_order1=tripdao.getRouteOrderByRouteNo(route_id,startpoint);
		   int route_order2=tripdao.getRouteOrderByRouteNo(route_id,endpoint);
		   if(route_id==6670)
		   {
			   //System.out.println("j222="+j);
		 //  System.out.println("startpoint"+startpoint+"endpoint"+endpoint+"route_id"+route_id+"route_order1"+route_order1+"route_order2"+route_order2);
		   }
		    //show bus stop
			//System.out.println("route_id"+a);
			   List<String> stages=new ArrayList<String>();
			   if(j==0)
			   {
				   setRoutename0(tripdao.getTripRouteAndDirection(a));
				   
				   fareStages=tripdao.getFareStagess(a,route_order1,route_order2); 
			   }
			   if(j==1)
			   {
				   setRoutename1(tripdao.getTripRouteAndDirection(a));
				   fareStages1=tripdao.getFareStagess(a,route_order1,route_order2); 
			   }
			   if(j==2)
			   {
				   setRoutename2(tripdao.getTripRouteAndDirection(a));
				   fareStages2=tripdao.getFareStagess(a,route_order1,route_order2);    
				   
			   }
			   if(j==3)
			   {
				   setRoutename3(tripdao.getTripRouteAndDirection(a));
				   fareStages3=tripdao.getFareStagess(a,route_order1,route_order2);   
				   
			   }
			   if(j==4)
			   {
				   setRoutename4(tripdao.getTripRouteAndDirection(a));
				   fareStages4=tripdao.getFareStagess(a,route_order1,route_order2);   
				   System.out.println("fareStages4"+fareStages4.size());
			   }
			   if(j==5)
			   {
				   setRoutename5(tripdao.getTripRouteAndDirection(a));
				   fareStages5=tripdao.getFareStagess(a,route_order1,route_order2);   
				   System.out.println("fareStages5"+fareStages5.size());
			   }
			   if(j==6)
			   {
				   setRoutename6(tripdao.getTripRouteAndDirection(a));
				   fareStages6=tripdao.getFareStagess(a,route_order1,route_order2);  
				   //System.out.println("routeid"+a+"route_order1"+route_order1+""+route_order1);
				   System.out.println("fareStages6"+fareStages6.size());
				   
			   }
			   if(j==7)
			   {
				   setRoutename7(tripdao.getTripRouteAndDirection(a));
				   fareStages7=tripdao.getFareStagess(a,route_order1,route_order2);  
				   
			   }
			   if(j==8)
			   {
				   setRoutename8(tripdao.getTripRouteAndDirection(a));
				   fareStages8=tripdao.getFareStagess(a,route_order1,route_order2);  
				   
			   }
			   if(j==9)
			   {
				   setRoutename9(tripdao.getTripRouteAndDirection(a));
				   fareStages9=tripdao.getFareStagess(a,route_order1,route_order2);  
				   
			   }
			   if(j==10)
			   {
				   setRoutename10(tripdao.getTripRouteAndDirection(a));
				   fareStages10=tripdao.getFareStagess(a,route_order1,route_order2);  
				   
			   }
			   if(j==11)
			   {
				   setRoutename11(tripdao.getTripRouteAndDirection(a));
				   fareStages11=tripdao.getFareStagess(a,route_order1,route_order2);  
				   
			   }
			   if(j==12)
			   {
				   setRoutename12(tripdao.getTripRouteAndDirection(a));
				   fareStages12=tripdao.getFareStagess(a,route_order1,route_order2);  
				   
			   }
				stages=tripdao.getFareStagess(a,route_order1,route_order2);
				//System.out.println("stages size"+stages.size());
				//fareStages.addAll(stages);
				setStages(stages);
				finalstageslist.add(getStages());
				j++;
		   //end to show bus stop
		    
		    
		}*/
	
		/*for(int a:routelist)
		{
			System.out.println("route_id"+a);
		   List<String> stages=new ArrayList<String>();
		   if(j==0)
		   {
			   setRoutename0(tripdao.getTripRouteAndDirection(a));
			   
			   fareStages=tripdao.getFareStagess(a); 
		   }
		   if(j==1)
		   {
			   setRoutename1(tripdao.getTripRouteAndDirection(a));
			   fareStages1=tripdao.getFareStagess(a); 
		   }
		   if(j==2)
		   {
			   setRoutename2(tripdao.getTripRouteAndDirection(a));
			   fareStages2=tripdao.getFareStagess(a);    
			   
		   }
		   if(j==3)
		   {
			   setRoutename3(tripdao.getTripRouteAndDirection(a));
			   fareStages3=tripdao.getFareStagess(a);   
			   
		   }
		   if(j==4)
		   {
			   setRoutename4(tripdao.getTripRouteAndDirection(a));
			   fareStages4=tripdao.getFareStagess(a);   
			   
		   }
		   if(j==5)
		   {
			   setRoutename5(tripdao.getTripRouteAndDirection(a));
			   fareStages5=tripdao.getFareStagess(a);   
		   }
		   if(j==6)
		   {
			   setRoutename6(tripdao.getTripRouteAndDirection(a));
			   fareStages6=tripdao.getFareStagess(a);  
			   
		   }
		   if(j==7)
		   {
			   setRoutename7(tripdao.getTripRouteAndDirection(a));
			   fareStages7=tripdao.getFareStagess(a);  
			   
		   }
		   if(j==8)
		   {
			   setRoutename8(tripdao.getTripRouteAndDirection(a));
			   fareStages8=tripdao.getFareStagess(a);  
			   
		   }
		   if(j==9)
		   {
			   setRoutename9(tripdao.getTripRouteAndDirection(a));
			   fareStages9=tripdao.getFareStagess(a);  
			   
		   }
		   if(j==10)
		   {
			   setRoutename10(tripdao.getTripRouteAndDirection(a));
			   fareStages10=tripdao.getFareStagess(a);  
			   
		   }
		   if(j==11)
		   {
			   setRoutename11(tripdao.getTripRouteAndDirection(a));
			   fareStages11=tripdao.getFareStagess(a);  
			   
		   }
		   if(j==12)
		   {
			   setRoutename12(tripdao.getTripRouteAndDirection(a));
			   fareStages12=tripdao.getFareStagess(a);  
			   
		   }
			stages=tripdao.getFareStagess(a);
			System.out.println("stages size"+stages.size());
			//fareStages.addAll(stages);
			setStages(stages);
			finalstageslist.add(getStages());
			j++;
			
		}*/
		double sch11=Double.parseDouble(formatter.format(sch1));
		double sch22=Double.parseDouble(formatter.format(sch2));
		double dead11=Double.parseDouble(formatter.format(dead1));
		double dead22=Double.parseDouble(formatter.format(dead2));
		double totaldead=dead11+dead22;
	
		double total=Double.parseDouble(formatter.format(sch11+sch22+dead11+dead22));
		double actual_km=Double.parseDouble(formatter.format(total-totaldead));;
		
		formFour.setSchkms1(Double.parseDouble(formatter.format(sch1)));
		formFour.setSchkms2(Double.parseDouble(formatter.format(sch2)));
		formFour.setDead1(Double.parseDouble(formatter.format(dead1)));
		formFour.setDead2(Double.parseDouble(formatter.format(dead2)));
		formFour.setTotalKm(total);
		if(formFour.getId()!=0){
			int count=tripdao.UpdateFormFourData(formFour.getId(),total,actual_km);
		
		}
		//System.out.println("route_id....09-15......."+formFour.getFormFourRoute().getRoute_id());
		//fareStages = tripdao.getFareStages(formFour.getFormFourRoute().getRoute_id());
		//setFareStages(finalstageslist);
		setFinalstageslist(finalstageslist);
		
		
		
		
		for (Map.Entry<Integer, String> entry : mapp.entrySet()) {
			//System.out.println("map" +entry.getValue());
		}
		/*for(int i=0;i<finalstageslist.size();i++)
		{
			System.out.println("stages size1"+((List<ScheduleDetails>) finalstageslist.get(i)).size());
		}*/
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	
public String saveTripView(){
	

		
		Locale fmtLocale = Locale.getDefault();
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);
		
		double sch1=0.00;
		double sch2=0.00;
		double dead1=0.00;
		double dead2=0.00;
		Common common = new Common();
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
		int id = 0;
		if(session.getAttribute("id") != null){
		id = (Integer) session.getAttribute("id");
		}
		if(id==0){
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		formFour = tripdao.getFormFour(id);
	
		Schedule schedule = tripdao.getSchedule(formFour.getScheduleNumber().getSchedule_id());
		BrandType brandtype = tripdao.getBrand(schedule.getBrand().getBrand_type_id());
		setBrand(brandtype.getBrand_type_name());
		Route_Trans route = tripdao.getRoute(formFour.getFormFourRoute().getRoute_id());
		
		setService(schedule.getServicetype().getServiceTypeName());
		setRouteNumber(route.getRouteNumber());
		setRouteName(route.getRouteName());
		setSchedule(schedule.getScheduleNumber());
		setFormfourname(formFour.getScheduleNumberName());
		setDivision(tripdao.getDivision(schedule.getDepot().getParent_id()));
		setDepot(schedule.getDepot().getOrg_name());
		setShift(schedule.getScheduletype().getScheduleName());
		setEffectstartdate(common.getDateInDDMMYY((formFour.getEffectiveStartDate())));
		String traffic_order = getTraffic_order();
		String recDate = formFour.getRecordDate();
//		setTraffic_order(fdao.getTrafficOrderNo(formfourId));
				System.out.println("traffic nd redord in Save trip view==========="+traffic_order+"------"+recDate);
		if(schedule.getScheduletype().getScheduleCode().equalsIgnoreCase("NO")){
			setShiftHeader1("Day 1");
			setShiftHeader2("Day 2");
			setScheduleCode(1);
			}
			else{
				setShiftHeader1("Shift 1");
				setShiftHeader2("Shift 2");
				setScheduleCode(0);
			}
		tripslist = tripdao.getTripDetails(formFour.getId());
		try{
		
		for(int i=0;i<tripslist.size();i++){
			tripslist.get(i).setOrigin(tripdao.getBusStopName(tripslist.get(i).getStartPoint()));
			tripslist.get(i).setDestination(tripdao.getBusStopName(tripslist.get(i).getEndPoint()));
			//System.out.println("tripslist.get(i).getStartTime() ----> "+tripslist.get(i).getStartTime());
			tripslist.get(i).setRouteNo(tripslist.get(i).getRouteNumber().getRouteNumber());
			if(tripslist.get(i).getStartTime().toString().equals("23:59:00")){
				tripslist.get(i).setStarttimeString("24:00");
			}
			else{
			tripslist.get(i).setStarttimeString(common.getDateInHHMM(tripslist.get(i).getStartTime()));
			}
			
			if(tripslist.get(i).getEndTime().toString().equals("23:59:00")){
				tripslist.get(i).setEndtimeString("24:00");
			}
			else{
				tripslist.get(i).setEndtimeString(common.getDateInHHMM(tripslist.get(i).getEndTime()));
			}
			
			
			tripslist.get(i).setJourneyTime(common.getDateInHHMM(tripslist.get(i).getRunningTime()));
			tripslist.get(i).setBreaktimeString(common.getDateInHHMM(tripslist.get(i).getBreakTime()));
			
			
			
			if(crewChangeFlag == 0){
				if(tripslist.get(i).getTripType().getTripTypeCode().equals("DT")){
					dead1 = dead1 + tripslist.get(i).getDistance();
				}
				else{
				sch1 = sch1+tripslist.get(i).getDistance();
				}
			}
			else{
				if(tripslist.get(i).getTripType().getTripTypeCode().equals("DT")){
					dead2 = dead2 + tripslist.get(i).getDistance();
				}
				else{
				sch2 = sch2+tripslist.get(i).getDistance();
				}
			}
			if(tripslist.get(i).getCrewChange()==1 || tripslist.get(i).getNightHalt()==1){
				crewChangeFlag = 1;
				setCrewChangeId(i+1);
				if(tripslist.get(i).getNightHalt()==1){
				nightHaltLocation = tripslist.get(i).getCrewChangeLocation();
				if(nightHaltLocation.equals("") || nightHaltLocation.equals(null)){
					nightHaltLocation = tripslist.get(i).getNightHaltLocation();	
				}
				}
			}
		}
		formFour.setSchkms1(Double.parseDouble(formatter.format(sch1)));
		formFour.setSchkms2(Double.parseDouble(formatter.format(sch2)));
		formFour.setDead1(Double.parseDouble(formatter.format(dead1)));
		formFour.setDead2(Double.parseDouble(formatter.format(dead2)));
		
		fareStages = tripdao.getFareStages(formFour.getFormFourRoute().getRoute_id());
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	
	public String deleteFormFour(){
	
	HttpServletRequest request=ServletActionContext.getRequest();
	String id = (String) request.getParameter("id");
	//System.out.println("idd ---> "+id);
	FormFourDAO formfourdao = new FormFourDAO();
	formfourdao.deleteFormFour(Integer.parseInt(id));
	return null;
}

	public String getTraffic_order() {
		return traffic_order;
	}

	public void setTraffic_order(String traffic_order) {
		this.traffic_order = traffic_order;
	}

//	public String getRecord_date() {
//		return record_date;
//	}
//
//	public void setRecord_date(String record_date) {
//		this.record_date = record_date;
//	}
}
