package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ReconTicketSale extends ActionSupport{
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
	
	public String getreconticketsale(){
		Session session1 = null;
		try {
			String regionTypeAjaxString = "";
			StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
		   
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		String depot=req.getParameter("depot");
		
		
		
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
		String sql="select org_name,waybill_no,schedule_no,route_no,trip_no,etim_no,ticket_date,ticket_time,inserted_date,ticket_no,ifnull(axiswaybill,'') axiswaybill,"+
                     "ifnull(ticket_id,'') ticket_id,etm_card_amt,px_total_amount,itsconvinence_fee,ifnull(axis_amt,0)axis_amt,ifnull(convenience_fee,0)convenience_fee,"+
                      "if(etm_card_amt-axis_amt=0,'Yes','NO') settlement_status,"+
                     "(etm_card_amt-axis_amt) diff,"+
                      "Ticket_Audited_Date,ifnull(settlement_date,'') settlement_date "+
                      "from (select org_name,ac.waybill_no,schedule_no,route_no,trip_no,etim_no,ticket_date,ticket_time,inserted_date,ticket_no,ar.waybill_no as axiswaybill,"+
                       "ticket_id,convinence_fee as itsconvinence_fee,"+
                        "ac.etm_card_amt,"+
                         "(select sum(px_total_amount) from bmtcGprs.etim_gprs_ticket "+
                       "where waybill_no=ac.waybill_no and payment_mode=1) px_total_amount,round((select ifnull((sum(transaction_amount-convenience_fee)),0) "+
                      "from bmtcGprs.axis_reconcillation  where waybill_no=ac.waybill_no),2) axis_amt,ar.convenience_fee,Ticket_Audited_Date,"+
                       "ifnull(settlement_date,'') settlement_date from bmtcGprs.Acc_66 ac inner join  bmtcGprs.etim_gprs_ticket egt on ac.waybill_no=egt.waybill_no "+
                       "left join  bmtcGprs.axis_reconcillation ar on egt.waybill_no=ar.waybill_no left join  bmtcGprs.org_chart oc on ac.depot_id=oc.org_chart_id "+
                       "where payment_mode=1 and Ticket_Audited_Date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' group by ac.waybill_no "+
                       "order by org_name,Ticket_Audited_Date) a";
		Query query = session1.createSQLQuery(sql);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
	
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Recon Of Ticket Sale</br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>OrgName</th><th>waybill no</th><th>Schedule no</th><th>Route no</th><th>Trip no</th><th>Etm no</th><th>Ticket Date</th><th>Ticket Time</th><th>Inserted Date</th><th>Its Ticket no</th><th>Axis waybill no</th><th>Axis Ticket Id</th><th>Acc-66 Amount</th><th>Its Amount</th><th>Its Convinence Fee no</th><th>axis Amount</th>" +
				"<th>Axis Convenience Fee</th><th>Differnce</th><th>Status</th><th>Acc-66 Date</th><th>Axis Settlement Date</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		        double its_transation_amount=0.0;
		        BigDecimal acc_amount=BigDecimal.ZERO;
		        //BigDecimal its_transation_amount=BigDecimal.ZERO;
		        BigDecimal axis_transation_amount=BigDecimal.ZERO;
		        BigDecimal total_transation_amount=BigDecimal.ZERO;
		        for (int i = 0; i < aliasToValueMapList.size(); i++) {
		        	int j=i+1;
		        	 Map<String, Object> list = aliasToValueMapList.get(i);
		        	 its_transation_amount +=Double.parseDouble(list.get("px_total_amount").toString());
		        	 acc_amount = acc_amount.add((BigDecimal) list.get("etm_card_amt"));
		        	// its_transation_amount = its_transation_amount.add((BigDecimal) list.get("px_total_amount"));
		        	 axis_transation_amount = axis_transation_amount.add((BigDecimal) list.get("axis_amt"));
		        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("diff"));

		        	 //totalcardamount +=Double.parseDouble((String) list.get("diff"));
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("org_name").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("waybill_no").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("schedule_no").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("route_no").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("trip_no").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("etim_no").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_date").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_time").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("inserted_date").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_no").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("axiswaybill").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("ticket_id").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("etm_card_amt").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("px_total_amount").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("itsconvinence_fee").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("axis_amt").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("convenience_fee").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("diff").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("settlement_status").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("Ticket_Audited_Date").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("settlement_date").toString() +"</td>");
//			regionTypeAjaxString1.append("<td align='right'>"+ list.get("px_total_amount").toString() +"</td>");
//			regionTypeAjaxString1.append("<td align='right'>"+ list.get("axis_amt").toString()  +"</td>");
//			regionTypeAjaxString1.append("<td align='right'>"+ list.get("diff").toString() +"</td>");
//			regionTypeAjaxString1.append("<td align='right'>"+ list.get("Ticket_Audited_Date").toString() +"</td>");
//			regionTypeAjaxString1.append("<td align='right'>"+list.get("settlement_date").toString()  +"</td>");
			//regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
			 regionTypeAjaxString1.append("</tr>");
					
					 }
		        total_transation_amount=total_transation_amount.setScale(2, BigDecimal.ROUND_UP);
		        regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td colspan='12'></td>" +
		        		"<td>"+acc_amount+"</td><td>"+its_transation_amount+"</td><td></td><td>"+axis_transation_amount+"</td><td></td><td>"+total_transation_amount+"</td><td></td><td></td></tr>");
		        regionTypeAjaxString1.append("</tbody></table></div>");
					 PrintWriter out;
                

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
finally{
	session1.close();
}
		return null;

	
	}
}
