package com.trimax.its.ad.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.jaxb.xml.trimax.com.VtsResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.ad.model.Advertisement;
import com.trimax.its.ad.model.AdvertisementType;
import com.trimax.its.common.Common;
import com.trimax.its.pis.model.DisplayUnitModel;
import com.trimax.its.pis.model.PisScrollMessageModel;
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.ticketing.dao.DenominationTypeDao;
import com.trimax.its.transport.model.Customer;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class AdMannagementDao {
	public String getFormattedDateForAirtime(String args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
				"dd MMMMM yyyy-hh:mm");
		Date date = new Date();
		String formattedDate = "";
		try {
			date = simpleDateFormat1.parse("12 December 1899-13:25");

			formattedDate = simpleDateFormat.format(date);
			System.out.println("formattedDate" + formattedDate);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			return formattedDate;
		}
	}

	public JSONObject getAdvertisementData(int total,
			HttpServletRequest request, String col, String dir) {
		System.out
				.println("\n \t IN JSONObject getTotaPisDisplayMappingData.........");

		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			// HibernateUtilVtu h = new HibernateUtilVtu();
			Session alertconfigsession = HibernateUtil
					.getSession("hibernate.cfg.xml");
			String sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
					+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
					+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
					+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE')  and ad.deleted_status=0";

			System.out
					.println("\n \t JSONObject getTotaPisDisplayMappingData 1111..............");
			int srch = 0;

			String search_term = request.getParameter("sSearch").trim();
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and ad.advertisement_id='" + srch + "'";
				sql += " OR cu.c_cname like '%" + search_term + "%'";
				sql += " OR adt.advertisement_type_name like '%" + search_term
						+ "%'";
				sql += " OR ad.advertisement_name like '%" + search_term + "%'";
				// sql += " OR ad.start_date like '%" + search_term + "%'";
				// sql += " OR ad.end_date like '%" + search_term + "%'";
				sql += " OR ad.status like '%" + search_term + "%'";
				/*
				 * sql += " OR ad.amount='" + search_term + "'"; sql +=
				 * " OR ad.duration='" + search_term + "'"; sql +=
				 * " OR ad.display_peak_hour='" + search_term + "'"; sql +=
				 * " OR ad.frequency='" + search_term + "'"; sql +=
				 * " OR ad.notes='" + search_term + "'"; sql +=
				 * " OR ad.airtime like '%" + search_term + "%'";
				 */
			}

			System.out.println("SORTING DIRECTION IS..." + dir + "column is..."
					+ col);
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
					.addScalar("advertisement_id", Hibernate.STRING)
					.addScalar("c_cname", Hibernate.STRING)
					.addScalar("advertisement_type_name", Hibernate.STRING)
					.addScalar("advertisement_name", Hibernate.STRING)
					.addScalar("start_date", Hibernate.STRING)
					.addScalar("end_date", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("amount", Hibernate.STRING)
					.addScalar("duration", Hibernate.STRING)
					.addScalar("display_peak_hour", Hibernate.STRING)
					.addScalar("notes", Hibernate.STRING)

					.addScalar("frequency", Hibernate.STRING)
					.addScalar("airtime", Hibernate.STRING);
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
								+ rs.get("advertisement_id")
								+ "' value='"
								+ rs.get("advertisement_id") + "'/>");
				jatickstock.add(i + 1);
				jatickstock.add(rs.get("advertisement_name"));
				jatickstock.add(rs.get("c_cname"));
                if(rs.get("start_date")!=null)
                {
				jatickstock.add(cm.getDateToPicker(rs.get("start_date")
						.toString()));
                }
                else
                {
                	jatickstock.add("");
                }
				if(rs.get("end_date")!=null)
                {
				jatickstock.add(cm.getDateToPicker(rs.get("end_date")
						.toString()));
                }
                else
                {
                	jatickstock.add("");
                }
				jatickstock.add(rs.get("advertisement_type_name"));

				jatickstock.add(rs.get("amount"));
				jatickstock.add(rs.get("duration"));
				jatickstock.add(rs.get("frequency"));
				jatickstock.add(rs.get("display_peak_hour"));

				jatickstock.add(rs.get("airtime"));
				jatickstock.add(rs.get("notes"));
				jatickstock.add(rs.get("status"));
				arraytickstock.add(jatickstock);

			}
			result.put("aaData", arraytickstock);
			int cnt = getTotaAdvertisementData(request, col, dir);

			result.put("iTotalRecords", cnt);

			result.put("iTotalDisplayRecords", cnt);

			System.out
					.println("****ticket array data...---->" + arraytickstock);

			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
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

	private int getTotaAdvertisementData(HttpServletRequest request,
			String col, String dir) {

		Session alertconfigsession = null;
		int cnt = 0;
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			alertconfigsession = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
					+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
					+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
					+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE') and ad.deleted_status=0";

			System.out
					.println("\n \t JSONObject getTotaPisDisplayMappingData 1111..............");
			int srch = 0;

			String search_term = request.getParameter("sSearch").trim();
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and ad.advertisement_id='" + srch + "'";
				sql += " OR cu.c_cname like '%" + search_term + "%'";
				sql += " OR adt.advertisement_type_name like '%" + search_term
						+ "%'";
				sql += " OR ad.advertisement_name like '%" + search_term + "%'";
				// sql += " OR ad.start_date like '%" + search_term + "%'";
				// sql += " OR ad.end_date like '%" + search_term + "%'";
				sql += " OR ad.status like '%" + search_term + "%'";
				/*
				 * sql += " OR ad.amount='" + search_term + "'"; sql +=
				 * " OR ad.duration='" + search_term + "'"; sql +=
				 * " OR ad.display_peak_hour='" + search_term + "'"; sql +=
				 * " OR ad.frequency='" + search_term + "'"; sql +=
				 * " OR ad.notes='" + search_term + "'"; sql +=
				 * " OR ad.airtime like '%" + search_term + "%'";
				 */
			}
			System.out.println("sql----" + sql);
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("advertisement_id", Hibernate.STRING)
					.addScalar("c_cname", Hibernate.STRING)
					.addScalar("advertisement_type_name", Hibernate.STRING)
					.addScalar("advertisement_name", Hibernate.STRING)
					.addScalar("start_date", Hibernate.STRING)
					.addScalar("end_date", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("amount", Hibernate.STRING)
					.addScalar("duration", Hibernate.STRING)
					.addScalar("display_peak_hour", Hibernate.STRING)
					.addScalar("notes", Hibernate.STRING)

					.addScalar("frequency", Hibernate.STRING)
					.addScalar("airtime", Hibernate.STRING);
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

	public List<String> getCustomerId() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select c_cid from customer where deleted_status=0 and c_status='ACTIVE' and c_cname!='NULL' "
					+ " order by c_cname";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("c_cid").toString());
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

	public List<String> getCustomerName() {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select c_cname from customer where deleted_status=0 and c_status='ACTIVE' and c_cname!='NULL' "
					+ " order by c_cname";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("c_cname").toString());
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

			String qry = "select advertisement_type_id from advertisement_type where deleted_status=0 and status='ACTIVE' and advertisement_type_name!='NULL' "
					+ " order by advertisement_type_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("advertisement_type_id").toString());
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

			String qry = "select advertisement_type_name from advertisement_type where deleted_status=0 and status='ACTIVE' and advertisement_type_name!='NULL' "
					+ " order by advertisement_type_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("advertisement_type_name").toString());
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

	public int insertAdvertisement(Advertisement advetisement) {
		Session session = null;
		int i = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			session.beginTransaction();
			advetisement.setCreated_date(new Date());

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			if (!advetisement.getStart_date().trim().isEmpty()) {
				advetisement.setStart_date(getDateFromDateTime_(advetisement
						.getStart_date()));
			} else {
				advetisement.setStart_date(null);
			}
			if (!advetisement.getEnd_date().trim().isEmpty()) {
				advetisement.setEnd_date(getDateFromDateTime_(advetisement
						.getEnd_date()));
			} else {
				advetisement.setEnd_date(null);
			}
			if (!advetisement.getAirtime().trim().isEmpty()) {
				advetisement.setAirtime(advetisement.getAirtime());
			} else {
				advetisement.setAirtime(null);
			}
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			advetisement.setCreated_by(usrsessionid);
			advetisement.setStatus("ACTIVE");
			i = (Integer) session.save(advetisement);
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

	public int checkForDuplicateAdvertisement(String message,int custId,int advType, int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int count=0;
		try{
		Query query=session.createSQLQuery("select count(*) from advertisement where advertisement_name='"
				+ message.trim() + "' "
				+ " and customer_id='"+ custId
				+ "' and advertisement_type='"+advType + "' "
				+ " and advertisement_id not in (" + id	+ ") and deleted_status=0");
//		query.setParameter(0, message.trim());
//		query.setParameter(1, custId);
//		query.setParameter(2, advType);
//		query.setParameter(3, advType);
		 count = Integer.parseInt(query.uniqueResult().toString());
		 System.out.println("Count======"+count);
		}catch(Exception e){
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return count;

	}

	public Advertisement getAdvertisementDetails(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Advertisement advetisement=null;
       try{
		 advetisement = (Advertisement) session.get(
				Advertisement.class, new Integer(id));
		Common cm = new Common();
		advetisement.setStart_date(cm.getDateToPicker(advetisement.getStart_date()));
System.out.println("===="+advetisement.getStart_date());
		if (!(advetisement.getEnd_date()=="") || !advetisement.getEnd_date().trim().equalsIgnoreCase(null) ) {
			advetisement.setEnd_date(cm.getDateToPicker(advetisement.getEnd_date()));
			} else {
//				advetisement.setEnd_date(null);
				advetisement.setEnd_date("");
			}
		
//		advetisement.setEnd_date(cm.getDateToPicker(advetisement.getEnd_date()));
       }catch(Exception e){
    	   e.printStackTrace();
       }
		return advetisement;
	}

	public void saveEditedAdvertisementDetails(String requestType, int id,
			Advertisement advetisement) {
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession sess = request.getSession();
			Advertisement advtise = (Advertisement) session.get(
					Advertisement.class, id);
			advtise.setAdvertisement_name(advetisement.getAdvertisement_name());
			Customer customer = new Customer();
			customer.setId(advetisement.getCust().getId());
			advtise.setCust(customer);
			if (!advetisement.getStart_date().trim().isEmpty()) {
			advtise.setStart_date(getDateFromDateTime_(advetisement
					.getStart_date()));
			} else {
				advetisement.setStart_date(null);
			}
			if (!advetisement.getEnd_date().trim().isEmpty()) {
			advtise.setEnd_date(getDateFromDateTime_(advetisement.getEnd_date()));
			} else {
				advetisement.setEnd_date(null);
			}
			advtise.setStatus(advetisement.getStatus());
			AdvertisementType adtype = new AdvertisementType();
			adtype.setAdvertisement_type_id(advetisement.getAdvetisetype()
					.getAdvertisement_type_id());
			advtise.setAdvetisetype(adtype);
			advtise.setAmount(advetisement.getAmount());
			advtise.setDuration(advetisement.getDuration()+"");
			advtise.setDisplay_peak_hour(advetisement.getDisplay_peak_hour());
			advtise.setFrequency(advetisement.getFrequency()+"");
			advtise.setAirtime(advetisement.getAirtime());

			advtise.setNotes(advetisement.getNotes().trim());
			int usrsessionid = (Integer) request.getSession().getAttribute(
					"userid");
			advtise.setUpdated_by(usrsessionid);
			advtise.setUpdated_date(new Date());

			session.update(advtise);
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

	public void deleteAdManagement(int id) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		// System.out.println("=====Route id" + role.getRole_id());
		try {
			//
			Advertisement advtise = (Advertisement) session.get(
					Advertisement.class, id);

			advtise.setStatus("DELETED");
			advtise.setDeleted_status(1);
			HttpServletRequest request = ServletActionContext.getRequest();
			advtise.setUpdated_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			// pisScroll_essage.setUpdated_date(new java.util.Date());

			session.update(advtise);
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

	public int checkForDuplicateScrollMessageInsert(Advertisement advetisement) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//String effdate = getDateFromDateTime_(advetisement.getStart_date());
		//String enddate = getDateFromDateTime_(advetisement.getEnd_date());
		// String
		// effenddt=dateFormat.format(ticketbagmaster.getEffectiveenddate());
		String qry="select count(*) from  advertisement where advertisement_name=? and customer_id=? and advertisement_type=? and deleted_status=0";
				
		Query query=session.createSQLQuery(qry);
		query.setParameter(0, advetisement.getAdvertisement_name().trim());
		query.setParameter(1, advetisement.getCust().getId());
		query.setParameter(2, advetisement.getAdvertisement_name().trim());
		int count = Integer.parseInt(query.uniqueResult().toString());

		// TODO Auto-generated method stub
		return count;
	}

	public Map<Integer, String> getCustomerList() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select c_cname,c_cid from customer where deleted_status=0 and c_status='ACTIVE' and c_cname!='NULL' "
					+ " order by c_cname";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			List<OrganisationChart> list = query.list();
			resultMap.put(-1, "All");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

				resultMap.put(Integer.parseInt(rs.get("c_cid").toString()), rs
						.get("c_cname").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return resultMap;
	}

	public JSONObject getAdvertisementDataForCustomer(int total,
			HttpServletRequest request, String col, String dir,
			int customerId, int advertiseId) {
		System.out 
				.println("\n \t IN JSONObject getTotaPisDisplayMappingData.........");

		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;
			// HibernateUtilVtu h = new HibernateUtilVtu();
			Session alertconfigsession = HibernateUtil
					.getSession("hibernate.cfg.xml");
			String sql="";
			if((customerId==-1)&&(advertiseId==-1))
			{
				sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
						+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
						+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
						+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE')  " +
						" and ad.deleted_status=0";
			}
			
			else
			{
				if(advertiseId==-1)
				{
				sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
						+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
						+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
						+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE')  " +
						" and ad.deleted_status=0 and ad.customer_id='"+customerId+"'";
				}
				
				else
				{
					sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
							+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
							+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
							+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE')  " +
							" and ad.deleted_status=0 and ad.advertisement_id='"+advertiseId+"' and ad.customer_id='"+customerId+"'"; 
				}
			}
			
			System.out
					.println("\n \t JSONObject getTotaPisDisplayMappingData 1111..............");
			int srch = 0;

			String search_term = request.getParameter("sSearch").trim();
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and ad.advertisement_id='" + srch + "'";
				
				sql += " OR ad.advertisement_name like '%" + search_term + "%'";
				sql += " OR ad.start_date like '%" + search_term + "%'";
				sql += " OR ad.end_date like '%" + search_term + "%'";
				
				/*
				 * sql += " OR ad.amount='" + search_term + "'"; sql +=
				 * " OR ad.duration='" + search_term + "'"; sql +=
				 * " OR ad.display_peak_hour='" + search_term + "'"; sql +=
				 * " OR ad.frequency='" + search_term + "'"; sql +=
				 * " OR ad.notes='" + search_term + "'"; sql +=
				 * " OR ad.airtime like '%" + search_term + "%'";
				 */
			}

			System.out.println("SORTING DIRECTION IS..." + dir + "column is..."
					+ col);
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
					.addScalar("advertisement_id", Hibernate.STRING)
					.addScalar("c_cname", Hibernate.STRING)
					.addScalar("advertisement_type_name", Hibernate.STRING)
					.addScalar("advertisement_name", Hibernate.STRING)
					.addScalar("start_date", Hibernate.STRING)
					.addScalar("end_date", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("amount", Hibernate.STRING)
					.addScalar("duration", Hibernate.STRING)
					.addScalar("display_peak_hour", Hibernate.STRING)
					.addScalar("notes", Hibernate.STRING)

					.addScalar("frequency", Hibernate.STRING)
					.addScalar("airtime", Hibernate.STRING);
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
								+ rs.get("advertisement_id")
								+ "' value='"
								+ rs.get("advertisement_id") + "'/>");
				jatickstock.add(i + 1);
				jatickstock.add(rs.get("advertisement_name"));
				jatickstock.add(rs.get("c_cname"));
				  if(rs.get("start_date")!=null)
	                {
				jatickstock.add(cm.getDateToPicker(rs.get("start_date")
						.toString()));
	                }
				  else
				  {
					  jatickstock.add("");  
				  }
				  if(rs.get("end_date")!=null)
				  {
				jatickstock.add(cm.getDateToPicker(rs.get("end_date")
						.toString()));
				  }
				  else
				  {
					  jatickstock.add("");  
				  }

				jatickstock.add(rs.get("amount"));
				jatickstock.add(rs.get("duration"));
				
				arraytickstock.add(jatickstock);

			}
			result.put("aaData", arraytickstock);
			int cnt = getTotaAdvertisementDataForCustomer(request, col, dir,customerId,advertiseId);

			result.put("iTotalRecords", cnt);

			result.put("iTotalDisplayRecords", cnt);

			System.out
					.println("****ticket array data...---->" + arraytickstock);

			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}
	private int getTotaAdvertisementDataForCustomer(HttpServletRequest request,
			String col, String dir,int customerId,int advertiseId) {

		Session alertconfigsession = null;
		int cnt = 0;
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			alertconfigsession = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql="";
			if( (customerId==-1)&&(advertiseId==-1))
			{
				sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
						+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
						+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
						+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE')  " +
						" and ad.deleted_status=0";
			}
			
			else
			{
				if(advertiseId==-1)
				{
				sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
						+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
						+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
						+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE')  " +
						" and ad.deleted_status=0 and ad.customer_id='"+customerId+"'";
				}
				
				else
				{
					sql = "select ad.advertisement_id,cu.c_cname,adt.advertisement_type_name,ad.notes,ad.advertisement_name,ad.start_date,ad.end_date,"
							+ " ad.status,ad.amount,ad.duration,ad.display_peak_hour,ad.frequency,ad.airtime from advertisement ad "
							+ " inner join  advertisement_type adt on ad.advertisement_type=adt.advertisement_type_id "
							+ " inner join customer cu on ad.customer_id=cu.c_cid where ad.status in('ACTIVE','INACTIVE')  " +
							" and ad.deleted_status=0 and ad.advertisement_id='"+advertiseId+"' and ad.customer_id='"+customerId+"'"; 
				}
			}

			System.out
					.println("\n \t JSONObject getTotaPisDisplayMappingData 1111..............");
			int srch = 0;

			String search_term = request.getParameter("sSearch").trim();
			if (isInteger(search_term)) {
				srch = Integer.parseInt(search_term);

			}

			if (search_term != null && !search_term.equals("")) {

				System.out.println("search_term---------" + search_term);

				sql += " and ad.advertisement_id='" + srch + "'";
				
				sql += " OR ad.advertisement_name like '%" + search_term + "%'";
				sql += " OR ad.start_date like '%" + search_term + "%'";
				sql += " OR ad.end_date like '%" + search_term + "%'";
			
				/*
				 * sql += " OR ad.amount='" + search_term + "'"; sql +=
				 * " OR ad.duration='" + search_term + "'"; sql +=
				 * " OR ad.display_peak_hour='" + search_term + "'"; sql +=
				 * " OR ad.frequency='" + search_term + "'"; sql +=
				 * " OR ad.notes='" + search_term + "'"; sql +=
				 * " OR ad.airtime like '%" + search_term + "%'";
				 */
			}
			System.out.println("sql----" + sql);
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("advertisement_id", Hibernate.STRING)
					.addScalar("c_cname", Hibernate.STRING)
					.addScalar("advertisement_type_name", Hibernate.STRING)
					.addScalar("advertisement_name", Hibernate.STRING)
					.addScalar("start_date", Hibernate.STRING)
					.addScalar("end_date", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("amount", Hibernate.STRING)
					.addScalar("duration", Hibernate.STRING)
					.addScalar("display_peak_hour", Hibernate.STRING)
					.addScalar("notes", Hibernate.STRING)

					.addScalar("frequency", Hibernate.STRING)
					.addScalar("airtime", Hibernate.STRING);
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

	public Map<Integer, String> getAdvertisementList() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select advertisement_name,advertisement_id from advertisement where deleted_status=0 and status='ACTIVE' and advertisement_name!='NULL' "
					+ " order by advertisement_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			List<OrganisationChart> list = query.list();
			resultMap.put(-1, "All");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

				resultMap.put(Integer.parseInt(rs.get("advertisement_id").toString()), rs
						.get("advertisement_name").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return resultMap;
	}

	public List<String> getAdvertiseId(int customerId) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry = "select advertisement_id from advertisement where deleted_status=0 and status='ACTIVE' " +
					" and advertisement_name!='NULL' and  customer_id='"+customerId +"'" +
							" order by advertisement_name";

			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("advertisement_id").toString());
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

	public List<String> getAdvertiseName(int customerId) {
		Session session = null;
		List list = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			list = new ArrayList();

			String qry =  "select advertisement_name from advertisement where deleted_status=0 and status='ACTIVE' " +
					" and advertisement_name!='NULL' and  customer_id='"+customerId +"'" +
							" order by advertisement_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("advertisement_name").toString());
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
	public static String rbKey = String.valueOf(getSysParameterForVts());
	
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
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getTotalVehicleDataDateTime(String Lat, String Long,String Start_time,String end_time) {

		JSONObject result = new JSONObject();
System.out.println("in get method");
		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			String dist="100";
			TimeUnit.SECONDS.sleep(10);   // to hold result for 10 sec
			
			VtsResponse alertresult = port.webGetVehicleDataOnMapDateTime(Lat, Long,Start_time,end_time,dist,
					rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);

				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME()!=null?alertresult.getVtsDatamodel().get(i).getSCHEDULENAME():"Not Mapped");
				ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				//System.out.println("alertresult.getVtsDatamodel().get(i).getDEPOTNAME()"+alertresult.getVtsDatamodel().get(i).getScheduleNumberName());
				ja.add(alertresult.getVtsDatamodel().get(i).getScheduleNumberName()); // depot name
//				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLEDIRECTION());
				array.add(ja);
			}
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}
	
}
