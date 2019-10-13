package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.ETMAvailabilityDao;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vehicle.dao.VehicleDeviceRelationDAO;
import com.trimax.its.vts.dao.ScheduleTripUpdate;
import com.trimax.its.vts.dao.UpdateTripStartEndEvent;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WayBillDetails;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class DeviceDisconnectedAction extends ActionSupport {

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	public String execute()
	{
//		this.setModelTypeList(daoObject.getModelTypeList());
		return "success";
	}

   private String orgdepotname;
	public String getOrgdepotname() {
	return orgdepotname;
}

public void setOrgdepotname(String orgdepotname) {
	this.orgdepotname = orgdepotname;
}
Session session = null;

	
	public String getDeviceDisconnectData() {

		
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		VehicleDeviceRelationDAO dao=new VehicleDeviceRelationDAO();
		JSONObject result = new JSONObject();
	
		try {
			
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");
			System.out.println("orgtypeid..........." + orgtypeid
					+ "orgchartid................." + orgchartid);
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = getDeviceDisconnectList(1, req, "", "",id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	//webservice call End
	
	
	public JSONObject getDeviceDisconnectList(int j,
			HttpServletRequest req, String string, String string2,String orgchartid) {

		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			System.out.println("now xall");
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			VtsResponse6 alertresult = port.getDisconncetedDeviceList(rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				 JSONArray ja = new JSONArray();
				
					ja.add(i+1);
					
					ja.add(alertresult.getWaybillDetails().get(i).getDEVICEID());
					ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
					ja.add(alertresult.getWaybillDetails().get(i).getDepotName());
					ja.add(alertresult.getWaybillDetails().get(i).getENDLATITUDE());
					ja.add(alertresult.getWaybillDetails().get(i).getENDLONGITUDE());
					ja.add(alertresult.getWaybillDetails().get(i).getFIRSTDATE());
					
					array.add(ja);
				 
				 }
				 result.put("aaData", array);
				 } catch (Exception ex) {
				 ex.printStackTrace();
				 }
				 return result;
				 }
	
	
	public static String rbKey = String.valueOf(getSysParameterForVts());

	

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





	

