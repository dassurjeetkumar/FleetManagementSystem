package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.TripType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class CCRevenueView {



    private Map<Integer, String> depotlist;
    private Map<Integer, String> charttypelist;
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
	private String startdate;
    private String enddate;
	public Map<Integer, String> getCharttypelist() {
		return charttypelist;
	}
	public void setCharttypelist(Map<Integer, String> charttypelist) {
		this.charttypelist = charttypelist;
	}
	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}
	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}
	public String execute() throws Exception {
		
depotlist = getDepotName();
charttypelist=getChartType();
return "success";
}
	public Map<Integer, String> getDepotName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=3 and deleted_status=0 order by orgchart.org_name");
		try {
		
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	public Map<Integer, String> getChartType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		try {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from TripType triptype  where route_type_id=5 and deleted_status=0  and status='active' ");
		
			
			List<TripType> list = query.list();
			System.out.println("list ----"+list.size());
			for (TripType tt : list) {
				resultMap.put(tt.getId(), tt.getTripTypeName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	public void getdata() {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		JSONObject result=new JSONObject();

		HttpServletRequest req = ServletActionContext.getRequest();
		Common common = new Common();
	
		String date1=common.getDateFromPicker(startdate);
		String depot=req.getParameter("depot");
		String date2=common.getDateFromPicker(enddate);
		try{
String sql="SELECT `org_name`, `vehicle_no`, `logsheet_no`, `d_tocken`, `departure_date`, `arrival_date`, `hrs_per_day`, `tot_kms`, "
		+ "`rent`, `ot_or_it`, `gst`, `tot_rev` FROM `casualcontractrevenue` cc"
		+ " inner join org_chart oc on oc.org_chart_id=cc.depot_no "
		+ " WHERE  date(departure_date) ='"+date1+"' and depot_no='"+depot+"'";
			Query query=session.createSQLQuery(sql);
/*					.addScalar("depot_no",Hibernate.STRING)
					.addScalar("vehicle_no",Hibernate.STRING)
					.addScalar("logsheet_no",Hibernate.STRING)
					.addScalar("d_tocken",Hibernate.STRING)
					.addScalar("departure_date",Hibernate.TIMESTAMP)
					.addScalar("arrival_date",Hibernate.TIMESTAMP)
					.addScalar("hrs_per_day",Hibernate.STRING)
					.addScalar("tot_kms",Hibernate.STRING)
					.addScalar("rent",Hibernate.STRING)
					.addScalar("ot_or_it",Hibernate.STRING)
					.addScalar("gst",Hibernate.STRING)
					.addScalar("tot_rev",Hibernate.STRING);*/
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasMapValueList=query.list();
			System.out.println("alsize----------->"+aliasMapValueList.size());
			JSONArray array=new JSONArray();
		int j=1;
			for(int i=0;i<aliasMapValueList.size();i++){
				Map<String, Object> rs=aliasMapValueList.get(i);
				JSONArray ja=new JSONArray();

			
				ja.add("<center>"+j+"<center>");
			j++;
			ja.add("<center>"+rs.get("org_name").toString()+"<center>");
			ja.add("<center>"+rs.get("vehicle_no").toString()+"<center>");
			
				ja.add("<center>"+rs.get("logsheet_no").toString()+"<center>");
				ja.add("<center>"+rs.get("d_tocken").toString()+"<center>");
			ja.add("<center>"+rs.get("departure_date").toString()+"<center>");
				ja.add("<center>"+rs.get("arrival_date").toString()+"<center>");
			
				ja.add("<center>"+rs.get("hrs_per_day").toString()+"<center>");
				ja.add("<center>"+rs.get("tot_kms").toString()+"<center>");
			
				
				ja.add("<center>"+rs.get("rent").toString()+"<center>");
				
				ja.add("<center>"+rs.get("ot_or_it").toString()+"<center>");
				ja.add("<center>"+rs.get("gst").toString()+"<center>");
				
				ja.add("<center>"+rs.get("tot_rev").toString()+"<center>");
	

			    array.add(ja);
				
				
			}
		
				
			
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
	
		
	
	}
	
	
	



}
