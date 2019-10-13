package com.trimax.its.vts.report.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

public class StudentPassCashCollection extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";
	String c=" ";

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;
	 int amount_total=0;
	    int vendor_total=0;
	    int net_total=0;
		int j=0;
	
	String regionTypeAjaxString = "";
	public String studentid;	
	public String execute(){
		  
//		  System.out.println("Enter into execute()");
		
		  
		  return "success";
	  }
	
	public String cashcollection(){
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			HttpServletRequest req = ServletActionContext.getRequest();
			String name=req.getParameter("value");
			String date=req.getParameter("date");
			CollectionReportDAO dao=new CollectionReportDAO();
			
//			String date1=dao.getDateFromPickerDate(startdate);
//			String date2=dao.getDateFromPickerDate(enddate);
			
			System.out.println("startdate====="+date);
			String date1=dao.getDateFromPickerDate(date);
		System.out.println("Name=="+date1);
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
	    
	   
		
			Common common = new Common();
		
		 String sql="SELECT `bmtc_card_id` BMTCCardNo, `student_name` StudentName,stu_caste_name, `degree_type` CollegeType, `payment_type` PaymentMode, `amount_to_collect` AmountCollected,10 VendorPayment,(amount_to_collect-10) NetPayment "+
				   " FROM `student_details` sd inner join pass_user_login ps on ps.user_id=sd.cashier_id WHERE `caste_category` = 'DIBYA' AND sd.`status` = 'PRINTED' and ps.user_name='"+name+"' and cash_collected_date='"+date1+"' ";
		 
		 Query query = session1.createSQLQuery(sql).addScalar("BMTCCardNo").addScalar("StudentName").addScalar("stu_caste_name").addScalar("CollegeType").addScalar("PaymentMode").addScalar("AmountCollected").addScalar("VendorPayment")
				 .addScalar("NetPayment");
		  
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String,Object>> aliasToValueMapList = query.list();
			
			regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Student Pass Cash Collection Report</h4></div>";

	        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
	    


			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString +="<thead><tr><th>S.No</th><th>BMTC Card No</th><th>Student Name</th><th>Student Caste</th><th>College Type</th><th>Payment Mode</th><th>Amount Collected</th><th>Vendor Payment</th><th>NetPayment</th>" +
				"</tr></thead><tbody>";
			
			JSONArray array = new JSONArray();
			  for (int i = 0; i < aliasToValueMapList.size(); i++) {
				  JSONArray ja = new JSONArray();
				Map<String,Object> list = aliasToValueMapList.get(i);
				j=i+1;
				regionTypeAjaxString +="<tr>";
				regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("BMTCCardNo").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("StudentName").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("stu_caste_name").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("CollegeType").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("PaymentMode").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("AmountCollected").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("VendorPayment").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("NetPayment").toString()+"</td>";
				
				amount_total =amount_total+Integer.parseInt(list.get("AmountCollected").toString());
				vendor_total =vendor_total+Integer.parseInt(list.get("VendorPayment").toString());
				net_total =net_total+Integer.parseInt(list.get("NetPayment").toString());
				
				 regionTypeAjaxString +="</tr>";
			
			  }
			  
			  
			  regionTypeAjaxString +="<tr>";
	            regionTypeAjaxString +="<td colspan='6'><center><b>Total</b></center></td><td>"+amount_total+"</td><td>"+vendor_total+"</td><td>"+net_total+"</td></tr>";
	   		 
			  
			  regionTypeAjaxString += "</table> </div> </div> ";
			  HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out;
					out = response.getWriter();
					out.print(regionTypeAjaxString);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
