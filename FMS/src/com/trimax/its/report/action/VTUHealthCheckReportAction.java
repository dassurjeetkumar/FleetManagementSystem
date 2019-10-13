package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

import model.jaxb.xml.trimax.com.VtsResponse;
import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

	public class VTUHealthCheckReportAction {
		
//	private Map<Integer, String> divisionlist;
	public String startdate;
	private String depotlist1;
//	public String divisionlist1;
	private Map<Integer, String> divisionlist1;
	String str = "";
	String path = "";
	char ft = 15;

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	

//	public Map<Integer, String> getDivisionlist() {
//		return divisionlist;
//	}
//
//	public void setDivisionlist(Map<Integer, String> divisionlist) {
//		this.divisionlist = divisionlist;
//	}
	
	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	
	private Map<Integer, String> alertlist1;

	
//	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString = new StringBuffer();
	
	public String execute() throws Exception {
	
		return "success";
	}
		
		 public static String rbKey = String.valueOf(getSysParameterForVts());
		 
		public String getSystemHealthData(){
		try {
			HttpServletRequest req=ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = null;
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			Common common = new Common();
			Transaction transaction=null;
			Session session1 = null;
			String startDate=req.getParameter("startdate");
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate1 = format.parse(startDate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
			String fromdate = fomat2.format(startDate1).toString();
			int reason=Integer.parseInt(req.getParameter("reason"));
			System.out.println("reason-----"+reason+"========="+fromdate);
			
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = getHealthCheckNRDDevices(fromdate,reason);
			out.print(result);
			
		}catch (Exception e) {
			e.printStackTrace();
		}			
		return null;
	}
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getHealthCheckNRDDevices(String fromdate,int reason) {
			JSONObject result = new JSONObject();
			try {
				boolean flag = false;
			     NewWsVts_Service service = new com.trimax.vts.NewWsVts_Service();
				 com.trimax.vts.NewWsVts port = service.getNewWsVtsPort();

			     VtsResponseNew alertresult = port.getHealthCheck(rbKey,fromdate, reason,3);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getVtsDatamodelnew().size(); i++) {
					JSONArray ja = new JSONArray();
	                 ja.add(i+1);
	                 ja.add(alertresult.getVtsDatamodelnew().get(i).getOrgName());
					ja.add(alertresult.getVtsDatamodelnew().get(i).getLICENCENUMBER());
					ja.add(alertresult.getVtsDatamodelnew().get(i).getDEVICEID());
					array.add(ja);
				}

				result.put("aaData", array);
				result.put("iTotalRecords", alertresult.getVtsDatamodelnew().size());

				result.put("iTotalDisplayRecords", 8);
			} catch (Exception ex) {
				ex.printStackTrace();

			} finally {
			}
			return result;
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
