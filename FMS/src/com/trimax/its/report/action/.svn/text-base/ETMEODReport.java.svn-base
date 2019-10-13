package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class ETMEODReport extends ActionSupport{
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

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
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
//		System.out.println("division........"+divisionlist);	
		return "success";
	}
	
	public String getETMEODReport(){
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
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");

		
		/*String sql="SELECT `device_id`, `waybill_no`, `total_transaction`, `total_amount`, `total_card_amount`, `terminal_id`, " +
				"if(settlement_status='N','Fail','Success') status FROM bmtcGprs.etim_settlement " +
				"WHERE `inserted_tm` between '"+date1+" 00:00:00' and '"+date2+" 23:59:59'";*/
		 String sql="SELECT oc.org_name,oc.org_chart_id,bG.device_id,waybill_no, bG.total_transaction, bG.total_amount, bG.total_card_amount, bG.terminal_id, "+
				 "if(settlement_status='N','Fail','Success') status FROM bmtcGprs.etim_settlement bG inner join org_chart oc "+
				 "on oc.org_code=left(bG.waybill_no,3) and oc.org_chart_id="+depot+" "+
				 "WHERE bG.inserted_tm between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' and oc.deleted_status=0";
		Query query = session1.createSQLQuery(sql);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
	
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>ETM EOD Report </br>From Date:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>OrgName</th><th>Org chat Id</th><th>Device Id</th><th>Waybill No</th><th>Total Transaction</th>" +
				"<th>Total Amount</th><th>Total Card Amount</th><th>Terminal Id</th><th>Status</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		        double totalamount=0.0;
		        double totalcardamount=0.0;
		        for (int i = 0; i < aliasToValueMapList.size(); i++) {
		        	int j=i+1;
		        	 Map<String, Object> list = aliasToValueMapList.get(i);
		        	 totalamount +=Double.parseDouble((String) list.get("total_amount"));
		        	 totalcardamount +=Double.parseDouble((String) list.get("total_card_amount"));
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("org_name").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("org_chart_id").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("device_id").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("waybill_no").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("total_transaction").toString()  +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("total_amount").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("total_card_amount").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("terminal_id").toString()  +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
			 regionTypeAjaxString1.append("</tr>");
					
					 }
		        regionTypeAjaxString1.append("<tr><td colspan='0'>Total</td><td></td><td></td><td></td><td></td>" +
							"<td></td><td>"+totalamount+"</td><td>"+totalcardamount+"</td><td></td><td></td></tr>");
					 PrintWriter out;
                

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;

	}

}
