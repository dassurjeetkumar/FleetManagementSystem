	package com.trimax.its.vts.action;

	import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
	import java.text.SimpleDateFormat;
    import java.util.ArrayList;
import java.util.Calendar;
	import java.util.Date;
	import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts2.ServletActionContext;
	import org.hibernate.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
    import org.hibernate.transform.Transformers;
	
import com.trimax.its.common.Common;
    import com.trimax.its.transport.model.Schedule;
    import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.ScheduleTripwiseStatausSummaryDOA;
import com.trimax.its.vts.dao.VtsDataDAO;

	import org.hibernate.Hibernate;
	import org.hibernate.Session;
import org.hibernate.Transaction;


	public class ScheduleOperationAndRevenueSummaryAction {
		
		String str="";
		String path="";
		char ft = 15;
	
	 public String startdate;
	    public String enddate;
	    public String dutyType;
	    
	   


		public String getDutyType() {
			return dutyType;
		}


		public void setDutyType(String dutyType) {
			this.dutyType = dutyType;
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

		


		

		
	double totalAmmount=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";
	
		public String execute() {
			System.out.println("in execute");
		
			return "success";
		}

		
		
		public String getScheduleOperationRevenueData() throws SQLException
		{
			System.out.println("in get summary");
		            Connection connection = null;
		            Statement stmt1 = null;
		            ResultSet rs1 = null;
		            Transaction transaction=null;
		            Session session1=null;
					try {
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
					session1 = null;
					String queryyy;
					Common common = new Common();
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
//				

			 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			 Date startDate = format.parse(startdate);
			 Date endDateO = format.parse(enddate);
			 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			 String startDate1 = fomat2.format(startDate).toString();
			 String endDate1 = fomat2.format(endDateO).toString();
			 System.out.println("shift type"+dutyType);
			 int shiftType=Integer.parseInt(dutyType);

							String filePath = "Report/";

							String fileName = "ScheduleOperationAndRevenueReport.txt";
					   
					 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Schedule Operation And Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b> Date:-</b>"+runDateTime+"</div></div>";
				        
				        String nwkr=""    +   "                                   Schedule Operation And Revenue Report    " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sl No.</th><th>Schedule No.</th><th>Total Schedule No. Of Trips</th><th>Total Operated Trips</th><th>No. Of Days Operated</th><th>Total Operated Km(s)</th><th>Total Revenue</th><th>Total EPKM</th><th>Avg No. of Trips/Day</th><th>Avg Operated KM/Day</th><th>Avg Revenue/Day</th><th>Avg EPKM/Day</th>"+"</tr></thead><tbody>";
						

						    
						    String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
						    		+add("Sl No",3)+"|"+add("Schedule No",7)+"|"+add("Total Schedule No. Of Trips",4)+"|"+add("Total Operated Trips",7)+"|"+add("No. Of Days Operated",7)+"|"+add("Total Operated Km(s)",7)+"|"+add("Total Revenue",7)+"|"+add("Total EPKM",7)+"|"+add("Avg No. of Trips/Day",7)+"|"+add("Avg Operated KM/Day",7)+"|"+add("Avg Revenue/Day",7)+"|"+add("Avg EPKM/Day",7)+
								 " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
							
							String path = realpath + filePath + fileName;
					        str+=ft+nwkr+add(headingprint,5);
						  
                            HttpServletResponse response = ServletActionContext.getResponse();
                            int i=1;
                            double total=0.0;
                            double rev=0.0;
                           

       					regionTypeAjaxString +="<tr><td colspan='3'><center><b>Total</b></center></td><td align='center'><b>"+ total+"</td></tr>" +"\n";

       				
       					
					PrintWriter out;
				
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					}catch(Exception e)	{
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
		
		
	
		public String getShiftTypeData() {

			System.out.println("in shift");
			HttpServletRequest req = ServletActionContext.getRequest();
			ScheduleTripwiseStatausSummaryDOA doa=new ScheduleTripwiseStatausSummaryDOA();
//			int parentId = Integer.parseInt(req.getParameter("val"));
			List<String> l1 = doa.getShiftTypeIdName();
			String regionTypeAjaxString = "";
			regionTypeAjaxString = "<option  value='-1'>--ALL--</option>";
			System.out.println("in "+regionTypeAjaxString);
			for (int i = 0; i < l1.size(); i++) {
				String scheduleArr[] = l1.get(i).toString().split("#");
				regionTypeAjaxString += "<option value=" + scheduleArr[0] + ">"
						+ scheduleArr[1] + "</option>";
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
		
		
		
		
		
			 }





	
	
	

