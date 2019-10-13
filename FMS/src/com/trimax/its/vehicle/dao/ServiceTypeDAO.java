package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Severity;
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
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.DependencyChecker;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.ServiceTypeTaxModel;

public class ServiceTypeDAO {

	
	public List<ServiceType> getServiceTypeList()
	{
		List<ServiceType> serviceTypeList = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(ServiceType.class);
			
			criteria.add(Restrictions.eq("deletedStatus",0));
			List<Object> resultSetArray = criteria.list();
			serviceTypeList = new ArrayList<ServiceType>();
			for(int i=0;i<resultSetArray.size();i++)
			{
				
				Object result = resultSetArray.get(i);
				ServiceType serviceType  = (ServiceType) result;
				serviceTypeList.add(serviceType);
				
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
			return serviceTypeList;
		}
	}
	
	public List<String> getServiceTypeName()
	{
			List list = new ArrayList();
			try{
			
			String qry = "SELECT service_type_name, status FROM service_type where status='ACTIVE'";
	        System.out.println("service type name is"+qry);
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("service_type_name").toString());
				}
			}
			System.out.println("query result");
			}catch(Exception e){
			}finally{
			HibernateUtil.closeSession();
			}
			return list;
		}
		
	
	public ServiceType getServiceTypeDetails(int serviceTypeId)
	{
		
		ServiceType serviceTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(ServiceType.class);
			criteria.add(Restrictions.eq("service_type_id",serviceTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				serviceTypeData  = (ServiceType) result;
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
			return serviceTypeData;
		}
	}
	
	
	public ServiceTypeTaxModel getServiceTaxDetails(int serviceTaxId)
	{
		
		ServiceTypeTaxModel serviceTaxData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(ServiceTypeTaxModel.class);
			criteria.add(Restrictions.eq("service_tax_id",serviceTaxId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				serviceTaxData  = (ServiceTypeTaxModel) result;
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
			return serviceTaxData;
		}
	}
	
	public boolean saveEditedDetails(ServiceType serviceTypeObject)
	{
		boolean isSuccess = false;
		ServiceType serviceTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			ServiceType serviceType = (ServiceType) session.get(ServiceType.class, serviceTypeObject.getService_type_id());
			serviceType.setNote(serviceTypeObject.getNote());
			serviceType.setStatus(serviceTypeObject.getStatus());
			serviceType.setService_type_name(serviceTypeObject.getService_type_name());
			serviceType.setAbbreviation(serviceTypeObject.getAbbreviation());
			serviceType.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			serviceType.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			transaction = session.beginTransaction();
			session.update(serviceType);
			System.out.println("ServiceType edited success....");
			session.getTransaction().commit();
			isSuccess = true;

			
		}catch(Exception e)
		{
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(session != null)
			{
				session.close();
			}
			return isSuccess;
		}
	}
		
		
		public boolean saveEditedTaxDetails(ServiceTypeTaxModel serviceTaxObject)
		{
			boolean isSuccess = false;
			ServiceTypeTaxModel serviceTaxData = null;
			Session session = null;
			Transaction transaction = null;
			try
			{
				session =  HibernateUtil.getSession("hibenate.cfg.xml");
				ServiceTypeTaxModel serviceTax = (ServiceTypeTaxModel) session.get(ServiceTypeTaxModel.class, serviceTaxObject.getService_type_id());
				serviceTax.setBaseValue(serviceTaxObject.getBaseValue());
				serviceTax.setStatus(serviceTaxObject.getStatus());
				serviceTax.setService_type_name(serviceTaxObject.getService_type_name());
//				serviceType.setAbbreviation(serviceTaxObject.getAbbreviation());
//				serviceTax.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
				serviceTax.setEnd_date((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
				transaction = session.beginTransaction();
				session.update(serviceTax);
				System.out.println("Service Tax edited success....");
				session.getTransaction().commit();
				isSuccess = true;

				
			}
		catch(Exception e)
		{
			e.printStackTrace();
			isSuccess = false;
		}
		finally{
			if(session != null)
			{
				session.close();
			}
			return isSuccess;
		}
	}
	
	public boolean  saveServiceType(ServiceType serviceTypeObject)
	{
		boolean isSucces = false;
		ServiceType serviceTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			Integer id = (Integer) session.save(serviceTypeObject);
			System.out.println("ServiceType edited success...."+id);
			ServletActionContext.getRequest().setAttribute("createdServiceTypeId", id);
			isSucces = true;
			
		}catch(Exception e){
			e.printStackTrace();
			isSucces = false;
		}
		finally{
			if(session != null){
					session.close();
			}
				return isSucces;
		}
	}
	
	public boolean  saveServiceTax(ServiceTypeTaxModel serviceTaxObject)
	{
		boolean isSucces = false;
		ServiceTypeTaxModel serviceTaxData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			Integer id = (Integer) session.save(serviceTaxObject);
			System.out.println("Service tax edited success...."+id);
			ServletActionContext.getRequest().setAttribute("createdServiceTypeId", id);
			isSucces = true;
			
		}catch(Exception e){
			e.printStackTrace();
			isSucces = false;
		}
		finally{
			if(session != null){
					session.close();
			}
				return isSucces;
		}
	}
	
	
	public String deleteServiceType(int serviceTypeId)
	{
		boolean isSuccess = false;
		String status="error";
		ServiceType serviceTypeData = null;
		Session session = null;
		Transaction transaction = null;
		
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session, "service_type", serviceTypeId);
			System.out.println("&&&&&&&&&&"+status);
			if(status.trim().equalsIgnoreCase("")){
			
				ServiceType serviceTypeObject =  (ServiceType) session.get(ServiceType.class,serviceTypeId);
			serviceTypeObject.setDeletedStatus(1);
			serviceTypeObject.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			serviceTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

			transaction = session.beginTransaction();
			session.update(serviceTypeObject);
			session.getTransaction().commit();
			isSuccess  = true;
			
			System.out.println("ServiceType delete success....");
			}
		}catch(Exception e){
			status="error:";
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
	
	
	public String deleteServiceTax(int serviceTaxId)
	{
		boolean isSuccess = false;
		String status="error";
		ServiceTypeTaxModel serviceTaxData = null;
		Session session = null;
		Transaction transaction = null;
		
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session, "service_type", serviceTaxId);
			System.out.println("&&&&&&&&&&"+status);
			if(status.trim().equalsIgnoreCase("")){
			
				ServiceTypeTaxModel serviceTaxObject =  (ServiceTypeTaxModel) session.get(ServiceTypeTaxModel.class,serviceTaxId);
			
				
			serviceTaxObject.setEnd_date((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

			transaction = session.beginTransaction();
			session.update(serviceTaxObject);
			session.getTransaction().commit();
			isSuccess  = true;
			
			System.out.println("Service Tax delete success....");
			}
		}catch(Exception e){
			status="error:";
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
	
	
	public boolean isServiceTypeNew(ServiceType serviceTypeObject,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from service_type where service_type_name='"+ serviceTypeObject.getService_type_name().trim()+"' and deleted_status='0'";
			if(reqType.equals("create")){
			 
			}
			else if(reqType.equals("update"))
			{
				query = "select count(*)as count from service_type where service_type_name='"+ serviceTypeObject.getService_type_name().trim()+"' and 	service_type_id	 not in ('"+serviceTypeObject.getService_type_id()+"')  and deleted_status='0'";
				
				//query = "select count(*)as count from vehicle where license_number not in ('"+ vehicleObject.getVehicleRegistrationNumber()+"') or " +
				//		"( vehicle_id ='"+vehicleObject.getVehicleId()+"' and license_number = '"+ vehicleObject.getVehicleRegistrationNumber()+"')";
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
	
	
	public boolean isServiceTaxNew(ServiceTypeTaxModel serviceTaxObject,String reqType) {
		boolean isNew = false;
           System.out.println("service tax newwwww");
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from Service_Tax where service_type_name='"+ serviceTaxObject.getService_type_name().trim()+"' and deleted_status='0'";
			if(reqType.equals("create")){
			 
			}
			else if(reqType.equals("update"))
			{
				query = "select count(*)as count from Service_Tax where service_type_name='"+ serviceTaxObject.getService_type_name().trim()+"' and 	service_type_id	 not in ('"+serviceTaxObject.getService_type_id()+"')  and deleted_status='0'";
				
				//query = "select count(*)as count from vehicle where license_number not in ('"+ vehicleObject.getVehicleRegistrationNumber()+"') or " +
				//		"( vehicle_id ='"+vehicleObject.getVehicleId()+"' and license_number = '"+ vehicleObject.getVehicleRegistrationNumber()+"')";
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
	
	@SuppressWarnings("unchecked")
	public JSONObject getServiceTypeData(int total,HttpServletRequest request, int index, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","service_type_id","service_type_name","note","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(ServiceType.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ServiceType.class);
			}else{
				 criteria = session.createCriteria(ServiceType.class);
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
					conditionGroup.add(Restrictions.eq("service_type_id", Integer.parseInt(search_term)));
				}catch(Exception e){		}
				conditionGroup.add(Restrictions.like("service_type_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<ServiceType> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getService_type_id() + "' value='" + list.get(i).getService_type_id()+ "'/>");
				ja.add(list.get(i).getService_type_id());
				ja.add(list.get(i).getService_type_name()!=null?list.get(i).getService_type_name().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getAbbreviation()!=null?list.get(i).getAbbreviation().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getNote());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getService_type_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getService_type_id()+"'/>Record in Database");	
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
				totalAfterFilter = getTotalServiceTypeRecords();
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
	@SuppressWarnings("unchecked")
	public JSONObject getServiceTaxData(int total,HttpServletRequest request, int index, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","service_type_id","service_type_name","baseValue","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(ServiceType.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ServiceTypeTaxModel.class);
			}else{
				 criteria = session.createCriteria(ServiceTypeTaxModel.class);
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
					conditionGroup.add(Restrictions.eq("service_type_id", Integer.parseInt(search_term)));
				}catch(Exception e){		}
				conditionGroup.add(Restrictions.like("service_type_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<ServiceTypeTaxModel> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getService_type_id() + "' value='" + list.get(i).getService_type_id()+ "'/>");
				ja.add(list.get(i).getService_type_id());
				ja.add(list.get(i).getService_type_name()!=null?list.get(i).getService_type_name().replaceAll(" ", "&nbsp;"):"");
//				ja.add(list.get(i).getAbbreviation()!=null?list.get(i).getAbbreviation().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getBaseValue());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getService_type_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getService_type_id()+"'/>Record in Database");	
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
				totalAfterFilter = getTotalServiceTaxRecord();
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


	public int getTotalServiceTaxRecord() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Criteria criteria = session.createCriteria(ServiceType.class);
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(ServiceTypeTaxModel.class);
		}else{
			 criteria = session.createCriteria(ServiceTypeTaxModel.class);
		     criteria.add(Restrictions.eq("deletedStatus", 0));
		}

		List<Integer> list = criteria.list();
		int cnt = list.size();
		return cnt;
	}
	
	public int getTotalServiceTypeRecords() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Criteria criteria = session.createCriteria(ServiceType.class);
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(ServiceType.class);
		}else{
			 criteria = session.createCriteria(ServiceType.class);
		     criteria.add(Restrictions.eq("deletedStatus", 0));
		}

		List<Integer> list = criteria.list();
		int cnt = list.size();
		return cnt;
	}
	public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,String cols ,String dir) {
		
		Session session;
		int cnt=0;
		try{String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				//Criteria criteria = session.createCriteria(ServiceType.class);
				//criteria.add(Restrictions.eq("deletedStatus", 0));
				Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(ServiceType.class);
				}else{
					 criteria = session.createCriteria(ServiceType.class);
				     criteria.add(Restrictions.eq("deletedStatus", 0));
				}

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
						conditionGroup.add(Restrictions.eq("service_type_id", Integer.parseInt(search_term)));
					}catch(Exception e){		}
					conditionGroup.add(Restrictions.like("service_type_name", search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
					
					criteria.add(conditionGroup);
				}
				List<ServiceType> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}
	
	
	

}