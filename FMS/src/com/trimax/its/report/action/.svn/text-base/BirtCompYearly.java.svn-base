package com.trimax.its.report.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class BirtCompYearly {
	public String getrevdata(){

		HttpServletRequest req = ServletActionContext.getRequest();
		   HttpServletResponse response = ServletActionContext.getResponse();
		String datefy=req.getParameter("fromdate");
		String dateey=req.getParameter("todate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date="";
		String date1="";
		String date2="";
		String fromDate="";
		String toDate="";
	String date3="";
		if(datefy==null && datefy==null){
			
			date="2016-01-01";
			date1="2016-12-31";
			date2="2017-01-01";
			date3="2017-12-31";
			fromDate="2016";
			toDate="2017";
		}else{
		date=datefy+"-01-01";
		date1=datefy+"-12-31";
		date2=dateey+"-01-01";
		date3=dateey+"-12-31";
		fromDate=datefy;
		toDate=dateey;
		}
		String url="https"+"://"+req.getServerName()+"/birt/frameset?__report=yearlycomparision.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&From Date="+fromDate+"&To Date="
				+ ""+toDate+"&date="+date+"&date1="+date1+"&date2="+date2+"&date3="+date3;
		/*String url="http"+"://"+req.getServerName()+":8080/birt/frameset?__report=yearlycomparision.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&From Date="+fromDate+"&To Date="
				+ ""+toDate+"&date="+date+"&date1="+date1+"&date2="+date2+"&date3="+date3;*/
		//String url="http"+"://"+req.getServerName()+":8080/birt/frameset?__report=customerrating.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&From Date="+date+"&To Date="+date1;

		//+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"
		req.getSession().setAttribute("url",url);	
		return "birt";

	}
}
