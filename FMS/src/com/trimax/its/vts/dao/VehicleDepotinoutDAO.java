package com.trimax.its.vts.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.jaxb.xml.trimax.com.VtsResponse;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.sql.Timestamp;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class VehicleDepotinoutDAO {
	String rbKey = String.valueOf(getSysParameterForVts());

	public String getDateTimeFromPickerBrandVIEW(String pickerDate) {

		DateFormat readFormat = new SimpleDateFormat("MMM dd, yyyy");

		DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = readFormat.parse(pickerDate.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String formattedDate = "";
		if (date != null) {
			formattedDate = writeFormat.format(date);
		}
		return formattedDate;
		// System.out.println(formattedDate);
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

	@SuppressWarnings("unchecked")
	public JSONObject showVehicleDepotinoutData(int total,
			HttpServletRequest request, String col, String dir,
			String daterange, String parameter, String depot_id) {
		String spliteddates[] = daterange.split("-");
		JSONObject result = new JSONObject();
		SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sm2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int earlyCount = 0, lateCount = 0, ontimeCount = 0;
		try {
			int totalAfterFilter = total;
			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			Date date = new Date();
			Date date2 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			// String stringDate = sdf.format(date);
			String stringDate = getDateTimeFromPickerBrandVIEW(spliteddates[0]);
			String formattedstartdate = getDateTimeFromPickerBrandVIEW(spliteddates[0])
					+ " 00:00:00";
			String formattedenddate = getDateTimeFromPickerBrandVIEW(spliteddates[1])
					+ " 23:59:59";
			// Session alertconfigsession = new HibernateUtilVtu().getSession();
			// get Start Point Id
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			int startPoint = 0;
			System.out.println("depot_id"+depot_id);
			//startPoint  = 0;
			
			System.out.println("startPoint"+startPoint);
			if (parameter.equals("Arrival")) {
				//if (startPoint != 0) {
					
					VtsResponse depotArrival = null;
					depotArrival = port.getDepotInoutReport(formattedstartdate,
							formattedenddate, stringDate, parameter.trim(),
							startPoint, rbKey,Integer.parseInt(depot_id));

					JSONArray arraytickstock = new JSONArray();
					String shift="";
					for (int i = 0; i < depotArrival.getVtsDatamodel().size(); i++) {
						// Map<String, Object> rs = aliasToValueMapList2.get(i);
						JSONArray jatickstock = new JSONArray();
						jatickstock.add(i + 1);
						jatickstock.add(depotArrival.getVtsDatamodel().get(i)
								.getVEHICLENO());
						jatickstock.add(depotArrival.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						switch (depotArrival
								.getVtsDatamodel().get(i).getDutyTypeId()) {
						case 1:
							shift = "General Shift";
							break;
						case 2:
							shift = "Day 1";
							break;
						case 3:
							shift = "Day 2";
							break;
						case 4:
							shift = "Shift 1";
							break;
						case 5:
							shift = "Shift 2";
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
							break;
						case 80:
							shift = "Shift 2";
							break;
						case 82:
							shift = "Split Service";
							break;
						}
						jatickstock.add(shift);
						jatickstock.add(depotArrival.getVtsDatamodel().get(i)
								.getENDTIME());

						jatickstock.add(depotArrival.getVtsDatamodel().get(i)
								.getACTTIMEARR());
						String entryTM = depotArrival.getVtsDatamodel().get(i)
								.getDEPOTENTRYTM() != null ? depotArrival
								.getVtsDatamodel().get(i).getDEPOTENTRYTM()
								: "--";
						String exitTM = depotArrival.getVtsDatamodel().get(i)
								.getDEPOTEXITTM() != null ? depotArrival
								.getVtsDatamodel().get(i).getDEPOTEXITTM()
								: "--";
						String ar[] = entryTM.split(" ");
						if (!entryTM.equals("--")) {

							// jatickstock.add(entryTM);
//							jatickstock
//									.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleDepotArrDetailsData('"
//											+ depotArrival.getVtsDatamodel()
//													.get(i).getDEVICEID()
//											+ "','"
//											+ ar[0]
//											+ "','"
//											+ depotArrival.getVtsDatamodel()
//													.get(i).getVEHICLENO()
//											+ "'"
//											+ ") title='Dept. Details' id='alert_details"
//											+ "'>" + entryTM + "</a>");
							// jatickstock.add(exitTM);
							int timeDiff = Integer.parseInt(!depotArrival
									.getVtsDatamodel().get(i).getTIMEDIFF()
									.toString().equals("-") ? depotArrival
									.getVtsDatamodel().get(i).getTIMEDIFF()
									.toString() : "0");
							System.out.println("VEHICLE NO"
									+ depotArrival.getVtsDatamodel().get(i)
											.getVEHICLENO() + "\t" + timeDiff);
							if (timeDiff > 15) {
								/*
								 * jatickstock.add("--"); jatickstock
								 * .add(""+rs.get("TIME_DIFF").toString());
								 */
								jatickstock.add("Early");

							} else {

								if (timeDiff < -15) {
									timeDiff = timeDiff * -1;
									jatickstock.add("Late");

								} else {
									jatickstock.add("Ontime");
								}
								/*
								 * jatickstock .add(timeDiff);
								 * jatickstock.add("--");
								 */
							}

						} else {
							// jatickstock.add(entryTM);
//							jatickstock
//									.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleDepotDetailsData('"
//											+ depotArrival.getVtsDatamodel()
//													.get(i).getDEVICEID()
//											+ "','"
//											+ ar[0]
//											+ "','"
//											+ depotArrival.getVtsDatamodel()
//													.get(i).getVEHICLENO()
//											+ "'"
//											+ ") title='Dept. Details' id='alert_details"
//											+ "'>" + entryTM + "</a>");
							jatickstock.add(exitTM);
							/*
							 * jatickstock.add("--"); jatickstock.add("--");
							 */
							jatickstock.add("--");

						}
						arraytickstock.add(jatickstock);
					}
					result.put("aaData", arraytickstock);
					/*
					 * String search_term2 = request.getParameter("sSearch");
					 * String search_term3 =
					 * request.getParameter("sSearch").trim(); int cnt =
					 * getTotalVehicleDepotinoutRecords(request, col, dir,
					 * daterange, parameter); result.put("iTotalRecords", cnt);
					 * result.put("iTotalDisplayRecords", cnt);
					 *///}
			} else {
				//if (startPoint != 0) {
					//result = new JSONObject();
					VtsResponse depotDeparture = null;
					depotDeparture = port.getDepotInoutReport(
							formattedstartdate, formattedenddate, stringDate,
							parameter.trim(), startPoint, rbKey,Integer.parseInt(depot_id));
				
					JSONArray arraytickstock = new JSONArray();
					String shift="";
					for (int i = 0; i < depotDeparture.getVtsDatamodel().size(); i++) {
						// Map<String, Object> rs = aliasToValueMapList2.get(i);
						JSONArray jatickstock = new JSONArray();
						jatickstock.add(i + 1);
						jatickstock.add(depotDeparture.getVtsDatamodel().get(i)
								.getVEHICLENO());
						jatickstock.add(depotDeparture.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						switch (depotDeparture
								.getVtsDatamodel().get(i).getDutyTypeId()) {
						case 1:
							shift = "General Shift";
							break;
						case 2:
							shift = "Day 1";
							break;
						case 3:
							shift = "Day 2";
							break;
						case 4:
							shift = "Shift 1";
							break;
						case 5:
							shift = "Shift 2";
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
							break;
						case 80:
							shift = "Shift 2";
							break;
						case 82:
							shift = "Split Service";
							break;
						}
						jatickstock.add(shift);
						jatickstock.add(depotDeparture.getVtsDatamodel().get(i)
								.getSTARTTIME());
						jatickstock.add(depotDeparture.getVtsDatamodel().get(i)
								.getEXITTM());
						/*
						 * jatickstock.add(
						 * "<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleDepotDetailsData('"
						 * +
						 * depotDeparture.getVtsDatamodel().get(i).getDEVICEID()
						 * + depotDeparture
						 * .getVtsDatamodel().get(i).getLICENCENUMBER() +
						 * "') title='Dept. Details' id='alert_details" + "'>" +
						 * depotDeparture.getVtsDatamodel().get(i).getEXITTM() +
						 * "</a>");
						 */
						String entryTM = depotDeparture.getVtsDatamodel()
								.get(i).getDEPOTENTRYTM() != null ? depotDeparture
								.getVtsDatamodel().get(i).getDEPOTENTRYTM()
								.toString()
								: "--";
						String exitTM = depotDeparture.getVtsDatamodel().get(i)
								.getDEPOTEXITTM() != null ? depotDeparture
								.getVtsDatamodel().get(i).getDEPOTEXITTM()
								.toString() : "--";
						String ar[] = exitTM.split(" ");
						if (!exitTM.equals("--")) {
							// jatickstock.add(exitTM);
//							jatickstock
//									.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleDepotDetailsData('"
//											+ depotDeparture.getVtsDatamodel()
//													.get(i).getDEVICEID()
//											+ "','"
//											+ ar[0]
//											+ "','"
//											+ depotDeparture.getVtsDatamodel()
//													.get(i).getVEHICLENO()
//											+ "'"
//											+ ") title='Dept. Details' id='alert_details"
//											+ "'>" + exitTM + "</a>");
							int timeDiff = Integer.parseInt(!depotDeparture
									.getVtsDatamodel().get(i).getTIMEDIFF()
									.toString().equals("-") ? depotDeparture
									.getVtsDatamodel().get(i).getTIMEDIFF()
									.toString() : "0");

							if (timeDiff > 15) {
								jatickstock.add("Early");
								earlyCount++;

							} else {

								if (timeDiff < -15) {
									timeDiff = timeDiff * -1;
									jatickstock.add("Late");
									lateCount++;
								} else {
									jatickstock.add("Ontime");
									ontimeCount++;
								}
							}

						} else {
							// jatickstock.add(entryTM);
//							jatickstock
//									.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleDepotDetailsData('"
//											+ depotDeparture.getVtsDatamodel()
//													.get(i).getDEVICEID()
//											+ "','"
//											+ ar[0]
//											+ "','"
//											+ depotDeparture.getVtsDatamodel()
//													.get(i).getVEHICLENO()
//											+ "'"
//											+ ") title='Dept. Details' id='alert_details"
//											+ "'>" + entryTM + "</a>");
							jatickstock.add(exitTM);
							jatickstock.add("--");
						}
						arraytickstock.add(jatickstock);
					}
					result.put("aaData", arraytickstock);
				//}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	private int getStartPoint(String depot_id) {
		// TODO Auto-generated method stub
		int param = 0;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select bus_stop_id from bus_stop where org_chart_id ='"+depot_id+"'";
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

	private int getTotalVehicleDepotinoutRecords(HttpServletRequest request,
			String col, String dir, String daterange, String parameter) {

		Session alertconfigsession = null;
		int cnt = 0;
		String spliteddates[] = daterange.split("-");
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			String stringDate = sdf.format(date);
			String formattedstartdate = getDateTimeFromPickerBrandVIEW(spliteddates[0])
					+ " 00:00:00";
			String formattedenddate = getDateTimeFromPickerBrandVIEW(spliteddates[1])
					+ " 23:59:59";
			// alertconfigsession = new HibernateUtilVtu().getSession();
			String sql = "";
			// Departure
			if (parameter.equals("arrival")) {
				sql = "SELECT * FROM (SELECT TIME(a.EXIT_TM) EXIT_TM, EXIT_TM1,a.DEPOT_EXIT_TM,a.DEPOT_ENTRY_TM,"
						+ " IF(b.END_TIME IS NULL,'-',DATE_FORMAT(b.END_TIME,'%H:%i:%s'))    END_TIME, a.device_id,"
						+ " IF(b.ACT_END_TIME IS NULL,'-',DATE_FORMAT(b.ACT_END_TIME,'%H:%i:%s'))  ACT_END_TIME,"
						+
						// " IF(b.device_id IS NULL,'-',b.device_id)    device_id,"
						// +
						" IF(b.vehicle_no IS NULL,a.device_id,b.vehicle_no )    vehicle_no,"
						+ " IF(b.schedule_name IS NULL,'-',b.schedule_name)    schedule_name,"
						+ " IF(b.duty_type_id IS NULL,'-',b.duty_type_id)    duty_type_id,"
						+ " IF(b.START_TIME IS NULL,'-',b.START_TIME)    START_TIME,"
						+ " IF(TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.END_TIME) IS NULL,'-', TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.END_TIME)) AS TIME_DIFF,"
						+ " IF(b.ID IS NULL,'-',b.ID)    ID FROM (SELECT min(depot_exit_tm)     AS EXIT_TM,"
						+ " DATE_FORMAT(DEPOT_EXIT_TM,'%d-%m-%Y %H:%i:%s') AS DEPOT_EXIT_TM,"
						+ " CONCAT(CURRENT_DATE,' ',DATE_FORMAT(min(DEPOT_ENTRY_TM),'%H:%i:%s')) AS EXIT_TM1,"
						+ " DATE_FORMAT(min(DEPOT_ENTRY_TM),'%d-%m-%Y %H:%i:%s') AS DEPOT_ENTRY_TM, device_id FROM vts_depot_geofence"
						+ " WHERE depot_entry_tm BETWEEN ? AND ? GROUP BY device_id) a"
						+ " LEFT JOIN (SELECT IF(END_TIME IS NULL, '-',CONCAT(CURRENT_DATE,' ',END_TIME)) AS END_TIME, START_TIME,ID, device_id,"
						+ " IF(ACT_END_TIME	 IS NULL, '-',CONCAT(CURRENT_DATE,' ',ACT_END_TIME)) AS ACT_END_TIME,VEHICLE_NO,SCHEDULE_NAME,duty_type_id FROM waybill_trip_details w WHERE duty_dt = ? "
						+ " AND w.trip_number = (SELECT  MAX(trip_number) FROM waybill_trip_details  WHERE waybill_id = w.waybill_id"
						+ " AND duty_type_id != 2 AND END_POINT = 4650)  AND duty_type_id != 2 ORDER BY device_id) b ON (a.device_id = b.device_id)) z";
				int srch = 0;
				String search_term = request.getParameter("sSearch");
				System.out.println("****" + search_term + "*****");
				if (search_term != null && !search_term.equals("")) {
					sql += " where z.vehicle_no like '%" + search_term + "%'";
					sql += " OR z.device_id like '%" + search_term + "%'";
					sql += " OR z.END_TIME like '%" + search_term + "%'";
					sql += " OR z.ACT_END_TIME like '%" + search_term + "%'";
					sql += " OR z.TIME_DIFF like '%" + search_term + "%'";
					sql += " OR z.schedule_name like '%" + search_term + "%'";
					sql += " OR z.DEPOT_EXIT_TM like '%" + search_term + "%'";
					sql += " OR z.DEPOT_ENTRY_TM like '%" + search_term + "%'";
				}

			} else {
				sql = "SELECT * FROM (SELECT TIME(a.EXIT_TM)     EXIT_TM, EXIT_TM1,a.DEPOT_EXIT_TM,a.DEPOT_ENTRY_TM,"
						+ " IF(b.START_TIME IS NULL,'-',DATE_FORMAT(b.START_TIME,'%H:%i'))    START_TIME, "
						+
						// "a.device_id," +
						" IF(b.ACT_START_TIME IS NULL,'-',DATE_FORMAT(b.ACT_START_TIME,'%H:%i'))  ACT_START_TIME,"
						+ " IF(b.device_id IS NULL,'-',b.device_id)    device_id,"
						+ " IF(b.vehicle_no IS NULL,a.device_id,b.vehicle_no )    vehicle_no,"
						+ " IF(b.schedule_name IS NULL,'-',b.schedule_name)    schedule_name,"
						+ " IF(b.duty_type_id IS NULL,'-',b.duty_type_id)    duty_type_id,"
						+ " IF(TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.START_TIME) IS NULL,'-', TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.START_TIME)) AS TIME_DIFF,"
						+ " IF(b.ID IS NULL,'-',b.ID)    IDF FROM (SELECT depot_exit_tm     AS EXIT_TM,"
						+ " DATE_FORMAT(DEPOT_EXIT_TM,'%d-%m-%Y %H:%i:%s') AS DEPOT_EXIT_TM,"
						+ " CONCAT(CURRENT_DATE,' ',DATE_FORMAT(DEPOT_EXIT_TM,'%H:%i:%s')) AS EXIT_TM1,"
						+ " DATE_FORMAT(DEPOT_ENTRY_TM,'%d-%m-%Y %H:%i:%s') AS DEPOT_ENTRY_TM, device_id  FROM vts_depot_geofence"
						+ " WHERE DEPOT_EXIT_TM BETWEEN ? AND ?  GROUP BY device_id) a"
						+ " LEFT JOIN (SELECT IF(START_TIME IS NULL, '-',CONCAT(CURRENT_DATE,' ',START_TIME)) AS START_TIME,ID,device_id,VEHICLE_NO,"
						+ " IF(ACT_START_TIME IS NULL, '-',CONCAT(CURRENT_DATE,' ',ACT_START_TIME)) AS ACT_START_TIME,SCHEDULE_NAME,duty_type_id FROM waybill_trip_details w WHERE duty_dt = ? "
						+ " AND trip_number = 2 AND duty_type_id != 3  AND START_POINT = 5002 ORDER BY device_id) b"
						+ " ON (a.device_id = b.device_id)) z";
				int srch = 0;
				String search_term = request.getParameter("sSearch");
				if (search_term != null && !search_term.equals("")) {
					sql += " where ( vehicle_no like '%" + search_term + "%'";
					sql += " OR z.device_id like '%" + search_term + "%'";
					sql += " OR z.TIME_DIFF like '%" + search_term + "%'";
					sql += " OR z.START_TIME like '%" + search_term + "%'";
					sql += " OR z.ACT_START_TIME like '%" + search_term + "%'";
					sql += " OR z.schedule_name like '%" + search_term + "%'";
					sql += " OR z.DEPOT_EXIT_TM like '%" + search_term + "%'";
					sql += " OR z.DEPOT_ENTRY_TM like '%" + search_term + "%')";
				}
			}
			Query query = alertconfigsession.createSQLQuery(sql)
					.addScalar("VEHICLE_NO", Hibernate.STRING)
					.addScalar("device_id", Hibernate.STRING)
					.addScalar("DEPOT_EXIT_TM", Hibernate.STRING)
					.addScalar("DEPOT_ENTRY_TM", Hibernate.STRING)
					.addScalar("TIME_DIFF", Hibernate.STRING)
					.addScalar("SCHEDULE_NAME", Hibernate.STRING)
					.addScalar("START_TIME", Hibernate.STRING)
					.addScalar("EXIT_TM", Hibernate.STRING);
			query.setParameter(0, formattedstartdate);
			query.setParameter(1, formattedenddate);
			query.setParameter(2, stringDate);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			cnt = aliasToValueMapList.size();

		} catch (Exception e) {
			e.printStackTrace();
			return cnt;
		} finally {
			if (alertconfigsession != null) {
				alertconfigsession.close();
			}
		}
		return cnt;

	}

	@SuppressWarnings("unchecked")
	public JSONObject showVehicleDepotinoutData1(int total,
			HttpServletRequest request, String col, String dir,
			String daterange, String parameter, String depot_id) {
		JSONObject result = new JSONObject();
		JSONArray arraytickstock = new JSONArray();
		int arr[] = new int[3];

		if (parameter.equals("Arrival")) {
			arr = getLateEarlyandOntimeCountArrival(daterange, parameter,
					depot_id);
			arraytickstock.add(arr[0]);
			arraytickstock.add(arr[1]);
			arraytickstock.add(arr[2]);
		} else {
			arr = getLateEarlyandOntimeCountDeparture(daterange, parameter,
					depot_id);
			arraytickstock.add(arr[0]);
			arraytickstock.add(arr[1]);
			arraytickstock.add(arr[2]);
		}
		result.put("countData", arraytickstock);
		return result;

	}

	public int[] getLateEarlyandOntimeCountArrival(String daterange,
			String parameter, String depot_id) {
		String spliteddates[] = daterange.split("-");
		String depoentrytime1 = null;
		JSONObject result = new JSONObject();
		SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sm2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int earlyCount = 0, lateCount = 0, ontimeCount = 0;
		int arr[] = new int[3];

		try {

			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			Date date = new Date();
			Date date2 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			// String stringDate = sdf.format(date);
			String stringDate = getDateTimeFromPickerBrandVIEW(spliteddates[0]);
			String formattedstartdate = getDateTimeFromPickerBrandVIEW(spliteddates[0])
					+ " 00:00:00";
			String formattedenddate = getDateTimeFromPickerBrandVIEW(spliteddates[1])
					+ " 23:59:59";
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			int startPoint = 0;

			//startPoint  = getStartPoint(depot_id);
			//if (startPoint != 0) {
				VtsResponse depotArrival = null;
				depotArrival = port.getDepotInoutReportCount(
						formattedstartdate, formattedenddate, stringDate,
						parameter.trim(), startPoint, rbKey,Integer.parseInt(depot_id));
				// Session alertconfigsession = new
				// HibernateUtilVtu().getSession();
				/*
				 * String sql = "SELECT * FROM (SELECT " +
				 * " IF(b.vehicle_no IS NULL,(select lICENCE_NUMBER from vehicle_device_rel where DEVICE_ID=a.device_id),b.vehicle_no )    vehicle_no,"
				 * +
				 * " IF(b.schedule_name IS NULL,'-',b.schedule_name)    schedule_name,"
				 * +
				 * " TIME(a.EXIT_TM) EXIT_TM, EXIT_TM1,a.DEPOT_EXIT_TM,a.DEPOT_ENTRY_TM,a.ACT_TIME_ARR,"
				 * +
				 * " IF(b.END_TIME IS NULL,'-',DATE_FORMAT(b.END_TIME,'%H:%i:%s'))    END_TIME,"
				 * +
				 * " IF(b.ACT_END_TIME IS NULL,'-',DATE_FORMAT(b.ACT_END_TIME,'%H:%i:%s'))  ACT_END_TIME, a.device_id,"
				 * +
				 * 
				 * // " IF(b.device_id IS NULL,'-',b.device_id)    device_id," +
				 * 
				 * " IF(b.duty_type_id IS NULL,'-',b.duty_type_id)    duty_type_id,"
				 * + " IF(b.START_TIME IS NULL,'-',b.START_TIME)    START_TIME,"
				 * +
				 * " IF(TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.END_TIME) IS NULL,'-', TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.END_TIME)) AS TIME_DIFF,"
				 * +
				 * " IF(b.ID IS NULL,'-',b.ID)    ID FROM (SELECT depot_exit_tm     AS EXIT_TM,"
				 * +
				 * " DATE_FORMAT(DEPOT_EXIT_TM,'%d-%m-%Y %H:%i:%s') AS DEPOT_EXIT_TM,"
				 * +
				 * " CONCAT(CURRENT_DATE,' ',DATE_FORMAT(DEPOT_ENTRY_TM,'%H:%i:%s')) AS EXIT_TM1,DATE_FORMAT(DEPOT_ENTRY_TM,'%H:%i:%s') as ACT_TIME_ARR,"
				 * +
				 * " DATE_FORMAT(DEPOT_ENTRY_TM,'%d-%m-%Y %H:%i:%s') AS DEPOT_ENTRY_TM, device_id FROM vts_depot_geofence"
				 * +
				 * " WHERE depot_entry_tm BETWEEN ? AND ? GROUP BY device_id) a"
				 * +
				 * " LEFT JOIN (SELECT IF(END_TIME IS NULL, '-',CONCAT(CURRENT_DATE,' ',END_TIME)) AS END_TIME,"
				 * +
				 * " IF(ACT_END_TIME	 IS NULL, '-',CONCAT(CURRENT_DATE,' ',ACT_END_TIME)) AS ACT_END_TIME, START_TIME,ID, device_id,"
				 * +
				 * " VEHICLE_NO,SCHEDULE_NAME,duty_type_id FROM waybill_trip_details w WHERE duty_dt = ? "
				 * +
				 * " AND w.trip_number = (SELECT  MAX(trip_number) FROM waybill_trip_details  WHERE waybill_id = w.waybill_id"
				 * +
				 * " AND duty_type_id != 2 AND END_POINT = 4650)  AND duty_type_id != 2 group by device_id ORDER BY device_id) b ON (a.device_id = b.device_id)) z"
				 * ; Query query = alertconfigsession.createSQLQuery(sql)
				 * .addScalar("vehicle_no", Hibernate.STRING)
				 * .addScalar("schedule_name", Hibernate.STRING)
				 * .addScalar("END_TIME", Hibernate.STRING)
				 * .addScalar("ACT_END_TIME", Hibernate.STRING)
				 * .addScalar("DEPOT_ENTRY_TM", Hibernate.STRING)
				 * .addScalar("DEPOT_EXIT_TM", Hibernate.STRING)
				 * .addScalar("TIME_DIFF", Hibernate.STRING)
				 * .addScalar("EXIT_TM", Hibernate.STRING)
				 * .addScalar("EXIT_TM1", Hibernate.STRING)
				 * .addScalar("ACT_TIME_ARR", Hibernate.STRING)
				 * 
				 * ;
				 * 
				 * query.setParameter(0, formattedstartdate);
				 * query.setParameter(1, formattedenddate);
				 * query.setParameter(2, stringDate); query.setResultTransformer
				 * (AliasToEntityMapResultTransformer.INSTANCE);
				 * List<Map<String, Object>> aliasToValueMapList2 = null;
				 * aliasToValueMapList2 = query.list();
				 * 
				 * JSONArray arraytickstock = new JSONArray();
				 */
				for (int i = 0; i < depotArrival.getVtsDatamodel().size(); i++) {
					// Map<String, Object> rs = aliasToValueMapList2.get(i);
					int timeDiff = Integer.parseInt(!depotArrival
							.getVtsDatamodel().get(i).getTIMEDIFF().toString()
							.equals("-") ? depotArrival.getVtsDatamodel()
							.get(i).getTIMEDIFF().toString() : "0");
					System.out.println("timeDiff"+timeDiff);
					if (timeDiff > 15) {
						// jatickstock.add("Early");
						earlyCount++;

					} else {

						if (timeDiff < -15) {
							timeDiff = timeDiff * -1;
							// jatickstock.add("Late");
							lateCount++;
						} else {
							// jatickstock.add("Ontime");
							ontimeCount++;
						}
					}
				}
				arr[0] = lateCount;
				arr[1] = earlyCount;
				arr[2] = ontimeCount;
			//}
		} catch (Exception ex) {

		} finally {

		}
		return arr;
	}

	public int[] getLateEarlyandOntimeCountDeparture(String daterange,
			String parameter, String depot_id) {
		String spliteddates[] = daterange.split("-");
		SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sm2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		int earlyCount = 0, lateCount = 0, ontimeCount = 0;
		int arr[] = new int[3];
		try {
			String DATE_FORMAT_NOW = "yyyy-MM-dd";
			Date date = new Date();
			Date date2 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
			// String stringDate = sdf.format(date);
			String stringDate = getDateTimeFromPickerBrandVIEW(spliteddates[0]);
			String formattedstartdate = getDateTimeFromPickerBrandVIEW(spliteddates[0])
					+ " 00:00:00";
			String formattedenddate = getDateTimeFromPickerBrandVIEW(spliteddates[1])
					+ " 23:59:59";
			// Session alertconfigsession = new HibernateUtilVtu().getSession();
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			VtsResponse depotDeparture = null;
			int startPoint = 0;

			//startPoint  = getStartPoint(depot_id);
			//if (startPoint != 0) {
				depotDeparture = port.getDepotInoutReportCount(
						formattedstartdate, formattedenddate, stringDate,
						parameter.trim(), startPoint, rbKey,Integer.parseInt(depot_id));
				/*
				 * String sql =
				 * "SELECT * FROM (SELECT TIME(a.EXIT_TM)     EXIT_TM, EXIT_TM1,a.DEPOT_EXIT_TM,a.DEPOT_ENTRY_TM,"
				 * + " " +
				 * " IF(b.START_TIME IS NULL,'-',DATE_FORMAT(b.START_TIME,'%H:%i:%s'))    START_TIME, "
				 * +
				 * " IF(b.ACT_START_TIME IS NULL,'-',DATE_FORMAT(b.ACT_START_TIME,'%H:%i:%s'))  ACT_START_TIME,"
				 * + " IF(b.device_id IS NULL,'-',b.device_id)    device_id," +
				 * " IF(b.vehicle_no IS NULL,(select lICENCE_NUMBER from vehicle_device_rel where DEVICE_ID=a.device_id),b.vehicle_no )    vehicle_no,"
				 * +
				 * " IF(b.schedule_name IS NULL,'-',b.schedule_name)    schedule_name,"
				 * +
				 * " IF(b.duty_type_id IS NULL,'-',b.duty_type_id)    duty_type_id,"
				 * +
				 * " IF(TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.START_TIME) IS NULL,'-', TIMESTAMPDIFF(MINUTE,(a.EXIT_TM1), b.START_TIME)) AS TIME_DIFF,"
				 * +
				 * " IF(b.ID IS NULL,'-',b.ID)    ID FROM (SELECT max(depot_exit_tm)     AS EXIT_TM,"
				 * +
				 * " DATE_FORMAT(max(DEPOT_EXIT_TM),'%d-%m-%Y %H:%i:%s') AS DEPOT_EXIT_TM,"
				 * +
				 * " CONCAT(CURRENT_DATE,' ',DATE_FORMAT(max(DEPOT_EXIT_TM),'%H:%i:%s')) AS EXIT_TM1,"
				 * +
				 * " DATE_FORMAT(DEPOT_ENTRY_TM,'%d-%m-%Y %H:%i:%s') AS DEPOT_ENTRY_TM, device_id  FROM vts_depot_geofence"
				 * +
				 * " WHERE DEPOT_EXIT_TM BETWEEN ? AND ?  GROUP BY device_id) a"
				 * +
				 * " LEFT JOIN (SELECT IF(START_TIME IS NULL, '-',CONCAT(CURRENT_DATE,' ',START_TIME)) AS START_TIME,"
				 * +
				 * " IF(ACT_START_TIME IS NULL, '-',CONCAT(CURRENT_DATE,' ',ACT_START_TIME)) AS ACT_START_TIME,ID,device_id,VEHICLE_NO,"
				 * +
				 * " SCHEDULE_NAME,duty_type_id FROM waybill_trip_details w WHERE duty_dt = ? "
				 * +
				 * " AND trip_number = 1 AND duty_type_id != 3  AND START_POINT = 8773 group by device_id ORDER BY device_id,START_TIME) b"
				 * + " ON (a.device_id = b.device_id)) z "; Query query =
				 * alertconfigsession.createSQLQuery(sql)
				 * .addScalar("vehicle_no", Hibernate.STRING)
				 * .addScalar("device_id", Hibernate.STRING)
				 * .addScalar("DEPOT_EXIT_TM", Hibernate.STRING)
				 * .addScalar("DEPOT_ENTRY_TM", Hibernate.STRING)
				 * .addScalar("TIME_DIFF", Hibernate.STRING)
				 * .addScalar("schedule_name", Hibernate.STRING)
				 * .addScalar("START_TIME", Hibernate.STRING)
				 * .addScalar("ACT_START_TIME", Hibernate.STRING)
				 * .addScalar("EXIT_TM", Hibernate.STRING);
				 * 
				 * query.setParameter(0, formattedstartdate);
				 * query.setParameter(1, formattedenddate);
				 * query.setParameter(2, stringDate); query.setResultTransformer
				 * (AliasToEntityMapResultTransformer.INSTANCE);
				 * List<Map<String, Object>> aliasToValueMapList2 = null;
				 * aliasToValueMapList2 = query.list(); JSONArray arraytickstock
				 * = new JSONArray();
				 */

				for (int i = 0; i < depotDeparture.getVtsDatamodel().size(); i++) {
					// Map<String, Object> rs = aliasToValueMapList2.get(i);
					int timeDiff = Integer.parseInt(!depotDeparture
							.getVtsDatamodel().get(i).getTIMEDIFF().toString()
							.equals("-") ? depotDeparture.getVtsDatamodel()
							.get(i).getTIMEDIFF().toString() : "0");
//					System.out.println("timeDiff" + timeDiff);
					if (timeDiff > 15) {
						// CALL WEBSERVICE FOR EARLY DEPARTURE..

						System.out.println("Early");
						// jatickstock.add("Early");
						earlyCount++;

					} else {
						// TO CALL WEBSERVICE FOR LATE DEPT COUNT..
						if (timeDiff < -15) {
							System.out.println("Late");
							// timeDiff=timeDiff *-1;
							// jatickstock.add("Late");
							lateCount++;
						} else {
							// jatickstock.add("Ontime");
							ontimeCount++;
						}
					}
				}
				arr[0] = lateCount;
				arr[1] = earlyCount;
				arr[2] = ontimeCount;
			//}
		} catch (Exception ex) {

		} finally {

		}
		return arr;
	}
}
