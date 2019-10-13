package com.trimax.its.vts.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.trimax.its.common.Common;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Route;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.StageWiseTicketConsumptionDOA;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

public class BusStopWiseSkipStopAction {

	private Map<Integer, String> divisionlist;
	OrganisationChart orgchart;
	private String depotlist1;
	private Map<Integer, String> divisionlist1;
	private List<Route> routeList;
	private String route;
	public String startdate;
	public String enddate;

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
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

	public List<Route> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Route> routeList) {
		this.routeList = routeList;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
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


	public String execute() throws Exception {
		Date date = new Date();
 		Date date1 = new Date();
 		Date addedDate2 = addDays(date1, 2);
		try {
 			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			setStartdate(sm3.format(addedDate2));
 			setEnddate(sm2.format(date));
 		} catch (Exception ex) {

 		}
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDepotWithoutAll();
         return "success";
	}
	
	public static Date addDays(Date d, int days) {
		d.setTime(d.getTime()  - 86400 * 1000);
		return d; 
	}
	
//	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString=new StringBuffer();
	
   /* @SuppressWarnings("finally")
    public String getSkipStopData() throws SQLException
    {
   
        HttpServletRequest req = ServletActionContext.getRequest();
                System.out.println("HI I bus stop skip stop");
                try {
               Common common=new Common();
        			Date ss1 = new Date();
        			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
        			String runDateTime = sdf.format(ss1);
        			  SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm ");
  				    String startdate1 = common.getDateTimeFromPickerToDB(startdate);
  				    String enddate1 = common.getDateTimeFromPickerToDB(enddate);
        			System.out.println("start--" +startdate1 +"end==="+enddate1);
        			
        			String stopId=req.getParameter("busStopId");
        			System.out.println("stop is==== "+stopId);
        			
        			model.jaxb.xml.trimax.com.VtsResponse6 skipresult = null;
					com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					skipresult=port.webGetBusStopWiseSkipStop(startdate1, enddate1, stopId, rbKey);
					
					regionTypeAjaxString.append("<div id='header' style='display: none;'><div align='center'><h4>"+ " </br>"
							+ " </br>Bus Stop Wise Skip Stop Report</br>From Date:- "
	             	+ startdate1			+ "To Date:- "+ enddate1+ "</h4></div>");
			regionTypeAjaxString.append("<div align='right'><b>Current Date:-</b>"+ runDateTime + "</div></div>");

			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString.append("<thead><tr><th>Sr No.</th><th>Vehicle No.</th><th>Driver Token No.</th><th>Schedule No</th><th>Skipped Date&Time</th>"
							+ "</tr></thead><tbody>");
			
					        System.out.println("size-----"+skipresult.getWaybillDetails().size());
					    	for (int i = 0; i < skipresult.getWaybillDetails().size(); i++) {
								int j=i+1;
								regionTypeAjaxString.append("<tr>");
								regionTypeAjaxString.append("<td>"+(i+1)+"</td>");
								regionTypeAjaxString.append("<td>"+skipresult.getWaybillDetails().get(i).getVEHICLENO()+"</td>");
								regionTypeAjaxString.append("<td>"+skipresult.getWaybillDetails().get(i).getDRIVERTOKENNO()+"</td>");
								regionTypeAjaxString.append("<td>"+skipresult.getWaybillDetails().get(i).getSCHEDULENAME()+"</td>");
								regionTypeAjaxString.append("<td>"+skipresult.getWaybillDetails().get(i).getDUTYDT()+"</td>");
					
							
								regionTypeAjaxString.append("</tr>");
		                    }
					  
							 regionTypeAjaxString.append( "</tbody></table></div> </div>"); 
							 if(skipresult.getWaybillDetails().size()==0){
						    		regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
						    		regionTypeAjaxString.append("<tr>");
									regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
									regionTypeAjaxString.append("</tr>");	 
								 }
								HttpServletResponse response = ServletActionContext.getResponse();
							PrintWriter out;
							out = response.getWriter();
							out.print(regionTypeAjaxString);

				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
                 
*/

@SkipValidation
public String getbusStopList() {
	// get Depot List..
	
	System.out.println("in bus stop drop down");
	try {
		HttpServletRequest req = ServletActionContext.getRequest();
		RouteDAO routedao = new RouteDAO();
		String busstopname=(req.getParameter("stopName"));
		List<BusStops> listroute = routedao.busStopDropDownList(busstopname);
		
		
		System.out.println("regionTypeAjaxString" + listroute.size());
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		out = response.getWriter();
		out.print(new JSONArray(listroute));
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return null;

}


public String getSkipStopData() {
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	try {
		JSONObject result = new JSONObject();
		int total = -1;
		
	/*	String startDateString = "06/27/2007";
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date startDate;
	    try {
	        startDate = df.parse(startDateString);
	        String newDateString = df.format(startDate);
	        System.out.println(newDateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }*/
		
		
		
		
		  Common common=new Common();
			  SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm ");
		    String startdate1 = common.getDateTimeFromPickerToDB(startdate);
		    String enddate1 = common.getDateTimeFromPickerToDB(enddate);
			System.out.println("start--" +startdate1 +"end==="+enddate1);
			
			String stopId=request.getParameter("busStopId");
			System.out.println("stop is==== "+stopId);
			
			 DateFormat df = new SimpleDateFormat("yyyy-mm-dd"); 
			 Date startDate;
			 startDate=df.parse(startdate1);
			String newDate=df.format(startDate);
			System.out.println("new  Date      "+newDate);
		
		
//		total = getSkipStopRecord(request, startdate1, enddate1,stopId);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result = getSkipStopRecord(request, startdate1, enddate1,stopId,newDate);
		out.print(result);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {

	}
	return null;

}





@SuppressWarnings("unchecked")
public JSONObject getSkipStopRecord(HttpServletRequest request, String startdate1,String enddate1,String busStopId,String dutyDate){
	JSONObject result = new JSONObject();
	try{
//		int depot=Integer.parseInt(depotId);
		model.jaxb.xml.trimax.com.VtsResponse6 skipresult = null;
		com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
		com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
		skipresult=port.webGetBusStopWiseSkipStop(startdate1, enddate1, busStopId,dutyDate, rbKey);
		org.json.simple.JSONArray array=new org.json.simple.JSONArray();
		for (int i = 0; i < skipresult.getWaybillDetails().size(); i++) {
			org.json.simple.JSONArray ja=new org.json.simple.JSONArray();
			// Calling vehicle result
			ja.add(i+1);
	
			ja.add(skipresult.getWaybillDetails().get(i).getVEHICLENO());
			ja.add(skipresult.getWaybillDetails().get(i).getDRIVERTOKENNO());
			ja.add(skipresult.getWaybillDetails().get(i).getSCHEDULENAME());
			ja.add(skipresult.getWaybillDetails().get(i).getDUTYDT());
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
