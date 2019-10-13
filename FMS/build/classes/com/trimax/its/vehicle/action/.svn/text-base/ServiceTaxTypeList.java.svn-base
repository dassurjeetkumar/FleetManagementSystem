package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.usermanagement.dao.EmployeeDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.ServiceTaxTypeDAO;
import com.trimax.its.vehicle.dao.ServiceTypeDAO;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.ServiceTaxType;
import com.trimax.its.vehicle.model.ServiceType;

public class ServiceTaxTypeList extends ActionSupport {

	String returnResult = "";
	/*
	 * property for show Service Type List
	 */
	private List<ServiceTaxType> serviceTypeList;
	
	/*
	 * property for particular Service Type
	 */
	private ServiceTaxType serviceTypeDetails;
	
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
	
	
	private Map<Integer, String> fareCatagoryList;
    public Map<Integer, String> getFareCatagoryList() {
        return fareCatagoryList;
    }

    public void setFareCatagoryList(Map<Integer, String> fareCatagoryList) {
        this.fareCatagoryList = fareCatagoryList;
    }
	
    private Map<Integer, String> taxtypList;
    private Map<Integer, String> tickettypList;
	public Map<Integer, String> getTickettypList() {
		return tickettypList;
	}

	public void setTickettypList(Map<Integer, String> tickettypList) {
		this.tickettypList = tickettypList;
	}

	public Map<Integer, String> getTaxtypList() {
		return taxtypList;
	}

	public void setTaxtypList(Map<Integer, String> taxtypList) {
		this.taxtypList = taxtypList;
	}

	static ServiceTaxTypeDAO daoObject = new ServiceTaxTypeDAO();
	static Number cnt = daoObject.getTotalServiceTaxTypeRecords();
	public List<ServiceTaxType> getServiceTypeList() {
		return serviceTypeList;
	}


	public void setServiceTypeList(List<ServiceTaxType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	
	public ServiceTaxType getServiceTypeDetails() {
		return serviceTypeDetails;
	}


	public void setServiceTypeDetails(ServiceTaxType serviceTypeDetails) {
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
		//fareCatagoryList = daoObject.getServiceTypeName1();
		return "success";
	}
	
	private String servname;
	private String taxname;
	
	public String getServname() {
		return servname;
	}

	public void setServname(String servname) {
		this.servname = servname;
	}

	public String getTaxname() {
		return taxname;
	}

	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}

