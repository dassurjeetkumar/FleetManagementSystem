package com.trimax.its.componenttype.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="components_type")
public class ComponentType {
		@Id
		@GeneratedValue
		
		@Column(name="component_type_id")
		private int component_type_id;
		
		@Column(name="component_name")
		private String component_type_name;
		
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

		public int getComponent_type_id() {
			return component_type_id;
		}

		public void setComponent_type_id(int component_type_id) {
			this.component_type_id = component_type_id;
		}

		public String getComponent_type_name() {
			return component_type_name.trim();
		}

		public void setComponent_type_name(String component_type_name) {
			this.component_type_name = component_type_name.trim();
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
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
		
		
}
