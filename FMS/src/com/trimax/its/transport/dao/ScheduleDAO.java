package com.trimax.its.transport.dao;

import java.nio.channels.SeekableByteChannel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.FormFourType;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ScheduleServiceLimit;
import com.trimax.its.vehicle.model.ServiceLimit;

public class ScheduleDAO {

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir) {
		Common common = new Common();
		JSONObject result = new JSONObject();
		String search_term="";
		try {
			int totalAfterFilter = total;
			int  depotid=0;

			// JSONArray array = new JSONArray();
			// String searchSQL = "";
			// String sql ="from BusStops where status != 'DELETED'";
			//
			// //sql += " order by " + COL_NAME + " " + DIR;
			//
			// sql += " limit " + request.getParameter("iDisplayStart") + ", " +
			// request.getParameter("iDisplayLength");
			// Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(BusStops.class);
			// criteria.add(Restrictions.ne("status", "deleted"));
			// System.out.println("sSearch------->"+request.getParameter("sSearch"));
			//
			
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Schedule.class);

			criteria.createAlias("brand", "brand");
			criteria.createAlias("depot", "depot");
			criteria.createAlias("servicetype", "servicetype");
			criteria.createAlias("scheduletype", "scheduletype");
			
			Junction or = Restrictions.disjunction();
			or.add(Restrictions.eq("status","ACTIVE"));
			or.add(Restrictions.eq("status","NEW"));
			//or.add(Restrictions.eq("status","Rationalized"));
			criteria.add(or);
			
			Junction or1 = Restrictions.disjunction();
			or1.add(Restrictions.eq("currentStatus","APPROVED"));
			or1.add(Restrictions.eq("currentStatus","CASE WORKER"));
			criteria.add(or1);
			
			Junction or2 = Restrictions.disjunction();
			or2.add(Restrictions.eq("deletedStatus",0));
			criteria.add(or2);
			if(request.getSession().getAttribute("orgtypeid").toString().equals("3"))
			{
				depotid=Integer.parseInt(request.getSession().getAttribute("orgchartid").toString());
				Junction or3 = Restrictions.disjunction();
				or3.add(Restrictions.eq("depot.org_chart_id",depotid));
				criteria.add(or3);
				
			}
			
			// criteria.setFetchMode("BrandType", FetchMode.JOIN);
			// criteria.setFetchMode("Route", FetchMode.JOIN);
			// criteria.setFetchMode("OrganisationChart", FetchMode.JOIN);
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("scheduleNumber", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("depot.org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("brand.brand_type_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("servicetype.serviceTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("scheduletype.scheduleName", "%"
						+ search_term + "%"));

			

//				conditionGroup.add(Restrictions.eq("ScheduleServiceLimit.schedulServiceId","%"+search_term+"%"));

				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("schedule_id",srch ));
				}
				// conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%"
				// ));
				criteria.add(conditionGroup);
				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
			}

			System.out
					.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"
							+ Integer.parseInt(request
									.getParameter("iDisplayLength")));
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			System.out.println("My Criteria");
			List<Schedule> list = criteria.list();
			JSONArray array = new JSONArray();
			String effDate="";
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				
				if(list.get(i).getEffectiveStartDate()!=null){
					effDate = common.getDateFromDateTime_(list.get(i).getEffectiveStartDate().toString()
							.replace(".0", "").substring(0, 10));
				
				}
				else{
					effDate = "-";
				}
				
				
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ i
						+ "' value='"
						+ list.get(i).getSchedule_id() + "'/><input type='hidden' name='status' id='status"+i+"' value='"+list.get(i).getStatus()+"'/><input type='hidden' name='effstartdate' id='effstartdate"+i+"' value='"+effDate+"'/>");
				ja.add(list.get(i).getSchedule_id());
				ja.add(list.get(i).getScheduleNumber());
				ja.add(list.get(i).getBrand().getBrand_type_name());

			if(list.get(i).getEffectiveStartDate()!=null){
					effDate = common.getDateFromDateTime_(list.get(i).getEffectiveStartDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(effDate);
				}
				else{
					ja.add("-");
				}
			
			if(list.get(i).getEffectiveEndDate()!=null){
				effDate = common.getDateFromDateTime_(list.get(i).getEffectiveEndDate().toString()
						.replace(".0", "").substring(0, 10));
			ja.add(effDate);
			}
			else{
				ja.add("-");
			}
				ja.add(list.get(i).getDepot().getOrg_name());

								ja.add(list.get(i).getServicetype().getServiceTypeName());
				if(list.get(i).getIsTrunkSchedule().equals("Y")){
					ja.add("Regular");
				}
				else{
					ja.add("Feeder");
				}
				String target_amount=getScheduleTargetAmount(list.get(i).getSchedule_id());
				if(target_amount!=null){
					ja.add(target_amount);
				}else
				{
				ja.add("");
				}
				
				String serviceLimit=getServiceLimit(list.get(i).getSchedule_id());
				if(serviceLimit!=null){
					ja.add(serviceLimit);
				}else
				{
				ja.add("");
				}
				
				ja.add(list.get(i).getScheduletype().getScheduleName());
				ja.add(list.get(i).getConductor());
				ja.add(list.get(i).getDriver());

				ja.add(list.get(i).getStatus());

				array.add(ja);
				
			}

			totalAfterFilter = getTotalRecords(search_term);

			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

