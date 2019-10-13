package com.trimax.its.vehicle.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.ModelTypeDAO;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.ScheduleMap;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.WaybillDetails;
import com.trimax.ws.vts.vtsutility.WayBillDetails;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class ScheduleMappingAction extends ActionSupport {

	ScheduleMapDAO daoObject = new ScheduleMapDAO();
	
	/*private ScheduleMap scheduleMapDetails;
//	private int isNewSchedule;
	public ScheduleMap getScheduleMapDetails() {
		return scheduleMapDetails;
	}

	public void setScheduleMapDetails(ScheduleMap scheduleMapDetails) {
		this.scheduleMapDetails = scheduleMapDetails;
	}
*/
	public String divisionlist1;
	private Map<Integer, String> divisionlist;
	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	private List<ScheduleMap> scheduleMapDetails;
	public List<ScheduleMap> getScheduleMapDetails() {
		return scheduleMapDetails;
	}

	public void setScheduleMapDetails(List<ScheduleMap> scheduleMapDetails) {
		this.scheduleMapDetails = scheduleMapDetails;
	}

	public int updatedScheduleMappingId;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String execute()
	{
//		this.setModelTypeList(daoObject.getModelTypeList());
		return "success";
	}

   private String orgdepotname;
	public String getOrgdepotname() {
	return orgdepotname;
}

public void setOrgdepotname(String orgdepotname) {
	this.orgdepotname = orgdepotname;
}
Session session = null;
	@SkipValidation
	public String getScheduleMappingRecordsList() throws IOException{
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();	
	String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

	
	ScheduleMapDAO mapDao=new ScheduleMapDAO();
	
	try{
		System.out.println("test1.........");
		session = HibernateUtil.getSession("hibernate.cfg.xml");	
		
		String[] dbcol={"","schedule_name","vehicle_no","depot_name","",""};
		String[] cols={"","schedule_name","vehicle_no","depot_name","",""};
		System.out.println("test2");
		JSONObject result = new JSONObject();
//		Number cnt = daoObject.getTotalScheduleMapRecord();
//		System.out.println("Count------>"+cnt);
		
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
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");
//		System.out.println("orgtypeid..........." + orgtypeid
//				+ "orgchartid................." + orgchartid);
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
//		System.out.println("id...." + id);
		String colName = cols[col];
		int total = -1;
		total=mapDao.getTotalScheduleMappingRecords(request, dbcol[Integer.parseInt(sCol)],sdir,id);
//		System.out.println("total----"+total);
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		//COL_NAME = colName;
		DIR = dir;
		START = start;
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		
		
		result=mapDao.getScheduleMappingRecords1(total,request,dbcol[Integer.parseInt(sCol)],sdir,id);
//		System.out.println("result---------"+result);
		out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;

}
		
/*		
   String sql ="SELECT schedule_id as schedule_id,IFNULL(s.schedule_number,'') as schedule_no,st.schedule_type_name as schedule_type_name," +
   		"IFNULL(v.license_number,'') as vehicle_no from schedule_mapping_vehicle sm left join schedule s on s.schedule_id=sm.schedule_no " +
   		"left join schedule_type st on st.schedule_type_id=sm.schedule_type_id " +
   		"left join vehicle v on v.vehicle_id=sm.vehicle_id where sm.status='ACTIVE'  group by sm.schedule_no";		
			
    System.out.println("query........."+sql);
  	 Query query = session.createSQLQuery(sql)
  			.addScalar("schedule_id", Hibernate.INTEGER)
				 .addScalar("schedule_no", Hibernate.STRING)
				 .addScalar("schedule_type_name", Hibernate.STRING)
				 .addScalar("vehicle_no", Hibernate.STRING);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();	
		
    	JSONArray array = new JSONArray();
		
		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);
			int j=i+1;
			JSONArray ja = new JSONArray();
			ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("schedule_id")+ "' value='"+ rs.get("schedule_id") + "'/>");
			data-set='#sample_2 .checkboxes' 
//			ja.add(rs.get("schedule_id"));
			ja.add(rs.get("schedule_no").toString());
			ja.add(rs.get("schedule_type_name").toString());
			ja.add(rs.get("vehicle_no").toString());
			
			array.add(ja);
		}
		int total=-1;
//		total = daoObject.getTotalScheduleMappingRecords();
//		System.out.println("tatal record is"+total);
		System.out.println(array);
		result.put("aaData", array);
		result.put("iTotalRecords", 0);
		result.put("iTotalDisplayRecords", 0);
	
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		out.print(result);
			

	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if (session != null) {
			session.close();
		}
	}
	return result;
}
*/
//public void validate()
//{
//	if(updatedScheduleMappingId!=0)	{
//		if(scheduleMapDetails.getSchedule_name()==null||scheduleMapDetails.getSchedule_name().trim().equals("")){
//			addFieldError("UpdatedScheduleMappingName","Please Enter Schedule ");
//		}else if(!scheduleMapDetails.getSchedule_name().trim().matches("^[A-ZA-Z0-9- ]*$")){
//			addFieldError("UpdatedScheduleMappingName","INVALID SCHEDULE");
//	}
//	}
//	
//}
String returnString="";


ScheduleMap schedulemp;

public ScheduleMap getSchedulemp() {
	return schedulemp;
}

public void setSchedulemp(ScheduleMap schedulemp) {
	this.schedulemp = schedulemp;
}

public String editScheduleMapping()
{
	int scheduleMappingId = Integer.parseInt((ServletActionContext.getRequest().getParameter("editScheduleMapId")));
	//For WebService call 
		/*
    String Strarr[]=scheduleMappingId.split("#");   //Schedule id and Shift id
	
    int schId=Integer.parseInt(Strarr[0]);
	int shiftId=Integer.parseInt(Strarr[1]);
	int depotId=Integer.parseInt(Strarr[2]);
	int schdId=Integer.parseInt(Strarr[3]);
	this.setSchedulemp(daoObject.getScheduleMapDetailsEdit(schId,shiftId,depotId,schdId));
   */
  
	
	//For normal Edit in its
	this.setSchedulemp(daoObject.getScheduleMapDetailsEdit(scheduleMappingId));
	
	return "success";
}
	
public String saveEditScheduleMappingAction(){
//	System.out.println("in edit schedule mapping action");
	AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	HttpServletRequest request = ServletActionContext.getRequest();

	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
       int id=Integer.parseInt(request.getParameter("schedule_type_id"));
  String mappedSchedule="";
       String vehNo=request.getParameter("vehicle_no");
	mappedSchedule=daoObject.checkVehicleScheduleMapping(vehNo);
     if(mappedSchedule.equals("")){         //  To check if same vehicle is already assigned to another schedule
		if(daoObject.saveEditedDetails(id,vehNo)){
//			System.out.println("getSchedule_type_id"+schedulemp.getSchedule_no());
			addActionMessage("Schedule Number "+schedulemp.getSchedule_no()+" updated successfully");
			returnString = "success";
		}
     
		else{
			addActionError("Enter Valid Vehicle No");
			returnString = "fail";
		}
     }
     else{
    	 addActionError("This Vehicle Number is Already assigned to "+mappedSchedule);
			returnString = "fail";
     }
	return returnString;
	
//		return "input";
	}

public String getPerticularVehicle() throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
//		VtsDataDAO dao = VtsDataDAO.getInstance();
		ScheduleMapDAO dao=new ScheduleMapDAO();
		Common common = new Common();
		int depotid=0;
		String id1=req.getParameter("val");
//System.out.println("depot finaly"+id1);

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



	public String deleteScheduleMapping() {
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid = (Integer) request.getSession()
				.getAttribute("userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,	"ScheduleMappingList.action");
//		if (delete.equalsIgnoreCase("Y")) {
			boolean status = false;
			String scheduleId =ServletActionContext
					.getRequest().getParameter("scheduleMapId");
			int scheduleId1 = Integer.parseInt(scheduleId);
			
			String Strarr[]=scheduleId.split("#");   //Schedule id and Shift id
			//For Webservice purpose
			/*
			int schId=Integer.parseInt(Strarr[0]);
			int shiftId=Integer.parseInt(Strarr[1]);
			int depotId=Integer.parseInt(Strarr[2]);
			int schdId=Integer.parseInt(Strarr[3]);
			System.out.println("===="+schdId);
			*/
try{
	
/*	for webservice call
		if(daoObject.deleteScheduleMappingWebservicecall(schdId)){
//			System.out.println("status is"+status);
				addActionMessage("Schedule Number deleted successfully");
				returnString = "success";
//				addActionMessage("Schedule Mapping " + scheduleId
//						+ " deleted successfully");
//				returnString = "success";
			}

		 else {
			 addActionError("error in deletion");
				returnString = "fail";
			} */
	
	if(daoObject.deleteScheduleMapping(scheduleId1)){
//		System.out.println("status is"+status);
			addActionMessage("Schedule Number deleted successfully");
			returnString = "success";
//			addActionMessage("Schedule Mapping " + scheduleId
//					+ " deleted successfully");
//			returnString = "success";
		}

	 else {
		 addActionError("error in deletion");
			returnString = "fail";
		}
}catch (Exception e) {
	// TODO: handle exception
}
			return returnString;

		}
	
	
	//Webservice call
	public String getScheduleMappingRecordsListAjax() {

//		System.out.println("hiiiiiiii");
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		ScheduleMapDAO dao= new ScheduleMapDAO();
		JSONObject result = new JSONObject();
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
		try {
			
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");
			System.out.println("orgtypeid..........." + orgtypeid
					+ "orgchartid................." + orgchartid);
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
			System.out.println("id...." + id);
			
			

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForshowScheduleMapping(1, req, "", "",id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	//webservice call End
	
	


public String saveEditScheduleMappingActionWebServiceCall(){
	AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	HttpServletRequest request = ServletActionContext.getRequest();

	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
       int id=Integer.parseInt(request.getParameter("schedule_type_id"));
  String mappedSchedule="";
       System.out.println("id is---"+id);
       String vehNo=request.getParameter("vehicle_no");
       System.out.println("veh no---"+vehNo);
       int shiftId=Integer.parseInt(request.getParameter("shift_type_id"));
       int schdId=Integer.parseInt(request.getParameter("form_four_id"));
       System.out.println("shiftId getting=="+shiftId);
       System.out.println("shiftId getting=="+schdId);
	DependencyChecker dc=new DependencyChecker();

	mappedSchedule=daoObject.checkVehicleScheduleMappingEditWebServiceCall(vehNo,schdId,shiftId);
     if(mappedSchedule.equals("")){         //  To check if same vehicle is already assigned to another schedule
		if(daoObject.saveEditedDetailsWebserviceCall(id,vehNo,shiftId)){
			addActionMessage("Schedule Number "+schedulemp.getSchedule_no()+" updated successfully");
			returnString = "success";
		}
     
		else{
			addActionError("Enter Valid Vehicle No");
			returnString = "fail";
		}
     }
     else{
    	 addActionError("This Vehicle Number is Already assigned to "+mappedSchedule);
			returnString = "fail";
     }
	return returnString;
	
//		return "input";
}

		public String createScheduleMapping(){
			//VtsDataDAO vvt = VtsDataDAO.getInstance();
			//divisionlist = vvt.getDivisionName();
			
			HttpServletResponse resp = ServletActionContext.getResponse();
			// resp.setCharacterEncoding("application/json");
			HttpServletRequest req = ServletActionContext.getRequest();
			 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	        // System.out.println("orgtypeid--"+orgtypeid +"orgchartid--"+orgchartid);
	       //  VtsDataDAO vvt = VtsDataDAO.getInstance();
	    
	         if(orgtypeid.equals("2")){
	        	VtsDataDAO vvt = VtsDataDAO.getInstance();
	        	int parentId1=0;
	        	Date date = new Date();
	     		Date date1 = new Date();
	     		//Date addedDate2 = addDays(date1, 1);
	     		int parentId=0;

				try {
					
					parentId1 = vvt.getDepot1DC(orgtypeid,orgchartid);
					System.out.println("parentId---"+parentId1);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("getDepot1DC--");
				divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId1);
				
				
		
	        }
	         
	        else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
	        	VtsDataDAO vvt = VtsDataDAO.getInstance();
	    		Date date = new Date();
	    		Date date1 = new Date();
	    		try {
	    			divisionlist = vvt.getDepot();

	    		} catch (Exception e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		divisionlist = vvt.getDivisionName();
	        }
	         
	        else{
	         	VtsDataDAO vvt = VtsDataDAO.getInstance();
	 		int parentId=0;

	 		try {
	 			parentId = vvt.getDepot1(orgtypeid,orgchartid);
	 			
	 		} catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	 		divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
	 		//Ends..
	         }
			return "success";
	
			
		}
		boolean isSiuccess=false;
		public String saveScheduleMappingActionWebServiceCall(){
			//AccessRightsDao  accessrightdao=new AccessRightsDao();
			//AccessRights accessrights=new AccessRights();
			HttpServletRequest request = ServletActionContext.getRequest();

//			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
//			String access=accessrights.getACCESS();
//			String create=accessrights.getCREATE();
//			String edit=accessrights.getEDIT();
//			String delete=accessrights.getDELETE();
			try{
		    int depotid=Integer.parseInt(request.getParameter("depotlist1"));
		    int schedulelist1=Integer.parseInt(request.getParameter("schedulelist1"));
		   // int shiftlist1=Integer.parseInt(request.getParameter("shiftlist1"));
		   String vehiclelist1=request.getParameter("vehiclelist1");
		   String vehicle_no=request.getParameter("vehicleNO");
		    System.out.println(depotid+"......"+schedulelist1+"..............."+vehiclelist1+"...............");
		    Common common = new Common();
		  String mappedSchedule="";
		  String mappedSchedule1="";
		  int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		       System.out.println("id is---"+id);
		  Session session1 = null;
			Transaction transaction  = null;
			 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			  String sqlMap = "select count(vehicle_id) count from vehicle_device WHERE vehicle_id='"+vehiclelist1+"'  and status = 'ACTIVE' ";
			  int mapp = Integer.parseInt(common.getDBResultStr(session1, sqlMap, "count"));
			  if(mapp !=0){
			
		  String sch_name="",shift="";
		  int form_four_id=0;
		  int shiftId=0;
		  int id=0;
//		  String qry = "select s.schedule_number schedule_number,og1.org_name division,schdt.schedule_type_name schedule_type_name,schdt.schedule_type_id schedule_type_id,st.shift_type_name,serv.service_type_id service_type_id,st.shift_type_id," +
//		  		" sd.form_four_id form_four_id,ff.schedule_number_name schedule_number_name," +
//		  		"sd.start_time start_time,sd.end_time end_time,sd.route_number_id route_number_id,r.route_number route_number," +
//		  		"r.route_direction route_direction,sd.trip_number trip_number,og.org_name org_name,'"+vehicle_no+"' license_number,"+vehiclelist1+" as vehicle_id,d.device_serial_number device_id,is_dread_trip " +
//		  		"from schedule_details sd " +
//		  		"inner join schedule s on sd.schedule_number=s.schedule_id " +
//		  		"inner join form_four ff on ff.form_four_id=sd.form_four_id " +
//		  		"inner join schedule_type schdt on schdt.schedule_type_id=s.schedule_type " +
//		  		"inner join service_type serv on serv.service_type_id=s.schedule_service_type " +
//		  		"inner join org_chart og on og.org_chart_id=s.depot_id " +
//		  		"inner join org_chart og1 on og1.org_chart_id=og.parent_id "+
//		  		"inner join shift_type st on st.shift_type_id=sd.shift_type_id " +
//		  		"inner join route r on r.route_id=sd.route_number_id " +
//		  		"left join vehicle_device vd on vd.vehicle_id="+vehiclelist1+" "+
//		  		"left join device d on d.device_id=vd.device_id "+
//		  		"where s.schedule_id='"+schedulelist1+"' " +
		  				
		  		
		  		          //"and sd.shift_type_id="+shiftlist1+" " +
//		  		" AND ff.`deleted_status` = '0' and s.status='NEW' and " +
//		  		"s.current_status='CASE WORKER' and sd.`deleted_status` = '0' and ff.current_status='ACTIVE' and vd.status='ACTIVE' and d.status='ACTIVE' and d.deleted_status=0 and st.deleted_status=0 " +
		  		             //"and sd.deleted_status=0 " ;
//		  		"group by sd.shift_type_id,sd.trip_number";
		  
		  String qry=" select s.schedule_id,s.schedule_number schedule_number,og1.org_name division,schdt.schedule_type_name schedule_type_name, "+
				  	 " schdt.schedule_type_id schedule_type_id,serv.service_type_id service_type_id, "+
				  	 " og.org_name org_name,s.depot_id,'"+vehicle_no+"' license_number,"+vehiclelist1+" as vehicle_id,d.device_serial_number device_id from schedule_details sd  "+
				  	 " inner join schedule s on sd.schedule_number=s.schedule_id  "+
				  	 " inner join schedule_type schdt on schdt.schedule_type_id=s.schedule_type  "+
				  	 " inner join service_type serv on serv.service_type_id=s.schedule_service_type "+
				  	 " inner join org_chart og on og.org_chart_id=s.depot_id  "+
				  	 " inner join org_chart og1 on og1.org_chart_id=og.parent_id "+
				  	 " left join vehicle_device vd on vd.vehicle_id="+vehiclelist1+" "+
				  	 " left join device d on d.device_id=vd.device_id  "+
				  	 " where s.schedule_id='"+schedulelist1+"' and s.status='NEW' and s.current_status='CASE WORKER'  "+
				  	 " and sd.`deleted_status` = '0'  and vd.status='ACTIVE' and d.status='ACTIVE' and d.deleted_status=0  "+
				  	 " group by schedule_number";
		 
			 transaction = session1.beginTransaction();
		  String sql11 = "select vehicle_id,license_number from vehicle WHERE vehicle_id='"+vehiclelist1+"' AND `deleted_status` = '0' AND `cause_status` != 'S' ";
		  String vehicleno = common.getDBResultStr(session1, sql11, "license_number");
		  
//			 
//			 Query query = session1.createSQLQuery(qry).addScalar("schedule_type_id")
//						.addScalar("service_type_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("start_time").addScalar("end_time")
//						.addScalar("route_number_id").addScalar("route_number").addScalar("route_direction").addScalar("trip_number")
//						.addScalar("org_name").addScalar("license_number").addScalar("vehicle_id").addScalar("shift_type_name")
//						.addScalar("shift_type_id").addScalar("schedule_number").addScalar("division").addScalar("schedule_type_name").addScalar("is_dread_trip").addScalar("device_id");
				
		  Query query = session1.createSQLQuery(qry).addScalar("schedule_id").addScalar("schedule_type_id").addScalar("service_type_id").addScalar("schedule_number").addScalar("schedule_type_name")
				  .addScalar("org_name").addScalar("depot_id").addScalar("license_number").addScalar("vehicle_id").addScalar("division").addScalar("device_id");
			
		  
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				int userid = (Integer) request.getSession().getAttribute("userid");
				String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
				List<WayBillDetails> schMapped= new ArrayList<WayBillDetails>();
				
				String org_name="";
				String depot_id="";
				 String schedule_number_name="";
				 String start_time="";
				 String end_time="";
				int service_type_id=0;
				int shift_type_id=0;
				int route_id=0;
				String route_number="";
				String route_direction="";
				int trip_number=0;
				if(aliasToValueMapList.size()>0){
					 mappedSchedule1=daoObject.checkScheduleNoScheduleMappingWebServiceCall(vehicleno, sch_name,schedulelist1);
					 if(mappedSchedule1.equals("")){
						 Map<String, Object> list1 = aliasToValueMapList.get(0);
						 int schedule_type_id11=Integer.parseInt(list1.get("schedule_type_id").toString());
						String schedule_name=list1.get("schedule_number").toString();
						 mappedSchedule=daoObject.checkVehicleScheduleMappingWebServiceCall(vehicleno, id, schedule_type_id11,schedule_name);
					     if(mappedSchedule.equals("")){   
					    	 System.out.println("mapped sch value=="+mappedSchedule);
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
					 Map<String, Object> list = aliasToValueMapList.get(i);
					 int schedule_type_id=Integer.parseInt(list.get("schedule_type_id").toString());
//					   id=Integer.parseInt(list.get("schedule_type_id").toString());
//					  org_name=list.get("org_name").toString();
//					  service_type_id=Integer.parseInt(list.get("service_type_id").toString());
//					 form_four_id=Integer.parseInt(list.get("form_four_id").toString());
//					 schedule_number_name=list.get("schedule_number_name").toString();
//					 sch_name=list.get("schedule_number_name").toString();
//					 shift=list.get("shift_type_name").toString();
//					 shiftId=Integer.parseInt(list.get("shift_type_id").toString());
//					
//					  start_time=list.get("start_time").toString();
//					  end_time=list.get("end_time").toString();
//					  route_id=Integer.parseInt(list.get("route_number_id").toString());
//					  route_number=list.get("route_number").toString();
//					  route_direction=list.get("route_direction").toString();
//					  trip_number=Integer.parseInt(list.get("trip_number").toString());
//					 String license_number=list.get("license_number").toString();
//					  shift_type_id=Integer.parseInt(list.get("shift_type_id").toString());
//					 String vehicle_id=(list.get("vehicle_id").toString());
//					 String schedule_number=(list.get("schedule_number").toString());
//					 String division=(list.get("division").toString());
//					 String schedule_type_name=(list.get("schedule_type_name").toString());
//					 String is_dead_trip=(list.get("is_dread_trip").toString());
//					 String device_id=(list.get("device_id").toString());
					 
					 
					 id=Integer.parseInt(list.get("schedule_type_id").toString());
					  org_name=list.get("org_name").toString();
					  depot_id=list.get("depot_id").toString();
					  service_type_id=Integer.parseInt(list.get("service_type_id").toString());
					 //form_four_id=Integer.parseInt(list.get("form_four_id").toString());
					 //schedule_number_name=list.get("schedule_number_name").toString();
					 sch_name=list.get("schedule_number").toString();
					
					 String license_number=list.get("license_number").toString();
					  //shift_type_id=Integer.parseInt(list.get("shift_type_id").toString());
					 String vehicle_id=(list.get("vehicle_id").toString());
					 String schedule_number=(list.get("schedule_number").toString());
					 String division=(list.get("division").toString());
					 String schedule_type_name=(list.get("schedule_type_name").toString());
					 //String is_dead_trip=(list.get("is_dread_trip").toString());
					 String device_id=(list.get("device_id").toString());

					
				
//				 isSiuccess=daoObject.saveDetailsWebserviceCall(depotid,org_name,id,service_type_id,form_four_id,
//							schedulelist1,schedule_number_name,start_time,end_time,shift_type_id,route_id,route_number,route_direction,trip_number,vehiclelist1,vehicleno,is_dead_trip,device_id);
					 isSiuccess=daoObject.saveDetailsWebserviceCall( depotid,org_name,id,service_type_id,schedulelist1,schedule_number,schedule_type_name,vehiclelist1,vehicleno,device_id,division,usrsessionid);
					 
				 }
				 if(isSiuccess==true){
						addActionMessage("Schedule Number '"+sch_name+"'  Mapped with Vehicle No'"+vehicle_no+"'  successfully");
						returnString = "success";
						}else{
							addActionError("Schedule Mapping Creation problem please try after sometime");
							returnString = "fail";
						}
				 
				     } else{
			    	 addActionError("This Vehicle Number '"+vehicle_no+"'  is Already assigned");
						returnString = "fail";
			     }
				 }else{
					 addActionError("This Schedule Number "+sch_name+"  is Already Mapped");
						returnString = "fail";
				 }
				 
			 }
			  }else{
				  addActionError("Vehicle is not Mapped with any Device.Please do the Vehicle Device Mapping first");
					returnString = "fail";
			  }
				 
//				 System.out.println("shift id  "+schMapped+"-id-"+id+"-vno"+vehicle_no+schedulelist1);
//				 mappedSchedule1=daoObject.checkScheduleNoScheduleMappingWebServiceCall(vehicleno, sch_name,schedulelist1);
//				 if(mappedSchedule1.equals("")){
//				 mappedSchedule=daoObject.checkVehicleScheduleMappingWebServiceCall(vehicleno, id, shiftId);
//			     if(mappedSchedule.equals("")){   
//			    	 System.out.println("in if");//  To check if same vehicle is already assigned to another schedule
//			    	 WsUtil_Service service = new WsUtil_Service();
//						WsUtil port = service.getWsUtilPort();
//						model.jaxb.xml.trimax.com.VtsResponse6 alertresult = null;
//						alertresult = port.insertIntoScheduleMappingVehicle(schMapped,"1");
//						System.out.println("here   "+alertresult.getWaybillDetails().get(0).getSTATUS());
//						 if(alertresult.getWaybillDetails().get(0).getSTATUS().equals("true")){
//								addActionMessage("Schedule Number '"+sch_name+"'  Mapped with Vehicle No'"+vehicle_no+"'  successfully");
//								returnString = "success";
//								}else{
//									addActionError("Schedule Mapping Creation problem please try after sometime");
//									returnString = "fail";
//								}
//			     }
//			     else{
//			    	 addActionError("This Vehicle Number '"+vehicle_no+"'  is Already assigned");
//						returnString = "fail";
//			     }
//				 }else{
//					 addActionError("This Schedule Number "+sch_name+"  is Already Mapped");
//						returnString = "fail";
//				 }
//				}
				 VtsDataDAO vvt = VtsDataDAO.getInstance();
					divisionlist = vvt.getDivisionName();
		     
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("return==="+returnString);
			return returnString;
			
//				return "input";
		}
		
		
		public String getScheduleListForScheduleMapping() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			ScheduleMapDAO schdmap=new ScheduleMapDAO();
			int parentId = Integer.parseInt(req.getParameter("val"));
			 //String dat = req.getParameter("startdate");
			 //System.out.println("dat==="+dat);
			 Common comm = new Common();
		//	String dat1=comm. getDateFromPicker(dat);
	        		List<String> l1 = schdmap.getScheduleId(parentId);
	        		List<String> l2 = schdmap.getScheduleName(parentId);
	        		String regionTypeAjaxString = "";
	        		regionTypeAjaxString += "<option value='0'>Select</option>";
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		
		
		
//		public String getShiftListForScheduleMapping() {
//			// get Depot List..
//			HttpServletRequest req = ServletActionContext.getRequest();
//			ScheduleMapDAO schdmap=new ScheduleMapDAO();
//		
//			int parentId = Integer.parseInt(req.getParameter("val"));
//			 //String dat = req.getParameter("startdate");
//			 //System.out.println("dat==="+dat);
//			 Common comm = new Common();
//		//	String dat1=comm. getDateFromPicker(dat);
//	        		List<String> l1 = schdmap.getShiftId(parentId);
//	        		List<String> l2 = schdmap.getShiftName(parentId);
//	        		String regionTypeAjaxString = "";
//	        		regionTypeAjaxString += "<option value='0'>Select</option>";
//	        		for (int i = 0; i < l1.size(); i++) {
//	        			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
//	        					+ ">" + l2.get(i).toString() + "</option>";
//	        		}
//	        		HttpServletResponse response = ServletActionContext.getResponse();
//	        		PrintWriter out;
//	        		try {
//	        			out = response.getWriter();
//	        			out.print(regionTypeAjaxString);
//	        		} catch (IOException e) {
//	        			// TODO Auto-generated catch block
//	        			e.printStackTrace();
//	        		}
////	        		return null;
////	        	}
//			
//	        		return null;
//	        }
//		
		
	
		public String getDepotVehicleForScheduleMapping() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
		
			int parentId = Integer.parseInt(req.getParameter("val"));
			 //String dat = req.getParameter("startdate");
			 //System.out.println("dat==="+dat);
			ScheduleMapDAO schdmap=new ScheduleMapDAO();
			 Common comm = new Common();
		//	String dat1=comm. getDateFromPicker(dat);
	        		List<String> l1 = schdmap.getVehicleId(parentId);
	        		List<String> l2 = schdmap.getVehicleName(parentId);
	        		String regionTypeAjaxString = "";
	        		regionTypeAjaxString += "<option value='0'>Select</option>";
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		
		public String getDepotListForMapping() {
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
//        		regionTypeAjaxString += "<option value='0'>select</option>";
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
    		regionTypeAjaxString += "<option value='0'>select</option>";
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
//    		regionTypeAjaxString += "<option value='0'>select</option>";
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
}

//}



	

