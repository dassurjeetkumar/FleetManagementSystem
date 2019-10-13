package com.trimax.its.transport.dao;

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


import com.trimax.its.transport.model.CaseType;
import com.trimax.its.util.HibernateUtil;

import freemarker.ext.servlet.HttpRequestHashModel;


public class CaseTypeDAO {
	boolean isSuccess = false;
	public List<CaseType> getCaseTypeList()
	{
		List<CaseType> caseTypeList = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			//Query query =session.createQuery("from CaseType where deletedStatus=0");
			Criteria criteria = session.createCriteria(CaseType.class);
			
			criteria.add(Restrictions.eq("deletedStatus",0));
			List<Object> resultSetArray = criteria.list();
			caseTypeList = new ArrayList<CaseType>();
			for(int i=0;i<resultSetArray.size();i++)
			{
				
				Object result = resultSetArray.get(i);
				CaseType CaseType  = (CaseType) result;
				caseTypeList.add(CaseType);
				
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
			return caseTypeList;
		}
	}
	
	public CaseType getCaseTypeDetails(int caseTypeId)
	{
		
		CaseType caseTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(CaseType.class);
			criteria.add(Restrictions.eq("caseTypeId",caseTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				caseTypeData  = (CaseType) result;
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
			return caseTypeData;
		}
	}
	
	public boolean saveEditedDetails(CaseType caseTypeObject)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			
			CaseType caseTypeObjectFromSession = (CaseType) session.get(CaseType.class, caseTypeObject.getCaseTypeId());
			caseTypeObjectFromSession.setCaseTypeName(caseTypeObject.getCaseTypeName().trim());
			caseTypeObjectFromSession.setStatus(caseTypeObject.getStatus());
			caseTypeObjectFromSession.setNotes(caseTypeObject.getNotes());
			caseTypeObjectFromSession.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			caseTypeObjectFromSession.setUpdatedDate(new Date());
			
			transaction = session.beginTransaction();
			session.update(caseTypeObjectFromSession);
			session.getTransaction().commit();
			System.out.println("CaseType edited success....");
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
	public boolean saveCaseType(CaseType caseTypeObject)
	{

		CaseType CaseTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			caseTypeObject.setCaseTypeName(caseTypeObject.getCaseTypeName().trim());
			caseTypeObject.setStatus(caseTypeObject.getStatus());
			caseTypeObject.setNotes(caseTypeObject.getNotes().trim());
			caseTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			caseTypeObject.setCreatedDate(new Date());
			Integer id = (Integer) session.save(caseTypeObject);
			System.out.println("CaseType edited success....");
			isSuccess = true;
			ServletActionContext.getRequest().setAttribute("createdCaseTypeId", id);
			
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
	
	@SuppressWarnings("finally")
	public boolean deleteCaseType(int CaseTypeId)
	{
		CaseType CaseTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			transaction = session.beginTransaction();
			CaseType caseTypeObject =  (CaseType) session.get(CaseType.class,CaseTypeId);
			int userId  = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			
			caseTypeObject.setDeletedStatus(1);
			caseTypeObject.setUpdatedBy(userId);
			caseTypeObject.setUpdatedDate(new Date());

			
			session.saveOrUpdate(caseTypeObject);
			session.getTransaction().commit();
			isSuccess = true;
			System.out.println("CaseType delete success....");
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			isSuccess = false;
		}
		finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
	}
	
	public boolean isCaseTypeNew(CaseType caseTypeObject,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from case_type where case_type_name='"+ caseTypeObject.getCaseTypeName().trim()+"' and deleted_status='0'";
			
			if(reqType.equals("update")){
				query = "select count(*)as count from case_type where case_type_name='"+ caseTypeObject.getCaseTypeName().trim()+"' and case_type_id	 not in ('"+caseTypeObject.getCaseTypeId()+"') and  deleted_status='0' ";
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
			if(session!=null){
				session.close();
			}
			return isNew;
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getCaseTypeHistoryData(int total,HttpServletRequest request, int index, String dir) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","caseTypeId","caseTypeName","notes","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(CaseType.class);
			
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			
			
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
			
			}else{
				
				criteria.add(Restrictions.eq("deletedStatus", 0));
			}
			
			
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
					conditionGroup.add(Restrictions.eq("caseTypeId", Integer.parseInt(search_term)));
				}catch(Exception e){		
					
				}
				conditionGroup.add(Restrictions.like("caseTypeName", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				conditionGroup.add(Restrictions.like("notes", search_term, MatchMode.ANYWHERE));
				
				criteria.add(conditionGroup);
			}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));

			
			List<CaseType> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getCaseTypeId() + "' value='" + list.get(i).getCaseTypeId()+ "'/>");
				ja.add(list.get(i).getCaseTypeId());
				ja.add(list.get(i).getCaseTypeName().replace(" ", "&nbsp;"));
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					String deletedstatus=String.valueOf(list.get(i).getDeletedStatus());
					System.out.println("deletedstatus---"+deletedstatus);
					
					if(deletedstatus.equalsIgnoreCase("1"))
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+list.get(i).getCaseTypeId()+"'/>Deleted Record");	

						}else{
						//ja.add("Record in Database");
							ja.add("<input type='hidden' value='Y' id='isRocordEligible"+list.get(i).getCaseTypeId()+"'/>Record in Database");	

					}
					
					
				}else{
					
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
				totalAfterFilter = getTotalCaseTypeHistoryRecords();
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
	
	
	public int getTotalCaseTypeHistoryRecords() {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Criteria criteria = session.createCriteria(CaseType.class);
		HttpServletRequest request = ServletActionContext.getRequest();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
		
		}else{
			
			criteria.add(Restrictions.eq("deletedStatus", 0));
		}
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		List<Integer> list = criteria.list();
		int cnt = list.size();
		return cnt;
	}
	public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,String cols ,String dir) {
		
		Session session;
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 Criteria criteria = session.createCriteria(CaseType.class);
			 
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
					
					try{
						conditionGroup.add(Restrictions.eq("caseTypeId", Integer.parseInt(search_term)));
					}catch(Exception e){		}
					
					conditionGroup.add(Restrictions.like("caseTypeName", search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
					conditionGroup.add(Restrictions.like("notes", search_term, MatchMode.ANYWHERE));
					
					criteria.add(conditionGroup);
				}
				if (!cols.equals("")) {
					if (!dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				List<CaseType> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}

}
