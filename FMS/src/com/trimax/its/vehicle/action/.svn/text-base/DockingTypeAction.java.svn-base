package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.DockingTypeDao;
import com.trimax.its.vehicle.model.DockingType;


public class DockingTypeAction extends ActionSupport {
	
	String returnString;
	private List<DockingType> dockingTypeList;
	private DockingType dockingTypeDetails;
	private int isNewDockingType;
	public int updatedDockingTypeId;

	DockingTypeDao daoObject = new DockingTypeDao();
	
	
	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	public List<DockingType> getDockingTypeList() {
		return dockingTypeList;
	}


	public void setDockingTypeList(List<DockingType> DockingTypeList) {
		this.dockingTypeList = DockingTypeList;
	}

	
	public DockingType getDockingTypeDetails() {
		return dockingTypeDetails;
	}


	public void setDockingTypeDetails(DockingType DockingTypeDetails) {
		this.dockingTypeDetails = DockingTypeDetails;
	}

	public int getIsNewDockingType() {
		return isNewDockingType;
	}


	public void setIsNewDockingType(int isNewDockingType) {
		this.isNewDockingType = isNewDockingType;
	}


	public int getUpdatedDockingTypeId() {
		return updatedDockingTypeId;
	}


	public void setUpdatedDockingTypeId(int updatedDockingTypeId) {
		this.updatedDockingTypeId = updatedDockingTypeId;
	}

	public String execute()
	{
		//this.setDockingTypeList(daoObject.getDockingTypeList());
		return "success";
	}
	public String editDockingType()
	{
		
		String dockingTypeId = (ServletActionContext.getRequest().getParameter("dockingTypeId"));
		this.setDockingTypeDetails(daoObject.getDockingTypeDetails(Integer.parseInt(dockingTypeId)));
		return "success";
	}
	
	public String saveEditedDockingTypeDetails(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingTypeList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		if(daoObject.isDockingTypeNew(dockingTypeDetails,"update")){
			if(daoObject.saveEditedDetails(dockingTypeDetails)){
				addActionMessage("Docking Id "+dockingTypeDetails.getDocking_type_id()+" updated successfully");
				returnString = "success";
			}
			else{
				addActionError("Error in Docking Type updation");
				returnString = "fail";
			}
		}else{
			addFieldError("updatedDockingTypeName","Docking Type already exists");
			returnString = "fail";
		}
		return returnString;
		}else{
			return "fail";
		}
	}
	
	public String createDockingType()
	{
		return "success";
	}
	
	public String saveNewDockingType()
	{
		AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	HttpServletRequest request = ServletActionContext.getRequest();

	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "DockingTypeList.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
	if(create.equalsIgnoreCase("Y")){
		if(daoObject.isDockingTypeNew(dockingTypeDetails,"create")){
			if(daoObject.saveDockingType(dockingTypeDetails)){
				addActionMessage("Docking Id "+ServletActionContext.getRequest().getAttribute("createdDockingTypeId")+" Created Successfully");
				returnString = "success";
				
				ServletActionContext.getRequest().removeAttribute("createdDockingTypeId");
			}
			else{
				addActionError("Error in Docking Type Creation");
				returnString = "fail";
			}
		}else{
			addFieldError("newDockingType", "Docking Type already exists ");
			returnString = "fail";
		}
		return returnString;
		}else{
			return "fail";
		}
	}
	
	/*public String deleteDockingType()
	{
		int dockingTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("DockingTypeId"));
		
		if(daoObject.deleteDockingType(dockingTypeId))
		{
			addActionMessage("Docking Id "+dockingTypeId+" deleted successfully");
			returnString = "success";
		}else{
			addActionError("Error in Docking deletion");
			returnString = "fail";
		}
		return returnString;
	}*/
	
	public void validate()
	{
		if(updatedDockingTypeId!=0)
		{
			if(dockingTypeDetails.getDocking_type()==null||dockingTypeDetails.getDocking_type().trim().equals("")){
				addFieldError("updatedDockingTypeName","Please Enter Docking Type");
			}else if(!dockingTypeDetails.getDocking_type().trim().matches("^[a-zA-Z0-9- ]*$")){
				addFieldError("updatedDockingTypeName","Invalid Docking Type");
			}
			
			if(dockingTypeDetails.getDockingTypeKms()==null||dockingTypeDetails.getDockingTypeKms().trim().equals("")){
				addFieldError("dockingTypeDetails.dockingTypeKms",	"Please Enter Docking Type Kms");
			}
			else if(!Pattern.matches("\\d*", dockingTypeDetails.getDockingTypeKms())){
					addFieldError("dockingTypeDetails.dockingTypeKms",	"Incorrect value for Docking Type Kms");
			}
			
			if(dockingTypeDetails.getDockingKmAlert()==null||dockingTypeDetails.getDockingKmAlert().trim().equals("")){
				addFieldError("dockingTypeDetails.dockingKmAlert",	"Please Enter KM limit for Alert");
			}
			else if(!Pattern.matches("\\d*", dockingTypeDetails.getDockingKmAlert())){
					addFieldError("dockingTypeDetails.dockingKmAlert",	"Incorrect value for KM limit for Alert");
			}
		}
		if(isNewDockingType!=0){
			
			if(dockingTypeDetails.getDocking_type()==null||dockingTypeDetails.getDocking_type().trim().equals("")){
				addFieldError("newDockingType","Please Enter Docking ");
			}else if(!dockingTypeDetails.getDocking_type().trim().matches("^[a-zA-Z0-9- ]*$")){
				addFieldError("newDockingType","Invalid Docking Type");
			}
			
			if(dockingTypeDetails.getDockingTypeKms()==null||dockingTypeDetails.getDockingTypeKms().trim().equals("")){
				addFieldError("dockingTypeDetails.dockingTypeKms",	"Please Enter Docking Type Kms");
			}
			else if(!Pattern.matches("\\d*", dockingTypeDetails.getDockingTypeKms())){
					addFieldError("dockingTypeDetails.dockingTypeKms",	"Incorrect value for Docking Type Kms");
			}
			
			if(dockingTypeDetails.getDockingKmAlert()==null||dockingTypeDetails.getDockingKmAlert().trim().equals("")){
				addFieldError("dockingTypeDetails.dockingKmAlert",	"Please Enter KM limit for Alert");
			}
			else if(!Pattern.matches("\\d*", dockingTypeDetails.getDockingKmAlert())){
					addFieldError("dockingTypeDetails.dockingKmAlert",	"Incorrect value for KM limit for Alert");
			}
		}
		if(hasFieldErrors()){
			
		}
	}
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public void getDockingTypeRecordsList() throws Exception {
	
		try {
		HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			DockingTypeDao dockingdao = new DockingTypeDao();
			
			String[] dbcol = { "", "docking_type_id", "docking_type", "dockingTypeKms","dockingKmAlert" };
			

			///int cnt = dockingdao.getTotalRecords();
		//	System.out.println("Count------>" + cnt);
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

			int total = -1;
			total = dockingdao.getTotalRecords(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			//COL_NAME = colName;
			DIR = dir;
			START = start;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = dockingdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}
	}
}
