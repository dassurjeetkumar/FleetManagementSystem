package com.trimax.its.vts.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;



public class VTUDeviceDetailsDao {
	
//	public int getTotalRecords(HttpServletRequest request, String deviceId,String date1,String date2,int count)	{
//		System.out.println("getTotal record-----");
//			int total=0;	
//			try{
//				
//				model.jaxb.xml.trimax.com.VtsResponse5 deviceResult = null;
//				com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//				com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
//				deviceResult=port.webHourlyCount(date1, date2, deviceId,count, rbKey);
//				total=deviceResult.getVtsDatamodel().size();
//			}catch(Exception e){
//				e.printStackTrace();
//			}finally{
//			}
//			return total;
//}
	public static String rbKey = String.valueOf(getSysParameterForVts());
	
	@SuppressWarnings("unchecked")
	public JSONObject getDeviceRecord(HttpServletRequest request,String deviceId,String date1,String date2,int count){
		JSONObject result = new JSONObject();
		
		try{
			model.jaxb.xml.trimax.com.VtsResponse5 deviceResult = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			deviceResult=port.webHourlyCount(date1, date2, deviceId,count, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < deviceResult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(deviceResult.getVtsDatamodel().get(i).getDEVICEID());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getPKTHEADER());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getPACKETCODE());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getMISCBYTES());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getIGNSTATUS());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getSIGNALSTRENGTH());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getNOSATELLITEINVIEW());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getEXTBATTERYVOLTAGE());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getINTERNALBATTERYVTG());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getSPEEDKMPH());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getLAT());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getLONGITUDE());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getISTDATE());
			    ja.add(deviceResult.getVtsDatamodel().get(i).getCREATEDDATE());
			    array.add(ja);
			}
			result.put("aaData", array);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return result;
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
	
}
