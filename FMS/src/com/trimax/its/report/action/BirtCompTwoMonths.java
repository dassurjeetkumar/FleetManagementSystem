package com.trimax.its.report.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class BirtCompTwoMonths {


	public String getrevdata(){

		HttpServletRequest req = ServletActionContext.getRequest();
		   HttpServletResponse response = ServletActionContext.getResponse();
		String date=req.getParameter("fromdate");
		String date1=req.getParameter("todate");
		System.out.println("-------"+date);
		System.out.println("-------"+date1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String hd1="";
		String hd2="";
		Date d=new Date();
		Date dd=subtractDays(d);
		String s=sdf.format(dd);
		String Month;
		String CompMonth;
		String Month1;
		String CompMonth1;
		if(date==null && date1==null){
			
			date="2017-01";
			date1="2018-01";
			Month="2018-01-01";
			CompMonth="2017-01-01";
			Month1="2018-01-31";
			CompMonth1="2017-01-31";
			
			String hdr[]=date.split("-");
			hd1=hdr[1]+"-"+hdr[0];
			String hdr1[]=date1.split("-");
			hd2=hdr1[1]+"-"+hdr1[0];
		}else{
		 Month=date+"-01";
		 CompMonth=date1+"-01";
		 System.out.println("====month"+Month);
		Date datestr=getLastDateOfMonth(Month);
		
		 Month1=sdf.format(datestr);
		// Month1=Month1.replaceAll("/","-");
		 System.out.println("====month1"+Month1);
		 System.out.println("==com"+CompMonth);
		Date datestr1=getLastDateOfMonth(CompMonth);

		 CompMonth1=sdf.format(datestr1);
		// CompMonth1=CompMonth1.replaceAll("/","-");
		 System.out.println("==com1"+CompMonth1);
		 
		 String hdr[]=date.split("-");
			hd1=hdr[1]+"-"+hdr[0];
			String hdr1[]=date1.split("-");
			hd2=hdr1[1]+"-"+hdr1[0];
		}
		//String url="https"+"://"+req.getServerName()+"/birt/frameset?__report=revenue_report.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Date="+date;
	/*	String url="http"+"://"+req.getServerName()+":8080/birt/frameset?__report=comptwomonths.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&"
				+ "From Date="+date+"&To Date="+date1+"&Month="+Month+"&Month1="+Month1+"&CompMonth="+CompMonth+"&"
						+ "CompMonth1="+CompMonth1+"&hd1="+hd1+"&hd2="+hd2;	*/	
		String url="https"+"://"+req.getServerName()+"/birt/frameset?__report=comptwomonths.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&"
				+ "From Date="+date+"&To Date="+date1+"&Month="+Month+"&Month1="+Month1+"&CompMonth="+CompMonth+"&"
						+ "CompMonth1="+CompMonth1+"&hd1="+hd1+"&hd2="+hd2;
		//+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"
		req.getSession().setAttribute("url",url);	
		return "birt";

	}
	public static  Date subtractDays(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -2);
				
		return cal.getTime();
	}

    public static Date getLastDateOfMonth(String date) {
    	Calendar c=null;
    	try{
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date convertedDate = dateFormat.parse(date);
    	System.out.println("---conv"+convertedDate);
    	 c = Calendar.getInstance();
    	c.setTime(convertedDate);
    	c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("return--"+c.getTime());
    	return c.getTime();
    }



}
