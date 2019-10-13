package com.trimax.its.report.action;

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

public class TripWiseCancelledDetailedReport {
	
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

	public String getTripWiseCancelledDetailedReport(){
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
		    String schedulelist1 = req.getParameter("schedulelist1");
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);

	    String depot=req.getParameter("depotlist1");
      // System.out.println("scheduleid=="+scheduleid+"days=="+days);
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
       String qry="";
       if( schedulelist1.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")){
   		qry="";
       }else if(!schedulelist1.equalsIgnoreCase("0") && shiftid.equalsIgnoreCase("0")){
   		qry=" and schedule_no='"+schedulelist1+"' ";
   	}else if(schedulelist1.equalsIgnoreCase("0") && !shiftid.equalsIgnoreCase("0")){
   		qry=" and shift_no='"+dayoutshift1+"' ";
   	}else{
   		qry=" and schedule_no='"+schedulelist1+"' and shift_no='"+dayoutshift1+"' ";
   	}
	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
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
		 String sql="select schedule_no,T1,round((T1/total)*100,2) T1Per,T2,round((T2/total)*100,2) T2Per," +
		 		"T3,round((T3/total)*100,2) T3Per,T4,round((T4/total)*100,2) T4Per,T5,round((T5/total)*100,2) T5Per,T6,round((T6/total)*100,2) T6Per,T7,round((T7/total)*100,2) T7Per," +
		 		"T8,round((T8/total)*100,2) T8Per,T9,round((T9/total)*100,2) T9Per,T10,round((T10/total)*100,2) T10Per,T11,round((T11/total)*100,2) T11Per,T12,round((T12/total)*100,2) T12Per," +
		 		"T13,round((T13/total)*100,2) T13Per,T14,round((T14/total)*100,2) T14Per, T15,round((T15/total)*100,2) T15Per,T16,round((T16/total)*100,2) T16Per," +
		 		"T17,round((T17/total)*100,2) T17Per,T18,round((T18/total)*100,2) T18Per,T19,round((T19/total)*100,2) T19Per,T20,round((T20/total)*100,2) T20Per,total from " +
		 		"(select *,sum(T1+T2+T3+T4+T5+T6+T7+T8+T9+T10+T11+T12+T13+T14+T15+T16+T17+T18+T19+T20) total from(select *,SUM(CASE WHEN trip_no=1 THEN cnnt ELSE 0 END) T1" +
		 		",SUM(CASE WHEN trip_no=2 THEN cnnt ELSE 0 END) T2" +
		 		",SUM(CASE WHEN trip_no=3 THEN cnnt ELSE 0 END) T3" +
		 		",SUM(CASE WHEN trip_no=4 THEN cnnt ELSE 0 END) T4" +
		 		",SUM(CASE WHEN trip_no=5 THEN cnnt ELSE 0 END) T5" +
		 		",SUM(CASE WHEN trip_no=6 THEN cnnt ELSE 0 END) T6" +
		 		",SUM(CASE WHEN trip_no=7 THEN cnnt ELSE 0 END) T7" +
		 		",SUM(CASE WHEN trip_no=8 THEN cnnt ELSE 0 END) T8" +
		 		",SUM(CASE WHEN trip_no=9 THEN cnnt ELSE 0 END) T9" +
		 		",SUM(CASE WHEN trip_no=10 THEN cnnt ELSE 0 END) T10" +
		 		",SUM(CASE WHEN trip_no=11 THEN cnnt ELSE 0 END) T11" +
		 		",SUM(CASE WHEN trip_no=12 THEN cnnt ELSE 0 END) T12" +
		 		",SUM(CASE WHEN trip_no=13 THEN cnnt ELSE 0 END) T13 " +
		 		",SUM(CASE WHEN trip_no=14 THEN cnnt ELSE 0 END) T14 " +
		 		",SUM(CASE WHEN trip_no=15 THEN cnnt ELSE 0 END) T15 " +
		 		",SUM(CASE WHEN trip_no=16 THEN cnnt ELSE 0 END) T16 " +
		 		",SUM(CASE WHEN trip_no=17 THEN cnnt ELSE 0 END) T17 " +
		 		",SUM(CASE WHEN trip_no=18 THEN cnnt ELSE 0 END) T18 " +
		 		",SUM(CASE WHEN trip_no=19 THEN cnnt ELSE 0 END) T19 " +
		 		",SUM(CASE WHEN trip_no=20 THEN cnnt ELSE 0 END) T20 " +
		 		"from (SELECT trip_no,schedule_no,shift_no,count(*) cnnt " +
		 		"FROM `ticket` WHERE  `ticket_sub_type_short_code` = 'TC' AND `ticket_date` between '"+date1+"' AND  '"+date2+"' "+qry+"" +
		 		"group by schedule_no,shift_no,trip_no ORDER BY `ticket_date`, `trip_no`) A " +
		 		"group by schedule_no) B group by schedule_no)C";
		 //sql="";
//		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);

		
	
