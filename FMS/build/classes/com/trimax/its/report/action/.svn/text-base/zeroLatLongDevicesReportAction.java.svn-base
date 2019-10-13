package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.report.dao.ScheduleWiseOperaDataDao;
import com.trimax.its.report.dao.ZeroKMDevicesReportDao;
import com.trimax.its.report.dao.ZeroLatLongDevicesReportDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class zeroLatLongDevicesReportAction {
	
	
	 public String startdate;
	 private Map<Integer, String> divisionlist;
	 private String depotlist1;
	 public String divisionlist1;
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}


	public String execute() {
	
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
	
	public String getZerolatLongDevicePackets() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			JSONObject result = new JSONObject();
			ZeroLatLongDevicesReportDao cdao = new ZeroLatLongDevicesReportDao();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			String startDate1 = fomat2.format(startDate).toString();
			//int total = -1;  
			//total = cdao.getTotalZeroKMDevicesRecords(request, depotlist1, startDate1);
			//response.setContentType("application/json");
			//response.setHeader("Cache-Control", "no-store");
			//
			System.out.println("depot name"+depotlist1+"    and date "+startDate1);
			result = cdao.getDataForZeroLatLongDevices(request,depotlist1, startDate1);
			System.out.println("Ashu after result result "+result);
			PrintWriter out = response.getWriter();
			out.print(result);
		} catch (Exception e) {
			System.out.println("probem in Action");
			e.printStackTrace();
		} finally {

		}
		return null;

	}

	public static void main(String[] args) {
		
	}
}
