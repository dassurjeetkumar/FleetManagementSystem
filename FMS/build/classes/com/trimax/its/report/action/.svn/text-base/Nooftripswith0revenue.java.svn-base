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

public class Nooftripswith0revenue extends ActionSupport{

	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String date;
	 public String depotNo;
	 public String divisonNo;
	 ArrayList<Integer> l;
	 
	

	public ArrayList<Integer> getL() {
		return l;
	}

	public void setL(ArrayList<Integer> l) {
		this.l = l;
	}

	public String getEnddate() {
		return date;
	}

	public void setEnddate(String enddate) {
		this.date = enddate;
	}

	public String getDivisonNo() {
		return divisonNo;
	}

	public void setDivisonNo(String divisonNo) {
		this.divisonNo = divisonNo;
	}

	public String getDepotNo() {
		return depotNo;
	}

	public void setDepotNo(String depotNo) {
		this.depotNo = depotNo;
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


	public String AjaxNooftripswith0revenue()
	{
		Session session1 = null;
		 Connection connection = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	try {
		  
		 Date  ss1=new Date();
	      Common common = new Common();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
		String date1=common.getDateFromPicker(date);
		
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	//	String sql="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotNo+"' AND division_id = '"+divisonNo+"'";
		String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotNo+"'";
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
		// System.out.println("connection........."+connection);
		 System.out.println("connection........."+connection);
		 stmt = connection.createStatement();
		    
         String voucherQuery = "";
         
         /*voucherQuery = "SELECT Waybill_No,generated_Date,schedule_no,COUNT(*) COUNT,etim_no,EMPLOYEE_NAME,TOKEN,px_total_amount FROM " +
         		"(SELECT wd.Waybill_No,generated_Date,tc.schedule_no,tc.trip_no,tc.etim_no,emp.EMPLOYEE_NAME,emp.TOKEN,tc.px_total_amount " +
         		"FROM ticket tc INNER JOIN Waybill_Details wd On wd.Waybill_No = tc.waybill_no " +
         		"INNER JOIN employee emp ON tc.conductor_token_id=emp.TOKEN " +
         		"WHERE wd.Ticket_Audited_date BETWEEN ('"+date1+" 0:00:00') AND ('"+date1+" 23:59:59') GROUP BY wd.Waybill_No,trip_no " +
         		"HAVING SUM(tc.px_total_amount)=0)a GROUP BY Waybill_No";*/
         
         
         voucherQuery="	SELECT Waybill_No,DATE_FORMAT(generated_Date,'%d-%m-%y')generated_Date,schedule_no,COUNT(*) COUNT,etim_no,EMPLOYEE_NAME,TOKEN,px_total_amount,shift_type_name FROM "+
  		"(SELECT wd.Waybill_No,generated_Date,tc.schedule_no,tc.trip_no,tc.etim_no,emp.EMPLOYEE_NAME,emp.TOKEN,tc.px_total_amount ,wd.Shift_Type, st.shift_type_name "+
  		"FROM ticket tc INNER JOIN Waybill_Details wd On wd.Waybill_No = tc.waybill_no "+
        "inner join shift_type st on wd.Shift_Type=st.shift_type_id "+
  		"INNER JOIN employee emp ON tc.conductor_token_id=emp.TOKEN "+ 
  		"WHERE wd.Ticket_Audited_date BETWEEN ('"+date1+" 0:00:00') AND ('"+date1+" 23:59:59') GROUP BY wd.Waybill_No,trip_no "+ 
  		"HAVING SUM(tc.px_total_amount)=0)a GROUP BY Waybill_No";
		

         rs = stmt.executeQuery(voucherQuery);
		    
		
        
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>No of trips with '0' revenue</br>Date:- "+date+"</h4></div>";
					     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
						    
				        
				        
				        
				        String nwkr="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _\n\n"
				          +	  "                                     "+""+"                                                                          \n"
			      		  +   "                                            "+""+"                                                                                  \n" 
			      		  +   "                                  No of trips with '0' revenue     " +
			      		 "  \n                                 Date:- "+date+"   \n"                                           
			      		  +   "                                                               Run Date:-"+runDateTime+"               \n";
	      
				       
				        
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' " +
				        		"style='width:50%;border-collapse: collapse;'></thead>";
						regionTypeAjaxString +="<td align='center' width='10%'><b>Sr.No.</b></td><td align='center' width='10%'><b>Date</b></td><td align='center' width='20%'><b>Schedule</b></td>";
						regionTypeAjaxString +="<td align='center' width='20%'><b>Duty Type</td>";
						regionTypeAjaxString +="<td align='center' width='20%'><b>No of Trips</b></td><td align='center' width='20%'><b>ETM No.</b></td>";
						regionTypeAjaxString +="<td align='center' width='20%'><b>Conductor Name</b></td><td align='center' width='20%'><b>Conductor Token</b></td></tr>";
						
						
						
				
						
					 int i=1;
						  while (rs.next()) {
							
							regionTypeAjaxString +="<tr>";
							regionTypeAjaxString +="<td>"+i+"</td>";
							regionTypeAjaxString +="<td>"+rs.getString("generated_Date")+"</td>";
							regionTypeAjaxString +="<td>"+rs.getString("schedule_no")+"</td>";
							regionTypeAjaxString +="<td>"+rs.getString("shift_type_name").toString()+"</td>";
							String waybillno=rs.getString("Waybill_No").toString();
							regionTypeAjaxString +="<td><a href='#' onclick=jsFunction('"+waybillno+"');>"+rs.getString("COUNT")+"</a>"+"</td>";
							regionTypeAjaxString +="<td>"+rs.getString("etim_no")+"</td>";
							regionTypeAjaxString +="<td>"+rs.getString("EMPLOYEE_NAME")+"</td>";
						    regionTypeAjaxString +="<td>"+rs.getString("TOKEN")+"</td>"; 
						    regionTypeAjaxString +="</tr>";
						 i++;   
					 }
						  regionTypeAjaxString += "</table></div> </div>  <div align='center'></div>";
   
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
				
						out = response.getWriter();
						out.print(regionTypeAjaxString);
	                }catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						if(session1!=null){
							session1.close();
						}
					}
					 

					return null;
	}
public String ShowTripdetailsAll(){
	HttpServletRequest request = ServletActionContext.getRequest();
    String waybillid = request.getParameter("waybillid");
    String depotNo= request.getParameter("depotNo");
    Session session1 = null;
	 Connection connection = null;
       Statement stmt = null;
       ResultSet rs = null;
try {
	 
     Common common = new Common();
	
	Transaction transaction  = null;
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	//String sql="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotNo+"' ";
	String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotNo+"'";
//	String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
	    
    String voucherQuery = "SELECT trip_no FROM ticket tc WHERE waybill_no='"+waybillid+"' GROUP BY trip_no HAVING SUM(px_total_amount)=0";
    	
    rs = stmt.executeQuery(voucherQuery);
    l=new ArrayList<Integer>();
    while (rs.next()) {
    	l.add(Integer.parseInt(rs.getString("trip_no")));
    }
}catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally{
	if(session1!=null){
		session1.close();
	}
}
	return "success";
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
