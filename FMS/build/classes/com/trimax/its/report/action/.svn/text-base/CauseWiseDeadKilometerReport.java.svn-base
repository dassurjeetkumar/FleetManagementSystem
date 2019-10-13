package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CauseWiseDeadKilometerReport {

	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	    public String enddate;
	
	    private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;
		private String depotlist1;
		private String divisionlist1;
		
	


	public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}


	public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}


	public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
		}


		public Map<Integer, String> getDepotlist() {
			return depotlist;
		}


		public void setDepotlist(Map<Integer, String> depotlist) {
			this.depotlist = depotlist;
		}


	public String getStartdate() {
			return startdate;
		}


		public void setStartdate(String startdate) {
			this.startdate = startdate;
		}


		public String getEnddate() {
			return enddate;
		}


		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}
		char fl = 18;
		char f2 = 12;
		int pagi = 1;

		int FULL_LEN = 120;
		int HALF_LEN = 60;

		double totalAmmount=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);

		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		public String getCausewiseDeadkilometer()
		{
			try {
			
				Common common = new Common();
				String date1=common.getDateFromPicker(startdate);
				String date2=common.getDateFromPicker(enddate);
			    String division1= divisionlist1;
			    String depot1= depotlist1;
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			
			Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
				Query qry1=session1.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip",Hibernate.STRING)
						.addScalar("central_uname")
						.addScalar("central_pwd");
				
				qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = qry1.list();	
					String depotdb="";
					String depotIp="";
					String user="";
					String password="";
					for (int j = 0; j < aliasToValueMapList.size(); j++) {
						Map<String, Object> aliasValue = aliasToValueMapList.get(j);
						 depotdb=aliasValue.get("depotname").toString();
						 depotIp=aliasValue.get("central_ip").toString();
						 user=aliasValue.get("central_uname").toString();
						 password=aliasValue.get("central_pwd").toString();
					}
				 Class.forName("com.mysql.jdbc.Driver");
				 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
				
			 String sql="select glog.gen_logsheet_no AS logsheet_no,sch.schedule_number AS schedule_number,r.route_number AS route_number,tm.bag_code AS bag_code,v.license_number AS license_number,"+
	                    " four.total_dead_km as scheduled_dead,cdk.depot_dead_km as depot_dead,"+
	                    " CASE when glog.status='cc_captured' then glog.runningkm"+
	                    " END depot_to_cws,cdk.bd_attend_km AS bd_attend_km,cdk.crew_change_km AS crew_change_km," +
	                    "cdk.bank_counter_km AS bank_counter_km,cdk.vehicle_exchange_km AS vehicle_exchange_km," +
	                    "glog.deviation_km AS deviation_km,cdk.others AS others"+
	                    " from causewise_dead_km cdk "+
	                    " inner join gen_logsheet glog on glog.gen_logsheet_id=cdk.gen_logsheet_id "+
	                    " inner join form_four four on four.form_four_id=glog.schedule_no "+
	                    " inner join schedule sch on sch.schedule_id=four.schedule_number_id "+
	                    " inner join route r on r.route_id=four.route_id "+
	                    " inner join Waybill_Details wd on wd.waybil_Id=glog.waybill_id "+
	                    " inner join ticketbag_master tm on tm.depot_id=wd.depot_id "+
	                    " inner join vehicle v on v.vehicle_id=glog.vehicle_id where glog.gen_logsheet_date BETWEEN '"+date1+" ' AND '"+date2+"'";

			 System.out.println(sql);
			 rs = stmt.executeQuery(sql);

			 double Totalvalues=0.0;
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Conductor and Driver Check List Report </br> From Date:- "+startdate+" To Date:- "+enddate+"</br></h4></div>");

			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        

			regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>S.No</th><th>LogSheet No</th><th>Schedule No</th><th>Route</th><th>Bag Number</th><th>Vehicle Number</th><th>Scheduled dead</th><th>Depot dead</th>" +
					"<th>Depot to CWS</th><th>Call bus</th><th>B/D atttend</th><th>Crew change</th><th>Bank / Counter</th><th>Vehilcle exchange</th><th>Deviation</th><th>others</th><th>Total Dead Kms</th>"+"</tr></thead><tbody>");
			

			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
 					 while (rs.next()) {

 			   regionTypeAjaxString1.append("<tr>");
 				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("logsheet_no") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("schedule_number") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("route_number") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("bag_code") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("license_number") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("scheduled_dead") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("depot_dead") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("depot_to_cws") +"</td>");
				regionTypeAjaxString1.append("<td align='right'></td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("bd_attend_km") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("crew_change_km") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("bank_counter_km") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("vehicle_exchange_km") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("deviation_km") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("others") +"</td>");
				
				String  logsheetno = rs.getString("logsheet_no").toString();
				String scheduleno = rs.getString("schedule_number").toString();
				String routeno = rs.getString("route_number").toString();
				String bagno = rs.getString("bag_code").toString();
				String vehicleno = rs.getString("license_number").toString();
				String scheduledead = rs.getString("scheduled_dead").toString();
				String depotdead = rs.getString("depot_dead").toString();
				String depot_cws = rs.getString("depot_to_cws").toString();

				String callbus_km = "";

				String attendkm = rs.getString("bd_attend_km").toString();
				String crew_change = rs.getString("crew_change_km").toString();
				String bank_km = rs.getString("bank_counter_km").toString();
				String vehicle_km = rs.getString("vehicle_exchange_km").toString();
				String deviation_km = rs.getString("deviation_km").toString();
				String others = rs.getString("others").toString();
				Totalvalues=Double.parseDouble(scheduledead)+Double.parseDouble(depotdead)+Double.parseDouble(depot_cws)+Double.parseDouble(attendkm)+Double.parseDouble(crew_change)+Double.parseDouble(bank_km)+Double.parseDouble(vehicle_km)+Double.parseDouble(deviation_km)+Double.parseDouble(others);
				regionTypeAjaxString1.append("<td align='right'>"+ Totalvalues +"</td>");
				regionTypeAjaxString1.append("</tr>");
				
			
 					 }
 					 PrintWriter out;
                     String p=str;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}

			return null;
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

	        //String sb1 =
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
	
}
