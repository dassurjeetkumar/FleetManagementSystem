package com.trimax.its.ccc.dao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*import javax.servlet.http.HttpServletRequest;*/
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.OnlineWaybillLC.action.OnlineWaybills;

import com.trimax.its.common.Common;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

public class cccDaoData {

	@SuppressWarnings({ "unchecked" })
	// Functio0n used getting data for table
	public JSONObject getData(int total, HttpServletRequest request,
			String cols, String dir, String fromdate, String tilldate) {
		JSONObject result = new JSONObject();
		Session session3 = null;
		int count=0;
		String conductor = "", driver = "", sch_no = "", route_no = "", depot_no = "";
		List<com.trimax.its.sw.service.GetSOS> getList = null;
		HibernateUtilVtu h = new HibernateUtilVtu();
		try {
			@SuppressWarnings("unused")
			int totalAfterFilter = total;

			String sql = "select sos.sosid,sos.device_id,vehicle.licence_number,sos.epbax_start_time,IST_DATE "
					+ "from vts_sos_push_data sos inner join vehicle_device_rel vehicle"
					+ " on sos.device_id = vehicle.device_id "
					+ "where created_date between '"
					+ fromdate
					+ "' and '"
					+ tilldate + "' " + "order by created_date desc";

			session3 = h.getSession(sql);
			Query query = session3.createSQLQuery(sql)
					.addScalar("sosid", Hibernate.STRING)
					.addScalar("device_id", Hibernate.STRING)
					.addScalar("licence_number", Hibernate.STRING)
					.addScalar("epbax_start_time", Hibernate.STRING)
					.addScalar("IST_DATE", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array1 = new JSONArray();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				String vehicle_no = rs.get("licence_number") != null ? rs.get(
						"licence_number").toString() : "";
				String start_time = rs.get("epbax_start_time") != null ? rs
						.get("epbax_start_time").toString() : rs
						.get("IST_DATE").toString();
				if (!vehicle_no.equals(null)) {
					com.trimax.its.sw.service.EpbxService service = new com.trimax.its.sw.service.EpbxService();
					com.trimax.its.sw.service.Epbx port = service.getEpbxPort();
					java.lang.String vehicleno = rs.get("device_id").toString();
					String [] frommdate=fromdate.split(" ");
					model.jaxb.sos.xml.trimax.com.SosResponce result1 = port.getSOSCallData(vehicleno,frommdate[0]); // calling web service
					if (result1.getVtsDatamodel().size() > 0) {

						for (int j = 0; j < result1.getVtsDatamodel().size(); j++) {
							getList = result1.getVtsDatamodel();
						}
						driver = getList.get(0).getDriverName().replace(" ", "");
						conductor = getList.get(0).getCondName().replace(" ", "");
						sch_no = getList.get(0).getScheduleNo().replace(" ", "");
						route_no = getList.get(0).getRouteNo();
						depot_no = getList.get(0).getDepotCd()!=null?getList.get(0).getDepotCd():"Depot11";
					} else {
						conductor = "";
						driver = "";
					}
				}
				//Check Wheather Available in accident_ccc and breakdown_ccc
				boolean chkFlag=checkCallId(rs.get("sosid").toString());
				
				if(!chkFlag){
					count++;
				//ja.add(i + 1);
				ja.add(rs.get("sosid").toString());
				ja.add(rs.get("device_id").toString());
				ja.add(vehicle_no);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date dt = new Date();
				String epbax_date = "";
				String arr[] = new String[2];
				if (!start_time.equals("")) {

					dt = simpleDateFormat.parse(start_time);
					epbax_date = simpleDateFormat.format(dt);
					arr = epbax_date.split(" ");

					ja.add(epbax_date);
				} else {
					ja.add("");
				}
				String arr1[] = new String[2];
				ja.add(driver);
				ja.add(conductor);
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=getSOSData('"
						+ rs.get("sosid").toString()
						+ "','"
						+ rs.get("device_id").toString()
						+ "','"
						+ arr[0]
						+ "-"
						+ arr[1]
						+ "','"
						+ driver
						+ "','"
						+ conductor
						+ "','"
						+ vehicle_no
						+ "','"
						+ route_no
						+ "','"
						+ sch_no
						+ "','"
						+ depot_no.replace(" ", "")
						+ "') title='Update Details' id='alert_details'>"
						+ "UPDATE" + "</a>");
				array1.add(ja);
				}
			}

			result.put("aaData", array1);
			result.put("iTotalRecords", count);
			result.put("iTotalDisplayRecords", count);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}
		return result;
	}

	private boolean checkCallId(String callId) {
		boolean flag = false;
		Session session = null;
		String accsql = "select call_id from accident_ccc where call_id="
				+ callId + " limit 1";
		String vehiclesql = "select call_id from breakdown_ccc where call_id="
				+ callId + " limit 1";
		try {
			session = HibernateUtil.getSession("");
			Query accquery = session.createSQLQuery(accsql);
			Query vehiclequery = session.createSQLQuery(vehiclesql);
			if(accquery.list().size()>0 || vehiclequery.list().size()>0)
			{
				flag=true;
			}
		} catch (Exception ex) {

		} finally {

		}
		return flag;

	}

