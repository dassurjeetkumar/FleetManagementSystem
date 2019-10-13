package com.trimax.its.vehicle.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;

import com.itextpdf.text.log.SysoLogger;
import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.dao.VehicleDAO;
import com.trimax.its.vehicle.model.BodyType;
import com.trimax.its.vehicle.model.BrandType;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vehicle.model.Vehicle;
import com.trimax.its.vehicle.model.VehicleCategory;
import com.trimax.its.vehicle.model.VehicleType;

public class CreateVehicleAction extends ActionSupport {

	// properties for Create Vehicle
	private List depotorUnitNameList;
	private List vehicleTypeList;
	private List brandTypeList;
	private List makeList;
	private List modelList;
	private List serviceTypeList;
	private List bodyTypeList;
	private List vehicleCategoryList;
	
	private String createNewVehicle;
	private String reigistrationNumber;
	private String fcExpiryDate;
	private String fcRenewalDate;
	public String getFcRenewalDate() {
		return fcRenewalDate;
	}

	public void setFcRenewalDate(String fcRenewalDate) {
		this.fcRenewalDate = fcRenewalDate;
	}
	private String progressingRunningKms;
	private String progressingScheduleKms;
	private int vehicleTypeId;
	private int brandTypeId;
	private String acAvailability;
	private int makeTypeId;
	private int modelTypeId;
	private String procuredDate;
	private int bodyTypeId;
	private String operationalDate;
	private String dockingPlanningDate;
	private int serviceTypeId;
	private String registrationDate;
	private String chasisNumber;
	private String engineNumber;
	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getFloorrHeigt() {
		return floorrHeigt;
	}

