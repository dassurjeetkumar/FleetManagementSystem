package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class DailyAccAxisSettlementSummary extends ActionSupport {
	public String startdate;
	public String enddate;
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	@Override
	public String execute() {
	return "success";
	}
public String getpendingamount(){


	Session session1 = null;
	JSONObject result = new JSONObject();

	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
		Common common = new Common();
		String date=common.getDateFromPicker(startdate);
		System.out.println("enddateis----"+enddate);
		String edate=common.getDateFromPicker(enddate);
		//String date2=common.getDateFromPicker(enddate);
	   
	
	Date  ss1=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
	
	DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	HttpServletRequest req = ServletActionContext.getRequest();
	
	
	
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	
/*
		 String sql="select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,sum(px_total_amount) px_total_amount,sum(axis_amt)axis_amt,"+
"sum((etm_card_amt-axis_amt)) diff,"+
"if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,Ticket_Audited_Date,inserted_date,settlement_date from "+
"(select org_name,org_chart_id,"+
"(0) etm_card_amt,(0) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date,ifnull(egt.inserted_date,'') inserted_date,ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac left join "+ 
"bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no "+
"left join  bmtcGprs.org_chart oc on "+
"ac.depot_id=oc.org_chart_id where payment_mode=1 and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+date+" 23:59:59' "+
"and ar.settlement_date='"+date+"' group by org_name "+
"union all "+
"select org_name,org_chart_id,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+
"where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date='"+date+"'),2) axis_amt,ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_date,'') inserted_date, "+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac "+
"left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date='"+date+"' "+
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 "+
"and Ticket_Audited_Date between '"+date+" 00:00:00' and '"+date+" 23:59:59' group by ac.waybill_no "+
"order by org_name,Ticket_Audited_Date ) a group by org_name";*/
	String sql="select DISTINCT org_name,(org_chart_id) org_chart_id,"+
			"etm_card_amt,total_card_amount,axis_amt,diff,settlement_status,Ticket_Audited_Date,inserted_tm,settlement_date from ("+
			"select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,round(sum(ifnull(total_card_amount,0)),2)total_card_amount,sum(axis_amt)axis_amt,"+
"sum((etm_card_amt-axis_amt)) diff,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,Ticket_Audited_Date,inserted_tm,"+
"settlement_date from (select org_name,org_chart_id,(0) etm_card_amt,(0) total_card_amount,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
"where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac left join bmtcGprs.etim_settlement egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where settlement_status='Y' and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+edate+" 23:59:59' and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+
"group by org_name union all select org_name,org_chart_id,ac.etm_card_amt,(select sum(total_card_amount) from bmtcGprs.etim_settlement "+
"where waybill_no=ac.waybill_no and settlement_status='Y') total_card_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'),2) axis_amt,"+
"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm, ifnull(settlement_date,'') settlement_date "+
"from bmtcGprs.Acc_66 ac left join  bmtcGprs.etim_settlement egt on ac.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' left join  "+
"bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where  Ticket_Audited_Date between '"+date+" 00:00:00' "+
"and '"+edate+" 23:59:59' group by ac.waybill_no order by org_name,Ticket_Audited_Date ) a group by org_name "+
"union " +
"select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,round(sum(ifnull(total_card_amount,0)),2)total_card_amount,"+
"sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,"+
"Ticket_Audited_Date,inserted_tm,settlement_date from ( "+
"select org_name,"+
"org_chart_id,ar.waybill_no,ifnull(ac.etm_card_amt,0)etm_card_amt,"+
"(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no and settlement_status='Y') total_card_amount,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+  
"where waybill_no=ar.waybill_no and ar.settlement_date between '"+date+"' and '"+edate+"'),2) axis_amt,"+
"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.axis_reconcillation ar  "+
"left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no "+
"left join bmtcGprs.etim_gprs_ticket eg on eg.waybill_no=ar.waybill_no  "+
//"left join bmtcGprs.etim_gprs_ticket150318 egg on egg.waybill_no=ar.waybill_no "+
"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id "+
//"left join  bmtcGprs.org_chart occ on egg.depot_id=occ.org_chart_id "+
"where ar.settlement_date between '"+date+"' and '"+edate+"' and eg.payment_mode=1 "+
"group by ar.waybill_no  " +
//") a group by org_name " +
"union "+
/*"select org_name,org_chart_id,sum(etm_card_amt) etm_card_amt,round(sum(ifnull(total_card_amount,0)),2)total_card_amount,"+
"sum(axis_amt)axis_amt,sum((etm_card_amt-axis_amt)) diff,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status,"+
"Ticket_Audited_Date,inserted_tm,settlement_date from ( "+*/
"select org_name,"+
"org_chart_id,ar.waybill_no,ifnull(ac.etm_card_amt,0)etm_card_amt,"+
"(select sum(ifnull(total_card_amount,0)) from bmtcGprs.etim_settlement where waybill_no=ar.waybill_no and settlement_status='Y') total_card_amount,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+  
"where waybill_no=ar.waybill_no and ar.settlement_date between '"+date+"' and '"+edate+"'),2) axis_amt,"+
"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(egt.inserted_tm,'') inserted_tm,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.axis_reconcillation ar  "+
"left join  bmtcGprs.etim_settlement egt on ar.waybill_no=egt.waybill_no "+
"left join  bmtcGprs.Acc_66 ac on ac.waybill_no=ar.waybill_no "+
"left join bmtcGprs.etim_gprs_ticket190219 eg on eg.waybill_no=ar.waybill_no "+
//"left join bmtcGprs.etim_gprs_ticket150318 egg on egg.waybill_no=ar.waybill_no "+
"left join  bmtcGprs.org_chart oc on eg.depot_id=oc.org_chart_id "+
//"left join  bmtcGprs.org_chart occ on egg.depot_id=occ.org_chart_id "+
"where ar.settlement_date between '"+date+"' and '"+edate+"' and eg.payment_mode=1  "+
"group by ar.waybill_no ) a group by org_name) b group by org_chart_id ";

	Query query = session1.createSQLQuery(sql).addScalar("org_name").addScalar("org_chart_id").addScalar("etm_card_amt").addScalar("total_card_amount").addScalar("axis_amt").
			addScalar("diff").addScalar("settlement_status").addScalar("Ticket_Audited_Date").addScalar("inserted_tm").addScalar("settlement_date");
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();

	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Recon Summary Report</br> Date:- "+startdate+"</br></h4></div>");

	regionTypeAjaxString1.append("<div align='right'></div></div>");
        

	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot</th><th>Acc-66 Amount</th><th>Its Amount</th><th>axis Amount</th>" +
			"<th>Differnce</th><th>Status</th><th>Audited Date</th>" +
				""+"</tr></thead><tbody>");


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
	        	// its_transation_amount = its_transation_amount.add((BigDecimal) list.get("px_total_amount"));
	        	 axis_transation_amount = axis_transation_amount.add((BigDecimal) list.get("axis_amt"));
        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("diff"));


 			ja.add(j);
 			
 		ja.add(
 					"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwisereport('"
 					+ list.get("org_chart_id").toString()
                     + "','"
                     + date
                     + "','"
                      + edate
 					+ "') title='Get Depot Wise Details' id='alert_details"
 					+ "'>"
 					+ list.get("org_name").toString()+""
 					+ "</a>");
 			
 		ja.add(list.get("etm_card_amt").toString());
 			ja.add(list.get("total_card_amount").toString());
 			ja.add(list.get("axis_amt").toString());
 			if(list.get("settlement_status").equals("No")){
 				ja.add(
 						"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getdepotnotsettamt('"
 						+ list.get("org_chart_id").toString()
 	                    + "','"
 	                    + date+ "','"+edate
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
 	        total_transation_amount=total_transation_amount.setScale(2, BigDecimal.ROUND_UP);
 	       BigDecimal d=new BigDecimal(its_transation_amount);
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
public String getdepotwise(){

	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);

		
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depotid");
	
	String date=req.getParameter("startdate");
	String edate=req.getParameter("enddate");
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	

	String sql="select org_name,org_chart_id,waybill_no,device_serial_number,etm_card_amt,px_total_amount,axis_amt,ifnull(Ticket_Audited_Date,'')Ticket_Audited_Date,ifnull(inserted_tm,'')inserted_tm,settlement_date,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status from (" +
			"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,(0) etm_card_amt,(0) px_total_amount,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
"where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac left join bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no " +
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where payment_mode=1 and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+edate+" 23:59:59' and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+
"and ac.depot_id="+depot+" group by ac.waybill_no "+
"union all "+ 
"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+ 
"where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'),2) axis_amt,"+
"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm, ifnull(settlement_date,'') settlement_date "+
"from bmtcGprs.Acc_66 ac left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+ 
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 and Ticket_Audited_Date between '"+date+" 00:00:00' "+
"and '"+edate+" 23:59:59'  and ac.depot_id="+depot+" group by ac.waybill_no )b group by waybill_no "+
"union "+
"select org_name,org_chart_id,waybill_no,device_serial_number,etm_card_amt,px_total_amount,axis_amt,ifnull(Ticket_Audited_Date,'')Ticket_Audited_Date,ifnull(inserted_tm,'')inserted_tm,settlement_date,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status from (" +
"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,(0) etm_card_amt,(0) px_total_amount,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
"where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac left join bmtcGprs.etim_gprs_ticket190219 egt on ac.waybill_no=egt.waybill_no " +
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where payment_mode=1 and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+edate+" 23:59:59' and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+
"and ac.depot_id="+depot+" group by ac.waybill_no "+
"union all "+ 
"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+ 
"where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'),2) axis_amt,"+
"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm, ifnull(settlement_date,'') settlement_date "+
"from bmtcGprs.Acc_66 ac left join  bmtcGprs.etim_gprs_ticket190219 egt on ac.waybill_no=egt.waybill_no "+
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+ 
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 and Ticket_Audited_Date between '"+date+" 00:00:00' "+
"and '"+edate+" 23:59:59'  and ac.depot_id="+depot+" group by ac.waybill_no )b group by waybill_no";
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
	        
			//regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_time").toString() +"</td>");
	        	
	        	 ja.add(list.get("etm_card_amt").toString());
	        	 if(list.get("px_total_amount")==null){
	        		 ja.add("");
	        	 }else{
			ja.add(list.get("px_total_amount").toString());}
			
			ja.add(list.get("axis_amt").toString());
			
			ja.add(list.get("settlement_status").toString());
			ja.add(list.get("Ticket_Audited_Date").toString());
			ja.add(list.get("inserted_tm").toString());
			ja.add(list.get("settlement_date").toString());
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
	headrearray.add(req.getParameter("enddate"));
	
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
public String depotwisenotsettleamt(){


	JSONObject result = new JSONObject();
	Session session1 = null;
	try {
		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);

		
	HttpServletRequest req = ServletActionContext.getRequest();
	String depot=req.getParameter("depotid");
	
	String date=req.getParameter("startdate");
	String edate=req.getParameter("enddate");
	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	

	String sql="select * from (select org_name,org_chart_id,waybill_no,device_serial_number,etm_card_amt,px_total_amount,axis_amt,ifnull(Ticket_Audited_Date,'')Ticket_Audited_Date,ifnull(inserted_tm,'')inserted_tm,settlement_date,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status from (" +
			"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,(0) etm_card_amt,(0) px_total_amount,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
"where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac left join bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no " +
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where payment_mode=1 and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+edate+" 23:59:59' and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+
"and ac.depot_id="+depot+" group by ac.waybill_no "+
"union all "+ 
"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+ 
"where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'),2) axis_amt,"+
"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm, ifnull(settlement_date,'') settlement_date "+
"from bmtcGprs.Acc_66 ac left join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+ 
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 and Ticket_Audited_Date between '"+date+" 00:00:00' "+
"and '"+edate+" 23:59:59'  and ac.depot_id="+depot+" group by ac.waybill_no )b group by waybill_no "+
"union "+
"select org_name,org_chart_id,waybill_no,device_serial_number,etm_card_amt,px_total_amount,axis_amt,ifnull(Ticket_Audited_Date,'')Ticket_Audited_Date,ifnull(inserted_tm,'')inserted_tm,settlement_date,if(sum(etm_card_amt)-sum(axis_amt)=0,'Yes','No') settlement_status from (" +
"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,(0) etm_card_amt,(0) px_total_amount,"+
"round((select ifnull((sum(transaction_amount-convenience_fee)),0) from bmtcGprs.axis_reconcillation "+ 
"where waybill_no=ac.waybill_no),2) axis_amt,Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm,"+
"ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac left join bmtcGprs.etim_gprs_ticket190219 egt on ac.waybill_no=egt.waybill_no " +
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
"where payment_mode=1 and Ticket_Audited_Date not between '"+date+" 00:00:00' and '"+edate+" 23:59:59' and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+
"and ac.depot_id="+depot+" group by ac.waybill_no "+
"union all "+ 
"select org_name,org_chart_id,ac.waybill_no,ac.device_serial_number,ac.etm_card_amt,(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+ 
"where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
"from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59'),2) axis_amt,"+
"ifnull(Ticket_Audited_Date,'') Ticket_Audited_Date,ifnull(es.inserted_tm,'') inserted_tm, ifnull(settlement_date,'') settlement_date "+
"from bmtcGprs.Acc_66 ac left join  bmtcGprs.etim_gprs_ticket190219 egt on ac.waybill_no=egt.waybill_no "+
"left join bmtcGprs.etim_settlement es on ac.waybill_no=es.waybill_no and settlement_status='Y' "+
"left join  bmtcGprs.axis_reconcillation ar on ac.waybill_no=ar.waybill_no and ar.settlement_date between '"+date+" 00:00:00' and '"+edate+" 23:59:59' "+ 
"left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id where payment_mode=1 and Ticket_Audited_Date between '"+date+" 00:00:00' "+
"and '"+edate+" 23:59:59'  and ac.depot_id="+depot+" group by ac.waybill_no )b group by waybill_no) b where settlement_status='No'";
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
	        
			//regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_time").toString() +"</td>");
	        	
	        	 ja.add(list.get("etm_card_amt").toString());
	         	 if(list.get("px_total_amount")==null){
	        		 ja.add("");
	        	 }else{
			ja.add(list.get("px_total_amount").toString());}
			
			ja.add(list.get("axis_amt").toString());
			ja.add(list.get("settlement_status").toString());
			ja.add(list.get("Ticket_Audited_Date").toString());
			ja.add(list.get("inserted_tm").toString());
			ja.add(list.get("settlement_date").toString());
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
}
