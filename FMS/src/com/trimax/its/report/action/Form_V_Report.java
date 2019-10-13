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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class Form_V_Report extends ActionSupport {

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
	
	public String getForm_VDivisionForm_vData(){
		

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
		 
		 String sql="select sum(city_ord_sch) city_ord_sch,sum(city_cor_sch) city_cor_sch,sum(city_vaj_sch) city_vaj_sch,sum(sub_ord_sch) sub_ord_sch, "+
				   " sum(sub_cor_sch) sub_cor_sch,sum(sub_vaj_sch) sub_vaj_sch,sum(sub_vvaj_sch) sub_vvaj_sch, "+
				   " Sum(city_ord_do) city_ord_do,sum(city_ord_no) city_ord_no,sum(city_ord_gs) city_ord_gs,sum(sub_ord_do) sub_ord_do,sum(sub_ord_no) sub_ord_no,sum(sub_ord_gs) sub_ord_gs, "+
				   " sum(city_cor_do) city_cor_do,sum(city_cor_no) city_cor_no,sum(city_cor_gs) city_cor_gs,sum(sub_cor_do) sub_cor_do,sum(sub_cor_no) sub_cor_no,sum(sub_cor_gs) sub_cor_gs, "+
				   " sum(city_vaj_do) city_vaj_do,sum(city_vaj_no) city_vaj_no, sum(city_vaj_gs) city_vaj_gs,sum(sub_vaj_do) sub_vaj_do,sum(sub_vaj_no) sub_vaj_no,sum(sub_vaj_gs) sub_vaj_gs, "+
				   " sum(city_vvaj_do) city_vvaj_do,sum(city_vvaj_no) city_vvaj_no,sum(city_vvaj_gs) city_vvaj_gs,sum(sub_vvaj_do) sub_vvaj_do,sum(sub_vvaj_no) sub_vvaj_no,sum(sub_vvaj_gs) sub_vvaj_gs, "+
				   " sum(night_service) night_service,org_name,depot_id from ( "+
				   " select SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary'  THEN sch_number ELSE 0 END) city_ord_sch, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Corona AC'  THEN sch_number ELSE 0 END) city_cor_sch, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra'  THEN sch_number ELSE 0 END) city_vaj_sch, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary'  THEN sch_number ELSE 0 END) sub_ord_sch, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC'  THEN sch_number ELSE 0 END) sub_cor_sch, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra'  THEN sch_number ELSE 0 END) sub_vaj_sch, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra'  THEN sch_number ELSE 0 END) sub_vvaj_sch, "+
				   " org_name,depot_id,0 as city_ord_do,0 as city_ord_no,0 as city_ord_gs,0 as sub_ord_do,0 as sub_ord_no,0 as sub_ord_gs,0 as city_cor_do,0 as city_cor_no,0 as city_cor_gs, "+
				   " 0 as sub_cor_do,0 as sub_cor_no,0 as sub_cor_gs,0 as city_vaj_do,0 as city_vaj_no,0 as city_vaj_gs,0 as sub_vaj_do,0 as sub_vaj_no,0 as sub_vaj_gs,0 as city_vvaj_do, "+
				   " 0 as city_vvaj_no,0 as city_vvaj_gs,0 as sub_vvaj_do,0 as sub_vvaj_no, 0 as sub_vvaj_gs,0 as night_service "+
				   " from (select count(schedule_number) sch_number,brand_type_name,service_limit,org_name,depot_id from ( "+
				   " select sd.schedule_number,brand_type_name,service_limit,shift_type_id,org_name,depot_id from schedule s "+
				   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
				   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
				   " inner join  org_chart oc on oc.org_chart_id=s.depot_id "+
				   " inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
				   " where trip_type='2' and f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') "+
			 	   " ) a group by a.brand_type_name,a.service_limit,a.org_name ) b group by b.org_name union all "+
				   " Select org_name,depot_id,0 as city_ord_sch,0 as city_cor_sch,0 as city_vaj_sch,0 as sub_ord_sch,0 as sub_cor_sch,0 as sub_vaj_sch,0 as sub_vvaj_sch, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) city_ord_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) city_ord_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) city_ord_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_ord_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_ord_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_ord_gs, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Corona AC' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) city_cor_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Corona AC' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) city_cor_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Corona AC' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) city_cor_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_cor_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_cor_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_cor_gs, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) city_vaj_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) city_vaj_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) city_vaj_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_vaj_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_vaj_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_vaj_gs, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) city_vvaj_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) city_vvaj_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) city_vvaj_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_vvaj_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_vvaj_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_vvaj_gs, "+
				   " SUM(CASE WHEN schedule_type_name='Night Service'  THEN sch_number ELSE 0 END) night_service from ( "+
				   " select count(schedule_number) sch_number,brand_type_name,service_limit,schedule_type_name,org_name,depot_id from( "+
				   " select sd.schedule_number,brand_type_name,service_limit,schedule_type_name,org_name,depot_id from schedule s "+
				   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
				   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
				   " inner join  org_chart oc on oc.org_chart_id=s.depot_id "+
				   " inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
				   " inner join  shift_type st on st.shift_type_id=sd.shift_type_id "+
				   " inner join schedule_type sp on sp.schedule_type_id=st.schedule_type_id "+
				   " where trip_type='2' and f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') "+
			       " ) a group by a.schedule_type_name,a.service_limit,a.brand_type_name,a.org_name ) b group by b.org_name ) c group by c.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("city_ord_sch").addScalar("city_cor_sch").addScalar("city_vaj_sch").addScalar("sub_ord_sch").addScalar("sub_cor_sch").addScalar("sub_vaj_sch").addScalar("sub_vvaj_sch").addScalar("city_ord_do").addScalar("city_ord_no").addScalar("city_ord_gs").addScalar("sub_ord_do").addScalar("sub_ord_no").addScalar("sub_ord_gs").addScalar("city_cor_do")
				 .addScalar("city_cor_no").addScalar("city_cor_gs").addScalar("sub_cor_do").addScalar("sub_cor_no").addScalar("sub_cor_gs").addScalar("city_vaj_do").addScalar("city_vaj_no").addScalar("city_vaj_gs").addScalar("sub_vaj_do")
				 .addScalar("sub_vaj_no").addScalar("sub_vaj_gs").addScalar("city_vvaj_do").addScalar("city_vvaj_no").addScalar("city_vvaj_gs").addScalar("sub_vvaj_do").addScalar("sub_vvaj_no").addScalar("sub_vvaj_gs").addScalar("night_service");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totalvalues=0.0;

		    
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>FORM V FOR THE MONTH OF FEBRUARY- 2016 AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th colspan='11' align='center'>CLASSIFICATION OF SCHEDULES</th><th colspan='6' align='center'>ORDINARY SCHEDULES</th><th colspan='6' align='center'>CORONA</th><th colspan='6' align='center'>VAJRA</th><th colspan='6' align='center'>VAYU VAJRA</th><th colspan='6' align='center'>TYPE OF SEVICE</th><th colspan='4' align='center'>CITY+SUBURBAN</th><th colspan='2' align='center'>TYPE OF CREWS</th></tr>" +
	        		"<tr><th></th><th colspan='4' align='center'>CITY SCHEDULES</th><th colspan='5' align='center'>SUB-URBAN SCHEDULES</th><th>Total</th><th colspan='3' align='center'>CITY SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>CITY SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>CITY SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>CITY SERVICES</th><th colspan='3' align='center'>SUBURBAN SERVICES</th><th colspan='3' align='center'>TOTAL CITY SERVICES</th><th colspan='3' align='center'>TOTAL SUBURBAN SERVICES</th><th colspan='4' align='center'>TOTAL TYPE OF SERVICE</th><th>DOUBLE</th><th>SINGLE</th></tr>" +
	        		"<tr><th>Depot</th><th>ORD</th><th>CORONA</th><th>VAJRA</th><th>TOTAL</th><th>ORD</th><th>CORONA</th><th>VAJRA</th><th>VAYU VAJRA</th><th>TOTAL</th><th>SCH</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>NS</th><th></th><th></th></tr>";

			 
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
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vaj_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_sch").toString() +"</td>";
			double city_total=Double.parseDouble(list.get("city_ord_sch").toString())+Double.parseDouble(list.get("city_vaj_sch").toString())+Double.parseDouble(list.get("city_cor_sch").toString());
			regionTypeAjaxString +="<td align='right'>"+ city_total +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vaj_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vvaj_sch").toString() +"</td>";
			double sub_total=Double.parseDouble(list.get("sub_ord_sch").toString())+Double.parseDouble(list.get("sub_cor_sch").toString())+Double.parseDouble(list.get("sub_vaj_sch").toString())+Double.parseDouble(list.get("sub_vvaj_sch").toString());
			regionTypeAjaxString +="<td align='right'>"+ sub_total +"</td>";
            double total_sch=city_total+sub_total;
			regionTypeAjaxString +="<td align='right'>"+ total_sch +"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_gs").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_gs").toString() +"</td>";

			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_gs").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_gs").toString() +"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vaj_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vaj_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vaj_gs").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vaj_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vaj_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vaj_gs").toString() +"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vvaj_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vvaj_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_vvaj_gs").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vvaj_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vvaj_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_vvaj_gs").toString() +"</td>";
			
			double total_city_dayout=Double.parseDouble(list.get("city_ord_do").toString())+Double.parseDouble(list.get("city_cor_do").toString())+Double.parseDouble(list.get("city_vaj_do").toString())+Double.parseDouble(list.get("city_vvaj_do").toString());
			double total_city_nightout=Double.parseDouble(list.get("city_ord_no").toString())+Double.parseDouble(list.get("city_cor_no").toString())+Double.parseDouble(list.get("city_vaj_no").toString())+Double.parseDouble(list.get("city_vvaj_no").toString());
			double total_city_general=Double.parseDouble(list.get("city_ord_gs").toString())+Double.parseDouble(list.get("city_cor_gs").toString())+Double.parseDouble(list.get("city_vaj_gs").toString())+Double.parseDouble(list.get("city_vvaj_gs").toString());

			double total_sub_dayout=Double.parseDouble(list.get("sub_ord_do").toString())+Double.parseDouble(list.get("sub_cor_do").toString())+Double.parseDouble(list.get("sub_vaj_do").toString())+Double.parseDouble(list.get("sub_vvaj_do").toString());
			double total_sub_nightout=Double.parseDouble(list.get("sub_ord_no").toString())+Double.parseDouble(list.get("sub_cor_no").toString())+Double.parseDouble(list.get("sub_vaj_no").toString())+Double.parseDouble(list.get("sub_vvaj_no").toString());
			double total_sub_general=Double.parseDouble(list.get("sub_ord_gs").toString())+Double.parseDouble(list.get("sub_cor_gs").toString())+Double.parseDouble(list.get("sub_vaj_gs").toString())+Double.parseDouble(list.get("sub_vvaj_gs").toString());
            
			double total_dayout=total_city_dayout+total_sub_dayout;
			double total_nightout=total_city_nightout+total_sub_nightout;
			double total_genshift=total_city_general+total_sub_general;
			
			regionTypeAjaxString +="<td align='right'>"+ total_city_dayout +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_city_nightout +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_city_general +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_sub_dayout +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_sub_nightout +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_sub_general +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_dayout +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_nightout +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_genshift +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("night_service").toString() +"</td>";
			double night_service=Double.parseDouble(list.get("night_service").toString());
			double single=total_dayout+total_nightout+total_genshift;
			regionTypeAjaxString +="<td align='right'>"+ night_service +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ single +"</td>";
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
	
