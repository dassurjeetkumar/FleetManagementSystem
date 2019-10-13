package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ConductorEtmUseage {
	private Map<Integer, String> divisionlist;
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


	public String startdate;
	    public String enddate;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() {
	VtsDataDAO vvt = VtsDataDAO.getInstance();
	divisionlist = vvt.getDivisionName();
	return "success";
	}
	
	
	public String getetmuseagedata() {
		JSONObject result = new JSONObject();
	     JSONArray array = new JSONArray();
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
		    String division= req.getParameter("div");
		    String depot= req.getParameter("depot");
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	
		
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
		 String sql1="";
// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
			if(division.trim().equals("0")) {
				 sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info where status='N' order by depotname ";

			}
			else if(depot.trim().equals("0")) {
			 sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE division_id = '"+division+"' order by depotname ";

		}else {
 sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
		}
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
				int i=0;
				for (int j = 0; j < aliasToValueMapList.size(); j++) {
					Map<String, Object> aliasValue = aliasToValueMapList.get(j);
					 depotdb=aliasValue.get("depotname").toString();
					 depotIp=aliasValue.get("central_ip").toString();
					 user=aliasValue.get("central_uname").toString();
					 password=aliasValue.get("central_pwd").toString();
			
			 Class.forName("com.mysql.jdbc.Driver");
			 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":13306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
			 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":13306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

			 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
			
/*		String sql="select count(*) no, sum(ETIM_Coll_Amt) etm,sum(manualpassenger) manual,"
				+ "(select org_name from depot) org_name," +
				"round((sum(ETIM_Coll_Amt)/(sum(ETIM_Coll_Amt)+sum(manualpassenger)))*100) etmper," +
				"round((sum(manualpassenger)/(sum(ETIM_Coll_Amt)+sum(manualpassenger)))*100) manuper from Waybill_Details wd " +
				//"inner join employee e on e.EMPLOYEE_ID=wd.conductor_Id " +
				"LEFT JOIN (SELECT a1.waybill_cwa_block_master_id, " +
				"sum(if(ticket_type_manual_id IN (1) and is_service_tax='N', value, 0)) AS manualpassenger FROM waybill_cwa_block_master a1 " +
				"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id  " +
				"GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2 " +
				"where Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' and wd.Status In ('Audited','closed') " +
				"and (Bag_Coll_Amt+ETIM_Coll_Amt)>0 group by org_name";*/
		 
		 String sql="select *,round((bvalue/(bvalue+etm_amt))*100,2) manualper,round((etm_amt/(bvalue+etm_amt))*100,2) etmper from ( " + 
		 		"select * ,sum((select sum(ETIM_Coll_Amt)  from Waybill_Details where date(Ticket_Audited_Date)=date1  and (`ETIM_Coll_Amt`+Bag_Coll_Amt)>0  ))etm_amt from( " + 
		 		"select count(distinct waybill_no) sch,sum(ifnull(block.value,0)) as bvalue ,date(Ticket_Audited_Date) date1, " + 
		 		"(select org_name from depot) org_name " + 
		 		"from Waybill_Details wd   " + 
		 		"left join waybill_cwa_block_master cwa  on cwa.waybill_cwa_block_master_id=wd.param2   " + 
		 		"left join Waybill_cwa_receipt_details block  on block.waybill_cwa_block_master_id = cwa.waybill_cwa_block_master_id and block.value!=0  " + 
		 		"where  wd.Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59'  and (`ETIM_Coll_Amt`+Bag_Coll_Amt)>0)a )b ";
		System.out.println(sql);
		 rs = stmt.executeQuery(sql);

	
	/*	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Conductor Wise % Of ETM Usage Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Conductor Name</th><th>Conductor Token</th><th>No.Of Days Operated</th>" +
				"<th>ETM Revenue</th><th>Manual Revenue</th><th>ETM %</th><th>Manual %</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		        int i=1;
					 while (rs.next()) {
						 if(rs.getString("etm").equalsIgnoreCase("0.00") && rs.getString("manual").equalsIgnoreCase("0")){
							 
						 }else{

			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ i++ +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("EMPLOYEE_NAME") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("TOKEN") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("noofdays") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("etm") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("manual") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("etmper") +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ rs.getString("manuper") +"</td>");
			
			
			 regionTypeAjaxString1.append("</torg_namer>");
						 }
		
					 }*/
		 

	 
	    
	      while (rs.next()) {
	        	JSONArray ja = new JSONArray();
	         i++;
	        	// Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(i);
			
			if(rs.getString("etmper").toString().trim().equals("0") && rs.getString("bvalue").toString().trim().equals("0")){
				ja.add("<center>"+rs.getString("org_name").toString()+"<center>");
			}else{
			 ja.add("<center>"+"<a data-toggle='modal'   href='#mymodal11'  onclick=javascript:getdepotwise('D"+rs.getString("org_name").toString().substring(6, 8)+"','"+date1+"','"+date2+"') title='Details'>"+rs.getString("org_name").toString() + "</a>"+"<center>");
			}
			ja.add("<center>"+rs.getString("sch").toString()+"<center>");
			ja.add("<center>"+rs.getString("etm_amt").toString()+"<center>");
			ja.add("<center>"+rs.getString("bvalue").toString()+"<center>");
			ja.add("<center>"+rs.getString("etmper").toString()+"<center>");
			ja.add("<center>"+Double.parseDouble(rs.getString("manualper").trim().toString())+"<center>");
	      
			
			array.add(ja);
			
			
		 }	}

   HttpServletResponse response = ServletActionContext.getResponse();

	 PrintWriter out;

		    	result.put("aaData",array);
				out = response.getWriter();
				out.print(result);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;
	}
}
