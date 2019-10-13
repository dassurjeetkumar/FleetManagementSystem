package com.trimax.its.customer.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.EtmDeviceHistoryDao;

public class CustomerFeedBackDao {
	
	public int getTotalRecords(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {
		
		System.out.println("Enter into totalRecord()");
		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql = "SELECT customer_feedback_id,route_number,vehicle_number,depot_name," +
			 		"travel_datetime,mobileno,email_id,floor_clean,seats_clean,windows_clean,doors_clean,lighting_inside,"+
						"seats_damage,AC_working,crew_behavior,crew_helpful,crew_response,conductor_rating,	driver_rating,trans_date,"+
						"complaint_ref_no,	BMTC_rating,complaint_raised,subcategory_id,caller_name,caller_remarks" +
						"from customer_feedback where complaint_raised='No' ";
//			 if(orgtTypeId!=0 && orgtTypeId==1){
//					ordchartcompare = " where complaint_raised='Yes'  ";
//				}else if(orgtTypeId!=0 && orgtTypeId==2){
//					ordchartcompare = " where etm_received_date is null and division_id='"+orgtTypeId+"'  ";
//				}
//				else if(orgtTypeId!=0 && orgtTypeId==3){
//					ordchartcompare = " where etm_received_date is null and depot_id='"+orgchartid+"'  ";
//				}
//			//	sql += ordchartcompare +" AND employee.status='Active'";	
//				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
//					if(orgtTypeId!=0 && orgtTypeId==1){
//						sql += " where route_number like '%" + search_term + "%'";	
//					}else{
					sql += " and route_number like '%" + search_term + "%'";
//					}
					
					sql += " OR vehicle_number like '%"+search_term+"%')";
				}
				
				
				
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("customer_feedback_id", Hibernate.STRING)
						.addScalar("route_number", Hibernate.STRING)
						.addScalar("vehicle_number", Hibernate.STRING)
						.addScalar("depot_name", Hibernate.STRING)
						.addScalar("travel_datetime", Hibernate.STRING)
						.addScalar("mobileno", Hibernate.STRING)
						.addScalar("email_id", Hibernate.STRING)
						.addScalar("floor_clean", Hibernate.STRING)
						.addScalar("seats_clean", Hibernate.STRING)
				       .addScalar("windows_clean", Hibernate.STRING)
				       
				       .addScalar("doors_clean", Hibernate.STRING)
						.addScalar("lighting_inside", Hibernate.STRING)
						.addScalar("seats_damage", Hibernate.STRING)
						.addScalar("AC_working", Hibernate.STRING)
						.addScalar("crew_behavior", Hibernate.STRING)
				       .addScalar("crew_helpful", Hibernate.STRING)
				       
				        .addScalar("crew_response", Hibernate.STRING)
						.addScalar("conductor_rating", Hibernate.STRING)
						.addScalar("trans_date", Hibernate.STRING)
						.addScalar("complaint_ref_no", Hibernate.STRING)
						.addScalar("BMTC_rating", Hibernate.STRING)
				       .addScalar("complaint_raised", Hibernate.STRING)
				       .addScalar("subcategory_id", Hibernate.STRING)
						.addScalar("caller_name", Hibernate.STRING)
						.addScalar("caller_remarks", Hibernate.STRING) 
						.addScalar("driver_rating", Hibernate.STRING) ;

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getData1(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {

		System.out.println("Hii in Vehicle Doa getData1()");
		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = "SELECT customer_feedback_id,route_number,vehicle_number,depot_name," +
			 		"travel_datetime,mobileno,email_id,floor_clean,seats_clean,windows_clean,doors_clean,lighting_inside,"+
						"seats_damage,AC_working,crew_behavior,crew_helpful,crew_response,conductor_rating,	driver_rating,trans_date,"+
						"complaint_ref_no,	BMTC_rating,complaint_raised,subcategory_id,caller_name,caller_remarks" +
						" from customer_feedback where complaint_raised='No' ";
//			if(orgtTypeId!=0 && orgtTypeId==1){
//				ordchartcompare = " where etm_received_date is null  ";
//			}else if(orgtTypeId!=0 && orgtTypeId==2){
//				ordchartcompare = " where etm_received_date is null and division_id='"+orgtTypeId+"'  ";
//			}
//			else if(orgtTypeId!=0 && orgtTypeId==3){
//				ordchartcompare = " where etm_received_date is null and depot_id='"+orgchartid+"'  ";
//			}
//		//	sql += ordchartcompare +" AND employee.status='Active'";	
//			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
//				if(orgtTypeId!=0 && orgtTypeId==1){
//					sql += " where device_serial_number like '%" + search_term + "%'";	
//				}else{
				sql += " and route_number like '%" + search_term + "%'";
//				}
				
				sql += " OR vehicle_number like '%"+search_term+"%')";
			}
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("customer_feedback_id", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING)
					.addScalar("vehicle_number", Hibernate.STRING)
					.addScalar("depot_name", Hibernate.STRING)
					.addScalar("travel_datetime", Hibernate.STRING)
					.addScalar("mobileno", Hibernate.STRING)
					.addScalar("email_id", Hibernate.STRING)
					.addScalar("floor_clean", Hibernate.STRING)
					.addScalar("seats_clean", Hibernate.STRING)
			       .addScalar("windows_clean", Hibernate.STRING)
			       
			       .addScalar("doors_clean", Hibernate.STRING)
					.addScalar("lighting_inside", Hibernate.STRING)
					.addScalar("seats_damage", Hibernate.STRING)
					.addScalar("AC_working", Hibernate.STRING)
					.addScalar("crew_behavior", Hibernate.STRING)
			       .addScalar("crew_helpful", Hibernate.STRING)
			       
			        .addScalar("crew_response", Hibernate.STRING)
					.addScalar("conductor_rating", Hibernate.STRING)
					.addScalar("trans_date", Hibernate.STRING)
					.addScalar("complaint_ref_no", Hibernate.STRING)
					.addScalar("BMTC_rating", Hibernate.STRING)
			       .addScalar("complaint_raised", Hibernate.STRING)
			       .addScalar("subcategory_id", Hibernate.STRING)
					.addScalar("caller_name", Hibernate.STRING)
					.addScalar("caller_remarks", Hibernate.STRING) 
					.addScalar("driver_rating", Hibernate.STRING) ;


			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			
//			List<LogsheetCancelType> list = criteria.list();
			JSONArray array = new JSONArray();
		//	System.out.println("List size----->" + list.size() + "\t"
			//		+ request.getParameter("iDisplayStart"));
			System.out.println("Size list==="+aliasToValueMapList.size());
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("customer_feedback_id")+"' value='"+rs.get("customer_feedback_id")+"'/>");
				ja.add(rs.get("customer_feedback_id").toString());
				ja.add(rs.get("route_number").toString());
				ja.add(rs.get("vehicle_number").toString());
				ja.add(rs.get("depot_name").toString());
				ja.add(rs.get("travel_datetime").toString());
				ja.add(rs.get("mobileno").toString());
				ja.add(rs.get("email_id").toString());
				ja.add(rs.get("floor_clean").toString());
				ja.add(rs.get("seats_clean").toString());
				ja.add(rs.get("windows_clean").toString());
				
				ja.add(rs.get("doors_clean").toString());
				ja.add(rs.get("lighting_inside").toString());
				ja.add(rs.get("seats_damage").toString());
				ja.add(rs.get("AC_working").toString());
				ja.add(rs.get("crew_behavior").toString());
				ja.add(rs.get("crew_helpful").toString());
				ja.add(rs.get("crew_response").toString());
				ja.add(rs.get("conductor_rating").toString());
				ja.add(rs.get("driver_rating").toString());
				ja.add(rs.get("trans_date").toString());
				ja.add(rs.get("complaint_ref_no").toString());
				
				ja.add(rs.get("BMTC_rating").toString());
				ja.add(rs.get("complaint_raised").toString());
				ja.add(rs.get("subcategory_id").toString());
				ja.add(rs.get("caller_name").toString());
				ja.add(rs.get("caller_remarks").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
          System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	
	
	
	
	public int getTotalRecordsForComplaint(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {
		
		System.out.println("Enter into totalRecord()");
		Common common = new Common();
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String ordchartcompare="";
		try{
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
		
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 String sql = "SELECT customer_feedback_id,route_number,vehicle_number,depot_name," +
			 		"travel_datetime,mobileno,email_id,floor_clean,seats_clean,windows_clean,doors_clean,lighting_inside,"+
						"seats_damage,AC_working,crew_behavior,crew_helpful,crew_response,conductor_rating,	driver_rating,trans_date,"+
						"complaint_ref_no,	BMTC_rating,complaint_raised,subcategory_id,caller_name,caller_remarks" +
						"from customer_feedback where complaint_raised='Yes' ";
//			 if(orgtTypeId!=0 && orgtTypeId==1){
//					ordchartcompare = " where complaint_raised='Yes'  ";
//				}else if(orgtTypeId!=0 && orgtTypeId==2){
//					ordchartcompare = " where etm_received_date is null and division_id='"+orgtTypeId+"'  ";
//				}
//				else if(orgtTypeId!=0 && orgtTypeId==3){
//					ordchartcompare = " where etm_received_date is null and depot_id='"+orgchartid+"'  ";
//				}
//			//	sql += ordchartcompare +" AND employee.status='Active'";	
//				sql += ordchartcompare;	

				
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
			
				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
//					if(orgtTypeId!=0 && orgtTypeId==1){
//						sql += " where route_number like '%" + search_term + "%'";	
//					}else{
					sql += " and route_number like '%" + search_term + "%'";
//					}
					
					sql += " OR vehicle_number like '%"+search_term+"%')";
				}
				
				
				
				
				
				if (!col.equals("")) {
					if (dir.equals("asc")) {
						sql += " order by " + col + " asc";
					} else {
						sql += " order by " + col + " desc";
					}
				}
				
				Query query = session.createSQLQuery(sql)
						.addScalar("customer_feedback_id", Hibernate.STRING)
						.addScalar("route_number", Hibernate.STRING)
						.addScalar("vehicle_number", Hibernate.STRING)
						.addScalar("depot_name", Hibernate.STRING)
						.addScalar("travel_datetime", Hibernate.STRING)
						.addScalar("mobileno", Hibernate.STRING)
						.addScalar("email_id", Hibernate.STRING)
						.addScalar("floor_clean", Hibernate.STRING)
						.addScalar("seats_clean", Hibernate.STRING)
				       .addScalar("windows_clean", Hibernate.STRING)
				       
				       .addScalar("doors_clean", Hibernate.STRING)
						.addScalar("lighting_inside", Hibernate.STRING)
						.addScalar("seats_damage", Hibernate.STRING)
						.addScalar("AC_working", Hibernate.STRING)
						.addScalar("crew_behavior", Hibernate.STRING)
				       .addScalar("crew_helpful", Hibernate.STRING)
				       
				        .addScalar("crew_response", Hibernate.STRING)
						.addScalar("conductor_rating", Hibernate.STRING)
						.addScalar("trans_date", Hibernate.STRING)
						.addScalar("complaint_ref_no", Hibernate.STRING)
						.addScalar("BMTC_rating", Hibernate.STRING)
				       .addScalar("complaint_raised", Hibernate.STRING)
				       .addScalar("subcategory_id", Hibernate.STRING)
						.addScalar("caller_name", Hibernate.STRING)
						.addScalar("caller_remarks", Hibernate.STRING) 
						.addScalar("driver_rating", Hibernate.STRING) ;

				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				cnt=aliasToValueMapList.size();
		} catch (Exception e) {

		}
		
	
		System.out.println("List size in total"+cnt);
		
		if(session!=null && session.isOpen()){
			session.close();
		}
		
		return cnt;
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForComplaint(int total, HttpServletRequest request,
			String col, String dir,String viewdeletedrecord) {

		System.out.println("Hii in Vehicle Doa getData1()");
		JSONObject result = new JSONObject();
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 String ordchartcompare="";
		Session session =null;
		
		try {
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			int orgtTypeId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString());
			
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = "SELECT customer_feedback_id,route_number,vehicle_number,depot_name," +
			 		"travel_datetime,mobileno,email_id,floor_clean,seats_clean,windows_clean,doors_clean,lighting_inside,"+
						"seats_damage,AC_working,crew_behavior,crew_helpful,crew_response,conductor_rating,	driver_rating,trans_date,"+
						"complaint_ref_no,	BMTC_rating,complaint_raised,subcategory_id,caller_name,caller_remarks" +
						" from customer_feedback where complaint_raised='Yes' ";
//			if(orgtTypeId!=0 && orgtTypeId==1){
//				ordchartcompare = " where etm_received_date is null  ";
//			}else if(orgtTypeId!=0 && orgtTypeId==2){
//				ordchartcompare = " where etm_received_date is null and division_id='"+orgtTypeId+"'  ";
//			}
//			else if(orgtTypeId!=0 && orgtTypeId==3){
//				ordchartcompare = " where etm_received_date is null and depot_id='"+orgchartid+"'  ";
//			}
//		//	sql += ordchartcompare +" AND employee.status='Active'";	
//			sql += ordchartcompare;	

			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
//				if(orgtTypeId!=0 && orgtTypeId==1){
//					sql += " where device_serial_number like '%" + search_term + "%'";	
//				}else{
				sql += " and route_number like '%" + search_term + "%'";
//				}
				
				sql += " OR vehicle_number like '%"+search_term+"%')";
			}
			
			
			
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			
//			criteria.setFirstResult(Integer.parseInt(request
//					.getParameter("iDisplayStart")));
//			criteria.setMaxResults(Integer.parseInt(request
//					.getParameter("iDisplayLength")));
			
			Query query = session.createSQLQuery(sql)
					.addScalar("customer_feedback_id", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING)
					.addScalar("vehicle_number", Hibernate.STRING)
					.addScalar("depot_name", Hibernate.STRING)
					.addScalar("travel_datetime", Hibernate.STRING)
					.addScalar("mobileno", Hibernate.STRING)
					.addScalar("email_id", Hibernate.STRING)
					.addScalar("floor_clean", Hibernate.STRING)
					.addScalar("seats_clean", Hibernate.STRING)
			       .addScalar("windows_clean", Hibernate.STRING)
			       
			       .addScalar("doors_clean", Hibernate.STRING)
					.addScalar("lighting_inside", Hibernate.STRING)
					.addScalar("seats_damage", Hibernate.STRING)
					.addScalar("AC_working", Hibernate.STRING)
					.addScalar("crew_behavior", Hibernate.STRING)
			       .addScalar("crew_helpful", Hibernate.STRING)
			       
			        .addScalar("crew_response", Hibernate.STRING)
					.addScalar("conductor_rating", Hibernate.STRING)
					.addScalar("trans_date", Hibernate.STRING)
					.addScalar("complaint_ref_no", Hibernate.STRING)
					.addScalar("BMTC_rating", Hibernate.STRING)
			       .addScalar("complaint_raised", Hibernate.STRING)
			       .addScalar("subcategory_id", Hibernate.STRING)
					.addScalar("caller_name", Hibernate.STRING)
					.addScalar("caller_remarks", Hibernate.STRING) 
					.addScalar("driver_rating", Hibernate.STRING) ;


			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			
//			List<LogsheetCancelType> list = criteria.list();
			JSONArray array = new JSONArray();
		//	System.out.println("List size----->" + list.size() + "\t"
			//		+ request.getParameter("iDisplayStart"));
			System.out.println("Size list==="+aliasToValueMapList.size());
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+rs.get("customer_feedback_id")+"' value='"+rs.get("customer_feedback_id")+"'/>");
				ja.add(rs.get("customer_feedback_id").toString());
				ja.add(rs.get("route_number").toString());
				ja.add(rs.get("vehicle_number").toString());
				ja.add(rs.get("depot_name").toString());
				ja.add(rs.get("travel_datetime").toString());
				ja.add(rs.get("mobileno").toString());
				ja.add(rs.get("email_id").toString());
				ja.add(rs.get("floor_clean").toString());
				ja.add(rs.get("seats_clean").toString());
				ja.add(rs.get("windows_clean").toString());
				
				ja.add(rs.get("doors_clean").toString());
				ja.add(rs.get("lighting_inside").toString());
				ja.add(rs.get("seats_damage").toString());
				ja.add(rs.get("AC_working").toString());
				ja.add(rs.get("crew_behavior").toString());
				ja.add(rs.get("crew_helpful").toString());
				ja.add(rs.get("crew_response").toString());
				ja.add(rs.get("conductor_rating").toString());
				ja.add(rs.get("driver_rating").toString());
				ja.add(rs.get("trans_date").toString());
				ja.add(rs.get("complaint_ref_no").toString());
				
				ja.add(rs.get("BMTC_rating").toString());
				ja.add(rs.get("complaint_raised").toString());
				ja.add(rs.get("subcategory_id").toString());
				ja.add(rs.get("caller_name").toString());
				ja.add(rs.get("caller_remarks").toString());
				array.add(ja);
			}			

			totalAfterFilter =total; //getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
          System.out.println("Result====="+result);
//			//System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			//ex.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return result;
	}
	

}