	public void setFloorrHeigt(String floorrHeigt) {
		this.floorrHeigt = floorrHeigt;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getSeat_Capacity() {
		return seat_Capacity;
	}

	public void setSeat_Capacity(String seat_Capacity) {
		this.seat_Capacity = seat_Capacity;
	}


	private String norm;
	private String floorrHeigt;
	private String hp;
	private String seat_Capacity;
	private String dockingklms;
	public String getDockingklms() {
		return dockingklms;
	}

	public void setDockingklms(String dockingklms) {
		this.dockingklms = dockingklms;
	}
	private String UnladenWeight;
	
	public String getUnladenWeight() {
		return UnladenWeight;
	}

	public void setUnladenWeight(String unladenWeight) {
		UnladenWeight = unladenWeight;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	private Integer wheelCount;
	private int vehicleUsageCatageryId;
	private String underWarranty;
	private String status;

	private List<OrgType> orgTypeList;
	private int unitType;
	private int orgChartId;
	
	Common common = new Common();
	VehicleDAO daoobject = new VehicleDAO();
	
	public List getDepotorUnitNameList() {
		return depotorUnitNameList;
	}

	public void setDepotorUnitNameList(List depotorUnitNameList) {
		this.depotorUnitNameList = depotorUnitNameList;
	}

	public List getVehicleTypeList() {
		return vehicleTypeList;
	}

	public void setVehicleTypeList(List vehicleTypeList) {
		this.vehicleTypeList = vehicleTypeList;
	}

	public List getBrandTypeList() {
		return brandTypeList;
	}

	public void setBrandTypeList(List brandTypeList) {
		this.brandTypeList = brandTypeList;
	}

	public List getMakeList() {
		return makeList;
	}

	public void setMakeList(List makeList) {
		this.makeList = makeList;
	}

	public List getModelList() {
		return modelList;
	}

	public void setModelList(List modelList) {
		this.modelList = modelList;
	}

	public List getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public List getBodyTypeList() {
		return bodyTypeList;
	}

	
	public List getVehicleCategoryList() {
		return vehicleCategoryList;
	}

	public void setVehicleCategoryList(List vehicleCategoryList) {
		this.vehicleCategoryList = vehicleCategoryList;
	}

	public void setBodyTypeList(List bodyTypeList) {
		this.bodyTypeList = bodyTypeList;
	}

	public String getCreateNewVehicle() {
		return createNewVehicle;
	}

	public void setCreateNewVehicle(String createNewVehicle) {
		this.createNewVehicle = createNewVehicle;
	}

	public String getReigistrationNumber() {
		return reigistrationNumber;
	}

	public void setReigistrationNumber(String reigistrationNumber) {
		this.reigistrationNumber = reigistrationNumber;
	}


	public String getProgressingRunningKms() {
		return progressingRunningKms;
	}

	public void setProgressingRunningKms(String progressingRunningKms) {
		this.progressingRunningKms = progressingRunningKms;
	}

	public String getProgressingScheduleKms() {
		return progressingScheduleKms;
	}

	public void setProgressingScheduleKms(String progressingScheduleKms) {
		this.progressingScheduleKms = progressingScheduleKms;
	}

	public String getFcExpiryDate() {
		return fcExpiryDate;
	}

	public void setFcExpiryDate(String fcExpiryDate) {
		this.fcExpiryDate = fcExpiryDate;
	}

	public int getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public int getBrandTypeId() {
		return brandTypeId;
	}

	public void setBrandTypeId(int brandTypeId) {
		this.brandTypeId = brandTypeId;
	}

	public String getAcAvailability() {
		return acAvailability;
	}

	public void setAcAvailability(String acAvailability) {
		this.acAvailability = acAvailability;
	}

	public int getMakeTypeId() {
		return makeTypeId;
	}

	public void setMakeTypeId(int makeTypeId) {
		this.makeTypeId = makeTypeId;
	}

	public int getModelTypeId() {
		return modelTypeId;
	}

	public void setModelTypeId(int modelTypeId) {
		this.modelTypeId = modelTypeId;
	}


	public String getProcuredDate() {
		return procuredDate;
	}

	public void setProcuredDate(String procuredDate) {
		this.procuredDate = procuredDate;
	}


	public String getOperationalDate() {
		return operationalDate;
	}

	public void setOperationalDate(String operationalDate) {
		this.operationalDate = operationalDate;
	}

	public int getBodyTypeId() {
		return bodyTypeId;
	}

	public void setBodyTypeId(int bodyTypeId) {
		this.bodyTypeId = bodyTypeId;
	}

	
	public String getDockingPlanningDate() {
		return dockingPlanningDate;
	}

	public void setDockingPlanningDate(String dockingPlanningDate) {
		this.dockingPlanningDate = dockingPlanningDate;
	}

	public int getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(int serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}


	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getChasisNumber() {
		return chasisNumber;
	}

	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	public Integer getWheelCount() {
		return wheelCount;
	}

	public void setWheelCount(Integer wheelCount) {
		this.wheelCount = wheelCount;
	}

	public int getVehicleUsageCatageryId() {
		return vehicleUsageCatageryId;
	}

	public void setVehicleUsageCatageryId(int vehicleUsageCatageryId) {
		this.vehicleUsageCatageryId = vehicleUsageCatageryId;
	}

	public String getUnderWarranty() {
		return underWarranty;
	}

	public void setUnderWarranty(String underWarranty) {
		this.underWarranty = underWarranty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public int getOrgChartId() {
		return orgChartId;
	}

	public void setOrgChartId(int orgChartId) {
		this.orgChartId = orgChartId;
	}


	public List<OrgType> getOrgTypeList() {
		return orgTypeList;
	}

	public void setOrgTypeList(List<OrgType> orgTypeList) {
		this.orgTypeList = orgTypeList;
	}

	public String createVehicleDetails() {

		getAllList();
		/*setReigistrationNumber("KA"+getReigistrationNumber());*/
		if(reigistrationNumber==null||reigistrationNumber.trim().equals(""))
		{
			reigistrationNumber = "KA";
			System.out.println("----------form entry-----------------------");
		}
		return "success";
	}
	public void getAllList(){
		
		this.setDepotorUnitNameList(daoobject.getDepotNames());
		this.setVehicleTypeList(daoobject.getVehicleTypes());
		this.setBrandTypeList(daoobject.getBrandTypes());
		this.setMakeList(daoobject.getMakeTypes());
		this.setModelList(daoobject.getModelTypes());
		this.setServiceTypeList(daoobject.getServiceTypes());
		/*this.setBodyTypeList(daoobject.getBodyTypes());*/
		this.setVehicleCategoryList(daoobject.getCategoryTypes());
		this.setOrgTypeList(daoobject.getOrgTypes());
	}
	 
	public String getUnitName() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
 		int orgtypeid=Integer.parseInt(request.getParameter("orgid")) ;
   	 	List<String> l1=daoobject.getUnitId(orgtypeid);
 		List<String> l2=daoobject.getUnitName(orgtypeid);
 		String regionTypeAjaxString = "";
 	    regionTypeAjaxString += "<option value=0>Select</option>";
 	    for (int i = 0; i < l1.size(); i++) {
 	        regionTypeAjaxString +="<option value=" + l1.get(i).toString() + " id='orgName"+l1.get(i).toString()+"'>" + l2.get(i).toString() + "</option>";
 	    }
 	    System.out.println("regionTypeAjaxString="+regionTypeAjaxString);
 	    HttpServletResponse response = ServletActionContext.getResponse();
 	    PrintWriter out;
 		try {
 			out = response.getWriter();
 			out.print(regionTypeAjaxString);
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 	    

   	System.out.println(orgtypeid);
		return null;
   	  
     }

	@SuppressWarnings("finally")
	public String createNewVehicle() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String returnResult = "fail";
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "VehicleDetails.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(create.equalsIgnoreCase("Y")){
		try {
			VehicleDAO daoObject = new VehicleDAO();

			VehicleType vehicleType = new VehicleType();
			vehicleType.setVehicle_type_id(vehicleTypeId);

			BrandType brandType = new BrandType();
			brandType.setBrand_type_id(brandTypeId);

			MakeType makeType = new MakeType();
			makeType.setMake_type_id(makeTypeId);

			ModelType modelType = new ModelType();
			modelType.setModel_type_id(modelTypeId);

			ServiceType serviceType = new ServiceType();
			serviceType.setService_type_id(serviceTypeId);

		/*	BodyType bodyType = new BodyType();
			bodyType.setBody_type_id(bodyTypeId);*/
			
			VehicleCategory vehicleCategory = new VehicleCategory();
			vehicleCategory.setVehicleCategoryId(vehicleUsageCatageryId);
			
			OrganisationChart orgChart = new OrganisationChart();
			orgChart.setOrg_chart_id( orgChartId);
			
			Vehicle vehicleObject = new Vehicle();

			
			vehicleObject.setVehicleType(vehicleType.getVehicle_type_id()!=0?vehicleType:null);
			vehicleObject.setBrandType(brandType.getBrand_type_id()!=0?brandType:null);
			vehicleObject.setMakeType(makeType.getMake_type_id()!=0?makeType:null);
			vehicleObject.setModelType(modelType.getModel_type_id()!=0?modelType:null);
			//vehicleObject.setBodyType(bodyType.getBody_type_id()!=0?bodyType:null);
			vehicleObject.setServiceType(serviceType.getService_type_id()!=0?serviceType:null);
			vehicleObject.setVehicleCategory(vehicleCategory.getVehicleCategoryId()!=0?vehicleCategory:null);
			

			vehicleObject.setVehicleRegistrationNumber(reigistrationNumber.replaceAll("\\s+", "").toUpperCase().replaceAll("-", ""));  // to remove spaces
			//vehicleObject.setFcExpiryDate(common.getDateFromDatePicker(fcExpiryDate));
			if(fcRenewalDate==null || fcRenewalDate.equalsIgnoreCase("")){
				vehicleObject.setFcRenewalDate(null);
			}
			vehicleObject.setFcRenewalDate(common.getDateFromDatePicker(fcRenewalDate));
			vehicleObject.setProgressingKMs(progressingRunningKms);
			vehicleObject.setScheduleKMs(progressingScheduleKms);
			vehicleObject.setAcAvailability(acAvailability);
			vehicleObject.setUnderWarranty(underWarranty);
			vehicleObject.setDepotOrUnit(orgChart.getOrg_chart_id()!=0?orgChart:null);
			vehicleObject.setIsScarpped("NO");
			vehicleObject.setCause_status("N");
			
			Date procuredDateObject = common.getDateFromDatePicker(procuredDate);
			if(procuredDateObject!=null){
				vehicleObject.setProceduredDate(procuredDateObject);
			}
			
			Date operationalDateObject = common.getDateFromDatePicker(operationalDate);
			if(operationalDateObject!=null){
				vehicleObject.setOperationalDate(operationalDateObject);
			}
			
			Date dockingPlanninglDateObject = common.getDateFromDatePicker(dockingPlanningDate);
			if(dockingPlanninglDateObject!=null){
				vehicleObject.setDockingPlanningDate(dockingPlanninglDateObject);
			}
			
			Date registrationDateObject = common.getDateFromDatePicker(registrationDate);
			if(registrationDateObject!=null){
				vehicleObject.setRegistrationDate(registrationDateObject);
			}
		
			vehicleObject.setChasisNumber(chasisNumber);
			vehicleObject.setEngineNumber(engineNumber);
			vehicleObject.setWheelsCount(wheelCount);
			vehicleObject.setNorm(norm);
			vehicleObject.setFloorrHeigt(floorrHeigt);
			vehicleObject.setHp(hp);
			vehicleObject.setSeat_Capacity(seat_Capacity);
			vehicleObject.setDocking_Kms(dockingklms);
			vehicleObject.setUnladenWeight(UnladenWeight);
			vehicleObject.setUnderWarranty(underWarranty);
			vehicleObject.setStatus(status);
			vehicleObject.setCreated_date((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
			vehicleObject.setCreated_by(Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString()));
			vehicleObject.setDeleted_status(0);
			vehicleObject.setUpdated_date(null);
			vehicleObject.setUpdated_by(0);

			if(daoObject.isVehicleNew(vehicleObject,"create")){
				if(daoObject.isnewchasis(vehicleObject,"create")){
				if(daoObject.saveNewVehicle(vehicleObject)){
					addActionMessage("Vehicle Id "+ServletActionContext.getRequest().getAttribute("createdVehicleId")+" Created Successfully");
					returnResult = "success";
				}else{
					addActionError("Error in Vehicle Creation");
					returnResult = "fail";
				}}else{
					addFieldError("chasisNumber","Duplicate Chasis Number");
					returnResult = "fail";	
				}
			}else{
				addFieldError("reigistrationNumber","Duplicate Registration Number");
				returnResult = "fail";
			}
			createVehicleDetails();
			} catch (Exception e) {

			e.printStackTrace();
		} finally {
			return returnResult;
		}
		}else{
			return returnResult;
		}
	}

	public void validate() {
		try{
		/*
		 * reigistrationNumber private int depotUnitId fcExpiryDate;
		 * progressingKms; vehicleTypeId; brandTypeId; acAvailability;
		 * makeTypeId; modelTypeId; proceduredDate; operationalDate; bodyTypeId;
		 * dockingPlanningDate; serviceTypeId; registrationDate; chasisNumber;
		 * wheelCount; vehicleUsageCatagery; underWarranty; status;
		 */
		
		System.out.println("--------entering validate--------------");
		CommonValidation commonvalid = new CommonValidation();
		if (createNewVehicle != null && !createNewVehicle.trim().equals("")) {

			reigistrationNumber = reigistrationNumber.trim();
			
			if (reigistrationNumber == null || reigistrationNumber.trim().equals("") || reigistrationNumber.trim().length()<8) {
				addFieldError("reigistrationNumber","Please Enter above 7 characters of Registration No");
			}
			else if(reigistrationNumber !=null  || reigistrationNumber.trim().length()>7){
				//System.out.println("==================="+reigistrationNumber.toString().subSequence(reigistrationNumber.toString().length()-4,reigistrationNumber.toString().length()));
				//System.out.println("==================="+Pattern.matches("\\d", reigistrationNumber.toString().subSequence(reigistrationNumber.toString().length()-4,reigistrationNumber.toString().length())));
				//System.out.println("==================="+Pattern.matches("\\d", reigistrationNumber.toString().subSequence(reigistrationNumber.toString().length()-4,reigistrationNumber.toString().length())));

				if(!Pattern.matches("\\d*", reigistrationNumber.toString().trim().subSequence(reigistrationNumber.toString().length()-4,reigistrationNumber.toString().length()))){
					addFieldError("reigistrationNumber","Registration No must end with four digits");
					}
				if (!commonvalid.isSpecialChar(reigistrationNumber)) {
					addFieldError("reigistrationNumber","Spical characters are not allowed");
				
					
				}
			}
			/*else if (!cmn.isSpecialChar(reigistrationNumber.trim())){
				addFieldError("reigistrationNumber","Special Characters Not Allowed For Registration Number");
			}else if(reigistrationNumber.trim().indexOf("KA")!=0){
				addFieldError("reigistrationNumber","Registartion Number Should Start with KA");
				
			}else if(reigistrationNumber.trim().equals("KA")){
				addFieldError("reigistrationNumber","Please enter registartion number after KA");
			}
			*/
			if (!commonvalid.isSpecialChar(progressingRunningKms)) {
				addFieldError("progressingRunningKms","Special Character For Progressive Running Kms is Not Allowed");
				//this.cashRemittanceVoucher.setBank_name(this.cashRemittanceVoucher.getBank_name());
				
			}
			if (!commonvalid.isSpecialChar(progressingScheduleKms)) {
				addFieldError("progressingScheduleKms","Special Character For Progressive Schedule Kms is Not Allowed");
				//this.cashRemittanceVoucher.setBank_name(this.cashRemittanceVoucher.getBank_name());
				
			}

			
			/*if(progressingRunningKms!=null&&!progressingRunningKms.trim().equals("")){
				if(!Pattern.matches("\\d.*", progressingRunningKms)){
					addFieldError("progressingRunningKms","Special Character For Progressive Running Kms is Not Allowed");
				}
			}*/
			
			/*if(progressingScheduleKms!=null&&!progressingScheduleKms.trim().equals("")){
				if(!Pattern.matches("\\d.*", progressingScheduleKms)){
					addFieldError("progressingScheduleKms","Special Character For Progressive Schedule Kms is Not Allowed");
				}
			}*/
			/*if (progressingRunningKms == null || progressingRunningKms.equals("")) {
				addFieldError("progressingKms",
						"Progressing Kms cant be empty");
			}*/
			
			if(unitType==0){
				addFieldError("org_type_id","Please Select Organization Type");
			}
			
			if(orgChartId==0){
				addFieldError("orgName","Please Select Organization Unit Name");
			}
			
			if(brandTypeId==0){
				addFieldError("brandTypeId","Please Select Brand type ");
			}
			
			if(acAvailability==null || acAvailability.equals("")){
				addFieldError("acAvailability","Please Select AC Aialability");
			}
			System.out.println("-------------in validate method middle-----------");
			
			if(makeTypeId==0 ){
				addFieldError("makeTypeId",	"Please Select Make Type");
			}
			
			
			if(modelTypeId==0 ){
				addFieldError("modelTypeId","Please Select Model Type");
			}
			if(vehicleTypeId==0 ){
				addFieldError("vehicleTypeId","Please Select vehicle Type");
			}
			
			if(fcRenewalDate==null || fcRenewalDate.equals("")){
				addFieldError("fcRenewalDate",	"Please Select Procured Date ");
			}
//			else  if(!(common.compareDates(procuredDate.toString(), fcExpiryDate.toString())>0)){
//				addFieldError("proceduredDate",	"Procured Date Should be greater than Fc Expiry date ");
//			}
			
			if(registrationDate==null||registrationDate.equals("")){
				addFieldError("registrationDate","Please select Registration Date");
			}/*else if(!(common.compareDates(registrationDate.toString(),procuredDate.toString())>0)){
				addFieldError("registrationDate","Registration Date Should be graeter than Procured Date");
			}*/
			
			if(operationalDate==null||operationalDate.equals("")){
				addFieldError("operationalDate","Please Select Operational Date ");
			}else if(!(common.compareDates(operationalDate.toString(),registrationDate.toString() )>0)){
				addFieldError("operationalDate","Operational Date Should be greater than Registration Date ");
			}
			if(makeTypeId==9){
				System.out.println("Enter into if()");
			}else{
		/*	if(dockingPlanningDate==null||dockingPlanningDate.equals("")){
				addFieldError("dockingPlanningDate","Please Select Docking Planning date");
			}else if(!(common.compareDates(dockingPlanningDate.toString(),operationalDate.toString())>0)){
				addFieldError("dockingPlanningDate","Docking Planning  Date Should be greater than Operational Date ");
			}*/
			}
			if(makeTypeId==9){
				System.out.println("Enter into if()");

			}
		/*	if (fcExpiryDate == null|| fcExpiryDate.trim().equals("")) {
				addFieldError("fcExpiryDate","Please Enter FC Expiry Date");
			}else if(!(common.compareDates(fcExpiryDate.toString(),dockingPlanningDate.toString())>0)){
				addFieldError("fcExpiryDate","Fc Expiry Date Should be greater than  Docking Planning Date ");
			}*/
			

			/*if(bodyTypeId==0){
				addFieldError("bodyTypeId","Please Select Body Type");
			}*/
			
			/*if(serviceTypeId==0){
				addFieldError("serviceTypeId","Please Select Service Type ");
			}*/
			
			/*if(procuredDate==null||procuredDate.equals("")){
				addFieldError("proceduredDate","Please Select Procured Date  ");
			}*/
			
			/*if(chasisNumber==null||chasisNumber.equals("")){
				addFieldError("chassisNumber","Please enter Chasis Number");
			}
			else if(!cmn.isSpecialChar(chasisNumber.trim()) ){
				addFieldError("chassisNumber","Special Characters Not Allowed For Chasis Number");
			}
			*/
			if(vehicleUsageCatageryId==0 ){
				addFieldError("vehicleUsageCatageryId","Please Select Vehicle Category Type ");
			}
			if(chasisNumber==null||chasisNumber.trim().equals("") ){
				addFieldError("chasisNumber","Chassis Number  cant be empty");
			}
			if (!commonvalid.isSpecialChar(chasisNumber)) {
				addFieldError("chasisNumber","Special Character For Chassis number is Not Allowed");
				//this.vehicle.setChasisNumber(this.vehicle.getChasisNumber());
				if(floorrHeigt.equals(0) ){
					addFieldError("floorHeigt","Please Select floor Heigt Type ");
				}
			}
			if(engineNumber==null||engineNumber.trim().equals("") ){
				addFieldError("engineNumber","Engine Number  cant be empty");
			}
			if (!commonvalid.isSpecialChar(engineNumber)) {
				addFieldError("engineNumber","Special Character For Engine number is Not Allowed");
				//this.vehicle.setChasisNumber(this.vehicle.getChasisNumber());
				
			}
System.out.println("----------entering new validate");
if(dockingklms.trim().equals("")){
	addFieldError("dockingklms","Please Enter Docking kms");
}
			if(!Pattern.matches("\\d*",dockingklms)){
				addFieldError("dockingklms","Please Enter Digits Only");
			}
				if(norm.equals("0") ){
					addFieldError("norm","Please Select Norm Type ");
				}
				//System.out.println("florr---------------"+floorrHeigt+"--"+hp+norm.equals(0));
				//System.out.println(Integer.parseInt(floorrHeigt)==0);

				if(floorrHeigt.equals("0") ){
					addFieldError("floorrHeigt","Please Select floor Heigt Type ");
				}
				if(hp.equals("0") ){
					addFieldError("hp","Please Select hp Type ");
				}
				if(seat_Capacity.equals("0") ){
					addFieldError("seat_Capacity","Please Select Seat Capacity Type ");
				}
				if(UnladenWeight.equals("0") ){
					addFieldError("UnladenWeight","Please Select UnladenWeight Type ");
				}
				
			/*if(chasisNumber.contains("") ){
				addFieldError("chasisNumber","Space For Chassis number is Not Allowed");
			}*/
			/*else if(!Pattern.matches("\\d*", chasisNumber)){
				addFieldError("chasisNumber","Incorrect value for chassisNumber");
			}*/
			if(wheelCount==null || wheelCount==null){
				addFieldError("wheelCount",	"Please enter No Of Wheels");
			}
			else if(wheelCount!=null){
				if(!Pattern.matches("\\d*", wheelCount.toString())){
					addFieldError("wheelCount",	"Incorrect value for No Of Wheels");
					}
			}
		}
		}catch (Exception e) {
		e.printStackTrace();
		}
		if(hasFieldErrors()){
			getAllList();
		}
	}
	public static void main(String args[]){
		CreateVehicleAction createObject = new CreateVehicleAction();
		
		System.out.print(createObject.isValidateRegistrationNumber("KA-5-jjjj"));
		}
	
//	public boolean isAlphaNumric(String  checkingString){
//		boolean isAlphaNumaric = true;
//		
//		for(int i=0;i<checkingString.length();i++){
//			if(!Pattern.matches("^[a-zA-Z0-9]", ""+checkingString.charAt(i))){
//				isAlphaNumaric = false;
//			}
//		}
//		return isAlphaNumaric;
//		
//	}
//	public boolean isAlphaNumricAndSpace(String  checkingString){
//		boolean isAlphaNumaric = true;
//		
//		for(int i=0;i<checkingString.length();i++){
//			if(!Pattern.matches("^[a-zA-Z0-9 ]", ""+checkingString.charAt(i))){
//				isAlphaNumaric = false;
//			}
//		}
//		return isAlphaNumaric;
//		
//	}
	public boolean isNumeric(String  checkingString){
		boolean isAlphaNumaric = true;
		
		for(int i=0;i<checkingString.length();i++){
			if(!Pattern.matches("^[0-9]", ""+checkingString.charAt(i))){
				isAlphaNumaric = false;
			}
		}
		return isAlphaNumaric;
		
	}
	public boolean isAlphabetical(String  checkingString){
		boolean isAlphaNumaric = true;
		
		for(int i=0;i<checkingString.length();i++){
			if(!Pattern.matches("^[a-zA-Z]", ""+checkingString.charAt(i))){
				isAlphaNumaric = false;
			}
		}
		return isAlphaNumaric;
		
	}
	public boolean isValidateRegistrationNumber(String licenceNumber)
	{
		boolean isCorrect = true;
		
		if(licenceNumber.contains("-")){
			String licenceNumArray[] = licenceNumber.split("-");
			if(licenceNumber.length()>=5 ){
				String firstPartOfLicence = licenceNumArray[0];
				if(firstPartOfLicence.length()==4){
					if(!isAlphabetical(firstPartOfLicence.substring(0,2))){
						isCorrect = false;
					}
					if(!isNumeric(firstPartOfLicence.substring(2,firstPartOfLicence.length()))){
						isCorrect = false;	
					}else{
						isCorrect = true;
					}
				}
				else{
					isCorrect = false;
				}
			}
			else{
				isCorrect  = false;
			}
		}
		else{
			isCorrect = false;
		}
		return isCorrect;
		 
/*
 * 
 * 
 ************************************   "KAXX-XX-XXXX" ***************************************************************************************
 ************************************   "KAXX-XX-XXXX" ***************************************************************************************
 ************************************   "KAXX-XX-XXXX" ***************************************************************************************
 
  		boolean isCorrect = true;
		
		// validFormat = "KAXX-XX-XXXX";
		
		 String licenceNumArray[] = licenceNumber.split("-");
  		 if(licenceNumber.length()<=12 && licenceNumArray.length==3){
 
			 String firstPartOfLicence = licenceNumArray[0];
	
			 if(firstPartOfLicence.length()<=4){
				if(firstPartOfLicence.length()>=2){
					if(!(firstPartOfLicence.substring(0,2).equals("KA"))){
						isCorrect = false;
					}
					if(!isAlphaNumric(firstPartOfLicence.substring(2,firstPartOfLicence.length()))){
						isCorrect = false;	
					}
				}else{
					isCorrect = false;
				}
			 }
			 else{
				 isCorrect = false;
			 }
			
			String secondPartOfLicence = licenceNumArray[1];
				 
			if(secondPartOfLicence.length()<=2 && secondPartOfLicence.length()!=0){
				if(secondPartOfLicence.length()==1){
					if(!isAlphaNumricAndSpace(secondPartOfLicence.substring(0,secondPartOfLicence.length()))){
						isCorrect = false;	
					}
				}
				else{
					if(!isAlphaNumric(secondPartOfLicence.substring(0,secondPartOfLicence.length()))){
						isCorrect = false;	
					}
				}
				
				
			}
			else{
				isCorrect = false;
			}
			
			
			String thirdPartOfLicence = licenceNumArray[2];
			if(thirdPartOfLicence.length()<=4 && thirdPartOfLicence.length()!=0){
				if(!isAlphaNumric(thirdPartOfLicence.substring(0,thirdPartOfLicence.length()))){
					isCorrect = false;	
				}
			}
			else{
				isCorrect = false;
			}
		}
		 else{
			 isCorrect = false;
		 }
 
		 return isCorrect;
		*/		
		 
//***************************         KA-XX-XX-XXXX       *************************************************************************************
//***************************         KA-XX-XX-XXXX       *************************************************************************************	
//***************************         KA-XX-XX-XXXX       *************************************************************************************	
		 /*char validcharSeq[] = validFormat.toCharArray();
		char licenceNumberChars[] = licenceNumber.toCharArray(); 
		
		if(licenceNumberChars.length!=validcharSeq.length){
			isCorrect = false;	
		}else{
			for(int i=0;i<validcharSeq.length;i++){
				if(validcharSeq[i]!='X' && validcharSeq[i]!='A' && validcharSeq[i]!='-'){
					if(validcharSeq[i]!=licenceNumberChars[i]){
						isCorrect = false;
					}
				}
				else if(validcharSeq[i]=='K' && licenceNumberChars[i]!='K'){
					isCorrect = false;
				}
				else if(validcharSeq[i]=='-' && licenceNumberChars[i]!='-'){
						isCorrect = false;
				}
				else if(validcharSeq[i]=='A' && licenceNumberChars[i]!='A'){
					isCorrect = false;
				}
				else if(validcharSeq[i]=='X' && (!Pattern.matches("^[a-zA-Z0-9 ]", ""+licenceNumberChars[i]))){
					isCorrect = false;
				}
			}
		}
		return isCorrect;*/
		
	}
	
	/*public boolean fcCompare(String fcDate,String procDate,String operDate,String dockDate,String regDate) throws ParseException{
		boolean returnValue= false;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = sdf.parse(fcDate);
    	Date date2 = sdf.parse(procDate);
    	Date date3 = sdf.parse(operDate);
    	Date date4 = sdf.parse(dockDate);
    	Date date5 = sdf.parse(regDate);
    	
    	if(date2.compareTo(date1))
    	{
    		
    	}
		
		return returnValue;
	}*/

}
