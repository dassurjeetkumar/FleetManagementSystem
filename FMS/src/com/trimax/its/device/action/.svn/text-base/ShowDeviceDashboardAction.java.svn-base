package com.trimax.its.device.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.ccc.action.showDashBoardAction;
import com.trimax.its.device.dao.DVPDashboardDao;
import com.trimax.its.device.dao.ShowDeviceDashboardDoa;
import com.trimax.its.etm.doa.showEtmDashboardDoa;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ShowDeviceDashboardAction extends ActionSupport  {
	private Map<Integer, String> divisionlist1;
   
	public String showDeviceDashoboardList()
     {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try {
			setDivisionlist1(dao.getDepot1());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
   }
	
	public String gettotalAlertDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		showEtmDashboardDoa dao=new showEtmDashboardDoa();
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		try {
			out = resp.getWriter();
			resp.setContentType("application/json");
			wholeresult = dao.getAlerts();

			//
		} catch (Exception ex) {

		}
		// out.print(result);
		out.print(wholeresult);
		return null;
	}
	
	
	//Etm Pie Chart
	public String getAllDeviceDetails() {
		System.out.println("in action=====");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
//	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		ShowDeviceDashboardDoa doa = new ShowDeviceDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForAllDevices(req);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	public String getDevicePieChartDetails() {
		System.out.println("in device pie chart details");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	  ShowDeviceDashboardDoa doa = new ShowDeviceDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String devicetype=req.getParameter("deviceType");
		System.out.println("devicetype===="+devicetype);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForDeviceChartDetails(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),devicetype);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	
	
	public String getDVPPieChartDetails() {
		System.out.println("in dvp pie chart details");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		DVPDashboardDao dvp = new DVPDashboardDao();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
//		String devicetype=req.getParameter("deviceType");
//		System.out.println("devicetype===="+devicetype);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dvp.getDataForDVPChart(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString());
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	
	
	
	public String getDevicePieChartNRDDetails() {
		System.out.println("in device pie chart NRD details");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	  ShowDeviceDashboardDoa doa = new ShowDeviceDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String devicetype=req.getParameter("deviceType");
		System.out.println("devicetype===="+devicetype);
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getWebDataForDeviceChartNRDDetails(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),devicetype);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	
	
	public String getDeviceDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	  ShowDeviceDashboardDoa doa = new ShowDeviceDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertId=req.getParameter("alertID");
		String devicetype=req.getParameter("deviceType");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForDevices(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId,devicetype);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	
	
	public String getDeviceNRDDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	  ShowDeviceDashboardDoa doa = new ShowDeviceDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertId=req.getParameter("alertID");
		String devicetype=req.getParameter("deviceType");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForNRDDevices(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId,devicetype);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	
	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}
	
	
	
	
	public String getDVPDeviceDetails() {
		
		System.out.println("DVP status  ==========");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		DVPDashboardDao doa= new DVPDashboardDao();
		JSONObject result = new JSONObject();
		String StatusalertId=req.getParameter("status_alertID");
//		String vehicleStatus=req.getParameter("vehicleStatus");
		try {
			System.out.println("in dvp----alertId----"+StatusalertId);
			
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForVehicleStatus(1, req, "", "",StatusalertId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
