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

public class ETMStatusReport {
	/*String path="";
	char ft = 15;
	String str="";

	String c=" ";*/
	 public String startdate;
	    public String enddate;
	

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
	 private Map<Integer, String> divisionlist;
		public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
		 private String divisionlist1;
		
		public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}


		private Map<Integer, String> depotlist;
		public Map<Integer, String> getDepotlist() {
			return depotlist;
		}


		public void setDepotlist(Map<Integer, String> depotlist) {
			this.depotlist = depotlist;
		}


		public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}


		private String depotlist1;

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		public String AjaxETMStatusReport()
		{  HttpServletRequest req = ServletActionContext.getRequest();
		
		Session session1 = null;
		 Connection connection = null;
	        Statement stmt = null;
	        Statement stmt1 = null;
	        ResultSet rs = null;
	        ResultSet rs1 = null;
					try {
						Date  ss1=new Date();
						SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
					    String runDateTime = sdf.format(ss1);
					  	Transaction transaction  = null;
						String queryyy;
						Common common = new Common();
						String date1=common.getDateFromPicker(startdate);
						String date2=common.getDateFromPicker(enddate);
						
						session1 = HibernateUtil.getSession("hibernate.cfg.xml");
						//String sql="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
						String sql="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
//						String depotdb=common.getDBResultStr(session1, sql1, "depotname");
						Query qry1=session1.createSQLQuery(sql).
								addScalar("depotname")
								.addScalar("central_ip",Hibernate.STRING)
								.addScalar("central_uname")
								.addScalar("central_pwd");
						
						qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
							List<Map<String, Object>> aliasToValueMapList = qry1.list();	
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
						 stmt1 = connection.createStatement();
						
					String sql1 = "SELECT COUNT(*) as coun FROM device WHERE device_type_id = '2' AND status = 'active' AND deleted_status = '0'";
					 rs = stmt.executeQuery(sql1);
					String sql2 = "SELECT sum(count) count,sum(count1) count1,sum(count2) count2,sum(count3) count3,generated_Date FROM " +
							"(SELECT count(*) count,0 count1,0 count2,0 count3," +
							"DATE_FORMAT(createdDate,'%Y-%m-%d') generated_Date FROM waybill_pending_collection " +
							"WHERE status NOT IN ('CLOSED') AND createdDate " +
							"between '"+date1+" 00:00:00' and '"+date2+" 23:59:59' group by " +
						    "DATE_FORMAT(createdDate,'%Y-%m-%d') union all SELECT 0 count,count(*) count1,0 count2," +
						    "0 count3, generated_Date FROM Waybill_Details WHERE Status != 'INACTIVE' AND " +
						    "generated_Date between '"+date1+"' and '"+date2+"' and ETM_No!='' group by generated_Date " +
						    "union all SELECT 0 count,0 count1,count(*) count2,0 count3,generated_Date FROM Waybill_Details " +
						    "WHERE Status Not in ('INACTIVE','NEW') AND generated_Date between '"+date1+"' and '"+date2+"' " +
						    "and ETM_No!='' group by generated_Date union all SELECT 0 count,0 count1,0 count2," +
						    "count(*) count3,generated_Date FROM Waybill_Details WHERE Status in ('AUDITED','CLOSED') AND " +
						    "generated_Date between '"+date1+"' and '"+date2+"' and ETM_No!='' group by generated_Date)a " +
						    		"group by generated_Date";
					 rs1 = stmt1.executeQuery(sql2);
				
					  
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>ETM Status Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        				
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>Sr No.</th><th>Date</th><th>Total ETMs</th><th>Working ETMs</th><th>Not Working ETMs</th><th>Uploaded ETMs</th><th>Departured ETMs</th><th>Audited ETMs</th><th>To be Audited ETMs</th>" +
								"                                                      "+"</tr></thead><tbody>";
						 
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					String tdevice="0";
					 while (rs.next()) {
						 tdevice=rs.getString("coun");
					 }
					 
					 int i=1;
					 while (rs1.next()) {
						 regionTypeAjaxString +="<tr>";
						regionTypeAjaxString +="<td>"+i+"</td>";
							regionTypeAjaxString +="<td>"+rs1.getString("generated_Date")+"</td>";
							regionTypeAjaxString +="<td>"+tdevice+"</td>";
							int worketm=Integer.parseInt(tdevice)-Integer.parseInt(rs1.getString("count"));
							regionTypeAjaxString +="<td>"+worketm+"</td>";
							regionTypeAjaxString +="<td>"+rs1.getString("count")+"</td>";
						    regionTypeAjaxString +="<td>"+rs1.getString("count1")+"</td>"; 
						    regionTypeAjaxString +="<td>"+rs1.getString("count2")+"</td>";
						    regionTypeAjaxString +="<td>"+rs1.getString("count3")+"</td>"; 
						    int notickets=Integer.parseInt(rs1.getString("count2"))-Integer.parseInt(rs1.getString("count3"));
							regionTypeAjaxString +="<td>"+notickets+"</td>";
						    
						    regionTypeAjaxString +="</tr>";
						    i++;
					 }
						  regionTypeAjaxString += "</table></div> </div>  ";

					
						// connection.close();
				            if (rs != null) {
				                rs.close();
				            }
				            
				            if (stmt != null) {
				                stmt.close();
				            }
				            if (rs1 != null) {
				                rs.close();
				            }
				            
				            if (stmt1 != null) {
				                stmt.close();
				            }

				            if (connection != null) {
				                connection.close();
				            }
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
					}

					return null;
			}
		
			 }



