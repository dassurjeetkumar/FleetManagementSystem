package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.route.model.Route;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class tripWiseDurationReport extends ActionSupport{
	public String startdate;
	public String enddate;
	public String route;
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	

		private String SEARCH_TERM;

		private String COL_NAME;

		private String DIR;

		private int START;

		private int AMOUNT;

		@SuppressWarnings({ "finally", "unchecked" })
		
		
		public String showTripwiseTravelduration()
		{
					
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		    //VtsDataDAO dao= VtsDataDAO.getInstance();
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			String startdate=req.getParameter("startdate");
			String stringsplit[] = startdate.split("-");
			String enddate=req.getParameter("enddate");
			String stringsplit1[] = enddate.split("-");
			String route=req.getParameter("route");
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
			out = resp.getWriter();
			result = getDataForRouteWiseTripTiming(req,format, format1,stringsplit[1],stringsplit1[1],route);
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
//			
		
//				
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
				out.print(result);
				return null;
			}
		public JSONObject getDataForRouteWiseTripTiming(HttpServletRequest req, 
				String selectedDate, String enddate, String starttime,String endtime,String route) {
			JSONObject result = new JSONObject();
			Session session = null;
			Session session1 = null;
			String startpoint="";String endpoint="";String routeid="";
			List<Map<String,String>> aliasToValueMapList = null;
			List<Map<String,String>> aliasToValueMapList1 = null;
			String stringsplit1[] = route.split(":");
			String starttime1=starttime.trim();
			String endtime1=endtime.trim();
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
				String queryForRouteNos = "SELECT route_id,start_point_id,end_point_id FROM `route` WHERE route_number='"+stringsplit1[0]+"' " +
						"and route_direction='"+stringsplit1[1]+"' and `status` = 'active' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
				Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("route_id",Hibernate.STRING)
						.addScalar("start_point_id",Hibernate.STRING).addScalar("end_point_id",Hibernate.STRING)
						.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				aliasToValueMapList = queryObject.list();
				
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> map = aliasToValueMapList.get(i);
					routeid=map.get("route_id");
					startpoint=map.get("start_point_id") ;
					endpoint=map.get("end_point_id");
					
				}
				
				session1 = HibernateUtilVtu.getSession("hibernate-vts.cfg.xml");
				String sql="select SCHEDULE_NAME,ROUTE_NO,TRIP_NUMBER,DUTY_DT,DEPARTURE_TIME,ARRIVAL_TIME,ACTUAL_DEPARTURE_TIME,ACTUAL_ARRIVAL_TIME_DISP," +
						"RUNNING_TIME TIME_DURATION,left(TIMEDIFF(ACTUAL_ARRIVAL_TIME_DISP,ACTUAL_DEPARTURE_TIME),8) Gps_Trip_duration from " +
						"(select DATE(ETM_START_TIME) ETM_START_TIME,DATE(ETM_END_TIME) ETM_END_TIME,DEPOT_CD,WAYBILL_NO,depot_id,ID,DEVICE_ID," +
						"TRIP_NUMBER,ROUTE_ID,START_POINT,END_POINT,VEHICLE_NO,START_BUS_STOP_NAME, (select BUS_STOP_NAME from " +
						"tripwise_busstop_details where ID=wtd.ID and ENTRY_TIME!='00:00:00' and ENTRY_TIME=(select ENTRY_TIME from " +
						"tripwise_busstop_details where ID=wtd.ID and ENTRY_TIME!='00:00:00' order by BUS_STOP_ORDER limit 1 ) limit 1) " +
						"Actual_Start_Point, END_BUS_STOP_NAME,(select BUS_STOP_NAME from tripwise_busstop_details where ID=wtd.ID and " +
						"ENTRY_TIME!='00:00:00' and ENTRY_TIME=(select ENTRY_TIME from tripwise_busstop_details where ID=wtd.ID and " +
						"ENTRY_TIME!='00:00:00' order by BUS_STOP_ORDER desc limit 1 ) limit 1) Actual_End_Point,DUTY_DT,SCHEDULE_NAME," +
						"is_dread_trip is_dread_trip_flag,START_TIME AS DEPARTURE_TIME,if((select min(ENTRY_TIME) from tripwise_busstop_details " +
						"where ID=wtd.ID and ENTRY_TIME!='00:00:00') is null, if(ETM_START_TIME is null ,ifnull(ACT_START_TIME,''),''), " +
						"(select min(ENTRY_TIME) from tripwise_busstop_details where ID=wtd.ID and ENTRY_TIME!='00:00:00')) AS ACTUAL_DEPARTURE_TIME," +
						"END_TIME AS ARRIVAL_TIME,ROUTE_NO,ifnull(if( ACT_END_TIME < ACT_START_TIME,ADDTIME(RUNNING_TIME,ACT_START_TIME),ACT_END_TIME),'') AS ACTUAL_ARRIVAL_TIME, " +
						"if((select ENTRY_TIME from tripwise_busstop_details where BUS_STOP_ORDER=(SELECT MIN(BUS_STOP_ORDER) " +
						"FROM tripwise_busstop_details WHERE ID=wtd.ID) and ID=wtd.ID)='00:00:00','-',(select ENTRY_TIME from tripwise_busstop_details " +
						"where BUS_STOP_ORDER=(SELECT MIN(BUS_STOP_ORDER) FROM tripwise_busstop_details WHERE ID=wtd.ID) and ID=wtd.ID)) as " +
						"ACT_START_TIME, if((select ENTRY_TIME from tripwise_busstop_details where BUS_STOP_ORDER=(SELECT MAX(BUS_STOP_ORDER) " +
						"FROM tripwise_busstop_details WHERE ID=wtd.ID) and ID=wtd.ID)='00:00:00','-',(select ENTRY_TIME from tripwise_busstop_details " +
						"where BUS_STOP_ORDER=(SELECT MAX(BUS_STOP_ORDER) FROM tripwise_busstop_details WHERE ID=wtd.ID) and ID=wtd.ID)) as END_TIME, " +
						"if((select max(ENTRY_TIME) from tripwise_busstop_details where ID=wtd.ID and ENTRY_TIME!='00:00:00') is null, " +
						"if(ETM_END_TIME is null,'',''), (select max(ENTRY_TIME) from tripwise_busstop_details where ID=wtd.ID and " +
						"ENTRY_TIME!='00:00:00')) AS ACTUAL_ARRIVAL_TIME_DISP, duty_type_id,DISTANCE,if(round((select " +
						"(max(cast(ACCUMULATED_DISTANCE as unsigned))-min(cast(ACCUMULATED_DISTANCE as unsigned)))/1000 AS ACCUMULATED_DISTANCE from " +
						"tripwise_busstop_details where ID=wtd.ID and ACCUMULATED_DISTANCE !='00000000' and ACCUMULATED_DISTANCE !=''),2) is null,'0'," +
						"round((select (max(cast(ACCUMULATED_DISTANCE as unsigned))-min(cast(ACCUMULATED_DISTANCE as unsigned)))/1000 AS " +
						"ACCUMULATED_DISTANCE from tripwise_busstop_details where ID=wtd.ID and ACCUMULATED_DISTANCE !='00000000' and " +
						"ACCUMULATED_DISTANCE !=''),2)) as TRIP_DISTANCE, RUNNING_TIME, (case when TRIP_STATUS='P' then 'Partial' when " +
						"TRIP_STATUS ='C' then 'Completed' when TRIP_STATUS IN('N','Y') then 'Not Operated' when TRIP_STATUS='D' then 'Deviated' end) as " +
						"TRIP_STATUS1 from waybill_trip_details wtd where duty_dt between '"+selectedDate+"' and '"+enddate+"' and ETIM_Coll_Amt!=0.0 and " +
						"ROUTE_ID='"+routeid+"' and device_id!='' and START_POINT='"+startpoint+"' and END_POINT='"+endpoint+"'  and is_dread_trip!=1 " +
						"having ACTUAL_DEPARTURE_TIME between '"+starttime1+":00' and '"+endtime1+":00' ) A";
				Query queryObject1 = session1.createSQLQuery(sql).addScalar("SCHEDULE_NAME",Hibernate.STRING).addScalar("ROUTE_NO",Hibernate.STRING)
						.addScalar("TRIP_NUMBER",Hibernate.STRING).addScalar("DUTY_DT",Hibernate.STRING).addScalar("DEPARTURE_TIME",Hibernate.STRING)
						.addScalar("ARRIVAL_TIME",Hibernate.STRING).addScalar("ACTUAL_DEPARTURE_TIME",Hibernate.STRING)
						.addScalar("ACTUAL_ARRIVAL_TIME_DISP",Hibernate.STRING)
						.addScalar("TIME_DURATION",Hibernate.STRING).addScalar("Gps_Trip_duration",Hibernate.STRING)
						.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				aliasToValueMapList1 = queryObject1.list();
				JSONArray array = new JSONArray();
				for(int i=0;i<aliasToValueMapList1.size();i++){
					Map<String,String> map = aliasToValueMapList1.get(i);
					JSONArray ja = new JSONArray();
					ja.add(i + 1);
					ja.add(map.get("SCHEDULE_NAME"));
					ja.add(map.get("ROUTE_NO"));
					ja.add(map.get("TRIP_NUMBER"));
					ja.add(map.get("DUTY_DT"));
					ja.add(map.get("DEPARTURE_TIME"));
					ja.add(map.get("ARRIVAL_TIME"));
					ja.add(map.get("ACTUAL_DEPARTURE_TIME"));
					ja.add(map.get("ACTUAL_ARRIVAL_TIME_DISP"));
					ja.add(map.get("TIME_DURATION"));
					ja.add(map.get("Gps_Trip_duration"));
					array.add(ja);
				}
				result.put("aaData", array);
				
			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {
			}
			return result;
		}
}
