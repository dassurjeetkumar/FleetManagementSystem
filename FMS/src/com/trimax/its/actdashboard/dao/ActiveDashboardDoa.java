package com.trimax.its.actdashboard.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;


public class ActiveDashboardDoa {

	
	public static String rbKey = String.valueOf(getSysParameterForVts());
	
	private static ActiveDashboardDoa singleton = new ActiveDashboardDoa( );
	private ActiveDashboardDoa() {
		// TODO Auto-generated constructor stub
	}
	public static ActiveDashboardDoa getInstance(){
		if(singleton==null){
			
			singleton = new ActiveDashboardDoa();
		}
		singleton.hashCode();
		return singleton;
	}
	
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
	
	public JSONObject getDataForAllDevices(HttpServletRequest request) {
			JSONObject result = new JSONObject();
//			int totalCount = 0;
	
				Session session =null;
		try {
			String sql = "";
			session =  HibernateUtil.getSession("hibernate.cfg.xml");
			
			sql = "select device_serial_number,license_number,org_name,vendor_name from device d inner join vehicle_device vd  on d.device_id=vd.device_id "+
                  " inner join vehicle v on vd.vehicle_id = v.vehicle_id  "+
                  " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
                 " inner join vendor on d.vendor_id = vendor.vendor_id "+
                 " where d.status='Active' "+ 
                " and d.deleted_status=0  and vd.status='Active'  and v.status='Active' and v.deleted_status=0 group by device_serial_number order by org_name ";

			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();

		  JSONArray array = new JSONArray();
		  for (int i = 0; i < aliasToValueMapList.size(); i++) {
			 Map<String, Object> rs = aliasToValueMapList.get(i);
			 JSONArray ja = new JSONArray();

			 ja.add(i + 1);
			 ja.add(rs.get("device_serial_number").toString());
			 ja.add(rs.get("license_number").toString());
			 ja.add(rs.get("org_name").toString());
			 ja.add(rs.get("vendor_name").toString());
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
	public JSONObject getDataForDeviceChartDetails(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String orgTypeId,
			String orgChartId) {
		JSONObject result = new JSONObject();
		try {

			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
//			System.out.println("orgtypeid..........." + orgtypeid
//					+ "orgchartid................." + orgchartid);
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
	

			int count[] = new int[7];
			String status[] = new String[7];

			JSONArray array = new JSONArray();
			// Implementing WebService For Vehicle Count.... dated 02-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port.getVehicleTotal(rbKey, id);

			int totalCount = 0;
			for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {

				totalCount = Integer.parseInt(vehicleCount.getVtsDatamodel()
						.get(i).getActive())
						+ Integer.parseInt(vehicleCount.getVtsDatamodel()
								.get(i).getInactive())
						+ vehicleCount.getVtsDatamodel().get(i)
								.getONINTBATTERY()
								+vehicleCount.getVtsDatamodel().get(i).getWorkShopVehicle()
								+vehicleCount.getVtsDatamodel().get(i).getDWVehicle()
								+vehicleCount.getVtsDatamodel().get(i).getScrapVehicle();
				count[0] = Integer.parseInt(vehicleCount.getVtsDatamodel()
						.get(i).getActive());
				count[1] = Integer.parseInt(vehicleCount.getVtsDatamodel()
						.get(i).getInactive());
//				count[2] = vehicleCount.getVtsDatamodel().get(i)
//						.getONINTBATTERY();
//				count[3] = vehicleCount.getVtsDatamodel().get(i).getWorkShopVehicle();
//				count[4] = vehicleCount.getVtsDatamodel().get(i).getDWVehicle();
//				count[5] = vehicleCount.getVtsDatamodel().get(i).getScrapVehicle();
				count[2] = vehicleCount.getVtsDatamodel().get(i).getONINTBATTERY();
				count[4] = vehicleCount.getVtsDatamodel().get(i)
						.getWorkShopVehicle();
				count[3] = vehicleCount.getVtsDatamodel().get(i).getDWVehicle();
				count[4] = vehicleCount.getVtsDatamodel().get(i)
						.getWorkShopVehicle();
//				count[4] = vehicleCount.getVtsDatamodel().get(i).getONINTBATTERY();
				count[5] = vehicleCount.getVtsDatamodel().get(i).getScrapVehicle();
				
				count[6] = totalCount;
				status[0] = "Trimax";
				status[1] = "Hanover";
				status[2] = "Volvo";

				status[3] = "Total Device";
			}

			for (int i = 0; i < 4; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalCount;
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
			String fromDate, String tillDate,String ticket_date,String alertId) {
		JSONObject result = new JSONObject();
		Session session=null;
		Common common1 = new Common();
		String ticketDate1=common1.getDateFromPicker(ticket_date);
		String activeDevice="";
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			if(alertId.equalsIgnoreCase("ACTIVE")){
				 activeDevice="select device_serial_number,license_number,org_name from device d inner join vehicle_device vd " +
						" on d.device_id=vd.device_id "+
                         " inner join vehicle v on vd.vehicle_id = v.vehicle_id "+
                         " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
                        " where d.status='Active' and d.deleted_status=0 and vendor_id=6 and vd.status='Active' " +
                        " and v.status='Active' and v.deleted_status=0 order by org_name ";
			}else if(alertId.equalsIgnoreCase("NRD")){
				 activeDevice="select device_serial_number,license_number,org_name from device d inner join vehicle_device vd " +
							" on d.device_id=vd.device_id "+
	                         " inner join vehicle v on vd.vehicle_id = v.vehicle_id "+
	                         " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
	                        " where d.status='Active' and d.deleted_status=0 and vendor_id=9 and vd.status='Active' " +
	                        " and v.status='Active' and v.deleted_status=0 order by org_name ";
			}else {
//				System.out.println("in volvo");
				 activeDevice="select device_serial_number,license_number,org_name from device d inner join vehicle_device vd " +
							" on d.device_id=vd.device_id "+
	                         " inner join vehicle v on vd.vehicle_id = v.vehicle_id "+
	                         " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
	                        " where d.status='Active' and d.deleted_status=0 and vendor_id=10 and vd.status='Active' " +
	                        " and v.status='Active' and v.deleted_status=0 order by org_name ";
			}
				Query query=session.createSQLQuery(activeDevice);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);		
				List<Map<String, Object>> aliasToValueMapList = query.list();
			
			JSONArray array = new JSONArray();
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(i+1);
				ja.add(rs.get("device_serial_number"));
				ja.add(rs.get("license_number"));
				ja.add(rs.get("org_name"));
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
	
	
	

}
