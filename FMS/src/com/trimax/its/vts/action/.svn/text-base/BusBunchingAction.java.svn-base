package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.VehicleDefectsDao;
import com.trimax.its.vehicle.model.VehicleNumber;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.Busstop;
import com.trimax.ws.vts.vtsutility.VtsDataModel;

public class BusBunchingAction extends ActionSupport {
	Map<String, String> listrout=new HashMap<String,String>();
	Map<Integer,String> listBusStop=new HashMap<Integer,String>();
	List<com.trimax.ws.vts.vtsutility.VtsDataModel> list = null;
	Busstop busstop;
	BusStops busstops;
	Route route;
	
	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getList() {
		return list;
	}

	public void setList(List<com.trimax.ws.vts.vtsutility.VtsDataModel> list) {
		this.list = list;
	}
	

	public Map<String, String> getListrout() {
		return listrout;
	}

	public void setListrout(Map<String, String> listrout) {
		this.listrout = listrout;
	}
	

	public Map<Integer, String> getListBusStop() {
		return listBusStop;
	}

	public void setListBusStop(Map<Integer, String> listBusStop) {
		this.listBusStop = listBusStop;
	}
	
	
	public Busstop getBusstop() {
		return busstop;
	}

	public void setBusstop(Busstop busstop) {
		this.busstop = busstop;
	}
	

	public BusStops getBusstops() {
		return busstops;
	}

	public void setBusstops(BusStops busstops) {
		this.busstops = busstops;
	}
	
	public Route getRoute() {
		return route;
	}

	
	public void setRoute(Route route) {
		this.route = route;
	}

	public String getBusBunching() {		
		return "success";
	}
	
	@SkipValidation
	public String getBusStopList(){
		VtsDataDAO dao = VtsDataDAO.getInstance();
		HttpServletRequest request=ServletActionContext.getRequest();
	    String idBusStopName = request.getParameter("id");	   
	    List<Busstop> list = dao.getBusStopList(idBusStopName);								
		 HttpServletResponse response=ServletActionContext.getResponse();		
		PrintWriter out;
		try {
			out = response.getWriter();			
			  out.print(new JSONArray(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
     	return null;
	}

	public String getRoutList() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		
		String startbusStop = req.getParameter("frombusStopid");		
		String endbusStop = req.getParameter("tobusStopid");		
		PrintWriter out = null;		
		try {			
		List<String> l1 = dao.getRouteId(startbusStop, endbusStop);
		List<String> l2 = dao.getRouteNumber(startbusStop, endbusStop);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	

	}
	
	public String getRoutebunchInfo(){
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		
		String routeid = req.getParameter("routeid1");
		String distanceid = req.getParameter("distanceid");
		Session session=null;
		try {
			out = resp.getWriter();
			session=HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getBusBunchingInfo(distanceid,routeid);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}
	
	public String getRouteonMap() throws IOException{
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	int route_id=Integer.parseInt(request.getParameter("routeid"));
	//Getting Route Parameters...
	VtsDataDAO dao=VtsDataDAO.getInstance();
	JSONObject object=dao.getRouteForBusBunching(route_id, request);
	PrintWriter out=response.getWriter();
	out.print(object);
	return null;
	}
}
