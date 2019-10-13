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
import com.trimax.its.pass.dao.PassPurchaseTypeDAO;
import com.trimax.its.pass.model.PassDistanceType;
import com.trimax.its.pass.model.PassPurchaseType;

public class PassPurchaseTypeAction extends ActionSupport{
	
	public PassPurchaseType passpurchase;

	
	
	public PassPurchaseType getPasspurchase() {
		return passpurchase;
	}
	public void setPasspurchase(PassPurchaseType passpurchase) {
		this.passpurchase = passpurchase;
	}
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void passpurchasetypeview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassPurchaseTypeDAO dao=new PassPurchaseTypeDAO();
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
			total =dao.getTotalRecordsForPassPurchase(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPassPurchaseList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createpasspurchase(){
		return "success";
	}
	public String savecreatepasspurchase(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassPurchaseTypeDAO dao=new PassPurchaseTypeDAO();
		try{
			System.out.println(passpurchase.getPass_purchase_name().trim());
			passpurchase.setPass_purchase_name(passpurchase.getPass_purchase_name().trim());
			passpurchase.setRemarks(passpurchase.getRemarks().trim());
			passpurchase.setCreated_date(new java.util.Date());
			passpurchase.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			passpurchase.setDeleted_status(0);
			passpurchase.setUpdated_by(0);
			passpurchase.setUpdated_date(null);
		
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
		int i=dao.addPassPurchaseType(passpurchase);
		
		if(i>0){
			addActionMessage("Pass Purchase Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_purchase_name","Pass Purchase Type already exist.");
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
		PassPurchaseTypeDAO dao=new PassPurchaseTypeDAO();
		passpurchase=dao.getPassPurchaseTypeById(id);
		
		return "success";
	}
	
	public String editPassPurchase(){
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
	String status=dc.getStatus(passpurchase.getPass_purchase_id(),"passpurchasetype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passpurchase.getStatus().equals("Active"))){
		
		PassPurchaseTypeDAO dao=new PassPurchaseTypeDAO();
		
		passpurchase.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passpurchase.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassPurchaseType(passpurchase);
		
		if(i>0){
			addActionMessage("Pass Purchase Type id "+passpurchase.getPass_purchase_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_purchase_name","Pass Purchase Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passpurchase.getStatus().equals("Inactive"))
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
	public String deletePassPurchaseforsmartcard() {
		
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
		PassPurchaseTypeDAO dao=new PassPurchaseTypeDAO();
		status=dao.deletePassPurchaseType(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Purchase Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Purchase Type id "+id+" deleted successfully.");		
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
			if (passpurchase.getPass_purchase_name() == null || passpurchase.getPass_purchase_name().trim().equals("")) {
				addFieldError("pass_purchase_name","Please enter Pass Purchase Type Name");
			}
			
			if (passpurchase.getStatus() == null || passpurchase.getStatus().trim().equals("")) {
			addFieldError("getStatus","Please enter Pass Purchase Status");
			}
		
//		super.validate();
//		
//		}
	}

}
