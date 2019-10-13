package com.trimax.its.vts.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.trimax.its.util.HibernateUtil;

public class ChangeVehicleStatusDAO {
	
	int qry1 = 0;
	int qry2 = 0;
	String rbKey = String.valueOf(getSysParameterForVts());
	com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
	com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
	
	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return param;
	}
	public int updateVehicleNormal(String vehicle_no) {
		qry2 = 0;
		
		Session session3 = null;
		Transaction tx = null;
		try {
			session3 = HibernateUtil.getSession("hibernate.cfg.xml");
			tx = session3.beginTransaction();
			String sql2 = " UPDATE vehicle SET cause_status = 'N' WHERE license_number= '"
							+ vehicle_no
							+"' AND deleted_status=0 AND status = 'ACTIVE' ";
			SQLQuery query = session3.createSQLQuery(sql2);
			qry2 = query.executeUpdate();
			
			if(qry2>0){
				tx.commit();
				port.updateVehicleDeviceRelation("N",vehicle_no , rbKey);
			}else{
				tx.rollback();
			}				
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			if (session3 != null) {
				session3.close();
			}
		}
		return qry2;
	}
		public int insertAccidentDetail(int user_id,String username, String call_id,
				String device_id, String call_time, String vehicle_no,
				String depot_name, String Schedule_no, String call_type,
				String description, String accident_type, String breakdown_type,String depotid, String driverToken) {
			qry1 = 0;
			qry2 = 0;
			String str = "";
			Session session3 = null;
			Transaction tx = null;
			try {
				String sql = "insert into accident_ccc(vehical_id,call_id,device_id,call_time," +
						"call_type,schedual_no,depot_name,notes,accident_type_id,created_by,attended_by_id,attended_by_name,depot_id,driver_token_no) values('"
						+ vehicle_no
						+ "','"
						+ call_id
						+ "','"
						+ device_id
						+ "','"
						+ call_time						
						+ "','"
						+ call_type
						+ "','"						
						+ Schedule_no
						+ "','"
						+ depot_name
						+ "','"
						+ description
						+ "','" + accident_type + "'," + user_id + "," + user_id + ",'" + username + "','" + depotid + "','" + driverToken + "')";

				session3 = HibernateUtil.getSession("hibernate.cfg.xml");
				tx = session3.beginTransaction();
				SQLQuery query = session3.createSQLQuery(sql);
				qry1 = query.executeUpdate();
				String sql2 = " UPDATE vehicle SET cause_status = 'A' WHERE license_number= '"
								+ vehicle_no
								+"' AND deleted_status=0 AND status = 'ACTIVE' ";
				query = session3.createSQLQuery(sql2);
				qry2 = query.executeUpdate();
				
				if(qry1>0 && qry2>0){
					tx.commit();
					port.updateVehicleDeviceRelation("A",vehicle_no , rbKey);
				}else{
					tx.rollback();
				}				
			} catch (Exception ex) {
				ex.printStackTrace();
				tx.rollback();
			} finally {
				if (session3 != null) {
					session3.close();
				}
			}
			return qry2;
		}
		
		public int insertBreakDownDetail(int user_id,String username, String call_id,
				String device_id, String call_time, String vehicle_no,
				String depot_name, String Schedule_no, String call_type,
				String description, String accident_type, String breakdown_type,String depotid, String driverToken) {
			qry1 = 0;
			qry2 = 0;
			String str = "";
			Session session3 = null;
			Transaction tx = null;
			try {
				String sql = "insert into breakdown_ccc(vehical_id,call_id,device_id,call_time," +
						"call_type,schedual_no,depot_name,notes,breakdown_type_id,created_by,attended_by_id,attended_by_name,depot_id,driver_token_no) values('"
						+ vehicle_no
						+ "','"
						+ call_id
						+ "','"
						+ device_id
						+ "','"
						+ call_time						
						+ "','"
						+ call_type
						+ "','"						
						+ Schedule_no
						+ "','"
						+ depot_name
						+ "','"
						+ description
						+ "','" + breakdown_type + "'," + user_id + "," + user_id + ",'" + username + "','" + depotid + "','" + driverToken + "')";

				session3 = HibernateUtil.getSession("hibernate.cfg.xml");
				tx = session3.beginTransaction();
				SQLQuery query = session3.createSQLQuery(sql);
				qry1 = query.executeUpdate();
				String sql2 = " UPDATE vehicle SET cause_status = 'A' WHERE license_number= '"
								+ vehicle_no
								+"' AND deleted_status=0 AND status = 'ACTIVE' ";
				query = session3.createSQLQuery(sql2);
				qry2 = query.executeUpdate();
				
				if(qry1>0 && qry2>0){
					tx.commit();
					port.updateVehicleDeviceRelation("A",vehicle_no , rbKey);
				}else{
					tx.rollback();
				}				
			} catch (Exception ex) {
				ex.printStackTrace();
				tx.rollback();
			} finally {
				if (session3 != null) {
					session3.close();
				}
			}
			return qry2;
		}

}
