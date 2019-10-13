package com.trimax.its.device.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;

import model.jaxb.xml.trimax.com.VtsResponse;

public class DVPDashboardDao {

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
			// System.out.println("Start Time"+new Date());
			String orgtypeid = (String) request.getSession().getAttribute("orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute("orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='" + orgchartid + "')";
			}

			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO process result here

			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
//				alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
			VtsResponse alertresult = port.webGetTotalDevicesDataForDashboard(rbKey, id);
			JSONArray array = new JSONArray();
			System.out.println("alertresult.getVtsDatamodel().size()====" + alertresult.getVtsDatamodel().size());
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				com.trimax.ws.vts.vtsutility.VtsDataModel rs = alertresult.getVtsDatamodel().get(i);
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(rs.getDEVICEID().toString());
				ja.add(rs.getOrgName().toString());
				ja.add(rs.getLICENCENUMBER().toString());

				array.add(ja);

				// totalGprs=(Integer.parseInt(list.get("gprssig").toString()));
				// printDuty.setBlockName(list.get("block_name").toString());
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
	public JSONObject getDataForDVPChart(int total, HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String ticket_date) {
		JSONObject result = new JSONObject();
		int totalCountDevice = 0;
		int totalCWS = 0;
		int totalDWS = 0;
		int totalRWS = 0;
		int totalCC = 0;
		int totalDocking = 0;
		int totalAccident = 0;
		int totalScrapProposal = 0;
		int totalBreakdown = 0;
		int totalAd = 0;
		int totalPoliceStation = 0;
		int totalOperableVeh = 0;
		int totalNotOperable = 0;

		Session session = null;
		int count[] = new int[13];
		String status[] = new String[13];
		Common common1 = new Common();
		String ticketDate1 = common1.getDateFromPicker(ticket_date);

		try {
			// System.out.println("Start Time"+new Date());
			String orgtypeid = (String) request.getSession().getAttribute("orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute("orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='" + orgchartid + "')";
			}

			session = HibernateUtil.getSession("hibernate.cfg.xml");

			// Query To Get DVP Header Information..

			JSONArray array = new JSONArray();

			Query qryy = session.createSQLQuery(
					"select (select count(*) as A from vehicle where deleted_status=0 and status='active' and cause_status !='s')  AS Total, "
							+ "(select count(*) as A from vehicle where deleted_status=0 and status='active' and cause_status ='N')  AS TotalOperable,"
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='CW')  AS CWS, "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='DW') AS DWS, "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='A') AS Accident, "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='B') AS breakdown , "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='RW') AS roadworth, "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='SP') AS scrapProposal,  "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='AD') AS authorizedDealer,  "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='PS') AS policeStation,  "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='D') AS Docking ,  "
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS ='CC' ) AS CC ,"
							+ "(select count(*) as A from vehicle  where deleted_status=0 and status='active' and CAUSE_STATUS in ('CW','DW','A','B','RW','SP','PS','AD','D','CC'))  AS totalNotOp  ")
					.addScalar("Total", Hibernate.INTEGER).addScalar("CWS", Hibernate.INTEGER)
					.addScalar("DWS", Hibernate.INTEGER).addScalar("Accident", Hibernate.INTEGER)
					.addScalar("breakdown", Hibernate.INTEGER).addScalar("roadworth", Hibernate.INTEGER)
					.addScalar("scrapProposal", Hibernate.INTEGER).addScalar("authorizedDealer", Hibernate.INTEGER)
					.addScalar("policeStation", Hibernate.INTEGER).addScalar("totalNotOp", Hibernate.INTEGER)
					.addScalar("Docking", Hibernate.INTEGER).addScalar("CC", Hibernate.INTEGER)
					.addScalar("TotalOperable", Hibernate.INTEGER);
			System.out.println("qryyyyy" + qryy);
			qryy.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = qryy.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				totalCountDevice = (Integer.parseInt(rs.get("Total").toString()));
				totalCWS = (Integer.parseInt(rs.get("CWS").toString()));
				totalDWS = (Integer.parseInt(rs.get("DWS").toString()));
				totalRWS = (Integer.parseInt(rs.get("roadworth").toString()));
				totalCC = (Integer.parseInt(rs.get("CC").toString()));
				totalDocking = (Integer.parseInt(rs.get("Docking").toString()));
				totalAccident = (Integer.parseInt(rs.get("Accident").toString()));
				totalScrapProposal = (Integer.parseInt(rs.get("scrapProposal").toString()));
				totalBreakdown = (Integer.parseInt(rs.get("breakdown").toString()));
				totalAd = (Integer.parseInt(rs.get("authorizedDealer").toString()));
				totalPoliceStation = (Integer.parseInt(rs.get("policeStation").toString()));
				totalOperableVeh = (Integer.parseInt(rs.get("TotalOperable").toString()));
				totalNotOperable = (Integer.parseInt(rs.get("totalNotOp").toString()));
			}

			count[0] = totalOperableVeh;
			count[1] = totalCWS;
			count[2] = totalDWS;
			count[3] = totalCC;
			count[4] = totalDocking;
			count[5] = totalAccident;
			count[6] = totalScrapProposal;
			count[7] = totalBreakdown;
			count[8] = totalAd;
			count[9] = totalPoliceStation;
			count[10] = totalRWS;
			count[11] = totalNotOperable;

			count[12] = totalCountDevice;

			status[0] = "Total Operable";
			status[1] = "CWS";
			status[2] = "DWS";
			status[3] = "CC";
			status[4] = "Docking";
			status[5] = "Accident";
			status[6] = "Scrap Proposal";
			status[7] = "Breakdown";
			status[8] = "Authorized Dealer";
			status[9] = "Police Station";
			status[10] = "Road Worthy";
			status[11] = "Not Operable";
			status[12] = "Total Vehicle";

			double totalPer = 0.0;

			for (int i = 0; i < 13; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100 / totalCountDevice;
				System.out.println("per-------" + per);
				if (per < 2.0 ) {
					per = per+2.0;
					totalPer +=2.0;
				}
				if(per > 80) {
					per = per-totalPer;
				}
				System.out.println("per-------" + per);

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
	public JSONObject getDataForVehicleStatus(int total,HttpServletRequest request, String cols, String dir,String alertId) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common1 = new Common();
		String status=""; 
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
			JSONArray array = new JSONArray();
			session = HibernateUtil.getSession("");
		 
			if(alertId.equalsIgnoreCase("Total Operable")) {
				status ="='N'";
			}else if(alertId.equalsIgnoreCase("CWS")) {
				status ="='CW'";
			}else if(alertId.equalsIgnoreCase("DWS")) {
				status ="='DW'";
			}else if(alertId.equalsIgnoreCase("Accident")) {
				status ="='A'";
			}else if(alertId.equalsIgnoreCase("Breakdown")) {
				status ="='B'";
			}else if(alertId.equalsIgnoreCase("Scrap Proposal")) {
				status ="='SP'";
			}else if(alertId.equalsIgnoreCase("Police Station")) {
				status ="='PS'";
			}else if(alertId.equalsIgnoreCase("Docking")) {
				status ="='D'";
			}else if(alertId.equalsIgnoreCase("Road Worthy")) {
				status ="='RW'";
			} else if(alertId.equalsIgnoreCase("Authorized Dealer")) {
				status ="='AD'";
			}else if(alertId.equalsIgnoreCase("Not Operable")) {
				status ="in('CW','DW','A','B','SP','PS','D','RW','AD','CC')";
		
			}else if(alertId.equalsIgnoreCase("CC")) {
				status ="='CC'";
			}
			
			String statusQuery=" select license_number,cause_status,device_serial_number,vd.status,oc.org_name from vehicle v " + 
					"left join vehicle_device vd on v.vehicle_id=vd.vehicle_id and vd.status='active' " + 
					"left join device d on vd.device_id=d.device_id  and d.deleted_status=0 and d.status='Active' " + 
					"left join sim_vtu sv on d.device_id=sv.device_id and sv.status='Active' " + 
					"left join simcard sc on sv.sim_id=simcard_id and sc.deleted_status=0 and sc.status='Active' " + 
					"inner join org_chart oc on v.org_chart_id=oc.org_chart_id and oc.deleted_status=0 " + 
					"where v.deleted_status=0 and v.status='Active' and cause_status "+status+" " + 
					"group by license_number " + 
					"order by oc.org_name  " ;

		Query queryy = session.createSQLQuery(statusQuery).addScalar("license_number").addScalar("device_serial_number").addScalar("org_name").addScalar("cause_status");
   			
		queryy.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = queryy.list();
					  for (int i = 0; i < aliasToValueMapList.size(); i++) {
						  Map<String, Object> rs = aliasToValueMapList.get(i);
						 JSONArray ja = new JSONArray();
						 ja.add(i+1);
						ja.add(rs.get("license_number"));
						ja.add(rs.get("device_serial_number"));
						ja.add(rs.get("org_name"));
						ja.add(rs.get("cause_status"));

						array.add(ja);
					
					  }
					
//				}
								
			  result.put("aaData", array);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
}
