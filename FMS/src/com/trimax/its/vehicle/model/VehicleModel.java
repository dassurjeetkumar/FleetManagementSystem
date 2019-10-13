package com.trimax.its.vehicle.model;

import java.util.List;

/**
 * @author root
 *
 */
public class VehicleModel {

	private String vehicleId;
	private String vehicleRegistrationNumber;
	private String depotOrUnitName;
	private String fcExpiryDate;
	private String progressingKMs;
	private String vehicleType;
	private String brandType;
	private String acAvailability;
	private String make;
	private String model;
	private String proceduredDate;
	private String operationalDate;
	private String vehicleUsageCategory;
	private String registrationDate;
	private String bodyType;
	private String wheelsCount;
	private String chasisNumber;
	private String status;;
	private String isScarpped;
	private String scrappedDate;
	
	private String dockingPlanningDate;
	private String underWarranty;
	private String kmpl;
	
	//////////  details for transfer History 
	private String transferType;
	private List transferTypesList;
	
	private String dockingType;
	private int dockinTypeId;
	
	private String fuelFilledUser;
	private int fuelFilledUserId;
	
	public String getVehicleId() {
		return vehicleId;
	}



	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}



	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}



	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}



	public String getDepotOrUnitName() {
		return depotOrUnitName;
	}



	public void setDepotOrUnitName(String depotOrUnitName) {
		this.depotOrUnitName = depotOrUnitName;
	}



	public String getFcExpiryDate() {
		return fcExpiryDate;
	}



	public void setFcExpiryDate(String fcExpiryDate) {
		this.fcExpiryDate = fcExpiryDate;
	}



	public String getProgressingKMs() {
		return progressingKMs;
	}



	public void setProgressingKMs(String progressingKMs) {
		this.progressingKMs = progressingKMs;
	}



	public String getVehicleType() {
		return vehicleType;
	}



	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}



	public String getBrandType() {
		return brandType;
	}



	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}



	public String getAcAvailability() {
		return acAvailability;
	}



	public void setAcAvailability(String acAvailability) {
		this.acAvailability = acAvailability;
	}



	public String getMake() {
		return make;
	}



	public void setMake(String make) {
		this.make = make;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getProceduredDate() {
		return proceduredDate;
	}



	public void setProceduredDate(String proceduredDate) {
		this.proceduredDate = proceduredDate;
	}



	public String getOperationalDate() {
		return operationalDate;
	}



	public void setOperationalDate(String operationalDate) {
		this.operationalDate = operationalDate;
	}



	public String getVehicleUsageCategory() {
		return vehicleUsageCategory;
	}



	public void setVehicleUsageCategory(String vehicleUsageCategory) {
		this.vehicleUsageCategory = vehicleUsageCategory;
	}



	public String getRegistrationDate() {
		return registrationDate;
	}



	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}



	public String getBodyType() {
		return bodyType;
	}



	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}



	public String getWheelsCount() {
		return wheelsCount;
	}



	public void setWheelsCount(String wheelsCount) {
		this.wheelsCount = wheelsCount;
	}



	public String getChasisNumber() {
		return chasisNumber;
	}



	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getIsScarpped() {
		return isScarpped;
	}



	public void setIsScarpped(String isScarpped) {
		this.isScarpped = isScarpped;
	}



	public String getScrappedDate() {
		return scrappedDate;
	}



	public void setScrappedDate(String scrappedDate) {
		this.scrappedDate = scrappedDate;
	}



	public List<VehicleModel> getVehicleList() {
		return vehicleList;
	}



	public void setVehicleList(List<VehicleModel> vehicleList) {
		this.vehicleList = vehicleList;
	}

	


	public String getDockingPlanningDate() {
		return dockingPlanningDate;
	}



	public void setDockingPlanningDate(String dockingPlanningDate) {
		this.dockingPlanningDate = dockingPlanningDate;
	}



	public String getUnderWarranty() {
		return underWarranty;
	}



	public void setUnderWarranty(String underWarranty) {
		this.underWarranty = underWarranty;
	}



	public String getKmpl() {
		return kmpl;
	}



	public void setKmpl(String kmpl) {
		this.kmpl = kmpl;
	}


	
	

	public String getTransferType() {
		return transferType;
	}



	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}



	public List getTransferTypesList() {
		return transferTypesList;
	}



	public void setTransferTypesList(List transferTypesList) {
		this.transferTypesList = transferTypesList;
	}





	public String getDockingType() {
		return dockingType;
	}



	public void setDockingType(String dockingType) {
		this.dockingType = dockingType;
	}

	


	public int getDockinTypeId() {
		return dockinTypeId;
	}



	public void setDockinTypeId(int dockinTypeId) {
		this.dockinTypeId = dockinTypeId;
	}



	public String getFuelFilledUser() {
		return fuelFilledUser;
	}



	public void setFuelFilledUser(String fuelFilledUser) {
		this.fuelFilledUser = fuelFilledUser;
	}










	public int getFuelFilledUserId() {
		return fuelFilledUserId;
	}



	public void setFuelFilledUserId(int fuelFilledUserId) {
		this.fuelFilledUserId = fuelFilledUserId;
	}










	private List<VehicleModel> vehicleList;  
	
}
