package com.trimax.its.device.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.model.Role;
import com.trimax.its.vehicle.model.Complaint;

public class DeviceTypeDao {
	public int getTotalRecords(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		/*Query query = session.createSQLQuery(
				"select count(*) as count from device_type where deleted_status=0 ").addScalar(
				"count", Hibernate.INTEGER);
		List<Integer> list = query.list();
		int cnt = list.get(0);
		System.out.println(cnt);*/
		Criteria criteria;
		if(viewdeletedrecord.equalsIgnoreCase("Y"))
		{
			 criteria = session.createCriteria(Device_Type.class);
		}else{
			criteria = session.createCriteria(Device_Type.class);
		 criteria.add(Restrictions.eq("deleted_status", 0));
		 
		}
		if (!request.getParameter("sSearch").equals("")) {
			String search_term = request.getParameter("sSearch").trim();

			Junction conditionGroup = Restrictions.disjunction();

			conditionGroup.add(Restrictions.like("device_type_name", "%"
					+ search_term + "%"));
			conditionGroup.add(Restrictions.like("status", search_term,MatchMode.START));
							criteria.add(conditionGroup);
			// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
			// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
		}
		if (!cols.equals("")) {
			if (dir.equals("asc")) {
				criteria.addOrder(Order.asc(cols));
			} else {
				criteria.addOrder(Order.desc(cols));
			}
		}
		
		List<Device_Type> list = criteria.list();
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,String col,String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		try {
			int totalAfterFilter = total;

			// JSONArray array = new JSONArray();
			String searchSQL = "";
			String sql = " from Device_Type  ";

			// sql += " order by " + COL_NAME + " " + DIR;

			sql += " limit " + request.getParameter("iDisplayStart") + ", "
					+ request.getParameter("iDisplayLength");
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			
			Criteria criteria;
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				System.out.println("&&&&&&&&&&&&&&&&&&"+viewdeletedrecord);
				 criteria = session.createCriteria(Device_Type.class);
			}else{
				System.out.println("&&&&&&&&&&&&&&&&&&111111111"+viewdeletedrecord);
				criteria = session.createCriteria(Device_Type.class);
			 criteria.add(Restrictions.eq("deleted_status", 0));
			 
			}
			 
			if (!request.getParameter("sSearch").equals("")) {
				String search_term = request.getParameter("sSearch").trim();

				Junction conditionGroup = Restrictions.disjunction();

				conditionGroup.add(Restrictions.like("device_type_name", "%"
						+ search_term + "%"));
				conditionGroup.add(Restrictions.like("status",  search_term,MatchMode.START));
								criteria.add(conditionGroup);
				// criteria.add(Restrictions.or(Restrictions.like("busStopName","%"+search_term+"%"
				// ), Restrictions.like("bus_stop_code","%"+search_term+"%" )));
			}
			if (!col.equals("")) {
				if (dir.equals("asc")) {
					criteria.addOrder(Order.asc(col));
				} else {
					criteria.addOrder(Order.desc(col));
				}
			}
			criteria.setFirstResult(Integer.parseInt(request
					.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request
					.getParameter("iDisplayLength")));
			List<Device_Type> list = criteria.list();
			JSONArray array = new JSONArray();
			System.out.println("List size----->" + list.size() + "\t"
					+ request.getParameter("iDisplayStart"));
			for (int i = 0; i < list.size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
						+ list.get(i).getDevice_type_id()
						+ "' value='"
						+ list.get(i).getDevice_type_id() + "'/>");
				ja.add(list.get(i).getDevice_type_id());
				ja.add(list.get(i).getDevice_type_name());
//				ja.add(list.get(i).getNote());

				ja.add(list.get(i).getNotes());
				ja.add(list.get(i).getStatus());
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getDevice_type_id()+"'/>Deleted Record");	

						//ja.add(" ");
					}else{
						//ja.add(" ");
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getDevice_type_id()+"'/>Record in Database");	

						//ja.add("Record in Database");	
					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
				array.add(ja);
				System.out.println("Array----->" + array);
			}
			
			totalAfterFilter = getTotalRecords(total,request,col,dir,viewdeletedrecord);
			result.put("aaData", array);
			result.put("iTotalRecords", totalAfterFilter);

			result.put("iTotalDisplayRecords", totalAfterFilter);
			
			System.out.println("REsult ------>"
					+ request.getParameter("iDisplayStart"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	public int insertDeviceType(Device_Type deviceType) {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int i=0;
		System.out.println("000000000000000000");
		try{
		 i = (Integer) session.save(deviceType);
		session.getTransaction().commit();
		}catch(Exception e)
		{
			
		}finally{
		session.close();
		}
		return i;

	}
	public Device_Type getEditedDeviceType(int id) {
		// BusStops busstops = new BusStops();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Device_Type devicetype = (Device_Type) session.get(
				Device_Type.class, new Integer(id));
		//System.out.println("--------->>>" + fareChart.getFarechart_master_id()
			//	+ "\t" + fareChart.getRoute_id());
		return devicetype;

	}
	public String updateDeviceType(Device_Type deviceType, int device_type_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	try{
		//
		Device_Type devicetype = (Device_Type) session.get(Device_Type.class,device_type_id);
		devicetype.setDevice_type_name(deviceType.getDevice_type_name());
		devicetype.setNotes(deviceType.getNotes());
		devicetype.setStatus(deviceType.getStatus());
		devicetype.setUpdated_date(new java.util.Date());
		// Code to Update FareChartMaster...
		session.update(devicetype);
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
	public String deleteDeviceType(Device_Type deviceType, int device_type_id) {
		// TODO Auto-generated method stub
		Session session=null;
		String status="error";
		try{
		 session = HibernateUtil.getSession("hibernate.cfg.xml");
		 DependencyChecker dc=new DependencyChecker();
			status=dc.checkDependentEntities(session,"device_type",device_type_id);
			System.out.println("&&&&&&&&&&"+status);
			if(status.trim().equalsIgnoreCase("")){
				session.beginTransaction();
	//System.out.println("=====Route id" + role.getRole_id());
	
		//
		Device_Type devicetype = (Device_Type) session.get(Device_Type.class,device_type_id);
		
		devicetype.setStatus("INACTIVE");
		devicetype.setDeleted_status(1);
		devicetype.setUpdated_by(deviceType.getUpdated_by());
		devicetype.setUpdated_date(new java.util.Date());
		
		session.update(devicetype);
		session.getTransaction().commit();
	}
	}catch(Exception ex)
	{
		status="error:";
		session.getTransaction().rollback();
		ex.printStackTrace();
	}finally{
		session.close();
	}
		
		return status;
	}
	
	public boolean checkDeviceType(String devicename)
	{
		boolean flag=false;
		String qry = " select device_type_name from device_type where device_type_name='"+devicename+"' and deleted_status= 0 ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public boolean checkDeviceTypeForUpdate(String devicename,int id)
	{
		boolean flag=false;
		String qry = " select device_type_name from device_type where device_type_name='"+devicename+"' and device_type_id= "+id;
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		
		System.out.println("QUery Size===>"+query.list().size());
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	
}
