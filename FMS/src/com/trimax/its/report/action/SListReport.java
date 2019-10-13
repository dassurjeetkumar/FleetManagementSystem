package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class SListReport extends ActionSupport {
	
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

	String regionTypeAjaxString = "";
	
	public String execute() {
		
		return "success";
	}
	
	public String getS_ListReport(){
		

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
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Session session1 = null;
		Transaction transaction = null;
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 
		 String sql="select SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) city_ord_do,  "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) city_ord_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) city_ord_gs, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) city_vaj_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) city_vaj_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) city_vaj_gs, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) city_vvaj_do, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) city_vvaj_no, "+
				   " SUM(CASE WHEN service_limit='City' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) city_vvaj_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_ord_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_ord_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Ordinary' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_ord_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_cor_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_cor_no, "+ 
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Corona AC' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_cor_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_vaj_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_vaj_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_vaj_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_vvaj_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_vvaj_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='Vayu Vajra' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_vvaj_gs, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='BIG-10' and schedule_type_name='Day Out'  THEN sch_number ELSE 0 END) sub_big_do, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='BIG-10' and schedule_type_name='Night Out'  THEN sch_number ELSE 0 END) sub_big_no, "+
				   " SUM(CASE WHEN service_limit='Suburban' and brand_type_name='BIG-10' and schedule_type_name='General shift'  THEN sch_number ELSE 0 END) sub_big_gs, "+
				   " org_name from ( select count(schedule_number) sch_number,brand_type_name,service_limit,schedule_type_name,org_name from( "+
				   " select sd.schedule_number,brand_type_name,service_limit,schedule_type_name,org_name from schedule s "+
				   " inner join form_four f on f.schedule_number_id=s.schedule_id "+
				   " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
				   " inner join  org_chart oc on oc.org_chart_id=s.depot_id "+
				   " inner join schedule_service_limit sl on sl.schedule_id=s.schedule_id "+
				   " inner join brand_type bt on bt.brand_type_id=s.brand_type_id "+
				   " inner join  shift_type st on st.shift_type_id=sd.shift_type_id "+
				   " inner join schedule_type sp on sp.schedule_type_id=st.schedule_type_id "+
				   " where trip_type='2' and f.deleted_status='0' and f.current_status='ACTIVE' and s.status='NEW' and s.current_status='CASE WORKER' "+
			       " ) a group by a.schedule_type_name,a.service_limit,a.brand_type_name,a.org_name ) b group by b.org_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("city_ord_do").addScalar("city_ord_no").addScalar("city_ord_gs").addScalar("city_vaj_do").addScalar("city_vaj_no").addScalar("city_vaj_gs")
				 .addScalar("city_vvaj_do").addScalar("city_vvaj_no").addScalar("city_vvaj_gs").addScalar("sub_ord_do").addScalar("sub_ord_no").addScalar("sub_ord_gs").addScalar("sub_cor_do").addScalar("sub_cor_no").addScalar("sub_cor_gs")
				 .addScalar("sub_vaj_do").addScalar("sub_vaj_no").addScalar("sub_vaj_gs").addScalar("sub_vvaj_do").addScalar("sub_vvaj_no").addScalar("sub_vvaj_gs").addScalar("sub_big_do").addScalar("sub_big_no").addScalar("sub_big_gs");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totalvalues=0.0;

			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br>CENTRAL OFFICES : BANGALORE 27 </br>CLASSIFICATION OF SCHEDULES";
			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        
	      
	       


			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th></th><th colspan='9' align='center'>CITY SCHEDULES</th><th colspan='17' align='center'>SUBURBAN SCHEDULES</th><th colspan='12' align='center'>CITY+SUBURBAN</th></tr>" +
					"<tr><th></th><th colspan='3' align='center'>ORDINARY</th><th></th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='3' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
					"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
					"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
					"</thead><tbody>";
			 
			
			if(aliasToValueMapList.size()==0){
		       	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
		            regionTypeAjaxString += "<tr>";
		            regionTypeAjaxString += "<td colspan='41' align='center'><b>No Result Found</b></td>";
		           
		            regionTypeAjaxString += "</tr>";
		       	
		       }else{
			
                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("city_ord_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("city_ord_no").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("city_ord_gs").toString()+"</td>";
			
			double total_city_ord=Double.parseDouble(list.get("city_ord_do").toString())+Double.parseDouble(list.get("city_ord_no").toString())+Double.parseDouble(list.get("city_ord_gs").toString());
			regionTypeAjaxString +="<td align='right'>"+total_city_ord+"</td>";
			double city_vol_do=Double.parseDouble(list.get("city_vaj_do").toString())+Double.parseDouble(list.get("city_vvaj_do").toString());
			double city_vol_no=Double.parseDouble(list.get("city_vaj_no").toString())+Double.parseDouble(list.get("city_vvaj_no").toString());
			double city_vol_gs=Double.parseDouble(list.get("city_vaj_gs").toString())+Double.parseDouble(list.get("city_vvaj_gs").toString());

			regionTypeAjaxString +="<td align='right'>"+city_vol_do+"</td>";
			regionTypeAjaxString +="<td align='right'>"+city_vol_no+"</td>";
			regionTypeAjaxString +="<td align='right'>"+city_vol_gs+"</td>";
			double total_city_vol=city_vol_do+city_vol_no+city_vol_gs;
			regionTypeAjaxString +="<td align='right'>"+total_city_vol+"</td>";
			double total_city=total_city_ord+total_city_vol;
			regionTypeAjaxString +="<td align='right'>"+total_city+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_ord_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_ord_no").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_ord_gs").toString()+"</td>";
			double total_sub_ord=Double.parseDouble(list.get("sub_ord_do").toString())+Double.parseDouble(list.get("sub_ord_no").toString())+Double.parseDouble(list.get("sub_ord_gs").toString());
			regionTypeAjaxString +="<td align='right'>"+ total_sub_ord +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_cor_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_cor_no").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_cor_gs").toString()+"</td>";
			double total_sub_cor=Double.parseDouble(list.get("sub_cor_do").toString())+Double.parseDouble(list.get("sub_cor_no").toString())+Double.parseDouble(list.get("sub_cor_gs").toString());
			regionTypeAjaxString +="<td align='right'>"+ total_sub_cor +"</td>";
			
			double sub_vol_do=Double.parseDouble(list.get("sub_vaj_do").toString())+Double.parseDouble(list.get("sub_vvaj_do").toString());
			double sub_vol_no=Double.parseDouble(list.get("sub_vaj_no").toString())+Double.parseDouble(list.get("sub_vvaj_no").toString());
			double sub_vol_gs=Double.parseDouble(list.get("sub_vaj_gs").toString())+Double.parseDouble(list.get("sub_vvaj_gs").toString());

			regionTypeAjaxString +="<td align='right'>"+sub_vol_do+"</td>";
			regionTypeAjaxString +="<td align='right'>"+sub_vol_no+"</td>";
			regionTypeAjaxString +="<td align='right'>"+sub_vol_gs+"</td>";
			double total_sub_vol=sub_vol_do+sub_vol_no+sub_vol_gs;
			regionTypeAjaxString +="<td align='right'>"+total_sub_vol+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("sub_big_do").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_big_no").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("sub_big_gs").toString()+"</td>";
			double total_sub_big=Double.parseDouble(list.get("sub_big_do").toString())+Double.parseDouble(list.get("sub_big_no").toString())+Double.parseDouble(list.get("sub_big_gs").toString());
			regionTypeAjaxString +="<td align='right'>"+ total_sub_big +"</td>";
			
			double total_sub=total_sub_ord+total_sub_cor+total_sub_vol+total_sub_big;
			double total_sch=total_city+total_sub;
			regionTypeAjaxString +="<td align='right'>"+ total_sub +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ total_sch +"</td>";
			
			double total_do=Double.parseDouble(list.get("city_ord_do").toString())+city_vol_do+Double.parseDouble(list.get("sub_ord_do").toString())+Double.parseDouble(list.get("sub_cor_do").toString())+sub_vol_do+Double.parseDouble(list.get("sub_big_do").toString());
			double total_no=Double.parseDouble(list.get("city_ord_no").toString())+city_vol_no+Double.parseDouble(list.get("sub_ord_no").toString())+Double.parseDouble(list.get("sub_cor_no").toString())+sub_vol_no+Double.parseDouble(list.get("sub_big_no").toString());
			double total_gs=Double.parseDouble(list.get("city_ord_gs").toString())+city_vol_no+Double.parseDouble(list.get("sub_ord_gs").toString())+Double.parseDouble(list.get("sub_cor_gs").toString())+sub_vol_no+Double.parseDouble(list.get("sub_big_gs").toString());
           double total_bmtc=total_do+total_no+total_gs;
           regionTypeAjaxString +="<td align='right'>"+ total_do +"</td>";
           regionTypeAjaxString +="<td align='right'>"+ total_no +"</td>";
           regionTypeAjaxString +="<td align='right'>"+ total_gs +"</td>";
           regionTypeAjaxString +="<td align='right'>"+ total_bmtc +"</td>";
           regionTypeAjaxString +="<td align='right'>"+list.get("org_name").toString()+"</td>";
           regionTypeAjaxString +="<td align='right'></td>";
           regionTypeAjaxString +="<td align='right'></td>";
           regionTypeAjaxString +="<td align='right'></td>";
           regionTypeAjaxString +="<td align='right'></td>";
           regionTypeAjaxString +="<td align='right'></td>";
           regionTypeAjaxString +="<td align='right'></td>";
			
			
			
			

			
			   regionTypeAjaxString +="</tr>";
		}

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
