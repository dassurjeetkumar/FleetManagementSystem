package com.trimax.its.report.action;

import java.io.FileOutputStream;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;



public class DayWiseTripReportActionForITS {
	

	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	private Map<Integer, String> divisionlist;
	private String division1;
	private String depot1;
	public String startdate;
	private String schedule;

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getDivision1() {
		return division1;
	}

	public void setDivision1(String division1) {
		this.division1 = division1;
	}

	public String getDepot1() {
		return depot1;
	}

	public void setDepot1(String depot1) {
		this.depot1 = depot1;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
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
			this.setDivisionlist(getDivisionName());
			return "success";
		}

		
		@SuppressWarnings("finally")
		public String getDayWiseTripReportITS()
		{
			
				
					try {
					
					Common common = new Common();
					String date1=common.getDateFromPicker(startdate);
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				  //  String division1= divisionlist1;
				    String depot= depot1;
					
					String queryyy="";
					if(schedule.equalsIgnoreCase("0")){
						queryyy="";
					}else{
						queryyy="and s.schedule_number='"+schedule+"'";
					}
					Connection connection=null;
					Statement stmt=null;
					Session session1 = null;
					ResultSet rs=null;
					Transaction transaction  = null;
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					 String realpath = ServletActionContext.getRequest()
								.getRealPath("/");
					// String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
					 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
//						String depotdb=common.getDBResultStr(session1, sql1, "depotname");
						Query qry1=session1.createSQLQuery(sql1).
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
					
//					
					
					String sql = "SELECT wd.generated_Date generated_Date,s.schedule_number schedule_number,trip_number,st.shift_type_name shift_type_name," +
							"bs.bus_stop_name start_busstop,bs1.bus_stop_name end_busstop," +
							"start_time,end_time,gps_start_time,gps_end_time,running_time,gps_time_duration,distance,gps_km," +
							"etm_ticket_count,etm_passenger_count,etm_passenger_amount " +
							"FROM Waybill_Trip_Details wtd inner join  Waybill_Details wd on wd.waybil_Id=wtd.waybil_Id  " +
							"inner join schedule s on s.schedule_id=wtd.schedule_number " +
							//"inner join route r on r.route_id = wtd.route_number_id " +
							"inner join shift_type st on st.shift_type_id=wtd.shift_type_id " +
							"inner join bus_stop bs on bs.bus_stop_id=wtd.start_point " +
							"inner join bus_stop bs1 on bs1.bus_stop_id=wtd.end_point " +
							"where wd.generated_Date = '"+date1+"' "+queryyy+" and (wd.ETIM_Coll_Amt+Bag_Coll_Amt) >0";
					System.out.println(sql);
					 rs = stmt.executeQuery(sql);
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> </br>DayWise Trip Report</br> Date:- "+startdate+"</div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				      
						
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Date</th><th>Sch. No</th><th>Trip No</th><th>Start BusStop Name</th><th>End BusStop Name</th>" +
								"<th>Trip Start Time</th><th>Trip End Time</th><th>Act start Time</th><th>Act End Time</th><th>Sch. Trip Duration</th>" +
								"<th>Gps. Trip Duration</th><th>Schedule KM</th><th>GPS KM</th><th>No of Tickets</th><th>No Of passengar</th>" +
								"<th>Revenue</th>"+"</tr></thead><tbody>";
						
                     
					    
						 HttpServletResponse response = ServletActionContext.getResponse();
					        int i=1;
		 					 while (rs.next()) {
								regionTypeAjaxString +="<tr>";
							//	Map<String, Object> list = aliasToValueMapList.get(i);
								JSONArray ja = new JSONArray();
								//int j=i+1;
								
								regionTypeAjaxString +="<td align='right'>"+ i++ +"</td>";
								regionTypeAjaxString +="<td align='right'>"+ common.getDateToPicker(rs.getString("generated_Date").toString()) +"</td>";
								regionTypeAjaxString +="<td align='right'>"+rs.getString("schedule_number").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+rs.getString("trip_number").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+rs.getString("start_busstop").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+rs.getString("end_busstop").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+rs.getString("start_time").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("end_time").toString() +"</td>";
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("gps_start_time").toString() +"</td>";
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("gps_end_time").toString() +"</td>";
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("running_time").toString() +"</td>";
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("gps_time_duration").toString() +"</td>";
								
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("distance").toString() +"</td>";
								regionTypeAjaxString +="<td align='right'>"+rs.getString("gps_km").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+rs.getString("etm_ticket_count").toString()+"</td>";
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("etm_passenger_count").toString() +"</td>";
								regionTypeAjaxString +="<td align='right'>"+ rs.getString("etm_passenger_amount").toString() +"</td>";
								
//							
								   regionTypeAjaxString +="</tr>";
						    }
								
//
						    regionTypeAjaxString += "</tbody></table></div> </div>"; 
                             str+="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _ _ \n";
						  //  str+= ""+add("",5)+"" + add("Total", 46) + "|" +  add(String.valueOf(b2),20)+ ""+"\n";
						    str+="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _ _ \n";
							
					PrintWriter out;
				
						
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
					}

					return null;
			}
		

	
		
		public String getScheduleForTripTiming() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
		
			int parentId = Integer.parseInt(req.getParameter("val"));
//			 String dat = req.getParameter("startdate");
//			 System.out.println("dat==="+dat);
			 Common comm = new Common();
			//String dat1=comm. getDateFromPicker(dat);
	        		List<String> l1 = this.getgetScheduleId(parentId);
	        		List<String> l2 = this.getgetScheduleName(parentId);
	        		String regionTypeAjaxString = "";
	        		regionTypeAjaxString += "<option value='0'>All</option>";
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
//	        		return null;
//	        	}
			
	        		return null;
	        }
		
		
		public List getgetScheduleId(int depotID) {
			List list = new ArrayList();
			String qry = "";
			try {
				qry = "SELECT schedule_id,schedule_number  FROM  schedule where (status='ACTIVE' or status='NEW')"+
						" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and 	depot_id='"+depotID+"'";
				Query query = HibernateUtil.getSession("").createSQLQuery(qry);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				if (aliasToValueMapList.size() > 0) {

					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("schedule_number").toString());
						//list.add(rs.get("schedule_number").toString());
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
				return list;
			}

		}
		
		public List getgetScheduleName(int depotID) {
			List list = new ArrayList();
			String qry = "";
			try {
				qry = "SELECT schedule_id,schedule_number  FROM  schedule where (status='ACTIVE' or status='NEW')"+
						" and (current_Status='APPROVED' or current_Status='CASE WORKER') and deleted_Status=0 and 	depot_id='"+depotID+"'";
				Query query = HibernateUtil.getSession("").createSQLQuery(qry);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				if (aliasToValueMapList.size() > 0) {

					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						Map<String, Object> rs = aliasToValueMapList.get(i);
						list.add(rs.get("schedule_number").toString() );
					//	list.add(rs.get("form_four_id").toString());
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
				return list;
			}

		}

		public Map<Integer, String> getDivisionName() {
			Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session
					.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
			try {
				List<OrganisationChart> list = query.list();
				for (OrganisationChart org : list) {
					resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
				}
			} catch (Exception ex) {
			} finally {
				HibernateUtil.closeSession();
			}
			return resultMap;
		}
		
	
}
