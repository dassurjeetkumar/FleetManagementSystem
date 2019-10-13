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
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.its.common.Common;
import com.trimax.its.pis.model.DisplayMappingModel;
import com.trimax.its.pis.model.DisplayUnitModel;
import com.trimax.its.pis.model.PisScrollMessageModel;
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.ticketing.dao.DenominationTypeDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.vehicle.model.OrganisationChart;

public class PisScrollMessageDao {
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public JSONObject getPisScrollMessageData(int total,
			HttpServletRequest request, String col, String dir) {
		System.out
				.println("\n \t IN JSONObject getTotaPisDisplayMappingData.........");

		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			// HibernateUtilVtu h = new HibernateUtilVtu();
			Session alertconfigsession = HibernateUtil
					.getSession("hibernate.cfg.xml");
			String sql = "select pis_id,org.org_name,pdu.display_unit_name,bus_stand_id,font_color,background_color,display_unit_id,message,"
					+ " effective_date,end_date from pis_scroll_message psm "
					+ " inner join pis_display_unit pdu on psm.display_unit_id=pdu.pis_display_unit_id "
					+ " inner join org_chart org on psm.bus_stand_id=org.org_chart_id "
					+ " where psm.deleted_status=0 and org.org_type_id=4 ";

			System.out
					.println("\n \t JSONObject getTotaPisDisplayMappingData 1111..............");
			int srch = 0;

			String search_term = request.getParameter("sSearch");
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}
			if (search_term != null && !search_term.equals("")) {

				sql += " and pis_id='" + srch + "'";
				sql += " OR org.org_name like '%" + search_term + "%'";
				sql += " OR pdu.display_unit_name like '%" + search_term + "%'";
				sql += " OR message like '%" + search_term + "%'";
				sql += " OR font_color like '%" + search_term + "%'";
				sql += " OR background_color like '%" + search_term + "%'";
				sql += " OR effective_date like '%" + search_term + "%'";
				sql += " OR end_date like '%" + search_term + "%'";
			}

