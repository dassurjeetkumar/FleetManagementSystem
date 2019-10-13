package com.trimax.its.report.action;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
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

public class Dailypassconsumptionreport extends ActionSupport{

String path="";
char ft = 15;
String str="";
String c=" ";
 public String startdate;
 public String enddate;
 public String depotNo;
 public String divisonNo;

 public String Passlist;
 
 private Map<Integer, String> servicetypelist;




public String getPasslist() {
	return Passlist;
}

public void setPasslist(String passlist) {
	Passlist = passlist;
}

public Map<Integer, String> getServicetypelist() {
	return servicetypelist;
}

public void setServicetypelist(Map<Integer, String> servicetypelist) {
	this.servicetypelist = servicetypelist;
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

public String getdailypass() {
	return Passlist;
}

public void setdailypass(String dailypass) {
	this.Passlist = dailypass;
}


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


public String AjaxDailypassconsumptionreport()
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
	if(!depotNo.equalsIgnoreCase("0") && !divisonNo.equalsIgnoreCase("0") && !Passlist.equalsIgnoreCase("0")){
		querytype=" and depot_id='"+depotNo+"' and division_id='"+divisonNo+"' and denom='"+Passlist+"'";
	}
	else if(!depotNo.equalsIgnoreCase("0") && !divisonNo.equalsIgnoreCase("0") && Passlist.equalsIgnoreCase("0")){
		querytype=" and depot_id='"+depotNo+"' and division_id='"+divisonNo+"'  ";
		
	}else if(!depotNo.equalsIgnoreCase("0") && divisonNo.equalsIgnoreCase("0") && !Passlist.equalsIgnoreCase("0")){
		querytype=" and depot_id='"+depotNo+"' and denom='"+Passlist+"'";
	}
    else if(depotNo.equalsIgnoreCase("0") && !divisonNo.equalsIgnoreCase("0") && !Passlist.equalsIgnoreCase("0")){
    	querytype=" and division_id='"+divisonNo+"' and denom ='"+Passlist+"' ";
    }else if(depotNo.equalsIgnoreCase("0") && divisonNo.equalsIgnoreCase("0") && !Passlist.equalsIgnoreCase("0")){
    	querytype=" and denom ='"+Passlist+"' ";
    }
    else if(depotNo.equalsIgnoreCase("0") && divisonNo.equalsIgnoreCase("0") && Passlist.equalsIgnoreCase("0")){
    	querytype="";
    }
	
			
	String sql="SELECT pass_day,denom,sum(total_tickets) as ttickts,sum(total_value) as value," +
			"case  pass_day  when 'monday' then 1  when 'tuesday' then 2 when 'wednesday' then 3  when 'thursday' then 4   " +
			"when 'friday' then 5 when 'saturday' then 6  when 'sunday' then -1  end as day_nr FROM daily_pass_consumption " +
			"where dat between '"+date1+"' and '"+date2+"' and ticket_type=2 "+querytype+" group by pass_day,denom order by CAST(denom AS UNSIGNED),day_nr";

	
	session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	 transaction = session1.beginTransaction();
	 Query query = session1.createSQLQuery(sql).addScalar("pass_day").addScalar("denom")
	 		.addScalar("ttickts").addScalar("value");
 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();

	regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>"+""+" </br>"+ ""+" </br>Day Pass consumption statment</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></div>";
    regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
    
			        regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
					regionTypeAjaxString +="<td align='center' width='10%'><b>Sl No</b></td><td align='center' width='20%'><b>DAYS</b></td><td align='center' width='20%'><b>Denom</b></td>";
					regionTypeAjaxString +="<td align='center' width='20%'><b>NO. OF PASSES</b></td><td align='center' width='20%'><b>TOTAL AMOUNT</b></td></tr>";
					

					double totalticket = 0,totalAmount=0.0;
					
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
						
						regionTypeAjaxString +="<tr>";
						Map<String, Object> list = aliasToValueMapList.get(i);
						int j=i+1;
						regionTypeAjaxString +="<td>"+j+"</td>";
						regionTypeAjaxString +="<td>"+list.get("pass_day").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("denom").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("ttickts").toString()+"</td>";
						regionTypeAjaxString +="<td>"+list.get("value").toString()+"</td>";
						
						totalticket += Double.parseDouble(list.get("ttickts").toString());
						totalAmount += Double.parseDouble(list.get("value").toString());
			
						regionTypeAjaxString +="</tr>";
					}
					regionTypeAjaxString +="<tr><td align='center' width='20%'colspan='3'><b>Total</b></td><td>"+totalticket+"</td><td>"+totalAmount+"</td></tr>";
	 				  
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
						

			
}
