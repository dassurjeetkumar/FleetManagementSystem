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

public class TripWiseWeekDayRiderShipReport {

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

	public String getTripWiseWeekDayRiderShip(){
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
		    String shiftid = req.getParameter("shiftid");
		    String scheduletypeid = req.getParameter("scheduletypeid");
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

	    String depot=req.getParameter("depotlist1");
       
    //   String shiftid = req.getParameter("shiftid");
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
	    	System.out.println("hiii");
	    	dayoutshift1=Integer.parseInt(shiftid);
	    }
       String qry="";

       
//       if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
//   		qry=" schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and shift_no ='"+dayoutshift1+"' and ";
//   		
//   	}else if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")){
//   		qry=" schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and ";
//   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
//   		qry=" DAYNAME(ticket_date)='"+days+"' and shift_no ='"+dayoutshift1+"'";
//   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")){
//   		qry=" DAYNAME(ticket_date)='"+days+"' and ";
//   	}
//   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
//   		qry=" schedule_no ='"+scheduleid+"' and shift_no ='"+dayoutshift1+"' and ";
//   	}
//   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
//   		qry=" schedule_no ='"+scheduleid+"' and ";
//   	
//   	}
//   	else if(!shiftid.equalsIgnoreCase("0") ){
//		qry=" shift_no ='"+dayoutshift1+"' and ";
//	
//	}
//   	else {
//   		qry="";
//   	}
       
       
       if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")&& !scheduletypeid.equalsIgnoreCase("0")){
   		qry=" ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and shift_no ='"+dayoutshift1+"' and Schedult_Type ='"+scheduletypeid+"' and ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and Shift_Type ='"+dayoutshift1+"' and ";
   		
   	}else if(!scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")&& scheduletypeid.equalsIgnoreCase("0")){
   		qry=" ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and DAYNAME(ticket_date)='"+days+"' and ";
   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0")&& !scheduletypeid.equalsIgnoreCase("0")){
   		qry=" DAYNAME(ticket_date)='"+days+"' and shift_no ='"+dayoutshift1+"'and Schedult_Type='"+scheduletypeid+"' ";
   		//qry=" DAYNAME(ticket_date)='"+days+"' and Shift_Type ='"+dayoutshift1+"'";
   	}else if(scheduleid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")&& shiftid.equalsIgnoreCase("0") && scheduletypeid.equalsIgnoreCase("0")){
   		qry=" DAYNAME(ticket_date)='"+days+"' and ";
   		//qry=" DAYNAME(ticket_date)='"+days+"' and ";
   	}
   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")&& !shiftid.equalsIgnoreCase("0") && !scheduletypeid.equalsIgnoreCase("0")){
   		qry=" ticket.schedule_no ='"+scheduleid+"' and shift_no ='"+dayoutshift1+"' and Schedult_Type='"+scheduletypeid+"' and";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduletypeid.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
   		qry=" Schedult_Type='"+scheduletypeid+"' and shift_no ='"+dayoutshift1+"'and";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduletypeid.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0") && !days.equalsIgnoreCase("0")){
   		qry=" Schedult_Type='"+scheduletypeid+"' and DAYNAME(ticket_date)='"+days+"' and";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduletypeid.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
   		qry=" Schedult_Type='"+scheduletypeid+"' and";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and Shift_Type ='"+dayoutshift1+"' and ";
   	}
   	else if(!scheduleid.equalsIgnoreCase("0") && days.equalsIgnoreCase("0")){
   		qry=" ticket.schedule_no ='"+scheduleid+"' and ";
   		//qry=" ticket.schedule_no ='"+scheduleid+"' and ";
   	
   	}
   	else if(!shiftid.equalsIgnoreCase("0") && !scheduletypeid.equalsIgnoreCase("0") ){
   		qry=" shift_no ='"+dayoutshift1+"' and Schedult_Type='"+scheduletypeid+"' and ";
   		//qry=" Shift_Type ='"+dayoutshift1+"' and ";
   	
   	}
   	else {
   		qry="";
   	}
	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
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
		
		 String sql="SELECT ticket_date,concat(DAYNAME(ticket_date),FLOOR((DayOfMonth(ticket_date)-1)/7)+1) weekda,ticket.schedule_no," +
			 		"case  DAYNAME(ticket_date)  when '' then -2  when 'monday' then 1  " +
			 		"when 'tuesday' then 2  when 'wednesday' then 3  when 'thursday' then 4 when 'friday' then 5  when 'saturday' then 6  when 'sunday' then -1 " +
			 		"end as day_nr," +
			 		"SUM(CASE WHEN trip_no=1   THEN px_count ELSE 0 END) T1," +
			 		"SUM(CASE WHEN trip_no=2   THEN px_count ELSE 0 END) T2," +
			 		"SUM(CASE WHEN trip_no=3   THEN px_count ELSE 0 END) T3," +
			 		"SUM(CASE WHEN trip_no=4   THEN px_count ELSE 0 END) T4," +
			 		"SUM(CASE WHEN trip_no=5   THEN px_count ELSE 0 END) T5," +
			 		"SUM(CASE WHEN trip_no=6   THEN px_count ELSE 0 END) T6," +
			 		"SUM(CASE WHEN trip_no=7   THEN px_count ELSE 0 END) T7," +
			 		"SUM(CASE WHEN trip_no=8   THEN px_count ELSE 0 END) T8," +
			 		"SUM(CASE WHEN trip_no=9   THEN px_count ELSE 0 END) T9," +
			 		"SUM(CASE WHEN trip_no=10   THEN px_count ELSE 0 END) T10," +
			 		"SUM(CASE WHEN trip_no=11   THEN px_count ELSE 0 END) T11," +
			 		"SUM(CASE WHEN trip_no=12   THEN px_count ELSE 0 END) T12," +
			 		"SUM(CASE WHEN trip_no=13   THEN px_count ELSE 0 END) T13," +
			 		"SUM(CASE WHEN trip_no=14   THEN px_count ELSE 0 END) T14," +
			 		"SUM(CASE WHEN trip_no=15   THEN px_count ELSE 0 END) T15, " +
			 		"SUM(CASE WHEN trip_no=16   THEN px_count ELSE 0 END) T16," +
			 		"SUM(CASE WHEN trip_no=17   THEN px_count ELSE 0 END) T17," +
			 		"SUM(CASE WHEN trip_no=18   THEN px_count ELSE 0 END) T18," +
			 		"SUM(CASE WHEN trip_no=19   THEN px_count ELSE 0 END) T19," +
			 		"SUM(CASE WHEN trip_no=20   THEN px_count ELSE 0 END) T20," +
			 		"SUM(CASE WHEN trip_no=21   THEN px_count ELSE 0 END) T21," +
			 		"SUM(CASE WHEN trip_no=22   THEN px_count ELSE 0 END) T22," +
			 		"SUM(CASE WHEN trip_no=23   THEN px_count ELSE 0 END) T23," +
			 		"SUM(CASE WHEN trip_no=24   THEN px_count ELSE 0 END) T24," +
			 		"SUM(CASE WHEN trip_no=25   THEN px_count ELSE 0 END) T25," +
			 		"SUM(CASE WHEN trip_no=26   THEN px_count ELSE 0 END) T26," +
			 		"SUM(CASE WHEN trip_no=27   THEN px_count ELSE 0 END) T27," +
			 		"SUM(CASE WHEN trip_no=28   THEN px_count ELSE 0 END) T28," +
			 		"SUM(CASE WHEN trip_no=29   THEN px_count ELSE 0 END) T29," +
			 		"SUM(CASE WHEN trip_no=30   THEN px_count ELSE 0 END) T30 " +
			 		"FROM `ticket` inner join Waybill_Details on Waybill_Details.waybil_Id=ticket.waybil_Id "  +
			 		"WHERE " +
			 		qry+
			 		" ticket_date between '"+date1+"' and '"+date2+"' " +
			 		"group by ticket.schedule_no,ticket_date order by ticket.schedule_no,day_nr,weekda";
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		
	
		String filePath = "Ticketing/";

		String fileName = "STTWO.txt";
		
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4></br> Trip Wise WeekDay RiderShip Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th><th>Sch no</th><th>Date</th><th>Day</th>" +
					"               <th>T1</th><th>T2</th><th>T3</th>" +
					"               <th>T4</th><th>T5</th><th>T6</th>" +
					"<th>T7</th><th>T8</th><th>T9</th><th>T10</th>" +
					"<th>T11</th><th>T12</th><th>T13</th>" +
					"<th>T14</th><th>T15</th><th>T16</th><th>T17</th><th>T18</th>" +
					"               <th>T19</th><th>T20</th><th>T21</th>" +
					"<th>T22</th><th>T23</th><th>T24</th><th>T25</th>" +
					"<th>T26</th><th>T27</th><th>T28</th>" +
					"<th>T29</th><th>T30</th><th>Total</th></tr></thead><tbody>";
			//regionTypeAjaxString +="<td colspan='9'></td><td align='center' width='10%' ><b>Cr Remitted</b></td> <td align='center' width='10%'><b>Dedicated/Chartered</b></td><td align='center' width='10%'><b>Total Revenue</b></td></tr>";

			 String path = realpath + filePath + fileName;
		   