//			System.out.println("REsult ------>" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData1(int total, HttpServletRequest request,String col,String dir) {
		Common common = new Common();
		JSONObject result = new JSONObject();
		String search_term="";
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			// String searchSQL = "";
			// String sql ="from BusStops where status != 'DELETED'";
			//
			// //sql += " order by " + COL_NAME + " " + DIR;
			//
			// sql += " limit " + request.getParameter("iDisplayStart") + ", " +
			// request.getParameter("iDisplayLength");
			// Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			// Criteria criteria = session.createCriteria(BusStops.class);
			// criteria.add(Restrictions.ne("status", "deleted"));
			// System.out.println("sSearch------->"+request.getParameter("sSearch"));
			//
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Schedule.class);

			criteria.createAlias("brand", "brand");
			criteria.createAlias("depot", "depot");
			criteria.createAlias("servicetype", "servicetype");
			criteria.createAlias("scheduletype", "scheduletype");
			
			
			Junction or = Restrictions.disjunction();
			
			or.add(Restrictions.eq("status","Rationalized"));
			criteria.add(or);
			
			Junction or1 = Restrictions.disjunction();
			or1.add(Restrictions.eq("currentStatus","APPROVED"));
			or1.add(Restrictions.eq("currentStatus","CASE WORKER"));
			criteria.add(or1);
			
			Junction or2 = Restrictions.disjunction();
			or2.add(Restrictions.eq("deletedStatus",0));
			criteria.add(or2);
			
			// criteria.setFetchMode("BrandType", FetchMode.JOIN);
			// criteria.setFetchMode("Route", FetchMode.JOIN);
			// criteria.setFetchMode("OrganisationChart", FetchMode.JOIN);
			if(request.getSession().getAttribute("orgtypeid").toString().equals("3"))
			{
				int depotid=Integer.parseInt(request.getSession().getAttribute("orgchartid").toString());
				Junction or3 = Restrictions.disjunction();
				or3.add(Restrictions.eq("depot.org_chart_id",depotid));
				criteria.add(or3);
				
			}
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("scheduleNumber", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("depot.org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("brand.brand_type_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("servicetype.serviceTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("scheduletype.scheduleName", "%"
						+ search_term + "%"));
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("schedule_id",srch ));
				}
				// conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%"
				// ));
				criteria.add(conditionGroup);
				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
			}

			System.out
					.println("Integer.parseInt(request.getParameter(iDisplayStart))---->"
							+ Integer.parseInt(request
									.getParameter("iDisplayLength")));
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
//			System.out.println("My Criteria");
			List<Schedule> list = criteria.list();
			JSONArray array = new JSONArray();
			String effDate="";
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				
				if(list.get(i).getEffectiveStartDate()!=null){
					effDate = common.getDateFromDateTime_(list.get(i).getEffectiveStartDate().toString()
							.replace(".0", "").substring(0, 10));
				
				}
				else{
					effDate = "-";
				}
				ja.add("");
				ja.add(list.get(i).getSchedule_id());
				ja.add(list.get(i).getScheduleNumber());
				ja.add(list.get(i).getBrand().getBrand_type_name());

			if(list.get(i).getEffectiveStartDate()!=null){
					effDate = common.getDateFromDateTime_(list.get(i).getEffectiveStartDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(effDate);
				}
				else{
					ja.add("-");
				}
			
			if(list.get(i).getEffectiveEndDate()!=null){
				effDate = common.getDateFromDateTime_(list.get(i).getEffectiveEndDate().toString()
						.replace(".0", "").substring(0, 10));
			ja.add(effDate);
			}
			else{
				ja.add("-");
			}
				ja.add(list.get(i).getDepot().getOrg_name());

								ja.add(list.get(i).getServicetype().getServiceTypeName());
				if(list.get(i).getIsTrunkSchedule().equals("Y")){
					ja.add("Regular");
				}
				else{
					ja.add("Feeder");
				}
				String target_amount=getScheduleTargetAmount(list.get(i).getSchedule_id());
				if(target_amount!=null){
					ja.add(target_amount);
				}else
				{
				ja.add("");
				}
				ja.add(list.get(i).getScheduletype().getScheduleName());

				ja.add(list.get(i).getStatus());

				array.add(ja);
				
			}

			totalAfterFilter = getTotalRecords1(search_term);

			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

//			System.out.println("REsult ------>" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public int getTotalRecords(String searchString) {
		String sql = "";
		if(!(searchString.equals("") || searchString.equals(null))){
			sql = "select count(*) as count from schedule s inner join brand_type bt on s.brand_type_id = bt.brand_type_id inner join org_chart oc on s.depot_id = oc.org_chart_id inner join schedule_type st on s.schedule_type = st.schedule_type_id inner join service_type svt on s.schedule_service_type = svt.service_type_id where (s.status = 'ACTIVE' or s.status = 'NEW') AND (current_status = 'APPROVED' or current_status = 'CASE WORKER') AND ";
			sql += "(";
			sql += " schedule_number like '%"+searchString+"%'";
			sql += " OR s.status like '%"+searchString+"%'";
			sql += " OR bt.brand_type_name like '%"+searchString+"%'";
			sql += " OR svt.service_type_name like '%"+searchString+"%'";
			sql += " OR st.schedule_type_name like '%"+searchString+"%'";
			sql += " OR oc.org_name like '%"+searchString+"%'";
			sql += " AND s.deleted_status=0";
			sql += ")";
			if(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString().equals("3")){
				sql += " AND s.depot_id='"+ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()+"'";
			}
		}
		else{
			sql = "select count(*) as count from schedule where (status = 'ACTIVE' or status = 'NEW' or status='Rationalised') AND (current_status = 'APPROVED' or current_status = 'CASE WORKER') " ;
			if(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString().equals("3")){
				sql += " AND depot_id='"+ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()+"'";
			}
				sql += " AND deleted_status=0";
		}
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql).addScalar("count",Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		return cnt;
	}
	
	public int getTotalRecords1(String searchString) {
		String sql = "";
		if(!(searchString.equals("") || searchString.equals(null))){
			sql = "select count(*) as count from schedule s inner join brand_type bt on s.brand_type_id = bt.brand_type_id inner join org_chart oc on s.depot_id = oc.org_chart_id inner join schedule_type st on s.schedule_type = st.schedule_type_id inner join service_type svt on s.schedule_service_type = svt.service_type_id where (s.status = 'Rationalized') AND (current_status = 'APPROVED' or current_status = 'CASE WORKER') AND ";
			sql += "(";
			sql += " schedule_number like '%"+searchString+"%'";
			sql += " OR s.status like '%"+searchString+"%'";
			sql += " OR bt.brand_type_name like '%"+searchString+"%'";
			sql += " OR svt.service_type_name like '%"+searchString+"%'";
			sql += " OR st.schedule_type_name like '%"+searchString+"%'";
			sql += " OR oc.org_name like '%"+searchString+"%'";
			sql += ")";	
			if(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString().equals("3")){
				sql += " AND s.depot_id='"+ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()+"'";
			}
		}
		else{
			sql = "select count(*) as count from schedule where (status = 'Rationalized' ) AND (current_status = 'APPROVED' or current_status = 'CASE WORKER') ";
			if(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString().equals("3")){
				sql += " AND depot_id='"+ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()+"'";
			}
			sql += " and deleted_status=0";
		}
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql).addScalar("count",Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		return cnt;
	}
	
	
	public Map<Integer, String> getScheduleType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = null;
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ScheduleType where status = 'ACTIVE' and deleted_status = '0'");
		List<ScheduleType> list = query.list();
		resultMap.put(0, "Select");
		for (ScheduleType type : list) {
			resultMap.put(type.getSchedule_type_id(), type.getScheduleName());
		}
		}
		finally{
			session.close();
		}
		return resultMap;
	}

	public Map<Integer, String> getServiceType() {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from ScheduleService where status = 'ACTIVE' and deleted_status = '0'");
		List<ScheduleService> list = query.list();
		resultMap.put(0, "Select");
		for (ScheduleService type : list) {
			resultMap.put(type.getService_type_id(), type.getServiceTypeName());
		}
		}
		finally{
			session.close();
		}
		return resultMap;

	}
	
	

	public Map<Integer, String> getBrand() {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from BrandType where status = 'ACTIVE' and deleted_status = '0'");
		List<BrandType> list = query.list();
		resultMap.put(0, "Select");
		for (BrandType type : list) {
			resultMap.put(type.getBrand_type_id(), type.getBrand_type_name());
		}
		}
		finally{
			session.close();
		}
		return resultMap;

	}
	
	
	public Map<Integer, String> getBrandService() {
//		System.out.println("in brand service");

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session.createQuery("from ServiceLimit");
		List<ServiceLimit> list = query.list();
		resultMap.put(0, "Select");
		for (ServiceLimit type : list) {
			resultMap.put(type.getBrand_service_id(), type.getService_limit());
		}
		}
		finally{
			session.close();
		}
		return resultMap;

	}
	

	public Map<Integer, String> getDepots() {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session
				.createQuery("from OrganisationChart org where org.orgType.org_type_id = '3' and deleted_status = '0' order by org_name");
		List<OrganisationChart> list = query.list();
		resultMap.put(0, "Select");
		for (OrganisationChart type : list) {
			resultMap.put(type.getOrg_chart_id(), type.getOrg_name());
		}
		}
		finally{
			session.close();
		}
		return resultMap;

	}

	public Map<Integer, String> getRoutes() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session
				.createQuery("from Route_Trans rt where deletedStatus=0 and status='Active' ");
