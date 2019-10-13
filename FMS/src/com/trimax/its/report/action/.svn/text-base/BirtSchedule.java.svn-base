package com.trimax.its.report.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class BirtSchedule {
/*public String getscheduledata(){
	HttpServletRequest req = ServletActionContext.getRequest();
	//String url="https"+"://"+req.getServerName()+"/birt/frameset?__report=revenue_report.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Date="+date;
		//String url="http"+"://"+req.getServerName()+":8080/birt/frameset?__report=schedulereport.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true";
		String url="https"+"://"+req.getServerName()+"/birt/frameset?__report=schedulereport.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true";
		//+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"
		req.getSession().setAttribute("url",url);	
		return "birt";
	
}*/

public String getscheduledata(){
	HttpServletRequest req = ServletActionContext.getRequest();
	   HttpServletResponse response = ServletActionContext.getResponse();
	String date=req.getParameter("date");
	String hd1="";
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	Date d=new Date();
	Date dd=subtractDays(d);
	String s=sdf.format(dd);
	if(date==null){
		
		date=s;
		String hdr[]=date.split("-");
		hd1=hdr[2]+"-"+hdr[1]+"-"+hdr[0];
	}else{
		String[] arr=date.split("/");
		date=arr[2]+"-"+arr[0]+"-"+arr[1];
		String hdr[]=date.split("-");
		hd1=hdr[2]+"-"+hdr[1]+"-"+hdr[0];
	}
	//String url="http"+"://"+req.getServerName()+":8080/birt/frameset?__report=schedulereport.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Date="+date+"&hd1="+hd1;
	String url="https"+"://"+req.getServerName()+"/birt/frameset?__report=schedulereport.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Date="+date+"&hd1="+hd1;
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
