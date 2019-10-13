package com.trimax.its.route.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.doa.ticketing.model.TicketInventoryLog;
import com.trimax.doa.ticketing.model.TicketInventoryMaster;
import com.trimax.doa.ticketing.model.TicketInventory;
import com.trimax.doa.ticketing.model.TicketInvoiceDetails;
import com.trimax.doa.ticketing.model.TicketInvoiceMaster;
import com.trimax.its.common.Common;
import com.trimax.its.inventoryticketpasstype.model.InventoryTicketPassType;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.dao.TicketInventoryDAO;
import com.trimax.its.route.model.Floor;
import com.trimax.its.route.model.Route;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.util.HibernateUtil;

public class TicketInventorys extends ActionSupport {
	
	private Integer orgTypeId;
	private Integer orgId;
	private String orgName;
	private String date;
	
	
	public Integer getOrgTypeId() {
		return orgTypeId;
	}


	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String execute(){

		TicketInventoryDAO daoObject = new TicketInventoryDAO();
		setOrgTypeId(Integer.parseInt((String) ServletActionContext.getRequest().getSession().getAttribute("orgtypeid")));
		setOrgId(Integer.parseInt((String) ServletActionContext.getRequest().getSession().getAttribute("orgchartid")));
		setOrgName(daoObject.getOrgName(orgId));
		setDate(new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(new Date()));
		return "success";
	}
	List<InventoryTicketPassType> daypass;
	List<InventoryTicketPassType> monthpass;
	


	public List<InventoryTicketPassType> getDaypass() {
		return daypass;
	}


	public void setDaypass(List<InventoryTicketPassType> daypass) {
		this.daypass = daypass;
	}


	public List<InventoryTicketPassType> getMonthpass() {
		return monthpass;
	}


	public void setMonthpass(List<InventoryTicketPassType> monthpass) {
		this.monthpass = monthpass;
	}


	public String issueTicketInventory(){
		TicketInventoryDAO daoObject = new TicketInventoryDAO();
		setOrgTypeId(Integer.parseInt((String) ServletActionContext.getRequest().getSession().getAttribute("orgtypeid")));
		setOrgId(Integer.parseInt((String) ServletActionContext.getRequest().getSession().getAttribute("orgchartid")));
		setOrgName(daoObject.getOrgName(orgId));
		String ortype=getOrgTypeId().toString();
		String orgcht=getOrgId().toString();
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		int count = ticketinvdao.checkForDuplicateInitialEntry(ortype,
				orgcht);
		if(count==0){
		
		}else{
			addActionMessage("Initial Stock Entry alredy exist for "+getOrgName());	
		}
		return "success";
	}
	
