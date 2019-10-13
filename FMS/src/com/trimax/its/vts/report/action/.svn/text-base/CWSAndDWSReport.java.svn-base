package com.trimax.its.vts.report.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse;
import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.device.dao.ShowDeviceDashboardDoa;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;

public class CWSAndDWSReport extends ActionSupport {
	
private Map<Integer, String> divisionlist;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	String path="";
	char ft = 15;
	String str="";
	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double earnAmmount=0.0;
	double dedtotal=0.0;
	int subtotalValues=0;
	double totalammount=0.0;
	String regionTypeAjaxString = "";
	
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
	
public static String rbKey = String.valueOf(getSysParameterForVts());
	
	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			session.close();
		}
		return param;
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
		divisionlist = getDivisionData(orgtypeid,id);

		return "success";
	}
	
	public Map<Integer, String> getDivisionData(String orgtypeid,String id) {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql="select org_chart_id,org_name from org_chart  where  "+id+" and deleted_status=0 order by org_name";
			
//		Query query = session
//				.createQuery("select org_chart_id,org_name from org_chart  where org_type_id="+orgtypeid+" and "+id+" and deleted_status=0 order by org_name");
		Query query = HibernateUtil.getSession("").createSQLQuery(sql).addScalar("org_chart_id",Hibernate.STRING)
				.addScalar("org_name", Hibernate.STRING);;
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<Integer, String>> list = query.list();
		
		resultMap.put(0, "---ALL---");
			for (int i = 0; i < list.size(); i++) {
//				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
				resultMap.put(Integer.parseInt(list.get(i).get("org_chart_id")), list.get(i).get("org_name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	public String getCwsAndDwsReport(){
		System.out.println("Enter into CWS And  DWS");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		//PrintWriter out = null;
	  ShowDeviceDashboardDoa doa = new ShowDeviceDashboardDoa();
//		JSONObject result = new JSONObject();
	  String result="";
		Date FromDate = new Date();
		String startdate=req.getParameter("startdate");
		String enddate=req.getParameter("enddate");
		String depot=req.getParameter("depot");
		String divison=req.getParameter("divison");
		String reason=req.getParameter("reason");
		System.out.println("startdate===="+startdate);
		System.out.println("enddate===="+enddate);
		System.out.println("depot===="+depot);
		System.out.println("divison===="+divison);
		System.out.println("reason===="+reason);
		
		try {
			Common common=new Common();
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println("from_date===="+common.getDateFromPicker(startdate));
			System.out.println("to_date===="+common.getDateFromPicker(enddate));
			//resp.setContentType("application/json");
			//out = resp.getWriter();
			result = getWebDataForCwsAndDwsReport(1, req, "", "",
					common.getDateFromPicker(startdate),
					common.getDateFromPicker(enddate),reason,depot,divison);
			
			
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			
			
				out = response.getWriter();
				out.print(result);
	
			
			
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getWebDataForCwsAndDwsReport(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String reason,String depot_id,String division_id) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common1 = new Common();
		//String ticketDate1=common1.getDateFromPicker(ticket_date);
		String activeDevice="";
		String device_type_id="";
		int device_type_id1=0;
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		try {
			//System.out.println("Start Time"+new Date());
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			System.out.println("orgtypeid===="+orgtypeid);
			System.out.println("orgchartid===="+orgchartid);
		
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
			if(division_id.equals("0")){
//				if(depot_id.equals("0")){
////					id = "oc.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
////							+ division_id + "')";
//				
					id="oc.org_chart_id!=0";
//				}else {
//					id = "oc.org_chart_id in('" + depot_id + "')";
//				}
			}else {
				if(depot_id.equals("0")){
					id = "oc.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
							+ division_id + "')";
				
//					id="oc.org_chart_id!=0";
				}else {
					id = "oc.org_chart_id in('" + depot_id + "')";
				}
				
			}
			
			System.out.println("Id==="+id);
			System.out.println("reason==="+reason);
			JSONArray array = new JSONArray();
			
			
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 
			 String sql="select vehicle_no,device_serial_number,status_cause,reason,org_name,from_date,to_date from Vehicle_Status v "+
					 	 " inner join vehicle v1 on v1.license_number=v.vehicle_no "+
					 	 " inner join org_chart oc on oc.org_chart_id=v1.org_chart_id "+
					 	 " inner join vehicle_device vd on vd.vehicle_id=v1.vehicle_id "+
					 	 " inner join device d on d.device_id=vd.device_id "+
					     " where status_cause = 'CW' and DATE(from_date) between '"+fromDate+"' and '"+tillDate+"' and "+id+"";
			
			 Query query = session1.createSQLQuery(sql).addScalar("vehicle_no").addScalar("device_serial_number").addScalar("status_cause").addScalar("reason").addScalar("org_name").addScalar("from_date").addScalar("to_date");
			  
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String,Object>> aliasToValueMapList = query.list();
			
		
			
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			//alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
			
//		     NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
//						 com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
//			VtsResponseNew  alertresult= port.webGetCWSANDDWSReport(fromDate,tillDate,reason,rbKey,id);
			 
			 System.out.println("aliasToValueMapList..size()===="+aliasToValueMapList.size());
			 
			 
				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>CWS AND DWS REPORT ";
//				//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</h4></div></div>";
		        
		        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>SlNo</th><th>Vehicle No</th><th>Device Id</th><th>Depot Name</th><th>Status_Cause</th><th>Reason</th><th>From_Date</th><th>To_Date</th></tr></thead><tbody>";
		       

//		        regionTypeAjaxString += "<tr><th>SlNo</th><th>Vehicle No</th><th>Device Id</th><th>Depot Name</th><th>Signal_Strength</th><th>Internal_Battery_VTG</th><th>Ext_Battery_VTG</th><th>Ignition_Status</th><th>IST_Date</th>" +
//		        		"<th>Speed</th><th>Speed_KMPH</th><th>Status_Cause</th><th>Reason</th><th>From_Date</th><th>To_Date</th></tr>";

		        if(aliasToValueMapList.size()==0){
		          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
		               regionTypeAjaxString += "<tr>";
		               regionTypeAjaxString += "<td colspan='8' align='center'><b>No Result Found</b></td>";
		              
		               regionTypeAjaxString += "</tr>";
		          }else{
			    for (int i = 0; i < aliasToValueMapList.size(); i++) {
				  regionTypeAjaxString +="<tr>";
				 // VtsDataModelNew rs = alertresult.getVtsDatamodelnew().get(i);
				  Map<String,Object> list = aliasToValueMapList.get(i);
				 JSONArray ja = new JSONArray();
//				 ja.add(i+1);
//				ja.add(rs.getDEVICEID().toString());
//				ja.add(rs.getOrgName().toString());
//				ja.add(rs.getLICENCENUMBER().toString());
//
//				array.add(ja);
				 
				 int j=i+1;
				 regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
				 regionTypeAjaxString +="<td align='right'>"+list.get("vehicle_no").toString()+"</td>";
				 regionTypeAjaxString +="<td align='right'>"+list.get("device_serial_number").toString()+"</td>";
				 regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
				 regionTypeAjaxString +="<td align='right'>"+list.get("status_cause").toString()+"</td>";
				 regionTypeAjaxString +="<td align='right'>"+list.get("reason").toString()+"</td>";
				 regionTypeAjaxString +="<td align='right'>"+list.get("from_date").toString()+"</td>";
				 //regionTypeAjaxString +="<td align='right'>"+list.get("to_date").toString()+"</td>";
				 System.out.println("list.get(to_date)==="+list.get("to_date"));
				 if(list.get("to_date")==null || list.get("to_date").toString()=="null"){
				 regionTypeAjaxString +="<td align='right'>N/A</td>";
 
			 }else {
			 regionTypeAjaxString +="<td align='right'>"+list.get("to_date").toString()+"</td>";
			 }
				// regionTypeAjaxString +="<td align='right'>"+list.get("").toString()+"</td>";
//				 regionTypeAjaxString +="<td align='right'>"+rs.getSPEED()+"</td>";
//				 regionTypeAjaxString +="<td align='right'>"+rs.getSPEEDKMPH()+"</td>";
//				 regionTypeAjaxString +="<td align='right'>"+rs.getStatusCause()+"</td>";
//				 regionTypeAjaxString +="<td align='right'>"+rs.getReason()+"</td>";
//				 regionTypeAjaxString +="<td align='right'>"+rs.getFromDate().toString()+"</td>";
//				 if(rs.getToDate().equalsIgnoreCase(null) || rs.getToDate()=="null"){
//					 regionTypeAjaxString +="<td align='right'>N/A</td>";
//	 
//				 }else {
//				 regionTypeAjaxString +="<td align='right'>"+rs.getToDate().toString()+"</td>";
//				 }
				 regionTypeAjaxString +="</tr>";
				//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
				//printDuty.setBlockName(list.get("block_name").toString());
			  }
			  
			  regionTypeAjaxString += "</table></div> </div>  ";
		          }
		
		        

//				HttpServletResponse response = ServletActionContext.getResponse();
//				PrintWriter out;
//				
//				
//					out = response.getWriter();
//					out.print(regionTypeAjaxString);
		
		

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return regionTypeAjaxString;
	}
	

}
