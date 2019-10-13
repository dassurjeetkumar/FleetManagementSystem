package com.trimax.its.report.action;

import java.io.FileOutputStream;
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

public class VolvoDivisionReport extends ActionSupport {

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

	String regionTypeAjaxString = "";
	
	public String execute() {
		
		return "success";
	}
	
	public String getVolvoDivisionForm_vData(){
		

		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
//		String date1=dao.getDateFromPickerDate(startdate);
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
	    
	    String dates[]=singledate.split("-");
		String month=dates[0];
		int year=Integer.parseInt(dates[1]);
		int select_month=Integer.parseInt(month);
		int curr_month=Calendar.getInstance().get(Calendar.MONTH);
		int curr_month1=curr_month+1;
	    
		 String date1="";
		    if(curr_month1==select_month){
		    	Date curr_date=new Date();
		    	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		    	date1=dateformat.format(curr_date);
		    }else{
		    	Calendar cal = new GregorianCalendar(year, select_month, 0);
				Date date = cal.getTime();
				DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				date1=sdf2.format(date);
		    }
	   
	   
		
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select SUM(CASE WHEN service_limit='City' THEN cor_sch ELSE 0 END) city_cor_sch, "+
				   " SUM(CASE WHEN service_limit='City' THEN vol_sch ELSE 0 END) city_vol_sch, "+
				   " SUM(CASE WHEN service_limit='Suburban' THEN cor_sch ELSE 0 END) sub_cor_sch, "+
				   " SUM(CASE WHEN service_limit='Suburban' THEN vol_sch ELSE 0 END) sub_vol_sch,service_limit,org_name from( "+
				   " select sum(cor_sch) cor_sch,ifnull(sum(vol_sch),0) vol_sch,service_limit,org_name from( "+
				   " select s.schedule_id,cor_sch,vol_sch,service_limit,org_name from schedule s "+
				   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
				   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
				   " inner join org_chart oc on oc.org_chart_id=s.depot_id and parent_id='5' "+
				   " inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
				   " inner join (SELECT count(schedule_details.schedule_number) as cor_sch,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id "+
				   " inner join schedule s1 on s1.schedule_id=ff1.schedule_number_id "+
				   " inner join brand_type bt1 on bt1.brand_type_id=s1.brand_type_id "+
				   " WHERE trip_type='2' and s1.brand_type_id='12' "+
				   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
				   " left join (SELECT ifnull(count(schedule_details.schedule_number),0) as vol_sch,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id "+
				   " inner join schedule s1 on s1.schedule_id=ff1.schedule_number_id "+
				   " inner join brand_type bt1 on bt1.brand_type_id=s1.brand_type_id "+
				   " WHERE trip_type='2' and s1.brand_type_id in ('8','9') "+
				   " group by ff1.form_four_id) b on b.form_four_id=f.form_four_id "+
				   " where f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"')) c group by c.service_limit ) d group by d.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("city_cor_sch").addScalar("city_vol_sch").addScalar("sub_cor_sch").addScalar("sub_vol_sch").addScalar("service_limit").addScalar("org_name");
////		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Total_city_cor=0.0;
			double Total_city_vol=0.0;
			double Total_city=0.0;
			double Total_sub_cor=0.0;
			double Total_sub_vol=0.0;
			double Total_sub=0.0;
			double G_Total=0.0;

		  
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO DIVISION ";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       
	        regionTypeAjaxString += "<tr><th colspan='8' align='center'>CLASSIFICATION OF SCHEDULES</th></tr>" +
	        		"<tr><th></th><th colspan='3' align='center'>CITY SCHEDULES</th><th colspan='3' align='center'>SUB-URBAN SCHEDULES</th><th>Total</th></tr>" +
	        		"<tr><th>Depot</th><th>CORONA</th><th>VOLVO</th><th>TOTAL</th><th>CORONA</th><th>VOLVO</th><th>TOTAL</th><th>SCH</th></tr>";
	        
	        
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='47' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{		
	      

                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vol_sch").toString() +"</td>";
			double total_city_sch=Double.parseDouble(list.get("city_cor_sch").toString())+Double.parseDouble(list.get("city_vol_sch").toString());
			
			regionTypeAjaxString +="<td align='right'>"+ total_city_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vol_sch").toString() +"</td>";
			double total_sub_sch=Double.parseDouble(list.get("sub_cor_sch").toString())+Double.parseDouble(list.get("sub_vol_sch").toString());

			regionTypeAjaxString +="<td align='right'>"+ total_sub_sch +"</td>";
			double total_sch=total_city_sch+total_sub_sch;
			regionTypeAjaxString +="<td align='right'>"+ total_sch +"</td>";
			
			Total_city_cor+=Double.parseDouble(list.get("city_cor_sch").toString());
			Total_city_vol+=Double.parseDouble(list.get("city_vol_sch").toString());
			Total_city+=total_city_sch;
			Total_sub_cor+=Double.parseDouble(list.get("sub_cor_sch").toString());
			Total_sub_vol+=Double.parseDouble(list.get("sub_vol_sch").toString());
			Total_sub+=total_sub_sch;
			G_Total+=total_sch;
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			

			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_city_cor+"</td>"+"<td align='right'><b>"+ Total_city_vol+"</td><td align='right'><b>"+ Total_city+"</td><td align='right'><b>"+Total_sub_cor+"</td>" +
						"<td align='right'><b>"+Total_sub_vol+"</td><td align='right'><b>"+Total_sub+"</td><td align='right'><b>"+G_Total+"</td></tr>" +"\n";  
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
	
public String getVolvoDivisionForm_vData1(){
		

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
	    
	    String dates[]=singledate.split("-");
		String month=dates[0];
		int year=Integer.parseInt(dates[1]);
		int select_month=Integer.parseInt(month);
		int curr_month=Calendar.getInstance().get(Calendar.MONTH);
		int curr_month1=curr_month+1;
	    
		 String date1="";
		    if(curr_month1==select_month){
		    	Date curr_date=new Date();
		    	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		    	date1=dateformat.format(curr_date);
		    }else{
		    	Calendar cal = new GregorianCalendar(year, select_month, 0);
				Date date = cal.getTime();
				DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				date1=sdf2.format(date);
		    }
	   
		
	
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select SUM(CASE WHEN brand_type_name='Corona AC' THEN city_day_sch ELSE 0 END) cor_city_day_sch, "+ 
				  	" SUM(CASE WHEN brand_type_name='Corona AC' THEN city_night_sch ELSE 0 END) cor_city_night_sch, "+
				    " SUM(CASE WHEN brand_type_name='Corona AC' THEN city_gen_sch ELSE 0 END) cor_city_gen_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Corona AC' THEN sub_day_sch ELSE 0 END) cor_sub_day_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Corona AC' THEN sub_night_sch ELSE 0 END) cor_sub_night_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Corona AC' THEN sub_gen_sch ELSE 0 END) cor_sub_gen_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' THEN city_day_sch ELSE 0 END) vaj_city_day_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' THEN city_night_sch ELSE 0 END) vaj_city_night_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' THEN city_gen_sch ELSE 0 END) vaj_city_gen_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' THEN sub_day_sch ELSE 0 END) vaj_sub_day_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' THEN sub_night_sch ELSE 0 END) vaj_sub_night_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' THEN sub_gen_sch ELSE 0 END) vaj_sub_gen_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN sub_day_sch ELSE 0 END) vvaj_sub_day_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN sub_night_sch ELSE 0 END) vvaj_sub_night_sch, "+
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN sub_gen_sch ELSE 0 END) vvaj_sub_gen_sch, "+
				 	" nservice_sch,service_limit,brand_type_name,org_name from ( "+
				  	" select SUM(CASE WHEN service_limit='City' THEN day_sch ELSE 0 END) city_day_sch, "+
				 	" SUM(CASE WHEN service_limit='City' THEN night_sch ELSE 0 END) city_night_sch, "+
				 	" SUM(CASE WHEN service_limit='City' THEN gen_sch ELSE 0 END) city_gen_sch, "+
				 	" SUM(CASE WHEN service_limit='Suburban' THEN day_sch ELSE 0 END) sub_day_sch, "+
				 	" SUM(CASE WHEN service_limit='Suburban' THEN night_sch ELSE 0 END) sub_night_sch, "+
				 	" SUM(CASE WHEN service_limit='Suburban' THEN gen_sch ELSE 0 END) sub_gen_sch, "+
				 	" nservice_sch,service_limit,brand_type_name,org_name from ( "+
				 	" select ifnull(sum(day_sch),0) day_sch,ifnull(sum(night_sch),0) night_sch,ifnull(sum(gen_sch),0) gen_sch,ifnull(sum(nservice_sch),0) nservice_sch,service_limit,brand_type_name,org_name from ( "+
				 	" select s.schedule_id,day_sch,night_sch,gen_sch,nservice_sch,sl.service_limit,brand_type_name,org_name from schedule s "+
				 	" inner join form_four f on f.schedule_number_id=s.schedule_id "+
				 	" inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='5' "+
				 	" inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				 	" inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
				 	" left join (SELECT schedule_number day_sch,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 	" on ff1.form_four_id=schedule_details.form_four_id "+
				 	" WHERE trip_type='2' and shift_type_id in ('4','5') "+
				 	" group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
				 	" left join (SELECT schedule_number  as night_sch,ff1.form_four_id FROM schedule_details left join form_four ff1  "+
				 	" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id='2' "+
				 	" group by ff1.form_four_id) b on b.form_four_id=f.form_four_id "+
				 	" left join (SELECT schedule_number  as gen_sch,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 	" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id='1' "+
				 	" group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
				 	" left join (SELECT schedule_number  as nservice_sch,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 	" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id='78' "+
				 	" group by ff1.form_four_id) d on d.form_four_id=f.form_four_id "+
				 	" where f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') ) e group by e.service_limit,e.brand_type_name,e.org_name "+
			 		" ) f group by f.service_limit,f.brand_type_name,f.org_name ) g group by g.service_limit,g.brand_type_name,g.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("cor_city_day_sch").addScalar("cor_city_night_sch").addScalar("cor_city_gen_sch").addScalar("cor_sub_day_sch").addScalar("cor_sub_night_sch").addScalar("cor_sub_gen_sch").addScalar("vaj_city_day_sch").addScalar("vaj_city_night_sch").addScalar("vaj_city_gen_sch").addScalar("vaj_sub_day_sch").addScalar("vaj_sub_night_sch").addScalar("vaj_sub_gen_sch")
				.addScalar("vvaj_sub_day_sch").addScalar("vvaj_sub_night_sch").addScalar("vvaj_sub_gen_sch").addScalar("nservice_sch").addScalar("service_limit").addScalar("brand_type_name").addScalar("org_name");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Total_city_day=0.0;
			double Total_city_night=0.0;
			double Total_city_gen=0.0;
			double Total_sub_day=0.0;
			double Total_sub_night=0.0;
			double Total_sub_gen=0.0;
			double Total_Vcity_day=0.0;
			double Total_Vcity_night=0.0;
			double Total_Vcity_gen=0.0;
			double Total_Vsub_day=0.0;
			double Total_Vsub_night=0.0;
			double Total_Vsub_gen=0.0;
			double Total_VVsub_day=0.0;
			double Total_VVsub_night=0.0;
			double Total_VVsub_gen=0.0;
			double GTotal_city_day=0.0;
			double GTotal_city_night=0.0;
			double GTotal_city_gen=0.0;
			double GTotal_sub_day=0.0;
			double GTotal_sub_night=0.0;
			double GTotal_sub_gen=0.0;
			double Total_day=0.0;
			double Total_night=0.0;
			double Total_gen=0.0;
			double Total_nightService=0.0;
			double Total_single=0.0;
			double Total_double=0.0;
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>FORM V FOR THE MONTH OF FEBRUARY ";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th></th><th colspan='6' align='center'>CORONA</th><th colspan='6' align='center'>VAJRA</th><th colspan='3' align='center'>VAYU VAJRA</th><th colspan='6' align='center'>TOTAL BMTC SCHEDULES</th><th colspan='3' align='center'>CITY+SUBURBAN</th><th></th><th colspan='2' align='center'>TYPE OF CREWS</th></tr>" +
	        		"<tr><th>Depot</th><th colspan='3' align='center'>CITY SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>CITY SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>CITY SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>TOTAL BMTC SCHS</th><th>N/S</th><th>DOUBLE</th><th>SINGLE</th></tr>" +
	        		"<tr><th>No</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th></th><th></th><th></th>";


	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='47' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{		

                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("cor_city_day_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("cor_city_night_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("cor_city_gen_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("cor_sub_day_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("cor_sub_night_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("cor_sub_gen_sch").toString() +"</td>";
			
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("vaj_city_day_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vaj_city_night_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vaj_city_gen_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vaj_sub_day_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vaj_sub_night_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vaj_sub_gen_sch").toString() +"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("vvaj_sub_day_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vvaj_sub_night_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("vvaj_sub_gen_sch").toString() +"</td>";
			double total_city_day_sch=Double.parseDouble(list.get("cor_city_day_sch").toString())+Double.parseDouble(list.get("vaj_city_day_sch").toString());
			double total_city_night_sch=Double.parseDouble(list.get("cor_city_day_sch").toString())+Double.parseDouble(list.get("vaj_city_night_sch").toString());
			double total_city_gen_sch=Double.parseDouble(list.get("cor_city_gen_sch").toString())+Double.parseDouble(list.get("vaj_city_gen_sch").toString());
			double total_sub_day_sch=Double.parseDouble(list.get("cor_sub_day_sch").toString())+Double.parseDouble(list.get("vaj_sub_day_sch").toString())+Double.parseDouble(list.get("vvaj_sub_day_sch").toString());
			double total_sub_night_sch=Double.parseDouble(list.get("cor_sub_night_sch").toString())+Double.parseDouble(list.get("vaj_sub_night_sch").toString())+Double.parseDouble(list.get("vvaj_sub_night_sch").toString());
			double total_sub_gen_sch=Double.parseDouble(list.get("cor_sub_gen_sch").toString())+Double.parseDouble(list.get("vaj_sub_gen_sch").toString())+Double.parseDouble(list.get("vvaj_sub_gen_sch").toString());
           
			double total_day_sch=total_city_day_sch+total_sub_day_sch;
			double total_night_sch=total_city_night_sch+total_sub_night_sch;
			double total_gen_sch=total_city_gen_sch+total_sub_gen_sch;
			
			regionTypeAjaxString +="<td align='right'>"+ total_city_day_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_city_night_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_city_gen_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_sub_day_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_sub_night_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_sub_gen_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_day_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_night_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_gen_sch +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("nservice_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_night_sch +"</td>";
			double total=total_day_sch+total_gen_sch;
			regionTypeAjaxString +="<td align='right'>"+ total +"</td>";
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";		
			
			Total_city_day+=Double.parseDouble(list.get("cor_city_day_sch").toString());
			Total_city_night+=Double.parseDouble(list.get("cor_city_night_sch").toString());
			Total_city_gen+=Double.parseDouble(list.get("cor_city_gen_sch").toString());
			Total_sub_day+=Double.parseDouble(list.get("cor_sub_day_sch").toString());
			Total_sub_night+=Double.parseDouble(list.get("cor_sub_night_sch").toString());
			Total_sub_gen+=Double.parseDouble(list.get("cor_sub_gen_sch").toString());
			Total_Vcity_day+=Double.parseDouble(list.get("vaj_city_day_sch").toString());
			Total_Vcity_night+=Double.parseDouble(list.get("vaj_city_night_sch").toString());
			Total_Vcity_gen+=Double.parseDouble(list.get("vaj_city_gen_sch").toString());
			Total_Vsub_day+=Double.parseDouble(list.get("vaj_sub_day_sch").toString());
			Total_Vsub_night+=Double.parseDouble(list.get("vaj_sub_night_sch").toString());
			Total_Vsub_gen+=Double.parseDouble(list.get("vaj_sub_gen_sch").toString());
			Total_VVsub_day+=Double.parseDouble(list.get("vvaj_sub_day_sch").toString());
			Total_VVsub_night+=Double.parseDouble(list.get("vvaj_sub_night_sch").toString());
			Total_VVsub_gen+=Double.parseDouble(list.get("vvaj_sub_gen_sch").toString());
			GTotal_city_day+=total_city_day_sch;
			GTotal_city_night+=total_city_night_sch;
			GTotal_city_gen+=total_city_gen_sch;
			GTotal_sub_day+=total_sub_day_sch;
			GTotal_sub_night+=total_sub_night_sch;
			GTotal_sub_gen+=total_sub_gen_sch;
			Total_day+=total_day_sch;
			Total_night+=total_night_sch;
			Total_gen+=total_gen_sch;
			Total_nightService+=Double.parseDouble(list.get("nservice_sch").toString());
			Total_single+=total_night_sch;
			Total_double+=total;
			   regionTypeAjaxString +="</tr>";
		}

				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_city_day+"</td>"+"<td align='right'><b>"+ Total_city_night+"</td><td align='right'><b>"+ Total_city_gen+"</td><td align='right'><b>"+Total_sub_day+"</td>" +
						"<td align='right'><b>"+Total_sub_night+"</td><td align='right'><b>"+Total_sub_gen+"</td><td align='right'><b>"+Total_Vcity_day+"</td><td align='right'><b>"+Total_Vcity_night+"</td><td align='right'><b>"+Total_Vcity_gen+"</td>" +
								"<td align='right'><b>"+Total_Vsub_day+"</td><td align='right'><b>"+Total_Vsub_night+"</td><td align='right'><b>"+Total_Vsub_gen+"</td>" +
										"<td align='right'><b>"+Total_VVsub_day+"</td><td align='right'><b>"+Total_VVsub_night+"</td><td align='right'><b>"+Total_VVsub_gen+"</td>" +
												"<td align='right'><b>"+GTotal_city_day+"</td><td align='right'><b>"+GTotal_city_night+"</td><td align='right'><b>"+GTotal_city_gen+"</td>" +
														"<td align='right'><b>"+GTotal_sub_day+"</td><td align='right'><b>"+GTotal_sub_night+"</td><td align='right'><b>"+GTotal_sub_gen+"</td>" +
																"<td align='right'><b>"+Total_day+"</td><td align='right'><b>"+Total_night+"</td><td align='right'><b>"+Total_gen+"</td>" +
																		"<td align='right'><b>"+Total_nightService+"</td><td align='right'><b>"+Total_single+"</td><td align='right'><b>"+Total_double+"</td></tr>" +"\n";  
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
	
	
	
	
public String getVolvoDivisionKilometerData(){
		

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
	    
	    String dates[]=singledate.split("-");
		String month=dates[0];
		int year=Integer.parseInt(dates[1]);
		int select_month=Integer.parseInt(month);
		int curr_month=Calendar.getInstance().get(Calendar.MONTH);
		int curr_month1=curr_month+1;
	    
		 String date1="";
		    if(curr_month1==select_month){
		    	Date curr_date=new Date();
		    	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		    	date1=dateformat.format(curr_date);
		    }else{
		    	Calendar cal = new GregorianCalendar(year, select_month, 0);
				Date date = cal.getTime();
				DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				date1=sdf2.format(date);
		    }
		
	
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="Select SUM(CASE WHEN service_limit='City' THEN vaj_dista ELSE 0 END) city_vaj_dista, "+
				 	" SUM(CASE WHEN service_limit='City' THEN vaj_gen_dista ELSE 0 END) city_vaj_gen_dista, "+
				    " SUM(CASE WHEN service_limit='City' THEN vvaj_dista ELSE 0 END) city_vvaj_dista, "+
				    " SUM(CASE WHEN service_limit='City' THEN vvaj_gen_dista ELSE 0 END) city_vvaj_gen_dista, "+
				    " SUM(CASE WHEN service_limit='Suburban' THEN vaj_dista ELSE 0 END) sub_vaj_dista, "+
				    " SUM(CASE WHEN service_limit='Suburban' THEN vaj_gen_dista ELSE 0 END) sub_vaj_gen_dista, "+
				    " SUM(CASE WHEN service_limit='Suburban' THEN vvaj_dista ELSE 0 END) sub_vvaj_dista, "+
				    " SUM(CASE WHEN service_limit='Suburban' THEN vvaj_gen_dista ELSE 0 END) sub_vvaj_gen_dista, "+
				    " SUM(CASE WHEN service_limit='Suburban' THEN cor_dista ELSE 0 END) sub_cor_dista, "+
				    " SUM(CASE WHEN service_limit='Suburban' THEN cor_gen_dista ELSE 0 END) sub_cor_gen_dista, "+
				    " dead_dista,service_limit,brand_type_name,sch,gen_sch,org_name from (  "+
				    " Select SUM(CASE WHEN brand_type_name='Vajra' THEN dista ELSE 0 END) vaj_dista, "+
				    " SUM(CASE WHEN brand_type_name='Vajra' THEN gen_dista ELSE 0 END) vaj_gen_dista,  "+
				    " SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN dista ELSE 0 END) vvaj_dista, "+
				    " SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN gen_dista ELSE 0 END) vvaj_gen_dista, "+
				    " SUM(CASE WHEN brand_type_name='Corona AC' THEN dista ELSE 0 END) cor_dista, "+
				    " SUM(CASE WHEN brand_type_name='Corona AC' THEN gen_dista ELSE 0 END) cor_gen_dista, "+
				    " dead_dista,service_limit,brand_type_name,sch,gen_sch,org_name from ( "+
				 	" Select ifnull(sum(sch),0) sch,ifnull(sum(dista),0) dista,ifnull(sum(gen_sch),0) gen_sch,ifnull(sum(gen_dista),0) gen_dista,ifnull(sum(dead_dista),0) dead_dista,service_limit,brand_type_name,org_name from( "+
				 	" select s.schedule_id,sch,gen_sch,dista,gen_dista,dead_dista,sl.service_limit,brand_type_name,org_name from schedule s "+
				 	" inner join form_four f on f.schedule_number_id=s.schedule_id "+
				 	" inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='5' "+
				 	" inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id  "+
				 	" inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
				 	" left join (SELECT count(schedule_number) sch,ifnull(sum(distance),'0.0')  as dista,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 	" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id in ('2','3','4','5') "+
				 	" group by ff1.form_four_id) a on a.form_four_id=f.form_four_id "+
				 	" left join (SELECT count(schedule_number) gen_sch,ifnull(sum(distance),'0.0')  as gen_dista,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 	" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id='1' "+
				 	" group by ff1.form_four_id) b on b.form_four_id=f.form_four_id "+
				 	" inner join (SELECT ifnull(sum(distance),'0.0')  as dead_dista,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				 	" on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
				 	" group by ff1.form_four_id) c on c.form_four_id=f.form_four_id  "+
				 	" where f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') ) d group by d.service_limit,d.brand_type_name,d.org_name "+
			 		" ) e group by e.service_limit,e.brand_type_name,e.org_name ) f group by f.service_limit,f.brand_type_name,f.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("city_vaj_dista").addScalar("city_vaj_gen_dista").addScalar("city_vvaj_dista").addScalar("city_vvaj_gen_dista").addScalar("sub_vaj_dista").addScalar("sub_vaj_gen_dista").addScalar("sub_vvaj_dista").addScalar("sub_vvaj_gen_dista").addScalar("sub_cor_dista").addScalar("sub_cor_gen_dista").addScalar("dead_dista").addScalar("service_limit").addScalar("brand_type_name").addScalar("sch").addScalar("gen_sch").addScalar("org_name");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totalvalues=0.0;

		   
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th></th><th colspan='3' align='center'>CITY KMS</th><th colspan='7' align='center'>SUB URBAN KMS</th><th colspan='8' align='center'>CITY+SUB URBAN</th></tr>" +
	        		"<tr><th>Depot</th><th colspan='2' align='center'>VOLVO</th><th>TOTAL</th><th colspan='2' align='center'>CORONA</th><th colspan='2' align='center'>VOLVO</th><th>TOTAL</th><th>total</th><th>Dead</th><th>TOTAL KMS</th><th></th><th>BMTC</th><th></th><th></th><th colspan='3' align='center'>vehicle utilisation (BMTC)</th></tr>" +
	        		"<tr><th>No</th><th>D/oN/o</th><th>G/s</th><th>CITY KMS</th><th>D/oN/o</th><th>G/S</th><th>D/oN/o</th><th>G/S</th><th>SUB-URBAN KMS</th><th>KMS</th><th>KMS</th><th>SHIFT/NO</th><th>GS</th><th>double</th><th>Single</th><th>total</th><th>shift/n/o</th><th>g/s</th><th>overall</th></tr>";

	        
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='47' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{		
                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			double total_vol_day=Double.parseDouble(list.get("city_vaj_dista").toString())+Double.parseDouble(list.get("city_vvaj_dista").toString());
			double total_vol_gen=Double.parseDouble(list.get("city_vaj_gen_dista").toString())+Double.parseDouble(list.get("city_vvaj_gen_dista").toString());
			double total_vol_kms=total_vol_day+total_vol_gen;
			regionTypeAjaxString +="<td align='right'>"+total_vol_day +"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_vol_gen+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_vol_kms+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_cor_dista").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_cor_gen_dista").toString()+"</td>";
			double total_sub_day=Double.parseDouble(list.get("sub_vaj_dista").toString())+Double.parseDouble(list.get("sub_vvaj_dista").toString());
			double total_sub_gen=Double.parseDouble(list.get("sub_vaj_gen_dista").toString())+Double.parseDouble(list.get("sub_vvaj_gen_dista").toString());
			double total_sub_kms=total_sub_day+total_sub_gen+Double.parseDouble(list.get("sub_cor_dista").toString())+Double.parseDouble(list.get("sub_cor_gen_dista").toString());;
			
			regionTypeAjaxString +="<td align='right'>"+total_sub_day +"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_sub_gen+"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_sub_kms+"</td>";
			double G_total=total_vol_kms+total_sub_kms;
			regionTypeAjaxString +="<td align='right'>"+G_total+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("dead_dista").toString()+"</td>";
			double total_dayout=total_vol_day+total_sub_day+Double.parseDouble(list.get("sub_cor_dista").toString());
			double total_genout=total_vol_gen+total_sub_gen+Double.parseDouble(list.get("sub_cor_gen_dista").toString());
			regionTypeAjaxString +="<td align='right'>"+total_dayout+"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_genout+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sch").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("gen_sch").toString()+"</td>";
			double total_sch=Double.parseDouble(list.get("sch").toString())+Double.parseDouble(list.get("gen_sch").toString());
			regionTypeAjaxString +="<td align='right'>"+total_sch+"</td>";
			double veh_day=0.0;
			if(Integer.parseInt(list.get("sch").toString())==0){
				veh_day=0.0;
			}else{
			 veh_day=total_dayout/Double.parseDouble(list.get("sch").toString());
			}
			double veh_gen=total_genout/Double.parseDouble(list.get("gen_sch").toString());
			double Veh_overall=G_total/total_sch;
			regionTypeAjaxString +="<td align='right'>"+veh_day+"</td>";
			regionTypeAjaxString +="<td align='right'>"+veh_gen+"</td>";
			regionTypeAjaxString +="<td align='right'>"+Veh_overall+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			

			
			   regionTypeAjaxString +="</tr>";
		}
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
	
public String getVolvoDivisionTripData(){
	

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
    
    String dates[]=singledate.split("-");
	String month=dates[0];
	int year=Integer.parseInt(dates[1]);
	int select_month=Integer.parseInt(month);
	int curr_month=Calendar.getInstance().get(Calendar.MONTH);
	int curr_month1=curr_month+1;
    
	 String date1="";
	    if(curr_month1==select_month){
	    	Date curr_date=new Date();
	    	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	    	date1=dateformat.format(curr_date);
	    }else{
	    	Calendar cal = new GregorianCalendar(year, select_month, 0);
			Date date = cal.getTime();
			DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			date1=sdf2.format(date);
	    }
   
	
	
	HttpServletRequest req = ServletActionContext.getRequest();
	Session session1 = null;
	Transaction transaction = null;
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 
	 String sql="Select SUM(CASE WHEN service_limit='City' THEN vaj_trips ELSE 0 END) city_vaj_trips, "+
			   " SUM(CASE WHEN service_limit='City' THEN vaj_gen_trips ELSE 0 END) city_vaj_gen_trips, "+
			   " SUM(CASE WHEN service_limit='City' THEN vvaj_trips ELSE 0 END) city_vvaj_trips, "+
			   " SUM(CASE WHEN service_limit='City' THEN vvaj_gen_trips ELSE 0 END) city_vvaj_gen_trips, "+
			   " SUM(CASE WHEN service_limit='Suburban' THEN vaj_trips ELSE 0 END) sub_vaj_trips, "+
			   " SUM(CASE WHEN service_limit='Suburban' THEN vaj_gen_trips ELSE 0 END) sub_vaj_gen_trips, "+
			   " SUM(CASE WHEN service_limit='Suburban' THEN vvaj_trips ELSE 0 END) sub_vvaj_trips, "+
			   " SUM(CASE WHEN service_limit='Suburban' THEN vvaj_gen_trips ELSE 0 END) sub_vvaj_gen_trips, "+
			   " SUM(CASE WHEN service_limit='Suburban' THEN cor_trips ELSE 0 END) sub_cor_trips, "+
			   " SUM(CASE WHEN service_limit='Suburban' THEN cor_gen_trips ELSE 0 END) sub_cor_gen_trips, "+
			   " dead_trips,service_limit,brand_type_name,sch,gen_sch,org_name from ( "+
			   " Select SUM(CASE WHEN brand_type_name='Vajra' THEN trips ELSE 0 END) vaj_trips, "+
			   " SUM(CASE WHEN brand_type_name='Vajra' THEN gen_trips ELSE 0 END) vaj_gen_trips, "+
			   " SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN trips ELSE 0 END) vvaj_trips, "+
			   " SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN gen_trips ELSE 0 END) vvaj_gen_trips, "+
			   " SUM(CASE WHEN brand_type_name='Corona AC' THEN trips ELSE 0 END) cor_trips, "+
			   " SUM(CASE WHEN brand_type_name='Corona AC' THEN gen_trips ELSE 0 END) cor_gen_trips, "+
			   " dead_trips,service_limit,brand_type_name,sch,gen_sch,org_name from ( "+
			   " Select ifnull(sum(sch),0) sch,ifnull(sum(gen_sch),0) gen_sch,ifnull(sum(trips),0) trips,ifnull(sum(gen_trips),0) gen_trips,ifnull(sum(dead_trips),0) dead_trips,service_limit,brand_type_name,org_name from(  "+
			   " select s.schedule_id,sch,gen_sch,trips,gen_trips,dead_trips,sl.service_limit,brand_type_name,org_name from schedule s "+
			   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id and parent_id='5' "+
			   " inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
			   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
			   " left join (SELECT count(schedule_number) sch,ifnull(max(trip_number),'0.0')  as trips,ff1.form_four_id FROM schedule_details left join form_four ff1  "+
			   " on ff1.form_four_id=schedule_details.form_four_id  "+
			   " WHERE trip_type='2' and shift_type_id in ('2','3','4','5') "+
			   " group by ff1.form_four_id) a on a.form_four_id=f.form_four_id  "+
			   " left join (SELECT count(schedule_number) gen_sch,ifnull(max(trip_number),'0.0')  as gen_trips,ff1.form_four_id FROM schedule_details left join form_four ff1  "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='2' and shift_type_id='1'  "+
			   " group by ff1.form_four_id) b on b.form_four_id=f.form_four_id  "+
			   " inner join (SELECT ifnull(max(trip_number),'0.0')  as dead_trips,ff1.form_four_id FROM schedule_details left join form_four ff1  "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+ 
			   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id  "+
			   " where f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') ) d group by d.service_limit,d.brand_type_name,d.org_name  "+
		       " ) e group by e.service_limit,e.brand_type_name,e.org_name ) f group by f.service_limit,f.brand_type_name,f.org_name";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("city_vaj_trips").addScalar("city_vaj_gen_trips").addScalar("city_vvaj_trips").addScalar("city_vvaj_gen_trips").addScalar("sub_vaj_trips").addScalar("sub_vaj_gen_trips").addScalar("sub_vvaj_trips").addScalar("sub_vvaj_gen_trips").addScalar("sub_cor_trips").addScalar("sub_cor_gen_trips").addScalar("dead_trips").addScalar("service_limit").addScalar("brand_type_name").addScalar("sch").addScalar("gen_sch").addScalar("org_name");
//	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
//		double Totalvalues=0.0;

	    
		
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

       

        regionTypeAjaxString += "<tr><th></th><th colspan='3' align='center'>CITY TRIPS</th><th colspan='5' align='center'>SUB URBAN TRIPS</th><th></th><th></th></tr>" +
        		"<tr><th></th><th colspan='2' align='center'>VOLVO</th><th>TOTAL</th><th colspan='2' align='center'>CORONA</th><th colspan='2' align='center'>VOLVO</th><th>TOTAL</th><th>TOTAL</th><th>DEAD</th></tr>" +
        		"<tr><th>Depot</th><th>D/oN/o</th><th>G/s</th><th>CITY TRIPS</th><th>D/oN/o</th><th>G/s</th><th>D/oN/o</th><th>G/s</th><th>SUB-URBAN  TRIPS</th> <th>TRIPS</th><th>TRIPS</th></tr>";

        
        if(aliasToValueMapList.size()==0){
         	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
              regionTypeAjaxString += "<tr>";
              regionTypeAjaxString += "<td colspan='47' align='center'><b>No Result Found</b></td>";
             
              regionTypeAjaxString += "</tr>";
         	
         }else{		
        
              
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
		
 		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
		double total_vol_day=Double.parseDouble(list.get("city_vaj_trips").toString())+Double.parseDouble(list.get("city_vvaj_trips").toString());
		double total_vol_gen=Double.parseDouble(list.get("city_vaj_gen_trips").toString())+Double.parseDouble(list.get("city_vvaj_gen_trips").toString());
		double total_vol_kms=total_vol_day+total_vol_gen;
		regionTypeAjaxString +="<td align='right'>"+total_vol_day +"</td>";
		regionTypeAjaxString +="<td align='right'>"+total_vol_gen+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ total_vol_kms+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("sub_cor_trips").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("sub_cor_gen_trips").toString()+"</td>";
		double total_sub_day=Double.parseDouble(list.get("sub_vaj_trips").toString())+Double.parseDouble(list.get("sub_vvaj_trips").toString());
		double total_sub_gen=Double.parseDouble(list.get("sub_vaj_gen_trips").toString())+Double.parseDouble(list.get("sub_vvaj_gen_trips").toString());
		double total_sub_kms=total_sub_day+total_sub_gen+Double.parseDouble(list.get("sub_cor_trips").toString())+Double.parseDouble(list.get("sub_cor_gen_trips").toString());;
		
		regionTypeAjaxString +="<td align='right'>"+total_sub_day +"</td>";
		regionTypeAjaxString +="<td align='right'>"+total_sub_gen+"</td>";
		regionTypeAjaxString +="<td align='right'>"+total_sub_kms+"</td>";
		double G_total=total_vol_kms+total_sub_kms;
		regionTypeAjaxString +="<td align='right'>"+G_total+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("dead_trips").toString()+"</td>";


		   regionTypeAjaxString +="</tr>";
	}
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
public String getVolvoDivisionMonthData(){
	

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
    
    String dates[]=singledate.split("-");
   	String month=dates[0];
   	int year=Integer.parseInt(dates[1]);
   	int select_month=Integer.parseInt(month);
   	int curr_month=Calendar.getInstance().get(Calendar.MONTH);
   	int curr_month1=curr_month+1;
       
   	 String date1="";
   	    if(curr_month1==select_month){
   	    	Date curr_date=new Date();
   	    	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
   	    	date1=dateformat.format(curr_date);
   	    }else{
   	    	Calendar cal = new GregorianCalendar(year, select_month, 0);
   			Date date = cal.getTime();
   			DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
   			date1=sdf2.format(date);
   	    }
   
	

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
        

       

        regionTypeAjaxString += "<tr><th></th><th colspan='3' align='center'>STEERING HOURS</th><th colspan='3' align='center'>CREW UTILIZATION</th><th colspan='3' align='center'>VEHICLE REQ. FOR OPN</th><th colspan='8' align='center'>CREW REQUIRED FOR OPERATION</th><th colspan='3' align='center'>VEHICLE UTILISATION (BMTC)</th><th colspan='4' align='center'>CREW UTILISATION FORMUAL</th></tr>" +
        		"<tr><th>Depot</th><th>Shift/NO</th><th>G/S</th><th>Overall</th><th>Shift</th><th>G/S</th><th>Over</th><th>For</th><th>Spare</th><th>Total</th><th colspan='3' align='center'>CONDUCTOR</th><th colspan='3' align='center'>DRIVER</th><th colspan='2' align='center'>BMTC</th><th>Shift /N/O</th><th>G/s</th> <th>Overall</th><th colspan='2' align='center'>SHIFT/NO</th><th>GS</th><th>OVERALL</th></tr>" +
        		"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th>All</th><th>OPN</th><th>4%</th><th></th><th>For OPN</th><th>38.33%</th><th>Total</th><th>For Opn</th><th>38.33%</th><th>Total</th><th>OT SCH</th><th>OT Hrs</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>";

		 
        if(aliasToValueMapList==null){
         	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
              regionTypeAjaxString += "<tr>";
              regionTypeAjaxString += "<td colspan='47' align='center'><b>No Result Found</b></td>";
             
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

	
	
}
