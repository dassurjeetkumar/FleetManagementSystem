package com.trimax.its.vehicle.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.trimax.its.common.Common;
import com.trimax.its.device.model.Device;
import com.trimax.its.vehicle.action.ScrapVehicleAction;
import com.trimax.its.vts.model.SarthiSectorGeofence;
import com.trimax.its.vts.model.SectorVehicleRelation;

@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Transient
	Common common = new Common();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vehicle_id")
	private int vehicleId;

	@Column(name = "license_number")
	private String vehicleRegistrationNumber;
	//
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "make_type_id")
	private MakeType makeType;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "org_chart_id")
	private OrganisationChart depotOrUnit;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "brand_type_id")
	private BrandType brandType;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "vehicle_type_id",nullable=true)
	private VehicleType vehicleType;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "model_type_id")
	private ModelType modelType;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "service_type_id")
	private ServiceType serviceType;
	
	
	/*@ManyToOne
	@JoinColumn(name = "vehicle")
	private BreakDownType breakDownType;*/

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "body_type_id")
	private BodyType bodyType;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "vehicle_usage_category")
	private VehicleCategory vehicleCategory;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private Set<BreakDownHistory> breakdownhistory;
	
	public Set<BreakDownHistory> getBreakdownhistory() {
		return breakdownhistory;
	}

	public void setBreakdownhistory(Set<BreakDownHistory> breakdownhistory) {
		this.breakdownhistory = breakdownhistory;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private Set<TransferVehicleHistory> transferVehicles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private Set<VehicleFcRenewal> fcVehicles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private Set<Fuel> fuleVehicle;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private Set<DockingHistory> dockingVehicle;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="scrapVehicle")
	private Set<VehicleScrap> vehicleScrap;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="vehicleDetails")
	private Set<VehicleDevice> deviceMappingVehicle;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private Set<SectorVehicleRelation> sectorRelation;

	@Column(name="registration_date")
	private Date registrationDate;

	@Column(name = "fc_expiry_date")
	private Date fcExpiryDate;
	@Column(name = "fc_renewal_date")
	private Date fcRenewalDate;

	public Date getFcRenewalDate() {
		return fcRenewalDate;
	}

	public void setFcRenewalDate(Date fcRenewalDate) {
		this.fcRenewalDate = fcRenewalDate;
	}


	@Column(name = "running_kms")
	private String progressingKMs;
	
	@Column(name = "schedule_kms")
	private String scheduleKMs;

	@Column(name = "ac_availability")
	private String acAvailability;

	@Column(name = "procured_date", nullable = true)
	private Date proceduredDate;

	@Column(name = "operational_date")
	private Date operationalDate;

	/*@Column(name = "vehicle_usage_category")
	private String vehicleUsageCategory;*/

	@Column(name = "number_of_wheels")
	private Integer wheelsCount;

	@Column(name = "chasis_number")
	private String chasisNumber;

	@Column(name = "status")
	private String status;

	@Column(name = "scrapped")
	private String isScarpped;

	@Column(name = "scrapped_date")
	private Date scrappedDate;

	@Column(name = "docking_planning_date")
	private Date dockingPlanningDate;

	@Column(name = "under_warranty")
	private String underWarranty;

	@Column(name = "kmpl")
	private String kmpl;

	@Column(nullable = true)
	private String updated_date;

	@Column(nullable = true)
	private Integer updated_by;

	@Column
	private Integer deleted_status;

	@Column
	private String created_date;




	@Column
	private Integer created_by;
	@Column(name="engine_no")
	private String engineNumber;
	@Column(name="norm")
	private String norm;
	@Column(name="floor_height")
	private String floorrHeigt;
	@Column(name="unladen_weight")
	private String UnladenWeight;
	public String getUnladenWeight() {
		return UnladenWeight;
	}

	public void setUnladenWeight(String unladenWeight) {
		UnladenWeight = unladenWeight;
	}

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

	public String getDocking_Kms() {
		return docking_Kms;
	}

	public void setDocking_Kms(String docking_Kms) {
		this.docking_Kms = docking_Kms;
	}


	@Column(name="hp")
	private String hp;
	@Column(name="seat_capacity")
	private String seat_Capacity;
	@Column(name="docking_kms")
	private String docking_Kms;
	
	
	
	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}


	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "vtu_id",nullable=true)
	private Device vtuDevice;

	@Transient
	private String FcExpiryDateString;
	@Transient
	private String FcRenewalDateString;
	
	public String getFcRenewalDateString() {
		return FcRenewalDateString;
	}

	public void setFcRenewalDateString(String fcRenewalDateString) {
		FcRenewalDateString = fcRenewalDateString;
	}


	@Transient
	private String operationalDateString;
	
	@Transient
	private String procuredDateString;

	@Transient
	private String registrationDateString;
	
	
	@Transient
	private String dockingPlanningDateString;
	
	@Transient
	private String scrappedDateString;
	
	@Column(name="effective_end_date")
	private Date effective_end_date;
	
	@Column(name="cause_status")
	private String cause_status;
	
	
	
	public String getCause_status() {
		return cause_status;
	}

	public void setCause_status(String cause_status) {
		this.cause_status = cause_status;
	}

	public Date getEffective_end_date() {
		return effective_end_date;
	}

	public void setEffective_end_date(Date effective_end_date) {
		this.effective_end_date = effective_end_date;
	}

	public int getVehicleId() {
		return vehicleId;
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	 private boolean isEmpty(String str) {
		 
	        return str == null ? true:(str.equals("") ? true:false);
	    }
	

	/*public void setVehicleId(String vehicleId) {
		if(isEmpty(vehicleId))
		{
			this.vehicleId = 0;
			
		}
		else if(isInteger(vehicleId))
		{
			this.vehicleId = Integer.parseInt(vehicleId);
		
	    }
		else
		{
			this.vehicleId=-1;
		}
		
	}
*/
	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber.trim();
	}

	public Common getCommon() {
		return common;
	}

	public void setCommon(Common common) {
		this.common = common;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber.trim();
	}

	public OrganisationChart getDepotOrUnit() {
		return depotOrUnit;
	}

	public void setDepotOrUnit(OrganisationChart depotOrUnit) {
		this.depotOrUnit = depotOrUnit;
	}

	public MakeType getMakeType() {
		return makeType;
	}

	public void setMakeType(MakeType makeType) {
		this.makeType = makeType;
	}

	

	public Date getFcExpiryDate() {
		return fcExpiryDate;
	}

	public void setFcExpiryDate(Date fcExpiryDate) {
		this.fcExpiryDate = fcExpiryDate;
	}

	public String getProgressingKMs() {
		return progressingKMs;
	}
	
	

	public String getScheduleKMs() {
		return scheduleKMs;
	}

	public void setScheduleKMs(String scheduleKMs) {
		if (scheduleKMs.trim().length() <= 0) {
			scheduleKMs = "0";
		}
		this.scheduleKMs = scheduleKMs;
	}

	public void setProgressingKMs(String progressingKMs) {
		if (progressingKMs.trim().length() <= 0) {
			progressingKMs = "0";
		}
		this.progressingKMs = progressingKMs;
	}

	public String getAcAvailability() {
		return acAvailability;
	}

	public void setAcAvailability(String acAvailability) {
		this.acAvailability = acAvailability;
	}

	public Date getProceduredDate() {
		
		return proceduredDate;
	}

	public void setProceduredDate(Date proceduredDate) {
		/*if (proceduredDate.trim().length() <= 0) {
			proceduredDate = "0000-00-00";
		} */
		this.proceduredDate = proceduredDate;
	}

	

	/*public String getVehicleUsageCategory() {
		return vehicleUsageCategory;
	}

	public void setVehicleUsageCategory(String vehicleUsageCategory) {
		this.vehicleUsageCategory = vehicleUsageCategory;
	}*/

	
	public Date getOperationalDate() {
		return operationalDate;
	}

	public void setOperationalDate(Date operationalDate) {
		this.operationalDate = operationalDate;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public VehicleCategory getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(VehicleCategory vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}

	public BrandType getBrandType() {
		return brandType;
	}

	public void setBrandType(BrandType brandType) {
		this.brandType = brandType;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public ModelType getModelType() {
		return modelType;
	}

	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
	}

	public Device getVtuDevice() {
		return vtuDevice;
	}

	public void setVtuDevice(Device vtuDevice) {
		this.vtuDevice = vtuDevice;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Integer getWheelsCount() {
		return wheelsCount;
	}

	public void setWheelsCount(Integer wheelsCount) {
		this.wheelsCount = wheelsCount;
	}

	public String getChasisNumber() {
		return chasisNumber;
	}

	public void setChasisNumber(String chasisNumber) {
		this.chasisNumber = chasisNumber;
	}

	public String getStatus() {
		if(status!=null){
			status = status.toUpperCase();
		}
		return status;
	}

	public void setStatus(String status) {
		if(status!=null){
			status = status.toUpperCase();
		}
		this.status = status;
	}

	public String getIsScarpped() {
		return isScarpped;
	}

	public void setIsScarpped(String isScarpped) {
		this.isScarpped = isScarpped;
	}

	
	public Date getScrappedDate() {
		return scrappedDate;
	}

	public void setScrappedDate(Date scrappedDate) {
		this.scrappedDate = scrappedDate;
	}

	public Date getDockingPlanningDate() {
		return dockingPlanningDate;
	}

	public void setDockingPlanningDate(Date dockingPlanningDate) {
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

		if (kmpl.trim().length() <= 0) {
			kmpl = "0";
		}
		this.kmpl = kmpl;
	}

	public String getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
				/*(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
				.format(new Date());*/
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	
	
	public Integer getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(Integer deleted_status) {
		this.deleted_status = deleted_status;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Set<TransferVehicleHistory> getTransferVehicles() {
		return transferVehicles;
	}

	public void setTransferVehicles(Set<TransferVehicleHistory> transferVehicles) {
		this.transferVehicles = transferVehicles;
	}

	public Set<VehicleFcRenewal> getFcVehicles() {
		return fcVehicles;
	}

	public void setFcVehicles(Set<VehicleFcRenewal> fcVehicles) {
		this.fcVehicles = fcVehicles;
	}

	public Set<Fuel> getFuleVehicle() {
		return fuleVehicle;
	}

	public void setFuleVehicle(Set<Fuel> fuleVehicle) {
		this.fuleVehicle = fuleVehicle;
	}

	public Set<DockingHistory> getDockingVehicle() {
		return dockingVehicle;
	}

	public void setDockingVehicle(Set<DockingHistory> dockingVehicle) {
		this.dockingVehicle = dockingVehicle;
	}

	public void setEditedVehicleId(int vehicleId2) {
		// TODO Auto-generated method stub

	}

	public String getFcExpiryDateString() {
		return FcExpiryDateString;
	}

	public void setFcExpiryDateString(String fcExpiryDateString) {
		FcExpiryDateString = fcExpiryDateString;
	}

	public String getOperationalDateString() {
		return operationalDateString;
	}

	public void setOperationalDateString(String operationalDateString) {
		this.operationalDateString = operationalDateString;
	}

	public String getProcuredDateString() {
		return procuredDateString;
	}

	public void setProcuredDateString(String procuredDateString) {
		this.procuredDateString = procuredDateString;
	}

	public String getRegistrationDateString() {
		return registrationDateString;
	}

	public void setRegistrationDateString(String registrationDateString) {
		this.registrationDateString = registrationDateString;
	}

	public String getDockingPlanningDateString() {
		return dockingPlanningDateString;
	}

	public void setDockingPlanningDateString(String dockingPlanningDateString) {
		this.dockingPlanningDateString = dockingPlanningDateString;
	}

	public String getScrappedDateString() {
		return scrappedDateString;
	}

	public void setScrappedDateString(String scrappedDateString) {
		this.scrappedDateString = scrappedDateString;
	}

	public Set<VehicleScrap> getVehicleScrap() {
		return vehicleScrap;
	}

	public void setVehicleScrap(Set<VehicleScrap> vehicleScrap) {
		this.vehicleScrap = vehicleScrap;
	}

	public Set<VehicleDevice> getDeviceMappingVehicle() {
		return deviceMappingVehicle;
	}

	public void setDeviceMappingVehicle(Set<VehicleDevice> deviceMappingVehicle) {
		this.deviceMappingVehicle = deviceMappingVehicle;
	}

	
//	Start Archana
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
//	private Set<VehicleDefects> vehicledefect;
//
//	public Set<VehicleDefects> getVehicledefect() {
//		return vehicledefect;
//	}
//
//	public void setVehicledefect(Set<VehicleDefects> vehicledefect) {
//		this.vehicledefect = vehicledefect;
//	}
//	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicle")
	private Set<VehicleMaintenance> vehiclemaintenance;
 
	public Set<VehicleMaintenance> getVehiclemaintenance() {
		return vehiclemaintenance;
	}

	public void setVehiclemaintenance(Set<VehicleMaintenance> vehiclemaintenance) {
		this.vehiclemaintenance = vehiclemaintenance;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vehicle [common=");
		builder.append(common);
		builder.append(", vehicleId=");
		builder.append(vehicleId);
		builder.append(", vehicleRegistrationNumber=");
		builder.append(vehicleRegistrationNumber);
		builder.append(", makeType=");
		builder.append(makeType);
		builder.append(", depotOrUnit=");
		builder.append(depotOrUnit);
		builder.append(", brandType=");
		builder.append(brandType);
		builder.append(", vehicleType=");
		builder.append(vehicleType);
		builder.append(", modelType=");
		builder.append(modelType);
		builder.append(", serviceType=");
		builder.append(serviceType);
		builder.append(", bodyType=");
		builder.append(bodyType);
		builder.append(", vehicleCategory=");
		builder.append(vehicleCategory);
		builder.append(", breakdownhistory=");
		builder.append(breakdownhistory);
		builder.append(", transferVehicles=");
		builder.append(transferVehicles);
		builder.append(", fcVehicles=");
		builder.append(fcVehicles);
		builder.append(", fuleVehicle=");
		builder.append(fuleVehicle);
		builder.append(", dockingVehicle=");
		builder.append(dockingVehicle);
		builder.append(", vehicleScrap=");
		builder.append(vehicleScrap);
		builder.append(", deviceMappingVehicle=");
		builder.append(deviceMappingVehicle);
		builder.append(", registrationDate=");
		builder.append(registrationDate);
		builder.append(", fcExpiryDate=");
		builder.append(fcExpiryDate);
		builder.append(", progressingKMs=");
		builder.append(progressingKMs);
		builder.append(", scheduleKMs=");
		builder.append(scheduleKMs);
		builder.append(", acAvailability=");
		builder.append(acAvailability);
		builder.append(", proceduredDate=");
		builder.append(proceduredDate);
		builder.append(", operationalDate=");
		builder.append(operationalDate);
		builder.append(", wheelsCount=");
		builder.append(wheelsCount);
		builder.append(", chasisNumber=");
		builder.append(chasisNumber);
		builder.append(", status=");
		builder.append(status);
		builder.append(", isScarpped=");
		builder.append(isScarpped);
		builder.append(", scrappedDate=");
		builder.append(scrappedDate);
		builder.append(", dockingPlanningDate=");
		builder.append(dockingPlanningDate);
		builder.append(", underWarranty=");
		builder.append(underWarranty);
		builder.append(", kmpl=");
		builder.append(kmpl);
		builder.append(", updated_date=");
		builder.append(updated_date);
		builder.append(", updated_by=");
		builder.append(updated_by);
		builder.append(", deleted_status=");
		builder.append(deleted_status);
		builder.append(", created_date=");
		builder.append(created_date);
		builder.append(", created_by=");
		builder.append(created_by);
		builder.append(", vtuDevice=");
		builder.append(vtuDevice);
		builder.append(", FcExpiryDateString=");
		builder.append(FcExpiryDateString);
		builder.append(", operationalDateString=");
		builder.append(operationalDateString);
		builder.append(", procuredDateString=");
		builder.append(procuredDateString);
		builder.append(", registrationDateString=");
		builder.append(registrationDateString);
		builder.append(", dockingPlanningDateString=");
		builder.append(dockingPlanningDateString);
		builder.append(", scrappedDateString=");
		builder.append(scrappedDateString);
		//builder.append(", vehicledefect=");
		//builder.append(vehicledefect);
		builder.append(", vehiclemaintenance=");
		builder.append(vehiclemaintenance);
		builder.append("]");
		return builder.toString();
	}

	public Set<SectorVehicleRelation> getSectorRelation() {
		return sectorRelation;
	}

	public void setSectorRelation(Set<SectorVehicleRelation> sectorRelation) {
		this.sectorRelation = sectorRelation;
	}

	/*public Set<SarthiSectorGeofence> getSarthiGeofence() {
		return sarthiGeofence;
	}

	public void setSarthiGeofence(Set<SarthiSectorGeofence> sarthiGeofence) {
		this.sarthiGeofence = sarthiGeofence;
	}
	*/

	
	

// End Archana	
	
}
