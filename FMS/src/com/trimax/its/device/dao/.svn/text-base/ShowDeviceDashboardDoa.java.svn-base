package com.trimax.its.device.dao;

import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.jaxb.xml.trimax.com.VtsResponse;
import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.etm.doa.showEtmDashboardDoa;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.WaybillDetails;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;
import com.trimax.ws.vts.vtsutility.VtsDataModel;


public class ShowDeviceDashboardDoa {
	
	
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
//			session.close();
		}
		return param;
	}

	

	
	public JSONObject getDataForAllDevices(HttpServletRequest request) {
			JSONObject result = new JSONObject();
//			int totalCount = 0;
	
			try {
				//System.out.println("Start Time"+new Date());
				String orgtypeid = (String) request.getSession().getAttribute(
						"orgtypeid");
				String orgchartid = (String) request.getSession().getAttribute(
						"orgchartid");
			
				String id = "!=0";
				if (orgtypeid.equals("1")) {
					id = "depot_id!=0";

				}
				if (orgtypeid.equals("3")) {

					id = "depot_id in('" + orgchartid + "')";
				}
				if (orgtypeid.equals("2")) {

					id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
				}
				
				
				com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
				com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
				// TODO process result here

				
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
//				alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
				VtsResponse alertresult = port
						.webGetTotalDevicesDataForDashboard(rbKey,id);
				 JSONArray array = new JSONArray();
				 System.out.println("alertresult.getVtsDatamodel().size()===="+alertresult.getVtsDatamodel().size());
				  for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
					com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult.getVtsDatamodel().get(i);
					 JSONArray ja = new JSONArray();
					 ja.add(i+1);
					ja.add(rs.getDEVICEID().toString());
					ja.add(rs.getOrgName().toString());
					ja.add(rs.getLICENCENUMBER().toString());

					array.add(ja);
				
				
					//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
					//printDuty.setBlockName(list.get("block_name").toString());
				  }

				  result.put("aaData", array);
			
//			
//				Session session =null;
//		try {
//			String sql = "";
//			session =  HibernateUtil.getSession("hibernate.cfg.xml");
//			
//			sql = "select device_serial_number,license_number,org_name,vendor_name from device d inner join vehicle_device vd  on d.device_id=vd.device_id "+
//                  " inner join vehicle v on vd.vehicle_id = v.vehicle_id  "+
//                  " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
//                 " inner join vendor on d.vendor_id = vendor.vendor_id "+
//                 " where d.status='Active' "+ 
//                " and d.deleted_status=0  and vd.status='Active'  and v.status='Active' and v.deleted_status=0 group by device_serial_number order by org_name ";
//
//			Query query = session.createSQLQuery(sql);
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			@SuppressWarnings("unchecked")
//			List<Map<String, Object>> aliasToValueMapList = query.list();
//
//		  JSONArray array = new JSONArray();
//		  for (int i = 0; i < aliasToValueMapList.size(); i++) {
//			 Map<String, Object> rs = aliasToValueMapList.get(i);
//			 JSONArray ja = new JSONArray();
//
//			 ja.add(i + 1);
//			 ja.add(rs.get("device_serial_number").toString());
//			 ja.add(rs.get("license_number").toString());
//			 ja.add(rs.get("org_name").toString());
//			 ja.add(rs.get("vendor_name").toString());
//			 array.add(ja);
//
//	      }
//			
//			       result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
				return result;
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForDeviceChartDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date,String device_type) {
		JSONObject result = new JSONObject();
		int totalCountDevice=0;
		int totalTrmxDevice=0;
		int totalHanDevice=0;
		int totalVolvoDevice=0;
		int totalinactive=0;
		int totatTemper=0;
		int totalDevice=0;
		int trimax_active=0;
		int trimax_nrd=0;
		int trimax_inbattary=0;
		int han_active=0;
		int han_nrd=0;
		int han_inbattary=0;
		int vol_active=0;
		int vol_nrd=0;
		int vol_inbattary=0;
		
		Session session=null;
		int count[]= new int[4];
		String status[]=new String[4];
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		
			try {
				//System.out.println("Start Time"+new Date());
				String orgtypeid = (String) request.getSession().getAttribute(
						"orgtypeid");
				String orgchartid = (String) request.getSession().getAttribute(
						"orgchartid");
			
				String id = "!=0";
				if (orgtypeid.equals("1")) {
					id = "depot_id!=0";

				}
				if (orgtypeid.equals("3")) {

					id = "depot_id in('" + orgchartid + "')";
				}
				if (orgtypeid.equals("2")) {

					id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
				}
				
				com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
				com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
				// TODO process result here

				
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
//				alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
				model.jaxb.xml.trimax.com.VtsResponse alertresult = port
						.webGetTotalDevicesForDashboard(rbKey,id);
				 JSONArray array = new JSONArray();
				 System.out.println("device_type=="+device_type);
				 if(device_type.equals("All")){
				  for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
					com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult.getVtsDatamodel().get(i);
					 JSONArray ja = new JSONArray();

					

			      
				
					totalDevice=(Integer.parseInt(rs.getTotalvehicle()));
					totalTrmxDevice=(Integer.parseInt(rs.getActive()));//+(Integer.parseInt(rs.getInactive()))+rs.getONINTBATTERY();
					totalHanDevice=(Integer.parseInt(rs.getHanActive()));//+(Integer.parseInt(rs.getHanNrd()))+(Integer.parseInt(rs.getHanInt()));
					totalVolvoDevice = (Integer.parseInt(rs.getVolvoActive()));//+(Integer.parseInt(rs.getVolvoInt()))+(Integer.parseInt(rs.getVolvoNrd()));
					totalCountDevice=totalTrmxDevice+totalHanDevice+totalVolvoDevice;
					//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
					//printDuty.setBlockName(list.get("block_name").toString());
					System.out.println("totalDevice=="+totalDevice+"total_all===="+totalCountDevice+"totalTrmxDevice=="+totalTrmxDevice+"totalHanDevice==="+totalHanDevice+"totalVolvoDevice==="+totalVolvoDevice);
				  }

//			JSONArray array = new JSONArray();
			
			int totalCount = 0;
			//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
				//count[0] =totalDevice;
				count[0] =totalTrmxDevice;
				count[1] = totalHanDevice;
				count[2] = totalVolvoDevice;
				count[3]=totalDevice;
				//status[0] = "Total ETIM";
				status[0] = "Trimax";
				status[1] = "Hanover";
				status[2] = "Volvo";
				status[3] = "Total Devices";
				//status[3]=" ";
			//}

			for (int i = 0; i < 4; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per=Double.parseDouble(String.valueOf(count[i]))*100/totalDevice;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			}
				 }
				 
				 model.jaxb.xml.trimax.com.VtsResponse alertresult1 = port
							.webGetTotalDevicesForDevice(rbKey,id);
				
				 if(device_type.equals("Trimax")){
					  for (int i = 0; i < alertresult1.getVtsDatamodel().size(); i++) {
						com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult1.getVtsDatamodel().get(i);
						 JSONArray ja = new JSONArray();

						

				      
					
						totalDevice=(Integer.parseInt(rs.getTotalvehicle()))+(Integer.parseInt(rs.getTotalNrd()))+(Integer.parseInt(rs.getTotalInt()));
						totalTrmxDevice=(Integer.parseInt(rs.getActive()))+(Integer.parseInt(rs.getInactive()))+rs.getONINTBATTERY();
						totalHanDevice=(Integer.parseInt(rs.getHanActive()))+(Integer.parseInt(rs.getHanNrd()))+(Integer.parseInt(rs.getHanInt()));
						totalVolvoDevice = (Integer.parseInt(rs.getVolvoActive()))+(Integer.parseInt(rs.getVolvoInt()))+(Integer.parseInt(rs.getVolvoNrd()));
						trimax_active=(Integer.parseInt(rs.getActive()));
						trimax_nrd=(Integer.parseInt(rs.getInactive()));
						trimax_inbattary=rs.getONINTBATTERY();
						totalCountDevice=totalTrmxDevice+totalHanDevice+totalVolvoDevice;
						//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
						System.out.println("totalDevice=="+totalDevice+"total_all===="+totalCountDevice+"totalTrmxDevice=="+totalTrmxDevice+"totalHanDevice==="+totalHanDevice+"totalVolvoDevice==="+totalVolvoDevice);
					  }

//				JSONArray array = new JSONArray();
				
				int totalCount = 0;
				//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
					//count[0] =totalDevice;
					count[0] =trimax_active;
					count[1] = trimax_nrd;
					count[2] = trimax_inbattary;
					count[3]=totalTrmxDevice;
					//status[0] = "Total ETIM";
					status[0] = "ACTIVE";
					status[1] = "NRD";
					status[2] = "Int.Battary";
					status[3] = "Total Trimax Devices";
					//status[3]=" ";
				//}

				for (int i = 0; i < 4; i++) {
					JSONArray ja = new JSONArray();
					ja.add(count[i]);
					double per=Double.parseDouble(String.valueOf(count[i]))*100/totalTrmxDevice;
					ja.add(per);
					ja.add(status[i]);
					array.add(ja);
				}
					 }
				 
				 if(device_type.equals("Hanover")){
					  for (int i = 0; i < alertresult1.getVtsDatamodel().size(); i++) {
						com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult1.getVtsDatamodel().get(i);
						 JSONArray ja = new JSONArray();

						

				      
					
//						totalDevice=(Integer.parseInt(rs.getTotalvehicle()))+(Integer.parseInt(rs.getTotalNrd()))+(Integer.parseInt(rs.getTotalInt()));
						 totalDevice=Integer.parseInt(rs.getHanActive());
						totalTrmxDevice=(Integer.parseInt(rs.getActive()))+(Integer.parseInt(rs.getInactive()))+rs.getONINTBATTERY();
						totalHanDevice=(Integer.parseInt(rs.getHanActive()))+(Integer.parseInt(rs.getHanNrd()))+(Integer.parseInt(rs.getHanInt()));
						totalVolvoDevice = (Integer.parseInt(rs.getVolvoActive()))+(Integer.parseInt(rs.getVolvoInt()))+(Integer.parseInt(rs.getVolvoNrd()));
						han_active=(Integer.parseInt(rs.getHanActive()));
						han_nrd=(Integer.parseInt(rs.getHanNrd()));
						han_inbattary=(Integer.parseInt(rs.getHanInt()));
						totalCountDevice=totalTrmxDevice+totalHanDevice+totalVolvoDevice;
						//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
						System.out.println("totalDevice=="+totalDevice+"total_all===="+totalCountDevice+"totalTrmxDevice=="+totalTrmxDevice+"totalHanDevice==="+totalHanDevice+"totalVolvoDevice==="+totalVolvoDevice);
					  }

//				JSONArray array = new JSONArray();
				
				int totalCount = 0;
				//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
					//count[0] =totalDevice;
					count[0] =han_active;
					count[1] = han_nrd;
					count[2] = han_inbattary;
					count[3]=totalHanDevice;
					//status[0] = "Total ETIM";
					status[0] = "ACTIVE";
					status[1] = "NRD";
					status[2] = "Int.Battary";
					status[3] = "Total Hanover Devices";
					//status[3]=" ";
				//}

				for (int i = 0; i < 4; i++) {
					JSONArray ja = new JSONArray();
					ja.add(count[i]);
					double per=Double.parseDouble(String.valueOf(count[i]))*100/totalHanDevice;
					ja.add(per);
					ja.add(status[i]);
					array.add(ja);
				}
					 }
				 
				 if(device_type.equals("Volvo")){
					  for (int i = 0; i < alertresult1.getVtsDatamodel().size(); i++) {
						com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult1.getVtsDatamodel().get(i);
						 JSONArray ja = new JSONArray();

						

				      
					
						totalDevice=(Integer.parseInt(rs.getTotalvehicle()))+(Integer.parseInt(rs.getTotalNrd()))+(Integer.parseInt(rs.getTotalInt()));
						totalTrmxDevice=(Integer.parseInt(rs.getActive()))+(Integer.parseInt(rs.getInactive()))+rs.getONINTBATTERY();
						totalHanDevice=(Integer.parseInt(rs.getHanActive()))+(Integer.parseInt(rs.getHanNrd()))+(Integer.parseInt(rs.getHanInt()));
						totalVolvoDevice = (Integer.parseInt(rs.getVolvoActive()))+(Integer.parseInt(rs.getVolvoInt()))+(Integer.parseInt(rs.getVolvoNrd()));
						vol_active=(Integer.parseInt(rs.getVolvoActive()));
						vol_nrd=(Integer.parseInt(rs.getVolvoNrd()));
						vol_inbattary=(Integer.parseInt(rs.getVolvoInt()));
						totalCountDevice=totalTrmxDevice+totalHanDevice+totalVolvoDevice;
						//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
						System.out.println("totalDevice=="+totalDevice+"total_all===="+totalCountDevice+"totalTrmxDevice=="+totalTrmxDevice+"totalHanDevice==="+totalHanDevice+"totalVolvoDevice==="+totalVolvoDevice);
					  }

//				JSONArray array = new JSONArray();
				
				int totalCount = 0;
				//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
					//count[0] =totalDevice;
					count[0] =vol_active;
					count[1] = vol_nrd;
					count[2] = vol_inbattary;
					count[3]=totalVolvoDevice;
					//status[0] = "Total ETIM";
					status[0] = "ACTIVE";
					status[1] = "NRD";
					status[2] = "Int.Battary";
					status[3] = "Total Volvo Devices";
					//status[3]=" ";
				//}

				for (int i = 0; i < 4; i++) {
					JSONArray ja = new JSONArray();
					ja.add(count[i]);
					double per=Double.parseDouble(String.valueOf(count[i]))*100/totalVolvoDevice;
					ja.add(per);
					ja.add(status[i]);
					array.add(ja);
				}
					 }
				 
				 
				 
				 
				 
				 
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	

	@SuppressWarnings("unchecked")
	public JSONObject getDataForDeviceChartNRDDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date,String device_type) {
		JSONObject result = new JSONObject();
		
		int totalDevice=0;
		int total_cws_dws=0;
		int other=0;
		int total_cws=0;
		int total_dws=0;
		int vendor_id=0;
		
		Session session=null;
		List<Map<String,Object>> aliasToValueMapList1 = null;
		int count[]= new int[4];
		String status[]=new String[4];
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		
			try {
				//System.out.println("Start Time"+new Date());
				String orgtypeid = (String) request.getSession().getAttribute(
						"orgtypeid");
				String orgchartid = (String) request.getSession().getAttribute(
						"orgchartid");
			
				String id = "!=0";
				if (orgtypeid.equals("1")) {
					id = "depot_id!=0";

				}
				if (orgtypeid.equals("3")) {

					id = "depot_id in('" + orgchartid + "')";
				}
				if (orgtypeid.equals("2")) {

					id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
				}
				
				 if(device_type.equals("Trimax")){
					 vendor_id=6;
				 }
			 
			 if(device_type.equals("Hanover")){
				 vendor_id=9;
				 }
			 
			 if(device_type.equals("Volvo")){
				 vendor_id=10;
				 }
				
				session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
				
				String sql=" select (SELECT count(*)  FROM `summaryDashboard_NRDvehile` B   "+ 
						   " inner join vehicle_device_rel vdr on B.DEVICE_ID= vdr.DEVICE_ID where B."+id+" and vendor_id="+vendor_id+" and CAUSE_STATUS not in ('S') ) as TrimaxNrd, "+
						   " (SELECT count(*) otherNrd  FROM `summaryDashboard_NRDvehile` B    "+
						   " right join vehicle_device_rel vdr on B.DEVICE_ID= vdr.DEVICE_ID where B."+id+" and vendor_id="+vendor_id+" and CAUSE_STATUS not in ('DW','CW','S') ) as OtherVehicle,  "+
						   " (SELECT count(*) cwNrd  FROM `summaryDashboard_NRDvehile` B   "+
						   " right join vehicle_device_rel vdr on B.DEVICE_ID= vdr.DEVICE_ID where B."+id+" and vendor_id="+vendor_id+" and CAUSE_STATUS  in ('CW')) as CWVehicle, "+
						   " (SELECT count(*) DwNrd  FROM `summaryDashboard_NRDvehile` B   "+
						   " right join vehicle_device_rel vdr on B.DEVICE_ID= vdr.DEVICE_ID where B."+id+" and vendor_id="+vendor_id+" and CAUSE_STATUS  in ('DW')) as DWVehicle ";
				System.out.println("Sql==="+sql);
				
				Query queryObject1 = session.createSQLQuery(sql).addScalar("TrimaxNrd",Hibernate.STRING).addScalar("DWVehicle",Hibernate.STRING).addScalar("CWVehicle",Hibernate.STRING).addScalar("OtherVehicle",Hibernate.STRING);
				queryObject1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String,Object>> aliasToValueMapList = queryObject1.list();
//				aliasToValueMapList1 = queryObject1.list();
				System.out.println("aliasToValueMapList1====="+aliasToValueMapList.size());
				JSONArray array = new JSONArray();
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,Object> map = aliasToValueMapList.get(i);
					JSONArray ja = new JSONArray();
					totalDevice=(Integer.parseInt(map.get("TrimaxNrd").toString()));
					total_cws=(Integer.parseInt(map.get("CWVehicle").toString()));
					total_dws=(Integer.parseInt(map.get("DWVehicle").toString()));
					total_cws_dws=total_cws+total_dws;
					other=(Integer.parseInt(map.get("OtherVehicle").toString()));
				}
				System.out.println("totalDevice=="+totalDevice+"total_cws===="+total_cws+"total_dws=="+total_dws+"total_Other=="+other);

//			JSONArray array = new JSONArray();
			
			int totalCount = 0;
			//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
				//count[0] =totalDevice;
				count[0] =total_cws;
				count[1] = total_dws;
				count[2] = other;
				count[3] = totalDevice;
				//status[0] = "Total ETIM";
				status[0] = "CWS";
				status[1] = "DWS";
				status[2] = "OTHER";
				status[3] = "Total";
				
				//status[3]=" ";
			//}

			for (int i = 0; i < 4; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per=Double.parseDouble(String.valueOf(count[i]))*100/totalDevice;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			} 
				 
				 
				 
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getWebDataForDeviceChartNRDDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date,String device_type) {
		JSONObject result = new JSONObject();
		
		int totalDevice=0;
		int total_cws_dws=0;
		int other=0;
		int total_cws=0;
		int total_dws=0;
		int vendor_id=0;
		
		Session session=null;
		List<Map<String,Object>> aliasToValueMapList1 = null;
		int count[]= new int[4];
		String status[]=new String[4];
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		
			try {
				//System.out.println("Start Time"+new Date());
				String orgtypeid = (String) request.getSession().getAttribute(
						"orgtypeid");
				String orgchartid = (String) request.getSession().getAttribute(
						"orgchartid");
			
				String id = "!=0";
				if (orgtypeid.equals("1")) {
					id = "depot_id!=0";

				}
				if (orgtypeid.equals("3")) {

					id = "depot_id in('" + orgchartid + "')";
				}
				if (orgtypeid.equals("2")) {

					id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
				}
				
				 if(device_type.equals("Trimax")){
					 vendor_id=6;
				 }
			 
			 if(device_type.equals("Hanover")){
				 vendor_id=9;
				 }
			 
			 if(device_type.equals("Volvo")){
				 vendor_id=10;
				 }
				
				NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
				com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
			 
			
				// TODO process result here

				
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
//				alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
				VtsResponseNew alertresult = port
						.webGetNrdDeviceCount(vendor_id,rbKey,id);
				 JSONArray array = new JSONArray();
				 System.out.println("device_type=="+device_type);
			System.out.println("alertresult.getVtsDatamodel().size()==="+alertresult.getVtsDatamodelnew().size());
				  for (int i = 0; i < alertresult.getVtsDatamodelnew().size(); i++) {
					VtsDataModelNew rs = alertresult.getVtsDatamodelnew().get(i);
					 JSONArray ja = new JSONArray();

//					 totalDevice=(Integer.parseInt(rs.getTrimaxNrd().toString()));
						total_cws=(Integer.parseInt(rs.getCWVehicle().toString()));
						total_dws=(Integer.parseInt(rs.getDWVehicle().toString()));
						total_cws_dws=total_cws+total_dws;
						other=(Integer.parseInt(rs.getOtherVehicle().toString()));

						totalDevice=total_cws+total_dws+other;
				
				
					//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
					//printDuty.setBlockName(list.get("block_name").toString());
//					System.out.println("totalDevice=="+totalDevice+"total_all===="+totalCountDevice+"totalTrmxDevice=="+totalTrmxDevice+"totalHanDevice==="+totalHanDevice+"totalVolvoDevice==="+totalVolvoDevice);
				  }

//			JSONArray array = new JSONArray();
			
	
				 
				System.out.println("totalDevice=="+totalDevice+"total_cws===="+total_cws+"total_dws=="+total_dws+"total_Other=="+other);

//			JSONArray array = new JSONArray();
			
			int totalCount = 0;
			//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
				//count[0] =totalDevice;
				count[0] =total_cws;
				count[1] = total_dws;
				count[2] = other;
				count[3] = totalDevice;
				//status[0] = "Total ETIM";
				status[0] = "CWS";
				status[1] = "DWS";
				status[2] = "OTHER";
				status[3] = "Total";
				
				//status[3]=" ";
			//}

			for (int i = 0; i < 4; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per=Double.parseDouble(String.valueOf(count[i]))*100/totalDevice;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			} 
				 
				 
				 
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForDevices(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date,String alertId,String device_type) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		String activeDevice="";
		String device_type_id="";
		int device_type_id1=0;
		
		try {
			//System.out.println("Start Time"+new Date());
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			System.out.println("alertId==="+alertId);
			System.out.println("device_type==="+device_type);
			JSONArray array = new JSONArray();
			if(device_type.equals("ALL")){
			
			if(alertId.equals("Trimax")){
				device_type_id="in (2,6)";
			}else if(alertId.equals("Hanover")){
				device_type_id="in (9)";
			}else {
				device_type_id="in (10)";
			}
			
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO process result here

			
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
//			alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
			VtsResponse alertresult = port
					.webGetActiveTotalDeviceType(device_type_id,rbKey,id);
			 
			 System.out.println("alertresult.getVtsDatamodel().size()===="+alertresult.getVtsDatamodel().size());
			  for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult.getVtsDatamodel().get(i);
				 JSONArray ja = new JSONArray();
				 ja.add(i+1);
				ja.add(rs.getDEVICEID().toString());
				ja.add(rs.getOrgName().toString());
				ja.add(rs.getLICENCENUMBER().toString());

				array.add(ja);
			
			
				//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
				//printDuty.setBlockName(list.get("block_name").toString());
			  }
			}else{
				
				if(device_type.equals("Trimax")){
					device_type_id1=6;
				}else if(device_type.equals("Hanover")){
					device_type_id1=9;
				}else {
					device_type_id1=10;
				}
				
				if(alertId.equals("ACTIVE")){
					
					com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					// TODO process result here

					
					// TODO initialize WS operation arguments here
					// Query To Get Schedule Header Information..
//					alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
					VtsResponse alertresult = port
							.webGetActiveDeviceType(device_type_id1,rbKey,id);
					 
					 System.out.println("alertresult.getVtsDatamodel().size()===="+alertresult.getVtsDatamodel().size());
					  for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
						com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult.getVtsDatamodel().get(i);
						 JSONArray ja = new JSONArray();
						 ja.add(i+1);
						ja.add(rs.getDEVICEID().toString());
						ja.add(rs.getOrgName().toString());
						ja.add(rs.getLICENCENUMBER().toString());

						array.add(ja);
					
					
						//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
					  }
					
				}else if(alertId.equals("NRD")){
					
					com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					// TODO process result here

					
					// TODO initialize WS operation arguments here
					// Query To Get Schedule Header Information..
//					alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
					VtsResponse alertresult = port
							.webGetNRDDeviceType(device_type_id1,rbKey,id);
					 
					 System.out.println("alertresult.getVtsDatamodel().size()===="+alertresult.getVtsDatamodel().size());
					  for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
						com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult.getVtsDatamodel().get(i);
						 JSONArray ja = new JSONArray();
						 ja.add(i+1);
						ja.add(rs.getDEVICEID().toString());
						ja.add(rs.getOrgName().toString());
						ja.add(rs.getLICENCENUMBER().toString());

						array.add(ja);
					
					
						//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
					  }
					
				}else if(alertId.equals("Int.Battary")){
					
					com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					// TODO process result here

					
					// TODO initialize WS operation arguments here
					// Query To Get Schedule Header Information..
//					alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
					VtsResponse alertresult = port
							.webGetInterBattDeviceType(device_type_id1,rbKey,id);
					 
					 System.out.println("alertresult.getVtsDatamodel().size()===="+alertresult.getVtsDatamodel().size());
					  for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
						com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult.getVtsDatamodel().get(i);
						 JSONArray ja = new JSONArray();
						 ja.add(i+1);
						ja.add(rs.getDEVICEID().toString());
						ja.add(rs.getOrgName().toString());
						ja.add(rs.getLICENCENUMBER().toString());

						array.add(ja);
					
					
						//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
					  }
					
					
				}
				
				
			}

			  result.put("aaData", array);
		
		
		
		
		
		
		
