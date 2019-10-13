package com.trimax.its.pass.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.pass.dao.PassCasteTypeDAO;
import com.trimax.its.pass.model.PassCasteType;

public class CasteTypeAction extends ActionSupport{
	public PassCasteType passcastetype;

	public PassCasteType getPasscastetype() {
		return passcastetype;
	}

	public void setPasscastetype(PassCasteType passcastetype) {
		this.passcastetype = passcastetype;
	}
	
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void passCasteTypeActionview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassCasteTypeDAO dao= new PassCasteTypeDAO();
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
			total =dao.getTotalRecordsForPassCaste(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPassCasteList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createPassCaste(){
		return "success";
	}
	public String savecreatepasscaste(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassCasteTypeDAO dao = new PassCasteTypeDAO();
		try{
			System.out.println(passcastetype.getPass_cast_name().trim());
			passcastetype.setPass_cast_name(passcastetype.getPass_cast_name().trim());
			passcastetype.setRemarks(passcastetype.getRemarks().trim());
			passcastetype.setCreated_date(new java.util.Date());
			passcastetype.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			//passcastetype.setDeleted_status(0);
			passcastetype.setUpdated_by(0);
			passcastetype.setUpdated_date(null);
		
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
		int i=dao.addPassCasteType(passcastetype);
		
		if(i>0){
			addActionMessage("Pass Caste Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_cast_name","Pass Caste Type already exist.");
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
		PassCasteTypeDAO dao = new PassCasteTypeDAO();
		passcastetype=dao.getPassCasteTypeById(id);
		
		return "success";
	}
	
	public String editPassCaste(){
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
	String status=dc.getStatus(passcastetype.getPass_cast_id(),"passcastetype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passcastetype.getStatus().equals("Y"))){
		
		PassCasteTypeDAO dao = new PassCasteTypeDAO();
		
		passcastetype.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passcastetype.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassCasteType(passcastetype);
		
		if(i>0){
			addActionMessage("Pass Caste Type id "+passcastetype.getPass_cast_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_cast_name","Pass Caste Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passcastetype.getStatus().equals("Inactive"))
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
	public String deletePassCasteforsmartcard() {
		
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
//		if(delete.equalsIgnoreCase("Y")){
		PassCasteTypeDAO dao = new PassCasteTypeDAO();
		status=dao.deletePassCaste(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Caste Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Caste Type id "+id+" deleted successfully.");		
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
			if (passcastetype.getPass_cast_name() == null || passcastetype.getPass_cast_name().trim().equals("")) {
				addFieldError("pass_cast_name","Please enter Pass Caste Type");
			}
			
			if (passcastetype.getStatus() == null || passcastetype.getStatus().trim().equals("")) {
			addFieldError("getStatus","Please enter Pass Caste Status");
			}
		
//		super.validate();
//		
//		}
	}


}
