package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ActualEtmAmountAndGprsAmountDiffReport extends ActionSupport {

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
		public String value;
		
	


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
//			System.out.println("division........"+divisionlist);	
			return "success";
		}

		
		public String getActualEtmAndGprsAmountReport()
		{
			
			try {
			
				Common common = new Common();
				String date1=common.getDateFromPicker(startdate);
//				String date2=common.getDateFromPicker(enddate);
			    String division1= divisionlist1;
			    String depot1= depotlist1;
			    String depotname=getDepotNames(depot1);
//			    System.out.println("value====="+value);
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
		
			 
//			 String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
//				 Class.forName("com.mysql.jdbc.Driver");
//			 connection = DriverManager.getConnection("jdbc:mysql://10.30.1.158:23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","temp","temp");
//			 
//			 stmt = connection.createStatement();
			 
				String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
				Query qry=session1.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip",Hibernate.STRING)
						.addScalar("central_uname")
						.addScalar("central_pwd");
				
				qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = qry.list();	
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
				// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

				 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();
				
				
				String sql="Select w.waybill_No waybill_No,d.device_serial_number device_serial_number,ETIM_Coll_Amt,ifnull(MAX(ticket_no),0) ticket_no,ifnull(SUM(px_count),0) px_count,Ticket_Audited_Date,ifnull(s.schedule_number,'') as schedule_No," +
						"ifnull(SUM(`px_total_amount`),0) amnt,sp.shift_type_name as shift_type_name from Waybill_Details w "+
                           " left join ticket t on t.waybil_Id=w.waybil_Id and t.fare_type Not IN ('NINC')  " +
						   "inner join form_four ff on ff.form_four_id=w.schedule_No "+
                           " inner join schedule s on s.schedule_id=ff.schedule_number_id "+
                           " inner join shift_type sp on sp.shift_type_id=w.Shift_Type "+
                           "inner join device d on d.device_id=w.ETM_No "+
                           "  where Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' group by w.waybill_No having ETIM_Coll_Amt>0";
              System.out.println(sql);
			 rs = stmt.executeQuery(sql);

		
			 regionTypeAjaxString +="<div id='header' style='display: none;'><div align='center'>DEPOT : "+depotname+" </br>GPRS And ACC-66 Comparision Report </br></h4></div>";

			 regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
			

			 regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Schedule No</th><th>Shift Type</th><th>Waybill No</th><th>ETM No</th><th>ETM Amount(DOA)</th><th>GPRS Amount</th><th>Difference Amount</th><th>Tickets(DOA)</th><th>Tickets(GPRS)</th><th>Passenger Count(DOA)</th><th>Passenger Count(GPRS)</th><th>Audited Date</th><th>Percentage</th>" +
						"</tr></thead><tbody>";
			
				Common cm = new Common();
			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
			        double diff=0.0;
			        double totaldiff=0.0;
			        double totaletm=0.0;
			        double totalgprs=0.0;
			        int ttickets=0;
			        int tgtickets=0;
			        int pasentotal=0;
			        int gprspasentotal=0;
			        double percentage=0.0;
			        String scheduleno="";
			        String waybillno="";
			        String gprsAmount="";
			        String ticketno="";
			        String passenger_count="";
			        String amount="";
 					 while (rs.next()) {

 						//regionTypeAjaxString +="<tr>";
 			 
//				regionTypeAjaxString +="<td align='right'>"+ rs.getString("createdDate").toString() +"</td>";
//				regionTypeAjaxString +="<td align='right'>"+ rs.getString("Bag_No").toString() +"</td>";
//				regionTypeAjaxString +="<td align='right'>"+rs.getString("device_serial_number").toString()+"</td>";
//				regionTypeAjaxString +="<td align='right'>"+rs.getString("waybill_no").toString()+"</td>";
//				System.out.println("Reached here+++++++++");
//				String sql11="Select schedule_no,waybill_no,SUM(px_total_amount)  gpraamount,count(distinct(ticket_no)) ticketNo,SUM(px_count) pxcount "+
//                             " from bmtcGprs.etim_gprs_ticket Where waybill_no='"+rs.getString("waybill_no").toString()+"' and ticket_no>0";
 			  
 			  String sql11=" select schedule_no,waybill_no, gpraamount,ticketNo,ifnull(pxcount,0)pxcount from (SELECT SUM(px_total_amount) gpraamount,ifnull(schedule_no,'') as schedule_no,waybill_no, "+
 					      " count(distinct(ticket_no)) ticketNo,SUM(px_count) pxcount FROM bmtcGprs.etim_gprs_ticket WHERE waybill_no='"+rs.getString("waybill_no").toString()+"'  "+
 					      " AND fare_type Not IN ('NINC') and ticket_no!=0 union	SELECT SUM(px_total_amount) gpraamount,schedule_no,waybill_no, "+
 					  	  " count(distinct(ticket_no)) ticketNo,SUM(px_count) pxcount FROM bmtcGprs.etim_gprs_ticket190219 WHERE waybill_no='"+rs.getString("waybill_no").toString()+"'  "+
 					  	  " AND fare_type Not IN ('NINC') and ticket_no!=0  " +
// 					  	  " union	SELECT SUM(px_total_amount) gpraamount,schedule_no,waybill_no, "+
// 					  	  " count(distinct(ticket_no)) ticketNo,SUM(px_count) pxcount FROM bmtcGprs.etim_gprs_ticket_old WHERE waybill_no='"+rs.getString("waybill_no").toString()+"'  "+
// 					  	  " AND fare_type Not IN ('NINC') and ticket_no!=0 " +
// 					  	  " union	SELECT SUM(px_total_amount) gpraamount,schedule_no,waybill_no, "+
// 					  	  " count(distinct(ticket_no)) ticketNo,SUM(px_count) pxcount FROM bmtcGprs.etim_gprs_ticket060717 WHERE waybill_no='"+rs.getString("waybill_no").toString()+"'  "+
// 					  	  " AND fare_type Not IN ('NINC') and ticket_no!=0  " +
 					  	  ") a where a.waybill_no is not null group by a.waybill_no" ;
 			  
 			  
 			
				//Query query1 = session1.createSQLQuery(sql11);
				  scheduleno = cm.getDBResultStr(session1, sql11, "schedule_no");
				  if(scheduleno==null || scheduleno.equals("")){
					  scheduleno="";
				  }
				  waybillno = cm.getDBResultStr(session1, sql11, "waybill_no");
				  if(waybillno==null || waybillno.equals("")){
					  waybillno="0";
				  }
				 gprsAmount= cm.getDBResultStr(session1, sql11, "gpraamount");
				 if(gprsAmount==null || gprsAmount.equals("")){
					 gprsAmount="0.0";
				 }
				
				 ticketno= cm.getDBResultStr(session1, sql11, "ticketNo");
				 if(ticketno==null || ticketno.equals("")){
					 ticketno="0";
				  }
				 
				 
				 passenger_count= cm.getDBResultStr(session1, sql11, "pxcount");
//				if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
//					 diff=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString())-Double.parseDouble(gprsAmount);
//					 totaletm+=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString());
//				}else{
//					 diff=Double.parseDouble(rs.getString("amnt").toString())-Double.parseDouble(gprsAmount);
//					 totaletm+=Integer.parseInt(rs.getString("amnt").toString());
//				}
//				totaldiff+=diff;
//				totalgprs+=Double.parseDouble(gprsAmount);
//				 if(rs.getString("ticket_no")==null || rs.getString("ticket_no").equals("")){
//					 ticketno="0";
//				 }else if(ticketno==null || ticketno.equals("0")){
//				 
//				 }else{
//					  percentage=(Double.parseDouble(ticketno)/Double.parseDouble(rs.getString("ticket_no").toString()))*100.0;
//				 }
//				 if(passenger_count==null || passenger_count.equals("")){
//					 passenger_count="0";
//				 }
//				 
//				 DecimalFormat dfu = new DecimalFormat("####0.00");
////				 System.out.println("Value: " + dfu.format(percentage));
//				 String percentage1=dfu.format(percentage);
//					System.out.println("Reached here=========");

				 if(value=="0" || value.equalsIgnoreCase("0")){
					 System.out.println("Enter into 0");
					 System.out.println(rs.getString("ETIM_Coll_Amt").toString());
					 System.out.println(Double.parseDouble(gprsAmount));
					 System.out.println(Double.parseDouble(rs.getString("amnt").toString()));


				 if(Double.parseDouble(rs.getString("amnt").toString())==Double.parseDouble(gprsAmount)){
					 System.out.println("Enter into value 0");
					 
					 if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
						 diff=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString())-Double.parseDouble(gprsAmount);
						 totaletm+=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString());
					}else{
						 diff=Double.parseDouble(rs.getString("amnt").toString())-Double.parseDouble(gprsAmount);
						 totaletm+=Double.parseDouble(rs.getString("amnt").toString());
					}
					totaldiff+=diff;
					totalgprs+=Double.parseDouble(gprsAmount);
					 if(rs.getString("ticket_no")==null || rs.getString("ticket_no").equals("")){
						 ticketno="0";
					 }else if(ticketno==null || ticketno.equals("0")){
					 
					 }else{
						  percentage=(Double.parseDouble(ticketno)/Double.parseDouble(rs.getString("ticket_no").toString()))*100.0;
					 }
					 if(passenger_count==null || passenger_count.equals("")){
						 passenger_count="0";
					 }
					 
					 DecimalFormat dfu = new DecimalFormat("####0.00");
//					 System.out.println("Value: " + dfu.format(percentage));
					 String percentage1=dfu.format(percentage);
					 
					 
					 regionTypeAjaxString +="<tr>";
					 regionTypeAjaxString +="<td align='right'>"+i++ +"</td>";
			regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_No").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+rs.getString("shift_type_name").toString() +"</td>";

			regionTypeAjaxString +="<td align='right'>"+ rs.getString("waybill_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ rs.getString("device_serial_number").toString() +"</td>";
			System.out.println("------------------------"+rs.getString("amnt").toString());
			if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
			regionTypeAjaxString +="<td align='right'>"+ Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString()) +"</td>";
			}else{
				regionTypeAjaxString +="<td align='right'>"+ rs.getString("amnt").toString() +"</td>";	
			}
			regionTypeAjaxString +="<td align='right'>"+ gprsAmount +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ diff +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ rs.getString("ticket_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ ticketno +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ rs.getString("px_count").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ passenger_count +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ rs.getString("Ticket_Audited_Date").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ percentage1 +"</td>";
			
			 regionTypeAjaxString +="</tr>";
			ttickets+=Integer.parseInt(rs.getString("ticket_no").toString());
	        tgtickets+=Integer.parseInt(ticketno);
	        pasentotal+=Integer.parseInt(rs.getString("px_count").toString());
	        
	        gprspasentotal+=Integer.parseInt(passenger_count);
				 }

				 }else if(value=="1" || value.equalsIgnoreCase("1")){
					 System.out.println("Enter into 1");
					 System.out.println(Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString()));
					 System.out.println(Double.parseDouble(gprsAmount));
					 System.out.println(Double.parseDouble(rs.getString("amnt").toString()));


					 if(Double.parseDouble(rs.getString("amnt").toString()) > Double.parseDouble(gprsAmount)){
						 System.out.println("Enter into value 1");
						 
							 
							 if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
								 diff=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString())-Double.parseDouble(gprsAmount);
								 totaletm+=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString());
							}else{
								 diff=Double.parseDouble(rs.getString("amnt").toString())-Double.parseDouble(gprsAmount);
								 totaletm+=Double.parseDouble(rs.getString("amnt").toString());
							}
							totaldiff+=diff;
							totalgprs+=Double.parseDouble(gprsAmount);
							 if(rs.getString("ticket_no")==null || rs.getString("ticket_no").equals("")){
								 ticketno="0";
							 }else if(ticketno==null || ticketno.equals("0")){
							 
							 }else{
								  percentage=(Double.parseDouble(ticketno)/Double.parseDouble(rs.getString("ticket_no").toString()))*100.0;
							 }
							 if(passenger_count==null || passenger_count.equals("")){
								 passenger_count="0";
							 }
							 
							 DecimalFormat dfu = new DecimalFormat("####0.00");
