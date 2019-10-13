package com.trimax.its.vehicle.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.ServiceTaxType;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.ServiceTypeTaxModel;

public class ServiceTaxTypeDAO {

	
	public List<ServiceTaxType> getServiceTypeList()
	{
		List<ServiceTaxType> serviceTypeList = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(ServiceTaxType.class);
			
			criteria.add(Restrictions.eq("deletedStatus",0));
			List<Object> resultSetArray = criteria.list();
			serviceTypeList = new ArrayList<ServiceTaxType>();
			for(int i=0;i<resultSetArray.size();i++)
			{
				
				Object result = resultSetArray.get(i);
				ServiceTaxType serviceType  = (ServiceTaxType) result;
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
		
	
	public ServiceTaxType getServiceTaxTypeDetails(int serviceTaxTypeId)
	{
		
		ServiceTaxType serviceTaxTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(ServiceTaxType.class);
			criteria.add(Restrictions.eq("service_tax_id",serviceTaxTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				serviceTaxTypeData  = (ServiceTaxType) result;
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
			return serviceTaxTypeData;
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
	
	@SuppressWarnings("finally")
	public ServiceTaxType saveEditedDetails(ServiceTaxType servicetaxtype,int sevid,int taxid,int ticketid1)
	{
		Common common = new Common();
		
		Session session = null;
		Transaction transaction = null;
		ServiceTaxType servicetaxTypeObjectFromSession=null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			System.out.println("Integer.parseInt(servicetaxtype.getService_type_name())  "+servicetaxtype.getService_type_name());
			System.out.println(" servicetaxtype.getService_tax_id()"+ servicetaxtype.getService_tax_id());
			servicetaxTypeObjectFromSession = (ServiceTaxType) session.get(ServiceTaxType.class, servicetaxtype.getService_tax_id());
			servicetaxTypeObjectFromSession.setService_type_id(sevid);
			
			servicetaxTypeObjectFromSession.setService_type_name(servicetaxtype.getService_type_name());
			servicetaxTypeObjectFromSession.setTax_type_id(taxid);
			servicetaxTypeObjectFromSession.setTax_type_name(servicetaxtype.getTax_type_name());
			servicetaxTypeObjectFromSession.setTicket_type_id(ticketid1);
			servicetaxTypeObjectFromSession.setTicket_type_name(servicetaxtype.getTicket_type_name());
			servicetaxTypeObjectFromSession.setBase_value(servicetaxtype.getBase_value());
			servicetaxTypeObjectFromSession.setService_tax_percentage(servicetaxtype.getService_tax_percentage());
			
			servicetaxTypeObjectFromSession.setStatus(servicetaxtype.getStatus());
			servicetaxTypeObjectFromSession.setEffective_start_date(common.getDateFromPicker(servicetaxtype.getEffective_start_date()));
			servicetaxTypeObjectFromSession.setEffective_end_date(common.getDateFromPicker(servicetaxtype.getEffective_end_date()));
			servicetaxTypeObjectFromSession.setUpdatedBy(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			servicetaxTypeObjectFromSession.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			transaction = session.beginTransaction();
			session.update(servicetaxTypeObjectFromSession);
			session.getTransaction().commit();

			
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
			return servicetaxTypeObjectFromSession;
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
	
	public boolean  saveserviceTaxType(ServiceTaxType serviceTypeObject)
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
		ServiceTaxType serviceTypeData = null;
		Session session = null;
		Transaction transaction = null;
		
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session, "service_tax", serviceTypeId);
			System.out.println("&&&&&&&&&&"+status);
			//if(status.trim().equalsIgnoreCase("")){
			
				ServiceTaxType serviceTypeObject =  (ServiceTaxType) session.get(ServiceTaxType.class,serviceTypeId);
			serviceTypeObject.setDeletedStatus(1);
			serviceTypeObject.setUpdatedBy(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
				//session.beginTransaction();
				//String Innersql = " UPDATE service_tax set deleted_status='1' WHERE service_tax_id='"+serviceTypeId+"'";
				//Query qry2 = session.createSQLQuery(Innersql);
				//qry2.executeUpdate();
			serviceTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

			transaction = session.beginTransaction();
			session.update(serviceTypeObject);
			session.getTransaction().commit();
			isSuccess  = true;
			
			System.out.println("ServiceType delete success....");
			//}
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
	
	
	public boolean isServiceTypeNew(ServiceTaxType serviceTypeObject,String reqType) {
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
	public JSONObject getServiceTaxTypeData(int total,HttpServletRequest request, int index, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","service_tax_id","tax_type_name","service_type_name","ticket_type_name","base_value","service_tax_percentage","effective_start_date","effective_end_date","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(ServiceType.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(ServiceTaxType.class);
			}else{
				 criteria = session.createCriteria(ServiceTaxType.class);
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
					conditionGroup.add(Restrictions.eq("service_tax_id", Integer.parseInt(search_term)));
				}catch(Exception e){		}
				conditionGroup.add(Restrictions.like("service_type_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("ticket_type_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<ServiceTaxType> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getService_tax_id() + "' value='" + list.get(i).getService_tax_id()+ "'/>");
				ja.add(list.get(i).getService_tax_id());
				ja.add(list.get(i).getTax_type_name()!=null?list.get(i).getTax_type_name().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getService_type_name()!=null?list.get(i).getService_type_name().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getTicket_type_name()!=null?list.get(i).getTicket_type_name().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getBase_value()!=null?list.get(i).getBase_value().replaceAll(" ", "&nbsp;"):"");
				ja.add(list.get(i).getService_tax_percentage());
				ja.add(list.get(i).getEffective_start_date());
				ja.add(list.get(i).getEffective_end_date());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getService_tax_id()+"'/>Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getService_tax_id()+"'/>Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				
				array.add(ja);
			}
       System.out.println("result1===");
			if (search_term != null && !search_term.equals("")) {
				totalAfterFilter =getTotalRecordsForSeacrch(total,request,col,dir);

				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
			else{
				totalAfterFilter = getTotalServiceTaxTypeRecords();
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
	
	public int getTotalServiceTaxTypeRecords() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Criteria criteria = session.createCriteria(ServiceType.class);
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(ServiceTaxType.class);
		}else{
			 criteria = session.createCriteria(ServiceTaxType.class);
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
					 criteria = session.createCriteria(ServiceTaxType.class);
				}else{
					 criteria = session.createCriteria(ServiceTaxType.class);
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
						conditionGroup.add(Restrictions.eq("service_tax_id", Integer.parseInt(search_term)));
					}catch(Exception e){		}
					conditionGroup.add(Restrictions.like("service_type_name", search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("ticket_type_name", search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
					
					criteria.add(conditionGroup);
				}
				List<ServiceTaxType> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}
	
	public Map<Integer, String> getServiceTypeName1() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from ServiceType where status='Active' and deleted_status=0 order by service_type_id");
		
		try{
		List<ServiceType> list = query.list();
		for (ServiceType serviceType : list) {
			resultMap.put(serviceType.getService_type_id(),serviceType.getService_type_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	
	
	public Map<Integer, String> getTaxTypeName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try {
			String sql="SELECT tax_type_id  , tax_type_name  " +
					"FROM tax_type where deleted_status=0";
			Query query4 = session.createSQLQuery(sql)
					.addScalar("tax_type_id", Hibernate.INTEGER)
					.addScalar("tax_type_name", Hibernate.STRING);
			query4.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query4.list();
			//resultMap.put(0, "select");
			System.out.println("Size of denomtype List : "+aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//System.out.println("month is"+rs.get("day_month").toString());
				resultMap.put(Integer.parseInt(rs.get("tax_type_id").toString()),rs.get("tax_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	
	}
	
	public Map<Integer, String> getTicketType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try {
			String sql="SELECT ticket_type_id  , ticket_type_name " +
					"FROM ticket_type where deleted_status=0 and status='ACTIVE'";
			Query query4 = session.createSQLQuery(sql)
					.addScalar("ticket_type_id", Hibernate.INTEGER)
					.addScalar("ticket_type_name", Hibernate.STRING);
			query4.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query4.list();
			//resultMap.put(0, "select");
			System.out.println("Size of denomtype List : "+aliasToValueMapList.size());
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//System.out.println("month is"+rs.get("day_month").toString());
				resultMap.put(Integer.parseInt(rs.get("ticket_type_id").toString()),rs.get("ticket_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	
	}
	
	
	public boolean getDuplicateRecordForUpdate(int id)
	{
		boolean flag=false;
		String qry = "select count(*) from service_tax where  service_tax_id="+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		if(query.list().size()>0){flag=true;}
		return flag;
	}
	
	
	
	
	public ServiceTaxType getServiceTaxTypeDetailsEdit(int serviceTypeID)
	{
		
		  System.out.println("servicetax_id==="+serviceTypeID);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		ServiceTaxType brandTypeData = (ServiceTaxType) session.get(ServiceTaxType.class, new Integer(serviceTypeID));
		brandTypeData.setEffective_start_date(common.getDateToPicker(brandTypeData.getEffective_start_date()));
		brandTypeData.setEffective_end_date(common.getDateToPicker(brandTypeData.getEffective_end_date()));
		return brandTypeData;
		/*BrandType brandTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(BrandType.class);
			criteria.add(Restrictions.eq("brand_type_id",brandTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				brandTypeData  = (BrandType) result;
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
			return brandTypeData;
		}*/
	}
	
}