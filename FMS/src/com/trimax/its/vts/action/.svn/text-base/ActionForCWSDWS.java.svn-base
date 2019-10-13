package com.trimax.its.vts.action;

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

public class ActionForCWSDWS {

	
	
	
	
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
	public JSONObject getDataForCWS(HttpServletRequest request,String fromDate, String tillDate, String limit,String status){
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
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			vehicleresult = port.getVehicleDetails(status, "", rbKey, id,limit);
			
//			sql="select B.lICENCE_NUMBER lICENCE_NUMBER,B.DEVICE_ID,org_name,IST_DATE,B.UPDATED_DATE,INTERNAL_BATTERY_VTG, FIRMWARE_VERSION, " +
//					" EXT_BATTERY_VOLTAGE,SIGNAL_STRENGTH,IGNITION_STATUS,SPEED,vendor_name,reason from summaryDashboard_NRDvehile B  " +
//					" inner join device vd   on B.DEVICE_ID=vd.device_serial_number inner join vehicle veh on B.lICENCE_NUMBER=veh.license_number " +
//					" left join vendor v  on vd.vendor_id=v.vendor_id where "+id+" and cause_status in('CW') group by B.DEVICE_ID " +
//					" order by org_name";
			
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add("<img src='assets/images/Bus-Icon.png'>");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID() != null ? vehicleresult.getVtsDatamodel().get(i).getDEVICEID(): "");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName() != null ? vehicleresult
						.getVtsDatamodel().get(i).getOrgName()
						: "NA");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getSPEEDKMPH() != null ? vehicleresult
						.getVtsDatamodel().get(i).getSPEEDKMPH() : "0");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getScheduleNumber() != null ? vehicleresult
						.getVtsDatamodel().get(i).getScheduleNumber() : "0");
				String shift = "";
				switch (Integer.parseInt(vehicleresult.getVtsDatamodel()
						.get(i).getDutyId() != null ? vehicleresult
						.getVtsDatamodel().get(i).getDutyId() : "0")) {
				case 1:
					shift = "General Shift";
					break;
				case 2:
					shift = "Day 1";
					break;
				case 3:
					shift = "Day 2";
					break;
				case 4:
					shift = "Shift 1";
					break;
				case 5:
					shift = "Shift 2";
					break;
				case 6:
					shift = "Day 1 old";
					break;
				case 7:
					shift = "Day 2 old";
					break;
				case 78:
					shift = "Night Service";
					break;
				case 79:
					shift = "Shift 1";
					break;
				case 80:
					shift = "Shift 2";
					break;
				case 82:
					shift = "Split Service";
					break;
					
				}
				ja.add(shift);
				double distance = Double
						.parseDouble(vehicleresult.getVtsDatamodel().get(i)
								.getACCDISTANCE() != null ? vehicleresult
								.getVtsDatamodel().get(i).getACCDISTANCE()
								: "0") / 1000;
				ja.add(Math.round(distance));
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getINTERNALBATTERYVTG() != null ? vehicleresult
						.getVtsDatamodel().get(i).getINTERNALBATTERYVTG()
						: "");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getEXTBATTERYVOLTAGE() != null ? vehicleresult
						.getVtsDatamodel().get(i).getEXTBATTERYVOLTAGE()
						: "");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getIGNSTATUS());
				
				String signalStrength = vehicleresult.getVtsDatamodel()
						.get(i).getSIGNALSTRENGTH() != null ? vehicleresult
						.getVtsDatamodel().get(i).getSIGNALSTRENGTH() : "0";
				
				if (!signalStrength.startsWith("N")) {
					// signalStrength=Math.round(Double.parseDouble(signalStrength));
					ja.add(Math.round(Double.parseDouble(signalStrength)));
				} else {
					ja.add(signalStrength);
				}
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getCREATEDDATE());
				
					array.add(ja);
				
				result.put("aaData", array);
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}

	public static String rbKey = String.valueOf(getSysParameterForVts());
	
	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return param;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForDWS(HttpServletRequest request,String fromDate, String tillDate, String limit,String status){
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
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			vehicleresult = port.getVehicleDetails(status, "", rbKey, id,limit);
			
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add("<img src='assets/images/Bus-Icon.png'>");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID() != null ? vehicleresult.getVtsDatamodel().get(i).getDEVICEID(): "");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName() != null ? vehicleresult
						.getVtsDatamodel().get(i).getOrgName()
						: "NA");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getSPEEDKMPH() != null ? vehicleresult
						.getVtsDatamodel().get(i).getSPEEDKMPH() : "0");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getScheduleNumber() != null ? vehicleresult
						.getVtsDatamodel().get(i).getScheduleNumber() : "0");
				String shift = "";
				switch (Integer.parseInt(vehicleresult.getVtsDatamodel()
						.get(i).getDutyId() != null ? vehicleresult
						.getVtsDatamodel().get(i).getDutyId() : "0")) {
				case 1:
					shift = "General Shift";
					break;
				case 2:
					shift = "Day 1";
					break;
				case 3:
					shift = "Day 2";
					break;
				case 4:
					shift = "Shift 1";
					break;
				case 5:
					shift = "Shift 2";
					break;
				case 6:
					shift = "Day 1 old";
					break;
				case 7:
					shift = "Day 2 old";
					break;
				case 78:
					shift = "Night Service";
					break;
				case 79:
					shift = "Shift 1";
					break;
				case 80:
					shift = "Shift 2";
					break;
				case 82:
					shift = "Split Service";
					break;
					
				}
				ja.add(shift);
				double distance = Double
						.parseDouble(vehicleresult.getVtsDatamodel().get(i)
								.getACCDISTANCE() != null ? vehicleresult
								.getVtsDatamodel().get(i).getACCDISTANCE()
								: "0") / 1000;
				ja.add(Math.round(distance));
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getINTERNALBATTERYVTG() != null ? vehicleresult
						.getVtsDatamodel().get(i).getINTERNALBATTERYVTG()
						: "");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getEXTBATTERYVOLTAGE() != null ? vehicleresult
						.getVtsDatamodel().get(i).getEXTBATTERYVOLTAGE()
						: "");
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getIGNSTATUS());
				
				String signalStrength = vehicleresult.getVtsDatamodel()
						.get(i).getSIGNALSTRENGTH() != null ? vehicleresult
						.getVtsDatamodel().get(i).getSIGNALSTRENGTH() : "0";
				
				if (!signalStrength.startsWith("N")) {
					// signalStrength=Math.round(Double.parseDouble(signalStrength));
					ja.add(Math.round(Double.parseDouble(signalStrength)));
				} else {
					ja.add(signalStrength);
				}
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getCREATEDDATE());
				
					array.add(ja);
				
				result.put("aaData", array);
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
	}

	
	
	
	
}