			if (!col.equals("")) {
				if (dir.equals("asc")) {
					sql += " order by " + col + " asc";
				} else {
					sql += " order by " + col + " desc";
				}
			}
			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			System.out.println("sql is.." + sql);
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("pis_id", Hibernate.STRING)
					.addScalar("bus_stand_id", Hibernate.STRING)
					.addScalar("display_unit_id", Hibernate.STRING)
					.addScalar("message", Hibernate.STRING)
					.addScalar("effective_date", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("font_color", Hibernate.STRING)
					.addScalar("background_color", Hibernate.STRING)
					.addScalar("display_unit_name", Hibernate.STRING)
					.addScalar("end_date", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList2 = null;
			aliasToValueMapList2 = query.list();

			JSONArray arraytickstock = new JSONArray();
			Common cm = new Common();
			for (int i = 0; i < aliasToValueMapList2.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList2.get(i);
				JSONArray jatickstock = new JSONArray();

				jatickstock
						.add("<input type='checkbox' class='group-checkable checkbox1' name='chkbox' data-set='#sample_2 .checkboxes' id='"
								+ rs.get("pis_id")
								+ "' value='"
								+ rs.get("pis_id") + "'/>");
				jatickstock.add(i + 1);
				jatickstock.add(rs.get("org_name"));
				jatickstock.add(rs.get("display_unit_name"));
				jatickstock.add(rs.get("message"));
				jatickstock.add(rs.get("font_color"));
				jatickstock.add(rs.get("background_color"));
				if (rs.get("effective_date") != null) {
					jatickstock.add(cm.getDateToPicker(rs.get("effective_date")
							.toString()));
				} else {
					jatickstock.add("");
				}
				if (rs.get("end_date") != null) {
					jatickstock.add(cm.getDateToPicker(rs.get("end_date")
							.toString()));

				} else {
					jatickstock.add("");
				}

				arraytickstock.add(jatickstock);

			}
			result.put("aaData", arraytickstock);
			int cnt = getTotaPisScrollMessageData(request, col, dir);

			result.put("iTotalRecords", cnt);

			result.put("iTotalDisplayRecords", cnt);

			System.out.println("****ticket array data...---->");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	/**
	 * @getTotalVehicleAlertConfigDataRecords
	 * @return
	 */
	public int getTotaPisScrollMessageData(HttpServletRequest request,
			String col, String dir) {
		Session alertconfigsession = null;
		int cnt = 0;
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			alertconfigsession = HibernateUtil.getSession("hibernate.cfg.xml");

			String sql = "select pis_id,org.org_name,pdu.display_unit_name,bus_stand_id,display_unit_id,message,effective_date,end_date from pis_scroll_message psm "
					+ " inner join pis_display_unit pdu on psm.display_unit_id=pdu.pis_display_unit_id "
					+ " inner join org_chart org on psm.bus_stand_id=org.org_chart_id "
					+ " where psm.deleted_status=0 and org.org_type_id=4";
			System.out
					.println("\n \t JSONObject getTotalVehicleAlertConfigData 1111..............");
			int srch = 0;
			String search_term = request.getParameter("sSearch").trim();
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}
			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and pis_id='" + srch + "'";
				sql += " OR org.org_name like '%" + search_term + "%'";
				sql += " OR pdu.display_unit_name like '%" + search_term + "%'";
				sql += " OR message like '%" + search_term + "%'";
				sql += " OR effective_date like '%" + search_term + "%'";
				sql += " OR end_date like '%" + search_term + "%'";
			}
			System.out.println("sql in total is----" + sql);
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("pis_id", Hibernate.STRING)
					.addScalar("bus_stand_id", Hibernate.STRING)
					.addScalar("display_unit_id", Hibernate.STRING)
					.addScalar("message", Hibernate.STRING)
					.addScalar("effective_date", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING)
					.addScalar("display_unit_name", Hibernate.STRING)
					.addScalar("end_date", Hibernate.STRING);
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

	@SuppressWarnings("finally")
	public String getDateFromDateTime_(String pickerDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String formattedDate = "";
		try {
			date = simpleDateFormat1.parse(pickerDate);
			formattedDate = simpleDateFormat.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			return formattedDate;
		}
	}

	public int insertPisScrollMessageModelDetails(
			PisScrollMessageModel pisScrollMessageModel) {
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			session.beginTransaction();
			pisScrollMessageModel.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			pisScrollMessageModel.setMessage(pisScrollMessageModel.getMessage()
					.trim());
			if (!pisScrollMessageModel.getEffective_date().trim().isEmpty()) {
				pisScrollMessageModel
						.setEffective_date(getDateFromDateTime_(pisScrollMessageModel
								.getEffective_date()));
			} else {
				pisScrollMessageModel.setEffective_date(null);
			}
			if (!pisScrollMessageModel.getEnd_date().trim().isEmpty()) {
				pisScrollMessageModel
						.setEnd_date(getDateFromDateTime_(pisScrollMessageModel
								.getEnd_date()));
			} else {
				pisScrollMessageModel.setEnd_date(null);
			}
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			pisScrollMessageModel.setCreated_by(usrsessionid);
			i = (Integer) session.save(pisScrollMessageModel);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return i;

	}

	public PisScrollMessageModel getScrollMessageDetails(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		PisScrollMessageModel pisScrollMessageModel = (PisScrollMessageModel) session
				.get(PisScrollMessageModel.class, new Integer(id));
		Common cm = new Common();
		pisScrollMessageModel.setEffective_date(cm
				.getDateToPicker(pisScrollMessageModel.getEffective_date()));
		pisScrollMessageModel.setEnd_date(cm
				.getDateToPicker(pisScrollMessageModel.getEnd_date()));
		return pisScrollMessageModel;

	}

	public void saveEditedDisplayMappingDetails(String requestType, int id,
			PisScrollMessageModel pisScrollMessageModel) {
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			// Page_Master page_Master= (BusStops)
			// session.get(Page_Master.class, id);

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			PisScrollMessageModel pisscroll_message = (PisScrollMessageModel) session
					.get(PisScrollMessageModel.class, id);

			OrganisationChart orgch = new OrganisationChart();
			orgch.setOrg_chart_id(pisScrollMessageModel.getBusstand()
					.getOrg_chart_id());
			pisscroll_message.setBusstand(orgch);
			DisplayUnitModel dispunit = new DisplayUnitModel();
			dispunit.setPis_display_unit_id(pisScrollMessageModel
					.getScroll_display_unit().getPis_display_unit_id());
			pisscroll_message.setScroll_display_unit(dispunit);
			if (!pisScrollMessageModel.getEffective_date().trim().isEmpty()) {
				pisscroll_message
						.setEffective_date(getDateFromDateTime_(pisScrollMessageModel
								.getEffective_date()));
			} else {
				pisScrollMessageModel.setEffective_date(null);
			}
			if (!pisScrollMessageModel.getEnd_date().trim().isEmpty()) {
				pisscroll_message
						.setEnd_date(getDateFromDateTime_(pisScrollMessageModel
								.getEnd_date()));
			} else {
				pisScrollMessageModel.setEnd_date(null);
			}
			pisscroll_message.setMessage(pisScrollMessageModel.getMessage()
					.trim());
			pisscroll_message.setFont_color(pisScrollMessageModel
					.getFont_color());
			pisscroll_message.setBackground_color(pisScrollMessageModel
					.getBackground_color());
			session.update(pisscroll_message);
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

	public int checkForDuplicateScrollMessageInsert(
			PisScrollMessageModel pisScrollMessageModel) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// String
		// effdate=getDateFromDateTime_(pisScrollMessageModel.getEffective_date());
		// String
		// enddate=getDateFromDateTime_(pisScrollMessageModel.getEnd_date());
		// String
		// effenddt=dateFormat.format(ticketbagmaster.getEffectiveenddate());

		int count = Integer.parseInt(session
				.createSQLQuery(
						"select count(*) from pis_scroll_message where message='"
								+ pisScrollMessageModel.getMessage().trim()
								+ "' "
								+ "and bus_stand_id='"
								+ pisScrollMessageModel.getBusstand()
										.getOrg_chart_id()
								+ "' and display_unit_id='"
								+ pisScrollMessageModel
										.getScroll_display_unit()
										.getPis_display_unit_id() + "' "
								+ " and deleted_status=0").uniqueResult()
				.toString());

		// TODO Auto-generated method stub
		return count;

	}

	public int checkForDuplicateScrollMessage(String message, int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int count = Integer.parseInt(session
				.createSQLQuery(
						"select count(*) from pis_scroll_message where message='"
								+ message.trim() + "' and pis_id not in (" + id
								+ ") and deleted_status=0")
				.uniqueResult().toString());

		// TODO Auto-generated method stub
		return count;

	}

	public void deleteScrollMessage(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			PisScrollMessageModel pisScroll_essage = (PisScrollMessageModel) session
					.get(PisScrollMessageModel.class, id);

			// ticketbag_master.setStatus("DELETED");
			pisScroll_essage.setDeleted_status(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			pisScroll_essage.setUpdated_by(Integer.parseInt(request
					.getSession().getAttribute("userid").toString()));
			// pisScroll_essage.setUpdated_date(new java.util.Date());

			session.update(pisScroll_essage);
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

	public List<String> getDisplayUnitId() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select pis_display_unit_id from pis_display_unit where deleted_status=0 and status='ACTIVE' and display_unit_name!='NULL' "
					+ " order by display_unit_name";

			Query query = session.createSQLQuery(qry);
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

	public List<String> getDisplayUnitName() {
		Session session = null;
		List list = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");

			list = new ArrayList();

			String qry = "select display_unit_name from pis_display_unit where deleted_status=0 and status='ACTIVE' and display_unit_name!='NULL' order by display_unit_name";

			Query query = session.createSQLQuery(qry);
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

	public List<String> getAdvertisementTypeId() {
		Session session = null;
		List list = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");

			list = new ArrayList();

			String qry = "select pis_display_unit_id from pis_display_unit where deleted_status=0 and status='ACTIVE' and display_unit_name!='NULL' "
					+ " order by display_unit_name";

			Query query = session.createSQLQuery(qry);
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

	public List<String> getAdvertisementTypeName() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select display_unit_name from pis_display_unit where deleted_status=0 and status='ACTIVE' and display_unit_name!='NULL' order by display_unit_name";
			Query query = session.createSQLQuery(qry);
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

}
