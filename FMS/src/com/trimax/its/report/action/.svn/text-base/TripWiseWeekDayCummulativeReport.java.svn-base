package com.trimax.its.report.action;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class TripWiseWeekDayCummulativeReport {
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		//this.setSchedulelist(getSchedulelistdata());
		return "success";
	}
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	 public String startdate;
	    public String enddate;
	 private List<Schedule> schedulelist;
	    private String schedule;
	    private Map<Integer, String> divisionlist;
	 		private Map<Integer, String> depotlist;
	 		private String depotlist1;
	 		private String divisionlist1;
	    
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


			public String getDepotlist1() {
				return depotlist1;
			}


			public void setDepotlist1(String depotlist1) {
				this.depotlist1 = depotlist1;
			}


			public String getDivisionlist1() {
				return divisionlist1;
			}


			public void setDivisionlist1(String divisionlist1) {
				this.divisionlist1 = divisionlist1;
			}


	public List<Schedule> getSchedulelist() {
			return schedulelist;
		}


		public void setSchedulelist(List<Schedule> schedulelist) {
			this.schedulelist = schedulelist;
		}


	public String getSchedule() {
			return schedule;
		}


		public void setSchedule(String schedule) {
			this.schedule = schedule;
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


	String regionTypeAjaxString = "";

	public String getTripWiseWeekDayCummulative(){
		try {
			Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			Common common = new Common();
			HttpServletRequest req=ServletActionContext.getRequest();
			String date1=common.getDateFromPicker(startdate);
			 String enddate = req.getParameter("enddate");
			String date2=common.getDateFromPicker(enddate);
		    String division1= divisionlist1;
		    String depot1= depotlist1;
		    String scheduleid = req.getParameter("scheduleid");
		    String days = req.getParameter("days");
		    String scheduletypeid = req.getParameter("scheduletypeid");
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

	    String depot=req.getParameter("depotlist1");
       
       String shiftid = req.getParameter("shiftid");
       int dayoutshift1=Integer.parseInt(shiftid);
	   // int dayoutshift2=0;
	    if(!division1.equalsIgnoreCase("5")){
		    if(shiftid.equalsIgnoreCase("4")){
		    	dayoutshift1=1;
		    }
		    if(shiftid.equalsIgnoreCase("5")){
		    	dayoutshift1=2;
		    }
		    if(shiftid.equalsIgnoreCase("2")){
		    	dayoutshift1=1;
		    }
		    if(shiftid.equalsIgnoreCase("3")){
		    	dayoutshift1=2;
		    }
	    }else{
	    	dayoutshift1=Integer.parseInt(shiftid);
	    }
       String qry="";
       
//       if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
//   		qry=" and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and ticket.shift_no ='"+dayoutshift1+"'  ";
//   		
//   	}else if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")){
//   		qry=" and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"'  ";
//   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")){
//   		qry=" and DAYNAME(ticket_date)='"+days+"' and ticket.shift_no ='"+dayoutshift1+"'";
//   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& shiftid.equalsIgnoreCase("0")){
//   		qry=" and DAYNAME(ticket_date)='"+days+"'  ";
//   	}
//   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")){
//   		qry=" and ticket.schedule_no ='"+scheduleid+"' and ticket.shift_no ='"+dayoutshift1+"'  ";
//   	}
//   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//   		qry=" and ticket.schedule_no ='"+scheduleid+"'  ";
//   	
//   	}
//   	else if(!shiftid.equalsIgnoreCase("0") ){
//		qry=" and ticket.shift_no ='"+dayoutshift1+"' ";
//	
//	}
//   	else {
//   		qry="";
//   	}
       
       
       if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")&& !scheduletypeid.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and shift_no ='"+dayoutshift1+"' and Schedult_Type ='"+scheduletypeid+"' ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and Shift_Type ='"+dayoutshift1+"' and ";
   		
   	}else if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")&& scheduletypeid.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"'  ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and ";
   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")&& !scheduletypeid.equalsIgnoreCase("0")){
   		qry=" and DAYNAME(ticket_date)='"+days+"' and shift_no ='"+dayoutshift1+"'and Schedult_Type='"+scheduletypeid+"' ";
   		//qry=" DAYNAME(ticket_date)='"+days+"' and Shift_Type ='"+dayoutshift1+"'";
   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& shiftid.equalsIgnoreCase("0") && scheduletypeid.equalsIgnoreCase("0")){
   		qry=" and DAYNAME(ticket_date)='"+days+"'  ";
   		//qry=" DAYNAME(ticket_date)='"+days+"' and ";
   	}
   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0") && !scheduletypeid.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"' and shift_no ='"+dayoutshift1+"' and Schedult_Type='"+scheduletypeid+"' ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduletypeid.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
   		qry=" and Schedult_Type='"+scheduletypeid+"' and shift_no ='"+dayoutshift1+"' ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduletypeid.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")){
   		qry=" and Schedult_Type='"+scheduletypeid+"' and DAYNAME(ticket_date)='"+days+"' ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduletypeid.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
   		qry=" and Schedult_Type='"+scheduletypeid+"' ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
   		qry=" and ticket.schedule_no ='"+scheduleid+"' ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and ";
   	
   	}
   	else if(!shiftid.equalsIgnoreCase("0") && !scheduletypeid.equalsIgnoreCase("0") ){
   		qry=" and shift_no ='"+dayoutshift1+"' and Schedult_Type='"+scheduletypeid+"' ";
   		//qry=" Shift_Type ='"+dayoutshift1+"' and ";
   	
   	}
   	else {
   		qry="";
   	}
