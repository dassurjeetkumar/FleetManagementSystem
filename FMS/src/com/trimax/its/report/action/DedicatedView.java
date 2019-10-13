package com.trimax.its.report.action;

import java.io.PrintWriter;
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
import com.trimax.its.transport.model.TripType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class DedicatedView {

	private String startdate;
    public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
    private Map<Integer, String> depotlist;
    private Map<Integer, String> charttypelist;
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
		String depot=req.getParameter("depot");
		Common common = new Common();
		
		String date=common.getDateFromPicker(startdate);
		try{
String sql="select from_date,to_date,schedule_number,schedule_code,shift_type_name,brand_type_name,r.route_number,c_cname,trip_no,kms_per_day,`rate/kms`,((kms_per_day) *( `rate/kms`)) rev  from dedicatedrev cr " + 
		"inner join schedule s on s.schedule_id=cr.schedule_no " + 
		"inner join schedule_type sc on sc.schedule_type_id=cr.schedule_type " + 
		"inner join shift_type shi on shi.shift_type_id=cr.shift_type " + 
		"inner join brand_type ttt on ttt.brand_type_id=cr.brand_type_id " + 
		"inner join route r on r.route_id=cr.route_no " + 
		"inner join customer c on c.c_cid=cr.customer_id " + 
		"where cr.depot="+depot+" and '"+date+"' between from_date and to_date  ";
			Query query=session.createSQLQuery(sql);
		/*			.addScalar("TOKEN")
					.addScalar("EMPLOYEE_NAME")
					.addScalar("PF")
					.addScalar("EMPLOYEE_ID");*/
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
			ja.add("<center>"+rs.get("from_date").toString()+"<center>");
			ja.add("<center>"+rs.get("to_date").toString()+"<center>");
				ja.add("<center>"+rs.get("schedule_number").toString()+"<center>");
				ja.add("<center>"+rs.get("schedule_code").toString()+"<center>");
				ja.add("<center>"+rs.get("shift_type_name").toString()+"<center>");
				ja.add("<center>"+rs.get("route_number").toString()+"<center>");
				ja.add("<center>"+rs.get("c_cname").toString()+"<center>");
			
				ja.add("<center>"+rs.get("trip_no").toString()+"<center>");
				ja.add("<center>"+rs.get("kms_per_day").toString()+"<center>");
				ja.add("<center>"+rs.get("rate/kms").toString()+"<center>");
				ja.add("<center>"+rs.get("rev").toString()+"<center>");  

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
