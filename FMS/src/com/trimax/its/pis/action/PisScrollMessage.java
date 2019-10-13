package com.trimax.its.pis.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.pis.dao.PisDisplayMappingDao;
import com.trimax.its.pis.dao.PisScrollMessageDao;
import com.trimax.its.pis.model.PisScrollMessageModel;

public class PisScrollMessage extends ActionSupport {
	private PisScrollMessageModel pisScrollMessageModel;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PisScrollMessageModel getPisScrollMessageModel() {
		return pisScrollMessageModel;
	}

	public void setPisScrollMessageModel(
			PisScrollMessageModel pisScrollMessageModel) {
		this.pisScrollMessageModel = pisScrollMessageModel;
	}

	public String viewPisScrollMessage() {
		return "success";
	}

	private String requestType;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String createScrollMessage() {
		return "success";
	}

	/**
	 * 
	 * @deleteScrollMessage
	 */
	public String deleteScrollMessage() {

		HttpServletRequest request = ServletActionContext.getRequest();
		PisScrollMessageDao pisscrollmessagedao = new PisScrollMessageDao();
		int displaymappid = Integer.parseInt(request
				.getParameter("scrollmessgid"));

		pisscrollmessagedao.deleteScrollMessage(Integer.parseInt(request
				.getParameter("scrollmessgid")));
		msg = "Scroll Message  Id " + displaymappid + " Deleted successfully";

		return "success";
	}