public String getForm_VDivisionKilometerData(){
		

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
		 
		 String sql="select SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) city_ord_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) city_ord_no,  "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN dista ELSE 0 END) city_ord_gs,  "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Corona AC' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) city_cor_do,  "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Corona AC' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) city_cor_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Corona AC' and schedule_type_name='General shift'  THEN dista ELSE 0 END) city_cor_gs, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) city_vaj_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) city_vaj_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN dista ELSE 0 END) city_vaj_gs, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) city_vvaj_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) city_vvaj_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN dista ELSE 0 END) city_vvaj_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) sub_ord_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) sub_ord_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN dista ELSE 0 END) sub_ord_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) sub_cor_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) sub_cor_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='General shift'  THEN dista ELSE 0 END) sub_cor_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) sub_vaj_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) sub_vaj_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN dista ELSE 0 END) sub_vaj_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN dista ELSE 0 END) sub_vvaj_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN dista ELSE 0 END) sub_vvaj_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN dista ELSE 0 END) sub_vvaj_gs, "+
				   " SUM(CASE WHEN schedule_type_name='Night Service'  THEN dista ELSE 0 END) night_service, "+
				   " SUM(CASE WHEN schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sch_do, "+
				   " SUM(CASE WHEN schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sch_no, "+
				   " SUM(CASE WHEN schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sch_gs, "+
				   " SUM(CASE WHEN schedule_type_name='Night Service'  THEN sch_number ELSE 0 END) sch_ns, "+
				   " distadead,org_name from ( select sch_number,dista,distadead,brand_type_name,service_limit,schedule_type_name,org_name from ( "+
				   " select count(sd.schedule_number) sch_number,ifnull(sum(distance),'0.0')  as dista,distadead,brand_type_name,service_limit,schedule_type_name,org_name from schedule s "+
				   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
				   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
				   " inner join  org_chart oc on oc.org_chart_id=s.depot_id   "+
				   " inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
				   " inner join  shift_type st on st.shift_type_id=sd.shift_type_id "+
				   " inner join schedule_type sp on sp.schedule_type_id=st.schedule_type_id "+
				   " inner join (SELECT ifnull(sum(distance),'0.0')  as distadead,max(trip_number) tripsdeadshift1,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
				   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
				   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
				   " where trip_type='2' and f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') "+
			       " ) a group by a.schedule_type_name,a.brand_type_name,a.service_limit,a.org_name ) b group by b.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("city_ord_do").addScalar("city_ord_no").addScalar("city_ord_gs").addScalar("city_cor_do").addScalar("city_cor_no").addScalar("city_cor_gs").addScalar("city_vaj_do").addScalar("city_vaj_no").addScalar("city_vaj_gs").addScalar("city_vvaj_do").addScalar("city_vvaj_no").addScalar("city_vvaj_gs").addScalar("sub_ord_do")
				 .addScalar("sub_ord_no").addScalar("sub_ord_gs").addScalar("sub_cor_do").addScalar("sub_cor_no").addScalar("sub_cor_gs").addScalar("sub_vaj_do").addScalar("sub_vaj_no").addScalar("sub_vaj_gs").addScalar("sub_vvaj_do").addScalar("sub_vvaj_no").addScalar("sub_vvaj_gs").addScalar("distadead",Hibernate.STRING)
				 .addScalar("night_service").addScalar("sch_do").addScalar("sch_no").addScalar("sch_gs").addScalar("sch_ns");
