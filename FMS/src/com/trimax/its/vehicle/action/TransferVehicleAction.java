package com.trimax.its.vehicle.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.vehicle.dao.TransferVehicleDAO;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.TransferVehicleHistory;
import com.trimax.its.vehicle.model.Vehicle;

public class TransferVehicleAction extends ActionSupport {

	TransferVehicleDAO daoObject = new TransferVehicleDAO();
	Common common = new Common();
	private List depotList;
	private Vehicle vehicle;
	private TransferVehicleHistory transferHistory;
	private int transfervehicleId;

	private String transferedType;

	public List getDepotList() {
		return depotList;
	}

	public void setDepotList(List depotList) {
		this.depotList = depotList;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public TransferVehicleHistory getTransferHistory() {
		return transferHistory;
	}

	public void setTransferHistory(TransferVehicleHistory transferHistory) {
		this.transferHistory = transferHistory;
	}

	public int getTransfervehicleId() {
		return transfervehicleId;
	}

	public void setTransfervehicleId(int transfervehicleId) {
		this.transfervehicleId = transfervehicleId;
	}

	public String getTransferedType() {
		return transferedType;
	}

	public void setTransferedType(String transferedType) {
		this.transferedType = transferedType;
	}

	public String execute() {
		String vehicleid = ServletActionContext.getRequest().getParameter("vehicleId");
		Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("transferVehicleId");

		String vehicleId = (vehicleid != null) ? vehicleid : vehicleID.toString();
		ServletActionContext.getRequest().getSession().removeAttribute("transferVehicleId");
		this.setDepotList(daoObject.getDepotNames());
		this.setVehicle(daoObject.getParticularVehicleDetails(vehicleId));
		this.setTransferedType(ServletActionContext.getRequest().getAttribute("transferTpe").toString());
		ServletActionContext.getRequest().removeAttribute("transferTpe");

		return "success";
	}

	public String saveTransferVehicleDetails() {
		String returnResult = "";
		boolean dltFlag;
		VehicleDAO vdao=new VehicleDAO();
		if (daoObject.saveTransferVehicle(transferHistory, vehicle)) {
			System.out.println("transfer veh");
			dltFlag = vdao.deleteScheduleMappingForVehicle(vehicle.getVehicleId());
			System.out.println("after dlt anf transfer");
			addActionMessage("Vehicle Id " + vehicle.getVehicleId()+ " Transfered Successfully");
			returnResult = "success";
		} else {
			addActionError("Error in  Transfering Vehicle");
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("transferVehicleId", vehicle.getVehicleId());
			execute();
		}
		return returnResult;
	}

	public void validate() {
		
		if (transfervehicleId != 0) {
			if (transferHistory.getFromDateString() == null || transferHistory.getFromDateString().equals("")) {
				addFieldError("transferHistory.fromDateString","Please Enter From date");
			}
			if (transferHistory.getToDateString() != null && !transferHistory.getToDateString().equals("")) {
			
			 if(!(common.compareDates(transferHistory.getToDateString(),transferHistory.getFromDateString())>0)){
				addFieldError("transferHistory.toDateString", "Till date should be greater than from date");
			 }
			}
			if(transferHistory.getFromOrganisationId().getOrg_chart_id()!=0&&transferHistory.getToOrganisationId().getOrg_chart_id()!=0)
			{
				if(transferHistory.getFromOrganisationId().getOrg_chart_id()==transferHistory.getToOrganisationId().getOrg_chart_id()){
					addFieldError("transferHistory.toOrganisationId.toOrganisationId", "To unit should not same as from unit ");
				}
			}
			if(hasFieldErrors()){
				ServletActionContext.getRequest().getSession().setAttribute("transferVehicleId", vehicle.getVehicleId());
				execute();
			}
		}
	}

}
