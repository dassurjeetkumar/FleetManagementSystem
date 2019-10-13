package com.trimax.its.customer.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.customer.dao.CustomerFeedBackDao;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.util.HibernateUtil;

public class CustomerSummaryAction extends ActionSupport{
	

	 String regionTypeAjaxString = "";
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

	@SkipValidation
		public String execute() {
			System.out.println("in execute");
			return "success";
		}
		
	@SkipValidation
	 public String getCustomerSummaryview(){
				 
			try {
				Connection connection=null;
				Statement stmt=null;
				Session session1 = null;
				ResultSet rs=null;
				Transaction transaction  = null;
			    Common common = new Common();
				HttpServletRequest req=ServletActionContext.getRequest();
				String date1=common.getDateFromPicker(startdate);
				 String enddate = req.getParameter("enddate");
				String date2=common.getDateFromPicker(enddate);
				Date  ss1=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			    String runDateTime = sdf.format(ss1);
		
				double totalamount=0;
				String sql="";
				
				sql="SELECT SQL_CALC_FOUND_ROWS `depot_name`, COUNT(`depot_name`) count " +
						"FROM `customer_feedback` " +
						"WHERE `travel_datetime` between '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59' GROUP BY `depot_name`" ;	
				
					
				
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				transaction = session1.beginTransaction();
				Query query = session1.createSQLQuery(sql).addScalar("depot_name").addScalar("count");
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();			
			

			regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1' style='width:50%;border-collapse: collapse;'>";
			regionTypeAjaxString +="<thead><tr><th >S.No</th><th>Depot</th><th >Overall response recvd</th>"+"</tr>" +
//					"<tr><th>responses recvd</th></tr>" +
					"</thead><tbody>";
			
		

	         for (int i = 0; i < aliasToValueMapList.size(); i++) {
					int j=i+1;
					regionTypeAjaxString +="<tr>";
					Map<String, Object> list = aliasToValueMapList.get(i);
					regionTypeAjaxString +="<td>"+j+"</td>";
					regionTypeAjaxString +="<td>"+list.get("depot_name").toString()+"</td>";
					regionTypeAjaxString +="<td>"+list.get("count").toString()+"</td>";
					
					regionTypeAjaxString +="</tr>";
				}
				
				    regionTypeAjaxString += "</tbody></table></div>"; 
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
		
			summaryOfAllstuff(date1,date2);
			summaryOfCrewRating(date1,date2);
				out = response.getWriter();
				out.print(regionTypeAjaxString);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	
			return null;
			}
	
	
	
	public String summaryOfAllstuff(String date1, String date2)
	{
		
		
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
	
		Session session1 = null;
		Transaction transaction  = null;
	
		String sql1="select sum(CASE WHEN floor_clean='Yes'  THEN 1 ELSE 0 END) flooryes," +
				"sum(CASE WHEN seats_clean='Yes'  THEN 1 ELSE 0 END) seatyes," +
				"sum(CASE WHEN windows_clean='Yes'  THEN 1 ELSE 0 END) windowyes," +
				"sum(CASE WHEN doors_clean='Yes'  THEN 1 ELSE 0 END) dooryes," +
				"sum(CASE WHEN lighting_inside='Yes'  THEN 1 ELSE 0 END) lightyes," +
				"sum(CASE WHEN seats_damage='No'  THEN 1 ELSE 0 END) seatdamageyes," +
				"sum(CASE WHEN AC_working='Yes'  THEN 1 ELSE 0 END) acyes," +
				"sum(CASE WHEN crew_behavior='Yes'  THEN 1 ELSE 0 END) crewbehavioryes," +
				"sum(CASE WHEN crew_helpful='Yes'  THEN 1 ELSE 0 END) crewhelpfulyes," +
				"sum(CASE WHEN crew_response='Yes'  THEN 1 ELSE 0 END) crewresponseyes," +
				
				"sum(CASE WHEN seats_clean='No'  THEN 1 ELSE 0 END) floorno," +
				"sum(CASE WHEN floor_clean='No'  THEN 1 ELSE 0 END) seatno," +
				"sum(CASE WHEN windows_clean='No'  THEN 1 ELSE 0 END) windowno," +
				"sum(CASE WHEN doors_clean='No'  THEN 1 ELSE 0 END) doorno," +
				"sum(CASE WHEN lighting_inside='No'  THEN 1 ELSE 0 END) lightno," +
				"sum(CASE WHEN seats_damage='Yes'  THEN 1 ELSE 0 END) seatdamageno," +
				"sum(CASE WHEN AC_working='No'  THEN 1 ELSE 0 END) acno," +
				"sum(CASE WHEN crew_behavior='No'  THEN 1 ELSE 0 END) crewbehaviorno," +
				"sum(CASE WHEN crew_helpful='No'  THEN 1 ELSE 0 END) crewhelpfulno," +
				"sum(CASE WHEN crew_response='No'  THEN 1 ELSE 0 END) crewresponseno," +
				
				"sum(CASE WHEN floor_clean is null  THEN 1 ELSE 0 END) floorna," +
				"sum(CASE WHEN seats_clean is null  THEN 1 ELSE 0 END) seatna," +
				"sum(CASE WHEN windows_clean is null  THEN 1 ELSE 0 END) windowna," +
				"sum(CASE WHEN doors_clean is null  THEN 1 ELSE 0 END) doorna," +
				"sum(CASE WHEN lighting_inside is null  THEN 1 ELSE 0 END) lightna," +
				"sum(CASE WHEN seats_damage is null  THEN 1 ELSE 0 END) seatdamagena," +
				"sum(CASE WHEN AC_working is null  THEN 1 ELSE 0 END) acna," +
				"sum(CASE WHEN crew_behavior is null  THEN 1 ELSE 0 END) crewbehaviorna," +
				"sum(CASE WHEN crew_helpful is null  THEN 1 ELSE 0 END) crewhelpfulna," +
				"sum(CASE WHEN crew_response is null  THEN 1 ELSE 0 END) crewresponsena " +
				"from customer_feedback " +
				"WHERE `travel_datetime` between '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59'" ;
			
			
		//System.out.println("query"+sql);	
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 transaction = session1.beginTransaction();
		 Query query1 = session1.createSQLQuery(sql1).addScalar("flooryes").addScalar("seatyes").addScalar("windowyes")
					.addScalar("dooryes").addScalar("lightyes").addScalar("seatdamageyes").addScalar("acyes").addScalar("crewbehavioryes").addScalar("crewhelpfulyes")
					.addScalar("crewresponseyes").addScalar("floorno").addScalar("seatno").addScalar("windowno").addScalar("doorno").addScalar("lightno")
					.addScalar("seatdamageno").addScalar("acno").addScalar("crewbehaviorno").addScalar("crewhelpfulno").addScalar("crewresponseno").addScalar("floorna")
					.addScalar("seatna").addScalar("windowna").addScalar("doorna").addScalar("lightna").addScalar("seatdamagena").addScalar("acna")
					.addScalar("crewbehaviorna").addScalar("crewhelpfulna").addScalar("crewresponsena");
			query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList1 = query1.list();	
		
			int flooryes=0;
			int seatyes=0;
			int windowyes=0;
			int dooryes=0;
			int lightyes=0;
			int seatdamageyes=0;
			int acyes=0;
			int crewbehavioryes=0;
			int crewhelpfulyes=0;
			int crewresponseyes=0;
			
			int floorno=0;
			int seatno=0;
			int windowno=0;
			int doorno=0;
			int lightno=0;
			int seatdamageno=0;
			int acno=0;
			int crewbehaviorno=0;
			int crewhelpfulno=0;
			int crewresponseno=0;
			
			int floorna=0;
			int seatna=0;
			int windowna=0;
			int doorna=0;
			int lightna=0;
			int seatdamagena=0;
			int acna=0;
			int crewbehaviorna=0;
			int crewhelpfulna=0;
			int crewresponsena=0;
			   for (int i = 0; i < aliasToValueMapList1.size(); i++) {
					
					Map<String, Object> list = aliasToValueMapList1.get(i);
					JSONArray ja = new JSONArray();
				
					flooryes=Integer.parseInt(list.get("flooryes").toString());
					System.out.println("flooryes==="+flooryes);
					seatyes=Integer.parseInt(list.get("seatyes").toString());
					windowyes=Integer.parseInt(list.get("windowyes").toString());
					dooryes=Integer.parseInt(list.get("dooryes").toString());
					lightyes=Integer.parseInt(list.get("lightyes").toString());
					seatdamageyes=Integer.parseInt(list.get("seatdamageyes").toString());
					acyes=Integer.parseInt(list.get("acyes").toString());
					crewbehavioryes=Integer.parseInt(list.get("crewbehavioryes").toString());
					crewhelpfulyes=Integer.parseInt(list.get("crewhelpfulyes").toString());
					crewresponseyes=Integer.parseInt(list.get("crewresponseyes").toString());
					
					
					floorno=Integer.parseInt(list.get("floorno").toString());
					seatno=Integer.parseInt(list.get("seatno").toString());
					windowno=Integer.parseInt(list.get("windowno").toString());
					doorno=Integer.parseInt(list.get("doorno").toString());
					lightno=Integer.parseInt(list.get("lightno").toString());
					seatdamageno=Integer.parseInt(list.get("seatdamageno").toString());
					acno=Integer.parseInt(list.get("acno").toString());
					crewbehaviorno=Integer.parseInt(list.get("crewbehaviorno").toString());
					crewhelpfulno=Integer.parseInt(list.get("crewhelpfulno").toString());
					crewresponseno=Integer.parseInt(list.get("crewresponseno").toString());
					
					floorna=Integer.parseInt(list.get("floorna").toString());
					seatna=Integer.parseInt(list.get("seatna").toString());
					windowna=Integer.parseInt(list.get("windowna").toString());
					doorna=Integer.parseInt(list.get("doorna").toString());
					lightna=Integer.parseInt(list.get("lightna").toString());
					seatdamagena=Integer.parseInt(list.get("seatdamagena").toString());
					acna=Integer.parseInt(list.get("acna").toString());
					crewbehaviorna=Integer.parseInt(list.get("crewbehaviorna").toString());
					crewhelpfulna=Integer.parseInt(list.get("crewhelpfulna").toString());
					crewresponsena=Integer.parseInt(list.get("crewresponsena").toString());
					
				
				}
		
			     regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'>";
				 regionTypeAjaxString +="<tr>";
				 regionTypeAjaxString +="<th><center><b>SI.No</b></center></th><th><center><b>Parameter</b></center></th><th><center><b> Response - Yes</b></center></th><th><center><b> Response - No</b></center></th><th><center><b> Response - NA</b></center></th></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>1</td><td align='left'>Cleanliness of the floor</td><td align='left'>"+flooryes+"</td><td align='left'>"+floorno+"</td><td align='left'>"+floorna+"</td></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>2</td><td align='left'>Cleanliness of seats</td><td align='left'>"+seatyes+"</td><td align='left'>"+seatno+"</td><td align='left'>"+seatna+"</td></tr>" ;
				 regionTypeAjaxString +="<tr><td align='left'>3</td><td align='left'>Cleanliness of Windows</td><td align='left'>"+windowyes+"</td><td align='left'>"+windowno+"</td><td align='left'>"+windowna+"</td></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>4</td><td align='left'>Cleanliness of Doors</td><td align='left'>"+dooryes+"</td><td align='left'>"+doorno+"</td><td align='left'>"+doorna+"</td></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>5</td><td align='left'>Lighting inside the vehicle</td><td align='left'>"+lightyes+"</td><td align='left'>"+lightno+"</td><td align='left'>"+lightna+"</td></tr>" ;
				 regionTypeAjaxString +="<tr><td align='left'>6</td><td align='left'>Damage to seats</td><td align='left'>"+seatdamageyes+"</td><td align='left'>"+seatdamageno+"</td><td align='left'>"+seatdamagena+"</td></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>7</td><td align='left'>AC working satisfactorily - Status</td><td align='left'>"+acyes+"</td><td align='left'>"+acno+"</td><td align='left'>"+acna+"</td></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>8</td><td align='left'>Behavour of the Conductor</td><td align='left'>"+crewbehavioryes+"</td><td align='left'>"+crewbehaviorno+"</td><td align='left'>"+crewbehaviorna+"</td></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>9</td><td align='left'>Courteous behaviour on part of crew</td><td align='left'>"+crewhelpfulyes+"</td><td align='left'>"+crewhelpfulno+"</td><td align='left'>"+crewhelpfulna+"</td></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>10</td><td align='left'>Satisfactory response from Crew</td><td align='left'>"+crewresponseyes+"</td><td align='left'>"+crewresponseno+"</td><td align='left'>"+crewresponsena+"</td></tr>";
				 		
				 regionTypeAjaxString += "</tbody></table></div>"; 

		return null;
	}
	
	
	public String summaryOfCrewRating(String date1, String date2)
	{
		
		
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		HttpServletRequest req = ServletActionContext.getRequest();
	
		Session session1 = null;
		Transaction transaction  = null;
	
		String sql1="select " +
				"sum(CASE WHEN conductor_rating=5  THEN 1 ELSE 0 END) conductorrat5, " +
				"sum(CASE WHEN conductor_rating=4  THEN 1 ELSE 0 END) conductorrat4," +
				"sum(CASE WHEN conductor_rating=3  THEN 1 ELSE 0 END) conductorrat3," +
				"sum(CASE WHEN conductor_rating=2  THEN 1 ELSE 0 END) conductorrat2," +
				"sum(CASE WHEN conductor_rating=1  THEN 1 ELSE 0 END) conductorrat1," +
				
				"sum(CASE WHEN driver_rating=5  THEN 1 ELSE 0 END) driverrat5," +
				"sum(CASE WHEN driver_rating=4  THEN 1 ELSE 0 END) driverrat4," +
				"sum(CASE WHEN driver_rating=3  THEN 1 ELSE 0 END) driverrat3," +
				"sum(CASE WHEN driver_rating=2  THEN 1 ELSE 0 END) driverrat2," +
				"sum(CASE WHEN driver_rating=1  THEN 1 ELSE 0 END) driverrat1," +
				
				"sum(CASE WHEN BMTC_rating=5  THEN 1 ELSE 0 END) bmtcrat5," +
				"sum(CASE WHEN BMTC_rating=4  THEN 1 ELSE 0 END) bmtcrat4," +
				"sum(CASE WHEN BMTC_rating=3  THEN 1 ELSE 0 END) bmtcrat3," +
				"sum(CASE WHEN BMTC_rating=2  THEN 1 ELSE 0 END) bmtcrat2," +
				"sum(CASE WHEN BMTC_rating=1  THEN 1 ELSE 0 END) bmtcrat1 " +
				"from customer_feedback" +
				" WHERE `travel_datetime` between '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59'" ;
			
			
		//System.out.println("query"+sql);	
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session1.beginTransaction();
			Query query1 = session1.createSQLQuery(sql1).addScalar("conductorrat5").addScalar("conductorrat4").addScalar("conductorrat3")
					.addScalar("conductorrat2").addScalar("conductorrat1").addScalar("driverrat5").addScalar("driverrat4").addScalar("driverrat3").addScalar("driverrat2")
					.addScalar("driverrat1").addScalar("bmtcrat5").addScalar("bmtcrat4").addScalar("bmtcrat3").addScalar("bmtcrat2").addScalar("bmtcrat1");
			query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList1 = query1.list();	
		
			int conductorrat5=0;
			int conductorrat4=0;
			int conductorrat3=0;
			int conductorrat2=0;
			int conductorrat1=0;
			int driverrat5=0;
			int driverrat4=0;
			int driverrat3=0;
			int driverrat2=0;
			int driverrat1=0;
			
			int bmtcrat5=0;
			int bmtcrat4=0;
			int bmtcrat3=0;
			int bmtcrat2=0;
			int bmtcrat1=0;
			
			   for (int i = 0; i < aliasToValueMapList1.size(); i++) {
					
					Map<String, Object> list = aliasToValueMapList1.get(i);
					JSONArray ja = new JSONArray();
				
					conductorrat5=Integer.parseInt(list.get("conductorrat5").toString());
					conductorrat4=Integer.parseInt(list.get("conductorrat4").toString());
					conductorrat3=Integer.parseInt(list.get("conductorrat3").toString());
					conductorrat2=Integer.parseInt(list.get("conductorrat2").toString());
					conductorrat1=Integer.parseInt(list.get("conductorrat1").toString());
					driverrat5=Integer.parseInt(list.get("driverrat5").toString());
					driverrat4=Integer.parseInt(list.get("driverrat4").toString());
					driverrat3=Integer.parseInt(list.get("driverrat3").toString());
					driverrat2=Integer.parseInt(list.get("driverrat2").toString());
					driverrat1=Integer.parseInt(list.get("driverrat1").toString());
					
					
					bmtcrat5=Integer.parseInt(list.get("bmtcrat5").toString());
					bmtcrat4=Integer.parseInt(list.get("bmtcrat4").toString());
					bmtcrat3=Integer.parseInt(list.get("bmtcrat3").toString());
					bmtcrat2=Integer.parseInt(list.get("bmtcrat2").toString());
					bmtcrat1=Integer.parseInt(list.get("bmtcrat1").toString());
					
				
				}
		
			     regionTypeAjaxString += "<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'>";
				 regionTypeAjaxString +="<tr>";
				 regionTypeAjaxString +="<th><center><b>SI.No</b></center></th><th><center><b>Rating for</b></center></th><th><center><b>Rating - 5</b></center></th><th><center><b>Rating - 4</b></center></th><th><center><b>Rating - 3</b></center></th>" +
				 		"<th><center><b>Rating - 2</b></center></th><th><center><b>Rating - 1</b></center></th></tr>";
				 regionTypeAjaxString +="<tr><td align='left'>1</td><td align='left'>Driver Rating</td><td align='left'>"+driverrat5+"</td><td align='left'>"+driverrat4+"</td><td align='left'>"+driverrat3+"</td>" +
					 		"<td align='left'>"+driverrat2+"</td><td align='left'>"+driverrat1+"</td></tr>" ;
				 regionTypeAjaxString +="<tr><td align='left'>2</td><td align='left'>Conductor Rating</td><td align='left'>"+conductorrat5+"</td><td align='left'>"+conductorrat4+"</td><td align='left'>"+conductorrat3+"</td>" +
				 		"<td align='left'>"+conductorrat2+"</td><td align='left'>"+conductorrat1+"</td></tr>" ;
				 regionTypeAjaxString +="<tr><td align='left'>3</td><td align='left'>BMTC Rating</td><td align='left'>"+bmtcrat5+"</td><td align='left'>"+bmtcrat4+"</td><td align='left'>"+bmtcrat3+"</td>"+
						 "<td align='left'>"+driverrat2+"</td><td align='left'>"+driverrat1+"</td></tr>" ;
				
				 regionTypeAjaxString += "</tbody></table></div>"; 

		return null;
	}

}
