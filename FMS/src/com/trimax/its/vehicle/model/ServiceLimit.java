package com.trimax.its.vehicle.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="brand_service")
public class ServiceLimit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int brand_service_id ;
	
//	@Column(name="brand_type_id")
//	private String brand_type_id ;
	
	
	public int getBrand_service_id() {
		return brand_service_id;
	}

	public void setBrand_service_id(int brand_service_id) {
		this.brand_service_id = brand_service_id;
	}

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name = "brand_type_id")
	private BrandType brandtypeId;
	
	
	
	public BrandType getBrandtypeId() {
		return brandtypeId;
	}

	public void setBrandtypeId(BrandType brandtypeId) {
		this.brandtypeId = brandtypeId;
	}

	@Column(name="brand_type_name")
	private String brand_type_name ;
	
	

	public String getBrand_type_name() {
		return brand_type_name;
	}

	public void setBrand_type_name(String brand_type_name) {
		this.brand_type_name = brand_type_name;
	}


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	@Column(name="brand_service")
	private String service_limit ;

	public String getService_limit() {
		return service_limit;
	}

	public void setService_limit(String service_limit) {
		this.service_limit = service_limit;
	}

	@Column(name="note")
	private String note;
	
	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="created_date")
	private String created_date;

}
