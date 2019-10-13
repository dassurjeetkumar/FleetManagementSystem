package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class Acc66AxisSettlementReport extends ActionSupport{
	public String startdate;
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
	public String enddate;
	@Override
	public String execute() {
	return "success";
	}
public String getaccaxiscomp(){
	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		//String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depot");
	
	
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	

/*		 String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,sum(px_total_amount)px_total_amount,sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,"+
"if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,Ticket_Audited_Date,inserted_date,settlement_date "+
"from (select org_name,org_chart_id,ac.waybill_no,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+
"where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date='"+date1+"'),2) axis_amt,ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_date,'') inserted_date,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac "+
"left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date='"+date1+"'"+
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 "+ 
"and Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' group by ac.waybill_no "+ 
"order by org_name,Ticket_Audited_Date) a group by org_name";*/
	
/*	String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,round(sum(ifnull(total_card_amount,0)),2)total_card_amount," +
			"sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status," +
			"Ticket_Audited_Date,inserted_tm,settlement_date from (select org_name,org_chart_id,ac.waybill_no,ac.etm_card_amt," +
			"(select sum(total_card_amount) from bmtcGprs.etim_settlement where waybill_no=ac.waybill_no and settlement_status='Y') total_card_amount," +
			"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation  " +
			"where waybill_no=ac.waybill_no and ar.settlement_date='"+date1+"'),2) axis_amt," +
					"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm," +
					"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac " +
					"left join  bmtcGprs.etim_settlement egt on ac.waybill_no=egt.waybill_no " +
					"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no " +
					"and ar.settlement_date='"+date1+"'left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id " +
							"where Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' " +
									"group by ac.waybill_no order by org_name,Ticket_Audited_Date) a group by org_name";*/
	///above one is prives 	query
	String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,round(sum(ifnull(total_card_amount,0)),2)total_card_amount,"+
			"sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,"+
			"Ticket_Audited_Date,inserted_tm,settlement_date from ( "+
			"select org_name,org_chart_id,ac.waybill_no,ac.etm_card_amt,"+
			"(select sum(total_card_amount) from bmtcGprs.etim_settlement where waybill_no=ac.waybill_no and settlement_status='Y') total_card_amount,"+
			"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation  "+
			"where waybill_no=ac.waybill_no and ar.settlement_date='"+date1+"'),2) axis_amt,"+
			"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,"+
			"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac "+
			"left join  bmtcGprs.etim_settlement egt on ac.waybill_no=egt.waybill_no "+
			"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no "+
			"and ar.settlement_date='"+date1+"'left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
			"where Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date1+" 23:59:59' "+
			"group by ac.waybill_no order by org_name,Ticket_Audited_Date) a group by org_name ";
/*			"union "+
			"select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,round(sum(ifnull(total_card_amount,0)),2)total_card_amount,"+
			"sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,"+
			"Ticket_Audited_Date,inserted_tm,settlement_date from ( "+
			"select org_name,"+
			"org_chart_id,ac.waybill_no,ifnull(ac.etm_card_amt,0)etm_card_amt,"+
			"(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no and settlement_status='Y') total_card_amount,"+
			"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+  
			"where waybill_no=eg.waybill_no and ar.settlement_date='"+date1+"'),2) axis_amt,"+
			"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,"+
			"ifnull(settlement_date,'') settlement_date from bmtcGprs.axis_reconcillation ar  "+
			"left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
			"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no "+
			"left join bmtcGprs.etim_gprs_ticket eg on eg.waybill_no=ar.waybill_no "+
			//"left join bmtcGprs.etim_gprs_ticket150318 egg on egg.waybill_no=ar.waybill_no "+
			"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id "+
			//"left join  bmtcGprs.org_chart occ on egg.depot_id=occ.org_chart_id "+
			"where ar.settlement_date='"+date1+"' and eg.payment_mode=1 "+
			"group by ar.waybill_no order by org_name,Ticket_Audited_Date) a group by org_name " +*/
	/*		"union "+
			"select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,round(sum(ifnull(total_card_amount,0)),2)total_card_amount,"+
			"sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,"+
			"Ticket_Audited_Date,inserted_tm,settlement_date from ( "+
			"select org_name,"+
			"org_chart_id,ac.waybill_no,ifnull(ac.etm_card_amt,0)etm_card_amt,"+
			"(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no and settlement_status='Y') total_card_amount,"+
			"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+  
			"where waybill_no=eg.waybill_no and ar.settlement_date='"+date1+"'),2) axis_amt,"+
			"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,"+
			"ifnull(settlement_date,'') settlement_date from bmtcGprs.axis_reconcillation ar  "+
			"left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
			"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no "+
			"left join bmtcGprs.etim_gprs_ticket150318 eg on eg.waybill_no=ar.waybill_no "+
			//"left join bmtcGprs.etim_gprs_ticket150318 egg on egg.waybill_no=ar.waybill_no "+
			"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id "+
			//"left join  bmtcGprs.org_chart occ on egg.depot_id=occ.org_chart_id "+
			"where ar.settlement_date='"+date1+"' and eg.payment_mode=1 "+
			"group by ar.waybill_no order by org_name,Ticket_Audited_Date) a group by org_name"
			+ ") b group by org_chart_id ";*/

	Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("org_chart_id").addScalar("etm_card_amt").addScalar("total_card_amount").addScalar("axis_amt").
			addScalar("diff").addScalar("settlement_status").addScalar("Ticket_Audited_Date").addScalar("inserted_tm").addScalar("settlement_date");
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();
/*
	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Daily Recon Report</br> Date:- "+startdate+"</br></h4></div>");

	regionTypeAjaxString1.append("</div>");
        

	regionTypeAjaxString1.append("<b><font color="+"red"+">Acc Amout Settlement Details</font></b><div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot</th><th>Acc-66 Amount</th><th>Its Amount</th><th>axis Amount</th>" +
			"<th>Differnce</th><th>Status</th><th>Audited Date</th>" +
				""+"</tr></thead><tbody>");*/
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	        double its_transation_amount=0.0;
	        BigDecimal acc_amount=BigDecimal.ZERO;
	        //BigDecimal its_transation_amount=BigDecimal.ZERO;
	        BigDecimal axis_transation_amount=BigDecimal.ZERO;
	        BigDecimal total_transation_amount=BigDecimal.ZERO;
	        JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        	 
	        	 its_transation_amount +=Double.parseDouble(list.get("total_card_amount").toString());
	        	 acc_amount = acc_amount.add((BigDecimal) list.get("etm_card_amt"));
	        	 axis_transation_amount = axis_transation_amount.add((BigDecimal) list.get("axis_amt"));
        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("diff"));

		 
			ja.add(j);
			
		ja.add(
					"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwisereport('"
					+ list.get("org_chart_id").toString()
                    + "','"
                    + date1
					+ "') title='Get Depot Wise Details' id='alert_details"
					+ "'>"
					+ list.get("org_name").toString()+""
					+ "</a>");
			
		ja.add(list.get("etm_card_amt").toString());
			ja.add(list.get("total_card_amount").toString());
			ja.add(list.get("axis_amt").toString());
			//ja.add(list.get("diff").toString());
			if(list.get("settlement_status").equals("No")){
			ja.add(
					"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getdepotnotsettamt('"
					+ list.get("org_chart_id").toString()
                    + "','"
                    + date1
					+ "') title='Get Depot Wise Details' id='alert_details"
					+ "'>"
					+list.get("diff").toString()+""
					+ "</a>");
			}else{ja.add(list.get("diff").toString());}
			ja.add(list.get("settlement_status").toString());
		if(!list.get("Ticket_Audited_Date").toString().equals("")){
			ja.add(list.get("Ticket_Audited_Date").toString());
		}else{
			ja.add(list.get("Ticket_Audited_Date").toString());
		}
			array.add(ja);
				
				 }
	        JSONArray ja = new JSONArray();
	        BigDecimal d=new BigDecimal(its_transation_amount);
	        total_transation_amount=total_transation_amount.setScale(2, BigDecimal.ROUND_UP);
	        ja.add("Total");
	        ja.add("");
	        ja.add(acc_amount);
	        ja.add(d.setScale(2, BigDecimal.ROUND_UP));ja.add(axis_transation_amount);ja.add(total_transation_amount);
	        ja.add("");
	        ja.add("");
	        array.add(ja);	
	   	 PrintWriter out;
 
	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
	
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
finally{
session1.close();
}
	return null;



}
public String getsettlementdiffdate(){
	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		//String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depot");
	
	
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	

/*		 String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,sum(px_total_amount)px_total_amount,sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,"+
"if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,Ticket_Audited_Date,inserted_date,settlement_date "+
"from (select org_name,org_chart_id,ac.waybill_no,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+
"where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date,ifnull(egt.inserted_date,'') inserted_date,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac "+
"left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no "+
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 "+ 
"and Ticket_Audited_Date not between '"+date1+" 00:00:00' and '"+date1+" 23:59:59'  and ar.settlement_date ='"+date1+"' group by ac.waybill_no "+ 
"order by org_name,Ticket_Audited_Date) a group by org_name";*/
String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,sum(ifnull(total_card_amount,0))total_card_amount," +
		"sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff," +
		"if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,Ticket_Audited_Date,inserted_tm," +
		"settlement_date from (select org_name,org_chart_id,ac.waybill_no,ac.etm_card_amt," +
		"(select sum(total_card_amount) from bmtcGprs.etim_settlement where waybill_no=ac.waybill_no and " +
		"settlement_status='Y') total_card_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) " +
		"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date," +
		"ifnull(egt.inserted_tm,'') inserted_tm,ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac " +
		"left join  bmtcGprs.etim_settlement egt on ac.waybill_no=egt.waybill_no " +
		"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no " +
		"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where Ticket_Audited_Date not between '"+date1+" 00:00:00' " +
				"and '"+date1+" 23:59:59'  and ar.settlement_date ='"+date1+"' group by ac.waybill_no order by org_name," +
						"Ticket_Audited_Date) a group by org_name";
	Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("org_chart_id").addScalar("etm_card_amt").addScalar("total_card_amount").addScalar("axis_amt").
			addScalar("diff").addScalar("settlement_status").addScalar("Ticket_Audited_Date").addScalar("inserted_tm").addScalar("settlement_date");
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();
/*
	regionTypeAjaxString1.append("<b><font color="+"red"+">Old Settlement Report</font></b><div id='header' style='display: none;'><div align='center'> </br></br> Date:- "+startdate+"</br></h4></div>");

	regionTypeAjaxString1.append("</div>");
        

	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot</th><th>Acc-66 Amount</th><th>Its Amount</th><th>axis Amount</th>" +
			"<th>Differnce</th><th>Status</th><th>Audited Date</th>" +
				""+"</tr></thead><tbody>");*/
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	        double its_transation_amount=0.0;
	        BigDecimal acc_amount=BigDecimal.ZERO;
	        //BigDecimal its_transation_amount=BigDecimal.ZERO;
	        BigDecimal axis_transation_amount=BigDecimal.ZERO;
	        BigDecimal total_transation_amount=BigDecimal.ZERO;
	        JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        	 
	        	 its_transation_amount +=Double.parseDouble(list.get("total_card_amount").toString());
	        	 acc_amount = acc_amount.add((BigDecimal) list.get("etm_card_amt"));
	        	 axis_transation_amount = axis_transation_amount.add((BigDecimal) list.get("axis_amt"));
        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("diff"));

		 
			ja.add(j);
			
		ja.add(
					"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwisereportnotsameday('"
					+ list.get("org_chart_id").toString()
                    + "','"
                    + date1
					+ "') title='Get Depot Wise Details' id='alert_details"
					+ "'>"
					+ list.get("org_name").toString()+""
					+ "</a>");
			
		ja.add(list.get("etm_card_amt").toString());
			ja.add(list.get("total_card_amount").toString());
			ja.add(list.get("axis_amt").toString());
			ja.add(list.get("diff").toString());
			ja.add(list.get("settlement_status").toString());
		
			ja.add(list.get("Ticket_Audited_Date").toString().substring(0,10));
			array.add(ja);
				
				 }
	        JSONArray ja = new JSONArray();
	        total_transation_amount=total_transation_amount.setScale(2, BigDecimal.ROUND_UP);
	        ja.add("Total");
	        ja.add("");
	        ja.add(acc_amount);
	        ja.add(its_transation_amount);ja.add(axis_transation_amount);ja.add(total_transation_amount);
	        ja.add("");
	        ja.add("");
	        array.add(ja);	
	   	 PrintWriter out;
 
	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
	
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
finally{
session1.close();
}
	return null;



}
public String getaccaxiscompdepotwise(){
	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
	/*	Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");*/
		
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depotid");
	
	String date=req.getParameter("startdate");
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	List<Map<String, Object>> aliasToValueMapList;

/*		 String sql="select org_name,waybill_no,etm_card_amt,px_total_amount,axis_amt,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from "+
                 "(select org_name,ac.waybill_no,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+
                  "where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,"+
                  "(select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no) axis_amt,"+
                  "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+ 
                 "from bmtcGprs.Acc_66 ac inner join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
                 "left join  bmtcGprs.axis_reconcillation ar on egt.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
                  "where payment_mode=1 and Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' group by ac.waybill_no "+
                  "order by org_name,Ticket_Audited_Date) a";*/
	String sql="select org_name,waybill_no,ifnull(schedule_no,'')schedule_no,ifnull(route_no,'')route_no,ifnull(trip_no,'')trip_no,etim_no,ticket_date,ifnull(inserted_tm,'')inserted_tm,ticket_no,axiswaybill,ticket_id,etm_card_amt,"+
               "ifnull(px_total_amount,0)px_total_amount,itsconvinence_fee,axis_amt,convenience_fee,settlement_status,diff,Ticket_Audited_Date,settlement_date from ( "+
               "select org_name,waybill_no,schedule_no,route_no,trip_no,etim_no,ticket_date,ticket_time,inserted_tm,ticket_no,"+
                "ifnull(axiswaybill,'') axiswaybill,ifnull(ticket_id,'') ticket_id,etm_card_amt,px_total_amount,itsconvinence_fee,ifnull(axis_amt,0)axis_amt,"+
              "ifnull(convenience_fee,0)convenience_fee,if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,"+
              "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date from (select org_name,ac.waybill_no,schedule_no,route_no,trip_no,etim_no,"+
              "ticket_date,ticket_time,es.inserted_tm,ticket_no,ar.waybill_no as axiswaybill,ticket_id,convinence_fee as itsconvinence_fee,ac.etm_card_amt,"+
              "(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,"+
               "round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
              "where waybill_no=ac.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,(select ifnull((sum(convenience_fee)),0) from bmtcGprs.axis_reconcillation where waybill_no=ac.waybill_no) convenience_fee," +
              		"Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+
               "from bmtcGprs.Acc_66 ac  inner join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
               "left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
              "left join  bmtcGprs.axis_reconcillation ar on ar.waybill_no=ac.waybill_no and ar.settlement_date='"+date+"' "+
              "left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where  Ticket_Audited_Date between '"+date+" 00:00:00' and '"+date+" 23:59:59' "+
              "and oc.org_chart_id="+depot+" group by ac.waybill_no order by org_name,Ticket_Audited_Date) a)b "+
   " union select org_name,waybill_no,ifnull(schedule_no,'')schedule_no,ifnull(route_no,'')route_no,ifnull(trip_no,'')trip_no,etim_no,ticket_date,ifnull(inserted_tm,'')inserted_tm,ticket_no,axiswaybill,ticket_id,etm_card_amt,"+
            "ifnull(px_total_amount,0)px_total_amount,itsconvinence_fee,axis_amt,convenience_fee,settlement_status,diff,Ticket_Audited_Date,settlement_date from ( "+
            "select org_name,waybill_no,schedule_no,route_no,trip_no,etim_no,ticket_date,ticket_time,inserted_tm,ticket_no,"+
             "ifnull(axiswaybill,'') axiswaybill,ifnull(ticket_id,'') ticket_id,etm_card_amt,px_total_amount,itsconvinence_fee,ifnull(axis_amt,0)axis_amt,"+
           "ifnull(convenience_fee,0)convenience_fee,if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,"+
           "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date from (select org_name,ac.waybill_no,schedule_no,route_no,trip_no,etim_no,"+
           "ticket_date,ticket_time,es.inserted_tm,ticket_no,ar.waybill_no as axiswaybill,ticket_id,convinence_fee as itsconvinence_fee,ac.etm_card_amt,"+
           "(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket190219 where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,"+
            "round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
           "where waybill_no=ac.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,(select ifnull((sum(convenience_fee)),0) from bmtcGprs.axis_reconcillation where waybill_no=ac.waybill_no) convenience_fee," +
           		"Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+
            "from bmtcGprs.Acc_66 ac  inner join  bmtcGprs.etim_gprs_ticket190219 egt on ac.waybill_no=egt.waybill_no "+
            "left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
           "left join  bmtcGprs.axis_reconcillation ar on ar.waybill_no=ac.waybill_no and ar.settlement_date='"+date+"' "+
           "left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where  Ticket_Audited_Date between '"+date+" 00:00:00' and '"+date+" 23:59:59' "+
           "and oc.org_chart_id="+depot+" group by ac.waybill_no order by org_name,Ticket_Audited_Date) a)b ";
	
	
	Query query = session1.createSQLQuery(sql);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
     aliasToValueMapList = query.list();
     System.out.println("map size---"+aliasToValueMapList);
/*if(aliasToValueMapList.size()==0){
	String sql1="select org_name,org_chart_id,waybill_no,"+
			"ifnull(schedule_no,'')schedule_no,ifnull(route_no,'')route_no,ifnull(trip_no,'')trip_no,ifnull(etim_no,'')etim_no,ifnull(ticket_date,'')ticket_date, axiswaybill,"+
			"ifnull(ticket_id,'')ticket_id,ifnull(px_total_amount,'')px_total_amount,ifnull(convenience_fee,'')convenience_fee,inserted_tm,etm_card_amt,axis_amt,"+
			"if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from( "+
			"select org_name,org_chart_id,ifnull(ac.waybill_no,'')waybill_no,"+
			"schedule_no,route_no,trip_no,etim_no,ticket_date,ar.waybill_no axiswaybill,"+
			"ticket_id,px_total_amount,convenience_fee,"+
			"ifnull(ac.etm_card_amt,0)etm_card_amt,(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no "+
			"and settlement_status='Y') total_card_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
			"from bmtcGprs.axis_reconcillation where waybill_no=eg.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,"+
			"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,ifnull(settlement_date,'') settlement_date "+
			"from bmtcGprs.axis_reconcillation ar left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
			"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no left join bmtcGprs.etim_gprs_ticket eg on eg.waybill_no=ar.waybill_no "+
			"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id where ar.settlement_date='"+date+"' and payment_mode=1 "+
			"and oc.org_chart_id="+depot+" group by ar.waybill_no order by org_name,Ticket_Audited_Date "+
			") a "+
			"union "+
			"select org_name,org_chart_id,waybill_no,"+
			"ifnull(schedule_no,'')schedule_no,ifnull(route_no,'')route_no,ifnull(trip_no,'')trip_no,ifnull(etim_no,'')etim_no,ifnull(ticket_date,'')ticket_date, axiswaybill,"+
			"ifnull(ticket_id,'')ticket_id,ifnull(px_total_amount,'')px_total_amount,ifnull(convenience_fee,'')convenience_fee,inserted_tm,etm_card_amt,axis_amt,"+
			"if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from( "+
			"select org_name,org_chart_id,ifnull(ac.waybill_no,'')waybill_no,"+
			"schedule_no,route_no,trip_no,etim_no,ticket_date,ar.waybill_no axiswaybill,"+
			"ticket_id,px_total_amount,convenience_fee,"+
			"ifnull(ac.etm_card_amt,0)etm_card_amt,(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no "+
			"and settlement_status='Y') total_card_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
			"from bmtcGprs.axis_reconcillation where waybill_no=eg.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,"+
			"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,ifnull(settlement_date,'') settlement_date "+
			"from bmtcGprs.axis_reconcillation ar left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
			"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no left join bmtcGprs.etim_gprs_ticket190219 eg on eg.waybill_no=ar.waybill_no "+
			"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id where ar.settlement_date='"+date+"' and payment_mode=1 "+
			"and oc.org_chart_id="+depot+" group by ar.waybill_no order by org_name,Ticket_Audited_Date "+
			") a";
	Query query1 = session1.createSQLQuery(sql1);
 query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
  aliasToValueMapList = query1.list();
}*/
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	       /* double its_transation_amount=0.0;
	        BigDecimal acc_amount=BigDecimal.ZERO;
	        //BigDecimal its_transation_amount=BigDecimal.ZERO;
	        BigDecimal axis_transation_amount=BigDecimal.ZERO;
	        BigDecimal total_transation_amount=BigDecimal.ZERO;*/
	        JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        /*	 its_transation_amount +=Double.parseDouble(list.get("px_total_amount").toString());
	        	 acc_amount = acc_amount.add((BigDecimal) list.get("etm_card_amt"));
	        	// its_transation_amount = its_transation_amount.add((BigDecimal) list.get("px_total_amount"));
	        	 axis_transation_amount = axis_transation_amount.add((BigDecimal) list.get("axis_amt"));
	        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("diff"));*/

	        	 //totalcardamount +=Double.parseDouble((String) list.get("diff"));
		  
	        	 ja.add(j);
	        	 ja.add(list.get("org_name"));
	        	 ja.add(list.get("waybill_no").toString());
	        	 ja.add(list.get("schedule_no").toString());
	        	 ja.add(list.get("route_no").toString());
	        	 ja.add(list.get("trip_no").toString());
	        	 ja.add(list.get("etim_no").toString());
	        	 ja.add(list.get("ticket_date").toString());
			//regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_time").toString() +"</td>");
	        	 ja.add(list.get("inserted_tm").toString());
	        	// ja.add(list.get("ticket_no").toString());
	        	 ja.add(list.get("axiswaybill").toString());
	        	 ja.add(list.get("ticket_id").toString());
	        	 ja.add(list.get("etm_card_amt").toString());
			ja.add(list.get("px_total_amount").toString());
			//ja.add(list.get("itsconvinence_fee").toString());
			ja.add(list.get("axis_amt").toString());
			ja.add(list.get("convenience_fee").toString());
			ja.add(list.get("diff").toString());
			ja.add(list.get("settlement_status").toString());
			ja.add(list.get("Ticket_Audited_Date").toString());
			ja.add(list.get("settlement_date").toString());
			array.add(ja);
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("px_total_amount").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("axis_amt").toString()  +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("diff").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("Ticket_Audited_Date").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+list.get("settlement_date").toString()  +"</td>");
		//regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
		 //regionTypeAjaxString1.append("</tr>");
				
				 }
	       /* total_transation_amount=total_transation_amount.setScale(2, BigDecimal.ROUND_UP);
	        regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td colspan='11'></td>" +
	        		"<td>"+acc_amount+"</td><td>"+its_transation_amount+"</td><td></td><td>"+axis_transation_amount+"</td><td></td><td>"+total_transation_amount+"</td><td></td><td></td></tr>");
	        regionTypeAjaxString1.append("</tbody></table></div>");*/
				 PrintWriter out;
            

