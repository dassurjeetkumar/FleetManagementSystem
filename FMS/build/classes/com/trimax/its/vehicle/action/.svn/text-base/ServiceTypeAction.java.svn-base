package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.ServiceTypeDAO;
import com.trimax.its.vehicle.model.ServiceType;

public class ServiceTypeAction extends ActionSupport {

	String returnResult = "";
	/*
	 * property for show Service Type List
	 */
	private List<ServiceType> serviceTypeList;
	
	/*
	 * property for particular Service Type
	 */
	private ServiceType serviceTypeDetails;
	
	/*
	 * 
	 * properties for updating Service Type Details
	 */
	private int isUpdateServiceType;
	public int updatedServiceTypeId;
	private String updatedServiceTypeName;
	private String updatedNotes;
	private String updatedStatus;
		
	
	/*
	 * 
	 * properties for creating new Service Type
	 */
	private int isNewServiceType;
	private String newServiceType ;
	private String newServiceNotes;
	private String newServiceStatus;
	
	static ServiceTypeDAO daoObject = new ServiceTypeDAO();
	static Number cnt = daoObject.getTotalServiceTypeRecords();
	public List<ServiceType> getServiceTypeList() {
		return serviceTypeList;
	}


	public void setServiceTypeList(List<ServiceType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	
	public ServiceType getServiceTypeDetails() {
		return serviceTypeDetails;
	}


	public void setServiceTypeDetails(ServiceType serviceTypeDetails) {
		this.serviceTypeDetails = serviceTypeDetails;
	}


	
	public int getIsUpdateServiceType() {
		return isUpdateServiceType;
	}


	public void setIsUpdateServiceType(int isUpdateServiceType) {
		this.isUpdateServiceType = isUpdateServiceType;
	}


	public int getUpdatedServiceTypeId() {
		return updatedServiceTypeId;
	}


	public void setUpdatedServiceTypeId(int updatedServiceTypeId) {
		this.updatedServiceTypeId = updatedServiceTypeId;
	}


	
	public String getUpdatedServiceTypeName() {
		return updatedServiceTypeName;
	}


	public void setUpdatedServiceTypeName(String updatedServiceTypeName) {
		this.updatedServiceTypeName = updatedServiceTypeName;
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


	
	
	public int getIsNewServiceType() {
		return isNewServiceType;
	}


	public void setIsNewServiceType(int isNewServiceType) {
		this.isNewServiceType = isNewServiceType;
	}


	public String getNewServiceType() {
		return newServiceType;
	}


	public void setNewServiceType(String newServiceType) {
		this.newServiceType = newServiceType;
	}


	public String getNewServiceNotes() {
		return newServiceNotes;
	}


	public void setNewServiceNotes(String newServiceNotes) {
		this.newServiceNotes = newServiceNotes;
	}


	public String getNewServiceStatus() {
		return newServiceStatus;
	}


	public void setNewServiceStatus(String newServiceStatus) {
		this.newServiceStatus = newServiceStatus;
	}

	public String execute()
	{
		
//		this.setServiceTypeList(daoObject.getServiceTypeList());
		return "success";
	}
	
	
	
	public String editServiceType()
	{
		
		String serviceTypeId = (ServletActionContext.getRequest().getParameter("editServiceTypeId"));
		Object vserviceTypeID = ServletActionContext.getRequest().getSession().getAttribute("servieTypeId");
		String serviceTypeid = (serviceTypeId != null) ? serviceTypeId : vserviceTypeID.toString();
		setServiceTypeDetails(daoObject.getServiceTypeDetails(Integer.parseInt(serviceTypeid)));
		return "success";
	}
	
	public String saveEditedServiceTypeDetails(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(serviceTypeDetails.getService_type_id(),"service_type");
			System.out.println("status---"+status);
			if(status.equals("")||(!status.equals("") &&  serviceTypeDetails.getStatus().equals("ACTIVE"))){		
		if(daoObject.isServiceTypeNew(serviceTypeDetails,"update")){
		if(daoObject.saveEditedDetails(serviceTypeDetails))
		{
			addActionMessage("Service Type Id "+serviceTypeDetails.getService_type_id()+" updated successfully");
			returnResult = "success";

		}else{
			addActionError("Error in upadtion of service type");
			returnResult = "fail";
			
		}
		}
		else{
			addFieldError("updatedServiceTypeName", "Duplicate Service Type");
			returnResult = "fail";
			//ServletActionContext.getRequest().getSession().setAttribute("servieTypeId",updatedServiceTypeId);
			//editServiceType();
		}
		}else{
			
			
			if(serviceTypeDetails.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}
		}
		return returnResult;
		}else{
			return "input";
		}
	}
	
	public String createServiceType()
	{
		return "success";
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	public String saveNewServiceType()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		ServiceType serviceType = new ServiceType();
		serviceType.setService_type_name(serviceTypeDetails.getService_type_name());
		serviceType.setAbbreviation(serviceTypeDetails.getAbbreviation());
		serviceType.setNote(serviceTypeDetails.getNote());
		serviceType.setStatus(serviceTypeDetails.getStatus());
		serviceType.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
		serviceType.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
		serviceType.setUpdatedBy(0);
		if(daoObject.isServiceTypeNew(serviceType,"update")){
			if(daoObject.saveServiceType(serviceType))
			{
				addActionMessage("Service Type Id "+ServletActionContext.getRequest().getAttribute("createdServiceTypeId")+" created successfully");
				returnResult = "success";
			}
			else{
				addActionError("Error in  creation service type");
				returnResult = "fail";
			}
		}
		else{
			addFieldError("newServiceType", "Duplicate Service Type");
			returnResult = "fail";
			
		}
		return returnResult;
		}else{
			return "fail";
		}
	}
	
