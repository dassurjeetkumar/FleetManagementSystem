package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;


import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.model.PointType;

public class BusStopAjaxAction {
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	

	public String execute() throws IOException{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BusStopsDAO busstopdao = new BusStopsDAO();
		int cnt = busstopdao.getTotalRecords(request.getParameter("sSearch"));
		String[] cols = {"Id ", "Id ", "Name", " Kanadda Name", "English Code", "Kanadda Code",
		"Locality ","Landmark", "Is Sheltered", "Alias 1","Kannada Alias 1", "Alias 2","Kannada Alias 2", "Alias 3","Kannada Alias 3",
		 "Alias 4","Kannada Alias 4",
		 "Toll Zone", "City Limit", "Ward No", " Area Population","Towards",
		"Stop Size","Fare Stage","Sub Stage", "Decscription", "Status"};
		
		String[] dbcol={"id","id","busStopName","busStopNameKannada","bus_stop_code","bus_stop_code_kannada",
				"locality","landmark","sheltered","alias1","kalias1","alias2","k_alias_2","alias3","k_alias_3","alias4","k_alias_4",
				//"alias5","k_alias_5","alias6","k_alias_6",
				"tollZone","cityLimit","wardNumber","areaPopulation","stop_direction",
				"stopSize","fareStage","sub_stage","description","status"};
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
			
			String colName = cols[col];
			int total = -1;
			total = busstopdao.getTotalRecords(request.getParameter("sSearch"));
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = busstopdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			out.print(result);
		return null;
	}
	
	public String getData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		BusStopsDAO busstopdao = new BusStopsDAO();
		int totalRecords = busstopdao.getTotalRecords(request.getParameter("sSearch"));
		int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		
		int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		String echo = request.getParameter("sEcho");
		
		int end = iDisplayStart+iDisplayLength;
		end = end > totalRecords ? totalRecords : end;
		
		
		return null;
	}
	
	
}