/*	out = response.getWriter();
	out.print(regionTypeAjaxString1);*/
	    	result.put("aaData",array);
			out = response.getWriter();
			out.print(result);
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
finally{
session1.close();
}
	return null;



}
public String getdepotwisesettlediffdate(){

	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
	/*	Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");*/
		
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depotid");
	
	String date=req.getParameter("startdate");
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	

/*		 String sql="select org_name,waybill_no,etm_card_amt,px_total_amount,axis_amt,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from "+
                 "(select org_name,ac.waybill_no,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+
                  "where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,"+
                  "(select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no) axis_amt,"+
                  "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+ 
                 "from bmtcGprs.Acc_66 ac inner join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
                 "left join  bmtcGprs.axis_reconcillation ar on egt.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
                  "where payment_mode=1 and Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' group by ac.waybill_no "+
                  "order by org_name,Ticket_Audited_Date) a";*/
	String sql="select org_name,waybill_no,schedule_no,route_no,trip_no,etim_no,ticket_date,ticket_time,inserted_tm,ticket_no,"+
                "ifnull(axiswaybill,'') axiswaybill,ifnull(ticket_id,'') ticket_id,etm_card_amt,px_total_amount,itsconvinence_fee,ifnull(axis_amt,0)axis_amt,"+
              "ifnull(convenience_fee,0)convenience_fee,if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,"+
              "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date from (select org_name,ac.waybill_no,schedule_no,route_no,trip_no,etim_no,"+
              "ticket_date,ticket_time,es.inserted_tm,ticket_no,ar.waybill_no as axiswaybill,ticket_id,convinence_fee as itsconvinence_fee,ac.etm_card_amt,"+
              "(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,"+
               "round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
              "where waybill_no=ac.waybill_no),2) axis_amt,(select ifnull((sum(convenience_fee)),0) from bmtcGprs.axis_reconcillation where waybill_no=ac.waybill_no) convenience_fee," +
              "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+
               "from bmtcGprs.Acc_66 ac  left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
               "left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
              "left join  bmtcGprs.axis_reconcillation ar on ar.waybill_no=ac.waybill_no "+
              "left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+date+" 23:59:59'  and ar.settlement_date ='"+date+"' "+
              "and oc.org_chart_id="+depot+" group by ac.waybill_no order by org_name,Ticket_Audited_Date)a ";
              
	Query query = session1.createSQLQuery(sql);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();
/*
	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'></br></h4></div>");

	regionTypeAjaxString1.append("<div align='right'></div></div>");
        

	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>OrgName</th><th>waybill no</th><th>Schedule no</th><th>Route no</th><th>Trip no</th><th>Etm no</th><th>Ticket Date</th><th>Inserted Date</th><th>Its Ticket no</th><th>Axis waybill no</th><th>Axis Ticket Id</th><th>Acc-66 Amount</th><th>Its Amount</th><th>Its Convinence Fee no</th><th>axis Amount</th>" +
			"<th>Axis Convenience Fee</th><th>Differnce</th><th>Status</th><th>Acc-66 Date</th><th>Axis Settlement Date</th>" +
				""+"</tr></thead><tbody>");*/
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	       /* double its_transation_amount=0.0;
	        BigDecimal acc_amount=BigDecimal.ZERO;
	        //BigDecimal its_transation_amount=BigDecimal.ZERO;
	        BigDecimal axis_transation_amount=BigDecimal.ZERO;
	        BigDecimal total_transation_amount=BigDecimal.ZERO;*/
	        JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        /*	 its_transation_amount +=Double.parseDouble(list.get("px_total_amount").toString());
	        	 acc_amount = acc_amount.add((BigDecimal) list.get("etm_card_amt"));
	        	// its_transation_amount = its_transation_amount.add((BigDecimal) list.get("px_total_amount"));
	        	 axis_transation_amount = axis_transation_amount.add((BigDecimal) list.get("axis_amt"));
	        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("diff"));*/

	        	 //totalcardamount +=Double.parseDouble((String) list.get("diff"));
		  
	        	 ja.add(j);
	        	 ja.add(list.get("org_name"));
	        	 ja.add(list.get("waybill_no").toString());
	        	 ja.add(list.get("schedule_no").toString());
	        	 ja.add(list.get("route_no").toString());
	        	 ja.add(list.get("trip_no").toString());
	        	 ja.add(list.get("etim_no").toString());
	        	 ja.add(list.get("ticket_date").toString());
			//regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_time").toString() +"</td>");
	        	 ja.add(list.get("inserted_tm").toString());
	        	// ja.add(list.get("ticket_no").toString());
	        	 ja.add(list.get("axiswaybill").toString());
	        	 ja.add(list.get("ticket_id").toString());
	        	 ja.add(list.get("etm_card_amt").toString());
			ja.add(list.get("px_total_amount").toString());
			//ja.add(list.get("itsconvinence_fee").toString());
			ja.add(list.get("axis_amt").toString());
			ja.add(list.get("convenience_fee").toString());
			ja.add(list.get("diff").toString());
			ja.add(list.get("settlement_status").toString());
			ja.add(list.get("Ticket_Audited_Date").toString());
			ja.add(list.get("settlement_date").toString());
			array.add(ja);
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("px_total_amount").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("axis_amt").toString()  +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("diff").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("Ticket_Audited_Date").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+list.get("settlement_date").toString()  +"</td>");
		//regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
		 //regionTypeAjaxString1.append("</tr>");
				
				 }
	       /* total_transation_amount=total_transation_amount.setScale(2, BigDecimal.ROUND_UP);
	        regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td colspan='11'></td>" +
	        		"<td>"+acc_amount+"</td><td>"+its_transation_amount+"</td><td></td><td>"+axis_transation_amount+"</td><td></td><td>"+total_transation_amount+"</td><td></td><td></td></tr>");
	        regionTypeAjaxString1.append("</tbody></table></div>");*/
				 PrintWriter out;
            

