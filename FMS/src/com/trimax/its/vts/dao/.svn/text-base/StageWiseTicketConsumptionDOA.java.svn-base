/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trimax.its.vts.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;


/**
 *
 * @author admin
 */
public class StageWiseTicketConsumptionDOA {

	
	
	
	@SuppressWarnings("unchecked")
	public List<Route> getRouteListDropDown1(String routeno){

		
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<Route> list1 = new ArrayList<Route>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			String queryForRouteNos = "SELECT route_id,route_number,route_direction FROM `route` WHERE `status` = 'active' AND route_number like'%"+routeno+"%' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00' order by route_number";
			Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("route_number",Hibernate.STRING)
					.addScalar("route_id",Hibernate.INTEGER).addScalar("route_direction",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				Route route1 = new Route();
				route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setValue(map.get("route_number"));
				//setValue
//				route1.setRoute_name(String.valueOf(map.get("route_id")) +"-"+String.valueOf(map.get("start_point_id"))+"-"+String.valueOf(map.get("end_point_id")));
				list1.add(route1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list1;
		
	}
	
	
	


	
	
	
	public int getRouteID(String routeno) {
		
		Session session = null;
		int id=0;
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String routename[]=routeno.split(":");
			String route=routename[0];
			String direction=routename[1];
			System.out.println("name---direction"+route+"-----"+direction);
			
			Query qry=session.createSQLQuery("SELECT route_id FROM `route`  WHERE `status` = 'active' AND route_number='"+route+"' AND route_direction='"+direction+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'");
			id=(Integer)qry.uniqueResult();
			System.out.println("result--"+id);
					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		     if(session!=null){
		    	 session.close();
		     }
		}
		return id;
	}
	
	
		
	
	public List getScheduleNameFormFourId(int routeId) {
		List list = new ArrayList();
		try {
		String qry = "select schedule_number,schedule_number_name as schedule_number_name, f.form_four_id as form_four_id from schedule s inner join form_four f on s.schedule_id=f.schedule_number_id " +
				" where f.route_id='"+routeId+"' and s.status ='new' and s.current_status='Case Worker' and s.deleted_status=0 group by schedule_number_name";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("form_four_id").toString() + "#"
							+ rs.get("schedule_number_name").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}
	
	
	@SuppressWarnings({ "unchecked", "finally" })
	public List getScheduleNameID(int depotId) {
		List list = new ArrayList();
		try {
		String qry = "SELECT form_four_id,schedule_number_name FROM `form_four` ff inner join schedule sd on ff.schedule_number_id=sd.schedule_id " +
				" WHERE ff.`current_status` = 'ACTIVE' AND ff.`deleted_status` = '0' and depot_id='"+depotId+"' ";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("form_four_id").toString() + "#"
							+ rs.get("schedule_number_name").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}
	
	
	
	
	
}
