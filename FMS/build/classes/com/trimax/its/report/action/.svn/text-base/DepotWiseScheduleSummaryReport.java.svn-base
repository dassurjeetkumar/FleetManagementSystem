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
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vts.dao.VtsDataDAO;

public class DepotWiseScheduleSummaryReport extends ActionSupport{

	
	 /**
	 * 
	 */
	public String startDate;
	public String enddate;
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String divison;

	public String getDivison() {
		return divison;
	}

	public void setDivison(String divison) {
		this.divison = divison;
	}
	
	StringBuffer regionTypeAjaxString = new StringBuffer();

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
						"where parent_id ='1' and deleted_status=0 and org_type_id = '2' order by orgchart.org_name");
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
	

	 public static String rbKey = String.valueOf(getSysParameterForVts());
	 
	@SuppressWarnings("finally")
public String getDepotwiseScheduleSummary()
  {
	try {
		VtsDataDAO dao=VtsDataDAO.getInstance();
		Date  ss1=new Date();
		String qry="";
		String qry2="in(";
		String qryy="";
	      Common common = new Common();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
		String date1=common.getDateFromPicker(startDate);
		String date2=common.getDateFromPicker(enddate);
		
		int divId=Integer.parseInt(divison);
		if(divId==0){
			qryy="!=0";
		}else{
		List<String> l1 = dao.getDepotId(divId);
		for (int i = 0; i < l1.size(); i++) {
			 qry +=l1.get(i).toString()+",";
		}
		qry =qry2+qry+")";                   
//		System.out.println("qry is====="+qry);
		String depotIn=remove(qry);
		qryy = depotIn +")";
		}
//		System.out.println("added depot is "+qryy);
		
		model.jaxb.xml.trimax.com.VtsResponse6 result = null;
		com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
		com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
		result = port.webGetDepotWiseScheduleSummary(date1, date2, qryy, rbKey); // calling Web service
		
		regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Depot Wise Schedule Summary Report </br>From Date:- "
				+ date1 +  "Todate : "+date2+"</h4></br></div>");

		regionTypeAjaxString.append("<div align='right'><b>Curent Date:-</b>"
				+ runDateTime + "</div></div>");

		regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString.append("<thead><tr><th>Sr.No.</th><th>Depot Name</th><th>Total Schedules</th><th>Operated Sch</th><th>Not Operated Sch</th>" +
				"<th>Late Dept Sch</th><th>Early Arrival Sch</th><th>Route Deviated Sch</th>" 			
				+ "</tr></thead><tbody>");


		HttpServletResponse response = ServletActionContext.getResponse();
		int i = 1;
		int j=1;
		
		for (i = 0; i < result.getWaybillDetails().size(); i++) {

			regionTypeAjaxString.append("<tr>");
			regionTypeAjaxString.append("<td>" + j + "</td>");
			regionTypeAjaxString.append("<td>"
					+ result.getWaybillDetails().get(i).getDepotName()
					+ "</td>");
			
			int depotId=result.getWaybillDetails().get(i).getDepotId();
			int total = geTotalSchedules(depotId,date1,date2);
			 
			 regionTypeAjaxString.append("<td>"+total+"</td>");
			 int notOp=result.getWaybillDetails().get(i).getTotalNotOp();
			 
			 int totalOp=total-notOp;
			regionTypeAjaxString.append("<td>"+totalOp+ "</td>");
			regionTypeAjaxString.append("<td>"+ notOp+ "</td>");
			regionTypeAjaxString.append( "<td>"+ result.getWaybillDetails().get(i).getTotalLate()+ "</td>");
			regionTypeAjaxString.append("<td>"+ result.getWaybillDetails().get(i).getTotalEarly()+ "</td>");
			regionTypeAjaxString.append("<td>"+result.getWaybillDetails().get(i).getTotalDeviated()+"</td>");

			regionTypeAjaxString.append("</tr>");
			j++;
		}
		if (result.getWaybillDetails().size() == 0) { // if no records
			regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
			regionTypeAjaxString.append("<tr>");
			regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
			regionTypeAjaxString.append("</tr>");
		}
		
	
		PrintWriter out;
		out = response.getWriter();
		out.print(regionTypeAjaxString);       // to display
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
	
	// remove last character
	public String remove(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length()-2)==',') {
	      str = str.substring(0, str.length()-2);
	    }
	    return str;
	}
	
	
	
	public int geTotalSchedules(int depotId,String date1,String date2){
		int count=0;
		Session session =null;
		try{
		String query="SELECT count(*)*(DATEDIFF ('"+date2+"', '"+date1+"')+1) count FROM `schedule` " +
				" where (status = 'ACTIVE' or status = 'NEW' or status='Rationalised') "+
               " AND (current_status = 'APPROVED' or current_status = 'CASE WORKER')  AND deleted_status=0 "+
               " and depot_id="+depotId+" ";
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query qry = session.createSQLQuery(query).addScalar("count",Hibernate.INTEGER);
		count = (Integer) qry.uniqueResult();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
		}
		return count;
	}

	
	public static int getSysParameterForVts() {
		int param = 2;
		Session session = null;
		try {
			session = HibernateUtil.getSession("");
			String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
			Query query = session.createSQLQuery(sql);
			if (query.list().size() > 0) {
				param = Integer.parseInt(query.uniqueResult().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return param;
	}
			
							

		}
	