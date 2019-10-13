package com.trimax.its.pass.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.pass.dao.PassInstitutionTypeDAO;
import com.trimax.its.pass.dao.PassIssuerCenterDAO;
import com.trimax.its.pass.model.PassInstitutionType;
import com.trimax.its.pass.model.PassIssuerCenter;

public class PassIssuerCenterAction extends ActionSupport{

	public PassIssuerCenter passissuercenter;
	

	
	public PassIssuerCenter getPassissuercenter() {
		return passissuercenter;
	}
	public void setPassissuercenter(PassIssuerCenter passissuercenter) {
		this.passissuercenter = passissuercenter;
	}
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void PassIssuerCenterview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassIssuerCenterDAO dao= new PassIssuerCenterDAO();
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
			total =dao.getTotalRecordsForPassIssuerCenter(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPassIssuerCenterList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createPassIssuerCenter(){
		return "success";
	}
	public String savecreatepassIssuerCenter(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassIssuerCenterDAO dao = new PassIssuerCenterDAO();
		try{
			System.out.println(passissuercenter.getPass_center_number());
			passissuercenter.setPass_center_name(passissuercenter.getPass_center_name().trim());
			passissuercenter.setPass_center_number(passissuercenter.getPass_center_number());
			passissuercenter.setRemarks(passissuercenter.getRemarks().trim());
			passissuercenter.setCreated_date(new java.util.Date());
			passissuercenter.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//passcastetype.setDeleted_status(0);
			passissuercenter.setUpdated_by(0);
			passissuercenter.setUpdated_date(null);
		
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
		int i=dao.addPassIssuerCenterType(passissuercenter);
		
		if(i>0){
			addActionMessage("Pass Issue Center  id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_center_name","Pass Issue Center already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		
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
		PassIssuerCenterDAO dao = new PassIssuerCenterDAO();
		passissuercenter=dao.getPassIssuerCenterTypeById(id);
		
		return "success";
	}
	
	public String editPassIssuerCenter(){
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
	String status=dc.getStatus(passissuercenter.getPass_issue_center_id(),"passissuercentertype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passissuercenter.getStatus().equals("Y"))){
		
		PassIssuerCenterDAO dao = new PassIssuerCenterDAO();
		
		passissuercenter.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passissuercenter.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassIssuerCenter(passissuercenter);
		
		if(i>0){
			addActionMessage("Pass Issuer Center id "+passissuercenter.getPass_issue_center_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_center_name","Pass Issuer Center already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passissuercenter.getStatus().equals("Inactive"))
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
	public String deletePassIssuerCenterForPass() {
		
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
		PassIssuerCenterDAO dao = new PassIssuerCenterDAO();
		status=dao.deletePassIssuerCenter(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Issuer Center id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Issuer Center id "+id+" deleted successfully.");		
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
			if (passissuercenter.getPass_center_name() == null || passissuercenter.getPass_center_name().trim().equals("")) {
				addFieldError("pass_center_name","Please enter Pass Issuer Center Name");
			}
//			if (passissuercenter.getPass_center_number()=="") {
//				addFieldError("pass_center_number","Please enter Pass Issuer Center Number");
//			}
			
			if (passissuercenter.getStatus() == null || passissuercenter.getStatus().trim().equals("")) {
			addFieldError("getStatus","Please enter Pass Institution Status");
			}
		
//		super.validate();
//		
//		}
	}
	
}
