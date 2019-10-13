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
import com.trimax.its.vts.dao.VtsDataDAO;

public class DepotwiseSummeryofCardandCashTrasationReport extends ActionSupport{
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
	public String execute() {
		
		return "success";
	}
	public String getcardandcashtrasation(){
		try {
			String regionTypeAjaxString = "";
			StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String date2=common.getDateFromPicker(enddate);
		   
		System.out.println("stage1");
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		System.out.println("stage1");
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		System.out.println("stage1");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
System.out.println(date1+date2);
		 String sql="select depot_id,org_name,sum(cash_transaction)cash_transation,sum(cash_passenger)cash_passenger,sum(total_cash)total_cash,sum(card_transaction)card_transacation, "+
				 "sum(card_passenger)card_passenger,sum(total_card)total_card from (select depot_id,org_name,count(ticket_no) cash_transaction,sum(px_count) cash_passenger, "+
				 "sum(px_total_amount) total_cash,0 card_transaction, 0 card_passenger,0 total_card "+
				 "FROM bmtcGprs.etim_gprs_ticket egt inner join org_chart oc on egt.depot_id=oc.org_chart_id "+
				 "where ticket_date between '2017-08-07' and '2017-08-07' and payment_mode=0 group by depot_id "+ 
				 "union all "+
				 "select depot_id,org_name,0 cash_transaction,0 cash_passenger,0 total_cash,count(ticket_no) card_transaction,sum(px_count) card_passenger, "+
				 "sum(px_total_amount) total_card "+
				 "FROM bmtcGprs.etim_gprs_ticket egt inner join org_chart oc on egt.depot_id=oc.org_chart_id "+
				 "where ticket_date between '"+date1+"' and '"+date2+"' and payment_mode=1 group by depot_id )a group by depot_id order by org_name";
		Query query = session1.createSQLQuery(sql);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
	
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Depotwise Summery of Card and Cash Trasation Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot Id.</th><th>Depot No.</th><th>Total number of Cash transactions</th><th>Total no. of passengers</th><th>Total Revenue Realized by Cash</th><th>Total number of Card transactions</th><th>Total no. of passengers</th>" +
				"<th>Total Revenue Realized by Card</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		       BigDecimal cashamount=BigDecimal.ZERO;
		       double cardamount=0.0;
		        for (int i = 0; i < aliasToValueMapList.size(); i++) {
		        	int j=i+1;
		        	 Map<String, Object> list = aliasToValueMapList.get(i);
		 //  System.out.println("list.get(total_cash).toString()===="+list.get("total_cash").toString());
		 //  System.out.println("list.get(total_card).toString()===="+list.get("total_cash").toString());
//		       	 cashamount += (Double)list.get("total_cash");
		       	System.out.println(list.size());
		       	BigDecimal b = new BigDecimal(Double.parseDouble(list.get("total_cash").toString()));
		       	System.out.println("b====="+b);
		       	cashamount =cashamount.add(b);
		        	 cardamount =  cardamount+(Double)list.get("total_card");
		        	 
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("depot_id").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("org_name").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("cash_transation").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("cash_passenger").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("total_cash").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("card_transacation").toString()  +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("card_passenger").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("total_card").toString() +"</td>");
			
			 regionTypeAjaxString1.append("</tr>");
					
					 }System.out.println("cashamount====="+cashamount);
					 double d=cashamount.doubleValue();
					d=Math.rint(d*100)/100;
						BigDecimal dedtot=new BigDecimal(d);
						dedtot = dedtot.setScale(2, BigDecimal.ROUND_HALF_UP);
						System.out.println("ded tot "+dedtot);
					 
					 
		        regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td></td><td></td><td></td><td></td>" +
							"<td>"+dedtot+"</td><td></td><td></td><td>"+cardamount+"</td></tr>");
					 PrintWriter out;
                

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;

	}
}
