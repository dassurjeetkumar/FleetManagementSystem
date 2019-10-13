package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.vts.dao.VehicleAlertConfigDAO;
import com.trimax.its.vts.dao.VehicleDepotinoutDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class VehicleDepotinoutAction {
	private String daterange;

	public String getDaterange() {
		return daterange;
	}

	public void setDaterange(String daterange) {
		this.daterange = daterange;
	}
	private Map<Integer, String> divisionlist1;
	private Map<Integer, String> divisionlist;
	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String viewVehicleDepotinout() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date date = new Date();
		Date date1 = new Date();
		//Date addedDate2 = addDays(date1, 1);
		try {
			
		} catch (Exception ex) {

		}
		int parentId=0;
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	         parentId = vvt.getDepot1(orgtypeid,orgchartid);
	        if(!orgtypeid.equals("1")  && !orgchartid.equals("1")){
	        	divisionlist=vvt.getDivisionName(orgtypeid,orgchartid,parentId);
	        }
	        else{
			divisionlist1 = vvt.getDepot1();
//			System.out.println(divisionlist1.size()
//					+ "divisionlist1%%%%%%%%%%%%%%%%%%%%%%");
			divisionlist = vvt.getDivisionName();
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String showVehicleDepotinoutList() throws IOException {

		HttpServletRequest request = ServletActionContext.getRequest();
		String daterange = request.getParameter("daterange");
		String parameter = request.getParameter("parameter") != null ? request
				.getParameter("parameter") : "Arrival";
		String depot_id=request.getParameter("depot_id");
		HttpServletResponse response = ServletActionContext.getResponse();
		VehicleDepotinoutDAO vehicledepotinoutdao = new VehicleDepotinoutDAO();
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {

			// int cnt =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			// int cnt = orgdao.getTotalRecordsOfWayBill();
			// System.out.println("Count------>" + cnt);

		/*	String[] cols = { "VEHICLE_NO", "schedule_name", "END_TIME",
					"ACT_END_TIME", "DEPOT_ENTRY_TM", "DEPOT_EXIT_TM" };
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			String sStart = "";
			String sAmount = "";
			String sCol = "";
			// sStart : 0 : sAmount : 10 : sCol : 1 : sdir : asc
			try {
				sStart = request.getParameter("iDisplayStart") != null ? request
						.getParameter("iDisplayStart") : "0";
				sAmount = request.getParameter("iDisplayLength") != null ? request
						.getParameter("iDisplayLength") : "10";
				sCol = request.getParameter("iSortCol_0");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String sdir = request.getParameter("sSortDir_0") != null ? request
					.getParameter("sSortDir_0") : "asc";
			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			} else {
				start = 0;
				sStart = "";
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			} else {
				amount = 10;
				sAmount = "";
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			} else {
				col = 0;
				sCol = "0";
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			} else {
				dir = "desc";
			}
			String colName = cols[col];
			int total = -1;
			// total = ticketinvdao.getTotalIssuedRecords(str);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch") != null ? request
					.getParameter("sSearch") : "";
			COL_NAME = colName;
			DIR = dir;
			START = start;
*/
			result = vehicledepotinoutdao.showVehicleDepotinoutData(0,
					request, "", "", daterange,
					parameter,depot_id);
//			System.out.println("result=====>"+result);
			out.print(result);

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public String showVehicleDepotinoutList1() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String daterange = request.getParameter("daterange");
		String depot_id=request.getParameter("depot_id");
		HttpServletResponse response = ServletActionContext.getResponse();
		VehicleDepotinoutDAO vehicledepotinoutdao = new VehicleDepotinoutDAO();
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		
		String parameter1 = request.getParameter("parameter1") != null ? request
				.getParameter("parameter1") : "Arrival";
		result = vehicledepotinoutdao.showVehicleDepotinoutData1(0, request,
				"", "", daterange, parameter1,depot_id);
		out.print(result);
		return null;
	}
}
