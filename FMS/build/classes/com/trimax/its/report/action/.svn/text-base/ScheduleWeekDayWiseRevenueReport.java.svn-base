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

public class ScheduleWeekDayWiseRevenueReport {
	
	
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

	public String getScheduleweekdaywiserevenue(){
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
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

	    String depot=req.getParameter("depotlist1");
	  
	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
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
		 String sql="";

//		 sql="select schedule_no,shift_no,round(sum(Sun),2) Sun,round(sum(Monday),2) Mon,round(sum(Tue),2) Tue,round(sum(Wed),2) Wed,round(sum(Thu),2) Thu," +
//		 		"round(sum(Fri),2) Fri,round(sum(Sat),2) Sat,round((sum(Sun)+sum(Monday)+sum(Tue)+sum(Wed)+sum(Thu)+sum(Fri)+sum(Sat))/7,2) avg from " +
//		 		"(select schedule_no,shift_no,CASE WHEN weekday1=0  THEN pxcount/cnt ELSE 0 END Monday,CASE WHEN weekday1=1   THEN pxcount/cnt ELSE 0 END Tue," +
//		 		"CASE WHEN weekday1=2   THEN pxcount/cnt ELSE 0 END Wed,CASE WHEN weekday1=3   THEN pxcount/cnt ELSE 0 END Thu," +
//		 		"CASE WHEN weekday1=4   THEN pxcount/cnt ELSE 0 END Fri ,CASE WHEN weekday1=5   THEN pxcount/cnt ELSE 0 END Sat," +
//		 		"CASE WHEN weekday1=6   THEN pxcount/cnt ELSE 0 END Sun from (SELECT SUM(`px_total_amount`) pxcount,schedule_no,shift_no,count(distinct(ticket_date)) cnt," +
//		 		"DAYNAME(ticket_date) weekday,ticket_date,WEEKDAY(ticket_date) weekday1 FROM `ticket` " +
//		 		"WHERE `ticket_date` between '"+date1+"' and '"+date2+"' AND ticket_no!=0 AND ticket_printed_flag='Y' AND fare_type Not IN ('NINC') " +
//		 		"group by schedule_no,shift_no,weekday order by schedule_no,WEEKDAY(ticket_date)) a) b group by schedule_no,shift_no order by schedule_no  ";
		
		 if(!division1.equalsIgnoreCase("5")){
		 sql="select schedule_no,shift_no,shift_type_name,round(sum(Sun),2) Sun,round(sum(Monday),2) Mon,round(sum(Tue),2) Tue,round(sum(Wed),2) Wed,round(sum(Thu),2) Thu," +
		 		"round(sum(Fri),2) Fri,round(sum(Sat),2) Sat,round((sum(Sun)+sum(Monday)+sum(Tue)+sum(Wed)+sum(Thu)+sum(Fri)+sum(Sat))/7,2) avg " +
		 		"from (select schedule_no,shift_no,shift_type_name,CASE WHEN weekday1=0  THEN pxcount/cnt ELSE 0 END Monday,CASE WHEN weekday1=1   THEN pxcount/cnt ELSE 0 END Tue," +
		 		"CASE WHEN weekday1=2   THEN pxcount/cnt ELSE 0 END Wed,CASE WHEN weekday1=3   THEN pxcount/cnt ELSE 0 END Thu,CASE WHEN weekday1=4   THEN pxcount/cnt ELSE 0 END Fri ," +
		 		"CASE WHEN weekday1=5   THEN pxcount/cnt ELSE 0 END Sat,CASE WHEN weekday1=6   THEN pxcount/cnt ELSE 0 END Sun " +
		 		"from (SELECT SUM(`px_total_amount`) pxcount,t.schedule_no,shift_no,count(distinct(ticket_date)) cnt," +
		 		"DAYNAME(ticket_date) weekday,ticket_date,WEEKDAY(ticket_date) weekday1," +
		 		"if(shift_type_name is null,schedule_type_name,if(shift_type_id=2 and shift_no=2,'DAY 2',shift_type_name)) shift_type_name " +
		 		"FROM `ticket` t " +
		 		"inner join Waybill_Details wd on t.waybill_no=wd.waybill_No " +
		 		"inner join schedule_type st on st.schedule_type_id=wd.Schedult_Type " +
		 		"left join shift_type sft on sft.shift_type_id=wd.Shift_Type " +
		 		"WHERE `ticket_date` " +
		 		"between '"+date1+"' and '"+date2+"' AND ticket_no!=0 AND ticket_printed_flag='Y' AND fare_type  " +
		 		"Not IN ('NINC')   group by schedule_no,shift_no,weekday order by schedule_no,WEEKDAY(ticket_date)) a) b " +
		 		"group by schedule_no,shift_no order by schedule_no ";
		 }else{
			 sql="select schedule_no,shift_no,shift_type_name,round(sum(Sun),2) Sun,round(sum(Monday),2) Mon,round(sum(Tue),2) Tue,round(sum(Wed),2) Wed,round(sum(Thu),2) Thu," +
				 		"round(sum(Fri),2) Fri,round(sum(Sat),2) Sat,round((sum(Sun)+sum(Monday)+sum(Tue)+sum(Wed)+sum(Thu)+sum(Fri)+sum(Sat))/7,2) avg " +
				 		"from (select schedule_no,shift_no,shift_type_name,CASE WHEN weekday1=0  THEN pxcount/cnt ELSE 0 END Monday,CASE WHEN weekday1=1   THEN pxcount/cnt ELSE 0 END Tue," +
				 		"CASE WHEN weekday1=2   THEN pxcount/cnt ELSE 0 END Wed,CASE WHEN weekday1=3   THEN pxcount/cnt ELSE 0 END Thu,CASE WHEN weekday1=4   THEN pxcount/cnt ELSE 0 END Fri ," +
				 		"CASE WHEN weekday1=5   THEN pxcount/cnt ELSE 0 END Sat,CASE WHEN weekday1=6   THEN pxcount/cnt ELSE 0 END Sun " +
				 		"from (SELECT SUM(`px_total_amount`) pxcount,t.schedule_no,shift_no,count(distinct(ticket_date)) cnt," +
				 		"DAYNAME(ticket_date) weekday,ticket_date,WEEKDAY(ticket_date) weekday1," +
				 		"if(shift_type_name is null,schedule_type_name,if(shift_type_id=2 and shift_no=3,'DAY 2',shift_type_name)) shift_type_name " +
				 		"FROM `ticket` t " +
				 		"inner join Waybill_Details wd on t.waybill_no=wd.waybill_No " +
				 		"inner join schedule_type st on st.schedule_type_id=wd.Schedult_Type " +
				 		"left join shift_type sft on sft.shift_type_id=wd.Shift_Type " +
				 		"WHERE `ticket_date` " +
				 		"between '"+date1+"' and '"+date2+"' AND ticket_no!=0 AND ticket_printed_flag='Y' AND fare_type  " +
				 		"Not IN ('NINC')   group by schedule_no,shift_no,weekday order by schedule_no,WEEKDAY(ticket_date)) a) b " +
				 		"group by schedule_no,shift_no order by schedule_no ";
		 }
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		
	
		String filePath = "Ticketing/";

		String fileName = "STTWO.txt";
		
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br> Schedules Weekday wise Revenue Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th><th>Sch no</th><th>Shift type</th>" +
					"               <th>Monday</th><th>Tuesday</th><th>Wednesday</th>" +
					"               <th>Thursday</th><th>Friday</th><th>Saturday</th><th>Sunday</th><th>Avg</th></tr></thead><tbody>";
			//regionTypeAjaxString +="<td colspan='9'></td><td align='center' width='10%' ><b>Cr Remitted</b></td> <td align='center' width='10%'><b>Dedicated/Chartered</b></td><td align='center' width='10%'><b>Total Revenue</b></td></tr>";

			 String path = realpath + filePath + fileName;
		   
//	        
			 int i=1;
				 while (rs.next()) {
					 regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_no").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("shift_type_name").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("Mon").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("Tue").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("Wed").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("Thu").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("Fri").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("Sat").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("Sun").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("avg").toString() +"</td>";
       			   regionTypeAjaxString +="</tr>";
				
				}
		
		 
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
	
	
	
	
	
	

}
