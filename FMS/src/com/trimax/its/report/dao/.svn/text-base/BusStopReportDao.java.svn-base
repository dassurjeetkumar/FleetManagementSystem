package com.trimax.its.report.dao;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;

public class BusStopReportDao {
	
	
	public int getTotalRecords(String status){
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		if(status.equalsIgnoreCase("All"))
		{
			 query = session.createSQLQuery("select count(*) as count from bus_stop").addScalar("count", Hibernate.INTEGER);
		
		}else{
			 query = session.createSQLQuery("select count(*) as count from bus_stop where status = '"+status+"'").addScalar("count", Hibernate.INTEGER);
		}
		//System.out.println("query------//////////----------"+query);
		
		List<Integer> list = query.list();
		int cnt = list.get(0);
		return cnt;
	}
	public String getname(String busStopNameKannada){
		StringBuffer out = new StringBuffer();
		String name;
	    for(int i=0; i<busStopNameKannada.length(); i++)
	    {
	        char c = busStopNameKannada.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>')
	        {
	           out.append("&#"+(int)c+";");
	        }
	        else
	        {
	            out.append(c);
	        }
	    }
	   
	    name=out.toString();
	    return name;
	}
	
	public ArrayList getbusstoplist(String status){
		ArrayList<ArrayList<String>> showbusstoplist=new ArrayList<ArrayList<String>>();
		String str="";
		try{
		String sql="select bus_stop_id, IFNULL(bus_stop_name,'NA') bus_stop_Name, IFNULL(bus_stop_code,'NA')bus_stop_code , IFNULL(bus_stop_name_kannada,'NA')bus_stop_name_kannada,IFNULL(bus_stop_code_kannada,'NA')bus_stop_code_kannada,IFNULL(status,'NA')status,IFNULL(landmark,'NA')landmark,is_sheltered," +
					" IFNULL(locality,'NA')locality,IFNULL(alias1,'NA')alias1,IFNULL(alias2,'NA')alias2,IFNULL(alias3,'NA')alias3,IFNULL(alias4,'NA')alias4,IFNULL(alias5,'NA')alias5,IFNULL(alias6,'NA')alias6,IFNULL(toll_zone,'NA')toll_zone,IFNULL(point_type_id,'NA')point_type_id," +
					" IFNULL(city_limit,'NA')city_limit,IFNULL(ward_number,'NA')ward_number,IFNULL(area_population,'NA')area_population,IFNULL(stop_size,'NA')stop_size,IFNULL(fare_stage,'NA')fare_stage,IFNULL(description,'NA')description,IFNULL(bmtc_status,'NA')bmtc_status,IFNULL(k_alias_1,'NA')k_alias_1,IFNULL(k_alias_2,'NA')k_alias_2" +
				    " from bus_stop where status='"+status+"'";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		for(int i=0;i<aliasToValueMapList.size();i++){
			ArrayList<String> a=new ArrayList<String>();
			Map<String, Object> rs = aliasToValueMapList.get(i);
			if(rs !=null){
				a.add(rs.get("bus_stop_Name").toString());
				a.add(getname(rs.get("bus_stop_name_kannada").toString()));
				a.add(rs.get("bus_stop_code").toString());
	
				a.add(getname(rs.get("bus_stop_code_kannada").toString()));
				a.add(rs.get("locality").toString());
				a.add(rs.get("landmark").toString());
				a.add(rs.get("is_sheltered").toString());
				a.add(rs.get("alias1").toString());
				a.add(rs.get("alias2").toString());
				a.add(rs.get("alias3").toString());
				a.add(rs.get("alias4").toString());
				a.add(rs.get("alias5").toString());
				a.add(rs.get("alias6").toString());
				a.add(getname(rs.get("k_alias_1").toString()));
				a.add(getname(rs.get("k_alias_2").toString()));
				a.add(rs.get("area_population").toString());
				a.add(rs.get("stop_size").toString());
				a.add(rs.get("fare_stage").toString());
				a.add(rs.get("description").toString());
				a.add(rs.get("status").toString());
				showbusstoplist.add(a);
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return showbusstoplist;
	}
	
public JSONObject getbusstoplist(int total, String status, HttpServletRequest request,String col, String dir){
	
	int totalAfterFilter = total;
	JSONObject result = new JSONObject();
	try{
		String sql="";
	//String sql="select bus_stop_id,IFNULL(bus_stop_name,'NA') bus_stop_Name,IFNULL(bus_stop_code,'NA') bus_stop_code ,IFNULL(bus_stop_name_kannada,'NA') bus_stop_name_kannada from bus_stop where status='"+status+"'";
	if(status.equalsIgnoreCase("All"))
	{
		 sql="select bus_stop_id, IFNULL(bus_stop_name,'NA') bus_stop_Name, IFNULL(bus_stop_code,'NA')bus_stop_code , IFNULL(bus_stop_name_kannada,'NA')bus_stop_name_kannada,IFNULL(bus_stop_code_kannada,'NA')bus_stop_code_kannada,IFNULL(status,'NA')status,IFNULL(landmark,'NA')landmark,is_sheltered," +
					" IFNULL(locality,'NA')locality,IFNULL(alias1,'NA')alias1,IFNULL(alias2,'NA')alias2,IFNULL(alias3,'NA')alias3,IFNULL(alias4,'NA')alias4,IFNULL(alias5,'NA')alias5,IFNULL(alias6,'NA')alias6,IFNULL(toll_zone,'NA')toll_zone,IFNULL(point_type_id,'NA')point_type_id," +
					" IFNULL(city_limit,'NA')city_limit,IFNULL(ward_number,'NA')ward_number,IFNULL(area_population,'NA')area_population,IFNULL(stop_size,'NA')stop_size,IFNULL(fare_stage,'NA')fare_stage,IFNULL(description,'NA')description,IFNULL(bmtc_status,'NA')bmtc_status,IFNULL(k_alias_1,'NA')k_alias_1,IFNULL(k_alias_2,'NA')k_alias_2" +
				    " from bus_stop";
	}else{
		 sql="select bus_stop_id, IFNULL(bus_stop_name,'NA') bus_stop_Name, IFNULL(bus_stop_code,'NA')bus_stop_code , IFNULL(bus_stop_name_kannada,'NA')bus_stop_name_kannada,IFNULL(bus_stop_code_kannada,'NA')bus_stop_code_kannada,IFNULL(status,'NA')status,IFNULL(landmark,'NA')landmark,is_sheltered," +
				" IFNULL(locality,'NA')locality,IFNULL(alias1,'NA')alias1,IFNULL(alias2,'NA')alias2,IFNULL(alias3,'NA')alias3,IFNULL(alias4,'NA')alias4,IFNULL(alias5,'NA')alias5,IFNULL(alias6,'NA')alias6,IFNULL(toll_zone,'NA')toll_zone,IFNULL(point_type_id,'NA')point_type_id," +
				" IFNULL(city_limit,'NA')city_limit,IFNULL(ward_number,'NA')ward_number,IFNULL(area_population,'NA')area_population,IFNULL(stop_size,'NA')stop_size,IFNULL(fare_stage,'NA')fare_stage,IFNULL(description,'NA')description,IFNULL(bmtc_status,'NA')bmtc_status,IFNULL(k_alias_1,'NA')k_alias_1,IFNULL(k_alias_2,'NA')k_alias_2" +
			    " from bus_stop where status='"+status+"'";
	}
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*if(!col.equals("")){
		if(dir.equals("asc")){
		    //criteria.addOrder(Order.asc(col));
			sql += " order by bus_stop_Name";
		}else{
			//criteria.addOrder(Order.desc(col));	
			sql += " order by bus_stop_Name";
		}
	}*/
	if(!request.getParameter("sSearch").equals("")){
		String search_term = request.getParameter("sSearch");
		sql += " and bus_stop_Name like '%"+search_term+"%'";
		
	}
	
		sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");	
//	System.out.println("sql----------"+sql);
	Query query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	JSONArray array = new JSONArray();
	
	for(int i=0;i<aliasToValueMapList.size();i++){
		Map<String, Object> rs = aliasToValueMapList.get(i);
		JSONArray ja = new JSONArray();
		ja.add(rs.get("bus_stop_Name").toString());
		ja.add(getname(rs.get("bus_stop_name_kannada").toString()));
		//ja.add("test");
		ja.add(rs.get("bus_stop_code").toString());
		String bustopcodekannda=getname(rs.get("bus_stop_code_kannada").toString());
		System.out.println("bustopcodekannda----///////--"+bustopcodekannda);
		ja.add(getname(rs.get("bus_stop_code_kannada").toString()));
		
		ja.add(rs.get("locality").toString());
		ja.add(rs.get("landmark").toString());
		ja.add(rs.get("is_sheltered").toString());
		ja.add(rs.get("alias1").toString());
		ja.add(rs.get("alias2").toString());
		ja.add(rs.get("alias3").toString());
		ja.add(rs.get("alias4").toString());
		ja.add(rs.get("alias5").toString());
		ja.add(rs.get("alias6").toString());
		ja.add(getname(rs.get("k_alias_1").toString()));
		ja.add(getname(rs.get("k_alias_2").toString()));
		ja.add(rs.get("area_population").toString());
		ja.add(rs.get("stop_size").toString());
		ja.add(rs.get("fare_stage").toString());
		ja.add(rs.get("description").toString());
		ja.add(rs.get("status").toString());
		array.add(ja);
		System.out.println("Array----->"+array);
	}
	
	totalAfterFilter = getTotalRecords(status);
		result.put("aaData",array);
		result.put("iTotalRecords", total);
		result.put("iTotalDisplayRecords", totalAfterFilter);
	}
	catch(Exception e){
	}
	return result;
	
}
	
}
