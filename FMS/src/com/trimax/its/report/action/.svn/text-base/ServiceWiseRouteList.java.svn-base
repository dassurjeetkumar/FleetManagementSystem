package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.trimax.its.route.model.Route;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ServiceWiseRouteList {

	 public Map<Integer, String> servicetypemap;
		private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;

public Map<Integer, String> getServicetypemap() {
			return servicetypemap;
		}
		public void setServicetypemap(Map<Integer, String> servicetypemap) {
			this.servicetypemap = servicetypemap;
		}
public String execute() {
	/*VtsDataDAO vvt = VtsDataDAO.getInstance();
	divisionlist = vvt.getDivisionName();*/
	this.setServicetypemap(getServiceType1());
	return "success";
}
public Map<Integer, String> getServiceType1() {
	Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	//int userId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("org_chart_id").toString());
	
	try {
		Query query = session
				.createSQLQuery("select brand_type_id,brand_type_name from brand_type where status='ACTIVE' and deleted_status='0'").addScalar("brand_type_id", Hibernate.INTEGER).addScalar("brand_type_name", Hibernate.STRING);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		resultMap.put(0, "Select");
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			resultMap.put(Integer.parseInt(rs.get("brand_type_id").toString()),rs.get("brand_type_name").toString());
		}				
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		  if(session!=null){ session.close(); }
		 
	}
	return resultMap;
}
public void getdata(){
	JSONObject result = new JSONObject();
	Session session = null;
	try {
	HttpServletRequest req = ServletActionContext.getRequest();
	
	String serviceid=req.getParameter("serviceid");
	
	
	
	session = HibernateUtil.getSession("hibernate.cfg.xml");
	
	
/*
	String sql="select route_number,count(schedule_number_id)no_sch,origin,destination,distance,route_direction,fare_stage,no_trips,brand_type_name,"+
			"No_stages from (select *,count(*)no_trips from (select r.route_number,ff.schedule_number_id,ff.schedule_number_name,service_type_name,"+
			"r.route_direction,bs2.bus_stop_name origin,sd.distance,bs3.bus_stop_name destination,brand_type_name,"+
			"(select count(*) from route_point rp1 where rp1.route_id=r.route_id and fare_stage='Y' and deleted_status=0 ) fare_stage,"+
			"(select count(*) from route_point rp1 where rp1.route_id=r.route_id  and deleted_status=0 ) No_stages "+
			"from schedule s inner join form_four ff on s.schedule_id=ff.schedule_number_id "+
			"inner join schedule_details sd on sd.form_four_id=ff.form_four_id inner join service_type  st on st.service_type_id=schedule_service_type "+
			"inner join org_chart og on og.org_chart_id=s.depot_id  inner join route r on r.route_id=sd.route_number_id " +
			"left join brand_type bt on s.brand_type_id=bt.brand_type_id "+
			"inner join bus_stop bs2 on r.start_point_id=bs2.bus_stop_id inner join bus_stop bs3 on r.end_point_id=bs3.bus_stop_id "+
			"where  sd.route_number_id=r.route_id and sd.deleted_status=0 and ff.current_status='ACTIVE'   and s.status='NEW' "+
			"and s.current_status='CASE WORKER' and sd.deleted_status=0 and s.deleted_status=0 and bt.brand_type_id='"+serviceid+"' and sd.is_dread_trip=0 "+
			"group by sd.form_four_id,sd.shift_type_id,sd.trip_number) A group by schedule_number_name,route_direction order by schedule_number_name "+
			")b group by route_number,route_direction";*/
	
	String sql="select route_number,count(schedule_number_name)no_sch,origin,destination,distance,route_direction,fare_stage,sum(no_trips),brand_type_name,"+
			"No_stages from ( "+
			"select *,count(*)no_trips from ( "+
			"select r.route_number,sd.form_four_id,ff.schedule_number_name,service_type_name,"+
			"r.route_direction,bs2.bus_stop_name origin,sd.distance,bs3.bus_stop_name destination,brand_type_name,"+
			"(select count(*) from route_point rp1 where rp1.route_id=r.route_id and fare_stage='Y' and deleted_status=0 ) fare_stage,"+
			"(select count(*) from route_point rp1 where rp1.route_id=r.route_id  and deleted_status=0 ) No_stages from schedule s "+
			"inner join form_four ff on s.schedule_id=ff.schedule_number_id inner join schedule_details sd on sd.form_four_id=ff.form_four_id "+
			"inner join service_type  st on st.service_type_id=schedule_service_type inner join org_chart og on og.org_chart_id=s.depot_id  "+
			"inner join route r on r.route_id=sd.route_number_id left join brand_type bt on s.brand_type_id=bt.brand_type_id "+
			"inner join bus_stop bs2 on r.start_point_id=bs2.bus_stop_id inner join bus_stop bs3 on r.end_point_id=bs3.bus_stop_id "+
			"where  sd.route_number_id=r.route_id and sd.deleted_status=0 and ff.current_status='ACTIVE'   and s.status='NEW' and ff.deleted_status=0 and ff.effective_end_date IS NULL "+
			"and s.current_status='CASE WORKER' and sd.deleted_status=0 and s.deleted_status=0 and bt.brand_type_id='"+serviceid+"' "+
			"and sd.is_dread_trip=0 group by sd.form_four_id,sd.shift_type_id,sd.trip_number) a group by a.form_four_id,a.route_direction,a.route_number "+
			")b group by b.route_number,b.route_direction";
	
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();	
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
		
		ja.add(list.get("route_number").toString());
			ja.add(list.get("no_sch").toString());
			ja.add(list.get("sum(no_trips)").toString());
			//ja.add(list.get("diff").toString());
		
			ja.add(list.get("origin").toString());
		
			ja.add(list.get("destination").toString());
			ja.add(list.get("route_direction").toString());
			ja.add(list.get("distance").toString());
			ja.add(list.get("fare_stage").toString());
			ja.add(list.get("No_stages").toString());
			
			ja.add(list.get("brand_type_name").toString());
			array.add(ja);
				
				 }
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
	}catch (Exception e) {
	e.printStackTrace();
	}finally{
		session.close();
	}

}}
