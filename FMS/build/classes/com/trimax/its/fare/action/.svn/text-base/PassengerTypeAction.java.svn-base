package com.trimax.its.fare.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.fare.dao.PassengerTypeDao;
import com.trimax.its.fare.model.PassengerType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PassengerTypeAction extends ActionSupport{
	private PassengerType passengerType;
	private int pid;
	
	
	public PassengerType getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(PassengerType passengerType) {
		this.passengerType = passengerType;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@SkipValidation
	public String view() {
		return "view";
	}
	
	@SkipValidation
	public String add() {
		return "add";
	}
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String para=request.getParameter("pid");
		
		if(para!=null && para.isEmpty()){
		pid=Integer.parseInt(para);
		}
		
		Common common=new Common();
		
		PassengerTypeDao dao=new PassengerTypeDao();
		passengerType=dao.getPassengerTypeById(pid);
		
		passengerType.setEffectiveStartDate(common.getDateFromDbToPicker(passengerType.getEffectiveStartDate()));
				
		if(passengerType.getEffectiveEndDate()!=null){
		 passengerType.setEffectiveEndDate(common.getDateFromDbToPicker(passengerType.getEffectiveEndDate()));
		}
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String para=request.getParameter("pid");
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassengerTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(para!=null && para.isEmpty()){
		pid=Integer.parseInt(para);
		}
		if(delete.equalsIgnoreCase("Y"))
		{
		 DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(pid, "passenger");
		System.out.println("status---"+status);
		
		if(status.equals("")){
		
		
		PassengerTypeDao dao=new PassengerTypeDao();
		String s=dao.deletePassengerType(pid);
	

		if(s.equals("success")){
			addActionMessage("Passenger Type id "+pid+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
		}else{
			addActionError(status);
		}
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassengerTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		//compare start & end dates
		try{
//			if(passengerType.getEffectiveEndDate().trim().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date1=sdf.parse(passengerType.getEffectiveStartDate());
				Date date2=sdf.parse(passengerType.getEffectiveEndDate());
				
				if(date1.compareTo(date2)>0){
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		
	        		return INPUT;
	        		
	        	}else if(date1.compareTo(date2)==0){
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		return INPUT;
	        		
	        	}
//			}
			}catch(Exception e){e.printStackTrace();}
		
		
		PassengerTypeDao dao=new PassengerTypeDao();
		int i=dao.addPassenger(passengerType);
		
		if(i>0){
			addActionMessage("Passenger Type id "+i+" created successfully.");
		}else{
			if(i==-2){
				addActionError("Passenger Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		//execute();
		return SUCCESS;}
		else{
			return INPUT;
		}
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassengerTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		//compare start & end dates
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(passengerType.getId(),"passenger");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  passengerType.getStatus().equals("Active"))){	
		try{
//					if(passengerType.getEffective_end_date().trim().length()>0){
						
						//validate str& end date
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date1=sdf.parse(passengerType.getEffectiveStartDate());
			Date date2=sdf.parse(passengerType.getEffectiveEndDate());
						
			if(date1.compareTo(date2)>0){
			  addActionError("Effective End Date should be greater than Effective Start Date");
			 return INPUT;	
			}else if(date1.compareTo(date2)==0){
			    addActionError("Effective End Date should be greater than Effective Start Date");
			    return INPUT;
			}
//					}
			}catch(Exception e){e.printStackTrace();}
		
		PassengerTypeDao dao=new PassengerTypeDao();

		int i=dao.updatePassenger(passengerType);
		
		if(i>0){
			addActionMessage("Passenger Type id "+passengerType.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addActionError("Passenger Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		}else
		{
			
			if(passengerType.getStatus().equals("Inactive"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		//execute();
		return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public void validate(){
	
		if(passengerType.getPassengerTypeName()==null || passengerType.getPassengerTypeName().trim().isEmpty()){
			addFieldError("passengerTypeName","Please enter Passenger Type");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(passengerType.getPassengerTypeName())){
				addFieldError("passengerTypeName","Special characters not allowed"); 
			 }
		}	
		
		if(passengerType.getEffectiveStartDate()==null || passengerType.getEffectiveStartDate().isEmpty()){
			addFieldError("effectiveStartDate","Please select Effective Start Date");
		}
		
		if(passengerType.getEffectiveEndDate()==null || passengerType.getEffectiveEndDate().isEmpty()){
			addFieldError("effectiveEndDate","Please select Effective End Date");
		}
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			PassengerTypeDao dao=new PassengerTypeDao();

			String[] dbcol={"","id","passengerTypeName","effectiveStartDate","effectiveEndDate","status"};

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
				if (sdir.equals("asc"))
					dir = "asc";
				else
					dir = "desc";
			}


			int total = -1;
			total = dao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[Integer.parseInt(sCol)],dir,viewdeletedrecord);

			out.print(result);
			
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}
}
