package com.trimax.its.report.action;

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
import org.hibernate.Query;
import org.hibernate.Transaction;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.util.HibernateUtilVtu;
import com.trimax.its.vts.dao.VtsDataDAO;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

	public class MasterSummaryReportAction {
		
	private Map<Integer, String> divisionlist;
	private String depotlist1;
	public String divisionlist1;
	public String cause;
	public String desg;
	


	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
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

	StringBuffer regionTypeAjaxString = new StringBuffer();
	
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
		 
		public String getMasterSummaryDataForVehicle()
       {
			Session session1=null;
			HttpServletRequest request=null;
			String qury="";
			String qury1;
		try {
			Date ss1 = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			Common common = new Common();
			session1=HibernateUtil.getSession("hibernate.cfg.xml");
			request=ServletActionContext.getRequest();
			int type=Integer.parseInt(request.getParameter("type"));
			int depotId=Integer.parseInt(request.getParameter("depot"));
			System.out.println(request.getParameter("depot")+"----"+depotId);
			String div=request.getParameter("divison");
			System.out.println(div+"-----");
			int divId=Integer.parseInt(div.substring(0,1));
				System.out.println(div+"-----");
				String caus=request.getParameter("cause");
		    int cause=Integer.parseInt(caus.substring(0,1));
			System.out.println("depot "+depotId+"===="+divId+"===="+cause);
			
			if(cause == 1){
				qury= " and cause_status='N' " ;
			}else if(cause == 2){
				qury= " and cause_status='S' " ;
			}else if(cause == 3){
				qury= " and cause_status='CW' " ;
			}else if(cause == 4){
				qury= " and cause_status='DW' " ;
			}else if(cause == 5){
				qury= " and cause_status='B' " ;
			}else{
				qury= " and cause_status='A' " ;
			}
			if(divId==0){
				qury1="";
			}
			else if(divId !=0 && depotId==0){
				qury1="and oc.parent_id="+divId;
			} else{
				qury1="and oc.org_chart_id="+depotId;
			}
				
			System.out.println("qury to added==="+qury+"query1 is-----"+qury1);
			
			
			regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Vehicle Master Summary Report</br></h4></br></div>");

			regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
					+ runDateTime + "</div></div>");

			regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
			regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot Name</th><th>Vehicle No</th><th>Device No</th><th>FC Exp Date</th><th>Renewal Date</th>"
					+ "<th>Cause</th><th>Device Type</th></tr></thead><tbody>");
			String qry="";
			System.out.println(type);
			if(type==1){ 
	/*			qry="select license_number,ifnull(DATE_FORMAT(fc_expiry_date,'%d-%m-%Y'),'')expiry_date,v.created_date createdDate,cause_status,device_serial_number,org_name,device_type_name, "+
                      " IFNULL(DATE_FORMAT(fc_renewal_date,'%d-%m-%Y'),'')renewal_date "+
                      " from vehicle v "+
                      " inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
                      " left join vehicle_device vd on v.vehicle_id=vd.vehicle_id and vd.status='active'"+
                      " left join device d on vd.device_id=d.device_id "+
                      " left join device_type dt on d.device_type_id=dt.device_type_id "+
                      " where v.status='ACTIVE' "+qury+" " +
                      //"and "+qury1+" order by org_name,license_number "+
                      "and d.status='ACTIVE' and d.deleted_status=0  and "+qury1+" and dt.status='ACTIVE' order by org_name";*/
				qry="select license_number,ifnull(DATE_FORMAT(fc_expiry_date,'%d-%m-%Y'),'')expiry_date,v.created_date createdDate,cause_status,"+
				"ifnull(device_serial_number,'')device_serial_number,ifnull(org_name,'')org_name,ifnull(device_type_name,'')device_type_name,IFNULL(DATE_FORMAT(fc_renewal_date,'%d-%m-%Y'),'')renewal_date from vehicle v "+
				"left join vehicle_device vd on v.vehicle_id=vd.vehicle_id  and vd.status='ACTIVE' "+
				"left join org_chart oc on v.org_chart_id=oc.org_chart_id "+
				"left join device d on vd.device_id=d.device_id  "+
				"and d.status='ACTIVE' "+
				"left join device_type dt on d.device_type_id=dt.device_type_id "+
				"where v.status='ACTIVE' "+qury+" "+
				"and v.deleted_status=0 "+qury1+" order by org_name";
			}else if(type==4){
			 qry="select distinct (vd.vehicle_id),license_number,oc.org_name,ifnull(DATE_FORMAT(fc_expiry_date,'%d-%m-%Y'),'')expiry_date,"+
			"v.created_date createdDate,cause_status,"+
			"device_serial_number,device_type_name,"+
			"IFNULL(DATE_FORMAT(fc_renewal_date,'%d-%m-%Y'),'')renewal_date "+ 
			"from vehicle_device vd "+
			"inner join vehicle v on vd.vehicle_id=v.vehicle_id "+
			"inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
			"left join device d on vd.device_id=d.device_id "+
			"left join device_type dt on d.device_type_id=dt.device_type_id "+
			"where vd.vehicle_id not in "+ 
			"(select vehicle_id from vehicle_device  where status='active'"+
			") and v.deleted_status=0 and v.status='active'"+qury+" and "+qury1+" order by org_name";
			}
			Query query=session1.createSQLQuery(qry)
					.addScalar("org_name")
					.addScalar("license_number")
					.addScalar("device_serial_number")
					.addScalar("expiry_date")
					.addScalar("renewal_date")
					.addScalar("createdDate")
					.addScalar("cause_status")
					.addScalar("device_type_name");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				
				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString.append("<td>"+ (i+1) +"</td>");
				regionTypeAjaxString.append("<td>"+rs.get("org_name").toString()+"</td>");
				regionTypeAjaxString.append("<td>"+rs.get("license_number").toString()+"</td>");
				regionTypeAjaxString.append("<td>"+rs.get("device_serial_number").toString()+"</td>");
				regionTypeAjaxString.append("<td>"+rs.get("expiry_date").toString()+"</td>");
				if(rs.get("renewal_date").toString().equals("")){
					regionTypeAjaxString.append("<td>--</td>");
				}else {
				regionTypeAjaxString.append("<td>"+rs.get("renewal_date").toString()+"</td>");
				}
//				regionTypeAjaxString.append("<td>"+rs.get("createdDate").toString());
//				regionTypeAjaxString.append("<td>"+rs.get("cause_status").toString());
//				System.out.println("cause is------"+rs.get("cause_status").toString());
				if(rs.get("cause_status").toString().equalsIgnoreCase("N")){
					regionTypeAjaxString.append("<td>Normal</td>");
				}else if(rs.get("cause_status").toString().equalsIgnoreCase("S"))
				{
					regionTypeAjaxString.append("<td>Scrap</td>");
				}else if(rs.get("cause_status").toString().equalsIgnoreCase("CW")){
					regionTypeAjaxString.append("<td>Central Workshop</td>");
				}
				else if(rs.get("cause_status").toString().equalsIgnoreCase("DW")){
					regionTypeAjaxString.append("<td>Depot Workshop</td>");
					
				}else if(rs.get("cause_status").toString().equalsIgnoreCase("B")){
					regionTypeAjaxString.append("<td>Breakdown</td>");
				}
				else {
					regionTypeAjaxString.append("<td>Acident</td>");
				}
					
					
					
				regionTypeAjaxString.append("<td>"+rs.get("device_type_name").toString()+"</td>");
				regionTypeAjaxString.append("</tr>");
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			if (aliasToValueMapList.size() == 0) { // if no records
				regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
				regionTypeAjaxString.append("<tr>");
				regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
				regionTypeAjaxString.append("</tr>");
			}
			PrintWriter out;
			out = response.getWriter();
			out.print(regionTypeAjaxString);       // to print
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session1 !=null){
				session1.close();
			}
			
		}
		return null;
	}
	public String getMasterSummaryDataForSchedule()
	 {
			try {
				Date ss1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				String runDateTime = sdf.format(ss1);
				Common common = new Common();
				
				regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Schedule Master Summary Report</br></h4></br></div>");

				regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
						+ runDateTime + "</div></div>");

				regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
				regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot Name</th><th>Schedule Name</th><th>shift</th><th>Date</th>"
						+ "</tr></thead><tbody>");

				HttpServletResponse response = ServletActionContext.getResponse();
				int i = 1;
				int j=1;
				PrintWriter out;
				out = response.getWriter();
				out.print(regionTypeAjaxString);       // to print
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	public String getMasterSummaryDataForEmployee()
	 {
		Session session =null;
		Transaction transaction=null;
		HttpServletRequest req=null;
		String qryy="";
		String qury1="";
			try {
				Date ss1 = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				String runDateTime = sdf.format(ss1);
				Common common = new Common();
				session=HibernateUtil.getSession("hibernate.cfg.xml");
			//	transaction=session.beginTransaction();
				req=ServletActionContext.getRequest();
				int depotId=Integer.parseInt(req.getParameter("depotlist1"));
				String div=req.getParameter("divisionlist1");
				int divId=Integer.parseInt(div);
				System.out.println("depot "+depotId+"===="+divId);
				System.out.println("desg==="+desg);
			if(Integer.parseInt(desg) == 1){
				qryy= "and EMPLOYEE_DESIGNATION='Driver' " ;
			}else if(Integer.parseInt(desg) == 2){
				qryy= "and EMPLOYEE_DESIGNATION='Conductor' " ;
			}else {
				qryy= "and EMPLOYEE_DESIGNATION='DriverConductor' " ;
			}
			if(divId==0){
				qury1="oc.parent_id in(1,2,3,4,5,6,7)";
			}
			else if(divId !=0 && depotId==0){
				qury1="oc.parent_id="+divId;
			} else{
				qury1="oc.org_chart_id="+depotId;
			}
				
				regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Employee Master Summary Report</br></h4></br></div>");

				regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
						+ runDateTime + "</div></div>");

				regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
				regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Employee Name</th><th>Depot No.</th><th>Emp Designation</th><th>Token no</th><th>Status</th>"
						+ "</tr></thead><tbody>");
				String qry="Select EMPLOYEE_NAME,EMPLOYEE_DESIGNATION,TOKEN,WORKING_DEPOT,ifnull(STATUS,'')STATUS from employee e inner join org_chart oc on e.org_chart_id=oc.org_chart_id where "+qury1+" "+qryy+" order by STATUS,TOKEN ";
				System.out.println("query is---"+qry);
				Query query=session.createSQLQuery(qry)
						.addScalar("EMPLOYEE_NAME")
						.addScalar("EMPLOYEE_DESIGNATION")
						.addScalar("TOKEN")
						.addScalar("WORKING_DEPOT")
						.addScalar("STATUS");
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					
					regionTypeAjaxString.append("<tr>");
					regionTypeAjaxString.append("<td>"+ (i+1) +"</td>");
					regionTypeAjaxString.append("<td>"+rs.get("EMPLOYEE_NAME").toString());
					regionTypeAjaxString.append("<td>"+rs.get("WORKING_DEPOT").toString());
					regionTypeAjaxString.append("<td>"+rs.get("EMPLOYEE_DESIGNATION").toString());
					regionTypeAjaxString.append("<td>"+rs.get("TOKEN").toString());
					regionTypeAjaxString.append("<td>"+rs.get("STATUS").toString());
					regionTypeAjaxString.append("</tr>");
				}
					
				HttpServletResponse response = ServletActionContext.getResponse();
			
				

				if (aliasToValueMapList.size() == 0) { // if no records
					regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
					regionTypeAjaxString.append("<tr>");
					regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
					regionTypeAjaxString.append("</tr>");
				}
				PrintWriter out;
				out = response.getWriter();
				out.print(regionTypeAjaxString);       // to print
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(session !=null){
					session.close();
				}
				
			}
			return null;
		}

	@SuppressWarnings("unchecked")
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