	private Integer taxid;
	private Integer ticketid;
	public Integer getTicketid() {
		return ticketid;
	}

	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}

	public Integer getTaxid() {
		return taxid;
	}

	public void setTaxid(Integer taxid) {
		this.taxid = taxid;
	}
	private Integer servid;
	public Integer getServid() {
		return servid;
	}

	public void setServid(Integer servid) {
		this.servid = servid;
	}

	public String editServiceTaxType()
	{
		
	//	this.setServiceTypeList(daoObject.getServiceTypeList());
		List<ServiceTaxType> serviceTypeList = null;
		Session session = null;
		
//		try
//		{
//			ServiceTaxType serviceType=new ServiceTaxType();
//			session =  HibernateUtil.getSession("hibenate.cfg.xml");
//			Criteria criteria = session.createCriteria(ServiceTaxType.class);
//			
//			criteria.add(Restrictions.eq("deletedStatus",0));
//			List<Object> resultSetArray = criteria.list();
//			serviceTypeList = new ArrayList<ServiceTaxType>();
//			
//			for(int i=0;i<resultSetArray.size();i++)
//			{
//				
//				Object result = resultSetArray.get(i);
//				 serviceType  = (ServiceTaxType) result;
//				serviceTypeList.add(serviceType);
//				
//			}
//			int  id1 =serviceType.getService_type_id();
//			String name1=serviceType.getService_type_name();
//			System.out.println("id1==="+id1);
//			System.out.println("name1==="+name1);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		taxid =serviceType.getTax_type_id();
//		servname=serviceType.getService_type_name();
//		servid=serviceType.getService_type_id();
//		taxname = serviceType.getTax_type_name();
		//System.out.println("servname==="+servname);
	//	System.out.println("taxname==="+taxname);
		tickettypList = daoObject.getTicketType();
		taxtypList = daoObject.getTaxTypeName();
		fareCatagoryList = daoObject.getServiceTypeName1();
		Session session1 = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		session1.beginTransaction();
         String servicetax_id = (ServletActionContext.getRequest().getParameter("editServiceTaxTypeId"));
         System.out.println("servicetax_id==="+servicetax_id);
		String sql8 = "select tax_type_id,service_type_id,ticket_type_id from service_tax where service_tax_id="+servicetax_id;
		Query queryedit = session1.createSQLQuery(sql8).addScalar("tax_type_id").addScalar("service_type_id").addScalar("ticket_type_id");
		queryedit.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = queryedit.list();	
		String id4="";
		String id5="";
		String id6 ="";
		 for (int i = 0; i < aliasToValueMapList.size(); i++) {
			 Map<String, Object> list = aliasToValueMapList.get(i);
			  id4=list.get("tax_type_id").toString();
			  id5=list.get("service_type_id").toString();
			  id6=list.get("ticket_type_id").toString();
			 System.out.println("id4======"+id4);
			 System.out.println("id5======"+id5);
		 }
		 taxid =Integer.parseInt(id4);
		 servid =Integer.parseInt(id5);
		 ticketid = Integer.parseInt(id6);
		 System.out.println("taxid======"+taxid);
		 System.out.println("servid======"+servid);
		this.setServiceTypeDetails(daoObject.getServiceTaxTypeDetailsEdit(Integer.parseInt(servicetax_id)));
		return "success";
	}
	
	public String saveEditedServiceTaxTypeDetails() throws ParseException{
		Common common = new Common();
		System.out.println("hiiiiiii");
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
//		if(edit.equalsIgnoreCase("Y")){
		Session session1 = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		session1.beginTransaction();
		HttpServletRequest req = ServletActionContext.getRequest();
		String serid =req.getParameter("serviceTypeDetails.service_type_name");
		String taxid =req.getParameter("serviceTypeDetails.tax_type_name");
		String tickettypeid =req.getParameter("serviceTypeDetails.ticket_type_name");
		String sqlury2 = "select service_type_name from service_type where status='Active' and deleted_status=0 and service_type_id= "+serid;
		
		try {
			count = common.getDBResultStr(session1, sqlury2, "service_type_name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        String sqlury7 = "select tax_type_name from tax_type where  tax_type_id= "+taxid;
		
		try {
			count1 = common.getDBResultStr(session1, sqlury7, "tax_type_name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sqlury8 = "select ticket_type_name from ticket_type where  ticket_type_id= "+tickettypeid;
		
		try {
			count2 = common.getDBResultStr(session1, sqlury8, "ticket_type_name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count=="+count);
		System.out.println("count=="+count1);
		System.out.println("count2=="+count2);
		
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(serviceTypeDetails.getService_tax_id(),"service_tax_id");
			System.out.println("status---"+status);
			
			if(status.equals("")||(!status.equals("") &&  serviceTypeDetails.getStatus().equals("ACTIVE"))){	
				
				
				
				ServiceTaxType serviceTaxType = new ServiceTaxType();
				
//				if(daoObject.getDuplicateRecordForUpdate(serviceTypeDetails.getService_tax_id())){
				   serviceTaxType.setService_tax_id(serviceTypeDetails.getService_tax_id());
					serviceTaxType.setService_type_id(Integer.parseInt(serviceTypeDetails.getService_type_name()));
					int sevid = Integer.parseInt(serviceTypeDetails.getService_type_name());
					int taxid1 =Integer.parseInt(serviceTypeDetails.getTax_type_name());
					int ticketid1 =Integer.parseInt(serviceTypeDetails.getTicket_type_name());
					serviceTaxType.setService_type_name(count);
					serviceTaxType.setTax_type_id(Integer.parseInt(serviceTypeDetails.getTax_type_name()));
					serviceTaxType.setTax_type_name(count1);
					serviceTaxType.setTicket_type_id(Integer.parseInt(serviceTypeDetails.getTicket_type_name()));
					serviceTaxType.setTicket_type_name(count2);
					serviceTaxType.setStatus(serviceTypeDetails.getStatus());
					serviceTaxType.setBase_value(serviceTypeDetails.getBase_value());
					serviceTaxType.setService_tax_percentage(serviceTypeDetails.getService_tax_percentage());
					serviceTaxType.setCreatedBy(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
				//brandTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
					serviceTaxType.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
				try {
					serviceTaxType.setEffective_start_date((serviceTypeDetails.getEffective_start_date()));
					serviceTaxType.setEffective_end_date((serviceTypeDetails.getEffective_end_date()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try{
					if(serviceTypeDetails.getEffective_end_date().toString().trim().length()>0){
						
						//validate str& end date
						//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						Date effstdate=sdf.parse(serviceTypeDetails.getEffective_start_date().toString());
						Date effenddate=sdf.parse(serviceTypeDetails.getEffective_end_date().toString());
						
						if(effstdate.compareTo(effenddate)>0){
			        		System.out.println("Date1 is after Date2");
			        		addActionError("Effective End Date should be greater than Effective Start Date");
			        		
			        		return INPUT;
			        		
			        	}else if(effstdate.compareTo(effenddate)==0){
			        		System.out.println("Date1 is equal to Date2");
			        		addActionError("Effective End Date should be greater than Effective Start Date");
			        		return INPUT;
			        		
			        	}
					}
					}catch(Exception e){e.printStackTrace();}
				
				
//				try{
//				if(!serviceTypeDetails.getEffective_end_date().equals("")){
//					Date date1=sdf.parse(serviceTypeDetails.getEffective_start_date());
//					Date date2=sdf.parse(serviceTypeDetails.getEffective_end_date());
//				if(common.compareDatesBrandEdit(serviceTypeDetails.getEffective_start_date(), serviceTypeDetails.getEffective_end_date())==1){
//		    		System.out.println("Date1 is after Date2");
//		    		addActionMessage("Effective Start Date should be before Effective End Date");
//		    		return "input";
//		    	}else if(date1.compareTo(date2)==0){
//		    		System.out.println("Date1 is equal to Date2");
//		    		addActionMessage("Effective Start Date should not be equal to Effective End Date ");
//		    		return "input";
//		    	}
//				
//				serviceTaxType.setEffective_start_date(common.getDateFromPicker(serviceTypeDetails.getEffective_start_date()));
//				serviceTaxType.setEffective_end_date(common.getDateFromPicker(serviceTypeDetails.getEffective_end_date()));
//				}else{
//					serviceTaxType.setEffective_start_date(common.getDateFromPicker(serviceTypeDetails.getEffective_start_date()));
//					serviceTaxType.setEffective_end_date(null);
//				}
//				}catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
				try{
				daoObject.saveEditedDetails(serviceTaxType,sevid,taxid1,ticketid1);
				
				}catch(Exception ex)
				{
					setUpdatedStatus("fail");
					return "input";
				}finally{
					setUpdatedStatus("success");
					addActionMessage("Service Tax Id "+ serviceTypeDetails.getService_tax_id() +" Updated Successfully");
				}
//				}
				
//				else if(!daoObject.getDuplicateRecord(serviceTypeDetails.getBrand_type_name())){
//					
//					brandTypeObject.setBrand_type_name(brandTypeDetails.getBrand_type_name());
//					System.out.println("brandTypeDetails.getNote()"+brandTypeDetails.getNote());
//					brandTypeObject.setNote(brandTypeDetails.getNote());
//					brandTypeObject.setStatus(brandTypeDetails.getStatus());
//					brandTypeObject.setServiceType(brandTypeDetails.getServiceType());
//					brandTypeObject.setCreatedBy(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
//					//brandTypeObject.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
//					brandTypeObject.setUpdatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					
//					Common common = new Common();
//					if(!brandTypeDetails.getEffective_end_date().equals("")){
//						Date date1=sdf.parse(brandTypeDetails.getEffective_start_date());
//						Date date2=sdf.parse(brandTypeDetails.getEffective_end_date());
//					if(common.compareDatesBrandEdit(brandTypeDetails.getEffective_start_date(), brandTypeDetails.getEffective_end_date())==1){
//			    		System.out.println("Date1 is after Date2");
//			    		addActionMessage("Effective Start Date should be before Effective End Date");
//			    		return "input";
//			    	}else if(date1.compareTo(date2)==0){
//			    		System.out.println("Date1 is equal to Date2");
//			    		addActionMessage("Effective Start Date should not be equal to Effective End Date ");
//			    		return "input";
//			    	}
//					
//					brandTypeObject.setEffective_start_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_start_date()));
//					brandTypeObject.setEffective_end_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_end_date()));
//					}else{
//						brandTypeObject.setEffective_start_date(common.getDateTimeFromPickerBrand(brandTypeDetails.getEffective_start_date()));
//						brandTypeObject.setEffective_end_date(null);
//					}
//					try{
//					daoObject.saveEditedDetails(brandTypeDetails);
//					
//					}catch(Exception ex)
//					{
//						setUpdatestatus("Fail");
//						return "input";
//					}finally{
//						setUpdatestatus("success");
//						addActionMessage("Brand Type Id "+ brandTypeDetails.getBrand_type_id() +" Updated Successfully");
//					}
//				}
//				else{
//					addActionMessage("Could Not insert Duplicate Brand  ");
//		    		return "input";
//				}
				}else
				{
					
					if(serviceTypeDetails.getStatus().equals("INACTIVE"))
					{
						setUpdatedStatus("success");
					addActionError(status);
					return "input";
					}	
					
				}
				return "success";
	}
	
	public String createServiceTaxType()
	{
		tickettypList = daoObject.getTicketType();
		taxtypList = daoObject.getTaxTypeName();
		fareCatagoryList = daoObject.getServiceTypeName1();
		return "success";
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	String count;
	String count1;
	String count2;
	public String saveNewServiceTaxType() throws ParseException
	{
		
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
//		if(create.equalsIgnoreCase("Y")){
		Common common = new Common();
		Session session = null;
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		session.beginTransaction();
		System.out.println("hiiiiiiiiiii");
		HttpServletRequest req = ServletActionContext.getRequest();
		String serid =req.getParameter("serviceTypeDetails.service_type_name");
		String taxid =req.getParameter("serviceTypeDetails.tax_type_name");
		String ticketid =req.getParameter("serviceTypeDetails.ticket_type_name");
		System.out.println("taxid"+taxid);
			ServiceTaxType serviceTaxType = new ServiceTaxType();
			String sqlury = "select service_type_name from service_type where status='Active' and deleted_status=0 and service_type_id= "+serid;
			
			try {
				count = common.getDBResultStr(session, sqlury, "service_type_name");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            String sqlury1 = "select tax_type_name from tax_type where  tax_type_id= "+taxid;
			
			try {
				count1 = common.getDBResultStr(session, sqlury1, "tax_type_name");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  String sqlury2 = "select ticket_type_name from ticket_type where  ticket_type_id= "+ticketid;
				
				try {
					count2 = common.getDBResultStr(session, sqlury2, "ticket_type_name");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("count=="+count);
			System.out.println("count=="+count1);
			System.out.println("count2=="+count2);
			serviceTaxType.setService_type_id(Integer.parseInt(serviceTypeDetails.getService_type_name()));
			serviceTaxType.setTax_type_id(Integer.parseInt(serviceTypeDetails.getTax_type_name()));
			serviceTaxType.setTicket_type_id(Integer.parseInt(serviceTypeDetails.getTicket_type_name()));
			serviceTaxType.setTax_type_name(count1);
			serviceTaxType.setService_type_name(count);
			serviceTaxType.setTicket_type_name(count2);
			//serviceTaxType.setService_type_name(serviceTypeDetails.getService_type_name());
			System.out.println("serviceTypeDetails.getBase_value()=="+serviceTypeDetails.getBase_value());
			System.out.println("serviceTypeDetails.getService_tax_percentage()=="+serviceTypeDetails.getService_tax_percentage());
			if(serviceTypeDetails.getBase_value().equalsIgnoreCase("")){
				serviceTaxType.setBase_value("0.0");
			}else{
			serviceTaxType.setBase_value(serviceTypeDetails.getBase_value());
			}
			if(serviceTypeDetails.getService_tax_percentage().equalsIgnoreCase("")){
				serviceTaxType.setService_tax_percentage("0.0");
			}else{
			serviceTaxType.setService_tax_percentage(serviceTypeDetails.getService_tax_percentage());
			}
			serviceTaxType.setEffective_start_date(serviceTypeDetails.getEffective_start_date());
			serviceTaxType.setEffective_end_date(serviceTypeDetails.getEffective_end_date());
			serviceTaxType.setStatus(serviceTypeDetails.getStatus());
			serviceTaxType.setCreatedBy(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());
			serviceTaxType.setCreatedDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			serviceTaxType.setUpdatedBy("0");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			//Common common = new Common();
			if(!serviceTypeDetails.getEffective_end_date().equals("")){
				Date date1=sdf.parse(serviceTypeDetails.getEffective_start_date());
				Date date2=sdf.parse(serviceTypeDetails.getEffective_end_date());
			if(common.compareDatesBrand(serviceTypeDetails.getEffective_start_date(), serviceTypeDetails.getEffective_end_date())==1){
	    		System.out.println("Date1 is after Date2");
	    		addActionMessage("Effective Start Date should be before Effective End Date");
	    		return "input";
	    	}else if(date1.compareTo(date2)==0){
	    		System.out.println("Date1 is equal to Date2");
	    		addActionMessage("Effective Start Date should not be equal to Effective End Date ");
	    		return "input";
	    		
	    	}
			serviceTaxType.setEffective_start_date(common.getDateTimeFromPickerBrand(serviceTypeDetails.getEffective_start_date()));
			serviceTaxType.setEffective_end_date(common.getDateTimeFromPickerBrand(serviceTypeDetails.getEffective_end_date()));
			}else{
				serviceTaxType.setEffective_start_date(common.getDateTimeFromPickerBrand(serviceTypeDetails.getEffective_start_date()));
				serviceTaxType.setEffective_end_date(null);
			}
			
			
			
//		if(daoObject.isServiceTypeNew(serviceTaxType,"update")){
			if(daoObject.saveserviceTaxType(serviceTaxType))
			{
				addActionMessage("Service Tax Id "+ServletActionContext.getRequest().getAttribute("createdServiceTypeId")+" created successfully");
				returnResult = "success";
			}
			else{
				addActionError("Error in  creation service tax");
				returnResult = "fail";
				
			}
//		}
//		else{
//			addFieldError("newServiceType", "Duplicate Service Type");
//			returnResult = "fail";
//						
//		}
		
		return returnResult;
//		}else{
//			return "fail";
//		}
	}
	
	public String deleteServiceTaxType()
	{
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ServiceTypeList.action");
//		String access=accessrights.getACCESS();
//		String create=accessrights.getCREATE();
//		String edit=accessrights.getEDIT();
//		String delete=accessrights.getDELETE();
		String returnResult1 = "";
		String status="";
//		if(delete.equalsIgnoreCase("Y")){
		int serviceTaxTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("serviceTaxTypeId"));
		
		status=daoObject.deleteServiceType(serviceTaxTypeId);
		
		if(status.split(":")[0].equals("success")){
		
			addActionMessage("Service Tax Id "+serviceTaxTypeId+" deleted successfully");
			returnResult1 = "success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Service Tax Id "+serviceTaxTypeId+" deleted successfully");
			returnResult1 = "success";
		}
		else
		{
			addActionError(status);
			returnResult1="fail";
		}
		
		
		return returnResult1;
		}
//		else{
//			addActionMessage("Access Denied - User Does Not Have Access Privileges");
//
//			return "success";
//		}
	
	
	
	public void validate()
	{
//		if(isNewServiceType!=0)
//		{
//			if(serviceTypeDetails.getService_type_name()!=null&&!serviceTypeDetails.getService_type_name().trim().equals(""))
//			{
//				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(serviceTypeDetails.getService_type_name()).matches())){
//					addFieldError("newServiceType", "Invalid service type name");
//				}
//			}
//			else {
//				addFieldError("newServiceType", "Please enter service type name");
//			}
//			
//		}
//		if(isUpdateServiceType!=0)
//		{
//			if(serviceTypeDetails.getService_type_name()!=null&&!serviceTypeDetails.getService_type_name().trim().equals(""))
//			{
//				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(serviceTypeDetails.getService_type_name()).matches())){
//					addFieldError("updatedServiceTypeName", "Invalid service type name");
//				}
//			}
//			else {
//				addFieldError("updatedServiceTypeName", "Please enter service type name");
//			}
//			//ServletActionContext.getRequest().getSession().setAttribute("servieTypeId",updatedServiceTypeId);
//			//editServiceType();
//		}
		try{
			String servicevalidate = serviceTypeDetails.getService_type_name();
			int servicevalidate1 =Integer.parseInt(servicevalidate);
			String taxvalidate = serviceTypeDetails.getTax_type_name();
			int taxvalidate1 =Integer.parseInt(taxvalidate);
			String ticketvalidate = serviceTypeDetails.getTicket_type_name();
			int ticketvalidate1 =Integer.parseInt(ticketvalidate);
			if(servicevalidate1==0)
			{
				addFieldError("ServiceTypeName","Fare Category Type is Required");
				//addActionError("Service Type Name Required");
			}
			if(taxvalidate1==0)
			{
				addFieldError("ServiceTaxName","service Tax Type is Required");
				//addActionError("Service Type Name Required");
			}
			if(ticketvalidate1==0)
			{
				addFieldError("TicketTypeName","Ticket Type is Required");
				//addActionError("Service Type Name Required");
			}
			if(serviceTypeDetails.getEffective_start_date().equals(""))
			{
				addFieldError("startDate","Effective Start Date is Required");
				//addActionError("Effective Start Date Required");
			}
//			if(serviceTypeDetails.getEffective_end_date().equals(""))
//			{
//				addFieldError("endDate","Effective End Date is Required");
//				//addActionError("Effective End Date Required");
//			}
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		if(hasFieldErrors()){
			tickettypList = daoObject.getTicketType();
			taxtypList = daoObject.getTaxTypeName();
			fareCatagoryList = daoObject.getServiceTypeName1();
		}
			
	}
	
	public String getServiceTaxTypeRecordsList() throws IOException{
		
          System.out.println("hiiiiii");
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
		System.out.println("result====");
		result = daoObject.getServiceTaxTypeData(amount, request,Integer.parseInt(sCol),sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
	
}
