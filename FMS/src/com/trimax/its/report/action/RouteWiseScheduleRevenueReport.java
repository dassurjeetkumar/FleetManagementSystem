package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class RouteWiseScheduleRevenueReport {
	

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
		//this.setServicetypemap(getServiceType1());
		return "success";
	}

	
	public String getRouteWiseScheduleRevenueReport()
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
		String routeno = req.getParameter("routeid");
		String scheduleNo=req.getParameter("schedulelist1");
		String route[]=routeno.split(":");
		if(route[1].equalsIgnoreCase("DOWN")){
			route[1]="DN";
		}else if(route[1].equalsIgnoreCase("UP")){
			route[1]="UP";
		}
		String qry1="";
		if(scheduleNo.equalsIgnoreCase("0")){
			qry1="";
		}else{
			qry1=" and t.schedule_no='"+scheduleNo+"' ";
		}
		String concatrouteno=route[0]+route[1];
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
     
			
		//	String sql1="SELECT depotname FROM depot_ip_info WHERE division_id = '"+divisionlist1+"'";

			String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE division_id = '"+divisionlist1+"'";
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
			String depotname="";
			
			 stmt = connection.createStatement();
			 String sql="";
			 int k=aliasToValueMapList.size();
			 //sql="select depot_name,depot_id,cnt1,schedule_no,sn,Sun,Mon,Tue,Wed,Thu,Fri,Sat,avg from (";
			for (int j = 0; j < aliasToValueMapList.size(); j++) {
				Map<String, Object> aliasValue = aliasToValueMapList.get(j);
				depotname=aliasValue.get("depotname").toString();
				if(j==0){
					sql+="select depot_name,depot_id,cnt1,schedule_no,sn,Sun,Mon,Tue,Wed,Thu,Fri,Sat,avg,total from (";
				}else{
					sql+="";
				}
				 
				  sql+="select (select org_name from "+depotname+".depot where org_chart_id=depot_id) depot_name,schedule_no,round(sum(Sun),2) Sun,round(sum(Monday),2) Mon,round(sum(Tue),2) Tue," +
				 		"round(sum(Wed),2) Wed,round(sum(Thu),2) Thu,round(sum(Fri),2) Fri,round(sum(Sat),2) Sat," +
				 		"round((sum(Sun)+sum(Monday)+sum(Tue)+sum(Wed)+sum(Thu)+sum(Fri)+sum(Sat)),2) total,"+
				 		"round((sum(Sun)+sum(Monday)+sum(Tue)+sum(Wed)+sum(Thu)+sum(Fri)+sum(Sat))/7,2) avg,depot_id,cnt1,sn from " +
				 		"(select schedule_no,CASE WHEN weekday1=0  THEN pxcount/cnt ELSE 0 END Monday,CASE WHEN weekday1=1   " +
				 		"THEN pxcount/cnt ELSE 0 END Tue,CASE WHEN weekday1=2   THEN pxcount/cnt ELSE 0 END Wed,CASE WHEN weekday1=3   " +
				 		"THEN pxcount/cnt ELSE 0 END Thu,CASE WHEN weekday1=4   THEN pxcount/cnt ELSE 0 END Fri ,CASE WHEN weekday1=5   " +
				 		"THEN pxcount/cnt ELSE 0 END Sat,CASE WHEN weekday1=6   THEN pxcount/cnt ELSE 0 END Sun,depot_id,cnt1,sn " +
				 		"from (SELECT SUM(`px_total_amount`) pxcount," +
				 		"t.schedule_no,CC.schedule_no sn,count(distinct(ticket_date)) cnt,DAYNAME(ticket_date) weekday,ticket_date," +
				 		"WEEKDAY(ticket_date) weekday1,t.depot_id,cnt1 " +
				 		"FROM "+depotname+".`ticket` t left join (SELECT schedule_no,count(distinct(ticket_date)) cnt1 " +
				 		"FROM "+depotname+".`ticket` WHERE  `ticket_date` between '"+date1+"' and '"+date2+"' group by schedule_no) CC  " +
				 		"on CC.schedule_no=t.schedule_no " +
				 		"WHERE `ticket_date` between '"+date1+"' and '"+date2+"'  and route_no='"+concatrouteno+"'"+qry1+" AND " +
				 		"ticket_no!=0 AND ticket_printed_flag='Y' AND fare_type Not IN ('NINC') " +
				 		"group by schedule_no,weekday order by schedule_no,WEEKDAY(ticket_date)) a) b group by schedule_no ";
				  if(j<(k-1)){
					  sql +=" union ";
					 // k++;
				  }else{
					  sql +="";
				  }
				 if(j==(k-1)){
					 sql +=") B group by schedule_no order by depot_name,schedule_no";
				 }
				  
				
				 
				 
			}
			 System.out.println(sql);
			rs = stmt.executeQuery(sql);
			
			