//		try {
//			session = HibernateUtil.getSession("hibernate.cfg.xml");
//			
//			if(alertId.equalsIgnoreCase("Trimax")){
//				 activeDevice="select device_serial_number,license_number,org_name from device d inner join vehicle_device vd " +
//						" on d.device_id=vd.device_id "+
//                         " inner join vehicle v on vd.vehicle_id = v.vehicle_id "+
//                         " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
//                        " where d.status='Active' and d.deleted_status=0 and vendor_id=6 and vd.status='Active' " +
//                        " and v.status='Active' and v.deleted_status=0 order by org_name ";
//			}else if(alertId.equalsIgnoreCase("Hanover")){
//				 activeDevice="select device_serial_number,license_number,org_name from device d inner join vehicle_device vd " +
//							" on d.device_id=vd.device_id "+
//	                         " inner join vehicle v on vd.vehicle_id = v.vehicle_id "+
//	                         " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
//	                        " where d.status='Active' and d.deleted_status=0 and vendor_id=9 and vd.status='Active' " +
//	                        " and v.status='Active' and v.deleted_status=0 order by org_name ";
//			}else {
////				System.out.println("in volvo");
//				 activeDevice="select device_serial_number,license_number,org_name from device d inner join vehicle_device vd " +
//							" on d.device_id=vd.device_id "+
//	                         " inner join vehicle v on vd.vehicle_id = v.vehicle_id "+
//	                         " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
//	                        " where d.status='Active' and d.deleted_status=0 and vendor_id=10 and vd.status='Active' " +
//	                        " and v.status='Active' and v.deleted_status=0 order by org_name ";
//			}
//				Query query=session.createSQLQuery(activeDevice);
//				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
//				List<Map<String, Object>> aliasToValueMapList = query.list();
//			
//			JSONArray array = new JSONArray();
//			for(int i=0;i<aliasToValueMapList.size();i++){
//				Map<String, Object> rs = aliasToValueMapList.get(i);
//				JSONArray ja = new JSONArray();
//				ja.add(i+1);
//				ja.add(rs.get("device_serial_number"));
//				ja.add(rs.get("org_name"));
//				ja.add(rs.get("license_number"));
//				
////				ja.add(rs.get("shift_type_name"));
////				ja.add(rs.get("vehicle_no"));
//				array.add(ja);
//			}
//			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForNRDDevices(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date,String alertId,String device_type) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		String activeDevice="";
		int device_type_id=0;
		String cause_status="";
		
		try {
			//System.out.println("Start Time"+new Date());
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			System.out.println("alertId==="+alertId);
			System.out.println("device_type==="+device_type);
//			JSONArray array = new JSONArray();
		
			
			if(device_type.equals("Trimax")){
				device_type_id=6;
			}else if(device_type.equals("Hanover")){
				device_type_id=9;
			}else {
				device_type_id=10;
			}
			System.out.println("device_type_id==="+device_type_id);
			if(alertId.equals("CWS")){
				cause_status="CAUSE_STATUS in ('CW')";
			}else if(alertId.equals("DWS")){
				cause_status="CAUSE_STATUS in ('DW')";
			}else {
				cause_status="CAUSE_STATUS not in ('CW','DW','S')";
			}
			
			
			NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
			com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
		 
		
			// TODO process result here

			
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
//			alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
			VtsResponseNew alertresult = port
					.webGetNrdDeviceData(cause_status,device_type_id,rbKey,id);
			 JSONArray array = new JSONArray();
			 System.out.println("device_type=="+device_type);
		System.out.println("alertresult.getVtsDatamodel().size()==="+alertresult.getVtsDatamodelnew().size());
			  for (int i = 0; i < alertresult.getVtsDatamodelnew().size(); i++) {
				VtsDataModelNew rs = alertresult.getVtsDatamodelnew().get(i);
				 JSONArray ja = new JSONArray();

				 ja.add(i+1);
					ja.add(rs.getDEVICEID());
					ja.add(rs.getLICENCENUMBER());
					ja.add(rs.getOrgName());

					array.add(ja);

			  }

			  result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
	
	
	
	
}
	

//}
//}