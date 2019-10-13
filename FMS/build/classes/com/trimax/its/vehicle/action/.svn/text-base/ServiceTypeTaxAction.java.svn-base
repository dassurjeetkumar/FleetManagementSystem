
package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONObject;


import com.opensymphony.xwork2.ActionSupport;

import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.ServiceTypeDAO;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.ServiceTypeTaxModel;
import com.trimax.its.vehicle.model.VehicleStatusModel;


public class ServiceTypeTaxAction extends ActionSupport {

	String returnResult = "";
	/*
	 * property for show Service Type List
	 */
	
	private String service_type_name;
	private List<ServiceTypeTaxModel> serviceTaxList = new ArrayList<ServiceTypeTaxModel>();
	public List<ServiceTypeTaxModel> getServiceTaxList() {
		return serviceTaxList;
	}


	public void setServiceTaxList(List<ServiceTypeTaxModel> serviceTaxList) {
		this.serviceTaxList = serviceTaxList;
	}
	/*
	 * property for particular Service Type
	 */
     private ServiceTypeTaxModel serviceTypeDetails;
	
	public ServiceTypeTaxModel getServiceTypeDetails() {
		return serviceTypeDetails;
	}


	public void setServiceTypeDetails(ServiceTypeTaxModel serviceTypeDetails) {
		this.serviceTypeDetails = serviceTypeDetails;
	}
	/*
	 * 
	 * properties for updating Service Type Details
	 */
     private int isUpdateServiceType;
 	public int updatedServiceTypeId;
 	private String updatedServiceTypeName;
// 	private String updatedNotes;
 	private String updatedBaseValue;
 	public String getUpdatedBaseValue() {
		return updatedBaseValue;
	}


	public void setUpdatedBaseValue(String updatedBaseValue) {
		this.updatedBaseValue = updatedBaseValue;
	}
	private String updatedStatus;

	/*
	 * 
	 * properties for creating new Service Type
	 */
 	private int isNewServiceTax;
	public int getIsNewServiceType() {
		return isNewServiceTax;
	}


	public void setIsNewServiceType(int isNewServiceTax) {
		this.isNewServiceTax = isNewServiceTax;
	}


	public String getNewServiceType() {
		return newServiceType;
	}


	public void setNewServiceType(String newServiceType) {
		this.newServiceType = newServiceType;
	}


	

	public String getNewServiceStatus() {
		return newServiceStatus;
	}


	public void setNewServiceStatus(String newServiceStatus) {
		this.newServiceStatus = newServiceStatus;
	}
	private String newServiceType ;
	private String newBaseValue;
	public String getNewBaseValue() {
		return newBaseValue;
	}


	public void setNewBaseValue(String newBaseValue) {
		this.newBaseValue = newBaseValue;
	}
	private String newServiceStatus;
	
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


	
	public String getUpdatedStatus() {
		return updatedStatus;
	}


	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	static ServiceTypeDAO daoObject = new ServiceTypeDAO();
	static Number cnt = daoObject.getTotalServiceTaxRecord();

	public void setService_type_name(String service_type_name) {
		this.service_type_name = service_type_name;
	}
	
	
	
	private double baseValue;

	public double getBaseValue() {
		return baseValue;
	}
	
	private String serviceType;

	

