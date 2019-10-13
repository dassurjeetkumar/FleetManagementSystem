package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
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
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ConductorWiseThreeCodeCountAction {
	
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
		public String tokenId ;
	


	

	public String getTokenId() {
			return tokenId;
		}


		public void setTokenId(String tokenId) {
			this.tokenId = tokenId;
		}


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
			return "success";
		}

		
		public String getConductorWiseThreeCodeCount()
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
//			String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot1+"'";
////			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
//			Query qry=session1.createSQLQuery(sql1).
//					addScalar("dbname")
//					.addScalar("ip")
//					.addScalar("uname")
//					.addScalar("password");
//			
//			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String, Object>> aliasToValueMapList = qry.list();	
//				String depotdb="";
//				String depotIp="";
//				String user="";
//				String password="";
//				for (int j = 0; j < aliasToValueMapList.size(); j++) {
//					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
//					 depotdb=aliasValue.get("dbname").toString();
//					 depotIp=aliasValue.get("ip").toString();
//					 user=aliasValue.get("uname").toString();
//					 password=aliasValue.get("password").toString();
//				}
//			 Class.forName("com.mysql.jdbc.Driver");
//			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":3306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
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
				
			String sql="select count(*) count,e.EMPLOYEE_NAME,e.TOKEN,wpc.threecode_amount from waybill_pending_collection wpc " +
					"inner join Waybill_Details wd on wd.waybill_No=wpc.waybill_no " +
					"left join employee e on e.EMPLOYEE_ID=wd.conductor_Id " +
					"where wpc.createdDate between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' AND wd.status in('closed','AUDITED') " +
					" group by e.TOKEN ";
//        System.out.println(sql);
			 rs = stmt.executeQuery(sql);

		
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Conductor Wise 3-code Count Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        

			regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Conductor Token</th><th>Conductor Name</th><th>Three Code Count</th>" +
						""+"</tr></thead><tbody>");
			

			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
 					 while (rs.next()) {

 			   regionTypeAjaxString1.append("<tr>");
 				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("TOKEN") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("EMPLOYEE_NAME") +"</td>");
				//regionTypeAjaxString1.append("<td align='right'><a href='#'>"+ rs.getString("count") +"</a></td>");
				regionTypeAjaxString1.append("<td align='right'><a data-toggle='modal'  href='#myModal11' ><button type='button' class='btn grey'onclick='getDetails("+rs.getString("TOKEN")+",\""+startdate+"\",\""+enddate+"\","+depotlist1+")'>"+ rs.getString("count") +"</button></a></td>");
				//regionTypeAjaxString1.append("<td align='right'><a data-toggle='modal'  href='#myModal11' ><button type='button' class='btn grey' onclick='getDetails("+rs.getString("TOKEN")+")"+rs.getString("count")+"</button></a></td>");
				
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
		
		public String getViewConductorthrecodeDetails(){
			setTokenId(ServletActionContext.getRequest().getParameter("value"));
			setStartdate(ServletActionContext.getRequest().getParameter("startdate"));
			setEnddate(ServletActionContext.getRequest().getParameter("enddate"));
			setDepotlist1(ServletActionContext.getRequest().getParameter("depotlist1"));
			System.out.println("hiii"+getStartdate());
			return "success";
		}
		
		public String getajaxconductordetails(){
			try{
				Common common = new Common();
				String date1=common.getDateFromPicker(startdate);
				String date2=common.getDateFromPicker(enddate);
			    String division1= divisionlist1;
			    String depot1= depotlist1;
			    String tokenId = ServletActionContext.getRequest().getParameter("tokenId");
			   // String date1 = ServletActionContext.getRequest().getParameter("startdate1");
			   // String date2 = ServletActionContext.getRequest().getParameter("enddate1");
			
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
//			String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot1+"'";
////			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
//			Query qry=session1.createSQLQuery(sql1).
//					addScalar("dbname")
//					.addScalar("ip")
//					.addScalar("uname")
//					.addScalar("password");
//			
//			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String, Object>> aliasToValueMapList = qry.list();	
//				String depotdb="";
//				String depotIp="";
//				String user="";
//				String password="";
//				for (int j = 0; j < aliasToValueMapList.size(); j++) {
//					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
//					 depotdb=aliasValue.get("dbname").toString();
//					 depotIp=aliasValue.get("ip").toString();
//					 user=aliasValue.get("uname").toString();
//					 password=aliasValue.get("password").toString();
//				}
//			 Class.forName("com.mysql.jdbc.Driver");
//			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":3306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
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
				
			String sql="select Ticket_Audited_Date,wd.waybill_No,wpc.collection_amount  from waybill_pending_collection wpc " +
					"inner join Waybill_Details wd on wd.waybill_No=wpc.waybill_no  " +
					"left join employee e on e.EMPLOYEE_ID=wd.conductor_Id " +
					"where wpc.createdDate between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' and wd.status in('closed','AUDITED') and e.TOKEN='"+tokenId+"'  ";
        System.out.println(sql);
			 rs = stmt.executeQuery(sql);

		
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Conductor Wise 3-code Count Report Details </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        

			regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Audited Date</th><th>Waybill No</th><th>Amount</th>" +
						""+"</tr></thead><tbody>");
			

			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
 					 while (rs.next()) {

 			   regionTypeAjaxString1.append("<tr>");
 				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("Ticket_Audited_Date") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("waybill_No") +"</td>");
			//	regionTypeAjaxString1.append("<td align='right'><a href='#' onclick='jsFunction1("+rs.getString("TOKEN")+"</a>"+ rs.getString("count") +"</a></td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("collection_amount") +"</td>");
				
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
	

}
