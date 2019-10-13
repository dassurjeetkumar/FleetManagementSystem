package com.trimax.its.passengercategory.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.passengercategory.model.PassengerCategory;
import com.trimax.its.util.HibernateUtil;

public class PassengerCategoryDao {

	public int getTotalRecords(int total, HttpServletRequest request,
			String cols, String dir) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		Criteria criteria = session.createCriteria(PassengerCategory.class);
		criteria.add(Restrictions.eq("deleted_status", 0));

		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("passenger_category_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,
					MatchMode.START));
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}

		List<PassengerCategory> list = criteria.list();
		
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,
			String col, String dir) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			String searchSQL = "";
			String sql = " from PassengerCategory  ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(PassengerCategory.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("passenger_category_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", search_term,
						MatchMode.START));
				criteria.add(conditionGroup);

			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			List<PassengerCategory> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getPassenger_category_id()
						+ "' value='"
						+ list.get(i).getPassenger_category_id()+ "'/>");
				ja.add(list.get(i).getPassenger_category_id());
				ja.add(list.get(i).getPassenger_category_name()!=null?list.get(i).getPassenger_category_name().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());

				array.add(ja);
			}

			totalAfterFilter = getTotalRecords(total, request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public int createPassengerCategory(PassengerCategory passengerCategory) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;

		try {
			i = (Integer) session.save(passengerCategory);
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
		return i;

	}
	public PassengerCategory getEditedPassengerCategory(int id) {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		PassengerCategory passengerCategory = (PassengerCategory) session.get(
				PassengerCategory.class, new Integer(id));

		return passengerCategory;

	}

	public String updatePassengerCategory(PassengerCategory passengerCategory,
			int passenger_category_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();

		try {
			//
			PassengerCategory passengercategory = (PassengerCategory) session.get(
					PassengerCategory.class, passenger_category_id);
			passengercategory.setPassenger_category_name(passengerCategory
					.getPassenger_category_name());
			passengercategory.setNotes(passengerCategory.getNotes());
			passengercategory.setStatus(passengerCategory.getStatus());
			passengercategory.setUpdated_date(new java.util.Date());
			passengercategory.setUpdated_by(passengerCategory.getUpdated_by());
			// Code to Update passengerCategoryMaster...
			session.update(passengercategory);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	public String deletePassengerCategory(PassengerCategory passengerCategory,
			int voucher_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		
		try {
			//
			PassengerCategory passengercategory = (PassengerCategory) session.get(
					PassengerCategory.class, voucher_id);

			passengercategory.setStatus("INACTIVE");
			passengercategory.setDeleted_status(1);
			passengercategory.setUpdated_by(passengerCategory.getUpdated_by());
			passengercategory.setUpdated_date(new java.util.Date());

			session.update(passengercategory);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	public boolean checkPassengerCategory(String passname) {
		boolean flag = false;
		String qry = " select passenger_category_name from passenger_population_category where passenger_category_name='"
				+ passname + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	public boolean checkPassengerCategoryForUpdate(String passencat, int id) {
		boolean flag = false;
		String qry = " select  passenger_category_name from passenger_population_category where passenger_category_name='"
				+ passencat + "' and passenger_category_id= " + id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
}
