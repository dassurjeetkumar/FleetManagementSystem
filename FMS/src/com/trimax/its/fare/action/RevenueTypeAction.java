package com.trimax.its.fare.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.fare.dao.RevenueTypeDao;
import com.trimax.its.fare.model.RevenueType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class RevenueTypeAction  extends ActionSupport{
	private RevenueType revenueType;
	private int cid;
	
	public RevenueType getRevenueType() {
		return revenueType;
	}

	public void setRevenueType(RevenueType revenueType) {
		this.revenueType= revenueType;
	}	

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
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
		String para=request.getParameter("cid");
		
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}

		RevenueTypeDao dao=new RevenueTypeDao();
		revenueType=dao.getRevenueTypeById(cid);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RevenueTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		String para=request.getParameter("cid");
		try{
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+para);
		DependencyChecker dc=new DependencyChecker();
        String status=dc.getStatus(Integer.parseInt(para),"revenue_type");
        System.out.println("status---"+status);
        
       if(status.equals("")){
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}
		
		RevenueTypeDao dao=new RevenueTypeDao();
		String s=dao.deleteRevenueType(cid);
		

		if(s.equals("success")){
			addActionMessage("Revenue id "+cid+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
       }else{
           addActionError(status);
           //returnResult = "success";
       }
		}catch(Exception e){
			
			System.out.println(e);
		}
		return "success";}
		else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RevenueTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();	
		RevenueTypeDao dao=new RevenueTypeDao();
		if(create.equalsIgnoreCase("Y")){
		int i=dao.addRevenueType(revenueType);
		
		if(i>0){
			addActionMessage("Revenue id "+i+" created successfully.");
		}else{
			if(i==-2){
				addFieldError("revenueTypeName","Revenue Type already exist.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "RevenueTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(revenueType.getId(),"revenue_type");
		if(edit.equalsIgnoreCase("Y")){
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  revenueType.getStatus().equals("Active"))){	
		RevenueTypeDao dao=new RevenueTypeDao();

		int i=dao.updateRevenueType(revenueType);
		
		if(i>0){
			addActionMessage("Revenue id "+revenueType.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addFieldError("revenueTypeName","Revenue Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		}else
		{
			
			if(revenueType.getStatus().equals("Inactive"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		//execute();
		return SUCCESS;}
		else{
			return "input";
		}
	}
	
	public void validate(){
	
		if(revenueType.getRevenueTypeName()==null || revenueType.getRevenueTypeName().trim().isEmpty()){
			addFieldError("revenueTypeName","Please enter Revenue type");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(revenueType.getRevenueTypeName())){
				addFieldError("revenueTypeName","Special characters not allowed"); 
			 }
		}	
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			RevenueTypeDao dao=new RevenueTypeDao();

			String[] dbcol={"","id","revenueTypeName","notes","status"};

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
				if (col < 0 || col > 7)
					col = 0;
			}
			if (sdir != null) {
				if (sdir.equals("asc"))
					dir = "asc";
				else
					dir="desc";
			}


			int total = -1;
			total = dao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[Integer.parseInt(sCol)],dir,viewdeletedrecord);

			out.print(result);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
	}
}
