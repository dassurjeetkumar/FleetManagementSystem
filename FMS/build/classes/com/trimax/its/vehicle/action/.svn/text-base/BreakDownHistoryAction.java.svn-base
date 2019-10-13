package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.dao.ticketbag.dao.TicketBagMasterDao;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.toll.dao.TollfeeDao;
import com.trimax.its.toll.model.TollBusStop;
import com.trimax.its.toll.model.Tollfee;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.vehicle.dao.BreakDownHistoryDao;
import com.trimax.its.vehicle.model.BreakDownHistoryTypeBreakdown;
import com.trimax.its.vehicle.model.BreakDownVehicle;
import com.trimax.its.vehicle.model.BreakDownHistory;
import com.trimax.its.vehicle.model.BreakDownTypeDetails;
import com.trimax.its.vehicle.model.Vehicle;

public class BreakDownHistoryAction extends ActionSupport {
	private Tollfee tollfee ;
	public Tollfee getTollfee() {
		return tollfee;
	}

	public void setTollfee(Tollfee tollfee){
		this.tollfee = tollfee;
	}

	private BreakDownHistory breakdownhistory;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	private String requestType;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;

	public BreakDownHistory getBreakdownhistory() {
		return breakdownhistory;
	}

	public void setBreakdownhistory(BreakDownHistory breakdownhistory) {
		this.breakdownhistory = breakdownhistory;
	}

	public String viewBreakDownHistory() {
		return "success";
	}

