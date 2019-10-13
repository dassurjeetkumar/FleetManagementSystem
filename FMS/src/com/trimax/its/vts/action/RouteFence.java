package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import org.json.simple.JSONObject;
import com.trimax.its.vts.dao.VtsDataDAO;

public class RouteFence extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//Getting Route Id and against RouteID Set all Parameter For RouteBoxer...
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		int route_id=Integer.parseInt(request.getParameter("routeid"));
		//Getting Route Parameters...
		VtsDataDAO dao=VtsDataDAO.getInstance();
		JSONObject object=dao.getRouteParameters(route_id,request);
		PrintWriter out=response.getWriter();
		out.print(object);
		return null;
	}
	public String regenerateFence() throws IOException{
		// TODO Auto-generated method stub
				//Getting Route Id and against RouteID Set all Parameter For RouteBoxer...
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				int route_id=Integer.parseInt(request.getParameter("routeid"));
				//Getting Route Parameters...
				VtsDataDAO dao=VtsDataDAO.getInstance();
				JSONObject object=dao.getRouteParameters(route_id,request);
				PrintWriter out=response.getWriter();
				out.print(object);
				return null;
	}
	public String insertRouteFence() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
		int route_id=Integer.parseInt(request.getParameter("routeid"));
		int startPont= Integer.parseInt(request.getParameter("startpoints").toString());
		int endPont= Integer.parseInt(request.getParameter("endpoints").toString());
		String path= request.getParameter("path").toString();
		String centerPoint[]= request.getParameter("centerpoint").toString().split("\\#");
		String routeString =request.getParameter("routeString").toString();
		String tempPath[]=routeString.split("\\#");
		String commonPath =request.getParameter("commonPath").toString();
		String stopOrder =request.getParameter("stopOrder").toString();
		VtsDataDAO dao=VtsDataDAO.getInstance();
		int insertedID=0;
		for(int i=0;i<tempPath.length;i++)
		{
			insertedID=dao.insertRouteParameters(route_id,startPont,endPont,stopOrder,path,commonPath,tempPath[i],centerPoint[i],request);
		}
		PrintWriter out=response.getWriter();
		out.print(insertedID);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}

	public String updateRouteGeofence()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
		int route_id=Integer.parseInt(request.getParameter("routeid"));
		VtsDataDAO dao=VtsDataDAO.getInstance();
		int updatefence=dao.updateRouteFenceString(route_id);
		int update=dao.updateRouteFence(route_id);
		//Write File After update ....Dated 11/09/2014
		
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	public String getRouteGeofence() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		int route_id=Integer.parseInt(request.getParameter("routeid"));
		int start_id=Integer.parseInt(request.getParameter("start_id"));
		int end_id=Integer.parseInt(request.getParameter("end_id"));
		//int vehicleId=Integer.parseInt(request.getParameter("vehicleid"));
		//Getting Route Parameters...
		VtsDataDAO dao=VtsDataDAO.getInstance();
		JSONObject object=dao.getroutePath(route_id,start_id,end_id);
		PrintWriter out=response.getWriter();
		out.print(object);
		return null;
	}
}
