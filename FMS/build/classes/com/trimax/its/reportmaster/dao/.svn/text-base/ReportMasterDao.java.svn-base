package com.trimax.its.reportmaster.dao;

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

import com.trimax.its.reportmaster.model.ReportMaster;
import com.trimax.its.util.HibernateUtil;

public class ReportMasterDao {

	public int getTotalRecords(int total, HttpServletRequest request,String cols, String dir,String viewdeletedrecord) {
		//String viewdeletedrecord = null;
		Criteria criteria ;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(ReportMaster.class);
		}else{
			 criteria = session.createCriteria(ReportMaster.class);
		     criteria.add(Restrictions.eq("deleted_status", 0));
		}
		if (!(request.getParameter("sSearch")==null)) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("report_master_name", "%" + search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
			criteria.add(conditionGroup);

		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}

		List<ReportMaster> list = criteria.list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			String searchSQL = "";
			String sql = " from ReportMaster  ";

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ReportMaster.class);
			}else{
				 criteria = session.createCriteria(ReportMaster.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			if (!(request.getParameter("sSearch")==null)) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("report_master_name", "%"
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
			
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<ReportMaster> list = criteria.list();
			JSONArray array = new JSONArray();
						
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				/*String note=WordUtils.wrap(list.get(i).getNotes(),25);
				System.out.println("NOTES-----0"+note);			*/					
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getReport_master_id()
						+ "' value='"
						+ list.get(i).getReport_master_id() + "'/>");
				ja.add(list.get(i).getReport_master_id());
				ja.add(list.get(i).getReport_master_name()!=null?list.get(i).getReport_master_name().replaceAll(" ", "&nbsp;"):"");
				//ja.add(WordUtils.wrap(note, 10));
				//ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getReport_master_id()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getReport_master_id()+"'/>Record in Database");	

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

	public int createReportMaster(ReportMaster reportmaster) {
		// TODO Auto-generated method stub		
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			session.beginTransaction();
			int i = 0;

			try {
				i = (Integer) session.save(reportmaster);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();			

			} finally {
				session.close();
			}
			return i;

		
	}
	
	public ReportMaster getEditedReportMaster(int id) {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		ReportMaster reportMaster = (ReportMaster) session.get(ReportMaster.class, new Integer(id));

		return reportMaster;

	}
	
	public String updateReportMaster(ReportMaster eportMaster,int report_master_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();

		try {
			ReportMaster reportMaster = (ReportMaster) session.get(ReportMaster.class, report_master_id);
			reportMaster.setReport_master_name(eportMaster.getReport_master_name());
			reportMaster.setStatus(eportMaster.getStatus());
			reportMaster.setUpdated_date(new java.util.Date());
			reportMaster.setUpdated_by(eportMaster.getUpdated_by());
			// Code to Update ComponenttypeMaster...
			session.update(reportMaster);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	
	public boolean checkReportMaster(String reportmastername) {
		boolean flag = false;
		String qry = " select report_master_name from report_master where report_master_name='"
				+ reportmastername + "' and deleted_status= " + 0;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean checkReportMasterForUpdate(String reportmastername, int id) {
		boolean flag = false;
		String qry = " select report_master_name from report_master where report_master_name='"
				+ reportmastername + "' and report_master_id= " + id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);

		if (query.list().size() > 0) {
			flag = true;
		}
		return flag;
	}
	
	public String deleteReportMaster(ReportMaster reportmaster,int report_master_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		
		try {
			//
			ReportMaster reportMaster = (ReportMaster) session.get(ReportMaster.class, report_master_id);

			reportMaster.setStatus("INACTIVE");
			reportMaster.setDeleted_status(1);
			reportMaster.setUpdated_by(reportmaster.getUpdated_by());
			reportMaster.setUpdated_date(new java.util.Date());

			session.update(reportMaster);
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
