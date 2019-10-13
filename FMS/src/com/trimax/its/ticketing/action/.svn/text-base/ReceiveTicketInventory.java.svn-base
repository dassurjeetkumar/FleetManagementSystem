package com.trimax.its.ticketing.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.trimax.its.ticketing.dao.TicketInventoryDao;

public class ReceiveTicketInventory {
	private String msg;
	 public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String receiveStock()
	 
	    {
	    	
	    	return "success";
	    }
	 
	 public String rejectTicket()
	 {

		 HttpServletRequest request = ServletActionContext.getRequest();
		 String issutick = request.getParameter("tick").toString();
		 issutick=issutick.substring(0,issutick.length()-1);
		 request.getSession().setAttribute("tickets", issutick);
		 String s[]=issutick.split(",");
		
		 for(int i=0;i<s.length;i++)
		 {
			 TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			
			 tickinvdaodao.updateTickInvDetailsReject(Integer.parseInt(s[i]));
			
			 tickinvdaodao.updateTicketInvoiceMasterReject(Integer.parseInt(s[i]));
			tickinvdaodao.updateTicketInventoryLogsReject(Integer.parseInt(s[i]));
		 }
		 msg="Issued tickets are rejected Successfully";
		 return "success"; 
	 }
	 
	 public String acceptTicket()
	 {
		 HttpServletRequest request = ServletActionContext.getRequest();
		 String issutick = request.getParameter("tick").toString();
		 issutick=issutick.substring(0,issutick.length()-1);
		 request.getSession().setAttribute("tickets", issutick);
		 String s[]=issutick.split(",");
		
		 for(int i=0;i<s.length;i++)
		 {
			 TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
				
			tickinvdaodao.updateTickInvDetails(Integer.parseInt(s[i]));
			 
			 tickinvdaodao.updateTicketInvoiceMaster(Integer.parseInt(s[i]));
			tickinvdaodao.updateTicketInventoryLogs(Integer.parseInt(s[i]));
			
		 }
		 msg="Issued tickets are accepted Successfully";
		 System.out.println("IssueStock is"+issutick);
		 return "success"; 
	 }
	 private String SEARCH_TERM;

		private String COL_NAME;

		private String DIR;

		private int START;

		private int AMOUNT;

	    public String execute()
	    {
	    	// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			HttpServletRequest request = ServletActionContext.getRequest();
			int usrsessionid = (Integer) request.getSession()
					.getAttribute("userid");
			try {
				// HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				TicketInventoryDao ticketinvdao = new TicketInventoryDao();
				int cnt = ticketinvdao.getTotalIssueRecords();
				String[] cols = { "ticket_inventory_id", "denoimination_type",
						"pass_type", "service_type", "luggage_type", "key_number",
						"status" };
				String[] dbcol = { "",
						"a.tickinvmstid", "ot.org_type", "oc.org_name",
						"a.created_date", "a.stockval"};
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

				String colName = cols[col];
				int total = -1;
				total = ticketinvdao.getTotalIssueRecords();
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				
				result = ticketinvdao.getIssueData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
				out.print(result);
			} catch (Exception ex) {
			} finally {

			}

			return null;

	    }
}
