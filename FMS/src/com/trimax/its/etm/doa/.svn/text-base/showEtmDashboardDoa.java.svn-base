package com.trimax.its.etm.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;

public class showEtmDashboardDoa {
	
	private String  device;
	
	public JSONObject getDataForChart(int total, HttpServletRequest request,
			String cols, String dir, String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		List vehiclelist = new ArrayList();
		List<String> totalCounts=null;
		List<String> gprsSigCount=null;
		List<String> tamperedCount=null;
		List<String> batStatusCount=null;
		double  batStatusPer=0.00;
		double  gprsStatusPer=0.00;
		double  tamperPer=0.00;
		
		JSONArray array = new JSONArray();
		
		Session session = null;
		int totalCountEtm=0;
		int totalbatterystaus=0;
		int totalGprs=0;
		int totatTemper=0;
			
	        HttpServletResponse response = ServletActionContext.getResponse();
			try {
				//select  count(gprssig) as 	gprssig ,DEVICE_ID  from ETM_DATA where 	gprssig <50  and inserted_tm between '2015-02-21 00:00:00' and '2015-02-23 21:59:00' group by DEVICE_ID 
				//inserted_tm between  '"+fromDate+"' and '"+tillDate+"'
				String  countQuery="SELECT group_concat(DEVICE_ID), group_concat(etm_id), count(DEVICE_ID) as cnt_device, IFNULL(sum(if(bstat<=50,1,0)),0) as bstat, " +
						" IFNULL(sum(if(tampstat=0,1,0)),0) as tampstat, " +
						" IFNULL(sum(if(gprssig<=50,1,0)),0) as gprssig" +
						" FROM `ETM_DATA` where etm_id in (select max(etm_id) from ETM_DATA where  inserted_tm between   '"+fromDate+"' and '"+tillDate+"'  group by DEVICE_ID)";

				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
				String totalCount="select Count(etm_id) as etm_id from ETM_DATA  where    inserted_tm between  '"+fromDate+"' and '"+tillDate+"' group by DEVICE_ID  ";
			
				Query query = session.createSQLQuery(totalCount).addScalar("etm_id",Hibernate.STRING);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				if (query.list().size() > 0) {
					
					totalCounts = query.list();
					//totalCountEtm=totalCounts.size();
				}
				
				Query q5 = session.createSQLQuery(countQuery).addScalar("gprssig",Hibernate.STRING)
						.addScalar("bstat",Hibernate.STRING).addScalar("tampstat", Hibernate.STRING);
				q5.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
				List<Map<String, Object>> aliasToValueMapList = q5.list();
				for(int i=0;i<aliasToValueMapList.size();i++){
			
					Map<String, Object> list = aliasToValueMapList.get(i);
					
					totalbatterystaus=(Integer.parseInt(list.get("bstat").toString()));
					totatTemper=(Integer.parseInt(list.get("tampstat").toString()));
					totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
					//printDuty.setBlockName(list.get("block_name").toString());
				
				}
				
				/*ja.add(batStatusCount);
				ja.add(gprsSigCount);
				ja.add(tamperedCount);
				*/
				
				if(totalbatterystaus==0){
					batStatusPer=batStatusPer;
				}else {
					batStatusPer=Double.parseDouble(String.valueOf(totalbatterystaus))* 100 / totalCounts.size();
				}
				if(totalGprs==0){
					gprsStatusPer=gprsStatusPer;
				}else {
					gprsStatusPer=Double.parseDouble(String.valueOf(totalGprs)) * 100 / totalCounts.size();
				}
				if(totatTemper==0){
					tamperPer=tamperPer;
				}else {
					tamperPer=Double.parseDouble(String.valueOf(totatTemper)) * 100 / totalCounts.size();
				}
				
			
				/*ja.add(batStatusPer);
				ja.add(gprsStatusPer);
				ja.add(tamperPer);*/
				for(int i=0;i<3;i++){
					JSONArray ja = new JSONArray();
					if(i==0){
						ja.add(totalbatterystaus);
						ja.add("Battery Status");
						ja.add("VTU Device");
						//ja.add(batStatusPer);
						ja.add("1");
					}
					if(i==1){
						ja.add(totalGprs);
						ja.add("GPRS");
						ja.add("VTU Devi33");
						//ja.add(gprsStatusPer);
						ja.add("2");
					}
					if(i==2){
						ja.add(totatTemper);
						ja.add("Tampered");
						ja.add("VTU Device3");
						//ja.add(tamperPer);
						ja.add("3");
					}
					array.add(ja);
					
				}
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
			} finally {
				if(session.isOpen() && session!=null){
					session.close();
				}
			
		
		result.put("aaData", array);
		
		return result;
	}
	

}
	
	
	public JSONObject getAlerts() {
		Session session = null;
		Map<Integer, String> alertMap = new LinkedHashMap<Integer, String>();
		JSONObject result = new JSONObject();
		try {

			/*com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO process result here
			model.jaxb.xml.trimax.com.VtsResponse totalAlertresult = port
					.getTotalAlerts(rbKey);*/
			JSONArray array = new JSONArray();
			/*alertMap.put(1,"Battery Status" );
			alertMap.put(2, "GPRS");
			alertMap.put(3, "Tempering");
			*/
			for(int i=0;i<3;i++){
				JSONArray ja = new JSONArray();
				if(i==0){
					ja.add(1);
					ja.add("Battery Status");
					ja.add("Battery statusss");
				}
				if(i==1){
					ja.add(2);
					ja.add("GPRS");
					ja.add("Gprs not schedule");
				}
				if(i==2){
					ja.add(3);
					ja.add("Tampered");
					ja.add("Tampereddd");
				}
				array.add(ja);
			}
			
		
		
			
			result.put("bbData", array);
		} catch (Exception ex) {

		}
		return result;
}
	public JSONObject getDataForChartBatteryStatus(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
	
			// Calling WebService For getting Data....01-09-2014
			
				
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
				try {
				
			    	//String sectorId=ServletActionContext.getRequest().getParameter("sectorId");
					String sql = "";
					String employeenamekannda = "";

					
					sql = "SELECT DEVICE_ID  FROM `ETM_DATA` where etm_id in (select max(etm_id) from ETM_DATA where  inserted_tm between  '"+fromDate+"' and '"+tillDate+"'  " +
							"group by DEVICE_ID) and bstat<=50;" ;
					
				// System.out.println("sql--------"+sql);

					

					/*
					 * int cntdetails=getTotalRecords(); if(cntdetails>10)
					 */
					// {
					/*sql += " limit " + request.getParameter("iDisplayStart") + ", "
							+ request.getParameter("iDisplayLength")*/;
				// }
			     //  System.out.println("sql----------"+sql);
					Query query = session.createSQLQuery(sql);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					@SuppressWarnings("unchecked")
					List<Map<String, Object>> aliasToValueMapList = query.list();

					JSONArray array = new JSONArray();
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						JSONArray ja = new JSONArray();
						
						ja.add(i+1);
						
		 				ja.add(rs.get("DEVICE_ID").toString()); 
		 				
						


						array.add(ja);

					}
			
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
				return result;
	}
	
	

	public JSONObject getDataForChartGprsSignal(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
			JSONObject result = new JSONObject();
			int totalCount = 0;
	
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql = "";
			String employeenamekannda = "";
			sql = "SELECT DEVICE_ID  FROM `ETM_DATA` where etm_id in (select max(etm_id) from ETM_DATA where inserted_tm between  '"+fromDate+"' and '"+tillDate+"' "
					+ "group by DEVICE_ID) and gprssig<=50;";

			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();

		  JSONArray array = new JSONArray();
		  for (int i = 0; i < aliasToValueMapList.size(); i++) {
			 Map<String, Object> rs = aliasToValueMapList.get(i);
			 JSONArray ja = new JSONArray();

			 ja.add(i + 1);
			 ja.add(rs.get("DEVICE_ID").toString());
			 array.add(ja);

	      }
			
			       result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
				return result;
	}
	
