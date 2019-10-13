package com.trimax.its.device.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.dao.DeviceTypeDao;
import com.trimax.its.device.model.Battery;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.DeviceHistory;
import com.trimax.its.device.model.Device_Type;
import com.trimax.its.device.model.Simcard;
import com.trimax.its.model.Vendor;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.dao.UserGroupDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.OrganisationChart;
/**
 * @author root
 *
 */
public class DeviceAction extends ActionSupport {
	
	Device device;
	Simcard simcard;
	OrganisationChart orgChart;
	Battery battery;
	String tabstatus;
	String insertstatus;
	String deletestatus;
	String releasestatus;
	Common common = new Common();
	DeviceDao dao = new DeviceDao();
	HttpServletRequest request = ServletActionContext.getRequest();
	
	public int isCreateDevice;
	public int isEditDevice;
	public int isSimCardAllocation;
	public int isOrgAllocation;
	public int isBatteryAllocation;
	public int isListView; 
	private List<Simcard> simcardlist;
	private Simcard assignedSimCard;
	private List<OrganisationChart> orglist;
	private OrganisationChart assignedOrg;
	private List<Battery> batteryList;
	private Battery assignedBattery;
	DeviceHistory divhistory;
	private static int previousDeviceType;
	
	public String getReleasestatus() {
		return releasestatus;
	}

	public void setReleasestatus(String releasestatus) {
		this.releasestatus = releasestatus;
	}

	public String getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(String deletestatus) {
		this.deletestatus = deletestatus;
	}

	public OrganisationChart getAssignedOrg() {
		return assignedOrg;
	}

	public void setAssignedOrg(OrganisationChart assignedOrg) {
		this.assignedOrg = assignedOrg;
	}


	private List<Vendor> vendorList;
	private List<Device_Type> deviceTypeList;
	private List<ModelType> modelTypeList;

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	public List<Device_Type> getDeviceTypeList() {
		return deviceTypeList;
	}

	public void setDeviceTypeList(List<Device_Type> deviceTypeList) {
		this.deviceTypeList = deviceTypeList;
	}

	public List<ModelType> getModelTypeList() {
		return modelTypeList;
	}

	public void setModelTypeList(List<ModelType> modelTypeList) {
		this.modelTypeList = modelTypeList;
	}

	public Simcard getSimcard() {
		return simcard;
	}

