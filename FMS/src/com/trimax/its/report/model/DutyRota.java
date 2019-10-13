package com.trimax.its.report.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name="dutyrota")
	public class DutyRota {

		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="dutyrota_id")
		private int dutyrota_id;
		
		@Column(name="emd_id")
		private String emd_id;
		@Column(name="duty_type")
		private String duty_type;
		@Column(name="section")
		private String section;
		@Column(name="batch")
		private String batch;
		public String getBatch() {
			return batch;
		}

		public void setBatch(String batch) {
			this.batch = batch;
		}

		public String getSection() {
			return section;
		}

		public void setSection(String section) {
			this.section = section;
		}

		@Column(name="duty_type1")
		private String duty_type1;
		public String getDuty_type1() {
			return duty_type1;
		}

		public void setDuty_type1(String duty_type1) {
			this.duty_type1 = duty_type1;
		}

		@Column(name="wo_1")
		private String wo1;
		@Column(name="wo_2")
		private String wo2;
		
		@Column(name="effective_start_date")
		private String effective_start_date;
		
		@Column(name="effective_end_date")
		private String effective_end_date;
		
		@Column(name="updated_by")
		Integer updated_by;
		@Column(name="created_by")
		Integer created_by;
		
		@Column(name="updated_date")
		private Date updatedDate;
		@Column(name="created_date")
		private Date created_date;
		
		@Column(name="deleted_status")
		private int deletedStatus;
		
	
		public int getDutyrota_id() {
			return dutyrota_id;
		}

		public void setDutyrota_id(int dutyrota_id) {
			this.dutyrota_id = dutyrota_id;
		}

		public String getEmd_id() {
			return emd_id;
		}

		public void setEmd_id(String emd_id) {
			this.emd_id = emd_id;
		}

		public String getDuty_type() {
			return duty_type;
		}

		public void setDuty_type(String duty_type) {
			this.duty_type = duty_type;
		}

		public String getWo1() {
			return wo1;
		}

		public void setWo1(String wo1) {
			this.wo1 = wo1;
		}

		public String getWo2() {
			return wo2;
		}

		public void setWo2(String wo2) {
			this.wo2 = wo2;
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

		public Integer getUpdated_by() {
			return updated_by;
		}

		public void setUpdated_by(Integer updated_by) {
			this.updated_by = updated_by;
		}

		public Integer getCreated_by() {
			return created_by;
		}

		public void setCreated_by(Integer created_by) {
			this.created_by = created_by;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public Date getCreated_date() {
			return created_date;
		}

		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}

		public int getDeletedStatus() {
			return deletedStatus;
		}

		public void setDeletedStatus(int deletedStatus) {
			this.deletedStatus = deletedStatus;
		}

		
}
