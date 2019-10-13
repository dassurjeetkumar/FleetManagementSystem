package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.common.DependencyChecker;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.SearchCriteriaJson;
import com.trimax.its.vehicle.dao.UpdateVehicleDetailsDAO;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.BodyType;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.DockingHistory;
import com.trimax.its.vehicle.model.DockingType;
import com.trimax.its.vehicle.model.Fuel;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.TransferVehicleHistory;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleCategory;
import com.trimax.its.vehicle.model.VehicleFcRenewal;
import com.trimax.its.vehicle.model.VehicleModel;
import com.trimax.its.vehicle.model.VehicleType;

/**
 * @author Satya
 * Changed By Aditi
 */

public class VehicleAction extends ActionSupport {

	Common common = new Common();
	String returnResult = "fail";
	private List VehicleModel;
	private Vehicle vehicle;
	private List depotList;
	private List<VehicleFcRenewal> vehicleFcRenewalHistory;
	private List<DockingType> dockingTypeList;
	private List<DockingHistory> dockingHistory;
	private TransferVehicleHistory transferHistory;
	private List<TransferVehicleHistory> transferVehicleHistoryList;

	private int unitType;
	private List fuelFilledByUsersList;
	private List<Fuel> fuelHistory;
	static Vehicle vehicleObject;
	private List componenetsTypeList;
	
	private String vehicleFcExpiryDate;
	private String vehicleDockingPlanningDate;
	private String vehicleOperationalDate;
	private String vehicleProcuredDate;
	private String vehicleRegistrationDate;
	
	private List<VehicleType> vehicleTypeList;
	private List<BrandType> brandTypeList;
	private List<MakeType> makeTypeList;
	private List<ModelType> modelTypeList;
	private List<BodyType> bodyTypeList;
	private List<ServiceType> serviceTypeList;
	private List<VehicleCategory> vehicleCategoryList;
	
	private List<OrgType> orgTypeList;
	private int editedVehicleId;
	private static String isScrapped ;
	
	VehicleDAO daoObject = new VehicleDAO();


	public int getEditedVehicleId() {
		return editedVehicleId;
	}

	public void setEditedVehicleId(int editedVehicleId) {
		this.editedVehicleId = editedVehicleId;
	}

	public List getVehicleModel() {
		return VehicleModel;
	}

