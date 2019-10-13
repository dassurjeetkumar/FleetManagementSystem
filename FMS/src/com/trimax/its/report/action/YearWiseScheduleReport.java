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

public class YearWiseScheduleReport extends ActionSupport {

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
	
	public String getYearWiseSchedule(){
		
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
		 
		 String sql="Select count(form_four_Id) total_id,dat from(select form_four_Id,record_date, "+
                   " if(DATE_Format(record_date,'%m')<4, "+
                    "concat(DATE_Format(record_date,'%Y')-1,'-',DATE_Format(record_date,'%Y')), "+
                   " concat(DATE_Format(record_date,'%Y'),'-',DATE_Format(record_date,'%Y')+1)) dat "+
                    " from form_four_traffic_order ) a group by a.dat";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("total_id").addScalar("dat");
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
//			double Totalvalues=0.0;
			int increse=0;
		    
			
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>Augmentation of Schedules as per Traffic Order";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th>Sl No</th><th>Year</th><th>No Of Schdules</th><th>No.Of Schs Increased</th></tr>" ;

	        if(aliasToValueMapList.size()==0){
	          	 regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>";
	               regionTypeAjaxString += "<tr>";
	               regionTypeAjaxString += "<td colspan='4' align='center'><b>No Result Found</b></td>";
	              
	               regionTypeAjaxString += "</tr>";
	          	
	          }else{ 

                  
		     for (int i = 0; i < aliasToValueMapList.size(); i++) {
		    	 regionTypeAjaxString +="<tr>";
			Map<String,Object> list = aliasToValueMapList.get(i);
     		int j=i+1;
			
     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("dat").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("total_id").toString() +"</td>";
			
			int total=Integer.parseInt(list.get("total_id").toString());
			if(i==0){
			regionTypeAjaxString +="<td align='right'>0</td>";
			}else{
				increse=total-increse;
				if(increse>0){
				regionTypeAjaxString +="<td align='right'>"+increse+"</td>";
				}else{
					regionTypeAjaxString +="<td align='right'>0</td>";
				}
			}
			increse=total;


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
	
	public String getYearWiseScheduleData(){
		
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
		 
		 String sql="select count(schedule_number) total,dat from( "+
                    " select schedule_number,created_date, "+
                    " if(DATE_Format(created_date,'%m')<4, "+
                    " concat(DATE_Format(created_date,'%Y')-1,'-',DATE_Format(created_date,'%Y')), "+
                    " concat(DATE_Format(created_date,'%Y'),'-',DATE_Format(created_date,'%Y')+1)) dat from schedule )a group by a.dat";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("total").addScalar("dat");
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
//			double Totalvalues=0.0;

		    int increse=0;
			
			
//			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+orgname+" </br> </br>VOLVO FORM V AS ON "+cur_date+"";
//			//</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>
//	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	        

	       

	        regionTypeAjaxString += "<tr><th>Sl No</th><th>Year</th><th>No Of Schdules</th><th>No.Of Schs Increased</th><th>City Sch's</th><th>Sub Sch's</th><th>Volvo</th></tr>" ;

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
			
     		regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
//			regionTypeAjaxString +="<td align='right'><a href='#' onclick=javascript:jsFunction('"+list.get("Date").toString()+"');>"+list.get("Date").toString()+"</a>"+"<input type='hidden' name='date' id='date' value="+list.get("Date").toString()+"</input"+"</td>";
			regionTypeAjaxString +="<td align='right'>"+list.get("dat").toString()+"</td>";
			regionTypeAjaxString +="<td align='right'>"+ list.get("total").toString() +"</td>";
			int total=Integer.parseInt(list.get("total").toString());
			if(i==0){
				regionTypeAjaxString +="<td align='right'>0</td>";
				}else{
					increse=total-increse;
					if(increse>0){
					regionTypeAjaxString +="<td align='right'>"+increse+"</td>";
					}else{
						regionTypeAjaxString +="<td align='right'>0</td>";
					}
				}
				increse=total;
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