		//String filePath = "Ticketing/";

		//String fileName = "STTWO.txt";
		
		     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Tripwise % of Zero Revenue Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	      
        
	        
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>Sr.No</th><th>Schedule No</th>" +
					"               <th>T1</th><th>T2</th><th>T3</th>" +
					"               <th>T4</th><th>T5</th><th>T6</th>" +
					"<th>T7</th><th>T8</th><th>T9</th><th>T10</th>" +
					"<th>T11</th><th>T12</th><th>T13</th>" +
					"<th>T14</th><th>T15</th><th>T16</th><th>T17</th><th>T18</th><th>T19</th><th>T20</th><th>Total</th></tr></thead><tbody>";
			//regionTypeAjaxString +="<td colspan='9'></td><td align='center' width='10%' ><b>Cr Remitted</b></td> <td align='center' width='10%'><b>Dedicated/Chartered</b></td><td align='center' width='10%'><b>Total Revenue</b></td></tr>";

			
		   
//	        
			 int i=1;
				 while (rs.next()) {
					 
					 
					
										 regionTypeAjaxString +="<tr>";
					regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
				
					regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_no").toString()+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T1").toString()+" ("+rs.getString("T1Per").toString()+")"+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T2").toString()+" ("+rs.getString("T2Per").toString()+")"+"</td>";
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T3").toString()+" ("+rs.getString("T3Per").toString()+")"+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T4").toString()+" ("+rs.getString("T4Per").toString()+")"+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T5").toString()+" ("+rs.getString("T5Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T6").toString()+" ("+rs.getString("T6Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T7").toString()+" ("+rs.getString("T7Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T8").toString()+" ("+rs.getString("T8Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T9").toString()+" ("+rs.getString("T9Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T10").toString()+" ("+rs.getString("T10Per").toString()+")"+"</td>";
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T11").toString()+" ("+rs.getString("T11Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+rs.getString("T12").toString()+" ("+rs.getString("T12Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T13").toString()+" ("+rs.getString("T13Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T14").toString()+" ("+rs.getString("T14Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T15").toString()+" ("+rs.getString("T15Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T16").toString()+" ("+rs.getString("T16Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T17").toString()+" ("+rs.getString("T17Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T18").toString()+" ("+rs.getString("T18Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T19").toString()+" ("+rs.getString("T19Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("T20").toString() +" ("+rs.getString("T20Per").toString()+")"+"</td>" ;
					regionTypeAjaxString +="<td align='right'>"+ rs.getString("total").toString() +"</td>" ;
					
					
				
					}
					//regionTypeAjaxString +="<td align='right'>"+ rs.getString("driver").toString() +"</td>" ;
					
					   regionTypeAjaxString +="</tr>";
				
				
		
		 
		 regionTypeAjaxString += "</tbody></table></div> </div> ";

		// ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
		// System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));
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
