/*package com.trimax.its.pis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.utility.dao.RoleDao;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.VtsDataModel;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class LiveBusTrackingAction extends ActionSupport {
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	List<com.trimax.ws.vts.vtsutility.VtsDataModel> list = null;
	OrganisationChart orgchart;
	private String E0;
	private String startdate;
	private String enddate;
	private String buttonname;
	private Map<Integer, String> divisionlist1;
	
	private Map<Integer, String> alertlist1;

	public Map<Integer, String> getAlertlist1() {
		return alertlist1;
	}

	public void setAlertlist1(Map<Integer, String> alertlist1) {
		this.alertlist1 = alertlist1;
	}

	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String getButtonname() {
		return buttonname;
	}

	public void setButtonname(String buttonname) {
		this.buttonname = buttonname;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getE0() {
		return E0;
	}

	public void setE0(String e0) {
		E0 = e0;
	}

	public OrganisationChart getOrgchart() {
		return orgchart;
	}

	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}

	private Map<Integer, String> divisionlist;

	private Map<Integer, String> schedulelist;

	private Map<String, String> routeList;

	public Map<String, String> getRouteList() {
		return routeList;
	}

	public void setRouteList(Map<String, String> routeList) {
		this.routeList = routeList;
	}

	public Map<Integer, String> getSchedulelist() {
		return schedulelist;
	}

	public void setSchedulelist(Map<Integer, String> schedulelist) {
		this.schedulelist = schedulelist;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getList() {
		return list;
	}

	public void setList(List<com.trimax.ws.vts.vtsutility.VtsDataModel> list) {
		this.list = list;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String execute() {

		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date date = new Date();
		Date date1 = new Date();
		Date addedDate2 = addDays(date1, 1);
		try {
			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - 00:00");
			setStartdate(sm3.format(date));
			setEnddate(sm2.format(date));
		} catch (Exception ex) {

		}
		try {
			divisionlist1 = vvt.getDepot1();
			System.out.println(divisionlist1.size()
					+ "divisionlist1%%%%%%%%%%%%%%%%%%%%%%");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		divisionlist = vvt.getDivisionName();

		return "success";
	}

	@SuppressWarnings("unchecked")
	public String getCordinates() throws ParseException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		Common common = new Common();
		String latLong = "";
		VtsDataModel vs = new VtsDataModel();
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		String startDate = req.getParameter("startdate") != null ? req
				.getParameter("startdate") : "";
		String endDate = req.getParameter("enddate") != null ? req
				.getParameter("enddate") : "";
		String value = req.getParameter("value") != null ? req
				.getParameter("value") : "";
		String fromDate = "", tillDate = "";

		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sm2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date(System.currentTimeMillis() - 720000L);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			System.out.println("format.format(date)" + format.format(date));
			if (value.equals("Submit")) {
				fromDate = common.getDateTimeFromPickerToDB(startDate);
				tillDate = common.getDateTimeFromPickerToDB(endDate);
			} else if (value.equals("ScheduleSubmit")) {
				fromDate = startDate;
				tillDate = endDate;
			} else if (value.equals("Running")) {

			} else {
				fromDate = sm.format(FromDate).toString() + " 00:00:00";
				tillDate = sm.format(FromDate).toString() + " 23:59:59";
			}

			PrintWriter a = resp.getWriter();

			String parameters = req.getParameter("DEVICE_ID");
			String DEVICE_ID = parameters;
			int ID = req.getParameter("ID") != null ? Integer.parseInt(req
					.getParameter("ID").toString()) : 0;
			if (DEVICE_ID.equals("ALL")) {

				list = vvt.getTrackingData(sm.format(FromDate).toString()
						+ " 00:00:00", sm.format(FromDate).toString()
						+ " 23:59:59", DEVICE_ID, ID, "0",0);

				for (int i = 0; i < list.size(); i++) {
					JSONArray ja = new JSONArray();
					ja.add(list.get(i).getLAT());
					ja.add(list.get(i).getLONGITUDE());
					ja.add(list.get(i).getSPEEDKMPH());
					FromDate = sm1.parse(list.get(i).getISTDATE());
					ja.add(sm2.format(FromDate));
					ja.add(list.get(i).getDEVICEID());
					ja.add(list.get(i).getID());
					ja.add(list.get(i).getLICENCENUMBER());
					ja.add(list.get(i).getPhoneNumber());
					ja.add(list.get(i).getVEHICLEDIRECTION());
					ja.add(list.get(i).getVEHICLETYPE());
					array.add(ja);
				}

				result.put("aaData", array);

			} else {
				if (value.equals("Submit")) {
					list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
							ID, "0",0);
				} else if (value.equals("ScheduleSubmit")) {
					System.out.println("fromDate tillDate" + fromDate + "\t"
							+ tillDate);
					list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
							ID, "0",0);
				} else {
					list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
							ID, "1",0);
				}
				for (int i = 0; i < list.size(); i++) {
					JSONArray ja = new JSONArray();
					ja.add(list.get(i).getLAT());
					ja.add(list.get(i).getLONGITUDE());
					ja.add(list.get(i).getSPEEDKMPH());
					FromDate = sm1.parse(list.get(i).getISTDATE());
					ja.add(sm2.format(FromDate));
					ja.add(list.get(i).getDEVICEID());
					ja.add(list.get(i).getID());
					ja.add(list.get(i).getLICENCENUMBER());
					ja.add(list.get(i).getPhoneNumber());
					ja.add(list.get(i).getVEHICLEDIRECTION());
					ja.add(list.get(i).getRECORDSTATUS());
					// ja.add(list.get(i).getVEHICLETYPE());
					array.add(ja);
				}
				result.put("aaData", array);
			}

			a.print(result);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return null;
	}

	public String getAlertsSuccess() {
		return "success";
	}

	public String getDashBoard() {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try {
			divisionlist1 = dao.getDepot1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String getRoutes() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getRouteId(parentId);
		List<String> l2 = dao.getRouteName(parentId);
		String regionTypeAjaxString = "";

		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getSchedule() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String date=req.getParameter("selectedDate");
		List<String> l1 = dao.getScheduleID(parentId,date);
		List<String> l2 = dao.getScheduleName(parentId,date);
		String regionTypeAjaxString = "";

		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getPerticularDepot() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getDepotId(parentId);
		List<String> l2 = dao.getDepotName(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getDepotArrivalTimeData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		String depot_code = req.getParameter("depotID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForArrival(packet_code);
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String getDepotDepartureTimeData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		String depot_code = req.getParameter("depotID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForDeparture(packet_code, depot_code, req, "",
					"", sm.format(FromDate).toString() + " 00:00:00", sm
							.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;

	}

	public String getSkippedStopData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForSkipStopData(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String getSoSAlertData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForSosAlert(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;

	}

	public String getPerticularVehicle() {

		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getVehicleId(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		regionTypeAjaxString += "<option  value='ALL'>------ALL------</option>";
		for (int i = 0; i < l1.size(); i++) {
			String vehiclearr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<option value=" + vehiclearr[0] + ">"
					+ vehiclearr[1] + "(" + vehiclearr[0] + ")" + "</option>";
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

	public String getVehicleDataForSms() {
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getVehicleId(parentId);
		String regionTypeAjaxString = "<table>";
		for (int i = 0; i < l1.size(); i++) {
			String vehiclearr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<tr><td><input type='checkbox' name='deviceid' value='"
					+ vehiclearr[0]
					+ "'> "
					+ vehiclearr[1]
					+ "("
					+ vehiclearr[0] + ")" + "</td></tr>";
		}
		regionTypeAjaxString += "</table>";
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

	public String getPerticularDevice() {
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int vehicleId = Integer.parseInt(req.getParameter("val"));
		// serviceTypeIds=rmDao.getServiceId();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();

		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

		list = vvt.getDeviceIMEI(sm.format(FromDate).toString() + " 00:00:00",
				sm.format(FromDate).toString() + " 23:59:59");
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		for (int i = 0; i < list.size(); i++) {
			regionTypeAjaxString += "<option value=" + list.get(i).getID()
					+ "," + list.get(i).getDEVICEID() + ">" + "("
					+ list.get(i).getDEVICEID() + ")" + list.get(i).getID()
					+ "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getVehicleDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		HttpSession session=req.getSession();
		try {
			String sessionOrgchartId=(String)session.getAttribute("orgchartid");
			String sessionOrgTypeId=(String)session.getAttribute("orgtypeid");
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForChartVehicle(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sessionOrgTypeId,sessionOrgchartId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}

	public String getAlertDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			resp.setContentType("application/json");
			result = dao.getDataForChart(1, req, "", "", sm.format(FromDate)
					.toString() + " 00:00:00", sm.format(FromDate).toString()
					+ " 23:59:59");

		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}

	public String gettotalAlertDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
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

	public String getAlerts() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		try {
			out = resp.getWriter();
			wholeresult = dao.getAlertsDetails(req);

			//
		} catch (Exception ex) {

		}
		// out.print(result);
		out.print(wholeresult);
		return null;
	}

	public String alertPerticularData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForChartGrid(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String vehiclePerticularData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String status = req.getParameter("vehicleStatus");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForChartGridVehicle(status, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59","0");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String vehicleAlertCountPerticular() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		String packet_code = request.getParameter("packet_code");
		String misc_byte = request.getParameter("misc_byte");
		String deivice_id = request.getParameter("deivice_id");

		String licence_number = request.getParameter("licence_number");

		try {
			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			if (packet_code.equalsIgnoreCase("DV")
					&& misc_byte.equalsIgnoreCase("00")) {
				result = vvt.getDataForDeviationAlert(1, request, "", "", sm
						.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",
						packet_code, misc_byte, deivice_id, licence_number);
			} else {
				result = vvt.getDataForAlert(1, request, "", "",
						sm.format(FromDate).toString() + " 00:00:00", sm
								.format(FromDate).toString() + " 23:59:59",
						packet_code, misc_byte, deivice_id, licence_number);
			}
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}

	@SkipValidation
	public String pollMsg() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}

	public String insertPollSms() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String checkboxvalues[] = request.getParameterValues("deviceid");
		if ("Submit".equals("E0")) {
			for (int i = 0; i < checkboxvalues.length; i++) {
			}
		}
		return null;
	}

	public static Date addDays(Date d, int days) {
		d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
		return d;
	}

	public String getDeviation() throws Exception {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		//schedulelist = vvt.getScheduleName();
		return "success";
	}

	public String getScheduleDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String status = req.getParameter("scheduleNo");
		String selectedDate = req.getParameter("") != null ? req
				.getParameter("") : "";

		try {
			out = resp.getWriter();
//			/result = dao.getDataForSchedule(status);
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String getRoutetrackinfo() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		try {
			routeList = vvt.getRouteName();
			divisionlist = vvt.getDivisionName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String getRouteTracking() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out = null;
		JSONObject result = new JSONObject();
		int routeID = Integer.parseInt(req.getParameter("routeID"));
		VtsDataDAO vvt = VtsDataDAO.getInstance();

		try {
			out = resp.getWriter();
			result = vvt.routeTeackingData(routeID);
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	// For Scheduled vehicle Report
	public String getScheduledVehicle() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;

		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			String deviceId = request.getParameter("deviceid");
			out = response.getWriter();
			result = vvt.getScheduleNameForTracking(deviceId);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

		return null;
	}

	public String getScheduledVehiclesuccess() {
		return "success";
	}

	public String getVtuReport() {
		return "success";
	}

	public String getHOURLYVTUReport() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		String selectedDate = request.getParameter("selectedDate") != null ? request
				.getParameter("selectedDate") : "";
				int selectedDate = request.getParameter("selectedDate") != null ? request
						.getParameter("selectedDate") : "";
		try {
			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = vvt.c(1, request, selectedDate, selectedDate);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {
	
		}
	
		return null;
	}

	public String getSkipStops() {
		return "success";
	}

	public String getSkipStopData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		try {
			Date date = new Date();
			Date dNow = new Date(); // Instantiate a Date object
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNow);
			cal.add(Calendar.MINUTE, -5);
			dNow = cal.getTime();
			SimpleDateFormat sm = new SimpleDateFormat("dddd-MM-yy HH:mm:ss");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = vvt.getDataForSkipStop(1, request, sm.format(FromDate)
					.toString(), sm.format(dNow).toString());
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;
	}

	public String getRouteDeviationData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		try {
			Date dNow = new Date(); // Instantiate a Date object
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNow);
			cal.add(Calendar.MINUTE, -5);
			dNow = cal.getTime();
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = vvt.getDataForRouteDeviation(1, request,
					sm.format(FromDate).toString(), sm.format(dNow).toString());
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;
	}

	public String getSkippedStopDetail() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataForSkipStopDetail(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59", session);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}

	public String getOverSpeedDetail() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataForOverSpeedDetail(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59", session);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}

	public String getAlertReport() {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try {

			divisionlist1 = dao.getDepot1();
			alertlist1 = dao.getAlertRouteReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String getAlertReportonChange() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertID = req.getParameter("alertID");
		String depotID = req.getParameter("depotID");
		String givendate = req.getParameter("givendate");
		Session session = null;
		int startPoint=0;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			startPoint  = getStartPoint(depotID);
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataForAlertReport(alertID, req, givendate,givendate,depotID,givendate, session,startPoint);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}
	private int getStartPoint(String depot_id) {
		// TODO Auto-generated method stub
		int param = 0;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select bus_stop_id from bus_stop where org_chart_id ='"+depot_id+"'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return param;
	}

}
*/