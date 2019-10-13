package com.trimax.its.vehicle.action;

import java.util.Currency;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.type.ComponentType;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.vehicle.dao.DockingVehicleDAO;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.DockingType;
import com.trimax.its.vehicle.model.Vehicle;

public class DockingAction extends ActionSupport {

	Common common = new Common();
	VehicleDAO vehicleDAO = new VehicleDAO();
	DockingVehicleDAO daoObject = new DockingVehicleDAO();
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	
	private String isDockingProcessed;
	
	private Vehicle vehicle;
	
	private DockingHistory dockingHistory;
	
	private DockingHistory dockingHisOfVehicle;
	
	private List  ComponenetsTypeList;
	
	private List dockingTypeList;
	
	private int startDocking;
	
	private int endDocking;
	
	private String dockingName;
	
	private int existedDockingTypeId;
	
	private List<DockingHistory> dockingHistoryList;
	
	private DockingHistory latestDockingObj;
	
	private String dockingEndDateString;
	
	private int noHistoryFound;
	

	public String getIsDockingProcessed() {
		return isDockingProcessed;
	}

	public void setIsDockingProcessed(String isDockingProcessed) {
		this.isDockingProcessed = isDockingProcessed;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public DockingHistory getDockingHistory() {
		return dockingHistory;
	}

	public void setDockingHistory(DockingHistory dockingHistory) {
		this.dockingHistory = dockingHistory;
	}

	public List getComponenetsTypeList() {
		return ComponenetsTypeList;
	}

	public void setComponenetsTypeList(List componenetsTypeList) {
		ComponenetsTypeList = componenetsTypeList;
	}

	public List getDockingTypeList() {
		return dockingTypeList;
	}

	public void setDockingTypeList(List dockingTypeList) {
		this.dockingTypeList = dockingTypeList;
	}

	public int getStartDocking() {
		return startDocking;
	}

	public void setStartDocking(int startDocking) {
		this.startDocking = startDocking;
	}

	public int getEndDocking() {
		return endDocking;
	}

	public void setEndDocking(int endDocking) {
		this.endDocking = endDocking;
	}

	public String getDockingName() {
		return dockingName;
	}

	public void setDockingName(String dockingName) {
		this.dockingName = dockingName;
	}

	public DockingHistory getDockingHisOfVehicle() {
		return dockingHisOfVehicle;
	}

	public void setDockingHisOfVehicle(DockingHistory dockingHisOfVehicle) {
		this.dockingHisOfVehicle = dockingHisOfVehicle;
	}

	public int getExistedDockingTypeId() {
		return existedDockingTypeId;
	}

	public void setExistedDockingTypeId(int existedDockingTypeId) {
		this.existedDockingTypeId = existedDockingTypeId;
	}

	public int getNoHistoryFound() {
		return noHistoryFound;
	}

	public void setNoHistoryFound(int noHistoryFound) {
		this.noHistoryFound = noHistoryFound;
	}

	public List<DockingHistory> getDockingHistoryList() {
		return dockingHistoryList;
	}

	public void setDockingHistoryList(List<DockingHistory> dockingHistoryList) {
		this.dockingHistoryList = dockingHistoryList;
	}

	public DockingHistory getLatestDockingObj() {
		return latestDockingObj;
	}

	public void setLatestDockingObj(DockingHistory latestDockingObj) {
		this.latestDockingObj = latestDockingObj;
	}

	public String getDockingEndDateString() {
		return dockingEndDateString;
	}

	public void setDockingEndDateString(String dockingEndDateString) {
		this.dockingEndDateString = dockingEndDateString;
	}

	public String execute()
	{
		String vehicleid = ServletActionContext.getRequest().getParameter("vehicleId");
		Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("dockingVehicleID");
		httpSession.removeAttribute("dockingVehicleID");
		String vehicleId = (vehicleid != null) ? vehicleid : vehicleID.toString();
		
		this.setDockingHistoryList(daoObject.getDockinDetails(Integer.parseInt(vehicleId)));
		
		this.setLatestDockingObj((DockingHistory) ServletActionContext.getRequest().getSession().getAttribute("latestDockingObject"));
		httpSession.removeAttribute("latestDockingObject");
		
		if(getLatestDockingObj()==null){
			noHistoryFound = 1;
		}
		httpSession.setAttribute("latestDockingObj",latestDockingObj);
		
		this.setVehicle(vehicleDAO.getParticularVehicleDetails(vehicleId));
		httpSession.setAttribute("vehicleObject",vehicle);
		
		this.setDockingHisOfVehicle(daoObject.isAlreadyDocked(vehicle));
		
		if(dockingHisOfVehicle!=null){
			this.setExistedDockingTypeId(dockingHisOfVehicle.getDockingType().getDocking_type_id());
		}
		
		this.setComponenetsTypeList(daoObject.getComponentsList());
		this.setDockingTypeList(daoObject.getVehicleDockingTypes());
		return "success";
	}
	
	public String startDockingVehicle()
	{
		String returnResult = "fail";
		
		Object latestDockingObjectFromSession = httpSession.getAttribute("latestDockingObj");
		httpSession.removeAttribute("latestDockingObj");
		
		Vehicle vehicleObject = (Vehicle) httpSession.getAttribute("vehicleObject");
		httpSession.removeAttribute("vehicleObject");
		
		setLatestDockingObj(latestDockingObjectFromSession!=null?(DockingHistory)latestDockingObjectFromSession:null);
		httpSession.setAttribute("dockingVehicleID", vehicle.getVehicleId());
		if(daoObject.isValidForDocking(dockingHistory,vehicleObject,latestDockingObj)){
			if(daoObject.saveDockingVehicle(dockingHistory,vehicleObject,latestDockingObj))
			{
				addActionMessage("Vehicle Id "+vehicle.getVehicleId()+" Docking Created Successfully");
				returnResult = "success";
			}else{
				addActionError("Error in docking vehicle");
				returnResult = "fail";
				this.setStartDocking(1);
			}
		}else{
			addFieldError("dockingHistory.dockingType.docking_type","Inavlid Docking type");
			returnResult = "fail";
			this.setStartDocking(1);
		}
		
/*//		DockingHistory prevDockingHistory=daoObject.isAlreadyDocked(vehicle);
		List<DockingType> dockingTypeMasterdata = daoObject.getDockingTypeRecords();
		dockingHistory.getDockingType().setDocking_type(getDockingName());
		String currDockType = getDockingName();
		
		
		if(prevDockingHistory!=null){
			String prevDockType = prevDockingHistory.getDockingType().getDocking_type();
						
//			if(prevDockType.equalsIgnoreCase(currDockType)){
//				addFieldError("dockingHistory.dockingType.docking_type", "Vehicle already in docking type "+prevDockType+"");
//				returnResult = "fail";
//				isSucccess = false;
//			}
			if((prevDockType.equals("D1")&& currDockType.equals("D2"))||(prevDockType.equals("D2")&& currDockType.equals("D3"))||(prevDockType.equals("D3")&& currDockType.equals("D4"))||(prevDockType.equals("D4")&& currDockType.equals("D1"))){
				if(daoObject.saveDockingVehicle(dockingHistory,vehicle,"",dockingTypeMasterdata))
				isSucccess = true;
			}
			else {
				if(daoObject.saveDockingVehicle(dockingHistory,vehicle,"update",dockingTypeMasterdata))
				isSucccess = true;
			}
		}
		else{
			if(daoObject.saveDockingVehicle(dockingHistory,vehicle,"update",dockingTypeMasterdata))
			isSucccess = true;
		}
			
		if(isSucccess){
			addActionMessage("Vehicle Id "+vehicle.getVehicleId()+" Docking Created Successfully");
			returnResult = "success";
		}
		else{
			if(!hasFieldErrors())addActionError("Error in Docking Creation");
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("dockingVehicleID", vehicle.getVehicleId());
			execute();
		}*/
		
		return returnResult;
	}
	
	public String endDockingVehicle(){
	
		String returnresult;
		
		if(daoObject.endDocking(latestDockingObj.getDocking_id(),dockingEndDateString)){
			addActionMessage("Vehicle Id "+latestDockingObj.getVehicle().getVehicleId()+" Docking Finished Successfully");
			returnresult = "success";
		}else{
			addActionError("Vehicle Id "+latestDockingObj.getVehicle().getVehicleId()+" Docking Finished Successfully");
			returnresult = "fail";
			this.setEndDocking(1);
		}
		return returnresult;
	}
	
	public void validate()
	{
		if(startDocking!=0)
		{
			DockingHistory prevDockingObject = (DockingHistory)httpSession.getAttribute("latestDockingObj");
			if(dockingHistory.getStartDateString()==null ||dockingHistory.getStartDateString().trim().equals(""))
			{
				addFieldError("dockingHistory.startDateString", "Please Enter Valid Start Date/Time");
			}else if(prevDockingObject!=null && (common.compareDates(common.changeDataFormat(dockingHistory.getStartDateString(), "dd MMM yyyy - HH:mm", "dd-MM-yyyy - HH:mm"),prevDockingObject.getEndDateString(),"dd-MM-yyyy - HH:mm")<=0)){
				addFieldError("dockingHistory.startDateString", "Start Date/Time should be greater than previous docking End Date/Time");
			}
			/*else if(!common.isGreaterThanCurrentDateTime(dockingHistory.getStartDateString())){
				addFieldError("dockingHistory.startDateString", "Start Date/Time should be greater than current date");
			}*/
//			if(dockingHistory.getEndDateString()==null ||dockingHistory.getEndDateString().trim().equals(""))
//			{
//				addFieldError("dockingHistory.endDateString", "Please enter End Date/Time");
//			}
			/*this.setDockingHisOfVehicle(daoObject.isAlreadyDocked(vehicle));
			if(this.getDockingHisOfVehicle()!=null){
				dockingHisOfVehicle.setEndDateString(common.getDateTimeToDatePicker(dockingHisOfVehicle.getEndTime()));
			if(!(common.compareDatesTime(dockingHistory.getStartDateString(),dockingHisOfVehicle.getEndDateString())>0)){
				addFieldError("dockingHistory.startDateString", "Start Date/Time should be greater than Previous Docking End Date/Time ");
			}
			}*/
//			if(!(common.compareDatesTime(dockingHistory.getEndDateString(),dockingHistory.getStartDateString())>0)){
//				addFieldError("dockingHistory.endDateString", "End Date/Time should be greater than Start Date/Time ");
//			}
			
			if(dockingHistory.getDockingType().getDocking_type_id()==0){
				addFieldError("dockingHistory.dockingType.docking_type", "Please select Docking type");
			}
			if(dockingHistory.getDocking_batch_name()!=null&&!dockingHistory.getDocking_batch_name().equals("")){
				if(!dockingHistory.getDocking_batch_name().trim().matches("^[a-zA-Z0-9- ]*$")){
					addFieldError("dockingHistory.docking_batch_name", "Special Characters not allowed");
				}
			}
			if(hasErrors()){
				ServletActionContext.getRequest().getSession().setAttribute("dockingVehicleID", getVehicle().getVehicleId());
				execute();
			}
		}
		
		else if(endDocking!=0){
			setLatestDockingObj((DockingHistory) ServletActionContext.getRequest().getAttribute("latestDockingObj"));
			if(dockingEndDateString.equals("")){
				addFieldError("dockingEndDateString", "Please Enter Valid Docking End Date/Time");
			}
			else if(common.compareDates( dockingEndDateString,common.changeDataFormat(latestDockingObj.getStartDateString(), "dd-MM-yyyy - hh:mm", "dd MMM yyyy - HH:mm"), "dd MMM yyyy - HH:mm")<=0){
				addFieldError("dockingEndDateString", "End Date/Time should be greater than Start Date/Time");
			}
			if(hasErrors()){
				ServletActionContext.getRequest().getSession().setAttribute("dockingVehicleID", latestDockingObj.getVehicle().getVehicleId());
				execute();
			}
		}
		
		
		
	}
}




/*public String saveDockingVehicle()
{
	Common common = new Common();
	String returnResult = "fail";
	boolean isSucccess = false;
	
	DockingHistory prevDockingHistory=daoObject.isAlreadyDocked(vehicle);
	String currDockType = getDockingName();
	if(prevDockingHistory!=null){
		String prevDockType = prevDockingHistory.getDockingType().getDocking_type();
					
		if(prevDockType.equalsIgnoreCase(currDockType)){
			addFieldError("dockingHistory.dockingType.docking_type_id", "Vehicle already in docking type "+prevDockType+"");
			returnResult = "fail";
			isSucccess = false;
		}
		else if(prevDockType.equals("D1") &&(currDockType.equals("D2")||currDockType.equals("D3")||currDockType.equals("D4"))){
			isSucccess = true;
		}
		else if(prevDockType.equals("D2") &&(currDockType.equals("D3")||currDockType.equals("D4"))){
			isSucccess = true;
		}
		else if(prevDockType.equals("D3") &&(currDockType.equals("D4"))){
			isSucccess = true;
		}
		else if(prevDockType.equals("D4") &&(currDockType.equals("D1"))){
			isSucccess = true;
		}
		else{
			addFieldError("dockingHistory.dockingType.docking_type_id", "Can't assign docking type from "+prevDockType+" to "+currDockType+"");
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("dockingVehicleID", vehicle.getVehicleId());
			execute();
			isSucccess = false;
		}
		else if(prevDockType!=4 && prevDockType>currDockType){
			addFieldError("dockingHistory.dockingType.docking_type_id1", "Could not assign docking type from "+prevDockingHistory.getDockingType().getDocking_type()+" ");
			returnResult = "fail";
			isSuvccess = false;
		}
		else if(prevDockType==4 && currDockType!=1){
			addFieldError("dockingHistory.dockingType.docking_type_id1", "Could not assign docking type from "+prevDockingHistory.getDockingType().getDocking_type()+" ");
			returnResult = "fail";
			isSuvccess = false;
		}else{
			
			isSuvccess = true;
		}
	}
	else{
		isSucccess = true;
	}
		
	if(isSucccess){
		if (daoObject.saveDockingVehicle(dockingHistory,vehicle)) {
	
			addActionMessage("Vehicle Id "+vehicle.getVehicleId()+" Docking Created Successfully");
			returnResult = "success";
		}

		else {
			addActionError("Error in Docking Creation");
			returnResult = "fail";
			ServletActionContext.getRequest().getSession().setAttribute("dockingVehicleID", vehicle.getVehicleId());
			execute();
		}
	}
	else{
		ServletActionContext.getRequest().getSession().setAttribute("dockingVehicleID", vehicle.getVehicleId());
		execute();
	}
	
	
	

	return returnResult;
}*/
