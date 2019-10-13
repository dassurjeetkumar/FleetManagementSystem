package com.trimax.its.device.action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class DepotWiseAndCauseWiseSummary extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	
	    private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;
		private String depotlist1;
		private String divisionlist1;
		public String value;
		
	


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
		char fl = 18;
		char f2 = 12;
		int pagi = 1;

		int FULL_LEN = 120;
		int HALF_LEN = 60;

		double totalAmmount=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);

		public String execute() {
//			VtsDataDAO vvt = VtsDataDAO.getInstance();
//			divisionlist = vvt.getDivisionName();
//			System.out.println("division........"+divisionlist);	
			return "success";
		}
		
		public String getDepotwiseAndCausewiseSummary(){
			
			try {
				CollectionReportDAO dao=new CollectionReportDAO();
//				System.out.println("Depot Name==="+depotName);
//				String date1=dao.getDateFromPickerDate(startdate);
//				String date2=dao.getDateFromPickerDate(enddate);
				Common common = new Common();
				String orgname=dao.getOrgName();
				String depot=dao.getDepotName();
				
				Date  ss1=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			    String runDateTime = sdf.format(ss1);
			    
			    SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy");
			    String cur_date = sdf1.format(ss1);
			    
//			    String dates[]=startdate.split("-");
//				String month=dates[0];
//				int year=Integer.parseInt(dates[1]);
//				int select_month=Integer.parseInt(month);
//				int curr_month=Calendar.getInstance().get(Calendar.MONTH);
//				int curr_month1=curr_month+1;
//			    
//				 String date1="";
//				    if(curr_month1==select_month){
//				    	Date curr_date=new Date();
//				    	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//				    	date1=dateformat.format(curr_date);
//				    }else{
//				    	Calendar cal = new GregorianCalendar(year, select_month, 0);
//						Date date = cal.getTime();
//						DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//						date1=sdf2.format(date);
//				    }
//			    
			    
				
				DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
				HttpServletRequest req = ServletActionContext.getRequest();
				Session session1 = null;
				Transaction transaction = null;
				
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				 transaction = session1.beginTransaction();
				 
				 String sql="";
				 
//				 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////				  
//				 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//					List<Map<String,Object>> aliasToValueMapList = query.list();
//					double Totalvalues=0.0;
				 List<Map<String,Object>> aliasToValueMapList=null;
				    
					
					regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>Daily Revenue For All Depot</br></h4></div>";

			        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
			    


					regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
					regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Depot No</th><th>Depot Name</th><th>ETM Bag</th><th>Supplied ETM</th><th>Stand by ETMs</th><th>In Repair ETM at Service Cent</th><th>Available ETM For Operation</th><th>Shortage/Excess</th><th>Faulty %</th><th>Battery Problem</th><th>Display Problem</th><th>Etm Damage</th><th>Keypad Problem</th>" +
							"<th>Printer Problem</th><th>Auto Restart/Switch off</th><th>Tamper Problem</th><th>USB port Problem</th><th>Ticket Slow</th><th>Log on</th><th>Settlement</th>" +
						"</tr></thead><tbody>";
//					regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//					regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//							"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//							"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//							"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//							"</thead><tbody>";
					 
			        if(aliasToValueMapList==null){
				       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
				            regionTypeAjaxString += "<tr>";
				            regionTypeAjaxString += "<td colspan='22' align='center'><b>No Result Found</b></td>";
				           
				            regionTypeAjaxString += "</tr>";
				       	
				       }else{
			        
			              
//				     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//				    	 regionTypeAjaxString +="<tr>";
//					Map<String,Object> list = aliasToValueMapList.get(i);
//			 		int j=i+1;
//					
////			 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////					regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//					regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
//					regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";

//					

//					

					
					
//					regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//					
					

//					
//					   regionTypeAjaxString +="</tr>";
//				}
//						regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//								"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//										"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
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

	
	

}