	public String convertDateToSring(Date date) {

		try {
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

			// Get the date today using Calendar object.
			Date today = date;
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			String reportDate = df.format(today);

			// Print what date is today!
			//System.out.println("\n \t Report Date: " + reportDate);
			return reportDate;

		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	@SkipValidation
	public String findBreakDownId()
	{
		//System.out.println("in get break down type id...");
		TollfeeDao tollfeedao = new TollfeeDao();
		BreakDownHistoryDao breakdownhistorydao = new BreakDownHistoryDao();
		HttpServletRequest request=ServletActionContext.getRequest();
	    String idBusStopName = request.getParameter("id");
	   // System.out.println("text entered is.."+idBusStopName);

	     List<BreakDownTypeDetails> breakdownlist = breakdownhistorydao.getBreakDownTypeDropList(idBusStopName);
		
		
		//	System.out.println("Size --->"+breakdownlist.size());
			
			List<BreakDownHistoryTypeBreakdown> breakdownvehiclelist = new ArrayList<BreakDownHistoryTypeBreakdown>();
			for(BreakDownTypeDetails breakdowntype : breakdownlist){
				TollBusStop bus = new TollBusStop();
				BreakDownHistoryTypeBreakdown breakdownhistorytype=new BreakDownHistoryTypeBreakdown();
				
				breakdownhistorytype.setBreakdowntypeId(breakdowntype.getBreakdown_type_Id());
				breakdownhistorytype.setBreakdownname(breakdowntype.getBreakdown_name());
				breakdownhistorytype.setValue(breakdowntype.getBreakdown_name());
				
				breakdownvehiclelist.add(breakdownhistorytype);
				
			}

		 HttpServletResponse response=ServletActionContext.getResponse();
		 
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Vehicle Id Size------>"+breakdownvehiclelist.size());
			  out.print(new JSONArray(breakdownvehiclelist));
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}

		return null;
	}
	@SkipValidation
    public String findVehicleId()
    {
    	//System.out.println("in get ");
		TollfeeDao tollfeedao = new TollfeeDao();
		BreakDownHistoryDao breakdownhistorydao = new BreakDownHistoryDao();
		HttpServletRequest request=ServletActionContext.getRequest();
	    String idBusStopName = request.getParameter("id");
	   // System.out.println("text entered is.."+idBusStopName);

	     List<Vehicle> vehiclelist = breakdownhistorydao.getVehicleDropList(idBusStopName);
		 List<BusStops> list = tollfeedao.getBusStopDropList(idBusStopName);
		
		//	System.out.println("Size --->"+list.size());
			
			List<BreakDownVehicle> breakdownvehiclelist = new ArrayList<BreakDownVehicle>();
			for(Vehicle vehicle : vehiclelist){
				TollBusStop bus = new TollBusStop();
				BreakDownVehicle breakdownvehicle=new BreakDownVehicle();
				
				breakdownvehicle.setVehicleId(vehicle.getVehicleId());
				breakdownvehicle.setVehicleRegistrationNumber(vehicle.getVehicleRegistrationNumber());
				breakdownvehicle.setValue(vehicle.getVehicleRegistrationNumber());
				
				breakdownvehiclelist.add(breakdownvehicle);
							}

		 HttpServletResponse response=ServletActionContext.getResponse();
		
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("Vehicle Id Size------>"+breakdownvehiclelist.size());
			  out.print(new JSONArray(breakdownvehiclelist));
		} catch (IOException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
     

		return null;

    	
    	
    }
	public String editBreakDownHistoryDetails() {
		
		//System.out.println("date is..." + breakdownhistory.getDate());
				CommonValidation cm = new CommonValidation();
		String date = breakdownhistory.getDate();
		int vehicleid = breakdownhistory.getVehicle().getVehicleId();
		//System.out.println("In Save break down..vehicleid"+vehicleid+"......."+breakdownhistory.getBreakDownTypeDetails().getBreakdown_name());
		
       int breakdowntypeId=breakdownhistory.getBreakDownTypeDetails().getBreakdown_type_Id();
		if (vehicleid == 0) {
			//System.out.println("hi if of old");

			addFieldError("vehicle", "BreakDownHistory VehicleId is Manadatory");
			return "error";
		}
		if (breakdowntypeId==0) {
			//System.out.println("In validation of type of breakdown history...."
			//		+ breakdowntypeId);
			addFieldError("breakdownType", "BreakdownTypeId is Manadatory");
			return "error";
		}

		if (("".equals(date)) || (date == null) || (date.length() == 0)) {
		//	System.out.println("hi if of old");

			addFieldError("status", "date  is Manadatory");

			return "error";

		}

		int breakdownid = breakdownhistory.getBreakdownId();
		//System.out.println("break down id is..."+breakdownid);
		BreakDownHistoryDao breakdownhistorydao = new BreakDownHistoryDao();
		
		int dupilcatebreakdownhistoryupdatecount = 0;
		dupilcatebreakdownhistoryupdatecount = breakdownhistorydao.checkForDuplicateBreakDownHistoryInsert(breakdownhistory);
		if (dupilcatebreakdownhistoryupdatecount == 0) {
			breakdownhistorydao.saveEditedBreakDownHistoryDetails(requestType,breakdownid, breakdownhistory);
			msg = "BreakDownHistory  Id " + breakdownid + " Updated Succesfully";
		} else {
			msg = "BreakDownHistory already Exists";
		    return "error";
		}
		
		

		return "success";
	}

	public String saveBreakDownHistoryDetails() {
		int vehicleid = breakdownhistory.getVehicle().getVehicleId();
		//System.out.println("In Save break down..vehicleid"+vehicleid+"......."+breakdownhistory.getBreakDownTypeDetails().getBreakdown_name());
		
       int breakdowntypeId=breakdownhistory.getBreakDownTypeDetails().getBreakdown_type_Id();
		if (vehicleid == 0) {
			//System.out.println("hi if of old");

			addFieldError("vehicle",
					"BreakDownHistory VehicleId is Manadatory");
			return "error";
		}
		if (breakdowntypeId==0) {
			//System.out.println("In validation of type of breakdown history...."
			//		+ breakdowntypeId);
			addFieldError("breakdownType", "BreakdownTypeId is Manadatory");
			return "error";
		}
		
		String date = breakdownhistory.getDate();

		if (("".equals(date)) || (date == null) || (date.length() == 0)) {
			//System.out.println("hi if of old");

			addFieldError("status", "date  is Manadatory");

			return "error";

		}

		int dupilcatebreakdownhistoryinsertcount = 0; 
		BreakDownHistoryDao breakdownhistorydao = new BreakDownHistoryDao();
	    dupilcatebreakdownhistoryinsertcount = breakdownhistorydao
			.checkForDuplicateBreakDownHistoryInsert(breakdownhistory);

		if (dupilcatebreakdownhistoryinsertcount == 0) {
			int breakdownhistoryid = breakdownhistorydao
					.insertBreakDownHistoryDetails(breakdownhistory);
			msg = "BreakDownHistory Id " + breakdownhistoryid
					+ " Inserted Succesfully";
		} else {
			msg = "BreakDownHistory already Exists";
		    return "error";
		}

		return "success";

	}

	public String createBreakDownHistory() {
		return "success";
	}

	public String editBreakDownHistory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		BreakDownHistoryDao breakdownhistorydao = new BreakDownHistoryDao();
		breakdownhistory = breakdownhistorydao.getBreakDownHistoryDetails(Integer.parseInt(request.getParameter("breakdownid")));
		Common cm=new Common();

		String date1 = cm.getDateToDate4(breakdownhistory.getDate());
		breakdownhistory.setDate(date1);
		return "success";
	}

