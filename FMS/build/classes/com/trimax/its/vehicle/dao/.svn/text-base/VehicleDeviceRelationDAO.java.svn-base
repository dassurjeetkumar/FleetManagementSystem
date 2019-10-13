package com.trimax.its.vehicle.dao;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import model.jaxb.xml.trimax.com.VtsResponse6;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.ws.vts.vtsutility.WayBillDetails;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class VehicleDeviceRelationDAO {
	
	@SuppressWarnings("unchecked")
	public JSONObject getVehicleDeviceRelation(int j,
			HttpServletRequest req, String string, String string2,String orgchartid) {

		JSONObject result = new JSONObject();
		Common common = new Common();
		Session session=null;
		
		try {
			session =HibernateUtil.getSession("");
			Query qryy = session.createSQLQuery("SELECT  license_number,device_serial_number,E.phone_number,cause_status,org.org_name  FROM its.vehicle_device A INNER JOIN its.vehicle B    " +
					" ON A.vehicle_id = B.vehicle_id  INNER JOIN its.device C  ON C.device_id = A.device_id INNER JOIN " +
					" its.sim_vtu D ON D.device_id=A.device_id  INNER JOIN its.simcard E ON E.simcard_id=D.sim_id  " +
					" INNER JOIN org_chart org on org.org_chart_id=B.org_chart_id and E.deleted_status=0 AND C.deleted_status=0 " +
					" AND B.deleted_status=0 AND B.cause_status !='s' and "+orgchartid+" and A.status='ACTIVE' and D.status='ACTIVE' and C.status='ACTIVE' " +
					" and B.status='ACTIVE' order by org.org_name ").addScalar("license_number").addScalar("device_serial_number").addScalar("phone_number").addScalar("cause_status").addScalar("org_name");
			System.out.println("qryyyyy"+qryy);
			qryy.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = qryy.list();
			JSONArray array = new JSONArray();
			for (int l = 0; l < aliasToValueMapList.size(); l++) {
				Map<String, Object> rs = aliasToValueMapList.get(l);
				 JSONArray ja = new JSONArray();
				 ja.add(l+1);
				ja.add(rs.get("license_number"));
				ja.add(rs.get("device_serial_number"));
				ja.add(rs.get("phone_number"));
				ja.add(rs.get("org_name"));
				ja.add(rs.get("cause_status"));
				
				array.add(ja);
	
			}
//			System.out.println("now xall");
//			WsUtil_Service service = new WsUtil_Service();
//			WsUtil port = service.getWsUtilPort();
//			// TODO initialize WS operation arguments here
//			// Query To Get Schedule Header Information..
//			VtsResponse6 alertresult = port.getVehicleDeviceRelation(rbKey);
//			JSONArray array = new JSONArray();
//			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//				 JSONArray ja = new JSONArray();
//				
//					ja.add("<input type='checkbox' class='group-checkable' id='"+ alertresult.getWaybillDetails().get(i).getId()+ "' value='"+ alertresult.getWaybillDetails().get(i).getId() +"'/>");
//					
//					ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
//					ja.add(alertresult.getWaybillDetails().get(i).getDEVICEID());
//					ja.add(alertresult.getWaybillDetails().get(i).getSimNumber());
//					ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
//					ja.add(alertresult.getWaybillDetails().get(i).getSTATUS());
//					
//					array.add(ja);
//				 
//				 }
				 result.put("aaData", array);
				 } catch (Exception ex) {
				 ex.printStackTrace();
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
	
	
	
}
