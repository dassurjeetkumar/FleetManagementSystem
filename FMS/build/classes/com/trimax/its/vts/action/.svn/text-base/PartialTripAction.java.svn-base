package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.trimax.its.common.Common;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

	public class PartialTripAction {
		
	private Map<Integer, String> divisionlist;
	public String startdate;
	private String depotlist1;
	public String divisionlist1;
	String str = "";
	String path = "";
	char ft = 15;

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

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	
	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}
		
		 public static String rbKey = String.valueOf(getSysParameterForVts());

		
		public String getPartialData() {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				JSONObject result = new JSONObject();
				String fromDate= request.getParameter("givendate");
				String depot_id= request.getParameter("depotid");
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date startDate = format.parse(fromDate);
				SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
				String startDate1 = fomat2.format(startDate).toString();
				System.out.println("fromDate"+fromDate+"startDate"+startDate1);				
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				result = getPartialDetails(startDate1,depot_id);
				out.print(result);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
			return null;

		}
		
		@SuppressWarnings("unchecked")
		public JSONObject getPartialDetails(String fromdate,String depot_id){
			JSONObject result = new JSONObject();
			try{
				
				model.jaxb.xml.trimax.com.VtsResponse6 activeresult = null;
				com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
				com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();

				activeresult = port.webGetPartialTripData(fromdate,depot_id);
				JSONArray array = new JSONArray();
				for (int i = 0; i < activeresult.getWaybillDetails().size(); i++) {
					JSONArray ja = new JSONArray();
					// Calling vehicle result
					ja.add(i+1);
					ja.add(activeresult.getWaybillDetails().get(i).getSCHEDULENAME());
					ja.add(activeresult.getWaybillDetails().get(i).getTripNumber());
					ja.add(activeresult.getWaybillDetails().get(i).getShiftTypeName());
					ja.add(activeresult.getWaybillDetails().get(i).getROUTENO());
					ja.add(activeresult.getWaybillDetails().get(i).getVEHICLENO());
					ja.add(activeresult.getWaybillDetails().get(i).getSTARTBUSSTOPNAME());
					ja.add(activeresult.getWaybillDetails().get(i).getENDBUSSTOPNAME());
				    array.add(ja);
				}
				result.put("aaData", array);
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
