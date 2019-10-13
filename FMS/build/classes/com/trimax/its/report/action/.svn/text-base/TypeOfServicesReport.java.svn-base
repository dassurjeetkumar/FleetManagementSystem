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

public class TypeOfServicesReport extends ActionSupport {

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
	
	public String getTypeOfService(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
		String date1=dao.getDateFromPickerDate(startdate);
//		String date2=dao.getDateFromPickerDate(enddate);
		Common common = new Common();
//		String orgname=dao.getOrgName();
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
		 
		 String sql="Select round(sum(ord_sch),2) tot_sch,round(sum(ord_dista),2) tot_dista,round(sum(ord_trips),2) tot_trip,service_type_name from( "+
                    " Select  ord_sch,ord_dista,ord_trips,service_type_id,service_type_name from schedule s "+
                    " inner join form_four ff on ff.schedule_number_id=s.schedule_id "+
                    " inner join schedule s1 on s1.schedule_id=ff.schedule_number_id "+
                    " inner join service_type st on st.service_type_id=s1.schedule_service_type "+
                    " inner join (SELECT count(schedule_number) as ord_sch,ifnull(sum(distance),'0.0')  as ord_dista,max(trip_number) ord_trips,ff1.form_four_id FROM schedule_details "+ 
                    " left join form_four ff1 on ff1.form_four_id=schedule_details.form_four_id "+
                    " WHERE trip_type='2'  group by ff1.form_four_id) a on a.form_four_id=ff.form_four_id "+ 
                    " where ff.current_status ='ACTIVE' and ff.deleted_status='0' AND  (ff.effective_end_date IS NULL or ff.effective_end_date >='"+date1+"')   ) e group by e.service_type_id";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("tot_sch").addScalar("tot_dista").addScalar("tot_trip").addScalar("service_type_name");
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			double Totaltrip=0.0;
			double Total_Sch=0.0;
		    double Total_Schkms=0.0;

		    
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        


	        regionTypeAjaxString += "<tr><th>Sl No</th><th>Type Of Services</th><th>No Of Schdules</th><th>Sch Kms</th><th>Trips</th></tr>" ;

	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='5' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{
	        
	        
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("service_type_name").toString()+"</td>";
			
			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_dista").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_trip").toString() +"</td>";
			
			Total_Sch+=Double.parseDouble(list.get("tot_sch").toString());
			Total_Schkms=Double.parseDouble(list.get("tot_dista").toString());
			Totaltrip=Double.parseDouble(list.get("tot_trip").toString());
			
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_Sch+"</td>"+"<td align='right'><b>"+ Total_Schkms+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+Totaltrip+"</td></tr>" +"\n";  
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
	
	public String getTypeOfServiceData(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
		String date1=dao.getDateFromPickerDate(startdate);
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
		 
		 String sql="select brand_type_name,count(schedule_number) as schedule_number from brand_type b "+
				  " inner join schedule s on s.brand_type_id=b.brand_type_id "+
				  " where b.status='ACTIVE' and b.deleted_status='0' and s.status = 'NEW' AND s.current_status = 'CASE WORKER' AND s.deleted_status = '0' "+
				  " AND  (s.effective_end_date IS NULL or s.effective_end_date >='"+date1+"')  group by brand_type_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("brand_type_name").addScalar("schedule_number");
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			
		    double Total_Sch=0.0;
		    
		    
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th>Sl No</th><th>Brand Types</th><th>No Of Schs</th></tr>" ;

			
	        
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='3' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{

                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("brand_type_name").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("schedule_number").toString() +"</td>";
			
			
			

			
			Total_Sch+=Double.parseDouble(list.get("schedule_number").toString());
			
			
		
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='right'><b>"+Total_Sch+"</td></tr>" +"\n";  
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
	
	public String getCategoryOfServices(){
		
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
		 
		 String sql="select brand_type_name,SUM(CASE WHEN brand_type_name='Ordinary' and schedule_type_name='Day Out' THEN schedule_number ELSE 0 END) ord_day_out, "+
				 	" SUM(CASE WHEN brand_type_name='Ordinary' and schedule_type_name='General shift' THEN schedule_number ELSE 0 END) ord_gen_out, "+
				 	" SUM(CASE WHEN brand_type_name='Ordinary' and schedule_type_name='Night Out' THEN schedule_number ELSE 0 END) ord_night_out, "+
				    " SUM(CASE WHEN brand_type_name='Ordinary' and schedule_type_name='Night Service' THEN schedule_number ELSE 0 END) ord_night_service, "+
				 	" SUM(CASE WHEN brand_type_name='Ordinary' and schedule_type_name='Split Service' THEN schedule_number ELSE 0 END) ord_split_service, "+
				 	" SUM(CASE WHEN brand_type_name='Ordinary' and schedule_type_name='General Night Out' THEN schedule_number ELSE 0 END) ord_gen_night_out, "+ 
				 	" SUM(CASE WHEN brand_type_name='Ordinary' and schedule_type_name='Moffusil' THEN schedule_number ELSE 0 END) ord_moff_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Corona AC' and schedule_type_name='Day Out' THEN schedule_number ELSE 0 END) cor_day_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Corona AC' and schedule_type_name='General shift' THEN schedule_number ELSE 0 END) cor_gen_out,  "+ 
				 	" SUM(CASE WHEN brand_type_name='Corona AC' and schedule_type_name='Night Out' THEN schedule_number ELSE 0 END) cor_night_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Corona AC' and schedule_type_name='Night Service' THEN schedule_number ELSE 0 END) cor_night_service, "+ 
				 	" SUM(CASE WHEN brand_type_name='Corona AC' and schedule_type_name='Split Service' THEN schedule_number ELSE 0 END) cor_split_service,  "+
				 	" SUM(CASE WHEN brand_type_name='Corona AC' and schedule_type_name='General Night Out' THEN schedule_number ELSE 0 END) cor_gen_night_out, "+  
				 	" SUM(CASE WHEN brand_type_name='Corona AC' and schedule_type_name='Moffusil' THEN schedule_number ELSE 0 END) cor_moff_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' and schedule_type_name='Day Out' THEN schedule_number ELSE 0 END) var_day_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' and schedule_type_name='General shift' THEN schedule_number ELSE 0 END) var_gen_out, "+ 
				 	" SUM(CASE WHEN brand_type_name='Vajra' and schedule_type_name='Night Out' THEN schedule_number ELSE 0 END) var_night_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' and schedule_type_name='Night Service' THEN schedule_number ELSE 0 END) var_night_service, "+  
				 	" SUM(CASE WHEN brand_type_name='Vajra' and schedule_type_name='Split Service' THEN schedule_number ELSE 0 END) var_split_service,  "+
				 	" SUM(CASE WHEN brand_type_name='Vajra' and schedule_type_name='General Night Out' THEN schedule_number ELSE 0 END) var_gen_night_out, "+   
				 	" SUM(CASE WHEN brand_type_name='Vajra' and schedule_type_name='Moffusil' THEN schedule_number ELSE 0 END) var_moff_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' and schedule_type_name='Day Out' THEN schedule_number ELSE 0 END) vvar_day_out, "+  
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' and schedule_type_name='General shift' THEN schedule_number ELSE 0 END) vvar_gen_out, "+  
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' and schedule_type_name='Night Out' THEN schedule_number ELSE 0 END) vvar_night_out,  "+
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' and schedule_type_name='Night Service' THEN schedule_number ELSE 0 END) vvar_night_service, "+  
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' and schedule_type_name='Split Service' THEN schedule_number ELSE 0 END) vvar_split_service,  "+
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' and schedule_type_name='General Night Out' THEN schedule_number ELSE 0 END) vvar_gen_night_out, "+  
				 	" SUM(CASE WHEN brand_type_name='Vayu Vajra' and schedule_type_name='Moffusil' THEN schedule_number ELSE 0 END) vvar_moff_out  "+
				 	" from ( select brand_type_name,count(schedule_number) as schedule_number,st.schedule_type_name from schedule s   "+
				 	" inner join brand_type b  on b.brand_type_id=s.brand_type_id   "+
				 	" inner join schedule_type st on st.schedule_type_id=s.schedule_type  "+  
				 	" where b.status='ACTIVE' and b.deleted_status='0' and s.status='NEW' and s.current_status='CASE WORKER' "+
				 	" and s.deleted_status=0  and st.status='ACTIVE' and st.deleted_status=0 and b.brand_type_id in ('6','8','9','12') AND  ( s.effective_end_date IS NULL or s.effective_end_date >='"+date1+"')    "+
				 	" group by schedule_type_name,brand_type_name  ) a group by a.brand_type_name";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("brand_type_name").addScalar("ord_day_out").addScalar("ord_gen_out").addScalar("ord_night_out").addScalar("ord_night_service").addScalar("ord_split_service").addScalar("ord_gen_night_out").addScalar("ord_moff_out").addScalar("cor_day_out").addScalar("cor_gen_out").addScalar("cor_night_out").addScalar("cor_night_service").addScalar("cor_split_service").addScalar("cor_gen_night_out").addScalar("cor_moff_out")
				    .addScalar("var_day_out").addScalar("var_gen_out").addScalar("var_night_out").addScalar("var_night_service").addScalar("var_split_service").addScalar("var_gen_night_out").addScalar("var_moff_out").addScalar("vvar_day_out").addScalar("vvar_gen_out").addScalar("vvar_night_out").addScalar("vvar_night_service").addScalar("vvar_split_service").addScalar("vvar_gen_night_out").addScalar("vvar_moff_out");
//		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();

			int var_dayout=0;
			int var_genout=0;
			int var_nightout=0;
			int var_night_ser=0;
			int var_split_ser=0;
			int var_gen_nightout=0;
			int var_moff_out=0;
			int var_total=0;
			int vvar_dayout=0;
			int vvar_genout=0;
			int vvar_nightout=0;
			int vvar_night_ser=0;
			int vvar_split_ser=0;
			int vvar_gen_nightout=0;
			int vvar_moff_out=0;
			int vvar_total=0;
		    
			int ord_dayout=0;
			int ord_genout=0;
			int ord_nightout=0;
			int ord_night_ser=0;
			int ord_split_ser=0;
			int ord_gen_nightout=0;
			int ord_moff_out=0;
			int total_ord=0;
			int cor_dayout=0;
			int cor_genout=0;
			int cor_nightout=0;
			int cor_night_ser=0;
			int cor_split_ser=0;
			int cor_gen_nightout=0;
			int cor_moff_out=0;
			int total_cor=0;
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th></th><th>Day out</th><th>General Shift</th><th>Night Out</th><th>Night Service</th><th>Split Duty Service</th><th>General Night Out</th><th>Moffusil</th><th>Total</th></tr>" ;

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
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			
     		if(list.get("brand_type_name")== "Ordinary" || list.get("brand_type_name").equals("Ordinary")){
     			regionTypeAjaxString +="<td align='right'>"+list.get("brand_type_name").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("ord_day_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("ord_gen_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("ord_night_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("ord_night_service").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("ord_split_service").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("ord_gen_night_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("ord_moff_out").toString()+"</td>";
     			ord_dayout=Integer.parseInt(list.get("ord_day_out").toString());
     			ord_genout=Integer.parseInt(list.get("ord_gen_out").toString());
     			ord_nightout=Integer.parseInt(list.get("ord_night_out").toString());
     			ord_night_ser=Integer.parseInt(list.get("ord_night_service").toString());
     			ord_split_ser=Integer.parseInt(list.get("ord_split_service").toString());
     			ord_gen_nightout=Integer.parseInt(list.get("ord_gen_night_out").toString());
     			ord_moff_out=Integer.parseInt(list.get("ord_moff_out").toString());
     			total_ord=Integer.parseInt(list.get("ord_day_out").toString())+Integer.parseInt(list.get("ord_gen_out").toString())+Integer.parseInt(list.get("ord_night_out").toString())+
     					Integer.parseInt(list.get("ord_night_service").toString())+Integer.parseInt(list.get("ord_split_service").toString())+
     					Integer.parseInt(list.get("ord_gen_night_out").toString())+Integer.parseInt(list.get("ord_moff_out").toString());
     			regionTypeAjaxString +="<td align='right'>"+total_ord+"</td>";
     			
     		}else if(list.get("brand_type_name")== "Corona AC" || list.get("brand_type_name").equals("Corona AC")){
     			
     			regionTypeAjaxString +="<td align='right'>"+list.get("brand_type_name").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("cor_day_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("cor_gen_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("cor_night_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("cor_night_service").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("cor_split_service").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("cor_gen_night_out").toString()+"</td>";
     			regionTypeAjaxString +="<td align='right'>"+list.get("cor_moff_out").toString()+"</td>";
     			cor_dayout=Integer.parseInt(list.get("cor_day_out").toString());
     			cor_genout=Integer.parseInt(list.get("cor_gen_out").toString());
     			cor_nightout=Integer.parseInt(list.get("cor_night_out").toString());
     			cor_night_ser=Integer.parseInt(list.get("cor_night_service").toString());
     			cor_split_ser=Integer.parseInt(list.get("cor_split_service").toString());
     			cor_gen_nightout=Integer.parseInt(list.get("cor_gen_night_out").toString());
     			cor_moff_out=Integer.parseInt(list.get("cor_moff_out").toString());
     			total_ord=Integer.parseInt(list.get("cor_day_out").toString())+Integer.parseInt(list.get("cor_gen_out").toString())+Integer.parseInt(list.get("cor_night_out").toString())+
     					Integer.parseInt(list.get("cor_night_service").toString())+Integer.parseInt(list.get("cor_split_service").toString())+
     					Integer.parseInt(list.get("cor_gen_night_out").toString())+Integer.parseInt(list.get("cor_moff_out").toString());
     			regionTypeAjaxString +="<td align='right'>"+total_ord+"</td>";
     			
     			
     		}else if(list.get("brand_type_name")== "Vajra" || list.get("brand_type_name").equals("Vajra")){
     			
     			var_dayout=Integer.parseInt(list.get("var_day_out").toString());
     			var_genout=Integer.parseInt(list.get("var_gen_out").toString());
     			var_nightout=Integer.parseInt(list.get("var_night_out").toString());
     			var_night_ser=Integer.parseInt(list.get("var_night_service").toString());
     			var_split_ser=Integer.parseInt(list.get("var_split_service").toString());
     			var_gen_nightout=Integer.parseInt(list.get("var_gen_night_out").toString());
     			var_moff_out=Integer.parseInt(list.get("var_moff_out").toString());
     			var_total=var_dayout+var_genout+var_nightout+var_night_ser+var_split_ser+var_gen_nightout+var_moff_out;
     			
     		}else if(list.get("brand_type_name")== "Vayu Vajra" || list.get("brand_type_name").equals("Vayu Vajra")){
     			
     			vvar_dayout=Integer.parseInt(list.get("vvar_day_out").toString());
     			vvar_genout=Integer.parseInt(list.get("vvar_gen_out").toString());
     			vvar_nightout=Integer.parseInt(list.get("vvar_night_out").toString());
     			vvar_night_ser=Integer.parseInt(list.get("vvar_night_service").toString());
     			vvar_split_ser=Integer.parseInt(list.get("vvar_split_service").toString());
     			vvar_gen_nightout=Integer.parseInt(list.get("vvar_gen_night_out").toString());
     			vvar_moff_out=Integer.parseInt(list.get("vvar_moff_out").toString());
     			vvar_total=vvar_dayout+vvar_genout+vvar_nightout+vvar_night_ser+vvar_split_ser+vvar_gen_nightout+vvar_moff_out;
     			
     			
     		}
		
			   regionTypeAjaxString +="</tr>";
		}
		     
		     regionTypeAjaxString +="<tr>";
		     
		     int vol_dayout=var_dayout+vvar_dayout;
	     		int vol_genout=var_genout+vvar_genout;
	     		int vol_nightout=var_nightout+vvar_nightout;
	     		int vol_night=var_night_ser+vvar_night_ser;
	     		int vol_split=var_split_ser+vvar_split_ser;
	     		int vol_gen_nightout=var_gen_nightout+vvar_gen_nightout;
	     		int vol_moff_out=var_moff_out+vvar_moff_out;
	     		int total_vol=var_total+vvar_total;
	     		
	     		regionTypeAjaxString +="<td align='right'>Volvo</td>";
	 			regionTypeAjaxString +="<td align='right'>"+vol_dayout+"</td>";
	 			regionTypeAjaxString +="<td align='right'>"+vol_genout+"</td>";
	 			regionTypeAjaxString +="<td align='right'>"+vol_nightout+"</td>";
	 			regionTypeAjaxString +="<td align='right'>"+vol_night+"</td>";
	 			regionTypeAjaxString +="<td align='right'>"+vol_split+"</td>";
	 			regionTypeAjaxString +="<td align='right'>"+vol_gen_nightout+"</td>";
	 			regionTypeAjaxString +="<td align='right'>"+vol_moff_out+"</td>";
	 			regionTypeAjaxString +="<td align='right'>"+total_vol+"</td>";
	 			regionTypeAjaxString +="</tr>";
		     
	 			int total_dayout=ord_dayout+cor_dayout+var_dayout+vvar_dayout;
	 			int total_genout=ord_genout+cor_genout+var_genout+vvar_genout;
	 			int total_nightout=ord_nightout+cor_nightout+var_nightout+vvar_nightout;
	 			int total_night_ser=ord_night_ser+cor_night_ser+var_dayout+vvar_dayout;
	 			int total_split=ord_split_ser+cor_split_ser+var_split_ser+vvar_split_ser;
	 			int total_gen_night=ord_gen_nightout+cor_dayout+var_gen_nightout+vvar_gen_nightout;
	 			int total_moff=ord_moff_out+cor_moff_out+var_moff_out+vvar_moff_out;
	 			int total=total_ord+total_cor+var_dayout+total_vol;
	 			
	 			double total_dayout1 =(double) total_dayout;
	 			double total_genout1=(double) total_genout;
	 			double total_nightout1=(double)total_nightout;
	 			double total_night_ser1=(double)total_night_ser;
	 			double total_split1=(double) total_split;
	 			double total_gen_night1=(double) total_gen_night;
	 			double total_moff1=(double) total_moff;
	 			double total1=(double) total;
	 			
	 			double per_dayout=total_dayout1/total1*100;
	 			double per_gen=total_genout1/total1*100;
	 			double per_night=total_nightout1/total1*100;
	 			double per_night_ser=total_night_ser1/total1*100;
	 			double per_split=total_split1/total1*100;
	 			double per_gen_night=total_gen_night1/total1*100;
	 			double per_moff=total_moff1/total1*100;
	 			double per_total=per_dayout+per_night;
	 			
	 			String per_dayout1=String.format("%.2f", per_dayout);
	 			String per_gen1=String.format("%.2f", per_gen);
	 			String per_night1=String.format("%.2f", per_night);
	 			String per_night_ser1=String.format("%.2f", per_night_ser);
	 			String per_split1=String.format("%.2f", per_split);
	 			String per_gen_night1=String.format("%.2f", per_gen_night);
	 			String per_moff1=String.format("%.2f", per_moff);
	 			String per_total1=String.format("%.2f", per_total);
		     
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ total_dayout+"</td>"+"<td align='right'><b>"+ total_genout+"</td><td align='right'><b>"+ total_nightout+"</td><td align='right'><b>"+total_night_ser+"</td>" +
						"<td align='right'><b>"+total_split+"</td><td align='right'><b>"+total_gen_night+"</td><td align='right'><b>"+total_moff+"</td><td align='right'><b>"+total+"</td></tr>" +"\n";  
////				
				regionTypeAjaxString +="<tr><td><center><b>In %</b></center></td><td align='right'><b>"+ per_dayout1+"</td>"+"<td align='right'><b>"+ per_gen1+"</td><td align='right'><b>"+ per_night1+"</td><td align='right'><b>"+per_night_ser1+"</td>" +
						"<td align='right'><b>"+per_split1+"</td><td align='right'><b>"+per_gen_night1+"</td><td align='right'><b>"+per_moff1+"</td><td align='right'><b>"+per_total1+"</td></tr>" +"\n";  
				
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
	
	public String getCategoryOfServicesData(){
		
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
		 
		 String sql="Select service_limit,count(sd.schedule_number) tot_sch,sum(distance) dista,Max(trip_number) trip from schedule_service_limit sl "+
				  " inner join schedule s on s.schedule_id=sl.schedule_id "+
				  " inner join form_four f on f.schedule_number_id=s.schedule_id "+
				  " inner join schedule_details sd on sd.form_four_id=f.form_four_id "+
				  " where trip_type='2' and f.current_status ='ACTIVE' and f.deleted_status='0' group by service_limit";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("service_limit").addScalar("tot_sch").addScalar("dista").addScalar("trip");
	  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();

		    double Total_Sch=0.0;

		    double Total_Kms=0.0;

		    double Total_Trip=0.0;
		    String Total_Sch1="";
		    String Total_Kms1="";
		    String Total_Trip1="";
		    
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	        regionTypeAjaxString += "<tr><th></th><th>Schs</th><th>Kms</th><th>Trips</th><th>Routes</th></tr>" ;
        
	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='5' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{
	        
	        
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
//     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("service_limit").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_sch").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("dista").toString() +"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("trip").toString() +"</td>";
			
			
			

			
			
			Total_Sch+=Double.parseDouble(list.get("tot_sch").toString());
			
			Total_Kms+=Double.parseDouble(list.get("dista").toString());
	
			Total_Trip+=Double.parseDouble(list.get("trip").toString());
			
			 Total_Sch1=String.format("%.2f", Total_Sch);
			 Total_Kms1=String.format("%.2f", Total_Kms);
			 Total_Trip1=String.format("%.2f", Total_Trip);
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			
//			
			   regionTypeAjaxString +="</tr>";
		}
				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ Total_Sch1+"</td>"+"<td align='right'><b>"+ Total_Kms1+"</td><td align='right'><b>"+ Total_Trip1+"</td></tr>" +"\n";  
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
	
	public String getCategoryOfServicesDataSummary(){
		
		try {
		CollectionReportDAO dao=new CollectionReportDAO();
//		System.out.println("Depot Name==="+depotName);
//		String date1=dao.getDateFromPickerDate(startdate);
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
		 
		 String sql="";
		 
//		 Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("sum(vschedules)").addScalar("sum(vbrand_type_id)").addScalar("sum(vtotal_km)").addScalar("sum(vno_of_trips)").addScalar("sum(vvschedules)").addScalar("sum(vvbrandtype)").addScalar("sum(vvtotal_km)").addScalar("sum(vvno_of_trips)").addScalar("totschds").addScalar("totkms").addScalar("tot_no_of_trips").addScalar("vajraveuti").addScalar("vayuveuti").addScalar("totveuti");
////		  
//		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String,Object>> aliasToValueMapList = query.list();
//			double Totalvalues=0.0;
//			double TotalV_Sch=0.0;
//		    double TotalVV_Sch=0.0;
//		    double Total_Sch=0.0;
//		    double TotalV_kms=0.0;
//		    double TotalVV_Kms=0.0;
//		    double Total_Kms=0.0;
//		    double TotalV_Trip=0.0;
//		    double TotalVV_Trip=0.0;
//		    double Total_Trip=0.0;
//		    double TotalV_Uti=0.0;
//		    double TotalVV_Uti=0.0;
//		    double Total_Uti=0.0;
		    
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			String filePath = "Ticketing/";
			String fileName = "CategoryOfServicesDataSummaryReport.txt";
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        
//	        String nwkr="\n\n"
//	          +	  "                                     "+orgname+"                                                                          \n"
//      		  +   "                                            "+depot+"    " 
//      		  +   "                                    BANGALORE METROPOLITAN TRANSPORT CORPORATION                                                                                 \n" 
//      		  +   "                                    Volvo Sch And KMS Report  AS ON  "+runDateTime+"                                                          					 \n"
////      		  +   "                                 From Date :-"+startdate+ "TO Date:- "+enddate+"                                                       \n "
//      		  +   "                                                               Printed Date:-"+runDateTime+"               \n";
//        
	        
	       

	        regionTypeAjaxString += "<tr><th></th><th>Numerical Routes</th><th>Alpha Numerical Routes</th></tr>" ;
//			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
//			regionTypeAjaxString +="<thead><tr><th></th><th colspan='8' align='center'>CITY SCHEDULES</th><th colspan='30' align='center'>SUBURBAN SCHEDULES</th></tr>" +
//					"<tr><th></th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>VOLVO SERVICES</th><th>TOTAL</th><th colspan='4' align='center'>ORDINARY</th><th colspan='4' align='center'>CORONA  SERVICES</th><th colspan='4' align='center'>VOLVO SERVICES</th><th colspan='4' align='center'>BIG-10</th><th>TOTAL</th><th colspan='5' align='center'>BMTC SCHEDULES</th><th colspan='4' align='center'>DRIVER REQUIREMENT</th><th colspan='4' align='center'>CONDUCTOR REQUIREMENT</th></tr>" +     
//					"<tr><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>CITY</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th>SUB URBAN</th><th>BMTC</th><th>D/O</th><th>N/O</th><th>G/S</th><th>TOTAL</th><th>Depot</th><th>For opn</th><th>38.33%</th><th>Total</th><th>For opn</th><th>38.33%</th><th>Total</th></tr>"+
//					"<tr><th>Depot</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>D/O</th><th>N/O</th><th>G/S</th><th>Total</th><th>SCHS</th><th>SCHS</th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th><th></th></tr>"+
//					"</thead><tbody>";
			 
//			
//			String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ __ __ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ __\n" 
//					+add("",5)+""+add("Depot",10)+ "|"+add("Vajra",10)+ "|"+add("Vayu Vajra",16)+"|"+add("Total",15)+"|"+add("Vajra",10)+ "|"+add("Vayu Vajra",16)+"|"+add("Total",15)+"|"+add("Vajra",10)+ "|"+add("Vayu Vajra",16)+"|"+add("Total",15)+"|"+add("Vajra",10)+ "|"+add("Vayu Vajra",16)+"|"+add("Total",15)+ "|"+"\n"
//					+"|"+add("No",5)+ "|"+add("Sch's",10)+"|"+add("Sch's",10)+"|"+add("Sch's",16)+ "|"+add("Kms",15)+"|"+add("Kms",10)+"|"+add("Kms",16)+ "|"+add("Trip",15)+"|"+add("Trip",10)+"|"+add("Trip",10)+"|"+add("Ve.Uti.",10)+"|"+add("Ve.Uti.",10)+"|"+add("Ve.Uti.",10)+ "|"+"\n"
//					+ " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ __ __ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ __\n";
//			 		
			    
			    String path = realpath + filePath + fileName;
//		        str+=ft+nwkr+add(headingprint,5);
                  
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
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vtotal_km)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvtotal_km)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("totkms").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vno_of_trips)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("sum(vvno_of_trips)").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("tot_no_of_trips").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+ list.get("vajraveuti").toString() +"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("vayuveuti").toString()+"</td>";
//			regionTypeAjaxString +="<td align='right'>"+list.get("totveuti").toString()+"</td>";
//			
//			
////			String  date = list.get("Date").toString();
////			String etim = list.get("ETIMServiceTax").toString();
////			String bag = list.get("BagServiceTax").toString();
////			String pass = list.get("PassServiceTax").toString();
////			Totalvalues=Double.parseDouble(etim)+Double.parseDouble(bag)+Double.parseDouble(pass);
//			
//			TotalV_Sch+=Double.parseDouble(list.get("sum(vschedules)").toString());
//			TotalVV_Sch+=Double.parseDouble(list.get("sum(vvschedules)").toString());
//			Total_Sch+=Double.parseDouble(list.get("totschds").toString());
//			TotalV_kms+=Double.parseDouble(list.get("sum(vtotal_km)").toString());
//			TotalVV_Kms+=Double.parseDouble(list.get("sum(vvtotal_km)").toString());
//			Total_Kms+=Double.parseDouble(list.get("totkms").toString());
//			TotalV_Trip+=Double.parseDouble(list.get("sum(vno_of_trips)").toString());
//			TotalVV_Trip+=Double.parseDouble(list.get("sum(vvno_of_trips)").toString());
//			Total_Trip+=Double.parseDouble(list.get("tot_no_of_trips").toString());
//			TotalV_Uti+=Double.parseDouble(list.get("vajraveuti").toString());
//			TotalVV_Uti+=Double.parseDouble(list.get("vayuveuti").toString());
//			Total_Uti+=Double.parseDouble(list.get("totveuti").toString());
			
			
//			regionTypeAjaxString +="<td align='right'>"+ Totalvalues +"</td>";
//			
			
//			str+=""+add("",5)+"" +add(String.valueOf(j), 10) +"|"  + add(date, 10) +"|" + add(etim,16)+"|" + add(bag,15)+"|" + add(pass,16)+"|" + add(String.valueOf(Totalvalues),10)+"|" + "\n";
//			   if(j%55==0){
//				   str+=f2+add(headingprint,5);
//			   }
//			
//			   regionTypeAjaxString +="</tr>";
//		}
//		     str+= "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ __ __ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ __\n";
//				regionTypeAjaxString +="<tr><td><center><b>Total</b></center></td><td align='right'><b>"+ TotalV_Sch+"</td>"+"<td align='right'><b>"+ TotalVV_Sch+"</td><td align='right'><b>"+ Total_Sch+"</td><td align='right'><b>"+TotalV_kms+"</td>" +
//						"<td align='right'><b>"+TotalVV_Kms+"</td><td align='right'><b>"+Total_Kms+"</td><td align='right'><b>"+TotalV_Trip+"</td><td align='right'><b>"+TotalVV_Trip+"</td><td align='right'><b>"+Total_Trip+"</td>" +
//								"<td align='right'><b>"+TotalV_Uti+"</td><td align='right'><b>"+TotalVV_Uti+"</td><td align='right'><b>"+Total_Uti+"</td></tr>" +"\n";  
////				
				 regionTypeAjaxString += "</table></div> </div>  ";
		 
//				 str+= ""+add("",5)+"" + add("Sub Total", 22) + "|" + add(String.valueOf(Totaletimservicetaxamount), 16) +"|" + add(String.valueOf(Totalbagservicetaxamount),15)+ "|"+ add(String.valueOf(Totalpasssservicetaxamount),16)+"|"+ add(String.valueOf(Granttotalservicetaxamount),10)+ "|"+"\n";
				 
		 
//		 str+="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ __ __ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ __\n";
		 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
		
		
			
			FileOutputStream FOS = new FileOutputStream(path);
			PrintWriter PW = new PrintWriter(FOS);
			
		String p=str;

		PW.println(p);
		PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	
	
	static String add(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
		}
		a1 = a1 - m1;
		for (int i = 0; i <= a1; i++) {
			sb.append(" ");
		}
		String sb1 = sb.toString();
		return sb1;
	}
	
	static String addMoney(String str1, int a1) {
        StringBuilder sb = new StringBuilder(str1);
        StringBuilder sb2 = new StringBuilder();

        //String sb1 =
        int m1 = str1.length();
        if (m1 >= a1) {
            str1.substring(0, a1 - 1);
            return str1;
        }
        a1 = a1 - m1;
        for (int i = 0; i < a1; i++) {
            sb2.append(" ");
        }
        sb2.append(sb);
        String sb1 = sb2.toString();
        return sb1;
    }
	
	
	
}
