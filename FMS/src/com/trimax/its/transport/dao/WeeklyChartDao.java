package com.trimax.its.transport.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xhtmlrenderer.css.parser.property.PrimitivePropertyBuilders.Src;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.transport.model.WeeklyChart;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;

public class WeeklyChartDao {
	@SuppressWarnings("unchecked")
	public int getTotalRecords(int total,HttpServletRequest request,String col, String dir,int scheduleid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int cnt=0;
		try {
			String sql = "";
			sql="select weekly_chart_id ,monday,tuesday,wednesday,thursday,friday,saturday,sunday," +
					"holiday,form_four_id,schedule_number_name,effective_start_date,effective_end_date,efe " 
			+		" from ( select weeklyChart.weekly_chart_id,weeklyChart.monday monday ," +
			"weeklyChart.tuesday tuesday,weeklyChart.wednesday wednesday, "
			+" weeklyChart.thursday thursday,weeklyChart.friday friday,weeklyChart.saturday saturday ," +
			"weeklyChart.sunday sunday,weeklyChart.holiday holiday,form_four.form_four_id," +
			"form_four.schedule_number_name, "
			+" DATE_FORMAT(form_four.effective_start_date,'%d-%m-%Y') AS effective_start_date," +
			"IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%Y'),'NA') AS effective_end_date," +
			"form_four.effective_end_date as efe"
			+" from weeklyChart " 
			+" INNER JOIN form_four ON form_four.form_four_id=weeklyChart.form_four_id where " 
			+"  form_four.schedule_number_id="+scheduleid+"  AND form_four.deleted_status=0 AND weeklyChart.deleted_status=0 and form_four.current_status='ACTIVE' "
			+" union "
			+" select 0 weekly_chart_id ,0 monday,0 tuesday ,0 wednesday,0 thursday,0 friday ,0 saturday,0 sunday,0 holiday," +
			"form_four_id,schedule_number_name,DATE_FORMAT(effective_start_date,'%d-%m-%Y') AS effective_start_date," +
			"IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%Y'),'NA') AS effective_end_date,form_four.effective_end_date as efe "	
			+" from form_four where schedule_number_id="+scheduleid+" AND deleted_status=0 and current_status='ACTIVE') a where efe>=curdate() or efe is null ";
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " where (schedule_number_name like '" + search_term + "%')";

			}
			//sql+= " or efe>=curdate() or effective_end_date is null ";
			sql += " group by form_four_id";
			
//			if(!col.equals(""))
//			 { if(dir.equals("asc"))
//			 { 
//				 sql +=
//					  " order by "+col+" asc";
//				 }else{ 
//					 sql += " order by "+col+" desc";
//					  }
//			 }
				
					
//			sql += " limit " + request.getParameter("iDisplayStart") + ", " +request.getParameter("iDisplayLength");
//			  
			 
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt=aliasToValueMapList.size();
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			
				//session.close();
			
		}
		return cnt;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getList(int total, HttpServletRequest request,String col, String dir,int scheduleid) {		
		int totalAfterFilter = total;
		int count=0;
		JSONObject result = new JSONObject();
		Session session = null;
		Common common = new Common();
		
		String sql = "";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			sql=" select weekly_chart_id ,monday,tuesday,wednesday,thursday,friday,saturday,sunday," +
					"holiday,form_four_id,schedule_number_name,effective_start_date,effective_end_date,efe " 
			+		" from ( select weeklyChart.weekly_chart_id,weeklyChart.monday monday ," +
			"weeklyChart.tuesday tuesday,weeklyChart.wednesday wednesday, "
			+" weeklyChart.thursday thursday,weeklyChart.friday friday,weeklyChart.saturday saturday ," +
			"weeklyChart.sunday sunday,weeklyChart.holiday holiday,form_four.form_four_id," +
			"form_four.schedule_number_name, "
			+" DATE_FORMAT(form_four.effective_start_date,'%d-%m-%Y') AS effective_start_date," +
			"IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%Y'),'NA') AS effective_end_date," +
			"form_four.effective_end_date as efe"
			+" from weeklyChart " 
			+" INNER JOIN form_four ON form_four.form_four_id=weeklyChart.form_four_id where " 
			+"  form_four.schedule_number_id="+scheduleid+"  AND form_four.deleted_status=0 AND weeklyChart.deleted_status=0 and form_four.current_status='ACTIVE' "
			+" union "
			+" select 0 weekly_chart_id ,0 monday,0 tuesday ,0 wednesday,0 thursday,0 friday ,0 saturday,0 sunday,0 holiday," +
			"form_four_id,schedule_number_name,DATE_FORMAT(effective_start_date,'%d-%m-%Y') AS effective_start_date," +
			"IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%Y'),'NA') AS effective_end_date,form_four.effective_end_date as efe "	
			+" from form_four where schedule_number_id="+scheduleid+" AND deleted_status=0 and current_status='ACTIVE') a  where efe>=curdate() or efe is null  ";
		
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " where (schedule_number_name like '" + search_term + "%')";

			}
			//sql+= " or efe>=curdate() or effective_end_date is null ";
			sql += " group by form_four_id";
			
			if(!col.equals(""))
			 { if(dir.equals("asc"))
			 { 
				 sql +=
					  " order by "+col+" asc";
				 }else{ 
					 sql += " order by "+col+" desc";
					  }
			 }
				
					
			sql += " limit " + request.getParameter("iDisplayStart") + ", " +request.getParameter("iDisplayLength");
		
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = null;
			aliasToValueMapList=query.list();
			
