package com.trimax.its.vts.report.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DailyCheckList extends ActionSupport{
	
	public String startdate;
    public String enddate;
    private Map<Integer, String> divisionlist;
	private Map<Integer, String> depotlist;
	private String depotlist1;
	private String divisionlist1;
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

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

	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}

	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}

	public String getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(String divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}
	
	@Override
	public String execute() throws Exception {
		VtsDataDAO vdd=VtsDataDAO.getInstance();
		divisionlist=vdd.getDivisionName();
		return "success";
	}
	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString1= new StringBuffer();
	public String getDailyCheckList() throws SQLException{
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
//		Transaction transaction  = null;
		HttpServletRequest req=ServletActionContext.getRequest();
		Common common =new Common();
			CollectionReportDAO dao= new CollectionReportDAO();
			String date1=dao.getDateFromPickerDate(startdate);
			String selected_date = common.getDateFromPicker(date1);
			System.out.println("date===="+date1);
//			String date2=dao.getDateFromPickerDate(enddate);
			int depotId=Integer.parseInt(req.getParameter("depotlist1"));
			String divisionlist=req.getParameter("divisionlist1");
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
//			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		    try {    
//		    System.out.println("FROM " + date1 + " TO " + date2);
			model.jaxb.xml.trimax.com.VtsResponse6 activeresult = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			activeresult = port.webGetDailyCheckListReportCount(depotId, date1, date1); // calling Web service
		 
			VtsDataDAO vtd = VtsDataDAO.getInstance();
		@SuppressWarnings("unchecked")
		List<String> totalSch= vtd.getDepotDateWiseScheduleNo(depotId, startdate);
		 int totalSchedules = totalSch.size();
//		 System.out.println("total size =="+totalSchedules);
					
		 regionTypeAjaxString1.append("<div id='header1' ><div align='center'><h4></br> </br>Daily Check List</br></h4></div>");
					regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'><h4></br> </br>Daily Check List</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>");

					regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
					
					regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'  align='center'>");
					int not_op = totalSchedules-activeresult.getWaybillDetails().get(0).getTotalOperated();
					System.out.println("not operated==="+not_op);
//					 for (int i = 0; i < activeresult.getWaybillDetails().size(); i++) {
					regionTypeAjaxString1.append("<thead><tr><th>No.Of Schedules -</th><td colspan='3'>&nbsp;&nbsp;&nbsp;"+   totalSchedules  +"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr colspan='2'><th>No.Of Schedules Operated -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getTotalOperated()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules Not Operated -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+not_op+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules Departed Late -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getTotalLate()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules Departed Early -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getTotalEarly()+"&nbsp;&nbsp;&nbsp;</td></tr>"
							+ "    <tr><th>No.Of Schedules All Trip Completed -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getCompletedSchedules()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules Certain Trip Cancelled -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getCertainCancelled()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules >95% Schedule KM Covered - </th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(1).getPerc95()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules <95% Schedule KM Covered - </th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(1).getPerc9575()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules <75% Schedule KM Covered - </th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(1).getPerc7550()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>No.Of Schedules <50% Schedule KM Covered - </th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(1).getPerc50()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
					    	"      <tr><th>Schedule KM -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getSchDistance()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>GPS KM -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getGpsDistance()+"&nbsp;&nbsp;&nbsp;</td></tr> " +
							"      <tr> <th>As Per Logsheet -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+0+"&nbsp;&nbsp;&nbsp;</td></tr> " +
							"      <tr><th>Fuel Consumption Per BPC -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getKmdiff()+"&nbsp;&nbsp;&nbsp;</td></tr>" +
							"      <tr><th>Late Dept with Reasons -</th><td colspan='2'>&nbsp;&nbsp;&nbsp;"+activeresult.getWaybillDetails().get(0).getTotal()+"&nbsp;&nbsp;&nbsp;</td></tr></thead><tbody>");
				        				        
                      regionTypeAjaxString1.append("</table></div>");
//					 }
					        HttpServletResponse response = ServletActionContext.getResponse();
//					        for (int i = 0; i < activeresult.getWaybillDetails().size(); i++) {
//								
////								regionTypeAjaxString1.append("<tr>");
//								regionTypeAjaxString1.append("<tr>"+ totalSchedules+ "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ activeresult.getWaybillDetails().get(i).getTotalOperated() + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ activeresult.getWaybillDetails().get(i).getTotalLate() + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ activeresult.getWaybillDetails().get(i).getTotalEarly() + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
//								regionTypeAjaxString1.append("<tr>"+ "0" + "</tr>");
////								regionTypeAjaxString1.append("</tr>");
//
//								
//							}
//					        
					        
					        
//		  					 while (rs.next()) {
//						regionTypeAjaxString1.append("<tr>");
//						
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("<td align='right'>"+""+"</a></td>");
//						regionTypeAjaxString1.append("</tr>");
//					 }
		  			PrintWriter out;

					

					out = response.getWriter();
					out.print(regionTypeAjaxString1);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (rs != null) {
            	rs.close();
            }
            
            if (stmt != null) {
            	stmt.close();
            }

            if (connection != null) {
                connection.close();
            }
            if(session1 !=null){
            	 session1.close();
             }
		}
		return null;
	}
	
	
	  @SuppressWarnings("finally")
      public String getDailyCheckListData()
      {
         
		  System.out.println("in daily check");
          HttpServletRequest req = ServletActionContext.getRequest();
          HttpServletResponse response = ServletActionContext.getResponse();
                  try {
                  	JSONObject result = new JSONObject();
                  Date  ss1=new Date();
                  CollectionReportDAO dao= new CollectionReportDAO();
                  SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
                  String runDateTime = sdf.format(ss1);
                  String date1=dao.getDateFromPickerDate(startdate);
      			String date2=dao.getDateFromPickerDate(enddate);
      			int depotId=Integer.parseInt(req.getParameter("depotlist1"));
      			String divisionlist=req.getParameter("divisionlist1");
                
                  response.setContentType("application/json");
      			response.setHeader("Cache-Control", "no-store");
      			PrintWriter out = response.getWriter();
      			result = getDataForDailyCheckList(depotId, date1,date2);
      			out.print(result);       // to print
          		} catch (Exception e) {
          			e.printStackTrace();
          		}
          		return null;
          	}
	  
	  
	  @SuppressWarnings("unchecked")
  	public JSONObject getDataForDailyCheckList(int depotId,String fromDate,String todate){
  		JSONObject result = new JSONObject();
  		try{
  			model.jaxb.xml.trimax.com.VtsResponse6 activeresult = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			activeresult = port.webGetDailyCheckListReportCount(depotId, fromDate, todate); // calling Web service
		 
			VtsDataDAO vtd = VtsDataDAO.getInstance();
		@SuppressWarnings("unchecked")
		List<String> totalSch= vtd.getDepotDateWiseScheduleNo(depotId,fromDate);
		 int totalSchedules = totalSch.size();
		 System.out.println("total size =="+totalSchedules);
		 int not_op = totalSchedules-activeresult.getWaybillDetails().get(0).getTotalOperated();
  		JSONArray array =new JSONArray();
//  			for (int i = 0; i < activeresult.getWaybillDetails().size(); i++) {
  				JSONArray ja = new JSONArray();
  				ja.add(totalSchedules);
  			
  				ja.add(activeresult.getWaybillDetails().get(0).getTotalOperated());
  				ja.add(not_op);
  				ja.add(activeresult.getWaybillDetails().get(0).getTotalLate());
  				ja.add(activeresult.getWaybillDetails().get(0).getTotalEarly());
  				ja.add(activeresult.getWaybillDetails().get(0).getCompletedSchedules());
  				ja.add(activeresult.getWaybillDetails().get(0).getCertainCancelled());
  				ja.add(activeresult.getWaybillDetails().get(1).getPerc95());
  				ja.add(activeresult.getWaybillDetails().get(1).getPerc9575());
  				ja.add(activeresult.getWaybillDetails().get(1).getPerc7550());
  				ja.add(activeresult.getWaybillDetails().get(1).getPerc50());
  				ja.add(activeresult.getWaybillDetails().get(0).getSchDistance());
  				ja.add(activeresult.getWaybillDetails().get(0).getGpsDistance());
  				ja.add(0);
  				ja.add(activeresult.getWaybillDetails().get(0).getVtuKMPL());
  				ja.add(activeresult.getWaybillDetails().get(0).getTotal());
  				
  				
  			    array.add(ja);
//  			}
  			result.put("aaData", array);
  		} catch (Exception ex) {
  			ex.printStackTrace();
  		} finally {
  		}
  		return result;
  	}
	
	
	

}
