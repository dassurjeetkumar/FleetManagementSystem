package com.trimax.its.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.model.Vendor;
import com.trimax.its.usermanagement.dao.EmployeeTypeDAO;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.dao.VendorDAO;

public class VendorAction extends ActionSupport {

	private ArrayList<Vendor> vendorlist;
	
	
	Vendor ven=new Vendor() ;
	VendorDAO vendao=new VendorDAO();
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	private int iddetails;
	
	
	
	public String addVendorDetails(){
		String flagdetails="";
		boolean flag1=true;
		   HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowVendorList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		//System.out.println("name----"+ven.getVendor_name());
		 if(create.equalsIgnoreCase("Y")){
		//int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	    ven.setCreated_by(usrsessionid);
	  //  ven.setUpdated_date(new Date());
	    CommonValidation cm=new CommonValidation();
	    if(ven.getVendor_name().length()==0){
	    	flagdetails="error";
			flag1=false;
			//addActionError("Vendor name should not blank");
			addFieldError("vendor_name","Vendor name should not blank");
	    }
	    
	    if(!(isSpecialChar(ven.getVendor_name())))
		{
		 flagdetails="error";
		  flag1=false;
		  //addActionError("Special characters not allowed for Vendor name");
		  addFieldError("vendor_name","Special characters not allowed for Vendor name");
		}
	    
	    if(ven.getVendor_contact_person().length()==0){
	    	flagdetails="error";
			flag1=false;
			//addActionError("Vendor contact person name should not blank");
			//vendor_contact_person
			addFieldError("vendor_contact_person","Vendor contact person name should not blank");
	    }
	    if(!(isSpecialChar(ven.getVendor_contact_person())))
		{
		 flagdetails="error";
		  flag1=false;
		  //addActionError("Special characters not allowed for Vendor contact person");
		  addFieldError("vendor_contact_person","Special characters not allowed for Vendor contact person");
		}
	    
	   /* if(ven.getNote().length()==0){
	    	flagdetails="error";
			flag1=false;
			//addActionError("Vendor Note should not blank");
			 addFieldError("note","Vendor Note should not blank");
	    }*/
	    
	   /* if(!(cm.isSpecialChar(ven.getNote())))
		{
		 flagdetails="error";
		  flag1=false;
		  //addActionError("Special characters not allowed for Vendor note");
		  addFieldError("note","Special characters not allowed for Vendor note");
		}*/
	  if(flag1)
	    {
	    	
		   String result=vendao.saveDetails(ven);
			String str[]=result.split("\\|");
			String flag=str[0];
			String msg=str[1];
			if(flag.equalsIgnoreCase("true")){
				addActionMessage(msg);
				flagdetails="success";
			}else{
				addActionError(msg);
				 flagdetails="error";
			}
		
			}
		 
  //System.out.println("flagdetails--------"+flagdetails);
	   // this.setVendorlist(vendao.getVendorlist());
		return flagdetails;
		 }else{
			 return "error";
		 }
	}
	
	
	public String getDisplayVendor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		return "success";
	}
	
	
	
	public String getVendorlistDetails(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			////System.out.println("list-------"+vendao.getVendorlist());
			//System.out.println("test-----------------list------");
		//this.setVendorlist(vendao.getVendorlist());
			
			HttpServletResponse response = ServletActionContext.getResponse();
			String pf = (String)request.getSession().getAttribute("PF");
			System.out.println("pf---///////////////----"+pf);
			//BusStopReportDao busstopdao = new BusStopReportDao();
			//int cnt = busstopdao.getTotalRecords();
			////System.out.println("Count------>"+cnt);
			//EmployeeTypeDAO empdao=new EmployeeTypeDAO();
			
			VendorDAO vendao=new VendorDAO();
			String[] cols = {"","Vendor Id", "Vendor Name", "Vendor Contact Person", "Note","Status"};
			
			String[] dbcol={"","vendor_id","vendor_name","vendor_contact_person","note","status"};
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			////System.out.println("sCol---"+sCol);
			//String sCol="4";
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
				total = vendao.getTotalRecords();
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;
				
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				////System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
				//result = busstopdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
				//BusStopReportDao bustopsreport=new BusStopReportDao();
				
				result=vendao.getVendorTypeList(total,request,dbcol[Integer.parseInt(sCol)],sdir);
				//System.out.println("result----------"+result);
				out.print(result);
				return null;
			
			
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		return "success";
	}

	public boolean isSpecialChar(String fieldvalue){
		String regex = "^[a-zA-Z0-9 ]*$";
		 if(fieldvalue.matches(regex)){
			 return true;
			 }else{
				 return false;
			 }
	}
	public String modifyVendor(){
		String flagdetails="";
		 HttpServletRequest request = ServletActionContext.getRequest();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowVendorList.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(edit.equalsIgnoreCase("Y")){
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(ven.getId(),"vendor");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  ven.getStatus().equals("ACTIVE"))){	
		// HttpServletRequest request = ServletActionContext.getRequest();
		try{
		//System.out.println("iddetails----////////---"+ven.getId());
		 
		//	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		    ven.setUpdated_by(usrsessionid);
			 CommonValidation cm=new CommonValidation();
			boolean flag1=true;
		    
		  //  ven.setUpdated_date(new Date());
			 if(ven.getVendor_name().length()==0){
			    	flagdetails="error";
					flag1=false;
					//addActionError("Vendor name should not blank");
					addFieldError("vendor_name","Vendor name should not blank");
			    }
			    
			    if(!(isSpecialChar(ven.getVendor_name())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Special characters not allowed for Vendor name");
				  addFieldError("vendor_name","Special characters not allowed for Vendor name");
				}
			    
			    if(ven.getVendor_contact_person().length()==0){
			    	flagdetails="error";
					flag1=false;
					//addActionError("Vendor contact person name should not blank");
					//vendor_contact_person
					addFieldError("vendor_contact_person","Vendor contact person name should not blank");
			    }
			    if(!(isSpecialChar(ven.getVendor_contact_person())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Special characters not allowed for Vendor contact person");
				  addFieldError("vendor_contact_person","Special characters not allowed for Vendor contact person");
				}
			    
			  /*  if(ven.getNote().length()==0){
			    	flagdetails="error";
					flag1=false;
					//addActionError("Vendor Note should not blank");
					 addFieldError("note","Vendor Note should not blank");
			    }*/
			    
			   /* if(!(cm.isSpecialChar(ven.getNote())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Special characters not allowed for Vendor note");
				  addFieldError("note","Special characters not allowed for Vendor note");
				}*/
		if(flag1)
		{
			 //int vid=Integer.parseInt(request.getParameter("vendorid"));
			int vid=ven.getId();
			
			 //System.out.println("vid======="+vid);
		
		String result=vendao.modifyVendor(vid, ven.getVendor_name(), ven.getVendor_contact_person(),ven.getNote().trim(),ven.getUpdated_by(),ven.getStatus());
		String str[]=result.split("\\|");
		String flag=str[0];
		String msg=str[1];
		if(flag.equalsIgnoreCase("true")){
			 flagdetails="success";
			addActionMessage(msg);
			
		}else{
			flagdetails="error";
			addActionError(msg);
		}
		
		}
		//System.out.println("flagdetails--------"+flagdetails);
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		
		}else
		{
			
			if(ven.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		//this.setVendorlist(vendao.getVendorlist());
		return flagdetails;
			}else{
				return "input";
			}
	}
	
	
	public String getValuefromId(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		try{
			//System.out.println("hello");
			//vendao.getlist(this.getIddetails());
			
			 int vid=Integer.parseInt(request.getParameter("vendoriddetails"));
			 //System.out.println("vid======="+vid);
			ven=vendao.getlist(vid);
			ven.setModifyrecord(true);
		   //System.out.println("v---"+ven.getStatus());
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		return "success";
	}
	//public boolean getInactiveRecord(int vendorid,int updatedby,String vendorname){

	public String getInactiveRecord(){
		String status="";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowVendorList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		try{
			//,ven.getVendor_name()
			//System.out.println("Test--------1--------------"+this.getIddetails());
			////System.out.println("Test--------1--------------"+ven.getVendor_name());
		
			 int vid=Integer.parseInt(request.getParameter("vendorid"));
			 //System.out.println("vid======="+vid);
			
			//	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
				status=vendao.getInactiveRecord(vid,usrsessionid);
				if(status.split(":")[0].equals("success")){
					
					addActionMessage("Vendor Id "+vid+" deleted successfully");
					status="success";
				}
				
				if(status.equals("")){
					
					addActionMessage("Vendor Id "+vid+" deleted successfully");
					status="success";
					
					
				
				} else {
					addActionError(status);
					status= "success";
				}
//				String str[]=result.split("\\|");
//				String flag=str[0];
//				//System.out.println("flag--"+flag);
//				String msg=str[1];
//				//System.out.println("msg--"+msg);
//				if(flag.equalsIgnoreCase("true")){
//					addActionMessage(msg);
//				}else{
//					addActionError(msg);
//				}
				//this.setVendorlist(vendao.getVendorlist());
			
		}catch(Exception e){
			//e.printStackTrace();
			Logger logger = TrimaxLogger.getTrimaxLogger();
			logger.error(this.getClass() + "$Exception in LoginAction action",
					e);
			SaveRequest.save(request);
			ErrorLog log = new ErrorLog();
			log.setMsg(e.getMessage());
			ErrorLogDAO.saveException(log);
		}
		return "success";
		}else{
			return "success";
		}
	}
	
	
	
	/**
	 * @return the ven
	 */
	public Vendor getVen() {
		return ven;
	}

	/**
	 * @param ven the ven to set
	 */
	public void setVen(Vendor ven) {
		this.ven = ven;
	}

	/**
	 * @return the vendorlist
	 */
	public ArrayList<Vendor> getVendorlist() {
		return vendorlist;
	}

	/**
	 * @param vendorlist the vendorlist to set
	 */
	public void setVendorlist(ArrayList<Vendor> vendorlist) {
		this.vendorlist = vendorlist;
	}

	/**
	 * @return the vendao
	 */
	public VendorDAO getVendao() {
		return vendao;
	}

	/**
	 * @param vendao the vendao to set
	 */
	public void setVendao(VendorDAO vendao) {
		this.vendao = vendao;
	}

	/**
	 * @return the iddetails
	 */
	public int getIddetails() {
		return iddetails;
	}

	/**
	 * @param iddetails the iddetails to set
	 */
	public void setIddetails(int iddetails) {
		this.iddetails = iddetails;
	}
	
	/*public String execute(){
		//System.out.println("test");
		return null;
	}*/
	/*
	public void validate(){
		CommonValidation cm=new CommonValidation();
	    if(ven.getVendor_name().length()==0){
	    	//flagdetails="error";
			//flag1=false;
			//addActionError("Vendor name should not blank");
			addFieldError("vendor_name","Vendor name should not blank");
	    }
	    
	    if(!(cm.isSpecialChar(ven.getVendor_name())))
		{
		 flagdetails="error";
		  flag1=false;
		  addActionError("Special characters not allowed for Vendor name"); 
		}
	    
	    if(ven.getVendor_contact_person().length()==0){
	    	flagdetails="error";
			flag1=false;
			addActionError("Vendor contact person name should not blank");
	    }
	    if(!(cm.isSpecialChar(ven.getVendor_contact_person())))
		{
		 flagdetails="error";
		  flag1=false;
		  addActionError("Special characters not allowed for Vendor contact person"); 
		}
	    
	    if(ven.getNote().length()==0){
	    	flagdetails="error";
			flag1=false;
			addActionError("Vendor Note should not blank");
	    }
	    if(!(cm.isSpecialChar(ven.getNote())))
		{
		 flagdetails="error";
		  flag1=false;
		  addActionError("Special characters not allowed for Vendor note"); 
		}
	}*/

	
}