	public JSONObject getDataForChartTampered(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
			JSONObject result = new JSONObject();
			int totalCount = 0;
	
				Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql = "";
			//String employeenamekannda = "";
			sql = "SELECT DEVICE_ID  FROM `ETM_DATA` where etm_id in (select max(etm_id) from ETM_DATA where  inserted_tm between  '"+fromDate+"' and '"+tillDate+"' "
					+ "group by DEVICE_ID) and tampstat=0;";

			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();

		  JSONArray array = new JSONArray();
		  for (int i = 0; i < aliasToValueMapList.size(); i++) {
			 Map<String, Object> rs = aliasToValueMapList.get(i);
			 JSONArray ja = new JSONArray();

			 ja.add(i + 1);
			 ja.add(rs.get("DEVICE_ID").toString());
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
		public JSONObject getDataForEtmChart(int total,
				HttpServletRequest request, String cols, String dir,
				String fromDate, String tillDate) {
			JSONObject result = new JSONObject();
			int totalCountEtm=0;
			int totalbatterystaus=0;
			int totalGprs=0;
			int totatTemper=0;
			int totalDevice=0;
			Session session=null;
			
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				int count[] = new int[4];
				String status[] = new String[4];
	                 String  countQuery="SELECT group_concat(DEVICE_ID), group_concat(etm_id), IFNULL(count(DEVICE_ID),0) as cnt_device, IFNULL(sum(if(bstat<=50,1,0)),0) as bstat, " +
							" IFNULL(sum(if(tampstat=0,1,0)),0) as tampstat, " +
							" IFNULL(sum(if(gprssig<=50,1,0)),0) as gprssig" +
							" FROM `ETM_DATA` where etm_id in (select max(etm_id) from ETM_DATA where  inserted_tm between  '"+fromDate+"' and '"+tillDate+"'  group by DEVICE_ID)";
							
							
				   	Query q1 = session.createSQLQuery(countQuery).addScalar("gprssig",Hibernate.STRING)
							.addScalar("bstat",Hibernate.STRING).addScalar("tampstat", Hibernate.STRING)
							.addScalar("cnt_device",Hibernate.STRING);
							
					q1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
					List<Map<String, Object>> aliasToValueMapList = q1.list();
					for(int i=0;i<aliasToValueMapList.size();i++){
				
						Map<String, Object> list = aliasToValueMapList.get(i);
						totalDevice=(Integer.parseInt(list.get("cnt_device").toString()));
						totalbatterystaus=(Integer.parseInt(list.get("bstat").toString()));
						totatTemper=(Integer.parseInt(list.get("tampstat").toString()));
						totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
					
					}

				JSONArray array = new JSONArray();
				
				int totalCount = 0;
				//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
					count[0] =totalbatterystaus;
					count[1] =totatTemper;
					count[2] = totalGprs;
					//count[3]=totalGprs;
					status[0] = "Battery Status";
					status[1] = "Tampered";
					status[2] = "GPRS";
					//status[3]=" ";
				//}

				for (int i = 0; i < 3; i++) {
					JSONArray ja = new JSONArray();
					ja.add(count[i]);
					double per = Double.parseDouble(String.valueOf(count[i])) * 100
							/ totalDevice;
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
	public JSONObject getDataForEtmChartDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date) {
		JSONObject result = new JSONObject();
		int totalCountEtm=0;
		int totalactiveDevice=0;
		int totalinactive=0;
		int totatTemper=0;
		int totalDevice=0;
		int totrepair=0;
		Session session=null;
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");

		String id = "!=0";
		if (orgtypeid.equals("1")) {
			//id = "org_id!=0";
			id = " !=0";

		}
		if (orgtypeid.equals("3")) {

			//id = "org_id in('" + orgchartid + "')";
			id = " in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
			//		+ orgchartid + "')";
			id = " in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
		}
		//String subquery="";
		String regdate=(String)request.getSession().getAttribute("regdate");
		//System.out.println("============getattt---------"+regdate);
		if(regdate !=null) {
			ticketDate1=regdate;
		}
		//else {}
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			int count[] = new int[4];
			String status[] = new String[4];
//                 String  countQuery="SELECT count(*)  FROM device " +
//                 		"inner join device_org do on do.device_id=device.device_id" +
//                 		" WHERE device_type_id = '2' AND device.status = 'ACTIVE' " +
//                 		" AND deleted_status = '0' and  do.status='ACTIVE' and org_id"+id+"";
/*			String countQuery="select count(*) from device dev " +
					" inner join device_org do on do.device_id=dev.device_id " +
					" inner join org_chart oc on do.org_id=oc.org_chart_id" +
					" where device_serial_number not in" +
					" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt" +
					" join org_chart oc on egt.depot_id=oc.org_chart_id" +
					" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" order by do.org_id";
				String activeDevice="SELECT count(*)  FROM bmtcGprs.etim_gprs_ticket egt join org_chart oc on egt.depot_id=oc.org_chart_id" +
						" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no";
				String repair="SELECT COUNT(distinct etm_number) FROM `etm_device_history`  edh "
						+ " inner join device dev on edh.etm_number=dev.device_id "+
						 "inner join device_org do on do.device_id=dev.device_id "
						+ "WHERE `etm_received_date` IS NULL and device_serial_number not in " + 
						" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt " + 
				" join org_chart oc on egt.depot_id=oc.org_chart_id  " + 
					" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+"   group by etim_no ) and do.status='ACTIVE' and org_id !=0 ";*/
			String totetm="select count(*)tot,sum(case when gprsetm is not null then 1 else 0 end)active,sum(case when gprsetm is  null then 1 else 0 end )  inactive from (" + 
					"select  device_serial_number detm,egt.etim_no gprsetm,parent_id from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
					"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = '"+ticketDate1+"' ) egt on egt.etim_no=d.device_serial_number " + 
					"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and do.status='ACTIVE' and org_id !=0 and org_chart_id "+id+" group by device_serial_number order by do.org_id )a";
			Query query=session.createSQLQuery(totetm);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
				List<Map<String, Object>> aliasToValueMapList = query.list();
				Map<String, Object> rs = aliasToValueMapList.get(0);
				
					//totalDevice=(Integer.parseInt(session.createSQLQuery(countQuery).uniqueResult().toString()));
				
					totalactiveDevice=Integer.parseInt(rs.get("active").toString());
					//totalinactive=totalDevice - totalactiveDevice;
					totalinactive=Integer.parseInt(rs.get("inactive").toString());
					//totrepair=Integer.parseInt(rs.get("inactive").toString());
							//(Integer)session.createSQLQuery(repair).uniqueResult();
					totalDevice=totalactiveDevice+totalinactive;//+totrepair
					//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
					//printDuty.setBlockName(list.get("block_name").toString());
			

			JSONArray array = new JSONArray();
			
			int totalCount = 0;
			//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
				//count[0] =totalDevice;
				count[0] =totalactiveDevice;
				count[1] = totalinactive;
				//count[2] = totrepair;
				count[2] = totalDevice;
			
				//count[3]=totalGprs;
				//status[0] = "Total ETIM";
				status[0] = "Active";
				status[1] = "InActive";
				//status[2] = "Repair";
				status[2] = "Total ETIM";
				
				//status[3]=" ";
			//}

			for (int i = 0; i < 3; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalDevice;
				ja.add(per);
				ja.add(status[i]);
			//	System.out.println(ja);
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
	public JSONObject getingenico(HttpServletRequest request) {
		JSONObject result = new JSONObject();
		int totalCountEtm=0;
		int totalactiveDevice=0;
		int totalinactive=0;
		int totatTemper=0;
		int totalDevice=0;
		int totrepair=0;
		Session session=null;
		//Common common1 = new Common();
	//	String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");

		String id = "!=0";
		if (orgtypeid.equals("1")) {
			//id = "org_id!=0";
			id = " !=0";

		}
		if (orgtypeid.equals("3")) {

			//id = "org_id in('" + orgchartid + "')";
			id = " in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
			//		+ orgchartid + "')";
			id = " in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
		}
		
		String subqry=" CURDATE() ";
		String regdate=(String)request.getSession().getAttribute("regdate");
if(regdate !=null) {
	subqry="'"+regdate+"'";
}
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			int count[] = new int[4];
			String status[] = new String[4];
			String totetm="select count(*)tot,sum(case when gprsetm is not null then 1 else 0 end)active,sum(case when gprsetm is  null then 1 else 0 end )  inactive from (" + 
					"select  device_serial_number detm,egt.etim_no gprsetm,parent_id from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
					"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = "+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
					"where  device_type_id = '2' AND d.status = 'ACTIVE'  and vendor_id=13  AND d.deleted_status = '0' and do.status='ACTIVE' and org_id !=0 and org_chart_id "+id+" group by device_serial_number order by do.org_id )a";
			Query query=session.createSQLQuery(totetm);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
				List<Map<String, Object>> aliasToValueMapList = query.list();
				Map<String, Object> rs = aliasToValueMapList.get(0);
				
					//totalDevice=(Integer.parseInt(session.createSQLQuery(countQuery).uniqueResult().toString()));
				
					totalactiveDevice=Integer.parseInt(rs.get("active").toString());
					//totalinactive=totalDevice - totalactiveDevice;
					totalinactive=Integer.parseInt(rs.get("inactive").toString());
					//totrepair=Integer.parseInt(rs.get("inactive").toString());
							//(Integer)session.createSQLQuery(repair).uniqueResult();
					totalDevice=totalactiveDevice+totalinactive;//+totrepair
					//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
					//printDuty.setBlockName(list.get("block_name").toString());
			

			JSONArray array = new JSONArray();
			
			int totalCount = 0;
			//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
				//count[0] =totalDevice;
				count[0] =totalactiveDevice;
				count[1] = totalinactive;
				//count[2] = totrepair;
				count[2] = totalDevice;
			
				//count[3]=totalGprs;
				//status[0] = "Total ETIM";
				status[0] = "Active";
				status[1] = "InActive";
				//status[2] = "Repair";
				status[2] = "Total ETIM";
				
				//status[3]=" ";			//}

			for (int i = 0; i < 3; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalDevice;
				ja.add(per);
				ja.add(status[i]);
				//System.out.println(ja);
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
	public JSONObject getDataForEtmChartGprsDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date,String alertId) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");

		String id = "!=0";
		if (orgtypeid.equals("1")) {
			//id = "org_id!=0";
			id = " !=0";

		}
		if (orgtypeid.equals("3")) {

			//id = "org_id in('" + orgchartid + "')";
			id = " in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
			//		+ orgchartid + "')";
			id = " in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
		}
		
		String regdate=(String)request.getSession().getAttribute("regdate");
		//System.out.println("============getattt---------"+regdate);
		if(regdate !=null) {
			ticketDate1=regdate;
		}
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
//				String activeDevice="SELECT etim_no,org_name,schedule_no,st.shift_type_name,vehicle_no  FROM bmtcGprs.etim_gprs_ticket egt" +
//						" join org_chart oc on egt.depot_id=oc.org_chart_id join shift_type st on st.shift_type_id=egt.shift_no" +
//						" WHERE ticket_date = '"+ticketDate1+"' and oc.org_chart_id"+id+" group by etim_no order by egt.depot_id,egt.shift_no ";
			
/*			String activeDevice="Select SUM(total) total,org_name from (SELECT count(DISTINCT(etim_no)) total,org_name  FROM bmtcGprs.etim_gprs_ticket egt "+ 
								" join org_chart oc on egt.depot_id=oc.org_chart_id "+
					            " join shift_type st on st.shift_type_id=egt.shift_no "+
								" WHERE ticket_date = '"+ticketDate1+"' and oc.org_chart_id"+id+" group by etim_no,org_name order by egt.depot_id ) a group by org_name ";*/
			String activeDevice="select count(*)tot,org_name from ( " + 
					"select  device_serial_number detm,egt.etim_no gprsetm,org_name from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
					"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = '"+ticketDate1+"' ) egt on egt.etim_no=d.device_serial_number " + 
					"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and oc.org_chart_id"+id+" and do.status='ACTIVE' and org_id !=0 group by device_serial_number order by do.org_id )a where gprsetm is not null group by org_name";
					
			Query query=session.createSQLQuery(activeDevice);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
				List<Map<String, Object>> aliasToValueMapList = query.list();
			
			JSONArray array = new JSONArray();
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(i+1);
//				ja.add(rs.get("etim_no"));
				ja.add(rs.get("org_name"));
				 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal12'  onclick=javascript:jsFunctionForActiveVehicle('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");

//				ja.add(rs.get("total"));
//				ja.add(rs.get("schedule_no"));
//				ja.add(rs.get("shift_type_name"));
//				ja.add(rs.get("vehicle_no"));
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
	public JSONObject getDataForEtmChartInactiveDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date,String alertId) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");

		String id = "!=0";
		if (orgtypeid.equals("1")) {
			//id = "org_id!=0";
			id = " !=0";

		}
		if (orgtypeid.equals("3")) {

			//id = "org_id in('" + orgchartid + "')";
			id = " in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
			//		+ orgchartid + "')";
			id = " in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
		}
		String regdate=(String)request.getSession().getAttribute("regdate");
		if(regdate !=null) {
			ticketDate1=regdate;
		}
	
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
//				String activeDevice="select device_serial_number,oc.org_name from device dev " +
//						" inner join device_org do on do.device_id=dev.device_id " +
//						" inner join org_chart oc on do.org_id=oc.org_chart_id" +
//						" where device_serial_number not in" +
//						" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt" +
//						" join org_chart oc on egt.depot_id=oc.org_chart_id" +
//						" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" order by do.org_id";
				
			String activeDevice="select count(device_serial_number) total,oc.org_name from device dev  "+
								" inner join device_org do on do.device_id=dev.device_id  "+
					            " inner join org_chart oc on do.org_id=oc.org_chart_id "+
								" where device_serial_number not in (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt "+ 
								" join org_chart oc on egt.depot_id=oc.org_chart_id WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) "+
								" and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" group by org_name order by org_name";
			
			Query query=session.createSQLQuery(activeDevice);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
				List<Map<String, Object>> aliasToValueMapList = query.list();
				
				JSONArray array = new JSONArray();
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String, Object> rs = aliasToValueMapList.get(i);
					JSONArray ja = new JSONArray();
					ja.add(i+1);
					
					ja.add(rs.get("org_name"));
					 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsFunctionForInActiveVehicle('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("total") + "</a>");

//					ja.add(rs.get("total"));
					//ja.add(rs.get("schedule_no"));
					array.add(ja);
				}
				result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	//For Volvo ETM Add by Rajesh
	@SuppressWarnings("unchecked")
	public JSONObject getDataForVolvoEtmChartDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate,String ticket_date) {
		JSONObject result = new JSONObject();
//		System.out.println("Enter into Volvo");
		int totalCountEtm=0;
		int totalactiveDevice=0;
		int totalinactive=0;
		int totatTemper=0;
		int totalDevice=0;
		int repair=0;
		Session session=null;
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		
		
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");

		String id = "!=0";
		if (orgtypeid.equals("1")) {
			//id = "org_id!=0";
			id = " !=0";

		}
		if (orgtypeid.equals("3")) {

			//id = "org_id in('" + orgchartid + "')";
			id = " in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
			//		+ orgchartid + "')";
			id = " in(select org_chart_id as depotids from org_chart where parent_id='"
							+ orgchartid + "')";
		}
		String regdate=(String)request.getSession().getAttribute("regdate");
		//System.out.println("============getattt---------"+regdate);
		if(regdate !=null) {
			ticketDate1=regdate;
		}
		
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			int count[] = new int[4];
			String status[] = new String[4];
//                 String  countQuery="SELECT count(*)  FROM device " +
//                 		"inner join device_org do on do.device_id=device.device_id" +
//                 		" WHERE device_type_id = '2' AND device.status = 'ACTIVE' " +
//                 		" AND deleted_status = '0' and  do.status='ACTIVE' and org_id"+id+"";
/*			String countQuery="select count(*) from device dev " +
					" inner join device_org do on do.device_id=dev.device_id " +
					" inner join org_chart oc on do.org_id=oc.org_chart_id  and parent_id=5 and org_type_id=3 and oc.deleted_status=0" +
					" where device_serial_number not in" +
					" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt" +
					" join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id=5 and org_type_id=3 and oc.deleted_status=0" +
					" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" order by do.org_id";
				String activeDevice="SELECT count(*)  FROM bmtcGprs.etim_gprs_ticket egt join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id=5 and org_type_id=3 and deleted_status=0" +
						" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no";
				String repairqry="select count(distinct etm_number)  " + 
						"from etm_device_history edh " + 
						"inner join device dev on edh.etm_number=dev.device_id  " + 
						"inner join device_org do on do.device_id=dev.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id   " + 
						"and parent_id=5 and org_type_id=3 and oc.deleted_status=0  " + 
						"where device_serial_number not in (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt join org_chart oc on egt.depot_id=oc.org_chart_id  " + 
						"and parent_id=5 and org_type_id=3 and oc.deleted_status=0 WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no )  " + 
						"and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and edh.etm_received_date is null " + 
						"and do.status='ACTIVE' and org_id"+id+" order by do.org_id";*/
			String totetm="select count(*)tot,sum(case when gprsetm is not null then 1 else 0 end)active,sum(case when gprsetm is  null then 1 else 0 end )  inactive from (" + 
					"select  device_serial_number detm,egt.etim_no gprsetm,parent_id from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
					"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = '"+ticketDate1+"' ) egt on egt.etim_no=d.device_serial_number " + 
					"where  device_type_id = '2' AND d.status = 'ACTIVE' and parent_id=5 AND d.deleted_status = '0' and do.status='ACTIVE' and org_id !=0 and org_chart_id "+id+" group by device_serial_number order by do.org_id )a";
			Query query=session.createSQLQuery(totetm);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
				List<Map<String, Object>> aliasToValueMapList = query.list();
				Map<String, Object> rs = aliasToValueMapList.get(0);
				
					//totalDevice=(Integer.parseInt(session.createSQLQuery(countQuery).uniqueResult().toString()));
				
					totalactiveDevice=Integer.parseInt(rs.get("active").toString());
					//totalinactive=totalDevice - totalactiveDevice;
					totalinactive=Integer.parseInt(rs.get("inactive").toString());
					//totrepair=Integer.parseInt(rs.get("inactive").toString());
							//(Integer)session.createSQLQuery(repair).uniqueResult();
					totalDevice=totalactiveDevice+totalinactive;//+totrepair
					//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
					//printDuty.setBlockName(list.get("block_name").toString());
			

			JSONArray array = new JSONArray();
			
			int totalCount = 0;
			//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
				//count[0] =totalDevice;
				count[0] =totalactiveDevice;
				count[1] = totalinactive;
				//count[2] = totrepair;
				count[2] = totalDevice;
			
				//count[3]=totalGprs;
				//status[0] = "Total ETIM";
				status[0] = "Active";
				status[1] = "InActive";
				//status[2] = "Repair";
				status[2] = "Total ETIM";
				
				//status[3]=" ";

			//}

			for (int i = 0; i < 3; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalDevice;
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
	
	//For Ordinary ETM Add by Rajesh
		@SuppressWarnings("unchecked")
		public JSONObject getDataForOrdEtmChartDetails(int total,
				HttpServletRequest request, String cols, String dir,
				String fromDate, String tillDate,String ticket_date) {
			JSONObject result = new JSONObject();
//			System.out.println("Enter into Ordinary");

			int totalCountEtm=0;
			int totalactiveDevice=0;
			int totalinactive=0;
			int totatTemper=0;
			int totalDevice=0;
			int totrepair=0;
			Session session=null;
			Common common1 = new Common();
			String ticketDate1=common1.getDateFromPicker(ticket_date);
			
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				//id = "org_id!=0";
				id = " !=0";

			}
			if (orgtypeid.equals("3")) {

				//id = "org_id in('" + orgchartid + "')";
				id = " in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
				//		+ orgchartid + "')";
				id = " in(select org_chart_id as depotids from org_chart where parent_id='"
								+ orgchartid + "')";
			}
			String regdate=(String)request.getSession().getAttribute("regdate");
			//System.out.println("============getattt---------"+regdate);
			if(regdate !=null) {
				ticketDate1=regdate;
			}
			
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				int count[] = new int[4];
				String status[] = new String[4];
//	                 String  countQuery="SELECT count(*)  FROM device " +
//	                 		"inner join device_org do on do.device_id=device.device_id" +
//	                 		" WHERE device_type_id = '2' AND device.status = 'ACTIVE' " +
//	                 		" AND deleted_status = '0' and  do.status='ACTIVE' and org_id"+id+"";
/*				String countQuery="select count(*) from device dev " +
						" inner join device_org do on do.device_id=dev.device_id " +
						" inner join org_chart oc on do.org_id=oc.org_chart_id  and parent_id!=5 and org_type_id=3 and oc.deleted_status=0" +
						" where device_serial_number not in" +
						" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt" +
						" join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id!=5 and org_type_id=3 and oc.deleted_status=0" +
						" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" order by do.org_id";
					String activeDevice="SELECT count(*)  FROM bmtcGprs.etim_gprs_ticket egt join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id!=5 and org_type_id=3 and deleted_status=0" +
							" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no";
					String repair="select count(distinct etm_number)  " + 
							"from etm_device_history edh " + 
							"inner join device dev on edh.etm_number=dev.device_id  " + 
							"inner join device_org do on do.device_id=dev.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id   " + 
							"and parent_id!=5 and org_type_id=3 and oc.deleted_status=0  " + 
							"where device_serial_number not in (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt join org_chart oc on egt.depot_id=oc.org_chart_id  " + 
							"and parent_id!=5 and org_type_id=3 and oc.deleted_status=0 WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no )  " + 
							"and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and edh.etm_received_date is null " + 
							"and do.status='ACTIVE' and org_id"+id+" order by do.org_id";*/
				String totetm="select count(*)tot,sum(case when gprsetm is not null then 1 else 0 end)active,sum(case when gprsetm is  null then 1 else 0 end )  inactive from (" + 
						"select  device_serial_number detm,egt.etim_no gprsetm,parent_id from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
						"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = '"+ticketDate1+"' ) egt on egt.etim_no=d.device_serial_number " + 
						"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and do.status='ACTIVE' and org_id !=0 and parent_id!=5  and org_chart_id "+id+" group by device_serial_number order by do.org_id )a";
				Query query=session.createSQLQuery(totetm);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
					List<Map<String, Object>> aliasToValueMapList = query.list();
					Map<String, Object> rs = aliasToValueMapList.get(0);
					
						//totalDevice=(Integer.parseInt(session.createSQLQuery(countQuery).uniqueResult().toString()));
					
						totalactiveDevice=Integer.parseInt(rs.get("active").toString());
						//totalinactive=totalDevice - totalactiveDevice;
						totalinactive=Integer.parseInt(rs.get("inactive").toString());
						//totrepair=Integer.parseInt(rs.get("inactive").toString());
								//(Integer)session.createSQLQuery(repair).uniqueResult();
						totalDevice=totalactiveDevice+totalinactive;//+totrepair
						//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
						//printDuty.setBlockName(list.get("block_name").toString());
				

				JSONArray array = new JSONArray();
				
				int totalCount = 0;
				//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
					//count[0] =totalDevice;
					count[0] =totalactiveDevice;
					count[1] = totalinactive;
					//count[2] = totrepair;
					count[2] = totalDevice;
				
					//count[3]=totalGprs;
					//status[0] = "Total ETIM";
					status[0] = "Active";
					status[1] = "InActive";
					//status[2] = "Repair";
					status[2] = "Total ETIM";
					
					//status[3]=" ";
				//}

				for (int i = 0; i < 3; i++) {
					JSONArray ja = new JSONArray();
					ja.add(count[i]);
					double per = Double.parseDouble(String.valueOf(count[i])) * 100
							/ totalDevice;
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
//Volvo Data Details For Active
		@SuppressWarnings("unchecked")
		public JSONObject getDataForVolvoEtmChartGprsDetails(int total,
				HttpServletRequest request, String cols, String dir,
				String fromDate, String tillDate,String ticket_date,String alertId) {
			JSONObject result = new JSONObject();
//			System.out.println("alertId===="+alertId);
			Session session=null;
			Common common1 = new Common();
			String ticketDate1=common1.getDateFromPicker(ticket_date);
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				//id = "org_id!=0";
				id = " !=0";

			}
			if (orgtypeid.equals("3")) {

				//id = "org_id in('" + orgchartid + "')";
				id = " in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
				//		+ orgchartid + "')";
				id = " in(select org_chart_id as depotids from org_chart where parent_id='"
								+ orgchartid + "')";
			}
			
			String regdate=(String)request.getSession().getAttribute("regdate");
			//System.out.println("============getattt---------"+regdate);
			if(regdate !=null) {
				ticketDate1=regdate;
			}
			
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
//					String activeDevice="SELECT etim_no,org_name,schedule_no,st.shift_type_name,vehicle_no  FROM bmtcGprs.etim_gprs_ticket egt" +
//							" join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id=5 and org_type_id=3 and oc.deleted_status=0 join shift_type st on st.shift_type_id=egt.shift_no" +
//							" WHERE ticket_date = '"+ticketDate1+"' and oc.org_chart_id"+id+" group by etim_no order by egt.depot_id,egt.shift_no ";
/*				String activeDevice="select sum(total) total,org_name from (SELECT count(distinct(etim_no)) total,org_name  FROM bmtcGprs.etim_gprs_ticket egt "+
						            " join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id=5 and org_type_id=3 and oc.deleted_status=0 "+					            
						            " join shift_type st on st.shift_type_id=egt.shift_no inner join "+
						             " WHERE ticket_date = '"+ticketDate1+"' and oc.org_chart_id"+id+"  "+
						            " group by etim_no order by egt.depot_id,egt.shift_no ) A group by org_name ";*/
				String activeDevice="select count(*)tot,org_name from ( " + 
						"select  device_serial_number detm,egt.etim_no gprsetm,org_name from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
						"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = '"+ticketDate1+"' ) egt on egt.etim_no=d.device_serial_number " + 
						"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and parent_id=5 and oc.org_chart_id"+id+" and do.status='ACTIVE' and org_id !=0 group by device_serial_number order by do.org_id )"
								+ "a where gprsetm is not null group by org_name";
				
					Query query=session.createSQLQuery(activeDevice);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
					List<Map<String, Object>> aliasToValueMapList = query.list();
				
				JSONArray array = new JSONArray();
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String, Object> rs = aliasToValueMapList.get(i);
					JSONArray ja = new JSONArray();
					ja.add(i+1);
//					ja.add(rs.get("etim_no"));
					ja.add(rs.get("org_name"));
					 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal12'  onclick=javascript:jsFunctionForActiveVehicle('"+rs.get("org_name")+"','"+alertId.toString()+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");

//					ja.add(rs.get("total"));
//					ja.add(rs.get("schedule_no"));
//					ja.add(rs.get("shift_type_name"));
//					ja.add(rs.get("vehicle_no"));
					array.add(ja);
				}
				result.put("aaData", array);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			}
			return result;
		}
//Volvo Data Details For InActive		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForVolvoEtmChartInactiveDetails(int total,
				HttpServletRequest request, String cols, String dir,
				String fromDate, String tillDate,String ticket_date,String alertId) {
			JSONObject result = new JSONObject();
			Session session=null;
			Common common1 = new Common();
			String ticketDate1=common1.getDateFromPicker(ticket_date);
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				//id = "org_id!=0";
				id = " !=0";

			}
			if (orgtypeid.equals("3")) {

				//id = "org_id in('" + orgchartid + "')";
				id = " in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
				//		+ orgchartid + "')";
				id = " in(select org_chart_id as depotids from org_chart where parent_id='"
								+ orgchartid + "')";
			}
			String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				ticketDate1=regdate;
			}
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
//					String activeDevice="select device_serial_number,oc.org_name from device dev " +
//							" inner join device_org do on do.device_id=dev.device_id " +
//							" inner join org_chart oc on do.org_id=oc.org_chart_id and parent_id=5 and org_type_id=3 and oc.deleted_status=0" +
//							" where device_serial_number not in" +
//							" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt" +
//							" join org_chart oc on egt.depot_id=oc.org_chart_id" +
//							" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" order by do.org_id";
				String activeDevice="select count(device_serial_number) total,oc.org_name from device dev "+ 
									" inner join device_org do on do.device_id=dev.device_id  "+
									" inner join org_chart oc on do.org_id=oc.org_chart_id and parent_id=5 and org_type_id=3 and oc.deleted_status=0 "+
									" where device_serial_number not in (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt join org_chart oc on egt.depot_id=oc.org_chart_id "+
									" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  "+
									" AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" group by oc.org_name order by org_name";	
				Query query=session.createSQLQuery(activeDevice);
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
					List<Map<String, Object>> aliasToValueMapList = query.list();
					
					JSONArray array = new JSONArray();
					for(int i=0;i<aliasToValueMapList.size();i++){
						Map<String, Object> rs = aliasToValueMapList.get(i);
						JSONArray ja = new JSONArray();
						ja.add(i+1);
//						ja.add(rs.get("device_serial_number"));
						ja.add(rs.get("org_name"));
						 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsFunctionForInActiveVehicle('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("total") + "</a>");

//						ja.add(rs.get("total"));
						//ja.add(rs.get("schedule_no"));
						array.add(ja);
					}
					result.put("aaData", array);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			}
			return result;
		}
		
