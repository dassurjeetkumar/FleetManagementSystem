package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.trimax.its.common.Common;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

	public class DriverWiseHarshAccAction {
		
	public String startdate;
	public String endDate;
	
	private String depotlist1;
	public String divisionlist1;
	private Map<Integer, String> divisionlist;
	  
	

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

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

	
//	String regionTypeAjaxString = "";
	StringBuilder regionTypeAjaxString=new StringBuilder();

	
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
		
		
	public static String rbKey = String.valueOf(getSysParameterForVts());
	
	
	
	public String getHarshAcc() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			JSONObject result = new JSONObject();
			int depotId = Integer.parseInt(depotlist1);
			int divId = Integer.parseInt(divisionlist1);
		

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			String startDate1 = fomat2.format(startDate).toString();

			String fromdate = startDate1;
			String todate=startDate1; 
			String depotName=request.getParameter("depotName");
			System.out.println("FROM " + fromdate + " TO " +todate+"  "+depotName);
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = getHarshAcc1(request,
					depotId, fromdate,todate,startDate1,depotName);
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public JSONObject getHarshAcc1(HttpServletRequest request,int depotId,String fromdate,String endDate,String startDate1,String depotName ) {
		JSONObject result1 = new JSONObject();
		try {
			
			
		
			System.out.print("depotNo is---" + depotlist1+" == "+depotName);

			
			model.jaxb.xml.trimax.com.VtsResponse6 result = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			result = port.webGetDriverWiseHarshAccReport(depotId,fromdate,endDate,depotName); // calling Web service
			
			JSONArray array = new JSONArray();
			for (int i = 0; i < result.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i+1);
			ja.add(result.getWaybillDetails().get(i).getDRIVERNAME());
			ja.add(result.getWaybillDetails().get(i).getDRIVERTOKENNO());
			System.out.println("getDEVICEID()"+result.getWaybillDetails().get(i).getDEVICEID());
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal41'  onclick=javascript:viewDriverWiseHarshDetails('"
						+ endDate
						+ "','"
						+ startDate1+"','"+depotId+"','"+result.getWaybillDetails().get(i).getDEVICEID()+"','"+result.getWaybillDetails().get(i).getDepotName()+"') >"
						+ result.getWaybillDetails().get(i).getTotalcount()
						+ "</a>");
//				ja.add(result.getWaybillDetails().get(i).getVEHICLENO());
//				ja.add(result.getWaybillDetails().get(i).getDepotName());
			
			    array.add(ja);
			}
		result1.put("aaData", array);
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
	}
	return result1;
		
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
		
		
		
		public String getHarshDetails() throws IOException{
			
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();	
			PrintWriter out = null;
			ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
			JSONObject result = new JSONObject();
				
				String formattedgivendate=request.getParameter("date").toString();
				String depotId=request.getParameter("depotId");
				String endDate=request.getParameter("endDate");
				String deviceId=request.getParameter("deviceId").toString();
				
//				System.out.println("device id=="+deviceId);
				Common cm = new Common();
				try{
					// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					response.setContentType("application/json");
					out = response.getWriter();
					result = getDataForHarshAccDriverWise(formattedgivendate,endDate, deviceId,depotId);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
				return null;
			}
		
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getDataForHarshAccDriverWise(String selectedDate,String toDate,String deviceId,String depotName) {

			JSONObject result = new JSONObject();
			System.out.println("selectedDate== "+selectedDate+"=="+deviceId+"depot== "+depotName+"=="+toDate);
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
			
				VtsResponse6 alertresult = port.webGetDriverWiseHarshAccDrill(selectedDate,
						toDate, deviceId, depotName);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					JSONArray ja = new JSONArray();
					ja.add(i+1);
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
//				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(alertresult.getWaybillDetails().get(i).getDutyStartDate());
				
				    array.add(ja);
				}
				result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
		}
		
			 }





	
	
	

