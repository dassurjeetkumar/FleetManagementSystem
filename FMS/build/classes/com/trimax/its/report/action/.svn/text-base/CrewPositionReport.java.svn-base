package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import org.json.simple.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CrewPositionReport extends ActionSupport{

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
	public String singledate;
	
	 DecimalFormat df1 = new DecimalFormat("0.00");
	 public String startdate;
	    public String enddate;
	    private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;
		private String depotlist1;
		private String divisionlist1;
		private String date1;
		 public String getDate1() {
			return date1;
		}


		public void setDate1(String date1) {
			this.date1 = date1;
		}

		private List<Schedule> schedulelist;
	    private String schedule;
	    
	    public String depottype;
	    public String divisiontype;
	public String getDivisiontype() {
			return divisiontype;
		}


		public void setDivisiontype(String divisiontype) {
			this.divisiontype = divisiontype;
		}


	public String getDepottype() {
			return depottype;
		}


		public void setDepottype(String depottype) {
			this.depottype = depottype;
		}


	public List<Schedule> getSchedulelist() {
			return schedulelist;
		}


		public void setSchedulelist(List<Schedule> schedulelist) {
			this.schedulelist = schedulelist;
		}


		public String getSchedule() {
			return schedule;
		}


		public void setSchedule(String schedule) {
			this.schedule = schedule;
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

	String regionTypeAjaxString = "";
	
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
	
	public String getCentralOfficeData(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
//		String depot=dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
	    String cur_date = sdf1.format(ss1);
	    
	   
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		
		 
		 String sql="";
		 
//		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////		  
//		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String,Object>> aliasToValueMapList = query.list();
//			double Totalvalues=0.0;

		 List<Map<String,Object>> aliasToValueMapList=null;
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br> CENTRAL OFFICES : BANGALORE 27 ";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	        
	       

	        regionTypeAjaxString += "<tr><th colspan='6' align='center'>BMTC Operation Details</th><th colspan='5' align='center'>DRIVER</th><th colspan='5' align='center'>CONDUCTOR</th><th>DC</th><th></th><th></th><th></th></tr>" +
	        		"<tr><th>Depot</th><th>Total</th><th colspan='4' align='center'>TYPE OF SCH'S</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>working</th><th>Actual</th><th>Actual</th><th>Actual</th></tr>" +
	        		"<tr><th>No</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th></th><th></th><th></th><th></th><th>Deficit</th><th></th><th></th><th></th><th></th><th>Deficit</th><th></th><th>Sanction</th><th>Working</th><th>Vacancy</th></tr>";
//			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//					"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//					"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//					"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//					"</thead><tbody>";
			 

	        if(aliasToValueMapList==null){
		       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
		            regionTypeAjaxString += "<tr>";
		            regionTypeAjaxString += "<td colspan='20' align='center'><b>No Result Found</b></td>";
		           
		            regionTypeAjaxString += "</tr>";
		       	
		       }else{
			    
	
//		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//		    	 regionTypeAjaxString +="<tr>";
//			Map<String,Object> list = aliasToValueMapList.get(i);
//     		int j=i+1;
//			
////     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//			

//			

			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			

//			
//			   regionTypeAjaxString +="</tr>";
//		}
//				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//						"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//								"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////				
				 regionTypeAjaxString += "</table></div> </div>  ";
				 
		       }
		 
				 System.out.println("Reached Here=========>");
				 
		 
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public String getNorthDivisionData(){
		
		System.out.println("HI I am");
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
//		String depot=dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
	    String cur_date = sdf1.format(ss1);
	    
	   
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="";
		 
//		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////		  
//		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String,Object>> aliasToValueMapList = query.list();
//			double Totalvalues=0.0;

		 List<Map<String,Object>> aliasToValueMapList=null;
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	        
	       

	        regionTypeAjaxString += "<tr><th></th><th></th><th colspan='4' align='center'></th><th colspan='5' align='center'>DRIVER</th><th colspan='6' align='center'>CONDUCTOR</th><th></th><th></th><th></th></tr>" +
	        		"<tr><th>Depot</th><th>SCHS</th><th colspan='4' align='center'>TYPE OF SCH'S</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>DC</th><th>Actual</th><th>Actual</th><th>Actual</th></tr>" +
	        		"<tr><th>No</th><th></th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th></th><th></th><th></th><th></th><th>Deficit</th><th></th><th></th><th></th><th></th><th>Deficit</th><th>working</th><th>Sanction</th><th>Working</th><th>Vacancy</th></tr>";
//			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//					"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//					"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//					"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//					"</thead><tbody>";
			 
	        if(aliasToValueMapList==null){
		       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
		            regionTypeAjaxString += "<tr>";
		            regionTypeAjaxString += "<td colspan='20' align='center'><b>No Result Found</b></td>";
		           
		            regionTypeAjaxString += "</tr>";
		       	
		       }else{

//		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//		    	 regionTypeAjaxString +="<tr>";
//			Map<String,Object> list = aliasToValueMapList.get(i);
//     		int j=i+1;
//			
////     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//			

//			

			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			

//			
//			   regionTypeAjaxString +="</tr>";
//		}
//				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//						"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//								"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////				
				 regionTypeAjaxString += "</table></div> </div>  ";
				 
		       }
		 
				 
		 
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}
	
public String getSouthDivisionData(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
//		String depot=dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
	    String cur_date = sdf1.format(ss1);
	    
	   
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="";
		 
//		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////		  
//		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String,Object>> aliasToValueMapList = query.list();
//			double Totalvalues=0.0;

		 List<Map<String,Object>> aliasToValueMapList=null;
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

			regionTypeAjaxString += "<tr><th></th><th></th><th colspan='4' align='center'></th><th colspan='5' align='center'>DRIVER</th><th colspan='6' align='center'>CONDUCTOR</th><th></th><th></th><th></th></tr>" +
	        		"<tr><th>Depot</th><th>SCHS</th><th colspan='4' align='center'>TYPE OF SCH'S</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>DC</th><th>Actual</th><th>Actual</th><th>Actual</th></tr>" +
	        		"<tr><th>No</th><th></th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th></th><th></th><th></th><th></th><th>Deficit</th><th></th><th></th><th></th><th></th><th>Deficit</th><th>working</th><th>Sanction</th><th>Working</th><th>Vacancy</th></tr>";
//			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//					"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//					"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//					"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//					"</thead><tbody>";
			 

			if(aliasToValueMapList==null){
		       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
		            regionTypeAjaxString += "<tr>";
		            regionTypeAjaxString += "<td colspan='20' align='center'><b>No Result Found</b></td>";
		           
		            regionTypeAjaxString += "</tr>";
		       	
		       }else{
			
			  
                  
//		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//		    	 regionTypeAjaxString +="<tr>";
//			Map<String,Object> list = aliasToValueMapList.get(i);
//     		int j=i+1;
//			
////     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//			

//			

			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			

//			
//			   regionTypeAjaxString +="</tr>";
//		}

//				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//						"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//								"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////				
				 regionTypeAjaxString += "</table></div> </div>  ";
				 
		       }
		

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}

public String getSouthDivisionSummaryData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;

	 List<Map<String,Object>> aliasToValueMapList=null;
	 
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

        
       

        regionTypeAjaxString += "<tr><th>D/O</th><th>N/O</th><th>G/S</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
        if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='3' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{

        
              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
	       }
			 

	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}




	
public String getEastDivisionData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;
 
	 List<Map<String,Object>> aliasToValueMapList=null;
	  
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

        
       

		regionTypeAjaxString += "<tr><th></th><th></th><th colspan='4' align='center'></th><th colspan='5' align='center'>DRIVER</th><th colspan='6' align='center'>CONDUCTOR</th><th></th><th></th><th></th></tr>" +
        		"<tr><th>Depot</th><th>SCHS</th><th colspan='4' align='center'>TYPE OF SCH'S</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>DC</th><th>Actual</th><th>Actual</th><th>Actual</th></tr>" +
        		"<tr><th>No</th><th></th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th></th><th></th><th></th><th></th><th>Deficit</th><th></th><th></th><th></th><th></th><th>Deficit</th><th>working</th><th>Sanction</th><th>Working</th><th>Vacancy</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 

		if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='3' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{
		
              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
	       }
	 
			 System.out.println("Reached Here=========>");
			 
	 
	
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}	

public String getEastDivisionSummaryData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;
	 List<Map<String,Object>> aliasToValueMapList =null;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

       

        regionTypeAjaxString += "<tr><th>D/O</th><th>N/O</th><th>G/S</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
        if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='3' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{

              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
	       }
			 
			 
	 

	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}





