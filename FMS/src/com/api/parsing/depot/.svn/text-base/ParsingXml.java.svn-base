package com.api.parsing.depot;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.trimax.its.common.Common;
import com.trimax.its.vehicle.model.BodyType;
import com.trimax.its.vehicle.model.MakeType;
import com.trimax.its.vehicle.model.ModelType;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.Vehicle;



public class ParsingXml {
	FleetDetailsDepotwise Fddw = new FleetDetailsDepotwise();
	
	MakeType makeType=new MakeType();
	Common common=new Common();
	private List makeList;

	public FleetDetailsDepotwise getFddw() {
		return Fddw;
	}

	public void setFddw(FleetDetailsDepotwise fddw) {
		Fddw = fddw;
	}

	/*public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}*/
	

	public MakeType getMakeType() {
		return makeType;
	}

	public void setMakeType(MakeType makeType) {
		this.makeType = makeType;
	}

	public List getMakeList() {
		return makeList;
	}

	public void setMakeList(List makeList) {
		this.makeList = makeList;
	}

	public String getxmldata(String url) throws ParserConfigurationException,
			SAXException, IOException {

		// System.out.println("In XML ");
		String result = "";
		FleetDetailsDepotwiseDao dao = new FleetDetailsDepotwiseDao();		
		int id = 0;

		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(url);

		doc.getDocumentElement().normalize();
		// System.out.println ("Root element: " +
		// doc.getDocumentElement().getNodeName());

		// loop through each item
		NodeList items = doc.getElementsByTagName("item");
		// System.out.println("Items Length----->"+items.getLength());
		for (int i = 1; i <items.getLength(); i++) {
			Vehicle vehicle = null;
			
			Node n = items.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				
				
				//dao.checkNewVehicle(vehicleObject);
				// fm_reg_no
				NodeList fm_reg_noList = e.getElementsByTagName("fm_reg_no");
				Element fm_reg_noelement = (Element) fm_reg_noList.item(0);
				NodeList textfm_reg_noList = fm_reg_noelement.getChildNodes();
				result= textfm_reg_noList.item(0).getNodeValue().trim();
				vehicle =dao.checkNewVehicle(result);
				System.out.println(vehicle);
				if (vehicle ==null) {
					vehicle = new Vehicle();
//					id = dao.saveParseData(vehicle);
					System.out.println("NEW.............");
				} 
				else {
//					dao.updateParseData(vehicle);
					System.out.println(" Vehicle Creation"+vehicle.getVehicleRegistrationNumber());
				}

				if (textfm_reg_noList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_reg_noList.item(0).getNodeValue().trim());
					
					vehicle.setVehicleRegistrationNumber(textfm_reg_noList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					vehicle.setVehicleRegistrationNumber("");
				}
				
				// fm_chassis_no
				NodeList fm_chassis_noList = e.getElementsByTagName("fm_chassis_no");
				Element fm_chassis_noelement = (Element) fm_chassis_noList.item(0);
				NodeList textfm_chassis_noList = fm_chassis_noelement.getChildNodes();
				if (textfm_chassis_noList.getLength() > 0) {
					/*
					 * System.out.println("fm_chassis_no-----> "+
					 * textfm_chassis_noList.item(0).getNodeValue().trim());
					 * result+=
					 * textfm_chassis_noList.item(0).getNodeValue().trim();
					 */
					vehicle.setChasisNumber(textfm_chassis_noList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					vehicle.setChasisNumber("");
				}
				
				NodeList fm_po_no_dateList = e.getElementsByTagName("fm_po_no_date");
				Element fm_po_no_dateelement = (Element) fm_po_no_dateList.item(0);
				NodeList textfm_po_no_dateList = fm_po_no_dateelement.getChildNodes();
				if (textfm_po_no_dateList.getLength() > 0) {
					/*
					 * System.out.println("fm_po_no_date-----> "+
					 * textfm_po_no_dateList.item(0).getNodeValue().trim());
					 * result
					 * +=textfm_po_no_dateList.item(0).getNodeValue().trim
					 * ()+",";
					 */					
					 result= textfm_po_no_dateList.item(0).getNodeValue().trim();
					 if(!result.equals(common.getDateFromApi(result))){
						//common.getDateFromApi(result);	
						// vehicle.setProceduredDate(null);					
					 }else{
						 
						 vehicle.setProceduredDate(common.getDateFromApi(result));	
					 }
				} else {
					
					vehicle.setProceduredDate(null);	
				}
				
				// For fm_chassis_make
				NodeList fm_chassis_makeList = e.getElementsByTagName("fm_chassis_make");
				Element fm_chassis_makeelement = (Element) fm_chassis_makeList.item(0);
				NodeList textchassis_makeList = fm_chassis_makeelement.getChildNodes();
				if (textchassis_makeList.getLength() > 0) {
					/*
					 * System.out.println("fm_chassis_make-----> "+
					 * textchassis_makeList.item(0).getNodeValue().trim());*/
					  //result +=textchassis_makeList.item(0).getNodeValue().trim()+",";
					List<MakeType> maketypeList = dao.getMakeTypeList(textchassis_makeList.item(0).getNodeValue().trim());
					 result=textchassis_makeList.item(0).getNodeValue().trim();
					 if(!dao.checkMakeType(result)){
					 dao.saveMakeTypeData(result);
					 }
					/*if (maketypeList.isEmpty()) {
						continue;
					}*/
					for (MakeType makeType : maketypeList) {
						//System.out.println("Size------"+makeType.size());
						vehicle.setMakeType(makeType);
					}
					
				} else {
					vehicle.setMakeType(null);
					
				}
				
				// fm_body_make
				NodeList fm_body_makeList = e.getElementsByTagName("fm_body_make");
				Element fm_body_makeelement = (Element) fm_body_makeList.item(0);
				NodeList textfm_body_makeList = fm_body_makeelement.getChildNodes();
				if (textfm_body_makeList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_body_makeList.item(0).getNodeValue().trim());
					 result=textfm_body_makeList.item(0).getNodeValue().trim();
					 if(!dao.checkBodyType(result)){
					 dao.saveBodyTypeData(result);
					 }
					//Fddw.setFm_body_make(textfm_body_makeList.item(0).getNodeValue().trim());
					List<BodyType> BodyTypeList = dao.getBodyTypeList(result);
					for (BodyType bodyType : BodyTypeList) {
						//System.out.println("Size------"+makeType.size());
						vehicle.setBodyType(bodyType);
					}
				} else {
					// result+= "";
					vehicle.setBodyType(null);
				}
				// For fm_manufacture_model
				NodeList etdList = e.getElementsByTagName("fm_manufacture_model");
				Element etdelement = (Element) etdList.item(0);
				NodeList textetdList = etdelement.getChildNodes();
				if (textetdList.getLength() > 0) {					
					//Fddw.setFm_manufacture_model(textetdList.item(0).getNodeValue().trim());
					result=textetdList.item(0).getNodeValue().trim();
					 if(!dao.checkModelType(result)){
					 dao.saveModelTypeData(result);
					 }
					//Fddw.setFm_body_make(textfm_body_makeList.item(0).getNodeValue().trim());
					List<ModelType> modelTypeList = dao.getModelTypeList(result);
					for (ModelType modelType : modelTypeList) {
						//System.out.println("Size------"+makeType.size());
						vehicle.setModelType(modelType);
					}
				} else {
					// result+= "";
					vehicle.setModelType(null);
				}

				// fm_its_dep_rec_id
				NodeList fm_its_dep_rec_idList = e.getElementsByTagName("fm_its_dep_rec_id");
				Element fm_its_dep_rec_idelement = (Element) fm_its_dep_rec_idList.item(0);
				NodeList textfm_its_dep_rec_idList = fm_its_dep_rec_idelement.getChildNodes();
				if (textfm_its_dep_rec_idList.getLength() > 0) {
					// System.out.println("fm_its_dep_rec_id-----> "+textfm_its_dep_rec_idList.item(0).getNodeValue().trim());
					   result = textfm_its_dep_rec_idList.item(0).getNodeValue().trim();
					   OrganisationChart org=new OrganisationChart();
					   org.setOrg_chart_id(Integer.parseInt(result));
					if (result.isEmpty()) {
						vehicle.setDepotOrUnit(null);
					}else {
						vehicle.setDepotOrUnit(org);
					}
				} else {
					// result+= "";
					vehicle.setDepotOrUnit(null);
				}
				
				// fm_fc_expiry_date
				NodeList fm_fc_expiry_dateList = e.getElementsByTagName("fm_fc_expiry_date");
				Element fm_fc_expiry_dateelement = (Element) fm_fc_expiry_dateList.item(0);
				NodeList textfm_fc_expiry_dateList = fm_fc_expiry_dateelement.getChildNodes();
				if (textfm_fc_expiry_dateList.getLength() > 0) {					
					 //System.out.println("fm_wheel_base-----> "+textfm_fc_expiry_dateList.item(0).getNodeValue().trim());
					 result= textfm_fc_expiry_dateList.item(0).getNodeValue().trim();
					 if(result.equals("0000-00-00")){
						 
						//vehicle.setFcExpiryDate(null);					
					 }else{
						 vehicle.setFcExpiryDate(common.getDateFromApi(result));	
					 }
				} else {
					
					vehicle.setFcExpiryDate(null);
				}
				
				// fm_date_of_commissioning
				NodeList fm_date_of_commissioningList = e.getElementsByTagName("fm_date_of_commissioning");
				Element fm_date_of_commissioningelement = (Element) fm_date_of_commissioningList.item(0);
				NodeList textfm_date_of_commissioningList = fm_date_of_commissioningelement.getChildNodes();
				if (textfm_date_of_commissioningList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_date_of_commissioningList.item(0).getNodeValue().trim());
					// result+=
					// textfm_date_of_commissioningList.item(0).getNodeValue().trim();				
					 result= textfm_date_of_commissioningList.item(0).getNodeValue().trim();
					 if(result.equals("0000-00-00")){
						 
						//vehicle.setOperationalDate(null);					
					 }else{
						 vehicle.setOperationalDate(common.getDateFromApi(result));	
					 }
				} else {
					
					vehicle.setOperationalDate(null);
				}
				
				// fm_reg_date
				NodeList fm_reg_dateList = e.getElementsByTagName("fm_reg_date");
				Element fm_reg_dateelement = (Element) fm_reg_dateList.item(0);
				NodeList textfm_reg_dateList = fm_reg_dateelement.getChildNodes();
				if (textfm_reg_dateList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_reg_dateList.item(0).getNodeValue().trim());
					// result+=
					// textfm_reg_dateList.item(0).getNodeValue().trim();					
					result= textfm_reg_dateList.item(0).getNodeValue().trim();	
					if(result.equals("0000-00-00")){
						 
						//vehicle.setRegistrationDate(null);					
					 }else{
					vehicle.setRegistrationDate(common.getDateFromApi(result));					
					 }
			   } else {
				
				vehicle.setRegistrationDate(null);
			   }
				
