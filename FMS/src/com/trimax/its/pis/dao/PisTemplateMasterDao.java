package com.trimax.its.pis.dao;

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

import com.trimax.its.pis.model.PisTemplateMaster;
import com.trimax.its.util.HibernateUtil;

public class PisTemplateMasterDao {

	public int getTotalRecords(int total, HttpServletRequest request,String cols, String dir, String viewdeletedrecord) {
		// TODO Auto-generated method stub
		Criteria criteria ;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(PisTemplateMaster.class);
		}else{
			 criteria = session.createCriteria(PisTemplateMaster.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		if (request.getParameter("sSearch")!=null) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("template_name", "%"
					+ search_term + "%"));
			
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}

		List<PisTemplateMaster> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols, String dir, String viewdeletedrecord) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			String searchSQL = "";
			String sql = " from PisTemplateMaster  ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(PisTemplateMaster.class);
			}else{
				 criteria = session.createCriteria(PisTemplateMaster.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			if (request.getParameter("sSearch")!=null) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("template_name", "%"
						+ search_term + "%"));
				
				criteria.add(conditionGroup);

			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
//			System.out.println("request.getParameter(iDisplayStart)=="+request.getParameter("iDisplayStart"));
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<PisTemplateMaster> list = criteria.list();
			JSONArray array = new JSONArray();
						
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				/*String note=WordUtils.wrap(list.get(i).getNotes(),25);
				System.out.println("NOTES-----0"+note);			*/					
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getTemplate_id()
						+ "' value='"
						+ list.get(i).getTemplate_id() + "'/>");
				ja.add(list.get(i).getTemplate_id());
				ja.add(list.get(i).getTemplate_name()!=null?list.get(i).getTemplate_name().replaceAll(" ", "&nbsp;"):"");
				//ja.add(WordUtils.wrap(note, 10));
//				ja.add(list.get(i).getNotes());
//				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getTemplate_id()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getTemplate_id()+"'/>Record in Database");	

					}
				
				}else{
					
				}
				array.add(ja);
			}

			
			totalAfterFilter = getTotalRecords(total, request, cols, dir,viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public int createPisTemplateMaster(PisTemplateMaster pisTemplateMaster) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i = 0;

		try {
			pisTemplateMaster.setStatus("ACTIVE");
			i = (Integer) session.save(pisTemplateMaster);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();			

		} finally {
			session.close();
		}
		return i;

	}
	
	public PisTemplateMaster getEditedPisTemplateMaster(int template_id){
		Session session=HibernateUtil.getSession("hibernate.cfg.xml");
		PisTemplateMaster pisTemplateMaster=(PisTemplateMaster) session.get(PisTemplateMaster.class, template_id);
		return pisTemplateMaster;
		
	}

	public String updateTemplate(PisTemplateMaster pisTemplateMaster,int template_id) {
		Session session=HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			PisTemplateMaster pisTemplatemaster = (PisTemplateMaster) session.get(PisTemplateMaster.class, template_id);
			pisTemplatemaster.setTemplate_name(pisTemplateMaster.getTemplate_name());
			
			pisTemplatemaster.setUpdated_date(new java.util.Date());
			pisTemplatemaster.setUpdated_by(pisTemplateMaster.getUpdated_by());
			// Code to Update PisTemplateMaster...
			session.update(pisTemplatemaster);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return null;	
		}
	
	public String deletePisTemplateid(PisTemplateMaster pisTemplateMaster,int template_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		
		try {
			//
			PisTemplateMaster componenttype = (PisTemplateMaster) session.get(PisTemplateMaster.class, template_id);

			//componenttype.setStatus("INACTIVE");
			componenttype.setDeleted_status(1);
			componenttype.setUpdated_by(pisTemplateMaster.getUpdated_by());
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
	
	public boolean checkTemplateName(String templatename) {
		boolean flag = false;
		String qry = " select template_name from pis_template_master where template_name='"
				+ templatename + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean checkTemplateNameforUpdate(String templatename,int id) {
		boolean flag = false;
		String qry = " select template_name from pis_template_master where template_name='"
				+ templatename + "' and template_id= " + id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}

}
