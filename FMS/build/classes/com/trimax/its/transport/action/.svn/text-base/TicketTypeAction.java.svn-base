package com.trimax.its.transport.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.classic.Validatable;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.transport.dao.TicketTypeDao;
import com.trimax.its.transport.model.TicketType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class TicketTypeAction extends ActionSupport implements Preparable,Validatable{

	private TicketType ticketType;

	
	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
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
		
		TicketTypeDao dao=new TicketTypeDao();
		ticketType=dao.getTicketTypeById(id);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
HttpServletRequest request = ServletActionContext.getRequest();
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TicketTypeAction!view");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
if(delete.equalsIgnoreCase("Y")){
		int id=Integer.parseInt(request.getParameter("id"));
		
		TicketTypeDao dao=new TicketTypeDao();
		String s=dao.deleteTicketType(id);
		

		if(s.equals("success")){
			addActionMessage("Ticket Type id "+id+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
		
		return "success";}
else{
	addActionMessage("Access Denied - User Does Not Have Access Privileges");

	return  "success";
}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TicketTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		TicketTypeDao dao=new TicketTypeDao();
		if(create.equalsIgnoreCase("Y")){
		ticketType.setTicketTypeName(ticketType.getTicketTypeName().trim());
		ticketType.setCreatedDate(new java.util.Date());
		ticketType.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		ticketType.setDeletedStatus(0);
		ticketType.setUpdatedBy(0);
		ticketType.setUpdatedDate(null);
		
		int i=dao.addTicketType(ticketType);
		
		if(i>0){
			addActionMessage("Ticket Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addActionError("Ticket Type already exist...!");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "TicketTypeAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		TicketTypeDao dao=new TicketTypeDao();
		if(edit.equalsIgnoreCase("Y")){
		ticketType.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		ticketType.setUpdatedDate(new java.util.Date());
		
		int i=dao.updateTicketType(ticketType);
		
		if(i>0){
			addActionMessage("Ticket Type id "+ticketType.getId()+" updated successfully.");
		}else{
			if(i==-1){
				addActionError("Ticket Type already exist...!");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		execute();
		return SUCCESS;}
		else{
			return INPUT;
		}
	}
	
	@SkipValidation
	public String execute()
	{
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketTypeDao dao = new TicketTypeDao();
			//int cnt = dao.getTotalRecords(request.getParameter("sSearch"));

			String[] cols = { "","id","ticketTypeName","status"};
			String[] dbcol={"","id","ticketTypeName","status"};
						
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
			total = dao.getTotalRecords(request,dbcol[col],dir);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[col],sdir);

			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}
	
	public void validate(){
		if(ticketType.getTicketTypeName()==null || ticketType.getTicketTypeName().length()<=0 || ticketType.getTicketTypeName().trim().equals("")){
			addFieldError("ticketTypeName","Please enter Trip Type Name");
		}	
		else{

			CommonValidation cv=new CommonValidation();	
		 if (!cv.isSpecialChar(ticketType.getTicketTypeName())){//(!Pattern.matches("^[^()<>;\\*%@#?!$]*$", ticketType.getticketTypeName())){
			addFieldError("ticketTypeName","Special characters not allowed"); 
		 }
		}
	}
	
	public void prepare() throws Exception {

	}

}
