package com.trimax.its.transport.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="shift_type")
public class ShiftType {
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shift_type_id")
	private int id;
	
	@Column(name="shift_type_name")
	private String shiftTypeName;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="status")
	private String status;
	
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	Integer createdBy;
	
	@Column(name="updated_by")
	Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="deleted_status")
	private int deletedStatus;
	@Column(name="shift_Type_code")
	private String shift_Type_code;
	public String getShift_Type_code() {
		return shift_Type_code;
	}

	public void setShift_Type_code(String shift_Type_code) {
		this.shift_Type_code = shift_Type_code;
	}

	@Column(name="schedule_type_id")
	private int schedule_type_id;
	public int getSchedule_type_id() {
		return schedule_type_id;
	}

	public void setSchedule_type_id(int schedule_type_id) {
		this.schedule_type_id = schedule_type_id;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="shiftType")
	private Set<ScheduleDetails> shiftDetails;

	public int getId() {
		return id;
	}
	 private boolean isEmpty(String str) {
		 
	        return str == null ? true:(str.equals("") ? true:false);
	    }
	public void setId(String id) {
		if(isEmpty(id))
		{
			this.id=0;
		}
		this.id =Integer.parseInt(id);
	}
	@ManyToOne
    @JoinColumn(name="schedule_type_id")
    private ScheduleType scheduletype;
	
	public ScheduleType getScheduletype() {
		return scheduletype;
	}

	public void setScheduletype(ScheduleType scheduletype) {
		this.scheduletype = scheduletype;
	}

	public String getShiftTypeName() {
		return shiftTypeName;
	}

	public int getShift_Type_code() {
		return shift_Type_code;
	}

	public void setShift_Type_code(String shift_Type_code) {
		if(isEmpty(shift_Type_code))
		{
			this.shift_Type_code=0;
		}
		else
		{
			if(isInteger(shift_Type_code))
			{
		        this.shift_Type_code =Integer.parseInt(shift_Type_code);
			}
			else	
			{
				this.shift_Type_code=-1;
			}
	}
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public void setShiftTypeName(String shiftTypeName) {
		this.shiftTypeName = shiftTypeName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public Set<ScheduleDetails> getShiftDetails() {
		return shiftDetails;
	}

	public void setShiftDetails(Set<ScheduleDetails> shiftDetails) {
		this.shiftDetails = shiftDetails;
	}
	*/
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shift_type_id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="shift_type_name")
	private String ShiftTypeName;
	
	@Column(name="shift_code")
	private String shift_code;
	
	@Column(name="duration")
	private String duration;

	@ManyToOne
    @JoinColumn(name="schedule_type_id")
    private ScheduleType schedule_type_id;
	
	
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="status")
	private String status;
	
	
	@Column(name="created_by")
	private int created_by;
	
	@Column(name="updated_by")
	private int updated_by;

	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="sign_in")
	private String signIn;

	@Column(name="sign_off")
	private String signOff;

	public String getShiftTypeName() {
		return ShiftTypeName;
	}

	public void setShiftTypeName(String shiftTypeName) {
		ShiftTypeName = shiftTypeName.trim();
	}

	public String getShift_code() {
		return shift_code;
	}

	public void setShift_code(String shift_code) {
		this.shift_code = shift_code.trim();
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration.trim();
	}

	public ScheduleType getSchedule_type_id() {
		return schedule_type_id;
	}

	public void setSchedule_type_id(ScheduleType schedule_type_id) {
		this.schedule_type_id = schedule_type_id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes.trim();
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

	public String getShift_Type_code() {
		return shift_Type_code;
	}

	public void setShift_Type_code(String shift_Type_code) {
		this.shift_Type_code = shift_Type_code;
	}
	
	

	/**
	 * @return the signIn
	 */
	public String getSignIn() {
		return signIn;
	}

	/**
	 * @param signIn the signIn to set
	 */
	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}

	/**
	 * @return the signOff
	 */
	public String getSignOff() {
		return signOff;
	}

	/**
	 * @param signOff the signOff to set
	 */
	public void setSignOff(String signOff) {
		this.signOff = signOff;
	}

	@Column(name="deleted_status")
	private int deleted_status;
	
	
	
	
	@Column(name="shift_Type_code")
	private String shift_Type_code;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="shiftType")
	private Set<ScheduleDetails> shiftDetails;

	public Set<ScheduleDetails> getShiftDetails() {
		return shiftDetails;
	}

	public void setShiftDetails(Set<ScheduleDetails> shiftDetails) {
		this.shiftDetails = shiftDetails;
	}
	
	
}
