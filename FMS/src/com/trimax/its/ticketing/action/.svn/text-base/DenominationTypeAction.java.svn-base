package com.trimax.its.ticketing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.dao.ticketbag.dao.TicketBagMasterDao;
import com.trimax.doa.ticketing.model.DenominationType;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.ticketing.dao.DenominationTypeDao;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class DenominationTypeAction extends ActionSupport {
	private String SEARCH_TERM;
	private String requestType;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;

	public DenominationType getDenominationtype() {
		return denominationtype;
	}

	public void setDenominationtype(DenominationType denominationtype) {
		this.denominationtype = denominationtype;
	}

	private DenominationType denominationtype;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	   @SkipValidation
	public String editDenomination() {
		HttpServletRequest request = ServletActionContext.getRequest();
		DenominationTypeDao denomtypedao = new DenominationTypeDao();
		denominationtype = denomtypedao.getDenomTypeDetails(Integer
				.parseInt(request.getParameter("denomtypeid")));
		return "success";
	}
	   @SkipValidation
	public String getAllTicketType() {
		DenominationTypeDao denomtypedao = new DenominationTypeDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = denomtypedao.getTicketTypeId();
		List<String> l2 = denomtypedao.getTicketTypeName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>------select------</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='ticketType" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
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
	   @SkipValidation
	public String createDenomination() {
		   
		   try{
		  denominationtype=new DenominationType();
		   denominationtype.setPercentage(0.0f);
		   denominationtype.setAmount(0);
		   denominationtype.setServicetax("0.00");
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		return "success";
	}
	   @SkipValidation
	public String viewDenom() {
		return "success";
	}

	public String saveEditedDenominationDetails() {
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"denomination.action");
		
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		String denomno = denominationtype.getDenomtype();
		String servicetax = denominationtype.getServicetax();
		//int tickettypeid = denominationtype.getTicketTypeManual().getTicketTypeId();
		String notes = denominationtype.getNotes();

		if (("".equals(denomno)) || (denomno == null)
				|| (denomno.length() == 0)) {

			addFieldError("denomno", "Denomination No is Required");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		
	/*	if (tickettypeid == 0) {
			System.out.println("hi if of old");

			addFieldError("orgName", "Please select Ticket Type");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
*/		DenominationTypeDao denomtypedao = new DenominationTypeDao();
		int denomtypeid = denominationtype.getDenomination_type_id();
		int dupilcatedenomtypeinsertcount = 0;

		/*dupilcatedenomtypeinsertcount = denomtypedao
				.checkForDuplicateDenomTypeInsert(denominationtype);

		if (dupilcatedenomtypeinsertcount == 0) {*/
			denomtypedao.saveEditedDenomTypeDetails(requestType, denomtypeid,
					denominationtype);

			msg = "Denomination Type  Id " + denomtypeid + " Updated Succesfully";
		/*} else {
			msg = "Denomination Type Entry already Exists";
			return "input";
		}*/

		return "success";
		}else{
			return "input";
		}
	}

	public String saveDenominationDetails() {

		/*
		 * shiftType.setScheduletype(new ScheduleType());
		 * shiftType.getScheduletype().setSchedule_type_id(6);
		 */AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			HttpServletRequest request = ServletActionContext.getRequest();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid,"denomination.action");
			
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(create.equalsIgnoreCase("Y")){
				
			
		String denomno = denominationtype.getDenomtype();
		String servicetax = denominationtype.getServicetax();
		int tickettypeid = denominationtype.getTicketTypeManual()
				.getTicketTypeId();
		String notes = denominationtype.getNotes();

		if (tickettypeid == 0) {

			addFieldError("TicketType", "Please select Ticket Type");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		if (tickettypeid != 4) {
			if (("".equals(denomno)) || (denomno == null)
					|| (denomno.length() == 0)) {

				addFieldError("denomno", "Denomination No is Required");
				// msg = " Please Insert Shift Type Details  Again";
				return "input";

			}
			 if(!isInteger(denomno))
		        {
		        	addFieldError("denomno", "Denomination No should be an Integer");
				//	 msg = " Please Insert Shift Type Details  Again";
					return "input";
		        }
		}
       
		if (tickettypeid == 4) {
			denominationtype.setDenomtype("");
		}
	

		/*
		 * if((shiftType.getShift_Type_code()==-1)) {
		 * addFieldError("ShiftType_code", "DutyType should be integer"); return
		 * "input";
		 * 
		 * }
		 */
		int dupilcatedenomtypeinsertcount = 0;
		DenominationTypeDao denomtypedao = new DenominationTypeDao();
		dupilcatedenomtypeinsertcount = denomtypedao
				.checkForDuplicateDenomTypeInsert(denominationtype);

		if (dupilcatedenomtypeinsertcount == 0) {
			int denominsertId = denomtypedao
					.insertDenominationTypeDetails(denominationtype);
			msg = "Denomination Type Id " + denominsertId + " Inserted Succesfully";
		} else {
			msg = "Could not insert Duplicate Denomination Type";
			return "input";
		}

		return "success";
			}else{
				return "input";
			}
	}
	   @SkipValidation
	public String deleteDenominationDetails() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		//HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"denomination.action");
		
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		DenominationTypeDao denomtypedao = new DenominationTypeDao();
		int denomtypeid = Integer.parseInt(request.getParameter("denomtypeid"));
		DependencyChecker dc=new DependencyChecker();
        String status=dc.getStatus(denomtypeid,"denomination_type_manual");
        
       if(status.equals("")){
		denomtypedao.deleteDenominationType(Integer.parseInt(request
				.getParameter("denomtypeid")));
		msg = "Denom Type  Id " + denomtypeid + " Deleted successfully";
       }else{
           addActionError(status);
           //returnResult = "success";
       }  
		return "success";
		}else{
			return "success";
		}
	}

	public String editDenominationDetails() {
		return "success";
	}
    @SkipValidation
	public String showDenom() {
		// System.out.println("i am in execute");

		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			// Thread.sleep(1000);
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			DenominationTypeDao denomtypedao = new DenominationTypeDao();
			//int cnt = denomtypedao.getTotalDenominationTypeRecords(total, request,cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			//System.out.println("Count------>" + cnt);
			String[] cols = { "","denomination_type_id", "denomtype","ticketTypeManual.ticketTypeId",
					"status","percentage","amount", "servicetax","notes" };

			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");
			//String sdir = request.getParameter("sSortDir_0");
			/*try {
				sStart = request.getParameter("iDisplayStart");
				sAmount = request.getParameter("iDisplayLength");
				System.out.println("sAmount---" + sAmount);
				sCol = request.getParameter("iSortCol_0");

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("sCol---" + sCol);
			String sdir = request.getParameter("sSortDir_0");*/
			

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
			total = denomtypedao.getTotalDenominationTypeRecords(total, request,cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = denomtypedao.getDenominationTypeData(total, request,cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			out.print(result);
		} catch (Exception ex) {
		} finally {

		}
		return null;
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
		/*if(denominationtype.getAmount()==0 && denominationtype.getPercentage()==0)
		{
			addFieldError("percentage", "Lumpsum Amount OR Percentage   is Required");
//			addActionError("DeviceType Name is Required");
			denominationtype.setAmount(denominationtype.getAmount());
			
		}*/
		if(denominationtype.getPercentage()<0)
		{
			addFieldError("percentage", "Please Enter Valid Incentive Percentage");
			denominationtype.setPercentage(denominationtype.getPercentage());
		}
		if(denominationtype.getAmount()<0){
			addFieldError("lumpsum_amount", "Please Enter Valid Incetive Amount");
			denominationtype.setPercentage(denominationtype.getAmount());
		}
		if(denominationtype.getAmount()>0 && denominationtype.getPercentage()>0)
		{
			addFieldError("percentage", "You Can Only Enter Incentive Amount OR Incentive Percentage ");
//			addActionError("DeviceType Name is Required");
			denominationtype.setAmount(denominationtype.getAmount());
			
		}
		if(denominationtype.getServicetax().equalsIgnoreCase("")){
			addFieldError("servicetax", "Please Enter valid Service Tax amount");
			denominationtype.setServicetax(denominationtype.getServicetax());
		}
		
		if(denominationtype.getPercentage()!=0 )
		{
			Float f=denominationtype.getPercentage();
			String percent=f.toString().trim();
			if((percent.indexOf("."))== 4 ||(percent.indexOf(".")) == 0)
			{
			addFieldError("percentage", "please Enter Correct Percentage Number ");
			denominationtype.setPercentage(denominationtype.getPercentage());
							
			}
			else if((percent.length())>=3  && ((percent.indexOf("."))==5||(percent.indexOf("."))==4)){
				addFieldError("percentage", "please Enter Correct Percentage Number ");
				
			}
			else if((percent.length())>5  ){
				
				addFieldError("percentage", "please Enter Correct Percentage Number ");
				
			}
			else if(f>100 ){
				
				addFieldError("percentage", "please Enter Correct Percentage Number ");
				
			}
			
			else if((percent.equals("100.0")&&( percent.indexOf("."))!= 3)){
				addFieldError("percentage", "please Enter Correct Percentage Number ");
				
			}
			
		else{
			//System.out.println("inside percentage else");
			denominationtype.setPercentage(denominationtype.getPercentage());
			}
		}
		
	}
}
