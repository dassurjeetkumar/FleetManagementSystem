package com.trimax.its.usermanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.report.dao.BusStopReportDao;
import com.trimax.its.usermanagement.dao.EmployeeTypeDAO;
import com.trimax.its.usermanagement.model.EmployeeType;
import com.trimax.its.common.CommonValidation;

public class EmployeeTypeAction extends ActionSupport {
	private int employeeid;
	private String employeetypename;
	private String employeenotes;
	private String employeestauts;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private ArrayList<EmployeeType> employeetypelist; 
	
	EmployeeType e;
	
	public EmployeeType getE() {
		return e;
	}
	public void setE(EmployeeType e) {
		this.e = e;
	}

	EmployeeTypeDAO empdao=new EmployeeTypeDAO();
	
	/*All Setter Getter Method of form*/
	
	public ArrayList<EmployeeType> getEmployeetypelist() {
		return employeetypelist;
	}
	public void setEmployeetypelist(ArrayList<EmployeeType> employeetypelist) {
		this.employeetypelist = employeetypelist;
	}
	
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeetypename() {
		return employeetypename;
	}
	public void setEmployeetypename(String employeetypename) {
		this.employeetypename = employeetypename.trim();
	}
	public String getEmployeenotes() {
		return employeenotes;
	}
	public void setEmployeenotes(String employeenotes) {
		this.employeenotes = employeenotes.trim();
	}
	public String getEmployeestauts() {
		return employeestauts;
	}
	public void setEmployeestauts(String employeestauts) {
		this.employeestauts = employeestauts;
	}
	
