package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import sun.security.x509.IssuerAlternativeNameExtension;

import com.trimax.its.common.DependencyChecker;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.CancellationCause;
import com.trimax.its.vehicle.model.VehicleType;


public class CancellationDAO {

	
	
	@SuppressWarnings("unchecked")
	public JSONObject getCancelltnCauseData(int total,HttpServletRequest request, int index, String dir) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","cancellation_id","cancellation_name","notes","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			Criteria criteria ;
			 criteria = session.createCriteria(CancellationCause.class);
			 criteria.add(Restrictions.eq("deletedstatus", 0));
			 
			String col = dbcol[index];
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
				try{
					conditionGroup.add(Restrictions.eq("cancellation_id", Integer.parseInt(search_term)));
				}catch(Exception e){		}
				conditionGroup.add(Restrictions.like("cancellation_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<CancellationCause> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getCancellation_id() + "' value='" + list.get(i).getCancellation_id()+ "'/>");
				ja.add(list.get(i).getCancellation_id());
				ja.add(list.get(i).getCancellation_name().replace(" ", "&nbsp;"));
				ja.add(list.get(i).getNotes());
				if((list.get(i).getStatus()).equalsIgnoreCase("Y")){
					ja.add("ACTIVE");
				}else{
					ja.add("INACTIVE");
				}
				
				
				array.add(ja);
			}

			if (search_term != null && !search_term.equals("")) {
				totalAfterFilter =getTotalRecordsForSeacrch(total,request,col,dir);

				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
			else{
				totalAfterFilter = getTotalCancellationRecords();
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
	}
	public int getTotalCancellationRecords() {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		 
		
		Criteria criteria ;
		
			 criteria = session.createCriteria(CancellationCause.class);
		
		
		List<Integer> list = criteria.list();
		int cnt = list.size();
		return cnt;
	}
	public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,String cols ,String dir) {
		
		Session session;
		int cnt=0;
		try{
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				Criteria criteria ;
					 criteria = session.createCriteria(CancellationCause.class);
					 criteria.add(Restrictions.eq("deletedstatus", 0));


				if (!cols.equals("")) {
					if (!dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				String search_term = request.getParameter("sSearch").trim();
				if (search_term != null && !search_term.equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					try{
						conditionGroup.add(Restrictions.eq("cancellation_id", Integer.parseInt(search_term)));
					}catch(Exception e){		}
					conditionGroup.add(Restrictions.like("cancellation_name", search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
					
					criteria.add(conditionGroup);
				}
				List<CancellationCause> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}
	
	public boolean isCancleCauseTypeNew(CancellationCause cancellationDetails,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from cancellation_cause where cancellation_name='"+ cancellationDetails.getCancellation_name().trim()+"'";
			if(reqType.equals("create")){
			 
			}
			else if(reqType.equals("update"))
			{
				query = "select count(*)as count from cancellation_cause " +
						" where cancellation_name='"+ cancellationDetails.getCancellation_name().trim()+"' and " +
								" cancellation_id not in ('"+cancellationDetails.getCancellation_id()+"') and deletedstatus='0'";
				
			}
			Query queryObject = session.createSQLQuery(query).addScalar(
					"count", Hibernate.INTEGER);
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
			return isNew;
		}
	}

	@SuppressWarnings("finally")
	public boolean saveNewCanceltionType(CancellationCause cancellationDetails)
	{
		boolean isSeuccess = false;
		Session session = null;
		Transaction transaction = null;
		CancellationCause cancellationDetail=new CancellationCause();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			
			cancellationDetail.setCancellation_name(cancellationDetails.getCancellation_name().trim());
			cancellationDetail.setNotes(cancellationDetails.getNotes().trim());
			cancellationDetail.setStatus("Y");
			cancellationDetail.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			cancellationDetail.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			cancellationDetail.setUpdatedBy(0);
			Integer id = (Integer) session.save(cancellationDetail);
			session.getTransaction().commit();
			System.out.println("Cancellation Cause created success.... having id---->>>"+id);
			ServletActionContext.getRequest().setAttribute("createdCancellationCauseId", id);
			isSeuccess = true;

		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
				isSeuccess  = false;
				e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return isSeuccess;
		}
		
	}	
	public CancellationCause getEditCanclDetails(String canclTypeId)
	{
		CancellationCause canclcause = null;
		Session session= null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from CancellationCause where cancellation_id =? ");
			query.setParameter(0, Integer.parseInt(canclTypeId));
			
			List<Object>  resultSetArray = query.list();
			
				Object result = resultSetArray.get(0);
				canclcause = (CancellationCause) result;
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
			return canclcause;
		}
		
	}
	
	public boolean saveEditCanlCauseDetails(CancellationCause cancellationDetails)
	{
		boolean returnResult = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			CancellationCause cancellationDet = (CancellationCause) session.get(CancellationCause.class, cancellationDetails.getCancellation_id());
			
			cancellationDet.setCancellation_name(cancellationDetails.getCancellation_name().trim());
			cancellationDet.setNotes(cancellationDetails.getNotes().trim());
			cancellationDet.setStatus(cancellationDetails.getStatus().trim());
			cancellationDet.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			cancellationDet.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			
			session.update(cancellationDet);
			System.out.println("cancellationDetails  edit success.... having id---->>>");
			session.getTransaction().commit();
			returnResult = true;

		} catch (Exception e) {
			transaction.rollback();
			returnResult = false;
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return returnResult;
		}
		
	}
	
	public String deleteCauseCanclType(int canclcauseTypeId)
	{
		
		boolean isSucces = false;
		Session session= null;
		String status="";
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			
				
			transaction = session.beginTransaction();
			CancellationCause canclTypeObject = (CancellationCause) session.get(CancellationCause.class,canclcauseTypeId);
			canclTypeObject.setDeletedstatus(1);
			canclTypeObject.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			canclTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			
			session.update(canclTypeObject);
			session.getTransaction().commit();
			isSucces = true;
		    status="";
		} catch (Exception e) {
			status="error";
			if(transaction != null)
				transaction.rollback();
				isSucces = false;
				e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return status;
		}	
	}
	
}
