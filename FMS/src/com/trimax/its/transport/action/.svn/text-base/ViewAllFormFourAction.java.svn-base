
package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.transport.dao.FormFourDAO;
import com.trimax.its.transport.dao.TripDAO;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.FormFourType;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ViewAllFormFourAction extends ActionSupport {
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

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
	public FormFour getFormFour() {
		return formFour;
	}

	public void setFormFour(FormFour formFour) {
		this.formFour = formFour;
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

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
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

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getFormfourname() {
		return formfourname;
	}

	public void setFormfourname(String formfourname) {
		this.formfourname = formfourname;
	}

	public List<ScheduleDetails> getTripslist() {
		return tripslist;
	}

	public void setTripslist(List<ScheduleDetails> tripslist) {
		this.tripslist = tripslist;
	}

	public List<ScheduleDetails> getTripslist1() {
		return tripslist1;
	}

	public void setTripslist1(List<ScheduleDetails> tripslist1) {
		this.tripslist1 = tripslist1;
	}

	public int getCrewChangeFlag() {
		return crewChangeFlag;
	}

	public void setCrewChangeFlag(int crewChangeFlag) {
		this.crewChangeFlag = crewChangeFlag;
	}

	public String getShiftHeader1() {
		return shiftHeader1;
	}

	public void setShiftHeader1(String shiftHeader1) {
		this.shiftHeader1 = shiftHeader1;
	}

	public String getShiftHeader2() {
		return shiftHeader2;
	}

	public void setShiftHeader2(String shiftHeader2) {
		this.shiftHeader2 = shiftHeader2;
	}

	public String getNightHaltLocation() {
		return nightHaltLocation;
	}

	public void setNightHaltLocation(String nightHaltLocation) {
		this.nightHaltLocation = nightHaltLocation;
	}

	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public int getCrewChangeId() {
		return crewChangeId;
	}

	public void setCrewChangeId(int crewChangeId) {
		this.crewChangeId = crewChangeId;
	}

	public List<String> getFareStages() {
		return fareStages;
	}

	public void setFareStages(List<String> fareStages) {
		this.fareStages = fareStages;
	}

	public List<String> getStages() {
		return stages;
	}

	public void setStages(List<String> stages) {
		this.stages = stages;
	}

	public List getFinalstageslist() {
		return finalstageslist;
	}

	public void setFinalstageslist(List finalstageslist) {
		this.finalstageslist = finalstageslist;
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

	public List<String> getFareStages8() {
		return fareStages8;
	}

	public void setFareStages8(List<String> fareStages8) {
		this.fareStages8 = fareStages8;
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

	public List<List<String>> getListOfFareStages() {
		return listOfFareStages;
	}

	public void setListOfFareStages(List<List<String>> listOfFareStages) {
		this.listOfFareStages = listOfFareStages;
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

	/*public void setMapSession(Map<String, Integer> mapSession) {
		this.mapSession = mapSession;
	}*/
	private Map<Integer,Object> scheduletype;
	public Map<Integer,Object> getScheduletype() {
		return scheduletype;
	}

	public void setScheduletype(Map<Integer,Object> scheduletype) {
		this.scheduletype = scheduletype;
	}

	/*public void setMapSession(Map<String, Integer> mapSession) {
		this.mapSession = mapSession;
	}*/

	private Map<Integer, String> formfourtypelist;
	
	
	private int id;
	Map<String, Integer> mapSession;
	

	
	public Map getMapSession() {
		return mapSession;
	}

	public void setMapSession(Map mapSession) {
		this.mapSession = mapSession;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, String> getFormfourtypelist() {
		return formfourtypelist;
	}

	public void setFormfourtypelist(Map<Integer, String> formfourtypelist) {
		this.formfourtypelist = formfourtypelist;
	}
	public String getdepot(){

		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        
        if(orgtypeid.equals("2")){
//        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
        		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
        		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
        		String regionTypeAjaxString = "";
        		regionTypeAjaxString += "<option value='0'>------Select------</option>";
        		for (int i = 0; i < l1.size(); i++) {
        			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
        					+ ">" + l2.get(i).toString() + "</option>";
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
//        		return null;
//        	}
		
        }
        
      
        else if(orgtypeid.equals("1") && orgchartid.equals("1")){
        	List<String> l1 = dao.getDepotId(parentId);
    		List<String> l2 = dao.getDepotName(parentId);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='0'>------Select------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
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
        }

        else {
        	List<String> l1 = dao.getDepotId(parentId,orgchartid);
    		List<String> l2 = dao.getDepotName(parentId,orgchartid);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='0'>------Select------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
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
        }
        
		return null;

	
	}
	public String execute() {
		//this.ServiceTaxCollections();
		//VtsDataDAO vvt = VtsDataDAO.getInstance();
		formfourtypelist = getFormFourType();
		//VtsDataDAO vvt = VtsDataDAO.getInstance();
		//divisionlist = vvt.getDivisionName();
		scheduletype=sheduletype();
		try{
			 
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			

			HttpServletRequest req = ServletActionContext.getRequest();
			System.out.println(req.getSession().getAttribute("orgtypeid")+"------"+req.getSession().getAttribute("orgchartid"));
			 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
//			VtsDataDAO vvt = VtsDataDAO.getInstance();
			if(orgtypeid.equals("2")){
	        	//Our Logic
			int parentId=0;
			try {
			} catch (Exception ex) {

			}
			try {
				parentId = vvt.getDepot1DC(orgtypeid,orgchartid);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId);
			//Ends..
	        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
			divisionlist = vvt.getDivisionName();
			//schedulelist = vvt.getScheduleName();
	        }else{
	        	int parentId=0;
				try {
				} catch (Exception ex) {

				}
				try {
					System.out.println(orgtypeid+"====="+orgchartid);
					parentId = vvt.getDepot1(orgtypeid,orgchartid);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public Map<Integer, String> getFormFourType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from FormFourType where status='ACTIVE' and deleted_status = '0'");
		try {
			List<FormFourType> list = query.list();
			for (FormFourType org : list) {
				resultMap.put(org.getId(), org.getFormFourTypeName());
			}
			System.out.println("resultMap=-="+resultMap);
		} catch (Exception ex) {
		} finally {
//			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	public String getAllFormFourDetails(){
		System.out.println("getAllFormFourDetails===");
		HttpServletRequest request=ServletActionContext.getRequest();
		try{
			
			
			if(request.getParameter("formfourtype") != null){
				id = Integer.parseInt(request.getParameter("formfourtype"));
				
			}
			else{
				id = Integer.parseInt(request.getSession().getAttribute("formfourtype").toString());
			}
			 System.out.println("id==="+id);
//			schedule = scheduledao.getSchedule(id);
//			mapSession.put("id", id);
			 request.setAttribute("id", id);
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		
		
		return "success";
	}

	public List<Integer> getAllFormFourID(int id){
		int  cnt=0;
		
		List<Integer> list=new ArrayList<Integer>();
		try{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		Transaction transaction = null;
		transaction = session.beginTransaction();
		String sql="SELECT form_four_id FROM `form_four`WHERE `form_four_name` ="+id+" and current_status='ACTIVE' and deleted_status=0 limit 1";
		 Query query1 = session.createSQLQuery(sql).addScalar("form_four_id");;
		 
		 query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query1.list();
			System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());
			for (int j = 0; j < aliasToValueMapList.size(); j++) {
				Map<String, Object> aliasValue = aliasToValueMapList.get(j);
				 list.add(Integer.parseInt(aliasValue.get("form_four_id").toString()));
				
			}
			 System.out.println("list=========>"+list);
			
		}catch(Exception e){
			e.printStackTrace();
	}
		
		return list;
		
	}
	public void showbyquery(){
		HttpServletRequest req=ServletActionContext.getRequest();
		try{
			String fid=req.getParameter("id");
			String dept=req.getParameter("depot");
			String schid=req.getParameter("schid");
			System.out.println("======="+fid+dept);
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	
		String qry="select st.service_type_name,s.schedule_number,ff.schedule_number_name,oc.org_name,ifnull(traffic_order_no,'') traffic_order_no,sd.trip_number,sd.distance,sd.start_time,sd.end_time,sd.running_time,"+
"s.schedule_type,sd.number_of_trips,ff.no_of_trips,r.route_number,sd.route_number_id,r.route_name,ifnull((select route_number from route where route_id=sd.route_number_id),'') sdroutename," +
"ifnull(sht.shift_type_name,'')shift_type_name,scht.schedule_type_name,ff.effective_start_date,"+
"sd.break_time,bt.break_type_name,sd.is_dread_trip,sd.crew_change_location,ifnull(sd.night_halt_location,'') night_halt_location,sd.start_point,sd.end_point,r.route_name,"+//#,r.route_direction,
"ifnull((select route_direction from route where route_id=sd.route_number_id),'') diraction,ff.actual_km,ff.total_dead_km,ff.total_km,"+
"bs.bus_stop_name,ifnull(sd.operation_type,'') operation_type,ifnull(sd.remarks,'') remarks,sd.crew_change,sd.night_halt,"+
"(select bus_stop_name from bus_stop where bus_stop_id=sd.end_point) endpoint,"+
"ifnull(ff.spread1,'') spread1,ifnull(ff.spread2,'') spread2,ifnull(ff.steering1,'') steering1,ifnull(ff.steering2,'') steering2,ff.ot1,ff.ot2,ifnull(ff.rest1,'') rest1,ifnull(ff.rest2,'') rest2 "+
//"(case when sd.night_halt=sd.route_number_id then r.route_direction ELSE 'Down' end) diraction "+
"from form_four ff "+ 
"inner join schedule_details sd on ff.form_four_id=sd.form_four_id "+
"inner join schedule s on sd.schedule_number=s.schedule_id "+
"left join service_type st on s.schedule_service_type=st.service_type_id "+
"left join org_chart oc on s.depot_id=oc.org_chart_id "+
"left join form_four_traffic_order fto on ff.form_four_Id=fto.form_four_Id "+
"left join  bus_stop  bs on bs.bus_stop_id=sd.start_point "+//# or bs.bus_stop_id=sd.end_point
"left join route r on r.route_id=ff.route_id "+
"left join break_type bt on sd.break_type_id=bt.break_type_id "+
"left join shift_type sht on sd.shift_type_id=sht.shift_type_id "+
"left join schedule_type scht on s.schedule_type=scht.schedule_type_id "+
"where ff.deleted_status=0 and ff.current_status='ACTIVE' and ff.form_four_name="+fid+" and oc.org_chart_id="+dept+" and scht.schedule_type_id="+schid+" and sd.deleted_status=0 and r.status='Active' and r.deleted_status=0";
//	System.out.println("======="+qry);
		Query query=session.createSQLQuery(qry);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	
	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>view all schedule form four </br>From Date:- "+""+" To Date:-"+""+"</br></h4></div>");

	regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	//int lo=0;
	if(aliasToValueMapList.size()==0){regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<h4><tr bgcolor="+"#ddd"+"><td colspan='14'><center> No Data Found</center></td></tr></h4></table></div>");
	}
	for(int ma=0;ma<aliasToValueMapList.size();ma++){
		Set<String> fair=new LinkedHashSet();
		String haltloc="";
		System.out.println("ma---"+ma);
	Map<String, Object> list = aliasToValueMapList.get(ma);
//System.out.println("no of trips----"+Integer.parseInt(list.get("number_of_trips").toString()));
	regionTypeAjaxString1.append("<div class='component'><h4><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<tr bgcolor="+"#ddd"+"><td><b>Service Type:</b>"+list.get("service_type_name").toString()+"</td>"+
			"<td><b>Form Four Name:</b>"+list.get("schedule_number_name").toString()+"</td><td><b>Depot:</b>"+list.get("org_name").toString()+"</td><td></td></tr><tr bgcolor="+"#ddd"+"><td><b>Route Number:</b>"+list.get("route_number").toString()+"</td>" +
				"<td><b>Route Number:</b>"+list.get("route_number").toString()+"</td><td><b>route_name:</b>"+list.get("route_name").toString().trim()+
				"</td><td></td></tr><tr bgcolor="+"#ddd"+"><td><b>Schedule Number:</b>"+list.get("schedule_number").toString()+"</td><td><b>Traffic Order No:</b>"+list.get("traffic_order_no").toString()+
				"</td><td><b>Schedule Type:</b>"+list.get("schedule_type_name").toString()+"</td><td><b>wef:</b>"+list.get("effective_start_date").toString()+
				"</td></tr></table></h4>");
	/*regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<tr bgcolor="+"#AFEEEE"+"><td>Sr No</td><td>OrgName</td><td>Org chat Id</td><td>Device Id</td><td>Waybill No</td><td>Total Transaction</td>" +
			"<td>Total Amount</td><td>Total Card Amount</td><td>Terminal Id</td><td>Status</td>" +
				""+"</tr><tbody></tbody></table></table>");*/
	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>List</th><th>Trip</th><th>Trip</th><th>Customer</th><th>Place</th><th>Place</th><th>Route</th>" +
			"<th>Distance</th><th>Timings</th><th>Timings</th><th>Running</th><th>Break</th><th>Break</th><th>Is Crew</th><th>Crew Change</th>" +
			"<th>Is Night</th><th>Duty</th><th>Operation</th><th>Remarks</th>"+	
			""+"</tr>");
	regionTypeAjaxString1.append("<tr><th>Item No.</th><th>No.</th><th>Type</th><th>Name</th><th>Start Point</th><th>End Point</th><th>Number</th>" +
			"<th></th><th>Start Time</th><th>End Time</th><th>Time</th><th>Type</th><th>Time</th><th>Change</th><th>Location</th>" +
			"<th>Halt</th><th>Type</th><th>Type</th><th></th>"+	
			""+"</tr></thead><tbody>");
	int lo2=ma;
	int si=ma+Integer.parseInt(list.get("no_of_trips").toString());
	int sl=1;
	for(int j=lo2;j<si;j++){
//	System.out.println("j loop======"+j);
		
		Map<String, Object> list1 = aliasToValueMapList.get(j);
fair.add((list1.get("route_number_id").toString()));
if(Integer.parseInt(list1.get("night_halt").toString())==1){haltloc=list1.get("night_halt_location").toString();}
	regionTypeAjaxString1.append("<tr>");
	regionTypeAjaxString1.append("<td align='right'>"+sl+"</td>");
	regionTypeAjaxString1.append("<td align='right'>"+list1.get("trip_number").toString() +"</td>");
	if(Integer.parseInt(list1.get("is_dread_trip").toString())==0){
	regionTypeAjaxString1.append("<td align='right'>"+"Regular Trip"+"</td>");}
	else{regionTypeAjaxString1.append("<td align='right'>"+"Dead Trip"+"</td>");}
	regionTypeAjaxString1.append("<td align='right'>"+"BMTC"+"</td>");
regionTypeAjaxString1.append("<td align='right'>"+list1.get("bus_stop_name").toString() +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("endpoint").toString() +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("sdroutename").toString()+"-"+list1.get("diraction").toString()+"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("distance").toString() +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("start_time").toString() +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+list1.get("end_time").toString()  +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("running_time").toString()  +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("break_type_name").toString()  +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("break_time").toString()  +"</td>");
if(Integer.parseInt(list1.get("crew_change").toString())==1){
	regionTypeAjaxString1.append("<td align='right'>"+"Yes"+"</td>");
}
else{regionTypeAjaxString1.append("<td align='right'>"+"No"+"</td>");}
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("crew_change_location").toString()  +"</td>");
if(Integer.parseInt(list1.get("night_halt").toString())==1){
	regionTypeAjaxString1.append("<td align='right'>"+"Yes"+"</td>");
}else{
	regionTypeAjaxString1.append("<td align='right'>"+"No"+"</td>");
}
//regionTypeAjaxString1.append("<td align='right'>"+ list1.get("night_halt_location").toString()  +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("shift_type_name").toString()  +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("operation_type").toString()  +"</td>");
regionTypeAjaxString1.append("<td align='right'>"+ list1.get("remarks").toString()  +"</td>"); 
regionTypeAjaxString1.append("</tr>");
if(Integer.parseInt(list1.get("night_halt").toString())==1 || Integer.parseInt(list1.get("crew_change").toString())==1){
	regionTypeAjaxString1.append("<tr><td colspan='14'><center>CREW CHANGE, FUELLING MAINTENANCE AT DEPOT</center></td></tr>");}

sl++;
ma++;}regionTypeAjaxString1.append("</tbody></table></div>");
Map<String, Object> list2 = aliasToValueMapList.get(ma-2);
Double shiftd=Double.parseDouble(list2.get("actual_km").toString());
Double deadk=Double.parseDouble(list2.get("total_dead_km").toString());
Double shiftdist=Double.parseDouble(list2.get("actual_km").toString())/2;
Double deadkm=Double.parseDouble(list2.get("total_dead_km").toString())/2;
String shiftday="";
String shiftdis="";
String shiftdea="";
if(Integer.parseInt(list2.get("schedule_type").toString())==2){
	shiftday="<td>day1</td><td>day2</td>";
}else{
	shiftday="<td>shift1</td><td>shift2</td>";
}
if(Integer.parseInt(list2.get("schedule_type").toString())==2 || Integer.parseInt(list2.get("schedule_type").toString())==3){
	shiftdis="<td>"+shiftdist+"</td><td>"+shiftdist+"</td>"; 
	shiftdea="<td>"+deadkm+"</td><td>"+deadkm+"</td>";
}
else{
	shiftdis="<td>"+shiftd+"</td><td>"+"0.0"+"</td>"; 
	shiftdea="<td>"+deadk+"</td><td>"+"0.0"+"</td>";
}/*else{ shiftdis="<td>"+shiftdist+"</td><td>"+shiftdist+"</td>"; 
shiftdea="<td>"+deadkm+"</td><td>"+deadkm+"</td>";
}*/
	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<tr bgcolor="+"lightblue"+"><td colspan='3'><center>VEHICLE UTILISATION</center></td><td colspan='3'><center>CREW DUTY HOURS</center></td><td colspan='2'><center>REST FOR CREW</center></td></tr>" +
			"<tr><td>------</td>"+shiftday+"<td> ----</td>"+shiftday+"<td colspan='3'></td></tr>" +
			"<tr><td>SCH.KMS</td>"+shiftdis+"<td>SPREAD</td><td>"+list2.get("spread1").toString()+"</td><td>"+list2.get("spread2").toString()+"</td><td>"+list2.get("rest1").toString()+"</td></tr>"+	
			"<tr><td>DEAD.KMS</td>"+shiftdea+"<td>STEERING</td><td>"+list2.get("steering1").toString()+"</td><td>"+list2.get("steering2").toString()+"</td><td>"+list2.get("rest2").toString()+"</td></tr>"+	
			"<tr><td>TOTAL KMS</td><td>"+list2.get("total_km").toString()+"</td><td></td><td>O.T.</td><td>"+list2.get("ot1").toString()+"</td><td>"+list2.get("ot2").toString()+"</td><td>Halt at:"+haltloc+"</td>"+
			""+"</tr></table></div>");
	ma=ma-1;
	System.out.println(fair);
	List<Map<String, Object>> aliasToValueMapList1=getroutefairs(fair);
//	System.out.println("size =========="+aliasToValueMapList1.size());
	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<tr>");
	String comp="";
	int cou=0;
	for(int fa=0;fa<aliasToValueMapList1.size();fa++){
		Map<String, Object> list1 = aliasToValueMapList1.get(fa);
		if(fa==0){regionTypeAjaxString1.append("<td>");
			//Map<String, Object> list1 = aliasToValueMapList1.get(0);
			comp=list1.get("routename").toString();
			regionTypeAjaxString1.append("<b>"+comp+"</b>");
		}
		if(comp.equals(list1.get("routename").toString())){//System.out.println("------------------------------------");
		
			regionTypeAjaxString1.append("<br>"+list1.get("bus_stop_name").toString());
		}else{
			regionTypeAjaxString1.append("</td>");
			comp=list1.get("routename").toString();
		
			//System.out.println("====================================================================");
			regionTypeAjaxString1.append("<td>");
			regionTypeAjaxString1.append("<b>"+comp+"</b><br>");
			regionTypeAjaxString1.append(list1.get("bus_stop_name").toString());
			}
	//fa++;
	}
	//if(comp.equals("") || comp)
	regionTypeAjaxString1.append("</tr></table></div>");
	}
	 PrintWriter out;
	 HttpServletResponse response = ServletActionContext.getResponse();

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public List<Map<String, Object>> getroutefairs(Set<String> fair){
		//LinkedHashSet setref=(LinkedHashSet) l;
		List<String> setref=new ArrayList(fair);
		String passint="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		for(int i=0;i<fair.size();i++){
			 if(i==0){passint=setref.get(0);
					 }else{
						 passint=passint+","+setref.get(i);
			 }
		}
		String qry="select CONCAT(r.route_number,'-',r.route_direction) routename,bs.bus_stop_name,rp.fare_stage,route_order  from route r inner join route_point rp on r.route_id = rp.route_id "+
                    "inner join bus_stop bs on bs.bus_stop_id = rp.bus_stop_id where r.route_id in("+passint+") and r.deleted_status=0 and rp.fare_stage='Y'"+
                     "and r.route_type_id not in(7,5) order by r.route_id,rp.route_order";
		Query query=session.createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		//System.out.println("called med"+aliasToValueMapList);
		return aliasToValueMapList;
	}
	public Map<Integer, Object> sheduletype(){
		Map<Integer, Object> l=new HashMap<Integer, Object>();
		try{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select schedule_type_id,schedule_type_name  from schedule_type where deleted_status=0";
		Query query=session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<Integer, Object>> aliasToValueMapList = query.list();
		StringBuffer b=new StringBuffer("");
		b.append("<option value='0'>------select------</option>");
		System.out.println(aliasToValueMapList);
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<Integer, Object> li = aliasToValueMapList.get(i);
			l.put(Integer.parseInt(li.get("schedule_type_id").toString()),li.get("schedule_type_name"));
			/*b.append("<option value=" + l.get("schedule_type_id").toString()
					+ ">" + l.get("schedule_type_name").toString() + "</option>");*/
		}
		
		System.out.println(l);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return l;
		
	}
}
