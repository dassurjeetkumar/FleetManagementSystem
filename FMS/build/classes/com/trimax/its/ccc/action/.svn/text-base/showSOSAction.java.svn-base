package com.trimax.its.ccc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.ccc.dao.cccDaoData;
import com.trimax.its.model.User;
import com.trimax.its.vts.dao.VtsDataDAO;

public class showSOSAction extends ActionSupport {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private String SEARCH_TERM;

	@SuppressWarnings("unused")
	private String COL_NAME;

	@SuppressWarnings("unused")
	private String DIR;

	@SuppressWarnings("unused")
	private int START;

	@SuppressWarnings("unused")
	private int AMOUNT;
	cccDaoData dao = new cccDaoData(); // Create Object Of DAO here
	@SuppressWarnings("rawtypes")
	List sosList;

	@SuppressWarnings("rawtypes")
	public List getSosList() {
		return sosList;
	}

	@SuppressWarnings("rawtypes")
	public void setSosList(List sosList) {
		this.sosList = sosList;
	}
	public String accidentStatus;
	public String breakdownStatus;
	public String employeestatus;
	public String tripStatus;
	public String depotid;
	
	


	public String getDepotid() {
		return depotid;
	}

	public void setDepotid(String depotid) {
		this.depotid = depotid;
	}

	public String getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(String tripStatus) {
		this.tripStatus = tripStatus;
	}

	public String getEmployeestatus() {
		return employeestatus;
	}

	public void setEmployeestatus(String employeestatus) {
		this.employeestatus = employeestatus;
	}

	public String getBreakdownStatus() {
		return breakdownStatus;
	}

	public void setBreakdownStatus(String breakdownStatus) {
		this.breakdownStatus = breakdownStatus;
	}

	public String getAccidentStatus() {
		return accidentStatus;
	}

	public void setAccidentStatus(String accidentStatus) {
		this.accidentStatus = accidentStatus;
	}
	private static final long serialVersionUID = 1L;

	
	
	
	
	public String execute() {
		String result = "";
		return result;
	}
	//show dashboard
	public String insertCCC()
	{
		return "success";
	}
	@SuppressWarnings({ "unused" })
	// method for getting Data for display
	public String getData() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String[] cols = { "", "", "", "", "", "", "", "" };
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");
			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			int total = -1;
			Date fromDate = new Date();
			
			Date FromDate=new Date();
			/*FromDate = "2014-08-23" + " 00:00:00";
			tillDate =  sm.format(fromDate).toString() "2014-08-23"
					+ " 23:59:59";*/

			HttpServletRequest req = ServletActionContext.getRequest();
			HttpServletResponse res = ServletActionContext.getResponse();

			JSONObject js = new JSONObject();
			org.json.simple.JSONArray arr = new org.json.simple.JSONArray();
			String colName = cols[col];

			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			res.setContentType("application/json");
			res.setHeader("Cache-Control", "no-store");
			PrintWriter out = res.getWriter();

			js = dao.getData(total, request, cols[Integer.parseInt(sCol)],
					sdir, sm.format(FromDate)+" 00:00:00", sm.format(FromDate)+" 23:59:59");

			out.print(js);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// method calling for getting alert data
	public String getAlerts() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			JSONObject rs = new JSONObject();
			rs = dao.getData1(1, request, 
					 sm.format(FromDate) + " 00:00:00", sm.format(FromDate)
							+ " 23:59:59");
			
