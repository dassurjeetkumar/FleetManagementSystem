package com.trimax.its.report.action;

import java.io.IOException;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ScheduleVehicleComaprisonReport extends ActionSupport {

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
	private Map<Integer, String> divisionlist;
	
	
	
	
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");
//		System.out.println("orgtypeid==="+orgtypeid);
//		System.out.println("orgchartid==="+orgchartid);
		
		String id = "!=0";
		if (orgtypeid.equals("1")) {
			id = "org_type_id=2 and org_chart_id!=0";

		}
		if (orgtypeid.equals("3")) {

			id = "org_chart_id in (select parent_id from org_chart where org_chart_id='"
					+ orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			id = "org_chart_id in ('"+ orgchartid + "')";
		}
//		System.out.println("id======="+id);
		getDivision(orgtypeid,id);
		return "success";
	}
	
	public String getDivision(String orgtypeid,String id){
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivision(orgtypeid,id);

		return "success";
	}
	
	public String getVehicleDetails(){
		
		return "success";
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getComparisonReport(String startDate,String depotid){
		
		JSONObject result = new JSONObject();

		try {
		CollectionReportDAO dao=new CollectionReportDAO();
		
//		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		

		
		
		System.out.println("startdate====="+startDate);
		String date=dao.getDateFromPickerDate(startDate);

		
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
		 
//		 String sql=" select dsm.depot_name depot_name,dsm.schedule_no_name schedule_no,dsm.shift_type_name shift_type_name,smv.vehicle_no Vehicle_no,dsm.vehicle_no vehicle_no2,dsm.updated_date updated_date from daily_schedule_mapping_vehicle dsm "+
//				    " inner join schedule_mapping_vehicle1 smv on smv.schedule_no=dsm.schedule_no  "+
//				 	" where dsm.updated_date like '%"+date+"%' and dsm.depot_id="+depotid+" group by dsm.schedule_no order by dsm.depot_id";
//		 
		String sql=" select dsm.depot_name depot_name,dsm.schedule_no_name schedule_no,dsm.shift_type_name shift_type_name,dsm.trip_number,smv.vehicle_no Vehicle_no, "+
				" dsm.vehicle_no vehicle_no2,dsm.updated_date updated_date,if(dsm.platform_ids='0','NA',dsm.platform_ids) platform_ids from daily_schedule_mapping_vehicle dsm  "+
				" inner join schedule_mapping_vehicle1 smv on smv.schedule_no=dsm.schedule_no  "+
				" where dsm.updated_date like '%"+date+"%' and dsm.depot_id="+depotid+" group by dsm.schedule_no,dsm.vehicle_no,dsm.shift_type_name order by dsm.depot_id";
		 
		 
		 
		 Query query = session1.createSQLQuery(sql).addScalar("depot_name").addScalar("schedule_no").addScalar("shift_type_name").addScalar("trip_number").addScalar("Vehicle_no").addScalar("vehicle_no2").addScalar("updated_date").addScalar("platform_ids");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			
		    
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br>SCHEDULE VEHICLE COMPARISON REPORT </h4></div>";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

//	        regionTypeAjaxString += "<tr><th>Sl.NO</th><th>Depot Name</th><th>Schedule No.</th><th>Shift Type</th><th>Vehicle No(As per Schedule Mapping)</th><th>Vehicle No(As per Daily Schedule Mapping)</th><th>Updated Date</th></tr>";

			 
//			
	        
//	        if(aliasToValueMapList.size()==0){
//	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
//	               regionTypeAjaxString += "<tr>";
//	               regionTypeAjaxString += "<td colspan='6' align='center'><b>No Result Found</b></td>";
//	              
//	               regionTypeAjaxString += "</tr>";
	          	
//	          }else{

//	        	  regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		  			regionTypeAjaxString +="<thead><tr><th>Sl.NO</th><th>Depot Name</th><th>Schedule No.</th><th>Shift Type</th><th>Vehicle No(As per Schedule Mapping)</th><th>Vehicle No(As per Daily Schedule Mapping)</th><th>Updated Date</th>" +
//		  					"</tr></thead><tbody>";
					JSONArray array = new JSONArray();

		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
			JSONArray ja = new JSONArray();
 		int j=i+1;
 		ja.add(j);
 		ja.add(list.get("depot_name").toString());
		 ja.add(list.get("schedule_no").toString());
		 ja.add(list.get("shift_type_name").toString());
		 ja.add(list.get("trip_number").toString());
		 ja.add(list.get("Vehicle_no").toString());
		 ja.add(list.get("vehicle_no2").toString());
		 ja.add(list.get("updated_date").toString());
		 ja.add(list.get("platform_ids").toString());
 		
// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("depot_name").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("schedule_no").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("shift_type_name").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("Vehicle_no").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("vehicle_no").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("updated_date").toString() +"</td>";
//				
//			
//			   regionTypeAjaxString +="</tr>";
		 array.add(ja);
		}
//				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_city_ord+"</td>"+"<td align='right'><b>"+ Total_city_vaj+"</td><td align='right'><b>"+ Total_city_cor+"</td><td align='right'><b>"+Total_city+"</td>" +
//						"<td align='right'><b>"+Total_sub_ord+"</td><td align='right'><b>"+Total_sub_cor+"</td><td align='right'><b>"+Total_sub_vaj+"</td><td align='right'><b>"+Total_sub_vvaj+"</td><td align='right'><b>"+Total_sub+"</td>" +
//								"<td align='right'><b>"+Total_sch+"</td><td align='right'><b>"+Total_city_ord_do+"</td><td align='right'><b>"+Total_city_ord_no+"</td><td align='right'><b>"+Total_city_ord_gs+"</td><td align='right'><b>"+Total_sub_ord_do+"</td><td align='right'><b>"+Total_sub_ord_no+"</td>" +
//										"<td align='right'><b>"+Total_sub_ord_gs+"</td><td align='right'><b>"+Total_city_cor_do+"</td><td align='right'><b>"+Total_city_cor_no+"</td><td align='right'><b>"+Total_city_cor_gs+"</td><td align='right'><b>"+Total_sub_cor_do+"</td><td align='right'><b>"+Total_sub_cor_no+"</td><td align='right'><b>"+Total_sub_cor_gs+"</td>" +
//												"<td align='right'><b>"+Total_city_vaj_do+"</td><td align='right'><b>"+Total_city_vaj_no+"</td><td align='right'><b>"+Total_city_vaj_gs+"</td><td align='right'><b>"+Total_sub_vaj_do+"</td><td align='right'><b>"+Total_sub_vaj_no+"</td><td align='right'><b>"+Total_sub_vaj_gs+"</td>" +
//														"<td align='right'><b>"+Total_city_vvaj_do+"</td><td align='right'><b>"+Total_city_vvaj_no+"</td><td align='right'><b>"+Total_city_vvaj_gs+"</td><td align='right'><b>"+Total_sub_vvaj_do+"</td><td align='right'><b>"+Total_sub_vvaj_no+"</td><td align='right'><b>"+Total_sub_vvaj_gs+"</td>" +
//																"<td align='right'><b>"+Total_city_do+"</td><td align='right'><b>"+Total_city_no+"</td><td align='right'><b>"+Total_city_gs+"</td><td align='right'><b>"+Total_sub_do+"</td><td align='right'><b>"+Total_sub_no+"</td><td align='right'><b>"+Total_sub_gs+"</td>" +
//																		"<td align='right'><b>"+Total_do+"</td><td align='right'><b>"+Total_no+"</td><td align='right'><b>"+Total_gs+"</td><td align='right'><b>"+Total_ns+"</td><td align='right'><b>"+Total_double+"</td><td align='right'><b>"+Total_single+"</td></tr>" +"\n";  
//				
//				 regionTypeAjaxString += "</table></div> </div>  ";
		 
				 
//	          }
	
	       
			
		 
		 result.put("aaData", array);
		System.out.println("Result==="+result);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return result;
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public String getModifiedSchedule(){
		JSONObject result = new JSONObject();
		try {
			CollectionReportDAO dao=new CollectionReportDAO();
			
//			String date1=dao.getDateFromPickerDate(startdate);
//			String date2=dao.getDateFromPickerDate(enddate);
			

			
			Common common = new Common();
//			String orgname=dao.getOrgName();
//			String depot=dao.getDepotName();
			System.out.println("startdate====="+startdate);
			String date=dao.getDateFromPickerDate(startdate);

			
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
			
			// transaction = session1.beginTransaction();
				System.out.println(req.getSession().getAttribute("orgtypeid")+"------"+req.getSession().getAttribute("orgchartid"));
				 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
			//=============
		         String qry="";
		         VtsDataDAO vvt = VtsDataDAO.getInstance();
		     	if(orgtypeid.equals("2")){
		
				qry=" oc.parent_id="+orgchartid;
				//Ends..
		        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
					qry=" org_type_id=3";

				//schedulelist = vvt.getScheduleName();
		        }else{
		   
					qry=" org_type_id="+orgtypeid+" and org_chart_id="+orgchartid;
		        }
		         //=================
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");

		         String sql=" select depot_id,depot_name,sum(total) total,sum(modified) modified from (  "+
					   " select depot_id,depot_name,count(DISTINCT schedule_no) total,0 as modified from daily_schedule_mapping_vehicle "+ 
					   " where operated_date='"+date+"' group by depot_id "+
					   " union   " +
					   " select depot_id,depot_name,0 total,count(DISTINCT schedule_no ) as modified from daily_schedule_mapping_vehicle  "+
					   " where updated_date like '%"+date+"%' group by depot_id ) a " +
					   		"inner join org_chart oc on a.depot_id=oc.org_chart_id " +
					   		"where "+qry+" " +
					   		" group by depot_name ";
			 
			 Query query = session1.createSQLQuery(sql).addScalar("depot_id").addScalar("depot_name").addScalar("total").addScalar("modified");
//			  
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String,Object>> aliasToValueMapList = query.list();
				
			    
				
				
//				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br>SCHEDULE VEHICLE COMPARISON REPORT </h4></div>";
////				//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		        

		       

//		        regionTypeAjaxString += "<tr><th>Sl.NO</th><th>Depot Name</th><th>Schedule No.</th><th>Shift Type</th><th>Vehicle No(As per Schedule Mapping)</th><th>Vehicle No(As per Daily Schedule Mapping)</th><th>Updated Date</th></tr>";

				 
//				
		        
//		        if(aliasToValueMapList.size()==0){
//		          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
//		               regionTypeAjaxString += "<tr>";
//		               regionTypeAjaxString += "<td colspan='4' align='center'><b>No Result Found</b></td>";
//		              
//		               regionTypeAjaxString += "</tr>";
//		          	
//		          }else{
//
//		        	  regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			  			regionTypeAjaxString +="<thead><tr><th>Sl.NO</th><th>Depot Name</th><th>Total Sch.</th><th>Modified Sch.</th>" +
//			  					"</tr></thead><tbody>";
				JSONArray array = new JSONArray();

			     for (int i = 0; i < aliasToValueMapList.size(); i++) {
//			    	 regionTypeAjaxString +="<tr>";
				Map<String,Object> list = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
	 		int j=i+1;
				
//	 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
////				regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
//				regionTypeAjaxString +="<td align='right'>"+list.get("depot_name").toString()+"</td>";
//				regionTypeAjaxString +="<td align='right'>"+ list.get("total").toString() +"</td>";
//				regionTypeAjaxString +="<td align='right'><a data-toggle='modal' class='btn blue' role='button' href='#mymodal11' onclick=javascript:jsFunctionForComparison('"+startdate+"','"+list.get("depot_id").toString()+"');>"+list.get("modified").toString()+"</a>"+"</td>";

//				regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+startdate+"','"
//		                        +list.get("depot_id").toString()+"');>"+list.get("modified").toString()+"</a>"+"<input> type='hidden' name='date' id='date' value="+startdate+"</input>"+"</td>";

	 		ja.add(j);
	 		ja.add(list.get("depot_name").toString());
			 ja.add(list.get("total").toString());
//			 ja.add(list.get("modified").toString());
//			 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal11'  onclick=javascript:jsFunctionForComparison('"+startdate+"','"+list.get("depot_id").toString()+"'</a>");
			 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal11'  onclick=javascript:jsFunctionForComparison('"+startdate+"','"+list.get("depot_id").toString()+"') title='Vehicle Details'>"+list.get("modified").toString() + "</a>");
//			 ja.add(list.get("Vehicle_no").toString());
//			 ja.add(list.get("vehicle_no").toString());
//			 ja.add(list.get("updated_date").toString());
					
			 array.add(ja);
//				   regionTypeAjaxString +="</tr>";
			}
//					regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_city_ord+"</td>"+"<td align='right'><b>"+ Total_city_vaj+"</td><td align='right'><b>"+ Total_city_cor+"</td><td align='right'><b>"+Total_city+"</td>" +
//							"<td align='right'><b>"+Total_sub_ord+"</td><td align='right'><b>"+Total_sub_cor+"</td><td align='right'><b>"+Total_sub_vaj+"</td><td align='right'><b>"+Total_sub_vvaj+"</td><td align='right'><b>"+Total_sub+"</td>" +
//									"<td align='right'><b>"+Total_sch+"</td><td align='right'><b>"+Total_city_ord_do+"</td><td align='right'><b>"+Total_city_ord_no+"</td><td align='right'><b>"+Total_city_ord_gs+"</td><td align='right'><b>"+Total_sub_ord_do+"</td><td align='right'><b>"+Total_sub_ord_no+"</td>" +
//											"<td align='right'><b>"+Total_sub_ord_gs+"</td><td align='right'><b>"+Total_city_cor_do+"</td><td align='right'><b>"+Total_city_cor_no+"</td><td align='right'><b>"+Total_city_cor_gs+"</td><td align='right'><b>"+Total_sub_cor_do+"</td><td align='right'><b>"+Total_sub_cor_no+"</td><td align='right'><b>"+Total_sub_cor_gs+"</td>" +
//													"<td align='right'><b>"+Total_city_vaj_do+"</td><td align='right'><b>"+Total_city_vaj_no+"</td><td align='right'><b>"+Total_city_vaj_gs+"</td><td align='right'><b>"+Total_sub_vaj_do+"</td><td align='right'><b>"+Total_sub_vaj_no+"</td><td align='right'><b>"+Total_sub_vaj_gs+"</td>" +
//															"<td align='right'><b>"+Total_city_vvaj_do+"</td><td align='right'><b>"+Total_city_vvaj_no+"</td><td align='right'><b>"+Total_city_vvaj_gs+"</td><td align='right'><b>"+Total_sub_vvaj_do+"</td><td align='right'><b>"+Total_sub_vvaj_no+"</td><td align='right'><b>"+Total_sub_vvaj_gs+"</td>" +
//																	"<td align='right'><b>"+Total_city_do+"</td><td align='right'><b>"+Total_city_no+"</td><td align='right'><b>"+Total_city_gs+"</td><td align='right'><b>"+Total_sub_do+"</td><td align='right'><b>"+Total_sub_no+"</td><td align='right'><b>"+Total_sub_gs+"</td>" +
//																			"<td align='right'><b>"+Total_do+"</td><td align='right'><b>"+Total_no+"</td><td align='right'><b>"+Total_gs+"</td><td align='right'><b>"+Total_ns+"</td><td align='right'><b>"+Total_double+"</td><td align='right'><b>"+Total_single+"</td></tr>" +"\n";  
//					
//					 regionTypeAjaxString += "</table></div> </div>  ";
			 
					 
//		          }
		
//			HttpServletResponse response = ServletActionContext.getResponse();
//			PrintWriter out;
//				out = response.getWriter();
//				out.print(regionTypeAjaxString);
			     
			     result.put("aaData", array);

					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
						out = response.getWriter();
						out.print(result);
				System.out.println("Result==="+result);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		
		return null;
	}
	
	
public String getComparisonData() throws IOException{
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		PrintWriter out = null;
		ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
		JSONObject result = new JSONObject();
			
			String startDate=request.getParameter("startdate").toString();
			String depot_id=request.getParameter("depotid").toString();
	
			Common cm = new Common();
//			String formattedgivendate = cm.getDateFromPicker(fromdate);
			try{
				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				out = response.getWriter();
				result = getComparisonReport(startDate,depot_id);
				System.out.println("data is==");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
		}

}
	