public String getWestDivisionData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;
	 List<Map<String,Object>> aliasToValueMapList=null;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

       

		regionTypeAjaxString += "<tr><th></th><th></th><th colspan='4' align='center'></th><th colspan='5' align='center'>DRIVER</th><th colspan='6' align='center'>CONDUCTOR</th><th></th><th></th><th></th></tr>" +
        		"<tr><th>Depot</th><th>SCHS</th><th colspan='4' align='center'>TYPE OF SCH'S</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>DC</th><th>Actual</th><th>Actual</th><th>Actual</th></tr>" +
        		"<tr><th>No</th><th></th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th></th><th></th><th></th><th></th><th>Deficit</th><th></th><th></th><th></th><th></th><th>Deficit</th><th>working</th><th>Sanction</th><th>Working</th><th>Vacancy</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
		if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='20' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{
		
              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
	       }
	 
			 
	 

	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}

public String getWestDivisionSummaryData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;
	 List<Map<String,Object>> aliasToValueMapList=null;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

        
       

        regionTypeAjaxString += "<tr><th>D/O</th><th>N/O</th><th>G/S</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
        if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='3' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{
              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
	       }
	
			 

	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}



public String getVolvoDivisionData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;
	 List<Map<String,Object>> aliasToValueMapList=null;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

        
       

		regionTypeAjaxString += "<tr><th></th><th></th><th colspan='4' align='center'></th><th colspan='5' align='center'>DRIVER</th><th colspan='6' align='center'>CONDUCTOR</th><th></th><th></th><th></th></tr>" +
        		"<tr><th>Depot</th><th>SCHS</th><th colspan='4' align='center'>TYPE OF SCH'S</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>Regular</th><th>Trainee</th><th>Sanctioned</th><th>Working</th><th>Excess/</th><th>DC</th><th>Actual</th><th>Actual</th><th>Actual</th></tr>" +
        		"<tr><th>No</th><th></th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th></th><th></th><th></th><th></th><th>Deficit</th><th></th><th></th><th></th><th></th><th>Deficit</th><th>working</th><th>Sanction</th><th>Working</th><th>Vacancy</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
		if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='20' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{
		
              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
	       }
	 

	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}

public String getVolvoDivisionSummaryData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;
	 List<Map<String,Object>> aliasToValueMapList=null;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
        regionTypeAjaxString += "<tr><th>D/O</th><th>N/O</th><th>G/S</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
        if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='3' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{

              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
	       }
	
			 	 
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}



public String getSummaryDivisionData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
//	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
//	String depot=dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    
   
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="";
	 
//	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////	  
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;
	 List<Map<String,Object>> aliasToValueMapList=null;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        


        regionTypeAjaxString += "<tr><th colspan='6' align='center'>Over All Asch's</th><th colspan='5' align='center'>DRIVER</th><th colspan='4' align='center'>CONDUCTOR</th><th></th><th></th><th></th><th></th></tr>" +
        		"<tr><th>Division</th><th>Over all Sch's</th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th>Actual Requirement</th><th>38.33%</th><th>Sanction</th><th>Drivers Working</th><th>Actual vacancy</th><th>38.33%</th><th>Sanction</th><th>Working</th><th>Actual vacancy</th><th>D/C Working</th><th>Over All Sanction</th><th>Working</th><th>Over All Actual Vacancy</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 

        if(aliasToValueMapList==null){
	       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	            regionTypeAjaxString += "<tr>";
	            regionTypeAjaxString += "<td colspan='19' align='center'><b>No Result Found</b></td>";
	           
	            regionTypeAjaxString += "</tr>";
	       	
	       }else{
              
//	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//	    	 regionTypeAjaxString +="<tr>";
//		Map<String,Object> list = aliasToValueMapList.get(i);
// 		int j=i+1;
//		
//// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////		regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//		regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//		

//		

		
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		   regionTypeAjaxString +="</tr>";
//	}
//			regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//					"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//							"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////			
			 regionTypeAjaxString += "</table></div> </div>  ";
			 
	       }
	 
        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
		regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Depot</th><th>Day Out</th><th>Night Out</th><th>General Shift</th><th>Night Service</th><th>Total Schedule</th><th>Driver Working</th><th>Sanction</th><th>Diff.</th><th>Conductor  Working</th><th>Sanction</th><th>Diff.</th><th>Driver-Conductor  Working</th><th>Sanction</th><th>Diff.</th><th>Total Working</th>" +
        		"<th>Crew Sanc</th><th>Diff</th><th>Current Ratio</th><th>Required Ratio</th><th>Diff.</th></tr>";
	HttpServletResponse response = ServletActionContext.getResponse();
	PrintWriter out;
	
	
		out = response.getWriter();
		out.print(regionTypeAjaxString);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
	return null;
}


