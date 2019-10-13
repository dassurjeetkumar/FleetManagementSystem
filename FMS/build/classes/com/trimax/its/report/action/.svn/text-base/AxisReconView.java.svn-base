package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class AxisReconView {


	private String startdate;
	private String enddate;
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
    public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String execute()  {

		return "success";
}

	 //  List list = new ArrayList();
	public void getdata() {
		JSONObject result = new JSONObject();


			Session session1 = null;
		

			HttpServletRequest req=ServletActionContext.getRequest();
			 Common common = new Common();
			String date=common.getDateFromPicker(startdate);
			String edate=common.getDateFromPicker(enddate);

			try {
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");

			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
	

			String sql1="SELECT `waybill_no`, `ticket_id`, `transaction_amount`, `convenience_fee`, `transaction_date`, `transaction_time`, `terminal_id`, `card_number`, `merchant_id`, `product_id` " + 
					" FROM bmtcGprs.axis_reconcillation " + 
					"WHERE `transaction_date` between  '"+date+"' and '"+edate+"' order by transaction_date,waybill_no";

			Query qry=session1.createSQLQuery(sql1)
				.addScalar("waybill_no",Hibernate.STRING)
				.addScalar("ticket_id",Hibernate.STRING)
				.addScalar("transaction_amount",Hibernate.STRING)
				.addScalar("convenience_fee",Hibernate.STRING)
				.addScalar("transaction_date",Hibernate.STRING)
				.addScalar("transaction_time",Hibernate.STRING)
				.addScalar("card_number",Hibernate.STRING)
				.addScalar("merchant_id",Hibernate.STRING)
				.addScalar("product_id",Hibernate.STRING);

			
			
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry.list();	
	
			      JSONArray array = new JSONArray();
				int j=0;
				for(int i=0;aliasToValueMapList.size()>i;i++) {
					
					Map<String, Object> rs=aliasToValueMapList.get(i);
					JSONArray ja=new JSONArray();
    j=i+1;
    		
				 ja.add(j);
				 ja.add(rs.get("waybill_no"));
			 ja.add(rs.get("ticket_id"));
				 ja.add(rs.get("transaction_amount"));
				 ja.add(rs.get("convenience_fee"));
					 ja.add(rs.get("transaction_date"));
			 ja.add(rs.get("transaction_time"));
				 ja.add(rs.get("terminal_id"));
				 ja.add(rs.get("card_number"));
				 ja.add(rs.get("merchant_id"));
				 ja.add(rs.get("product_id"));
				 
				 

					array.add(ja);
				}
			     HttpServletResponse response = ServletActionContext.getResponse();

			   	 PrintWriter out;

			   		    	result.put("aaData",array);
			   				out = response.getWriter();
			   				out.print(result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
				
}


}
