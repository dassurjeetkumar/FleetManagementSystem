package com.trimax.its.pass.action;

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
import com.trimax.its.pass.dao.PassDistanceTypeDAO;
import com.trimax.its.pass.dao.PassDurationTypeDAO;
import com.trimax.its.pass.model.PassDistanceType;
import com.trimax.its.pass.model.PassDurationType;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.pass.model.PassType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class PassDurationTypeAction extends ActionSupport{
	
	public PassDurationType getPassduration() {
		return passduration;
	}
	public void setPassduration(PassDurationType passduration) {
		this.passduration = passduration;
	}
	public PassDurationType passduration;

	 private Map<Integer, String> passtype1;
		private Map<Integer, String> passsubtype;
		
		public String pass_type_name;
		public String pass_type_id;
		
		
		
	
	public String getPass_type_id() {
			return pass_type_id;
		}
		public void setPass_type_id(String pass_type_id) {
			this.pass_type_id = pass_type_id;
		}
	public String getPass_type_name() {
			return pass_type_name;
		}
		public void setPass_type_name(String pass_type_name) {
			this.pass_type_name = pass_type_name;
		}
	public Map<Integer, String> getPasstype1() {
			return passtype1;
		}
		public void setPasstype1(Map<Integer, String> passtype1) {
			this.passtype1 = passtype1;
		}
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
	public void passdurationtypeview(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			PassDurationTypeDAO dao = new PassDurationTypeDAO();
			String[] dbcol={"","ID","NAME","PASS_TYPE_NAME","PASS_SUB_TYPE","PASS_START_DATE","PASS_EXPIRY_DATE","STATUS","Remarks"};
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
			total =dao.getTotalRecordsForPassDuration(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=dao.getPassDurationList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SkipValidation
	public String createpassduration(){
		passtype1=getPassTypeList();
		passsubtype=getPassSubType();
		return "success";
	}
	
	public Map<Integer, String> getPassTypeList() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from PassType where deleted_status=0");
		try {
			List<PassType> list = query.list();
			for (PassType type : list) {
				resultMap.put(type.getPass_type_id(), type.getPass_type_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	public Map<Integer, String> getPassSubType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from PassSubType where status = 'Y'");
		try {
			List<PassSubType> list = query.list();
			for (PassSubType type : list) {
				resultMap.put(type.getPass_subtype_id(), type.getSub_type_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	
	public String savecreatepassduration(){
		
        HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("hiiiiii");
		PassDurationTypeDAO dao = new PassDurationTypeDAO();
		Common common = new Common();
		try{
			System.out.println(passduration.getPass_duration_name().trim());
			passduration.setPass_duration_name(passduration.getPass_duration_name().trim());
			passduration.setPass_type_id(passduration.getPass_type_id());
			passduration.setPass_sub_type_id(passduration.getPass_sub_type_id());
			String Pass_start_date=common.getDateFromPicker(passduration.getPass_start_date().toString());
			String Pass_expiry_date=common.getDateFromPicker(passduration.getPass_expiry_date().toString());
			System.out.println("Pass_start_date==="+Pass_start_date+"  Pass_expity_date===="+Pass_expiry_date);
			passduration.setPass_start_date(Pass_start_date);
			passduration.setPass_expiry_date(Pass_expiry_date);
			passduration.setStatus(passduration.getStatus());
			passduration.setRemarks(passduration.getRemarks().trim());
			passduration.setCreated_date(new java.util.Date());
			passduration.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			passduration.setDeleted_status(0);
			passduration.setUpdated_by(0);
			passduration.setUpdated_date(null);
		System.out.println("passduration.getStatus()=="+passduration.getStatus());
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
		int i=dao.addPassDurationType(passduration);
		
		if(i>0){
			addActionMessage("Pass Duration Type id "+i+" created successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_duration_name","Pass Duration Type already exist.");
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
		PassDurationTypeDAO dao = new PassDurationTypeDAO();
		passtype1=getPassTypeList();
		passsubtype=getPassSubType();
		passduration=dao.getPassDurationTypeById(id);
		int passtypeid=passduration.getPass_type_id();
		String pass_name=getPassName(passtypeid);
//		setPass_type_name(pass_name);
		
		return "success";
	}
	
public String getPassName(int id){
		
		Session session=null;
		Transaction tx=null;
		PassType passType=null;
		String pass_name="";
		
		try{
		session=HibernateUtil.getSession("");
		
		String query="from PassType where pass_type_id="+id+" and deleted_status=0";
		
		List list=session.createQuery(query).list();
		
		passType=(PassType)list.get(0);
		pass_name=passType.getPass_type_name();
		System.out.println("passType==="+passType.getPass_type_name());
		
		}
		catch(Exception e){
			e.printStackTrace();

		}
		finally{
			 if(session.isOpen()){
				 session.close();
			 }
			}
		return pass_name;
	}
	
	public String editPassDuration(){
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
	String status=dc.getStatus(passduration.getPass_duration_id(),"passdurationtype");
	System.out.println("status---"+status);
	if(status.equals("")||(!status.equals("") &&  passduration.getStatus().equals("Active"))){
		
		PassDurationTypeDAO dao = new PassDurationTypeDAO();
		
		passduration.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passduration.setUpdated_date(new java.util.Date());
		
		int i=dao.updatePassDurationType(passduration);
		
		if(i>0){
			addActionMessage("Pass Duration Type id "+passduration.getPass_duration_id()+" updated successfully.");
		}else{
			if(i==-1){
				addFieldError("pass_duration_name","Pass Duration Type already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
	}else
	{
		
		if(passduration.getStatus().equals("Inactive"))
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
	public String deletePassDurationforsmartcard() {
		
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
		PassDurationTypeDAO dao = new PassDurationTypeDAO();
		status=dao.deletePassDurationType(id);
		if(status.split(":")[0].equals("success")){
			
			addActionMessage("Pass Distance Type id "+id+" deleted successfully.");		
			status="success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Pass Distance Type id "+id+" deleted successfully.");		
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
		
//		if(getCreatePassIssuerType()!=0){
			if (passduration.getPass_duration_name() == null || passduration.getPass_duration_name().trim().equals("")) {
				addFieldError("pass_duration_name","Please enter Pass Duration Name");
			}
			
			if (passduration.getStatus() == null || passduration.getStatus().trim().equals("")) {
			addFieldError("getStatus","Please enter Pass Duration Status");
			}
		
//		super.validate();
//		
//		}
	}

}
