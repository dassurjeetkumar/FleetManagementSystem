package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.transport.dao.TripDAO;
import com.trimax.its.transport.model.ScheduleDetails;

public class TripAjaxAction {

	public String getDepotData(){
		try {
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String q = request.getParameter("q");
		String data = tripdao.getDepot(q);
		String [] splitResult = data.split(",");
		for(int i=0;i<splitResult.length;i++){
			out.println(splitResult[i]);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getDeadRouteStops(){
		try {
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String q = request.getParameter("q");
		String data = tripdao.getDeadStops(q);
		String [] splitResult = data.split(",");
		for(int i=0;i<splitResult.length;i++){
			out.println(splitResult[i]);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRoutes(){
		try{
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String start = request.getParameter("startpoint");
		String end = request.getParameter("endpoint");
		String serviceid = request.getParameter("serviceid");
		int triptypeid = Integer.parseInt(request.getParameter("triptypeid"));
		String startPoint = tripdao.getBusPoint(start);
		String endPoint = tripdao.getBusPoint(end);
		if(startPoint.equals("") || endPoint.equals("")){
			startPoint="0";
			endPoint="0";
		}
		String data = tripdao.getRoute(startPoint, endPoint,serviceid,triptypeid);
		out.println(data);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getDeadRoutes(){
		try{
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String start = request.getParameter("startpoint");
		String end = request.getParameter("endpoint");
		int triptypeid = Integer.parseInt(request.getParameter("triptypeid"));
		String startPoint = tripdao.getBusPoint(start);
		String endPoint = tripdao.getBusPoint(end);
		String data = tripdao.getDeadRoute(startPoint, endPoint,triptypeid);
		out.println(data);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRoutesById(){
		try{
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String routeno = request.getParameter("routeno");
		String data = tripdao.getRouteByID(routeno);
		out.println(data);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRouteDistance(){
		try{
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String routeno = request.getParameter("routeno");
		String startStop = request.getParameter("startstop");
		String endStop = request.getParameter("endstop");
		String startOrder =tripdao.getBusOrder(routeno, startStop);
		String endOrder =tripdao.getBusOrder(routeno, endStop);
		String data = tripdao.getDistance(routeno, startOrder, endOrder);
		out.println(data);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getGroupStops(){
		try{
		TripDAO tripdao = new TripDAO();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		String busStopPoint = request.getParameter("buspoint");
		String data  = tripdao.getGroupingStops(busStopPoint);
		out.println(data);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
