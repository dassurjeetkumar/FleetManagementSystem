package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;

	public class SystemHealthCheckReportAction {
		
//	private Map<Integer, String> divisionlist;
	public String startdate;
	private String depotlist1;
//	public String divisionlist1;
	private Map<Integer, String> divisionlist1;
	String str = "";
	String path = "";
	char ft = 15;

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	

//	public Map<Integer, String> getDivisionlist() {
//		return divisionlist;
//	}
//
//	public void setDivisionlist(Map<Integer, String> divisionlist) {
//		this.divisionlist = divisionlist;
//	}
	
	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	
	private Map<Integer, String> alertlist1;

	
//	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString = new StringBuffer();
	
	public String execute() throws Exception {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}
		
		 public static String rbKey = String.valueOf(getSysParameterForVts());
		 
		public String getSystemHealthData(){
		try {
			HttpServletRequest req=ServletActionContext.getRequest();
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			Common common = new Common();
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			Transaction transaction=null;
			Transaction transaction1=null;
			Session session1 = null;
			Session session2 = null;
			String startDate=req.getParameter("startdate");
		
String enddate=req.getParameter("enddate");
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate1 = format.parse(startDate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
			String fromdate = fomat2.format(startDate1).toString();
			String depotId=req.getParameter("depotlist1");
			Date enddate1 = format.parse(enddate);
			String todate = fomat2.format(enddate1).toString();
			String deviceId=req.getParameter("device_id");
		
			
//			String filePath = "Report/";

//			String fileName = "ActiveVehicleReport.txt";
			String queryy="SELECT `lICENCE_NUMBER` as VEHICLE_NO, `org_name`, `DEVICE_ID`, `Schedule_number` as SCHEDULE_NAME,DATE_FORMAT(record_date,'%d-%m-%y') as DATE "
        + "FROM dash_board_active_vehicle_daily "
        + " where record_date  between '"+fromdate+"' and '"+todate+"' and depot_id="+depotId+" and device_id="+deviceId+" group by record_date order by record_date";

			session1 =HibernateUtilVtu.getSession("");
			transaction =session1.beginTransaction();
			 Query query = session1.createSQLQuery(queryy).addScalar("VEHICLE_NO").addScalar("org_name").addScalar("DEVICE_ID")
				 		.addScalar("SCHEDULE_NAME").addScalar("DATE");
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		 List<Map<String, Object>> aliasToValueMapList = query.list();
		 
		 String queryy2="SELECT `lICENCE_NUMBER` as VEHICLE_NRd, `org_name` as org, `DEVICE_ID` as device,DATE_FORMAT(record_date,'%d-%m-%y') as DATE1 "
			        + "FROM dash_board_Nrd_vehicle_daily "
			        + " where record_date  between '"+fromdate+"' and '"+todate+"' and depot_id="+depotId+" and device_id="+deviceId+" group by record_date order by record_date";

						session2 =HibernateUtilVtu.getSession("");
						transaction1 =session1.beginTransaction();
						 Query query2 = session1.createSQLQuery(queryy2).addScalar("VEHICLE_NRd").addScalar("org").addScalar("device")
							 		.addScalar("DATE1");
						 query2.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					 List<Map<String, Object>> aliasToValueMapList1 = query2.list();
			
			regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>VTS System/Health Check Report</br>From Date:- "
					+ fromdate + " To Date:-" + todate + "</h4></br></div>");

			regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
					+ runDateTime + "</div></div>");

//			String nwkr = ""
//					+ "                                   Active Vehicle    " +
//
//					"\n                                    From Date:"
//					+ fromdate + "  End Date:" + todate
//					+ "                                              ";
//			regionTypeAjaxString.append("<div ><table align='center'><tr>Device No : </b>"+deviceId+"</tr></table></div>");
			
//			regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
//			regionTypeAjaxString +="<td align='center' width='10%'><b>Sl No</b></td><td align='center' width='20%'><b>DENOMINATION</b></td>";
//			regionTypeAjaxString +="<td align='center' width='20%'><b>NO. OF TICKETS</b></td><td align='center' width='20%'><b>TOTAL REVENUE</b></td></tr>";
			
			regionTypeAjaxString.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
//			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString.append("<td align='center' width='10%'><b>Sl No</b></td><td align='center' width='20%'><b>Date</b></td><td align='center' width='20%'><b>Status</b></td>");
//			regionTypeAjaxString.append("<thead><tr><th >Sr.No.</th><th >Date</th><th>Status</th>"
//					+ "</tr></thead><tbody>");

//			String headingprint = " "
//					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n"
//					+ add("SNo", 3)
//					+ "|"
//					+ add("Depot Name", 14)
//					+ "|"
//					+ add("Vehicle No.", 10)
//					+ "|"
//					+ add("Device Id", 9)
//					+ "|"
//					+ add("IST Date", 9)
//					+ "|"
//					+ " "
//					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";

//			String path = realpath + filePath + fileName;
//			str += ft + nwkr + add(headingprint, 5);
			Date date1 = new Date();
     		Date addedDate2 = addDays(date1, 1);
     		int j=0;
     		 for (int i = 0; i < aliasToValueMapList1.size(); i++) {
     			j=i+1;
					
					regionTypeAjaxString .append("<tr>");
					Map<String, Object> list = aliasToValueMapList1.get(i);
					JSONArray ja = new JSONArray();
//					int j=i+1;
					String date="";String status="";
					date=list.get("DATE1").toString();
					
					regionTypeAjaxString.append("<td>"+j+"</td>");
					regionTypeAjaxString.append("<td>"+date+"</td>");
					regionTypeAjaxString.append("<td>NRD</td>");
//					if(date.equals(fromdate)){
//						status="active";
					
//					regionTypeAjaxString.append("<td>"+status+"</td>");
//					}else{
//						status="NRD";
//					}
					
				}
				 regionTypeAjaxString.append("</tr>");
     		
     		
     	
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
					
				regionTypeAjaxString .append("<tr>");
				Map<String, Object> list = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				 
				String datee="";String status="";
				datee=list.get("DATE").toString();
				
				regionTypeAjaxString.append("<td>"+(j+1)+"</td>");
				regionTypeAjaxString.append("<td>"+datee+"</td>");
				regionTypeAjaxString.append("<td>Active</td>");
//				if(date.equals(fromdate)){
//					status="active";
//				
//				regionTypeAjaxString.append("<td>"+status+"</td>");
//				}else{
//					status="NRD";
//				}
				j++;
			}
			 regionTypeAjaxString.append("</tr>");
			 
			 
			 if(aliasToValueMapList.size() ==0 && aliasToValueMapList1.size() ==0){
//				 regionTypeAjaxString.append("<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>");
//				 regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
		    		regionTypeAjaxString.append("<tr>");
					regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
					regionTypeAjaxString.append("</tr>");	
			 }
			
				
			HttpServletResponse response = ServletActionContext.getResponse();
//			int i = 1;
//			int j=1;
			

	/*		if (activeresult.getWaybillDetails().size() == 0) { // if no records
				regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
				regionTypeAjaxString.append("</tr>");
			}*/
			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString);       // to print
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
			
			public static Date addDays(Date d, int days) {
				d.setTime(d.getTime()  - 86400 * 1000);
				return d;
			}
		
	static String add(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
		}
		a1 = a1 - m1;
		for (int i = 0; i <= a1; i++) {
			sb.append(" ");
		}
		String sb1 = sb.toString();
		return sb1;
	}

	static String addMoney(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		StringBuilder sb2 = new StringBuilder();

		// String sb1 =
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
			return str1;
		}
		a1 = a1 - m1;
		for (int i = 0; i < a1; i++) {
			sb2.append(" ");
		}
		sb2.append(sb);
		String sb1 = sb2.toString();
		return sb1;
	}
		
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
			session.close();
		}
		return param;
	}
	
	public String getVehicle() {

		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getVehicleID(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
//		regionTypeAjaxString += "<option  value='ALL'>------ALL------</option>";
		for (int i = 0; i < l1.size(); i++) {
			String vehiclearr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<option value=" + vehiclearr[0] + ">"
					+ vehiclearr[1] + "(" + vehiclearr[0] + ")" + "</option>";
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

	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public Map<Integer, String> getAlertlist1() {
		return alertlist1;
	}

	public void setAlertlist1(Map<Integer, String> alertlist1) {
		this.alertlist1 = alertlist1;
	}
			
	}
