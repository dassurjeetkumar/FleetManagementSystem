package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;

public class ETMDeviceOnline {
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
		int totaletmcount=0;

		String regionTypeAjaxString = "";
	
	


	public String execute() throws Exception {
		//this.setDepotlist(getDepotName());
		return "success";
	}


	
	@SuppressWarnings("finally")
public String etmDeviceOnline()
  {

		Session session1 = null;
		 Connection connection = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	try {
		  
		 Date  ss1=new Date();
	      Common common = new Common();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
//		String date1=common.getDateFromPicker(date);
		 String date1=common.getDateFromPicker(startdate);
		
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		// Class.forName("com.mysql.jdbc.Driver");
		// connection = DriverManager.getConnection("jdbc:mysql://10.30.1.157:3306/bmtcSLA?zeroDateTimeBehavior=convertToNull&autoReconnect=true","itishree","itishreeN");
		 //connection = DriverManager.getConnection("jdbc:mysql://10.30.1.163:43306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true","ramaD","ramaD@111");
	      
		// stmt = connection.createStatement();
		    
         String voucherQuery = "";
         
//         voucherQuery = "SELECT depot_id,oc.org_name division,SUM(waybills_total_count) total,SUM(waybills_performed_count)as performed " +
//         		"FROM Etim_Availibility_Live INNER JOIN org_chart oc ON oc.org_chart_id = depot_id " +
//         		"where duty_date ='"+date1+"' group by oc.org_name";
         voucherQuery= "SELECT generated_date, schedule_no, etm_device_id, oc.org_name org_name" +
         		" FROM `etm_daily_device_data` etm INNER JOIN org_chart oc ON oc.org_chart_id = depot_id " +
         		"WHERE  generated_date = '"+date1+"'";

         Query query = session1.createSQLQuery(voucherQuery).addScalar("generated_date").addScalar("schedule_no").addScalar("etm_device_id").addScalar("org_name");
    	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    		List<Map<String, Object>> aliasToValueMapList = query.list();
    					String realpath = ServletActionContext.getRequest()
    							.getRealPath("/");
		    
					
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br> ETM Online Device Report</br>Date:- "+startdate+"</h4></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        
				        
				        
//				        String nwkr="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _\n\n"
//				          +	  "                                     "+""+"                                                                          \n"
//			      		  +   "                                            "+""+"                                                                                  \n" 
//			      		  +   "                                  No of trips with '0' revenue     " +
//			      		 "  \n                                 Date:- "+date+"   \n"                                           
//			      		  +   "                                                               Run Date:-"+runDateTime+"               \n";
//	      
//				       
//				 
				        
				        
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' " +
				        		"style='width:50%;border-collapse: collapse;'></thead>";
				        regionTypeAjaxString +="<tr><td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Depot No</b></td><td  align='center' width='10%'><b>ETM No</b></td><td  align='center' width='10%'><b>Schedule No</b></td></tr>";

						
//					    
				        if(aliasToValueMapList.size()==0){
							regionTypeAjaxString +="<tr><td colspan='5'><center><b>No Records Found</b></center></td></tr>";
						}
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							int j=i+1;
							
							Map<String, Object> list = aliasToValueMapList.get(i);
							
								
						
								regionTypeAjaxString +="<tr>";
						
							regionTypeAjaxString +="<td>"+j+"</td>";
							regionTypeAjaxString +="<td>"+list.get("org_name").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("etm_device_id").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("schedule_no").toString()+"</td>";
						
							regionTypeAjaxString +="</tr>";
							}
						  //regionTypeAjaxString +="<tr><td colspan='2'><center><b>Total</b></center></td><td align='left'><b>"+ totaletmcount+"</td></tr>" +"\n";

						  regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
   
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
				
						out = response.getWriter();
						out.print(regionTypeAjaxString);
	                }catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						if(session1!=null){
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
}