			JSONArray array = new JSONArray();
		
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				/*String endDate= rs.get("effective_end_date").toString();
				System.out.println("endDate"+endDate);
				String currentDate=common.getDateInDDMMYY(new Date());
				System.out.println("currentDate"+currentDate);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date date1 = sdf.parse(endDate);
				Date date2=sdf.parse(currentDate);;
				System.out.println("date1.compareTo(date2)"+date1.compareTo(date2));
				if(date1.compareTo(date2)>=0){	*/			
				//if(this.isGreaterThanCurrentDateTime(endDate)||endDate.equalsIgnoreCase("00-00-0000")){
				ja.add(rs.get("form_four_id"));	
		        System.out.println(rs.get("effective_end_date")!=null?rs.get("effective_end_date"):"");
				ja.add(rs.get("schedule_number_name"));
				ja.add(rs.get("effective_start_date")!=null?rs.get("effective_start_date"):"");
				ja.add(rs.get("effective_end_date")!=null?rs.get("effective_end_date"):"");
				String monday=rs.get("monday").toString();
				String tuesday=rs.get("tuesday").toString();
				String wednesday=rs.get("wednesday").toString();
				String thursday=rs.get("thursday").toString();
				String friday=rs.get("friday").toString();
				String saturday=rs.get("saturday").toString();
				String sunday=rs.get("sunday").toString();
				String holiday=rs.get("holiday").toString();
				
				if (monday.equalsIgnoreCase("0")) {
					ja.add("");
				} else if(monday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
				}else{
					ja.add(null);
				}
				
				if (tuesday.equalsIgnoreCase("0")) {
					ja.add("");
				} else if(tuesday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
					
				}else{
					ja.add(null);
				}
				
				if (wednesday.equalsIgnoreCase("0")) {
					ja.add("");
				} else if(wednesday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
				}else{
					ja.add(null);
				}
				
				if (thursday.equalsIgnoreCase("0")) {
					ja.add("");
				}else if(thursday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
				}else{
					ja.add(null);
				}
				
				if (friday.equalsIgnoreCase("0")) {
					ja.add("");
				} else if(friday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
				}else{
					ja.add(null);
				}
				
				if (saturday.equalsIgnoreCase("0")) {
					ja.add("");
				} else if(saturday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
				}else{
					ja.add(null);
				}
				
				if (sunday.equalsIgnoreCase("0")) {
					ja.add("");
				} else if(sunday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
				}else{
					ja.add(null);
				}
				
				//ja.add("holiday");
				if (holiday.equalsIgnoreCase("0")) {
					ja.add("");
				} else if(holiday.equalsIgnoreCase("1")){
					ja.add("&#x2714");
				}else{
					ja.add(null);
				}
			
