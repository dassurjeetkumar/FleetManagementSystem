package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Route;
import com.trimax.its.route.model.RoutePlatformRel;
import com.trimax.its.transport.model.ScheduleType;
import com.trimax.its.usermanagement.dao.EmployeeDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.VehicleStatusModel;
import com.trimax.its.vts.dao.VtsDataDAO;

import freemarker.template.utility.Execute;

public class RouteMapPisActon extends ActionSupport{

	public RoutePlatformRel rel;
		
		private List<Route> routelist;
		private String ttmclist;
		private String platformlist;
		private Map<Integer, String> ttmclistName;
		private Map<Integer, String> bayslistName;
		private Map<Integer, String> platformlistName;
		private Map<Integer, String> listfloor;
		private String bayslist;
		private String listfloorid;
		
		public RoutePlatformRel getRel() {
			return rel;
		}
	public void setRel(RoutePlatformRel rel) {
			this.rel = rel;
		}

		public Map<Integer, String> getListfloor() {
			return listfloor;
		}


		public void setListfloor(Map<Integer, String> listfloor) {
			this.listfloor = listfloor;
		}
	public String getListfloorid() {
			return listfloorid;
		}


		public void setListfloorid(String listfloorid) {
			this.listfloorid = listfloorid;
		}


		public List<Route> getRoutelist() {
			return routelist;
		}

		public void setRoutelist(List<Route> routelist) {
			this.routelist = routelist;
		}

		public String getTtmclist() {
			return ttmclist;
		}



		public void setTtmclist(String ttmclist) {
			this.ttmclist = ttmclist;
		}

		public String getPlatformlist() {
			return platformlist;
		}

	public void setPlatformlist(String platformlist) {
			this.platformlist = platformlist;
		}


		public Map<Integer, String> getTtmclistName() {
			return ttmclistName;
		}

public void setTtmclistName(Map<Integer, String> ttmclistName) {
			this.ttmclistName = ttmclistName;
		}


		public Map<Integer, String> getBayslistName() {
			return bayslistName;
		}

public void setBayslistName(Map<Integer, String> bayslistName) {
			this.bayslistName = bayslistName;
		}

		public Map<Integer, String> getPlatformlistName() {
			return platformlistName;
		}

		public void setPlatformlistName(Map<Integer, String> platformlistName) {
			this.platformlistName = platformlistName;
		}



		public String getBayslist() {
			return bayslist;
		}

		public void setBayslist(String bayslist) {
			this.bayslist = bayslist;
		}

		public String execute(){
			
			return "success";
		}
		
		
	public String showRoutemapPis(){
		
		try {
	        HttpServletRequest req = ServletActionContext.getRequest();

			// String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	       //  String orgchartid=(String)req.getSession().getAttribute("orgchartid");
			RouteDAO routdao = new RouteDAO();
			
			
			routelist = routdao.getRouteNo();
			//vehiclelistname = routdao.getVehicleName();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return SUCCESS;
	}
	private ArrayList<String> serviceid;

public ArrayList<String> getServiceid() {
		return serviceid;
	}

	public void setServiceid(ArrayList<String> serviceid) {
		this.serviceid = serviceid;
	}


public String getSaveRoutMapPisAction(){
		String returnString = "success";
		try {
			//System.out.println("hiii---");
	        HttpServletRequest req = ServletActionContext.getRequest();
	        RouteDAO dao = new RouteDAO();
	        String project = req.getParameter("project-id");
	        String projectsplit[] = project.split("-");
	        String routeno = projectsplit[0];
			//String routeno =req.getParameter("project");
			int routeid= Integer.parseInt(routeno);
			System.out.println("routeid"+routeid);
			String ttmc =req.getParameter("ttmcid");
			int ttmcid1= Integer.parseInt(ttmc);
			String floor =req.getParameter("floorid");
			int floor1= Integer.parseInt(floor);
			String bay =req.getParameter("bayid");
			int bay1= Integer.parseInt(bay);
			String platform =req.getParameter("platformid");
			int platform1= Integer.parseInt(platform);
			int bsstop=Integer.parseInt(req.getParameter("bsstop"));
			//String servicetype[] =req.getParameterValues("serviceid");
//			System.out.println("list,,.........."+getServiceid());
//			System.out.println("ttmc--"+ttmc);
//			System.out.println("routeno---"+routeno);
			if(dao.isDuplicate(routeid,bsstop,ttmcid1,floor1,bay1,platform1,getServiceid())){
			dao.insertRoutMapValues(routeid,bsstop,ttmcid1,floor1,bay1,platform1,getServiceid());
			addActionMessage("Route Map PIS Id  Created Successfully");
			returnString="success";
			}else{
				addActionError("Route Map PIS Id already exist");
				returnString = "input";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return returnString;
	}
	
	
	
	
	public String getPlatForm() {

		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO dao = new RouteDAO();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getPlatFormId(parentId);
		List<String> l2 = dao.getPlatFormName(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			//String vehiclearr[] = l1.get(i).toString().split("#");
		System.out.println(l1.get(0));
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getPerticularBusStop() {

		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO dao = new RouteDAO();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getBusstopId(parentId);
		List<String> l2 = dao.getBusStopName(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			//String vehiclearr[] = l1.get(i).toString().split("#");
		System.out.println(l1.get(0));
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getfloorpis() {

		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO dao = new RouteDAO();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getFloorId(parentId);
		List<String> l2 = dao.getFloorName(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			//String vehiclearr[] = l1.get(i).toString().split("#");
		System.out.println(l1.get(0));
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getBays() {

		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO dao = new RouteDAO();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getBaysId(parentId);
		List<String> l2 = dao.getBaysName(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			//String vehiclearr[] = l1.get(i).toString().split("#");
		System.out.println(l1.get(0));
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getService() {

		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO dao = new RouteDAO();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getServiceId(parentId);
		List<String> l2 = dao.getServiceNameType(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			//String vehiclearr[] = l1.get(i).toString().split("#");
		System.out.println(l1.get(0));
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getBusStopRoute() {

		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO dao = new RouteDAO();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getStopRouteId(parentId);
		List<String> l2 = dao.getStopRouteNameType(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			//String vehiclearr[] = l1.get(i).toString().split("#");
		System.out.println(l1.get(0));
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String DIR;
	private int START;
public void getviewroutmapdetails() throws Exception {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			RouteDAO dao = new RouteDAO();
			String[] dbcol={"","route_platform_id","route_number","route_name","org_name","stop_name","floor_name","bay_name","platform_name","service_type_name"};
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
			int total = -1;
			String SEARCH_TERM = request.getParameter("sSearch");
			total =dao.getTotalRecordsForviewroute(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getrouteList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
}
public String deleteviewRoutePis(){
	String returnResult = "success";
	RouteDAO dao = new RouteDAO();
	int vid = Integer.parseInt(ServletActionContext.getRequest().getParameter("vid"));
	if(dao.deletepisroute(vid))
		{
			addActionMessage("Route Map PIS id "+vid+" deleted successfully");
		}else{
			addActionError("Problem in deleting route map PIS");
		}
	return returnResult;
}
public String editviewRoutePis(){
	String returnResult = "success";
	
	return returnResult;
}
}

	

