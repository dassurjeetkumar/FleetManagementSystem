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
import org.json.simple.JSONArray;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class Denominationwiseticketconsumptionstatment extends ActionSupport {
	String path = "";
	char ft = 15;
	String str = "";
	String c = " ";
	public String startdate;
	public String enddate;
	public String depotNo;
	public String divisonNo;
	public Map<Integer, String> servicetypemap;
	
	public String denomservicetype;

	
	public String getDenomservicetype() {
		return denomservicetype;
	}


	public void setDenomservicetype(String denomservicetype) {
		this.denomservicetype = denomservicetype;
	}


	public Map<Integer, String> getServicetypemap() {
		return servicetypemap;
	}


	public void setServicetypemap(Map<Integer, String> servicetypemap) {
		this.servicetypemap = servicetypemap;
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

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	int subtotalTickets = 0;
	int subtotalValues = 0;

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
		this.setServicetypemap(getServiceType1());
		return "success";
	}

	private Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session.createQuery("from OrganisationChart orgchart  "
				+ "where parent_id ='1' and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			resultMap.put(-1, "All");
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
			regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">" + l2.get(i).toString() + "</option>";
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
		String qry = "select vehicle_id from vehicle where deleted_status=0 and status='ACTIVE' and " + "org_chart_id="
				+ orgchart_id + " order by vehicle_id";
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
		String qry = "select license_number from vehicle where deleted_status=0 and status='ACTIVE' and "
				+ "org_chart_id=" + orgchart_id + " order by vehicle_id";
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

	public String AjaxDenominationwiseticketconsumptionstatment() {
		try {

			Date ss1 = new Date();
			Common common = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			String date1 = common.getDateFromPicker(startdate);
			String date2 = common.getDateFromPicker(enddate);
			Session session1 = null;
			Transaction transaction = null;

			HttpServletRequest req = ServletActionContext.getRequest();

			//this.denomservicetype = req.getParameter("service");
			String querytype = "";
//        if(depotNo.equalsIgnoreCase("select ") && divisonNo.equalsIgnoreCase("0") ){
//            querytype="  ";
//        }
//        else if(depotNo.equalsIgnoreCase("select ") && !divisonNo.equalsIgnoreCase("0")){
//            querytype="  and division_id='"+divisonNo+"'  ";
//           
//        }else {
//            querytype=" and division_id='"+divisonNo+"'  and depot_id='"+depotNo+"' ";
//        }
			
//			if (depotNo.equalsIgnoreCase("select ") && divisonNo.equalsIgnoreCase("0")) {
//				querytype = "  ";
//			} else if (depotNo.equalsIgnoreCase("select ") && divisonNo.equalsIgnoreCase("-1")) {
//				querytype = "  ";
//			} else if (depotNo.equalsIgnoreCase("select ") && !divisonNo.equalsIgnoreCase("0")) {
//				if (divisonNo.equalsIgnoreCase("-1")) {
//					querytype = "";
//				} else {
//					querytype = "  and division_id='" + divisonNo + "'  ";
//				}
//			} else {
//				querytype = " and division_id='" + divisonNo + "'  and depot_id='" + depotNo + "' ";
//			}
			
			  if( divisonNo.equalsIgnoreCase("0") ){
		        	if(depotNo.equalsIgnoreCase("select ")) {
		        		querytype ="";
		        		
		        	}
		        }else if(divisonNo.equalsIgnoreCase("-1") ) {
		        	querytype ="";
		        }else if(depotNo.contains("select") ) {
		        	 querytype=" and division_id='"+divisonNo+"'   ";
		       
		        }else if(depotNo.contains("-1")) {
		        	
		        	querytype=" and division_id='"+divisonNo+"'   ";	
		        }
		        else {
		        	
		        	 querytype=" and division_id='"+divisonNo+"'  and depot_id='"+depotNo+"' ";
		        }

			if(!denomservicetype.equalsIgnoreCase("0")) {
				querytype=querytype+"and service_type='"+denomservicetype+"'";
			}
			 System.out.println("Value of division and depot ---------------->>>>>>>>>>>>"+querytype +"service type ------->>> "+denomservicetype);

			String sql = "select denom,sum(etickets) as etick,sum(evalue) as eval,sum(ntickets) as ntick,sum(nvalue) as nval"
					+ " from (SELECT pass_day,denom,sum(total_tickets) as ntickets,sum(total_value) as nvalue,0 as etickets,0 as evalue "
					+ "FROM daily_pass_consumption where dat between '" + date1 + "' and '" + date2 + "' " + querytype
					+ "and pass_day ='Normal' group by pass_day,denom union all "
					+ "SELECT pass_day,denom,0 as ntickets,0 as nvalue,sum(total_tickets),sum(total_value) FROM daily_pass_consumption "
					+ "where dat between '" + date1 + "' and '" + date2 + "' " + querytype + "  and pass_day ='ETM'  "
					+ "group by pass_day,denom ) a group by denom order by CAST(denom AS UNSIGNED)";

			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			Query query = session1.createSQLQuery(sql).addScalar("denom").addScalar("etick", Hibernate.INTEGER)
					.addScalar("eval", Hibernate.INTEGER).addScalar("ntick", Hibernate.INTEGER)
					.addScalar("nval", Hibernate.INTEGER);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();

//                    String realpath = ServletActionContext.getRequest()
//                            .getRealPath("/");

			regionTypeAjaxString += " <div id='header' style='display: none;'><div align='center'><td colspan='7' align='left'><h4>Denomination wise ticket consumption statement</br>From Date:- "
					+ startdate + " To Date:-" + enddate + "</h4></br></div>";

			regionTypeAjaxString += "<div align='right'><td colspan='7' align='left'><b>Run Date:-</b>" + runDateTime
					+ "</div></div>";

//                         regionTypeAjaxString +=" <div id='header' style='display: none;'><table border='0'><tr><td colspan='7' align='center'><h4>Denomination wise ticket consumption statement</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></td></tr></div>";
//
//                         regionTypeAjaxString +="<div><tr><td colspan='7' align='left'><b>Run Date:-</b>"+runDateTime+"</td></tr></table></div>";

			String nwkr = "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _\n\n"
					+ "                                     " + ""
					+ "                                                                          \n"
					+ "                                            " + ""
					+ "                                                                                  \n"
					+ "                                   Denomination wise ticket consumption statement      "
					+ "  \n                                  From Date:- " + startdate + " To Date:-" + enddate
					+ "    \n" + "                                                               Run Date:-"
					+ runDateTime + "               \n";

			regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
			regionTypeAjaxString += "<td align='center' width='10%'><b>Sl No</b></td><td align='center' width='20%'><b>Denomination(ETM)</b></td>";
			regionTypeAjaxString += "<td align='center' width='20%'><b>No.of tickets sold</b></td><td align='center' width='20%'><b>Amount</b></td>";
			regionTypeAjaxString += "<td align='center' width='10%'><b>Denomination(Manual)</b></td><td align='center' width='20%'><b>No of ticket sold</b></td><td align='center' width='20%'><b>Amount</b></td></tr>";

//                        String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n"
//                                +add("",5)+"|"+add("Date",20)+ "|"+add("Schedule No",20)+ "|"+add("Scheduled Km",20)+"|"+add("Target Earnings",20)+"|"+add("Actual Earnings",20)+"|"+"\n"
//                                 + " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n";
//                       

			double totalETMAmount = 0.0, totalManualAmount = 0.0;
			double totalETMticket = 0, totalManualticket = 0;
			// int totalETMticket = 0,totalManualticket=0;

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				regionTypeAjaxString += "<tr>";
				Map<String, Object> list = aliasToValueMapList.get(i);
				int j = i + 1;
				regionTypeAjaxString += "<td>" + j + "</td>";
				regionTypeAjaxString += "<td>" + list.get("denom").toString() + "</td>";
				regionTypeAjaxString += "<td>" + list.get("etick").toString() + "</td>";
				BigDecimal b = new BigDecimal(list.get("eval").toString());
				MathContext mc1 = new MathContext(12);
				b = b.round(mc1);
				regionTypeAjaxString += "<td>" + b + "</td>";
				regionTypeAjaxString += "<td>" + list.get("denom").toString() + "</td>";
				regionTypeAjaxString += "<td>" + list.get("ntick").toString() + "</td>";
				regionTypeAjaxString += "<td>" + list.get("nval").toString() + "</td>";
				totalETMticket += Double.parseDouble(list.get("etick").toString());
				totalManualticket += Double.parseDouble(list.get("ntick").toString());
				totalETMAmount += Double.parseDouble(list.get("eval").toString());
				totalManualAmount += Double.parseDouble(list.get("nval").toString());

				// totalETMticket += Integer.parseInt(list.get("etick").toString());
				// totalManualticket += Integer.parseInt(list.get("ntick").toString());

			}
			BigDecimal b3 = new BigDecimal(totalETMticket);
			MathContext mc1 = new MathContext(12);
			b3 = b3.round(mc1);
			BigDecimal b2 = new BigDecimal(totalManualticket);
			MathContext mc = new MathContext(12);
			b2 = b2.round(mc);
			BigDecimal b4 = new BigDecimal(totalETMAmount);
			MathContext mc2 = new MathContext(12);
			b4 = b4.round(mc2);
			BigDecimal b5 = new BigDecimal(totalManualAmount);
			MathContext mc3 = new MathContext(12);
			b5 = b5.round(mc3);
			regionTypeAjaxString += "<tr><td align='center' width='20%'colspan='2'><b>Total</b></td><td>" + b3
					+ "</td><td>" + b4 + "</td>" + "<td align='center' width='20%'colspan='1'><td>" + b2 + "</td><td>"
					+ b5 + "</td></tr>";
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
	
	public Map<Integer, String> getServiceType1() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//int userId= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("org_chart_id").toString());
		
		try {
			Query query = session
					.createSQLQuery("select service_type_id,service_type_name from service_type where status='ACTIVE' and deleted_status='0'").addScalar("service_type_id", Hibernate.INTEGER).addScalar("service_type_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			resultMap.put(0, "Select");
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("service_type_id").toString()),rs.get("service_type_name").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
}