//		System.out.println("here i"+query);
		List<Route_Trans> list = query.list();
		resultMap.put(0, "Select");
		for (Route_Trans type : list) {
			resultMap.put(type.getRoute_id(), type.getRouteName());
		}
		}catch (Exception e) {
         e.printStackTrace();
		}
		finally{
			session.close();
		}
		return resultMap;

	}
	
	public int saveSchedule(Schedule schedule){
		int id = 0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session.beginTransaction();
		id = (Integer) session.save(schedule);
		session.getTransaction().commit();
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		return id;
		
	}
	
	public int saveFormFour(FormFour formfour){
		int id = 0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session.beginTransaction();
		id = (Integer)session.save(formfour);
		session.getTransaction().commit();
		
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			/*if(session!=null){
                session.close();
            }*/
		}
		
		return id;
	}
	
	public int updateSchedule(Schedule schedule){
		ScheduleDAO schdao = new ScheduleDAO();
		List<FormFour> formlist = new ArrayList<FormFour>();
		int id = 0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session.beginTransaction();
		id = schedule.getSchedule_id();
		Schedule sch = (Schedule) session.get(Schedule.class, new Integer(schedule.getSchedule_id()));
		
		
		ScheduleType scheduletype = new ScheduleType();
		scheduletype.setSchedule_type_id(schedule.getScheduletype().getSchedule_type_id());
		
		ScheduleService schservice = new ScheduleService();
		schservice.setService_type_id(schedule.getServicetype().getService_type_id());
		
		BrandType brand = new BrandType();
		brand.setBrand_type_id(schedule.getBrand().getBrand_type_id());
		
		OrganisationChart depot = new OrganisationChart();
		depot.setOrg_chart_id(schedule.getDepot().getOrg_chart_id());
		
		sch.setScheduletype(scheduletype);
		sch.setServicetype(schservice);
		sch.setBrand(brand);
		sch.setDepot(depot);
		
		/*sch.getScheduletype().setSchedule_type_id(schedule.getScheduletype().getSchedule_type_id());
		sch.getServicetype().setService_type_id(schedule.getServicetype().getService_type_id());
		sch.getBrand().setBrand_type_id(schedule.getBrand().getBrand_type_id());
		sch.getDepot().setOrg_chart_id(schedule.getDepot().getOrg_chart_id());*/
		sch.setStatus(schedule.getStatus());
		sch.setConductor(schedule.getConductor());
		sch.setDriver(schedule.getDriver());
		sch.setRemarks(schedule.getRemarks());
		sch.setUpdatedBy(schedule.getUpdatedBy());
		sch.setUpdatedDate(schedule.getUpdatedDate());
		sch.setIsTrunkSchedule(schedule.getIsTrunkSchedule());
		sch.setScheduleNumber(schedule.getScheduleNumber());
		sch.setEffectiveStartDate(schedule.getEffectiveStartDate());
		sch.setEffectiveEndDate(schedule.getEffectiveEndDate());
		//sch.setConductor(schedule.getConductor());
		//sch.setDriver(schedule.getDriver());
		//sch.setTargetamount(schedule.getTargetamount());
		session.update(sch);
		
		formlist = schdao.getFormFourList(sch.getSchedule_id());
		for(FormFour formfour : formlist){
			FormFour form = (FormFour) session.get(FormFour.class, new Integer(formfour.getId()));
			String formfourname = form.getFormFourType().getFormFourTypeName();
			form.setScheduleNumberName(schedule.getScheduleNumber() +"-"+formfourname);
			if(sch.getEffectiveEndDate()!=null && formfour.getEffectiveEndDate()!= null){
			if(sch.getEffectiveEndDate().compareTo(formfour.getEffectiveEndDate())<0){
				formfour.setEffectiveEndDate(sch.getEffectiveEndDate());
			}
			}
			session.update(form);
		}
		
		session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		return id;
	}
	
	public void RationaliseSchedule(Schedule schedule,int userID){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Common common = new Common();
		try{
		session.beginTransaction();
		Schedule sch = (Schedule) session.get(Schedule.class, new Integer(schedule.getSchedule_id()));
		sch.setStatus("Rationalized");
		sch.setUpdatedBy(userID);
		sch.setUpdatedDate(new Date());
		sch.setEffectiveEndDate(common.getIncrementDate(schedule.getEffectiveStartDate(), -1));
		session.update(sch);
		
		Query query = session.createQuery("from FormFour where scheduleNumber.schedule_id = "+schedule.getSchedule_id());
		List<FormFour> formFourList = query.list();
		for(FormFour formfour : formFourList){
			if(sch.getEffectiveEndDate() != null && formfour.getEffectiveEndDate() !=null){
			if(common.getIncrementDate(schedule.getEffectiveStartDate(), -1).compareTo(formfour.getEffectiveEndDate())<0){
				FormFour form = (FormFour) session.get(FormFour.class, new Integer(formfour.getId()));
				form.setEffectiveEndDate(sch.getEffectiveEndDate());
				session.update(form);
			}
			}
		}
		
		session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		
	}
	
	public int checkSchedule(String scheduleNo){
		int cnt = 0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			scheduleNo = scheduleNo.replace(" ", "");	
		Query query = session
				.createQuery("from Schedule org where scheduleNumber = '"+scheduleNo+"' and deletedStatus = 0 and status='NEW' ");
		if(query.list().size()>0){
			cnt = 1;
		}
		}
		catch(Exception e){
			session.getTransaction().rollback();
			System.out.println("Exception --->"+e);
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		return cnt;
	}
	
	public Schedule getSchedule(int id){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Schedule schedule;
		try{
			schedule = (Schedule) session.get(Schedule.class, new Integer(id));
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		return schedule;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getFormFourData(int total, HttpServletRequest request,String col,String dir,int id) {
		Common common = new Common();
		JSONObject result = new JSONObject();
		try {
			String search_term="";
			int totalAfterFilter = total;
			
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(FormFour.class);
			criteria.createAlias("scheduleNumber", "schno");
			criteria.createAlias("formFourType", "formFourType");
			criteria.createAlias("formFourRoute", "formFourRoute");
			criteria.add(Restrictions.eq("schno.schedule_id", id));
			String[] status = { "ACTIVE", "Partial" };
			Junction or = Restrictions.conjunction();
			or.add(Restrictions.in("currentStatus", status));
			//or.add(Restrictions.eq("currentStatus","Partial"));
			or.add(Restrictions.eq("deletedStatus",0));
			criteria.add(or);
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("formFourType.formFourTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("currentStatus", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("formFourRoute.routeNumber", "%"
						+ search_term + "%"));
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("NoofTrips",srch ));
				conditionGroup.add(Restrictions.eq("id",srch ));
				}
				criteria.add(conditionGroup);
			}
						/*
				// conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%"
				// ));
				
				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
			}*/

			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			List<FormFour> list = criteria.list();
			JSONArray array = new JSONArray();
			String effDate="";
			String startDate="";
			String endDate="";
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"
						+ list.get(i).getId() + "'/><input type='hidden' id='status"+list.get(i).getId()+"' name='status"+list.get(i).getId()+"' value='"+list.get(i).getCurrentStatus()+"'/>");
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getFormFourType().getFormFourTypeName());
				ja.add(list.get(i).getScheduleNumber().getScheduleNumber());
				ja.add(list.get(i).getScheduleNumberName());
				ja.add(list.get(i).getNoofTrips());
				ja.add(list.get(i).getFormFourRoute().getRouteNumber());
				/*if(list.get(i).getStartTime()!=null){
					startDate = common.getDateFromDateTime_(list.get(i).getStartTime().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(startDate);
				}
				else{
					ja.add("0000-00-00");
				}*/
				ja.add(list.get(i).getStartTime().toString());
				if(list.get(i).getEffectiveStartDate()!=null){
					effDate = common.getDateFromDateTime_(list.get(i).getEffectiveStartDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(effDate);
				}
				else{
					ja.add(" ");
				}
				
				if(list.get(i).getEffectiveEndDate()!=null){
					endDate = common.getDateFromDateTime_(list.get(i).getEffectiveEndDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(endDate);
				}
				else{
					ja.add(" ");
				}
				
				ja.add(list.get(i).getCurrentStatus());
				ja.add(list.get(i).getRemarks());
				/*ja.add(list.get(i).getNoofTrips());getEffectiveEndDate

								ja.add(list.get(i).getServicetype().getServiceTypeName());
				if(list.get(i).getIsTrunkSchedule().equals("Y")){
					ja.add("Yes");
				}
				else{
					ja.add("No");
				}
				
				ja.add("BMTC");
				ja.add(list.get(i).getScheduletype().getScheduleName());

				ja.add(list.get(i).getStatus());*/

				array.add(ja);
				
			}

			totalAfterFilter = getFormFourTotalRecords(id,search_term);

			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			System.out.println("REsult ------>" + result);

		} catch (Exception e) {
			System.out.println("Exception---->" + e);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getRationalizedFormFourData(int total, HttpServletRequest request,String col,String dir,int scheduleId) {
		Common common = new Common();
		JSONObject result = new JSONObject();
		try {
			String search_term="";
			int totalAfterFilter = total;
			
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(FormFour.class);
			criteria.createAlias("scheduleNumber", "schno");
			criteria.createAlias("formFourType", "formFourType");
			criteria.createAlias("formFourRoute", "formFourRoute");
			criteria.add(Restrictions.eq("schno.schedule_id", scheduleId));
			Junction or = Restrictions.conjunction();
			
			or.add(Restrictions.eq("currentStatus","INACTIVE"));
			
			criteria.add(or);
			
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("formFourType.formFourTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("currentStatus", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("formFourRoute.routeNumber", "%"
						+ search_term + "%"));
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("NoofTrips",srch ));
				conditionGroup.add(Restrictions.eq("id",srch ));
				}
				criteria.add(conditionGroup);
			}
						/*
				// conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%"
				// ));
				
				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
			}*/

			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			List<FormFour> list = criteria.list();
			JSONArray array = new JSONArray();
			String effDate="";
			String startDate="";
			String endDate="";
			String updatedDate="";
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("");
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getFormFourType().getFormFourTypeName());
				ja.add(list.get(i).getScheduleNumber().getScheduleNumber());
				ja.add(list.get(i).getScheduleNumberName());
				ja.add(list.get(i).getNoofTrips());
				ja.add(list.get(i).getFormFourRoute().getRouteNumber());
				/*if(list.get(i).getStartTime()!=null){
					startDate = common.getDateFromDateTime_(list.get(i).getStartTime().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(startDate);
				}
				else{
					ja.add("0000-00-00");
				}*/
				ja.add(list.get(i).getStartTime().toString());
				if(list.get(i).getEffectiveStartDate()!=null){
					effDate = common.getDateFromDateTime_(list.get(i).getEffectiveStartDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(effDate);
				}
				else{
					ja.add(" ");
				}
				
				if(list.get(i).getEffectiveEndDate()!=null){
					endDate = common.getDateFromDateTime_(list.get(i).getEffectiveEndDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(endDate);
				}
				else{
					ja.add(" ");
				}
				
				ja.add(list.get(i).getCurrentStatus());
         		ja.add(list.get(i).getRemarks());
	            String uname=getUserLoginName(session, list.get(i).getUpdatedBy());
                ja.add(uname);
                if(list.get(i).getUpdatedDate()!=null){
                	updatedDate = common.getDateFromDateTime_(list.get(i).getUpdatedDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(updatedDate);
				}else{
				ja.add("");
				}
				/*ja.add(list.get(i).getNoofTrips());getEffectiveEndDate

								ja.add(list.get(i).getServicetype().getServiceTypeName());
				if(list.get(i).getIsTrunkSchedule().equals("Y")){
					ja.add("Yes");
				}
				else{
					ja.add("No");
				}
				
				ja.add("BMTC");
				ja.add(list.get(i).getScheduletype().getScheduleName());

				ja.add(list.get(i).getStatus());*/

				array.add(ja);
				
			}

			totalAfterFilter = getRationalizedFormFourTotalRecords(search_term,scheduleId);

			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

//			System.out.println("REsult ------>" + result);

		} catch (Exception e) {
			System.out.println("Exception---->" + e);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getPartialFormFourData(int total, HttpServletRequest request,String col,String dir) {
		Common common = new Common();
		JSONObject result = new JSONObject();
		try {
			String search_term="";
			int totalAfterFilter = total;
			
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(FormFour.class);
			criteria.createAlias("scheduleNumber", "schno");
			criteria.createAlias("formFourType", "formFourType");
			criteria.createAlias("formFourRoute", "formFourRoute");
			Junction or = Restrictions.conjunction();
			
			or.add(Restrictions.eq("currentStatus","Partial"));
			
			criteria.add(or);
			if(request.getSession().getAttribute("orgtypeid").toString().equals("3"))
			{
				int depotid=Integer.parseInt(request.getSession().getAttribute("orgchartid").toString());
				Junction or3 = Restrictions.disjunction();
				or3.add(Restrictions.eq("schno.depot.org_chart_id",depotid));
				criteria.add(or3);
				
			}
			if(!col.equals("")){
				if(dir.equals("asc")){
				    criteria.addOrder(Order.asc(col));
				}else{
					criteria.addOrder(Order.desc(col));	
				}
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("formFourType.formFourTypeName", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("currentStatus", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("formFourRoute.routeNumber", "%"
						+ search_term + "%"));
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("NoofTrips",srch ));
				conditionGroup.add(Restrictions.eq("id",srch ));
				}
				criteria.add(conditionGroup);
			}
						/*
				// conditionGroup.add(Restrictions.like("alias2","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias3","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias4","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias5","%"+search_term+"%"
				// ));
				// conditionGroup.add(Restrictions.like("alias6","%"+search_term+"%"
				// ));
				
				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
			}*/

			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			List<FormFour> list = criteria.list();
			JSONArray array = new JSONArray();
			String effDate="";
			String startDate="";
			String endDate="";
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("");

				ja.add(list.get(i).getId());
				ja.add(list.get(i).getFormFourType().getFormFourTypeName());
				ja.add(list.get(i).getScheduleNumber().getScheduleNumber());
				ja.add(list.get(i).getScheduleNumberName());
				ja.add(list.get(i).getNoofTrips());
				ja.add(list.get(i).getFormFourRoute().getRouteNumber());
				/*if(list.get(i).getStartTime()!=null){
					startDate = common.getDateFromDateTime_(list.get(i).getStartTime().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(startDate);
				}
				else{
					ja.add("0000-00-00");
				}*/
				ja.add(list.get(i).getStartTime().toString());
				if(list.get(i).getEffectiveStartDate()!=null){
					effDate = common.getDateFromDateTime_(list.get(i).getEffectiveStartDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(effDate);
				}
				else{
					ja.add(" ");
				}
				
				if(list.get(i).getEffectiveEndDate()!=null){
					endDate = common.getDateFromDateTime_(list.get(i).getEffectiveEndDate().toString()
							.replace(".0", "").substring(0, 10));
				ja.add(endDate);
				}
				else{
					ja.add(" ");
				}
				
				ja.add(list.get(i).getCurrentStatus());
				ja.add(list.get(i).getRemarks());
				/*ja.add(list.get(i).getNoofTrips());getEffectiveEndDate

								ja.add(list.get(i).getServicetype().getServiceTypeName());
				if(list.get(i).getIsTrunkSchedule().equals("Y")){
					ja.add("Yes");
				}
				else{
					ja.add("No");
				}
				
				ja.add("BMTC");
				ja.add(list.get(i).getScheduletype().getScheduleName());

				ja.add(list.get(i).getStatus());*/

				array.add(ja);
				
			}

			totalAfterFilter = getParialFormFourTotalRecords(search_term);

			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

			System.out.println("REsult ------>" + result);

		} catch (Exception e) {
			System.out.println("Exception---->" + e);
		}
		return result;
	}
	
	public int getFormFourTotalRecords(int id, String searchString) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt = 0;
		String sql = "";
		if(!(searchString.equals("") || searchString.equals(null))){
			sql = "select count(*) as count from form_four ff inner join form_four_type ft on ff.form_four_name = ft.form_four_type_id inner join route r on r.route_id = ff.route_id where schedule_number_id = "+id+" and ff.current_status = 'ACTIVE' and ff.deleted_status='0' and ";
			sql += "(";
			sql += " ft.form_four_type_name like '%"+searchString+"%'";
			sql += " OR ff.form_four_id = '"+searchString+"'";
			sql += " OR ff.no_of_trips = '"+searchString+"'";
			sql += " OR r.route_number like '%"+searchString+"%'";
			sql += " OR current_status like '%"+searchString+"%'";
			sql += ")";
		}
		else{
			sql = "select count(*) as count from form_four where schedule_number_id = "+id+" and current_status IN ('ACTIVE','Partial') and deleted_status='0'";
		}
		try{
		Query query = session.createSQLQuery(sql).addScalar("count",
				Hibernate.INTEGER);
		List<Integer> list = query.list();
		
		
		if(list.size()>0){
		cnt = list.get(0);
		}
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		
		return cnt;
	}
	
	public int getRationalizedFormFourTotalRecords(String searchString,int scheduleId) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt = 0;
		String sql = "";
		if(!(searchString.equals("") || searchString.equals(null))){
			sql = "select count(*) as count from form_four ff inner join form_four_type ft on ff.form_four_name = ft.form_four_type_id inner join route r on r.route_id = ff.route_id where ff.current_status = 'INACTIVE' and ";
			sql += "(";
			sql += " ft.form_four_type_name like '%"+searchString+"%'";
			sql += " OR ff.form_four_id = '"+searchString+"'";
			sql += " OR ff.no_of_trips = '"+searchString+"'";
			sql += " OR r.route_number like '%"+searchString+"%'";
			sql += " OR current_status like '%"+searchString+"%'";
			sql += ")";
		}
		else{
			sql = "select count(*) as count from form_four where current_status='INACTIVE' and schedule_number_id='"+scheduleId+"' " ;
		}
		try{
		Query query = session.createSQLQuery(sql).addScalar("count",
				Hibernate.INTEGER);
		List<Integer> list = query.list();
		
		
		if(list.size()>0){
		cnt = list.get(0);
		}
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		
		return cnt;
	}
	
	public int getParialFormFourTotalRecords(String searchString) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt = 0;
		String sql = "";
		if(!(searchString.equals("") || searchString.equals(null))){
			sql = "select count(*) as count from form_four ff " +
				" LEFT JOIN schedule s on s.schedule_id = ff.schedule_number_id " +
				" inner join form_four_type ft on ff.form_four_name = ft.form_four_type_id " +
				" inner join route r on r.route_id = ff.route_id where ff.current_status = 'Partial' and ";
			sql += "(";
			sql += " ft.form_four_type_name like '%"+searchString+"%'";
			sql += " OR ff.form_four_id = '"+searchString+"'";
			sql += " OR ff.no_of_trips = '"+searchString+"'";
			sql += " OR r.route_number like '%"+searchString+"%'";
			sql += " OR ff.current_status like '%"+searchString+"%'";
			sql += ")";
			if(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString().equals("3"))
			{
				int depotid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
				sql += "AND s.depot_id='"+depotid+"' ";
				
			}
			
		}
		else{
			sql = "  select count(*) as count from form_four  ff LEFT JOIN schedule s on s.schedule_id = ff.schedule_number_id where ff.current_status='Partial' ";
			if(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString().equals("3"))
			{
				int depotid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
				sql += "AND s.depot_id='"+depotid+"' ";
				
			}
		}
		try{
		Query query = session.createSQLQuery(sql).addScalar("count",
				Hibernate.INTEGER);
		List<Integer> list = query.list();
		
		
		if(list.size()>0){
		cnt = list.get(0);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		
		return cnt;
	}
	
	public List<FormFour> getFormFourList(int scheduleid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(FormFour.class);
		criteria.createAlias("scheduleNumber", "schno");
		criteria.add(Restrictions.eq("schno.schedule_id", scheduleid));
		List<FormFour> list = criteria.list();
		return list;
	}
	
	public List<ScheduleDetails> getScheduleDetailsList(int formfourid){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(ScheduleDetails.class);
		criteria.createAlias("formFour", "formfour");
		criteria.add(Restrictions.eq("formfour.id", formfourid));
		List<ScheduleDetails> list = criteria.list();
		return list;
	}
	
	
	
	public void saveScheduleDetails(ScheduleDetails details){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session.beginTransaction();
		session.save(details);
		session.getTransaction().commit();
		
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			/*if(session!=null){
                session.close();
            }*/
		}
	}
	
	public void permanentCurtail(int id){
		HttpServletRequest request=ServletActionContext.getRequest();
		int userid = (Integer)request.getSession().getAttribute("userid");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session.beginTransaction();
		Schedule sch = (Schedule) session.get(Schedule.class, id);
		sch.setCurrentStatus("Curtailed");
		sch.setStatus("INACTIVE");
		sch.setDeletedStatus(1);
		sch.setUpdatedBy(userid);
		sch.setUpdatedDate(new Date());
		session.update(sch);
		
		Query query = session.createSQLQuery("update form_four set current_status = 'INACTIVE', deleted_status = 1, updated_date = now(), updated_by="+userid+"  where schedule_number_id = "+id);
		query.executeUpdate();
		
		Query query1 = session.createSQLQuery("update schedule_details set deleted_status = 1, updated_date = now(), updated_by="+userid+"  where schedule_number = "+id);
		query1.executeUpdate();
		
		
		session.getTransaction().commit();
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		
	}
	
	public void temporaryCurtail(int id,String effectiveEndDate){
		HttpServletRequest request=ServletActionContext.getRequest();
		 int userid = (Integer)request.getSession().getAttribute("userid");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		session.beginTransaction();
		Schedule sch = (Schedule) session.get(Schedule.class, id);
		Date effEnddate = sch.getEffectiveEndDate();
		sch.setEffectiveEndDate(common.getDate(effectiveEndDate));
		sch.setCurrentStatus("Curtailed");
		sch.setStatus("INACTIVE");
		sch.setDeletedStatus(1);
		
		Schedule sch1 = new Schedule();
		sch1.setScheduleNumber(sch.getScheduleNumber());
		
		BrandType brandType = sch.getBrand();
		sch1.setBrand(brandType);
		sch1.setStatus("NEW");
		sch1.setEffectiveStartDate(common.getIncrementDate(common.getDate(effectiveEndDate), 1));
		if(sch.getEffectiveEndDate()!=null){
			sch1.setEffectiveEndDate(effEnddate);
		}
		sch1.setDepot(sch.getDepot());
		sch1.setScheduletype(sch.getScheduletype());
		sch1.setServicetype(sch.getServicetype());
		sch1.setCreatedBy(sch.getCreatedBy());
		sch1.setCreatedDate(sch.getCreatedDate());
		sch1.setUpdatedBy(userid);
		sch1.setUpdatedDate(new Date());
		sch1.setDeletedStatus(0);
		sch1.setIsTrunkSchedule(sch.getIsTrunkSchedule());
		sch1.setRemarks(sch.getRemarks());
		sch1.setCurrentStatus("CASE WORKER");
		
		session.update(sch);
		session.save(sch1);
		session.getTransaction().commit();
		
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		session1.beginTransaction();
		Query query = session.createQuery("from Schedule where status = 'NEW' and deletedStatus = '0' and scheduleNumber = '"+sch.getScheduleNumber()+"'");
		List<Schedule> list = query.list();
		if(list.size()>0){
			System.out.println("list.get(0).getSchedule_id() -----> "+list.get(0).getSchedule_id()+ "   id -------> "+id);
			String sql = "UPDATE form_four a  SET a.schedule_number_id='"+list.get(0).getSchedule_id()+"' WHERE a.schedule_number_id='"+id+"'";
			Query query1 = session1.createSQLQuery(sql);
			query1.executeUpdate();
			
			sql = "UPDATE schedule_details a  SET a.schedule_number='"+list.get(0).getSchedule_id()+"' WHERE a.schedule_number='"+id+"'";
			Query query2 = session.createSQLQuery(sql);
			query2.executeUpdate();
			
			
		}
		session1.getTransaction().commit();
		/*Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		session1.beginTransaction();
		Schedule sch1 = (Schedule) session1.get(Schedule.class, id);
		sch1.setSchedule_id(null);
		sch1.setEffectiveStartDate(common.getIncrementDate(sch1.getEffectiveStartDate(), 1));
		sch1.setCurrentStatus("CASE WORKER");
		sch1.setStatus("NEW");
		sch1.setDeletedStatus(0);
		session1.saveOrUpdate(sch1);
		session1.getTransaction().commit();*/
		}
		catch(Exception e){
			session.getTransaction().rollback();
		}
		finally{
			if(session!=null){
                session.close();
            }
		}
		
	}
	
