package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.trimax.its.model.Vendor;
import com.trimax.its.transport.dao.ScheduleDAO;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.BrandTypeDAO;
import com.trimax.its.vehicle.dao.ETMAvailabilityDao;
import com.trimax.its.vehicle.dao.ScheduleMapDAO;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.ServiceLimit;

public class BrandTypeServiceLimitAction extends ActionSupport {

////	ScheduleMapDAO daoObject = new ScheduleMapDAO();
//
//	public int updatedScheduleMappingId;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	ServiceLimit serviceLimit= new ServiceLimit();
//	public ServiceLimit getServiceLimit() {
//		return serviceLimit;
//	}


	BrandTypeDAO daoObject=new BrandTypeDAO();

//	public void setServiceLimit(ServiceLimit serviceLimit) {
//		this.serviceLimit = serviceLimit;
//	}


	public String execute()
	{
		return "success";
	}

Session session = null;
	@SkipValidation
	public String getServiceLimitData() throws IOException{
	
	
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();	
	String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");

	BrandTypeDAO dao=new BrandTypeDAO();
	
	try{
		session = HibernateUtil.getSession("hibernate.cfg.xml");	
		
		String[] dbcol={"brand_service_id", "brand_type_id","brand_service","note"};
		String[] cols={"brand_service_id", "brand_type_id","brand_service","note"};
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
		String orgtypeid = (String) request.getSession().getAttribute(
				"orgtypeid");
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");
		String id = "!=0";
		if (orgtypeid.equals("1")) {
			id = "depot_id!=0";

		}
		if (orgtypeid.equals("3")) {

			id = "depot_id in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
					+ orgchartid + "')";
		}
		String colName = cols[col];
		int total = -1;
		total=dao.getTotalServiceLimitData(request, dbcol[Integer.parseInt(sCol)],sdir,id);
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		//COL_NAME = colName;
		DIR = dir;
		START = start;
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		
		
		result=dao.getServiceLimitDetails(total,request,dbcol[Integer.parseInt(sCol)],sdir,id);
		out.print(result);
			
		}catch(Exception e){
//			e.printStackTrace();
		}
		return null;

}
	
	private Map<Integer,String> brandTypeMap;
	
	public Map<Integer, String> getBrandTypeMap() {
		return brandTypeMap;
	}


	public void setBrandTypeMap(Map<Integer, String> brandTypeMap) {
		this.brandTypeMap = brandTypeMap;
	}

	private Map<Integer, String> brandlist;
	public Map<Integer, String> getBrandlist() {
		return brandlist;
	}


	public void setBrandlist(Map<Integer, String> brandlist) {
		this.brandlist = brandlist;
	}
	private List<ServiceLimit> serviceLimits;

	public List<ServiceLimit> getServiceLimits() {
		return serviceLimits;
	}


	public void setServiceLimits(List<ServiceLimit> serviceLimits) {
		this.serviceLimits = serviceLimits;
	}

	BrandTypeDAO bdao=new BrandTypeDAO();
	public String createBrandServiceLimit()
	{
		ScheduleDAO sched=new ScheduleDAO();
		brandlist = sched.getBrand();
		
		
		return "success";
	}
	
	public String EditBrandServiceValue()
	{
		System.out.println("hereeeeeeeeeee");
//		ScheduleDAO sched=new ScheduleDAO();
//		brandlist = sched.getBrand();
		try{
		String brandId = (ServletActionContext.getRequest().getParameter("editBrandId"));
		System.out.println("1111111111");
		String brandObj[]=brandId.split("#");
		String brandTypeId=brandObj[0];
		String brandName=brandObj[1];
		String serviceValue=brandObj[2];
		String brandServiceId=brandObj[3];
		
		System.out.println("brand id=="+brandTypeId+"====="+brandName+"==="+serviceValue+"=="+brandServiceId);	
		HttpServletRequest request=ServletActionContext.getRequest();
		request.getSession().setAttribute("brandServiceId", brandTypeId);
		request.getSession().setAttribute("brand_type_name", brandName);
		request.getSession().setAttribute("old_brand-service",serviceValue);
		request.getSession().setAttribute("brand-serviceId",brandServiceId);
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			System.out.println("finally");
		}
		
		return "success";
	}
	

	String returnString="";
	
	
	@SuppressWarnings("finally")
	public String saveEditBrandService(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
try{
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BrandTypeServiceLimit.action");
	       
	  String mappedSchedule="";
	       int id=Integer.parseInt(request.getParameter("brand_type_name"));
	       String service = request.getParameter("service_limit");
	       String remarks = request.getParameter("note");
	       String brandServiceId=request.getParameter("brand_serviceID");
	       String brandName=daoObject.getBrandName(id);
	       if(daoObject.saveEditBrandServiceValue(id, brandName, service, remarks)){
	    	   addActionMessage("Brand Service of "+brandName+" inserted successfully");
				returnString = "success";
	       }
	       else{
				addActionError("Enter Valid Brand Name");
				returnString = "fail";
			}

//	     }
}catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
}finally{
	return returnString;
}
		
		
		}
	
