package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.trimax.its.util.HibernateUtil;

public class RouteWiseTripTimePlanningFrequency {

	private Map<Integer, String> divisionlist;
	private String division1;
	private String depot1;
	public String startdate;

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDivision1() {
		return division1;
	}

	public void setDivision1(String division1) {
		this.division1 = division1;
	}

	public String getDepot1() {
		return depot1;
	}

	public void setDepot1(String depot1) {
		this.depot1 = depot1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String execute() {

		// try {
		// if(dateStr!=null){
		// Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		// request.getSession().setAttribute("cDate", date);
		// }else{
		// request.getSession().setAttribute("cDate", new Date());
		// }
		// } catch (Exception e) {
		// request.getSession().setAttribute("cDate", new Date());
		// e.printStackTrace();
		// }

		// this.setDivisionlist(getDivisionName());
		return "success";
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String getRouteWiseTripTiming() throws IOException {

		try {
			


			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();

			String sourceid = request.getParameter("source");
			String destid = request.getParameter("dest");
			String starttime[] = request.getParameter("startdate").split(":");
			String endtime[] = request.getParameter("enddate").split(":");
			String starttime1 = "";
			String endtime1 = "";
			if (Integer.parseInt(starttime[0]) < 10) {
				starttime1 = "0" + starttime[0] + ":" + starttime[1];
			} else {
				starttime1 = starttime[0] + ":" + starttime[1];
			}
			if (Integer.parseInt(endtime[0]) < 10) {
				endtime1 = "0" + endtime[0] + ":" + endtime[1];
			} else {
				endtime1 = endtime[0] + ":" + endtime[1];
			}

			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");
			String search_term = request.getParameter("sSearch");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}
			// int total = this.getTotalNoOfRecords(sourceid,destid,starttime,endtime);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = this.getListOfData(request, Integer.parseInt(sCol), sdir, sourceid, destid, starttime1, endtime1);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getTotalNoOfRecords(String sourceid, String destid, String starttime, String endtime) {
		int cnt = 0;
		Common common = new Common();
		// String startdate1 = common.getDateFromPicker(startdate);
		// Date date = getCurrDate();
		// String logsheetdate = getTodaysDate(date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Session session = null;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql1 = "SELECT group_concat(bus_stop_id) bus_stop_id FROM bus_stop WHERE bus_stop_group_id ="
					+ sourceid + " AND `status` = 'Approved'";
			Query query1 = session.createSQLQuery(sql1);
			String ids = common.getDBResultStr(session, sql1, "bus_stop_id");
			String sql2 = "SELECT group_concat(bus_stop_id) bus_stop_id1 FROM bus_stop WHERE bus_stop_group_id ="
					+ destid + " AND `status` = 'Approved'";
			Query query2 = session.createSQLQuery(sql2);
			String ids1 = common.getDBResultStr(session, sql2, "bus_stop_id1");
			String sql = "";
			sql = "SELECT count(*) FROM `schedule_details` sd inner join form_four ff "
					+ "on sd.form_four_id=ff.form_four_id "
					+ "inner join schedule s on s.schedule_id=sd.schedule_number inner join route r "
					+ "on r.route_id=sd.route_number_id " + "inner join bus_stop bs1 on bs1.bus_stop_id=sd.start_point "
					+ "inner join bus_stop bs2 on bs2.bus_stop_id=sd.end_point "
					+ "inner join org_chart og on og.org_chart_id=s.depot_id" +
//			"#inner join route_map rm on r.route_id=rm.route_id " +
					" WHERE  ff.`deleted_status` = '0' and s.status='NEW' and sd.start_point IN(" + ids + ") "
					+ "and end_point IN(" + ids1 + ") and "
					+ "s.current_status='CASE WORKER' and sd.`deleted_status` = '0' and ff.current_status='ACTIVE' AND `is_dread_trip` = '0' "
					+ "and time(sd.start_time) between '" + starttime + "' and '" + endtime + "' "
					+ "and bs1.status='APPROVED' order by s.schedule_number,org_name";

			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	@SuppressWarnings({ "unchecked" })
	public JSONObject getListOfData(HttpServletRequest request, int index, String dir, String sourceid, String destid,
			String starttime, String endtime) {
		JSONObject result = new JSONObject();
		Session session = null;
		Common common = new Common();
		// String startdate1 = common.getDateFromPicker(startdate);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String[] dbcol = { "", "route_number", "route_id", "route_name", "route_direction", "via" };
		try {
			// int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			String sql1 = "SELECT group_concat(bus_stop_id) bus_stop_id FROM bus_stop WHERE bus_stop_group_id ="
					+ sourceid + " AND `status` = 'Approved'";
			Query query1 = session.createSQLQuery(sql1);
			String ids = common.getDBResultStr(session, sql1, "bus_stop_id");
			String sql2 = "SELECT group_concat(bus_stop_id) bus_stop_id1 FROM bus_stop WHERE bus_stop_group_id ="
					+ destid + " AND `status` = 'Approved'";
			Query query2 = session.createSQLQuery(sql2);
			String ids1 = common.getDBResultStr(session, sql2, "bus_stop_id1");
			if (ids.equals("") || ids.equalsIgnoreCase("null")) {
				ids = sourceid;
			}
			if (ids1.equals("") || ids1.equalsIgnoreCase("null")) {
				ids1 = destid;
			}

//				 System.out.println("ids1==="+ids+"======"+ids1);
			String sql = "";
			sql = "SELECT r.route_number route_number,f.schedule_number_name schedule_number,bs1.bus_stop_name origin,bs2.bus_stop_name destination,"
					+ "sd.start_time start_time,sd.end_time end_time,og.org_name	org_name,sd.trip_number trip_number,st.schedule_type_name schedule_type_name "
					+ "FROM `schedule_details` sd inner join form_four ff " + "on sd.form_four_id=ff.form_four_id "
					+ "inner join schedule s on s.schedule_id=sd.schedule_number inner join route r "
					+ "on r.route_id=sd.route_number_id " + "inner join bus_stop bs1 on bs1.bus_stop_id=sd.start_point "
					+ "inner join bus_stop bs2 on bs2.bus_stop_id=sd.end_point "
					+ "inner join form_four as f on f.schedule_number_id = s.schedule_id "
					+ "inner join org_chart og on og.org_chart_id=s.depot_id"
					+ " left join schedule_type st on st.schedule_type_id=s.schedule_type " +
//			"#inner join route_map rm on r.route_id=rm.route_id " +
					" WHERE  ff.`deleted_status` = '0' and s.status='NEW' and sd.start_point IN(" + ids + ") "
					+ "and end_point IN(" + ids1 + ") and "
					+ "s.current_status='CASE WORKER' and sd.`deleted_status` = '0' and ff.current_status='ACTIVE' AND `is_dread_trip` = '0' "
					+ "and time(sd.start_time) between '" + starttime + ":00' and '" + endtime + ":00' "
					+ "and bs1.status='APPROVED' order by s.schedule_number,org_name";
			
			
			
		sql="	SELECT r.route_number route_number,ff.schedule_number_name schedule_number,bs1.bus_stop_name origin,bs2.bus_stop_name destination,sd.start_time start_time,"
				+ " sd.end_time end_time,og.org_name	org_name,sd.trip_number trip_number,st.schedule_type_name schedule_type_name FROM `schedule_details` sd "
				+ " inner join form_four ff on sd.form_four_id=ff.form_four_id "
				+ " inner join schedule s on s.schedule_id=sd.schedule_number "
				+ " inner join route r on r.route_id=sd.route_number_id inner join bus_stop bs1 on bs1.bus_stop_id=sd.start_point " 
				+ " 	inner join bus_stop bs2 on bs2.bus_stop_id=sd.end_point "
				+ "	inner join org_chart og on og.org_chart_id=s.depot_id left join schedule_type st on st.schedule_type_id=s.schedule_type "  
				+ " WHERE  ff.`deleted_status` = 0 and s.status='NEW' and s.deleted_status=0 and sd.start_point IN (" + ids +")  and end_point IN (" + ids1 +") and s.current_status='CASE WORKER' " 
				+ " and sd.`deleted_status` = '0' and ff.current_status='ACTIVE' AND `is_dread_trip` = '0' and time(sd.start_time) between '" + starttime + ":00' and '" + endtime + ":00'  "
				+ " and bs1.status='APPROVED' #group by f.schedule_number_name,sd.trip_number "
				+ " order by s.schedule_number,org_name";

			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
//				sql += " and (route_number like '%" + search_term + "%'";
				// sql += " OR gen_logsheet_id like '%"+search_term+"%'";
				// sql +=
				// " OR form_four.schedule_number_name like '"+search_term+"%'";
				// sql += " OR driver1.TOKEN like '"+search_term+"%'";
				// sql += " OR driver1.EMPLOYEE_NAME like '"+search_term+"%'";
				// sql += " OR driver2.TOKEN like '"+search_term+"%'";
				// sql += " OR driver2.EMPLOYEE_NAME like '"+search_term+"%'";
				// sql += " OR conductor1.TOKEN like '"+search_term+"%'";
				// sql +=
				// " OR conductor1.EMPLOYEE_NAME like '"+search_term+"%'";
				// sql += " OR conductor2.TOKEN like '"+search_term+"%'";
				// sql +=
				// " OR conductor2.EMPLOYEE_NAME like '"+search_term+"%'";
				// sql +=
				// " OR schedule_type.schedule_type_name like '"+search_term+"%'";
				// sql +=
				// " OR service_type.service_type_name like '"+search_term+"%'";
				// sql += " OR gen_logsheet.status like '"+search_term+"%'";
				// sql +=
				// " OR vehicle.license_number like '%"+search_term+"%')";
			}
			String col = dbcol[index];
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					// sql += " order by " + col + " asc";
				} else {
					// sql += " order by " + col + " desc";
				}
			} else {
				// sql += " order by rt.route_number desc";
			}
//			sql += " limit " + request.getParameter("iDisplayStart") + ", "
//					+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql).addScalar("route_number", Hibernate.STRING)
					.addScalar("schedule_number", Hibernate.STRING).addScalar("origin", Hibernate.STRING)
					.addScalar("destination", Hibernate.STRING).addScalar("start_time", Hibernate.STRING)
					.addScalar("end_time", Hibernate.STRING).addScalar("org_name", Hibernate.STRING)
					.addScalar("trip_number", Hibernate.STRING).addScalar("schedule_type_name", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
//				String routename = rs.get("route_name").toString();
//				String stringsplit[] = routename.split("To");
				ja.add(j);
				ja.add(rs.get("schedule_number").toString());
				ja.add(rs.get("route_number").toString());
				ja.add(rs.get("trip_number").toString());
				ja.add(rs.get("start_time").toString());
				ja.add(rs.get("end_time").toString());
				ja.add(rs.get("origin").toString());
				ja.add(rs.get("destination").toString());
				ja.add(rs.get("schedule_type_name").toString());
				ja.add(rs.get("org_name").toString());

				array.add(ja);

			}
			int cnt = 0;
			result.put("aaData", array);
			// if (search_term1 != null && !search_term1.equals("")) {
			// totalAfterFilter = getTotalRecordsForSeacrch(total, request,col,
			// dir);
			// result.put("aaData", array);
			// result.put("iTotalRecords", totalAfterFilter);
			// result.put("iTotalDisplayRecords",totalAfterFilter);
			// } else {
			// totalAfterFilter = getTotalNoOfRecords( sourceid, destid, starttime,
			// endtime);
			result.put("aaData", array);
			// result.put("iTotalRecords", totalAfterFilter);
			// result.put("iTotalDisplayRecords", totalAfterFilter);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

}
