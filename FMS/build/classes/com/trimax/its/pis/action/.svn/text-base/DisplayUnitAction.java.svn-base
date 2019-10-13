package com.trimax.its.pis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.pis.dao.PisDisplayUnitDao;
import com.trimax.its.pis.model.DisplayUnitModel;
import com.trimax.its.pis.dao.PisDisplayMappingDao;
import com.trimax.its.pis.dao.PisDisplayUnitDao;
import com.trimax.its.pis.dao.PisScrollMessageDao;
import com.trimax.its.route.model.Platform;

public class DisplayUnitAction extends ActionSupport {
	private DisplayUnitModel displayUnitModel;
	private String msg;
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DisplayUnitModel getDisplayUnitModel() {
		return displayUnitModel;
	}

	public void setDisplayUnitModel(DisplayUnitModel displayUnitModel) {
		this.displayUnitModel = displayUnitModel;
	}

	public String viewDisplayUnit() {

		return "success";
	}
	private String requestType;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getDisplayUnitList() throws IOException {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PisDisplayUnitDao displayUnitDao = new PisDisplayUnitDao();

		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {

			String[] cols = { "", "pis_display_unit_id", "bus_station_id",
					"floor_id", "bay_id", "platform_id","template_name" };
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			// sStart : 0 : sAmount : 10 : sCol : 1 : sdir : asc

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
			// total =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			result = displayUnitDao.getPisDisplayUnitData(total, request,
					cols[Integer.parseInt(sCol)], sdir);

			
			out.print(result);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
		}

		return null;

	}
	public String createDisplayUnit() {
		return "success";
	}
	public String saveDisplayUnitData() {
		
		int busstationid = displayUnitModel.getDisplay_bus_station_id()
				.getOrg_chart_id();
		int floorid = displayUnitModel.getDisplay_floor().getFloor_id();
		int bayid = displayUnitModel.getDisplay_bay().getBay_id();
		String displayUnitName=displayUnitModel.getPis_display_unit_name();
		String[] platform = displayUnitModel.getPlatform();
				
		
		if (("".equals(displayUnitName)) || (displayUnitName == null)
				|| (displayUnitName.length() == 0)) {

			addFieldError("display_unitname", "Display Unit Name is Manadatory");
			return "input";

		}

		
		if (busstationid == 0) {
			

			addFieldError("bus_station_id",
					"Display Unit Bus Station Id is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		if (floorid == 0) {

			addFieldError("floorName", "Display Unit Floor No. is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (bayid == 0) {

			addFieldError("bayName", "Display Unit Bay No. is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (platform ==null) {

			addFieldError("platformNo", "Display Unit Platform No. is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
			PisDisplayUnitDao adMgmtDao = new PisDisplayUnitDao();

		int duplicateinsertcount = 0;
		for (int i = 0; i < platform.length; i++) {
			Platform platf = new Platform();
			int platformid = Integer.parseInt(platform[i].trim());
			platf.setPlatform_id(platformid);
			displayUnitModel.setDisplay_platform(platf);
		if (displayUnitName != null) {
			
			
			duplicateinsertcount = adMgmtDao.checkForDuplicateDisplayUnitInsert(displayUnitModel);
			
		}
		if (duplicateinsertcount == 0) {
			int insertid = adMgmtDao
					.insertPisDisplayUnitDetails(displayUnitModel);
			msg = "Display Unit Id " + insertid + " Inserted Succesfully";

		} else {
			msg = "Display Unit   already exists";
			return "input";
		
		}
		}
		return "success";
	}

	public String saveUpdatedDisplayUnit() { 
		
		int busstationid = displayUnitModel.getDisplay_bus_station_id()
				.getOrg_chart_id();
		int floorid = displayUnitModel.getDisplay_floor().getFloor_id();
		int bayid = displayUnitModel.getDisplay_bay().getBay_id();
		String displayUnitName=displayUnitModel.getPis_display_unit_name();
		int platformid=displayUnitModel.getDisplay_platform().getPlatform_id();
		if (("".equals(displayUnitName)) || (displayUnitName == null)
				|| (displayUnitName.length() == 0)) {
 
			addFieldError("display_unitname", "Display Unit Name is Manadatory");
			return "input";

		}
		if (busstationid == 0) {
		

			addFieldError("bus_station_id",
					"Display Unit Bus Station Id is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		if (floorid == 0) {

			addFieldError("floorName", "Display Unit Floor No. is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (bayid == 0) {

			addFieldError("bayName", "Display Unit Bay No. is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		if (platformid == 0) {

			addFieldError("platformNo",
					"Display Unit PlatForm No. is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
          
		int id = displayUnitModel.getPis_display_unit_id();
		PisDisplayUnitDao adMgmtDao = new PisDisplayUnitDao();
		int duplicatecount = 0;
		if (displayUnitName !=null) {
			duplicatecount = adMgmtDao.checkForDuplicateDisplayUnit(
					displayUnitName,platformid,id);
			
		}
		if (duplicatecount == 0) {
			adMgmtDao.saveEditedDisplayUnitDetails(requestType, id,
				displayUnitModel);

		msg = "Display Unit  Id " + id + " Updated Succesfully";
		}
		 else {
				msg = "Display Unit already exists";
				return "input";
			}

		return "success";
	}


	public String editDisplayUnit() {
		HttpServletRequest request = ServletActionContext.getRequest();
	
		PisDisplayUnitDao adMgmtDao = new PisDisplayUnitDao();
		displayUnitModel = adMgmtDao.getDisplayUnitDetails(Integer
				.parseInt(request.getParameter("id")));
		
		return "success";
	}
	public String deleteDisplayUnit()
    {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	PisDisplayUnitDao adMgmtDao = new PisDisplayUnitDao();
		int  displayunitid= Integer.parseInt(request.getParameter("displayunitid"));

		adMgmtDao.deleteDisplayUnit(Integer.parseInt(request
				.getParameter("displayunitid")));
		msg = "Display Unit  Id " + displayunitid + " Deleted successfully";

    	return "success";
    }
	
	/**
	 * Find all Bus Stations
	 * 
	 * @return
	 */
	public String findFloorNameAfterSubmit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int orgtypeid = Integer.parseInt(request.getParameter("floorid"));
		PisDisplayUnitDao adMgmtDao = new PisDisplayUnitDao();
		
		// serviceTypeIds=rmDao.getServiceId();
		
	    String floorName="";
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			floorName=adMgmtDao.getFloorName(orgtypeid);
			out.print(floorName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;

	}
	/**
	 * Find all Bus Stations
	 * 
	 * @return
	 */
	public String findBayNameAfterSubmit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int bayid = Integer.parseInt(request.getParameter("bayid"));
		PisDisplayUnitDao adMgmtDao = new PisDisplayUnitDao();
		
		// serviceTypeIds=rmDao.getServiceId();
		
	    String bayName="";
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			bayName=adMgmtDao.getBayName(bayid);
			out.print(bayName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;

	}
	
	/**
	 * Find all Bus Stations
	 * 
	 * @return
	 */
	public String findPlatformNameAfterSubmit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int platformid = Integer.parseInt(request.getParameter("platformid"));
		PisDisplayUnitDao adMgmtDao = new PisDisplayUnitDao();
		
		// serviceTypeIds=rmDao.getServiceId();
		
	    String platformName="";
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			platformName=adMgmtDao.getPlatformName(platformid);
			out.print(platformName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;

	}
	 
}
