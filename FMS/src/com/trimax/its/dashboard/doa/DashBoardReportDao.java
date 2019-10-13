package com.trimax.its.dashboard.doa;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class DashBoardReportDao {
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

	public Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	public JSONObject getRouteDeviationforDashBoard(int j,String fromDate,String toDate, String DepotId) {

				JSONObject result = new JSONObject();

				try {

					boolean flag = false;
					String trip_status = "";
					WsUtil_Service service = new WsUtil_Service();
					WsUtil port = service.getWsUtilPort();
					// TODO initialize WS operation arguments here
					// Query To Get Schedule Header Information..

					VtsResponse6 alertresult = port.webGetRouteDeviationForDashBoard(fromDate,toDate, DepotId, rbKey);
					JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

						JSONArray ja = new JSONArray();
						ja.add(i + 1);
						//ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
						ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						String shift = "";
						switch (alertresult.getWaybillDetails().get(i)
								.getDutyTypeId()) {
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
						}
						ja.add(shift);
						
						//ja.add(alertresult.getWaybillDetails().get(i).getBBROUTENO());
						ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
						ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
						//ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
						//.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
						ja.add(alertresult.getWaybillDetails().get(i).getDEVIATEDFROM());
						ja.add(alertresult.getWaybillDetails().get(i).getDEVIATEDTILL());
						//ja.add(alertresult.getWaybillDetails().get(i).getDESTINATION());

						//
						array.add(ja);
					}
					// result.put("bbData", headrearray);
					result.put("aaData", array);

				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
				}
				return result;
			}

	
	public JSONObject getTripCancelationforDashBoard(int j,String fromDate,String toDate, String DepotId) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetTripCancelationForDashBoard(fromDate,toDate, DepotId, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				//ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				String shift = "";
				switch (alertresult.getWaybillDetails().get(i).getDutyTypeId()) {
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
				}
				ja.add(shift);
				
				//ja.add(alertresult.getWaybillDetails().get(i).getBBROUTENO());
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i).getTRIPS());
				ja.add(alertresult.getWaybillDetails().get(i).getACTTRIPS());
				//.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getENDBUSSTOPNAME());
				//ja.add(alertresult.getWaybillDetails().get(i).getDESTINATION());
				
				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// TOTAL summury Report start
			public JSONObject getTotalSummuryReport(String frmdate,String todate,String depotid) {

				System.out.println("frmdate.."+frmdate+"todate...."+todate);
				JSONObject result = new JSONObject();
				int count[] = new int[3];
				String status[] = new String[3];
				int totalCount = 0;
				List l1 = null;
				JSONArray array = new JSONArray();
				JSONArray ja1 = new JSONArray();
				JSONArray ja2 = new JSONArray();
				JSONArray ja3 = new JSONArray();
				JSONArray ja4 = new JSONArray();
				JSONArray ja5 = new JSONArray();
				JSONArray ja6 = new JSONArray();
				JSONArray ja7 = new JSONArray();
				try {
					
					// System.out.println("currentDate"+currentDate);
					
					//VtsResponse5 accresult = port.webGetTotalRouteCount(currentDate,rbKey);
					int  active_bus_stop=getActiveBusStop(frmdate,todate,depotid); 
					
					int Active_Route_count=getActiveRoute(todate,depotid);
					int Active_form_four=getActiveFormFour(frmdate,todate,depotid);
					int schedule=getActiveSchedule(frmdate,todate,depotid);
					int Deat_trips=getDeadTrips(frmdate,todate,depotid);
					int charted_trips=getChartedTrips(frmdate,todate,depotid);
					int Regular_trips=getRegulartrips(frmdate,todate,depotid);
					
					//for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
						
						ja1.add("Total No. Of Active Form Four ");
						ja1.add(Active_form_four);
						
						ja2.add("Active Bus Stop");
						ja2.add(active_bus_stop);
						
						ja3.add("Route");
						ja3.add(Active_Route_count);
						
						
						ja4.add("Schedule ");
						ja4.add(schedule);
						
						ja5.add("Dead Trips  ");
						ja5.add(Deat_trips);
						
						ja6.add("Charted Trips ");
						ja6.add(charted_trips);
						
						ja7.add("Regular Trips  ");
						ja7.add(Regular_trips);
						
						array.add(ja1);
						array.add(ja2);
						array.add(ja3);
						array.add(ja4);
						array.add(ja5);
						array.add(ja6);
						array.add(ja7);
						
					//}

					result.put("aaData", array);
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {

				}
				return result;
			}

			//  TOTAL summury Report end
			//get Active Bus Stop start
			
			public int getActiveBusStop(String fromdate,String todate,String depotid)
			
			{
				int  busStop_count=0;
			
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
					String sql="select count(*) as busStop_count from bus_stop where status not in('inactive','deleted') and  updated_date between concat('"+fromdate+"', ' 00:00:00') and concat('"+todate+"', ' 23:59:59')";
					//System.out.println("sql^getActiveBusStop........"+sql);
				
							Query query = session.createSQLQuery(sql).addScalar("busStop_count");
						
					
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
				
				
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
					 Map<String, Object> list = aliasToValueMapList.get(i);
				
					 busStop_count=Integer.parseInt(list.get("busStop_count").toString());
					 
				 }
					
				}
				catch(Exception e){
					e.printStackTrace();
					
					
				}
				finally{
					
					session.close();
				}
				return busStop_count;
			}
			//get Active Bus Stop end
			//get Active Route
			
			public int getActiveRoute(String todate,String depotid)
			
			{
				int  activeroute_count=0;
				
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
					String sql="select  count(*) as activeroute from route where deleted_status=0 and CONCAT('"+todate+"', ' 00:00:00') between effective_from and if(effective_till='0000-00-00 00:00:00',CONCAT('"+todate+"', ' 00:00:00'),effective_till) and effective_from!=effective_till";
					//System.out.println("sql^getActiveRoute........"+sql);
							Query query = session.createSQLQuery(sql).addScalar("activeroute");
							
					
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
				
				
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
					 Map<String, Object> list = aliasToValueMapList.get(i);
				
					 activeroute_count=Integer.parseInt(list.get("activeroute").toString());
					 
				 }
					
				}
				catch(Exception e){
					e.printStackTrace();
					
					
				}
				finally{
					
					session.close();
				}
				return activeroute_count;
			}
			//end Active Route

	//Active Route
	//get Active Form Four
	
		public int getActiveFormFour(String fromdate,String todate,String depotid) 
				
				{
			Common common=new Common();
		//	String to_date=common.getDateTimeFromPickerBrandEdit(todate);
		
			//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Date date=(Date) simpleDateFormat.parse(to_date);			
			//System.out.println("to_date................#"+to_date);
					int  activeformfour=0;
					
					Session session=HibernateUtil.getSession("hibernate.cfg.xml");
					session.beginTransaction();
					try{
						String sql="select  count(*) as activeformfour from form_four  where  effective_start_date >='"+fromdate+"'  and "+
								" if(effective_end_date is null,'"+todate+"','"+todate+"')<='"+todate+"' "+
								" and  deleted_status = '0' ";
								//"and CONCAT('"+todate+"', ' 00:00:00') between effective_from and if(effective_till='0000-00-00 00:00:00',CONCAT('"+todate+"', ' 00:00:00'),effective_till) and effective_from!=effective_till";
						//System.out.println("getActiveFormFour"+sql);
								Query query = session.createSQLQuery(sql).addScalar("activeformfour");
								
						
						query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
					
					
					 for (int i = 0; i < aliasToValueMapList.size(); i++) {
						 Map<String, Object> list = aliasToValueMapList.get(i);
					
						 activeformfour=Integer.parseInt(list.get("activeformfour").toString());
						 
					 }
						
					}
					catch(Exception e){
						e.printStackTrace();
						
						
					}
					finally{
						
						session.close();
					}
					return activeformfour;
				}
				
	//End Form Four
		//Schedule start
	
		
			public int getActiveSchedule(String fromdate,String todate,String depotid)
					
					{
						int  schedulecount=0;
						
						Session session=HibernateUtil.getSession("hibernate.cfg.xml");
						session.beginTransaction();
						try{
							String sql="select  count(*) as schedulecount from schedule where   effective_start_date >='"+fromdate+"' "+
							" and if(effective_end_date is null,'"+todate+"','"+todate+"')<='"+todate+"'" +
							" and depot_id='"+depotid+"' and status IN('ACTIVE','Rationalized')  and deleted_status = '0'";
							//and now() between effective_from and if(effective_till='0000-00-00 00:00:00',now(),effective_till) and effective_from!=effective_till";
							//System.out.println("getActiveSchedule"+sql);
									Query query = session.createSQLQuery(sql).addScalar("schedulecount");
									
							
							query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
							List<Map<String, Object>> aliasToValueMapList = query.list();
						
						
						 for (int i = 0; i < aliasToValueMapList.size(); i++) {
							 Map<String, Object> list = aliasToValueMapList.get(i);
						
							 schedulecount=Integer.parseInt(list.get("schedulecount").toString());
							 
						 }
							
						}
						catch(Exception e){
							e.printStackTrace();
							
							
						}
						finally{
							
							session.close();
						}
						return schedulecount;
					}
					
		
		//schedule end
			//Start Dead Trip
			public int getDeadTrips(String fromdate,String todate,String depotid)
			
			{
				int  deadtrip_count=0;
				
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
//					String sql="select  count(*) as deadtrip_count from schedule_details where deleted_status=0 and trip_type=3 "; 
							//"and now() between effective_from and if(effective_till='0000-00-00 00:00:00',now(),effective_till) and effective_from!=effective_till";
					String sql="select  count(*) as deadtrip_count from schedule_details sdd "+
							" inner join schedule sd on sd.schedule_id=sdd.schedule_number "+
							" where sdd.trip_type=3 and effective_start_date >='"+fromdate+"' "+
							" and if(effective_end_date is null,'"+todate+"','"+todate+"')<='"+todate+"'" +
							" and depot_id='"+depotid+"' and status IN('ACTIVE','Rationalized')  and sd.deleted_status = '0'";
					//System.out.println("getDeadTrips"+sql);
							Query query = session.createSQLQuery(sql).addScalar("deadtrip_count");
							
					
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
				
				
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
					 Map<String, Object> list = aliasToValueMapList.get(i);
				
					 deadtrip_count=Integer.parseInt(list.get("deadtrip_count").toString());
					 
				 }
					
				}
				catch(Exception e){
					e.printStackTrace();
					
					
				}
				finally{
					
					session.close();
				}
				return deadtrip_count;
			}
			//end Dead Trips
			//charted Trips
			public int getChartedTrips(String fromdate,String todate,String depotid)
			
			{
				int  chartedtrip_count=0;
				
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
					/*String sql="select  count(*) as chartedtrip_count from schedule_details where deleted_status=0 and trip_type in(19,20) " ;*/
							//"and now() between effective_from and if(effective_till='0000-00-00 00:00:00',now(),effective_till) and effective_from!=effective_till";
					String sql="select  count(*) as chartedtrip_count from schedule_details sdd "+
							" inner join schedule sd on sd.schedule_id=sdd.schedule_number "+
							" where sdd.trip_type in(19,20) and effective_start_date >='"+fromdate+"' "+
							" and if(effective_end_date is null,'"+todate+"','"+todate+"')<='"+todate+"'" +
							" and depot_id='"+depotid+"' and status IN('ACTIVE','Rationalized')  and sd.deleted_status = '0'";
					
					//System.out.println("getChartedTrips"+sql);
							Query query = session.createSQLQuery(sql).addScalar("chartedtrip_count");
							
					
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
				
				
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
					 Map<String, Object> list = aliasToValueMapList.get(i);
				
					 chartedtrip_count=Integer.parseInt(list.get("chartedtrip_count").toString());
					 
				 }
					
				}
				catch(Exception e){
					e.printStackTrace();
					
					
				}
				finally{
					
					session.close();
				}
				return chartedtrip_count;
			}
			//end charted Trips
			//start Regular Trips
			public int getRegulartrips(String fromdate,String todate,String depotid)
			
			{
				Common common=new Common();
				
				int  regulartrip_count=0;
				
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
					/*String sql="select  count(*) as regulartrip_count from schedule_details where deleted_status=0 and trip_type=2" ;
							//"and now() between effective_from and if(effective_till='0000-00-00 00:00:00',now(),effective_till) and effective_from!=effective_till";
*/					
					String sql="select  count(*) as regulartrip_count from schedule_details sdd "+
							" inner join schedule sd on sd.schedule_id=sdd.schedule_number "+
							" where  sdd.trip_type=2 and effective_start_date >='"+fromdate+"' "+
							" and if(effective_end_date is null,'"+todate+"','"+todate+"')<='"+todate+"'" +
							" and depot_id='"+depotid+"' and status IN('ACTIVE','Rationalized')  and sd.deleted_status = '0'";
					Query query = session.createSQLQuery(sql).addScalar("regulartrip_count");
					//System.out.println("getRegulartrips"+sql);	
					
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
				
				
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
					 Map<String, Object> list = aliasToValueMapList.get(i);
				
					 regulartrip_count=Integer.parseInt(list.get("regulartrip_count").toString());
					 
				 }
					
				}
				catch(Exception e){
					e.printStackTrace();
					
					
				}
				finally{
					
					session.close();
				}
				return regulartrip_count;
			}
			//end Regular Trips
			
			
			
			public JSONObject getKmCancelationforDashBoard(int j,String fromDate,String toDate, String DepotId) {

				JSONObject result = new JSONObject();

				try {

					boolean flag = false;
					String trip_status = "";
					WsUtil_Service service = new WsUtil_Service();
					WsUtil port = service.getWsUtilPort();
					// TODO initialize WS operation arguments here
					// Query To Get Schedule Header Information..

					VtsResponse6 alertresult = port.webGetTripCancelationForDashBoard(fromDate,toDate, DepotId, rbKey);
					JSONArray array = new JSONArray();
					for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

						JSONArray ja = new JSONArray();
						ja.add(i + 1);
						//ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
						ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
						String shift = "";
						switch (alertresult.getWaybillDetails().get(i).getDutyTypeId()) {
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
						}
						ja.add(shift);
						
						//ja.add(alertresult.getWaybillDetails().get(i).getBBROUTENO());
						ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
						ja.add(alertresult.getWaybillDetails().get(i).getDISTANCE());
						ja.add(alertresult.getWaybillDetails().get(i).getACTDIST());
						//.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
						//ja.add(alertresult.getWaybillDetails().get(i).getSTARTBUSSTOPNAME());
						ja.add(alertresult.getWaybillDetails().get(i).getDISDIFF());
						//ja.add(alertresult.getWaybillDetails().get(i).getDESTINATION());
						
						//
						array.add(ja);
					}
					// result.put("bbData", headrearray);
					result.put("aaData", array);

				} catch (Exception ex) {
					ex.printStackTrace();

				} finally {
									}
				return result;
			}
			
			public JSONObject getAccidentDetailsForDashBoard(int j,String fromDate,String toDate,String depotId){
				JSONObject result = new JSONObject();
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
				String sql="select call_time,route_no,driver_name,vehical_id,notes,accident_type " +
						"from accident_ccc " +
						"where depot_id="+depotId+" and created_date between '"+fromDate+" 00:00:00' and '"+toDate+" 23:59:59'";
				Query query=session.createSQLQuery(sql)
						.addScalar("call_time")
						.addScalar("route_no")
						.addScalar("driver_name")
						.addScalar("vehical_id")
						.addScalar("notes")
						.addScalar("accident_type");
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliseToMapValueList=query.list();
				JSONArray array=new JSONArray();
				
				for(int i=0;i<aliseToMapValueList.size();i++){
					Map<String, Object> rs=aliseToMapValueList.get(i);
					JSONArray ja=new JSONArray();
					ja.add(i+1);
					ja.add(rs.get("call_time").toString());
					ja.add(rs.get("route_no")!=null?rs.get("route_no").toString():"");
					ja.add(rs.get("driver_name")!=null?rs.get("driver_name").toString():"");
					ja.add(" ");
					ja.add(rs.get("vehical_id")!=null?rs.get("vehical_id").toString():"");
					ja.add(rs.get("notes")!=null?rs.get("notes").toString():"");
					ja.add(rs.get("accident_type")!=null?rs.get("accident_type").toString():"");
					
					array.add(ja);
				}
				result.put("aaData", array);
				result.put("iTotalRecords", aliseToMapValueList.size());
				result.put("iTotalDisplayRecords", aliseToMapValueList.size());
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					if (session != null) {
						session.close();
						//session.flush();
					}
				}
				return result;
				
			}
			
			public JSONObject getTotalScheduleForDashBoard(int j,String fromDate,String toDate,String depotId){
				JSONObject result = new JSONObject();
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
				String sql="select count(sc.schedule_type) as totalSch,st.schedule_type_name," +
						" sc.schedule_type as schedule_type,sc.schedule_id as schedule_id,sc.schedule_number as schedule_number " +
						"from schedule sc " +
						"INNER JOIN schedule_type st ON st.schedule_type_id=sc.schedule_type " +
						"	where sc.effective_start_date >='"+fromDate+"' " +
						" and if(sc.effective_end_date is null,"+toDate+","+toDate+")<='"+toDate+"'  " +
						"and sc.depot_id="+depotId+" and sc.status IN('ACTIVE','Rationalized')  and sc.deleted_status = '0' " +
						"group by sc.schedule_type " +
						"order by sc.schedule_type ";
				Query query=session.createSQLQuery(sql)
						.addScalar("totalSch")
						.addScalar("schedule_type_name")
						.addScalar("schedule_type")
						.addScalar("schedule_id")
						.addScalar("schedule_number");
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliseToMapValueList=query.list();
				JSONArray array=new JSONArray();
				int count = 0;
				int totalSch = 0;
				for(int i=0;i<aliseToMapValueList.size();i++){
					Map<String, Object> rs=aliseToMapValueList.get(i);
					JSONArray ja=new JSONArray();
					
					ja.add(rs.get("schedule_type_name").toString());
					ja.add(rs.get("totalSch").toString());
					totalSch += Integer.parseInt(rs.get("totalSch").toString());
					System.out.println(totalSch + " "+ i + " "+aliseToMapValueList.size());
					array.add(ja);
					if(i == aliseToMapValueList.size()-1){
						System.out.println(totalSch);
						JSONArray ja1=new JSONArray();
						ja1.add("<b>Grand Total<b>");
						ja1.add(totalSch);	
						array.add(ja1);
					}
					
				}
				result.put("aaData", array);
				result.put("iTotalRecords", aliseToMapValueList.size());
				result.put("iTotalDisplayRecords", aliseToMapValueList.size());
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					if (session != null) {
						session.close();
						//session.flush();
					}
				}
				return result;
				
			}
			
			public JSONObject getLicenceDetailsForDashBoard(int j,String fromDate,String toDate,String depotId){
				JSONObject result = new JSONObject();
				Session session=HibernateUtil.getSession("hibernate.cfg.xml");
				session.beginTransaction();
				try{
				String sql="select ifnull(emp.EMPLOYEE_NAME,'') as EMPLOYEE_NAME,ifnull(emp.TOKEN,'') as TOKEN ," +
						"ifnull(emp.DL_EXPIRY_DT,'') as DL_EXPIRY_DT," +
						"ifnull(emp.DRIVING_LC_NO,'') as licence_no," +
						"RETIREMENT_DT,CONDUCTOR_LC_EXPDATE,WORKING_DESIGNATION," +
						"ifnull(emp.EMPLOYEE_DESIGNATION,'') as EMPLOYEE_DESIGNATION  " +
						"from employee emp " +
						"where org_chart_id="+depotId+" and  emp.CONDUCTOR_LC_EXPDATE<='2015-06-31' " +
						"order by EMPLOYEE_DESIGNATION ";

				Query query=session.createSQLQuery(sql)
						.addScalar("EMPLOYEE_NAME")
						.addScalar("TOKEN")
						.addScalar("DL_EXPIRY_DT")
						.addScalar("licence_no")
						.addScalar("RETIREMENT_DT")
						.addScalar("CONDUCTOR_LC_EXPDATE")
						.addScalar("WORKING_DESIGNATION")
						.addScalar("EMPLOYEE_DESIGNATION");
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliseToMapValueList=query.list();
				JSONArray array=new JSONArray();
				int count = 0;
				int totalSch = 0;
				String designation ="";
				int sequence = 1;
				for(int i=0;i<aliseToMapValueList.size();i++){
					Map<String, Object> rs=aliseToMapValueList.get(i);
					JSONArray ja=new JSONArray();
					
					int id=Integer.parseInt(rs.get("WORKING_DESIGNATION")!=null?rs.get("WORKING_DESIGNATION").toString():"");
					String desig = rs.get("WORKING_DESIGNATION").toString();
					
					if(designation.equals(desig)){
						sequence++;
						
					}else {
					sequence=1;
					designation = desig;
					JSONArray ja1=new JSONArray();
					ja1.add(rs.get("EMPLOYEE_DESIGNATION").toString());
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					array.add(ja1);
					}
//					switch (id) {
//					case 1:
//						if(designation.equals("DRIVER")){
//							sequence++;
//							break;
//						}
//						sequence=1;
//						designation = "DRIVER";
//						JSONArray ja1=new JSONArray();
//						ja1.add(designation);
//						ja1.add("");
//						ja1.add("");
//						ja1.add("");
//						ja1.add("");
//						ja1.add("");
//						ja1.add("");
//						ja1.add("");
//						array.add(ja1);
//						break;
//					case 2:
//						if(designation.equals("CONDUCTOR")){
//							sequence++;
//							break;
//						}
//						sequence=1;
//						designation = "CONDUCTOR";
//						JSONArray ja2=new JSONArray();
//						ja2.add(designation);
//						ja2.add("");
//						ja2.add("");
//						ja2.add("");
//						ja2.add("");
//						ja2.add("");
//						ja2.add("");
//						ja2.add("");
//						array.add(ja2);
//						break;
//					case 15:
//						if(designation.equals("ISSUE CLERK")){
//							sequence++;
//							break;
//						}
//						sequence=1;
//						designation = "ISSUE CLERK";
//						JSONArray ja3=new JSONArray();
//						ja3.add(designation);
//						ja3.add("");
//						ja3.add("");
//						ja3.add("");
//						ja3.add("");
//						ja3.add("");
//						ja3.add("");
//						ja3.add("");
//						array.add(ja3);
//						break;
//					case 16:
//						if(designation.equals("DRIVER CUM CONDUCTOR")){
//							sequence++;
//							break;
//						}
//						sequence=1;
//						designation = "DRIVER CUM CONDUCTOR";
//						JSONArray ja4=new JSONArray();
//						ja4.add(designation);
//						ja4.add("");
//						ja4.add("");
//						ja4.add("");
//						ja4.add("");
//						ja4.add("");
//						ja4.add("");
//						ja4.add("");
//						array.add(ja4);
//						break;
//					case 17:
//						if(designation.equals("Mechanic")){
//							sequence++;
//							break;
//						}
//						sequence=1;
//						designation = "Mechanic";
//						JSONArray ja5=new JSONArray();
//						ja5.add(designation);
//						ja5.add("");
//						ja5.add("");
//						ja5.add("");
//						ja5.add("");
//						ja5.add("");
//						ja5.add("");
//						ja5.add("");
//						array.add(ja5);
//						break;
//					case 18:
//						if(designation.equals("Others")){
//							sequence++;
//							break;
//						}
//						sequence=1;
//						designation = "Others";
//						JSONArray ja6=new JSONArray();
//						ja6.add(designation);
//						ja6.add("");
//						ja6.add("");
//						ja6.add("");
//						ja6.add("");
//						ja6.add("");
//						ja6.add("");
//						ja6.add("");
//						array.add(ja6);
//						break;
//					
//					}
					/*if((rs.get("WORKING_DESIGNATION").toString()).equals("1")){
						JSONArray ja1=new JSONArray();
					ja1.add("DRIVER");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					array.add(ja1);
					}*/
						//System.out.println(totalSch);
						
					
					ja.add(sequence);
					ja.add(rs.get("EMPLOYEE_NAME")!=null?rs.get("EMPLOYEE_NAME").toString():"");
					ja.add(rs.get("TOKEN")!=null?rs.get("TOKEN").toString():"");
					ja.add(rs.get("licence_no")!=null?rs.get("licence_no").toString():"");
					ja.add("Not Confirm");
					ja.add(rs.get("DL_EXPIRY_DT")!=null?rs.get("DL_EXPIRY_DT").toString():"");					
					ja.add(rs.get("RETIREMENT_DT")!=null?rs.get("RETIREMENT_DT").toString():"");
					//ja.add(rs.get("CONDUCTOR_LC_EXPDATE")!=null?rs.get("CONDUCTOR_LC_EXPDATE").toString():"");
					ja.add(rs.get("EMPLOYEE_DESIGNATION")!=null?rs.get("EMPLOYEE_DESIGNATION").toString():"");
					
					array.add(ja);
					
					
					
				}
				result.put("aaData", array);
				result.put("iTotalRecords", aliseToMapValueList.size());
				result.put("iTotalDisplayRecords", aliseToMapValueList.size());
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					if (session != null) {
						session.close();
						//session.flush();
					}
				}
				return result;
				
			}
			

}
