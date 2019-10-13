package com.trimax.its.transport.action;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.pass.dao.PassDurationTypeDAO;
import com.trimax.its.pass.model.PassDurationType;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.transport.dao.TicketSubTypeDAO;
import com.trimax.its.transport.model.TicketSubType;
import com.trimax.its.transport.model.TicketType;
import com.trimax.its.util.HibernateUtil;

public class TicketSubTypeAction extends ActionSupport{
	
	
	public TicketSubType ticketsubtype;

	 private Map<Integer, String> Tickettype;
		
		public String Ticket_type_name;
		public String Ticket_type_id;
		
		
		
	
	
	public TicketSubType getTicketsubtype() {
			return ticketsubtype;
		}
		public void setTicketsubtype(TicketSubType ticketsubtype) {
			this.ticketsubtype = ticketsubtype;
		}
		public Map<Integer, String> getTickettype() {
			return Tickettype;
		}
		public void setTickettype(Map<Integer, String> tickettype) {
			Tickettype = tickettype;
		}
		public String getTicket_type_name() {
			return Ticket_type_name;
		}
		public void setTicket_type_name(String ticket_type_name) {
			Ticket_type_name = ticket_type_name;
		}
		public String getTicket_type_id() {
			return Ticket_type_id;
		}
		public void setTicket_type_id(String ticket_type_id) {
			Ticket_type_id = ticket_type_id;
		}
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void TicketSubTypeview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			TicketSubTypeDAO dao = new TicketSubTypeDAO();
			String[] dbcol={"","ticket_type_id","ticket_sub_type_code","ticket_type_name","ticket_sub_type_name_kannada","ticket_type_id","notes","status"};
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
			total =dao.getTotalRecordsForTicketSubType(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getTicketSubTypeList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createTicketSubType(){
		Tickettype=getTicketTypeList();
		return "success";
	}
	
	public Map<Integer, String> getTicketTypeList() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from TicketType where deleted_status=0 and status='ACTIVE'");
		try {
			List<TicketType> list = query.list();
			for (TicketType type : list) {
				resultMap.put(type.getId(), type.getTicketTypeName());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	
	
	public String saveTicketSubType(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		TicketSubTypeDAO dao = new TicketSubTypeDAO();
		Common common = new Common();
		try{
			System.out.println(ticketsubtype.getTicket_sub_type_name().trim());
			System.out.println("---");
			System.out.println("----"+ticketsubtype.getTicket_sub_type_code());
			ticketsubtype.setTicket_sub_type_name(ticketsubtype.getTicket_sub_type_name().trim());
			ticketsubtype.setTicket_type_id(ticketsubtype.getTicket_type_id());
			ticketsubtype.setTicket_sub_type_code(ticketsubtype.getTicket_sub_type_code().trim());
			ticketsubtype.setTicket_sub_type_name_kannada(ticketsubtype.getTicket_sub_type_name_kannada().trim());
			ticketsubtype.setStatus(ticketsubtype.getStatus());
			ticketsubtype.setNotes(ticketsubtype.getNotes().trim());
			ticketsubtype.setCreatedDate(new java.util.Date());
			ticketsubtype.setCreatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			ticketsubtype.setDeletedStatus(0);
			//ticketsubtype.setUpd(0);
			//passduration.setUpdated_date(null);
		System.out.println("ticketsubtype.getStatus()=="+ticketsubtype.getStatus());
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
		int i=dao.addTicketSubType(ticketsubtype);
		
		if(i>0){
			addActionMessage("Ticket Sub Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("ticket_sub_type_name","Ticket Sub Type already exist.");
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
		
		int id=Integer.parseInt(request.getParameter("subid"));
		System.out.println("id=="+id);
		TicketSubTypeDAO dao = new TicketSubTypeDAO();
		Tickettype=getTicketTypeList();
		ticketsubtype=dao.getTicketSubTypeById(id);
		String tickettypeid=ticketsubtype.getTicket_type_id();
		String ticket_name=getTypeName(tickettypeid);
		setTicket_type_id(ticket_name);
		//System.out.println(request.ge);
		
		return "success";
	}
	
public String getTypeName(String id){
		
		Session session=null;
		Transaction tx=null;
		TicketType ticketType=null;
		String tickettype_name="";
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from TicketType where id="+id+" and deletedStatus=0";
		
		List list=session.createQuery(query).list();
		
		ticketType=(TicketType)list.get(0);
		tickettype_name=ticketType.getTicketTypeName();
		System.out.println("ticketType==="+ticketType.getTicketTypeName());
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return tickettype_name;
	}
	
	public String editTicketSubType(){
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
		try{
	DependencyChecker dc=new DependencyChecker();
	String status=dc.getStatus(ticketsubtype.getId(),"TicketSubType");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  ticketsubtype.getStatus().equals("Active"))){
		
		TicketSubTypeDAO dao = new TicketSubTypeDAO();
		
		ticketsubtype.setUpdatedBy(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		ticketsubtype.setUpdatedDate(new java.util.Date());
		
		int i=dao.updateTicketSubType(ticketsubtype);
		
		if(i>0){
			addActionMessage("Ticket Sub Type id "+ticketsubtype.getId()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("ticket_sub_type_name","Ticket Sub Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(ticketsubtype.getStatus().equals("Inactive"))
		{
			//setUpdatestaus("success");
		addActionError(status);
		return "input";
		}	
		
	}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		//execute();
		return SUCCESS;
//		}else{
//			return INPUT;
//		}
	}
	
	
	@SkipValidation
	public String deleteTicketSubType() {
		
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
		int id=Integer.parseInt(request.getParameter("subid"));
//		if(delete.equalsIgnoreCase("Y")){
		TicketSubTypeDAO dao = new TicketSubTypeDAO();
		status=dao.deleteSubType(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Ticket Sub Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Ticket Sub Type id "+id+" deleted successfully.");		
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
//	
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
			if (ticketsubtype.getTicket_sub_type_name() == null || ticketsubtype.getTicket_sub_type_name().trim().equals("")) {
				addFieldError("ticket_sub_type_name","Please enter Ticket Sub Type Name");
			}
			
			if (ticketsubtype.getStatus() == null || ticketsubtype.getStatus().trim().equals("")) {
			addFieldError("getStatus","Please enter Pass Duration Status");
			}
		
//		super.validate();
//		
//		}
	}
}
