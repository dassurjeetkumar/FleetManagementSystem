package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.transport.dao.CaseTypeDAO;
import com.trimax.its.transport.model.CaseType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;


public class CaseTypeAction extends ActionSupport {

	String returnString="";
	private List<CaseType> caseTypeList;
	private CaseType caseTypeDetails;
	private int isNewCaseType;
	public int updatedCaseTypeId;

	CaseTypeDAO daoObject = new CaseTypeDAO();
	
	public List<CaseType> getCaseTypeList() {
		return caseTypeList;
	}


	public void setCaseTypeList(List<CaseType> CaseTypeList) {
		this.caseTypeList = CaseTypeList;
	}

	
	public CaseType getCaseTypeDetails() {
		return caseTypeDetails;
	}


	public void setCaseTypeDetails(CaseType CaseTypeDetails) {
		this.caseTypeDetails = CaseTypeDetails;
	}

	public int getIsNewCaseType() {
		return isNewCaseType;
	}


	public void setIsNewCaseType(int isNewCaseType) {
		this.isNewCaseType = isNewCaseType;
	}


	public int getUpdatedCaseTypeId() {
		return updatedCaseTypeId;
	}


	public void setUpdatedCaseTypeId(int updatedCaseTypeId) {
		this.updatedCaseTypeId = updatedCaseTypeId;
	}

	public String execute()
	{
		this.setCaseTypeList(daoObject.getCaseTypeList());
		return "success";
	}
	public String editCaseType()
	{
		
		String caseTypeId = (ServletActionContext.getRequest().getParameter("editCaseTypeId"));
		this.setCaseTypeDetails(daoObject.getCaseTypeDetails(Integer.parseInt(caseTypeId)));
		return "success";
	}
	
	public String saveEditedCaseTypeDetails(){
		 HttpServletRequest request = ServletActionContext.getRequest();
			AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "CaseTypeList.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(edit.equalsIgnoreCase("Y")){
		if(daoObject.isCaseTypeNew(caseTypeDetails,"update")){
			if(daoObject.saveEditedDetails(caseTypeDetails)){
				addActionMessage("Case Type Id "+caseTypeDetails.getCaseTypeId()+" updated successfully");
				returnString = "success";
			}
			else{
				addActionError("Error in Case Type updation");
				returnString = "fail";
			}
		}else{
			addActionError("This Case Type already exist");
			returnString = "fail";
		}
		return returnString;}else{
			return "fail";
		}
	}
	
	public String createCaseType()
	{
		return "success";
	}
	
	public String saveNewCaseType()
	{   HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "CaseTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		if(daoObject.isCaseTypeNew(caseTypeDetails,"create")){
			if(daoObject.saveCaseType(caseTypeDetails)){
				addActionMessage("Case Type Id "+ServletActionContext.getRequest().getAttribute("createdCaseTypeId")+" Created Successfully");
				returnString = "success";
				ServletActionContext.getRequest().removeAttribute("createdCaseTypeId");
			}
			else{
				addActionError("Error in Case Type creation");
				returnString = "fail";
			}
		}else{
			addActionError("This Case Type already exist");
			returnString = "fail";
		}
		return returnString;}
		else{
			return "fail";
		}
	}
	
	public String deleteCaseType()
	{HttpServletRequest request = ServletActionContext.getRequest();
	
	AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "CaseTypeList.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
	if(delete.equalsIgnoreCase("Y")){
		int caseTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("caseTypeId"));
		
		if(daoObject.deleteCaseType(caseTypeId))
		{
			addActionMessage("Case Type Id "+caseTypeId+" deleted successfully");
			returnString = "success";
		}else{
			addActionError("Error in Case Type deletion");
			returnString = "fail";
		}
		System.out.println("\t before return ::"+returnString);
		return returnString;}
	else{
		addActionMessage("Access Denied - User Does Not Have Access Privileges");
		return "fail";
	}
	}
	
	public void validate()
	{
		if(updatedCaseTypeId!=0)
		{
			if(caseTypeDetails.getCaseTypeName()==null||caseTypeDetails.getCaseTypeName().trim().equals("")){
				addFieldError("updatedCaseTypeName","Please Enter Case Type");
			}else if(!caseTypeDetails.getCaseTypeName().trim().matches("^[a-zA-Z0-9- ]*$")){
				addFieldError("updatedCaseTypeName","Invalid Case Type");
			}
		}
		if(isNewCaseType!=0){
			if(caseTypeDetails.getCaseTypeName()==null||caseTypeDetails.getCaseTypeName().trim().equals("")){
			
				addFieldError("newCaseType","Please Enter Case Type ");
			}else if(!caseTypeDetails.getCaseTypeName().trim().matches("^[a-zA-Z0-9- ]*$")){
				addFieldError("newCaseType","Invalid Case Type");
			}
		}
}
	
	
	
	
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String getCaseTypeRecordsList() throws IOException{
		

		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		Number cnt = daoObject.getTotalCaseTypeHistoryRecords();
		
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
		total = daoObject.getTotalCaseTypeHistoryRecords();
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result = daoObject.getCaseTypeHistoryData(total, request,Integer.parseInt(sCol),sdir);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
}
