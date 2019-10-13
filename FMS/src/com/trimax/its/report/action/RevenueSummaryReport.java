package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
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
import com.trimax.its.depot.util.DefaultDepotUtil;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.action.DailyScheduleMappingAction;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;

public class RevenueSummaryReport extends ActionSupport{
	private Map<Integer, String> divisionlist;
	private Map<Integer, String> defaultdivisionList;
	private Integer defaultdevisionnumber;
	private Map<Integer, String> servicelist;
	public String startdate;
	public String enddate;
	public String division;
	public String depot;
	public String service;
	
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	public Map<Integer, String> getServicelist() {
		return servicelist;
	}

	public void setServicelist(Map<Integer, String> servicelist) {
		this.servicelist = servicelist;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	
	public void setDefaultdevisionnumber(Integer defaultdevisionNumber) {
		this.defaultdevisionnumber = defaultdevisionNumber;
	}
	
	public Integer getDefaultdevisionnumber() {
		return this.defaultdevisionnumber;
	}
	
	String regionTypeAjaxString = "";
	@Override
	public String execute() throws Exception {	
		DefaultDepotUtil defaultDepo = new DefaultDepotUtil();
		Map<Integer, String> map = defaultDepo.getDefaultDepotUtil();
		this.setDefaultdevisionnumber(map.entrySet().iterator().next().getKey());
//		for (Map.Entry<Integer,String> entry : this.defaultdivisionList.entrySet())  {
//            System.out.println("Key = " + entry.getKey() + 
//                             ", Value = " + entry.getValue()); 
//    } 
		this.setDivisionlist(getDivisionName());
		this.setServicelist(getServiceNAme());
		return "success";
	}

	
	
	@SuppressWarnings("finally")
	public String RevenueSummaryReport()
	{
		
			
				try {
				
					
					Common common=new Common();
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				 
		           
		           SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					 Date startDate = format.parse(startdate);
					 Date endDateO = format.parse(enddate);
					 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
					 String startDate1 = fomat2.format(startDate).toString();
					 String endDate1 = fomat2.format(endDateO).toString();

				    
					Session session1 = null;
					Transaction transaction  = null;
					
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
					 transaction = session1.beginTransaction();
					 String sql="SELECT sum(px_total_amount)ticket," +
					 		"sum(lugg_total_amount)luggage FROM bmtcGprs.etim_gprs_ticket egt  inner join device d on d.device_serial_number = egt.etim_no  " +
					 		"inner join form_four ff on ff.form_four_id = egt.schedule_id inner join schedule s on s.schedule_id = ff.schedule_number_id " +
					 		"inner join schedule_type st on st.schedule_type_id = s.schedule_type  and d.status = 'ACTIVE' and d.deleted_status = 0  " +
					 		"where egt.depot_id = '"+depot+"' and  ticket_no!=0 and bus_service_id='"+service+"' AND egt.ticket_date BETWEEN ('"+startDate1+"') " +
					 		"AND ('"+endDate1+"') ";
						
						
					 Query query = session1.createSQLQuery(sql)
								.addScalar("ticket").addScalar("luggage");
						
					   query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
						List<Map<String, Object>> aliasToValueMapList = query.list();
						
						
//				String denom="";
				//int Tickettotal=0;
				//int Totalvalues=0;
				//int tTickettotal=0;
				//int tTotalvalues=0;
				String ticketmanualname="";
				String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
				
			
				String filePath = "Ticketing/";

				String fileName = "MonthlyPassConsumption.txt";
				
				     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+" </br>"+" </br>Monthly Pass Consumption</br>From Date:- "+"</h4></div>";

			        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
			        
			       
			        
			        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
					regionTypeAjaxString +="<tr><td width='20%' rowspan='2'>ETM Ticket Revenue(a)</td><td width='20%' rowspan='2'>ETM Luggage Revenue(b)</td><td width='20%' rowspan='2'>Manual Ticket Revenue(c)</td><td width='20%' rowspan='2'>CC Revenue(d)</td>" +
							"<td width='20%' rowspan='1' colspan='2'>Daily Pass Revenue</td><td width='20%' rowspan='2'>Monthly Pass Revenue(g)</td><td width='20%' rowspan='2'>Student Pass Revenue(h)</td><td width='20%' rowspan='2'>Chartered Service Revenue(i)</td><td width='20%' rowspan='2'>Dedicated Service Revenue(j)</td>" +
							"<td width='20%' rowspan='2'>Luggage Revenue(k)</td><td width='20%' rowspan='2'>Total Revenue=a+b+c+d+e+f+g+h+i+j+k</td></tr>";
    
			       regionTypeAjaxString +="<tr>" +
					"<td>Rs.65/-(e)</td><td>Rs.70/-(f)</td>" +
					"";
					
							
							   regionTypeAjaxString +="</tr>";
						
							   for (int i = 0; i < aliasToValueMapList.size(); i++) {
									
									regionTypeAjaxString +="<tr>";
									int j=i+1;
									Map<String, Object> list = aliasToValueMapList.get(i);
									if(list.get("ticket")==null){
										regionTypeAjaxString +="<td>"+"0"+"</td>";
									}else{
										regionTypeAjaxString +="<td>"+list.get("ticket").toString()+"</td>";
									}
									if(list.get("luggage")==null){
										regionTypeAjaxString +="<td>"+"0"+"</td>";
									}else{
										regionTypeAjaxString +="<td>"+list.get("luggage").toString()+"</td>";
									}
									
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									regionTypeAjaxString +="<td>"+"0"+"</td>";
									if(list.get("ticket")==null && list.get("luggage")==null){
										regionTypeAjaxString +="<td>"+"0"+"</td>";
									}else{
									double total=Double.parseDouble(list.get("ticket").toString())+Double.parseDouble(list.get("luggage").toString());
									regionTypeAjaxString +="<td>"+total+"</td>";
									}
//									
//									str+=""+add("",5)+"|"+add(date,20)+"|" + add(snumber, 20) +"|" + add(count,20)+"|" + add(count1,20)+"|" + add(total,20)+ "|"+"\n"; 
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

	
	private Map<Integer, String> getServiceNAme() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try{
		String sql="select service_type_id,service_type_name as servicename from service_type where status='ACTIVE' and deleted_status=0 " +
				" ORDER BY servicename ";
		Query query = session.createSQLQuery(sql)
				.addScalar("service_type_id", Hibernate.INTEGER)
				.addScalar("servicename", Hibernate.STRING);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		resultMap.put(0, "select");
		System.out.println("Size of servicetype List : "+aliasToValueMapList.size());
		for(int i=0;i<aliasToValueMapList.size();i++){
			Map<String, Object> rs = aliasToValueMapList.get(i);
			resultMap.put(Integer.parseInt(rs.get("service_type_id").toString()),rs.get("servicename").toString());
		}				
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	
}
