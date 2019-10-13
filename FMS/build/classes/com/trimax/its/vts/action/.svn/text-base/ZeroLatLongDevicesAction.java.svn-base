package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse;
import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.trimax.its.common.Common;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;

import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ZeroLatLongDevicesAction {

	public String startdate;
	public int type;

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String endDate;

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	private String depotlist1;
	public String divisionlist1;

	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	StringBuilder regionTypeAjaxString = new StringBuilder();

	public String execute() {
		System.out.println("in execute");
//		VtsDataDAO vvt = VtsDataDAO.getInstance();
//		divisionlist = vvt.getDivisionName();
		return "success";
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public static String rbKey = String.valueOf(getSysParameterForVts());

//	public String getZeroLatLongDevicesListsd() {
//		VtsDataDAO dao = VtsDataDAO.getInstance();
//		// System.out.println("in zero lat long Report ");
//
//		try {
//			Date ss1 = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
//			String runDateTime = sdf.format(ss1);
//			HttpServletRequest request=ServletActionContext.getRequest();
////			String qryy = "";
//			String orgtypeid = (String) request.getSession().getAttribute(
//					"orgtypeid");
//			String orgchartid = (String) request.getSession().getAttribute(
//					"orgchartid");
////			System.out.println("orgtypeid..........." + orgtypeid
////					+ "orgchartid................." + orgchartid);
//			String id = "!=0";
//			if (orgtypeid.equals("1")) {
//				id = "depot_id!=0";
//
//			}
//			if (orgtypeid.equals("3")) {
//
//				id = "depot_id in('" + orgchartid + "')";
//			}
//			if (orgtypeid.equals("2")) {
//
//				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
//						+ orgchartid + "')";
//			}
//			
//			
//
//			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//			Date startDate = format.parse(startdate);
//			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
//			String startDate1 = fomat2.format(startDate).toString();
//
//			String fromdate = startDate1 + " 00:00:00";
//			String todate = startDate1 + " 23:59:59";
//			System.out.println("FROM " + fromdate + " TO " + todate + "type--"
//					+ type);
//			model.jaxb.xml.trimax.com.VtsResponse6 nrdresult = null;
//			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
//			nrdresult = port.webGetZeroLatLongReport(fromdate, todate, id,rbKey, type); // calling Web service
//
//			regionTypeAjaxString
//					.append(" <div id='header' style='display: none;'><div align='center'><h4>Zero LAt Long Device Report</br>From Date:- "
//							+ fromdate
//							+ " To Date:-"
//							+ todate
//							+ "</h4></br></div>");
//			regionTypeAjaxString
//					.append("<div align='right'><b>Current Date:-</b>"
//							+ runDateTime + "</div></div>");
//
//			regionTypeAjaxString
//					.append("<div class='component'><table class='overflow-y' border='1'>");
//			regionTypeAjaxString
//					.append("<thead><tr><th>Sr.No.</th><th>Device No</th><th>Vehicle No</th><th>Depot Name</th><th>From Date</th><th>To Date</th><th>Difference</th>"
//							+ "</tr></thead><tbody>");
//
//			HttpServletResponse response = ServletActionContext.getResponse();
//			int i = 1;
//			int j = 1;
//			// System.out.println("size-" +
//			// nrdresult.getWaybillDetails().size());
//			for (i = 0; i < nrdresult.getWaybillDetails().size(); i++) {
//
//				regionTypeAjaxString.append("<tr>");
//				regionTypeAjaxString.append("<td>" + j + "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getDEVICEID()
//						+ "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getVEHICLENO()
//						+ "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getOrgName()
//						+ "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getSTARTTIME()
//						+ "</td>");
//				if (nrdresult.getWaybillDetails().get(i).getENDTIME() == null) {
//					regionTypeAjaxString.append("<td>00-00-0000 00:00:00</td>");
//				} else {
//					regionTypeAjaxString.append("<td>"
//							+ nrdresult.getWaybillDetails().get(i).getENDTIME()
//							+ "</td>");
//				}
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getSTATUS()
//						+ "</td>");
//				regionTypeAjaxString.append("</tr>");
//
//				j++;
//			}
//
//			if (nrdresult.getWaybillDetails().size() == 0) { // if no records
//				regionTypeAjaxString
//						.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
//				regionTypeAjaxString.append("<tr>");
//				regionTypeAjaxString
//						.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
//				regionTypeAjaxString.append("</tr>");
//			}
//
//			PrintWriter out;
//			out = response.getWriter();
//			out.print(regionTypeAjaxString); // to print
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//		return null;
//	}
	
	
	public String getZeroLatLongDevicesList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			JSONObject result = new JSONObject();
			
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "DEPOT_ID!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "DEPOT_ID in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "DEPOT_ID in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			Date end_date= format.parse(endDate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			String startDate1 = fomat2.format(startDate).toString();
			String endDate1 = fomat2.format(end_date).toString();
			String fromdate = startDate1 + " 00:00:00";
			String todate = endDate1 + " 23:59:59";
			System.out.println("FROM " + fromdate + " TO " + todate + "type--"
					+ type+" depot  "+id);
			
//			model.jaxb.xml.trimax.com.VtsResponse6 nrdresult = null;
//			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
//			nrdresult = port.webGetZeroLatLongReport(fromdate, todate, id,rbKey, type); // calling Web service

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = getDataForZeroLatLong(request,id, fromdate,todate,type);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;

	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForZeroLatLong(HttpServletRequest request, String depotId,String fromdate,String todate,int type){
		JSONObject result = new JSONObject();
		try{
//			int depot=Integer.parseInt(depotId);
		
			NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
			com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
			VtsResponseNew dataresult = port.webGetZeroLatLongReportCount(fromdate, todate, depotId, rbKey, type);
			System.out.println("depot---"+depotId);
			JSONArray array = new JSONArray();
			for (int i = 0; i < dataresult.getVtsDatamodelnew().size(); i++) {
				JSONArray ja = new JSONArray();
				// Calling vehicle result
				//System.out.println("Depot"+vehicleresult.getVtsDatamodel().get(i).getDepotName());
				ja.add(i+1);
				ja.add(dataresult.getVtsDatamodelnew().get(i).getDEVICEID());
				ja.add(dataresult.getVtsDatamodelnew().get(i).getLICENCENUMBER());
				ja.add(dataresult.getVtsDatamodelnew().get(i).getOrgName());
//				ja.add(dataresult.getVtsDatamodelnew().get(i).getPacketcount());	
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal40'  onclick=javascript:viewZeroLatLongData('"
						+ fromdate.replace(" ", "@")+ "','"
						+ todate.replace(" ", "@")+"',"+type+",'"+dataresult.getVtsDatamodelnew().get(i).getDEVICEID()+"') >"
						+ dataresult.getVtsDatamodelnew().get(i).getPacketcount()
						+ "</a>");

			    array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	

	public String remove(String str) {
		if (str != null && str.length() > 0
				&& str.charAt(str.length() - 2) == ',') {
			str = str.substring(0, str.length() - 2);
		}
		return str;
	}


	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
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
	
	
      public String getZeroLatlong() throws IOException{
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		PrintWriter out = null;
		ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
		JSONObject result = new JSONObject();
			
			String fromDate=(request.getParameter("fromdate").toString()).replace("@", " ");
			String toDate=(request.getParameter("todate")).replace("@", " ");
			String deviceNo=request.getParameter("DeviceNo");
			int type = Integer.parseInt(request.getParameter("type"));
			Common cm = new Common();
			System.out.println("from==="+fromDate+" to  "+toDate+" device "+deviceNo+" tye  "+type);
//			String formattedgivendate = cm.getDateFromPicker(fromdate);
			try{
				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				response.setContentType("application/json");
				out = response.getWriter();
				result = getZeroLatLongDetails(fromDate,toDate,deviceNo,type);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
		}
	
	
      
      @SuppressWarnings("unchecked")
  	public JSONObject getZeroLatLongDetails(String fromdate,String todate,String deviceNo,int type){
  		JSONObject result = new JSONObject();
  		try{
//  			int depot=Integer.parseInt(depotId);
  		
  			NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
  			com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();
  			VtsResponseNew dataresult = port.webGetZeroLatLongReport(fromdate, todate, deviceNo, rbKey, type);
  			System.out.println("DEVICE_ID=="+dataresult.getVtsDatamodelnew().size());
  			JSONArray array = new JSONArray();
  			for (int i = 0; i < dataresult.getVtsDatamodelnew().size(); i++) {
  				JSONArray ja = new JSONArray();
  				// Calling vehicle result
  				//System.out.println("Depot"+vehicleresult.getVtsDatamodel().get(i).getDepotName());
  				ja.add(i+1);
  				ja.add(dataresult.getVtsDatamodelnew().get(i).getDEVICEID());
  				ja.add(dataresult.getVtsDatamodelnew().get(i).getLICENCENUMBER());
  				ja.add(dataresult.getVtsDatamodelnew().get(i).getScheduleno());
  				ja.add(dataresult.getVtsDatamodelnew().get(i).getReasonDate());
  				ja.add(dataresult.getVtsDatamodelnew().get(i).getStartTime());
  				ja.add(dataresult.getVtsDatamodelnew().get(i).getEndTime());
  				
  				ja.add(dataresult.getVtsDatamodelnew().get(i).getStatus());

  			    array.add(ja);
  			}
  			result.put("aaData", array);
  		} catch (Exception ex) {
  			ex.printStackTrace();
  		} finally {
  		}
  		return result;
  	}
  	
      
}
