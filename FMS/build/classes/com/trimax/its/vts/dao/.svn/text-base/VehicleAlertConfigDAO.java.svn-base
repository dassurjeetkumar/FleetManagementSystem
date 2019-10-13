package com.trimax.its.vts.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.model.VehicleAlertConfig;
import com.trimax.its.vts.model.VtsAlertMaster;

public class VehicleAlertConfigDAO {
	/**
	 * @getTotalVehicleAlertConfigDataRecords
	 * @return
	 */
	public int getTotalVehicleAlertConfigDataRecords(
			HttpServletRequest request, String col, String dir) {
		Session alertconfigsession = null;
		int cnt = 0;
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			alertconfigsession = new HibernateUtilVtu().getSession("");
			String sql = " select c.VTS_ALERT_CONFIG_ID,c.TIME_DURATION,m.ALERT_DESC from vts_alert_config c inner join vts_alert_master m on c.VTS_ALERT_CONFIG_ID="
					+ " m.ID where c.RECORD_STATUS='Y'";

			System.out
					.println("\n \t JSONObject getTotalVehicleAlertConfigData 1111..............");
			int srch = 0;
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and ( c.TIME_DURATION='" + search_term + "'";
				sql += " OR m.ALERT_DESC like '%" + search_term + "%')";

			}

			System.out.println("sql----" + sql);
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("VTS_ALERT_CONFIG_ID", Hibernate.STRING)
					.addScalar("TIME_DURATION", Hibernate.STRING)
					.addScalar("ALERT_DESC", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			cnt = aliasToValueMapList.size();
			System.out
					.println("inside getTotalVehicleAlertConfigDataRecords..."
							+ cnt);

		} catch (Exception e) {
			System.out
					.println("\n \t Inside WaybillDAO Class with Exception in getTotalRecordsOfWayBill Method as : "
							+ e.getMessage());
			return cnt;
		} finally {
			if (alertconfigsession != null) {
				alertconfigsession.close();
			}
		}
		return cnt;

	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	/**
	 * @getTotalVehicleAlertConfigData
	 * @param total
	 * @param request
	 * @param cols
	 * @param dir
	 * @return
	 */
	public JSONObject getTotalVehicleAlertConfigData(int total,
			HttpServletRequest request, String col, String dir) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			// HibernateUtilVtu h = new HibernateUtilVtu();
			Session alertconfigsession = new HibernateUtilVtu().getSession("");
			String sql = "select c.VTS_ALERT_CONFIG_ID,c.TIME_DURATION,m.ALERT_DESC from vts_alert_config c inner join vts_alert_master m on c.VTS_ALERT_MASTER_ID="
					+ " m.ID where c.RECORD_STATUS='Y'";
			// Criteria criteria =
			// alertconfigsession.createCriteria(VehicleAlertConfig.class);
			// criteria.createAlias("vtsalertmaster", "vtsalertmst");
			// criteria.add(Restrictions.in("RECORD_STATUS",
			// Arrays.asList("Y")));

			int srch = 0;
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				sql += " and ( c.TIME_DURATION='" + search_term + "'";
				sql += " OR m.ALERT_DESC like '%" + search_term + "%')";

			}
			/*
			 * if (!request.getParameter("sSearch").equals("")) { String
			 * search_term = request.getParameter("sSearch").trim();
			 * 
			 * Junction conditionGroup = Restrictions.disjunction();
			 * 
			 * conditionGroup.add(Restrictions.like("MISC_BYTES", "%" +
			 * search_term + "%"));
			 * 
			 * conditionGroup.add(Restrictions.like("TIME_DURATION", "%" +
			 * search_term + "%"));
			 * 
			 * 
			 * criteria.add(conditionGroup);
			 * 
			 * }
			 */
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("VTS_ALERT_CONFIG_ID", Hibernate.STRING)
					.addScalar("TIME_DURATION", Hibernate.STRING)
					.addScalar("ALERT_DESC", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2 = null;
			aliasToValueMapList2 = query.list();

			JSONArray arraytickstock = new JSONArray();
			// System.out.println("List size----->" + list.size() +
			// list.get(0).getValue()+"\t"
			// + request.getParameter("iDisplayStart"));
			for (int i = 0; i < aliasToValueMapList2.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList2.get(i);
				JSONArray jatickstock = new JSONArray();

				jatickstock
						.add("<input type='checkbox' class='group-checkable checkbox1' name='chkbox' data-set='#sample_2 .checkboxes' id='"
								+ rs.get("VTS_ALERT_CONFIG_ID")
								+ "' value='"
								+ rs.get("VTS_ALERT_CONFIG_ID") + "'/>");

				jatickstock.add(rs.get("ALERT_DESC"));
				jatickstock.add(rs.get("TIME_DURATION"));

				arraytickstock.add(jatickstock);

			}

			/*
			 * query.setFirstResult(Integer.parseInt(request
			 * .getParameter("iDisplayStart")));
			 * query.setMaxResults(Integer.parseInt(request
			 * .getParameter("iDisplayLength")));
			 * criteria.setFirstResult(Integer.parseInt(request
			 * .getParameter("iDisplayStart")));
			 * criteria.setMaxResults(Integer.parseInt(request
			 * .getParameter("iDisplayLength")));
			 * 
			 * List<VehicleAlertConfig> list = criteria.list();
			 * System.out.println
			 * ("\n \t JSONObject getData 22222..............");
			 * 
			 * JSONArray array = new JSONArray();
			 * System.out.println("List size----->" + list.size() + "\t" +
			 * request.getParameter("iDisplayStart")); for (int i = 0; i <
			 * list.size(); i++) {
			 * 
			 * JSONArray ja = new JSONArray();
			 * 
			 * ja.add(
			 * "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
			 * + list.get(i).getVTS_ALERT_CONFIG_ID() + "' value='" +
			 * list.get(i).getVTS_ALERT_CONFIG_ID() + "'/>");
			 * 
			 * 
			 * 
			 * 
			 * ja.add(list.get(i).getVtsalertmaster().getALERT_DESC());
			 * ja.add(""); ja.add(list.get(i).getTIME_DURATION());
			 * 
			 * array.add(ja); }
			 */
			/*
			 * if (!request.getParameter("sSearch").equals("")) {
			 * totalAfterFilter = aliasToValueMapList2.size();
			 * result.put("aaData", arraytickstock); result.put("iTotalRecords",
			 * total);
			 * 
			 * result.put("iTotalDisplayRecords", aliasToValueMapList2.size());
			 * } else { totalAfterFilter =
			 * getTotalVehicleAlertConfigDataRecords(request,col,dir);
			 * result.put("aaData", arraytickstock); result.put("iTotalRecords",
			 * total);
			 * 
			 * result.put("iTotalDisplayRecords", totalAfterFilter); }
			 */

			result.put("aaData", arraytickstock);
			String search_term2 = request.getParameter("sSearch");

			String search_term3 = request.getParameter("sSearch").trim();
			int cnt = getTotalVehicleAlertConfigDataRecords(request, col, dir);

			result.put("iTotalRecords", cnt);

			result.put("iTotalDisplayRecords", cnt);

			/*
			 * result.put("aaData", arraytickstock); result.put("iTotalRecords",
			 * total);
			 * 
			 * result.put("iTotalDisplayRecords", totalAfterFilter);
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	public List<String> getVtsAlertMasterId() {
		Session alertconfigsession = null;
		List list = null;
		try {
			alertconfigsession = new HibernateUtilVtu().getSession("");
			list = new ArrayList();

			String qry = "select ID from vts_alert_master where RECORD_STATUS='Y'";

			Query query = alertconfigsession.createSQLQuery(qry).addScalar(
					"ID", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("ID").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (alertconfigsession != null && alertconfigsession.isOpen()) {
				alertconfigsession.flush();
				alertconfigsession.close();

			}

		}
		return list;

	}

	public List<String> getVtsAlertDescriptionName() {
		Session alertconfigsession = null;
		List list = null;
		try {
			alertconfigsession = new HibernateUtilVtu().getSession("");
			list = new ArrayList();

			String qry = "select ALERT_DESC from vts_alert_master where RECORD_STATUS='Y'";
			Query query = alertconfigsession.createSQLQuery(qry).addScalar(
					"ALERT_DESC", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("ALERT_DESC").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (alertconfigsession != null && alertconfigsession.isOpen()) {
				alertconfigsession.flush();
				alertconfigsession.close();

			}

		}
		return list;

	}

	public String getPacketCodeMisByteData(String alertDescId) {
		Session sessionpasspriority = null;
		String packetcode = "";
		try {
			sessionpasspriority = new HibernateUtilVtu().getSession("");

			packetcode = sessionpasspriority
					.createSQLQuery(
							"select PACKET_CODE from vts_alert_master where DASHBOARD_VIEW=1 and RECORD_STATUS='Y' and ID="
									+ alertDescId + "").uniqueResult()
					.toString();
			System.out.println("ticket type priority.." + packetcode);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessionpasspriority != null && sessionpasspriority.isOpen()) {
				sessionpasspriority.close();
				sessionpasspriority = null;
			}

		}

		return packetcode;

	}

	public String getAlertDecriptionName(String alertDescId) {
		Session sessionpasspriority = null;
		String alertDecsriptionName = "";
		try {
			sessionpasspriority = new HibernateUtilVtu().getSession("");

			alertDecsriptionName = sessionpasspriority
					.createSQLQuery(
							"select MISC_BYTES from vts_alert_master where DASHBOARD_VIEW=1 and RECORD_STATUS='Y' and ID="
									+ alertDescId + "")
					.addScalar("MISC_BYTES", Hibernate.STRING).uniqueResult()
					.toString();
			System.out.println("ticket type priority.." + alertDecsriptionName);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (sessionpasspriority != null && sessionpasspriority.isOpen()) {
				sessionpasspriority.close();
				sessionpasspriority = null;
			}

		}

		return alertDecsriptionName;

	}
	
	//Getting ORG_CHART_ID and ORG_TYPE_ID
	public String [] getOrgChartIdandType(String bus_stop_id) {
		Session session = null;
		String getOrgChart = "";
		String [] orgArr =null;
		try {
			session = HibernateUtil.getSession("");
			orgArr = new String [2];
			getOrgChart = "select bs.org_chart_id,org.org_type_id from bus_stop bs inner join org_chart org on bs.org_chart_id= org.org_chart_id " +
					" where bs.bus_stop_id='"+bus_stop_id+"' and org.deleted_status=0 ";
			Query query = session.createSQLQuery(getOrgChart);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				orgArr[0] = rs.get("org_chart_id").toString();
				orgArr[1] = rs.get("org_type_id").toString();
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				
			}

		}
		return orgArr;
	}
	//
	
	public void inserGeofenceStorage(String bus_stop_id,String name,double Lat,double LNG,String orgId,String OrgTypeId) {
		Session session = null;
		String geofenceStorage = "";
		
		Transaction tx=null;
		try {
			session = HibernateUtil.getSession("");
			
			geofenceStorage = "insert ignore into geofence_storage(ORG_ID,BUS_STOP_ID,ORG_NAME,LAT,LNG,RADIUS,ORG_TYPE_ID,deleted_status,inserted_date)" +
					" values('"+orgId+"','"+bus_stop_id+"','"+name+"','"+Lat+"','"+LNG+"','50','"+OrgTypeId+"','0',now())";
			Query query = session.createSQLQuery(geofenceStorage);
			query.executeUpdate();
			tx=session.beginTransaction();
			tx.commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				
			}

		}
		
	}

	public int saveAlertConfigDetails(VehicleAlertConfig vehiclealertconfig) {
		Session session = null;
		int i = 0;
		try {
			session = new HibernateUtilVtu().getSession("");

			session.beginTransaction();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			/*
			 * String sql="insert into vts_alert_config" +
			 * "(VTS_ALERT_MASTER_ID,PACKET_CODE,MISC_BYTES,TIME_DURATION,CREATED_BY,RECORD_STATUS,UPDATED_BY) "
			 * +
			 * " values('"+vehiclealertconfig.getVtsalertmaster().getVts_id()+"','"
			 * +vehiclealertconfig.getPACKET_CODE()+"'," +
			 * "'"+vehiclealertconfig
			 * .getMISC_BYTES()+"','"+vehiclealertconfig.getTIME_DURATION()+"',"
			 * + "'"+usrsessionid+"','Y','0')";
			 * System.out.println("SQL IS..."+sql); Query query =
			 * session.createSQLQuery(sql); i = query.executeUpdate();
			 */
			vehiclealertconfig.setCREATED_DATE(new Date());

			vehiclealertconfig.setCREATED_BY(usrsessionid);
			vehiclealertconfig.setRECORD_STATUS("Y");
			i = (Integer) session.save(vehiclealertconfig);
			session.getTransaction().commit();
			System.out.println("end of commit");
		} catch (Exception e) {
			e.printStackTrace();

			// session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return i;

	}

