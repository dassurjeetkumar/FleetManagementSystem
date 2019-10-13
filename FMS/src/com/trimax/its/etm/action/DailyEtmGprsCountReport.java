package com.trimax.its.etm.action;

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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DailyEtmGprsCountReport extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";
	public String startdate;
	public String name;
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


		
	
	
	
	public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


	public String execute() {
		//this.ServiceTaxCollections();
//		VtsDataDAO vvt = VtsDataDAO.getInstance();
//		divisionlist = vvt.getDivisionName();
		return "success";
	}
	
	public String getDailyetmGprsCount(){
		

		try {
		CollectionReportDAO dao=new CollectionReportDAO();
		
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		
		System.out.println("Date===="+startdate);
		System.out.println("Name===="+name);
		String date1=dao.getDateFromPickerDate(startdate);
		System.out.println("date==="+date1);
		Common common = new Common();
		String orgname=dao.getOrgName();
//		String depot=dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	   
	    
	   
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String sql="";
		 if(name=="1"){
			 sql="SELECT org_name,etim_no,schedule_no,waybill_no,sum(px_total_amount) collection  FROM bmtcGprs.etim_gprs_ticket egt  "+
				 " join org_chart oc on egt.depot_id=oc.org_chart_id  WHERE ticket_date = '"+date1+"' "+
				  " group by etim_no having collection>0 order by org_name ";
		 }else{
			 sql=" SELECT org_name,etim_no,schedule_no,waybill_no,sum(px_total_amount) collection  FROM bmtcGprs.etim_gprs_ticket egt  "+
				  " join org_chart oc on egt.depot_id=oc.org_chart_id  WHERE ticket_date = '"+date1+"' "+
				  " group by waybill_no having collection>0 order by org_name ";
		 }
		 
		 
		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("etim_no").addScalar("schedule_no").addScalar("waybill_no").addScalar("collection");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
		
			
			double Total_do=0.0;
			double Total_no=0.0;
			double Total_gs=0.0;
			double Total_ns=0.0;
			double Total_single=0.0;
			double Total_double=0.0;

		    
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>DAILY ETM GPRS COUNT REPORT </h4></div>";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       
	        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
	        regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Depot Name</th><th>ETM NO</th><th>Sch No</th><th>WAYBILL NO</th><th>Collection</th></thead><tbody>";

//	        regionTypeAjaxString += "<tr><th align='center'>Sl.No</th><th align='center'>Depot Name</th><th align='center'>ETM NO</th><th align='center'>Sch.NO</th><th align='center'>WAYBILL NO</th><th align='center'>Collection</th></tr>";

			 
//			
	        
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='6' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{

              
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
			
 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("etim_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("schedule_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("waybill_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("collection").toString() +"</td>";
			
			   regionTypeAjaxString +="</tr>";
		}
//				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_city_ord+"</td>"+"<td align='right'><b>"+ Total_city_vaj+"</td></tr>" +"\n";  
//				
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
		
	
	
	

}
