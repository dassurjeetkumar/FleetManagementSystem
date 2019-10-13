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

public class DaillyActiveEtmData {

	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	private String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
	public String getdata(){
		JSONObject result = new JSONObject();
		Common common = new Common();
		String startdate1=common.getDateFromPicker(startdate);
		Session session = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
	/*	
		String divid=req.getParameter("divid");
		String subquy="and oc.parent_id="+divid;
		if(divid.equals("0")){
			subquy="";
		}*/
		
		
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="SELECT etim_no,org_name,schedule_no,st.shift_type_name,vehicle_no,bstat,gprssig "+
                    " FROM bmtcGprs.etim_gprs_ticket egt "+
                    "  inner join org_chart oc on egt.depot_id=oc.org_chart_id inner join shift_type st on st.shift_type_id=egt.shift_no "+
                   " WHERE ticket_date ='"+startdate1+"' " +
                   		//" and oc.org_name='Depot-02' " +
                   		" group by etim_no "+
                    "  order by egt.depot_id ";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();	
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("etim_no").toString());
		ja.add(list.get("org_name").toString());
			ja.add(list.get("schedule_no").toString());
			ja.add(list.get("shift_type_name").toString());
			ja.add(list.get("vehicle_no").toString());
			ja.add(list.get("bstat").toString());
			ja.add(list.get("gprssig").toString());

			
				//ja.add(list.get("total").toString());
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

}
