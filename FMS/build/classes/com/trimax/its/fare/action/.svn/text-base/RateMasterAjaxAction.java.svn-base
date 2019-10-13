package com.trimax.its.fare.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.fare.dao.RateMasterDAO;
import com.trimax.its.transport.dao.BusStopsDAO;

public class RateMasterAjaxAction {
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String execute() throws IOException{
		System.out.println("Inside Rate Master Ajax ");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		RateMasterDAO rateMasterdao = new RateMasterDAO();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		//System.out.println("Count------>"+cnt);
		String[] cols = { "","Rate Master Id ", "Version No.-Fare Type", "Effective Start Date", "Effective End Date", "Status","Rate Master Id "};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		
		String[] dbcol={"","rateMasterId","versionNumber","effectiveStartDate","effectiveEndDate","status","rateMasterId"};
		//System.out.println("sCol>>"+sCol);
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
			col = 1;
			}
			if (sdir != null) {
			if (!sdir.equals("asc"))
			dir = "desc";
			}
			
			String colName = cols[col];
			int total = -1;
			total = rateMasterdao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//System.out.println("REsult of datatable------>"+result+" scol="+sCol);
			//System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
			result = rateMasterdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
			out.print(result);
		return null;
	}
	
	
}
