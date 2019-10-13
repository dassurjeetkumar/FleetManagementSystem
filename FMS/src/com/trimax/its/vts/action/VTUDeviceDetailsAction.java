package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vts.dao.VTUDeviceDetailsDao;

public class VTUDeviceDetailsAction {
	
	public String execute()
	{
		return "success";
	}
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	public String getVtuDeviceDetailsAjax() throws IOException{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

		VTUDeviceDetailsDao vdao=new VTUDeviceDetailsDao();
		
		try{
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");	
			
			JSONObject result = new JSONObject();
			String fromdate=request.getParameter("date1");
			String deviceId=request.getParameter("device_id");
			String todate=request.getParameter("date2");
			String hour=request.getParameter("hour");
			int hourCount=Integer.parseInt(request.getParameter("count"));
			int total = -1;
//			total=vdao.getTotalRecords(request, deviceId,fromdate,todate,hourCount);
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=vdao.getDeviceRecord(request,deviceId,fromdate,todate,hourCount);
			out.print(result);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;

	}

}
