package com.trimax.its.vts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name = "vts_alert_config")
public class VehicleAlertConfig {
	
	@Id
	@GeneratedValue
	@Column(name = "VTS_ALERT_CONFIG_ID")
	private int VTS_ALERT_CONFIG_ID;

	@Column(name = "PACKET_CODE")
	private String PACKET_CODE;

	@ManyToOne
	@JoinColumn(name="VTS_ALERT_MASTER_ID")
	private VtsAlertMaster vtsalertmaster;
   

	@Column(name = "MISC_BYTES")
	private String MISC_BYTES;
	
	@Column(name = "TIME_DURATION")
	private int TIME_DURATION;

	@Column(name = "CREATED_DATE")
	private Date CREATED_DATE;


	@Column(name = "CREATED_BY")
	private int CREATED_BY;

	@Column(name = "UPDATED_DATE")
	private Date UPDATED_DATE;
	
	@Column(name = "UPDATED_BY")
	private int UPDATED_BY;
	
	@Column(name = "RECORD_STATUS")
    private String RECORD_STATUS;
	
	
	public int getVTS_ALERT_CONFIG_ID() {
		return VTS_ALERT_CONFIG_ID;
	}
	public void setVTS_ALERT_CONFIG_ID(int vTS_ALERT_CONFIG_ID) {
		VTS_ALERT_CONFIG_ID = vTS_ALERT_CONFIG_ID;
	}
	
	
	public String getRECORD_STATUS() {
		return RECORD_STATUS;
	}
	public void setRECORD_STATUS(String rECORD_STATUS) {
		RECORD_STATUS = rECORD_STATUS;
	}
	
	public VtsAlertMaster getVtsalertmaster() {
		return vtsalertmaster;
	}
	public void setVtsalertmaster(VtsAlertMaster vtsalertmaster) {
		this.vtsalertmaster = vtsalertmaster;
	}
	
	public String getPACKET_CODE() {
		return PACKET_CODE;
	}
	public void setPACKET_CODE(String pACKET_CODE) {
		PACKET_CODE = pACKET_CODE;
	}
	
	public String getMISC_BYTES() {
		return MISC_BYTES;
	}
	public void setMISC_BYTES(String mISC_BYTES) {
		MISC_BYTES = mISC_BYTES;
	}

	public int getTIME_DURATION() {
		return TIME_DURATION;
	}
	public void setTIME_DURATION(int tIME_DURATION) {
		TIME_DURATION = tIME_DURATION;
	}
	public Date getCREATED_DATE() {
		return CREATED_DATE;
	}
	public void setCREATED_DATE(Date cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}
	public int getCREATED_BY() {
		return CREATED_BY;
	}
	public void setCREATED_BY(int cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}
	public Date getUPDATED_DATE() {
		return UPDATED_DATE;
	}
	public void setUPDATED_DATE(Date uPDATED_DATE) {
		UPDATED_DATE = uPDATED_DATE;
	}
	public int getUPDATED_BY() {
		return UPDATED_BY;
	}
	public void setUPDATED_BY(int uPDATED_BY) {
		UPDATED_BY = uPDATED_BY;
	}

	

}
