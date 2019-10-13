package com.trimax.its.vehicle.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.device.model.Device;
import com.trimax.its.transport.model.BreakType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.AccidentType;

public class AccidentTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 //Criteria criteria = session.createCriteria(AccidentType.class);
			//	criteria.add(Restrictions.eq("deleted_status", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(AccidentType.class);
				}else{
					 criteria = session.createCriteria(AccidentType.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}

				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
					try{
						conditionGroup.add(Restrictions.eq("accident_type_id",
								Integer.parseInt(search_term)));
					}catch(Exception ex)
					{
						
					}
					conditionGroup.add(Restrictions.like("accident_type_name", search_term, MatchMode.ANYWHERE));
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
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from AccidentType ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(AccidentType.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));[
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(AccidentType.class);
			}else{
				 criteria = session.createCriteria(AccidentType.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}

			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			if (!request.getParameter("sSearch").equals("")) {
				Junction conditionGroup = Restrictions.disjunction();
				String search_term = request.getParameter("sSearch").trim();
				
				try{
					conditionGroup.add(Restrictions.eq("accident_type_id",
							Integer.parseInt(search_term)));
				}catch(Exception ex)
				{
					
				}
				conditionGroup.add(Restrictions.like("accident_type_name", search_term, MatchMode.ANYWHERE));
				
				criteria.add(conditionGroup);
				
			}
			
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<AccidentType> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getAccident_type_id()
						+ "' value='"
						+ list.get(i).getAccident_type_id() + "'/>");
				ja.add(list.get(i).getAccident_type_id());
				ja.add(list.get(i).getAccident_type_name().toUpperCase());
				// ja.add(list.get(i).getNote());
				
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						//ja.add(" ");
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getAccident_type_id()+"'/>Deleted Record");	

					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getAccident_type_id()+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				System.out.println("Array----->" + array);
			}

			totalAfterFilter=total;//getTotalRecords(total,request,cols,dir);
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
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(Exception e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public int saveAccidentType(AccidentType accidenttype)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(accidenttype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}

	public boolean getDuplicate(String accName)
	{
		boolean flag=false;
		String qry = " select accident_type_name from accident_type where accident_type_name= ? ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setParameter(0, accName);
		
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public String deleteAccidentType(AccidentType accidenttype, int accid,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			AccidentType accobj = (AccidentType) session.get(AccidentType.class, accid);

			//accobj.setStatus("INACTIVE");
			accobj.setDeleted_status(1);
			accobj.setUpdated_by(Integer.parseInt(request.getSession()
					.getAttribute("userid").toString()));
			accobj.setUpdated_date(new java.util.Date());

			session.update(accobj);
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
