package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vts.dao.VtsDataDAO;

public class EarlyDutyStartFromHaltingAction extends ActionSupport{

	
	 public String selectedDate;
	
//	 public String divison;
	 
		StringBuffer regionTypeAjaxString = new StringBuffer();
	
	private Map<Integer, String> divisionlist;
//	
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
//
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	@Override
	public String execute() throws Exception {
		this.setDivisionlist(getDivisionName());
		return "success";
	}

	private Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  " +
						"where parent_id ='1' and deleted_status=0 and org_type_id = '2' order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	 public static String rbKey = String.valueOf(getSysParameterForVts());
	
	@SuppressWarnings("finally")
public String getEarlyDutyStartFromHalting()
  {
	try {
		Date  ss1=new Date();
	      Common common = new Common();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
		String date1=common.getDateFromPicker(selectedDate);
		
		String fromdate = date1 + " 00:00:00";
		String todate = date1 + " 23:59:59";
	
	model.jaxb.xml.trimax.com.VtsResponse6 result = null;
	com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
	com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
	result = port.webGetEarlyDutyStartFromHalt(fromdate, todate, rbKey); // calling Web service
		
		regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Early Duty End From Halting Location</br>From Date:- "
				+ selectedDate +  "</h4></br></div>");

		regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
				+ runDateTime + "</div></div>");

		regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot</th><th>Schedule</th><th>Duty Type</th><th>Vehicle No.</th><th>Sch End Time</th>" +
				"<th>Act End Time</th><th>Time Diff</th><th>Halt Loc</th>"
				+ "</tr></thead><tbody>");

		HttpServletResponse response = ServletActionContext.getResponse();
		int i = 1;
		int j=1;
		for (i = 0; i < result.getWaybillDetails().size(); i++) {

			regionTypeAjaxString.append("<tr>");
			regionTypeAjaxString.append("<td>" + j + "</td>");
			regionTypeAjaxString.append("<td>"
					+ result.getWaybillDetails().get(i).getDepotName()
					+ "</td>");
			regionTypeAjaxString.append("<td>"
					+ result.getWaybillDetails().get(i).getSCHEDULENAME()
					+ "</td>");
			regionTypeAjaxString.append("<td>"
					+ result.getWaybillDetails().get(i).getShift1()
					+ "</td>");
			regionTypeAjaxString.append( "<td>"
					+ result.getWaybillDetails().get(i).getVEHICLENO()
					+ "</td>");
			regionTypeAjaxString.append("<td>"
					+ result.getWaybillDetails().get(i).getENDTIME()
					+ "</td>");
			regionTypeAjaxString.append("<td>"+result.getWaybillDetails().get(i).getETMENDTIME()+"</td>");
			regionTypeAjaxString.append("<td>"+result.getWaybillDetails().get(i).getARRTIMEDIFF()+"</td>");
			regionTypeAjaxString.append("<td>"+result.getWaybillDetails().get(i).getSTARTBUSSTOPNAME()+"</td>");

			regionTypeAjaxString.append("</tr>");

			j++;
		}

		if (result.getWaybillDetails().size() == 0) { // if no records
			regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
			regionTypeAjaxString.append("<tr>");
			regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
			regionTypeAjaxString.append("</tr>");
		}

		PrintWriter out;
		out = response.getWriter();
		out.print(regionTypeAjaxString); // to print
	} catch (Exception e) {

		e.printStackTrace();
	}

	return null;
}
	

//	public String getDivison() {
//		return divison;
//	}
//
//	public void setDivison(String divison) {
//		this.divison = divison;
//	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
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
							

		}
	