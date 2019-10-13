package com.trimax.its.report.action;

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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class IssueRegisterReport extends ActionSupport{
	
	
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
		
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);

		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		public String getmonthlyTicketIssue()
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
			 //String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
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
				
			 String sql="select dy.denomination_type_manual as denom,hti.denomination_key as dkey,htim.voucher_number as vnumber," +
				"min(hti.opening_number) as onr,max(hti.closing_number) as cnr,sum(hti.number_of_books) as nbooks," +
				"sum(hti.number_of_passes) as npasses,sum(hti.value) as val from his_ticket_invoice_master htim " +
				"inner join his_ticket_invoice_details htid on htid.ticket_invoice_mast_id=htim.his_id " +
				"inner join his_ticket_inventory_details hti on hti.his_id=htid.ticket_inventory_det_id " +
				"inner join denomination_type_manual dy on dy.denomination_type_manual_id= hti.denomination_type_manual_id " +
				"WHERE htim.his_date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' and htim.status = 'ACTIVE' and " +
				"htim.current_status='Issued' group by hti.denomination_key order by denom";

			 rs = stmt.executeQuery(sql);

			 double Totalvalues=0.0;
			regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'>"+depot1+" </br>Ticket Issue Register Report </br></h4></div>");

			regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		        

			regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString1.append("<thead><tr><th>S.No</th><th>Denom</th><th>Denom key</th><th>Voucher No</th><th>Opening Number</th><th>Closing Number</th>" +
					"<th>No Of Books</th><th>No Of Passes</th><th>Value</th>"+"</tr></thead><tbody>");
			

			        HttpServletResponse response = ServletActionContext.getResponse();
			        int i=1;
 					 while (rs.next()) {

 			   regionTypeAjaxString1.append("<tr>");
 				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("denom") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("dkey") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("vnumber") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("onr") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("cnr") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("nbooks") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("npasses") +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("val") +"</td>");
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

		

}
