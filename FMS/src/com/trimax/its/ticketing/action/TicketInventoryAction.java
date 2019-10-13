package com.trimax.its.ticketing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.trimax.doa.ticketing.model.TicketInventory;
import com.trimax.doa.ticketing.model.TicketInventoryMaster;
import com.trimax.doa.ticketing.model.TicketInvoiceMaster;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.route.dao.FloorDao;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.transport.dao.ShiftTypeDao;
import com.trimax.its.usermanagement.dao.UserDetailDAO;
import com.trimax.its.util.HibernateUtilTick;
import com.trimax.its.utility.dao.PageMasterDao;
import com.trimax.its.vehicle.dao.ComplaintDataDao;

public class TicketInventoryAction extends ActionSupport implements Preparable {

	private TicketInventory tickinv;
	private String stockentrysuccmsg;
	private static String vouncherid = "";
	private String msg;
	private String ticktmsg;
	private String tickteditmsg;
	private String ticktdeletemsg;
	public String orgName;
	private String passdeletemsg;
	private String luggagedeletemsg;
	private String passeditmsg;
	private String luggeditmsg;
	private int noofissuedticketbooks;
	private int totalissuedticketvalue;
	private int noofissuedpassbooks;
	private int totalissuedpassvalue;
	private int noofissuedluggagebooks;
	private int totalissuedluggagevalue;
	private String passmsg;
	private String luggmsg;
	private boolean insertflag;
	private int stockEntryTempRefId;

	TicketInventoryDao daoObject = new TicketInventoryDao();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	public String getStockentrysuccmsg() {
		return stockentrysuccmsg;
	}

	public void setStockentrysuccmsg(String stockentrysuccmsg) {
		this.stockentrysuccmsg = stockentrysuccmsg;
	}

	public String getTicktdeletemsg() {
		return ticktdeletemsg;
	}

	public void setTicktdeletemsg(String ticktdeletemsg) {
		this.ticktdeletemsg = ticktdeletemsg;
	}

	public String getPassdeletemsg() {
		return passdeletemsg;
	}

	public void setPassdeletemsg(String passdeletemsg) {
		this.passdeletemsg = passdeletemsg;
	}

	public String getLuggagedeletemsg() {
		return luggagedeletemsg;
	}