	/**
	 *  
	 * @editScrollMessage
	 */
	public String editScrollMessage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		PisScrollMessageDao pisscrollmessagedao = new PisScrollMessageDao();
		pisScrollMessageModel = pisscrollmessagedao
				.getScrollMessageDetails(Integer.parseInt(request
						.getParameter("id")));
		
		
		return "success";
	}

	/**
	 * 
	 * @updateScrollMessage
	 */
	public String updateScrollMessage() {
		
		int id = pisScrollMessageModel.getPis_id();
		String scrollMessage = pisScrollMessageModel.getMessage().trim();
		int displayunitid = pisScrollMessageModel.getScroll_display_unit().getPis_display_unit_id();
		int busstandid = pisScrollMessageModel.getBusstand().getOrg_chart_id();
		String fontcolor=pisScrollMessageModel.getFont_color();
		String backgroundcolor=pisScrollMessageModel.getBackground_color();
		if (("".equals(scrollMessage)) || (scrollMessage == null)
				|| (scrollMessage.length() == 0)) {

			addFieldError("message", "Scroll Message Message is Manadatory");
			return "input";

		}
		
		if (("".equals(fontcolor)) || (fontcolor == null)
				|| (fontcolor.length() == 0)) {

			addFieldError("font_color", "Scroll Message Font Color is Manadatory");
			return "input";

		}
		if (("".equals(backgroundcolor)) || (backgroundcolor == null)
				|| (backgroundcolor.length() == 0)) {

			addFieldError("background_color", "Scroll Message Background Color is Manadatory");
			return "input";

		}

		
		CommonValidation cm = new CommonValidation();
		/*if (!cm.isSpecialChar(scrollMessage)) {
			addFieldError("message",
					"Scroll Message is Should be a alphanumeric");
			return "input";
		}*/
		if (busstandid == 0) {
			

			addFieldError("busstandid",
					"Scroll Message Bus Stand Id is Manadatory");
			// msg = " Please Insert Shift Type Details  Again"; 
			return "input";

		}

	if (displayunitid == 0) {

			addFieldError("displayunit",
					"Scroll Message Display Unit is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		String date1 = pisScrollMessageModel.getEffective_date();
		String date2 = pisScrollMessageModel.getEnd_date();
		
		try{
			 if(pisScrollMessageModel.getEffective_date()==null || (pisScrollMessageModel.getEffective_date().equals("")) || pisScrollMessageModel.getEffective_date().length()<=0){
					
	            	addFieldError("startdate",
	    					"Scroll Message Effective Date is Manadatory");
	    			 
	    			return "input";
				}
			if(pisScrollMessageModel.getEnd_date().trim().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date effstdate=sdf.parse(pisScrollMessageModel.getEffective_date());
				Date effenddate=sdf.parse(pisScrollMessageModel.getEnd_date());
				
				if(effstdate.compareTo(effenddate)>0){
	        	
	        		addActionError("End Date should be greater than Start Date");
	        		
	        		return INPUT;
	        		
	        	}/*else if(effstdate.compareTo(effenddate)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		return INPUT;
	        		
	        	}*/
			}
			}catch(Exception e){e.printStackTrace();}
		PisScrollMessageDao pisscrollmessagedao = new PisScrollMessageDao();
		int duplicateeditcount = 0;
		if (scrollMessage != null) {
			duplicateeditcount = pisscrollmessagedao.checkForDuplicateScrollMessage(
					scrollMessage, id);
			
		}
		if (duplicateeditcount== 0) {
		pisscrollmessagedao.saveEditedDisplayMappingDetails(requestType, id,
				pisScrollMessageModel);

		msg = "Scroll Message  Id " + id + " Updated Succesfully";
		
	} else {
		msg = "Scroll Message   already exists";
		return "input";
	}
		return "success";
	}

	/**
	 * 
	 * @saveScrollMessage
	 */
	public String saveScrollMessage() {
		System.out.println("**********inside save************");

		String scrollMessage = pisScrollMessageModel.getMessage().trim();
		int displayunitid = pisScrollMessageModel.getScroll_display_unit().getPis_display_unit_id();
		int busstandid = pisScrollMessageModel.getBusstand().getOrg_chart_id();
		String fontcolor=pisScrollMessageModel.getFont_color();
		String backgroundcolor=pisScrollMessageModel.getBackground_color();
		if (("".equals(scrollMessage)) || (scrollMessage == null)
				|| (scrollMessage.length() == 0)) {

			addFieldError("message", "Scroll Message Message is Manadatory");
			return "input";

		}
		if (("".equals(fontcolor)) || (fontcolor == null)
				|| (fontcolor.length() == 0)) {

			addFieldError("font_color", "Scroll Message Font Color is Manadatory");
			return "input";

		}
		if (("".equals(backgroundcolor)) || (backgroundcolor == null)
				|| (backgroundcolor.length() == 0)) {

			addFieldError("background_color", "Scroll Message Background Color is Manadatory");
			return "input";

		}

	
		CommonValidation cm = new CommonValidation();
		/*if (!cm.isSpecialChar(scrollMessage)) {
			addFieldError("message",
					"Scroll Message is Should be a alphanumeric");
			return "input";
		}*/
		if (busstandid == 0) {
			

			addFieldError("busstandid",
					"Scroll Message Bus Stand Id is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}

		if (displayunitid == 0) {

			addFieldError("displayunit",
					"Scroll Message Display Unit is Manadatory");
			// msg = " Please Insert Shift Type Details  Again";
			return "input";

		}
		String date1 = pisScrollMessageModel.getEffective_date();
		String date2 = pisScrollMessageModel.getEnd_date();
	
		try{

           

			 if(pisScrollMessageModel.getEffective_date()==null || (pisScrollMessageModel.getEffective_date().equals("")) || pisScrollMessageModel.getEffective_date().length()<=0){
					
	            	addFieldError("startdate",
	    					"Scroll Message Effective Date is Manadatory");
	    			 
	    			return "input";
				}

			if(pisScrollMessageModel.getEnd_date().trim().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date effstdate=sdf.parse(pisScrollMessageModel.getEffective_date());
				Date effenddate=sdf.parse(pisScrollMessageModel.getEnd_date());
				
				if(effstdate.compareTo(effenddate)>0){
	        		
	        		addActionError("End Date should be greater than Start Date");
	        		
	        		return INPUT;
	        		
	        	}/*else if(effstdate.compareTo(effenddate)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		return INPUT;
	        		
	        	}*/
			}
			}catch(Exception e){e.printStackTrace();}

		PisScrollMessageDao pisscrollmessagedao = new PisScrollMessageDao();
		int duplicateinsertcount = 0;
		if ((!"".equals(scrollMessage)) || (scrollMessage != null)
				|| (scrollMessage.length() != 0))
		{
			duplicateinsertcount = pisscrollmessagedao.checkForDuplicateScrollMessageInsert(pisScrollMessageModel);
			
			
		}
		if(duplicateinsertcount==0)
		{
		int insertid = pisscrollmessagedao.insertPisScrollMessageModelDetails(pisScrollMessageModel);
		msg = "Scroll Message Id " + insertid + " Inserted Succesfully";
		}
		else
		{
			msg = "Scroll Message already Exists";
			return "input";
		}
		return "success";
	}

	/**
	 * @showScrollMessageList
	 * @return
	 * @throws IOException
	 */
	public String showScrollMessageList() throws IOException {
		System.out
				.println("\n \t ************Start of showScrollMessageList*****************");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PisScrollMessageDao pisscrolldao = new PisScrollMessageDao();
		JSONObject result = new JSONObject();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		try {


			
			String[] cols = { "", "pis_id", "bus_stand_id","display_unit_id",
					"message","font_color","background_color","effective_date", "end_date" };

			// int cnt =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			// int cnt = orgdao.getTotalRecordsOfWayBill();
			// System.out.println("Count------>" + cnt);
			
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			// sStart : 0 : sAmount : 10 : sCol : 1 : sdir : asc

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

			int total = -1;
			// total =
			// vehiclealertconfigdao.getTotalVehicleAlertConfigDataRecords();
			result = pisscrolldao.getPisScrollMessageData(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			
			out.print(result);
		} catch (Exception e) {
			System.out
					.println("\n \t Exception in prepare showScrollMessageList : "
							+ e.getMessage());
			e.printStackTrace();
		} finally {
			
		}

		return null;

	}
	
	/**
	 * Find all Bus Stations
	 * 
	 * @return
	 */
	public String findDisplayUnit() {
		PisScrollMessageDao pisscrolldao = new PisScrollMessageDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = pisscrolldao.getDisplayUnitId();
		
		List<String> l2 = pisscrolldao.getDisplayUnitName();
		String regionTypeAjaxString = "";
		
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='display_unit" + l1.get(i)
					+ "' value=" + l1.get(i).toString() + ">"
					+ l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
	System.out.println("regiontypestring is..."+regionTypeAjaxString);
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
}