////		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totalvalues=0.0;

		    
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th></th><th colspan='9' align='center'>CITY KMS</th><th></th><th colspan='9' align='center'>SUB URBAN KMS</th><th></th><th></th><th></th><th colspan='5' align='center'></th><th colspan='4' align='center'></th></tr>" +
	        		"<tr><th></th><th colspan='3' align='center'>ORDINARY</th><th colspan='3' align='center'>CORONA</th><th colspan='3' align='center'>VOLVO</th><th>TOTAL</th><th colspan='3' align='center'>ORDINARY</th><th colspan='3' align='center'>CORONA</th><th colspan='3' align='center'>VOLVO</th><th>TOTAL</th><th>BMTC TotaL</th><th>Dead</th><th colspan='5' align='center'>TOTAL  KMS</th><th colspan='4' align='center'>VEHICLE UTILIZATION(BMTC) </th></tr>" +
	        		"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th><th>D/O</th><th>N/O</th><th>G/S</th> <th>CITY KMS</th>  <th>D/O</th><th>NO</th><th>G/S</th><th>D/O</th><th>NO</th><th>G/S</th> <th>D/O</th><th>NO</th><th>G/S</th><th>SUB-URBAN KMS</th><th>KMS</th><th>KMS</th><th>DO</th><th>NO</th><th>GS</th><th>NS</th><th>total</th><th>DO</th><th>NO</th><th>GS+NS</th><th>overall</th></tr>";
