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


	public class ScheduleTripwiseStatusSummaryReport {
		
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

	String regionTypeAjaxString = "";
	
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

		
		public String getScheduleTripwiseSummaryData() throws SQLException
		{
		            Session session1=null;
					try {
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
					Common common = new Common();
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					

			 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			 Date startDate = format.parse(startdate);
			 Date endDateO = format.parse(enddate);
			 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			 String startDate1 = fomat2.format(startDate).toString();
			 String endDate1 = fomat2.format(endDateO).toString();
			 System.out.println("shift type= "+dutyType);
			 int shiftType=Integer.parseInt(dutyType);
			 System.out.println("depot= "+depotlist1);
			 System.out.println("div = "+divisionlist1);
			 
							String filePath = "Report/";

							String fileName = "ScheduleTripWiseStatusSummaryReport.txt";
					   
					 regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Schedule Tripwise Status Summary Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b> Date:-</b>"+runDateTime+"</div></div>";
				        
				        String nwkr=""    +   "                                   Schedule Tripwise Status Summary Report    " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Schedule No.</th><th>Schedule Trips</th><th>Schedule OverTime</th><th>Fully Operated Trips</th><th>Partially Operated Trips</th><th>Difference</th><th>Total Scheduled KM</th><th>Total Operated GPS KM</th>"+"</tr></thead><tbody>";
						

						    
						    String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
						    		+add("Generated Date",3)+"|"+add("Trip No",3)+"|"+add("Shift Name",7)+"|"+add("Revenue",7)+
								 " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
							
							String path = realpath + filePath + fileName;
					        str+=ft+nwkr+add(headingprint,5);
						  
                            HttpServletResponse response = ServletActionContext.getResponse();
                            int i=1;
                         

       				
       					
					PrintWriter out;
				
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
					}finally{
						// connection.close();

			            if(session1 !=null){
			            	session1.close();
			            }
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
		
		
	
	
		public String getPerticularDepot() {
			// get Depot List..
			System.out.println("in all selection");
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("val"));
			String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	        System.out.println("parent"+parentId+"----"+orgchartid+"-------"+orgtypeid);
	        if(orgtypeid.equals("2")){
//	        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
	        		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
	        		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
	        		String regionTypeAjaxString = "";
	    			regionTypeAjaxString = "<option  value='-1'>------ALL------</option>";
	    			System.out.println("hereeeeee-----"+regionTypeAjaxString);
	        		for (int i = 0; i < l1.size(); i++) {
	        			regionTypeAjaxString += "<option value='" + l1.get(i).toString()
	        					+ "'>" + l2.get(i).toString() + "</option>";
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
//	        		return null;
//	        	}
			
	        }

	        else if(orgtypeid.equals("1") && orgchartid.equals("1")){
	        	List<String> l1 = dao.getDepotId(parentId);
	    		List<String> l2 = dao.getDepotName(parentId);
	    		String regionTypeAjaxString = "";
    			regionTypeAjaxString += "<option  value=0>------ALL------</option>";
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
    			regionTypeAjaxString += "<option  value=0>------ALL------</option>";
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
		
		
		
		public String getShiftTypeData() {
System.out.println("heree");
			HttpServletRequest req = ServletActionContext.getRequest();
			ScheduleTripwiseStatausSummaryDOA doa=new ScheduleTripwiseStatausSummaryDOA();
			int parentId = Integer.parseInt(req.getParameter("val"));
			List<String> l1 = doa.getShiftTypeIdName();
			String regionTypeAjaxString = "";
			regionTypeAjaxString = "<option  value='ALL'>--ALL--</option>";
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





	
	
	