//	schedulemp.schedule_no
	Schedule schedulemp=new Schedule();
	
	public Schedule getSchedulemp() {
		return schedulemp;
	}


	public void setSchedulemp(Schedule schedulemp) {
		this.schedulemp = schedulemp;
	}


	public String getData()
	{
		System.out.println("in get data");
		HttpServletRequest req=ServletActionContext.getRequest();
		ScheduleDAO sched=new ScheduleDAO();
//	sched.getBrand();
		
		int schNo=Integer.parseInt(req.getParameter("sch"));
		
		String schName=sched.getScheduleName(schNo);
		
		schedulemp.setScheduleNumber(schName);
		brandlist = sched.getBrandService();
		
		
		return "success";
	}
	
	
	
	@SuppressWarnings("finally")
	public String saveEditScheduleServiceLimit(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
try{
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "BrandTypeServiceLimit.action");
	       ScheduleDAO schdao=new ScheduleDAO();
	       String schName=request.getParameter("schNo");
	       
	       int schid=schdao.getScheduleId(schName);
	       
	       int brandserviceId = Integer.parseInt(request.getParameter("service"));
	       int limit = Integer.parseInt(request.getParameter("service_limit"));
	       String slimit="";
	       if(limit==1){
	    	   slimit="City";
	       }else{
	    	   slimit="Suburban";
	       }
	       if(bdao.checkScheduleServiceLimit(schid)){
	       if(daoObject.saveEditServiceLimit(schid, brandserviceId, slimit)){
	    	   addActionMessage("Service Limit of Schedule "+schName+" inserted successfully");
				returnString = "success";
	       }
	       else{
				addActionError("Enter Valid Schedule No.");
				returnString = "fail";
			}

	     }else{
	    	 if(daoObject.UpdateServiceLimit(schid,slimit).equals("true")){
		    	   addActionMessage("Service Limit of Schedule "+schName+" updated successfully");
					returnString = "success";
		       }
		       else{
					addActionError("Enter Valid Schedule No.");
					returnString = "fail";
				}
	     }
}catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
}finally{
	return returnString;
}
		
		}
	
	
	public String editBrandServiceDataValue(){
		System.out.println("in edit brand action");
//		AccessRightsDao  accessrightdao=new AccessRightsDao();
//		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		try{
//		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ScheduleMappingList.action");
	       int id=Integer.parseInt(request.getParameter("brandServiceId"));
	       String brandName=request.getParameter("brand_type_name");
	       String newBrandService=request.getParameter("service");
	       String new_note=request.getParameter("note");
	       String old_service=request.getParameter("oldBrandService");
	       String brandServiceId=request.getParameter("brand_serviceID");
	       System.out.println("here in edit brand=="+id+"==="+brandName+"=="+new_note+"=="+newBrandService+"==="+old_service+"--"+brandServiceId);
	
			if(daoObject.EditBrandServiceValue(id, brandName,newBrandService,new_note,old_service,brandServiceId)){
				addActionMessage("Brand Type Name '"+brandName+"' edited successfully");
				returnString = "success";
			}
     
			else{
				addActionError("Try Again Later....");
				System.out.println("else  ");
				returnString = "fail";
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			
		}
		return returnString;
		
		}
	
	
	public String deleteBrandType() {
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid = (Integer) request.getSession()
				.getAttribute("userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,	"BrandTypeServiceLimit.action");
//		if (delete.equalsIgnoreCase("Y")) {
			boolean status = false;
			String brandId =ServletActionContext.getRequest().getParameter("deletebrandId");
			System.out.println("details=="+brandId);
			
			String brandArr[]=brandId.split("#");   //brand Type Id,name ,service and brandServiceId
			
			int typeId=Integer.parseInt(brandArr[0]);
			String name=brandArr[1];
			String service=brandArr[2];
			String brandServiceId=brandArr[3];
			System.out.println("===="+brandServiceId);
try{
		if(daoObject.deleteBrandService(brandServiceId)){
//			System.out.println("status is"+status);
				addActionMessage("Brand Type '"+name+"' with Brand Service '"+service+"' deleted successfully");
				returnString = "success";
//				addActionMessage("Schedule Mapping " + scheduleId
//						+ " deleted successfully");
//				returnString = "success";
			}

		 else {
			 addActionError("error in deletion");
				returnString = "fail";
			}
}catch (Exception e) {
	// TODO: handle exception
}
			return returnString;

		}
	
	

	
	
}




	