public String getCrewPositionData(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();

	Common common = new Common();
	String orgname=dao.getOrgName();

	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    String date1=common.getDateFromPicker(startdate);
    
    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
    String cur_date = sdf1.format(ss1);
    System.out.println("depotlist1==="+depotlist1);
   
    System.out.println("divisionlist1=="+divisionlist1);
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 String query1="";
	 if(depotlist1.equalsIgnoreCase("0") && divisionlist1.equalsIgnoreCase("0")){
		 query1="";
	 }
	 else if(depotlist1.equalsIgnoreCase("0") && !divisionlist1.equalsIgnoreCase("0")){
		 query1=" og.parent_id='"+divisionlist1+"' and ";
	 }else if(!depotlist1.equalsIgnoreCase("0") && divisionlist1.equalsIgnoreCase("0")){
		 query1="og.org_chart_id = '"+depotlist1+"' and ";
	 }else{
		 query1="og.parent_id='"+divisionlist1+"' and og.org_chart_id = '"+depotlist1+"' and ";
	 }
	 


//	 String sql="select c.org_name org_name,c.org_chart_id,b.org_chart_id,ifnull(a.dayout,0) dayout,ifnull(a.conductorcount,0) dayoutconductorcount," +
//	 		"ifnull(a.drivercount,0) dayoutdrivercount,ifnull(b.nightout,0) nightout,ifnull(b.conductorcount,0) nightoutconductorcount,ifnull(b.drivercount,0) nightoutdrivercount," +
//	 		"ifnull(c.genshift,0) genshift,ifnull(c.conductorcount,0) genshiftconductorcount,ifnull(c.drivercount,0) genshiftdrivercount,ifnull(d.conductorcount,0) nightserviceconductorcount," +
//	 		"ifnull(d.drivercount,0) nightservicedrivercount,ifnull(d.nightservice,0) nightservice,ifnull(e.driver,0) driver,ifnull(f.conductor,0) conductor,ifnull(g.ConductorDriver,0) ConductorDriver," +
//	 		"ifnull(spli.splitservice,0) splitservice,ifnull(spli.conductorcount,0) splitserviceconductorcount,ifnull(spli.drivercount,0) splitservicedrivercount,"+
////	 		"ifnull(((a.dayout*a.drivercount)+(b.nightout*b.drivercount)+(c.genshift*c.drivercount)+(d.nightservice*d.drivercount)),0) driversanction," +
////	 		"ifnull(((a.dayout*a.conductorcount)+(b.nightout*b.conductorcount)+(c.genshift*c.conductorcount)+(d.nightservice*d.conductorcount)),0) conductorsanction  " +
//            "ifnull((a.drivercount+b.drivercount+c.drivercount+d.drivercount),0) driversanction," +
//	        "ifnull((a.conductorcount+b.conductorcount+c.conductorcount+d.conductorcount),0) conductorsanction  " +
//	 		"from (select count(schedule_number) genshift,og.org_name org_name,og.org_chart_id org_chart_id,sum(conductor) conductorcount,sum(driver) drivercount " +
//	 		"from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id where  schedule_type='1' and status='NEW' " +
//	 		"and  "+query1+" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) c left join " +
//	 		"(select count(schedule_number) nightout,og.org_name,og.org_chart_id org_chart_id,sum(conductor) conductorcount," +
//	 		"sum(driver) drivercount from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//	 		"where  schedule_type='2' and status='NEW' and  "+query1+"  current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) b " +
//	 		"on b.org_name=c.org_name left join " +
//	 		"(select count(schedule_number) dayout,og.org_name,og.org_chart_id org_chart_id,sum(conductor) conductorcount," +
//	 		"sum(driver) drivercount from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//	 		"where  schedule_type='3' and status='NEW' and  "+query1+"   current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) a " +
//	 		"on a.org_name=c.org_name left join " +
//	 		"(select count(schedule_number) nightservice,og.org_name,og.org_chart_id org_chart_id,sum(conductor) conductorcount," +
//	 		"sum(driver) drivercount from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//	 		"where  schedule_type='4' and status='NEW' and  "+query1+"   current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) d " +
//	 		"on d.org_name=c.org_name left join " +
//	 		"(select count(schedule_number) splitservice,og.org_name org_name,og.org_chart_id org_chart_id,sum(conductor) conductorcount,sum(driver) drivercount " +
//	 		"from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id where  schedule_type='11' and status='NEW' " +
//	 		"and  "+query1+" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) spli on  spli.org_name=c.org_name left join"+
//	 		"(SELECT COUNT(EMPLOYEE_NAME) driver,org_name,org_chart_id from " +
//	 		"(select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` " +
//	 		"inner join   org_chart og on og.org_chart_id=employee.org_chart_id " +
//	 		"WHERE `EMPLOYEE_DESIGNATION` = 'driver' AND  "+query1+"   employee.STATUS = 'ACTIVE'  group by EMPLOYEE_ID) ab group by ab.org_chart_id) e on e.org_name=c.org_name " +
//	 		"left join (SELECT COUNT(EMPLOYEE_NAME) conductor,org_name ,org_chart_id " +
//	 		"from (select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` " +
//	 		"left join   org_chart og on og.org_chart_id=employee.org_chart_id " +
//	 		"WHERE `EMPLOYEE_DESIGNATION` = 'Conductor' AND  "+query1+"   employee.STATUS = 'ACTIVE'  ) ac group by ac.org_chart_id ) f " +
//	 		"on f.org_name=c.org_name left join " +
//	 		"(SELECT COUNT(EMPLOYEE_NAME) ConductorDriver,org_name,org_chart_id " +
//	 		"from (select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` inner join   org_chart og on og.org_chart_id=employee.org_chart_id " +
//	 		"WHERE `EMPLOYEE_DESIGNATION` = 'DriverConductor' AND  "+query1+"    employee.STATUS = 'ACTIVE'   group by EMPLOYEE_ID) ad " +
//	 		"group by ad.org_chart_id)  g on g.org_name=c.org_name ";
//	 
	 
//	 String sql="select c.org_name org_name,c.org_chart_id,b.org_chart_id,ifnull(a.dayout,0) dayout,ifnull(a.conductorcount,0) dayoutconductorcount," +
//		 		"ifnull(a.drivercount,0) dayoutdrivercount,ifnull(b.nightout,0) nightout,ifnull(b.conductorcount,0) nightoutconductorcount,ifnull(b.drivercount,0) nightoutdrivercount," +
//		 		"ifnull(c.genshift,0) genshift,ifnull(c.conductorcount,0) genshiftconductorcount,ifnull(c.drivercount,0) genshiftdrivercount,ifnull(d.conductorcount,0) nightserviceconductorcount," +
//		 		"ifnull(d.drivercount,0) nightservicedrivercount,ifnull(d.nightservice,0) nightservice,ifnull(e.driver,0) driver,ifnull(f.conductor,0) conductor,ifnull(g.ConductorDriver,0) ConductorDriver," +
//		 		"ifnull(spli.splitservice,0) splitservice,ifnull(spli.conductorcount,0) splitserviceconductorcount,ifnull(spli.drivercount,0) splitservicedrivercount,"+
//		 		"ifnull(a.conductorzero,0) conductorzerodayout,ifnull(a.driverzero,0) driverzerodayout,"+
//		 		"ifnull(b.conductorzero,0) conductorzeronightout,ifnull(b.driverzero,0) driverzeronightout,"+
//		 		"ifnull(c.conductorzero,0) conductorzerogenshift,ifnull(c.driverzero,0) driverzerogenshift,"+
//		 		"ifnull(d.conductorzero,0) conductorzeronightservice,ifnull(d.driverzero,0) driverzeronightservice,"+
//		 		"ifnull(spli.conductorzero,0) conductorzerosplitservice,ifnull(spli.driverzero,0) driverzerosplitservice,"+
////		 		"ifnull(((a.dayout*a.drivercount)+(b.nightout*b.drivercount)+(c.genshift*c.drivercount)+(d.nightservice*d.drivercount)),0) driversanction," +
////		 		"ifnull(((a.dayout*a.conductorcount)+(b.nightout*b.conductorcount)+(c.genshift*c.conductorcount)+(d.nightservice*d.conductorcount)),0) conductorsanction  " +
//	            "ifnull((a.drivercount+b.drivercount+c.drivercount+d.drivercount),0) driversanction," +
//		        "ifnull((a.conductorcount+b.conductorcount+c.conductorcount+d.conductorcount),0) conductorsanction  " +
//		 		"from (select count(schedule_number) genshift,og.org_name org_name,og.org_chart_id org_chart_id, sum(driver)  drivercount,ifnull((SELECT sum(conductor) conductorcountzero " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '1'  " +
//		 		"and `conductor` = '0'  and status='NEW' and  "+query1 +
//		 		"  current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) conductorzero," +
//		 		"ifnull((SELECT sum(driver)  drivercountzero  FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '1'  and `driver` = '0'  and status='NEW' " +
//		 		"and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) driverzero," +
//		 		"(SELECT sum(conductor) conductorcount  FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '1'  " +
//		 		"and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) conductorcount,depot_id " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '1'  and status='NEW' " +
//		 		"and  "+query1 +"  current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) c left join " +
//		 		
//		 		"(select count(schedule_number) nightout,og.org_name org_name,og.org_chart_id org_chart_id, sum(driver)  drivercount,ifnull((SELECT sum(conductor) conductorcountzero " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '2'  " +
//		 		"and `conductor` = '0'  and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) conductorzero," +
//		 		"ifnull((SELECT sum(driver)  drivercountzero FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '2'  and `driver` = '0'  and status='NEW' " +
//		 		"and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) driverzero," +
//		 		"( SELECT sum(conductor) cnt FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '2'   " +
//		 		"and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) conductorcount,depot_id " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '2'  and status='NEW' " +
//		 		"and  "+query1 +"  current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) b " +
//		 		"on b.org_name=c.org_name left join " +
//		 		
//		 		"(select count(schedule_number) dayout,og.org_name org_name,og.org_chart_id org_chart_id, sum(driver)  drivercount,ifnull((SELECT sum(conductor) conductorcountzero " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '3'  " +
//		 		"and `conductor` = '0'  and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) conductorzero," +
//		 		"ifnull((SELECT sum(driver) cnt FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '3'  and `driver` = '0'  and status='NEW' " +
//		 		"and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) driverzero," +
//		 		"(SELECT sum(conductor) cnt FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '3'   " +
//		 		"and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) conductorcount,depot_id " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '3'  and status='NEW' " +
//		 		"and  "+query1 +"  current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) a " +
//		 		"on a.org_name=c.org_name left join " +
//		 		
//		 		"(select count(schedule_number) nightservice,og.org_name org_name,og.org_chart_id org_chart_id, sum(driver)  drivercount,ifnull((SELECT sum(conductor) conductorcountzero " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '4'  " +
//		 		"and `conductor` = '0'  and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) conductorzero," +
//		 		"ifnull((SELECT sum(driver) cnt FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '4'  and `driver` = '0'  and status='NEW' " +
//		 		"and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) driverzero," +
//		 		"(SELECT sum(conductor) cnt FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '4'   " +
//		 		"and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) conductorcount,depot_id " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '4'  and status='NEW' " +
//		 		"and  "+query1 +"  current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) d " +
//		 		"on d.org_name=c.org_name left join " +
//		 		
//		 		"(select count(schedule_number) splitservice,og.org_name org_name,og.org_chart_id org_chart_id, sum(driver)  drivercount,ifnull((SELECT sum(conductor) conductorcountzero " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '11'  " +
//		 		"and `conductor` = '0'  and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) conductorzero," +
//		 		"ifnull((SELECT sum(driver) cnt FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '11'  and `driver` = '0'  and status='NEW' " +
//		 		"and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id),0) driverzero," +
//		 		"(SELECT sum(conductor) cnt FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '11'   " +
//		 		"and status='NEW' and  "+query1 +
//		 		" current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) conductorcount,depot_id " +
//		 		"FROM `schedule` inner join org_chart og on og.org_chart_id=schedule.depot_id " +
//		 		"WHERE  `schedule_type` = '11' AND  status='NEW' " +
//		 		"and  "+query1 +"  current_status='CASE WORKER' and schedule.deleted_status='0'  group by schedule.depot_id) spli on  spli.org_name=c.org_name left join"+
//		 		
//		 		"(SELECT COUNT(EMPLOYEE_NAME) driver,org_name,org_chart_id from " +
//		 		"(select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` " +
//		 		"inner join   org_chart og on og.org_chart_id=employee.org_chart_id " +
//		 		"WHERE `EMPLOYEE_DESIGNATION` = 'driver' AND  "+query1+"   employee.STATUS = 'ACTIVE'  group by EMPLOYEE_ID) ab group by ab.org_chart_id) e on e.org_name=c.org_name " +
//		 		"left join (SELECT COUNT(EMPLOYEE_NAME) conductor,org_name ,org_chart_id " +
//		 		"from (select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` " +
//		 		"left join   org_chart og on og.org_chart_id=employee.org_chart_id " +
//		 		"WHERE `EMPLOYEE_DESIGNATION` = 'Conductor' AND  "+query1+"   employee.STATUS = 'ACTIVE'  ) ac group by ac.org_chart_id ) f " +
//		 		"on f.org_name=c.org_name left join " +
//		 		"(SELECT COUNT(EMPLOYEE_NAME) ConductorDriver,org_name,org_chart_id " +
//		 		"from (select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` inner join   org_chart og on og.org_chart_id=employee.org_chart_id " +
//		 		"WHERE `EMPLOYEE_DESIGNATION` = 'DriverConductor' AND  "+query1+"    employee.STATUS = 'ACTIVE'   group by EMPLOYEE_ID) ad " +
//		 		"group by ad.org_chart_id)  g on g.org_name=c.org_name ";
	 
	 String sql = "select og.org_name org_name,og.org_chart_id org_chart_id,driveremp driver,condemp conductor," +
	 		"conddriveremp ConductorDriver,ifnull(dayout,0) dayout,ifnull(nightout,0) nightout,ifnull(genshift,0) genshift,ifnull(nightservice,0) nightservice,ifnull(splitservice,0) splitservice," +
	 		"SUM(CASE WHEN conductor = '0' and `schedule_type` = '1'   THEN 1 ELSE 0  END) conductorzerogenshift," +
	 		"SUM(CASE WHEN conductor != '0' and `schedule_type` = '1'   THEN conductor ELSE 0  END) genshiftconductorcount," +
	 		"SUM(CASE WHEN driver = '0' and `schedule_type` = '1'  THEN 1 ELSE 0 END) driverzerogenshift," +
	 		"SUM(CASE WHEN driver != '0' and `schedule_type` = '1'  THEN driver ELSE 0 END) genshiftdrivercount," +
	 		
	 		
	 		"SUM(CASE WHEN conductor = '0' and `schedule_type` = '2'   THEN 1 ELSE 0  END) conductorzeronightout," +
	 		"SUM(CASE WHEN conductor != '0' and `schedule_type` = '2'   THEN conductor ELSE 0  END) nightoutconductorcount," +
	 		"SUM(CASE WHEN driver = '0' and `schedule_type` = '2'  THEN 1 ELSE 0 END) driverzeronightout," +
	 		"SUM(CASE WHEN driver != '0' and `schedule_type` = '2'  THEN driver ELSE 0 END) nightoutdrivercount," +
	 		
	 		"SUM(CASE WHEN conductor = '0' and `schedule_type` = '3'   THEN 1 ELSE 0  END) conductorzerodayout," +
	 		"SUM(CASE WHEN conductor != '0' and `schedule_type` = '3'   THEN conductor ELSE 0  END) dayoutconductorcount," +
	 		"SUM(CASE WHEN driver = '0' and `schedule_type` = '3'  THEN 1 ELSE 0 END) driverzerodayout," +
	 		"SUM(CASE WHEN driver != '0' and `schedule_type` = '3'  THEN driver ELSE 0 END) dayoutdrivercount," +
	 		
	 		"SUM(CASE WHEN conductor = '0' and `schedule_type` = '4'   THEN 1 ELSE 0  END) conductorzeronightservice," +
	 		"SUM(CASE WHEN conductor != '0' and `schedule_type` = '4'   THEN conductor ELSE 0  END) nightserviceconductorcount," +
	 		"SUM(CASE WHEN driver = '0' and `schedule_type` = '4'  THEN 1 ELSE 0 END) driverzeronightservice," +
	 		"SUM(CASE WHEN driver != '0' and `schedule_type` = '4'  THEN driver ELSE 0 END) nightservicedrivercount," +
	 		
	 		
	 		"SUM(CASE WHEN conductor = '0' and `schedule_type` = '11'   THEN 1 ELSE 0  END) conductorzerosplitservice," +
	 		"SUM(CASE WHEN conductor != '0' and `schedule_type` = '11'   THEN conductor ELSE 0  END) splitserviceconductorcount," +
	 		"SUM(CASE WHEN driver = '0' and `schedule_type` = '11'  THEN 1 ELSE 0 END) driverzerosplitservice," +
	 		"SUM(CASE WHEN driver != '0' and `schedule_type` = '11'  THEN driver ELSE 0 END) splitservicedrivercount " +
	 		"from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id  " +
	 		
           "inner join (select count(schedule_number) genshift,org_chart_id from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id " +
	       "where  schedule_type='1' and status='NEW' " +
	      "and  "+query1 +"  current_status='CASE WORKER' and schedule.deleted_status='0' and schedule.created_date <='"+date1+"'  group by schedule.depot_id) c on c.org_chart_id=og.org_chart_id " +
	 		
	 		"left join (select count(schedule_number) dayout,org_chart_id from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id  " +
	 		"where  schedule_type='3' and status='NEW' " +
	 		"and  "+query1 +"   current_status='CASE WORKER' and schedule.deleted_status='0' and schedule.created_date <='"+date1+"' group by schedule.depot_id) a on a.org_chart_id=og.org_chart_id " +
	 		
	 		"left join (select count(schedule_number) nightout,org_chart_id from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id " +
	 		"where  schedule_type='2' and status='NEW' " +
	 		"and  "+query1 +"   current_status='CASE WORKER' and schedule.deleted_status='0' and schedule.created_date <='"+date1+"' group by schedule.depot_id) b on b.org_chart_id=og.org_chart_id " +
	 		
	 		
	 		"left join (select count(schedule_number) nightservice,org_chart_id from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id " +
	 		"where  schedule_type='4' and status='NEW' " +
	 		"and  "+query1 +"   current_status='CASE WORKER' and schedule.deleted_status='0' and schedule.created_date <='"+date1+"' group by schedule.depot_id) d on d.org_chart_id=og.org_chart_id " +
	 		"left join (select count(schedule_number) splitservice,org_chart_id from schedule inner join org_chart og on og.org_chart_id=schedule.depot_id " +
	 		"where  schedule_type='11' and status='NEW' " +
	 		"and  "+query1 +"    current_status='CASE WORKER' and schedule.deleted_status='0' and schedule.created_date <='"+date1+"' group by schedule.depot_id) spl on spl.org_chart_id=og.org_chart_id "+
	 		
	 		
	 		"inner join (SELECT COUNT(EMPLOYEE_NAME) driveremp,org_name,org_chart_id from " +
	 		"(select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` " +
	 		"inner join   org_chart og on og.org_chart_id=employee.org_chart_id " +
	 		"WHERE employee.`EMPLOYEE_DESIGNATION` = 'driver' AND  "+query1 +" " +
	 		" employee.STATUS = 'ACTIVE'  and employee.created_date <='"+date1+"' group by EMPLOYEE_ID) ab group by ab.org_chart_id) e on e.org_name=og.org_name " +
	 		
	 		"left join (SELECT COUNT(EMPLOYEE_NAME)condemp,org_name,org_chart_id from " +
	 		"(select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` " +
	 		"inner join   org_chart og on og.org_chart_id=employee.org_chart_id " +
	 		"WHERE employee.`EMPLOYEE_DESIGNATION` = 'Conductor' AND  "+query1 +" " +
	 		"  employee.STATUS = 'ACTIVE'  and employee.created_date <='"+date1+"' group by EMPLOYEE_ID) ac group by ac.org_chart_id) f on f.org_name=og.org_name " +
	 		
	 		"left join (SELECT COUNT(EMPLOYEE_NAME)conddriveremp,org_name,org_chart_id from " +
	 		"(select EMPLOYEE_NAME,org_name,og.org_chart_id  from  `employee` " +
	 		"inner join   org_chart og on og.org_chart_id=employee.org_chart_id " +
	 		"WHERE employee.`EMPLOYEE_DESIGNATION` = 'DriverConductor' AND  "+query1 +"  " +
	 		"  employee.STATUS = 'ACTIVE'  and employee.created_date <='"+date1+"' group by EMPLOYEE_ID) ad group by ad.org_chart_id) g on g.org_name=og.org_name "+
	 		
	 		"where  schedule.status='NEW' and " +
	 		"current_status='CASE WORKER' and "+query1 +" schedule.deleted_status='0' and schedule.created_date<='"+date1+"'  group by schedule.depot_id";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("dayout").addScalar("dayoutconductorcount")
				.addScalar("dayoutdrivercount").addScalar("nightout").addScalar("nightoutconductorcount").addScalar("nightoutdrivercount").addScalar("genshift")
				.addScalar("genshiftconductorcount").addScalar("genshiftdrivercount").addScalar("nightserviceconductorcount").addScalar("nightservicedrivercount")
				.addScalar("nightservice").addScalar("driver").addScalar("conductor").addScalar("ConductorDriver")
//				.addScalar("driversanction")
				.addScalar("org_chart_id")
				.addScalar("splitservice")
				.addScalar("splitserviceconductorcount").addScalar("splitservicedrivercount")
				.addScalar("conductorzerodayout").addScalar("driverzerodayout").addScalar("conductorzeronightout").addScalar("driverzeronightout")
				.addScalar("conductorzerogenshift").addScalar("driverzerogenshift").addScalar("conductorzeronightservice").addScalar("driverzeronightservice")
				.addScalar("conductorzerosplitservice").addScalar("driverzerosplitservice");
		
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
	
  regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br> Crew Position Report</br></h4></div>";

 regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";


 
 regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
// regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>Depot</th><th colspan='2'>No. of Schedules</th><th>Day Out</th><th>Night Out</th><th>General Shift</th><th>Night Service</th><th>Total Schedule</th><th>Driver Working</th><th>Sanction</th><th>Diff.</th><th>Conductor  Working</th><th>Sanction</th><th>Diff.</th><th>Driver-Conductor  Working</th><th>Total Working</th><th>Total Sanction</th><th>Diff.</th>" +
// 		"<th>Crew Sanc</th><th>Diff</th><th>Current Ratio</th><th>Required Ratio</th><th>Diff.</th></tr></thead><tbody>";
 
 regionTypeAjaxString +="<thead><tr><th rowspan='2'>Sl.No</th><th rowspan='2'>Depot</th><th colspan='6' rowspan='1' align='center'>No. of Schedules</th><th align='center' rowspan='2'>Conductor Less Schedlue</th><th colspan='3' rowspan='1' align='center'>Driver</th><th colspan='3' rowspan='1' align='center'>Conductor</th><th rowspan='2'>Driver-Conductor  Working</th><th colspan='3' rowspan='1' align='center'>Total</th><th colspan='2' rowspan='1' align='center'>Req. As Per CR:4.6</th><th colspan='3' rowspan='1' align='center'>Crew Ratio</th>" +
	 	"</th></tr>" +
	 	"<tr><th>Day Out</th><th>Night Out</th><th>General Shift</th><th>Night Service</th><th>Split Service</th><th>Total</th>" +
	 	"<th>Driver Working</th><th>Sanction</th><th>Diff.</th>" +
	 	"<th>Conductor Working</th><th>Sanction</th><th>Diff.</th>" +
	 	"<th>Working</th><th>Sanction</th><th>Diff.</th>" +
	 	"<th>Crew Sanc</th><th>Diff</th>" +
	 	"<th>Current Ratio</th><th>Required Ratio</th><th>Diff.</th></tr></thead><tbody>";
 int dayouttotal=0;
 int nightouttotal=0;
 int genshifttotal=0;
 int nightservicetotal=0;
 int splitservicetotal=0;
 double wholetotala=0;
 int driverconductorlesstotal=0;
 int driverworkingtotal=0;
 double driversanctiontotal=0;
 double driverdifftotal=0;
 int conductortotal=0;
 double conductorsanctiontotal=0;
 double conductordifftotal=0;
 int conductordrivertotal=0;
 double totaltotalworking=0;
 long totaltotalsaction=0;
 double totaltotaldiff=0;
 double totalcrewsanc=0;
 double totalcrewdiffsanction=0;
 double totalcurrentratio=0;
 double totalrequredration=0;
 double totallastdiff=0;
 
 for (int i = 0; i < aliasToValueMapList.size(); i++) {
		regionTypeAjaxString +="<tr>";
		Map<String, Object> list = aliasToValueMapList.get(i);
		JSONArray ja = new JSONArray();
		int j=i+1;
		
		
		
		regionTypeAjaxString +="<td align='right'>"+ j+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()  +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("dayout").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("nightout").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("genshift").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("nightservice").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("splitservice").toString() +"</td>";
		double totalschedule = Integer.parseInt(list.get("dayout").toString())+Integer.parseInt(list.get("nightout").toString())
		+Integer.parseInt(list.get("genshift").toString())+Integer.parseInt(list.get("nightservice").toString())+Integer.parseInt(list.get("splitservice").toString());
		
		regionTypeAjaxString +="<td align='right'>"+ totalschedule +"</td>";
		
		int driverconductorzero=Integer.parseInt(list.get("conductorzerodayout").toString())+Integer.parseInt(list.get("driverzerodayout").toString())
				+Integer.parseInt(list.get("conductorzeronightout").toString())+Integer.parseInt(list.get("driverzeronightout").toString())
				+Integer.parseInt(list.get("conductorzerogenshift").toString())+Integer.parseInt(list.get("driverzerogenshift").toString())
				+Integer.parseInt(list.get("conductorzeronightservice").toString())+Integer.parseInt(list.get("driverzeronightservice").toString())
				+Integer.parseInt(list.get("conductorzerosplitservice").toString())+Integer.parseInt(list.get("driverzerosplitservice").toString());
		
		regionTypeAjaxString +="<td><a href='#' onclick='jsFunction("+list.get("org_chart_id").toString()+")'>"+driverconductorzero+"</a>"+"</td>";
		//regionTypeAjaxString +="<td align='right'>"+ driverconductorzero +"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("driver").toString() +"</td>";
		
		int driversanction=Integer.parseInt(list.get("dayoutdrivercount").toString())+Integer.parseInt(list.get("nightoutdrivercount").toString())
				+Integer.parseInt(list.get("genshiftdrivercount").toString())+Integer.parseInt(list.get("nightservicedrivercount").toString())
				+Integer.parseInt(list.get("splitservicedrivercount").toString());
		System.out.println("driversanction=="+driversanction);
		double driversanction1 = driversanction+(driversanction*0.3833);
		regionTypeAjaxString +="<td align='right'>"+ Math.round(driversanction1) +"</td>";
		double driverdiff= Integer.parseInt(list.get("driver").toString())-Math.round(driversanction1);
		regionTypeAjaxString +="<td align='right'>"+ driverdiff +"</td>";
		
		regionTypeAjaxString +="<td align='right'>"+list.get("conductor").toString()  +"</td>";
		int conductorsanction=Integer.parseInt(list.get("dayoutconductorcount").toString())+Integer.parseInt(list.get("nightoutconductorcount").toString())
				+Integer.parseInt(list.get("genshiftconductorcount").toString())+Integer.parseInt(list.get("nightserviceconductorcount").toString())
				+Integer.parseInt(list.get("splitserviceconductorcount").toString());
		double conductorsanction1 = conductorsanction+(conductorsanction*0.3833);
		regionTypeAjaxString +="<td align='right'>"+Math.round(conductorsanction1)+"</td>";
		double condudiff= Integer.parseInt(list.get("conductor").toString())-Math.round(conductorsanction1);
		regionTypeAjaxString +="<td align='right'>"+condudiff+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("ConductorDriver").toString() +"</td>";
		double totalworking= Integer.parseInt(list.get("driver").toString())+Integer.parseInt(list.get("conductor").toString())+Integer.parseInt(list.get("ConductorDriver").toString());
		regionTypeAjaxString +="<td align='right'>"+ totalworking +"</td>";
		long totalsanction= Math.round(driversanction1)+Math.round(conductorsanction1);
		regionTypeAjaxString +="<td align='right'>"+ totalsanction +"</td>";
		double totaldiff = totalworking-(int)totalsanction;
		regionTypeAjaxString +="<td align='right'>"+ totaldiff +"</td>";
		double crewsanc = Double.parseDouble(df1.format(totalschedule*4.6));
		regionTypeAjaxString +="<td align='right'>"+ crewsanc +"</td>";
		double crewdiffsanction = totalworking-crewsanc;
		regionTypeAjaxString +="<td align='right'>"+df1.format(crewdiffsanction)+"</td>";
		double current = totalworking/totalschedule;
		regionTypeAjaxString +="<td align='right'>"+ df1.format(current) +"</td>";
		double required = totalsanction/totalschedule;
		regionTypeAjaxString +="<td align='right'>"+ df1.format(required) +"</td>";
		double lastdiff = current-required;
		regionTypeAjaxString +="<td align='right'>"+ df1.format(lastdiff) +"</td>";
		
			
		dayouttotal=dayouttotal+Integer.parseInt(list.get("dayout").toString());
		nightouttotal=nightouttotal+Integer.parseInt(list.get("nightout").toString());
		genshifttotal=genshifttotal+Integer.parseInt(list.get("genshift").toString());
		nightservicetotal=nightservicetotal+Integer.parseInt(list.get("nightservice").toString());
		splitservicetotal=splitservicetotal+Integer.parseInt(list.get("splitservice").toString());
		wholetotala=wholetotala+totalschedule;
		driverconductorlesstotal=driverconductorlesstotal+driverconductorzero;
		driverworkingtotal=driverworkingtotal+Integer.parseInt(list.get("driver").toString());
		driversanctiontotal=Math.round(driversanctiontotal+driversanction1);
		driverdifftotal=driverdifftotal+driverdiff;
		conductortotal=conductortotal+Integer.parseInt(list.get("conductor").toString());
		conductorsanctiontotal=Math.round(conductorsanctiontotal+conductorsanction1);
		conductordifftotal=conductordifftotal+condudiff;
		conductordrivertotal=conductordrivertotal+Integer.parseInt(list.get("ConductorDriver").toString());
		totaltotalworking=totaltotalworking+totalworking;
		totaltotalsaction=totaltotalsaction+totalsanction;
		totaltotaldiff=totaltotaldiff+totaldiff;
		totalcrewsanc=totalcrewsanc+crewsanc;
		totalcrewdiffsanction=totalcrewdiffsanction+crewdiffsanction;
		totalcurrentratio=totalcurrentratio+current;
		totalrequredration=totalrequredration+required;
		totallastdiff=totallastdiff+lastdiff;
			   regionTypeAjaxString +="</tr>";
			 
				
		
		}
	   
  
	   regionTypeAjaxString +="<tr><td colspan='2'><center><b>Totals</b></center></td><td align='center'><b>"+ dayouttotal+"</td>" +
	   		"<td align='center'><b>"+ nightouttotal+"</td><td align='center'><b>"+ genshifttotal+"</td><td align='center'><b>"+ nightservicetotal+"</td>" +
	   				"<td align='center'><b>"+ splitservicetotal+"</td><td align='center'><b>"+ wholetotala+"</td>" +
	   						"<td align='center'><b> <a href='#' onclick='jsFunction1("+divisionlist1+");'>"+driverconductorlesstotal+"</a></td>" +
	   								"<td align='center'><b>"+ driverworkingtotal+"</td><td align='center'><b>"+ driversanctiontotal+"</td>" +
	   										"<td align='center'><b>"+ driverdifftotal+"</td><td align='center'><b>"+ conductortotal+"</td>" +
	   												"<td align='center'><b>"+ conductorsanctiontotal+"</td><td align='center'><b>"+ conductordifftotal+"</td>" +
	   														"<td align='center'><b>"+ conductordrivertotal+"</td><td align='center'><b>"+ totaltotalworking+"</td>" +
	   																"<td align='center'><b>"+ totaltotalsaction+"</td><td align='center'><b>"+ totaltotaldiff+"</td>" +
	   																		"<td align='center'><b>"+ totalcrewsanc+"</td><td align='center'><b>"+ df1.format(totalcrewdiffsanction)+"</td>" +
	   																				"<td align='center'><b>"+ df1.format(totalcurrentratio)+"</td><td align='center'><b>"+ df1.format(totalrequredration)+"</td>" +
	   																						"<td align='center'><b>"+ df1.format(totallastdiff)+"</td></tr>" +"\n";  
	    regionTypeAjaxString += "</tbody></table></div> </div>"; 
     
		 
		 //System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));
HttpServletResponse response = ServletActionContext.getResponse();
PrintWriter out;
 




	out = response.getWriter();
	out.print(regionTypeAjaxString);
} catch (Exception e) {
	
	e.printStackTrace();
}

