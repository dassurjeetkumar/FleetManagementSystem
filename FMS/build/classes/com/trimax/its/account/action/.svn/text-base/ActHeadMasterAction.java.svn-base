package com.trimax.its.account.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.account.dao.ActHeadMasterdao;
import com.trimax.its.account.model.ActHeadMasterModel;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;





public class ActHeadMasterAction extends ActionSupport implements Preparable  {
	private ActHeadMasterModel actheadmaster;
	private int aid;
//	private int cid;
	private Map<Integer,String> revenueTypeMap;
	
	
	@SkipValidation
	public String view() {
		return "view";
	}
	
	@SkipValidation
	public String add() {
		return "add";
	}
	
	@SkipValidation
	public String showAccount() {
		return "success";
	}
	
	@SkipValidation
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//String para=request.getParameter("aid");
		String para=request.getParameter("aid");
		aid=Integer.parseInt(para);
		if(para!=null && para.isEmpty()){
		aid=Integer.parseInt(para);
		}
		
		System.out.println(aid);
		
		Common common=new Common();
		
		ActHeadMasterdao dao=new ActHeadMasterdao();
		actheadmaster=dao.getAccountheadById(aid);
		//actheadmaster.setNotes(actheadmaster.getNotes().trim());
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		System.out.println("User" +usrsessionid);
		String para=request.getParameter("cid");
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		//int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"AccountHeadaction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(para!=null && para.isEmpty()){
			aid=Integer.parseInt(para);
		}
		System.out.println("CID"+aid);
		if(delete.equalsIgnoreCase("Y")){
		ActHeadMasterdao dao=new ActHeadMasterdao();
		String s=dao.deleteAccounthead(aid,usrsessionid);
		

		if(s.equals("success")){
			addActionMessage("Account Head id "+aid+" deleted successfully.");
		}else{
			addActionError("Database error retry again.");
		}
		
		return "success";}
		else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			ActHeadMasterdao dao=new ActHeadMasterdao();

			String[] dbcol={"","acthead_id","acthead_name","rvntype.revenueTypeName","status","notes","CR_DB","remarks"};

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
			total = dao.getTotalRecords(request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);

			out.print(result);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
		
	}
	
	

	public ActHeadMasterModel getActheadmaster() {
		return actheadmaster;
	}

	public void setActheadmaster(ActHeadMasterModel actheadmaster) {
		this.actheadmaster = actheadmaster;
	}

	@Override
	public void prepare() throws Exception {
		ActHeadMasterdao dao= new ActHeadMasterdao();
		setRevenueTypeMap(dao.getRevenueType());
		
	}
	//Add Data into database
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ActHeadMasterdao dao=new ActHeadMasterdao();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"AccountHeadaction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		if(actheadmaster.getActhead_name()==null || actheadmaster.getActhead_name().trim().equalsIgnoreCase("") 
				&& actheadmaster.getRevenuetype().getId()==0)
		{
			addFieldError("actName","Please insert Account Head number ");
			addFieldError("id","Please select Revenue Type");
			return INPUT;
		}
		CommonValidation comm=new CommonValidation();
		
		if(actheadmaster.getActhead_name()==null || actheadmaster.getActhead_name().trim().equalsIgnoreCase("") )
		{
			addFieldError("actName","Please insert Account Head number ");			
			return INPUT;
		}
		else if (!comm.isSpecialChar(actheadmaster.getActhead_name().trim()))
		{
			 addFieldError("actName","Special Characters are not allowed. ");			
	 		return INPUT;
		     
		}
		
		if(actheadmaster.getRevenuetype().getId()==0)
		{
			addFieldError("id","Please select Revenue Type");			
			return INPUT;
		}
		
		int i=dao.addAccountHead(actheadmaster);
		
		if(i>0){
			addActionMessage("Account Head id "+i+" created successfully.");
		}else{
			if(i==-2){
				addActionError("Could not insert Duplicate Account Head Number");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		//execute();
		return  SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	//Update data
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();		
		String para=request.getParameter("aid");
		CommonValidation comm=new CommonValidation();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"AccountHeadaction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		if(actheadmaster.getActhead_name()==null || actheadmaster.getActhead_name().trim().equalsIgnoreCase("") 
				&& actheadmaster.getRevenuetype().getId()==0)
		{
			addFieldError("actName","Please insert Account Head number ");
			addFieldError("revenuetype.id","Please select Revenue Type");
			return INPUT;
		}

		if(actheadmaster.getActhead_name()==null || actheadmaster.getActhead_name().trim().equalsIgnoreCase("") )
		{
			addFieldError("actName","Please insert Account Head number ");			
			return INPUT;
		}
		else if (!comm.isSpecialChar(actheadmaster.getActhead_name().trim()))
		{
			 addFieldError("actName","Special Characters are not allowed. ");			
	 		return INPUT;
		     
		}
		
		if(actheadmaster.getRevenuetype().getId()==0)
		{
			addFieldError("revenuetype.id","Please select Revenue Type");			
			return INPUT;
		}
		
		ActHeadMasterdao dao=new ActHeadMasterdao();

		int i=dao.updateAccounthead(actheadmaster);
		
		if(i>0){
			addActionMessage("Account Head id "+actheadmaster.getActhead_id()+" updated successfully.");
		}else{
			if(i==-2){
				addActionError("Could not insert Duplicate Account Head Number");
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
	
	/*public void validate()
	{
		Common common=new Common();
		if(actheadmaster.getActhead_name()==null || actheadmaster.getActhead_name().equalsIgnoreCase("") )
		{
			addFieldError("actName","Please insert Account Head name ");			
		}
		if(actheadmaster.getRevenuetype().getRevenueTypeName()==null || actheadmaster.getRevenuetype().getRevenueTypeName().equalsIgnoreCase("Select"))
		{
			addFieldError("id","Please select Revenue Type");			
		}
		
	}*/

	public int getCid() {
		return aid;
	}

	public void setCid(int aid) {
		this.aid = aid;
	}

	public Map<Integer, String> getRevenueTypeMap() {
		return revenueTypeMap;
	}

	public void setRevenueTypeMap(Map<Integer, String> revenueTypeMap) {
		this.revenueTypeMap = revenueTypeMap;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}
	

}