//	        
			 int i=1;
				 while (rs.next()) {
					 regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_no").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("ticket_date").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("weekda").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T1").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T2").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T4").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T5").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T7").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T8").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T9").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T10").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T11").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T13").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T14").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T15").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T16").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T17").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T18").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T19").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T20").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T21").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T22").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T23").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T24").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T25").toString() +"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T26").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T27").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T28").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T29").toString() +"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T30").toString() +"</td>" ;
					int total=Integer.parseInt(rs.getString("T1").toString())+(Integer.parseInt(rs.getString("T2").toString()))
							+Integer.parseInt(rs.getString("T3").toString())+Integer.parseInt(rs.getString("T4").toString())
							+Integer.parseInt(rs.getString("T5").toString())+Integer.parseInt(rs.getString("T6").toString())
							+Integer.parseInt(rs.getString("T7").toString())+Integer.parseInt(rs.getString("T8").toString())
							+Integer.parseInt(rs.getString("T9").toString())+Integer.parseInt(rs.getString("T10").toString())
							+Integer.parseInt(rs.getString("T11").toString())+Integer.parseInt(rs.getString("T12").toString())
							+Integer.parseInt(rs.getString("T13").toString())+Integer.parseInt(rs.getString("T14").toString())
							+Integer.parseInt(rs.getString("T15").toString())+Integer.parseInt(rs.getString("T16").toString())
							+Integer.parseInt(rs.getString("T17").toString())+Integer.parseInt(rs.getString("T18").toString())
							+Integer.parseInt(rs.getString("T19").toString())+Integer.parseInt(rs.getString("T20").toString())
							+Integer.parseInt(rs.getString("T21").toString())+Integer.parseInt(rs.getString("T22").toString())
							+Integer.parseInt(rs.getString("T23").toString())+Integer.parseInt(rs.getString("T24").toString())
							+Integer.parseInt(rs.getString("T25").toString())+Integer.parseInt(rs.getString("T26").toString())
							+Integer.parseInt(rs.getString("T27").toString())+Integer.parseInt(rs.getString("T28").toString())
							+Integer.parseInt(rs.getString("T29").toString())+Integer.parseInt(rs.getString("T30").toString());
					regionTypeAjaxString +="<td align='right'>"+ total +"</td>" ;
					
					
				
				
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
		
		String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
				" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";
//		System.out.println("qry1"+qry1);
		rs=stmt.executeQuery(qry1);
	
		 while(rs.next()){
//		System.out.println("--------"+rs.getString("scheduleNumber"));
					list.add(rs.getString("scheduleNumber"));
//				System.out.println("++++++"+list.size());
			
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
				 System.out.println("connection........."+connection);
			 stmt = connection.createStatement();
			
			String qry1 = "SELECT schedule_id,schedule_number as scheduleNumber FROM  schedule where (status='ACTIVE' or status='NEW')"+
				" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0";
			rs=stmt.executeQuery(qry1);
//		System.out.println("rs==="+rs);
			 while(rs.next()){
//			System.out.println("hiiii--------"+rs.getString("scheduleNumber"));
						list.add(rs.getString("scheduleNumber"));
//					System.out.println("++++++"+list.size());
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				return list;
			
	}
	
}