	public void setVehicleModel(List vehicleModel) {
		VehicleModel = vehicleModel;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	
	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public List getDepotList() {
		return depotList;
	}

	public void setDepotList(List depotList) {
		this.depotList = depotList;
	}

	public TransferVehicleHistory getTransferHistory() {
		return transferHistory;
	}

	public void setTransferHistory(TransferVehicleHistory transferHistory) {
		this.transferHistory = transferHistory;
	}

	public List<TransferVehicleHistory> getTransferVehicleHistoryList() {
		return transferVehicleHistoryList;
	}

	public void setTransferVehicleHistoryList(
			List<TransferVehicleHistory> transferVehicleHistoryList) {
		this.transferVehicleHistoryList = transferVehicleHistoryList;

	}

	public List getVehicleFcRenewalHistory() {
		return vehicleFcRenewalHistory;
	}

	public void setVehicleFcRenewalHistory(List vehicleFcRenewalHistory) {
		this.vehicleFcRenewalHistory = vehicleFcRenewalHistory;
	}

	public List<DockingType> getDockingTypeList() {
		return dockingTypeList;
	}

	public void setDockingTypeList(List<DockingType> dockingTypeList) {
		this.dockingTypeList = dockingTypeList;
	}

	public List getFuelFilledByUsersList() {
		return fuelFilledByUsersList;
	}

	public List<Fuel> getFuelHistory() {
		return fuelHistory;
	}

	public void setFuelHistory(List<Fuel> fuelHistory) {
		this.fuelHistory = fuelHistory;
	}

	public void setFuelFilledByUsersList(List fuelFilledByUsersList) {
		this.fuelFilledByUsersList = fuelFilledByUsersList;
	}

	public List<DockingHistory> getDockingHistory() {
		return dockingHistory;
	}

	public void setDockingHistory(List<DockingHistory> dockingHistory) {
		this.dockingHistory = dockingHistory;
	}

	public List getComponenetsTypeList() {
		return componenetsTypeList;
	}

	public void setComponenetsTypeList(List componenetsTypeList) {
		this.componenetsTypeList = componenetsTypeList;
	}

	
	

	public String getVehicleFcExpiryDate() {
		return vehicleFcExpiryDate;
	}

	public void setVehicleFcExpiryDate(String vehicleFcExpiryDate) {
		this.vehicleFcExpiryDate = vehicleFcExpiryDate;
	}

	public String getVehicleDockingPlanningDate() {
		return vehicleDockingPlanningDate;
	}

	public void setVehicleDockingPlanningDate(String vehicleDockingPlanningDate) {
		this.vehicleDockingPlanningDate = vehicleDockingPlanningDate;
	}

	public String getVehicleOperationalDate() {
		return vehicleOperationalDate;
	}

	public void setVehicleOperationalDate(String vehicleOperationalDate) {
		this.vehicleOperationalDate = vehicleOperationalDate;
	}

	public String getVehicleProcuredDate() {
		return vehicleProcuredDate;
	}

	public void setVehicleProcuredDate(String vehicleProcuredDate) {
		this.vehicleProcuredDate = vehicleProcuredDate;
	}

	public String getVehicleRegistrationDate() {
		return vehicleRegistrationDate;
	}

	public void setVehicleRegistrationDate(String vehicleRegistrationDate) {
		this.vehicleRegistrationDate = vehicleRegistrationDate;
	}

	public List<VehicleType> getVehicleTypeList() {
		return vehicleTypeList;
	}

	public void setVehicleTypeList(List<VehicleType> vehicleTypeList) {
		this.vehicleTypeList = vehicleTypeList;
	}

	public List<BrandType> getBrandTypeList() {
		return brandTypeList;
	}

	public void setBrandTypeList(List<BrandType> brandTypeList) {
		this.brandTypeList = brandTypeList;
	}

	public List<MakeType> getMakeTypeList() {
		return makeTypeList;
	}

	public void setMakeTypeList(List<MakeType> makeTypeList) {
		this.makeTypeList = makeTypeList;
	}

	public List<ModelType> getModelTypeList() {
		return modelTypeList;
	}

	public void setModelTypeList(List<ModelType> modelTypeList) {
		this.modelTypeList = modelTypeList;
	}

	public List<BodyType> getBodyTypeList() {
		return bodyTypeList;
	}

	public void setBodyTypeList(List<BodyType> bodyTypeList) {
		this.bodyTypeList = bodyTypeList;
	}

	public List<ServiceType> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<ServiceType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public List<VehicleCategory> getVehicleCategoryList() {
		return vehicleCategoryList;
	}

	public void setVehicleCategoryList(List<VehicleCategory> vehicleCategoryList) {
		this.vehicleCategoryList = vehicleCategoryList;
	}
	public List<OrgType> getOrgTypeList() {
		return orgTypeList;
	}

	public void setOrgTypeList(List<OrgType> orgTypeList) {
		this.orgTypeList = orgTypeList;
	}

	
	public String getIsScrapped() {
		return isScrapped;
	}

	public void setIsScrapped(String isScrapped) {
		this.isScrapped = isScrapped;
	}

	public String execute() {
		VehicleModel modelObject = new VehicleModel();
		this.setVehicleModel(daoObject.getVehicleList());

		return "success";
	}
	
	public void statusOfVehicle()
	{
		String vehicleID = ServletActionContext.getRequest().getParameter("vehicleId");
		String statusOfVehicle = daoObject.statusOfVehicle(vehicleID);
		HttpServletResponse response = ServletActionContext.getResponse();
	 	PrintWriter out;
	 	try {
	 		out = response.getWriter();
	 		out.print(statusOfVehicle);
	 	} catch (IOException e) {
	 		e.printStackTrace();
	 	}
	}

	public String getParticularVehicleDetails() {
		VehicleModel modelObject = new VehicleModel();
		String vehicleid = ServletActionContext.getRequest().getParameter("value");
		Object vehicleID = ServletActionContext.getRequest().getSession().getAttribute("vehilceID");
		
		String vehicleId = (vehicleid != null) ? vehicleid : vehicleID.toString();
		
		ServletActionContext.getRequest().getSession().setAttribute("editVehicleId", vehicleId);
		ServletActionContext.getRequest().getSession().removeAttribute("vehilceID");
		
		this.setVehicle(daoObject.getParticularVehicleDetails(vehicleId));
		this.setIsScrapped(vehicle.getIsScarpped());
		if(this.getVehicle().getFcExpiryDate()!=null){
			this.vehicle.setFcExpiryDateString(common.getDateToDatePicker(this.getVehicle().getFcExpiryDate()));
		}
		if(this.getVehicle().getFcRenewalDate()!=null){
			this.vehicle.setFcRenewalDateString(common.getDateToDatePicker(this.getVehicle().getFcRenewalDate()));
		}
		if(this.getVehicle().getDockingPlanningDate()!=null){
			this.vehicle.setDockingPlanningDateString(common.getDateToDatePicker(this.getVehicle().getDockingPlanningDate()));
		}
		if(this.getVehicle().getOperationalDate()!=null){
			this.vehicle.setOperationalDateString(common.getDateToDatePicker(this.getVehicle().getOperationalDate()));
		}
		if(this.getVehicle().getProceduredDate()!=null){
			this.vehicle.setProcuredDateString(common.getDateToDatePicker(this.getVehicle().getProceduredDate()));
		}
		if(this.getVehicle().getRegistrationDate()!=null){
			this.vehicle.setRegistrationDateString(common.getDateToDatePicker(this.getVehicle().getRegistrationDate()));
		}
		if(this.getVehicle().getScrappedDate()!=null){
			this.vehicle.setScrappedDateString(common.getDateToDatePicker(this.getVehicle().getScrappedDate()));
		}
		

		allLists();
		
		return "success";
	}
	public void allLists()
	{
		this.setVehicleTypeList(daoObject.getVehicleTypes());
		this.setBrandTypeList(daoObject.getBrandTypes());
		this.setMakeTypeList(daoObject.getMakeTypes());
		this.setModelTypeList(daoObject.getModelTypes());
		this.setBodyTypeList(daoObject.getBodyTypes());
		this.setServiceTypeList(daoObject.getServiceTypes());
		this.setVehicleCategoryList(daoObject.getCategoryTypes());

		this.setOrgTypeList(daoObject.getOrgTypes());
	}
	public String saveEditVehicleDetails() {

		return "success";
	}

	public String saveEditedVehicleDetails() {
		
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		HttpServletRequest request = ServletActionContext.getRequest();

		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleDetails.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		System.out.println("in save edit");
		UpdateVehicleDetailsAction values = new UpdateVehicleDetailsAction();
		UpdateVehicleDetailsDAO daoobject = new UpdateVehicleDetailsDAO();
		//System.out.println("vehicleObject.getProcuredDateString()=="+vehicleObject.getProcuredDateString());
		if(edit.equalsIgnoreCase("Y"))
		{
		/*if(vehicle.getStatus().equals("INACTIVE"))
		{*/
			try{
				//vehicleObject.setProceduredDate(common.getDate((vehicleObject.getProceduredDate()));
//				vehicleObject.setProceduredDate(common.getDateFromDatePicker(vehicleObject.getProcuredDateString());
//				vehicleObject.setOperationalDate(common.getDateFromDatePicker(vehicleObject.getOperationalDateString()));
//				vehicleObject.setRegistrationDate(common.getDateFromDatePicker(vehicleObject.getRegistrationDateString()));
//				vehicleObject.setFcExpiryDate(common.getDateFromDatePicker(vehicleObject.getFcExpiryDateString()));
//				vehicleObject.setDockingPlanningDate(common.getDateFromDatePicker(vehicleObject.getDockingPlanningDateString()));
//				vehicleObject.setScrappedDate(common.getDateFromDatePicker(vehicleObject.getScrappedDateString()));
			}catch(Exception e){
				e.printStackTrace();
			}
			
		 DependencyChecker dc=new DependencyChecker();
			String status=dc.getStatus(vehicle.getVehicleId(), "vehicle");
			
		if(status.equals("")  ||(!status.equals("") &&  vehicle.getStatus().equals("ACTIVE"))){
			
		//i commented for new record to be created
			System.out.println("----------------");
		if(daoObject.isVehicleNew(vehicle,"update")){
			if(daoObject.isnewchasis(vehicle,"update")){
		
			if (daoobject.saveEditedVehicleDetails(vehicle)) {
				addActionMessage("Vehicle Id "+vehicle.getVehicleId()+" Edited  Successfully");
				returnResult = "success";
			} else {
				addActionError("Error in Edit Vehicle Record");
				returnResult = "fail";
				allLists();
			}
			}else{
				addFieldError("vehicle.chasisNumber","Duplicate Chasis Number");
				returnResult = "fail";
				allLists();
			}
		}
		else{
			addFieldError("registrationNumber","Duplicate Vehicle Number");
			returnResult = "fail";
			allLists();
		}
			//end by itishree
		/*ServletActionContext.getRequest().getSession().setAttribute("vehilceID", editedVehicleId);
		getParticularVehicleDetails();*/
	}else{
		if(vehicle.getStatus().equals("INACTIVE"))
		{
			//setUpdatestaus("success");
			allLists();
		addActionError(status);
		return "input";
		}
		
	}
		return returnResult;
		}else{
			return "input";
		}
	}
	


	public String deleteVehicle() {
		String status="";
		boolean schFlag;
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleDetails.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String deleteVehicleId = ServletActionContext.getRequest().getParameter("deleteVehicleId");
		if(delete.equalsIgnoreCase("Y")){
		status=daoObject.deleteVehilce(Integer.parseInt(deleteVehicleId));
		schFlag=daoObject.deleteScheduleMappingForVehicle(Integer.parseInt(deleteVehicleId));
		System.out.println("after delete");
		if(status.split(":")[0].equals("success")){
		
			addActionMessage("Vehicle Id "+deleteVehicleId+" deleted successfully");
			returnResult = "success";
		}
		
		if(status.equals("")){
			
			addActionMessage("Vehicle Id "+deleteVehicleId+" deleted successfully");
			returnResult = "success";
		} else {
			addActionError(status);
			returnResult = "fail";
		}
		return returnResult;
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
		
	}

	@SuppressWarnings("deprecation")
	public void validate() {
		try{
		Common common=new Common();
		CreateVehicleAction createVehicle = new CreateVehicleAction();
		CommonValidation commonvalid = new CommonValidation();
		if (editedVehicleId != 0) {

			
			/*if (vehicle.getVehicleRegistrationNumber() == null || vehicle.getVehicleRegistrationNumber().trim().equals("")) {
				this.addFieldError("registrationNumber","Registration Number Can't empty");
			}*/
			if (vehicle.getVehicleRegistrationNumber() == null || vehicle.getVehicleRegistrationNumber().trim().equals("")) {
				addFieldError("registrationNumber","Please enter Registration number");
			}
//			else if(!createVehicle.isValidateRegistrationNumber(vehicle.getVehicleRegistrationNumber().trim())){
//				addFieldError("registrationNumber","Invalid Format");
//				
//			}
			else if (!vehicle.getVehicleRegistrationNumber().trim().matches("^[a-zA-Z0-9- ]*$")) {
				addFieldError("registrationNumber","Special characters not allowed for Registration number");
			}else if(vehicle.getVehicleRegistrationNumber().trim().indexOf("KA")!=0){
				addFieldError("registrationNumber","Registartion Number should start with KA");
			}else if(vehicle.getVehicleRegistrationNumber().trim().equals("KA")){
				addFieldError("registrationNumber","Please enter registartion number after KA");
			}
			
			if(vehicle.getDepotOrUnit().getOrgType().getOrg_type_id()==0){
				addFieldError("org_type_id","Please Select Organization Type");
			}
			if(vehicle.getDepotOrUnit().getOrg_chart_id()==0){
				addFieldError("orgName","Please Select Organization Unit Name");
			}
			
			if (!commonvalid.isSpecialChar(vehicle.getProgressingKMs())) {
				addFieldError("progressingRunningKms","Special Character For Progressive Running Kms is Not Allowed");
				//this.cashRemittanceVoucher.setBank_name(this.cashRemittanceVoucher.getBank_name());
				
			}
			if (!commonvalid.isSpecialChar(vehicle.getScheduleKMs())) {
				addFieldError("progressingScheduleKms","Special Character For Progressive Schedule Kms is Not Allowed");
				//this.cashRemittanceVoucher.setBank_name(this.cashRemittanceVoucher.getBank_name());
				
			}
			
			/*if(vehicle.getProgressingKMs()!=null&&!vehicle.getProgressingKMs().trim().equals("")){
				if(!Pattern.matches("\\d.*", vehicle.getProgressingKMs())){
					addFieldError("progressingRunningKms","Incorrect value for Progressive Running Kms");
				}
			}
			
			if(vehicle.getScheduleKMs()!=null&&!vehicle.getScheduleKMs().trim().equals("")){
				if(!Pattern.matches("\\d.*", vehicle.getScheduleKMs())){
					addFieldError("progressingScheduleKms","Incorrect value for Progressive Schedule Kms");
				}
			}*/
			
			
			if(vehicle.getVehicleType().getVehicle_type_id()==0){
				addFieldError("vehicle.vehicleType.vehicle_type_id","Please Select Vehicle Type ");
			}
			
			
			if(vehicle.getBrandType().getBrand_type_id()==0){
				addFieldError("vehicle.brandType.brand_type_id","Please Select Brand Type ");
			}
			
			if(vehicle.getMakeType().getMake_type_id()==0){
				addFieldError("vehicle.makeType.make_type_id","Please Select Make");
			}
			
			if(vehicle.getModelType().getModel_type_id()==0){
				addFieldError("vehicle.modelType.model_type_id","Please Select Model");
			}
			
			/*if(vehicle.getBodyType().getBody_type_id()==0){
				addFieldError("vehicle.bodyType.body_type_id","Please Select Body Type");
			}*/

			/*if(vehicle.getServiceType().getService_type_id()==0){
				addFieldError("vehicle.serviceType.service_type_id","Please Service Type");
			}*/
			
			if(vehicle.getFcRenewalDateString()==null||vehicle.getFcRenewalDateString().equals("") ){
				addFieldError("editedFcExpiryDate",	"Please Select Procured Date ");
			}
			
			if(vehicle.getRegistrationDateString()==null||vehicle.getRegistrationDateString().equals("") ){
				addFieldError("registrationDate","Please select registration date");
			}/*else if(!(common.compareDates(vehicle.getRegistrationDateString().toString(),vehicle.getProcuredDateString().toString())>0)){
				addFieldError("registrationDate","Registration Date Should be graeter than Procured date");
			}*/
			
			if(vehicle.getOperationalDateString()==null||vehicle.getOperationalDateString().equals("")){
				addFieldError("operationalDate","Please Select Operational date");
			}else if(!(common.compareDates(vehicle.getOperationalDateString().toString(),vehicle.getRegistrationDateString().toString())>0)){
				addFieldError("operationalDate","Operational Date Should be greater than  Registration date ");
			}
			if(vehicle.getMakeType().getMake_type_id()==9){
				
			}
			else{
			
		/*	if(vehicle.getDockingPlanningDateString()==null||vehicle.getDockingPlanningDateString().equals("")){
				addFieldError("dockingPlanningDate","Please select Docking Planning date");
			}else if(!(common.compareDates(vehicle.getDockingPlanningDateString().toString(),vehicle.getOperationalDateString().toString())>0)){
				addFieldError("dockingPlanningDate","Docking Planning  Date Should be greater than  Operational date ");
			}*/
			}
			
			if(vehicle.getMakeType().getMake_type_id()==9){
				
			}else{
			
			/*if (vehicle.getFcExpiryDateString() == null || vehicle.getFcExpiryDateString().trim().equals("")) {
				this.addFieldError("editedFcExpiryDate","FC Expiry Date Can't be empty");
			}else if(!(common.compareDates(vehicle.getFcExpiryDateString().toString(), vehicle.getDockingPlanningDateString().toString())>0)){
				addFieldError("editedFcExpiryDate",	"FC Expiry Date Should be greater than Docking Planning  Date ");
			}*/
			}
			if(vehicle.getChasisNumber()==null||vehicle.getChasisNumber().trim().equals("")){
				addFieldError("vehicle.chasisNumber", "Please enter Chassis number");
			}
			if (!commonvalid.isSpecialChar(vehicle.getChasisNumber())) {
				addFieldError("vehicle.chasisNumber","Special Character For Chassis number is Not Allowed");
				this.vehicle.setChasisNumber(this.vehicle.getChasisNumber());
				
			}

			if(vehicle.getEngineNumber()==null||vehicle.getEngineNumber().equals("") ){
				addFieldError("engineNumber","Engine Number  cant be empty");
			}
			if (!commonvalid.isSpecialChar(vehicle.getEngineNumber())) {
				addFieldError("engineNumber","Special Character For Engine number is Not Allowed");
				//this.vehicle.setChasisNumber(this.vehicle.getChasisNumber());
				
			}
			//System.out.println("norm is--"+vehicle.getNorm().equals("0"));
			if(vehicle.getNorm().equals("0")){
				addFieldError("norm","Please select norm type");
			}
			System.out.println("fh-------------"+vehicle.getFloorrHeigt().equals("0"));
			if(vehicle.getFloorrHeigt().equals("0")){
				addFieldError("floorrHeigt","Please select Floor height type");
			}if(vehicle.getHp().equals("0")){
				addFieldError("hp","Please select hp type");
			}
			if(vehicle.getSeat_Capacity().equals("0")){
				addFieldError("seat_Capacity","Please select seat capacity type");
			}
			if(vehicle.getUnladenWeight().equals("0")){
				addFieldError("UnladenWeight","Please select unladen weight type");
			}
			if(vehicle.getDocking_Kms()==null||vehicle.getDocking_Kms().trim().equals("")){
				addFieldError("docking_Kms",	"Please enter docking kms");
			}else if(!Pattern.matches("\\d*", vehicle.getDocking_Kms().toString())){
					addFieldError("docking_Kms",	"Only enter the digits only");
			}
			
			
			
			if(vehicle.getWheelsCount()==null||vehicle.getWheelsCount()==0){
				addFieldError("wheelCount",	"Please enter No.Of Wheels");
			}else if(!Pattern.matches("\\d*", vehicle.getWheelsCount().toString())){
					addFieldError("wheelCount",	"Please Enter Valid Value For No. Of Wheels");
			}
			
			if(vehicle.getVehicleCategory().getVehicleCategoryId()==0){
				addFieldError("vehicle.vehicleCategory.vehicleCategoryId", "Please Select Vehicle Usage Category");
			}
			
			if(vehicle.getStatus().equalsIgnoreCase("SCRAP")){
				if (vehicle.getScrappedDateString() == null || vehicle.getScrappedDateString().trim().equals("")) {
					this.addFieldError("scrapDate","Please Enter Scrap date");
				}
			}

			if(hasFieldErrors()){
				allLists();
			}
			
		}}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDepotNames() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String idBusStopName = request.getParameter("id");
			
		List<OrganisationChart> list = daoObject.getDepotNames(idBusStopName);

		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		for (OrganisationChart depot : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(depot.getOrg_name() + " "
					+ depot.getOrg_chart_id());
			searchCriteria.setAlias1(depot.getOrg_chart_id());
			searchCriteria.setSearchCriteriaName(depot.getOrg_name());
			list1.add(searchCriteria);
		}

		
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
						e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}

	public String getVehicleType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String vehicleTypeContains = request.getParameter("id");

		List<VehicleType> list = daoObject.getVehicleTypes(vehicleTypeContains);

		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		for (VehicleType vehicleType : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(vehicleType.getVehicle_type_name() + " "
					+ vehicleType.getVehicle_type_id());
			searchCriteria.setAlias1(vehicleType.getVehicle_type_id());
			searchCriteria.setSearchCriteriaName(vehicleType
					.getVehicle_type_name());
			list1.add(searchCriteria);
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}

	public String getBrandType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String brandTypeNameStartWith = request.getParameter("id");

		List<BrandType> list = daoObject.getBrandTypes(brandTypeNameStartWith);

		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		for (BrandType brandType : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(brandType.getBrand_type_name() + " "
					+ brandType.getBrand_type_id());
			searchCriteria.setAlias1(brandType.getBrand_type_id());
			searchCriteria
					.setSearchCriteriaName(brandType.getBrand_type_name());
			list1.add(searchCriteria);
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}

	public String getMakeType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String makeTypeNameStartWith = request.getParameter("id");

		List<MakeType> list = daoObject.getMakeTypes(makeTypeNameStartWith);

		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		for (MakeType makeType : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(makeType.getMake_type_name() + " "
					+ makeType.getMake_type_id());
			searchCriteria.setAlias1(makeType.getMake_type_id());
			searchCriteria.setSearchCriteriaName(makeType.getMake_type_name());
			list1.add(searchCriteria);
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}

	public String getModelType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String modelTypeNameStartWith = request.getParameter("id");

		List<ModelType> list = daoObject.getModelTypes(modelTypeNameStartWith);

		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		// if(list.size()>0){
		for (ModelType modelType : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(modelType.getModel_type_name() + " "
					+ modelType.getModel_type_id());
			searchCriteria.setAlias1(modelType.getModel_type_id());
			searchCriteria
					.setSearchCriteriaName(modelType.getModel_type_name());
			list1.add(searchCriteria);
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}

	public String getBodyType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String bodyTypeNameStartWith = request.getParameter("id");
		List<BodyType> list = daoObject.getBodyTypes(bodyTypeNameStartWith);
		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		for (BodyType bodyType : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(bodyType.getBody_type_name() + " "
					+ bodyType.getBody_type_id());
			searchCriteria.setAlias1(bodyType.getBody_type_id());
			searchCriteria.setSearchCriteriaName(bodyType.getBody_type_name());
			list1.add(searchCriteria);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}
	public String getCategoryType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String serviceTypeNameStartWith = request.getParameter("id");
		List<VehicleCategory> list = daoObject.getCategoryTypes(serviceTypeNameStartWith);
		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		for (VehicleCategory categoryType : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(categoryType.getVehicleCategoryName() + " "
					+ categoryType.getVehicleCategoryId());
			searchCriteria.setAlias1(categoryType.getVehicleCategoryId());
			searchCriteria.setSearchCriteriaName(categoryType.getVehicleCategoryName());
			list1.add(searchCriteria);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}
	public String getServiceType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String serviceTypeNameStartWith = request.getParameter("id");
		List<ServiceType> list = daoObject.getServiceTypes(serviceTypeNameStartWith);
		List<SearchCriteriaJson> list1 = new ArrayList<SearchCriteriaJson>();
		for (ServiceType serviceType : list) {

			SearchCriteriaJson searchCriteria = new SearchCriteriaJson();
			searchCriteria.setValue(serviceType.getService_type_name() + " "
					+ serviceType.getService_type_id());
			searchCriteria.setAlias1(serviceType.getService_type_id());
			searchCriteria.setSearchCriteriaName(serviceType.getService_type_name());
			list1.add(searchCriteria);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(new JSONArray(list1));

		return null;
	}
}
