
package com.trimax.its.transport.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.Transaction;

import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;

import com.trimax.its.common.Common;
import com.trimax.its.route.model.RoutePoint;
import com.trimax.its.transport.model.BreakType;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.Customer;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.FormFourType;
import com.trimax.its.transport.model.Route_Trans;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.transport.model.TripType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BrandType;
@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
public class TripDAO {

	public String getDepot(String q) {
		String direction = "";
		String data = "";
		String searchString="";
		String sql ="";
		String condition="";
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String userorgtype = (String) request.getSession().getAttribute(
				"userorgtype");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
		
			sql = "SELECT `bus_stop_id` id, `bus_stop_name` busStopName, `stop_direction` stop_direction FROM `bus_stop` where bus_stop_name like '"+q+"%' and ";
			
			if (userorgtype.equals("TRIMAX")) {
				condition = " status='NEW'";
				
			} else {
				condition = " (status='Approved' OR manual='Y')";
				
			}
			
			sql = sql+condition;
			
			Query query =  session.createSQLQuery(sql).addScalar("id",Hibernate.INTEGER).addScalar("busStopName",Hibernate.STRING).addScalar("stop_direction",Hibernate.STRING);
			query.setResultTransformer(Transformers.aliasToBean(BusStops.class));
			List<BusStops> list = query.list();
			for (BusStops bustops : list) {
				if (!(bustops.getStop_direction() == null || bustops
						.getStop_direction().equals("NULL"))) {
					if (!bustops.getStop_direction().trim().equals("")) {
						direction = "(Towards : " + bustops.getStop_direction()
								+ ")";
					} else {
						direction = "(Towards : )";
					}
				} else {
					direction = "(Towards : )";
				}
				data += bustops.getId() + "|" + bustops.getBusStopName() + " "
						+ direction + ",";
			}
			if (list.size() == 0) {
				data = "No Matching Records";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			 if(session!=null){ session.close(); }
			 
		}
		return data;
	}