/*	out = response.getWriter();
	out.print(regionTypeAjaxString1);*/
	    	result.put("aaData",array);
			out = response.getWriter();
			out.print(result);
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
finally{
session1.close();
}
	return null;




}
public String getnotsettleamnt(){


	Session session1 = null;
	JSONObject result = new JSONObject();
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		Common common = new Common();
		String date=common.getDateFromPicker(startdate);
		//String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	//DateFormat df = new SimpleDateFormat("yyyy");
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date d=df.parse(startdate);
	HttpServletRequest req = ServletActionContext.getRequest();
	//String depot=req.getParameter("depot");
	Calendar cal = Calendar.getInstance();
	cal.setTime(d);
	cal.add(Calendar.DATE,-1);
	
	System.out.println(cal);
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	

/*		 String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,sum(axis_amt)axis_amt,sum(Settlement)Settlement,"+
"sum(((etm_card_amt+Settlement)-axis_amt)) pending,sum(opening_amt)opening_amt,"+
"(sum(opening_amt)+sum(((etm_card_amt+Settlement)-axis_amt))-Settlement)total "+
"from (select org_name,org_chart_id,(0) etm_card_amt,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
"where waybill_no=ac.waybill_no),2) axis_amt,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
"where waybill_no=ac.waybill_no and ar.settlement_date='"+date+"'),2) Settlement,(0)opening_amt "+
"from bmtcGprs.Acc_66 ac left join bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where payment_mode=1 and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+date+" 23:59:59' and ar.settlement_date='"+date+"' "+
"group by org_name "+
"union all "+
"select org_name,org_chart_id,ac.etm_card_amt,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,(0) Settlement,(0)opening_amt "+
"from bmtcGprs.Acc_66 ac left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date='"+date+"' "+
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 and Ticket_Audited_Date between '"+date+" 00:00:00' "+
"and '"+date+" 23:59:59' group by ac.waybill_no "+
"union all "+
"select oc.org_name,org_chart_id,(0)etm_card_amt,(0)axis_amt,(0)Settlement,"+
"sum(ac.etm_card_amt) opening_amt from bmtcGprs.Acc_66 ac "+
"left join bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where waybill_no not in (select waybill_no from bmtcGprs.axis_reconcillation where settlement_date < '"+date+"') "+
"and Ticket_Audited_Date< '"+date+" 00:00:00'  group by ac.waybill_no order by org_name"+
") a group by org_name";*/
	String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,sum(axis_amt)axis_amt,sum(Settlement)Settlement,"+
"sum(((etm_card_amt+Settlement)-axis_amt)) pending,sum(opening_amt)opening_amt,"+
"(sum(opening_amt)+sum(((etm_card_amt+Settlement)-axis_amt))-sum(Settlement))total from (select org_name,org_chart_id,(0) etm_card_amt,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation where waybill_no=ac.waybill_no),2) axis_amt,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation where waybill_no=ac.waybill_no "+
"and ar.settlement_date='"+date+"'),2) Settlement,(0)opening_amt from bmtcGprs.Acc_66 ac "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where  Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+date+" 23:59:59' and ar.settlement_date='"+date+"' "+
"group by org_name union all select org_name,org_chart_id,ac.etm_card_amt,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,(0) Settlement,"+
"(0)opening_amt from bmtcGprs.Acc_66 ac "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date='"+date+"' "+
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where  Ticket_Audited_Date between '"+date+" 00:00:00' and '"+date+" 23:59:59' " +
		"group by ac.waybill_no union all select oc.org_name,org_chart_id,(0)etm_card_amt,(0)axis_amt," +
		"(0)Settlement,sum(ac.etm_card_amt) opening_amt from bmtcGprs.Acc_66 ac left join bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id " +
		"where waybill_no not in (select waybill_no from bmtcGprs.axis_reconcillation where settlement_date < '"+date+"') and " +
				"Ticket_Audited_Date< '"+date+" 00:00:00'  group by ac.waybill_no order by org_name) a group by org_name";
		 
	Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("org_chart_id").addScalar("etm_card_amt").addScalar("axis_amt").
			addScalar("Settlement").addScalar("pending").addScalar("opening_amt").addScalar("total");
		
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();
/*
	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br></br> Date:- "+startdate+"</br></h4></div>");

	regionTypeAjaxString1.append("</div>");
        

	regionTypeAjaxString1.append("<b><font color="+"red"+">Details of Unsettlement Amount  </font></b><div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot</th><th>Opening Amount</th><th>Past Settlement</th><th>Pending</th><th>Total</th>" +
			//"<th>Acc-66 Date</th><th>Its Date</th>" +
				""+"</tr></thead><tbody>");*/
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	        BigDecimal openamount=BigDecimal.ZERO;
	        BigDecimal settleamount=BigDecimal.ZERO;
	        BigDecimal pendingamount=BigDecimal.ZERO;
	        BigDecimal totalamount=BigDecimal.ZERO;
	        JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        	 openamount = openamount.add((BigDecimal) list.get("opening_amt"));
	        	 settleamount = settleamount.add((BigDecimal) list.get("Settlement"));
	        	 pendingamount = pendingamount.add((BigDecimal) list.get("pending"));
	        	 totalamount = totalamount.add((BigDecimal) list.get("total"));
	       
		   
	 			ja.add(j);
				
	 			ja.add(
	 						"<a data-toggle='modal' class='btn green' role='button' href='#mymodal11'  onclick=javascript:hflgetdepotwisenotsettleamt('"
	 						+ list.get("org_chart_id").toString()
	 	                    + "','"
	 	                    + date
	 						+ "') title='Get Depot Wise Details' id='alert_details"
	 						+ "'>"
	 						+ list.get("org_name").toString()+""
	 						+ "</a>");
	 			ja.add(list.get("opening_amt").toString());
	 			ja.add(list.get("Settlement").toString());
	 			ja.add(list.get("pending").toString());
	 			ja.add(list.get("total").toString());
	array.add(ja);
				
				 }
	       
	        /*regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td colspan='1'></td>" +
	        		"<td>"+openamount+"</td><td>"+settleamount+"</td><td>"+pendingamount+"</td><td>"+totalamount+"</td></tr>");
	        regionTypeAjaxString1.append("</tbody></table></div>");*/
				 PrintWriter out;
	        JSONArray ja = new JSONArray();
	      
	        ja.add("Total");
	        ja.add("");
	        ja.add(openamount);
	        ja.add(settleamount);ja.add(pendingamount);ja.add(totalamount);
	        ja.add("");
	        ja.add("");
	        array.add(ja);
	        result.put("aaData",array);
			out = response.getWriter();
			out.print(result);

	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
