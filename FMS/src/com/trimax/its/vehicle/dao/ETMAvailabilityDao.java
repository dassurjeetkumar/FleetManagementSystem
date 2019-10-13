package com.trimax.its.vehicle.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


import com.sun.beans.decoder.ValueObject;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.model.Device;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.ScheduleMap;

public class ETMAvailabilityDao {

	
	public int getTotalETMList(HttpServletRequest request,String col,String dir, String orgchartid) {
		System.out.println("getTotalScheduleMappingRecords-----");
		int cnt=0;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			 
			
			sql="select  IFNULL(oc.org_name,'') as org_name,IFNULL(d.device_serial_number,'') as Etm_Serial_number ,occ.org_name as divisionName, IFNULL(sc.phone_number,'') as sim_number, IFNULL(sc.serial_number,'') as sim_serial_number from device d left join device_org do on d.device_id=do.device_id inner join org_chart oc on do.org_id=oc.org_chart_id inner join org_chart occ on oc.parent_id=occ.org_chart_id left join sim_vtu sv on d.device_id=sv.device_id  inner join simcard sc on sv.sim_id=sc.simcard_id  where device_type_id=2 and d.status='ACTIVE' and d.deleted_status=0 and do.status='Active'  and oc.deleted_status=0 and sv.status='active' and sc.deleted_status=0  ";
			
			String search_term1 = request.getParameter("sSearch");
			if (search_term1 != null && !search_term1.equals("")) {
				String search_term = request.getParameter("sSearch").trim();
				sql += " and (occ.org_name like '%"+search_term+"%'";
				sql += " OR oc.org_name like '%"+search_term+"%'";
//				sql += " OR depot_name like '%"+search_term+"%'";
				sql += " OR d.device_serial_number like '%"+search_term+"%'";
				sql += " OR sc.phone_number like '%"+search_term+"%' ";
				sql += " OR sc.serial_number like '%"+search_term+"%') ";
			}
			sql+="group by d.device_serial_number order by org_name ";
			System.out.println("%%%%%"+sql );
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			 cnt =	aliasToValueMapList.size();
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	
}
	
	
	public JSONObject getAvailableEtmList(int total, HttpServletRequest request,String col,String dir, String orgchartid){
		System.out.println("in records1111111111111111111111");
		System.out.println("id is--------"+orgchartid);
		int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			
			sql="select  IFNULL(oc.org_name,'') as org_name,IFNULL(d.device_serial_number,'') as Etm_Serial_number ,occ.org_name as divisionName, IFNULL(sc.phone_number,'') as sim_number, IFNULL(sc.serial_number,'') as sim_serial_number from device d left join device_org do on d.device_id=do.device_id inner join org_chart oc on do.org_id=oc.org_chart_id inner join org_chart occ on oc.parent_id=occ.org_chart_id left join sim_vtu sv on d.device_id=sv.device_id  inner join simcard sc on sv.sim_id=sc.simcard_id  where device_type_id=2 and d.status='ACTIVE' and d.deleted_status=0 and do.status='Active'  and oc.deleted_status=0 and sv.status='active' and sc.deleted_status=0 ";
			
			String search_term = request.getParameter("sSearch");
			if (search_term != null && !search_term.equals("")) {
				search_term=search_term.trim();
				System.out.println("term is=="+search_term);

				sql += " and (occ.org_name like '%"+search_term+"%'";
				sql += "OR oc.org_name like '%"+search_term+"%'";
//				sql += " OR depot_name like '%"+search_term+"%'";
				sql += " OR d.device_serial_number like '%"+search_term+"%'";
				sql += " OR sc.phone_number like '%"+search_term+"%' ";
				sql += " OR sc.serial_number like '%"+search_term+"%' )";
			}
			sql+=" group by d.device_serial_number order by org_name";
			
//			if(!col.equals("")){
//				if(dir.equals("asc")){
//				  sql += " order by "+col+" asc";
//				}else{
//					sql += " order by "+col+" desc";
//				}
//			}
			
			if(!request.getParameter("iDisplayLength").equals("-1")){
			sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			}
		    System.out.println("query........."+sql);
		  	 Query query = session.createSQLQuery(sql)
		  			.addScalar("org_name", Hibernate.STRING)
						 .addScalar("divisionName", Hibernate.STRING)
						 .addScalar("Etm_Serial_number", Hibernate.STRING)
						 .addScalar("sim_number", Hibernate.STRING)
		  	 			 .addScalar("sim_serial_number",Hibernate.STRING);
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("sim_number")+ "' value='"+ rs.get("sim_number") + "'/>");
				ja.add(rs.get("org_name").toString());
					ja.add(rs.get("divisionName").toString());
					ja.add(rs.get("Etm_Serial_number").toString());
					ja.add(rs.get("sim_number").toString());
					ja.add(rs.get("sim_serial_number").toString());
					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				int cnt=0;
				totalAfterFilter = aliasToValueMapList.size();
				result.put("aaData", array);
				String search_term2= request.getParameter("sSearch");
				

