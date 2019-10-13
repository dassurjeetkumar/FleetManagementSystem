package com.trimax.its.vts.action;

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

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;

	public class DepotwiseScheduleTripOperatedAction {
		
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

//	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString = new StringBuffer();
	
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
		
	 public static String rbKey = String.valueOf(getSysParameterForVts());	 
	
	
		public String getDepotWiseScheduleDetails()
 {
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			Date ss1 = new Date();
			VtsDataDAO dao=VtsDataDAO.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
Session ses=null;
//String orgname=dao.getOrgName();
			Transaction transaction=null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
			String startDate1 = fomat2.format(startDate).toString();
			System.out.println("depot"+depotlist1+"---start--"+startDate1+"------"+startdate);
			String depotName="";
			depotName = "depot_id="+depotlist1;
			System.out.println("depot name is--"+depotName);

			model.jaxb.xml.trimax.com.VtsResponse6 result= null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			result=port.getScheduleStatus(startDate1, depotName, rbKey);
			
			
			regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Depot wise Schedule Trips Operated Report</br>Date:- "
					+ startdate + "</h4></br></div>");

			regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
					+ runDateTime + "</div></div>");

			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Schedule</th><th>Duty Type</th><th>Total Trips</th><th>Fully Operated</th><th>Partially Operated</th><th>Deviated</th><th>Not Operated</th>"
					+ "</tr></thead><tbody>");
			
			System.out.println("size-"+ result.getWaybillDetails().size());
		
               for (int i = 0; i < result.getWaybillDetails().size(); i++) {
				int j=i+1;
				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString.append("<td>" + j + "</td>");
				regionTypeAjaxString.append("<td>"+ result.getWaybillDetails().get(i).getSCHEDULENAME() +"</td>");
				regionTypeAjaxString.append("<td>"+ result.getWaybillDetails().get(i).getShiftTypeName() +"</td>");
				regionTypeAjaxString.append("<td>"+ result.getWaybillDetails().get(i).getTOTALTRIP() +"</td>");
				regionTypeAjaxString.append("<td>"+ result.getWaybillDetails().get(i).getCompeted() +"</td>");
				regionTypeAjaxString.append("<td>"+result.getWaybillDetails().get(i).getPARTIAL()+"</td>");
				regionTypeAjaxString.append("<td>"+result.getWaybillDetails().get(i).getDeviatedTrip()+"</td>");
				regionTypeAjaxString.append("<td>"+result.getWaybillDetails().get(i).getNOTOperated() +"</td>");
				

				regionTypeAjaxString.append("</tr>");

				
			}
			
               if (result.getWaybillDetails().size() == 0) { // if no records
       				regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
       				regionTypeAjaxString.append("<tr>");

       				regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
       				regionTypeAjaxString.append("</tr>");
       			}

			HttpServletResponse response = ServletActionContext.getResponse();
			
						PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString);       // to print
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	static String add(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
		}
		a1 = a1 - m1;
		for (int i = 0; i <= a1; i++) {
			sb.append(" ");
		}
		String sb1 = sb.toString();
		return sb1;
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
