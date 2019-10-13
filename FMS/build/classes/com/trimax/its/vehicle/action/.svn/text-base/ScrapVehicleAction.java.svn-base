package com.trimax.its.vehicle.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleScrap;

public class ScrapVehicleAction extends ActionSupport {

	VehicleDAO daoObject = new VehicleDAO();
	private VehicleScrap vehicleScrap ;
	private Vehicle vehicle;
	private int editedScrappedVehicleId;
	private String scrapdate;
	


	public String getScrapdate() {
		return scrapdate;
	}

	public void setScrapdate(String scrapdate) {
		this.scrapdate = scrapdate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public VehicleScrap getVehicleScrap() {
		return vehicleScrap;
	}

	public void setVehicleScrap(VehicleScrap vehicleScrap) {
		this.vehicleScrap = vehicleScrap;
	}

	
	

	public int getEditedScrappedVehicleId() {
		return editedScrappedVehicleId;
	}

	public void setEditedScrappedVehicleId(int editedScrappedVehicleId) {
		this.editedScrappedVehicleId = editedScrappedVehicleId;
	}

	public String execute()
	{
		System.out.println("inside-------------");
	
		try {
			
		String  vehicleid = ServletActionContext.getRequest().getParameter("vehicleId");
		Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("scrapVehicleID");
		
		String vehicleId = (vehicleid != null) ? vehicleid : vehicleID.toString();
		ServletActionContext.getRequest().getSession().removeAttribute("scrapVehicleID");
		
		this.setVehicle(daoObject.getParticularVehicleDetails(vehicleId));
//		System.out.println(this.getVehicle());
		
		}catch (Exception e) {
		e.printStackTrace();
		}
		
		return "success";
	}
	
	public String scrapVehicle()
	{
		String returnResult = "fail";
		boolean schFlag;
		VehicleDAO vdaobject=new VehicleDAO();
		if(daoObject.isnewscrap(vehicle.getVehicleId())) {
			
			if(daoObject.saveScrapVehicle(vehicle,vehicleScrap,scrapdate)){
				schFlag=vdaobject.deleteScheduleMappingForVehicle(vehicle.getVehicleId());
				addActionMessage("Vehicle Id "+vehicle.getVehicleId()+" Scrapped successfully ");
				returnResult = "success";
			}else{
				addActionError("Error in scrap creation");
				returnResult = "fail";
				ServletActionContext.getRequest().getSession().setAttribute("scrapVehicleID", vehicle.getVehicleId());
				execute();
			}
		}else{
			addActionError("its already in scrap");
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("scrapVehicleID", vehicle.getVehicleId());
			execute();
}
		return returnResult; 
	}
	
	public void validate()
	{
		System.out.println("scrap---------"+scrapdate);
		
		if(editedScrappedVehicleId!=0){
			if(vehicleScrap.getReason().trim().equals("")){
				addFieldError("vehicleScrap.reason", "Please specify Reason ");
			}
			if(scrapdate==null || scrapdate.equals("")) {
				addFieldError("scrapdate", "Please Pick Scrap Date");
		
			}
		}
		
		if(hasErrors())
		{
			ServletActionContext.getRequest().getSession().setAttribute("scrapVehicleID", vehicle.getVehicleId());
			execute();
		}
	}
}
