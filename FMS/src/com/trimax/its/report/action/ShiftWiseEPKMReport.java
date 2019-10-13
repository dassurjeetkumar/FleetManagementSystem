package com.trimax.its.report.action;

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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ShiftWiseEPKMReport extends ActionSupport {

	String path="";
	char ft = 15;
	String str="";
	public String startdate;
	public String enddate;
	String c=" ";

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	int subtotalTickets=0;
	int subtotalValues=0;
	
	String regionTypeAjaxString = "";
	
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
	
	
	
	public String execute() {
		//this.ServiceTaxCollections();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
	
	public List<Schedule> getScheduleTypeData() {
		List<Schedule> schedulelist = null;
		Session session = null;
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try{
			int depotId = Integer.parseInt(req.getParameter("val"));
			
//			List<String> l1 = dao.getCondTokenNoId(depotId);
//    		List<String> l2 = dao.getCondTokenNoName(depotId);
    		List<String> l1 = dao.getScheduleTypeId();
    		List<String> l2 = dao.getScheduleTypeName();
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='0'>------select------</option>";
    		regionTypeAjaxString += "<option value='1'>---All---</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    	
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//    		return null;
			
		
			return null;

	}
	
	
	public String getShiftWiseData(){
		Connection connection=null;
		Statement stmt=null;
		ResultSet rs=null;
		Session session1 = null;
		try {
			
			 HttpServletRequest req = ServletActionContext.getRequest();
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			Common common = new Common();
//			String orgname=dao.getOrgName();
//			String depot=dao.getDepotName();
			
			String depotid=req.getParameter("depot");
			String divisionid=req.getParameter("division");
			String scheduleid=req.getParameter("scheduleid");
			String shifttypeid="";
			if(scheduleid == "2"){
				shifttypeid=req.getParameter("nightshift");
			}else if(scheduleid == "3"){
				shifttypeid=req.getParameter("dayshift");
			}

			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
		    
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			String currentdate=sdf1.format(ss1);
			String curr_date=dao.getDateFromPickerDate(currentdate);

		    
		    double epkm=0.0;
			
			Transaction transaction = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 
			// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"' AND division_id = '"+divisionid+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
			 String sqlq="";
			
			 
//			 System.out.println("query"+sqlq);
				
//			 rs = stmt.executeQuery(sqlq);
		

			 
//			 String sql="";
//			 Query query = session1.createSQLQuery(sql).addScalar("Date").addScalar("ETIMServiceTax").addScalar("BagServiceTax").addScalar("PassServiceTax");
//
//			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String,Object>> aliasToValueMapList = query.list();
				
				
				
				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br> </br>Shift Wise EPKM Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		        
		       


		        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
		        regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Date</th><th>Depot:</th><th>SHIFT Type</th><th>No Of Sch</th><th>Total Sch kms</th><th colspan='2'>Total Can Kms</th><th>Total Eff Kms</th><th>Cancellation Name</th><th colspan='3'>Revenue</th><th>EPKM</th>" +
	                        "</tr><tr><th></th><th></th><th></th><th>N/O,GS,D/O (I&II Shift),NS.</th><th></th><th></th><th>No OfTrips</th><th>Total Kms</th><th></th><th></th><th>Total Cr Revenue</th><th>Dedicated/Chartered</th><th>Total Revenue</th><th></th></tr></thead><tbody>";
				 
				
			System.out.println("Enter into 1st");
				    
//				    String path = realpath + filePath + fileName;
			 
//				    String depotname="";
//			        String depotname=getDepotNames(depotid);
//			        int i=0;
//			        while(rs.next()){
//						System.out.println("Enter into 2nd");
//
//			        	i++;
//			        	regionTypeAjaxString +="<tr>";
//			        	
//			        	regionTypeAjaxString +="<td align='right'>"+ i +"</td>";
//			        	regionTypeAjaxString +="<td align='right'>"+rs.getString("generated_Date").toString()+"</td>";
//			        	regionTypeAjaxString +="<td align='right'>"+depotname+"</td>";
//			        	regionTypeAjaxString +="<td align='right'>"+rs.getString("TOKEN").toString()+"</td>";
//			        	regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_number").toString()+"</td>";
//			        	regionTypeAjaxString +="<td align='right'>"+rs.getString("shift_type_name").toString()+"</td>";
//			        	regionTypeAjaxString +="<td align='right'>"+rs.getString("route_number").toString()+"</td>";
//						System.out.println("Enter into 3rd");
//
//			        	regionTypeAjaxString +="<td align='right'>"+rs.getString("bus_stop_code1").toString()+"</td>";
//			        	if(rs.getString("bus_stop_code")=="" || rs.getString("bus_stop_code")==null){
//			        		regionTypeAjaxString +="<td align='right'></td>";
//			        	}else{
//			        	regionTypeAjaxString +="<td align='right'>"+rs.getString("bus_stop_code").toString()+"</td>";
//			        	}
////						regionTypeAjaxString +="<td align='right'>Operated</td>";
//						double effkms=Double.parseDouble(rs.getString("dista").toString())-Double.parseDouble(rs.getString("cancelkm").toString());
//						System.out.println("effkms=="+effkms);
//						String effkm=String.format("%.2f", effkms);
//						System.out.println("effkms=="+effkms+"effkm===="+effkm);
//						regionTypeAjaxString +="<td align='right'>"+rs.getString("dista").toString()+"</td>";
//						regionTypeAjaxString +="<td align='right'>"+rs.getString("ctrips").toString()+"</td>";
//						regionTypeAjaxString +="<td align='right'>"+rs.getString("cancelkm").toString()+"</td>";
//						regionTypeAjaxString +="<td align='right'>"+effkm+"</td>";
//						regionTypeAjaxString +="<td align='right'>"+rs.getString("cancellation_name").toString()+"</td>";
////						regionTypeAjaxString +="<td align='right'>"+rs.getString("token").toString()+"</td>";
//						double cr_revenue=Double.parseDouble(rs.getString("ticketrevenue").toString());
//						double dedicated=0.0;
//						double total=cr_revenue+dedicated;
//						System.out.println("Enter into 4th");
//
//						regionTypeAjaxString +="<td align='right'>"+cr_revenue+"</td>";
//						regionTypeAjaxString +="<td align='right'>"+dedicated+"</td>";
////						regionTypeAjaxString +="<td align='right'>"+list.get("route_number").toString()+"</td>";
//						regionTypeAjaxString +="<td align='right'>"+total+"</td>";
//						if(total==0.0){
//							epkm=0.0;
//						}else{
//						 epkm=effkms/total;
//						}
//						 System.out.println("epkm===="+epkm);
//						 System.out.println("double : " + String.format("%.2f", epkm));
//		                String epkms=String.format("%.2f", epkm);
//						regionTypeAjaxString +="<td align='right'>"+epkms+"</td>";
//						System.out.println("Enter into 5th");
//
//						regionTypeAjaxString +="</tr>";
//
//			        	
//			        	
//			        }
			        
			        
			        
	                  
					 regionTypeAjaxString += "</table></div> </div>  ";

					 
					 
			 
			
//			 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
//			 System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			
			
			
				
//				FileOutputStream FOS = new FileOutputStream(path);
//				PrintWriter PW = new PrintWriter(FOS);
//				
//	         	System.out.println(str);
//			String p=str;
//			System.out.println("realpath="+path);
//			System.out.println("string..@@"+p);
//
//			PW.println(p);
//			PW.close();
				out = response.getWriter();
				out.print(regionTypeAjaxString);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// connection.close();
           try{
            if (rs != null) {
            	rs.close();
            }
            
            if (stmt != null) {
            	stmt.close();
            }

            if (connection != null) {
                connection.close();
            }
            if(session1 !=null){
            	 session1.close();
             }
           }catch(Exception e){
        	   e.printStackTrace();
           }
		}
		
		
		
		return null;
		
		
	}
	
public String getDepotNames(String depot_id){
		
		String depotname="";
		Session session =null;
		try{
		String sql = " select org_name from org_chart where deleted_status=0 and org_chart_id="+depot_id+" and org_type_id=3  order by org_name";

		// sql += " order by " + COL_NAME + " " + DIR;
		Query query = HibernateUtil.getSession("").createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				depotname=rs.get("org_name").toString();
			}
		}
		HibernateUtil.closeSession();
		
	
		}catch(Exception e){
			e.printStackTrace();
		}
	
	return depotname;
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
