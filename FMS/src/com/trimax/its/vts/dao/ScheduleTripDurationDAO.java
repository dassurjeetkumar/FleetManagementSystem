package com.trimax.its.vts.dao;

import java.text.SimpleDateFormat; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.validator.Length;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class ScheduleTripDurationDAO {


	public static String rbKey = String.valueOf(getSysParameterForVts());

	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getDeviceIMEI(
			String FromDate, String TillDate) {
		List<com.trimax.ws.vts.vtsutility.VtsDataModel> imeiList = null;
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse result = port
					.getIMEIList(rbKey);
			for (int i = 0; i < result.getVtsDatamodel().size(); i++) {
				imeiList = result.getVtsDatamodel();
			}
		} catch (Exception ex) {

		} finally {
			// session.close();
		}
		return imeiList;
	}

	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return param;
	}

//	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public List<WaybillDetails> getScheduleNameTripDuraion(int parentID) {
//		List<WaybillDetails> list = new ArrayList();
//		// Session session = null;
//		try {
//			// session = new HibernateUtilVtu().getSession();
//
//			// Query query = session
//			// .createSQLQuery("select SCHEDULE_NO,concat(SCHEDULE_NAME,'(',VEHICLE_NO,')') as SCHEDULE_NAME  from waybill_trip_details where STATUS ='ONLINE' group by SCHEDULE_NO order by SCHEDULE_NO");
//			// query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//			// List<Map<String, Object>> aliasToValueMapList = query.list();
//			// if (aliasToValueMapList.size() > 0) {
//			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
//			model.jaxb.xml.trimax.com.VtsResponse schedulename = port
//					.webGetScheduleNameDuration(String.valueOf(parentID), rbKey);
//			for (int i = 0; i < schedulename.getVtsDatamodel().size(); i++) {
//				WaybillDetails details=new WaybillDetails();
//				System.out.println("schedulename.getVtsDatamodel().get(i).getSCHEDULENO()==="+schedulename.getVtsDatamodel().get(i).getSCHEDULENO());
//				details.setSchedule_no(Integer.parseInt(schedulename.getVtsDatamodel().get(i).getSCHEDULENO()));
//				details.setSchedule_name(schedulename.getVtsDatamodel().get(i).getSCHEDULENAME());
//				list.add(details);
////				// Map<String, Object> rs = aliasToValueMapList.get(i);
////				list.add(schedulename.getVtsDatamodel().get(i)
////						.getSCHEDULENAME() != null ? schedulename
////						.getVtsDatamodel().get(i).getSCHEDULENAME() : "");
//			}
//
//			// }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//
//			//HibernateUtilVtu.closeSessionFactory();
//
//		}
//		return list;
//	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForshowScheduletripdurationReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedstartDate,String selectedendDate, String scheduleno,String depotName) {

		JSONObject result = new JSONObject();
		Common common = new Common();
		//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
		//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
		//System.out.println("final date is--"+selectedstartdate);
		try {
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			VtsResponse6 alertresult = port.webGetScheduleTripDurationReport(
					selectedstartDate,selectedendDate, scheduleno,depotName, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				 JSONArray ja = new JSONArray();
				 ja.add(i + 1);
				 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
				 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
				 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
				 ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
				 //
				 ja.add(alertresult.getWaybillDetails().get(i).getARRIVALDIFF());
				
				 array.add(ja);
				
				 }
				 result.put("aaData", array);
				 } catch (Exception ex) {
				 ex.printStackTrace();
				 }
				 return result;
				 }
			
	@SuppressWarnings("rawtypes")
	public List getScheduleNameDurationID(int parentID) {
		List list = new ArrayList();
		Session session = HibernateUtil.getSession("");
		Query query = session
				.createSQLQuery("SELECT form_four_id FROM `form_four` ff inner join " +
						"     schedule sd on ff.schedule_number_id=sd.schedule_id WHERE ff.`current_status` = 'ACTIVE' " +
						" AND ff.`deleted_status` = '0' and depot_id="+parentID+" and sd.status='NEW'  and " +
						" sd.current_status='CASE WORKER' and sd.deleted_status=0");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("form_four_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	@SuppressWarnings("rawtypes")
	public List getScheduleNameDurationName(int parentID) {
		List list = new ArrayList();
		Session session = HibernateUtil.getSession("");
		Query query = session
				.createSQLQuery("SELECT schedule_number_name FROM `form_four` ff inner join " +
						"     schedule sd on ff.schedule_number_id=sd.schedule_id WHERE ff.`current_status` = 'ACTIVE' " +
						" AND ff.`deleted_status` = '0' and depot_id="+parentID+" and sd.status='NEW'  and " +
						" sd.current_status='CASE WORKER' and sd.deleted_status=0 ");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("schedule_number_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForScheduleTripStatusReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno,String depotName) {

		JSONObject result = new JSONObject();
		
		Common common = new Common();
		String selecteddate = common.getDateFromDateTime_(selectedDate);
		
		try {
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			//String vehicleno = req.getParameter("vehicleno").toString();
			//for(int x=0;x<1;x++){
			//	String ar[]= new String []{selectedDate,vehicleno,scheduleno};
			//	ScheduleTripUpdate.main(ar);
			//	}
			VtsResponse6 alertresult = port.webGetScheduleTripSTatusReport(
					selectedDate, scheduleno, rbKey);
			JSONArray array = new JSONArray();
			String Start_time = "";
			int start_duty_type, end_duty_type = 0;
			String End_time = "";
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();
				String shift = "";
				int etmshift=0;
				
				switch (alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDUTYTYPEID()) {
				case 1:
					shift = "General Shift";
					etmshift=1;
					break;
				case 2:
					shift = "Day 1";
					etmshift=1;
					break;
				case 3:
					shift = "Day 2";
					etmshift=2;
					break;
				case 4:
					shift = "Shift 1";
					etmshift=1;
					break;
				case 5:
					shift = "Shift 2";
					etmshift=2;
					break;
				case 6:
					shift = "Day 1 old";
					break;
				case 7:
					shift = "Day 2 old";
					break;
				case 78:
					shift = "Night Service";
					break;
				case 79:
					shift = "Shift 1";
					etmshift=1;
					break;
				case 80:
					shift = "Shift 2";
					etmshift=2;
					break;
				}
				String depstatus = "";
				String arrivalstatus = "";
				long departurediff;
				long arrivaldiff;

				// Check Dept and Arrival Time
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME().equals("")
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME() == null
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("00:00:00")) {
//					String deptTime = port.getTripwiseDistanceLogic(
//							String.valueOf(alertresult.getWaybillDetails()
//									.get(i).getId()), "startTime", rbKey);
					alertresult.getWaybillDetails().get(i)
							.setACTUALDEPARTURETIME(alertresult.getWaybillDetails().get(i)
									.getTRIPDISTANCE());

				}
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALARRIVALTIMEDISP().equals("")
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP() == null
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP().equals("00:00:00")) {
//					String arrTime = port.getTripwiseDistanceLogic(
//							String.valueOf(alertresult.getWaybillDetails()
//									.get(i).getId()), "endTime", rbKey);
					alertresult.getWaybillDetails().get(i)
							.setACTUALARRIVALTIMEDISP(alertresult.getWaybillDetails().get(i)
									.getTRIPDISTANCE());
				}
				
				ja.add(i+1);
				if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()!=1){
				ja.add("<a  class='btn blue' role='button' href='#mymodal123'  onclick=javascript:viewTripWithonlineTicket('"
						+ selecteddate+"','"+
						alertresult.getWaybillDetails().get(i).getWAYBILLNO()+"','"+
						alertresult.getWaybillDetails().get(i).getDepotCd().replace(" ", "")+"','"+
						alertresult.getWaybillDetails().get(i).getDepotId()+"','"+
						alertresult.getWaybillDetails().get(i).getDEVICEID()+"','"+
						alertresult.getWaybillDetails().get(i).getVEHICLENO()+"','"+
						alertresult.getWaybillDetails().get(i).getSCHEDULEDUTYTYPEID()

						+ "','"+
						alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPTRIPNUMBER()

						+ "') title='Alert Details' id='alert_details"
						
						+ "'>"
						+  alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPTRIPNUMBER() + "</a>");
				}else{
					ja.add(alertresult.getWaybillDetails().get(i)
							.getSCHEDULETRIPTRIPNUMBER());
				}
				//ja.add(shift);
				String appendedact_dtime="";
				String appendedact_atime="";
				ja.add(shift);
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPENDBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Not Operated") || alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Operated(Not tracked)")) {
					ja.add("00:00:00");
				}else{
				if(alertresult
						.getWaybillDetails().get(i).getACTSTARTTIME().equals("-") ){
					if( alertresult
							.getWaybillDetails().get(i).getACTSTARTTIME().equals("0") || alertresult
							.getWaybillDetails().get(i).getACTSTARTTIME().equals("") || alertresult
							.getWaybillDetails().get(i).getACTUALDEPARTURETIME().equals("0")){
						appendedact_dtime="-";
						ja.add("-");
					}else{
						appendedact_dtime=alertresult
								.getWaybillDetails().get(i).getACTUALDEPARTURETIME();
				ja.add(alertresult
						.getWaybillDetails().get(i).getACTUALDEPARTURETIME()+"("+alertresult
						.getWaybillDetails().get(i).getActualStartPoint()+")");
					}
				}else{
					appendedact_dtime=alertresult
							.getWaybillDetails().get(i).getACTUALDEPARTURETIME();
					ja.add(alertresult
							.getWaybillDetails().get(i).getACTUALDEPARTURETIME());
				}
				}
