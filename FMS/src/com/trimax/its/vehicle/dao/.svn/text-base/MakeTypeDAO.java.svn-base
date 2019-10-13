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
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.VehicleType;

public class MakeTypeDAO {
	public List<MakeType> getMakeTypeList()
	{
		List<MakeType> makeTypeList = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(MakeType.class);
			
			criteria.add(Restrictions.eq("deletedStatus",0));
			List<Object> resultSetArray = criteria.list();
			makeTypeList = new ArrayList<MakeType>();
			for(int i=0;i<resultSetArray.size();i++)
			{
				
				Object result = resultSetArray.get(i);
				MakeType MakeType  = (MakeType) result;
				makeTypeList.add(MakeType);
				
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
			return makeTypeList;
		}
	}
	
	public MakeType getMakeTypeDetails(int modelTypeId)
	{
		
		MakeType modelTypeData = null;
		Session session = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			Criteria criteria = session.createCriteria(MakeType.class);
			criteria.add(Restrictions.eq("make_type_id",modelTypeId));
			List<Object> resultSetArray = criteria.list();
			if(resultSetArray.size()>0){
				Object result = resultSetArray.get(0);
				modelTypeData  = (MakeType) result;
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
	
	public boolean saveEditedDetails(MakeType makeTypeObject)
	{
		boolean isSuccess = false;
		MakeType MakeTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session =  HibernateUtil.getSession("hibenate.cfg.xml");
			MakeType makeTypeObjectFromSession = (MakeType) session.get(MakeType.class, makeTypeObject.getMake_type_id());
			makeTypeObjectFromSession.setMake_type_name(makeTypeObject.getMake_type_name());
			makeTypeObjectFromSession.setNote(makeTypeObject.getNote());
			makeTypeObjectFromSession.setStatus(makeTypeObject.getStatus());
			makeTypeObjectFromSession.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			makeTypeObjectFromSession.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			transaction = session.beginTransaction();
			session.update(makeTypeObjectFromSession);
			System.out.println("MakeType edited success....");
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
	
	public boolean saveMakeType(MakeType makeTypeObject)
	{
		boolean isSuccess = false;
		MakeType MakeTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			makeTypeObject.setMake_type_name(makeTypeObject.getMake_type_name().trim());
			makeTypeObject.setNote(makeTypeObject.getNote().trim());
			makeTypeObject.setStatus(makeTypeObject.getStatus());
			makeTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			makeTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			makeTypeObject.setUpdatedBy(0);
			
			Integer id = (Integer) session.save(makeTypeObject);
			System.out.println("MakeType edited success....");
			ServletActionContext.getRequest().setAttribute("createdMakeTypeId", id);
			isSuccess = true;
			
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
	
	public String deleteMakeType(int MakeTypeId)
	{
		boolean isSuccess = false;
		String status="error";
		MakeType MakeTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			
			
			DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"make_type",MakeTypeId);
			if(status.trim().equalsIgnoreCase("")){
			MakeType makeTypeObject =  (MakeType) session.get(MakeType.class,MakeTypeId);
			
			makeTypeObject.setDeletedStatus(1);
			makeTypeObject.setUpdatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			makeTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));

			transaction = session.beginTransaction();
			session.update(makeTypeObject);
			session.getTransaction().commit();
			isSuccess = true;
			
			System.out.println("MakeType delete success....");
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
	public boolean isMakeTypeNew(MakeType makeTypeObject,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from make_type where make_type_name='"+ makeTypeObject.getMake_type_name().trim()+"' and deleted_status='0'";
			if(reqType.equals("create")){
			 
			}
			else if(reqType.equals("update"))
			{
				query = "select count(*)as count from make_type where make_type_name='"+ makeTypeObject.getMake_type_name().trim()+"' and 	make_type_id	 not in ('"+makeTypeObject.getMake_type_id()+"') and deleted_status='0' ";
				
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
	public JSONObject getMakeTypeData(int total,HttpServletRequest request, int index, String dir,String viewdeletedrecord) {
		
		JSONObject result = new JSONObject();
		Session session = null;
		
		String[] dbcol={"","make_type_id","make_type_name","note","status"};
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
		//	Criteria criteria = session.createCriteria(MakeType.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(MakeType.class);
			}else{
				 criteria = session.createCriteria(MakeType.class);
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
					conditionGroup.add(Restrictions.eq("make_type_id", Integer.parseInt(search_term)));
				}catch(Exception e){		}
				conditionGroup.add(Restrictions.like("make_type_name", search_term, MatchMode.ANYWHERE));
				conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
				
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<MakeType> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				
				ja.add( "<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='" + list.get(i).getMake_type_id() + "' value='" + list.get(i).getMake_type_id()+ "'/>");
				ja.add(list.get(i).getMake_type_id());
				ja.add(list.get(i).getMake_type_name().replace(" ", "&nbsp;"));
				ja.add(list.get(i).getNote());
				ja.add(list.get(i).getStatus());
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getMake_type_id()+"'/>Deleted Record");	

						//ja.add("Deleted Record");	
						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getMake_type_id()+"'/>Record in Database1");	

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
				totalAfterFilter = getTotalMakeTypeRecords();
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
	
	
	public int getTotalMakeTypeRecords() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		//Criteria criteria = session.createCriteria(MakeType.class);
		//criteria.add(Restrictions.eq("deletedStatus", 0));
		Criteria criteria ;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(MakeType.class);
		}else{
			 criteria = session.createCriteria(MakeType.class);
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
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				//Criteria criteria = session.createCriteria(MakeType.class);
				//criteria.add(Restrictions.eq("deletedStatus", 0));
				Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(MakeType.class);
				}else{
					 criteria = session.createCriteria(MakeType.class);
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
						conditionGroup.add(Restrictions.eq("make_type_id", Integer.parseInt(search_term)));
					}catch(Exception e){		}
					conditionGroup.add(Restrictions.like("make_type_name", search_term, MatchMode.ANYWHERE));
					conditionGroup.add(Restrictions.like("status", search_term, MatchMode.START));
					
					criteria.add(conditionGroup);
				}
				List<MakeType> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}

}
