package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.Vehicle;

public class DeviceAllocationToVehicle extends ActionSupport {

	VehicleDAO daoObject = new VehicleDAO();
	private Vehicle vehicle;
	private Device device; 
	private int isDeviceAllocation;
	private List<Device_Type> deviceTypeList;
	private Device allocatedDeviceToVehicle;
	
	private List<Device> deviceList;

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getIsDeviceAllocation() {
		return isDeviceAllocation;
	}

	public void setIsDeviceAllocation(int isDeviceAllocation) {
		this.isDeviceAllocation = isDeviceAllocation;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public List<Device_Type> getDeviceTypeList() {
		return deviceTypeList;
	}

	public void setDeviceTypeList(List<Device_Type> deviceTypeList) {
		this.deviceTypeList = deviceTypeList;
	}


	public Device getAllocatedDeviceToVehicle() {
		return allocatedDeviceToVehicle;
	}

	public void setAllocatedDeviceToVehicle(Device allocatedDeviceToVehicle) {
		this.allocatedDeviceToVehicle = allocatedDeviceToVehicle;
	}
	public String execute(){
		
		String  vehicleid = ServletActionContext.getRequest().getParameter("vehicleId");
		Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("vtuVehicleID");
		
		String vehicleId = (vehicleid != null) ? vehicleid : vehicleID.toString();
		ServletActionContext.getRequest().getSession().removeAttribute("vtuVehicleID");
		
		this.setVehicle(daoObject.getParticularVehicleDetails(vehicleId));
		this.setDeviceTypeList(daoObject.getDeviceTypeList());
		
		return "success";
	}
	
	public String assignDevice()
	{
		String returnResult="fail";
		
		if(daoObject.assignDevice(vehicle,device)){
			if(device.getDevice_id()==-10){
				addActionMessage("Device released from vehicle id "+vehicle.getVehicleId()+" successfully.");
			}else{
				addActionMessage("Device assigned to vehicle id "+vehicle.getVehicleId()+" successfully.");
			}
			returnResult = "success";
		}
		else{

			if(device.getDevice_id()==-10){
				addActionError("Error in device release");
			}else{
				addActionError("Error in device allocation");
			}
			
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("vtuVehicleID", vehicle.getVehicleId());
			execute();
		}
		
		return returnResult;
	}
	public void validate()
	{
		if(isDeviceAllocation!=0)
		{
			if(device.getDevice().getDevice_type_id()==0){
				addFieldError("device.device.device_type_id", "Please Select Device Type");
			}
			if(device.getDevice_id()==0){
				addFieldError("device.device_id", "Please Select Device Serial Number");
				
			}
			if(hasErrors()){
				ServletActionContext.getRequest().getSession().setAttribute("vtuVehicleID", vehicle.getVehicleId());
				execute();
			}
		}
	}
	public void getDeviceNumbers(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
 		int devTypeid=Integer.parseInt(request.getParameter("devType"));
 		int vehicleId=Integer.parseInt(request.getParameter("vehicleId"));
 		
 		int devId=daoObject.currentAllocatedDevice(vehicleId,devTypeid);
		System.out.print("current allocated device "+devId);
 		List<Device> deviceList = daoObject.getAllDeviceList(devTypeid,vehicleId);
 		
 		String regionTypeAjaxString = "";
 		String optionForRelease = "";
 	    //regionTypeAjaxString += "<option value=0>Select</option>";
 	    for (int i = 0; i < deviceList.size(); i++) {
 	    	Device dev = deviceList.get(i);
 	    	if(devId==dev.getDevice_id()){
 	    		optionForRelease += "<option id='orgNA' value='-10'>NA</option>";
 	    		regionTypeAjaxString += "<option value='" + dev.getDevice_id() + "' selected id='org"+dev.getDevice_id()+"' >" + dev.getDevice_serial_number() + "</option>";
 	    	}else{
 	    		regionTypeAjaxString +="<option value='" + dev.getDevice_id() + "' id='org"+dev.getDevice_id()+"'>" + dev.getDevice_serial_number() + "</option>";
 	    	}
 	    }
 	   regionTypeAjaxString += optionForRelease;
 	   // regionTypeAjaxString += "-"+devId;
// 	    		"@<script>$('select>option[value='"+devId+"']').prop('selected', true);</script>";
 	    
// 	    System.out.println("devNumberAjaxString="+regionTypeAjaxString);
 	    HttpServletResponse response = ServletActionContext.getResponse();
 	    PrintWriter out;
 		try {
 			out = response.getWriter();
 			out.print(regionTypeAjaxString);
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
		
	}
	}
	
}
