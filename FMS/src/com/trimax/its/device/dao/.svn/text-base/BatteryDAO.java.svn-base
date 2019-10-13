package com.trimax.its.device.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.device.model.Battery;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.model.Vendor;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.Complaint;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.VehicleFcRenewal;

public class BatteryDAO {
	
	Common common = new Common();
	public List<Device> getBatteryList()
	{
		List<Device> deviceList = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Device.class);
			criteria.createAlias("device", "deviceType");
			criteria.add(Restrictions.like("deviceType.device_type_name", "Battery"));
			criteria.createAlias("vendorid", "vendor");
			
			List<Object> det = criteria.list();
			
			if(det.size()>0){
				deviceList = new ArrayList<Device>();
				for (int i = 0; i < det.size(); i++) {
					{
						Object result = det.get(i);
						Device device = (Device) result;
						//Device_Type devType = (Device_Type) result[1];
						deviceList.add(device);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			
			return deviceList;
		
		}
	}
	
	public int saveNewBattery(Battery batteryObject){
	{
		boolean isSuccess = false;
		Session session = null;
		Transaction transaction = null;
		int id=0;
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = (Transaction) session.beginTransaction();
			//vendorObject.setVendor_name(vendorObject.getVendor_name().trim());
			//batteryObject.setVendorDetails(vendorObject);
			batteryObject.setSerial_number(batteryObject.getSerial_number().trim());
			batteryObject.setCapacity(batteryObject.getCapacity().trim());
			batteryObject.setProcured_date(common.getDateFromDatePicker((batteryObject.getProcuredDateString().trim())));
			batteryObject.setStatus(batteryObject.getStatus().trim());
			batteryObject.setNotes(batteryObject.getNotes().trim());
			batteryObject.setCreated_by(batteryObject.getCreated_by());
			id=(Integer)session.save(batteryObject);
			
			session.getTransaction().commit();
			isSuccess = true;
			
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
			isSuccess = false;
		}finally{
			if(session != null){
				session.close();
			}
			return id;
		}
		
	}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getBatteryAjaxList(int total,	HttpServletRequest request, String colIndex, String dir,String viewdeletedrecord) {
		JSONObject result = new JSONObject();
		Criteria criteria;
		Session session = null;
		try {
			int totalAfterFilter = total;
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String[] dbcol={"","battery_id","vendor.vendor_name","serial_number","capacity","procured_date","status","notes"};
			String col = dbcol[Integer.parseInt(colIndex)];
			
			
			
			if(viewdeletedrecord.equalsIgnoreCase("Y"))
			{
				 criteria = session.createCriteria(Battery.class);
				criteria.createAlias("vendorDetails", "vendor");
			}else{
				 criteria = session.createCriteria(Battery.class);
				criteria.createAlias("vendorDetails", "vendor");
				criteria.add(Restrictions.eq("deleted_status", 0));
						
			}
			
			
			
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
				conditionGroup.add(Restrictions.eq("battery_id", Integer.parseInt(search_term)));
				}catch(Exception e){
				}
				conditionGroup.add(Restrictions.like("vendor.vendor_name", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("serial_number", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("capacity", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("status", "%" + search_term + "%"));
				conditionGroup.add(Restrictions.like("notes", "%" + search_term + "%"));
				criteria.add(conditionGroup);
			}

			criteria.setFirstResult(Integer.parseInt(request.getParameter("iDisplayStart")));
			criteria.setMaxResults(Integer.parseInt(request.getParameter("iDisplayLength")));
			//criteria.add(Restrictions.eq("v.vehicleId",	Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("editVehicleId").toString())));

			List<Battery> list = criteria.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"+ list.get(i).getBattery_id()+ "' value='"+ list.get(i).getBattery_id() + "'/>");
				ja.add(list.get(i).getBattery_id());
				ja.add(list.get(i).getVendorDetails().getVendor_name());
				ja.add(list.get(i).getSerial_number());
				ja.add(list.get(i).getCapacity());
				if(list.get(i).getProcured_date()!=null){
					ja.add(common.getDateToDatePicker(list.get(i).getProcured_date()));
				}
				else{
					ja.add("");
					
				}
				//ja.add(list.get(i).getProcured_date()!=null ? list.get(i).getProcured_date():"");

				ja.add(list.get(i).getStatus());
				ja.add(list.get(i).getNotes());
				
				
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					int deletedstatus=list.get(i).getDeleted_status();
										
					if(deletedstatus==1)
					{
						//ja.add("Deleted Record");	
						ja.add("<input type='hidden' value='N' id='isRocordEligible"+ list.get(i).getBattery_id()+"'/>Deleted Record");	

						//ja.add(" ");
					}else{
						//ja.add(" ");
						//ja.add("Record in Database");	
						ja.add("<input type='hidden' value='Y' id='isRocordEligible"+ list.get(i).getBattery_id()+"'/>Record in Database");	

					}
					
					//ja.add(rs.get("vendor_name").toString());	
				}else{
					
				}
//				ja.add(common.getDateToDatePicker(list.get(i).getFcExpiryDate()));
//				ja.add(common.getDateToDatePicker(list.get(i).getFcRenewalDate()));
				array.add(ja);
			}
			if (search_term != null && !search_term.equals("")) {
				totalAfterFilter =getTotalRecordsForSeacrch(total,request,col,dir,viewdeletedrecord);

				result.put("aaData", array);
				result.put("iTotalRecords", totalAfterFilter);
				result.put("iTotalDisplayRecords", totalAfterFilter);
			}
			else{
				totalAfterFilter = getTotalBatteryRecords();
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
	
	public int getTotalBatteryRecords()
	{
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Criteria criteria = session.createCriteria(Battery.class);
			criteria.createAlias("vendorDetails", "vendor");
			criteria.add(Restrictions.eq("deleted_status", 0));
			//("select count(*) as count from `vehicle_fc_renewal` where vehicle_id='"+ ServletActionContext.getRequest().getSession().getAttribute("editVehicleId") + "' ").addScalar("count", Hibernate.INTEGER);
			
			List<Integer> list = criteria.list();
			int cnt = list.size();
			return cnt;
		
	}
public int getTotalRecordsForSeacrch(int total, HttpServletRequest request,String cols ,String dir,String viewdeletedrecord) {
		
		Session session;
		int cnt=0;
		try{
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			 
			 
			 	Criteria criteria;
				if(viewdeletedrecord.equalsIgnoreCase("Y"))
				{
					 criteria = session.createCriteria(Battery.class);
				}else{
					 criteria = session.createCriteria(Battery.class);
				     criteria.add(Restrictions.eq("deleted_status", 0));
				}
			
			
				String search_term = request.getParameter("sSearch").trim();
				criteria.createCriteria("vendorDetails", "vendor");
				if (!request.getParameter("sSearch").equals("")) {
					Junction conditionGroup = Restrictions.disjunction();
					try{
					conditionGroup.add(Restrictions.eq("battery_id", Integer.parseInt(search_term)));
					}catch(Exception e){
					}
					conditionGroup.add(Restrictions.like("vendor.vendor_name", "%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("serial_number", "%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("capacity", "%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("status", "%" + search_term + "%"));
					conditionGroup.add(Restrictions.like("notes", "%" + search_term + "%"));
					criteria.add(conditionGroup);
				}
				if (!cols.equals("")) {
					if (dir.equals("asc")) {
						criteria.addOrder(Order.asc(cols));
					} else {
						criteria.addOrder(Order.desc(cols));
					}
				}
				List<Battery> list = criteria.list();
				cnt = list.size();
			}catch(Exception e){
				e.printStackTrace();
		}
		return cnt;
	}
	public Map<Integer, String> getVendorIDName() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from Vendor where status='Active' and deleted_status=0 order by vendor_name ");
		
		try{
		List<Vendor> list = query.list();
		for (Vendor vendor : list) {
			resultMap.put(vendor.getId(),vendor.getVendor_name());
		}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return resultMap;
		

	}
	public boolean checkBattery(String serial_number)
	{
		boolean flag=false;
		String qry = " select battery_id from battery where deleted_status=0 and serial_number=? ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setParameter(0, serial_number);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}
	public Battery getEditedBattery(int parseInt) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Battery battery = (Battery) session.get(Battery.class, new Integer(
				parseInt));
		// System.out.println("--------->>>" +
		// fareChart.getFarechart_master_id()
		// + "\t" + fareChart.getRoute_id());
		return battery;
	}
	
	public boolean checkBatteryForUpdate(String batteryname,int id)
	{
		boolean flag=false;
		String qry = " select battery_id from battery where serial_number=? and battery_id=? ";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setParameter(0, batteryname);
		query.setParameter(1, id);
		if(query.list().size()>0)
		{
			flag=true;
		}
		return flag;
	}

	public String  updateBattery(Battery device, int device_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		int id=0;
		try {
			//
			Battery deviceobj = (Battery) session.get(Battery.class, device_id);

			
			deviceobj.setStatus(device.getStatus());
			deviceobj.setUpdated_by(device.getUpdated_by());
			deviceobj.setUpdated_date(new java.util.Date());
			deviceobj.setSerial_number(device.getSerial_number());
			deviceobj.setCapacity(device.getCapacity());
			//deviceobj.setModel(device.getModel());
			//deviceobj.getDevice().setDevice_type_id(device.getDevice().getDevice_type_id());
			deviceobj.setProcured_date(common.getDateFromDatePicker((device.getProcuredDateString())));
			//deviceobj.getVendorid().setId(device.getVendorid().getId());
			//deviceobj.setDevice(device.getDevice());
			deviceobj.setVendorDetails(device.getVendorDetails());
			deviceobj.setNotes(device.getNotes());
			session.update(deviceobj);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
	public String deleteDevice(Battery device, int device_id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		try {
			//
			Battery deviceobj = (Battery) session.get(Battery.class, device_id);

			//deviceobj.setStatus("INACTIVE");
			deviceobj.setDeleted_status(1);
			deviceobj.setUpdated_by(device.getUpdated_by());
			deviceobj.setUpdated_date(new java.util.Date());

			session.update(deviceobj);
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}
}