				// fm_createdbyList
				NodeList fm_createdbyList = e.getElementsByTagName("fm_createdby");
				Element fm_createdbyelement = (Element) fm_createdbyList.item(0);
				NodeList textfm_createdbyList = fm_createdbyelement.getChildNodes();
				if (textfm_createdbyList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_createdbyList.item(0).getNodeValue().trim());
					// result+=
					// textfm_createdbyList.item(0).getNodeValue().trim();
					vehicle.setCreated_by(Integer.parseInt(textfm_createdbyList.item(0).getNodeValue().trim()));
				} else {
					// result+= "";
					vehicle.setCreated_by(null);
				}
				
				// fm_createddt
				NodeList fm_createddtList = e.getElementsByTagName("fm_createddt");
				Element fm_createddtelement = (Element) fm_createddtList.item(0);
				NodeList textfm_createddtList = fm_createddtelement.getChildNodes();
				if (textfm_createddtList.getLength() > 0) {
					 System.out.println("fm_wheel_base-----> "+textfm_createddtList.item(0).getNodeValue().trim());
					 result = textfm_createddtList.item(0).getNodeValue().trim();
					 vehicle.setCreated_date(result);
					
				} else {					
					 vehicle.setCreated_date((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
				}
				
				// fm_fc_plan_date
				NodeList fm_fc_plan_dateList = e.getElementsByTagName("fm_fc_plan_date");
				Element fm_fc_plan_dateelement = (Element) fm_fc_plan_dateList.item(0);
				NodeList textfm_fc_plan_dateList = fm_fc_plan_dateelement.getChildNodes();
				if (textfm_fc_plan_dateList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_fc_plan_dateList.item(0).getNodeValue().trim());
					// result+=
					// textfm_fc_plan_dateList.item(0).getNodeValue().trim();
					//Fddw.setFcExpiryDateString(textfm_fc_plan_dateList.item(0).getNodeValue().trim());
					    result= textfm_fc_plan_dateList.item(0).getNodeValue().trim();					
							
						if(result.equals("0000-00-00")){
							 
							//vehicle.setDockingPlanningDate(null);					
						 }else{
							 vehicle.setDockingPlanningDate(common.getDateFromApi(result));					
						 }
				   } else {
					
					vehicle.setDockingPlanningDate(null);
				   }
				
				// fm_modifieddt
				NodeList fm_modifieddtList = e.getElementsByTagName("fm_modifieddt");
				Element fm_modifieddtelement = (Element) fm_modifieddtList.item(0);
				NodeList textfm_modifieddtList = fm_modifieddtelement.getChildNodes();
				if (textfm_modifieddtList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_modifieddtList.item(0).getNodeValue().trim());
					// result+=
					// textfm_modifieddtList.item(0).getNodeValue().trim();
					vehicle.setUpdated_date(textfm_modifieddtList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					vehicle.setUpdated_date("");
				}
				// fm__modifiedby
				NodeList fm__modifiedbyList = e.getElementsByTagName("fm__modifiedby");
				Element fm__modifiedbyelement = (Element) fm__modifiedbyList.item(0);
				NodeList textfm__modifiedbyList = fm__modifiedbyelement.getChildNodes();
				if (textfm__modifiedbyList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm__modifiedbyList.item(0).getNodeValue().trim());
					// result+=
					// textfm__modifiedbyList.item(0).getNodeValue().trim();
					vehicle.setUpdated_by(Integer.parseInt(textfm__modifiedbyList.item(0).getNodeValue().trim()));
				} else {
					// result+= "";
					vehicle.setUpdated_by(Integer.parseInt(""));
				}
				
				
				// For fm_rec_id
				NodeList fm_rec_idList = e.getElementsByTagName("fm_rec_id");
				Element fm_rec_idelement = (Element) fm_rec_idList.item(0);
				NodeList textIdList = fm_rec_idelement.getChildNodes();
				if (textIdList.getLength() > 0) {
					/*
					 * System.out.println("fm_rec_id-----> "+textIdList.item(0).
					 * getNodeValue().trim()); result
					 * +=textIdList.item(0).getNodeValue().trim()+",";
					 */
					Fddw.setFm_rec_id(textIdList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_rec_id(" ");
				}					
							
				// fm_engine_no
				NodeList fm_engine_noList = e.getElementsByTagName("fm_engine_no");
				Element fm_engine_noelement = (Element) fm_engine_noList.item(0);
				NodeList textfm_engine_noList = fm_engine_noelement.getChildNodes();
				if (textfm_engine_noList.getLength() > 0) {
					// System.out.println("fm_engine_no-----> "+textfm_engine_noList.item(0).getNodeValue().trim());
					// result+=
					// textfm_engine_noList.item(0).getNodeValue().trim();
					Fddw.setFm_engine_no(textfm_engine_noList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_engine_no("");
				}

				// fm_horse_power
				NodeList fm_horse_powerList = e.getElementsByTagName("fm_horse_power");
				Element fm_horse_powerelement = (Element) fm_horse_powerList.item(0);
				NodeList textfm_horse_powerList = fm_horse_powerelement.getChildNodes();
				if (textfm_horse_powerList.getLength() > 0) {
					// System.out.println("fm_horse_power-----> "+textfm_horse_powerList.item(0).getNodeValue().trim());
					// result+=
					// textfm_horse_powerList.item(0).getNodeValue().trim();
					Fddw.setFm_horse_power(textfm_horse_powerList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_horse_power("");
				}

				// fm_fip
				NodeList fm_fipList = e.getElementsByTagName("fm_fip");
				Element fm_fipelement = (Element) fm_fipList.item(0);
				NodeList textfm_fipList = fm_fipelement.getChildNodes();
				if (textfm_fipList.getLength() > 0) {
					// System.out.println("fm_fip-----> "+textfm_fipList.item(0).getNodeValue().trim());
					// result+= textfm_fipList.item(0).getNodeValue().trim();
					Fddw.setFm_fip(textfm_fipList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_fip("");
				}
				// fm_wheel_base
				NodeList fm_wheel_baseList = e.getElementsByTagName("fm_wheel_base");
				Element fm_wheel_baseelement = (Element) fm_wheel_baseList.item(0);
				NodeList textfm_wheel_baseList = fm_wheel_baseelement.getChildNodes();
				if (textfm_wheel_baseList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_wheel_baseList.item(0).getNodeValue().trim());
					// result+=
					// textfm_wheel_baseList.item(0).getNodeValue().trim();
					Fddw.setFm_wheel_base(textfm_wheel_baseList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_wheel_base("");
				}

				
				// fm_body_built_by
				NodeList fm_body_built_byList = e.getElementsByTagName("fm_body_built_by");
				Element fm_body_built_byelement = (Element) fm_body_built_byList.item(0);
				NodeList textfm_body_built_byList = fm_body_built_byelement.getChildNodes();
				if (textfm_body_built_byList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_body_built_byList.item(0).getNodeValue().trim());
					// result+=
					// textfm_body_built_byList.item(0).getNodeValue().trim();
					Fddw.setFm_body_built_by(textfm_body_built_byList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_body_built_by("");
				}

				// fm_engine_make
				NodeList fm_engine_makeList = e.getElementsByTagName("fm_engine_make");
				Element fm_engine_makeelement = (Element) fm_engine_makeList.item(0);
				NodeList textffm_engine_makeList = fm_engine_makeelement.getChildNodes();
				if (textffm_engine_makeList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textffm_engine_makeList.item(0).getNodeValue().trim());
					// result+=
					// textffm_engine_makeList.item(0).getNodeValue().trim();
					Fddw.setFm_engine_make(textffm_engine_makeList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_engine_make("");
				}

				// fm_engine_type
				NodeList fm_engine_typeList = e.getElementsByTagName("fm_engine_type");
				Element fm_engine_typeelement = (Element) fm_engine_typeList.item(0);
				NodeList textfm_engine_typeList = fm_engine_typeelement.getChildNodes();
				if (textfm_engine_typeList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_engine_typeList.item(0).getNodeValue().trim());
					// result+=
					// textfm_engine_typeList.item(0).getNodeValue().trim();
					Fddw.setFm_engine_type(textfm_engine_typeList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_engine_type("");
				}
				// fm_engine_norms
				NodeList fm_engine_normsList = e.getElementsByTagName("fm_engine_norms");
				Element fm_engine_normselement = (Element) fm_engine_normsList.item(0);
				NodeList textfm_engine_normsList = fm_engine_normselement.getChildNodes();
				if (textfm_engine_normsList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_engine_normsList.item(0).getNodeValue().trim());
					// result+=
					// textfm_engine_normsList.item(0).getNodeValue().trim();
					Fddw.setFm_engine_norms(textfm_engine_normsList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_engine_norms("");
				}
				// fm_seating_capacity
				NodeList fm_seating_capacityList = e.getElementsByTagName("fm_seating_capacity");
				Element fm_seating_capacityelement = (Element) fm_seating_capacityList.item(0);
				NodeList textfm_seating_capacityList = fm_seating_capacityelement.getChildNodes();
				if (textfm_seating_capacityList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_seating_capacityList.item(0).getNodeValue().trim());
					// result+=
					// textfm_seating_capacityList.item(0).getNodeValue().trim();
					Fddw.setFm_seating_capacity(textfm_seating_capacityList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_seating_capacity("");
				}
				// fm_diesel_tank_capacity
				NodeList diesel_tank_capacityList = e.getElementsByTagName("fm_diesel_tank_capacity");
				Element diesel_tank_capacityelement = (Element) diesel_tank_capacityList.item(0);
				NodeList textdiesel_tank_capacityList = diesel_tank_capacityelement.getChildNodes();
				if (textdiesel_tank_capacityList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textdiesel_tank_capacityList.item(0).getNodeValue().trim());
					// result+=
					// textdiesel_tank_capacityList.item(0).getNodeValue().trim();
					Fddw.setFm_diesel_tank_capacity(textdiesel_tank_capacityList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_diesel_tank_capacity("");
				}
				// fm_tyre_type
				NodeList fm_tyre_typeList = e.getElementsByTagName("fm_tyre_type");
				Element fm_tyre_typeelement = (Element) fm_tyre_typeList.item(0);
				NodeList textfm_tyre_typeList = fm_tyre_typeelement.getChildNodes();
				if (textfm_tyre_typeList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_tyre_typeList.item(0).getNodeValue().trim());
					// result+=
					// textfm_tyre_typeList.item(0).getNodeValue().trim();
					Fddw.setFm_tyre_type(textfm_tyre_typeList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_tyre_type("");
				}
				// fm_tyre_size
				NodeList fm_tyre_sizeList = e.getElementsByTagName("fm_tyre_size");
				Element fm_tyre_sizeelement = (Element) fm_tyre_sizeList.item(0);
				NodeList textfm_tyre_sizeList = fm_tyre_sizeelement.getChildNodes();
				if (textfm_tyre_sizeList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_tyre_sizeList.item(0).getNodeValue().trim());
					// result+=
					// textfm_tyre_sizeList.item(0).getNodeValue().trim();
					Fddw.setFm_tyre_size(textfm_tyre_sizeList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_tyre_size("");
				}
				// fm_unladen_weight
				NodeList fm_unladen_weightList = e.getElementsByTagName("fm_unladen_weight");
				Element fm_unladen_weightelement = (Element) fm_unladen_weightList.item(0);
				NodeList textfm_unladen_weightList = fm_unladen_weightelement.getChildNodes();
				if (textfm_unladen_weightList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_unladen_weightList.item(0).getNodeValue().trim());
					// result+=
					// textfm_unladen_weightList.item(0).getNodeValue().trim();
					Fddw.setFm_unladen_weight(textfm_unladen_weightList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_unladen_weight("");
				}
				
				
			
				// fm_status
				NodeList fm_statusList = e.getElementsByTagName("fm_status");
				Element fm_statuselement = (Element) fm_statusList.item(0);
				NodeList textfm_statusList = fm_statuselement.getChildNodes();
				if (textfm_statusList.getLength() > 0) {
					// /
					// System.out.println("fm_wheel_base-----> "+textfm_statusList.item(0).getNodeValue().trim());
					// result+= textfm_statusList.item(0).getNodeValue().trim();
					Fddw.setFm_status(textfm_statusList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_status("");
				}

				// fm_approval
				NodeList fm_approvalList = e.getElementsByTagName("fm_approval");
				Element fm_approvalelement = (Element) fm_approvalList.item(0);
				NodeList textfm_approvalList = fm_approvalelement.getChildNodes();
				if (textfm_approvalList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_approvalList.item(0).getNodeValue().trim());
					// result+=
					// textfm_approvalList.item(0).getNodeValue().trim();
					Fddw.setFm_approval(textfm_approvalList.item(0)
							.getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_approval("");
				}

				// fm_wo_no
				NodeList fm_wo_noList = e.getElementsByTagName("fm_wo_no");
				Element fm_wo_noelement = (Element) fm_wo_noList.item(0);
				NodeList textfm_wo_noList = fm_wo_noelement.getChildNodes();
				if (textfm_wo_noList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_wo_noList.item(0).getNodeValue().trim());
					// result+= textfm_wo_noList.item(0).getNodeValue().trim();
					Fddw.setFm_wo_no(textfm_wo_noList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_wo_no("");
				}
				
				// fm_led_fit_date
				NodeList fm_led_fit_dateList = e.getElementsByTagName("fm_led_fit_date");
				Element fm_led_fit_dateelement = (Element) fm_led_fit_dateList.item(0);
				NodeList textfm_led_fit_dateList = fm_led_fit_dateelement.getChildNodes();
				if (textfm_led_fit_dateList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_led_fit_dateList.item(0).getNodeValue().trim());
					// result+=
					// textfm_led_fit_dateList.item(0).getNodeValue().trim();
					Fddw.setFm_led_fit_date(textfm_led_fit_dateList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_led_fit_date("");
				}
				// fm_led_make
				NodeList fm_led_makeList = e.getElementsByTagName("fm_led_make");
				Element fm_led_makeelement = (Element) fm_led_makeList.item(0);
				NodeList textfm_led_makeList = fm_led_makeelement.getChildNodes();
				if (textfm_led_makeList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_led_makeList.item(0).getNodeValue().trim());
					// result+=
					// textfm_led_makeList.item(0).getNodeValue().trim();
					Fddw.setFm_led_make(textfm_led_makeList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_led_make("");
				}
				
				// fm_camera_fit_date
				NodeList fm_camera_fit_dateList = e.getElementsByTagName("fm_camera_fit_date");
				Element fm_camera_fit_dateelement = (Element) fm_camera_fit_dateList.item(0);
				NodeList textfm_camera_fit_dateList = fm_camera_fit_dateelement.getChildNodes();
				if (textfm_camera_fit_dateList.getLength() > 0) {
					// System.out.println("fm_camera_fit_date-----> "+textfm_camera_fit_dateList.item(0).getNodeValue().trim());
					// result+=
					// textfm_camera_fit_dateList.item(0).getNodeValue().trim();
					Fddw.setFm_camera_fit_date(textfm_camera_fit_dateList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_camera_fit_date("");
				}
				
				// fm_camera_make
				NodeList fm_camera_makeList = e.getElementsByTagName("fm_camera_make");
				Element fm_camera_makeelement = (Element) fm_camera_makeList.item(0);
				NodeList textfm_camera_makeList = fm_camera_makeelement.getChildNodes();
				if (textfm_camera_makeList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_camera_makeList.item(0).getNodeValue().trim());
					// result+=
					// textfm_camera_makeList.item(0).getNodeValue().trim();
					Fddw.setFm_camera_make(textfm_camera_makeList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_camera_make("");
				}
				
				
				
				// fm_its_div_rec_id
				NodeList fm_its_div_rec_idList = e.getElementsByTagName("fm_its_div_rec_id");
				Element fm_its_div_rec_idelement = (Element) fm_its_div_rec_idList.item(0);
				NodeList textfm_its_div_rec_idList = fm_its_div_rec_idelement.getChildNodes();
				if (textfm_its_div_rec_idList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_its_div_rec_idList.item(0).getNodeValue().trim());
					
					 //List<OrganisationChart> orgChartList =  dao.getOrganizationList(textfm_its_div_rec_idList.item(0).getNodeValue().trim());
						//for (OrganisationChart orgChart : orgChartList) {
							//vehicle.setDepotOrUnit(orgChart);
						//}
					
				} else {
					// result+= "";
					//vehicle.setDepotOrUnit(null);
				}
				
				
				// fm_ipaddress
				NodeList fm_ipaddressList = e.getElementsByTagName("fm_ipaddress");
				Element fm_ipaddresselement = (Element) fm_ipaddressList.item(0);
				NodeList textfm_ipaddressList = fm_ipaddresselement.getChildNodes();
				if (textfm_ipaddressList.getLength() > 0) {
					// System.out.println("fm_wheel_base-----> "+textfm_ipaddressList.item(0).getNodeValue().trim());
					// result+=
					// textfm_ipaddressList.item(0).getNodeValue().trim();
					Fddw.setFm_ipaddress(textfm_ipaddressList.item(0).getNodeValue().trim());
				} else {
					// result+= "";
					Fddw.setFm_ipaddress("");
				}
				
				vehicle.setDeleted_status(0);
			}
			
			dao.updateParseData(vehicle);
			
		}
		// System.out.println("--------In XML----- "+result);
		return null;
	}


	public String  fleetInsert(){

		/*//String url = "http://apps.mybmtc.com:8000/API/index.php/drsme_fleet_api/depot/id/all.xml";
		String url = "http://apps.mybmtc.com:8000/API/index.php/drsme_fleet_api/fleet/id/all.xml";
		ParsingXml ap = new ParsingXml();
		try {
			ap.getxmldata(url.toString().substring(0, url.length()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}*/
		// ap.createParseData();
		return "success";
	}

}