return null;
}

public String getDepotForCrewPosition() {
	// get Depot List..
	HttpServletRequest req = ServletActionContext.getRequest();
	VtsDataDAO dao = VtsDataDAO.getInstance();
	int parentId = Integer.parseInt(req.getParameter("val"));
	String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
    String orgchartid=(String)req.getSession().getAttribute("orgchartid");
    
    if(orgtypeid.equals("2")){
//    	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
    		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
    		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
    		String regionTypeAjaxString = "";
    		//regionTypeAjaxString += "<option value='0'>------select------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		try {
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//    		return null;
//    	}
	
    }
    
  
    else if(orgtypeid.equals("1") && orgchartid.equals("1")){
    	List<String> l1 = dao.getDepotId(parentId);
		List<String> l2 = dao.getDepotName(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>All</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    else {
    	List<String> l1 = dao.getDepotId(parentId,orgchartid);
		List<String> l2 = dao.getDepotName(parentId,orgchartid);
		String regionTypeAjaxString = "";
		//regionTypeAjaxString += "<option value='0'>------select------</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	return null;

}

public String getdriveconddetails(){
	String depotlist = ServletActionContext.getRequest().getParameter("depotlist1");
	String startdate1 = ServletActionContext.getRequest().getParameter("startdate");
	Common common = new Common();
	
    String date1=common.getDateFromPicker(startdate1);
	setDepottype(depotlist);
	setDate1(date1);
	return "success";
}


public String getDriverConductordetails1(){
	
	String depottype = ServletActionContext.getRequest().getParameter("depottype");
	String date1 = ServletActionContext.getRequest().getParameter("date1");
	Common common = new Common();
    CollectionReportDAO dao = new CollectionReportDAO();
   
    
    HttpServletRequest req = ServletActionContext.getRequest();
    Session session1 = null;
    Transaction transaction = null;
    String regionTypeAjaxString1 = "";
   try{
	      session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	        transaction = session1.beginTransaction();
    String dueforauditquery="SELECT `driver`, `conductor`,schedule_number,schedule_type_name FROM `schedule` inner join schedule_type st on st.schedule_type_id=schedule.schedule_type " +
    		"WHERE `driver` = '0' AND `conductor` = '0' AND schedule.`current_status` = 'case worker' " +
    		"AND schedule.`status` = 'new' and schedule.depot_id='"+depottype+"' and schedule.created_date <='"+date1+"'";
   
    Query query = session1.createSQLQuery(dueforauditquery);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();
   
    regionTypeAjaxString1 += "<div class='component'><table border='1' class='overflow-y'>";
    regionTypeAjaxString1 += "<thead><tr><th style='font-size:15px;'>SI.No.</th><th style='font-size:15px;'>Schedule No.</th>" +
    		"<th style='font-size:15px;'>Schedule Type</th><th style='font-size:15px;'>Driver</th>"
            + " <th style='font-size:15px;'>Conductor</th></tr></thead><tbody>";
int j=0;
    if (aliasToValueMapList.size() > 0) {
    	for (int i = 0; i < aliasToValueMapList. size(); i++) {
          Map<String, Object> list = aliasToValueMapList.get(i);
          j=i+1;
          regionTypeAjaxString1+="<tr>";
          regionTypeAjaxString1 += "<td>" + j + "</td>";
          regionTypeAjaxString1+= "<td>" + list.get("schedule_number").toString() + "</td>";
          regionTypeAjaxString1 += "<td>" + list.get("schedule_type_name").toString() + "</td>";
          regionTypeAjaxString1 += "<td>" + list.get("driver").toString() + "</td>";
          regionTypeAjaxString1 += "<td>" + list.get("conductor").toString() + "</td>";
          regionTypeAjaxString1+="</tr>";
    	}
    }
    
    HttpServletResponse response = ServletActionContext.getResponse();
    PrintWriter out;
    out = response.getWriter();
    out.print(regionTypeAjaxString1);
    }catch(Exception e)
      {
	  e.printStackTrace();
      }
	return null;
}
	

public String gettotaldriveconddetails(){
	String divisionlist = ServletActionContext.getRequest().getParameter("divisionlist1");
	String startdate1 = ServletActionContext.getRequest().getParameter("startdate");
	Common common = new Common();
	
    String date1=common.getDateFromPicker(startdate1);
	setDivisiontype(divisionlist);
	setDate1(date1);
	return "success";
}


public String getTotalDriverConductordetails1(){
	
	String divisiontype = ServletActionContext.getRequest().getParameter("divisiontype");
	String date1 = ServletActionContext.getRequest().getParameter("date1");
	Common common = new Common();
    CollectionReportDAO dao = new CollectionReportDAO();
   
    
    HttpServletRequest req = ServletActionContext.getRequest();
    Session session1 = null;
    Transaction transaction = null;
    String regionTypeAjaxString1 = "";
   try{
	      session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	        transaction = session1.beginTransaction();
    String dueforauditquery="SELECT `driver`, `conductor`,schedule_number,schedule_type_name  " +
    		"FROM `schedule` " +
    		"inner join schedule_type st on st.schedule_type_id=schedule.schedule_type " +
    		"inner join org_chart on org_chart.org_chart_id = schedule.depot_id " +
    		"WHERE `driver` = '0' AND `conductor` = '0'  " +
    		"AND schedule.`current_status` = 'case worker' AND schedule.`status` = 'new'  " +
    		"and org_chart.parent_id="+divisiontype+" and schedule.created_date <='"+date1+"'";
   
    Query query = session1.createSQLQuery(dueforauditquery);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();
   
    regionTypeAjaxString1 += "<div class='component'><table border='1' class='overflow-y'>";
    regionTypeAjaxString1 += "<thead><tr><th style='font-size:15px;'>SI.No.</th><th style='font-size:15px;'>Schedule No.</th>" +
    		"<th style='font-size:15px;'>Schedule Type</th><th style='font-size:15px;'>Driver</th>"
            + " <th style='font-size:15px;'>Conductor</th></tr></thead><tbody>";
int j=0;
    if (aliasToValueMapList.size() > 0) {
    	for (int i = 0; i < aliasToValueMapList. size(); i++) {
          Map<String, Object> list = aliasToValueMapList.get(i);
          j=i+1;
          regionTypeAjaxString1+="<tr>";
          regionTypeAjaxString1 += "<td>" + j + "</td>";
          regionTypeAjaxString1+= "<td>" + list.get("schedule_number").toString() + "</td>";
          regionTypeAjaxString1 += "<td>" + list.get("schedule_type_name").toString() + "</td>";
          regionTypeAjaxString1 += "<td>" + list.get("driver").toString() + "</td>";
          regionTypeAjaxString1 += "<td>" + list.get("conductor").toString() + "</td>";
          regionTypeAjaxString1+="</tr>";
    	}
    }
    
    HttpServletResponse response = ServletActionContext.getResponse();
    PrintWriter out;
    out = response.getWriter();
    out.print(regionTypeAjaxString1);
    }catch(Exception e)
      {
	  e.printStackTrace();
      }
	return null;
}

}