//				if(alertresult
//						.getWaybillDetails().get(i).getACTSTARTTIME().equals("-") ){
//				ja.add(alertresult.getWaybillDetails().get(i).getACTUALDEPARTURETIME());
//				}else{
//					appendedact_dtime=alertresult
//							.getWaybillDetails().get(i).getACTUALDEPARTURETIME();
//					ja.add(alertresult
//							.getWaybillDetails().get(i).getACTUALDEPARTURETIME());
//				}
				
				//For Dept and Arrival Status....Surjeet
				if (alertresult.getWaybillDetails().get(i).getDEPTUREDIFF() != null) {
					departurediff = getTimeDiffInMinutes(alertresult.getWaybillDetails().get(i).getETMSTARTTIME(),alertresult.getWaybillDetails().get(i)
							.getDEPARTURETIME(),appendedact_dtime);
					System.out.println("Difference is "+departurediff);

					if (departurediff >= 15) {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME().equals("")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
										.equals("0.00") || alertresult
										.getWaybillDetails().get(i).getACTSTARTTIME()
										.equals("-")) {
							depstatus = "-";
						} else {
							depstatus = "Late";
						}
					} else if (departurediff <= -15) {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME().equals("")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
										.equals("0.00") || alertresult
										.getWaybillDetails().get(i).getACTSTARTTIME()
										.equals("-")) {
							depstatus = "-";
						} else {
							depstatus = "Early";
						}
					} else {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME().equals("")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
										.equals("0.00") || alertresult
										.getWaybillDetails().get(i).getACTSTARTTIME()
										.equals("-")) {
							depstatus = "-";
						}else{
						depstatus = "Ontime";
						}
					}
				} else {
					if (!alertresult.getWaybillDetails().get(i)
							.getACTUALDEPARTURETIME().equals("0.00")) {
						// if nullget actual diff
						double timeDiff = getTimeDiffInMin(alertresult
								.getWaybillDetails().get(i).getDUTYDT(),
								alertresult.getWaybillDetails().get(i)
										.getDEPARTURETIME(), alertresult
										.getWaybillDetails().get(i).getACTSTARTTIME());
						
						if (timeDiff >= 15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALDEPARTURETIME()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("0.00") || alertresult
											.getWaybillDetails().get(i).getACTSTARTTIME()
											.equals("-")) {
								depstatus = "-";
							} else {
								depstatus = "Late";
							}
						} else if (timeDiff <= -15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALDEPARTURETIME()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("0.00") || alertresult
											.getWaybillDetails().get(i).getACTSTARTTIME()
											.equals("-")) {
								depstatus = "-";
							} else {
								depstatus = "Early";
							}
						} else {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALDEPARTURETIME()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("0.00") || alertresult
											.getWaybillDetails().get(i).getACTSTARTTIME()
											.equals("-")) {
								depstatus = "-";
							} else {
								if (alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
										.equals("00:00:00")
										|| alertresult.getWaybillDetails().get(i)
												.getACTUALDEPARTURETIME()
												.equals("")
										|| alertresult.getWaybillDetails().get(i)
												.getACTUALDEPARTURETIME()
												.equals("0.00") || alertresult
												.getWaybillDetails().get(i).getACTSTARTTIME()
												.equals("-")) {
									depstatus = "-";
								}
									else{
								depstatus = "Ontime";
									}
							}
							
						}
					}
				}
				
				if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Not Operated")){
					ja.add("-");
				}else{
			
					ja.add(depstatus);
				}
				
				ja.add(alertresult.getWaybillDetails().get(i).getARRIVALTIME());
				if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Not Operated")) {
					ja.add("00:00:00");
				}else {
					
				if(alertresult
						.getWaybillDetails().get(i).getENDTIME().equals("-")){
					if( alertresult
							.getWaybillDetails().get(i).getACTUALARRIVALTIME().equals("0") || alertresult
							.getWaybillDetails().get(i).getACTUALARRIVALTIME().equals("") || alertresult
							.getWaybillDetails().get(i).getENDTIME().equals("0")){
						appendedact_atime="-";
						ja.add("-");
					}else{
						appendedact_atime=alertresult
								.getWaybillDetails().get(i).getACTUALARRIVALTIMEDISP();
				ja.add(alertresult
						.getWaybillDetails().get(i).getACTUALARRIVALTIMEDISP()+"("+alertresult
						.getWaybillDetails().get(i).getActualEndPoint()+")");
					}
				}else{
					appendedact_atime=alertresult
							.getWaybillDetails().get(i).getACTUALARRIVALTIMEDISP();
					ja.add(alertresult
							.getWaybillDetails().get(i).getACTUALARRIVALTIMEDISP());
				}
				}
				
				if (alertresult.getWaybillDetails().get(i).getARRIVALDIFF() != null) {
					arrivaldiff = getTimeDiffInMinutes(alertresult.getWaybillDetails().get(i).getETMENDTIME(),alertresult.getWaybillDetails().get(i).getARRIVALTIME(),appendedact_atime);
					if (arrivaldiff >= 15) {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP().equals("") || alertresult
										.getWaybillDetails().get(i).getENDTIME().equals("-")) {
							arrivalstatus = "-";
						} else {
							arrivalstatus = "Late";
						}
					} else if (arrivaldiff <= -15) {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP().equals("")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00") || alertresult
										.getWaybillDetails().get(i).getENDTIME().equals("-")) {
							arrivalstatus = "-";
						} else {
							arrivalstatus = "Early";
						}
					} else {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP()
								.equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00") || alertresult
										.getWaybillDetails().get(i).getENDTIME().equals("-")) {
							arrivalstatus = "-";
						} else {
						arrivalstatus = "Ontime";
						}
					}
				} else {
					if (!alertresult.getWaybillDetails().get(i)
							.getACTUALARRIVALTIMEDISP().equals("0.00")) {
						// if nullget actual diff
						double timeDiff = getTimeDiffInMin(alertresult
								.getWaybillDetails().get(i).getDUTYDT(),
								alertresult.getWaybillDetails().get(i)
										.getARRIVALTIME(), alertresult
										.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP() );
						
						if (timeDiff >= 15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP()
											.equals("") || alertresult
											.getWaybillDetails().get(i).getENDTIME().equals("-")) {
								arrivalstatus = "-";
							} else {
								arrivalstatus = "Late";
							}
						} else if (timeDiff <= -15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP()
											.equals("")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP()
											.equals("0.00") || alertresult
											.getWaybillDetails().get(i).getENDTIME().equals("-")) {
								arrivalstatus = "-";
							} else {
								arrivalstatus = "Early";
							}
						} else {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP()
											.equals("")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP()
											.equals("0.00") || alertresult
											.getWaybillDetails().get(i).getENDTIME().equals("-")) {
								arrivalstatus = "-";
							} else {
							arrivalstatus = "Ontime";
							}
						}

					}
				}
				
				
				//End
				String tripstatus=alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS();
				
				
				
				
				if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Not Operated") || alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Operated(Not tracked)")){
					ja.add("-");
				}else{
			
				ja.add(arrivalstatus);
				}
				System.out.println("appendedact_dtime====="+appendedact_dtime+"\t"+"appendedact_atime"+appendedact_atime);
				
				
				ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
				System.out.println("keval0000"+alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS());
				if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Not Operated") || alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Operated(Not tracked)")){
					ja.add("00:00:00");
					System.out.println("keval"+alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS());
				}else{
					System.out.println("keval=="+alertresult.getWaybillDetails().get(i).getACTUALDEPARTURETIME()+"===="+alertresult.getWaybillDetails().get(i).getACTUALARRIVALTIMEDISP());
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME().equals("00:00:00")
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP().equals("00:00:00")) {
					ja.add("N/A");
				} else {
					// Logic to calculate
					if (alertresult.getWaybillDetails().get(i)
							.getACTUALDEPARTURETIME().equals("00:00:00")
							|| alertresult.getWaybillDetails().get(i)
									.getACTUALDEPARTURETIME().equals("")
							|| alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP()
									.equals("00:00:00")
							|| alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP().equals("-") ) {
						ja.add("-");
					} else {
						System.out.println("keval333====="+alertresult.getWaybillDetails().get(i).getETMSTARTTIME());
						System.out.println(alertresult.getWaybillDetails().get(i).getETMSTARTTIME()+"===="+appendedact_dtime+"======"+alertresult.getWaybillDetails().get(i).getETMENDTIME()+"====="+appendedact_atime+"time difference"+getTimeDiff(alertresult.getWaybillDetails().get(i).getETMSTARTTIME(), appendedact_dtime,alertresult.getWaybillDetails().get(i).getETMENDTIME(), appendedact_atime));
						ja.add(getTimeDiff(
								alertresult.getWaybillDetails().get(i)
										.getETMSTARTTIME(), appendedact_dtime,alertresult.getWaybillDetails().get(i)
										.getETMENDTIME(), appendedact_atime));
					}
				}
				}
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPDISTANCE());
				//String tripstatus = alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS();
				
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null
						&& alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP() != null) {
					if (alertresult.getWaybillDetails().get(i)
							.getACTUALDEPARTURETIME().equals("00:00:00")
							|| alertresult.getWaybillDetails().get(i)

							.getACTUALARRIVALTIMEDISP().equals("00:00:00")) {
						//tripstatus = "Deviated";
					} else {
						

						 //tripstatus=port.checkTripStatus(String.valueOf(alertresult.getWaybillDetails().get(i).getId()),
						// rbKey);
						
						if (!(alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("0.00"))
								&& !(alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00"))) {
							//tripstatus = "Completed";
						} else if (alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("0.00")
								&& alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00")) {
							//tripstatus = "Not Operated/Deviated";
						} else {
							//tripstatus = "Partial";
						}
					}
				} else {
					//tripstatus = "N/A";
				}
				//port.checkTripStatus(String.valueOf(alertresult.getWaybillDetails().get(i).getId()),	 rbKey);
				//System.out.println("TRIP STATUS"+tripstatus);
				String gpsDistance = alertresult.getWaybillDetails().get(i)
						.getTRIPDISTANCE();
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null
						&& alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP() != null) {
					if (alertresult.getWaybillDetails().get(i)
							.getACTUALDEPARTURETIME().equals("00:00:00")
							|| alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP()
									.equals("00:00:00")) {
						double Distance = Double.parseDouble(alertresult.getWaybillDetails().get(i).getTRIPDISTANCE());//getActualDistanceLogic(alertresult.getWaybillDetails().get(i).getId(), "start");
						if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()==1){
							Distance = Double.parseDouble(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPDISTANCE());
						}
						else if (tripstatus.equals("Not Operated/Deviated")) {
							//Distance = 0.0;
						} 
						System.out.println("Distance is"+Distance);
						ja.add(Distance);
					}

					else {
						if (tripstatus.equals("Partial")) {
							if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()==1){
								ja.add(Double.parseDouble(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPDISTANCE()));
							}else{
							ja.add(alertresult.getWaybillDetails().get(i).getTRIPDISTANCE());
							}
						} else if (Double.parseDouble(gpsDistance) <= 0) {
							if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()==1){
								ja.add(Double.parseDouble(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPDISTANCE()));
							}else{
							
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALDEPARTURETIME() != null
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP() != null) {
								ja.add(alertresult.getWaybillDetails().get(i).getTRIPDISTANCE());
							} else {
								ja.add("0.0");
							}
							}
						} else {
							double Distance = Double.parseDouble(alertresult.getWaybillDetails().get(i).getTRIPDISTANCE());
							if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()==1){
								Distance = Double.parseDouble(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPDISTANCE());
							}
							else if (tripstatus.equals("Not Operated/Deviated")) {
								//Distance = 0.0;
							}
							ja.add(Distance);
						}
					}
				} else {
					if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()==1){
						gpsDistance = alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPDISTANCE();
					}
				else if (tripstatus.equals("Not Operated/Deviated")) {
						//gpsDistance = "0.0";
					}
					ja.add(gpsDistance);
				}
				ja.add(alertresult.getWaybillDetails().get(i).getDeviatedTrip());
				ja.add(alertresult.getWaybillDetails().get(i).getCompletedTrip());
				
				ja.add("<a data-toggle='modal' role='button' href='#mymodal123'  onclick=javascript:viewTripWithBusstopname('"
						+ alertresult.getWaybillDetails().get(i).getId()
						+ "') title='Track Online' id='alert_details"
						+ "'>"
						+ tripstatus + "</a>");
				if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS().equalsIgnoreCase("Not Operated")) {
					if(alertresult.getWaybillDetails().get(i).getOfflineDepot().equalsIgnoreCase("ETIMCANCEL")) {
						ja.add("ETIM");
					}else {
						ja.add("GPS");
					}
				}else {
					ja.add("");
				}
				ja.add(alertresult.getWaybillDetails().get(i).getOnlineDepot());
				
				String starttime = "";
				String endtime = "";
				/*endtime = (alertresult.getWaybillDetails().get(i)
						.getACTUALARRIVALTIMEDISP().equals("")
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP() == null || alertresult
						.getWaybillDetails().get(i).getACTUALARRIVALTIME()
						.equals("00:00:00")) ? getActualEndTime(alertresult
						.getWaybillDetails().get(i).getId()) : alertresult
						.getWaybillDetails().get(i).getACTUALARRIVALTIMEDISP();
				starttime = (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME().equals("")
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME() == null || alertresult
						.getWaybillDetails().get(i).getACTUALDEPARTURETIME()
						.equals("00:00:00")) ? getActualStartTime(alertresult
						.getWaybillDetails().get(i).getId()) : alertresult
						.getWaybillDetails().get(i).getACTUALDEPARTURETIME();*/

				// Calculation of Actual Distance
				if (i == 0) {
					Start_time = alertresult.getWaybillDetails().get(i)
							.getDUTYDT()
							+ " " + starttime;
					start_duty_type = alertresult.getWaybillDetails().get(i)
							.getSCHEDULEDUTYTYPEID();
				}
				if (i == alertresult.getWaybillDetails().size() - 1) {
					End_time = alertresult.getWaybillDetails().get(i)
							.getDUTYDT()
							+ " " + endtime;
					end_duty_type = alertresult.getWaybillDetails().get(i)
							.getSCHEDULEDUTYTYPEID();
				}
				// Calling a Webservice that will get Actual Distance in between
				// Schedule Time

				//
