package com.trimax.its.dashboard.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.dashboard.doa.DashBoardReportDao;

public class DashboardReportAction extends ActionSupport{
	private Map<Integer, String> divisionList;
	DashBoardReportDao dao=new DashBoardReportDao();
	
	HttpServletResponse resp = ServletActionContext.getResponse();
	HttpServletRequest req = ServletActionContext.getRequest();
	
	public Map<Integer, String> getDivisionList() {
		return divisionList;
	}


	public void setDivisionList(Map<Integer, String> divisionList) {
		this.divisionList = divisionList;
	}

	private String orgtypeid;
	private String orgchartid;
	
	
	public String getOrgtypeid() {
		return orgtypeid;
	}


	public void setOrgtypeid(String orgtypeid) {
		this.orgtypeid = orgtypeid;
	}


	public String getOrgchartid() {
		return orgchartid;
	}


	public void setOrgchartid(String orgchartid) {
		this.orgchartid = orgchartid;
	}

	public String ShowDashBoardReport(){
		HttpServletRequest request = ServletActionContext.getRequest();
		divisionList=dao.getDivisionName();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid"); 
		
		orgtypeid=(String)request.getSession().getAttribute("orgtypeid"); 
		System.out.println("orgtypeid"+orgtypeid);
	
		orgchartid=(String)request.getSession().getAttribute("orgchartid"); 
		System.out.println("orgchartid"+orgchartid);
		divisionList=dao.getDivisionName();
		return "success";		
	}
	
	public String showRouteDeviationDashBoard() {
		
		PrintWriter out = null;
		//DashBoardReportDao dao = new DashBoardReportDao();
        
		String fromDate = req.getParameter("fromdate").toString();
		String toDate = req.getParameter("todate").toString();		
		String depotid = req.getParameter("depotid").toString();
		String BusstopId="";
		Common common=new Common();
		JSONObject result = new JSONObject();
		
		try {
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getRouteDeviationforDashBoard(1,fromDate, toDate, depotid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String showTripCancelationDashBoard(){
		//HttpServletRequest req=ServletActionContext.getRequest();
		//HttpServletResponse res=ServletActionContext
		PrintWriter out = null;
		String fromDate = req.getParameter("fromdate").toString();
		String toDate = req.getParameter("todate").toString();		
		String depotid = req.getParameter("depotid").toString();
		JSONObject result=new JSONObject();
		try{
			resp.setContentType("application/json");
			out=resp.getWriter();
			result=dao.getTripCancelationforDashBoard(1, fromDate, toDate, depotid);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		out.print(result);
		
		return null;
		
	}
	//summury report start
	
		public String ShowSummuryReportData() {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = null;
			DashBoardReportDao dao = new DashBoardReportDao();
			String fromDate = request.getParameter("fromdate").toString();
			String toDate = request.getParameter("todate").toString();
			
			String depotid = request.getParameter("depotid").toString();
			//cccDaoData dao = new cccDaoData();
			System.out.println("fromDate"+fromDate+"toDate"+toDate+"depotid"+depotid);
			JSONObject result = new JSONObject();
			try {
				
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				out = response.getWriter();
				result=dao.getTotalSummuryReport(fromDate,toDate,depotid);
			} catch (Exception ex) {

			}
			//System.out.println("result.accident................"+result);
			out.print(result);
			return null;
		}
		//end
	public String showKmCancelationDashBoard(){
		//HttpServletRequest req=ServletActionContext.getRequest();
		//HttpServletResponse res=ServletActionContext
		PrintWriter out = null;
		String fromDate = req.getParameter("fromdate").toString();
		String toDate = req.getParameter("todate").toString();		
		String depotid = req.getParameter("depotid").toString();
		JSONObject result=new JSONObject();
		try{
			resp.setContentType("application/json");
			out=resp.getWriter();
			result=dao.getKmCancelationforDashBoard(1, fromDate, toDate, depotid);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		out.print(result);
		
		return null;
		
	}
	
	public String showAccidentRegisterDashBoard(){
		PrintWriter out=null;
		String fromDate = req.getParameter("fromdate").toString();
		String toDate = req.getParameter("todate").toString();		
		String depotid = req.getParameter("depotid").toString();
		JSONObject result=new JSONObject();
		try{
			resp.setContentType("application/json");
			out=resp.getWriter();
			result=dao.getAccidentDetailsForDashBoard(1, fromDate, toDate, depotid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		out.print(result);
		return null;
		
	}
	public String showTotalScheduleDashBoard(){
		PrintWriter out=null;
		String fromDate = req.getParameter("fromdate").toString();
		String toDate = req.getParameter("todate").toString();		
		String depotid = req.getParameter("depotid").toString();
		JSONObject result=new JSONObject();
		try{
			resp.setContentType("application/json");
			out=resp.getWriter();
			result=dao.getTotalScheduleForDashBoard(1, fromDate, toDate, depotid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		out.print(result);
		return null;
		
	}
	
	public String showLicenceDetailsDashBoard(){
		PrintWriter out=null;
		String fromDate = req.getParameter("fromdate").toString();
		String toDate = req.getParameter("todate").toString();		
		String depotid = req.getParameter("depotid").toString();
		JSONObject result=new JSONObject();
		try{
			resp.setContentType("application/json");
			out=resp.getWriter();
			result=dao.getLicenceDetailsForDashBoard(1, fromDate, toDate, depotid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		out.print(result);
		return null;
		
	}
	
}
