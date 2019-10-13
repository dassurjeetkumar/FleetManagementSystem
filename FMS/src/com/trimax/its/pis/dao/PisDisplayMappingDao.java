package com.trimax.its.pis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.its.common.Common;
import com.trimax.its.pis.model.DisplayMappingModel;
import com.trimax.its.pis.model.DisplayUnitModel;
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.ticketing.dao.DenominationTypeDao;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vehicle.model.OrganisationChart;

public class PisDisplayMappingDao {
	/**
	 * getPisDisplayMappingData
	 * 
	 * @param total
	 * @param request
	 * @param cols
	 * @param dir
	 * @return
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
	 	}
		// only got here if we didn't return false
		return true;
	}

	public JSONObject getPisDisplayMappingData(int total,
			HttpServletRequest request, String col, String dir) {
		System.out
				.println("\n \t IN JSONObject getTotaPisDisplayMappingData.........");

		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			// HibernateUtilVtu h = new HibernateUtilVtu();
			Session alertconfigsession = HibernateUtil
					.getSession("hibernate.cfg.xml");
			String sql = "select pis_display_platform_id,org.org_name,pdp.bus_station_id,pdp.floor_id" +
					     " ,pdu.display_unit_name,plt.platform_name,pdp.display_unit_id,flr.floor_name," +
					     " bcv.bay_name,pdp.platform_id from pis_display_platfrom pdp " +
					     " inner join org_chart org on pdp.bus_station_id=org.org_chart_id " +
					     " inner join floor flr on pdp.floor_id=flr.floor_id " +
					     " inner join bay bcv on pdp.bay_id=bcv.bay_id " +
					     " inner join platform plt on pdp.platform_id=plt.platform_id  " +
					     "inner join pis_display_unit pdu on pdp.display_unit_id=pdu.pis_display_unit_id " +
					     " where pdp.deleted_status=0 and org.org_type_id=4";

			int srch = 0;

			String search_term = request.getParameter("sSearch");
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {
				search_term=search_term.trim();
				sql += " and  pis_display_platform_id='" + search_term + "'";
				sql += " OR org.org_name like '%" + search_term + "%'";
				sql += " OR pdu.display_unit_name like '%" + search_term + "%'";
				sql += " OR flr.floor_name like '%" + search_term + "%'";
				sql +="  OR plt.platform_name like '%" + search_term + "%'";
				
			}
			
			
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", "+ request.getParameter("iDisplayLength");

			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("pis_display_platform_id", Hibernate.STRING)
					.addScalar("bus_station_id", Hibernate.STRING)
					.addScalar("floor_id", Hibernate.STRING)
					.addScalar("display_unit_id", Hibernate.STRING)
					.addScalar("display_unit_name", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("floor_name", Hibernate.STRING)
					.addScalar("bay_name", Hibernate.STRING)
					.addScalar("platform_name", Hibernate.STRING)
					.addScalar("platform_id", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2 = null;
			aliasToValueMapList2 = query.list();

			JSONArray arraytickstock = new JSONArray();

			for (int i = 0; i < aliasToValueMapList2.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList2.get(i);
				JSONArray jatickstock = new JSONArray();

				jatickstock.add("<input type='checkbox' class='group-checkable checkbox1' name='chkbox' data-set='#sample_2 .checkboxes' id='"
								+ rs.get("pis_display_platform_id")
								+ "' value='"+ rs.get("pis_display_platform_id") + "'/>");
				jatickstock.add(i+1);
				jatickstock.add(rs.get("display_unit_name"));
				jatickstock.add(rs.get("org_name"));			
				jatickstock.add(rs.get("platform_name"));
				jatickstock.add(rs.get("floor_name"));
		
				arraytickstock.add(jatickstock);
			}
			
			result.put("aaData", arraytickstock);
			int cnt = getTotaPisDisplayMappingData(request, col, dir);

			result.put("iTotalRecords", cnt);
			result.put("iTotalDisplayRecords", cnt);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	/**
	 * @getTotalVehicleAlertConfigDataRecords
	 * @return
	 */
	public int getTotaPisDisplayMappingData(HttpServletRequest request,
			String col, String dir) {
		Session alertconfigsession = null;
		int cnt = 0;
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			alertconfigsession = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql = "select pis_display_platform_id,org.org_name,pdp.bus_station_id,pdp.floor_id" +
				     " ,pdu.display_unit_name,plt.platform_name,pdp.display_unit_id,flr.floor_name," +
				     " bcv.bay_name,pdp.platform_id from pis_display_platfrom pdp " +
				     " inner join org_chart org on pdp.bus_station_id=org.org_chart_id " +
				     " inner join floor flr on pdp.floor_id=flr.floor_id " +
				     " inner join bay bcv on pdp.bay_id=bcv.bay_id " +
				     " inner join platform plt on pdp.platform_id=plt.platform_id  " +
				     "inner join pis_display_unit pdu on pdp.display_unit_id=pdu.pis_display_unit_id " +
				     " where pdp.deleted_status=0 and org.org_type_id=4";

			System.out
					.println("\n \t JSONObject getTotalVehicleAlertConfigData 1111..............");
			int srch = 0;

			String search_term = request.getParameter("sSearch");
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {

				

				sql += " and  pis_display_platform_id='" + search_term + "'";
				sql += " OR org.org_name like '%" + search_term + "%'";
				sql += " OR pdu.display_unit_name like '%" + search_term + "%'";
				sql += " OR flr.floor_name like '%" + search_term + "%'";
				sql +="  OR plt.platform_name like '%" + search_term + "%'";
				
			}

		
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("pis_display_platform_id", Hibernate.STRING)
					.addScalar("bus_station_id", Hibernate.STRING)
					.addScalar("floor_id", Hibernate.STRING)
					.addScalar("display_unit_id", Hibernate.STRING)
					.addScalar("display_unit_name", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("floor_name", Hibernate.STRING)
					.addScalar("bay_name", Hibernate.STRING)
					.addScalar("platform_name", Hibernate.STRING)
					.addScalar("platform_id", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
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

	public List<String> getBusStationId() {
		Session session = null;
		List list = null;
		String status = "ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select org_chart_id from  org_chart where deleted_status=0 and org_name!='NULL' and org_type_id=4 order by org_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_chart_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getBusStationName() {
		Session session = null;
		List list = null;
		String status = "ACTIVE";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select org_name from  org_chart where deleted_status=0 and org_name!='NULL' and org_type_id=4 order by org_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("org_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getFloorId(int busstationid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select floor_id from floor where deleted_status=0 and floor_name!='NULL' and parent_id="
					+ busstationid + " order by floor_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("floor_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getFloorName(int busstationid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select floor_name from floor where deleted_status=0 and floor_name!='NULL' and parent_id="
					+ busstationid + " order by floor_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("floor_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getBayId(int floorid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select bay_id from bay where deleted_status=0 and status='ACTIVE' and bay_name!='NULL' and floor_id="
					+ floorid + " order by bay_name";


			Query query = session .createSQLQuery(qry);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("bay_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getBayName(int floorid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select bay_name from bay where deleted_status=0 and status='ACTIVE' and bay_name!='NULL' and floor_id="
					+ floorid + " order by bay_name";

			Query query =session.createSQLQuery(qry);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("bay_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DenominationTypeDao denomtypedao = new DenominationTypeDao();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getPlatformId(int bayid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select platform_id from platform where deleted_status=0 and status='ACTIVE' and platform_name!='NULL' and bay_id="
					+ bayid + " order by platform_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("platform_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getPlatformName(int bayid) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select platform_name from platform where deleted_status=0 and status='ACTIVE' and platform_name!='NULL' and bay_id="
					+ bayid + " order by platform_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("platform_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DenominationTypeDao denomtypedao = new DenominationTypeDao();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public int insertPisDisplayMappingDetails(
			DisplayMappingModel displaymappingmodel) {
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			session.beginTransaction();
			displaymappingmodel.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			displaymappingmodel.setCreated_by(usrsessionid);
			i = (Integer) session.save(displaymappingmodel);
			session.getTransaction().commit();
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

	public DisplayMappingModel getDisplayMappingDetails(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		DisplayMappingModel displaymapping = (DisplayMappingModel) session.get(
				DisplayMappingModel.class, new Integer(id));

		return displaymapping;
	}

	public void saveEditedDisplayMappingDetails(String requestType, int id,
			DisplayMappingModel displaymappingmodel) {
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			// Page_Master page_Master= (BusStops)
			// session.get(Page_Master.class, id);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			DisplayMappingModel display_mapp = (DisplayMappingModel) session
					.get(DisplayMappingModel.class, id);

			OrganisationChart orgch = new OrganisationChart();
			orgch.setOrg_chart_id(displaymappingmodel.getBus_station_id()
					.getOrg_chart_id());
			display_mapp.setBus_station_id(orgch);
			// shift_master.getScheduletype().setSchedule_type_id(1);
			Floor floor = new Floor();
			floor.setFloor_id(displaymappingmodel.getFloor().getFloor_id());
			display_mapp.setFloor(floor);
			Bay bay = new Bay();
			bay.setBay_id(displaymappingmodel.getBay().getBay_id());
			Platform platform = new Platform();
			platform.setPlatform_id(displaymappingmodel.getPlatform()
					.getPlatform_id());
			display_mapp.setPlatform(platform);
			DisplayUnitModel dispunit=new DisplayUnitModel();
			dispunit.setPis_display_unit_id(display_mapp.getDisplay_unit().getPis_display_unit_id());
			
			display_mapp.setDisplay_unit(dispunit);
			session.update(display_mapp);
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

	public void deleteDisplayMapping(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			DisplayMappingModel display_mapping = (DisplayMappingModel) session
					.get(DisplayMappingModel.class, id);

			// ticketbag_master.setStatus("DELETED");
			display_mapping.setDeleted_status(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			display_mapping.setUpdated_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			display_mapping.setUpdated_date(new java.util.Date());

			session.update(display_mapping);
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

	public int checkForDuplicateDisplayMappingInsert(
			DisplayMappingModel displaymappingmodel) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		// String
		// effenddt=dateFormat.format(ticketbagmaster.getEffectiveenddate());
		int count = Integer.parseInt(session
				.createSQLQuery(
						"select count(*) from pis_display_platfrom where bus_station_id='"
								+ displaymappingmodel.getBus_station_id()
										.getOrg_chart_id()
								+ "' "
								+ "and floor_id='"
								+ displaymappingmodel.getFloor().getFloor_id()
								+ "' and bay_id='"
								+ displaymappingmodel.getBay().getBay_id()
								+ "' "
								+ "and platform_id='"
								+ displaymappingmodel.getPlatform()
										.getPlatform_id()
								+ "' and display_unit_id='"
								+ displaymappingmodel.getDisplay_unit().getPis_display_unit_id()
								+ "' " + " and deleted_status=0")
				.uniqueResult().toString());
		
		// TODO Auto-generated method stub
		return count;
	}

	public int checkForDuplicateDisplayMapping(int busstationid,int floor,int bay,int pForm,int display, int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int count = Integer.parseInt(session
				.createSQLQuery(
						"select count(*) from pis_display_platfrom where bus_station_id='"+busstationid
								+ "' and floor_id='"+ floor
								+ "' and bay_id='"+ bay
								+ "' and platform_id='"+ pForm
								+ "' and display_unit_id='"+ display
								+ "' and pis_display_platform_id not in (" + id
								+ ") and deleted_status=0")
				.uniqueResult().toString());
		 
		return count;
	}

	public List<String> getDisplayId(int platformid) {
		Session session = null;
		List list = null;
		try {

			session =HibernateUtil.getSession("hibernate.cfg.xml");

			list = new ArrayList();

			String qry = "select pis_display_unit_id from pis_display_unit where deleted_status=0 and status='ACTIVE' and display_unit_name!='NULL' "
					+ " and platform_id="
					+ platformid
					+ " order by display_unit_name";


			Query query =session.createSQLQuery(qry);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("pis_display_unit_id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}

	public List<String> getDisplayName(int platformid) {
		Session session = null;
		List list = null;
		try {

			session =HibernateUtil.getSession("hibernate.cfg.xml");

			list = new ArrayList();

			String qry = "select display_unit_name from pis_display_unit where deleted_status=0 and status='ACTIVE' and display_unit_name!='NULL' "
					+ " and platform_id="
					+ platformid
					+ " order by display_unit_name";

			Query query =session.createSQLQuery(qry);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("display_unit_name").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			DenominationTypeDao denomtypedao = new DenominationTypeDao();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();

			}

		}
		return list;
	}
	public String getDisplayUnitName(int displayunitid) {
		Session session = null;
		String platformName = "";
		
		
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			platformName = session
							.createSQLQuery(
									"select IFNULL(display_unit_name,0) from  pis_display_unit where pis_display_unit_id='"+displayunitid+"'")
							.uniqueResult().toString();
		
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
