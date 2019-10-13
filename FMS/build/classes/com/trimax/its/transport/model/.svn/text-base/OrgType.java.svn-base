package com.trimax.its.transport.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.TransferVehicleHistory;

@Entity
@Table(name="org_type")
public class OrgType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int org_type_id;
	
	@Column(name="org_type")
	String orgType;
	
	@Column(name="notes")
	String notes;
	
	@Column(name="status")
	String status;
	
	@Column(name="created_date")
	Date createdDate;
	
	@Column(name="created_by")
	int createdBy;
	
	@Column(name="updated_by")
	int updatedBy;
	
	@Column(name="updated_date")
	Date updatedDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgtype")
	private Set<Employee> employee;
	
		
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgType")
//	private Set<OrganisationChart> orgDetails;

//	public Set<OrganisationChart> getOrgDetails() {
//		return orgDetails;
//	}
//
//	public void setOrgDetails(Set<OrganisationChart> orgDetails) {
//		this.orgDetails = orgDetails;
//	}

	public int getOrg_type_id() {
		return org_type_id;
	}

	public void setOrg_type_id(int org_type_id) {
		this.org_type_id = org_type_id;
	}

	public String getOrgType() {
		return orgType.trim();
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType.trim();
	}

	public String getNotes() {
		return notes.trim();
	}

	public void setNotes(String notes) {
		this.notes = notes.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgType")
	private Set<OrganisationChart> depot;

	public Set<OrganisationChart> getDepot() {
		return depot;
	}

	public void setDepot(Set<OrganisationChart> depot) {
		this.depot = depot;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgtype")
	private Set<TicketBagMaster> ticketOrgabnizatuion;


	public Set<TicketBagMaster> getTicketOrgabnizatuion() {
		return ticketOrgabnizatuion;
	}

	public void setTicketOrgabnizatuion(Set<TicketBagMaster> ticketOrgabnizatuion) {
		this.ticketOrgabnizatuion = ticketOrgabnizatuion;
	}
	
	
	@OneToMany(mappedBy="orgTypeCash")
	private Set<CashRemittanceVoucher> orgTypeCashRemittanceVoucher;

	public Set<CashRemittanceVoucher> getOrgTypeCashRemittanceVoucher() {
		return orgTypeCashRemittanceVoucher;
	}

	public void setOrgTypeCashRemittanceVoucher(
			Set<CashRemittanceVoucher> orgTypeCashRemittanceVoucher) {
		this.orgTypeCashRemittanceVoucher = orgTypeCashRemittanceVoucher;
	}
	
	
	
}
