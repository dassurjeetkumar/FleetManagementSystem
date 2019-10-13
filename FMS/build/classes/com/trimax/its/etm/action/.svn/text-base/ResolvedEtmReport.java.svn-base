package com.trimax.its.etm.action;

import java.io.PrintWriter;
import java.text.DateFormat;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ResolvedEtmReport {

	
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	
	    private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;
		private String depotlist1;
		private String divisionlist1;
		public String value;
		
	


	public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}


	public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}


	public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
		}


		public Map<Integer, String> getDepotlist() {
			return depotlist;
		}


		public void setDepotlist(Map<Integer, String> depotlist) {
			this.depotlist = depotlist;
		}


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
		char fl = 18;
		char f2 = 12;
		int pagi = 1;

		int FULL_LEN = 120;
		int HALF_LEN = 60;

		double totalAmmount=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";
		StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);

		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
//			System.out.println("division........"+divisionlist);	
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
	     	   depots=" and oc.parent_id="+divid+" "; 
	        }else if(!divid.equals("0") && !depot.equals("0")){
	     	   depots=" and oc.org_chart_id="+depot+" "; 
	        }
			
			
			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
/*			String sql="select org_name,org_chart_id,"+
					"sum(case when etm_created_date is not null && etm_pickup_by_fme is null then 1 else 0 end)depot,"+
					"sum(case when etm_pickup_by_fme is not null && etm_pickup_by_verifone is null then 1 else 0 end)fme,"+
					"sum(case when etm_pickup_by_verifone is not null && etm_resolved_by_verifone is null then 1 else 0 end)verpick,"+
					"sum(case when etm_resolved_by_verifone is not null && etm_received_by_fme is null then 1 else 0 end)verresolve,"+
					"sum(case when etm_received_by_fme is not null && etm_received_date is null then 1 else 0 end)recevefme "+
					"from( "+
					"select org_name,org_chart_id,device_serial_number,edh.id,"+
					"etm_issue,"+
					"etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,"+
					"etm_resolved_by_verifone,etm_received_by_fme,etm_received_date "+
					"from etm_device_history edh "+
					//"inner join device d on d.device_id=edh.etm_number "+
					"inner join device d on d.device_id=edh.etm_number "+
					"inner join org_chart oc on edh.depot_id=oc.org_chart_id "+
					"inner join etm_issue ei on edh.etm_issue =ei.id "+
					"where etm_received_date is null and  etm_created_date between '"+date1+"' and '"+date2+"' "+depots+" )a group by org_name";*/
			
			String sql="select org_name,device_serial_number,"+
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es "+
					"inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name,"+
					"etm_created_date,ifnull(etm_pickup_by_fme,'')etm_pickup_by_fme,ifnull(etm_resolved_by_fme,'')etm_resolved_by_fme,"+
					"ifnull(etm_pickup_by_verifone,'')etm_pickup_by_verifone,"+
					"ifnull(etm_resolved_by_verifone,'')etm_resolved_by_verifone,ifnull(etm_received_by_fme,'')etm_received_by_fme,"+
					"ifnull(etm_received_date,'')etm_received_date,ifnull(resolved_reason,'')resolved_reason,"
					+ "ifnull(remarks,'')remarks "+
					"from etm_device_history edh "+
					"inner join device d on d.device_id=edh.etm_number  "+
					"inner join org_chart oc on edh.depot_id=oc.org_chart_id "+
					"left join etm_issue ei on edh.etm_issue =ei.id "+
					"where etm_received_date is  null and etm_resolved_by_fme is null and etm_pickup_by_verifone is not null and "+
					"etm_created_date between '"+date1+"' and '"+date2+"' "+depots+" order by org_name ";
			
			Query query = session.createSQLQuery(sql)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("device_serial_number", Hibernate.STRING)
					.addScalar("issue_name", Hibernate.STRING)
					.addScalar("etm_created_date", Hibernate.STRING)
					.addScalar("etm_pickup_by_fme", Hibernate.STRING)
					.addScalar("etm_pickup_by_verifone", Hibernate.STRING)
					.addScalar("etm_resolved_by_verifone", Hibernate.STRING)
					.addScalar("etm_received_by_fme", Hibernate.STRING)
					//.addScalar("etm_received_date", Hibernate.STRING)
					.addScalar("resolved_reason", Hibernate.STRING)
					.addScalar("remarks", Hibernate.STRING);
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
				
				ja.add(list.get("issue_name").toString());
				ja.add(list.get("etm_created_date").toString());
				ja.add(list.get("etm_pickup_by_fme").toString());
				ja.add(list.get("etm_pickup_by_verifone").toString());
				ja.add(list.get("etm_resolved_by_verifone").toString());
				ja.add(list.get("etm_received_by_fme").toString());
				//ja.add(list.get("etm_received_date").toString());
				ja.add(list.get("resolved_reason").toString());
				ja.add(list.get("remarks").toString());


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
