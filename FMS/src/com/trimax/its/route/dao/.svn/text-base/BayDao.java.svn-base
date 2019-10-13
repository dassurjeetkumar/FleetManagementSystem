package com.trimax.its.route.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.model.User;
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.util.HibernateUtil;

public class BayDao {
	public int addBay(Bay bay) {
		int i = 0;
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSession("");
			tx = session.beginTransaction();

			String query = "from Bay where bay_name='"
					+ bay.getBay_name().trim() + "' and deleted_status=0"
					+ " and floor.floor_id=" + bay.getFloor().getFloor_id();

			List list = session.createQuery(query).list();

			if (list.size() <= 0) {

				Floor floor = getFloorById(session, bay.getFloor()
						.getFloor_id());

				bay.setParent_id(floor.getOrgChart().getOrg_chart_id());

				i = (Integer) session.save(bay);
			} else {
				i = -1;
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
		return i;
	}

	public int updateBay(Bay bay) {
		int i = 0;
		Session session = null;
		Transaction tx = null;

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			session = HibernateUtil.getSession("");
			tx = session.beginTransaction();

			String qry = "from Bay where bay_name='" + bay.getBay_name().trim()
					+ "' and deleted_status=0" + " and floor.floor_id="
					+ bay.getFloor().getFloor_id();

			List<Bay> list = session.createQuery(qry).list();

			if (list.size() <= 0 || list.get(0).getBay_id() == bay.getBay_id()) {

				Bay bayNew = (Bay) session.get(Bay.class, bay.getBay_id());

				bayNew.setBay_name(bay.getBay_name());
				bayNew.setFloor(bay.getFloor());

				Floor floor = getFloorById(session, bay.getFloor()
						.getFloor_id());

				bayNew.setParent_id(floor.getOrgChart().getOrg_chart_id());

				String userid = request.getSession().getAttribute("userid")
						.toString();

				if (userid != null || userid.equals("")) {
					bayNew.setUpdated_by(Integer.parseInt(userid));
				}

				bayNew.setUpdated_date(new Date());
				bayNew.setStatus(bay.getStatus());

				session.update(bayNew);
				i = 1;
			} else {
				i = -1;
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			i = 0;
		} finally {
			session.close();
		}
		return i;
	}

	public Bay getBayById(int id) {

		Session session = null;
		Transaction tx = null;
		Bay bay = null;

		try {
			session = HibernateUtil.getSession("");

			String query = "from Bay where bay_id=" + id
					+ " and deleted_status=0";

			List list = session.createQuery(query).list();

			bay = (Bay) list.get(0);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
		return bay;
	}

	public String deleteBay(int id) {

		Session session = null;
		String s = "error";
		try {

			session = HibernateUtil.getSession("");
			session.beginTransaction();

			Bay bay = (Bay) session.load(Bay.class, new Integer(id));

			HttpServletRequest request = ServletActionContext.getRequest();
			User user = (User) request.getSession().getAttribute("user");

			bay.setUpdated_date(new java.util.Date());
			bay.setUpdated_by(user.getUserId());
			bay.setDeleted_status(1);
			s = "success";
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.getTransaction().commit();
			HibernateUtil.closeSession();
		}

		return s;
	}

	public int getTotalRecords(HttpServletRequest request, String col,
			String dir) {
		// Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//
		// Query query = session.createSQLQuery(
		// "select count(*) as count from bay where deleted_status=0").addScalar(
		// "count", Hibernate.INTEGER);
		//
		// List<Integer> list = query.list();
		// int cnt = list.get(0);

		int cnt = 0;
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Bay.class);
			criteria.add(Restrictions.ne("deleted_status", 1));

			criteria.createAlias("floor", "floor");
			criteria.createAlias("floor.orgChart", "orgChart");

			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("bay_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("floor.floor_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("orgChart.org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				criteria.add(conditionGroup);

			}

			List<Bay> list = criteria.list();
			cnt = list.size();
		} catch (Exception e) {

		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,
			String col, String dir) {
		JSONObject result = new JSONObject();

		try {

			int totalAfterFilter = total;

			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Bay.class);
			criteria.add(Restrictions.ne("deleted_status", 1));

			criteria.createAlias("floor", "floor");
			criteria.createAlias("floor.orgChart", "orgChart");

			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			} else {
				criteria.addOrder(Order.asc("bay_id"));
			}

			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				conditionGroup.add(Restrictions.like("bay_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("floor.floor_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("orgChart.org_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				criteria.add(conditionGroup);

			}

			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));

			List<Bay> list = criteria.list();

			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				/*ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getBay_id()
						+ "' value='"
						+ list.get(i).getBay_id() + "'/>");*/

				ja.add(list.get(i).getBay_id());
				ja.add(list.get(i).getBay_name() != null ? list.get(i)
						.getBay_name().toString() : "");
				ja.add(list.get(i).getFloor().getFloor_name() != null ? list
						.get(i).getFloor().getFloor_name() : "");
				ja.add(list.get(i).getFloor().getOrgChart().getOrg_name() != null ? list
						.get(i).getFloor().getOrgChart().getOrg_name()
						: "");
				ja.add(list.get(i).getStatus() != null ? list.get(i)
						.getStatus() : "");

				array.add(ja);
				// System.out.println("Array----->" + array);
			}

			totalAfterFilter = getTotalRecords(request, col, dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public Map<Integer, String> getFloorName() {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("Select f.floor_id,f.floor_name from Floor f where f.deleted_status=0 order by f.floor_name");
		List list = query.list();

		Object[] obj;
		int floorId;
		String fName;

		for (Iterator i = list.iterator(); i.hasNext();) {

			obj = (Object[]) i.next();
			floorId = (Integer) obj[0];
			fName = (String) obj[1];
			resultMap.put(floorId, fName);
		}
		return resultMap;

	}

	public String getFloorNameByByPid(int id) {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("Select f.floor_id,f.floor_name from Floor f where f.deleted_status=0 and f.orgChart.org_chart_id="
						+ id + " order by f.floor_name");
		List list = query.list();

		Object[] obj;
		int floorId;
		String fName;

		String regionTypeAjaxString = "";
		if (list.size() > 0) {
			regionTypeAjaxString += "<option value='0'>Select</option>";
		}

		for (Iterator i = list.iterator(); i.hasNext();) {

			obj = (Object[]) i.next();
			floorId = (Integer) obj[0];
			fName = (String) obj[1];
			regionTypeAjaxString += "<option value=" + floorId + ">" + fName
					+ "</option>";
		}
		return regionTypeAjaxString;

	}

	public Map<Integer, String> getFloorName(int pid) {

		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("Select f.floor_id,f.floor_name from Floor f where f.deleted_status=0 and parent_id="
						+ pid + "  order by f.floor_name");
		List list = query.list();

		Object[] obj;
		int floorId;
		String fName;

		for (Iterator i = list.iterator(); i.hasNext();) {

			obj = (Object[]) i.next();
			floorId = (Integer) obj[0];
			fName = (String) obj[1];
			resultMap.put(floorId, fName);
			System.out.println(floorId);
		}
		return resultMap;

	}

	public Floor getFloorById(Session session, int id) {
		Floor floor = null;

		try {

			floor = (Floor) session.get(Floor.class, new Integer(id));
		} catch (Exception e) {
		}

		return floor;
	}

	public String getFloorNameById(int id) {
		Floor floor = null;
		String fname = "";
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			floor = (Floor) session.get(Floor.class, new Integer(id));
			fname = floor.getFloor_name();
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return fname;
	}

	public int getTotalRecordsForBusstationFloor(HttpServletRequest request,
			String col, String dir, int FloorId) {
		// Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//
		// Query query = session.createSQLQuery(
		// "select count(*) as count from floor where deleted_status=0").addScalar(
		// "count", Hibernate.INTEGER);
		//
		// List<Integer> list = query.list();
		// int cnt = list.get(0);
		int cnt = 0;
		try {
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Bay.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			criteria.add(Restrictions.eq("floor.floor_id", FloorId));
			criteria.createAlias("floor", "floor");
			// criteria.createAlias("floor.orgChart","orgChart");
			// criteria.createAlias("Floor", "floor");
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}

			List<Bay> list = criteria.list();
			cnt = list.size();
		} catch (Exception e) {

		}

		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForBusstationFloor(int total,
			HttpServletRequest request, String col, String dir, int FloorId) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {

			int totalAfterFilter = total;

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Bay.class);
			criteria.add(Restrictions.ne("deleted_status", 1));
			criteria.add(Restrictions.eq("floor.floor_id", FloorId));
			criteria.createAlias("floor", "floor");
			// criteria.createAlias("floor.orgChart","orgChart");
			// criteria.createAlias("Floor", "floor");
			// criteria.createAlias("orgChart","orgChart");
			// criteria.createAlias("floor","Chart");
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			} else {
				criteria.addOrder(Order.asc("floor.floor_id"));
			}

			List<Bay> list = criteria.list();

			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				/*
				 * ja.add("<input type='hidden'  id='" +
				 * list.get(i).getFloor_id()+ "' value='" +
				 * list.get(i).getFloor_id() + "'/>");
				 */
				// ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+
				// list.get(i).getFloor_id()+ "' value='"+
				// list.get(i).getFloor_id() + "'/>");
				ja.add(list.get(i).getBay_id());
				ja.add(list.get(i).getBay_name() != null ? list.get(i)
						.getBay_name().toString() : "");
				// ja.add(list.get(i).getOrgChart().getOrg_name() != null ?
				// list.get(i).getOrgChart().getOrg_name() : "");
				ja.add(list.get(i).getStatus() != null ? list.get(i)
						.getStatus() : "");
				ja.add("<a class='edit' href='' id='bay_edit_"
						+ list.get(i).getBay_id() + "' value='"
						+ list.get(i).getBay_id() + "'> Edit</a>");
				ja.add("<a class='delete' href='' id='bay_delete_"
						+ list.get(i).getBay_id() + "' value='"
						+ list.get(i).getBay_id() + "'> Delete</a>");
				array.add(ja);
				// System.out.println("Array----->" + array);
			}

