package com.trimax.its.report.dao;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.trimax.its.util.HibernateUtil;


public class CancellationKMReportDao {
	public static String rbKey = String.valueOf(getSysParameterForVts());

	@SuppressWarnings("unchecked")
	public JSONObject getDataForKMCancellationReport(int total,HttpServletRequest request, String depotId,String date){
		JSONObject result = new JSONObject();
		try{
			int depot=Integer.parseInt(depotId);
			model.jaxb.xml.trimax.com.VtsResponse5 vehicleresult = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			vehicleresult = port.webGet20PerCan(depot, date, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				// Calling vehicle result
				//System.out.println("Depot"+vehicleresult.getVtsDatamodel().get(i).getDepotName());
				ja.add(i+1);
//				ja.add(vehicleresult.getVtsDatamodel().get(i).getScheduleNumberName());
				String schedule_name=vehicleresult.getVtsDatamodel().get(i)
						.getScheduleNumberName();
				int formFour_id= getScheduleFormFourId(schedule_name);

//				System.out.println("form form id"+formFour_id);

			
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal4'  onclick=javascript:viewCancelledKMDetails('"
						+ formFour_id
						+ "','"
						+ date+"','"+depotId+"') >"
						+ vehicleresult.getVtsDatamodel().get(i)
								.getScheduleNumberName()
						+ "</a>");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEPOTNAME());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getSCHDISTANCE());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getPisDistance());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getCanPer());
			    array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
	public int getScheduleFormFourId(String scheduleName){
		int id=0;
		Session ses=null;
		try{
			 ses=HibernateUtil.getSession("hibernate.cfg.xml");
			 Query query2 = ses.createSQLQuery("SELECT form_four_id  FROM `form_four` WHERE " +
			 		"`schedule_number_name` LIKE '%"+scheduleName+"%' AND `deleted_status` = '0' AND `current_status` = 'ACTIVE'  limit 1")
						.addScalar("form_four_id",Hibernate.STRING);
			 if (query2.list().size() > 0) {
					id = Integer.parseInt(query2.uniqueResult().toString());
				}
//			 id= (Integer)query2.uniqueResult();	
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally{
		
	}
		return id;
}
	
		public int getTotalCancelRecord(HttpServletRequest request, String depotId,String date)	{
		int total=0;	
		try{
			int depot=Integer.parseInt(depotId);
			model.jaxb.xml.trimax.com.VtsResponse5 vehicleresult = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			vehicleresult = port.webGet20PerCan(depot, date, rbKey);
			total=vehicleresult.getVtsDatamodel().size();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return total;
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