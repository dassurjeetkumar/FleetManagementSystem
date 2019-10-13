package com.trimax.its.device.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.dao.DeviceHistoryDao;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.DeviceHistory;

public class DeviceHistoryAction {
	DeviceHistory devicehis;
	Device device;
	public DeviceHistory getDevicehis() {
		return devicehis;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevicehis(DeviceHistory devicehis) {
		this.devicehis = devicehis;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

	String tabstatus;		
	
	
	public String getTabstatus() {
		return tabstatus;
	}
	public void setTabstatus(String tabstatus) {
		this.tabstatus = tabstatus;
	}
	@SkipValidation
	public String showDeviceHistory() {
		return "success";
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	@SkipValidation
	public String execute() throws IOException {
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			DeviceHistoryDao devicedao = new DeviceHistoryDao();

			
			String[] cols = { "","device_history_id","div.device_id","onboarding_date","malfunctioning_date","date_of_activity","div.updated_date","div.purchase_date","status","div.notes"};
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			total = devicedao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = devicedao.getData(total,request,cols[Integer.parseInt(sCol)],sdir);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
	}
	
	
	

		
	
}
