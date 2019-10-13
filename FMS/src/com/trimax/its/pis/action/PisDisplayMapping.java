package com.trimax.its.pis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.dao.ticketbag.dao.TicketBagMasterDao;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.pis.dao.PisDisplayMappingDao;
import com.trimax.its.pis.dao.PisDisplayUnitDao;
import com.trimax.its.pis.model.DisplayMappingModel;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.vts.dao.VehicleAlertConfigDAO;

public class PisDisplayMapping extends ActionSupport {
	private DisplayMappingModel displaymappingmodel;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DisplayMappingModel getDisplaymappingmodel() {
		return displaymappingmodel;
	}

	public void setDisplaymappingmodel(DisplayMappingModel displaymappingmodel) {
		this.displaymappingmodel = displaymappingmodel;
	}

	@SkipValidation
	public String viewPisDisplayMapping() {
		return "success";
	}

	@SkipValidation
	public String createPisDisplayMapping() {
		return "success";
	}

	private String requestType;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@SkipValidation
	public String deleteDisolayMappingData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		int displaymappid = Integer.parseInt(request
				.getParameter("displaymappid"));

		pisdisplaymappingdao.deleteDisplayMapping(Integer.parseInt(request
				.getParameter("displaymappid")));
		msg = "Display Mapping  Id " + displaymappid + " Deleted successfully";

		return "success";
	}

	public String editPisDisplayMappingDetails() {
		int id = displaymappingmodel.getPis_display_platform_id();
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		
		int duplicatecount = 0;
		duplicatecount = pisdisplaymappingdao.
		checkForDuplicateDisplayMapping(displaymappingmodel.getBus_station_id().getOrg_chart_id(),
				displaymappingmodel.getFloor().getFloor_id(),displaymappingmodel.getBay().getBay_id(),
				displaymappingmodel.getPlatform().getPlatform_id(),displaymappingmodel.getDisplay_unit().getPis_display_unit_id(),id);
		
		if(duplicatecount==0){
		pisdisplaymappingdao.saveEditedDisplayMappingDetails(requestType,id, displaymappingmodel);
			msg = "Display Mapping  Id " + id + " Updated Succesfully";
		} else {
			msg = "Display Mapping already Exists";
			return "input";
		}

 		return "success";
	}

	public String saveDisplayMappingDetails() {
		int dupilcateinsertcount = 0;
		
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		dupilcateinsertcount = pisdisplaymappingdao.checkForDuplicateDisplayMappingInsert(displaymappingmodel);
		
		if (dupilcateinsertcount == 0) {
		    int insertid = pisdisplaymappingdao.insertPisDisplayMappingDetails(displaymappingmodel);
			msg = "Display Mapping Id " + insertid + " Inserted Succesfully";
		} else {
			msg = "Display Mapping already Exists";
			return "input";
		}
		
		return "success";
	}

	@SkipValidation
	public String editPisDisplayMapping() {
		HttpServletRequest request = ServletActionContext.getRequest();
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		displaymappingmodel = pisdisplaymappingdao.getDisplayMappingDetails(Integer.parseInt(request.getParameter("id")));
		
		return "success";
	}

	@SkipValidation
	public String showDisplayMappingList() throws IOException {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {
			String[] cols = { "", "pis_display_platform_id", "display_unit_name",
					"org_name", "platform_name", "floor_name" };
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
			result = pisdisplaymappingdao.getPisDisplayMappingData(total,request, cols[Integer.parseInt(sCol)], sdir);		
			out.print(result);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
		}

		return null;
	}

	/**
	 * Find all Bus Stations
	 * 
	 * @return
	 */
	@SkipValidation
	public String getBusStation() {
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = pisdisplaymappingdao.getBusStationId();
		List<String> l2 = pisdisplaymappingdao.getBusStationName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='bus_station_id" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Getting all Floor Names
	 * 
	 * @return
	 */
	@SkipValidation
	public String getBusStationFloorName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int orgtypeid = Integer.parseInt(request.getParameter("busstationid"));
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = pisdisplaymappingdao.getFloorId(orgtypeid);
		List<String> l2 = pisdisplaymappingdao.getFloorName(orgtypeid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='floorid" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;

	}

	/**
	 * Getting all Bay Names
	 * 
	 * @return
	 */
	@SkipValidation
	public String getFloorBayName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int floorid = Integer.parseInt(request.getParameter("floorid"));
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = pisdisplaymappingdao.getBayId(floorid);
		List<String> l2 = pisdisplaymappingdao.getBayName(floorid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='bayName" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Getting all Bay Names
	 * 
	 * @return
	 */
	@SkipValidation
	public String getBayPlatFormName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int floorid = Integer.parseInt(request.getParameter("bayid"));
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = pisdisplaymappingdao.getPlatformId(floorid);
		List<String> l2 = pisdisplaymappingdao.getPlatformName(floorid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='platformName" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Getting all Bay Names
	 * 
	 * @return
	 */
	@SkipValidation
	public String getPlatFormDisplayUnitName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int platformid = Integer.parseInt(request.getParameter("platformid"));
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = pisdisplaymappingdao.getDisplayId(platformid);
		List<String> l2 = pisdisplaymappingdao.getDisplayName(platformid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='platformName" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
	
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	@SkipValidation
	public String findDisplayUnitNameAfterSubmit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int displayunitid = Integer.parseInt(request.getParameter("displayunitid"));
		
		PisDisplayMappingDao pisdisplaymappingdao = new PisDisplayMappingDao();
		// serviceTypeIds=rmDao.getServiceId();
		
	    String platformName="";
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			platformName=pisdisplaymappingdao.getDisplayUnitName(displayunitid);
			out.print(platformName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;

	}
	
	public void validate() {
		if(displaymappingmodel.getBus_station_id().getOrg_chart_id() == 0) {
			addFieldError("bus_station_id","Please Select Bus Station");
		}

		if(displaymappingmodel.getFloor().getFloor_id() == 0) {
		    addFieldError("floorName","Please Select Floor Name");
		}
		
		if(displaymappingmodel.getBay().getBay_id() == 0) {
			addFieldError("bayName", "Please Select Bay Name");
		}

		if(displaymappingmodel.getPlatform().getPlatform_id() == 0) {
			addFieldError("platformNo","Please Select PlatForm Name");
		}
		
		if(displaymappingmodel.getDisplay_unit().getPis_display_unit_id() == 0) {
			addFieldError("orgName","Please Select Display Unit Name");
		}
	}
}