public String getBrandtype(String serviceTypeId){
//		System.out.println("Inside DAO");
		String data="";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			
			Query query = session.createQuery("from BrandType b where b.status = 'ACTIVE' and b.deletedStatus = '0' and b.serviceType.service_type_id = "+serviceTypeId);
			List<BrandType> list = query.list();
			for(BrandType result : list){
				data  += result.getBrand_type_id()+"@";
				data  += result.getBrand_type_name()+",";
			}
		/*String sql="select rp.bus_stop_id as busStopid,bs.bus_stop_name as busStopName from bus_stop bs " +
				" inner join route_point rp on bs.bus_Stop_id = rp.bus_stop_id " +
				" where route_id = "+routeno ;
		Query query = session.createSQLQuery(sql).addScalar("busStopid", Hibernate.STRING).addScalar("busStopName", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(Route_Trans.class));
		List<Route_Trans> list = query.list();
		for(Route_Trans result: list){
			data  += result.getBusStopid()+"@";
			data  += result.getBusStopName()+",";
		}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			/*if(session!=null){
                session.close();
            }*/
		}
		return data;
	}
public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException e) { 
        return false; 
    }
    // only got here if we didn't return false
    return true;
}

public int updateFormFourRelatedSchedule(int newSchid, int oldSchid){
	int cnt = 0;
	String sql = "update form_four set schedule_number_id = "+newSchid+ " where schedule_number_id = "+oldSchid;
	String sql1 = "update schedule_details set schedule_number = "+newSchid +" where schedule_number = "+oldSchid;
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	Query query = session.createSQLQuery(sql);
	Query query1 = session.createSQLQuery(sql1);
	cnt = query.executeUpdate();
	cnt = query1.executeUpdate();
	session.getTransaction().commit();
	return cnt;
}

