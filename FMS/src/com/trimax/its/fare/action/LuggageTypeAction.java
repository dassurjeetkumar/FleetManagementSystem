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
import com.trimax.its.fare.dao.LuggageTypeDao;
import com.trimax.its.fare.model.LuggageType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class LuggageTypeAction  extends ActionSupport{
	private LuggageType luggageType;
	private int cid;
	
	public LuggageType getLuggageType() {
		return luggageType;
	}

	public void setLuggageType(LuggageType luggageType) {
		this.luggageType= luggageType;
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

		LuggageTypeDao dao=new LuggageTypeDao();
		luggageType=dao.getLuggageTypeById(cid);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "LuggageTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		String para=request.getParameter("cid");
		
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}
		
		LuggageTypeDao dao=new LuggageTypeDao();
		String s=dao.deleteLuggageType(cid);
		

		if(s.equals("success")){
			addActionMessage("Luggage id "+cid+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "LuggageTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		LuggageTypeDao dao=new LuggageTypeDao();
		if(create.equalsIgnoreCase("Y")){
		int i=dao.addLuggageType(luggageType);
		
		if(i>0){
			addActionMessage("Luggage id "+i+" created successfully.");
		}else{
			if(i==-2){
				addFieldError("luggageName","Luggage Type already exist.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "LuggageTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();	
		LuggageTypeDao dao=new LuggageTypeDao();
if(edit.equalsIgnoreCase("Y")){
		int i=dao.updateLuggageType(luggageType);
		
		if(i>0){
			addActionMessage("Luggage id "+luggageType.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addFieldError("luggageName","Luggage Type already exist.");
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
	
	public void validate(){
	
		if(luggageType.getLuggageName()==null || luggageType.getLuggageName().trim().isEmpty()){
			addFieldError("luggageName","Please enter Luggage type");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(luggageType.getLuggageName())){
				addFieldError("luggageName","Special characters not allowed"); 
			 }
		}	
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			LuggageTypeDao dao=new LuggageTypeDao();

			String[] dbcol={"","id","luggageName","notes","status"};

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
