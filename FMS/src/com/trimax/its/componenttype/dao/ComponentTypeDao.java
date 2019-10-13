package com.trimax.its.componenttype.dao;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

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

import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.util.HibernateUtil;

public class ComponentTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols, String dir,String viewdeletedrecord) {
		//String viewdeletedrecord = null;
		Criteria criteria ;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(ComponentType.class);
		}else{
			 criteria = session.createCriteria(ComponentType.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("component_type_name", "%"
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

		List<ComponentType> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			String searchSQL = "";
			String sql = " from ComponentType  ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ComponentType.class);
			}else{
				 criteria = session.createCriteria(ComponentType.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("component_type_name", "%"
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
			List<ComponentType> list = criteria.list();
			JSONArray array = new JSONArray();
						
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				/*String note=WordUtils.wrap(list.get(i).getNotes(),25);
				System.out.println("NOTES-----0"+note);			*/					
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getComponent_type_id()
						+ "' value='"
						+ list.get(i).getComponent_type_id() + "'/>");
				ja.add(list.get(i).getComponent_type_id());
				ja.add(list.get(i).getComponent_type_name()!=null?list.get(i).getComponent_type_name().replaceAll(" ", "&nbsp;"):"");
				//ja.add(WordUtils.wrap(note, 10));
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getComponent_type_id()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getComponent_type_id()+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
			}

			
			totalAfterFilter = getTotalRecords(total, request, col, dir,viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int createComponentType(ComponentType componentType) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;

		try {
			i = (Integer) session.save(componentType);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();			

		} finally {
			session.close();
		}
		return i;

	}

	public String deleteComponentType(ComponentType componentType,
			int component_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		
		try {
			//
			ComponentType componenttype = (ComponentType) session.get(
					ComponentType.class, component_type_id);

			//componenttype.setStatus("INACTIVE");
			componenttype.setDeleted_status(1);
			componenttype.setUpdated_by(componentType.getUpdated_by());
			componenttype.setUpdated_date(new java.util.Date());

			session.update(componenttype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	public ComponentType getEditedComponentType(int id) {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		ComponentType componentType = (ComponentType) session.get(ComponentType.class, new Integer(id));

		return componentType;

	}

	public String updateComponentType(ComponentType componentType,
			int component_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();

		try {
			//
			ComponentType componenttype = (ComponentType) session.get(
					ComponentType.class, component_type_id);
			componenttype.setComponent_type_name(componentType
					.getComponent_type_name());
			componenttype.setNotes(componentType.getNotes());
			componenttype.setStatus(componentType.getStatus());
			componenttype.setUpdated_date(new java.util.Date());
			componenttype.setUpdated_by(componentType.getUpdated_by());
			// Code to Update ComponenttypeMaster...
			session.update(componenttype);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

	public boolean checkComponentType(String componentname) {
		boolean flag = false;
		String qry = " select component_name from components_type where component_name='"
				+ componentname + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean checkComponentTypeForUpdate(String component, int id) {
		boolean flag = false;
		String qry = " select component_name from components_type where component_name='"
				+ component + "' and component_type_id= " + id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	
	public static String [] wrapText (String text, int len)
	{
	  // return empty array for null text
	  if (text == null)
	  return new String [] {};

	  // return text if len is zero or less
	  if (len <= 0)
	  return new String [] {text};

	  // return text if less than length
	  if (text.length() <= len)
	  return new String [] {text};

	  char [] chars = text.toCharArray();
	  Vector lines = new Vector();
	  StringBuffer line = new StringBuffer();
	  StringBuffer word = new StringBuffer();

	  for (int i = 0; i < chars.length; i++) {
	    word.append(chars[i]);

	    if (chars[i] == ' ') {
	      if ((line.length() + word.length()) > len) {
	        lines.add(line.toString());
	        line.delete(0, line.length());
	      }

	      line.append(word);
	      word.delete(0, word.length());
	    }
	  }

	  // handle any extra chars in current word
	  if (word.length() > 0) {
	    if ((line.length() + word.length()) > len) {
	      lines.add(line.toString());
	      line.delete(0, line.length());
	    }
	    line.append(word);
	  }

	  // handle extra line
	  if (line.length() > 0) {
	    lines.add(line.toString());
	  }

	  String [] ret = new String[lines.size()];
	  int c = 0; // counter
	  for (Enumeration e = lines.elements(); e.hasMoreElements(); c++) {
	    ret[c] = (String) e.nextElement();
	  }

	  return ret;
	}
	
}