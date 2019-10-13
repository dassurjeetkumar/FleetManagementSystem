package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.vehicle.dao.VehicleDockingReportDao;
import com.trimax.its.vts.dao.VtsDataDAO;

public class VehicleDockingReportAction extends ActionSupport {
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	private Map<Integer, String> divisionlist;
	private Map<Integer,String> dockingTypeList;	
	
	public Map<Integer, String> getDockingTypeList() {
		return dockingTypeList;
	}
	public void setDockingTypeList(Map<Integer, String> dockingTypeList) {
		this.dockingTypeList = dockingTypeList;
	}
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String getVehicleDockingReport(){
		VehicleDockingReportDao dao=new VehicleDockingReportDao();
		dockingTypeList=dao.getDockingType();
		divisionlist=dao.getDivisionName();
		return "success";
		
	}
	public String getDepotList() {	
	
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		/*String runningkm = req.getParameter("dockingtype");
		System.out.println("runningkm"+runningkm);*/
		VehicleDockingReportDao dao=new VehicleDockingReportDao();
		int parentId = Integer.parseInt(req.getParameter("division"));
		System.out.println("-------------"+parentId);
		List<String> l1 = dao.getDepotId(parentId);
		List<String> l2 = dao.getDepotName(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
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
	 public String getVehicleDockingDataForReport() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VehicleDockingReportDao dao=new VehicleDockingReportDao();
		
		int runningkm = Integer.parseInt(request.getParameter("runningkm"));
		System.out.println("dockingtype"+runningkm);
		int depot_id = Integer.parseInt(request.getParameter("depot_id"));
		System.out.println("depot_id"+depot_id);
		if(runningkm!=0){
			
		try {
			
			
			String[] dbcol = { "vehicleId","vehicleRegistrationNumber","org.org_name","org.org_name","fcExpiryDate","progressingKMs","scheduleKMs","vehicle.vehicle_type_name",
					"brand.brand_type_name","acAvailability","make.make_type_name","model.model_type_name","proceduredDate",
					"body.body_type_name","operationalDate","dockingPlanningDate","service.service_type_name","registrationDate","chassisNumber",
					"wheelsCount","category.vehicleCategoryName","underWarranty","status","isScarpped","scrappedDate"};
			JSONObject result = new JSONObject();
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

			String colName = dbcol[col];
			int total = -1;
			total = dao.getTotalRecordsForDocking(total,runningkm, depot_id, request,dbcol[Integer.parseInt(sCol)],sdir);
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getDataForDocking(total,runningkm, depot_id, request, dbcol[Integer.parseInt(sCol)], sdir);
			
			out.print(result);
		} catch (Exception ex) {
		//ex.printStackTrace();	
		} finally {

		}
		}else{
			System.out.println("--------------------------");
		addActionMessage("Please Select Docking Type");
		}
		return null;

	}
		
		//			HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
//		VtsDataDAO vvt = VtsDataDAO.getInstance();
//		Date FromDate = new Date();
//		PrintWriter out = null;
//		String runningkm = request.getParameter("runningkm");
//		System.out.println("runningkm"+runningkm);
//		int depot_id = Integer.parseInt(request.getParameter("depot_id"));
//		System.out.println("depot_id"+depot_id);
//
//		try {
//			/*Date dNow = new Date(); // Instantiate a Date object
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(dNow);
//			cal.add(Calendar.MINUTE, -5);
//			dNow = cal.getTime();
//*/
//			JSONObject result = new JSONObject();
//			out = response.getWriter();
//			result = vvt.getDataForDocking(runningkm, depot_id,request,"","");
//			//result = vvt.getDataForVehicleDockingReport(runningkm, depot_id);
//			out.print(result);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//
//		}
//
//		return null;
//
//	}	
}
