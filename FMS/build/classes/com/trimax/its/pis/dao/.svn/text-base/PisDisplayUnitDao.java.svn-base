package com.trimax.its.pis.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.pis.model.DisplayUnitModel;
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.vehicle.model.OrganisationChart;

public class PisDisplayUnitDao {

	public JSONObject getPisDisplayUnitData(int total,
			HttpServletRequest request, String col, String dir) {

		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			// HibernateUtilVtu h = new HibernateUtilVtu();
			Session alertconfigsession = HibernateUtil
					.getSession("hibernate.cfg.xml");
			String sql = "select pis_display_unit_id,display_unit_name,pdu.refresh_rate,org.org_name,flr.floor_name,bcv.bay_name,plt.platform_name,"
					+ " pdu.bus_station_id,pdu.floor_id,pdu.bay_id,pdu.platform_id,pdu.status,pdu.deleted_status,pdu.template_name "
					+ " from pis_display_unit pdu "
					+ " inner join org_chart org on pdu.bus_station_id=org.org_chart_id "
					+ " inner join floor flr on pdu.floor_id=flr.floor_id "
					+ " inner join bay bcv on pdu.bay_id=bcv.bay_id "
					+ " inner join platform plt on pdu.platform_id=plt.platform_id  "
					+ " where pdu.deleted_status=0";

			int srch = 0;

			String search_term = request.getParameter("sSearch").trim();
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {
				sql += " AND( pdu.pis_display_unit_id='" + search_term + "'";
				sql += " OR pdu.display_unit_name LIKE '%" + search_term + "%'";
				sql += " OR org.org_name like '%" + search_term + "%'";
				sql += " OR flr.floor_name like '%" + search_term + "%'";
				sql += " OR bcv.bay_name like '%" + search_term + "%'";
				sql += " OR plt.platform_name like '%" + search_term + "%'";
				sql += " OR pdu.template_name like '%" + search_term + "%'";
				sql += " OR pdu.refresh_rate='" + search_term + "')";
			}
		
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc,";
				} else {
					sql += " order by " + col + " desc,";
				}
			}
			sql+=" display_unit_name asc";
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("pis_display_unit_id", Hibernate.STRING)
					.addScalar("bus_station_id", Hibernate.STRING)
					.addScalar("floor_id", Hibernate.STRING)
					.addScalar("bay_id", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("floor_name", Hibernate.STRING)
					.addScalar("bay_name", Hibernate.STRING)
					.addScalar("platform_name", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("deleted_status", Hibernate.STRING)
                    .addScalar("refresh_rate", Hibernate.STRING)
					.addScalar("display_unit_name", Hibernate.STRING)
					.addScalar("platform_id", Hibernate.STRING)
			.addScalar("template_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2 = null;
			aliasToValueMapList2 = query.list();

			JSONArray arraytickstock = new JSONArray();

			for (int i = 0; i < aliasToValueMapList2.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList2.get(i);
				JSONArray jatickstock = new JSONArray();

				jatickstock
						.add("<input type='checkbox' class='group-checkable checkbox1' name='chkbox' data-set='#sample_2 .checkboxes' id='"
								+ rs.get("pis_display_unit_id")
								+ "' value='"
								+ rs.get("pis_display_unit_id") + "'/>");
				jatickstock.add(i + 1);
				jatickstock.add(rs.get("display_unit_name"));
				jatickstock.add(rs.get("org_name"));
				jatickstock.add(rs.get("platform_name"));
				jatickstock.add(rs.get("floor_name"));
				jatickstock.add(rs.get("refresh_rate"));
				jatickstock.add(rs.get("template_name"));
				// jatickstock.add(rs.get("deleted_status"));
				/*jatickstock.add(rs.get("status"));*/
				arraytickstock.add(jatickstock);

			}
			result.put("aaData", arraytickstock);
			int cnt = getTotaPisDisplayUnitData(request, col, dir);

			result.put("iTotalRecords", cnt);

			result.put("iTotalDisplayRecords", cnt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

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

	private int getTotaPisDisplayUnitData(HttpServletRequest request,
			String col, String dir) {

		Session alertconfigsession = null;
		int cnt = 0;
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			alertconfigsession = HibernateUtil.getSession("hibernate.cfg.xml");
			

			String sql = "select pis_display_unit_id,display_unit_name,org.org_name,pdu.refresh_rate,flr.floor_name,bcv.bay_name,plt.platform_name,"
					+ " pdu.bus_station_id,pdu.floor_id,pdu.bay_id,pdu.platform_id,pdu.status,pdu.deleted_status "
					+ " from pis_display_unit pdu "
					+ " inner join org_chart org on pdu.bus_station_id=org.org_chart_id "
					+ " inner join floor flr on pdu.floor_id=flr.floor_id "
					+ " inner join bay bcv on pdu.bay_id=bcv.bay_id "
					+ " inner join platform plt on pdu.platform_id=plt.platform_id  "
					+ " where pdu.deleted_status=0 ";

			int srch = 0;

			String search_term = request.getParameter("sSearch").trim();
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {
				sql += " AND( pdu.pis_display_unit_id='" + search_term + "'";
				sql += " OR pdu.display_unit_name LIKE'%" + search_term + "%'";
				sql += " OR org.org_name like '%" + search_term + "%'";
				sql += " OR flr.floor_name like '%" + search_term + "%'";
				sql += " OR bcv.bay_name like '%" + search_term + "%'";
				sql += " OR plt.platform_name like '%" + search_term + "%'";
				sql += " OR pdu.refresh_rate='" + search_term + "')";
			}
			sql += " order by display_unit_name asc";
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("pis_display_unit_id", Hibernate.STRING)
					.addScalar("bus_station_id", Hibernate.STRING)
					.addScalar("floor_id", Hibernate.STRING)
					.addScalar("bay_id", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("floor_name", Hibernate.STRING)
					.addScalar("bay_name", Hibernate.STRING)
					.addScalar("refresh_rate", Hibernate.STRING)
					.addScalar("platform_name", Hibernate.STRING)
					.addScalar("deleted_status", Hibernate.STRING)
					.addScalar("platform_id", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();

			cnt = aliasToValueMapList.size();


		} catch (Exception e) {
			e.printStackTrace();
			return cnt;
		} finally {
			if (alertconfigsession != null) {
				alertconfigsession.close();
			}
		}
		return cnt;

	}

	public int checkForDuplicateDisplayUnitInsert(
			DisplayUnitModel displayUnitModel) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		// String
		// effenddt=dateFormat.format(ticketbagmaster.getEffectiveenddate());
		int count = Integer.parseInt(session
				.createSQLQuery(
						"select count(*) from  pis_display_unit where display_unit_name='"
								+ displayUnitModel.getPis_display_unit_name()
								+ "' "
								+ " and bus_station_id='"
								+ displayUnitModel.getDisplay_bus_station_id()
										.getOrg_chart_id()
								+ "' "
								+ "and floor_id='"
		 						+ displayUnitModel.getDisplay_floor()
										.getFloor_id()
								+ "' and bay_id='"
								+ displayUnitModel.getDisplay_bay().getBay_id()
								+ "' "
								+ " and platform_id='"
								+ displayUnitModel.getDisplay_platform()
										.getPlatform_id()
								+ "' and deleted_status=0").uniqueResult()
				.toString());

		// TODO Auto-generated method stub
		return count;
	}

	public int insertPisDisplayUnitDetails(DisplayUnitModel displayUnitModel) {
		Session session = null;
		int j = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			session.beginTransaction();
		
			
				displayUnitModel.setCreated_date(new Date());
				displayUnitModel.setStatus("ACTIVE");
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession sess = request.getSession();
				int usrsessionid = (Integer) request.getSession().getAttribute(
						"userid");
				displayUnitModel.setCreated_by(usrsessionid);
				j = (Integer) session.save(displayUnitModel);
				session.getTransaction().commit();
		

		} catch (Exception e) {
			e.printStackTrace();
			// session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return j;
	}

	public int checkForDuplicateDisplayUnit(String displayunitname,int platformid,int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int count = Integer.parseInt(session
				.createSQLQuery(
						"select count(*) from  pis_display_unit where display_unit_name='"
								+ displayunitname
								+ "' and pis_display_unit_id not in (" + id
								+ ") " +
								" and platform_id='"+
								platformid
								+ "' and status='ACTIVE' and deleted_status=0")
				.uniqueResult().toString());

		// TODO Auto-generated method stub
		return count;
	}

	public DisplayUnitModel getDisplayUnitDetails(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		DisplayUnitModel displayunit = (DisplayUnitModel) session.get(
				DisplayUnitModel.class, new Integer(id));

		return displayunit;
	}

	public void saveEditedDisplayUnitDetails(String requestType, int id,
			DisplayUnitModel displayUnitModel) {
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			// Page_Master page_Master= (BusStops)
			// session.get(Page_Master.class, id);

		 	HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			DisplayUnitModel display_unit = (DisplayUnitModel) session.get(
					DisplayUnitModel.class, id);
			display_unit.setPis_display_unit_name(displayUnitModel
					.getPis_display_unit_name());
			OrganisationChart orgch = new OrganisationChart();
			orgch.setOrg_chart_id(displayUnitModel.getDisplay_bus_station_id()
					.getOrg_chart_id());
			display_unit.setDisplay_bus_station_id(orgch);
			// shift_master.getScheduletype().setSchedule_type_id(1);
			Floor floor = new Floor();
			floor.setFloor_id(displayUnitModel.getDisplay_floor().getFloor_id());
			display_unit.setDisplay_floor(floor);
			Bay bay = new Bay();
			bay.setBay_id(displayUnitModel.getDisplay_bay().getBay_id());
			display_unit.setDisplay_bay(bay);
			Platform platform = new Platform();
			platform.setPlatform_id(displayUnitModel.getDisplay_platform()
					.getPlatform_id());
			display_unit.setDisplay_platform(platform);
			display_unit.setRefresh_rate(displayUnitModel.getRefresh_rate());
			display_unit.setTemplate_name(displayUnitModel.getTemplate_name());
			session.update(display_unit);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}
		// TODO Auto-generated method stub

	}

	public void deleteDisplayUnit(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();

		try {
			//
			DisplayUnitModel display_unit = (DisplayUnitModel) session.get(
					DisplayUnitModel.class, id);

			// ticketbag_master.setStatus("DELETED");
			display_unit.setDeleted_status(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			display_unit.setUpdated_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			display_unit.setUpdated_date(new java.util.Date());

			session.update(display_unit);
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

	public String getFloorName(int orgtypeid) {
		Session session = null;
		String floorName = "";

		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			floorName = session
					.createSQLQuery(
							"select IFNULL(floor_name,0) from  floor where floor_id='"
									+ orgtypeid + "'").uniqueResult()
					.toString();

		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

		// TODO Auto-generated method stub
		return floorName;
	}

	public String getBayName(int bayid) {
		Session session = null;
		String bayName = "";

		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			bayName = session
					.createSQLQuery(
							"select IFNULL(bay_name,0) from  bay where bay_id='"
									+ bayid + "'").uniqueResult().toString();

		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

		// TODO Auto-generated method stub
		return bayName;
	}

	public String getPlatformName(int platformid) {
		Session session = null;
		String platformName = "";

		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			platformName = session
					.createSQLQuery(
							"select IFNULL(platform_name,0) from  platform where platform_id='"
									+ platformid + "'").uniqueResult()
					.toString();

		} catch (Exception e) {
			session.getTransaction().rollback();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}

		// TODO Auto-generated method stub
		return platformName;
	}

}
