package com.trimax.its.route.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.fare.dao.FareChartMasterDao;
import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Bay;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Platform;
import com.trimax.its.route.model.Route;
import com.trimax.its.route.model.RouteMap;
import com.trimax.its.route.model.RoutePlatformRel;
import com.trimax.its.route.model.RoutePoint;
import com.trimax.its.route.model.RouteType;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.RoadType;
import com.trimax.its.transport.model.ScheduleDetails;
import com.trimax.its.transport.model.ScheduleService;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VehicleAlertConfigDAO;

public class RouteAction extends ActionSupport implements Preparable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Route> route;
	List<RoutePoint> routePoint;
	List<RoutePlatformRel> routePlatRel;
	List<RouteMap> routeMap;
	List<BusStops> list;
	List<RouteType> listRouteType;
	List<ScheduleService> listServiceType;
	List<Platform> platform;
	List<OrganisationChart> orgchart;
	List<Floor> listfloor;
	List<Bay> listbay;
	List<ScheduleService> service_type;
	List<RateMaster> rate_master;
	List<RoadType> roadtype;
	
	List<Route> routeData;
	List<RoutePoint> routePointData;
	List<RouteMap> routeMapData;
	List<RoutePlatformRel> routePlatRelData;
	List<ScheduleDetails> routeScheduleData;
	List<FormFour> routeFormfourdata;
	String busstopId;
	String busStopName;
	String Latitutde;
	String Longitude;
	
	public String route_name; 
	public String routeid;
	
	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getLatitutde() {
		return Latitutde;
	}

	public void setLatitutde(String latitutde) {
		Latitutde = latitutde;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getBusStopName() {
		return busStopName;
	}

	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}

	public String getBusstopId() {
		return busstopId;
	}

	public void setBusstopId(String busstopId) {
		this.busstopId = busstopId;
	}
    
	public List<RoadType> getRoadtype() {
		return roadtype;
	}

	public void setRoadtype(List<RoadType> roadtype) {
		this.roadtype = roadtype;
	}

	public List<ScheduleService> getService_type() {
		return service_type;
	}

	public void setService_type(List<ScheduleService> service_type) {
		this.service_type = service_type;
	}

	public List<RateMaster> getRate_master() {
		return rate_master;
	}

	public void setRate_master(List<RateMaster> rate_master) {
		this.rate_master = rate_master;
	}

	private BusStops busstops;

	private RouteType routetype;
	
	public RouteType getRoutetype() {
		return routetype;
	}

	public void setRoutetype(RouteType routetype) {
		this.routetype = routetype;
	}

	public BusStops getBusstops() {
		return busstops;
	}

	public void setBusstops(BusStops busstops) {
		this.busstops = busstops;
	}

	public List<Floor> getListfloor() {
		return listfloor;
	}

	public void setListfloor(List<Floor> listfloor) {
		this.listfloor = listfloor;
	}

	public List<OrganisationChart> getOrgchart() {
		return orgchart;
	}

	public void setOrgchart(List<OrganisationChart> orgchart) {
		this.orgchart = orgchart;
	}

	public List<ScheduleService> getListServiceType() {
		return listServiceType;
	}

	public void setListServiceType(List<ScheduleService> listServiceType) {
		this.listServiceType = listServiceType;
	}

	public List<Platform> getPlatform() {
		return platform;
	}

	public void setPlatform(List<Platform> platform) {
		this.platform = platform;
	}

	private Route routes;
