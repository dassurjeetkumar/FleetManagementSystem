package com.trimax.its.usermanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_transfer")
public class EmployeeTransfer {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="employee_id")
	private int employeeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getFromDepotId() {
		return fromDepotId;
	}
	public void setFromDepotId(int fromDepotId) {
		this.fromDepotId = fromDepotId;
	}
	public int getToDepotId() {
		return toDepotId;
	}
	public void setToDepotId(int toDepotId) {
		this.toDepotId = toDepotId;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	
	@Column(name="from_depot_id")
	private int fromDepotId;
	@Column(name="to_depot_id")
	private int toDepotId;
	@Column(name="updated_by")
	private int updateBy;
	@Column(name="updated_date")
	private Date updatedDate;
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
