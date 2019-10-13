package com.trimax.its.route.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="trunk_feeder_mapping")
public class TrunkFeederMapping {
	
	@Id
	@GeneratedValue
	@Column(name="trunk_feeder_id")
	private int trunk_feeder_id;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="trunk_route_id")
	private Route trunk_route;
	
	@ManyToOne/*(fetch = FetchType.LAZY)*/
	@JoinColumn(name="feeder_route_id")
	private Route feeder_route;

}
