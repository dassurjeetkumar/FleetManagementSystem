package com.trimax.its.transport.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.trimax.its.route.model.Route;
import com.trimax.its.vehicle.model.OrganisationChart;

@Entity
@Table(name = "bus_stop")
public class BusStops {

	@Id
	@GeneratedValue
	@Column(name = "bus_stop_id")
	private int id;

	@Column(name = "bus_stop_name")
	private String busStopName;

	@Column(name = "sub_stage")
	private String sub_stage;

	public String getSub_stage() {
		return sub_stage;
	}

	public void setSub_stage(String sub_stage) {
		this.sub_stage = sub_stage;
	}

	@Column(name = "bus_stop_name_kannada")
	private String busStopNameKannada;

	private String busStopNameKannada1;

	private String busStopCodeKannada1;

	public String getBusStopNameKannada1() {
		String out = "";
		busStopNameKannada1 = busStopNameKannada1.replace(" ", " ");
		String[] s = busStopNameKannada1.split(";");
		// String[] s = a.split(";");
		try {
			for (int i = 0; i < s.length; i++) {
				if (s[i].charAt(0) == ' ') {
					out += " ";
				}
				String s1 = s[i].replace("#", "");
				s1 = s1.replace("&", "");
				s1 = s1.trim();
				char c = (char) Integer.parseInt(s1);
				System.out.println(s.length + "" + c + "----" + s1);
				out += "" + c;

			}
			System.out.println("Out-------->" + out);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception -----> " + e);
		}
		return out;

	}

	public void setBusStopNameKannada1(String busStopNameKannada1) {
		this.busStopNameKannada1 = busStopNameKannada1;
	}

	/*
	 * public void setBusStopNameKannada1(String busStopNameKannada1) {
	 * this.busStopNameKannada1 = busStopNameKannada1.trim(); } public void
	 * setBusStopNameKannada1(String busStopNameKannada1) {
	 * this.busStopNameKannada1 = busStopNameKannada1.trim(); }
	 */

	public String getBusStopCodeKannada1() {
		String out = "";
		//System.out.println("nus----" + busStopCodeKannada1);
		// String a =
		// "&#3257;&#3274;&#3221;&#3270;&#3247;&#3277; &#3256;&#3277;&#3231;&#3271;&#3233;&#3263;&#3247;&#3202;";
		if (busStopCodeKannada1 != null) {
			busStopCodeKannada1 = busStopCodeKannada1.replace(" ", "");
			String[] s = busStopCodeKannada1.split(";");
			// String[] s = a.split(";");
			try {
				for (int i = 0; i < s.length; i++) {
					if (s[i].charAt(0) == ' ') {
						out += " ";
					}
					String s1 = s[i].replace("#", "");
					s1 = s1.replace("&", "");
					s1 = s1.trim();
					char c = (char) Integer.parseInt(s1);
					System.out.println(s.length + "" + c + "----" + s1);
					out += "" + c;

				}
				System.out.println("Out-------->" + out);
			} catch (Exception e) {
				System.out.println("Exception -----> " + e);
			}
		}
		return out;
	}

	public void setBusStopCodeKannada1(String busStopCodeKannada1) {
		this.busStopCodeKannada1 = busStopCodeKannada1;
	}

