package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.trimax.its.util.HibernateUtil;



public class FormOneReportAction {
	
	
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

		this.setDivisionlist(getDivisionName());
		return "success";
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String showFormOnelistData() throws IOException {

		try {

			
			
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();



			division1 = request.getParameter("division");

			depot1 = request.getParameter("depot");
			startdate = request.getParameter("startdate");
			
			String sql="";
			String Condition = "";
			if(!division1.equalsIgnoreCase("0") && !depot1.equalsIgnoreCase("0") ){
				Condition = "  og.parent_id='"+division1+"' and s.depot_id='"+depot1+"' and";
			}else 
			if(!division1.equalsIgnoreCase("0") && depot1.equalsIgnoreCase("0")) {
				
				Condition = " og.parent_id='"+division1+"' and";
			}else if(division1.equalsIgnoreCase("0") && !depot1.equalsIgnoreCase("0")) {
				Condition = " s.depot_id='"+depot1+"' and";
				
			}else{
				Condition="";
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
			int total = this.getTotalFormOneRecords(Condition);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = this.getFormOneData(total, request, Integer.parseInt(sCol),sdir,Condition);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		try {
			String sql = "select org_chart_id ,org_name  from org_chart where org_type_id=2 and deleted_status=0 order by org_name ";
			Query query = session.createSQLQuery(sql)
					.addScalar("org_chart_id", Hibernate.INTEGER)
					.addScalar("org_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			resultMap.put(0, "ALL");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("org_chart_id")
						.toString()), rs.get("org_name").toString());
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
//
//	public String getPerticularDepot() {
//		HttpServletRequest req = ServletActionContext.getRequest();
//		int parentId = Integer.parseInt(req.getParameter("val"));
//		List<String> l1 = this.getDepotId(parentId);
//		List<String> l2 = this.getDepotName(parentId);
//		String regionTypeAjaxString = "";
//		regionTypeAjaxString += "<option value='0'>------select------</option>";
//		for (int i = 0; i < l1.size(); i++) {
//			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
//					+ ">" + l2.get(i).toString() + "</option>";
//		}
//		HttpServletResponse response = ServletActionContext.getResponse();
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.print(regionTypeAjaxString);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@SuppressWarnings("rawtypes")
//	public List getDepotId(int parentID) {
//		List list = new ArrayList();
//		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
//				+ parentID + " and org_type_id=3 order by org_name";
//		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
//		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String, Object>> aliasToValueMapList = query.list();
//		if (aliasToValueMapList.size() > 0) {
//
//			for (int i = 0; i < aliasToValueMapList.size(); i++) {
//				Map<String, Object> rs = aliasToValueMapList.get(i);
//				list.add(rs.get("org_chart_id").toString());
//			}
//		}
//		HibernateUtil.closeSession();
//		return list;
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public List getDepotName(int parentID) {
//		List list = new ArrayList();
//		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
//				+ parentID + " and org_type_id=3  order by org_name";
//		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
//		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String, Object>> aliasToValueMapList = query.list();
//		if (aliasToValueMapList.size() > 0) {
//
//			for (int i = 0; i < aliasToValueMapList.size(); i++) {
//				Map<String, Object> rs = aliasToValueMapList.get(i);
//				list.add(rs.get("org_name").toString());
//			}
//		}
//		HibernateUtil.closeSession();
//		return list;
//	}

	public int getTotalFormOneRecords(String Condition) {
		int cnt = 0;
		Common common = new Common();
		//String startdate1 = common.getDateFromPicker(startdate);
		// Date date = getCurrDate();
		// String logsheetdate = getTodaysDate(date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql = " select count(*) count from route rt " +
					"inner join schedule_details sd on sd.route_number_id=rt.route_id " +
					"inner join schedule s on s.schedule_id=sd.schedule_number " +
					"inner join  org_chart og on og.org_chart_id=s.depot_id " +
					"inner join bus_stop bs on bs.bus_stop_id=rt.start_point_id " +
					"inner join bus_stop bs1 on bs1.bus_stop_id=rt.end_point_id "+
					"where "+Condition+"  rt.status='Active' and rt.deleted_status='0' and " +
					"s.deleted_status='0' and s.status in('ACTIVE','NEW') and s.current_status in('APPROVED','CASE WORKER')  " +
					"and (rt.effective_from>= '"+dateFormat.format(new Date())+"' or rt.effective_till>='"+dateFormat.format(new Date())+"' or rt.effective_till='0000-00-00 00:00:00') " +
					"group by rt.route_id order by rt.route_number ";
					
			cnt = common.getDBResultInt(session, sql, "count");
		} catch (Exception e) {

		}
		return cnt;
	}

	@SuppressWarnings({ "unchecked" })
	public JSONObject getFormOneData(int total, HttpServletRequest request,
			int index, String dir,String Condition) {
		JSONObject result = new JSONObject();
		Session session = null;

		Common common = new Common();
		//String startdate1 = common.getDateFromPicker(startdate);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String[] dbcol = { "", "route_number", "route_id",
				"route_name","route_direction","via" };
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql = " select rt.route_id route_id,rt.route_number route_number,rt.route_name route_name," +
					"rt.route_direction route_direction,IFNULL(rt.via,'') via,bs.bus_stop_name startbusstop,bs1.bus_stop_name endbusstop," +
					"s.schedule_number schedule_number,og.org_name org_name from route rt " +
					"inner join schedule_details sd on sd.route_number_id=rt.route_id " +
					"inner join schedule s on s.schedule_id=sd.schedule_number " +
					"inner join  org_chart og on og.org_chart_id=s.depot_id " +
					"inner join bus_stop bs on bs.bus_stop_id=rt.start_point_id " +
					"inner join bus_stop bs1 on bs1.bus_stop_id=rt.end_point_id "+
					"where "+Condition+"  rt.status='Active' and rt.deleted_status='0' and " +
					"s.deleted_status='0' and s.status in('ACTIVE','NEW') and s.current_status in('APPROVED','CASE WORKER')  " +
					"and (rt.effective_from>= '"+dateFormat.format(new Date())+"' or rt.effective_till>='"+dateFormat.format(new Date())+"' or rt.effective_till='0000-00-00 00:00:00') " +
					"group by rt.route_id ";
					
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (route_number like '%" + search_term + "%'";
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
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			} else {
				sql += " order by rt.route_number desc";
			}
//			sql += " limit " + request.getParameter("iDisplayStart") + ", "
//					+ request.getParameter("iDisplayLength");
			Query query = session.createSQLQuery(sql)
					.addScalar("route_id", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING)
					.addScalar("route_name", Hibernate.STRING)
					.addScalar("startbusstop", Hibernate.STRING)
					.addScalar("endbusstop", Hibernate.STRING)
					.addScalar("route_direction", Hibernate.STRING)
					.addScalar("via", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				int j = i + 1;
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				String routename = rs.get("route_name").toString();
				String stringsplit[] = routename.split("To");
				ja.add(j);
				ja.add(rs.get("route_id").toString());
				ja.add(rs.get("route_number").toString());
				ja.add(rs.get("startbusstop").toString());
				ja.add(rs.get("endbusstop").toString());
				ja.add(rs.get("via").toString());
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
			totalAfterFilter = getTotalFormOneRecords(Condition);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);
			result.put("iTotalDisplayRecords", totalAfterFilter);
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
