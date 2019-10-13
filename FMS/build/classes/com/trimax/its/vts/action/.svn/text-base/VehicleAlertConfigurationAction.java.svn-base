package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.dao.ticketbag.dao.TicketBagMasterDao;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.vts.dao.VehicleAlertConfigDAO;
import com.trimax.its.vts.model.VehicleAlertConfig;

public class VehicleAlertConfigurationAction {
	private String msg;
	private String requestType;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private VehicleAlertConfig vehiclealertconfig;

	public VehicleAlertConfig getVehiclealertconfig() {
		return vehiclealertconfig;
	}

	public void setVehiclealertconfig(VehicleAlertConfig vehiclealertconfig) {
		this.vehiclealertconfig = vehiclealertconfig;
	}

	public String viewVehicleAlertConfiguration() {
		return "success";
	}

	/**
	 * showVehicleAlertConfigData
	 * 
	 * @return
	 * @throws IOException
	 */
	public String showVehicleAlertConfigData() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VehicleAlertConfigDAO vehiclealertconfigdao = new VehicleAlertConfigDAO();
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		try {
			String[] cols = { "", "ALERT_DESC", "TIME_DURATION" };
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
			result = vehiclealertconfigdao.getTotalVehicleAlertConfigData(
					total, request, cols[Integer.parseInt(sCol)], sdir);
			
			out.print(result);
		} catch (Exception e) {
			System.out
					.println("\n \t Exception in prepare : " + e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("\n \t In ViewWayBills prepare Finally.....");
		}

		return null;
	}

	public String getAlertDescAction() {
		VehicleAlertConfigDAO vehiclealertconfigdao = new VehicleAlertConfigDAO();

		// serviceTypeIds=rmDao.getServiceId();
		/*
		 * List<String> l1 = tickinvdaodao.getOrgTypeId();
		 * 
		 * List<String> l2 = tickinvdaodao.getOrgTypeName();
		 */List<String> l1 = vehiclealertconfigdao.getVtsAlertMasterId();

		List<String> l2 = vehiclealertconfigdao.getVtsAlertDescriptionName();

		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='orgType" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
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

	public String getPacketCodeAndMisBytes() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		// System.out.println("hi 2"+parentid);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String alertDescId = request.getParameter("alertDescid").toString();

			System.out.println("Alert DEscription Id  .." + alertDescId);
			VehicleAlertConfigDAO vehiclealertconfigdao = new VehicleAlertConfigDAO();
			String alertdescId = vehiclealertconfigdao
					.getPacketCodeMisByteData(alertDescId);
			String alertDescName = vehiclealertconfigdao
					.getAlertDecriptionName(alertDescId);
			out.print(alertdescId + "@" + alertDescName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;

	}

	public String saveAlertConfig() {
		try {
			System.out.println("i am inside saveAlertConfig"
					+ vehiclealertconfig.getVtsalertmaster().getVts_id());
		} catch (Exception e) {
			e.printStackTrace();
		}

		int insertid = 0;
		VehicleAlertConfigDAO vehiclealertconfigdao = new VehicleAlertConfigDAO();
		int duplicateinsertcount = vehiclealertconfigdao
				.checkDuplicateInsertAlertConfig(vehiclealertconfig);
		if (duplicateinsertcount == 0) {
			insertid = vehiclealertconfigdao
					.saveAlertConfigDetails(vehiclealertconfig);
			System.out.println("ticket bag alert decsription is,..."
					+ duplicateinsertcount);
			msg = "Vehicle Alert Config  Id " + insertid
					+ " Inserted Succesfully";
			return "success";
		} else {
			System.out.println("ticket bag alert decsription is,..."
					+ duplicateinsertcount);
			msg = "Vehicle Alert Config   Id Already Exists";
			return "input";
		}

	}

	public String createVehicleAlertConfiguration() {

		return "success";
	}

	public String editVehicleAlertConfiguration() {
		HttpServletRequest request = ServletActionContext.getRequest();
		VehicleAlertConfigDAO vehiclealertconfigdao = new VehicleAlertConfigDAO();
		System.out.println("vts id is..."
				+ Integer.parseInt(request.getParameter("alertconfigid")));
		vehiclealertconfig = vehiclealertconfigdao
				.getVehicleAlertConfigDetails(Integer.parseInt(request
						.getParameter("alertconfigid")));

		return "success";
	}

	public String updateAlertConfigDetails() {
		int id = vehiclealertconfig.getVTS_ALERT_CONFIG_ID();
		VehicleAlertConfigDAO vehiclealertconfigdao = new VehicleAlertConfigDAO();
		// int
		// duplicateupdatecount=vehiclealertconfigdao.checkDuplicateUpdateAlertConfig(vehiclealertconfig);
		// if(duplicateupdatecount==0)
		// {
		vehiclealertconfigdao.saveEditedTicketBagDetails(requestType, id,
				vehiclealertconfig);

		msg = "Vehicle Alert Config  Id " + id + " Updated Succesfully";
		return "success";
		// }
		// else
		// {
		// msg = "Vehicle Alert Config Already Exists";
		// return "input";
		// }

	}

	public String deleteVehicleAlertConfiguration() {
		HttpServletRequest request = ServletActionContext.getRequest();
		VehicleAlertConfigDAO vehiclealertconfigdao = new VehicleAlertConfigDAO();
		int alertconfigid = Integer.parseInt(request
				.getParameter("alertconfigid"));

		vehiclealertconfigdao.deleteVehicleAlertConfig(Integer.parseInt(request
				.getParameter("alertconfigid")));
		msg = "Vehicle Alert Config  Id " + alertconfigid
				+ " Deleted successfully";

		return "success";
	}
}
