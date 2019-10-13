package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class AxisSettlementDetailReport {
	
	

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
	public String enddate;
	String regionTypeAjaxString = "";
public String execute() {
		
		return "success";
	}

public String getAxisSettlementReport(){
	
	try{
		
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Startdate==="+startdate);
//		System.out.println("Enddate==="+enddate);
//		System.out.println("RouteNo==="+routeno);
		Common common=new Common();
		System.out.println(startdate+"srrrrrr");
		String startdate1=dao.getDateFromPickerDate(startdate);
		String enddate2=dao.getDateFromPickerDate(enddate);
	    System.out.println("startdate1===="+startdate1);
	    System.out.println("end date============"+enddate2);
	    Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	   
	   
	    
	   
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String gprsvalue=dao.getDataFromGprs(startdate1);
		 String axiesvalue=dao.getDataFromAxies(startdate1);
		 System.out.println("gprsvalue==="+gprsvalue+"  axiesvalue=="+axiesvalue);
		 
		 String sql="";
		 
		
		 sql="select org_name,waybill_no,schedule_no,route_no,trip_no,etim_no,ticket_date,ticket_time,its_ticket,axis_waybill,axis_ticket,"+
				    "its_transactionAmt,its_convinence,axis_transactionAmt,axis_convinence,settlement_status,its_settlement_date,settlement_date from "+
				 "(SELECT org_name,eg.waybill_no,eg.schedule_no,eg.route_no,eg.trip_no,eg.etim_no,eg.ticket_date,eg.ticket_time,eg.ticket_no its_ticket,"+
				 "ifnull(ar.waybill_no,'') axis_waybill,ifnull(ar.ticket_id,'') axis_ticket,"+
				 "sum(round((eg.px_total_amount+eg.lugg_total_amount),4)) its_transactionAmt,"+
				 "eg.convinence_fee its_convinence,ifnull(ar.transaction_amount-ar.convenience_fee,0) axis_transactionAmt,ifnull(ar.convenience_fee,0) axis_convinence,"+  
				 "if(sum(round((eg.px_total_amount+eg.lugg_total_amount),2)) =ifnull(ar.transaction_amount-ar.convenience_fee,0),'','NO') settlement_status,ifnull(inserted_tm,'') as its_settlement_date,ifnull(ar.settlement_date,'') settlement_date "+
				 "FROM bmtcGprs.etim_gprs_ticket eg left join bmtcGprs.axis_reconcillation ar on eg.waybill_no=ar.waybill_no and eg.ticket_no=ar.ticket_id "+  
				 "LEFT join bmtcGprs.etim_settlement es on eg.waybill_no=es.waybill_no and es.settlement_status='Y' "+ 
				 "inner join its.org_chart oc on eg.depot_id = oc.org_chart_id "+  
				 "WHERE eg.depot_id!=42 and "+ 
				 "eg.ticket_date between '"+startdate1+"' AND '"+enddate2+"'  AND `payment_mode` = '1' group by eg.ticket_no,eg.waybill_no order by org_name, "+
				 "date(ticket_date),eg.ticket_no) a ";
	
		 
		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("waybill_no").addScalar("schedule_no").addScalar("route_no").addScalar("trip_no").addScalar("etim_no").addScalar("ticket_date").addScalar("ticket_time").addScalar("its_ticket").addScalar("axis_waybill").
				 addScalar("axis_ticket").addScalar("its_transactionAmt").addScalar("its_convinence").addScalar("axis_transactionAmt").addScalar("axis_convinence").addScalar("settlement_status").addScalar("its_settlement_date").addScalar("settlement_date");
////		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
	

		    
			double total_its_transactionAmt=0.0;
			double total_its_convinence=0.0;
			double total_axis_transactionAmt=0.0;
			double total_axis_convinence=0.0;
			String total_its_transactionAmt1="";
			String total_its_convinence1="";
			String total_axis_transactionAmt1="";
			String total_axis_convinence1="";
			//regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'> </br>AXIES SETTLEMENT REPORT </h4></div>";
			//regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
			regionTypeAjaxString +="<div id='header' style='display: none;'><div align='center'> </br>Axis Settlement Detail Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

//	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Org Name</th><th>Waybill No</th><th>Schedule No</th><th>Route No</th><th>Trip No</th><th>Etm No</th><th>Ticket No</th><th>Ticket Time</th><th>Its Ticket</th><th>Axis_Waybill</th><th>Axis_Ticket</th><th>ITS_Transaction_Amt</th><th>ITS_Convinence</th><th>Axis_Transaction_Amt</th><th>Axis_Convinence</th><th>Settlement</th>" +
//					"</tr></thead><tbody>";
			 
//System.out.println("aliasToValueMapList.size()=="+aliasToValueMapList.size());
	        
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='16' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{

	        	  regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
	  			regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Org Name</th><th>Waybill No</th><th>Schedule No</th><th>Route No</th><th>Trip No</th><th>Etm No</th><th>Ticket Date</th><th>Ticket Time</th><th>Its Ticket</th><th>Axis_Waybill</th><th>Axis_Ticket</th><th>ITS_Transaction_Amt</th><th>ITS_Convinence</th><th>Axis_Transaction_Amt</th><th>Axis_Convinence</th><th>Settlement_Status</th><th> Its Settlement Date</th><th> Axis Settlement Date</th>" +
	  					"</tr></thead><tbody>";
              
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
			
 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("waybill_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("schedule_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("route_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("trip_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("etim_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("ticket_date").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("ticket_time").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("its_ticket").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("axis_waybill").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("axis_ticket").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("its_transactionAmt").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("its_convinence").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("axis_transactionAmt").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("axis_convinence").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("settlement_status").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("its_settlement_date").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("settlement_date").toString() +"</td>";

			
//			
//			total_count+=Integer.parseInt(list.get("passenger_count").toString());
//			total_rev+=Double.parseDouble(list.get("revenue").toString());
			total_its_transactionAmt+=Double.parseDouble(list.get("its_transactionAmt").toString());
			total_its_convinence+=Double.parseDouble(list.get("its_convinence").toString());
			total_axis_transactionAmt+=Double.parseDouble(list.get("axis_transactionAmt").toString());
			total_axis_convinence+=Double.parseDouble(list.get("axis_convinence").toString());
			total_its_transactionAmt1=String.format("%.2f", total_its_transactionAmt);
			total_its_convinence1=String.format("%.2f", total_its_convinence);
			total_axis_transactionAmt1=String.format("%.2f",total_axis_transactionAmt);
			 total_axis_convinence1=String.format("%.2f",total_axis_convinence);
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td colspan='12'><center ><b>Total</b></center></td><td align='right'><b>"+ total_its_transactionAmt1+"</td><td align='right'><b>"+ total_its_convinence1+"</td><td align='right'><b>"+ total_axis_transactionAmt1+"</td><td align='right'><b>"+ total_axis_convinence1+"</td></tr>" +"\n";																	  
				
				
		 
				 
	          }
	        regionTypeAjaxString += "</table></div> </div>  ";
//	
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	
	return null;
}
}
