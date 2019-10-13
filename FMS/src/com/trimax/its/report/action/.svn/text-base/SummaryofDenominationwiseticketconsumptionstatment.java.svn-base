package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class SummaryofDenominationwiseticketconsumptionstatment extends ActionSupport{
	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	 public String startdate;
	 public String enddate;
	 public String depotNo;
	 public String divisonNo;
	 public String denom;
	

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
	
	public String getDivisonNo() {
		return divisonNo;
	}

	public void setDivisonNo(String divisonNo) {
		this.divisonNo = divisonNo;
	}
	
	public String getDepotNo() {
		return depotNo;
	}
	
	public void setDepotNo(String depotNo) {
		this.depotNo = depotNo;
	}
	
	public String getdenom() {
		return denom;
	}
	public void setdenom(String denom) {
		this.denom = denom;
	}
	
	
	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	int subtotalTickets=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";

	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
	return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
	this.divisionlist = divisionlist;
	}

	@Override
	public String execute() throws Exception {
	this.setDivisionlist(getDivisionName());
	return "success";
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
	@SuppressWarnings("finally")


	public String AjaxSummaryofDenominationwiseticketconsumptionstatment()
	{
	try {
		  
		 Date  ss1=new Date();
	      Common common = new Common();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
		String date1=common.getDateFromPicker(startdate);
		String date2=common.getDateFromPicker(enddate);
		Session session1 = null;
		Transaction transaction  = null;
		
		
		String querytype ="";
		if(!depotNo.trim().equalsIgnoreCase("0") && !divisonNo.equalsIgnoreCase("0") ){
			querytype="depot_id='"+depotNo+"' and division_id='"+divisonNo+"' and";
			
		}else if(!depotNo.trim().equalsIgnoreCase("0") && divisonNo.equalsIgnoreCase("0") ){
			querytype="depot_id='"+depotNo+"' and";
		}
	    
	    else if(depotNo.trim().equalsIgnoreCase("0") && !divisonNo.equalsIgnoreCase("0") ){
	    	querytype=" division_id='"+divisonNo+"' and";
	    }else{
	    	querytype="";
	    }
		
		String sql="";
		
		if(denom.equalsIgnoreCase("0")){
			sql="SELECT denom,sum(total_tickets) as tick,sum(total_value)as val FROM daily_pass_consumption"+
                   " where dat between '"+date1+"' and '"+date2+"' and  "+querytype+ " pass_day IN('Normal','ETM')"+
                    "group by denom order by CAST(denom AS UNSIGNED)";
		}
		else{ 
			sql="SELECT denom,sum(total_tickets) as tick,sum(total_value) as val FROM daily_pass_consumption" +
			" where dat between '"+date1+"' and '"+date2+"' and "+querytype+" pass_day ='"+denom+"'" +
			" group by denom order by CAST(denom AS UNSIGNED)";
		}
                     
		
		
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
		 Query query = session1.createSQLQuery(sql).addScalar("denom")
		 		.addScalar("tick").addScalar("val");
	 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();

        regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Summary of Denomination wise ticket consumption statement</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";
	    regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
	     		
                  
				     
				        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' " +
				        		"style='width:50%;border-collapse: collapse;'></thead>";
						regionTypeAjaxString +="<td align='center' width='10%'><b>Sl No</b></td><td align='center' width='20%'><b>Denomination</b></td>";
						regionTypeAjaxString +="<td align='center' width='20%'><b>No.of tickets sold</b></td><td align='center' width='20%'><b>Amount</b></td></tr>";


						double totalETMtick = 0;
						double totalETMAmount = 0.0;
						
						for (int i = 0; i < aliasToValueMapList.size(); i++) {
							
							regionTypeAjaxString +="<tr>";
							Map<String, Object> list = aliasToValueMapList.get(i);
							int j=i+1;
							regionTypeAjaxString +="<td>"+j+"</td>";
							regionTypeAjaxString +="<td>"+list.get("denom").toString()+"</td>";
							regionTypeAjaxString +="<td>"+list.get("tick").toString()+"</td>";
							BigDecimal b1=new BigDecimal(Double.parseDouble(list.get("val").toString()));
							 MathContext mc = new MathContext(12);
							b1=b1.round(mc);
							regionTypeAjaxString +="<td>"+b1+"</td>";
							totalETMtick += Double.parseDouble(list.get("tick").toString());
							totalETMAmount += Double.parseDouble(list.get("val").toString());
							
						}
						BigDecimal b3=new BigDecimal(totalETMtick);
						 MathContext mc1 = new MathContext(12);
						b3=b3.round(mc1);
						BigDecimal b2=new BigDecimal(totalETMAmount);
						 MathContext mc = new MathContext(12);
						b2=b2.round(mc);
					 regionTypeAjaxString +="<tr><td align='center' width='20%'colspan='2'><b>Total</b></td><td>"+b3+"</td><td>"+b2+"</td></tr>";
				
							 				
					 
					 
					 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";


					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
	
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (IOException e) {
						
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
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
