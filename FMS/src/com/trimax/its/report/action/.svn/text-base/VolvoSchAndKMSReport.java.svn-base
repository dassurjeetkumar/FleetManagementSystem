package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class VolvoSchAndKMSReport extends ActionSupport{
	
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
	
	public String execute() {
		
		return "success";
	}
	
	public String getVolvoSchAndKMSReport(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot=dao.getDepotName();
		
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
		 
		 String sql="select org_name, sum(vschedules),sum(vbrand_type_id),sum(vtotal_km),sum(vno_of_trips),sum(vvschedules), "+
				 	" sum(vvbrandtype),sum(vvtotal_km), sum(vvno_of_trips), (sum(vschedules)+sum(vvschedules)) totschds,  "+
				    " (sum(vvtotal_km)+sum(vtotal_km)) totkms, (sum(vno_of_trips)+sum(vvno_of_trips)) tot_no_of_trips, "+
				    " ifnull(sum(vtotal_km)/sum(vschedules),'0') vajraveuti, ifnull(sum(vvtotal_km)/sum(vvschedules),'0') vayuveuti, "+
				 	" ifnull((sum(vvtotal_km)+sum(vtotal_km))/(sum(vvschedules)+sum(vschedules)),'0') totveuti  "+
				    " from (select org_name,count(ff.schedule_number_id) vschedules,bt.brand_type_id vbrand_type_id,sum(total_km) vtotal_km, sum(ff.no_of_trips) vno_of_trips, "+
				 	" 0 as vvschedules,0 as vvbrandtype,0 as vvtotal_km,0 as vvno_of_trips from form_four ff  "+
				    " inner join schedule s on s.schedule_id=ff.schedule_number_id  "+
				 	" inner join brand_type bt on bt.brand_type_id=s.brand_type_id  "+
				 	" inner join org_chart oc on oc.org_chart_id=s.depot_id and parent_id=5  "+
				 	" where bt.brand_type_id='8' and ff.deleted_status='0' and ff.current_status='ACTIVE' "+
				    " and s.status='NEW' and s.current_status='CASE WORKER' and (ff.effective_end_date IS NULL or ff.effective_end_date >='2016-07-06')  group by org_name  "+
				 	" union all  select org_name,0 as vschedules,0 as vbrandtype,0 as vtotal_km,0 as vno_of_trips, "+
				 	" count(ff.schedule_number_id) vvschedules, bt.brand_type_id vvbrandtype,sum(total_km) vvtotal_km, "+
				 	" sum(ff.no_of_trips) vvno_of_trips from form_four ff  inner join schedule s on s.schedule_id=ff.schedule_number_id  "+ 
				 	" inner join brand_type bt on bt.brand_type_id=s.brand_type_id  "+
				 	" inner join org_chart oc on oc.org_chart_id=s.depot_id and parent_id=5   "+
				 	" where bt.brand_type_id='9' and ff.deleted_status='0' and ff.current_status='ACTIVE' "+ 
				 	" and s.status='NEW' and s.current_status='CASE WORKER' and (ff.effective_end_date IS NULL or ff.effective_end_date >='"+date1+"')  group by org_name) b group by org_name" ;
		 
		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totalvalues=0.0;
			double TotalV_Sch=0.0;
		    double TotalVV_Sch=0.0;
		    double Total_Sch=0.0;
		    double TotalV_kms=0.0;
		    double TotalVV_Kms=0.0;
		    double Total_Kms=0.0;
		    double TotalV_Trip=0.0;
		    double TotalVV_Trip=0.0;
		    double Total_Trip=0.0;
		    double TotalV_Uti=0.0;
		    double TotalVV_Uti=0.0;
		    double Total_Uti=0.0;
		    
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th>Depot</th><th>Vajra</th><th>Vayu Vajra</th><th>Total</th><th>Vajra</th><th>Vayu Vajra</th><th>Total</th><th>Vajra</th><th>Vayu Vajra</th><th>Total</th><th>Vajra</th><th>Vayu Vajra</th><th>Total</th></tr>" +
					      "<tr><th>No</th><th>Sch's</th><th>Sch's</th><th>Sch's</th><th>Kms</th><th>Kms</th><th>KMS</th><th>Trip</th><th>Trip</th><th>Trip</th><th>Ve.Uti.</th><th>Ve.Uti.</th><th>Ve.Uti.</th> "+
					"</tr>";
//			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//					"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//					"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//					"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//					"</thead><tbody>";
			 
//			
		
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='13' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{
                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vschedules)").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvschedules)").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("totschds").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vtotal_km)").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvtotal_km)").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("totkms").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vno_of_trips)").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvno_of_trips)").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_no_of_trips").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vajraveuti").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("vayuveuti").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("totveuti").toString()+"</td>";
			
			
