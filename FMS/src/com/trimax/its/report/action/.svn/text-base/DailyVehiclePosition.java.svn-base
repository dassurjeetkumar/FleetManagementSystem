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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DailyVehiclePosition extends ActionSupport{
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	 public String depotNo;
	 public String divisonNo;
	

	   // private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;
		private String depotlist1;
		private String divisionlist1;


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

	public String getDepotNo() {
		return depotNo;
	}

	public void setDepotNo(String depotNo) {
		this.depotNo = depotNo;
	}

	public String getDivisonNo() {
		return divisonNo;
	}

	public void setDivisonNo(String divisonNo) {
		this.divisonNo = divisonNo;
	}

	
	char fl = 18;
		char f2 = 12;
		int pagi = 1;

		int FULL_LEN = 120;
		int HALF_LEN = 60;

		int subtotalTickets=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";
	
	private Map<Integer, String> divisionlist;
	
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	@Override
	public String execute() throws Exception {
		this.setDivisionlist(getDivisionName());
		return "success";
	}

	private Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  " +
						"where parent_id ='1' and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	@SuppressWarnings("finally")
public String DailyVehiclePosition()
  {
	try {
		  
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
	    String division1= divisionlist1;
	    String depot1= depotlist1;
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot2=req.getParameter("depotlist1");
	Connection connection=null;
	Statement stmt=null;
	Session session1 = null;
	ResultSet rs=null;
	Transaction transaction  = null;
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 String realpath = ServletActionContext.getRequest()
				.getRealPath("/");
	// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot2+"'";
	 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot2+"'";
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
		
		String sql="";
		 sql="select 'Schedules(As per Form-V)' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Schedules Operated' as Particulars,count(*) Nos from Waybill_Details " +
					"where status IN ('AUDITED','CLOSED') and (ETIM_Coll_Amt+Bag_Coll_Amt)!=0 " +
					"and Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' " +
					"UNION select 'Actual Schedules (operated)' as Particulars,count(*) Nos from Waybill_Details " +
					"where status IN ('AUDITED','CLOSED') " +
					"and Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' " +
					"union select 'Schedules Cancelled (Planned)' as Particulars,COUNT(*) AS Nos from gen_logsheet " +
					"WHERE gen_logsheet_date='"+date1+"' AND cancel_type='planned' union " +
					"select 'Vehicles held' as Particulars,count(*) AS Nos from vehicle WHERE status='ACTIVE' and deleted_status=0 union " +
					"select 'Vehicles on Road' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Vehicles off road at Depot' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Vehicles held at CWS-I/II/DWS' as Particulars,COUNT(*) AS Nos from gen_logsheet " +
					"WHERE gen_logsheet_date='"+date1+"' AND Duty_type in ('CWS','DWS') union " +
					"select 'Vehicles held at Authorized Dealers' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Vehicles road worthy spare' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Vehicles held at Police Station' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Vehicles sent to other depots' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Vehicles received from other depots' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'New Vehicles added' as Particulars,0 AS Nos from Waybill_Details union " +
					"select 'Vehicles on Casual Contract' as Particulars,COUNT(*) AS Nos from gen_logsheet " +
					"WHERE gen_logsheet_date='"+date1+"' AND Duty_type='CC'";
		 rs = stmt.executeQuery(sql);
//		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
//		 transaction = session1.beginTransaction();
//		 Query query = session1.createSQLQuery(sql).addScalar("dte").addScalar("snumber")
//			 		.addScalar("dist").addScalar("total");
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String, Object>> aliasToValueMapList = query.list();
//					String realpath = ServletActionContext.getRequest()
//							.getRealPath("/");
//		
//			
//					String filePath = "Reports/";
//
//					String fileName = "EarlyArrivalSummary.txt";
					
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Daily Vehicle Position Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        
				        
				        
				  
//				        regionTypeAjaxString +="<div align='center'><b>Range Of Conductors:</b></div>"+"<br>";
				       
				        
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
				        //regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No</th>" +
								"<th>Particulars</th><th colspan='5'>Nos.</th>" +
								""+"</tr></thead><tbody>";
						


						 int i=1;
	 					 while (rs.next()) {
//							
							regionTypeAjaxString +="<tr>";
							regionTypeAjaxString +="<td>"+ i++ +"</td>";
							
							 regionTypeAjaxString +="<td>"+ rs.getString("Particulars").toString() +"</td>";
					    	 regionTypeAjaxString +="<td >"+ rs.getString("Nos").toString() +"</td>";
//							
							regionTypeAjaxString +="</tr>";
						}
//						
				
					 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
//				

					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
					
					
					
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (IOException e) {
						
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
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
}