//	private RouteMap routemap;
//	private RoutePoint routepoint;
//	
//	public RouteMap getRoutemap() {
//		return routemap;
//	}
//
//	public void setRoutemap(RouteMap routemap) {
//		this.routemap = routemap;
//	}
//
//	public RoutePoint getRoutepoint() {
//		return routepoint;
//	}
//
//	public void setRoutepoint(RoutePoint routepoint) {
//		this.routepoint = routepoint;
//	}

	

	/*private int[] start_bus_stop_id_M;
	private int[] end_bus_stop_id_M;
	private String[] road_type_M;
	private int[] google_distance_M;
	private int[] vtu_distance_M;
	private int[] distance_M ;
	private int[] google_time_M;
	private int[] vtu_time_M;
	private String[] time_to_travel_M;
	private String[] status_M;
	private String[] path_M;
	private String[] route_type_M;
	
	private int[] busstopid_P;
	private String[] fare_P;
	private String[] status_P;*/
	
	/*public int[] getStart_bus_stop_id_M() {
		return start_bus_stop_id_M;
	}

	public void setStart_bus_stop_id_M(int[] start_bus_stop_id_M) {
		this.start_bus_stop_id_M = start_bus_stop_id_M;
	}

	public int[] getEnd_bus_stop_id_M() {
		return end_bus_stop_id_M;
	}

	public void setEnd_bus_stop_id_M(int[] end_bus_stop_id_M) {
		this.end_bus_stop_id_M = end_bus_stop_id_M;
	}

	public String[] getRoad_type_M() {
		return road_type_M;
	}

	public void setRoad_type_M(String[] road_type_M) {
		this.road_type_M = road_type_M;
	}

	public int[] getGoogle_distance_M() {
		return google_distance_M;
	}

	public void setGoogle_distance_M(int[] google_distance_M) {
		this.google_distance_M = google_distance_M;
	}

	public int[] getVtu_distance_M() {
		return vtu_distance_M;
	}

	public void setVtu_distance_M(int[] vtu_distance) {
		this.vtu_distance_M = vtu_distance_M;
	}

	public int[] getDistance_M() {
		return distance_M;
	}

	public void setDistance_M(int[] distance_M) {
		this.distance_M = distance_M;
	}

	public int[] getGoogle_time_M() {
		return google_time_M;
	}

	public void setGoogle_time_M(int[] google_time_M) {
		this.google_time_M = google_time_M;
	}

	public int[] getVtu_time_M() {
		return vtu_time_M;
	}

	public void setVtu_time_M(int[] vtu_time_M) {
		this.vtu_time_M = vtu_time_M;
	}

	public String[] getTime_to_travel_M() {
		return time_to_travel_M;
	}

	public void setTime_to_travel_M(String[] time_to_travel_M) {
		this.time_to_travel_M = time_to_travel_M;
	}

	public String[] getStatus_M() {
		return status_M;
	}

	public void setStatus_M(String[] status_M) {
		this.status_M = status_M;
	}

	public String[] getPath_M() {
		return path_M;
	}

	public void setPath_M(String[] path_M) {
		this.path_M = path_M;
	}

	public String[] getRoute_type_M() {
		return route_type_M;
	}

	public void setRoute_type_M(String[] route_type_M) {
		this.route_type_M = route_type_M;
	}

	public int[] getBusstopid_P() {
		return busstopid_P;
	}

	public void setBusstopid_P(int[] busstopid_P) {
		this.busstopid_P = busstopid_P;
	}

	public String[] getFare_P() {
		return fare_P;
	}

	public void setFare_P(String[] fare_P) {
		this.fare_P = fare_P;
	}

	public String[] getStatus_P() {
		return status_P;
	}

	public void setStatus_P(String[] status_P) {
		this.status_P = status_P;
	}*/

	public List<BusStops> getList() {
		return list;
	}

	public void setList(List<BusStops> list) {
		this.list = list;
	}

	public String execute(){
		//System.out.println("in execute");
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date = new Date();
		 String datefil= new SimpleDateFormat("dd/MM/yyyy").format(date);
		 if(request.getParameter("datecri")==null){
			 request.getSession().setAttribute("route_filter_date",datefil ); 
		 }else{
			 request.getSession().setAttribute("route_filter_date", request.getParameter("datecri"));	 
		 }
		 
		 if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("null")){
				
			}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage1").toString().equalsIgnoreCase("0")){
			}else{
				String msg=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString();
//				addActionMessage(msg);
			
				addActionMessage(msg);
				
				ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","null");
				ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage1","0");
				
			}
		 
		
		RouteDAO routedao = new RouteDAO();
		route = routedao.getRouteList();
		listRouteType= routedao.getRouteType();
		//System.out.println("in execute======="+route.size());
		return "success";
	}

	public List<RouteType> getListRouteType() {
		return listRouteType;
	}

	public void setListRouteType(List<RouteType> listRouteType) {
		this.listRouteType = listRouteType;
	}

	public List<Route> getRoute() {
		return route;
	}

	public void setRoute(List<Route> route) {
		this.route = route;
	}
	
	public String showmap(){
		//System.out.println("in show map=======");
		RouteDAO routedao = new RouteDAO();
		listRouteType= routedao.getRouteType();
		listServiceType= routedao.getServiceType();
		//platform= routedao.getPlatform();
		orgchart=routedao.getOrgchart();
		service_type=routedao.getService();
		rate_master=routedao.getRate();
		//System.out.println("iin show map======"+platform.size());
		return "success";
	}
	
	@SkipValidation
	public String getBusStop() throws UnsupportedEncodingException{
		//System.out.println("in get bus stop");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		//RouteDAO routedao = new RouteDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
	    HttpSession sess=request.getSession();
	    //listRouteType= routedao.getRouteType();
	    //System.out.println("in execute======="+listRouteType.size());
	    //request.getSession().setAttribute("listRouteType",listRouteType);
		int usractid=(Integer)request.getSession().getAttribute("userid");
		//System.out.println("userid is============="+usractid);
		double para=0.02;
		double factor=1.2;
		//list = busstopsdao.getBusStopRouteList( request.getParameter("lat"), request.getParameter("lng"), para);
		for(int j=0; j<10;j++){
			list = busstopsdao.getBusStopRouteList( "12.97928309", "77.57205963", para);
			 //System.out.println("list.size() after div"+list.size());
			 if((list.size()>110) && (list.size()<200)){
				 break;
			 }
			 if(list.size()<175){
				 //factor=factor
				 para=para*factor ;
			 }
			 if(list.size()>175){
				 para=para/factor ;
			 }
		 }
		
		String busDet="";
		for(int i=0; i<list.size(); i++){
			 BusStops obj = list.get(i);
			 busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+"|"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPointtype().getPoint_type_id()+"|"+obj.getFareStage()+"|"+obj.getBus_stations().getOrg_chart_id()+"|"+obj.getSub_stage()+"@@@";
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			  out.print(busDet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	
	
	
	@SkipValidation
	public String getBusStopForRoute() throws UnsupportedEncodingException{
		//System.out.println("in get bus stop");
		BusStopsDAO busstopsdao = new BusStopsDAO();
		//RouteDAO routedao = new RouteDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
	    HttpSession sess=request.getSession();
	    //listRouteType= routedao.getRouteType();
	    //System.out.println("in execute======="+listRouteType.size());
	    //request.getSession().setAttribute("listRouteType",listRouteType);
		int usractid=(Integer)request.getSession().getAttribute("userid");
		//System.out.println("userid is============="+usractid);
		double para=0.02;
		double factor=1.2;
		//list = busstopsdao.getBusStopRouteList( request.getParameter("lat"), request.getParameter("lng"), para);
		//for(int j=0; j<10;j++){
		String busstoppoint=request.getParameter("bussopslist");
			list = busstopsdao.getBusStopRouteList( "12.97928309", "77.57205963", para,busstoppoint);
			 //System.out.println("list.size() after div"+list.size());
			 /*if((list.size()>110) && (list.size()<200)){
				 break;
			 }
			 if(list.size()<175){
				 //factor=factor
				 para=para*factor ;
			 }
			 if(list.size()>175){
				 para=para/factor ;
			 }*/
		// }
		
		String busDet="";
		for(int i=0; i<list.size(); i++){
			 BusStops obj = list.get(i);
			 busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+"|"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPointtype().getPoint_type_id()+"|"+obj.getFareStage()+"|"+obj.getBus_stations().getOrg_chart_id()+"|"+obj.getSub_stage()+"@@@";
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+list.size()+busDet);
			  out.print(busDet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	@SuppressWarnings("finally")
	public String saveRoute(){
		//routemap.set
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			session.getTransaction().setTimeout(20);
			session.getTransaction().begin();
		RoutePoint routepoint =new RoutePoint();
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try{
			out = response.getWriter();
		HttpServletRequest request=ServletActionContext.getRequest();
		int googledist=0,googletime=0,aquadist=0,aquatime=0;
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		String[] start_bus_stop_id_M=request.getParameter("start_bus_stop_id_M").split(",");
		String[] end_bus_stop_id_M=request.getParameter("end_bus_stop_id_M").split(",");
		String[] road_type_M=request.getParameter("road_type_M").split(",");
		String[] google_distance_M=request.getParameter("google_distance_M").split(",");
		String[] vtu_distance_M=request.getParameter("vtu_distance_M").split(",");
		String[] distance_M =request.getParameter("distance_M").split(",");
		String[] google_time_M=request.getParameter("google_time_M").split(",");
		String[] vtu_time_M=request.getParameter("vtu_time_M").split(",");
		String[] time_to_travel_M=request.getParameter("time_to_travel_M").split(",");
		String[] status_M=request.getParameter("status_M").split(",");
		String[] path_M=request.getParameter("path_M").split(",");
		String[] route_type_M=request.getParameter("route_type_M").split(",");
		String[] schedule_time_M=request.getParameter("schedule_time_M").split(",");
		String[] schedule_distance_M=request.getParameter("schedule_distance_M").split(",");
		String[] edit_path_M=request.getParameter("edit_path_M").split(",");
		
		String[] busstopid_P=request.getParameter("busstopid_P").split(",");
		String[] fare_P=request.getParameter("fare_P").split(",");
		String[] status_P=request.getParameter("status_P").split(",");
		String[] substage_P=request.getParameter("substage_P").split(",");
		
		
		String[] depotpl=request.getParameter("depotpl").split(",");
		String[] floorpl=request.getParameter("floorpl").split(",");
		String[] baypl=request.getParameter("baypl").split(",");
		String[] platformpl=request.getParameter("platformpl").split(",");
		String[] servicepl=request.getParameter("servicepl").split(",");
		String[] busplatformidpl=request.getParameter("busplatformidpl").split(",");
		
		Route routes =new Route();
		
		routes.setRoute_number(request.getParameter("route_number"));
		
		//busstops.setId(Integer.parseInt(request.getParameter("start_point_id")));
	   // System.out.println(request.getParameter("start_point_id"));
	   // int id =Integer.parseInt(request.getParameter("start_point_id"));
	    busstops=new BusStops(); 
	    busstops.setId(Integer.parseInt(request.getParameter("start_point_id")));
	    routes.setStart_stop(busstops);
	    busstops=new BusStops(); 
	    busstops.setId(Integer.parseInt(request.getParameter("end_point_id")));
	    routes.setEnd_stop(busstops);
		
		//busstops.setId(Integer.parseInt(request.getParameter("end_point_id")));
	    routetype= new RouteType();
	    routetype.setRoute_type_id(Integer.parseInt(request.getParameter("route_type_id")));
		routes.setRoute_type(routetype);
		routes.setRoute_name(request.getParameter("route_name"));
		routes.setStatus(request.getParameter("status"));
		routes.setVia(request.getParameter("via"));
		routes.setCreated_by(usrsessionid);
		routes.setCreated_date(new Date());
		routes.setDeleted_status(Integer.parseInt(request.getParameter("deleted_status")));
		routes.setRoute_direction(request.getParameter("route_direction"));
		routes.setEffective_from(request.getParameter("effect_start_date_route"));
		routes.setDescription(request.getParameter("route_description"));
		routes.setRoute_geofence("N");
		if((String)request.getParameter("effect_end_date_route")=="0000-00-00"){
			routes.setEffective_till("");
			request.getParameter("in loope");
		}else{
			routes.setEffective_till(request.getParameter("effect_end_date_route"));	
		}
		//routes.setRpolypath
		RouteDAO routedao = new RouteDAO();
		route=routedao.routeUni(routes.getRoute_number(),routes.getRoute_direction(),session);
		if(route.size()>0){
	    	 out.print("@Route No Already Exist@");	
	    	 //session.close();
	    	 return null;
	    }else{
	    	out.print("success");
	    }
		
		//session = HibernateUtil.getSession("hibernate.cfg.xml");
		String res=routedao.addRoute(routes,session);
		
			//System.out.println("start_bus_stop_id_M.length====="+start_bus_stop_id_M.length);
			RoutePlatformRel routepl=new RoutePlatformRel();
			RouteMap routemap =new RouteMap();
		 for(int j=0;j<start_bus_stop_id_M.length;j++){
				//System.out.println("j====="+j);
				routemap= new RouteMap();
				routemap.setRoute_id(routes.getRoute_id());
				routemap.setStart_bus_stop_id(Integer.parseInt(start_bus_stop_id_M[j]));
				routemap.setEnd_bus_stop_id(Integer.parseInt(end_bus_stop_id_M[j]));
				routemap.setRoad_type(road_type_M[j]);
				routemap.setGoogle_distance((int) (Double.parseDouble(google_distance_M[j])*1000));
				routemap.setVtu_distance((int) (Double.parseDouble(vtu_distance_M[j])*1000));
				routemap.setDistance((int) (Double.parseDouble(google_distance_M[j])*1000)+(int) (Double.parseDouble(vtu_distance_M[j])*1000));
				routemap.setGoogle_time((int) (Double.parseDouble(google_time_M[j])));
				routemap.setVtu_time((int) (Double.parseDouble(vtu_time_M[j])));
				routemap.setTime_to_travel((int) (Double.parseDouble(google_time_M[j]))+(int) (Double.parseDouble(vtu_time_M[j])));
				routemap.setStatus(status_M[j]);
				routemap.setBus_stop_order(j+1);
				routemap.setPath(path_M[j]);
				routemap.setRoute_type(route_type_M[j]);
				routemap.setSchedule_time(Integer.parseInt(schedule_time_M[j]));
				routemap.setSchedule_distance(Double.parseDouble(schedule_distance_M[j]));
				routemap.setEdit_path(edit_path_M[j]);
				
				String resmap=routedao.addRoute(routemap,session);
				
				
				googledist=googledist+routemap.getGoogle_distance();
				googletime=googletime+routemap.getGoogle_time();
				aquadist=aquadist+routemap.getVtu_distance();
				aquatime=aquatime+routemap.getVtu_time();
				
				
							}
			
		 
		 //System.out.println("routes.getRouteMap().size()====="+routes.getRouteMap().size());
		
		
		for(int i=0;i<busstopid_P.length;i++){
			//System.out.println("i"+i);
			routepoint= new RoutePoint();
			Route r1= new Route();
			r1.setRoute_id(routes.getRoute_id());
			routepoint.setRoute(r1);//getRoute().getRoute_id()
			routepoint.setBus_stop_id(Integer.parseInt(busstopid_P[i]));
			routepoint.setRoute_order(i+1);
			routepoint.setPoint_status(status_P[i]);
			routepoint.setFare_stage(fare_P[i]);
			routepoint.setSub_stage(substage_P[i]);
			routepoint.setCreated_by(usrsessionid);
			routepoint.setCreated_date(new Date());
			routepoint.setDeleted_status(0);
			
			//System.out.println("routepoint====="+routepoint.getBus_stop_id()+routepoint.toString());
			//routes.getRoutePoint().add(routepoint);
			String respoint=routedao.addRoute(routepoint,session);
		}
		session.getTransaction().commit();
		/* if(session!=null){
             session.close();
         }*/
		Route routes1 =new Route();
		routes1.setRoute_id(routes.getRoute_id());
		routes1.setGoogle_distance(googledist);
		routes1.setGoogle_time(googletime);
		routes1.setTrimax_distance(aquadist);
		routes1.setTrimax_time(aquatime);
		
		/*GeometryFactory factory = new GeometryFactory(new PrecisionModel(10000.0), 31300);
	    CoordinateArraySequence coords = new CoordinateArraySequence(new Coordinate[] { new Coordinate(12.0, 34.23),
	            new Coordinate(12.000, 54.555), new Coordinate(-0.01, 0.0) });
	    LineString p = new LineString(coords, factory);
		routes1.setRoute_string(p);*/
		String resroutefinal=routedao.edit(routes1,routes.getRoute_id());
		String coord=request.getParameter("route_string");
				//"LINESTRING(12.97943 77.57235,12.979190000000001 77.57254)";
		//session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.getTransaction().begin();
		int resroutefinal1=routedao.editCoord(routes.getRoute_id(),coord, session);
		session.getTransaction().commit();
		
		
		
		//System.out.println("depotpl.length"+depotpl.length);
		//session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		for(int i=0;i<depotpl.length;i++){
			//System.out.println("i"+i);
			routepl=new RoutePlatformRel();
            if(!depotpl[i].equals("")){
			routepl.setRoute_id(routes.getRoute_id());
			routepl.setBus_stop_id(Integer.parseInt(busplatformidpl[i]));
			routepl.setDepo_id(Integer.parseInt(depotpl[i]));
			routepl.setFloor_id(Integer.parseInt(floorpl[i]));
			routepl.setBay_id(Integer.parseInt(baypl[i]));
			routepl.setPlatform_id(Integer.parseInt(platformpl[i]));
			//busplatformidpl.
			//System.out.println("servicepl[i]"+servicepl[i]);
			String [] plarr=servicepl[i].split("@");
			//System.out.println("servicepl[i]"+plarr.length);
			
			for(int j=0;j<plarr.length;j++){
				//System.out.println("plarr[j]==========="+plarr[j]);
				//session.beginTransaction();
				routepl.setService_type_id(Integer.parseInt(plarr[j]));
				routepl.setStatus("Y");
				
				String resroutePlatrel=routedao.addPlatform(routepl);
				//session.getTransaction().commit();
				if(j>0){
					i++;
				}
				
			}
			
            }
			
           
			//String respoint=routedao.addRoute(routepoint);
		}
		 
		//System.out.println("routes.getRoutePoint()"+routes.getRoutePoint().size());
		out.print("@Route Created Successfully@"+routes.getRoute_number()+"@"+routes.getRoute_id());
		}
		catch(Exception e){
			out = response.getWriter();
			out.print("@Database Error in creating route.Please Try Again.@"+routes.getRoute_number()+"@"+routes.getRoute_id());	
			e.printStackTrace();
			session.getTransaction().rollback();
		//	System.out.print(e.printStackTrace());
		
		}finally{
			return null;	
		}
		}
		catch(Exception e){
			session.getTransaction().rollback();
			PrintWriter out;
			HttpServletResponse response=ServletActionContext.getResponse();
			try {
				out = response.getWriter();
				out.print("@Database Error in creating route.Please Try Again.@"+routes.getRoute_number()+"@"+routes.getRoute_id());	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				out = response.getWriter();
				out.print("@Database Error in creating route.Please Try Again.@"+routes.getRoute_number()+"@"+routes.getRoute_id());	
				e1.printStackTrace();
			}
			
			System.out.println("exxx="+e);
		}
		finally{
			 if(session!=null){
	               // session.close();
	            }
			return null;
		}
		
		
	}
	private Map<Integer,String> routeNumberMap;
	private Map<Integer,String> serviceTypeMap;
	private Map<Integer,String> passengerTypeMap;
	private Map<String,String> nightServiceMap;
	private Map<String,String> peakHoursMap;
	public Map<Integer, String> getPassengerTypeMap() {
		return passengerTypeMap;
	}

	public void setPassengerTypeMap(Map<Integer, String> passengerTypeMap) {
		this.passengerTypeMap = passengerTypeMap;
	}

	public Map<String, String> getNightServiceMap() {
		return nightServiceMap;
	}

	public void setNightServiceMap(Map<String, String> nightServiceMap) {
		this.nightServiceMap = nightServiceMap;
	}

	public Map<String, String> getPeakHoursMap() {
		return peakHoursMap;
	}

	public void setPeakHoursMap(Map<String, String> peakHoursMap) {
		this.peakHoursMap = peakHoursMap;
	}

	public Map<Integer, String> getRouteNumberMap() {
		return routeNumberMap;
	}

	public void setRouteNumberMap(Map<Integer, String> routeNumberMap) {
		this.routeNumberMap = routeNumberMap;
	}

	public Map<Integer, String> getServiceTypeMap() {
		return serviceTypeMap;
	}

	public void setServiceTypeMap(Map<Integer, String> serviceTypeMap) {
		this.serviceTypeMap = serviceTypeMap;
	}

	@Override
	public void prepare() throws Exception {
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		
		routeNumberMap=fareChartdao.getRouteNumber();
		
		serviceTypeMap=fareChartdao.getServiceType();
		
		/*passengerTypeMap=new HashMap<Integer,String>();
		passengerTypeMap.put(1,"Child");
		passengerTypeMap.put(2,"Adult");
		passengerTypeMap.put(3,"Senior Citizen");*/
		
		nightServiceMap=new HashMap<String,String>();
		nightServiceMap.put("N", "No");
		nightServiceMap.put("Y", "Yes");
		
		peakHoursMap=new HashMap<String,String>();
		peakHoursMap.put("N", "No");
		peakHoursMap.put("Y", "Yes");
		
	}
	
	@SkipValidation
	public String getFloor(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    int id=Integer.parseInt(request.getParameter("id"));
	    RouteDAO routedao = new RouteDAO();
	    listfloor = routedao.getFloor(id);
	    String floorop="";
	    if(listfloor.size()>0){
	    	floorop="<option value='0'>Select</option>";	
	    }
		
		for(int i=0; i<listfloor.size(); i++){
			Floor obj = listfloor.get(i);
			floorop=floorop+"<option value='"+obj.getFloor_id()+"'>"+obj.getFloor_name()+"</option>";
			// busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+"|"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPoint_type_id()+"|"+obj.getFareStage()+"@@@";
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+listfloor.size());
			  out.print(floorop);
		} catch (IOException e) {
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	
	
	@SkipValidation
	public String getBay(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    int id=Integer.parseInt(request.getParameter("id"));
	    RouteDAO routedao = new RouteDAO();
	    listbay = routedao.getBay(id);
	    String floorop="";
	    if(listbay.size()>0){
		floorop="<option value='0'>Select</option>";
	    }
		for(int i=0; i<listbay.size(); i++){
			Bay obj = listbay.get(i);
			floorop=floorop+"<option value='"+obj.getBay_id()+"'>"+obj.getBay_name()+"</option>";
			// busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+"|"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPoint_type_id()+"|"+obj.getFareStage()+"@@@";
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+listbay.size());
			  out.print(floorop);
		} catch (IOException e) {
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	
	@SkipValidation
	public String getPlatforms(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    int id=Integer.parseInt(request.getParameter("id"));
	    RouteDAO routedao = new RouteDAO();
	    listServiceType= routedao.getServiceType();
	    platform= routedao.getPlatform(id);
	    String platformnos="";
		String floorop="";
		if(platform.size()>0){
				floorop+="<input class='form-control input-sm' type='hidden' id='platformsize' name='platformsize' value='"+platform.size()+"'>";
		}
				//String floorop="";
		for(int i=0; i<platform.size(); i++){
			Platform obj = platform.get(i);
			//floorop=floorop+obj.getPlatform_id()+"||"+obj.getPlatform_name()+"@@@";
			platformnos=platformnos+obj.getPlatform_id()+",";
			if(platform.size()>0){
			floorop+= "<div class='row' id='platformdiv' style='display: block;'>"+
						"<div class='col-md-6'>"+
						"<label class='col-md-10 control-label' id='platform"+obj.getPlatform_id()+"'>"+obj.getPlatform_name()+"</label>"+
						"</div>"+
						"<div class='col-md-6'>"+

						"</div>"+
						"<div class='col-md-6'>"+
						"<select class='form-control input-sm' id='servicetype-"+obj.getPlatform_id()+"' name='servicetype-"+obj.getPlatform_id()+"' multiple>";
			}
			
			for(int j=0; j<listServiceType.size(); j++){
				ScheduleService obj1 = listServiceType.get(j);
				floorop=floorop+"<option value='"+obj1.getService_type_id()+"'>"+obj1.getServiceTypeName()+"</option>";
				
			 }
			
			

			floorop+=	"</select>" +
						"</div>"+
						"</div>";
			
			// busDet=busDet+obj.getBusStopName()+"|"+obj.getLatitude()+"|"+obj.getLongitude()+"|"+obj.getId()+"|"+obj.getDeviceCode()+"|"+obj.getBusStopNameKannada()+"|"+obj.getDescription()+"|"+obj.getStatus()+"|"+obj.getLocality()+"|"+obj.getBmtcStatus()+"|"+obj.getStop_direction()+"|"+obj.getLandmark()+"|"+obj.getAreaPopulation()+"|"+obj.getTollZone()+"|"+obj.getPoint_type_id()+"|"+obj.getFareStage()+"@@@";
		 }
		if(platform.size()>0){
		floorop+="<input class='form-control input-sm' type='hidden' id='platformnos' name='platformnos' value='"+platformnos+"'>";
		} 
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Bus stops Size------>"+platform.size());
			  out.print(floorop);
		} catch (IOException e) {
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	@SkipValidation
	public String showRoutemap(){
		//System.out.println("in edit map=======");
		try{
		HttpServletRequest request=ServletActionContext.getRequest();
	    int id=Integer.parseInt(request.getParameter("routeid"));
	    request.getSession().setAttribute("editrouteid",id);
		RouteDAO routedao = new RouteDAO();
		listRouteType= routedao.getRouteType();
		listServiceType= routedao.getServiceType();
		orgchart=routedao.getOrgchart();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "success";
	}
	
	
	
	
	@SkipValidation
	public String showRoutemapAjax(){
		//System.out.println("in edit map=======");
		HttpServletRequest request=ServletActionContext.getRequest();
		PrintWriter out;
	    int id=(Integer) request.getSession().getAttribute("editrouteid");
		RouteDAO routedao = new RouteDAO();
		route=routedao.getRouteInfo(id);
		routeMap=routedao.getRouteMapInfo(id);
		routePoint=routedao.getRoutePointInfo(id);
		routePlatRel=routedao.getRoutePlatRelInfo(id);
		//JSONObject jo = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONArray array3 = new JSONArray();
		JSONObject result = new JSONObject();
		//JSONArray array = new JSONArray();
		//JSONArray array = new JSONArray();
		try {
		for(int i=0;i<route.size();i++){
			JSONArray jo = new JSONArray();
				jo.add( route.get(i).getRoute_id());
				jo.add( route.get(i).getRoute_number());
				jo.add( route.get(i).getStart_stop().getId());
				jo.add( route.get(i).getEnd_stop().getId());
				jo.add( route.get(i).getRoute_type().getRoute_type_id());
				
				jo.add( route.get(i).getRoute_name());
				jo.add( route.get(i).getStatus());
				jo.add( route.get(i).getVia());
				jo.add( route.get(i).getRoute_direction());
				jo.add( route.get(i).getTrimax_distance());
				
				jo.add( route.get(i).getTrimax_time());
				jo.add( route.get(i).getGoogle_distance());
				jo.add( route.get(i).getGoogle_time());
				jo.add( route.get(i).getEffective_from());
				jo.add( route.get(i).getEffective_till());
				jo.add( route.get(i).getDescription());
				
				array.add(jo);
			//	mainObj.put("route", jo);
			//	finalarr.put(jo);
				

		}
		result.put("route",array);
		result.put("routelen",route.size());
		
		//JSONObject jo2 = new JSONObject();
		//jo2.put("length",route.size() );
		//finalarr.put(jo2);
		//mainObj.put("routelenght", jo2);
		for(int i=0;i<routeMap.size();i++){
			//JSONObject map = new JSONObject();	
			JSONArray map = new JSONArray();
			map.add( routeMap.get(i).getRoute_id());
			map.add( routeMap.get(i).getStart_bus_stop_id());
			map.add( routeMap.get(i).getEnd_bus_stop_id());
			map.add( routeMap.get(i).getFare_stage());
			map.add( routeMap.get(i).getRoad_type());
			
			map.add( routeMap.get(i).getGoogle_distance());
			map.add( routeMap.get(i).getVtu_distance());
			map.add( routeMap.get(i).getDistance());
			map.add( routeMap.get(i).getGoogle_time());
			map.add( routeMap.get(i).getVtu_time());
			
			map.add( routeMap.get(i).getTime_to_travel());
			map.add( routeMap.get(i).getStatus());
			map.add( routeMap.get(i).getBus_stop_order());
			map.add( routeMap.get(i).getPath());
			map.add( routeMap.get(i).getEdit_path());
			map.add( routeMap.get(i).getRoute_type());
			list=routedao.getbusStopName(routeMap.get(i).getStart_bus_stop_id());
			map.add(list.get(0).getBusStopName());
			list=routedao.getbusStopName(routeMap.get(i).getEnd_bus_stop_id());
			map.add(list.get(0).getBusStopName());
			map.add( routeMap.get(i).getSchedule_distance());
			map.add( routeMap.get(i).getSchedule_time());
			array1.add(map);

	}
		result.put("routemaplen",routeMap.size());
		result.put("routemap",array1);
		
		
		
		for(int i=0;i<routePoint.size();i++){
			//JSONObject map = new JSONObject();	
			JSONArray point = new JSONArray();
			point.add( routePoint.get(i).getRoute().getRoute_id());
			point.add( routePoint.get(i).getBus_stop_id());
			point.add( routePoint.get(i).getRoute_order());
			point.add( routePoint.get(i).getPoint_status());
			
			point.add( routePoint.get(i).getFare_stage());
			point.add( routePoint.get(i).getSub_stage());
			
			list=routedao.getbusStopName(routePoint.get(i).getBus_stop_id());
			point.add(list.get(0).getBusStopName());
			point.add(list.get(0).getPointtype().getPoint_type_id());
			point.add(list.get(0).getBus_stations().getOrg_chart_id());
			point.add(list.get(0).getLatitude());
			point.add(list.get(0).getLongitude());
			
			array2.add(point);

	}
		result.put("routepointlen",routePoint.size());
		result.put("routepoint",array2);
		
		
		for(int i=0;i<routePlatRel.size();i++){
			JSONArray routeplatrel = new JSONArray();
			routeplatrel.add(routePlatRel.get(i).getRoute_id());
			routeplatrel.add(routePlatRel.get(i).getBus_stop_id());
			routeplatrel.add(routePlatRel.get(i).getDepo_id());
			routeplatrel.add(routePlatRel.get(i).getFloor_id());
			routeplatrel.add(routePlatRel.get(i).getBay_id());
			routeplatrel.add(routePlatRel.get(i).getPlatform_id());
			routeplatrel.add(routePlatRel.get(i).getService_type_id());
			listServiceType=routedao.getServiceName(routePlatRel.get(i).getService_type_id());
			routeplatrel.add(listServiceType.get(0).getServiceTypeName());
			platform=routedao.getPlattformName(routePlatRel.get(i).getPlatform_id());
			routeplatrel.add(platform.get(0).getPlatform_name());
			array3.add(routeplatrel);
		}
		result.put("routeplatrellen",routePlatRel.size());
		result.put("routeplatrel",array3);
		
		HttpServletResponse response=ServletActionContext.getResponse();
		out = response.getWriter();
		 out.print(result);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//route.get(0).getRouteMap().get
		listRouteType= routedao.getRouteType();
		listServiceType= routedao.getServiceType();
		//platform= routedao.getPlatform();
		orgchart=routedao.getOrgchart();
		//System.out.println("iin show map======"+platform.size());
		return null;
	}
	
	
	
	@SkipValidation
	public String showRoutemapAjax1(){
		//System.out.println("in edit map=======");
		HttpServletRequest request=ServletActionContext.getRequest();
		PrintWriter out;
	    int id=Integer.parseInt(request.getParameter("routeid"));
		RouteDAO routedao = new RouteDAO();
		route=routedao.getRouteInfo(id);
		routeMap=routedao.getRouteMapInfo(id);
		routePoint=routedao.getRoutePointInfo(id);
		//routePlatRel=routedao.getRoutePlatRelInfo(id);
		//JSONObject jo = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONArray array3 = new JSONArray();
		JSONObject result = new JSONObject();
		//JSONArray array = new JSONArray();
		//JSONArray array = new JSONArray();
		try {
		for(int i=0;i<route.size();i++){
			JSONArray jo = new JSONArray();
				jo.add( route.get(i).getRoute_id());
				jo.add( route.get(i).getRoute_number());
				jo.add( route.get(i).getStart_stop().getId());
				jo.add( route.get(i).getEnd_stop().getId());
				jo.add( route.get(i).getRoute_type().getRoute_type_id());
				
				jo.add( route.get(i).getRoute_name());
				jo.add( route.get(i).getStatus());
				jo.add( route.get(i).getVia());
				jo.add( route.get(i).getRoute_direction());
				jo.add( route.get(i).getTrimax_distance());
				
				jo.add( route.get(i).getTrimax_time());
				jo.add( route.get(i).getGoogle_distance());
				jo.add( route.get(i).getGoogle_time());
				jo.add( route.get(i).getEffective_from());
				jo.add( route.get(i).getEffective_till());
				jo.add( route.get(i).getDescription());
				
				array.add(jo);
			//	mainObj.put("route", jo);
			//	finalarr.put(jo);
				

		}
		result.put("route",array);
		result.put("routelen",route.size());
		
		//JSONObject jo2 = new JSONObject();
		//jo2.put("length",route.size() );
		//finalarr.put(jo2);
		//mainObj.put("routelenght", jo2);
		for(int i=0;i<routeMap.size();i++){
			//JSONObject map = new JSONObject();	
			JSONArray map = new JSONArray();
			map.add( routeMap.get(i).getRoute_id());
			map.add( routeMap.get(i).getStart_bus_stop_id());
			map.add( routeMap.get(i).getEnd_bus_stop_id());
			map.add( routeMap.get(i).getFare_stage());
			map.add( routeMap.get(i).getRoad_type());
			
			map.add( routeMap.get(i).getGoogle_distance());
			map.add( routeMap.get(i).getVtu_distance());
			map.add( routeMap.get(i).getDistance());
			map.add( routeMap.get(i).getGoogle_time());
			map.add( routeMap.get(i).getVtu_time());
			
			map.add( routeMap.get(i).getTime_to_travel());
			map.add( routeMap.get(i).getStatus());
			map.add( routeMap.get(i).getBus_stop_order());
			map.add( routeMap.get(i).getPath());
			map.add( routeMap.get(i).getEdit_path());
			map.add( routeMap.get(i).getRoute_type());
			list=routedao.getbusStopName(routeMap.get(i).getStart_bus_stop_id());
			map.add(list.get(0).getBusStopName());
			list=routedao.getbusStopName(routeMap.get(i).getEnd_bus_stop_id());
			map.add(list.get(0).getBusStopName());
			map.add( routeMap.get(i).getSchedule_distance());
			map.add( routeMap.get(i).getSchedule_time());
			array1.add(map);

	}
		result.put("routemaplen",routeMap.size());
		result.put("routemap",array1);
		
		
		
		for(int i=0;i<routePoint.size();i++){
			//JSONObject map = new JSONObject();	
			JSONArray point = new JSONArray();
			point.add( routePoint.get(i).getRoute().getRoute_id());
			point.add( routePoint.get(i).getBus_stop_id());
			point.add( routePoint.get(i).getRoute_order());
			point.add( routePoint.get(i).getPoint_status());
			
			point.add( routePoint.get(i).getFare_stage());
			point.add( routePoint.get(i).getSub_stage());
			
			list=routedao.getbusStopName(routePoint.get(i).getBus_stop_id());
			point.add(list.get(0).getBusStopName());
			point.add(list.get(0).getPointtype().getPoint_type_id());
			point.add(list.get(0).getBus_stations().getOrg_chart_id());
			point.add(list.get(0).getLatitude());
			point.add(list.get(0).getLongitude());
			
			array2.add(point);

	}
		result.put("routepointlen",routePoint.size());
		result.put("routepoint",array2);
		
		
		/*for(int i=0;i<routePlatRel.size();i++){
			JSONArray routeplatrel = new JSONArray();
			routeplatrel.add(routePlatRel.get(i).getRoute_id());
			routeplatrel.add(routePlatRel.get(i).getBus_stop_id());
			routeplatrel.add(routePlatRel.get(i).getDepo_id());
			routeplatrel.add(routePlatRel.get(i).getFloor_id());
			routeplatrel.add(routePlatRel.get(i).getBay_id());
			routeplatrel.add(routePlatRel.get(i).getPlatform_id());
			routeplatrel.add(routePlatRel.get(i).getService_type_id());
			listServiceType=routedao.getServiceName(routePlatRel.get(i).getService_type_id());
			routeplatrel.add(listServiceType.get(0).getServiceTypeName());
			platform=routedao.getPlattformName(routePlatRel.get(i).getPlatform_id());
			routeplatrel.add(platform.get(0).getPlatform_name());
			array3.add(routeplatrel);
		}
		result.put("routeplatrellen",routePlatRel.size());*/
		//result.put("routeplatrel",array3);
		
		HttpServletResponse response=ServletActionContext.getResponse();
		out = response.getWriter();
		 out.print(result);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//route.get(0).getRouteMap().get
		listRouteType= routedao.getRouteType();
		listServiceType= routedao.getServiceType();
		//platform= routedao.getPlatform();
		orgchart=routedao.getOrgchart();
		//System.out.println("iin show map======"+platform.size());
		return null;
	}	
	
	
	
	@SkipValidation
	public String getRouteUniNo(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    String routeno=request.getParameter("routeno");
	    String routedir=request.getParameter("route_direction");
	    RouteDAO routedao = new RouteDAO();
	    Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	    try{
	    route = routedao.routeUni(routeno,routedir,session);
	    HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			if(route.size()>0){
		    	 out.print("Route No Already Exist");		
		    }else{
		    	out.print("success");
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    } catch(Exception e){
			System.out.println(e);
		}
		
		finally{
            if(session!=null){
                session.close();
            }
		}
		
		 
		
     

		return null;
	
	}
	
	@SkipValidation
	public String getServiceTable(){
		//System.out.println("in show map=======");
		RouteDAO routedao = new RouteDAO();
		HttpServletRequest request=ServletActionContext.getRequest();
		service_type=routedao.getService();
		rate_master=routedao.getRate();
		String tableinfo="<tr><th>Service Type:</th><th></th><th>Rate Master Version:</th></tr>";
		int temp;int temp1=0;
		for(int i=0;i<service_type.size();i++){
			temp=0;
			for(int j=0;j<rate_master.size();j++){
				
				if(service_type.get(i).getService_type_id()==rate_master.get(j).getServiceTypeId()){
					tableinfo+= "<tr>";
					if(temp==0){
						tableinfo+= "<td>"+service_type.get(i).getServiceTypeName()+"</td><td><input type='checkbox' class='chk' name='servicetypeid'  id='servicetypeid["+temp1+"]' value="+service_type.get(i).getService_type_id()+"-"+rate_master.get(j).getRateMasterId()+"></td><td>"+rate_master.get(j).getVersionNoServiceType()+"</td>";
					}else{
						tableinfo+= "<td></td><td><input type='checkbox' name='servicetypeid' class='chk' id='servicetypeid["+temp1+"]' value="+service_type.get(i).getService_type_id()+"-"+rate_master.get(j).getRateMasterId()+"></td><td>"+rate_master.get(j).getVersionNoServiceType()+"</td>";
					}	
					temp++;
					tableinfo+= "</tr>";
					temp1++;
				}
				
				
				
			}
			
		}
		//tableinfo+= "</tbody>";
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
		    	 out.print(tableinfo);			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	@SkipValidation
	public String getRoadType(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    String roadtypeop="";
	    RouteDAO routedao = new RouteDAO();
	    roadtype = routedao.getRoadType();
	    HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			for(int i=0;i<roadtype.size();i++){
				roadtypeop=roadtypeop+"<option value=\""+roadtype.get(i).getRoad_type_name()+"\">"+roadtype.get(i).getRoad_type_name()+"</option>";
			}
			out = response.getWriter();
			out.print(roadtypeop);	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("finally")
	public String checkScheduledata(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		RouteDAO routedao1 = new RouteDAO();
		String busstops=request.getParameter("busstopid_P");
		String[] arrbusstops=busstops.split(",");
		//System.out.print("arrbusstops data"+busstops);
		routeScheduleData=routedao1.getRouteScheduleInfo(Integer.parseInt(request.getParameter("route_id")));
		//getdistRouteScheduleInfo
		PrintWriter out;
		try {
			out = response.getWriter();
			String data="";
			String schdata="";
			if(routeScheduleData.size()!=0){
				for(int i=0;i<routeScheduleData.size();i++){
					//data=data+routeScheduleData.get(i).getScheduleNumber().getScheduleNumber()+" , ";
					if (( indexOf(routeScheduleData.get(i).getStartPoint(), arrbusstops)!=-1) && (indexOf(routeScheduleData.get(i).getEndPoint(), arrbusstops)!=-1) ) {
					  // System.out.println("found"+routeScheduleData.get(i).getStartPoint()+"@@"+indexOf(routeScheduleData.get(i).getStartPoint(), arrbusstops)+"=="+routeScheduleData.get(i).getEndPoint()+"@@"+(indexOf(routeScheduleData.get(i).getEndPoint(), arrbusstops)!=-1));
					}else{
						//System.out.println("arrbusstops"+arrbusstops.length+"===="+busstops);
						//System.out.println("Not found"+routeScheduleData.get(i).getStartPoint()+"==="+routeScheduleData.get(i).getEndPoint()+"====="+indexOf(routeScheduleData.get(i).getStartPoint(), arrbusstops)+"======"+indexOf(routeScheduleData.get(i).getEndPoint(), arrbusstops));
						if(indexOf(routeScheduleData.get(i).getStartPoint(), arrbusstops)==-1){
							System.out.println("routeScheduleData.get(i).getStartPoint()"+routeScheduleData.get(i).getStartPoint());
							schdata=schdata+" Bus Stop "+routedao1.getbusStopName(routeScheduleData.get(i).getStartPoint()).get(0).getBusStopName()+" Is Included In Schedule No. "+routeScheduleData.get(i).getScheduleNumber().getScheduleNumber()+" .So Please remove This Stop From Schedule";
							System.out.println("aaaaa"+schdata);
							break;
						}
						if(indexOf(routeScheduleData.get(i).getEndPoint(), arrbusstops)==-1){
							schdata=schdata+" Bus Stop "+routedao1.getbusStopName(routeScheduleData.get(i).getEndPoint()).get(0).getBusStopName()+" Is Included In Schedule No. "+routeScheduleData.get(i).getScheduleNumber().getScheduleNumber()+" .So Please remove This Stop From Schedule Before Editing Route.";
							//System.out.println("bbbb"+schdata);
							break;
						}
						break;
					}
				}
				
			}
			routeScheduleData=routedao1.getdistRouteScheduleInfo(Integer.parseInt(request.getParameter("route_id")));
			if(routeScheduleData.size()!=0){
				for(int i=0;i<routeScheduleData.size();i++){
					data=data+routeScheduleData.get(i).getScheduleNumber().getScheduleNumber()+" , ";
					
				}
				
			}
			if(schdata==""){
			out.print("1@"+data);
			}
			else{
				out.print("2@"+schdata);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public int indexOf(int stop, String[] stoparr)
	{
		
	    for (int i=0; i<stoparr.length; i++)
	    {
	    	//System.out.println("needle"+stop+"haystack[i]"+stoparr[i]);
	        if ( Integer.parseInt(stoparr[i])==stop) return i;
	    }

	    return -1;
	}
	
	@SuppressWarnings("finally")
	public String editRoute(){
		//routemap.set
		HttpServletRequest request=ServletActionContext.getRequest();
		int oldrouteid=Integer.parseInt(request.getParameter("route_id"));
		RouteDAO routedao1 = new RouteDAO();
		/*int plateditflag=Integer.parseInt(request.getParameter("routeplatidval"));*/
		//routeMapData=routedao1.getRouteMapInfo(Integer.parseInt(request.getParameter("route_id")));
		//routePointData=routedao1.getRoutePointInfo(Integer.parseInt(request.getParameter("route_id")));
		//routePlatRelData=routedao1.getRoutePlatRelInfo(Integer.parseInt(request.getParameter("route_id")));
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			session.getTransaction().setTimeout(20);
			session.getTransaction().begin();
			RoutePoint routepoint =new RoutePoint();
			HttpServletResponse response=ServletActionContext.getResponse();
			PrintWriter out;
			try{
				out = response.getWriter();
		
				int googledist=0,googletime=0,aquadist=0,aquatime=0;
				int usrsessionid=(Integer)request.getSession().getAttribute("userid");
				String[] start_bus_stop_id_M=request.getParameter("start_bus_stop_id_M").split(",");
				String[] end_bus_stop_id_M=request.getParameter("end_bus_stop_id_M").split(",");
				String[] road_type_M=request.getParameter("road_type_M").split(",");
				String[] google_distance_M=request.getParameter("google_distance_M").split(",");
				String[] vtu_distance_M=request.getParameter("vtu_distance_M").split(",");
				String[] distance_M =request.getParameter("distance_M").split(",");
				String[] google_time_M=request.getParameter("google_time_M").split(",");
				String[] vtu_time_M=request.getParameter("vtu_time_M").split(",");
				String[] time_to_travel_M=request.getParameter("time_to_travel_M").split(",");
				String[] status_M=request.getParameter("status_M").split(",");
				String[] path_M=request.getParameter("path_M").split(",");
				String[] route_type_M=request.getParameter("route_type_M").split(",");
				String[] schedule_time_M=request.getParameter("schedule_time_M").split(",");
				String[] schedule_distance_M=request.getParameter("schedule_distance_M").split(",");
				String[] edit_path_M=request.getParameter("edit_path_M").split(",");
				String[] busstopid_P=request.getParameter("busstopid_P").split(",");
				String[] fare_P=request.getParameter("fare_P").split(",");
				String[] status_P=request.getParameter("status_P").split(",");
				String[] substage_P=request.getParameter("substage_P").split(",");
				String[] depotpl=request.getParameter("depotpl").split(",");
				String[] floorpl=request.getParameter("floorpl").split(",");
				String[] baypl=request.getParameter("baypl").split(",");
				String[] platformpl=request.getParameter("platformpl").split(",");
				String[] servicepl=request.getParameter("servicepl").split(",");
				String[] busplatformidpl=request.getParameter("busplatformidpl").split(",");
				/*if(plateditflag==1){
					int uprouteplatflag=routedao1.updatePrevPlatform(Integer.parseInt(request.getParameter("route_id")));
					if(uprouteplatflag==0){
						System.out.println("in 222222222222");
						//System.out.println("updatedata"+"@Route Created Successfully@"+Integer.parseInt(request.getParameter("route_number"))+"@"+Integer.parseInt(request.getParameter("route_id")));
					String updatedata=updateRoutePlatform(depotpl, busplatformidpl, floorpl, baypl, platformpl, servicepl, Integer.parseInt(request.getParameter("route_id")));
					
					out.print("@Route Created Successfully@"+request.getParameter("route_number")+"@"+Integer.parseInt(request.getParameter("route_id")));
					}else{
						System.out.println("in 1111111111111");
					out.print("@Database Error in Editing Route.Please Try Again.@"+request.getParameter("route_number")+"@"+Integer.parseInt(request.getParameter("route_id")));	
					}
					System.out.println("aaaaaaaaaaaaa");
					return null;
				}*/
				routeData=routedao1.getRouteInfo(Integer.parseInt(request.getParameter("route_id")));
				routeScheduleData=routedao1.getRouteScheduleInfo(Integer.parseInt(request.getParameter("route_id")));
				if(routeScheduleData.size()==0){
					routedao1.updateRouteFareChartData(Integer.parseInt(request.getParameter("route_id")));
				}else{
					
				}
				Route routes =new Route();
				routes.setRoute_number(request.getParameter("route_number"));
				//busstops.setId(Integer.parseInt(request.getParameter("start_point_id")));
			   // System.out.println(request.getParameter("start_point_id"));
			   // int id =Integer.parseInt(request.getParameter("start_point_id"));
			    busstops=new BusStops(); 
			    busstops.setId(Integer.parseInt(request.getParameter("start_point_id")));
			    routes.setStart_stop(busstops);
			    busstops=new BusStops(); 
			    busstops.setId(Integer.parseInt(request.getParameter("end_point_id")));
			    routes.setEnd_stop(busstops);
				//busstops.setId(Integer.parseInt(request.getParameter("end_point_id")));
			    routetype= new RouteType();
			    routetype.setRoute_type_id(Integer.parseInt(request.getParameter("route_type_id")));
				routes.setRoute_type(routetype);
				routes.setRoute_name(request.getParameter("route_name"));
				routes.setStatus(request.getParameter("status"));
				routes.setVia(request.getParameter("via"));
				routes.setCreated_by(usrsessionid);
				routes.setCreated_date(new Date());
				routes.setDeleted_status(Integer.parseInt(request.getParameter("deleted_status")));
				routes.setRoute_direction(request.getParameter("route_direction"));
				routes.setDescription(request.getParameter("route_description"));
				routes.setEffective_from(request.getParameter("effect_start_date_route"));
				routes.setRoute_geofence("N");
				if(request.getParameter("effect_end_date_route")=="0000-00-00"){
					routes.setEffective_till("");
				}else{
					routes.setEffective_till(request.getParameter("effect_end_date_route"));	
				}
				
				RouteDAO routedao = new RouteDAO();
//				int dupliRouteNo=routedao.getDuplicateRouteCnt(routes.getEffective_from(), routes.getEffective_till(), Integer.parseInt(request.getParameter("route_id")), routes.getRoute_number(), routes.getRoute_direction(),session);
//				if(dupliRouteNo>0){
//					out = response.getWriter();
//					out.print("1@This Route Is Already Active For Given Date Range..@"+routes.getRoute_number()+"@"+routes.getRoute_id());
//					return null;
//				}
				//routes.setRpolypath
				int uproute=routedao.updateRoute(Integer.parseInt(request.getParameter("route_id")),routes.getEffective_from(),session,usrsessionid);
				//session = HibernateUtil.getSession("hibernate.cfg.xml");
				String res=routedao.addRoute(routes,session);
					//System.out.println("start_bus_stop_id_M.length====="+start_bus_stop_id_M.length);
				RoutePlatformRel routepl=new RoutePlatformRel();
				RouteMap routemap =new RouteMap();
				for(int j=0;j<start_bus_stop_id_M.length;j++){
				//System.out.println("j====="+j);
					routemap= new RouteMap();
					routemap.setRoute_id(routes.getRoute_id());
					routemap.setStart_bus_stop_id(Integer.parseInt(start_bus_stop_id_M[j]));
					routemap.setEnd_bus_stop_id(Integer.parseInt(end_bus_stop_id_M[j]));
					routemap.setRoad_type(road_type_M[j]);
					routemap.setGoogle_distance((int) (Double.parseDouble(google_distance_M[j])*1000));
					routemap.setVtu_distance((int) (Double.parseDouble(vtu_distance_M[j])*1000));
					routemap.setDistance((int) (Double.parseDouble(google_distance_M[j])*1000)+(int) (Double.parseDouble(vtu_distance_M[j])*1000));
					routemap.setGoogle_time((int) (Double.parseDouble(google_time_M[j])));
					routemap.setVtu_time((int) (Double.parseDouble(vtu_time_M[j])));
					routemap.setTime_to_travel((int) (Double.parseDouble(google_time_M[j]))+(int) (Double.parseDouble(vtu_time_M[j])));
					routemap.setStatus(status_M[j]);
					routemap.setBus_stop_order(j+1);
					routemap.setPath(path_M[j]);
					routemap.setRoute_type(route_type_M[j]);
					routemap.setSchedule_time(Integer.parseInt(schedule_time_M[j]));
					routemap.setSchedule_distance(Double.parseDouble(schedule_distance_M[j]));
					routemap.setEdit_path(edit_path_M[j]);
					String resmap=routedao.addRoute(routemap,session);
					googledist=googledist+routemap.getGoogle_distance();
					googletime=googletime+routemap.getGoogle_time();
					aquadist=aquadist+routemap.getVtu_distance();
					aquatime=aquatime+routemap.getVtu_time();
				
				}
		 //System.out.println("routes.getRouteMap().size()====="+routes.getRouteMap().size());
				for(int i=0;i<busstopid_P.length;i++){
					//System.out.println("i"+i);
					routepoint= new RoutePoint();
					Route r1= new Route();
					r1.setRoute_id(routes.getRoute_id());
					routepoint.setRoute(r1);
					//routepoint.setRoute_id(routes.getRoute_id());
					routepoint.setBus_stop_id(Integer.parseInt(busstopid_P[i]));
					routepoint.setRoute_order(i+1);
					routepoint.setPoint_status(status_P[i]);
					routepoint.setFare_stage(fare_P[i]);
					routepoint.setSub_stage(substage_P[i]);
					routepoint.setCreated_by(usrsessionid);
					routepoint.setCreated_date(new Date());
					routepoint.setDeleted_status(0);
					//System.out.println("routepoint====="+routepoint.getBus_stop_id()+routepoint.toString());
					//routes.getRoutePoint().add(routepoint);
					String respoint=routedao.addRoute(routepoint,session);
				}
				session.getTransaction().commit();
				/* if(session!=null){
		             session.close();
		         }*/
				Route routes1 =new Route();
				routes1.setRoute_id(routes.getRoute_id());
				routes1.setGoogle_distance(googledist);
				routes1.setGoogle_time(googletime);
				routes1.setTrimax_distance(aquadist);
				routes1.setTrimax_time(aquatime);
				/*GeometryFactory factory = new GeometryFactory(new PrecisionModel(10000.0), 31300);
			    CoordinateArraySequence coords = new CoordinateArraySequence(new Coordinate[] { new Coordinate(12.0, 34.23),
			            new Coordinate(12.000, 54.555), new Coordinate(-0.01, 0.0) });
			    LineString p = new LineString(coords, factory);
				routes1.setRoute_string(p);*/
				String resroutefinal=routedao.edit(routes1,routes.getRoute_id());
				String coord=request.getParameter("route_string");
						//"LINESTRING(12.97943 77.57235,12.979190000000001 77.57254)";
				//session = HibernateUtil.getSession("hibernate.cfg.xml");
				session.getTransaction().begin();
				int resroutefinal1=routedao.editCoord(routes.getRoute_id(),coord, session);
				session.getTransaction().commit();
				if(Integer.parseInt(request.getParameter("editscheduleflag"))==1){
					session.getTransaction().begin();
					routedao.updateScheduledata(oldrouteid,routes.getRoute_id(),session);
					session.getTransaction().commit();
				}
				//System.out.println("depotpl.length"+depotpl.length);
				//session = HibernateUtil.getSession("hibernate.cfg.xml");
				//session.beginTransaction();
				for(int i=0;i<depotpl.length;i++){
					//System.out.println("i"+i);
					routepl=new RoutePlatformRel();
		            if(!depotpl[i].equals("")){
						routepl.setRoute_id(routes.getRoute_id());
						routepl.setBus_stop_id(Integer.parseInt(busplatformidpl[i]));
						routepl.setDepo_id(Integer.parseInt(depotpl[i]));
						routepl.setFloor_id(Integer.parseInt(floorpl[i]));
						routepl.setBay_id(Integer.parseInt(baypl[i]));
						routepl.setPlatform_id(Integer.parseInt(platformpl[i]));
						//busplatformidpl.
						//System.out.println("servicepl[i]"+servicepl[i]);
						//String [] plarr=servicepl[i].split("@");
						//System.out.println("servicepl[i]"+plarr.length);
						//for(int j=0;j<plarr.length;j++){
						//System.out.println("plarr[j]==========="+plarr[j]);
						routepl.setService_type_id(Integer.parseInt(servicepl[i]));
						routepl.setStatus("Y");
						String resroutePlatrel=routedao.addPlatform(routepl);
						//}
		            }
			
			//String respoint=routedao.addRoute(routepoint);
				}
				// session.getTransaction().commit();
				//System.out.println("routes.getRoutePoint()"+routes.getRoutePoint().size());
				out.print("@Route Created Successfully@"+routes.getRoute_number()+"@"+routes.getRoute_id());
				String delRouteId=routedao.getDeleteRouteId();
				if(delRouteId!=null){
					routedao.delRouteFareChart(delRouteId);
				}
			}
			catch(Exception e){
				out = response.getWriter();
				out.print("@Database Error in Editing Route.Please Try Again.@"+routes.getRoute_number()+"@"+routes.getRoute_id());
				e.printStackTrace();
				session.getTransaction().rollback();
			//	System.out.print(e.printStackTrace());
				out.print("@Database Error in Editing Route.Please Try Again.@"+routes.getRoute_number()+"@"+routes.getRoute_id());
			}finally{
				return null;	
			}
		}catch(Exception e){
			session.getTransaction().rollback();
			PrintWriter out;
			HttpServletResponse response=ServletActionContext.getResponse();
			try {
				out = response.getWriter();
				out.print("@Database Error in Editing Route.Please Try Again.@"+routes.getRoute_number()+"@"+routes.getRoute_id());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				out = response.getWriter();
				out.print("@Database Error in Editing Route.Please Try Again.@"+routes.getRoute_number()+"@"+routes.getRoute_id());
			}
			
		}
		finally{
			 if(session!=null){
	              //  session.close();
	            }
			return null;
		}
	}
	@SkipValidation
	public String updateRoutePlatform(String []depotpl, String[] busplatformidpl, String[] floorpl, String[] baypl, String[] platformpl, String[] servicepl, int routeid){
		//System.out.println("in edit map=======");
		
		RouteDAO routedao = new RouteDAO();
		routedao.updatePrevPlatform(routeid);
		RoutePlatformRel routepl=new RoutePlatformRel();
		for(int i=0;i<depotpl.length;i++){
			routepl=new RoutePlatformRel();
            if(!depotpl[i].equals("")){
			routepl.setRoute_id(routeid);
			routepl.setBus_stop_id(Integer.parseInt(busplatformidpl[i]));
			routepl.setDepo_id(Integer.parseInt(depotpl[i]));
			routepl.setFloor_id(Integer.parseInt(floorpl[i]));
			routepl.setBay_id(Integer.parseInt(baypl[i]));
			routepl.setPlatform_id(Integer.parseInt(platformpl[i]));
			
				routepl.setService_type_id(Integer.parseInt(servicepl[i]));
				routepl.setStatus("Y");
				try{
				String resroutePlatrel=routedao.addPlatform(routepl);
				}catch(Exception e){
					e.printStackTrace();
					return "error";
				}
			//}
			
            }
			
           
			//String respoint=routedao.addRoute(routepoint);
		}
		return "success";
	}
	
	@SkipValidation
	public String copyRoutemap(){
		//System.out.println("in edit map=======");
		HttpServletRequest request=ServletActionContext.getRequest();
		RouteDAO routedao = new RouteDAO();
		listRouteType= routedao.getRouteType();
		listServiceType= routedao.getServiceType();
		orgchart=routedao.getOrgchart();
		request.getSession().setAttribute("copyrouteid", Integer.parseInt(request.getParameter("routeid")));
		return "success";
	}
	
	@SkipValidation
	public String getBusStopRouteList(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    int id=Integer.parseInt(request.getParameter("busstopid"));
	    RouteDAO routedao = new RouteDAO();
	    String routeform=routedao.findBusStopRoute(id);
	    
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
		    out.print(routeform);
		} catch (IOException e) {
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	@SkipValidation
	public String getRouteBusStopsList(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    int id=(Integer) request.getSession().getAttribute("copyrouteid");
	    int busstopid=0;
	    RouteDAO routedao = new RouteDAO();
	    String routeform=routedao.findRouteStopList(id,busstopid);
	    
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
		    out.print(routeform);
		} catch (IOException e) {
			e.printStackTrace();
		}
     

		return null;
	
	}
	
	@SkipValidation
	public String getRouteCopyInfo(){
		//System.out.println("in edit map=======");
				HttpServletRequest request=ServletActionContext.getRequest();
				PrintWriter out;
			    int id=Integer.parseInt(request.getParameter("routeid"));
			    int startstopid=Integer.parseInt(request.getParameter("startbusstopid"));
			    int endstopid=Integer.parseInt(request.getParameter("endbusstopid"));
				RouteDAO routedao = new RouteDAO();
				route=routedao.getRouteInfo(id);
				routeMap=routedao.getRouteMapInfo(id);
				routePoint=routedao.getRoutePointInfo(id);
				routePlatRel=routedao.getRoutePlatRelInfo(id);
				//JSONObject jo = new JSONObject();
				JSONArray array = new JSONArray();
				JSONArray array1 = new JSONArray();
				JSONArray array2 = new JSONArray();
				JSONArray array3 = new JSONArray();
				JSONObject result = new JSONObject();
				//JSONArray array = new JSONArray();
				//JSONArray array = new JSONArray();
				try {
				for(int i=0;i<route.size();i++){
					JSONArray jo = new JSONArray();
						jo.add( route.get(i).getRoute_id());
						jo.add( route.get(i).getRoute_number());
						jo.add( route.get(i).getStart_stop().getId());
						jo.add( route.get(i).getEnd_stop().getId());
						jo.add( route.get(i).getRoute_type().getRoute_type_id());
						
						jo.add( route.get(i).getRoute_name());
						jo.add( route.get(i).getStatus());
						jo.add( route.get(i).getVia());
						jo.add( route.get(i).getRoute_direction());
						jo.add( route.get(i).getTrimax_distance());
						
						jo.add( route.get(i).getTrimax_time());
						jo.add( route.get(i).getGoogle_distance());
						jo.add( route.get(i).getGoogle_time());
						jo.add( route.get(i).getEffective_from());
						jo.add( route.get(i).getEffective_till());
						jo.add( route.get(i).getDescription());
						
						array.add(jo);
					//	mainObj.put("route", jo);
					//	finalarr.put(jo);
						

				}
				result.put("route",array);
				result.put("routelen",route.size());
				
				//JSONObject jo2 = new JSONObject();
				//jo2.put("length",route.size() );
				//finalarr.put(jo2);
				//mainObj.put("routelenght", jo2);
				int showroutemap=0;
				int countroutemap=0;
				for(int i=0;i<routeMap.size();i++){
					if(startstopid==routeMap.get(i).getStart_bus_stop_id()){
						showroutemap=1;
					}
					if(showroutemap==1){
					//JSONObject map = new JSONObject();	
					JSONArray map = new JSONArray();
					map.add( routeMap.get(i).getRoute_id());
					map.add( routeMap.get(i).getStart_bus_stop_id());
					map.add( routeMap.get(i).getEnd_bus_stop_id());
					map.add( routeMap.get(i).getFare_stage());
					map.add( routeMap.get(i).getRoad_type());
					
					map.add( routeMap.get(i).getGoogle_distance());
					map.add( routeMap.get(i).getVtu_distance());
					map.add( routeMap.get(i).getDistance());
					map.add( routeMap.get(i).getGoogle_time());
					map.add( routeMap.get(i).getVtu_time());
					
					map.add( routeMap.get(i).getTime_to_travel());
					map.add( routeMap.get(i).getStatus());
					map.add( routeMap.get(i).getBus_stop_order());
					map.add( routeMap.get(i).getPath().replace("\\", "\\\\"));
					map.add( routeMap.get(i).getEdit_path());
					map.add( routeMap.get(i).getRoute_type());
					list=routedao.getbusStopName(routeMap.get(i).getStart_bus_stop_id());
					map.add(list.get(0).getBusStopName());
					list=routedao.getbusStopName(routeMap.get(i).getEnd_bus_stop_id());
					map.add(list.get(0).getBusStopName());
					map.add( routeMap.get(i).getSchedule_distance());
					map.add( routeMap.get(i).getSchedule_time());
					array1.add(map);
					if(endstopid==routeMap.get(i).getEnd_bus_stop_id()){
						break;
					}
					countroutemap++;
					}
					

			}
				result.put("routemaplen",countroutemap+1);
				result.put("routemap",array1);
				
				
				int showroutepoint=0;
				int countroutepoint=0;
				for(int i=0;i<routePoint.size();i++){
					//JSONObject map = new JSONObject();
					if(startstopid==routePoint.get(i).getBus_stop_id()){
						showroutepoint=1;
					}
					if(showroutepoint==1){
					JSONArray point = new JSONArray();
					point.add( routePoint.get(i).getRoute().getRoute_id());
					point.add( routePoint.get(i).getBus_stop_id());
					point.add( routePoint.get(i).getRoute_order());
					point.add( routePoint.get(i).getPoint_status());
					
					point.add( routePoint.get(i).getFare_stage());
					point.add( routePoint.get(i).getSub_stage());
					
					list=routedao.getbusStopName(routePoint.get(i).getBus_stop_id());
					point.add(list.get(0).getBusStopName());
					point.add(list.get(0).getPointtype().getPoint_type_id());
					point.add(list.get(0).getBus_stations().getOrg_chart_id());
					point.add(list.get(0).getLatitude());
					point.add(list.get(0).getLongitude());
					
					array2.add(point);
					if(endstopid==routePoint.get(i).getBus_stop_id()){
						break;
					}
					countroutepoint++;
					}

			}
				result.put("routepointlen",countroutepoint+1);
				result.put("routepoint",array2);
				
				
				/*for(int i=0;i<routePlatRel.size();i++){
					JSONArray routeplatrel = new JSONArray();
					routeplatrel.add(routePlatRel.get(i).getRoute_id());
					routeplatrel.add(routePlatRel.get(i).getBus_stop_id());
					routeplatrel.add(routePlatRel.get(i).getDepo_id());
					routeplatrel.add(routePlatRel.get(i).getFloor_id());
					routeplatrel.add(routePlatRel.get(i).getBay_id());
					routeplatrel.add(routePlatRel.get(i).getPlatform_id());
					routeplatrel.add(routePlatRel.get(i).getService_type_id());
					listServiceType=routedao.getServiceName(routePlatRel.get(i).getService_type_id());
					routeplatrel.add(listServiceType.get(0).getServiceTypeName());
					platform=routedao.getPlattformName(routePlatRel.get(i).getPlatform_id());
					routeplatrel.add(platform.get(0).getPlatform_name());
					array3.add(routeplatrel);
				}
				result.put("routeplatrellen",routePlatRel.size());
				result.put("routeplatrel",array3);*/
				
				HttpServletResponse response=ServletActionContext.getResponse();
				out = response.getWriter(); 
				 out.print(result);
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//route.get(0).getRouteMap().get
				listRouteType= routedao.getRouteType();
				listServiceType= routedao.getServiceType();
				//platform= routedao.getPlatform();
				orgchart=routedao.getOrgchart();
				//System.out.println("iin show map======"+platform.size());
				return null;
	
	}
	
	public String deleteRoute(){
		try{
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date = new Date();
		 String datefil= new SimpleDateFormat("dd/MM/yyyy").format(date);
		 if(request.getParameter("datecri")==null){
			 request.getSession().setAttribute("route_filter_date",datefil ); 
		 }else{
			 request.getSession().setAttribute("route_filter_date", request.getParameter("datecri"));	 
		 }
		int routeId=Integer.parseInt(request.getParameter("routeid"));
		RouteDAO routedao = new RouteDAO();
		String Scheduleno=routedao.getdistRouteScheduleDetailsInfo(routeId);
		//routeFormfourdata=routedao.getdistFormFourInfo(routeId);
		String msg="";
		if(Scheduleno.length()>0){
			msg="Route Is Used In ";
			//for(int i=0;i<Scheduleno.length();i++){
				msg=msg+Scheduleno;
			//}
			msg=msg+" Schedules. Please Delete Schedule Before Deleting Route.";
			addActionError(msg);
		} 
		/*if(routeFormfourdata.size()>0){
			msg="Route Is Used In ";
			for(int i=0;i<routeFormfourdata.size();i++){
				msg=msg+routeFormfourdata.get(i).getScheduleNumberName()+", ";
			}
			msg=msg+" Form Fours. Please Delete Form Fours Before Deleting Route.";
			addActionError(msg);
		}*/
		else{
			routedao.delRouteFareChart(String.valueOf(routeId));
			msg=msg+"RouteId "+routeId+" Is Deleted Successfully.";
			addActionMessage(msg);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "success";
	}
	
	
	@SkipValidation
	public String showRouteInfoAjax(){
		HttpServletRequest request=ServletActionContext.getRequest();
		PrintWriter out;
	    int id=Integer.parseInt(request.getParameter("routeid"));
	    int startstopid=Integer.parseInt(request.getParameter("start_point"));
	    int endstopid=Integer.parseInt(request.getParameter("end_point"));
		RouteDAO routedao = new RouteDAO();
		route=routedao.getRouteInfo(id);
		routeMap=routedao.getRouteMapInfo(id);
		routePoint=routedao.getRoutePointInfo(id);
		routePlatRel=routedao.getRoutePlatRelInfo(id);
		//JSONObject jo = new JSONObject();
		JSONArray array = new JSONArray();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONArray array3 = new JSONArray();
		JSONObject result = new JSONObject();
		//JSONArray array = new JSONArray();
		//JSONArray array = new JSONArray();
		try {
		for(int i=0;i<route.size();i++){
			JSONArray jo = new JSONArray();
				jo.add( route.get(i).getRoute_id());
				jo.add( route.get(i).getRoute_number());
				jo.add( route.get(i).getStart_stop().getId());
				jo.add( route.get(i).getEnd_stop().getId());
				jo.add( route.get(i).getRoute_type().getRoute_type_id());
				
				jo.add( route.get(i).getRoute_name());
				jo.add( route.get(i).getStatus());
				jo.add( route.get(i).getVia());
				jo.add( route.get(i).getRoute_direction());
				jo.add( route.get(i).getTrimax_distance());
				
				jo.add( route.get(i).getTrimax_time());
				jo.add( route.get(i).getGoogle_distance());
				jo.add( route.get(i).getGoogle_time());
				jo.add( route.get(i).getEffective_from());
				jo.add( route.get(i).getEffective_till());
				jo.add( route.get(i).getDescription());
				
				array.add(jo);
			//	mainObj.put("route", jo);
			//	finalarr.put(jo);
				

		}
		result.put("route",array);
		result.put("routelen",route.size());
		
		//JSONObject jo2 = new JSONObject();
		//jo2.put("length",route.size() );
		//finalarr.put(jo2);
		//mainObj.put("routelenght", jo2);
		int strtflagmap=1;
		int endflagmap=1;
		for(int i=0;i<routeMap.size();i++){
			//JSONObject map = new JSONObject();
			if((routeMap.get(i).getStart_bus_stop_id()==startstopid)|| strtflagmap==0){
				strtflagmap=0;
			}
			if(endflagmap==0){
				break;
			}
			if(strtflagmap==0){
			JSONArray map = new JSONArray();
			map.add( routeMap.get(i).getRoute_id());
			map.add( routeMap.get(i).getStart_bus_stop_id());
			map.add( routeMap.get(i).getEnd_bus_stop_id());
			map.add( routeMap.get(i).getFare_stage());
			map.add( routeMap.get(i).getRoad_type());
			
			map.add( routeMap.get(i).getGoogle_distance());
			map.add( routeMap.get(i).getVtu_distance());
			map.add( routeMap.get(i).getDistance());
			map.add( routeMap.get(i).getGoogle_time());
			map.add( routeMap.get(i).getVtu_time());
			
			map.add( routeMap.get(i).getTime_to_travel());
			map.add( routeMap.get(i).getStatus());
			map.add( routeMap.get(i).getBus_stop_order());
			map.add( routeMap.get(i).getPath());
			map.add( routeMap.get(i).getEdit_path());
			map.add( routeMap.get(i).getRoute_type());
			list=routedao.getbusStopName(routeMap.get(i).getStart_bus_stop_id());
			map.add(list.get(0).getBusStopName());
			list=routedao.getbusStopName(routeMap.get(i).getEnd_bus_stop_id());
			map.add(list.get(0).getBusStopName());
			map.add( routeMap.get(i).getSchedule_distance());
			map.add( routeMap.get(i).getSchedule_time());
			array1.add(map);
			if((routeMap.get(i).getEnd_bus_stop_id()==endstopid)){
				endflagmap=0;
			}
			}

	}
		result.put("routemaplen",routeMap.size());
		result.put("routemap",array1);
		
		int strtflag=1;
		int endflag=1;
		
		for(int i=0;i<routePoint.size();i++){
			//JSONObject map = new JSONObject();	
			if((routePoint.get(i).getBus_stop_id()==startstopid)|| strtflag==0){
				strtflag=0;
			}
			if(endflag==0){
				break;
			}
			if(strtflag==0){
				list=routedao.getbusStopName(routePoint.get(i).getBus_stop_id());
				//if(list.get(0).getPointtype().getPoint_type_id()!=2 && list.get(0).getPointtype().getPoint_type_id()!=13){
					JSONArray point = new JSONArray();
					point.add( routePoint.get(i).getRoute().getRoute_id());
					point.add( routePoint.get(i).getBus_stop_id());
					point.add( routePoint.get(i).getRoute_order());
					point.add( routePoint.get(i).getPoint_status());
					
					point.add( routePoint.get(i).getFare_stage());
					point.add( routePoint.get(i).getSub_stage());
					
					
					point.add(list.get(0).getBusStopName());
					point.add(list.get(0).getPointtype().getPoint_type_id());
					point.add(list.get(0).getBus_stations().getOrg_chart_id());
					point.add(list.get(0).getLatitude());
					point.add(list.get(0).getLongitude());
					
						array2.add(point);
					if((routePoint.get(i).getBus_stop_id()==endstopid)){
						endflag=0;
					}
				//}
			}
		}
		result.put("routepointlen",routePoint.size());
		result.put("routepoint",array2);
		
		
		/*for(int i=0;i<routePlatRel.size();i++){
			JSONArray routeplatrel = new JSONArray();
			routeplatrel.add(routePlatRel.get(i).getRoute_id());
			routeplatrel.add(routePlatRel.get(i).getBus_stop_id());
			routeplatrel.add(routePlatRel.get(i).getDepo_id());
			routeplatrel.add(routePlatRel.get(i).getFloor_id());
			routeplatrel.add(routePlatRel.get(i).getBay_id());
			routeplatrel.add(routePlatRel.get(i).getPlatform_id());
			routeplatrel.add(routePlatRel.get(i).getService_type_id());
			listServiceType=routedao.getServiceName(routePlatRel.get(i).getService_type_id());
			routeplatrel.add(listServiceType.get(0).getServiceTypeName());
			platform=routedao.getPlattformName(routePlatRel.get(i).getPlatform_id());
			routeplatrel.add(platform.get(0).getPlatform_name());
			array3.add(routeplatrel);
		}
		result.put("routeplatrellen",routePlatRel.size());
		result.put("routeplatrel",array3);*/
		
		HttpServletResponse response=ServletActionContext.getResponse();
		out = response.getWriter();
		 out.print(result);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//route.get(0).getRouteMap().get
		/*listRouteType= routedao.getRouteType();
		listServiceType= routedao.getServiceType();
		//platform= routedao.getPlatform();
		orgchart=routedao.getOrgchart();*/
		//System.out.println("iin show map======"+platform.size());
		return null;
	}
	
	
	@SkipValidation
	public String savePlatformChanges(){
		HttpServletRequest request=ServletActionContext.getRequest();
		int routeid=Integer.parseInt(request.getParameter("route_id"));
		String[] depotpl=request.getParameter("depotpl").split(",");
		String[] floorpl=request.getParameter("floorpl").split(",");
		String[] baypl=request.getParameter("baypl").split(",");
		String[] platformpl=request.getParameter("platformpl").split(",");
		String[] servicepl=request.getParameter("servicepl").split(",");
		String[] busplatformidpl=request.getParameter("busplatformidpl").split(",");
		
		String retvalue=updateRoutePlatform(depotpl, busplatformidpl, floorpl, baypl, platformpl, servicepl, routeid);
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try{
		out = response.getWriter();
		
		if(retvalue=="success"){
			 out.print("Platform Added Successfully");
		}else{
			 out.print("Database Error...Retry Again");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String showRouteInfo(){
		//System.out.println("in execute");
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date = new Date();
		 String datefil= new SimpleDateFormat("dd/MM/yyyy").format(date);
		 if(request.getParameter("datecri")==null){
			 request.getSession().setAttribute("route_filter_date",datefil ); 
		 }else{
			 request.getSession().setAttribute("route_filter_date", request.getParameter("datecri"));	 
		 }
		String busstopid = request.getParameter("busstopId");
		String busStopName = request.getParameter("busStopName");
		//System.out.print("BusId"+busstopid+"NAme"+busStopName);
		setBusstopId(busstopid);
		setBusStopName(busStopName.toUpperCase());
		RouteDAO routedao = new RouteDAO();
		route = routedao.getRouteList();
		listRouteType= routedao.getRouteType();
		//System.out.println("in execute======="+route.size());
		return "success";
	}

	public String showVehicles(){
		HttpServletRequest req=ServletActionContext.getRequest();
		String Lat = req.getParameter("Lat").toString();
		String Long = req.getParameter("Long").toString();
		String id =req.getParameter("id");
		String busStopName = req.getParameter("busStopName");
		//System.out.print("BusId"+busstopid+"NAme"+busStopName);
		
		setBusStopName(busStopName.toUpperCase());
		setBusstopId(id);
		setLatitutde(Lat);
		setLongitude(Long);
		//Insert into DB geofence_storage..
		//Getting ORG_ID and ORG_Type.
		try{
		VehicleAlertConfigDAO vad =new VehicleAlertConfigDAO();
		String arr[] = vad.getOrgChartIdandType(id);
		
		vad.inserGeofenceStorage(id, busStopName.toUpperCase(), Double.parseDouble(Lat), Double.parseDouble(Long), arr[0], arr[1]);
		}catch(Exception ex){
			
		}
		//
		
		return "success";
		
	}
	
	public String getRouteList(){
		return "success";
	}
	
	public String getRouteDropdownList() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO routedao = new RouteDAO();
		List<Route> listroute = routedao.getRouteListDropDown();
		/*String regionTypeAjaxString = "<option value='0'>----Select----</option>";
			
		for (int i = 0; i < 100; i++) {
			regionTypeAjaxString += "<option value=" + route.get(i).getRoute_id() +"-"+route.get(i).getStart_stop().getId()+"-"+route.get(i).getEnd_stop().getId()+ ">" + route.get(i).getRoute_number()+"-"+route.get(i).getRoute_direction() + "</option>";
		}*/
		
		List<Route> list1 = new ArrayList<Route>();
		for(Route route : listroute){
			Route route1 = new Route();
			//bus.setId(bustop.getId());
			//bus.setValue("a"+i);
			route1.setRoute_number(route.getRoute_number());
			route1.setRoute_name(route.getRoute_id() +"-"+route.getStart_stop().getId()+"-"+route.getEnd_stop().getId());
			
			list1.add(route1);
			//i+=1;
			//System.out.println("Bus stops Size------>"+bus.getBusStopName());
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("aa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	

    public String getVehicleData() {

    		PrintWriter out = null;
    	 try {
    		 ResourceBundle resopurceBundle = ResourceBundle.getBundle("com.trimax.its.route.action.linearTracking");
    		 HttpServletRequest req=ServletActionContext.getRequest();
    		 String routeName = req.getParameter("routeName").toString();
    		 
    		 Map map = new HashMap();
    		 map.put("routeNo", routeName.split(":")[0]);
    		 map.put("direction", routeName.split(":")[1]);
    		 
    			URL url = new URL("http://"+resopurceBundle.getString("tracking_loaction")+"/listnerservice/receive/routeLinetracking/"+routeName);   
//    			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//    			conn.setRequestMethod("POST");
//    			conn.setDoOutput(true);
//    			conn.setRequestProperty("Accept", "application/json");
//    			OutputStreamWriter outp = new OutputStreamWriter(conn.getOutputStream());
//    			//com.trimax.its.org.json.JSONObject jsonObject = new com.trimax.its.org.json.JSONObject(routeName);
//                outp.write(routeName.toString());
//                outp.close();
//    			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//    			connection.setDoOutput(true);
//    			//connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setConnectTimeout(10000);
//                connection.setReadTimeout(300000);
//                OutputStreamWriter outp = new OutputStreamWriter(connection.getOutputStream());
//                com.trimax.its.org.json.JSONObject jsonObject = new com.trimax.its.org.json.JSONObject(map);
////                outp.write(routeName.toString());
//                outp.flush();
//                outp.close();
    			
    			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    			connection.setRequestMethod("GET");
    			connection.setRequestProperty("Accept", "application/json");
 
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        connection.getInputStream()));
//    			if (connection.getResponseCode() != 200) {
//    				throw new RuntimeException("Failed : HTTP error code : "
//    						+ connection.getResponseCode());
//    			}
    	 
    			BufferedReader br = new BufferedReader(new InputStreamReader(
    				(connection.getInputStream())));
    	 
    			String output="";
    			while ((output = br.readLine()) != null) {
    			//	System.out.println(output);
    				output=output;
    				HttpServletResponse response=ServletActionContext.getResponse();
        			out = response.getWriter();
        			 out.print(output.trim());
    			}
    			
    			connection.disconnect();
    	 
    		  } catch (MalformedURLException e) {
    	 
    			e.printStackTrace();
    	 
    		  } catch (IOException e) {
    	 
    			e.printStackTrace();
    	 
    		  }catch(Exception e){
    			  e.printStackTrace();
    		  }
    	 return null;
    		}
    
    public String getRouteStartEndPoint() throws IOException{
    	PrintWriter out = null;
    	JSONArray array = new JSONArray();
    	JSONObject result = new JSONObject();
    	try{
    		HttpServletRequest req=ServletActionContext.getRequest();
    		int routeID = Integer.parseInt(req.getParameter("routeid").toString());
    		RouteDAO routedao = new RouteDAO();
    		List<BusStops> listroute = routedao.getStartEndPoint(routeID);
    		for(int i=0;i<listroute.size();i++){
    			JSONArray jo = new JSONArray();
    			jo.add(listroute.get(i).getId());
    			jo.add(listroute.get(i).getSetLocal_bus_stop_id());
    			array.add(jo);
    		}
    		result.put("startend", array);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	HttpServletResponse response=ServletActionContext.getResponse();
		out = response.getWriter();
		 out.print(result);
    	return null;
    }
    
    public String showRouteName(){
    	 HttpServletRequest req=ServletActionContext.getRequest();
    	String route_id=req.getParameter("routeid");
    	System.out.println("Route_id==="+route_id);
    	try{
    		RouteDAO routedao = new RouteDAO();
    		String route_name=routedao.getRouteNameInfo(route_id);
    		setRouteid(route_id);
    		setRoute_name(route_name);
			
		}catch(Exception e){
			e.printStackTrace();
		}
    	
		return "success";
	}
    
    public String saveRouteName(){
    	
   	 HttpServletRequest req=ServletActionContext.getRequest();
   	String route_id=req.getParameter("routeid");
   	String route_name=req.getParameter("route_name");
   	System.out.println("Route_id==="+route_id);
   	System.out.println("Route_id==="+route_name);
   	try{
   		
   		RouteDAO routedao = new RouteDAO();
   		int count=routedao.saveRouteName(route_id,route_name);
   		
   		if(count>0){
   		String msg="Route Number "+route_name+" updated successfully ";
   		System.out.println("Route Number "+route_name+" updated successfully ");
		 	//msg1="Schedule Number "+mappedSchedule+" updated successfully";
		// msg1="";
      ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", msg);
      ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage1", "1");
   		}
   		
			
		}catch(Exception e){
			e.printStackTrace();
		}
   	
		return "success";
	}
	
}