//       if(scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//   		qry="";
//   		
//   	}else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//   		qry="and ticket.schedule_no ='"+scheduleid+"'  ";
//   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")){
//   		qry="and DAYNAME(ticket_date)='"+days+"'  ";
//   	}else{
//   		qry="and ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"'  ";
//   	}
	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	
		//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
		 String sql="SELECT ticket_date,DAYNAME(ticket_date) weekda,ticket.schedule_no,cnt,case  DAYNAME(ticket_date)  " +
		 		"when '' then -2  when 'monday' then 1  when 'tuesday' then 2  when 'wednesday' " +
		 		"then 3  when 'thursday' then 4 when 'friday' then 5  when 'saturday' then 6  " +
		 		"when 'sunday' then -1 end as day_nr," +
		 		"SUM(CASE WHEN trip_no=1   THEN px_total_amount ELSE 0 END) T1," +
		 		"SUM(CASE WHEN trip_no=2   THEN px_total_amount ELSE 0 END) T2,SUM(CASE WHEN trip_no=3   THEN px_total_amount ELSE 0 END) T3," +
		 		"SUM(CASE WHEN trip_no=4   THEN px_total_amount ELSE 0 END) T4,SUM(CASE WHEN trip_no=5   THEN px_total_amount ELSE 0 END) T5," +
		 		"SUM(CASE WHEN trip_no=6   THEN px_total_amount ELSE 0 END) T6,SUM(CASE WHEN trip_no=7   THEN px_total_amount ELSE 0 END) T7," +
		 		"SUM(CASE WHEN trip_no=8   THEN px_total_amount ELSE 0 END) T8,SUM(CASE WHEN trip_no=9   THEN px_total_amount ELSE 0 END) T9," +
		 		"SUM(CASE WHEN trip_no=10   THEN px_total_amount ELSE 0 END) T10,SUM(CASE WHEN trip_no=11   THEN px_total_amount ELSE 0 END) T11," +
		 		"SUM(CASE WHEN trip_no=12   THEN px_total_amount ELSE 0 END) T12,SUM(CASE WHEN trip_no=13   THEN px_total_amount ELSE 0 END) T13," +
		 		"SUM(CASE WHEN trip_no=14   THEN px_total_amount ELSE 0 END) T14,SUM(CASE WHEN trip_no=15   THEN px_total_amount ELSE 0 END) T15, " +
		 		"SUM(CASE WHEN trip_no=16   THEN px_total_amount ELSE 0 END) T16," +
		 		"SUM(CASE WHEN trip_no=17   THEN px_total_amount ELSE 0 END) T17,SUM(CASE WHEN trip_no=18   THEN px_total_amount ELSE 0 END) T18," +
		 		"SUM(CASE WHEN trip_no=19   THEN px_total_amount ELSE 0 END) T19,SUM(CASE WHEN trip_no=20   THEN px_total_amount ELSE 0 END) T20," +
		 		"SUM(CASE WHEN trip_no=21   THEN px_total_amount ELSE 0 END) T21,SUM(CASE WHEN trip_no=22   THEN px_total_amount ELSE 0 END) T22," +
		 		"SUM(CASE WHEN trip_no=23   THEN px_total_amount ELSE 0 END) T23,SUM(CASE WHEN trip_no=24   THEN px_total_amount ELSE 0 END) T24," +
		 		"SUM(CASE WHEN trip_no=25   THEN px_total_amount ELSE 0 END) T25,SUM(CASE WHEN trip_no=26   THEN px_total_amount ELSE 0 END) T26," +
		 		"SUM(CASE WHEN trip_no=27   THEN px_total_amount ELSE 0 END) T27,SUM(CASE WHEN trip_no=28   THEN px_total_amount ELSE 0 END) T28," +
		 		"SUM(CASE WHEN trip_no=29   THEN px_total_amount ELSE 0 END) T29,SUM(CASE WHEN trip_no=30   THEN px_total_amount ELSE 0 END) T30 " +
		 		"FROM `ticket` left join (SELECT DAYNAME(ticket_date) weekda,ticket.schedule_no,count(distinct(trip_no)) cnt FROM `ticket`  " +
		 		"inner join Waybill_Details on Waybill_Details.waybil_Id=ticket.waybil_Id "+
		 		"WHERE  ticket_date between '"+date1+"' and '"+date2+"' "+qry+" and ticket_no!=0 and px_total_amount!=0 group by schedule_no,weekda) cc " +
		 		"on cc.schedule_no=ticket.schedule_no and cc.weekda=DAYNAME(ticket_date) " +
		 		"inner join Waybill_Details on Waybill_Details.waybil_Id=ticket.waybil_Id "+
		 		"WHERE  ticket_date between '"+date1+"' and '"+date2+"' "+qry+"  and ticket_no!=0 group by schedule_no,weekda order by  " +
		 		"schedule_no,day_nr,weekda";
		 //sql="";
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		
	
		String filePath = "Ticketing/";

		String fileName = "STTWO.txt";
		
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Cummulative TripWise Weekday Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th><th>Sch no</th><th>Date</th><th>Day</th>" +
					"               <th>T1(Avg Of %)</th><th>T2(Avg Of %)</th><th>T3(Avg Of %)</th>" +
					"               <th>T4(Avg Of %)</th><th>T5(Avg Of %)</th><th>T6(Avg Of %)</th>" +
					"<th>T7(Avg Of %)</th><th>T8(Avg Of %)</th><th>T9(Avg Of %)</th><th>T10(Avg Of %)</th>" +
					"<th>T11(Avg Of %)</th><th>T12(Avg Of %)</th><th>T13(Avg Of %)</th>" +
					"<th>T14(Avg Of %)</th><th>T15(Avg Of %)</th><th>T16(Avg Of %)</th><th>T17(Avg Of %)</th><th>T18(Avg Of %)</th>" +
						"               <th>T19(Avg Of %)</th><th>T20(Avg Of %)</th><th>T21(Avg Of %)</th>" +
						"<th>T22(Avg Of %)</th><th>T23(Avg Of %)</th><th>T24(Avg Of %)</th><th>T25(Avg Of %)</th>" +
						"<th>T26(Avg Of %)</th><th>T27(Avg Of %)</th><th>T28(Avg Of %)</th>" +
						"<th>T29(Avg Of %)</th><th>T30(Avg Of %)</th><th>Total</th><th>Avg</th></tr></thead><tbody>";
			//regionTypeAjaxString +="<td colspan='9'></td><td align='center' width='10%' ><b>Cr Remitted</b></td> <td align='center' width='10%'><b>Dedicated/Chartered</b></td><td align='center' width='10%'><b>Total Revenue</b></td></tr>";

			 String path = realpath + filePath + fileName;
		   
