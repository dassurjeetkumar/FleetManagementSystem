package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import org.json.simple.JSONArray;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;


public class MonthlyTicketReceiptStmt {
	
	String path="";
	char ft = 15;
	String str="";
	String new_line = System.getProperty("line.separator");

	String c=" ";
	 public String startdate;
	    public String enddate;
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

	int subtotalTickets=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";


		
		
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		public String getMonthlyTicketReceiptstmtc()
		{

			double Totalvalues=0;
			int tTickettotal=0;
			double tTotalvalues=0;
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
//					String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","itishree","ItishreeN");
					 System.out.println("connection........."+connection);
				 stmt = connection.createStatement();

			 String sql="select dy.denomination_type_manual as denom,hti.denomination_key as dkey,htim.voucher_number as vnumber," +
						"min(hti.opening_number) as onr,max(hti.closing_number) as cnr,sum(hti.number_of_books) as nbooks," +
						"sum(hti.number_of_passes) as npasses,sum(hti.value) as val,'' as bag_code,'' as opening_number,'' as closing_number," +
						"0 as number_of_books,0 as totaltickets,0 as value from his_ticket_invoice_master htim " +
						"inner join his_ticket_invoice_details htid on htid.ticket_invoice_mast_id=htim.his_id " +
						"inner join his_ticket_inventory_details hti on hti.his_id=htid.ticket_inventory_det_id " +
						"inner join denomination_type_manual dy on dy.denomination_type_manual_id= hti.denomination_type_manual_id " +
						"WHERE htim.his_date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' and htim.status = 'ACTIVE' and " +
						"htim.current_status='Issued' group by hti.denomination_key union all SELECT denomination_type_manual as denom, " +
						"tbh.denomination_key as dkey,'' as vnumber,'' as onr,'' as cnr,0 as nbooks,0 as npasses,0 as val,bag_code," +
						"tbh.opening_number as opening_number, tbh.closing_number as closing_number,tbh.number_of_books as number_of_books," +
						"CAST(tbh.closing_number AS SIGNED)-CAST(tbh.opening_number AS SIGNED)+1 as totaltickets," +
						"(CAST(tbh.closing_number AS SIGNED)-CAST(tbh.opening_number AS SIGNED)+1)*denomination_type_manual as value " +
						"FROM `ticket_bag_header` tbh " +
						"INNER JOIN denomination_type_manual dtm ON dtm.denomination_type_manual_id = tbh.denoimination_type_manual_id " +
						"INNER JOIN ticket_bag_details tbd ON tbd.ticket_bag_header_id = tbh.ticket_bag_header_id " +
						"LEFT JOIN ticketbag_master tm ON tm.ticketbag_id = tbh.ticketbag_id WHERE tbh.`ticket_bag_header_id`  IN  " +
						"(SELECT `ticket_bag_header_id` FROM `ticket_bag_details` WHERE `created_date` " +
						"BETWEEN ('"+date1+" 00:00:00' ) AND ('"+date2+" 23:59:59')  AND `current_status` IN ('Issued','closed') " +
						"AND ticket_inventory_det_id!=0) ORDER BY CAST(bag_code AS SIGNED),CAST(denom AS SIGNED)";
//			 System.out.println(sql);
			 rs = stmt.executeQuery(sql);

			     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+depot+" </br> Monthly Ticket Receipt Stmt Report</br>From Date:- "+startdate+" To Date:- "+enddate+"</h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
		      
	        
		        
		        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th colspan='7' align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RECEIPTS</th><th colspan='7' align='center'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ISSUES</th>" +"</tr></thead>";
				regionTypeAjaxString +="<thead><tr><th>Denomenation</th><th>Series</th><th>Voucher Number</th><th>Start No</th><th>End No</th>" +
						"<th>No of Books</th><th>Value</th><th>Bag No</th><th>Start No</th><th>End No</th><th>No of Books</th><th>Value</th>" +"</tr></thead><tbody>";
				
				 int i=1;
					 while (rs.next()) {
						 
					    regionTypeAjaxString +="<tr>";
						//regionTypeAjaxString +="<td align='right'>"+i+++"</td>";
						regionTypeAjaxString +="<td align='right'>"+ rs.getString("denom").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("dkey").toString()+"</td>";
						if(rs.getString("vnumber")==null){
							regionTypeAjaxString +="<td align='right'>"+""+"</td>";
						}else{
						regionTypeAjaxString +="<td align='right'>"+rs.getString("vnumber").toString()+"</td>";
						}

						String onrs=rs.getString("onr").toString();
						if(onrs==null){
							regionTypeAjaxString +="<td align='right'>"+""+"</td>";
							onrs="";
						}else{
						regionTypeAjaxString +="<td align='right'>"+onrs+"</td>";
						}
						if(rs.getString("cnr")==null){
							regionTypeAjaxString +="<td align='right'>"+""+"</td>";
						}else{
						regionTypeAjaxString +="<td align='right'>"+rs.getString("cnr").toString()+"</td>";
						}
						if(rs.getString("denom").toString().equalsIgnoreCase("65") || rs.getString("denom").toString().equalsIgnoreCase("70")){
							regionTypeAjaxString +="<td align='right'>"+rs.getString("npasses").toString()+"</td>";
						}else{
							regionTypeAjaxString +="<td align='right'>"+rs.getString("nbooks").toString()+"</td>";
						}
						regionTypeAjaxString +="<td align='right'>"+rs.getString("val").toString()+"</td>";
						if(rs.getString("bag_code")==null){
							regionTypeAjaxString +="<td align='right'>"+""+"</td>";
						}else{
						regionTypeAjaxString +="<td align='right'>"+rs.getString("bag_code").toString()+"</td>";
						}
						if(rs.getString("opening_number")==null){
							regionTypeAjaxString +="<td align='right'>"+""+"</td>";
						}else{
						regionTypeAjaxString +="<td align='right'>"+rs.getString("opening_number").toString()+"</td>";
						}
						if(rs.getString("closing_number")==null){
							regionTypeAjaxString +="<td align='right'>"+""+"</td>";
						}else{
						regionTypeAjaxString +="<td align='right'>"+rs.getString("closing_number").toString()+"</td>";
						}
						if(rs.getString("denom").toString().equalsIgnoreCase("65") || rs.getString("denom").toString().equalsIgnoreCase("70")){
							regionTypeAjaxString +="<td align='right'>"+rs.getString("totaltickets").toString()+"</td>";
						}else{
							regionTypeAjaxString +="<td align='right'>"+rs.getString("number_of_books").toString()+"</td>";
						}
						
						regionTypeAjaxString +="<td align='right'>"+rs.getString("value").toString()+"</td>";
						tTotalvalues+=Double.parseDouble(rs.getString("val").toString());
						Totalvalues+=Double.parseDouble(rs.getString("value").toString());
						
						}
						
						   regionTypeAjaxString +="</tr>";
					
						   regionTypeAjaxString +="<tr><td colspan='6'><center><b>Sum</b></center></td><td align='right'><b>"+ tTotalvalues+"</td><td colspan='4'><center><b>Sum</b></center></td><td align='right'><b>"+ Totalvalues+"</td></tr>" +"\n";  
						 regionTypeAjaxString += "</tbody></table></div> </div>"; 

			
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			
			
				out = response.getWriter();
				out.print(regionTypeAjaxString);
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
		

}