	public VehicleAlertConfig getVehicleAlertConfigDetails(int id) {
		Session session;
		VehicleAlertConfig vehiclealertconfig = null;
		try {
			session = new HibernateUtilVtu().getSession("");
			vehiclealertconfig = (VehicleAlertConfig) session.get(
					VehicleAlertConfig.class, new Integer(id));
			System.out.println("--------->>>"
					+ vehiclealertconfig.getPACKET_CODE());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vehiclealertconfig;

	}

	public void deleteVehicleAlertConfig(int id) {
		Session session = null;

		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			session = new HibernateUtilVtu().getSession("");
			session.beginTransaction();
			VehicleAlertConfig vehiclealertconfig = (VehicleAlertConfig) session
					.get(VehicleAlertConfig.class, id);

			vehiclealertconfig.setRECORD_STATUS("N");
			HttpServletRequest request = ServletActionContext.getRequest();
			vehiclealertconfig.setUPDATED_BY(Integer.parseInt(request
					.getSession().getAttribute("userid").toString()));
			vehiclealertconfig.setUPDATED_DATE(new java.util.Date());

			session.update(vehiclealertconfig);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {

			if (session != null) {
				session.close();

			}
		}
	}

	public void saveEditedTicketBagDetails(String requestType, int id,
			VehicleAlertConfig vehiclealertconfig) {
		Session session = null;
		try {
			session = new HibernateUtilVtu().getSession("");
			session.beginTransaction();
			String sql = "update vts_alert_config set TIME_DURATION="
					+ vehiclealertconfig.getTIME_DURATION()
					+ " where VTS_ALERT_CONFIG_ID='"
					+ vehiclealertconfig.getVTS_ALERT_CONFIG_ID() + "'";
			System.out.println("SQL IS..." + sql);
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			/*
			 * //Page_Master page_Master= (BusStops)
			 * session.get(Page_Master.class, id);
			 * System.out.println("hi in dao"
			 * +vehiclealertconfig.getVTS_ALERT_CONFIG_ID()); HttpServletRequest
			 * request=ServletActionContext.getRequest(); HttpSession
			 * sess=request.getSession(); VehicleAlertConfig
			 * vehicle_alert_config
			 * =(VehicleAlertConfig)session.get(VehicleAlertConfig.class, id);
			 * 
			 * vehicle_alert_config.setMISC_BYTES(vehicle_alert_config.getMISC_BYTES
			 * ());
			 * vehicle_alert_config.setPACKET_CODE(vehiclealertconfig.getPACKET_CODE
			 * ()); vehicle_alert_config.setTIME_DURATION(vehiclealertconfig.
			 * getTIME_DURATION()); VtsAlertMaster vtsalertmst=new
			 * VtsAlertMaster();
			 * vtsalertmst.setALERT_DESC(vehiclealertconfig.getVtsalertmaster
			 * ().getALERT_DESC());
			 * vehicle_alert_config.setVtsalertmaster(vtsalertmst);
			 * 
			 * //shift_master.getScheduletype().setSchedule_type_id(1); int
			 * usrsessionid
			 * =(Integer)request.getSession().getAttribute("userid");
			 * vehicle_alert_config.setUPDATED_BY(usrsessionid);
			 * vehicle_alert_config.setUPDATED_DATE(new Date());
		 	 * session.update(vehicle_alert_config);
			 */
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public int checkDuplicateInsertAlertConfig(
			VehicleAlertConfig vehiclealertconfig) {
		Session session = null;
		int duplicatecount = 0;
		try {
			session = new HibernateUtilVtu().getSession("");

			session.beginTransaction();

			String sql = "select IFNULL(count(*),0) from vts_alert_config where "
					+ " VTS_ALERT_MASTER_ID='"
					+ vehiclealertconfig.getVtsalertmaster().getVts_id()
					+ "' AND PACKET_CODE='"
					+ vehiclealertconfig.getPACKET_CODE()
					+ "' AND MISC_BYTES='"
					+ vehiclealertconfig.getMISC_BYTES()

					+ "' AND "
					+ " RECORD_STATUS='Y'";
			System.out.println("SQL IS..." + sql);

			duplicatecount = Integer.parseInt(session.createSQLQuery(sql)
					.uniqueResult().toString());
			System.out.println("ticket type priority.." + duplicatecount);
			// TODO Auto-generated method stub
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}

			return duplicatecount;
		}
	}
}
