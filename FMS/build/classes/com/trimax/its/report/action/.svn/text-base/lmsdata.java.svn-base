package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
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

public class lmsdata {

	public String startdate;
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	private Map<String,String>depotlist;
	public Map<String, String> getDepotlist() {
		return depotlist;
	}
	public void setDepotlist(Map<String, String> depotlist) {
		this.depotlist = depotlist;
	}
	public String execute() {
		/*VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();*/
		///==========
		Session session = null;
		Map depot=new LinkedHashMap();
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select org_name from org_chart where deleted_status=0 and org_name like '%depot%' and org_name !='Depot-Test' order by org_name ";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();	
	    
	    
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        	 depot.put(list.get("org_name").toString().replace("Depot-0", "Depot-"), list.get("org_name").toString());
	        }
		}catch (Exception e) {
		e.printStackTrace();
		}finally{session.close();}
		this.depotlist=depot;
		return "success";
	}
	public String getdata(){
		JSONObject result = new JSONObject();
		Session session = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		String depot=req.getParameter("depot");
		String date=req.getParameter("startdate");
		Common common = new Common();
		 date=common.getDateFromPicker(startdate);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date date1 = sdf.parse(date);
         date=sdf.format(date1);
		System.out.println(date);
		String qry1="";
		if(!depot.equals("0")){
			qry1="depot='"+depot+"' and ";
		}
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select e_name,token_no,pf_number,designation,leave_from,leave_to,total_leave,applied_date,"
				+ "approved_date,leave_type,location,depot "+
                "from lms_data where "+qry1+" leave_from between '"+date+" 00:00:00' and '"+date+" 23:59:59'";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();	
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("depot").toString());
		ja.add(list.get("e_name").toString());
			ja.add(list.get("token_no").toString());
			ja.add(list.get("pf_number").toString());
			ja.add(list.get("designation").toString());
			ja.add(list.get("leave_from").toString());
			ja.add(list.get("leave_to").toString());
			ja.add(list.get("total_leave").toString());
			ja.add(list.get("applied_date").toString());
			ja.add(list.get("approved_date").toString());
			ja.add(list.get("leave_type").toString());
			ja.add(list.get("location").toString());
			
			
		
			array.add(ja);
			
				
				 }
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public String header(){
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		JSONObject result = new JSONObject();
		JSONArray headrearray = new JSONArray();
		headrearray.add(req.getParameter("depot"));
		headrearray.add(req.getParameter("startdate"));
		//headrearray.add(req.getParameter("enddate"));
		
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
}
