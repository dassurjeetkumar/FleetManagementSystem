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
import com.trimax.its.transport.model.CaseType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleType;

public class VehicleTypeDAO {

	public List getVehicleTypeList()
	{
		List<VehicleType> vehicleTypeList = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria= session.createCriteria(VehicleType.class);
			criteria.add(Restrictions.eq("deletedStatus",0));
			
			List<Object> resultSetArray = criteria.list();
			vehicleTypeList = new ArrayList<VehicleType>();
			for(int i=0;i<resultSetArray.size();i++)
			{
				
				Object result = resultSetArray.get(i);
				VehicleType vehicleType  = (VehicleType) result;
				vehicleTypeList.add(vehicleType);
				
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
			return vehicleTypeList;
		}
	}
	
	public VehicleType getEditVehicleDetails(String vehicleTypeId)
	{
		VehicleType vehicleDetails = null;
		Session session= null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createQuery("from VehicleType where vehicle_type_id =? ");
			query.setParameter(0, Integer.parseInt(vehicleTypeId));
			
			List<Object>  resultSetArray = query.list();
			
				Object result = resultSetArray.get(0);
				vehicleDetails = (VehicleType) result;
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
			return vehicleDetails;
		}
		
	}
	
	public String deleteVehicleType(int vehicleTypeId)
	{
		
		boolean isSucces = false;
		Session session= null;
		String status="error";
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			 DependencyChecker dc=new DependencyChecker();
				status=dc.checkDependentEntities(session,"vehicle_type",vehicleTypeId);
				if(status.trim().equalsIgnoreCase("")){
			transaction = session.beginTransaction();
			VehicleType vehicleTypeObject = (VehicleType) session.get(VehicleType.class,vehicleTypeId);
			vehicleTypeObject.setDeletedStatus(1);
			vehicleTypeObject.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			vehicleTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			
			session.update(vehicleTypeObject);
			session.getTransaction().commit();
			isSucces = true;
				}
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
	
	public boolean saveEditVehicleTypeDetails(VehicleType modifiedVehicleTypeObject)
	{
		boolean returnResult = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			VehicleType vehicleType = (VehicleType) session.get(VehicleType.class, modifiedVehicleTypeObject.getVehicle_type_id());
			
			vehicleType.setVehicle_type_name(modifiedVehicleTypeObject.getVehicle_type_name().trim());
			vehicleType.setNotes(modifiedVehicleTypeObject.getNotes().trim());
			vehicleType.setStatus(modifiedVehicleTypeObject.getStatus().trim());
			vehicleType.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			vehicleType.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			
			session.update(vehicleType);
			System.out.println("vehicleType edit success.... having id---->>>");
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
	
	@SuppressWarnings("finally")
	public boolean saveNewVehicleType(VehicleType modifiedVehicleTypeObject)
	{
		boolean isSeuccess = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			
			modifiedVehicleTypeObject.setVehicle_type_name(modifiedVehicleTypeObject.getVehicle_type_name().trim());
			modifiedVehicleTypeObject.setNotes(modifiedVehicleTypeObject.getNotes().trim());
			modifiedVehicleTypeObject.setStatus(modifiedVehicleTypeObject.getStatus().trim());
			modifiedVehicleTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			modifiedVehicleTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			modifiedVehicleTypeObject.setUpdatedBy(0);
			Integer id = (Integer) session.save(modifiedVehicleTypeObject);
			session.getTransaction().commit();
			System.out.println("vehicleType created success.... having id---->>>"+id);
			ServletActionContext.getRequest().setAttribute("createdVehicleTypeId", id);
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
	public boolean isVehicleTypeNew(VehicleType vehicleTypeObject,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from vehicle_type where vehicle_type_name='"+ vehicleTypeObject.getVehicle_type_name().trim()+"' and deleted_status='0'";
			if(reqType.equals("create")){
			 
			}
			else if(reqType.equals("update"))
			{
				query = "select count(*)as count from vehicle_type where vehicle_type_name='"+ vehicleTypeObject.getVehicle_type_name().trim()+"' and vehicle_type_id not in ('"+vehicleTypeObject.getVehicle_type_id()+"') and deleted_status='0'";
				
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
	public JSONObject getVehicleTypeData(int total,HttpServletRequest request, int index, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","vehicle_type_id","vehicle_type_name","notes","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(VehicleType.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(VehicleType.class);
			}else{
				 criteria = session.createCriteria(VehicleType.class);
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
					conditionGroup.add(Restrictions.eq("vehicle_type_id", Integer.parseInt(search_term)));
				}catch(Exception e){		}
				conditionGroup.add(Restrictions.like("vehicle_type_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<VehicleType> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getVehicle_type_id() + "' value='" + list.get(i).getVehicle_type_id()+ "'/>");
				ja.add(list.get(i).getVehicle_type_id());
				ja.add(list.get(i).getVehicle_type_name().replace(" ", "&nbsp;"));
				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						//ja.add(" ");
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getVehicle_type_id()+"'/>Deleted Record");	

					}else{
						//ja.add(" ");
						//ja.add("Record in Database");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getVehicle_type_id()+"'/>Record in Database");	

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
				totalAfterFilter = getTotalVehicleTypeRecords();
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
	
	
	public int getTotalVehicleTypeRecords() {

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//Criteria criteria = session.createCriteria(VehicleType.class);
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		HttpServletRequest request = ServletActionContext.getRequest();
		 
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(VehicleType.class);
		}else{
			 criteria = session.createCriteria(VehicleType.class);
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
//				Criteria criteria = session.createCriteria(VehicleType.class);
//				criteria.add(Restrictions.eq("deletedStatus", 0));
				Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(VehicleType.class);
				}else{
					 criteria = session.createCriteria(VehicleType.class);
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
						conditionGroup.add(Restrictions.eq("vehicle_type_id", Integer.parseInt(search_term)));
					}catch(Exception e){		}
					conditionGroup.add(Restrictions.like("vehicle_type_name", search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
					
					criteria.add(conditionGroup);
				}
				List<VehicleType> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}
	
}