//	        
			 int i=1;
				 while (rs.next()) {
					 
					 
						Double total=Double.parseDouble(rs.getString("T1").toString())+(Double.parseDouble(rs.getString("T2").toString()))
								+Double.parseDouble(rs.getString("T3").toString())+Double.parseDouble(rs.getString("T4").toString())
								+Double.parseDouble(rs.getString("T5").toString())+Double.parseDouble(rs.getString("T6").toString())
								+Double.parseDouble(rs.getString("T7").toString())+Double.parseDouble(rs.getString("T8").toString())
								+Double.parseDouble(rs.getString("T9").toString())+Double.parseDouble(rs.getString("T10").toString())
								+Double.parseDouble(rs.getString("T11").toString())+Double.parseDouble(rs.getString("T12").toString())
								+Double.parseDouble(rs.getString("T13").toString())+Double.parseDouble(rs.getString("T14").toString())
								+Double.parseDouble(rs.getString("T15").toString())+Double.parseDouble(rs.getString("T16").toString())
								+Double.parseDouble(rs.getString("T17").toString())+Double.parseDouble(rs.getString("T18").toString())
								+Double.parseDouble(rs.getString("T19").toString())+Double.parseDouble(rs.getString("T20").toString())
								+Double.parseDouble(rs.getString("T21").toString())+Double.parseDouble(rs.getString("T22").toString())
								+Double.parseDouble(rs.getString("T23").toString())+Double.parseDouble(rs.getString("T24").toString())
								+Double.parseDouble(rs.getString("T25").toString())+Double.parseDouble(rs.getString("T26").toString())
								+Double.parseDouble(rs.getString("T27").toString())+Double.parseDouble(rs.getString("T28").toString())
								+Double.parseDouble(rs.getString("T29").toString())+Double.parseDouble(rs.getString("T30").toString());
					 double avg=total/Double.parseDouble(rs.getString("cnt").toString());
					 String weekday=rs.getString("weekda").toString();
										 regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_no").toString()+"</td>";
					
					regionTypeAjaxString +="<td align='right'>"+rs.getString("ticket_date").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("weekda").toString()+"</td>";
					if(rs.getString("T1").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T1").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T1").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString()
                            + "','"
	                        + weekday
	                        +"','"
	                        +1
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString()
							+ "' value='"
							+ rs.getString("schedule_no").toString()
							+ "'>"
							+ rs.getString("T1").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T1").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T2").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T2").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T2").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +2
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T2").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T2").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T3").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T3").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T3").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +3
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T3").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T3").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T4").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T4").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T4").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +4
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T4").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T4").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T5").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T5").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T5").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +5
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T5").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T5").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T6").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T6").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T6").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +6
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T6").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T6").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T7").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T7").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T7").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +7
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T7").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T7").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T8").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T8").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T8").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +8
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T8").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T8").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T9").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T9").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T9").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +9
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T9").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T9").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T10").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T10").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T10").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +10
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T10").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T10").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T11").toString().equalsIgnoreCase("11")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T11").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T11").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +11
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T11").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T11").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T12").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T12").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T12").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +12
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T12").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T12").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T13").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T13").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T13").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +13
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T13").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T13").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T14").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T14").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T14").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +14
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T14").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T14").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T15").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T15").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T15").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +15
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T15").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T15").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					
					//hiii
					
					if(rs.getString("T16").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T16").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T16").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +16
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T16").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T16").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T17").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T17").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T17").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +17
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T17").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T17").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T18").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T18").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T18").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +18
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T18").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T18").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T19").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T19").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T19").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +19
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T19").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T19").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T20").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T20").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T20").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +20
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T20").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T20").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T21").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T21").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T21").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +21
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T21").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T21").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T22").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T22").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T22").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +22
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T22").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T22").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T23").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T23").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T23").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +23
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T23").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T23").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T24").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T24").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T24").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +24
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T24").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T24").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T25").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T25").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T25").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +25
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T25").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T25").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T26").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T26").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T26").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +26
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T26").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T26").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T27").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T27").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T27").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +27
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T27").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T27").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T28").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T28").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T28").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +28
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T28").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T28").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T29").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T29").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T29").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +29
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T29").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T29").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
					if(rs.getString("T30").toString().equalsIgnoreCase("0")){
						regionTypeAjaxString +="<td align='right'>"+rs.getString("T30").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T30").toString())/total*100)+")"+"</td>";
					}else{
					regionTypeAjaxString +="<td align='right'>"
							+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getAlertTripWisecummulative('"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
                            + "','"
	                        + weekday
	                        +"','"
	                        +30
	                         +"','"
	                        +date1
	                         +"','"
	                        +date2
	                        +"','"
	                        + depot
							+ "') title='Alert Details' id='alert_details"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "' value='"
							+ rs.getString("schedule_no").toString().replace(" ", "@")
							+ "'>"
							+ rs.getString("T30").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T30").toString())/total*100)+")"
							+ "</a>"+
					"</td>";
					}
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T2").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T2").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T3").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T4").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T4").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T5").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T5").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T6").toString())/total*100)+")"+"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T7").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T7").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T8").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T8").toString())/total*100)+")"+"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T9").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T9").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T10").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T10").toString())/total*100)+")" +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T11").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T11").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T12").toString())/total*100)+")"+"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T13").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T13").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T14").toString()+" ("+Math.rint(Double.parseDouble(rs.getString("T14").toString())/total*100)+")" +"</td>" ;
//					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T15").toString() +" ("+Math.rint(Double.parseDouble(rs.getString("T15").toString())/total*100)+")"+"</td>" ;
//					
					regionTypeAjaxString +="<td align='right'>"+ total +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ total/Integer.parseInt(rs.getString("cnt").toString())+"</td>" ;
					
					
				
				
					}
					//regionTypeAjaxString +="<td align='right'>"+ rs.getString("driver").toString() +"</td>" ;
					
					   regionTypeAjaxString +="</tr>";
				
				
		
		 
		 regionTypeAjaxString += "</tbody></table></div> </div> ";

		// ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
		// System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
		
			
			FileOutputStream FOS = new FileOutputStream(path);
			PrintWriter PW = new PrintWriter(FOS);
			
            	
		
		PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getScheduleNo() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		int parentId = Integer.parseInt(req.getParameter("val"));
		 //String dat = req.getParameter("startdate");
		 //System.out.println("dat==="+dat);
		 Common comm = new Common();
	//	String dat1=comm. getDateFromPicker(dat);
        		List<String> l1 = this.getScheduleId(parentId);
        		List<String> l2 = this.getScheduleName(parentId);
        		String regionTypeAjaxString = "";
        		regionTypeAjaxString += "<option value='0'>All</option>";
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
		
        		return null;
        }
	
	
	@SuppressWarnings("rawtypes")
	public List getScheduleId(int parentID) {
      List list = new ArrayList();
		
      Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		
		Transaction transaction  = null;
		HttpServletRequest req=ServletActionContext.getRequest();
		try {
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
		 Common common = new Common();
		//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
			 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
		
		String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
				" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";
//		System.out.println("qry1"+qry1);
		rs=stmt.executeQuery(qry1);
	
		 while(rs.next()){
					list.add(rs.getString("scheduleNumber"));
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return list;
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScheduleName(int parentID) {
		  List list = new ArrayList();
			
          Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
			//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			 Common common = new Common();
				//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
				 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//					String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
					 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
			String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
				" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";
			rs=stmt.executeQuery(qry1);
			 while(rs.next()){
						list.add(rs.getString("scheduleNumber"));
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
			
	}
	public String getAlertTripwiserevenuecummulative() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			Date FromDate = new Date();
			String schedulename = req.getParameter("schedulename");
			String trip = req.getParameter("cnt");
			String weekday = req.getParameter("weekday");
			String startdate = req.getParameter("startdate");
			String enddate = req.getParameter("enddate");
			String depot = req.getParameter("depot");
			Common cm = new Common();
			
		
			out = resp.getWriter();
			
			result = getAlertTripwiserevenuecummulativeDetails(schedulename,trip,weekday,startdate,enddate,depot);
			out.print(result);
		} catch (Exception ex) {
         ex.printStackTrace();
		} finally {
//			session.close();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getAlertTripwiserevenuecummulativeDetails( String schedulename,String trip,String weekday,
			String selectedstartDate,String selectedendDate,String depot) {
       JSONObject result =new JSONObject();
       Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		
		Transaction transaction  = null;
		
		
		Session session = null;
		try {
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
		//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			 Common common = new Common();
			 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
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
			 String qry = "SELECT DAYNAME(ticket_date) weekda,schedule_no," +
						"trip_no,count(distinct((ticket_date))) ndays FROM `ticket`  WHERE  ticket_date between '"+selectedstartDate+"' and '"+selectedendDate+"'  " +
						"and ticket_no!=0 and px_total_amount!=0 and schedule_no='"+schedulename.replace("@", " ")+"' and DAYNAME(ticket_date)='"+weekday+"' and trip_no="+trip ;
//				System.out.println(qry);
			JSONArray array = new JSONArray();
			
			rs=stmt.executeQuery(qry);
			int i=1;
			 while(rs.next()){
				 JSONArray ja = new JSONArray();
				 ja.add(i+1);
				 ja.add(rs.getString("schedule_no"));
				 ja.add(rs.getString("weekda"));
				 ja.add(rs.getString("ndays"));
				 array.add(ja);
								//list.add(rs.getString("scheduleNumber"));
							//System.out.println("++++++"+list.size());
							result.put("aaData", array);
					}
//			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return result;
		}
	}

}