	public String getDeadStops(String q) {
		String direction = "";
		String data = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql = "select  bs.bus_stop_id as stopid,bs.bus_stop_name as stop,bs.stop_direction as towards from route  "
				+ " inner join bus_stop bs "
				+ " on bs.bus_stop_id = route.start_point_id"
				+ " where route.route_type_id='7' and bs.bus_stop_name like '"+q+"%'"
				+ " UNION distinct "
				+ "select  bs.bus_stop_id as stopid,bs.bus_stop_name as stop,bs.stop_direction as towards from route  "
				+ "inner join bus_stop bs "
				+ " on bs.bus_stop_id = route.end_point_id "
				+ "where route.route_type_id='7' and bs.bus_stop_name like '"+q+"%'";
		try {
			Query query = session.createSQLQuery(sql)
					.addScalar("stopid", Hibernate.STRING)
					.addScalar("stop", Hibernate.STRING)
					.addScalar("towards", Hibernate.STRING);
			List<Object[]> result = (List<Object[]>) query.list();
			for (Object[] stop : result) {
				if (stop[2] != null) {
					direction = "(Towards : " + stop[2].toString() + ")";
				}
				else{
					direction = "(Towards :)";
				}
				data += stop[0] + "|" + stop[1] + " " + direction + ",";
			}

			if (result.size() == 0) {
				data = "No Matching Records";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			 if(session!=null){ session.close(); }
			 
		}
		return data;
	}

	/*
	 * public String getRoute(String startpoint,String destpoint){ String
	 * direction=""; String data=""; Session session =
	 * HibernateUtil.getSession("hibernate.cfg.xml"); String sql=
	 * "select a.route_number as routeNumber,a.route_id,a.route_direction as routeDirection  from "
	 * + "(SELECT r.route_number,r.route_id,rs.bus_stop_id,r.route_direction " +
	 * "from route r " +
	 * "inner join (select route_id,bus_stop_id,route_order,point_status from route_point  where bus_stop_id in ('"
	 * +startpoint+"','"+destpoint+"') group by route_id,bus_stop_id  " +
	 * "order by route_id,route_order) rs " + "on r.route_id=rs.route_id " +
	 * "where rs.point_status='ACTIVE' and  CURDATE() between effective_from and if(effective_till IS NULL  OR effective_till = '0000-00-00 00:00:00', CURDATE(),effective_till) "
	 * + "group by r.route_id " +
	 * "having min(rs.route_order) <> max(rs.route_order) " + ") a " +
	 * "where bus_stop_id='"+startpoint+"' "; try{ Query query =
	 * session.createSQLQuery(sql).addScalar("routeNumber",
	 * Hibernate.STRING).addScalar("route_id",
	 * Hibernate.INTEGER).addScalar("routeDirection", Hibernate.STRING);
	 * query.setResultTransformer(Transformers.aliasToBean(Route_Trans.class));
	 * List<Route_Trans> list = query.list(); for(Route_Trans result: list){
	 * direction = "("+result.getRouteDirection()+")"; data +=
	 * result.getRoute_id()+"@"; data +=
	 * result.getRouteNumber()+" "+direction+","; } } catch(Exception e){
	 * 
	 * } finally{ if(session!=null){ session.close(); } } return data; }
	 */
	
//	public static void main(String ar[]) throws Exception{
//		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		Map<String, Integer> mMap = new HashMap<String, Integer>();
//		Map<String, Integer> mMap1 = new HashMap<String, Integer>();
//		List<String> resultList = new ArrayList<String>();
//		String deadString="";
//		Common common = new Common();
//		String noFareChartTripds = "3,18,19,20";
//		String triparr [] = noFareChartTripds.split(",");
//		boolean b = Arrays.asList(triparr).contains(String.valueOf(20));
//		System.out.println("B"+b);
//			}
	public String getRoute(String startpoint, String destpoint,String serviceid,int triptype_id) {
		String direction = "";
		String data = "";
		String key1 = "";
		String key2 = "";
		Session session  = null;
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Map<String, Integer> mMap = new HashMap<String, Integer>();
		//Map<String, Integer> mMap1 = new HashMap<String, Integer>();
		//List<String> resultList = new ArrayList<String>();
		String deadString="";
		Common common = new Common();
		String noFareChartTripds = common.getDBResultStr(session, "SELECT `sys_value` FROM `default_system_veriable` WHERE `sys_key` = 'Charted_Service'", "sys_value");
		String triparr [] = noFareChartTripds.split("\\,");
		
		if(serviceid.equals("2")){
			serviceid="2,3,17";
		}else{
			
		}
		boolean b = Arrays.asList(triparr).contains(String.valueOf(triptype_id));
		if(!b){
			deadString ="and fare.service_type_id IN("+serviceid +")";
	
		String sql1 = "select Distinct r.route_number as routeNumber,r.route_id,rp.route_order as routeOrder, route_direction as routeDirection," +
				"if(effective_from IS NULL OR effective_from = '0000-00-00 00:00:00','-',DATE_FORMAT(effective_from,'%d-%m-%Y')) as effectiveFrom, " +
				"if(effective_till IS NULL OR effective_till = '0000-00-00 00:00:00','-',DATE_FORMAT(effective_till,'%d-%m-%Y')) as effectiveTill " +
				"from route as r " +
				"inner join route_point as rp  on r.route_id = rp.route_id inner join route_point as rp1  on r.route_id = rp1.route_id " +
				"inner join route_type rt on rt.route_type_id =r.route_type_id " +
				"inner join trip_type tt on tt.route_type_id = rt.route_type_id "+
				"inner join farechart_master fare on fare.route_id=r.route_id where rp.bus_stop_id = "
				+ startpoint
				+ " AND rp1.bus_stop_id  ='"+destpoint+"' AND rp.route_order < rp1.route_order  and rp.point_status='ACTIVE' and r.status='ACTIVE' and tt.trip_type_id="+triptype_id+" and r.deleted_status=0 " +
				deadString+
				" and  CURDATE() between effective_from and " +
						"if(effective_till IS NULL  OR effective_till = '0000-00-00 00:00:00', CURDATE(),effective_till) and " +
						"DATE_FORMAT(effective_from,'%d-%m-%Y') != DATE_FORMAT(effective_till,'%d-%m-%Y') and " +
						" DATE(if(effect_end_date is null,now(),effect_end_date))>=DATE(now())";
		//System.out.println("check rute dropdwn"+sql1);
		Query query1 = session.createSQLQuery(sql1)
				.addScalar("routeNumber", Hibernate.STRING)
				.addScalar("route_id", Hibernate.INTEGER)
				.addScalar("routeDirection", Hibernate.STRING)
				.addScalar("routeOrder", Hibernate.STRING)
				.addScalar("effectiveFrom", Hibernate.STRING)
				.addScalar("effectiveTill", Hibernate.STRING);
		query1.setResultTransformer(Transformers.aliasToBean(Route_Trans.class));
		List<Route_Trans> list = query1.list();
		for (Route_Trans result : list) {
			//key1 = result.getRoute_id() + "@" + result.getRouteNumber() + "@"
			//		+ result.getRouteDirection();
			//mMap.put(key1, Integer.parseInt(result.getRouteOrder()));
			direction = "(" + result.getRouteDirection() + ")";
			data += result.getRoute_id() + "@";
			data += result.getRouteNumber() + " " + direction + "@";
			data += result.getEffectiveFrom() + "@";
			data += result.getEffectiveTill() + ",";
		}

		
		
//		System.out.println("data-------> " + data);
		/*
		 * Iterator iter = (Iterator) mMap.entrySet().iterator(); while
		 * (((java.util.Iterator<Entry<String, Integer>>) iter).hasNext()) {
		 * Map.Entry mEntry = (Map.Entry) ((java.util.Iterator<Entry<String,
		 * Integer>>) iter).next();
		 * 
		 * }
		 */
		}else{
			data=getDeadRoute(startpoint,destpoint,triptype_id);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return data;
		}
	}

	public String getDeadRoute(String startpoint, String destpoint,int triptype_id) {
		String direction = "";
		String data = "";
		String key1 = "";
		String key2 = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Map<String, Integer> mMap = new HashMap<String, Integer>();
		Map<String, Integer> mMap1 = new HashMap<String, Integer>();
		List<String> resultList = new ArrayList<String>();
		try{
		String sql1 = "select r.route_number as routeNumber,r.route_id,rp.route_order as routeOrder, " +
				"route_direction as routeDirection,if(effective_from IS NULL " +
				"OR effective_from = '0000-00-00 00:00:00','-',DATE_FORMAT(effective_from,'%d-%m-%Y')) as effectiveFrom, " +
				"if(effective_till IS NULL OR effective_till = '0000-00-00 00:00:00','-',DATE_FORMAT(effective_till,'%d-%m-%Y')) as effectiveTill " +
				"from route as r " +
				" inner join route_point as rp  on r.route_id = rp.route_id inner join route_point as rp1  on r.route_id = rp1.route_id " +
				"inner join route_type rt on rt.route_type_id =r.route_type_id " +
				"inner join trip_type tt on tt.route_type_id = rt.route_type_id "+
				" where rp.bus_stop_id = "
				+ startpoint
				+ " AND rp1.bus_stop_id  ='"+destpoint+"' AND rp.route_order < rp1.route_order and rp.point_status='ACTIVE' and r.status='ACTIVE' and r.deleted_status=0  and tt.trip_type_id="+triptype_id+" and " +
				"CURDATE() between effective_from and if(effective_till IS NULL  OR effective_till = '0000-00-00 00:00:00', " +
				"CURDATE(),effective_till) and DATE_FORMAT(effective_from,'%d-%m-%Y') != DATE_FORMAT(effective_till,'%d-%m-%Y') ";
//		System.out.println("check rute dropdwn"+sql1);
		Query query1 = session.createSQLQuery(sql1)
				.addScalar("routeNumber", Hibernate.STRING)
				.addScalar("route_id", Hibernate.INTEGER)
				.addScalar("routeDirection", Hibernate.STRING)
				.addScalar("routeOrder", Hibernate.STRING)
				.addScalar("effectiveFrom", Hibernate.STRING)
				.addScalar("effectiveTill", Hibernate.STRING);
		query1.setResultTransformer(Transformers.aliasToBean(Route_Trans.class));
		List<Route_Trans> list = query1.list();
		for (Route_Trans result : list) {
			direction = "(" + result.getRouteDirection() + ")";
			data += result.getRoute_id() + "@";
			data += result.getRouteNumber() + " " + direction + "@";
			data += result.getEffectiveFrom() + "@";
			data += result.getEffectiveTill() + ",";
		}
		
		
		System.out.println("data-------> " + data);
		/*
		 * Iterator iter = (Iterator) mMap.entrySet().iterator(); while
		 * (((java.util.Iterator<Entry<String, Integer>>) iter).hasNext()) {
		 * Map.Entry mEntry = (Map.Entry) ((java.util.Iterator<Entry<String,
		 * Integer>>) iter).next();
		 * 
		 * }
		 */
		//session.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return data;
	}
	
	public String getDistance(String routeNo, String startpoint,
			String destpoint) {
		Common common = new Common();
		String data = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		String sql = " select sum(sch_distance) as distance "
				+ "from "
				+ "(SELECT rp.route_id,rp.bus_stop_id,rp.route_order,"
				+ " (if(distance IS NULL,0,distance)/1000) distance, "
				+ " if(schedule_distance IS NULL,0,schedule_distance) sch_distance"
				+ " FROM `route_point` rp "
				+ "left join route_map rm2 on rm2.end_bus_stop_id=rp.bus_stop_id and rm2.route_id=rp.route_id "
				+ "WHERE rp.route_id = '" + routeNo
				+ "' AND rp.route_order >= '" + startpoint + "' "
				+ "AND rp.route_order <= '" + destpoint
				+ "' order by rp.route_order) a ";

		try {
			Query query = session.createSQLQuery(sql).addScalar("distance",
					Hibernate.STRING);
			List<String> list = query.list();
			for (String result : list) {
				data += common.getInDecimal(Double.parseDouble(result));
			}
		} catch (Exception e) {

		} finally {
			
			  if(session!=null){ session.close(); }
			 
		}
		return data;
	}

	public String getBusOrder(String routeNo, String busStopPoint) {
		String data = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql = " select route_order as routeOrder from route_point "
				+ "where route_id ='" + routeNo + "' " + "and bus_stop_id = '"
				+ busStopPoint + "'";
		/*
		 * " SELECT `bus_stop_id` as id FROM `bus_stop` WHERE `bus_stop_name` = '"+
		 * busStopPoint+"' and status = 'Approved' )";
		 */
		try {
			Query query = session.createSQLQuery(sql).addScalar("routeOrder",
					Hibernate.STRING);
			List<String> list = query.list();
			for (String result : list) {
				data += result + ",";
			}
		} catch (Exception e) {

		} finally {
			
			  if(session!=null){ session.close(); }
			 
		}
		return data;
	}

	public String getRouteByID(String routeno) {

		String data = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {

			String sql = "select rp.bus_stop_id as busStopid,bs.bus_stop_name as busStopName from bus_stop bs "
					+ " inner join route_point rp on bs.bus_Stop_id = rp.bus_stop_id "
					+ " where route_id = " + routeno;
			Query query = session.createSQLQuery(sql)
					.addScalar("busStopid", Hibernate.STRING)
					.addScalar("busStopName", Hibernate.STRING);
			query.setResultTransformer(Transformers
					.aliasToBean(Route_Trans.class));
			List<Route_Trans> list = query.list();
			for (Route_Trans result : list) {
				data += result.getBusStopid() + "@";
				data += result.getBusStopName() + ",";
			}
		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return data;
	}

	public String getBusPoint(String busStopName) {
		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql = "SELECT `bus_stop_id` as id FROM `bus_stop` WHERE `bus_stop_name` = '"
					+ busStopName + "' and status = 'Approved'";
			Query query = session.createSQLQuery(sql).addScalar("id",
					Hibernate.STRING);
			List<String> list = query.list();
			if (list.size() > 0) {
				result = list.get(0);
			} else {
				result = busStopName;
			}
		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return result;
	}

	public String getBusPoint_(Session session, String busStopName) {
		String result = "";

		String sql = "SELECT `bus_stop_id` as id FROM `bus_stop` WHERE `bus_stop_name` = '"
				+ busStopName + "' and status = 'Approved'";
		try {
			Query query = session.createSQLQuery(sql).addScalar("id",
					Hibernate.STRING);
			List<String> list = query.list();
			if (list.size() > 0) {
				result = list.get(0);
			} else {
				result = busStopName;
			}
		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return result;
	}

	public Map<String, String> getBreakType() {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createQuery("from BreakType where status = 'ACTIVE' and deleted_status = '0'");

			List<BreakType> list = query.list();

			for (BreakType type : list) {
				if (type.getBreakTypeName().toLowerCase().contains("waiting")) {
					resultMap
							.put(type.getDuration() + "-" + type.getId() + "-"
									+ type.getSteeringHours() + "-"
									+ type.getSpreadHours() + "-"
									+ type.getRest() + "-" + type.getOt_hours(),
									type.getBreakTypeName());
				}
			}
			for (BreakType type : list) {
				if (!type.getBreakTypeName().toLowerCase().contains("waiting")) {
					resultMap
							.put(type.getDuration() + "-" + type.getId() + "-"
									+ type.getSteeringHours() + "-"
									+ type.getSpreadHours() + "-"
									+ type.getRest() + "-" + type.getOt_hours(),
									type.getBreakTypeName());
				}
			}
		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return resultMap;
	}

	public Map<String, String> getCustomer() {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createQuery("from Customer where status = 'ACTIVE'  and deleted_status = '0' order by case when name ='BMTC' then 1 else 2 end, name");
			

			List<Customer> list = query.list();
			for (Customer type : list) {
				resultMap.put(String.valueOf(type.getId()), type.getName());
			}
		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return resultMap;
	}

	public Map<String, String> getCustomerforCharter() {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createQuery("from Customer where status = 'ACTIVE'  and deleted_status = '0' order by case when name ='BMTC' then 1 else 2 end, name");

			List<Customer> list = query.list();
			for (Customer type : list) {
				resultMap.put(String.valueOf(type.getId()), type.getName());
			}
		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return resultMap;
	}

	public Map<Integer, String> getTripType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createQuery("from TripType where status = 'ACTIVE' and deleted_status = '0'");

			List<TripType> list = query.list();
			
			for (TripType type : list) {
				if (type.getTripTypeName().toLowerCase().contains("regular")) {
				resultMap.put(type.getId(), type.getTripTypeName());
				}
			}
			
			for (TripType type : list) {
				if (!type.getTripTypeName().toLowerCase().contains("regular")) {
				resultMap.put(type.getId(), type.getTripTypeName());
				}
			}
		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return resultMap;
	}

	public Map<Integer, String> getShiftType(int scheduleid) {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		Query query = session
				.createQuery("from ShiftType where status = 'ACTIVE' and deleted_status = '0' and schedule_type_id = "
						+ scheduleid);
		List<ShiftType> list = query.list();
		for (ShiftType type : list) {
			resultMap.put(type.getId(), type.getShiftTypeName());
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;

	}
	
	public String getSignInOff(int scheduleid) {
		
		String result="";
		String signin="";
		String signoff="";
		String sysKey="";
		String sql = "select sign_in,sign_off,sys_key from shift_type  st inner join default_system_veriable on shift_type_id = sys_value and sys_key IN ('DAY_1','DAY_2','SHIFT_1','SHIFT_2','GENERAL_SHIFT','NIGHT_SHIFT','Split_Service' ) " +
				"where st.status='ACTIVE' and st.deleted_status='0' and schedule_type_id= "+scheduleid;
		try{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql);
		List<Object[]> list = (List<Object[]>) query.list();
		/*List<ShiftType> list = query.list();
		for (ShiftType type : list) {
			result = type.getShift_code()+"-"+type.getSignIn()+"-"+type.getSignOff();
		}*/
		
		for (Object[] resultObj : list) {
			signin = (String) resultObj[0];
			signoff = (String) resultObj[1];
			sysKey = (String) resultObj[2];
			result += signin+"-"+signoff+"-"+sysKey+"@";
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}

	public FormFour getFormFour(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		FormFour formfour = new FormFour();
		try {
			formfour = (FormFour) session.get(FormFour.class, id);

		} catch (Exception e) {

		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return formfour;
	}

	public BrandType getBrand(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		BrandType brand = new BrandType();
		try {
			brand = (BrandType) session.get(BrandType.class, id);
		} catch (Exception e) {

		} finally {
			if(session!=null){ session.close(); }
			
		}
		return brand;
	}

	public Schedule getSchedule(int id) {
//		System.out.println("Iddd-----> "+id);
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Schedule schedule = new Schedule();
		try {
			schedule = (Schedule) session.get(Schedule.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 if(session!=null){ session.close(); }
			 
		}
		return schedule;
	}

	public Route_Trans getRoute(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Route_Trans route = new Route_Trans();
		try {
			route = (Route_Trans) session.get(Route_Trans.class, id);
		} catch (Exception e) {
			
		} finally {
			
			 if(session!=null){ session.close(); }
			 
		}
		return route;
	}
	
	public String getTripRoute(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String routeNumber ="";
		try {
			Query query = session
					.createQuery("from Route_Trans where status = 'ACTIVE' and deleted_status = '0' and route_id = "+id);
			List<Route_Trans> routeList = query.list();
			if(routeList.size()>0){
				routeNumber = routeList.get(0).getRouteNumber();
			}
			
		} catch (Exception e) {

		} finally {
			
			 if(session!=null){ session.close(); }
			 
		}
		return routeNumber;
	}

	public String getDivision(int id) {
		String result = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery(
							"select org_name from org_chart where org_chart_id = "
									+ id).addScalar("org_name",
							Hibernate.STRING);
			if (query.list().size() > 0) {
				result = (String) query.list().get(0);
			}
		} catch (Exception e) {

		} finally {
			
			 if(session!=null){ session.close(); }
			
		}
		return result;
	}

	public int saveTrips(List<ScheduleDetails> tripdetails, FormFour formfour, String crewchangeid,String traffic_no ) {
		Common common = new Common();
		int id=0;
		int tripdetailssize = tripdetails.size();
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		Session session2=HibernateUtil.getSession("hibernate.cfg.xml");
		Transaction transaction2=null;
		Session ses=null;
		String formFourId="";
		try {

			String startpoint = "";
			String endpoint = "";
System.out.println("in save trips 1");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int userid = (Integer) request.getSession().getAttribute("userid");

			String totalRunningTime = "";

			for (int i = 0; i < tripdetails.size(); i++) {
				
				ScheduleDetails schdetails = new ScheduleDetails();
				schdetails = tripdetails.get(i);
				if (i == 1) {
					if(schdetails.getJourneyTime()!=null){
					totalRunningTime = common.addTime(tripdetails.get(i - 1)
							.getJourneyTime(), schdetails.getJourneyTime());
					}
					// System.out.println("schdetails.getJourneyTime()-->"+schdetails.getJourneyTime()+"     totalRunningTime--------->"+totalRunningTime);
				}
				if (i > 1) {
					if(schdetails.getJourneyTime()!=null){
					totalRunningTime = common.addTime(totalRunningTime,
							schdetails.getJourneyTime());
					}
					// System.out.println("schdetails.getJourneyTime()-->"+schdetails.getJourneyTime()+"     totalRunningTime--------->"+totalRunningTime);
				}

			}

			FormFourDAO formdao = new FormFourDAO();

			if (formfour.getNoofTrips() != tripdetailssize) {
				formfour.setNoofTrips(tripdetailssize);
			}
			formfour.setActualKm(formfour.getSchkms1() + formfour.getSchkms2());
			formfour.setTotalDeadKm(formfour.getDead1() + formfour.getDead2());
			// formfour.setTotalKm(formfour.getTotalKm());
			formfour.setSpreadOverHours(common.addTime(formfour.getSpread1(),
					formfour.getSpread2()));
			formfour.setTotalSteeringTime(common.addTime(
					formfour.getSteering1(), formfour.getSteering2()));
			formfour.setOtHours(common.addTime(formfour.getOt1(),
					formfour.getOt2()));
			formfour.setTotalRunTime(totalRunningTime);
			formfour.setTraffic_order_no(traffic_no);
			System.out.println("in save trips setting form four");
			String resultDate = formdao.setEnddateofprevFormFour(formfour);
			if(!resultDate.equals("-")){
				formfour.setEffectiveEndDate(common.getDateFromApi(resultDate));
			}
			
			id = formdao.saveFormFour(formfour);
//			System.out.println("in save form four---$$$$$$-- "+id);
			
			
			 int count=0;
			 String sql="";
			 ses=HibernateUtil.getSession("hibernate.cfg.xml");
			 Query qry=ses.createSQLQuery("Select IFNULL(form_four_Id,'')as form_four_Id from form_four_traffic_order where" +
			 		" form_four_Id="+id+" ").addScalar("form_four_Id",Hibernate.INTEGER);
			 qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qry.list();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> list = aliasToValueMapList.get(i);
					 JSONArray ja = new JSONArray();
					 formFourId = list.get("form_four_Id").toString();
				}
				if(aliasToValueMapList.size()==0){
					String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
					 session2 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction2 = session2.beginTransaction();
//					    String startdate1 = common.getDateFromPicker(traffic_date);
//					    System.out.println("now start date==="+startdate1);
					          sql = "insert into form_four_traffic_order (form_four_Id,traffic_order_no	,record_date,inserted_date,inserted_by) values ("+id+",'"+traffic_no+"',curdate(),'"+currentDate+"',"+userid+")";
						 
//					 System.out.println("Sql=="+sql);
				 Query query = session2.createSQLQuery(sql);
				 count=query.executeUpdate();
//				 System.out.println("count"+count);
				}else{
					String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
					 session2 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction2 = session2.beginTransaction();
//					    String startdate1 = common.getDateFromPicker(traffic_date);
//					    System.out.println("now update==="+startdate1);
					          sql = "update form_four_traffic_order set traffic_order_no='"+traffic_no+"' where form_four_Id="+id+" ";
						 
//					 System.out.println("Sql=="+sql);
				 Query query = session2.createSQLQuery(sql);
				 count=query.executeUpdate();
//				 System.out.println("count"+count);
					
				}
			 
//				Query query = session.createSQLQuery(sql);
//				int a=query.executeUpdate();
//					//session.update(formfour);
//				session.getTransaction().commit();
//				}catch(Exception e){
//					e.printStackTrace();
//				}finally{
//					session.close();
//				}
		 
			
		

			
			/*FormFour form = formdao.getFormFour(id);
			FormFourType type = formdao.getFormFourType(form.getFormFourType().getId());
			System.out.println("FormFourName ------> "+type.getFormFourTypeName().toLowerCase().replace(" ", ""));
			if(formdao.checkFormFourCount(tripdetails.get(0).getScheduleNumber().getSchedule_id()) == 0){
			if(type.getFormFourTypeName().toLowerCase().replace(" ", "").contains("alldays")){
				WeeklyChartDao weeklydao = new WeeklyChartDao();
				weeklydao.insertInitialChart(id,userid);
			}
			}*/
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			session1.beginTransaction();
			for (int i = 0; i < tripdetails.size(); i++) {
				FormFour formfour1 = new FormFour();
				formfour1.setId(id);
				ScheduleDetails schdetails = new ScheduleDetails();

				schdetails = tripdetails.get(i);
				schdetails.setFormFour(formfour1);

				startpoint = schdetails.getOrigin();
				if (schdetails.getNoofTrips() != tripdetailssize) {
					schdetails.setNoofTrips(tripdetailssize);
				}
				/*if(i!=0){
					if(Integer.parseInt(crewchangeid) == i){
						schdetails.setCrewChange(1);
					}
				}*/
				if (schdetails.isCrewChangeStatus()) {
					schdetails.setCrewChange(1);
				}
				
//				System.out.println("getIsDreadTrip -----> "+schdetails.getIsDreadTrip());
				/*if(schdetails.getTripType().getTripTypeCode().equals("DT")){
					schdetails.setIsDreadTrip(1);
				}*/
				/*if (schdetails.isDeadTrip()) {
					schdetails.setIsDreadTrip(1);
				}*/

				if (schdetails.isNightTrip()) {
					schdetails.setNightHalt(1);
				}
				
				if(schdetails.getStarttimeString().equals("24:00")){
					schdetails.setStarttimeString("23:59");
				}
				
				if(schdetails.getEndtimeString().equals("24:00")){
					schdetails.setEndtimeString("23:59");
				}
				schdetails.setStartTime(common.getHours(schdetails
						.getStarttimeString()));
				schdetails.setEndTime(common.getHours(schdetails
						.getEndtimeString()));
				schdetails.setRunningTime(common.getHours(schdetails
						.getJourneyTime()));
				schdetails.setBreakTime(common.getHours(schdetails
						.getBreaktimeString()));
				schdetails.setCreatedBy(userid);
				schdetails.setCreatedDate(new Date());
				/*if (i == 1) {

					totalRunningTime = common.addTime(tripdetails.get(i - 1)
							.getJourneyTime(), schdetails.getJourneyTime());
					// System.out.println("schdetails.getJourneyTime()-->"+schdetails.getJourneyTime()+"     totalRunningTime--------->"+totalRunningTime);
				}
				if (i > 1) {

					totalRunningTime = common.addTime(totalRunningTime,
							schdetails.getJourneyTime());
					// System.out.println("schdetails.getJourneyTime()-->"+schdetails.getJourneyTime()+"     totalRunningTime--------->"+totalRunningTime);
				}*/

				BreakType breaktype = new BreakType();
				String[] splitResult = schdetails.getBreakTypeString().split(
						"-");
				breaktype.setId(Integer.parseInt(splitResult[1]));
				schdetails.setBreakType(breaktype);
				int up = (Integer) session1.save(schdetails);
				// System.out.println("i---"+i+" up="+schdetails.getNoofTrips());

			}

			// FormFour formfour1 = (FormFour) session1.get(FormFour.class,
			// tripdetails.get(0).getFormFour().getId());

			// formfour.setSpread1(formfour.getSpread1());
			// formfour.setSpread2(formfour.getSpread2());
			// formfour.setSteering1(formfour.getSteering1());
			// formfour.setSteering2(formfour.getSteering2());
			// formfour.setOt1(formfour.getOt1());
			// formfour.setOt2(formfour.getOt2());
			// formfour.setRestForCrew1(formfour.getRestForCrew1());
			// formfour.setRestForCrew2(formfour.getRestForCrew2());
			session1.update(formfour);
			session1.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ses.close();
//			session1.close();
//			session2.close();
		}
		return id;
	}
	
	public int insertIntoTraffic_Order(int id,String traffic_no,String traffic_date,int userid){
		Session session2 =null;
		Transaction transaction2=null;
		 int count=0;
		 String sql="";
		 Common common=new Common();
		try{
			String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		 session2 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction2 = session2.beginTransaction();
		    String startdate1 = common.getDateFromPicker(traffic_date);
//		    System.out.println("now start date==="+startdate1);
		          sql = "insert into form_four_traffic_order (form_four_Id,traffic_order_no	,record_date,inserted_date,inserted_by) values ("+id+",'"+traffic_no+"','"+startdate1+"','"+currentDate+"',"+userid+")";
			 
//		 System.out.println("Sql=="+sql);
	 Query query = session2.createSQLQuery(sql);
	 count=query.executeUpdate();
//	 System.out.println("count"+count);
	 
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
//			if(session2 !=null){
//				session2.close();
//			}
		}
		return count;
		
	}
	

	public String getGroupingStops(String busStopPoint) {
		System.out.println("----------getGroupingStops-------------");
		String data = "";
		String direction = "";
		Session session = HibernateUtil.getSession("hibernate_slave.cfg.xml");
		String sql = " select bs.bus_stop_id ,bus_stop_name,if(bs.stop_direction IS NULL ,'-',bs.stop_direction) as bus_stop_direction from bus_stop bs "
				+ "inner join  "
				+ "(select bus_stop_id from bus_stop_group_stop  "
				+ "where status = 'Y' and group_id = (select group_id from bus_stop_group where bus_stop_id = '"
				+ busStopPoint 
				+ "' and group_type_id=2 and status='ACTIVE') ) as a  "
				+ "on a.bus_stop_id = bs.bus_stop_id ";
		try {
			Query query = session.createSQLQuery(sql)
					.addScalar("bus_stop_id", Hibernate.STRING)
					.addScalar("bus_stop_name", Hibernate.STRING)
					.addScalar("bus_stop_direction", Hibernate.STRING);
			List<Object[]> result = (List<Object[]>) query.list();
			for (Object[] stop : result) {
				if (!stop[2].toString().equals("-")) {
					direction = "(Towards : " + stop[2] + ")";
				} else {
					direction = "(Towards : )";
				}
				data += stop[0] + "|" + stop[1] + " " + direction + ",";
			}
		} catch (Exception e) {

		} finally {
			
			 if(session!=null){ session.close(); }
			 
		}
		return data;
	}

	public List<ScheduleDetails> getTripDetails(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from ScheduleDetails s where s.formFour.id = "
						+ id
						+ " and s.deletedStatus=0 ORDER BY s.listItemNumber");
		List<ScheduleDetails> list = query.list();
		session.close();
		return list;
	}
	
	public List<ScheduleDetails> getTripDetails1(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql = "";
		Query query = session
				.createQuery("from ScheduleDetails s where s.formFour.id = "
						+ id
						+ " and s.deletedStatus=0 ORDER BY s.listItemNumber");
		List<ScheduleDetails> list = query.list();
		session.close();
		return list;
	}

	public String getBusStopName(int id) {
		String busstopname = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from BusStops where id = " + id);
		List<BusStops> list = query.list();
		if (list.size() > 0) {
			busstopname = list.get(0).getBusStopName();
		}
		session.close();
		return busstopname;
	}

	public void saveEditedTrips(List<ScheduleDetails> details, FormFour formfour,FormFour editedformfour) {
		Date createddate  = new Date();
		int createdId;
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		String totalRunningTime = "";
		Common common = new Common();
		try {
			session1.beginTransaction();
			session1.flush();
			FormFour formfour1 = (FormFour) session1.get(FormFour.class, details
					.get(0).getFormFour().getId());
			if(!formfour1.getCurrentStatus().equalsIgnoreCase("ACTIVE")){
			formfour1.setActualKm(formfour.getSchkms1() + formfour.getSchkms2());
			formfour1.setTotalDeadKm(formfour.getDead1() + formfour.getDead2());
			formfour1.setTotalKm(formfour.getTotalKm());
			formfour1.setSpreadOverHours(common.addTime(formfour.getSpread1(),
					formfour.getSpread2()));
			formfour1.setTotalSteeringTime(common.addTime(
					formfour.getSteering1(), formfour.getSteering2()));
			formfour1.setOtHours(common.addTime(formfour.getOt1(),
					formfour.getOt2()));
			formfour1.setTotalRunTime(totalRunningTime);
			formfour1.setSpread1(formfour.getSpread1());
			formfour1.setSpread2(formfour.getSpread2());
			formfour1.setSteering1(formfour.getSteering1());
			formfour1.setSteering2(formfour.getSteering2());
			formfour1.setOt1(formfour.getOt1());
			formfour1.setOt2(formfour.getOt2());
			formfour1.setRestForCrew1(formfour.getRestForCrew1());
			formfour1.setRestForCrew2(formfour.getRestForCrew2());
			formfour1.setNoofTrips(details.size());
			Schedule sch = new Schedule();
			sch.setSchedule_id(editedformfour.getScheduleNumber().getSchedule_id());
			formfour1.setScheduleNumber(sch);
			
			FormFourType formfourtype = new FormFourType();
			formfourtype.setId(editedformfour.getFormFourType().getId());
			formfour1.setFormFourType(formfourtype);
			
			Route_Trans route = new Route_Trans();
			route.setRoute_id(editedformfour.getFormFourRoute().getRoute_id());
			formfour1.setFormFourRoute(route);
			String tr = formfour1.getTraffic_order_no();
			String dte = formfour1.getRecordDate();
//			System.out.println("in save edited traffic-- "+tr+"dte "+dte);
			
			formfour1.setEffectiveStartDate(editedformfour.getEffectiveStartDate());
			formfour1.setEffectiveEndDate(editedformfour.getEffectiveEndDate());
			
			formfour1.setTollZone(editedformfour.getTollZone());
			//changes done for partial form four at time of edit formfour //date- 2-7-2015
			//previous code
			//formfour1.setCurrentStatus(editedformfour.getCurrentStatus());
			//updated code as below first line
			//check Condition if Status is Active then 
			//System.out.println("formfour1.getStatus"+formfour1.getCurrentStatus());
			if(formfour1.getCurrentStatus().equalsIgnoreCase("ACTIVE")){
				formfour1.setCurrentStatus("ACTIVE");
			}else{
			formfour1.setCurrentStatus("Partial");
			}
			formfour1.setRemarks(editedformfour.getRemarks());
			formfour1.setUpdatedBy(editedformfour.getUpdatedBy());
			formfour1.setUpdatedDate(editedformfour.getUpdatedDate());
			formfour1.setStartTime(editedformfour.getStartTime());
			formfour1.setScheduleNumberName(editedformfour.getScheduleNumberName());
			session1.saveOrUpdate(formfour1);
			
			
			
			for (int i = 0; i < details.size(); i++) {
				ScheduleDetails result = details.get(i);
				if (result.getId() == 0) {
					saveTrips(session1,result);
				} else {
					if (i == 1) {

						totalRunningTime = common.addTime(details.get(i - 1)
								.getJourneyTime(), result.getJourneyTime());
					}
					if (i > 1) {
						totalRunningTime = common.addTime(totalRunningTime,
								result.getJourneyTime());
					}
					System.out.println("tripslist.get(i).getEndtimeString() -----> "+result.getEndtimeString());
			
					session1.saveOrUpdate(result);
				}

			}
			}
			
			session1.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveEditedTripsActive(List<ScheduleDetails> details, FormFour formfour,FormFour editedformfour,int id) {
		Date createddate  = new Date();
		int createdId;
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		String totalRunningTime = "";
		Common common = new Common();
		try {
			session1.beginTransaction();
			session1.flush();
			FormFour formfour1 = (FormFour) session1.get(FormFour.class, id);
			//if(!formfour1.getCurrentStatus().equalsIgnoreCase("ACTIVE")){
			formfour1.setActualKm(formfour.getSchkms1() + formfour.getSchkms2());
			formfour1.setTotalDeadKm(formfour.getDead1() + formfour.getDead2());
			formfour1.setTotalKm(formfour.getTotalKm());
			formfour1.setSpreadOverHours(common.addTime(formfour.getSpread1(),
					formfour.getSpread2()));
			formfour1.setTotalSteeringTime(common.addTime(
					formfour.getSteering1(), formfour.getSteering2()));
			formfour1.setOtHours(common.addTime(formfour.getOt1(),
					formfour.getOt2()));
			formfour1.setTotalRunTime(totalRunningTime);
			formfour1.setSpread1(formfour.getSpread1());
			formfour1.setSpread2(formfour.getSpread2());
			formfour1.setSteering1(formfour.getSteering1());
			formfour1.setSteering2(formfour.getSteering2());
			formfour1.setOt1(formfour.getOt1());
			formfour1.setOt2(formfour.getOt2());
			formfour1.setRestForCrew1(formfour.getRestForCrew1());
			formfour1.setRestForCrew2(formfour.getRestForCrew2());
			formfour1.setNoofTrips(details.size());
			Schedule sch = new Schedule();
			sch.setSchedule_id(editedformfour.getScheduleNumber().getSchedule_id());
			formfour1.setScheduleNumber(sch);
			
			FormFourType formfourtype = new FormFourType();
			formfourtype.setId(editedformfour.getFormFourType().getId());
			formfour1.setFormFourType(formfourtype);
			
			Route_Trans route = new Route_Trans();
			route.setRoute_id(editedformfour.getFormFourRoute().getRoute_id());
			formfour1.setFormFourRoute(route);
			
			formfour1.setEffectiveStartDate(editedformfour.getEffectiveStartDate());
			formfour1.setEffectiveEndDate(editedformfour.getEffectiveEndDate());
			
			formfour1.setTollZone(editedformfour.getTollZone());
			//changes done for partial form four at time of edit formfour //date- 2-7-2015
			//previous code
			//formfour1.setCurrentStatus(editedformfour.getCurrentStatus());
			//updated code as below first line
			//check Condition if Status is Active then 
			//System.out.println("formfour1.getStatus"+formfour1.getCurrentStatus());
			if(formfour1.getCurrentStatus().equalsIgnoreCase("ACTIVE")){
				formfour1.setCurrentStatus("ACTIVE");
			}else{
			formfour1.setCurrentStatus("Partial");
			}
			formfour1.setRemarks(editedformfour.getRemarks());
			formfour1.setUpdatedBy(editedformfour.getUpdatedBy());
			formfour1.setUpdatedDate(editedformfour.getUpdatedDate());
			formfour1.setStartTime(editedformfour.getStartTime());
			formfour1.setScheduleNumberName(editedformfour.getScheduleNumberName());
			session1.saveOrUpdate(formfour1);
			
			
			
			for (int i = 0; i < details.size(); i++) {
				ScheduleDetails result = details.get(i);
				result.getFormFour().setId(id);
					saveTrips(session1,result);
				
			}
		//	}
			
			session1.getTransaction().commit();
		} catch (Exception e) {
			session1.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public void setDeleteStatus(String id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		ScheduleDetails details = (ScheduleDetails) session.get(
				ScheduleDetails.class, Integer.parseInt(id));
		details.setDeletedStatus(1);
		
		session.save(details);
		session.getTransaction().commit();
		session.close();

	}

	public static void saveTrips(Session session,ScheduleDetails schdetails) {
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		ScheduleDetails details = new ScheduleDetails();
		Common common = new Common();
		try {
			//session.beginTransaction();
			String startpoint = schdetails.getOrigin();
			String totalRunningTime = "";

			if (schdetails.isCrewChangeStatus()) {
				schdetails.setCrewChange(1);
			}
			if (schdetails.isDeadTrip() || schdetails.getTripType().getId()==3) {
				schdetails.setIsDreadTrip(1);
			}

			if (schdetails.isNightTrip()) {
				schdetails.setNightHalt(1);
			}
			System.out.println("starttimestring ------- > "+schdetails.getStarttimeString());
			System.out.println("EndtimeString ------- > "+schdetails.getEndtimeString());
			if(schdetails.getStarttimeString().equals("24:00")){
				schdetails.setStarttimeString("23:59");
			}
			
			if(schdetails.getEndtimeString().equals("24:00")){
				schdetails.setEndtimeString("23:59");
			}
			
			schdetails.setStartTime(common.getHours(schdetails
					.getStarttimeString()));
			schdetails
					.setEndTime(common.getHours(schdetails.getEndtimeString()));
			schdetails.setRunningTime(common.getHours(schdetails
					.getJourneyTime()));
			schdetails.setBreakTime(common.getHours(schdetails
					.getBreaktimeString()));

			BreakType breaktype = new BreakType();
			String[] splitResult = schdetails.getBreakTypeString().split("-");
			breaktype.setId(Integer.parseInt(splitResult[1]));
			schdetails.setBreakType(breaktype);

			details.setNoofTrips(schdetails.getNoofTrips());
			details.setListItemNumber(schdetails.getListItemNumber());
			details.setTripNumber(schdetails.getTripNumber());
			details.setStartPoint(schdetails.getStartPoint());
			details.setEndPoint(schdetails.getEndPoint());
			details.setDistance(schdetails.getDistance());
			details.setStartTime(schdetails.getStartTime());
			details.setEndTime(schdetails.getEndTime());
			details.setRunningTime(schdetails.getRunningTime());
			details.setBreakTime(schdetails.getBreakTime());
			details.setCrewChange(schdetails.getCrewChange());
			if(schdetails.getNightHalt()==1){
				//String night_halt=;
				System.out.println("Inside night Halt");
				details.setNightHaltLocation(new TripDAO().getBusStopNameWithTowardsHalt(schdetails.getEndPoint(),session));
			}else{
				System.out.println("Uotside night Halt");
				details.setNightHaltLocation(schdetails.getNightHaltLocation());
			}
			details.setNightHalt(schdetails.getNightHalt());
			details.setIsDreadTrip(schdetails.getIsDreadTrip());
			details.setCrewChangeLocation(schdetails.getCrewChangeLocation());
			
			details.setBreakLocation(schdetails.getBreakLocation());
			details.setOperationType(schdetails.getOperationType());
			details.setRemarks(schdetails.getRemarks());
			details.setCreatedDate(new Date());
			details.setFormFour(schdetails.getFormFour());
			details.setScheduleNumber(schdetails.getScheduleNumber());
			details.setTripType(schdetails.getTripType());
			details.setBreakType(schdetails.getBreakType());
			details.setShiftType(schdetails.getShiftType());
			details.setRouteNumber(schdetails.getRouteNumber());
			details.setCustomer(schdetails.getCustomer());

			session.save(details);
			//session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getBusStopNameWithTowards(int id) {
		String busstopname = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from BusStops where id = " + id);
		List<BusStops> list = query.list();
		if (list.size() > 0) {
			BusStops bustops = list.get(0);
			busstopname = bustops.getBusStopName();
			if (!(bustops.getStop_direction() == null || bustops
					.getStop_direction().equals("NULL"))){
				busstopname = busstopname + "(Towards : "+bustops.getStop_direction()+")";
			}
			else{
				busstopname = busstopname + "(Towards : )";
			}
		}
		session.close();
		return busstopname;
	}
	
	public String getBusStopNameWithTowardsHalt(int id,Session session) {
		String busstopname = "";
		//Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from BusStops where id = " + id);
		List<BusStops> list = query.list();
		if (list.size() > 0) {
			BusStops bustops = list.get(0);
			busstopname = bustops.getBusStopName();
			if (!(bustops.getStop_direction() == null || bustops
					.getStop_direction().equals("NULL"))){
				busstopname = busstopname + "(Towards : "+bustops.getStop_direction()+")";
			}
			else{
				busstopname = busstopname + "(Towards : )";
			}
		}
		//session.close();
		return busstopname;
	}
	
	public List<String> getFareStages(int routeId){
		String sql = "select bs.bus_stop_name as stopname from route r inner join route_point rp on r.route_id = rp.route_id inner join bus_stop bs on bs.bus_stop_id = rp.bus_stop_id where r.route_id = '"+routeId+"' and rp.fare_stage='Y'";
		List<String> fareStageList = new ArrayList<String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql).addScalar("stopname", Hibernate.STRING);
		fareStageList = query.list();
		session.close();
		return fareStageList;
	}
	
	public int checkRouteNumber(int startpoint,int endpoint,int routeId){
		int i =0;
		String sql = "SELECT * FROM `route_point` WHERE `route_id` = '"+routeId+"' AND `bus_stop_id` IN ("+startpoint+","+endpoint+")";
		try{
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session1.createSQLQuery(sql);
		List list = query.list();
		if(list.size()==2){
			i = 1;
		}
		session1.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	
	
	public Map<Integer,String> getBusStopNameWithTowards(String busIds) {
		String busstopname = "";
		Map<Integer,String> nMap = new HashMap<Integer, String>();
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from BusStops where id IN " + busIds);
		List<BusStops> list = query.list();
		
		for(int i=0;i<list.size();i++){
			BusStops bustops = list.get(i);
			busstopname = bustops.getBusStopName();
			if (!(bustops.getStop_direction() == null || bustops
					.getStop_direction().equals("NULL"))){
				busstopname = busstopname + "(Towards : "+bustops.getStop_direction()+")";
			}
			else{
				busstopname = busstopname + "(Towards : )";
			}
			nMap.put(bustops.getId(), busstopname);
		}
		
		session.close();
		return nMap;
	}
	
	
	public String getRoute1(String startpoint, String destpoint) {
		System.out.println("--------------getRoute1------------");
		String data = "";
		try{
		String direction = "";
		
		String key1 = "";
		String key2 = "";
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		String sql = "select r.route_number as routeNumber,r.route_id,rp.route_order as routeOrder, route_direction as routeDirection,if(effective_from IS NULL OR effective_from = '0000-00-00 00:00:00','-',DATE_FORMAT(effective_from,'%d-%m-%Y')) as effectiveFrom, if(effective_till IS NULL OR effective_till = '0000-00-00 00:00:00','-',DATE_FORMAT(effective_till,'%d-%m-%Y')) as effectiveTill from route as r inner join route_point as rp  on r.route_id = rp.route_id where rp.bus_stop_id IN('"+startpoint+"', '"+destpoint+"')" +
				" and rp.point_status='ACTIVE' and r.status='ACTIVE' and r.deleted_status=0 and  CURDATE() between effective_from and if(effective_till IS NULL  OR effective_till = '0000-00-00 00:00:00', CURDATE(),effective_till) and DATE_FORMAT(effective_from,'%d-%m-%Y') != DATE_FORMAT(effective_till,'%d-%m-%Y') " +
				"group by route_id having max(route_order) - min(route_order) > 0";
		Query query = session.createSQLQuery(sql).addScalar("routeNumber", Hibernate.STRING)
				.addScalar("route_id", Hibernate.INTEGER)
				.addScalar("routeDirection", Hibernate.STRING)
				.addScalar("routeOrder", Hibernate.STRING)
				.addScalar("effectiveFrom", Hibernate.STRING)
				.addScalar("effectiveTill", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(Route_Trans.class));
		List<Route_Trans> routelist = query.list();
		
		for (Route_Trans result : routelist) {
			direction = "(" + result.getRouteDirection() + ")";
			data += result.getRoute_id() + "@";
			data += result.getRouteNumber() + " " + direction + "@";
			data += result.getEffectiveFrom() + "@";
			data += result.getEffectiveTill() + ",";
		}
		
		session.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	
	public List<List<String>> getAllFareStagesOfAllRoutesofFormFour(ArrayList<String> routenamess){
		List<List<String>> listOfList = new ArrayList<List<String>>();
		Map map = new HashMap<String, String>();
		try{
			for(int i=0;i<routenamess.size();i++){
				List list = new ArrayList<String>();
				String sql = "select bs.bus_stop_name as stopname,rp.fare_stage,route_order  from route r inner join route_point rp on r.route_id = rp.route_id " +
						" inner join bus_stop bs on bs.bus_stop_id = rp.bus_stop_id where r.route_id = '"+routenamess.get(i).split("@")[0]+"' " +
						" and r.route_type_id not in(7) order by rp.route_order";
					List<Map<String,String>> fareStageList = new ArrayList<Map<String,String>>();
					List<Map<String,String>> fareStageListFinal = new ArrayList<Map<String,String>>();
					Session session = HibernateUtil.getSession("hibernate.cfg.xml");
					Query query = session.createSQLQuery(sql).addScalar("stopname", Hibernate.STRING).addScalar("fare_stage", Hibernate.STRING).addScalar("route_order", Hibernate.STRING);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					fareStageList = query.list();
					for(int j=0;j<fareStageList.size();j++){
						Map<String,String> map1 = fareStageList.get(j);
						int routeOrder = Integer.parseInt(String.valueOf(map1.get("route_order")));
						String fareStage = fareStageList.get(j).get("fare_stage");
						if(routeOrder==0 || fareStage.equals("Y")|| (routeOrder==fareStageList.size())){
							fareStageListFinal.add(fareStageList.get(j));
						}
					}
					if(!map.containsKey(routenamess.get(i).split("@")[3]+"-"+routenamess.get(i).split("@")[4]) && fareStageList.size()>0){
						list.add(routenamess.get(i).split("@")[3]+"-"+routenamess.get(i).split("@")[4]);
						list.add(fareStageListFinal);   
						listOfList.add(list);
						map.put(routenamess.get(i).split("@")[3]+"-"+routenamess.get(i).split("@")[4], "");
					}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return listOfList;
		}
	}
	
	public List<String> getFareStagess(int routeId,int order1,int order2){
		
		
		/*String sql1 = "select bs.bus_stop_name as stopname from route r inner join route_point rp on r.route_id = rp.route_id inner join bus_stop bs on bs.bus_stop_id = rp.bus_stop_id where r.route_id = '"+routeId+"' " +
				" and rp.route_order ="+order1+"   and r.route_type_id not in(7,5) order by rp.route_order";
		List<String> fareStageList1 = new ArrayList<String>();
		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query1 = session1.createSQLQuery(sql1).addScalar("stopname", Hibernate.STRING);
		fareStageList1=query1.list();
		String sql = "select bs.bus_stop_name as stopname from route r inner join route_point rp on r.route_id = rp.route_id inner join bus_stop bs on bs.bus_stop_id = rp.bus_stop_id where r.route_id = '"+routeId+"' " +
				" and rp.fare_stage='Y' and rp.route_order>"+order1+"  and rp.route_order<"+order2+" and r.route_type_id not in(7,5) order by rp.route_order";
		List<String> fareStageList = new ArrayList<String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql).addScalar("stopname", Hibernate.STRING);
		fareStageList = query.list();
		String sql2 = "select bs.bus_stop_name as stopname from route r inner join route_point rp on r.route_id = rp.route_id inner join bus_stop bs on bs.bus_stop_id = rp.bus_stop_id where r.route_id = '"+routeId+"' " +
				" and rp.route_order ="+order2+"   and r.route_type_id not in(7,5) order by rp.route_order";
		List<String> fareStageList2 = new ArrayList<String>();
		Session session2 = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query2 = session1.createSQLQuery(sql2).addScalar("stopname", Hibernate.STRING);
		fareStageList2=query2.list();
		
		fareStageList1.addAll(fareStageList);
		fareStageList1.addAll(fareStageList2);*/
		String sql = "select bs.bus_stop_name as stopname from route r inner join route_point rp on r.route_id = rp.route_id inner join bus_stop bs on bs.bus_stop_id = rp.bus_stop_id where r.route_id = '"+routeId+"' " +
				" and rp.fare_stage='Y'  and r.route_type_id not in(7,5) order by rp.route_order";
		List<String> fareStageList = new ArrayList<String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery(sql).addScalar("stopname", Hibernate.STRING);
		fareStageList = query.list();
		
		session.close();
		return fareStageList;
	}
	 //getRouteNumberById
	
	public String getTripRouteAndDirection(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String routeNumber ="";
		try {
			Query query = session
					.createQuery("from Route_Trans where status = 'ACTIVE' and deleted_status = '0' and route_id = "+id);
			List<Route_Trans> routeList = query.list();
			if(routeList.size()>0){
				routeNumber = routeList.get(0).getRouteNumber()+"-"+routeList.get(0).getRouteDirection();
				//System.out.println("routeanddirection"+routeNumber);
			}
			
		} catch (Exception e) {

		} finally {
			
			 if(session!=null){ session.close(); }
			 
		}
		return routeNumber;
	}
	//end
	
	//getRoute_order by route_id and bus_stop_id
	public int getRouteOrderByRouteNo(int id,String busstopid) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int routeorder=0;
		try {
			Query query = session
					.createQuery("from RoutePoint where  deleted_status = '0' and bus_stop_id="+busstopid+" and route_id = "+id);
			List<RoutePoint> routeList = query.list();
			if(routeList.size()>0){
				routeorder = routeList.get(0).getRoute_order();
				System.out.println("routeanddirection"+routeorder);
			}
			
		} catch (Exception e) {

		} finally {
			
			 if(session!=null){ session.close(); }
			 
		}
		return routeorder;
	}
	//end
	//new test start 30-10-2015
	
	public HashMap ModifeHighestStagesRoutes(ArrayList<String> listroute)
	{
		int truecount=0;
		int falsecount=0;
		int count=0;
		//make final maplist
		//Map finallist=new HashMap();
		String startpoint="";
		String endpoint="";
		int  routeid=0;
		int getstartno=0;
		int getendno=0;
		int oldstartno=0;
		int oldendno=0;
		boolean flag=false;
		
		HashMap<Integer,String> finalmap=new HashMap<Integer,String>();
		int length=listroute.size();
//		System.out.println("length"+length);
		for(int i=0;i<length;i++)
		{
		 String routedesc=(String)listroute.get(i);
		 
		// System.out.println(i+"="+routedesc);
		 String spiltarray[]=routedesc.split("@");
		 routeid=Integer.parseInt(spiltarray[0]);
		 //System.out.println("test-routeid"+routeid);
		 startpoint=spiltarray[1];
		 endpoint=spiltarray[2];
		 
		 
		 //condition to check exit route in map
		 //Object value = map.get(key);
		 if(finalmap.containsKey(routeid))
		 {
			// System.out.println("routeid_check"+routeid);
			 String oldroutedesc=(String)finalmap.get(routeid);
			 String oldarray[]=oldroutedesc.split("@");
			 
			  getstartno=getRouteOrderByRouteNo(routeid,startpoint);
			  getendno=getRouteOrderByRouteNo(routeid,endpoint);
			  
			  
			   oldstartno=getRouteOrderByRouteNo(routeid,oldarray[0]);
			   oldendno=getRouteOrderByRouteNo(routeid,oldarray[1]);
			  //System.out.println("old"+oldstartno+"@"+oldendno);
			  
			 // System.out.println("new"+getstartno+"@"+getendno);
			  
			  
			  //condition already new  route is greater than prevoius route then rplace that
			  if((getstartno<=oldstartno) && getendno>=oldendno)
			  { 
				  truecount++;
				  flag=true;
				  
				 // finallist.put(routeid, startpoint+"@"+endpoint);  
			  }
			  else{
				  falsecount++;
				  flag=false;
				  
			  }
			  if(flag)
				 {
				  finalmap.put(routeid, startpoint+"@"+endpoint);
				 }
			 
		 }
		 else{
			 count++;
		 finalmap.put(routeid, startpoint+"@"+endpoint);
		 }
			
		}
		/*System.out.println("finallist....map.."+finalmap);
		System.out.println("true count....map.."+truecount);
		System.out.println("false count....map.."+falsecount);
		System.out.println("count count....map.."+count);*/
		
		return finalmap;
	}
	
	public int UpdateFormFourData(int id,double total,double actual_km){
		int  cnt=0;
		try{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		Transaction transaction = null;
		transaction = session.beginTransaction();
		String sql="UPDATE form_four set total_km="+total+",actual_km="+actual_km+" where form_four_id="+id+" and deleted_status=0";
		 Query query1 = session.createSQLQuery(sql);
			 cnt = query1.executeUpdate();
//			 System.out.println("cnt=========>"+cnt);
			 if(cnt>0){
				 transaction.commit();
			 }
//			 System.out.println("cnt=========>"+cnt);
		}catch(Exception e){
			e.printStackTrace();
	}
		
		return cnt;
		
	}
	
	//end test
	
	public List<Integer> getAllFormFourID(int id){
		int  cnt=0;
		
		List<Integer> list=new ArrayList<Integer>();
		try{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		Transaction transaction = null;
		transaction = session.beginTransaction();
		String sql="SELECT form_four_id FROM `form_four`WHERE `form_four_name` ="+id+" and current_status='ACTIVE' and deleted_status=0 limit 10";
		 Query query1 = session.createSQLQuery(sql).addScalar("form_four_id");;
		 
		 query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query1.list();
//			System.out.println("aliasToValueMapList.size()==="+aliasToValueMapList.size());
			for (int j = 0; j < aliasToValueMapList.size(); j++) {
				Map<String, Object> aliasValue = aliasToValueMapList.get(j);
				 list.add(Integer.parseInt(aliasValue.get("form_four_id").toString()));
				
			}
//			 System.out.println("list=========>"+list);
			
		}catch(Exception e){
			e.printStackTrace();
	}
		
		return list;
		
	}
	
	
	
}
