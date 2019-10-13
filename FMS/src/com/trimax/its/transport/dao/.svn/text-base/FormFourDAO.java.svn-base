
package com.trimax.its.transport.dao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.naming.java.javaURLContextFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.FormFourType;
import com.trimax.its.transport.model.RouteNoUtil;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;

public class FormFourDAO {

	public Map<Integer, String> getScheduleNumber() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session.createSQLQuery(
					"SELECT `schedule_id`, `schedule_number` as scheduleNumber FROM `schedule` where status = 'NEW' and deleted_status = '0'")
					.addScalar("schedule_id", Hibernate.INTEGER).addScalar("scheduleNumber", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(Schedule.class));
			List<Schedule> list = query.list();
			resultMap.put(0, "Select");
			System.out.println("Size of Schedule List : " + list.size());
			for (Schedule type : list) {
				resultMap.put(type.getSchedule_id(), type.getScheduleNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
	}

	@SuppressWarnings("finally")
	public boolean checkValidRouteNO(String routeId, String routeNo) {
		System.out.println("--------------checkValidRouteNO---------------");
		boolean isSuccess = false;
		Session session = null;
		Common common = new Common();
		try {
			String routeNoArray[] = routeNo.split("-");
			session = HibernateUtil.getSession("");
			String queryToCheck = "SELECT count(*) AS count FROM route r WHERE r.route_id='" + routeId
					+ "' AND route_number ='" + routeNoArray[0] + "' AND route_direction='" + routeNoArray[1] + "'";
			int count = common.getDBResultInt(session, queryToCheck, "count");
			if (count == 1) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}
		} catch (Exception e) {
			isSuccess = false;
		} finally {
			if (session != null) {
				session.close();
			}
			return isSuccess;
		}

	}

