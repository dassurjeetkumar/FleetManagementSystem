package com.trimax.its.fare.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.fare.dao.ConcessionDao;
import com.trimax.its.fare.dao.PassengerTypeDao;
import com.trimax.its.fare.model.Concession;
import com.trimax.its.pass.dao.PassRateDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class ConcessionAction  extends ActionSupport implements Preparable{
	private Concession concession;
	private int cid;
	private Map<Integer,String> passengerTypeMap;
	
	public Concession getConcession() {
		return concession;
	}

	public void setConcession(Concession concession) {
		this.concession= concession;
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
		
		Common common=new Common();
		
		ConcessionDao dao=new ConcessionDao();
		concession=dao.getConcessionById(cid);
		
		concession.setEffectiveStartDate(common.getDateFromDbToPicker(concession.getEffectiveStartDate()));
				
		if(concession.getEffectiveEndDate()!=null){
		 concession.setEffectiveEndDate(common.getDateFromDbToPicker(concession.getEffectiveEndDate()));
		}
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ConcessionAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String para=request.getParameter("cid");
		if(delete.equalsIgnoreCase("Y")){
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}
		
		ConcessionDao dao=new ConcessionDao();
		String s=dao.deleteConcession(cid);
		

		if(s.equals("success")){
			addActionMessage("Concession id "+cid+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
		
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}
	
	public String Add(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ConcessionAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		//compare start & end dates
		try{
//			if(concession.getEffectiveEndDate().trim().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date1=sdf.parse(concession.getEffectiveStartDate());
				Date date2=sdf.parse(concession.getEffectiveEndDate());
				
				if(date1.compareTo(date2)>0){
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		
	        		return INPUT;
	        		
	        	}else if(date1.compareTo(date2)==0){
	        		addActionError("Effective End Date should be greater than Effective Start Date");
	        		return INPUT;
	        		
	        	}
//			}
			}catch(Exception e){e.printStackTrace();}
		
		
		ConcessionDao dao=new ConcessionDao();
		int i=dao.addConcession(concession);
		
		if(i>0){
			addActionMessage("Concession id "+i+" created successfully.");
		}else{
			if(i==-2){
				addActionError("Concession already exist.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ConcessionAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		//compare start & end dates
		try{
//					if(concession.getEffective_end_date().trim().length()>0){
						
						//validate str& end date
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date date1=sdf.parse(concession.getEffectiveStartDate());
			Date date2=sdf.parse(concession.getEffectiveEndDate());
						
			if(date1.compareTo(date2)>0){
			  addActionError("Effective End Date should be greater than Effective Start Date");
			 return INPUT;	
			}else if(date1.compareTo(date2)==0){
			    addActionError("Effective End Date should be greater than Effective Start Date");
			    return INPUT;
			}
//					}
			}catch(Exception e){e.printStackTrace();}
		
		ConcessionDao dao=new ConcessionDao();

		int i=dao.updateConcession(concession);
		
		if(i>0){
			addActionMessage("Concession id "+concession.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addActionError("Concession already exist.");
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
	
	public void validate(){
	
		if(concession.getConcessionName()==null || concession.getConcessionName().trim().isEmpty()){
			addFieldError("concessionName","Please enter Concession name");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(concession.getConcessionName())){
				addFieldError("concessionName","Special characters not allowed"); 
			 }
		}	
		
		if(concession.getPassengerType().getId()<=0){
			addFieldError("passengerType","Please select Passenger Type");
		}
		
		if(concession.getPercentage()<=0){
			addFieldError("percentage","Please enter Percentage");
		}else{
			if(concession.getPercentage()>100){
				addFieldError("percentage","Please enter valid Percentage value");
			}
		}
		
		if(concession.getEffectiveStartDate()==null || concession.getEffectiveStartDate().isEmpty()){
			addFieldError("effectiveStartDate","Please select Effective Start Date");
		}
		
		if(concession.getEffectiveEndDate()==null || concession.getEffectiveEndDate().isEmpty()){
			addFieldError("effectiveEndDate","Please select Effective End Date");
		}
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			ConcessionDao dao=new ConcessionDao();

			String[] dbcol={"","id","concessionName","passengerType.passengerTypeName","percentage","note","effectiveStartDate","effectiveEndDate","status"};

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
				if (col < 0 || col > 8)
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

	@Override
	public void prepare() throws Exception {
		ConcessionDao dao = new ConcessionDao();
		
		setPassengerTypeMap(dao.getPassengerType());
		
	}

	public Map<Integer,String> getPassengerTypeMap() {
		return passengerTypeMap;
	}

	public void setPassengerTypeMap(Map<Integer,String> passengerTypeMap) {
		this.passengerTypeMap = passengerTypeMap;
	}
}