	public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
	}

	

	public String getService_type_name() {
		return service_type_name;
	}

	
	public String execute()
	{
		
//		this.setServiceTypeList(daoObject.getServiceTypeList());
		return "success";
	}
	
	public String editServiceTax()
	{
		
		String serviceTaxId = (ServletActionContext.getRequest().getParameter("editServiceTypeId"));
		Object vserviceTypeID = ServletActionContext.getRequest().getSession().getAttribute("serviceTaxId");
		String serviceTaxid = (serviceTaxId != null) ? serviceTaxId : vserviceTypeID.toString();
//		setServiceTypeDetails(daoObject.getServiceTypeDetails(Integer.parseInt(serviceTypeid)));
		setServiceTypeDetails(daoObject.getServiceTaxDetails(Integer.parseInt(serviceTaxid)));
		return "success";
	}
		

	public String saveEditedServiceTaxDetails(){
		System.out.println("save edit service tax");
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
			String status=dc.getStatus1(serviceTypeDetails.getService_type_id(),"Service_Tax");
			System.out.println("status---"+status);
			if(status.equals("")||(!status.equals("") &&  serviceTypeDetails.getStatus().equals("ACTIVE"))){		
		if(daoObject.isServiceTaxNew(serviceTypeDetails,"update")){
		if(daoObject.saveEditedTaxDetails(serviceTypeDetails))
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
	
	public String createServiceTax()
	{
		return "success";
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	

	public String saveNewServiceTax()
	{
		System.out.println("in save new service tax");
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
		ServiceTypeTaxModel serviceTax = new ServiceTypeTaxModel();
		serviceTax.setService_type_name(serviceTypeDetails.getService_type_name());
		serviceTax.setBaseValue(serviceTypeDetails.getBaseValue());
//		serviceTax.setNote(serviceTypeDetails.getNote());
		serviceTax.setStatus(serviceTypeDetails.getStatus());
//		serviceTax.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
		serviceTax.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
//		serviceTax.setUpdatedBy(0);
		if(daoObject.isServiceTaxNew(serviceTax,"update")){
			if(daoObject.saveServiceTax(serviceTax))
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
	
	public String deleteServiceTax()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeTaxList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String returnResult1 = "";
		String status="";
		if(delete.equalsIgnoreCase("Y")){
		int serviceTaxId =Integer.parseInt( ServletActionContext.getRequest().getParameter("serviceTaxId"));
		
		status=daoObject.deleteServiceTax(serviceTaxId);
		
		if(status.split(":")[0].equals("success")){
		
			addActionMessage("Service Tax Id "+serviceTaxId+" deleted successfully");
			returnResult1 = "success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Service Tax Id "+serviceTaxId+" deleted successfully");
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
		if(isNewServiceTax!=0)
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
	
	public String getServiceTaxRecordsList() throws IOException{
		System.out.println("in service tax record list");
		try{
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
         String viewdeletedrecord=(String)req.getSession().getAttribute("viewdeletedrecord");
         System.out.println("Count------>"+cnt);
         JSONObject result = new JSONObject();
         int amount = 10;
 		int start = 0;
 		int col = 0;
 		String dir = "asc";
 		String sStart = req.getParameter("iDisplayStart");
		String sAmount = req.getParameter("iDisplayLength");
		String sCol = req.getParameter("iSortCol_0");
		String sdir = req.getParameter("sSortDir_0");
		String search_term = req.getParameter("sSearch");
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
		AMOUNT = amount;
		SEARCH_TERM = req.getParameter("sSearch");
		DIR = dir;
		START = start;
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result = daoObject.getServiceTaxData(amount, req,Integer.parseInt(sCol),sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
//		ServiceTypeDAO dao=new ServiceTypeDAO();
//
//		List list = new ArrayList();
//		List<String> type = dao.getServiceTypeName();
//		System.out.println("service type is...."+type);
//		String regionTypeAjaxString = "";
//		regionTypeAjaxString="<select>";
//		regionTypeAjaxString += "<option value='0'>------select------</option>";
//		for (int i = 0; i < type.size(); i++) {
//			regionTypeAjaxString += "<option value="+"'"+type.get(i).toString()
//					+"'"+ ">"+ type.get(i).toString()+ "</option>";
////			System.out.println("region type ajax--"+regionTypeAjaxString);
//		}
//		regionTypeAjaxString +="</select>";
//			HttpServletResponse response = ServletActionContext.getResponse();
//    		PrintWriter out;
//		
//		try {
//			out = response.getWriter();
//			out.print(regionTypeAjaxString);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//}

     /*  public String submitServiceTax(){
    	   int count=0;
    	   boolean isSuccess = false;
	HttpServletRequest req = ServletActionContext.getRequest();
	String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	Session session = null;
	Common common = new Common();
	try {
	
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		System.out.println("i m in service type tax  action");
		
		int usrid = Integer.parseInt(ServletActionContext
				.getRequest().getSession().getAttribute("userid")
				.toString());
//		serviceType = this.getService_type_name();
				//req.getParameter("service_type_name");
		serviceType = req.getParameter("service_type_name");
	 System.out.println("service u entered"+serviceType);
		String hql1 = "select service_type_name as service_type_name from service_type where service_type_name="+"'"+serviceType+"'";
		 String vno = common.getDBResultStr(session, hql1, "service_type_name");
//		service_type_name = req.getParameter("service_type_name");
		System.out.println("service_type_name"+serviceType);
		baseValue = Double.parseDouble(req.getParameter("notes"));
		 
		 ServletActionContext.getRequest().getSession().setAttribute("notes", baseValue);
		System.out.println("baseValue : " + baseValue);
		
		session.beginTransaction();
        

       ServiceTypeTaxModel tax = new ServiceTypeTaxModel();
     
       try{
    	   tax.setService_type_name(vno);
//    	   tax.setService_type_name(service_type_name);
    	   tax.setBaseValue(baseValue);
       tax.setCreatedDate(currentDate);
       tax.setEnd_date(null);	
       ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", "1");
       addActionMessage("Record inserted Successfully");
       session.save(tax);
       }catch (Exception e) {
		
    	   e.printStackTrace();
	}
       
        System.out.println("data saved");
        
        
        //String hql2 = "select count(*) as count from Vehicle_Status where vehicle_no='"+vno+"' AND to_date IS NULL";
        String hql2 = "select count(*) as count from Service_Tax where service_type_name='"+serviceType+"' ";
         count = common.getDBResultInt(session, hql2, "count");
        
        
        if(count==0){
        	session.save(tax);
        	 System.out.println("data saved in table");
        	
        }else{
        	
//        	String veh="select vehicle_no vehicleno from Vehicle_Status where vehicle_no="+vehiclelist;
//        	vehicle = common.getDBResultStr(session, veh, "vehicleno");
    		

    				

//        	 String hql3 = "update Vehicle_Status set status_cause ='"+status +"',remarks='"+remarksVehicle+"',to_date='"+currentDate+"' where vehicle_no='"+vno+"'";
//        	 System.out.println(hql3);
//        	 Query qry4= session.createSQLQuery(hql3);
//             qry4.executeUpdate();

      
        	 System.out.println("updated again");
        	
        	 session.getTransaction().commit();
        System.out.println(count);
     
    	isSuccess = true;
       
        }	
		} catch (Exception ex) {
			ex.printStackTrace();
			isSuccess = false;
		} finally {
//			if(session != null && session.isOpen()){
				session.close();
//			}
			
		}
	return "success";
	}
	*/
	
	
}
