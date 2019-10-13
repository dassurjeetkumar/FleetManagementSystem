package com.trimax.its.device.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.trimax.its.device.model.DeviceHistory;
import com.trimax.its.util.HibernateUtil;

public class DeviceHistoryDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from devicehistory ").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		Criteria criteria = session.createCriteria(DeviceHistory.class);

		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			if (isInteger(search_term)) {
				int scrh = Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("activity_done_by",
						scrh));
				conditionGroup.add(Restrictions.eq("div.device_id",
						scrh));

			} else {
//				conditionGroup.add(Restrictions.eq("device_history_id",
//						Integer.parseInt(search_term)));

				
				conditionGroup.add(Restrictions.like("status",
						"%"+ search_term +"%"));
			}
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		
		criteria.createCriteria("deviceHis", "div");
		// criteria.createCriteria("vendorid", "ven");
		List<DeviceHistory> list = criteria.list();
		
		return list.size();
		
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,
			String cols, String dir) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from DeviceHistory  ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(DeviceHistory.class);

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				if (isInteger(search_term)) {
					int scrh = Integer.parseInt(search_term);
					conditionGroup.add(Restrictions.eq("activity_done_by",
							scrh));
					conditionGroup.add(Restrictions.eq("div.device_id",
							scrh));

				} else {
//					conditionGroup.add(Restrictions.eq("device_history_id",
//							Integer.parseInt(search_term)));

					
					conditionGroup.add(Restrictions.like("status",
							"%"+ search_term +"%"));
				}
				criteria.add(conditionGroup);

			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			criteria.createCriteria("deviceHis", "div");
			// criteria.createCriteria("vendorid", "ven");
			List<DeviceHistory> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getDevice_history_id()
						+ "' value='"
						+ list.get(i).getDevice_history_id() + "'/>");
				ja.add(list.get(i).getDevice_history_id());
				ja.add(list.get(i).getDeviceHis().getDevice_id());
				ja.add(list.get(i).getOnboarding_date()!=null ? list.get(i).getOnboarding_date().toString() : "");
				ja.add(list.get(i).getActivity_done_by());
				ja.add(list.get(i).getDeviceHis().getUpdated_date() != null ? list
						.get(i).getDeviceHis().getUpdated_date().toString()
						: "");
				ja.add(list.get(i).getDeviceHis().getPurchase_date() != null ? list
						.get(i).getDeviceHis().getPurchase_date().toString()
						: "");
				ja.add(list.get(i).getMalfunctioning_date() !=null ? list.get(i).getMalfunctioning_date().toString() : "");
				
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getDeviceHis().getNotes());
				array.add(ja);
				System.out.println("Array----->" + array);
			}

			
				totalAfterFilter = getTotalRecords(total,request,cols,dir);
				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);

				result.put("iTotalDisplayRecords", totalAfterFilter);
			
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
		} catch (Exception e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}