//		 Class.forName("com.mysql.jdbc.Driver");
//		 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":3306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			
		 
  

	
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Route wise Schedules Revenue Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot No</th><th>Schedule No</th>" +
//				"<th>Duty Type</th>" +
				"<th>No.Of Days Operated</th><th>Total Revenue</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thurs</th><th>Fri</th><th>Sat</th><th>Sun</th><th>Avg Revenue</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		        int i=1;
					 while (rs.next()) {
						
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("depot_name") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("schedule_no") +"</td>");
//			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("ndays") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("cnt1") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("total") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Mon") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Tue") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Wed") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Thu") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Fri") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Sat") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Sun") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("avg") +"</td>");
			
			 regionTypeAjaxString1.append("</tr>");
					
					 }
					 PrintWriter out;
               
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
	
	
	
	
	public String getDepotWiseScheduleListReport() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		String routeId = req.getParameter("val");
		String route[]=routeId.split(":");
		 //String dat = req.getParameter("startdate");
		 //System.out.println("dat==="+dat);
		 Common comm = new Common();
	//	String dat1=comm. getDateFromPicker(dat);
        		List<String> l1 = this.getScheduleId(route[0],route[1]);
        		List<String> l2 = this.getScheduleName(route[0],route[1]);
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
	public List getScheduleId(String route1,String route2) {
     
		List list = new ArrayList();
		try {
//		String qry = "SELECT s.schedule_number schedule_number,s.schedule_id schedule_id FROM `schedule_details` sd " +
//				"inner join form_four ff on sd.form_four_id=ff.form_four_id " +
//				"inner join schedule s on s.schedule_id=ff.schedule_number_id " +
//				"inner join route r on r.route_id=sd.route_number_id " +
//				"where ff.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' and ff.deleted_status=0 " +
//				"and sd.deleted_status=0 and r.route_number='"+route1+"' and r.route_direction='"+route2+"' and r.deleted_status=0 and r.effective_till='0000-00-00 00:00:00' " +
//				"group by s.schedule_number";
			
			String qry = "SELECT s.schedule_number schedule_number,s.schedule_id schedule_id FROM " +
					"form_four ff inner join schedule s on s.schedule_id=ff.schedule_number_id " +
					"inner join route r on r.route_id=ff.route_id " +
					"where ff.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' and ff.deleted_status=0 " +
					"and s.schedule_number like '%"+route1+"%' and r.deleted_status=0 and r.effective_till='0000-00-00 00:00:00' " +
					"group by s.schedule_number";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_number").toString() );
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
			
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScheduleName(String route1,String route2) {
		List list = new ArrayList();
		try {
			String qry = "SELECT s.schedule_number schedule_number,s.schedule_id schedule_id FROM " +
					"form_four ff inner join schedule s on s.schedule_id=ff.schedule_number_id " +
					"inner join route r on r.route_id=ff.route_id " +
					"where ff.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' and ff.deleted_status=0 " +
					"and s.schedule_number like '%"+route1+"%' and r.deleted_status=0 and r.effective_till='0000-00-00 00:00:00' " +
					"group by s.schedule_number";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_number").toString() );
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}
}
