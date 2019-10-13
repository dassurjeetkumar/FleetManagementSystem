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

public class SpecialPassTicketReport extends ActionSupport {
	
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
	
	public List<Schedule> getScheduledata() {
		List<Schedule> schedulelist = null;
		Session session = null;
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try{
			int depotId = Integer.parseInt(req.getParameter("val"));
			
			List<String> l1 = dao.getDepotWiseSchdNoId(depotId);
    		List<String> l2 = dao.getDepotWiseSchdNoName(depotId);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='0'>------select------</option>";
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
	
	
	public String getSpecialPassReport(){
		Connection connection=null;
		Statement stmt=null;
		
		ResultSet rs=null;
		try {
			  HttpServletRequest req = ServletActionContext.getRequest();
			CollectionReportDAO dao=new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String date2=dao.getDateFromPickerDate(enddate);
			Common common = new Common();
			String orgname=dao.getOrgName();
//			String depot=dao.getDepotName();
			
			String depotid=req.getParameter("depot");
			String divisionid=req.getParameter("division");
			
		
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
		    
		  
			Session session1 = null;
			Transaction transaction = null;
			 int total=0;
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 
			 //String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"' AND division_id = '"+divisionid+"'";
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
			 
			 
			 String sql="SELECT count(*) as count FROM `ticket` WHERE `ticket_sub_type_short_code` = 'ST' AND "+

                    " ticket_date between '"+date1+"' AND '"+date2+"' AND `px_total_amount` = '38' and ticket_printed_flag='Y' ";
			 
//			 System.out.println("query"+sql);
				
			 rs = stmt.executeQuery(sql); 
			 
			 String depotname=getDepotNames(depotid);
			 
//			 String sql="";
//			 Query query = session1.createSQLQuery(sql).addScalar("Date").addScalar("ETIMServiceTax").addScalar("BagServiceTax").addScalar("PassServiceTax");
//
//			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String,Object>> aliasToValueMapList = query.list();
				
				
				
				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depotname+" </br>Rs. 35/- Special Pass Ticket Statement Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		        
		      
		        
		       


				regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Depot No:</th><th>No. of Rs.35/- Special pass ticket</th>" +
	                        "</tr></thead><tbody>";
				 
				
				
	                  
			        int i=0;
			        while(rs.next()){
			        	i++;
			        	regionTypeAjaxString +="<tr>";
			        	
			        	regionTypeAjaxString +="<td align='right'>"+ i +"</td>";
						regionTypeAjaxString +="<td align='right'>"+depotname+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("count").toString()+"</td>";
						int count=Integer.parseInt(rs.getString("count").toString());
						 total=total+count;
						

						regionTypeAjaxString +="</tr>";




			        }
			        
					regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ total+"</td></tr>" +"\n";  

			        

					 regionTypeAjaxString += "</table></div> </div>  ";
			 
					 
					 
			 
			
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