	public String deleteBreakDownHistoryDetails() {
		HttpServletRequest request = ServletActionContext.getRequest();
		BreakDownHistoryDao breakdownhistorydao = new BreakDownHistoryDao();
		int denomtypeid = Integer.parseInt(request.getParameter("breakdownid"));
		System.out.println("denomtypeid"+denomtypeid);
		try{
		breakdownhistorydao.deleteBreakDownHistoryType(denomtypeid);
		
		}catch(Exception ex){
			ex.printStackTrace();
			
			//setDeletestaus("fail");
			return "input";
		}finally{
			//setDeletestaus("success");
			msg = "BreakDownHistory  Id " + denomtypeid + " Deleted successfully";
		}
		return "success";
	}

	public String viewBreakDownHistoryDetails() throws IOException {
		//System.out
		//		.println("\n \t Hello...........In .viewBreakDownHistoryDetails...... .............");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		
		BreakDownHistoryDao breakdownhistorydao = new BreakDownHistoryDao();
		
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {
			response.setHeader("Cache-Control", "no-store");

			int cnt = breakdownhistorydao.getTotalViewBreakDownHistoryRecords(viewdeletedrecord);
			// int cnt = orgdao.getTotalRecordsOfWayBill();
			System.out.println("Count------>" + cnt);
			String[] cols = { "", "breakdownId","date","veh.vehicleRegistrationNumber", "breakdowntype.breakdown_name" };
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			// sStart : 0 : sAmount : 10 : sCol : 1 : sdir : asc

			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

		//	System.out
		//			.println("\n \t***** In Ticket Bag Details......showTicketBag*********** : sStart : "
		//					+ sStart + " : sAmount : ");

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
			total = breakdownhistorydao.getTotalViewBreakDownHistoryRecords(viewdeletedrecord);
			result = breakdownhistorydao.viewBreakDownHistoryData(total,request, cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			/*
			 * result = orgdao.getData(total, request,
			 * cols[Integer.parseInt(sCol)], sdir);
			 */
			//System.out
		//			.println("REsult of datatable in prepare------>" + result);
			out.print(result);
		} catch (Exception e) {
		//	System.out
		//			.println("\n \t *************Exception in viewBreakDownHistoryDetails *********** "
		//					+ e.getMessage());
			e.printStackTrace();
		} finally {
			//System.out
			//		.println("\n \t *************In viewBreakDownHistoryDetails Finally***********");
		}

		return null;
	}
}
