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

public class TripWiseAbstractReport {
	
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

	public String getTripWiseAbstractReport(){
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
		   // String scheduleid = req.getParameter("scheduleid");
		   // String days = req.getParameter("days");
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

	    String depot=req.getParameter("depotlist1");
      // System.out.println("scheduleid=="+scheduleid+"days=="+days);
//       String qry="";
//       if( days.equalsIgnoreCase("0")){
//   		qry="";
//       }else{
//   		qry=" and DAYNAME(ticket_date)='"+days+"' ";
//   	}
	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
		//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
		String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//		String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
		 String sql="select (select org_name from depot where org_chart_id=depot_id) depot_name,schedule_no,cnt,tot_schedule,(cnt/tot_schedule)*100 per,shift_no " +
		 		"from (select depot_id,schedule_no,cnt ,shift_no ,(total*(select DATEDIFF('"+date2+"','"+date1+"'))) tot_schedule " +
		 		"from (SELECT schedule_no,count(*) cnt,shift_no,if((select no_of_trips from form_four ff " +
		 		"where form_four_id=t.form_four_id and  ff.current_status='ACTIVE' and ff.current_status='ACTIVE' ) IS NULL,false," +
		 		"(select no_of_trips from form_four ff " +
		 		"where form_four_id=t.form_four_id and  ff.current_status='ACTIVE' and ff.current_status='ACTIVE' )) total,t.depot_id " +
		 		"FROM `ticket` t WHERE `ticket_sub_type_short_code` = 'TC' AND `ticket_date` between '"+date1+"' AND  '"+date2+"' " +
		 		"group by schedule_no order by cnt desc )A)B having per is not null";
		 //sql="";
//		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		
	
		//String filePath = "Ticketing/";

		//String fileName = "STTWO.txt";
		
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Schedule wise % of Zero Revenue Trips  Report	</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th><th>Depot No</th><th>Sch no</th><th>Total Trips</th><th>No.of '0' Revenue Trips</th><th>Percentage</th></tr></thead><tbody>";
			
			 int i=1;
				 while (rs.next()) {
					 
					 
				
					regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("depot_name").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_no").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("tot_schedule").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("cnt").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("per").toString()+"</td>";
				
					
				
				
					}
					//regionTypeAjaxString +="<td align='right'>"+ rs.getString("driver").toString() +"</td>" ;
					
					   regionTypeAjaxString +="</tr>";
				
				
		
		 
		 regionTypeAjaxString += "</tbody></table></div> </div> ";

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
		
			
			//FileOutputStream FOS = new FileOutputStream(path);
			//PrintWriter PW = new PrintWriter(FOS);
			
            	
		//System.out.println("realpath="+path);
		
		//PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	
	


}
