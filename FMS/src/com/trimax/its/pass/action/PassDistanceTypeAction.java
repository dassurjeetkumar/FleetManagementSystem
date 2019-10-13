package com.trimax.its.pass.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.pass.dao.PassDistanceTypeDAO;
import com.trimax.its.pass.dao.PassIssuerTypeDAO;
import com.trimax.its.pass.model.PassDistanceType;
import com.trimax.its.pass.model.PassIssuerType;

public class PassDistanceTypeAction extends ActionSupport {

	public PassDistanceType passdistance;

	public PassDistanceType getPassdistance() {
		return passdistance;
	}

	public void setPassdistance(PassDistanceType passdistance) {
		this.passdistance = passdistance;
	}
	
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void passdistnacetypeview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassDistanceTypeDAO dao=new PassDistanceTypeDAO();
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
			total =dao.getTotalRecordsForPassDistance(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPassDistanceList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createpassdistance(){
		return "success";
	}
	public String savecreatepassdistance(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassDistanceTypeDAO dao=new PassDistanceTypeDAO();
		try{
			System.out.println(passdistance.getPass_distance_name().trim());
			passdistance.setPass_distance_name(passdistance.getPass_distance_name().trim());
			passdistance.setRemarks(passdistance.getRemarks().trim());
			passdistance.setCreated_date(new java.util.Date());
			passdistance.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			passdistance.setDeleted_status(0);
			passdistance.setUpdated_by(0);
			passdistance.setUpdated_date(null);
		
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
		int i=dao.addPassDistanceType(passdistance);
		
		if(i>0){
			addActionMessage("Pass Distance Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_distance_name","Pass Distance Type already exist.");
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
		PassDistanceTypeDAO dao=new PassDistanceTypeDAO();
		passdistance=dao.getPassDistanceTypeById(id);
		
		return "success";
	}
	
	public String editPassDistance(){
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
	String status=dc.getStatus(passdistance.getPass_distance_id(),"passdistancetype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passdistance.getStatus().equals("Active"))){
		
		PassDistanceTypeDAO dao=new PassDistanceTypeDAO();
		
		passdistance.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passdistance.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassDistanceType(passdistance);
		
		if(i>0){
			addActionMessage("Pass Distance Type id "+passdistance.getPass_distance_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_distance_name","Pass Distance Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passdistance.getStatus().equals("Inactive"))
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
	public String deletePassDistanceforsmartcard() {
		
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
		PassDistanceTypeDAO dao=new PassDistanceTypeDAO();
		status=dao.deletePassDistanceType(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Distance Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Distance Type id "+id+" deleted successfully.");		
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
			if (passdistance.getPass_distance_name() == null || passdistance.getPass_distance_name().trim().equals("")) {
				addFieldError("pass_distance_name","Please enter Pass Distance Type");
			}
			
			if (passdistance.getStatus() == null || passdistance.getStatus().trim().equals("")) {
			addFieldError("getStatus","Please enter Pass Distance Status");
			}
		
//		super.validate();
//		
//		}
	}
}