System.out.println("alertresult.getWaybillDetails().get(i).getETMSTARTTIME()"+alertresult.getWaybillDetails().get(i).getETMSTARTTIME());
				ja.add("<a href='#' class='mapClass' title='View on Map' onclick=javascript:drawScheduleRouteDeviation('"
						+ alertresult.getWaybillDetails().get(i).getDEVICEID()
						+ "',"
						+ alertresult.getWaybillDetails().get(i).getROUTEID()
						+ ","
						+ alertresult.getWaybillDetails().get(i)
								.getSTARTPOINT()
						+ ","
						+ alertresult.getWaybillDetails().get(i).getENDPOINT()
						+ ",'"
						+ appendedact_dtime
						+ "','"
						+ tripstatus
						
						+ "','"
						+ alertresult.getWaybillDetails().get(i).getETMSTARTTIME()
						+ "',"
						+ alertresult.getWaybillDetails().get(i).getId()
						+ ",'"
						+ appendedact_atime
						+ "'"
						+ ",'"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULENAME().replace(" ", "")
						+ "'"
						+ ")>"
						+ "<img src='assets/images/bus-map-icon.png'/>"
						+ "</a>");
				ja.add("<a href='#' class='mapClass' title='View Playback on Map' onclick=javascript:openWindowfor_playback('"
						+ alertresult.getWaybillDetails().get(i).getDEVICEID()
						+ "','"
						+ alertresult.getWaybillDetails().get(i).getETMSTARTTIME()
						+ "',"
						+ alertresult.getWaybillDetails().get(i).getROUTEID()
						+ ","
						+ alertresult.getWaybillDetails().get(i)
								.getSTARTPOINT()
						+ ","
						+ alertresult.getWaybillDetails().get(i).getENDPOINT()
						+ ",'"
						+ appendedact_dtime
						+ "','"
						+ appendedact_atime
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULENAME().replace(" ", "")
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULETRIPTRIPNUMBER()
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULETRIPSTARTBUSSTOPNAME()
								.replace(" ", "")
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULETRIPENDBUSSTOPNAME()
								.replace(" ", "")
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getDEPARTURETIME()
						+ "','"
						+ appendedact_dtime
						+ "','"
						+ appendedact_atime
						+ "','"
						+ appendedact_atime
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULEDTRIPDISTANCE()
						+ "'"
						+ ")>"
						+ "Playback" + "</a>");

				array.add(ja);
			}
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}
	
	// get waybill wise total amount end
		public double getTimeDiffInMin(String dutyDate, String time1, String time2) {
			String timeDiff = "";
			long diffMinutes = 0;
			try {
//				System.out.println("dutyDate" + dutyDate + "\t" + time1 + "\t"
//						+ time2);
				String time3 = dutyDate + " " + time1;
				String time4 = dutyDate + " " + time2;

				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date1 = format.parse(time3);
				Date date2 = format.parse(time4);
				long difference = date2.getTime() - date1.getTime();
				diffMinutes = difference / (60 * 1000) % 60;
				// System.out.println("HMS"+hms);
			} catch (Exception ex) {

			} finally {

			}
			return diffMinutes;
		}

	public String getActualStartTime(int ID) {
		String str = "";
		// Calling WebServices
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		str = port.getTripwiseStartTime(String.valueOf(ID), rbKey);

		return str;

	}

	public String getActualEndTime(int ID) {
		String str = "";
		// Calling WebServices
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		str = port.getTripwiseEndTime(String.valueOf(ID), rbKey);
		return str;

	}

	public double getActualDistanceLogic(int ID, String status) {
		String str = "";
		// Calling WebServices
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		str = port.getTripwiseDistanceLogic(String.valueOf(ID), status, rbKey);
		return Double.parseDouble(str);
	}

	// get waybill wise total amount end
		public static String getTimeDiff(String dutyDate, String time1,String duty_dt2, String time2) {
			String timeDiff = "";
			try {
				System.out.println(dutyDate+"====="+time1+"====="+duty_dt2+"===="+time2);
				String time3 = dutyDate.substring(0, 10) + " " + time1;
				String time4 = duty_dt2.substring(0, 10) + " " + time2;
				String time5 = dutyDate.substring(0, 10) + " " + time2 ;
				System.out.println("time3==="+time3+"time5===="+time5);
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date1 = format.parse(time3);
				Date date2 = format.parse(time4);
				Date date3 = format.parse(time5); 
				System.out.println("dutyDate" + date1 +  "\t"
						+ date3);
				long difference = date3.getTime() - date1.getTime();
				if(difference <0){
					difference = difference+86400;
				}
//				long diff1 = date1.getTime() - date3.getTime();
//				long diffHours = diff1 / (60 * 60 * 1000);  
				timeDiff = String.format(
						"%02d:%02d:%02d",
						TimeUnit.MILLISECONDS.toHours(difference),
						TimeUnit.MILLISECONDS.toMinutes(difference)
								- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
										.toHours(difference)),
						TimeUnit.MILLISECONDS.toSeconds(difference)
								- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
										.toMinutes(difference)));
				

			} catch (Exception ex) {

			} finally {

			}
			return timeDiff;
		}	
		
		// get waybill wise total amount end
				public static long getTimeDiffInMinutes(String dutyDate, String time1, String time2) {
					long diffMinutes = 0L;
					try {
//						System.out.println("dutyDate" + dutyDate + "\t" + time1 + "\t"
//								+ time2);
						String time3 = dutyDate + " " + time1;
						String time4 = dutyDate + " " + time2;

						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date date1 = format.parse(time3);
						Date date2 = format.parse(time4);
						System.out.println("time1="+date1+"=="+date2);
						long difference = date2.getTime() - date1.getTime();
						System.out.println("differ=="+difference);
						 diffMinutes = difference / (60 * 1000) ;
//						 System.out.println("HMS"+diffMinutes);
					} catch (Exception ex) {

					} finally {

					}
					System.out.println("diffMinutes"+diffMinutes);
					return diffMinutes;
				}	
		
		
	
	
		@SuppressWarnings("unchecked")
		public JSONObject getDataForScheduleNotDepartedReport(int j,
				HttpServletRequest req, String string, String string2,
				String selectedstartDate,String selectedendDate,String depotName) {

			JSONObject result = new JSONObject();
			Common common = new Common();
			//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
			//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
			//System.out.println("final date is--"+selectedstartdate);
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				VtsResponse6 alertresult = port.webGetScheduleNotDepartedReport(selectedstartDate,
						selectedendDate,depotName, rbKey);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					 JSONArray ja = new JSONArray();
					 ja.add(i + 1);
					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
					 ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
					 ja.add(alertresult.getWaybillDetails().get(i).getSERVICETYPE());
					 
					
					 array.add(ja);
					
					 }
					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return result;
					 }
		@SuppressWarnings("unchecked")
		public VtsResponse6 getDataTripWiseEarlyArrivalReport(String selectedstartDate,String scheduleno) {
			VtsResponse6 alertresult= new VtsResponse6() ;
			JSONObject result = new JSONObject();
			Common common = new Common();
			//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
			//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
			//System.out.println("final date is--"+selectedstartdate);
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				alertresult = port.webGetTripWiseEarlyArrivalReport(selectedstartDate,scheduleno, rbKey);
//				JSONArray array = new JSONArray();
//				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//					 JSONArray ja = new JSONArray();
//					 ja.add(i + 1);
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
//					 //
//					 ja.add(alertresult.getWaybillDetails().get(i).getARRIVALDIFF());
//					
//					 array.add(ja);
//					
//					 }
//					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return alertresult;
					 }
		
		@SuppressWarnings("unchecked")
		public VtsResponse6 getDataTripWiseLateArrivalReport(String selectedstartDate,String scheduleno) {
			VtsResponse6 alertresult= new VtsResponse6() ;
			JSONObject result = new JSONObject();
			Common common = new Common();
			//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
			//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
			//System.out.println("final date is--"+selectedstartdate);
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				alertresult = port.webGetTripWiseLateArrivalReport(selectedstartDate,scheduleno, rbKey);
				System.out.println("alertresult==="+alertresult.getWaybillDetails().size());
//				JSONArray array = new JSONArray();
//				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//					 JSONArray ja = new JSONArray();
//					 ja.add(i + 1);
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
//					 //
//					 ja.add(alertresult.getWaybillDetails().get(i).getARRIVALDIFF());
//					
//					 array.add(ja);
//					
//					 }
//					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return alertresult;
					 }
		
		@SuppressWarnings("unchecked")
		public VtsResponse6 getDataTripWiseLateDepartureReport(String selectedstartDate,String scheduleno) {
			VtsResponse6 alertresult= new VtsResponse6() ;
			JSONObject result = new JSONObject();
			Common common = new Common();
			//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
			//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
			//System.out.println("final date is--"+selectedstartdate);
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				alertresult = port.webGetTripWiseLateDepartureReport(selectedstartDate,scheduleno, rbKey);
//				JSONArray array = new JSONArray();
//				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//					 JSONArray ja = new JSONArray();
//					 ja.add(i + 1);
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
//					 //
//					 ja.add(alertresult.getWaybillDetails().get(i).getARRIVALDIFF());
//					
//					 array.add(ja);
//					
//					 }
//					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return alertresult;
					 }
		
		@SuppressWarnings("unchecked")
		public VtsResponse6 getDataTripWiseEarlyDepartureReport(String selectedstartDate,String scheduleno) {
			VtsResponse6 alertresult= new VtsResponse6() ;
			JSONObject result = new JSONObject();
			Common common = new Common();
			//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
			//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
			//System.out.println("final date is--"+selectedstartdate);
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				alertresult = port.webGetTripWiseEarlyDepartureReport(selectedstartDate,scheduleno, rbKey);
//				JSONArray array = new JSONArray();
//				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//					 JSONArray ja = new JSONArray();
//					 ja.add(i + 1);
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
//					 //
//					 ja.add(alertresult.getWaybillDetails().get(i).getARRIVALDIFF());
//					
//					 array.add(ja);
//					
//					 }
//					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return alertresult;
					 }
		
		@SuppressWarnings("rawtypes")
		public List getEtmDeviceID(int parentID) {
			List list = new ArrayList();
			Session session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select dev.device_id,dev.device_serial_number from device dev inner join device_org dorg on dorg.device_id=dev.device_id  " +
							" where dev.device_serial_number not in (select etm_number from etm_device_history  where (etm_replace_date IS NULL or etm_replace_date >=now())) " +
							" and device_type_id=2 and dev.status='ACTIVE' and dorg.status='ACTIVE'  and dev.deleted_status=0 and dorg.org_id='"+parentID+"'");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("device_id").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
		@SuppressWarnings("rawtypes")
		public List getEtmDeviceNumber(int parentID) {
			List list = new ArrayList();
			Session session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select dev.device_id,dev.device_serial_number from device dev inner join device_org dorg on dorg.device_id=dev.device_id  " +
							" where dev.device_serial_number not in (select etm_number from etm_device_history  " +
							" where (etm_replace_date IS NULL or etm_replace_date >=now())) " +
							" and device_type_id=2 and dev.status='ACTIVE' and dorg.status='ACTIVE'  and dev.deleted_status=0 and dorg.org_id='"+parentID+"'");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("device_serial_number").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
		
		public String getPriviousSubmitDate(String etmno){
//			List list = new ArrayList();
			String etmDate="";
			Session session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select MAX(etm_replace_date) as etm_replace_date from etm_device_history where etm_number='"+etmno+"' ");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					if(rs.get("etm_replace_date")==null){
						
					}else{
						etmDate=rs.get("etm_replace_date").toString();
					}
					 
				}
			}
			HibernateUtil.closeSession();
			return etmDate;
			
			
		}
		
		@SuppressWarnings("unchecked")
		public VtsResponse6 getDataVehicleDeviceOnlineReport(String selectedstartDate) {
			VtsResponse6 alertresult= new VtsResponse6() ;
			JSONObject result = new JSONObject();
			Common common = new Common();
			//String selectedstartdate = common.getDateFromDateTime_(selectedstartDate);
			//String selectedenddate = common.getDateFromDateTime_(selectedendDate);
			//System.out.println("final date is--"+selectedstartdate);
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
				// TODO initialize WS operation arguments here
				// Query To Get Schedule Header Information..
				alertresult = port.webGetVehicleDeviceOnlineReport(selectedstartDate, rbKey);
//				JSONArray array = new JSONArray();
//				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
//					 JSONArray ja = new JSONArray();
//					 ja.add(i + 1);
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
//					 ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
//					 ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
//					 //
//					 ja.add(alertresult.getWaybillDetails().get(i).getARRIVALDIFF());
//					
//					 array.add(ja);
//					
//					 }
//					 result.put("aaData", array);
					 } catch (Exception ex) {
					 ex.printStackTrace();
					 }
					 return alertresult;
					 }
		
		
}