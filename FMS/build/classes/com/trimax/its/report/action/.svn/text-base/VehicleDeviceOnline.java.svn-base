package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.ws.vts.vtsutility.WayBillDetails;

public class VehicleDeviceOnline {
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	 
	 private Map<Integer, String> depotlist;
	 private String drivertoken;
	 private Map<Integer, String> drivertokenlist;
	 public String divisionid;
		
		public String getDivisionid() {
			return divisionid;
		}
		public void setDivisionid(String divisionid) {
			this.divisionid = divisionid;
		}

		 public String depotid;
		 
	public String getDepotid() {
			return depotid;
		}
		public void setDepotid(String depotid) {
			this.depotid = depotid;
		}
	public String getDrivertoken() {
		return drivertoken;
	}

	public void setDrivertoken(String drivertoken) {
		this.drivertoken = drivertoken;
	}

	public Map<Integer, String> getDrivertokenlist() {
		return drivertokenlist;
	}

	public void setDrivertokenlist(Map<Integer, String> drivertokenlist) {
		this.drivertokenlist = drivertokenlist;
	}

	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}

	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
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


	
	char fl = 18;
		char f2 = 12;
		int pagi = 1;

		int FULL_LEN = 120;
		int HALF_LEN = 60;

		int subtotalTickets=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";
	
	


	public String execute() throws Exception {
		//this.setDepotlist(getDepotName());
		return "success";
	}


	
	@SuppressWarnings("finally")
public String vehicleDeviceOnline()
  {
		ScheduleTripDurationDAO scheduleDAO=new ScheduleTripDurationDAO();
	try {
		  
	      Date  ss1=new Date();
	      Common common = new Common();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
		//System.out.println("enddate"+enddate);
		  String date1=common.getDateFromPicker(startdate);
		//String date2=common.getDateFromPicker(enddate);
		//System.out.println("date2===="+date2);
		Session session1 = null;
		Transaction transaction  = null;
		
		String sql="";
//		HibernateUtilVtu h = new HibernateUtilVtu();
//		session1 = h.getSession("");
//		 transaction = session1.beginTransaction();
//		  sql = "SELECT IFNULL(lICENCE_NUMBER,'')lICENCE_NUMBER,  IFNULL(DEVICE_ID,'')DEVICE_ID, IFNULL(org_name,'')org_name " +
//		 		"FROM `dash_board_active_vehicle_daily` WHERE `record_date` between '" + date1 +" 00:00:00' and '" + date1 +" 23:59:59' ";
//
//		 Query query = session1.createSQLQuery(sql).addScalar("lICENCE_NUMBER").addScalar("DEVICE_ID").addScalar("org_name");
//	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//		List<Map<String, Object>> aliasToValueMapList = query.list();
//					String realpath = ServletActionContext.getRequest()
//							.getRealPath("/");
		
		VtsResponse6 alertresults=scheduleDAO.getDataVehicleDeviceOnlineReport(date1);
					
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Vehicle Device Online Report</br>From Date:- "+startdate+" To Date:-"+ startdate+"</h4></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        
				        
				
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
						regionTypeAjaxString +="<tr><td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Depot No</b></td><td  align='center' width='10%'><b>Device NO</b></td><td  align='center' width='10%'><b>Vehicle NO</b></td></tr>";

						if(alertresults.getWaybillDetails().size()==0){
							regionTypeAjaxString +="<tr><td colspan='5'><center><b>No Records Found</b></center></td></tr>";
						}
						for (int i = 0; i < alertresults.getWaybillDetails().size(); i++) {
							int j=i+1;
							
							WayBillDetails list = alertresults.getWaybillDetails().get(i);
							
								
						
								regionTypeAjaxString +="<tr>";
						
							regionTypeAjaxString +="<td>"+j+"</td>";
							regionTypeAjaxString +="<td>"+list.getOrgName().toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.getDEVICEID().toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.getVEHICLENO().toString()+"</td>";
						
							regionTypeAjaxString +="</tr>";
							}
						
						
					
					 
					 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
//				
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
				
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					}  catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return null;
			}
		
	
	
	
		
		static String add(String str1, int a1) {
			StringBuilder sb = new StringBuilder(str1);
			int m1 = str1.length();
			if (m1 >= a1) {
				str1.substring(0, a1 - 1);
			}
			a1 = a1 - m1;
			for (int i = 0; i <= a1; i++) {
				sb.append(" ");
			}
			String sb1 = sb.toString();
			return sb1;
		}
		
		static String addMoney(String str1, int a1) {
	        StringBuilder sb = new StringBuilder(str1);
	        StringBuilder sb2 = new StringBuilder();

	        //String sb1 =
	        int m1 = str1.length();
	        if (m1 >= a1) {
	            str1.substring(0, a1 - 1);
	            return str1;
	        }
	        a1 = a1 - m1;
	        for (int i = 0; i < a1; i++) {
	            sb2.append(" ");
	        }
	        sb2.append(sb);
	        String sb1 = sb2.toString();
	        return sb1;
	    }
}
