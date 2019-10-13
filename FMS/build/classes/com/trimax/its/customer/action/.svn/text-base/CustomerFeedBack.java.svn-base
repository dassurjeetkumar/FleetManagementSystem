package com.trimax.its.customer.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.customer.dao.CustomerFeedBackDao;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.EtmDeviceHistoryDao;

public class CustomerFeedBack extends ActionSupport{
	
	
	
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	
	

@SkipValidation
	public String execute() {
		System.out.println("in execute");
		return "success";
	}
	
	@SkipValidation
	 public String getFeedBackview(){
				 
		 HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			System.out.println("Enter into feedBack View");
			try {
				
//				RevenueSectorDao revenuedao = new RevenueSectorDao();
//				LineSectorDao linedao=new LineSectorDao();
				CustomerFeedBackDao cstdao=new CustomerFeedBackDao();
				String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
				System.out.println(viewdeletedrecord);
				/*
				 * int cnt = devicedao.getTotalRecords();
				 * System.out.println("Count------>" + cnt);
				 */
				String[] dbcol = {"","customer_feedback_id","route_number","vehicle_number","depot_name",
						"travel_datetime","mobileno","email_id","floor_clean","seats_clean","windows_clean","doors_clean","lighting_inside",
						"seats_damage","AC_working","crew_behavior","crew_helpful","crew_response",	"conductor_rating",	"driver_rating","trans_date",
						"complaint_ref_no",	"BMTC_rating",	"complaint_raised",	"subcategory_id","caller_name",	"caller_remarks",""};
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

				String colName = dbcol[col];
				int total = -1;
				total = cstdao.getTotalRecords(total, request,
						dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();

				result = cstdao.getData1(total, request,
						dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//				System.out.println("REsult of datatable------>" + result);
				out.print(result);
			} catch (Exception ex) {
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",
	                    ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
				// System.out.println("=====?" + ex);
				// ex.printStackTrace();
			} finally {

			}
		 
		 
		 
		 
		 return null;
	 }
	
	
	
	
	
	
}
