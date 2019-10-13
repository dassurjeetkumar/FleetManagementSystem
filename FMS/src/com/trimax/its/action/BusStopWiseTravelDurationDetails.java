package com.trimax.its.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;



import com.trimax.its.common.Common;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

public class BusStopWiseTravelDurationDetails {
	
	
	public static String rbKey = String.valueOf(getSysParameterForVts());

	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getDeviceIMEI(
			String FromDate, String TillDate) {
		List<com.trimax.ws.vts.vtsutility.VtsDataModel> imeiList = null;
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse result = port
					.getIMEIList(rbKey);
			for (int i = 0; i < result.getVtsDatamodel().size(); i++) {
				imeiList = result.getVtsDatamodel();
			}
		} catch (Exception ex) {

		} finally {
			// session.close();
		}
		return imeiList;
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
	public String execute(){
		//System.out.println("in execute");
		HttpServletRequest request=ServletActionContext.getRequest();

		
	
		return "success";
	}
	
	
	@SkipValidation
	public String getBusStopDropDownList1() {
		// get Depot List..
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			RouteDAO routedao = new RouteDAO();
			String busstopname=(req.getParameter("routename"));
			String rouet=(req.getParameter("buststop"));
//			System.out.println("busstopname==="+busstopname);
//			System.out.println("rouet==="+rouet);
			List<BusStops> listroute = this.tripPlannerDownList1(busstopname,rouet);
			
			
//			System.out.println("regionTypeAjaxString" + listroute.size());
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(new JSONArray(listroute));
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
//			System.out.println("route==="+route[0]);
//			System.out.println("route==="+route[1]);
			Common common = new Common();
			String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
			String routid=common.getDBResultStr(session, queryforrouteid, "route_id");
//			System.out.println("routid===="+routid);
//			String queryForRouteNos="SELECT group_id bus_stop_group_id,group_name stop FROM `bus_stop_group` where group_name like'%"+routeno+"%'  " +
//					" and `group_type_id` = '1' AND `deleted_status` = '0' AND `status` = 'ACTIVE'";
			String busstop1="select bs.bus_stop_id bus_stop_id,bs.bus_stop_name bus_stop_name,bs.group_name stop from route_point rp " +
					"inner join bus_stop bs on bs.bus_stop_id=rp.bus_stop_id " +
					"inner join bus_stop_group bsg on bsg.group_id=bs.bus_stop_group_id " +
					"where rp.route_id='"+routid+"' and bs.status='Approved' and rp.deleted_status='0' and bsg.group_name like'%"+rouet+"%' group by bs.bus_stop_id";
			Query queryObject = session.createSQLQuery(busstop1).addScalar("bus_stop_id",Hibernate.STRING)
					.addScalar("bus_stop_name",Hibernate.STRING).addScalar("stop",Hibernate.STRING)
					.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();
			
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String,String> map = aliasToValueMapList.get(i);
				BusStops route1 = new BusStops();
				//route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
				route1.setBus_stop_code(map.get("bus_stop_id"));
				route1.setBusStopName(map.get("stop"));		
				route1.setValue(map.get("stop"));
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
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
//	public String getStopWiseTravelDurationDetails() throws IOException {
//		System.out.println("hiiii");
//
//		try {
//
//			
//			
//			
//			HttpServletRequest request = ServletActionContext.getRequest();
//			HttpServletResponse response = ServletActionContext.getResponse();
//
//
//          String sourceid=request.getParameter("source");
//          String destid=request.getParameter("dest");
//          String startdate=request.getParameter("startdate");
//  		String stringsplit[] = startdate.split("-");
//  		String enddate=request.getParameter("enddate");
//  		String stringsplit1[] = enddate.split("-");
//          String routno=request.getParameter("busstop");
//          Date date1=new Date(stringsplit[0]);
//			Date date2=new Date(stringsplit1[0]);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String format = formatter.format(date1);
//		String format1 = formatter.format(date2);
//		System.out.println(format);
//		System.out.println(format1);
//        //  System.out.println("sourceid=="+sourceid+"destid=="+destid+"starttime=="+starttime+"endtime=="+endtime+"routno=="+routno);
//          System.out.println("jiiiiiiiii");
//			
//			JSONObject result = new JSONObject();
//			int amount = 10;
//			int start = 0;
//			int col = 0;
//			String dir = "asc";
//			String sStart = request.getParameter("iDisplayStart");
//			String sAmount = request.getParameter("iDisplayLength");
//			String sCol = request.getParameter("iSortCol_0");
//			String sdir = request.getParameter("sSortDir_0");
//			String search_term = request.getParameter("sSearch");
//
//			if (sStart != null) {
//				start = Integer.parseInt(sStart);
//				if (start < 0) {
//					start = 0;
//				}
//			}
//			if (sAmount != null) {
//				amount = Integer.parseInt(sAmount);
//				if (amount < 10 || amount > 50) {
//					amount = 10;
//				}
//			}
//			if (sCol != null) {
//				col = Integer.parseInt(sCol);
//				if (col < 0 || col > 5)
//					col = 0;
//			}
//			if (sdir != null) {
//				if (!sdir.equals("asc"))
//					dir = "desc";
//			}
//			int total = this.getTotalNoOfRecords(sourceid,destid,stringsplit[1],stringsplit1[1],routno,format,format1);
//			System.out.println("total=="+total);
//			AMOUNT = amount;
//			SEARCH_TERM = request.getParameter("sSearch");
//			DIR = dir;
//			START = start;
//			response.setContentType("application/json");
//			response.setHeader("Cache-Control", "no-store");
//			PrintWriter out = response.getWriter();
//			result = this.getListOfData(total,request, Integer.parseInt(sCol),sdir,sourceid,destid,stringsplit[1],stringsplit1[1],routno,format,format1);
//			out.print(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
//	@SuppressWarnings({ "unchecked" })
//	public JSONObject getListOfData(HttpServletRequest request,
//			int index, String dir,String sourceid,String destid,String starttime,String endtime,String routeno,String startdate,String enddate) {
//		JSONObject result = new JSONObject();
//		Session session = null;
//		Session session1 = null;
//
//		Common common = new Common();
//		//String startdate1 = common.getDateFromPicker(startdate);
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	     System.out.println("date is===="+dateFormat.format(new Date()));
//      
//	     String starttime1 =starttime.trim();
//	     String endtime1 =endtime.trim();
//		String[] dbcol = { "", "route_number", "route_id",
//				"route_name","route_direction","via","","","","","" };
//		try {
//			int totalAfterFilter = total;
//			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
//			
//			String route[]=routeno.split(":");
//			System.out.println("route==="+route[0]);
//			System.out.println("route==="+route[1]);
//			
//			String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' and deleted_status='0'";
//			String routid=common.getDBResultStr(session1, queryforrouteid, "route_id");
//			System.out.println("routid===="+routid);
//			//query for bus stop name by using sourceid and destid
//			String queryforbusstop="select bus_stop_name from bus_stop where bus_stop_id='"+sourceid+"'";
//			String sourcebus=common.getDBResultStr(session1, queryforbusstop, "bus_stop_name");
//			String queryforbusstop1="select bus_stop_name from bus_stop where bus_stop_id='"+destid+"'";
//			String destbus=common.getDBResultStr(session1, queryforbusstop1, "bus_stop_name");
//			//end
//			session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
//			String sql="";
//			 sql = "select *,left(timediff(ARR_TM,DEPT_TM),8) gps_trip_diff from (SELECT TRIP_NUMBER,RUNNING_TIME,wtd.SCHEDULE_NAME,SUBSTRING_INDEX(SUBSTRING_INDEX(GROUP_CONCAT(ENTRY_TIME), ',', 1), ',', -1) AS DEPT_TM," +
//			 		"SUBSTRING_INDEX(SUBSTRING_INDEX(GROUP_CONCAT(ENTRY_TIME), ',', 2), ',', -1) AS ARR_TM," +
//			 		"group_concat(BUS_STOP_NAME) From_To_Stop,group_concat(ENTRY_TIME) GPS_TIME,START_TIME,END_TIME " +
//			 		"FROM `tripwise_busstop_details` tbd  " +
//			 		"inner join waybill_trip_details wtd on wtd.ID=tbd.ID " +
//			 		"WHERE tbd.`ROUTE_ID` = '"+routid+"' AND `BUS_STOP_ID` IN( '"+sourceid+"',  '"+destid+"' ) AND `DUTY_DT` = '"+startdate+"' and tbd.ENTRY_TIME!='00:00:00' " +
//			 		"group by tbd.ID order by BUS_STOP_ORDER ) A having DEPT_TM between '"+starttime1+":00' and '"+endtime1+":00'";
//					
//			String search_term1 = request.getParameter("sSearch");
//			if (search_term1 != null && !search_term1.equals("")) {
//				String search_term = request.getParameter("sSearch").trim();
////				
//			}
//			String col = dbcol[index];
//			if (!col.equals("")) {
//				if (dir.equals("asc")) {
//					//sql += " order by " + col + " asc";
//				} else {
//					//sql += " order by " + col + " desc";
//				}
//			} else {
//				//sql += " order by rt.route_number desc";
//			}
////			sql += " limit " + request.getParameter("iDisplayStart") + ", "
////					+ request.getParameter("iDisplayLength");
//			Query query = session.createSQLQuery(sql)
//					.addScalar("TRIP_NUMBER", Hibernate.STRING)
//					.addScalar("RUNNING_TIME", Hibernate.STRING)
//					.addScalar("SCHEDULE_NAME", Hibernate.STRING)
//					.addScalar("DEPT_TM", Hibernate.STRING)
//					.addScalar("ARR_TM", Hibernate.STRING)
//					.addScalar("From_To_Stop", Hibernate.STRING)
//					.addScalar("GPS_TIME", Hibernate.STRING)
//					.addScalar("START_TIME", Hibernate.STRING)
//					.addScalar("END_TIME", Hibernate.STRING)
//					.addScalar("gps_trip_diff", Hibernate.STRING);
//			
//
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String, Object>> aliasToValueMapList = query.list();
//
//			org.json.simple.JSONArray array = new org.json.simple.JSONArray();
//			for (int i = 0; i < aliasToValueMapList.size(); i++) {
//				int j = i + 1;
//				Map<String, Object> rs = aliasToValueMapList.get(i);
//			   org.json.simple.JSONArray ja = new org.json.simple.JSONArray();
//				String fromtostop = rs.get("From_To_Stop").toString();
//				
//				
//			   ja.add(j);
//				ja.add(rs.get("SCHEDULE_NAME").toString());
//				ja.add(rs.get("TRIP_NUMBER").toString());
//				if(fromtostop.contains(",")){
//					String stringsplit[] = fromtostop.split(",");
//					ja.add(stringsplit[0]);
//					ja.add(stringsplit[1]);
//				}else{
//					ja.add(sourcebus);
//					ja.add(destbus);
//					//String fromtostop = rs.get("From_To_Stop").toString();
//				}
//				
//				ja.add(rs.get("START_TIME").toString());
//				ja.add(rs.get("END_TIME").toString());
//				ja.add(rs.get("DEPT_TM").toString());
//				ja.add(rs.get("ARR_TM").toString());
//				ja.add(rs.get("RUNNING_TIME").toString());
//				//ja.add(rs.get("From_To_Stop").toString());
//				//ja.add(rs.get("GPS_TIME").toString());
//				
//				ja.add(rs.get("gps_trip_diff").toString());
//				
//
//				array.add(ja);
//
//			}
//			int cnt = 0;
//			result.put("aaData", array);
//			 if (search_term1 != null && !search_term1.equals("")) {
//			 totalAfterFilter = getTotalNoOfRecords(sourceid, destid, starttime, endtime, routeno, startdate, enddate);
//			
//			 result.put("aaData", array);
//			 result.put("iTotalRecords", totalAfterFilter);
//			 result.put("iTotalDisplayRecords",totalAfterFilter);
//			 } else {
//			totalAfterFilter = getTotalNoOfRecords(sourceid, destid, starttime, endtime, routeno, startdate, enddate);
//			result.put("aaData", array);
//			result.put("iTotalRecords", totalAfterFilter);
//			result.put("iTotalDisplayRecords", totalAfterFilter);
//			 }
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//		return result;
//	}

//	public int getTotalNoOfRecords(String sourceid,String destid,String starttime,String endtime,String routeno,String startdate,String enddate) {
//		int cnt = 0;
//		int cnt1 = 0;
//		Session session = null;
//		Session session1 = null;
//
//		Common common = new Common();
//		//String startdate1 = common.getDateFromPicker(startdate);
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	     System.out.println("date is===="+dateFormat.format(new Date()));
//      
//	     String starttime1 =starttime.trim();
//	     String endtime1 =endtime.trim();
//		
//		try {
//			//int totalAfterFilter = total;
//			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
//			
//			String route[]=routeno.split(":");
//			System.out.println("route==="+route[0]);
//			System.out.println("route==="+route[1]);
//			
//			String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' and deleted_status='0'";
//			String routid=common.getDBResultStr(session1, queryforrouteid, "route_id");
//			System.out.println("routid===="+routid);
//			//query for bus stop name by using sourceid and destid
//			String queryforbusstop="select bus_stop_name from bus_stop where bus_stop_id='"+sourceid+"'";
//			String sourcebus=common.getDBResultStr(session1, queryforbusstop, "bus_stop_name");
//			String queryforbusstop1="select bus_stop_name from bus_stop where bus_stop_id='"+destid+"'";
//			String destbus=common.getDBResultStr(session1, queryforbusstop1, "bus_stop_name");
//			//end
//			session = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
//			String sql="";
//			 sql = "select *,left(timediff(ARR_TM,DEPT_TM),8) gps_trip_diff from (SELECT TRIP_NUMBER,RUNNING_TIME,wtd.SCHEDULE_NAME,SUBSTRING_INDEX(SUBSTRING_INDEX(GROUP_CONCAT(ENTRY_TIME), ',', 1), ',', -1) AS DEPT_TM," +
//			 		"SUBSTRING_INDEX(SUBSTRING_INDEX(GROUP_CONCAT(ENTRY_TIME), ',', 2), ',', -1) AS ARR_TM," +
//			 		"group_concat(BUS_STOP_NAME) From_To_Stop,group_concat(ENTRY_TIME) GPS_TIME,START_TIME,END_TIME " +
//			 		"FROM `tripwise_busstop_details` tbd  " +
//			 		"inner join waybill_trip_details wtd on wtd.ID=tbd.ID " +
//			 		"WHERE tbd.`ROUTE_ID` = '"+routid+"' AND `BUS_STOP_ID` IN( '"+sourceid+"',  '"+destid+"' ) AND `DUTY_DT` = '"+startdate+"' and tbd.ENTRY_TIME!='00:00:00' " +
//			 		"group by tbd.ID order by BUS_STOP_ORDER ) A having DEPT_TM between '"+starttime1+":00' and '"+endtime1+":00'";
//					
//			
//			Query query = session.createSQLQuery(sql)
//					.addScalar("TRIP_NUMBER", Hibernate.STRING)
//					.addScalar("RUNNING_TIME", Hibernate.STRING)
//					.addScalar("SCHEDULE_NAME", Hibernate.STRING)
//					.addScalar("DEPT_TM", Hibernate.STRING)
//					.addScalar("ARR_TM", Hibernate.STRING)
//					.addScalar("From_To_Stop", Hibernate.STRING)
//					.addScalar("GPS_TIME", Hibernate.STRING)
//					.addScalar("START_TIME", Hibernate.STRING)
//					.addScalar("END_TIME", Hibernate.STRING)
//					.addScalar("gps_trip_diff", Hibernate.STRING);
//			
//
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			List<Map<String, Object>> aliasToValueMapList = query.list();
//			System.out.println("aliasToValueMapList==="+aliasToValueMapList.size());
//			cnt1=aliasToValueMapList.size();
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		return cnt1;
//	}

	
	
	public String getStopWiseTravelDurationDetails() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();


      String sourceid=request.getParameter("source");
      String destid=request.getParameter("dest");
      String startdate=request.getParameter("startdate");
		String stringsplit[] = startdate.split("-");
		String enddate=request.getParameter("enddate");
		String stringsplit1[] = enddate.split("-");
      String routno=request.getParameter("busstop");
      String effectiveStartDate=request.getParameter("effectiveStartDate");
      
      Date date1=new Date(stringsplit[0]);
		Date date2=new Date(stringsplit1[0]);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String format = formatter.format(date1);
	String format1 = formatter.format(date2);

	Common common = new Common();
	//String effectiveStartDate1=common.getDateFromPicker(effectiveStartDate);
    //  System.out.println("sourceid=="+sourceid+"destid=="+destid+"starttime=="+starttime+"endtime=="+endtime+"routno=="+routno);
     
		JSONObject result = new JSONObject();
		PrintWriter out = null;
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = response.getWriter();
			result = getListOfData1(request,sourceid,destid,stringsplit[1]+ ":00",stringsplit1[1]+ ":00",routno,format,format1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}
	
	
	public JSONObject getListOfData1(HttpServletRequest request,String sourceid,String destid,String starttime,String endtime,String routeno,String startdate,String enddate) {
			 JSONObject result = new JSONObject();
			 Common common = new Common();
			 try {
				 
				Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					
					String route[]=routeno.split(":");
//					System.out.println("route==="+route[0]);
//					System.out.println("route==="+route[1]);
					
					String queryforrouteid="select route_id from route where route_number='"+route[0]+"' and route_direction='"+route[1]+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
					String routid=common.getDBResultStr(session1, queryforrouteid, "route_id");
//					System.out.println("routid===="+routid);
					//query for bus stop name by using sourceid and destid
					String queryforbusstop="select bus_stop_name from bus_stop where bus_stop_id='"+sourceid+"'";
					String sourcebus=common.getDBResultStr(session1, queryforbusstop, "bus_stop_name");
					String queryforbusstop1="select bus_stop_name from bus_stop where bus_stop_id='"+destid+"'";
					String destbus=common.getDBResultStr(session1, queryforbusstop1, "bus_stop_name");
				 
			 Session session = HibernateUtil.getSession("");
			
			 com.trimax.ws.vts.vtsutility.WsUtil_Service service = new
			 com.trimax.ws.vts.vtsutility.WsUtil_Service();
			 com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			 // TODO initialize WS operation arguments here
			 model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			 org.json.simple.JSONArray array = new org.json.simple.JSONArray();
			 vehicleresult = port.webGetBusStopWiseDuration(sourceid,destid,starttime,endtime,routid,startdate,enddate,rbKey);
			 for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
			 org.json.simple.JSONArray ja = new org.json.simple.JSONArray();
			 ja.add(i + 1);
			
			 //System.out.println("device_serial_number"+vehicleresult.getVtsDatamodel().get(i).getSTARTTM());
			// System.out.println("orgname"+vehicleresult.getVtsDatamodel().get(i).getOrgName());
			
			// System.out.println(vehicleresult.getVtsDatamodel().get(i).getSCHEDULENAME());
			 String fromtostop = vehicleresult.getVtsDatamodel().get(i).getSTARTBUSSTOPNAME(); 
//			 System.out.println("fromtostop=="+fromtostop);
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getSCHEDULENAME());
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getTRIPNUMBER());
			 
			 if(fromtostop.contains(",")){
					String stringsplit[] = fromtostop.split(",");
					ja.add(stringsplit[0]);
					ja.add(stringsplit[1]);
				}else{
					ja.add(sourcebus);
					ja.add(destbus);
					//String fromtostop = rs.get("From_To_Stop").toString();
				}
			 
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getSTARTTM());
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getENDTM());
			 //
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getActualDeparture());
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getActualArrival());
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getACTUALARRIVALTIME());
			 ja.add(vehicleresult.getVtsDatamodel().get(i).getARRIVALDIFF());
			
			 array.add(ja);
			
			 }
			 result.put("aaData", array);
			 } catch (Exception ex) {
			 ex.printStackTrace();
			 }
			 return result;
			 }
	
}