//	        <th>single</th><th>total</th><th>shift/n/o</th><th>g/s</th><th>overall</th>
			    

	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='32' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{
	        
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_gs").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_cor_gs").toString() +"</td>";
			double vol_do=Double.parseDouble(list.get("city_vaj_do").toString())+Double.parseDouble(list.get("city_vvaj_do").toString());
			double vol_no=Double.parseDouble(list.get("city_vaj_no").toString())+Double.parseDouble(list.get("city_vvaj_no").toString());
			double vol_gs=Double.parseDouble(list.get("city_vaj_gs").toString())+Double.parseDouble(list.get("city_vvaj_gs").toString());

			regionTypeAjaxString +="<td align='right'>"+vol_do +"</td>";
			regionTypeAjaxString +="<td align='right'>"+vol_no +"</td>";
			regionTypeAjaxString +="<td align='right'>"+vol_gs +"</td>";
			double total_city=Double.parseDouble(list.get("city_ord_do").toString())+Double.parseDouble(list.get("city_cor_do").toString())+vol_do+Double.parseDouble(list.get("city_ord_no").toString())+Double.parseDouble(list.get("city_cor_no").toString())+vol_no+Double.parseDouble(list.get("city_ord_gs").toString())+Double.parseDouble(list.get("city_cor_gs").toString())+vol_gs;
			regionTypeAjaxString +="<td align='right'>"+total_city+"</td>";
			
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_gs").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_no").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_gs").toString() +"</td>";
			double vol_do_sub=Double.parseDouble(list.get("sub_vaj_do").toString())+Double.parseDouble(list.get("sub_vvaj_do").toString());
			double vol_no_sub=Double.parseDouble(list.get("sub_vaj_no").toString())+Double.parseDouble(list.get("sub_vvaj_no").toString());
			double vol_gs_sub=Double.parseDouble(list.get("sub_vaj_gs").toString())+Double.parseDouble(list.get("sub_vvaj_gs").toString());

			regionTypeAjaxString +="<td align='right'>"+vol_do_sub +"</td>";
			regionTypeAjaxString +="<td align='right'>"+vol_no_sub +"</td>";
			regionTypeAjaxString +="<td align='right'>"+vol_gs_sub +"</td>";
			double total_city_sub=Double.parseDouble(list.get("sub_ord_do").toString())+Double.parseDouble(list.get("sub_cor_do").toString())+vol_do_sub+Double.parseDouble(list.get("sub_ord_no").toString())+Double.parseDouble(list.get("sub_cor_no").toString())+vol_no_sub+Double.parseDouble(list.get("sub_ord_gs").toString())+Double.parseDouble(list.get("sub_cor_gs").toString())+vol_gs_sub;
			regionTypeAjaxString +="<td align='right'>"+total_city_sub+"</td>";
			
			double total_kms=total_city+total_city_sub;
			regionTypeAjaxString +="<td align='right'>"+total_kms+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("distadead").toString() +"</td>";
			
			double total_do=Double.parseDouble(list.get("city_ord_do").toString())+Double.parseDouble(list.get("city_cor_do").toString())+vol_do+Double.parseDouble(list.get("sub_ord_do").toString())+Double.parseDouble(list.get("sub_cor_do").toString())+vol_do_sub;
			double total_no=Double.parseDouble(list.get("city_ord_no").toString())+Double.parseDouble(list.get("city_cor_no").toString())+vol_no+Double.parseDouble(list.get("sub_ord_no").toString())+Double.parseDouble(list.get("sub_cor_no").toString())+vol_no_sub;
			double total_gs=Double.parseDouble(list.get("city_ord_gs").toString())+Double.parseDouble(list.get("city_cor_gs").toString())+vol_gs+Double.parseDouble(list.get("sub_ord_gs").toString())+Double.parseDouble(list.get("sub_cor_gs").toString())+vol_gs_sub;
			regionTypeAjaxString +="<td align='right'>"+total_do +"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_no +"</td>";
			regionTypeAjaxString +="<td align='right'>"+total_gs +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("night_service").toString() +"</td>";
			double total=total=total_do+total_no+total_gs+Double.parseDouble(list.get("sch_do").toString());
			
			regionTypeAjaxString +="<td align='right'>"+total+"</td>";
			
			double veh_do=0.0;
			if(Integer.parseInt(list.get("sch_do").toString())==0){
				veh_do=0.0;
			}else{
				veh_do=total_do/Double.parseDouble(list.get("sch_do").toString());
			}
			double veh_no=0.0;
			if(Integer.parseInt(list.get("sch_no").toString())==0){
				veh_no=0.0;
			}else{
				veh_do=total_do/Double.parseDouble(list.get("sch_no").toString());
			}
			double veh_gs=0.0;
			double total_ns_gs=total_gs+Double.parseDouble(list.get("night_service").toString());
			double total_sch=Double.parseDouble(list.get("sch_gs").toString())+Double.parseDouble(list.get("sch_ns").toString());
			if(total_sch==0.0){
				veh_gs=0.0;
			}else{
				veh_gs=total_ns_gs/total_sch;
			}
			regionTypeAjaxString +="<td align='right'>"+veh_do +"</td>";
			regionTypeAjaxString +="<td align='right'>"+veh_no +"</td>";
			regionTypeAjaxString +="<td align='right'>"+veh_gs +"</td>";
			double overall=total_kms/total;
			regionTypeAjaxString +="<td align='right'>"+overall +"</td>";

			
			//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
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
	
