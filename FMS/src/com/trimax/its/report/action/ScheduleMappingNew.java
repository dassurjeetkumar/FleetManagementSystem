package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vehicle.dao.VehicleDockingReportDao;
import com.trimax.its.vehicle.model.ScheduleMap;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WayBillDetails;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class ScheduleMappingNew extends ActionSupport {
	
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
	private Map<Integer, String> scheduletypelist;
	
	
	
	
	
	
	public Map<Integer, String> getScheduletypelist() {
		return scheduletypelist;
	}

	public void setScheduletypelist(Map<Integer, String> scheduletypelist) {
		this.scheduletypelist = scheduletypelist;
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
		
		//String msg1="";
if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("null")){
			
		}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("0")){
		}else{
			String msg=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString();
			addActionMessage(msg);
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","null");
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage1","null");
		}

			if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2").toString().equalsIgnoreCase("null")){
				
			}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage3").toString().equalsIgnoreCase("0")){
			}else{
				String msg1=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2").toString();
				 addActionError(msg1);
				 ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage2","null");
					ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage3","null");
			}
			//addActionMessage(msg);
			//addActionError(msg1);
			
			
		//}
		
		
		
		
		
		
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
		scheduletypelist=getShiftType();

		return "success";
	}
	
	public String getDepotList() {
		// get Depot List..
		System.out.println("in all selection");
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        System.out.println("parent"+parentId+"----"+orgchartid+"-------"+orgtypeid);
        if(orgtypeid.equals("2")){
//        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
        		List<String> l1 = getDepotIdDC(parentId,orgchartid);
        		List<String> l2 = getDepotNameDC(parentId,orgchartid);
        		String regionTypeAjaxString = "";
    			regionTypeAjaxString = "<option  value='0'>------Select------</option>";
    			//System.out.println("hereeeeee-----"+regionTypeAjaxString);
        		for (int i = 0; i < l1.size(); i++) {
        			regionTypeAjaxString += "<option value='" + l1.get(i).toString()
        					+ "'>" + l2.get(i).toString() + "</option>";
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
        	List<String> l1 = getDepotId(parentId);
    		List<String> l2 = getDepotName(parentId);
    		String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option  value='0'>------Select------</option>";
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
        	List<String> l1 = getDepotId(parentId,orgchartid);
    		List<String> l2 = getDepotName(parentId,orgchartid);
    		String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option  value='0'>------Select------</option>";
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
	
	
	
	@SuppressWarnings("rawtypes")
	public List getDepotIdDC(int parentID, String orgchart_id) {
		List list = new ArrayList();
		
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotNameDC(int parentID, String org_chart_id) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getDepotId(int parentID) {
		List list = new ArrayList();
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotName(int parentID) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getDepotId(int parentID, String orgchart_id) {
		List list = new ArrayList();
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_chart_id="
				+ orgchart_id
				+ " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotName(int parentID, String org_chart_id) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_chart_id='"
				+ org_chart_id
				+ "' and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	
	
	

	
	public Map<Integer, String> getShiftType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql="SELECT `schedule_type_id`, `schedule_type_name` FROM `schedule_type` WHERE `deleted_status` = '0' AND `status` = 'ACTIVE' ";
			
//		Query query = session
//				.createQuery("select org_chart_id,org_name from org_chart  where org_type_id="+orgtypeid+" and "+id+" and deleted_status=0 order by org_name");
		Query query = HibernateUtil.getSession("").createSQLQuery(sql).addScalar("schedule_type_id",Hibernate.STRING)
				.addScalar("schedule_type_name", Hibernate.STRING);;
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<Integer, String>> list = query.list();
			for (int i = 0; i < list.size(); i++) {
//				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
				resultMap.put(Integer.parseInt(list.get(i).get("schedule_type_id")), list.get(i).get("schedule_type_name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	
	
	
	public void getScheduleDetails() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String depot=req.getParameter("depot");
		String division=req.getParameter("division");
		String type=req.getParameter("type");
		//String chart=req.getParameter("chart");
		System.out.println("depot==="+depot);
		System.out.println("division==="+division);
		System.out.println("type==="+type);
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		JSONObject result=new JSONObject();
		Common common = new Common();
		
		//String date=common.getDateFromPicker(startdate);
		String str="";
		try{
			
			
			String vehicle=getVehicleNumber(depot).toString();

			
			if(type.equals("0")){
				str=" SELECT schedule_id,schedule_number  FROM  schedule s where (s.status='ACTIVE' or s.status='NEW') and  schedule_id not in(select distinct(schedule_no) from schedule_mapping_vehicle_new) and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and s.depot_id="+depot+" ";
			}else {
			
			str=" SELECT schedule_id,schedule_number  FROM  schedule s where (s.status='ACTIVE' or s.status='NEW') and  schedule_id not in(select distinct(schedule_no) from schedule_mapping_vehicle_new) and (current_Status='APPROVED' or current_Status='CASE WORKER') AND `schedule_type` = '"+type+"' and deleted_Status=0 and s.depot_id="+depot+" ";
			
			}
			 System.out.println(str);
//			 rs = stmt.executeQuery(str);
			
			Query query=session.createSQLQuery(str)	;
				/*	.addScalar("schedule_number")
					.addScalar("schedule_code")
					.addScalar("route_number")
					.addScalar("trip_number");*/
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasMapValueList=query.list();
			int size=aliasMapValueList.size();
			System.out.println("alsize----------->"+size);
			    
		int j=1;
			JSONArray array=new JSONArray();
			for(int i=0;i<aliasMapValueList.size();i++){
				Map<String, Object> rs=aliasMapValueList.get(i);
				JSONArray ja=new JSONArray();
//		while(rs.next()){
//			JSONArray ja=new JSONArray();
		//	ja.add("<input type='hidden'  id='chart' name='chart' readonly='readonly' value='"+chart+ "'/>"); 
			/*	ja.add("<input type='hidden'  id='s_type' name='s_type' readonly='readonly' value='"+ rs.get("schedule_type")+ "'/>"); 
				ja.add("<input type='hidden'  id='route' name='s_no' readonly='readonly' value='"+ rs.get("route_id")+ "'/>"); 
				ja.add("<input type='hidden'  id='trip' name='s_no' readonly='readonly' value='"+ rs.get("trip_number")+ "'/>"); 
				*/
		ja.add("<input type='hidden'  id='chart' name='schedulemapping' readonly='readonly' value='ss'/>"+"<center>"+j+"<center>");
                    j++;
                ja.add("<input type='hidden'  id='schedule_no' name='schedule_no' readonly='readonly' value='"+ rs.get("schedule_id")+"#"+rs.get("schedule_number")+"'/>"+"<center>"+rs.get("schedule_number")+"<center>");
				
                ja.add("<select id='"+i+"' name='vehicle' onchange='getVehicleNo("+i+",this.value,"+size+")' class='select2_category1 form-control' > "+
						"<option value='0'>--Select--</option>"+vehicle+" "+
					    " </select> " );
                
//                ja.add("<select list='depotlist' id='depotlist1' class='select2_category form-control' name='depotlist1' > "+
//												" <option value='0'>select</option><option value='1'>All</option>  "+
//											"</select>");
			    
			    array.add(ja);
				
				
			}
		
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		

	}
	
	public StringBuffer getVehicleNumber(String depot_id) {
		StringBuffer sb=new StringBuffer();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
//	String sql=" select vehicle_id,license_number from vehicle WHERE `org_chart_id` = "+depot_id+" AND  vehicle_id not in (select distinct(vehicle_id) from schedule_mapping_vehicle_new) and `deleted_status` = '0' AND `cause_status` = 'N' ";
			String sql=" select vehicle_id,license_number from vehicle WHERE `org_chart_id` = "+depot_id+" and `deleted_status` = '0' AND `cause_status` = 'N' ";

			Query query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> l= query.list();
	for(Map<String, Object> ll:l) {
		sb=sb.append("<option value='"+ll.get("vehicle_id")+"-"+ll.get("license_number")+"'>"+ll.get("license_number")+"</option>");
	}
	System.out.println(sb);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//session.close();
		}
		return sb;
		
	}
	
	
	public String getsavedata() {
		String result="";
		int i=0;
		HttpSession session=ServletActionContext.getRequest().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		try{
			String depot=request.getParameter("depotlist1");
					String[] schedule_no=request.getParameterValues("schedule_no");
					String[] license_number=request.getParameterValues("vehicle");
String sch_num="";
String fail_sch_num="";
String vehicle_id="0";
String vehicle_no="0";
				System.out.println("depot===="+depot);
				
					Common common = new Common();
					
					//String date1=common.getDateFromPicker(startdate);
				
//					String date2=common.getDateFromPicker(enddate);
				//	String depot=request.getParameter("depotname");
//					String chart=request.getParameter("chartname");
				//	System.out.println("startdate==="+startdate);
//					System.out.println("enddate==="+enddate);
					// String empid="";
					 int loop=0;
					 int loop1=0;
			for(String schedule:schedule_no){
		String schedule_number[]=schedule.split("#");
		String scheduleid=schedule_number[0];
		String schedule_num=schedule_number[1];
		String vehicle=license_number[loop];
		
		String vehicle_Data[]=vehicle.split("-");
		if(!vehicle.equals("0") || !vehicle.equals("0")) {
		 vehicle_id=vehicle_Data[0];
		 vehicle_no=vehicle_Data[1];
		}else {
			vehicle_id="0";
			 vehicle_no="0";
		}
//		String kmss=kms[loop].trim();
//		String ratee=rate[loop].trim();
		
	
		
		
		
		
	
			System.out.println("schedule_number=="+schedule_number);
			System.out.println("vehicle_no=="+vehicle_no+"===="+vehicle_id);
			
					    Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
						session1.beginTransaction();
						
							try{
					if(!vehicle_no.equals("0") || !vehicle_no.equals("0")) {
					result=saveScheduleMappingActionWebServiceCall(depot,scheduleid,vehicle_id,vehicle_no);
					if(result.equalsIgnoreCase("success")){
						sch_num=sch_num+","+schedule_num;
					}else {
						fail_sch_num=fail_sch_num+","+schedule_num;
					}
					}else {
						
					}
						
							}catch (Exception e) {
								e.printStackTrace();
								
							}/*finally{
						
							}*/
							loop++;
						}
			if(!sch_num.equals("")) {
			String  msg="Schedule Number "+sch_num.substring(1)+" updated successfully";
	         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", msg);
	         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage1", "1");
	         System.out.println("Updated Schedule==="+sch_num);
	         sch_num="";
		}
            //addActionMessage("Schedule Number "+sch_no+" updated successfully");
	         if(!fail_sch_num.equals("")){
	         String  msg1="Schedule Number "+fail_sch_num.substring(1)+" Not updated successfully";
	         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage2", msg1);
	         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage3", "1");
	         System.out.println("Not Updated Schedule==="+fail_sch_num);
	         fail_sch_num="";
	         }
//			if(result !=0 ){
//				session.setAttribute("message", "Charted Service  Created Successfully");
//				addActionMessage("Charted Service  Created Successfully");
//				}else{
//					session.setAttribute("message", "Charted Service  Creation Failed");
//					addActionError("Charted Service  Creation Failed");
//				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.setAttribute("message", "Error Happend");
				addActionError("Error Happend");

			}
		return "redir";
		
	
	}
	public int savedata(String date1,String depot_id,String vehicle_no,String logsheet_no,String drivertoken,String dep_date,String ari_date,String hour,String km,String rent_price,String ot_time,String gst_amt,double total_amt) {
		
		


		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
int i=0;
	
		try{
String sql="INSERT INTO `casualcontractrevenue` (depot_no,vehicle_no,logsheet_no,d_tocken,departure_date,arrival_date,hrs_per_day,tot_kms,rent,ot_or_it,gst,tot_rev) "
		+ "values('"+depot_id+"','"+vehicle_no+"','"+logsheet_no+"','"+drivertoken+"','"+dep_date+"','"+ari_date+"',"+hour+",'"+km+"','"+rent_price+"','"+ot_time+"','"+gst_amt+"',"+total_amt+")";
			Query query=session.createSQLQuery(sql);

	i=query.executeUpdate();
		
	System.out.println("i===="+i);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return i;
		
	}
	
	boolean isSiuccess=false;
	String returnString="";
	public String saveScheduleMappingActionWebServiceCall(String depot,String schedule_number1,String vehicle_id1,String vehicle_no1){
		//AccessRightsDao  accessrightdao=new AccessRightsDao();
		//AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		ScheduleMapDAO daoObject = new ScheduleMapDAO();


		try{
	    int depotid=Integer.parseInt(depot);
	    int schedulelist1=Integer.parseInt(schedule_number1);
	   // int shiftlist1=Integer.parseInt(request.getParameter("shiftlist1"));
	   String vehiclelist1=vehicle_id1;
	   String vehicle_no=vehicle_no1;
	    System.out.println(depotid+"......"+schedulelist1+"..............."+vehiclelist1+"...............");
	    Common common = new Common();
	  String mappedSchedule="";
	  String mappedSchedule1="";
//	       System.out.println("id is---"+id);
	  int usrsessionid=(Integer)request.getSession().getAttribute("userid");
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
	  
		 
//		 Query query = session1.createSQLQuery(qry).addScalar("schedule_type_id")
//					.addScalar("service_type_id").addScalar("form_four_id").addScalar("schedule_number_name").addScalar("start_time").addScalar("end_time")
//					.addScalar("route_number_id").addScalar("route_number").addScalar("route_direction").addScalar("trip_number")
//					.addScalar("org_name").addScalar("license_number").addScalar("vehicle_id").addScalar("shift_type_name")
//					.addScalar("shift_type_id").addScalar("schedule_number").addScalar("division").addScalar("schedule_type_name").addScalar("is_dread_trip").addScalar("device_id");
//			
	  
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
				 mappedSchedule1=checkScheduleNoScheduleMappingWebServiceCall(vehicleno, sch_name,schedulelist1);
				 if(mappedSchedule1.equals("")){
					 Map<String, Object> list1 = aliasToValueMapList.get(0);
					 int schedule_type_id11=Integer.parseInt(list1.get("schedule_type_id").toString());
					String schedule_name=list1.get("schedule_number").toString();
					 mappedSchedule=checkVehicleScheduleMappingWebServiceCall(vehicleno, id, schedule_type_id11,schedule_name);
				     if(mappedSchedule.equals("")){   
				    	 System.out.println("mapped sch value=="+mappedSchedule);
			 for (int i = 0; i < aliasToValueMapList.size(); i++) {
				 Map<String, Object> list = aliasToValueMapList.get(i);
				 //int schedule_id=Integer.parseInt(list.get("schedule_id").toString());
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

			
			
//			 isSiuccess=daoObject.saveDetailsWebserviceCall(depotid,org_name,id,service_type_id,form_four_id,
//						schedulelist1,schedule_number_name,start_time,end_time,shift_type_id,route_id,route_number,route_direction,trip_number,vehiclelist1,vehicleno,is_dead_trip,device_id);
				 isSiuccess=saveDetailsScheduleMapping(depotid,org_name,id,service_type_id,schedulelist1,schedule_number,schedule_type_name,vehiclelist1,vehicleno,device_id,division,usrsessionid);
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
			 

			 VtsDataDAO vvt = VtsDataDAO.getInstance();
				divisionlist = vvt.getDivisionName();
	     
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("return==="+returnString);
		return returnString;
		
//			return "input";
	}
	String schname1 = "";
	String ismapped1 = "";
	String schname = "";
	String ismapped = "";
	
	public String checkScheduleNoScheduleMappingWebServiceCall(String vehicleNo, String Schedule_name ,int scheduleid)
	{
		
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		// TODO initialize WS operation arguments here
		// Query To Get Schedule Header Information..
//		System.out.println("rbKey==="+rbKey);
		
		
		
		if(!vehicleNo.equals("NA")){
		try
		{
//			System.out.println("in check 1");
			Session session1 = HibernateUtil.getSession("");
		
			//VtsResponse6 alertresult = port.getScheduleNoScheduleMappingWebServiceCall(vehicleNo,Schedule_name,scheduleid,rbKey);
			String qur="select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle_new where schedule_no='"+scheduleid+"' ";
//
			Query qury2 = session1.createSQLQuery(qur)
		  			.addScalar("schedule_name", Hibernate.STRING);
//			System.out.println("check already== "+qury2);
			//List results = qury2.list();
			qury2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qury2.list();
			
			String schname="";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				 JSONArray ja = new JSONArray();
				   schname = list.get("schedule_name").toString();
			}
//			System.out.println("sch name is-------"+schname);
//			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//				 JSONArray ja = new JSONArray();
//				 schname =  alertresult.getWaybillDetails().get(i).getSCHEDULENAME();
//			}
			if(aliasToValueMapList.size()==0){
//			System.out.println("size is-----"+alertresult.getWaybillDetails().size());
			ismapped="";
	}
			else 
			{
				ismapped=schname;
			}
			}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
//			if(session != null){
//				session.close();
//			}
		}	
		}
		return ismapped;
	}
	
	public String checkVehicleScheduleMappingWebServiceCall(String vehicleNo, int Schedule_id,int schduleTypeId, String schedule_name)
	{
		
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		// TODO initialize WS operation arguments here
		// Query To Get Schedule Header Information..
		
//		System.out.println("sch id===="+schduleTypeId);  //  new schedule schedule_type id
		int schedule_type_id=0;
		if(!vehicleNo.equals("NA")){
		/*try
		{
			System.out.println("check 2");
			VtsResponse6 alertresult = port.getScheduleMappingDetailsEditWebservicecall(vehicleNo,Schedule_id,shiftId,rbKey);
//			String qur="select IFNULL(schedule_name,'') as schedule_name from pisdb.schedule_mapping_vehicle where vehicle_no='"+vehicleNo+"' ";
//
//			Query qury2 = session.createSQLQuery(qur)
//		  			.addScalar("schedule_name", Hibernate.STRING);
//			List results = qury2.list();
			String schname="";
//			System.out.println("sch name is-------"+schname);
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				 JSONArray ja = new JSONArray();
				 schname =  alertresult.getWaybillDetails().get(i).getSCHEDULENAME();
			}
			if(alertresult.getWaybillDetails().size()==0){
//			System.out.println("size is-----"+alertresult.getWaybillDetails().size());
			ismapped="";
	}
			else 
			{
				ismapped=schname;
			}
			}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
//			if(session != null){
//				session.close();
//			}
		}	
		
		*/
		
		try
		{
			Common common=new Common();
			Session session1 = HibernateUtil.getSession("");
			  String schType = "select schedule_type_id from schedule_mapping_vehicle_new WHERE vehicle_no='"+vehicleNo+"' limit 1";
			 if(common.getDBResultStr(session1, schType, "schedule_type_id").equals("")){
				  schedule_type_id = 0;

			 }else{
				  schedule_type_id = Integer.parseInt(common.getDBResultStr(session1, schType, "schedule_type_id"));
			 }
//			  System.out.println("sche type id==="+schedule_type_id);    // already existed schedule schedule_type_id
			 
			  if(schedule_type_id !=4 && schedule_type_id !=1){     // if no night service and general shift then assign any schedule
//				  System.out.println("no night ser and gen shift");
			String qur="select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle_new where " +
					" vehicle_no='"+vehicleNo+"' and schedule_no!='"+Schedule_id+"' ";

			Query qury2 = session1.createSQLQuery(qur)
		  			.addScalar("schedule_name", Hibernate.STRING);
			qury2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qury2.list();
			
			String schname="";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				 JSONArray ja = new JSONArray();
				   schname = list.get("schedule_name").toString();
			}

			if(aliasToValueMapList.size()==0){      // no Schedule then assign any schedule
			ismapped=""; 
	}
			else 
			{
				ismapped=schname;       
			}
			}else{     // if in night service or general shift schedule you are assigning to other schedule 
				String scheduleno = "select count(distinct schedule_no) schedule_no from schedule_mapping_vehicle_new WHERE vehicle_no='"+vehicleNo+"' ";
				  int schedulNo = Integer.parseInt(common.getDBResultStr(session1, scheduleno, "schedule_no"));
//				  System.out.println("sche allotted is ==="+schedulNo);
				  if(schedulNo !=2 ){     // if vehicle is already mapped with 2 schedule (1-ns,2-gs)
				if(schedule_type_id == 4){        // in night shift only gen shift sch can mapped 
					if(schduleTypeId ==1){
//					System.out.println("assigned to gen==");
					String scheduleType = "select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle_new WHERE vehicle_no='"+vehicleNo+"' and schedule_type_id=1 limit 1";
					  String schedulname = common.getDBResultStr(session1, scheduleType, "schedule_name");
					  if(schedulname.equalsIgnoreCase("") || schedulname == null){
						  ismapped="";
					  }else{
						  ismapped = schname;
					  }
				}else{
					ismapped = schname;
				}
				}else if(schedule_type_id == 1){   // in gen shift only allow to mapped night service
//					System.out.println("in gen if");
					if(schduleTypeId == 4){
//					System.out.println("assigned to night==");
					String scheduleType = "select IFNULL(schedule_name,'') as schedule_name from schedule_mapping_vehicle_new WHERE vehicle_no='"+vehicleNo+"' and schedule_type_id=4 limit 1";
					  String schedulname = common.getDBResultStr(session1, scheduleType, "schedule_name");
//					  System.out.println("sche name==="+schedulname);
					  if(schedulname.equalsIgnoreCase("") || schedulname == null){
						  ismapped="";
					  }else{
						  ismapped = schname;
					  }
					
				}else{
//					already vehicle mapped with 2 schedule (1-ns,2-gs) then not able to assign to other schedule
					ismapped=schedule_name;
				}
					
				}
				  else{
//					  if not ns and gs 
					  ismapped = schedule_name;
				  }
				  }else{
//					  more than 2 sch cant assigned 
					  ismapped = schedule_name;
				  }
				
//				ismapped="";
			}
		}
		catch(Exception e){
			e.printStackTrace();
			ismapped = schname;
		}
		finally{
//			if(session != null){
//				session.close();
//			}
		}	
		
		}
		return ismapped;
	}
	
	
	boolean isSuccess = false;
	public boolean saveDetailsScheduleMapping(int depotid,String org_name,int schedule_type_id,int service_type_id,int schedulelist1,String schedule_number,String schedule_type_name,String vehicle_id,String license_number,String device_id,String division,int user_id)
	{
		 HttpServletRequest request=ServletActionContext.getRequest();
		Session session = null;
		Session sessionITS=null;
//		Transaction transaction = null;
		int resultId;
		//session =  HibernateUtilVtu.getSession("");
		sessionITS = HibernateUtil.getSession("hibernate.cfg.xml"); //HibernateUtil.getSession("");
		
		try
		{

			Transaction tx=sessionITS.beginTransaction();

			
			System.out.println(depotid+"--"+org_name+"--"+schedule_type_id+"--"+service_type_id+"--"+schedulelist1+"--"+
                  schedule_number+"--"+vehicle_id+"--"+license_number);
			String hql = "INSERT INTO schedule_mapping_vehicle_new (depot_id,depot_name,schedule_type_id,service_type_id,schedule_no,schedule_name,vehicle_id,vehicle_no,device_id,inserted_by,updated_date)  VALUES ("+depotid+", '"+org_name+"', "+schedule_type_id+","+service_type_id+","+schedulelist1+",'"+schedule_number+"'," +
							" "+vehicle_id+",'"+license_number+"','"+device_id+"',"+user_id+",now())";
			 Query query= sessionITS.createSQLQuery(hql);
			 int count=query.executeUpdate();
//			10--Depot-07--3--3--12325--6164--V-335E/26-All Days--20:50:00--5--13512--KBS-D-7--DOWN--5--1366--KA01F3924
			System.out.println("Count===="+count);
			 if(count>0){
			 tx.commit();
			 isSuccess = true;	
			 }else {
				 tx.rollback();
				 isSuccess = false;	
			 }
					
		}
		catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(sessionITS != null){
				sessionITS.close();
			}
			
		}			
		
		return isSuccess;	
	}
	
	
	public String getScheduleMappingview()
	{
//		this.setModelTypeList(daoObject.getModelTypeList());
		
if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("null")){
			
		}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("0")){
		}else{
			String msg=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString();
			addActionMessage(msg);
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","null");
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage1","null");
		}

			if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2").toString().equalsIgnoreCase("null")){
				
			}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage3").toString().equalsIgnoreCase("0")){
			}else{
				String msg1=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage2").toString();
				 addActionError(msg1);
				 ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage2","null");
					ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage3","null");
			}
		
		return "success";
	}
	Session session = null;
	@SkipValidation
	public String getScheduleMappingRecordsList() throws IOException{
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();	
	String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

	
	ScheduleMapDAO mapDao=new ScheduleMapDAO();
	
	try{
		System.out.println("Schedule Mapping New.........");
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
		
		
		result=getScheduleMappingRecords1(total,request,dbcol[Integer.parseInt(sCol)],sdir,id);
//		System.out.println("result---------"+result);
		out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;

}
	
	public JSONObject getScheduleMappingRecords1(int total, HttpServletRequest request,String col,String dir, String orgchartid){
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
//		Session session = HibernateUtilVtu.getSession("");
		Session session1 = HibernateUtil.getSession("");
		try{
			String sql;
//			
			
			sql="SELECT schedule_no as schedule_id,schedule_name,IFNULL(s.schedule_number,'') as schedule_no,st.schedule_type_name as schedule_type_name,"+
   		        " vehicle_no,sm.depot_name as depot_name,occ.org_name as org_name from schedule_mapping_vehicle_new sm " +
   		        " left join schedule s on s.schedule_id=sm.schedule_no "+
   		        "left join schedule_type st on st.schedule_type_id=sm.schedule_type_id "+
   		       // "left join vehicle v on v.vehicle_id=sm.vehicle_id "+
                "left join org_chart oc on oc.org_chart_id=sm.depot_id "+
                "left join org_chart occ on occ.org_chart_id=oc.parent_id where sm.status='ACTIVE' and sm."+orgchartid+" ";
			
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
				search_term=search_term.trim();
//				sql += " and (schedule_no like '%"+search_term+"%'";
				sql += " and (schedule_name like '%"+search_term+"%'";
				//sql += " OR schedule_type_name like '%"+search_term+"%'";
				sql += " OR vehicle_no like '%"+search_term+"%'";
				sql += " OR depot_name like '%"+search_term+"%') ";
			}
			sql+=" group by sm.schedule_name";
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			
		  // System.out.println("query........."+sql);
		  	 Query query = session1.createSQLQuery(sql)
		  			.addScalar("schedule_id", Hibernate.INTEGER)
						 .addScalar("schedule_no", Hibernate.STRING)
						 .addScalar("schedule_name", Hibernate.STRING)
						 .addScalar("schedule_type_name", Hibernate.STRING)
						 .addScalar("vehicle_no", Hibernate.STRING)
		  	 			 .addScalar("depot_name",Hibernate.STRING)
		  	 			 .addScalar("org_name",Hibernate.STRING);
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("schedule_id")+ "' value='"+ rs.get("schedule_id") + "'/>");
					/*data-set='#sample_2 .checkboxes' */
//					ja.add(rs.get("schedule_id"));
					//ja.add(rs.get("schedule_no").toString());
					ja.add(rs.get("schedule_name").toString());
					//ja.add(rs.get("schedule_type_name").toString());
					ja.add(rs.get("vehicle_no").toString());
					ja.add(rs.get("depot_name").toString());
					ja.add(rs.get("org_name").toString());
//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				int cnt=0;
				totalAfterFilter = aliasToValueMapList.size();
				result.put("aaData", array);
				String search_term2= request.getParameter("sSearch");
				

				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalScheduleMappingRecords(request,col,dir,orgchartid);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session1 != null) {
				session1.close();
			}
		}
		return result;
	}
	
	public int getTotalScheduleMappingRecords(HttpServletRequest request,String col,String dir, String orgchartid) {
		int cnt=0;
//		Session session = HibernateUtilVtu.getSession("");
		Session session1 = HibernateUtil.getSession("");
	 
		try{
			String sql;
			
			// we changed the query for pisdb.schedule_mapping_vehicle to its schedule_mapping_vehicle
			sql="SELECT schedule_no ,schedule_name,st.schedule_type_name as schedule_type_name,"+
	   		        " vehicle_no,sm.depot_name as depot_name,occ.org_name as org_name " +
	   		        " from schedule_mapping_vehicle_new sm left join schedule s on s.schedule_id=sm.schedule_no "+
	   		        "left join schedule_type st on st.schedule_type_id=sm.schedule_type_id "+
	   		        //"left join vehicle v on v.vehicle_id=sm.vehicle_id "+
	                "left join org_chart oc on oc.org_chart_id=sm.depot_id "+
	                "left join org_chart occ on occ.org_chart_id=oc.parent_id where sm.status='ACTIVE' and schedule_no is not null and sm."+orgchartid+" ";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
				search_term1=search_term1.trim();
//				sql += " and (schedule_no like '%"+search_term+"%'";
				sql += " and (schedule_name like '%"+search_term1+"%'";
				//sql += " OR schedule_type_name like '%"+search_term1+"%'";
				sql += " OR vehicle_no like '%"+search_term1+"%'";
				sql += " OR depot_name like '%"+search_term1+"%') ";
			}
			sql +="group by sm.schedule_name";
			Query query = session1.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt =	aliasToValueMapList.size();
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
//			if (session != null) {
//				session.close();
//			}
		}
		return cnt;
	
}
	
	public String deleteScheduleMapping() {
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid = (Integer) request.getSession()
				.getAttribute("userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,	"getScheduleMappingView.action");
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
	
	if(deleteScheduleMapping(scheduleId1)){
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
	
	public boolean deleteScheduleMapping(int scheduleMapId)
	{
		System.out.println("in delete schedule dao");
		System.out.println("id coming is"+scheduleMapId);
		String status="error";
		ScheduleMap schMapData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("");
			//for pisd.schedule_mapping_vehicle query
			//Query query = session.createSQLQuery("delete from pisdb.schedule_mapping_vehicle where schedule_no=?");
			Query query = session.createSQLQuery("delete from schedule_mapping_vehicle_new where schedule_no=?");
		    query.setInteger(0, scheduleMapId);
		    int deleted = query.executeUpdate();
			isSuccess = true;
			
		}catch(Exception e){
			status="error";
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			
			return isSuccess;
		}
	}
	
	

}
