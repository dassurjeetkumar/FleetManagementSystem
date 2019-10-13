package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;


import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ConductorLicenseExpiryReportActionIts {

	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	    public String enddate;
	
	    private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;
		private String depotlist1;
		private String divisionlist1;
		
	


	public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}


	public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}


	public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
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

		double totalAmmount=0;
		int subtotalValues=0;

		String regionTypeAjaxString = "";

		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		
		public String getconductorlicenseexpiry()
		{
			try {
			//CollectionReportDAO dao=new CollectionReportDAO();
				Common common = new Common();
				String date1=common.getDateFromPicker(startdate);
			    String date2=common.getDateFromPicker(enddate);
			String division1= divisionlist1;
			String depot1= depotlist1;
			
			Date  ss1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		    String runDateTime = sdf.format(ss1);
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			HttpServletRequest req = ServletActionContext.getRequest();
			Session session1 = null;
			Transaction transaction  = null;
			String Condition = "";
			if(!division1.equalsIgnoreCase("0") && !depot1.equalsIgnoreCase("0") ){
				Condition = " and DIVISION_ID='"+division1+"' and org_chart_id='"+depot1+"'";
			}else 
			if(!division1.equalsIgnoreCase("0") && depot1.equalsIgnoreCase("0")) {
				
				Condition = " and DIVISION_ID='"+division1+"'";
			}else if(division1.equalsIgnoreCase("0") && !depot1.equalsIgnoreCase("0")) {
				Condition = " and  org_chart_id='"+depot1+"'";
				
			}else{
				Condition="";
			}
			
			
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			
				
			 String sql2 = "select EMPLOYEE_NAME,IFNULL(TOKEN,'') TOKEN,EMPLOYEE_DESIGNATION,IFNULL(DRIVING_LC_NO,'') DRIVING_LC_NO,IFNULL(CONDUCTOR_LC_EXPDATE,'') CONDUCTOR_LC_EXPDATE," +
			 		"emp.status from employee emp inner join designation_type dg on dg.designation_type_id=emp.WORKING_DESIGNATION where emp.WORKING_DESIGNATION IN(2,16) and emp.status='active' " +
			 		"and CONDUCTOR_LC_EXPDATE between '"+date1+"' and '"+date2+"'"+ Condition;
			
			
			Query query = session1.createSQLQuery(sql2).addScalar("EMPLOYEE_NAME").addScalar("TOKEN").addScalar("EMPLOYEE_DESIGNATION")
					.addScalar("DRIVING_LC_NO").addScalar("CONDUCTOR_LC_EXPDATE");
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
			int Tickettotal=0;
			int Totalvalues=0;
			
//			
//			String realpath = ServletActionContext.getRequest()
//					.getRealPath("/");
//			
//		
//			String filePath = "Ticketing/";
//
//			String fileName = "ConductorExpiryReport.txt";
//			
			     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br> Conductor License Expiry Report</br></h4></div>";

		        regionTypeAjaxString +="<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>";
		       
		        
		        regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
				regionTypeAjaxString +="<thead><tr><th>Sr No</th><th>Conductor Name</th><th>Conductor Token No</th><th>Conductor License No.</th><th>Expire Date</th>" +
//						"<th>Remarks</th>" +
						""+"</tr></thead><tbody>";
		        
		        
		        
			
			        
			     for (int i = 0; i < aliasToValueMapList.size(); i++) {
				regionTypeAjaxString +="<tr>";
				Map<String, Object> list = aliasToValueMapList.get(i);
				//JSONArray ja = new JSONArray();
				int j=i+1;
				
				regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
				regionTypeAjaxString +="<td align='right'>"+ list.get("EMPLOYEE_NAME").toString() +"</td>";
				regionTypeAjaxString +="<td align='right'>"+ list.get("TOKEN").toString() +"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("DRIVING_LC_NO").toString()+"</td>";
				regionTypeAjaxString +="<td align='right'>"+list.get("CONDUCTOR_LC_EXPDATE").toString()+"</td>";
				
				
					// str+=""+add("",1)+"|"+add(String.valueOf(j),9)+"|"+add(list.get("waybill_No").toString(),20)+"|"+add(list.get("schedule_number").toString(),12)+"|"+add(list.get("device_serial_number").toString(),12)+"|" + add(list.get("TOKEN").toString(), 14) +"|" + add(list.get("amount").toString(),15)+ "|"+"\n"; 

//				   if(j%55==0){
//					   str+=f2+add(headingprint,5);
//				   }
				
				   regionTypeAjaxString +="</tr>";
			
			}
			    
//			regionTypeAjaxString +="<tr><td colspan='4'><center><b>Sub Total</b></center></td><td align='right'><b>"+ Tickettotal+"</td>"+"<td align='right'><b>"+ Totalvalues+"</td></tr>" +"\n";  
//			str+= " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _\n";
			 regionTypeAjaxString += "</tbody></table></div> </div>";  
	 
//			 str+= ""+add("",5)+"" + add("Sub Total", 58) + "|" + add(String.valueOf(Tickettotal), 12) +"|" + add(String.valueOf(Totalvalues),15)+ "|"+"\n"; 
			
//			 str+="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _\n";
			 //ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
			// System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			
			
			
				
//				FileOutputStream FOS = new FileOutputStream(path);
//				PrintWriter PW = new PrintWriter(FOS);
//				
//	            	System.out.println(str);
//			String p=str;
//			System.out.println("realpath="+path);
			//System.out.println("string..@@"+p);

//			PW.println(p);
//			PW.close();
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
