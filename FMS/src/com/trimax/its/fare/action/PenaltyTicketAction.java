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
import com.trimax.its.fare.dao.PenaltyTicketDao;
import com.trimax.its.fare.model.PenaltyTicket;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PenaltyTicketAction  extends ActionSupport{
	private PenaltyTicket penaltyTicket;
	private int cid;
	
	public PenaltyTicket getPenaltyTicket() {
		return penaltyTicket;
	}

	public void setPenaltyTicket(PenaltyTicket penaltyTicket) {
		this.penaltyTicket= penaltyTicket;
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

		PenaltyTicketDao dao=new PenaltyTicketDao();
		penaltyTicket=dao.getPenaltyTicketById(cid);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PenaltyTicketAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String para=request.getParameter("cid");
		if(delete.equalsIgnoreCase(""))
		{
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}
		
		PenaltyTicketDao dao=new PenaltyTicketDao();
		String s=dao.deletePenaltyTicket(cid);
		

		if(s.equals("success")){
			addActionMessage("Penalty id "+cid+" deleted successfully.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PenaltyTicketAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();	
		PenaltyTicketDao dao=new PenaltyTicketDao();
		if(create.equalsIgnoreCase("Y")){
		int i=dao.addPenaltyTicket(penaltyTicket);
		
		if(i>0){
			addActionMessage("Penalty id "+i+" created successfully.");
		}else{
			if(i==-2){
				addFieldError("penaltyTicketName","Penalty Ticket Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		//execute();
		return SUCCESS;}
		return INPUT;
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PenaltyTicketAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		PenaltyTicketDao dao=new PenaltyTicketDao();

		int i=dao.updatePenaltyTicket(penaltyTicket);
		
		if(i>0){
			addActionMessage("Penalty id "+penaltyTicket.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addFieldError("penaltyTicketName","Penalty Ticket Type already exist.");
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
	
		if(penaltyTicket.getPenaltyTicketName()==null || penaltyTicket.getPenaltyTicketName().trim().isEmpty()){
			addFieldError("penaltyTicketName","Please enter Penalty Ticket Type");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(penaltyTicket.getPenaltyTicketName())){
				addFieldError("penaltyTicketName","Special characters not allowed"); 
			 }
		}	
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			PenaltyTicketDao dao=new PenaltyTicketDao();

			String[] dbcol={"","id","penaltyTicketName","notes","status"};

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
