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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DenominationWiseHourlyCountReport extends ActionSupport {
	
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> servicetypemap;
	public Map<Integer, String> tickettypemap;
	
	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
	
	public String division;
	public String depot;
	public String servicetype;
	public String tickettype;
	public String daytype;
	public String startdate;
	public String enddate;
	
	public Map<Integer, String> getTickettypemap() {
		return tickettypemap;
	}
	public void setTickettypemap(Map<Integer, String> tickettypemap) {
		this.tickettypemap = tickettypemap;
	}
	public Map<Integer, String> getServicetypemap() {
		return servicetypemap;
	}
	public void setServicetypemap(Map<Integer, String> servicetypemap) {
		this.servicetypemap = servicetypemap;
	}
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
	    this.setServicetypemap(getServicetype());
//	    this.setTickettypemap(getTicketType());
		return "success";
	}

	public Map<Integer, String> getServicetype() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		
		try {
			Query query = session
					.createSQLQuery("select service_type_id,service_type_name from service_type where status='ACTIVE' and deleted_status='0'").addScalar("service_type_id", Hibernate.INTEGER).addScalar("service_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("service_type_id").toString()),rs.get("service_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	
	public Map<Integer, String> getTicketType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		
		try {
			Query query = session
					.createSQLQuery("select form_four_type_id,form_four_type_name from `form_four_type` where status='ACTIVE' and deleted_status='0'").addScalar("form_four_type_id", Hibernate.INTEGER).addScalar("form_four_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			//resultMap.put(0, "Select");
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("form_four_type_id").toString()),rs.get("form_four_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	
	public String getDenominationWiseHourlyCount()
	{
		System.out.println("Enter into getDenominationWiseHourlyCount()");
		try {
		
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
			String weekdays="";
//		    String division1= division;
		//    String depot1= depot;
		    System.out.println("date1===="+date1+"  date2===="+date2+"  depot1==="+depot+"  servicetype===="+servicetype+"  tickettype=="+tickettype+"   daytype===="+daytype);
//		    String depotname=getDepotNames(depot1);
//		    System.out.println("value====="+value);
		    
		    if(daytype=="1" || daytype.equalsIgnoreCase("1") ){
		    	weekdays="WEEKDAY(ticket_date) BETWEEN 0 and 4";
		    }else if(daytype=="2" || daytype.equalsIgnoreCase("2")){
		    	weekdays="WEEKDAY(ticket_date) =5";
		    }else{
		    	weekdays="WEEKDAY(ticket_date) =6";
		    }
		    
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
		 
//		 String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
//			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
//			 Class.forName("com.mysql.jdbc.Driver");
//		 connection = DriverManager.getConnection("jdbc:mysql://10.30.1.158:23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","temp","temp");
//		 
//		 stmt = connection.createStatement();
		 
		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
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
			
			String sql=" select denom,sum(case when ticket_time like '%00:00:%' THEN  (px_count) ELSE 0 END) 1hr, "+
						" sum(case when ticket_time like '%01:%:%' THEN  (px_count) ELSE 0 END) 2hr, "+
					    " sum(case when ticket_time like '%02:%:%' THEN  (px_count) ELSE 0 END) 3hr, "+
					    " sum(case when ticket_time like '%03:%:%' THEN  (px_count) ELSE 0 END) 4hr, "+
						" sum(case when ticket_time like '%04:%:%' THEN  (px_count) ELSE 0 END) 5hr, "+
						" sum(case when ticket_time like '%05:%:%' THEN  (px_count) ELSE 0 END) 6hr, "+
						" sum(case when ticket_time like '%06:%:%' THEN  (px_count) ELSE 0 END) 7hr, "+
						" sum(case when ticket_time like '%07:%:%' THEN  (px_count) ELSE 0 END) 8hr, "+
						" sum(case when ticket_time like '%08:%:%' THEN  (px_count) ELSE 0 END) 9hr, "+
						" sum(case when ticket_time like '%09:%:%' THEN  (px_count) ELSE 0 END) 10hr, "+
						" sum(case when ticket_time like '%10:%:%' THEN  (px_count) ELSE 0 END) 11hr, "+
						" sum(case when ticket_time like '%11:%:%' THEN  (px_count) ELSE 0 END) 12hr, "+
						" sum(case when ticket_time like '%12:%:%' THEN  (px_count) ELSE 0 END) 13hr, "+
						" sum(case when ticket_time like '%13:%:%' THEN  (px_count) ELSE 0 END) 14hr, "+
						" sum(case when ticket_time like '%14:%:%' THEN  (px_count) ELSE 0 END) 15hr, "+
						" sum(case when ticket_time like '%15:%:%' THEN  (px_count) ELSE 0 END) 16hr, "+
						" sum(case when ticket_time like '%16:%:%' THEN  (px_count) ELSE 0 END) 17hr, "+
						" sum(case when ticket_time like '%17:%:%' THEN  (px_count) ELSE 0 END) 18hr, "+
						" sum(case when ticket_time like '%18:%:%' THEN  (px_count) ELSE 0 END) 19hr, "+
						" sum(case when ticket_time like '%19:%:%' THEN  (px_count) ELSE 0 END) 20hr, "+
						" sum(case when ticket_time like '%20:%:%' THEN  (px_count) ELSE 0 END) 21hr, "+
						" sum(case when ticket_time like '%21:%:%' THEN  (px_count) ELSE 0 END) 22hr, "+
						" sum(case when ticket_time like '%22:%:%' THEN  (px_count) ELSE 0 END) 23hr, "+
						" sum(case when ticket_time like '%23:%:%' THEN  (px_count) ELSE 0 END) 24hr from "+
						" (SELECT round((px_total_amount-toll_amount-service_tax_amt)/px_count) denom, px_count,ticket_date, ticket_time FROM "+depotdb+".ticket "+
						" WHERE bus_service_id ="+servicetype+" AND ticket_date between '"+date1+"' and '"+date2+"'  and ticket_sub_type_short_code='"+tickettype+"' and ticket_no!=0 and "+weekdays+")d group by denom ";
          System.out.println(sql);
		 rs = stmt.executeQuery(sql);

	
		 regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'>DEPOT : "+depot+" </br>Denomination Wise Hourly Count Report </br></h4></div>");

		 regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		

		 regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>Sl.No</th><th>Denom</th><th>1hr</th><th>2hr</th><th>3hr</th><th>4hr</th><th>5hr</th><th>6hr</th><th>7hr</th><th>8hr</th><th>9hr</th><th>10hr</th><th>11hr</th><th>12hr</th><th>13hr</th>" +
					"<th>14hr</th><th>15hr</th><th>16hr</th><th>17hr</th><th>18hr</th><th>19hr</th><th>20hr</th><th>21hr</th><th>22hr</th><th>23hr</th><th>24hr</th>" +
					"</tr></thead><tbody>");
		
			Common cm = new Common();
		        HttpServletResponse response = ServletActionContext.getResponse();
		        int i=1;
		        
		        int total_1=0;
		        int total_2=0;
		        int total_3=0;
		        int total_4=0;
		        int total_5=0;
		        int total_6=0;
		        int total_7=0;
		        int total_8=0;
		        int total_9=0;
		        int total_10=0;
		        int total_11=0;
		        int total_12=0;
		        int total_13=0;
		        int total_14=0;
		        int total_15=0;
		        int total_16=0;
		        int total_17=0;
		        int total_18=0;
		        int total_19=0;
		        int total_20=0;
		        int total_21=0;
		        int total_22=0;
		        int total_23=0;
		        int total_24=0;
		        
		        
		       
		        
		        while (rs.next()) {

						regionTypeAjaxString1.append("<tr>");
						
						 regionTypeAjaxString1.append("<td align='right'>"+i++ +"</td>");
						 regionTypeAjaxString1.append("<td align='right'>"+rs.getString("denom").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("1hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("2hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("3hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("4hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("5hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("6hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("7hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("8hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("9hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("10hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("11hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("12hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("13hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("14hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("15hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("16hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("17hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("18hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("19hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("20hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("21hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("22hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("23hr").toString() +"</td>");
							regionTypeAjaxString1.append("<td align='right'>"+rs.getString("24hr").toString() +"</td>");

							 regionTypeAjaxString1.append("</tr>");
							 
							 total_1+=Integer.parseInt(rs.getString("1hr").toString());
							 total_2+=Integer.parseInt(rs.getString("2hr").toString());
							 total_3+=Integer.parseInt(rs.getString("3hr").toString());
							 total_4+=Integer.parseInt(rs.getString("4hr").toString());
							 total_5+=Integer.parseInt(rs.getString("5hr").toString());
							 total_6+=Integer.parseInt(rs.getString("6hr").toString());
							 total_7+=Integer.parseInt(rs.getString("7hr").toString());
							 total_8+=Integer.parseInt(rs.getString("8hr").toString());
							 total_9+=Integer.parseInt(rs.getString("9hr").toString());
							 total_10+=Integer.parseInt(rs.getString("10hr").toString());
							 total_11+=Integer.parseInt(rs.getString("11hr").toString());
							 total_12+=Integer.parseInt(rs.getString("12hr").toString());
							 total_13+=Integer.parseInt(rs.getString("13hr").toString());
							 total_14+=Integer.parseInt(rs.getString("14hr").toString());
							 total_15+=Integer.parseInt(rs.getString("15hr").toString());
							 total_16+=Integer.parseInt(rs.getString("16hr").toString());
							 total_17+=Integer.parseInt(rs.getString("17hr").toString());
							 total_18+=Integer.parseInt(rs.getString("18hr").toString());
							 total_19+=Integer.parseInt(rs.getString("19hr").toString());
							 total_20+=Integer.parseInt(rs.getString("20hr").toString());
							 total_21+=Integer.parseInt(rs.getString("21hr").toString());
							 total_22+=Integer.parseInt(rs.getString("22hr").toString());
							 total_23+=Integer.parseInt(rs.getString("23hr").toString());
							 total_24+=Integer.parseInt(rs.getString("24hr").toString());
							
							 
						
						
		        }
	
					regionTypeAjaxString1.append("<tr><td colspan='2'><center><b>Total</b></center></td><td>"+total_1+"</td><td>"+total_2+"</td><td>"+total_3+"</td><td>"+total_4+"</td>" +
							"<td>"+total_5+"</td><td>"+total_6+"</td><td>"+total_7+"</td><td>"+total_8+"</td><td>"+total_9+"</td><td>"+total_10+"</td>" +
									"<td>"+total_11+"</td><td>"+total_12+"</td><td>"+total_13+"</td><td>"+total_14+"</td><td>"+total_15+"</td><td>"+total_16+"</td><td>"+total_17+"</td>" +
											"<td>"+total_18+"</td><td>"+total_19+"</td><td>"+total_20+"</td><td>"+total_21+"</td><td>"+total_22+"</td><td>"+total_23+"</td><td>"+total_24+"</td></tr>");
					 PrintWriter out;
                 

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	
}
