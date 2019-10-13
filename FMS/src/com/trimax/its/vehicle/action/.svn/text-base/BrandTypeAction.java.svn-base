package com.trimax.its.vehicle.action;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.BrandTypeDAO;
import com.trimax.its.vehicle.model.BrandType;

public class BrandTypeAction extends ActionSupport implements Preparable {


	/*
	 * property for show Brand Type List
	 */
	private List<BrandType> brandTypeList;
	
	/*
	 * property for particular Brand Type
	 */
	private BrandType brandTypeDetails;
	
	/*
	 * 
	 * properties for updating Brand Type Details
	 */
	public int updatedBrandTypeId;
	private String updatedNotes;
	private String updatedStatus;
    private String insertstatus;
    private String updatestatus;
    private String deletestatus;
    private Map<Integer, String> fareCatagoryList;
	public String getDeletestatus() {
		return deletestatus;
	}


	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}
	/*
	 * 
	 * properties for creating new Brand Type
	 */
	private String newBrandType ;
	private String newBrandNotes;
	private String newBrandStatus;
	
	BrandTypeDAO daoObject = new BrandTypeDAO();
	public List<BrandType> getBrandTypeList() {
		return brandTypeList;
	}


	public void setBrandTypeList(List<BrandType> BrandTypeList) {
		this.brandTypeList = BrandTypeList;
	}

	
	public BrandType getBrandTypeDetails() {
		return brandTypeDetails;
	}


	public void setBrandTypeDetails(BrandType BrandTypeDetails) {
		this.brandTypeDetails = BrandTypeDetails;
	}


	
	public int getUpdatedBrandTypeId() {
		return updatedBrandTypeId;
	}


	public void setUpdatedBrandTypeId(int updatedBrandTypeId) {
		this.updatedBrandTypeId = updatedBrandTypeId;
	}


	public String getUpdatedNotes() {
		return updatedNotes;
	}


	public void setUpdatedNotes(String updatedNotes) {
		this.updatedNotes = updatedNotes;
	}


	public String getUpdatedStatus() {
		return updatedStatus;
	}


	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}


	
	public String getNewBrandType() {
		return newBrandType;
	}


	public void setNewBrandType(String newBrandType) {
		this.newBrandType = newBrandType;
	}


	public String getNewBrandNotes() {
		return newBrandNotes;
	}


	public void setNewBrandNotes(String newBrandNotes) {
		this.newBrandNotes = newBrandNotes;
	}


	public String getNewBrandStatus() {
		return newBrandStatus;
	}


	public void setNewBrandStatus(String newBrandStatus) {
		this.newBrandStatus = newBrandStatus;
	}


	public BrandTypeDAO getDaoObject() {
		return daoObject;
	}


	public void setDaoObject(BrandTypeDAO daoObject) {
		this.daoObject = daoObject;
	}

	public String getInsertstatus() {
		return insertstatus;
	}


	public void setInsertstatus(String insertstatus) {
		this.insertstatus = insertstatus;
	}


	public String getUpdatestatus() {
		return updatestatus;
	}


	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}


	@SkipValidation
	public String execute()
	{
		
		//this.setBrandTypeList(daoObject.getBrandTypeList());
		return null;
	}
	
	
	
	public Map<Integer, String> getFareCatagoryList() {
		return fareCatagoryList;
	}


	public void setFareCatagoryList(Map<Integer, String> fareCatagoryList) {
		this.fareCatagoryList = fareCatagoryList;
	}


	@SkipValidation
	public String editBrandType()
	{
		
		String brandTypeId = (ServletActionContext.getRequest().getParameter("brandTypeId"));
		
		this.setBrandTypeDetails(daoObject.getBrandTypeDetails(Integer.parseInt(brandTypeId)));
		return "success";
	}
	
	public String saveEditedBrandTypeDetails() throws ParseException{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BrandTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(brandTypeDetails.getBrand_type_id(),"brand_type");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  brandTypeDetails.getStatus().equals("ACTIVE"))){	
		
		BrandType brandTypeObject = new BrandType();
		if(daoObject.getDuplicateRecordForUpdate(brandTypeDetails.getBrand_type_name(),brandTypeDetails.getBrand_type_id())){
			brandTypeObject.setBrand_type_name(brandTypeDetails.getBrand_type_name());
		brandTypeObject.setNote(brandTypeDetails.getNote());
		brandTypeObject.setStatus(brandTypeDetails.getStatus());
		brandTypeObject.setServiceType(brandTypeDetails.getServiceType());
		brandTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
		//brandTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
		brandTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		Common common = new Common();
		if(!brandTypeDetails.getEffective_end_date().equals("")){
			Date date1=sdf.parse(brandTypeDetails.getEffective_start_date());
			Date date2=sdf.parse(brandTypeDetails.getEffective_end_date());
		if(common.compareDatesBrandEdit(brandTypeDetails.getEffective_start_date(), brandTypeDetails.getEffective_end_date())==1){
    		System.out.println("Date1 is after Date2");
    		addActionMessage("Effective Start Date should be before Effective End Date");
    		return "input";
    	}else if(date1.compareTo(date2)==0){
    		System.out.println("Date1 is equal to Date2");
    		addActionMessage("Effective Start Date should not be equal to Effective End Date ");
    		return "input";
    	}
		
		brandTypeObject.setEffective_start_date(common.getDateFromPicker(brandTypeDetails.getEffective_start_date()));
		brandTypeObject.setEffective_end_date(common.getDateFromPicker(brandTypeDetails.getEffective_end_date()));
		}else{
			brandTypeObject.setEffective_start_date(common.getDateFromPicker(brandTypeDetails.getEffective_start_date()));
			brandTypeObject.setEffective_end_date(null);
		}
		try{
		daoObject.saveEditedDetails(brandTypeDetails);
		
		}catch(Exception ex)
		{
			setUpdatestatus("Fail");
			return "input";
		}finally{
			setUpdatestatus("success");
			addActionMessage("Brand Type Id "+ brandTypeDetails.getBrand_type_id() +" Updated Successfully");
		}
		}else if(!daoObject.getDuplicateRecord(brandTypeDetails.getBrand_type_name())){
			
			brandTypeObject.setBrand_type_name(brandTypeDetails.getBrand_type_name());
			System.out.println("brandTypeDetails.getNote()"+brandTypeDetails.getNote());
			brandTypeObject.setNote(brandTypeDetails.getNote());
			brandTypeObject.setStatus(brandTypeDetails.getStatus());
			brandTypeObject.setServiceType(brandTypeDetails.getServiceType());
			brandTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			//brandTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			brandTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Common common = new Common();
			if(!brandTypeDetails.getEffective_end_date().equals("")){
				Date date1=sdf.parse(brandTypeDetails.getEffective_start_date());
				Date date2=sdf.parse(brandTypeDetails.getEffective_end_date());
			if(common.compareDatesBrandEdit(brandTypeDetails.getEffective_start_date(), brandTypeDetails.getEffective_end_date())==1){
	    		System.out.println("Date1 is after Date2");
	    		addActionMessage("Effective Start Date should be before Effective End Date");
	    		return "input";
	    	}else if(date1.compareTo(date2)==0){
	    		System.out.println("Date1 is equal to Date2");
	    		addActionMessage("Effective Start Date should not be equal to Effective End Date ");
	    		return "input";
	    	}
			
			brandTypeObject.setEffective_start_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_start_date()));
			brandTypeObject.setEffective_end_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_end_date()));
			}else{
				brandTypeObject.setEffective_start_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_start_date()));
				brandTypeObject.setEffective_end_date(null);
			}
			try{
			daoObject.saveEditedDetails(brandTypeDetails);
			
			}catch(Exception ex)
			{
				setUpdatestatus("Fail");
				return "input";
			}finally{
				setUpdatestatus("success");
				addActionMessage("Brand Type Id "+ brandTypeDetails.getBrand_type_id() +" Updated Successfully");
			}
		}
		else{
			addActionMessage("Could Not insert Duplicate Brand  ");
    		return "input";
		}
		}else
		{
			
			if(brandTypeDetails.getStatus().equals("INACTIVE"))
			{
				setUpdatestatus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		return "success";
		}else{
			return "input";
		}
	}
	@SkipValidation
	public String createBrandType()
	{
		return "success";
	}
	
	public String saveNewBrandType() throws ParseException
	{   AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	HttpServletRequest request = ServletActionContext.getRequest();
	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BrandTypeList.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
	if(create.equalsIgnoreCase("Y")){
		BrandType brandTypeObject = new BrandType();
		brandTypeObject.setBrand_type_name(brandTypeDetails.getBrand_type_name());
		if(!daoObject.getDuplicateRecord(brandTypeDetails.getBrand_type_name())){
		brandTypeObject.setNote(brandTypeDetails.getNote());
		brandTypeObject.setStatus(brandTypeDetails.getStatus());
		brandTypeObject.setServiceType(brandTypeDetails.getServiceType());
		brandTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
		brandTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		Common common = new Common();
		if(!brandTypeDetails.getEffective_end_date().equals("")){
			Date date1=sdf.parse(brandTypeDetails.getEffective_start_date());
			Date date2=sdf.parse(brandTypeDetails.getEffective_end_date());
		if(common.compareDatesBrand(brandTypeDetails.getEffective_start_date(), brandTypeDetails.getEffective_end_date())==1){
    		System.out.println("Date1 is after Date2");
    		addActionMessage("Effective Start Date should be before Effective End Date");
    		return "input";
    	}else if(date1.compareTo(date2)==0){
    		System.out.println("Date1 is equal to Date2");
    		addActionMessage("Effective Start Date should not be equal to Effective End Date ");
    		return "input";
    	}
		brandTypeObject.setEffective_start_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_start_date()));
		brandTypeObject.setEffective_end_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_end_date()));
		}else{
			brandTypeObject.setEffective_start_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_start_date()));
			brandTypeObject.setEffective_end_date(null);
		}
		
		try{
		int result=daoObject.saveBrandType(brandTypeObject);
		if(result>0){
		addActionMessage("Brand Type Id "+ result + " Inserted SuccessFully");
		}else{
			setInsertstatus("Fail");
			addActionMessage("Database Error!! Please Try after Sometime");
			return "input";
		}
		}
		catch(Exception ex)
		{
			setInsertstatus("Fail");
			addActionMessage("Database Error!! Please Try after Sometime");
			return "input";
		}finally{
			setInsertstatus("success");
			
		}
		}else{
			setInsertstatus("duplicate");
			addActionMessage("BrandType Name aready Exists!!");
    		return "input";
		}
		return "success";
	}else{
		return "input";
	}
	}
	@SkipValidation
	public String deleteBrandType()
	{    AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	HttpServletRequest request = ServletActionContext.getRequest();
	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BrandTypeList.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
	if(delete.equalsIgnoreCase("Y")){
		String status="";
		String status1="";
		int BrandTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("delbrandTypeId"));
		
		status=daoObject.deleteBrandType(BrandTypeId);
		if(status.split(":")[0].equals("success")){
			setDeletestatus("Record Deleted SuccessFully!");

			addActionMessage("Brand Type Id "+ BrandTypeId +" Deleted Successfully");
			status1 = "success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Brand Type Id "+ BrandTypeId +" Deleted Successfully");
			setDeletestatus("Record Deleted SuccessFully!");

			status1 = "success";
		}
		else
		{
			addActionError(status);
			status1="success";
			
		}
		//addActionMessage("Brand Type Id "+ BrandTypeId +" Deleted Successfully");
		return status1;
	}else{
		addActionMessage("Access Denied - User Does Not Have Access Privileges");
	return "success" ;
	
	}
	}
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			fareCatagoryList=daoObject.getServiceTypeName();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			// int cnt = devicedao.getTotalRecords();
			// System.out.println("Count------>" + cnt);
			String[] cols = { "", "brand_type_id", "brand_type_name","","","","", "status",};
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

			String colName = cols[col];
			int total = -1;
			total = daoObject.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = daoObject.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

		
		
	}
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation commvalidate = new CommonValidation();
	if(brandTypeDetails.getBrand_type_name().equals(""))
	{
		addFieldError("brandnamename","Brand Type Name Required");
		//addActionError("Brand Type Name Required");
	}
	if (!commvalidate.isSpecialChar(brandTypeDetails.getBrand_type_name().trim())) {
		
		addFieldError("brandnamename", "Special Character For Brand Type Name is Not Allowed");
		//addActionError("Special Character For Device Serial Number is Not Allowed");
	}
	if(brandTypeDetails.getServiceType().getService_type_id()==0)
	{
		addFieldError("serviceType","Fare Category Type is Required");
		//addActionError("Service Type Name Required");
	}
	if(brandTypeDetails.getEffective_start_date().equals(""))
	{
		addFieldError("startDate","Effective Start Date is Required");
		//addActionError("Effective Start Date Required");
	}
	if(brandTypeDetails.getEffective_end_date().equals(""))
	{
		addFieldError("endDate","Effective End Date is Required");
		//addActionError("Effective End Date Required");
	}
	/*if(brandTypeDetails.getStatus().equals(""))
	{
		addActionError("Status is Required");
	}*/
	/*if(brandTypeDetails.getNote()==null ||brandTypeDetails.getNote().equals(""))
	{
		addActionError("Note Cannot be Empty");
	}*/
	}
	@SkipValidation
	public String showBrandType()
	{
		return "success";
	}
}