public String getForm_VDivisionTripData(){
	

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
	 
	 String sql="select SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) city_ord_do, "+
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) city_ord_no,  "+  
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN trips ELSE 0 END) city_ord_gs, "+
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) city_vaj_do,  "+
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) city_vaj_no, "+
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN trips ELSE 0 END) city_vaj_gs, "+
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) city_vvaj_do, "+
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) city_vvaj_no, "+
			   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN trips ELSE 0 END) city_vvaj_gs, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) sub_ord_do, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) sub_ord_no, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN trips ELSE 0 END) sub_ord_gs, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) sub_cor_do, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) sub_cor_no, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='General shift'  THEN trips ELSE 0 END) sub_cor_gs, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) sub_vaj_do, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) sub_vaj_no, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN trips ELSE 0 END) sub_vaj_gs, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) sub_vvaj_do, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) sub_vvaj_no, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN trips ELSE 0 END) sub_vvaj_gs, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='BIG-10' and schedule_type_name='Day Out'  THEN trips ELSE 0 END) sub_big_do, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='BIG-10' and schedule_type_name='Night Out'  THEN trips ELSE 0 END) sub_big_no, "+
			   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='BIG-10' and schedule_type_name='General shift'  THEN trips ELSE 0 END) sub_big_gs, "+
			   " tripsdead,org_name from ( select trips,tripsdead,brand_type_name,service_limit,schedule_type_name,org_name from ( "+
			   " select ifnull(max(trip_number),'0.0')  as trips,tripsdead,brand_type_name,service_limit,schedule_type_name,org_name from schedule s "+
			   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
			   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id  "+
			   " inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
			   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
			   " inner join  shift_type st on st.shift_type_id=sd.shift_type_id "+
			   " inner join schedule_type sp on sp.schedule_type_id=st.schedule_type_id "+
			   " inner join (SELECT ifnull(max(trip_number),'0.0')  as tripsdead,ff1.form_four_id FROM schedule_details left join form_four ff1 "+
			   " on ff1.form_four_id=schedule_details.form_four_id WHERE trip_type='3' "+
			   " group by ff1.form_four_id) c on c.form_four_id=f.form_four_id "+
			   " where trip_type='2' and f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') "+
		       " ) a group by a.schedule_type_name,a.brand_type_name,a.service_limit,a.org_name ) b group by b.org_name"; 
	 
	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("city_ord_do").addScalar("city_ord_no").addScalar("city_ord_gs").addScalar("city_vaj_do").addScalar("city_vaj_no").addScalar("city_vaj_gs").addScalar("city_vvaj_do").addScalar("city_vvaj_no").addScalar("city_vvaj_gs").addScalar("sub_ord_do").addScalar("sub_ord_no").addScalar("sub_ord_gs")
			 .addScalar("sub_cor_do").addScalar("sub_cor_no").addScalar("sub_cor_gs").addScalar("sub_vaj_do").addScalar("sub_vaj_no").addScalar("sub_vaj_gs").addScalar("sub_vvaj_do").addScalar("sub_vvaj_no").addScalar("sub_vvaj_gs").addScalar("sub_big_do").addScalar("sub_big_no").addScalar("sub_big_gs").addScalar("tripsdead");
