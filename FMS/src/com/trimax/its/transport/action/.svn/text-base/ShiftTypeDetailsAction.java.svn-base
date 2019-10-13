package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.transport.dao.ShiftTypeDetailDao;
import com.trimax.its.transport.model.ShiftType;

public class ShiftTypeDetailsAction extends ActionSupport {
	ShiftType shiftype;
	public ShiftType getShiftype() {
		return shiftype;
	}

	public void setShiftype(ShiftType shiftype) {
		this.shiftype = shiftype;
	}

	public Map<Integer, String> getSheduletypelist() {
		return sheduletypelist;
	}

	public void setSheduletypelist(Map<Integer, String> sheduletypelist) {
		this.sheduletypelist = sheduletypelist;
	}

	private Map<Integer, String> sheduletypelist;
	

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	
	
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	
	@SkipValidation
	public String getShiftypelistDetails() throws IOException {
	/*	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		JSONObject result = new JSONObject();
		ShiftTypeDao shiftypedao=new ShiftTypeDao();
		try {
			System.out.println("test");
			String[] dbcol = {"","shift_type_id", "shift_type_name","schedule_type_name","duration","notes","status"};
			String[] cols =  {"","shift_type_id", "shift_type_name","schedule_type_name","duration","notes","status"};
			
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
			
			//AMOUNT = amount;
			//SEARCH_TERM = request.getParameter("sSearch");
			//total = roledao.getTotalRecordsForCount(SEARCH_TERM);
			total=shiftypedao.getTotalRecords(request, dbcol[Integer.parseInt(sCol)], sdir);
			//COL_NAME = colName;
			//DIR = dir;
			//START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			//result = roledao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			result=shiftypedao.getShiftypelist(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
			*/
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();	
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		
		ShiftTypeDetailDao shiftypedao=new ShiftTypeDetailDao();
		try {
		String[] dbcol={"","shift_type_id", "shift_type_name","duration","schedule_type_name","shift_type.notes","status"};
		String[] cols={"","shift_type_id", "shift_type_name","duration","schedule_type_name","shift_type.notes","status"};
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
	
		total=shiftypedao.getTotalRecords(request, dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		//COL_NAME = colName;
		DIR = dir;
		START = start;

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result=shiftypedao.getShiftypelist(total,request,dbcol[Integer.parseInt(sCol)],sdir,viewdeletedrecord);
		out.print(result);
			
		}catch(Exception e){
//			e.printStackTrace();
		}
		return null;

}
	
	@SkipValidation
	public String getdisplayShifttype(){
		ShiftTypeDetailDao shiftypedao=new ShiftTypeDetailDao();
		try{
			sheduletypelist=shiftypedao.getScheduletypeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
		
	}
	
	public String getAddShifttype(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ShiftTypeDetailDao shiftypedao=new ShiftTypeDetailDao();
		String flagdetails="";
		try{
			sheduletypelist=shiftypedao.getScheduletypeList();
			int i=shiftypedao.addShiftype(shiftype, Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			if(i>0)
			{
				addActionMessage("Duty Type Id " +  i + " Added Successfully");
				flagdetails="success";	 
			}else{
				if(i ==-1)
				{
					addActionError("Duty Type Already Exist");
					flagdetails="input";
					
				}else{
					addActionError("Database error");
			    	flagdetails="input";
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flagdetails;
	}
	
	@SkipValidation
	public String getEditDetails(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ShiftTypeDetailDao shiftypedao=new ShiftTypeDetailDao();
		try{
			sheduletypelist=shiftypedao.getScheduletypeList();
			int iddetail=Integer.parseInt(request.getParameter("shiftypeid").toString());
			shiftype=shiftypedao.getEditShifttype(iddetail);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	@SkipValidation
	public String getDeletedShiftype(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ShiftTypeDetailDao shiftypedao=new ShiftTypeDetailDao();
		String flagdetails="";
		try{
			int iddetail=Integer.parseInt(request.getParameter("shiftypeiddetail").toString());
			
			DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(Integer.parseInt(request.getParameter("shiftypeiddetail")),"shift_type");
	       if(status.equals("")){
			
			if(shiftypedao.getDeletedRecord(shiftype, Integer.parseInt(request.getSession().getAttribute("userid").toString()),iddetail))
			{
				addActionMessage("Duty Type Id " +  iddetail + " Deleted Successfully");
			}else{
				addActionMessage("Duty Type Id " +  iddetail + " Not Deleted ");
			}
	       }
	       else{
               addActionError(status);
               //returnResult = "success";
           }
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	
	public String getUpdateDetails(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ShiftTypeDetailDao shiftypedao=new ShiftTypeDetailDao();
		String flagdetails="";
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(shiftype.getId(),"shift_type");
		if(status.equals("")||(!status.equals("") &&  shiftype.getStatus().equals("ACTIVE"))){	
		try{
			sheduletypelist=shiftypedao.getScheduletypeList();
			int iddetails=shiftype.getId();
			int i=shiftypedao.updateShiftype(shiftype, Integer.parseInt(request.getSession().getAttribute("userid").toString()));
			if(i>0)
			{
				addActionMessage("Duty Type Id "+ iddetails + " Updated Successfully");
				flagdetails="success";	 
			}else{
				if(i ==-1)
				{
					addActionError("Duty Type  Already Exist");
					flagdetails="input";
					
				}else{
					addActionError("Database error");
			    	flagdetails="input";
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		}else
		{
			
			if(shiftype.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
		}
		return flagdetails;
	}
	
	
	
	public void validate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ShiftTypeDetailDao shiftypedao=new ShiftTypeDetailDao();
		sheduletypelist=shiftypedao.getScheduletypeList();
		CommonValidation common=new CommonValidation();
		  String shiftypename=shiftype.getShiftTypeName();
		  String duration=shiftype.getDuration();
		if((shiftypename.trim().equals(""))||(shiftypename==null)||(shiftypename.trim().length()==0))
		{
			 addFieldError("ShiftTypeName","Duty Type Name should not blank");
		}
		if(!common.isSpecialChar(shiftypename))
			{
			addFieldError("ShiftTypeName","Special Character Not Allowed For Duty Type");
			}
		
		if(shiftype.getSchedule_type_id().getSchedule_type_id()==0){
			addFieldError("schedule_type_id","Please Select Schedule Type ");
		}
		if((duration.trim().equals(""))||(duration==null)||(duration.trim().length()==0))
		{
			 addFieldError("duration","Duration should not blank");
		}
	/*	if(!common.isSpecialChar(duration))
		{
			 addFieldError("duration","Special Character Not Allowed For Duration");
		}*/
	}
}