package com.trimax.its.report.action;

import java.io.PrintWriter;
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

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class StudentPassCount {
	private String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	private String enddate;
	
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String execute() {
		return "success";
	}
	public void getdata() {
		

		JSONObject result = new JSONObject();
		Session session = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select Date(inserted_date) date1,count(*) tot from student_details "
				+ "where inserted_date between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' "
				+ "group by date1";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();

	   
	    
	      JSONArray array = new JSONArray();
	      int tot=0;
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("date1").toString());
		//ja.add(list.get("tot").toString());
			 ja.add("<a a data-toggle='modal' class='btn blue' role='button' href='#mymodal11'   onclick=javascript:getpassdetails('"+list.get("date1").toString()+"') title='Vehicle Details'>"+list.get("tot").toString() + "</a>");

			array.add(ja);
			
				tot=tot+Integer.parseInt(list.get("tot").toString());
				 }
	     	JSONArray ja = new JSONArray();
	     	ja.add("Total");ja.add("");ja.add(tot);
	    	array.add(ja);
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}

		
	
		
	}
}
