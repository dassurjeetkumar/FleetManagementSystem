/*package com.trimax.its.pis.dao;

import java.util.List;
import java.util.Map;

import model.jaxb.xml.pis.trimax.com.PisResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.pis.service.GetPIS;
import com.trimax.its.pis.service.WsPis;
import com.trimax.its.pis.service.WsPis_Service;
import com.trimax.its.util.HibernateUtilVtu;

public class PISDao {

	// Method for get data from DB
	public List<GetPIS> getData(String location,
			String platform) {
		List<GetPIS> getList = null;
		try {
			WsPis_Service service = new WsPis_Service();
			WsPis port = service.getWsPisPort();
			PisResponse result = port.getPISDetails(location, platform, "", "");
			// Calling Web Service Method here
			for (int i = 0; i < result.getVtsDatamodel().size(); i++) {
				getList = result.getVtsDatamodel();
			}
		} catch (Exception ex) {
			ex.printStackTrace(); // Statement For Catching Exception
		}
		return getList;

	}

	@SuppressWarnings("unchecked")
	public JSONObject getPisLocations() {
		// TODO Auto-generated method stub
		JSONObject result = null;
		try {
			result = new JSONObject();
			Session session = new HibernateUtilVtu().getSession();
			String sqlQuery = "select end_point, end_bus_stop_name from waybill_trip_details "
					+ "	where trip_status = \"Y\" "
					+ "	group by end_point "
					+ "	order by end_bus_stop_name";
			Query query = session.createSQLQuery(sqlQuery)
					.addScalar("end_point", Hibernate.STRING)
					.addScalar("end_bus_stop_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(rs.get("end_point").toString());
				ja.add(rs.get("end_bus_stop_name").toString());
				array.add(ja);
			}
			result.put("locData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getPisPlatformLocations(int endstopId) {
		// TODO Auto-generated method stub
				JSONObject result = null;
				try {
					result = new JSONObject();
					Session session = new HibernateUtilVtu().getSession();
					String sqlQuery = "select ID, PLATFORM_NO from waybill_trip_details "
							+ "	where trip_status = \"Y\"  and END_POINT ="+endstopId+" "
							+ " group by PLATFORM_NO"
							+ "	order by ID,PLATFORM_NO";
					System.out.println("sqlQuery ====  " + sqlQuery);
					Query query = session.createSQLQuery(sqlQuery)
							.addScalar("ID", Hibernate.STRING)
							.addScalar("PLATFORM_NO", Hibernate.STRING);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					JSONArray array = new JSONArray();
					System.out.println(aliasToValueMapList.size());
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						JSONArray ja = new JSONArray();
						ja.add(rs.get("ID").toString());
						ja.add(rs.get("PLATFORM_NO").toString());
						array.add(ja);
					}
					result.put("pfData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {

				}
				return result;
			}
}
*/