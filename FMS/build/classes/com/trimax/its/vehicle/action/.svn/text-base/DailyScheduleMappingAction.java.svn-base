package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vehicle.model.ScheduleMap;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DailyScheduleMappingAction extends ActionSupport {
	
	private Map<Integer, String> divisionlist;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String division;
	public String depot;
	public String startdate;
	public String value;
	
	public String schedule_no;
	public String shiftName;
	public String vehicle_no;
	public String schedule_id;
	public String depot_id;
	public String vehiclelist;
	
	public String division1;
	public String depot1;
	public String startdate1;
	
	public String vehicleno;
	
	public String date;

	ScheduleMapDAO dao= new ScheduleMapDAO();
	
	private Map<Integer, String> triplist;

	public String reasondata;
	
	
	
	public String getReasondata() {
		return reasondata;
	}

	public void setReasondata(String reasondata) {
		this.reasondata = reasondata;
	}

	public Map<Integer, String> getTriplist() {
		return triplist;
	}

	public void setTriplist(Map<Integer, String> triplist) {
		this.triplist = triplist;
	}

	public String getDivision1() {
		return division1;
	}

	public void setDivision1(String division1) {
		this.division1 = division1;
	}

	public String getDepot1() {
		return depot1;
	}

	public void setDepot1(String depot1) {
		this.depot1 = depot1;
	}

	public String getStartdate1() {
		return startdate1;
	}

	public void setStartdate1(String startdate1) {
		this.startdate1 = startdate1;
	}

	public String getVehiclelist() {
		return vehiclelist;
	}

	public void setVehiclelist(String vehiclelist) {
		this.vehiclelist = vehiclelist;
	}

	public String getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(String depot_id) {
		this.depot_id = depot_id;
	}

	public String getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(String schedule_no) {
		this.schedule_no = schedule_no;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	
	public String execute()
	{
//		this.setModelTypeList(daoObject.getModelTypeList());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");
//		System.out.println("orgtypeid==="+orgtypeid);
//		System.out.println("orgchartid==="+orgchartid);
		
		String id = "!=0";
		if (orgtypeid.equals("1")) {
			id = "org_type_id=2 and org_chart_id!=0";

		}
		if (orgtypeid.equals("3")) {

			id = "org_chart_id in (select parent_id from org_chart where org_chart_id='"
					+ orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			id = "org_chart_id in ('"+ orgchartid + "')";
		}
//		System.out.println("id======="+id);
		getDivision(orgtypeid,id);
		
		
//		
//		 if(orgtypeid.equals("2")){
//	        	VtsDataDAO vvt = VtsDataDAO.getInstance();
//	        	int parentId1=0;
//	        	Date date = new Date();
//	     		Date date1 = new Date();
//	     		//Date addedDate2 = addDays(date1, 1);
//	     		int parentId=0;
//
//				try {
//					
//					parentId1 = vvt.getDepot1DC(orgtypeid,orgchartid);
//					System.out.println("parentId---"+parentId1);
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				//System.out.println("getDepot1DC--");
//				divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId1);
//				
//				
//		
//	        }
//	         
//	        else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
//	        	VtsDataDAO vvt = VtsDataDAO.getInstance();
//	    		Date date = new Date();
//	    		Date date1 = new Date();
//	    		try {
//	    			divisionlist = vvt.getDepot();
//
//	    		} catch (Exception e) {
//	    			// TODO Auto-generated catch block
//	    			e.printStackTrace();
//	    		}
//	    		divisionlist = vvt.getDivisionName();
//	        }
//	         
//	        else{
//	         	VtsDataDAO vvt = VtsDataDAO.getInstance();
//	 		int parentId=0;
//
//	 		try {
//	 			parentId = vvt.getDepot1(orgtypeid,orgchartid);
//	 			
//	 		} catch (Exception e) {
//	 			// TODO Auto-generated catch block
//	 			e.printStackTrace();
//	 		}
//	 		divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
//	 		//Ends..
//	         }
		
		
		
		return "success";
	}
	
	public String getDivision(String orgtypeid,String id){
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivision(orgtypeid,id);

		return "success";
	}
	
	public String getDailyScheduleView(){
	
		ScheduleMapDAO dao= new ScheduleMapDAO();
		int count=0;
		int count1=0;
		int deletecount=0;
		int norecord=0;
		int norecord1=0;
		String msg1="";
		try{
//			System.out.println("startdate==="+startdate);
//			System.out.println("division===="+division);
//			System.out.println("depot===="+depot);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
		
		Common common=new Common();
//		String date1 = common.getDateFromPicker(startdate);
		CollectionReportDAO dao1=new CollectionReportDAO();
		
		if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("null")){
			
		}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("0")){
		}else{
			String msg=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString();
//			addActionMessage(msg);
			if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2").toString().equalsIgnoreCase("null")){
				
			}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2").toString().equalsIgnoreCase("0")){
			}else{
				msg1=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2").toString();
//				addActionMessage(msg1);
			}
			addActionMessage(msg);
			addActionMessage(msg1);
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","null");
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage2","null");
		}
		
		if(startdate==null || startdate.equalsIgnoreCase("null")){
			startdate=session.getAttribute("date").toString();
		}
		if(depot==null || depot.equalsIgnoreCase("null")){
			depot=session.getAttribute("depot").toString();
		}
		if(division==null || division.equalsIgnoreCase("null")){
			division=session.getAttribute("division").toString();
		}
