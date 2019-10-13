package com.trimax.its.reportmaster.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.componenttype.dao.ComponentTypeDao;
import com.trimax.its.componenttype.model.ComponentType;
import com.trimax.its.fare.dao.RateMasterDAO;
import com.trimax.its.reportmaster.dao.ReportMasterDao;
import com.trimax.its.reportmaster.model.EmailMaster;
import com.trimax.its.reportmaster.model.ReportMaster;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class ReportMasterAction extends ActionSupport implements Preparable{
	
	ReportMaster reportmaster=new ReportMaster();
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	String insertstaus;
	String updatestaus;
	String deletestaus;
	
	public ReportMaster getReportmaster() {
		return reportmaster;
	}
	public void setReportmaster(ReportMaster reportmaster) {
		this.reportmaster = reportmaster;
	}
	public String getInsertstaus() {
		return insertstaus;
	}
	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}
	public String getUpdatestaus() {
		return updatestaus;
	}
	public void setUpdatestaus(String updatestaus) {
		this.updatestaus = updatestaus;
	}
	public String getDeletestaus() {
		return deletestaus;
	}
	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}
	@SkipValidation
	public String viewReportMaster(){
		
		return "success";
		
	}
	@SkipValidation
	public String createReportMaster(){
		return "success";
	}
	
	public String createReportMasterAction(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewReportMaster.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
	//	HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ReportMasterDao dao=new ReportMasterDao();
		if(!dao.checkReportMaster(reportmaster.getReport_master_name())){
		    reportmaster.setCreated_by(request.getSession().getAttribute("userid").toString());
		    reportmaster.setCreated_date(new java.util.Date());
		String res="";
		int id=0;
		try{
		 id= dao.createReportMaster(reportmaster);
		}catch(Exception ex)
		{
			setInsertstaus("fail");
			return "input";
		}finally{
			setInsertstaus("success");
			addActionMessage("Report Master "+id+" Created Successfully");
		}
		}
		else{
			setInsertstaus("duplicate");
			return "input";
		}
		return "success";
	}else{
			return "input";
		}
		
	}
	@SkipValidation
	public String editReportMaster(){
		ReportMasterDao dao=new ReportMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		reportmaster=dao.getEditedReportMaster(Integer.parseInt(request.getParameter("ratemasterid")));
		return "success";
	}
	
	public String editReportMasterAction(){
		ReportMasterDao dao=new ReportMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
	
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewReportMaster.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		DependencyChecker dc=new DependencyChecker();
		if(edit.equalsIgnoreCase("Y")){
		String status=dc.getStatus(reportmaster.getReport_master_id(),"report_master");
		if(status.equals("")||(!status.equals("") &&  reportmaster.getStatus().equals("ACTIVE"))){	
		
		if(dao.checkReportMasterForUpdate(reportmaster.getReport_master_name(), reportmaster.getReport_master_id())){
			reportmaster.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
		dao.updateReportMaster(reportmaster,reportmaster.getReport_master_id());
		}catch(Exception ex){
			setUpdatestaus("fail");
			return "input";
		}finally{
			setUpdatestaus("success");
			addActionMessage("Report Master Id "+ reportmaster.getReport_master_id()+" Updated Successfully" );
		}
		}else if(!dao.checkReportMaster(reportmaster.getReport_master_name())){
			try{
				dao.updateReportMaster(reportmaster,reportmaster.getReport_master_id());
				}catch(Exception ex){
					setUpdatestaus("fail");
					return "input";
				}finally{
					setUpdatestaus("success");
					addActionMessage("Report Master Id "+ reportmaster.getReport_master_id()+" Updated Successfully" );
				}
		}else
		{
			setUpdatestaus("duplicate");
			return "input";
		}
		}else
		{
			
			if(reportmaster.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
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
	public String deleteReportMasterAction()
	{
		ReportMasterDao dao=new ReportMasterDao();
		ReportMaster master=new ReportMaster();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
	
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "viewReportMaster.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
			master.setUpdated_by(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		try{
			
			DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(Integer.parseInt(request.getParameter("delratemasterid")),"report_master");
	       if(status.equals("")){
			dao.deleteReportMaster(master,Integer.parseInt(request.getParameter("delratemasterid")));
			addActionMessage("Report Master Id "+request.getParameter("delratemasterid")+" Deleted Successfully");
	       }
	       else{
               addActionError(status);
               //returnResult = "success";
           }
		}catch(Exception ex)
		{
			setDeletestaus("fail");
			return "input";
		}finally{
			setDeletestaus("success");
			
		}
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");

			return "input";
		}
		
	}
	
	
	@SkipValidation
	public String execute(){
		
		return null;
		
	}

	@SkipValidation
	public void validate() {
		// TODO Auto-generated method stub
		CommonValidation common=new CommonValidation();
		if(reportmaster.getReport_master_name().trim().equals("") || reportmaster.getReport_master_name().equals(" "))
		{
			addFieldError("report_master_name", "Report Master is Required");
			
			reportmaster.setReport_master_name(reportmaster.getReport_master_name());
			
		}
		
		if(!common.isSpecialChar(reportmaster.getReport_master_name()))
		{
			addFieldError("report_master_name", "Special Character For Report Master is Not Allowed");
			
			this.reportmaster.setReport_master_name(this.reportmaster.getReport_master_name());
		}
		/*if(componentType.getNotes().length()>100){
			addFieldError("componentTypenotes", "Notes size is very long");
		}*/
		
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			ReportMasterDao dao=new ReportMasterDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			
			String[] dbcol = {"","", ""};
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

			String colName = dbcol[col];
			int total = -1;
			total = dao.getTotalRecords(total,request,dbcol[col],sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total,request,dbcol[col],sdir,viewdeletedrecord);
			out.print(result);
		} catch (Exception ex) {
		ex.printStackTrace();	
		} finally {

		}
	}

}
