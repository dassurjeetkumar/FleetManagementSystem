package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.ModelTypeDAO;
import com.trimax.its.vehicle.model.ModelType;

public class ModelTypeAction extends ActionSupport {

	String returnString="";
	private List<ModelType> modelTypeList;
	private ModelType modelTypeDetails;
	private int isNewModelType;
	public int updatedModelTypeId;

	ModelTypeDAO daoObject = new ModelTypeDAO();
	
	public List<ModelType> getModelTypeList() {
		return modelTypeList;
	}


	public void setModelTypeList(List<ModelType> ModelTypeList) {
		this.modelTypeList = ModelTypeList;
	}

	
	public ModelType getModelTypeDetails() {
		return modelTypeDetails;
	}


	public void setModelTypeDetails(ModelType ModelTypeDetails) {
		this.modelTypeDetails = ModelTypeDetails;
	}

	public int getIsNewModelType() {
		return isNewModelType;
	}


	public void setIsNewModelType(int isNewModelType) {
		this.isNewModelType = isNewModelType;
	}


	public int getUpdatedModelTypeId() {
		return updatedModelTypeId;
	}


	public void setUpdatedModelTypeId(int updatedModelTypeId) {
		this.updatedModelTypeId = updatedModelTypeId;
	}

	public String execute()
	{
		this.setModelTypeList(daoObject.getModelTypeList());
		return "success";
	}
	public String editModelType()
	{
		
		String modelTypeId = (ServletActionContext.getRequest().getParameter("editModelTypeId"));
		this.setModelTypeDetails(daoObject.getModelTypeDetails(Integer.parseInt(modelTypeId)));
		return "success";
	}
	
	public String saveEditedModelTypeDetails(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ModelTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		
		DependencyChecker dc=new DependencyChecker();
		if(edit.equalsIgnoreCase("Y")){
		String status=dc.getStatus(modelTypeDetails.getModel_type_id(),"model_type");
		System.out.println("status---"+status);
		if(status.equals("")||(!status.equals("") &&  modelTypeDetails.getStatus().equals("ACTIVE"))){	
		if(daoObject.isModelTypeNew(modelTypeDetails,"update")){
			if(daoObject.saveEditedDetails(modelTypeDetails)){
				addActionMessage("Model Id "+modelTypeDetails.getModel_type_id()+" updated successfully");
				returnString = "success";
			}
			else{
				addActionError("Error in Model updation");
				returnString = "fail";
			}
		}else{
			addActionError("Model Type already exist");
			returnString = "fail";
		}
		}else
		{
			
			if(modelTypeDetails.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}	
			
		}
		return returnString;
		}else{
			return "input";
		}
	}
	
	public String createModelType()
	{
		return "success";
	}
	
	public String saveNewModelType()
	 {AccessRightsDao  accessrightdao=new AccessRightsDao();
	 AccessRights accessrights=new AccessRights();
	 HttpServletRequest request = ServletActionContext.getRequest();

	 int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	 accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ModelTypeList.action");
	 String access=accessrights.getACCESS();
	 String create=accessrights.getCREATE();
	 String edit=accessrights.getEDIT();
	 String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		if(daoObject.isModelTypeNew(modelTypeDetails,"create")){
			if(daoObject.saveModelType(modelTypeDetails)){
				addActionMessage("Model Id "+ServletActionContext.getRequest().getAttribute("createdModelTypeId")+" Created Successfully");
				returnString = "success";
				ServletActionContext.getRequest().removeAttribute("createdModelTypeId");
			}
			else{
				addActionError("Error in Model creation");
				returnString = "fail";
			}
		}else{
			addActionError("Model Type already exist");
			returnString = "fail";
		}
		return returnString;
		}else{
			return "fail";
		}
	}
	
	public String deleteModelType()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	HttpServletRequest request = ServletActionContext.getRequest();
	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ModelTypeList.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
	if(delete.equalsIgnoreCase("Y")){
		String status="";
		int modelTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("modelTypeId"));
		
		status=daoObject.deleteModelType(modelTypeId);
		if(status.split(":")[0].equals("success")){
		addActionMessage("Model Type Id "+modelTypeId+" deleted successfully");			
		returnString = "success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Model Type Id "+modelTypeId+" deleted successfully");			
			returnString = "success";
		} else {
			addActionError(status);
			returnString = "success";
		}
			
		return returnString;
	} else{
		addActionMessage("Access Denied - User Does Not Have Access Privileges");

		return "success";
	}
	}
	
	public void validate()
	{
		if(updatedModelTypeId!=0)
		{
			if(modelTypeDetails.getModel_type_name()==null||modelTypeDetails.getModel_type_name().trim().equals("")){
				addFieldError("updatedModelTypeName","Please Enter Model ");
			}else if(!modelTypeDetails.getModel_type_name().trim().matches("^[a-zA-Z0-9- ]*$")){
				addFieldError("updatedModelTypeName","Invalid Model");
			}
		}
		if(isNewModelType!=0){
			if(modelTypeDetails.getModel_type_name()==null||modelTypeDetails.getModel_type_name().trim().equals("")){
				addFieldError("newModelType","Please Enter Model ");
			}else if(!modelTypeDetails.getModel_type_name().trim().matches("^[a-zA-Z0-9- ]*$")){
				addFieldError("newModelType","Invalid Model");
			}
		}
	}
	
	
	
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String getModelTypeRecordsList() throws IOException{
		

		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
		Number cnt = daoObject.getTotalModelTypeHistoryRecords();
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
		total = daoObject.getTotalModelTypeHistoryRecords();
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		result = daoObject.getDockingHistoryData(total, request,Integer.parseInt(sCol),sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
}