/*public List<FormFour> getFormFourList*/
//insert schedule tartget amount
public void insertScheduleTargetAmount(int scheduleid,String targetamount,String d)

{
	Common common=new Common();
//	System.out.println("date"+d);
	String updatedate=common.getDateFromPicker(d);
	int cnt=0;
	try{
	String sql1 = "insert into  scheduletargetamount(scheduleid,targetamount,effective_start_date) values("+scheduleid+",'"+targetamount+"','"+updatedate+"')";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	//Query query = session.createSQLQuery(sql);
	Query query1 = session.createSQLQuery(sql1);
	cnt = query1.executeUpdate();
	
	session.getTransaction().commit();	
	}catch(Exception e)
	{
		e.printStackTrace();
	}
}
//end insert schedule tartget amount
//update target Amount
public void UpdateScheduleTargetAmountt(String targetamount,int scheduleid,String d) throws ParseException
{
	Common common=new Common();
	
	int cnt=0;
	Date date=common.getDateFromDatePicker(d);
//	System.out.println("date///"+date);
	Date previous=common.getIncrementDate(date, -1);
	String a=previous.toString();
	
//	System.out.println("previous///"+previous);
//	System.out.println("a///"+a);
	String updatedate=common.getDatefromString(a);
	System.out.println("updatedate///"+updatedate);
	String sql1 = "update scheduletargetamount  set deletedstatus =1,effective_end_date='"+updatedate+"',targetamount='"+targetamount+"' where scheduleid = '"+scheduleid+"' and deletedstatus=0"  ;
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	Query query = session.createSQLQuery(sql1);
	//Query query1 = session.createSQLQuery(sql1);
	cnt = query.executeUpdate();
	
	session.getTransaction().commit();	
}
//update target amount end
//get Target Amount
public String  getScheduleTargetAmount(int ld)
{
	int cnt=0;
	String targetamount=null;
	List<Map<String,String>> lists = null;
	String query = "select  targetamount  from scheduletargetamount  where scheduleid = '"+ld+"' and deletedstatus=0 and (effective_end_date >= DATE_FORMAT(now(),'%Y-%m-%d') or effective_end_date ='0000-00-00' or effective_end_date is null)";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	//Query query = session.createSQLQuery(sql1);
	//Query query1 = session.createSQLQuery(sql1);
	//cnt = query.executeUpdate();
	
	Query queryobjct=session.createSQLQuery(query).addScalar("targetamount", Hibernate.STRING);
	queryobjct.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//	System.out.println("qry foir target ===="+queryobjct);
	lists=queryobjct.list();
	if(lists.size()>0){
		Map<String,String> resultSet = lists.get(0);
		//System.out.println(resultSet.get("targetamount"));
		targetamount=resultSet.get("targetamount");
	}
	
	session.getTransaction().commit();	
	return targetamount;
}
//end target Amount

