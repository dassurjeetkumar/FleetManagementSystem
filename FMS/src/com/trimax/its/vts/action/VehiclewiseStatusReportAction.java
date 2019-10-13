package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.vts.dao.VehiclewiseStatusreportDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class VehiclewiseStatusReportAction {
	
	private Map<Integer, String> divisionlist;

	private Map<Integer, String> schedulelist;

	
	
	/**
	 * @return the divisionlist
	 */
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}



	/**
	 * @param divisionlist the divisionlist to set
	 */
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}



	/**
	 * @return the schedulelist
	 */
	public Map<Integer, String> getSchedulelist() {
		return schedulelist;
	}



	/**
	 * @param schedulelist the schedulelist to set
	 */
	public void setSchedulelist(Map<Integer, String> schedulelist) {
		this.schedulelist = schedulelist;
	}



	public String execute(){
		
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	public String getVehicles() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		Common common = new Common();
		resp.setContentType("application/json");
		JSONObject result = new JSONObject();
		PrintWriter out = resp.getWriter();
		String scheduleNo = request.getParameter("scheduleno");
		String selectedDate = common.getDateFromPicker(request.getParameter("selectedDate"));
		VehiclewiseStatusreportDAO dao = new VehiclewiseStatusreportDAO();
		result = dao.getCrewAllocationRecords1(scheduleNo, selectedDate);
		out.print(result);
		return null;
	}
	
	public String updateVehicle(){
		Common common = new Common();
		HttpServletRequest request = ServletActionContext.getRequest();
		String vehicleno = request.getParameter("vehicleNo");
		String scheduleno = request.getParameter("scheduleNo");
		String dutyType = request.getParameter("dutytype");
		String oldVehicleNo = request.getParameter("oldVehicleNo");
		String selectedDate = common.getDateFromPicker(request.getParameter("selectedDate"));
		System.out.println("selected Date--->");
		VehiclewiseStatusreportDAO dao = new VehiclewiseStatusreportDAO();
		int cnt = dao.updateVehicle(vehicleno, dutyType,scheduleno,oldVehicleNo);
		String url = "http://10.30.1.154/pisdataupdate_indv_new.php?vehicle="+vehicleno+"&date="+selectedDate;
		callUrl(url);
		return null;
	}
	public static void callUrl(String url){
		try{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		int responseCode = con.getResponseCode();
		System.out.println("Response Code ----> "+responseCode);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
