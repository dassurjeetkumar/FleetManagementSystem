package com.trimax.doa.ticketing.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.trimax.its.transport.model.OrgType;
import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name="ticketbag_master")
public class TicketBagMaster {
	@Id
	@GeneratedValue
	@Column(name="ticketbag_id")
	private int ticketbag_id;
	@Column(name="bag_code")
	private String bagcode;
	@Column(name="depot_id")
	private int depotid;
	@Column(name="effective_start_date")
    private String effectivestartdate;
	@Column(name="effective_end_date")
    private String effectiveenddate;
	public String getEffectivestartdate() {
		return effectivestartdate;
	}
	public void setEffectivestartdate(String effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}
	public String getEffectiveenddate() {
		return effectiveenddate;
	}
	public void setEffectiveenddate(String effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}
	@Column(name="status")
	private String status;
	@Column(name="notes")
	private String notes;
	@Column(name="created_by")
	private int created_by;
	@Column(name="updated_by")
	private int updated_by;
	@Column(name="created_date")
    private Date created_date;
	@Column(name="updated_date")
    private Date updated_date;
	@Column(name="deleted_status")
	private int deleted_status;
	
//	@Column(name="org_chart_id")
//	private int orgchartid;
	
	@ManyToOne
	@JoinColumn(name="org_chart_id")
	private OrganisationChart orgchart;
	@ManyToOne
	@JoinColumn(name="org_type_id")
	private OrgType orgtype;
	
	
	public OrgType getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(OrgType orgtype) {
		this.orgtype = orgtype;
	}
	public OrganisationChart getOrgchart() {
		return orgchart;
	}
	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}
	/*public int getOrgcharttypeid() {
		return orgcharttypeid;
	}
	public void setOrgcharttypeid(int orgcharttypeid) {
		this.orgcharttypeid = orgcharttypeid;
	}*/
/*	@Column(name="org_chart_type_id")
	private int orgcharttypeid;
*/	
	public int getTicketbag_id() {
		return ticketbag_id;
	}
	public void setTicketbag_id(int ticketbag_id) {
		this.ticketbag_id = ticketbag_id;
	}
	public String getBagcode() {
		return bagcode;
	}
	public void setBagcode(String bagcode) {
		this.bagcode = bagcode;
	}
	public int getDepotid() {
		return depotid;
	}
	public void setDepotid(int depotid) {
		this.depotid = depotid;
	}
	/*public void setEffectivestartdate(String effectivestartdate) throws ParseException {
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 if(effectivestartdate.trim().length()>0){
		this.effectivestartdate = sdf.parse(effectivestartdate);
	}
	}
	public void setEffectiveenddate(String effectiveenddate) throws ParseException {
		System.out.println("effective date is.."+effectiveenddate);
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 if(effectiveenddate.trim().length()>0){
		 this.effectiveenddate = sdf.parse(effectiveenddate);
		 }
	}
	public Date getEffectivestartdate() {
		return effectivestartdate;
	}
	public void setEffectivestartdate(Date effectivestartdate) {
		this.effectivestartdate = effectivestartdate;
	}
	public Date getEffectiveenddate() {
		return effectiveenddate;
	}
*/	/*public void setEffectiveenddate(Date effectiveenddate) {
		this.effectiveenddate = effectiveenddate;
	}*/
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
	public int getDeleted_status() {
		return deleted_status;
	}
	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	
	
	

}
