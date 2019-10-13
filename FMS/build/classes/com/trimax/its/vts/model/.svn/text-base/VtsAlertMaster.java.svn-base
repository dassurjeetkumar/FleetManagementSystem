package com.trimax.its.vts.model;

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


 

public class VtsAlertMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int vts_id;

	

	@Column(name = "PACKET_CODE")
	private String packet_code;

	
	@Column(name = "ALERT_SHORT_CODE")
	private String alert_short_code;

	@Column(name = "MISC_BYTES")
	private String misc_bytes;
	
	@Column(name = "ALERT_DESC")
	private String alert_desc;

	@Column(name = "ADDITIONAL_INFO_1")
	private String additional_info_1;
	
	@Column(name = "ADDITIONAL_INFO_2")
	private String additional_info_2;
	
	@Column(name = "ADDITIONAL_INFO_3")
	private String additional_info_3;
	
	
	@Column(name = "RECORD_STATUS")
    private String record_status;
	
	@Column(name = "CREATED_DATE")
	private Date created_date;
	
	@Column(name = "DASHBOARD_VIEW")
    private Integer dashboard_view;

	@Column(name = "CREATED_BY")
	private Integer created_by;

	@Column(name = "UPDATED_DATE")
	private Date updated_date;
	
	@Column(name = "UPDATED_BY")
	private Integer updated_by;
	
	@Column(name = "INFO_MODE")
    private String infomode;

	public void setInfomode(String infomode) {
		this.infomode = infomode;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="vtsalertmaster")
	private Set<VehicleAlertConfig> vtsalertconfig;
	
	public int getVts_id() {
		return vts_id;
	}
	public void setVts_id(int vts_id) {
		this.vts_id = vts_id;
	}
	
	
	public Set<VehicleAlertConfig> getVtsalertconfig() {
		return vtsalertconfig;
	}
	public void setVtsalertconfig(Set<VehicleAlertConfig> vtsalertconfig) {
		this.vtsalertconfig = vtsalertconfig;
	}
	
	
	
	
	public String getPacket_code() {
		return packet_code;
	}
	public void setPacket_code(String packet_code) {
		this.packet_code = packet_code;
	}
	public String getAlert_short_code() {
		return alert_short_code;
	}
	public void setAlert_short_code(String alert_short_code) {
		this.alert_short_code = alert_short_code;
	}
	public String getMisc_bytes() {
		return misc_bytes;
	}
	public void setMisc_bytes(String misc_bytes) {
		this.misc_bytes = misc_bytes;
	}
	public String getAlert_desc() {
		return alert_desc;
	}
	public void setAlert_desc(String alert_desc) {
		this.alert_desc = alert_desc;
	}
	public String getAdditional_info_1() {
		return additional_info_1;
	}
	public void setAdditional_info_1(String additional_info_1) {
		this.additional_info_1 = additional_info_1;
	}
	public String getAdditional_info_2() {
		return additional_info_2;
	}
	public void setAdditional_info_2(String additional_info_2) {
		this.additional_info_2 = additional_info_2;
	}
	public String getAdditional_info_3() {
		return additional_info_3;
	}
	public void setAdditional_info_3(String additional_info_3) {
		this.additional_info_3 = additional_info_3;
	}
	public String getRecord_status() {
		return record_status;
	}
	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Integer getDashboard_view() {
		return dashboard_view;
	}
	public void setDashboard_view(Integer dashboard_view) {
		this.dashboard_view = dashboard_view;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public Integer getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}
	/*public INFO_MODE getInfomode() {
		return infomode;
	}

	public void setInfomode(INFO_MODE infomode) {
		this.infomode = infomode;
	}*/
}
