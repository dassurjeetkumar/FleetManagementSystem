package com.trimax.its.breaktype.dao;

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

import com.trimax.its.device.model.Device;
import com.trimax.its.transport.model.BreakType;
import com.trimax.its.util.HibernateUtil;

public class BreakTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from break_type where deleted_status=0 ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		Criteria criteria = session.createCriteria(BreakType.class);
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
		
		}else{
			
			criteria.add(Restrictions.eq("deletedStatus", 0));
		}
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();
			if(isInteger(search_term))
			{
			int srch=Integer.parseInt(search_term);
			conditionGroup.add(Restrictions.eq("id",
					srch ));
			
			
			}else{
			conditionGroup.add(Restrictions.like("breakTypeName",
					"%" + search_term + "%"));
			conditionGroup.add(Restrictions.like("status", "%"
					+ search_term + "%"));
			
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
				
		List<BreakType> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			
			String sql = " from Device  ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(BreakType.class);
			
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			
			}else{
				
				criteria.add(Restrictions.eq("deletedStatus", 0));
			}
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();
				if(isInteger(search_term))
				{
				int srch=Integer.parseInt(search_term);
				conditionGroup.add(Restrictions.eq("id",
						srch ));
				
				
				}else{
				conditionGroup.add(Restrictions.like("breakTypeName",
						"%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%"
						+ search_term + "%"));
				
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
			
			List<BreakType> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getId()
						+ "' value='"
						+ list.get(i).getId() + "'/>");
				ja.add(list.get(i).getId());
				ja.add(list.get(i).getBreakTypeName());
				ja.add(list.get(i).getDuration());
				
				
				ja.add(list.get(i).getSteeringHours()!=0 ? "Yes" : "No");
				ja.add(list.get(i).getSpreadHours() !=0 ? "Yes" : "No");
				ja.add(list.get(i).getOt_hours() !=0 ? "Yes" : "No");
				ja.add(list.get(i).getRest() !=0 ? "Yes" : "No");
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=String.valueOf(list.get(i).getDeletedStatus());
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getId()+"'/>Deleted Record");	

						}else{
						//ja.add("Record in Database");	
							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getId()+"'/>Record in Database");	

					}
					
					
				}else{
					
				}
				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
			totalAfterFilter = getTotalRecords(total,request,cols,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", total);
			

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
	    } catch(Exception e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public int saveBreaktype(BreakType breaktype)
	{
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int id=0;
		try {

			session.beginTransaction();
			id=(Integer) session.save(breaktype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}
	public BreakType getEditedBreaktype(int parseInt) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		BreakType breaktype = (BreakType) session.get(BreakType.class, new Integer(
				parseInt));
		// System.out.println("--------->>>" +
		// fareChart.getFarechart_master_id()
		// + "\t" + fareChart.getRoute_id());
		return breaktype;
	}
	public String updateBreaktype(BreakType breakT, int device_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			BreakType breaktype = (BreakType) session.get(BreakType.class, device_id);

			breaktype.setBreakTypeName(breakT.getBreakTypeName());
			breaktype.setDuration(breakT.getDuration());
			breaktype.setOt_hours(breakT.getOt_hours());
			breaktype.setSpreadHours(breakT.getSpreadHours());
			breaktype.setSteeringHours(breakT.getSteeringHours());
			breaktype.setRest(breakT.getRest());
			breaktype.setStatus(breakT.getStatus());
			breaktype.setUpdatedDate(new java.util.Date());
			
			session.update(breaktype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	
	public String deleteBreaktype(BreakType breakT, int device_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			BreakType breaktype = (BreakType) session.get(BreakType.class, device_id);

			//breaktype.setStatus("INACTIVE");
			breaktype.setDeletedStatus(1);
			//breaktype.setUpdatedBy(breakT.getUpdatedBy());
			breaktype.setUpdatedDate(new java.util.Date());

			session.update(breaktype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	
	public boolean checkBreaktype(String breaktypename)
	{
		boolean flag=false;
		String qry = " select break_type_name from break_type where deleted_status=0 and break_type_name=? and deleted_status=0 ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setParameter(0, breaktypename);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public boolean checkBreaktypeForUpdate(String breaktypename,int id)
	{
		boolean flag=false;
		String qry = " select break_type_name from break_type where break_type_name=? and break_type_id=? ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setParameter(0, breaktypename);
		query.setParameter(1, id);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}

}