		//Ordinary Data Details For Active
				@SuppressWarnings("unchecked")
				public JSONObject getDataForOrdEtmChartGprsDetails(int total,
						HttpServletRequest request, String cols, String dir,
						String fromDate, String tillDate,String ticket_date,String alertId) {
					JSONObject result = new JSONObject();
					Session session=null;
					Common common1 = new Common();
					String ticketDate1=common1.getDateFromPicker(ticket_date);
					
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					String regdate=(String)request.getSession().getAttribute("regdate");
					//System.out.println("============getattt---------"+regdate);
					if(regdate !=null) {
						ticketDate1=regdate;
					}
					
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
//							String activeDevice="SELECT etim_no,org_name,schedule_no,st.shift_type_name,vehicle_no  FROM bmtcGprs.etim_gprs_ticket egt" +
//									" join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id!=5 and org_type_id=3 and oc.deleted_status=0 join shift_type st on st.shift_type_id=egt.shift_no" +
//									" WHERE ticket_date = '"+ticketDate1+"' and oc.org_chart_id"+id+" group by etim_no order by egt.depot_id,egt.shift_no ";
						
						String activeDevice="select sum(total) total,org_name from (SELECT count(distinct(etim_no)) total,org_name  FROM bmtcGprs.etim_gprs_ticket egt "+
					            " join org_chart oc on egt.depot_id=oc.org_chart_id  and parent_id!=5 and org_type_id=3 and oc.deleted_status=0 "+					            
					            " join shift_type st on st.shift_type_id=egt.shift_no WHERE ticket_date = '"+ticketDate1+"' and oc.org_chart_id"+id+"  "+
					            " group by etim_no order by egt.depot_id,egt.shift_no ) A group by org_name ";
						
							Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
						
						JSONArray array = new JSONArray();
						for(int i=0;i<aliasToValueMapList.size();i++){
							Map<String, Object> rs = aliasToValueMapList.get(i);
							JSONArray ja = new JSONArray();
							ja.add(i+1);
//							ja.add(rs.get("etim_no"));
							ja.add(rs.get("org_name"));
							 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal12'  onclick=javascript:jsFunctionForActiveVehicle('"+rs.get("org_name")+"','"+alertId.toString()+"') title='Vehicle Details'>"+rs.get("total") + "</a>");

//							ja.add(rs.get("total"));
//							ja.add(rs.get("schedule_no"));
//							ja.add(rs.get("shift_type_name"));
//							ja.add(rs.get("vehicle_no"));
							array.add(ja);
						}
						result.put("aaData", array);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
					}
					return result;
				}
		//Ordinary Data Details For InActive		
				@SuppressWarnings("unchecked")
				public JSONObject getDataForOrdEtmChartInactiveDetails(int total,
						HttpServletRequest request, String cols, String dir,
						String fromDate, String tillDate,String ticket_date,String alertId) {
					JSONObject result = new JSONObject();
					Session session=null;
					Common common1 = new Common();
					String ticketDate1=common1.getDateFromPicker(ticket_date);
					
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					String regdate=(String)request.getSession().getAttribute("regdate");
					if(regdate !=null) {
						ticketDate1=regdate;
					}
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
//							String activeDevice="select device_serial_number,oc.org_name from device dev " +
//									" inner join device_org do on do.device_id=dev.device_id " +
//									" inner join org_chart oc on do.org_id=oc.org_chart_id and parent_id!=5 and org_type_id=3 and oc.deleted_status=0" +
//									" where device_serial_number not in" +
//									" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt" +
//									" join org_chart oc on egt.depot_id=oc.org_chart_id" +
//									" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" order by do.org_id";
						
						String activeDevice="select count(device_serial_number) total,oc.org_name from device dev "+ 
								" inner join device_org do on do.device_id=dev.device_id  "+
								" inner join org_chart oc on do.org_id=oc.org_chart_id and parent_id!=5 and org_type_id=3 and oc.deleted_status=0 "+
								" where device_serial_number not in (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt join org_chart oc on egt.depot_id=oc.org_chart_id "+
								" WHERE ticket_date = '"+ticketDate1+"' and depot_id"+id+" group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  "+
								" AND dev.deleted_status = '0' and do.status='ACTIVE' and org_id"+id+" group by oc.org_name order by org_name";
						
						Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
							
							JSONArray array = new JSONArray();
							for(int i=0;i<aliasToValueMapList.size();i++){
								Map<String, Object> rs = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								ja.add(i+1);
//								ja.add(rs.get("device_serial_number"));
								ja.add(rs.get("org_name"));
								 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsFunctionForInActiveVehicle('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("total") + "</a>");

//								ja.add(rs.get("total"));
								//ja.add(rs.get("schedule_no"));
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
				public JSONObject getDataForActiveVehicleDetails(int total,
						HttpServletRequest request, String cols, String dir,
						String fromDate, String tillDate,String ticket_date,String depot_name,String alertId) {
					JSONObject result = new JSONObject();
					Session session=null;
					Common common1 = new Common();
					String activeDevice="";
//					System.out.println("AlertId====="+alertId);
					String ticketDate1=common1.getDateFromPicker(ticket_date);
					
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					String regdate=(String)request.getSession().getAttribute("regdate");
					if(regdate !=null) {
						ticketDate1=regdate;
					}
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
						if(alertId=="Active" || alertId.equalsIgnoreCase("Active")){
							
					/*		 activeDevice="SELECT etim_no,org_name,schedule_no,st.shift_type_name,vehicle_no  FROM bmtcGprs.etim_gprs_ticket egt" +
									" join org_chart oc on egt.depot_id=oc.org_chart_id join shift_type st on st.shift_type_id=egt.shift_no" +
									" WHERE ticket_date = '"+ticketDate1+"' and oc.org_name='"+depot_name+"' group by etim_no order by egt.depot_id,egt.shift_no ";*/
						activeDevice="select * from ( " + 
								"select   device_serial_number,egt.etim_no gprsetm ,org_name,schedule_no,st.shift_type_name,vehicle_no from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
								"left join (select  distinct etim_no,shift_no,schedule_no,vehicle_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date ='"+ticketDate1+"' ) egt on egt.etim_no=d.device_serial_number " + 
								"inner join shift_type st on st.shift_type_id=egt.shift_no " + 
								"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and do.status='ACTIVE' and org_id !=0 and oc.org_name='"+depot_name+"' " + 
								"group by device_serial_number order by do.org_id )a where gprsetm is not null";
						//System.out.println("1st-------------");
						}else{
						/*	activeDevice="select device_serial_number,oc.org_name from device dev " +
									" inner join device_org do on do.device_id=dev.device_id " +
									" inner join org_chart oc on do.org_id=oc.org_chart_id" +
									" where device_serial_number not in" +
									" (SELECT etim_no FROM bmtcGprs.etim_gprs_ticket egt" +
									" join org_chart oc on egt.depot_id=oc.org_chart_id" +
									" WHERE ticket_date = '"+ticketDate1+"' and oc.org_name='"+depot_name+"' group by etim_no ) and device_type_id = '2' AND dev.status = 'ACTIVE'  AND dev.deleted_status = '0' and do.status='ACTIVE' and oc.org_name='"+depot_name+"' order by do.org_id";
					*/
							//System.out.println("two----------");
							activeDevice="select * from ( " + 
									"select  device_serial_number,oc.org_name,egt.etim_no gprsetm from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
									"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date ='"+ticketDate1+"' ) egt on egt.etim_no=d.device_serial_number " + 
									//"inner join shift_type st on st.shift_type_id=egt.shift_no " + 
									"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and do.status='ACTIVE' and org_id !=0 and oc.org_name='"+depot_name+"' " + 
									"group by device_serial_number order by do.org_id )a where gprsetm is null";
						}
						
						
						Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
						
						JSONArray array = new JSONArray();
						if(alertId=="Active" || alertId.equalsIgnoreCase("Active")){
						for(int i=0;i<aliasToValueMapList.size();i++){
							Map<String, Object> rs = aliasToValueMapList.get(i);
							JSONArray ja = new JSONArray();
							ja.add(i+1);
							ja.add(rs.get("device_serial_number"));
							ja.add(rs.get("org_name"));

//							ja.add(rs.get("total"));
							ja.add(rs.get("schedule_no"));
							ja.add(rs.get("shift_type_name"));
							ja.add(rs.get("vehicle_no"));
							array.add(ja);
						}
						}else {
							for(int i=0;i<aliasToValueMapList.size();i++){
								Map<String, Object> rs = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								ja.add(i+1);
								ja.add(rs.get("device_serial_number"));
								ja.add(rs.get("org_name"));

//								ja.add(rs.get("total"));
//								ja.add(rs.get("schedule_no"));
//								ja.add(rs.get("shift_type_name"));
//								ja.add(rs.get("vehicle_no"));
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
				public JSONObject getingenicodetailsdao(HttpServletRequest request,  
						String alertId) {

					JSONObject result = new JSONObject();
					Session session=null;
				/*	Common common1 = new Common();
					String ticketDate1=common1.getDateFromPicker(ticket_date);
					*/
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					String status=" is not null ";
					if(alertId.trim().equals("InActive")){
						status=" is null ";
					}
					String subqry=" CURDATE() ";
					String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				subqry="'"+regdate+"'";
			}
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
						String activeDevice="select count(*)tot,org_name from ( " + 
								"select  device_serial_number detm,egt.etim_no gprsetm,org_name from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
								"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = "+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
								"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and vendor_id=13  and oc.org_chart_id"+id+" and do.status='ACTIVE' and org_id !=0 group by device_serial_number order by do.org_id )"
										+ "a where gprsetm "+status+" group by org_name";
						
						Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
							
							JSONArray array = new JSONArray();
							for(int i=0;i<aliasToValueMapList.size();i++){
								Map<String, Object> rs = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								ja.add(i+1);
//								ja.add(rs.get("device_serial_number"));
								ja.add(rs.get("org_name"));
								
								if(alertId.trim().equals("InActive")){
									 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsfunctioningenico('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
								}else {
								
								 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal12'  onclick=javascript:jsfunctioningenico('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
							}
//								ja.add(rs.get("total"));
								//ja.add(rs.get("schedule_no"));
								array.add(ja);
							}
							result.put("aaData", array);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
					}
					return result;
				
				}

				
				public JSONObject getingenicodepot(
						HttpServletRequest request, String depot_name,
						String alertId) {
					JSONObject result = new JSONObject();
					Session session=null;

					String activeDevice="";
			
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					
					String subqry=" CURDATE() ";
					String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				subqry="'"+regdate+"'";
			}
			
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
						if(alertId=="Active" || alertId.equalsIgnoreCase("Active")){
							
					/*		 activeDevice="SELECT etim_no,org_name,schedule_no,st.shift_type_name,vehicle_no  FROM bmtcGprs.etim_gprs_ticket egt" +
									" join org_chart oc on egt.depot_id=oc.org_chart_id join shift_type st on st.shift_type_id=egt.shift_no" +
									" WHERE ticket_date = '"+ticketDate1+"' and oc.org_name='"+depot_name+"' group by etim_no order by egt.depot_id,egt.shift_no ";*/
						activeDevice="select * from ( " + 
								"select   device_serial_number,egt.etim_no gprsetm ,org_name,schedule_no,ifnull(st.shift_type_name,'')shift_type_name,vehicle_no from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
								"left join (select  distinct etim_no,shift_no,schedule_no,vehicle_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date ="+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
								"left join shift_type st on st.shift_type_id=egt.shift_no " + 
								"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0'   and vendor_id=13 and do.status='ACTIVE' and org_id !=0 and oc.org_name='"+depot_name+"' " + 
								"group by device_serial_number order by do.org_id )a where gprsetm is not null";
						//System.out.println("1st-------------");
						}else{
				
							activeDevice="select * from ( " + 
									"select  device_serial_number,oc.org_name,egt.etim_no gprsetm from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
									"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date ="+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
									//"inner join shift_type st on st.shift_type_id=egt.shift_no " + 
									"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and vendor_id=13  and do.status='ACTIVE' and org_id !=0 and oc.org_name='"+depot_name+"' " + 
									"group by device_serial_number order by do.org_id )a where gprsetm is null";
						}
						
						
						Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
						
						JSONArray array = new JSONArray();
						if(alertId=="Active" || alertId.equalsIgnoreCase("Active")){
						for(int i=0;i<aliasToValueMapList.size();i++){
							Map<String, Object> rs = aliasToValueMapList.get(i);
							JSONArray ja = new JSONArray();
							ja.add(i+1);
							ja.add(rs.get("device_serial_number"));
							ja.add(rs.get("org_name"));

							ja.add(rs.get("schedule_no"));
							ja.add(rs.get("shift_type_name"));
							ja.add(rs.get("vehicle_no"));
							array.add(ja);
						}
						}else {
							for(int i=0;i<aliasToValueMapList.size();i++){
								Map<String, Object> rs = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								ja.add(i+1);
								ja.add(rs.get("device_serial_number"));
								ja.add(rs.get("org_name"));


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
				public JSONObject getverifone(HttpServletRequest request) {
					JSONObject result = new JSONObject();
					int totalCountEtm=0;
					int totalactiveDevice=0;
					int totalinactive=0;
					int totatTemper=0;
					int totalDevice=0;
					int totrepair=0;
					Session session=null;
					Common common1 = new Common();
				//	String ticketDate1=common1.getDateFromPicker(ticket_date);
					
					
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					
					String subqry=" CURDATE() ";
					String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				subqry="'"+regdate+"'";
			}
					
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
						int count[] = new int[4];
						String status[] = new String[4];
						String totetm="select count(*)tot,sum(case when gprsetm is not null then 1 else 0 end)active,sum(case when gprsetm is  null then 1 else 0 end )  inactive from (" + 
								"select  device_serial_number detm,egt.etim_no gprsetm,parent_id from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
								"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = "+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
								"where  device_type_id = '2' AND d.status = 'ACTIVE'  and vendor_id=8  AND d.deleted_status = '0' and do.status='ACTIVE' and org_id !=0 and org_chart_id "+id+" group by device_serial_number order by do.org_id )a";
						Query query=session.createSQLQuery(totetm);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
							Map<String, Object> rs = aliasToValueMapList.get(0);
							
								//totalDevice=(Integer.parseInt(session.createSQLQuery(countQuery).uniqueResult().toString()));
							
								totalactiveDevice=Integer.parseInt(rs.get("active").toString());
								//totalinactive=totalDevice - totalactiveDevice;
								totalinactive=Integer.parseInt(rs.get("inactive").toString());
								//totrepair=Integer.parseInt(rs.get("inactive").toString());
										//(Integer)session.createSQLQuery(repair).uniqueResult();
								totalDevice=totalactiveDevice+totalinactive;//+totrepair
								//totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
								//printDuty.setBlockName(list.get("block_name").toString());
						

						JSONArray array = new JSONArray();
						
						int totalCount = 0;
						//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
							//count[0] =totalDevice;
							count[0] =totalactiveDevice;
							count[1] = totalinactive;
							//count[2] = totrepair;
							count[2] = totalDevice;
						
							//count[3]=totalGprs;
							//status[0] = "Total ETIM";
							status[0] = "Active";
							status[1] = "InActive";
							//status[2] = "Repair";
							status[2] = "Total ETIM";
							
							//status[3]=" ";			//}

						for (int i = 0; i < 3; i++) {
							JSONArray ja = new JSONArray();
							ja.add(count[i]);
							double per = Double.parseDouble(String.valueOf(count[i])) * 100
									/ totalDevice;
							ja.add(per);
							ja.add(status[i]);
						//	System.out.println(ja);
							array.add(ja);
						}
						result.put("aaData", array);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
					}
					return result;
				}
				public JSONObject getverifonedetailsdao(HttpServletRequest request,  
						String alertId) {

					JSONObject result = new JSONObject();
					Session session=null;
				/*	Common common1 = new Common();
					String ticketDate1=common1.getDateFromPicker(ticket_date);
					*/
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					String status=" is not null ";
					if(alertId.trim().equals("InActive")){
						status=" is null ";
					}
					
					String subqry=" CURDATE() ";
					String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				subqry="'"+regdate+"'";
			}
			
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
						String activeDevice="select count(*)tot,org_name from ( " + 
								"select  device_serial_number detm,egt.etim_no gprsetm,org_name from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
								"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = "+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
								"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and vendor_id=8  and oc.org_chart_id"+id+" and do.status='ACTIVE' and org_id !=0 group by device_serial_number order by do.org_id )"
										+ "a where gprsetm "+status+" group by org_name";
						
						Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
							
							JSONArray array = new JSONArray();
							for(int i=0;i<aliasToValueMapList.size();i++){
								Map<String, Object> rs = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								ja.add(i+1);
//								ja.add(rs.get("device_serial_number"));
								ja.add(rs.get("org_name"));
								 //ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsfunctionverifone('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
									if(alertId.trim().equals("InActive")){
										 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsfunctionverifone('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
									}else {
									
									 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal12'  onclick=javascript:jsfunctionverifone('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
								}
//								ja.add(rs.get("total"));
								//ja.add(rs.get("schedule_no"));
								array.add(ja);
							}
							result.put("aaData", array);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
					}
					return result;
				
				}

				
				public JSONObject getverifonedepot(
						HttpServletRequest request, String depot_name,
						String alertId) {
					JSONObject result = new JSONObject();
					Session session=null;

					String activeDevice="";
			
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					
					
					String subqry=" CURDATE() ";
					String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				subqry="'"+regdate+"'";
			}
			
			
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
						if(alertId=="Active" || alertId.equalsIgnoreCase("Active")){
							
					/*		 activeDevice="SELECT etim_no,org_name,schedule_no,st.shift_type_name,vehicle_no  FROM bmtcGprs.etim_gprs_ticket egt" +
									" join org_chart oc on egt.depot_id=oc.org_chart_id join shift_type st on st.shift_type_id=egt.shift_no" +
									" WHERE ticket_date = '"+ticketDate1+"' and oc.org_name='"+depot_name+"' group by etim_no order by egt.depot_id,egt.shift_no ";*/
						activeDevice="select * from ( " + 
								"select   device_serial_number,egt.etim_no gprsetm ,org_name,schedule_no,st.shift_type_name,vehicle_no from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
								"left join (select  distinct etim_no,shift_no,schedule_no,vehicle_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date ="+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
								"inner join shift_type st on st.shift_type_id=egt.shift_no " + 
								"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0'   and vendor_id=8 and do.status='ACTIVE' and org_id !=0 and oc.org_name='"+depot_name+"' " + 
								"group by device_serial_number order by do.org_id )a where gprsetm is not null";
						//System.out.println("1st-------------");
						}else{
				
							activeDevice="select * from ( " + 
									"select  device_serial_number,oc.org_name,egt.etim_no gprsetm from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
									"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date ="+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
									//"inner join shift_type st on st.shift_type_id=egt.shift_no " + 
									"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and vendor_id=8  and do.status='ACTIVE' and org_id !=0 and oc.org_name='"+depot_name+"' " + 
									"group by device_serial_number order by do.org_id )a where gprsetm is null";
						}
						
						
						Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
						
						JSONArray array = new JSONArray();
						if(alertId=="Active" || alertId.equalsIgnoreCase("Active")){
						for(int i=0;i<aliasToValueMapList.size();i++){
							Map<String, Object> rs = aliasToValueMapList.get(i);
							JSONArray ja = new JSONArray();
							ja.add(i+1);
							ja.add(rs.get("device_serial_number"));
							ja.add(rs.get("org_name"));

							ja.add(rs.get("schedule_no"));
							ja.add(rs.get("shift_type_name"));
							ja.add(rs.get("vehicle_no"));
							array.add(ja);
						}
						}else {
							for(int i=0;i<aliasToValueMapList.size();i++){
								Map<String, Object> rs = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								ja.add(i+1);
								ja.add(rs.get("device_serial_number"));
								ja.add(rs.get("org_name"));


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
				
				
				/*public JSONObject getetmpingdata(HttpServletRequest request,  
						String alertId) {
					System.out.println("doa-----------------------++++++++++++*******");

					JSONObject result = new JSONObject();
					Session session=null;
					Common common1 = new Common();
					String ticketDate1=common1.getDateFromPicker(ticket_date);
					
					String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					String status=" is not null ";
					if(alertId.trim().equals("InActive")){
						status=" is null ";
					}
					
					String subqry=" CURDATE() ";
					String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				subqry="'"+regdate+"'";
			}
			
					try {
						session = HibernateUtil.getSession("hibernate.cfg.xml");
						String activeDevice="select count(*)tot,org_name from ( " + 
								"select  device_serial_number detm,egt.etim_no gprsetm,org_name from device d inner join device_org do on do.device_id=d.device_id  inner join org_chart oc on do.org_id=oc.org_chart_id " + 
								"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = "+subqry+" ) egt on egt.etim_no=d.device_serial_number " + 
								"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0' and vendor_id=8  and oc.org_chart_id"+id+" and do.status='ACTIVE' and org_id !=0 group by device_serial_number order by do.org_id )"
										+ "a where gprsetm "+status+" group by org_name";
						
						Query query=session.createSQLQuery(activeDevice);
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
							List<Map<String, Object>> aliasToValueMapList = query.list();
							
							JSONArray array = new JSONArray();
							for(int i=0;i<aliasToValueMapList.size();i++){
								Map<String, Object> rs = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								ja.add(i+1);
//								ja.add(rs.get("device_serial_number"));
								ja.add(rs.get("org_name"));
								 //ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsfunctionverifone('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
									if(alertId.trim().equals("InActive")){
										 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsfunctionverifone('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
									}else {
									
									 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal12'  onclick=javascript:jsfunctionverifone('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
								}
//								ja.add(rs.get("total"));g
								//ja.add(rs.get("schedule_no"));
								array.add(ja);
							}
							result.put("aaData", array);
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
					}
					return result;
				
				}
*/			public JSONObject getetmpingdata(HttpServletRequest request,  
		String alertId) {
	JSONObject result = new JSONObject();

					List active=new ArrayList<String>();
					String depotdb="";
					String depotIp="";
					String user="";
					String password="";
					Connection connection=null;
					Statement stmt=null;
					Session session1 = null;
					ResultSet rs=null;
					Transaction transaction  = null;
					//HttpServletRequest request=ServletActionContext.getRequest();
					

					int count[] = new int[4];
					String status[] = new String[4];
			        String queryyy;
			        String queryyy1;
			        Date ss1 = new Date();
			        SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			        String runDateTime = sdf.format(ss1);
			        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			   
			        String regionTypeAjaxString = "";
			        
			        String orgtypeid = (String) request.getSession().getAttribute(
							"orgtypeid");
					String orgchartid = (String) request.getSession().getAttribute(
							"orgchartid");

					String id = "!=0";
					if (orgtypeid.equals("1")) {
						//id = "org_id!=0";
						id = " !=0";

					}
					if (orgtypeid.equals("3")) {

						//id = "org_id in('" + orgchartid + "')";
						id = " in('" + orgchartid + "')";
					}
					if (orgtypeid.equals("2")) {

						//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
						//		+ orgchartid + "')";
						id = " in(select org_chart_id as depotids from org_chart where parent_id='"
										+ orgchartid + "')";
					}
					String subqry=" CURDATE() ";
					String regdate=(String)request.getSession().getAttribute("regdate");
			if(regdate !=null) {
				subqry="'"+regdate+"'";
			}

			       try {
			        session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					 String realpath = ServletActionContext.getRequest()
								.getRealPath("/");
				//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
					 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id  "+id+"";
//						String depotdb=common.getDBResultStr(session1, sql1, "depotname");
						Query qry1=session1.createSQLQuery(sql1).
								addScalar("depotname")
								.addScalar("central_ip",Hibernate.STRING)
								.addScalar("central_uname")
								.addScalar("central_pwd");
						
						qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
							List<Map<String, Object>> aliasToValueMapList = qry1.list();	
					/*		String depotdb="";
							String depotIp="";
							String user="";
							String password="";*/
							
				/*			for (int j = 0; j < aliasToValueMapList.size(); j++) {
								Map<String, Object> aliasValue = aliasToValueMapList.get(j);
								 depotdb=aliasValue.get("depotname").toString();
								 depotIp=aliasValue.get("central_ip").toString();
								 user=aliasValue.get("central_uname").toString();
								 password=aliasValue.get("central_pwd").toString();
							}*/
						 Class.forName("com.mysql.jdbc.Driver");
						 for(int  i=0;i<aliasToValueMapList.size();i++) {
							 
								Map<String, Object> aliasValue = aliasToValueMapList.get(i);
								 depotdb=aliasValue.get("depotname").toString();
								 depotIp=aliasValue.get("central_ip").toString();
								 user=aliasValue.get("central_uname").toString();
								 password=aliasValue.get("central_pwd").toString();
						try {		 
						 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
						// connection = DriverManager.getConnection("jdbc:mysql://"+aliasValue.get("central_ip").toString()+":23306/" + aliasValue.get("depotname").toString() + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
								
						// System.out.println("connection........."+connection);
					 stmt = connection.createStatement();
			/*		 String sql ="select distinct device_serial_number from Waybill_Details wd  " + 
					 		"inner join device d on d.device_id=wd.ETM_No  " + 
					 		"where generated_Date="+subqry+" and wd.Status in ('online','Closed','AUDITED')";*/
					 String sql= "select distinct device_serial_number from Waybill_Details wd   " + 
					 "inner join device d on d.device_id=wd.ETM_No  " + 
					 "where generated_Date="+subqry+" and wd.Status in ('Closed','AUDITED') and (ETIM_Coll_Amt+Bag_Coll_Amt)>0 " + 
					 "union  " + 
					 "select distinct device_serial_number from Waybill_Details wd   " + 
					 "inner join device d on d.device_id=wd.ETM_No  " + 
					 "where generated_Date="+subqry+" and wd.Status='online' and (ETIM_Coll_Amt+Bag_Coll_Amt)>0";
					// System.out.println(sql);
					 rs = stmt.executeQuery(sql);
				      while (rs.next()) {
				    	  active.add(rs.getObject("device_serial_number").toString());
				      }
				      
								}catch (Exception e) {
								e.printStackTrace();	} }	
						/*	String activeDevice="select detm from ( " + 
									"select  distinct device_serial_number detm,egt.etim_no gprsetm from device d inner join device_org do on do.device_id=d.device_id   " + 
									"inner join org_chart oc on do.org_id=oc.org_chart_id  " + 
									"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date =  "+subqry+" ) egt on egt.etim_no=d.device_serial_number  " + 
									"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0'   and oc.org_chart_id !=0 and do.status='ACTIVE' and org_id !=0 " + 
									")v where gprsetm is not null";*/
						 
							String activeDevice= "select distinct etim_no  from bmtcGprs.etim_gprs_ticket egt " + 
						 "inner join org_chart oc on oc.org_chart_id=egt.depot_id " + 
						 "where ticket_date="+subqry+"   and px_total_amount !=0";
							Query qry11=session1.createSQLQuery(activeDevice);
						List<String> device=	qry11.list();
						int tot=active.size();
						active.removeAll(device);
						
						
						JSONArray array = new JSONArray();
						
						int totalCount = 0;
						//for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
							//count[0] =totalDevice;
							count[0] =tot-active.size();
							count[1] = active.size();
							//count[2] = totrepair;
							count[2] = tot;
						
							//count[3]=totalGprs;
							//status[0] = "Total ETIM";
							status[0] = "Pinging";
							status[1] = "Not Pinging";
							//status[2] = "Repair";
							status[2] = "Total ETIM";
							
							//status[3]=" ";
						//}

						for (int i = 0; i < 3; i++) {
							JSONArray ja = new JSONArray();
							ja.add(count[i]);
							double per = Double.parseDouble(String.valueOf(count[i])) * 100
									/ tot;
							ja.add(per);
							ja.add(status[i]);
							array.add(ja);
						}
						result.put("aaData", array);
						
						
			       }catch (Exception e) {
                             e.printStackTrace();			
}
			       return result;
}
//@Override
/*public boolean equals(Object o) {
	System.out.println("in equals method-----");
	if(this.device==(String)o) {
	
		System.out.println("in ture-----------");
		return true;
	}

else { 
	System.out.println("in false-----------");
	return false;
}
}*/
		

public JSONObject getetmpingdepot(HttpServletRequest request,  
		String alertId) {
	JSONObject result = new JSONObject();

	List active=new ArrayList<String>();

	List<Map<String, Object>> resumain=new ArrayList<Map<String,Object>>();
	String depotdb="";
	String depotIp="";
	String user="";
	String password="";
	Connection connection=null;
	Statement stmt=null;
	Session session1 = null;
	ResultSet rs=null;
	Transaction transaction  = null;
	//HttpServletRequest request=ServletActionContext.getRequest();
	

	int count[] = new int[4];
	String status[] = new String[4];
    String queryyy;
    String queryyy1;
    Date ss1 = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

    String regionTypeAjaxString = "";
    
    String orgtypeid = (String) request.getSession().getAttribute(
			"orgtypeid");
	String orgchartid = (String) request.getSession().getAttribute(
			"orgchartid");

	String id = "!=0";
	if (orgtypeid.equals("1")) {
		//id = "org_id!=0";
		id = " !=0";

	}
	if (orgtypeid.equals("3")) {

		//id = "org_id in('" + orgchartid + "')";
		id = " in('" + orgchartid + "')";
	}
	if (orgtypeid.equals("2")) {

		//id = "org_id in(select org_chart_id as depotids from org_chart where parent_id='"
		//		+ orgchartid + "')";
		id = " in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
	}
	
	String subqry=" CURDATE() ";
	String regdate=(String)request.getSession().getAttribute("regdate");
if(regdate !=null) {
subqry="'"+regdate+"'";
}

   try {
    session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 String realpath = ServletActionContext.getRequest()
				.getRealPath("/");
//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
	 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id  "+id+"";
//		String depotdb=common.getDBResultStr(session1, sql1, "depotname");
		Query qry1=session1.createSQLQuery(sql1).
				addScalar("depotname")
				.addScalar("central_ip",Hibernate.STRING)
				.addScalar("central_uname")
				.addScalar("central_pwd");
		
		qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qry1.list();	

			
		/*	String activeDevice="select detm from ( " + 
					"select  distinct device_serial_number detm,egt.etim_no gprsetm from device d inner join device_org do on do.device_id=d.device_id   " + 
					"inner join org_chart oc on do.org_id=oc.org_chart_id  " + 
					"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = "+subqry+" ) egt on egt.etim_no=d.device_serial_number  " + 
					"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0'   and oc.org_chart_id "+id+" and do.status='ACTIVE' and org_id !=0 " + 
					")v where gprsetm is not null";*/
			
			 
			String activeDevice= "select distinct etim_no  from bmtcGprs.etim_gprs_ticket egt " + 
		 "inner join org_chart oc on oc.org_chart_id=egt.depot_id " + 
		 "where ticket_date="+subqry+"   and px_total_amount !=0";
			
			
			Query qry11=session1.createSQLQuery(activeDevice);
		List<String> device=	qry11.list();
		
			
			
		 Class.forName("com.mysql.jdbc.Driver");
		 for(int  i=0;i<aliasToValueMapList.size();i++) {
			 
				Map<String, Object> aliasValue = aliasToValueMapList.get(i);
				 depotdb=aliasValue.get("depotname").toString();
				 depotIp=aliasValue.get("central_ip").toString();
				 user=aliasValue.get("central_uname").toString();
				 password=aliasValue.get("central_pwd").toString();
		try {		 
		 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
		// connection = DriverManager.getConnection("jdbc:mysql://"+aliasValue.get("central_ip").toString()+":23306/" + aliasValue.get("depotname").toString() + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
				
		// System.out.println("connection........."+connection);
	 stmt = connection.createStatement();
	/* String sql ="select distinct device_serial_number from Waybill_Details wd  " + 
	 		"inner join device d on d.device_id=wd.ETM_No  " + 
	 		"where generated_Date= "+subqry+" and wd.Status in ('online','Closed','AUDITED')";*/
	 String sql= "select distinct device_serial_number from Waybill_Details wd   " + 
			 "inner join device d on d.device_id=wd.ETM_No  " + 
			 "where generated_Date="+subqry+" and wd.Status in ('Closed','AUDITED') and (ETIM_Coll_Amt+Bag_Coll_Amt)>0 " + 
			 "union  " + 
			 "select distinct device_serial_number from Waybill_Details wd   " + 
			 "inner join device d on d.device_id=wd.ETM_No  " + 
			 "where generated_Date="+subqry+" and wd.Status='online' and (ETIM_Coll_Amt+Bag_Coll_Amt)>0";
	// System.out.println(sql);
	 rs = stmt.executeQuery(sql);
	 active.clear();
      while (rs.next()) {
    	 
    	  active.add(rs.getObject("device_serial_number").toString());
    
      }
	  int tot=active.size();
		active.removeAll(device);
		
		if(alertId.trim().equals("Pinging")){
			//System.out.println("in ping");
			if(active.size()!=0) {
				Map resu=new HashMap();
		resu.put("org_name","Depot"+depotdb.substring(1, 3));
		resu.put("count",tot-active.size());
		resumain.add(resu);
			}
		}else {
			if(active.size()!=0) {
				Map resu=new HashMap();
	 		resu.put("org_name","Depot"+depotdb.substring(1, 3));
	  		resu.put("count",active.size());
	  		resumain.add(resu);
			}
		}
		
		
      
				}catch (Exception e) {
				e.printStackTrace();	} }	
			
		 
		 
			JSONArray array = new JSONArray();
			for(int i=0;i<resumain.size();i++){
				Map<String, Object> rs1 = resumain.get(i);
				JSONArray ja = new JSONArray();
				ja.add(i+1);
//				ja.add(rs.get("device_serial_number"));
				ja.add(rs1.get("org_name"));
				 //ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsfunctionverifone('"+rs.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs.get("tot") + "</a>");
				/*	if(alertId.trim().equals("Not Pinging")){
						 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal13'  onclick=javascript:jsfunctionetmping('"+rs1.get("org_name")+"','"+alertId+"') title='Vehicle Details'>"+rs1.get("count") + "</a>");
					}else {*/
					
					 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal12'  onclick=javascript:jsfunctionetmping('"+rs1.get("org_name")+"','"+alertId.replace(" ", "")+"') title='Vehicle Details'>"+rs1.get("count") + "</a>");
				//}
//				ja.add(rs.get("total"));
				//ja.add(rs.get("schedule_no"));
				array.add(ja);
			}
			result.put("aaData", array);
		

		
		
   }catch (Exception e) {
             e.printStackTrace();			
}
   return result;
}


public JSONObject depotwisepingdetails(HttpServletRequest req, String depot, String alertId) {

	JSONObject result = new JSONObject();

	List active=new ArrayList<String>();

	List<Map<String, Object>> resumain=new ArrayList<Map<String,Object>>();
	Map<String,Map> group=new HashMap<String, Map>();
	String depotdb="";
	String depotIp="";
	String user="";
	String password="";
	Connection connection=null;
	Statement stmt=null;
	Session session1 = null;
	ResultSet rs=null;
	Transaction transaction  = null;
	//HttpServletRequest request=ServletActionContext.getRequest();
	

	int count[] = new int[4];
	String status[] = new String[4];
    String queryyy;
    String queryyy1;
    Date ss1 = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
    String runDateTime = sdf.format(ss1);
    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

    String regionTypeAjaxString = "";
    
 
	
	String subqry=" CURDATE() ";
	String regdate=(String)req.getSession().getAttribute("regdate");
if(regdate !=null) {
subqry="'"+regdate+"'";
}

   try {
    session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 String realpath = ServletActionContext.getRequest()
				.getRealPath("/");
//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
	 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depotname ='"+"D"+depot.substring(5, 7)+"'" ;
//		String depotdb=common.getDBResultStr(session1, sql1, "depotname");
		Query qry1=session1.createSQLQuery(sql1).
				addScalar("depotname")
				.addScalar("central_ip",Hibernate.STRING)
				.addScalar("central_uname")
				.addScalar("central_pwd");
		
		qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qry1.list();	

			
			/*String activeDevice="select detm from ( " + 
					"select  distinct device_serial_number detm,egt.etim_no gprsetm from device d inner join device_org do on do.device_id=d.device_id   " + 
					"inner join org_chart oc on do.org_id=oc.org_chart_id  " + 
					"left join (select  distinct etim_no FROM bmtcGprs.etim_gprs_ticket egt  WHERE ticket_date = "+subqry+" ) egt on egt.etim_no=d.device_serial_number  " + 
					"where  device_type_id = '2' AND d.status = 'ACTIVE'  AND d.deleted_status = '0'   and oc.org_name='"+depot.substring(0, 5)+"-"+depot.substring(5, 7)+"' and do.status='ACTIVE' and org_id !=0 " + 
					")v where gprsetm is not null";*/
			
			 
			String activeDevice= "select distinct etim_no  from bmtcGprs.etim_gprs_ticket egt " + 
		 "inner join org_chart oc on oc.org_chart_id=egt.depot_id " + 
		 "where ticket_date="+subqry+"   and px_total_amount !=0  and oc.org_name='"+depot.substring(0, 5)+"-"+depot.substring(5, 7)+"' " ; 
			
			Query qry11=session1.createSQLQuery(activeDevice);
		List<String> device=	qry11.list();
		
			
			
		 Class.forName("com.mysql.jdbc.Driver");
		 for(int  i=0;i<aliasToValueMapList.size();i++) {
			 
				Map<String, Object> aliasValue = aliasToValueMapList.get(i);
				 depotdb=aliasValue.get("depotname").toString();
				 depotIp=aliasValue.get("central_ip").toString();
				 user=aliasValue.get("central_uname").toString();
				 password=aliasValue.get("central_pwd").toString();
		try {		 
		 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
		 //connection = DriverManager.getConnection("jdbc:mysql://"+aliasValue.get("central_ip").toString()+":23306/" + aliasValue.get("depotname").toString() + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");
				
		// System.out.println("connection........."+connection);
	 stmt = connection.createStatement();
	/* String sql ="select distinct device_serial_number from Waybill_Details wd  " + 
	 		"inner join device d on d.device_id=wd.ETM_No  " + 
	 		"where generated_Date= "+subqry+" and wd.Status in ('online','Closed','AUDITED')";*/
	/* String sql="select distinct device_serial_number,schedule_number_name,license_number,shift_type_name from Waybill_Details wd  " + 
	 		"inner join device d on d.device_id=wd.ETM_No  " + 
	 		"inner join vehicle v on v.vehicle_id=wd.Vehicle_No " + 
	 		"inner join form_four ff on ff.form_four_id=wd.schedule_No " + 
	 		"inner join shift_type st on st.shift_type_id=wd.Shift_Type " + 
	 		"where generated_Date="+subqry+" and wd.Status in ('online','Closed','AUDITED')";*/
	 
	 
	 String sql= "select distinct device_serial_number,schedule_number_name,schedule_No,waybill_No,  " + 
	 		"license_number,shift_type_name   " + 
	 		"from Waybill_Details wd    " + 
	 		"inner join device d on d.device_id=wd.ETM_No   " + 
	 		"left join vehicle v on v.vehicle_id=wd.Vehicle_No  " + 
	 		"left join form_four ff on ff.form_four_id=wd.schedule_No  " + 
	 		"left join shift_type st on st.shift_type_id=wd.Shift_Type   " + 
	 		"where generated_Date="+subqry+" and wd.Status in ('online','Closed','AUDITED') and (ETIM_Coll_Amt+Bag_Coll_Amt)>0  " + 
	 		"union   " + 
	 		"select distinct device_serial_number,schedule_number_name,schedule_No,waybill_No,  " + 
	 		"license_number,shift_type_name   " + 
	 		"from Waybill_Details wd    " + 
	 		"inner join device d on d.device_id=wd.ETM_No   " + 
	 		"left join vehicle v on v.vehicle_id=wd.Vehicle_No  " + 
	 		"left join form_four ff on ff.form_four_id=wd.schedule_No  " + 
	 		"left join shift_type st on st.shift_type_id=wd.Shift_Type   " + 
	 		"where generated_Date="+subqry+" and wd.Status='online'";
	System.out.println(sql);
	 rs = stmt.executeQuery(sql);
	 active.clear();
      while (rs.next()) {
    	Map mm=new HashMap();
    	 mm.put("device_serial_number",rs.getObject("device_serial_number"));
    	 mm.put("org_name","Depot-"+depotdb.substring(1, 3));
    	 mm.put("schedule_number_name",rs.getObject("schedule_number_name"));
    	 mm.put("shift_type_name",rs.getObject("shift_type_name"));
    	 mm.put("license_number",rs.getObject("license_number"));
    group.put(rs.getObject("device_serial_number").toString(), mm);
      }
    //System.out.println("devvice -----------"+device.size());
    //System.out.println("dev--------------"+group.size());
    if(alertId.equals("Pinging")) {
 HashMap mmd=new HashMap(group);

    mmd.keySet().removeAll(device);
    group.keySet().removeAll(mmd.keySet());
   // System.out.println("mm111 -----------"+mmd.size());
    //System.out.println("dev111--------------"+group.size());
    } else {
    	group.keySet().removeAll(device);
        //System.out.println("mm222 -----------"+mmd.size());
        //System.out.println("dev222--------------"+group.size());
    }
      

		
		
      
				}catch (Exception e) {
				e.printStackTrace();	} }	
			List <Map>all=new ArrayList<Map>();
		 for ( Map.Entry<String, Map> entry : group.entrySet()) {
			    String key = entry.getKey();
			    Map tab = entry.getValue();
			  all.add(tab);
			}
		 
			JSONArray array = new JSONArray();
			for(int i=0;i<all.size();i++){
				//System.out.println("insied-----------------------------");
				Map<String, Object> rs1 = all.get(i);
				JSONArray ja = new JSONArray();
				ja.add(i+1);
				ja.add(rs1.get("device_serial_number"));
				ja.add(rs1.get("org_name"));

				ja.add(rs1.get("schedule_number_name"));
				ja.add(rs1.get("shift_type_name"));
				ja.add(rs1.get("license_number"));
				array.add(ja);
			}
			result.put("aaData", array);
		

		
		
   }catch (Exception e) {
             e.printStackTrace();			
}
   return result;

}




}
	
