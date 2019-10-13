package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.device.model.Device;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.AccidentType;
import com.trimax.its.vehicle.model.DockingType;

public class DockingTypeDao {

	boolean isSuccess  = false;
	
	public DockingType getDockingTypeDetails(int dockingTypeId)
	{
		
		DockingType dockingTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(DockingType.class);
			criteria.add(Restrictions.eq("docking_type_id",dockingTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				dockingTypeData  = (DockingType) result;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(session != null)
			{
				session.close();
			}
			return dockingTypeData;
		}
	}
	
	public boolean saveEditedDetails(DockingType dockingTypeObject)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			
			DockingType dockingTypeObjectFromSession = (DockingType) session.get(DockingType.class, dockingTypeObject.getDocking_type_id());
			dockingTypeObjectFromSession.setDocking_type(dockingTypeObject.getDocking_type().trim());
			dockingTypeObjectFromSession.setDockingTypeKms(dockingTypeObject.getDockingTypeKms().trim());
			dockingTypeObjectFromSession.setDockingKmAlert(dockingTypeObject.getDockingKmAlert().trim());
			dockingTypeObjectFromSession.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			dockingTypeObjectFromSession.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			
			transaction = session.beginTransaction();
			session.update(dockingTypeObjectFromSession);
			session.getTransaction().commit();
			System.out.println("DockingType edited success....");
			isSuccess = true;			
		}
		catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(session != null){
				session.close();
			}
			return isSuccess;			
		}
	}
	public boolean saveDockingType(DockingType dockingTypeObject)
	{

		DockingType DockingTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			dockingTypeObject.setDocking_type(dockingTypeObject.getDocking_type().trim());
			dockingTypeObject.setDockingTypeKms(dockingTypeObject.getDockingTypeKms().trim());
			dockingTypeObject.setDockingKmAlert(dockingTypeObject.getDockingKmAlert().trim());
			dockingTypeObject.setStatus("ACTIVE");
			dockingTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			dockingTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			
			Integer id = (Integer) session.save(dockingTypeObject);
			System.out.println("DockingType edited success....");
			isSuccess = true;
			ServletActionContext.getRequest().setAttribute("createdDockingTypeId", id);
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(session != null){
					session.close();
			}
				return isSuccess;
		}
	}
	
	/*public boolean deleteDockingType(int DockingTypeId)
	{
		DockingType DockingTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			DockingType dockingTypeObject =  (DockingType) session.get(DockingType.class,DockingTypeId);
			
			dockingTypeObject.setDeleted_status(1);
			dockingTypeObject.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			dockingTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

			transaction = session.beginTransaction();
			session.update(dockingTypeObject);
			session.getTransaction().commit();
			isSuccess = true;
			System.out.println("DockingType delete success....");
			
		}catch(Exception e){
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(session != null){
					session.close();
			}
				return isSuccess;
		}
	}*/
	
	public boolean isDockingTypeNew(DockingType dockingTypeObject,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from docking_type where docking_type='"+ dockingTypeObject.getDocking_type().trim()+"'";
			
			if(reqType.equals("update")){
				query = "select count(*)as count from docking_type where docking_type='"+ dockingTypeObject.getDocking_type().trim()+"' and 	docking_type_id	 not in ('"+dockingTypeObject.getDocking_type_id()+"') ";
			}
			Query queryObject = session.createSQLQuery(query).addScalar("count", Hibernate.INTEGER);
			List<Integer> list = queryObject.list();
			int cnt = list.get(0);

			if (cnt > 0) {
				isNew = false;
			} else {
				isNew = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isNew = false;
		} finally {
			if(session!=null){
				session.close();
			}
			return isNew;
		}
	}
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 Criteria criteria = session.createCriteria(DockingType.class);
				criteria.add(Restrictions.eq("deleted_status", 0));
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					try{
						conditionGroup.add(Restrictions.eq("docking_type_id",Integer.parseInt(search_term)));
						//conditionGroup.add(Restrictions.eq("dockingTypeKms",Integer.parseInt(search_term)));
					}catch(Exception ex){
						
					}
					conditionGroup.add(Restrictions.like("dockingTypeKms",search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("dockingKmAlert",search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("docking_type", search_term, MatchMode.ANYWHERE));
						/*conditionGroup.add(Restrictions.like("accident_type_name",
								"%" + search_term + "%"));*/
					criteria.add(conditionGroup);
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				
						List<AccidentType> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			
		}
		return cnt;
	}
	public int getTotalRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createSQLQuery("select count(*) as count from docking_type where deleted_status=0 ").addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir) {

		JSONObject result = new JSONObject();
		Session session = null;

		String[] dbcol = { "", "docking_type_id", "docking_type", "dockingTypeKms","dockingKmAlert" };
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(DockingType.class);
			criteria.add(Restrictions.eq("deleted_status", 0));

			//String col = dbcol[index];
			if (!col.equals("")) {
				if (!dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			String search_term = request.getParameter("sSearch").trim();
			if (search_term != null && !search_term.equals("")) {
				Junction conditionGroup = Restrictions.disjunction();
				try {
					conditionGroup.add(Restrictions.eq("docking_type_id",Integer.parseInt(search_term)));
					
				} catch (Exception e) {
				}
				conditionGroup.add(Restrictions.like("dockingTypeKms",search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("dockingKmAlert",search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("docking_type", search_term, MatchMode.ANYWHERE));
				criteria.add(conditionGroup);
			}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));

			List<DockingType> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ list.get(i).getDocking_type_id()+ "' value='"+ list.get(i).getDocking_type_id() + "'/>");
				ja.add(list.get(i).getDocking_type_id());
				ja.add(list.get(i).getDocking_type().replace(" ", "&nbsp;"));
				ja.add(list.get(i).getDockingTypeKms());
				ja.add(list.get(i).getDockingKmAlert());
				array.add(ja);
			}
			totalAfterFilter = getTotalRecords(total,request,col,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);
			result.put("iTotalDisplayRecords", totalAfterFilter);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
			/*if (search_term != null && !search_term.equals("")) {
				totalAfterFilter = getTotalRecordsForSeacrch(total, request,
						col, dir);

				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			} else {
				totalAfterFilter = getTotalDockingTypeRecords();
				result.put("aaData", array);
				result.put("iTotalRecords", total);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return result;
		}
	}*/

	public int getTotalDockingTypeRecords() {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(DockingType.class);
		criteria.add(Restrictions.eq("deleted_status", 0));
		List<Integer> list = criteria.list();
		int cnt = list.size();
		return cnt;
	}

	public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,	String cols, String dir) {

		Session session;
		int cnt = 0;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(DockingType.class);
			criteria.add(Restrictions.eq("deleted_status", 0));
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				try {
					criteria.add(Restrictions.eq("docking_type_id",Integer.parseInt(search_term)));
				} catch (Exception e) {
				}
				criteria.add(Restrictions.like("docking_type", search_term,MatchMode.ANYWHERE));
				criteria.add(conditionGroup);
			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			List<Device> list = criteria.list();
			cnt = list.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
/*	public String addDockingtype(DockingType docking) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			session.beginTransaction();
			session.save(docking);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return "success";
	}

	public DockingType getEditedDockingType(int parseInt) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		DockingType dockingtype = (DockingType) session.get(DockingType.class,
				new Integer(parseInt));
		// System.out.println("--------->>>" +
		// fareChart.getFarechart_master_id()
		// + "\t" + fareChart.getRoute_id());
		return dockingtype;
	}

	public String updateDockingType(DockingType docking, int device_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			DockingType dockingObj = (DockingType) session.get(
					DockingType.class, device_id);
			dockingObj.setDocking_type(docking.getDocking_type());
			dockingObj.setStatus(docking.getStatus());
			dockingObj.setNotes(docking.getNotes());
			dockingObj.setUpdated_by(docking.getUpdated_by());
			dockingObj.setUpdated_date(docking.getUpdated_date());

			session.update(dockingObj);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

}


 * try { int totalAfterFilter = total; String[] cols = { "", "docking_type_id",
 * "docking_type", "dockingKms"};
 * 
 * String searchSQL = ""; String sql = " from DockingType  ";
 * 
 * // sql += " order by " + COL_NAME + " " + DIR;
 * 
 * sql += " limit " + request.getParameter("iDisplayStart") + ", " +
 * request.getParameter("iDisplayLength"); Session session =
 * HibernateUtil.getSession("hibernate.cfg.xml"); Criteria criteria =
 * session.createCriteria(DockingType.class);
 * criteria.add(Restrictions.eq("deleted_status", 0));
 * 
 * if (!request.getParameter("sSearch").equals("")) { String search_term =
 * request.getParameter("sSearch");
 * 
 * Junction conditionGroup = Restrictions.disjunction();
 * 
 * conditionGroup.add(Restrictions.like("docking_type", "%" + search_term +
 * "%"));
 * 
 * conditionGroup.add(Restrictions.like("status", "%" + search_term + "%"));
 * 
 * criteria.add(conditionGroup);
 * 
 * } if (cols.equals("")) { if (dir.equals("asc")) {
 * criteria.addOrder(Order.asc(cols)); } else {
 * criteria.addOrder(Order.desc(cols)); } }
 * criteria.setFirstResult(Integer.parseInt(request
 * .getParameter("iDisplayStart")));
 * criteria.setMaxResults(Integer.parseInt(request
 * .getParameter("iDisplayLength")));
 * 
 * List<DockingType> list = criteria.list(); JSONArray array = new JSONArray();
 * System.out.println("List size----->" + list.size() + "\t" +
 * request.getParameter("iDisplayStart")); for (int i = 0; i < list.size(); i++)
 * { JSONArray ja = new JSONArray(); ja.add(
 * "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
 * + list.get(i).getDocking_type_id() + "' value='" +
 * list.get(i).getDocking_type_id() + "'/>");
 * ja.add(list.get(i).getDocking_type_id());
 * ja.add(list.get(i).getDocking_type());
 * 
 * ja.add(list.get(i).getNotes()); ja.add(list.get(i).getStatus());
 * array.add(ja); System.out.println("Array----->" + array); }
 * 
 * totalAfterFilter = getTotalRecords(); result.put("aaData", array);
 * result.put("iTotalRecords", total);
 * 
 * result.put("iTotalDisplayRecords", totalAfterFilter);
 * 
 * System.out.println("REsult ------>" + request.getParameter("iDisplayStart"));
 * } catch (Exception ex) { ex.printStackTrace(); } return result;
 
*/