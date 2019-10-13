package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.VtsDataModel;

public class ShowLiveBusActionForAll {
	
	
	private Map<Integer, String> divisionlist;
	private String vehiclelist;
	private String startdate;
	private Map<Integer, String> vehiclelistname;
	ArrayList speedlist = new ArrayList();


	private static final long serialVersionUID = 1L;
	List<com.trimax.ws.vts.vtsutility.VtsDataModel> list = null;
	OrganisationChart orgchart;
	private String E0;
	
	private String buttonname;
	
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

	

	public String getButtonname() {
		return buttonname;
	}

	public void setButtonname(String buttonname) {
		this.buttonname = buttonname;
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

	

	public List<com.trimax.ws.vts.vtsutility.VtsDataModel> getList() {
		return list;
	}

	public void setList(List<com.trimax.ws.vts.vtsutility.VtsDataModel> list) {
		this.list = list;
	}
	
	

	public Map<Integer, String> getVehiclelistname() {
		return vehiclelistname;
	}

	public void setVehiclelistname(Map<Integer, String> vehiclelistname) {
		this.vehiclelistname = vehiclelistname;
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

	private String enddate;
	public String getVehiclelist() {
		return vehiclelist;
	}

	public void setVehiclelist(String vehiclelist) {
		this.vehiclelist = vehiclelist;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	 
		public String showLiveBusDetailsTrackingForAll() {
			
			try {
				
				VtsDataDAO vvt = VtsDataDAO.getInstance();
		        HttpServletRequest req = ServletActionContext.getRequest();
		        String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
		        Date date = new Date();
				Date date1 = new Date();
				//Date addedDate2 = addDays(date1, 1);
				int parentId=0;
//				divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
				divisionlist = getDivisionName();
				System.out.println("divisionlist"+divisionlist);
				vehiclelist = req.getParameter("vehiclelist");
				System.out.println("vehiclelist"+vehiclelist);
				
				try {
					SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
					SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
					setStartdate(sm3.format(date1));
					setEnddate(sm2.format(date1));
					System.out.println("startdate:"+getStartdate());
					System.out.println("enddate:"+getEnddate());
					//VtsDataDAO dao= VtsDataDAO.getInstance();
			        
			       
				} catch (Exception ex) {

				}
				try {
				} catch (Exception ex) {

				}
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
		        	try {
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		        	vehiclelistname = vvt.getVehicledetails(0);
				System.out.println("vehiclelist"+vehiclelistname);
				//schedulelist = vvt.getScheduleName();
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
				
				return "success";
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
					resultMap.put(-1, "Select");
					resultMap.put(0, "All");
					resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
				}
			} catch (Exception ex) {
			} finally {
				HibernateUtil.closeSession();
			}
			return resultMap;
		}
		
		
		
		
		@SuppressWarnings("unchecked")
		public String getCordinatesForAll() throws ParseException {
			System.out.println("in act All Value cordinates");
			HttpServletResponse resp = ServletActionContext.getResponse();
			// resp.setCharacterEncoding("application/json");
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			Date FromDate = new Date();
			Common common = new Common();
			VtsDataDAO dao= VtsDataDAO.getInstance();
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
			
			System.out.println("startDate=="+startDate+"endDate"+endDate+"value"+value);

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
				int divId=Integer.parseInt(req.getParameter("divisionId"));
				
				String qryy="";
				String qry2="in(";
				String qry="";
				
				if(divId == 0){      // if div selected 'All'
					qryy=" !=0";
					
				}else
					
					if(divId !=0 ){         // Any division selected 
						List<String> l1 = dao.getDepotId(divId);
						for (int i = 0; i < l1.size(); i++) {
							 qry +=l1.get(i).toString()+",";
						}
						qry =qry2+qry+")";                   
						String depotIn=remove(qry);
						qryy = depotIn +")";    // qryy=" in(10,11,12,13,15)" ;
						
					}
				System.out.println("qryy is="+qryy);
				
				double totdistancee=0;
				System.out.println("value==="+value);
				if (value.equals("0") || value.equalsIgnoreCase("")) {
					System.out.println("in all values in new");
					list = vvt.getTrackingDataForAll(sm.format(FromDate).toString()
							+ " 00:00:00", sm.format(FromDate).toString()
							+ " 23:59:59", DEVICE_ID, ID, "0",qryy);
					System.out.println("List===="+ list.size());
					
					for (int i = 0; i < list.size(); i++) {
//						System.out.println("List_LAT===="+ list.get(i).getDEVICEID());
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

				}
				
				
//				if (DEVICE_ID.equals("ALL")) {
//					System.out.println("in devic all");
//					int depotID = Integer.parseInt(req.getParameter("depotID"));
//					list = vvt.getTrackingData(sm.format(FromDate).toString()
//							+ " 00:00:00", sm.format(FromDate).toString()
//							+ " 23:59:59", DEVICE_ID, ID, "0", depotID);
//
//					for (int i = 0; i < list.size(); i++) {
//
//						JSONArray ja = new JSONArray();
//						ja.add(list.get(i).getLAT());
//						ja.add(list.get(i).getLONGITUDE());
//						// double
//						// speed=(0.1*(Double.parseDouble(list.get(i).getSPEEDKMPH())))+(0.9*30);
//						ja.add(list.get(i).getSPEEDKMPH());
//						FromDate = sm1.parse(list.get(i).getISTDATE());
//						ja.add(sm2.format(FromDate));
//						ja.add(list.get(i).getDEVICEID());
//						ja.add(list.get(i).getID());
//						ja.add(list.get(i).getLICENCENUMBER());
//						ja.add(list.get(i).getPhoneNumber());
//						ja.add(list.get(i).getVEHICLEDIRECTION());
//						ja.add(list.get(i).getVEHICLETYPE());
//						ja.add(list.get(i).getSCHEDULENO()!=null?list.get(i).getSCHEDULENO():"NotMapped");
//						ja.add(list.get(i).getCAUSESTATUS());
//						ja.add(list.get(i).getDEPOTNAME());
//						ja.add(list.get(i).getBusStopId());
//						ja.add(list.get(i).getDutyTypeId());
//						ja.add(list.get(i).getDRIVERTOKENNO()!=null ? list.get(i).getDRIVERTOKENNO():"0");
//						// ja.add(Integer.parseInt(list.get(i).getACCDISTANCE()));
//						// ja.add(avgspeed);
//						array.add(ja);
//					}
//
//					result.put("aaData", array);
//
//				} else {
//					System.out.println("in else 1");
//					if (value.equals("Submit")) {
//						System.out.println("in else 2");
//						list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
//								ID, "0", 0);
//					} else if (value.equals("ScheduleSubmit")) {
//						list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
//								ID, "0", 0);
//					} else {
//
//						list = vvt.getTrackingData(fromDate, tillDate, DEVICE_ID,
//								ID, "1", 0);
//					}
//					for (int j = 0; j < list.size() - 1; j++) {
//	                    if (j != list.size()) {
//	                        totdistancee += distanceBetweenTwoLocationsInM((list.get(j).getLAT().toString()),list.get(j).getLONGITUDE().toString(), Double.parseDouble(list.get(j + 1).getLAT()), Double.parseDouble(list.get(j + 1).getLONGITUDE()));
//	                    }
//
//	                }
//					for (int i = 0; i < list.size(); i++) {
//						JSONArray ja = new JSONArray();
//						
//						//Find Total Distance..
//						
//						//End
//						
//						
//						/*if (speedlist.size() > 30) {
//							if (Double.parseDouble(list.get(i).getSPEEDKMPH()) != 0.00) {
//								speedlist.remove(0);
//								speedlist.add(Double.parseDouble(list.get(i)
//										.getSPEEDKMPH()));
//							}
//						} else {
//							if (Double.parseDouble(list.get(i).getSPEEDKMPH()) != 0.00) {
//								speedlist.add(Double.parseDouble(list.get(i)
//										.getSPEEDKMPH()));
//							}
//						}*/
//						double avgspeed = 0;
//						/*for (int x = 0; x < speedlist.size(); x++) {
//							avgspeed = avgspeed
//									+ Double.parseDouble(speedlist.get(x)
//											.toString());
//						}*/
//						avgspeed = avgspeed / speedlist.size();
//						ja.add(list.get(i).getLAT());
//						ja.add(list.get(i).getLONGITUDE());
//						// double speed = (0.1 * (Double.parseDouble(list.get(i)
//						// .getSPEEDKMPH()))) + (0.9 * 30);
//						double speed = Double.parseDouble(list.get(i)
//								.getSPEEDKMPH());
//						ja.add(speed);
//						FromDate = sm1.parse(list.get(i).getISTDATE());
//						ja.add(sm2.format(FromDate));
//						ja.add(list.get(i).getDEVICEID());
//						ja.add(list.get(i).getID());
//						ja.add(list.get(i).getLICENCENUMBER());
//						ja.add(list.get(i).getPhoneNumber());
//						ja.add(list.get(i).getVEHICLEDIRECTION());
//						ja.add(list.get(i).getRECORDSTATUS());
//						try{
//						ja.add(Double.parseDouble(list.get(i).getACCDISTANCE()));
//						}catch(Exception ex){
//							ex.printStackTrace();
//						}
//						ja.add(avgspeed);
//						ja.add(list.get(i).getScheduleNumber());
//						ja.add(list.get(i).getACCDISTANCE());
//						ja.add(list.get(i).getDEPOTNAME());
//						ja.add(list.get(i).getSCHEDULENO()!=null?list.get(i).getSCHEDULENO():"NotMapped");
//						ja.add(list.get(i).getSIGNALSTRENGTH());
//						ja.add(list.get(i).getPKTHEADER());
//						ja.add(sm2.format(sm1.parse(list.get(i).getCREATEDDATE())));
//						ja.add(list.get(i).getIGNSTATUS());
//						ja.add(Math.round(totdistancee/1000));
//						array.add(ja);
//					}
//					result.put("aaData", array);
//				}

				a.print(result);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				 e.printStackTrace();
			}

			return null;
		}
		
		public String remove(String str) {
		    if (str != null && str.length() > 0 && str.charAt(str.length()-2)==',') {
		      str = str.substring(0, str.length()-2);
		    }
		    return str;
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
}
