package com.trimax.its.report.action;

import java.io.PrintWriter;
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

public class ActionTakenReport extends ActionSupport {
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

	public String getreportdepotwise(){
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
		Connection connection=null;
		Statement stmt=null;
	
		ResultSet rs=null;
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 String realpath = ServletActionContext.getRequest()
					.getRealPath("/");

	
		 String sql="select oc.org_name,e.EMPLOYEE_NAME,e.TOKEN,c.compliant_type,complaint_description,identification_data,complaint_date,"+
				 "complaint_media from complaint c "+
				 "inner join org_chart oc on depot_id=org_chart_id "+
				 "inner join employee e on e.EMPLOYEE_ID=c.employee_name "+
				 "where depot_id='"+depot+"' and complaint_date between '"+date1+"' and '"+date2+"'";
		Query query = session1.createSQLQuery(sql);
		System.out.println("queryyyyyyyyyyyyyyyy");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
	System.out.println("======uuuu");
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Action Taken Report </br>Date Of Action From:- "+startdate+" To Date:-"+ enddate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot</th><th>Employee Name</th><th>Token No</th><th>Fault</th><th>Remarks</th>" +
				"<th>Refrence No</th><th>Date of action taken</th><th>Action Taken</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		       /* double totalamount=0.0;
		        double totalcardamount=0.0;*/
		        for (int i = 0; i < aliasToValueMapList.size(); i++) {
		        	int j=i+1;
		        	 Map<String, Object> list = aliasToValueMapList.get(i);
		        	 System.out.println("===="+list.size());
		        	/* totalamount +=Double.parseDouble((String) list.get("total_amount"));
		        	 totalcardamount +=Double.parseDouble((String) list.get("total_card_amount"));*/
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("org_name").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("EMPLOYEE_NAME").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("TOKEN").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("compliant_type").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("complaint_description").toString()  +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("identification_data").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+ list.get("complaint_date").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("complaint_media").toString()  +"</td>");
	//regionTypeAjaxString1.append("<td align='right'>"+ list.get("status").toString()  +"</td>");
			 regionTypeAjaxString1.append("</tr>");
					
					 }
		    
					 PrintWriter out;
                

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		session1.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			session1.close();
		}
		
		return null;

	}
}
