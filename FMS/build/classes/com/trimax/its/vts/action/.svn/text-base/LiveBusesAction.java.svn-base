package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.trimax.its.common.Common;
import com.trimax.its.dao.getReasonListDao;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;

import com.trimax.its.vehicle.dao.ETMAvailabilityDao;
import com.trimax.its.vehicle.dao.VehicleDockingReportDao;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.ScheduleTripUpdate;
import com.trimax.its.vts.dao.UpdateTripStartEndEvent;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.VtsDataModel;
import com.trimax.its.vts.model.WaybillDetails;

import com.trimx.its.OnlineWaybills.action.OnlineWaybills;
import com.trimx.its.OnlineWaybills.action.ShowOnlineWaybillDAO;

public class LiveBusesAction extends ActionSupport {
	/**
	 * 
	 */

	private String schedulename;

	public String getSchedulename() {
		return schedulename;
	}

	public void setSchedulename(String schedulename) {
		this.schedulename = schedulename;
	}

	private static final long serialVersionUID = 1L;
	List<com.trimax.ws.vts.vtsutility.VtsDataModel> list = null;
	OrganisationChart orgchart;
	private String E0;
	private String startdate;
	private String enddate;
	private String buttonname;
	private Map<Integer, String> divisionlist1;
	private Map<Integer, String> breakdownlist;

	public Map<Integer, String> getBreakdownlist() {
		return breakdownlist;
	}

	public void setBreakdownlist(Map<Integer, String> breakdownlist) {
		this.breakdownlist = breakdownlist;
	}

	private Map<Integer, String> alertlist1;

	public Map<Integer, String> getAlertlist1() {
		return alertlist1;
	}

	public void setAlertlist1(Map<Integer, String> alertlist1) {
		this.alertlist1 = alertlist1;
	}

	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String getButtonname() {
		return buttonname;
	}

