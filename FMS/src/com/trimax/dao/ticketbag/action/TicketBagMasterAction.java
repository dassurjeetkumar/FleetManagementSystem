package com.trimax.dao.ticketbag.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.OrganisationChart;

import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.dao.ticketbag.dao.TicketBagMasterDao;

public class TicketBagMasterAction extends ActionSupport implements Preparable {

	private String SEARCH_TERM;
	private String requestType;
    private int orgchartid;
    private int orgtTypeId;
    private OrganisationChart orgchart;
    private OrgType orgtype;
    
	public OrganisationChart getOrgchart() {
		return orgchart;
	}

	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}

	public OrgType getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(OrgType orgtype) {
		this.orgtype = orgtype;
	}

	public int getOrgchartid() {
		return orgchartid;
	}

	public void setOrgchartid(int orgchartid) {
		this.orgchartid = orgchartid;
	}

	public int getOrgtTypeId() {
		return orgtTypeId;
	}

	public void setOrgtTypeId(int orgtTypeId) {
		this.orgtTypeId = orgtTypeId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private TicketBagMaster ticketbagmaster;
	private String msg;

	@SkipValidation
	public String view() {
		return "view";
	}

	public String createTicketBag() {
		setOrgchartid(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()));
		setOrgtTypeId(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString()));
		return "success";
	}

	public String getDateTimeToDatePicker(Date pickerDate)

	{
		Date date = new Date();
		// String pickerDate = "2014-08-22 00:00:00";
		String arr[] = pickerDate.toString().split(" ");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

		String formattedDate = "";
		try {
			date = simpleDateFormat1.parse(arr[0]);
			formattedDate = simpleDateFormat.format(date);
			System.out.println("formattedDate" + formattedDate);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			return formattedDate;
		}

	}

	public String editTicketBag() {
		HttpServletRequest request = ServletActionContext.getRequest();
		TicketBagMasterDao ticketbagdao = new TicketBagMasterDao();
		ticketbagmaster = ticketbagdao.getTicketBagDetails(Integer
				.parseInt(request.getParameter("id")));
		setOrgchartid(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()));
		setOrgtTypeId(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString()));
		String date1 = ticketbagmaster.getEffectivestartdate();
		String date2 = ticketbagmaster.getEffectiveenddate();

		Common cm = new Common();

		// String date1 = cm.getDateToDate4(breakdownhistory.getDate());

		String effstartdate = cm.getDateFromDateTime_(date1);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ticketbagmaster.setEffectivestartdate(effstartdate);
			if (date2 != null) {
				String effenddate = cm.getDateFromDateTime_(date2);
				ticketbagmaster.setEffectiveenddate(effenddate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ticketbagmaster.setEffectiveenddate(convertDateToSring(date1));
		return "success";

	}

	public String convertDateToSring(Date date) {

		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			// Get the date today using Calendar object.
			Date today = date;
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			String reportDate = df.format(today);

			// Print what date is today!
			System.out.println("\n \t Report Date: " + reportDate);
			return reportDate;

		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public String editTicketBagDetails() {
		int orgchartid1=0;
		int orgtypeid1=0;
		orgchart=new OrganisationChart();
		orgtype=new OrgType();
		setOrgchartid(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()));
		setOrgtTypeId(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString()));
		if(getOrgtTypeId()==1){
			 orgchartid1 = ticketbagmaster.getOrgchart().getOrg_chart_id();
		}else if(getOrgtTypeId()==2){
			orgchart.setOrg_chart_id(getOrgchartid());
			ticketbagmaster.setOrgchart(orgchart.getOrg_chart_id()!=0?orgchart:null);
		   orgchartid1 = getOrgchartid();
		}else if(getOrgtTypeId()==3){
			orgchart.setOrg_chart_id(getOrgchartid());
			ticketbagmaster.setOrgchart(orgchart.getOrg_chart_id()!=0?orgchart:null);
			orgtype.setOrg_type_id(getOrgtTypeId());
			ticketbagmaster.setOrgtype(orgtype.getOrg_type_id()!=0?orgtype:null);
			 orgchartid1 = getOrgchartid();
		}
		
		TicketBagMasterDao ticketbagdao = new TicketBagMasterDao();
		int orgchartid = ticketbagmaster.getOrgchart().getOrg_chart_id();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ticketbag.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
			if(getOrgtTypeId()==1){
				 orgtypeid1 = ticketbagmaster.getOrgtype().getOrg_type_id();
				}else if(getOrgtTypeId()==2){
					orgtypeid1 = getOrgtTypeId();
				}else if(getOrgtTypeId()==3){
					orgtypeid1 = getOrgtTypeId();
				}
		if (orgtypeid1 == 0) {
			System.out.println("hi if of old");

			addFieldError("orgtypeid",
					"Ticket Bag Organisation  Type is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		if (orgchartid1 == 0) {
			System.out.println("hi if of old");

			addFieldError("orgName",
					"Ticket Bag Organisation  Name is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		

		int id = ticketbagmaster.getTicketbag_id();
		String status = ticketbagmaster.getStatus();
		String ticketbagcodeCode = ticketbagmaster.getBagcode();
				if (("".equals(ticketbagcodeCode)) || (ticketbagcodeCode == null)
				|| (ticketbagcodeCode.length() == 0)) {
			System.out.println("hi if of old of ticketbagmaster.getBagcode();"
					+ ticketbagmaster.getBagcode());

			addFieldError("TicketBagCode", "Ticket Bag Code is Manadatory");
			return "input";

		}
		CommonValidation cm = new CommonValidation();
		if (!cm.isSpecialChar(ticketbagcodeCode)) {
			addFieldError("TicketBagCode",
					"Ticket Bag Organisation  Name is Should be a alphanumeric");
			return "input";
		}
		
		

		String date1 = ticketbagmaster.getEffectivestartdate();
		String date2 = ticketbagmaster.getEffectiveenddate();
		System.out.println("start date ..." + date1 + "end date is..." + date2);
		try{
			if(ticketbagmaster.getEffectiveenddate().trim().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date effstdate=sdf.parse(ticketbagmaster.getEffectivestartdate());
				Date effenddate=sdf.parse(ticketbagmaster.getEffectiveenddate());
				
				if(effstdate.compareTo(effenddate)>0){
	        		System.out.println("Date1 is after Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		
	        		return INPUT;
	        		
	        	}else if(effstdate.compareTo(effenddate)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		return INPUT;
	        		
	        	}
			}
			}catch(Exception e){e.printStackTrace();}

		
		if (("".equals(status)) || (status == null) || (status.length() == 0)) {
			System.out.println("hi if of old");

			addFieldError("status", "status  is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		int duplicatecount = 0;
		if (ticketbagcodeCode != null) {
			duplicatecount = ticketbagdao.checkForDuplicateTicketBagCode(
					ticketbagcodeCode, id);
			System.out.println("no of records with the given value"
					+ duplicatecount);
		}
		

		if (duplicatecount == 0) {
			ticketbagdao.saveEditedTicketBagDetails(requestType, id,
					ticketbagmaster);

			msg = "Ticket Bag  Id " + id + " Updated Succesfully";
		} else {
			msg = "Ticket Bag   already exists";
			return "input";
		}
		// System.out.println("hi i am in editPageDEtails" + shiftid);

		return "success";
		}else{
			return "input";
		}

	}

	public String saveTicketBagDetails() {
		int orgchartid1=0;
		int orgtypeid1=0;
		orgchart=new OrganisationChart();
		orgtype=new OrgType();
		setOrgchartid(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString()));
		setOrgtTypeId(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgtypeid").toString()));
		if(getOrgtTypeId()==1){
			 orgchartid1 = ticketbagmaster.getOrgchart().getOrg_chart_id();
		}else if(getOrgtTypeId()==2){
			orgchart.setOrg_chart_id(getOrgchartid());
			ticketbagmaster.setOrgchart(orgchart.getOrg_chart_id()!=0?orgchart:null);
		   orgchartid1 = getOrgchartid();
		}else if(getOrgtTypeId()==3){
			orgchart.setOrg_chart_id(getOrgchartid());
			ticketbagmaster.setOrgchart(orgchart.getOrg_chart_id()!=0?orgchart:null);
			orgtype.setOrg_type_id(getOrgtTypeId());
			ticketbagmaster.setOrgtype(orgtype.getOrg_type_id()!=0?orgtype:null);
			 orgchartid1 = getOrgchartid();
		}
		
		System.out.println("hi---i mam in create ticket bag"
				+ ticketbagmaster.getEffectivestartdate() + "notes is..."
				+ ticketbagmaster.getNotes());

       HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ticketbag.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
			if(getOrgtTypeId()==1){
			 orgtypeid1 = ticketbagmaster.getOrgtype().getOrg_type_id();
			}else if(getOrgtTypeId()==2){
				orgtypeid1 = getOrgtTypeId();
			}else if(getOrgtTypeId()==3){
				orgtypeid1 = getOrgtTypeId();
			}
		
		int id = ticketbagmaster.getTicketbag_id();
		String status = ticketbagmaster.getStatus();
		String ticketbagcodeCode = ticketbagmaster.getBagcode();
		if (orgtypeid1 == 0) {
			System.out.println("hi if of old");

			addFieldError("orgtypeid",
					"Ticket Bag Organisation  Type is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		if (orgchartid1 == 0) {
			System.out.println("hi if of old");

			addFieldError("orgName",
					"Ticket Bag Organisation  Name is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		if (("".equals(ticketbagcodeCode)) || (ticketbagcodeCode == null)
				|| (ticketbagcodeCode.length() == 0)) {
			System.out.println("hi if of old of ticketbagmaster.getBagcode();"
					+ ticketbagmaster.getBagcode());

			addFieldError("TicketBagCode", "Ticket Bag Code is Manadatory");
			return "input";

		}
		CommonValidation cm = new CommonValidation();
		if (!cm.isSpecialChar(ticketbagcodeCode)) {
			addFieldError("TicketBagCode",
					"Ticket Bag Organisation  Name is Should be a alphanumeric");
			return "input";
		}
		String date2 = ticketbagmaster.getEffectiveenddate();
		String date1 = ticketbagmaster.getEffectivestartdate();
		System.out.println("start date ..." + date1 + "end date is..." + date2);
		if (("".equals(date1)) || (date1 == null)) {
			System.out
					.println("hi if of old of ticketbagmaster.getEffectiveenddate();"
							+ ticketbagmaster.getEffectiveenddate());

			addFieldError("startdate", "Ticket Bag Start Date  is Manadatory");
			return "input";

		}
		try{
			if(ticketbagmaster.getEffectiveenddate().trim().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date effstdate=sdf.parse(ticketbagmaster.getEffectivestartdate());
				Date effenddate=sdf.parse(ticketbagmaster.getEffectiveenddate());
				
				if(effstdate.compareTo(effenddate)>0){
	        		System.out.println("Date1 is after Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		
	        		return INPUT;
	        		
	        	}else if(effstdate.compareTo(effenddate)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		return INPUT;
	        		
	        	}
			}
			}catch(Exception e){e.printStackTrace();}

		
		/*if (("".equals(status)) || (status == null) || (status.length() == 0)) {
			System.out.println("hi if of old");

			addFieldError("status", "status  is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}*/

		int dupilcateticketbaginsertcount = 0;
		TicketBagMasterDao ticketbagdao = new TicketBagMasterDao();
		dupilcateticketbaginsertcount = ticketbagdao
				.checkForDuplicateTicketBagInsert(ticketbagmaster);

		//int insertid = ticketbagdao.getMaxTicketBagID() + 1;
		if (dupilcateticketbaginsertcount == 0) {
			int insertid = ticketbagdao
					.insertTicketBagDetails(ticketbagmaster);
			msg = "Ticket Bag Master Id " + insertid + " Inserted Succesfully";
		} else {
			msg = "Ticket Bag Master already Exists";
			return "input";
		}

		return "success";
		}else{
			return "input";
		}
	}

	public TicketBagMaster getTicketbagmaster() {
		return ticketbagmaster;
	}

	public void setTicketbagmaster(TicketBagMaster ticketbagmaster) {
		this.ticketbagmaster = ticketbagmaster;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@SkipValidation
	public String showTicketBag() throws IOException {

		System.out
				.println("\n \t Hello...........In Prepare....... Yes..............");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		TicketBagMasterDao ticketbagdao = new TicketBagMasterDao();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {

			int cnt = ticketbagdao.getTotalBagRecords();
			// int cnt = orgdao.getTotalRecordsOfWayBill();
			System.out.println("Count------>" + cnt);
			String[] cols = { "","ticketbag_id", "bagcode", "org.org_name",
					"effectivestartdate", "effectiveenddate", "status", "notes" };
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			// sStart : 0 : sAmount : 10 : sCol : 1 : sdir : asc

			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			System.out
					.println("\n \t In ViewWayBills......Prepare...... : sStart : "
							+ sStart
							+ " : sAmount : "
							+ sAmount
							+ " : sCol : "
							+ sCol + " : sdir : " + sdir);

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
			total = ticketbagdao.getTotalBagRecords();
			result = ticketbagdao.getBagData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
						System.out
					.println("REsult of datatable in prepare------>" + result);
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

	public String deleteTicketBag() {
		HttpServletRequest request = ServletActionContext.getRequest();
		TicketBagMasterDao ticketbagdao = new TicketBagMasterDao();

		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ticketbag.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		int shiftid = Integer.parseInt(request.getParameter("ticketbagid"));

		ticketbagdao.deleteTicketBag(Integer.parseInt(request
				.getParameter("ticketbagid")));
		msg = "Ticket Bag  Id " + shiftid + " Deleted successfully";

		return "success";
		}else{
			return "success";
		}
	}

	@SkipValidation
	public String viewTickBag() {
		return "success";
	}

	@SkipValidation
	public String add() {
		return "add";
	}

	
	

	

	
	@SkipValidation
	public String execute() {
		// System.out.println("i am in execute");

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// Thread.sleep(1000);
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketBagMasterDao ticketbagdao = new TicketBagMasterDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			int cnt = ticketbagdao.getTotalBagRecords();
			System.out.println("Count------>" + cnt);
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
				System.out.println("sAmount---" + sAmount);
				sCol = request.getParameter("iSortCol_0");

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("sCol---" + sCol);
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
			total = ticketbagdao.getTotalBagRecords();
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = ticketbagdao.getBagData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}

	@Override
	public void prepare() throws Exception {

		
	}
}