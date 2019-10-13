package com.trimax.its.pass.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.pass.dao.PassCounterMasterDAO;
import com.trimax.its.pass.dao.PassIssuerCenterDAO;
import com.trimax.its.pass.model.PassCounterMaster;
import com.trimax.its.pass.model.PassIssuerCenter;
import com.trimax.its.transport.dao.ScheduleDAO;

public class PassCounterMasterAction extends ActionSupport {
	
public PassCounterMaster passcountermaster;
	
private Map<Integer, String> centerissuerlist; 
	
	
	public Map<Integer, String> getCenterissuerlist() {
	return centerissuerlist;
}
public void setCenterissuerlist(Map<Integer, String> centerissuerlist) {
	this.centerissuerlist = centerissuerlist;
}
	public PassCounterMaster getPasscountermaster() {
	       return passcountermaster;
       }
	public void setPasscountermaster(PassCounterMaster passcountermaster) {
		this.passcountermaster = passcountermaster;
	}
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		PassCounterMasterDAO dao= new PassCounterMasterDAO();
		centerissuerlist= dao.getCenterIssuerType();
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void PassCounterMasterview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassCounterMasterDAO dao= new PassCounterMasterDAO();
			String[] dbcol={"","ID","NAME","STATUS","Remarks"};
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
			int total = -1;
			String SEARCH_TERM = request.getParameter("sSearch");
			total =dao.getTotalRecordsForPasscounterMaster(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPasscounterMasterCenterList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createPassCounterMaster(){
		PassCounterMasterDAO dao= new PassCounterMasterDAO();
		centerissuerlist= dao.getCenterIssuerType();
		return "success";
	}
	public String savecreatepassCounterMaster(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassCounterMasterDAO dao = new PassCounterMasterDAO();
		try{
			//System.out.println(passissuercenter.getPass_center_number());
			passcountermaster.setPass_counter_name(passcountermaster.getPass_counter_name().trim());
			passcountermaster.setPass_issue_center_id(passcountermaster.getPass_issue_center_id());
			passcountermaster.setTerminal_no(passcountermaster.getTerminal_no());
			passcountermaster.setPass_counter_number(passcountermaster.getPass_counter_number());
			passcountermaster.setRemarks(passcountermaster.getRemarks().trim());
			passcountermaster.setCreated_date(new java.util.Date());
			passcountermaster.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//passcastetype.setDeleted_status(0);
			passcountermaster.setUpdated_by(0);
			passcountermaster.setUpdated_date(null);
		
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassIssuerTypeAction!view");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
		
//		if(create.equalsIgnoreCase("Y")){
		int i=dao.addPassCounterMasterType(passcountermaster);
		
		if(i>0){
			addActionMessage("Pass Issue Center  id "+i+" created successfully.");
		}else{
			if(i==-1){
				centerissuerlist= dao.getCenterIssuerType();
				addFieldError("pass_center_name","Pass Issue Center already exist.");
				return INPUT;
			}else{
				centerissuerlist= dao.getCenterIssuerType();
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		centerissuerlist= dao.getCenterIssuerType();
		//execute();
		return SUCCESS;
//		}else{
//			return INPUT;
//		}
		 
	
		//return "success";
	}
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int id=Integer.parseInt(request.getParameter("passid"));
		System.out.println("id=="+id);
		PassCounterMasterDAO dao = new PassCounterMasterDAO();
		centerissuerlist= dao.getCenterIssuerType();
		passcountermaster=dao.getPassCounterMasterById(id);
		
		return "success";
	}
	
	public String editPassCounterMaster(){
		HttpServletRequest request = ServletActionContext.getRequest();
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
//		if(edit.equalsIgnoreCase("Y")){
	DependencyChecker dc=new DependencyChecker();
	String status=dc.getStatus(passcountermaster.getPass_issue_counter_id(),"passcountermastertype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passcountermaster.getStatus().equals("Y"))){
		
		PassCounterMasterDAO dao = new PassCounterMasterDAO();
		
		passcountermaster.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passcountermaster.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassCounterMaster(passcountermaster);
		
		if(i>0){
			addActionMessage("Pass Issue Counter id "+passcountermaster.getPass_issue_counter_id()+" updated successfully.");
		}else{
			if(i==-1){
				centerissuerlist= dao.getCenterIssuerType();
				addFieldError("pass_counter_name","Pass Issuer Counter already exist.");
				return INPUT;
			}else{
				centerissuerlist= dao.getCenterIssuerType();
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passcountermaster.getStatus().equals("Inactive"))
		{
			//setUpdatestaus("success");
		addActionError(status);
		return "input";
		}	
		
	}
		//execute();
		return SUCCESS;
//		}else{
//			return INPUT;
//		}
	}
	
	
	@SkipValidation
	public String deletePassCounterMasterForPass() {
		
		String status="";
		HttpServletRequest request = ServletActionContext.getRequest();
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
		int id=Integer.parseInt(request.getParameter("passid"));
		System.out.println("id==="+id);
//		if(delete.equalsIgnoreCase("Y")){
		PassCounterMasterDAO dao = new PassCounterMasterDAO();
		status=dao.deletePassCounterMaster(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Issue Counter id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Issue Counter id "+id+" deleted successfully.");		
			status="success";
			
			
		
		} else {
			addActionError(status);
			status= "success";
		}

		return "success";
//		}
//		else{
//			addActionMessage("Access Denied - User Does Not Have Access Privileges");
//			return "success";
//		}
	}
	
	private int createPassIssuerType;
	
	public int getCreatePassIssuerType() {
		return createPassIssuerType;
	}
	public void setCreatePassIssuerType(int createPassIssuerType) {
		this.createPassIssuerType = createPassIssuerType;
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		
//		if(getCreatePassIssuerType()!=0){
			if (passcountermaster.getPass_counter_name() == null || passcountermaster.getPass_counter_name().trim().equals("")) {
				addFieldError("pass_counter_name","Please enter Pass Counter Master Name");
			}
			if (passcountermaster.getPass_counter_number()==null || passcountermaster.getPass_counter_number().trim().equals("")) {
				addFieldError("pass_center_number","Please enter Pass Counter Number");
			}
			
			if (passcountermaster.getTerminal_no() == null || passcountermaster.getTerminal_no().trim().equals("")) {
			addFieldError("terminal_no","Please enter Terminal No");
			}
			if (passcountermaster.getPass_issue_center_id() == null || passcountermaster.getPass_issue_center_id().trim().equals("")) {
				addFieldError("pass_issue_center_id","Please enter Center Name");
				}
			
			if(hasFieldErrors()){
				PassCounterMasterDAO dao = new PassCounterMasterDAO();
				 centerissuerlist= dao.getCenterIssuerType();
					
				}
		
//		super.validate();
//		
//		}
	}

}
