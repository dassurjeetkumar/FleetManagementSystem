package com.trimax.its.cashremittancevoucher.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.transport.model.OrgType;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="cash_remittance_voucher")
public class CashRemittanceVoucher {
	@Id
	@GeneratedValue
	
	@Column(name="voucher_id")
	private int voucher_id;
	
	@Column(name="voucher_number")
	private String voucher_number;
	
	@Column(name="voucher_date")
	private String voucher_date;
	
	/*@Column(name="remitted_by")
	private String remitted_by;*/
	
	@Column(name="bank_name")
	private String bank_name;
	
	/*@Column(name="org_chart_id")
	private int org_unit_id;*/
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="status")
	private String status;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_by")
	private int updated_by;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="deleted_status")
	private int deleted_status;
	
	@ManyToOne
	@JoinColumn(name="org_chart_id")
	private OrganisationChart orgChartCash;
	
	@ManyToOne
	@JoinColumn(name="org_type_id")
	private OrgType orgTypeCash;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee employeeCash;

	public int getVoucher_id() {
		return voucher_id;
	}

	public void setVoucher_id(int voucher_id) {
		this.voucher_id = voucher_id;
	}

	public String getVoucher_number() {
		
		return voucher_number.trim();
	}

	public void setVoucher_number(String voucher_number) {
		
		this.voucher_number = voucher_number.trim();
	}
	

	/*public Date getVoucher_date() {
		return voucher_date;
	}

	public void setVoucher_date(Date voucher_date) {
		this.voucher_date = voucher_date;
	}*/

	public String getVoucher_date() {
		return voucher_date;
	}

	public void setVoucher_date(String voucher_date) {
		this.voucher_date = voucher_date;
	}

	/*public int getRemitted_by() {
		return remitted_by;
	}

	public void setRemitted_by(int remitted_by) {
		this.remitted_by = remitted_by;
	}*/
	

	public String getBank_name() {
		return bank_name.trim();
	}

	/*public String getRemitted_by() {
		return remitted_by;
	}

	public void setRemitted_by(String remitted_by) {
		this.remitted_by = remitted_by;
	}*/

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name.trim();
	}

	/*public int getOrg_unit_id() {
		return org_unit_id;
	}

	public void setOrg_unit_id(int org_unit_id) {
		this.org_unit_id = org_unit_id;
	}*/

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}

	public OrganisationChart getOrgChartCash() {
		return orgChartCash;
	}

	public void setOrgChartCash(OrganisationChart orgChartCash) {
		this.orgChartCash = orgChartCash;
	}

	public OrgType getOrgTypeCash() {
		return orgTypeCash;
	}

	public void setOrgTypeCash(OrgType orgTypeCash) {
		this.orgTypeCash = orgTypeCash;
	}

	public Employee getEmployeeCash() {
		return employeeCash;
	}

	public void setEmployeeCash(Employee employeeCash) {
		this.employeeCash = employeeCash;
	}
	
	

}
