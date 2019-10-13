package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
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

import com.trimax.its.common.Common;
import com.trimax.its.device.model.Battery;
import com.trimax.its.device.model.Device;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleCategory;

public class VehicleCategoryDAO {
	Common common = new Common();

	public boolean isVehicleCategoryNew(VehicleCategory vehicleCategoryObject,String reqType) {
		boolean isNew = false;

		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String query = "select count(*)as count from vehicle_category where vehicle_category_name='"+ vehicleCategoryObject.getVehicleCategoryName().trim()+"' and deleted_status=0 ";
			
			if(reqType.equals("update")){
				
				query = "select count(*)as count from vehicle_category where vehicle_category_name='"+ vehicleCategoryObject.getVehicleCategoryName().trim()+"' and 	make_type_id	 not in ('"+vehicleCategoryObject.getVehicleCategoryId()+"') ";
			}
			Query queryObject = session.createSQLQuery(query).addScalar("count", Hibernate.INTEGER);
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
	
	public boolean saveVehicleCategoryType(VehicleCategory vehilceCategory)
	{
		boolean isSuccess = false;
		MakeType MakeTypeData = null;
		Session session = null;
		Transaction transaction = null;
		try
		{
			session = HibernateUtil.getSession("hibenate.cfg.xml");
			vehilceCategory.setVehicleCategoryName(vehilceCategory.getVehicleCategoryName().trim());
			vehilceCategory.setRemarks(vehilceCategory.getRemarks().trim());
			vehilceCategory.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			vehilceCategory.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			vehilceCategory.setUpdatedBy(0);
			
			Integer id = (Integer) session.save(vehilceCategory);
			System.out.println("vehilce category create success....");
			ServletActionContext.getRequest().setAttribute("createdVehicleCategoryId", id);
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
	
	public boolean deleteVehilceCategory(int vehiCatId){
	
		boolean isSuccess = false;
		Session session = null;
		Transaction transaction = null;
		try{
			
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			
			VehicleCategory vehicleCategoryObject =  (VehicleCategory) session.get(VehicleCategory.class,vehiCatId);
			vehicleCategoryObject.setDeletedStatus(1);
			session.save(vehicleCategoryObject);
			
			session.getTransaction().commit();
			System.out.println("Vehicle cat deleted successfully");
			isSuccess = true;
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return isSuccess;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getVehicleCategoryList(int total,	HttpServletRequest request, String colIndex, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();

		Session session = null;
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String[] dbcol={"","vehicleCategoryId","vehicleCategoryName","remarks"};
			String col = dbcol[Integer.parseInt(colIndex)];
			
			//Criteria criteria = session.createCriteria(VehicleCategory.class);
			//criteria.add(Restrictions.eq("deletedStatus", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(VehicleCategory.class);
			}else{
				 criteria = session.createCriteria(VehicleCategory.class);
			     criteria.add(Restrictions.eq("deletedStatus", 0));
			}
					
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			String search_term = request.getParameter("sSearch").trim();
			if (search_term != null && !search_term.equals("")) {

				Junction conditionGroup = Restrictions.disjunction();
				try{
				conditionGroup.add(Restrictions.eq("vehicleCategoryId",Integer.parseInt(search_term)));
				}catch(Exception e){
					
				}
				conditionGroup.add(Restrictions.like("vehicleCategoryName",search_term,MatchMode.ANYWHERE));
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			
			List<VehicleCategory> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ list.get(i).getVehicleCategoryId()+ "' value='"+ list.get(i).getVehicleCategoryId() + "'/>");
				ja.add(list.get(i).getVehicleCategoryId());
				ja.add(list.get(i).getVehicleCategoryName());
				ja.add(list.get(i).getRemarks());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeletedStatus();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						//ja.add(" ");
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getVehicleCategoryId()+"'/>Deleted Record");	

					}else{
						//ja.add(" ");
						//ja.add("Record in Database");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getVehicleCategoryId()+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
			}
			if (search_term != null && !search_term.equals("")) {
				totalAfterFilter =getTotalRecordsForSeacrch(total,request,col,dir,viewdeletedrecord);

				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}else{

				totalAfterFilter = getTotalVehicleCategoryRecords();
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

	public int getTotalVehicleCategoryRecords()
	{       HttpServletRequest request = ServletActionContext.getRequest();  
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");   
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			/*Criteria criteria = session.createCriteria(VehicleCategory.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));*/
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(VehicleCategory.class);
			}else{
				 criteria = session.createCriteria(VehicleCategory.class);
			     criteria.add(Restrictions.eq("deletedStatus", 0));
			}

			List<Integer> list = criteria.list();
			int cnt = list.size();
			return cnt;
		
	}
	
	public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		
	Session session;
	int cnt=0;
	try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
			/*Criteria criteria = session.createCriteria(VehicleCategory.class);
			criteria.add(Restrictions.eq("deletedStatus", 0));*/
		 Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(VehicleCategory.class);
			}else{
				 criteria = session.createCriteria(VehicleCategory.class);
			     criteria.add(Restrictions.eq("deletedStatus", 0));
			}
			
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				Junction conditionGroup = Restrictions.disjunction();
				try{
					conditionGroup.add(Restrictions.eq("vehicleCategoryId",Integer.parseInt(search_term)));
					}catch(Exception e){
						
					}
				conditionGroup.add(Restrictions.like("vehicleCategoryName",search_term,MatchMode.ANYWHERE));
				criteria.add(conditionGroup);
			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			List<Device> list = criteria.list();
			cnt = list.size();
		}catch(Exception e){
			e.printStackTrace();
	}
	return cnt;
}
}
