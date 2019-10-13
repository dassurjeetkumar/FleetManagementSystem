package com.trimax.its.report.action;


import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class OperatedScheduledKm extends ActionSupport{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
  
        public String startdate;
        private String depotlist1;
        public String divisionlist1;
        public String getDivisionlist1() {
            return divisionlist1;
        }


        public void setDivisionlist1(String divisionlist1) {
            this.divisionlist1 = divisionlist1;
        }


        private Map<Integer, String> divisionlist;
     


    public Map<Integer, String> getDivisionlist() {
            return divisionlist;
        }


        public void setDivisionlist(Map<Integer, String> divisionlist) {
            this.divisionlist = divisionlist;
        }
   

    public String getDepotlist1() {
            return depotlist1;
        }


        public void setDepotlist1(String depotlist1) {
            this.depotlist1 = depotlist1;
        }


    public String getStartdate() {
            return startdate;
        }


        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }


StringBuffer regionTypeAjaxString=new StringBuffer();

		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}
		
		public static String rbKey = String.valueOf(getSysParameterForVts());


        @SuppressWarnings("finally")
        public String getOperatedScheduleKMData()
        {
           
            HttpServletRequest req = ServletActionContext.getRequest();
                    try {
                   
                    Date  ss1=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
                    String runDateTime = sdf.format(ss1);
                    String depot=req.getParameter("depotId");
                    int division=Integer.parseInt(req.getParameter("divId"));
                    int schId=Integer.parseInt(req.getParameter("scheduleNo"));
                    String todate=req.getParameter("endate");
               
                    String startdate=req.getParameter("date");
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date startDate = format.parse(startdate);
                    SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
                    String startDate1 = fomat2.format(startDate).toString();
                    JSONObject result = new JSONObject();
                    JSONArray array = new JSONArray();
                   
                    Date toDate = format.parse(todate);
                    String toDate1 = fomat2.format(toDate).toString();
                   
                    model.jaxb.xml.trimax.com.VtsResponse6 operatedResult = null;
                    com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
                    com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
                     operatedResult=port.webGetScheduleDistance(startDate1, toDate1, depot, schId, rbKey);
                     
                    regionTypeAjaxString.append("<div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Schedule wise Distance Travelled Report</br>From Date:- "+startdate+"To Date:- "+toDate1+"</h4></div>");
                    regionTypeAjaxString.append("<div align='right'><b>Current Date:-</b>"+runDateTime+"</div></div>");
                     
                        regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
                        regionTypeAjaxString.append("<thead><tr><th>Sr No.</th><th>Date</th><th>Schedule No.</th><th>Shift Type</th><th>Vehicle No</th><th>Start_Time</th><th>End_Time</th><th>Scheduled KM</th><th>Operated GPS KM</th><th>Cancel GPS KM</th><th>Difference(KM)</th>"+"</tr></thead><tbody>");

                            if(operatedResult.getWaybillDetails().size() !=0){          // both are selected
                         
                            	 String shift = "";
                            	 String status="";
                            	 String date="";
                            	 String []date1=null;
                            for (int i = 0; i < operatedResult.getWaybillDetails().size(); i++) {
                            	JSONArray ja = new JSONArray();
                                ja.add(i+1);
                                ja.add(operatedResult.getWaybillDetails().get(i).getDepotCd());
                                ja.add(operatedResult.getWaybillDetails().get(i).getDUTYDT());
                                date1=operatedResult.getWaybillDetails().get(i).getDUTYDT().split("-");
                                date=date1[2]+"-"+date1[1]+"-"+date1[0];
                                ja.add("<a target=\"_blank\"  href='"+req.getContextPath()+"/scheduletripstatus.action?scheduleNo="+operatedResult.getWaybillDetails().get(i).getSCHEDULENO()+"&selectedDate="+date+"&depotName="+operatedResult.getWaybillDetails().get(i).getDepotId()+"'>"+operatedResult.getWaybillDetails().get(i).getSCHEDULENAME()+"</a>");
                                
                            	switch (operatedResult.getWaybillDetails().get(i).getDutyTypeId()) {
            					case 1:
            						shift = "General shift";
            						break;
            					case 2:
            						shift = "Night Out";
            						break;
            					case 3:
            						shift = "Day Out";
            						break;
            					case 4:
            						shift = "Night Service";
            						break;
            					case 11:
            						shift = "Split Service";
            						break;
            					case 12:
            						shift = "General Night Out";
            						break;
            					case 13:
            						shift = "Depot Spare";
            						break;
            					case 14:
            						shift = "Spare service";
            						break;
            					
            					}
                            	ja.add(shift);
                                ja.add(operatedResult.getWaybillDetails().get(i).getVEHICLENO());
                                ja.add(operatedResult.getWaybillDetails().get(i).getETMSTARTTIME());
                                ja.add(operatedResult.getWaybillDetails().get(i).getGroupedData());
                                ja.add(operatedResult.getWaybillDetails().get(i).getETMNO());
                                ja.add(operatedResult.getWaybillDetails().get(i).getETMENDTIME());
                                ja.add(operatedResult.getWaybillDetails().get(i).getFIRSTDATE());
                                ja.add(operatedResult.getWaybillDetails().get(i).getOrgName());
                                ja.add(operatedResult.getWaybillDetails().get(i).getSchDistance());
                                ja.add(operatedResult.getWaybillDetails().get(i).getGpsDistance());
                                ja.add(operatedResult.getWaybillDetails().get(i).getDeviatedTrip());
                                ja.add(operatedResult.getWaybillDetails().get(i).getCompletedTrip());
                                ja.add((operatedResult.getWaybillDetails().get(i).getSchDistance())-Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitTm()));
                                ja.add(Math.round((operatedResult.getWaybillDetails().get(i).getSchDistance())-Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitTm())-Double.parseDouble(operatedResult.getWaybillDetails().get(i).getCompletedTrip())));
                                ja.add(operatedResult.getWaybillDetails().get(i).getCanceldist());
                                ja.add(operatedResult.getWaybillDetails().get(i).getKmdiff());
                                ja.add(operatedResult.getWaybillDetails().get(i).getRouteExitTm());
                                ja.add(Math.round(Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitTm())-Double.parseDouble(operatedResult.getWaybillDetails().get(i).getCanceldist())));
                                ja.add(operatedResult.getWaybillDetails().get(i).getRouteExitGeocode());
                                ja.add(Integer.parseInt(operatedResult.getWaybillDetails().get(i).getRouteEntryGeocode())-Integer.parseInt(operatedResult.getWaybillDetails().get(i).getRouteExitGeocode()));
                                ja.add(operatedResult.getWaybillDetails().get(i).getRouteEntryGeocode());
                                ja.add(operatedResult.getWaybillDetails().get(i).getRouteEntryTm());
                                ja.add(operatedResult.getWaybillDetails().get(i).getRouteExitKm());
                                if(Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())>operatedResult.getWaybillDetails().get(i).getGpsDistance()) {
                                    ja.add(Math.round(Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())-(operatedResult.getWaybillDetails().get(i).getGpsDistance())));
                                    } else {
                                    	ja.add("0");
                                    }
                                ja.add(operatedResult.getWaybillDetails().get(i).getSunday());
                                ja.add(operatedResult.getWaybillDetails().get(i).getMonday());
                                ja.add(operatedResult.getWaybillDetails().get(i).getETIMCollAmt());
                                
                                
                                
                                
                                regionTypeAjaxString.append("</tr>");
                                status="";
                               // if(operatedResult.getWaybillDetails().get(i).getSchTravelDist()!=null) {
                                if(operatedResult.getWaybillDetails().get(i).getGpsDistance()<=0 && Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())<=0) {
                                	status="Device Issue";
                                }
                                System.out.println(Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())+"===="+0.7*(operatedResult.getWaybillDetails().get(i).getGpsDistance()));
                                if(0.7*operatedResult.getWaybillDetails().get(i).getGpsDistance()>Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())) {
                                	status="Device Issue";
                                	System.out.println("===="+status);  
                                }
                                System.out.println(0.7*Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())+"===="+operatedResult.getWaybillDetails().get(i).getGpsDistance());
                                
                                if(operatedResult.getWaybillDetails().get(i).getGpsDistance()<0.9*Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm()) && operatedResult.getWaybillDetails().get(i).getGpsDistance()>0.3*Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())) {
                                	status="Route Deviation";
                                	System.out.println("===="+status);
                                }
                                if(operatedResult.getWaybillDetails().get(i).getGpsDistance()<0.3*Double.parseDouble(operatedResult.getWaybillDetails().get(i).getRouteExitKm())) {
                                	status="Schedule Deviation";  
                                	System.out.println("===="+status);
                                }
                                //}
                                if(operatedResult.getWaybillDetails().get(i).getENDLATITUDE().equalsIgnoreCase("Y")) {
                                	status="<1000 packets";
                                }
                                
                                if(operatedResult.getWaybillDetails().get(i).getENDLONGITUDE().equalsIgnoreCase("Y")) {
                                	status="0 Lat Long";
                                }
                                
                                if(Integer.parseInt(operatedResult.getWaybillDetails().get(i).getVtuKMPL())>=4) {
                                	status="Not Processed";
                                }
                                ja.add(status);
                                
                                array.add(ja);
                                  
                            }
                            }
