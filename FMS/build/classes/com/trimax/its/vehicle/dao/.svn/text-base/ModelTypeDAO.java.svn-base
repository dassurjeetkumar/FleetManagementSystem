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

import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.model.Device;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.ModelType;

public class ModelTypeDAO {
	boolean isSuccess = false;
	public List<ModelType> getModelTypeList()
	{
		List<ModelType> modelTypeList = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(ModelType.class);
			
			criteria.add(Restrictions.eq("deletedStatus",0));
			List<Object> resultSetArray = criteria.list();
			modelTypeList = new ArrayList<ModelType>();
			for(int i=0;i<resultSetArray.size();i++)
			{
				
				Object result = resultSetArray.get(i);
				ModelType ModelType  = (ModelType) result;
				modelTypeList.add(ModelType);
				
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
			return modelTypeList;
		}
	}
	
	public ModelType getModelTypeDetails(int modelTypeId)
	{
		
		ModelType modelTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(ModelType.class);
			criteria.add(Restrictions.eq("model_type_id",modelTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				modelTypeData  = (ModelType) result;
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
			return modelTypeData;
		}
	}
	
	public boolean saveEditedDetails(ModelType modelTypeObject)
	{
		Session session = null;
		Transaction transaction = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			
			ModelType modelTypeObjectFromSession = (ModelType) session.get(ModelType.class, modelTypeObject.getModel_type_id());
			modelTypeObjectFromSession.setModel_type_name(modelTypeObject.getModel_type_name().trim());
			modelTypeObjectFromSession.setStatus(modelTypeObject.getStatus());
			modelTypeObjectFromSession.setNotes(modelTypeObject.getNotes());
			modelTypeObjectFromSession.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			modelTypeObjectFromSession.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			
			transaction = session.beginTransaction();
			session.update(modelTypeObjectFromSession);
			session.getTransaction().commit();
			System.out.println("ModelType edited success....");
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
	public boolean saveModelType(ModelType modelTypeObject)
	{

		ModelType ModelTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			modelTypeObject.setModel_type_name(modelTypeObject.getModel_type_name().trim());
			modelTypeObject.setStatus(modelTypeObject.getStatus());
			modelTypeObject.setNotes(modelTypeObject.getNotes().trim());
			modelTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			modelTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			Integer id = (Integer) session.save(modelTypeObject);
			System.out.println("ModelType edited success....");
			isSuccess = true;
			ServletActionContext.getRequest().setAttribute("createdModelTypeId", id);
			
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
	
	public String deleteModelType(int ModelTypeId)
	{
		String status="error";
		ModelType ModelTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"model_type",ModelTypeId);
			if(status.trim().equalsIgnoreCase("")){
			ModelType modelTypeObject =  (ModelType) session.get(ModelType.class,ModelTypeId);
			
			modelTypeObject.setDeletedStatus(1);
			modelTypeObject.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			modelTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

			transaction = session.beginTransaction();
			session.update(modelTypeObject);
			session.getTransaction().commit();
			isSuccess = true;
			System.out.println("ModelType delete success....");
			}
		}catch(Exception e){
			status="error";
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(session != null){
					session.close();
			}
			return status;
		}
	}
	
	public boolean isModelTypeNew(ModelType modelTypeObject,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from model_type where model_type_name='"+ modelTypeObject.getModel_type_name().trim()+"' and deleted_status='0'";
			
			if(reqType.equals("update")){
				query = "select count(*)as count from model_type where model_type_name='"+ modelTypeObject.getModel_type_name().trim()+"' and 	model_type_id	 not in ('"+modelTypeObject.getModel_type_id()+"') and deleted_status='0' ";
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
	public JSONObject getDockingHistoryData(int total,HttpServletRequest request, int index, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","model_type_id","model_type_name","notes","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(ModelType.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ModelType.class);
			}else{
				 criteria = session.createCriteria(ModelType.class);
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
					conditionGroup.add(Restrictions.eq("model_type_id", Integer.parseInt(search_term)));
				}catch(Exception e){		}
				conditionGroup.add(Restrictions.like("model_type_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				
				criteria.add(conditionGroup);
			}
			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));

			
			List<ModelType> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getModel_type_id() + "' value='" + list.get(i).getModel_type_id()+ "'/>");
				ja.add(list.get(i).getModel_type_id());
				ja.add(list.get(i).getModel_type_name().replace(" ", "&nbsp;"));
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getModel_type_id()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getModel_type_id()+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
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
				totalAfterFilter = getTotalModelTypeHistoryRecords();
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
	
	
	public int getTotalModelTypeHistoryRecords() {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Criteria criteria = session.createCriteria(ModelType.class);
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		HttpServletRequest request = ServletActionContext.getRequest();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(ModelType.class);
		}else{
			 criteria = session.createCriteria(ModelType.class);
		     criteria.add(Restrictions.eq("deletedStatus", 0));
		}
		List<Integer> list = criteria.list();
		int cnt = list.size();
		return cnt;
	}
	public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,String cols ,String dir) {
		
		Session session;
		int cnt=0;
		try{
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 //Criteria criteria = session.createCriteria(ModelType.class);
			//	criteria.add(Restrictions.eq("deletedStatus", 0));
			 Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(ModelType.class);
				}else{
					 criteria = session.createCriteria(ModelType.class);
				     criteria.add(Restrictions.eq("deletedStatus", 0));
				}

				if (!request.getParameter("sSearch").equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					Junction conditionGroup = Restrictions.disjunction();
					
					try{    
						conditionGroup.add(Restrictions.eq("model_type_id", Integer.parseInt(search_term)));
					}catch(Exception e){		}
					conditionGroup.add(Restrictions.like("model_type_name",search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
					criteria.add(conditionGroup);
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {       
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));        
					}
				}
				List<ModelType> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}

}