//							 System.out.println("Value: " + dfu.format(percentage));
							 String percentage1=dfu.format(percentage);
						 
							 regionTypeAjaxString +="<tr>";
						     regionTypeAjaxString +="<td align='right'>"+i++ +"</td>";
							regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_No").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+rs.getString("shift_type_name").toString() +"</td>";
							System.out.println("------------------------"+rs.getString("amnt").toString());
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("waybill_no").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("device_serial_number").toString() +"</td>";

							if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
							regionTypeAjaxString +="<td align='right'>"+ Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString()) +"</td>";
							}else{
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("amnt").toString() +"</td>";	
//								regionTypeAjaxString +="<td align='right'>"+ (int) Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString()) +"</td>";	

							}
							regionTypeAjaxString +="<td align='right'>"+ gprsAmount +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ diff +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("ticket_no").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ ticketno +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("px_count").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ passenger_count +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("Ticket_Audited_Date").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ percentage1 +"</td>";
							

							ttickets+=Integer.parseInt(rs.getString("ticket_no").toString());
					        tgtickets+=Integer.parseInt(ticketno);
					        pasentotal+=Integer.parseInt(rs.getString("px_count").toString());
					        
					        gprspasentotal+=Integer.parseInt(passenger_count);
					        regionTypeAjaxString +="</tr>";
						 }
					 
					 
				 }else if(value=="2" || value.equalsIgnoreCase("2")){
					 
					 if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
						  amount=rs.getString("ETIM_Coll_Amt").toString();
					 }else{
						 amount=rs.getString("amnt").toString();
					 }
					 System.out.println("Enter into 2");
					 System.out.println(Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString()));
					 System.out.println(Double.parseDouble(gprsAmount));
					 System.out.println(Double.parseDouble(rs.getString("amnt").toString()));

					 if(Double.parseDouble(amount) < Double.parseDouble(gprsAmount)){
						 
						 System.out.println("Enter into value 2");
						 
//						 if(Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString())==Double.parseDouble(gprsAmount)){
//							 System.out.println("Enter into value 0");
							 
							 if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
								 diff=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString())-Double.parseDouble(gprsAmount);
								 totaletm+=Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString());
							}else{
								 diff=Double.parseDouble(rs.getString("amnt").toString())-Double.parseDouble(gprsAmount);
								 totaletm+=Double.parseDouble(rs.getString("amnt").toString());
							}
							totaldiff+=diff;
							totalgprs+=Double.parseDouble(gprsAmount);
							 if(rs.getString("ticket_no")==null || rs.getString("ticket_no").equals("")){
								 ticketno="0";
							 }else if(ticketno==null || ticketno.equals("0")){
							 
							 }else{
								  percentage=(Double.parseDouble(ticketno)/Double.parseDouble(rs.getString("ticket_no").toString()))*100.0;
							 }
							 if(passenger_count==null || passenger_count.equals("")){
								 passenger_count="0";
							 }
							 
							 DecimalFormat dfu = new DecimalFormat("####0.00");
