package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.trimax.its.route.dao.FloorDao;
import com.trimax.its.route.dao.RouteDAO;

public class RouteAjaxAction {
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String execute() throws IOException{
		System.out.println("in get execute");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		RouteDAO routedao = new RouteDAO();
//		int cnt = routedao.getTotalRecords(request.getParameter("sSearch"),(String)request.getSession().getAttribute("route_filter_date"));
		//System.out.println("Count------>"+cnt);
		String[] cols = { "Route Id", "Route Id", "Route Number(m)", "Route Name(m)", "Start Bus Stop Name(m)", "End Bus Stop Name(m)",
		"Route Type(m) ","Status(m)", "Direction", "Via", "Effective From", "Effective Till", "GeoFence"};
		
		String[] dbcol={"route_id","route_id","route_number","route_name","start_stop","end_stop",
				"route_type.route_type_id","status","route_direction","via","effective_from",	"effective_till", "route_geofence"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		
		if (sStart != null) {
			start = Integer.parseInt(sStart);
			if (start < 0) {
			start = 0;
			}
			}
			if (sAmount != null) {
			amount = Integer.parseInt(sAmount);
			if (amount < 10 || amount > 50) {
			amount = 10;
			}
			}
			if (sCol != null) {
			col = Integer.parseInt(sCol);
			if (col < 0 || col > 5)
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			total = routedao.getTotalRecords(request.getParameter("sSearch"),(String)request.getSession().getAttribute("route_filter_date"));
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
			String datefil="";
			//System.out.println("aaaaaaaaaaaaaaaaaaaaa"+(String)request.getSession().getAttribute("route_filter_date"));
			String ssdate=(String)request.getSession().getAttribute("route_filter_date");
			if(ssdate!="" && ssdate!=null){
				datefil=(String)request.getSession().getAttribute("route_filter_date");
				System.out.println("datefil======"+datefil);
				String [] datefil1=datefil.split("/");
				datefil=datefil1[2]+"-"+datefil1[1]+"-"+datefil1[0];
			}else{
				Date date = new Date();
				 datefil= new SimpleDateFormat("yyyy-MM-dd").format(date);
			}
			result = routedao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,datefil);
			out.print(result);
		return null;
	}
	
	public String getData(){
		System.out.println("in get data");
		HttpServletRequest request = ServletActionContext.getRequest();
		RouteDAO routedao = new RouteDAO();
//		int totalRecords = routedao.getTotalRecords(request.getParameter("sSearch"),(String)request.getSession().getAttribute("route_filter_date"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		String echo = request.getParameter("sEcho");
		
		int end = iDisplayStart+iDisplayLength;
//		end = end > totalRecords ? totalRecords : end;
		
		
		return null;
	}
	
	
    public String getRouteDataByBusStop()  throws Exception{
    	System.out.println("in get route data by bus stop");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		RouteDAO routedao = new RouteDAO();
		String busstopid = request.getParameter("busstopId");
		int cnt = routedao.getTotalRecordsByBusStopId(request.getParameter("sSearch"),(String)request.getSession().getAttribute("route_filter_date"),Integer.parseInt(busstopid));
		//System.out.println("Count------>"+cnt);
		String[] cols = { "Route Id", "Route Id", "Route Number(m)", "Route Name(m)", "Start Bus Stop Name(m)", "End Bus Stop Name(m)",
		"Route Type(m) ","Status(m)", "Direction", "Via", "Effective From", "Effective Till", "GeoFence"};
		
		String[] dbcol={"route_id","route_id","route_number","route_name","start_stop","end_stop",
				"route_type.route_type_id","status","route_direction","via","effective_from",	"effective_till", "route_geofence"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
	
		
		
		if (sStart != null) {
			start = Integer.parseInt(sStart);
			if (start < 0) {
			start = 0;
			}
			}
			if (sAmount != null) {
			amount = Integer.parseInt(sAmount);
			if (amount < 10 || amount > 50) {
			amount = 10;
			}
			}
			if (sCol != null) {
			col = Integer.parseInt(sCol);
			if (col < 0 || col > 5)
			col = 0;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			total = routedao.getTotalRecordsByBusStopId(request.getParameter("sSearch"),(String)request.getSession().getAttribute("route_filter_date"),Integer.parseInt(busstopid));
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
			String datefil="";
			//System.out.println("aaaaaaaaaaaaaaaaaaaaa"+(String)request.getSession().getAttribute("route_filter_date"));
			String ssdate=(String)request.getSession().getAttribute("route_filter_date");
			if(ssdate!="" && ssdate!=null){
				datefil=(String)request.getSession().getAttribute("route_filter_date");
				String [] datefil1=datefil.split("/");
				datefil=datefil1[2]+"-"+datefil1[1]+"-"+datefil1[0];
			}else{
				Date date = new Date();
				 datefil= new SimpleDateFormat("yyyy-MM-dd").format(date);
			}
			result = routedao.getDataByBusStopId(total, request,dbcol[Integer.parseInt(sCol)],sdir,datefil,busstopid);
			out.print(result);
		return null;
	}
	
	
	/*public String getDataForFloor() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		FloorDao floordao = new FloorDao();
		int totalRecords = floordao.getTotalRecords(request.getParameter("sSearch"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		String echo = request.getParameter("sEcho");
		
		int end = iDisplayStart+iDisplayLength;
		end = end > totalRecords ? totalRecords : end;
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		System.out.println("Inside Floor Creation");
		JSONArray array = new JSONArray();
		PrintWriter out = response.getWriter();
		result.put("aaData", floordao.getData(7);
		out.print(result);
		return null;
	}*/
	
}
