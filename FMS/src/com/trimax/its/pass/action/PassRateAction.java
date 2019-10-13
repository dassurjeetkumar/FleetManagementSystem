package com.trimax.its.pass.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.pass.dao.PassRateDao;
import com.trimax.its.pass.model.PassRate;
import com.trimax.its.pass.model.PassSubType;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class PassRateAction extends ActionSupport implements Preparable{
	
	private PassRate passRate;
	private Map<Integer,String> passTypeMap;
	private Map<Integer,String> passPassengerTypeMap;
	private Map<Integer,String> passissuerTypeMap;
	private Map<Integer,String> passpurchaseTypeMap;
	private Map<Integer,String> passdistanceTypeMap;
	private Map<Integer,String> passdurationTypeMap;
	private Map<Integer,String> ServiceTypeMap;
	private Map<Integer,String> castcategory;
	private Map<Integer,String> passsubtype;
	
	
	public Map<Integer, String> getPasssubtype() {
		return passsubtype;
	}

	public void setPasssubtype(Map<Integer, String> passsubtype) {
		this.passsubtype = passsubtype;
	}

	public Map<Integer, String> getCastcategory() {
		return castcategory;
	}

	public void setCastcategory(Map<Integer, String> castcategory) {
		this.castcategory = castcategory;
	}

	public Map<Integer, String> getServiceTypeMap() {
		return ServiceTypeMap;
	}

	public void setServiceTypeMap(Map<Integer, String> serviceTypeMap) {
		ServiceTypeMap = serviceTypeMap;
	}

	public Map<Integer, String> getPassPassengerTypeMap() {
		return passPassengerTypeMap;
	}

	public void setPassPassengerTypeMap(Map<Integer, String> passPassengerTypeMap) {
		this.passPassengerTypeMap = passPassengerTypeMap;
	}

	public Map<Integer, String> getPassissuerTypeMap() {
		return passissuerTypeMap;
	}

	public void setPassissuerTypeMap(Map<Integer, String> passissuerTypeMap) {
		this.passissuerTypeMap = passissuerTypeMap;
	}

	public Map<Integer, String> getPasspurchaseTypeMap() {
		return passpurchaseTypeMap;
	}

	public void setPasspurchaseTypeMap(Map<Integer, String> passpurchaseTypeMap) {
		this.passpurchaseTypeMap = passpurchaseTypeMap;
	}

	public Map<Integer, String> getPassdistanceTypeMap() {
		return passdistanceTypeMap;
	}

	public void setPassdistanceTypeMap(Map<Integer, String> passdistanceTypeMap) {
		this.passdistanceTypeMap = passdistanceTypeMap;
	}

	public Map<Integer, String> getPassdurationTypeMap() {
		return passdurationTypeMap;
	}

	public void setPassdurationTypeMap(Map<Integer, String> passdurationTypeMap) {
		this.passdurationTypeMap = passdurationTypeMap;
	}

	public PassRate getPassRate() {
		return passRate;
	}

	public void setPassRate(PassRate passRate) {
		this.passRate = passRate;
	}

	public Map<Integer, String> getPassTypeMap() {
		return passTypeMap;
	}

	public void setPassTypeMap(Map<Integer, String> passTypeMap) {
		this.passTypeMap = passTypeMap;
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
		Common common=new Common();
		
		PassRateDao dao=new PassRateDao();
		passRate=dao.getPassRateById(id);
		
		passRate.setEffective_start_date(common.getDateFromDbToPicker(passRate.getEffective_start_date()));
		
		
		if(passRate.getEffective_end_date()!=null){
			
			passRate.setEffective_end_date(common.getDateFromDbToPicker(passRate.getEffective_end_date()));
		}
		
		return "edit";
	}
	
	@SkipValidation
	public String delete() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassRateAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		int id=Integer.parseInt(request.getParameter("id"));
		
		PassRateDao dao=new PassRateDao();
		String s=dao.deletePassRate(id);
		

		if(s.equals("success")){
			addActionMessage("Pass Rate id "+id+" deleted successfully.");
		}else{
			addActionMessage("Database error retry again.");
		}
		
		return "success";
		}
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
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassRateAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		//compare start & end dates
		try{
//			if(passRate.getEffective_end_date().trim().length()>0){
//				
//				//validate str& end date
//				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//				Date date1=sdf.parse(passRate.getEffective_start_date());
//				Date date2=sdf.parse(passRate.getEffective_end_date());
//				
//				if(date1.compareTo(date2)>0){
//	        		System.out.println("Date1 is after Date2");
//	        		addActionError("Effective End Date should be greater than Effective Start Date");
//	        		
//	        		return INPUT;
//	        		
//	        	}else if(date1.compareTo(date2)==0){
//	        		System.out.println("Date1 is equal to Date2");
//	        		addActionError("Effective End Date should be greater than Effective Start Date");
//	        		return INPUT;
//	        		
//	        	}
//			}
			
			
			
		
		
		}catch(Exception e){e.printStackTrace();}
		Common common = new Common();
		PassRateDao dao=new PassRateDao();
		passRate.setPassenger_type(passRate.getPassenger_type());
		passRate.setPass_issuer_type(passRate.getPass_issuer_type());
		passRate.setService_type_id(passRate.getService_type_id());
		passRate.setPass_type_id(passRate.getPass_type_id());
		passRate.setPass_sub_type_id(passRate.getPass_sub_type_id());
		passRate.setPass_purchase_type(passRate.getPass_purchase_type());
		passRate.setPass_distance_type(passRate.getPass_distance_type());
		passRate.setPass_duration_type(passRate.getPass_duration_type());
		passRate.setValidity_months(passRate.getValidity_months());
		passRate.setPass_amount(passRate.getPass_amount());
		passRate.setService_tax_amount(passRate.getService_tax_amount());
		passRate.setProcessing_amount(passRate.getProcessing_amount());
		passRate.setTotal_amount(passRate.getTotal_amount());
		passRate.setPass_start_date(passRate.getPass_start_date());
		passRate.setPass_expiry_date(passRate.getPass_expiry_date());
		passRate.setPass_start_time(passRate.getPass_start_time());
		passRate.setPass_end_time(passRate.getPass_end_time());
		passRate.setPass_to_validate(passRate.getPass_to_validate());
		passRate.setUpgrade_availability(passRate.getUpgrade_availability());
		passRate.setRoute_to_validate(passRate.getRoute_to_validate());
		passRate.setException_day(passRate.getException_day());
		passRate.setExtension_expiry_date(passRate.getExtension_expiry_date());
		passRate.setEffective_start_date(common.getDateFromPicker(passRate.getEffective_start_date()));
		passRate.setEffective_end_date(common.getDateFromPicker(passRate.getEffective_end_date()));
		passRate.setExtension_status(passRate.getExtension_status());
		passRate.setNotes(passRate.getNotes());
		passRate.setVersion_number(passRate.getVersion_number().trim());
		passRate.setCreated_date(new java.util.Date());
		passRate.setCreated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passRate.setDeleted_status(0);
		passRate.setUpdated_by(0);
		passRate.setUpdated_date(null);

		//int i=dao.addPassRate(passRate); before code for pass rate
		int i=dao.addPassRateNew(passRate);
		
		if(i>0){
			addActionMessage("Pass Rate id "+i+" created successfully.");
		}else{
			if(i==-2){
				addActionError("Pass Rate already exist.");
				return INPUT;
			}else{
				addActionError("Database error retry again.");
				return INPUT;
			}
		}
		//execute();
		return SUCCESS;
		}
		else{
			return INPUT;
			
		}
	}
	
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "PassRateAction!view");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		//compare start & end dates
//				try{
//					if(passRate.getEffective_end_date().trim().length()>0){
//						
//						//validate str& end date
//						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//						Date date1=sdf.parse(passRate.getEffective_start_date());
//						Date date2=sdf.parse(passRate.getEffective_end_date());
//						
//						if(date1.compareTo(date2)>0){
//			        		System.out.println("Date1 is after Date2");
//			        		addActionError("Effective End Date should be greater than Effective Start Date");
//			        		
//			        		return INPUT;
//			        		
//			        	}else if(date1.compareTo(date2)==0){
//			        		System.out.println("Date1 is equal to Date2");
//			        		addActionError("Effective End Date should be greater than Effective Start Date");
//			        		return INPUT;
//			        		
//			        	}
//					}
//					}catch(Exception e){e.printStackTrace();}
		
		PassRateDao dao=new PassRateDao();
		
		passRate.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		passRate.setUpdated_date(new java.util.Date());
		System.out.println("==="+Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		//int i=dao.updatePassRate(passRate);  before Changes in rate master
		int i=dao.updatePassRateNew(passRate);
		
		if(i>0){
			addActionMessage("Pass Rate id "+passRate.getId()+" updated successfully.");
		}else{
			if(i==-2){
				addActionError("Pass Rate already exist.");
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
	
		CommonValidation cv=new CommonValidation();
		
//		if(passRate.getPassType()==null || passRate.getPassType().getPass_type_id()==0){
//			addFieldError("passType.pass_type_id","Please select Pass Type");
//		}
//		
//		if(passRate.getEffective_start_date()==null || passRate.getEffective_start_date().length()<=0){
//			addFieldError("effective_start_date","Please select Effective Start Date");
//		}
//		
//		if(passRate.getVersionNumber()==null || passRate.getVersionNumber().trim().equals("")){
//			addFieldError("versionNumber","Please enter Version Number.");	
//		}
//		else{
//			if(cv.isNumberic(passRate.getVersionNumber())){
//				addFieldError("versionNumber","Version Number should be number");
//			}
//		}
		
		
//		if(passRate.getAmount()<=0){
//			addFieldError("amount","Please enter Amount");
//		}
		
		if(passRate.getPassenger_type()==0){
			addFieldError("passenger_type","Please select Passenger Type");
		}
		if(passRate.getPass_issuer_type()==0){
			addFieldError("pass_issuer_type","Please select Pass Issuer Type");
		}
		if(passRate.getPass_type_id()==0){
			addFieldError("pass_type_id","Please select Pass Type");
		}
//		if(passRate.getPass_sub_type_id()==0){
//			addFieldError("pass_sub_type_id","Please Enter Pass Sub Type");
//		}
		if(passRate.getPass_purchase_type()==0){
			addFieldError("pass_purchase_type","Please Enter Pass Purchase Type");
		}
//		if(passRate.getPass_distance_type()==0){
//			addFieldError("pass_distance_type","Please Enter Pass Distance Type");
//		}
//		if(passRate.getPass_duration_type()==0){
//			addFieldError("pass_duration_type","Please Enter Pass Duration Type");
//		}
		if(passRate.getValidity_months()==null || passRate.getValidity_months().equalsIgnoreCase("")){
			addFieldError("validity_months","Please Enter Validity Month");
		}
		if(passRate.getPass_amount()==null || passRate.getPass_amount().equalsIgnoreCase("")){
			addFieldError("pass_amount","Please Enter Pass Amount");
		}
		if(passRate.getService_tax_amount()==null || passRate.getService_tax_amount().equalsIgnoreCase("")){
			addFieldError("service_tax_amount","Please Enter Service Tax Amount");
		}
		if(passRate.getProcessing_amount()==null || passRate.getProcessing_amount().equalsIgnoreCase("")){
			addFieldError("processing_amount","Please Enter Processing Amount");
		}
		if(passRate.getPass_start_time()==null || passRate.getPass_start_time().equalsIgnoreCase("")){
			addFieldError("pass_start_time","Please Enter Pass Start Time");
		}
		if(passRate.getPass_end_time()==null || passRate.getPass_end_time().equalsIgnoreCase("")){
			addFieldError("pass_end_time","Please Enter Pass End Time");
		}
		if(passRate.getService_type_id()==0){
			addFieldError("service_type_id","Please Enter Service Type");
		}
		if(passRate.getPass_start_date().equalsIgnoreCase("") || passRate.getPass_start_date()==null){
			addFieldError("pass_start_date","Please Enter Pass Start Date");
		}
		if(passRate.getVersion_number().equalsIgnoreCase("") || passRate.getVersion_number()==null){
			addFieldError("version_number","Please Enter Version Number");
		}
		if(passRate.getVersion_number().equalsIgnoreCase("") || passRate.getVersion_number()==null){
			addFieldError("version_number","Please Enter Version Number");
		}
		else{
			if(cv.isNumberic(passRate.getVersion_number())){
				addFieldError("version_number","Version Number should be number");
			}
		}
	}
	
	@SkipValidation
	public String execute()  {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			PassRateDao passRatedao = new PassRateDao();

			String[] dbcol={"","pass_rate_master_id","passType.pass_type_name","amount","effective_start_date","effective_end_date",
					"versionNumber","status","notes","","","","","","","","","","","","","",""};
			
			
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
				if (sdir.equals("asc"))
					dir = "desc";
			}


			int total = -1;
			total = passRatedao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = passRatedao.getData(total, request,dbcol[Integer.parseInt(sCol)],dir,viewdeletedrecord);

			out.print(result);
			
		} catch (Exception ex) {
			System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}

	@Override
	public void prepare() throws Exception {
		try{
		PassRateDao passRatedao = new PassRateDao();
		setPassPassengerTypeMap(passRatedao.getPassengerType());
		setPassTypeMap(passRatedao.getPassType());
		setPassissuerTypeMap(passRatedao.getPaIssuerType());
		setPasspurchaseTypeMap(passRatedao.getPurchaseType());
		setPassdistanceTypeMap(passRatedao.getPassDistanceType());
		setPassdurationTypeMap(passRatedao.getPassDurationType());
		setServiceTypeMap(passRatedao.getServiceType());
		setCastcategory(passRatedao.getPassCategory());
		
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public String getsubTypeDetails() {
		System.out.println("hiiiiiii");
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			int sctype=Integer.parseInt(request.getParameter("passtype")) ;
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
		String sql="SELECT pass_subtype_id , sub_type_name FROM pass_sub_type WHERE pass_type_id= '"+passtype+"' " +
				"AND status = 'y'";
         Query query = session.createSQLQuery(sql)
				.addScalar("pass_subtype_id", Hibernate.STRING)
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