//		System.out.println("startdate==="+startdate);
//		System.out.println("division===="+division);
//		System.out.println("depot===="+depot);
		String date=dao1.getDateFromPickerDate(startdate);
//			System.out.println("date====="+date);
//			setStartdate1(date);
//			setDivision1(division);
//			setDepot1(depot);
//		HttpSession session = request.getSession();
		
		session.setAttribute("date", startdate);
		session.setAttribute("depot", depot);
		session.setAttribute("division", division);
		norecord=dao.getDailyScheduleData(date,depot);
		norecord1=dao.getScheduleMappingCountRecords(depot);
			System.out.println("count===="+norecord+"=="+norecord1);
//			if(norecord==norecord1){
			if(norecord>0){
				
			}else{
				//deletecount=dao.deleteDailyScheduleMapping(depot,date);
			    count=dao.getAllDataIntoSchedule(depot,date);
			   
			}
//			System.out.println("count====="+count);	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "success";
	}
	
	public String getDailySchedule() throws IOException{
		
		Session session = null;
		String value=null;
		CollectionReportDAO dao1=new CollectionReportDAO();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session1 = request.getSession();
		String date="";
		try{
			if(session1 !=null){
			String selecteddate=session1.getAttribute("date").toString();
//			System.out.println("Date1======"+selecteddate);
			 date=dao1.getDateFromPickerDate(selecteddate);
			}else{
				value="fail";
			}
//			System.out.println("Converted Date======"+date);
//		System.out.println("getDepot1=="+getDepot1()+"  getStartdate1=="+getStartdate1()+" getDivision1=="+getDivision1());
//		setDepot1(getDepot1());
//		setStartdate1(getStartdate1());
//		setDivision1(getDivision1());
//		PrintWriter out = null;
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		ScheduleMapDAO dao= new ScheduleMapDAO();
		//String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		//System.out.println(viewdeletedrecord);
//		JSONObject result = new JSONObject();
		//String selectedstartdate = req.getParameter("selectedstartdate").toString();
		//String selectedenddate = req.getParameter("selectedenddate").toString();
//		String Scheduleno = req.getParameter("scheduleNo");
//		String depotName = req.getParameter("depotName");
//		System.out.println("depotName----"+depotName);
//		Common cm = new Common();
//		String formattedgivendate = cm.getDateFromPicker(selectedstartdate);
//		String formattedgivenenddate = cm.getDateFromPicker(selectedenddate);
		// TODO initialize WS operation arguments here

		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			String depotNo = request.getParameter("depotNo");
		
			
			
			
//			String[] dbcol = {"","schedule_no","schedule_name","duty Type","shift_type",
//					"vehicle_no","depot_name","division"};
			JSONObject result = new JSONObject();
//			int amount = 10;
//			int start = 0;
//			int col = 0;
			String dir = "asc";
			//String sStart = request.getParameter("iDisplayStart");
			//String sAmount = request.getParameter("iDisplayLength");
//			String sCol = request.getParameter("iSortCol_0");
//			String sdir = request.getParameter("sSortDir_0");

//			if (sStart != null) {
//				start = Integer.parseInt(sStart);
//				if (start < 0) {
//					start = 0;
//				}
//			}
//			if (sAmount != null) {
//				amount = Integer.parseInt(sAmount);
//				if (amount < 10 || amount > 50) {
//					amount = 10;
//				}
//			}
//			if (sCol != null) {
//				col = Integer.parseInt(sCol);
//				if (col < 0 || col > 5)
//					col = 0;
//			}
//			if (sdir != null) {
//				if (!sdir.equals("asc"))
//					dir = "desc";
//			}

//			String colName = dbcol[col];
//			int total = -1;
//			total = dao.getTotalRecordsData(total, request);
//			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
//			COL_NAME = colName;
//			DIR = dir;
//			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getAllData(id,request,depotNo,date);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

			if(session!=null){
				session=null;
			}
		}
		return value;
		
		
	}
	
	
	
	public String editDailyScheduleMappimg(){
		
//		System.out.println("Edit Daily Schedule");
		ScheduleMap schMapData = new ScheduleMap();
		CollectionReportDAO dao1=new CollectionReportDAO();
		try{
			
//			String scheduleMappingId = ServletActionContext.getRequest().getParameter("editScheduleMapId");
//			System.out.println("value+===="+value);
			String[] string=value.split(",");
//			System.out.println("string==="+string);
//			int etmno=Integer.parseInt(string[0].trim());
			String schedule_id=string[0].trim();
//			System.out.println("schedule_id=="+schedule_id);
			String schedule_no=string[1];
			String schedule_type=string[2];
			String shift_type=string[3];
			String vehicle_no=string[4];
			String depot_name=string[5];
			String division_name=string[6];
			String depot_id=string[7];
			String operateddate=string[8];
			String date=dao1.getDateFromPickerDate1(operateddate);
//			System.out.println("date=="+date);
//			schMapData.setSchedule_no(schedule_no);
//			schMapData.setVehicle_no(vehicle_no);
//			schMapData.setDepot_name(depot_name);
			schMapData.setSchedule_type_name(schedule_type);
////			schMapData.setShift_type_name(shift_type);
//			System.out.println("Schedule_no--"+schMapData.getSchedule_no());
//			System.out.println("Schedule_no--"+schMapData.getVehicle_no());
			String divisionid=getDivisionid(division_name);
			setSchedule_id(schedule_id);
			setSchedule_no(schedule_no);
			setShiftName(shift_type);
			setVehicle_no(vehicle_no);
			setDepot_id(depot_id);
//			setVehiclelist(vehicle_no);
//			System.out.println("getDepot1=="+getDepot1()+"  getStartdate1=="+getStartdate1()+" getDivision1=="+getDivision1());
			setDepot1(depot_id);
			setStartdate1(date);
			setDivision1(divisionid);
			
			triplist = getAllTrip(schedule_id);
			
			
//			schMapData.setDepot_id(Integer.parseInt(rs.get("depot_id").toString()));
			
			
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
		
		return "success";
	}
	String returnString="";
	public String saveEditDailyScheduleMapping(){
		CollectionReportDAO dao1=new CollectionReportDAO();
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		String msg="null";
		String msg1="null";
		String tripno="";
		try{
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
	       int id=Integer.parseInt(request.getParameter("schedule_id"));
	  String mappedSchedule="";
		
	       String vehNo=request.getParameter("vehicle_no");
	       String tripnodata=request.getParameter("trip_no");
	       String reason=request.getParameter("reasondata");
	       System.out.println("id===="+id+" vehNo===="+vehNo+" tripno===="+tripnodata+" reason===="+reason);
	       
	       if(tripnodata.equals("0")){
	    	   tripno="";
	       }else {
	    	   tripno=" and trip_number >="+tripnodata+" ";
	       }
	       String id1=getSchedule_id();
	       String vehicle_no=getVehicle_no();
	       String vehicle_no1=getVehiclelist();
	       String sch_no=getSchedule_no();
	       String shift_type=getShiftName();
	       String op_date=dao1.getDateFromPickerDate(getStartdate1());
	       System.out.println("Date===="+op_date);
	       System.out.println("Shift_type==="+shift_type);
	       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	   	Date date = new Date();
	   	String today_date=formatter.format(date);
	   	System.out.println(today_date);
	       
	       

	       if(vehicle_no=="Select" || vehicle_no.equalsIgnoreCase("Select")){
	    	   addActionError("Select Vehicle Number For Schedule Number "+sch_no+"");
	    	   returnString="fail";
	       }else{
	    	   
	    	   if(op_date.equalsIgnoreCase(today_date)){
	    	   
//	    	   if(shift_type.equalsIgnoreCase("Night Service")){
//	    		   mappedSchedule="";
//	    	   }else{
		mappedSchedule=dao.checkVehicleDailyScheduleMapping(vehNo,op_date);
	    	 //  }
		System.out.println("mappedSchedule===="+mappedSchedule);
	     if(mappedSchedule.equals("")){         //  To check if same vehicle is already assigned to another schedule
			if(dao.saveEditedDailySchduleDetails(id,vehNo,op_date,sch_no,shift_type,tripno,reason)){
//				System.out.println("getSchedule_type_id===="+sch_no);
				 msg="Schedule Number "+sch_no+" updated successfully";
		         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", msg);
		         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage1", "1");


				addActionMessage("Schedule Number "+sch_no+" updated successfully");
				returnString = "success";
			}
	     
			else{
				addActionError("Error...Try Again Later");
				returnString = "fail";
			}
	     }
	     else{
	    	if( dao.getUpdateEditVehicle(mappedSchedule,id,vehNo,op_date,shift_type,tripno,reason)){
	    		//addActionMessage("Schedule Number "+mappedSchedule+" updated successfully");
	    		addActionMessage("Schedule Number "+sch_no+" updated successfully");
	    		 msg="Schedule Number "+sch_no+" updated successfully ";
	    		 	//msg1="Schedule Number "+mappedSchedule+" updated successfully";
	    		 msg1="";
		         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", msg);
		         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage1", "1");
		         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage2", msg1);
		         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage3", "0");
				returnString = "success";
	    	}else{
//	    	 addActionError("This Vehicle Number is Already assigned to "+mappedSchedule); 
				
//	    		System.out.println("Enter Update else");
//	    	 Bootbox.confirm("This Vehicle Number is Already assigned to "+mappedSchedule + "Do you want to Continue");
				returnString = "fail";
	    	}
	     }
	    	   }else {
	    		   addActionError("Please Select Today Date For Edit Schedule");
		    	   returnString="fail";
	    	   }
	       }
	       
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return returnString;
		
//			return "input";
		}
	
	public String getPerticularVehicleList() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
//		VtsDataDAO dao = VtsDataDAO.getInstance();
		ScheduleMapDAO dao=new ScheduleMapDAO();
		Common common = new Common();
		int depotid=0;
		String id1=req.getParameter("val");
//System.out.println("depot finaly"+id1);
		Session session = null;
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		if(id1.equals("NA"))
		{
			id1="NA";
			depotid=0;
		}else {
//			String sql="SELECT org_chart_id  from org_chart where org_name='"+id1+"' ";
//			String depotChartId=common.getDBResultStr(session, sql, "org_chart_id");
			depotid =Integer.parseInt(id1);
		}
	   
		List<String> l1 = dao.getVehicleID2(depotid);
		List<String> l2 = dao.getVehicleName1(depotid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option  value='Select'>Select</option>";
		regionTypeAjaxString += "<option  value='NA'>NA</option>";
		for (int i = 0; i < l1.size(); i++) {
			String vehiclearr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<option value=" + l2.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String getDivisionid(String divisionname){
		ScheduleMapDAO dao=new ScheduleMapDAO();
		Common common = new Common();
		String divisionid="";
		String qry = "select org_chart_id from  org_chart where org_name='"+divisionname+"' and deleted_status=0";
//		SELECT * FROM `vehicle` WHERE `org_chart_id` = '31' AND `deleted_status` = '0' AND `cause_status` != 'S'
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					divisionid=rs.get("org_chart_id").toString();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			
		}
		return divisionid;
	}
	
	public String validateVehicleNo(){
//		ScheduleMapDAO dao=new ScheduleMapDAO();
		Common common = new Common();
		CollectionReportDAO dao1=new CollectionReportDAO();
//		System.out.println("Enter into check vehicle");
//		System.out.println("vehicleno=="+vehicleno);
		String sch_no="";
//		String shift_type="";
		
		try {
			  String op_date=dao1.getDateFromPickerDate(date);
		       System.out.println("Date222===="+op_date);
//		       shift_type= dao.checkVehicleShiftTypeDailyScheduleMapping(vehicleno,op_date);
			sch_no=dao.checkVehicleDailyScheduleMapping(vehicleno,op_date);
//			System.out.println("Schedule_no====="+sch_no);
			 HttpServletResponse response = ServletActionContext.getResponse();
	            PrintWriter out;

	            out = response.getWriter();
	            out.print(sch_no.toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}
	
	public Map<Integer, String> getAllTrip(String sch_id) {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql="select trip_number from daily_schedule_mapping_vehicle where schedule_no=45 and operated_date=DATE(now())";
			
//		Query query = session
//				.createQuery("select org_chart_id,org_name from org_chart  where org_type_id="+orgtypeid+" and "+id+" and deleted_status=0 order by org_name");
		Query query = HibernateUtil.getSession("").createSQLQuery(sql).addScalar("trip_number",Hibernate.STRING);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<Integer, String>> list = query.list();
		
			for (int i = 0; i < list.size(); i++) {
//				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
				resultMap.put(Integer.parseInt(list.get(i).get("trip_number")), list.get(i).get("trip_number"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	

}
