package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
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

public class SmartCardExceptionReport extends ActionSupport{
	public String startdate;
public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

public String execute() {
		
		return "success";
	}

public String getsmartcardexception(){
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
	//String depot=req.getParameter("depot");
	Connection connection=null;
	Statement stmt=null;
	Session session1 = null;
	ResultSet rs=null;
	Transaction transaction  = null;
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 String realpath = ServletActionContext.getRequest()
				.getRealPath("/");

	
		 String sql="SELECT waybill_no, `schedule_no`, `etim_no`, `route_no`, `ticket_no`,px_total_amount,0 as transaction_amount "+
			 "FROM bmtcGprs.etim_gprs_ticket060717 "+
			 "WHERE `payment_mode` = '1' AND `ticket_date` = '"+date1+"' and (waybill_no,ticket_no)  "+
			 "not in (select waybill_no,ticket_id from bmtcGprs.axis_reconcillation where transaction_date = '"+date1+"') "+
			 "union all "+
			 "SELECT waybill_no, `schedule_no`,  `etim_no`, `route_no`, `ticket_no`,px_total_amount,0 as transaction_amount "+
			 "FROM bmtcGprs.etim_gprs_ticket "+ 
			 "WHERE `payment_mode` = '1' AND `ticket_date` = '"+date1+"' and (waybill_no,ticket_no) "+ 
			 "not in (select waybill_no,ticket_id from bmtcGprs.axis_reconcillation where transaction_date = '"+date1+"') "+
			 "union all "+
			 "SELECT ar.waybill_no, egt.schedule_no, `etim_no`, `route_no`, ticket_id as ticket_no,0 as px_total_amount,transaction_amount "+
			 "FROM bmtcGprs.axis_reconcillation ar "+
			 "inner join bmtcGprs.etim_gprs_ticket egt on egt.waybill_no=ar.waybill_no "+
			 "WHERE `transaction_date` = '"+date1+"' and (ar.waybill_no,ticket_id) "+ 
			 "not in (select waybill_no,ticket_no from bmtcGprs.etim_gprs_ticket where ticket_date = '"+date1+"') "+
			 "union all "+
			 "SELECT ar.waybill_no, egt.schedule_no, `etim_no`, `route_no`, ticket_id as ticket_no,0 as px_total_amount,transaction_amount "+
			 "FROM bmtcGprs.axis_reconcillation ar "+
			 "inner join bmtcGprs.etim_gprs_ticket060717 egt on egt.waybill_no=ar.waybill_no "+
			 "WHERE `transaction_date` = '"+date1+"' and (ar.waybill_no,ticket_id) "+ 
			 "not in (select waybill_no,ticket_no from bmtcGprs.etim_gprs_ticket060717 where ticket_date = '"+date1+"') "+
			 "union all "+
			 "SELECT egt.waybill_no, `schedule_no`, `etim_no`, `route_no`, `ticket_no`,px_total_amount,transaction_amount "+
			 "FROM bmtcGprs.etim_gprs_ticket060717 egt "+
			 "inner join bmtcGprs.axis_reconcillation ar on ar.waybill_no=egt.waybill_no and ar.ticket_id=egt.ticket_no "+
			 "WHERE `payment_mode` = '1' AND `ticket_date` = '"+date1+"' and px_total_amount!=transaction_amount "+
			 "union all "+
			 "SELECT egt.waybill_no, `schedule_no`, `etim_no`, `route_no`, `ticket_no`,px_total_amount,transaction_amount "+
			 "FROM bmtcGprs.etim_gprs_ticket egt "+
			 "inner join bmtcGprs.axis_reconcillation ar on ar.waybill_no=egt.waybill_no and ar.ticket_id=egt.ticket_no "+
			 "WHERE `payment_mode` = '1' AND `ticket_date` = '"+date1+"' and px_total_amount!=transaction_amount";
	Query query = session1.createSQLQuery(sql);
    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List<Map<String, Object>> aliasToValueMapList = query.list();

	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Smart Card Exception Report </br>Report Date:- "+startdate+"</br></h4></div>");

	regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
        

	regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
	regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Waybill No</th><th>Schedule No</th><th>Etim No</th><th>Route No</th><th>Ticket No</th>" +
			"<th>Px Total Amount</th><th>Transaction Amount</th>" +
				""+"</tr></thead><tbody>");
	

	        HttpServletResponse response = ServletActionContext.getResponse();
	       double totalpxamount=0.0;
	        BigDecimal total_transation_amount=BigDecimal.ZERO;
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        	 totalpxamount += Double.parseDouble((String) list.get("px_total_amount"));
	        	 total_transation_amount = total_transation_amount.add((BigDecimal) list.get("transaction_amount"));
		   regionTypeAjaxString1.append("<tr>");
			regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("waybill_no").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("schedule_no").toString() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+list.get("etim_no").toString() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.get("route_no").toString() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.get("ticket_no").toString()  +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.get("px_total_amount").toString() +"</td>");
		regionTypeAjaxString1.append("<td align='right'>"+ list.get("transaction_amount").toString() +"</td>");
		
		 regionTypeAjaxString1.append("</tr>");
				
				 }
	        regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td></td><td></td><td></td><td></td>" +
						"<td></td><td>"+totalpxamount+"</td><td>"+total_transation_amount+"</td></tr>");
				 PrintWriter out;
            

	out = response.getWriter();
	out.print(regionTypeAjaxString1);
	
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}

	return null;

}
	
}