	public void setSimcard(Simcard simcard) {
		this.simcard = simcard;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getTabstatus() {
		return tabstatus;
	}

	public void setTabstatus(String tabstatus) {
		this.tabstatus = tabstatus;
	}

	public List<Simcard> getSimcardlist() {
		return simcardlist;
	}

	public void setSimcardlist(List<Simcard> simcardlist) {
		this.simcardlist = simcardlist;
	}

	public List<OrganisationChart> getOrglist() {
		return orglist;
	}

	public void setOrglist(List<OrganisationChart> orglist) {
		this.orglist = orglist;
	}

	public OrganisationChart getOrgChart() {
		return orgChart;
	}

	public void setOrgChart(OrganisationChart orgChart) {
		this.orgChart = orgChart;
	}

	public Battery getBattery() {
		return battery;
	}

	public List<Battery> getBatteryList() {
		return batteryList;
	}

	public void setBattery(Battery battery) {
		this.battery = battery;
	}

	public void setBatteryList(List<Battery> batteryList) {
		this.batteryList = batteryList;
	}

	@SkipValidation
	public String showDevice() {
		return "success";
	}

	public String getInsertstatus() {
		return insertstatus;
	}

	public void setInsertstatus(String insertstatus) {
		this.insertstatus = insertstatus;
	}

	public Simcard getAssignedSimCard() {
		return assignedSimCard;
	}

	public void setAssignedSimCard(Simcard assignedSimCard) {
		this.assignedSimCard = assignedSimCard;
	}

	public int getIsCreateDevice() {
		return isCreateDevice;
	}

	public void setIsCreateDevice(int isCreateDevice) {
		this.isCreateDevice = isCreateDevice;
	}

	public int getIsEditDevice() {
		return isEditDevice;
	}

	public void setIsEditDevice(int isEditDevice) {
		this.isEditDevice = isEditDevice;
	}

	public int getIsSimCardAllocation() {
		return isSimCardAllocation;
	}

	public void setIsSimCardAllocation(int isSimCardAllocation) {
		this.isSimCardAllocation = isSimCardAllocation;
	}

	public int getIsOrgAllocation() {
		return isOrgAllocation;
	}

	public void setIsOrgAllocation(int isOrgAllocation) {
		this.isOrgAllocation = isOrgAllocation;
	}

	public int getIsBatteryAllocation() {
		return isBatteryAllocation;
	}

	public void setIsBatteryAllocation(int isBatteryAllocation) {
		this.isBatteryAllocation = isBatteryAllocation;
	}

	public Battery getAssignedBattery() {
		return assignedBattery;
	}

	public void setAssignedBattery(Battery assignedBattery) {
		this.assignedBattery = assignedBattery;
	}

	public int getIsListView() {
		return isListView;
	}

	public void setIsListView(int isListView) {
		this.isListView = isListView;
	}

	public int getPreviousDeviceType() {
		return previousDeviceType;
	}

	public void setPreviousDeviceType(int previousDeviceType) {
		this.previousDeviceType = previousDeviceType;
	}

	public String execute() throws IOException {
		setIsListView(1);
		return null;
	}

	public String createDevice() {
		getListForCreation();
		return "success";
		
	}
	public void getVendorName() {
		setVendorList(dao.getVendorName());
		System.out.println(getVendorList().size()+"+++++++++++++++++++++++");

	}
	public void getListForCreation(){
		//setVendorList(dao.getVendorName());
		setDeviceTypeList(dao.getDeviceTypeNames());
		setModelTypeList(dao.getModelName());
	}
	
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	public void getDeviceList(){
	
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			DeviceDao devicedao = new DeviceDao();
			String[] cols = { "", "device_id", "device_serial_number","device.device_type_name", "model.model_type_name", "status","vendor.vendor_name", "purchase_date", "notes" };
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

			String colName = cols[col];
			int total = -1;
/*			total = devicedao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);*/
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = devicedao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

	}

	@SkipValidation
	public String addDevice() {
		return "success";
	}

	public String addDeviceAction() {
		String returnResult = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"showdevice.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		int hisid = 0;
		if (dao.checkDeviceForUpdate(device,"create")) {
			if(dao.addDevice(device)){
				int createdDeviceId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("createdDeviceId"); 
				hisid = dao.addDeviceHistory(divhistory,createdDeviceId, request);
				dao.updateDeviceidHistory(divhistory, createdDeviceId, request, hisid);
				addActionMessage("Device Id " + createdDeviceId+ " created Successfully");
				returnResult = "success";
			}else {
				addActionError("Problem occured in Device creation");
				getListForCreation();
				returnResult ="input";
				//getListForCreation();
			}
		}else{
			addFieldError("serialno","Duplicate Serial Number");
			getListForCreation();
			returnResult ="input";
			//getListForCreation();

		}
		return returnResult;
		}else{
			return "input";
		}
	}
	public void getAllValues(int deviceId){
		
		simcardlist = dao.getSimcard();
		orglist = dao.getorganization();
		batteryList = dao.getBatteryList();
//		setVendorList(dao.getVendorName(deviceId));
		setDeviceTypeList(dao.getDeviceTypeNames());
		setModelTypeList(dao.getModelName());

		if(dao.isDeviceTypeCanConatinSimCardAndBattery(device.getDevice_id())){
			request.getSession().setAttribute("tabstat", "ALL");
			setTabstatus("ALL");
		}
		else{
			request.getSession().setAttribute("tabstat", "Partial");
		}
	}
	public void getvendoredit(String devicetype) {
		System.out.println("devicetype-------------------------------------"+devicetype);
		setVendorList(dao.getVendorName(devicetype));

	}
	public void getAssignedValues(int deviceId)
	{
		this.setAssignedSimCard(dao.assignedSimCard(deviceId));
		this.setAssignedOrg(dao.assignedOrg(deviceId));
		this.setAssignedBattery(dao.assignedBattery(deviceId));
		if (assignedSimCard!=null) {
			Simcard card = new Simcard();
			card.setSimcard_id(-10);
			card.setPhone_number("NA");
			simcardlist.add(card);
		}
		if(assignedBattery!=null){
			Battery batteryNA = new Battery();
			batteryNA.setBattery_id(-10);
			batteryNA.setSerial_number("NA");
			batteryList.add(batteryNA);
		}
		if(assignedOrg!=null){
			OrganisationChart orgChartNA = new OrganisationChart();
			orgChartNA.setOrg_chart_id(-10);
			orgChartNA.setOrg_name("NA");
			orglist.add(orgChartNA);
		}
		if(	dao.isDeviceTypeCanConatinSimCardAndBattery(device.getDevice_id())){
			request.getSession().setAttribute("tabstat", "ALL");
			setTabstatus("ALL"); 
		}
	}