public String  getServiceLimit(int ld)
{
	int cnt=0;
	String result=null;
	List<Map<String,String>> lists = null;
	Session session=null;
	try{
	
	String query = "select  service_limit  from schedule_service_limit  where schedule_id = "+ld+" ";
	 session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	
	Query queryobjct=session.createSQLQuery(query).addScalar("service_limit", Hibernate.STRING);
	queryobjct.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	lists=queryobjct.list();
	if(lists.size()>0){
		Map<String,String> resultSet = lists.get(0);
		//System.out.println(resultSet.get("targetamount"));
		result=resultSet.get("service_limit");
		session.getTransaction().commit();	
	}
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}finally{
		if(session!=null){
			session.close();
		}
	}
	
	return result;
}


//saveFormFourInNewschedule
public void saveFormFourInNewschedule(Schedule sch , int schId,int oldSchID){
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	String formFoursql = "INSERT INTO `form_four` (`form_four_name`, `schedule_number_id`, `schedule_number_name`, `no_of_trips`, `start_time`, `route_id`, `route_number`, `toll_zone`, `area_limit`, `total_km`, `total_dead_km`, `actual_km`, `total_running_time`, `total_break_time`, `total_steering_time`, `spread_over_hours`, `ot_hours`, `sign_on`, `sign_off`, `spread1`, `spread2`, `steering1`, `steering2`, `ot1`, `ot2`, `rest1`, `rest2`, `effective_start_date`, `effective_end_date`, `created_date`, `created_by`, `updated_by`, `updated_date`, `stotal_dead_km`, `sactual_km`, `stotal_steering_time`, `sspread_over_hours`, `sot_hours`, `deleted_status`, `current_status`, `remarks`, `sync_updated_date`) " +
			"SELECT `form_four_name`, "+schId+", `schedule_number_name`, `no_of_trips`, `start_time`, `route_id`, `route_number`, `toll_zone`, `area_limit`, `total_km`, `total_dead_km`, `actual_km`, `total_running_time`, `total_break_time`, `total_steering_time`, `spread_over_hours`, `ot_hours`, `sign_on`, `sign_off`, `spread1`, `spread2`, `steering1`, `steering2`, `ot1`, `ot2`, `rest1`, `rest2`, `effective_start_date`, `effective_end_date`, now(), "+sch.getCreatedBy()+", 0, null, `stotal_dead_km`, `sactual_km`, `stotal_steering_time`, `sspread_over_hours`, `sot_hours`, `deleted_status`, 'Partial', `remarks`, now() " +
			"FROM `form_four` " +
			"WHERE (`schedule_number_id` = "+oldSchID+")";
	
	try{
		session.beginTransaction();
		Query query = session.createSQLQuery(formFoursql);
		query.executeUpdate();
		session.getTransaction().commit();
		
		String sql = "select form_four_id from form_four where `schedule_number_id` = "+oldSchID;
		query = session.createSQLQuery(sql);
		List<Integer> oldformFourList = query.list();
		
		sql = "select form_four_id from form_four where `schedule_number_id` = "+schId;
		query = session.createSQLQuery(sql);
		List<Integer> newformFourList = query.list();
		
		for(int i=0;i<newformFourList.size();i++){
			sql = "INSERT INTO `schedule_details` (`form_four_id`, `schedule_number`, `number_of_trips`, `list_item_number`, `trip_number`, `customer_id`, `trip_type`, `start_point`, `end_point`, `route_number_id`, `route_number`, `route_direction`, `distance`, `start_time`, `end_time`, `running_time`, `break_type_id`, `break_time`, `crew_change`, `night_halt`, `shift_type_id`, `is_dread_trip`, `crew_change_location`, `night_halt_location`, `break_location`, `operation_type`, `remarks`, `created_by`, `created_date`, `updated_by`, `updated_date`, `deleted_status`, `sync_updated_date`) " +
					"SELECT "+newformFourList.get(i)+", `schedule_number`, `number_of_trips`, `list_item_number`, `trip_number`, `customer_id`, `trip_type`, `start_point`, `end_point`, `route_number_id`, `route_number`, `route_direction`, `distance`, `start_time`, `end_time`, `running_time`, `break_type_id`, `break_time`, `crew_change`, `night_halt`, `shift_type_id`, `is_dread_trip`, `crew_change_location`, `night_halt_location`, `break_location`, `operation_type`, `remarks`, `created_by`, `created_date`, `updated_by`, `updated_date`, `deleted_status`, `sync_updated_date` " +
					"FROM `schedule_details` " +
					"WHERE (`form_four_id` = '"+oldformFourList.get(i)+"')";
			session.beginTransaction();
			query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
//saveFormFourInNewschedule end
//get updated start date of edit target//rajesh
public String  getScheduleStartDate(int ld)
{
	int cnt=0;
	String targetamount=null;
	List<Map<String,String>> lists = null;
	String query = "select  effective_start_date  from scheduletargetamount  where scheduleid = '"+ld+"' and deletedstatus=0";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	//Query query = session.createSQLQuery(sql1);
	//Query query1 = session.createSQLQuery(sql1);
	//cnt = query.executeUpdate();
	
	Query queryobjct=session.createSQLQuery(query).addScalar("effective_start_date", Hibernate.STRING);
	queryobjct.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	lists=queryobjct.list();
	if(lists.size()>0){
		Map<String,String> resultSet = lists.get(0);
		//System.out.println(resultSet.get("targetamount"));
		targetamount=resultSet.get("effective_start_date");
	}
	
	session.getTransaction().commit();	
	return targetamount;
}
//end 

public String getUserLoginName(Session session,int emp_id)
{
	String result = "";
	try {
		String sqlqry = "SELECT userloginname FROM menu_user_master WHERE user_id in( "
				+ emp_id + ")";
		
		Query query = session.createSQLQuery(sqlqry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query
				.list();

		for (int i = 0; i < aliasToValueMapList.size(); i++) {
			Map<String, Object> rs = aliasToValueMapList.get(i);

			if (rs.size() == 1) {
				result = rs.get("userloginname").toString();
			}
		}
	

	} catch (Exception e) {
		e.printStackTrace();
	} /*finally {
		if (session1 != null) {
			session1.close();
		}
	}*/
	
	return  result;
}



public void UpdateScheduleTargetAmounttchanges(String targetamount,int scheduleid,String d) throws ParseException
{
	Common common=new Common();
	
	int cnt=0;
	Date date=common.getDateFromDatePicker(d);
	System.out.println("date///"+date);
	Date previous=common.getIncrementDate(date, -1);
	String a=previous.toString();
	
	System.out.println("previous///"+previous);
	System.out.println("a///"+a);
	String updatedate=common.getDatefromString(a);
	System.out.println("updatedate///"+updatedate);
//	String sql1 = "update scheduletargetamount  set deletedstatus =1,effective_end_date='"+updatedate+"' where scheduleid = '"+scheduleid+"' and deletedstatus=0"  ;
	String sql1 = "update scheduletargetamount  set effective_end_date='"+updatedate+"' where scheduleid = '"+scheduleid+"' and deletedstatus=0"  ;
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	Query query = session.createSQLQuery(sql1);
	//Query query1 = session.createSQLQuery(sql1);
	cnt = query.executeUpdate();
	
	session.getTransaction().commit();	
}


public void insertScheduleTargetAmountchanges(int scheduleid,String targetamount,String d)

{
	Common common=new Common();
	System.out.println("date"+d);
	String updatedate=common.getDateFromPicker(d);
	int cnt=0;
	try{
	String sql1 = "insert into  scheduletargetamount(scheduleid,targetamount,effective_start_date) values("+scheduleid+",'"+targetamount+"','"+updatedate+"')";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	//Query query = session.createSQLQuery(sql);
	Query query1 = session.createSQLQuery(sql1);
	cnt = query1.executeUpdate();
	
	session.getTransaction().commit();	
	}catch(Exception e)
	{
		e.printStackTrace();
	}
}

public void updateTargetAmountBasedOnSchedule(String targetamount ,int scheduleid) throws ParseException
{
	Common common=new Common();
	
	int cnt=0;
	//Date date=common.getDateFromDatePicker(d);
//	System.out.println("date///"+date);
//	Date previous=common.getIncrementDate(date, -1);
//	String a=previous.toString();
	
	
//	String sql1 = "update scheduletargetamount  set deletedstatus =1,effective_end_date='"+updatedate+"' where scheduleid = '"+scheduleid+"' and deletedstatus=0"  ;
	String sql1 = "update schedule  set target_revenue_status='"+targetamount+"' where schedule_id = '"+scheduleid+"' and 	deleted_status=0"  ;
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	session.beginTransaction();
	Query query = session.createSQLQuery(sql1);
	//Query query1 = session.createSQLQuery(sql1);
	cnt = query.executeUpdate();
	
	session.getTransaction().commit();	
}


public String getScheduleName(int schId) {

	String result="";
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	try{
	Query query = session.createSQLQuery("SELECT schedule_number FROM schedule WHERE schedule_id = "+schId+" AND deleted_status = '0' ");
	
	result=query.uniqueResult().toString();
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
	finally{
		session.close();
	}
	return result;

}

public int getScheduleId(String schName) {

	int result=0;
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	try{
	Query query = session.createSQLQuery("SELECT schedule_id FROM schedule WHERE schedule_number = '"+schName+"' AND deleted_status = '0' and status='New' ");
	
	result=(Integer)query.uniqueResult();
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
	finally{
		session.close();
	}
	return result;

}


}
