package com.trimax.its.vts.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.*;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.util.HibernateUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test().updateRouteFenceString(0);
	}
	public int updateRouteFenceString(int route_id) {
		// TODO Auto-generated method stub
		Session session = null;
		String concatsql = "";
		System.out.println("route_id=======>>" + route_id);
		String getRouteID= "SELECT route_id FROM `route_geofence` WHERE `route_string` != '' group by route_id;";
		session = HibernateUtil.getSession("");
		Query query1 = session.createSQLQuery(getRouteID)
				.addScalar("route_id", Hibernate.STRING);
		query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList1 = query1.list();
		for (int x = 0; x < aliasToValueMapList1.size(); x++) {
			Map<String, Object> rs1 = aliasToValueMapList1.get(x);
		try {
			session = HibernateUtil.getSession("");
			String strsql = "select group_concat(route_string) route_string from route_geofence where route_id="
					+ rs1.get("route_id") + " ";
			Query querystr = session.createSQLQuery(strsql).addScalar(
					"route_string", Hibernate.STRING);
			querystr.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = querystr.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				concatsql += rs.get("route_string").toString();

			}
			System.out.println("concatsql" + concatsql);
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "update route_geofence set route_fence_string=GeomFromText('LINESTRING("
					+ concatsql + ")') where route_id=" + rs1.get("route_id") + " ";
			concatsql="";
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		}
		return 0;
	}

}
