package com.trimax.its.vehicle.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.its.transport.model.Schedule;

@Entity
@Table(name="service_tax")
public class ServiceTaxType {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int service_tax_id	;
	@Column
	private int tax_type_id;
	@Column
	private String tax_type_name;
	public int getTax_type_id() {
		return tax_type_id;
	}

	public void setTax_type_id(int tax_type_id) {
		this.tax_type_id = tax_type_id;
	}

	public String getTax_type_name() {
		return tax_type_name;
	}

	public void setTax_type_name(String tax_type_name) {
		this.tax_type_name = tax_type_name;
	}

	@Column
	private String service_type_name;
	@Column
	private int service_type_id;
	@Column
	private String ticket_type_name;
	public String getTicket_type_name() {
		return ticket_type_name;
	}

	public void setTicket_type_name(String ticket_type_name) {
		this.ticket_type_name = ticket_type_name;
	}

	public int getTicket_type_id() {
		return ticket_type_id;
	}

	public void setTicket_type_id(int ticket_type_id) {
		this.ticket_type_id = ticket_type_id;
	}

	@Column
	private int ticket_type_id;
	@Column
	private String base_value;
	public int getService_tax_id() {
		return service_tax_id;
	}

	public void setService_tax_id(int service_tax_id) {
		this.service_tax_id = service_tax_id;
	}

	public String getService_type_name() {
		return service_type_name;
	}

	public void setService_type_name(String service_type_name) {
		this.service_type_name = service_type_name;
	}

	public int getService_type_id() {
		return service_type_id;
	}

	public void setService_type_id(int service_type_id) {
		this.service_type_id = service_type_id;
	}

	public String getBase_value() {
		return base_value;
	}

	public void setBase_value(String base_value) {
		this.base_value = base_value;
	}

	public String getService_tax_percentage() {
		return service_tax_percentage;
	}

	public void setService_tax_percentage(String service_tax_percentage) {
		this.service_tax_percentage = service_tax_percentage;
	}

	public String getEffective_start_date() {
		return effective_start_date;
	}

	public void setEffective_start_date(String effective_start_date) {
		this.effective_start_date = effective_start_date;
	}

	public String getEffective_end_date() {
		return effective_end_date;
	}

	public void setEffective_end_date(String effective_end_date) {
		this.effective_end_date = effective_end_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	@Column
	private String service_tax_percentage;
	@Column
	private String effective_start_date;
	@Column
	private String effective_end_date;

	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="vehicleType")
//	private Set<Vehicle> vehicles;

	@Column
	private String status;
	
	
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_date") 
	private String updatedDate;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;

	
	
}
