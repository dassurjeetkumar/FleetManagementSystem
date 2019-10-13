package com.trimax.its.pass.action;


import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.pass.dao.PassTypeDao;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PassTypeAction extends ActionSupport implements Preparable{
	
	private PassType passType;
	
	public PassType getPassType() {
		return passType;
	}

	public void setPassType(PassType passType) {
		this.passType = passType;
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
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		PassTypeDao dao=new PassTypeDao();
		passType=dao.getPassTypeById(id);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		String status="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		int id=Integer.parseInt(request.getParameter("id"));
		if(delete.equalsIgnoreCase("Y")){
		PassTypeDao dao=new PassTypeDao();
		status=dao.deletePassType(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Type id "+id+" deleted successfully.");		
			status="success";
			
			
		
		} else {
			addActionError(status);
			status= "success";
		}

//		if(s.equals("success")){
//			addActionMessage("Pass Type id "+id+" deleted successfully.");
//		}else{
//			addActionMessage("Database error retry again.");
//		}
//		
		return "success";}
		else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PassTypeDao dao=new PassTypeDao();
		
		passType.setPass_type_name(passType.getPass_type_name().trim());
		passType.setNote(passType.getNote().trim());
		passType.setCreated_date(new java.util.Date());
		passType.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passType.setDeleted_status(0);
		passType.setUpdated_by(0);
		passType.setUpdated_date(null);
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		
		if(create.equalsIgnoreCase("Y")){
		int i=dao.addPassType(passType);
		
		if(i>0){
			addActionMessage("Pass Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_type_name","Pass Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		
		//execute();
		return SUCCESS;
		}else{
			return INPUT;
		}
	}
	

	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
	DependencyChecker dc=new DependencyChecker();
	String status=dc.getStatus(passType.getPass_type_id(),"passtype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passType.getStatus().equals("Active"))){
		
		PassTypeDao dao=new PassTypeDao();
		
		passType.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passType.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassType(passType);
		
		if(i>0){
			addActionMessage("Pass Type id "+passType.getPass_type_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_type_name","Pass Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passType.getStatus().equals("Inactive"))
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
	
		
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			PassTypeDao passTypedao = new PassTypeDao();

			String[] dbcol={"","pass_type_id","pass_type_name","status","pass_type_id"};
			
			
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
					dir = "desc";
			}

			//String colName = cols[col];
			int total = -1;
			total = passTypedao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = passTypedao.getData(total, request,dbcol[Integer.parseInt(sCol)],dir,viewdeletedrecord);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}
	
	@SkipValidation
	public String ViewPassType(){
		
		return "success";
	}
	
	public void prepare() throws Exception {
		/*FareChartMasterDao fareChartdao = new FareChartMasterDao();
		serviceTypeMap=fareChartdao.getServiceType();*/
	}
	
	public void validate(){
		System.out.println("pt="+passType.getPass_type_name()+":"+passType.getNote());
		if(passType.getPass_type_name()==null || passType.getPass_type_name().trim().isEmpty()){
			addFieldError("pass_type_name","Please enter Pass Type Name");
		}else{

			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(passType.getPass_type_name())){
				addFieldError("pass_type_name","Special characters not allowed"); 
			 }
		}	
		
		
		
	}
}