	public void validate() {
		
		CommonValidation commvalidate = new CommonValidation();
		
		if(getIsEditDevice() == 1|| getIsCreateDevice() == 1){
			if (device.getDevice_serial_number().equals("")) {
				addFieldError("serialno", "Please Enter Device Serial No");
			}
			if (device.getDevice_serial_number().trim().length()<7) {
				addFieldError("serialno", "Device Serial No Must More Than 7 Digits");
			}
			if (device.getDevice().getDevice_type_id() == 1 && !commvalidate.isNumber(device.getDevice_serial_number())) {
				addFieldError("serialno", "VTU device Number Must be Numeric");
			}
			
			if (!commvalidate.isSpecialChar(device.getDevice_serial_number())) {
				if (device.getDevice_id() != 0) {
					device = dao.getEditedDevice(device.getDevice_id());
				}
				addFieldError("serialno","Special Character For Device Serial Number is Not Allowed");
			}
			if (device.getModel().getModel_type_id()==0) {
				addFieldError("modelno", "Please Select Model Type");
			}
			String puchaseDate = device.getPurchasedateString() != null ? device.getPurchasedateString().toString() : "";
			if (puchaseDate.equals("") && puchaseDate != null) {
				addFieldError("purchasedate", "Please Select Purchase Date");
			}
			if (device.getVendor().getId() == 0) {
				addFieldError("vendorname", "Please Select Vendor Name");
			}

			if (device.getStatus().equals("")) {
				addFieldError("status", "Please Select Status");
			}
			if (device.getDevice().getDevice_type_id() == 0) {
				addFieldError("devicetype", "Please Select Device Type");
			}
			//setIsEditDevice(1);
			
		}else if(getIsOrgAllocation()==1){
			if(orgChart.getOrg_chart_id()==0){
				addFieldError("orgchart", "Please Select Organization");
				setIsOrgAllocation(1);
			}
		}else if(getIsSimCardAllocation()==1){
			if(simcard.getSimcard_id()==0){
				addFieldError("simCard", "Please Select SimCard");
				setIsSimCardAllocation(1);
			}
		}else if(getIsBatteryAllocation()==1){
			if(battery.getBattery_id()==0){
				addFieldError("battery", "Please Select Battery");
				setIsBatteryAllocation(1);
			}
		}
		if(hasActionErrors() || hasFieldErrors()){
			if(dao.isDeviceTypeCanConatinSimCardAndBattery( device.getDevice_id())){
				request.getSession().setAttribute("tabstat", "ALL");
				setTabstatus("ALL"); 
			}
		if(getIsEditDevice()==1){
			getAllValues(device.getDevice_id());
			device = dao.getEditedDevice(device.getDevice_id());
			this.setPreviousDeviceType(device.getDevice().getDevice_type_id());
			getAssignedValues(device.getDevice_id());}
		else if(getIsCreateDevice()==1){
			setVendorList(dao.getVendorName());
			setDeviceTypeList(dao.getDeviceTypeNames());
			setModelTypeList(dao.getModelName());
		}
		setIsCreateDevice(0);
		setIsEditDevice(0);
//			request.getSession().setAttribute("editingDeviceId", device.getDevice_id());
//			editPageAction();
		}
	}
	public String editPageAction() {
		

		Object edtingDeviceObject = request.getSession().getAttribute("editingDeviceId");
		String deviceId = request.getParameter("deviceid"); 
		String finalDeviceId = (edtingDeviceObject != null) ? edtingDeviceObject.toString() : deviceId;
		
		device = dao.getEditedDevice(Integer.parseInt(finalDeviceId));
		setPreviousDeviceType(device.getDevice().getDevice_type_id());
//		
		
		device.setPurchasedateString(common.getDateToDatePicker(device.getPurchase_date()));
		
		getAllValues(Integer.parseInt(finalDeviceId));
		getAssignedValues(Integer.parseInt(finalDeviceId));
		getvendoredit(device.getVendor().getDevice_type_id());
		return "success";
	}

