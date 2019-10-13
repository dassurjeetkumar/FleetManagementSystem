package com.trimax.its.inventory.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.inventory.dao.EditTicketsPriorityDAO;
import com.trimax.its.ticketing.dao.TicketInventoryDao;


public class EditTicketsPriorityAction {

	EditTicketsPriorityDAO daoObject = new EditTicketsPriorityDAO();
	
	public String editTicket() {
		
		PrintWriter out = null;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String tickinvstid = request.getParameter("tickinvsid").toString();
			HttpServletResponse response = ServletActionContext.getResponse();
			out = response.getWriter();
			String priority = request.getParameter("priority");
			if (Integer.parseInt(tickinvstid) != 0) {
				daoObject.updateTicketInventoryMasterPriority(tickinvstid,priority);
				daoObject.updatePriorityTicketInventoryDetails(tickinvstid,	priority);
				out.print("Ticket Type Edited Successfully" + "@");
			} else {
				out.print("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	public String editPass() {
		
		PrintWriter out = null;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String passinvstid = request.getParameter("passinvstid").toString();
			String priority = request.getParameter("passpriority");

			HttpServletResponse response = ServletActionContext.getResponse();
			out = response.getWriter();
			if (Integer.parseInt(passinvstid) != 0) {
				daoObject.updateTicketInventoryMasterPriority(passinvstid,priority);
				daoObject.updatePriorityTicketInventoryDetails(passinvstid,	priority);
				out.print("Pass Type Edited Successfully" + "@");
			} else {
				out.print("");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	public String editLuggage() {

		PrintWriter out = null;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String lugginvstid = request.getParameter("luggageinvstid").toString();
			String priority = request.getParameter("luggagepriority");

			HttpServletResponse response = ServletActionContext.getResponse();
			out = response.getWriter();

			if (Integer.parseInt(lugginvstid) != 0) {
				daoObject.updateTicketInventoryMasterPriority(lugginvstid,priority);
				daoObject.updatePriorityTicketInventoryDetails(lugginvstid,priority);
				out.print("Luggage Type Edited Successfully" + "@");
			} else {
				out.print("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}
	public String editStock() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String issutick = request.getParameter("tick").toString();
		request.getSession().setAttribute("tickets", issutick);
		System.out.println("IssueStock is" + issutick);
		return "success";
	}

	public String editLUggageData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String[] cols = { "ticket_inventory_id", "denoimination_type",
					"pass_type", "service_type", "luggage_type", "key_number",
					"status" };
			String[] dbcol = { "", "ticket_inventory_mst_id",
					"dy.denomination_type", "denomination_key",
					"opening_number", "closing_number", "number_of_books",
					"value", "priority" };
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
			// total = ticketinvdao.getTotalEditLuggageRecords(str);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getEditLuggageData(request, str,dbcol[Integer.parseInt(sCol)], sdir);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {
		}
		return null;
	}

	public String editPassData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String[] cols = { "ticket_inventory_id", "denoimination_type",
					"pass_type", "service_type", "luggage_type", "key_number",
					"status" };
			String[] dbcol = { "", "ticket_inventory_mst_id",
					"dy.denomination_type", "denomination_key",
					"opening_number", "closing_number", "number_of_books",
					"value", "priority" };
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
			// total = ticketinvdao.getTotalEditPassRecords(str);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getEditPassData(request, str,dbcol[Integer.parseInt(sCol)], sdir);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}
		return null;

	}
	public String editStockData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();
		System.out.println("denom key is" + str);

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String[] cols = { "ticket_inventory_id", "denoimination_type",
					"pass_type", "service_type", "luggage_type", "key_number",
					"status" };
			String[] dbcol = { "", "ticket_inventory_mst_id",
					"dy.denomination_type", "denomination_key",
					"opening_number", "closing_number", "number_of_books",
					"value", "priority" };
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
			// total = ticketinvdao.getTotalEditRecords(str);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getEditData(request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}

		return null;

	}
}
