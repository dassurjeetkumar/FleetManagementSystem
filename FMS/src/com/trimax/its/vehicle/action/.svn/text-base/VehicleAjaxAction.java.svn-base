package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;

import com.trimax.its.vehicle.dao.BrandTypeDAO;
import com.trimax.its.vehicle.dao.VehicleDAO;

public class VehicleAjaxAction {
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	static VehicleDAO daoObject = new VehicleDAO();
	
	
	//static Long total = daoObject.getTotalRecords(); //commented before changing criteria query
	
	public String execute() throws IOException{
		

		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");
//		System.out.println("orgtypeid..........." + orgtypeid
//				+ "orgchartid................." + orgchartid);
//		String id = "!=0";
		String id="";
//		if (orgtypeid.equals("1")) {
//			id = "depot_id!=0";
//
//		}
//		if (orgtypeid.equals("3")) {
//
//			id = "depot_id in('" + orgchartid + "')";
//		}
//		if (orgtypeid.equals("2")) {
//
//			id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
//					+ orgchartid + "')";
//		}
		
		if(!orgtypeid.equals("0") && orgtypeid.equals("1")){
			
		}else if(!orgtypeid.equals("0") &&  orgtypeid.equals("2")){
			//id=" and ot.org_type_id="+orgtypeid;
			id = "and og.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
					+ orgchartid + "')";
			
		}
		else if(!orgtypeid.equals("0") &&  orgtypeid.equals("3")){
			id="and og.org_chart_id="+orgchartid;
			
		}
		
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
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//result = daoObject.getData(total, request,sCol,sdir,viewdeletedrecord);
			result = daoObject.getData1(request,sCol,sdir,id);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
	
public String getTransferVehicleHistoryList() throws IOException{
		

		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VehicleDAO vehicleDao = new VehicleDAO();
		Number cnt = vehicleDao.getTotalTransferVehicleHistoryRecords();
		System.out.println("Count------>"+cnt);
		String[] dbcol={"","transfer_vehicle_type","veh.vehicleId","fromorg.org_name","toorg.org_name",	"from_date","to_date","description","status"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		String search_term = request.getParameter("sSearch");
		
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
			total = vehicleDao.getTotalTransferVehicleHistoryRecords();
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
			result = vehicleDao.getTransferHistoryData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}


public String getFCRenewalRecordsList() throws IOException{
	

	try{
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	VehicleDAO vehicleDao = new VehicleDAO();
	Number cnt = vehicleDao.getTotalFCRenewalRecords();
	System.out.println("Count------>"+cnt);
	String[] dbcol={"","vehicle_fc_renewal_id","v.vehicleId","fc_expiry_date","fc_renewal_date"};
	JSONObject result = new JSONObject();
	int amount = 10;
	int start = 0;
	int col = 0;
	String dir = "asc";
	String sStart = request.getParameter("iDisplayStart");
	String sAmount = request.getParameter("iDisplayLength");
	String sCol = request.getParameter("iSortCol_0");
	String sdir = request.getParameter("sSortDir_0");
	String search_term = request.getParameter("sSearch");
	
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
		total = vehicleDao.getTotalFCRenewalRecords();
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
		result = vehicleDao.getVehicleFCRenewalData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
		out.print(result);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return null;
}



//public String getFuelRecordsList() throws IOException{
//	
//
//	try{
//	HttpServletRequest request = ServletActionContext.getRequest();
//	HttpServletResponse response = ServletActionContext.getResponse();
//	VehicleDAO vehicleDao = new VehicleDAO();
//	Number cnt = vehicleDao.getTotalFuelRecords();
//	System.out.println("Count------>"+cnt);
//	String[] dbcol={"fuel_id","v.vehicleId","kms","litres","date","fuel_type","u.user_name"};
//	JSONObject result = new JSONObject();
//	int amount = 10;
//	int start = 0;
//	int col = 0;
//	String dir = "asc";
//	String sStart = request.getParameter("iDisplayStart");
//	String sAmount = request.getParameter("iDisplayLength");
//	String sCol = request.getParameter("iSortCol_0");
//	String sdir = request.getParameter("sSortDir_0");
//	String search_term = request.getParameter("sSearch");
//	
//	if (sStart != null) {
//		start = Integer.parseInt(sStart);
//		if (start < 0) {
//		start = 0;
//		}
//		}
//		if (sAmount != null) {
//		amount = Integer.parseInt(sAmount);
//		if (amount < 10 || amount > 50) {
//		amount = 10;
//		}
//		}
//		if (sCol != null) {
//		col = Integer.parseInt(sCol);
//		if (col < 0 || col > 5)
//		col = 0;
//		}
//		if (sdir != null) {
//		if (!sdir.equals("asc"))
//		dir = "desc";
//		}
//
//		int total = -1;
//		total = vehicleDao.getTotalFuelRecords();
//		AMOUNT = amount;
//		SEARCH_TERM = request.getParameter("sSearch");
//		DIR = dir;
//		START = start;
//		
//		response.setContentType("application/json");
//		response.setHeader("Cache-Control", "no-store");
//		PrintWriter out = response.getWriter();
//		System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
//		result = vehicleDao.getFuelHistoryData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
//		out.print(result);
//	}
//	catch(Exception e)
//	{
//	e.printStackTrace();
//	}
//	return null;
//}

public String getServiceType()
{
	BrandTypeDAO dao=new BrandTypeDAO();
	List<String> l1 = dao.getServiceId();
	List<String> l2 = dao.getServiceName();
	String regionTypeAjaxString = "";
	regionTypeAjaxString += "<option value='0'>---Select---</option>";
	for (int i = 0; i < l1.size(); i++) {
		regionTypeAjaxString += "<option value=" + l1.get(i).toString()
				+ ">" + l2.get(i).toString() + "</option>";
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


//public String getScrapVehicleList() throws IOException{
	
public String getScrapVehicleList() throws IOException{
	

	try{
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
	
	String orgtypeid = (String) request.getSession().getAttribute(
			"orgtypeid");
	String orgchartid = (String) request.getSession().getAttribute(
			"orgchartid");

	String id="";

	
	if(!orgtypeid.equals("0") && orgtypeid.equals("1")){
		
	}else if(!orgtypeid.equals("0") &&  orgtypeid.equals("2")){
		//id=" and ot.org_type_id="+orgtypeid;
		id = "and og.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
				+ orgchartid + "')";
		
	}
	else if(!orgtypeid.equals("0") &&  orgtypeid.equals("3")){
		id="and og.org_chart_id="+orgchartid;
		
	}
	
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
		
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		//result = daoObject.getData(total, request,sCol,sdir,viewdeletedrecord);
		result = daoObject.getDataForScrap(request,sCol,sdir,id);
		out.print(result);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return null;
}


}
