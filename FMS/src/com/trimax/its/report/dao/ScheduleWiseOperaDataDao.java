package com.trimax.its.report.dao;


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

	public class ScheduleWiseOperaDataDao {
		
		public int getTotalRecords(HttpServletRequest request,String fromdate,String todate,String depot) {
			int cnt=0;
			Session session = HibernateUtilVtu.getSession("");
		 
			try{
				String sql;
				
				sql="SELECT `schedule_no`, `TotalTrips`, `OperatedTrip`, `CancelTrips`, (conductor_Total_Bata+driver_Total_Bata) SOT, " +
						"generated_Date FROM `sot` WHERE `org_chart_id` = '"+depot+"' AND `generated_Date` between  '"+fromdate+"' and '"+todate+"' ";				
				String search_term1 = request.getParameter("sSearch");
				if (search_term1 != null && !search_term1.equals("")) {
					String search_term = request.getParameter("sSearch").trim();
					sql += " and  schedule_no like '%"+search_term+"%'";  
					sql += " OR TotalTrips like '%"+search_term+"%'";
					sql += " OR OperatedTrip like '%"+search_term+"%'";
					sql += " OR CancelTrips like '%"+search_term+"%'";
//					sql += " OR generated_Date like '%"+search_term+"%'";
					
				}
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				 cnt =	aliasToValueMapList.size();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
//				if (session != null) {
//					session.close();
//				}
			}
			return cnt;
		
	}
		/*public JSONObject getSchedulewiseOperData(int total,HttpServletRequest request,String fromdate,String todate,String depot){
			JSONObject result = new JSONObject();
			Session session = HibernateUtilVtu.getSession("");
			try{
				String sql;
				sql="SELECT `schedule_no`, `TotalTrips`, `OperatedTrip`, `CancelTrips`, (conductor_Total_Bata+driver_Total_Bata) SOT, " +
						"generated_Date FROM `sot` WHERE `org_chart_id` = '"+depot+"' AND `generated_Date` between  '"+fromdate+"' and '"+todate+"' ";
				
				String search_term = request.getParameter("sSearch");
				if (search_term != null && !search_term.equals("")) {
					search_term=search_term.trim();
					sql += " and  schedule_no like '%"+search_term+"%'";
					sql += " OR TotalTrips like '%"+search_term+"%'";
					sql += " OR OperatedTrip like '%"+search_term+"%'";
					sql += " OR CancelTrips like '%"+search_term+"%'";
//					sql += " OR generated_Date like '%"+search_term+"%'";
					
				}
				
//				sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
				
			  	 Query query = session.createSQLQuery(sql)
			  			.addScalar("schedule_no", Hibernate.STRING)
							 .addScalar("TotalTrips", Hibernate.INTEGER)
							 .addScalar("OperatedTrip", Hibernate.INTEGER)
							 .addScalar("CancelTrips", Hibernate.INTEGER)
			  	 .addScalar("SOT", Hibernate.DOUBLE);
//			  	 .addScalar("generated_Date", Hibernate.STRING);
				    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();	
					
			    	JSONArray array = new JSONArray();
			    	
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						int j=i+1;
						
						JSONArray ja = new JSONArray();
						ja.add(i+1);
						ja.add(rs.get("schedule_no").toString());
						ja.add(rs.get("TotalTrips").toString());
						ja.add(rs.get("OperatedTrip").toString());
						ja.add(rs.get("CancelTrips").toString());
						ja.add(rs.get("SOT").toString());
						ja.add("");
						ja.add("");
						ja.add("");
						ja.add("");
						ja.add("");
						ja.add("");
						ja.add("");
						ja.add("");
						
//						ja.add(rs.get("generated_Date").toString());
						array.add(ja);
						
					}
					
					int cnt=0;
					result.put("aaData", array);

				 cnt=getTotalRecords(request,fromdate,todate,depot);//getTotalRecordsForCount(search_term3);
				 result.put("iTotalRecords",cnt);
					
					result.put("iTotalDisplayRecords", cnt);
			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (session != null) {
					session.close();
				}
			}
			return result;
		}
	*/
		public static String rbKey = String.valueOf(getSysParameterForVts());
		
		@SuppressWarnings("unchecked")
		public JSONObject getSchedulewiseOperData(int total,HttpServletRequest request,String fromdate,String todate,String depot){
			JSONObject result = new JSONObject();
			try{
				model.jaxb.xml.trimax.com.VtsResponse6 sotResult = null;
				com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
				com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
//				vehicleresult = port.webGet20PerCan(depot, date, rbKey);
				sotResult = port.webGetScheduleOperationalData(depot, fromdate, todate, rbKey);
				JSONArray array = new JSONArray();
				for (int i = 0; i < sotResult.getWaybillDetails().size() ; i++) {
					JSONArray ja = new JSONArray();
					// Calling vehicle result
					//System.out.println("Depot"+vehicleresult.getVtsDatamodel().get(i).getDepotName());
					ja.add(i+1);
//					ja.add(vehicleresult.getVtsDatamodel().get(i).getScheduleNumberName());
					ja.add(sotResult.getWaybillDetails().get(i).getSCHEDULENAME());
					ja.add(sotResult.getWaybillDetails().get(i).getTOTALTRIP());
					ja.add(sotResult.getWaybillDetails().get(i).getCompletedTrip());
					ja.add(sotResult.getWaybillDetails().get(i).getNOTOperated());
					ja.add(sotResult.getWaybillDetails().get(i).getSot());
					ja.add("");
					ja.add("");
					ja.add("");
					ja.add("");
					ja.add("");
					ja.add("");
					ja.add("");
					ja.add("");
				
					
				    array.add(ja);
				}
				result.put("aaData", array);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
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
