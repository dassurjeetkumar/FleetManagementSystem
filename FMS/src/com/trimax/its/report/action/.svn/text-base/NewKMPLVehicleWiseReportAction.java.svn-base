package com.trimax.its.report.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.report.dao.CancellationKMReportDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.VehicleDockingReportDao;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.ws.vts.vtsutility.WsUtil;
import com.trimax.ws.vts.vtsutility.WsUtil_Service;

public class NewKMPLVehicleWiseReportAction extends ActionSupport{
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
        public String getKMPLScheduleWiseNewReportData()
        {
           
            HttpServletRequest req = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
                    try {
                    	JSONObject result = new JSONObject();
                    Date  ss1=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
                    String runDateTime = sdf.format(ss1);
                    int depot=Integer.parseInt(req.getParameter("depotId"));
//                    int division=Integer.parseInt(req.getParameter("divId"));
                    int schId=Integer.parseInt(req.getParameter("scheduleNo"));
                    String todate=req.getParameter("endate");
               
                    String startdate=req.getParameter("date");
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date startDate = format.parse(startdate);
                    SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
                    String startDate1 = fomat2.format(startDate).toString();
                   
                    Date toDate = format.parse(todate);
                    String toDate1 = fomat2.format(toDate).toString();
                   System.out.println("in gt method=="+depot+"=="+schId+"="+startDate1+"==="+toDate1);
                  
                    String scheduleNo="";
                    if(schId ==0){
                    	scheduleNo = "!=0" ;
                    }else{
                    	scheduleNo = "="+String.valueOf(schId);
                    }
                    response.setContentType("application/json");
        			response.setHeader("Cache-Control", "no-store");
        			PrintWriter out = response.getWriter();
        			result = getDataForKMPLSchedule(depot, scheduleNo,toDate1,startDate1);
        			out.print(result);       // to print
            		} catch (Exception e) {
            			e.printStackTrace();
            		}
            		return null;
            	}
        
        
        public String getKMPLVehicleWiseNewData() {
    		HttpServletRequest request = ServletActionContext.getRequest();
    		HttpServletResponse response = ServletActionContext.getResponse();
    		try {
    			JSONObject result = new JSONObject();
    		    int depot=Integer.parseInt(request.getParameter("depotId"));
              String vehId=request.getParameter("vehicleNo");
              String todate=request.getParameter("endate");
         
              String startdate=request.getParameter("date");
              SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
              Date startDate = format.parse(startdate);
              SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
              String startDate1 = fomat2.format(startDate).toString();
              String vehicle_no="";
              if(vehId.equalsIgnoreCase("ALL")){
              	vehicle_no = "!=''" ;
              }else{
              	vehicle_no = "='"+vehId+"'";
              }
              Date toDate = format.parse(todate);
              String toDate1 = fomat2.format(toDate).toString();
             System.out.println("in gt method=="+depot+"=="+vehId+"="+startDate1+"==="+toDate1);
    		
    			response.setContentType("application/json");
    			response.setHeader("Cache-Control", "no-store");
    			PrintWriter out = response.getWriter();
    			result = getDataForKMPLVeh(depot, vehicle_no,toDate1,startDate1);
    			out.print(result);
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {

    		}
    		return null;

    	}
        
        
        
        @SuppressWarnings("unchecked")
    	public JSONObject getDataForKMPLVeh(int depotId,String vehicleNo,String todate,String fromDate){
    		JSONObject result = new JSONObject();
    		try{
    			model.jaxb.xml.trimax.com.VtsResponse6 operatedResult = null;
                com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
                com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
                
    			 operatedResult=port.webGetKMPLReportVehicleWise(fromDate, todate, depotId,vehicleNo);
    			
    			JSONArray array = new JSONArray();
    			for (int i = 0; i < operatedResult.getWaybillDetails().size(); i++) {
    				JSONArray ja = new JSONArray();
    				ja.add(i+1);
    				
    				ja.add(operatedResult.getWaybillDetails().get(i).getDepotName());
    				ja.add("<a data-toggle='modal'  role='button' href='#mymodal43'  onclick=javascript:viewVehicleWiseKMPLDetailsData('"
    						+ todate
    						+ "','"
    						+ fromDate+"','"+depotId+"','"+operatedResult.getWaybillDetails().get(i).getVEHICLENO()+"') >"
    						+ operatedResult.getWaybillDetails().get(i).getVEHICLENO() 
    						+ "</a>");
    			
    				ja.add(operatedResult.getWaybillDetails().get(i).getSchDistance());
    				ja.add(operatedResult.getWaybillDetails().get(i).getRunningKM());
    				ja.add(operatedResult.getWaybillDetails().get(i).getGpsDistance());
    				ja.add(operatedResult.getWaybillDetails().get(i).getFuel());
    				ja.add(operatedResult.getWaybillDetails().get(i).getSchKMPL());
    				ja.add(operatedResult.getWaybillDetails().get(i).getVtuKMPL() );
    				
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
    	public JSONObject getDataForKMPLSchedule(int depotId,String scheduleNo,String todate,String fromDate){
    		JSONObject result = new JSONObject();
    		try{
    			model.jaxb.xml.trimax.com.VtsResponse6 operatedResult = null;
                com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
                com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
                
    			 operatedResult=port.webGetKMPLReportScheduleWise(fromDate, todate, depotId,scheduleNo);
    			
    			JSONArray array = new JSONArray();
    			for (int i = 0; i < operatedResult.getWaybillDetails().size(); i++) {
    				JSONArray ja = new JSONArray();
    				ja.add(i+1);
    			System.out.println("Schedule NO==="+operatedResult.getWaybillDetails().get(i).getSCHEDULENO());
    				ja.add(operatedResult.getWaybillDetails().get(i).getDepotName());
    				ja.add("<a data-toggle='modal'  role='button' href='#mymodal44'  onclick=javascript:viewScheduleWiseKMPLData('"
    						+ todate
    						+ "','"
    						+ fromDate+"','"+depotId+"','"+operatedResult.getWaybillDetails().get(i).getSCHEDULENO().replace(" ", "@")+"') >"
    						+ operatedResult.getWaybillDetails().get(i).getSCHEDULENO()
    						+ "</a>");
    			
    				ja.add(operatedResult.getWaybillDetails().get(i).getSchDistance());
    				ja.add(operatedResult.getWaybillDetails().get(i).getRunningKM());
    				ja.add(operatedResult.getWaybillDetails().get(i).getGpsDistance());
    				ja.add(operatedResult.getWaybillDetails().get(i).getFuel());
    				ja.add(operatedResult.getWaybillDetails().get(i).getSchKMPL());
    				ja.add(operatedResult.getWaybillDetails().get(i).getVtuKMPL() );
    				
    			    array.add(ja);
    			}
    			result.put("aaData", array);
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		} finally {
    		}
    		return result;
    	}
        
        
     
        
        @SuppressWarnings("finally")
        public String getKMPLDriverWiseNewData()
        {
           
            HttpServletRequest req = ServletActionContext.getRequest();
                    try {
                   
                    Date  ss1=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
                    String runDateTime = sdf.format(ss1);
                    int depot=Integer.parseInt(req.getParameter("depotId"));
                    int division=Integer.parseInt(req.getParameter("divId"));
                    String tokenNo=req.getParameter("driverToken");
                    String todate=req.getParameter("endate");
               String tokenNO="";
                    String startdate=req.getParameter("date");
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date startDate = format.parse(startdate);
                    SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
                    String startDate1 = fomat2.format(startDate).toString();
                   
                    Date toDate = format.parse(todate);
                    String toDate1 = fomat2.format(toDate).toString();
                   System.out.println("in gt method=="+depot+"=="+division+"=="+tokenNo+"="+startDate1+"==="+toDate1);
                   if(tokenNo.equals("0")){
                	   tokenNO = "!=0" ;
                   }else{
                	   tokenNO = "="+String.valueOf(tokenNo);
                   }
                    model.jaxb.xml.trimax.com.VtsResponse6 operatedResult = null;
                    com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
                    com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
                    
                     operatedResult=port.webGetKMPLReportDriverWise(startDate1, toDate1, depot,tokenNO);
                     
                    regionTypeAjaxString.append("<div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Driver wise KMPL Report</br>From Date:- "+startdate+"To Date:- "+toDate1+"</h4></div>");
                    regionTypeAjaxString.append("<div align='right'><b>Current Date:-</b>"+runDateTime+"</div></div>");
                     
                        regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
                        regionTypeAjaxString.append("<thead><tr><th>Sr No.</th><th>Depot No</th><th>Driver Name</th><th>Token</th><th>Sch KM</th><th>Log KM</th><th>GPS KM</th><th>Fuel</th><th>Log KMPL</th><th>GPS KMPL</th>"+"</tr></thead><tbody>");

                           
                        HttpServletResponse response = ServletActionContext.getResponse();
            			System.out.println("size-"+ operatedResult.getWaybillDetails().size());
            			for (int i = 0; i < operatedResult.getWaybillDetails().size(); i++) {
            				
            				regionTypeAjaxString.append("<tr>");
            				regionTypeAjaxString.append("<td>" + (i+1) + "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getDepotName()+ "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getDRIVERNAME() + "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getDRIVERTOKENNO() + "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getSchDistance() + "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getRunningKM()+ "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getGpsDistance() + "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getFuel() + "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getSchKMPL() + "</td>");
            				regionTypeAjaxString.append("<td>"+ operatedResult.getWaybillDetails().get(i).getVtuKMPL() + "</td>");

            				regionTypeAjaxString.append("</tr>");

            				
            			}

            			if (operatedResult.getWaybillDetails().size() == 0) { // if no records
            				regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
            				regionTypeAjaxString.append("<tr>");
            				regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
            				regionTypeAjaxString.append("</tr>");
            			}
            			PrintWriter out;
            			out = response.getWriter();
            			out.print(regionTypeAjaxString);       // to print
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
       
       
       
        
        public String getDepotList() {	
        	
    		// get Depot List..
    		HttpServletRequest req = ServletActionContext.getRequest();
    		/*String runningkm = req.getParameter("dockingtype");
    		System.out.println("runningkm"+runningkm);*/
    		VehicleDockingReportDao dao=new VehicleDockingReportDao();
    		int parentId = Integer.parseInt(req.getParameter("division"));
    		System.out.println("-------------"+parentId);
    		List<String> l1 = dao.getDepotId(parentId);
    		List<String> l2 = dao.getDepotName(parentId);
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
        
        public String getPerticularVehicles() {

    		HttpServletRequest req = ServletActionContext.getRequest();
    		VtsDataDAO dao = VtsDataDAO.getInstance();
    		int parentId = Integer.parseInt(req.getParameter("val"));
    		List<String> l1 = getVehicleID(parentId);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='-1'>------select------</option>";
    		regionTypeAjaxString += "<option value='0'>All</option>";    		
    		for (int i = 0; i < l1.size(); i++) {
    			String vehiclearr[] = l1.get(i).toString().split("#");
    			regionTypeAjaxString += "<option value=" + vehiclearr[0] + ">"
    					 + vehiclearr[1] + "</option>";
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
    	
    	 public String getDepotDriverList() {

    		
     		HttpServletRequest req = ServletActionContext.getRequest();
     		VtsDataDAO dao = VtsDataDAO.getInstance();
     		int parentId = Integer.parseInt(req.getParameter("val"));
     		List<String> l1 = getDriverList(parentId);
     		String regionTypeAjaxString = "";
     		regionTypeAjaxString += "<option value='-1'>------select------</option>";
     		regionTypeAjaxString += "<option value='0'>All</option>";    		
     		for (int i = 0; i < l1.size(); i++) {
     			String driverArr[] = l1.get(i).toString().split("#");
     			regionTypeAjaxString += "<option value=" + driverArr[0] + ">"
     					 + driverArr[0] + "</option>";
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

        
        public List getVehicleID(int depotID) {
    		List list = new ArrayList();
    		String qry = "select vehicle_id,license_number from vehicle where deleted_status=0 and org_chart_id= "
    				+ depotID + " group by vehicle_id   order by vehicle_id";
    		try {
    			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
    			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    			List<Map<String, Object>> aliasToValueMapList = query.list();
//    			System.out.println("size =="+aliasToValueMapList.size());
    			if (aliasToValueMapList.size() > 0) {

    				for (int i = 0; i < aliasToValueMapList.size(); i++) {
    					Map<String, Object> rs = aliasToValueMapList.get(i);
    					list.add(rs.get("vehicle_id").toString() + "#"
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
        
        
        public List getDriverList(int depotID) {
    		List list = new ArrayList();
    		 System.out.println("in drop"+depotID);
    		String qry = "SELECT TOKEN, EMPLOYEE_NAME FROM employee WHERE EMPLOYEE_DESIGNATION " +
    				" IN ('DriverConductor','driver') AND STATUS = 'active' AND org_chart_id ="+depotID+" ORDER BY TOKEN";
    		try {
    			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
    			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    			List<Map<String, Object>> aliasToValueMapList = query.list();
//    			System.out.println("size =="+aliasToValueMapList.size());
    			if (aliasToValueMapList.size() > 0) {

    				for (int i = 0; i < aliasToValueMapList.size(); i++) {
    					Map<String, Object> rs = aliasToValueMapList.get(i);
    					list.add(rs.get("TOKEN").toString() + "#"
    							+ rs.get("EMPLOYEE_NAME").toString());
    				}
    			}
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		} finally {
    			HibernateUtil.closeSession();
    			return list;
    		}

    	}
        
        
        public String getKMPLVehWiseDetails() throws IOException{
    		
    		
    		HttpServletRequest request = ServletActionContext.getRequest();
    		HttpServletResponse response = ServletActionContext.getResponse();	
    		PrintWriter out = null;
    		JSONObject result = new JSONObject();
    		try{	
    			String formattedgivendate=request.getParameter("fromdate").toString();
    			String endDate=request.getParameter("endDate").toString();
    			int depotId=Integer.parseInt(request.getParameter("depotId"));
    			
    			String vehicleNo=request.getParameter("vehicleNo");
    			System.out.println("in=="+vehicleNo+"==="+depotId+"==="+endDate+"=="+formattedgivendate);
    			Common cm = new Common();
    				response.setContentType("application/json");
    				out = response.getWriter();
    				result =getDataForVehWiseKMPL(vehicleNo,formattedgivendate, endDate,depotId);
    			} catch (Exception ex) {
    				ex.printStackTrace();
    			}
    			out.print(result);
    			return null;
    		}
        
        
    public String getKMPLScheduleWiseDetails() throws IOException{
    		
    		
    		HttpServletRequest request = ServletActionContext.getRequest();
    		HttpServletResponse response = ServletActionContext.getResponse();	
    		PrintWriter out = null;
    		JSONObject result = new JSONObject();
    		try{	
    			String formattedgivendate=request.getParameter("fromdate").toString();
    			String endDate=request.getParameter("endDate").toString();
    			int depotId=Integer.parseInt(request.getParameter("depotId"));
    			
    			String scheduleNo=request.getParameter("scheduleNo").replace("@", " ");
    			System.out.println("in=="+scheduleNo+"==="+depotId+"==="+endDate+"=="+formattedgivendate);
//    			Common cm = new Common();
    				response.setContentType("application/json");
    				out = response.getWriter();
    				result =getDataForScheduleWiseKMPL(scheduleNo,formattedgivendate, endDate,depotId);
    			} catch (Exception ex) {
    				ex.printStackTrace();
    			}
    			out.print(result);
    			return null;
    		}
        
        
        
        @SuppressWarnings("unchecked")
    	public JSONObject getDataForVehWiseKMPL(String vehicleNo,String fromDate,String enddate,int depotId) {

        	JSONObject result = new JSONObject();
			System.out.println("selectedDate== "+vehicleNo+"=="+fromDate+"depot== "+enddate+"=="+depotId);
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
			
				VtsResponse6 alertresult = port.webGetKMPLReportVehWiseDetails(vehicleNo, fromDate, enddate, depotId);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					JSONArray ja = new JSONArray();
					ja.add(i+1);
				ja.add(alertresult.getWaybillDetails().get(i).getVEHICLENO());
				ja.add(alertresult.getWaybillDetails().get(i).getSchDistance());
				ja.add(alertresult.getWaybillDetails().get(i).getRunningKM());
				ja.add(alertresult.getWaybillDetails().get(i).getGpsDistance());
				ja.add(alertresult.getWaybillDetails().get(i).getFuel());
				ja.add(alertresult.getWaybillDetails().get(i).getSchKMPL());
				ja.add(alertresult.getWaybillDetails().get(i).getVtuKMPL());
				ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
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
    	public JSONObject getDataForScheduleWiseKMPL(String scheduleNo,String fromDate,String enddate,int depotId) {

        	JSONObject result = new JSONObject();
			System.out.println("selectedDate== "+scheduleNo+"=="+fromDate+"depot== "+enddate+"=="+depotId);
			
			try {
				WsUtil_Service service = new WsUtil_Service();
				WsUtil port = service.getWsUtilPort();
			
				VtsResponse6 alertresult = port.webGetKMPLReportScheduleWiseDetails(scheduleNo, fromDate, enddate, depotId);
				JSONArray array = new JSONArray();
				for (int i = 0; i < alertresult.getWaybillDetails().size(); i++) {
					JSONArray ja = new JSONArray();
					ja.add(i+1);
				ja.add(alertresult.getWaybillDetails().get(i).getSCHEDULENO());
				ja.add(alertresult.getWaybillDetails().get(i).getSchDistance());
				ja.add(alertresult.getWaybillDetails().get(i).getRunningKM());
				ja.add(alertresult.getWaybillDetails().get(i).getGpsDistance());
				ja.add(alertresult.getWaybillDetails().get(i).getFuel());
				ja.add(alertresult.getWaybillDetails().get(i).getSchKMPL());
				ja.add(alertresult.getWaybillDetails().get(i).getVtuKMPL());
				ja.add(alertresult.getWaybillDetails().get(i).getDUTYDT());
				    array.add(ja);
				}
				result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return result;
		}
}
       