//							 System.out.println("Value: " + dfu.format(percentage));
							 String percentage1=dfu.format(percentage);
						 
							 regionTypeAjaxString +="<tr>";
						 regionTypeAjaxString +="<td align='right'>"+i++ +"</td>";
							regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_No").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+rs.getString("shift_type_name").toString() +"</td>";

							regionTypeAjaxString +="<td align='right'>"+ rs.getString("waybill_no").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("device_serial_number").toString() +"</td>";
							System.out.println("------------------------"+rs.getString("amnt").toString());
							if(rs.getString("amnt").toString().equalsIgnoreCase("0")){
							regionTypeAjaxString +="<td align='right'>"+ Double.parseDouble(rs.getString("ETIM_Coll_Amt").toString()) +"</td>";
							}else{
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("amnt").toString() +"</td>";	
							}
							regionTypeAjaxString +="<td align='right'>"+ gprsAmount +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ diff +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("ticket_no").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ ticketno +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("px_count").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ passenger_count +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ rs.getString("Ticket_Audited_Date").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ percentage1 +"</td>";
							 regionTypeAjaxString +="</tr>";

							ttickets+=Integer.parseInt(rs.getString("ticket_no").toString());
					        tgtickets+=Integer.parseInt(ticketno);
					        pasentotal+=Integer.parseInt(rs.getString("px_count").toString());
					        
					        gprspasentotal+=Integer.parseInt(passenger_count);
					 
					 
				 }


				 }