finally{
session1.close();
}
	return null;
	
}
public String getdepotwisenotsettleamt(){


	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depotid");
	
	String date=req.getParameter("startdate");
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");

	String sql="select oc.org_name,oc.org_chart_id,ac.waybill_no,ac.device_serial_number,ac.etm_card_amt,sum(ifnull(egt.px_total_amount,0)) px_total_amount," +
			"ac.Ticket_Audited_Date,ifnull(es.inserted_tm,'')inserted_tm  "+ 
            "from bmtcGprs.Acc_66 ac "+
            "left join bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no  and payment_mode=1 " +
            "left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
             "left join bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
              "where ac.waybill_no not in(select waybill_no from  bmtcGprs.axis_reconcillation where settlement_date < '"+date+"') and oc.org_chart_id="+depot+" "+
              " and Ticket_Audited_Date<'"+date+" 00:00:00' "+
            "group by ac.waybill_no ";
             /* "union "+
              "select oc.org_name,oc.org_chart_id,ac.waybill_no,ac.device_serial_number,ac.etm_card_amt,sum(ifnull(egt.px_total_amount,0)) px_total_amount," +
  			"ac.Ticket_Audited_Date,ifnull(es.inserted_tm,'')inserted_tm  "+ 
              "from bmtcGprs.Acc_66 ac "+
              "left join bmtcGprs.etim_gprs_ticket150318 egt on ac.waybill_no=egt.waybill_no and payment_mode=1 " +
              "left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
               "left join bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
                "where ac.waybill_no not in(select waybill_no from  bmtcGprs.axis_reconcillation where settlement_date < '"+date+"') and oc.org_chart_id="+depot+" "+
                " and Ticket_Audited_Date<'"+date+" 00:00:00' "+
              "group by ac.waybill_no ";*/
	Query query = session1.createSQLQuery(sql);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();

	        HttpServletResponse response = ServletActionContext.getResponse();
	
	        JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	    		  
	        	 ja.add(j);
	        	 ja.add(list.get("org_name"));
	        	 ja.add(list.get("waybill_no").toString());
	        	 ja.add(list.get("device_serial_number").toString());
	        	 ja.add(list.get("etm_card_amt").toString());
	        	 ja.add(list.get("px_total_amount").toString());
                ja.add(list.get("Ticket_Audited_Date").toString());
                ja.add(list.get("inserted_tm").toString());
			//ja.add(list.get("settlement_date").toString());
			array.add(ja);
				
				 }

				 PrintWriter out;

	    	result.put("aaData",array);
			out = response.getWriter();
			out.print(result);
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
finally{
session1.close();
}
	return null;

}
public String getheader(){
	
	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	PrintWriter out = null;
	JSONObject result = new JSONObject();
	JSONArray headrearray = new JSONArray();
	
	headrearray.add(req.getParameter("startdate"));
	
	
	result.put("bbData", headrearray);
	//return result;
	try {
		out = resp.getWriter();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	out.print(result);
	return null;
}
public String getaccaxiscompdepotnotsettamt(){

	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
	/*	Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");*/
		
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depotid");
	
	String date=req.getParameter("startdate");
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	List<Map<String, Object>> aliasToValueMapList;

/*		 String sql="select org_name,waybill_no,etm_card_amt,px_total_amount,axis_amt,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from "+
                 "(select org_name,ac.waybill_no,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+
                  "where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,"+
                  "(select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no) axis_amt,"+
                  "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+ 
                 "from bmtcGprs.Acc_66 ac inner join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
                 "left join  bmtcGprs.axis_reconcillation ar on egt.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
                  "where payment_mode=1 and Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' group by ac.waybill_no "+
                  "order by org_name,Ticket_Audited_Date) a";*/
	String sql="select 	ifnull(schedule_no,'')schedule_no,ifnull(waybill_no,'')waybill_no,ifnull(route_no,'')route_no,ifnull(trip_no,'')trip_no,ifnull(etim_no,'')etim_no,ifnull(ticket_date,'')ticket_date, axiswaybill,"+
    				"ifnull(ticket_id,'')ticket_id,ifnull(px_total_amount,'')px_total_amount,ifnull(convenience_fee,'')convenience_fee,ifnull(inserted_tm,'')inserted_tm,etm_card_amt,axis_amt,"+
    				"if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from ( "+
               "select org_name,waybill_no,schedule_no,route_no,trip_no,etim_no,ticket_date,ticket_time,inserted_tm,ticket_no,"+
                "ifnull(axiswaybill,'') axiswaybill,ifnull(ticket_id,'') ticket_id,etm_card_amt,px_total_amount,itsconvinence_fee,ifnull(axis_amt,0)axis_amt,"+
              "ifnull(convenience_fee,0)convenience_fee,if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,"+
              "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date from (select org_name,ac.waybill_no,schedule_no,route_no,trip_no,etim_no,"+
              "ticket_date,ticket_time,es.inserted_tm,ticket_no,ar.waybill_no as axiswaybill,ticket_id,convinence_fee as itsconvinence_fee,ac.etm_card_amt,"+
              "(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,"+
               "round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
              "where waybill_no=ac.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,(select ifnull((sum(convenience_fee)),0) from bmtcGprs.axis_reconcillation where waybill_no=ac.waybill_no) convenience_fee," +
              		"Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+
               "from bmtcGprs.Acc_66 ac  left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
               "left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
              "left join  bmtcGprs.axis_reconcillation ar on ar.waybill_no=ac.waybill_no and ar.settlement_date='"+date+"' "+
              "left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where  Ticket_Audited_Date between '"+date+" 00:00:00' and '"+date+" 23:59:59' "+
              "and oc.org_chart_id="+depot+" group by ac.waybill_no order by org_name,Ticket_Audited_Date) a)b where settlement_status='No'";
	Query query = session1.createSQLQuery(sql);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
     aliasToValueMapList = query.list();
     if(aliasToValueMapList.size()==0){
    		String sql1="select * from (select " +
    				"ifnull(schedule_no,'')schedule_no,ifnull(route_no,'')route_no,ifnull(trip_no,'')trip_no,ifnull(etim_no,'')etim_no,ifnull(ticket_date,'')ticket_date, axiswaybill,"+
    				"ifnull(ticket_id,'')ticket_id,ifnull(px_total_amount,'')px_total_amount,ifnull(convenience_fee,'')convenience_fee,ifnull(inserted_tm,'')inserted_tm,etm_card_amt,axis_amt,"+
    				"if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from( "+
    				"select org_name,org_chart_id,ifnull(ac.waybill_no,'')waybill_no,"+
    				"schedule_no,route_no,trip_no,etim_no,ticket_date,ar.waybill_no axiswaybill,"+
    				"ticket_id,px_total_amount,convenience_fee,"+
    				"ifnull(ac.etm_card_amt,0)etm_card_amt,(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no "+
    				"and settlement_status='Y') total_card_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
    				"from bmtcGprs.axis_reconcillation where waybill_no=eg.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,"+
    				"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,ifnull(settlement_date,'') settlement_date "+
    				"from bmtcGprs.axis_reconcillation ar left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
    				"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no left join bmtcGprs.etim_gprs_ticket eg on eg.waybill_no=ar.waybill_no "+
    				"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id where ar.settlement_date='"+date+"' and payment_mode=1 "+
    				"and oc.org_chart_id="+depot+" group by ar.waybill_no order by org_name,Ticket_Audited_Date "+
    				") a ) b where settlement_status='No' " ;
    		/*		"union "+
    				"select * from (select " +
    				"ifnull(schedule_no,'')schedule_no,ifnull(route_no,'')route_no,ifnull(trip_no,'')trip_no,ifnull(etim_no,'')etim_no,ifnull(ticket_date,'')ticket_date, axiswaybill,"+
    				"ifnull(ticket_id,'')ticket_id,ifnull(px_total_amount,'')px_total_amount,ifnull(convenience_fee,'')convenience_fee,ifnull(inserted_tm,'')inserted_tm,etm_card_amt,axis_amt,"+
    				"if(etm_card_amt-axis_amt=0,'Yes','No') settlement_status,(etm_card_amt-axis_amt) diff,Ticket_Audited_Date,settlement_date from( "+
    				"select org_name,org_chart_id,ifnull(ac.waybill_no,'')waybill_no,"+
    				"schedule_no,route_no,trip_no,etim_no,ticket_date,ar.waybill_no axiswaybill,"+
    				"ticket_id,px_total_amount,convenience_fee,"+
    				"ifnull(ac.etm_card_amt,0)etm_card_amt,(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no "+
    				"and settlement_status='Y') total_card_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
    				"from bmtcGprs.axis_reconcillation where waybill_no=eg.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,"+
    				"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,ifnull(settlement_date,'') settlement_date "+
    				"from bmtcGprs.axis_reconcillation ar left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
    				"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no left join bmtcGprs.etim_gprs_ticket150318 eg on eg.waybill_no=ar.waybill_no "+
    				"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id where ar.settlement_date='"+date+"' and payment_mode=1 "+
    				"and oc.org_chart_id="+depot+" group by ar.waybill_no order by org_name,Ticket_Audited_Date "+
    				") a ) b where settlement_status='No'";*/
    		Query query1 = session1.createSQLQuery(sql1);
    	 query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    	  aliasToValueMapList = query1.list();
    	}
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	       /* double its_transation_amount=0.0;
	        BigDecimal acc_amount=BigDecimal.ZERO;
	        //BigDecimal its_transation_amount=BigDecimal.ZERO;
	        BigDecimal axis_transation_amount=BigDecimal.ZERO;
	        BigDecimal total_transation_amount=BigDecimal.ZERO;*/
	        JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        /*	 its_transation_amount +=Double.parseDouble(list.get("px_total_amount").toString());
	        	 acc_amount = acc_amount.add((BigDecimal) list.get("etm_card_amt"));
	        	// its_transation_amount = its_transation_amount.add((BigDecimal) list.get("px_total_amount"));
	        	 axis_transation_amount = axis_transation_amount.add((BigDecimal) list.get("axis_amt"));
	        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("diff"));*/

	        	 //totalcardamount +=Double.parseDouble((String) list.get("diff"));
		  
	        	 ja.add(j);
	        	 ja.add(list.get("org_name"));
	        	 if(list.get("waybill_no")==null){ ja.add("");}else{
	        	 ja.add(list.get("waybill_no").toString());}
	        	 ja.add(list.get("schedule_no").toString());
	        	 ja.add(list.get("route_no").toString());
	        	 ja.add(list.get("trip_no").toString());
	        	 ja.add(list.get("etim_no").toString());
	        	 ja.add(list.get("ticket_date").toString());
			//regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_time").toString() +"</td>");
	        	 ja.add(list.get("inserted_tm").toString());
	        	// ja.add(list.get("ticket_no").toString());
	        	 ja.add(list.get("axiswaybill").toString());
	        	 ja.add(list.get("ticket_id").toString());
	        	 ja.add(list.get("etm_card_amt").toString());
			ja.add(list.get("px_total_amount").toString());
			//ja.add(list.get("itsconvinence_fee").toString());
			ja.add(list.get("axis_amt").toString());
			ja.add(list.get("convenience_fee").toString());
			ja.add(list.get("diff").toString());
			ja.add(list.get("settlement_status").toString());
			ja.add(list.get("Ticket_Audited_Date").toString());
			ja.add(list.get("settlement_date").toString());
			array.add(ja);
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("px_total_amount").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("axis_amt").toString()  +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("diff").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+ list.get("Ticket_Audited_Date").toString() +"</td>");
//		regionTypeAjaxString1.append("<td align='right'>"+list.get("settlement_date").toString()  +"</td>");
		//regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
		 //regionTypeAjaxString1.append("</tr>");
				
				 }
	       /* total_transation_amount=total_transation_amount.setScale(2, BigDecimal.ROUND_UP);
	        regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td colspan='11'></td>" +
	        		"<td>"+acc_amount+"</td><td>"+its_transation_amount+"</td><td></td><td>"+axis_transation_amount+"</td><td></td><td>"+total_transation_amount+"</td><td></td><td></td></tr>");
	        regionTypeAjaxString1.append("</tbody></table></div>");*/
				 PrintWriter out;
            

/*	out = response.getWriter();
	out.print(regionTypeAjaxString1);*/
	    	result.put("aaData",array);
			out = response.getWriter();
			out.print(result);
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
finally{
session1.close();
}
	return null;




}
}
