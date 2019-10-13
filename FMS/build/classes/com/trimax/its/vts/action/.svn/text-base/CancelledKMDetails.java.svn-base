package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;

public class CancelledKMDetails {
	
	/*
	Start Schedule Trip status report
	*/
public String getKMCancelDetailsAjax() throws IOException{
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		PrintWriter out = null;
		ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
		JSONObject result = new JSONObject();
			
			String formattedgivendate=request.getParameter("date").toString();
			String depotId=request.getParameter("depotId");
			String Scheduleno=request.getParameter("scheduleNo");
			Common cm = new Common();
//			String formattedgivendate = cm.getDateFromPicker(fromdate);
			try{
				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				response.setContentType("application/json");
				out = response.getWriter();
				result = dao.getDataForScheduleTripStatusReport(1, request, "", "",
						formattedgivendate, Scheduleno,depotId);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
		}

}