//			String  date = list.get("Date").toString();
//			String etim = list.get("ETIMServiceTax").toString();
//			String bag = list.get("BagServiceTax").toString();
//			String pass = list.get("PassServiceTax").toString();
//			Totalvalues=Double.parseDouble(etim)+Double.parseDouble(bag)+Double.parseDouble(pass);
			
			TotalV_Sch+=Double.parseDouble(list.get("sum(vschedules)").toString());
			TotalVV_Sch+=Double.parseDouble(list.get("sum(vvschedules)").toString());
			Total_Sch+=Double.parseDouble(list.get("totschds").toString());
			TotalV_kms+=Double.parseDouble(list.get("sum(vtotal_km)").toString());
			TotalVV_Kms+=Double.parseDouble(list.get("sum(vvtotal_km)").toString());
			Total_Kms+=Double.parseDouble(list.get("totkms").toString());
			TotalV_Trip+=Double.parseDouble(list.get("sum(vno_of_trips)").toString());
			TotalVV_Trip+=Double.parseDouble(list.get("sum(vvno_of_trips)").toString());
			Total_Trip+=Double.parseDouble(list.get("tot_no_of_trips").toString());
			TotalV_Uti+=Double.parseDouble(list.get("vajraveuti").toString());
			TotalVV_Uti+=Double.parseDouble(list.get("vayuveuti").toString());
			Total_Uti+=Double.parseDouble(list.get("totveuti").toString());
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			

			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
						"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
								"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
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
	
	
	public String getVolvoSchAndKMSDataReport(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname="";
				//dao.getOrgName();
		String depot="";
				//dao.getDepotName();
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	    SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
	    String cur_date = sdf1.format(ss1);
	    
	    String date=dao.getDateFromPickerDate(cur_date);
		
		
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select org_name,sum(Generalshift) GS,sum(DayOut) DO,sum(NightOut) NO,sum(NightService) NS,sum(SplitService) SS from "+
				    " (SELECT org_name,"+
						 " (case when s.schedule_type='1' THEN  count(*) ELSE 0 END) Generalshift,"+
						 " (case when s.schedule_type='3' THEN  count(*) ELSE 0 END) DayOut,"+
						 " (case when s.schedule_type='2' THEN  count(*) ELSE 0 END) NightOut, "+
						 " (case when s.schedule_type='4' THEN  count(*) ELSE 0 END) NightService,"+
						 " (case when s.schedule_type='11' THEN  count(*) ELSE 0 END) SplitService "+
						 " FROM `schedule` s "+
						 " inner join org_chart oc on oc.org_chart_id=s.depot_id and parent_id=5 "+
						 " inner join schedule_type st on st.schedule_type_id=s.schedule_type "+
						 " WHERE s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND "+
						 " (`effective_end_date` IS NULL or effective_end_date>='"+date1+"') group by org_name,schedule_type_name)a group by org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("GS").addScalar("DO").addScalar("NO").addScalar("NS").addScalar("SS");
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totalvalues=0.0;
			double Total_DO=0.0;
		    double Total_NO=0.0;
		    double Total_GS=0.0;
		    double Total_NS=0.0;
		    double Total_SS=0.0;
		    double G_total=0.0;
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th>Depot No</th><th>DO</th><th>NO</th><th>GS</th><th>NS</th><th>Total</th></tr>";


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
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("DO").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("NO").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("GS").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("NS").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("SS").toString()+"</td>";
			
			

			Totalvalues=Double.parseDouble(list.get("DO").toString())+Double.parseDouble(list.get("NO").toString())+Double.parseDouble(list.get("GS").toString())+Double.parseDouble(list.get("NS").toString());
			
			Total_DO+=Double.parseDouble(list.get("DO").toString());
			Total_NO+=Double.parseDouble(list.get("NO").toString());
			Total_GS+=Double.parseDouble(list.get("GS").toString());
			Total_NS+=Double.parseDouble(list.get("NS").toString());
//			Total_SS+=Double.parseDouble(list.get("SS").toString());
			G_total+=Totalvalues;
			
			
			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
			
			

			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_DO+"</td>"+"<td align='right'><b>"+ Total_NO+"</td><td align='right'><b>"+ Total_GS+"</td><td align='right'><b>"+ Total_NS+"</td><td align='right'><b>"+ G_total+"</td></tr>" +"\n";  
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
	
public String getCoronaServiceReport(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
		String orgname=dao.getOrgName();
		String depot="";
				//dao.getDepotName();
		
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
		 
		 String sql="Select SUM(CASE WHEN service_limit='City' THEN sub_sch ELSE 0 END) City_sch, "+
				   " SUM(CASE WHEN service_limit='City' THEN sub_dista ELSE 0 END) City_dista, "+
				   " SUM(CASE WHEN service_limit='City' THEN sub_trips ELSE 0 END) City_trip, "+
				   " SUM(CASE WHEN service_limit='Suburban' THEN sub_sch ELSE 0 END) Sub_sch, "+
				   " SUM(CASE WHEN service_limit='Suburban' THEN sub_dista ELSE 0 END) Sub_dista, "+
				   " SUM(CASE WHEN service_limit='Suburban' THEN sub_trips ELSE 0 END) Sub_trip,org_name from( "+
				   " select sum(sub_sch), sub_sch,round(sum(sub_dista),2) sub_dista,sum(sub_trips) sub_trips,service_limit,org_name from( "+
				   " select sl.schedule_id,sub_sch,sub_dista,sub_trips,service_limit,org_name from schedule_service_limit sl "+
				   " inner join schedule s on s.schedule_id=sl.schedule_id and brand_type_id='12' "+
				   " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
				   " inner join schedule_details sd on sd.form_four_id=ff.form_four_id "+
				   " inner join org_chart oc on oc.org_chart_id=s.depot_id and parent_id='5' "+
				   " inner join (SELECT count(schedule_number_name) sub_sch,ifnull(sum(distance),'0.0')  as sub_dista,max(trip_number) sub_trips,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id "+
				   " inner join schedule s1 on s1.schedule_id=ff1.schedule_number_id "+
				   " inner join schedule_service_limit sl1 on sl1.schedule_id=s1.schedule_id WHERE trip_type='2' "+
				   " group by ff1.form_four_id) b on b.form_four_id=ff.form_four_id "+
				   " where  ff.deleted_status='0' and ff.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (ff.effective_end_date IS NULL or ff.effective_end_date >='"+date1+"')  ) c group by c.service_limit ) d group by d.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("City_sch").addScalar("City_dista").addScalar("City_trip").addScalar("Sub_sch").addScalar("Sub_dista").addScalar("Sub_trip").addScalar("org_name");
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
           
			double Total_city_sch=0.0;
			double Total_sub_sch=0.0;
			double Total_city_trip=0.0;
			double Total_sub_trip=0.0;
			double Total_city_kms=0.0;
			double Total_sub_kms=0.0;
			double Total_schs=0.0;
			double Total_trips=0.0;
			double Total_kmss=0.0;
			double Total_city_uti=0.0;
			double Total_sub_uti=0.0;
			double Total_uti=0.0;
			String Total_city_uti1="";
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>CORONA SERVICE DETAILS  AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	        
	       

	        regionTypeAjaxString += "<tr><th>Depot</th><th>CORONA</th><th>CORONA</th><th>Total</th><th>CRN</th><th>CRN</th><th>Total</th><th>CORONA</th><th>CORONA</th><th>Total</th><th>City</th><th>Sub</th><th>Total</th></tr>" +
					      "<tr><th>No</th><th> City Sch's</th><th>Sub Sch's</th><th>Sch's</th><th>City Trip</th><th>Sub Trip</th><th>Trip</th><th>City KMS</th><th>Sub KMS</th><th>KMS</th><th>Ve.Uti.</th><th>Ve.Uti.</th><th>Ve.Uti.</th> "+
					"</tr>";
//			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//					"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//					"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//					"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//					"</thead><tbody>";
			 

	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='13' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{
	        
                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("City_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("Sub_sch").toString() +"</td>";
			double total_sch=Double.parseDouble(list.get("City_sch").toString())+Double.parseDouble(list.get("Sub_sch").toString());
			regionTypeAjaxString +="<td align='right'>"+total_sch+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("City_trip").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("Sub_trip").toString() +"</td>";
			double total_trip=Double.parseDouble(list.get("City_trip").toString())+Double.parseDouble(list.get("Sub_trip").toString());
			regionTypeAjaxString +="<td align='right'>"+total_trip+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("City_dista").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("Sub_dista").toString() +"</td>";
			double total_dista=Double.parseDouble(list.get("City_dista").toString())+Double.parseDouble(list.get("Sub_dista").toString());
			regionTypeAjaxString +="<td align='right'>"+total_dista+"</td>";
			double city_uti=0.0;
			if(Integer.parseInt(list.get("City_sch").toString())==0){
				city_uti=0.0;
			}else{
			 city_uti=Double.parseDouble(list.get("City_dista").toString())/Double.parseDouble(list.get("City_sch").toString());
			}
			double sub_uti=0.0;
			if(Integer.parseInt(list.get("Sub_sch").toString())==0){
				sub_uti=0.0;
			}else{
				 sub_uti=Double.parseDouble(list.get("Sub_dista").toString())/Double.parseDouble(list.get("Sub_sch").toString());

			}
			
			double total_uti=city_uti+sub_uti;
			String city_uti1=String.format("%.2f", city_uti);
			String sub_uti1=String.format("%.2f", sub_uti);
			String total_uti1=String.format("%.2f", total_uti);
			regionTypeAjaxString +="<td align='right'>"+city_uti1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+sub_uti1+"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_uti1+"</td>";
              
			
			Total_city_sch+=Double.parseDouble(list.get("City_sch").toString());
			Total_sub_sch+=Double.parseDouble(list.get("Sub_sch").toString());
			Total_schs+=total_sch;
			Total_city_trip+=Double.parseDouble(list.get("City_trip").toString());
			Total_sub_trip+=Double.parseDouble(list.get("Sub_trip").toString());
			Total_trips+=total_trip;
			Total_city_kms+=Double.parseDouble(list.get("City_dista").toString());
			Total_sub_kms+=Double.parseDouble(list.get("Sub_dista").toString());
			Total_kmss+=total_dista;
			Total_city_uti+=city_uti;
			Total_city_uti1=String.format("%.2f", Total_city_uti);
			Total_sub_uti+=sub_uti;
			Total_uti+=total_uti;
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
			
			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_city_sch+"</td>"+"<td align='right'><b>"+ Total_sub_sch+"</td><td align='right'><b>"+ Total_schs+"</td>" +
						"<td align='right'><b>"+ Total_city_trip+"</td><td align='right'><b>"+ Total_sub_trip+"</td><td align='right'><b>"+ Total_trips+"</td><td align='right'><b>"+Total_city_kms+"</td>" +
								"<td align='right'><b>"+Total_sub_kms+"</td><td align='right'><b>"+Total_kmss+"</td><td align='right'><b>"+Total_city_uti1+"</td><td align='right'><b>"+Total_sub_uti+"</td><td align='right'><b>"+Total_uti+"</td></tr>" +"\n";  
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
	

public String getCoronaServiceDetailReport(){
	
	try {
	CollectionReportDAO dao=new CollectionReportDAO();
//	System.out.println("Depot Name==="+depotName);
	String date1=dao.getDateFromPickerDate(startdate);
//	String date2=dao.getDateFromPickerDate(enddate);
	Common common = new Common();
	String orgname=dao.getOrgName();
	String depot="";
			//dao.getDepotName();
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="select org_name,sum(Generalshift) GS,sum(DayOut) DO,sum(NightOut) NO,sum(NightService) NS,sum(SplitService) SS, (ot1+ot2) ot "+
			    " from  (SELECT org_name, (case when s.schedule_type='1' THEN  count(*) ELSE 0 END) Generalshift, (case when s.schedule_type='3' THEN  count(*) ELSE 0 END) DayOut, (case when s.schedule_type='2' THEN  count(*) ELSE 0 END) NightOut,  (case when s.schedule_type='4' THEN  count(*) ELSE 0 END) NightService, (case when s.schedule_type='11' THEN  count(*) ELSE 0 END) SplitService,TIME_TO_SEC(ot1) ot1,TIME_TO_SEC(ot2) ot2  "+
			    " FROM `schedule` s  inner join org_chart oc on oc.org_chart_id=s.depot_id and parent_id=5  "+
			    " inner join schedule_type st on st.schedule_type_id=s.schedule_type  "+
			    " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
			    " WHERE s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' AND (s.effective_end_date IS NULL or s.effective_end_date>='"+date1+"') group by org_name,schedule_type_name)a group by org_name";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("GS").addScalar("DO").addScalar("NO").addScalar("NS").addScalar("SS").addScalar("ot");
  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
       int Total_DO=0;
       int Total_NO=0;
       int Total_GS=0;
       int G_Total=0;
       int Total_hrs=0;
       int Total_min=0;
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>"+depot+" </br>Service Tax Collection Report";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        
       

        regionTypeAjaxString += "<tr><th>Depot No</th><th>DO</th><th>NO</th><th>GS</th><th>Total</th><th>OT MIN</th><th>OT HOURs</th></tr>";

        if(aliasToValueMapList.size()==0){
          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
               regionTypeAjaxString += "<tr>";
               regionTypeAjaxString += "<td colspan='7' align='center'><b>No Result Found</b></td>";
              
               regionTypeAjaxString += "</tr>";
          	
          }else{
        
        
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
		
// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("DO").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("NO").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("GS").toString()+"</td>";
		int total=Integer.parseInt(list.get("DO").toString())+Integer.parseInt(list.get("NO").toString())+Integer.parseInt(list.get("GS").toString());
		regionTypeAjaxString +="<td align='right'>"+total+"</td>";
		int sec=Integer.parseInt(list.get("ot").toString());
		int hours = sec / 3600;
        int minutes = (sec%3600)/60;
		regionTypeAjaxString +="<td align='right'>"+minutes+"</td>";
		regionTypeAjaxString +="<td align='right'>"+hours+"</td>";
		
		Total_DO+=Integer.parseInt(list.get("DO").toString());
		Total_NO+=Integer.parseInt(list.get("NO").toString());
		Total_GS+=Integer.parseInt(list.get("GS").toString());
		G_Total+=total;
		Total_hrs+=hours;
		Total_min+=minutes;
		   regionTypeAjaxString +="</tr>";
	}

	     regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+Total_DO+"</td>"+"<td align='right'><b>"+Total_NO+"</td><td align='right'><b>"+ Total_GS+"</td>" +
					"<td align='right'><b>"+ G_Total+"</td><td align='right'><b>"+ Total_min+"</td><td align='right'><b>"+ Total_hrs+"</td></tr>" +"\n";  
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
