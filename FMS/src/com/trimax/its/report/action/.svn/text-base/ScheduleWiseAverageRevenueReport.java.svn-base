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

public class ScheduleWiseAverageRevenueReport {
	
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	    public String enddate;
	    public Map<Integer, String> servicetypemap;
	    public String servicetpe;
	
	    public String getServicetpe() {
			return servicetpe;
		}


		public void setServicetpe(String servicetpe) {
			this.servicetpe = servicetpe;
		}


		public Map<Integer, String> getServicetypemap() {
			return servicetypemap;
		}


		public void setServicetypemap(Map<Integer, String> servicetypemap) {
			this.servicetypemap = servicetypemap;
		}
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
			this.setServicetypemap(getServiceType1());
			return "success";
		}

		
		public String getScheduleWiseAverageRevenueReport()
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
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");

			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
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
				
			 String query1 ="";
			 if(servicetpe.equalsIgnoreCase("0")){
				 query1="";
			 }else{
				 query1= "and wd.Service_Type='"+servicetpe+"'";
			 }
			 stmt = connection.createStatement();
				
			String sql="select schedule_number,dutytype,count(*) ndays,sum(totalrevenue) totalrevenue,sum(tamount) tamount," +
					"round((sum(totalrevenue)/count(*)),2) avgreve,round((sum(tamount)/count(*))) tavgreve," +
					"ifnull(round(((sum(totalrevenue))/(sum(tamount)))*100,2),0) percen,sum(ifnull(passenger,0)) passenger " +
					"from (select s.schedule_number,if(schedule_type_name='Day Out',shift_type_name,schedule_type_name) dutytype," +
					"(ETIM_Coll_Amt+Bag_Coll_Amt) totalrevenue,ifnull(tamount,0)tamount,sum(px_count) passenger from Waybill_Details wd " +
					"inner join form_four ff on ff.form_four_id=wd.schedule_No inner join schedule_type st on st.schedule_type_id=wd.Schedult_Type " +
					"inner join shift_type sht on sht.shift_type_id=wd.Shift_Type INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
					"left join ticket t on t.waybil_Id=wd.waybil_Id and ticket_printed_flag='Y' AND fare_type Not IN ('NINC') " +
					"left join (SELECT distinct(targetamount) tamount, `scheduleid`,shift_type_id FROM `scheduletargetamount` where " +
					"deletedstatus=0 and  (effective_end_date is null or effective_end_date>='"+date2+"') GROUP BY `scheduleid`,shift_type_id) " +
					"star on star.scheduleid=s.schedule_id and star.shift_type_id=sht.shift_type_id where Ticket_Audited_Date between " +
					"'"+date1+" 00:00:00' and '"+date2+" 23:59:59' "+query1+"  and wd.Status In ('Audited','closed') and  " +
					"(Bag_Coll_Amt+ETIM_Coll_Amt)>0 group by wd.waybil_Id)a " +
					"group by schedule_number,dutytype order by percen asc";
//			 String sql="select schedule_number,dutytype,count(*) ndays,sum(totalrevenue) totalrevenue,sum(tamount) tamount," +
//			 		"round((sum(totalrevenue)/count(*)),2) avgreve,round((sum(tamount)/count(*))) tavgreve," +
//			 		"round(((sum(totalrevenue))/(sum(tamount)))*100,2) percen,sum(ifnull(passenger,0)) passenger " +
//			 		"from (select wd.schedule_No,wd.waybill_No,generated_Date,schedule_number,if(schedule_type_name='Day Out',shift_type_name,schedule_type_name) dutytype," +
//			 		"(ETIM_Coll_Amt+Bag_Coll_Amt) totalrevenue,avg(targetamount) tamount,sum(px_count) passenger from Waybill_Details wd  " +
//			 		"inner join form_four ff on ff.form_four_id=wd.schedule_No " +
//			 		"inner join schedule_type st on st.schedule_type_id=wd.Schedult_Type " +
//			 		"inner join shift_type sht on sht.shift_type_id=wd.Shift_Type " +
//			 		"INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id " +
//			 		"left join ticket t on t.waybil_Id=wd.waybil_Id and ticket_printed_flag='Y' AND fare_type Not IN ('NINC') " +
//			 		"inner join  `scheduletargetamount`  sm on s.schedule_id=sm.scheduleid " +
//			 		"where Ticket_Audited_Date between " +
//			 		"'"+date1+" 00:00:00' and '"+date2+" 23:59:59' "+query1+" and sm.deletedstatus=0 " +
//			 		"and wd.Status In ('Audited','closed') and (Bag_Coll_Amt+ETIM_Coll_Amt)>0  group by wd.waybil_Id)a " +
//			 		"group by schedule_number,dutytype order by percen asc";
//        System.out.println(sql);
			 rs = stmt.executeQuery(sql);

		
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Percentage Schedule Wise Average Revenue Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        

			regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Schedule No</th><th>Shift Type</th><th>No.Of Days<br>Operated</th>" +
					"<th>Total Revenue</th><th>Avg Schedule <br>Target Revenue</th><th>Average Revenue</th><th>Percentage</th><th>ETM Passenger count</th>" +
						""+"</tr></thead><tbody>");
			

			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
 					 while (rs.next()) {
 						
 			   regionTypeAjaxString1.append("<tr>");
 				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("schedule_number") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("dutytype") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("ndays") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("totalrevenue") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("tavgreve") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("avgreve") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("percen") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("passenger") +"</td>");
				 regionTypeAjaxString1.append("</tr>");
						
 					 }
 					 PrintWriter out;
                     String p=str;

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
		
		
		public Map<Integer, String> getServiceType1() {
			Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//int userId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("org_chart_id").toString());
			
			try {
				Query query = session
						.createSQLQuery("select service_type_id,service_type_name from service_type where status='ACTIVE' and deleted_status='0'").addScalar("service_type_id", Hibernate.INTEGER).addScalar("service_type_name", Hibernate.STRING);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				resultMap.put(0, "Select");
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


}
