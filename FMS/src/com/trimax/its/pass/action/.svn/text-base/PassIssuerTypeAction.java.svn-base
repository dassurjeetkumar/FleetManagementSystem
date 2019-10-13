package com.trimax.its.pass.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.pass.dao.PassIssuerTypeDAO;
import com.trimax.its.pass.dao.PassTypeDao;
import com.trimax.its.pass.model.PassIssuerType;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.usermanagement.dao.EmployeeDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;


public class PassIssuerTypeAction extends ActionSupport{
	public PassIssuerType passissuer;
	
	
	public PassIssuerType getPassissuer() {
		return passissuer;
	}
	public void setPassissuer(PassIssuerType passissuer) {
		this.passissuer = passissuer;
	}
	
private Map<Integer,String> passsubtype;
	
	
	public Map<Integer, String> getPasssubtype() {
		return passsubtype;
	}

	public void setPasssubtype(Map<Integer, String> passsubtype) {
		this.passsubtype = passsubtype;
	}
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	private String DIR;
	private int START;
	@SkipValidation
	public void passissueryypeview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassIssuerTypeDAO dao=new PassIssuerTypeDAO();
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
			total =dao.getTotalRecordsForPassIssuer(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPassIssuerList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createpassissuer(){
		return "success";
	}
	public String savecreatepassissuer(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassIssuerTypeDAO dao=new PassIssuerTypeDAO();
		try{
			System.out.println(passissuer.getPass_issuer_name().trim());
		passissuer.setPass_issuer_name(passissuer.getPass_issuer_name().trim());
		passissuer.setRemarks(passissuer.getRemarks().trim());
		passissuer.setCreated_date(new java.util.Date());
		passissuer.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passissuer.setDeleted_status(0);
		passissuer.setUpdated_by(0);
		passissuer.setUpdated_date(null);
		
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
		int i=dao.addPassIssuerType(passissuer);
		
		if(i>0){
			addActionMessage("Pass Issuer Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_issuer_name","Pass Issuer Type already exist.");
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
		PassIssuerTypeDAO dao=new PassIssuerTypeDAO();
		passissuer=dao.getPassIssuerTypeById(id);
		
		return "success";
	}
	
	public String editPassIssuer(){
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
	String status=dc.getStatus(passissuer.getPass_issuer_id(),"passissuertype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passissuer.getStatus().equals("Active"))){
		
		PassIssuerTypeDAO dao=new PassIssuerTypeDAO();
		
		passissuer.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passissuer.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassIssuerType(passissuer);
		
		if(i>0){
			addActionMessage("Pass Issuer Type id "+passissuer.getPass_issuer_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("getPass_issuer_name","Pass Issuer Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passissuer.getStatus().equals("Inactive"))
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
	public String deletePassIssuerforsmartcard() {
		
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
		PassIssuerTypeDAO dao=new PassIssuerTypeDAO();
		status=dao.deletePassIssuerType(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Issuer Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Issuer Type id "+id+" deleted successfully.");		
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
		try{
//		if(getCreatePassIssuerType()!=0){
			if (passissuer.getPass_issuer_name() == null || passissuer.getPass_issuer_name().trim().equals("")) {
				addFieldError("getPass_issuer_name","Please enter Happy Hour Name");
			}
			if (passissuer.getStatus() == null || passissuer.getStatus().trim().equals("")) {
			addFieldError("getStatus","Please enter Pass Issuer Status");
			}
		
//		super.validate();
//		
//		}
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
//		
	}
	
	@SkipValidation
	public String getsubTypeDetails() {
		System.out.println("hiiiiiii");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			int sctype=Integer.parseInt(request.getParameter("val")) ;
			System.out.println("sctype"+sctype);
			String regionTypeAjaxString = "";
			List<PassSubType> sybtypelist=getSubtypelist(sctype);
			regionTypeAjaxString +="<option value=0 id='0'>Select</option>";
			for (int i = 0; i < sybtypelist.size(); i++) {
				regionTypeAjaxString +="<option value=" + sybtypelist.get(i).getPass_subtype_id()+ " id='"+ sybtypelist.get(i).getPass_subtype_id()+"'>" + sybtypelist.get(i).getSub_type_name() + "</option>";
			}
		    HttpServletResponse response = ServletActionContext.getResponse();
		    PrintWriter out;


			out = response.getWriter();
			out.print(regionTypeAjaxString);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return null;
		}

	 }
    @SuppressWarnings("finally")
	public List<PassSubType> getSubtypelist(int passtype) {
		List<PassSubType> sublist = null;
		Session session = null;
		try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="SELECT pass_subtype_id , sub_type_name FROM pass_sub_type WHERE pass_type_id= "+passtype+" " +
				"AND status = 'y'";
         Query query = session.createSQLQuery(sql)
				.addScalar("pass_subtype_id")
				.addScalar("sub_type_name", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(PassSubType.class));
		sublist=query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
			return sublist;
		}
	}
}
