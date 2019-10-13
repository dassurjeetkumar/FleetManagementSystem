package com.trimax.its.route.action;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.model.Route;
import com.trimax.its.util.HibernateUtil;


public class RouteWiseTripAction {

		private Map<Integer, String> divisionlist;
		public String startdate;
		private String depotlist1;
		public String divisionlist1;
		private List<Route> routeList;
		private String route;
		String str = "";
		String path = "";
		char ft = 15;
		char fl = 18;
		char f2 = 12;
		int pagi = 1;
		
		

		public String getStartdate() {
			return startdate;
		}

		public void setStartdate(String startdate) {
			this.startdate = startdate;
		}

		public String getDivisionlist1() {
			return divisionlist1;
		}

		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}

		public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}

		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
		}

		public String getDepotlist1() {
			return depotlist1;
		}

		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}

		public List<Route> getRouteList() {
			return routeList;
		}

		public void setRouteList(List<Route> routeList) {
			this.routeList = routeList;
		}

		public String getRoute() {
			return route;
		}

		public void setRoute(String route) {
			this.route = route;
		}

		public String execute() throws Exception {
			return "success";
		}

		@SuppressWarnings("unchecked")
		public List<Route> getRouteListDropDown1() {

			Session session = null;
			List<Map<String, String>> aliasToValueMapList = null;
			List<Route> list1 = new ArrayList<Route>();
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String today = formatter.format(new java.util.Date());
				session = HibernateUtil.getSession("hibernate.cfg.xml");

				String queryForRouteNos = "select route_number,route_id,route_direction, CONCAT(route_number, '/', route_direction) as route_detail from route where `status` = 'active' and  effective_till ='0000-00-00 00:00:00' and  deleted_status='0' ";
				Query queryObject = session
						.createSQLQuery(queryForRouteNos)
						.addScalar("route_number", Hibernate.STRING)
						.addScalar("route_id", Hibernate.INTEGER)
						.addScalar("route_direction", Hibernate.STRING)

						.setResultTransformer(
								AliasToEntityMapResultTransformer.INSTANCE);
				aliasToValueMapList = queryObject.list();

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, String> map = aliasToValueMapList.get(i);
					Route route1 = new Route();
					route1.setRoute_number(map.get("route_number") + ":"
							+ map.get("route_direction"));
					route1.setValue(map.get("route_number"));
					// setValue
					// route1.setRoute_name(String.valueOf(map.get("route_id"))
					// +"-"+String.valueOf(map.get("start_point_id"))+"-"+String.valueOf(map.get("end_point_id")));
					list1.add(route1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				if (session != null) {
					session.close();
				}
			}
			return list1;
		}

		public String showRouteWiselistData() {
			try {
				HttpServletRequest req = ServletActionContext.getRequest();
				Date ss1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				String runDateTime = sdf.format(ss1);
				String regionTypeAjaxString = "";
				Common common = new Common();
				
				String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
				
				String routid = req.getParameter("routeId");
				
				
				String routename[]=routid.split(":");
				String route=routename[0];
				String direction=routename[1];

				Session session1 = null;
				Transaction transaction  = null;
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				transaction = session1.beginTransaction();
			
				String queryForRouteIDs = "SELECT route_id FROM `route`  WHERE `status` = 'active' AND route_number='"+route+"' AND route_direction='"+direction+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
				String count = common.getDBResultStr(session1, queryForRouteIDs, "route_id");
				String sql="";
			
/*	       sql="select *,count(*) total_trips from (select schedule_number_name,service_type_name,og.org_name org_name,bs2.bus_stop_name origin,bs3.bus_stop_name destination " +
	       		"from schedule s inner join form_four ff on s.schedule_id=ff.schedule_number_id " +
	       		"inner join schedule_details sd on sd.form_four_id=ff.form_four_id " +
	       		"inner join service_type  st on st.service_type_id=schedule_service_type " +
	       		"inner join org_chart og on og.org_chart_id=s.depot_id  " +
	       		"inner join route r on r.route_id=sd.route_number_id " +
	       		"inner join bus_stop bs2 on r.start_point_id=bs2.bus_stop_id " +
	       		"inner join bus_stop bs3 on r.end_point_id=bs3.bus_stop_id " +
	       		"where  sd.route_number_id='"+count+"' and sd.deleted_status=0 and ff.current_status='ACTIVE'   " +
	       		"and s.status='NEW' and s.current_status='CASE WORKER' and sd.deleted_status=0 and s.deleted_status=0 " +
	       		"group by sd.form_four_id,sd.shift_type_id,sd.trip_number) A group by schedule_number_name order by schedule_number_name";*/
				 sql="select *,count(*)no_trips from (select ff.schedule_number_name,service_type_name,og.org_name,"+
						"bs2.bus_stop_name origin,sd.distance,bs3.bus_stop_name destination "+
						"from schedule s inner join form_four ff on s.schedule_id=ff.schedule_number_id "+
						"inner join schedule_details sd on sd.form_four_id=ff.form_four_id inner join service_type  st on st.service_type_id=schedule_service_type "+
						"inner join org_chart og on og.org_chart_id=s.depot_id  inner join route r on r.route_id=sd.route_number_id "+
						"inner join bus_stop bs2 on r.start_point_id=bs2.bus_stop_id inner join bus_stop bs3 on r.end_point_id=bs3.bus_stop_id "+
						"where  sd.route_number_id=r.route_id and sd.deleted_status=0 and ff.current_status='ACTIVE'   and s.status='NEW' and r.route_id='"+count+"' "+
						"and s.current_status='CASE WORKER' and sd.deleted_status=0 and s.deleted_status=0 and ff.deleted_status=0 "+
						"and sd.is_dread_trip=0 "+
						"group by sd.form_four_id,sd.shift_type_id,sd.trip_number) A group by schedule_number_name order by schedule_number_name";

	Query query = session1.createSQLQuery(sql).addScalar("schedule_number_name").addScalar("service_type_name")
	 		.addScalar("org_name").addScalar("origin").addScalar("destination").addScalar("no_trips");
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();			
//				String realpath = ServletActionContext.getRequest()
//						.getRealPath("/");
				//String count="";
			System.out.println("query==="+aliasToValueMapList.size());	
						
			
				String filePath = "Ticketing/";

				String fileName = "StageWiseTicketCount.txt";
				
	          
				     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4> Route Wise Trip Report</div>";
				  
				    


			        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
			        
			        
			        
			        String nwkr="";
			         
		        
			       
			        
//			        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
//					regionTypeAjaxString +="<td align='center' width='10%'><b>Date</b></td><td align='center' width='20%'><b>Schedule No.</b></td><td align='center' width='20%'><b>Token_No</b></td>";
//					regionTypeAjaxString +="<td align='center' width='10%'><b>Name</b></td><td align='center' width='10%'><b>Revenue</b></td></tr>";
					
					
					
					regionTypeAjaxString += "<div class='component'><div id='printid'><table class='overflow-y' border='1'>";
					regionTypeAjaxString +="<thead><tr><th>S.No</th><th>Form Four No</th><th>Service Type</th><th>Depot</th><th>Origin</th><th>Destination</th><th>Total Trips</th>"+"</tr></thead><tbody>";
					
					
					String headingprint ="";
					 		
					 		
					
						
					 
					    String path = realpath + filePath + fileName;
				        str+=ft+nwkr+add(headingprint,5);
	         if(aliasToValueMapList.size()>0){
	             for (int i = 0; i < aliasToValueMapList.size(); i++) {
						int j=i+1;
						regionTypeAjaxString +="<tr>";
						Map<String, Object> list = aliasToValueMapList.get(i);
						regionTypeAjaxString +="<td>"+j+"</td>";
						regionTypeAjaxString +="<td>"+list.get("schedule_number_name").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("service_type_name").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("org_name").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("origin").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("destination").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("no_trips").toString()+"</td>";
						
						
						regionTypeAjaxString +="</tr>";
					}
	         }else{
	        	 regionTypeAjaxString +="<tr><td colspan='6' align='center'><b>No Data Avalaible</b></td></tr>";
	         }
	             
					    ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
						 
					    regionTypeAjaxString += "</tbody></table></div></div>"; 
					 //  System.out.println("regionTypeAjaxString==="+regionTypeAjaxString);
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out;
				FileOutputStream FOS = new FileOutputStream(path);
				PrintWriter PW = new PrintWriter(FOS);
				
			String p=str;
			//System.out.println("string..@@"+p);
			out = response.getWriter();
			out.print(regionTypeAjaxString);
			PW.println(p);
			PW.close();
				
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

			// String sb1 =
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
		
		

		public String getRouteListData() {
			
			
			try {
				HttpServletRequest req = ServletActionContext.getRequest();
				//StageWiseTicketConsumptionDOA doa = new StageWiseTicketConsumptionDOA();
				String routename=req.getParameter("routename");
			List<Route> l1 = getRouteListDropDown1(routename);
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(new JSONArray(l1));
			} catch (IOException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		



//		public String getScheduleFormFourID() {
//			System.out.println("heree scedule");
//			try {
//			HttpServletRequest req = ServletActionContext.getRequest();
//			String routename = req.getParameter("route");
//		
//			System.out.println("route id " + routename);
//			//StageWiseTicketConsumptionDOA doa = new StageWiseTicketConsumptionDOA();
//			
//			int routeId=getRouteID(routename);
//			System.out.println("iddddd"+routeId);
//			
//			List<String> l1 = getScheduleNameFormFourId(routeId);
//			String regionTypeAjaxString1 = "";
//			regionTypeAjaxString1 = "<option  value='0'>--ALL--</option>";
//			for (int i = 0; i < l1.size(); i++) {
//				String routeArr[] = l1.get(i).toString().split("#");
//				regionTypeAjaxString1 += "<option value=" + routeArr[0] + ">"
//						+ routeArr[1] + "</option>";
//
//			}
//			HttpServletResponse response = ServletActionContext.getResponse();
//			PrintWriter out;
//			
//				out = response.getWriter();
//				out.print(regionTypeAjaxString1);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			return null;
//		}
		
		
		
		@SuppressWarnings("unchecked")
		public List<Route> getRouteListDropDown1(String routeno){

			
			Session session = null;
			List<Map<String,String>> aliasToValueMapList = null;
			List<Route> list1 = new ArrayList<Route>();
			try{
				SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd" );
				String today = formatter.format( new java.util.Date() );
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				
				String queryForRouteNos = "SELECT route_id,route_number,route_direction FROM `route` WHERE `status` = 'active' AND route_number like'%"+routeno+"%' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'";
				Query queryObject = session.createSQLQuery(queryForRouteNos).addScalar("route_number",Hibernate.STRING)
						.addScalar("route_id",Hibernate.INTEGER).addScalar("route_direction",Hibernate.STRING)
						.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				aliasToValueMapList = queryObject.list();
				
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String,String> map = aliasToValueMapList.get(i);
					Route route1 = new Route();
					route1.setRoute_number(map.get("route_number") +":"+ map.get("route_direction"));
					route1.setValue(map.get("route_number"));
					//setValue
//					route1.setRoute_name(String.valueOf(map.get("route_id")) +"-"+String.valueOf(map.get("start_point_id"))+"-"+String.valueOf(map.get("end_point_id")));
					list1.add(route1);
				}
				
			} catch(Exception e){
				e.printStackTrace();
			}
			
			finally{
	            if(session!=null){
	                session.close();
	            }
			}
			
			return list1;
			
		}
		
	public int getRouteID(String routeno) {
			
			Session session = null;
			int id=0;
			
			try {
				session = HibernateUtil.getSession("hibernate.cfg.xml");
				String routename[]=routeno.split(":");
				String route=routename[0];
				String direction=routename[1];
				
				Query qry=session.createSQLQuery("SELECT route_id FROM `route`  WHERE `status` = 'active' AND route_number='"+route+"' AND route_direction='"+direction+"' AND `deleted_status` = '0' AND `effective_till` = '0000-00-00 00:00:00'");
				id=(Integer)qry.uniqueResult();
						
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			     if(session!=null){
			    	 session.close();
			     }
			}
			return id;
		}
		

	

	}


