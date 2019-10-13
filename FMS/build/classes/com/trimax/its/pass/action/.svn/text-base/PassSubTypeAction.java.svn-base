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
import com.trimax.its.pass.dao.PassPurchaseTypeDAO;
import com.trimax.its.pass.dao.PassSubTypeDAO;
import com.trimax.its.pass.model.PassPurchaseType;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.transport.dao.ScheduleDAO;

public class PassSubTypeAction extends ActionSupport{
	
	
public PassSubType passsubtype;

	
private Map<Integer, String> passtypelist; 

	public Map<Integer, String> getPasstypelist() {
	return passtypelist;
}
public void setPasstypelist(Map<Integer, String> passtypelist) {
	this.passtypelist = passtypelist;
}
	public PassSubType getPasssubtype() {
	return passsubtype;
	}
	public void setPasssubtype(PassSubType passsubtype) {
		this.passsubtype = passsubtype;
	}
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		PassSubTypeDAO dao= new PassSubTypeDAO();
		passtypelist= dao.getPassTypeData();
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void PassSubTypeview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassSubTypeDAO dao = new PassSubTypeDAO();
			//System.out.println("hiiiiiiii");
			String[] dbcol={"","pass_subtype_id","sub_type_name","pass_type_name","status","remarks"};
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
			total =dao.getTotalRecordsForPassSubType(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPassSubTypeList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createPassSubType(){
		PassSubTypeDAO dao=new PassSubTypeDAO();
		passtypelist= dao.getPassTypeData();
		return "success";
	}
	public String savecreatepassSubType(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassSubTypeDAO dao=new PassSubTypeDAO();
		passtypelist= dao.getPassTypeData();
		try{
			passsubtype.setSub_type_name(passsubtype.getSub_type_name().trim());
			passsubtype.setPass_type_id(passsubtype.getPass_type_id());
			passsubtype.setRemarks(passsubtype.getRemarks().trim());
			passsubtype.setStatus(passsubtype.getStatus());
			passsubtype.setCreated_date(new java.util.Date());
			passsubtype.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			passsubtype.setUpdated_by(0);
			passsubtype.setUpdated_date(null);
		
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
		int i=dao.addPassSubtype(passsubtype);
		
		if(i>0){
			addActionMessage("Pass Sub Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("sub_type_name","Pass Sub Type already exist.");
				passtypelist= dao.getPassTypeData();
				return INPUT;
			}else{
				passtypelist= dao.getPassTypeData();
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
		PassSubTypeDAO dao=new PassSubTypeDAO();
		passsubtype=dao.getPassSubTypeById(id);
		passtypelist= dao.getPassTypeData();
		return "success";
	}
	
	public String editPassSubType(){
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
		PassSubTypeDAO dao=new PassSubTypeDAO();
	DependencyChecker dc=new DependencyChecker();
	String status=dc.getStatus(passsubtype.getPass_subtype_id(),"passsubtype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passsubtype.getStatus().equals("Active"))){
		
		
		
		passsubtype.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passsubtype.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassSubType(passsubtype);
		
		if(i>0){
			addActionMessage("Pass Sub Type id "+passsubtype.getPass_subtype_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("sub_type_name","Pass Sub Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passsubtype.getStatus().equals("Inactive"))
		{
			//setUpdatestaus("success");
		addActionError(status);
		passtypelist= dao.getPassTypeData();
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
	public String deletePassSubType() {
		
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
		PassSubTypeDAO dao=new PassSubTypeDAO();
		status=dao.deletePassSubType(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Sub Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Sub Type id "+id+" deleted successfully.");		
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
			if (passsubtype.getSub_type_name() == null || passsubtype.getSub_type_name().trim().equals("")) {
				addFieldError("sub_type_name","Please enter Pass Sub Type Name");
			}
			
			if (passsubtype.getStatus() == null || passsubtype.getStatus().trim().equals("")) {
			addFieldError("status","Please enter Pass SubType Status");
			}
			if (passsubtype.getPass_type_id() == 0 ) {
				addFieldError("pass_type_id","Please enter Pass Type");
				}
		
			if(hasFieldErrors()){
				PassSubTypeDAO dao=new PassSubTypeDAO();
				passtypelist= dao.getPassTypeData();
				}
//		super.validate();
//		
//		}
	}

}
