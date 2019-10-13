package com.trimax.its.vehicle.action;

import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.VehicleCategoryDAO;
import com.trimax.its.vehicle.model.VehicleCategory;

public class VehicleCategoryAction extends ActionSupport {

	VehicleCategoryDAO daoObject = new VehicleCategoryDAO();
	
	private VehicleCategory vehicleCategory;
	
	private int isNewVehicleCategoryType;
	
	public int getIsNewVehicleCategoryType() {
		return isNewVehicleCategoryType;
	}

	public void setIsNewVehicleCategoryType(int isNewVehicleCategoryType) {
		this.isNewVehicleCategoryType = isNewVehicleCategoryType;
	}

	
	public VehicleCategory getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(VehicleCategory vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public String execute()
	{
		return "success";
	}
	
	public String createVehilceCategory()
	{
		
		return "success";
	}
	
	public String saveVehicleCategory(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleCategoryList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		String returnResult = "";
		if(daoObject.isVehicleCategoryNew(vehicleCategory, "create")){
			if(daoObject.saveVehicleCategoryType(vehicleCategory)){
				int createdId = (Integer) ServletActionContext.getRequest().getAttribute("createdVehicleCategoryId");
				addActionMessage("Vehicle category Id "+createdId+" created successfully");
				returnResult = "success";
			}
			else{
				addActionError("Error in Vehicle Category creation");
				returnResult = "fail";
			}
		}
		else{
			addFieldError("vehicleCategory.vehicleCategoryName","Duplicate Vehicle Category name");
			returnResult = "fail";
		}
		
		return returnResult;
		}else{
			return "fail";
		}
	}
	public String deleteVehicleCategory(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleCategoryList.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		String returnResult = "";
		int vehiclecatId = Integer.parseInt(ServletActionContext.getRequest().getParameter("VehCatid"));
		 DependencyChecker dc=new DependencyChecker();
	        String status=dc.getStatus(vehiclecatId,"vehicle_category");
	        System.out.println("status---"+status);
	       if(status.equals("")){
		if(daoObject.deleteVehilceCategory(vehiclecatId)){
			addActionMessage("Vehicle Category Id "+vehiclecatId+" deleted successfully");
			//returnResult = "success";
		}
		else{
			addActionError("Error in Vehicle Category deletion");
			//returnResult = "fail";
		}
	      }
	      else{
	                addActionError(status);
	                //returnResult = "success";
	            }
	   
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");

			return "success";
		}
	}
	public void validate()
	{
		if(isNewVehicleCategoryType!=0){
			if(!vehicleCategory.getVehicleCategoryName().trim().equals("")){
				
				if(!((Pattern.compile("[A-Za-z0-9 ]+")).matcher(vehicleCategory.getVehicleCategoryName()).matches())){
					addFieldError("vehicleCategory.vehicleCategoryName", "Invalid Vehicle Category type");
				}
			}else{
				addFieldError("vehicleCategory.vehicleCategoryName","Please enter Vehilce Category Name");
				vehicleCategory.setVehicleCategoryName(vehicleCategory.getVehicleCategoryName().trim());
			}
		}	
	}
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	public String getVehicleCategoryList()
	{	
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			Number cnt = daoObject.getTotalVehicleCategoryRecords();
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
			total = (Integer) cnt;
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			DIR = dir;
			START = start;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result = daoObject.getVehicleCategoryList(total,request,sCol,sdir,viewdeletedrecord);
			out.print(result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
