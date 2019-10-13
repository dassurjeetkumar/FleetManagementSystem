package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;


import com.trimax.its.common.Common;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;


public class TcktBookSaleReport {
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

		public String AjaxTicketBookSaleReport()
		{          HttpServletRequest req = ServletActionContext.getRequest();
					Session session1 = null;
					 Connection connection = null;
				        Statement stmt = null;
				        ResultSet rs = null;
					try {
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				  	Transaction transaction  = null;
					String queryyy;
					Common common = new Common();
					String date1=common.getDateFromPicker(startdate);
					//String date2=common.getDateFromPicker(enddate);
					
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					//String sql="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
					String sql2="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
//					String depotdb=common.getDBResultStr(session1, sql1, "depotname");
					Query qry=session1.createSQLQuery(sql2).
							addScalar("depotname")
							.addScalar("central_ip",Hibernate.STRING)
							.addScalar("central_uname")
							.addScalar("central_pwd");
					
					qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = qry.list();	
						String depotdb="";
						String depotIp="";
						String user="";
						String password="";
						for (int j = 0; j < aliasToValueMapList.size(); j++) {
							Map<String, Object> aliasValue = aliasToValueMapList.get(j);
							 depotdb=aliasValue.get("depotname").toString();
							 depotIp=aliasValue.get("central_ip").toString();
							 user=aliasValue.get("central_uname").toString();
							 password=aliasValue.get("central_pwd").toString();
						}
					 Class.forName("com.mysql.jdbc.Driver");
					 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
					 System.out.println("connection........."+connection);
					 stmt = connection.createStatement();
					
					String sql1 = "SELECT min(opening_number) as onum,max(closing_number) as clonum,ticket_type_manual_name," +
							"denomination_type_manual,denomination_key,waybill_No," +
							"pass_day,tm.bag_code as bno,value FROM waybill_cwa_block_master cwa2 " +
							"INNER JOIN Waybill_cwa_receipt_details cwa ON cwa.waybill_cwa_block_master_id = cwa2.waybill_cwa_block_master_id " +
							"INNER JOIN Waybill_Details wd ON wd.param2= cwa.waybill_cwa_block_master_id " +
							"INNER JOIN ticketbag_master tm ON tm.ticketbag_id = cwa2.ticketbag_id " +
							"INNER JOIN denomination_type_manual dtm ON dtm.denomination_type_manual_id = cwa.denomination_type_manual_id " +
							"INNER JOIN ticket_type_manual type ON type.ticket_type_manual_id = cwa.ticket_type_manual_id " +
							"WHERE date(wd.Ticket_Audited_date) BETWEEN  ('"+date1+" 00:00:00') AND ('"+date1+" 23:59:59')  " +
							"and block_status!= 'OPEN' and cwa.closing_number!=0  group by  ticket_bag_det_id " +
							"ORDER BY tm.bag_code asc, cast(dtm.denomination_type_manual as unsigned ),cwa.denomination_key";
					  rs = stmt.executeQuery(sql1);
//				         System.out.print(sql1);
					String filePath = "Ticketing/";

					String fileName = "TicketBookSaleReport.txt";
					  
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Ticket Book Sale Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        String nwkr=""    +   "                                   Ticket Book Sale Report   " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Bag No.</th><th>Cw No.</th><th>Dnmn</th><th>Denomination Key</th><th>Start No.</th><th>End No.</th><th>No.of Tickets</th><th>Revenue</th>" +
								"                                                      "+"</tr></thead><tbody>";
						

						String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
								+add("SNo",3)+"|"+add("Bag No.",10)+ "|"+add("Cw No.",25)+ "|"+add("Dnmn",7)+"|"+add("Series",14)+"|"+add("Start No",10)+"|"+add("End No",11)+"|"+add("No.of Tickets",7)+"|"+add("Revenue",10)+"|"
							 +" "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
						
							
						 
						    String path = realpath + filePath + fileName;
					        str+=ft+nwkr+add(headingprint,5);
						int i=1;
					        while (rs.next()) {
								
								regionTypeAjaxString +="<tr>";
								regionTypeAjaxString +="<td>"+i+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("bno")+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("waybill_No")+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("denomination_type_manual")+"</td>";
								regionTypeAjaxString +="<td>"+rs.getString("denomination_key")+"</td>";
							    regionTypeAjaxString +="<td>"+rs.getString("onum")+"</td>"; 
							    regionTypeAjaxString +="<td>"+rs.getString("clonum")+"</td>";
							    int notickets=Integer.parseInt(rs.getString("clonum"))-Integer.parseInt(rs.getString("onum"))+1;
								regionTypeAjaxString +="<td>"+notickets+"</td>";
							    regionTypeAjaxString +="<td>"+rs.getString("value")+"</td>"; 
							    regionTypeAjaxString +="</tr>";
							    i++;
						 }
							  regionTypeAjaxString += "</table></div> </div>  ";
	   
						    		                    
						    
                             str+="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ____ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
						   ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
					
					
						
						FileOutputStream FOS = new FileOutputStream(path);
						PrintWriter PW = new PrintWriter(FOS);
						
					String p=str;

					PW.println(p);
					PW.close();
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

