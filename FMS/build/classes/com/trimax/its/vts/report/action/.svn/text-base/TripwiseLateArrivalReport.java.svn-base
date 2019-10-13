package com.trimax.its.vts.report.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.trimax.com.VtsResponse6;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.WaybillDetails;
import com.trimax.ws.vts.vtsutility.WayBillDetails;

public class TripwiseLateArrivalReport extends ActionSupport {


	String path="";
	char ft = 15;
	String str="";
	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double earnAmmount=0.0;
	double dedtotal=0.0;
	int subtotalValues=0;
	double totalammount=0.0;

	String regionTypeAjaxString = "";
	
    public String divisonNo;
    public String depotName;
    public String selectedDate;
    public String scheduleNo;
	
	private Map<Integer, String> divisionlist;
	
	
	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String execute() {

		
		return "success";
	}
	
	public String getScheduleTripStatus() {
//		System.out.println("Enter getScheduleTripStatus()");
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
	public String getSchedule() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String date = req.getParameter("selectedDate");
		Common cm = new Common();
		String formattedgivendate = cm.getDateFromPicker(date);
//		System.out.println("parentId="+parentId+"formattedgivendate"+formattedgivendate);
		
		List<WaybillDetails> l2 = dao.getScheduleNameTrip(parentId, formattedgivendate);
		String regionTypeAjaxString = "<option value='0'>----Select----</option>";
		System.out.println(l2.size());
		for (int i = 0; i < l2.size(); i++) {
				
			regionTypeAjaxString += "<option value=" + l2.get(i).getSchedule_no()
					+ ">" + l2.get(i).getSchedule_name() + "</option>";
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
	        System.out.println("l1.size"+l1.size());
	        System.out.println("l2.size"+l2.size());
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
	  
	  public String getTripwiseLateArrival(){
			
//			System.out.println("Enter into getTripwiseLateArrival");
			HttpServletRequest req = ServletActionContext.getRequest();
			ScheduleTripDurationDAO scheduleDAO=new ScheduleTripDurationDAO();
			
			try {
				CollectionReportDAO dao=new CollectionReportDAO();

				String date1=dao.getDateFromPickerDate(selectedDate);
				
				
				Date  ss1=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			    String runDateTime = sdf.format(ss1);
			    Session session1 = null;
				Transaction transaction  = null;
				Common common = new Common();
//				String depot=req.getParameter("depotId");
//				String division=req.getParameter("divId");
//				System.out.println("depot---"+depot);
//				System.out.println("div---"+division);
//				String statr1= req.getParameter("startdate");
//				String end1= req.getParameter("startdate");
//				String date1=common.getDateFromPicker(startdate);
//				String date2=common.getDateFromPicker(enddate);
				String queryyy;
//				if(division.equalsIgnoreCase("0") && depot.equalsIgnoreCase("0")){
//					queryyy="";
//				}else if(division.equalsIgnoreCase("0")){
//					queryyy= " and oc.org_chart_id='"+depot+"'";
//				}else if(depot.equalsIgnoreCase("0")){
//					queryyy= " and oc.parent_id='"+division+"'";
//				}else{
//					queryyy= " and oc.parent_id='"+division+"' and oc.org_chart_id='"+depot+"'";
//				}
				
				String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
				
				
				String sql = "";
//				 sql="select org_name,v.license_number license_number,d.device_serial_number device_serial_number,date(vd.created_date) regdate from vehicle v inner join vehicle_device vd on v.vehicle_id = vd.vehicle_id " +
//				 		"and vd.status='ACTIVE' " +
//				 		"inner join device d on d.device_id = vd.device_id inner join device_org do on do.device_id=d.device_id  " +
//				 		"inner join org_chart oc on oc.org_chart_id = do.org_id and do.status='ACTIVE' " +
//				 		"where v.status='ACTIVE' and v.deleted_status=0 and d.status='ACTIVE' and d.deleted_status=0  "+queryyy+" order by org_name" ;
					  		
//				 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
//				 transaction = session1.beginTransaction();
//				 Query query = session1.createSQLQuery(sql);
//			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//				List<Map<String, Object>> aliasToValueMapList = query.list();
				 VtsResponse6 alertresults=scheduleDAO.getDataTripWiseLateArrivalReport(date1,scheduleNo);
//				 System.out.println("alertresults Size==="+alertresults.getWaybillDetails().size());
				
				 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br> Tripwise Late Arrival Report</br></h4></div>";
			     regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"<b>Schedule NO:-</b>"+scheduleNo+"</div></div>";
			     
				 
	              
			      
					
					regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
					regionTypeAjaxString +="<thead><tr><th>Sl No.</th><th>Duty type</th><th>Trip No</th><th>Vehicle No</th><th>End Point Name</th><th>Sch Trip Arr. Time </th><th>Act Trip Arr. Time</th><th>Time Difference</th><th>Trip Status</th>"+"</tr></thead><tbody>";
					

					for (int i = 0; i < alertresults.getWaybillDetails().size(); i++) {
						regionTypeAjaxString +="<tr>";
						WayBillDetails list = alertresults.getWaybillDetails().get(i);
						JSONArray ja = new JSONArray();
						int j=i+1;
						regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
						regionTypeAjaxString +="<td align='right'>"+list.getShiftTypeName().toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+list.getSCHEDULETRIPTRIPNUMBER().toString()+"</td>";
						regionTypeAjaxString +="<td align='right'>"+ list.getVEHICLENO().toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ list.getENDBUSSTOPNAME().toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ list.getENDTIME().toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ list.getETMENDTIME().toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ list.getSCHTIMEDIFF().toString() +"</td>";
						regionTypeAjaxString +="<td align='right'>"+ list.getTRIPSTATUS().toString() +"</td>";
//						regionTypeAjaxString +="<td align='right'> </td>";
					
						   regionTypeAjaxString +="</tr>";
					
					}
							   
						
				     
					    regionTypeAjaxString += "</tbody></table></div>        </div>"; 
					   
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out;
				
				
				
					
					//FileOutputStream FOS = new FileOutputStream(path);
					//PrintWriter PW = new PrintWriter(FOS);
					

					out = response.getWriter();
					out.print(regionTypeAjaxString);
				} catch (Exception e) {
					
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
