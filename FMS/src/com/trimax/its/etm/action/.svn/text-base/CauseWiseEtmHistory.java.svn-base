package com.trimax.its.etm.action;

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

public class CauseWiseEtmHistory {

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
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;}
	
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
	public String getdata(){

		

		JSONObject result = new JSONObject();
		Session session = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		 
		Common common = new Common();
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
		String divid=req.getParameter("divid");
		String depot=req.getParameter("depot");
		
	    String depots="";
        if(divid.equals("0")){
        }else if(!divid.equals("0") && depot.equals("0")){
     	   depots="and oc.parent_id="+divid+" "; 
        }else if(!divid.equals("0") && !depot.equals("0")){
     	   depots=" and oc.org_chart_id="+depot+" "; 
        }
		
		
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select org_name,device_serial_number,id,DATEDIFF(etm_received_date,etm_created_date)noofdays,sum(case when etm_issue=1 then 1 else 0 end) battpro,sum(case when "+ "etm_issue=2 then 1 else 0 end) dispro,"+
				"sum(case when etm_issue=3 then 1 else 0 end) etmdamage,sum(case when etm_issue=4 then 1 else 0 end) keypadpro,"+
				"sum(case when etm_issue=5 then 1 else 0 end) printerpro,sum(case when etm_issue=6 then 1 else 0 end) autorestartorswithoff,"+
				"sum(case when etm_issue=7 then 1 else 0 end) tamperpro,sum(case when etm_issue=8 then 1 else 0 end) usbpro,"+
				"sum(case when etm_issue=9 then 1 else 0 end) tktpro,sum(case when etm_issue=10 then 1 else 0 end) logpro,"+
				"sum(case when etm_issue=11 then 1 else 0 end) settpro,sum(case when etm_issue=12 then 1 else 0 end) lostetm,"+
				"sum(case when etm_issue=13 then 1 else 0 end) 3code,sum(case when etm_issue=14 then 1 else 0 end) versionpro,"+
				"sum(case when etm_issue=15 then 1 else 0 end) dustindisplay,sum(case when etm_issue=16 then 1 else 0 end) etmsnonotmatched from ( "+
				"select org_name,device_serial_number,edh.id,etm_issue,etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,"+
				"etm_resolved_by_verifone,etm_received_by_fme,etm_received_date "+
				"from etm_device_history edh  "+
				"inner join device d on d.device_id=edh.etm_number "+
				"inner join org_chart oc on edh.depot_id=oc.org_chart_id "+
				"inner join etm_issue ei on edh.etm_issue =ei.id "+
				"where etm_received_date between '"+date1+"' and '"+date2+"' "+depots+" "+
				"union all "+
				"select org_name,device_serial_number,edh.id,etm_issue_id as etm_issue,etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,"+
				"etm_resolved_by_verifone,etm_received_by_fme,etm_received_date "+
				"from etm_device_history edh "+
				"inner join device d on d.device_id=edh.etm_number "+
				"inner join org_chart oc on edh.depot_id=oc.org_chart_id "+
				"inner join etm_device_issue edi on edh.id=edi.etm_device_history_id "+
				"where etm_received_date between '"+date1+"' and '"+date2+"' "+depots+" "+
				")a group by a.device_serial_number order by org_name";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();
	
	   
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("org_name").toString());
		ja.add(list.get("device_serial_number").toString());
			ja.add(list.get("noofdays").toString());
			
			ja.add(list.get("battpro").toString());
			//ja.add(Integer.parseInt(list.get("sch_0").toString())+Integer.parseInt(list.get("sch_v").toString())+Integer.parseInt(list.get("sch_vv").toString())+Integer.parseInt(list.get("sch_a").toString()));
			ja.add(list.get("dispro").toString());
			ja.add(list.get("etmdamage").toString());
			ja.add(list.get("keypadpro").toString());
			ja.add(list.get("printerpro").toString());
			ja.add(list.get("autorestartorswithoff").toString());
			ja.add(list.get("tamperpro").toString());
			ja.add(list.get("usbpro").toString());
			ja.add(list.get("tktpro").toString());
			ja.add(list.get("logpro").toString());
			ja.add(list.get("settpro").toString());
			ja.add(list.get("lostetm").toString());
			ja.add(list.get("3code").toString());
			ja.add(list.get("versionpro").toString());
			ja.add(list.get("dustindisplay").toString());
			ja.add(list.get("etmsnonotmatched").toString());

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
