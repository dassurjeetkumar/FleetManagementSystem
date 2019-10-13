package com.trimax.its.vehicle.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.transport.model.BusStops;

@Entity
@Table(name="breakdown_history")
public class BreakDownHistory {
	@Id 
	@GeneratedValue
	@Column(name="breakdown_id")
	private int breakdownId;
	public int getBreakdownId() {
		return breakdownId;
	}
	public void setBreakdownId(int breakdownId) {
		this.breakdownId = breakdownId;
	}
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name="breakdown_type_id")
	private BreakDownTypeDetails breakDownTypeDetails;
	public BreakDownTypeDetails getBreakDownTypeDetails() {
		return breakDownTypeDetails;
	}
	public void setBreakDownTypeDetails(BreakDownTypeDetails breakDownTypeDetails) {
		this.breakDownTypeDetails = breakDownTypeDetails;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	/*public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}*/
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
	/*@Column(name="vehicle_id")
	private int vehicleId;*/
	
	@Column(name="date") 
	private  String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	/*public void setDate(String date) {
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		// if(date.trim().length()>0){
		try {
			this.date =  sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	 }
	}*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	@Column(name="status")
	private String status;
	/*@Column(name="type")
	private String type;
*/	@Column(name="created_by")
	private int created_by;
	@Column(name="created_date") 
	private  Date created_date;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="updated_date")
	private  Date updated_date;
	@Column(name="deleted_status")
	private int deleted_status;
    
}
