package com.trimax.its.vehicle.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleDefects;

import com.trimax.its.vehicle.model.VehicleMaintenance;

public class VehicleMaintenanceDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");

		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 //Criteria criteria = session.createCriteria(VehicleMaintenance.class);
				//criteria.add(Restrictions.eq("deleted_status", 0));
			     Criteria criteria ;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(VehicleMaintenance.class);
				}else{
					 criteria = session.createCriteria(VehicleMaintenance.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					String search_term = request.getParameter("sSearch").trim();
						conditionGroup.add(Restrictions.like("maintenance_status",
								"" + search_term+ "%"));
						conditionGroup.add(Restrictions.like("veh.vehicleRegistrationNumber", ""
								+ search_term + "%"));
					
					
					criteria.add(conditionGroup);
					
				}
				criteria.createCriteria("vehicle", "veh");
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				
						List<VehicleMaintenance> list = criteria.list();
			cnt = list.size();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return cnt;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from VehicleMaintenance ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			//Criteria criteria = session.createCriteria(VehicleMaintenance.class);
			//criteria.add(Restrictions.eq("deleted_status", 0));
			Criteria criteria ;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(VehicleMaintenance.class);
			}else{
				 criteria = session.createCriteria(VehicleMaintenance.class);
			     criteria.add(Restrictions.eq("deleted_status", 0));
			}
			if (!cols.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(cols));
				} else {
					criteria.addOrder(Order.desc(cols));
				}
			}
			if (!request.getParameter("sSearch").equals("")) {
				Junction conditionGroup = Restrictions.disjunction();
				String search_term = request.getParameter("sSearch").trim();
				
					
					conditionGroup.add(Restrictions.like("maintenance_status",
							"" + search_term+ "%"));
				
					
					conditionGroup.add(Restrictions.like("veh.vehicleRegistrationNumber",
							"" + search_term+ "%"));
				criteria.add(conditionGroup);
				
			}
			criteria.createCriteria("vehicle", "veh");
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			
			List<VehicleMaintenance> list = criteria.list();
			JSONArray array = new JSONArray();

			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getVehicle_maintaince_id()
						+ "' value='"
						+ list.get(i).getVehicle_maintaince_id() + "'/>");
				ja.add(list.get(i).getVehicle_maintaince_id());
				ja.add(common.getDateToDatePicker(list.get(i).getMaintainance_date()));
				ja.add(list.get(i).getMaintenance_status());
				// ja.add("1");toUpperCase()
				ja.add(list.get(i).getVehicle().getVehicleId());
				ja.add(list.get(i).getVehicle().getVehicleRegistrationNumber());
//				
				ja.add(list.get(i).getDescription());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getVehicle_maintaince_id()+"'/>Deleted Record");	

						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getVehicle_maintaince_id()+"'/>Record in Database1");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}		
				array.add(ja);
				//System.out.println("Array----->" + array);
			}

			totalAfterFilter = total;//getTotalRecords(total,request,cols,dir);
			result.put("aaData", array);
			result.put("iTotalRecords", total);

			result.put("iTotalDisplayRecords", totalAfterFilter);

//			System.out.println("REsult ------>"
//					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}
		return result;
	}
	
	
	public Map<Integer, String> getVehicleList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Vehicle where status='Active' and deleted_status=0");
		
		try{
		List<Vehicle> list = query.list();
		
		for (Vehicle vehicle : list) {
			resultMap.put(vehicle.getVehicleId(), vehicle.getVehicleRegistrationNumber());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return resultMap;
		

	}
	public int saveVehicleMaintenance(VehicleMaintenance maintence)
	{
		int id=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			
			session.beginTransaction();
			id=(Integer)session.save(maintence);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		return id;
	}
	public String deletevehiclemaintenance(VehicleMaintenance maintenance, int maintenanceid) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		VehicleMaintenance vehiclemaintenance = (VehicleMaintenance) session.get(VehicleMaintenance.class,maintenanceid);
		
		//devicetype.setStatus("INACTIVE");
		vehiclemaintenance.setDeleted_status(1);
		vehiclemaintenance.setUpdated_by(maintenance.getUpdated_by());
		vehiclemaintenance.setUpdated_date(new java.util.Date());
		
		session.update(vehiclemaintenance);
		session.getTransaction().commit();
	}catch(Exception ex)
	{
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return null;
	}
	public VehicleMaintenance getEditedVehicleMaintenance(int id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		VehicleMaintenance maintenance = (VehicleMaintenance) session.get(VehicleMaintenance.class, new Integer(
				id));

		return maintenance;
	}
	
	public int updateVehicleMaintenance(int id,VehicleMaintenance user){
		boolean flag=false;
		Common common = new Common();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		try{
		
		VehicleMaintenance user1=(VehicleMaintenance)session.load(VehicleMaintenance.class,new Integer(id));
		HttpServletRequest request=ServletActionContext.getRequest();
		user1.setDescription(user.getDescription());
		user1.setMaintenance_status(user.getMaintenance_status());
		user1.setVehicle(user.getVehicle());
		user1.setMaintainance_date(common.getDateFromDatePicker(user.getMaintenancedate()));
		user1.setUpdated_by(user1.getUpdated_by());
		user1.setUpdated_date(new java.util.Date());
		session.update(user1);
		session.getTransaction().commit();		
		i=1;
		}
		catch(Exception ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
		}
		finally{
			if (session != null) {
				session.close();
			}
		}

		return i;
}
	
	public boolean getDuplicate(int vehid,String date)
	{
		boolean flag=false;
		String qry = " select vehicle_id from vehicle_maintence where deleted_status=0 and vehicle_id="+vehid+" and maintenance_status='ACTIVE' and  DATE_FORMAT(maintainance_date, '%d-%m-%Y')='"+date+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
//		System.out.println("-----"+qry);
//		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public boolean getUpdateDuplicate(int vehid,String date, int id)
	{
		boolean flag=false;
		String qry = " select vehicle_id from vehicle_maintence where deleted_status=0 and vehicle_id="+vehid+"  and  DATE_FORMAT(maintainance_date, '%d-%m-%Y')='"+date+"' and vehicle_maintaince_id="+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
//		System.out.println("-----"+qry);
//		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
}