	@SuppressWarnings({ "unchecked", "unused" })
	// function for retrieve data for alert
	public JSONObject getData1(int total, HttpServletRequest request,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();
		try {
			int totalAfterFilter = total;
			JSONArray array = new JSONArray();
			String sql = "select count(*) as totalcount,vat.PACKET_CODE,vat.MISC_BYTES, vam.ALERT_SHORT_CODE,ALERT_DESC from vts_alert_data vat"
					+ " inner join vts_alert_master vam on vam.MISC_BYTES =vat.MISC_BYTES and "
					+ " vam.PACKET_CODE = vat.PACKET_CODE where vat.CREATED_DATE between '"
					+ fromDate
					+ "' and '"
					+ tillDate
					+ "' group by vat.PACKET_CODE,vat.MISC_BYTES";
			session3 = h.getSession(sql);
			Query query = session3.createSQLQuery(sql)
					.addScalar("totalcount", Hibernate.STRING)
					.addScalar("ALERT_SHORT_CODE", Hibernate.STRING)
					.addScalar("ALERT_DESC", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(rs.get("totalcount").toString());
				ja.add(rs.get("ALERT_SHORT_CODE").toString());
				ja.add(rs.get("ALERT_DESC").toString());
				array.add(ja);
			}

			result.put("aaData", array);

			result.put("iTotalRecords", aliasToValueMapList.size());
			result.put("iTotalDisplayRecords", aliasToValueMapList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();
				// HibernateUtilVtu.closeVtuSession();
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// function for getting accident detail
	public List getAccidentType() {
		String status = "ACTIVE";
		List list = new ArrayList();

		String qry = "select accident_type_id,accident_type_name from accident_type where status='"
				+ status + "' and deleted_status=0";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("accident_type_id").toString() + "#"
						+ rs.get("accident_type_name").toString());
			}
		}

		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// function for getting breakdown type
	public List getBreakdownType() {

		List list = new ArrayList();
		String qry="select breakdown_type_Id,breakdown_category from breakdown_type_details where deleted_status=0";

		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("breakdown_type_Id").toString() + "#"
						+ rs.get("breakdown_category").toString());
			}
		}

		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	// function for getting SOS Data
	public JSONObject getSOSData(String vehicle_no) {
		JSONObject result = new JSONObject();

		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();

		try {

			JSONArray array = new JSONArray();
			JSONArray array1 = new JSONArray();
			String sql = "select org_name from org_chart where org_chart_id IN (select org_chart_id from vehicle where license_number='KA01F2289')";

			session3 = h.getSession(sql);
			Query query = session3.createSQLQuery(sql).addScalar("org_name",
					Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			String sql_1 = "select sch_no,route_id from vehicle_schedule where vehicle_no='KA01FA1238'";

			Query query1 = session3.createSQLQuery(sql_1)
					.addScalar("sch_no", Hibernate.STRING)
					.addScalar("route_id", Hibernate.STRING);
			query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			List<Map<String, Object>> aliasToValueMapList1 = query1.list();
			for (int i = 0; i < aliasToValueMapList1.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList1.get(i);

				ja.add(rs.get("sch_no").toString());
				ja.add(rs.get("route_id").toString());
				array1.add(ja);
			}
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);

				ja.add(rs.get("org_name").toString());
				array.add(ja);
			}

			result.put("aaData", array);
			result.put("aaData1", array1);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getRevenueData() {

		JSONObject result = new JSONObject();

		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();

		try {

			JSONArray array = new JSONArray();
			String sql2 = "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+bag_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where org.parent_id='1' group by org.org_name";
			// String sql =
			// "SELECT SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details where duty_start_date ='2014-09-08 19:58:23'";
			// String sql1 =
			// "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where duty_start_date ='2014-09-08 19:58:23' and org.parent_id='0'";
			session3 = h.getSession(sql2);
			Query query = session3.createSQLQuery(sql2)
					.addScalar("org_chart_id", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("TotalItemsOrdered", Hibernate.STRING)
					.addScalar("TotalPassenger", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);

				ja.add("<a data-toggle='modal' class='btn blue' href='#mymodal_depot' onclick=getRevenueDetails('"
						+ rs.get("org_chart_id").toString()
						+ "') title='Revenue Details' id='alert_details'>"
						+ rs.get("org_name").toString() + "</a>");
				ja.add(rs.get("TotalItemsOrdered").toString());
				ja.add(rs.get("TotalPassenger").toString());
				array.add(ja);
			}

			result.put("aaData", array);
			System.out.println("result for revenue ........." + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getRevenueData_depot(String parent_id) {

		JSONObject result = new JSONObject();

		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();

		try {

			JSONArray array = new JSONArray();
			String sql2 = "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+bag_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where org.parent_id='"
					+ parent_id + "' group by org.org_name";
			System.out.println("QUERY :::::" + sql2);
			// String sql =
			// "SELECT SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details where duty_start_date ='2014-09-08 19:58:23'";
			// String sql1 =
			// "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where duty_start_date ='2014-09-08 19:58:23' and org.parent_id='0'";
			session3 = h.getSession(sql2);
			Query query = session3.createSQLQuery(sql2)
					.addScalar("org_chart_id", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("TotalItemsOrdered", Hibernate.STRING)
					.addScalar("TotalPassenger", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);

				ja.add("<a data-toggle='modal' class='btn blue' href='#mymodal_waybill' onclick=getRevenueDetails_depot('"
						+ rs.get("org_chart_id").toString()
						+ "') title='Depot Details' id='alert_details'>"
						+ rs.get("org_name").toString() + "</a>");
				ja.add(rs.get("TotalItemsOrdered").toString());
				ja.add(rs.get("TotalPassenger").toString());
				array.add(ja);
			}

			result.put("aaData", array);
			System.out.println("result for revenue ........." + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getRevenueDataByWayBill(String parent_id) {

		JSONObject result = new JSONObject();

		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();

		try {

			JSONArray array = new JSONArray();
			String sql2 = "select  wd.waybill_id,wd.waybill_no,SUM(etm_passenger_amount+bag_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd  where wd.depot_cd='"
					+ parent_id + "' group by wd.waybill_no";
			System.out.println("QUERY :::::" + sql2);
			// String sql1 =
			// "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where duty_start_date ='2014-09-08 19:58:23' and org.parent_id='0'";
			session3 = h.getSession(sql2);
			Query query = session3.createSQLQuery(sql2)
					.addScalar("waybill_id", Hibernate.STRING)
					.addScalar("waybill_no", Hibernate.STRING)
					.addScalar("TotalItemsOrdered", Hibernate.STRING)
					.addScalar("TotalPassenger", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);

				ja.add(rs.get("waybill_no").toString());
				ja.add(rs.get("TotalItemsOrdered").toString());
				ja.add(rs.get("TotalPassenger").toString());
				array.add(ja);
			}

			result.put("aaData", array);
			System.out.println("result for revenue ........." + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getTicketData() {

		JSONObject result = new JSONObject();

		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();

		try {

			JSONArray array = new JSONArray();
			String sql2 = "select org.org_chart_id,org.org_name,SUM(bag_passenger_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where org.parent_id='1' group by org.org_name";
			// String sql =
			// "SELECT SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details where duty_start_date ='2014-09-08 19:58:23'";
			// String sql1 =
			// "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where duty_start_date ='2014-09-08 19:58:23' and org.parent_id='0'";
			session3 = h.getSession(sql2);
			Query query = session3.createSQLQuery(sql2)
					.addScalar("org_chart_id", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("TotalItemsOrdered", Hibernate.STRING)
					.addScalar("TotalPassenger", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(i + 1);
				ja.add("<a data-toggle='modal' class='btn blue' href='#mymodal_ticket_depot' onclick=getTicket_Depot('"
						+ rs.get("org_chart_id").toString()
						+ "') title='Revenue Details' id='alert_details'>"
						+ rs.get("org_name").toString() + "</a>");
				ja.add(rs.get("TotalItemsOrdered").toString());
				ja.add(rs.get("TotalPassenger").toString());
				array.add(ja);
			}

			result.put("aaData", array);
			System.out.println("result for revenue ........." + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getTicketByData_depot(String parent_id) {

		JSONObject result = new JSONObject();

		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();

		try {

			JSONArray array = new JSONArray();
			String sql2 = "select org.org_chart_id,org.org_name,SUM(bag_passenger_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where org.parent_id='"
					+ parent_id + "' group by org.org_name";
			System.out.println("QUERY :::::" + sql2);
			// String sql =
			// "SELECT SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details where duty_start_date ='2014-09-08 19:58:23'";
			// String sql1 =
			// "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where duty_start_date ='2014-09-08 19:58:23' and org.parent_id='0'";
			session3 = h.getSession(sql2);
			Query query = session3.createSQLQuery(sql2)
					.addScalar("org_chart_id", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("TotalItemsOrdered", Hibernate.STRING)
					.addScalar("TotalPassenger", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(i + 1);
				ja.add("<a data-toggle='modal' class='btn blue' href='#mymodal_ticket_waybill' onclick=getTicket_Way('"
						+ rs.get("org_chart_id").toString()
						+ "') title='Depot Details' id='alert_details'>"
						+ rs.get("org_name").toString() + "</a>");
				ja.add(rs.get("TotalItemsOrdered").toString());
				ja.add(rs.get("TotalPassenger").toString());
				array.add(ja);
			}

			result.put("aaData", array);
			System.out.println("result for ticket ........." + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getTicketDataByWayBill(String parent_id) {

		JSONObject result = new JSONObject();

		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();

		try {

			JSONArray array = new JSONArray();
			String sql2 = "select  wd.waybill_id,wd.waybill_no,SUM(bag_passenger_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd  where wd.depot_cd='"
					+ parent_id + "' group by wd.waybill_no";
			System.out.println("QUERY :::::" + sql2);
			// String sql1 =
			// "select org.org_chart_id,org.org_name,SUM(etm_passenger_amount+etm_lugg_amount+etm_pass_amount+etm_pass_amount+bag_lugg_amount+bag_pass_amount)AS TotalItemsOrdered ,SUM(etm_passenger_count+etm_lugg_count+etm_pass_count+bag_passenger_count+bag_lugg_count+bag_pass_count) as TotalPassenger FROM waybill_trip_details wd inner join vehicle v on v.license_number=wd.VEHICLE_NO inner join org_chart org on org.org_chart_id = v.org_chart_id where duty_start_date ='2014-09-08 19:58:23' and org.parent_id='0'";
			session3 = h.getSession(sql2);
			Query query = session3.createSQLQuery(sql2)
					.addScalar("waybill_id", Hibernate.STRING)
					.addScalar("waybill_no", Hibernate.STRING)
					.addScalar("TotalItemsOrdered", Hibernate.STRING)
					.addScalar("TotalPassenger", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(i + 1);
				ja.add(rs.get("waybill_no").toString());
				ja.add(rs.get("TotalItemsOrdered").toString());
				ja.add(rs.get("TotalPassenger").toString());
				array.add(ja);
			}

			result.put("aaData", array);
			System.out.println("result for revenue ........." + result);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();

			}
		}

		return result;
	}

	// function for save data
	public String insertAccidentDetail(int user_id,String username, String call_id,
			String device_id, String call_time, String driver_name,
			String conductor_name, String vehicle_no, String route_no,
			String depot_name, String Schedule_no, String call_type,
			String description, String accident_type, String breakdown_type) {

		String str = "";

		Session session3 = null;
		Transaction tx = null;
        Common cm=new Common();
        call_time=cm.getDateFromPickerWithTime(call_time);
		try {

			String sql = "insert into accident_ccc(vehical_id,call_id,device_id,call_time,driver_name,conductor_name," +
					"call_type,route_no,schedual_no,depot_name,notes,accident_type,created_by,attended_by_id,attended_by_name) values('"
					+ vehicle_no
					+ "','"
					+ call_id
					+ "','"
					+ device_id
					+ "','"
					+ call_time
					+ "','"
					+ driver_name
					+ "','"
					+ conductor_name
					+ "','"
					+ call_type
					+ "','"
					+ route_no
					+ "','"
					+ Schedule_no
					+ "','"
					+ depot_name
					+ "','"
					+ description
					+ "','" + accident_type + "'," + user_id + "," + user_id + ",'" + username + "')";

			session3 = HibernateUtil.getSession("hibernate.cfg.xml");
			tx = session3.beginTransaction();
			SQLQuery query = session3.createSQLQuery(sql);
			query.executeUpdate();

			tx.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			if (session3 != null) {

				session3.close();

			}
		}

		return str;
	}

	// function for save breakdown data

	public String saveBreakdownDetail(int user_id,String username, String call_id,
			String device_id, String call_time, String driver_name,
			String conductor_name, String vehicle_no, String route_no,
			String depot_name, String Schedule_no, String call_type,
			String description, String accident_type, String breakdown_type) {

		String str = "";

		Session session3 = null;
		Transaction tx = null;

		try {

			String sql = "insert into breakdown_ccc(vehical_id,call_id,device_id,call_time,driver_name,conductor_name," +
					"call_type,route_no,schedual_no,depot_name,notes,breakdown_type,created_by,attended_by_id,attended_by_name) values('"
					+ vehicle_no
					+ "','"
					+ call_id
					+ "','"
					+ device_id
					+ "','"
					+ call_time
					+ "','"
					+ driver_name
					+ "','"
					+ conductor_name
					+ "','"
					+ call_type
					+ "','"
					+ route_no
					+ "','"
					+ Schedule_no
					+ "','"
					+ depot_name
					+ "','"
					+ description
					+ "','" + breakdown_type + "'," + user_id + "," + user_id + ",'" + username + "'";

			session3 = HibernateUtil.getSession("hibernate.cfg.xml");
			tx = session3.beginTransaction();
			SQLQuery query = session3.createSQLQuery(sql);
			query.executeUpdate();

			tx.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			if (session3 != null) {

				session3.close();

			}
		}

		return str;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForAttendedUnattendedCall(int x,
			HttpServletRequest request, String fromDate, String tillDate) {
		Session session = null;
		JSONObject result = new JSONObject();
		try {
			HibernateUtilVtu h = new HibernateUtilVtu();
			
			String totalDevice = "select 	count(*) as totalcount "
					+ "  from vts_sos_push_data vspd "
					+ " inner join vehicle_device_rel vdr on vdr.DEVICE_ID=vspd.DEVICE_ID"
					+ " where CREATED_DATE between '" + fromDate + "' and '"
					+ tillDate + "' ";
			session = h.getSession(totalDevice);
			Query query1 = session.createSQLQuery(totalDevice).addScalar(
					"totalcount", Hibernate.STRING);
			int totalcount = Integer.parseInt(query1.uniqueResult().toString());
			int totalattendedcount = 0;
			int totalunattendedcount = 0;
			String sql = "select EPBAX_ATTENDED_BY,if(EPBAX_ATTENDED_BY IS NULL ,0,1) as Attendedstatus,EPBAX_CALL_STATUS,EPBAX_START_TIME,EPBAX_END_TIME, "
					+ " vspt.DEVICE_ID,vdr.lICENCE_NUMBER	 from vts_sos_push_data vspt inner join vehicle_device_rel vdr on vdr.DEVICE_ID=vspt.DEVICE_ID"
					+ "  where CREATED_DATE between '"
					+ fromDate
					+ "' and '"
					+ tillDate + "' ";
			Query query = session.createSQLQuery(sql).addScalar(
					"Attendedstatus", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);
				if (rs.get("Attendedstatus").toString().equals("1")) {
					totalattendedcount++;
				} else {
					totalunattendedcount++;
				}

			}
			JSONArray ja = new JSONArray();

			ja.add("<a href='#' title='SOS Details' onclick=javascript:getSosAttendedDetails()>"
					+ totalattendedcount + "</a>");
			ja.add("<a href='#' title='SOS Details' onclick=javascript:getSosUnAttendedDetails()>"
					+ totalunattendedcount + "</a>");
			ja.add(totalcount);
			array.add(ja);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForAttendedCallDetails(int x,
			HttpServletRequest request, String fromDate, String toDate) {
		Session session = null;
		JSONObject result = new JSONObject();
		try {
			HibernateUtilVtu h = new HibernateUtilVtu();
			
			String sql = "select EPBAX_ATTENDED_BY,EPBAX_CALL_STATUS,EPBAX_START_TIME,EPBAX_END_TIME, "
					+ " vspt.DEVICE_ID,vdr.lICENCE_NUMBER	 from vts_sos_push_data vspt inner join vehicle_device_rel vdr on vdr.DEVICE_ID=vspt.DEVICE_ID"
					+ "  where CREATED_DATE between '"
					+ fromDate
					+ "' and '"
					+ toDate + "' and EPBAX_ATTENDED_BY IS NOT NULL ";
			session = h.getSession(sql);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(rs.get("EPBAX_ATTENDED_BY").toString());
				ja.add(rs.get("EPBAX_CALL_STATUS").toString());
				ja.add(rs.get("lICENCE_NUMBER").toString());
				ja.add(rs.get("DEVICE_ID").toString());
				ja.add(rs.get("EPBAX_START_TIME").toString());
				ja.add(rs.get("EPBAX_END_TIME") != null ? rs.get(
						"EPBAX_END_TIME").toString() : "");
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForUnAttendedCallDetails(int x,
			HttpServletRequest request, String fromDate, String toDate) {
		Session session = null;
		JSONObject result = new JSONObject();
		try {
			HibernateUtilVtu h = new HibernateUtilVtu();
			
			String sql = "select EPBAX_ATTENDED_BY,if(EPBAX_ATTENDED_BY IS NULL ,0,1) as Attendedstatus,EPBAX_CALL_STATUS,EPBAX_START_TIME,EPBAX_END_TIME, "
					+ " vspt.DEVICE_ID,vdr.lICENCE_NUMBER	 from vts_sos_push_data vspt inner join vehicle_device_rel vdr on vdr.DEVICE_ID=vspt.DEVICE_ID"
					+ "  where CREATED_DATE between '"
					+ fromDate
					+ "' and '"
					+ toDate + "' and EPBAX_ATTENDED_BY IS  NULL ";
			session = h.getSession(sql);
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(rs.get("EPBAX_ATTENDED_BY") != null ? rs.get(
						"EPBAX_ATTENDED_BY").toString() : "");
				ja.add(rs.get("EPBAX_CALL_STATUS") != null ? rs.get(
						"EPBAX_CALL_STATUS").toString() : "");
				ja.add(rs.get("lICENCE_NUMBER"));
				ja.add(rs.get("DEVICE_ID"));
				ja.add(rs.get("EPBAX_START_TIME") != null ? rs.get(
						"EPBAX_START_TIME").toString() : "");
				ja.add(rs.get("EPBAX_END_TIME") != null ? rs.get(
						"EPBAX_END_TIME").toString() : "");
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}
		return result;
	}
	//vehicle pie chart
	
	//data for Accident type piechart
	@SuppressWarnings("unchecked")
	public JSONObject getDataForChartPieVehicle() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject result = new JSONObject();
		List list=new ArrayList();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String currentDate1=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	
		try {
			String orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
			String orgchartid=(String)request.getSession().getAttribute("orgchartid");
			System.out.println("orgtypeid..........."+orgtypeid+"orgchartid................."+orgchartid);
			String id="!=0";
			if(orgtypeid.equals("1"))
					{
				id="!=0";
				
					}
			if(orgtypeid.equals("3"))
			{
		
				id="="+orgchartid+"";
			}
			if(orgtypeid.equals("2"))
			{
		
				id=" in(select org_chart_id as depotids from org_chart where parent_id='"+orgchartid+"')";
			}
			System.out.println("id...."+id);
			
			
			int totalCount = 0;
			int count[]=new int[3];
			String status[] =new String[3];

			JSONArray array = new JSONArray();
			// Implementing WebService For Vehicle Count.... dated 02-09-2014
			/*com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
					.getVehicleCount(rbKey);*/
		//	String qry = "SELECT accident_type, COUNT(1) as breakdowncount FROM accident_ccc WHERE accident_type IS NOT NULL and call_time between '"+currentDate+" 00:00:00' and  '"+currentDate+" 23:59:59' GROUP BY accident_type;";
			
			
			String qry ="select a.tvehicle,b.accidentcount,c.breakdowncount "+
					" from  "+
					" (     "+
					" select  count(*) as tvehicle from vehicle where deleted_status='0' and org_chart_id"+id+")a "+
					"	,(" +
					" select count(*) as accidentcount from accident_ccc where  "+
					" created_date between '"+currentDate+" 00:00:00' and '"+currentDate+" 23:59:59' "+
					" )b,"+
					" (select count(*) as breakdowncount from breakdown_ccc where "+
					" created_date between '"+currentDate+" 00:00:00' and '"+currentDate+" 23:59:59' )c";
			System.out.print("ACCIDENT_CCC"+qry);
			
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			
			//count=new int[aliasToValueMapList.size()];
			//status=new  String[aliasToValueMapList.size()];
			
			if (aliasToValueMapList.size() > 0) {
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					//totalCount = Integer.parseInt(rs.get("START_LAT").toString());
					count[0] = Integer.parseInt(rs.get("tvehicle").toString());
					count[1] = Integer.parseInt(rs.get("accidentcount").toString());
					count[2] = Integer.parseInt(rs.get("breakdowncount").toString());
					//status[i] = rs.get("accident_type").toString();
					//totalCount+=count[i];
					
					
				}
				totalCount=count[0]+count[1]+count[2];
				status[0] = "vehicle";
				status[1] = "vehicle_Accident";
				status[2]=  "vehicle_Breakdown";
			}
			
			

			for (int i = 0; i < status.length;i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalCount;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			}
			System.out.println("array.................."+array);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return result;
	}
	//end Break down
	
	//vehicle pie chart end
	//data for Accident type piechart
			@SuppressWarnings("unchecked")
			public JSONObject getDataForChartPieAccident() {
				JSONObject result = new JSONObject();
				List list=new ArrayList();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String currentDate1=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			String depotname="";
				try {
					int totalCount = 0;
					int count[];
					String status[] ;

					JSONArray array = new JSONArray();
					// Implementing WebService For Vehicle Count.... dated 02-09-2014
					/*com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
							.getVehicleCount(rbKey);*/
					String qry = "SELECT depot_name,depotid , COUNT(1) as breakdowncount FROM accident_ccc WHERE accident_type IS NOT NULL and created_date between '"+currentDate+" 00:00:00' and  '"+currentDate+" 23:59:59'  GROUP BY  depotid";
					
					
//					String qry ="select a.tvehicle,b.accidentcount,c.breakdowncount "
//							from
//							(
//							select  count(*) as tvehicle from vehicle where deleted_status='0' )a
//									,(
//											select count(*) as accidentcount from accident_ccc where  
//											created_date between '2015-03-14 00:00:00' and '2015-03-14 20:23:32'
//											)b,
//											(select count(*) as breakdowncount from breakdown_ccc where 
//													created_date between '2015-03-14 00:00:00' and '2015-03-14 20:23:32' )c";
					Query query = HibernateUtil.getSession("").createSQLQuery(qry).addScalar("depotid").addScalar("depot_name").addScalar("breakdowncount");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					
					
					count=new int[aliasToValueMapList.size()];
					status=new  String[aliasToValueMapList.size()];
					
					if (aliasToValueMapList.size() > 0) {
						
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							JSONArray ja = new JSONArray();
							String depot=rs.get("depot_name").toString().replace(' ', '-');
							System.out.println("depot"+depot);
							
							ja.add("<a data-toggle='modal' role='button' href='#mymodal1237'  onclick=javascript:viewDepotAccidentData('"
									+rs. get("depotid")
									
									+ "') title='Break Location' id='break_location"
									+ "'>"
									+ rs.get("depot_name")
									+ "</a>");
							ja.add(rs.get("breakdowncount"));
							//ja.add("breakdowncount");
							
							//totalCount = Integer.parseInt(rs.get("START_LAT").toString());
							//count[i] = Integer.parseInt(rs.get("breakdowncount").toString());
							//status[i] = rs.get("accident_type").toString();
							//totalCount+=count[i];
							array.add(ja);
						}
						result.put("aaData", array);
					}
					
					

					/*for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						ja.add(count[i]);
						double per = Double.parseDouble(String.valueOf(count[i])) * 100
								/ totalCount;
						ja.add(per);
						ja.add(status[i]);
						array.add(ja);
					}*/
					//System.out.println("array.................."+array);
					//result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					HibernateUtil.closeSession();
				}
				return result;
			}
			//end Break down
			
			//data for Breakdown type piechart
			@SuppressWarnings("unchecked")
			public JSONObject getDataForChartPieBreakdown() {
				JSONObject result = new JSONObject();
				List list=new ArrayList();
				
				try {
					int totalCount = 0;
					int count[];
					String status[] ;
					String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					JSONArray array = new JSONArray();
					// Implementing WebService For Vehicle Count.... dated 02-09-2014
					/*com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
							.getVehicleCount(rbKey);*/
					String qry = "SELECT  COUNT(1) as breakdowncount,depot_name,depotid FROM breakdown_ccc WHERE breakdown_type IS NOT NULL and created_date between '"+currentDate+" 00:00:00' and  '"+currentDate+" 23:59:59' GROUP BY  depotid ";
					Query query = HibernateUtil.getSession("").createSQLQuery(qry).addScalar("depotid").addScalar("depot_name").addScalar("breakdowncount");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					
					///count=new int[aliasToValueMapList.size()];
					//status=new  String[aliasToValueMapList.size()];
					
					
					if (aliasToValueMapList.size() > 0) {
						
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							//totalCount = Integer.parseInt(rs.get("START_LAT").toString());
							//count[i] = Integer.parseInt(rs.get("breakdowncount").toString());
							//status[i] =rs.get("breakdown_type").toString();
							
							//totalCount+=count[i];
							
						}
						//totalCount=count[0]+count[1];
						//status[0] = "Avoidable";
						//status[1] = "UnAvoidable";
					}
					
					

					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						JSONArray ja = new JSONArray();
						//ja.add(rs.get("depot_name").toString());
						//String depotname=rs.get
						String depot=rs.get("depot_name").toString().replace(' ', '-');
						System.out.println("depot"+depot);
						
						ja.add("<a data-toggle='modal' role='button' href='#mymodal1236'  onclick=javascript:viewDepotBreakdownData('"
								+ rs.get("depotid")
								
								+ "') title='Break Location' id='break_location"
								+ "'>"
								+ rs.get("depot_name")
								+ "</a>");
						ja.add(rs.get("breakdowncount").toString());
						//ja.add(rs.get("breakdown_type").toString());
						array.add(ja);
					}
					System.out.println("array.................."+array);
					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					HibernateUtil.closeSession();
				}
				return result;
			}
			//end Break down
			
			//count conductor 
			
			//data for count conductor 
			@SuppressWarnings("unchecked")
			public JSONObject getDataForChartPieConductorAndDriver() {
				
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				
				JSONObject result = new JSONObject();
				List list=new ArrayList();
				try {
					
					String orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
					String orgchartid=(String)request.getSession().getAttribute("orgchartid");
					System.out.println("orgtypeid..........."+orgtypeid+"orgchartid................."+orgchartid);
					String id="!=0";
					if(orgtypeid.equals("1"))
							{
						id="!=0";
						
							}
					if(orgtypeid.equals("3"))
					{
				
						id="="+orgchartid+"";
					}
					if(orgtypeid.equals("2"))
					{
				
						id=" in(select org_chart_id as depotids from org_chart where parent_id='"+orgchartid+"')";
					}
					System.out.println("id...."+id);
					
					int totalCount = 0;
					int count[] = new int[3];
					String status[] = new String[3]; 

					JSONArray array = new JSONArray();
					// Implementing WebService For Vehicle Count.... dated 02-09-2014
					/*com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
							.getVehicleCount(rbKey);*/
					String qry = "SELECT EMPLOYEE_DESIGNATION ,COUNT(1) as empcount FROM employee WHERE EMPLOYEE_DESIGNATION IS NOT NULL  and STATUS='active' " +
							"and EMPLOYEE_DESIGNATION in('Conductor','Driver','DriverConductor') and org_chart_id"+id+"  GROUP BY EMPLOYEE_DESIGNATION;";
					
					System.out.println("conductor qry............."+qry);
					Query query = HibernateUtil.getSession("").createSQLQuery(qry);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					if (aliasToValueMapList.size() > 0) {
						       
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							//totalCount = Integer.parseInt(rs.get("START_LAT").toString());
							count[i] = Integer.parseInt(rs.get("empcount").toString());
							//count[1] = Integer.parseInt(rs.get("START_LAT").toString());
							
							
							
						}
						totalCount=count[0]+count[1]+count[2];
						status[0] = "Conductor";
						status[1] = "Driver";
						status[2] = "DriverConductor";
					}
					
					

					for (int i = 0; i < 3; i++) {
						JSONArray ja = new JSONArray();
						ja.add(count[i]);
						double per = Double.parseDouble(String.valueOf(count[i])) * 100
								/ totalCount;
						ja.add(count[i]);
						ja.add(status[i]);
						array.add(ja);
					}
					System.out.println("array.................."+array);
					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					HibernateUtil.closeSession();
				}
				return result;
			}
			
			
			//end count conductor
			
			
			//datatable aacident start
			@SuppressWarnings("unchecked")
			public JSONObject getDataAccident(int x,HttpServletRequest request, String depotname) {
				
				Session session = null;
				JSONObject result = new JSONObject();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String currentDate1=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				
				try {
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					String sql = "SELECT vehical_id, device_id, call_time, driver_name, conductor_name, route_no, schedual_no, depot_name, notes "	
							+" FROM accident_ccc "
							+" WHERE depotid = '"+depotname+"' and  created_date between '"+currentDate+" 00:00:00' and '"+currentDate+" 23:59:59' ";
					System.out.println("sqlACIIDENTCC....."+sql);
					Query query = HibernateUtil.getSession("").createSQLQuery(sql);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						ja.add(rs.get("vehical_id") != null ? rs.get("vehical_id").toString() : "");
						ja.add(rs.get("device_id") != null ? rs.get("device_id").toString() : "");
						ja.add(rs.get("call_time")!= null ? rs.get("call_time").toString() : "");
						ja.add(rs.get("driver_name")!= null ? rs.get("driver_name").toString() : "");
						ja.add(rs.get("conductor_name") != null ? rs.get("conductor_name").toString() : "");
						ja.add(rs.get("route_no") != null ? rs.get("route_no").toString() : "");
						ja.add(rs.get("schedual_no") != null ? rs.get("schedual_no").toString() : "");
						ja.add(rs.get("depot_name") != null ? rs.get("depot_name").toString() : "");
						
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					HibernateUtil.closeSession();
				}
				return result;
			}
			//datatable aacident end
			
			//datatable breakdown start
			
			@SuppressWarnings("unchecked")
			public JSONObject getDataBreakdown(int x,HttpServletRequest request, String depotname) {
				Session session1 = null;
				Transaction transaction  = null;
				JSONObject result = new JSONObject();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				try {
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					String sql1 = "SELECT vehical_id, device_id, call_time, driver_name, conductor_name, route_no, schedual_no, depot_name, notes "	
							+" FROM breakdown_ccc "
							+" WHERE depotid = '"+depotname+"' and  created_date between '"+currentDate+" 00:00:00' and  '"+currentDate+" 23:59:59'";
					System.out.println("sql....."+sql1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					Query query = session1.createSQLQuery(sql1);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						ja.add(rs.get("vehical_id") != null ? rs.get("vehical_id").toString() : "");
						//System.out.println("vehicleid="+rs.get("vehical_id"));
						ja.add(rs.get("device_id") != null ? rs.get("device_id").toString() : "");
						ja.add(rs.get("call_time")!= null ? rs.get("call_time").toString() : "");
						ja.add(rs.get("driver_name")!= null ? rs.get("driver_name").toString() : "");
						ja.add(rs.get("conductor_name") != null ? rs.get("conductor_name").toString() : "");
						ja.add(rs.get("route_no") != null ? rs.get("route_no").toString() : "");
						ja.add(rs.get("schedual_no") != null ? rs.get("schedual_no").toString() : "");
						ja.add(rs.get("depot_name") != null ? rs.get("depot_name").toString() : "");
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					session1.close();
				}
				return result;
			}
			
			//datatable breakdown end
			
			//getVehicle Depotwise count
			
			@SuppressWarnings("unchecked")
			public JSONObject getVehicleDepotWiseCount(int x,HttpServletRequest request) {
				Session session1 = null;
				Transaction transaction  = null;
				JSONObject result = new JSONObject();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				try {
					
					//HttpServletRequest request = ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
					String orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
					String orgchartid=(String)request.getSession().getAttribute("orgchartid");
					System.out.println("orgtypeid..........."+orgtypeid+"orgchartid................."+orgchartid);
					String id="!=0";
					if(orgtypeid.equals("1"))
							{
						id="!=0";
						
							}
					if(orgtypeid.equals("3"))
					{
				
						id="="+orgchartid+"";
					}
					if(orgtypeid.equals("2"))
					{
				
						id=" in(select org_chart_id as depotids from org_chart where parent_id='"+orgchartid+"')";
					}
					System.out.println("id...."+id);
					
					
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					String sql1 =" SELECT count(*) as vehicle_count,org.org_name as orgname,v.org_chart_id as org_chart_id from  vehicle v " +
							" inner join org_chart org	on org.org_chart_id=v.org_chart_id " +
							" where v.deleted_status='0' and v.org_chart_id"+id+" group by v.org_chart_id ";
					System.out.println("sql....."+sql1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					Query query = session1.createSQLQuery(sql1).addScalar("vehicle_count").addScalar("orgname").addScalar("org_chart_id");;
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						//ja.add(rs.get("orgname") != null ? rs.get("orgname").toString() : "");
						
						
						

						ja.add("<a data-toggle='modal' role='button' href='#mymodal1238'  onclick=javascript:viewDepotVehicleData('"
								+ rs.get("org_chart_id")
								
								+ "') title='Break Location' id='break_location"
								+ "'>"
								+ rs.get("orgname")
								+ "</a>");
						
						
						///ja.add("");
						//System.out.println("vehicleid="+rs.get("vehical_id"));
						ja.add(rs.get("vehicle_count") != null ? rs.get("vehicle_count").toString() : "");
						
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					session1.close();
				}
				return result;
			}
			//depotwise vehicle count end
			
			//vehicle depotwise data start 
			public JSONObject getVehicleDepotWiseData(int x,HttpServletRequest request, String depotid) {
				Session session1 = null;
				Transaction transaction  = null;
				JSONObject result = new JSONObject();
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				try {
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					String sql1 =" SELECT license_number, org.org_name as orgname from  vehicle v " +
							" inner join org_chart org on org.org_chart_id=v.org_chart_id " +
							" where v.deleted_status='0' and v.org_chart_id='"+depotid+"' "; 
					//System.out.println("sql....."+sql1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					Query query = session1.createSQLQuery(sql1).addScalar("license_number").addScalar("orgname");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						ja.add(rs.get("orgname") != null ? rs.get("orgname").toString() : "");
						//System.out.println("vehicleid="+rs.get("vehical_id"));
						ja.add(rs.get("license_number") != null ? rs.get("license_number").toString() : "");
						
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					session1.close();
				}
				return result;
			}
			//vehicle depotwise data end
			
			//employee data start
			
			@SuppressWarnings("unchecked")
			public JSONObject getDataEmployee(HttpServletRequest request, String status,String id) {
				Session session1 = null;
				Transaction transaction  = null;
				JSONObject result = new JSONObject();
				try {
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					/*String sql1 = "SELECT vehical_id, device_id, call_time, driver_name, conductor_name, route_no, schedual_no, depot_name, notes "	
							+" FROM breakdown_ccc "
							+" WHERE breakdown_type = '"+status+"'";*/
					String sql1="select ifnull(EMPLOYEE_NAME,'') as name,ifnull(EMPLOYEE_DESIGNATION,'') as designation,ifnull(TOKEN,'') as token from employee where STATUS='active' and  EMPLOYEE_DESIGNATION='"+status+"' and org_chart_id='"+id+"' " ;
					
					System.out.println("Em[ployee data....."+sql1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					Query query = session1.createSQLQuery(sql1);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						ja.add(rs.get("name") != null ? rs.get("name").toString() : "");
						//System.out.println("vehicleid="+rs.get("vehical_id"));
						ja.add(rs.get("designation") != null ? rs.get("designation").toString() : "");
						ja.add(rs.get("token")!= null ? rs.get("token").toString() : "");
						/*ja.add(rs.get("driver_name")!= null ? rs.get("driver_name").toString() : "");
						ja.add(rs.get("conductor_name") != null ? rs.get("conductor_name").toString() : "");
						ja.add(rs.get("route_no") != null ? rs.get("route_no").toString() : "");
						ja.add(rs.get("schedual_no") != null ? rs.get("schedual_no").toString() : "");
						ja.add(rs.get("depot_name") != null ? rs.get("depot_name").toString() : "");*/
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					session1.close();
				}
				return result;
			}
			
			
			//employee data end//
			
			//employee data depotwise
			
			@SuppressWarnings("unchecked")
			public JSONObject getDataEmployeeDepotwise(int x,HttpServletRequest request, String status) {
				Session session1 = null;
				Transaction transaction  = null;
				JSONObject result = new JSONObject();
				try {
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					/*String sql1 = "SELECT vehical_id, device_id, call_time, driver_name, conductor_name, route_no, schedual_no, depot_name, notes "	
							+" FROM breakdown_ccc "
							+" WHERE breakdown_type = '"+status+"'";*/
					String sql1="SELECT e.org_chart_id as org_id,e.EMPLOYEE_DESIGNATION as designation,ochart.org_name as orgname, COUNT(1) as empcount FROM employee e "

							+" inner join org_chart ochart on ochart.org_chart_id=e.org_chart_id "

							+" WHERE e.EMPLOYEE_DESIGNATION IS NOT NULL  " 
							+" and e.STATUS='active' and e.EMPLOYEE_DESIGNATION in('"+status+"') GROUP BY e.EMPLOYEE_DESIGNATION,e.org_chart_id order by e.org_chart_id; " ;
					
					System.out.println("getDataEmployeeDepotwise....."+sql1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					Query query = session1.createSQLQuery(sql1).addScalar("designation").addScalar("orgname").addScalar("empcount").addScalar("org_id");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						//ja.add(rs.get("orgname") != null ? rs.get("orgname").toString() : "");
						
						
						
						ja.add("<a data-toggle='modal' role='button' href='#mymodal123'  onclick=javascript:viewDataDepotwise("   
						+ rs.get("org_id").toString()
						+ ",'" +status+"') title='Break Location' id='break_location"
										+ "'>"
										+ rs.get("orgname").toString()
										+ "</a>");
						
						//System.out.println("vehicleid="+rs.get("vehical_id"));
						ja.add(rs.get("designation") != null ? rs.get("designation").toString() : "");
						ja.add(rs.get("empcount")!= null ? rs.get("empcount").toString() : "");
						/*ja.add(rs.get("driver_name")!= null ? rs.get("driver_name").toString() : "");
						ja.add(rs.get("conductor_name") != null ? rs.get("conductor_name").toString() : "");
						ja.add(rs.get("route_no") != null ? rs.get("route_no").toString() : "");
						ja.add(rs.get("schedual_no") != null ? rs.get("schedual_no").toString() : "");
						ja.add(rs.get("depot_name") != null ? rs.get("depot_name").toString() : "");*/
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					session1.close();
				}
				return result;
			}
			
			
			//employee data end
			
			//end
			
			//vtu count start
			
			
			@SuppressWarnings("unchecked")
			public JSONObject getDataForChartPieVTU() {
				JSONObject result = new JSONObject();
				List list=new ArrayList();
				try {
					int totalCount = 0;
					int count[] = new int[2];
					String status[] = new String[2];

					JSONArray array = new JSONArray();
					// Implementing WebService For Vehicle Count.... dated 02-09-2014
					/*com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
							.getVehicleCount(rbKey);*/
					String qry = "select count(1) as vtu_count,'0' as fault "
								+" from device d "
								+" where d.device_type_id in(1) and d.deleted_status='0' "
								+" group by d.device_type_id ";
					
					System.out.println("VTU qry............."+qry);
					Query query = HibernateUtil.getSession("").createSQLQuery(qry);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					if (aliasToValueMapList.size() > 0) {
						       
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							//totalCount = Integer.parseInt(rs.get("START_LAT").toString());
							count[0] = Integer.parseInt(rs.get("vtu_count").toString());
							count[1] = Integer.parseInt(rs.get("fault").toString());
							
							
							
						}
						//count[1]=0;
						totalCount=count[0]+count[1];
						//totalCount=count[0]+count[1]+count[2];
						status[0] = "VTU";
						status[1] = "VTU FAULT";
						//status[2] = "DriverConductor";
					}
					
					

					for (int i = 0; i < 2; i++) {
						JSONArray ja = new JSONArray();
						ja.add(count[i]);
						double per = Double.parseDouble(String.valueOf(count[i])) * 100
								/ totalCount;
						ja.add(per);
						ja.add(status[i]);
						array.add(ja);
					}
					//System.out.println("array.................."+array);
					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					HibernateUtil.closeSession();
				}
				return result;
			}
			
			
			
			//vtu count end
			//ETM count start
			
			@SuppressWarnings("unchecked")
			public JSONObject getDataForChartPieETM(int faultcount) {
				JSONObject result = new JSONObject();
				List list=new ArrayList();
				try {
					int totalCount = 0;
					int count[] = new int[2];
					String status[] = new String[2];

					JSONArray array = new JSONArray();
					// Implementing WebService For Vehicle Count.... dated 02-09-2014
					/*com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
							.getVehicleCount(rbKey);*/
					String qry = "select count(1) as etm_count,'0' as fault "
							+" from device d "
							+" where d.device_type_id in(2) and d.deleted_status='0' "
							+" group by d.device_type_id ";
			
					//System.out.println("conductor qry............."+qry);
					Query query = HibernateUtil.getSession("").createSQLQuery(qry);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					if (aliasToValueMapList.size() > 0) {
						       
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							Map<String, Object> rs = aliasToValueMapList.get(i);
							//totalCount = Integer.parseInt(rs.get("START_LAT").toString());
							count[i] = Integer.parseInt(rs.get("etm_count").toString());
							//count[1] = Integer.parseInt(rs.get("START_LAT").toString());
							count[1] = faultcount;
							
							
						}
						
						totalCount=count[0]+count[1];
						//totalCount=count[0]+count[1]+count[2];
						status[0] = "ETM";
						status[1] = "ETM FAULT";
						//status[2] = "DriverConductor";
					}
					
					

					for (int i = 0; i < 2; i++) {
						JSONArray ja = new JSONArray();
						ja.add(count[i]);
						double per = Double.parseDouble(String.valueOf(count[i])) * 100
								/ totalCount;
						ja.add(per);
						ja.add(status[i]);
						array.add(ja);
					}
					System.out.println("array.................."+array);
					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					HibernateUtil.closeSession();
				}
				return result;
			}
			
//ETM count end
//VTU data start

			
			@SuppressWarnings("unchecked")
			public JSONObject getDataVTU(int x,HttpServletRequest request) {
				Session session1 = null;
				Transaction transaction  = null;
				JSONObject result = new JSONObject();
				try {
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					/*String sql1 = "SELECT vehical_id, device_id, call_time, driver_name, conductor_name, route_no, schedual_no, depot_name, notes "	
							+" FROM breakdown_ccc "
							+" WHERE breakdown_type = '"+status+"'";*/
					//String sql1="select ifnull(EMPLOYEE_NAME,'') as name,ifnull(EMPLOYEE_DESIGNATION,'') as designation,ifnull(TOKEN,'') as token from employee where STATUS='active' and  EMPLOYEE_DESIGNATION='"+request+"' " ;
					
					
					String sql1="select dy.device_type_name as name,d.device_type_id as deviceid,d.device_serial_number as serialno"
								+" from device d " 
								+" inner join device_type dy on d.device_type_id=dy.device_type_id "
								+" where d.device_type_id in(1) and d.deleted_status='0' "
								+" order by d.device_type_id";
					//System.out.println("sql....."+sql1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					Query query = session1.createSQLQuery(sql1).addScalar("name")
							.addScalar("deviceid")
							.addScalar("serialno");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						ja.add(rs.get("name") != null ? rs.get("name").toString() : "");
						//System.out.println("vehicleid="+rs.get("vehical_id"));
						ja.add(rs.get("deviceid") != null ? rs.get("deviceid").toString() : "");
						ja.add(rs.get("serialno")!= null ? rs.get("serialno").toString() : "");
						/*ja.add(rs.get("driver_name")!= null ? rs.get("driver_name").toString() : "");
						ja.add(rs.get("conductor_name") != null ? rs.get("conductor_name").toString() : "");
						ja.add(rs.get("route_no") != null ? rs.get("route_no").toString() : "");
						ja.add(rs.get("schedual_no") != null ? rs.get("schedual_no").toString() : "");
						ja.add(rs.get("depot_name") != null ? rs.get("depot_name").toString() : "");*/
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					session1.close();
				}
				return result;
			}
			
			
//vtu data end		
			
//ETM data start
			@SuppressWarnings("unchecked")
			public JSONObject getDataETM(int x,HttpServletRequest request) {
				Session session1 = null;
				Transaction transaction  = null;
				JSONObject result = new JSONObject();
				try {
					//HibernateUtilVtu h = new HibernateUtilVtu();
					//session = h.getSession();
					/*String sql1 = "SELECT vehical_id, device_id, call_time, driver_name, conductor_name, route_no, schedual_no, depot_name, notes "	
							+" FROM breakdown_ccc "
							+" WHERE breakdown_type = '"+status+"'";*/
					String sql1="select dy.device_type_name as name,d.device_type_id as deviceid,d.device_serial_number as serialno"
							+" from device d " 
							+" inner join device_type dy on d.device_type_id=dy.device_type_id  and d.deleted_status='0'"
							+" where d.device_type_id in(2) "
							+" order by d.device_type_id";
				System.out.println("sql....."+sql1);
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				 transaction = session1.beginTransaction();
				Query query = session1.createSQLQuery(sql1).addScalar("name")
						.addScalar("deviceid")
						.addScalar("serialno");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						ja.add(rs.get("name") != null ? rs.get("name").toString() : "");
						//System.out.println("vehicleid="+rs.get("vehical_id"));
						//ja.add(rs.get("deviceid") != null ? rs.get("deviceid").toString() : "");
						ja.add(rs.get("serialno")!= null ? rs.get("serialno").toString() : "");
						/*ja.add(rs.get("driver_name")!= null ? rs.get("driver_name").toString() : "");
						ja.add(rs.get("conductor_name") != null ? rs.get("conductor_name").toString() : "");
						ja.add(rs.get("route_no") != null ? rs.get("route_no").toString() : "");
						ja.add(rs.get("schedual_no") != null ? rs.get("schedual_no").toString() : "");
						ja.add(rs.get("depot_name") != null ? rs.get("depot_name").toString() : "");*/
						array.add(ja);
					}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
					session1.close();
				}
				return result;
			}
			
			
			//employee data end			
//ETM data end			
//show lc ETM and LC ticket Count
			public  JSONObject getLCCount() {

				ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();
				JSONObject result = new JSONObject();
				HttpServletRequest request = ServletActionContext.getRequest();
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				try {
					
					String orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
					String orgchartid=(String)request.getSession().getAttribute("orgchartid");
					System.out.println("orgtypeid..........."+orgtypeid+"orgchartid................."+orgchartid);
					String id="!=0";
					if(orgtypeid.equals("1"))
							{
						id="!=0";
						
							}
					if(orgtypeid.equals("3"))
					{
				
						id="="+orgchartid+"";
					}
					if(orgtypeid.equals("2"))
					{
				
						id=" in(select org_chart_id as depotids from org_chart where parent_id='"+orgchartid+"')";
					}
					System.out.println("id...."+id);
					/*
					 * String strQry=
					 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
					 * +
					 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
					 * + " and a.status like 'Active%' order by TOKEN asc";
					 */
					Date startDate = new Date();
					String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					String strQry="SELECT    oc.org_name, egt.depot_id,  count(distinct egt.etim_no) as etim_no,   count(distinct egt.ticket_no) as no_of_ticket "+
							" FROM    etim_gprs_ticket egt "+
							" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "+
							" inner join    device d  on d.device_serial_number =  egt.etim_no "+
							" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "+
							" inner join    schedule s       on s.schedule_number = egt.schedule_no "+
							" inner join    schedule_type st       on st.schedule_type_id = s.schedule_type "+
							" inner  join    schedule_details sd       on sd.schedule_number = s.schedule_id "+
							" inner join    shift_type sft       on sd.shift_type_id = sft.shift_type_id "+
							" and d.status =  'ACTIVE'       and d.deleted_status = 0 "+
							" where egt.ticket_date ='"+currentDate+"' and egt.depot_id"+id+
							/*"   egt.ticket_date ='"+ ticketDate + "' and " +*/
							" and  tt.ticket_type_name ='Penalty Ticket'  " +
							/*"  and oc.parent_id='"+divisionId+"' "+*/
							" group by    egt.depot_id order by     egt.depot_id";
		System.out.println("strQry....."+strQry);
					
					
					JSONArray array = new JSONArray();
					Query query = session.createSQLQuery(strQry);
					List list = query.list();

					for (Iterator i = list.iterator(); i.hasNext();) {
						JSONArray ja = new JSONArray();
						Object[] obj = (Object[]) i.next();
						//ja.add(obj[0].toString().trim());
						
						
						
						
						ja.add("<a data-toggle='modal' role='button' href='#mymodal1234'  onclick=javascript:viewDepotLC('"
								+ obj[1].toString().trim()
								
								+ "') title='Break Location' id='break_location"
								+ "'>"
								+ obj[0].toString().trim()
								+ "</a>");
						
						
						//ja.add(obj[1].toString().trim());
						ja.add(obj[3].toString().trim());

						array.add(ja);
					}
				result.put("aaData", array);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (session != null && session.isOpen()) {
						session.close();
					}
				}
				return result;
			}
			
			//lc depotwise data
			
			public JSONObject getLCWaybillsOfDepot(String depotId)
					 {
				JSONObject result = new JSONObject();
				ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();
				
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				try {
					String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					Date startDate = new Date();
					
					String strQry = "SELECT egt.waybill_no, egt.schedule_no, st.schedule_type_name , sft.shift_type_name,egt.etim_no, egt.route_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount,"
						+" count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no,oc.org_name ,egt.trip_no,egt.shift_no"
						+" FROM etim_gprs_ticket egt  "
						+" inner join device d on d.device_serial_number = egt.etim_no "
					 	+" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "
					 	+" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "
						+"  inner join schedule s on s.schedule_number = egt.schedule_no "
						+"  inner join schedule_type st on st.schedule_type_id = s.schedule_type " 
						 +" inner join schedule_details sd on sd.schedule_number = s.schedule_id "
						+"  inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0 " 
						+"  where egt.ticket_date = '"+currentDate+"' and tt.ticket_type_name ='Penalty Ticket' and  egt.depot_id = '"+depotId+"' " 
						+" group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ";
					JSONArray array = new JSONArray();
					Query query = session.createSQLQuery(strQry);
					List list = query.list();

					for (Iterator i = list.iterator(); i.hasNext();) {
						JSONArray ja = new JSONArray();
						Object[] obj = (Object[]) i.next();

					
						ja.add(obj[0].toString().trim());
						ja.add(obj[1].toString().trim());
						ja.add(obj[2].toString().trim());
						ja.add(obj[3].toString().trim());
						ja.add(obj[4].toString().trim());
						ja.add(obj[8].toString().trim());
						ja.add(obj[6].toString().trim());
						ja.add(obj[7].toString().trim());
						/*ja.add(obj[8].toString());
						ja.add(obj[9].toString());
						ja.add(obj[10].toString());
						ja.add(obj[11].toString());*/
					
						
						array.add(ja);
					}
					result.put("aaData", array);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (session != null && session.isOpen()) {
						session.close();
					}
				}
				return result;
			}
			
			//lc depotwise data end
	
//end LC count
//	total no of ticket
			public  JSONObject getTotalTicketCount() {
				HttpServletRequest request = ServletActionContext.getRequest();
				ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();
				JSONObject result = new JSONObject();
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				try {
					/*
					 * String strQry=
					 * "Select distinct EMPLOYEE_ID ,TOKEN from employee a inner join designation_type d "
					 * +
					 * " ON a.WORKING_DESIGNATION=d.designation_type_id where a.WORKING_DESIGNATION ='91'"
					 * + " and a.status like 'Active%' order by TOKEN asc";
					 */
					
					String orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
					String orgchartid=(String)request.getSession().getAttribute("orgchartid");
					System.out.println("orgtypeid..........."+orgtypeid+"orgchartid................."+orgchartid);
					String id="!=0";
					if(orgtypeid.equals("1"))
							{
						id="!=0";
						
							}
					if(orgtypeid.equals("3"))
					{
				
						id="="+orgchartid+"";
					}
					if(orgtypeid.equals("2"))
					{
				
						id=" in(select org_chart_id as depotids from org_chart where parent_id='"+orgchartid+"')";
					}
					System.out.println("id...."+id);
					
					
					String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					System.out.println("total_ticket_count date"+currentDate);
					Date startDate = new Date();

					String strQry="SELECT    oc.org_name,  egt.depot_id,  count(distinct egt.etim_no) as etim_no,count(distinct egt.ticket_no) as no_of_ticket "+
							 "FROM    etim_gprs_ticket egt "+
							" inner join    ticket_type tt    on tt.ticket_type_id=egt.tkt_type_short_code "+
							" inner join    device d  on d.device_serial_number =  egt.etim_no "+
							" inner join    org_chart oc  on oc.org_chart_id = egt.depot_id "+
							//" inner join    schedule s       on s.schedule_number = egt.schedule_no "+
							//" inner join    schedule_type st       on st.schedule_type_id = s.schedule_type "+
							//" inner  join    schedule_details sd       on sd.schedule_number = s.schedule_id "+
							//" inner join    shift_type sft       on sd.shift_type_id = sft.shift_type_id "+
							" and d.status =  'ACTIVE'  and d.deleted_status = '0' " +
							" where  egt.ticket_date ='"+currentDate+"' and egt.depot_id"+id+
							/*"   egt.ticket_date ='"+ ticketDate + "' and " +
							"  tt.ticket_type_name ='Penalty Ticket'  " +*/
							/*"  and oc.parent_id='"+divisionId+"' "+*/
							" group by    egt.depot_id order by     egt.depot_id";
							//System.out.println("strQry....."+strQry);
					JSONArray array = new JSONArray();
					Query query = session.createSQLQuery(strQry);
					List list = query.list();

					for (Iterator i = list.iterator(); i.hasNext();) {
						JSONArray ja = new JSONArray();
						Object[] obj = (Object[]) i.next();
						//ja.add(obj[0].toString().trim());
						//ja.add(obj[1].toString().trim());
						
						ja.add("<a data-toggle='modal' role='button' href='#mymodal1235'  onclick=javascript:viewDepotTicket('"
								+ obj[1].toString().trim()
								
								+ "') title='Break Location' id='break_location"
								+ "'>"
								+ obj[0].toString().trim()
								+ "</a>");
						ja.add(obj[2].toString().trim());

						array.add(ja);
					}
				result.put("aaData", array);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (session != null && session.isOpen()) {
						session.close();
					}
				}
				return result;
			}			
			
//total pass ticket end	
			
//total depotwise ticket data
			
			public JSONObject getTicketWaybillsOfDepot(String depotId)
			 {
		JSONObject result = new JSONObject();
		ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();
		
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			Date startDate = new Date();
			
			String strQry="select egt.waybill_no, egt.schedule_no, st.schedule_type_name , sft.shift_type_name,egt.etim_no, egt.route_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, "+
				"  count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no "+
					"  FROM etim_gprs_ticket egt "+  
					"   inner join device d on d.device_serial_number = egt.etim_no "+ 
					"  inner join schedule s on s.schedule_number = egt.schedule_no "+
					"   inner join schedule_type st on st.schedule_type_id = s.schedule_type  "+
					"   inner join schedule_details sd on sd.schedule_number = s.schedule_id "+
					"   inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0  "+
					"   where egt.ticket_date = '"+currentDate+"' and egt.depot_id = '"+depotId+"'  "+
					"   group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ;";
			 
			
			System.out.println("strQry............"+strQry);
			JSONArray array = new JSONArray();
			Query query = session.createSQLQuery(strQry);
			List list = query.list();

			for (Iterator i = list.iterator(); i.hasNext();) {
				JSONArray ja = new JSONArray();
				Object[] obj = (Object[]) i.next();

			
				ja.add(obj[0].toString().trim());
				ja.add(obj[1].toString().trim());
				ja.add(obj[2].toString().trim());
				ja.add(obj[3].toString().trim());
				ja.add(obj[4].toString().trim());
				ja.add(obj[5].toString().trim());
				ja.add(obj[6].toString().trim());
				ja.add(obj[7].toString().trim());
				ja.add(obj[8].toString());
				/*ja.add(obj[9].toString());
				ja.add(obj[10].toString());
				ja.add(obj[11].toString());*/
			
				
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}
	
//depot wise data end			
			
			
//			total no of pass ticket
					public  JSONObject getTotalPassCount() {
						
						HttpServletRequest request = ServletActionContext.getRequest();
						JSONObject result = new JSONObject();
						Session session = HibernateUtil.getSession("hibernate.cfg.xml");
						try {
							String orgtypeid=(String)request.getSession().getAttribute("orgtypeid");
							String orgchartid=(String)request.getSession().getAttribute("orgchartid");
							System.out.println("orgtypeid..........."+orgtypeid+"orgchartid................."+orgchartid);
							String id="!=0";
							if(orgtypeid.equals("1"))
									{
								id="!=0";
								
									}
							if(orgtypeid.equals("3"))
							{
						
								id="="+orgchartid+"";
							}
							if(orgtypeid.equals("2"))
							{
						
								id=" in(select org_chart_id as depotids from org_chart where parent_id='"+orgchartid+"')";
							}
							System.out.println("id...."+id);
							Date startDate = new Date();
							String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
							//System.out.println("currentDate_"+currentDate);
							String strQry="SELECT    oc.org_name, egt.depot_id,count(distinct egt.etim_no) as etim_no,count(distinct egt.ticket_no) as no_of_ticket "+
									 "FROM    etim_gprs_ticket egt "+
									" inner join  ticket_type tt on tt.ticket_type_id=egt.tkt_type_short_code "+
									" inner join device d  on d.device_serial_number =  egt.etim_no "+
									" inner join  org_chart oc  on oc.org_chart_id = egt.depot_id "+
									//" inner join    schedule s       on s.schedule_number = egt.schedule_no "+
									//" inner join    schedule_type st       on st.schedule_type_id = s.schedule_type "+
									//" inner  join    schedule_details sd       on sd.schedule_number = s.schedule_id "+
									//" inner join    shift_type sft       on sd.shift_type_id = sft.shift_type_id "+
									" and d.status =  'ACTIVE' and d.deleted_status ='0' "+
									//" where   +
									/*" where  " +
									"   egt.ticket_date ='"+ ticketDate + "' and " +*/
									" where tt.ticket_type_name ='Pass Ticket' and egt.ticket_date ='"+currentDate+"' and egt.depot_id"+id+
									/*"  and oc.parent_id='"+divisionId+"' "+*/
									" group by   egt.depot_id order by  egt.depot_id";
									//System.out.println("strQry....."+strQry);
							JSONArray array = new JSONArray();
							Query query = session.createSQLQuery(strQry);
							List list = query.list();

							for (Iterator i = list.iterator(); i.hasNext();) {
								JSONArray ja = new JSONArray();
								Object[] obj = (Object[]) i.next();
								ja.add("<a data-toggle='modal' role='button' href='#mymodal123589'  onclick=javascript:viewDepotPass('"
										+ obj[1].toString().trim()
										
										+ "') title='Break Location' id='break_location"
										+ "'>"
										+ obj[0].toString().trim()
										+ "</a>");
								ja.add(obj[2].toString().trim());

								array.add(ja);
							}
						result.put("aaData", array);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (session != null && session.isOpen()) {
								session.close();
							}
						}
						return result;
					}			
					
		//total no pass ticket end	
					
					//total depotwise ticket pass data
					
				public JSONObject getTicketPassWaybillsOfDepot(String depotId)
					 {
				JSONObject result = new JSONObject();
				ArrayList<OnlineWaybills> arrayList = new ArrayList<OnlineWaybills>();
				
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				try {
					String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
					Date startDate = new Date();
					
					String strQry="select egt.waybill_no, egt.schedule_no, st.schedule_type_name , sft.shift_type_name,egt.etim_no, egt.route_no, sum(egt.px_total_amount+egt.lugg_total_amount) total_ticket_amount, "+
						"  count(distinct egt.ticket_no) as no_of_ticket,egt.vehicle_no "+
							" FROM etim_gprs_ticket egt "+  
							" inner join  ticket_type tt on tt.ticket_type_id=egt.tkt_type_short_code "+
							"  inner join device d on d.device_serial_number = egt.etim_no "+ 
							"  inner join schedule s on s.schedule_number = egt.schedule_no "+
							"   inner join schedule_type st on st.schedule_type_id = s.schedule_type  "+
							"   inner join schedule_details sd on sd.schedule_number = s.schedule_id "+
							"   inner join shift_type sft on sd.shift_type_id = sft.shift_type_id and d.status = 'ACTIVE' and d.deleted_status = 0  "+
							"   where egt.ticket_date =  '"+currentDate+"' and egt.depot_id = '"+depotId+"' and tt.ticket_type_name ='Pass Ticket'  "+
							"   group by egt.waybill_no, egt.schedule_no, egt.etim_no order by  egt.waybill_no ;";
					 
					
					System.out.println("strQry............"+strQry);
					JSONArray array = new JSONArray();
					Query query = session.createSQLQuery(strQry);
					List list = query.list();

					for (Iterator i = list.iterator(); i.hasNext();) {
						JSONArray ja = new JSONArray();
						Object[] obj = (Object[]) i.next();

					
						ja.add(obj[0].toString().trim());
						ja.add(obj[1].toString().trim());
						ja.add(obj[2].toString().trim());
						ja.add(obj[3].toString().trim());
						ja.add(obj[4].toString().trim());
						ja.add(obj[5].toString().trim());
						ja.add(obj[6].toString().trim());
						ja.add(obj[7].toString().trim());
						ja.add(obj[8].toString());
						/*ja.add(obj[9].toString());
						ja.add(obj[10].toString());
						ja.add(obj[11].toString());*/
					
						
						array.add(ja);
					}
					result.put("aaData", array);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (session != null && session.isOpen()) {
						session.close();
					}
				}
				return result;
			}
			
		//depot wise data pass data end			
					
//complaint count start
				
							public  JSONObject getTotalComplaintCount() {
								JSONObject result = new JSONObject();
								Session session = HibernateUtil.getSession("hibernate.cfg.xml");
								try {
									String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									Date startDate = new Date();

									String strQry="select count(complaint_id) as complaint_count from complaint where deleted_status='0' and status='active' and created_date between '"+currentDate+" 00.00.00' and '"+currentDate+" 23.59.59'";
											//System.out.println("strQry....."+strQry);
									JSONArray array = new JSONArray();
									Query query = session.createSQLQuery(strQry);
									query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
									List<Map<String, Object>> aliasToValueMapList = query.list();
									
									for (int i = 0; i < aliasToValueMapList.size(); i++) {
										JSONArray ja = new JSONArray();
										Map<String, Object> rs = aliasToValueMapList.get(i);
										ja.add("complaint");
										ja.add(rs.get("complaint_count") != null ? rs.get("complaint_count").toString() : "");
										array.add(ja);
									}
								result.put("aaData", array);
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (session != null && session.isOpen()) {
										session.close();
									}
								}
								return result;
							}			
//complaint count end					
//Total trip  count start
							public  JSONObject getTotalTripCount() {
								JSONObject result = new JSONObject();
								Session session = HibernateUtil.getSession("hibernate.cfg.xml");
								try {
									String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									Date startDate = new Date();
									String strQry="select sum(if(TRIP_STATUS='C',1,0)) AS total_no_trip_cancel,sum(if(TRIP_STATUS='y',1,0)) as toal_no_trip_completed ,DEPOT_CD as depot_name from waybill_trip_details " +
											" where duty_start_date between '2015-02-14 00:00:00' and '2015-02-16 23:59:59' group by depot_id ";

											System.out.println("strQry....."+strQry);
									JSONArray array = new JSONArray();
									Query query = session.createSQLQuery(strQry).addScalar("total_no_trip_cancel").addScalar("toal_no_trip_completed").addScalar("depot_name");
									query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
									List<Map<String, Object>> aliasToValueMapList = query.list();
									
									for (int i = 0; i < aliasToValueMapList.size(); i++) {
										JSONArray ja = new JSONArray();
										Map<String, Object> rs = aliasToValueMapList.get(i);
									int total=(Integer.parseInt(rs.get("total_no_trip_cancel").toString())+Integer.parseInt(rs.get("toal_no_trip_completed").toString()));
									
										ja.add(rs.get("depot_name"));
										ja.add(total);
										ja.add(rs.get("total_no_trip_cancel") != null ? rs.get("total_no_trip_cancel").toString() : "");
										ja.add(rs.get("toal_no_trip_completed") != null ? rs.get("toal_no_trip_completed").toString() : "");
										array.add(ja);
									}
								result.put("aaData", array);
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (session != null && session.isOpen()) {
										session.close();
									}
								}
								return result;
							}			
//trip count end	
							
//toatal no. schedule count		
							public  JSONObject getTotalScheduleCount() {
								JSONObject result = new JSONObject();
								Session session = HibernateUtil.getSession("hibernate.cfg.xml");
								try {
									String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									Date startDate = new Date();
									
									String strQry="select ifnull(sum(if(status='closed',1,0 )),0) as total_cancle_schedule ,ifnull(sum(if(status='online',1,0)),0) as active_schedule,DEPOT_CD as depot_name from waybill_trip_details " +
											"where duty_start_date between '2015-02-14 00:00:00' and '2015-02-16 23:59:59' group by depot_id";
									
									//System.out.println("strQry....."+strQry);
									JSONArray array = new JSONArray();
									Query query = session.createSQLQuery(strQry).addScalar("total_cancle_schedule").addScalar("active_schedule").addScalar("depot_name");
									query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
									List<Map<String, Object>> aliasToValueMapList = query.list();
									
									for (int i = 0; i < aliasToValueMapList.size(); i++) {
										JSONArray ja = new JSONArray();
										Map<String, Object> rs = aliasToValueMapList.get(i);
										int total=(Integer.parseInt(rs.get("total_cancle_schedule").toString())+Integer.parseInt(rs.get("active_schedule").toString()));
										ja.add(rs.get("depot_name"));
										ja.add(total);
										ja.add(rs.get("total_cancle_schedule") != null ? rs.get("total_cancle_schedule").toString() : "");
										ja.add(rs.get("active_schedule") != null ? rs.get("active_schedule").toString() : "");
										array.add(ja);
									}
								result.put("aaData", array);
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (session != null && session.isOpen()) {
										session.close();
									}
								}
								return result;
							}							
//toatal no. schedule count end	
//total no. route
							
							public  JSONObject getTotalRouteCount() {
								
								JSONObject result = new JSONObject();
								Session session = HibernateUtil.getSession("hibernate.cfg.xml");
								try {
									String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									Date startDate = new Date();
									
									String strQry="select count(ROUTE_NO) as route_count, DEPOT_CD  as depot_name from waybill_trip_details where duty_start_date between '2015-02-14 00:00:00' and '2015-02-14 23:59:59' " 
											+" group by depot_id ";
									
									//System.out.println("strQry....."+strQry);
									JSONArray array = new JSONArray();
									Query query = session.createSQLQuery(strQry).addScalar("route_count").addScalar("depot_name");
									query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
									List<Map<String, Object>> aliasToValueMapList = query.list();
									
									for (int i = 0; i < aliasToValueMapList.size(); i++) {
										JSONArray ja = new JSONArray();
										Map<String, Object> rs = aliasToValueMapList.get(i);
										ja.add(rs.get("depot_name"));
										ja.add(rs.get("route_count") != null ? rs.get("route_count").toString() : "");
										array.add(ja);
									}
								result.put("aaData", array);
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (session != null && session.isOpen()) {
										session.close();
									}
								}
								return result;
							}							
		
//total no. route end
//total Bus skip count
							
							public  JSONObject getTotalBusSkipCount() {
								JSONObject result = new JSONObject();
								Session session = HibernateUtil.getSession("hibernate.cfg.xml");
								try {
									String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									Date startDate = new Date();
									
									String strQry="select count(ROUTE_NO) as route_count, DEPOT_CD  as depot_name from waybill_trip_details where duty_start_date between '2015-02-14 00:00:00' and '2015-02-14 23:59:59' " 
											+" group by depot_id ";
									
									//System.out.println("strQry....."+strQry);
									JSONArray array = new JSONArray();
									Query query = session.createSQLQuery(strQry).addScalar("route_count").addScalar("depot_name");
									query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
									List<Map<String, Object>> aliasToValueMapList = query.list();
									
									for (int i = 0; i < aliasToValueMapList.size(); i++) {
										JSONArray ja = new JSONArray();
										Map<String, Object> rs = aliasToValueMapList.get(i);
										ja.add(rs.get("depot_name"));
										ja.add(rs.get("route_count") != null ? rs.get("route_count").toString() : "");
										array.add(ja);
									}
								result.put("aaData", array);
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if (session != null && session.isOpen()) {
										session.close();
									}
								}
								return result;
							}							
		
//total bus skip end		
				/*public String getDepotidByorgchartid(String id)
				{
					String ids="";
					Session session = HibernateUtil.getSession("hibernate.cfg.xml");
					String strQry="select group_concat(org_chart_id) as depotids from org_chart where parent_id='"+id+"' " ;

							
					
					//System.out.println("strQry....."+strQry);
					JSONArray array = new JSONArray();
					Query query = session.createSQLQuery(strQry).addScalar("depotids");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						JSONArray ja = new JSONArray();
						Map<String, Object> rs = aliasToValueMapList.get(i);
						ids=rs.get("depotids").toString();
						
					}
					
					
					
					return ids;
				}*/
							
}
