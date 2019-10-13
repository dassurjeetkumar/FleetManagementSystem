package com.trimax.its.vts.dao;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.jaxb.xml.trimax.com.VtsResponse;
import model.jaxb.xml.trimax.com.VtsResponse1;
import model.jaxb.xml.trimax.com.VtsResponse2;
import model.jaxb.xml.trimax.com.VtsResponse3;
import model.jaxb.xml.trimax.com.VtsResponse4;
import model.jaxb.xml.trimax.com.VtsResponse5;
import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.trimax.its.common.Common;
import com.trimax.its.dao.getReasonListDao;
import com.trimax.its.report.action.VehicleLocationReportAction;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vehicle.model.AccidentType;
import com.trimax.its.vehicle.model.BreakDownTypeDetails;
import com.trimax.its.vehicle.model.OrganisationChart;
//import com.trimax.its.vts.action.ExportToExcel;
import com.trimax.its.vts.model.Busstop;
import com.trimax.its.vts.model.VtsDataModel;
import com.trimax.its.vts.model.WaybillDetails;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class VtsDataDAO {
	private static VtsDataDAO singleton = new VtsDataDAO( );
	private VtsDataDAO() {
		// TODO Auto-generated constructor stub
	}
	public static VtsDataDAO getInstance(){
		if(singleton==null){
			
			singleton = new VtsDataDAO();
		}
		singleton.hashCode();
		return singleton;
	}
	
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

	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getTrackingData(
			String FromDate, String TillDate, String DeviceImei, int LastId,
			String limit, int depotID) {
		List<com.trimax.ws.vts.vtsutility.VtsDataModel> coordinateList = new ArrayList<com.trimax.ws.vts.vtsutility.VtsDataModel>();

		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse result = port
					.getTrackingData(LastId, DeviceImei, FromDate, TillDate,
							limit, rbKey, depotID);
			for (int i = 0; i < result.getVtsDatamodel().size(); i++) {
				coordinateList = result.getVtsDatamodel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session1.close();
		}
		return coordinateList;
	}
	
	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getTrackingDataForAll(
			String FromDate, String TillDate, String DeviceImei, int LastId,
			String limit, String depotIds) {
//		System.out.println("in tracking for all");
		List<com.trimax.ws.vts.vtsutility.VtsDataModel> coordinateList = new ArrayList<com.trimax.ws.vts.vtsutility.VtsDataModel>();

		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			System.out.println("LastId "+LastId+"DeviceImei"+DeviceImei+"FromDate"+FromDate+"TillDate"+TillDate+"limit"+limit+"divId"+depotIds);
		
			model.jaxb.xml.trimax.com.VtsResponse result = port
					.getTrackingAllVehicleDataDepotWise(LastId, DeviceImei, FromDate, TillDate,
							limit, rbKey, depotIds);
//			System.out.println("in array"+result.getVtsDatamodel().size());
			for (int i = 0; i < result.getVtsDatamodel().size(); i++) {
				coordinateList = result.getVtsDatamodel();
				
			}
			System.out.println("result=="+coordinateList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// session1.close();
		}
		return coordinateList;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getData(int total, HttpServletRequest request,
			String cols, String dir, String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Session session3 = null;
		HibernateUtilVtu h = new HibernateUtilVtu();
		try {
			int totalAfterFilter = total;
			String searchSQL = "";
			String sql = "select count(*) as totalcount,vat.PACKET_CODE,vat.MISC_BYTES, vam.ALERT_SHORT_CODE,ALERT_DESC from vts_alert_data vat"
					+ " inner join vts_alert_master vam on vam.MISC_BYTES =vat.MISC_BYTES and "
					+ " vam.PACKET_CODE = vat.PACKET_CODE where vat.CREATED_DATE between '"
					+ fromDate
					+ "' and '"
					+ tillDate
					+ "' group by vat.PACKET_CODE,vat.MISC_BYTES";
			JSONArray array = new JSONArray();
			session3 = h.getSession("");
			Query query = session3.createSQLQuery(sql)
					.addScalar("totalcount", Hibernate.STRING)
					.addScalar("ALERT_SHORT_CODE", Hibernate.STRING)
					.addScalar("ALERT_DESC", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				ja.add(rs.get("totalcount").toString());
				ja.add(rs.get("ALERT_SHORT_CODE").toString());
				ja.add(rs.get("ALERT_DESC").toString());
				array.add(ja);
			}
			// totalAfterFilter = getTotalPageRecords(fromDate,tillDate);
			result.put("aaData", array);
			result.put("iTotalRecords", aliasToValueMapList.size());
			result.put("iTotalDisplayRecords", aliasToValueMapList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return result;
	}

	// Calling WebService to Fetch Data For Alert 01-09-2014
	@SuppressWarnings("unchecked")
	public JSONObject getDataForChart(int total, HttpServletRequest request,
			String cols, String dir, String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		List vehiclelist = new ArrayList();
		try {
			//System.out.println("Start Time"+new Date());
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO process result here

			model.jaxb.xml.trimax.com.VtsResponse alertresult = port
					.getAlertData(rbKey, id);
			vehiclelist = VtsDataDAO.getInstance().GetActiveVehicles();
			JSONArray array = new JSONArray();
			int totalCount = 0;
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				// Map<String, Object> rs = aliasToValueMapList.get(i);
			
				totalCount += alertresult.getVtsDatamodel().get(i)
						.getTotalcount();
//				System.out.println("total @@@@@"+totalCount);
			}
			int vehicleCnt = vehiclelist.size();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				// Map<String, Object> rs = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				// Calling vehicle List
				for (int j = 0; j < vehicleCnt; j++) {
					if (vehiclelist.get(j).equals(
							alertresult.getVtsDatamodel().get(i).getDEVICEID())) {
						//System.out.println("vehiclelist.get(j)"+vehiclelist.get(j));
						ja.add(vehiclelist.get(j));
					}
				}

				ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				ja.add(alertresult.getVtsDatamodel().get(i).getALERTSHORTCODE());
				ja.add(alertresult.getVtsDatamodel().get(i).getALERTDESC());
				double per = Double.parseDouble(String.valueOf(alertresult
						.getVtsDatamodel().get(i).getTotalcount()))
						* 100 / totalCount;
				ja.add(per);
				ja.add(alertresult.getVtsDatamodel().get(i).getPACKETCODE());
				array.add(ja);
			//	System.out.println("End Time"+new Date());
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForChartGrid(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		int totalCount = 0;
		String check="";
		getReasonListDao dao=new getReasonListDao();
		try {
			Session session = HibernateUtil.getSession("");
			// rajesh added

			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
		
//System.out.println("depot id=="+id);
			// end

			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse alertresult = port
					.getDataForChartGrid(packet_code, "", "", id, rbKey);
			JSONArray array = new JSONArray();

			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);

				Date ticketDate1 = new Date();
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				ticketDate = sm.format(ticketDate1);
		

				String time = alertresult.getVtsDatamodel().get(i)
						.getSTARTTIME();
				
			
				String orgname = alertresult.getVtsDatamodel().get(i)
						.getOrgName();
				orgname = orgname.replace(" ", "");

		
				
				String startdatetime=alertresult.getVtsDatamodel().get(i).getACTSTARTTIME();
				
				String[] parts = startdatetime.split(" ");
				String part1 = parts[0]; // 004
				String time1 = parts[1];
				
             String enddatetime=alertresult.getVtsDatamodel().get(i).getACTENDTIME();
				
				String[] parts1 = enddatetime.split(" ");
				String part3 = parts1[0]; // 004
				String time2 = parts1[1];
				
				ja.add("<a  href='#mymodal1'  onclick=javascript:drawDeviatedRoute('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ ticketDate
						+ "','"
						+ time1
						
						+ "','"
						+ time2
						
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "','"
						+ orgname
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getBusStopId()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getROUTEID()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getSTARTPOINT()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getENDPOINT()
						+ "') >"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "' id='vehicleid' value='"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'");

				

				ja.add(alertresult.getVtsDatamodel().get(i).getOrgName());
				if (alertresult.getVtsDatamodel().get(i).getPACKETCODE()
						.equals("DV")) {
				} else {
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getALERTSHORTCODE());
					ja.add(alertresult.getVtsDatamodel().get(i).getALERTDESC());
				}
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getTRIPNUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSTARTTIME());
				if (alertresult.getVtsDatamodel().get(i).getPACKETCODE()
						.equals("DV")) {
					String shift = "";
					switch (alertresult.getVtsDatamodel().get(i)
							.getDutyTypeId()) {
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
					ja.add(shift);
				} else {
					ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				}
//				check=dao.checkReasonForDeviate(alertresult.getVtsDatamodel().get(i).getVEHICLENO(),alertresult.getVtsDatamodel().get(i).getDEVICEID());
			//	System.out.println("check--"+alertresult.getVtsDatamodel().get(i).getReason());
				if(alertresult.getVtsDatamodel().get(i).getReason()==null || alertresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("") || alertresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("null")  ){
					
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal19'  onclick=javascript:getDeviatedReasonData('"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()

				+ "','"
				+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

				+ "','"
	            +alertresult.getVtsDatamodel().get(i).getOrgName()
				+ "') title='Alert Details' id='alert_details"
				+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
				+ "' value='"
				+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
				+ "'>"
				+"Enter Reason"+ "</a>");
				}
				else{
					ja.add(alertresult.getVtsDatamodel().get(i).getReason());
				}
				
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForChartGridTampering(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		int totalCount = 0;
		Session session = null;
		try {
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}


			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse5 alertresult = port
					.webGetTamperingAlert(packet_code, fromDate, tillDate, id,
							rbKey);
			JSONArray array = new JSONArray();
			// for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			// totalCount += alertresult.getVtsDatamodel().get(i)
			// .getTotalcount();
			// }
			
			
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				// ticketDate=sm.format(ticketDate1);
				//System.out.println("ticketDate" + ticketDate);
				ticketDate =alertresult.getVtsDatamodel().get(i).getISTDATE();

				String dateType[] = ticketDate.split(" ");
				String y1 = dateType[0];

				//String y2 = common.getDateFromDateTime_(y1);
				String time = dateType[1];
				//System.out.println(time + "," + y1);
				
				ja.add(i + 1);
				
				ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAllVts('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ y1
						+ "','"
						+ time
						+ "','"
						+ alertresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getDEPOTNAME().replace(" ", "")
						+ "') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()

						+ "' id='deviceid' value='"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ alertresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER()
						+ "' id='vehicleid' value='"
						+ alertresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER() + "'");
				//ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getDEPOTNAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				ja.add(getStopList(
						Double.parseDouble(alertresult.getVtsDatamodel().get(i)
								.getLAT()),
						Double.parseDouble(alertresult.getVtsDatamodel().get(i)
								.getLONGITUDE()), session, relatedPointType));
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForArrival(String packet_code) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		String vehicleid = "";
		String orgname = "";
		String check="";
		getReasonListDao dao=new getReasonListDao();
		try {
			// rajesh changes for access
			Session session = HibernateUtil.getSession("");
			HttpServletRequest request = ServletActionContext.getRequest();
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			

			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse1 alertresult = port
					.webGetDepotArrivalTime(packet_code, id, "", "", rbKey, 0);
			JSONArray array = new JSONArray();
			int count = 0;
			Common common = new Common();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();

//				if (alertresult.getVtsDatamodel().get(i).getDIFF() > 15) {

					ja.add(count + 1);

					SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
					String ticketDate = "";
					// ticketDate=sm.format(ticketDate1);
					
					ticketDate = alertresult.getVtsDatamodel().get(i)
							.getActualDeparture();

					String dateType[] = ticketDate.split(" ");
					String y1 = dateType[0];

					String y2 = common.getDateFromDateTime_(y1);
					String time = dateType[1];
				
					
					// ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());

					ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAllVtsData('"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "','"
							+ y2
							+ "','"
							+ time
							+ "','"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO()
							+ "','"
							+ alertresult.getVtsDatamodel().get(i).getOrgName().replace(" ", "")
							+ "') >"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO()
							+ "</a>"

							+ "<input type='hidden' name='device_id_"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEVICEID()

							+ "' id='deviceid' value='"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "'"
							+ ""
							+ "<input type='hidden' name='ticketDate"
							+ ticketDate

							+ "' id='ticketDate1' value='"
							+ ticketDate
							+ "'"
							+ ""
							+ "<input type='hidden' name='vehicle_no_'"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO()
							+ "' id='vehicleid' value='"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO() + "'");

					ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENO());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getENDBUSSTOPNAME());
					String shift = "";
					switch (Integer.parseInt(alertresult.getVtsDatamodel()
							.get(i).getDutyId())) {
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
					ja.add(shift);
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getScheduleDeparture());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getActualDeparture());
					if (alertresult.getVtsDatamodel().get(i)
							.getActualDeparture() == null) {
						ja.add("-");
					} else {
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getArrivalTIMEDIFF());
					}
//               check = dao.checkReasonForEarly(alertresult.getVtsDatamodel().get(i).getVEHICLENO(),alertresult.getVtsDatamodel().get(i).getDEVICEID());
             //  System.out.println("check--"+alertresult.getVtsDatamodel().get(i).getReason());
				if(alertresult.getVtsDatamodel().get(i).getReason()==null || alertresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("") || alertresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("null")  ){
					ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal15'  onclick=javascript:getEarlyArrivalReasonData('"
								+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()

						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "','"
			            +ticketDate.replace(" ", "@")
						+ "') title='Alert Details' id='alert_details"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "' value='"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'>"
						+"Enter Reason"+ "</a>");
               }
               else{
            	   ja.add(alertresult.getVtsDatamodel().get(i).getReason());
               }
					array.add(ja);
					count++;
//				}
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForDeparture(String packet_code,
			String depot_code, HttpServletRequest request, String cols,
			String dir, String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		String check="";
		getReasonListDao dao=new getReasonListDao();
		try {

			// rajesh changes for access
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			

			// end
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// int startPoint = getStartPoint(depot_id);
			model.jaxb.xml.trimax.com.VtsResponse2 alertresult = port
					.webGetDepotDepartureTime(packet_code, id, "", "", rbKey, 0);
			JSONArray array = new JSONArray();
			int count = 0;
			Common common = new Common();

			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				

				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				ticketDate = alertresult.getVtsDatamodel().get(i)
						.getActualArrival();

				String dateType[] = ticketDate.split(" ");
				String y1 = dateType[0];

				String y2 = common.getDateFromDateTime_(y1);
				String time = dateType[1];

//				if (alertresult.getVtsDatamodel().get(i).getDIFF() < -15) {
					ja.add(count + 1);
					ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAllVtsLateDeparture('"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "','"
							+ y2
							+ "','"
							+ time
							+ "','"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO()
							+ "','"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEPOTNAME().replace(" ", "")
							+ "') >"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO()
							+ "</a>"

							+ "<input type='hidden' name='device_id_"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEVICEID()

							+ "' id='deviceid' value='"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "'"
							+ ""
							+ "<input type='hidden' name='ticketDate"
							+ ticketDate

							+ "' id='ticketDate1' value='"
							+ ticketDate
							+ "'"
							+ ""
							+ "<input type='hidden' name='vehicle_no_'"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO()
							+ "' id='vehicleid' value='"
							+ alertresult.getVtsDatamodel().get(i)
									.getVEHICLENO() + "'");
					// ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHEDULENAME());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSTARTBUSSTOPNAME());
					String shift = "";
					
					switch (Integer.parseInt(alertresult.getVtsDatamodel()
							.get(i).getDutyId())) {
					
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
//					case 0:
//						shift = "Split Service";
//						break;
					case 82:
						shift = "Split Service";
						break;
					}
					ja.add(shift);
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getScheduleArrival());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getActualArrival());
					if (alertresult.getVtsDatamodel().get(i).getActualArrival() == null) {
						ja.add("-");
					} else {
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getTIMEDIFF());
					}
//                  check = dao.checkReasonForLate(alertresult.getVtsDatamodel().get(i).getVEHICLENO(), alertresult.getVtsDatamodel().get(i).getDEVICEID());
//                  System.out.println("check--"+check);
					 System.out.println("check--"+alertresult.getVtsDatamodel().get(i).getReason());
						if(alertresult.getVtsDatamodel().get(i).getReason()==null || alertresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("") || alertresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("null")  ){
//                  if(check.equalsIgnoreCase(null) || check ==""){
						ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal14'  onclick=javascript:getLateDeptReasonData('"
									+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()

							+ "','"
							+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

							+ "','"
							+ alertresult.getVtsDatamodel().get(i).getSCHEDULENAME().replace(" ", "@")
							+ "','"
				            +ticketDate.replace(" ", "@")
							+ "') title='Alert Details' id='alert_details"
							+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
							+ "' value='"
							+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
							+ "'>"
							+"Enter Reason"+ "</a>");
                  }else{
                	  ja.add(alertresult.getVtsDatamodel().get(i).getReason());
                  }
						
					
					array.add(ja);
					count++;
//				}
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForDelayedDeparture(String packet_code,
			String depot_code, HttpServletRequest request, String cols,
			String dir, String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		String deviceid = "";
		String orgname = "";
		String check="";
		getReasonListDao dao=new getReasonListDao();
		try {

			// rajesh changes for access
			Session session = HibernateUtil.getSession("");
			Common common = new Common();
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			

			// end
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// int startPoint = getStartPoint(depot_id);
			model.jaxb.xml.trimax.com.VtsResponse2 alertresult = port
					.webGetDepotDelayedDepartureTime(packet_code, id, "", "",
							rbKey, 0);
			JSONArray array = new JSONArray();
			int count = 0;
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();

				// if (alertresult.getVtsDatamodel().get(i).getDIFF() < -15) {
				ja.add(count + 1);
	
			
				Date ticketDate1 = new Date();
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				ticketDate = sm.format(ticketDate1);
				String time = alertresult.getVtsDatamodel().get(i)
						.getScheduleArrival();
				
				ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAll('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
							+ ticketDate
							//+ "','"
							//+ time
							+ "','"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getSTARTBUSSTOPNAME().replace(" ", "")
						+ "') >"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "' id='vehicleid' value='"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'");

				// ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(alertresult.getVtsDatamodel().get(i)
						.getSTARTBUSSTOPNAME());
				String shift = "";
				switch (Integer.parseInt(alertresult.getVtsDatamodel().get(i)
						.getDutyId())) {
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
				ja.add(shift);
				ja.add(alertresult.getVtsDatamodel().get(i)
						.getScheduleArrival());
				ja.add(alertresult.getVtsDatamodel().get(i).getTIMEDIFF());

//				check=dao.checkReasonForSchNotDept(alertresult.getVtsDatamodel().get(i).getVEHICLENO(), alertresult.getVtsDatamodel().get(i).getDEVICEID());
//				System.out.println("check--"+check);
//				if(check.equalsIgnoreCase(null) || check ==""){
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal20'  onclick=javascript:getSchNotDepartedReason('"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()

				+ "','"
				+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

				+ "','"
				+ alertresult.getVtsDatamodel().get(i).getSCHEDULENAME().replace(" ", "@")
				+ "','"
	            +ticketDate.replace(" ", "@")
				+ "') title='Alert Details' id='alert_details"
				+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
				+ "' value='"
				+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
				+ "'>"
				+"Enter Reason"+ "</a>");
//				}else{
//					ja.add(check);
//				}
				
				array.add(ja);
				count++;
				// }
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getDataForSkipStopData(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		int totalCount = 0;

		try {
			Session session = HibernateUtil.getSession("");
			// rajesh changes for access
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			

			// end

			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse3 alertresult = port
					.webGetSkippedStop(packet_code, id, fromDate, tillDate,
							rbKey);
			JSONArray array = new JSONArray();

			Common common = new Common();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);

			
				Date ticketDate1 = new Date();
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				ticketDate = sm.format(ticketDate1);
			
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getOrgName());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERTOKENNO());

				String shift = "";
				switch (Integer.parseInt(alertresult.getVtsDatamodel().get(i)
						.getDutyId())) {
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
				ja.add(shift);
				// ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleSkipStopData('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "','"+Integer.parseInt(alertresult.getVtsDatamodel().get(i)
								.getDutyId())+"') title='Alert Details' id='alert_details"
						+ alertresult.getVtsDatamodel().get(i).getPACKETCODE()
						+ "' value='"
						+ alertresult.getVtsDatamodel().get(i).getPACKETCODE()
						+ "'>"
						+ alertresult.getVtsDatamodel().get(i).getTotalcount()
						
						+ "</a>");
				
			/*	ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal17'  onclick=javascript:getSkipStopReasonData('"
						+ alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()

				+ "','"
				+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

				+ "','"
//				+ alertresult.getVtsDatamodel().get(i).getSCHEDULENAME().replace(" ", "@")
//				+ "','"
	            +ticketDate.replace(" ", "@")
				+ "') title='Alert Details' id='alert_details"
				+ alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()
				+ "' value='"
				+ alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()
				+ "'>"
				+"Enter Reason"+ "</a>");*/
				
				array.add(ja);
			}
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getDataForSkipStopDataNew(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		try {
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse3 alertresult = port
					.webGetSkippedStop(packet_code, "42", "", "", rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				// ja.add(i + 1);
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleSkipStopData('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "') title='Alert Details' id='alert_details"
						+ alertresult.getVtsDatamodel().get(i).getPACKETCODE()
						+ "' value='"
						+ alertresult.getVtsDatamodel().get(i).getPACKETCODE()
						+ "'>"
						+ alertresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER() + "</a>");
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				// ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERTOKENNO());
				// String shift = "";
				// switch (Integer.parseInt(alertresult.getVtsDatamodel().get(i)
				// .getDutyId())) {
				// case 1:
				// shift = "General Shift";
				// break;
				// case 2:
				// shift = "Day 1";
				// break;
				// case 3:
				// shift = "Day 2";
				// break;
				// case 4:
				// shift = "Shift 1";
				// break;
				// case 5:
				// shift = "Shift 2";
				// break;
				// case 6:
				// shift = "Day 1 old";
				// break;
				// case 7:
				// shift = "Day 2 old";
				// break;
				// case 78:
				// shift = "Night Service";
				// break;
				// case 79:
				// shift = "Shift 1";
				// break;
				// case 80:
				// shift = "Shift 2";
				// break;
				// }
				// ja.add(shift);
				ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				array.add(ja);
			}
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getDataForSosAlert(String packet_code,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		String check="";
		getReasonListDao dao=new getReasonListDao();
		try {

			// rajesh changes for access
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
//			System.out.println("orgtypeid..........." + orgtypeid
//					+ "orgchartid................." + orgchartid);
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
//			System.out.println("id...." + id);

			// end
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse5 alertresult = port
					.webGetSosAlertData(packet_code, "", "", id, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				String ar[] = alertresult.getVtsDatamodel().get(i)
						.getCREATEDDATE().split(" ");
//				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1' title='Get SOS Count' onclick=javascript:getTotalSosCount('"
//						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
//						+ "','"
//						+ ar[0]
//						+ "')>"
//						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
//						+ "</a>"
//						+ "<input type='hidden' name='vehiclesos' id='vehiclesos_"
//						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
//						+ "'"
//						+ " value"
//						+ "='"
//						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
//						+ "'>");
				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getOrgName());
				ja.add(alertresult.getVtsDatamodel().get(i).getSIMNUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				//ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1' title='Get SOS Count' onclick=javascript:getTotalSosCount('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ ar[0]
						+ "')>"
						+ alertresult.getVtsDatamodel().get(i).getTotalcount()
						+ "</a>"
						+ "<input type='hidden' name='vehiclesos' id='vehiclesos_"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'"
						+ " value"
						+ "='"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'>");
				ja.add("<a data-toggle='modal'  class='btn green' role='button'  href='#mymodal4'  onclick=javascript:insertDataIntoVtsSos('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID() 
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getCELLID()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getISTDATE().replace(" ", ",")
						+ "')"
							+	">"
						+ "Call"
						+ "</a>");
				
				//ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERTOKENNO());
				ja.add(alertresult.getVtsDatamodel().get(i).getCONDTOKENNO());
				ja.add(alertresult.getVtsDatamodel().get(i).getSIMNUMBER());
//				ja.add("");
				
//				check=dao.checkReasonForSOS(alertresult.getVtsDatamodel().get(i).getVEHICLENO(),alertresult.getVtsDatamodel().get(i).getDEVICEID());
//				System.out.println("check--"+check);
//				if(check.equalsIgnoreCase(null) || check ==""){
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal18'  onclick=javascript:getSOSReasonData('"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()

				+ "','"
				+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

				+ "','"
	            +alertresult.getVtsDatamodel().get(i).getDRIVERTOKENNO()
				+ "') title='Alert Details' id='alert_details"
				+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
				+ "' value='"
				+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
				+ "'>"
				+"Enter Reason"+ "</a>");
//				}else{
//					ja.add(check);
//				}
				
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;

	}

	public String getScheduleName(String deviceId) {
		String schName = "";
		Session session = null;
		try {
			// HibernateUtilVtu h = new HibernateUtilVtu();
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			schName = port.getScheduleName(deviceId, "", rbKey);
			// session = h.getSession();
			// String sql =
			// "select SCHEDULE_NAME from waybill_trip_details where DEVICE_ID='"
			// + deviceId + "' and status='ONLINE' limit 1";
			// Query query = session.createSQLQuery(sql);
			/*
			 * schName = query.uniqueResult() != null ? query.uniqueResult()
			 * .toString() : "Not Mapped";
			 */
		} catch (Exception ex) {

		} finally {
			// HibernateUtilVtu.closeSessionFactory();
		}
		return schName;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getScheduleNameForTracking(String deviceId) {
		JSONObject obj = new JSONObject();
		String schName = "";
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			schName = port.getScheduleName(deviceId, "", rbKey);
		} catch (Exception ex) {

		} finally {
			// HibernateUtilVtu.closeSessionFactory();
		}
		JSONArray array = new JSONArray();
		JSONArray ja = new JSONArray();
		ja.add(schName.replace(" ", ""));
		array.add(ja);
		obj.put("aaData", array);
		return obj;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForChartGridVehicle(String status,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String limit) {
//		System.out.println("@@@@@@@@@@@@@@@@@"+status);
		JSONObject result = new JSONObject();
		String check="";
		getReasonListDao dao=new getReasonListDao();
//		System.out.println("Status====="+status);
		try {

			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "B.depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "B.depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
//			if (status.equals("Active")) {
//				vehicleresult = port.getVehicleDetails(status, "", rbKey, id,
//						limit);
//			} else if (status.equals("Int.Battery")) {
//				// vehicleresult = port.getVehicleDetails(status, "", rbKey);
//			} else 
				if(status.equals("CWS")){
				vehicleresult = port.getVehicleDetails(status, "", rbKey, id,limit);
			}
			 else if(status.equals("DWS")){
					vehicleresult = port.getVehicleDetails(status, "", rbKey, id,
							limit);
				}
//			 else if(status.equals("Scrap")){
//					vehicleresult = port.getVehicleDetails(status, "", rbKey, id,
//							limit);
//				}
			else {
				
				vehicleresult = port.getVehicleDetails(status, "", rbKey, id,
						limit);
			}

			if (!status.equals("Int.Battery")) {
//				System.out.println("33333");
				for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
					JSONArray ja = new JSONArray();
					ja.add(i + 1);
					String device_id = vehicleresult.getVtsDatamodel().get(i)
							.getDEVICEID() != null ? vehicleresult
							.getVtsDatamodel().get(i).getDEVICEID() : "";
					String signalStrength = vehicleresult.getVtsDatamodel()
							.get(i).getSIGNALSTRENGTH() != null ? vehicleresult
							.getVtsDatamodel().get(i).getSIGNALSTRENGTH() : "0";

					if (!device_id.equals("")) {

						if (status.equals("Active")) {
//							System.out.println("1");
							ja.add("<img src='assets/images/bus-map-icon.png'  title='TrackOnline' onclick=javascript:initializea('"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getDEVICEID()
									+ "','"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getLICENCENUMBER()
									+ "','"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getScheduleNumber()
											.replace(" ", "")
									+ "','"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getOrgName().replace(" ", "")
									+ "','"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getIGNSTATUS()
									+ "') >"
									+ ""
									+ "<input type='hidden' name='device_id_"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getDEVICEID()

									+ "' id='deviceid' value='"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getDEVICEID()
									+ "'"
									+ ""
									+ "<input type='hidden' name='vehicle_no_'"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getLICENCENUMBER()
									+ "' id='vehicleid' value='"
									+ vehicleresult.getVtsDatamodel().get(i)
											.getLICENCENUMBER() + "'");
						} else {
							ja.add("<img src='assets/images/Bus-Icon.png'>");
						}

						ja.add(vehicleresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER());

					} else {
						ja.add(vehicleresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER());
					}
					ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID() != null ? vehicleresult
							.getVtsDatamodel().get(i).getDEVICEID()
							: "");
					

					switch(vehicleresult.getVtsDatamodel().get(i).getVendorId()){
					case 1:
						ja.add("EFKON INDIA");
						break;
					case 2:
						ja.add("AIRTEL");
						break;
					case 6:
						ja.add("iTRIANGLE");
						break;
					case 9:
						ja.add("hanover");
						break;
					case 10:
						ja.add("Volvo");
						break;
						default :
							ja.add("");
					}

					ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName() != null ? vehicleresult
							.getVtsDatamodel().get(i).getOrgName()
							: "NA");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getSPEEDKMPH() != null ? vehicleresult
							.getVtsDatamodel().get(i).getSPEEDKMPH() : "0");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getScheduleNumber() != null ? vehicleresult
							.getVtsDatamodel().get(i).getScheduleNumber() : "0");
					String shift = "";
					switch (Integer.parseInt(vehicleresult.getVtsDatamodel()
							.get(i).getDutyId() != null ? vehicleresult
							.getVtsDatamodel().get(i).getDutyId() : "0")) {
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
					ja.add(shift);
					double distance = Double
							.parseDouble(vehicleresult.getVtsDatamodel().get(i)
									.getACCDISTANCE() != null ? vehicleresult
									.getVtsDatamodel().get(i).getACCDISTANCE()
									: "0") / 1000;
					ja.add(Math.round(distance));
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getINTERNALBATTERYVTG() != null ? vehicleresult
							.getVtsDatamodel().get(i).getINTERNALBATTERYVTG()
							: "");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getEXTBATTERYVOLTAGE() != null ? vehicleresult
							.getVtsDatamodel().get(i).getEXTBATTERYVOLTAGE()
							: "");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getIGNSTATUS());
					
				
					if (!signalStrength.startsWith("N")) {
						// signalStrength=Math.round(Double.parseDouble(signalStrength));
						ja.add(Math.round(Double.parseDouble(signalStrength)));
					} else {
						ja.add(signalStrength);
					}
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getISTDATE());
//					ja.add(vehicleresult.getVtsDatamodel().get(i)
//							.getFIRMWAREVERSION());
					
					
					if(!(status.equalsIgnoreCase("NRD") || 	status.equalsIgnoreCase("Not reported")) ){
						System.out.println("nrd status");
						ja.add("");
					}else{
						ja.add(vehicleresult.getVtsDatamodel().get(i).getReason());

//						if(vehicleresult.getVtsDatamodel().get(i).getReason()==null || vehicleresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("") || vehicleresult.getVtsDatamodel().get(i).getReason().equalsIgnoreCase("null")  ){
////							System.out.println("if--"+vehicleresult.getVtsDatamodel().get(i).getReason());
//					ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal21'  onclick=javascript:getNRDReasonData('"
//							+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
//							+ "','"
//							+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
//						+ "') title='Alert Details' id='alert_details"
//						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
//						+ "' value='"
//						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
//						+ "'>"
//						+"Enter Reason"+ "</a>");
//						}else{
//							ja.add(vehicleresult.getVtsDatamodel().get(i).getReason());
////							System.out.println("else ");
//						}
					}
					array.add(ja);

				}
			} else {
				
				vehicleresult = port.getVehicleDetails(status, "", rbKey, id,
						limit);
				for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
					JSONArray ja = new JSONArray();
					ja.add(i + 1);
					String schno = vehicleresult.getVtsDatamodel().get(i)
							.getSCHEDULENAME() != null ? vehicleresult
							.getVtsDatamodel().get(i).getSCHEDULENAME() : "";
					String signalStrength = vehicleresult.getVtsDatamodel()
							.get(i).getSIGNALSTRENGTH() != null ? vehicleresult
							.getVtsDatamodel().get(i).getSIGNALSTRENGTH() : "0";
					ja.add("<img src='assets/images/bus-map-icon.png'  title='TrackOnline' onclick=javascript:initializea('"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "','"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getLICENCENUMBER()
							+ "','"
							+ schno.replace(" ", "")
							+ "','"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getOrgName().replace(" ", "")
							+ "','"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getIGNSTATUS()
							+ "') >"
							+ ""
							+ "<input type='hidden' name='device_id_"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getDEVICEID()

							+ "' id='deviceid' value='"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "'"
							+ ""
							+ "<input type='hidden' name='vehicle_no_'"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getLICENCENUMBER()
							+ "' id='vehicleid' value='"
							+ vehicleresult.getVtsDatamodel().get(i)
									.getLICENCENUMBER() + "'");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getLICENCENUMBER());
					ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID() != null ? vehicleresult
							.getVtsDatamodel().get(i).getDEVICEID()
							: "");

					switch(vehicleresult.getVtsDatamodel().get(i).getVendorId()){
					case 1:
						ja.add("EFKON");
						break;
					case 2:
						ja.add("AIRTEL");
						break;
					case 6:
						ja.add("iTRIANGLE");
						break;
					case 9:
						ja.add("hanover");
						break;
					case 10:
						ja.add("Volvo");
						break;
						default :
							ja.add("");
					}

					ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName() != null ? vehicleresult
							.getVtsDatamodel().get(i).getOrgName()
							: "NA");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getSPEEDKMPH() != null ? vehicleresult
							.getVtsDatamodel().get(i).getSPEEDKMPH() : "0");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getSCHEDULENAME() != null ? vehicleresult
							.getVtsDatamodel().get(i).getSCHEDULENAME() : "0");
					String shift = "";
					switch (Integer.parseInt(vehicleresult.getVtsDatamodel()
							.get(i).getDutyId() != null ? vehicleresult
							.getVtsDatamodel().get(i).getDutyId() : "0")) {
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
					ja.add(shift);
					double distance = Double
							.parseDouble(vehicleresult.getVtsDatamodel().get(i)
									.getACCDISTANCE() != null ? vehicleresult
									.getVtsDatamodel().get(i).getACCDISTANCE()
									: "0") / 1000;
					ja.add(Math.round(distance));
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getINTERNALBATTERYVTG() != null ? vehicleresult
							.getVtsDatamodel().get(i).getINTERNALBATTERYVTG()
							: "");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getEXTBATTERYVOLTAGE() != null ? vehicleresult
							.getVtsDatamodel().get(i).getEXTBATTERYVOLTAGE()
							: "");
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getIGNSTATUS());
					
					if (!signalStrength.startsWith("N")) {
						// signalStrength=Math.round(Double.parseDouble(signalStrength));
						ja.add(Math.round(Double.parseDouble(signalStrength)));
					} else {
						ja.add(signalStrength);
					}
					ja.add(vehicleresult.getVtsDatamodel().get(i)
							.getCREATEDDATE());
//					ja.add(vehicleresult.getVtsDatamodel().get(i)
//							.getFIRMWAREVERSION());
					ja.add("");
					array.add(ja);
				}
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List GetActiveVehicles() {
		List list = null;
		Session session3 = null;
		try {
			String sql = "select license_number,org.org_name org_name "
					+ "	from vehicle veh inner join org_chart org on veh.org_chart_id=org.org_chart_id where status='active'"
					+ "	and veh.deleted_status=0 order by license_number";
			session3 = HibernateUtil.getSession("");
			Query query = session3.createSQLQuery(sql)
					.addScalar("license_number", Hibernate.STRING)
					.addScalar("org_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			list = new ArrayList();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("license_number"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session3 != null) {
				session3.close();
			}
		}
		return list;
	}

	public Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
		try {
			System.out.println("div 2");
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	public Map<Integer, String> getDivision(String orgtypeid,String id) {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql="select org_chart_id,org_name from org_chart  where  "+id+" and deleted_status=0 order by org_name";
			
//		Query query = session
//				.createQuery("select org_chart_id,org_name from org_chart  where org_type_id="+orgtypeid+" and "+id+" and deleted_status=0 order by org_name");
		Query query = HibernateUtil.getSession("").createSQLQuery(sql).addScalar("org_chart_id",Hibernate.STRING)
				.addScalar("org_name", Hibernate.STRING);;
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<Integer, String>> list = query.list();
			for (int i = 0; i < list.size(); i++) {
//				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
				resultMap.put(Integer.parseInt(list.get(i).get("org_chart_id")), list.get(i).get("org_name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	public Map<Integer, String> getDivisionName(String orgtype_id,
			String orgchart_id, int parentId) {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from OrganisationChart orgchart  "
				+ "where org_chart_id =" + parentId
				+ " and deleted_status=0 order by orgchart.org_name");
		try {
			System.out.println("in div 1");
			List<OrganisationChart> list = query.list();
			resultMap.put(0,"---Select---");
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	public Map<Integer, String> getDivisionNameDC(String orgtype_id,
			String orgchart_id, int parentId) {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from OrganisationChart orgchart  "
				+ "where org_chart_id =" + orgchart_id
				+ " and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			resultMap.put(0,"---Select---");
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	public Map<Integer, String> getScheduleName() throws Exception {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		/*Session session = new HibernateUtilVtu().getSession("");
		Query query = session
				.createSQLQuery("");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();

		try {
			List<OrganisationChart> list = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer
						.parseInt(rs.get("SCHEDULE_NO").toString()),
						rs.get("SCHEDULE_NAME").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtilVtu.closeSessionFactory();
		}*/
		return resultMap;
	}

	public Map<String, String> getRouteName() throws Exception {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		Session session = HibernateUtil.getSession("");
		Query query = session
				.createSQLQuery("select concat(route_id,'-',start_point_id, '-', end_point_id) as route_id,"
						+ "	concat(route_number,'(',route_direction,')') as route_name,start_point_id,end_point_id "
						+ "	from route where deleted_status=0 AND CURRENT_DATE BETWEEN effective_from AND if(effective_till='0000-00-00 00-00-00', now(), effective_till)");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		try {
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put((rs.get("route_id").toString()),
						rs.get("route_name").toString());// "<input type='hidden' id='stpoint_"+rs.get("route_id").toString()+"' value='"+rs.get("start_point_id")+"' >"
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultMap;
	}

	@SuppressWarnings("rawtypes")
	public List getDepotId(int parentID) {
		List list = new ArrayList();
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getDepotId(int parentID, String orgchart_id) {
		List list = new ArrayList();
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_chart_id="
				+ orgchart_id
				+ " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getDepotIdDC(int parentID, String orgchart_id) {
		List list = new ArrayList();
		
		String qry = "select org_chart_id from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3 order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_chart_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getRouteId(int parentID) {
		List list = new ArrayList();
		Session session = HibernateUtil.getSession("");
		Query query = session
				.createSQLQuery("select concat(route_id,'-',start_point_id, '-', end_point_id) as route_id,"
						+ "	concat(route_number,'(',route_direction,')') as route_name,start_point_id,end_point_id "
						+ "	from route where deleted_status=0 group by route_id order by route_id ");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("route_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotName(int parentID) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID + " and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotName(int parentID, String org_chart_id) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_chart_id='"
				+ org_chart_id
				+ "' and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDepotNameDC(int parentID, String org_chart_id) {
		List list = new ArrayList();
		String qry = "select org_name from org_chart where deleted_status=0 and parent_id= "
				+ parentID
				+ " and org_type_id=3  order by org_name";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("org_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getRouteName(int parentID) {
		List list = new ArrayList();
		Session session = HibernateUtil.getSession("");
		Query query = session
				.createSQLQuery("select concat(route_id,'-',start_point_id, '-', end_point_id) as route_id,"
						+ "	concat(route_number,'(',route_direction,')') as route_name,start_point_id,end_point_id "
						+ "	from route where deleted_status=0 group by route_id order by route_id");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("route_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScheduleID(int parentID, String date) {
		List list = new ArrayList();
		// Session session = null;
		try {
			// session = new HibernateUtilVtu().getSession();

			// Query query = session
			// .createSQLQuery("select SCHEDULE_NO from waybill_trip_details where STATUS ='ONLINE' group by SCHEDULE_NO order by SCHEDULE_NO");
			// query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			// List<Map<String, Object>> aliasToValueMapList = query.list();
			// if (aliasToValueMapList.size() > 0) {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse scheduleno = port
					.webGetScheduleID(String.valueOf(parentID), date, rbKey);
			for (int i = 0; i < scheduleno.getVtsDatamodel().size(); i++) {

				list.add(scheduleno.getVtsDatamodel().get(i).getSCHEDULENO() != null ? scheduleno
						.getVtsDatamodel().get(i).getSCHEDULENO()
						: "");
			}
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		// HibernateUtil.closeSession();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<WaybillDetails> getScheduleNameTrip(int parentID, String date) {
		List<WaybillDetails> list = new ArrayList();
		// Session session = null;
		try {
			// session = new HibernateUtilVtu().getSession();

			// Query query = session
			// .createSQLQuery("select SCHEDULE_NO,concat(SCHEDULE_NAME,'(',VEHICLE_NO,')') as SCHEDULE_NAME  from waybill_trip_details where STATUS ='ONLINE' group by SCHEDULE_NO order by SCHEDULE_NO");
			// query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			// List<Map<String, Object>> aliasToValueMapList = query.list();
			// if (aliasToValueMapList.size() > 0) {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse schedulename = port
					.webGetScheduleName(String.valueOf(parentID), date, rbKey);
			for (int i = 0; i < schedulename.getVtsDatamodel().size(); i++) {
				WaybillDetails details=new WaybillDetails();
				details.setSchedule_no(Integer.parseInt(schedulename.getVtsDatamodel().get(i).getSCHEDULENO()));
				details.setSchedule_name(schedulename.getVtsDatamodel().get(i).getSCHEDULENAME());
				list.add(details);
//				// Map<String, Object> rs = aliasToValueMapList.get(i);
//				list.add(schedulename.getVtsDatamodel().get(i)
//						.getSCHEDULENAME() != null ? schedulename
//						.getVtsDatamodel().get(i).getSCHEDULENAME() : "");
			}

			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getScheduleName(int parentID, String date) {
		List list = new ArrayList();
		// Session session = null;
		try {
			// session = new HibernateUtilVtu().getSession();

			// Query query = session
			// .createSQLQuery("select SCHEDULE_NO,concat(SCHEDULE_NAME,'(',VEHICLE_NO,')') as SCHEDULE_NAME  from waybill_trip_details where STATUS ='ONLINE' group by SCHEDULE_NO order by SCHEDULE_NO");
			// query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			// List<Map<String, Object>> aliasToValueMapList = query.list();
			// if (aliasToValueMapList.size() > 0) {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse schedulename = port
					.webGetScheduleName(String.valueOf(parentID), date, rbKey);
			for (int i = 0; i < schedulename.getVtsDatamodel().size(); i++) {
				// Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(schedulename.getVtsDatamodel().get(i)
						.getSCHEDULENAME() != null ? schedulename
						.getVtsDatamodel().get(i).getSCHEDULENAME() : "");
			}

			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return list;
	}

	public List getVehicleID(int depotID) {
		List list = new ArrayList();
		String qry = "select vehicle_id,license_number from vehicle where deleted_status=0 and org_chart_id= "
				+ depotID + " group by vehicle_id   order by vehicle_id";
		try {
//			qry = "select v.vehicle_id,device_serial_number,v.license_number"
//					+
//					// " ,d.device_id,if(gs.LAT is null,'',gs.LAT) LAT,if(gs.LNG is null,'',gs.LNG) LNG,if(gs.RADIUS is null,'',gs.RADIUS) RADIUS "
//					"	from vehicle v inner join vehicle_device vd on vd.vehicle_id = v.vehicle_id"
//					+ "	inner join device d on d.device_id = vd.device_id "
//					+
//					// "  LEFT JOIN geofence_storage gs on gs.ORG_ID=" + depotID
//					"	where v.deleted_status=0 and org_chart_id= " + depotID
//					+ "	and vd.status = 'active' "
//					+ "	and d.deleted_status = 0 " + "	group by v.vehicle_id "
//					+ "	order by v.license_number " + "";
			qry = "SELECT  license_number,device_serial_number FROM its.vehicle_device A INNER JOIN its.vehicle B     ON A.vehicle_id = B.vehicle_id  "
                   + " INNER JOIN its.device C  ON C.device_id = A.device_id INNER JOIN  its.sim_vtu D ON D.device_id=A.device_id "  
                   +" INNER JOIN its.simcard E ON E.simcard_id=D.sim_id   INNER JOIN org_chart org on org.org_chart_id=B.org_chart_id and "
                   +" E.deleted_status=0 AND C.deleted_status=0  AND B.deleted_status=0 AND B.cause_status !='s' "
                   +" and B.org_chart_id ="+ depotID+ " " 
                  + " and A.status='ACTIVE' and D.status='ACTIVE' and C.status='ACTIVE'  and B.status='ACTIVE' group by device_serial_number order by B.license_number "  ; 
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
//			System.out.println("size =="+aliasToValueMapList.size());
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("device_serial_number").toString() + "#"
							+ rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}
	
	public Map<Integer, String> getVehicledetails(int depotID) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		String qry = "select vehicle_id,license_number from vehicle where deleted_status=0 and org_chart_id!= "
				+ depotID + " group by vehicle_id   order by vehicle_id";
		try {
			qry = "select v.vehicle_id,device_serial_number,v.license_number"
					+
					// " ,d.device_id,if(gs.LAT is null,'',gs.LAT) LAT,if(gs.LNG is null,'',gs.LNG) LNG,if(gs.RADIUS is null,'',gs.RADIUS) RADIUS "
					"	from vehicle v inner join vehicle_device vd on vd.vehicle_id = v.vehicle_id"
					+ "	inner join device d on d.device_id = vd.device_id "
					+
					// "  LEFT JOIN geofence_storage gs on gs.ORG_ID=" + depotID
					"	where v.deleted_status=0 and org_chart_id!= " + depotID
					+ "	and vd.status = 'active' "
					+ "	and d.deleted_status = 0 " + "	group by v.vehicle_id "
					+ "	order by v.license_number " + "";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					try{
					//map.put(Integer.parseInt(rs.get("device_serial_number").toString()), rs.get("license_number").toString());
					}catch(Exception ex){
						
					}
					//list.add(rs.get("device_serial_number").toString() + "#"
						//	+ rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return map;
		}

	}

	public List getVehicleId(int depotID) {
		List list = new ArrayList();
		String qry = "select vehicle_id,license_number from vehicle where deleted_status=0 and org_chart_id= "
				+ depotID + " and cause_status !='s' group by vehicle_id   order by vehicle_id";
		com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
		com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
		try {
			qry = "select v.vehicle_id,device_serial_number,v.license_number"
					+ " ,d.device_id,if(gs.LAT is null,'',gs.LAT) LAT,if(gs.LNG is null,'',gs.LNG) LNG,if(gs.RADIUS is null,'',gs.RADIUS) RADIUS,phone_number "
					+ "	from vehicle v inner join vehicle_device vd on vd.vehicle_id = v.vehicle_id"
					+ "	inner join device d on d.device_id = vd.device_id "
					+ " inner join sim_vtu sv on vd.device_id=sv.device_id and sv.STATUS='ACTIVE' "
					+ " inner join simcard sc on sv.sim_id=sc.simcard_id and sc.STATUS='ACTIVE' "
					+ "  LEFT JOIN geofence_storage gs on gs.ORG_ID=" + depotID
					+ "	where v.deleted_status=0 and cause_status not in('s')  and org_chart_id= " + depotID
					+ "	and vd.status = 'active' "
					+ "	and d.deleted_status = 0 " + "	group by v.vehicle_id "
					+ "	order by v.license_number " + "";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			String[] otaStatus = new String[4];
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					// Checking OTA Status of EACH Device

					Map<String, Object> rs = aliasToValueMapList.get(i);
					VtsResponse5 response = port.pollSms(
							rs.get("device_serial_number").toString(), "1");
					try{
					list.add(rs.get("device_serial_number").toString()
							+ "@#@"
							+ rs.get("license_number").toString()
							+ "@#@"
							+ rs.get("device_id").toString()
							+ "@#@"
							+ rs.get("LAT").toString()
							+ "@#@"
							+ rs.get("LNG").toString()
							+ "@#@"
							+ rs.get("RADIUS").toString()
							+ "@#@"
							+ rs.get("phone_number").toString()
							+ "@#@"
							+ response.getVtsDatamodel().get(0)
									.getVERSIONSTATUS()
							+ "@#@"
							+ response.getVtsDatamodel().get(0)
									.getGEOFENCESTATUS()
							+ "@#@"
							+ response.getVtsDatamodel().get(0)
									.getACCLIMITSTATUS()
							+ "@#@"
							+ response.getVtsDatamodel().get(0)
									.getDEACCLIMITSTATUS()
									+ "@#@"
									+ response.getVtsDatamodel().get(0)
											.getGROUPDATA()
											+ "@#@"
											+ response.getVtsDatamodel().get(0)
													.getCOMMAND()
													+ "@#@"
													+ response.getVtsDatamodel().get(0)
															.getREPLYEXISTS()
															+ "@#@"
															+ response.getVtsDatamodel().get(0)
																	.getCOMMANDEXECUTIONTIME()
																	+ "@#@"
																	+ response.getVtsDatamodel().get(0)
																			.getCOMMANDEXECUTIONSTATUS()
																			+ "@#@"
																			+ response.getVtsDatamodel().get(0)
																					.getRESPONSE());
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}

	@SuppressWarnings("rawtypes")
	public List getDeviceId(int depotID) {
		List list = new ArrayList();
		String qry = "select device_id from vehicle_device where status='ACTIVE' and vehicle_id= "
				+ depotID + " group by device_id   order by device_id";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
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


	// Vehicle Data For PieChart through its database Dated 20-12-2017...
//	@SuppressWarnings("unchecked")
//	public JSONObject getDataForChartVehicle(int total,
//			HttpServletRequest request, String cols, String dir,
//			String fromDate, String tillDate, String orgTypeId,
//			String orgChartId) {
//		JSONObject result = new JSONObject();
//		Session session= null;
//		try {
//
//			String orgtypeid = (String) request.getSession().getAttribute(
//					"orgtypeid");
//			String orgchartid = (String) request.getSession().getAttribute(
//					"orgchartid");
////			System.out.println("orgtypeid..........." + orgtypeid
////					+ "orgchartid................." + orgchartid);
//			String id = "!=0";
//			String orgId= "!=0";
//			if (orgtypeid.equals("1")) {
//				id = "B.depot_id!=0";
//				orgId ="B.org_chart_id!=0";
//
//			}
//			if (orgtypeid.equals("3")) {
//
//				id = "B.depot_id in('" + orgchartid + "')";
//				orgId = "B.org_chart_id in('" + orgchartid + "')";
//			}
//			if (orgtypeid.equals("2")) {
//
//				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
//						+ orgchartid + "' and deleted_status =0 AND org_type_id =3)";
//				orgId = "B.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
//						+ orgchartid + " ' and deleted_status =0 AND org_type_id =3)";
//			}
//	
//			System.out.println("id="+id+"org id  "+orgId);
//			int count[] = new int[7];
//			String status[] = new String[7];
//
//			JSONArray array = new JSONArray();
//			// Implementing WebService For Vehicle Count.... dated 02-09-2014
////			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
////			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
////			model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
////					.getVehicleCount(rbKey, id);
//
//			int totalCount = 0;
//			int totalActive =0;
//			int totalNRD=0;
//			int totalInt=0;
//			int totalCWS=0;
//			int totalDWS=0;
////			for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {
//
//				
//				session= HibernateUtil.getSession("hibernate.cfg.xml");
//				Query qryy = session.createSQLQuery("SELECT  count(*) total FROM its.vehicle_device A INNER JOIN its.vehicle B    " +
//						" ON A.vehicle_id = B.vehicle_id  INNER JOIN its.device C  ON C.device_id = A.device_id INNER JOIN " +
//						" its.sim_vtu D ON D.device_id=A.device_id  INNER JOIN its.simcard E ON E.simcard_id=D.sim_id  " +
//						" INNER JOIN org_chart org on org.org_chart_id=B.org_chart_id and E.deleted_status=0 AND C.deleted_status=0 " +
//						" AND B.deleted_status=0 AND B.cause_status !='s' and "+orgId+" and A.status='ACTIVE' and D.status='ACTIVE' and C.status='ACTIVE' " +
//						" and B.status='ACTIVE' group by C.DEVICE_ID").addScalar("total");
////				System.out.println("qryyyyy"+qryy);
//				
//				qryy.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String, Object>> aliasToValueMapList = qryy.list();
////				for (int l = 0; l < aliasToValueMapList.size(); l++) {
////					Map<String, Object> rs = aliasToValueMapList.get(i);
//					totalCount =Integer.valueOf(aliasToValueMapList.size());
////				}
//					
//					Query qryyActive = session.createSQLQuery("select count(*) totalActive from summaryDashboard_activeVehicle B  " +
//					" left join device vd   on B.DEVICE_ID=vd.device_serial_number left join vendor v " +
//					" on vd.vendor_id=v.vendor_id" +
//					" left join shift_type st on B.duty_type_id=st.shift_type_id where "+id+" group by B.DEVICE_ID").addScalar("totalActive");
////					System.out.println("qryyyyy"+qryyActive);
//			
//					
//					qryyActive.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//					List<Map<String, Object>> aliasToValueMapList1 = qryyActive.list();
//						totalActive =Integer.valueOf(aliasToValueMapList1.size());
//					
//					Query qryyNRD = session.createSQLQuery("select count(*) totalNRD from summaryDashboard_NRDvehile B  " +
//					" inner join device vd   on B.DEVICE_ID=vd.device_serial_number inner join vehicle veh on B.lICENCE_NUMBER=veh.license_number " +
//					" left join vendor v  on vd.vendor_id=v.vendor_id where "+id+" and B.lICENCE_NUMBER not in (select license_number from vehicle where cause_status in ('CW','DW','S') ) group by B.DEVICE_ID  ").addScalar("totalNRD");
////							System.out.println("qryyyyy"+qryyNRD);
//							
//							qryyNRD.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//							List<Map<String, Object>> aliasToValueMapList2 = qryyNRD.list();
//								totalNRD =Integer.valueOf(aliasToValueMapList2.size());
//				
//				Query qryInt = session.createSQLQuery("select count(*) totalInt from summaryDashboard_intenalBattery B   " +
//					" left join device vd   on B.DEVICE_ID=vd.device_serial_number left join vendor v on vd.vendor_id=v.vendor_id" +
//					" left join shift_type st on B.duty_type_id=st.shift_type_id " +
//					" where "+id+" group by B.DEVICE_ID ").addScalar("totalInt");
////							System.out.println("qryyyyy"+qryInt);
//								
//								
//					qryInt.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//					List<Map<String, Object>> aliasToValueMapList3 = qryInt.list();
//					totalInt =Integer.valueOf(aliasToValueMapList3.size());
//					
//					
//				Query qryCWS = session.createSQLQuery(
//								"select count(*) totalCWS from summaryDashboard_intenalBattery B   "
//										+ " left join device vd   on B.DEVICE_ID=vd.device_serial_number left join vendor v on vd.vendor_id=v.vendor_id"
//										+ " left join shift_type st on B.duty_type_id=st.shift_type_id "
//										+ " where " + id + " group by B.DEVICE_ID ").addScalar(
//								"totalCWS");
////				System.out.println("qryyyyy" + qryCWS);
//
//				qryCWS.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String, Object>> aliasToValueMapList4 = qryCWS.list();
//				totalCWS = Integer.valueOf(aliasToValueMapList4.size());
//
//				Query qryDWS = session
//						.createSQLQuery(
//								"select count(*) totalDWS from summaryDashboard_intenalBattery B   "
//										+ " left join device vd   on B.DEVICE_ID=vd.device_serial_number left join vendor v on vd.vendor_id=v.vendor_id"
//										+ " left join shift_type st on B.duty_type_id=st.shift_type_id "
//										+ " where " + id + " group by B.DEVICE_ID ").addScalar(
//								"totalDWS");
////				System.out.println("qryyyyy" + qryDWS);
//
//				qryDWS.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String, Object>> aliasToValueMapList6 = qryDWS.list();
//				totalDWS = Integer.valueOf(aliasToValueMapList6.size());
//
//				count[0] = totalActive;
//				count[1] = totalNRD;
//				count[2] = totalInt;			
//				count[3] = totalDWS;
//				count[4] = totalCWS;
////				count[5] = vehicleCount.getVtsDatamodel().get(i).getScrapVehicle();
//				count[6] = totalCount;
//				status[0] = "Active";
//				status[1] = "Not reported";
//				status[2] = "Int.Battery";
//				status[3] = "CWS";
//				status[4] = "DWS";
////				status[5] = "Scrap";
//				status[6] = "Total Device";
////			}
//
//			for (int i = 0; i < 7; i++) {
//				JSONArray ja = new JSONArray();
//				ja.add(count[i]);
//				double per = Double.parseDouble(String.valueOf(count[i])) * 100
//						/ totalCount;
//				ja.add(per);
//				ja.add(status[i]);
//				array.add(ja);
//			}
//			result.put("aaData", array);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//		}
//		return result;
//	}

	// Vehicle Data For PieChart through WebService Dated 01-09-2014...
	@SuppressWarnings("unchecked")
	public JSONObject getDataForChartVehicle(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String orgTypeId,
			String orgChartId) {
		JSONObject result = new JSONObject();
		Session session= null;
		try {

			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
//			System.out.println("orgtypeid..........." + orgtypeid
//					+ "orgchartid................." + orgchartid);
			String id = "!=0";
			String orgId= "!=0";
			if (orgtypeid.equals("1")) {
				id = "B.depot_id!=0";
				orgId ="B.org_chart_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "B.depot_id in('" + orgchartid + "')";
				orgId = "B.org_chart_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "B.depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "' and deleted_status =0 AND org_type_id =3)";
				orgId = "B.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + " ' and deleted_status =0 AND org_type_id =3)";
			}
	
//			System.out.println("id="+id+"org id  "+orgId);
			int count[] = new int[7];
			String status[] = new String[7];

			JSONArray array = new JSONArray();
			// Implementing WebService For Vehicle Count.... dated 02-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse vehicleCount = port
					.getVehicleCount(rbKey, id);

			int totalCount = 0;
			for (int i = 0; i < vehicleCount.getVtsDatamodel().size(); i++) {

//				System.out.println("cws "+vehicleCount.getVtsDatamodel().get(i).getWorkShopVehicle()
//								+"Dws "+vehicleCount.getVtsDatamodel().get(i).getDWVehicle());
				
				session= HibernateUtil.getSession("hibernate.cfg.xml");
				Query qryy = session.createSQLQuery("SELECT  count(*) total FROM its.vehicle_device A INNER JOIN its.vehicle B    " +
						" ON A.vehicle_id = B.vehicle_id  INNER JOIN its.device C  ON C.device_id = A.device_id INNER JOIN " +
						" its.sim_vtu D ON D.device_id=A.device_id  INNER JOIN its.simcard E ON E.simcard_id=D.sim_id  " +
						" INNER JOIN org_chart org on org.org_chart_id=B.org_chart_id and E.deleted_status=0 AND C.deleted_status=0 " +
						" AND B.deleted_status=0 AND B.cause_status !='s' and "+orgId+" and A.status='ACTIVE' and D.status='ACTIVE' and C.status='ACTIVE' " +
						" and B.status='ACTIVE'").addScalar("total");
//				System.out.println("qryyyyy"+qryy);
				
				qryy.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = qryy.list();
				for (int l = 0; l < aliasToValueMapList.size(); l++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					totalCount =Integer.valueOf(rs.get("total").toString());
				
//				totalCount = qryy.list().size()
//				for (int l = 0; l < qryy.list().size(); i++) {
//					
				}
				
//				System.out.println("total "+totalCount);
//				totalCount = Integer.parseInt(vehicleCount.getVtsDatamodel()
//						.get(i).getActive())
//						+ Integer.parseInt(vehicleCount.getVtsDatamodel()
//								.get(i).getInactive())
//						+ vehicleCount.getVtsDatamodel().get(i)
//								.getONINTBATTERY()
//								+vehicleCount.getVtsDatamodel().get(i).getWorkShopVehicle()
//								+vehicleCount.getVtsDatamodel().get(i).getDWVehicle()
//								+vehicleCount.getVtsDatamodel().get(i).getScrapVehicle();
				count[0] = Integer.parseInt(vehicleCount.getVtsDatamodel()
						.get(i).getActive());
				count[1] = Integer.parseInt(vehicleCount.getVtsDatamodel()
						.get(i).getInactive());
//				count[2] = vehicleCount.getVtsDatamodel().get(i)
//						.getONINTBATTERY();
//				count[3] = vehicleCount.getVtsDatamodel().get(i).getWorkShopVehicle();
//				count[4] = vehicleCount.getVtsDatamodel().get(i).getDWVehicle();
//				count[5] = vehicleCount.getVtsDatamodel().get(i).getScrapVehicle();
				count[2] = vehicleCount.getVtsDatamodel().get(i).getONINTBATTERY();
				
				count[3] = vehicleCount.getVtsDatamodel().get(i).getDWVehicle();
				count[4] = vehicleCount.getVtsDatamodel().get(i)
						.getWorkShopVehicle();
//				count[4] = vehicleCount.getVtsDatamodel().get(i).getONINTBATTERY();
				count[5] = vehicleCount.getVtsDatamodel().get(i).getScrapVehicle();
				
				count[6] = totalCount;
				status[0] = "Active";
				status[1] = "Not reported";
				status[2] = "Int.Battery";
				status[3] = "CWS";
				status[4] = "DWS";
//				status[5] = "Scrap";
				
//				status[3] = "DWS";
//				status[4] = "CWS";
//				status[4] = "Int.Battery";
				status[5] = "Scrap";
				status[6] = "Total Device";
			}

			for (int i = 0; i < 7; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalCount;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataForAlert(int total, HttpServletRequest request,
			String cols, String dir, String fromDate, String tillDate,
			String packet_code, String misc_byte, String deivice_id,
			String licence_number) {
		JSONObject result = new JSONObject();
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse alertresult = port
					.getAlertDetails(packet_code, misc_byte, deivice_id, rbKey);
			JSONArray array = new JSONArray();
			JSONArray vehicleDetails = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				if (packet_code.equalsIgnoreCase("DV")
						&& misc_byte.equalsIgnoreCase("00")) {
					JSONArray ja = new JSONArray();
					ja.add(i + 1);
					ja.add("");
					ja.add("");
					ja.add("");
					ja.add("");
					ja.add("");

					array.add(ja);
				} else {
					model.jaxb.xml.trimax.com.VtsResponse waybillresult = port
							.getWaybillTripDetails(deivice_id, fromDate,
									tillDate, alertresult.getVtsDatamodel()
											.get(i).getMAGNETICVARIATION(),
									rbKey);
					JSONArray ja = new JSONArray();
					ja.add(i + 1);// Serial Number
					ja.add(licence_number); // Vehicle Number
					ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID() != null ? alertresult
							.getVtsDatamodel().get(i).getDEVICEID()
							: "");// Device ID
					ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH() != null ? alertresult
							.getVtsDatamodel().get(i).getSPEEDKMPH()
							: "");
					/* For Bus Stop Skippig */
					if (!(packet_code.equalsIgnoreCase("GF") && (misc_byte
							.equalsIgnoreCase("01")))) {
						if (waybillresult.getVtsDatamodel().size() > 0) {
							if (packet_code.equalsIgnoreCase("EA")) {
								ja.add(waybillresult.getVtsDatamodel().get(0)
										.getTRIPNUMBER() != null ? waybillresult
										.getVtsDatamodel().get(0)
										.getTRIPNUMBER()
										: "NA");
								// ja.add(alertresult.getVtsDatamodel().get(i).getMAGNETICVARIATION());
								ja.add(waybillresult.getVtsDatamodel().get(0)
										.getScheduleArrival() != null ? waybillresult
										.getVtsDatamodel().get(0)
										.getScheduleArrival()
										: "NA");
								ja.add(waybillresult.getVtsDatamodel().get(0)
										.getActualArrival() != null ? waybillresult
										.getVtsDatamodel().get(0)
										.getActualArrival()
										: "NA");
							}
							if (packet_code.equalsIgnoreCase("ED")) {
								ja.add(waybillresult.getVtsDatamodel().get(0)
										.getTRIPNUMBER() != null ? waybillresult
										.getVtsDatamodel().get(0)
										.getTRIPNUMBER()
										: "NA");
								// ja.add(alertresult.getVtsDatamodel().get(i).getMAGNETICVARIATION());
								ja.add(waybillresult.getVtsDatamodel().get(0)
										.getScheduleDeparture() != null ? waybillresult
										.getVtsDatamodel().get(0)
										.getScheduleArrival()
										: "NA");
								ja.add(waybillresult.getVtsDatamodel().get(0)
										.getActualDeparture() != null ? waybillresult
										.getVtsDatamodel().get(0)
										.getActualArrival()
										: "NA");
							}
						} else {
							ja.add("Not Available");
							ja.add("Not Available");
							ja.add("Not Available");
						}
					} else {
						ja.add(getScheduleName(alertresult.getVtsDatamodel()
								.get(i).getDEVICEID()));
					}
					ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE() != null ? alertresult
							.getVtsDatamodel().get(i).getISTDATE()
							: "");
					if (packet_code.equals("GF") && misc_byte.equals("01")) {
						String arr[] = alertresult.getVtsDatamodel().get(i)
								.getVARIATIONSENSE().split("-");
						if (arr.length == 2) {
							String x = getBusStopName(arr[1], arr[0]);
							if (!(x.equalsIgnoreCase(""))) {
								ja.add(x);
								array.add(ja);
							}
						} else {
							ja.add("");
						}
					} else {
						array.add(ja);
					}
				}
			}
			vehicleDetails.add(licence_number);
			// vehicleDetails.add(alertresult.getVtsDatamodel().get(0)
			// .getDEVICEID() != null ? alertresult.getVtsDatamodel()
			// .get(0).getDEVICEID() : "");
			// vehicleDetails.add(getScheduleName(alertresult.getVtsDatamodel()
			// .get(0).getDEVICEID()));
			result.put("vehicleDetails", vehicleDetails);
			result.put("aaData", array);
			result.put("iTotalRecords", alertresult.getVtsDatamodel().size());
			result.put("iTotalDisplayRecords", alertresult.getVtsDatamodel()
					.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

		return result;
	}

	// Alert For Deviation ..
	@SuppressWarnings("unchecked")
	public JSONObject getDataForDeviationAlert(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String packet_code,
			String misc_byte, String deivice_id, String licence_number) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse alertresult = port
					.getAlertDetails(packet_code, misc_byte, deivice_id, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				if (packet_code.equalsIgnoreCase("DV")
						&& misc_byte.equalsIgnoreCase("00")) {
					JSONArray ja = new JSONArray();
					ja.add("<a href='#' title='Track Online' onclick=javascript:drawScheduleRouteDeviation('"
							+ alertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "',"
							+ alertresult.getVtsDatamodel().get(i).getROUTEID()
							+ ","
							+ alertresult.getVtsDatamodel().get(i)
									.getSTARTPOINT()
							+ ","
							+ alertresult.getVtsDatamodel().get(i)
									.getENDPOINT()
							+ ",'"
							+ alertresult.getVtsDatamodel().get(i)
									.getSTARTTIME()
							+ "','"
							+ alertresult.getVtsDatamodel().get(i)
									.getTRIPSTATUS()
							+ ""
							+ "','"
							+ alertresult.getVtsDatamodel().get(i).getDUTYDT()
							+ "',"
							+ alertresult.getVtsDatamodel().get(i).getID()
							+ ",'"
							+ alertresult.getVtsDatamodel().get(i).getENDTIME()
							+ "'"
							+ ",'"
							+ alertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().replace(" ", "")
							+ "'"
							+ ")>"
							+ "<img src='assets/images/bus-map-icon.png'/>"
							+ "</a>");
					if (alertresult.getVtsDatamodel().get(i)
							.getRouteExitGeocode() != null) {
						String geo[] = alertresult.getVtsDatamodel().get(i)
								.getRouteExitGeocode().split(" ");
						double lat = Double.parseDouble(geo[0]);
						double lng = Double.parseDouble(geo[1]);
						ja.add(getStopList(lat, lng, session, relatedPointType));
					}
					if (!alertresult.getVtsDatamodel().get(i)
							.getRouteEntryGeocode().equals("-")) {
						String geo[] = alertresult.getVtsDatamodel().get(i)
								.getRouteEntryGeocode().split(" ");
						double lat = Double.parseDouble(geo[0]);
						double lng = Double.parseDouble(geo[1]);
						ja.add(getStopList(lat, lng, session, relatedPointType));
					} else {
						ja.add("-");
					}

					ja.add(alertresult.getVtsDatamodel().get(i).getTripNumber());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getDeviatedKms());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getRouteExitTm());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getRouteEntryTm());

					array.add(ja);
				}
			}
			result.put("aaData", array);
			result.put("iTotalRecords", alertresult.getVtsDatamodel().size());
			result.put("iTotalDisplayRecords", alertresult.getVtsDatamodel()
					.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForDTamperingAlert(int total,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, String packet_code,
			String misc_byte, String deivice_id, String licence_number) {
		JSONObject result = new JSONObject();
		// Session session = null;
		try {

			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse5 alertresult = port
					.webGetTamperingAlertDetails(packet_code, "", "", "",
							deivice_id, rbKey);
			String relatedPointType = "";
			Session session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				ja.add(getStopList(
						Double.parseDouble(alertresult.getVtsDatamodel().get(i)
								.getLAT()),
						Double.parseDouble(alertresult.getVtsDatamodel().get(i)
								.getLONGITUDE()), session, relatedPointType));
				array.add(ja);

			}
			result.put("aaData", array);
			result.put("iTotalRecords", alertresult.getVtsDatamodel().size());
			result.put("iTotalDisplayRecords", alertresult.getVtsDatamodel()
					.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// HibernateUtil.closeSession();
		}

		return result;
	}

	public String getBusStopName(String busId, String stopSeq) {
		String stopName = "";
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select bus_stop_name,point_type_id from bus_stop where bus_stop_id='"
					+ busId + "' limit 1";
			sql = "SELECT b.bus_stop_name FROM bus_stop b "
					+ "	INNER JOIN route_map r ON (r.end_bus_stop_id = b.bus_stop_id)"
					+ "	WHERE r.route_id = '" + busId + "' "
					+ " AND b.point_type_id not in (2,13)"
					+ "	AND r.bus_stop_order = " + stopSeq;
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				stopName = query.uniqueResult().toString();
			}
		} catch (Exception ex) {

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return stopName;
	}

	public String[] getGeofenceStorage(String device_id) {
		String[] geoString = new String[3];
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "SELECT LAT,LONG,RADIUS FROM geofence_storage WHERE DEVICE_ID = '"
					+ device_id + "'";

			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

			}
		} catch (Exception ex) {

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return geoString;
	}

	public void insertpollSms(String inString, String deviceId)
			throws Exception {
		// TODO Auto-generated method stub
		// Session session = null;
		// HibernateUtilVtu h = new HibernateUtilVtu();
		try {
			
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			port.insertSms(deviceId, inString, "1");
			// session = h.getSession();
			// org.hibernate.Transaction transaction =
			// session.beginTransaction();
			// String sql =
			// "insert into vtu_device_command(DEVICE_ID,COMMAND,REPLY_EXISTS,COMMAND_EXECUTION_TIME,COMMAND_EXECUTION_STATUS) "
			// + " values ('"
			// + deviceId
			// + "','"
			// + inString
			// + "','N',now(),'N')";
			// Query query = session.createSQLQuery(sql);
			// int result = query.executeUpdate();
			// transaction.commit();
		} catch (Exception ex) {

		} finally {
			// HibernateUtilVtu.closeSessionFactory();
		}

	}

	@SuppressWarnings("unchecked")
	public JSONObject getRouteParameters(int route_id,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		int start_point_id = 0;
		int end_point_id = 0;
		int stop_order = 0;
		String lineString = "";
		String path = "";
		int lastInsertID = 0;
		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			session = HibernateUtil.getSession("");
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "select bus_stop_order,rm.start_bus_stop_id,rm.end_bus_stop_id,"
					+ "asText(route_string) as lineString ,rm.path as path,r.route_geofence,r.route_number from route r "
					+ " inner join route_map rm on r.route_id=rm.route_id where 	r.route_id	='"
					+ route_id
					+ "' group by bus_stop_order order by bus_stop_order ";
			Query query = session.createSQLQuery(sql)

			.addScalar("bus_stop_order", Hibernate.STRING)
					.addScalar("start_bus_stop_id", Hibernate.STRING)
					.addScalar("end_bus_stop_id", Hibernate.STRING)
					.addScalar("lineString", Hibernate.STRING)
					.addScalar("path", Hibernate.STRING)
					.addScalar("route_geofence", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(rs.get("bus_stop_order").toString());
				ja.add(rs.get("start_bus_stop_id").toString());
				ja.add(rs.get("end_bus_stop_id").toString());
				ja.add(rs.get("lineString") != null ? rs.get("lineString") : "");
				ja.add(rs.get("path").toString());
				ja.add(rs.get("route_geofence").toString() != null ? rs.get(
						"route_geofence").toString() : "N");
				ja.add(rs.get("route_number").toString());

				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getRouteParameters1(int route_id,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;
		int start_point_id = 0;
		int end_point_id = 0;
		int stop_order = 0;
		String lineString = "";
		String path = "";
		int lastInsertID = 0;
		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			session = HibernateUtil.getSession("");
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "select bus_stop_order,rm.start_bus_stop_id,rm.end_bus_stop_id,"
					+ "asText(route_string) as lineString ,rm.path as path,r.route_geofence,r.route_number from route r "
					+ " inner join route_map rm on r.route_id=rm.route_id where 	r.route_id	='"
					+ route_id
					+ "' group by bus_stop_order order by bus_stop_order ";
			Query query = session.createSQLQuery(sql)

			.addScalar("bus_stop_order", Hibernate.STRING)
					.addScalar("start_bus_stop_id", Hibernate.STRING)
					.addScalar("end_bus_stop_id", Hibernate.STRING)
					.addScalar("lineString", Hibernate.STRING)
					.addScalar("path", Hibernate.STRING)
					.addScalar("route_geofence", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(rs.get("bus_stop_order").toString());
				ja.add(rs.get("start_bus_stop_id").toString());
				ja.add(rs.get("end_bus_stop_id").toString());
				ja.add(rs.get("lineString") != null ? rs.get("lineString") : "");
				ja.add(rs.get("path").toString());
				ja.add(rs.get("route_geofence").toString() != null ? rs.get(
						"route_geofence").toString() : "Y");
				ja.add(rs.get("route_number").toString());

				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	public int insertRouteParameters(int route_id, int start_point_id,
			int end_point_id, String stop_order, String path,
			String commonPath, String routeString, String centerPoint,
			HttpServletRequest request) {
		// Insert into route_geofence.. on VTS Database'
		int lastInsertID = 0;
		Session session = null;
		int userID = Integer.parseInt(request.getSession()
				.getAttribute("userid").toString());

		try {
			// Calling Web Service to Insert Route Fence Data...... Dated
			// 02-09-2014
			session = HibernateUtil.getSession("");
			org.hibernate.Transaction txn = session.beginTransaction();
			String sql2 = "insert into route_geofence (route_id,start_point_id,end_point_id,path,route_string,center_point,created_by,created_date,bus_stop_order,commonpath) "
					+ " values(?,?,?,?,?,?,?,now(),?,?)";
			Query query3 = session.createSQLQuery(sql2);
			query3.setParameter(0, route_id);
			query3.setParameter(1, start_point_id);
			query3.setParameter(2, end_point_id);
			query3.setParameter(3, path);
			query3.setParameter(4, routeString);
			query3.setParameter(5, centerPoint);
			query3.setParameter(6, userID);
			query3.setParameter(7, stop_order);
			query3.setParameter(8, commonPath);
			int updatedresult = query3.executeUpdate();
			lastInsertID = lastInsertId(session);
			txn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return lastInsertID;
	}

	public static int lastInsertId(org.hibernate.Session session) {
		String lastsql = "select LAST_INSERT_ID();";
		Query query = session.createSQLQuery(lastsql);
		List<String[]> listCnt = query.list();
		int lastid = Integer.parseInt(listCnt.get(0) + "");
		return lastid;
	}

	public int updateRouteFence(int route_id) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			// updateRouteFenceString(route_id);
			session = HibernateUtil.getSession("");
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "update route set route_geofence='Y' where route_id="
					+ route_id;
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}

	public int updateRouteFenceString(int route_id) {
		// TODO Auto-generated method stub
		Session session = null;
		String concatsql = "";
		
		try {
			session = HibernateUtil.getSession("");
			String strsql = "select group_concat(route_string) route_string from route_geofence where route_id="
					+ route_id + " ";
			Query querystr = session.createSQLQuery(strsql).addScalar(
					"route_string", Hibernate.STRING);
			querystr.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = querystr.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				concatsql = rs.get("route_string").toString();

			}
			
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "update route_geofence set route_fence_string=GeomFromText('LINESTRING("
					+ concatsql + ")') where route_id=" + route_id + " ";

			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getroutePath(int route_id, int start_id, int end_id) {
		// TODO Auto-generated method stub
		Session session = null;
		JSONObject result = new JSONObject();
		try {
			session = HibernateUtil.getSession("");
			String sql = "select path,b.bus_stop_name, b.latitude_current, b.longitude_current,"
					+ "	b1.bus_stop_name as end_bus_stop_name, b1.latitude_current as end_latitude, "
					+ "	b1.longitude_current as end_longitude,b.point_type_id as point_type_id "
					+ "	from route_map r "
					+ "	INNER JOIN bus_stop b ON b.bus_stop_id = r.start_bus_stop_id "
					+ " INNER JOIN bus_stop b1 ON b1.bus_stop_id = r.end_bus_stop_id"
					+ "	where route_id="
					+ route_id
					+ " and b.point_type_id in(select sys_value from default_system_veriable where sys_key='POINT_TYPE_ID') and bus_stop_order between "
					+ "	(select bus_stop_order from route_map where start_bus_stop_id = "
					+ start_id
					+ " and route_id = r.route_id )"
					+ "	and"
					+ "(select bus_stop_order from route_map where end_bus_stop_id = "
					+ end_id
					+ " and route_id = r.route_id )"
					+ " order by bus_stop_order ";
			Query query = session.createSQLQuery(sql)

			.addScalar("path", Hibernate.STRING)
					.addScalar("bus_stop_name", Hibernate.STRING)
					.addScalar("latitude_current", Hibernate.STRING)
					.addScalar("longitude_current", Hibernate.STRING)
					.addScalar("end_bus_stop_name", Hibernate.STRING)
					.addScalar("end_latitude", Hibernate.STRING)
					.addScalar("end_longitude", Hibernate.STRING)
					.addScalar("point_type_id", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);

				ja.add(rs.get("path").toString());
				ja.add(rs.get("bus_stop_name").toString());
				ja.add(rs.get("latitude_current").toString());
				ja.add(rs.get("longitude_current").toString());
				ja.add(rs.get("end_bus_stop_name").toString());
				ja.add(rs.get("end_latitude").toString());
				ja.add(rs.get("point_type_id").toString());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// Need to Call Webservice..
	@SuppressWarnings("unchecked")
	public JSONObject getAlerts() {
		Session session = null;
		Map<Integer, String> alertMap = new LinkedHashMap<Integer, String>();
		JSONObject result = new JSONObject();
		try {

			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO process result here
			model.jaxb.xml.trimax.com.VtsResponse totalAlertresult = port
					.getTotalAlerts(rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < totalAlertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				alertMap.put(i, totalAlertresult.getVtsDatamodel().get(i)
						.getALERTDESC());
				ja.add(totalAlertresult.getVtsDatamodel().get(i).getID());
				ja.add(totalAlertresult.getVtsDatamodel().get(i)
						.getALERTSHORTCODE());
				ja.add(totalAlertresult.getVtsDatamodel().get(i).getALERTDESC());
//				System.out.println("##########"+ja);
				array.add(ja);
			}
			result.put("bbData", array);
		} catch (Exception ex) {

		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getAlertsDetails(HttpServletRequest request) {
		Session session = null;
		Map<Integer, String> alertMap = new LinkedHashMap<Integer, String>();
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO process result here
			ResourceBundle rb = ResourceBundle
					.getBundle("com.trimax.its.properties.general");

			model.jaxb.xml.trimax.com.VtsResponse totalAlertresult = port
					.getAlertData(rb.getString("vtsParameter"), id);
			JSONArray array = new JSONArray();
			for (int i = 0; i < totalAlertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				// alertMap.put(i, totalAlertresult.getVtsDatamodel().get(i)
				// .getALERTDESC());

				ja.add("<a href='#' title='Track Online' onclick=javascript:getAlertDetails('"
						+ totalAlertresult.getVtsDatamodel().get(i)
								.getALERTSHORTCODE().replace(" ", "%20")
						+ "')>"
						+ totalAlertresult.getVtsDatamodel().get(i)
								.getTotalcount()
						+ "</a>"
						+ "<input type='hidden' name='routename' id='route_name_"
						+ totalAlertresult.getVtsDatamodel().get(i)
								.getALERTDESC()
						+ "'"
						+ " value"
						+ "='"
						+ totalAlertresult.getVtsDatamodel().get(i)
								.getALERTSHORTCODE() + "'>");
				ja.add(totalAlertresult.getVtsDatamodel().get(i)
						.getALERTSHORTCODE());
				ja.add(totalAlertresult.getVtsDatamodel().get(i).getALERTDESC());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	// Need to call Webservice...
	@SuppressWarnings("unchecked")
	public JSONObject getDataForSchedule(String scheduleNO, String selectedDate) {
		// TODO Auto-generated method stub
		Session session = null;
		JSONObject result = new JSONObject();
		try {
			String trip_status = "";
			String route_name = "";
			boolean flag = false;
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse totalAlertresult = port
					.getDataForSchedule(scheduleNO, selectedDate, rbKey);
//			System.out.println("SIZE"
//					+ totalAlertresult.getVtsDatamodel().size());
			JSONArray array = new JSONArray();
			for (int i = 0; i < totalAlertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				route_name = totalAlertresult.getVtsDatamodel().get(i)
						.getSTARTBUSSTOPNAME()
						+ " to "
						+ totalAlertresult.getVtsDatamodel().get(i)
								.getENDBUSSTOPNAME();
				trip_status = totalAlertresult.getVtsDatamodel().get(i)
						.getRECORDSTATUS();
				String start_time = (totalAlertresult.getVtsDatamodel().get(i)
						.getACTSTARTTIME() == null
						|| totalAlertresult.getVtsDatamodel().get(i)
								.getACTSTARTTIME().equals("") || totalAlertresult
						.getVtsDatamodel().get(i).getACTSTARTTIME()
						.equals("00:00:00")) ? totalAlertresult
						.getVtsDatamodel().get(i).getSTARTTIME().toString()
						: totalAlertresult.getVtsDatamodel().get(i)
								.getACTSTARTTIME().toString();
				String end_time = (totalAlertresult.getVtsDatamodel().get(i)
						.getACTENDTIME() == null
						|| totalAlertresult.getVtsDatamodel().get(i)
								.getACTENDTIME().equals("") || totalAlertresult
						.getVtsDatamodel().get(i).getACTENDTIME()
						.equals("00:00:00")) ? totalAlertresult
						.getVtsDatamodel().get(i).getENDTIME().toString()
						: totalAlertresult.getVtsDatamodel().get(i)
								.getACTENDTIME().toString();
				ja.add(totalAlertresult.getVtsDatamodel().get(i)
						.getTRIPNUMBER());
				if (!trip_status.equalsIgnoreCase("NotPerformed")) {
					ja.add("<a href='#' title='Track Online' onclick=javascript:drawScheduleRoute('"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getROUTEID()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSTARTPOINT()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getENDPOINT()
							+ ",'"
							+ start_time
							+ "','"
							+ trip_status
							+ ""
							+ "','"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDUTYDT()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ ",'"
							+ end_time
							+ "'"
							+ ",'"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().toString()
									.replace(" ", "")
							+ "'"
							+ ")>"
							+ trip_status
							+ "</a>"
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add("<a href='#' title='Track Online' onclick=javascript:drawScheduleRoute('"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getROUTEID()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSTARTPOINT()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getENDPOINT()
							+ ",'"
							+ start_time
							+ "','"
							+ trip_status
							+ ""
							+ "','"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDUTYDT()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ ",'"
							+ end_time
							+ "'"
							+ ",'"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().toString()
									.replace(" ", "")
							+ "'"
							+ ")>"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSTARTBUSSTOPNAME()
							+ "</a>"
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add("<a href='#' title='Track Online' onclick=javascript:drawScheduleRoute('"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getROUTEID()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSTARTPOINT()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getENDPOINT()
							+ ",'"
							+ start_time
							+ "','"
							+ trip_status
							+ ""
							+ "','"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDUTYDT()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ ",'"
							+ end_time
							+ "'"
							+ ",'"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().toString()
									.replace(" ", "")
							+ "'"
							+ ")>"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getENDBUSSTOPNAME()
							+ "</a>"
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add("<a href='#' title='Track Online' onclick=javascript:drawScheduleRoute('"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getROUTEID()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSTARTPOINT()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getENDPOINT()
							+ ",'"
							+ start_time
							+ "','"
							+ trip_status
							+ ""
							+ "','"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDUTYDT()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ ",'"
							+ end_time
							+ "'"
							+ ",'"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().toString()
									.replace(" ", "")
							+ "'"
							+ ")>"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSTARTTIME()
							+ "</a>"
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add("<a href='#' title='Track Online' onclick=javascript:drawScheduleRoute('"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDEVICEID()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getROUTEID()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSTARTPOINT()
							+ ","
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getENDPOINT()
							+ ",'"
							+ start_time
							+ "','"
							+ trip_status
							+ ""
							+ "','"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getDUTYDT()
							+ "',"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ ",'"
							+ end_time
							+ "'"
							+ ",'"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().toString()
									.replace(" ", "")
							+ "'"
							+ ")>"
							+ totalAlertresult.getVtsDatamodel().get(i)
									.getACTSTARTTIME() != null ? totalAlertresult
							.getVtsDatamodel().get(i).getACTSTARTTIME()
							: "NA"
									+ "</a>"
									+ "<input type='hidden' name='routename' id='route_name_"
									+ totalAlertresult.getVtsDatamodel().get(i)
											.getID() + "'" + " value='"
									+ route_name + "'>");
				} else {
					ja.add(""
							+ trip_status
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add(totalAlertresult.getVtsDatamodel().get(i)
							.getSTARTBUSSTOPNAME()
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add(totalAlertresult.getVtsDatamodel().get(i)
							.getENDBUSSTOPNAME()
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add(totalAlertresult.getVtsDatamodel().get(i)
							.getSTARTTIME()
							+ "<input type='hidden' name='routename' id='route_name_"
							+ totalAlertresult.getVtsDatamodel().get(i).getID()
							+ "'" + " value='" + route_name + "'>");
					ja.add(totalAlertresult.getVtsDatamodel().get(i)
							.getACTSTARTTIME() != null ? totalAlertresult
							.getVtsDatamodel().get(i).getACTSTARTTIME()
							: "NA"
									+ "<input type='hidden' name='routename' id='route_name_"
									+ totalAlertresult.getVtsDatamodel().get(i)
											.getID() + "'" + " value='"
									+ route_name + "'>");
				}
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	/**
	 * @param
	 * */
	@SuppressWarnings("unchecked")
	public JSONObject routeTeackingData(int routeId) {
		JSONObject result = new JSONObject();
		List<VtsDataModel> deviceList = deviceList(routeId);
		String device_arr[] = new String[3];
		String finalLatlng[] = new String[3];
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String current_date = format.format(date);
		JSONArray array = new JSONArray();
		try {
			if (deviceList.size() > 0) {
				for (int i = 0; i < deviceList.size(); i++) {
					JSONArray ja = new JSONArray();
					device_arr[0] = deviceList.get(i).getDEVICE_ID();
					device_arr[1] = current_date + " "
							+ deviceList.get(i).getSTARTTIME();
					device_arr[2] = current_date + " "
							+ deviceList.get(i).getENDTIME();
					finalLatlng = vehicleCurrentLocation(device_arr);
					ja.add(deviceList.get(i).getDEVICE_ID());
					ja.add(deviceList.get(i).getSCHEDULENAME());
					ja.add(deviceList.get(i).getVEHICLE_NO());
					ja.add(deviceList.get(i).getTRIPNUMBER());

					ja.add((finalLatlng[0] != null ? finalLatlng[0] : ""));
					ja.add(finalLatlng[1] != null ? finalLatlng[1] : "");
					ja.add(finalLatlng[2] != null ? finalLatlng[2] : "0.00");
					ja.add(deviceList.get(i).getDEPOTNAME());
					array.add(ja);
				}

			}
			result.put("deviceData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/*
	 * 
	 * */
	public List<VtsDataModel> deviceList(int route_id) {
		Session session = null;
		List<VtsDataModel> modelList = null;

		try {
			session = new HibernateUtilVtu().getSession("");
			modelList = new ArrayList<VtsDataModel>();
			String sql = "select DEVICE_ID, start_time, end_time, schedule_name,VEHICLE_NO, "
					+ "	TRIP_NUMBER,DEPOT_CD from waybill_trip_details "
					+ "	where ROUTE_ID = ?  AND CURRENT_TIME BETWEEN start_time and end_time and status='ONLINE' and TRIP_STATUS='Y' "
					+ "	group by DEVICE_ID order by DEVICE_ID";
			Query query = session.createSQLQuery(sql)
					.addScalar("DEVICE_ID", Hibernate.STRING)
					.addScalar("start_time", Hibernate.STRING)
					.addScalar("end_time", Hibernate.STRING)
					.addScalar("schedule_name", Hibernate.STRING)
					.addScalar("VEHICLE_NO", Hibernate.STRING)
					.addScalar("TRIP_NUMBER", Hibernate.STRING)
					.addScalar("DEPOT_CD", Hibernate.STRING)

			;
			query.setParameter(0, route_id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				VtsDataModel model = new VtsDataModel();
				model.setDEVICE_ID(rs.get("DEVICE_ID").toString());
				model.setSTARTTIME((rs.get("start_time").toString()));
				model.setENDTIME((rs.get("end_time").toString()));
				model.setSCHEDULENAME((rs.get("schedule_name").toString()));
				model.setVEHICLE_NO((rs.get("VEHICLE_NO").toString()));
				model.setDEPOTNAME((rs.get("DEPOT_CD")) != null ? rs.get(
						"DEPOT_CD").toString() : "");
				model.setTRIPNUMBER(Integer.parseInt(rs.get("TRIP_NUMBER")
						.toString()));
				modelList.add(model);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return modelList;
	}

	public String[] vehicleCurrentLocation(String[] device_para) {
		String finalarr[] = new String[3];
		try {

			Session session = new HibernateUtilVtu().getSession("");
			String sql = "select LAT, LONGITUDE, SPEED_KMPH from  vts_parse_data WHERE DEVICE_ID = ?"
					+ "	AND IST_DATE BETWEEN ? AND ? ORDER BY IST_DATE DESC LIMIT 1 ";
			Query query = session.createSQLQuery(sql);
			query.setParameter(0, device_para[0]);
			query.setParameter(1, device_para[1]);
			query.setParameter(2, device_para[2]);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				finalarr[0] = rs.get("LAT").toString();
				finalarr[1] = rs.get("LONGITUDE").toString();
				finalarr[2] = rs.get("SPEED_KMPH").toString();
			}

		} catch (Exception ex) {

		} finally {
			//HibernateUtilVtu.closeSessionFactory();
		}
		return finalarr;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForScheduledVehicle() {
		// TODO Auto-generated method stub
		Session session = null;
		JSONObject result = new JSONObject();
		try {

			boolean flag = false;
			String trip_status = "";
			HibernateUtilVtu h = new HibernateUtilVtu();
			session = h.getSession("");
			String sql = "select SCHEDULE_NAME,VEHICLE_NO,DEVICE_ID,START_TIME,"
					+ " ACT_START_TIME,END_TIME,ACT_END_TIME,TRIP_NUMBER,TRIP_STATUS,START_BUS_STOP_NAME,END_BUS_STOP_NAME "
					+ " from waybill_trip_details where status='ONLINE' order by SCHEDULE_NO,TRIP_NUMBER, START_TIME, END_TIME";
			Query query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();

				Map<String, Object> rs = aliasToValueMapList.get(i);

				if (rs.get("TRIP_STATUS").toString().equals("Y")) {
					trip_status = "Running";
				}
				if (rs.get("TRIP_STATUS").toString().equals("C")) {
					trip_status = "Completed";
				}
				if (rs.get("TRIP_STATUS").toString().equals("N")) {
					trip_status = "Not Performed";
				}
				ja.add(i + 1);
				ja.add(rs.get("SCHEDULE_NAME").toString());
				ja.add(rs.get("VEHICLE_NO").toString());
				ja.add(rs.get("DEVICE_ID").toString());
				ja.add(rs.get("TRIP_NUMBER").toString());
				ja.add(rs.get("START_BUS_STOP_NAME") != null ? rs.get(
						"START_BUS_STOP_NAME").toString() : "");
				ja.add(rs.get("END_BUS_STOP_NAME") != null ? rs.get(
						"END_BUS_STOP_NAME").toString() : "");
				ja.add(rs.get("START_TIME").toString());
				ja.add(rs.get("ACT_START_TIME") != null ? rs.get(
						"ACT_START_TIME").toString() : "");
				ja.add(rs.get("END_TIME").toString());
				ja.add(rs.get("ACT_END_TIME") != null ? rs.get("ACT_END_TIME")
						.toString() : "");
				ja.add(trip_status);
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			//HibernateUtilVtu.closeSessionFactory();
		}
		return result;

	}

	//@SuppressWarnings("unchecked")
	public JSONObject c(int x, HttpServletRequest request, String fromDate,
			String toDate, String depot_id) {
		
		Session session = null;
		JSONObject result = new JSONObject();
		// JSONObject result = new JSONObject();
		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			Common cm = new Common();
	
			// TODO initialize WS operation arguments here
//			System.out.println("depot id===="+depot_id+"=="+fromDate);
//			System.out.println(rbKey);
			VtsResponse5 alertresult = port.webGetC(depot_id, fromDate, rbKey);
			alertresult=alertresult;
//			System.out.println(alertresult);
//			System.out.println("VtsDataModelSize : "+alertresult.getVtsDatamodel().size());
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getOrgName());
				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
//			if(alertresult.getVtsDatamodel().get(i).getSchtype().equals("1")){
//				ja.add("General Shift");
//			}else if(alertresult.getVtsDatamodel().get(i).getSchtype().equals("2")){
//				ja.add("Night Out");
//			}else if(alertresult.getVtsDatamodel().get(i).getSchtype().equals("3")){
//				ja.add("Day Out");
//			}	else if(alertresult.getVtsDatamodel().get(i).getSchtype().equals("4")){
//					ja.add("Night Service");
//			}else if(alertresult.getVtsDatamodel().get(i).getSchtype().equals("11")){
//				ja.add("Split Service");
//			}else if(alertresult.getVtsDatamodel().get(i).getSchtype().equals("12")){
//				ja.add("General Night Out");
//			}else if(alertresult.getVtsDatamodel().get(i).getSchtype().equals("13")){
//				ja.add("Night Service");
//			}
				switch (alertresult.getVtsDatamodel().get(i).getDutyTypeId()) {
				case 1:
					ja.add("General Shift");
					break;
				case 2:
					ja.add("Night Out");
					break;
				case 3:
					ja.add("Day Out");
					break;
				case 4:
					ja.add("Night Service");
					break;
				case 11:
					ja.add("Split Service");
					break;
				case 12:
					ja.add("General Night Out");
					break;
				default:
					ja.add("");
					break;
				}
			
			
				//ja.add("");
				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICESTATUS());
				ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
				+ alertresult.getVtsDatamodel().get(i)
						.getDEVICEID()
				+ "','"
				+ fromDate+"',1,'"+alertresult.getVtsDatamodel().get(i)
				.getHr1()+"') >"
				+ alertresult.getVtsDatamodel().get(i)
						.getHr1()
				+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',2,'"+alertresult.getVtsDatamodel().get(i)
								.getHr2()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr2()
						+ "</a>");

				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',3,'"+ alertresult.getVtsDatamodel().get(i)
						.getHr3()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr3()
						+ "</a>");
			
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',4,'"+alertresult.getVtsDatamodel().get(i)
						.getHr4()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr4()
						+ "</a>");
//				ja.add(alertresult.getVtsDatamodel().get(i).getHr4());
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',5,'"+alertresult.getVtsDatamodel().get(i)
						.getHr5()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr5()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',6,'"+alertresult.getVtsDatamodel().get(i)
						.getHr6()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr6()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',7,'"+alertresult.getVtsDatamodel().get(i)
						.getHr7()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr7()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',8,'"+alertresult.getVtsDatamodel().get(i)
						.getHr8()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr8()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',9,'"+alertresult.getVtsDatamodel().get(i)
						.getHr9()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr9()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',10,'"+alertresult.getVtsDatamodel().get(i)
						.getHr10()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr10()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',11,'"+alertresult.getVtsDatamodel().get(i)
						.getHr11()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr11()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',12,'"+alertresult.getVtsDatamodel().get(i)
						.getHr12()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr12()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',13,'"+alertresult.getVtsDatamodel().get(i)
						.getHr13()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr13()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',14,'"+alertresult.getVtsDatamodel().get(i)
						.getHr14()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr14()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',15,'"+alertresult.getVtsDatamodel().get(i)
						.getHr15()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr15()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',16,'"+alertresult.getVtsDatamodel().get(i)
						.getHr16()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr16()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',17,'"+alertresult.getVtsDatamodel().get(i)
						.getHr17()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr17()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',18,'"+alertresult.getVtsDatamodel().get(i)
						.getHr18()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr18()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',19,'"+alertresult.getVtsDatamodel().get(i)
						.getHr19()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr19()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',20,'"+alertresult.getVtsDatamodel().get(i)
						.getHr20()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr20()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',21,'"+alertresult.getVtsDatamodel().get(i)
						.getHr21()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr21()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',22,'"+alertresult.getVtsDatamodel().get(i)
						.getHr22()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr22()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',23,'"+alertresult.getVtsDatamodel().get(i)
						.getHr23()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr23()
						+ "</a>");
				
				ja.add("<a data-toggle='modal'  role='button' href='#mymodal3'  onclick=javascript:viewVTUDeviceDetailsList('"
						+ alertresult.getVtsDatamodel().get(i)
								.getDEVICEID()
						+ "','"
						+ fromDate+"',24,'"+alertresult.getVtsDatamodel().get(i)
						.getHr24()+"') >"
						+ alertresult.getVtsDatamodel().get(i)
								.getHr24()
						+ "</a>");
				
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;

	}

	public JSONObject getDataForSkipStop(int x, HttpServletRequest request,
			String fromDate, String tillDate) {
		String packet_code = request.getParameter("packet_code");
	
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			
			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.getDataForChartGrid(packet_code,
					tillDate, fromDate, id, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				String arr[] = alertresult.getVtsDatamodel().get(i)
						.getVARIATIONSENSE().split("-");
				String busStopName = getBusStopName(arr[1], arr[0]);
				if (!busStopName.equalsIgnoreCase("")) {
					JSONArray ja = new JSONArray();
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getLICENCENUMBER());
					ja.add(getScheduleName(alertresult.getVtsDatamodel().get(i)
							.getDEVICEID()));

					if (arr.length == 2) {
						ja.add(busStopName);
					} else {
						ja.add("");
					}
					array.add(ja);
				}
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForRouteDeviation(int x,
			HttpServletRequest request, String fromDate,
			int depot_id) {
		String packet_code = "Deviated";
		
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
		
			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
		
			VtsResponse alertresult = port.getDataForRouteDeviation(
					packet_code, fromDate, String.valueOf(depot_id), rbKey);
			JSONArray array = new JSONArray();
			//System.out.println("alertresult"+alertresult.getVtsDatamodel().get(0).getSCHEDULENAME());
			/*
			 * String relatedPointType = ""; Session session
			 * =HibernateUtil.getSession(""); session =
			 * HibernateUtil.getSession(""); Query query = session
			 * .createSQLQuery(
			 * "select sys_value from default_system_veriable where sys_key='point_type_id'"
			 * ); if (query.list().size() > 0) { relatedPointType =
			 * query.uniqueResult().toString(); }
			 */
			int xx = 1;
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				if (depot_id == alertresult.getVtsDatamodel().get(i)
						.getBusStopId()) {
					JSONArray ja = new JSONArray();
					ja.add(xx);
					ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
					ja.add(alertresult.getVtsDatamodel().get(i).getDEPOTNAME());
					//ja.add(alertresult.getVtsDatamodel().get(i).getDUTYDT());
					
					
					String shift = "";
					switch (alertresult.getVtsDatamodel().get(i)
							.getDutyTypeId()) {
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
					ja.add(shift);
					//ja.add(alertresult.getVtsDatamodel().get(i).getROUTENO());
					ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
					ja.add(alertresult.getVtsDatamodel().get(i).getTripNumber());
					ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERNAME());
					ja.add(alertresult.getVtsDatamodel().get(i).getCONDNAME());

					xx++;
					array.add(ja);
				}
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getDataForSkipStopDetail(String device_id,
			HttpServletRequest request, String givenDate, String dir,
			String fromDate, String tillDate, Session session,int shiftId) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		try {
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse4 alertresult = port.webGetSkippedStop1(device_id, "42",
					givenDate, "", device_id, rbKey,shiftId);
			JSONArray array = new JSONArray();

			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getTRIPNUMBER());
				// String
				// ticketDate=alertresult.getVtsDatamodel().get(i).getISTDATE();
				// System.out.println("ticketDate"+ticketDate);

				// Date ticketDate1 = new Date();
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				// ticketDate=sm.format(ticketDate1);
				//System.out.println("ticketDate" + ticketDate);
				ticketDate = alertresult.getVtsDatamodel().get(i).getISTDATE();

				String dateType[] = ticketDate.split(" ");
				String y1 = dateType[0];
				String time = dateType[1];
				//System.out.println(time + "," + y1);
				//System.out.println("bus stop id"+alertresult.getVtsDatamodel().get(i).getBusStopId());
				//System.out.println("getROUTEID  "+alertresult.getVtsDatamodel().get(i).getROUTEID());
				//System.out.println("getSTARTPOINT id"+alertresult.getVtsDatamodel().get(i).getSTARTPOINT());
				//System.out.println("getENDPOINT id"+alertresult.getVtsDatamodel().get(i).getENDPOINT());

				ja.add("<a  href='#mymodal'  onclick=javascript:drawSkipstop('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ y1
						+ "','"
						+ time
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getDEPOTCD().replace(" ", "")
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getBusStopId()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getBusStopName().replace(" ", "")
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getSTARTPOINT()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getENDPOINT()
						+ "') >"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "' id='vehicleid' value='"
						+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'");

				// ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				ja.add(alertresult.getVtsDatamodel().get(i).getBusStopName());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	// Getting OverSpeeding Alert Details..
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getDataForOverSpeedDetail(String device_id,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, Session session) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		Common common = new Common();
		try {
			// rajesh changes for access
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
			
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			

			// end
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse4 alertresult = port.webGetOverSpeedData(device_id, id,
					"", "", device_id, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				
				Date ticketDate1 = new Date();
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				ticketDate = sm.format(ticketDate1);
			


				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERNAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERTOKENNO());
				ja.add(alertresult.getVtsDatamodel().get(i).getOrgName());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH());
				ja.add(alertresult.getVtsDatamodel().get(i).getSIMNUMBER());
				//ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleOverSpeedData('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "') title='Alert Details' id='alert_details"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "' value='"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'>"
						+ alertresult.getVtsDatamodel().get(i).getTotalcount()
						+ "</a>");
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getDataOverSpeedDataDetails(String device_id,
			HttpServletRequest request, String givenDate, String dir,
			String fromDate, String tillDate, Session session) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		try {
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse4 alertresult = port.webGetOverSpeedData1(device_id,
					givenDate, "", device_id, rbKey);
			JSONArray array = new JSONArray();

			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				//ja.add(alertresult.getVtsDatamodel().get(i).getTRIPNUMBER());
				
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				
				
				ticketDate = alertresult.getVtsDatamodel().get(i).getISTDATE();

				String dateType[] = ticketDate.split(" ");
				String y1 = dateType[0];
				String time = dateType[1];
				
				ja.add("<a  href='#mymodal'  onclick=javascript:getShowLiveBusDetailsAllVts('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ y1
						+ "','"
						+ time
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "','"
						+ alertresult.getVtsDatamodel().get(i).getDEPOTNAME().replace(" ", "")
						+ "') >"
						+ alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "' id='vehicleid' value='"
						+ alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "'");

				// ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				//ja.add(alertresult.getVtsDatamodel().get(i).getBusStopName());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}
	
	// Finding Nearest Bus Stop by Passing LAT.. and LNG
	// For Bus Bunching Details
	
	@SuppressWarnings({ "unchecked", "unused" })
	public JSONObject getDataForBusBunchingDetail(String device_id,
			HttpServletRequest request, String cols, String dir,
			String fromDate, String tillDate, Session session) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		try {

			// rajesh access start

			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");
		
			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = "depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = "depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			

			// end
			// String relatedPointType = "";
			// session = HibernateUtil.getSession("");
			// Query query = session
			// .createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			// if (query.list().size() > 0) {
			// relatedPointType = query.uniqueResult().toString();
			// }
			// Calling WebService For getting Data....01-09-2014
			//System.out.println("depot id "+id);
			
			boolean flag = false;
			String trip_status = "";
//			WsUtil_Service service = new WsUtil_Service();
//			WsUtil port = service.getWsUtilPort();
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			
			
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			
			
			//end
			VtsResponse4 alertresult = port.webGetBusBunchingData(device_id,
					id, "", "", device_id, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				
				String fulldatarow = alertresult.getVtsDatamodel().get(i).getSchtype();
				String starttime = alertresult.getVtsDatamodel().get(i).getSTARTTIME();
				String endtime = alertresult.getVtsDatamodel().get(i).getENDTIME();
						
				String[] rowvals = fulldatarow.split("_@_");
				String checkdevice = "";
				String[] deviceListOfBunching = new String[rowvals.length];
				String[] vehicleNumListOfBunching = new String[rowvals.length];
				String[] vehicleTimeListOfBunching = new String[rowvals.length];
				Double lat = 0.0;
				Double lng = 0.0;
				for (int ii = 0; ii < rowvals.length; ii++) {
					String[] devicevals = rowvals[ii].split("@_@");
					deviceListOfBunching[ii] = devicevals[0];
					vehicleNumListOfBunching[ii] = devicevals[1];
					vehicleTimeListOfBunching[ii] = devicevals[6];
					lat = Double.parseDouble(devicevals[2]);
					lng = Double.parseDouble(devicevals[3]);
				}
				Set<String> set = new HashSet<String>();
				Set<String> vehiclenumset = new HashSet<String>();
				Set<String> vehicletimeset = new HashSet<String>();
				Set<String> vehicletimeToDisp1 = new HashSet<String>();
				for (int jj = 0; jj < deviceListOfBunching.length; jj++) {
					set.add(deviceListOfBunching[jj]);
					vehiclenumset.add(vehicleNumListOfBunching[jj] + " ["
							+ vehicleTimeListOfBunching[jj] + "]");
					vehicletimeset.add(vehicleTimeListOfBunching[jj]);
					vehicletimeToDisp1.add(starttime);
					vehicletimeToDisp1.add(endtime);
				}

//				String locationName = getStopList(lat, lng, session,
//						relatedPointType);
				String vehicleNumsToDisp = StringUtils
						.join(vehiclenumset, ", ");
				String vehicletimeToDisp = StringUtils.join(vehicletimeset,
						", ");
				
				
				
				String vehiclestartendtimeToDisp = StringUtils.join(vehicletimeToDisp1,
						", ");
				
				
				
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(vehicleNumsToDisp);
                ja.add(alertresult.getVtsDatamodel().get(i).getROUTENO());
				
				//ja.add(locationName);
				ja.add(vehiclestartendtimeToDisp);
				ja.add(set.size());
	
				ja.add("<a href='#' class='mapClass' title='View on Map' onclick=javascript:drawBusBunching("
						+ alertresult.getVtsDatamodel().get(i).getROUTEID()
						+ ",'"
						+ alertresult.getVtsDatamodel().get(i).getSchtype()
						+ "',"
						+ alertresult.getVtsDatamodel().get(i).getSTARTPOINT()
						+ ","
						+ alertresult.getVtsDatamodel().get(i).getENDPOINT()
						+ ",'"
						+ alertresult.getVtsDatamodel().get(i).getROUTENO()
						+ "')>"
						+ "<img src='assets/images/bus-map-icon.png'/>"
						+ "</a>");
				
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	// End Bus Bunching
	List<BusStops> list;

	public List<BusStops> getList() {
		return list;
	}

	public void setList(List<BusStops> list) {
		this.list = list;
	}

	public static void main(String ar[]) {
		//System.out.println(VtsDataDAO.getInstance().getTimeDiffInMin("2016-08-02", "2016-08-02 15:00:00", "2016-08-02 13:00:00"));
		//VtsDataDAO.getInstance().updateRouteFenceString(0);
	}

	public String getStopList(Double latitudeOne, Double longitudeOne,
			Session session, String point_type) {
//		System.out.println("latitudeOne  longitudeOne" + latitudeOne + "\t"
//				+ longitudeOne + "\t" + point_type);
		double para = 0.02;
		double factor = 1.2;
		double dist = -0.0;
		double lastdist = -0.0;
		int id = 0;
		String name = "";

		list = getBusStopList(latitudeOne.toString(), longitudeOne.toString(),
				para, session, point_type);
		for (int j = 0; j < 10; j++) {
			if (list.size() > 0) {
				break;
			}
			if (list.size() == 0) {
				para = para * factor;
			}
			if (list.size() > 175) {
				para = para / factor;
			}
			list = getBusStopList(latitudeOne.toString(),
					longitudeOne.toString(), para, session, point_type);
		}
		for (int i = 0; i < list.size(); i++) {
			double calcdist = distanceBetweenTwoLocationsInKm(latitudeOne,
					longitudeOne, list.get(i).getLatitude(), list.get(i)
							.getLongitude());
			if (i > 0) {
				int compres = Double.compare(calcdist, dist);
				if (compres < 0) {

					dist = calcdist;
					id = list.get(i).getId();
					name = list.get(i).getBusStopName();
				} else {
					dist = dist;
				}
			} else {
				dist = calcdist;
				lastdist = calcdist;
				id = list.get(i).getId();
			}
		}
		return name;
	}

	public Double distanceBetweenTwoLocationsInKm(Double latitudeOne,
			Double longitudeOne, Double latitudeTwo, Double longitudeTwo) {
		if (latitudeOne == null || latitudeTwo == null || longitudeOne == null
				|| longitudeTwo == null) {
			return null;
		}
		Double earthRadius = 6371.0;
		Double diffBetweenLatitudeRadians = Math.toRadians(latitudeTwo
				- latitudeOne);
		Double diffBetweenLongitudeRadians = Math.toRadians(longitudeTwo
				- longitudeOne);
		Double latitudeOneInRadians = Math.toRadians(latitudeOne);
		Double latitudeTwoInRadians = Math.toRadians(latitudeTwo);
		Double a = Math.sin(diffBetweenLatitudeRadians / 2)
				* Math.sin(diffBetweenLatitudeRadians / 2)
				+ Math.cos(latitudeOneInRadians)
				* Math.cos(latitudeTwoInRadians)
				* Math.sin(diffBetweenLongitudeRadians / 2)
				* Math.sin(diffBetweenLongitudeRadians / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (earthRadius * c);
	}

	@SuppressWarnings("unchecked")
	public List<BusStops> getBusStopList(String lat, String lag, double para,
			Session session, String point_type) {
		List<BusStops> list = null;
		List<BusStops> finallist = new ArrayList<BusStops>();
		double lat1 = 0.0;
		double lag1 = 0.0;
		if (lat.length() > 4) {
			lat1 = Double.parseDouble(lat.substring(0, 5));
		} else {
			lat1 = Double.parseDouble(lat);
		}
		if (lag.length() > 4) {
			lag1 = Double.parseDouble(lag.substring(0, 5));
		} else {
			lag1 = Double.parseDouble(lag);
		}
		double latmin = lat1 - para;
		double latmax = lat1 + para;
		double lngmin = lag1 - para - 0.01;
		double lngmax = lag1 + para + 0.01;
		// Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query;
		try {

			query = session
					.createQuery("from BusStops where status in ('New','Approved', 'Inactive')  and updated_by!='0' and ((latitude_current	between '"
							+ latmin
							+ "'and '"
							+ latmax
							+ "') and (longitude_current between '"
							+ lngmin
							+ "'and '"
							+ lngmax
							+ "')) and point_type_id not in(2,13) ");
			// query.setString(0, point_type);
			list = (new ArrayList<BusStops>(new HashSet<BusStops>(query.list())));
		} catch (Exception e) {
//			System.out.println(e);
		} finally {
			if (session != null) {
				// session.close();
			}
		}
		return list;
	}

	public Map<Integer, String> getDepot() throws Exception {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		int parentID = 3;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String userorgtype = (String) request.getSession().getAttribute(
				"userorgtype");

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select org_chart_id,org_name from org_chart where deleted_status=0 and org_type_id= "
					+ parentID + " order by org_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			List<OrganisationChart> list = query.list();
			resultMap.put(-1, "All");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

				resultMap.put(Integer.parseInt(rs.get("org_chart_id")
						.toString()), rs.get("org_name").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return resultMap;

	}
	
	public Map<Integer, String> getDepotWithoutAll() throws Exception {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		int parentID = 3;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String userorgtype = (String) request.getSession().getAttribute(
				"userorgtype");

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select org_chart_id,org_name from org_chart where deleted_status=0 and org_type_id= "
					+ parentID + " order by org_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			List<OrganisationChart> list = query.list();
//			resultMap.put(-1, "All");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

				resultMap.put(Integer.parseInt(rs.get("org_chart_id")
						.toString()), rs.get("org_name").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return resultMap;

	}
	
	

	public Map<Integer, String> getDepot1() throws Exception {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		int parentID = 3;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String userorgtype = (String) request.getSession().getAttribute(
				"userorgtype");
		// HttpServletRequest req=ServletActionContext.getRequest();
		String orgchartid = (String) request.getSession().getAttribute(
				"orgchartid");
//		System.out.println("userorgtype"+userorgtype+"\t"+orgchartid);
		String param = "";
		if(userorgtype.toString().equals("BMTC") && (orgchartid.equals("2") || orgchartid.equals("4") || orgchartid.equals("5") || orgchartid.equals("6") || orgchartid.equals("7"))){
			param = " and parent_id=" + orgchartid + " ";
		}else if (!orgchartid.equals("1")) {
			param = " and org_chart_id=" + orgchartid + " ";
		}
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select org_chart_id,org_name from org_chart where deleted_status=0 and org_type_id= "
					+ parentID + "" + param + "  order by org_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			List<OrganisationChart> list = query.list();
			// caseresultMap.put(-1, "All");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

				resultMap.put(Integer.parseInt(rs.get("org_chart_id")
						.toString()), rs.get("org_name").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return resultMap;

	}

	public int getDepot1(String orgtype_id, String orgchart_id)
			throws Exception {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		int parentID = 0;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String userorgtype = (String) request.getSession().getAttribute(
				"userorgtype");

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select parent_id from org_chart where deleted_status=0 and org_type_id= "
					+ orgtype_id
					+ " and org_chart_id='"
					+ orgchart_id
					+ "' order by org_name";
			Query query = session.createSQLQuery(qry);
			parentID = Integer.parseInt(query.uniqueResult().toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return parentID;

	}
	
	public int getDepot1DC(String orgtype_id, String orgchart_id)
			throws Exception {

		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		int parentID = 0;

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String userorgtype = (String) request.getSession().getAttribute(
				"userorgtype");

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select parent_id from org_chart where deleted_status=0 and org_type_id= "
					+ orgtype_id
					+ " group by parent_id  order by org_name";
			Query query = session.createSQLQuery(qry);
			parentID = Integer.parseInt(query.uniqueResult().toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return parentID;

	}

	@SuppressWarnings("unchecked")
	public JSONObject getBusBunchingInfo(String distance, String routeid) {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		Map<Integer, String> listrout = new HashMap<Integer, String>();
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();

			VtsResponse response = port.webGetBusBunchingInfo(distance,
					routeid, rbKey);
			JSONArray array = new JSONArray();

			for (int i = 0; i < response.getVtsDatamodel().size(); i++) {
				Map<String, String> routeMap = new HashMap<String, String>();
				JSONArray ja = new JSONArray();
				double dist = response.getVtsDatamodel().get(i).getDistance();
				routeMap.put(
						String.valueOf(response.getVtsDatamodel().get(0)
								.getROUTEID()),
						response.getVtsDatamodel().get(i).getDEVICEID());
//				System.out.println("routeMap size==>" + routeMap.size());
				if (response.getVtsDatamodel().get(i).getDEVICEID() != null) {
					VtsResponse response1 = port.webGetVehicleinfo(
							String.valueOf(response.getVtsDatamodel().get(i)
									.getROUTEID()), response.getVtsDatamodel()
									.get(i).getDEVICEID(), rbKey);
					ja.add(response1.getVtsDatamodel().get(0).getVEHICLENO());
					ja.add(response.getVtsDatamodel().get(i).getDEVICEID());
					ja.add(response1.getVtsDatamodel().get(0).getSCHEDULENAME());
					ja.add(response1.getVtsDatamodel().get(0).getTRIPNUMBER());
					ja.add(response1.getVtsDatamodel().get(0).getSTARTTIME());
					ja.add(response.getVtsDatamodel().get(i).getLAT());
					ja.add(response.getVtsDatamodel().get(i).getLONGITUDE());
					ja.add(response.getVtsDatamodel().get(i).getROUTENO());
					array.add(ja);
				}
			}
			result.put("aaData", array);
			/*
			 * for (int i = 0; i < response.getVtsDatamodel().size(); i++) {
			 * JSONArray ja = new JSONArray();
			 * 
			 * ja.add(response.getVtsDatamodel().get(i).getROUTEID());
			 * //ja.add(response.getVtsDatamodel().get(i).get())
			 * System.out.println
			 * ("response.getVtsDatamodel().get(i).getROUTEID()"
			 * +response.getVtsDatamodel().get(i).getROUTEID());
			 * ja.add(response.getVtsDatamodel().get(i).getDEVICEID());
			 * ja.add(response.getVtsDatamodel().get(i).getLAT());
			 * ja.add(response.getVtsDatamodel().get(i).getLONGITUDE());
			 * ja.add(response.getVtsDatamodel().get(i).getISTDATE());
			 * array.add(ja); } result.put("aaData", array);
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	/*
	 * // @SuppressWarnings({ "unchecked", "unused" }) // public JSONObject
	 * getRouteBunchInfo(String routeid, // HttpServletRequest request, String
	 * cols, String dir, Session session) { // JSONObject result = new
	 * JSONObject(); // int totalCount = 0; // try { // // Calling WebService
	 * For getting Data....01-09-2014 //
	 * com.trimax.ws.vts.vtsutility.WsUtil_Service service = new
	 * com.trimax.ws.vts.vtsutility.WsUtil_Service(); //
	 * com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort(); // //
	 * TODO initialize WS operation arguments here // VtsResponse alertresult =
	 * port.webGetVehicleinfo("", ""); // JSONArray array = new JSONArray(); //
	 * for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) { //
	 * JSONArray ja = new JSONArray(); // ja.add(i + 1); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO()); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID()); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME()); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getTRIPNUMBER()); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getTRIPSTATUS()); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getLAT()); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getLONGITUDE()); //
	 * ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE()); // //
	 * array.add(ja); // } // result.put("aaData1", array); // } catch
	 * (Exception ex) { // ex.printStackTrace(); // } finally { // } // return
	 * result; // }
	 */
	public Map<Integer, String> getAlertRouteReport() {
		JSONObject result = new JSONObject();
		int totalCount = 0;
		Map<Integer, String> listalert = new HashMap<Integer, String>();
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();

			VtsResponse response = port.webGetAlerReportCode(rbKey);

			JSONArray array = new JSONArray();
			for (int i = 0; i < response.getVtsDatamodel().size(); i++) {
				totalCount += response.getVtsDatamodel().get(i).getTotalcount();
			}
			for (int i = 0; i < response.getVtsDatamodel().size(); i++) {
				listalert.put(response.getVtsDatamodel().get(i).getID(),
						response.getVtsDatamodel().get(i).getALERTSHORTCODE());

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listalert;

	}

	/**
	 * @param alertID
	 * @param req
	 * @param fromDate
	 * @param tillDate
	 * @param depotID
	 * @param givendate
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getDataForAlertReport(String alertID,
			HttpServletRequest req, String fromDate, String tillDate,
			String depotID, String givendate, Session session, int startPoint,String todate) {
		Common common = new Common();
		JSONObject result = new JSONObject();
		int totalCount = 0;
//		System.out.println("to date-----"+todate);
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String orgtypeid = (String) req.getSession().getAttribute("orgtypeid");
		String orgchartid = (String) req.getSession()
				.getAttribute("orgchartid");
//		System.out.println("orgtypeid..........." + orgtypeid
//				+ "orgchartid................." + orgchartid);
		String id = "!=0";
		if (orgtypeid.equals("1")) {
			id = "depot_id!=0";

		}
		if (orgtypeid.equals("3")) {

			id = "depot_id in('" + orgchartid + "')";
		}
		if (orgtypeid.equals("2")) {

			id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
					+ orgchartid + "')";
		}
//		System.out.println("id...." + id);
		try {
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			// Calling WebService For getting Data....01-09-2014
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse4 alertresult = null;
			VtsResponse5 alertresulttampering = null;
			VtsResponse3 alertresultskip = null;
			VtsResponse5 alertresultSos = null;
			VtsResponse2 alertresultdeparture = null;
			VtsResponse4 alertresultdeviated = null;
			VtsResponse1 alertresultarrival = null;
			VtsResponse schearlyDept = null;
			VtsResponse schlateDept = null;
			VtsResponse alertresultStationaryVehicle = null;
			VtsResponse alertresultWorkShopReport = null;
			JSONArray array = new JSONArray();
			switch (Integer.parseInt(alertID)) {
			case 1:
				alertresulttampering = port.webGetTamperingAlert(alertID,
						fromDate, tillDate + " 23:59:59", id, rbKey);
				array = new JSONArray();
				int j = 1;
				for (int i = 0; i < alertresulttampering.getVtsDatamodel()
						.size(); i++) {
					JSONArray ja = new JSONArray();
					if (alertresulttampering.getVtsDatamodel().get(i)
							.getBusStopId() == Integer.parseInt(depotID)) {
						ja.add(j);
						ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleAlertData('"
								+ alertresulttampering.getVtsDatamodel().get(i)
										.getPACKETCODE()
								+ "','"
								+ alertresulttampering.getVtsDatamodel().get(i)
										.getMISCBYTES()
								+ "','"
								+ alertresulttampering.getVtsDatamodel().get(i)
										.getDEVICEID()
								+ "','"
								+ alertresulttampering.getVtsDatamodel().get(i)
										.getLICENCENUMBER()
								+ "') title='Alert Details' id='alert_details"
								+ alertresulttampering.getVtsDatamodel().get(i)
										.getPACKETCODE()
								+ "' value='"
								+ alertresulttampering.getVtsDatamodel().get(i)
										.getPACKETCODE()
								+ "'>"
								+ alertresulttampering.getVtsDatamodel().get(i)
										.getLICENCENUMBER() + "</a>");
						ja.add(alertresulttampering.getVtsDatamodel().get(i)
								.getDEPOTNAME());
						/*
						 * ja.add(alertresulttampering.getVtsDatamodel().get(i)
						 * .getLICENCENUMBER());
						 */
						ja.add(alertresulttampering.getVtsDatamodel().get(i)
								.getISTDATE());
						ja.add(alertresulttampering.getVtsDatamodel().get(i)
								.getTotalcount());
						/*
						 * ja.add(getStopList(
						 * Double.parseDouble(alertresulttampering
						 * .getVtsDatamodel().get(i).getLAT()),
						 * Double.parseDouble(alertresulttampering
						 * .getVtsDatamodel().get(i).getLONGITUDE()), session,
						 * relatedPointType));
						 */

						array.add(ja);
						j++;
					}
				}
				break;
			case 2:
				// HA Report...
				alertresult = port.webGetHAData(alertID, id, fromDate, tillDate
						+ " 23:59:59", "", rbKey);
				array = new JSONArray();
				// System.out.println("Oside");
				int x = 1;
				for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

					if (alertresult.getVtsDatamodel().get(i).getBusStopId() == Integer
							.parseInt(depotID)) {
						String ist_date[] = alertresult.getVtsDatamodel()
								.get(i).getISTDATE().split(" ");

						JSONArray ja = new JSONArray();
						ja.add(x);
						ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getHADataPerticular('"
								+ alertresult.getVtsDatamodel().get(i)
										.getDEVICEID()
								+ "','"
								+ ist_date[0]
								+ "') title='Alert Details'>"
								+ alertresult.getVtsDatamodel().get(i)
										.getLICENCENUMBER() + "</a>");
						// ja.add(alertresult.getVtsDatamodel().get(i)
						// .getLICENCENUMBER());
						// ja.add(alertresult.getVtsDatamodel().get(i)
						// .getSCHEDULENAME());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getOrgName());
						// ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getTotalcount());
						// System.out.println("Iside");
						// ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH());

						// ja.add(alertresult.getVtsDatamodel().get(i).getSIMNUMBER());
						/*
						 * ja.add(getStopList(
						 * Double.parseDouble(alertresult.getVtsDatamodel()
						 * .get(i).getLAT()),
						 * Double.parseDouble(alertresult.getVtsDatamodel()
						 * .get(i).getLONGITUDE()), session, relatedPointType));
						 */

						array.add(ja);
						x++;
					}
				}

				//
				break;
			case 3:
				// HD Report...
				alertresult = port.webGetHDData(alertID, id, fromDate, tillDate
						+ " 23:59:59", "", rbKey);
				array = new JSONArray();
				int y = 1;
				for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

					if (alertresult.getVtsDatamodel().get(i).getBusStopId() == Integer
							.parseInt(depotID)) {
						String ist_date[] = alertresult.getVtsDatamodel()
								.get(i).getISTDATE().split(" ");

						JSONArray ja = new JSONArray();
						ja.add(y);
						ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getHADataPerticular('"
								+ alertresult.getVtsDatamodel().get(i)
										.getDEVICEID()
								+ "','"
								+ ist_date[0]
								+ "') title='Alert Details'>"
								+ alertresult.getVtsDatamodel().get(i)
										.getLICENCENUMBER() + "</a>");
						// ja.add(alertresult.getVtsDatamodel().get(i)
						// .getLICENCENUMBER());
						// ja.add(alertresult.getVtsDatamodel().get(i)
						// .getSCHEDULENAME());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getOrgName());
						// ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getTotalcount());
						// System.out.println("Iside");
						// ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH());

						// ja.add(alertresult.getVtsDatamodel().get(i).getSIMNUMBER());
						/*
						 * ja.add(getStopList(
						 * Double.parseDouble(alertresult.getVtsDatamodel()
						 * .get(i).getLAT()),
						 * Double.parseDouble(alertresult.getVtsDatamodel()
						 * .get(i).getLONGITUDE()), session, relatedPointType));
						 */

						array.add(ja);
						y++;
					}
				}

				//
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				alertresult = port.webGetOverSpeedData(alertID, id, fromDate,
						tillDate + " 23:59:59", "", rbKey);
				array = new JSONArray();
				int count2=0;
				for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
					if (alertresult.getVtsDatamodel().get(i).getBusStopId() == Integer
							.parseInt(depotID)) {
						JSONArray ja = new JSONArray();
						ja.add(count2 + 1);
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getOrgName());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getSPEEDKMPH());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getISTDATE());
						ja.add(alertresult.getVtsDatamodel().get(i)
								.getSIMNUMBER());
						ja.add(getStopList(
								Double.parseDouble(alertresult
										.getVtsDatamodel().get(i).getLAT()),
								Double.parseDouble(alertresult
										.getVtsDatamodel().get(i)
										.getLONGITUDE()), session,
								relatedPointType));

						array.add(ja);
						count2++;
					}
				}
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				alertresultSos = port.webGetSosAlertReport(alertID, fromDate,
						tillDate + " 23:59:59", id, rbKey);
				array = new JSONArray();
				int count5=0;
				for (int i = 0; i < alertresultSos.getVtsDatamodel().size(); i++) {
					JSONArray ja = new JSONArray();
					if (alertresultSos.getVtsDatamodel().get(i).getBusStopId() == Integer
							.parseInt(depotID)) {
						ja.add(count5 + 1);
						ja.add(alertresultSos.getVtsDatamodel().get(i)
								.getVEHICLENO());
//						ja.add(alertresultSos.getVtsDatamodel().get(i)
//								.getOrgName());
						ja.add(alertresultSos.getVtsDatamodel().get(i).getDEVICEID());
						ja.add(alertresultSos.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						ja.add(alertresultSos.getVtsDatamodel().get(i)
								.getSIMNUMBER());
						ja.add(alertresultSos.getVtsDatamodel().get(i)
								.getISTDATE());
						ja.add(alertresultSos.getVtsDatamodel().get(i)
								.getDRIVERTOKENNO());
						ja.add(alertresultSos.getVtsDatamodel().get(i)
								.getCONDTOKENNO());
						ja.add(alertresultSos.getVtsDatamodel().get(i)
								.getSIMNUMBER());
						ja.add("--");
						array.add(ja);
						count5++;
					}
				}

				break;
			case 13:
//				System.out.println("depotID "+depotID);
				alertresultdeviated = port.webGetDeviatedData(alertID, depotID,
						fromDate, tillDate + " 23:59:59", "", rbKey);
				
				array = new JSONArray();
				for (int i = 0; i < alertresultdeviated.getVtsDatamodel()
						.size(); i++) {
					JSONArray ja = new JSONArray();
					if (alertresultdeviated.getVtsDatamodel().get(i)
							.getBusStopId() == Integer.parseInt(depotID)) {
						ja.add(i + 1);
						ja.add(alertresultdeviated.getVtsDatamodel().get(i)
								.getLICENCENUMBER());
						ja.add(alertresultdeviated.getVtsDatamodel().get(i)
								.getSCHEDULENO());

						ja.add("DEpot11");

						ja.add("Depot 11");
						ja.add("Depot 11");

						ja.add(alertresultdeviated.getVtsDatamodel().get(i)
								.getISTDATE());
						ja.add(alertresultdeviated.getVtsDatamodel().get(i)
								.getSIMNUMBER());
						String geo[] = alertresultdeviated.getVtsDatamodel()
								.get(i).getRouteExitGeocode().split(" ");
						double lat = Double.parseDouble(geo[0]);
						double lng = Double.parseDouble(geo[1]);
						ja.add(getStopList(lat, lng, session, relatedPointType));

						array.add(ja);
					}
				}
				break;
			case 14:
				alertresultarrival = port.webGetDepotArrivalTimeReport(alertID, id,
						fromDate, todate + " 23:59:59", rbKey, Integer.parseInt(depotID));
				array = new JSONArray();
				int count = 0;
				for (int i = 0; i < alertresultarrival.getVtsDatamodel().size(); i++) {
					//System.out.println("depotid"+alertresultarrival.getVtsDatamodel().get(i)
					//		.getDEPOTID());
					JSONArray ja = new JSONArray();
					/*if (alertresultarrival.getVtsDatamodel().get(i)
							.getDEPOTID() == Integer.parseInt(depotID)) {*/
						if (alertresultarrival.getVtsDatamodel().get(i)
								.getDIFF() > 15) {

							ja.add(count + 1);
							ja.add(alertresultarrival.getVtsDatamodel().get(i)
									.getVEHICLENO());
							ja.add(alertresultarrival.getVtsDatamodel().get(i)
									.getSCHEDULENO());
							ja.add(alertresultarrival.getVtsDatamodel().get(i)
									.getENDBUSSTOPNAME());
							String shift = "";
							switch (Integer.parseInt(alertresultarrival
									.getVtsDatamodel().get(i).getDutyId())) {
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

							ja.add(shift);
							ja.add(alertresultarrival.getVtsDatamodel().get(i)
									.getScheduleDeparture());
							ja.add(alertresultarrival.getVtsDatamodel().get(i)
									.getActualDeparture());
							String day=alertresultarrival.getVtsDatamodel().get(i)
									.getActualDeparture();
							
							
							if (alertresultarrival.getVtsDatamodel().get(i).getActualDeparture() == null) {
								ja.add("-");
							}else{
							SimpleDateFormat sm2 = new SimpleDateFormat("dd/MM/yyyy");
							Common cm = new Common();
							String formatteddate= cm.getDateFromPicker(day);
//							System.out.println("---"+formatteddate);
							  Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(formatteddate);  
//							    System.out.println(formatteddate+"\t"+date1);  
							    Calendar c = Calendar.getInstance();
							    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
							    String dayOfWeek = dateFormat.format(date1);
//							    System.out.println("-----"+dayOfWeek);
							    ja.add(dayOfWeek);
							}
							    
							if (alertresultarrival.getVtsDatamodel().get(i)
									.getActualDeparture() == null) {
								ja.add("-");
							} else {
								ja.add(alertresultarrival.getVtsDatamodel()
										.get(i).getArrivalTIMEDIFF());
							}
							if(alertresultarrival.getVtsDatamodel().get(i).getReason().equals("") || alertresultarrival.getVtsDatamodel().get(i).getReason() ==null)
							{
								ja.add("-");
							}else{
							ja.add(alertresultarrival.getVtsDatamodel().get(i).getReason());
							}
							array.add(ja);
							count++;
						}
						// array.add(ja);
					//}
				}
				break;
			case 15:
				break;
			case 16:
				alertresultskip = port.webGetSkippedStopReport(alertID, id, fromDate,
						todate + " 23:59:59", rbKey);
//System.out.println("to date  "+todate);
				array = new JSONArray();
				int count1 = 0;
				for (int i = 0; i < alertresultskip.getVtsDatamodel().size(); i++) {
					JSONArray ja = new JSONArray();
					
					
					//SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
					String ticketDate = "";
					ticketDate =alertresultskip.getVtsDatamodel().get(i).getISTDATE();

					String dateType[] = ticketDate.split(" ");
					String y1 = dateType[0];
					//String y2 = common.getDateFromDateTime_();

					String time = dateType[1];
					//System.out.println(time + "," + y2);
					
					//System.out.println("depotID----"+Integer.parseInt(depotID));
					//System.out.println("---"+alertresultskip.getVtsDatamodel().get(i).getOrgName());
					if (alertresultskip.getVtsDatamodel().get(i).getDEPOTID() == Integer
							.parseInt(depotID)) {
						
						ja.add(count1 + 1);
						ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleSkipStopDataPerticular('"
								+ alertresultskip.getVtsDatamodel().get(i)
										.getDEVICEID()

								+ "','"
								+ fromDate

								+ "','"
								+ Integer.parseInt(alertresultskip
										.getVtsDatamodel().get(i).getDutyId())

								+ "') title='Alert Details' id='alert_details"
								+ alertresultskip.getVtsDatamodel().get(i)
										.getPACKETCODE()
								+ "' value='"
								+ alertresultskip.getVtsDatamodel().get(i)
										.getPACKETCODE()
								+ "'>"
								+ alertresultskip.getVtsDatamodel().get(i)
										.getLICENCENUMBER() + "</a>");
						/*
						 * ja.add(alertresultskip.getVtsDatamodel().get(i)
						 * .getLICENCENUMBER());
						 */
						ja.add(alertresultskip.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						ja.add(alertresultskip.getVtsDatamodel().get(i)
								.getOrgName());
						ja.add(alertresultskip.getVtsDatamodel().get(i)
								.getISTDATE());
						ja.add(alertresultskip.getVtsDatamodel().get(i)
								.getDRIVERTOKENNO());
						String shift = "";
						switch (Integer.parseInt(alertresultskip
								.getVtsDatamodel().get(i).getDutyId())) {
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
						ja.add(shift);
						ja.add(alertresultskip.getVtsDatamodel().get(i)
								.getTotalcount());
						array.add(ja);
						count1++;
					}
				}
				break;
			case 17:
				break;
			case 18:
				//System.out.println("StartPoint" + startPoint);
				alertresultdeparture = port
						.webGetLateDepotDepartureTime(alertID, id, fromDate,
								todate + " 23:59:59", rbKey, Integer.parseInt(depotID));
				//System.out.println("in late dept");
				array = new JSONArray();
				int count3=0;
				for (int i = 0; i < alertresultdeparture.getVtsDatamodel()
						.size(); i++) {
					JSONArray ja = new JSONArray();
					
//					if (alertresultdeparture.getVtsDatamodel().get(i)
//							.getDEPOTID() == Integer.parseInt(depotID)) {
						//System.out.println("Diff----"+alertresultdeparture.getVtsDatamodel().get(i).getDIFF());
						if (alertresultdeparture.getVtsDatamodel().get(i).getDIFF()< -15) {
						//System.out.println("DEPOTID---"+alertresultdeparture.getVtsDatamodel().get(i).getDEPOTID());
						ja.add(count3 + 1);
						ja.add(alertresultdeparture.getVtsDatamodel().get(i)
								.getVEHICLENO());
						//System.out.println("vehicleno---"+alertresultdeparture.getVtsDatamodel().get(i).getVEHICLENO());
						ja.add(alertresultdeparture.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						ja.add(alertresultdeparture.getVtsDatamodel().get(i)
								.getSTARTBUSSTOPNAME());
						String shift = "";
						switch (Integer.parseInt(alertresultdeparture
								.getVtsDatamodel().get(i).getDutyId())) {
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
//						case 0:
//							shift = "Split Service";
//							break;
						case 82:
							shift = "Split Service";
							break;
						}
						ja.add(shift);
						ja.add(alertresultdeparture.getVtsDatamodel().get(i)
								.getScheduleArrival());
						ja.add(alertresultdeparture.getVtsDatamodel().get(i)
								.getActualArrival());
						String dayy=alertresultdeparture.getVtsDatamodel().get(i).getActualArrival();
						
						
						if (alertresultdeparture.getVtsDatamodel().get(i).getActualArrival() == null) {
							ja.add("-");
						}else{
						SimpleDateFormat sm2 = new SimpleDateFormat("dd/MM/yyyy");
						Common cm = new Common();
						String formatteddate= cm.getDateFromPicker(dayy);
//						System.out.println("---"+formatteddate);
						  Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(formatteddate);  
//						    System.out.println(formatteddate+"\t"+date1);  
						    Calendar c = Calendar.getInstance();
						    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
						    String dayOfWeek1 = dateFormat.format(date1);
//						    System.out.println("-------------------------------"+dayOfWeek1);
						    ja.add(dayOfWeek1);
						}
						
						if (alertresultdeparture.getVtsDatamodel().get(i)
								.getActualArrival() == null) {
							ja.add("-");
							
							
							
						} else {
							ja.add(alertresultdeparture.getVtsDatamodel()
									.get(i).getTIMEDIFF());
						}
//						if(alertresultdeparture.getVtsDatamodel().get(i).getReason().equals("-") || alertresultarrival.getVtsDatamodel().get(i).getReason() ==null)
//						{
//							ja.add("-");
//						}else{
						ja.add(alertresultdeparture.getVtsDatamodel().get(i).getReason());
//						}
						array.add(ja);
						count3++;
						}
					//}
				}
				break;
			case 19:
				break;
			case 20:
				// For Schedule Early Departure..
				schearlyDept = port.getSchEarlyDeparture(fromDate, depotID,
						rbKey);
				array = new JSONArray();
				for (int i = 0; i < schearlyDept.getVtsDatamodel().size(); i++) {
					if (schearlyDept.getVtsDatamodel().get(i).getBusStopId() == Integer
							.parseInt(depotID)) {
						JSONArray ja = new JSONArray();
						ja.add(i + 1);
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getVEHICLENO());
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getDEVICEID());
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getSTARTTIME());
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getACTSTARTTIME());
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getTIMEDIFF());
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getSTARTBUSSTOPNAME());
						ja.add(schearlyDept.getVtsDatamodel().get(i)
								.getENDBUSSTOPNAME());
						array.add(ja);
					}
				}
				break;
			case 21:
				// For Schedule Late Departure..
				schlateDept = port.getSchLateDept(fromDate, depotID, rbKey);
				array = new JSONArray();
				for (int i = 0; i < schlateDept.getVtsDatamodel().size(); i++) {
					JSONArray ja = new JSONArray();
					if (schearlyDept.getVtsDatamodel().get(i).getBusStopId() == Integer
							.parseInt(depotID)) {
						ja.add(i + 1);
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getVEHICLENO());
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getDEVICEID());
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getSCHEDULENAME());
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getSTARTTIME());
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getACTSTARTTIME());
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getTIMEDIFF());
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getSTARTBUSSTOPNAME());
						ja.add(schlateDept.getVtsDatamodel().get(i)
								.getENDBUSSTOPNAME());
						array.add(ja);
					}
				}
				break;
			case 22:
				String sql = "select vehical_id,device_id,call_time,driver_name,schedual_no,driver_token_no,accident_type "
						+ " from accident_ccc where created_date between '"
						+ fromDate
						+ " 00:00:00' and '"
						+ tillDate
						+ " 23:59:59'";
				query = session.createSQLQuery(sql)
						.addScalar("vehical_id", Hibernate.STRING)
						.addScalar("device_id", Hibernate.STRING)
						.addScalar("schedual_no", Hibernate.STRING)
						.addScalar("driver_token_no", Hibernate.STRING);
				// .addScalar("driver_name", Hibernate.STRING)
				// .addScalar("conductor_name", Hibernate.STRING)
				// .addScalar("schedual_no", Hibernate.STRING)
				// .addScalar("attended_by_name", Hibernate.STRING);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				array = new JSONArray();
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					JSONArray ja = new JSONArray();
					Map<String, Object> rs = aliasToValueMapList.get(i);
					ja.add(i + 1);
					ja.add(rs.get("vehical_id") != null ? rs.get("vehical_id")
							.toString() : "");
					ja.add(rs.get("device_id") != null ? rs.get("device_id")
							.toString() : "");
					ja.add(rs.get("schedual_no") != null ? rs
							.get("schedual_no").toString() : "");
					ja.add(rs.get("driver_token_no") != null ? rs.get(
							"driver_token_no").toString() : "");
					array.add(ja);
				}
				break;
			case 23:
				String sql1 = "SELECT vehical_id,device_id,call_time,route_no,schedual_no,driver_name,conductor_name,attended_by_name"
						+ " FROM breakdown_ccc where created_date between '"
						+ fromDate
						+ " 00:00:00' and '"
						+ tillDate
						+ " 23:59:59'";
				query = session.createSQLQuery(sql1)
						.addScalar("vehical_id", Hibernate.STRING)
						.addScalar("device_id", Hibernate.STRING)
						.addScalar("call_time", Hibernate.STRING)
						.addScalar("route_no", Hibernate.STRING)
						.addScalar("driver_name", Hibernate.STRING)
						.addScalar("conductor_name", Hibernate.STRING)
						.addScalar("schedual_no", Hibernate.STRING)
						.addScalar("attended_by_name", Hibernate.STRING);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList1 = query.list();
				array = new JSONArray();
				for (int i = 0; i < aliasToValueMapList1.size(); i++) {
					JSONArray ja = new JSONArray();
					Map<String, Object> rs = aliasToValueMapList1.get(i);
					ja.add(i + 1);
					ja.add(rs.get("vehical_id").toString());
					ja.add(rs.get("device_id").toString());
					ja.add(rs.get("call_time").toString());
					ja.add(rs.get("route_no").toString());
					ja.add(rs.get("driver_name").toString());
					ja.add(rs.get("conductor_name").toString());
					ja.add(rs.get("schedual_no").toString());
					ja.add(rs.get("attended_by_name").toString() != null ? rs
							.get("attended_by_name").toString() : "");
					array.add(ja);
				}
				break;
				
				
			case 24:
				try{
					
				alertresultStationaryVehicle = port.webGetStationaryVehicleReport(alertID,  fromDate,
						tillDate + " 23:59:59", depotID, rbKey);
				array = new JSONArray();
				int count7=0;
				String location ="";	
				System.out.println("depotID----"+Integer.parseInt(depotID)+"==="+fromDate+"===="+tillDate);
				for (int i = 0; i < alertresultStationaryVehicle.getVtsDatamodel().size(); i++) {
					//System.out.println("BusStop Id is--"+alertresultStationaryVehicle.getVtsDatamodel().get(i).getBusStopId());
//					if (alertresultStationaryVehicle.getVtsDatamodel().get(i).getBusStopId() == Integer
//							.parseInt(depotID)) {
						JSONArray ja = new JSONArray();
						ja.add(count7 + 1);
						 ja.add(alertresultStationaryVehicle.getVtsDatamodel().get(i).getLICENCENUMBER());
						 ja.add(alertresultStationaryVehicle.getVtsDatamodel().get(i).getDEVICEID());
						 ja.add(alertresultStationaryVehicle.getVtsDatamodel().get(i).getOrgName());
				        ja.add(alertresultStationaryVehicle.getVtsDatamodel().get(i).getISTDATE());
						ja.add(alertresultStationaryVehicle.getVtsDatamodel().get(i).getACTTIMEARR());
						ja.add(alertresultStationaryVehicle.getVtsDatamodel().get(i).getSCHDIFF());
						VehicleLocationReportAction vdao = new VehicleLocationReportAction();
		 location=vdao.getVehicleLocation(Double.parseDouble(alertresultStationaryVehicle.getVtsDatamodel().get(i).getLAT()),Double.parseDouble(alertresultStationaryVehicle.getVtsDatamodel().get(i).getLONGITUDE()));
					System.out.println(location);
						ja.add("KBS KBS KBS");

						array.add(ja);
						count7++;
//					}
				}
				System.out.println("For loop End");
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				break;
				
				
			case 25:
				alertresultWorkShopReport = port.webGetWorkShopReportDetails(alertID,  fromDate,
						tillDate + " 23:59:59", depotID, rbKey);
				array = new JSONArray();
				int count8=0;
//				System.out.println("depotID----"+Integer.parseInt(depotID));
				for (int i = 0; i < alertresultWorkShopReport.getVtsDatamodel().size(); i++) {
//					System.out.println("BusStop Id is--"+alertresultWorkShopReport.getVtsDatamodel().get(i).getBusStopId());
					if (alertresultWorkShopReport.getVtsDatamodel().get(i).getBusStopId() == Integer
							.parseInt(depotID)) {
						JSONArray ja = new JSONArray();
						ja.add(count8 + 1);
						 ja.add(alertresultWorkShopReport.getVtsDatamodel().get(i).getLICENCENUMBER());
				        //ja.add(alertresultStationaryVehicle.getVtsDatamodel().get(i).getISTDATE());
						ja.add(alertresultWorkShopReport.getVtsDatamodel().get(i).getDEVICEID());
						ja.add(alertresultWorkShopReport.getVtsDatamodel().get(i).getOrgName());
						

						array.add(ja);
						count8++;
					}
				}
				break;
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getVehicleDataReports(String fromdate, String tilldate,
			String depot_id, String vehicleDeviceId) {
System.out.println("coming"+vehicleDeviceId);
		JSONObject result = new JSONObject();
		try {
			boolean flag = false;
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();

			VtsResponse alertresult = port.getDistanceTravelled(rbKey,
					fromdate, tilldate, depot_id,vehicleDeviceId);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
                 ja.add(i+1);
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(alertresult.getVtsDatamodel().get(i).getACCDISTANCE());
				array.add(ja);
			}

			result.put("aaData", array);
			result.put("iTotalRecords", alertresult.getVtsDatamodel().size());

			result.put("iTotalDisplayRecords", 8);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public Map<Integer, String> getBusStop() throws Exception {
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createSQLQuery(
						"select bus_stop_id as id,bus_stop_name as busStopName from bus_stop where status!='DELETED' and point_type_id!=2 and point_type_id!=13")
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("busStopName", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(BusStops.class));

		try {
			List<BusStops> list = query.list();

			for (BusStops busStops : list) {
				resultMap.put(busStops.getId(), busStops.getBusStopName()
						.trim());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return resultMap;

		/*
		 * Map<Integer, String> resultMap = new HashMap<Integer, String>();
		 * //int parentID = 3;
		 * 
		 * HttpServletRequest request = ServletActionContext.getRequest();
		 * HttpSession sess = request.getSession(); //String userorgtype =
		 * (String) request.getSession().getAttribute("userorgtype");
		 * 
		 * Session session = HibernateUtil.getSession("hibernate.cfg.xml"); try
		 * {
		 * 
		 * String qry =
		 * "select bus_stop_id,bus_stop_name from bus_stop where status!='DELETED' and point_type_id!=2 and point_type_id!=13 limit 30"
		 * ; Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		 * query
		 * .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		 * List<Map<String, Object>> aliasToValueMapList = query.list();
		 * //resultMap.put(-1, "All"); for (int i = 0; i <
		 * aliasToValueMapList.size(); i++) {
		 * 
		 * Map<String, Object> rs = aliasToValueMapList.get(i);
		 * //System.out.println
		 * ("bus_stop_id"+Integer.parseInt(rs.get("bus_stop_id").toString()));
		 * resultMap.put(Integer.parseInt(rs.get("bus_stop_id").toString()),
		 * rs.get("bus_stop_name").toString()); } } catch (Exception ex) {
		 * ex.printStackTrace(); } finally { session.close(); }
		 * 
		 * return resultMap;
		 */
	}

	@SuppressWarnings("unchecked")
	public List<Busstop> getBusStopList(String name) {
		List<BusStops> list = null;
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		// Query query;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		List<Map<String, Object>> aliasToValueMapList = null;
		List<Busstop> list1 = new ArrayList<Busstop>();
		try {
			String qry = "select bus_stop_id,concat(bus_stop_name,'(',bus_stop_code,')' ,'(Towards:',stop_direction,')') as bus_stop_name "
					+ " from bus_stop "
					+ "where status!='DELETED' and point_type_id!=2 and point_type_id!=13 "
					+ "and stop_direction is not null and bus_stop_code is not null "
					+ "and (bus_stop_name like '"
					+ name
					+ "%') order by bus_stop_name";
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			aliasToValueMapList = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				Busstop busstop = new Busstop();
				busstop.setId(Integer
						.parseInt(rs.get("bus_stop_id").toString()));
				busstop.setValue(rs.get("bus_stop_name").toString());
				busstop.setStopName(rs.get("bus_stop_name").toString());
				list1.add(busstop);
			}
			// System.out.println("list============"+aliasToValueMapList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		// session.close();
		return list1;

	}

	public List getRouteId(String startbusStop, String endbusStop)
			throws Exception {

		List resultMap = new ArrayList();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String allRouteId = " ";

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select distinct(rm.route_id) as route_id,concat(route_number,'(',route_direction,')') as route_number,"
					+ " rt.effective_from ,rt.effective_till from route rt"
					+ " inner join route_point rm on rm.route_id=rt.route_id"
					+ " where rm.bus_stop_id in("
					+ startbusStop
					+ ","
					+ endbusStop
					+ ")"
					+ " and rt.deleted_status=0 "
					+ " and rt.effective_from != rt.effective_till "
					+ " and now() between rt.effective_from and if(rt.effective_till ='0000-00-00 00:00:00' ,now(),rt.effective_till)";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			// resultMap.put(-1, "All");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

				resultMap.add(rs.get("route_id").toString());// ,rs.get("route_number").toString());
				allRouteId += (rs.get("route_id").toString()) + ",";

			}
			// resultMap.add(allRouteId.substring(0, allRouteId.length()-1));//,
			// "All");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return resultMap;

	}

	public List getRouteNumber(String startbusStop, String endbusStop)
			throws Exception {

		List resultMap = new ArrayList();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession sess = request.getSession();
		String allRouteId = " ";

		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String qry = "select distinct(rm.route_id) as route_id,concat(route_number,'(',route_direction,')') as route_number,"
					+ " rt.effective_from ,rt.effective_till from route rt"
					+ " inner join route_point rm on rm.route_id=rt.route_id"
					+ " where rm.bus_stop_id in("
					+ startbusStop
					+ ","
					+ endbusStop
					+ ")"
					+ " and rt.deleted_status=0 "
					+ " and rt.effective_from != rt.effective_till "
					+ " and now() between rt.effective_from and if(rt.effective_till ='0000-00-00 00:00:00' ,now(),rt.effective_till)";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			// resultMap.put(-1, "All");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {

				Map<String, Object> rs = aliasToValueMapList.get(i);

				resultMap.add(rs.get("route_number").toString());// ,rs.get("route_number").toString());
				allRouteId += (rs.get("route_id").toString()) + ",";

			}
			// resultMap.add(allRouteId.substring(0, allRouteId.length()-1));//,
			// "All");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return resultMap;

	}

	/*@SuppressWarnings("unchecked")
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
				case 82:
					shift = "Split Service";
					break;
					
				}
				String depstatus = "";
				String arrivalstatus = "";
				int departurediff;
				int arrivaldiff;

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
				// End
				
				if (alertresult.getWaybillDetails().get(i).getDEPTUREDIFF() != null) {
					departurediff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getDEPTUREDIFF()
							.toString());

					if (departurediff >= 15) {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME().equals("")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
										.equals("0.00")) {
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
										.equals("0.00")) {
							depstatus = "-";
						} else {
							depstatus = "Early";
						}
					} else {
						depstatus = "Ontime";
					}
				} else {
					if (!alertresult.getWaybillDetails().get(i)
							.getACTUALDEPARTURETIME().equals("0.00")) {
						// if nullget actual diff
						double timeDiff = getTimeDiffInMin(alertresult
								.getWaybillDetails().get(i).getDUTYDT(),
								alertresult.getWaybillDetails().get(i)
										.getDEPARTURETIME(), alertresult
										.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME());
						
						if (timeDiff >= 15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALDEPARTURETIME()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("0.00")) {
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
											.equals("0.00")) {
								depstatus = "-";
							} else {
								depstatus = "Early";
							}
						} else {
							depstatus = "Ontime";
						}
					}
				}

				if (alertresult.getWaybillDetails().get(i).getARRIVALDIFF() != null) {
					arrivaldiff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getARRIVALDIFF()
							.toString());
					if (arrivaldiff >= 15) {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP().equals("")) {
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
										.equals("0.00")) {
							arrivalstatus = "-";
						} else {
							arrivalstatus = "Early";
						}
					} else {
						arrivalstatus = "Ontime";
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
										.getACTUALARRIVALTIMEDISP());
						
						if (timeDiff >= 15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP()
											.equals("")) {
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
											.equals("0.00")) {
								arrivalstatus = "-";
							} else {
								arrivalstatus = "Early";
							}
						} else {
							arrivalstatus = "Ontime";
						}

					}
				}
				ja.add(i + 1);
//				ja.add("<a data-toggle='modal' role='button' href='#mymodal123'  onclick=javascript:viewTripWithBusstopname('"
//						+ alertresult.getWaybillDetails().get(i).getId()
//						+ "') title='Track Online' id='alert_details"
//						+ "'>"
//						+ alertresult.getWaybillDetails().get(i)
//								.getSCHEDULETRIPTRIPNUMBER() + "</a>");
				
				
				if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()!=1){
				ja.add("<a  class='btn blue' role='button' href='#mymodal123'  onclick=javascript:viewTripWithonlineTicket('"
						+ selecteddate+"','"+
						alertresult.getWaybillDetails().get(i).getWAYBILLNO()+"','"+
						alertresult.getWaybillDetails().get(i).getDepotCd().replace(" ", "")+"','"+
						alertresult.getWaybillDetails().get(i).getDepotId()+"','"+
						alertresult.getWaybillDetails().get(i).getDEVICEID()+"','"+
						alertresult.getWaybillDetails().get(i).getVEHICLENO()+"','"+
						etmshift

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
				
				
//				ja.add("<a  href='#mymodal123'  onclick=javascript:viewTripWithonlineTicket('"
//						+ selecteddate
//						+","
//						+depotName.replace(" ", "")
//						+ "') title='Track Online' id='alert_details"
//						+ "'>"
//						+ alertresult.getWaybillDetails().get(i)
//								.getSCHEDULETRIPTRIPNUMBER() + "</a>");
				
				
				ja.add(shift);
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPENDBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				ja.add(alertresult
						.getWaybillDetails().get(i).getACTSTARTTIME());
				ja.add(depstatus);
				ja.add(alertresult.getWaybillDetails().get(i).getARRIVALTIME());
				ja.add(alertresult
						.getWaybillDetails().get(i).getENDTIME());
				ja.add(arrivalstatus);
				ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
				
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
									.getACTUALARRIVALTIMEDISP().equals("-") || alertresult
									.getWaybillDetails().get(i).getACTSTARTTIME().equals("-") || alertresult
									.getWaybillDetails().get(i).getENDTIME().equals("-")) {
						ja.add("-");
					} else {
						ja.add(getTimeDiff(
								alertresult.getWaybillDetails().get(i)
										.getDUTYDT(), alertresult
										.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME(), alertresult
										.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()));
					}
				}
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPDISTANCE());
				String tripstatus = alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPSTATUS();
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null
						&& alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP() != null) {
					if (alertresult.getWaybillDetails().get(i)
							.getACTUALDEPARTURETIME().equals("00:00:00")
							|| alertresult.getWaybillDetails().get(i)

							.getACTUALARRIVALTIMEDISP().equals("00:00:00")) {
						tripstatus = "Deviated";
					} else {
						

						 tripstatus=port.checkTripStatus(String.valueOf(alertresult.getWaybillDetails().get(i).getId()),
						 rbKey);
						
						if (!(alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("0.00"))
								&& !(alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00"))) {
							tripstatus = "Completed";
						} else if (alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("0.00")
								&& alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00")) {
							tripstatus = "Not Operated/Deviated";
						} else {
							tripstatus = "Partial";
						}
					}
				} else {
					tripstatus = "N/A";
				}
				tripstatus=port.checkTripStatus(String.valueOf(alertresult.getWaybillDetails().get(i).getId()),
						 rbKey);
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
							Distance = 0.0;
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
								Distance = 0.0;
							}
							ja.add(Distance);
						}
					}
				} else {
					if(alertresult.getWaybillDetails().get(i).getIsDreadTripFlag()==1){
						gpsDistance = alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPDISTANCE();
					}
				else if (tripstatus.equals("Not Operated/Deviated")) {
						gpsDistance = "0.0";
					}
					ja.add(gpsDistance);
				}
				
				ja.add("<a data-toggle='modal' role='button' href='#mymodal123'  onclick=javascript:viewTripWithBusstopname('"
						+ alertresult.getWaybillDetails().get(i).getId()
						+ "') title='Track Online' id='alert_details"
						+ "'>"
						+ tripstatus + "</a>");
				String starttime = "";
				String endtime = "";
//				endtime = (alertresult.getWaybillDetails().get(i)
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
						.getWaybillDetails().get(i).getACTUALDEPARTURETIME();

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
						+ starttime
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getTRIPSTATUS()
						+ ""
						+ "','"
						+ alertresult.getWaybillDetails().get(i).getDUTYDT()
						+ "',"
						+ alertresult.getWaybillDetails().get(i).getId()
						+ ",'"
						+ endtime
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
						+ alertresult.getWaybillDetails().get(i).getDUTYDT()
						+ "',"
						+ alertresult.getWaybillDetails().get(i).getROUTEID()
						+ ","
						+ alertresult.getWaybillDetails().get(i)
								.getSTARTPOINT()
						+ ","
						+ alertresult.getWaybillDetails().get(i).getENDPOINT()
						+ ",'"
						+ starttime
						+ "','"
						+ endtime
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
						+ alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME()
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP()
						+ "','"
						+ alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP()
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
*/
	
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
						etmshift

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
							.getWaybillDetails().get(i).getACTSTARTTIME();
					ja.add(alertresult
							.getWaybillDetails().get(i).getACTSTARTTIME());
				}
				
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
										.equals("0.00") || alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
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
										.equals("0.00") || alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
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
										.equals("0.00") || alertresult.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME()
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
										.getWaybillDetails().get(i)
										.getACTUALDEPARTURETIME());
						
						if (timeDiff >= 15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALDEPARTURETIME()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
											.equals("0.00") || alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
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
											.equals("0.00") || alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
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
											.equals("0.00") || alertresult.getWaybillDetails().get(i)
											.getACTUALDEPARTURETIME()
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
												.equals("0.00") || alertresult.getWaybillDetails().get(i)
												.getACTUALDEPARTURETIME()
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

				ja.add(depstatus);
				ja.add(alertresult.getWaybillDetails().get(i).getARRIVALTIME());
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
							.getWaybillDetails().get(i).getENDTIME();
					ja.add(alertresult
							.getWaybillDetails().get(i).getENDTIME());
				}
				
				if (alertresult.getWaybillDetails().get(i).getARRIVALDIFF() != null) {
					arrivaldiff = getTimeDiffInMinutes(alertresult.getWaybillDetails().get(i).getETMENDTIME(),alertresult.getWaybillDetails().get(i).getARRIVALTIME(),appendedact_atime);
					if (arrivaldiff >= 15) {
						if (alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP().equals("00:00:00")
								|| alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP().equals("")) {
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
										.equals("0.00")) {
							arrivalstatus = "-";
						} else {
							arrivalstatus = "Early";
						}
					} else {
						arrivalstatus = "Ontime";
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
										.getACTUALARRIVALTIMEDISP());
						
						if (timeDiff >= 15) {
							if (alertresult.getWaybillDetails().get(i)
									.getACTUALARRIVALTIMEDISP()
									.equals("00:00:00")
									|| alertresult.getWaybillDetails().get(i)
											.getACTUALARRIVALTIMEDISP()
											.equals("")) {
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
											.equals("0.00")) {
								arrivalstatus = "-";
							} else {
								arrivalstatus = "Early";
							}
						} else {
							arrivalstatus = "Ontime";
						}

					}
				}
				
				
				//End
				
				
				
				
				
				
			
				ja.add(arrivalstatus);
				
				System.out.println("appendedact_dtime"+appendedact_dtime+"\t"+"appendedact_atime"+appendedact_atime);
				
				
				ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
				
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
						ja.add(getTimeDiff(
								alertresult.getWaybillDetails().get(i)
										.getDUTYDT(), appendedact_dtime, appendedact_atime));
					}
				}
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPDISTANCE());
				String tripstatus = alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPSTATUS();
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null
						&& alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIMEDISP() != null) {
					if (alertresult.getWaybillDetails().get(i)
							.getACTUALDEPARTURETIME().equals("00:00:00")
							|| alertresult.getWaybillDetails().get(i)

							.getACTUALARRIVALTIMEDISP().equals("00:00:00")) {
						tripstatus = "Deviated";
					} else {
						

						 //tripstatus=port.checkTripStatus(String.valueOf(alertresult.getWaybillDetails().get(i).getId()),
						// rbKey);
						
						if (!(alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("0.00"))
								&& !(alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00"))) {
							tripstatus = "Completed";
						} else if (alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("0.00")
								&& alertresult.getWaybillDetails().get(i)
										.getACTUALARRIVALTIMEDISP()
										.equals("0.00")) {
							tripstatus = "Not Operated/Deviated";
						} else {
							tripstatus = "Partial";
						}
					}
				} else {
					tripstatus = "N/A";
				}
				tripstatus=alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS();//port.checkTripStatus(String.valueOf(alertresult.getWaybillDetails().get(i).getId()),	 rbKey);
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
				
				ja.add("<a data-toggle='modal' role='button' href='#mymodal123'  onclick=javascript:viewTripWithBusstopname('"
						+ alertresult.getWaybillDetails().get(i).getId()
						+ "') title='Track Online' id='alert_details"
						+ "'>"
						+ tripstatus + "</a>");
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
						+ alertresult.getWaybillDetails().get(i)
								.getTRIPSTATUS()
						+ ""
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
						+ starttime
						+ "','"
						+ endtime
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
	public String getActualStartTime(int ID) {
		String str = "";
		// Calling WebServices
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		str = port.getTripwiseStartTime(String.valueOf(ID), rbKey);

		return str;

	}

	// get waybill wise total amount end
	public static long getTimeDiffInMinutes(String dutyDate, String time1, String time2) {
		long diffMinutes = 0L;
		try {
			System.out.println("dutyDate" + dutyDate + "\t" + time1 + "\t"
					+ time2);
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

	//
	public static double randInt(double min, double max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		double randomNum = rand.nextDouble() + min;

		return randomNum;
	}

	public JSONObject getDataForScheduleReportDetails(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String id) {
		JSONObject result = new JSONObject();
		Session session = null;
		try {

			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse5 alertresult = port.webGetTripWiseBusStopDetails(
					Integer.parseInt(id), rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();
				// Map<String, Object> rs = aliasToValueMapList.get(i);
				int bus_stop_id = alertresult.getWaybillDetails().get(i)
						.getBUSSTOPID();
				ja.add(i + 1);
				ja.add(getBustopName(bus_stop_id));
				ja.add(alertresult.getWaybillDetails().get(i).getENTRYTIME());
				ja.add(alertresult.getWaybillDetails().get(i).getEXITTIME());
				if (alertresult.getWaybillDetails().get(i).getENTRYTIME()
						.equals("00:00:00")
						&& alertresult.getWaybillDetails().get(i).getEXITTIME()
								.toString().equals("00:00:00")) {
					ja.add("Gps Data N/A,Not Operated");
				} else {
					ja.add("operated");
				}
				ja.add(alertresult.getWaybillDetails().get(i).getDISTANCE());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			//HibernateUtilVtu.closeSessionFactory();
		}
		return result;

	}

	public String getBustopName(int bus_stop_id) {
		String stopName = "";
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select bus_stop_name from bus_stop where bus_stop_id="
					+ bus_stop_id + " limit 1";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				stopName = query.uniqueResult().toString();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return stopName;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getRouteForBusBunching(int route_id,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Session session = null;

		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			session = HibernateUtil.getSession("");
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "select bs.bus_stop_id,latitude_current,longitude_current,path,route_number from route r"
					+ " inner join route_map rm on r.route_id=rm.route_id"
					+ " inner join bus_stop bs on bs.bus_stop_id=r.start_point_id"
					+ " where r.route_id="
					+ route_id
					+ " and r.deleted_status=0 and"
					+ " r.effective_from< now() and if(r.effective_till='0000-00-00 00:00:00',now(),r.effective_till)>=now()";
			Query query = session.createSQLQuery(sql)
					.addScalar("bus_stop_id", Hibernate.STRING)
					.addScalar("latitude_current", Hibernate.STRING)
					.addScalar("longitude_current", Hibernate.STRING)
					.addScalar("path", Hibernate.STRING)
					.addScalar("route_number", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(rs.get("bus_stop_id").toString());
				ja.add(rs.get("latitude_current").toString());
				ja.add(rs.get("longitude_current").toString());
				ja.add(rs.get("path").toString());
				ja.add(rs.get("route_number").toString());

				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	public JSONObject getTripVtuPacketReport(int j, HttpServletRequest req,
			String string, String string2, String selectedDate,
			String scheduleno) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetVtuPacketRecievedCount(
					selectedDate, scheduleno, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				String shift = "";
				switch (alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDUTYTYPEID()) {
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
				String depstatus = "";
				String arrivalstatus = "";
				int departurediff;
				int arrivaldiff;

				if (alertresult.getWaybillDetails().get(i).getDEPTUREDIFF() != null) {
					departurediff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getDEPTUREDIFF()
							.toString());
					if (departurediff > 0) {
						depstatus = "Late";
					} else if (departurediff < 0) {
						depstatus = "Early";
					} else if (departurediff == 0) {
						depstatus = "Ontime";
					} else {
						depstatus = "";
					}
				}
				if (alertresult.getWaybillDetails().get(i).getARRIVALDIFF() != null) {
					arrivaldiff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getARRIVALDIFF()
							.toString());
					if (arrivaldiff > 0) {
						arrivalstatus = "Late";
					} else if (arrivaldiff < 0) {
						arrivalstatus = "Early";
					} else if (arrivaldiff == 0) {
						arrivalstatus = "Ontime";
					} else {
						arrivalstatus = "";
					}
				}
				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getDEVICEID());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPTRIPNUMBER());
				ja.add(shift);
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPENDBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i).getARRIVALTIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALARRIVALTIME());
				// ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());
				// ja.add(alertresult.getWaybillDetails().get(i).getTIMEDURATION());
				// ja.add(alertresult.getWaybillDetails().get(i)
				// .getSCHEDULEDTRIPDISTANCE());
				// /*String gpsDistance = alertresult.getWaybillDetails().get(i)
				// .getTRIPDISTANCE();
				// String schDistance = alertresult.getWaybillDetails().get(i)
				// .getSCHEDULEDTRIPDISTANCE();
				// if (Double.parseDouble(gpsDistance) <= 0) {
				// ja.add(alertresult.getWaybillDetails().get(i)
				// .getSCHEDULEDTRIPDISTANCE());
				//
				// } else if ((Double.parseDouble(gpsDistance) - Double
				// .parseDouble(schDistance)) < -10
				// || (Double.parseDouble(gpsDistance) - Double
				// .parseDouble(schDistance)) > 10) {
				// ja.add(String.format(
				// "%.1f",
				// randInt(Double.parseDouble(schDistance),
				// Double.parseDouble(gpsDistance))));
				// } else if (alertresult.getWaybillDetails().get(i)
				// .getACTUALDEPARTURETIME() != null
				// && alertresult.getWaybillDetails().get(i)
				// .getACTUALARRIVALTIME() != null) {
				// //ja.add(0.0);
				// } else {
				// //ja.add(gpsDistance);
				// }*/
				String stime = (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null)
						|| (!alertresult.getWaybillDetails().get(i)
								.getACTUALDEPARTURETIME().equals("00:00:00")) ? alertresult
						.getWaybillDetails().get(i).getACTUALDEPARTURETIME()
						: alertresult.getWaybillDetails().get(i)
								.getDEPARTURETIME();
				String etime = alertresult.getWaybillDetails().get(i)
						.getACTUALARRIVALTIME() != null ? alertresult
						.getWaybillDetails().get(i).getACTUALARRIVALTIME()
						: alertresult.getWaybillDetails().get(i)
								.getARRIVALTIME();
				int count = 0;
				if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null
						|| alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIME() != null) {
					count = alertresult.getWaybillDetails().get(i)
							.getTotalcount();
					ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal7'  onclick=javascript:getPacketReceived('"
							+ alertresult.getWaybillDetails().get(i)
									.getDEVICEID()
							+ "','"
							+ stime
							+ "','"
							+ etime

							+ "') "
							+ "title='Alert Details' id='alert_details' value=''>"
							+ count + "</a>");
				} else {
					count = 0;
					ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal7'  onclick=javascript:getPacketReceived('"
							+ alertresult.getWaybillDetails().get(i)
									.getDEVICEID()
							+ "','"
							+ "00:00:00"
							+ "','"
							+ "00:00:00"

							+ "') "
							+ "title='Alert Details' id='alert_details' value=''>"
							+ count + "</a>");
				}

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getTripVtuPacketRecieved(HttpServletRequest req,
			String deviceId, String selectedDate, String startTime,
			String endTime) {

		JSONObject result = new JSONObject();
		Session session = null;
		try {

			boolean flag = false;
			String trip_status = "";
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetVtuPacketRecievedDetails(
					deviceId, selectedDate, startTime, endTime);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				ja.add(getStopList(
						Double.parseDouble(alertresult.getVtsDatamodel().get(i)
								.getLAT()),
						Double.parseDouble(alertresult.getVtsDatamodel().get(i)
								.getLONGITUDE()), session, relatedPointType));
				ja.add(alertresult.getVtsDatamodel().get(i).getCREATEDDATE());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForScheduleDeviaton(int j, HttpServletRequest req,
			String string, String string2, String selectedDate,
			String scheduleno, String tripno) {

		JSONObject result = new JSONObject();
		Session session = null;
		double exitkm = 0.00;
		double entrykm = 0.00;
		double deviatedistance = 0.00;
		try {
			DecimalFormat df1 = new DecimalFormat("0.00");
			boolean flag = false;
			String trip_status = "";
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetScheduleDeviationData(
					selectedDate, scheduleno, tripno, rbKey);
			System.out.println("Size" + alertresult.getVtsDatamodel().size());
			// VtsResponse deviationresult =
			// port.webGetScheduleDeviationData(selectedDate, scheduleno,
			// rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				/*
				 * String shift = ""; int dutyid=
				 * Integer.parseInt(alertresult.getVtsDatamodel
				 * ().get(i).getDutyId()); System.out.println("DutyId"+dutyid);
				 * switch (dutyid) { case 1: shift = "General Shift"; break;
				 * case 2: shift = "Day 1"; break; case 3: shift = "Day 2";
				 * break; case 4: shift = "Shift 1"; break; case 5: shift =
				 * "Shift 2"; break; case 6: shift = "Day 1 old"; break; case 7:
				 * shift = "Day 2 old"; break; case 78: shift = "Night Service";
				 * break; case 79: shift = "Shift 1"; break; case 80: shift =
				 * "Shift 2"; break; }
				 */ja.add(i + 1);
				/*
				 * ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				 * ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				 * ja.add(alertresult.getVtsDatamodel().get(i).getTripNumber());
				 * ja.add(shift);
				 */
				// ja.add();
				exitkm = Double.parseDouble(alertresult.getVtsDatamodel()
						.get(i).getRouteExitKm());

				entrykm = Double.parseDouble(alertresult.getVtsDatamodel()
						.get(i).getRouteEntryKm());
				// / System.out.println("Exit"+exitkm+"ENTr"+entrykm);
				// deviatedistance=((entrykm-exitkm)*1.6);
				if (alertresult.getVtsDatamodel().get(i).getRouteEntryKm()
						.equals("0")) {

					ja.add(0.00);
				} else {
					deviatedistance = ((entrykm - exitkm) / 1000);
					df1.format(deviatedistance);
					ja.add(df1.format(deviatedistance));
				}

				// exitkm

				// ja.add(alertresult.getVtsDatamodel().get(i).getRouteExitTm());
				String geo[] = alertresult.getVtsDatamodel().get(i)
						.getRouteExitGeocode().split(" ");
				double lat = Double.parseDouble(geo[0]);
				double lng = Double.parseDouble(geo[1]);
				ja.add(getStopList(lat, lng, session, relatedPointType));
				// ja.add(alertresult.getVtsDatamodel().get(i).getRouteEntryKm());
				// ja.add(alertresult.getVtsDatamodel().get(i).getRouteEntryTm());
				String geocodentry = alertresult.getVtsDatamodel().get(i)
						.getRouteEntryGeocode();

				// / System.out.println("Geovoe"+geocodentry);
				// list.get(i).getGeo_fence()!=null? "Available":"Not Available"
				// geocodentry.equals("")||geocodentry==null||geocodentry.contains("null")
				if (alertresult.getVtsDatamodel().get(i).getRouteEntryGeocode()
						.equals("-")) {
					ja.add(" ");
				} else {
					// ja.add(alertresult.getVtsDatamodel().get(i).getRouteEntryGeocode()!=null?
					String geoe[] = alertresult.getVtsDatamodel().get(i)
							.getRouteExitGeocode().split(" ");
					double lat1 = Double.parseDouble(geo[0]);
					double lng2 = Double.parseDouble(geo[1]);
					ja.add(getStopList(lat, lng, session, relatedPointType));
				}

				// ja.add(alertresult.getWaybillDetails().get(i).getTIMEDURATION());
				// ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPDISTANCE());
				// ja.add(alertresult.getWaybillDetails().get(i).getTRIPDISTANCE());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}

		return result;
	}

	public JSONObject getDataForScheduleDevaitionInfo(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse6 alertresult = port.webGetScheduleDeviationInfo(
					selectedDate, scheduleno, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				String shift = "";
				switch (alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDUTYTYPEID()) {
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
				String depstatus = "";
				String arrivalstatus = "";
				int departurediff;
				int arrivaldiff;

				if (alertresult.getWaybillDetails().get(i).getDEPTUREDIFF() != null) {
					departurediff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getDEPTUREDIFF()
							.toString());
					if (departurediff > 0) {
						depstatus = "Late";
					} else if (departurediff < 0) {
						depstatus = "Early";
					} else if (departurediff == 0) {
						depstatus = "Ontime";
					} else {
						depstatus = "";
					}
				}
				if (alertresult.getWaybillDetails().get(i).getARRIVALDIFF() != null) {
					arrivaldiff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getARRIVALDIFF()
							.toString());
					if (arrivaldiff > 0) {
						arrivalstatus = "Late";
					} else if (arrivaldiff < 0) {
						arrivalstatus = "Early";
					} else if (arrivaldiff == 0) {
						arrivalstatus = "Ontime";
					} else {
						arrivalstatus = "";
					}
				}

				ja.add(i + 1);
				ja.add("<a data-toggle='modal' role='button' href='#deviationinfo1'  onclick=javascript:getDeviatedScheduleInfo('"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULETRIPTRIPNUMBER()
						+ "') title='Track Online' id='alert_details"
						+ "'>"
						+ alertresult.getWaybillDetails().get(i)
								.getSCHEDULETRIPTRIPNUMBER() + "</a>");

				// ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULETRIPTRIPNUMBER());
				ja.add(shift);
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPENDBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME());
				ja.add(depstatus);
				ja.add(alertresult.getWaybillDetails().get(i).getARRIVALTIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALARRIVALTIME());
				ja.add(arrivalstatus);
				ja.add(alertresult.getWaybillDetails().get(i).getTIMEDURATION());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPDISTANCE());
				String gpsDistance = alertresult.getWaybillDetails().get(i)
						.getTRIPDISTANCE();
				String schDistance = alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPDISTANCE();
				if (Double.parseDouble(gpsDistance) <= 0) {
					ja.add(alertresult.getWaybillDetails().get(i)
							.getSCHEDULEDTRIPDISTANCE());

				} else if ((Double.parseDouble(gpsDistance) - Double
						.parseDouble(schDistance)) < -10
						|| (Double.parseDouble(gpsDistance) - Double
								.parseDouble(schDistance)) > 10) {
					ja.add(String.format(
							"%.1f",
							randInt(Double.parseDouble(schDistance),
									Double.parseDouble(gpsDistance))));
				} else if (alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null
						&& alertresult.getWaybillDetails().get(i)
								.getACTUALARRIVALTIME() != null) {
					ja.add(0.0);
				} else {
					ja.add(gpsDistance);
				}
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULEDTRIPSTATUS());
				/*
				 * if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS
				 * ().equals("Partial")||alertresult.getWaybillDetails().get(i).
				 * getSCHEDULEDTRIPSTATUS().equals("Cancelled")) { ja.add(
				 * "<input type='checkbox' data-toggle='modal' role='button' href='#deviationinfo' onclick=javascript:getsghhh('"
				 * + selectedDate + "') id='deviated' />"); }
				 * 
				 * else { ja.add(""); }
				 */

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// public String getDataForScheduleName(int i, HttpServletRequest req,
	// String string, String string2, String selectedDate, String scheduleno) {
	// // TODO Auto-generated method stub
	// WsUtil_Service service1 = new WsUtil_Service();
	// WsUtil port = service1.getWsUtilPort();
	// String schedulename="";
	// // TODO initialize WS operation arguments here
	// VtsResponse6 alertresult1 =
	// port.webGetScheduleTripSTatusReport(selectedDate, scheduleno, rbKey);
	// //System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
	//
	//
	// case 1:
	// schedulename = "General Shift";
	// break;
	// case 2:
	// schedulename = "Night Out";
	// break;
	// case 6:
	// schedulename = "Day Out";
	// break;
	// case 8:
	// schedulename = "Night Service";
	// break;
	// case 11:
	// schedulename = "Split  Duty";
	// break;
	// case 12:
	// schedulename = "Night Out";
	// break;
	// case 13:
	// schedulename = "fyfhj";
	// break;
	//
	// }
	//
	// return schedulename;
	// }
	public JSONObject getScheduleHeader(int formFourId) {
		JSONObject result = new JSONObject();
		String arr[] = getScheduleHeaderDetails(formFourId);
		JSONArray headrearray = new JSONArray();
		headrearray.add(arr[0]);
		headrearray.add(arr[1]);
		headrearray.add(arr[2]);
		headrearray.add(arr[3]);
		headrearray.add(arr[4]);
		result.put("bbData", headrearray);
		return result;
	}

	public static String[] getScheduleHeaderDetails(int formFourID) {
		String[] arr = new String[5];
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery(
							"select ff.schedule_number_name,ss.effective_start_date,"
									+ " if(ss.effective_end_date is null,'',ss.effective_end_date) effective_end_date ,st.schedule_type_name,"
									+ " concat(rt.route_number,' ',rt.route_direction) route_name from form_four ff"
									+ " inner join schedule ss on ff.schedule_number_id=ss.schedule_id"
									+ " inner join schedule_type st on st.schedule_type_id=ss.schedule_service_type"
									+ " inner join route rt on rt.route_id=ff.route_id where form_four_id="
									+ formFourID)
					.addScalar("schedule_number_name", Hibernate.STRING)
					.addScalar("effective_start_date", Hibernate.STRING)
					.addScalar("effective_end_date", Hibernate.STRING)
					.addScalar("schedule_type_name", Hibernate.STRING)
					.addScalar("route_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				arr[0] = rs.get("schedule_number_name") != null ? rs.get(
						"schedule_number_name").toString() : "";
				arr[1] = rs.get("effective_start_date") != null ? rs.get(
						"effective_start_date").toString() : "";
				arr[2] = rs.get("effective_end_date") != null ? rs.get(
						"effective_end_date").toString() : "";
				arr[3] = rs.get("schedule_type_name") != null ? rs.get(
						"schedule_type_name").toString() : "";
				arr[4] = rs.get("route_name") != null ? rs.get("route_name")
						.toString() : "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return arr;
	}

	public JSONObject getDataForSosDetails(String device_ID, String fromDate) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
		com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
		// TODO initialize WS operation arguments here
		model.jaxb.xml.trimax.com.VtsResponse5 alertresult = port
				.webGetSosAlertDetailsData(fromDate, fromDate, device_ID, rbKey);
//		System.out.println("==== "+alertresult.getVtsDatamodel().get(i).getSCHEDULENO());
		JSONArray array = new JSONArray();
		for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
			JSONArray ja = new JSONArray();
			ja.add(i + 1);
			ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
			ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
			ja.add("UnAttended");
			//ja.add("Change Vehicle");
//			System.out.println("@@@@0000 "+alertresult.getVtsDatamodel().get(i).getSCHEDULENO());
//			System.out.println("name is--"+alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
			ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal2'  onclick=javascript:getVehicleSosChangeDetails('"
					+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()

			+ "','"
			+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

			+ "','"
			+ alertresult.getVtsDatamodel().get(i).getDEPOTID()
			+ "','"
            +alertresult.getVtsDatamodel().get(i).getDRIVERTOKENNO()
			+ "','"+alertresult.getVtsDatamodel().get(i).getSCHEDULENO()+"') title='Alert Details' id='alert_details"
			+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
			+ "' value='"
			+ alertresult.getVtsDatamodel().get(i).getVEHICLENO()
			+ "'>"
			+"Change Vehicle"+ "</a>");

			ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
			ja.add(alertresult.getVtsDatamodel().get(i).getOrgName());
			ja.add(alertresult.getVtsDatamodel().get(i).getSIMNUMBER());
			ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
			ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
			ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERTOKENNO());
			ja.add(alertresult.getVtsDatamodel().get(i).getCONDTOKENNO());
			ja.add(alertresult.getVtsDatamodel().get(i).getSIMNUMBER());
			array.add(ja);
		}
		result.put("aaData", array);

		return result;
	}

	// extract trip list report start
	@SuppressWarnings("unchecked")
	public JSONObject getDataForExtractTripStatusReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String depotname) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse alertresult = port.webGetExtractTripData(selectedDate,
					depotname, rbKey);
			JSONArray array = new JSONArray();

			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				if (alertresult.getVtsDatamodel().get(i).getSCHEDULENAME() == null
						|| alertresult.getVtsDatamodel().get(i)
								.getSCHEDULENAME() == "") {
					ja.add("<a data-toggle='modal' role='button' href='#mymodal123'  onclick=javascript:viewScheduleWiseExtractReport('"
							+ alertresult.getVtsDatamodel().get(i)
									.getSCHEDULENO()
							+ "','"
							+ selectedDate
							+ "','"
							+ alertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().replace(" ", "")
							+ "' ) title='Track Online'  id='alert_details"
							+ "'>" + "-" + "</a>");

				} else {
					ja.add("<a data-toggle='modal' role='button' href='#mymodal123'  onclick=javascript:viewScheduleWiseExtractReport('"
							+ alertresult.getVtsDatamodel().get(i)
									.getSCHEDULENO()
							+ "','"
							+ selectedDate
							+ "','"
							+ alertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME().replace(" ", "")
							+ "') title='Track Online' id='alert_details"
							+ "'>"
							+ alertresult.getVtsDatamodel().get(i)
									.getSCHEDULENAME() + "</a>");

				}

				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getSTARTTIME());
				ja.add(alertresult.getVtsDatamodel().get(i).getENDTIME());
				ja.add(Math.round(alertresult.getVtsDatamodel().get(i)
						.getSCHDISTANCE()));
				ja.add(alertresult.getVtsDatamodel().get(i)
						.getACCUMULATEDDISTANCE());
				array.add(ja);
				// System.out.println("ja..............."+ja );
			}
			result.put("aaData", array);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	// schedule wise data

	public JSONObject getScheduleWiseReportDetails(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno) {
		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			VtsResponse alertresult = port.webGetScheduleExtractTripData(
					selectedDate, scheduleno, rbKey);
			JSONArray array = new JSONArray();

			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getTRIPNUMBER());
				String shift = "";
				switch (alertresult.getVtsDatamodel().get(i).getDutyTypeId()) {
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
				ja.add(shift);
				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHDISTANCE());
				ja.add(alertresult.getVtsDatamodel().get(i)
						.getACCUMULATEDDISTANCE());
				ja.add(alertresult.getVtsDatamodel().get(i).getDRIVERNAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getCONDNAME());
				array.add(ja);
			}

			result.put("aaData", array);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// end
	public JSONObject getDataForUnAuthorizedBreakLocationReport(String depotNo,
			String selectedDate, String fromDate, String toDate) {

		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			// session =new HibernateUtilVtu().getSessionFactory();
			// org.hibernate.Transaction transaction =
			// session.beginTransaction();
			// String sql="SELECT A.*, COUNT(1) as vehiclecount " +
			// " FROM (" +
			// " SELECT wtp.DUTY_DT, wtp.SCHEDULE_NAME, wtp.VEHICLE_NO, aub.DEVICE_ID,"
			// +
			// " wtp.duty_type_id, aub.DEPOT_CD, wtp.COND_NAME, wtp.DRIVER_NAME "
			// +
			// " FROM alert_unauth_brk aub " +
			// " INNER JOIN waybill_trip_details wtp " +
			// "  ON wtp.DEVICE_ID = aub.DEVICE_ID " +
			// " AND wtp.DUTY_DT = DATE(aub.START_TM) " +
			// " AND wtp.depot_id = aub.DEPOT_CD  " +
			// " WHERE  wtp.DUTY_DT ='"+selectedDate+"'" +
			// " and aub.DEPOT_CD='"+depotNo+"'" +
			// " GROUP BY aub.ID " +
			// " ) as A " +
			// " GROUP BY A.DEVICE_ID";
			//
			// Query query = session.createSQLQuery(sql)
			// .addScalar("VEHICLE_NO", Hibernate.STRING)
			// .addScalar("DRIVER_NAME", Hibernate.STRING)
			// .addScalar("COND_NAME", Hibernate.STRING)
			// .addScalar("duty_type_id", Hibernate.STRING)
			// .addScalar("DEVICE_ID", Hibernate.STRING)
			// .addScalar("vehiclecount", Hibernate.STRING)
			// .addScalar("SCHEDULE_NAME", Hibernate.STRING);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse uaresult = port
					.getDataForUnAuthorizedBreakLocationReport(depotNo,
							selectedDate, fromDate, toDate, rbKey);
			// query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			// List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < uaresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				// Map<String, Object> rs = aliasToValueMapList.get(i);
				String shift = "";
				ja.add(i + 1);
				if (uaresult.getVtsDatamodel().get(i).getVEHICLENO() != null) {
					ja.add(uaresult.getVtsDatamodel().get(i).getVEHICLENO()
							.toString());
				} else {
					ja.add("");
				}
				if (uaresult.getVtsDatamodel().get(i).getDEVICEID() != null) {
					ja.add("<a data-toggle='modal' role='button' href='#mymodal1234'  onclick=javascript:viewBreakLocations('"
							+ uaresult.getVtsDatamodel().get(i).getDEVICEID()
									.toString()
							+ "--"
							+ selectedDate
							+ "') title='Break Location' id='break_location"
							+ "'>"
							+ uaresult.getVtsDatamodel().get(i).getTotalcount()
							+ "</a>");
				} else {
					ja.add("");
				}
				if (uaresult.getVtsDatamodel().get(i).getDRIVERNAME() != null) {
					ja.add(uaresult.getVtsDatamodel().get(i).getDRIVERNAME()
							.toString());
				} else {
					ja.add("");
				}
				if (uaresult.getVtsDatamodel().get(i).getCONDNAME() != null) {
					ja.add(uaresult.getVtsDatamodel().get(i).getCONDNAME()
							.toString());
				} else {
					ja.add("");
				}
				if (uaresult.getVtsDatamodel().get(i).getSCHEDULENAME() != null) {
					ja.add(uaresult.getVtsDatamodel().get(i).getSCHEDULENAME()
							.toString());
				} else {
					ja.add("");
				}
				switch (uaresult.getVtsDatamodel().get(i).getDutyTypeId()) {
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
				ja.add(shift);
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;

	}

	public JSONObject getDataForUnAuthorizedBreakLocations(String deviceId,
			String fromDate, String toDate) {
		Session session = null;

		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			// session =new HibernateUtilVtu().getSessionFactory();
			// org.hibernate.Transaction transaction =
			// session.beginTransaction();
			// String
			// sql="select DEVICE_ID,DATE_FORMAT(START_TM,'%d-%m-%Y %H:%i:%s') START_TM,START_LAT,START_LONG,"
			// +
			// " DATE_FORMAT(END_TM,'%d-%m-%Y %H:%i:%s') END_TM from alert_unauth_brk where DEVICE_ID='"+deviceId+"' and START_TM between '"+fromDate+"' and '"+toDate+"'";
			// Query query = session.createSQLQuery(sql)
			// .addScalar("DEVICE_ID", Hibernate.STRING)
			// .addScalar("START_TM", Hibernate.STRING)
			// .addScalar("START_LAT", Hibernate.STRING)
			// .addScalar("START_LONG", Hibernate.STRING)
			// .addScalar("END_TM", Hibernate.STRING);
			//
			// query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			// List<Map<String, Object>> aliasToValueMapList = query.list();
			Common cm = new Common();
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query1 = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query1.list().size() > 0) {
				relatedPointType = query1.uniqueResult().toString();
			}
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse uaresult = port.getDataForUnAuthorizedBreakLocations(
					deviceId, fromDate, toDate, rbKey);
			double lat1 = 0.0;
			double lng2 = 0.0;
			JSONArray array = new JSONArray();
			for (int i = 0; i < uaresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				// Map<String, Object> rs = aliasToValueMapList.get(i);
				String shift = "";
				ja.add(i + 1);
				if (uaresult.getVtsDatamodel().get(i).getDEVICEID() != null) {
					ja.add(uaresult.getVtsDatamodel().get(i).getDEVICEID()
							.toString());
				} else {
					ja.add("");
				}
				if (uaresult.getVtsDatamodel().get(i).getSTARTTM() != null) {
					ja.add(uaresult.getVtsDatamodel().get(i).getSTARTTM()
							.toString());
				} else {
					ja.add("");
				}
				if (uaresult.getVtsDatamodel().get(i).getENDTM() != null) {
					ja.add(uaresult.getVtsDatamodel().get(i).getENDTM()
							.toString());
				} else {
					ja.add("");
				}
				if (uaresult.getVtsDatamodel().get(i).getSTARTLAT() != null) {
					lat1 = Double.parseDouble(uaresult.getVtsDatamodel().get(i)
							.getSTARTLAT().toString());
				}
				if (uaresult.getVtsDatamodel().get(i).getSTARTLONG() != null) {
					lng2 = Double.parseDouble(uaresult.getVtsDatamodel().get(i)
							.getSTARTLONG().toString());
				}

				String loc = getLocation(lat1, lng2, relatedPointType);
				if (loc != null) {
					ja.add(loc);
				} else {
					ja.add("");
				}
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;

	}

	public String getLocation(double l1, double l2, String relatedPointType) {
		Session session = null;
		String location = "";
		try {
			session = HibernateUtil.getSession("");
			location = getStopList(l1, l2, session, relatedPointType);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return location;
		}
	}

	public JSONObject getDepotDeptDetails(String device_id, String selecteTime,
			String vehicle_no) {
		JSONObject result = new JSONObject();
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		VtsResponse depotresult = port.getDepotDeptDetails(selecteTime,
				device_id, rbKey);
		JSONArray array = new JSONArray();
		for (int i = 0; i < depotresult.getVtsDatamodel().size(); i++) {
			JSONArray ja = new JSONArray();
			ja.add(i + 1);
			ja.add(vehicle_no);
			ja.add(depotresult.getVtsDatamodel().get(i).getDEPOTEXITTM());
			array.add(ja);
		}
		result.put("aaData", array);
		return result;
	}

	public JSONObject getDepotArrDetails(String device_id, String selecteTime,
			String vehicle_no) {
		JSONObject result = new JSONObject();
		WsUtil_Service service = new WsUtil_Service();
		WsUtil port = service.getWsUtilPort();
		VtsResponse depotresult = port.getDepotArrDetails(selecteTime,
				device_id, rbKey);
		JSONArray array = new JSONArray();
		for (int i = 0; i < depotresult.getVtsDatamodel().size(); i++) {
			JSONArray ja = new JSONArray();
			ja.add(i + 1);
			ja.add(vehicle_no);
			ja.add(depotresult.getVtsDatamodel().get(i).getDEPOTENTRYTM());
			array.add(ja);
		}
		result.put("aaData", array);
		return result;
	}

	public JSONObject getDataForAccidentAnalysisList(String depotNo,
			String selectedDate, String fromDate, String toDate, String accType) {
		Session session = null;
		String depot_param = "";
		if (!depotNo.equals("-1")) {
			depot_param = "and depot_id=" + depotNo;
		} else {
			depot_param = "and depot_id!=0";
		}
		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "select vehical_id,device_id,call_time,driver_name,schedual_no,driver_token_no,accident_type "
					+ " from accident_ccc where created_date between '"
					+ fromDate
					+ " 00:00:00' "
					+ " and '"
					+ toDate
					+ " 23:59:59' and accident_type_id='"
					+ accType
					+ "' "
					+ depot_param;

			Query query = session.createSQLQuery(sql)
					.addScalar("vehical_id", Hibernate.STRING)
					.addScalar("device_id", Hibernate.STRING)
					.addScalar("call_time", Hibernate.STRING)
					.addScalar("driver_name", Hibernate.STRING)
					.addScalar("accident_type", Hibernate.STRING)
					.addScalar("schedual_no", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				String shift = "";
				ja.add(i + 1);
				ja.add(rs.get("vehical_id"));
				ja.add(rs.get("schedual_no"));
				ja.add(rs.get("driver_name"));
				ja.add(rs.get("driver_token_no"));
				ja.add(rs.get("accident_type"));
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return result;

	}

	public JSONObject getDataForBreakDown(String depotNo, String selectedDate,
			String brkType) {
		Session session = null;
		String depot_param = "";
		if (!depotNo.equals("-1")) {
			depot_param = "and depot_id=" + depotNo;
		} else {
			depot_param = "and depot_id!=0";
		}
		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "select vehical_id,driver_token_no,schedual_no,st.shift_type_name,bcc.breakdown_type,"
					+ " if(bcc.lattitude is null,0,bcc.lattitude) lattitude,if(bcc.longitude is null,0,bcc.longitude) longitude,bcc.notes"
					+ " from breakdown_ccc  bcc left join shift_type st on bcc.shift_type=st.shift_type_id"
					+
					// " inner join breakdown_type_details btd " +
					// " on btd.breakdown_type_Id=bcc.breakdown_type_id" +
					" where bcc.created_date between '"
					+ selectedDate
					+ " 00:00:00' "
					+ " and '"
					+ selectedDate
					+ " 23:59:59' "
					+ depot_param;

			Query query = session.createSQLQuery(sql)
					.addScalar("vehical_id", Hibernate.STRING)
					.addScalar("driver_token_no", Hibernate.STRING)
					.addScalar("shift_type_name", Hibernate.STRING)
					.addScalar("schedual_no", Hibernate.STRING)
					.addScalar("lattitude", Hibernate.STRING)
					.addScalar("longitude", Hibernate.STRING)
					.addScalar("notes", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			String relatedPointType = "";
			Query query1 = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query1.list().size() > 0) {
				relatedPointType = query1.uniqueResult().toString();
			}
			String locationName = "";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				String shift = "";
				ja.add(i + 1);
				ja.add(rs.get("vehical_id"));
				ja.add(rs.get("driver_token_no"));
				ja.add(rs.get("schedual_no"));
				ja.add(rs.get("shift_type_name"));
				if (!(rs.get("lattitude").toString().equals("0"))
						&& !(rs.get("longitude").toString().equals("0"))) {
					locationName = getStopList(
							Double.parseDouble(rs.get("lattitude").toString()),
							Double.parseDouble(rs.get("longitude").toString()),
							session, relatedPointType);
				}
				ja.add(locationName);
				ja.add(rs.get("notes"));
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return result;

	}

	public List getAccidentLocation(String deviceid, String calltime) {
		String location = "";

		List l1 = null;
		try {

			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetAccidentAnalysisData(deviceid,
					calltime, rbKey);

			l1 = new ArrayList<String>();
			for (int i = 0; i < accresult.getVtsDatamodel().size(); i++) {

				String shift = "";

				double lat1 = accresult.getVtsDatamodel().get(i)
						.getACCRPTLATITUDE();
				double lng2 = accresult.getVtsDatamodel().get(i)
						.getACCRPTLONGITUDE();
				String loc = getACCLocation(lat1, lng2);
				l1.add(0, loc);

				l1.add(1, accresult.getVtsDatamodel().get(i)
						.getACCRPTSPEEDKMPH());

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return l1;
	}

	public String getACCLocation(double l1, double l2) {
		Session session = null;
		String location = "";
		String relatedPointType = "";
		try {
			session = HibernateUtil.getSession("");
			Query query1 = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query1.list().size() > 0) {
				relatedPointType = query1.uniqueResult().toString();
			}
			location = getStopList(l1, l2, session, relatedPointType);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return location;
		}
	}

	// Sarthi Deviation Report Data
	public JSONObject getDataForSarthiDevaitionData(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetSarthiLCDeviationInfo(
					selectedDate, scheduleno, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();

				ja.add(i + 1);
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getSarthiDeviatedInfo('"
						+ alertresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "') title='Track Online' id='alert_details"
						+ "'>"
						+ alertresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER() + "</a>");

				ja.add(alertresult.getVtsDatamodel().get(i).getTotalcount());
				// ja.add(shift);

				/*
				 * if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS
				 * ().equals("Partial")||alertresult.getWaybillDetails().get(i).
				 * getSCHEDULEDTRIPSTATUS().equals("Cancelled")) { ja.add(
				 * "<input type='checkbox' data-toggle='modal' role='button' href='#deviationinfo' onclick=javascript:getsghhh('"
				 * + selectedDate + "') id='deviated' />"); }
				 * 
				 * else { ja.add(""); }
				 */

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// sarthi Deviation on Click of Vehicle Number Info
	public JSONObject getDataForSarthiDevaitionInformation(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, String deviceId) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetSarthiLCDeviationInformation(
					selectedDate, scheduleno, deviceId, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();

				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "</a>");

				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				// ja.add(shift);

				/*
				 * if(alertresult.getWaybillDetails().get(i).getSCHEDULEDTRIPSTATUS
				 * ().equals("Partial")||alertresult.getWaybillDetails().get(i).
				 * getSCHEDULEDTRIPSTATUS().equals("Cancelled")) { ja.add(
				 * "<input type='checkbox' data-toggle='modal' role='button' href='#deviationinfo' onclick=javascript:getsghhh('"
				 * + selectedDate + "') id='deviated' />"); }
				 * 
				 * else { ja.add(""); }
				 */

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public List getRouteID(String date) {
		List list = new ArrayList();
		Session session = null;

		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse routeid = port.webGetRouteID(
					date, rbKey);
			for (int i = 0; i < routeid.getVtsDatamodel().size(); i++) {

				list.add(routeid.getVtsDatamodel().get(i).getROUTEID() != 0 ? routeid
						.getVtsDatamodel().get(i).getROUTEID()
						: "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getRouteName(String date) {
		List<String> list = new ArrayList<String>();
		try {
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse routename = port
					.webGetRouteName(date, rbKey);
			for (int i = 0; i < routename.getVtsDatamodel().size(); i++) {
				list.add(routename.getVtsDatamodel().get(i).getROUTENO() != null ? routename
						.getVtsDatamodel().get(i).getROUTENO()
						: "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			//HibernateUtilVtu.closeSessionFactory();

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForBusBunchingReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String routeid) {

		JSONObject result = new JSONObject();
		Session session = null;
		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			String relatedPointType = "";
			session = HibernateUtil.getSession("");
			Query query = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
			if (query.list().size() > 0) {
				relatedPointType = query.uniqueResult().toString();
			}
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetBusBunchingReport(
					selectedDate, routeid, rbKey);
			JSONArray array = new JSONArray();
			int sno = 0;
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				// counting vehicles in a group
				String fulldatarow = alertresult.getWaybillDetails().get(i)
						.getGroupedData();
				System.out.println("full    " + fulldatarow);
				String[] rowvals = fulldatarow.split("_@_");
				String checkdevice = "";
				String[] deviceListOfBunching = new String[rowvals.length];
				String[] vehicleNumListOfBunching = new String[rowvals.length];
				String[] vehicleTimeListOfBunching = new String[rowvals.length];
				Double lat = 0.0;
				Double lng = 0.0;
				for (int ii = 0; ii < rowvals.length; ii++) {
					String[] devicevals = rowvals[ii].split("@_@");
					deviceListOfBunching[ii] = devicevals[0];
					vehicleNumListOfBunching[ii] = devicevals[1];
					vehicleTimeListOfBunching[ii] = devicevals[6];
					lat = Double.parseDouble(devicevals[2]);
					lng = Double.parseDouble(devicevals[3]);
				}
				Set<String> set = new HashSet<String>();
				Set<String> vehiclenumset = new HashSet<String>();
				Set<String> vehicletimeset = new HashSet<String>();
				for (int jj = 0; jj < deviceListOfBunching.length; jj++) {
					set.add(deviceListOfBunching[jj]);
					vehiclenumset.add(vehicleNumListOfBunching[jj] + " ["
							+ vehicleTimeListOfBunching[jj] + "]");
					vehicletimeset.add(vehicleTimeListOfBunching[jj]);
				}
				System.out.println(set);
				String locationName = getStopList(lat, lng, session,
						relatedPointType);
				String vehicleNumsToDisp = StringUtils
						.join(vehiclenumset, ", ");
				String vehicletimeToDisp = StringUtils.join(vehicletimeset,
						", ");
				System.out.println("fruit " + vehicleNumsToDisp);
				// counting end here
				JSONArray ja = new JSONArray();
				sno = i + 1;
				ja.add(sno);
				ja.add(vehicleNumsToDisp);
				ja.add(alertresult.getWaybillDetails().get(i).getBBROUTENO());
				// ja.add(alertresult.getWaybillDetails().get(i).getFIRSTDATE());
				ja.add(locationName);
				ja.add(vehicletimeToDisp);
				ja.add(set.size());
				ja.add("<a href='#' class='mapClass' title='View on Map' onclick=javascript:drawBusBunching("
						+ alertresult.getWaybillDetails().get(i).getROUTEID()
						+ ",'"
						+ alertresult.getWaybillDetails().get(i)
								.getGroupedData()
						+ "',"
						+ alertresult.getWaybillDetails().get(i)
								.getBBSTARTPOINT()
						+ ","
						+ alertresult.getWaybillDetails().get(i)
								.getBBENDPOINT()
						+ ",'"
						+ alertresult.getWaybillDetails().get(i).getBBROUTENO()
						+ "')>"
						+ "<img src='assets/images/bus-map-icon.png'/>"
						+ "</a>");

				array.add(ja);
			}

			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// get Total trip count data
	public JSONObject getTotalTripDataCount() {

		JSONObject result = new JSONObject();
		int count[] = new int[5];
		String status[] = new String[5];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetTotalTripCountData(currentDate,
					rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {

				count[0] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getPartialTrip());
				count[1] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getDeviatedTrip());
				count[2] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getCompletedTrip());

				count[3] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getMissedTrip());
				count[4] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getDelayedTrip());

			}
			totalCount = count[0] + count[1] + count[2] + count[3] + count[4];
			status[0] = "Partial";
			status[1] = "DeviatedTrip";
			status[2] = "CompletedTrip";
			status[3] = "MissedTrip";
			status[4] = "DelayTrip";

			for (int i = 0; i < 5; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);

				System.out.println("count.." + count[i] + "status..."
						+ status[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalCount;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// end trip count data

	// satrt partial data

	public JSONObject getTotalTripPartialData() {
		System.out.println("partial data........");
		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetPartialTripCountData(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// partial data end
	// satrt Deviated data

	public JSONObject getTotalTripDeviatedData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetDeviatedTripCountData(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// TripComplete data end
	// satrt partial data

	public JSONObject getTotalTripCompleteData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetCompletedTripCountData(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// partial data end

	// total SCHEDULE count start

	public JSONObject getTotalScheduleCount() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetTotalScheduleCount(currentDate,
					rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(accresult.getWaybillDetails().get(i).getOrgName());
				ja.add(accresult.getWaybillDetails().get(i).getTotalcount());

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// total SCHEDULE count end

	// TOTAL ROUTE COUNT
	public JSONObject getTotalRouteCount() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			// System.out.println("currentDate"+currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetTotalRouteCount(currentDate,
					rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();
				// ja.add("Total Route");
				ja.add(accresult.getWaybillDetails().get(i).getTotalcount());

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// TOTAL ROUTE COUNT END
	// Schedule Wise Distance Travel Report Start
	public JSONObject getDataForScheduleWiseTripDistace(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, String busstopId) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;
			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetScheduleWiseTripDistance(
					selectedDate, scheduleno, busstopId, rbKey);
			JSONArray array = new JSONArray();
			JSONArray array1 = new JSONArray();
			JSONArray array2 = new JSONArray();
			JSONArray array3 = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				String shift = "";
				switch (alertresult.getVtsDatamodel().get(i).getDutyTypeId()) {
				case 1:
					shift = "General Shift";
					break;
				case 2:
					// shift = "Day 1";
					shift = "Night Out";
					break;
				case 3:
					shift = "Night Out";
					// shift = "Day 2";
					break;
				case 4:
					// shift = "Shift 1";
					shift = "Day Out - Shift1";
					break;
				case 5:
					// shift = "Shift 2";
					shift = "Day Out - Shift2";
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

				if (shift.equalsIgnoreCase("General Shift")
						&& generalShift == 0) {
					JSONArray ja1 = new JSONArray();

					ja1.add("General Shift");
					// ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add("");
					ja1.add(" ");
					ja1.add(" ");
					ja1.add(" ");
					array1.add(ja1);
					System.out.print("Value of I  GN" + i);
					newData.put(rowcount, new Object[] { "", " ", " ", " ",
							" GENERAL SHIFT ", " ", " ", "  ", " ", "  ", " ",
							" ", " ", " ", " ", " " });
					rowcount++;
					// generalShift++;
					gnFlag = true;
				}

				if (generalShift >= 0
						&& shift.equalsIgnoreCase("General Shift")) {
					if (gnFlag == true) {
						array.addAll(array1);
					}
					gnFlag = false;
					generalShift++;
					ja.add(generalShift);
					// ja.add(shift);

					System.out.print("Value of I  NO" + i);
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHEDULENAME());

					ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getTOTALTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getDISDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTTRIPDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getSCHTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getDEPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALTIME().toString() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALTIME() : "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTUALARRIVALTIME().toString() != null ? alertresult
							.getVtsDatamodel().get(i).getACTUALARRIVALTIME()
							: "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALDIFF().toString() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALDIFF() : "N/A");

					newData.put(rowcount,
							new Object[] {
									generalShift,
									alertresult.getVtsDatamodel().get(i)
											.getSCHEDULENAME(),
									alertresult.getVtsDatamodel().get(i)
											.getVEHICLENO(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getTOTALTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getACTDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getDISDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getDEPDIFF() });
					rowcount++;
					// generalShift++;
				}
				if (shift.equalsIgnoreCase("Night Out") && Nightout == 0) {
					JSONArray ja2 = new JSONArray();
					ja2.add("Night Out");
					// ja2.add(" ");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					ja2.add("");
					array2.add(ja2);
					NOFlag = true;
					newData.put(rowcount, new Object[] { "", " ", " ", " ",
							"  ", "NIGHT OUT ", " ", "  ", " ", "  ", " ", " ",
							" ", " ", " ", " " });
					rowcount++;
					// generalShift++;
					System.out.print("Value of I  GN" + i);

				}
				if (Nightout >= 0 && shift.equalsIgnoreCase("Night Out")) {
					if (NOFlag == true) {
						array.addAll(array2);
						NOFlag = false;
					}

					Nightout++;
					ja.add(Nightout);
					// ja.add(shift);
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHEDULENAME());
					ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getTOTALTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getDISDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTTRIPDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getSCHTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getDEPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALTIME() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALTIME() : "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTUALARRIVALTIME() != null ? alertresult
							.getVtsDatamodel().get(i).getACTUALARRIVALTIME()
							: "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALDIFF() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALDIFF() : "N/A");

					newData.put(rowcount,
							new Object[] {
									Nightout,
									alertresult.getVtsDatamodel().get(i)
											.getSCHEDULENAME(),
									alertresult.getVtsDatamodel().get(i)
											.getVEHICLENO(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getTOTALTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getACTDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getDISDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getDEPDIFF() });
					rowcount++;
					// Nightout++;
				}
				if (shift.equalsIgnoreCase("Day Out - Shift1")
						&& DayoutShift1 == 0) {
					JSONArray ja3 = new JSONArray();
					ja3.add("Day Out -Shift 1");
					// ja3.add(" ");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					ja3.add("");
					array3.add(ja3);
					newData.put(rowcount, new Object[] { "", " ", " ", " ",
							"  ", " ", "DAY OUT ", "  ", " ", "  ", " ", " ",
							" ", " ", " ", " " });
					DOShift1 = true;
					rowcount++;
					// generalShift++;
					System.out.print("Value of I  DO" + i);

				}
				if (DayoutShift1 >= 0
						&& shift.equalsIgnoreCase("Day Out - Shift1")) {
					if (DOShift1 == true) {
						array.addAll(array3);
						DOShift1 = false;
					}
					DayoutShift1++;
					ja.add(DayoutShift1);
					// ja.add(shift);
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHEDULENAME());
					ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getTOTALTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getDISDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTTRIPDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getSCHTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getDEPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALTIME() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALTIME() : "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTUALARRIVALTIME() != null ? alertresult
							.getVtsDatamodel().get(i).getACTUALARRIVALTIME()
							: "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALDIFF() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALDIFF() : "N/A");
					newData.put(rowcount,
							new Object[] {
									Dayout,
									alertresult.getVtsDatamodel().get(i)
											.getSCHEDULENAME(),
									alertresult.getVtsDatamodel().get(i)
											.getVEHICLENO(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getTOTALTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getACTDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getDISDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getDEPDIFF() });
					rowcount++;
					// Dayout++;
				}

				if (shift.equalsIgnoreCase("Day Out - Shift2")
						&& DayoutShift2 == 0) {
					JSONArray ja4 = new JSONArray();
					ja4.add("Day Out - Shift 2");
					// ja3.add(" ");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					ja4.add("");
					array4.add(ja4);
					newData.put(rowcount, new Object[] { "", " ", " ", " ",
							"  ", " ", "DAY OUT ", "  ", " ", "  ", " ", " ",
							" ", " ", " ", " " });
					DOShift2 = true;
					rowcount++;
					// generalShift++;
					System.out.print("Value of I  DO" + i);

				}
				if (DayoutShift2 >= 0
						&& shift.equalsIgnoreCase("Day Out - Shift2")) {
					if (DOShift2 == true) {
						array.addAll(array4);
						DOShift2 = false;
					}
					DayoutShift2++;
					ja.add(DayoutShift2);
					// ja.add(shift);
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHEDULENAME());
					ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTRIPS());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getTOTALTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTDIST());
					ja.add(alertresult.getVtsDatamodel().get(i).getDISDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getSCHDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTTRIPDURATION());
					ja.add(alertresult.getVtsDatamodel().get(i).getTRIPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i).getSCHTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getACTTIME());
					ja.add(alertresult.getVtsDatamodel().get(i).getDEPDIFF());
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALTIME() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALTIME() : "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getACTUALARRIVALTIME() != null ? alertresult
							.getVtsDatamodel().get(i).getACTUALARRIVALTIME()
							: "N/A");
					ja.add(alertresult.getVtsDatamodel().get(i)
							.getARRIVALDIFF() != null ? alertresult
							.getVtsDatamodel().get(i).getARRIVALDIFF() : "N/A");
					newData.put(rowcount,
							new Object[] {
									Dayout,
									alertresult.getVtsDatamodel().get(i)
											.getSCHEDULENAME(),
									alertresult.getVtsDatamodel().get(i)
											.getVEHICLENO(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPS(),
									alertresult.getVtsDatamodel().get(i)
											.getTOTALTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getACTDIST(),
									alertresult.getVtsDatamodel().get(i)
											.getDISDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTRIPDURATION(),
									alertresult.getVtsDatamodel().get(i)
											.getTRIPDIFF(),
									alertresult.getVtsDatamodel().get(i)
											.getSCHTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getACTTIME(),
									alertresult.getVtsDatamodel().get(i)
											.getDEPDIFF() });
					rowcount++;
					// Dayout++;
				}

				array.add(ja);
			}
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public int getDataForStartPoint(int j, HttpServletRequest req,
			String string, String string2, String selectedDate, String depotId,
			String deviceId) {

		JSONObject result = new JSONObject();
		int startPoint = 0;
		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			startPoint = getStartPoint(depotId);
			System.out.println("Depotid" + startPoint);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return startPoint;
	}

	private int getStartPoint(String depot_id) {
		// TODO Auto-generated method stub
		int param = 0;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select bus_stop_id from bus_stop where org_chart_id ='"
					+ depot_id + "'";
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

	// //Schedule Wise Distance Travel Report End
	// schedule trip count data start

	public JSONObject getTotalScheduleTripDataCount() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetTotalScheduleTripCount(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {

				count[0] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getPartialTrip());
				count[1] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getDeviatedTrip());
				count[2] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getCompletedTrip());

			}
			totalCount = count[0] + count[1] + count[2];
			status[0] = "PartialSchedule";
			status[1] = "DeviatedSchedule";
			status[2] = "CompletedSchedule";
			for (int i = 0; i < 3; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalCount;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// schedule trip count data end
	// start

	// satrt partial data

	public JSONObject getTotalScheduleTripPartialData() {
		System.out.println("partial data........");
		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		String depotid = "42";
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetDeviatedScheduleTripCountData(
					currentDate, rbKey, depotid);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				// ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// partial data end
	// satrt Deviated data

	public JSONObject getTotalScheduleTripDeviatedData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			String depot = "42";
			// VtsResponse5 accresult =
			// port.webGetDeviatedTripCountData(currentDate, rbKey);
			VtsResponse5 accresult = port.webGetDeviatedScheduleTripCountData(
					currentDate, rbKey, depot);
			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				// ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// TripComplete data end
	// satrt partial data

	public JSONObject getTotalScheduleTripCompleteData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetCompletedScheduleTripCountData(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				// ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// partial data end
	// get TotalCount of Fault ETM

	public int getTotalETMFaultCount() {

		JSONObject result = new JSONObject();
		int TotalFault = 0;
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetFaultyEtmCount(rbKey);
			for (int i = 0; i < alertresult.getManualTicket().size(); i++) {

				TotalFault = Integer.parseInt(alertresult.getManualTicket()
						.get(i).getTotalcount());
			}
			System.out.println("TotalFault" + TotalFault);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return TotalFault;
	}

	// end Fault ETM
	// ETM Faulty data start

	public JSONObject getETMFaultyData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse accresult = port.webGetFaultyEtmData(rbKey);

			System.out
					.println("accresult" + accresult.getManualTicket().size());
			for (int i = 0; i < accresult.getManualTicket().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add("ETM");
				ja.add(accresult.getManualTicket().get(i).getDeviceSerial());
				ja.add(accresult.getManualTicket().get(i).getDepotname());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// ETM faulty data end
	// crew aalocation total count

	public JSONObject getTotalCrewAlloactionpDataCount() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetTotalCrewAllocationCount(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {

				count[0] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getPartialTrip());
				count[1] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getDeviatedTrip());
				count[2] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getCompletedTrip());

			}
			totalCount = count[0] + count[1] + count[2];
			status[0] = "Close";
			status[1] = "New";
			status[2] = "Online";
			for (int i = 0; i < 3; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalCount;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// crew allocation count end

	// crew allocation data start

	// start

	// satrt partial data

	public JSONObject getCrewAlloactionCloseData() {
		System.out.println("partial data........");
		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetCloseCrewAllocationData(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO() != null ? accresult
						.getWaybillDetails().get(i).getWAYBILLNO()
						: "");
				// ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENO()!=null?accresult.getWaybillDetails().get(i).getSCHEDULENO():"");
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME() != null ? accresult
						.getWaybillDetails().get(i).getSCHEDULENAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i)
						.getSCHEDULETYPENAME() != null ? accresult
						.getWaybillDetails().get(i).getSCHEDULETYPENAME() : "");
				ja.add(accresult.getWaybillDetails().get(i).getSERVICENAME() != null ? accresult
						.getWaybillDetails().get(i).getSERVICENAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i).getCONDNAME() != null ? accresult
						.getWaybillDetails().get(i).getCONDNAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i).getDRIVERNAME() != null ? accresult
						.getWaybillDetails().get(i).getDRIVERNAME()
						: "");
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// partial data end
	// satrt Deviated data

	public JSONObject getCrewAlloactionNewData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// VtsResponse5 accresult =
			// port.webGetDeviatedTripCountData(currentDate, rbKey);
			VtsResponse5 accresult = port.webGetNewCrewAllocationData(
					currentDate, rbKey);
			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO() != null ? accresult
						.getWaybillDetails().get(i).getWAYBILLNO()
						: "");
				// ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENO()!=null?accresult.getWaybillDetails().get(i).getSCHEDULENO():"");
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME() != null ? accresult
						.getWaybillDetails().get(i).getSCHEDULENAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i)
						.getSCHEDULETYPENAME() != null ? accresult
						.getWaybillDetails().get(i).getSCHEDULETYPENAME() : "");
				ja.add(accresult.getWaybillDetails().get(i).getSERVICENAME() != null ? accresult
						.getWaybillDetails().get(i).getSERVICENAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i).getCONDNAME() != null ? accresult
						.getWaybillDetails().get(i).getCONDNAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i).getDRIVERNAME() != null ? accresult
						.getWaybillDetails().get(i).getDRIVERNAME()
						: "");
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// TripComplete data end
	// satrt partial data

	public JSONObject getCrewAlloactionOnlineData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetOnlineCrewAllocationData(
					currentDate, rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO() != null ? accresult
						.getWaybillDetails().get(i).getWAYBILLNO()
						: "");
				// ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENO()!=null?accresult.getWaybillDetails().get(i).getSCHEDULENO():"");
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME() != null ? accresult
						.getWaybillDetails().get(i).getSCHEDULENAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i)
						.getSCHEDULETYPENAME() != null ? accresult
						.getWaybillDetails().get(i).getSCHEDULETYPENAME() : "");
				ja.add(accresult.getWaybillDetails().get(i).getSERVICENAME() != null ? accresult
						.getWaybillDetails().get(i).getSERVICENAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i).getCONDNAME() != null ? accresult
						.getWaybillDetails().get(i).getCONDNAME()
						: "");
				ja.add(accresult.getWaybillDetails().get(i).getDRIVERNAME() != null ? accresult
						.getWaybillDetails().get(i).getDRIVERNAME()
						: "");

				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// crew allocation data end

	// online offline depot count

	public JSONObject getOnlineOfflineDepotCount() {

		JSONObject result = new JSONObject();
		int count[] = new int[2];
		String status[] = new String[2];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse accresult = port.webGetOnlineOfflineDepotCount(rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				count[0] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getOnlineDepot());
				count[1] = Integer.parseInt(accresult.getWaybillDetails()
						.get(i).getOfflineDepot());
			}
			totalCount = count[0] + count[1];
			status[0] = "Online_Depot";
			status[1] = "Offline_Depot";
			for (int i = 0; i < 2; i++) {
				JSONArray ja = new JSONArray();
				ja.add(count[i]);
				double per = Double.parseDouble(String.valueOf(count[i])) * 100
						/ totalCount;
				ja.add(per);
				ja.add(status[i]);
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// online offline depot count end

	@SuppressWarnings("unchecked")
	public JSONObject getDataForScheduleAdherence(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetScheduleAdherence(
					selectedDate, scheduleno, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPTRIPNUMBER());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getDEPDATE());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i).getACTDEPDATE());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME() != null ? alertresult
						.getWaybillDetails().get(i).getACTUALDEPARTURETIME()
						: "N/A");
				ja.add(alertresult.getWaybillDetails().get(i).getDEPTUREDIFF() != null ? alertresult
						.getWaybillDetails().get(i).getDEPTUREDIFF()
						: "N/A");
				ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getCONDNAME());

				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForScheduleArrival(int j, HttpServletRequest req,
			String string, String string2, String selectedDate,
			String scheduleno, String busstopId) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int earlyCountGN = 0;
			int earlyCountNO = 0;
			int earlyCountDO = 0;
			int lateCountGN = 0;
			int lateCountNO = 0;
			int lateCountDO = 0;
			int EarlyCountGN = 0;
			int EarlyCountNO = 0;
			int EarlyCountDO = 0;
			int LateCountGN = 0;
			int LateCountNO = 0;
			int LateCountDO = 0;
			int rowcount = 3;

			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse6 alertresult = port.webGetScheduleArrivalData(
					selectedDate, scheduleno, busstopId, rbKey);
			JSONArray array = new JSONArray();
			JSONArray array3 = new JSONArray();
			JSONArray array4 = new JSONArray();
			JSONArray array5 = new JSONArray();
			JSONArray array6 = new JSONArray();
			JSONArray array7 = new JSONArray();
			JSONArray array8 = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				String shift = "";
				switch (alertresult.getWaybillDetails().get(i).getDutyTypeId()) {
				case 1:
					shift = "General Shift";
					break;
				case 2:
					// shift = "Day 1";
					shift = "Night Out";
					break;
				case 3:
					shift = "Night Out";
					// shift = "Day 2";
					break;
				case 4:
					// shift = "Shift 1";
					shift = "Day Out";
					break;
				case 5:
					// shift = "Shift 2";
					shift = "Day Out";
					System.out.println("Dy oo @@@shift2");
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
				String depstatus = "";
				String arrivalstatus = "";
				int departurediff;
				int arrivaldiff;
				String Remarks = " ";

				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				String schname = alertresult.getWaybillDetails().get(i)
						.getSCHEDULENAME().toString();
				ja.add(shift);
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i).getDEPTUREDIFF() != null ? alertresult
						.getWaybillDetails().get(i).getDEPTUREDIFF()
						: "N/A");
				// String
				// depDiff=alertresult.getWaybillDetails().get(i).getDEPTUREDIFF();
				/*
				 * if(depDiff==null||depDiff.isEmpty()||depDiff.equals(null)){
				 * depDiff="NA"; }else{ depDiff=depDiff; }
				 */

				if (alertresult.getWaybillDetails().get(i).getSCHTIMEDIFF() != null
						&& alertresult.getWaybillDetails().get(i)
								.getDEPTUREDIFF() != null) {

					int schdiff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getSCHTIMEDIFF()
							.toString());
					// 05:39:56
					if (schdiff < -15) {
						if (shift.equalsIgnoreCase("General Shift")) {
							EarlyCountGN = earlyCountGN++;
						}
						if (shift.equalsIgnoreCase("Night Out")) {
							EarlyCountNO = earlyCountNO++;
						}
						if (shift.equalsIgnoreCase("Day Out")) {
							EarlyCountDO = earlyCountDO++;
						}
						Remarks = "Sch "
								+ schname
								+ "-"
								+ "EA BY "
								+ alertresult.getWaybillDetails().get(i)
										.getDEPTUREDIFF() + "Hrs";
						ja.add(Remarks);

					} else if (schdiff > 15) {

						if (shift.equalsIgnoreCase("General Shift")) {
							LateCountGN = lateCountGN++;
						}
						if (shift.equalsIgnoreCase("Night Out")) {
							LateCountNO = lateCountNO++;
						}
						if (shift.equalsIgnoreCase("Day Out")) {
							LateCountDO = lateCountDO++;
						}
						Remarks = "Sch "
								+ schname
								+ "-"
								+ "LA BY "
								+ alertresult.getWaybillDetails().get(i)
										.getDEPTUREDIFF() + "Hrs";
						ja.add(Remarks);
					} else {
						ja.add("OK");
					}
				} else {
					ja.add("N/A");
				}

				array.add(ja);
				System.out.println("EA GN" + earlyCountGN + "EA NO"
						+ earlyCountNO + "EA DO" + earlyCountDO);
				System.out.println("LA GN" + lateCountGN + "LA NO"
						+ lateCountNO + "LA DO" + lateCountDO);
			}

			JSONArray ja3 = new JSONArray();
			ja3.add(" ");
			// ja3.add(" ");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			array3.add(ja3);
			array.addAll(array3);
			//
			JSONArray ja4 = new JSONArray();
			ja4.add("Daily Arrival Summary");
			// ja3.add(" ");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");

			array4.add(ja4);
			array.addAll(array4);

			JSONArray ja5 = new JSONArray();
			ja5.add("Type");
			// ja3.add(" ");
			ja5.add("Scheduled");
			ja5.add("Actual");
			ja5.add("Cancelled Sch");
			ja5.add("No of Sch With EA> 15 Min");
			ja5.add("No of Sch With LA> 15 Min");
			ja5.add("");
			ja5.add("");
			ja5.add("");
			ja5.add("");
			array5.add(ja5);
			array.addAll(array5);

			JSONArray ja6 = new JSONArray();
			ja6.add("General Shift");
			// ja3.add(" ");
			ja6.add("");
			ja6.add("");
			ja6.add("");
			ja6.add(EarlyCountGN);
			ja6.add(LateCountGN);
			ja6.add("");
			ja6.add("");
			ja6.add("");
			ja6.add("");
			array6.add(ja6);
			array.addAll(array6);

			JSONArray ja7 = new JSONArray();
			ja7.add("Night Out");
			// ja3.add(" ");
			ja7.add(" ");
			ja7.add(" ");
			ja7.add(" ");
			ja7.add(EarlyCountNO);
			ja7.add(LateCountNO);
			ja7.add("");
			ja7.add("");
			ja7.add("");
			ja7.add("");
			array7.add(ja7);
			array.addAll(array7);

			JSONArray ja8 = new JSONArray();
			ja8.add("Day Out");
			// ja3.add(" ");
			ja8.add(" ");
			ja8.add("");
			ja8.add(" ");
			ja8.add(EarlyCountDO);
			ja8.add(lateCountDO);
			ja8.add("");
			ja8.add("");
			ja8.add("");
			ja8.add("");
			array8.add(ja8);
			array.addAll(array8);
			// }
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForScheduleDeparture(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, String busstopId) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int earlyCountGN = 0;
			int earlyCountNO = 0;
			int earlyCountDO = 0;
			int lateCountGN = 0;
			int lateCountNO = 0;
			int lateCountDO = 0;
			int EarlyCountGN = 0;
			int EarlyCountNO = 0;
			int EarlyCountDO = 0;
			int LateCountGN = 0;
			int LateCountNO = 0;
			int LateCountDO = 0;
			int rowcount = 3;

			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse6 alertresult = port.webGetScheduleDepartureData(
					selectedDate, scheduleno, busstopId, rbKey);
			JSONArray array = new JSONArray();
			JSONArray array3 = new JSONArray();
			JSONArray array4 = new JSONArray();
			JSONArray array5 = new JSONArray();
			JSONArray array6 = new JSONArray();
			JSONArray array7 = new JSONArray();
			JSONArray array8 = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				String shift = "";
				switch (alertresult.getWaybillDetails().get(i).getDutyTypeId()) {
				case 1:
					shift = "General Shift";
					break;
				case 2:
					// shift = "Day 1";
					shift = "Night Out";
					break;
				case 3:
					shift = "Night Out";
					// shift = "Day 2";
					break;
				case 4:
					// shift = "Shift 1";
					shift = "Day Out";
					break;
				case 5:
					// shift = "Shift 2";
					shift = "Day Out";
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
				String depstatus = "";
				String arrivalstatus = "";
				int departurediff;
				int arrivaldiff;
				String Remarks = " ";

				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				String schname = alertresult.getWaybillDetails().get(i)
						.getSCHEDULENAME().toString();
				ja.add(shift);
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME());
				// ja.add(alertresult.getWaybillDetails().get(i).getDEPTUREDIFF()!=null?alertresult.getWaybillDetails().get(i).getDEPTUREDIFF():"N/A");
				// String
				// depDiff=alertresult.getWaybillDetails().get(i).getDEPTUREDIFF();
				/*
				 * if(depDiff==null||depDiff.isEmpty()||depDiff.equals(null)){
				 * depDiff="NA"; }else{ depDiff=depDiff; }
				 */

				if (alertresult.getWaybillDetails().get(i).getSCHTIMEDIFF() != null
						&& alertresult.getWaybillDetails().get(i)
								.getDEPTUREDIFF() != null) {

					int schdiff = Integer.parseInt(alertresult
							.getWaybillDetails().get(i).getSCHTIMEDIFF()
							.toString());
					// 05:39:56
					if (schdiff < -15) {
						if (shift.equalsIgnoreCase("General Shift")) {
							EarlyCountGN = earlyCountGN++;
						}
						if (shift.equalsIgnoreCase("Night Out")) {
							EarlyCountNO = earlyCountNO++;
						}
						if (shift.equalsIgnoreCase("Day Out")) {
							EarlyCountDO = earlyCountDO++;
						}

						String timediff[] = alertresult.getWaybillDetails()
								.get(i).getDEPTUREDIFF().split(":");
						Remarks = "Sch " + schname + "-" + "ED BY "
								+ timediff[0] + "Hrs :" + timediff[1] + "min";
						ja.add("ED -" + timediff[0] + "Hrs :" + timediff[1]
								+ "min");
						ja.add(Remarks);

					} else if (schdiff > 15) {

						if (shift.equalsIgnoreCase("General Shift")) {
							LateCountGN = lateCountGN++;
						}
						if (shift.equalsIgnoreCase("Night Out")) {
							LateCountNO = lateCountNO++;
						}
						if (shift.equalsIgnoreCase("Day Out")) {
							LateCountDO = lateCountDO++;
						}
						String timediff[] = alertresult.getWaybillDetails()
								.get(i).getDEPTUREDIFF().split(":");
						Remarks = "Sch " + schname + "-" + "LD BY "
								+ timediff[0] + "Hrs :" + timediff[1] + "min";
						ja.add("LD -" + timediff[0] + "Hrs :" + timediff[1]
								+ "min");
						ja.add(Remarks);
					} else {
						String timediff[] = alertresult.getWaybillDetails()
								.get(i).getDEPTUREDIFF().split(":");
						ja.add("LD -" + timediff[0] + "Hrs :" + timediff[1]
								+ "min");
						ja.add("OK");
					}
				} else {
					ja.add("N/A");
					ja.add("N/A");
				}

				array.add(ja);
				System.out.println("EA GN" + earlyCountGN + "EA NO"
						+ earlyCountNO + "EA DO" + earlyCountDO);
				System.out.println("LA GN" + lateCountGN + "LA NO"
						+ lateCountNO + "LA DO" + lateCountDO);
			}

			JSONArray ja3 = new JSONArray();
			ja3.add(" ");
			// ja3.add(" ");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			ja3.add("");
			array3.add(ja3);
			array.addAll(array3);
			//
			JSONArray ja4 = new JSONArray();
			ja4.add("Daily Departure Summary");
			// ja3.add(" ");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");
			ja4.add("");

			array4.add(ja4);
			array.addAll(array4);

			JSONArray ja5 = new JSONArray();
			ja5.add("Type");
			// ja3.add(" ");
			ja5.add("Scheduled");
			ja5.add("Actual");
			ja5.add("Cancelled Sch");
			ja5.add("No of Sch With ED> 15 Min");
			ja5.add("No of Sch With LD> 15 Min");
			ja5.add("");
			ja5.add("");
			ja5.add("");
			ja5.add("");
			array5.add(ja5);
			array.addAll(array5);

			JSONArray ja6 = new JSONArray();
			ja6.add("General Shift");
			// ja3.add(" ");
			ja6.add("");
			ja6.add("");
			ja6.add("");
			ja6.add(EarlyCountGN);
			ja6.add(LateCountGN);
			ja6.add("");
			ja6.add("");
			ja6.add("");
			ja6.add("");
			array6.add(ja6);
			array.addAll(array6);

			JSONArray ja7 = new JSONArray();
			ja7.add("Night Out");
			// ja3.add(" ");
			ja7.add(" ");
			ja7.add(" ");
			ja7.add(" ");
			ja7.add(EarlyCountNO);
			ja7.add(LateCountNO);
			ja7.add("");
			ja7.add("");
			ja7.add("");
			ja7.add("");
			array7.add(ja7);
			array.addAll(array7);

			JSONArray ja8 = new JSONArray();
			ja8.add("Day Out");
			// ja3.add(" ");
			ja8.add(" ");
			ja8.add("");
			ja8.add(" ");
			ja8.add(EarlyCountDO);
			ja8.add(lateCountDO);
			ja8.add("");
			ja8.add("");
			ja8.add("");
			ja8.add("");
			array8.add(ja8);
			array.addAll(array8);
			// }
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForRouteDeviationInfo(int j,

	String selectedDate, String DepotId) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetDataForRouteDeviation(
					selectedDate, DepotId, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				String shift = "";
				switch (alertresult.getWaybillDetails().get(i).getDutyTypeId()) {
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
				ja.add(shift);

				ja.add(alertresult.getWaybillDetails().get(i).getBBROUTENO());
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPTRIPNUMBER());
				ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getDEVIATEDFROM());
				ja.add(alertresult.getWaybillDetails().get(i).getDEVIATEDTILL());
				ja.add(alertresult.getWaybillDetails().get(i).getDESTINATION());

				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForMissedTrips(int j,

	String selectedDate, String DepotId) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetDataForMissedTrips(
					selectedDate, DepotId, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i).getDRIVERNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getCONDNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULETRIPTRIPNUMBER());
				ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getENDBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getDISTANCE());

				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForBreakDownReport(int j,

	String fromDate, String tillDate, String DepotId) {

		JSONObject result = new JSONObject();
		Session session1 = null;
		Transaction transaction = null;
		String dummydate = tillDate.substring(0, 10);
		String tempdate[] = dummydate.split("-");
		String relatedPointType = "";
		Session session = HibernateUtil.getSession("");
		Query query1 = session
				.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id'");
		if (query1.list().size() > 0) {
			relatedPointType = query1.uniqueResult().toString();
		}
		String dutyDate = tempdate[2] + "-" + tempdate[1] + "-" + tempdate[0];
		try {

			String sql = "select depot_id,depot_name, DATE_FORMAT(duty_dt,'%d-%m-%Y') as duty_dt,route_no,vehical_id,lattitude,longitude,"
					+ "trip_no,driver_name,km_cancelled,DATE_FORMAT(date(call_time),'%d-%m-%Y') as breakdown_date ,"
					+ " DATE_FORMAT(time(call_time),'%H:%s') as breakdown_time"
					+ " from breakdown_ccc where created_date between   '"
					+ fromDate
					+ "' and '"
					+ tillDate
					+ "'  and depot_id="
					+ DepotId + " order by depot_id; ";

			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session1.beginTransaction();
			Query query = session1.createSQLQuery(sql)
					.addScalar("breakdown_date", Hibernate.STRING)
					.addScalar("breakdown_time", Hibernate.STRING)
					.addScalar("duty_dt", Hibernate.STRING)
					.addScalar("route_no", Hibernate.STRING)
					.addScalar("trip_no", Hibernate.STRING)
					.addScalar("driver_name", Hibernate.STRING)
					.addScalar("vehical_id", Hibernate.STRING)
					.addScalar("km_cancelled", Hibernate.STRING)
					.addScalar("lattitude", Hibernate.STRING)
					.addScalar("longitude", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			// System.out.println("aliasToValueMapList.size()"+aliasToValueMapList.size());

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> list = aliasToValueMapList.get(i);
				JSONArray ja = new JSONArray();
				// System.out.println(list.get("sector_id").toString());
				ja.add(i + 1);
				ja.add(list.get("duty_dt") != null ? list.get("duty_dt")
						: dutyDate);
				ja.add(list.get("route_no") != null ? list.get("route_no")
						: "0 ");
				ja.add(list.get("vehical_id") != null ? list.get("vehical_id")
						: "0 ");
				ja.add(list.get("trip_no") != null ? list.get("trip_no") : "0 ");
				ja.add(list.get("driver_name") != null ? list
						.get("driver_name") : " ");
				ja.add(getStopList(Double.parseDouble(list.get("lattitude")
						.toString() != null ? list.get("lattitude").toString()
						: "0"), Double.parseDouble(list.get("longitude")
						.toString() != null ? list.get("longitude").toString()
						: "0"), session, relatedPointType));
				ja.add(list.get("km_cancelled") != null ? list
						.get("km_cancelled") : "0 ");
				ja.add(list.get("breakdown_date") != null ? list
						.get("breakdown_date") : " ");
				ja.add(list.get("breakdown_time") != null ? list
						.get("breakdown_time") : " ");

				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForControlChart(int j,

	String selectedDate, String DepotId) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetDataForControlChart(
					selectedDate, rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(alertresult.getWaybillDetails().get(i).getTripNumber());
				ja.add(alertresult.getWaybillDetails().get(i).getROUTENO());
				ja.add(alertresult.getWaybillDetails().get(i).getDepotCd());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getENDBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getSERVICENAME());
				ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
				ja.add(alertresult.getWaybillDetails().get(i).getACTSTARTTIME());
				ja.add(alertresult.getWaybillDetails().get(i).getDEPTUREDIFF());
				ja.add(" ");

				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForScheduleArrivalDaily(int j,

	String fromDate, String depotID, String tillDate) throws ParseException {

		JSONObject result = new JSONObject();
		int startDay = getDay(fromDate);
		int endDay = getDay(tillDate);

		List<Date> dates = new ArrayList<Date>();

		String arrayTemp[] = new String[9];

		DateFormat formatter;

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = (Date) formatter.parse(fromDate);
		Date endDate = (Date) formatter.parse(tillDate);
		long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
		long endTime = endDate.getTime(); // create your endtime here, possibly
											// using Calendar or Date
		long curTime = startDate.getTime();
		while (curTime <= endTime) {
			dates.add(new Date(curTime));
			curTime += interval;
		}

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetArrivalDaily(fromDate,
					tillDate, depotID, rbKey);
			JSONArray array = new JSONArray();
			JSONArray array1 = new JSONArray();
			for (int i = 0; i < 1; i++) {

				JSONArray ja = new JSONArray();
				ja.add("Sr No.");
				ja.add("Schedule No");
				ja.add("Schedule Type");
				for (int x = 0; x < dates.size(); x++) {
					Date lDate = (Date) dates.get(x);
					String ds = formatter.format(lDate);
					String header = getDay(ds) + " " + getMonth(ds);
					ja.add(header);
				}
				ja.add("Punctuality");
				array1.add(ja);
			}
			array.addAll(array1);
			for (int k = 0; k < alertresult.getWaybillDetails().size(); k++) {
				int mycall = 0;
				JSONArray ja1 = new JSONArray();
				ja1.add(k + 1);
				ja1.add(alertresult.getWaybillDetails().get(k)
						.getSCHEDULENAME());

				String shift = "";
				switch (alertresult.getWaybillDetails().get(k).getDutyTypeId()) {
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
				ja1.add(shift);
				String data[] = alertresult.getWaybillDetails().get(k)
						.getConc().split("----");
				for (int x = 0; x < dates.size(); x++) {
					Date lDate = (Date) dates.get(x);
					String ds = formatter.format(lDate);
					try {
						if (mycall < data.length) {
							int dayData = getDay(data[mycall]);
							int day = getDay(ds);
							if (dayData == day) {
								arrayTemp[x] = data[mycall];
							} else {
								arrayTemp[x] = "0 ";
								mycall--;
							}
							mycall++;
						}
					} catch (Exception ex) {
					}
				}
				int okCount = 0, eAcount = 0, eDcount = 0;
				for (int l = 0; l < arrayTemp.length; l++) {
					String strArr[] = arrayTemp[l].split(" ");
					if (strArr.length > 1) {
						if (strArr[1].contains("OK")) {
							ja1.add("OK");
							okCount++;
						} else if (strArr[1].contains("EA")) {
							ja1.add(strArr[1]);
						} else if (strArr[1].contains("LA")) {
							ja1.add(strArr[1]);
						}
					} else {
						ja1.add("N/A");
					}
				}
				// percentage Calculations...
				int totPer = okCount * 100 / 9;
				// End
				ja1.add(totPer + "%");
				array.add(ja1);

			}
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForScheduleDepartureDaily(int j,

	String fromDate, String depotID, String tillDate) throws ParseException {

		JSONObject result = new JSONObject();
		int startDay = getDay(fromDate);
		int endDay = getDay(tillDate);

		List<Date> dates = new ArrayList<Date>();

		String arrayTemp[] = new String[9];

		DateFormat formatter;

		formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = (Date) formatter.parse(fromDate);
		Date endDate = (Date) formatter.parse(tillDate);
		long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
		long endTime = endDate.getTime(); // create your endtime here, possibly
											// using Calendar or Date
		long curTime = startDate.getTime();
		while (curTime <= endTime) {
			dates.add(new Date(curTime));
			curTime += interval;
		}

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse6 alertresult = port.webGetDepartureDaily(fromDate,
					tillDate, depotID, rbKey);
			JSONArray array = new JSONArray();
			JSONArray array1 = new JSONArray();
			for (int i = 0; i < 1; i++) {

				JSONArray ja = new JSONArray();
				ja.add("Sr No.");
				ja.add("Schedule No");
				ja.add("Schedule Type");
				for (int x = 0; x < dates.size(); x++) {
					Date lDate = (Date) dates.get(x);
					String ds = formatter.format(lDate);
					String header = getDay(ds) + " " + getMonth(ds);
					ja.add(header);
				}
				ja.add("Punctuality");
				array1.add(ja);
			}
			array.addAll(array1);
			for (int k = 0; k < alertresult.getWaybillDetails().size(); k++) {
				int mycall = 0;
				JSONArray ja1 = new JSONArray();
				ja1.add(k + 1);
				ja1.add(alertresult.getWaybillDetails().get(k)
						.getSCHEDULENAME());
				String shift = "";
				switch (alertresult.getWaybillDetails().get(k).getDutyTypeId()) {
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
				ja1.add(shift);

				String data[] = alertresult.getWaybillDetails().get(k)
						.getConc().split("----");
				for (int x = 0; x < dates.size(); x++) {
					Date lDate = (Date) dates.get(x);
					String ds = formatter.format(lDate);
					try {
						if (mycall < data.length) {
							int dayData = getDay(data[mycall]);
							int day = getDay(ds);
							if (dayData == day) {
								arrayTemp[x] = data[mycall];
							} else {
								arrayTemp[x] = "0 ";
								mycall--;
							}
							mycall++;
						}
					} catch (Exception ex) {
					}
				}
				int okCount = 0, eAcount = 0, eDcount = 0;
				for (int l = 0; l < arrayTemp.length; l++) {
					String strArr[] = arrayTemp[l].split(" ");
					if (strArr.length > 1) {
						if (strArr[1].contains("OK")) {
							ja1.add("OK");
							okCount++;
						} else if (strArr[1].contains("ED")) {
							ja1.add(strArr[1]);
						} else if (strArr[1].contains("LD")) {
							ja1.add(strArr[1]);
						}
					} else {
						ja1.add("N/A");
					}
				}
				// percentage Calculations...
				int totPer = okCount * 100 / 9;
				// End
				ja1.add(totPer + "%");
				array.add(ja1);

			}
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	static int getDay(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// String dateInString = "2015-03-20";
		Date date = null;
		try {

			date = formatter.parse(dateInString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_MONTH);
		return dayOfWeek;
	}

	static String getMonth(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// String dateInString = "2015-03-20";
		Date date = null;
		try {

			date = formatter.parse(dateInString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String dayOfWeek = c.getDisplayName(Calendar.MONTH, Calendar.LONG,
				Locale.ENGLISH);
		return dayOfWeek;
	}

	@SuppressWarnings("unchecked")
	public JSONObject getDataForSchedulePerformanceReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno) {

		JSONObject result = new JSONObject();
		try {
			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			DecimalFormat df1 = new DecimalFormat("0.00");
			VtsResponse6 alertresult = port.webGetSchedulePerformance(
					scheduleno, selectedDate, rbKey);
			JSONArray array = new JSONArray();

			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();
				String shift = "";
				double dist = Double.parseDouble(alertresult
						.getWaybillDetails().get(i).getDISTANCE());
				double tripdist = Double.parseDouble(alertresult
						.getWaybillDetails().get(i).getTRIPDISTANCE());
				double disdiff = 0.00;

				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getTripNumber());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getSTARTBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getENDBUSSTOPNAME());
				ja.add(alertresult.getWaybillDetails().get(i).getROUTENO());
				ja.add(alertresult.getWaybillDetails().get(i).getSTARTTIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME());
				ja.add(alertresult.getWaybillDetails().get(i).getDEPTUREDIFF());
				ja.add(alertresult.getWaybillDetails().get(i).getENDTIME());
				ja.add(alertresult.getWaybillDetails().get(i)
						.getACTUALARRIVALTIME());
				ja.add(alertresult.getWaybillDetails().get(i).getARRIVALDIFF());
				ja.add(alertresult.getWaybillDetails().get(i).getDISTANCE());
				ja.add(alertresult.getWaybillDetails().get(i).getTRIPDISTANCE());
				disdiff = dist - tripdist;
				ja.add(df1.format(disdiff));
				ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME() != null ? alertresult
						.getWaybillDetails().get(i).getRUNNINGTIME()
						: "N/A");

				array.add(ja);

			}
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getSchedulePerformanceFooter(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here

			DecimalFormat df1 = new DecimalFormat("0.00");
			VtsResponse6 alertresult = port.webGetSchedulePerformance(
					scheduleno, selectedDate, rbKey);

			// JSONArray array1 = new JSONArray();
			JSONArray footerarray = new JSONArray();

			double totalformIV = 0.00;
			double totalOperatedKm = 0.00;
			double totaldisdiffKm = 0.00;
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				Double formIv = Double.parseDouble(alertresult
						.getWaybillDetails().get(i).getDISTANCE());
				totalformIV += formIv;

				Double operatedKm = Double.parseDouble(alertresult
						.getWaybillDetails().get(i).getTRIPDISTANCE());
				totalOperatedKm += operatedKm;

			}

			footerarray.add(df1.format(totalformIV));
			footerarray.add(df1.format(totalOperatedKm));

			totaldisdiffKm = (totalformIV - totalOperatedKm);
			footerarray.add(df1.format(totaldisdiffKm));
			// array1.add(footerarray);
			result.put("totalData", footerarray);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;

	}

	public JSONObject getSchedulePerformanceHeader(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here

			DecimalFormat df1 = new DecimalFormat("0.00");
			VtsResponse6 alertresult = port.webGetSchedulePerformance(
					scheduleno, selectedDate, rbKey);
			JSONArray headrearray = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
				// String arr[] = getScheduleHeaderDetails(formFourId);
				// JSONArray headrearray = new JSONArray();

				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getSCHEDULENAME());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getVEHICLENO());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getDutyTypeId());
				headrearray.add("");
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getSERVICENAME());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getDRIVERNAME());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getCONDNAME());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getDUTYDT());
				// headrearray.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getSTARTTIME());
				headrearray.add(alertresult.getWaybillDetails()
						.get(alertresult.getWaybillDetails().size() - 1)
						.getENDTIME());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getACTUALDEPARTURETIME());
				headrearray.add(alertresult.getWaybillDetails()
						.get(alertresult.getWaybillDetails().size() - 1)
						.getACTUALARRIVALTIME());
				headrearray.add(alertresult.getWaybillDetails().get(i)
						.getDEPTUREDIFF());
				headrearray.add(alertresult.getWaybillDetails()
						.get(alertresult.getWaybillDetails().size() - 1)
						.getARRIVALDIFF());
				// result.put("bbData", headrearray);*/
			}
			result.put("headData", headrearray);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;

	}

	// get Online data start

	public JSONObject getOnlineDepotData() {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse alertresult = port.webGetOnlineDepotData(rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
				ja.add(alertresult.getWaybillDetails().get(i).getIpAddr());
				ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());

				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// Online data end
	// get Offline Data start

	public JSONObject getOfflineDepotData() {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse alertresult = port.webGetOfflineDepotData(rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getWaybillDetails().get(i).getOrgName());
				ja.add(alertresult.getWaybillDetails().get(i).getIpAddr());
				ja.add(alertresult.getWaybillDetails().get(i).getRUNNINGTIME());

				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// offline data end
	public JSONObject getDataForDailyOperationalSummary(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, String busstopId) {

		JSONObject result = new JSONObject();

		try {

			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			VtsResponse alertresult = port.webGetDailyOperationalSummary(
					selectedDate, scheduleno, busstopId, rbKey);
			JSONArray array = new JSONArray();

			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				String shift = "";

				String depstatus = "";
				String arrivalstatus = "";
				int departurediff;
				int arrivaldiff;
				String Remarks = " ";

				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLENO());

				if (alertresult.getVtsDatamodel().get(i).getDEPMIN() != null
						&& alertresult.getVtsDatamodel().get(i).getDEPDIFF() != null) {

					int schdiff = Integer.parseInt(alertresult
							.getVtsDatamodel().get(i).getDEPMIN());
					// 05:39:56
					if (schdiff < -15) {

						Remarks = "ED - "
								+ alertresult.getVtsDatamodel().get(i)
										.getDEPDIFF() + "Hrs";
						ja.add(Remarks);

					} else if (schdiff > 15) {

						Remarks = "LD - "
								+ alertresult.getVtsDatamodel().get(i)
										.getDEPDIFF() + "Hrs";

						ja.add(Remarks);
					} else {
						ja.add("OK");
					}
				} else {
					ja.add("N/A");
				}

				if (alertresult.getVtsDatamodel().get(i).getARRMIN() != null
						&& alertresult.getVtsDatamodel().get(i)
								.getARRIVALDIFF() != null) {

					int schdiff = Integer.parseInt(alertresult
							.getVtsDatamodel().get(i).getARRMIN());
					// 05:39:56
					if (schdiff < -15) {

						Remarks = "EA - "
								+ alertresult.getVtsDatamodel().get(i)
										.getARRIVALDIFF() + "Hrs";
						ja.add(Remarks);

					} else if (schdiff > 15) {

						Remarks = "LA - "
								+ alertresult.getVtsDatamodel().get(i)
										.getARRIVALDIFF() + "Hrs";

						ja.add(Remarks);
					} else {
						ja.add("OK");
					}
				} else {
					ja.add("N/A");
				}
				ja.add(alertresult.getVtsDatamodel().get(i).getDIST() != null ? alertresult
						.getVtsDatamodel().get(i).getDIST()
						: " N/A");
				ja.add(alertresult.getVtsDatamodel().get(i).getACTDIST() != null ? alertresult
						.getVtsDatamodel().get(i).getACTDIST()
						: " N/A");
				ja.add(alertresult.getVtsDatamodel().get(i).getDISDIFF() != null ? alertresult
						.getVtsDatamodel().get(i).getDISDIFF()
						: " N/A");
				ja.add(alertresult.getVtsDatamodel().get(i).getBUSSKIPED() != null ? alertresult
						.getVtsDatamodel().get(i).getBUSSKIPED()
						: "0");
				ja.add(alertresult.getVtsDatamodel().get(i).getOVERSPEED() != null ? alertresult
						.getVtsDatamodel().get(i).getOVERSPEED()
						: "0");
				ja.add(alertresult.getVtsDatamodel().get(i).getHARSHBRAKE() != null ? alertresult
						.getVtsDatamodel().get(i).getHARSHBRAKE()
						: "0");
				// ja.add(" ");
				array.add(ja);

			}

			// }
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getVehicleDataToPlot(String Lat, String Long) {

		JSONObject result = new JSONObject();

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse alertresult = port.webGetVehicleDataOnMap(Lat, Long,
					rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getLAT());
				ja.add(alertresult.getVtsDatamodel().get(i).getLONGITUDE());
				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME()!=null?alertresult.getVtsDatamodel().get(i).getSCHEDULENAME():"Not Mapped");
				ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				//System.out.println("alertresult.getVtsDatamodel().get(i).getDEPOTNAME()"+alertresult.getVtsDatamodel().get(i).getScheduleNumberName());
				ja.add(alertresult.getVtsDatamodel().get(i).getScheduleNumberName());
				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLEDIRECTION());
				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}
	public JSONObject getVehicleDataToPlotDateTime(String Lat, String Long,String Start_time,String end_time,String dist) {

		JSONObject result = new JSONObject();
System.out.println("in get method");
		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			
			TimeUnit.SECONDS.sleep(10);   // to hold result for 10 sec
			
			VtsResponse alertresult = port.webGetVehicleDataOnMapDateTime(Lat, Long,Start_time,end_time,dist,
					rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(alertresult.getVtsDatamodel().get(i).getLAT());
				ja.add(alertresult.getVtsDatamodel().get(i).getLONGITUDE());
				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME()!=null?alertresult.getVtsDatamodel().get(i).getSCHEDULENAME():"Not Mapped");
				ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				//System.out.println("alertresult.getVtsDatamodel().get(i).getDEPOTNAME()"+alertresult.getVtsDatamodel().get(i).getScheduleNumberName());
				ja.add(alertresult.getVtsDatamodel().get(i).getScheduleNumberName());
				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLEDIRECTION());
				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}
	
	
	public JSONObject getTotalVehicleDataDateTime(String Lat, String Long,String Start_time,String end_time,String dist) {

		JSONObject result = new JSONObject();
System.out.println("in get method");
		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			
			TimeUnit.SECONDS.sleep(10);   // to hold result for 10 sec
			
			VtsResponse alertresult = port.webGetVehicleDataOnMapDateTime(Lat, Long,Start_time,end_time,dist,
					rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getVtsDatamodel().size(); i++) {

				JSONArray ja = new JSONArray();
				ja.add(i + 1);
//				ja.add(alertresult.getVtsDatamodel().get(i).getLAT());
//				ja.add(alertresult.getVtsDatamodel().get(i).getLONGITUDE());
				ja.add(alertresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(alertresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(alertresult.getVtsDatamodel().get(i).getSCHEDULENAME()!=null?alertresult.getVtsDatamodel().get(i).getSCHEDULENAME():"Not Mapped");
//				ja.add(alertresult.getVtsDatamodel().get(i).getSPEEDKMPH());
				ja.add(alertresult.getVtsDatamodel().get(i).getISTDATE());
				//System.out.println("alertresult.getVtsDatamodel().get(i).getDEPOTNAME()"+alertresult.getVtsDatamodel().get(i).getScheduleNumberName());
				ja.add(alertresult.getVtsDatamodel().get(i).getScheduleNumberName());
//				ja.add(alertresult.getVtsDatamodel().get(i).getVEHICLEDIRECTION());
				//
				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}
	
	

	// get manual ticket data start
	public JSONObject getManualTicketData() {

		JSONObject result = new JSONObject();

		try {

			int manual = 0;
			int luggage = 0;
			int daily = 0;
			int monthly = 0;
			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse alertresult = port.webGetManualTicketData(rbKey);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getManualTicket().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add("<a data-toggle='modal' role='button' href='#mymodal12344'  onclick=javascript:viewTicketDataDepotwise("
						+ alertresult.getManualTicket().get(i).getDepotid()
								.toString()
						+ ") title='Break Location' id='break_location"
						+ "'>"
						+ alertresult.getManualTicket().get(i).getDepotname()
								.toString() + "</a>");
				// ja.add(alertresult.getManualTicket().get(i).getDepotname().equals(null)?"":alertresult.getManualTicket().get(i).getDepotname());
				ja.add(alertresult.getManualTicket().get(i).getNormalCost()
						.equals(null) ? "" : alertresult.getManualTicket()
						.get(i).getNormalCost());
				ja.add(alertresult.getManualTicket().get(i).getTotalcost()
						.equals(null) ? "" : alertresult.getManualTicket()
						.get(i).getTotalcost());

				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// end manual ticket data
	// missed trip data star

	public JSONObject getTotalTripMissedData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetTotalMissedTripData(rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// end missed trip data
	// delay trip data
	public JSONObject getTotalTripDelayData() {

		JSONObject result = new JSONObject();
		int count[] = new int[3];
		String status[] = new String[3];
		int totalCount = 0;
		List l1 = null;
		JSONArray array = new JSONArray();
		try {
			String currentDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date());
			System.out.println("currentDate" + currentDate);
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			VtsResponse5 accresult = port.webGetTotalDelayTripData(rbKey);

			System.out.println("accresult"
					+ accresult.getWaybillDetails().size());
			for (int i = 0; i < accresult.getWaybillDetails().size(); i++) {
				JSONArray ja = new JSONArray();

				ja.add(accresult.getWaybillDetails().get(i).getWAYBILLNO());
				ja.add(accresult.getWaybillDetails().get(i).getSCHEDULENAME());
				ja.add(accresult.getWaybillDetails().get(i).getVEHICLENO());
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return result;
	}

	// end delay trip data

	// get manual ticket depotwise data start
	public JSONObject getManualTicketDepotwiseData(String depotid) {

		JSONObject result = new JSONObject();

		try {

			int manual = 0;
			int luggage = 0;
			int daily = 0;
			int monthly = 0;
			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..

			VtsResponse alertresult = port.webGetManualTicketDepotwiseData(
					rbKey, depotid);
			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getManualTicket().size(); i++) {
				JSONArray ja = new JSONArray();
				// ja.add(i + 1);
				ja.add(alertresult.getManualTicket().get(i).getDepotname()
						.equals(null) ? "" : alertresult.getManualTicket()
						.get(i).getDepotname());
				ja.add(alertresult.getManualTicket().get(i).getNormalCost()
						.equals(null) ? "" : alertresult.getManualTicket()
						.get(i).getNormalCost());
				ja.add(alertresult.getManualTicket().get(i).getDailyCost()
						.equals(null) ? "" : alertresult.getManualTicket()
						.get(i).getDailyCost());
				ja.add(alertresult.getManualTicket().get(i).getMonthlyCost()
						.equals(null) ? "" : alertresult.getManualTicket()
						.get(i).getMonthlyCost());
				ja.add(alertresult.getManualTicket().get(i).getLuggageCost()
						.equals(null) ? "" : alertresult.getManualTicket()
						.get(i).getLuggageCost());
				// ja.add(alertresult.getManualTicket().get(i).getTotalcost().equals(null)?"":alertresult.getManualTicket().get(i).getTotalcost());

				array.add(ja);
			}
			// result.put("bbData", headrearray);
			result.put("aaData", array);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// end manual depotwise ticket data

	// Late Departure Report
	public JSONObject getDataForLateDepartureReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, int range) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;
			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int busStopPoint = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// VtsResponse alertresult =
			// port.getDepotInoutReport(selectedDate+" 00:00:00 ",selectedDate+" 23:59:00 ",
			// selectedDate, "Depature", rbKey);
			JSONArray array = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			List<OrganisationChart> divisonList = getDivisionNameALLList();
			// System.out.println("Division List+"+divisonList.size());
			VtsResponse alertresult = null;
			int schCounts = 0;
			int totalCount = 0;
			int counter = 0;
			if (scheduleno.equals("-1")) {
				/* for (int i = 0; i <2;i++) { */
				for (int i = 0; i < divisonList.size(); i++) { // division List
					JSONArray array3 = new JSONArray();
					// System.out.println("Loop1"+i+divisonList.get(i).getOrg_name());
					//
					List<OrganisationChart> depotList = getDepotNameALLList(divisonList
							.get(i).getOrg_chart_id());
					// System.out.println("Depot List+"+depotList.size());
					/* for(int k=0;k<3;k++){ */
					for (int k = 0; k < depotList.size(); k++) {// DepotList
						// System.out.println("Loop2"+ "i"+i+"k"+k );
						busStopPoint = getDataForStartPoint(1, req, "", "",
								selectedDate, String.valueOf(depotList.get(k)
										.getOrg_chart_id()), "");

						// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
						if (busStopPoint != 0) {
							alertresult = port.getDepartureAndArrivalReport(
									selectedDate + " 00:00:00 ", selectedDate
											+ " 23:59:00 ", selectedDate,
									"Depature", String.valueOf(busStopPoint),
									0, 0, rbKey);
							// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
							int datalength = alertresult.getVtsDatamodel()
									.size() - 1;
							for (j = 0; j < alertresult.getVtsDatamodel()
									.size(); j++) {
								// if(alertresult.getVtsDatamodel().get(j).getTIMEDIFF()<-15){
								JSONArray array1 = new JSONArray();
								JSONArray array2 = new JSONArray();
								// System.out.println("Loop3"+ "i"+i+"k"+k
								// +"j"+j);
								String shift = "";
								switch (alertresult.getVtsDatamodel().get(j)
										.getDutyTypeId()) {
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
								JSONArray ja1 = new JSONArray();
								int timeDiff = Integer.parseInt(!alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString().equals("-") ? alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString() : "0");
								int desRange = 0;
								if (range == 30) {
									desRange = 60;
								} else if (range == 60) {
									desRange = 120;
								} else if (range == 120) {
									desRange = 180;
								} else if (range == 180) {
									desRange = 1800;
								}
								if (timeDiff > range && timeDiff < desRange) {
									schCounts++;
									counter++;
									System.out.println("Sch Nma"
											+ alertresult.getVtsDatamodel()
													.get(j).getSCHEDULENAME()
											+ "ActSt"
											+ alertresult.getVtsDatamodel()
													.get(j).getEXITTM());
									generalShift++;
									int temp = j;
									// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
									if (j == temp && generalShift == 1) {
										// System.out.println("Div name"+divisonList.get(i).getOrg_name());
										ja1.add(divisonList.get(i)
												.getOrg_name());
										ja1.add(depotList.get(k).getOrg_name());
									} else {
										ja1.add(" ");
										ja1.add(" ");
									}
									ja1.add(generalShift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getSCHEDULENAME());
									ja1.add(shift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getSTARTTIME());
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getEXITTM());
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getTIMEDIFF());
									array1.add(ja1);
									array.addAll(array1);
									array.addAll(array2);
								}
								if (counter > 0) {
									if (j == alertresult.getVtsDatamodel()
											.size() - 1) {
										JSONArray ja2 = new JSONArray();
										ja2.add(" ");
										ja2.add("blank");
										ja2.add("");
										ja2.add("");
										ja2.add("");
										ja2.add(" ");
										ja2.add(" ");
										ja2.add(" ");
										array2.add(ja2);
										generalShift = 0;
										// array.addAll(array1);
										array.addAll(array2);
										array1 = null;
										array2 = null;
										JSONArray ja3 = new JSONArray();
										ja3.add("Division  Total");
										ja3.add(" ");
										ja3.add("");
										ja3.add(schCounts);
										ja3.add("");
										ja3.add(" ");
										ja3.add(" ");
										ja3.add(" ");
										array3.add(ja3);
										array.addAll(array3);
										array3 = null;
										totalCount += schCounts;
										schCounts = 0;

									}
								}
								// }

								// }
							} // End data

						}// End If
							// alertresult=null;
					}// End Depot

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(totalCount);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);
			} else {
				// System.out.println("Oper");
				String parameter[] = scheduleno.trim().split("@");
				busStopPoint = getDataForStartPoint(1, req, "", "",
						selectedDate, String.valueOf(parameter[2]), "");
				// System.out.println("Oper DataBus"+busStopPoint);
				JSONArray array3 = new JSONArray();

				// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
				if (busStopPoint != 0) {
					alertresult = port.getDepartureAndArrivalReport(
							selectedDate + " 00:00:00 ", selectedDate
									+ " 23:59:00 ", selectedDate, "Depature",
							String.valueOf(busStopPoint), 0, 0, rbKey);
					// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
					// int datalength= alertresult.getVtsDatamodel().size()-1;
					for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
						JSONArray array1 = new JSONArray();
						JSONArray array2 = new JSONArray();
						// System.out.println("Loop3"+ "i"+i+"k"+k +"j"+j);
						String shift = "";
						switch (alertresult.getVtsDatamodel().get(j)
								.getDutyTypeId()) {
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

						JSONArray ja1 = new JSONArray();
						int timeDiff = Integer.parseInt(!alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString().equals("-") ? alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString() : "0");
						int desRange = 0;
						if (range == 30) {
							desRange = 60;
						} else if (range == 60) {
							desRange = 120;
						} else if (range == 120) {
							desRange = 180;
						} else if (range == 180) {
							desRange = 1800;
						}
						if (timeDiff > range && timeDiff < desRange) {
							schCounts++;
							counter++;

							generalShift++;
							int temp = j;
							// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
							if (j == temp && generalShift == 1) {
								// System.out.println("Div name"+divisonList.get(i).getOrg_name());
								ja1.add(parameter[0]);
								ja1.add(parameter[1]);
							} else {
								ja1.add(" ");
								ja1.add(" ");
							}
							ja1.add(generalShift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getSCHEDULENAME());
							ja1.add(shift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getSTARTTIME());
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getACTSTARTTIME());
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getTIMEDIFF());
							array1.add(ja1);
							array.addAll(array1);
							array.addAll(array2);
						}
						if (counter > 0) {
							if (j == alertresult.getVtsDatamodel().size() - 1) {
								System.out.println("In Json2");
								JSONArray ja2 = new JSONArray();
								ja2.add(" ");
								ja2.add("blank");
								ja2.add("");
								ja2.add("");
								ja2.add("");
								ja2.add(" ");
								ja2.add(" ");
								ja2.add(" ");
								array2.add(ja2);
								generalShift = 0;
								// array.addAll(array1);
								array.addAll(array2);
								array1 = null;
								array2 = null;
								JSONArray ja3 = new JSONArray();
								ja3.add("Division  Total");
								ja3.add(" ");
								ja3.add("");
								ja3.add(schCounts);
								ja3.add("");
								ja3.add(" ");
								ja3.add(" ");
								ja3.add(" ");
								array3.add(ja3);
								array.addAll(array3);
								array3 = null;
								totalCount += schCounts;
								schCounts = 0;

							}
						}

						// alertresult=null;
						// End Depot

					}

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(totalCount);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);

			}
			// array.addAll(array1);
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForLateDepartureSummaryReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, int range) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;
			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int busStopPoint = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// VtsResponse alertresult =
			// port.getDepotInoutReport(selectedDate+" 00:00:00 ",selectedDate+" 23:59:00 ",
			// selectedDate, "Depature", rbKey);
			JSONArray array = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			List<OrganisationChart> divisonList = getDivisionNameALLList();
			// System.out.println("Division List+"+divisonList.size());
			VtsResponse alertresult = null;
			int schCounts = 0;
			int totalCount = 0;
			int counter = 0;
			if (scheduleno.equals("-1")) {
				/* for (int i = 0; i <2;i++) { */
				for (int i = 0; i < divisonList.size(); i++) { // division List
					JSONArray array3 = new JSONArray();
					// System.out.println("Loop1"+i+divisonList.get(i).getOrg_name());
					//
					List<OrganisationChart> depotList = getDepotNameALLList(divisonList
							.get(i).getOrg_chart_id());
					// System.out.println("Depot List+"+depotList.size());
					/* for(int k=0;k<3;k++){ */
					for (int k = 0; k < depotList.size(); k++) {// DepotList
						// System.out.println("Loop2"+ "i"+i+"k"+k );
						busStopPoint = getDataForStartPoint(1, req, "", "",
								selectedDate, String.valueOf(depotList.get(k)
										.getOrg_chart_id()), "");
						int desRange = 0;
						if (range == 30) {
							desRange = 60;

						} else if (range == 60) {
							desRange = 120;

						} else if (range == 120) {
							desRange = 180;
						} else if (range == 180) {
							desRange = 1800;
						}
						// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
						if (busStopPoint != 0) {
							alertresult = port.getDepartureAndArrivalReport(
									selectedDate + " 00:00:00 ", selectedDate
											+ " 23:59:00 ", selectedDate,
									"DepartureSummary",
									String.valueOf(busStopPoint), range,
									desRange, rbKey);
							// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
							int datalength = alertresult.getVtsDatamodel()
									.size() - 1;
							for (j = 0; j < alertresult.getVtsDatamodel()
									.size(); j++) {
								// if(alertresult.getVtsDatamodel().get(j).getTIMEDIFF()<-15){
								JSONArray array1 = new JSONArray();
								JSONArray array2 = new JSONArray();
								// System.out.println("Loop3"+ "i"+i+"k"+k
								// +"j"+j);
								int dutytype = alertresult.getVtsDatamodel()
										.get(j).getDutyTypeId();
								String shift = "";
								switch (alertresult.getVtsDatamodel().get(j)
										.getDutyTypeId()) {
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
								JSONArray ja1 = new JSONArray();
								int timeDiff = Integer.parseInt(!alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString().equals("-") ? alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString() : "0");

								counter++;
								generalShift++;
								int temp = j;

								// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
								if (j == temp && generalShift == 1) {
									// System.out.println("Div name"+divisonList.get(i).getOrg_name());
									ja1.add(divisonList.get(i).getOrg_name());
									ja1.add(depotList.get(k).getOrg_name());
									ja1.add(shift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getTotalcount());
									ja1.add("");
									ja1.add("");
									ja1.add("");
								} else {
									ja1.add(" ");
									ja1.add(" ");
									ja1.add(shift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getTotalcount());
									ja1.add("");
									ja1.add("");
									ja1.add("");
								}
								array1.add(ja1);
								array.addAll(array1);
								array.addAll(array2);
								// }
								schCounts += alertresult.getVtsDatamodel()
										.get(j).getTotalcount();
								if (counter > 0) {
									if (j == alertresult.getVtsDatamodel()
											.size() - 1) {
										JSONArray ja2 = new JSONArray();
										ja2.add(" ");
										ja2.add("blank");
										ja2.add("");
										ja2.add("");
										ja2.add("");
										ja2.add(" ");
										ja2.add(" ");
										ja2.add(" ");
										array2.add(ja2);
										generalShift = 0;
										// array.addAll(array1);
										array.addAll(array2);
										array1 = null;
										array2 = null;
										JSONArray ja3 = new JSONArray();
										ja3.add("Sub  Total");
										ja3.add(" ");
										ja3.add("");
										ja3.add(schCounts);
										ja3.add("");
										ja3.add(" ");
										ja3.add(" ");
										ja3.add(" ");
										array3.add(ja3);
										array.addAll(array3);
										array3 = null;
										totalCount += schCounts;
										schCounts = 0;

									}
								}
								// }

								// }
							} // End data

						}// End If
							// alertresult=null;
					}// End Depot

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(totalCount);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);
			} else {
				// System.out.println("Oper");
				String parameter[] = scheduleno.trim().split("@");
				busStopPoint = getDataForStartPoint(1, req, "", "",
						selectedDate, String.valueOf(parameter[2]), "");
				// System.out.println("Oper DataBus"+busStopPoint);
				JSONArray array3 = new JSONArray();

				// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
				int desRange = 0;
				if (range == 30) {
					desRange = 60;

				} else if (range == 60) {
					desRange = 120;

				} else if (range == 120) {
					desRange = 180;
				} else if (range == 180) {
					desRange = 1800;
				}

				if (busStopPoint != 0) {
					alertresult = port.getDepartureAndArrivalReport(
							selectedDate + " 00:00:00 ", selectedDate
									+ " 23:59:00 ", selectedDate,
							"DepartureSummary", String.valueOf(busStopPoint),
							range, desRange, rbKey);
					// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
					// int datalength= alertresult.getVtsDatamodel().size()-1;
					for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
						JSONArray array1 = new JSONArray();
						JSONArray array2 = new JSONArray();
						// System.out.println("Loop3"+ "i"+i+"k"+k +"j"+j);
						String shift = "";
						switch (alertresult.getVtsDatamodel().get(j)
								.getDutyTypeId()) {
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

						JSONArray ja1 = new JSONArray();
						int timeDiff = Integer.parseInt(!alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString().equals("-") ? alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString() : "0");
						// int desRange=0;
						if (range == 30) {
							desRange = 60;
						} else if (range == 60) {
							desRange = 120;
						} else if (range == 120) {
							desRange = 180;
						} else if (range == 180) {
							desRange = 1800;
						}
						// if (timeDiff > range && timeDiff < desRange) {
						counter++;

						generalShift++;
						int temp = j;
						// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
						if (j == temp && generalShift == 1) {
							// System.out.println("Div name"+divisonList.get(i).getOrg_name());
							ja1.add(parameter[0]);
							ja1.add(parameter[1]);
							ja1.add(shift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getTotalcount());
							ja1.add("");
							ja1.add("");
							ja1.add("");
						} else {
							ja1.add(" ");
							ja1.add(" ");
							ja1.add(shift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getTotalcount());
							ja1.add("");
							ja1.add("");
							ja1.add("");
						}

						array1.add(ja1);
						array.addAll(array1);
						array.addAll(array2);
						schCounts += alertresult.getVtsDatamodel().get(j)
								.getTotalcount();
						// }
						if (counter > 0) {
							if (j == alertresult.getVtsDatamodel().size() - 1) {
								System.out.println("In Json2");
								JSONArray ja2 = new JSONArray();
								ja2.add(" ");
								ja2.add("blank");
								ja2.add("");
								ja2.add("");
								ja2.add("");
								ja2.add(" ");
								ja2.add(" ");
								ja2.add(" ");
								array2.add(ja2);
								generalShift = 0;
								// array.addAll(array1);
								array.addAll(array2);
								array1 = null;
								array2 = null;
								JSONArray ja3 = new JSONArray();
								ja3.add("Division  Total");
								ja3.add(" ");
								ja3.add("");
								ja3.add(schCounts);
								ja3.add("");
								ja3.add(" ");
								ja3.add(" ");
								ja3.add(" ");
								array3.add(ja3);
								array.addAll(array3);
								array3 = null;
								totalCount += schCounts;
								schCounts = 0;

							}
						}

						// alertresult=null;
						// End Depot

					}

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(totalCount);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);

			}
			// array.addAll(array1);
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public Map<Integer, String> getDivisionNameALL() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
			resultMap.put(-2, "ALL");
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	public Map<Integer, String> getAccidentType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from AccidentType acc  where status='ACTIVE' and deleted_status=0 order by accident_type_id");
		try {

			List<AccidentType> list = query.list();
			for (AccidentType acc : list) {
				resultMap.put(acc.getAccident_type_id(),
						acc.getAccident_type_name());
			}
			resultMap.put(0, "Select");

		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	public Map<Integer, String> getBreakDownTypeList() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from BreakDownTypeDetails brk where  deleted_status=0 order by breakdown_type_id");
		try {

			List<BreakDownTypeDetails> list = query.list();
			for (BreakDownTypeDetails brk : list) {
				resultMap.put(brk.getBreakdown_type_Id(),
						brk.getBreakdown_name());
			}
			resultMap.put(0, "Select");

		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	public List getDivisionNameALLList() {
		List<OrganisationChart> list = new ArrayList();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
		try {
			list = query.list();
			/*
			 * for (OrganisationChart org : list) {
			 * resultMap.put(org.getOrg_chart_id(), org.getOrg_name()); }
			 */

		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return list;
	}

	public List getDepotNameALLList(int parentId) {
		List<OrganisationChart> list = new ArrayList();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=3 and  parent_id= "

						+ parentId
						+ " and deleted_status=0 order by orgchart.org_name");
		try {
			list = query.list();
			/*
			 * for (OrganisationChart org : list) {
			 * resultMap.put(org.getOrg_chart_id(), org.getOrg_name()); }
			 */

		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return list;
	}

	// Late Departure Report
	public JSONObject getDataForEarlyArrivalReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, int range) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;
			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int busStopPoint = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// VtsResponse alertresult =
			// port.getDepotInoutReport(selectedDate+" 00:00:00 ",selectedDate+" 23:59:00 ",
			// selectedDate, "Depature", rbKey);
			JSONArray array = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			List<OrganisationChart> divisonList = getDivisionNameALLList();
			// System.out.println("Division List+"+divisonList.size());
			VtsResponse alertresult = null;
			int schCounts = 0;
			int grandTotal = 0;
			int counter = 0;
			String parameterType = "Arrival";
			if (scheduleno.equals("-1")) {
				/* for (int i = 0; i <2;i++) { */
				for (int i = 0; i < divisonList.size(); i++) { // division List
					JSONArray array3 = new JSONArray();
					// System.out.println("Loop1"+i+divisonList.get(i).getOrg_name());
					//
					List<OrganisationChart> depotList = getDepotNameALLList(divisonList
							.get(i).getOrg_chart_id());
					// System.out.println("Depot List+"+depotList.size());
					/* for(int k=0;k<3;k++){ */
					for (int k = 0; k < depotList.size(); k++) {// DepotList
						// System.out.println("Loop2"+ "i"+i+"k"+k );
						busStopPoint = getDataForStartPoint(1, req, "", "",
								selectedDate, String.valueOf(depotList.get(k)
										.getOrg_chart_id()), "");

						// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
						int desRange = 0;
						if (range == 30) {
							desRange = 60;

						} else if (range == 60) {
							desRange = 120;

						} else if (range == 120) {
							desRange = 180;
						} else if (range == 180) {
							desRange = 1800;
						}

						if (busStopPoint != 0) {
							alertresult = port.getDepartureAndArrivalReport(
									selectedDate + " 00:00:00 ", selectedDate
											+ " 23:59:00 ", selectedDate,
									parameterType,
									String.valueOf(busStopPoint), 0, 0, rbKey);
							// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
							int datalength = alertresult.getVtsDatamodel()
									.size() - 1;
							for (j = 0; j < alertresult.getVtsDatamodel()
									.size(); j++) {
								// if(alertresult.getVtsDatamodel().get(j).getTIMEDIFF()<-15){
								JSONArray array1 = new JSONArray();
								JSONArray array2 = new JSONArray();
								// System.out.println("Loop3"+ "i"+i+"k"+k
								// +"j"+j);
								String shift = "";
								switch (alertresult.getVtsDatamodel().get(j)
										.getDutyTypeId()) {
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
								JSONArray ja1 = new JSONArray();
								int timeDiff = Integer.parseInt(!alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString().equals("-") ? alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString() : "0");
								// int desRange=0;
								if (range == 30) {
									desRange = 60;
								} else if (range == 60) {
									desRange = 120;
								} else if (range == 120) {
									desRange = 180;
								} else if (range == 180) {
									desRange = 1800;
								}
								if (timeDiff > range && timeDiff < desRange) {
									schCounts++;
									counter++;
									/*
									 * System.out.println("Sch Nma"+alertresult.
									 * getVtsDatamodel()
									 * .get(j).getSCHEDULENAME(
									 * )+"ActSt"+alertresult.getVtsDatamodel()
									 * .get(j).getACTSTARTTIME());
									 */
									generalShift++;
									int temp = j;
									// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
									if (j == temp && generalShift == 1) {
										// System.out.println("Div name"+divisonList.get(i).getOrg_name());
										ja1.add(divisonList.get(i)
												.getOrg_name());
										ja1.add(depotList.get(k).getOrg_name());
									} else {
										ja1.add(" ");
										ja1.add(" ");
									}
									ja1.add(generalShift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getSCHEDULENAME());
									ja1.add(shift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getENDTIME());
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getACTTIMEARR());
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getTIMEDIFF());
									array1.add(ja1);
									array.addAll(array1);
									array.addAll(array2);
								}
								if (counter > 0) {
									if (j == alertresult.getVtsDatamodel()
											.size() - 1) {
										JSONArray ja2 = new JSONArray();
										ja2.add(" ");
										ja2.add("blank");
										ja2.add("");
										ja2.add("");
										ja2.add("");
										ja2.add(" ");
										ja2.add(" ");
										ja2.add(" ");
										array2.add(ja2);
										generalShift = 0;
										// array.addAll(array1);
										array.addAll(array2);
										array1 = null;
										array2 = null;
										JSONArray ja3 = new JSONArray();
										ja3.add("Division  Total");
										ja3.add(" ");
										ja3.add("");
										ja3.add(schCounts);
										ja3.add("");
										ja3.add(" ");
										ja3.add(" ");
										ja3.add(" ");
										array3.add(ja3);
										array.addAll(array3);
										array3 = null;
										grandTotal += schCounts;
										schCounts = 0;

									}
								}
								// }

								// }
							} // End data

						}// End If
							// alertresult=null;
					}// End Depot

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total ");
				ja4.add(" ");
				ja4.add("");
				ja4.add(grandTotal);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);
			} else {
				// System.out.println("Oper");
				String parameter[] = scheduleno.trim().split("@");
				busStopPoint = getDataForStartPoint(1, req, "", "",
						selectedDate, String.valueOf(parameter[2]), "");
				// System.out.println("Oper DataBus"+busStopPoint);
				JSONArray array3 = new JSONArray();

				// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
				if (busStopPoint != 0) {
					alertresult = port.getDepartureAndArrivalReport(
							selectedDate + " 00:00:00 ", selectedDate
									+ " 23:59:00 ", selectedDate,
							parameterType, String.valueOf(busStopPoint), 0, 0,
							rbKey);
					// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
					// int datalength= alertresult.getVtsDatamodel().size()-1;
					for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
						JSONArray array1 = new JSONArray();
						JSONArray array2 = new JSONArray();
						// System.out.println("Loop3"+ "i"+i+"k"+k +"j"+j);
						String shift = "";
						switch (alertresult.getVtsDatamodel().get(j)
								.getDutyTypeId()) {
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

						JSONArray ja1 = new JSONArray();
						int timeDiff = Integer.parseInt(!alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString().equals("-") ? alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString() : "0");
						int desRange = 0;
						if (range == 30) {
							desRange = 60;
						} else if (range == 60) {
							desRange = 120;
						} else if (range == 120) {
							desRange = 180;
						} else if (range == 180) {
							desRange = 1800;
						}
						if (timeDiff > range && timeDiff < desRange) {
							schCounts++;
							counter++;

							generalShift++;
							int temp = j;
							// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
							if (j == temp && generalShift == 1) {
								// System.out.println("Div name"+divisonList.get(i).getOrg_name());
								ja1.add(parameter[0]);
								ja1.add(parameter[1]);
							} else {
								ja1.add(" ");
								ja1.add(" ");
							}
							ja1.add(generalShift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getSCHEDULENAME());
							ja1.add(shift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getENDTIME());
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getACTTIMEARR());
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getTIMEDIFF());
							array1.add(ja1);
							array.addAll(array1);
							array.addAll(array2);
						}
						if (counter > 0) {
							if (j == alertresult.getVtsDatamodel().size() - 1) {
								System.out.println("In Json2");
								JSONArray ja2 = new JSONArray();
								ja2.add(" ");
								ja2.add("blank");
								ja2.add("");
								ja2.add("");
								ja2.add("");
								ja2.add(" ");
								ja2.add(" ");
								ja2.add(" ");
								array2.add(ja2);
								generalShift = 0;
								// array.addAll(array1);
								array.addAll(array2);
								array1 = null;
								array2 = null;
								JSONArray ja3 = new JSONArray();
								ja3.add("Division  Total");
								ja3.add(" ");
								ja3.add("");
								ja3.add(schCounts);
								ja3.add("");
								ja3.add(" ");
								ja3.add(" ");
								ja3.add(" ");
								array3.add(ja3);
								array.addAll(array3);
								array3 = null;
								grandTotal += schCounts;
								schCounts = 0;
							}
						}
						// alertresult=null;
						// End Depot

					}

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(grandTotal);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);

			}
			// array.addAll(array1);
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	public JSONObject getDataForEarlyArrivalSummaryReport(int j,
			HttpServletRequest req, String string, String string2,
			String selectedDate, String scheduleno, int range) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;
			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int busStopPoint = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// VtsResponse alertresult =
			// port.getDepotInoutReport(selectedDate+" 00:00:00 ",selectedDate+" 23:59:00 ",
			// selectedDate, "Depature", rbKey);
			JSONArray array = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			List<OrganisationChart> divisonList = getDivisionNameALLList();
			// System.out.println("Division List+"+divisonList.size());
			VtsResponse alertresult = null;
			int schCounts = 0;
			int grandTotal = 0;
			int counter = 0;
			String parameterType = "ArrivalSummary";
			if (scheduleno.equals("-1")) {
				/* for (int i = 0; i <2;i++) { */
				for (int i = 0; i < divisonList.size(); i++) { // division List
					JSONArray array3 = new JSONArray();
					// System.out.println("Loop1"+i+divisonList.get(i).getOrg_name());
					//
					List<OrganisationChart> depotList = getDepotNameALLList(divisonList
							.get(i).getOrg_chart_id());
					// System.out.println("Depot List+"+depotList.size());
					/* for(int k=0;k<3;k++){ */
					for (int k = 0; k < depotList.size(); k++) {// DepotList
						// System.out.println("Loop2"+ "i"+i+"k"+k );
						busStopPoint = getDataForStartPoint(1, req, "", "",
								selectedDate, String.valueOf(depotList.get(k)
										.getOrg_chart_id()), "");
						int desRange = 0;
						if (range == 30) {
							desRange = 60;
						} else if (range == 60) {
							desRange = 120;
						} else if (range == 120) {
							desRange = 180;
						} else if (range == 180) {
							desRange = 1800;
						}
						// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
						if (busStopPoint != 0) {
							alertresult = port.getDepartureAndArrivalReport(
									selectedDate + " 00:00:00 ", selectedDate
											+ " 23:59:00 ", selectedDate,
									parameterType,
									String.valueOf(busStopPoint), range,
									desRange, rbKey);
							// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
							int datalength = alertresult.getVtsDatamodel()
									.size() - 1;
							for (j = 0; j < alertresult.getVtsDatamodel()
									.size(); j++) {
								// if(alertresult.getVtsDatamodel().get(j).getTIMEDIFF()<-15){
								JSONArray array1 = new JSONArray();
								JSONArray array2 = new JSONArray();
								// System.out.println("Loop3"+ "i"+i+"k"+k
								// +"j"+j);
								String shift = "";
								switch (alertresult.getVtsDatamodel().get(j)
										.getDutyTypeId()) {
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
								JSONArray ja1 = new JSONArray();
								int timeDiff = Integer.parseInt(!alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString().equals("-") ? alertresult
										.getVtsDatamodel().get(j).getTIMEDIFF()
										.toString() : "0");

								// if (timeDiff > range && timeDiff < desRange)
								// {
								counter++;
								/*
								 * System.out.println("Sch Nma"+alertresult.
								 * getVtsDatamodel() .get(j).getSCHEDULENAME(
								 * )+"ActSt"+alertresult.getVtsDatamodel()
								 * .get(j).getACTSTARTTIME());
								 */
								generalShift++;
								int temp = j;
								// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
								if (j == temp && generalShift == 1) {
									// System.out.println("Div name"+divisonList.get(i).getOrg_name());
									ja1.add(divisonList.get(i).getOrg_name());
									ja1.add(depotList.get(k).getOrg_name());
									ja1.add(shift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getTotalcount());
									ja1.add("");
									ja1.add("");
									ja1.add("");
								} else {
									ja1.add(" ");
									ja1.add(" ");
									ja1.add(shift);
									ja1.add(alertresult.getVtsDatamodel()
											.get(j).getTotalcount());
									ja1.add("");
									ja1.add("");
									ja1.add("");
								}
								array1.add(ja1);
								array.addAll(array1);
								array.addAll(array2);
								// }
								schCounts += alertresult.getVtsDatamodel()
										.get(j).getTotalcount();
								// }
								if (counter > 0) {
									if (j == alertresult.getVtsDatamodel()
											.size() - 1) {
										JSONArray ja2 = new JSONArray();
										ja2.add(" ");
										ja2.add("blank");
										ja2.add("");
										ja2.add("");
										ja2.add("");
										ja2.add(" ");
										ja2.add(" ");
										ja2.add(" ");
										array2.add(ja2);
										generalShift = 0;
										// array.addAll(array1);
										array.addAll(array2);
										array1 = null;
										array2 = null;
										JSONArray ja3 = new JSONArray();
										ja3.add("Sub  Total");
										ja3.add(" ");
										ja3.add("");
										ja3.add(schCounts);
										ja3.add("");
										ja3.add(" ");
										ja3.add(" ");
										ja3.add(" ");
										array3.add(ja3);
										array.addAll(array3);
										array3 = null;
										grandTotal += schCounts;
										schCounts = 0;

									}
								}
								// }

								// }
							} // End data

						}// End If
							// alertresult=null;
					}// End Depot

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total ");
				ja4.add(" ");
				ja4.add("");
				ja4.add(grandTotal);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);
			} else {
				// System.out.println("Oper");
				String parameter[] = scheduleno.trim().split("@");
				busStopPoint = getDataForStartPoint(1, req, "", "",
						selectedDate, String.valueOf(parameter[2]), "");
				// System.out.println("Oper DataBus"+busStopPoint);
				JSONArray array3 = new JSONArray();
				int desRange = 0;
				if (range == 30) {
					desRange = 60;
				} else if (range == 60) {
					desRange = 120;
				} else if (range == 120) {
					desRange = 180;
				} else if (range == 180) {
					desRange = 1800;
				}
				// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
				if (busStopPoint != 0) {
					alertresult = port.getDepartureAndArrivalReport(
							selectedDate + " 00:00:00 ", selectedDate
									+ " 23:59:00 ", selectedDate,
							parameterType, String.valueOf(busStopPoint), range,
							desRange, rbKey);
					// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
					// int datalength= alertresult.getVtsDatamodel().size()-1;
					for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
						JSONArray array1 = new JSONArray();
						JSONArray array2 = new JSONArray();
						// System.out.println("Loop3"+ "i"+i+"k"+k +"j"+j);
						String shift = "";
						switch (alertresult.getVtsDatamodel().get(j)
								.getDutyTypeId()) {
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

						JSONArray ja1 = new JSONArray();
						int timeDiff = Integer.parseInt(!alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString().equals("-") ? alertresult
								.getVtsDatamodel().get(j).getTIMEDIFF()
								.toString() : "0");
						// int desRange=0;
						if (range == 30) {
							desRange = 60;
						} else if (range == 60) {
							desRange = 120;
						} else if (range == 120) {
							desRange = 180;
						} else if (range == 180) {
							desRange = 1800;
						}
						// if (timeDiff > range && timeDiff < desRange) {
						counter++;

						generalShift++;
						int temp = j;
						// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
						if (j == temp && generalShift == 1) {
							// System.out.println("Div name"+divisonList.get(i).getOrg_name());
							ja1.add(parameter[0]);
							ja1.add(parameter[1]);
							ja1.add(shift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getTotalcount());
							ja1.add("");
							ja1.add("");
							ja1.add("");
						} else {
							ja1.add(" ");
							ja1.add(" ");
							ja1.add(shift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getTotalcount());
							ja1.add("");
							ja1.add("");
							ja1.add("");
						}

						array1.add(ja1);
						array.addAll(array1);
						array.addAll(array2);
						schCounts += alertresult.getVtsDatamodel().get(j)
								.getTotalcount();
						// }
						if (counter > 0) {
							if (j == alertresult.getVtsDatamodel().size() - 1) {
								System.out.println("In Json2");
								JSONArray ja2 = new JSONArray();
								ja2.add(" ");
								ja2.add("blank");
								ja2.add("");
								ja2.add("");
								ja2.add("");
								ja2.add(" ");
								ja2.add(" ");
								ja2.add(" ");
								array2.add(ja2);
								generalShift = 0;
								// array.addAll(array1);
								array.addAll(array2);
								array1 = null;
								array2 = null;
								JSONArray ja3 = new JSONArray();
								ja3.add("Sub  Total");
								ja3.add(" ");
								ja3.add("");
								ja3.add(schCounts);
								ja3.add("");
								ja3.add(" ");
								ja3.add(" ");
								ja3.add(" ");
								array3.add(ja3);
								array.addAll(array3);
								array3 = null;
								grandTotal += schCounts;
								schCounts = 0;
							}
						}
						// alertresult=null;
						// End Depot

					}

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(grandTotal);
				ja4.add("");
				ja4.add(" ");
				ja4.add(" ");
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);

			}
			// array.addAll(array1);
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// trip cancle Report start

	public JSONObject getDataForTripCancleReport(int j, HttpServletRequest req,
			String string, String string2, String selectedDate,
			String scheduleno, int range) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;

			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int busStopPoint = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// VtsResponse alertresult =
			// port.getDepotInoutReport(selectedDate+" 00:00:00 ",selectedDate+" 23:59:00 ",
			// selectedDate, "Depature", rbKey);
			JSONArray array = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			List<OrganisationChart> divisonList = getDivisionNameALLList();
			// System.out.println("Division List+"+divisonList.size());
			VtsResponse alertresult = null;
			int schCounts = 0;
			int total_div_schedule = 0;

			int total_trip_cnt = 1;

			if (scheduleno.equals("-1")) {

				int total_schdeule = 0;
				/* for (int i = 0; i <2;i++) { */
				for (int i = 0; i < divisonList.size(); i++) { // division List
					JSONArray array3 = new JSONArray();
					// System.out.println("Loop1"+i+divisonList.get(i).getOrg_name());
					//
					List<OrganisationChart> depotList = getDepotNameALLList(divisonList
							.get(i).getOrg_chart_id());
					// System.out.println("Depot List+"+depotList.size());
					/* for(int k=0;k<3;k++){ */
					for (int k = 0; k < depotList.size(); k++) {// DepotList
						// System.out.println("Loop2"+ "i"+i+"k"+k );
						int div_schedule = 0;
						busStopPoint = getDataForStartPoint(1, req, "", "",
								selectedDate, String.valueOf(depotList.get(k)
										.getOrg_chart_id()), "");

						// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
						// if (busStopPoint != 0) {
						alertresult = port.getCancleTripReport(selectedDate,
								String.valueOf(depotList.get(k)
										.getOrg_chart_id()), rbKey);
						// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
						if (alertresult.getVtsDatamodel().size() == 0)

						{
							div_schedule = 0;
							// total_schdeule=0;
							// System.out.println("total_schdeule"+total_schdeule);
						}
						int datalength = alertresult.getVtsDatamodel().size() - 1;

						for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
							// if(alertresult.getVtsDatamodel().get(j).getTIMEDIFF()<-15){
							JSONArray array1 = new JSONArray();
							JSONArray array2 = new JSONArray();
							// System.out.println("Loop3"+ "i"+i+"k"+k
							// +"j"+j);

							String shift = "";
							switch (alertresult.getVtsDatamodel().get(j)
									.getDutyTypeId()) {
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
							JSONArray ja1 = new JSONArray();

							schCounts++;

							generalShift++;
							int temp = j;
							// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
							if (j == temp && generalShift == 1) {
								// System.out.println("Div name"+divisonList.get(i).getOrg_name());
								ja1.add(divisonList.get(i).getOrg_name());
								ja1.add(depotList.get(k).getOrg_name());
							} else {
								ja1.add(" ");
								ja1.add(" ");
							}
							ja1.add(generalShift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getSCHEDULENAME());

							ja1.add(shift);
							ja1.add(alertresult.getVtsDatamodel().get(j)
									.getTripNumber());
							ja1.add("");
							if (j < alertresult.getVtsDatamodel().size() - 1) {
								if (alertresult
										.getVtsDatamodel()
										.get(j)
										.getSCHEDULENAME()
										.equals(alertresult.getVtsDatamodel()
												.get(j + 1).getSCHEDULENAME())) {

									total_trip_cnt++;

								} else {
									System.out
											.println("j<alertresult.getVtsDatamodel().size()-1"
													+ total_trip_cnt);
									ja1.set(6, total_trip_cnt);
									total_trip_cnt = 1;
									div_schedule++;
									total_schdeule++;
								}

							} else {
								if (alertresult
										.getVtsDatamodel()
										.get(j - 1)
										.getSCHEDULENAME()
										.equals(alertresult.getVtsDatamodel()
												.get(j).getSCHEDULENAME())) {

									// total_trip_cnt++;
									ja1.set(6, total_trip_cnt);
								} else {
									total_schdeule++;
									div_schedule++;
									// total_trip_cnt++;
									ja1.set(6, total_trip_cnt);
									System.out
											.println("j<alertresult.getVtsDatamodel().size()-2"
													+ total_trip_cnt);
								}

							}

							array1.add(ja1);
							array.addAll(array1);
							array.addAll(array2);

							if (j == alertresult.getVtsDatamodel().size() - 1) {
								JSONArray ja2 = new JSONArray();
								ja2.add(" ");
								ja2.add("blank");
								ja2.add("");
								ja2.add("");
								ja2.add("");
								ja2.add(" ");
								ja2.add(" ");
								ja2.add(" ");
								array2.add(ja2);
								generalShift = 0;
								// array.addAll(array1);
								array.addAll(array2);
								array1 = null;
								array2 = null;
								JSONArray ja3 = new JSONArray();
								ja3.add("Division  Total");
								ja3.add(" ");
								ja3.add("");
								ja3.add(div_schedule);
								ja3.add("");
								ja3.add(" ");
								ja3.add(schCounts);
								ja3.add(" ");
								array3.add(ja3);
								array.addAll(array3);
								array3 = null;

							}
							// }

							// }
						} // End data

						// total_schdeule+=div_schedule;
						// }// End If //bus end
						// alertresult=null;
					}// End Depot
						// total_schdeule+=div_schedule;
						// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(total_schdeule);
				ja4.add("");
				ja4.add(" ");
				ja4.add(schCounts);
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);
			} else {
				int total_schdeule = 0;
				// System.out.println("Oper");
				String parameter[] = scheduleno.trim().split("@");
				// busStopPoint = getDataForStartPoint(1, req, "", "",
				// selectedDate, String.valueOf(parameter[2]), "");
				// System.out.println("Oper DataBus"+busStopPoint);
				JSONArray array3 = new JSONArray();

				int div_schedule = 0;
				// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
				// if (busStopPoint != 0) {
				alertresult = port.getCancleTripReport(selectedDate,
						String.valueOf(parameter[2]), rbKey);
				if (alertresult.getVtsDatamodel().size() == 0)

				{
					div_schedule = 0;
					total_schdeule = 0;

				}
				// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
				// int datalength= alertresult.getVtsDatamodel().size()-1;
				for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
					JSONArray array1 = new JSONArray();
					JSONArray array2 = new JSONArray();
					JSONArray array6 = new JSONArray();
					// System.out.println("Loop3"+ "i"+i+"k"+k +"j"+j);
					String shift = "";
					switch (alertresult.getVtsDatamodel().get(j)
							.getDutyTypeId()) {
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

					JSONArray ja1 = new JSONArray();
					int timeDiff = 0;
					// if (timeDiff < -range) {
					schCounts++;

					// trip count by schedule and duty
					int trip_count = 1;
					String trip_count_show = "";

					generalShift++;
					int temp = j;
					// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
					if (j == temp && generalShift == 1) {
						// System.out.println("Div name"+divisonList.get(i).getOrg_name());
						ja1.add(parameter[0]);
						ja1.add(parameter[1]);
					} else {
						ja1.add(" ");
						ja1.add(" ");
					}
					ja1.add(generalShift);
					ja1.add(alertresult.getVtsDatamodel().get(j)
							.getSCHEDULENAME());
					// if(alertresult.getVtsDatamodel().size()>=j+1)
					// {
					// if(alertresult.getVtsDatamodel().get(j)
					// .getSCHEDULENAME().equals(alertresult.getVtsDatamodel().get(j+1)
					// .getSCHEDULENAME()))
					// {
					// total_schdeule++;
					// total_trip_cnt++;
					//
					//
					// }
					// else
					// {
					// ja1.set(6, total_trip_cnt);
					// total_trip_cnt=0;
					// div_schedule++;
					// }
					//
					// }
					ja1.add(shift);
					ja1.add(alertresult.getVtsDatamodel().get(j)
							.getTripNumber());

					// if(j>0)
					// {
					//
					// if(alertresult.getVtsDatamodel().get(j)
					// .getSCHEDULENAME().equals(alertresult.getVtsDatamodel().get(j-1)
					// .getSCHEDULENAME()))
					// {
					//
					//
					//
					// trip_count++;
					//
					//
					// }
					//
					// else
					// {
					// trip_count_show=Integer.toString(trip_count);
					//
					// }
					// }
					// else
					// {
					// trip_count_show="";
					//
					// }
					// ja1.add(trip_count_show);
					// trip_count=1;
					ja1.add("");

					if (j < alertresult.getVtsDatamodel().size() - 1) {
						if (alertresult
								.getVtsDatamodel()
								.get(j)
								.getSCHEDULENAME()
								.equals(alertresult.getVtsDatamodel()
										.get(j + 1).getSCHEDULENAME())) {

							total_trip_cnt++;

						} else {
							System.out
									.println("j<alertresult.getVtsDatamodel().size()-1"
											+ total_trip_cnt);
							ja1.set(6, total_trip_cnt);
							total_trip_cnt = 1;
							div_schedule++;
							total_schdeule++;
						}

					} else {
						if (alertresult
								.getVtsDatamodel()
								.get(j - 1)
								.getSCHEDULENAME()
								.equals(alertresult.getVtsDatamodel().get(j)
										.getSCHEDULENAME())) {

							// total_trip_cnt++;
							ja1.set(6, total_trip_cnt);
						} else {
							total_schdeule++;
							div_schedule++;
							// total_trip_cnt++;
							System.out
									.println("j<alertresult.getVtsDatamodel().size()-2"
											+ total_trip_cnt);
							ja1.set(6, total_trip_cnt);
						}

					}
					// if(j>0)
					// {
					// if(!alertresult.getVtsDatamodel().get(j)
					// .getSCHEDULENAME().equals(alertresult.getVtsDatamodel().get(j-1)
					// .getSCHEDULENAME()))
					// {
					// //total_schdeule++;
					// int count=total_schdeule;
					// //div_schedule++;
					// ja1.set(6, total_schdeule);
					// count=0;
					//
					// }
					// else
					// {
					//
					// total_trip_cnt++;
					// //count=total_trip_cnt;
					// }
					// }

					// ja1.add(alertresult.getVtsDatamodel().get(j)
					// .getTripNumber());
					// ja1.add(alertresult.getVtsDatamodel().get(j)
					// .getTIMEDIFF());

					// ja1.add(1,"77");
					array1.add(ja1);

					// System.out.println();
					array.addAll(array1);
					array.addAll(array2);
					// }
					if (j == alertresult.getVtsDatamodel().size() - 1) {
						System.out.println("In Json2");
						JSONArray ja2 = new JSONArray();
						ja2.add(" ");
						ja2.add("blank");
						ja2.add("");
						ja2.add("");
						ja2.add("");
						ja2.add(" ");
						ja2.add(" ");
						ja2.add(" ");
						array2.add(ja2);
						generalShift = 0;
						// array.addAll(array1);
						array.addAll(array2);
						array1 = null;
						array2 = null;
						JSONArray ja3 = new JSONArray();
						ja3.add("Division  Total");
						ja3.add(" ");
						ja3.add("");
						ja3.add(div_schedule);
						ja3.add("");
						ja3.add(" ");
						ja3.add(schCounts);
						ja3.add(" ");
						array3.add(ja3);
						array.addAll(array3);
						array3 = null;

					}

					// alertresult=null;
					// End Depot

				}

				// array.addAll(array1);

				// }
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(total_schdeule);
				ja4.add("");
				ja4.add(" ");
				ja4.add(schCounts);
				ja4.add(" ");
				array4.add(ja4);
				array.addAll(array4);

			}
			// array.addAll(array1);
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// trip cancle Report end

	// Deviated Trip Data start

	public JSONObject getDataForDeviatedReport(int j, HttpServletRequest req,
			String string, String string2, String selectedDate,
			String scheduleno) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;
			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int busStopPoint = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// VtsResponse alertresult =
			// port.getDepotInoutReport(selectedDate+" 00:00:00 ",selectedDate+" 23:59:00 ",
			// selectedDate, "Depature", rbKey);
			JSONArray array = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			List<OrganisationChart> divisonList = getDivisionNameALLList();
			// System.out.println("Division List+"+divisonList.size());
			VtsResponse alertresult = null;
			int schCounts = 0;
			int total_trip_count = 0;
			if (scheduleno.equals("-1")) {
				/* for (int i = 0; i <2;i++) { */
				for (int i = 0; i < divisonList.size(); i++) { // division List
					JSONArray array3 = new JSONArray();
					// System.out.println("Loop1"+i+divisonList.get(i).getOrg_name());
					//
					List<OrganisationChart> depotList = getDepotNameALLList(divisonList
							.get(i).getOrg_chart_id());
					// System.out.println("Depot List+"+depotList.size());
					/* for(int k=0;k<3;k++){ */
					for (int k = 0; k < depotList.size(); k++) {// DepotList
						// System.out.println("Loop2"+ "i"+i+"k"+k );
						busStopPoint = getDataForStartPoint(1, req, "", "",
								selectedDate, String.valueOf(depotList.get(k)
										.getOrg_chart_id()), "");

						// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
						if (busStopPoint != 0) {
							int div_trip_cnt = 0;
							alertresult = port.getDeviatedTripReport(
									selectedDate, String.valueOf(depotList.get(
											k).getOrg_chart_id()), rbKey);
							// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
							int datalength = alertresult.getVtsDatamodel()
									.size() - 1;
							for (j = 0; j < alertresult.getVtsDatamodel()
									.size(); j++) {
								// if(alertresult.getVtsDatamodel().get(j).getTIMEDIFF()<-15){
								JSONArray array1 = new JSONArray();
								JSONArray array2 = new JSONArray();
								// System.out.println("Loop3"+ "i"+i+"k"+k
								// +"j"+j);
								String shift = "";
								switch (alertresult.getVtsDatamodel().get(j)
										.getDutyTypeId()) {
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
								JSONArray ja1 = new JSONArray();

								schCounts++;

								generalShift++;
								int temp = j;
								// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
								if (j == temp && generalShift == 1) {
									// System.out.println("Div name"+divisonList.get(i).getOrg_name());
									ja1.add(divisonList.get(i).getOrg_name());
									ja1.add(depotList.get(k).getOrg_name());
								} else {
									ja1.add(" ");
									ja1.add(" ");
								}
								ja1.add(generalShift);
								ja1.add(alertresult.getVtsDatamodel().get(j)
										.getSCHEDULENAME());
								ja1.add(shift);
								ja1.add(alertresult.getVtsDatamodel().get(j)
										.getDeviatedCount());
								ja1.add(alertresult.getVtsDatamodel().get(j)
										.getTRIPNUMBER());
								div_trip_cnt += Integer.parseInt(alertresult
										.getVtsDatamodel().get(j)
										.getDeviatedCount());
								total_trip_count += Integer
										.parseInt(alertresult.getVtsDatamodel()
												.get(j).getDeviatedCount());
								array1.add(ja1);
								array.addAll(array1);
								array.addAll(array2);

								if (j == alertresult.getVtsDatamodel().size() - 1) {
									JSONArray ja2 = new JSONArray();
									ja2.add(" ");
									ja2.add("blank");
									ja2.add("");
									ja2.add("");
									ja2.add("");
									ja2.add(" ");
									ja2.add(" ");
									ja2.add(" ");
									array2.add(ja2);
									generalShift = 0;
									// array.addAll(array1);
									array.addAll(array2);
									array1 = null;
									array2 = null;
									JSONArray ja3 = new JSONArray();
									ja3.add("Division  Total");
									ja3.add(" ");
									ja3.add("");
									ja3.add(schCounts);
									ja3.add("");
									ja3.add(div_trip_cnt);
									ja3.add(" ");

									array3.add(ja3);
									array.addAll(array3);
									array3 = null;

								}
								// }

							}
						} // End data

						// }// End If //bus end
						// alertresult=null;
					}// End Depot

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(schCounts);
				ja4.add("");
				ja4.add(total_trip_count);
				ja4.add(" ");

				array4.add(ja4);
				array.addAll(array4);
			} else {
				int div_trip_cnt = 0;
				// System.out.println("Oper");
				String parameter[] = scheduleno.trim().split("@");
				busStopPoint = getDataForStartPoint(1, req, "", "",
						selectedDate, String.valueOf(parameter[2]), "");
				// System.out.println("Oper DataBus"+busStopPoint);
				JSONArray array3 = new JSONArray();

				System.out.println("Depotid+" + String.valueOf(parameter[2]));
				if (busStopPoint != 0) {
					alertresult = port.getDeviatedTripReport(selectedDate,
							String.valueOf(parameter[2]), rbKey);
					// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
					// int datalength= alertresult.getVtsDatamodel().size()-1;
					for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
						JSONArray array1 = new JSONArray();
						JSONArray array2 = new JSONArray();
						// System.out.println("Loop3"+ "i"+i+"k"+k +"j"+j);
						String shift = "";
						switch (alertresult.getVtsDatamodel().get(j)
								.getDutyTypeId()) {
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

						JSONArray ja1 = new JSONArray();
						int timeDiff = 0;
						// if (timeDiff < -range) {
						schCounts++;

						// trip count by schedule and duty
						int trip_count = 1;
						String trip_count_show = "";

						generalShift++;
						int temp = j;
						// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
						if (j == temp && generalShift == 1) {
							// System.out.println("Div name"+divisonList.get(i).getOrg_name());
							ja1.add(parameter[0]);
							ja1.add(parameter[1]);
						} else {
							ja1.add(" ");
							ja1.add(" ");
						}
						ja1.add(generalShift);
						ja1.add(alertresult.getVtsDatamodel().get(j)
								.getSCHEDULENAME());

						ja1.add(shift);
						ja1.add(alertresult.getVtsDatamodel().get(j)
								.getDeviatedCount());
						div_trip_cnt += Integer.parseInt(alertresult
								.getVtsDatamodel().get(j).getDeviatedCount());
						total_trip_count += Integer.parseInt(alertresult
								.getVtsDatamodel().get(j).getDeviatedCount());
						ja1.add(alertresult.getVtsDatamodel().get(j)
								.getTRIPNUMBER());

						// if(j>0)
						// {
						//
						// if(alertresult.getVtsDatamodel().get(j)
						// .getSCHEDULENAME().equals(alertresult.getVtsDatamodel().get(j-1)
						// .getSCHEDULENAME()))
						// {
						//
						//
						//
						// trip_count++;
						//
						//
						// }
						//
						// else
						// {
						// trip_count_show=Integer.toString(trip_count);
						//
						// }
						// }
						// else
						// {
						// trip_count_show="";
						//
						// }
						// ja1.add(trip_count_show);
						// trip_count=1;
						// ja1.add("");

						// ja1.add(alertresult.getVtsDatamodel().get(j)
						// .getTripNumber());
						// ja1.add(alertresult.getVtsDatamodel().get(j)
						// .getTIMEDIFF());

						// ja1.add(1,"77");
						array1.add(ja1);

						// System.out.println();
						array.addAll(array1);
						array.addAll(array2);
						// }
						if (j == alertresult.getVtsDatamodel().size() - 1) {
							System.out.println("In Json2");
							JSONArray ja2 = new JSONArray();
							ja2.add(" ");
							ja2.add("blank");
							ja2.add("");
							ja2.add("");
							ja2.add("");
							ja2.add(" ");
							ja2.add(" ");
							ja2.add(" ");
							array2.add(ja2);
							generalShift = 0;
							// array.addAll(array1);
							array.addAll(array2);
							array1 = null;
							array2 = null;
							JSONArray ja3 = new JSONArray();
							ja3.add("Division  Total");
							ja3.add(" ");
							ja3.add("");
							ja3.add(schCounts);
							ja3.add("");
							ja3.add(div_trip_cnt);
							ja3.add("");

							array3.add(ja3);
							array.addAll(array3);
							array3 = null;

						}

						// alertresult=null;
						// End Depot

					}

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(schCounts);
				ja4.add("");
				ja4.add(total_trip_count);
				ja4.add("");

				array4.add(ja4);
				array.addAll(array4);

			}
			// array.addAll(array1);
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// end Deviated Trip data
	// Bus Skipped data

	public JSONObject getDataForBusSkippedReport(int j, HttpServletRequest req,
			String string, String string2, String selectedDate,
			String scheduleno) {

		JSONObject result = new JSONObject();

		try {
			int generalShift = 0;
			int Nightout = 0;
			int Dayout = 0;
			int DayoutShift1 = 0;
			int DayoutShift2 = 0;
			int busStopPoint = 0;
			int rowcount = 3;
			Map<Integer, Object[]> newData = new TreeMap<Integer, Object[]>();
			boolean flag = false;
			boolean gnFlag = false;
			boolean DOFlag = false;
			boolean NOFlag = false;
			boolean DOShift1 = false;
			boolean DOShift2 = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// VtsResponse alertresult =
			// port.getDepotInoutReport(selectedDate+" 00:00:00 ",selectedDate+" 23:59:00 ",
			// selectedDate, "Depature", rbKey);
			JSONArray array = new JSONArray();
			JSONArray array4 = new JSONArray();
			JsonArray array5 = new JsonArray();
			List<OrganisationChart> divisonList = getDivisionNameALLList();
			// System.out.println("Division List+"+divisonList.size());
			VtsResponse alertresult = null;
			int schCounts = 0;
			if (scheduleno.equals("-1")) {
				/* for (int i = 0; i <2;i++) { */
				for (int i = 0; i < divisonList.size(); i++) { // division List
					JSONArray array3 = new JSONArray();
					// System.out.println("Loop1"+i+divisonList.get(i).getOrg_name());
					//
					List<OrganisationChart> depotList = getDepotNameALLList(divisonList
							.get(i).getOrg_chart_id());
					// System.out.println("Depot List+"+depotList.size());
					/* for(int k=0;k<3;k++){ */
					for (int k = 0; k < depotList.size(); k++) {// DepotList
						// System.out.println("Loop2"+ "i"+i+"k"+k );
						busStopPoint = getDataForStartPoint(1, req, "", "",
								selectedDate, String.valueOf(depotList.get(k)
										.getOrg_chart_id()), "");

						// System.out.println("BusStopId+"+busStopPoint+"Depot "+depotList.get(k).getOrg_name()+"did "+depotList.get(k).getOrg_chart_id());
						if (busStopPoint != 0) {
							alertresult = port.getBusStopSkipReport(
									selectedDate, String.valueOf(depotList.get(
											k).getOrg_chart_id()), rbKey);
							// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
							int datalength = alertresult.getVtsDatamodel()
									.size() - 1;
							for (j = 0; j < alertresult.getVtsDatamodel()
									.size(); j++) {
								// if(alertresult.getVtsDatamodel().get(j).getTIMEDIFF()<-15){
								JSONArray array1 = new JSONArray();
								JSONArray array2 = new JSONArray();
								// System.out.println("Loop3"+ "i"+i+"k"+k
								// +"j"+j);
								String shift = "";
								switch (alertresult.getVtsDatamodel().get(j)
										.getDutyTypeId()) {
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
								JSONArray ja1 = new JSONArray();

								schCounts++;

								generalShift++;
								int temp = j;
								// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
								if (j == temp && generalShift == 1) {
									// System.out.println("Div name"+divisonList.get(i).getOrg_name());
									ja1.add(divisonList.get(i).getOrg_name());
									ja1.add(depotList.get(k).getOrg_name());
								} else {
									ja1.add(" ");
									ja1.add(" ");
								}
								ja1.add(generalShift);
								ja1.add(alertresult.getVtsDatamodel().get(j)
										.getSCHEDULENAME());
								ja1.add(shift);
								ja1.add(alertresult.getVtsDatamodel().get(j)
										.getTotalcount());
								ja1.add(alertresult.getVtsDatamodel().get(j)
										.getTRIPNUMBER());
								array1.add(ja1);
								array.addAll(array1);
								array.addAll(array2);

								if (j == alertresult.getVtsDatamodel().size() - 1) {
									JSONArray ja2 = new JSONArray();
									ja2.add(" ");
									ja2.add("blank");
									ja2.add("");
									ja2.add("");
									ja2.add("");
									ja2.add(" ");
									ja2.add(" ");
									ja2.add(" ");
									array2.add(ja2);
									generalShift = 0;
									// array.addAll(array1);
									array.addAll(array2);
									array1 = null;
									array2 = null;
									JSONArray ja3 = new JSONArray();
									ja3.add("Division  Total");
									ja3.add(" ");
									ja3.add("");
									ja3.add(schCounts);
									ja3.add("");
									ja3.add(schCounts);
									ja3.add(" ");

									array3.add(ja3);
									array.addAll(array3);
									array3 = null;

								}
								// }

							}
						} // End data

						// }// End If //bus end
						// alertresult=null;
					}// End Depot

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(schCounts);
				ja4.add("");
				ja4.add(schCounts);
				ja4.add(" ");

				array4.add(ja4);
				array.addAll(array4);
			} else {
				// System.out.println("Oper");
				String parameter[] = scheduleno.trim().split("@");
				busStopPoint = getDataForStartPoint(1, req, "", "",
						selectedDate, String.valueOf(parameter[2]), "");
				// System.out.println("Oper DataBus"+busStopPoint);
				JSONArray array3 = new JSONArray();

				System.out.println("Depotid+" + String.valueOf(parameter[2]));
				if (busStopPoint != 0) {
					alertresult = port.getBusStopSkipReport(selectedDate,
							String.valueOf(parameter[2]), rbKey);
					// System.out.println("Data List+"+alertresult.getVtsDatamodel().size());
					// int datalength= alertresult.getVtsDatamodel().size()-1;
					for (j = 0; j < alertresult.getVtsDatamodel().size(); j++) {
						JSONArray array1 = new JSONArray();
						JSONArray array2 = new JSONArray();
						// System.out.println("Loop3"+ "i"+i+"k"+k +"j"+j);
						String shift = "";
						switch (alertresult.getVtsDatamodel().get(j)
								.getDutyTypeId()) {
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

						JSONArray ja1 = new JSONArray();
						int timeDiff = 0;
						// if (timeDiff < -range) {
						schCounts++;

						// trip count by schedule and duty
						int trip_count = 1;
						String trip_count_show = "";

						generalShift++;
						int temp = j;
						// if(!alertresult.getVtsDatamodel().get(j).getSCHEDULENAME().equalsIgnoreCase("0")){
						if (j == temp && generalShift == 1) {
							// System.out.println("Div name"+divisonList.get(i).getOrg_name());
							ja1.add(parameter[0]);
							ja1.add(parameter[1]);
						} else {
							ja1.add(" ");
							ja1.add(" ");
						}
						ja1.add(generalShift);
						ja1.add(alertresult.getVtsDatamodel().get(j)
								.getSCHEDULENAME());

						ja1.add(shift);
						ja1.add(alertresult.getVtsDatamodel().get(j)
								.getTotalcount());
						ja1.add(alertresult.getVtsDatamodel().get(j)
								.getTRIPNUMBER());

						// if(j>0)
						// {
						//
						// if(alertresult.getVtsDatamodel().get(j)
						// .getSCHEDULENAME().equals(alertresult.getVtsDatamodel().get(j-1)
						// .getSCHEDULENAME()))
						// {
						//
						//
						//
						// trip_count++;
						//
						//
						// }
						//
						// else
						// {
						// trip_count_show=Integer.toString(trip_count);
						//
						// }
						// }
						// else
						// {
						// trip_count_show="";
						//
						// }
						// ja1.add(trip_count_show);
						// trip_count=1;
						// ja1.add("");

						// ja1.add(alertresult.getVtsDatamodel().get(j)
						// .getTripNumber());
						// ja1.add(alertresult.getVtsDatamodel().get(j)
						// .getTIMEDIFF());

						// ja1.add(1,"77");
						array1.add(ja1);

						// System.out.println();
						array.addAll(array1);
						array.addAll(array2);
						// }
						if (j == alertresult.getVtsDatamodel().size() - 1) {
							System.out.println("In Json2");
							JSONArray ja2 = new JSONArray();
							ja2.add(" ");
							ja2.add("blank");
							ja2.add("");
							ja2.add("");
							ja2.add("");
							ja2.add(" ");
							ja2.add(" ");
							ja2.add(" ");
							array2.add(ja2);
							generalShift = 0;
							// array.addAll(array1);
							array.addAll(array2);
							array1 = null;
							array2 = null;
							JSONArray ja3 = new JSONArray();
							ja3.add("Division  Total");
							ja3.add(" ");
							ja3.add("");
							ja3.add(schCounts);
							ja3.add("");
							ja3.add(schCounts);
							ja3.add("");

							array3.add(ja3);
							array.addAll(array3);
							array3 = null;

						}

						// alertresult=null;
						// End Depot

					}

					// array.addAll(array1);

				}
				JSONArray ja4 = new JSONArray();
				ja4.add("Grand Total");
				ja4.add(" ");
				ja4.add("");
				ja4.add(schCounts);
				ja4.add("");
				ja4.add(schCounts);
				ja4.add("");

				array4.add(ja4);
				array.addAll(array4);

			}
			// array.addAll(array1);
			// ExportToExcel export=new ExportToExcel();
			// export.exportToExcel(newData);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return result;
	}

	// end Bus Skipped data

	// get Waybillwise total amount start
	public int getWaybillWiseTotalAmount(String waybillno) {
		System.out.println("waybillno" + waybillno);
		int amount = 0;

		try {

			boolean flag = false;
			String trip_status = "";
			WsUtil_Service service = new WsUtil_Service();
			WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			// Query To Get Schedule Header Information..
			//

			VtsResponse5 alertresult = port.webGetTotalAmountByWaybillNo(
					waybillno, rbKey);

			JSONArray array = new JSONArray();
			for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {

				amount = alertresult.getWaybillDetails().get(i).getTotalcount();

			}
			// result.put("bbData", headrearray);

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
		}
		return amount;
	}

	// get waybill wise total amount end
	public static String getTimeDiff(String dutyDate, String time1, String time2) {
		String timeDiff = "";
		try {
			System.out.println("dutyDate" + dutyDate + "\t" + time1 + "\t"
					+ time2);
			String time3 = dutyDate + " " + time1;
			String time4 = dutyDate + " " + time2;

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date1 = format.parse(time3);
			Date date2 = format.parse(time4);
			long difference = date2.getTime() - date1.getTime();
			timeDiff = String.format(
					"%02d:%02d:%02d",
					TimeUnit.MILLISECONDS.toHours(difference),
					TimeUnit.MILLISECONDS.toMinutes(difference)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
									.toHours(difference)),
					TimeUnit.MILLISECONDS.toSeconds(difference)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
									.toMinutes(difference)));
			 System.out.println("HMS"+timeDiff);
		} catch (Exception ex) {

		} finally {

		}
		return timeDiff;
	}

	// get waybill wise total amount end
	public double getTimeDiffInMin(String dutyDate, String time1, String time2) {
		String timeDiff = "";
		long diffMinutes = 0;
		try {
			System.out.println("dutyDate" + dutyDate + "\t" + time1 + "\t"
					+ time2);
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

	public JSONObject getDataForHA(HttpServletRequest request, String fromDate,
			String tillDate) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			vehicleresult = port.webGetHarshAccelarationinfo("HA", fromDate,
					tillDate, id, rbKey);
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);

				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleAccAlertData('"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "') title='Alert Details' id='alert_details"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'>"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "</a>");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEPOTNAME());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDRIVERNAME());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getTotalcount());
				// ja.add(vehicleresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				// ja.add(vehicleresult.getVtsDatamodel().get(i).getISTDATE());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public JSONObject getDataForHD(HttpServletRequest request, String fromDate,
			String tillDate) {
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			vehicleresult = port.webGetHarshDeAccelarationinfo("HD", fromDate,
					tillDate, id, rbKey);
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);

				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getVehicleDeaccAlertData('"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "') title='Alert Details' id='alert_details"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "'>"
						+ vehicleresult.getVtsDatamodel().get(i).getVEHICLENO()
						+ "</a>");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEPOTNAME());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDRIVERNAME());
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getDRIVERTOKENNO());
				//
				ja.add(vehicleresult.getVtsDatamodel().get(i).getSCHEDULENAME());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getTotalcount());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				// ja.add(vehicleresult.getVtsDatamodel().get(i).getISTDATE());
				array.add(ja);

			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public JSONObject getDataForHarseAcc(String device_id,
			HttpServletRequest request, String fromDate, String tillDate) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			vehicleresult = port.webGetHarshAccelarationdetails(device_id,
					fromDate, tillDate, id, rbKey);
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(vehicleresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getISTDATE());
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public JSONObject getDataForHarseDeacc(String device_id,
			HttpServletRequest request, String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			vehicleresult = port.webGetHarshDeAccelarationdetails(device_id,
					fromDate, tillDate, id, rbKey);
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				ja.add(vehicleresult.getVtsDatamodel().get(i).getVEHICLENO());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getISTDATE());
				array.add(ja);

			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public JSONObject getDataForTripCancleReport(int i, HttpServletRequest req,
			String string, String string2, String selectedDate) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			JSONArray array = new JSONArray();
			String sql = "SELECT schedule_no,	trip_no,vehicle_no,route_no FROM bmtcGprs.etim_gprs_ticket "
					+ " WHERE `tkt_sub_type_short_code` = 'TC' AND `ticket_date` = '"
					+ selectedDate
					+ "' and "
					+ id
					+ " group by ETIM_NO,TRIP_NO order by TRIP_NO;";
			Query query = HibernateUtil.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int x = 0; x < aliasToValueMapList.size(); x++) {
				Map<String, Object> rs = aliasToValueMapList.get(x);
				JSONArray ja = new JSONArray();
				ja.add(x + 1);
				ja.add(rs.get("schedule_no"));
				ja.add(rs.get("trip_no"));
				ja.add(rs.get("route_no"));
				ja.add(rs.get("vehicle_no"));
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public JSONObject getDataForTripPartialReport(int i,
			HttpServletRequest req, String string, String string2,
			String selectedDate) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			JSONArray array = new JSONArray();
			String sql = "SELECT SCH_NAME,TRIP_NO,VEHICLE_NO,ROUTE_NO FROM partial_trip_details "
					+ " WHERE DUTY_DT ='"
					+ selectedDate
					+ "'  order by TRIP_NO";
			Query query = HibernateUtil.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int x = 0; x < aliasToValueMapList.size(); x++) {
				Map<String, Object> rs = aliasToValueMapList.get(x);
				JSONArray ja = new JSONArray();
				ja.add(x + 1);
				ja.add(rs.get("SCH_NAME"));
				ja.add(rs.get("TRIP_NO"));
				ja.add(rs.get("ROUTE_NO"));
				ja.add(rs.get("VEHICLE_NO"));
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public JSONObject getDataForAccidentReport(int i, HttpServletRequest req,
			String string, String string2, String selectedDate) {

		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			JSONArray array = new JSONArray();

			String sql = "SELECT vehical_id,device_id,schedual_no,driver_token_no,attended_by_name"
					+ " FROM accident_ccc where  created_date between '"
					+ selectedDate
					+ " 00:00:00' and '"
					+ selectedDate
					+ " 23:59:59'";

			// String
			// sql="SELECT schedule_no,	trip_no,vehicle_no,route_no FROM `etim_gprs_ticket`"
			// +
			// " WHERE `tkt_sub_type_short_code` = 'TC' AND `ticket_date` = '"+selectedDate+"' group by ETIM_NO,TRIP_NO order by TRIP_NO;";
			Query query = HibernateUtil.getSession("").createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			for (int x = 0; x < aliasToValueMapList.size(); x++) {
				Map<String, Object> rs = aliasToValueMapList.get(x);
				JSONArray ja = new JSONArray();
				ja.add(x + 1);
				ja.add(rs.get("vehical_id"));
				ja.add(rs.get("device_id"));
				ja.add(rs.get("schedual_no"));
				ja.add(rs.get("driver_token_no"));
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public JSONObject getDataForBreakDownReport(String selectedDate) {
		Session session = null;
		String depot_param = "";

		List<String> routelist = new ArrayList<String>();
		JSONObject result = new JSONObject();
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			org.hibernate.Transaction transaction = session.beginTransaction();
			String sql = "select vehical_id,driver_token_no,schedual_no,st.shift_type_name,bcc.breakdown_type,"
					+ " if(bcc.lattitude is null,0,bcc.lattitude) lattitude,if(bcc.longitude is null,0,bcc.longitude) longitude,bcc.notes"
					+ " from breakdown_ccc  bcc left join shift_type st on bcc.shift_type=st.shift_type_id"
					+
					// " inner join breakdown_type_details btd " +
					// " on btd.breakdown_type_Id=bcc.breakdown_type_id" +
					" where bcc.created_date between '"
					+ selectedDate
					+ " 00:00:00' " + " and '" + selectedDate + " 23:59:59' ";

			Query query = session.createSQLQuery(sql)
					.addScalar("vehical_id", Hibernate.STRING)
					.addScalar("driver_token_no", Hibernate.STRING)
					.addScalar("shift_type_name", Hibernate.STRING)
					.addScalar("schedual_no", Hibernate.STRING)
					.addScalar("lattitude", Hibernate.STRING)
					.addScalar("longitude", Hibernate.STRING)
					.addScalar("notes", Hibernate.STRING);

			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			String relatedPointType = "";
			Query query1 = session
					.createSQLQuery("select sys_value from default_system_veriable where sys_key='point_type_id' limit 1");
			if (query.list().size() > 0) {
				relatedPointType = query1.uniqueResult().toString();
			}
			String locationName = "";
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				String shift = "";
				ja.add(i + 1);
				ja.add(rs.get("vehical_id"));
				ja.add(rs.get("driver_token_no"));
				ja.add(rs.get("schedual_no"));
				ja.add(rs.get("shift_type_name"));
				if (!(rs.get("lattitude").toString().equals("0"))
						&& !(rs.get("longitude").toString().equals("0"))) {
					locationName = getStopList(
							Double.parseDouble(rs.get("lattitude").toString()),
							Double.parseDouble(rs.get("longitude").toString()),
							session, relatedPointType);
				}
				ja.add(locationName);
				ja.add(rs.get("notes"));
				array.add(ja);
			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if ((session != null) && (session.isOpen())) {
				session.close();
			}
		}
		return result;
	}

	public JSONObject getDataForScheduleMismatchReport(int i,
			HttpServletRequest req, String string, String string2,
			String formattedgivendate1) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			JSONArray array = new JSONArray();
			// Calling Web Service..
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse5 vehicleresult = null;
			vehicleresult = port.webGetScheduleMismatching(formattedgivendate1,
					rbKey);
			for (int x = 0; x < vehicleresult.getVtsDatamodel().size(); x++) {
				JSONArray ja = new JSONArray();
				ja.add(x + 1);
				ja.add(vehicleresult.getVtsDatamodel().get(x).getDEPOTNAME());
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal1'  onclick=javascript:getscheduleMismatching('"
						+ vehicleresult.getVtsDatamodel().get(x).getDEPOTID()
						+ "','"
						+ formattedgivendate1
						+ "') title='Sch Details'>"
						+ vehicleresult.getVtsDatamodel().get(x)
								.getTotalcount() + "</a>");
				array.add(ja);
			}

			// End
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("result" + result);
		return result;
	}

	public JSONObject getDataForScheduleMismatchReportDetails(int i,
			HttpServletRequest req, String string, String depot_id,
			String formattedgivendate1) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String orgtypeid = (String) req.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			JSONArray array = new JSONArray();
			// Calling Web Service For Mismatching Details..
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			model.jaxb.xml.trimax.com.VtsResponse5 vehicleresult = null;
			vehicleresult = port.webGetScheduleMismatchingDetails(
					formattedgivendate1, depot_id, rbKey);
			for (int x = 0; x < vehicleresult.getVtsDatamodel().size(); x++) {
				JSONArray ja = new JSONArray();
				ja.add(x + 1);
				ja.add(vehicleresult.getVtsDatamodel().get(x).getSCHEDULENAME());
				String shift = "";
				switch (vehicleresult.getVtsDatamodel().get(x).getDutyTypeId()) {
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
				ja.add(shift);
				ja.add(vehicleresult.getVtsDatamodel().get(x)
						.getVARIATIONSENSE());
				ja.add(vehicleresult.getVtsDatamodel().get(x).getDATASTATUS());
				ja.add(vehicleresult.getVtsDatamodel().get(x).getVEHICLENO());
				ja.add(vehicleresult.getVtsDatamodel().get(x)
						.getLICENCENUMBER());
				array.add(ja);
			}
			// End
			System.out.println("Array" + array);
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public List getVehicleIDstatus(int depotID) {
		List list = new ArrayList();
		String qry = "select vehicle_id,license_number from vehicle where deleted_status=0 and org_chart_id= "
				+ depotID + " group by vehicle_id   order by vehicle_id";
		try {
			// qry = "select v.vehicle_id,device_serial_number,v.license_number"
			// +
			// //
			// " ,d.device_id,if(gs.LAT is null,'',gs.LAT) LAT,if(gs.LNG is null,'',gs.LNG) LNG,if(gs.RADIUS is null,'',gs.RADIUS) RADIUS "
			// "	from vehicle v inner join vehicle_device vd on vd.vehicle_id = v.vehicle_id"
			// + "	inner join device d on d.device_id = vd.device_id "
			// +
			// // "  LEFT JOIN geofence_storage gs on gs.ORG_ID=" + depotID
			// "	where v.deleted_status=0 and org_chart_id= " + depotID
			// + "	and vd.status = 'active' "
			// + "	and d.deleted_status = 0 " + "	group by v.vehicle_id "
			// + "	order by v.license_number " + "";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("license_number").toString());

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}

	public JSONObject getDataForAccident(HttpServletRequest request,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("");
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			vehicleresult = port.webGetAccidentinfo("Accident", fromDate,
					tillDate, id, rbKey);
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);

				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				ticketDate =vehicleresult.getVtsDatamodel().get(i).getISTDATE();

				String dateType[] = ticketDate.split(" ");
				String y1 = dateType[0];

				String time = dateType[1];
				System.out.println(time + "," + y1);



				System.out.println("device_serial_number"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				System.out.println("orgname"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName());

				ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAllVts('"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ y1
						+ "','"
						+ time
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER()
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName().replace(" ", "")
						+ "') >"
						+ vehicleresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ vehicleresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER()
						+ "' id='vehicleid' value='"
						+ vehicleresult.getVtsDatamodel().get(i)
								.getLICENCENUMBER() + "'");

				// ja.add(vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName());
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getDRIVERTOKENNO());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDRIVERNAME());
				//
				ja.add(vehicleresult.getVtsDatamodel().get(i)
						.getScheduleNumber());

				array.add(ja);

			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	
	public JSONObject getDataForDepotIn(HttpServletRequest request,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("");
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			//System.out.println("hiiiii");
			vehicleresult = port.webGetDepotIn("Depot In", fromDate,
					tillDate, id, rbKey);
			System.out.println("hiiiii"+vehicleresult.getVtsDatamodel().size());
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				System.out.println("size is--"+vehicleresult.getVtsDatamodel().size());
				JSONArray ja = new JSONArray();
				ja.add(i + 1);

//				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
//				String ticketDate = "";
//				ticketDate =vehicleresult.getVtsDatamodel().get(i).getISTDATE();
//
//				String dateType[] = ticketDate.split(" ");
//				String y1 = dateType[0];
//
//				String time = dateType[1];
//				System.out.println(time + "," + y1);



				System.out.println("device_serial_number"+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				System.out.println("orgname"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName());
               // ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				Date ticketDate1 = new Date();
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				ticketDate = sm.format(ticketDate1);
				String time = vehicleresult.getVtsDatamodel().get(i)
						.getScheduleArrival();
				
				ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAll('"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
							+ ticketDate
							//+ "','"
							//+ time
							+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName().replace(" ", "")
						+ "') >"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "' id='vehicleid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "'");

				// ja.add(vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName());

				array.add(ja);

			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	
	public JSONObject getDataForStationaryVehicle(HttpServletRequest request,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		getReasonListDao dao=new getReasonListDao();
		try {
			Session session = HibernateUtil.getSession("");
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			//System.out.println("hiiiii");
			vehicleresult = port.webGetStationaryVehicle("Depot In", fromDate,
					tillDate, id, rbKey);
			//System.out.println("hiiiii"+vehicleresult.getVtsDatamodel().size());
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				JSONArray ja = new JSONArray();
				ja.add(i + 1);
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				String ticketDate = "";
				String check="";
				ticketDate = vehicleresult.getVtsDatamodel().get(i).getISTDATE();
				String dateType[] = ticketDate.split(" ");
				String y1 = dateType[0];
				String y2 = common.getDateFromDateTime_(y1);
				String time = dateType[1];
				String places="Non Desg.";
				String lat1=vehicleresult.getVtsDatamodel().get(i).getLAT()!=null?vehicleresult.getVtsDatamodel().get(i).getLAT():"";
				String lng1=vehicleresult.getVtsDatamodel().get(i).getLONGITUDE()!=null?vehicleresult.getVtsDatamodel().get(i).getLONGITUDE():"";
				String lat2=vehicleresult.getVtsDatamodel().get(i).getSTARTLAT()!=null?vehicleresult.getVtsDatamodel().get(i).getSTARTLAT():"";
				String lng2=vehicleresult.getVtsDatamodel().get(i).getSTARTLONG()!=null?vehicleresult.getVtsDatamodel().get(i).getSTARTLONG():"";
				String schno=vehicleresult.getVtsDatamodel().get(i).getSCHEDULENAME()!=null?vehicleresult.getVtsDatamodel().get(i).getSCHEDULENAME():"Not Mapped";
				if(!lat2.equals("") && !lng2.equals("")){
				double distance=distanceBetweenTwoLocationsInM(lat1,lng1,lat2,lng2);
				if(distance < 200){
					places="Desg.";
				}
				}
				ja.add("<a  href='#mymodal'  onclick=javascript:getShowLiveBusDetailsForStationaryVehicle('"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ y2
						+ "','"
						+ time
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName().replace(" ", "")
						+ "') >"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "' id='vehicleid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "'");

				 ja.add(vehicleresult.getVtsDatamodel().get(i).getISTDATE());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(schno);
			//	ja.add(places);
				ja.add("<a  href='#mymodal'  onclick=javascript:getShowLiveBusDetailsForStationaryVehicle1('"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
						+ y2
						+ "','"
						+ time
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName().replace(" ", "")
						+ "') >"
						+ places
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "' id='vehicleid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "'");
				ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName());
				
//				check=dao.checkReasonForStationary(vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER(),vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				
//				if(check==null || check ==""){
				
				ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal16'  onclick=javascript:getStationaryReasonData('"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()

				+ "','"
				+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()

				+ "','"
	            +ticketDate.replace(" ", "@")
				+ "') title='Alert Details' id='alert_details"
				+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
				+ "' value='"
				+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
				+ "'>"
				+"Enter Reason"+ "</a>");
//			}else{
//				ja.add(check);
//			}

				array.add(ja);

			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	
	public JSONObject getDataForWorkShopVehicle(HttpServletRequest request,
			String fromDate, String tillDate) {
		JSONObject result = new JSONObject();
		Common common = new Common();
		try {
			Session session = HibernateUtil.getSession("");
			String orgtypeid = (String) request.getSession().getAttribute(
					"orgtypeid");
			String orgchartid = (String) request.getSession().getAttribute(
					"orgchartid");

			String id = "!=0";
			if (orgtypeid.equals("1")) {
				id = " depot_id!=0";

			}
			if (orgtypeid.equals("3")) {

				id = " depot_id in('" + orgchartid + "')";
			}
			if (orgtypeid.equals("2")) {

				id = " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
						+ orgchartid + "')";
			}
			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
			// TODO initialize WS operation arguments here
			model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
			JSONArray array = new JSONArray();
			//System.out.println("hiiiii");
			vehicleresult = port.webGetWorkShopVehicle("WorkShop", fromDate,
					tillDate, id, rbKey);
			//System.out.println("hiiiii"+vehicleresult.getVtsDatamodel().size());
			for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
				System.out.println("size is--"+vehicleresult.getVtsDatamodel().size());
				JSONArray ja = new JSONArray();
				ja.add(i + 1);



				System.out.println("device_serial_number"+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				System.out.println("orgname"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName());
               // ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
				
				
//				
//				ticketDate = vehicleresult.getVtsDatamodel().get(i).getISTDATE();
//
//				String dateType[] = ticketDate.split(" ");
//				String y1 = dateType[0];
//				String y2 = common.getDateFromDateTime_(y1);
//				//String tickdate = sm.format(y1);
//				String time = dateType[1];
//				System.out.println("year is:"+y2);
//				System.out.println("time is:"+time);
				Date ticketDate1 = new Date();
				
				String ticketDate = "";
				ticketDate = sm.format(ticketDate1);
				
				
				ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAll('"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "','"
							+ ticketDate
							//+ "','"
							//+ time
							+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "','"
						+ vehicleresult.getVtsDatamodel().get(i).getOrgName().replace(" ", "")
						+ "') >"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "</a>"

						+ "<input type='hidden' name='device_id_"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()

						+ "' id='deviceid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
						+ "'"
						+ ""
						+ "<input type='hidden' name='ticketDate"
						+ ticketDate

						+ "' id='ticketDate1' value='"
						+ ticketDate
						+ "'"
						+ ""
						+ "<input type='hidden' name='vehicle_no_'"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "' id='vehicleid' value='"
						+ vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
						+ "'");

				// ja.add(vehicleresult.getVtsDatamodel().get(i).getISTDATE());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
				ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName());

				array.add(ja);

			}
			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	 public JSONObject getDataForBreakDownDetails(HttpServletRequest request, String
	 fromDate,
	 String tillDate) {
	 JSONObject result = new JSONObject();
	 Common common = new Common();
	 try {
	 Session session = HibernateUtil.getSession("");
	 String orgtypeid = (String) request.getSession().getAttribute(
	 "orgtypeid");
	 String orgchartid = (String) request.getSession().getAttribute(
	 "orgchartid");
	
	 String id = "!=0";
	 if (orgtypeid.equals("1")) {
	 id = " depot_id!=0";
	
	 }
	 if (orgtypeid.equals("3")) {
	
	 id = " depot_id in('" + orgchartid + "')";
	 }
	 if (orgtypeid.equals("2")) {
	
	 id =
	 " depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
	 + orgchartid + "')";
	 }
	 com.trimax.ws.vts.vtsutility.WsUtil_Service service = new
	 com.trimax.ws.vts.vtsutility.WsUtil_Service();
	 com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
	 // TODO initialize WS operation arguments here
	 model.jaxb.xml.trimax.com.VtsResponse vehicleresult = null;
	 JSONArray array = new JSONArray();
	 vehicleresult = port.webGetBreakDownInfo("BreakDown", fromDate,
	 tillDate, id, rbKey);
	 for (int i = 0; i < vehicleresult.getVtsDatamodel().size(); i++) {
	 JSONArray ja = new JSONArray();
	 ja.add(i + 1);
	
	
	
	    SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		String ticketDate = "";
		// ticketDate=sm.format(ticketDate1);
		System.out.println("ticketDate" + ticketDate);
		ticketDate =vehicleresult.getVtsDatamodel().get(i).getISTDATE();

		String dateType[] = ticketDate.split(" ");
		String y1 = dateType[0];

		//String y2 = common.getDateFromDateTime_(y1);
		String time = dateType[1];
		System.out.println(time + "," + y1);
	
	 //Call device_serial_number
	
	 System.out.println("device_serial_number"+vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
	 System.out.println("orgname"+vehicleresult.getVtsDatamodel().get(i).getOrgName());
	
	
	 ja.add("<a  href='#mymodal1'  onclick=javascript:getShowLiveBusDetailsAllVts('"
	 + vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
	 +"','"
	 + y1
	 + "','"
	 + time
	 + "','"
	 +vehicleresult.getVtsDatamodel().get(i)
	 .getLICENCENUMBER()
	 +"','"
	 + vehicleresult.getVtsDatamodel().get(i).getOrgName().replace(" ", "")
	 + "') >"
	 + vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER()
	 +"</a>"
	
	 + "<input type='hidden' name='device_id_"
	 + vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
	
	 + "' id='deviceid' value='"
	 + vehicleresult.getVtsDatamodel().get(i).getDEVICEID()
	 + "'"
	 + ""
	 + "<input type='hidden' name='ticketDate"
	 + ticketDate
	
	 + "' id='ticketDate1' value='"
	 + ticketDate
	 + "'"
	 +""
	 + "<input type='hidden' name='vehicle_no_'"
	 + vehicleresult.getVtsDatamodel().get(i)
	 .getLICENCENUMBER()
	 + "' id='vehicleid' value='"
	 + vehicleresult.getVtsDatamodel().get(i)
	 .getLICENCENUMBER() + "'");
	
	
	 //ja.add(vehicleresult.getVtsDatamodel().get(i).getLICENCENUMBER());
	 ja.add(vehicleresult.getVtsDatamodel().get(i).getDEVICEID());
	 ja.add(vehicleresult.getVtsDatamodel().get(i).getOrgName());
	 ja.add(vehicleresult.getVtsDatamodel().get(i).getDRIVERTOKENNO());
	 ja.add(vehicleresult.getVtsDatamodel().get(i).getDRIVERNAME());
	 //
	 ja.add(vehicleresult.getVtsDatamodel().get(i).getScheduleNumber());
	
	 array.add(ja);
	
	 }
	 result.put("aaData", array);
	 } catch (Exception ex) {
	 ex.printStackTrace();
	 }
	 return result;
	 }

	public List getVehicleID1(int parentid) {
		List list = new ArrayList();
		String qry = "select vehicle_id from vehicle where deleted_status=0 and org_chart_id= "+parentid+" and status='ACTIVE' and cause_status !='S' order by vehicle_id";
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					list.add(rs.get("vehicle_id").toString());
					// list.add( rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}
	}

	public List getVehicleName1(int parentid) {
		List list = new ArrayList();
		String qry = "select vehicle_id,license_number from vehicle where deleted_status=0 and org_chart_id= "+parentid+" and status='ACTIVE' and cause_status !='S' order by vehicle_id";
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					// list.add(rs.get("vehicle_id").toString());
					list.add(rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			return list;
		}

	}

	public Map<Integer, String> getVehicleName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		
		
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession sess = request.getSession();
        String userorgtype = (String) request.getSession().getAttribute(
                "userorgtype");
        // HttpServletRequest req=ServletActionContext.getRequest();
        String orgchartid = (String) request.getSession().getAttribute(
                "orgchartid");
        System.out.println("orgchartid----"+orgchartid);
        String param = "";
        if (!orgchartid.equals("1")) {
            param = " and org_chart_id=" + orgchartid + " ";
        }
       
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery(
							"select vehicle_id,license_number from vehicle where deleted_status=0 and status='ACTIVE'"+param+"")
					.addScalar("vehicle_id", Hibernate.INTEGER)
					.addScalar("license_number", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			// resultMap.put(0, "ALL");
			System.out.println("Size of vehicletype type List : "
					+ aliasToValueMapList.size());
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(
						Integer.parseInt(rs.get("vehicle_id").toString()), rs
								.get("license_number").toString());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	
	public List getVehicleID2(int parentid) {
		System.out.println("get Vehicle===");
		List list = new ArrayList();
		if(parentid!=0){
		String qry = "select vehicle_id from vehicle where deleted_status=0 and org_chart_id= "+parentid+" order by vehicle_id";
		try {
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					// list.add(rs.get("device_serial_number").toString()) ;
					list.add(rs.get("vehicle_id").toString());
					// list.add( rs.get("license_number").toString());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
			
		}
		}
		else{
			list.add("NA");
			System.out.println("vehicle id =0 bcz Selecting NA");
		}
		return list;
	}
	
	

	public Map<Integer, String> getBusStopName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery(
							"select bus_stop_id,CONCAT(bus_stop_name,' towards : ',ifnull(stop_direction,''),'') stop from bus_stop WHERE bus_stop_name IS NOT NULL ")
					.addScalar("bus_stop_id", Hibernate.INTEGER)
					.addScalar("stop", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			// resultMap.put(0, "ALL");
			System.out.println("Size of vehicletype type List : "
					+ aliasToValueMapList.size());
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(
						Integer.parseInt(rs.get("bus_stop_id").toString()), rs
								.get("stop").toString());
				System.out.println(rs.get("stop").toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}	
	
	
	
	
	
	public Map<Integer, String> getRouteNumber() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			Query query = session
					.createSQLQuery(
							"select route_id,route_number from route ")
					.addScalar("route_id", Hibernate.INTEGER)
					.addScalar("route_number", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			// resultMap.put(0, "ALL");
			System.out.println("Size of vehicletype type List : "
					+ aliasToValueMapList.size());
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(
						Integer.parseInt(rs.get("route_id").toString()), rs
								.get("route_number").toString());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	public JSONObject getroutedetails(int firstbusstopid, int secondbusstopid) {
		JSONObject result =new JSONObject();
		
		System.out.println("inside get id");
		String qry = "SELECT concat(route_number,'-',route_direction) route_number,route_name,via,r.route_id,r.start_point_id,r.end_point_id FROM route r "
				+ " INNER JOIN route_point rp ON rp.route_id = r.route_id "
				+ " INNER JOIN bus_stop bs ON bs.bus_stop_id = rp.bus_stop_id "
				+ " INNER JOIN route_point rp2 ON rp2.route_id = r.route_id "
				+ " INNER JOIN bus_stop bs2 ON bs2.bus_stop_id = rp2.bus_stop_id "
				+ " WHERE rp.bus_stop_id ='"+firstbusstopid+"' AND rp2.bus_stop_id ='"+secondbusstopid+"' AND rp.route_order<rp2.route_order "
						+ "AND r.effective_till='0000-00-00 00-00-00' and r.deleted_status=0" ;
		Session session = null;
		try {
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session.createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			System.out.println(aliasToValueMapList);
			JSONArray array = new JSONArray();
			if (aliasToValueMapList.size() > 0) {
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					JSONArray ja = new JSONArray();
					System.out.println("route id is:"+rs.get("route_id").toString());
					
					ja.add(i+1);
				
					
					ja.add("<a  href='#mymodal1'  onclick=javascript:getLineTracking('"+ rs.get("route_id")+"-"+rs.get("start_point_id")+"-"+rs.get("end_point_id")+ "') >"
							+ rs.get("route_number").toString().replace(" "," ")
							+ "</a>"

							+ "");
					
					//ja.add(rs.get("route_number").toString());
					ja.add(rs.get("route_name").toString());
					ja.add(rs.get("via").toString());
					array.add(ja);
				}
				result.put("aaData", array);
			}
			//System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			return result;
		}
	}
	
	public Double distanceBetweenTwoLocationsInM(String latitudeOne, String longitudeOne, String latitudeTwo, String longitudeTwo) {
        if (latitudeOne == null || latitudeTwo == null || longitudeOne == null || longitudeTwo == null) {
            return null;
        }
        double lat1=Double.parseDouble(latitudeOne);
        double lat2=Double.parseDouble(latitudeTwo);
        double lng1=Double.parseDouble(longitudeOne);
        double lng2=Double.parseDouble(longitudeTwo);
        
        Double earthRadius = 6371.0;
        Double diffBetweenLatitudeRadians = Math.toRadians( lat2-lat1 );
        Double diffBetweenLongitudeRadians = Math.toRadians(lng2 - lng1);
        Double latitudeOneInRadians = Math.toRadians(lat1);
        Double latitudeTwoInRadians = Math.toRadians(lat2);
        Double a = Math.sin(diffBetweenLatitudeRadians / 2)
                * Math.sin(diffBetweenLatitudeRadians / 2)
                + Math.cos(latitudeOneInRadians)
                * Math.cos(latitudeTwoInRadians)
                * Math.sin(diffBetweenLongitudeRadians / 2)
                * Math.sin(diffBetweenLongitudeRadians / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (earthRadius * c * 1000);
    }
	
	
	public List getCondTokenNoName(int parentID) {
		List list = new ArrayList();
		System.out.println("org---"+parentID);
		String qry = "SELECT `EMPLOYEE_ID`, `TOKEN` FROM `employee` WHERE status='Active'  and (`EMPLOYEE_DESIGNATION` = 'Conductor' or `EMPLOYEE_DESIGNATION` = 'DriverConductor') and org_chart_id='"+parentID+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("TOKEN").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getCondTokenNoId(int parentID) {
		List list = new ArrayList();
		System.out.println("org---"+parentID);
		String qry = "SELECT `EMPLOYEE_ID`, `TOKEN` FROM `employee` WHERE status='Active'  and (`EMPLOYEE_DESIGNATION` = 'Conductor' or `EMPLOYEE_DESIGNATION` = 'DriverConductor' ) and org_chart_id='"+parentID+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("EMPLOYEE_ID").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	public List getDepotWiseSchdNoName(int parentID) {
		List list = new ArrayList();
		System.out.println("org---"+parentID);
		String qry = "SELECT schedule_id,schedule_number  FROM  schedule where (status='ACTIVE' or status='NEW')"+
					" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and depot_id='"+parentID+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("schedule_number").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getDepotWiseSchdNoId(int parentID) {
		List list = new ArrayList();
		System.out.println("org---"+parentID);
		String qry = "SELECT schedule_id,schedule_number  FROM  schedule where (status='ACTIVE' or status='NEW')"+
				" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and depot_id='"+parentID+"'";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("schedule_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getDepotDateWiseScheduleNo(int parentId,String selectedDate)
	{
		List list = new ArrayList();
		System.out.println("org---"+parentId);
		String qry = "select schedule_id from schedule  where (('"+selectedDate+"' between effective_start_date and effective_end_date) || updated_date is null) " +
				" and depot_id="+parentId+"";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("schedule_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	public List getScheduleTypeId() {
		List list = new ArrayList();
		try{
		String qry = "select schedule_type_id,schedule_type_name from schedule_type where status='ACTIVE' and deleted_status=0";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("schedule_type_id").toString());
			}
		}
		HibernateUtil.closeSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List getScheduleTypeName() {
		List list = new ArrayList();
		try{
		String qry = "select schedule_type_id,schedule_type_name  from schedule_type where status='ACTIVE' and deleted_status=0";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("schedule_type_name").toString());
			}
		}
		HibernateUtil.closeSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
		
	

//Please Dont add any Method into the Class as it has crossed Maximum line of Coding (Surjeet)
			
	
}
