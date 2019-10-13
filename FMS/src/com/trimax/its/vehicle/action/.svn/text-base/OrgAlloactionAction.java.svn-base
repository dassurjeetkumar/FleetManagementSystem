package com.trimax.its.vehicle.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.Vehicle;

public class OrgAlloactionAction extends ActionSupport{


	
	private int IsEditedVehicle;
	private int orgEditedVehicleId;
	private int unitType;
	private int orgChartId;
	VehicleDAO daoObject = new VehicleDAO();
	private List orgTypeList;

	private Vehicle vehicleOrg;
	public List getOrgTypeList() {
		return orgTypeList;
	}

	public void setOrgTypeList(List orgTypeList) {
		this.orgTypeList = orgTypeList;
	}
	
	

	public int getOrgChartId() {
		return orgChartId;
	}

	public void setOrgChartId(int orgChartId) {
		this.orgChartId = orgChartId;
	}



	public int getOrgEditedVehicleId() {
		return orgEditedVehicleId;
	}

	public void setOrgEditedVehicleId(int orgEditedVehicleId) {
		this.orgEditedVehicleId = orgEditedVehicleId;
	}

	public int getIsEditedVehicle() {
		return IsEditedVehicle;
	}

	public void setIsEditedVehicle(int isEditedVehicle) {
		IsEditedVehicle = isEditedVehicle;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	
	public Vehicle getVehicleOrg() {
		return vehicleOrg;
	}

	public void setVehicleOrg(Vehicle vehicleOrg) {
		this.vehicleOrg = vehicleOrg;
	}

	public String execute() {
		String vehicleid = ServletActionContext.getRequest().getParameter("vehicleId");
		Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("vehicle_id");
		
		orgEditedVehicleId = Integer.parseInt((vehicleid != null) ? vehicleid : vehicleID.toString());	
		setVehicleOrg(daoObject.getParticularVehicleDetails(""+orgEditedVehicleId));
		getList();
		return "success";
	}
	public void getList()
	{
		this.setOrgTypeList(daoObject.getOrgTypes());
	}
	public String AllocateOrgToVehicle()
	{
		String returnResult = "";
		
//		vehicleOrg = new Vehicle();
		vehicleOrg.setVehicleId(orgEditedVehicleId);
		
		/*OrganisationChart orgChart = new OrganisationChart();
		orgChart.setOrg_chart_id(orgChartId);
		vehicleOrg.setDepotOrUnit(orgChart);*/
		if(daoObject.saveOrganisationOfVehicle(vehicleOrg)){
			addActionMessage("Organization is assigned to vehicle id "+vehicleOrg.getVehicleId()+" successfully");
			returnResult = "success";
		}else{
			addActionError("Error in assign Organization");
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("vehicle_id",orgEditedVehicleId);
			execute();
		}
		return returnResult;
	}
	
	public void validate()
	{
		if(IsEditedVehicle!=0){
			if(vehicleOrg.getDepotOrUnit().getOrgType().getOrg_type_id()==0){
				addFieldError("vehicleOrg.depotOrUnit.orgType", "Please Select Organization Type");
			}
			if(vehicleOrg.getDepotOrUnit().getOrg_chart_id()==0){
				addFieldError("vehicleOrg.depotOrUnit.org_chart_id", "Please Select Organization Unit Name");
			}
			if(hasErrors()){
				ServletActionContext.getRequest().getSession().setAttribute("vehicle_id",orgEditedVehicleId);
				getList();
			}
		}
	}

}
