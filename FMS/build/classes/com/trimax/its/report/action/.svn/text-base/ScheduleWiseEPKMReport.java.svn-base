package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.hibernate.transform.Transformers;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;


public class ScheduleWiseEPKMReport extends ActionSupport {

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
	
	
	public String getScheduleWiseReport(){
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
			String scheduleno=req.getParameter("scheduleno");

			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
		    
		  
			Session session1 = null;
			Transaction transaction = null;
			 double epkm=0.0;
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 
			// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotid+"' AND division_id = '"+divisionid+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotid+"'";
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
			 
			 
			 String sql=" SELECT wd.generated_Date as generated_Date,s.schedule_number as schedule_number,if(s.schedule_type='3',shift_type_name,schedule_type_name) schtypename,r.route_name as route_name,"+
                     " r.route_number route_number,license_number,service_type_name, "+
                     "  a.dista as dista,ifnull(gl.canceled_trips,0) ctrips,ifnull(gl.cancel_km,0)cancelkm,ifnull(cancellation_name,'')cancellation_name, "+
                     "  ifnull(gl1.cancel_type,'') planedcancel,e.TOKEN AS token, "+
                     "  (ifnull(block.manualpassenger,0)+sum(ifnull(px_total_amount,0))) ticketrevenue,ifnull(block.manualdailypass,0) manualdailypass, "+
                     "  ifnull(block.manualluggage,0) manualluggage FROM Waybill_Details wd "+
                     " LEFT JOIN (SELECT a1.waybill_cwa_block_master_id, sum(if(ticket_type_manual_id IN (1), value, 0)) AS manualpassenger, "+
                     " sum(if(ticket_type_manual_id=2, value, 0)) AS manualdailypass,sum(if(ticket_type_manual_id IN (4), value, 0)) AS manualluggage "+ 
                     "  FROM waybill_cwa_block_master a1 INNER JOIN Waybill_cwa_receipt_details a2 "+
                     " ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id  GROUP BY a1.waybill_cwa_block_master_id) block "+
                     "  ON block.waybill_cwa_block_master_id = wd.param2 INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No "+
                     "  INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id "+
                     " inner join route r on r.route_id=ff.route_id  "+
                     "  LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id  AND ticket_printed_flag ='Y' AND fare_type NOT IN ('NINC')  "+
                     " LEFT JOIN gen_logsheet gl ON gl.waybill_id=wd.waybil_Id left join cancellation_cause cc on cc.cancellation_id=gl.cause_cancel_id "+
                     " left JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id "+
                     " INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type  LEFT JOIN shift_type STYPE "+
                     "  ON STYPE.shift_type_id=wd.Shift_Type  LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id  "+
                     "  inner join vehicle v on v.vehicle_id=wd.Vehicle_No LEFT JOIN gen_logsheet gl1 ON gl1.waybill_id=wd.waybil_Id and gl1.cancel_type='Planned' "+
                     "  inner join service_type sty on sty.service_type_id=wd.Service_Type left join (SELECT ifnull(sum(distance),'0.0')  as dista,form_four_id, "+
                     " max(list_item_number) trips FROM `schedule_details` WHERE  `schedule_number` = '"+scheduleno+"' group by form_four_id)a "+
                     " on a.form_four_id=wd.schedule_No left join (SELECT ifnull(sum(distance),'0.0')  as cahrte,form_four_id FROM `schedule_details` "+
                     " WHERE  `schedule_number` = '"+scheduleno+"' and `trip_type` IN (19,20) group by form_four_id)b on b.form_four_id=wd.schedule_No "+
                     " left join scheduletargetamount st on st.scheduleid=ff.schedule_number_id and week_day=dayname(wd.generated_Date)  "+
                     " left join (SELECT ifnull(sum(distance),'0.0')  as sdead,form_four_id FROM `schedule_details` WHERE  `schedule_number` = '"+scheduleno+"' "+
                     " and `trip_type`=3 group by form_four_id)c on c.form_four_id=wd.schedule_No left join causewise_dead_km cdkm "+
                     " on cdkm.gen_logsheet_id=gl.gen_logsheet_id WHERE wd.generated_Date BETWEEN '"+date1+"' and '"+date2+"' AND s.schedule_id='"+scheduleno+"' "+
                     " and  (wd.status='AUDITED' OR wd.status='CLOSED') group by wd.waybil_Id order by wd.generated_Date,CAST(e.token as UNSIGNED)";
			
			 
//			 System.out.println("query"+sql);
				
			 rs = stmt.executeQuery(sql); 
			 
			 String depotname=getDepotNames(depotid);
			 
//			 String sql="";
//			 Query query = session1.createSQLQuery(sql).addScalar("Date").addScalar("ETIMServiceTax").addScalar("BagServiceTax").addScalar("PassServiceTax");
//
//			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String,Object>> aliasToValueMapList = query.list();
				
				
				
				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depotname+" </br>Schedule Wise EPKM Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		        
		      
		        
		       


				regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Date</th><th>Depot:</th><th>Sch No</th><th>Status (N/O, GS, D/O (I&II Shift), NS)</th><th>Route</th><th>From </th><th>To</th><th>Operated Days</th><th>Sch kms</th><th colspan='2'>Can Kms</th><th>Eff Kms</th><th>Cancellation Name</th><th>Cr Token no</th><th colspan='3'>Revenue</th><th>EPKM</th>" +
	                        "</tr><tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>Trips</th><th>Kms</th><th></th><th></th><th></th><th>Cr Remitted</th><th>Dedicated/Chartered</th><th>Total Revenue</th><th></th></tr></thead><tbody>";
				 
				
				
	                  
			        int i=0;
			        while(rs.next()){
			        	i++;
			        	regionTypeAjaxString +="<tr>";
			        	
			        	regionTypeAjaxString +="<td align='right'>"+ i +"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("generated_Date").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+depotname+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_number").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("schtypename").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("route_number").toString()+"</td>";
						
						
						
						String routename = rs.getString("route_name").toString();
						String stringsplit[] = routename.split("To");
						regionTypeAjaxString +="<td align='right'>"+ stringsplit[0] +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ stringsplit[1] +"</td>";
						regionTypeAjaxString +="<td align='right'>Operated</td>";
						double effkms=Double.parseDouble(rs.getString("dista").toString())-Double.parseDouble(rs.getString("cancelkm").toString());
//						System.out.println("effkms=="+effkms);
						regionTypeAjaxString +="<td align='right'>"+rs.getString("dista").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("ctrips").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("cancelkm").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+effkms+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("cancellation_name").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+rs.getString("token").toString()+"</td>";
						double cr_revenue=Double.parseDouble(rs.getString("ticketrevenue").toString())+Double.parseDouble(rs.getString("manualdailypass").toString())+Double.parseDouble(rs.getString("manualluggage").toString());
						double dedicated=0.0;
						double total=cr_revenue+dedicated;
						regionTypeAjaxString +="<td align='right'>"+cr_revenue+"</td>";
						regionTypeAjaxString +="<td align='right'>"+dedicated+"</td>";
//						regionTypeAjaxString +="<td align='right'>"+list.get("route_number").toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+total+"</td>";
						if(total==0.0){
							epkm=0.0;
						}else{
						 epkm=total/effkms;
						}
		                String epkms=String.format("%.2f", epkm);
						regionTypeAjaxString +="<td align='right'>"+epkms+"</td>";

						regionTypeAjaxString +="</tr>";




			        }

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
