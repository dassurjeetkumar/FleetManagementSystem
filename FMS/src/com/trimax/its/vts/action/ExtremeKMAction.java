

package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;
import org.hibernate.Session;

	public class ExtremeKMAction {
		
	public String startdate;
	public String endDate;
	public String km_limit;
	public String type;
	private String depotlist1;
	public String divisionlist1;
	private Map<Integer, String> divisionlist;
	  
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKm_limit() {
		return km_limit;
	}

	public void setKm_limit(String km_limit) {
		this.km_limit = km_limit;
	}

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

	public String getExtremeKmDeviceList() {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try {
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			System.out.print("depotNo is---" + depotlist1);
			System.out.println("division id" + divisionlist1);

			int depotId = Integer.parseInt(depotlist1);
			int divId = Integer.parseInt(divisionlist1);
			System.out.println("km   " + km_limit);
		
			String limit=type+km_limit;
			System.out.println("type  "+limit);
			String qryy = "";
			String qry2 = "in(";
			String qry = "";

			if (divId == 0 && depotId == 0) { // if div and depot selected 'All'
				qryy = " !=0";

			} else

			if (divId != 0 && depotId == 0) { // only depot="All" selected
				List<String> l1 = dao.getDepotId(divId);
				for (int i = 0; i < l1.size(); i++) {
					qry += l1.get(i).toString() + ",";
				}
				qry = qry2 + qry + ")";
				System.out.println("qry is=====" + qry);
				String depotIn = remove(qry);
				qryy = depotIn + ")"; // qryy=" in(10,11,12,13,15)" ;
				// depotIn =depotIn+")";

			} else {

				// if division and depot selected
				qryy = "in('" + depotId + "')"; // qryy="in(10)"
			}
			System.out.println("added--" + qryy);

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
//			Date endate = format.parse(endDate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			String startDate1 = fomat2.format(startDate).toString();
//			String endDate = fomat2.format(endate).toString();
			String endDate = "";

			String fromdate = startDate1;
			String todate = endDate;
			System.out.println("FROM " + fromdate + " TO " + todate);
			model.jaxb.xml.trimax.com.VtsResponse6 result = null;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			result = port.webGetExtremeKmReport(fromdate, todate, qryy,limit, rbKey); // calling Web service

			regionTypeAjaxString
					.append(" <div id='header' style='display: none;'><div align='center'><h4>EXTREME KM DEVICE REPORT</br>From Date:- "
							+ fromdate
							+ " To Date:-"
							+ todate
							+ "</h4></br></div>");
			regionTypeAjaxString
					.append("<div align='right'><b>Current Date:-</b>"
							+ runDateTime + "</div></div>");

			regionTypeAjaxString
					.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString
					.append("<thead><tr><th>Sr.No.</th><th>Date</th><th>Depot Name</th><th>Vehicle No.</th><th>Device No</th><th>Travelled KM</th>"
							+ "</tr></thead><tbody>");

			HttpServletResponse response = ServletActionContext.getResponse();
			int i = 1;
			int j = 1;
			System.out.println("size-" + result.getWaybillDetails().size());
			for (i = 0; i < result.getWaybillDetails().size(); i++) {

				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString.append("<td>" + j + "</td>");
				regionTypeAjaxString.append("<td>"
						+ result.getWaybillDetails().get(i).getFIRSTDATE());
				regionTypeAjaxString.append("<td>"
						+ result.getWaybillDetails().get(i).getOrgName()
						+ "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ result.getWaybillDetails().get(i).getSCHEDULENO()
//						+ "</td>");
				regionTypeAjaxString.append("<td>"
						+ result.getWaybillDetails().get(i).getVEHICLENO()
						+ "</td>");
				regionTypeAjaxString.append("<td>"
						+ result.getWaybillDetails().get(i).getDEVICEID()
						+ "</td>");

				regionTypeAjaxString.append("<td>"
						+ result.getWaybillDetails().get(i).getKmdiff()
						+ "</td>");

				regionTypeAjaxString.append("</tr>");

				j++;
			}

			if (result.getWaybillDetails().size() == 0) { // if no records
				regionTypeAjaxString
						.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString
						.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
				regionTypeAjaxString.append("</tr>");
			}

			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString); // to print
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
		
		
		public String remove(String str) {
		    if (str != null && str.length() > 0 && str.charAt(str.length()-2)==',') {
		      str = str.substring(0, str.length()-2);
		    }
		    return str;
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





	
	
	

