package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.trimax.its.util.HibernateUtil;

public class RouteWisePassengerCountReport extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";
	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double earnAmmount=0.0;
	double dedtotal=0.0;
	int subtotalValues=0;
	double totalammount=0.0;
	public String startdate;
	String regionTypeAjaxString = "";
	public String enddate;
	public String routeno;
	
	
	public String execute() {
		
		return "success";
	}
	
	public String RouteWisePassengerCount(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Startdate==="+startdate);
//		System.out.println("Enddate==="+enddate);
//		System.out.println("RouteNo==="+routeno);
		Common common=new Common();
		 SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm ");
		    String startdate1 = common.getDateTimeFromPickerToDB(startdate);
		    String enddate1 = common.getDateTimeFromPickerToDB(enddate);
		
		String dates[]=startdate1.split(" ");
		String start_date=dates[0];
		String start_time=dates[1];
		String dates1[]=enddate1.split(" ");
		String end_date=dates1[0];
		String end_time=dates1[1];
		String routes[]=routeno.split(":");
		String route_no=routes[0];
		String route_direction=routes[1];
		String route_number="";
		if(route_direction=="DOWN" || route_direction.equalsIgnoreCase("DOWN")){
			route_number=route_no+"DN";
		}else{
			route_number=route_no+"UP";
		}
		
//		System.out.println("route_no=="+route_no+"  route_direction=="+route_direction+"  route_number==="+route_number);
//		System.out.println("start_date=="+start_date+"  start_time=="+start_time+"  end_date==="+end_date+"  end_time=="+end_time);
		
//		int year=Integer.parseInt(dates[1]);
//		int select_month=Integer.parseInt(month);
//		int curr_month=Calendar.getInstance().get(Calendar.MONTH);
//		int curr_month1=curr_month+1;
//		System.out.println("Current month====="+curr_month1);
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		

		
//		Common common = new Common();
		String orgname=dao.getOrgName();
//		String depot=dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
	    String cur_date = sdf1.format(ss1);
	    String date1="";
	   
	    
	   
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql=" SELECT schedule_no,sum(px_count) passenger_count,sum(px_total_amount) revenue FROM bmtcGprs.etim_gprs_ticket WHERE `route_no` = '"+route_number+"' AND `ticket_date` between '"+start_date+"' and '"+end_date+"'  AND `ticket_time` between '"+start_time+"' and '"+end_time+"' "+
				   " group by schedule_no " +
				   " union " +
				   " SELECT schedule_no,sum(px_count) passenger_count,sum(px_total_amount) revenue FROM bmtcGprs.etim_gprs_ticket060717 WHERE `route_no` = '"+route_number+"' AND `ticket_date` between '"+start_date+"' and '"+end_date+"'  AND `ticket_time` between '"+start_time+"' and '"+end_time+"' "+
				   " group by schedule_no "+
				  " union " +
				   " SELECT schedule_no,sum(px_count) passenger_count,sum(px_total_amount) revenue FROM bmtcGprs.etim_gprs_ticket150318 WHERE `route_no` = '"+route_number+"' AND `ticket_date` between '"+start_date+"' and '"+end_date+"'  AND `ticket_time` between '"+start_time+"' and '"+end_time+"' "+
				   " group by schedule_no ";
		 Query query = session1.createSQLQuery(sql).addScalar("schedule_no").addScalar("passenger_count",Hibernate.INTEGER).addScalar("revenue");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
	

		    
			int total_count=0;
			double total_rev=0.0;
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>ROUTE WISE PASSENGER COUNT REPORT "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

//	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Schedule No</th><th>Passenger Count</th>" +
//					"</tr></thead><tbody>";
			 
//System.out.println("aliasToValueMapList.size()=="+aliasToValueMapList.size());
	        
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='3' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{

	        	  regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
	  			regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Schedule No</th><th>Passenger Count</th><th>Revenue</th>" +
	  					"</tr></thead><tbody>";
              
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
			
 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("schedule_no").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("passenger_count").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("revenue").toString() +"</td>";
//			
			total_count+=Integer.parseInt(list.get("passenger_count").toString());
			total_rev+=Double.parseDouble(list.get("revenue").toString());
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td colspan='2'><center ><b>Total</b></center></td><td align='right'><b>"+ total_count+"</td><td align='right'><b>"+ total_rev+"</td></tr>" +"\n";																	  
				
				
		 
				 
	          }
	        regionTypeAjaxString += "</table></div> </div>  ";
	
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
//			System.out.println("op"+regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}

}
