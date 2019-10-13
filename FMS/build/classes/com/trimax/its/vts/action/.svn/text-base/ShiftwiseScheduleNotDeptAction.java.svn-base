package com.trimax.its.vts.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;
import org.hibernate.Session;

	public class ShiftwiseScheduleNotDeptAction {
		
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

		
		
	double totalAmmount=0;
	int subtotalValues=0;

//	String regionTypeAjaxString = "";
	StringBuilder regionTypeAjaxString=new StringBuilder();

	
		public String execute() {
			System.out.println("in execute");
			
			return "success";
		}

		
		 public static String rbKey = String.valueOf(getSysParameterForVts());
		public String getNotDepartedData()
		{
					System.out.println("in late dept Report ");
					
					try {
						HttpServletRequest request=ServletActionContext.getRequest();
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
		
			String date=request.getParameter("selectedDate");

			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = format.parse(date);
			SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			String selectedDate= fomat2.format(startDate).toString();
			System.out.println("startdate--" + selectedDate);

			String filePath = "Report/";

			String fileName = "ShiftwiseScheduleNotDepartureReport.txt";
			System.out.println("we are in ajax code................");

			regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Shift wise Schedule Not Departure Report</br>From Date:- "
					+ startDate  +"</h4></br></div>");
			regionTypeAjaxString.append("<div align='right'><b>Current Date:-</b>"
					+ runDateTime + "</div></div>");

			String nwkr = ""
					+ "                                   Shiftwise Schedule Not Departed    " +

					"\n                                    From Date:"
					+ startDate 
					+ "                                              ";
			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString.append("<thead><tr><th>Division</th><th>No.Of Schedule</th><th>N/o</th><th>Gen Shift</th><th>Shift</th><th>Total</th><th>Percentage(%)</th>"
					+ "</tr></thead><tbody>");

			String headingprint = " "
					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n"
//					+ add("Date", 10)
//					+ "|"
					+ add("No. of Schedule", 9) + "|" + add("N/o.", 10) + "|" + add("Gen Shift", 10) 
					+ "|" + add("Shift", 7) + "|" + add("Total", 7) + "|" + add("%", 7) + "|" + " "
					+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";

			String path = realpath + filePath + fileName;
			str += ft + nwkr + add(headingprint, 5);

			HttpServletResponse response = ServletActionContext.getResponse();
			int i = 1;
			regionTypeAjaxString.append("<tr><td><b><center>NORTH DIVISION</center></b></td></tr>");
			// loop for 7 div 
			regionTypeAjaxString.append("<tr><td>Depot-09</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-10</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-11</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-14</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-22</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-23</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-26</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-30</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-40</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-43</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-45</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-46</td></tr>");
			// Total
			regionTypeAjaxString.append("<tr><td><b><center>SOUTH DIVISION</center></b></td></tr>");
			//loop
			regionTypeAjaxString.append("<tr><td>Depot-02</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-03</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-04</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-15</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-20</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-27</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-26</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-33</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-34</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-44</td></tr>");
			//total
			regionTypeAjaxString.append("<tr><td><b><center>EAST DIVISION</center></b></td></tr>");
			//loop
			regionTypeAjaxString.append("<tr><td>Depot-06</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-19</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-24</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-29</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-32</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-38</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-39</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-41</td></tr>");
			//total
			regionTypeAjaxString.append("<tr><td><b><center>WEST DIVISION</center></b></td></tr>");
			//loop
			regionTypeAjaxString.append("<tr><td>Depot-12</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-16</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-17</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-21</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-31</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-35</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-36</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-37</td></tr>");
			//total
			regionTypeAjaxString.append("<tr><td><b><center>CENTRAL DIVISION</center></b></td></tr>");
			//loop
			regionTypeAjaxString.append("<tr><td>Depot-07</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-13</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-18</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-25</td></tr>");
			regionTypeAjaxString.append("<tr><td>Depot-28</td></tr>");
			//total
			

			
			
			
			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString); // to print
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





	
	
	

