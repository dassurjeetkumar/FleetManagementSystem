package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse5;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vehicle.dao.ETMAvailabilityDao;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class SOSVehicleDashboardAction extends ActionSupport {

	public int updatedScheduleMappingId;
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	public String totalSos;
	
	public String getTotalSos() {
		return totalSos;
	}

	public void setTotalSos(String totalSos) {
		this.totalSos = totalSos;
	}

	public String execute()
	{
		totalSos = String.valueOf(getTotalSOS());
		System.out.println("total SOS="+totalSos);
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
	@SkipValidation
	public String getSOSList() throws IOException{
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

		
		VtsDataDAO dao=VtsDataDAO.getInstance();
		Date FromDate = new Date();
		try{
			session = HibernateUtil.getSession("hibernate.cfg.xml");	
			
//			String[] dbcol={"","vehicle_no", "depot_n0","device_sim_no","schedule_no","total_sos_count","call_epbx","driver_token_no","cond_token_no","cond_mob_no","reason"};
//			String[] cols={"","vehicle_no", "depot_n0","device_sim_no","schedule_no","total_sos_count","call_epbx","driver_token_no","cond_token_no","cond_mob_no","reason"};
//			System.out.println("test2");
			JSONObject result = new JSONObject();

//			int amount = 10;
//			int start = 0;
//			int col = 0;
//			String dir = "asc";
//			String sStart = request.getParameter("iDisplayStart");
//			String sAmount = request.getParameter("iDisplayLength");
//			String sCol = request.getParameter("iSortCol_0");
//			String sdir = request.getParameter("sSortDir_0");
//		
//			
//					
//			if (sStart != null) {
//				start = Integer.parseInt(sStart);
//				if (start < 0) {
//				start = 0;
//				}
//			}
//			if (sAmount != null) {
//				amount = Integer.parseInt(sAmount);
//				if (amount < 10 || amount > 50) {
//					amount = 10;
//				}
//			}
//			if (sCol != null) {
//				col = Integer.parseInt(sCol);
//				if (col < 0 || col > 5)
//					col = 0;
//				}
//			if (sdir != null) {
//				if (!sdir.equals("asc"))
//					dir = "desc";
//			}
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
//			System.out.println("orgtypeid..........." + orgtypeid
//					+ "orgchartid................." + orgchartid);
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
//			String colName = cols[col];
//			int total = -1;
//			AMOUNT = amount;
//			SEARCH_TERM = request.getParameter("sSearch");
//			//COL_NAME = colName;
//			DIR = dir;
//			START = start;
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = dao.getDataForSosAlert("", request, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
			out.print(result);
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				/*if(session !=null){
					session.close();
				}*/
			}
			return null;

	}
		
	
	public int getTotalSOS() {
		
		Date FromDate = new Date();
		int total =0;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			// HibernateUtilVtu h = new HibernateUtilVtu();
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse alertresult =port.webGetTotalSosCountDashoboard(sm.format(FromDate).toString() + " 00:00:00",sm.format(FromDate).toString() + " 23:59:59", rbKey);
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			
			total=alertresult.getVtsDatamodel().get(i).getTotalcount();
			}
		} catch (Exception ex) {

		} finally {
		}
		return total;
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





	

