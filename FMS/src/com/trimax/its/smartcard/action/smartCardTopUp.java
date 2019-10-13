package com.trimax.its.smartcard.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import com.trimax.its.util.HibernateUtil;

public class smartCardTopUp extends ActionSupport{
	public String startdate;
	public String enddate;
	
	
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

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	
	public String getsmartCardTopUpReport(){
		
		String regionTypeAjaxString="";
		try{
			HttpServletResponse response = ServletActionContext.getResponse();
		 Common common = new Common();
		 Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
			Session session1 = null;
			Transaction transaction  = null;
		 String sql="select * from bmtcGprs.axis_topup";
		 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 Query query = session1.createSQLQuery(sql);
		 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			String realpath = ServletActionContext.getRequest()
								.getRealPath("/");
		regionTypeAjaxString +="<div id='header' style='display: none;'><div align='center'></br>Smart Card Top Up Report </br></h4></div>";
		regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
		regionTypeAjaxString +="<thead><tr><th>Sl.No</th><th>User Name</th><th>Counter Name</th><th>Terminal Id</th><th>Transaction Date</th><th>Transaction Time</th><th>Transaction Amount</th></tr></thead><tbody>";				
		 if (aliasToValueMapList.size() > 0) {
			 
		 }else {
             regionTypeAjaxString += "<tr><td colspan='7'><b>No Records Found<b></td></tr>";
         }
		PrintWriter out;
         

			out = response.getWriter();
			out.print(regionTypeAjaxString);
			
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}

				       
		return null;
		
	}
	

}
