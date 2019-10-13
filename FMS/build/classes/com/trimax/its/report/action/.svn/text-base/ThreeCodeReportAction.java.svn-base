package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ThreeCodeReportAction {
	
	
	
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

		
		public String getThreeCode()
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
		
			 //String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
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
				 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/"+depotdb+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
				
				
				String sql="select DATE_FORMAT(wpc.createdDate,'%d-%m-%Y') createdDate, wpc.waybill_no,ifnull(tm.bag_code,'') Bag_No,dv.device_serial_number," +
						"emp.EMPLOYEE_NAME ,emp.TOKEN,wpc.collection_amount,wpc.threecode_amount ,wpc.status,ifnull(wpc.remarks,'') remarks,ifnull(cause_type_name,'') cause_type_name  from   " +
						"waybill_pending_collection wpc inner join " +
						"Waybill_Details wd on wpc.waybill_no=wd.waybill_No " +
						"inner join cause_type cause on cause.cause_type_id=wpc.cause_type "+
						"left join ticketbag_master tm on wd.Bag_No=tm.ticketbag_id left join employee emp on wd.conductor_Id=emp.EMPLOYEE_ID " +
						"inner join device dv on  dv.device_id=wd.ETM_No  WHERE wpc.createdDate between ('"+date1+" 00:00:00') AND ('"+date2+" 23:59:59') " +
								"and wpc.collection_amount!=0";

			 rs = stmt.executeQuery(sql);

		
			 regionTypeAjaxString +="<div id='header' style='display: none;'><div align='center'>"+depot1+" </br>Three Code Report </br></h4></div>";

			 regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		        

			 regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Date</th><th>Bag No</th><th>ETM No</th><th>WayBill No</th><th>Conductor Token</th><th>Conductor Name</th><th>EIA(3 Code Amt)</th><th>DOA(Final 3 Code Amt)</th>" +
						"<th>GPRS (3 Code Amt)</th><th>Difference</th><th>Status</th><th>Reason</th><th>Remarks</th>"+"</tr></thead><tbody>";
			
				Common cm = new Common();
			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
			        double diff=0.0;
 					 while (rs.next()) {

 						regionTypeAjaxString +="<tr>";
 			  regionTypeAjaxString +="<td align='right'>"+i++ +"</td>";
				regionTypeAjaxString +="<td align='right'>"+ rs.getString("createdDate").toString() +"</td>";
				regionTypeAjaxString +="<td align='right'>"+ rs.getString("Bag_No").toString() +"</td>";
				regionTypeAjaxString +="<td align='right'>"+rs.getString("device_serial_number").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+rs.getString("waybill_no").toString()+"</td>";
				
			/*	String sql11="select gpraamount from (SELECT SUM(px_total_amount) gpraamount,waybill_no FROM bmtcGprs.etim_gprs_ticket " +
						"WHERE waybill_no='"+rs.getString("waybill_no").toString()+"' and tkt_printed_flag='Y' AND fare_type Not IN ('NINC') and ticket_no!=0  and `payment_mode` = '0' " +
						" union	SELECT SUM(px_total_amount) gpraamount,waybill_no FROM bmtcGprs.etim_gprs_ticket WHERE waybill_no='"+rs.getString("waybill_no").toString()+"' " +
						"and tkt_printed_flag='Y' AND fare_type Not IN ('NINC') and ticket_no!=0   " +
						") a where a.waybill_no is not null	group by a.waybill_no";*/
				
				String sql11="select gpraamount from ( " + 
						"SELECT SUM(px_total_amount) gpraamount,waybill_no  " + 
						"FROM bmtcGprs.etim_gprs_ticket WHERE waybill_no='"+rs.getString("waybill_no").toString()+"' and tkt_printed_flag='Y' AND fare_type Not IN ('NINC') and ticket_no!=0   " + 
						"and `payment_mode` = '0'  union	SELECT SUM(px_total_amount) gpraamount,waybill_no FROM bmtcGprs.etim_gprs_ticket  " + 
						"WHERE waybill_no='"+rs.getString("waybill_no").toString()+"' and tkt_printed_flag='Y' AND fare_type Not IN ('NINC') and ticket_no!=0    " + 
						"union " + 
						"SELECT SUM(px_total_amount) gpraamount,waybill_no  " + 
						"FROM bmtcGprs.etim_gprs_ticket WHERE waybill_no='"+rs.getString("waybill_no").toString()+"' and tkt_printed_flag='Y' AND fare_type Not IN ('NINC') and ticket_no!=0   " + 
						"and `payment_mode` = '0'  union	SELECT SUM(px_total_amount) gpraamount,waybill_no FROM bmtcGprs.etim_gprs_ticket190219 " + 
						"WHERE waybill_no='"+rs.getString("waybill_no").toString()+"'  and tkt_printed_flag='Y' AND fare_type Not IN ('NINC') and ticket_no!=0  " + 
						") a \n" + 
						"where a.waybill_no is not null	group by a.waybill_no";
				
				
				//Query query1 = session1.createSQLQuery(sql11);
				 String gpraamount = cm.getDBResultStr(session1, sql11, "gpraamount");
				 if(rs.getString("TOKEN")==null || rs.getString("TOKEN").equals("")){
						regionTypeAjaxString +="<td align='right'></td>";

				 }else{
				regionTypeAjaxString +="<td align='right'>"+rs.getString("TOKEN").toString()+"</td>";
				 }
				 if(rs.getString("EMPLOYEE_NAME")==null || rs.getString("EMPLOYEE_NAME").equals("")){
						regionTypeAjaxString +="<td align='right'></td>";

				 }else{
					 regionTypeAjaxString +="<td align='right'>"+rs.getString("EMPLOYEE_NAME").toString()+"</td>";
				 }
//				regionTypeAjaxString +="<td align='right'>"+rs.getString("EMPLOYEE_NAME").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+rs.getString("collection_amount").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+rs.getString("threecode_amount").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+gpraamount+"</td>";
				diff=Double.parseDouble(rs.getString("collection_amount").toString())-Double.parseDouble(rs.getString("threecode_amount").toString());
				regionTypeAjaxString +="<td align='right'>"+diff+"</td>";
				regionTypeAjaxString +="<td align='right'>"+rs.getString("status").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+rs.getString("cause_type_name").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+rs.getString("remarks").toString()+"</td>";
				
				  regionTypeAjaxString +="</tr>";
				
			
 					 }
 					 PrintWriter out;
                    

			out = response.getWriter();
			out.print(regionTypeAjaxString);
			
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}

			return null;
		}

		

}