//                            regionTypeAjaxString += "</tbody></table></div>        </div>";
                         
                            else {     // no data if size==0
                                regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
                                regionTypeAjaxString.append("<tr>");
                                regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
                                regionTypeAjaxString.append("</tr>");     
                             }
                    HttpServletResponse response = ServletActionContext.getResponse();
                    PrintWriter out;
                        out = response.getWriter();
                        result.put("aaData", array);
                        out.print(result);
                    } catch (Exception e) {
                       
                        e.printStackTrace();
                    }

                    return null;
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
       
       
        public String getScheduleListData(){
           
        HttpServletRequest req = ServletActionContext.getRequest();
        //VtsDataDAO dao = new VtsDataDAO();
        ScheduleTripDurationDAO dao= new ScheduleTripDurationDAO();
        int parentId = Integer.parseInt(req.getParameter("val"));
        String date = req.getParameter("selectedDate");
        Common common = new Common();
        //String date = req.getParameter("selectedDate");
        List<Integer> l1 = dao.getScheduleNameDurationID(parentId);
        List<String> l2 = dao.getScheduleNameDurationName(parentId);
        String regionTypeAjaxString = "<option value='0'>----All----</option>";
        try {
            for (int i = 0; i < l1.size(); i++) {

                regionTypeAjaxString += "<option value=" + l1.get(i)
                        + ">" + l2.get(i).toString() + "</option>";
            }
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
        
        public String getScheduleDropdown(){
            
            HttpServletRequest req = ServletActionContext.getRequest();
            //VtsDataDAO dao = new VtsDataDAO();
            ScheduleTripDurationDAO dao= new ScheduleTripDurationDAO();
            int parentId = Integer.parseInt(req.getParameter("val"));
            String date = req.getParameter("selectedDate");
            Common common = new Common();
            //String date = req.getParameter("selectedDate");
            List<Integer> l1 = dao.getScheduleNameDurationID(parentId);
            List<String> l2 = dao.getScheduleNameDurationName(parentId);
            String regionTypeAjaxString = "<option value='0'>----Select----</option>";
            try {
                for (int i = 0; i < l1.size(); i++) {

                    regionTypeAjaxString += "<option value=" + l1.get(i)
                            + ">" + l2.get(i).toString() + "</option>";
                }
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


}
       
