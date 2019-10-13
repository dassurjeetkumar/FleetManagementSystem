package com.trimax.its.pis.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.pis.dao.PISCustomerDao;
import com.trimax.its.pis.model.PISCustomer;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PISCustomerAction  extends ActionSupport{
	private PISCustomer pisCustomer;
	private int cid;

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

		PISCustomerDao dao=new PISCustomerDao();
		pisCustomer=dao.getPisCustomerById(cid);
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "customerView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		String para=request.getParameter("cid");
		
		if(para!=null && para.isEmpty()){
		cid=Integer.parseInt(para);
		}
		
		PISCustomerDao dao=new PISCustomerDao();
		String s=dao.deletePisCustomer(cid);
		

		if(s.equals("success")){
			addActionMessage("Customer id "+cid+" deleted successfully.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "customerView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		PISCustomerDao dao=new PISCustomerDao();
		if(create.equalsIgnoreCase("Y")){
		int i=dao.addPisCustomer(pisCustomer);
		
		if(i>0){
			addActionMessage("Customer id "+i+" created successfully.");
		}else{
			if(i==-2){
				addActionError("Customer already exist.");
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "customerView.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();	
		PISCustomerDao dao=new PISCustomerDao();
		if(edit.equalsIgnoreCase("Y")){
		int i=dao.updatePisCustomer(pisCustomer);
		
		if(i>0){
			addActionMessage("Customer id "+pisCustomer.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addActionError("Customer already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		 return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public void validate(){
	
		if(pisCustomer.getCustName()==null || pisCustomer.getCustName().trim().isEmpty()){
			addFieldError("custName","Please Enter Customer Name");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustName())){
				addFieldError("custName","Special characters not allowed"); 
			 }
		}
		
		if(pisCustomer.getCustCity()==null || pisCustomer.getCustCity().trim().isEmpty()){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustCity())){
				addFieldError("custCity","Special characters not allowed"); 
			 }
		}
		
		if(pisCustomer.getCustState()==null || pisCustomer.getCustState().trim().isEmpty()){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustState())){
				addFieldError("custState","Special characters not allowed"); 
			 }
		}
		
		if(pisCustomer.getCustCountry()==null || pisCustomer.getCustCountry().trim().isEmpty()){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustCountry())){
				addFieldError("custCountry","Special characters not allowed"); 
			 }
		}
		
		if(pisCustomer.getCustEmail()==null || pisCustomer.getCustEmail().trim().isEmpty()){	
			addFieldError("custEmail","Please Enter Email");
		}else{
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isValidEmailId(pisCustomer.getCustEmail())){
				addFieldError("custEmail","Please Enter Valid Email Id"); 
			 }
		}
		
		if(pisCustomer.getCustWebsite()==null || pisCustomer.getCustWebsite().trim().isEmpty()){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustWebsite())){
				//addFieldError("custWebsite","Special characters not allowed"); 
			 }
		}
		
		if(pisCustomer.getCustPhone()!=null && pisCustomer.getCustPhone().trim().length()>0){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustPhone())){
				addFieldError("custPhone","Special characters not allowed"); 
			 }else{
				if(!cv.isValidPhoneNum(pisCustomer.getCustPhone())){
					addFieldError("custPhone","Please Enter valid Phone Number"); 	
				}else{
					if(!cv.isNumber(pisCustomer.getCustPhone())){
						addFieldError("custCell","Please Enter valid Phone Number");	
					}
				}
			 }
		}
		
		if(pisCustomer.getCustCell()!=null && pisCustomer.getCustCell().trim().length()>0){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustCell())){
				addFieldError("custCell","Special characters not allowed"); 
			 }else{
					if(!(pisCustomer.getCustCell().length()<=12 && pisCustomer.getCustCell().length()>=10) && !cv.isValidMobileNum(pisCustomer.getCustCell(),12)){
						addFieldError("custCell","Please Enter valid Mobile Number"); 	
					}else{
						if(!cv.isNumber(pisCustomer.getCustCell())){
							addFieldError("custCell","Please Enter valid Mobile Number");	
						}
					}
					
			 }
		}
		
		if(pisCustomer.getCustContactPersonName()!=null || !pisCustomer.getCustContactPersonName().trim().isEmpty()){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustContactPersonName())){
				addFieldError("custContactPersonName","Special characters not allowed"); 
			 }
		}
		
		if(pisCustomer.getCustContactPersonEmail()!=null && pisCustomer.getCustContactPersonEmail().trim().length()>0){	
			 CommonValidation cv=new CommonValidation();
			 
			 if (!cv.isValidEmailId(pisCustomer.getCustContactPersonEmail())){
					addFieldError("custContactPersonEmail","Please Enter Valid Email Id"); 
			 }
		}
		
		if(pisCustomer.getCustContactPersonPhone()!=null && pisCustomer.getCustContactPersonPhone().trim().length()>0){	
			 CommonValidation cv=new CommonValidation();
			 if (!cv.isSpecialChar(pisCustomer.getCustContactPersonPhone())){
				addFieldError("custContactPersonPhone","Special characters not allowed"); 
			 }else{
					if(!cv.isValidPhoneNum(pisCustomer.getCustContactPersonPhone())){
						addFieldError("custContactPersonPhone","Please Enter valid Contact Person Phone Number"); 	
					}else{
						if(!cv.isNumber(pisCustomer.getCustContactPersonPhone())){
							addFieldError("custCell","Please Enter valid Contact Person Phone Number");	
						}
					}
			 }
		}
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			PISCustomerDao dao=new PISCustomerDao();

			String[] dbcol={"","id","custName","custAddress","custCity","custState","custCountry","custEmail",
					"custWebsite","custPhone","custCell","custContactPersonName","custContactPersonEmail","custContactPersonPhone",
					"status"};

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
			
			int total =  dao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);

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

	public PISCustomer getPisCustomer() {
		return pisCustomer;
	}

	public void setPisCustomer(PISCustomer pisCustomer) {
		this.pisCustomer = pisCustomer;
	}
}