//	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
		double Totalvalues=0.0;

	  
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

        
       

        regionTypeAjaxString += "<tr><th></th><th colspan='5' align='center'>CITY TRIPS</th><th colspan='10' align='center'>SUB URBAN TRIPS</th><th></th></tr>" +
        		"<tr><th></th><th colspan='2' align='center'>ORDINARY</th><th colspan='2' align='center'>VOLVO</th><th>TOTAL</th><th colspan='2' align='center'>ORDINARY</th><th colspan='2' align='center'>CORONA</th><th colspan='2' align='center'>VOLVO</th><th colspan='2' align='center'>BIG-10</th><th>TOTAL</th><th>TOTAL</th><th>DEAD</th></tr>" +
        		"<tr><th>Depot</th><th>D/oN/o</th><th>G/S</th><th>D/oN/o</th><th>G/S</th><th>CITY TRIPS</th> <th>D/oN/o</th><th>G/S</th><th>D/oN/o</th><th>G/S</th> <th>D/oN/o</th><th>G/S</th><th>D/oN/o</th><th>G/S</th><th>SUB-URBAN TRIPS</th><th>TRIPS</th><th>TRIPS</th></tr>";

		 
        if(aliasToValueMapList.size()==0){
         	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
              regionTypeAjaxString += "<tr>";
              regionTypeAjaxString += "<td colspan='17' align='center'><b>No Result Found</b></td>";
             
              regionTypeAjaxString += "</tr>";
         	
         }else{

	
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
		
// 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
		double city_ord_do_no=Double.parseDouble(list.get("city_ord_do").toString())+Double.parseDouble(list.get("city_ord_no").toString());
		regionTypeAjaxString +="<td align='right'>"+city_ord_do_no+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_gs").toString() +"</td>";
		double city_vol_do_no=Double.parseDouble(list.get("city_vaj_do").toString())+Double.parseDouble(list.get("city_vaj_no").toString())+Double.parseDouble(list.get("city_vvaj_do").toString())+Double.parseDouble(list.get("city_vvaj_no").toString());
        double city_vol_gs=Double.parseDouble(list.get("city_vaj_gs").toString())+Double.parseDouble(list.get("city_vvaj_gs").toString());
		regionTypeAjaxString +="<td align='right'>"+city_vol_do_no+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ city_vol_gs +"</td>";
		double city_total=city_ord_do_no+city_vol_do_no+city_vol_gs+Double.parseDouble(list.get("city_ord_gs").toString());
		regionTypeAjaxString +="<td align='right'>"+ city_total +"</td>";
		double sub_ord_do_no=Double.parseDouble(list.get("sub_ord_do").toString())+Double.parseDouble(list.get("sub_ord_no").toString());
		double sub_cor_do_no=Double.parseDouble(list.get("sub_cor_do").toString())+Double.parseDouble(list.get("sub_cor_no").toString());
		double sub_vol_do_no=Double.parseDouble(list.get("sub_vaj_do").toString())+Double.parseDouble(list.get("sub_vaj_no").toString())+Double.parseDouble(list.get("sub_vvaj_do").toString())+Double.parseDouble(list.get("sub_vvaj_no").toString());
        double sub_vol_gs=Double.parseDouble(list.get("sub_vaj_gs").toString())+Double.parseDouble(list.get("sub_vvaj_gs").toString());
		double sub_big_do_no=Double.parseDouble(list.get("sub_big_do").toString())+Double.parseDouble(list.get("sub_big_no").toString());

		regionTypeAjaxString +="<td align='right'>"+sub_ord_do_no+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_gs").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+sub_cor_do_no+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_gs").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+sub_vol_do_no+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ sub_vol_gs +"</td>";

		regionTypeAjaxString +="<td align='right'>"+sub_big_do_no+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("sub_big_gs").toString() +"</td>";
      
		double sub_total=sub_ord_do_no+sub_cor_do_no+sub_vol_do_no+sub_vol_gs+sub_big_do_no+Double.parseDouble(list.get("sub_ord_gs").toString())+Double.parseDouble(list.get("sub_cor_gs").toString())+Double.parseDouble(list.get("sub_big_gs").toString());
		regionTypeAjaxString +="<td align='right'>"+sub_total+"</td>";
		double total_trip=city_total+sub_total;
		regionTypeAjaxString +="<td align='right'>"+total_trip+"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("tripsdead").toString() +"</td>";
		
//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