	public Map<Integer, String> getFormFourName() {
		System.out.println("--------------getFormFourName---------------");
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		try {
			Query query = session.createQuery("from FormFourType where status = 'ACTIVE' and deleted_status = '0'");
			List<FormFourType> list = query.list();
			resultMap.put(0, "Select");
			for (FormFourType type : list) {
				resultMap.put(type.getId(), type.getFormFourTypeName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
	}

	public Map<Integer, String> getRoute() {
		System.out.println("--------------getRoute---------------");
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		try {
			Query query = session.createSQLQuery(
					"select concat(route_number,'(',route_direction,')')  as routeNumber,route_id,route_direction as routeDirection from route "
							+ "where if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), "
							+ "effective_till) >= CURDATE() and status='ACTIVE' and deleted_status='0' ")
					.addScalar("routeNumber", Hibernate.STRING).addScalar("route_id", Hibernate.INTEGER)
					.addScalar("routeDirection", Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(Route_Trans.class));
			List<Route_Trans> list = query.list();

			resultMap.put(0, "Select");
			for (Route_Trans type : list) {
				resultMap.put(type.getRoute_id(), type.getRouteNumber());
			}
			/*
			 * for (Route_Trans type : list) { resultMap.put(type.getRoute_id(),
			 * type.getRouteNumber() + " (" + type.getRouteDirection() + ")"); }
			 */
		} catch (Exception e) {
			/* session.getTransaction().rollback(); */
		} finally {

			if (session != null) {
				session.close();
			}

		}
		return resultMap;
	}

	@SuppressWarnings("finally")
	public List<RouteNoUtil> getRouteNoList(String... roouteNoContains) {
		System.out.println("--------------getRouteNoList---------------");
		List<Map<String, String>> aliasToValueMapList = null;
		List<RouteNoUtil> routeNoList = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
			/*
			 * Criteria criteria = session.createCriteria(BodyType.class);
			 * 
			 * if (waybillNumberContains.length > 0) { if
			 * (!waybillNumberContains[0].equals("")) {
			 * criteria.add(Restrictions.like("body_type_name", "%"+
			 * waybillNumberContains[0] + "%")); } }
			 * criteria.addOrder(Order.asc("body_type_name")); List<Object> det =
			 * criteria.list();
			 */
			routeNoList = new ArrayList<RouteNoUtil>();

//			Query query = session
//					.createSQLQuery(
//							"select concat(route_number,'(',route_direction,')')  as routeNumber,route_id,route_direction as routeDirection from route " +
//							"where if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), " +
//							"effective_till) >= CURDATE() and status='ACTIVE' and deleted_status='0' and route_number like '%"+roouteNoContains+"%'")
//					.addScalar("routeNumber", Hibernate.STRING)
//					.addScalar("route_id", Hibernate.INTEGER)
//					.addScalar("routeDirection", Hibernate.STRING);
//			query.setResultTransformer(Transformers
//					.aliasToBean(Route_Trans.class));
//			List<Route_Trans> list = query.list();

			Query queryObject = session.createSQLQuery(
					"select concat(route_number,'-',route_direction)  as routeNumber,route_id,route_direction as routeDirection from route "
							+ "where if(effective_till IS NULL or effective_till = '0000-00-00 00:00:00', CURDATE(), "
							+ "effective_till) >= CURDATE() and status='ACTIVE' and deleted_status='0' and route_number like '%"
							+ roouteNoContains[0] + "%'")
					.addScalar("routeNumber", Hibernate.STRING).addScalar("route_id", Hibernate.INTEGER)
					.addScalar("routeDirection", Hibernate.STRING);
			queryObject.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			// RouteNoUtil routeList = new RouteNoUtil();
			// routeList.setRouteNo("0");
			// routeList.setRouteId("Select");
			// routeNoList.add(routeList);

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				RouteNoUtil route1 = new RouteNoUtil();
				route1.setRouteId(String.valueOf(aliasToValueMapList.get(i).get("route_id")));
				route1.setRouteNo(aliasToValueMapList.get(i).get("routeNumber"));
				routeNoList.add(route1);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
			return routeNoList;
		}
	}

	public int saveFormFour(FormFour formfour) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			session.beginTransaction();
			session.save(formfour);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {

			/* if(session1!=null){ session1.close(); } */

		}
		return formfour.getId();
	}

	public String getScheduleNumber(int id) {
		System.out.println("--------------getScheduleNumber---------------");
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		List<Schedule> list = new ArrayList<Schedule>();
		try {
			Query query = session.createQuery("from Schedule where id='" + id + "'");
			list = query.list();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list.get(0).getScheduleNumber();
	}

	public String getFormFourTypeName(int id) {
		System.out.println("--------------getFormFourTypeName---------------");
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		List<FormFourType> list = new ArrayList<FormFourType>();
		try {
			Query query = session.createQuery("from FormFourType where id='" + id + "' and deleted_status = '0'");
			list = query.list();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list.get(0).getFormFourTypeName();
	}

	public FormFour getFormFour(int id) {
		System.out.println("--------------getFormFour---------------");
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		FormFour formfour;
		try {
			formfour = (FormFour) session.get(FormFour.class, new Integer(id));

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return formfour;
	}

	public FormFourType getFormFourType(int id) {
		System.out.println("--------------getFormFourType---------------");
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		FormFourType formfour;
		try {
			formfour = (FormFourType) session.get(FormFourType.class, new Integer(id));

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return formfour;
	}

	public FormFour updateFormFour(FormFour formfour) {
		int id = 0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		FormFour formfour1 = new FormFour();
		Transaction transaction = null;
		String sql = "";
		int count = 0;
		try {
			id = formfour.getId();
			System.out.println("Id in update form four------> " + id);
			formfour1 = (FormFour) session.get(FormFour.class, id);

			Schedule sch = new Schedule();
			sch.setSchedule_id(formfour.getScheduleNumber().getSchedule_id());
			formfour1.setScheduleNumber(sch);

			FormFourType formfourtype = new FormFourType();
			formfourtype.setId(formfour.getFormFourType().getId());
			formfour1.setFormFourType(formfourtype);

			formfour1.setNoofTrips(formfour.getNoofTrips());

			Route_Trans route = new Route_Trans();
			route.setRoute_id(formfour.getFormFourRoute().getRoute_id());
			formfour1.setFormFourRoute(route);

			formfour1.setEffectiveStartDate(formfour.getEffectiveStartDate());
			formfour1.setEffectiveEndDate(formfour.getEffectiveEndDate());

			formfour1.setTollZone(formfour.getTollZone());
			formfour1.setCurrentStatus(formfour.getCurrentStatus());
			formfour1.setRemarks(formfour.getRemarks());
			formfour1.setUpdatedBy(formfour.getUpdatedBy());
			formfour1.setUpdatedDate(formfour.getUpdatedDate());
			formfour1.setStartTime(formfour.getStartTime());
			formfour1.setScheduleNumberName(formfour.getScheduleNumberName());
			formfour1.setTraffic_order_no(formfour.getTraffic_order_no());
			formfour1.setRecordDate(formfour.getRecordDate());
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return formfour1;
	}

	public int checkFormFour(FormFour formfour, String dateString) {
		System.out.println("--------------checkFormFour---------------");
		System.out.println("inside dao check formfour");
		int cnt = 0;
		String sql = "select count(*) as count from form_four where form_four_name = "
				+ formfour.getFormFourType().getId() + " and schedule_number_id = "
				+ formfour.getScheduleNumber().getSchedule_id() + " and '" + dateString
				+ "' between effective_start_date and if(effective_end_date IS NULL or effective_end_date = '0000-00-00 00:00:00', effective_start_date, effective_end_date) and deleted_status =0 and current_status IN ('ACTIVE','Partial')";
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		try {
			Query query = session.createSQLQuery(sql).addScalar("count", Hibernate.INTEGER);
			cnt = (Integer) query.uniqueResult();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}

	public int checkFormFourCount(int scheduleId) {
		System.out.println("--------------checkFormFourCount---------------");
		int cnt = 0;
		String sql = "select count(*) as count from form_four where schedule_number_id = " + scheduleId
				+ " and current_status = 'ACTIVE'";
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		try {
			Query query = session.createSQLQuery(sql).addScalar("count", Hibernate.INTEGER);
			cnt = (Integer) query.uniqueResult();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}

	public void deleteFormFour(int formfourid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			session.beginTransaction();
			FormFour formfour = (FormFour) session.get(FormFour.class, formfourid);
			formfour.setCurrentStatus("INACTIVE");
			formfour.setDeletedStatus(1);
			session.update(formfour);

			Query query = session.createSQLQuery(
					"update schedule_details set deleted_status = 1 where form_four_id = " + formfourid);
			query.executeUpdate();

			Query query1 = session.createSQLQuery(
					"update weeklyChart set deleted_status = 1 ,status = 'INACTIVE' where form_four_id = "
							+ formfourid);
			query1.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public int checkStartDate(String startDate, int id) {
		System.out.println("--------------checkStartDate---------------");
		int result = 0;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		try {
			Query query = session.createSQLQuery(
					"select DATE_FORMAT(effective_start_date,'%d-%m-%Y') as effectivedate from schedule where schedule_id= "
							+ id)
					.addScalar("effectivedate", Hibernate.STRING);
			List<String> resultList = query.list();
			System.out.println("size =-------> " + resultList.size());
			String effectivedate = resultList.get(0);
			System.out.println("effectivedate ----> " + effectivedate);
			result = common.compareDates(startDate, effectivedate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	public int checkEndDate(String endDate, int id) {
		System.out.println("--------------checkEndDate---------------");
		int result = 0;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		try {
			Query query = session.createSQLQuery(
					"select DATE_FORMAT(effective_end_date,'%d-%m-%Y') as effectivedate from schedule where schedule_id= "
							+ id)
					.addScalar("effectivedate", Hibernate.STRING);
			List<String> resultList = query.list();
			System.out.println("size =-------> " + resultList.size());
			String effectivedate = resultList.get(0);
			System.out.println("effectivedate ----> " + effectivedate);
			if (effectivedate != null) {
				result = common.compareDates(endDate, effectivedate);
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	public String setEnddateofprevFormFour(FormFour formfour) {
		Common common = new Common();
		String formid = "";
		String effectiveStartDate = "";
		String effectiveEndDate = "";
		String resultDate = "-";
		int j = 0;
		String sql = "select form_four_id as id,effective_start_date,if(effective_end_date is null,'-',effective_end_date) as effective_end_date from form_four where form_four_name = "
				+ formfour.getFormFourType().getId() + " and schedule_number_id = "
				+ formfour.getScheduleNumber().getSchedule_id() + " and current_status in ('ACTIVE','Partial')";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		Query query = session.createSQLQuery(sql).addScalar("id", Hibernate.STRING)
				.addScalar("effective_start_date", Hibernate.STRING).addScalar("effective_end_date", Hibernate.STRING);
		List result = query.list();
		/*
		 * if(result.size()>0){ String formid = result.get((result.size()-1)); FormFour
		 * form = (FormFour) session.load(FormFour.class, Integer.parseInt(formid));
		 * form.setEffectiveEndDate(common.getIncrementDate(formfour.
		 * getEffectiveStartDate(), -1)); session.update(form); }
		 */

		for (Iterator i = result.iterator(); i.hasNext();) {
			Object[] obj = (Object[]) i.next();
			formid = obj[0].toString().trim();
			effectiveStartDate = obj[1].toString().trim();
			effectiveEndDate = obj[2].toString().trim();
			if (effectiveEndDate.equals("-")) {
				if (formfour.getEffectiveStartDate().compareTo(common.getDateFromApi(effectiveStartDate)) > 0) {
					FormFour form = (FormFour) session.load(FormFour.class, Integer.parseInt(formid));
					form.setEffectiveEndDate(common.getIncrementDate(formfour.getEffectiveStartDate(), -1));
					session.update(form);
				}

			}
			if (formfour.getEffectiveStartDate().compareTo(common.getDateFromApi(effectiveStartDate)) < 0) {
				resultDate = effectiveEndDate;
			}

		}
		session.getTransaction().commit();
		if (session != null) {
			session.close();
		}
		return resultDate;
	}

	public void activateFormFour(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		FormFour formfour = (FormFour) session.get(FormFour.class, new Integer(id));
		formfour.setCurrentStatus("ACTIVE");
		session.update(formfour);
		session.getTransaction().commit();
		session.close();
	}

	public int validateTripDetails(int id) {
		int result = 0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			String sql = "select * from schedule_details where form_four_id = '" + id
					+ "' and (start_point=0 || end_point =0 || route_number_id =0) and deleted_status=0";
			Query query = session.createSQLQuery(sql);
			List<Object> resultList = query.list();
			if (resultList.size() > 0) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public int checkWeeklyChartCount(int formFourId) {

		int cnt = 0;
		String sql = "select count(*) as count from weeklyChart where form_four_id = " + formFourId
				+ " and status = 'ACTIVE' and deleted_status='0'";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session.createSQLQuery(sql).addScalar("count", Hibernate.INTEGER);
			cnt = (Integer) query.uniqueResult();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}
	/* public int */

	// check route exit
	public int checkRouteExit(int routeid, int serviceid) {
		int cnt = 0;

		String sql = "select * from farechart_master where route_id=" + routeid + " and service_type_id=" + serviceid
				+ " and if(effect_end_date is null,now(),effect_end_date)>=now()";
		System.out.println("sql" + sql);
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session.createSQLQuery(sql);
			List<Object> farelist = query.list();
			System.out.println("list size" + farelist.size());
			if (farelist.size() > 0) {
				cnt = 1;
			} else {
				cnt = 0;

			}

		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}

	// end route Exit
//	getServiceTypeIDByScheduleid
	public int getServiceTypeIDByScheduleid(int schedule_id) {
		System.out.println("--------------getServiceTypeIDByScheduleid---------------");
		int serviceid = 0;

		List<Schedule> list = new ArrayList<Schedule>();
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");

		try {
			Query query = session
					.createQuery("from Schedule where schedule_id='" + schedule_id + "' and deleted_status = '0'");
			list = query.list();
			if (list.size() > 0) {
				serviceid = list.get(0).getServicetype().getService_type_id();
			}

		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return serviceid;
	}

//	end
//24-07-2015
//UpdatedOldFormFour
	public void UpdatedOldFormFour(FormFour formfour, String pdate) {

		System.out.println("update old form four");
		Common common = new Common();
		Session session = null;
		String status = null;
		try {
			java.util.Date previous = common.getIncrementDate(formfour.getEffectiveStartDate(), -1);
			System.out.println("previous" + previous);
			java.util.Date d = new java.util.Date();
			String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
			String updatedate = common.getDatefromString(previous.toString());
			System.out.println("formatDate" + formatDate + "updatedate:" + updatedate);
//		if(common.compareDatesBrandEdit(updatedate,formatDate) >= 0){
//			//if(common.compareOnlyDate(common.getDate(updatedate)) == -1){
//			
//			 status="current_status='Partial',deleted_status='0'";
//			
//			}
//		else{

			status = "current_status='Inactive',deleted_status='1'";
			// }
			int formid = formfour.getId();

			System.out.println("updatedate----------->" + updatedate);
			// start 31-07-015

			// end
			// formfour.setEffectiveEndDate(common.getDateFromApi(updatedate));
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql = "update form_four set effective_end_date='" + updatedate + "' , effective_start_date='" + pdate
					+ "'," + status + " where form_four_id=" + formid;
			Query query = session.createSQLQuery(sql);
			int a = query.executeUpdate();
			// session.update(formfour);
			session.getTransaction().commit();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
		}
	}

//	public static void main(String ar[])
//	{
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		 java.util.Date myDate = dateFormat.parse("2015-");
//			java.util.Date  previous= new Date(myDate.getTime() - 2);
//			System.out.println("previous"+previous);
//			//String updatedate=previous;
//	}
	public void UpdatedFormFourEndDate(int formFourID, String updatedate, String pdate, int chkStats) {

		System.out.println("form four dao update end date");
		Common common = new Common();
		Session session = null;
		String status = null;
		try {
			if (chkStats == 1) {
				status = "current_status='Inactive',deleted_status='1'";
			} else {
				status = "current_status='ACTIVE',deleted_status='0'";
			}
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql = "update form_four set effective_end_date='" + updatedate + "' , effective_start_date='" + pdate
					+ "'," + status + " where form_four_id=" + formFourID;
			Query query = session.createSQLQuery(sql);
			int a = query.executeUpdate();
			// session.update(formfour);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
		}
	}

	public void UpdatedOldFormFourActive(FormFour formfour1, String updatedDate, String pdate, String edate) {
		Common common = new Common();
		Session session = null;
		String status = null;
		try {

//		if(common.compareDatesBrandEdit(updatedate,formatDate) >= 0){
//			//if(common.compareOnlyDate(common.getDate(updatedate)) == -1){
//			
//			 status="current_status='Partial',deleted_status='0'";
//			
//			}
//		else{
			if (common.compareOnlyDate(common.getDate(edate)) == -1
					|| common.compareOnlyDate(common.getDate(edate)) == 0) {
				status = "current_status='Inactive',deleted_status='1'";
				// updatedDate =;
			} else {
				status = "current_status='ACTIVE',deleted_status='0'";
			}
			// }
			int formid = formfour1.getId();

			// start 31-07-015

			// end
			// formfour.setEffectiveEndDate(common.getDateFromApi(updatedate));
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql = "update form_four set effective_end_date=DATE_ADD('" + updatedDate
					+ "', INTERVAL -1 DAY) , effective_start_date='" + pdate + "'," + status + " where form_four_id="
					+ formid;
			System.out.println("Sql Query" + sql);
			Query query = session.createSQLQuery(sql);
			int a = query.executeUpdate();
			// session.update(formfour);
			session.getTransaction().commit();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
		}
	}

	public void UpdatedOldFormFourActive(FormFour formfour, String pdate, int id) {
		Common common = new Common();
		Session session = null;
		String status = null;
		try {
			java.util.Date previous = common.getIncrementDate(formfour.getEffectiveStartDate(), -1);
			System.out.println("previous" + previous);
			java.util.Date d = new java.util.Date();
			String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
			String updatedate = common.getDatefromString(previous.toString());
			System.out.println("formatDate" + formatDate + "updatedate:" + updatedate);
//		if(common.compareDatesBrandEdit(updatedate,formatDate) >= 0){
//			//if(common.compareOnlyDate(common.getDate(updatedate)) == -1){
//			
//			 status="current_status='Partial',deleted_status='0'";
//			
//			}
//		else{

			status = "current_status='Inactive',deleted_status='1'";
			// }
			int formid = formfour.getId();

			System.out.println("updatedate----------->" + updatedate);
			// start 31-07-015

			// end
			// formfour.setEffectiveEndDate(common.getDateFromApi(updatedate));
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql = "update form_four set effective_end_date='" + updatedate + "' , effective_start_date='" + pdate
					+ "'," + status + " where form_four_id=" + formid;
			Query query = session.createSQLQuery(sql);
			int a = query.executeUpdate();
			// session.update(formfour);
			session.getTransaction().commit();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();
		}
	}

//update old formfour end	
//savenewFormFour
	public int SaveNewFormFour(FormFour formfour, FormFour formfour1) {
		int newformid = 0;

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			// FormFour newFormfoura=(FormFour) session.load(FormFour.class,
			// formfour.getId());

			FormFour newFormfour = new FormFour();
			newFormfour.setActualKm(formfour.getActualKm());
			newFormfour.setAreaLimit(formfour.getAreaLimit());
			newFormfour.setCreatedBy(formfour.getCreatedBy());
			newFormfour.setCreatedDate(formfour.getCreatedDate());
			newFormfour.setCurrentStatus(formfour.getCurrentStatus());
			// newFormfour.setDead1(dead1);
			// newFormfour.setDead2(dead2);
			newFormfour.setDeletedStatus(formfour.getDeletedStatus());

			newFormfour.setEffectiveEndDate(formfour1.getEffectiveEndDate());

			newFormfour.setEffectiveStartDate(formfour.getEffectiveStartDate());
			// newFormfour.setEndDate(endDate);
			newFormfour.setFormFourRoute(formfour.getFormFourRoute());
			// newFormfour.setFormFourString(formFourString);
			newFormfour.setFormFourType(formfour.getFormFourType());
			newFormfour.setNoofTrips(formfour.getNoofTrips());

			newFormfour.setOt1(formfour.getOt1());
			newFormfour.setOt2(formfour.getOt2());
			newFormfour.setOtHours(formfour.getOtHours());
			newFormfour.setRemarks(formfour.getRemarks());
			newFormfour.setRestForCrew1(formfour.getRestForCrew1());
			newFormfour.setRestForCrew2(formfour.getRestForCrew2());
			newFormfour.setSactualKm(formfour.getSactualKm());
			// newFormfour.setSchDetails(formfour.getSchDetails());
			newFormfour.setScheduleNumber(formfour.getScheduleNumber());
			newFormfour.setScheduleNumberName(formfour.getScheduleNumberName());
			// newFormfour.setScheduleString(scheduleString);
			// newFormfour.setSchkms1(schkms1);
			// newFormfour.setSchkms2(schkms2);
			newFormfour.setSignOff(formfour.getSignOff());
			newFormfour.setSignOn(formfour.getSignOn());

			newFormfour.setSotHours(formfour.getSotHours());
			newFormfour.setSpread1(formfour.getSpread1());
			newFormfour.setSpread2(formfour.getSpread2());
			newFormfour.setSpreadOverHours(formfour.getSpreadOverHours());
			// newFormfour.setSspreadOverHours(sspreadOverHours);
			// newFormfour.setStartDate(startDate);
			newFormfour.setStartTime(formfour.getStartTime());
			// newFormfour.setStarttimeString(starttimeString);
			newFormfour.setSteering1(formfour.getSteering1());
			newFormfour.setSteering2(formfour.getSteering2());
			newFormfour.setStotalDeadKm(formfour.getStotalDeadKm());
			newFormfour.setStotalSteeringTime(formfour.getStotalSteeringTime());

			newFormfour.setTollZone(formfour.getTollZone());
			newFormfour.setTotalBreakTime(formfour.getTotalBreakTime());
			newFormfour.setTotalDeadKm(formfour.getTotalDeadKm());
			newFormfour.setTotalKm(formfour.getTotalKm());
			newFormfour.setTotalRunTime(formfour.getTotalRunTime());
			newFormfour.setTotalSteeringTime(formfour.getTotalSteeringTime());
			newFormfour.setUpdatedBy(formfour.getUpdatedBy());
			newFormfour.setUpdatedDate(formfour.getUpdatedDate());
			// newFormfour.setWeeklychart(formfour.getWeeklychart());

			session.beginTransaction();
			newformid = (Integer) session.save(newFormfour);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newformid;
	}

//savenewFormFour	
//copytripfromoldform to new FormFour start
	public void saveFormFourInNewschedule(int formfourid, int newformfourid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		try {
			String sql = "INSERT INTO `schedule_details` "
					+ "(`form_four_id`, `schedule_number`, `number_of_trips`, `list_item_number`, `trip_number`, `customer_id`, "
					+ "`trip_type`, `start_point`, `end_point`, `route_number_id`, `route_number`, `route_direction`, `distance`, "
					+ "`start_time`, `end_time`, `running_time`, `break_type_id`, `break_time`, `crew_change`, `night_halt`, "
					+ "`shift_type_id`, `is_dread_trip`, `crew_change_location`, `night_halt_location`, `break_location`, "
					+ "`operation_type`, `remarks`, `created_by`, `created_date`, `updated_by`, `updated_date`, `deleted_status`, "
					+ "`sync_updated_date`) " + "SELECT " + newformfourid
					+ ", `schedule_number`, `number_of_trips`, `list_item_number`, `trip_number`, `customer_id`, "
					+ "`trip_type`, `start_point`, `end_point`, `route_number_id`, `route_number`, `route_direction`, `distance`, "
					+ "`start_time`, `end_time`, `running_time`, `break_type_id`, `break_time`, `crew_change`, `night_halt`, "
					+ "`shift_type_id`, `is_dread_trip`, `crew_change_location`, `night_halt_location`, `break_location`, "
					+ "`operation_type`, `remarks`, `created_by`, `created_date`, `updated_by`, `updated_date`, `deleted_status`,"
					+ " `sync_updated_date` " + "FROM `schedule_details` " + "WHERE (`form_four_id` = '" + formfourid
					+ "')";
			System.out.println("sql----------->" + sql);
			session.beginTransaction();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//copytripfromoldform to new FormFour end	
//get new FormFour by id start 	formfour1 = (FormFour) session.get(FormFour.class,id);
	public FormFour getFormFourById(int newformfourid) {
		System.out.println("--------------getFormFourById---------------");
		FormFour formfour1 = new FormFour();
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		try {
			session.beginTransaction();
			formfour1 = (FormFour) session.get(FormFour.class, newformfourid);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return formfour1;
	}

//end	

//new 31-07-15
	// savenewFormFour
	public int SaveNewFormFour2(FormFour formfour) {
		int newformid = 0;

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			// FormFour newFormfoura=(FormFour) session.load(FormFour.class,
			// formfour.getId());

			FormFour newFormfour = new FormFour();
			newFormfour.setActualKm(formfour.getActualKm());
			newFormfour.setAreaLimit(formfour.getAreaLimit());
			newFormfour.setCreatedBy(formfour.getCreatedBy());
			newFormfour.setCreatedDate(formfour.getCreatedDate());
			newFormfour.setCurrentStatus(formfour.getCurrentStatus());
			// newFormfour.setDead1(dead1);
			// newFormfour.setDead2(dead2);
			newFormfour.setDeletedStatus(formfour.getDeletedStatus());

			newFormfour.setEffectiveEndDate(formfour.getEffectiveEndDate());

			newFormfour.setEffectiveStartDate(formfour.getEffectiveStartDate());
			// newFormfour.setEndDate(endDate);
			newFormfour.setFormFourRoute(formfour.getFormFourRoute());
			// newFormfour.setFormFourString(formFourString);
			newFormfour.setFormFourType(formfour.getFormFourType());
			newFormfour.setNoofTrips(formfour.getNoofTrips());

			newFormfour.setOt1(formfour.getOt1());
			newFormfour.setOt2(formfour.getOt2());
			newFormfour.setOtHours(formfour.getOtHours());
			newFormfour.setRemarks(formfour.getRemarks());
			newFormfour.setRestForCrew1(formfour.getRestForCrew1());
			newFormfour.setRestForCrew2(formfour.getRestForCrew2());
			newFormfour.setSactualKm(formfour.getSactualKm());
			// newFormfour.setSchDetails(formfour.getSchDetails());
			newFormfour.setScheduleNumber(formfour.getScheduleNumber());
			newFormfour.setScheduleNumberName(formfour.getScheduleNumberName());
			// newFormfour.setScheduleString(scheduleString);
			// newFormfour.setSchkms1(schkms1);
			// newFormfour.setSchkms2(schkms2);
			newFormfour.setSignOff(formfour.getSignOff());
			newFormfour.setSignOn(formfour.getSignOn());

			newFormfour.setSotHours(formfour.getSotHours());
			newFormfour.setSpread1(formfour.getSpread1());
			newFormfour.setSpread2(formfour.getSpread2());
			newFormfour.setSpreadOverHours(formfour.getSpreadOverHours());
			// newFormfour.setSspreadOverHours(sspreadOverHours);
			// newFormfour.setStartDate(startDate);
			newFormfour.setStartTime(formfour.getStartTime());
			// newFormfour.setStarttimeString(starttimeString);
			newFormfour.setSteering1(formfour.getSteering1());
			newFormfour.setSteering2(formfour.getSteering2());
			newFormfour.setStotalDeadKm(formfour.getStotalDeadKm());
			newFormfour.setStotalSteeringTime(formfour.getStotalSteeringTime());

			newFormfour.setTollZone(formfour.getTollZone());
			newFormfour.setTotalBreakTime(formfour.getTotalBreakTime());
			newFormfour.setTotalDeadKm(formfour.getTotalDeadKm());
			newFormfour.setTotalKm(formfour.getTotalKm());
			newFormfour.setTotalRunTime(formfour.getTotalRunTime());
			newFormfour.setTotalSteeringTime(formfour.getTotalSteeringTime());
			newFormfour.setUpdatedBy(formfour.getUpdatedBy());
			newFormfour.setUpdatedDate(formfour.getUpdatedDate());
			// newFormfour.setWeeklychart(formfour.getWeeklychart());

			session.beginTransaction();
			newformid = (Integer) session.save(newFormfour);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newformid;
	}
	// savenewFormFour end
	//
//end	

	public void updateweeklychart(int id, int newformfourid) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql = "UPDATE weeklyChart SET form_four_id='" + newformfourid + "' where form_four_id=" + id;
			Query query = session.createSQLQuery(sql);
			int a = query.executeUpdate();
			// session.update(formfour);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void insertIntoTraffic_order(int newformfourid, String traffic_order_No) {

//				System.out.println("in insert traffic order value");
		Session session = null;
		Transaction transaction = null;
		int count = 0;
		String sql = "";
		Session ses = null;
		String formFourId = "";
//				 Session session1=null;
		Session session2 = null;
		Transaction transaction2 = null;
		Common common = new Common();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {

			ses = HibernateUtil.getSession("hibernate.cfg.xml");
//					 session1=HibernateUtil.getSession("hibernate.cfg.xml");
			session2 = HibernateUtil.getSession("hibernate.cfg.xml");
			Query qry = ses
					.createSQLQuery("Select IFNULL(form_four_Id,'')as form_four_Id from form_four_traffic_order where"
							+ " form_four_Id=" + newformfourid + " ")
					.addScalar("form_four_Id", Hibernate.INTEGER);
			qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qry.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				formFourId = list.get("form_four_Id").toString();
				System.out.println("form four id====" + formFourId);
			}
			if (aliasToValueMapList.size() == 0) {

				int userid = (Integer) request.getSession().getAttribute("userid");
				String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				transaction = session.beginTransaction();
//						    String startdate1 = common.getDateFromPicker(record_dte);
//						    System.out.println("now start date==="+startdate1);
				sql = "insert into form_four_traffic_order (form_four_Id,traffic_order_no	,record_date,inserted_date,inserted_by) values ("
						+ newformfourid + ",'" + traffic_order_No + "',curdate(),'" + currentDate + "'," + userid + ")";

				System.out.println("Sql==" + sql);
				Query query = session.createSQLQuery(sql);
				count = query.executeUpdate();
				System.out.println("count" + count);
				transaction.commit();

			} else {

//							 int userid = (Integer) request.getSession().getAttribute("userid");
//								String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				transaction = session.beginTransaction();
//							    String startdate1 = common.getDateFromPicker(record_dte);
//							    System.out.println("now start date==="+startdate1);
				sql = "update form_four_traffic_order set traffic_order_no='" + traffic_order_No
						+ "' where form_four_Id=" + newformfourid + " ";

				System.out.println("Sql==" + sql);
				Query query = session.createSQLQuery(sql);
				count = query.executeUpdate();
				System.out.println("count" + count);
				transaction.commit();

			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

//			added by Aditi
	public String getTrafficOrderNo(String formFourId) {
		System.out.println("here ");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String trafficOrderNo = "";
		try {
			Query query = session.createSQLQuery(
					"select IFNULL(traffic_order_no,'')traffic_order_no from form_four_traffic_order where form_four_Id="
							+ formFourId + " ");
			System.out.println("query  " + query);
			trafficOrderNo = (String) query.uniqueResult();
			System.out.println("traffic order is--" + trafficOrderNo);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return trafficOrderNo;
	}

	public boolean checkTraficOrderFormFour(int formFourId, String trafficOrder) {
		boolean flag = false;
		Session session = null;
		String sql = "";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			sql = "select traffic_order_no,form_four_Id from form_four_traffic_order where form_four_Id=" + formFourId
					+ "  and traffic_order_no='" + trafficOrder + "'";
			Query qury = session.createSQLQuery(sql).addScalar("traffic_order_no", Hibernate.STRING)
					.addScalar("form_four_Id", Hibernate.INTEGER);
			List results = qury.list();
//					String traffic=results.get(0).toString();
			if (results.size() == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return flag;

	}

	public String checkTraficOrderSameOrNot(int formFourId, String trafficOrder) {
		boolean flag = false;
		Session session = null;
		String sql = "";
		String result = "";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			sql = "select IFNULL(traffic_order_no,'')traffic_order_no from form_four_traffic_order where form_four_Id="
					+ formFourId + "  and traffic_order_no='" + trafficOrder + "'";
			Query qury = session.createSQLQuery(sql).addScalar("traffic_order_no", Hibernate.STRING);
			List results = qury.list();
			String traffic = results.get(0).toString();
			if (traffic.equalsIgnoreCase(trafficOrder)) {
				result = "";
			} else {
				result = trafficOrder;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;

	}

	// update Schedule Mapping add by Rajesh
	public void updateschedulemapping(int id, int newformfourid, int schedule_id) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			String sql = "UPDATE schedule_mapping_vehicle1 SET form_four_id='" + newformfourid + "' where form_four_id="
					+ id + " and schedule_no =" + schedule_id;
			Query query = session.createSQLQuery(sql);
			int a = query.executeUpdate();
			// session.update(formfour);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