	public String execute(){
		try{
			//System.out.println("IN execute");
		//this.setEmployeetypelist(empdao.getEmployeeTypeList());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	public String getDisplayEmployeetypelist() throws IOException{
		//System.out.println("IN getDisplayEmployeetypelist");
	
			
			//this.setEmployeetypelist(empdao.getEmployeeTypeList());
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			//BusStopReportDao busstopdao = new BusStopReportDao();
			//int cnt = busstopdao.getTotalRecords();
			//System.out.println("Count------>"+cnt);
			EmployeeTypeDAO empdao=new EmployeeTypeDAO();
			String[] cols = {"","EmployeeType Id ", "EmployeeType Name", "Note", "Status"};
			
			String[] dbcol={"","employee_id","employee_type_name","note","status"};
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
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
				total = empdao.getTotalRecords();
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
				
				result=empdao.getEmployeeTypeList(total,request,dbcol[Integer.parseInt(sCol)],sdir);
				//System.out.println("result----------"+result);
				out.print(result);
				return null;
			
			
			
		
		
	}
	
	public String getAddEmployeetype(){
		//System.out.println("IN getAddEmployeetype");
		String flag="";
		try{
			
		boolean flagdetails=true;
			 HttpServletRequest request=ServletActionContext.getRequest();
			 int createdby =Integer.parseInt((request.getSession().getAttribute("userid")).toString());
			 CommonValidation cm=new CommonValidation();
				  //System.out.println("createdby------"+createdby);
				  //System.out.println("empname---------"+e.getEmployee_typename().length());
				  /*if(this.employeetypename.equalsIgnoreCase("")){
					  flag="error";
					  flagdetails=false;
					  addActionError("Employee Type  Should not be blank");
				  }*/
				  //System.out.println("empname-----"+e.getEmployee_typename());
				  if(e.getEmployee_typename().equalsIgnoreCase("")){
					  flag="error";
					  flagdetails=false;
					 // addActionError("Employee Type  Should not be blank");
					  addFieldError("employee_typename","Employee Type Should not be blank");
				  }
				  if(!(cm.isSpecialChar(e.getEmployee_typename())))
					{
					  flag="error";
					  flagdetails=false;
					 // addActionError("Employee type name should  not allowed special characters"); 
					  addFieldError("employee_typename","Special characters and symbol not allowed for Employee Type");
						  }
//				  if(e.getEmployee_note().equalsIgnoreCase(""))
//				  {
//					  flag="error";
//					  flagdetails=false;
//					 // addActionError("Employee Note  Should not be blank");
//					  addFieldError("employee_note","Employee Note  Should not be blank");
//				  }
//				  
//				  if(!(cm.isSpecialChar(e.getEmployee_note())))
//				  {
//						  flag="error";
//						  flagdetails=false;
//						  //addActionError("Note Should not alllowed special characters");
//						  addFieldError("employee_note","Special characters and symbol not allowed for Note");
//				  }
				  
				 if(flagdetails)
				 {
			/*if(empdao.addemployeetype(e.getEmployee_typename(),e.getEmployee_note() ,"NEW",createdby))
			{
				flag="success";
				addActionMessage("Added Record sucessfully");
			}else{
				flag="error";
				addActionError("Employee Type  Already Exists");
				 }
				*/
						String result=empdao.addemployeetype(e.getEmployee_typename(),e.getEmployee_note() ,"ACTIVE",createdby);
						String str[]=result.split("\\|");
						String flag1=str[0];
						//System.out.println("flag1--"+flag1);
						String msg=str[1];
						//System.out.println("msg1--"+msg);
						if(flag1.equalsIgnoreCase("true")){
							flag="success";
							addActionMessage(msg);
						}else{
							flag="error";
							addActionError(msg);
						}
			//this.setEmployeetypelist(empdao.getEmployeeTypeList());
			this.setEmployeeid(0);
			this.setEmployeenotes("");
			this.setEmployeestauts("");
			this.setEmployeetypename("");
				 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public String getEmployeeTypeDetailsFromId(){
		//System.out.println("IN getEmployeeTypeDetailsFromId");
		try{
			
			//vendao.getlist(this.getIddetails());
			//ven=vendao.getlist(this.getIddetails());
			//ven.setModifyrecord(true);
		  // System.out.println("v---"+ven.getVendor_name());
			 HttpServletRequest request=ServletActionContext.getRequest();
			 int employeeiddetails=Integer.parseInt(request.getParameter("employeeid"));
			e=empdao.getEmployeeTypeListUsingid(employeeiddetails);
			//System.out.println("emname---"+e.getEmployee_typename());
			//this.setEmployeetypelist(empdao.getEmployeeTypeList());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public String modifyEmployeeTypeRecord(){
		String flag1="";
		try{
			boolean flagdetails=true;
			 HttpServletRequest request=ServletActionContext.getRequest();
			 int createdby =Integer.parseInt((request.getSession().getAttribute("userid")).toString());
			  //System.out.println("empname---------"+e.getEmployee_typename().length());
			 
			  CommonValidation cm=new CommonValidation();
			  //System.out.println("empname------"+e.getEmployee_typename());
			 if(e.getEmployee_typename().equals("")){
				 flag1="error";
				  flagdetails=false;
				 // addActionError("Employee Type Should not be blank");
				  addFieldError("employee_typename","Employee Type Should not be blank");
			  }
			  if(!(cm.isSpecialChar(e.getEmployee_typename())))
				{
				  flag1="error";
				  flagdetails=false;
				  //addActionError("Employee type name should  not allowed special characters");
				  addFieldError("employee_typename","Special characters and symbol not allowed for Employee Type");
					  }
			  if(e.getEmployee_note().equalsIgnoreCase(""))
			  {
				  flag1="error";
				  flagdetails=false;
				  //addActionError("Employee Note  Should not be blank"); 
				  addFieldError("employee_note","Employee Note  Should not be blank");
			  }
			  
			  if(!(cm.isSpecialChar(e.getEmployee_note())))
			  {
					  flag1="error";
					  flagdetails=false;
					  //addActionError("Note Should not alllowed special characters");
					  addFieldError("employee_note","Special characters and symbol not allowed for Note");
			  }
			  
			 
			 
			 if(flagdetails){
				 //int employeeiddetails=Integer.parseInt(request.getParameter("employeeid"));
				 int employeeiddetails=e.getEmp_id();
				 //System.out.println("employeeiddetails---"+employeeiddetails);
				 
			String result=empdao.modifyEmployeeType(employeeiddetails,e.getEmployee_typename(),e.getEmployee_status(),e.getEmployee_note(),createdby);
			 
			String str[]=result.split("\\|");
			String flag=str[0];
			//System.out.println("flag--"+flag);
			String msg=str[1];
			//System.out.println("msg--"+msg);
			if(flag.equalsIgnoreCase("true")){
				flag1="success";
				addActionMessage(msg);
			}else{
				flag1="error";
				addActionError(msg);
			}
		//	this.setEmployeetypelist(empdao.getEmployeeTypeList());
			this.setEmployeeid(0);
			this.setEmployeenotes("");
			this.setEmployeestauts("");
			this.setEmployeetypename("");
			 }
		}catch(Exception e){e.printStackTrace();}
		return flag1;
	}
	
	public String getInactiveRecord(){
		try{
			//,ven.getVendor_name()
			 HttpServletRequest request=ServletActionContext.getRequest();
			 int createdby =Integer.parseInt((request.getSession().getAttribute("userid")).toString());
			 int employeeiddetails=Integer.parseInt(request.getParameter("employeeid"));
			String result=empdao.getInactiveRecord(employeeiddetails,createdby);
			
				String str[]=result.split("\\|");
				String flag=str[0];
				//System.out.println("flag--"+flag);
				String msg=str[1];
				//System.out.println("msg--"+msg);
				if(flag.equalsIgnoreCase("true")){
					addActionMessage(msg);
				}else{
					addActionError(msg);
				}
				//this.setEmployeetypelist(empdao.getEmployeeTypeList());
				this.setEmployeeid(0);
				this.setEmployeenotes("");
				this.setEmployeestauts("");
				this.setEmployeetypename("");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	public String getDisplayEmployeetype(){
		return "success";
	}

}