	public String getDayDropdown(){

		HttpServletRequest request=ServletActionContext.getRequest();
	    TicketInventoryDAO ticketInventoryDAO= new TicketInventoryDAO();
	    daypass = ticketInventoryDAO.getdaypass();
	    String data="";	
	    data=data+"<select id='pass_day_' class='form-control pass_day_' style='width:80px'>";
		for(int i=0; i<daypass.size(); i++){
			InventoryTicketPassType obj = daypass.get(i);
			data=data+"<option value='"+obj.getDay_month()+"'>"+obj.getDay_month()+"</option>";
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
     	return null;
	}
	
	public String getMonthDropDown(){
		HttpServletRequest request=ServletActionContext.getRequest();
	    TicketInventoryDAO ticketInventoryDAO= new TicketInventoryDAO();
	    daypass = ticketInventoryDAO.getmonthpass();
	    String data="";	
	    data=data+"<select id='pass_day_' class='form-control pass_day_' style='width:80px'>";
		for(int i=0; i<daypass.size(); i++){
			InventoryTicketPassType obj = daypass.get(i);
			data=data+"<option value='"+obj.getDay_month()+"'>"+obj.getDay_month()+"</option>";
		 }
		 HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
     	return null;
	}
	
	public String getOrgType() {

		// serviceTypeIds=rmDao.getServiceId();
		TicketInventoryDAO ticketInventoryDAO= new TicketInventoryDAO();
		List<String> l1 = ticketInventoryDAO.getOrgTypeId();
		List<String> l2 = ticketInventoryDAO.getOrgTypeName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public String getUnitName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		int orgtypeid = Integer.parseInt(request.getParameter("orgid"));
		// serviceTypeIds=rmDao.getServiceId();
		TicketInventoryDAO ticketInventoryDAO= new TicketInventoryDAO();
		List<String> l1 = ticketInventoryDAO.getUnitId(orgtypeid);
		List<String> l2 = ticketInventoryDAO.getUnitName(orgtypeid);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	public String checkDuplicateInitialStock() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();

		String ortype = request.getParameter("ortype").toString();
		String orgcht = request.getParameter("orgcht").toString();
		
		TicketInventoryDao ticketinvdao = new TicketInventoryDao();
		int count = ticketinvdao.checkForDuplicateInitialEntry(ortype,
				orgcht);
		if(count==0){
		
		}else{
			addActionMessage("Initial Stock Entry alredy exist");	
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		out.print(count);
		return null;
	}
	
	public String getViewData(){
		Common common=new Common();
		JSONArray array1 = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONArray array3 = new JSONArray();
		JSONArray array4 = new JSONArray();
		JSONObject result = new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		int unit_type=Integer.parseInt(request.getParameter("unittype"));
		int unit_name=Integer.parseInt(request.getParameter("unitname"));
		PrintWriter out;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
			session.getTransaction().setTimeout(200);
			session.getTransaction().begin();
		TicketInventoryDAO ticketInventoryDAO= new TicketInventoryDAO();
		List<Map<String, Object>> ticketdata=ticketInventoryDAO.getTicketData(session,unit_type,unit_name);
		List<Map<String, Object>> passdata=ticketInventoryDAO.gePasstData(session,unit_type,unit_name);
		List<Map<String, Object>> luggagedata=ticketInventoryDAO.getLuggData(session,unit_type,unit_name);
		List<Map<String, Object>> tsheetdata=ticketInventoryDAO.getTSheetData(session,unit_type,unit_name);
		
		for(int i=0;i<ticketdata.size();i++){
			Map<String, Object> rs = ticketdata.get(i);
			JSONArray jo = new JSONArray();
				jo.add( rs.get("denomination_key"));
				jo.add(rs.get("denomination_type_manual"));
				jo.add( rs.get("denomination_key"));
				jo.add( rs.get("opening_number"));
				jo.add( rs.get("closing_number"));
				jo.add( rs.get("number_of_tickets"));
				jo.add( rs.get("number_of_books"));
				jo.add( rs.get("value"));
				jo.add( rs.get("org_name"));
				jo.add( rs.get("org_type"));
				jo.add( rs.get("priority"));
				jo.add(common.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				
				array1.add(jo);
		}
		result.put("ticketdata",array1);
		result.put("ticketdatasize",ticketdata.size());
		
		
		for(int i=0;i<passdata.size();i++){
			Map<String, Object> rs = passdata.get(i);
			JSONArray jo = new JSONArray();
				jo.add( rs.get("denomination_key"));
				jo.add( rs.get("ticket_type_manual_name"));
				jo.add( rs.get("denomination_type_manual"));
				jo.add( rs.get("denomination_key"));
				jo.add( rs.get("pass_day"));
				jo.add( rs.get("opening_number"));
				jo.add( rs.get("closing_number"));
				jo.add( rs.get("number_of_tickets"));
				jo.add( rs.get("number_of_books"));
				jo.add( rs.get("value"));
				jo.add( rs.get("priority"));
				jo.add(common.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				
				array2.add(jo);
		}
		result.put("passdata",array2);
		result.put("passdatasize",passdata.size());
		
		
		for(int i=0;i<luggagedata.size();i++){
			Map<String, Object> rs = luggagedata.get(i);
			JSONArray jo = new JSONArray();
				jo.add( rs.get("denomination_key"));
				jo.add( rs.get("denomination_key"));
				jo.add( rs.get("opening_number"));
				jo.add( rs.get("closing_number"));
				jo.add( rs.get("number_of_tickets"));
				jo.add( rs.get("number_of_books"));
				jo.add( rs.get("priority"));
				jo.add(common.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				
				array3.add(jo);
		}
		result.put("luggdata",array3);
		result.put("luggdatasize",luggagedata.size());
		
		for(int i=0;i<tsheetdata.size();i++){
			Map<String, Object> rs = tsheetdata.get(i);
			JSONArray jo = new JSONArray();
				jo.add( rs.get("denomination_key"));
				jo.add( rs.get("denomination_key"));
				jo.add( rs.get("opening_number"));
				jo.add( rs.get("closing_number"));
				jo.add( rs.get("number_of_passes"));
				jo.add(common.changeDataFormat(rs.get("created_date").toString(),"yyyy-MM-dd HH:mm:ss.s","dd-MM-yyyy"));
				
				array4.add(jo);
		}
		result.put("tsheetdata",array4);
		result.put("tsheetdatasize",tsheetdata.size());
		
		
		session.getTransaction().commit();
		HttpServletResponse response=ServletActionContext.getResponse();
		out = response.getWriter();
		 out.print(result);
		} catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			session.close();
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		
		return null;
		
	}
	
	public String getTicketDenomList() {

		String data = "";

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			TicketInventoryDAO tickinvdaodao = new TicketInventoryDAO();
			String rolelist = tickinvdaodao.getDenomlist();
			data = rolelist;
			// data="Ashwini";
			out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPassType() {

		String data = "";

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			TicketInventoryDAO tickinvdaodao = new TicketInventoryDAO();
			String rolelist = tickinvdaodao.getPassTypelist();
			data = rolelist;
			// data="Ashwini";
			out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPasslist() {
		String data = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		String passtype = request.getParameter("tickettype").toString();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out = response.getWriter();
			TicketInventoryDAO tickinvdaodao = new TicketInventoryDAO();
			String rolelist = tickinvdaodao.getPasslist(passtype);
			data = rolelist;
			out.println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String saveTicketInventory(){
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try{
			session.getTransaction().setTimeout(2000);
			session.getTransaction().begin();
			HttpServletResponse response=ServletActionContext.getResponse();
			TicketInventoryDAO tickinvdaodao = new TicketInventoryDAO();
			PrintWriter out;
			out = response.getWriter();
			HttpServletRequest request=ServletActionContext.getRequest();
			int usrsessionid = (Integer) request.getSession().getAttribute("userid");
			
			int unit_type=Integer.parseInt(request.getParameter("unit_type"));
			int unit_name=Integer.parseInt(request.getParameter("unit_name"));
			
			
			String[] tkt_denom_no=request.getParameter("tkt_denom_no").split(",");
			String[] tkt_denom_key=request.getParameter("tkt_denom_key").split(",");
			String[] tkt_denom_value=request.getParameter("tkt_denom_value").split(",");
			String[] tkt_start_no=request.getParameter("tkt_start_no").split(",");
			String[] tkt_end_no=request.getParameter("tkt_end_no").split(",");
			String[] tkt_priority=request.getParameter("tkt_priority").split(",");
			String[] tkt_value=request.getParameter("tkt_value").split(",");
			
			TicketInventoryMaster ticketinv= new TicketInventoryMaster();
			TicketInventory ticketinvdet = new TicketInventory();
			
			String updateid="";
			
			int totalvalue=0;
			String tktmasterid="";
			String tktdetailid="";
			//System.out.println("tkt_denom_no.length=========="+tkt_denom_no.length);
			if(request.getParameter("tkt_denom_no")!=""){
			for(int i=0; i<tkt_denom_no.length;i++){
				ticketinv= new TicketInventoryMaster();
				ticketinv.setDenoimination_type(tkt_denom_no[i]);
				ticketinv.setTicket_type_manual_id(1);
				ticketinv.setKey_number(tkt_denom_key[i]);
				ticketinv.setOpening_number(tkt_start_no[i]);
				ticketinv.setClosing_number(tkt_end_no[i]);
				ticketinv.setNumber_of_tickets(Integer.parseInt(tkt_end_no[i])-Integer.parseInt(tkt_start_no[i])+1);
				ticketinv.setNoofbooks((Integer.parseInt(tkt_end_no[i])-Integer.parseInt(tkt_start_no[i])+1)/100);
				ticketinv.setValue(Integer.parseInt(tkt_value[i]));
				totalvalue=totalvalue+Integer.parseInt(tkt_value[i]);
				ticketinv.setUnittype(unit_type);
				ticketinv.setUnitname(unit_name);
				ticketinv.setCurrentstatus("New");
				ticketinv.setStatus("ACTIVE");
				ticketinv.setCreated_by(usrsessionid);
				ticketinv.setCreated_date(new Date());
				ticketinv.setPriority(Integer.parseInt(tkt_priority[i]));
			
				
				tickinvdaodao.insertTicketInventoryMaster(ticketinv,session);
				tktmasterid=tktmasterid+ticketinv.getTicketinventorymasterid()+",";
				
				if(ticketinv.getOpening_number()>ticketinv.getClosing_number()){
					out.print("Closing No Should Be greater Than Opening No. In Ticket Inventory..");
					session.getTransaction().rollback();
					return null;
				}
				if((ticketinv.getOpening_number()%10)!=00){
					out.print("Please Enter Start No. Ending With 00 In Ticket Inventory..");
					session.getTransaction().rollback();
					return null;
				}
				if((ticketinv.getClosing_number()%100)!=99){
					out.print("Please Enter End No. Ending With 99 In Ticket Inventory..");
					session.getTransaction().rollback();
					return null;
				}
				
				int starttktno=ticketinv.getOpening_number();
				for(int j=0;j<ticketinv.getNoofbooks(); j++){
					ticketinvdet = new TicketInventory();
					ticketinvdet.setTicketinventorymasterid(ticketinv.getTicketinventorymasterid());
					ticketinvdet.setDenoimination_type(String.valueOf(ticketinv.getDenoimination_type()));
					ticketinvdet.setTicket_type_manual_id(ticketinv.getTicket_type_manual_id());
					ticketinvdet.setKey_number(ticketinv.getKey_number());
					ticketinvdet.setStartno(starttktno);
					ticketinvdet.setEndno(starttktno+100-1);
					starttktno=ticketinvdet.getEndno()+1;
					ticketinvdet.setNumber_of_tickets(100);
					ticketinvdet.setNoofbooks(1);
					ticketinvdet.setValue((ticketinvdet.getEndno()-ticketinvdet.getStartno()+1)*(ticketinv.getValue()/(ticketinv.getNoofbooks()*100)));
					ticketinvdet.setUnittype(unit_type);
					ticketinvdet.setUnitname(unit_name);
					ticketinvdet.setPartialbook("N");
					ticketinvdet.setCurrentstatus("New");
					ticketinvdet.setStatus("ACTIVE");
					ticketinvdet.setCreated_by(usrsessionid);
					ticketinvdet.setCreated_date(new Date());
					ticketinvdet.setPriority(Integer.parseInt(tkt_priority[i]));
					ticketinvdet.setStock_entry("Y");
					
					tickinvdaodao.insertTicketInventoryDetails(ticketinvdet,session);
					ticketinvdet.setCurrentstatus("In Transit");
					ticketinvdet.setCurrentstatus("Issued");
					tktdetailid=tktdetailid+ticketinvdet.getTicket_inventory_id()+",";
					updateid=updateid+ticketinvdet.getTicket_inventory_id()+",";
					int count=tickinvdaodao.checkForDuplicateTicketEntry(  Integer.toString(ticketinvdet.getTicket_type_manual_id()),   ticketinvdet.getKey_number(),  Integer.toString(ticketinvdet.getStartno()),   Integer.toString(ticketinvdet.getEndno()), session,  Integer.toString(ticketinvdet.getTicket_type_manual_id()));
					if(count!=0){
						out.print("Duplicate Entry In Ticket Inventory..");
						session.getTransaction().rollback();
						return null;
					}
				}
				tktdetailid=tktdetailid+"@";
			}
			}
			
			String[] pass_pass_type=request.getParameter("pass_pass_type").split(",");
			String[] pass_denom_no=request.getParameter("pass_denom_no").split(",");
			String[] pass_denom_key=request.getParameter("pass_denom_key").split(",");
			String[] pass_pass_day=request.getParameter("pass_pass_day").split(",");
			String[] pass_start_no=request.getParameter("pass_start_no").split(",");
			String[] pass_end_no=request.getParameter("pass_end_no").split(",");
			String[] pass_prority=request.getParameter("pass_prority").split(",");
			String[] pass_value=request.getParameter("pass_value").split(",");
			String[] pass_denom_value=request.getParameter("pass_denom_value").split(",");
			
			String passmasterid="";
			String passdetailid="";
			//System.out.println("pass_denom_no.length=========="+pass_denom_no.length);
			if(request.getParameter("pass_pass_type")!=""){
			for(int i=0; i<pass_denom_no.length;i++){
				ticketinv= new TicketInventoryMaster();
				ticketinv.setDenoimination_type(pass_denom_no[i]);
				ticketinv.setTicket_type_manual_id(Integer.parseInt(pass_pass_type[i]));
				ticketinv.setKey_number(pass_denom_key[i]);
				ticketinv.setOpening_number(pass_start_no[i]);
				ticketinv.setClosing_number(pass_end_no[i]);
				ticketinv.setPass_day(pass_pass_day[i]);
				ticketinv.setNoofpasses(Integer.parseInt(pass_end_no[i])-Integer.parseInt(pass_start_no[i])+1);
				ticketinv.setNoofbooks((Integer.parseInt(pass_end_no[i])-Integer.parseInt(pass_start_no[i])+1)/50);
				ticketinv.setValue(Integer.parseInt(pass_value[i]));
				totalvalue=totalvalue+Integer.parseInt(pass_value[i]);
				ticketinv.setUnittype(unit_type);
				ticketinv.setUnitname(unit_name);
				ticketinv.setCurrentstatus("New");
				ticketinv.setStatus("ACTIVE");
				ticketinv.setCreated_by(usrsessionid);
				ticketinv.setCreated_date(new Date());
				ticketinv.setPriority(Integer.parseInt(pass_prority[i]));
				
				
				tickinvdaodao.insertTicketInventoryMaster(ticketinv,session);
				passmasterid=passmasterid+ticketinv.getTicketinventorymasterid()+",";
				int starttktno=ticketinv.getOpening_number();
				int loopcounter=0;
				if(ticketinv.getTicket_type_manual_id()==2){
					loopcounter=ticketinv.getNoofbooks();
					if(ticketinv.getOpening_number()>ticketinv.getClosing_number()){
						out.print("Closing No Should Be greater Than Opening No. In Pass Inventory..");
						session.getTransaction().rollback();
						return null;
					}
					if((ticketinv.getOpening_number()%100)!=01 || (ticketinv.getOpening_number()%100)!=51){
						
					}else{
						out.print("Please Enter Start No. Ending With 01 Or 51 In Daily Pass Inventory..");
						session.getTransaction().rollback();
						return null;
					}
					if((ticketinv.getClosing_number()%100)!=00 || (ticketinv.getClosing_number()%100)!=50){
						
					}else{
						out.print("Please Enter End No. Ending With 00 Or 50 In Daily Pass Inventory..");
						session.getTransaction().rollback();
						return null;
					}
				}
				if(ticketinv.getTicket_type_manual_id()==3){
					loopcounter=ticketinv.getClosing_number()-ticketinv.getOpening_number()+1;
					/*if(pass_start_no[i]!=pass_end_no[i]){
						out.print("Closing No Should Be Same As Opening No. In Ticket Inventory..");
						session.getTransaction().rollback();
						return null;
					}*/
				}
				
				
				
				for(int j=0;j<loopcounter; j++){
					ticketinvdet = new TicketInventory();
					ticketinvdet.setTicketinventorymasterid(ticketinv.getTicketinventorymasterid());
					ticketinvdet.setDenoimination_type(String.valueOf(ticketinv.getDenoimination_type()));
					ticketinvdet.setTicket_type_manual_id(ticketinv.getTicket_type_manual_id());
					ticketinvdet.setKey_number(ticketinv.getKey_number());
					ticketinvdet.setPass_day(ticketinv.getPass_day());
					if(ticketinv.getTicket_type_manual_id()==2){
					ticketinvdet.setStartno(starttktno);
					ticketinvdet.setEndno(starttktno+50-1);
					starttktno=ticketinvdet.getEndno()+1;
					ticketinvdet.setNoofpasses(50);
					ticketinvdet.setNoofbooks(1);
					ticketinvdet.setStock_entry("Y");
					}
					if(ticketinv.getTicket_type_manual_id()==3){
						ticketinvdet.setStartno(starttktno);
						ticketinvdet.setEndno(starttktno+1);
						starttktno=ticketinvdet.getEndno();	
						ticketinvdet.setNoofpasses(1);
						ticketinvdet.setNoofbooks(0);
					}
					
					
					ticketinvdet.setValue((ticketinvdet.getEndno()-ticketinvdet.getStartno()+1)*Integer.parseInt(pass_denom_value[i]));
					ticketinvdet.setUnittype(unit_type);
					ticketinvdet.setUnitname(unit_name);
					ticketinvdet.setPartialbook("N");
					ticketinvdet.setCurrentstatus("New");
					ticketinvdet.setStatus("ACTIVE");
					ticketinvdet.setCreated_by(usrsessionid);
					ticketinvdet.setCreated_date(new Date());
					ticketinvdet.setPriority(Integer.parseInt(pass_prority[i]));
					
					tickinvdaodao.insertTicketInventoryDetails(ticketinvdet,session);
					ticketinvdet.setCurrentstatus("In Transit");
					ticketinvdet.setCurrentstatus("Issued");
					passdetailid=passdetailid+ticketinvdet.getTicket_inventory_id()+",";
					updateid=updateid+ticketinvdet.getTicket_inventory_id()+",";
					
					int count=tickinvdaodao.checkForDuplicateTicketEntry(  Integer.toString(ticketinvdet.getTicket_type_manual_id()),   ticketinvdet.getKey_number(),  Integer.toString(ticketinvdet.getStartno()),   Integer.toString(ticketinvdet.getEndno()), session,  Integer.toString(ticketinvdet.getTicket_type_manual_id()));
					if(count!=0){
						out.print("Duplicate Entry In Pass Inventory..");
						session.getTransaction().rollback();
						return null;
					}
				}
				passdetailid=passdetailid+"@";
			}
			}
			
			
			String[] lugg_ticket_key=request.getParameter("lugg_ticket_key").split(",");
			String[] lugg_start_no=request.getParameter("lugg_start_no").split(",");
			String[] lugg_end_no=request.getParameter("lugg_end_no").split(",");
			String[] lugg_priority=request.getParameter("lugg_priority").split(",");
			
			String luggmasterid="";
			String luggdetid="";
			
			String luggagedeomid = tickinvdaodao.getLuggageDenomVal();
			if(request.getParameter("lugg_ticket_key")!=""){
			for(int i=0; i<lugg_start_no.length;i++){
				
				ticketinv= new TicketInventoryMaster();
				ticketinv.setDenoimination_type(luggagedeomid);
				ticketinv.setTicket_type_manual_id(4);
				ticketinv.setKey_number(lugg_ticket_key[i]);
				ticketinv.setOpening_number(lugg_start_no[i]);
				ticketinv.setClosing_number(lugg_end_no[i]);
				
				ticketinv.setNoofpasses(Integer.parseInt(lugg_end_no[i])-Integer.parseInt(lugg_start_no[i])+1);
				ticketinv.setNoofbooks((Integer.parseInt(lugg_end_no[i])-Integer.parseInt(lugg_start_no[i])+1)/50);
				
				ticketinv.setUnittype(unit_type);
				ticketinv.setUnitname(unit_name);
				ticketinv.setCurrentstatus("New");
				ticketinv.setStatus("ACTIVE");
				ticketinv.setCreated_by(usrsessionid);
				ticketinv.setCreated_date(new Date());
				ticketinv.setPriority(Integer.parseInt(lugg_priority[i]));
				
				tickinvdaodao.insertTicketInventoryMaster(ticketinv,session);
				luggmasterid=luggmasterid+ticketinv.getTicketinventorymasterid()+",";
				
				if(ticketinv.getOpening_number()>ticketinv.getClosing_number()){
					out.print("Closing No Should Be greater Than Opening No. In Luggage Inventory..");
					session.getTransaction().rollback();
					return null;
				}
				
				if((ticketinv.getOpening_number()%100)!=00 || (ticketinv.getOpening_number()%100)!=50){
					
				}else{
					out.print("Please Enter Start No. Ending With 00 Or 50 In Luggage Inventory..");
					session.getTransaction().rollback();
					return null;
				}
				if((ticketinv.getClosing_number()%100)!=49 || (ticketinv.getClosing_number()%100)!=99){
					
				}else{
					out.print("Please Enter End No. Ending With 49 Or 99 In Luggage Inventory..");
					session.getTransaction().rollback();
					return null;
				}
				
				
				
				int starttktno=ticketinv.getOpening_number();
				for(int j=0;j<ticketinv.getNoofbooks(); j++){
					ticketinvdet = new TicketInventory();
					ticketinvdet.setTicketinventorymasterid(ticketinv.getTicketinventorymasterid());
					ticketinvdet.setDenoimination_type(String.valueOf(ticketinv.getDenoimination_type()));
					ticketinvdet.setTicket_type_manual_id(ticketinv.getTicket_type_manual_id());
					ticketinvdet.setKey_number(ticketinv.getKey_number());
					ticketinvdet.setStartno(starttktno);
					ticketinvdet.setEndno(starttktno+50-1);
					starttktno=ticketinvdet.getEndno()+1;
					ticketinvdet.setNoofpasses(50);
					ticketinvdet.setNoofbooks(1);
					ticketinvdet.setUnittype(unit_type); 
					ticketinvdet.setUnitname(unit_name);
					ticketinvdet.setPartialbook("N");
					ticketinvdet.setCurrentstatus("New");
					ticketinvdet.setStatus("ACTIVE");
					ticketinvdet.setCreated_by(usrsessionid);
					ticketinvdet.setCreated_date(new Date());
					ticketinvdet.setPriority(Integer.parseInt(lugg_priority[i]));
					ticketinvdet.setStock_entry("Y");
					
					tickinvdaodao.insertTicketInventoryDetails(ticketinvdet,session);
					ticketinvdet.setCurrentstatus("In Transit");
					ticketinvdet.setCurrentstatus("Issued");
					
					luggdetid=luggdetid+ticketinvdet.getTicket_inventory_id()+",";
					updateid=updateid+ticketinvdet.getTicket_inventory_id()+",";
					int count=tickinvdaodao.checkForDuplicateTicketEntry(  Integer.toString(7),   ticketinvdet.getKey_number(),  Integer.toString(ticketinvdet.getStartno()),   Integer.toString(ticketinvdet.getEndno()), session,  Integer.toString(ticketinvdet.getTicket_type_manual_id()));
					if(count!=0){
						out.print("Duplicate Entry In Luggage Inventory..");
						session.getTransaction().rollback();
						return null;
					}
				}
				luggdetid=luggdetid+"@";
			}
			}
			
			String[] waybill_key=request.getParameter("waybill_key").split(",");
			String[] tsheet_start_no=request.getParameter("tsheet_start_no").split(",");
			String[] tsheet_end_no=request.getParameter("tsheet_end_no").split(",");
						
			String tsheetmasterid="";
			String tsheetdetid="";
			
			String tsheetdeomid = tickinvdaodao.getTsheetDenomVal();
			if(request.getParameter("waybill_key")!=""){
			for(int i=0; i<tsheet_start_no.length;i++){
				
				ticketinv= new TicketInventoryMaster();
				ticketinv.setDenoimination_type(tsheetdeomid);
				ticketinv.setTicket_type_manual_id(5);
				ticketinv.setKey_number(waybill_key[i]);
				ticketinv.setOpening_number(tsheet_start_no[i]);
				ticketinv.setClosing_number(tsheet_end_no[i]);
				
				ticketinv.setNoofpasses(Integer.parseInt(tsheet_end_no[i])-Integer.parseInt(tsheet_start_no[i])+1);
				ticketinv.setNoofbooks(0);
				
				
				ticketinv.setUnittype(unit_type);
				ticketinv.setUnitname(unit_name);
				ticketinv.setCurrentstatus("New");
				ticketinv.setStatus("ACTIVE");
				ticketinv.setCreated_by(usrsessionid);
				ticketinv.setCreated_date(new Date());
				
				
				tickinvdaodao.insertTicketInventoryMaster(ticketinv,session);
				tsheetmasterid=tsheetmasterid+ticketinv.getTicketinventorymasterid()+",";
				
				if(ticketinv.getOpening_number()>ticketinv.getClosing_number()){
					out.print("Closing No Should Be greater Than Opening No. In Trip Sheet Inventory..");
					session.getTransaction().rollback();
					return null;
				}
				
				
				
				
				
					ticketinvdet = new TicketInventory();
					ticketinvdet.setTicketinventorymasterid(ticketinv.getTicketinventorymasterid());
					ticketinvdet.setDenoimination_type(String.valueOf(ticketinv.getDenoimination_type()));
					ticketinvdet.setTicket_type_manual_id(ticketinv.getTicket_type_manual_id());
					ticketinvdet.setKey_number(ticketinv.getKey_number());
					ticketinvdet.setStartno(ticketinv.getOpening_number());
					ticketinvdet.setEndno(ticketinv.getClosing_number());
					ticketinvdet.setNoofpasses(ticketinv.getNoofpasses());
					ticketinvdet.setUnittype(unit_type); 
					ticketinvdet.setUnitname(unit_name);
					ticketinvdet.setPartialbook("N");
					ticketinvdet.setCurrentstatus("New");
					ticketinvdet.setStatus("ACTIVE");
					ticketinvdet.setCreated_by(usrsessionid);
					ticketinvdet.setCreated_date(new Date());
					ticketinvdet.setStock_entry("Y");
					
					tickinvdaodao.insertTicketInventoryDetails(ticketinvdet,session);
					ticketinvdet.setCurrentstatus("In Transit");
					ticketinvdet.setCurrentstatus("Issued");
					
					tsheetdetid=tsheetdetid+ticketinvdet.getTicket_inventory_id()+",";
					updateid=updateid+ticketinvdet.getTicket_inventory_id()+",";
					int count=tickinvdaodao.checkForDuplicateTicketEntry(  Integer.toString(36),   ticketinvdet.getKey_number(),  Integer.toString(ticketinvdet.getStartno()),   Integer.toString(ticketinvdet.getEndno()), session,  Integer.toString(ticketinvdet.getTicket_type_manual_id()));
					if(count!=0){
						out.print("Duplicate Entry In Trip Sheet Inventory..");
						session.getTransaction().rollback();
						return null;
					}
				tsheetdetid=tsheetdetid+"@";
			}
			}
			TicketInvoiceMaster ticketinvoicemaster = new TicketInvoiceMaster();
			
			ticketinvoicemaster.setIssuetounit(unit_type);
			ticketinvoicemaster.setIssuetounitid(unit_name);
			ticketinvoicemaster.setIssuefromunit(Integer.parseInt(request.getSession().getAttribute("orgchartid").toString()));
			ticketinvoicemaster.setIssuefromunitid(Integer.parseInt(request.getSession().getAttribute("orgtypeid").toString()));
			ticketinvoicemaster.setCreated_by(usrsessionid);
			ticketinvoicemaster.setStatus("ACTIVE");
			ticketinvoicemaster.setCreated_date(new Date());
			ticketinvoicemaster.setStockvalue(totalvalue);
			
			String voucherNo = tickinvdaodao.getVoucherNo();
			ticketinvoicemaster.setVoucherno(voucherNo);
			TicketInventoryLog ticketInventoryLog = new TicketInventoryLog();
			//ticketInventoryLog.setTicket_invoice_mast_id(ticketinvoicemaster.getTicket_invoice_mast_id());
			ticketInventoryLog.setAction_unit(request.getSession().getAttribute("orgchartid").toString());
			ticketInventoryLog.setAction_by(request.getSession().getAttribute("orgtypeid").toString());
			ticketInventoryLog.setCreated_by(String.valueOf(usrsessionid));   
			ticketInventoryLog.setCreated_date(new Date());
			
			IntransitProcess(ticketinvoicemaster,tktmasterid,tktdetailid,passmasterid,passdetailid,luggmasterid,luggdetid,tsheetmasterid,tsheetdetid,session,usrsessionid, ticketInventoryLog, updateid);
			IssueProcess(ticketinvoicemaster,session, ticketInventoryLog, updateid);
			
			
			session.getTransaction().commit();
			out.print("Inventory Added Successfully...");
		}
		catch(Exception e){
			e.printStackTrace();
			PrintWriter out;
			HttpServletResponse response=ServletActionContext.getResponse();
			try{
			out = response.getWriter();
			out.print("Error In Saving Inventory Data...");
			session.getTransaction().rollback();
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
		}
		finally{
			session.close();
		}
		return null;
	}
	
	
	public String getPriority()
    {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try
		{
		out = response.getWriter();
		String denom = request.getParameter("denom").toString();
		String denomkey = request.getParameter("denomkey").toString();
		String tickettype = request.getParameter("tickettype").toString();
		
		
		TicketInventoryDAO ticketinvdao = new TicketInventoryDAO();
		int maxpriority=ticketinvdao.getMaxPriority(denom,denomkey,tickettype);
		out.print(maxpriority);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
    	return null;
    }
	
	
	public String getDuplicateCheck()
    {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try
		{
		out = response.getWriter();
		String denomno = request.getParameter("denomno").toString();
		String denomkey = request.getParameter("denomkey").toString();
		String startno = request.getParameter("startno").toString();
		String endno = request.getParameter("endno").toString();
		String tickettype = request.getParameter("tickettype").toString();
		TicketInventoryDAO ticketinvdao = new TicketInventoryDAO();
		if(Integer.parseInt(tickettype)==4){
			String luggagedeomid = ticketinvdao.getLuggageDenomVal();
			denomno=luggagedeomid;
		}
		if(Integer.parseInt(tickettype)==5){
			String tsheetdeomid = ticketinvdao.getTsheetDenomVal();
			denomno=tsheetdeomid;
		}
		
		int count=ticketinvdao.checkForDuplicateTicketEntry( denomno,  denomkey, startno,  endno, session, tickettype);
		String msg="";
		if(count==0){
			msg="";
		}else{
			msg="Duplicate Data";
		}
		out.print(msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
    	return null;
    }
	
	
	public String IntransitProcess(TicketInvoiceMaster ticketinvoicemaster,String tktmasterid,String tktdetailid,String passmasterid,String passdetailid,String luggmasterid,String luggdetid,String tsheetmasterid,String tsheetdetid,Session session, int usrsessionid, TicketInventoryLog ticketInventoryLog, String updateid){
		HttpServletRequest request=ServletActionContext.getRequest();
		TicketInventoryDAO tickinvdaodao = new TicketInventoryDAO();
		TicketInventory ticketinvdet = new TicketInventory();
		ticketinvoicemaster.setCurrentstatus("In Transit");
		tickinvdaodao.insertTicketInvoiceMaster(ticketinvoicemaster,session);
		//ticketinvoicemaster.setVoucherno(String.format("%010d", ticketinvoicemaster.getTicket_invoice_mast_id()));
		
		
		TicketInvoiceDetails ticketinvoicedetail =new TicketInvoiceDetails();
		String [] tktmasterids=tktmasterid.split(",");
		String [] passmasterids=passmasterid.split(",");
		String [] luggmasterids=luggmasterid.split(",");
		String [] tsheetmasterids=tsheetmasterid.split(",");
		
		String [] tktdetailids = tktdetailid.split("@");
		if(request.getParameter("tkt_denom_no")!=""){
		for(int i=0; i<tktmasterids.length; i++){
			String [] tktdetailidss=tktdetailids[i].split(",");
			for(int j=0;j<tktdetailidss.length;j++){
				ticketinvoicedetail =new TicketInvoiceDetails();
				ticketinvoicedetail.setTicketinvoicemasterid(ticketinvoicemaster.getTicket_invoice_mast_id());
				ticketinvoicedetail.setTicketinventorymastid(Integer.parseInt(tktmasterids[i]));
				//System.out.pri
				ticketinvoicedetail.setTicketinventorydetailstid(Integer.parseInt(tktdetailidss[j]));
				ticketinvoicedetail.setCreated_by(usrsessionid);
				ticketinvoicedetail.setCreated_date(new Date());
				tickinvdaodao.insertTicketInvoiceDetail(ticketinvoicedetail, session);
				ticketinvdet = new TicketInventory();
				//ticketinvdet.setTicketinventorymasterid(ticketinventorymasterid)
				
			}
		}
		}
		
		String [] passdetailids = passdetailid.split("@");
		if(request.getParameter("pass_pass_type")!=""){
		for(int i=0; i<passmasterids.length; i++){
			String [] passdetailidss=passdetailids[i].split(",");
			for(int j=0;j<passdetailidss.length;j++){
				ticketinvoicedetail =new TicketInvoiceDetails();
				ticketinvoicedetail.setTicketinvoicemasterid(ticketinvoicemaster.getTicket_invoice_mast_id());
				ticketinvoicedetail.setTicketinventorymastid(Integer.parseInt(passmasterids[i]));
				ticketinvoicedetail.setTicketinventorydetailstid(Integer.parseInt(passdetailidss[j]));
				ticketinvoicedetail.setCreated_by(usrsessionid);
				ticketinvoicedetail.setCreated_date(new Date());
				tickinvdaodao.insertTicketInvoiceDetail(ticketinvoicedetail, session);
			}
		}
		}
		
		
		String [] luggdetids = luggdetid.split("@");
		if(request.getParameter("lugg_ticket_key")!=""){
		for(int i=0; i<luggmasterids.length; i++){
			String []luggdetidss=luggdetids[i].split(",");
			for(int j=0;j<luggdetidss.length;j++){
				ticketinvoicedetail =new TicketInvoiceDetails();
				ticketinvoicedetail.setTicketinvoicemasterid(ticketinvoicemaster.getTicket_invoice_mast_id());
				ticketinvoicedetail.setTicketinventorymastid(Integer.parseInt(luggmasterids[i]));
				ticketinvoicedetail.setTicketinventorydetailstid(Integer.parseInt(luggdetidss[j]));
				ticketinvoicedetail.setCreated_by(usrsessionid);
				ticketinvoicedetail.setCreated_date(new Date());
				tickinvdaodao.insertTicketInvoiceDetail(ticketinvoicedetail, session);
			}
		}
		}
		
		String [] tsheetdetids = tsheetdetid.split("@");
		if(request.getParameter("waybill_key")!=""){
		for(int i=0; i<tsheetmasterids.length; i++){
			String []tsheetdetidss=tsheetdetids[i].split(",");
			for(int j=0;j<tsheetdetidss.length;j++){
				ticketinvoicedetail =new TicketInvoiceDetails();
				ticketinvoicedetail.setTicketinvoicemasterid(ticketinvoicemaster.getTicket_invoice_mast_id());
				ticketinvoicedetail.setTicketinventorymastid(Integer.parseInt(tsheetmasterids[i]));
				ticketinvoicedetail.setTicketinventorydetailstid(Integer.parseInt(tsheetdetidss[j]));
				ticketinvoicedetail.setCreated_by(usrsessionid);
				ticketinvoicedetail.setCreated_date(new Date());
				tickinvdaodao.insertTicketInvoiceDetail(ticketinvoicedetail, session);
			}
		}
		}
		
		String updateids[] =updateid.split(",");
		TicketInventory ticketinvdetail = new TicketInventory();
		for(int k=0;k<updateids.length; k++){
			ticketinvdetail = new TicketInventory();
			ticketinvdetail.setTicket_inventory_id(Integer.parseInt(updateids[k]));
			ticketinvdetail.setCurrentstatus("In Transit");
			//tickinvdaodao.updateTicketinvdetail(ticketinvdetail, session);
		}
		
		ticketInventoryLog.setInvoice_status("In Transit");
		ticketInventoryLog.setTransaction_on_type("Issued");
		ticketInventoryLog.setTicket_invoice_mast_id(ticketinvoicemaster.getTicket_invoice_mast_id());
		tickinvdaodao.insertTicketInventoryLog(ticketInventoryLog,session);
		
		return "success";
	}
	
	public String IssueProcess(TicketInvoiceMaster ticketinvoicemaster,Session session, TicketInventoryLog ticketInventoryLog, String updateid){
		TicketInventoryDAO tickinvdaodao = new TicketInventoryDAO();
		ticketinvoicemaster.setCurrentstatus("Issued");
		tickinvdaodao.insertTicketInvoiceMaster(ticketinvoicemaster,session);
		
		
		
		String updateids[] =updateid.split(",");
		TicketInventory ticketinvdetail = new TicketInventory();
		for(int k=0;k<updateids.length; k++){
			ticketinvdetail = new TicketInventory();
			ticketinvdetail.setTicket_inventory_id(Integer.parseInt(updateids[k]));
			ticketinvdetail.setCurrentstatus("Issued");
			//tickinvdaodao.updateTicketinvdetail(ticketinvdetail, session);
		}
		ticketInventoryLog.setInvoice_status("Accepted");
		ticketInventoryLog.setTransaction_on_type("Accepted");
		ticketInventoryLog.setTicket_invoice_mast_id(ticketinvoicemaster.getTicket_invoice_mast_id());
		tickinvdaodao.insertTicketInventoryLog(ticketInventoryLog,session);
		return "success";
	}
	

}
