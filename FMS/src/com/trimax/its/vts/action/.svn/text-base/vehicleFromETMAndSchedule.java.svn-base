package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.trimax.its.common.Common;
import com.trimax.its.report.action.VehicleLocationReportAction;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

	public class vehicleFromETMAndSchedule {
		
	
	public String startdate;
	private String depotlist1;
	
	

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	private Map<Integer, String> divisionlist1;
	
	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}

	public String getDepotlist1() {
		return depotlist1;
	}

	public void setDepotlist1(String depotlist1) {
		this.depotlist1 = depotlist1;
	}
	
	private Map<Integer, String> alertlist1;
	
	public Map<Integer, String> getAlertlist1() {
		return alertlist1;
	}

	public void setAlertlist1(Map<Integer, String> alertlist1) {
		this.alertlist1 = alertlist1;
	}

//	String regionTypeAjaxString = "";
	StringBuffer regionTypeAjaxString = new StringBuffer();
	VtsDataDAO dao = VtsDataDAO.getInstance();
	
	public String execute() throws Exception {
		divisionlist1 = dao.getDepot1();
		return "success";
	}
		
		 public static String rbKey = String.valueOf(getSysParameterForVts());
		 
		public String vehicleMismatchFromETMToScheduleReport()
 {
		try {
			VtsDataDAO dao= VtsDataDAO.getInstance();
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			System.out.println("in get report");
//			int depotId=Integer.parseInt(depotlist1);
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd"); // change date in sql format
			String startDate1 = fomat2.format(startDate).toString();
			System.out.println("startdate====="+startDate1+"====="+depotlist1);
			
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction = null;
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 
			 String sql="select egt.schedule_no,etim_no,egt.vehicle_no as etm_vehicle,dsm.vehicle_no as schedule_vehicle,depot_name from bmtcGprs.etim_gprs_ticket egt inner join daily_schedule_mapping_vehicle dsm " +
			 		" on egt.schedule_id=dsm.form_four_id and trip_no=1 where ticket_date='"+startDate1+"' and dsm.depot_id="+depotlist1+" and egt.depot_id="+depotlist1+" " +
			 		" and trip_number=1 and operated_date='"+startDate1+"' and is_dead_trip=1 group by schedule_no,shift_no";
			 
			 Query query = session1.createSQLQuery(sql).addScalar("schedule_no").addScalar("etim_no").addScalar("etm_vehicle").addScalar("schedule_vehicle").addScalar("depot_name");
			  
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String,Object>> aliasToValueMapList = query.list();
				
				regionTypeAjaxString.append("<div id='header' style='display: none;'><div align='center'><h4>Vehicle Mismatch Report </h4></div>");

		        regionTypeAjaxString.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
		    
				regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
				regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot Name</th><th>etim_no</th><th>ETM_Vehicle</th>" +
						"<th>Schedule_Mapping_Vehicle</th><th>Schedule No</th>"+ "</tr></thead><tbody>");
				 System.out.println("aliasToValueMapList.size()=="+aliasToValueMapList.size());
				 
			     for (int i = 0; i < aliasToValueMapList.size(); i++) {
			    	 regionTypeAjaxString.append("<tr>");
				Map<String,Object> list = aliasToValueMapList.get(i);

	     		regionTypeAjaxString.append("<td align='right'>"+ (i+1) +"</td>");
				regionTypeAjaxString.append("<td align='right'>"+list.get("depot_name").toString()+"</td>");
				regionTypeAjaxString.append("<td align='right'>"+list.get("etim_no").toString()+"</td>");
				regionTypeAjaxString.append("<td align='right'>"+list.get("etm_vehicle").toString()+"</td>");
				regionTypeAjaxString.append("<td align='right'>"+list.get("schedule_vehicle").toString()+"</td>");
				regionTypeAjaxString.append("<td align='right'>"+list.get("schedule_no").toString()+"</td>");
				
				   regionTypeAjaxString.append("</tr>");
			}
			     System.out.println("End Here1");
					 regionTypeAjaxString.append("</table> </div> </div> ");
					
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
				
				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	  
	  
	  
	  return null;
  }

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			
//			
//
//			model.jaxb.xml.trimax.com.VtsResponse activeresult = null;
//			com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
//			com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
//			activeresult = port.webGetStationaryVehicleReport(String.valueOf(24), startDate1, startDate1+" 23:59:59", depotlist1, rbKey); // calling Web service
//			
//
//			regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Stationary Vehicle Report</br>From Date:- "
//					+ startdate + "</h4></br></div>");
//
//			regionTypeAjaxString.append("<div align='right'><b>Current Date:-</b>"
//					+ runDateTime + "</div></div>");
//
//			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
//			regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot Name</th><th>etim_no</th><th>ETM_Vehicle</th>" +
//					"<th>Schedule_Mapping_Vehicle</th><th>Schedule No</th>"
//					+ "</tr></thead><tbody>");
//
//
//			HttpServletResponse response = ServletActionContext.getResponse();
//			int i=1;
//			String location="";
////			System.out.println("size-"+ activeresult.getVtsDatamodel().size());
//			for (i = 0; i < activeresult.getVtsDatamodel().size(); i++) {
//				
//				regionTypeAjaxString.append("<tr>");
//				regionTypeAjaxString.append("<td>" + (i+1) + "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getVtsDatamodel().get(i).getOrgName()+ "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getVtsDatamodel().get(i).getLICENCENUMBER() + "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getVtsDatamodel().get(i).getDEVICEID() + "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getVtsDatamodel().get(i).getISTDATE() + "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getVtsDatamodel().get(i).getACTTIMEARR() + "</td>");
//				regionTypeAjaxString.append("<td>"+ activeresult.getVtsDatamodel().get(i).getSCHDIFF() + "</td>");
//				VehicleLocationReportAction vdao = new VehicleLocationReportAction();
//		 location=vdao.getVehicleLocation(Double.parseDouble(activeresult.getVtsDatamodel().get(i).getLAT()),Double.parseDouble(activeresult.getVtsDatamodel().get(i).getLONGITUDE()));
////					System.out.println(location);
//					regionTypeAjaxString.append("<td>"+ location + "</td>");
//				regionTypeAjaxString.append("</tr>");
//			}
//
//			if (activeresult.getVtsDatamodel().size() == 0) { // if no records
//				regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
//				regionTypeAjaxString.append("<tr>");
//				regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
//				regionTypeAjaxString.append("</tr>");
//			}
//			PrintWriter out;
//			out = response.getWriter();
//			out.print(regionTypeAjaxString);       // to print
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
		

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
			
	}