//				 if(rs.getString("TOKEN")==null || rs.getString("TOKEN").equals("")){
//						regionTypeAjaxString +="<td align='right'></td>";
//
//				 }else{
//				regionTypeAjaxString +="<td align='right'>"+rs.getString("TOKEN").toString()+"</td>";
//				 }
				
//				  regionTypeAjaxString +="</tr>";
//					System.out.println("Reached here******************");

			
 					 }
 					regionTypeAjaxString +="<tr><td colspan='5'>Total</td><td>"+totaletm+"</td><td>"+totalgprs+"</td><td>"+totaldiff+"</td><td>"+ttickets+"</td>" +
 							"<td>"+tgtickets+"</td><td>"+pasentotal+"</td><td>"+gprspasentotal+"</td><td colspan='2'>"+""+"</td></tr>";
 					 PrintWriter out;
                     

			out = response.getWriter();
			out.print(regionTypeAjaxString);
			
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}

			return null;
		}

		public String getDepotNames(String depot_id){
			
			String depotname="";
			Session session =null;
			try{
			String sql = " select org_name from org_chart where deleted_status=0 and org_chart_id="+depot_id+" and org_type_id=3  order by org_name";

			// sql += " order by " + COL_NAME + " " + DIR;
			Query query = HibernateUtil.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					depotname=rs.get("org_name").toString();
				}
			}
			HibernateUtil.closeSession();
			
		
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return depotname;
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
