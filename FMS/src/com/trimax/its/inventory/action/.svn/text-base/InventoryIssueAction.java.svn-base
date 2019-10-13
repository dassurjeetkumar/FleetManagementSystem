package com.trimax.its.inventory.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.inventory.dao.InventoryIssueDAO;

public class InventoryIssueAction extends ActionSupport {

	InventoryIssueDAO daoObject = new InventoryIssueDAO();

	private String issutick;
	private String unitType;
	private String unitName;
	private String orgId;
	
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	private Map<Integer, String> divisionlist;
	public String getIssutick() {
		return issutick;
	}

	public void setIssutick(String issutick) {
		this.issutick = issutick;
	}
	

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String orgName;
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String issueStock() {
	
		HttpServletRequest request = ServletActionContext.getRequest();
		issutick = request.getParameter("tick").toString();
		request.getSession().setAttribute("tickets", issutick);
		setOrgName(daoObject.getOrgName());
		divisionlist =daoObject.getDivisionName();
		
		/*		noofissuedticketbooks = ticketinvdao.getTotalnoOfBooks(issutick);
		totalissuedticketvalue = ticketinvdao
				.getTotalIssuedTicketValue(issutick);

		System.out.println("inside issuestock....total ticket val"
				+ noofissuedticketbooks + "total ticket value"
				+ totalissuedticketvalue);
		noofissuedpassbooks = ticketinvdao.getTotalBooksPass(issutick);
		totalissuedpassvalue = ticketinvdao.getTotalIssuedPassvalue(issutick);

		noofissuedluggagebooks = daoObject.getTotalBooksLuggage(issutick);
		// totalissuedluggagevalue=ticketinvdao.getTotalIssuedLuggagevalue(issutick);
*/		return "success";
	}

	public String issuedStockData() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String[] cols = { "ticket_inventory_id", "denoimination_type",
					"pass_type", "service_type", "luggage_type", "key_number",
					"status" };
			String[] dbcol = { "", "denomination_type_manual",
					"denomination_key", "opening_number", "closing_number",
					"number_of_tickets", "number_of_books", "value" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = "";
			String sAmount = "";
			String sCol = "";
			sStart = request.getParameter("iDisplayStart");
			sAmount = request.getParameter("iDisplayLength");
			sCol = request.getParameter("iSortCol_0");

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

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getIssuedData(request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}

		return null;

	}
	public String issuedPassStockData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			String[] cols = { "ticket_inventory_id", "denoimination_type",
					"pass_type", "service_type", "luggage_type", "key_number",
					"status" };
			String[] dbcol = { "", "denomination_type_manual",
					"denomination_key", "opening_number", "closing_number",
					"number_of_tickets", "number_of_books", "value" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = "";
			String sAmount = "";
			String sCol = "";
			try {
				sStart = request.getParameter("iDisplayStart");
				sAmount = request.getParameter("iDisplayLength");
				sCol = request.getParameter("iSortCol_0");
			} catch (Exception e) {
				e.printStackTrace();
			}

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
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getIssuedPassData(request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}
	@SuppressWarnings("finally")
	public String issuedLuggageStockData() {
		System.out.println("issuestock is" + issutick);
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();
		String s[] = str.split(",");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String[] cols = { "ticket_inventory_id", "denoimination_type",
					"pass_type", "service_type", "luggage_type", "key_number",
					"status" };
			String[] dbcol = { "", "denomination_key", "opening_number",
					"closing_number", "number_of_tickets", "number_of_books",
					"value" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = "";
			String sAmount = "";
			String sCol = "";
			try {
				sStart = request.getParameter("iDisplayStart");
				sAmount = request.getParameter("iDisplayLength");

				sCol = request.getParameter("iSortCol_0");

			} catch (Exception e) {
				e.printStackTrace();
			}
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
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getIssuedLuggageData(request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {
			return null;
		}
	}
	
	@SuppressWarnings("finally")
	public String issuedTSheetStockData() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();
		System.out.println("str.................................."+str);
		String s[] = str.split(",");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String[] cols = { "ticket_inventory_id", "denoimination_type",
					"pass_type", "service_type", "tsheet_type", "key_number",
					"status" };
			String[] dbcol = { "", "denomination_key", "opening_number",
					"closing_number", "number_of_passes" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = "";
			String sAmount = "";
			String sCol = "";
			try {
				sStart = request.getParameter("iDisplayStart");
				sAmount = request.getParameter("iDisplayLength");

				sCol = request.getParameter("iSortCol_0");

			} catch (Exception e) {
				e.printStackTrace();
			}
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
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getIssuedTsheetData(request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {
			return null;
		}
	}
	public String saveissueStock(){
		
		String returnResult = "success";
		HttpServletRequest request = ServletActionContext.getRequest();
		if(!daoObject.validateIssue()){
			if(request.getSession().getAttribute("error")!=null){
				addActionError("Error in Issueing") ;
			}else{
				addActionError("No.of books required must be lesser than or equal to already present");
			}
			returnResult="fail";
		}else{
			if(daoObject.saveIssueStock(unitType,unitName)){
				String voucherNumber = (String) ServletActionContext.getRequest().getSession().getAttribute("voucherNumber");
				addActionMessage("Voucher no "+""+voucherNumber+" is generated and saved successfully");
				returnResult="success";
			}else{
				divisionlist =daoObject.getDivisionName();
				addActionError("Error:"+ServletActionContext.getRequest().getSession().getAttribute("error").toString()) ;
				returnResult="fail";
				ServletActionContext.getRequest().getSession().removeAttribute("error");
			}
		}
		ServletActionContext.getRequest().getSession().removeAttribute("message");
		
		return returnResult;
	}
	
	@SuppressWarnings("unchecked")
	public String getPerticularDepotName() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = daoObject.getDepotId(parentId);
		List<String> l2 = daoObject.getDepotName(parentId);
		req.getSession().setAttribute("Depotid",l1);
		List <String> depid=(List<String>)req.getSession().getAttribute("Depotid");
		System.out.println("Depoid"+depid.size());
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>Select</option>" ;
							
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

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
}
