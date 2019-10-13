package com.trimax.its.memo.model;

import java.util.Date;
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



@Entity
@Table(name="memo_type")
public class MemoType {

	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)	
private int memo_type_id;

@Column
private String memo_type_name;

@Column
private String status;

@Column
private String notes;

@Column
private int created_by;

@Column
private Date created_date;

@Column
private int updated_by;

@Column
private Date updated_date;

@Column
private int deleted_status;

public int getMemo_type_id() {
	return memo_type_id;
}

public void setMemo_type_id(int memo_type_id) {
	this.memo_type_id = memo_type_id;
}

public String getMemo_type_name() {
	return memo_type_name;
}

public void setMemo_type_name(String memo_type_name) {
	this.memo_type_name = memo_type_name;
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

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "memotype")
private Set<Memo> memo;

public Set<Memo> getMemo() {
	return memo;
}

public void setMemo(Set<Memo> memo) {
	this.memo = memo;
}




}
