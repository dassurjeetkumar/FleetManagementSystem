package com.trimax.its.vehicle.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.trimax.doa.ticketing.model.TicketBagMaster;
import com.trimax.its.pis.model.DisplayMappingModel;
import com.trimax.its.pis.model.PisScrollMessageModel;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStops;

import com.trimax.its.pis.model.DisplayUnitModel;
import com.trimax.its.cashremittancevoucher.model.CashRemittanceVoucher;
import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.route.model.Floor;

import com.trimax.its.transport.model.OrgType;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.usermanagement.model.UserDetails;

@Entity
@Table(name="org_chart")
public class OrganisationChart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int org_chart_id;
	
	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	@Column
	private Integer parent_id;
	@Column(nullable=true)
	private String org_name;
	
	@Transient
	private String parent_org_name;
	
	public String getParent_org_name() {
		return parent_org_name;
	}

	public void setParent_org_name(String parent_org_name) {
		this.parent_org_name = parent_org_name;
	}

	@Column   
	private String org_name_kannada;
	
	@Column   
	private String org_code;
	
	@Column   
	private String org_code_kannada;
	
	@Column   
	private String website;
	
	@Column   
	private String address="";
	
	@Column   
	private String phone_number;
	
	@Column   
	private String city;
	
	@Column   
	private String state;
	
	@Column   
	private String country;
	
	@Column   
	private String latitude="0.0";
	
	@Column   
	private String landmark;
	
	@Column   
	private String logitude="0.0";
	
	@Column   
	private String location_string="";
	
	
	@Column   
	private String contact_person;
	
	@Column   
	private String sector_layer;
	
	@Column   
	private String sector_for_line_checking;
	
	
	
	public String getSector_layer() {
		return sector_layer;
	}

	public void setSector_layer(String sector_layer) {
		this.sector_layer = sector_layer;
	}

	public String getSector_for_line_checking() {
		return sector_for_line_checking;
	}

	public void setSector_for_line_checking(String sector_for_line_checking) {
		this.sector_for_line_checking = sector_for_line_checking;
	}

	public int getDeleted_status() {
		return deleted_status;
	}

	public void setDeleted_status(int deleted_status) {
		this.deleted_status = deleted_status;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgchart")
	private Set<Employee> employee;

	@Column   
	private int deleted_status;
	
	private int created_by;	 
	private int updated_by;	
	private java.util.Date updated_date;	
	
	
	public int getCreated_by() {
		return created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public java.util.Date getUpdated_date() {
		return updated_date;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public void setUpdated_date(java.util.Date updated_date) {
		this.updated_date = updated_date;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="depotOrUnit")
	private Set<Vehicle> vehicles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="fromOrganisationId")
	private Set<TransferVehicleHistory> transferedFromDepot;
	
	
	 
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="toOrganisationId")
	private Set<TransferVehicleHistory> transferedToDepot;
//	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="depot")
	private Set<Schedule> scheduleDetails;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgchart")
	private Set<TicketBagMaster> ticketOrgabnizatuion;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="bus_station_id")
	private Set<DisplayMappingModel> displayMappingModel;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="busstand")
	private Set<PisScrollMessageModel> scrollMessageModel;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="display_bus_station_id")
	private Set<DisplayUnitModel> displayUnitModel;
	public Set<DisplayUnitModel> getDisplayUnitModel() {
		return displayUnitModel;
	}

	public void setDisplayUnitModel(Set<DisplayUnitModel> displayUnitModel) {
		this.displayUnitModel = displayUnitModel;
	}

	public Set<PisScrollMessageModel> getScrollMessageModel() {
		return scrollMessageModel;
	}

	public void setScrollMessageModel(Set<PisScrollMessageModel> scrollMessageModel) {
		this.scrollMessageModel = scrollMessageModel;
	}

	@ManyToOne
	@JoinColumn(name="org_type_id")
	private OrgType orgType;
	
	public Set<DisplayMappingModel> getDisplayMappingModel() {
		return displayMappingModel;
	}

	public void setDisplayMappingModel(Set<DisplayMappingModel> displayMappingModel) {
		this.displayMappingModel = displayMappingModel;
	}

	public int getOrg_chart_id() {
		return org_chart_id;
	}

	public void setOrg_chart_id(int org_chart_id) {
		this.org_chart_id = org_chart_id;
	}

	public String getOrg_name() {
		return org_name.trim();
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name.trim();
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Set<TransferVehicleHistory> getTransferedFromDepot() {
		return transferedFromDepot;
	}

	public void setTransferedFromDepot(
			Set<TransferVehicleHistory> transferedFromDepot) {
		this.transferedFromDepot = transferedFromDepot;
	}

	public Set<TransferVehicleHistory> getTransferedToDepot() {
		return transferedToDepot;
	}

	public void setTransferedToDepot(Set<TransferVehicleHistory> transferedToDepot) {
		this.transferedToDepot = transferedToDepot;
	}

	public Set<Schedule> getScheduleDetails() {
		return scheduleDetails;
	}

	public void setScheduleDetails(Set<Schedule> scheduleDetails) {
		this.scheduleDetails = scheduleDetails;
	}

	public OrgType getOrgType() {
		return orgType;
	}

	public void setOrgType(OrgType orgType) {
		this.orgType = orgType;
	}

	public String getOrg_name_kannada() {
		/*String out = "";
		if( org_name_kannada!=null){
			System.out.println("org_name_kannadaorg_name_kannada"+org_name_kannada);
			org_name_kannada = org_name_kannada.trim().replace(" ", "");
		String[] s = org_name_kannada.split(";");
		//String[] s = a.split(";");
		try {
		for(int i=0; i<s.length; i++)
	    {
			
			String s1= s[i].replace("#", "");
			s1= s1.replace("&", "");
			s1= s1.trim();
	        char c = (char)Integer.parseInt(s1);
	      System.out.println(s.length+""+c+"----"+s1);
	           out+=""+c;
	        
	    }
		System.out.println("Out-------->"+out);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception -----> "+e);
		}
		}
		return out;*/
		return org_name_kannada;
	}
	public void setOrg_name_kannada(String org_name_kannada) {
		StringBuffer out = new StringBuffer();
		System.out.println("org_name_kannada===>"+org_name_kannada);
	    for(int i=0; i<org_name_kannada.length(); i++)
	    {
	        char c = org_name_kannada.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>')
	        {
	           out.append("&#"+(int)c+";");
	           System.out.println("hiiii&#"+(int)c+";");
	        }
	        else
	        {
	        	System.out.println("c===="+c);
	            out.append(c);
	        }
	    }
	    System.out.println("outout===>"+out);
		this.org_name_kannada = out.toString();
	}
	@Transient
	private String orgcodekannada;
	@Transient
	private String orgnamekannada;
	
	
	public String getOrgnamekannada() {
		String out = "";
		orgnamekannada = orgnamekannada.replace(" ", "");
		String[] s = orgnamekannada.split(";");
		//String[] s = a.split(";");
		try {
		for(int i=0; i<s.length; i++)
	    {
			
			String s1= s[i].replace("#", "");
			s1= s1.replace("&", "");
			s1= s1.trim();
	        char c = (char)Integer.parseInt(s1);
	      System.out.println(s.length+""+c+"----"+s1);
	           out+=""+c;
	        
	    }
		System.out.println("Out-------->"+out);
		}
		catch(Exception e){
			System.out.println("Exception -----> "+e);
		}
		return out;
	}

	public void setOrgnamekannada(String orgnamekannada) {
		this.orgnamekannada = orgnamekannada;
	}

	public String getOrgcodekannada() {
		String out = "";
		orgcodekannada = orgcodekannada.replace(" ", "");
		String[] s = orgcodekannada.split(";");
		//String[] s = a.split(";");
		try {
		for(int i=0; i<s.length; i++)
	    {
			
			String s1= s[i].replace("#", "");
			s1= s1.replace("&", "");
			s1= s1.trim();
	        char c = (char)Integer.parseInt(s1);
	      System.out.println(s.length+""+c+"----"+s1);
	           out+=""+c;
	        
	    }
		System.out.println("Out-------->"+out);
		}
		catch(Exception e){
			System.out.println("Exception -----> "+e);
		}
		return out;
	}

	public void setOrgcodekannada(String orgcodekannada) {
		this.orgcodekannada = orgcodekannada;
	}

	public String getOrg_code_kannada() {
		/*String out = "";
		if( org_code_kannada!=null){
		org_code_kannada = org_code_kannada.replace(" ", "");
		String[] s = org_code_kannada.split(";");
		//String[] s = a.split(";");
		try {
		for(int i=0; i<s.length; i++)
	    {
			
			String s1= s[i].replace("#", "");
			s1= s1.replace("&", "");
			s1= s1.trim();
	        char c = (char)Integer.parseInt(s1);
	      System.out.println(s.length+""+c+"----"+s1);
	           out+=""+c;
	        
	    }
		System.out.println("Out-------->"+out);
		}
		catch(Exception e){
			System.out.println("Exception -----> "+e);
		}
		}
		return out;*/
		return org_code_kannada;
	}

	public String getOrg_code() {
		return org_code;
	}

	public String getWebsite() {
		return website;
	}

	public String getAddress() {
		
		return address;
		
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLandmark() {
		return landmark;
	}

	public String getLogitude() {
		return logitude;
	}

	public String getContact_person() {
		return contact_person;
	}

	

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public void setOrg_code_kannada(String org_code_kannada) {
		StringBuffer out = new StringBuffer();
	    for(int i=0; i<org_code_kannada.length(); i++)
	    {
	        char c = org_code_kannada.charAt(i);
	        if(c > 127 || c=='"' || c=='<' || c=='>')
	        {
	           out.append("&#"+(int)c+";");
	        }
	        else
	        {
	            out.append(c);
	        }
	    }
	   // return 
	this.org_code_kannada = out.toString();
		//this.org_code_kannada = org_code_kannada;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setAddress(String address) {
		this.address = address.trim();
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
		
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public void setLogitude(String logitude) {
		this.logitude = logitude;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="bus_stations")
	private Set<BusStops> bus_station;

	public Set<BusStops> getBus_station() {
		return bus_station;
	}

	public void setBus_station(Set<BusStops> bus_station) {
		this.bus_station= bus_station;
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy="orgChart")
	Set<Floor> floor;

	public Set<Floor> getFloor() {
		return floor;
	}

	public void setFloor(Set<Floor> floor) {
		this.floor = floor;
	}
	
	
	

	public String getLocation_string() {
		return location_string;
	}

	public void setLocation_string(String location_string) {
		this.location_string = location_string;
	}

	public Set<TicketBagMaster> getTicketOrgabnizatuion() {
		return ticketOrgabnizatuion;
	}

	public void setTicketOrgabnizatuion(Set<TicketBagMaster> ticketOrgabnizatuion) {
		this.ticketOrgabnizatuion = ticketOrgabnizatuion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy="orgchartdetails")
	private Set<UserDetails> userDetails;

	public Set<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(Set<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}
	@OneToMany(mappedBy="orgChartCash")
	private Set<CashRemittanceVoucher> orgCashRemittanceVoucher;
	
	public Set<CashRemittanceVoucher> getOrgCashRemittanceVoucher() {
		return orgCashRemittanceVoucher;
	}

	public void setOrgCashRemittanceVoucher(
			Set<CashRemittanceVoucher> orgCashRemittanceVoucher) {
		this.orgCashRemittanceVoucher = orgCashRemittanceVoucher;
	}

	
}