				array.add(ja);
				//}
			}
			int cnt = getTotalRecords(total,request,col,dir,scheduleid);
			totalAfterFilter = aliasToValueMapList.size();
			if(aliasToValueMapList!=null){
				aliasToValueMapList.clear();
			}
			
			result.put("aaData", array);
			result.put("iTotalRecords", cnt);
			result.put("iTotalDisplayRecords", cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//session.close();
			if (session != null) {
				session.close();
				//session.flush();
			}
			return result;
		}
		
	}
	/*public int getTotalRecords(int total, HttpServletRequest request,
			String col, String dir) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt = 0;
		try {
			String sql = "";

			sql = "select weeklyChart.weekly_chart_id,max(weeklyChart.monday),max(weeklyChart.tuesday),"
					+ "max(weeklyChart.wednesday),max(weeklyChart.thursday), "
					+ "max(weeklyChart.friday),max(weeklyChart.saturday),max(weeklyChart.sunday),f.form_four_id,"
					+ "max(f.form_four_name),max(t.form_four_type_id),"
					+ " t.form_four_type_name ,s.schedule_number,s.schedule_id "
					+ "from weeklyChart weeklyChart "
					+ "inner join form_four f on weeklyChart.form_four_id=f.form_four_id "
					+ "inner join form_four_type t on f.form_four_name=t.form_four_type_id "
					+ "inner join schedule s on f.schedule_number_id=s.schedule_id ";
			
			 * "select weeklyChart.weekly_chart_id,max(weeklyChart.monday),max(weeklyChart.tuesday),max(weeklyChart.wednesday),max(weeklyChart.thursday), "
			 * +
			 * "max(weeklyChart.friday),max(weeklyChart.saturday),max(weeklyChart.sunday),f.form_four_id,"
			 * +
			 * "f.form_four_name,t.form_four_type_name,s.schedule_number,s.schedule_id from weeklyChart weeklyChart "
			 * +
			 * "inner join form_four f on weeklyChart.form_four_id=f.form_four_id "
			 * +
			 * "inner join form_four_type t on f.form_four_name=t.form_four_type_id "
			 * +"inner join schedule s on f.schedule_number_id=s.schedule_id " ;
			 

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (s.schedule_number	like '" + search_term + "%')";

			}

			sql += "group by s.schedule_number";

			
			 * if(!col.equals("")){ if(dir.equals("asc")){ sql +=
			 * " order by "+col+" asc"; }else{ sql += " order by "+col+" desc";
			 * } }
			 
			
			 * sql += " limit " + request.getParameter("iDisplayStart") + ", " +
			 * request.getParameter("iDisplayLength");
			 
			Query query = session.createSQLQuery(sql);
			List<Integer> list = query.list();
			cnt = list.size();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return cnt;
	}*/

	/*
	 * public JSONObject getList(int total, HttpServletRequest request,String
	 * col, String dir) { int totalAfterFilter = total;
	 * 
	 * JSONObject result = new JSONObject(); Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml");
	 * session.beginTransaction(); try { String sql = "";
	 * 
	 * 
	 * sql =
	 * "select weeklyChart.weekly_chart_id,max(weeklyChart.monday)monday ,max(weeklyChart.tuesday)tuesday,"
	 * +
	 * "max(weeklyChart.wednesday)wednesday,max(weeklyChart.thursday)thursday, "
	 * +
	 * "max(weeklyChart.friday)friday,max(weeklyChart.saturday)saturday,max(weeklyChart.sunday)sunday,f.form_four_id,"
	 * + "max(f.form_four_name),max(t.form_four_type_id)," +
	 * " t.form_four_type_name ,s.schedule_number,s.schedule_id " +
	 * "from weeklyChart weeklyChart " +
	 * "inner join form_four f on weeklyChart.form_four_id=f.form_four_id " +
	 * "inner join form_four_type t on f.form_four_name=t.form_four_type_id " +
	 * "inner join schedule s on f.schedule_number_id=s.schedule_id " ; sql =
	 * "select weeklyChart.weekly_chart_id,weeklyChart.monday,weeklyChart.tuesday,weeklyChart.wednesday,"
	 * +
	 * "weeklyChart.thursday,weeklyChart.friday,weeklyChart.saturday,weeklyChart.sunday,f.form_four_id,f.form_four_name,"
	 * + "CONCAT(GROUP_CONCAT(t.form_four_type_name)) form_four_type_name,"
	 * +"s.schedule_number," + "s.schedule_id from weeklyChart weeklyChart " +
	 * "inner join form_four f on weeklyChart.form_four_id=f.form_four_id " +
	 * "inner join form_four_type t on f.form_four_name=t.form_four_type_id " +
	 * "inner join schedule s on f.schedule_number_id=s.schedule_id " +
	 * "group by t.form_four_type_name,s.schedule_number";
	 * 
	 * if (!request.getParameter("sSearch").equals("")) { String search_term =
	 * request.getParameter("sSearch").trim(); // int srch =
	 * Integer.parseInt(search_term);
	 * 
	 * sql += " and (s.schedule_number	like '" + search_term + "%')"; //
	 * System.out.println("sql search"+sql); }
	 * 
	 * sql += "group by s.schedule_number,t.form_four_type_name ";
	 * 
	 * if(!col.equals("")){ if(dir.equals("asc")){ sql +=
	 * " order by "+col+" asc"; }else{ sql += " order by "+col+" desc"; } } sql
	 * += " limit " + request.getParameter("iDisplayStart") + ", " +
	 * request.getParameter("iDisplayLength");
	 * 
	 * Query query = session.createSQLQuery(sql);
	 * 
	 * query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	 * List<Map<String, Object>> aliasToValueMapList = query.list();
	 * System.out.println("hdfdf"+aliasToValueMapList);
	 * 
	 * 
	 * 
	 * 
	 * JSONArray array = new JSONArray(); // String effDate=""; for (int i = 0;
	 * i < aliasToValueMapList.size(); i++) { //
	 * System.out.println("hdfdf"+aliasToValueMapList.get(i)); Map<String,
	 * Object> rs = aliasToValueMapList.get(i);
	 * 
	 * JSONArray ja = new JSONArray();
	 * 
	 * ja.add(
	 * "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' "
	 * + "id='" + rs.get("schedule_id") + "' name='checkid' value='" +
	 * rs.get("schedule_id") + "'/>" +
	 * "<input type='hidden' id='checkidd' value='" + aliasToValueMapList.size()
	 * + "'/>");
	 * 
	 * String query1=
	 * "select weeklyChart.weekly_chart_id,max(weeklyChart.monday)monday ,max(weeklyChart.tuesday)tuesday,"
	 * +
	 * "max(weeklyChart.wednesday)wednesday,max(weeklyChart.thursday)thursday, "
	 * +
	 * "max(weeklyChart.friday)friday,max(weeklyChart.saturday)saturday,max(weeklyChart.sunday)sunday,f.form_four_id,"
	 * + "max(f.form_four_name),max(t.form_four_type_id)," +
	 * " t.form_four_type_name ,s.schedule_number,s.schedule_id " +
	 * "from weeklyChart weeklyChart " +
	 * "inner join form_four f on weeklyChart.form_four_id=f.form_four_id " +
	 * "inner join form_four_type t on f.form_four_name=t.form_four_type_id " +
	 * "inner join schedule s on f.schedule_number_id=s.schedule_id  where schedule_id = "
	 * + rs.get("schedule_id").toString()+
	 * " group by s.schedule_number,t.form_four_type_name";
	 * 
	 * Query query2 = session.createSQLQuery(query1);
	 * 
	 * query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	 * List<Map<String, Object>> aliasToValueMapList1 = query2.list(); HashMap
	 * scheduledetails=new HashMap(); ArrayList daydetails=new ArrayList();
	 * if(aliasToValueMapList1.size()>1){
	 * 
	 * for(int j=0;j<aliasToValueMapList1.size();j++){
	 * System.out.println("aliasToValueMapList1---"
	 * +aliasToValueMapList1.size()); ArrayList day=new ArrayList(); Map<String,
	 * Object> rs1 = aliasToValueMapList.get(j); String monday =
	 * rs.get("monday").toString(); String tuesday =
	 * rs.get("tuesday").toString(); String wednesday =
	 * rs.get("wednesday").toString(); String thursday =
	 * rs.get("thursday").toString(); String friday =
	 * rs.get("friday").toString(); String saturday =
	 * rs.get("saturday").toString(); String sunday =
	 * rs.get("sunday").toString(); String
	 * formfortypename=rs.get("form_four_type_name").toString();
	 * day.add(monday); day.add(tuesday); day.add(wednesday); day.add(thursday);
	 * day.add(friday); day.add(saturday); day.add(sunday); daydetails.add(day);
	 * scheduledetails.put(formfortypename,day); }
	 * 
	 * System.out.println("scheduledetails----//////////////------"+scheduledetails
	 * );
	 * 
	 * }else{ ja.add(rs.get("schedule_number")); String number =
	 * rs.get("max(t.form_four_type_id)").toString();
	 * System.out.println("mcmzcz"+number); String monday =
	 * rs.get("monday").toString(); String tuesday =
	 * rs.get("tuesday").toString(); String wednesday =
	 * rs.get("wednesday").toString(); String thursday =
	 * rs.get("thursday").toString(); String friday =
	 * rs.get("friday").toString(); String saturday =
	 * rs.get("saturday").toString(); String sunday =
	 * rs.get("sunday").toString();
	 * 
	 * if (monday.equals("1")) { ja.add(rs.get("form_four_type_name")); } else {
	 * ja.add(""); } if (tuesday.equals("1")) {
	 * ja.add(rs.get("form_four_type_name")); } else { ja.add(""); } if
	 * (wednesday.equals("1")) { ja.add(rs.get("form_four_type_name")); } else {
	 * ja.add(""); } if (thursday.equals("1")) {
	 * ja.add(rs.get("form_four_type_name")); } else { ja.add(""); } if
	 * (friday.equals("1")) { ja.add(rs.get("form_four_type_name")); } else {
	 * ja.add(""); } if (saturday.equals("1")) {
	 * ja.add(rs.get("form_four_type_name")); } else { ja.add(""); } if
	 * (sunday.equals("1")) { ja.add(rs.get("form_four_type_name")); } else {
	 * ja.add(""); }
	 * 
	 * array.add(ja); }
	 * 
	 * 
	 * 
	 * 
	 * ja.add(
	 * "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' "
	 * + "id='" + rs.get("schedule_id") + "' name='checkid' value='" +
	 * rs.get("schedule_id") + "'/>" +
	 * "<input type='hidden' id='checkidd' value='" + aliasToValueMapList.size()
	 * + "'/>");
	 * 
	 * 
	 * 
	 * 
	 * //}
	 * 
	 * int cnt = 0; totalAfterFilter = aliasToValueMapList.size();
	 * result.put("aaData", array); cnt=getTotalRecords(total,request,col,dir);
	 * result.put("iTotalRecords", cnt); result.put("iTotalDisplayRecords",
	 * cnt); } catch (Exception e) { e.printStackTrace(); } finally {
	 * session.close(); } return result; }
	 */

	/*public int getTotalRecords(int total, HttpServletRequest request, String cols, String dir) {
	  
	  int cnt = 0; 
	  Session session = HibernateUtil.getSession("hibernate.cfg.xml"); 
	  try { 
		  Criteria criteria = session.createCriteria(WeeklyChart.class);
	     criteria.add(Restrictions.eq("deleted_status",0));
	  
	  if (!request.getParameter("sSearch").equals(""))
	  { 
		  String search_term = request.getParameter("sSearch").trim();
	  
	  Junction conditionGroup = Restrictions.disjunction();
	  
	  conditionGroup.add(Restrictions.like("form4.scheduleNumberName", "%" +  search_term + "%")); 
	  // conditionGroup.add(Restrictions.like("status",	  search_term, // MatchMode.START)); 
	  criteria.add(conditionGroup);
	  
	  }
	  if (!cols.equals("")) 
	  { 
		  if (dir.equals("asc"))
	  {
	  criteria.addOrder(Order.asc(cols)); 
	  } else {
	  criteria.addOrder(Order.desc(cols)); 
	  }
		  }
	  
	  criteria.createCriteria("formFour", "form4"); 
	  List<WeeklyChart> list =  criteria.list(); 
	  cnt = list.size(); 
	  } catch (Exception e) {
	  e.printStackTrace();
	  } finally {
		  if (session.isOpen()) { session.close();
	  } 
		  } 
	  return cnt;
		  }

	@SuppressWarnings("unchecked") 
	public JSONObject getData(int total, HttpServletRequest request,String col, String dir) 
	{ 
		JSONObject result = new JSONObject(); 
		  Session session =  HibernateUtil.getSession("hibernate.cfg.xml"); 
		try {
			int totalAfterFilter = total;
	  
	  String searchSQL = ""; 
	  String sql = " from WeeklyChart  ";
	  
	  sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
	
	  Criteria criteria =  session.createCriteria(WeeklyChart.class);
	  criteria.add(Restrictions.eq("deleted_status", 0)); 
	  if(!request.getParameter("sSearch").equals("")) 
	  { 
		  String search_term = request.getParameter("sSearch").trim();
	  
	  Junction conditionGroup = Restrictions.disjunction();
	  conditionGroup.add(Restrictions.like("form4.scheduleNumberName", "%" + search_term + "%")); 
	  // conditionGroup.add(Restrictions.like("status", search_term, // MatchMode.START)); 
	  criteria.add(conditionGroup);
	  
	  }
	  if (!col.equals("")) 
	  { 
		  if (dir.equals("asc"))
		  {
	  criteria.addOrder(Order.asc(col));
	  } else {
	  criteria.addOrder(Order.desc(col)); 
	  } 
		  }
	  criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
	  criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
	  criteria.createCriteria("formFour","form4");
	  
	  List<WeeklyChart> list = criteria.list(); 
	  JSONArray array = new  JSONArray(); 
	  for (int i = 0; i < list.size(); i++) 
	  { 
		  JSONArray ja = new  JSONArray();
	  //
	  ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
	  + list.get(i).getWeekly_chart_id() + "' value='" +
	  list.get(i).getWeekly_chart_id() + "'/>");
	  
	  ja.add(list.get(i).getWeekly_chart_id());
	  ja.add(list.get(i).getFormFour().getScheduleNumberName()); 
	  if(list.get(i).getMonday() == 1) { 
		  ja.add("&#x2714"); 
		  } else { 
			  ja.add("");
	  } 
	  if (list.get(i).getTuesday() == 1) { 
		  ja.add("&#x2714");
		  } else {
	  ja.add(""); 
	  } 
	  if (list.get(i).getWednesday() == 1) 
	  { ja.add("&#x2714"); 
	  }
	  else { ja.add(""); 
	  } 
	  if (list.get(i).getThursday() == 1) {
	  ja.add("&#x2714"); 
	  } else { ja.add("");
	  }
	  if (list.get(i).getFriday() == 1) 
	  { 
		  ja.add("&#x2714");
		  } else { ja.add("");
		  }
	  if(list.get(i).getSaturday() == 1) 
	  { 
		  ja.add("&#x2714");
	  } else {
	  ja.add(""); 
	  } 
	  if (list.get(i).getSunday() == 1) {
		  ja.add("&#x2714"); }
	  else { 
		  ja.add("");
		  }
	   array.add(ja); 
	   }
	  totalAfterFilter = getTotalRecords(total, request, col, dir); result.put("aaData", array);
	  result.put("iTotalRecords", totalAfterFilter);//
	  result.put("iTotalDisplayRecords", totalAfterFilter); 
	  
	  } catch (Exception  ex)  	  { 
		  ex.printStackTrace(); 
	  }
		return result; 
		}
*/
	public Map<Integer, String> getScheduleNumber() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			Query query = session.createQuery("from Schedule where status = 'NEW' and deleted_status = '0' and currentStatus = 'CASE WORKER' order by schedule_number");
			List<Schedule> list = query.list();
			resultMap.put(0, "Select");
			for (Schedule type : list) {
				resultMap.put(type.getSchedule_id(), type.getScheduleNumber());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}

		return resultMap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getScheduleid() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			// String qry =
			// "select org_type_id from org_type where org_type in('CENTRAL OFFICE','DIVISION','STORE','ACCOUNT','DEPOT')";
			String qry = "select schedule_id from schedule where status = 'NEW' and deleted_status = '0' and currentStatus = 'CASE WORKER' order by schedule_number";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getschedulenumberName() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select schedule_number from schedule where status = 'NEW' and deleted_status = '0' and currentStatus = 'CASE WORKER' order by schedule_number ";
			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_number").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getFormfourId(int schedulenoid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
			// String qry =
			// "SELECT form_four_id FROM weeklyChart WHERE form_four_id IN(SELECT form_four_id FROM form_four where schedule_number_id="+formfourid
			// +"))";
			String qry = "select form_four_id from form_four where schedule_number_id="
					+ schedulenoid + " and current_status='ACTIVE'";

			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("form_four_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getFormfourName(int scheduleid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml");
			list = new ArrayList();
			String qry = "SELECT schedule_number_name FROM form_four where schedule_number_id="
					+ scheduleid + " and current_status='ACTIVE' ";

			Query query = HibernateUtilTick.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_number_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<FormFour> getFormFourList(int scheduleid) {
		List<FormFour> list = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Criteria criteria = session.createCriteria(FormFour.class);
			criteria.createAlias("scheduleNumber", "schno");
			criteria.add(Restrictions.eq("schno.schedule_id", scheduleid));
			criteria.add(Restrictions.eq("deletedStatus", 0));
			criteria.add(Restrictions.eq("currentStatus", "ACTIVE"));
		

			list = criteria.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return list;
	}

	/*
	 * public List<FormFour> getFormFourList(int scheduleid) { Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml"); List<FormFour> list = new
	 * ArrayList<FormFour>(); String qry =
	 * "SELECT * FROM form_four where schedule_number_id="+ scheduleid + ""; try
	 * { list = session.createSQLQuery(qry).list(); } catch(Exception e) {
	 * e.printStackTrace(); } return list; }
	 */
	public int createWeeklyChart(List<WeeklyChart> weeklychartList, String[] weklyid) {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();

		int j = 0;
		try {
			for (int i = 0; i < weeklychartList.size(); i++) {
				int id = Integer.parseInt(weklyid[i]);
				WeeklyChart weeklychart = (WeeklyChart) session.get(WeeklyChart.class, id);
				//WeeklyChart weeklychart = new WeeklyChart();
				weeklychart = weeklychartList.get(i);
				weeklychart.setCreated_by(request.getSession().getAttribute("userid").toString());
				weeklychart.setCreated_date(new java.util.Date());
				weeklychart.setStatus("ACTIVE");

				if (weeklychartList.get(i).isMondaystatus()) {
					weeklychartList.get(i).setMonday(1);
				}
				if (weeklychartList.get(i).isTuesdaystatus()) {
					weeklychartList.get(i).setTuesday(1);
				}
				if (weeklychartList.get(i).isWednesdaystatus()) {
					weeklychartList.get(i).setWednesday(1);
				}
				if (weeklychartList.get(i).isThursdaystatus()) {
					weeklychartList.get(i).setThursday(1);
				}
				if (weeklychartList.get(i).isFridaystatus()) {
					weeklychartList.get(i).setFriday(1);
				}
				if (weeklychartList.get(i).isSaturdaystatus()) {
					weeklychartList.get(i).setSaturday(1);
				}
				if (weeklychartList.get(i).isSundaystatus()) {
					weeklychartList.get(i).setSunday(1);
				}

				if (weeklychartList.size() >= 0) {
					  session.saveOrUpdate(weeklychart);
				}
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return j;

	}

	/*
	 * @SuppressWarnings({ "unchecked", "unchecked" }) public JSONObject
	 * getEditList(int total, HttpServletRequest request,int scheduleid) {
	 * System.out.println("id"+scheduleid); int totalAfterFilter = total;
	 * 
	 * JSONObject result = new JSONObject(); Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml");
	 * session.beginTransaction(); try { String sql = ""; sql =
	 * "select weeklyChart.weekly_chart_id,weeklyChart.monday,weeklyChart.tuesday,weeklyChart.wednesday,"
	 * +
	 * "weeklyChart.thursday,weeklyChart.friday,weeklyChart.saturday,weeklyChart.sunday,form_four.form_four_id,form_four.schedule_number_name"
	 * +
	 * " from weeklyChart INNER JOIN form_four ON form_four.form_four_id=weeklyChart.form_four_id where "
	 * + "form_four.schedule_number_id="+scheduleid+
	 * " AND form_four.deleted_status=0 AND weeklyChart.deleted_status=0";
	 * 
	 * Query query = session.createSQLQuery(sql);
	 * query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	 * List<Map<String, Object>> aliasToValueMapList = query.list(); int j=0;
	 * int count=0; JSONArray array = new JSONArray(); for (int i = 0; i <
	 * aliasToValueMapList.size(); i++) {
	 * 
	 * Map<String, Object> rs = aliasToValueMapList.get(i); JSONArray ja = new
	 * JSONArray(); ja.add(
	 * "<input type='checkbox' class='group-checkable' checked='checked' data-set='#sample_2 .checkboxes' "
	 * + "id='"+ rs.get("weekly_chart_id")+"' name='checkid' value='"+
	 * rs.get("weekly_chart_id")+ "'/>"
	 * +"<input type='hidden' id='checkidd' value='"
	 * +aliasToValueMapList.size()+"'/>"
	 * +"<input type='hidden' id='"+rs.get("form_four_id"
	 * )+"' name='formfourid' value='"+rs.get("form_four_id")+"' />");
	 * ja.add(rs.get("weekly_chart_id"));
	 * 
	 * ja.add(rs.get("schedule_number_name")); String
	 * monday=rs.get("monday").toString();
	 * //System.out.println("hiiiiiiiiiiiii"+monday); String
	 * tuesday=rs.get("tuesday").toString(); String
	 * wednesday=rs.get("wednesday").toString(); String
	 * thursday=rs.get("thursday").toString(); String
	 * friday=rs.get("friday").toString(); String
	 * saturday=rs.get("saturday").toString(); String
	 * sunday=rs.get("sunday").toString();
	 * 
	 * //System.out.println("helllllllllllllllllll"+rs.get("monday")); if
	 * (monday.equalsIgnoreCase("0")) { ja.add(
	 * "<s:checkbox name='%{'weeklychartList[' + #ctr.index + '].tuesdaystatus'}' />"
	 * ); //
	 * +"<input type='hidden' name='checkid' value='"+rs.get("weekly_chart_id"
	 * )+"'/>");
	 * 
	 * } else {
	 * ja.add("<input type='checkbox' class='group-checkable' checked='checked' "
	 * + "data-set='#sample_2 .checkboxes' name='weeklychartList["+count+
	 * "].mondaystatus' id='mon0"+j+"' value=''/>");
	 * 
	 * } if (tuesday.equalsIgnoreCase("0")) {
	 * ja.add("<input type='checkbox' class='group-checkable' " +
	 * "data-set='#sample_2 .checkboxes' name='weeklychartList["
	 * +count+"].tuesdaystatus' id='mon1"+j+"' value=''/>");
	 * 
	 * } else {
	 * ja.add("<input type='checkbox' class='group-checkable' checked='checked' "
	 * + "data-set='#sample_2 .checkboxes' name='weeklychartList["+count+
	 * "].tuesdaystatus' id='mon1"+j+"' value=''/>");
	 * 
	 * } if (wednesday.equalsIgnoreCase("0")) {
	 * ja.add("<input type='checkbox' class='group-checkable' " +
	 * "data-set='#sample_2 .checkboxes' name='weeklychartList["
	 * +count+"].wednesdaystatus' id='mon2"+j+"' value=''/>"); } else {
	 * ja.add("<input type='checkbox' class='group-checkable' checked='checked' "
	 * + "data-set='#sample_2 .checkboxes' name='weeklychartList["+count+
	 * "].wednesdaystatus' id='mon2"+j+"' value=''/>"); } if
	 * (thursday.equalsIgnoreCase("0")) {
	 * ja.add("<input type='checkbox' class='group-checkable' " +
	 * "data-set='#sample_2 .checkboxes' name='weeklychartList["
	 * +count+"].thursdaystatus'' id='mon3"+j+"' value=''/>"); } else {
	 * ja.add("<input type='checkbox' class='group-checkable' checked='checked' "
	 * + "data-set='#sample_2 .checkboxes' name='weeklychartList["+count+
	 * "].thursdaystatus' id='mon3"+j+"' value=''/>"); } if
	 * (friday.equalsIgnoreCase("0")) {
	 * ja.add("<input type='checkbox' class='group-checkable' " +
	 * "data-set='#sample_2 .checkboxes' name='weeklychartList["
	 * +count+"].fridaystatus' id='mon4"+j+"' value=''/>"); } else {
	 * ja.add("<input type='checkbox' class='group-checkable' checked='checked' "
	 * + "data-set='#sample_2 .checkboxes' name='weeklychartList["+count+
	 * "].fridaystatus' id='mon4"+j+"' value=''/>"); } if
	 * (saturday.equalsIgnoreCase("0")) {
	 * ja.add("<input type='checkbox' class='group-checkable' " +
	 * "data-set='#sample_2 .checkboxes' name='weeklychartList["
	 * +count+"].saturdaystatus' id='mon5"+j+"' value=''/>"); } else {
	 * ja.add("<input type='checkbox' class='group-checkable' checked='checked' "
	 * + "data-set='#sample_2 .checkboxes' name='weeklychartList["+count+
	 * "].saturdaystatus' id='mon5"+j+"' value=''/>"); } if
	 * (sunday.equalsIgnoreCase("0")) {
	 * ja.add("<input type='checkbox' class='group-checkable' " +
	 * "data-set='#sample_2 .checkboxes' name='weeklychartList["
	 * +count+"].sundaystatus' id='mon6"+j+"' value=''/>"); } else {
	 * ja.add("<input type='checkbox' class='group-checkable' checked='checked' "
	 * + "data-set='#sample_2 .checkboxes' name='weeklychartList["+count+
	 * "].sundaystatus' id='mon6"+j+"' value=''/>"); } j++; count++;
	 * array.add(ja);
	 * 
	 * } int cnt = 0; totalAfterFilter = aliasToValueMapList.size();
	 * result.put("aaData", array); result.put("iTotalRecords",
	 * totalAfterFilter); result.put("iTotalDisplayRecords", totalAfterFilter);
	 * } catch (Exception e) { e.printStackTrace(); } finally { session.close();
	 * }
	 * 
	 * return result;
	 * 
	 * }
	 */
	// public void updateWeeklyChart(List<WeeklyChart> weeklychartList,String[]
	// mon,String[] formfourid) {
	public String updateWeeklyChart(List<WeeklyChart> weeklychartList, String[] weklyid,String[] formfourid1) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Transaction tx=null;
		session.beginTransaction();
		
		
		try {
		
			for (int i = 0; i < weeklychartList.size(); i++) {
				int id = Integer.parseInt(weklyid[i]);
				int formfourid = Integer.parseInt(formfourid1[i]);
			
				if(id==0){
					tx=session.beginTransaction();
					WeeklyChart weeklychart=new WeeklyChart();
					FormFour ff=new FormFour();
					ff.setId(formfourid);
					weeklychart.setFormFour(ff);
					if (weeklychartList.get(i).isMondaystatus()) {
						weeklychart.setMonday(1);
					} else {
						weeklychart.setMonday(0);
					}
					if (weeklychartList.get(i).isTuesdaystatus()) {
						weeklychart.setTuesday(1);
					} else {
						weeklychart.setTuesday(0);
					}
					if (weeklychartList.get(i).isWednesdaystatus()) {
						weeklychart.setWednesday(1);
					} else {
						weeklychart.setWednesday(0);
					}
					if (weeklychartList.get(i).isThursdaystatus()) {
						weeklychart.setThursday(1);
					} else {
						weeklychart.setThursday(0);
					}
					if (weeklychartList.get(i).isFridaystatus()) {
						weeklychart.setFriday(1);
					} else {
						weeklychart.setFriday(0);
					}
					if (weeklychartList.get(i).isSaturdaystatus()) {
						weeklychart.setSaturday(1);
					} else {
						weeklychart.setSaturday(0);
					}
					if (weeklychartList.get(i).isSundaystatus()) {
						weeklychart.setSunday(1);
					} else {
						weeklychart.setSunday(0);
					}
					if (weeklychartList.get(i).isHolidaystatus()) {
						weeklychart.setHoliday(1);
					} else {
						weeklychart.setHoliday(0);
					}
					weeklychart.setStatus("ACTIVE");
					weeklychart.setCreated_date(new Date());
					weeklychart.setCreated_by(request.getSession().getAttribute("userid").toString());
					int a = (Integer) session.save(weeklychart);
					session.getTransaction().commit();
				}else{
					tx=session.beginTransaction();
				WeeklyChart weeklychart1 = (WeeklyChart) session.get(WeeklyChart.class, id);
				weeklychart1.setUpdated_by(request.getSession().getAttribute("userid").toString());
				weeklychart1.setUpdated_date(new Date());
				//weeklychart1.setFormFour(ff);

				if (weeklychartList.get(i).isMondaystatus()) {
					weeklychart1.setMonday(1);
				} else {
					weeklychart1.setMonday(0);
				}
				if (weeklychartList.get(i).isTuesdaystatus()) {
					weeklychart1.setTuesday(1);
				} else {
					weeklychart1.setTuesday(0);
				}
				if (weeklychartList.get(i).isWednesdaystatus()) {
					weeklychart1.setWednesday(1);
				} else {
					weeklychart1.setWednesday(0);
				}
				if (weeklychartList.get(i).isThursdaystatus()) {
					weeklychart1.setThursday(1);
				} else {
					weeklychart1.setThursday(0);
				}
				if (weeklychartList.get(i).isFridaystatus()) {
					weeklychart1.setFriday(1);
				} else {
					weeklychart1.setFriday(0);
				}
				if (weeklychartList.get(i).isSaturdaystatus()) {
					weeklychart1.setSaturday(1);
				} else {
					weeklychart1.setSaturday(0);
				}
				if (weeklychartList.get(i).isSundaystatus()) {
					weeklychart1.setSunday(1);
				} else {
					weeklychart1.setSunday(0);
				}
				if (weeklychartList.get(i).isHolidaystatus()) {
					weeklychart1.setHoliday(1);
				} else {
					weeklychart1.setHoliday(0);
				}
			
					//System.out.println("weklyid"+weeklychart1.getWeekly_chart_id());
					session.update(weeklychart1);
					session.getTransaction().commit();
			
			}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	public List<WeeklyChart> getEditedWeeklyChart(int id) {
		
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		List<WeeklyChart> list = new ArrayList<WeeklyChart>();
		FormFour fm = new FormFour();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//List<WeeklyChart> Formlist=new ArrayList<WeeklyChart>();
		session.beginTransaction();
		try {
			String sql = "";
			sql=" select weekly_chart_id ,monday,tuesday,wednesday,thursday,friday,saturday,sunday," +
					"holiday,form_four_id,schedule_number_name,effective_start_date,effective_end_date,efe " 
			+		" from ( select weeklyChart.weekly_chart_id,weeklyChart.monday monday ," +
			"weeklyChart.tuesday tuesday,weeklyChart.wednesday wednesday, "
			+" weeklyChart.thursday thursday,weeklyChart.friday friday,weeklyChart.saturday saturday ," +
			"weeklyChart.sunday sunday,weeklyChart.holiday holiday,form_four.form_four_id," +
			"form_four.schedule_number_name, "
			+" DATE_FORMAT(form_four.effective_start_date,'%d-%m-%Y') AS effective_start_date," +
			"IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%Y'),'NA') AS effective_end_date," +
			"form_four.effective_end_date as efe"
			+" from weeklyChart " 
			+" INNER JOIN form_four ON form_four.form_four_id=weeklyChart.form_four_id where " 
			+"  form_four.schedule_number_id="+id+"  AND form_four.deleted_status=0 AND weeklyChart.deleted_status=0 and form_four.current_status='ACTIVE' "
			+" union "
			+" select 0 weekly_chart_id ,0 monday,0 tuesday ,0 wednesday,0 thursday,0 friday ,0 saturday,0 sunday,0 holiday," +
			"form_four_id,schedule_number_name,DATE_FORMAT(effective_start_date,'%d-%m-%Y') AS effective_start_date," +
			"IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%Y'),'NA') AS effective_end_date,form_four.effective_end_date as efe "	
			+" from form_four where schedule_number_id="+id+" AND deleted_status=0 and current_status='ACTIVE') a where efe>=curdate() or efe is null ";
			sql += " group by form_four_id";
			sql += " order by form_four_id";
			/*sql=" select * from ( select weeklyChart.weekly_chart_id,weeklyChart.monday,weeklyChart.tuesday,weeklyChart.wednesday, "
+" weeklyChart.thursday,weeklyChart.friday,weeklyChart.saturday,weeklyChart.sunday,form_four.form_four_id,form_four.schedule_number_name, "
+" DATE_FORMAT(form_four.effective_start_date,'%d-%m-%y') AS effective_start_date,IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%y'),'NA') AS effective_end_date "
+" from weeklyChart " 
+" INNER JOIN form_four ON form_four.form_four_id=weeklyChart.form_four_id where " 
+"  form_four.schedule_number_id="+id+"  AND form_four.deleted_status=0 AND weeklyChart.deleted_status=0 "
+" union "
+" select 0,0,0,0,0,0,0,0,form_four_id,schedule_number_name,DATE_FORMAT(effective_start_date,'%d-%m-%y') AS effective_start_date,IFNULL(DATE_FORMAT(form_four.effective_end_date,'%d-%m-%y'),'NA') AS effective_end_date "	
+" from form_four where schedule_number_id="+id+" AND deleted_status=0 ) a  group by form_four_id order by weekly_chart_id";*/
			
			
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					//list.add(rs.get("schedule_number_name").toString());
					WeeklyChart wch=new WeeklyChart();
					if (rs != null) {
					wch.setWeekly_chart_id(Integer.parseInt(rs.get("weekly_chart_id").toString()));
					wch.setFormfourname(rs.get("schedule_number_name").toString());
					wch.setStartdate(rs.get("effective_start_date").toString());
					wch.setEnddate((rs.get("effective_end_date").toString()!=null?rs.get("effective_end_date").toString():""));
					wch.setMonday(Integer.parseInt(rs.get("monday").toString()));
					wch.setTuesday(Integer.parseInt(rs.get("tuesday").toString()));
					wch.setWednesday(Integer.parseInt(rs.get("wednesday").toString()));
					wch.setThursday(Integer.parseInt(rs.get("thursday").toString()));
					wch.setFriday(Integer.parseInt(rs.get("friday").toString()));
					wch.setSaturday(Integer.parseInt(rs.get("saturday").toString()));
					wch.setSunday(Integer.parseInt(rs.get("sunday").toString()));
					wch.setHoliday(Integer.parseInt(rs.get("holiday").toString()));
					wch.setFormfourid(Integer.parseInt(rs.get("form_four_id").toString()));
					
					}
					list.add(wch);
				}
			}
		/*	Criteria criteria = session.createCriteria(WeeklyChart.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			criteria.createAlias("formFour", "formFour");
			
			Junction or = Restrictions.conjunction();
			or.add(Restrictions.eq("formFour.scheduleNumber.schedule_id", id));

			criteria.add(or);

		    list = criteria.list();*/
		   // Iterator iterator = (Iterator) list.iterator();
//		    WeeklyChart employee =new WeeklyChart();
//		    Date date = fm.getEffectiveStartDate();
//		    System.out.println("Database Date Format :" + date);
//		    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//		   // String dateBufferString = format.format(date);
//		    Date dateBuffer;
//		  
//
//		  //  dateBuffer = format.parse(dateBufferString);
//		    System.out.println("Database Date Format :" + date);
//		    System.out.println("(dd-MM-yyyy) date: " + format.format(date));
//		  //  System.out.println("dateBufferSTring " + dateBufferString);
//		  //  System.out.println("dateBuffer: " + dateBuffer);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return list;
	}

	public boolean checkWeeklyChartFormFour(List<WeeklyChart> weeklychartList) {
		boolean flag = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Query query = null;
		String[] formFourid = request.getParameterValues("formFourid");
		String status = "ACTIVE";
		for (int i = 0; i < weeklychartList.size(); i++) {
			// System.out.println("id-----" + formfourid);
			int id1 = Integer.parseInt(formFourid[i]);
			String qry = "select form_four_id from weeklyChart where form_four_id='"
					+ id1 + "' and deleted_status= " + 0;
			query = HibernateUtil.getSession("").createSQLQuery(qry);
		}

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	/*
	 * public List getScheduleid(){ Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml");
	 * session.beginTransaction(); try{ String q=
	 * "select weeklyChart.weekly_chart_id,max(weeklyChart.monday)monday ,max(weeklyChart.tuesday)tuesday,"
	 * +
	 * "max(weeklyChart.wednesday)wednesday,max(weeklyChart.thursday)thursday, "
	 * +
	 * "max(weeklyChart.friday)friday,max(weeklyChart.saturday)saturday,max(weeklyChart.sunday)sunday,f.form_four_id,"
	 * + "max(f.form_four_name),max(t.form_four_type_id)," +
	 * " t.form_four_type_name ,s.schedule_number,s.schedule_id " +
	 * "from weeklyChart weeklyChart " +
	 * "inner join form_four f on weeklyChart.form_four_id=f.form_four_id " +
	 * "inner join form_four_type t on f.form_four_name=t.form_four_type_id " +
	 * "inner join schedule s on f.schedule_number_id=s.schedule_id group by s.schedule_number,t.form_four_type_name"
	 * ; Query query = session.createSQLQuery(q);
	 * query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	 * List<Map<String, Object>> aliasToValueMapList = query.list();
	 * System.out.println("hdfdf"+aliasToValueMapList); }catch(Exception e){
	 * e.printStackTrace() }
	 */

	// }
	
	public void insertInitialChart(int formfourid, int userid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		WeeklyChart chart = new WeeklyChart();
		FormFour formfour = new FormFour();
		formfour.setId(formfourid);
		chart.setFormFour(formfour);
		chart.setMonday(1);
		chart.setTuesday(1);
		chart.setWednesday(1);
		chart.setThursday(1);
		chart.setFriday(1);
		chart.setSaturday(1);
		chart.setSunday(1);
		chart.setHoliday(1);
		chart.setCreated_date(new Date());
		chart.setCreated_by(String.valueOf(userid));
		chart.setStatus("ACTIVE");
		session.save(chart);
		session.getTransaction().commit();
	}
	
	
	public boolean getChecklist(int id){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		boolean flag=false;
		int count=0;
		try{
		
			Query query = session.createSQLQuery("select count(*) count from weeklyChart INNER JOIN form_four ON form_four.form_four_id=weeklyChart.form_four_id where " + 
					" form_four.schedule_number_id = "+ id +"  AND form_four.deleted_status=0 AND weeklyChart.deleted_status=0;").addScalar("count",Hibernate.INTEGER);
			List<Integer> list = query.list();
			 count=list.get(0);
			 if(count==0){
				 flag=true;
			 }else{
				 flag=false;
			 }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return flag;
	}
	public boolean isGreaterThanCurrentDateTime(String date){
		boolean isSuccess = false;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date1 = sdf.parse(date);
			if(date1.compareTo(new Date())>=0){
				isSuccess =true;
			}else{
				isSuccess = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isSuccess;
				
	}
}
