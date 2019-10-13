package com.trimax.its.report.action;

import java.io.FileOutputStream;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;

import com.opensymphony.xwork2.ActionSupport;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class TripWiseTripOperationReportAction extends ActionSupport {
	/**
     *
     */
	private static final long serialVersionUID = 1L;

	public String startdate;
	private String depotlist1;
	public String divisionlist1;
	private Map<Integer, String> divisionlist;

	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	// String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString = new StringBuffer();

	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}

	public static String rbKey = String.valueOf(getSysParameterForVts());

	@SuppressWarnings("finally")
	public String getTripOperationData() {

		HttpServletRequest req = ServletActionContext.getRequest();
		try {

			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			String depot = req.getParameter("depotId");
			int division = Integer.parseInt(req.getParameter("divId"));
			int schId = Integer.parseInt(req.getParameter("scheduleNo"));
			String todate = req.getParameter("endate");
			String startdate = req.getParameter("date");
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
																			
			String startDate1 = fomat2.format(startDate).toString();
			Date toDate = format.parse(todate);
			String toDate1 = fomat2.format(toDate).toString();

			model.jaxb.xml.trimax.com.VtsResponse6 tripResult = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			tripResult = port.webGetTripWiseDistanceTravelledPerc(startDate1, toDate1,depot, rbKey, schId);

			regionTypeAjaxString.append("<div id='header' style='display: none;'><div align='center'><h4>"+ " </br>"
							+ " </br>Trip Wise % of Trip Operation Report</br>From Date:- "
	             	+ startdate			+ "To Date:- "+ toDate1+ "</h4></div>");
			regionTypeAjaxString.append("<div align='right'><b>Current Date:-</b>"+ runDateTime + "</div></div>");

			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString.append("<thead><tr><th>Sr No.</th><th>Schedule No.</th><th>Trip No.</th><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th>"
							+ "</tr></thead><tbody>");

			if (tripResult.getWaybillDetails().size() != 0) {
				for (int i = 0; i < tripResult.getWaybillDetails().size(); i++) {

					regionTypeAjaxString.append("<tr>");
					regionTypeAjaxString.append("<td>" + (i + 1) + "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getSCHEDULENAME() + "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getTripNumber() + "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getSunday()+ "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getMonday()+ "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getTueday()+ "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getWednesday() + "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getThursday() + "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getFriday()+ "</td>");
					regionTypeAjaxString.append("<td>"+ tripResult.getWaybillDetails().get(i).getSaturday() + "</td>");

					regionTypeAjaxString.append("</tr>");
				}
			}

			else { // no data if size==0
				regionTypeAjaxString
						.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString
						.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
				regionTypeAjaxString.append("</tr>");
			}

			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
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
	
	
	public String getScheduleListData(){
        
        HttpServletRequest req = ServletActionContext.getRequest();
        //VtsDataDAO dao = new VtsDataDAO();
        ScheduleTripDurationDAO dao= new ScheduleTripDurationDAO();
        int parentId = Integer.parseInt(req.getParameter("val"));
        //String date = req.getParameter("selectedDate");
        List<Integer> l1 = dao.getScheduleNameDurationID(parentId);
        List<String> l2 = dao.getScheduleNameDurationName(parentId);
        String regionTypeAjaxString = "";
        try {
            for (int i = 0; i < l1.size(); i++) {

                regionTypeAjaxString += "<option value=" + l1.get(i)
                        + ">" + l2.get(i).toString() + "</option>";
            }
            HttpServletResponse response = ServletActionContext.getResponse();
            PrintWriter out;

            out = response.getWriter();
            out.print(regionTypeAjaxString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

}