//		
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
public String getForm_VDivisionMonthData(){
	

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
        

        
       

        regionTypeAjaxString += "<tr><th></th><th colspan='3' align='center'>STEERING HOURS</th><th colspan='3' align='center'>CREW UTILIZATION</th><th colspan='3' align='center'>VEHICLE REQ. FOR OPN</th><th colspan='6' align='center'>CREW REQUIRED FOR OPERATION</th><th colspan='2' align='center'></th><th colspan='3' align='center'>VEHICLE UTILIZATION</th><th colspan='3' align='center'>CREW UTILIZATION FORMULA</th></tr>" +
        		"<tr><th>Depot</th><th>Shift/NO</th><th>G/S</th><th>Overall</th><th>shift</th><th>G/S</th><th>Over</th><th>For</th><th>Spare</th><th>Total</th><th colspan='3' align='center'>CONDUCTOR</th><th colspan='3' align='center'>DRIVER</th><th colspan='2' align='center'>Over All</th><th>Shift/N/O</th><th>G/S</th><th>Overall</th> <th>Shift/NO</th><th>GS</th><th>Over All</th></tr>" +
        		"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th>All</th><th>Opn</th><th>4%</th><th></th><th>For Opn</th><th>38.33%</th><th>Total</th><th>For Opn</th><th>38.33%</th><th>Total</th><th>OT SCH</th><th>OT Hrs</th><th></th><th></th><th></th><th></th><th></th><th></th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 

        if(aliasToValueMapList==null){
         	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
              regionTypeAjaxString += "<tr>";
              regionTypeAjaxString += "<td colspan='24' align='center'><b>No Result Found</b></td>";
             
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

public String getForm_VDivisionSummaryData (){
	

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
	 
	 String sql="Select sum(sch_do) sch_do,sum(sch_gs) sch_gs,sum(sch_no) sch_no,sum(sch_ns) sch_ns,sum(sch_ss) sch_ss,sum(sch_ord) sch_ord,sum(sch_cor) sch_cor,sum(sch_vj) sch_vj,sum(sch_vvj) sch_vvj,org_name from (  "+
			   " Select SUM(CASE WHEN schedule_type_name='Day Out' THEN sch_number ELSE 0 END) sch_do, "+
			   " SUM(CASE WHEN schedule_type_name='General shift' THEN sch_number ELSE 0 END) sch_gs, "+
			   " SUM(CASE WHEN schedule_type_name='Night Out' THEN sch_number ELSE 0 END) sch_no, "+
			   " SUM(CASE WHEN schedule_type_name='Night Service' THEN sch_number ELSE 0 END) sch_ns,  "+
			   " SUM(CASE WHEN schedule_type_name='Split Service' THEN sch_number ELSE 0 END) sch_ss, "+
			   " org_name,0 as sch_ord,0 as sch_cor, 0 as sch_vj, 0 as sch_vvj from ( "+
			   " select count(schedule_number) sch_number,schedule_type_name,org_name from( "+
			   " select sd.schedule_number,schedule_type_name,depot_id,oc1.org_name from schedule s "+
			   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
			   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id "+
			   " inner join  shift_type st on st.shift_type_id=sd.shift_type_id "+
			   " inner join schedule_type sp on sp.schedule_type_id=st.schedule_type_id "+
			   " inner join org_chart oc1 on oc1.org_chart_id=oc.parent_id "+
			   " where trip_type='2' and f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER'  "+
		       " ) a group by a.schedule_type_name,a.org_name ) b group by b.org_name "+
			   " union all Select 0 as sch_do,0 as sch_gs,0 as sch_no,0 as sch_ns,0 as sch_ss,org_name, "+
			   " SUM(CASE WHEN brand_type_name='Ordinary' THEN sch_numbers ELSE 0 END) sch_ord, "+
			   " SUM(CASE WHEN brand_type_name='Corona AC' THEN sch_numbers ELSE 0 END) sch_cor, "+
			   " SUM(CASE WHEN brand_type_name='Vajra' THEN sch_numbers ELSE 0 END) sch_vj, "+
			   " SUM(CASE WHEN brand_type_name='Vayu Vajra' THEN sch_numbers ELSE 0 END) sch_vvj from (  "+
			   " select count(schedule_number) sch_numbers,org_name,brand_type_name from( "+
			   " select sd.schedule_number,schedule_type_name,depot_id,oc1.org_name,brand_type_name from schedule s  "+
			   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
			   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
			   " inner join  org_chart oc on oc.org_chart_id=s.depot_id "+
			   " inner join  shift_type st on st.shift_type_id=sd.shift_type_id "+
			   " inner join schedule_type sp on sp.schedule_type_id=st.schedule_type_id  "+
			   " inner join org_chart oc1 on oc1.org_chart_id=oc.parent_id "+
			   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
			   " where trip_type='2' and f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' AND  (f.effective_end_date IS NULL or f.effective_end_date >='"+date1+"') "+
		 	   " ) a group by a.brand_type_name,a.org_name ) b group by org_name ) c group by org_name";
	 
	 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sch_do").addScalar("sch_gs").addScalar("sch_no").addScalar("sch_ns").addScalar("sch_ss").addScalar("sch_ord").addScalar("sch_cor").addScalar("sch_vj").addScalar("sch_vvj");
//	  
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>> aliasToValueMapList = query.list();
		double Totalsingle=0.0;
		double Totaldouble=0.0;
		double Total_ns=0.0;
		double Total_schs=0.0;
		double Total_ord=0.0;
		double Total_cor=0.0;
		double Total_vol=0.0;

	  
		
//		regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//		//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
        

       

        regionTypeAjaxString += "<tr><th></th><th></th><th colspan='2' align='center'>No Of Sch's</th><th></th><th>Total</th><th colspan='3' align='center'>CLASSIFICATION OF SCHEDULES</th></tr>" +
        		"<tr><th>Sl.no</th><th>Divn</th><th>Single</th><th>Double</th><th>NS</th><th>Sch's</th><th>ORD</th><th>CORONA</th><th>VOLVO</th></tr>";

		 
        if(aliasToValueMapList.size()==0){
         	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
              regionTypeAjaxString += "<tr>";
              regionTypeAjaxString += "<td colspan='9' align='center'><b>No Result Found</b></td>";
             
              regionTypeAjaxString += "</tr>";
         	
         }else{

              
	     for (int i = 0; i < aliasToValueMapList.size(); i++) {
	    	 regionTypeAjaxString +="<tr>";
		Map<String,Object> list = aliasToValueMapList.get(i);
 		int j=i+1;
		
 		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
		double single=Double.parseDouble(list.get("sch_gs").toString())+Double.parseDouble(list.get("sch_no").toString())+Double.parseDouble(list.get("sch_ss").toString());
		regionTypeAjaxString +="<td align='right'>"+ single +"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("sch_do").toString() +"</td>";
		regionTypeAjaxString +="<td align='right'>"+ list.get("sch_ns").toString() +"</td>";
		double total=single+Double.parseDouble(list.get("sch_do").toString())+Double.parseDouble(list.get("sch_ns").toString());
		regionTypeAjaxString +="<td align='right'>"+ total +"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("sch_ord").toString()+"</td>";
		regionTypeAjaxString +="<td align='right'>"+list.get("sch_cor").toString()+"</td>";
		double volvo=Double.parseDouble(list.get("sch_vj").toString())+Double.parseDouble(list.get("sch_vvj").toString());
		regionTypeAjaxString +="<td align='right'>"+ volvo +"</td>";
		Totalsingle+=single;
		Totaldouble+=Double.parseDouble(list.get("sch_do").toString());
		Total_ns+=Double.parseDouble(list.get("sch_ns").toString());
		Total_schs+=total;
		Total_ord+=Double.parseDouble(list.get("sch_ord").toString());
		Total_cor+=Double.parseDouble(list.get("sch_cor").toString());
		Total_vol+=volvo;

//		regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//		
		

		
		   regionTypeAjaxString +="</tr>";
	}
			regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+ Totalsingle+"</td>"+"<td align='right'><b>"+ Totaldouble+"</td><td align='right'><b>"+ Total_ns+"</td><td align='right'><b>"+Total_schs+"</td>" +
					"<td align='right'><b>"+Total_ord+"</td><td align='right'><b>"+Total_cor+"</td><td align='right'><b>"+Total_vol+"</td></tr>" +"\n";  
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

public String getForm_VDivisionKilometerSummary(){
	

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
        

       

        regionTypeAjaxString += "<tr><th>Sl.no</th><th>Divn</th><th colspan='4 align='center'>CLASSIFICATION OF KILOMITERS</th><th>Total</th><th>Veh.uti</th><th>No Of</th><th>Total</th></tr>" +
        		"<tr><th></th><th></th><th>ORD</th><th>NS.KMS</th><th>CORONA</th><th>VOLVO</th><th>KMS</th><th></th><th>OT Sch's</th><th>OT Hours</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 

        if(aliasToValueMapList==null){
         	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
              regionTypeAjaxString += "<tr>";
              regionTypeAjaxString += "<td colspan='10' align='center'><b>No Result Found</b></td>";
             
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

public String getForm_VDivisionTripSummary(){
	

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
        


       

        regionTypeAjaxString += "<tr><th>Sl.no</th><th>Divn</th><th colspan='4' align='center'>CLASSIFICATION OF TRIP</th><th>Total</th><th></th><th></th></tr>" +
        		"<tr><th></th><th></th><th>ORD</th><th>NS</th><th>CORONA</th><th>VOLVO</th><th>Trips</th><th>STEERING HOURS</th><th>CREW UTILIZATION</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
//		

        if(aliasToValueMapList==null){
        	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
             regionTypeAjaxString += "<tr>";
             regionTypeAjaxString += "<td colspan='9' align='center'><b>No Result Found</b></td>";
            
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



public String getForm_VDivisionCrewSummary(){
	

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
        

        
       

        regionTypeAjaxString += "<tr><th></th><th>Divn</th><th colspan='7' align='center'>Crew Requirement</th><th colspan='3' align='center'>Vehicle  Req. For  Operation</th></tr>" +
        		"<tr><th>Divn</th><th>Driver Sanction</th><th>Spare 38.33%</th><th>Total</th><th>Conductor Sanction</th><th>Spare 38.33%</th><th>Total</th><th>Over All Sanction</th><th>For Open</th><th>Spare 4%</th><th>Total</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
//		
        if(aliasToValueMapList==null){
        	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
             regionTypeAjaxString += "<tr>";
             regionTypeAjaxString += "<td colspan='12' align='center'><b>No Result Found</b></td>";
            
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

public String getForm_VDivisionSummary(){
	

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
        

        
       

        regionTypeAjaxString += "<tr><th></th><th></th><th>Jan-16</th><th>Feb-16</th><th></th><th>Jan-16</th><th>Feb-16</th><th></th><th>Jan-16</th><th>Feb-16</th><th></th></tr>" +
        		"<tr><th>Sl.no</th><th>Divn</th><th colspan='2' align='center'>Total Sch's</th><th>Difference</th><th colspan='2' align='center'>Kms</th><th>Difference</th><th colspan='2' align='center'>Trips</th><th>Difference</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 

        if(aliasToValueMapList==null){
       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
            regionTypeAjaxString += "<tr>";
            regionTypeAjaxString += "<td colspan='11' align='center'><b>No Result Found</b></td>";
           
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
public String getForm_VDivisionSummary1(){
	

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
        

       

        regionTypeAjaxString += "<tr><th></th><th></th><th colspan='2' align='center'>Jan-16</th><th colspan='2' align='center'>Feb-16</th><th colspan='2' align='center'>Difference</th><th>Jan-16</th><th>Feb-16</th><th>Jan-16</th><th>Feb-16</th></tr>" +
        		"<tr><th>Sl.no</th><th>Divn</th><th>OT Sch's</th><th>OT Hours</th><th>OT Sch's</th><th>OT Hours</th><th>OT Sch's</th><th>OT Hours</th><th colspan='2' align='center'>Vehicle Uti</th><th colspan='2' align='center'>CREW UTILIZATION</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 

        if(aliasToValueMapList==null){
       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
            regionTypeAjaxString += "<tr>";
            regionTypeAjaxString += "<td colspan='12' align='center'><b>No Result Found</b></td>";
           
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
public String getForm_VDivisionSummary2(){
	

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
        

       

        regionTypeAjaxString += "<tr><th></th><th></th><th colspan='4' align='center'>Jan-15 Crew Position</th><th colspan='4' align='center'>Feb-16 Crew Position</th></tr>" +
        		"<tr><th>Sl.no</th><th>Divn</th><th>Sch's</th><th>Sanction</th><th>Working</th><th>Vacancy</th><th>Sch's</th><th>Sanction</th><th>Working</th><th>Vacancy</th></tr>";
//		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//		regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//				"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//				"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//				"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//				"</thead><tbody>";
		 
//		

        if(aliasToValueMapList==null){
          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
               regionTypeAjaxString += "<tr>";
               regionTypeAjaxString += "<td colspan='10' align='center'><b>No Result Found</b></td>";
              
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