	public String saveEdited() {
		String returnResult = "";
		DependencyChecker dc=new DependencyChecker();
		String status=dc.getStatus(device.getDevice_id(),"device");
		System.out.println("status---"+status);
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"showdevice.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(edit.equalsIgnoreCase("Y")){
		if(status.equals("")||(!status.equals("") &&  device.getStatus().equals("ACTIVE"))){
		
		System.out.println("Device value.."	+ device.getDevice().getDevice_type_id());
		if(device.getDevice().getDevice_type_id()!= getPreviousDeviceType()){
			if(dao.isDeviceContainsSimcard(device.getDevice_id())){
				addActionError("Selected Device contains Sim Card.Please release Sim Card before changing device type");
				returnResult = "input";
				
			}else if(dao.isDeviceContainsBattery(device.getDevice_id())){
				addActionError("Selected Device contains Battery.Please release Battery before changing device type");
				returnResult = "input";
				
			}else if(dao.updateDevice(device, device.getDevice_id())){
				addActionMessage("Device id " + device.getDevice_id()+ " Updated Successfully");
				returnResult = "success";
			}else{
				addActionError("Problem occured in Device Edititng");
				returnResult = "input";
			}
		}else{
			 if(dao.updateDevice(device, device.getDevice_id())){
				addActionMessage("Device id " + device.getDevice_id()+ " Updated Successfully");
				returnResult = "success";
			}else{
				addActionError("Problem occured in Device Edititng");
				returnResult = "input";
			}
			
		}
		getAllValues(device.getDevice_id());
		getAssignedValues(device.getDevice_id());
		getvendoredit(device.getVendor().getDevice_type_id());
		setIsEditDevice(1);
		
		}else
		{
			
			if(device.getStatus().equals("INACTIVE"))
			{
				getAllValues(device.getDevice_id());
				getAssignedValues(device.getDevice_id());
				getvendoredit(device.getVendor().getDevice_type_id());
				setIsEditDevice(1);
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


	public String deleteselected(){
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid,"showdevice.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
	String returnResult = "";
	int deviceId = Integer.parseInt(request.getParameter("deldeviceid"));
	if(dao.isDeviceFreeFromOrg(deviceId)){
		if(dao.isDeviceFreeFromVehicle(deviceId)){
			dao.updateOrgToDevice(deviceId,null,true);
			dao.updateSimCardToDevice(deviceId, null, true);
			dao.updateBatteryToDevice(deviceId, null, true);
			if(dao.deleteDevice(deviceId)){
				addActionMessage("Device Id "+deviceId+" deleted Successfully");
				returnResult = "success";
			}else{
				addActionError("Problem occured in Device Id "+deviceId+" deletion");
				returnResult = "fail";
			}
		}else{
			String vehicleNo = (String) ServletActionContext.getRequest().getSession().getAttribute("vehicleNo");
			ServletActionContext.getRequest().getSession().removeAttribute("vehicleNo");
			addActionError("Device Id "+deviceId+" is assigned to vehicle No "+vehicleNo+". Please release device from vehicle.");
			returnResult = "fail";
		}
	}else{
		String orgName = (String) ServletActionContext.getRequest().getSession().getAttribute("orgName");
		ServletActionContext.getRequest().getSession().removeAttribute("orgName");
		addActionError("Device Id "+deviceId+" is assigned to Organization id "+orgName+"");
		returnResult = "fail";
	}
		
		return returnResult;
		}else{
			return "success";
		}
	}

	public String saveSimCard() {
		
		String retrunResult = "";
		System.out.println("Function called");
		try
		{
		if (simcard.getSimcard_id()!= 0) {
			if(dao.updateSimCardToDevice(device.getDevice_id(),simcard,false)){
				if(simcard.getSimcard_id()==-10)
					addActionMessage("Simcard is released from Device id "+device.getDevice_id()+" Successfully");
				else
					addActionMessage("Simcard Id "+simcard.getSimcard_id()+" assigned to Device Id "+device.getDevice_id()+" Successfully");
				retrunResult = "success";
			}else{
				if(simcard.getSimcard_id()==-10)
					addActionError("Problem occured in Simcard Release");
				else
					addActionError("Problem occured in Simcard Updation");
				retrunResult = "input";
			}
		}else{
			addFieldError("simCard", "Please Select Simcard");
			retrunResult = "input";
		}
		getAllValues(device.getDevice_id());
		getAssignedValues(device.getDevice_id());
		
		if(device!=null&&device.getVendor()!=null)
			getvendoredit(device.getVendor().getDevice_type_id());
		setIsSimCardAllocation(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		System.out.println("Going to return["+retrunResult+"]");
		return retrunResult;
	}



	public String addOrg() {
		
		String retrunResult = "";
		if (orgChart.getOrg_chart_id()!= 0) {
			System.out.println("----------------------before");
			if(dao.updateOrgToDevice(device.getDevice_id(),orgChart,false)){
				if(orgChart.getOrg_chart_id()==-10)
					addActionMessage("Organization is released from Device id "+device.getDevice_id()+" Successfully");
				else
					addActionMessage("Organization Id "+orgChart.getOrg_chart_id()+" assigned to Device Id "+device.getDevice_id()+" Successfully");
				retrunResult = "success";
			}else{
				if(orgChart.getOrg_chart_id()==-10)
					addActionError("Problem occured in Organization Release");
				else
					addActionError("Problem occured in Organization Updation");
				retrunResult = "input";
			}
		}else{
			addFieldError("orgChart", "Please Select Organization");
			retrunResult = "input";
		}
		try {
		getAllValues(device.getDevice_id());
		getAssignedValues(device.getDevice_id());
		getvendoredit(device.getVendor().getDevice_type_id());
		setIsOrgAllocation(1);
		System.out.println("resut is-------------------"+retrunResult);
		}catch (Exception e) {
e.printStackTrace();	
}
		return retrunResult;
	}

	public String addBattery() {

		String retrunResult = "";
		if (battery.getBattery_id()!= 0) {
			if(dao.updateBatteryToDevice(device.getDevice_id(),battery,false)){
				if(battery.getBattery_id()==-10)
					addActionMessage("Battery is released from Device id "+device.getDevice_id()+" Successfully");
				else
					addActionMessage("Battery Id "+battery.getBattery_id()+" assigned to Device Id "+device.getDevice_id()+" Successfully");
				retrunResult = "success";
			}else{
				if(battery.getBattery_id()==-10)
					addActionError("Problem occured in Battery Release");
				else
					addActionError("Problem occured in Battery Updation");
				retrunResult = "input";
			}
		}else{
			addFieldError("battery", "Please Select battery");
			retrunResult = "input";
		}
		getAllValues(device.getDevice_id());
		getAssignedValues(device.getDevice_id());
		getvendoredit(device.getVendor().getDevice_type_id());
		setIsBatteryAllocation(1);
		
		return retrunResult;
	}
}