	//Added For Trip Plannig 
	@Transient
	private String Value;
	
	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}
	//End
	
	@Column(name = "bus_stop_code")
	private String bus_stop_code;

	@Column(name = "bus_stop_code_kannada")
	private String bus_stop_code_kannada;

	@Column(name = "updated_date")
	private Date updated_date;

	public String getBus_stop_code() {
		return bus_stop_code;
	}

	public void setBus_stop_code(String bus_stop_code) {
		this.bus_stop_code = bus_stop_code;
	}

	public String getBus_stop_code_kannada() {
		return bus_stop_code_kannada;
	}

	public void setBus_stop_code_kannada(String bus_stop_code_kannada) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < bus_stop_code_kannada.length(); i++) {
			char c = bus_stop_code_kannada.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.bus_stop_code_kannada = out.toString();
		// this.bus_stop_code_kannada = bus_stop_code_kannada;
	}

	@Column(name = "status")
	private String status;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "latitude_current")
	private double latitude;

	@Column(name = "longitude_current")
	private double longitude;

	@Column(name = "is_sheltered")
	private String sheltered;

	@Column(name = "speed")
	private String speed;

	@Column(name = "accuracy")
	private String accuracy;

	@Column(name = "altitude")
	private String altitude;

	@Column(name = "bearing")
	private String bearing;

	@Column(name = "device_code")
	private String deviceCode;

	@Column(name = "locality")
	private String locality;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "alias1")
	private String alias1;

	@Column(name = "alias2")
	private String alias2;

	@Column(name = "alias3")
	private String alias3;

	@Column(name = "alias4")
	private String alias4;

	@Column(name = "alias5")
	private String alias5;

	@Column(name = "alias6")
	private String alias6;

	@Column(name = "local_bus_stop_id")
	private Integer setLocal_bus_stop_id;

	public Integer getSetLocal_bus_stop_id() {
		return setLocal_bus_stop_id;
	}

	public void setSetLocal_bus_stop_id(Integer setLocal_bus_stop_id) {
		this.setLocal_bus_stop_id = setLocal_bus_stop_id;
	}

	public String getAlias5() {
		return alias5;
	}

	public void setAlias5(String alias5) {
		this.alias5 = alias5;
	}

	public String getAlias6() {
		return alias6;
	}

	public void setAlias6(String alias6) {
		this.alias6 = alias6;
	}

	@Column(name = "toll_zone")
	private String tollZone;

	@Column(name = "code_1")
	private String code1;

	@Column(name = "code_2")
	private String code2;

	@Column(name = "city_limit")
	private String cityLimit;

	@Column(name = "ward_number")
	private String wardNumber;

	@Column(name = "area_population")
	private String areaPopulation;

	@Column(name = "stop_size")
	private String stopSize;

	@Column(name = "fare_stage")
	private String fareStage;

	@Column(name = "description")
	private String description;

	@Column(name = "bmtc_status")
	private String bmtcStatus;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "k_alias_1")
	private String kalias1;

	@Column(name = "k_alias_2")
	private String k_alias_2;
	
	@Column(name = "k_alias_3")
	private String k_alias_3;
	
	@Column(name = "k_alias_4")
	private String k_alias_4;
	
	@Column(name = "k_alias_5")
	private String k_alias_5;
	
	@Column(name = "k_alias_6")
	private String k_alias_6;

	// public void setK_alias_6(String k_alias_6) {
	// this.k_alias_6 = k_alias_6;
	// }

	@Column(name = "stop_direction")
	private String stop_direction;

	@Column(name = "updated_by")
	private int updated_by;

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public String getStop_direction() {
		return stop_direction;
	}

	public void setStop_direction(String stop_direction) {
		this.stop_direction = stop_direction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusStopName() {
		return busStopName;
	}

	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}

	public String getBusStopNameKannada() {

		/*
		 * System.out.println("Kanada----->"+busStopNameKannada); String out =
		 * "";
		 * 
		 * //String a =
		 * "&#3257;&#3274;&#3221;&#3270;&#3247;&#3277; &#3256;&#3277;&#3231;&#3271;&#3233;&#3263;&#3247;&#3202;"
		 * ; busStopNameKannada = busStopNameKannada.replace(" ", ""); String[]
		 * s = busStopNameKannada.split(";"); //String[] s = a.split(";"); try {
		 * for(int i=0; i<s.length; i++) {
		 * 
		 * String s1= s[i].replace("#", ""); s1= s1.replace("&", ""); s1=
		 * s1.trim(); char c = (char)Integer.parseInt(s1);
		 * System.out.println(s.length+""+c+"----"+s1); out+=""+c;
		 * 
		 * } System.out.println("Out-------->"+out); } catch(Exception e){
		 * System.out.println("Exception -----> "+e); }
		 */
		return busStopNameKannada;
	}

	public void setBusStopNameKannada(String busStopNameKannada) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < busStopNameKannada.length(); i++) {
			char c = busStopNameKannada.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.busStopNameKannada = out.toString();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		// if(status.equals("DELETED")){
		this.status = status;
		// }else{
		// this.status = "UPDATED BY "+status;
		// }
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getSheltered() {
		return sheltered;
	}

	public void setSheltered(String sheltered) {
		this.sheltered = sheltered;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getBearing() {
		return bearing;
	}

	public void setBearing(String bearing) {
		this.bearing = bearing;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getAlias1() {
		return alias1;
	}

	public void setAlias1(String alias1) {
		this.alias1 = alias1;
	}

	public String getAlias2() {
		return alias2;
	}

	public void setAlias2(String alias2) {
		this.alias2 = alias2;
	}

	public String getAlias3() {
		return alias3;
	}

	public void setAlias3(String alias3) {
		this.alias3 = alias3;
	}

	public String getAlias4() {
		return alias4;
	}

	public void setAlias4(String alias4) {
		this.alias4 = alias4;
	}

	public String getTollZone() {
		return tollZone;
	}

	public void setTollZone(String tollZone) {
		this.tollZone = tollZone;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCityLimit() {
		return cityLimit;
	}

	public void setCityLimit(String cityLimit) {
		this.cityLimit = cityLimit;
	}

	public String getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	public String getAreaPopulation() {
		return areaPopulation;
	}

	public void setAreaPopulation(String areaPopulation) {
		this.areaPopulation = areaPopulation;
	}

	public String getStopSize() {
		return stopSize;
	}

	public void setStopSize(String stopSize) {
		this.stopSize = stopSize;
	}

	public String getFareStage() {
		return fareStage;
	}

	public void setFareStage(String fareStage) {
		this.fareStage = fareStage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getBmtcStatus() {
		return bmtcStatus;
	}

	public void setBmtcStatus(String bmtcStatus) {
		this.bmtcStatus = bmtcStatus;
	}

	public String getKalias1() {
		return kalias1;
	}

	public void setKalias1(String kalias1) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < kalias1.length(); i++) {
			char c = kalias1.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.kalias1 = out.toString();
		// this.kalias1 = kalias1;
	}

	public String getK_alias_2() {
		return k_alias_2;
	}

	public void setK_alias_2(String k_alias_2) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < k_alias_2.length(); i++) {
			char c = k_alias_2.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.k_alias_2 = out.toString();
		// this.k_alias_2 = k_alias_2;
	}

	public String getK_alias_3() {
		return k_alias_3;
	}

	public void setK_alias_3(String k_alias_3) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < k_alias_3.length(); i++) {
			char c = k_alias_3.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.k_alias_3 = out.toString();
		// this.k_alias_2 = k_alias_2;
	}

	// public void setK_alias_3(String k_alias_3) {
	// this.k_alias_3 = k_alias_3;
	// }

	public String getK_alias_4() {
		return k_alias_4;
	}

	public void setK_alias_4(String k_alias_4) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < k_alias_4.length(); i++) {
			char c = k_alias_4.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.k_alias_4 = out.toString();
		// this.k_alias_2 = k_alias_2;
	}

	/*
	 * public void setK_alias_4(String k_alias_4) { this.k_alias_4 = k_alias_4;
	 * }
	 */

	public String getK_alias_5() {
		return k_alias_5;
	}

	public void setK_alias_5(String k_alias_5) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < k_alias_5.length(); i++) {
			char c = k_alias_5.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.k_alias_5 = out.toString();
		// this.k_alias_2 = k_alias_2;
	}

	/*
	 * public void setK_alias_5(String k_alias_5) { this.k_alias_5 = k_alias_5;
	 * }
	 */

	public String getK_alias_6() {
		return k_alias_6;
	}

	public void setK_alias_6(String k_alias_6) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < k_alias_6.length(); i++) {
			char c = k_alias_6.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		// return
		this.k_alias_6 = out.toString();
		// this.k_alias_2 = k_alias_2;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "busstartpoint")
	private Set<Route_Trans> busdetails;

	public Set<Route_Trans> getBusdetails() {
		return busdetails;
	}

	public void setBusdetails(Set<Route_Trans> busdetails) {
		this.busdetails = busdetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "busendpoint")
	private Set<Route_Trans> busenddetails;

	public Set<Route_Trans> getBusenddetails() {
		return busenddetails;
	}

	public void setBusenddetails(Set<Route_Trans> busenddetails) {
		this.busenddetails = busenddetails;
	}

	// @OneToMany(cascade = CascadeType.ALL, fetch =
	// FetchType.LAZY,mappedBy="depotOrUnit")
	// private Set<Schedule> scheduleDetails;
	//
	// public Set<Schedule> getScheduleDetails() {
	// return scheduleDetails;
	// }
	//
	// public void setScheduleDetails(Set<Schedule> scheduleDetails) {
	// this.scheduleDetails = scheduleDetails;
	// }
	//
	/*
	 * @Column(name="point_type_id") private Integer point_type_id;
	 * 
	 * 
	 * public Integer getPoint_type_id() { return point_type_id; }
	 * 
	 * public void setPoint_type_id(Integer point_type_id) { this.point_type_id
	 * = point_type_id; }
	 */

	@Column(name = "manual")
	private char manual;

	public PointType getPointtype() {
		return pointtype;
	}

	public void setPointtype(PointType pointtype) {
		this.pointtype = pointtype;
	}

	public char getManual() {
		return manual;
	}

	public void setManual(char manual) {
		this.manual = manual;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "start_stop")
	private Set<Route> start_route;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "end_stop")
	private Set<Route> end_route;

	public Set<Route> getStart_route() {
		return start_route;
	}

	public void setStart_route(Set<Route> start_route) {
		this.start_route = start_route;
	}

	public Set<Route> getEnd_route() {
		return end_route;
	}

	public void setEnd_route(Set<Route> end_route) {
		this.end_route = end_route;
	}

	@ManyToOne
	@JoinColumn(name = "org_chart_id")
	private OrganisationChart bus_stations;

	public OrganisationChart getBus_stations() {
		return bus_stations;
	}

	public void setBus_stations(OrganisationChart bus_stations) {
		this.bus_stations = bus_stations;
	}

	@Transient
	private String kalias11;

	public String getKalias11() {
		String out = "";
		kalias11 = kalias11.replace(" ", "");
		String[] s = kalias11.split(";");
		// String[] s = a.split(";");
		try {
			for (int i = 0; i < s.length; i++) {

				String s1 = s[i].replace("#", "");
				s1 = s1.replace("&", "");
				s1 = s1.trim();
				char c = (char) Integer.parseInt(s1);
				System.out.println(s.length + "" + c + "----" + s1);
				out += "" + c;

			}
			System.out.println("Out-------->" + out);
		} catch (Exception e) {
			System.out.println("Exception -----> " + e);
		}
		return out;

	}

	public void setKalias11(String kalias11) {
		this.kalias11 = kalias11;
	}

	@Transient
	private String k_alias_21;

	public String getK_alias_21() {
		String out = "";
		k_alias_21 = k_alias_21.replace(" ", "");
		String[] s = k_alias_21.split(";");
		// String[] s = a.split(";");
		try {
			for (int i = 0; i < s.length; i++) {

				String s1 = s[i].replace("#", "");
				s1 = s1.replace("&", "");
				s1 = s1.trim();
				char c = (char) Integer.parseInt(s1);
				System.out.println(s.length + "" + c + "----" + s1);
				out += "" + c;

			}
			System.out.println("Out-------->" + out);
		} catch (Exception e) {
			System.out.println("Exception -----> " + e);
		}
		return out;

	}

	public void setK_alias_21(String k_alias_21) {
		this.k_alias_21 = k_alias_21;
	}

	@Transient
	private String k_alias_31;

	public String getK_alias_31() {
		String out = "";
		k_alias_31 = k_alias_31.replace(" ", "");
		String[] s = k_alias_31.split(";");
		// String[] s = a.split(";");
		try {
			for (int i = 0; i < s.length; i++) {

				String s1 = s[i].replace("#", "");
				s1 = s1.replace("&", "");
				s1 = s1.trim();
				char c = (char) Integer.parseInt(s1);
				System.out.println(s.length + "" + c + "----" + s1);
				out += "" + c;

			}
			System.out.println("Out-------->" + out);
		} catch (Exception e) {
			System.out.println("Exception -----> " + e);
		}
		return out;

	}

	public void setK_alias_31(String k_alias_31) {
		this.k_alias_31 = k_alias_31;
	}

	@Transient
	private String k_alias_41;

	public String getK_alias_41() {
		String out = "";
		k_alias_41 = k_alias_41.replace(" ", "");
		String[] s = k_alias_41.split(";");
		// String[] s = a.split(";");
		try {
			for (int i = 0; i < s.length; i++) {

				String s1 = s[i].replace("#", "");
				s1 = s1.replace("&", "");
				s1 = s1.trim();
				char c = (char) Integer.parseInt(s1);
				System.out.println(s.length + "" + c + "----" + s1);
				out += "" + c;

			}
			System.out.println("Out-------->" + out);
		} catch (Exception e) {
			System.out.println("Exception -----> " + e);
		}
		return out;

	}

	public void setK_alias_41(String k_alias_41) {
		this.k_alias_41 = k_alias_41;
	}

	@Transient
	private String k_alias_51;

	public String getK_alias_51() {
		String out = "";
		k_alias_51 = k_alias_51.replace(" ", "");
		String[] s = k_alias_51.split(";");
		// String[] s = a.split(";");
		try {
			for (int i = 0; i < s.length; i++) {

				String s1 = s[i].replace("#", "");
				s1 = s1.replace("&", "");
				s1 = s1.trim();
				char c = (char) Integer.parseInt(s1);
				System.out.println(s.length + "" + c + "----" + s1);
				out += "" + c;

			}
			System.out.println("Out-------->" + out);
		} catch (Exception e) {
			System.out.println("Exception -----> " + e);
		}
		return out;

	}

	public void setK_alias_51(String k_alias_51) {
		this.k_alias_51 = k_alias_51;
	}

	@Transient
	private String k_alias_61;

	public String getK_alias_61() {
		String out = "";
		k_alias_61 = k_alias_61.replace(" ", "");
		String[] s = k_alias_61.split(";");
		// String[] s = a.split(";");
		try {
			for (int i = 0; i < s.length; i++) {

				String s1 = s[i].replace("#", "");
				s1 = s1.replace("&", "");
				s1 = s1.trim();
				char c = (char) Integer.parseInt(s1);
				System.out.println(s.length + "" + c + "----" + s1);
				out += "" + c;

			}
			System.out.println("Out-------->" + out);
		} catch (Exception e) {
			System.out.println("Exception -----> " + e);
		}
		return out;

	}

	public void setK_alias_61(String k_alias_61) {
		this.k_alias_61 = k_alias_61;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group_stop")
	private Set<BusStopGroup> group_bus_stop;

	public Set<BusStopGroup> getGroup_bus_stop() {
		return group_bus_stop;
	}

	public void setGroup_bus_stop(Set<BusStopGroup> group_bus_stop) {
		this.group_bus_stop = group_bus_stop;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bus_stop_group_stop")
	private Set<BusStopGroupStop> BusStopGroupStop;

	public Set<BusStopGroupStop> getBusStopGroupStop() {
		return BusStopGroupStop;
	}

	public void setBusStopGroupStop(Set<BusStopGroupStop> busStopGroupStop) {
		BusStopGroupStop = busStopGroupStop;
	}

	@ManyToOne
	@JoinColumn(name = "point_type_id")
	private PointType pointtype;

	@Column(name = "toll_fee")
	private Double toll_fee;

	public Double getToll_fee() {
		return toll_fee;
	}

	public void setToll_fee(Double toll_fee) {
		this.toll_fee = toll_fee;
	}

	@Transient
	public String frombusStopName;
	@Transient
	public String tobusStopName;

	public String getFrombusStopName() {
		return frombusStopName;
	}

	public void setFrombusStopName(String frombusStopName) {
		this.frombusStopName = frombusStopName;
	}

	public String getTobusStopName() {
		return tobusStopName;
	}

	public void setTobusStopName(String tobusStopName) {
		this.tobusStopName = tobusStopName;
	}
	@Transient
	public String busStopNamenudi;

	public String getBusStopNamenudi() {
		return busStopNamenudi;
	}

	public void setBusStopNamenudi(String busStopNamenudi) {
		this.busStopNamenudi = busStopNamenudi;
	}
	
	@Column(name = "bus_stop_name_nudi")
	private String bus_stop_name_nudi;

	public String getBus_stop_name_nudi() {
		return bus_stop_name_nudi;
	}

	public void setBus_stop_name_nudi(String bus_stop_name_nudi) {
		this.bus_stop_name_nudi = bus_stop_name_nudi;
	}

}
