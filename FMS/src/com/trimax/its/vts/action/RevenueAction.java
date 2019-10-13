package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.action.DashBoardDao;
import com.trimax.its.vts.dao.VtsDataDAO;

public class RevenueAction  extends ActionSupport{

	public String getWeeklyRevenue() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		DashBoardDao dao = new DashBoardDao();
		JSONObject result = new JSONObject();

		try {
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForWeeklyRevenue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}
	public String getMonthlyevenue() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		DashBoardDao dao = new DashBoardDao();
		JSONObject result = new JSONObject();

		try {
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForMonthlyRevenue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}
}