	public void setLuggagedeletemsg(String luggagedeletemsg) {
		this.luggagedeletemsg = luggagedeletemsg;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTickteditmsg() {
		return tickteditmsg;
	}

	public void setTickteditmsg(String tickteditmsg) {
		this.tickteditmsg = tickteditmsg;
	}

	public String getPasseditmsg() {
		return passeditmsg;
	}

	public void setPasseditmsg(String passeditmsg) {
		this.passeditmsg = passeditmsg;
	}

	public String getLuggeditmsg() {
		return luggeditmsg;
	}

	public void setLuggeditmsg(String luggeditmsg) {
		this.luggeditmsg = luggeditmsg;
	}

	public int getNoofissuedticketbooks() {
		return noofissuedticketbooks;
	}

	public void setNoofissuedticketbooks(int noofissuedticketbooks) {
		this.noofissuedticketbooks = noofissuedticketbooks;
	}

	public int getTotalissuedticketvalue() {
		return totalissuedticketvalue;
	}

	public void setTotalissuedticketvalue(int totalissuedticketvalue) {
		this.totalissuedticketvalue = totalissuedticketvalue;
	}

	public int getNoofissuedpassbooks() {
		return noofissuedpassbooks;
	}

	public void setNoofissuedpassbooks(int noofissuedpassbooks) {
		this.noofissuedpassbooks = noofissuedpassbooks;
	}

	public int getTotalissuedpassvalue() {
		return totalissuedpassvalue;
	}

	public void setTotalissuedpassvalue(int totalissuedpassvalue) {
		this.totalissuedpassvalue = totalissuedpassvalue;
	}

	public int getNoofissuedluggagebooks() {
		return noofissuedluggagebooks;
	}

	public void setNoofissuedluggagebooks(int noofissuedluggagebooks) {
		this.noofissuedluggagebooks = noofissuedluggagebooks;
	}

	public int getTotalissuedluggagevalue() {
		return totalissuedluggagevalue;
	}

	public void setTotalissuedluggagevalue(int totalissuedluggagevalue) {
		this.totalissuedluggagevalue = totalissuedluggagevalue;
	}

	public String getTicktmsg() {
		return ticktmsg;
	}

	public void setTicktmsg(String ticktmsg) {
		this.ticktmsg = ticktmsg;
	}

	public String getPassmsg() {
		return passmsg;
	}

	public void setPassmsg(String passmsg) {
		this.passmsg = passmsg;
	}

	public String getLuggmsg() {
		return luggmsg;
	}

	public void setLuggmsg(String luggmsg) {
		this.luggmsg = luggmsg;
	}

	public boolean isInsertflag() {
		return insertflag;
	}

	public void setInsertflag(boolean insertflag) {
		this.insertflag = insertflag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username;

	private Map<Integer, String> denomMap;

	public Map<Integer, String> getDenomMap() {
		return denomMap;
	}

	public void setDenomMap(Map<Integer, String> denomMap) {
		this.denomMap = denomMap;
	}

	private String issutick;

	public String getIssutick() {
		return issutick;
	}

	public void setIssutick(String issutick) {
		this.issutick = issutick;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public TicketInventory getTickinv() {
		return tickinv;
	}

	public void setTickinv(TicketInventory tickinv) {
		this.tickinv = tickinv;
	}
	public int getStockEntryTempRefId() {
		return stockEntryTempRefId;
	}

	public void setStockEntryTempRefId(int stockEntryTempRefId) {
		this.stockEntryTempRefId = stockEntryTempRefId;
	}

	public String execute() {


		String succmsgdelete = request.getParameter("succmsgdelete");
		if (succmsgdelete != null) {
			String deletemessage[] = succmsgdelete.split("@");
			if (!deletemessage[1].equalsIgnoreCase("-")) {
				ticktdeletemsg = deletemessage[1];
			}
			if (!deletemessage[2].equalsIgnoreCase("-")) {
				passdeletemsg = deletemessage[2];
			}
			if (!deletemessage[3].equalsIgnoreCase("-")) {
				luggagedeletemsg = deletemessage[3];
			}
			for (int i = 0; i < deletemessage.length; i++) {
//				System.out.println("message is.." + deletemessage[i]);
			}
		}

		String succmsgedit = request.getParameter("succmsgedit");
		if (succmsgedit != null) {
			String editmessage[] = succmsgedit.split("@");
			if (!editmessage[1].equalsIgnoreCase("-")) {
				tickteditmsg = editmessage[1];
			}
			if (!editmessage[2].equalsIgnoreCase("-")) {
				passeditmsg = editmessage[2];
			}
			if (!editmessage[3].equalsIgnoreCase("-")) {
				luggeditmsg = editmessage[3];
			}
			for (int i = 0; i < editmessage.length; i++) {
//				System.out.println("message is.." + editmessage[i]);
			}
		}

		String succmsg = request.getParameter("stockentrysuccmsg");
		if (succmsg != null) {
			String message[] = succmsg.split("@");
			try {
				if (!message[1].equalsIgnoreCase("")) {
					ticktmsg = message[1];
				}
				if (!message[2].equalsIgnoreCase("")) {
					passmsg = message[2];
				}
				if (!message[3].equalsIgnoreCase("")) {
					luggmsg = message[3];
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			for (int i = 0; i < message.length; i++) {
//				System.out.println("message is.." + "...I" + i + "***"
//						+ message[i]);
			}
		}

		setOrgName(daoObject.setCurrentOrgName());
		return "success";
	}

	public String issuTicket() {
		setOrgName(daoObject.setCurrentOrgName());
		setStockEntryTempRefId(daoObject.getStockeEntryRefId());
		return "success";
	}

	private boolean isEmpty(String str) {

		return str == null ? true : (str.equals("") ? true : false);
	}

	public String validateDenom() {

		String denomno = request.getParameter("denom_no").toString();
		String denomkey = request.getParameter("denom_key").toString();
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		String noofbooks = request.getParameter("no_of_books").toString();
		String value1 = request.getParameter("value").toString();
		String tickettype = request.getParameter("tickettype").toString();
		int clno = Integer.parseInt(endno);
		if (isEmpty(getUsername())) {

			addActionError("field name is manadatory");
			return "input";

		}

		return "success";
	}

	public String getDenom() {
		try {
			PrintWriter out = response.getWriter();
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			String data = tickinvdaodao.getDenomlist();
			out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPasslist() {
		String passtype = request.getParameter("tickettype").toString();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			String data = tickinvdaodao.getPasslist(passtype);
			out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getPassDayOrMonth() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String passtype = request.getParameter("tickettype").toString();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			String data = tickinvdaodao.getPassDayOrMOnthList(passtype);
			out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getLuggagelist() {

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			String data = tickinvdaodao.getLuggagelist();
			out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String insertrows() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		int orgTypeId = Integer.parseInt((String) request.getSession().getAttribute("orgtypeid"));
		int orgId = Integer.parseInt((String) request.getSession().getAttribute("orgchartid"));
		String denomno = request.getParameter("denom_no").toString();
		String denomkey = request.getParameter("denom_key").toString();
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		String noofbooks = request.getParameter("no_of_books").toString();
		String value1 = request.getParameter("value").toString();
		String tickettype = request.getParameter("tickettype").toString();
		String priority = request.getParameter("priority").toString();
		int refId = Integer.parseInt(request.getParameter("refId").toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		int endinnolength = 0;
		int startlength = 0;
		PrintWriter out = null;

		if (!denomkey.equals("0")) {
			out = response.getWriter();
			String result = "";
			int stno = 0;
			int clno = 0;
			boolean flag = true;
			if ((denomkey == null) || (denomkey == "")	|| (denomkey.trim().length() < 1)) {
				flag = false;
				result = "denom key is required@";
				out.print(result);
			}
			if ((startno == null) || (startno == "")|| (startno.trim().length() < 1)) {
				flag = false;
				result = "startno  is required@";
				out.print(result);
			} else {
				stno = Integer.parseInt(startno);
				startlength = (int) Math.log10(stno) + 1;
			}
			if ((endno == null) || (endno == "") || (endno.trim().length() < 1)) {
				flag = false;
				result = "endno  is required@";
				out.print(result);
			} else {
				clno = Integer.parseInt(endno);
				endinnolength = (int) Math.log10(clno) + 1;
			}
			int diff = (clno - stno) + 1;
			int firsttwostart = stno % 100;
			int firstthreestart = stno % 1000;
			int firstthreeend = clno % 1000;
			int thirddigit = getNthDigit(stno, 10, 3);
			int endmsgno = (thirddigit * 100) + 99;
			int b = clno % 100;
			if (b != 99) {
				flag = false;
				result = "Last two digits of Closing Number should be 99@";
				out.print(result);
			}
			if (firsttwostart != 00) {
				int diff1 = firstthreeend - firstthreestart;
				if (diff1 > 98) {
					flag = false;
					result = "Partial Block end no Should not  Exceed "	+ endmsgno + "@";
					out.print(result);
				}
			}
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			int deomval = 0;
			if (!denomkey.equals("0")) {
				deomval = tickinvdaodao.getDenomVal(Integer.parseInt(denomno));
			}
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			int count = ticketinvdao.checkForDuplicateTicketEntry(denomno,denomkey, startno, endno);
			if (count != 0) {
				flag = false;
				out.print("Ticket Type already exist." + "@");
			}

			if (flag == true) {
				int blockno = diff / 100;
				int partblock = diff % 100;
				TicketInventoryMaster tickinvmaster = new TicketInventoryMaster();
				tickinvmaster.setNumber_of_tickets(Integer.parseInt(noofbooks));
				tickinvmaster.setValue(Integer.parseInt(value1));
				tickinvmaster.setDenoimination_type(denomno);

				tickinvmaster.setKey_number(denomkey);
				tickinvmaster.setTicket_type_manual_id(1);
				tickinvmaster.setUnittype(orgTypeId);
				tickinvmaster.setUnitname(orgId);
				int inserttickinvmstid = 0;
				tickinvmaster.setOpening_number(String.valueOf(stno));
				tickinvmaster.setClosing_number(String.valueOf(clno));
				tickinvmaster.setNoofbooks(1);
				tickinvmaster.setNoofbooks(blockno);
				int prioritycount = tickinvdaodao.getPriorityCount(Integer.parseInt(denomno));
				tickinvmaster.setPriority(Integer.parseInt(priority));
				tickinvmaster.setTempRefId(refId);
				int tickinvId = tickinvdaodao.insertTicketInventoryMaster(tickinvmaster);
				inserttickinvmstid = tickinvdaodao.getMaxDEnomid();
				tickinvId = tickinvdaodao.insertTicketInventoryDetails(blockno,denomkey, denomno, priority, stno, deomval,inserttickinvmstid);
				int inserttickid = tickinvdaodao.getMaxTickinvId();
				result = "Tickets created successfully.@";
				out.print(result); // 5 aug 14
			}
			out.close();
		}
		return null;
	}

	public String getPriority() {
		

		PrintWriter out = null;
		try {
			out = response.getWriter();
			String ticketdenomno = request.getParameter("denomid").toString();
			//String ticketdenomkey = request.getParameter("denom_key").toString();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			int maxpriority = ticketinvdao.getMaxPriority(ticketdenomno);
			out.print(maxpriority);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	public String getPassPriority() {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			String passdenomno = request.getParameter("denomid").toString();
			String passDay = (request.getParameter("passDay")==null)?"":request.getParameter("passDay").toString();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			int maxpriority = ticketinvdao.getMaxPassPriority(passdenomno,passDay);
			out.print(maxpriority);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	public String getLuggagePriority() {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			String luggagedenomkey = request.getParameter("denom_key").toString();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			String luggagedeomid = ticketinvdao.getLuggageDenomVal();
			int maxpriority = ticketinvdao.getMaxLuggagePriority(luggagedeomid,luggagedenomkey);
			out.print(maxpriority);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}

	public String insertLuggagerows() throws IOException {

		String luggageticketkey = request.getParameter("luggage_ticket_key").toString();
		String noofluggageticket = request.getParameter("no_of_luggage_ticket").toString();
		int orgTypeId = Integer.parseInt((String) request.getSession().getAttribute("orgtypeid"));
		int orgId = Integer.parseInt((String) request.getSession().getAttribute("orgchartid"));
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		String noofbooks = request.getParameter("no_of_books").toString();
		String tickettype = request.getParameter("tickettype").toString();
		String priority = request.getParameter("priority").toString();
		int refId = Integer.parseInt(request.getParameter("refId").toString());
		PrintWriter out = null;
		if (!luggageticketkey.equals("0")) {
			HttpServletResponse response = ServletActionContext.getResponse();
			String result = "";
			int stno = 0;
			int clno = 0;
			int endinnolength = 0;
			int startlength = 0;
			out = response.getWriter();
			boolean flag = true;
			if ((luggageticketkey == null) || (luggageticketkey == "")|| (luggageticketkey.trim().length() < 1)) {
				
				flag = false;
				result = "denom key is required@";
				out.print(result);
			}
			if ((startno == null) || (startno == "") || (startno.trim().length() < 1)) {
				flag = false;
				result = "startno  is required@";
				out.print(result);
			} else {
				stno = Integer.parseInt(startno);
				startlength = (int) Math.log10(stno) + 1;
			}

			if ((endno == null) || (endno == "") || (endno.trim().length() < 1)) {
				flag = false;
				result = "endno  is required@";
				out.print(result);
			} else {
				clno = Integer.parseInt(endno);
				endinnolength = (int) Math.log10(clno) + 1;
			}
			int diff = (clno - stno) + 1;
			int thirddigit = getNthDigit(stno, 10, 3);
			int lastwostartno = stno % 100;

			if (!((lastwostartno == 01) || (lastwostartno == 51))) {
				flag = false;
				result = "Last two digits of Starting Number should start with 01 or 51@";
				out.print(result);
			}
			int lastwoendno = clno % 100;
			if (!((lastwoendno == 00) || (lastwoendno == 50))) {
				flag = false;
				result = "Last two digits of Closing Number should be 00 or 50\n";
				out.print(result);
			}
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			int count = ticketinvdao.checkForDuplicateLuggageEntry(luggageticketkey, startno, endno);
			if (count != 0) {
				flag = false;
			}

			int blockno = diff / 50;
			int partblock = diff % 50;
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			String luggagedeomid = tickinvdaodao.getLuggageDenomVal();
			TicketInventoryMaster tickinvmaster = new TicketInventoryMaster();

			int inserttickinvmstid = 0;
			if (flag == true) {
				tickinvmaster.setOpening_number(String.valueOf(stno));
				tickinvmaster.setClosing_number(String.valueOf(clno));
				tickinvmaster.setNoofbooks(Integer.parseInt(noofbooks));
				tickinvmaster.setNoofpasses(Integer.parseInt(noofluggageticket));
				tickinvmaster.setValue(0);
				tickinvmaster.setDenoimination_type(luggagedeomid);
				tickinvmaster.setKey_number(luggageticketkey);
				tickinvmaster.setTicket_type_manual_id(4);
				tickinvmaster.setUnittype(orgTypeId);
				tickinvmaster.setUnitname(orgId);
				tickinvmaster.setPriority(Integer.parseInt(priority));
				tickinvmaster.setTempRefId(refId);
				int tickinvId = tickinvdaodao.insertTicketInventoryMasterLuggage(tickinvmaster);

				inserttickinvmstid = tickinvdaodao.getMaxDEnomid();
				tickinvId = tickinvdaodao.insertTicketInventoryDetailsLug(blockno, luggageticketkey, "0", priority, stno, 0,inserttickinvmstid, luggagedeomid);
				int insertluggid = tickinvdaodao.getMaxTickinvId();

				result = "Luggage tickets created successfully.@";
				out.print(result);
			}
			out.close();
		}

		return null;
	}
	
	public String insertTSheetrows() throws IOException {

		String tsheetkey = request.getParameter("waybill_key").toString();
		String nooftsheets = request.getParameter("no_of_tsheets_").toString();
		int orgTypeId = Integer.parseInt((String) request.getSession().getAttribute("orgtypeid"));
		int orgId = Integer.parseInt((String) request.getSession().getAttribute("orgchartid"));
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		String tickettype = request.getParameter("tickettype").toString();
		int refId = Integer.parseInt(request.getParameter("refId").toString());
		PrintWriter out = null;
		if (!tsheetkey.equals("0")) {
			HttpServletResponse response = ServletActionContext.getResponse();
			String result = "";
			int stno = 0;
			int clno = 0;
			int endinnolength = 0;
			int startlength = 0;
			out = response.getWriter();
			boolean flag = true;
			if ((tsheetkey == null) || (tsheetkey == "")|| (tsheetkey.trim().length() < 1)) {
				
				flag = false;
				result = "denom key is required@";
				out.print(result);
			}
			if ((startno == null) || (startno == "") || (startno.trim().length() < 1)) {
				flag = false;
				result = "startno  is required@";
				out.print(result);
			} else {
				stno = Integer.parseInt(startno);
				startlength = (int) Math.log10(stno) + 1;
			}

			if ((endno == null) || (endno == "") || (endno.trim().length() < 1)) {
				flag = false;
				result = "endno  is required@";
				out.print(result);
			} else {
				clno = Integer.parseInt(endno);
				endinnolength = (int) Math.log10(clno) + 1;
			}
			int diff = (clno - stno) + 1;
			
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			int count = ticketinvdao.checkForDuplicateLuggageEntry(tsheetkey, startno, endno);
			if (count != 0) {
				flag = false;
			}

			
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			String tsheetdeomid = tickinvdaodao.getTsheetDenomVal();
			TicketInventoryMaster tickinvmaster = new TicketInventoryMaster();

			int inserttickinvmstid = 0;
			if (flag == true) {
				tickinvmaster.setOpening_number(String.valueOf(stno));
				tickinvmaster.setClosing_number(String.valueOf(clno));
				
				tickinvmaster.setNoofpasses(Integer.parseInt(nooftsheets));
				tickinvmaster.setValue(0);
				tickinvmaster.setDenoimination_type(tsheetdeomid);
				tickinvmaster.setKey_number(tsheetkey);
				tickinvmaster.setTicket_type_manual_id(5);
				tickinvmaster.setUnittype(orgTypeId);
				tickinvmaster.setUnitname(orgId);
				
				tickinvmaster.setTempRefId(refId);
				int tickinvId = tickinvdaodao.insertTicketInventoryMasterLuggage(tickinvmaster);

				inserttickinvmstid = tickinvdaodao.getMaxDEnomid();
				tickinvId = tickinvdaodao.insertTicketInventoryDetailsTsheet( tsheetkey, tsheetdeomid,  stno,clno, inserttickinvmstid,nooftsheets);
				int inserttsheetid = tickinvdaodao.getMaxTickinvId();

				result = "Trip Sheet created successfully.@";
				out.print(result);
			}
			out.close();
		}

		return null;
	}

	public String insertPassrows() throws IOException {

		int orgTypeId = Integer.parseInt((String) request.getSession().getAttribute("orgtypeid"));
		int orgId = Integer.parseInt((String) request.getSession().getAttribute("orgchartid"));
		String typeofpass = request.getParameter("type_of_pass").toString();
		String denomno = request.getParameter("denom_no").toString();
		String denomkey = request.getParameter("denom_key").toString();
		String passday = (request.getParameter("pass_day")==null)?"":request.getParameter("pass_day").toString();
		String noofpasses = request.getParameter("no_of_passess").toString();
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		String noofbooks = request.getParameter("no_of_books").toString();
		String value1 = request.getParameter("total_value").toString();
		String tickettype = request.getParameter("tickettype").toString();
		String priority = request.getParameter("priority").toString();
		int refId = Integer.parseInt(request.getParameter("refId").toString());

		PrintWriter out = null;
		if (!denomkey.equals("0")) {
			HttpServletResponse response = ServletActionContext.getResponse();
			String result = "";
			int stno = 0;
			int clno = 0;
			int endinnolength = 0;
			int startlength = 0;
			out = response.getWriter();
			
			boolean flag = true;
			if ((denomkey == null) || (denomkey == "")|| (denomkey.trim().length() < 1)) {
				flag = false;
				result = "denom key is required@";
				out.print(result);
			}

			if ((startno == null) || (startno == "")|| (startno.trim().length() < 1)) {
				flag = false;
				result = "startno  is required@";
				out.print(result);
			} else {
				stno = Integer.parseInt(startno);
				startlength = (int) Math.log10(stno) + 1;
			}

			if ((endno == null) || (endno == "") || (endno.trim().length() < 1)) {
				flag = false;
				result = "endno  is required@";
				out.print(result);
			} else {
				clno = Integer.parseInt(endno);
				endinnolength = (int) Math.log10(clno) + 1;
			}
			int diff = (clno - stno) + 1;
			int lastwostartno = stno % 100;
			int lastwoendno = clno % 100;
			if (Integer.parseInt(typeofpass) == 2) {

				if (!((lastwostartno == 01) || (lastwostartno == 51))) {
					flag = false;
					result = "Last two digits of Starting Number should start with 01 or 51@";
					out.print(result);
				}

				if (!((lastwoendno == 50) || (lastwoendno == 00))) {
					flag = false;
					result = "Last two digits of Closing Number should be 50 or 00";
					out.print(result);
				}
			}
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			int count = ticketinvdao.checkForDuplicatePassEntry(denomno, startno, endno,passday,denomkey);
			if (count != 0) {
				flag = false;
				out.print("Pass Type already exist." + "@");
			}
			int blockno = diff / 50;
			int partblock = diff % 50;
			TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
			int deomval = tickinvdaodao.getPassVal(Integer.parseInt(denomno));
			TicketInventoryMaster tickinvmaster = new TicketInventoryMaster();
			int inserttickinvmstid = 0;
			if (flag == true) {
				tickinvmaster.setOpening_number(String.valueOf(stno));
				tickinvmaster.setClosing_number(String.valueOf(clno));
				if (Integer.parseInt(typeofpass) == 2) {
					tickinvmaster.setNoofbooks(Integer.parseInt(noofbooks));
					tickinvmaster.setNoofpasses(Integer.parseInt(noofpasses));
					tickinvmaster.setValue(Integer.parseInt(value1));
				} else {
					tickinvmaster.setNoofbooks(0);
					tickinvmaster.setNoofpasses(Integer.parseInt(noofpasses));
					tickinvmaster.setValue(Integer.parseInt(value1));

				}
				tickinvmaster.setDenoimination_type(denomno);
				tickinvmaster.setPass_day(passday);
				tickinvmaster.setKey_number(denomkey);
				tickinvmaster.setTicket_type_manual_id(Integer.parseInt(tickettype));
				tickinvmaster.setUnittype(orgTypeId);
				tickinvmaster.setUnitname(orgId);
				tickinvmaster.setPriority(Integer.parseInt(priority));
				tickinvmaster.setTempRefId(refId);
				int tickinvId = tickinvdaodao.insertTicketInventoryMasterPass(tickinvmaster);
				inserttickinvmstid = tickinvdaodao.getMaxDEnomid();
				TicketInventory tickinv = new TicketInventory();
				tickinv.setTicket_type_manual_id(1);
				tickinv.setKey_number(denomkey);
				tickinv.setDenoimination_type(denomno);
				tickinv.setUnittype(orgTypeId);
				tickinv.setUnitname(orgId);
				inserttickinvmstid = tickinvdaodao.getMaxDEnomid();
				if (Integer.parseInt(typeofpass) == 2) {
					tickinvId = tickinvdaodao.insertTicketInventoryDetailsPass(	passday, tickettype, blockno, denomkey, denomno,priority, stno, deomval, inserttickinvmstid);

				}
				if (Integer.parseInt(typeofpass) == 3) {
					/*tickinv.setStartno(Integer.parseInt(startno));
					tickinv.setEndno(Integer.parseInt(endno));
					tickinv.setNoofbooks(1);
					tickinv.setNoofpasses(Integer.parseInt(noofpasses));
					tickinv.setTicketinventorymasterid(inserttickinvmstid);
					tickinv.setValue(Integer.parseInt(value1));
					tickinv.setDenoimination_type(denomno);
					tickinv.setPass_day(passday);
					tickinv.setKey_number(denomkey);
					tickinv.setTicket_type_manual_id(Integer.parseInt(tickettype));
					tickinv.setUnittype(orgTypeId);
					tickinv.setUnitname(orgId);
					tickinv.setPriority(Integer.parseInt(priority));
					tickinvId = tickinvdaodao.insertTicketInventoryDetails(tickinv);*/
					
					tickinvId = tickinvdaodao.insertTicketInventoryDetailsMonthlyPass(	passday, tickettype, noofpasses, denomkey, denomno,priority, stno, deomval, inserttickinvmstid);


				}if (Integer.parseInt(typeofpass) == 6 || Integer.parseInt(typeofpass)==7) {
					/*tickinv.setStartno(Integer.parseInt(startno));
					tickinv.setEndno(Integer.parseInt(endno));
					tickinv.setNoofbooks(1);
					tickinv.setNoofpasses(Integer.parseInt(noofpasses));
					tickinv.setTicketinventorymasterid(inserttickinvmstid);
					tickinv.setValue(Integer.parseInt(value1));
					tickinv.setDenoimination_type(denomno);
					tickinv.setPass_day(passday);
					tickinv.setKey_number(denomkey);
					tickinv.setTicket_type_manual_id(Integer.parseInt(tickettype));
					tickinv.setUnittype(orgTypeId);
					tickinv.setUnitname(orgId);
					tickinv.setPriority(Integer.parseInt(priority));
					tickinvId = tickinvdaodao.insertTicketInventoryDetails(tickinv);*/
					
					tickinvId = tickinvdaodao.insertTicketInventoryDetailsMonthlyDailyPassId( tickettype, noofpasses, denomkey, denomno,priority, stno, deomval, inserttickinvmstid);


				}
				int insertpassid = tickinvdaodao.getMaxTickinvId();
				result = "Pass tickets created successfully.@";
				out.print(result);
			}
			out.close();
		}
		return null;
	}
	
	public String saveEntry(){
		
		String returnResult = "";
		int id = Integer.parseInt(request.getParameter("stockentrysuccmsg").toString());
		if(daoObject.finalSaveOfStockEntry(id)){
			int refId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("RefId").toString());
			addActionMessage("Stock Saved Successfully. Ref No. "+refId);
			//request.getSession().removeAttribute("RefId");
			return "success";
		}else{
			return "success";
		}
		
	}

	public String getAllDenoms() {
		TicketInventoryDao dao = new TicketInventoryDao();

		List<String> l1 = dao.getDenomId();
		List<String> l2 = dao.getDenomName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>------select------</option>";
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
			e.printStackTrace();
		}

		return null;
	}

	public String addStock() {
		
		HttpServletRequest request = ServletActionContext.getRequest();

		TicketInventoryDao dao = new TicketInventoryDao();
		boolean flag = false;
		int issuefromunitid = Integer.parseInt(request.getSession().getAttribute("orgchartid").toString());
		int issuefromunit = Integer.parseInt(request.getSession().getAttribute("orgtypeid").toString());
		String voucherid = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtilTick.getSession("hibernate.cfg.xml"); 
			tx = session.beginTransaction();
			if (request.getParameterValues("requiredamount") != null) {
				String s1[] = request.getParameterValues("requiredamount");
				String tickettype = request.getParameter("tickettype");
				String noofbookstkt1[] = request
						.getParameterValues("numberofbooks");
				int j = 0;
				for (int i = 0; i < s1.length; i++) {
					if (s1[i] == "") {
						msg = "Please provide No. of books to be issued.";
						return "input";
					}
					if (s1[i].contains(".") == true) {
						msg = "No. of books to be issued should not be decimal.";
						return "input";
					}
					if (Integer.parseInt(s1[i]) > Integer
							.parseInt(noofbookstkt1[i])) {
						msg = "No. of books count to be issued should be lesser the available no. of books.";
						return "input";
					}
				}
			}
			if (request.getParameterValues("requiredpassamount") != null) {
				String spass1[] = request
						.getParameterValues("requiredpassamount");
				String passtype[] = request.getParameterValues("passtype");
				String noofbookspass1[] = request
						.getParameterValues("numberofbookspass");
				String noofpass1[] = request.getParameterValues("numberofpass");
				int j = 0;
				for (int i = 0; i < spass1.length; i++) {
					if (spass1[i] == "") {
						msg = "Please provide No. of books to be issued.";
						return "input";
					}
					if (spass1[i].contains(".") == true) {
						msg = "No.of books to be issued should not be decimal.";
						return "input";
					}
					if (passtype[i].equals("2")
							&& Integer.parseInt(spass1[i]) > Integer
									.parseInt(noofbookspass1[i])) {
						msg = "No.of books count to be issued should be lesser the available no.of books.";
						return "input";
					}
					if (passtype[i].equals("3")
							&& Integer.parseInt(spass1[i]) > Integer
									.parseInt(noofpass1[i])) {
						msg = "No.of books count to be issued should be lesser the available no.of books.";
						return "input";
					}
				}
			}
			if (request.getParameterValues("requiredluggageamount") != null) {
				String sluggage1[] = request
						.getParameterValues("requiredluggageamount");
				int luggagetype = Integer.parseInt(request
						.getParameter("luggagetype"));
				String noofbookslugg1[] = request
						.getParameterValues("numberofbooksluggage");
				int j = 0;
				for (int i = 0; i < sluggage1.length; i++) {
					if (sluggage1[i] == "") {
						msg = "Please provide No. of books to be issued.";
						return "input";
					}
					if (sluggage1[i].contains(".") == true) {
						msg = "No. of books to be issued should not be decimal.";
						return "input";
					}
					if (Integer.parseInt(sluggage1[i]) > Integer
							.parseInt(noofbookslugg1[i])) {
						msg = "No. of books count to be issued should be lesser the available no. of books.";
						return "input";
					}
				}
			}
			if (request.getParameterValues("requiredamount") != null) {
				String s[] = request.getParameterValues("requiredamount");
				String tickettype = request.getParameter("tickettype");
				String noofbookstkt[] = request
						.getParameterValues("numberofbooks");
				int j = 0;
				for (int i = 0; i < s.length; i++) {
					String denomkey = request
							.getParameterValues("denominationkey")[i];
					voucherid = dao.insertTicketInventoryLogs(session,
							denomkey, Integer.parseInt(s[i]), tickinv, flag,
							issuefromunit, issuefromunitid,
							Integer.parseInt(tickettype));
					flag = true;
					int tickinvinsertinvoicemasterid = dao
							.getMaxInventInvMastId(session);
					int stockvalue = Integer.parseInt(s[i]) * 100 * 5;
					dao.updateTicketInvoiceDetails(session,
							tickinvinsertinvoicemasterid, stockvalue);

				}
			}
			if (request.getParameterValues("requiredpassamount") != null) {

				String spass[] = request
						.getParameterValues("requiredpassamount");
				int passtype = Integer.parseInt(request
						.getParameter("passtype"));
				String noofbookspass[] = request
						.getParameterValues("numberofbookspass");
				String noofpass[] = request.getParameterValues("numberofpass");
				int j = 0;
				for (int i = 0; i < spass.length; i++) {
					String denomkey = request.getParameterValues("passkey")[i];
					String pastypes = request.getParameterValues("passtype")[i];
					int passType = Integer.parseInt(pastypes);
					voucherid = dao.insertTicketInventoryLogs(session,
							denomkey, Integer.parseInt(spass[i]), tickinv,
							flag, issuefromunit, issuefromunitid, passType);
					flag = true;
					int tickinvinsertinvoicemasterid = dao
							.getMaxInventInvMastId(session);
					int stockvalue = Integer.parseInt(spass[i]) * 100 * 5;
					dao.updateTicketInvoiceDetails(session,
							tickinvinsertinvoicemasterid, stockvalue);
				}
			}

			if (request.getParameterValues("requiredluggageamount") != null) {
				String sluggage[] = request
						.getParameterValues("requiredluggageamount");
				int luggagetype = Integer.parseInt(request
						.getParameter("luggagetype"));
				String noofbookslugg[] = request
						.getParameterValues("numberofbooksluggage");
				int j = 0;
				for (int i = 0; i < sluggage.length; i++) {
					String denomkey = request.getParameterValues("luggakey")[i];
					voucherid = dao.insertTicketInventoryLogs(session,
							denomkey, Integer.parseInt(sluggage[i]), tickinv,
							flag, issuefromunit, issuefromunitid, luggagetype);
					flag = true;
					int tickinvinsertinvoicemasterid = dao
							.getMaxInventInvMastId(session);
					int stockvalue = Integer.parseInt(sluggage[i]) * 100 * 5;
					dao.updateTicketInvoiceDetails(session,
							tickinvinsertinvoicemasterid, stockvalue);
				}
			}
			if (!voucherid.equals("")) {
				msg = "Voucher number " + voucherid
						+ "  is generated and saved successfully";
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
		return "success";
	}

	@SkipValidation
	public String showTicketInv() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			String[] cols = { "", "dy.denomination_type_manual",
					"a.created_date", "a.denomination_key", "a.opening_number",
					"a.closing_number", "a.number_of_tickets",
					"a.number_of_books", "a.value", "a.priority" };
			String[] dbcol = { "", "dy.denomination_type_manual",
					"a.created_date", "a.denomination_key", "a.opening_number",
					"a.closing_number", "a.number_of_tickets",
					"a.number_of_books", "a.value", "a.priority" };
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
				if (col < 0 || col > 10)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			//total = ticketinvdao.getTotalRecords();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = ticketinvdao.getData(total, request, dbcol[col], sdir);
			out.print(result);
		} catch (Exception ex) {
		} finally {
		}
		return null;
	}
	public String showPassInv() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			String[] cols = { "", "dy.denomination_type_manual",
					"ttm.ticket_type_manual_name", "a.created_date",
					"a.denomination_key", "a.pass_day", "a.opening_number",
					"a.closing_number", "a.number_of_tickets",
					"a.number_of_books", "a.value", "a.priority" };
			String[] dbcol = { "", "dy.denomination_type_manual",
					"ttm.ticket_type_manual_name", "a.created_date",
					"a.denomination_key", "a.pass_day", "a.opening_number",
					"a.closing_number", "a.number_of_tickets",
					"a.number_of_books", "a.value", "a.priority" };
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
				if (col < 0 || col > 12)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			//total = ticketinvdao.getTotalPassRecords();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = ticketinvdao.getPassData(total, request,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
		} finally {

		}

		return null;

	}


	public String showLuggageInv() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			String[] cols = { "", "dy.denomination_type_manual",
					"a.created_date", "a.denomination_key", "a.opening_number",
					"a.closing_number", "a.number_of_tickets",
					"a.number_of_books", "a.value", "a.priority" };
			String[] dbcol = { "", "dy.denomination_type_manual",
					"a.created_date", "a.denomination_key", "a.opening_number",
					"a.closing_number", "a.number_of_tickets",
					"a.number_of_books", "a.value", "a.priority" };
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
				if (col < 0 || col > 15)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			//total = ticketinvdao.getLuggageTotalRecords();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = ticketinvdao.getLuggageData(total, request,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
		} finally {

		}

		return null;
	}
	
	public String showTSheetInv() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			String[] cols = { "", "dy.denomination_type_manual",
					"a.created_date", "a.denomination_key", "a.opening_number",
					"a.closing_number", "a.number_of_passes" };
			String[] dbcol = { "", "dy.denomination_type_manual",
					"a.created_date", "a.denomination_key", "a.opening_number",
					"a.closing_number", "a.number_of_passes" };
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
				if (col < 0 || col > 15)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
			//total = ticketinvdao.getLuggageTotalRecords();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = ticketinvdao.getTsheetData(total, request,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
		} finally {

		}

		return null;
	}

	public String getForPrint(){
		TicketInventoryDao daoObject = new TicketInventoryDao();
		String data = daoObject.getPrintData();
		PrintWriter out = null;
		HttpServletResponse response = null;
		try{
			response = ServletActionContext.getResponse();
			out = response.getWriter();
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(data);
		return null;
		
	}
	public String getTotalPass() throws IOException {
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		int passtotal = ticketinvdao.getTotalNumberOfPasses();
		int passtotalvalue = ticketinvdao.getTotalPassValue();
		HttpServletResponse response = ServletActionContext.getResponse();
		String result = passtotal + "@" + passtotalvalue;
		PrintWriter out = null;
		out = response.getWriter();
		out.print(result);

		return null;
	}
	public String getTotalLuggage() throws IOException {
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		int luggagetotal = ticketinvdao.getTotalNumberOfLuggagess();
		// int luggagetotalvalue=ticketinvdao.getTotalLugggageValue();
		HttpServletResponse response = ServletActionContext.getResponse();
		String result = luggagetotal + "";
		PrintWriter out = null;
		out = response.getWriter();
		out.print(result);

		return null;
	}

	public String getTotalTicket() throws IOException {
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		int ticktotal = ticketinvdao.getTotalNumberOfBooks();
		int tickettotalvalue = ticketinvdao.getTotalTicketValue();
		HttpServletResponse response = ServletActionContext.getResponse();
		String result = ticktotal + "@" + tickettotalvalue;
		PrintWriter out = null;
		out = response.getWriter();
		out.print(result);

		return null;
	}
	public String getOrgType() {
		
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		List<String> l1 = tickinvdaodao.getOrgTypeId();
		List<String> l2 = tickinvdaodao.getOrgTypeName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='orgType" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getUnitName() {
		
		int orgtypeid = Integer.parseInt(request.getParameter("orgid"));
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		List<String> l1 = tickinvdaodao.getUnitId(orgtypeid);
		List<String> l2 = tickinvdaodao.getUnitName(orgtypeid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='orgName" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println(orgtypeid);
		return null;

	}

	

	public void prepare() throws Exception {
	}

	

	public String deleteTicket() {

		String tickinvmstd = request.getParameter("tickinvmstid").toString();
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (Integer.parseInt(tickinvmstd) != 0) {
				tickinvdaodao.delteTicketInvMstDetails(tickinvmstd);
				tickinvdaodao.delteTicketInvDetails(tickinvmstd);
				out.print("Ticket Type Deleted Successfully@");
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

	public String deletePass() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String passinvmstd = request.getParameter("tickinvpassid").toString();
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (Integer.parseInt(passinvmstd) != 0) {
				tickinvdaodao.delteTicketInvMstDetails(passinvmstd);
				tickinvdaodao.delteTicketInvDetails(passinvmstd);
				out.print("Pass Type Deleted Successfully@");
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

	public String checkForDelTickInv() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tickinvmstd = request.getParameter("tickinvid").toString();
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		int count = tickinvdaodao.checkFordeleteTicketInvMst(tickinvmstd);
		PrintWriter out = null;
		HttpServletResponse response = ServletActionContext.getResponse();

		out = response.getWriter();
		if (count != 0) {
			// result = "Duty type Id in use can not Delete";
			out.print(count);
			msg = "Duty Type Id in Use Not Deleted";
		} else {
			// result = "Press ok";
			out.print(count);
		}

		return null;
	}

	public String checkForDelLuggageInv() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tickinvmstd = request.getParameter("luggagetickinvid")
				.toString();
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		int count = tickinvdaodao.checkFordeleteTicketInvMst(tickinvmstd);
		PrintWriter out = null;
		HttpServletResponse response = ServletActionContext.getResponse();

		out = response.getWriter();
		if (count != 0) {
			// result = "Duty type Id in use can not Delete";
			out.print(count);
			msg = "Duty Type Id in Use Not Deleted";
		} else {
			// result = "Press ok";
			out.print(count);
		}

		return null;
	}

	public String checkForDelPassInv() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tickinvmstd = request.getParameter("passtickinvid").toString();
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		int count = tickinvdaodao.checkFordeleteTicketInvMst(tickinvmstd);
		PrintWriter out = null;
		HttpServletResponse response = ServletActionContext.getResponse();

		out = response.getWriter();
		if (count != 0) {
			// result = "Duty type Id in use can not Delete";
			out.print(count);
			msg = "Duty Type Id in Use Not Deleted";
		} else {
			// result = "Press ok";
			out.print(count);
		}

		return null;
	}

	public String deleteLuggage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String lugginvmstd = request.getParameter("tickinvluggageid")
				.toString();
		TicketInventoryDao tickinvdaodao = new TicketInventoryDao();
		// tickinvdaodao.delteTicketInvMstDetails(lugginvmstd);
		// tickinvdaodao.delteTicketInvDetails(lugginvmstd);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (Integer.parseInt(lugginvmstd) != 0) {
				tickinvdaodao.delteTicketInvMstDetails(lugginvmstd);
				tickinvdaodao.delteTicketInvDetails(lugginvmstd);
				out.print("Luggage Type Deleted Successfully@");
			} else {
				out.print("");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
		return null;
	}


	public String deleteStock() {
		HttpServletRequest request = ServletActionContext.getRequest();
		issutick = request.getParameter("tick").toString();
		request.getSession().setAttribute("tickets", issutick);
		return "success";
	}

	public String deletePassData() {
		// System.out.println("issuestock is" + issutick);
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();

		int usrsessionid = (Integer) request.getSession()
				.getAttribute("userid");
		try {
			// HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();
			int cnt = ticketinvdao.getTotalDeletePassRecords(str);
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
			total = ticketinvdao.getTotalDeletePassRecords(str);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = ticketinvdao.getDeletePassData(total, request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;

	}

	public String deleteLuggageData() {
		// System.out.println("issuestock is" + issutick);
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();

		int usrsessionid = (Integer) request.getSession()
				.getAttribute("userid");
		try {
			// HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();

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
			total = ticketinvdao.getTotalLuggageDeleteRecords(str);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = ticketinvdao.getLuggageDeleteData(total, request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;

	}

	public String deleteStockData() {
		// System.out.println("issuestock is" + issutick);
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getSession().getAttribute("tickets").toString();

		int usrsessionid = (Integer) request.getSession()
				.getAttribute("userid");
		try {
			// HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao ticketinvdao = new TicketInventoryDao();

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
			total = ticketinvdao.getTotalDeleteRecords(str);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = ticketinvdao.getDeleteData(total, request, str,dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;

	}

	public String checkDuplicateTicket() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String denomno = request.getParameter("denomval").toString();
		String denomkey = request.getParameter("denom_key").toString();
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		
		int count = ticketinvdao.checkForDuplicateTicketEntry(denomno,
				denomkey, startno, endno);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(count);

		return null;
	}

	public String checkDuplicatePass() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String denomno = request.getParameter("denomval").toString();
		String denomkey = request.getParameter("denom_key").toString();
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		String passDay = (request.getParameter("passDay")==null)?"":request.getParameter("passDay").toString();
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		int count = ticketinvdao.checkForDuplicatePassEntry(denomno,startno,endno,denomkey,passDay);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(count);
		return null;
	}

	public String checkDuplicateLuggage() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String denomkey = request.getParameter("denom_key").toString();
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		
		int count = ticketinvdao.checkForDuplicateLuggageEntry(denomkey,
				startno, endno);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(count);
		return null;
	}
	public String checkDuplicateTsheet() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String denomkey = request.getParameter("denom_key").toString();
		String startno = request.getParameter("start_no").toString();
		String endno = request.getParameter("end_no").toString();
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		
		int count = ticketinvdao.checkForDuplicateTSheetEntry(denomkey,
				startno, endno);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(count);
		return null;
	}

	public int getNthDigit(int number, int base, int n) {
		return (int) ((number / Math.pow(base, n - 1)) % base);
	}

	public String getVouncherDetailsShow() throws Exception {

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketInventoryDao userdao = new TicketInventoryDao();

			// JSONObject result = new JSONObject();
			String result;

			// response.setContentType("application/json");
			// response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			// result=userdao.getListVoucheShow(vouncherid);
			result = userdao.getListVoucheShow(vouncherid);
			// result = userdao.getUserList(total,request,sdir);
			// System.out.println("result111----------"+result);
			out = response.getWriter();
			out.print(result);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
