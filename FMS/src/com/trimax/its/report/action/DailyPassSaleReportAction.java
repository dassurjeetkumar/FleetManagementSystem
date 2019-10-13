package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class DailyPassSaleReportAction {
	
	
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
		
		 public Map<Integer, String> passtype;
		    public String passtp;

		public Map<Integer, String> getPasstype() {
				return passtype;
			}

			public void setPasstype(Map<Integer, String> passtype) {
				this.passtype = passtype;
			}

			public String getPasstp() {
				return passtp;
			}

			public void setPasstp(String passtp) {
				this.passtp = passtp;
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
			//this.setPasstype(getPassType1());
			return "success";
		}

		
		public String getdailyPassSale()
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
			String passtype=req.getParameter("passlist1");
			Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
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
			String query1="";	
			 if(passtype.equalsIgnoreCase("0")){
					query1="";
				}else{
					query1="dtm.denomination_type_manual='"+passtype+"' and";
				}
				String sql="";
				
				sql="select bg.bag_code bag_code,s.schedule_number schedule_number,st.schedule_type_name schedule_type_name," +
						"dtm.denomination_type_manual denomination_type_manual,sum(blocks_sold) count,sum(value) value " +
						"from Waybill_cwa_receipt_details wcrd " +
						"inner join denomination_type_manual dtm on dtm.denomination_type_manual_id=wcrd.denomination_type_manual_id " +
						"inner join Waybill_Details wd on wd.param2=wcrd.waybill_cwa_block_master_id " +
						"inner join form_four fr on fr.form_four_id=wd.schedule_No " +
						"inner join schedule s on s.schedule_id=fr.schedule_number_id " +
						"inner join ticketbag_master bg on bg.ticketbag_id=wd.Bag_No " +
						"inner join schedule_type st on st.schedule_type_id=wd.Schedult_Type  " +
						"where "+query1+" Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' and dtm.ticket_type_manual_id='2' " +
						"and wd.Status IN (UPPER('Closed'),'AUDITED') GROUP BY denomination_type_manual,bg.bag_code order by cast(bg.bag_code as unsigned)";

			 rs = stmt.executeQuery(sql);

		
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'>"+depot1+" </br>Daily Pass Sale Report </br></h4></div>");

			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        

			regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>S.No</th><th>Bag No</th><th>Schedule No</th><th>Schedule Type</th><th>Pass</th><th>Counts</th><th>Value</th>"+"</tr></thead><tbody>");
			

			        HttpServletResponse response = ServletActionContext.getResponse();
			        int totalpass=0;
			        int totalcount=0;
			        int i=1;
 					 while (rs.next()) {

 			   regionTypeAjaxString1.append("<tr>");
 				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("bag_code") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("schedule_number") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("schedule_type_name") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("denomination_type_manual") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("count") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("value") +"</td>");
				totalpass=totalpass+Integer.parseInt(rs.getString("count").toString());
				totalcount=totalcount+Integer.parseInt(rs.getString("value").toString());
				 regionTypeAjaxString1.append("</tr>");
				
			
 					 }
 					regionTypeAjaxString1.append("<tr><td colspan='5'><center><b>Total</b></center></td><td align='right'><b>"+ totalpass+"</td><td align='right'><b>"+ totalcount+"</td></tr>" );
 					 PrintWriter out;
                     String p=str;

			out = response.getWriter();
			out.print(regionTypeAjaxString1);
			
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}

			return null;
		}

		


	
	
		
		public String passtypeForIts() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
		
			int parentId = Integer.parseInt(req.getParameter("val"));
			
			 Common comm = new Common();
		//	String dat1=comm. getDateFromPicker(dat);
	        		List<String> l1 = this.getPassId(parentId);
	        		List<String> l2 = this.getPassName(parentId);
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		
		public List getPassId(int parentID) {
	          List list = new ArrayList();
				
	          Connection connection=null;
				Statement stmt=null;
				Session session1 = null;
				ResultSet rs=null;
				
				Transaction transaction  = null;
				HttpServletRequest req=ServletActionContext.getRequest();
				try {
					Common common = new Common();
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				 transaction = session1.beginTransaction();
				 String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
				//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
			//	String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
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
				
				String qry1 = "select sys_value,denomination_type_manual from local_system_variable ls inner join denomination_type_manual dtm " +
						"on dtm.denomination_type_manual=ls.sys_value where dtm.ticket_type_manual_id='2' and dtm.deleted_status='0' and " +
						"ls.status='Y' and dtm.status='ACTIVE'";
				System.out.println("qry1"+qry1);
				rs=stmt.executeQuery(qry1);
			
				 while(rs.next()){
				System.out.println("--------"+rs.getString("denomination_type_manual"));
							list.add(rs.getString("denomination_type_manual"));
						System.out.println("++++++"+list.size());
					
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					return list;
					
			}
		
		public List getPassName(int parentID) {
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
				
				String qry1 = "select sys_value,denomination_type_manual from local_system_variable ls inner join denomination_type_manual dtm " +
						"on dtm.denomination_type_manual=ls.sys_value where dtm.ticket_type_manual_id='2' and dtm.deleted_status='0' and " +
						"ls.status='Y' and dtm.status='ACTIVE'";
				System.out.println("qry1"+qry1);
				rs=stmt.executeQuery(qry1);
			
				 while(rs.next()){
							list.add(rs.getString("sys_value"));
					
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					return list;
					
			}

}
