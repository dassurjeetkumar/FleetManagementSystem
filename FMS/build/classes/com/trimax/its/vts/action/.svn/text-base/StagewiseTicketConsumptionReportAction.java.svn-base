package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.trimax.its.common.Common;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Route;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.StageWiseTicketConsumptionDOA;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

public class StagewiseTicketConsumptionReportAction {

	private Map<Integer, String> divisionlist;
	public String startdate;
	private String depotlist1;
	public String divisionlist1;
	private List<Route> routeList;
	private String route;
	String str = "";
	String path = "";
	char ft = 15;
	String regionTypeAjaxString = "";

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

	public String execute() throws Exception {
		System.out.println("in Execute");
		return "success";
	}

	@SuppressWarnings("unchecked")
	public List<Route> getRouteListDropDown1() {

		Session session = null;
		List<Map<String, String>> aliasToValueMapList = null;
		List<Route> list1 = new ArrayList<Route>();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String today = formatter.format(new java.util.Date());
			session = HibernateUtil.getSession("hibernate.cfg.xml");

			String queryForRouteNos = "select route_number,route_id,route_direction, CONCAT(route_number, '/', route_direction) as route_detail from route where `status` = 'active' and  effective_till ='0000-00-00 00:00:00' and  deleted_status='0' ";
			Query queryObject = session
					.createSQLQuery(queryForRouteNos)
					.addScalar("route_number", Hibernate.STRING)
					.addScalar("route_id", Hibernate.INTEGER)
					.addScalar("route_direction", Hibernate.STRING)

					.setResultTransformer(
							AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = queryObject.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, String> map = aliasToValueMapList.get(i);
				Route route1 = new Route();
				route1.setRoute_number(map.get("route_number") + ":"
						+ map.get("route_direction"));
				route1.setValue(map.get("route_number"));
				// setValue
				// route1.setRoute_name(String.valueOf(map.get("route_id"))
				// +"-"+String.valueOf(map.get("start_point_id"))+"-"+String.valueOf(map.get("end_point_id")));
				list1.add(route1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return list1;
	}

	public String getStagewiseTicketConsumptionReportData() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			Common common = new Common();
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			String startdate = req.getParameter("date");
			SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
			Date startDate = format.parse(startdate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM");     // change date in sql format

			String startDate1 = fomat2.format(startDate).toString();
			System.out.println("start--" + startDate1);
			String fromdate = startDate1 + " 00:00:00";
			String todate = startDate1 + " 23:59:59";
			System.out.println("FROM " + fromdate + " TO " + todate);

			String filePath = "Report/";

			String fileName = "StagewiseTicketConsumptionReport.txt";

			regionTypeAjaxString += " <div id='header' style='display: none;'><div align='center'><h4>Stagewise Ticket Consumption Report</br>From Date:- "
					+ fromdate + " To Date:-" + todate + "</h4></br></div>";

			regionTypeAjaxString += "<div align='right'><b>Curent Date:-</b>"
					+ runDateTime + "</div></div>";

			String nwkr = ""
					+ "                                   Stagewise Ticket Consumption Report    "
					+

					"\n                                    From Date:"
					+ fromdate + "  End Date:" + todate
					+ "                                              ";
			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
			regionTypeAjaxString += "<thead><tr><th>Sr.No.</th><th>Depot Name</th><th>Vehicle No.</th><th>Device Id</th><th>IST Date</th>"
					+ "</tr></thead><tbody>";

			String headingprint = " "
					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n"
					+ add("SNo", 3)
					+ "|"
					+ add("Depot Name", 14)
					+ "|"
					+ add("Vehicle No.", 10)
					+ "|"
					+ add("Device Id", 9)
					+ "|"
					+ add("IST Date", 9)
					+ "|"
					+ " "
					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";

			String path = realpath + filePath + fileName;
			str += ft + nwkr + add(headingprint, 5);

			HttpServletResponse response = ServletActionContext.getResponse();
			int i = 1;
			int j = 1;

			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString); // to display
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

	static String addMoney(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		StringBuilder sb2 = new StringBuilder();

		// String sb1 =
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
			return str1;
		}
		a1 = a1 - m1;
		for (int i = 0; i < a1; i++) {
			sb2.append(" ");
		}
		sb2.append(sb);
		String sb1 = sb2.toString();
		return sb1;
	}
	
	public String getPerticularVehicle() {

		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	    String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	    
		List<String> l1 = dao.getVehicleID1(parentId);
		List<String> l2 = dao.getVehicleName1(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			//String vehiclearr[] = l1.get(i).toString().split("#");
		//System.out.println(l1.get(0));
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString()  + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	

	public String getRouteListData() {
		System.out.println("heree");
		
		
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			StageWiseTicketConsumptionDOA doa = new StageWiseTicketConsumptionDOA();
			String routename=req.getParameter("routename");
			System.out.println("route is------"+routename);
		List<Route> l1 = doa.getRouteListDropDown1(routename);
		System.out.println("regionTypeAjaxString" + l1.size());
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		out = response.getWriter();
		System.out.println(new JSONArray(l1));
		out.print(new JSONArray(l1));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	



	public String getScheduleFormFourID() {
		System.out.println("heree scedule");
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		String routename = req.getParameter("route");
	
		System.out.println("route id " + routename);
		StageWiseTicketConsumptionDOA doa = new StageWiseTicketConsumptionDOA();
		
		int routeId=doa.getRouteID(routename);
		System.out.println("iddddd"+routeId);
		
		List<String> l1 = doa.getScheduleNameFormFourId(routeId);
		String regionTypeAjaxString = "";
//		regionTypeAjaxString = "<option  value='ALL'>--ALL--</option>";
		for (int i = 0; i < l1.size(); i++) {
			String routeArr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<option value=" + routeArr[0] + ">"
					+ routeArr[1] + "</option>";

		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

}
