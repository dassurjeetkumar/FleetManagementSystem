package com.trimax.its.pis.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="pis_scroll_message")
public class PisScrollMessageModel {
	@Id
	@GeneratedValue
	@Column(name="pis_id")
	private int pis_id;
	@ManyToOne
	@JoinColumn(name="bus_stand_id")
	private OrganisationChart busstand;
	@ManyToOne
	@JoinColumn(name="display_unit_id")
	private DisplayUnitModel scroll_display_unit;
	public OrganisationChart getBusstand() {
		return busstand;
	}
	public void setBusstand(OrganisationChart busstand) {
		this.busstand = busstand;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	/*@Column(name="display_unit_id")
	private int display_unit_id;*/
	@Column(name="message")
	private String message;
	@Column(name="font_color")
	private String font_color;
	@Column(name="background_color")
	private String background_color;
	public String getFont_color() {
		return font_color;
	}
	public void setFont_color(String font_color) {
		this.font_color = font_color;
	}
	public String getBackground_color() {
		return background_color;
	}
	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}
	@Column(name="status")
	private String status;
	@Column(name="created_by")
	private int created_by;
	@Column(name="deleted_status")
	private int deleted_status;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="created_date")
    private Date created_date;
	@Column(name="updated_date")
    private Date updated_date;
	@Column(name="effective_date")
    private String effective_date;
	@Column(name="end_date")
    private String end_date;
	public int getPis_id() {
		return pis_id;
	}
	public void setPis_id(int pis_id) {
		this.pis_id = pis_id;
	}
	
	/*public int getDisplay_unit_id() {
		return display_unit_id;
	}
	public void setDisplay_unit_id(int display_unit_id) {
		this.display_unit_id = display_unit_id;
	}*/
	public String getMessage() {
		return message;
	}
	public DisplayUnitModel getScroll_display_unit() {
		return scroll_display_unit;
	}
	public void setScroll_display_unit(DisplayUnitModel scroll_display_unit) {
		this.scroll_display_unit = scroll_display_unit;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public String getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}

}
