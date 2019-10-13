package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.opensymphony.xwork2.ActionSupport;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ProgressiveKMReport extends ActionSupport{
	private Map<Integer, String> divisionlist;
	private Map<Integer, String> depotlist;
	 public String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	private String depotlist1;
		private String divisionlist1;

	
	
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
	String regionTypeAjaxString = "";
	@Override
	public String execute() throws Exception {
		this.setDivisionlist(getDivisionName());
		
		return "success";
	}

	
	
	@SuppressWarnings("finally")
	public String ProgressiveKMReport()
	{
		
			
				try {
				
					
					
					Connection connection=null;
					Statement stmt=null;
					Session session1 = null;
					ResultSet rs=null;
					Transaction transaction  = null;
					Common common = new Common();
					HttpServletRequest req=ServletActionContext.getRequest();
					//String date1=common.getDateFromPicker(startdate);
					
				    String division1= divisionlist1;
				    String depot1= depotlist1;
				    Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
					
					DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
					
					 session1 = null;
					 transaction  = null;
					String vhid= req.getParameter("vehiclelist");
					//String input = "2009-09-30";
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					Date myDate = dateFormat.parse(startdate);
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(myDate);
					cal1.add(Calendar.MONTH, -1);
					Date previousDate = cal1.getTime();
					String formattedDate = dateFormat.format(previousDate);
					SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM");
					Date formattedDate1 = dateFormat.parse(formattedDate);
					String currentdate = dateFormat1.format(myDate);
					String previousdate = dateFormat1.format(formattedDate1);
				
			   // String schdid = req.getParameter("scheduleid");
			    String depot=req.getParameter("depotlist1");
			  
			
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				 transaction = session1.beginTransaction();
				 String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
				//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
			 //String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depot+"' AND division_id = '"+divisionlist1+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
				 String sql="";
			
				 
				  sql="select license_number," +
					 		"sum(case when dat like '%"+formattedDate+"%' then km Else 0 end) cumltvkm," +
					 		"sum(case when dat='"+currentdate+"-01' then (km) Else 0 end) '01'," +
					 		"sum(case when dat='"+currentdate+"-02' then (km) Else 0 end) '02'," +
					 		"sum(case when dat='"+currentdate+"-03' then (km) Else 0 end) '03'," +
					 		"sum(case when dat='"+currentdate+"-04' then (km) Else 0 end) '04'," +
					 		"sum(case when dat='"+currentdate+"-05' then (km) Else 0 end) '05'," +
					 		"sum(case when dat='"+currentdate+"-06' then (km) Else 0 end) '06'," +
					 		"sum(case when dat='"+currentdate+"-07' then (km) Else 0 end) '07'," +
					 		"sum(case when dat='"+currentdate+"-08' then (km) Else 0 end) '08'," +
					 		"sum(case when dat='"+currentdate+"-09' then (km) Else 0 end) '09'," +
					 		"sum(case when dat='"+currentdate+"-10' then (km) Else 0 end) '10'," +
					 		"sum(case when dat='"+currentdate+"-11' then (km) Else 0 end) '11'," +
					 		"sum(case when dat='"+currentdate+"-12' then (km) Else 0 end) '12'," +
					 		"sum(case when dat='"+currentdate+"-13' then (km) Else 0 end) '13'," +
					 		"sum(case when dat='"+currentdate+"-14' then (km) Else 0 end) '14'," +
					 		"sum(case when dat='"+currentdate+"-15' then (km) Else 0 end) '15'," +
					 		"sum(case when dat='"+currentdate+"-16' then (km) Else 0 end) '16'," +
					 		"sum(case when dat='"+currentdate+"-17' then (km) Else 0 end) '17'," +
					 		"sum(case when dat='"+currentdate+"-18' then (km) Else 0 end) '18'," +
					 		"sum(case when dat='"+currentdate+"-19' then (km) Else 0 end) '19'," +
					 		"sum(case when dat='"+currentdate+"-20' then (km) Else 0 end) '20'," +
					 		"sum(case when dat='"+currentdate+"-21' then (km) Else 0 end) '21'," +
					 		"sum(case when dat='"+currentdate+"-22' then (km) Else 0 end) '22'," +
					 		"sum(case when dat='"+currentdate+"-23' then (km) Else 0 end) '23'," +
					 		"sum(case when dat='"+currentdate+"-24' then (km) Else 0 end) '24'," +
					 		"sum(case when dat='"+currentdate+"-25' then (km) Else 0 end) '25'," +
					 		"sum(case when dat='"+currentdate+"-26' then (km) Else 0 end) '26'," +
					 		"sum(case when dat='"+currentdate+"-27' then (km) Else 0 end) '27'," +
					 		"sum(case when dat='"+currentdate+"-28' then (km) Else 0 end) '28'," +
					 		"sum(case when dat='"+currentdate+"-29' then (km) Else 0 end) '29'," +
					 		"sum(case when dat='"+currentdate+"-30' then (km) Else 0 end) '30'," +
					 		"sum(case when dat='"+currentdate+"-31' then (km) Else 0 end) '31' " +
					 		"from(select license_number,date_format(gen_logsheet_date,'%m-%Y') dat,sum(runningkm) km from `gen_logsheet` gl " +
					 		"inner join vehicle v on v.vehicle_id=gl.vehicle_id " +
					 		"WHERE `runningkm` != '0' AND v.vehicle_id = '"+vhid+"' AND `gen_logsheet_date` LIKE '%"+previousdate+"%' " +
					 		"union select license_number,gen_logsheet_date dat,runningkm km from `gen_logsheet` gl " +
					 		"inner join vehicle v on v.vehicle_id=gl.vehicle_id " +
					 		"WHERE `runningkm` != '0' AND v.vehicle_id = '"+vhid+"' AND `gen_logsheet_date` LIKE '%"+currentdate+"%')a " +
					 		"group by license_number";
				  System.out.println("sql=="+sql);
				  rs = stmt.executeQuery(sql);
				 
				String filePath = "Ticketing/";

				String fileName = "MonthlyPassConsumption.txt";
				
				     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+" </br>"+" </br>Monthly Pass Consumption</br>From Date:- "+"</h4></div>";

			        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
			        
			       
			        
			        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
					regionTypeAjaxString +="<td width='20%'>Vehicle No</td><td width='20%'></td><td width='20%'>1</td>" +
							"<td width='20%'>2</td><td width='20%'>3</td><td width='20%'>4</td><td width='20%'>5</td>" +
							"<td width='20%'>6</td><td width='20%'>7</td><td width='20%'>8</td><td width='20%'>9</td><td width='20%'>10</td><td width='20%'>11</td><td width='20%'>12</td><td width='20%'>13</td>" +
							"<td width='20%'>14</td><td width='20%'>15</td><td width='20%'>16</td><td width='20%'>17</td><td width='20%'>18</td><td width='20%'>19</td>" +
							"<td width='20%'>20</td><td width='20%'>21</td><td width='20%'>22</td><td width='20%'>23</td><td width='20%'>24</td><td width='20%'>25</td><td width='20%'>26</td><td width='20%'>27</td>" +
							"<td width='20%'>28</td><td width='20%'>29</td><td width='20%'>30</td><td width='20%'>31</td></tr>";
    
			       //regionTypeAjaxString +="<tr><td></td><td></td><td width='20%'>Daily Operated KM</td>";
			    //   regionTypeAjaxString +="<tr><td></td><td width='20%'>Cumulative KM</td><td width='20%'>Progressive KM</td></tr>";
					
			                   regionTypeAjaxString +="</tr>";
							   regionTypeAjaxString +="</tr>";
							
							 //  regionTypeAjaxString +="</tr>";
						
						//}
							   
							   int i=1;
								 while (rs.next()) {
									
									regionTypeAjaxString +="<tr>";
									
									//Map<String, Object> list = aliasToValueMapList.get(i);
									
									//regionTypeAjaxString +="<td>"+j+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("license_number").toString()+"</td>";
									regionTypeAjaxString +="<td width='20%'>Daily Operated KM</td>";
									regionTypeAjaxString +="<td>"+rs.getString("01").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("02").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("03").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("04").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("05").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("06").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("07").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("08").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("09").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("10").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("11").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("12").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("13").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("14").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("15").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("16").toString()+"</td>";
									
									regionTypeAjaxString +="<td>"+rs.getString("17").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("18").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("19").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("20").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("21").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("22").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("23").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("24").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("25").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("26").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("27").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("28").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("29").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("30").toString()+"</td>";
									regionTypeAjaxString +="<td>"+rs.getString("31").toString()+"</td>";
									
									regionTypeAjaxString +="</tr>";
									regionTypeAjaxString +="<tr>";
									regionTypeAjaxString +="<td width='20%'>"+rs.getString("cumltvkm").toString()+"</td><td width='20%'>Progressive KM</td>";
									double prog1=Double.parseDouble(rs.getString("cumltvkm").toString())+Double.parseDouble(rs.getString("01").toString());
									double prog2=prog1+Double.parseDouble(rs.getString("02").toString());
									double prog3=prog2+Double.parseDouble(rs.getString("03").toString());
									double prog4=prog3+Double.parseDouble(rs.getString("04").toString());
									double prog5=prog4+Double.parseDouble(rs.getString("05").toString());
									double prog6=prog5+Double.parseDouble(rs.getString("06").toString());
									double prog7=prog6+Double.parseDouble(rs.getString("07").toString());
									double prog8=prog7+Double.parseDouble(rs.getString("08").toString());
									double prog9=prog8+Double.parseDouble(rs.getString("09").toString());
									double prog10=prog9+Double.parseDouble(rs.getString("10").toString());
									double prog11=prog10+Double.parseDouble(rs.getString("11").toString());
									double prog12=prog11+Double.parseDouble(rs.getString("12").toString());
									double prog13=prog12+Double.parseDouble(rs.getString("13").toString());
									double prog14=prog13+Double.parseDouble(rs.getString("14").toString());
									double prog15=prog14+Double.parseDouble(rs.getString("15").toString());
									double prog16=prog15+Double.parseDouble(rs.getString("16").toString());
									double prog17=prog16+Double.parseDouble(rs.getString("17").toString());
									double prog18=prog17+Double.parseDouble(rs.getString("18").toString());
									double prog19=prog18+Double.parseDouble(rs.getString("19").toString());
									double prog20=prog19+Double.parseDouble(rs.getString("20").toString());
									double prog21=prog20+Double.parseDouble(rs.getString("21").toString());
									double prog22=prog21+Double.parseDouble(rs.getString("22").toString());
									double prog23=prog22+Double.parseDouble(rs.getString("23").toString());
									double prog24=prog23+Double.parseDouble(rs.getString("24").toString());
									double prog25=prog24+Double.parseDouble(rs.getString("25").toString());
									double prog26=prog25+Double.parseDouble(rs.getString("26").toString());
									double prog27=prog26+Double.parseDouble(rs.getString("27").toString());
									double prog28=prog27+Double.parseDouble(rs.getString("28").toString());
									double prog29=prog28+Double.parseDouble(rs.getString("29").toString());
									double prog30=prog29+Double.parseDouble(rs.getString("30").toString());
									double prog31=prog30+Double.parseDouble(rs.getString("31").toString());
									regionTypeAjaxString +="<td width='20%'>"+prog1+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog2+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog3+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog4+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog5+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog6+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog7+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog8+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog9+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog10+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog11+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog12+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog13+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog14+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog15+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog16+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog17+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog18+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog19+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog20+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog21+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog22+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog23+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog24+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog25+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog26+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog27+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog28+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog29+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog30+"</td>";
									regionTypeAjaxString +="<td width='20%'>"+prog31+"</td>";
									regionTypeAjaxString +="</tr>";
								}
							   
							   
							   
							   String path = realpath + filePath + fileName;
				 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";

				 ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
				 
				HttpServletResponse response = ServletActionContext.getResponse();
				PrintWriter out;
				
				
				
					
					FileOutputStream FOS = new FileOutputStream(path);
					PrintWriter PW = new PrintWriter(FOS);
				
				PW.close();
					out = response.getWriter();
					out.print(regionTypeAjaxString);
				} catch (Exception e) {
					
					e.printStackTrace();
				}

				return null;
			}
	
	
	private Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  " +
						"where parent_id ='1' and deleted_status=0 order by orgchart.org_name");
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

	
	
	public String getVehicle() {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		int parentId = Integer.parseInt(req.getParameter("val"));
		String regionTypeAjaxString = "";
		
	    	List<String> l1 = getVehicleId(parentId);
			List<String> l2 = getVehicleName(parentId);
			
			regionTypeAjaxString += "<option value='0'>------select------</option>";
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
	    

		return null;
	}
		public List getVehicleId(int orgchart_id) {
			List list = new ArrayList();
			String qry = "select vehicle_id from vehicle where deleted_status=0 and status='ACTIVE' and " +
					"org_chart_id="+orgchart_id+" order by vehicle_id";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("vehicle_id").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
		public List getVehicleName(int orgchart_id) {
			List list = new ArrayList();
			String qry = "select license_number from vehicle where deleted_status=0 and status='ACTIVE' and " +
					"org_chart_id="+orgchart_id+" order by vehicle_id";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("license_number").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}

	
}
