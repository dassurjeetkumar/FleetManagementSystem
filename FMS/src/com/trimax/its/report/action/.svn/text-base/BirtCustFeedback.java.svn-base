package com.trimax.its.report.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class BirtCustFeedback {


	public String getrevdata(){

		HttpServletRequest req = ServletActionContext.getRequest();
		   HttpServletResponse response = ServletActionContext.getResponse();
		String date=req.getParameter("fromdate");
		String date1=req.getParameter("todate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		Date d=new Date();
		Date dd=subtractDays(d);
		String s=sdf.format(dd);
		if(date==null && date1==null){
			
			date=s;
			date1=s;
		}else{
			String[] arr=date.split("/");
			date=arr[2]+"-"+arr[0]+"-"+arr[1];
			String[] arr1=date1.split("/");
			date1=arr1[2]+"-"+arr1[0]+"-"+arr1[1];
		}
		
		String url="https"+"://"+req.getServerName()+"/birt/frameset?__report=customerreport.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&From Date="+date+"&To Date="+date1;
		//String url="http"+"://"+req.getServerName()+":8080/birt/frameset?__report=customerreport.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&From Date="+date+"&To Date="+date1;

		//+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"
		req.getSession().setAttribute("url",url);	
		return "birt";

	}
	public static  Date subtractDays(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, 0);
				
		return cal.getTime();
	}


}
