package com.trimax.its.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.Menu;
import com.trimax.its.util.HibernateUtil;

public class DashBoardDao {

	public DashBoardParameter getDashBoardParams() {
		// TODO Auto-generated method stub
		String sql = "select reported_date,vtu_count,etm_count,pis_count,"
				+ " operated_schedule,revenue,running_km	 from device_revenue_count where reported_date=date(now()) ;";
		Session session = null;
		DashBoardParameter param=null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(sql)
					.addScalar("reported_date", Hibernate.INTEGER)
					.addScalar("vtu_count", Hibernate.INTEGER)
					.addScalar("etm_count", Hibernate.INTEGER)
					.addScalar("pis_count", Hibernate.INTEGER)
					.addScalar("operated_schedule", Hibernate.INTEGER)
					.addScalar("revenue", Hibernate.DOUBLE)
					.addScalar("running_km", Hibernate.DOUBLE)
					;
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> dashboardList=query.list();
			for (int i=0;i<dashboardList.size();i++) {		
				param=new DashBoardParameter(); 
				Map<String, Object> rs = dashboardList.get(i);
				param.setEtmcount((Integer)rs.get("etm_count"));
				param.setVtucount((Integer)rs.get("vtu_count"));
				param.setPiscount((Integer)rs.get("pis_count"));
				param.setOpsch((Integer)rs.get("operated_schedule"));
				param.setRevenue((Double)rs.get("revenue"));
				param.setRunningKm((Double)rs.get("running_km"));
				//param.setR((Integer)rs.get("reported_date"));
//System.out.println(menu.getPageId()+"|"+menu.getPageName()+"|"+menu.getPath()+"|"+menu.getGroupId());
			}
		} catch (Exception e) {
			System.out.println(e);
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in UserDao", e);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);// save error log to db
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}
		return param;
	}
	
	public JSONObject getDataForWeeklyRevenue() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		Session session3 = null;
		HibernateUtil h = new HibernateUtil();
		try {
			String sql = " select concat(DAY(reported_date),'/',YEAR(reported_date)) days,revenue from device_revenue_count\r\n" + 
					"where DAY(reported_date) < DAY(now()) and MONTH(reported_date)=MONTH(now())\r\n" + 
					"group by reported_date;  ";
			JSONArray array = new JSONArray();
			session3 = h.getSession("");
			Query query = session3.createSQLQuery(sql)
					.addScalar("days", Hibernate.STRING)
					.addScalar("revenue", Hibernate.DOUBLE);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(rs.get("days").toString());
				ja.add(Double.parseDouble(rs.get("revenue").toString()));
				array.add(ja);
			}
			// totalAfterFilter = getTotalPageRecords(fromDate,tillDate);
			result.put("aaData", array);
			result.put("iTotalRecords", aliasToValueMapList.size());
			result.put("iTotalDisplayRecords", aliasToValueMapList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return result;
	}
	
	public JSONObject getDataForMonthlyRevenue() {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		Session session3 = null;
		HibernateUtil h = new HibernateUtil();
		try {
			String sql = " select reported_date,MONTHNAME(reported_date) Month ,sum(revenue) revenue from device_revenue_count\r\n" + 
					"where  MONTH(reported_date) < MONTH(now())\r\n" + 
					"group by MONTH(reported_date); ";
			JSONArray array = new JSONArray();
			session3 = h.getSession("");
			Query query = session3.createSQLQuery(sql)
					.addScalar("Month", Hibernate.STRING)
					.addScalar("revenue", Hibernate.DOUBLE);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(rs.get("Month").toString());
				ja.add(Double.parseDouble(rs.get("revenue").toString()));
				array.add(ja);
			}
			// totalAfterFilter = getTotalPageRecords(fromDate,tillDate);
			result.put("aaData", array);
			result.put("iTotalRecords", aliasToValueMapList.size());
			result.put("iTotalDisplayRecords", aliasToValueMapList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return result;
	}	


}