			// totalAfterFilter =
			// getTotalRecordsForBusstationFloor(request,col,dir,FloorId);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// HibernateUtil.closeSession();
		}
		return result;
	}

	public int updateFloorDetails(String floorname, String status, int id,
			HttpServletRequest request) {
		int updateid = 0;
		int userId = Integer.parseInt(request.getSession()
				.getAttribute("userid").toString());
		String sql = "update bay set bay_name='" + floorname + "' , status='"
				+ status + "',updated_by=" + userId + ",updated_date=now() where bay_id=" + id;
		Session session = null;
		try {

			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Transaction txn = session.beginTransaction();
			Query qry = session.createSQLQuery(sql);
			txn.commit();
			qry.executeUpdate();
		} catch (Exception ex) {
			session.getTransaction().rollback();
		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
		return id;
	}

	public void insertFloorDetails(String floor_name, String status,
			int floor_id, int parent_id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Floor floor = new Floor();
		Session session = null;
		int userId = Integer.parseInt(request.getSession()
				.getAttribute("userid").toString());
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql = "insert into bay (bay_name,floor_id,parent_id,status,created_by,updated_by) values ('"
				+ floor_name
				+ "',"
				+ floor_id
				+ ","
				+ parent_id
				+ ",'"
				+ status + "'," + userId + ",'0')";
		try {
			/*
			 * floor.setFloor_name(floor_name); floor.setStatus(status);
			 * floor.getOrgChart().setParent_id(parentid);
			 */
			Transaction txn = session.beginTransaction();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			// session.save(floor);

			txn.commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}
	}

	public void deleteFloor(int floortid, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Floor floor = new Floor();
		Session session = null;
		int userId = Integer.parseInt(request.getSession()
				.getAttribute("userid").toString());
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql = "update bay set deleted_status=1,updated_by=" + userId
				+ " where bay_id=" + floortid;
		try {
			/*
			 * floor.setFloor_name(floor_name); floor.setStatus(status);
			 * floor.getOrgChart().setParent_id(parentid);
			 */
			Transaction txn = session.beginTransaction();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			// session.save(floor);

			txn.commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			/*
			 * if(session!=null){ session.close(); }
			 */
		}

	}
	public boolean checkDuplcateBay(String bay_name,int parent_id,int floorId)
	{
		boolean flag=false;
		try{
			String qry = " select bay_name from bay where bay_name='"+bay_name+"' and floor_id="+floorId+"";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			if(query.list().size()>0)
			{
				flag=true;
			}
		}catch(Exception ex)
		{
			
		}finally{
			
		}
		return flag;
	}
	
	public boolean checkDuplcateBayForUpdate(String bay_name,int floorId,int bayId)
	{
		boolean flag=false;
		try{
			String qry = " select bay_name from bay where bay_name='"+bay_name+"' and floor_id="+floorId+" and bay_id="+bayId;
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			if(query.list().size()>0)
			{
				flag=true;
			}
		}catch(Exception ex)
		{
			
		}finally{
			
		}
		return flag;
	}

}
