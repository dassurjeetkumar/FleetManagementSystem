package com.trimax.its.transport.dao;

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
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.util.HibernateUtil;

public class ScheduleTypeDao {

	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from schedule_type where deleted_status=0 ")
				.addScalar("count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Criteria criteria = session.createCriteria(ScheduleType.class);
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
				conditionGroup.add(Restrictions.eq("schedule_type_id",
						 srch  ));
			}else{
			conditionGroup.add(Restrictions.like("scheduleName",
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
		
		
		List<ScheduleType> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir) {
		JSONObject result = new JSONObject();
		try {
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from ScheduleType ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(ScheduleType.class);
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				
			}else{
			criteria.add(Restrictions.eq("deletedStatus", 0));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch");

				Junction conditionGroup = Restrictions.disjunction();
				if(isInteger(search_term))
				{
					int srch=Integer.parseInt(search_term);
					conditionGroup.add(Restrictions.eq("schedule_type_id",
							 srch  ));
				}else{
				conditionGroup.add(Restrictions.like("scheduleName",
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
			
			List<ScheduleType> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				//ja.add(123);
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getSchedule_type_id()
						+ "' value='"
						+ list.get(i).getSchedule_type_id() + "'/>");
				ja.add(list.get(i).getSchedule_type_id());
				ja.add(list.get(i).getScheduleName());
				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNotes());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=String.valueOf(list.get(i).getDeletedStatus());
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getSchedule_type_id()+"'/>Deleted Record");	

						}else{
							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getSchedule_type_id()+"'/>Record in Database");	
					}
					
					
				}else{
					
				}
				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
			totalAfterFilter = getTotalRecords(total,request,cols,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);
			
			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public int insertScheduleType(ScheduleType scheduletype) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		try{
			scheduletype.setScheduleName(scheduletype.getScheduleName().trim());
		i = (Integer) session.save(scheduletype);
		session.getTransaction().commit();
		}catch(Exception ex)
		{
			
		}finally{
		session.close();
		}
		return i;

	}
	
	public ScheduleType getEditedScheduleType(int id) {
		// BusStops busstops = new BusStops();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		ScheduleType scheduletype = (ScheduleType) session.get(
				ScheduleType.class, new Integer(id));
		//System.out.println("--------->>>" + fareChart.getFarechart_master_id()
			//	+ "\t" + fareChart.getRoute_id());
		return scheduletype;

	}
	
	public String updateScheduleType(ScheduleType scheduleType, int schedule_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	System.out.println("=====schedule_type_id id" +schedule_type_id);
	try{
		//
		ScheduleType scheduletype = (ScheduleType) session.get(ScheduleType.class,schedule_type_id);
		scheduletype.setScheduleName(scheduleType.getScheduleName().trim());
		scheduletype.setStatus(scheduleType.getStatus());
		scheduletype.setNotes(scheduleType.getNotes());
		scheduletype.setUpdatedDate(new java.util.Date());
		scheduletype.setUpdatedBy(scheduleType.getUpdatedBy());
		
		session.update(scheduletype);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
	}
	
	public String deleteScheduleType(ScheduleType scheduleType, int schedule_type_id,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	System.out.println("=====Route id" + schedule_type_id);
	try{
		//
		//scheduleType.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		ScheduleType scheduletype = (ScheduleType) session.get(ScheduleType.class,schedule_type_id);
		//scheduletype.setStatus("INACTIVE");
		scheduletype.setDeletedStatus(1);
		//scheduletype.setUpdatedBy(scheduleType.getUpdatedBy());
		scheduletype.setUpdatedDate(new java.util.Date());
		session.update(scheduletype);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
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
	
	public boolean checkScheduletype(String breaktypename)
	{
		boolean flag=false;
		String qry = " select schedule_type_name from schedule_type where schedule_type_name='"+breaktypename+"' and deleted_status=0 ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public boolean checkScheduletypeForUpdate(String scheduletypename,int id)
	{
		boolean flag=false;
		String qry = " select schedule_type_name from schedule_type where schedule_type_name='"+scheduletypename+"' and schedule_type_id= "+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}

}
