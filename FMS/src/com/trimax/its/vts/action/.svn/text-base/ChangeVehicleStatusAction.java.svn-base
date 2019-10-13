package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.ccc.dao.cccDaoData;
import com.trimax.its.model.User;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.VehicleDefectsDao;
import com.trimax.its.vehicle.model.VehicleNumber;
import com.trimax.its.vts.dao.ChangeVehicleStatusDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.Busstop;
import com.trimax.ws.vts.vtsutility.VtsDataModel;

public class ChangeVehicleStatusAction extends ActionSupport {
	ChangeVehicleStatusDAO dao = new ChangeVehicleStatusDAO(); // Create Object Of DAO
	// method for save date into the DB
		public String insertData() throws IOException {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/text");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			
			String call_id = "";
			String device_id = request.getParameter("device_id");
			String call_time = request.getParameter("gpstime");
			
			String vehicle_no = request.getParameter("vehicle_no");
			
			String depot_name = request.getParameter("depotnm");
//			System.out.println("depot name---"+depot_name);
			String Schedule_no = request.getParameter("scheduleno");
//			System.out.println("no is=="+Schedule_no);
			String scheduleId = request.getParameter("schId");
//			System.out.println("getting"+scheduleId);
			String schname=getScheduleNamewithFormFourId(scheduleId);
//			System.out.println("sch name for insert"+schname);
			String call_type = request.getParameter("call_type");
			String description = request.getParameter("notes");
			String accident_type = request.getParameter("acctypelist");
			String breakdown_type = request.getParameter("brkdwntypelist");
			String depotid=request.getParameter("depot_id");
			String driverToken=request.getParameter("driverToken");
			User user = (User) request.getSession().getAttribute("user");
			String username=user.getFirstname();
			Integer userid=user.getUserId();
			int funcStatus = 0;
			System.out.println("call_type########"+call_type);
			
			if(call_type.equals("1")){
				funcStatus = dao.updateVehicleNormal(vehicle_no); 
			} else if (call_type.equals("2")) {
				
				funcStatus = dao.insertAccidentDetail(
						userid,username,call_id,
						device_id, call_time, vehicle_no, depot_name, schname, call_type,
						description, accident_type, breakdown_type,depotid,driverToken); 
				
			} else if (call_type.equals("3")) {
				
				funcStatus = dao.insertBreakDownDetail(
						userid,username,call_id,
						device_id, call_time, vehicle_no, depot_name, schname, call_type,
						description, accident_type, breakdown_type,depotid,driverToken); 
				
			} else {
				//For Vehicle Status 'Other' -- Do Nothing
			}
			System.out.println("funcStatus----"+funcStatus);
			if(funcStatus>0){
				out.print("success");
			}else{
				out.print("error");
			}
			out.close();
			return null;
		}
		
		public String getScheduleNamewithFormFourId(String scheduleId){
			Session session=null;
			String scheduleName="";
			try{
				session=HibernateUtil.getSession("hibernate.cfg.xml");	
			Query query =session.createSQLQuery("SELECT schedule_number_name FROM `form_four` WHERE `form_four_id` = '"+scheduleId+"' AND `deleted_status` = '0' AND `current_status` = 'active'")
					.addScalar("schedule_number_name");
			scheduleName = (String)query.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
			
			return scheduleName;
		}
		
		
}
