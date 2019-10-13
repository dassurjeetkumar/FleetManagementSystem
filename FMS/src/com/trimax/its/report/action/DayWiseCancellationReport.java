package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DayWiseCancellationReport {
	
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

	public String getDayWiseCancellationReport(){
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
		   // String days = req.getParameter("days");
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

	    String depot=req.getParameter("depotlist1");
	    String scheduleid = req.getParameter("schedulelist1");
	    String days = req.getParameter("days");
	    
	    String shiftid = req.getParameter("shiftid");
	    int dayoutshift1=0;
	   // int dayoutshift2=0;
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
      // System.out.println("scheduleid=="+scheduleid+"days=="+days);
	    String qry="";
//	    if(scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//			qry="";
//			
//		}else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//			qry="and t.schedule_no ='"+scheduleid+"'  ";
//		}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")){
//			qry="and DAYNAME(ticket_date)='"+days+"'  ";
//		}else{
//			qry="and t.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"'  ";
//		}
	    
	    
	    
	    if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
	   		qry=" and t.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and t.shift_no ='"+dayoutshift1+"'  ";
	   		
	   	}else if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")){
	   		qry=" and t.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"'  ";
	   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")){
	   		qry=" and DAYNAME(ticket_date)='"+days+"' and t.shift_no ='"+dayoutshift1+"'";
	   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& shiftid.equalsIgnoreCase("0")){
	   		qry=" and DAYNAME(ticket_date)='"+days+"'  ";
	   	}
	   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")){
	   		qry=" and t.schedule_no ='"+scheduleid+"' and t.shift_no ='"+dayoutshift1+"'  ";
	   	}
	   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
	   		qry=" and t.schedule_no ='"+scheduleid+"'  ";
	   	
	   	}else {
	   		qry="";
	   	}
	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
		//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
		String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//		String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
//		 String sql="select * from(select sum(cnnt) cnt,schedule_no,shift_no,trip_no,weekday,case  weekday  when '' then -2  " +
//		 		"when 'monday' then 1  when 'tuesday' then 2  when 'wednesday' then 3  when 'thursday' then 4 when 'friday' then 5  " +
//		 		"when 'saturday' then 6  when 'sunday' then -1 end as day_nr, " +
//		 		"SUM(CASE WHEN trip_no=1 THEN cnnt ELSE 0 END) T1, " +
//		 		"SUM(CASE WHEN trip_no=2 THEN cnnt ELSE 0 END) T2, SUM(CASE WHEN trip_no=3 THEN cnnt ELSE 0 END) T3, " +
//		 		"SUM(CASE WHEN trip_no=4 THEN cnnt ELSE 0 END) T4, SUM(CASE WHEN trip_no=5 THEN cnnt ELSE 0 END) T5, " +
//		 		"SUM(CASE WHEN trip_no=6 THEN cnnt ELSE 0 END) T6, SUM(CASE WHEN trip_no=7 THEN cnnt ELSE 0 END) T7" +
//		 		", SUM(CASE WHEN trip_no=8 THEN cnnt ELSE 0 END) T8, SUM(CASE WHEN trip_no=9 THEN cnnt ELSE 0 END) T9" +
//		 		", SUM(CASE WHEN trip_no=10 THEN cnnt ELSE 0 END) T10, SUM(CASE WHEN trip_no=11 THEN cnnt ELSE 0 END) T11" +
//		 		", SUM(CASE WHEN trip_no=12 THEN cnnt ELSE 0 END) T12, SUM(CASE WHEN trip_no=13 THEN cnnt ELSE 0 END) T13  " +
//		 		"from (select * from  (select trip_no,schedule_no,shift_no,count(*) cnnt,DAYNAME(ticket_date) weekday," +
//		 		"WEEKDAY(ticket_date) weekday1 FROM ticket WHERE `ticket_sub_type_short_code` = 'TC' AND ticket_date " +
//		 		"between '"+date1+"' AND  '"+date2+"' "+qry+"  group by  schedule_no,shift_no,trip_no,weekday order by ticket_date," +
//		 		"shift_no,trip_no,WEEKDAY(ticket_date)) nn ) a group by schedule_no,shift_no,trip_no) B order by schedule_no,shift_no,trip_no;";
		 String sql ="select * from(select sum(cnnt) cnt,schedule_no,shift_no,weekday,cnt1,case  weekday  " +
		 		"when '' then -2  when 'monday' then 1  when 'tuesday' then 2  when 'wednesday' then 3  " +
		 		"when 'thursday' then 4 when 'friday' then 5  when 'saturday' then 6  when 'sunday' then -1 " +
		 		"end as day_nr, SUM(CASE WHEN trip_no=1 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T1, SUM(CASE WHEN trip_no=2 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T2, " +
		 		"SUM(CASE WHEN trip_no=3 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T3, SUM(CASE WHEN trip_no=4 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T4, " +
		 		"SUM(CASE WHEN trip_no=5 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T5, SUM(CASE WHEN trip_no=6 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T6, " +
		 		"SUM(CASE WHEN trip_no=7 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T7, SUM(CASE WHEN trip_no=8 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T8, " +
		 		"SUM(CASE WHEN trip_no=9 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T9, SUM(CASE WHEN trip_no=10 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T10, " +
		 		"SUM(CASE WHEN trip_no=11 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T11, SUM(CASE WHEN trip_no=12 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T12, " +
		 		"SUM(CASE WHEN trip_no=13 THEN  round(cnnt/cnt1*100,2) ELSE 0 END) T13  from (select * from  (select trip_no,t.schedule_no,shift_no,count(*) cnnt," +
		 		"DAYNAME(ticket_date) weekday,WEEKDAY(ticket_date) weekday1,cnt1 FROM ticket t " +
		 		"left join (select schedule_no,count(*)cnt1  FROM ticket t WHERE `ticket_sub_type_short_code` = 'TC' AND " +
		 		"ticket_date between '"+date1+"' AND  '"+date2+"' "+qry+"   group by  schedule_no) cc on cc.schedule_no=t.schedule_no " +
		 		"WHERE `ticket_sub_type_short_code` = 'TC' AND ticket_date between '"+date1+"' AND  '"+date2+"' "+qry+"   group by  " +
		 		"schedule_no,shift_no,trip_no,weekday order by ticket_date,shift_no,trip_no,WEEKDAY(ticket_date)) nn ) a group by " +
		 		"schedule_no,shift_no,weekday) B order by schedule_no,shift_no";
		 //sql="";
//		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		
		
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Daywise % of zero Revenue trips Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th>" +
//					"               <th>Shift No</th>" +
					"<th>Schedule No</th><th>Weekday</th>" +
					"               <th>T1</th><th>T2</th><th>T3</th>" +
					"<th>T4</th><th>T5</th><th>T6</th><th>T7</th><th>T8</th>" +
					"<th>T9</th><th>T10</th><th>T11</th><th>T12</th><th>T13</th></tr></thead><tbody>";
			
			 int i=1;
				 while (rs.next()) {
					 
					 
					
										 regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
				
				
//					regionTypeAjaxString +="<td align='right'>"+rs.getString("shift_no").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("schedule_no").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("weekday").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T1").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T2").toString()+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T4").toString()+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T5").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T6").toString()+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T7").toString()+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T8").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T9").toString()+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T10").toString()+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T11").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T12").toString()+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T13").toString()+"</td>" ;
					
					}
					
					   regionTypeAjaxString +="</tr>";
				
		          regionTypeAjaxString += "</tbody></table></div> </div> ";

		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	

}