			out.print(rs);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
	}

	// get the for breakdown Type
	@SuppressWarnings("unchecked")
	public String getBreakdownType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String type = request.getParameter("type");

		if (type.equals("accident")) {
			String regionTypeAjaxString = "";
			List<String> l1 = dao.getAccidentType(); // method calling of DAO
			regionTypeAjaxString +="<option value=0>--Select--</option>";
			for (int i = 0; i < l1.size(); i++) {
				String vehiclearr[] = l1.get(i).toString().split("#");
				regionTypeAjaxString += "<option value=" + vehiclearr[0] + ">"
						+ vehiclearr[1] + "</option>";
			}

			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			String regionTypeAjaxString = "";
			List<String> l1 = dao.getBreakdownType();
			regionTypeAjaxString +="<option value=0>--Select--</option>";
			for (int i = 0; i < l1.size(); i++) {
				String vehiclearr[] = l1.get(i).toString().split("#");
				regionTypeAjaxString += "<option value=" + vehiclearr[0] + ">"
						+ vehiclearr[1] + "</option>";
			}

			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	// method for getting SOSData
	public String getSOSData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String vehicle_no = request.getParameter("vehical");
		JSONObject js = new JSONObject();

		try {
			js = dao.getSOSData(vehicle_no);

			PrintWriter out;
			out = response.getWriter();
			out.print(js);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getRevenueData() {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		JSONObject js = new JSONObject();

		try {
			String[] cols = { "", "", "" };
			JSONObject rs = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			js = dao.getRevenueData();

		
			System.out.println("result in action from revenue ..." +js);
			out.print(js);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	public String getRevenueData_depot() {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String parent_id = request.getParameter("parent_id");
		System.out.println("parent_id" +parent_id);
		JSONObject js = new JSONObject();

		try {
			String[] cols = { "", "", "" };
			JSONObject rs = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			js = dao.getRevenueData_depot(parent_id);

		
			System.out.println("result in action from revenue for depot ..." +js);
			out.print(js);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getRevenueDataByWaybill() {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String parent_id = request.getParameter("parent_id");
		System.out.println("parent_id" +parent_id);
		JSONObject js = new JSONObject();

		try {
			String[] cols = { "", "", "" };
			JSONObject rs = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			js = dao.getRevenueDataByWayBill("Depot 11");

		
			System.out.println("result in action from revenue for WayBill ..." +js);
			out.print(js);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	public String getTicketDetail() {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		JSONObject js = new JSONObject();

		try {
			String[] cols = {"", "", "", "" };
			JSONObject rs = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			js = dao.getTicketData();

		
			System.out.println("result in action from revenue for WayBill ..." +js);
			out.print(js);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	public String getTicketByData_depot() {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String parent_id = request.getParameter("parent_id");
		System.out.println("parent_id" +parent_id);
		JSONObject js = new JSONObject();

		try {
			String[] cols = { "","", "", "" };
			JSONObject rs = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			js = dao.getTicketByData_depot(parent_id);

		
			System.out.println("result in action from revenue for depot ..." +js);
			out.print(js);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getTicketDataByWaybill() {
		@SuppressWarnings("unused")
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String parent_id = request.getParameter("parent_id");
		System.out.println("parent_id" +parent_id);
		JSONObject js = new JSONObject();

		try {
			String[] cols = { "","", "", "" };
			JSONObject rs = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			js = dao.getTicketDataByWayBill("Depot 11");

		
			System.out.println("result in action from revenue for WayBill ..." +js);
			out.print(js);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}
	
	
	// method for save date into the DB
	public String insertData() {
		
		HttpServletRequest request = ServletActionContext.getRequest();

		String call_id = request.getParameter("call_id");
		String device_id = request.getParameter("device_id");
		String call_time = request.getParameter("call_time");
		String driver_name = request.getParameter("driver_name");
		String conductor_name = request.getParameter("conductor_name");
		String vehicle_no = request.getParameter("vehicle_no");
		String route_no = request.getParameter("route_no");
		String depot_name = request.getParameter("depot_name");
		String Schedule_no = request.getParameter("schedule_no");
		String call_type = request.getParameter("call_type");
		String description = request.getParameter("notes");
		String accident_type = request.getParameter("depotlist");
		String breakdown_type = request.getParameter("vehiclelist");
		User user = (User) request.getSession().getAttribute("user");
		String username=user.getFirstname();
		Integer userid=user.getUserId();
		if (call_type.equals("1")) {
			
			 dao.insertAccidentDetail(
					userid,username,call_id,
					device_id, call_time, driver_name, conductor_name,
					vehicle_no, route_no, depot_name, Schedule_no, call_type,
					description, accident_type, breakdown_type); // method call
																	// of DAO
			
		} else {
			
			 dao.saveBreakdownDetail(
					 userid,username, call_id,
					device_id, call_time, driver_name, conductor_name,
					vehicle_no, route_no, depot_name, Schedule_no, call_type,
					description, accident_type, breakdown_type); // Method call
																	// for
																	// breakdown
																	// of DAO
			
		}
		addActionMessage("Call Details Updated SuccessFully");
		return "success";
	}
	
	public String getSosattendedUnattended()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Date FromDate = new Date();
		PrintWriter out = null;
		String fromDate=request.getParameter("fromDate")!=null?request.getParameter("fromDate"):"";
		String toDate=request.getParameter("toDate")!=null?request.getParameter("toDate"):"";
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = dao.getDataForAttendedUnattendedCall(1, request, fromDate + " 00:00:00",
					toDate + " 23:59:59" );
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}

	public String getSosAttendedDetails()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Date FromDate = new Date();
		PrintWriter out = null;
		String fromDate=request.getParameter("fromDate")!=null?request.getParameter("fromDate"):"";
		String toDate=request.getParameter("toDate")!=null?request.getParameter("toDate"):"";
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = dao.getDataForAttendedCallDetails(1, request, fromDate + " 00:00:00",
					toDate + " 23:59:59" );
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
	
	public String getSosUnAttendedDetails()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Date FromDate = new Date();
		PrintWriter out = null;
		String fromDate=request.getParameter("fromDate")!=null?request.getParameter("fromDate"):"";
		String toDate=request.getParameter("toDate")!=null?request.getParameter("toDate"):"";
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = dao.getDataForUnAttendedCallDetails(1, request, fromDate + " 00:00:00",
					toDate + " 23:59:59" );
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
	//get vechicle total count data
	public String getVehicleCount() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		cccDaoData dao = new cccDaoData();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			//result =dao.getDataForChartPieAccident();
			result =dao.getDataForChartPieVehicle();
			
		} catch (Exception ex) {

		}
		//System.out.println("result................."+result);
		out.print(result);

		return null;
	}
	//end
	//getAccidetpiechart count
	
			public String getAccidetpiechart() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					resp.setContentType("application/json");
					out = resp.getWriter();

					//result =dao.getDataForChartPieAccident();
					result =dao.getDataForChartPieAccident();

										
				} catch (Exception ex) {

				}
				//System.out.println("result................."+result);
				out.print(result);

				return null;
			}

			//end
			//getBreakdownpiechart count
			
		
			
			public String getBreakdownpiechart() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getDataForChartPieBreakdown();
				} catch (Exception ex) {

				}
				//System.out.println("result................."+result);
				out.print(result);

				return null;
			}

			//end
			
//Driver cum conductor  count
			
			public String getDriverConductorCountpiechart() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getDataForChartPieConductorAndDriver();
				} catch (Exception ex) {

				}
				System.out.println("Employee................."+result);
				out.print(result);

				return null;
			}

			//end
			
			
			//accident datatable data start
			public String getAccidentDatapiechart() {
				String status=getDepotid();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getDataAccident(1, request,status);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//accident datatable data end
			
			//accident datatable data start
			public String getBreakdownDatapiechart() {
				String status=getDepotid();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getDataBreakdown(1, request,status);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//accident datatable data end
			
			//vehicle depotwise count
			
			public String getVehicleDepotwiseCount() {
				String status=getBreakdownStatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getVehicleDepotWiseCount(1, request);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//end
			//depotwise data
			public String getVehicleDepotwiseData() {
				String status=getBreakdownStatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				String depotid=getDepotid();
				System.out.println("depotid>>"+depotid);
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getVehicleDepotWiseData(1, request,depotid);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end
			
			//Employee datatable data start
			public String getEmployeeDatapiechart() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getDataEmployeeDepotwise(1, request,status);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//Employee datatable data end
			//get getEmployeeDataDepot
			public String getEmployeeDataDepot() {
				String status=getEmployeestatus();
				String id=getDepotid();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getDataEmployee(request,status,id);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//end
			
			//VTU count data start
			public String getVTUCountpiechart() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getDataForChartPieVTU();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//VTU count end
			
			//ETM count data start
			public String getETMCountpiechart() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				VtsDataDAO vtsdoa=VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					
					int faultcount=vtsdoa.getTotalETMFaultCount();
					result=dao.getDataForChartPieETM(faultcount);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//ETM count endgetDataETM
			
			//start data vtu
			
			public String getVTUData() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getDataVTU(1, request);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//end data vtu
			
			//start data etm
			
			public String getETMData() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getDataETM(1, request);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			
			//get ETM Faulty data
			public String getETMFaultyData() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getETMFaultyData();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end data etm
			//LC count 
			public String getLCCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getLCCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//LC count end
			
			//lc data od depotwise
			public String getLCDepotdata() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				String depotid=getDepotid();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getLCWaybillsOfDepot(depotid);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end
			//total ticket count
			public String getTotalTicketCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTotalTicketCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end total ticket count
			//depotwise ticket data
			public String getTotalTicketData() {
				String depotid=getDepotid();
				
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTicketWaybillsOfDepot(depotid);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//depotwise ticket data end
			//total pass count
			public String getTotalPassCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTotalPassCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//depotwise pass data
			public String getTotalPassData() {
				String depotid=getDepotid();
				
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTicketPassWaybillsOfDepot(depotid);
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			//depotwise pass data end
			
			//total complaint  count
			public String getComplaintCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTotalComplaintCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end total ticket count
			
			//total trip   count
			public String getTripCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTotalTripCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end total trip count
			
			//total trip   count
			public String getScheduleCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				//cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTotalScheduleCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end total trip count
			//total Route  count
			public String getRouteCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				//cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTotalRouteCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end total Route count
			
			//total Route  count
			public String getBusSkipCount() {
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				cccDaoData dao = new cccDaoData();
				JSONObject result = new JSONObject();
				try {
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					result=dao.getTotalBusSkipCount();
				} catch (Exception ex) {

				}
				//System.out.println("result.accident................"+result);
				out.print(result);

				return null;
			}
			
			//end total Route count
			
			//get Total trip count pichart data
			public String getTripCounttpiechart() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getTotalTripDataCount();
				} catch (Exception ex) {

				}
				System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			
			public String  getStatusWiseDataofTrip()
			{
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				try{
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					if (tripStatus.equals("Partial")){
						//System.out.println("partial result"+result);
					result =dao.getTotalTripPartialData();
					System.out.println("partial result"+result);
					}
					else if (tripStatus.equals("DeviatedTrip")){
						result =dao.getTotalTripDeviatedData();
						}
					else if (tripStatus.equals("CompletedTrip")){
						result =dao.getTotalTripCompleteData();
						}
					
					else if (tripStatus.equals("MissedTrip")){
						result =dao.getTotalTripMissedData();
						}
					else if (tripStatus.equals("DelayTrip")){
						result =dao.getTotalTripDelayData();
						}
					//System.out.println("compkleted result"+result);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				out.print(result);
				return null;
			}
			//get total piechart data end
			
			//get total schedule trip count data start 
			
			public String getScheduleTripCounttpiechart() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getTotalScheduleTripDataCount();
				} catch (Exception ex) {

				}
				System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			
			//get total scheduletrip count pie chart dat end 
			
			//get schedule wise data
			public String  getStatusWiseDataofScheduleTrip()
			{
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				try{
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					System.out.println("starus:"+getTripStatus());
					if (tripStatus.equals("PartialSchedule")){
						System.out.println("PartialSchedule");
					result =dao.getTotalScheduleTripPartialData();
					System.out.println("partial result"+result);
					}
					else if (tripStatus.equals("DeviatedSchedule")){
						System.out.println("DeviatedSchedule");
						result =dao.getTotalScheduleTripDeviatedData();
						}
					else if (tripStatus.equals("CompletedSchedule")){
						System.out.println("CompletedSchedule");
						result =dao.getTotalScheduleTripCompleteData();
						}
					//System.out.println("compkleted result"+result);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				out.print(result);
				return null;
			}
			//end schedulewise data
			//get total crew count
			public String getCrewAllocationCountpiechart() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getTotalCrewAlloactionpDataCount();
				} catch (Exception ex) {

				}
				//System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			//end crew count
			
			//status wise crew allocation start
			public String  getStatusWiseDataofCrewAlloaction()
			{
				String status=getEmployeestatus();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				try{
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					out = response.getWriter();
					System.out.println("starus:"+getTripStatus());
					if (tripStatus.equals("Close")){
						System.out.println("Online");
					result =dao.getCrewAlloactionCloseData();
					System.out.println("Online result"+result);
					}
					else if (tripStatus.equals("New")){
						System.out.println("New");
						result =dao.getCrewAlloactionNewData();
						}
					else if (tripStatus.equals("Online")){
						System.out.println("Online");
						result =dao.getCrewAlloactionOnlineData();
						}
					//System.out.println("compkleted result"+result);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				out.print(result);
				return null;
			}
			//status wise crew allocation end
			//get total onlineoffline depot 
			public String getOnlineOfflineDepotCounttpiechart() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getOnlineOfflineDepotCount();
				} catch (Exception ex) {

				}
				//System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			//end crew count
			//get Online data start
			public String getOnlineDepotData() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getOnlineDepotData();
				} catch (Exception ex) {

				}
				//System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			//get Online data end
			
			//get Offline data start
			public String getOfflineDepotData() {
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getOfflineDepotData();
				} catch (Exception ex) {

				}
				//System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			//get Offline data end
			
			//get depot wide manual ticket data start
			public String getManualTicketDepotData()
			{
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getManualTicketData();
				} catch (Exception ex) {

				}
				//System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			//end depotwise manual ticket data   
			
			//manual ticket depot wise data start
			
			public String getManualTicketDepotwiseData()
			{
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				PrintWriter out = null;
				String status=getDepotid();
				VtsDataDAO dao = VtsDataDAO.getInstance();
				JSONObject result = new JSONObject();
				Date FromDate = new Date();
				try {
					SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//					resp.setContentType("application/json");
					out = resp.getWriter();
					result =dao.getManualTicketDepotwiseData(status);
				} catch (Exception ex) {

				}
				//System.out.println("getTripCounttpiechart................."+result);
				out.print(result);

				return null;
			}
			//manual ticket depotwise data end
			
			
}