	public void setButtonname(String buttonname) {
		this.buttonname = buttonname;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getE0() {
		return E0;
	}

	public void setE0(String e0) {
		E0 = e0;
	}

	public OrganisationChart getOrgchart() {
		return orgchart;
	}

	public void setOrgchart(OrganisationChart orgchart) {
		this.orgchart = orgchart;
	}

	private Map<Integer, String> divisionlist;

	private Map<Integer, String> schedulelist;

	private Map<String, String> routeList;
	private Map<Integer, String> acctypelist;
	

	public Map<Integer, String> getAcctypelist() {
		return acctypelist;
	}

	public void setAcctypelist(Map<Integer, String> acctypelist) {
		this.acctypelist = acctypelist;
	}

	public Map<String, String> getRouteList() {
		return routeList;
	}

	public void setRouteList(Map<String, String> routeList) {
		this.routeList = routeList;
	}

	public Map<Integer, String> getSchedulelist() {
		return schedulelist;
	}

	public void setSchedulelist(Map<Integer, String> schedulelist) {
		this.schedulelist = schedulelist;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getList() {
		return list;
	}

	public void setList(List<com.trimax.ws.vts.vtsutility.VtsDataModel> list) {
		this.list = list;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;

	ArrayList speedlist = new ArrayList();

	public String execute() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		// resp.setCharacterEncoding("application/json");
		HttpServletRequest req = ServletActionContext.getRequest();
		 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        // System.out.println("orgtypeid--"+orgtypeid +"orgchartid--"+orgchartid);
       //  VtsDataDAO vvt = VtsDataDAO.getInstance();
    
         if(orgtypeid.equals("2")){
        	VtsDataDAO vvt = VtsDataDAO.getInstance();
        	int parentId1=0;
        	Date date = new Date();
     		Date date1 = new Date();
     		Date addedDate2 = addDays(date1, 1);
     		int parentId=0;
//     		
//     		if(!orgtypeid.equals("1")  && !orgchartid.equals("1")){
//     			
//     		}
     		try {
     			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
     			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
     			setStartdate(sm3.format(addedDate2));
     			setEnddate(sm2.format(date));
     		} catch (Exception ex) {

     		}
			try {
				
				parentId1 = vvt.getDepot1DC(orgtypeid,orgchartid);
				System.out.println("parentId---"+parentId1);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("getDepot1DC--");
			divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId1);
			
			
	
        }
         
        else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
        	VtsDataDAO vvt = VtsDataDAO.getInstance();
    		Date date = new Date();
    		Date date1 = new Date();
    		Date addedDate2 = addDays(date1, 1);
    		try {
    			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
    			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
    			setStartdate(sm3.format(addedDate2));
    			setEnddate(sm2.format(date));
    		} catch (Exception ex) {

    		}
    		try {
    			divisionlist1 = vvt.getDepot();
//    			System.out.println(divisionlist1.size()
//    					+ "divisionlist1%%%%%%%%%%%%%%%%%%%%%%");
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		divisionlist = vvt.getDivisionName();
        }
         
        else{
         	//Our Logic
         	//System.out.println("hii east");
         	VtsDataDAO vvt = VtsDataDAO.getInstance();
 		Date date = new Date();
 		Date date1 = new Date();
 		Date addedDate2 = addDays(date1, 1);
 		int parentId=0;
// 		
// 		if(!orgtypeid.equals("1")  && !orgchartid.equals("1")){
// 			
// 		}
 		try {
 			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			setStartdate(sm3.format(addedDate2));
 			setEnddate(sm2.format(date));
 		} catch (Exception ex) {

 		}
 		try {
 			parentId = vvt.getDepot1(orgtypeid,orgchartid);
 			
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
 		//Ends..
         }
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String getCordinates() throws ParseException {
		HttpServletResponse resp = ServletActionContext.getResponse();
		// resp.setCharacterEncoding("application/json");
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		Common common = new Common();

		String latLong = "";
		VtsDataModel vs = new VtsDataModel();
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		String startDate = req.getParameter("startdate") != null ? req
				.getParameter("startdate") : "";
		String endDate = req.getParameter("enddate") != null ? req
				.getParameter("enddate") : "";
		String value = req.getParameter("value") != null ? req
				.getParameter("value") : "";
		String fromDate = "", tillDate = "";

		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sm2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = new Date(System.currentTimeMillis() - 720000L);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			if (value.equals("Submit")) {
				fromDate = common.getDateTimeFromPickerToDB(startDate);
				tillDate = common.getDateTimeFromPickerToDB(endDate);
			} else if (value.equals("ScheduleSubmit")) {

				fromDate = startDate;
				tillDate = endDate;
			} else if (value.equals("Running")) {

			} else {

				fromDate = sm.format(FromDate).toString() + " 00:00:00";
				tillDate = sm.format(FromDate).toString() + " 23:59:59";
			}

			PrintWriter a = resp.getWriter();

			String parameters = req.getParameter("DEVICE_ID");
			String DEVICE_ID = parameters;
			//System.out.println("DEVICE_ID==="+DEVICE_ID);

			// int ID=0;
			/*
			 * if(req.getParameter("ID")==null) {
			 * System.out.println("INSIDE NULL IF ..."); } else {
			 * 
			 * String id=req.getParameter("ID").toString(); ID
			 * =Integer.parseInt(id); System.out.println("INSIDE ELSE"+ID); }
			 */
			int ID = req.getParameter("ID") != null ? Integer.parseInt(req
					.getParameter("ID").toString()) : 0;
			//System.out.println("ID==="+ID);
			double totdistancee=0;
			if (DEVICE_ID.equals("ALL")) {
				int depotID = Integer.parseInt(req.getParameter("depotID"));
				list = vvt.getTrackingData(sm.format(FromDate).toString()
						+ " 00:00:00", sm.format(FromDate).toString()
						+ " 23:59:59", DEVICE_ID, ID, "0", depotID);

				for (int i = 0; i < list.size(); i++) {

					JSONArray ja = new JSONArray();
					ja.add(list.get(i).getLAT());
					ja.add(list.get(i).getLONGITUDE());
					// double
					// speed=(0.1*(Double.parseDouble(list.get(i).getSPEEDKMPH())))+(0.9*30);
					ja.add(list.get(i).getSPEEDKMPH());
					FromDate = sm1.parse(list.get(i).getISTDATE());
					ja.add(sm2.format(FromDate));
					ja.add(list.get(i).getDEVICEID());
					ja.add(list.get(i).getID());
					ja.add(list.get(i).getLICENCENUMBER());
					ja.add(list.get(i).getPhoneNumber());
					ja.add(list.get(i).getVEHICLEDIRECTION());
					ja.add(list.get(i).getVEHICLETYPE());
					
					ja.add(list.get(i).getSCHEDULENO()!=null?list.get(i).getSCHEDULENO():"NotMapped");
					ja.add(list.get(i).getCAUSESTATUS());
					ja.add(list.get(i).getDEPOTNAME());
					ja.add(list.get(i).getBusStopId());
					ja.add(list.get(i).getDutyTypeId());
					ja.add(list.get(i).getDRIVERTOKENNO()!=null ? list.get(i).getDRIVERTOKENNO():"0");
					// ja.add(Integer.parseInt(list.get(i).getACCDISTANCE()));
					// ja.add(avgspeed);
					array.add(ja);
				}

				result.put("aaData", array);

			} else {
				if (value.equals("Submit")) {
					
//					System.out.println("Inside Submit===");
//					System.out.println("fromDate==="+fromDate);
//					System.out.println("tillDate==="+tillDate);
//					System.out.println("DEVICE_ID==="+DEVICE_ID);
//					System.out.println("ID==="+ID);

					list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
							ID, "0", 0);
				} else if (value.equals("ScheduleSubmit")) {
					//System.out.println("Inside ScheduleSubmit===");
					list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
							ID, "0", 0);
				} else {
					//System.out.println("Inside else===");
					list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
							ID, "1", 0);
				}
				for (int j = 0; j < list.size() - 1; j++) {
                    if (j != list.size()) {
                        totdistancee += distanceBetweenTwoLocationsInM((list.get(j).getLAT().toString()),list.get(j).getLONGITUDE().toString(), Double.parseDouble(list.get(j + 1).getLAT()), Double.parseDouble(list.get(j + 1).getLONGITUDE()));
                    }

                }
				for (int i = 0; i < list.size(); i++) {
					JSONArray ja = new JSONArray();
					
					//Find Total Distance..
					
					//End
					
					
					/*if (speedlist.size() > 30) {
						if (Double.parseDouble(list.get(i).getSPEEDKMPH()) != 0.00) {
							speedlist.remove(0);
							speedlist.add(Double.parseDouble(list.get(i)
									.getSPEEDKMPH()));
						}
					} else {
						if (Double.parseDouble(list.get(i).getSPEEDKMPH()) != 0.00) {
							speedlist.add(Double.parseDouble(list.get(i)
									.getSPEEDKMPH()));
						}
					}*/
					double avgspeed = 0;
					/*for (int x = 0; x < speedlist.size(); x++) {
						avgspeed = avgspeed
								+ Double.parseDouble(speedlist.get(x)
										.toString());
					}*/
					avgspeed = avgspeed / speedlist.size();
					ja.add(list.get(i).getLAT());
					ja.add(list.get(i).getLONGITUDE());
					// double speed = (0.1 * (Double.parseDouble(list.get(i)
					// .getSPEEDKMPH()))) + (0.9 * 30);
					double speed = Double.parseDouble(list.get(i)
							.getSPEEDKMPH());
					ja.add(speed);
					FromDate = sm1.parse(list.get(i).getISTDATE());
					ja.add(sm2.format(FromDate));
					ja.add(list.get(i).getDEVICEID());
					ja.add(list.get(i).getID());
					ja.add(list.get(i).getLICENCENUMBER());
					ja.add(list.get(i).getPhoneNumber());
					ja.add(list.get(i).getVEHICLEDIRECTION());
					ja.add(list.get(i).getRECORDSTATUS());
					try{
					ja.add(Double.parseDouble(list.get(i).getACCDISTANCE()));
					}catch(Exception ex){
						ex.printStackTrace();
					}
					ja.add(avgspeed);
					ja.add(list.get(i).getScheduleNumber());
					ja.add(list.get(i).getACCDISTANCE());
					ja.add(list.get(i).getDEPOTNAME());
					System.out.println("Schedule_no=="+list.get(i).getSCHEDULENO());
					ja.add(list.get(i).getSCHEDULENO()!=null?list.get(i).getSCHEDULENO():"NotMapped");
					ja.add(list.get(i).getSIGNALSTRENGTH());
					ja.add(list.get(i).getPKTHEADER());
					ja.add(sm2.format(sm1.parse(list.get(i).getCREATEDDATE())));
					ja.add(list.get(i).getIGNSTATUS());
					ja.add(Math.round(totdistancee/1000));
					array.add(ja);
				}
				result.put("aaData", array);
			}

			a.print(result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}

		return null;
	}
	//Calculate Distance Between Two Points
	public static Double distanceBetweenTwoLocationsInM(String latitudeOne, String longitudeOne, Double latitudeTwo, Double longitudeTwo) {
        if (latitudeOne == null || latitudeTwo == null || longitudeOne == null || longitudeTwo == null) {
            return null;
        }
        Double earthRadius = 6371.0;
        Double diffBetweenLatitudeRadians = Math.toRadians(latitudeTwo - Double.parseDouble(latitudeOne));
        Double diffBetweenLongitudeRadians = Math.toRadians(longitudeTwo - Double.parseDouble(longitudeOne));
        Double latitudeOneInRadians = Math.toRadians(Double.parseDouble(latitudeOne));
        Double latitudeTwoInRadians = Math.toRadians(latitudeTwo);
        Double a = Math.sin(diffBetweenLatitudeRadians / 2)
                * Math.sin(diffBetweenLatitudeRadians / 2)
                + Math.cos(latitudeOneInRadians)
                * Math.cos(latitudeTwoInRadians)
                * Math.sin(diffBetweenLongitudeRadians / 2)
                * Math.sin(diffBetweenLongitudeRadians / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        if (earthRadius * c * 1000 > 5000) {
            return 0.0;
        }
        return (earthRadius * c * 1000);
    }

	public String getAlertsSuccess() {
		return "success";
	}

	public String getDashBoard() {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try {
			divisionlist1 = dao.getDepot1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String getRoutes() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getRouteId(parentId);
		List<String> l2 = dao.getRouteName(parentId);
		String regionTypeAjaxString = "";

		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getSchedule() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String date = req.getParameter("selectedDate");
		Common cm = new Common();
		String formattedgivendate = cm.getDateFromPicker(date);
		
		List<WaybillDetails> l2 = dao.getScheduleNameTrip(parentId, formattedgivendate);
		String regionTypeAjaxString = "<option value='0'>----Select----</option>";
		
		for (int i = 0; i < l2.size(); i++) {
				
			regionTypeAjaxString += "<option value=" + l2.get(i).getSchedule_no()
					+ ">" + l2.get(i).getSchedule_name() + "</option>";
		}
		System.out.println("regionTypeAjaxString" + regionTypeAjaxString);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getPerticularDepot() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        
        if(orgtypeid.equals("2")){
//        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
        		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
        		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
        		String regionTypeAjaxString = "";
        		//regionTypeAjaxString += "<option value='0'>------select------</option>";
        		for (int i = 0; i < l1.size(); i++) {
        			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
        					+ ">" + l2.get(i).toString() + "</option>";
        		}
        		HttpServletResponse response = ServletActionContext.getResponse();
        		PrintWriter out;
        		try {
        			out = response.getWriter();
        			out.print(regionTypeAjaxString);
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
//        		return null;
//        	}
		
        }
        
      
        else if(orgtypeid.equals("1") && orgchartid.equals("1")){
        	List<String> l1 = dao.getDepotId(parentId);
    		List<String> l2 = dao.getDepotName(parentId);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='0'>select</option>";
    		regionTypeAjaxString += "<option value='-1'>All</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		try {
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }

        else {
        	List<String> l1 = dao.getDepotId(parentId,orgchartid);
    		List<String> l2 = dao.getDepotName(parentId,orgchartid);
    		String regionTypeAjaxString = "";
    		//regionTypeAjaxString += "<option value='0'>------select------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		try {
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
		return null;

	}

	public String getDepotArrivalTimeData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		String depot_code = req.getParameter("depotID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForArrival(packet_code);
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String getDepotDepartureTimeData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		String depot_code = req.getParameter("depotID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForDeparture(packet_code, depot_code, req, "",
					"", sm.format(FromDate).toString() + " 00:00:00", sm
							.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;

	}

	public String getSkippedStopData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForSkipStopData(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String getSkippedStopDataNew() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForSkipStopDataNew(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String getSoSAlertData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForSosAlert(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;

	}

	public String getPerticularVehicle() {

		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getVehicleID(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		regionTypeAjaxString += "<option  value='ALL'>------ALL------</option>";
		for (int i = 0; i < l1.size(); i++) {
			String vehiclearr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<option value=" + vehiclearr[0] + ">"
					+ vehiclearr[1] + "(" + vehiclearr[0] + ")" + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	public String getPerticularVehicles() {

		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getVehicleID(parentId);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		
		for (int i = 0; i < l1.size(); i++) {
			String vehiclearr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<option value=" + vehiclearr[0] + ">"
					+ vehiclearr[1] + "(" + vehiclearr[0] + ")" + "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	public String getVehicleDataForSms() {
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		List<String> l1 = dao.getVehicleId(parentId);
		String regionTypeAjaxString = "<table border=1 id='tblData' align='center'><tr><th>Sr. No.</th><th>Vehicle</th><th>Version</th>" +
				//"<th>Geofence</th><th width='10%'>ACC</th><th>DEACC</th>" +
				"<th >COMMAND</th><th >REPLY EXISTS</th><th >COMMAND EXECUTION TIME</th><th >COMMAND EXECUTION STATUS</th><th >RESPONSE</th></tr>";
		try {
		for (int i = 0; i < l1.size(); i++) {
//			System.out.println("l1.get"+l1.get(i));
			String vehiclearr[] = l1.get(i).toString().split("@#@");
			
			int j=i+1;
			regionTypeAjaxString += "<tr><td>"+j+"</td><td><input type='checkbox' name='deviceid' value='"
					+ vehiclearr[0]
					+ "'> "
					+ vehiclearr[1]
					+ "("
					+ vehiclearr[0]
					+ ")"
					+ "("
					+ vehiclearr[6]
					+ ")"
					+ "<input type='hidden' name='bus_id_"
					+ vehiclearr[0]
					+ "' id='bus_id_"
					+ vehiclearr[0]
					+ "' value='"
					+ vehiclearr[2]
					+ "'> "
					+ "<input type='hidden' name='lat_"
					+ vehiclearr[0]
					+ "' id='lat_"
					+ vehiclearr[0]
					+ "' value='"
					+ vehiclearr[3]
					+ "'> "
					+ "<input type='hidden' name='lng_"
					+ vehiclearr[0]
					+ "' id='lng_"
					+ vehiclearr[0]
					+ "' value='"
					+ vehiclearr[4]
					+ "'> "
					+ "<input type='hidden' name='rad_"
					+ vehiclearr[0]
					+ "' id='rad_"
					+ vehiclearr[0]
					+ "' value='" + vehiclearr[5] + "'> " + "</td>";
			regionTypeAjaxString +=
					//"<td >" + vehiclearr[7] + "</td><td>" + vehiclearr[8] + "</td><td width='10%'>" + vehiclearr[9] + 
					"</td><td>" + vehiclearr[7] + "</td><td>" + vehiclearr[12] + "</td><td>" + vehiclearr[13] + "</td><td>" + vehiclearr[14] + "</td><td>" + vehiclearr[15] + "</td><td>" + vehiclearr[16] + "</td></tr>";
//			System.out.println("region==="+regionTypeAjaxString);
		}
		regionTypeAjaxString += "</table>";
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getPerticularDevice() {
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int vehicleId = Integer.parseInt(req.getParameter("val"));
		// serviceTypeIds=rmDao.getServiceId();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();

		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

		list = vvt.getDeviceIMEI(sm.format(FromDate).toString() + " 00:00:00",
				sm.format(FromDate).toString() + " 23:59:59");
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------select------</option>";
		for (int i = 0; i < list.size(); i++) {
			regionTypeAjaxString += "<option value=" + list.get(i).getID()
					+ "," + list.get(i).getDEVICEID() + ">" + "("
					+ list.get(i).getDEVICEID() + ")" + list.get(i).getID()
					+ "</option>";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getVehicleDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();

		try {
			String sessionOrgchartId = (String) session
					.getAttribute("orgchartid");
			String sessionOrgTypeId = (String) session
					.getAttribute("orgtypeid");
//			System.out.println("Orgarddd@@" + sessionOrgchartId);
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForChartVehicle(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",
					sessionOrgTypeId, sessionOrgchartId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	public String getAlertDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			resp.setContentType("application/json");
			result = dao.getDataForChart(1, req, "", "", sm.format(FromDate)
					.toString() + " 00:00:00", sm.format(FromDate).toString()
					+ " 23:59:59");

		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}

	public String gettotalAlertDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		try {
			out = resp.getWriter();
			resp.setContentType("application/json");
			wholeresult = dao.getAlerts();

			//
		} catch (Exception ex) {

		}
		// out.print(result);
		out.print(wholeresult);
		return null;
	}

	public String getAlerts() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		try {
			out = resp.getWriter();
			wholeresult = dao.getAlertsDetails(req);

			//
		} catch (Exception ex) {

		}
		// out.print(result);
		out.print(wholeresult);
		return null;
	}

	public String alertPerticularData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			if (packet_code.equalsIgnoreCase("Tampering")) {
				result = dao.getDataForChartGridTampering(packet_code, req, "",
						"", sm.format(FromDate).toString() + " 00:00:00", sm
								.format(FromDate).toString() + " 23:59:59");
			} else {
				result = dao.getDataForChartGrid(packet_code, req, "", "", sm
						.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59");
			}
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

//	public String vehiclePerticularData() {
//		HttpServletResponse resp = ServletActionContext.getResponse();
//		HttpServletRequest req = ServletActionContext.getRequest();
//		PrintWriter out = null;
//		VtsDataDAO dao = VtsDataDAO.getInstance();
//		JSONObject result = new JSONObject();
//		Date FromDate = new Date();
//		String status = req.getParameter("vehicleStatus");
////		String status = req.getParameter("alertID");
//		System.out.println("Status-----"+status);
//		ETMAvailabilityDao dao1=new ETMAvailabilityDao();
//		String limit = req.getParameter("limit")!=null?req.getParameter("limit"):"false";
//		try {
//			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
//			out = resp.getWriter();
//			resp.setContentType("application/json");
//			if(status.equalsIgnoreCase("Active")){
//			result =	dao1.getDataforActiveVehicle(req, "", "", sm
//					.format(FromDate).toString() + " 00:00:00",
//					sm.format(FromDate).toString() + " 23:59:59",limit);
//			}else if(status.equalsIgnoreCase("Nrd") || status.equalsIgnoreCase("Not reported")){
//				result =	dao1.getDataforNRDVehicle(req, "", "", sm
//						.format(FromDate).toString() + " 00:00:00",
//						sm.format(FromDate).toString() + " 23:59:59",limit);
//			}else if(status.equalsIgnoreCase("Int.Battery")){
//			result = dao1.getDataForInternalBatteryVehicle(req, "", "", sm
//					.format(FromDate).toString() + " 00:00:00",
//					sm.format(FromDate).toString() + " 23:59:59",limit);
//			}else if(status.equalsIgnoreCase("CWS")){
////				result = dao1.getDataForCWS(req, "", "", sm
////						.format(FromDate).toString() + " 00:00:00",
////						sm.format(FromDate).toString() + " 23:59:59",limit);
//				result = dao.getDataForChartGridVehicle(status, req, "", "", sm
//						.format(FromDate).toString() + " 00:00:00",
//						sm.format(FromDate).toString() + " 23:59:59",limit);
//			}else{
////				result = dao1.getDataForDWS(req, "", "", sm
////						.format(FromDate).toString() + " 00:00:00",
////						sm.format(FromDate).toString() + " 23:59:59",limit);
//				result = dao.getDataForChartGridVehicle(status, req, "", "", sm
//						.format(FromDate).toString() + " 00:00:00",
//						sm.format(FromDate).toString() + " 23:59:59",limit);
//			}
//			out.print(result);
//		} catch (Exception ex) {
//
//		} finally {
//
//		}
//		return null;
//	}
	
	public String vehiclePerticularData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		ActionForCWSDWS dao1=new ActionForCWSDWS();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String status = req.getParameter("vehicleStatus");
		String limit = req.getParameter("limit")!=null?req.getParameter("limit"):"false";
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			resp.setContentType("application/json");
			
			if(status.equalsIgnoreCase("CWS")){
				result = dao1.getDataForCWS(req, sm.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",limit,"CWS");
			}else if(status.equalsIgnoreCase("DWS")){
					result = dao1.getDataForDWS(req, sm.format(FromDate).toString() + " 00:00:00",
							sm.format(FromDate).toString() + " 23:59:59",limit,"DWS");
				}
				else{
			result = dao.getDataForChartGridVehicle(status, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",limit);
				}
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}
	
	

	public String vehicleAlertCountPerticular() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		String packet_code = request.getParameter("packet_code");
		String misc_byte = request.getParameter("misc_byte");
		String deivice_id = request.getParameter("deivice_id");

		String licence_number = request.getParameter("licence_number");

		try {
			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			if (packet_code.equalsIgnoreCase("DV")
					&& misc_byte.equalsIgnoreCase("00")) {
				result = vvt.getDataForDeviationAlert(1, request, "", "", sm
						.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",
						packet_code, misc_byte, deivice_id, licence_number);
			} else if (packet_code.equalsIgnoreCase("51")
					&& misc_byte.equalsIgnoreCase("11")) {
				result = vvt.getDataForDTamperingAlert(1, request, "", "", sm
						.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",
						packet_code, misc_byte, deivice_id, licence_number);

			} else {
				result = vvt.getDataForAlert(1, request, "", "",
						sm.format(FromDate).toString() + " 00:00:00", sm
								.format(FromDate).toString() + " 23:59:59",
						packet_code, misc_byte, deivice_id, licence_number);
			}
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
	}

	@SkipValidation
	public String pollMsg() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}

	public String insertPollSms() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String checkboxvalues[] = request.getParameterValues("deviceid");
		if ("Submit".equals("E0")) {
			for (int i = 0; i < checkboxvalues.length; i++) {
			}
		}
		return null;
	}

	public static Date addDays(Date d, int days) {
		d.setTime(d.getTime()  - 10800 * 1000);
		return d;
	}

	public String getDeviation() throws Exception {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		// schedulelist = vvt.getScheduleName();
		return "success";
	}

	public String getScheduleDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String scheduleNO = req.getParameter("scheduleNo");
		String selectedDate = req.getParameter("selectedDate") != null ? req
				.getParameter("selectedDate") : "";
		Common cm = new Common();
		String formattedgivendate = cm.getDateFromPicker(selectedDate);
//		System.out.println("formattedgivendate" + formattedgivendate);

		try {
			out = resp.getWriter();
			result = dao.getDataForSchedule(scheduleNO, formattedgivendate);
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	public String getRoutetrackinfo() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		try {
			routeList = vvt.getRouteName();
			divisionlist = vvt.getDivisionName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String getRouteTracking() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out = null;
		JSONObject result = new JSONObject();
		int routeID = Integer.parseInt(req.getParameter("routeID"));
		VtsDataDAO vvt = VtsDataDAO.getInstance();

		try {
			out = resp.getWriter();
			//result = vvt.routeTeackingData(routeID);
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
	}

	// For Scheduled vehicle Report
	public String getScheduledVehicle() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;

		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			String deviceId = request.getParameter("deviceid");
			out = response.getWriter();
			result = vvt.getScheduleNameForTracking(deviceId);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

		return null;
	}

	public String getScheduledVehicleDetails() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;

		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			String deviceId = request.getParameter("deviceid");
			out = response.getWriter();
			//result = vvt.getDataForScheduledVehicle();
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

		return null;
	}

	public String getScheduledVehiclesuccess() {
		return "success";
	}

	public String getVtuReport() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}

	public String getHOURLYVTUReport() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		String selectedDate = request.getParameter("selectedDate") != null ? request
				.getParameter("selectedDate") : "";
		int depot_id = Integer.parseInt(request.getParameter("depot_id"));
		int divid=Integer.parseInt(request.getParameter("divId"));
//		System.out.println("date==="+selectedDate+"----"+depot_id);
		
		
		String qryy="";
		String qry2="in(";
		String qry="";
		
		if(divid == 0 && depot_id == 0){      // if div and depot selected 'All'
			qryy=" !=0";
			
		}else
			
			if(divid !=0 && depot_id ==-1){         // only depot="All" selected 
				List<String> l1 = dao.getDepotId(divid);
				for (int i = 0; i < l1.size(); i++) {
					 qry +=l1.get(i).toString()+",";
				}
				qry =qry2+qry+")";                   
				String depotIn=remove(qry);
				qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
				
			}else {
				
				 // if division and depot selected
				qryy= "in('"+depot_id+"')";  // qryy="in(10)"
			}
//		System.out.println("in getting==="+qryy);
		
		try {
			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");

			Common cm = new Common();
			String formattedsectedDate = cm.getDateFromPicker(selectedDate);

			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = vvt.c(depot_id, request, formattedsectedDate,
					formattedsectedDate,qryy);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {

		}

		return null;
	}
	
	public String remove(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length()-2)==',') {
	      str = str.substring(0, str.length()-2);
	    }
	    return str;
	}
	

	public String getSkipStops() {
		return "success";
	}

	public String getSkipStopData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		try {
			Date date = new Date();
			Date dNow = new Date(); // Instantiate a Date object
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNow);
			cal.add(Calendar.MINUTE, -5);
			dNow = cal.getTime();
			SimpleDateFormat sm = new SimpleDateFormat("dddd-MM-yy HH:mm:ss");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = vvt.getDataForSkipStop(1, request, sm.format(FromDate)
					.toString(), sm.format(dNow).toString());
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;
	}

	public String getRouteDeviationData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		Date FromDate = new Date();
		PrintWriter out = null;
		try {
			
			String selected_Date=request.getParameter("selectedDate");
			int depot_id=Integer.parseInt(request.getParameter("depotid"));
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String[] cols = { "", "", "", "", "" };
			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = vvt.getDataForRouteDeviation(1, request,
					selected_Date,depot_id);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;
	}

	public String getSkippedStopDetail() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			Date FromDate = new Date();
			String packet_code = req.getParameter("alertID");
			String givenDate = req.getParameter("givenDate") != null ? req
					.getParameter("givenDate") : sm.format(FromDate).toString();
			int dutyType= Integer.parseInt(req.getParameter("dutyType"));
			out = resp.getWriter();
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataForSkipStopDetail(packet_code, req, givenDate,
					"", givenDate,
					sm.format(FromDate).toString() + " 23:59:59", session,dutyType);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public String getOverSpeedDetail() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataForOverSpeedDetail(packet_code, req, "", "", sm
					.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59", session);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}

	public String getOverSpeedDataDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			Date FromDate = new Date();
			String packet_code = req.getParameter("alertID");
			String givenDate = req.getParameter("givenDate") != null ? req
					.getParameter("givenDate") : sm.format(FromDate).toString();

			out = resp.getWriter();
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataOverSpeedDataDetails(packet_code, req, givenDate,
					"", givenDate,
					sm.format(FromDate).toString() + " 23:59:59", session);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}
	
	public String getBusBunchingDetail() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		Session session = null;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataForBusBunchingDetail(packet_code, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59", session);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}

	public String getVehicleDataForReports() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		CollectionReportDAO cdao=new CollectionReportDAO();
		Date FromDate = new Date();
		PrintWriter out = null;
		Common common = new Common();
		String startDate = request.getParameter("startdate") != null ? request
				.getParameter("startdate") : "";
		String endDate = request.getParameter("enddate") != null ? request
				.getParameter("enddate") : "";
//				System.out.println("start and end"+startDate+"----"+endDate);

				String vehNo=request.getParameter("vehicleNo");
//				System.out.println("device id of vehicle==="+vehNo);
				String depot_id = request.getParameter("depot_id");
//				System.out.println("depot  "+depot_id);
		String fromDate = "", tillDate = "";
		 fromDate=cdao.getDateFromPickerDate(startDate);
			 tillDate=cdao.getDateFromPickerDate(endDate);

		try {
//			System.out.print(" rrom " + fromDate + "tillDa" + tillDate);
			// String selectedDate = request.getParameter("selectedDate");
			
			Date dNow = new Date(); // Instantiate a Date object
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNow);
			cal.add(Calendar.MINUTE, -5);
			dNow = cal.getTime();

			JSONObject result = new JSONObject();
			out = response.getWriter();
			result = vvt.getVehicleDataReports(fromDate, tillDate, depot_id,vehNo);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

		return null;

	}

	public String getAlertReport() {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		try {

			divisionlist1 = dao.getDepot1();
			alertlist1 = dao.getAlertRouteReport();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String getAlertReportonChange() {
//		System.out.println("hete");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertID = req.getParameter("alertID");
		String depotID = req.getParameter("depotID");
		String givendate = req.getParameter("givendate");
		String todate= req.getParameter("todate");
//		System.out.println("todate and from"+givendate+"---"+todate);
		Session session = null;
		int startPoint = 0;
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			Common cm = new Common();
			startPoint = getStartPoint(depotID);
			// System.out.println();
//			System.out.println("formatted  date is."
//					+ cm.getDateFromPicker(givendate) + "\t" + alertID);
		
			if(todate==null || todate.equalsIgnoreCase("")){
				todate=givendate;
			}
			String formattedTodate= cm.getDateFromPicker(todate);
			String formattedgivendate = cm.getDateFromPicker(givendate);
		
			out = resp.getWriter();
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			result = dao.getDataForAlertReport(alertID, req,
					formattedgivendate, formattedgivendate, depotID,
					formattedgivendate, session, startPoint,formattedTodate);
//			System.out.println("result====="+result);
			out.print(result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}
		return null;
	}

	
	
	
	
	
	private int getStartPoint(String depot_id) {
		// TODO Auto-generated method stub
		int param = 0;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select bus_stop_id from bus_stop where org_chart_id ='"
					+ depot_id + "' and status='Approved' limit 1 ";
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

	public String vehiclewisedetail() {
		VtsDataDAO dao = VtsDataDAO.getInstance();
		Date date = new Date();
		Date date1 = new Date();
		Date addedDate2 = addDays(date1, 1);
		try {
//			System.out.println("date is==="+date+"==="+date1);
//			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
//			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - 00:00");
//			setStartdate(sm3.format(date));
//			setEnddate(sm2.format(date));
			
			try {
				HttpServletRequest req = ServletActionContext.getRequest();
				 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
				VtsDataDAO vvt = VtsDataDAO.getInstance();
				if(orgtypeid.equals("2")){
		        	//Our Logic
				int parentId=0;
				try {
				} catch (Exception ex) {

				}
				try {
					parentId = vvt.getDepot1DC(orgtypeid,orgchartid);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId);
				//Ends..
		        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
				divisionlist = vvt.getDivisionName();
				//schedulelist = vvt.getScheduleName();
		        }else{
		        	int parentId=0;
					try {
					} catch (Exception ex) {

					}
					try {
						parentId = vvt.getDepot1(orgtypeid,orgchartid);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}


			 
			
//			divisionlist = dao.getDivisionName();
//
//			divisionlist1 = dao.getDepot1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";

		// /return "success";
	}
	
	public String getDivision(String orgtypeid,String id){
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivision(orgtypeid,id);

		return "success";
	}

	public String getScheduleHeaderData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getScheduleHeader(Integer.parseInt(Scheduleno));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	/**
	 * Start of Schedule TRIP status REport
	 */
	public String showScheduledTripStatusData() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		ScheduleTripDurationDAO dao = new ScheduleTripDurationDAO();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		String depotName = req.getParameter("depotName");
		System.out.println("depotName----"+depotName);
		Common cm = new Common();
		String formattedgivendate = cm.getDateFromPicker(SelectedDate);
		// TODO initialize WS operation arguments here

		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForScheduleTripStatusReport(1, req, "", "",
					formattedgivendate, Scheduleno,depotName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String divisionlist() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showtripReportDetails() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String Id = req.getParameter("id").toString();
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao
					.getDataForScheduleReportDetails(1, req, "", "", "", Id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	public String viewTripWiseVTUPacketAnalysis() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			// schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showTripVtuPacketAnalysis() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		System.out.println("Scheduleno..." + Scheduleno + "SelectedDate..."
				+ SelectedDate);

		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getTripVtuPacketReport(1, req, "", "", SelectedDate,
					Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String viewTripVtuPacketRecieved() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		// deviceid+"&selectedDate="+selectedDate+"&startTime="+startTime+"&endTime="+endTime
		String SelectedDate = req.getParameter("selectedDate").toString();
		String deviceId = req.getParameter("deviceid");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getTripVtuPacketRecieved(req, deviceId, SelectedDate,
					startTime, endTime);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getScheduleDeviation() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		String tripno = req.getParameter("tripno");
		System.out.println("Schedulenowww..." + Scheduleno + "SelectedDate..."
				+ SelectedDate);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			// result = dao.getDataForScheduleTripStatusReport(1, req, "",
			// "",SelectedDate,Scheduleno);
			result = dao.getDataForScheduleDeviaton(1, req, "", "",
					SelectedDate, Scheduleno, tripno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	// /////////////////////////////2nd/////////
	public String showScheduleDeviationData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
				+ SelectedDate);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForScheduleDevaitionInfo(1, req, "", "",
					SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	VtsDataDAO dao = VtsDataDAO.getInstance();

	public String earlyreport() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String sosreport() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String accidentalert() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}
	
	public String accidentalertvts() throws Exception {

//		divisionlist1 = dao.getDepot1();
//		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}


	public String overspeed() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String breakdownreport() throws Exception {

		divisionlist = dao.getDivisionNameALL();
		breakdownlist = dao.getBreakDownTypeList();
		return "success";
	}
	
	
	public String breakdownreportvts() throws Exception {

//		divisionlist = dao.getDivisionNameALL();
//		breakdownlist = dao.getBreakDownTypeList();
		return "success";
	}


	public String schearlydept() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String schlatedept() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String latedeptreport() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String tamperingreport() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String skipstopreport() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}
	
	public String stationaryvehiclereport() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}
	public String workshopreportdetails() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}
	public String getHaReport() throws Exception{
		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}
	public String getHdReport() throws Exception{
		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	public String getSosDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String device_ID = req.getParameter("device_ID").toString();
		String fromDate = req.getParameter("fromDate").toString();
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForSosDetails(device_ID, fromDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	// extract trip report
	public String getExtractTripStatus() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showExtractdTripReportData() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String depotNo = req.getParameter("depotNo").toString();
		System.out.println("SelectedDate................" + SelectedDate);
		// String Scheduleno = req.getParameter("scheduleNo");

		// TODO initialize WS operation arguments here

		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForExtractTripStatusReport(1, req, "", "",
					SelectedDate, depotNo);
			// System.out.println("result........"+result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	// schedule wise data

	public String showScheduleWiseReportDetails() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		// String Id = req.getParameter("id").toString();
		String SelectedDate = req.getParameter("selectdate").toString();
		String Scheduleno = req.getParameter("scheduleno").toString();
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getScheduleWiseReportDetails(1, req, "", "",
					SelectedDate, Scheduleno);
			System.out.println("result.............>>>>>>>" + result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	// end
	/**
	 * 
	 * @viewUnauthorizedBreakLocationReport
	 */
	public String viewUnauthorizedBreakLocationReport() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 
	 * @showUnAuthorizedBreakLocationList
	 */
	public String showUnAuthorizedBreakLocationList() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String depotNo = req.getParameter("depotNo").toString();
		String selectedDate = req.getParameter("selectedDate").toString();
		Common cm = new Common();
		String formattedgivendate = cm.getDateFromPicker(selectedDate);
		String fromDate = formattedgivendate + " 00:00:00";
		String toDate = formattedgivendate + " 23:59:59";
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForUnAuthorizedBreakLocationReport(depotNo,
					formattedgivendate, fromDate, toDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	/**
	 * 
	 * @showbreaklocationList
	 */
	public String showbreaklocationList() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String vehicleNoWithdate = req.getParameter("id").toString();
		String s[] = vehicleNoWithdate.split("--");
		String vehicleNo = s[0];
		String fromDate = s[1] + " 00:00:00";
		String toDate = s[1] + " 23:59:59";

		try {

			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForUnAuthorizedBreakLocations(vehicleNo,
					fromDate, toDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String depotdeptDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String device_id = req.getParameter("deviceID").toString();
		String selecteTime = req.getParameter("selecteTime").toString();
		String vehicleno = req.getParameter("vehicleno").toString();
		try {

			resp.setContentType("application/json");
			out = resp.getWriter();
			Common cm = new Common();
			String formattedgivendate = cm.getDateFromPicker(selecteTime);
			result = dao.getDepotDeptDetails(device_id, formattedgivendate,
					vehicleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String depotArrDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String device_id = req.getParameter("deviceID").toString();
		String selecteTime = req.getParameter("selecteTime").toString();
		String vehicleno = req.getParameter("vehicleno").toString();
		try {

			resp.setContentType("application/json");
			out = resp.getWriter();
			Common cm = new Common();
			String formattedgivendate = cm.getDateFromPicker(selecteTime);
			result = dao.getDepotArrDetails(device_id, formattedgivendate,
					vehicleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	/**
	 * 
	 * @viewAccidentAnalysisReport
	 */
	public String viewAccidentAnalysisReport() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionNameALL();
			acctypelist = vvt.getAccidentType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	

	/**
	 * 
	 * @showAccidentAnalysisReportList
	 */
	public String showAccidentAnalysisReportList() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String depotNo = req.getParameter("depotNo").toString();
		String selectedDate1 = req.getParameter("selectedDate1").toString();
		String selectedDate2 = req.getParameter("selectedDate2").toString();
		String accType=req.getParameter("acctype").toString();
		Common cm = new Common();
		String formattedgivendate1 = cm.getDateFromPicker(selectedDate1);
		String formattedgivendate2 = cm.getDateFromPicker(selectedDate2);
		
		try {

			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForAccidentAnalysisList(depotNo,
					formattedgivendate1, formattedgivendate1, formattedgivendate2,accType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	public String breakDownReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String depotNo = req.getParameter("depotNo").toString();
		String selectedDate = req.getParameter("selectedDate").toString();
		String brkType = req.getParameter("breakdowntype").toString();
		Common cm = new Common();
		String formattedgivendate1 = cm.getDateFromPicker(selectedDate);
				try {

			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForBreakDown(depotNo,
					formattedgivendate1,brkType);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String sarthiDeviation() throws Exception {

		divisionlist1 = dao.getDepot1();
		alertlist1 = dao.getAlertRouteReport();
		return "success";
	}

	// Sarthi Deviation Report Data
	public String showSarthiDeviationData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("givendate").toString();
		Common common = new Common();
		SelectedDate = common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
				"yyyy-mm-dd");
		String Scheduleno = req.getParameter("alertID");
		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
				+ SelectedDate);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForSarthiDevaitionData(1, req, "", "",
					SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	// sarthi Deviation on Click of Vehicle Number Info
	public String showSarthiDeviationInformation() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("givendate").toString();
		String deviceId = req.getParameter("deviceId").toString();
		Common common = new Common();
		SelectedDate = common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
				"yyyy-mm-dd");
		String Scheduleno = req.getParameter("alertID");
		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
				+ SelectedDate + "deviceId" + deviceId);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForSarthiDevaitionInformation(1, req, "", "",
					SelectedDate, Scheduleno, deviceId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getRouteNo() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		Common common = new Common();
		String date = req.getParameter("selectedDate");
		List<Integer> l1 = dao.getRouteID(common.getDateFromPicker(date));
		List<String> l2 = dao.getRouteName(common.getDateFromPicker(date));
//		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String regionTypeAjaxString = "<option value='0'>----Select----</option><option value='-1'> ALL </option>";
		try {
			for (int i = 0; i < l1.size(); i++) {

				regionTypeAjaxString += "<option value=" + l1.get(i).toString()
						+ ">" + l2.get(i).toString() + "</option>";
			}
//			System.out.println("regionTypeAjaxString" + regionTypeAjaxString);
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String showBusBunchingData() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Common common = new Common();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String routeNo = req.getParameter("routeID");
		try {

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForBusBunchingReport(1, req, "", "",
					common.getDateFromPicker(SelectedDate), routeNo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getScheduleALL() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String date = req.getParameter("selectedDate");
		List<String> l1 = dao.getScheduleID(parentId, date);
		List<String> l2 = dao.getScheduleName(parentId, date);
		String regionTypeAjaxString = "<option value='0'>----Select----</option>"
				+ "<option value='-1'>---ALL----</option>";

		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
//		System.out.println("regionTypeAjaxString" + regionTypeAjaxString);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String showScheduleTripWiseDistace() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo").toString();
		String BusstopId = req.getParameter("busStopId").toString();
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForScheduleWiseTripDistace(1, req, "", "",
					SelectedDate, Scheduleno, BusstopId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String showStartPointForDepot() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int startPoint = 0;
		String SelectedDate = req.getParameter("selectedDate").toString();

		String Scheduleno = req.getParameter("depotid").toString();
		String BusstopId = "";
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");
//
//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			startPoint = dao.getDataForStartPoint(1, req, "", "",
					common.getDateFromPicker(SelectedDate), Scheduleno,
					BusstopId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(startPoint);
		return null;
	}

	// schedule adherence Report
	public String getscheduleAdherence() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showScheduleAdherenceData() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		// String SelectDate
		String Scheduleno = req.getParameter("scheduleNo");

		// TODO initialize WS operation arguments here

		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForScheduleAdherence(1, req, "", "",
					SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	// schedule Arrival Report
	public String getscheduleArrival() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showScheduleArrivalData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo").toString();
		String BusstopId = req.getParameter("busStopId").toString();
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForScheduleArrival(1, req, "", "",
					common.getDateFromPicker(SelectedDate), Scheduleno,
					BusstopId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getscheduleDeparture() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showScheduleDepartureData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo").toString();
		String BusstopId = req.getParameter("busStopId").toString();
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForScheduleDeparture(1, req, "", "",
					SelectedDate, Scheduleno, BusstopId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getRouteDeviation() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showRouteDeviationData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int startPoint = 0;
		String SelectedDate = req.getParameter("selectedDate").toString();

		String Scheduleno = req.getParameter("depotid").toString();
		String BusstopId = "";
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		JSONObject result = new JSONObject();

		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForRouteDeviationInfo(1, SelectedDate,
					Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	// getMissedTrips
	public String getMissedTrips() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showMissedTripsData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int startPoint = 0;
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("depotid").toString();
		String BusstopId = "";
		Common common = new Common();
		JSONObject result = new JSONObject();
		try {
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForMissedTrips(1, SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	//

	public String getbusBreakDown() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showbusBreakDownData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int startPoint = 0;
		String SelectedDate = req.getParameter("selectedDate").toString();

		String Scheduleno = req.getParameter("depotid").toString();
		String BusstopId = "";
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		JSONObject result = new JSONObject();

		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForBreakDownReport(1, SelectedDate
					+ " 00:00:00", SelectedDate + " 23:59:59", Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getControlChart() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showControlChartData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int startPoint = 0;
		String SelectedDate = req.getParameter("selectedDate").toString();

		String Scheduleno = req.getParameter("depotid").toString();
		String BusstopId = "";
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		JSONObject result = new JSONObject();

		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ SelectedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForControlChart(1, SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String scheduleArrivalDaily() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	@SuppressWarnings("unchecked")
	public String showScheduleArrivalDaily() {
		try {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int startPoint = 0;
			String fromDate = req.getParameter("fromDate").toString();

			String tillDate = req.getParameter("tillDate").toString();
			String depotId = req.getParameter("depotID").toString();
			String BusstopId = "";
			Common common = new Common();
			if (common.differenceOfDateString(fromDate, tillDate) == 9) {
				addFieldError("tillDate",
						"Difference between two dates shouldn't greater than 9 days");

				JSONObject result = new JSONObject();
				try {
					out = resp.getWriter();
					result = dao.getDataForScheduleArrivalDaily(1,
							common.getDateFromPicker(fromDate), depotId,
							common.getDateFromPicker(tillDate));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
			} else {
				JSONObject result = new JSONObject();
				out = resp.getWriter();
				result.put("bbData",
						"Difference between two dates shouldn't greater than 9 days");
				out.print(result);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getScheduleDepartureDataDaily() {
		try {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int startPoint = 0;
			String fromDate = req.getParameter("fromDate").toString();

			String tillDate = req.getParameter("tillDate").toString();
			String depotId = req.getParameter("depotID").toString();
			String BusstopId = "";
			Common common = new Common();
			if (common.differenceOfDateString(fromDate, tillDate) == 9) {
				addFieldError("tillDate",
						"Difference between two dates shouldn't greater than 9 days");

				JSONObject result = new JSONObject();
				try {
					out = resp.getWriter();
					result = dao.getDataForScheduleDepartureDaily(1,
							common.getDateFromPicker(fromDate), depotId,
							common.getDateFromPicker(tillDate));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				out.print(result);
			} else {
				JSONObject result = new JSONObject();
				out = resp.getWriter();
				result.put("bbData",
						"Difference between two dates shouldn't greater than 9 days");
				out.print(result);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String getSchedulePerformanceReport() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showScheduledPerformanceData() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");

		// TODO initialize WS operation arguments here

		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {

			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForSchedulePerformanceReport(1, req, "", "",
					SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getSchedulePerformanceHeaderData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getSchedulePerformanceHeader(1, req, "", "",
					SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	public String getSchedulePerformanceFooterData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getSchedulePerformanceFooter(1, req, "", "",
					SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	public String dailyOpearationSummary() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String showDailyOperationalSummary() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String DateArray[] = SelectedDate.split("-");
		String seletedDate = DateArray[2] + "-" + DateArray[1] + "-"
				+ DateArray[0];

		String Scheduleno = req.getParameter("scheduleNo").toString();
		String BusstopId = req.getParameter("busStopId").toString();
		Common common = new Common();
		// SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		// "yyyy-mm-dd");

//		System.out.println("Scheduleno11..." + Scheduleno + "SelectedDate..."
//				+ seletedDate + "deviceId" + Scheduleno);
		try {
			// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForDailyOperationalSummary(1, req, "", "",
					common.getDateFromPicker(SelectedDate), Scheduleno,
					BusstopId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String showVehiclesToPlotOnMap() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		String Lat = req.getParameter("Lat").toString();
		String Long = req.getParameter("Long").toString();
		try {
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getVehicleDataToPlot(Lat, Long);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	public String showVehiclesToPlotOnMapDateTime() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String Lat = req.getParameter("Lat").toString();
		String Long = req.getParameter("Long").toString();
		
		String startDate = request.getParameter("start_date") != null ? request
				.getParameter("start_date") : "";
		String endDate = request.getParameter("end_date") != null ? request
				.getParameter("end_date") : "";
				String dist = request.getParameter("dist") != null ? request
						.getParameter("dist") : "";
//						System.out.println("Start and End"+startDate+"\t"+endDate);
					String date1[] = startDate.split(" ");
					String d0=date1[0];
					String d1=date1[1];
					String d2=date1[2];
					String d3=date1[3];
					String d4=date1[4];
					String d5 =date1[5];
					String d6=date1[6];
					String startDate1=d0+" "+d1+" "+d2+" "+d3+" "+d4;
					
					String date2[] = endDate.split(" ");
					String e0=date2[0];
					String e1=date2[1];
					String e2=date2[2];
					String e3=date2[3];
					String e4=date2[4];
					String e5 =date2[5];
					String e6=date2[6];
					String endDate1=e0+" "+e1+" "+e2+" "+e3+" "+e4;
					
//					System.out.println("arrange==="+startDate1+ "    "+endDate1);
					
					
//					    DateFormat readFormat = new SimpleDateFormat( "EEE MMM dd yyyy hh:mm");
//
//					    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//					    Date date = null;
//					    try {
//					       date = readFormat.parse( endDate1 );
//					    } catch ( ParseException e ) {
//					        e.printStackTrace();
//					    }
//
//					    String formattedDate = "";
//					    if( date != null ) {
//					    	formattedDate = writeFormat.format( date );
//					    }
//
//					    System.out.println(formattedDate);
					
						Common common=new Common();
						String fromDate = common.getDateTimeISTFromPickerToDB(startDate1);
						String tillDate = common.getDateTimeISTFromPickerToDB(endDate1);
//						System.out.println("#######-   - "+fromDate+"   "+tillDate);
						
						
		try {
//			System.out.println("agefertet--"+fromDate+"   "+tillDate);
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getVehicleDataToPlotDateTime(Lat, Long,fromDate,tillDate,dist);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String getVehicleMapDataOnDateTime() {
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String Lat = req.getParameter("Lat").toString();
		String Long = req.getParameter("Long").toString();
//		System.out.println("lat "+Lat+"long   "+Long);
		String startDate = request.getParameter("start_date") != null ? request
				.getParameter("start_date") : "";
		String endDate = request.getParameter("end_date") != null ? request
				.getParameter("end_date") : "";
				
//				System.out.println("start "+startDate+"enf   "+endDate);
				String dist = request.getParameter("dist") != null ? request
						.getParameter("dist") : "";
						
//					System.out.println("disisis"+dist);
//						System.out.println("Start and End"+startDate+"\t"+endDate);
					String date1[] = startDate.split(" ");
					String d0=date1[0];
					String d1=date1[1];
					String d2=date1[2];
					String d3=date1[3];
					String d4=date1[4];
					String d5 =date1[5];
					String d6=date1[6];
					String startDate1=d0+" "+d1+" "+d2+" "+d3+" "+d4;
					
					String date2[] = endDate.split(" ");
					String e0=date2[0];
					String e1=date2[1];
					String e2=date2[2];
					String e3=date2[3];
					String e4=date2[4];
					String e5 =date2[5];
					String e6=date2[6];
					String endDate1=e0+" "+e1+" "+e2+" "+e3+" "+e4;
					
//					System.out.println("arrange==="+startDate1+ "    "+endDate1);
					
					
//					    DateFormat readFormat = new SimpleDateFormat( "EEE MMM dd yyyy hh:mm");
//
//					    DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//					    Date date = null;
//					    try {
//					       date = readFormat.parse( endDate1 );
//					    } catch ( ParseException e ) {
//					        e.printStackTrace();
//					    }
//
//					    String formattedDate = "";
//					    if( date != null ) {
//					    	formattedDate = writeFormat.format( date );
//					    }
//
//					    System.out.println(formattedDate);
					
						Common common=new Common();
						String fromDate = common.getDateTimeISTFromPickerToDB(startDate1);
						String tillDate = common.getDateTimeISTFromPickerToDB(endDate1);
//						System.out.println("#######-   - "+fromDate+"   "+tillDate);
						
						
		try {
//			System.out.println("agefertet--"+fromDate+"   "+tillDate);
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getTotalVehicleDataDateTime(Lat, Long,fromDate,tillDate,dist);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}


	public String showlateDepartureReport() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionNameALL();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String getlateDepartureReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForLateDepartureReport(1, req, "", "",
					SelectedDate, Scheduleno, range);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}
	
	public String getlateDepartureSummaryReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForLateDepartureSummaryReport(1, req, "", "",
					SelectedDate, Scheduleno, range);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}


	public String showEarlyArrivalReport() {
		try {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionNameALL();
			schedulelist = vvt.getScheduleName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	public String getEarlyArrivalReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForEarlyArrivalReport(1, req, "", "",
					SelectedDate, Scheduleno, range);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}
	public String getEarlyArrivalReportSummaryData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForEarlyArrivalSummaryReport(1, req, "", "",
					SelectedDate, Scheduleno, range);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	
	// trip cancle report
	public String getTripCancleReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForTripCancleReport(1, req, "", "",
					sm.format(SelectedDate), Scheduleno, range);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}
	
	public String getTripPartialReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("givendate").toString();
		//String Scheduleno = req.getParameter("scheduleNo");
		//int range = Integer.parseInt(req.getParameter("range"));
		Common common = new Common();
		 SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		 "yyyy-mm-dd");
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForTripPartialReport(1, req, "", "",
					SelectedDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	public String getTripCancelReportDataSummary() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("givendate").toString();
//		System.out.println("SelectedDate"+SelectedDate);
		Common common = new Common();
		 SelectedDate=common.changeDataFormat(SelectedDate, "dd-mm-yyyy",
		 "yyyy-mm-dd");
		//String Scheduleno = req.getParameter("scheduleNo");
		//int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForTripCancleReport(1, req, "", "",
					SelectedDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	// end trip cancle report

	
	// end trip cancle report
	

	// Deviated Trip data
	public String getDeviatedTripReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForDeviatedReport(1, req, "", "", SelectedDate,
					Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	// end deviated trip data
	// Bus Stop Skiped data
	public String getBusSkippedReportData() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("selectedDate").toString();
		String Scheduleno = req.getParameter("scheduleNo");
		int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			out = resp.getWriter();
			result = dao.getDataForBusSkippedReport(1, req, "", "",
					SelectedDate, Scheduleno);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}

	// end deviated trip data
	public String getHA() {
		// Calling Web Service
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao
					.getDataForHA(req, sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String getHD() {

		// Calling Web Service
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao
					.getDataForHD(req, sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	
	public String getAccidentInfo() {

		// Calling Web Service
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao
					.getDataForAccident(req, sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String getDepotInInfo() {

		// Calling Web Service
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String parentId = req.getParameter("alertID");
//		System.out.println("parentId is----"+parentId);
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao
					.getDataForDepotIn(req, sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String getStationaryVehicleInfo() {

		// Calling Web Service
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String parentId = req.getParameter("alertID");
//		System.out.println("parentId is----"+parentId);
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao
					.getDataForStationaryVehicle(req, sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	
	public String getWorkShopVehicleInfo() {

		// Calling Web Service
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String parentId = req.getParameter("alertID");
//		System.out.println("parentId is----"+parentId);
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao
					.getDataForWorkShopVehicle(req, sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String getBreakDownInfo() {

		// Calling Web Service
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao
					.getDataForBreakDownDetails(req, sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	

	public String vehicleAlertAccDetails() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String device_id = req.getParameter("device_id");
		String ist_date=req.getParameter("ist_date");
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForHarseAcc(device_id, req, sm.format(ist_date)
					.toString() + " 00:00:00", sm.format(ist_date).toString()
					+ " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	public String vehicleAlertDeaccDetails() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String device_id = req.getParameter("device_id");
		String ist_date=req.getParameter("ist_date");
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForHarseDeacc(device_id, req,
					sm.format(ist_date).toString() + " 00:00:00",
					sm.format(ist_date).toString() + " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	public String vehicleAlertAccDetailsReport() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String device_id = req.getParameter("device_id");
		String ist_date = req.getParameter("ist_date");
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForHarseAcc(device_id, req, ist_date + " 00:00:00",
					ist_date + " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String vehicleAlertDeaccDetailsReport() {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String device_id = req.getParameter("device_id");
		String ist_date = req.getParameter("ist_date");
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForHarseDeacc(device_id, req,
					ist_date + " 00:00:00",
					ist_date + " 23:59:59");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}

	
	public String getScheduleTripStatus() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			if(orgtypeid.equals("2")){
	        	//Our Logic
			int parentId=0;
			try {
			} catch (Exception ex) {

			}
			try {
				parentId = vvt.getDepot1DC(orgtypeid,orgchartid);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId);
			//Ends..
	        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
			divisionlist = vvt.getDivisionName();
			//schedulelist = vvt.getScheduleName();
	        }else{
	        	int parentId=0;
				try {
				} catch (Exception ex) {

				}
				try {
					parentId = vvt.getDepot1(orgtypeid,orgchartid);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}
	
	public String getTripCancelReportPage(){
		return "success";
	}
	public String getTripPartialReportPage(){
		 try {
				divisionlist = dao.getDepot1();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "success";
	}
	public String getTripCancelVtsReportPage(){
		return "success";
	}
	public String getTripPartialVtsReportPage(){
		return "success";
	}

	
	public String breakDownVtsReportData() {
		System.out.println("selectedDate");
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		//String depotNo = req.getParameter("depotNo").toString();
		
		String selectedDate = req.getParameter("givendate").toString();
		System.out.println("selectedDate"+selectedDate);
		//String brkType = req.getParameter("breakdowntype").toString();
		Common cm = new Common();
		String formattedgivendate1 = cm.getDateFromPicker(selectedDate);
				try {

			resp.setContentType("application/json");
			out = resp.getWriter();
			result = dao.getDataForBreakDownReport(formattedgivendate1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String getAlertReportonChangeVts() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();

		String SelectedDate = req.getParameter("givendate").toString();
		System.out.println("SelectedDate"+SelectedDate);
		//String Scheduleno = req.getParameter("scheduleNo");
		//int range = Integer.parseInt(req.getParameter("range"));
		JSONObject result = new JSONObject();
		// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
		Common cm = new Common();
		String formattedgivendate1 = cm.getDateFromPicker(SelectedDate);
		try {
			out = resp.getWriter();
			result = dao.getDataForAccidentReport(1, req, "", "",
					formattedgivendate1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		
		return null;
	}
	public String getScheduleMismatchingPage(){
		return "success";
	}
	
	public String getscheduleMismatchingRecord(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		String SelectedDate = req.getParameter("givendate").toString();
		//String depot_id = req.getParameter("depot_id").toString();
		JSONObject result = new JSONObject();
		Common cm = new Common();
		String formattedgivendate1 = cm.getDateFromPicker(SelectedDate);
		try {
			out = resp.getWriter();
			result = dao.getDataForScheduleMismatchReport(1, req, "", "",
					formattedgivendate1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);

		return null;
	}
	public String getscheduleMismatchingRecordDetails(){
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		String SelectedDate = req.getParameter("givendate").toString();
		String depot_id = req.getParameter("depot_id").toString();
		JSONObject result = new JSONObject();
		//Common cm = new Common();
		//String formattedgivendate1 = cm.getDateFromPicker(SelectedDate);
		try {
			out = resp.getWriter();
			result = dao.getDataForScheduleMismatchReportDetails(1, req, "", depot_id,
					SelectedDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(result);
		return null;
	}
	
	public String getDepotDelayedDepartureTime(){
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		String depot_code = req.getParameter("depotID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			result = dao.getDataForDelayedDeparture(packet_code, depot_code, req, "",
					"", sm.format(FromDate).toString() + " 00:00:00", sm
							.format(FromDate).toString() + " 23:59:59");
			out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
		
	}
	
	
	private String vehicleNo;
	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	private String ticketDate;
	private String serialno;
	private String orgname;
	private String time;
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	private String datetime;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	
	
	public String showLiveBusDetailsVts(){
		try{
		Date date = new Date();
//		System.out.println();
		Date date1 = new Date();
//		System.out.println("showLiveBusDetails()");
		HttpServletRequest request = ServletActionContext.getRequest();
	    vehicleNo = request.getParameter("vehicleNo");
		ticketDate= request.getParameter("ticketdate");
		serialno=request.getParameter("deviceser");
		String datetime1 = ticketDate+" "+time;
//		System.out.println("datetime1--"+datetime1);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		Date date2 = (Date)formatter.parse(datetime1);
		Date date3 = (Date)formatter.parse(datetime1);
		Date subdate = subMin(date2, 1);
		
		Date adddate = addMin(date3, 1);
		 orgname = request.getParameter("orgname");
		 time =  request.getParameter("time");
		// depotId = request.getParameter("depotId");
	
		SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
		//SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
		setStartdate(sm2.format(subdate));
		setEnddate(sm2.format(adddate));
		System.out.println("getstartTime--"+getStartdate());
		System.out.println("getendTime--"+getEnddate());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	
	//this function is used for early and late deparature
	public String showLiveBusDetailsVtsEarlyData(){
		try{
			Date date = new Date();
//			System.out.println();
			Date date1 = new Date();
			HttpServletRequest request = ServletActionContext.getRequest();
		    vehicleNo = request.getParameter("vehicleNo");
			ticketDate= request.getParameter("ticketdate");
			serialno=request.getParameter("deviceser");
			String datetime1 = ticketDate+" "+time;
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
			Date date2 = (Date)formatter.parse(datetime1);
			Date date3 = (Date)formatter.parse(datetime1);
			Date subdate = subMin(date2, 1);
			
			Date adddate = addMin2(date3, 1);
			 orgname = request.getParameter("orgname");
			 time =  request.getParameter("time");
		
			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
			//SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
			
			setStartdate(sm2.format(subdate));
			setEnddate(sm2.format(adddate));
			
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return "success";
	}
	
	//end
	
	
	public String showLiveBusDetailsVtsLateData(){
		try{
		Date date = new Date();
//		System.out.println();
		Date date1 = new Date();
//		System.out.println("showLiveBusDetails()");
		HttpServletRequest request = ServletActionContext.getRequest();
	    vehicleNo = request.getParameter("vehicleNo");
		ticketDate= request.getParameter("ticketdate");
		serialno=request.getParameter("deviceser");
		String datetime1 = ticketDate+" "+time;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		Date date2 = (Date)formatter.parse(datetime1);
		Date date3 = (Date)formatter.parse(datetime1);
		//Date subdate = subMin(date2, 1);
		
		
		Date adddate = addMin(date3, 1);
		 orgname = request.getParameter("orgname");
		 time =  request.getParameter("time");
		// depotId = request.getParameter("depotId");
	
		SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
		//SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
		
		setStartdate(sm2.format(date2));
		setEnddate(sm2.format(adddate));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	
	
	private String busstopid;
	public String getBusstopid() {
		return busstopid;
	}

	public void setBusstopid(String busstopid) {
		this.busstopid = busstopid;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getStartpoint() {
		return startpoint;
	}

	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	private String routeid;
	private String startpoint;
	private String endpoint;
	
	public String showLiveBusSkipStopDetailsVts(){
		try{
			Date date = new Date();
//			System.out.println();
			Date date1 = new Date();
			HttpServletRequest request = ServletActionContext.getRequest();
		    vehicleNo = request.getParameter("vehicleNo");
			ticketDate= request.getParameter("ticketdate");
			serialno=request.getParameter("deviceser");
			String datetime1 = ticketDate+" "+time;
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
			Date date2 = (Date)formatter.parse(datetime1);
			Date date3 = (Date)formatter.parse(datetime1);
			Date subdate = subMin(date2, 1);
			
			Date adddate = addMin(date3, 1);
			 orgname = request.getParameter("orgname");
			 time =  request.getParameter("time");
		
			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
			//SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
			
			setStartdate(sm2.format(subdate));
			setEnddate(sm2.format(adddate));
			
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "success";
	}
	
	private String time1;
	private String time2;
	
	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String showLiveBusDeviatedDetailsVts(){
		try{
			Date date = new Date();
//			System.out.println();
			Date date1 = new Date();
			HttpServletRequest request = ServletActionContext.getRequest();
		    vehicleNo = request.getParameter("vehicleNo");
			ticketDate= request.getParameter("ticketdate");
			time1=request.getParameter("time1");
			time2=request.getParameter("time2");
			
			serialno=request.getParameter("deviceser");
			String datetime1 = ticketDate+" "+time1;
			String datetime2 = ticketDate+" "+time2;
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
			Date date2 = (Date)formatter.parse(datetime1);
			Date date3 = (Date)formatter.parse(datetime2);
//			Date subdate = subMin(date2, 1);

			
//			Date adddate = addMin(date3, 1);

//			System.out.println("date2"+date2);
//			System.out.println("date3"+date3);
			 orgname = request.getParameter("orgname");
			 time =  request.getParameter("time");
//			 System.out.println("time"+time);
			// depotId = request.getParameter("depotId");
//			 System.out.println("serialno"+serialno);
//			System.out.println(serialno +" " +ticketDate+" " +orgname);
//			
		    
		    System.out.println("datetime1" +datetime1);
		    System.out.println("datetime2" +datetime2);
			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
			//SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
			
			
			setStartdate(sm2.format(date2));
			setEnddate(sm2.format(date3));
			System.out.println("getTicketDate"+getStartdate());
			System.out.println("getTicketDate"+getEnddate());
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "success";
	}
	

	private String dateforonlineticket;
	public String getDateforonlineticket() {
		return dateforonlineticket;
	}

	public void setDateforonlineticket(String dateforonlineticket) {
		this.dateforonlineticket = dateforonlineticket;
	}

	public String getDepotNameForTicket() {
		return depotNameForTicket;
	}

	public void setDepotNameForTicket(String depotNameForTicket) {
		this.depotNameForTicket = depotNameForTicket;
	}
	
  

	private String depotNameForTicket;
	public String TripTicketOnline(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			dateforonlineticket=request.getParameter("date");
			depotNameForTicket=request.getParameter("depotName");
//			System.out.println("dateforonlineticket---"+dateforonlineticket);
//			System.out.println("depotNameForTicket---"+depotNameForTicket);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return "success";
	}
	private int totalPassengers;
    public int getTotalPassengers() {
		return totalPassengers;
	}

	public void setTotalPassengers(int totalPassengers) {
		this.totalPassengers = totalPassengers;
	}

	private String depotId;
	public String getDepotId() {
		return depotId;
	}

	public void setDepotId(String depotId) {
		this.depotId = depotId;
	}

	private int totalTickets;
	public int getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}

	private double totalAmount;
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	ArrayList<OnlineWaybills> showWaybill = new ArrayList<OnlineWaybills>();
	public ArrayList<OnlineWaybills> getShowWaybill() {
		return showWaybill;
	}

	public void setShowWaybill(ArrayList<OnlineWaybills> showWaybill) {
		this.showWaybill = showWaybill;
	}
	private Map<Integer, String> depotMap;

	public Map<Integer, String> getDepotMap() {
		return depotMap;
	}

	public void setDepotMap(Map<Integer, String> depotMap) {
		this.depotMap = depotMap;
	}

	private ShowOnlineWaybillDAO waybillShowDao = new ShowOnlineWaybillDAO();
	public String getTripTicketWayBill(){
	
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			HttpServletRequest request = ServletActionContext.getRequest();
			String dateforonlineticket1="";
			String depotNameForTicket1="";
//			System.out.println("request.getParameter"+request.getParameter("startdate"));
//			System.out.println("request.getParameter"+request.getParameter("depotName"));
			if(request.getParameter("depotName")!=null){
				dateforonlineticket1=request.getParameter("startdate");
				depotNameForTicket1=request.getParameter("depotName");
//	            System.out.println(" I m in getTripTicketWaybill()");
//				System.out.println("dateforonlineticket---"+dateforonlineticket1);
//				System.out.println("depotNameForTicket---"+depotNameForTicket1);
				setDepotNameForTicket(depotNameForTicket1);
				setDateforonlineticket(dateforonlineticket1);
				
			}
			Common common=new Common();
			//String strQry1 = "select org_name as org_name from org_chart where org_chart_id="+depotNameForTicket+" and deleted_status=0";
//			try {
//			orgname = common.getDBResultStr(session, strQry1, "org_name");
//				System.out.println("orgname is---"+orgname);
//			}
//			catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
			depotMap = waybillShowDao.getDepots();
			System.out.println("orgname is"+orgname);
			
			double totAmt = 0.0;
			int totTickets = 0;
			int totPassengers = 0;
			showWaybill = waybillShowDao.getAllWaybills(depotNameForTicket,dateforonlineticket1);	
			for(int i=0;i<showWaybill.size();i++){
				totAmt = totAmt+Double.parseDouble(showWaybill.get(i).getTotal_ticket_amount());
				totTickets = totTickets+Integer.parseInt(showWaybill.get(i).getNoOfTickets());
				totPassengers += Integer.parseInt(showWaybill.get(i).getPx_count());
			}
			setTotalAmount(totAmt);
			setTotalTickets(totTickets);
			setDepotId(depotId);
			setTicketDate(ticketDate);
			setTotalPassengers(totPassengers);
			return SUCCESS;
		}

	
	
	
	
	
	private String latcurrent;
	private String longcurrent;
	
	public String getLatcurrent() {
		return latcurrent;
	}

	public void setLatcurrent(String latcurrent) {
		this.latcurrent = latcurrent;
	}

	public String getLongcurrent() {
		return longcurrent;
	}

	public void setLongcurrent(String longcurrent) {
		this.longcurrent = longcurrent;
	}

	public String showskipstoproute(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		PrintWriter out;
		int busid=Integer.parseInt(request.getParameter("busstopid"));
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		List list = new ArrayList();
		JSONArray array = new JSONArray();
		JSONObject result = new JSONObject();
		
		
		try{
			String query = "select latitude_current,longitude_current from bus_stop where bus_stop_id="+busid;
			
			Query querySession = session.createSQLQuery(query).addScalar("latitude_current").addScalar("longitude_current");
			 		
			querySession.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		   List<Map<String, Object>> aliasToValueMapList = querySession.list();	
		   JSONArray point = new JSONArray();
			
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					
					Map<String, Object> rs = aliasToValueMapList.get(i);
					
					
					latcurrent=rs.get("latitude_current").toString();
					longcurrent=rs.get("longitude_current").toString();
					
				}
				point.add(latcurrent);
				point.add(longcurrent);
				array.add(point);
				
			}
			result.put("aaData",array);

			System.out.println("latcurrent---"+latcurrent);
			System.out.println("longcurrent---"+longcurrent);
			
			HttpServletResponse response=ServletActionContext.getResponse();
			out = response.getWriter();
			 out.print(result);
			
			} catch(Exception e){
				e.printStackTrace();
				
			} finally{
				//session.close();
			}
		
		
		return null;
	}
	
	
	public String ShowLiveBusDetailsForStationaryVehicle(){
		try{
		Date date = new Date();
//		System.out.println();
		Date date1 = new Date();
//		System.out.println("showLiveBusDetails()");
		HttpServletRequest request = ServletActionContext.getRequest();
	    vehicleNo = request.getParameter("vehicleNo");
		ticketDate= request.getParameter("ticketdate");
		serialno=request.getParameter("deviceser");
		String datetime1 = ticketDate+" "+time;
//		System.out.println("datetime1--"+datetime1);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		Date date2 = (Date)formatter.parse(datetime1);
		Date date3 = (Date)formatter.parse(datetime1);
		//Date subdate = subMin(date2, 1);
		
		Date adddate = addMinStationary(date3, 1);
		 orgname = request.getParameter("orgname");
		 time =  request.getParameter("time");
		// depotId = request.getParameter("depotId");
	
		SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
		//SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
		
		
//		 DateFormat df = new SimpleDateFormat("hh:mm:ss a");
//		 Calendar cal = Calendar.getInstance();
//	        cal.setTime(date3);
//	        cal.add(Calendar.SECOND, 30);
//	        date3 = cal.getTime();
//	        System.out.println("date3 sec"+date3);
//	        System.out.println("time+30: " + df.format(date3));
		
	        setStartdate(sm2.format(date2));
			setEnddate(sm2.format(adddate));
		
		System.out.println("getstartTime--"+getStartdate());
		System.out.println("getendTime--"+getEnddate());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	
	
	public String ShowLiveBusDetailsForStationaryVehicleLocation(){
		try{
		Date date = new Date();
		System.out.println();
		Date date1 = new Date();
//		System.out.println("showLiveBusDetails() place");
		HttpServletRequest request = ServletActionContext.getRequest();
	    vehicleNo = request.getParameter("vehicleNo");
		ticketDate= request.getParameter("ticketdate");
		serialno=request.getParameter("deviceser");
		String datetime1 = ticketDate+" "+time;
//		System.out.println("datetime1--"+datetime1);
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
		Date date2 = (Date)formatter.parse(datetime1);
		Date date3 = (Date)formatter.parse(datetime1);
		//Date subdate = subMin(date2, 1);
		
		Date adddate = addMinStationary(date3, 1);
		 orgname = request.getParameter("orgname");
		 time =  request.getParameter("time");
		// depotId = request.getParameter("depotId");
	
		SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm:ss");
	        setStartdate(sm2.format(date2));
			setEnddate(sm2.format(adddate));
		System.out.println("getstartTime--"+getStartdate());
		System.out.println("getendTime--"+getEnddate());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "success";
	}
	
	
	 public static Date subMin(Date d, int days) {
	        d.setTime(d.getTime()  - 600 * 1000);
	        return d;
	    }
	 public static Date addMin(Date d, int days) {
	        d.setTime(d.getTime()  + 600 * 1000);
	        return d;
	    }
	 public static Date addMin2(Date d, int days) {
	        d.setTime(d.getTime()  + 120 * 1000);
	        return d;
	    }
	 
	 public static Date subMinStationary(Date d, int days) {
	        d.setTime(d.getTime()  - 60 * 1000);
	        return d;
	    }
	 public static Date addMinStationary(Date d, int days) {
	        d.setTime(d.getTime()  + 60 * 1000);
	        return d;
	    }
	 
	 
		public String getScheduletripdurationanalysis() {
			try {
				HttpServletRequest req = ServletActionContext.getRequest();
				 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
				VtsDataDAO vvt = VtsDataDAO.getInstance();
				if(orgtypeid.equals("2")){
		        	//Our Logic
				int parentId=0;
				try {
				} catch (Exception ex) {

				}
				try {
					parentId = vvt.getDepot1DC(orgtypeid,orgchartid);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId);
				//Ends..
		        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
				divisionlist = vvt.getDivisionName();
				//schedulelist = vvt.getScheduleName();
		        }else{
		        	int parentId=0;
					try {
					} catch (Exception ex) {

					}
					try {
						parentId = vvt.getDepot1(orgtypeid,orgchartid);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "success";
		}
		
		
		public String getScheduleDuration() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			//VtsDataDAO dao = VtsDataDAO.getInstance();
			ScheduleTripDurationDAO dao= new ScheduleTripDurationDAO();
			int parentId = Integer.parseInt(req.getParameter("val"));
			String date = req.getParameter("selectedDate");
			Common common = new Common();
			//String date = req.getParameter("selectedDate");
			List<Integer> l1 = dao.getScheduleNameDurationID(parentId);
			List<String> l2 = dao.getScheduleNameDurationName(parentId);
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			String regionTypeAjaxString = "<option value='0'>----Select----</option>";
			System.out.println("l1.size"+l1.size());
			System.out.println("l2.size"+l2.size());
			try {
				for (int i = 0; i < l1.size(); i++) {

					regionTypeAjaxString += "<option value=" + l1.get(i)
							+ ">" + l2.get(i).toString() + "</option>";
				}
				System.out.println("regionTypeAjaxString" + regionTypeAjaxString);
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out;

				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;

		}
		
		
//		public String getScheduleDuration() {
//			// get Depot List..
//			HttpServletRequest req = ServletActionContext.getRequest();
//			//VtsDataDAO dao = VtsDataDAO.getInstance();
//			ScheduleTripDurationDAO dao= new ScheduleTripDurationDAO();
//			int parentId = Integer.parseInt(req.getParameter("val"));
//			String date = req.getParameter("selectedDate");
//			System.out.println("hiii");
//			Common cm = new Common();
//			//String formattedgivendate = cm.getDateFromPicker(date);
//			
//			List<WaybillDetails> l2 = dao.getScheduleNameTripDuraion(parentId);
//			String regionTypeAjaxString = "<option value='0'>----select----</option>";
//			
//			for (int i = 0; i < l2.size(); i++) {
//					
//				regionTypeAjaxString += "<option value=" + l2.get(i).getSchedule_no()
//						+ ">" + l2.get(i).getSchedule_name() + "</option>";
//			}
//			System.out.println("regionTypeAjaxString" + regionTypeAjaxString);
//			HttpServletResponse response = ServletActionContext.getResponse();
//			PrintWriter out;
//			try {
//				out = response.getWriter();
//				out.print(regionTypeAjaxString);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			return null;
//
//		}
//		
		
		public String showScheduletripdurationanalysisData() {

			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			//VtsDataDAO dao = VtsDataDAO.getInstance();
			ScheduleTripDurationDAO dao= new ScheduleTripDurationDAO();
			JSONObject result = new JSONObject();
			System.out.println("hellooo");
			String selectedstartdate = req.getParameter("selectedstartdate").toString();
			String selectedenddate = req.getParameter("selectedenddate").toString();
			String Scheduleno = req.getParameter("scheduleNo");
			String depotName = req.getParameter("depotName");
			System.out.println("depotName----"+depotName);
			Common cm = new Common();
			String formattedgivendate = cm.getDateFromPicker(selectedstartdate);
			String formattedgivenenddate = cm.getDateFromPicker(selectedenddate);
			// TODO initialize WS operation arguments here

			// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
			try {

				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				resp.setContentType("application/json");
				out = resp.getWriter();
				result = dao.getDataForshowScheduletripdurationReport(1, req, "", "",
						formattedgivendate,formattedgivenenddate, Scheduleno,depotName);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
		}
		
		public String updateScheduleData(){
			try{
				System.out.println("INSIDE Update");
				HttpServletResponse resp = ServletActionContext.getResponse();
				HttpServletRequest req = ServletActionContext.getRequest();
				String selectedDate = req.getParameter("selectedDate").toString();
				String vehicleno = req.getParameter("vehicleno").toString();
				String Scheduleno = req.getParameter("schedule_name");
				int depot_id=Integer.parseInt(req.getParameter("depot_id"));
				String formattedgivendate =new Common().getDateFromPicker(selectedDate);
				//Check For Update Trip Details From Depot...
				try{
				String artrip[]=new String [3];
				artrip[0]= String.valueOf(depot_id);
				artrip [1]= Scheduleno;
				artrip [2]= formattedgivendate;
				UpdateTripStartEndEvent ute =new UpdateTripStartEndEvent();
				ute.main(artrip);
				Thread.sleep(10000);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				//END
				ScheduleTripUpdate dao =new ScheduleTripUpdate();
				String ar[]=new String [3];
				ar[0]=formattedgivendate;
				ar[1]=vehicleno;
				ar[2]= Scheduleno;
				dao.main(ar);
				//PrintWriter out=resp.getWriter();
				
			
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			
			
			return null;
		}
		
		

		
		
		public String getScheduleNotDeparted() {
			try {
				HttpServletRequest req = ServletActionContext.getRequest();
				 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
				VtsDataDAO vvt = VtsDataDAO.getInstance();
				if(orgtypeid.equals("2")){
		        	//Our Logic
				int parentId=0;
				try {
				} catch (Exception ex) {

				}
				try {
					parentId = vvt.getDepot1DC(orgtypeid,orgchartid);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId);
				//Ends..
		        }else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
				divisionlist = vvt.getDivisionName();
				//schedulelist = vvt.getScheduleName();
		        }else{
		        	int parentId=0;
					try {
					} catch (Exception ex) {

					}
					try {
						parentId = vvt.getDepot1(orgtypeid,orgchartid);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "success";
		}
		

		public String showScheduleNotDeparted() {

			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
			//VtsDataDAO dao = VtsDataDAO.getInstance();
			ScheduleTripDurationDAO dao= new ScheduleTripDurationDAO();
			JSONObject result = new JSONObject();
			System.out.println("hellooo");
			//String selectedstartdate = req.getParameter("selectedstartdate").toString();
			String selectedenddate = req.getParameter("selectedenddate").toString();
			//String Scheduleno = req.getParameter("scheduleNo");
			String depotName = req.getParameter("depotName");
			System.out.println("depotName----"+depotName);
			Common cm = new Common();
			//String formattedgivendate = cm.getDateFromPicker(selectedstartdate);
			String formattedgivenenddate = cm.getDateFromPicker(selectedenddate);
			// TODO initialize WS operation arguments here
			Date FromDate = new Date();
			// System.out.println("Scheduleno..."+Scheduleno+"SelectedDate..."+SelectedDate+"scheduled type id"+alertresult1.getWaybillDetails().get(0).getSCHEDULETRIPTYPEID());
			try {

				// SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				resp.setContentType("application/json");
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				out = resp.getWriter();
				result = dao.getDataForScheduleNotDepartedReport(1, req, "", "",
						formattedgivenenddate + " 00:00:00", formattedgivenenddate + " 23:59:59",depotName);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			out.print(result);
			return null;
		}
		
		public String getLateDeptReasonList() {	
			System.out.println("here coming");
			// get Late Dept Reason List
			HttpServletRequest req = ServletActionContext.getRequest();
		
			getReasonListDao dao = new getReasonListDao();
		
			List<String> l1 = dao.getLateDeptReasonID();
			List<String> l2 = dao.getLateDeptReasonName();
			String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option value='0'>------select------</option>";
			for (int i = 0; i < l1.size(); i++) {
				regionTypeAjaxString += "<option value=" + l1.get(i).toString()
						+ ">" + l2.get(i).toString() + "</option>";
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;

		}
}
