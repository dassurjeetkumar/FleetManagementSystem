package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class RouteWiseTripTimePlanning {
	
	private Map<Integer, String> divisionlist;
	private String division1;
	private String depot1;
	public String startdate;

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDivision1() {
		return division1;
	}

	public void setDivision1(String division1) {
		this.division1 = division1;
	}

	public String getDepot1() {
		return depot1;
	}

	public void setDepot1(String depot1) {
		this.depot1 = depot1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
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
	public String execute() {

		// try {
		// if(dateStr!=null){
		// Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		// request.getSession().setAttribute("cDate", date);
		// }else{
		// request.getSession().setAttribute("cDate", new Date());
		// }
		// } catch (Exception e) {
		// request.getSession().setAttribute("cDate", new Date());
		// e.printStackTrace();
		// }

		this.setDivisionlist(getDivisionName());
		return "success";
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	@SuppressWarnings({ "finally", "unchecked" })
	
	
	public String getRouteWiseTripTimingVtu()
	{
				
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
	    VtsDataDAO dao= VtsDataDAO.getInstance();
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		String source=req.getParameter("source");
		String dest=req.getParameter("dest");
		String routeid=req.getParameter("routeid");
		String startdate=req.getParameter("startdate");
		String stringsplit[] = startdate.split("-");
		String enddate=req.getParameter("enddate");
		String stringsplit1[] = enddate.split("-");
		
		String route[]=routeid.split(":");
		//System.out.println("route==="+route[0]);
		//System.out.println("route==="+route[1]);
		Common common = new Common();
		
		//String schedulelist=req.getParameter("schedulelist");
		PrintWriter out = null;
		JSONObject result = new JSONObject();

		Common cm = new Common();
		//String formattedgivendate = cm.getDateFromPicker(stringsplit[0]);
		try{
			Date date1=new Date(stringsplit[0]);
			Date date2=new Date(stringsplit1[0]);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String format = formatter.format(date1);
		String format1 = formatter.format(date2);
		
		 SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		resp.setContentType("application/json");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		String sql1="SELECT group_concat(bus_stop_id) bus_stop_id FROM bus_stop WHERE bus_stop_group_id ="+ source+" AND `status` = 'Approved'";
//		Query query1 = session.createSQLQuery(sql1);
//		 String ids = cm.getDBResultStr(session, sql1, "bus_stop_id");
//		 System.out.println("ids=="+ids);
//		 String sql2="SELECT group_concat(bus_stop_id) bus_stop_id1 FROM bus_stop WHERE bus_stop_group_id ="+ dest+" AND `status` = 'Approved'";
//			Query query2 = session.createSQLQuery(sql2);
//			 String ids1 = cm.getDBResultStr(session, sql2, "bus_stop_id1");
			 
			 String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
				String routid=common.getDBResultStr(session, queryforrouteid, "route_id");
		out = resp.getWriter();
		result = getDataForRouteWiseTripTiming(1, req, "", "",format, format1, source,dest,routid,stringsplit[1],stringsplit1[1]);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
//		
	
//			
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
			out.print(result);
			return null;
		}

	

	public JSONObject getDataForRouteWiseTripTiming(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String enddate, String source, String dest, String routid,String starttime,String endtime) {
		JSONObject result = new JSONObject();
		try {
			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetDataForRouteWiseTripTiming(selectedDate,enddate,source,dest,routid,starttime, endtime,rbKey);
			JSONArray array = new JSONArray();
			//System.out.println(alertresult.getVtsDatamodel().size());
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getDUTYDT());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getROUTENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getTRIPNUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSTARTTM());
				ja.add(alertresult.getVtsDatamodel().get(i).getENDTM());
				ja.add(alertresult.getVtsDatamodel().get(i).getACTTIME());
				ja.add(alertresult.getVtsDatamodel().get(i).getACTUALARRIVALTIME());
				ja.add(alertresult.getVtsDatamodel().get(i).getACTSTARTTIME());
				ja.add(alertresult.getVtsDatamodel().get(i).getACTENDTIME());
				ja.add(alertresult.getVtsDatamodel().get(i).getSTARTBUSSTOPNAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getENDBUSSTOPNAME());
				
				ja.add(alertresult.getVtsDatamodel().get(i).getSchtype());
				ja.add(alertresult.getVtsDatamodel().get(i).getDEPOTCD());
				// ja.add(shift);

				/*
				 * if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS
				 * ().equals("Partial")||alertresult.getWaybillDetails().get(i).
				 * getSCHEDULEDTRIPSTATUS().equals("Cancelled")) { ja.add(
				 * "<input type='checkbox' data-toggle='modal' role='button' href='#deviationinfo' onclick=javascript:getsghhh('"
				 * + selectedDate + "') id='deviated' />"); }
				 * 
				 * else { ja.add(""); }
				 */

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	
	
	public String getScheduleForTripTiming() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
	
		int parentId = Integer.parseInt(req.getParameter("val"));
//		 String dat = req.getParameter("startdate");
//		 System.out.println("dat==="+dat);
		 Common comm = new Common();
		//String dat1=comm. getDateFromPicker(dat);
        		List<String> l1 = this.getgetScheduleForTripTimingId(parentId);
        		List<String> l2 = this.getgetScheduleForTripTimingName(parentId);
        		String regionTypeAjaxString = "";
//        		regionTypeAjaxString += "<option value='0'>------select------</option>";
        		for (int i = 0; i < l1.size(); i++) {
        			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
        					+ ">" + l2.get(i).toString() + "</option>";
        		}
        		HttpServletResponse response = ServletActionContext.getResponse();
        		PrintWriter out;
        		try {
        			out = response.getWriter();
        			out.print(regionTypeAjaxString);
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
//        		return null;
//        	}
		
        		return null;
        }
	
	
	public List getgetScheduleForTripTimingId(int depotID) {
		List list = new ArrayList();
		String qry = "";
		try {
			qry = "select schedule_number,form_four_id from schedule s inner join form_four ff on ff.schedule_number_id=s.schedule_id where  (s.status='ACTIVE' or s.status='NEW') " +
					"and (s.current_Status='APPROVED' or s.current_Status='CASE WORKER') and s.deleted_Status=0 and depot_id='"+depotID+"' and ff.deleted_status=0";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("form_four_id").toString());
					//list.add(rs.get("schedule_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}
	
	public List getgetScheduleForTripTimingName(int depotID) {
		List list = new ArrayList();
		String qry = "";
		try {
			qry = "select schedule_number,form_four_id from schedule s inner join form_four ff on ff.schedule_number_id=s.schedule_id where  (s.status='ACTIVE' or s.status='NEW') " +
					"and (s.current_Status='APPROVED' or s.current_Status='CASE WORKER') and s.deleted_Status=0 and depot_id='"+depotID+"' and ff.deleted_status=0";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_number").toString() );
				//	list.add(rs.get("form_four_id").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}

	public Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
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

	 public String sourcebus;
	 public String destbus;
	public String getSourcebus() {
		return sourcebus;
	}

	public void setSourcebus(String sourcebus) {
		this.sourcebus = sourcebus;
	}

	public String getDestbus() {
		return destbus;
	}

	public void setDestbus(String destbus) {
		this.destbus = destbus;
	}

	@SkipValidation
	public String getBusStopFixedSource() {
		// get Depot List..
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			RouteDAO routedao = new RouteDAO();
			String busstopname=(req.getParameter("routename"));
			String rouet=(req.getParameter("buststop"));
			List<BusStops> listroute = this.tripPlannerDownList1(busstopname,rouet);
			
			
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(new com.trimax.its.org.json.JSONArray(listroute));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	@SuppressWarnings("unchecked")
	public List<BusStops> tripPlannerDownList1(String routeno,String rouet){

		
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<BusStops> list1 = new ArrayList<BusStops>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String route[]=routeno.split(":");
			Common common = new Common();
			String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
			String routid=common.getDBResultStr(session, queryforrouteid, "route_id");
//			String queryForRouteNos="SELECT group_id bus_stop_group_id,group_name stop FROM `bus_stop_group` where group_name like'%"+routeno+"%'  " +
//					" and `group_type_id` = '1' AND `deleted_status` = '0' AND `status` = 'ACTIVE'";
			String busstop1="select bs.bus_stop_id,bs.bus_stop_name stop from bus_stop bs inner join route r on " +
					"r.start_point_id=bs.bus_stop_id where bs.status='Approved' and " +
					"r.status='Active'  and r.route_id='"+routid+"' group by bus_stop_id";
			Query queryObject = session.createSQLQuery(busstop1).addScalar("bus_stop_id",Hibernate.STRING)
					.addScalar("stop",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
//			for (int i = 0; i < aliasToValueMapList.size(); i++) {
//
//				BusStops device = (BusStops) aliasToValueMapList.get(i);
//				list1.add(device);
//			}
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				BusStops route1 = new BusStops();
				//route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setBus_stop_code(map.get("bus_stop_id"));
				route1.setBusStopName(map.get("stop"));		
				route1.setValue(map.get("stop"));
				sourcebus=route1.getBusStopName();
				list1.add(route1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list1;
		
	}
	
	
	@SkipValidation
	public String getBusStopFixedDest() {
		// get Depot List..
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			RouteDAO routedao = new RouteDAO();
			String busstopname=(req.getParameter("routename"));
			String rouet=(req.getParameter("buststop"));
			List<BusStops> listroute = this.tripPlannerDownList2(busstopname,rouet);
			
			
			System.out.println("regionTypeAjaxString" + listroute.size());
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(new com.trimax.its.org.json.JSONArray(listroute));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	@SuppressWarnings("unchecked")
	public List<BusStops> tripPlannerDownList2(String routeno,String rouet){

		
		Session session = null;
		List<Map<String,String>> aliasToValueMapList = null;
		List<BusStops> list1 = new ArrayList<BusStops>();
		try{
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
			String today = formatter.format( new java.util.Date() );
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			String route[]=routeno.split(":");
			Common common = new Common();
			String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
			String routid=common.getDBResultStr(session, queryforrouteid, "route_id");
			System.out.println("routid===="+routid);
//			String queryForRouteNos="SELECT group_id bus_stop_group_id,group_name stop FROM `bus_stop_group` where group_name like'%"+routeno+"%'  " +
//					" and `group_type_id` = '1' AND `deleted_status` = '0' AND `status` = 'ACTIVE'";
			String busstop1="select bs.bus_stop_id,bs.bus_stop_name stop from bus_stop bs inner join route r on " +
					"r.end_point_id=bs.bus_stop_id where bs.status='Approved' and " +
					"r.status='Active' and r.route_id='"+routid+"' group by bus_stop_id";
			Query queryObject = session.createSQLQuery(busstop1).addScalar("bus_stop_id",Hibernate.STRING)
					.addScalar("stop",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				BusStops route1 = new BusStops();
				//route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setBus_stop_code(map.get("bus_stop_id"));
				route1.setBusStopName(map.get("stop"));		
				route1.setValue(map.get("stop"));
				destbus=route1.getBusStopName();
				list1.add(route1);
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		return list1;
		
	}
	
}
