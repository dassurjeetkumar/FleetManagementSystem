package com.trimax.its.usermanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.common.CommonValidation;
import com.trimax.its.usermanagement.dao.DesignationTypeDAO;
import com.trimax.its.usermanagement.dao.EmployeeTypeDAO;
import com.trimax.its.usermanagement.model.DesignationType;





import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;


import com.trimax.its.util.HibernateUtil;


public class DesignationTypeAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private DesignationType designationtype;
	
	private int iddetails;
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	 public int getIddetails() {
		return iddetails;
	}
	public void setIddetails(int iddetails) {
		this.iddetails = iddetails;
	}
	private String requestType;
	 
	 public List<DesignationType> getList() {
		return list;
	}
	public void setList(List<DesignationType> list) {
		this.list = list;
	}
	List<DesignationType> list;
	
	 public DesignationType getDesignationtype() {
		return designationtype;
	}
	public void setDesignationtype(DesignationType designationtype) {
		this.designationtype = designationtype;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	
	public String getDesignationtypelist(){
		DesignationTypeDAO designation = new DesignationTypeDAO();
		////System.out.println("designationtype-------"+designationtype);
		// list = designation.getDesignationList();
		 //System.out.println("list---"+list);
		// HttpServletRequest request=ServletActionContext.getRequest();
		 return "success";
	}
	
	public String execute() throws IOException{
	
		return "success";
	}
	public String getListAjax() throws IOException{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		DesignationTypeDAO designation = new DesignationTypeDAO();
		String[] cols = {"","Designation Id ", "Designation type Name","Designation Type Code", "Status", "Note"};
		
		String[] dbcol={"","designation_type_id","designation_type_name","designation_type_code","status","note"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart=""; 
		String sAmount="";
		String sCol="";
		try{
			sStart= request.getParameter("iDisplayStart");
		 sAmount = request.getParameter("iDisplayLength");
		//System.out.println("sAmount---"+sAmount);
		 sCol = request.getParameter("iSortCol_0");
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("sCol---"+sCol);
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
			total = designation.getTotalRecords();
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			//System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
			//result = busstopdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			//BusStopReportDao bustopsreport=new BusStopReportDao();
			
			result=designation.getDesignationTypeList(total,request,dbcol[Integer.parseInt(sCol)],sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			return null;
		
	}
	 
	
	public String addDesignationType() {
		 DesignationTypeDAO designation = new DesignationTypeDAO();
		 String flagdetails="";
		 boolean flag1=true;
		try{
			
		    HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession sess=request.getSession();
			//System.out.println("designationtype-------"+designationtype.getDesignation_type_name());
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			designationtype.setCreated_by(usrsessionid);
			designationtype.setCreated_date(new Date());
			 CommonValidation cm=new CommonValidation();
			//System.out.println("desiname--------"+designationtype.getDesignation_type_name());
			//System.out.println("desiname--------"+designationtype.getDesignation_type_name().length());
			
			if(designationtype.getDesignation_type_name().length()==0){
				
				flagdetails="error";
				flag1=false;
				//addActionError("Designationtype should not blank");
				addFieldError("designation_type_name","Designation Type should not blank");
			}
			 if(!(cm.isSpecialChar(designationtype.getDesignation_type_name())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Designation type name should  not allowed special characters");
				  addFieldError("designation_type_name","Special characters and symbol not allowed for Designation Type");
					  }
			 
			 if(designationtype.getDesignation_type_code().length()==0){
					
					flagdetails="error";
					flag1=false;
					//addActionError("Designationtype should not blank");
					addFieldError("designation_type_code","Designation Type Code should not blank");
				}
			 
			 if(!(cm.isSpecialChar(designationtype.getDesignation_type_code())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Designation type name should  not allowed special characters");
				  addFieldError("designation_type_code","Special characters and symbol not allowed for Designation Type Code");
					  }
			 
			/*if(designationtype.getNote().length()==0){
				
				flagdetails="error";
				flag1=false;
				//addActionError("Designationtype Note should not blank");
				 addFieldError("note","Designationtype Note should not blank");
			}*/
			/* if(!(cm.isSpecialChar(designationtype.getNote())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Designation not not allowed special characters");
				  addFieldError("note","Special Characters and symbol not allowed for  Designationtype Note ");
					  }*/
			
			if(flag1)
			{
			String uniquecheck=designation.checkuniquness(designationtype.getDesignation_type_name(),designationtype.getDesignation_type_code());
			String str[]=uniquecheck.split("\\|");
			String flag=str[0];
			String msg=str[1];
			//System.out.println("flag------"+flag);
			if(flag.equalsIgnoreCase("true")){
				designationtype.setUpdated_by(0);
				int result = designation.addDesignationType(designationtype);
				if(result>0){
				addActionMessage("Designation Type ID " + result + " Added successfully");
				flagdetails="success";
			}else{
			    if(result ==-1){
			    	addActionError("Designation Type Presentt");
			    	flagdetails="error";
			    }else{
			    	addActionError("Database error");
			    	flagdetails="error";
			    }
			
				
				
			}
			}else{
				addActionError(msg);
		    	flagdetails="error";
			}
			// list = designation.getDesignationList();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			 return flagdetails;
	}

	public String edit(){
		try{
		
			 DesignationTypeDAO designation = new DesignationTypeDAO();
			 HttpServletRequest request = ServletActionContext.getRequest();
			// System.out.println("id details-----------"+this.getIddetails());
			 int desid=Integer.parseInt(request.getParameter("designation_type_id"));
			 designationtype=designation.getlistfromId(desid);
			 //System.out.println("designationtype---------"+designationtype.getDesignation_type_id());
			 //System.out.println("name----------"+designationtype.getDesignation_type_name());
			 //list = designation.getDesignationList();
			}catch(Exception e){
			e.printStackTrace();
		}
		 return "success";
	}
	
	
	
	
	public String updateDesignation(){
		//System.out.println("in updateDesignation");
		String flagdetails="";
		 boolean flag1=true;
			DesignationTypeDAO designation = new DesignationTypeDAO();
		   HttpServletRequest request = ServletActionContext.getRequest();
		  	   HttpSession sess=request.getSession();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			 CommonValidation cm=new CommonValidation();
		   designationtype.setUpdated_by(usrsessionid);
			designationtype.setUpdated_date(new Date());
			//System.out.println("desiname--------"+designationtype.getDesignation_type_name().trim().length());
			
			if(designationtype.getDesignation_type_name().trim().length()==0){
				
				flagdetails="error";
				flag1=false;
				//addActionError("Designationtype not blank");
				addFieldError("designation_type_name","Designation Type should not blank");
			}
			
			 if(!(cm.isSpecialChar(designationtype.getDesignation_type_name())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Designation type name should  not allowed special characters");
				  addFieldError("designation_type_name","Special characters and symbol not allowed for Designation Type");
					  }
			
			 
			 if(designationtype.getDesignation_type_code().length()==0){
					
					flagdetails="error";
					flag1=false;
					//addActionError("Designationtype should not blank");
					addFieldError("designation_type_code","Designation Type Code should not blank");
				}
			 
			 if(!(cm.isSpecialChar(designationtype.getDesignation_type_code())))
				{
				 flagdetails="error";
				  flag1=false;
				  //addActionError("Designation type name should  not allowed special characters");
				  addFieldError("designation_type_code","Special characters and symbol not allowed for Designation Type Code");
					  }
			 
				/*if(designationtype.getNote().length()==0){
				
				flagdetails="error";
				flag1=false;
				//addActionError("Designationtype Note should not blank");
				 addFieldError("note","Designationtype Note should not blank");
			}*/
				
				/* if(!(cm.isSpecialChar(designationtype.getNote())))
					{
					 flagdetails="error";
					  flag1=false;
					  //addActionError("Designation not not allowed special characters"); 
					  addFieldError("note","Special Characters and symbol not allowed for  Designationtype Note ");
						  }*/
				
				
		   if(flag1)
		   {
			   
			   //int desid=Integer.parseInt(request.getParameter("designation_type_id"));
			   //System.out.println("desid---"+desid);
			   //System.out.println("designationtype---------"+designationtype.getDesignation_type_id());
			   //System.out.println("designation Status--------"+designationtype.getStatus());
			   int desid=designationtype.getDesignation_type_id();	
			   String result=designation.saveEdited(desid, designationtype, usrsessionid);
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
			// list = designation.getDesignationList();
		   }
		  return flagdetails;
		}
	
	
	public String deleteDesignation(){
		 DesignationTypeDAO designation = new DesignationTypeDAO();
		 HttpServletRequest request = ServletActionContext.getRequest();
		 HttpSession sess=request.getSession();
		 int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		 int desid=Integer.parseInt(request.getParameter("designation_type_id"));
		 if(designation.deleteDesignationType(desid,usrsessionid))
		 {
			 addActionMessage("Designation Type ID " + desid + " Deleted successfully");
		 }else{
			// addActionMessage("Record not deleted");
			 addActionError("Designation Type ID " + desid + " not deleted ");
		 }
		// list = designation.getDesignationList();
		 return "success";
	}
	
	public String showDesigationType(){
		
		 return "success";
	}
	 

}
