package com.trimax.its.vehicle.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleFcRenewal;

public class FcRenewAction extends ActionSupport{

	Common common = new Common();
	VehicleDAO daoObject  = new VehicleDAO();
	
	private Vehicle vehicle;
	private VehicleFcRenewal vehicleFcRenew;
	private int fcRenewVehicleId;
	private String fcExpiryDate;
	
	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public VehicleFcRenewal getVehicleFcRenew() {
		return vehicleFcRenew;
	}


	public void setVehicleFcRenew(VehicleFcRenewal vehicleFcRenew) {
		this.vehicleFcRenew = vehicleFcRenew;
	}

	

	public int getFcRenewVehicleId() {
		return fcRenewVehicleId;
	}


	public void setFcRenewVehicleId(int fcRenewVehicleId) {
		this.fcRenewVehicleId = fcRenewVehicleId;
	}

	

	public String getFcExpiryDate() {
		return fcExpiryDate;
	}


	public void setFcExpiryDate(String fcExpiryDate) {
		this.fcExpiryDate = fcExpiryDate;
	}


	public String execute()
	{
		String  vehicleid = ServletActionContext.getRequest().getParameter("vehicleId");
		Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("fcRenewVehicleId");
		
		String vehicleId = (vehicleid != null) ? vehicleid : vehicleID.toString();
		ServletActionContext.getRequest().getSession().removeAttribute("fcRenewVehicleId");
		this.setVehicle(daoObject.getParticularVehicleDetails(vehicleId));
		
		
		this.setFcExpiryDate(common.getDateToDatePicker(vehicle.getFcExpiryDate()));
		return "success";
		
	}
	
	public String saveFcRenewalDetails()
	{
		
		String returnResult = "fail";
		
		if (daoObject.saveFCRenewalDetails(vehicleFcRenew,vehicle)) {
			addActionMessage("Vehicle Id "+vehicle.getVehicleId()+" FC Renewal Updated Successfully");
			returnResult = "success";
		} else {
			addActionError("Error in FC Renewal Updation");
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("fcRenewVehicleId",vehicle.getVehicleId());
			execute();
		}
		
		
		return returnResult;
	}
	
	public void validate()
	{
		if(fcRenewVehicleId!=0)
		{
			if(vehicleFcRenew.getFcExpiryDateString()==null||vehicleFcRenew.getFcExpiryDateString().trim().equals("")){
				addFieldError("vehicleFcRenew.fcExpiryDateString", "Please enter FC Expiry Date ");
			}
			if(vehicleFcRenew.getFcRenewDateString()==null||vehicleFcRenew.getFcRenewDateString().trim().equals("")){
				addFieldError("vehicleFcRenew.fcRenewDateString", "Please enter FC Renew Date ");
			}
			if(!(common.compareDates(vehicleFcRenew.getFcRenewDateString(),vehicleFcRenew.getFcExpiryDateString())>0)){
				addFieldError("vehicleFcRenew.fcRenewDateString", "FC Renew date should be greater than FC Expiry date ");
			}
			ServletActionContext.getRequest().getSession().setAttribute("fcRenewVehicleId",vehicle.getVehicleId());
			execute();
		}
	}
}
