package com.trimax.its.vts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vts_parse_data")
public class VtsDataModel {

	public VtsDataModel() {

	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int ID;

	@Column(name = "PKT_HEADER")
	private String PKT_HEADER;

	@Column(name = "DEVICE_ID")
	private String DEVICE_ID;

	@Column(name = "PACKET_CODE")
	private String PACKET_CODE;
	
	@Column(name = "MISC_BYTES")
	private String MISC_BYTES;

	@Column(name = "IGN_STATUS")
	private int IGN_STATUS;


	@Column(name = "ACC_DISTANCE")
	private String ACC_DISTANCE;

	@Column(name = "SIGNAL_STRENGTH")
	private int SIGNAL_STRENGTH;
	
	@Column(name = "COUNTRY_CODE")
	private String COUNTRY_CODE	;
	
	@Column(name = "NETWORK_CODE")
	private String NETWORK_CODE	;
	
	@Column(name = "LOCATION_AREA_CODE")
	private String LOCATION_AREA_CODE;
	
	@Column(name = "CELL_ID")
	private String CELL_ID;
	
	@Column(name = "NO_SATELLITE_IN_VIEW")
	private int NO_SATELLITE_IN_VIEW;
	
	
	private String EXT_BATTERY_VOLTAGE;
	
	private String INTERNAL_BATTERY_VTG;
	
	private String SENSOR_INFO;
	
	private String DIGITAL_INPUT;
	
	private Date IST_TIME;
	
	private String DIGITAL_OUTPUT;
	
	private String ANALOG_INPUT;
	
	private String MAX_SPEED;
	
	private String GPRMC_HEADER;
	
	private String DATA_STATUS;
	
	private String LAT_DEGREE;
	
	private String NS;
	private String LAT;
	private String LONGITUDE_DEGREE	;
	private String EW;
	private String LONGITUDE;
	private String VEHICLE_DIRECTION;
	private String SPEED_KNOTS;
	private String SPEED_KMPH;
	private String GMT_TIME;
	private String GMT_DATE;
	private String IST_DATE;
	private String VARIATION_SENSE;
	private String MAGNETIC_VARIATION;
	private String CHECKSUM_HEX;
	private String PARSER_VERSION;
	private String SERVER_PORT;
	private String PARSER_VERSION_DATE;
	private String RECORD_STATUS;
	private String STARTTIME;
	private String ENDTIME;
	private String SCHEDULENAME;
	private String VEHICLE_NO;
	private int TRIPNUMBER;
	private String DEPOTNAME;
	
	
	public String getDEPOTNAME() {
		return DEPOTNAME;
	}
	public void setDEPOTNAME(String dEPOTNAME) {
		DEPOTNAME = dEPOTNAME;
	}
	public int getTRIPNUMBER() {
		return TRIPNUMBER;
	}
	public void setTRIPNUMBER(int tRIPNUMBER) {
		TRIPNUMBER = tRIPNUMBER;
	}
	public String getVEHICLE_NO() {
		return VEHICLE_NO;
	}
	public void setVEHICLE_NO(String vEHICLE_NO) {
		VEHICLE_NO = vEHICLE_NO;
	}
	public String getSCHEDULENAME() {
		return SCHEDULENAME;
	}
	public void setSCHEDULENAME(String sCHEDULENAME) {
		SCHEDULENAME = sCHEDULENAME;
	}
	public String getSTARTTIME() {
		return STARTTIME;
	}
	public void setSTARTTIME(String sTARTTIME) {
		STARTTIME = sTARTTIME;
	}
	public String getENDTIME() {
		return ENDTIME;
	}
	public void setENDTIME(String eNDTIME) {
		ENDTIME = eNDTIME;
	}
	public int getId() {
		return ID;
	}
	public void setId(int id) {
		ID = id;
	}
	public String getPKT_HEADER() {
		return PKT_HEADER;
	}
	public void setPKT_HEADER(String pKT_HEADER) {
		PKT_HEADER = pKT_HEADER;
	}
	public String getDEVICE_ID() {
		return DEVICE_ID;
	}
	public void setDEVICE_ID(String dEVICE_ID) {
		DEVICE_ID = dEVICE_ID;
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
	public int getIGN_STATUS() {
		return IGN_STATUS;
	}
	public void setIGN_STATUS(int iGN_STATUS) {
		IGN_STATUS = iGN_STATUS;
	}
	public String getACC_DISTANCE() {
		return ACC_DISTANCE;
	}
	public void setACC_DISTANCE(String aCC_DISTANCE) {
		ACC_DISTANCE = aCC_DISTANCE;
	}
	public int getSIGNAL_STRENGTH() {
		return SIGNAL_STRENGTH;
	}
	public void setSIGNAL_STRENGTH(int sIGNAL_STRENGTH) {
		SIGNAL_STRENGTH = sIGNAL_STRENGTH;
	}
	public String getCOUNTRY_CODE() {
		return COUNTRY_CODE;
	}
	public void setCOUNTRY_CODE(String cOUNTRY_CODE) {
		COUNTRY_CODE = cOUNTRY_CODE;
	}
	public String getNETWORK_CODE() {
		return NETWORK_CODE;
	}
	public void setNETWORK_CODE(String nETWORK_CODE) {
		NETWORK_CODE = nETWORK_CODE;
	}
	public String getLOCATION_AREA_CODE() {
		return LOCATION_AREA_CODE;
	}
	public void setLOCATION_AREA_CODE(String lOCATION_AREA_CODE) {
		LOCATION_AREA_CODE = lOCATION_AREA_CODE;
	}
	public String getCELL_ID() {
		return CELL_ID;
	}
	public void setCELL_ID(String cELL_ID) {
		CELL_ID = cELL_ID;
	}
	public int getNO_SATELLITE_IN_VIEW() {
		return NO_SATELLITE_IN_VIEW;
	}
	public void setNO_SATELLITE_IN_VIEW(int nO_SATELLITE_IN_VIEW) {
		NO_SATELLITE_IN_VIEW = nO_SATELLITE_IN_VIEW;
	}
	public String getEXT_BATTERY_VOLTAGE() {
		return EXT_BATTERY_VOLTAGE;
	}
	public void setEXT_BATTERY_VOLTAGE(String eXT_BATTERY_VOLTAGE) {
		EXT_BATTERY_VOLTAGE = eXT_BATTERY_VOLTAGE;
	}
	public String getINTERNAL_BATTERY_VTG() {
		return INTERNAL_BATTERY_VTG;
	}
	public void setINTERNAL_BATTERY_VTG(String iNTERNAL_BATTERY_VTG) {
		INTERNAL_BATTERY_VTG = iNTERNAL_BATTERY_VTG;
	}
	public String getSENSOR_INFO() {
		return SENSOR_INFO;
	}
	public void setSENSOR_INFO(String sENSOR_INFO) {
		SENSOR_INFO = sENSOR_INFO;
	}
	public String getDIGITAL_INPUT() {
		return DIGITAL_INPUT;
	}
	public void setDIGITAL_INPUT(String dIGITAL_INPUT) {
		DIGITAL_INPUT = dIGITAL_INPUT;
	}
	public Date getIST_TIME() {
		return IST_TIME;
	}
	public void setIST_TIME(Date iST_TIME) {
		IST_TIME = iST_TIME;
	}
	public String getDIGITAL_OUTPUT() {
		return DIGITAL_OUTPUT;
	}
	public void setDIGITAL_OUTPUT(String dIGITAL_OUTPUT) {
		DIGITAL_OUTPUT = dIGITAL_OUTPUT;
	}
	public String getANALOG_INPUT() {
		return ANALOG_INPUT;
	}
	public void setANALOG_INPUT(String aNALOG_INPUT) {
		ANALOG_INPUT = aNALOG_INPUT;
	}
	public String getMAX_SPEED() {
		return MAX_SPEED;
	}
	public void setMAX_SPEED(String mAX_SPEED) {
		MAX_SPEED = mAX_SPEED;
	}
	public String getGPRMC_HEADER() {
		return GPRMC_HEADER;
	}
	public void setGPRMC_HEADER(String gPRMC_HEADER) {
		GPRMC_HEADER = gPRMC_HEADER;
	}
	public String getDATA_STATUS() {
		return DATA_STATUS;
	}
	public void setDATA_STATUS(String dATA_STATUS) {
		DATA_STATUS = dATA_STATUS;
	}
	public String getLAT_DEGREE() {
		return LAT_DEGREE;
	}
	public void setLAT_DEGREE(String lAT_DEGREE) {
		LAT_DEGREE = lAT_DEGREE;
	}
	public String getNS() {
		return NS;
	}
	public void setNS(String nS) {
		NS = nS;
	}
	public String getLAT() {
		return LAT;
	}
	public void setLAT(String lAT) {
		LAT = lAT;
	}
	public String getLONGITUDE_DEGREE() {
		return LONGITUDE_DEGREE;
	}
	public void setLONGITUDE_DEGREE(String lONGITUDE_DEGREE) {
		LONGITUDE_DEGREE = lONGITUDE_DEGREE;
	}
	public String getEW() {
		return EW;
	}
	public void setEW(String eW) {
		EW = eW;
	}
	public String getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public String getVEHICLE_DIRECTION() {
		return VEHICLE_DIRECTION;
	}
	public void setVEHICLE_DIRECTION(String vEHICLE_DIRECTION) {
		VEHICLE_DIRECTION = vEHICLE_DIRECTION;
	}
	public String getSPEED_KNOTS() {
		return SPEED_KNOTS;
	}
	public void setSPEED_KNOTS(String sPEED_KNOTS) {
		SPEED_KNOTS = sPEED_KNOTS;
	}
	public String getSPEED_KMPH() {
		return SPEED_KMPH;
	}
	public void setSPEED_KMPH(String sPEED_KMPH) {
		SPEED_KMPH = sPEED_KMPH;
	}
	public String getGMT_TIME() {
		return GMT_TIME;
	}
	public void setGMT_TIME(String gMT_TIME) {
		GMT_TIME = gMT_TIME;
	}
	public String getGMT_DATE() {
		return GMT_DATE;
	}
	public void setGMT_DATE(String gMT_DATE) {
		GMT_DATE = gMT_DATE;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getIST_DATE() {
		return IST_DATE;
	}
	public void setIST_DATE(String iST_DATE) {
		IST_DATE = iST_DATE;
	}
	/*public Date getIST_DATE() {
		return IST_DATE;
	}
	public void setIST_DATE(Date iST_DATE) {
		IST_DATE = iST_DATE;
	}*/
	public String getVARIATION_SENSE() {
		return VARIATION_SENSE;
	}
	public void setVARIATION_SENSE(String vARIATION_SENSE) {
		VARIATION_SENSE = vARIATION_SENSE;
	}
	public String getMAGNETIC_VARIATION() {
		return MAGNETIC_VARIATION;
	}
	public void setMAGNETIC_VARIATION(String mAGNETIC_VARIATION) {
		MAGNETIC_VARIATION = mAGNETIC_VARIATION;
	}
	public String getCHECKSUM_HEX() {
		return CHECKSUM_HEX;
	}
	public void setCHECKSUM_HEX(String cHECKSUM_HEX) {
		CHECKSUM_HEX = cHECKSUM_HEX;
	}
	public String getPARSER_VERSION() {
		return PARSER_VERSION;
	}
	public void setPARSER_VERSION(String pARSER_VERSION) {
		PARSER_VERSION = pARSER_VERSION;
	}
	public String getSERVER_PORT() {
		return SERVER_PORT;
	}
	public void setSERVER_PORT(String sERVER_PORT) {
		SERVER_PORT = sERVER_PORT;
	}
	public String getPARSER_VERSION_DATE() {
		return PARSER_VERSION_DATE;
	}
	public void setPARSER_VERSION_DATE(String pARSER_VERSION_DATE) {
		PARSER_VERSION_DATE = pARSER_VERSION_DATE;
	}
	public String getRECORD_STATUS() {
		return RECORD_STATUS;
	}
	public void setRECORD_STATUS(String rECORD_STATUS) {
		RECORD_STATUS = rECORD_STATUS;
	}
	
	
	
	
	
	/*
	
	@Column(name = "longitude")
	private double Longitude;

	@Column(name = "we")
	// West-East
	private String We;

	@Column(name = "gps_date_time")
	private String gpsDateTime;

	@Column(name = "gps_status")
	private char GpsStatus;

	@Column(name = "distance")
	private double Distance;

	@Column(name = "delta_distance")
	private double DeltaDistance;

	@Column(name = "speed")
	private double Speed;

	@Column(name = "heading")
	private double Heading;

	@Column(name = "nmr")
	private String Nmr;

	@Column(name = "network_date_time")
	private String NetworkDateTime;

	@Column(name = "network_code")
	private String NetworkCode;

	@Column(name = "location_area_code")
	private String LocationAreaCode;

	@Column(name = "celld")
	private String CellId;

	@Column(name = "receive_signal_strength")
	private String ReceiveStringStrength;

	@Column(name = "csq")
	private String Csq;

	@Column(name = "csq1")
	private String Csq1;

	@Column(name = "digital_input")
	private String DigitalInput;

	@Column(name = "digital_outpur")
	private String DigitalOutPut;

	@Column(name = "analogue_input")
	private String AnalogueInput;

	@Column(name = "created_date_time")
	private String CreatedDateTime;

	@Column(name = "rout_deviation")
	private String RouteDeviation;

	@Column(name = "point_id")
	private String PointId;

	@Column(name = "deviation")
	private int Deviation;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMessageId() {
		return MessageId;
	}

	public void setMessageId(String messageId) {
		MessageId = messageId;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getDeviceIMEI() {
		return DeviceIMEI;
	}

	public void setDeviceIMEI(String deviceIMEI) {
		DeviceIMEI = deviceIMEI;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public String getNs() {
		return Ns;
	}

	public void setNs(String ns) {
		Ns = ns;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public String getWe() {
		return We;
	}

	public void setWe(String we) {
		We = we;
	}

	public String getGpsDateTime() {
		return gpsDateTime;
	}

	public void setGpsDateTime(String gpsDateTime) {
		this.gpsDateTime = gpsDateTime;
	}

	public char getGpsStatus() {
		return GpsStatus;
	}

	public void setGpsStatus(char gpsStatus) {
		GpsStatus = gpsStatus;
	}

	public double getDistance() {
		return Distance;
	}

	public void setDistance(double distance) {
		Distance = distance;
	}

	public double getDeltaDistance() {
		return DeltaDistance;
	}

	public void setDeltaDistance(double deltaDistance) {
		DeltaDistance = deltaDistance;
	}

	public double getSpeed() {
		return Speed;
	}

	public void setSpeed(double speed) {
		Speed = speed;
	}

	public double getHeading() {
		return Heading;
	}

	public void setHeading(double heading) {
		Heading = heading;
	}

	public String getNmr() {
		return Nmr;
	}

	public void setNmr(String nmr) {
		Nmr = nmr;
	}

	public String getNetworkDateTime() {
		return NetworkDateTime;
	}

	public void setNetworkDateTime(String networkDateTime) {
		NetworkDateTime = networkDateTime;
	}

	public String getNetworkCode() {
		return NetworkCode;
	}

	public void setNetworkCode(String networkCode) {
		NetworkCode = networkCode;
	}

	public String getLocationAreaCode() {
		return LocationAreaCode;
	}

	public void setLocationAreaCode(String locationAreaCode) {
		LocationAreaCode = locationAreaCode;
	}

	public String getCellId() {
		return CellId;
	}

	public void setCellId(String cellId) {
		CellId = cellId;
	}

	public String getReceiveStringStrength() {
		return ReceiveStringStrength;
	}

	public void setReceiveStringStrength(String receiveStringStrength) {
		ReceiveStringStrength = receiveStringStrength;
	}

	public String getCsq() {
		return Csq;
	}

	public void setCsq(String csq) {
		Csq = csq;
	}

	public String getCsq1() {
		return Csq1;
	}

	public void setCsq1(String csq1) {
		Csq1 = csq1;
	}

	public String getDigitalInput() {
		return DigitalInput;
	}

	public void setDigitalInput(String digitalInput) {
		DigitalInput = digitalInput;
	}

	public String getDigitalOutPut() {
		return DigitalOutPut;
	}

	public void setDigitalOutPut(String digitalOutPut) {
		DigitalOutPut = digitalOutPut;
	}

	public String getAnalogueInput() {
		return AnalogueInput;
	}

	public void setAnalogueInput(String analogueInput) {
		AnalogueInput = analogueInput;
	}

	public String getCreatedDateTime() {
		return CreatedDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		CreatedDateTime = createdDateTime;
	}

	public String getRouteDeviation() {
		return RouteDeviation;
	}

	public void setRouteDeviation(String routeDeviation) {
		RouteDeviation = routeDeviation;
	}

	public String getPointId() {
		return PointId;
	}

	public void setPointId(String pointId) {
		PointId = pointId;
	}

	public int getDeviation() {
		return Deviation;
	}

	public void setDeviation(int deviation) {
		Deviation = deviation;
	}
*/
	
	//Setters and Getters....
	
	
	
	
	
	//
	
	
	
	
	
}
