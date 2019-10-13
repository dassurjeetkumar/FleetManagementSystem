package com.trimax.its.vts.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;


public class VehiclewiseStatusreportDAO {
	
	
	public JSONObject getCrewAllocationRecords1(String scheduleNo, String selectedDate){
		JSONObject result = new JSONObject();
			String sql = "select duty_type_id,VEHICLE_NO  from waybill_trip_details where SCHEDULE_NO = '"+scheduleNo+"' and DUTY_DT = '"+selectedDate+"' group by duty_type_id,VEHICLE_NO";
			String sql1="SELECT `vehicle_id`, `license_number` FROM `vehicle`";
			Session session = null;
			try{
				session = new HibernateUtilVtu().getSession("");
				//session = HibernateUtil.getSession("hibernate-vts.cfg.xml");
				Query query = session.createSQLQuery(sql);
				Query query1 = session.createSQLQuery(sql1);
				
				List list = query.list();
				List list1 = query1.list();
				String vehicleSelect = "<option value='0'>Select</option>";
				for (Iterator i = list1.iterator(); i.hasNext();) {
					Object[] obj = (Object[]) i.next();
					vehicleSelect += "<option value='"+obj[1].toString().trim()+"'>"+obj[1].toString().trim()+"</option>";
				}
				int cnt=0;
				JSONArray array = new JSONArray();
				for (Iterator i = list.iterator(); i.hasNext();) {
					cnt = cnt+1;
					JSONArray ja = new JSONArray();
					Object[] obj = (Object[]) i.next();
					ja.add(""+cnt);
					ja.add(obj[0].toString().trim());
					ja.add(obj[1].toString().trim());
					ja.add("<select id='vehicles"+cnt+"'>"+vehicleSelect+"</select>");
					ja.add("<input type='button' name='submit' value='Submit' onclick=\"changeVehicle("+cnt+","+obj[0].toString().trim()+",'"+obj[1].toString().trim()+"')\"/>");
					array.add(ja);
					
				}
				result.put("aaData", array);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return result;
			
		}
	
	public int updateVehicle(String vehicleNo,String dutyType,String scheduleNo,String oldvehicleNo){
		int cnt = 0;
		Session session = null;
		String sql = "update waybill_trip_details set VEHICLE_NO = '"+vehicleNo+"' where SCHEDULE_NO = '"+scheduleNo+"' and duty_type_id = '"+dutyType+"' and VEHICLE_NO = '"+oldvehicleNo+"'";
		try{
		session = new HibernateUtilVtu().getSession("");
		session.beginTransaction();
		Query query = session.createSQLQuery(sql);
		cnt = query.executeUpdate();
		session.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return cnt;
	}
	
}