	public String deleteServiceType()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String returnResult1 = "";
		String status="";
		if(delete.equalsIgnoreCase("Y")){
		int serviceTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("serviceTypeId"));
		
		status=daoObject.deleteServiceType(serviceTypeId);
		
		if(status.split(":")[0].equals("success")){
		
			addActionMessage("Service Type Id "+serviceTypeId+" deleted successfully");
			returnResult1 = "success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Service Type Id "+serviceTypeId+" deleted successfully");
			returnResult1 = "success";
		}
		else
		{
			addActionError(status);
			returnResult1="fail";
		}
		
		
		return returnResult1;
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");

			return "success";
		}
	}
	
	public void validate()
	{
		if(isNewServiceType!=0)
		{
			if(serviceTypeDetails.getService_type_name()!=null&&!serviceTypeDetails.getService_type_name().trim().equals(""))
			{
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(serviceTypeDetails.getService_type_name()).matches())){
					addFieldError("newServiceType", "Invalid service type name");
				}
			}
			else {
				addFieldError("newServiceType", "Please enter service type name");
			}
			
		}
		if(isUpdateServiceType!=0)
		{
			if(serviceTypeDetails.getService_type_name()!=null&&!serviceTypeDetails.getService_type_name().trim().equals(""))
			{
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(serviceTypeDetails.getService_type_name()).matches())){
					addFieldError("updatedServiceTypeName", "Invalid service type name");
				}
			}
			else {
				addFieldError("updatedServiceTypeName", "Please enter service type name");
			}
			//ServletActionContext.getRequest().getSession().setAttribute("servieTypeId",updatedServiceTypeId);
			//editServiceType();
		}
	}
	
	public String getServiceTypeRecordsList() throws IOException{
		

		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		
		System.out.println("Count------>"+cnt);
		
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		String search_term = request.getParameter("sSearch");
		
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
//		total = (Integer) cnt;
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result = daoObject.getServiceTypeData(amount, request,Integer.parseInt(sCol),sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
	
	
	
}