				String search_term3= request.getParameter("sSearch").trim();
			 cnt=getTotalETMList(request,col,dir,orgchartid);//getTotalRecordsForCount(search_term3);
		
			result.put("iTotalRecords",cnt);
			
			result.put("iTotalDisplayRecords", cnt);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataforActiveVehicle(
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String limit){
		System.out.println("in records1111111111111111111111");
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "B.depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "B.depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			
			sql="select B.lICENCE_NUMBER lICENCE_NUMBER,B.DEVICE_ID,Schedule_number,org_name,IST_DATE,B.UPDATED_DATE,duty_type_id,distanceTravelled,INTERNAL_BATTERY_VTG," +
					" FIRMWARE_VERSION,EXT_BATTERY_VOLTAGE,EXT_BATTERY_VOLTAGE,SIGNAL_STRENGTH,IGNITION_STATUS,SPEED,vendor_name,shift_type_name from summaryDashboard_activeVehicle B  " +
					" left join device vd   on B.DEVICE_ID=vd.device_serial_number left join vendor v " +
					" on vd.vendor_id=v.vendor_id" +
					" left join shift_type st on B.duty_type_id=st.shift_type_id where "+id+" group by B.DEVICE_ID order by org_name ";
			
			
			
		    System.out.println("query........."+sql);
		  	 Query query = session.createSQLQuery(sql)
		  			.addScalar("lICENCE_NUMBER", Hibernate.STRING)
						 .addScalar("DEVICE_ID", Hibernate.STRING)
						 .addScalar("Schedule_number", Hibernate.STRING)
						 .addScalar("org_name", Hibernate.STRING)
		  	 			 .addScalar("IST_DATE",Hibernate.STRING)
		  	 			 .addScalar("UPDATED_DATE",Hibernate.STRING)
		  	 			 .addScalar("duty_type_id",Hibernate.STRING)
		  	 			 .addScalar("distanceTravelled",Hibernate.STRING)
		  	 			 .addScalar("INTERNAL_BATTERY_VTG",Hibernate.STRING)
		  	 			 .addScalar("FIRMWARE_VERSION",Hibernate.STRING)
		  	 			 .addScalar("EXT_BATTERY_VOLTAGE",Hibernate.STRING)
		  	 			 .addScalar("SIGNAL_STRENGTH",Hibernate.STRING)
		  	 			 .addScalar("IGNITION_STATUS",Hibernate.STRING)
		  	 			 .addScalar("SPEED",Hibernate.STRING)
		  	 			 .addScalar("vendor_name",Hibernate.STRING)
		  	 			  .addScalar("shift_type_name",Hibernate.STRING)
		  	 			 ;
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					
					JSONArray ja = new JSONArray();
					ja.add(i+1);
					ja.add("<img src='assets/images/bus-map-icon.png'  title='TrackOnline' onclick=javascript:initializea('"
							+ rs.get("DEVICE_ID")
							+ "','"
							+ rs.get("lICENCE_NUMBER")
							+ "','"
							+ rs.get("Schedule_number").toString().replace(" ", "")
							+ "','"
							+ rs.get("org_name").toString().replace(" ", "")
							+ "','"
							+ rs.get("IGNITION_STATUS")
							+ "') >"
							+ ""
							+ "<input type='hidden' name='device_id_"
							+ rs.get("DEVICE_ID")

							+ "' id='deviceid' value='"
							+ rs.get("DEVICE_ID")
							+ "'"
							+ ""
							+ "<input type='hidden' name='vehicle_no_'"
							+ rs.get("lICENCE_NUMBER")
							+ "' id='vehicleid' value='"
							+ rs.get("lICENCE_NUMBER") + "'");
					ja.add(rs.get("lICENCE_NUMBER").toString());
					ja.add(rs.get("DEVICE_ID").toString() != null ? rs.get("DEVICE_ID").toString()
							: "");
					ja.add(rs.get("vendor_name"));
					ja.add(rs.get("org_name"));
					ja.add(rs.get("SPEED"));
					ja.add(rs.get("Schedule_number"));
					ja.add(rs.get("shift_type_name"));
					ja.add(rs.get("distanceTravelled"));
					ja.add(rs.get("INTERNAL_BATTERY_VTG"));
					ja.add(rs.get("EXT_BATTERY_VOLTAGE"));
					ja.add(rs.get("IGNITION_STATUS"));
					ja.add(rs.get("SIGNAL_STRENGTH"));
					ja.add(rs.get("IST_DATE"));
					ja.add("");

					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				result.put("aaData", array);

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataforNRDVehicle(
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String limit){
		System.out.println("in record2222222222222");
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "B.depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "B.depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			
			sql="select B.lICENCE_NUMBER lICENCE_NUMBER,B.DEVICE_ID,org_name,IST_DATE,B.UPDATED_DATE,INTERNAL_BATTERY_VTG, FIRMWARE_VERSION, " +
					" EXT_BATTERY_VOLTAGE,EXT_BATTERY_VOLTAGE,SIGNAL_STRENGTH,IGNITION_STATUS,SPEED,vendor_name,reason from summaryDashboard_NRDvehile B  " +
					" inner join device vd   on B.DEVICE_ID=vd.device_serial_number inner join vehicle veh on B.lICENCE_NUMBER=veh.license_number " +
					" left join vendor v  on vd.vendor_id=v.vendor_id where "+id+" and B.lICENCE_NUMBER not in (select license_number from vehicle where cause_status in ('CW','DW','S') ) group by B.DEVICE_ID " +
					" order by org_name ";
			
			
			
		    System.out.println("query........."+sql);
		  	 Query query = session.createSQLQuery(sql)
		  			.addScalar("lICENCE_NUMBER", Hibernate.STRING)
						 .addScalar("DEVICE_ID", Hibernate.STRING)
						 .addScalar("org_name", Hibernate.STRING)
		  	 			 .addScalar("IST_DATE",Hibernate.STRING)
		  	 			 .addScalar("UPDATED_DATE",Hibernate.STRING)
		  	 			 .addScalar("INTERNAL_BATTERY_VTG",Hibernate.STRING)
		  	 			 .addScalar("FIRMWARE_VERSION",Hibernate.STRING)
		  	 			 .addScalar("EXT_BATTERY_VOLTAGE",Hibernate.STRING)
		  	 			 .addScalar("SIGNAL_STRENGTH",Hibernate.STRING)
		  	 			 .addScalar("IGNITION_STATUS",Hibernate.STRING)
		  	 			 .addScalar("SPEED",Hibernate.STRING)
		  	 			 .addScalar("vendor_name",Hibernate.STRING)
		  	 			  .addScalar("reason",Hibernate.STRING)
		  	 			 ;
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					
					JSONArray ja = new JSONArray();
					ja.add(i+1);
					ja.add("<img src='assets/images/Bus-Icon.png'>");
					ja.add(rs.get("lICENCE_NUMBER").toString());
					ja.add(rs.get("DEVICE_ID").toString() != null ? rs.get("DEVICE_ID").toString()
							: "");
					ja.add(rs.get("vendor_name"));
					ja.add(rs.get("org_name"));
					if(rs.get("SPEED").equals("null")){
						ja.add("0");
					}else{
					ja.add(rs.get("SPEED"));
					}
					ja.add("0");
					ja.add("");
					ja.add(rs.get("0"));
					ja.add(rs.get("INTERNAL_BATTERY_VTG"));
					ja.add(rs.get("EXT_BATTERY_VOLTAGE"));
					ja.add(rs.get("IGNITION_STATUS"));
					ja.add(rs.get("SIGNAL_STRENGTH"));
					if(rs.get("IST_DATE").equals("null")){
						ja.add("");
					}else{
						ja.add(rs.get("IST_DATE"));
					}
					
					if(rs.get("reason").equals("null")){
						ja.add("");
					}else{
						ja.add(rs.get("reason"));	
					}
					

//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				result.put("aaData", array);

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	public JSONObject getDataForInternalBatteryVehicle(
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String limit){
		System.out.println("in record2222222222222");
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "B.depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "B.depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			
			sql="select B.lICENCE_NUMBER lICENCE_NUMBER,B.DEVICE_ID,DEPOT_ID,DEPOT_NAME,Schedule_number,distanceTravelled,B.UPDATED_DATE,INTERNAL_BATTERY_VTG,FIRMWARE_VERSION," +
					" EXT_BATTERY_VOLTAGE,EXT_BATTERY_VOLTAGE,SIGNAL_STRENGTH,IGNITION_STATUS,SPEED,vendor_name,shift_type_name from summaryDashboard_intenalBattery B   " +
					" left join device vd   on B.DEVICE_ID=vd.device_serial_number left join vendor v on vd.vendor_id=v.vendor_id" +
					" left join shift_type st on B.duty_type_id=st.shift_type_id " +
					" where "+id+"  group by B.DEVICE_ID order by DEPOT_NAME";
			
			
			
		    System.out.println("query........."+sql);
		  	 Query query = session.createSQLQuery(sql)
		  			.addScalar("lICENCE_NUMBER", Hibernate.STRING)
						 .addScalar("DEVICE_ID", Hibernate.STRING)
						 .addScalar("DEPOT_ID",Hibernate.STRING)
						 .addScalar("Schedule_number",Hibernate.STRING)
						 .addScalar("distanceTravelled",Hibernate.STRING)
						 .addScalar("shift_type_name", Hibernate.STRING)
						 .addScalar("DEPOT_NAME", Hibernate.STRING)
		  	 			 .addScalar("UPDATED_DATE",Hibernate.STRING)
		  	 			 .addScalar("INTERNAL_BATTERY_VTG",Hibernate.STRING)
		  	 			 .addScalar("FIRMWARE_VERSION",Hibernate.STRING)
		  	 			 .addScalar("EXT_BATTERY_VOLTAGE",Hibernate.STRING)
		  	 			 .addScalar("SIGNAL_STRENGTH",Hibernate.STRING)
		  	 			 .addScalar("IGNITION_STATUS",Hibernate.STRING)
		  	 			 .addScalar("SPEED",Hibernate.STRING)
		  	 			 .addScalar("vendor_name",Hibernate.STRING)
		  	 			 ;
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					
					JSONArray ja = new JSONArray();
					ja.add(i+1);
					ja.add("<img src='assets/images/bus-map-icon.png'  title='TrackOnline' onclick=javascript:initializea('"
							+ rs.get("DEVICE_ID")
							+ "','"
							+ rs.get("lICENCE_NUMBER")
							+ "','"
							+ rs.get("Schedule_number").toString().replace(" ", "")
							+ "','"
							+ rs.get("DEPOT_NAME").toString().replace(" ", "")
							+ "','"
							+ rs.get("IGNITION_STATUS")
							+ "') >"
							+ ""
							+ "<input type='hidden' name='device_id_"
							+ rs.get("DEVICE_ID")

							+ "' id='deviceid' value='"
							+ rs.get("DEVICE_ID")
							+ "'"
							+ ""
							+ "<input type='hidden' name='vehicle_no_'"
							+ rs.get("lICENCE_NUMBER")
							+ "' id='vehicleid' value='"
							+ rs.get("lICENCE_NUMBER") + "'");
					ja.add(rs.get("lICENCE_NUMBER").toString());
					ja.add(rs.get("DEVICE_ID").toString() != null ? rs.get("DEVICE_ID").toString()
							: "");
					ja.add(rs.get("vendor_name"));
					ja.add(rs.get("DEPOT_NAME"));
					ja.add(rs.get("SPEED"));
					if(rs.get("Schedule_number").equals("null")){
						ja.add("");
					}else{
						ja.add(rs.get("Schedule_number"));
					}
					
					ja.add(rs.get("shift_type_name"));
					ja.add(rs.get("distanceTravelled"));
					ja.add(rs.get("INTERNAL_BATTERY_VTG"));
					ja.add(rs.get("EXT_BATTERY_VOLTAGE"));
					ja.add(rs.get("IGNITION_STATUS"));
					ja.add(rs.get("SIGNAL_STRENGTH"));
					if(rs.get("UPDATED_DATE").equals("null")){
						ja.add("");
					}else{
						ja.add(rs.get("UPDATED_DATE"));
					}
					

//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				result.put("aaData", array);

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForCWS(
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String limit){
		System.out.println("in record cwssssss");
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "B.depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "B.depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			
			sql="select B.lICENCE_NUMBER lICENCE_NUMBER,B.DEVICE_ID,org_name,IST_DATE,B.UPDATED_DATE,INTERNAL_BATTERY_VTG, FIRMWARE_VERSION, " +
					" EXT_BATTERY_VOLTAGE,SIGNAL_STRENGTH,IGNITION_STATUS,SPEED,vendor_name,reason from summaryDashboard_NRDvehile B  " +
					" inner join device vd   on B.DEVICE_ID=vd.device_serial_number inner join vehicle veh on B.lICENCE_NUMBER=veh.license_number " +
					" left join vendor v  on vd.vendor_id=v.vendor_id where "+id+" and cause_status in('CW') group by B.DEVICE_ID " +
					" order by org_name";
			
			
			
		    System.out.println("query........."+sql);
		  	 Query query = session.createSQLQuery(sql)
		  			.addScalar("lICENCE_NUMBER", Hibernate.STRING)
						 .addScalar("DEVICE_ID", Hibernate.STRING)
						 .addScalar("org_name",Hibernate.STRING)
						 .addScalar("IST_DATE",Hibernate.STRING)
						 .addScalar("UPDATED_DATE",Hibernate.STRING)
						 .addScalar("INTERNAL_BATTERY_VTG", Hibernate.STRING)
						 .addScalar("FIRMWARE_VERSION", Hibernate.STRING)
		  	 			 .addScalar("EXT_BATTERY_VOLTAGE",Hibernate.STRING)
		  	 			 .addScalar("INTERNAL_BATTERY_VTG",Hibernate.STRING)
		  	 			 .addScalar("SIGNAL_STRENGTH",Hibernate.STRING)
		  	 			 .addScalar("IGNITION_STATUS",Hibernate.STRING)
		  	 			 .addScalar("SPEED",Hibernate.STRING)
		  	 			 .addScalar("IGNITION_STATUS",Hibernate.STRING)
		  	 			 .addScalar("vendor_name",Hibernate.STRING)
		  	 			 .addScalar("reason",Hibernate.STRING)
		  	 			 ;
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					
					JSONArray ja = new JSONArray();
					ja.add(i+1);
					ja.add("<img src='assets/images/Bus-Icon.png'>");
					ja.add(rs.get("lICENCE_NUMBER").toString());
					ja.add(rs.get("DEVICE_ID").toString() != null ? rs.get("DEVICE_ID").toString()
							: "");
					ja.add(rs.get("vendor_name"));
					ja.add(rs.get("org_name"));
					ja.add(rs.get("SPEED"));
					ja.add(rs.get("Schedule_number"));
					ja.add(rs.get("shift_type_name"));
					ja.add(rs.get("distanceTravelled"));
					ja.add(rs.get("INTERNAL_BATTERY_VTG"));
					ja.add(rs.get("EXT_BATTERY_VOLTAGE"));
					ja.add(rs.get("IGNITION_STATUS"));
					ja.add(rs.get("SIGNAL_STRENGTH"));
					if(rs.get("IST_DATE").equals("null")){
						ja.add("");
					}else{
						ja.add(rs.get("IST_DATE"));
					}
					
					ja.add("");

//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				result.put("aaData", array);

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForDWS(
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String limit){
		System.out.println("in record dwssssss");
		JSONObject result = new JSONObject();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			String sql;
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "B.depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "B.depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			
			sql="select B.lICENCE_NUMBER lICENCE_NUMBER,B.DEVICE_ID,org_name,IST_DATE,B.UPDATED_DATE,INTERNAL_BATTERY_VTG, FIRMWARE_VERSION, " +
					" EXT_BATTERY_VOLTAGE,SIGNAL_STRENGTH,IGNITION_STATUS,SPEED,vendor_name,reason from summaryDashboard_NRDvehile B  " +
					" inner join device vd   on B.DEVICE_ID=vd.device_serial_number inner join vehicle veh on B.lICENCE_NUMBER=veh.license_number " +
					" left join vendor v  on vd.vendor_id=v.vendor_id where "+id+" and cause_status in('DW') group by B.DEVICE_ID " +
					" order by org_name";
			
			
			
		    System.out.println("query........."+sql);
		  	 Query query = session.createSQLQuery(sql)
		  			.addScalar("lICENCE_NUMBER", Hibernate.STRING)
						 .addScalar("DEVICE_ID", Hibernate.STRING)
						 .addScalar("org_name",Hibernate.STRING)
						 .addScalar("IST_DATE",Hibernate.STRING)
						 .addScalar("UPDATED_DATE",Hibernate.STRING)
						 .addScalar("INTERNAL_BATTERY_VTG", Hibernate.STRING)
						 .addScalar("FIRMWARE_VERSION", Hibernate.STRING)
		  	 			 .addScalar("EXT_BATTERY_VOLTAGE",Hibernate.STRING)
		  	 			 .addScalar("INTERNAL_BATTERY_VTG",Hibernate.STRING)
		  	 			 .addScalar("SIGNAL_STRENGTH",Hibernate.STRING)
		  	 			 .addScalar("IGNITION_STATUS",Hibernate.STRING)
		  	 			 .addScalar("SPEED",Hibernate.STRING)
		  	 			 .addScalar("IGNITION_STATUS",Hibernate.STRING)
		  	 			 .addScalar("vendor_name",Hibernate.STRING)
		  	 			 .addScalar("reason",Hibernate.STRING)
		  	 			 ;
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					
					JSONArray ja = new JSONArray();
					ja.add(i+1);
					ja.add("<img src='assets/images/Bus-Icon.png'>");
					ja.add(rs.get("lICENCE_NUMBER").toString());
					ja.add(rs.get("DEVICE_ID").toString() != null ? rs.get("DEVICE_ID").toString()
							: "");
					ja.add(rs.get("vendor_name"));
					ja.add(rs.get("org_name"));
					ja.add(rs.get("SPEED"));
					ja.add(rs.get("Schedule_number"));
					ja.add(rs.get("shift_type_name"));
					ja.add(rs.get("distanceTravelled"));
					ja.add(rs.get("INTERNAL_BATTERY_VTG"));
					ja.add(rs.get("EXT_BATTERY_VOLTAGE"));
					ja.add(rs.get("IGNITION_STATUS"));
					ja.add(rs.get("SIGNAL_STRENGTH"));
					if(rs.get("IST_DATE").equals("null")){
						ja.add("");
					}else{
						ja.add(rs.get("IST_DATE"));
					}
				
					ja.add("");

//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				result.put("aaData", array);

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}
	
	

	
	
	
	
}
