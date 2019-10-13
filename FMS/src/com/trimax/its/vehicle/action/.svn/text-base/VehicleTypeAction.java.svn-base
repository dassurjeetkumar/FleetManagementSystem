package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.VehicleTypeDAO;
import com.trimax.its.vehicle.model.VehicleType;

public class VehicleTypeAction extends ActionSupport {

	private List<VehicleType> vehicleTypeList;
	private VehicleType vehicleDetails;
	static VehicleTypeDAO daoObject = new VehicleTypeDAO();
	String returnResult="";
	static Number cnt = daoObject.getTotalVehicleTypeRecords();
	private int isEditVehicleType;
	private int isNewVehicleType;

	public List<VehicleType> getVehicleTypeList() {
		return vehicleTypeList;
	}

	public void setVehicleTypeList(List<VehicleType> vehicleTypeList) {
		this.vehicleTypeList = vehicleTypeList;
	}

	public VehicleType getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(VehicleType vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	
	public int getIsEditVehicleType() {
		return isEditVehicleType;
	}

	public void setIsEditVehicleType(int isEditVehicleType) {
		this.isEditVehicleType = isEditVehicleType;
	}
	@SkipValidation
	public String execute() {
//		VehicleTypeDAO daoobject = new VehicleTypeDAO();
//		this.setVehicleTypeList(daoobject.getVehicleTypeList());
		return "success";
	}
	public String createVehicleType() {
		return "success";
	}
	public int getIsNewVehicleType() {
		return isNewVehicleType;
	}
	public void setIsNewVehicleType(int isNewVehicleType) {
		this.isNewVehicleType = isNewVehicleType;
	}

	public String editVehicleType() {

		VehicleTypeDAO daoObject = new VehicleTypeDAO();
		String vehicleTypeId = ServletActionContext.getRequest().getParameter("editVehicleTypeId");
		Object vehicleTypeID = ServletActionContext.getRequest().getSession().getAttribute("vehicleTypeId");
		String vehicleId = (vehicleTypeId != null) ? vehicleTypeId : vehicleTypeID.toString();
		this.setVehicleDetails(daoObject.getEditVehicleDetails(vehicleId));
		return "success";
	}
	
	public String deleteVehicleType()
	{
		String status=""; 
		int vehicleTypeId =Integer.parseInt( ServletActionContext.getRequest().getParameter("vehicleTypeId"));
		 AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			HttpServletRequest request = ServletActionContext.getRequest();

			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleType.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(delete.equalsIgnoreCase("Y")){
		status=daoObject.deleteVehicleType(vehicleTypeId);
//		System.out.println("*************************"+status);
		if(status.split(":")[0].equals("success")){
			addActionMessage("Vehicle Type Id "+vehicleTypeId+" deleted successfully");		
			returnResult = "success";
		}
		if(status.equals("")){
			addActionMessage("Vehicle Type Id "+vehicleTypeId+" deleted successfully");
			returnResult = "success";
		}else{
			addActionError(status);
			returnResult = "success";
		}
			
	return returnResult;
			}else{
				addActionMessage("Access Denied - User Does Not Have Access Privileges");
				return "success";
			}
	}
		
	public String saveEditedVehilceTypeDetails()
	{     AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	HttpServletRequest request = ServletActionContext.getRequest();

	int usrsessionid=(Integer)request.getSession().getAttribute("userid");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleType.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(vehicleDetails.getVehicle_type_id(), "vehicle_type");
			System.out.println("status---"+status);
		if(status.equals("")  ||(!status.equals("") &&  vehicleDetails.getStatus().equals("ACTIVE"))){
		if(daoObject.isVehicleTypeNew(vehicleDetails,"update")){
			if(daoObject.saveEditVehicleTypeDetails(vehicleDetails))
			{
				addActionMessage("Vehicle Type Id "+vehicleDetails.getVehicle_type_id()+" updated successfully");
				returnResult="success";
			}else{
				addActionError("Error in Vehicle type updation ");
				returnResult = "fail";
			}
		}
		else{
			addFieldError("updatedVehicleTypeName", "Duplicate Vehicle Type");
			returnResult = "fail";
			//ServletActionContext.getRequest().getSession().setAttribute("vehicleTypeId",editedVehicleTypeId);
			//editVehicleType();
		}
		}else{
			if(vehicleDetails.getStatus().equals("INACTIVE"))
			{
				//setUpdatestaus("success");
			addActionError(status);
			return "input";
			}
			
		}
		return returnResult;
		}else{
			return "input";
		}
	}
	
	public String saveNewVehicleType()
	{
		 AccessRightsDao  accessrightdao=new AccessRightsDao();
			AccessRights accessrights=new AccessRights();
			HttpServletRequest request = ServletActionContext.getRequest();

			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleType.action");
			String access=accessrights.getACCESS();
			String create=accessrights.getCREATE();
			String edit=accessrights.getEDIT();
			String delete=accessrights.getDELETE();
			if(create.equalsIgnoreCase("Y")){
		if(daoObject.isVehicleTypeNew(vehicleDetails,"create")){
			if(daoObject.saveNewVehicleType(vehicleDetails))
			{
				addActionMessage("Vehicle Type Id "+ServletActionContext.getRequest().getAttribute("createdVehicleTypeId")+" created successfully");
				returnResult="success";
			}else{
				addActionError("Error in Vehicle type creation ");
				returnResult = "fail";
			}
		}
		else{
			addFieldError("newVehicleType", "Dupliacte Vehicle Type");
			returnResult = "fail";
		}
		return returnResult;}
			else{
				return "fail";
			}
	}
	
	public void validate()
	{
		if(isNewVehicleType!=0)
		{
			if(vehicleDetails.getVehicle_type_name()!=null&&!vehicleDetails.getVehicle_type_name().trim().equals(""))
			{
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(vehicleDetails.getVehicle_type_name().trim()).matches())){
					addFieldError("newVehicleType", "Invalid vehicle type name");
				}
			}
			else {
				addFieldError("newVehicleType", "Please enter vehicle type name");
			}
			/*if(vehicleDetails.getNotes()!=null && !vehicleDetails.getNotes().trim().equals("")){
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(vehicleDetails.getNotes().trim()).matches())){
					addFieldError("notes", "Notes Required");
				//}
			}*/
		}
		if(isEditVehicleType!=0)
		{
			if(vehicleDetails.getVehicle_type_name()!=null&&!vehicleDetails.getVehicle_type_name().trim().equals(""))
			{
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(vehicleDetails.getVehicle_type_name().trim()).matches())){
					addFieldError("updatedVehicleTypeName", "Invalid vehicle type name");
				}
			}
			else {
				addFieldError("updatedVehicleTypeName", "Please enter vehicle type name");
			}
			/*if(vehicleDetails.getNotes()!=null && !vehicleDetails.getNotes().trim().equals("")){
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(vehicleDetails.getNotes().trim()).matches())){
					addFieldError("notes", "Notes Required");
				//}
			}*/
			//ServletActionContext.getRequest().getSession().setAttribute("vehicleTypeId",editedVehicleTypeId);
			//editVehicleType();
		}
	}

//	@Override
//	public void prepare() throws Exception {
//		// TODO Auto-generated method stub
//		
//		getCaseTypeRecordsList();
//	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public String getVehicleTypeRecordsList() throws IOException{
		

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
		result = daoObject.getVehicleTypeData(amount, request,Integer.parseInt(sCol),sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}
}
