package com.trimax.its.memo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.vehicle.model.ServiceType;

@Entity
@Table(name="memo")
public class Memo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int memo_id;
	@Column
	private String status;
	@Column
	private String notes;
	@Column
	private int created_by;
	
	@Column
	private String date;
	
	@Column
	private Date created_date;

	@Column
	private int updated_by;


	@Column
	private Date updated_date;

	@Column
	private int deleted_status;
	
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getMemo_id() {
		return memo_id;
	}


	public void setMemo_id(int memo_id) {
		this.memo_id = memo_id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public int getCreated_by() {
		return created_by;
	}


	public void setCreated_by(int created_by) {
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


	


	
	
	@ManyToOne
	@JoinColumn(name="memo_type_id")
	private  MemoType memotype;
	public MemoType getMemotype() {
		return memotype;
	}


	public void setMemotype(MemoType memotype) {
		this.memotype = memotype;
	}
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private  Employee user;


	public Employee getUser() {
		return user;
	}


	public void setUser(Employee user) {
		this.user = user;
	}

	
	

}
