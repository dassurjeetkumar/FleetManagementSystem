package com.trimax.its.vts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.StageWiseTicketConsumptionDOA;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.its.vts.model.WaybillDetails;

import org.hibernate.Session;

	public class MissedTripAction {
		
		String str="";
		String path="";
		char ft = 15;
	
	 public String startdate;


	public String getStartdate() {
			return startdate;
		}


		public void setStartdate(String startdate) {
			this.startdate = startdate;
		}

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

		
	double totalAmmount=0;
	int subtotalValues=0;

//	String regionTypeAjaxString = "";
	StringBuilder regionTypeAjaxString=new StringBuilder();

	
		public String execute() {
			System.out.println("in execute");
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		
		public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}

		double mnthTotal=0.0;
		Double netTotal=0.0;
		
		 public static String rbKey = String.valueOf(getSysParameterForVts());
		public String getMissedTripList()
		{
					System.out.println("in missed trips Report ");
					
					try {
						HttpServletRequest req=ServletActionContext.getRequest();
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			System.out.print("depotNo is---" + depotlist1);
			System.out.println("division id" + divisionlist1);
String schedule=req.getParameter("scheduleId");
String startDate11=req.getParameter("startdate");
String endDate1=req.getParameter("enddate");

System.out.println("schedule------"+schedule+"start----"+startDate11+"end------"+endDate1);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(startdate);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			String startDate1 = fomat2.format(startDate).toString();
			
			Date endDate = format.parse(endDate1);
			String endate = fomat2.format(endDate).toString();
			System.out.println("startdate--" + startDate1);

			String filePath = "Report/";

			String fileName = "MissedTripsReport.txt";
			System.out.println("we are in ajax code................");

			regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Missed Trips Report</br>From Date:- "
					+ startDate1 + " To Date:-" + endate + "</h4></br></div>");
			regionTypeAjaxString.append("<div align='right'><b>Current Date:-</b>"
					+ runDateTime + "</div></div>");

			String nwkr = ""
					+ "                                   Missed Trips Report    " +

					"\n                                    From Date:"
					+ startDate11 + "  End Date:" + endDate1
					+ "                                              ";
			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot Name</th><th>Schedule No..</th><th>Cond Token No.</th><th>Driver Token No.</th>" +
					"<th>Schedule Trips</th><th>Fully Operated Trips</th><th>Partially Operated Trips</th><th>Missed Trips</th>"
					+ "</tr></thead><tbody>");

			String headingprint = " "
					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n"
					+ add("SNo", 3)
					+ "|"
					+ add("Depot Name", 14)
					+ "|"
					+ add("Schedule No.", 10)
					+ "|"
					+ add("Cond Token No", 11)
					+ "|"
					+ add("Driver Token No", 7)
					+ "|"
					+ add("Schedule Trips", 7)
					+ " "
					+ add("Fully Operated Trips", 7)
					+ " "
					+ add("Partial Operated Trips", 7)
					+ " "
					+ add("Missed Trips", 7)
					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";

			String path = realpath + filePath + fileName;
			str += ft + nwkr + add(headingprint, 5);

			HttpServletResponse response = ServletActionContext.getResponse();
			int i = 1;
			int j=1;
//			System.out.println("size-" + nrdresult.getWaybillDetails().size());
//			for (i = 0; i < nrdresult.getWaybillDetails().size(); i++) {
//
//				regionTypeAjaxString.append("<tr>");
//				regionTypeAjaxString.append("<td>" + j + "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getOrgName()
//						+ "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getVEHICLENO()
//						+ "</td>");
//				regionTypeAjaxString.append( "<td>"
//						+ nrdresult.getWaybillDetails().get(i).getDEVICEID()
//						+ "</td>");
//				regionTypeAjaxString.append("<td>"
//						+ nrdresult.getWaybillDetails().get(i).getFIRSTDATE()
//						+ "</td>");
//
//				regionTypeAjaxString.append("</tr>");
//
//				j++;
//			}

			/*if (nrdresult.getWaybillDetails().size() == 0) { // if no records
				regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
				regionTypeAjaxString.append("</tr>");
			}*/

			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString); // to print
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

		  
	 @SuppressWarnings("unchecked")
	public String getScheduleName() {

		 System.out.println("yesss");
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		StageWiseTicketConsumptionDOA doa=new StageWiseTicketConsumptionDOA();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	    String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	  
		List<String> l1=doa.getScheduleNameID(parentId);
	
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value='0'>------All------</option>";
		//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
		for (int i = 0; i < l1.size(); i++) {
			
			String schedulearr[] = l1.get(i).toString().split("#");
			regionTypeAjaxString += "<option value=" + schedulearr[0] + ">"
					+ schedulearr[1] + "</option>";
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





	
	
	

