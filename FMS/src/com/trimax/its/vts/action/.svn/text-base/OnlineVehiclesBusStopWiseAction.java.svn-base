package com.trimax.its.vts.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.trimax.its.ad.dao.AdMannagementDao;
import com.trimax.its.common.Common;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Route;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.StageWiseTicketConsumptionDOA;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

public class OnlineVehiclesBusStopWiseAction {

	private Map<Integer, String> divisionlist;
	OrganisationChart orgchart;
	private String depotlist1;
	private Map<Integer, String> divisionlist1;
	private List<Route> routeList;
	private String route;
	public String startdate;
	public String enddate;

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public List<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
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


	public String execute() throws Exception {
		Date date = new Date();
 		Date date1 = new Date();
 		Date addedDate2 = addDays(date1, 2);
		try {
 			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			setStartdate(sm3.format(addedDate2));
 			setEnddate(sm2.format(date));
 		} catch (Exception ex) {

 		}
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDepotWithoutAll();
         return "success";
	}
	
	public static Date addDays(Date d, int days) {
		d.setTime(d.getTime()  - 1800 * 1000);
		return d; 
	}
	
//	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString=new StringBuffer();
	

@SkipValidation
public String getbusStopList() {
	// get Depot List..
	
	try {
		HttpServletRequest req = ServletActionContext.getRequest();
	
		String busstopname=(req.getParameter("stopName"));
		List<BusStops> listroute = busStopDropDownList(busstopname);
		
		
		System.out.println("regionTypeAjaxString" + listroute.size());
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
public List<BusStops> busStopDropDownList(String routeno){

	System.out.println("trip drop dowm");
	Session session = null;
	List<Map<String,String>> aliasToValueMapList = null;
	List<BusStops> list1 = new ArrayList<BusStops>();
	try{
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
		String today = formatter.format( new java.util.Date() );
		session = HibernateUtil.getSession("hibernate.cfg.xml");
			
		String queryForRouteNos="SELECT bus_stop_id,bus_stop_name stop,stop_direction FROM bus_stop WHERE status='Approved'  AND point_type_id NOT IN(2,13,18) and bus_stop_name like'%"+routeno+"%'  " +
				" order by bus_stop_name";
		Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("bus_stop_id",Hibernate.STRING)
				.addScalar("stop",Hibernate.STRING).addScalar("stop_direction",Hibernate.STRING)
				.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		aliasToValueMapList = queryObject.list();
		
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String,String> map = aliasToValueMapList.get(i);
			BusStops route1 = new BusStops();
			//route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
			route1.setBus_stop_code(map.get("bus_stop_id"));
			route1.setBusStopName(map.get("stop")+"(direction : "+map.get("stop_direction")+")");		
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



public String getData() {
	
	System.out.println("in get data ");
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	Session session=null;
	String lat="";
	String longitude="";
	AdMannagementDao dao=new AdMannagementDao();
	try {
		JSONObject result = new JSONObject();
		int total = -1;
		
		  Common common=new Common();
			  SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm ");
		    String startdate1 = common.getDateTimeFromPickerToDB(startdate);
		    String enddate1 = common.getDateTimeFromPickerToDB(enddate);
			System.out.println("start--" +startdate1 +"end==="+enddate1);
			
			String stopId=request.getParameter("busStopId");
			System.out.println("stop is==== "+stopId);
			session=HibernateUtil.getSession("hibernate.cfg.xml");
			String qruy="select latitude_current,longitude_current from bus_stop where bus_stop_id="+stopId+" " ;
//					"and status='Approved'
			Query query=session.createSQLQuery(qruy)
					.addScalar("latitude_current")
					.addScalar("longitude_current");
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
					 Map<String, Object> list = aliasToValueMapList.get(i);
					  lat=list.get("latitude_current").toString();
					  longitude=list.get("longitude_current").toString();
					 System.out.println("lat--"+lat+"-long-"+longitude);
				 }
			 DateFormat df = new SimpleDateFormat("yyyy-mm-dd"); 
			 Date startDate;
			 startDate=df.parse(startdate1);
			String newDate=df.format(startDate);
			System.out.println("new  Date      "+newDate);
		
//		total = getSkipStopRecord(request, startdate1, enddate1,stopId);
//		response.setContentType("application/json");
//		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result = dao.getTotalVehicleDataDateTime(lat, longitude,startdate1,enddate1);
		out.print(result);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {

	}
	return null;

}





}
